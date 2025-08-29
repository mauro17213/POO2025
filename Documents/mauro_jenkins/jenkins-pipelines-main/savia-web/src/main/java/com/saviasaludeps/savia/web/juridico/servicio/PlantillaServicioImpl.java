package com.saviasaludeps.savia.web.juridico.servicio;

import com.saviasaludeps.savia.dominio.juridico.CntjCampo;
import com.saviasaludeps.savia.dominio.juridico.CntjPlantilla;
import com.saviasaludeps.savia.dominio.juridico.CntjPlantillaCampo;
import com.saviasaludeps.savia.dominio.juridico.CntjProcesoDocumento;
import com.saviasaludeps.savia.negocio.juridico.CntjPlantillaCampoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjPlantillaRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjProcesoDocumentoRemoto;
import com.saviasaludeps.savia.web.juridico.bean.PlantillaBean;
import com.saviasaludeps.savia.web.juridico.utilidades.CntjConstantes;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

/**
 *
 * @author idbohorquez
 */
public class PlantillaServicioImpl extends AccionesBO implements PlantillaServicioIface{
    
    private CntjPlantillaRemoto getCntjPlantillaRemoto() throws Exception {
        return (CntjPlantillaRemoto) RemotoEJB.getEJBRemoto("CntjPlantillaServicio", CntjPlantillaRemoto.class.getName());
    }
    
    private CntjPlantillaCampoRemoto getCntjPlantillaCampoRemoto() throws Exception {
        return (CntjPlantillaCampoRemoto) RemotoEJB.getEJBRemoto("CntjPlantillaCampoServicio", CntjPlantillaCampoRemoto.class.getName());
    }
    
    private CntjProcesoDocumentoRemoto getCntjProcesoDocumentoRemoto() throws Exception {
        return (CntjProcesoDocumentoRemoto) RemotoEJB.getEJBRemoto("CntjProcesoDocumentoServicio", CntjProcesoDocumentoRemoto.class.getName());
    }

    @Override
    public void Accion(PlantillaBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break; 
                case Url.ACCION_VER:
                    ver(bean);
                    break; 
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
                    editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;   
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarCampos(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    duplicarPlantilla(bean);
                    break; 
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }
    
    @Override
    public void cargasInicial(PlantillaBean bean) {
        try {
            cargarListaDocumentosAgrupados(bean);
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de apoyo.");
        }
    }

    private void listar(PlantillaBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getCntjPlantillaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getCntjPlantillaRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de información.");
        }
    }

    private void ver(PlantillaBean bean) {
       try {
            bean.setObjeto(getCntjPlantillaRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al consultar plantilla. ");
        }
    }

    private void crear(PlantillaBean bean) {
        bean.setObjeto(new CntjPlantilla());
        cargarListaDocumentosAgrupados(bean);
    }

    private void guardar(PlantillaBean bean) {
        try {
            if(bean.getObjeto().getNombre().isEmpty()){
                bean.addError("Debe indicar el nombre de la plantilla a crear.");
            }
            if(bean.getObjeto().getDescripcion().isEmpty()){
                bean.addError("Debe indicar la descripción de la plantilla a crear.");
            }
            if(CntjConstantes.htmlVacio(bean.getObjeto().getEstructura())){
                bean.addError("Debe indicar la estructura de la plantilla a crear.");
            }
                        
            if (!bean.getErrores().isEmpty()) {
                return;
            } 
            
            //se consulta cantidad de plantillas del mismo documento
            Integer plantillaExistentes = 0;
            if(bean.getObjeto().getProcesodocumentoId() != null){
                plantillaExistentes = getCntjPlantillaRemoto().plantillasDocumento(bean.getObjeto().getProcesodocumentoId().getId());
            }
            Integer versionanterior = null;
            //se inactivan las otras plantillas del documento
            if(plantillaExistentes > 0){
                bean.auditoriaModificar(bean.getObjeto());
                getCntjPlantillaRemoto().inactivarPlantillasDocumento(bean.getObjeto());   
                //se consulta la version anterior de la nueva plantilla
                versionanterior = getCntjPlantillaRemoto().plantillaIdVersionAnterior(bean.getObjeto().getProcesodocumentoId().getId());
            }
            
            bean.getObjeto().setVersion(String.format("V%s", plantillaExistentes+1));
            bean.auditoriaGuardar(bean.getObjeto());
            bean.getObjeto().setId(getCntjPlantillaRemoto().insertar(bean.getObjeto()));
            if (plantillaExistentes > 0 && versionanterior != null) {
                //se optienen los campos de la version anterior
                List<CntjCampo> campos = getCntjPlantillaCampoRemoto().getCamposPlantilla(versionanterior);
                for (CntjCampo campo : campos) {
                    CntjPlantillaCampo plantillaCampo = new CntjPlantillaCampo();
                    plantillaCampo.setCntjCampoId(campo);
                    plantillaCampo.setCntjPlantillaId(bean.getObjeto());
                    bean.auditoriaGuardar(plantillaCampo);
                    getCntjPlantillaCampoRemoto().insertar(plantillaCampo);
                }
            }
            bean.addMensaje("Se creo el registro de manera exitosa");
        } catch (Exception e) {
            e.printStackTrace();
            bean.addError("Se presento inconveniente al realizar el guardado de la información. " + e.getMessage());
        }
    }

    private void editar(PlantillaBean bean) {
        try {
            cargarListaDocumentosAgrupados(bean);
            bean.setObjeto(getCntjPlantillaRemoto().consultar(bean.getObjeto().getId()));
            int docGenerados = getCntjPlantillaRemoto().documentosGenerados(bean.getObjeto().getId());
            if(docGenerados > 0){
                bean.addError("No se puede editar la plantilla porque ya existen documentos generados.");
            }
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al consultar plantilla. ");
        }
    }

    private void modificar(PlantillaBean bean) {
       try {
            if(bean.getObjeto().getNombre().isEmpty()){
                bean.addError("Debe indicar el nombre de la plantilla a crear.");
            }
            if(bean.getObjeto().getDescripcion().isEmpty()){
                bean.addError("Debe indicar la descripción de la plantilla a crear.");
            }
            if(CntjConstantes.htmlVacio(bean.getObjeto().getEstructura())){
                bean.addError("Debe indicar la estructura de la plantilla a crear.");
            }
                    
            boolean planDocActiva = bean.getRegistros().stream()
                   .anyMatch(elemento -> elemento.getProcesodocumentoId().getId() != null && (elemento.getProcesodocumentoId().getId().equals(bean.getObjeto().getProcesodocumentoId().getId()) && elemento.getActivo() == true && !elemento.getId().equals(bean.getObjeto().getId())));

            if (planDocActiva) {
                bean.addError("Existen plantillas asociadas al documento en estado activo, solo puede existir una plantilla activa por documento.");
            }
         
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            bean.auditoriaModificar(bean.getObjeto());
            getCntjPlantillaRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se actualizó el registro de manera exitosa");
        } catch (Exception e) {
            e.printStackTrace();
            bean.addError("Se presento inconveniente al modificar la información. " + e.getMessage());
        }
    }

    private void listarCampos(PlantillaBean bean) {
        try {
            bean.setListaCampos(getCntjPlantillaCampoRemoto().getCamposPlantilla(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al consultar informacion de campos. ");
        }
    }

    private void duplicarPlantilla(PlantillaBean bean) {
        try {
            bean.setObjeto(getCntjPlantillaRemoto().consultar(bean.getObjeto().getId()));                        
            //se consulta cantidad de plantillas del mismo documento
            Integer plantillaExistentes = 0;
            if(bean.getObjeto().getProcesodocumentoId() != null){
                plantillaExistentes = getCntjPlantillaRemoto().plantillasDocumento(bean.getObjeto().getProcesodocumentoId().getId());
            }
            Integer versionanterior = null;
            //se inactivan las otras plantillas del documento
            if(plantillaExistentes > 0){
                bean.auditoriaModificar(bean.getObjeto());
                getCntjPlantillaRemoto().inactivarPlantillasDocumento(bean.getObjeto());   
                //se consulta la version anterior de la nueva plantilla
                versionanterior = getCntjPlantillaRemoto().plantillaIdVersionAnterior(bean.getObjeto().getProcesodocumentoId().getId());
            }
            
            boolean continuar = true;
            
            CntjPlantilla nuevaPlantilla = new CntjPlantilla();
            nuevaPlantilla.setProcesodocumentoId(bean.getObjeto().getProcesodocumentoId());
            nuevaPlantilla.setNombre(bean.getObjeto().getNombre());
            nuevaPlantilla.setDescripcion(bean.getObjeto().getDescripcion());
            nuevaPlantilla.setActivo(true);
            nuevaPlantilla.setEstructura(bean.getObjeto().getEstructura());
            nuevaPlantilla.setVersion(String.format("V%s", plantillaExistentes+1));
            bean.auditoriaGuardar(nuevaPlantilla);
            try {
                nuevaPlantilla.setId(getCntjPlantillaRemoto().insertar(nuevaPlantilla));
            } catch (Exception e) {
                continuar = false;
                bean.addError("No fue posible duplicar la plantilla.");
            }
            
            if (continuar) {
                List<Integer> guardados = new ArrayList<>();
                if (plantillaExistentes > 0 && versionanterior != null) {
                    try {
                        //se optienen los campos de la version anterior
                        List<CntjCampo> campos = getCntjPlantillaCampoRemoto().getCamposPlantilla(bean.getObjeto().getId());
                        for (CntjCampo campo : campos) {
                            CntjPlantillaCampo plantillaCampo = new CntjPlantillaCampo();
                            plantillaCampo.setCntjCampoId(campo);
                            plantillaCampo.setCntjPlantillaId(nuevaPlantilla);
                            bean.auditoriaGuardar(plantillaCampo);
                            int id = getCntjPlantillaCampoRemoto().insertar(plantillaCampo);
                            guardados.add(id);
                        }
                    } catch (Exception e) {
                        continuar = false;
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al asociar los campos a la nueva plantilla. %s", e.getMessage()), e);
                        bean.addError("Se presento inconveniente al asociar los campos a la nueva plantilla.");
                        for(Integer c : guardados){
                            getCntjPlantillaCampoRemoto().eliminar(c);
                        }
                        getCntjPlantillaRemoto().eliminar(nuevaPlantilla.getId());
                    }
                }
            }
            
            if(continuar){
                bean.addMensaje("Información guardada correctamente.");
            }            
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al duplicar la plantilla. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al duplicar la plantilla. " + e.getMessage());
        }
    }
    
    private void cargarListaDocumentosAgrupados(PlantillaBean bean){
        try {
            bean.setDocumentosGroup(new ArrayList<>());
            List<CntjProcesoDocumento> documentos = getCntjProcesoDocumentoRemoto().consultarDocumentosGeneradosMixtos();
            Map<String, List<CntjProcesoDocumento>> categoriaMap = new HashMap<>();
            for (CntjProcesoDocumento item : documentos) {
                categoriaMap
                        .computeIfAbsent(item.getProcesoId().getNombre(), k -> new ArrayList<>()) // Crea la lista si no existe
                        .add(item); // Agrega el item a la categoría correspondiente
            }
            for (Map.Entry<String, List<CntjProcesoDocumento>> entry : categoriaMap.entrySet()) {
                SelectItemGroup grupo = new SelectItemGroup(entry.getKey()); // Categoría como grupo
                SelectItem[] opciones = entry.getValue().stream()
                        .map(i -> new SelectItem(i.getId(), i.getNombre()))
                        .toArray(SelectItem[]::new);
                grupo.setSelectItems(opciones);
                bean.getDocumentosGroup().add(grupo);
            }
        } catch (Exception e) {
            bean.addError("Se presento inconvenientes al agrupar lista de documentos.");
        }
    }
    
}
