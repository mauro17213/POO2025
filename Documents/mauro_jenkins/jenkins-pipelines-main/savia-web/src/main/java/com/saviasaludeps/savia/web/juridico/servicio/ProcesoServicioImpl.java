
package com.saviasaludeps.savia.web.juridico.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.juridico.CntjCampo;
import com.saviasaludeps.savia.dominio.juridico.CntjEstado;
import com.saviasaludeps.savia.dominio.juridico.CntjEstadoProcesoDocumento;
import com.saviasaludeps.savia.dominio.juridico.CntjProceso;
import com.saviasaludeps.savia.dominio.juridico.CntjProcesoDocumento;
import com.saviasaludeps.savia.dominio.juridico.CntjTransicion;
import com.saviasaludeps.savia.negocio.juridico.CntjCampoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjEstadoGrupoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjEstadoPlantillaRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjEstadoProcesoDocumentoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjPlantillaCampoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjPlantillaRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjProcesoDocumentoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjEstadoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjGrupoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjProcesoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjTransicionRemoto;
import com.saviasaludeps.savia.web.juridico.bean.ProcesoBean;
import com.saviasaludeps.savia.web.juridico.utilidades.CntjConstantes;
import com.saviasaludeps.savia.web.singleton.MaestroSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

/**
 *
 * @author idbohorquez
 */
public class ProcesoServicioImpl extends AccionesBO implements ProcesoServicioIface{

    private CtnjProcesoRemoto getCtnjProcesoRemoto() throws Exception {
        return (CtnjProcesoRemoto) RemotoEJB.getEJBRemoto("CntjProcesoServicio", CtnjProcesoRemoto.class.getName());
    }
    
    private CntjCampoRemoto getCntjCampoRemoto() throws Exception {
        return (CntjCampoRemoto) RemotoEJB.getEJBRemoto("CntjCampoServicio", CntjCampoRemoto.class.getName());
    }
    
    private CntjPlantillaRemoto getCntjPlantillaRemoto() throws Exception {
        return (CntjPlantillaRemoto) RemotoEJB.getEJBRemoto("CntjPlantillaServicio", CntjPlantillaRemoto.class.getName());
    }
    
    private CntjPlantillaCampoRemoto getCntjPlantillaCampoRemoto() throws Exception {
        return (CntjPlantillaCampoRemoto) RemotoEJB.getEJBRemoto("CntjPlantillaCampoServicio", CntjPlantillaCampoRemoto.class.getName());
    }
    
    private CtnjEstadoRemoto getCtnjEstadoRemoto() throws Exception {
        return (CtnjEstadoRemoto) RemotoEJB.getEJBRemoto("CntjEstadoServicio", CtnjEstadoRemoto.class.getName());
    }
        
    private CtnjTransicionRemoto getCtnjTransicionRemoto() throws Exception {
        return (CtnjTransicionRemoto) RemotoEJB.getEJBRemoto("CntjTransicionServicio", CtnjTransicionRemoto.class.getName());
    }
        
    private CntjEstadoGrupoRemoto getCntjEstadoGrupoRemoto() throws Exception {
        return (CntjEstadoGrupoRemoto) RemotoEJB.getEJBRemoto("CntjEstadoGrupoServicio", CntjEstadoGrupoRemoto.class.getName());
    }
    
    private CtnjGrupoRemoto getCtnjGrupoRemoto() throws Exception {
        return (CtnjGrupoRemoto) RemotoEJB.getEJBRemoto("CntjGrupoServicio", CtnjGrupoRemoto.class.getName());
    }
    
    private CntjEstadoPlantillaRemoto getCntjEstadoPlantillaRemoto() throws Exception {
        return (CntjEstadoPlantillaRemoto) RemotoEJB.getEJBRemoto("CntjEstadoPlantillaServicio", CntjEstadoPlantillaRemoto.class.getName());
    }
    
    private CntjProcesoDocumentoRemoto getCntjProcesoDocumentoRemoto() throws Exception {
        return (CntjProcesoDocumentoRemoto) RemotoEJB.getEJBRemoto("CntjProcesoDocumentoServicio", CntjProcesoDocumentoRemoto.class.getName());
    }
    
    private CntjEstadoProcesoDocumentoRemoto getCntjEstadoProcesoDocumentoRemoto() throws Exception {
        return (CntjEstadoProcesoDocumentoRemoto) RemotoEJB.getEJBRemoto("CntjEstadoProcesoDocumentoServicio", CntjEstadoProcesoDocumentoRemoto.class.getName());
    }
    
    
    @Override
    public void Accion(ProcesoBean bean) {
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
                            listarEstados(bean);
                            break;
                        case Url.ACCION_VER:
                            verEstado(bean);
                            break;
                        case Url.ACCION_CREAR:
                            crearEstado(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarEstado(bean);
                            break;
                        case Url.ACCION_EDITAR:
                            editarEstado(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            modificarEstado(bean);
                            break;
                        default:
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarTransiciones(bean);
                            break;
                        case Url.ACCION_VER:
                            verTransicion(bean);
                            break;
                        case Url.ACCION_CREAR:
                            crearTransicion(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarTransicion(bean);
                            break;
                        case Url.ACCION_EDITAR:
                            editarTransicion(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            modificarTransicion(bean);
                            break;
                        default:
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarCampos(bean);
                            break;
                        case Url.ACCION_VER:
                            verCampo(bean);
                            break;
                        case Url.ACCION_CREAR:
                            crearCampo(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarCampo(bean);
                            break;
                        case Url.ACCION_EDITAR:
                            editarCampo(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            modificarCampo(bean);
                            break;
                        case Url.ACCION_BORRAR:
                            borrarPlantillaCampo(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            consultarListaMaestros(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            consultarCamposTabla(bean);
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            consultarCamposReferenciados(bean);
                            break;
                        case Url.ACCION_ADICIONAL_4:
                            consultarValoresCamposReferenciados(bean);
                            break;                       
                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarplantillasCampo(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarPlantillasCampo(bean);
                            break;
                        case Url.ACCION_BORRAR:
                            borrarPlantillasCampo(bean);
                            break;
                        default:
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_5:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarGruposEstado(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarEstadoGrupo(bean);
                            break;
                        case Url.ACCION_BORRAR:
                            borrarEstadoGrupo(bean);
                            break;
                        default:
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_6:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarDocumentos(bean);
                            break;
                        case Url.ACCION_VER:
                            verDocumentos(bean);
                            break;
                        case Url.ACCION_CREAR:
                            crearDocumentos(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarDocumentos(bean);
                            break;
                        case Url.ACCION_EDITAR:
                            editarDocumentos(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            modificarDocumentos(bean);
                            break;
                    }
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }
    
    
    @Override
    public void cargasInicial(ProcesoBean bean) {
        try {
            bean.setListaTipoDato(CntjConstantes.listaTipoDato());
            bean.setListaTipoEstado(CntjConstantes.listaTipoEstados());
            bean.setListaTipoProceso(CntjConstantes.getMaestroTipoProceso());
            bean.setListaTablas(getCntjCampoRemoto().listaTablasDb());            
            bean.setListaValoresReferencias(new ArrayList<>());
            bean.setListaProceso(getCtnjProcesoRemoto().getProcesos());
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de apoyo.");
        }
    }

    private void listar(ProcesoBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getCtnjProcesoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getCtnjProcesoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de información.");
        }
    }

    private void crear(ProcesoBean bean) {
        try {
            bean.setObjeto(new CntjProceso());
        } catch (Exception e) {
            bean.addError("Se presento inconveniente. ");
        }
    }

    private void guardar(ProcesoBean bean) {
        try {
            if(bean.getObjeto().getNombre().isEmpty()){
                bean.addError("Debe indicar el nombre del procesos a crear.");
            }
            if(bean.getObjeto().getDescripcion().isEmpty()){
                bean.addError("Debe indicar el nombre del procesos a crear.");
            }
            
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            bean.auditoriaGuardar(bean.getObjeto());
            getCtnjProcesoRemoto().insertar(bean.getObjeto());
            bean.addMensaje("Se creo el registro de manera exitosa");
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al realizar el guardado de la información. ");
        }
    }

    private void ver(ProcesoBean bean) {
        try {
            bean.setObjeto(getCtnjProcesoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al consultar proceso. ");
        }
    }

    private void editar(ProcesoBean bean) {
        try {
            bean.setObjeto(getCtnjProcesoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al consultar proceso. ");
        }
    }

    private void modificar(ProcesoBean bean) {
        try {
            if(bean.getObjeto().getNombre().isEmpty()){
                bean.addError("Debe indicar el nombre del procesos a crear.");
            }
            if(bean.getObjeto().getDescripcion().isEmpty()){
                bean.addError("Debe indicar el nombre del procesos a crear.");
            }
            
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            bean.auditoriaModificar(bean.getObjeto());
            getCtnjProcesoRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se modificó el registro de manera exitosa");
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al realizar modificación de la información. ");
        }
    }

    private void listarTransiciones(ProcesoBean bean) {
        try {
            bean.getParamConsulta(2).setCantidadRegistros(getCtnjTransicionRemoto().consultarCantidadLista(bean.getParamConsulta(2)));
            bean.setRegistrosTransiciones(getCtnjTransicionRemoto().consultarLista(bean.getParamConsulta(2)));
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de información.");
        }
    }
    
    private void verTransicion(ProcesoBean bean){
        try {
            bean.setObjetoTransicion(getCtnjTransicionRemoto().consultar(bean.getObjetoTransicion().getId()));
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al consultar información. ");
        }
    }

    private void crearTransicion(ProcesoBean bean) {
        try {
            bean.setListaEstados(getCtnjEstadoRemoto().listaEstadosProceso(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Se presento inconvenientes al consultar informacion " + e.getMessage());
        }
    }

    private void guardarTransicion(ProcesoBean bean) {
        try {
            if(bean.getObjetoTransicion().getNombre().isEmpty()){
                bean.addError("Debe indicar el nombre de la transición.");
            }
            if(bean.getObjetoTransicion().getCntjEstadoId() == null){
                bean.addError("Debe seleccionar el estado.");
            }
            
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            bean.auditoriaGuardar(bean.getObjetoTransicion());
            getCtnjTransicionRemoto().insertar(bean.getObjetoTransicion());
            bean.addMensaje("Se creo el registro de manera exitosa");
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al realizar el guardado de la información. ");
        }
    }
    
    private void editarTransicion(ProcesoBean bean){
        try {
            bean.setObjetoTransicion(getCtnjTransicionRemoto().consultar(bean.getObjetoTransicion().getId()));
            bean.setListaEstados(getCtnjEstadoRemoto().listaEstadosProceso(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Se presento inconvenientes al consultar informacion " + e.getMessage());
        }
    }
    
    private void modificarTransicion(ProcesoBean bean) {
        try {
            if(bean.getObjetoTransicion().getNombre().isEmpty()){
                bean.addError("Debe indicar el nombre de la transicion.");
            }
            if(bean.getObjetoTransicion().getCntjEstadoId() == null){
                bean.addError("Debe seleccionar el estado.");
            }
            
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            bean.auditoriaModificar(bean.getObjetoTransicion());
            getCtnjTransicionRemoto().actualizar(bean.getObjetoTransicion());
            bean.addMensaje("Se creo el registro de manera exitosa");
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al realizar el guardado de la información. ");
        }
    }

    private void listarEstados(ProcesoBean bean) {
        try {
            bean.getParamConsulta(1).setCantidadRegistros(getCtnjEstadoRemoto().consultarCantidadLista(bean.getParamConsulta(1)));
            bean.setRegistrosEstados(getCtnjEstadoRemoto().consultarLista(bean.getParamConsulta(1)));
            bean.setListaDocumentosProceso(getCntjProcesoDocumentoRemoto().consultarDocumentosProceso(bean.getObjeto().getId()));
            bean.setHashListaDocumentosProceso(CntjConstantes.convertProcesoDocumentoToHash(bean.getListaDocumentosProceso()));
        } catch (Exception e) {
            bean.addError("Se presento inconvenientes al consultar informacion " + e.getMessage());
        }
    }
    
    private void verEstado(ProcesoBean bean){
        try {
            bean.setObjetoEstado(getCtnjEstadoRemoto().consultar(bean.getObjetoEstado().getId()));
            if(bean.getObjetoEstado().getCntjTransicionId() != null){
                bean.setObjetoTransicion(getCtnjTransicionRemoto().consultar(bean.getObjetoEstado().getCntjTransicionId().getId()));
            }  
            bean.setListaEstadoProcesoDocumento(getCntjEstadoProcesoDocumentoRemoto().listaDocumentoEstado(bean.getObjetoEstado().getId()));
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al consultar información. ");
        }
    }

    private void crearEstado(ProcesoBean bean) {
        try {
            bean.setObjetoEstado(new CntjEstado());
            bean.setObjetoTransicion(new CntjTransicion());
            bean.setListaEstados(getCtnjEstadoRemoto().listaEstadosProceso(bean.getObjeto().getId()));
            bean.setListaEstadoProcesoDocumento(new ArrayList<>());
            bean.setObjEstadoProcesoDocumento(new CntjEstadoProcesoDocumento());
            bean.setIdTemporal(-1);
        } catch (Exception e) {
            e.printStackTrace();
            bean.addError("Se presento inconvenientes al consultar informacion " + e.getMessage());
        }
    }

    private void guardarEstado(ProcesoBean bean) {
        try {
            bean.getObjetoEstado().setCntjProcesoId(new CntjProceso(bean.getObjeto().getId()));
            
            if (bean.getObjetoEstado().getNombre().isEmpty()) {
                bean.addError("Debe ingresar el nombre del estado");
            }
            if (bean.getObjetoEstado().getTipo() == null) {
                bean.addError("Debe ingresar el tipo estado");
            }
            if (bean.getObjetoEstado().getCntjProcesoId() == null) {
                bean.addError("Debe asociar el estado a un proceso");
            }
            
            boolean existeInicio = bean.getRegistrosEstados().stream().anyMatch(estado -> estado.getTipo() == CntjConstantes.TIPO_INICIO);
            if(existeInicio && bean.getObjetoEstado().getTipo().equals(CntjConstantes.TIPO_INICIO)){
                bean.addError("Ya existe un estado de tipo inicio, debe elegir otro tipo de estado.");
            }       
            
            boolean existeNombre = bean.getRegistrosEstados().stream().anyMatch(estado -> estado.getNombre().equals(bean.getObjetoEstado().getNombre()));
            if(existeNombre){
                bean.addError("Ya existe un estado con el mismo nombre, debe cambiar el nombre del estado que desea crear.");
            }
            
            boolean guardarTransicion = false;
            if (bean.getObjetoEstado().getTipo() > CntjConstantes.TIPO_INICIO) {
                guardarTransicion = true;
                if (bean.getObjetoTransicion().getNombre().isEmpty()) {
                    bean.addError("Debe indicar el nombre de la transición.");
                }
                if (bean.getObjetoTransicion().getCntjEstadoId() == null) {
                    bean.addError("Debe seleccionar el estado.");
                }
            }
            

            if (!bean.getErrores().isEmpty()) {
                return;
            }
            //guardado de la transicion
            if (guardarTransicion) {
                bean.auditoriaGuardar(bean.getObjetoTransicion());
                int idtransicion = getCtnjTransicionRemoto().insertar(bean.getObjetoTransicion());
                bean.getObjetoEstado().setCntjTransicionId(new CntjTransicion(idtransicion));
            }  
            //guardado del estado
            bean.auditoriaGuardar(bean.getObjetoEstado());
            bean.getObjetoEstado().setId(getCtnjEstadoRemoto().insertar(bean.getObjetoEstado())); 
            
            //guardado de las plantillas asociadas al estado.
            if(!bean.getListaEstadoProcesoDocumento().isEmpty()){
                for(CntjEstadoProcesoDocumento item : bean.getListaEstadoProcesoDocumento()){
                    item.setEstadoId(bean.getObjetoEstado());
                    bean.auditoriaGuardar(item);
                    getCntjEstadoProcesoDocumentoRemoto().insertar(item);
                }
            }
            
                      
            bean.addMensaje("Se creo el registro de manera exitosa");
        } catch (Exception e) {
            e.printStackTrace();
            bean.addError("Se presento inconvenientes al guardar la información.");
        }
    }
    
    private void editarEstado(ProcesoBean bean){
        try {
            bean.setListaEstados(getCtnjEstadoRemoto().listaEstadosProceso(bean.getObjeto().getId()));
            bean.setObjetoEstado(getCtnjEstadoRemoto().consultar(bean.getObjetoEstado().getId()));
            if(bean.getObjetoEstado().getCntjTransicionId() != null){
                bean.setObjetoTransicion(getCtnjTransicionRemoto().consultar(bean.getObjetoEstado().getCntjTransicionId().getId()));
            } 
            bean.setListaEstadoProcesoDocumento(getCntjEstadoProcesoDocumentoRemoto().listaDocumentoEstado(bean.getObjetoEstado().getId()));
            bean.setObjEstadoProcesoDocumento(new CntjEstadoProcesoDocumento());
            bean.setListaEstadoProcesoDocumentoBorrar(new ArrayList<>());
            bean.setIdTemporal(-1);
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al consultar información. ");
        }
    }
    
    private void modificarEstado(ProcesoBean bean){
        try {
            
            if (bean.getObjetoEstado().getNombre().isEmpty()) {
                bean.addError("Debe ingresar el nombre del estado");
            }
            if (bean.getObjetoEstado().getTipo() == null) {
                bean.addError("Debe ingresar el tipo estado");
            }
            if (bean.getObjetoEstado().getCntjProcesoId() == null) {
                bean.addError("Debe asociar el estado a un proceso");
            }
            
            boolean existeInicio = bean.getRegistrosEstados().stream().anyMatch(estado -> (estado.getTipo() == CntjConstantes.TIPO_INICIO && estado.getId() != bean.getObjetoEstado().getId()));
            if(existeInicio && bean.getObjetoEstado().getTipo().equals(CntjConstantes.TIPO_INICIO)){
                bean.addError("Ya existe un estado de tipo inicio, debe elegir otro tipo de estado.");
            } 
            
            boolean guardarTransicion = false;
            if (bean.getObjetoEstado().getTipo() > CntjConstantes.TIPO_INICIO) {
                guardarTransicion = true;
                if (bean.getObjetoTransicion().getNombre().isEmpty()) {
                    bean.addError("Debe indicar el nombre de la transición.");
                }
                if (bean.getObjetoTransicion().getCntjEstadoId() == null) {
                    bean.addError("Debe seleccionar el estado.");
                }
            }

            if (!bean.getErrores().isEmpty()) {
                return;
            }
            //guardado del campo
            bean.auditoriaModificar(bean.getObjetoEstado());
            getCtnjEstadoRemoto().actualizar(bean.getObjetoEstado());      
            //modificar transicion
            if (guardarTransicion) {
                bean.auditoriaModificar(bean.getObjetoTransicion());
                getCtnjTransicionRemoto().actualizar(bean.getObjetoTransicion());
            }
            
            //Se valida si existen documentos para quitar
            if(!bean.getListaEstadoProcesoDocumentoBorrar().isEmpty()){
                for(CntjEstadoProcesoDocumento item : bean.getListaEstadoProcesoDocumentoBorrar()){
                    getCntjEstadoProcesoDocumentoRemoto().eliminar(item.getId());
                }
            }
            
            //se valida si existen nuevos documentos para asociar
            List<CntjEstadoProcesoDocumento> nuevoDocumento = bean.getListaEstadoProcesoDocumento().stream()
                .filter(item -> item.getId() < 0) 
                .collect(Collectors.toList());
            
            if(!nuevoDocumento.isEmpty()){
                for(CntjEstadoProcesoDocumento item : nuevoDocumento){
                    item.setEstadoId(bean.getObjetoEstado());
                    bean.auditoriaGuardar(item);
                    getCntjEstadoProcesoDocumentoRemoto().insertar(item);
                }
            }
            
            bean.addMensaje("Información modificada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
             bean.addError("Se presento inconvenientes al modificar la información.");
        }
    }

    private void listarCampos(ProcesoBean bean) {
        try {
            bean.getParamConsulta(0).setCantidadRegistros(getCntjCampoRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistrosCampos(getCntjCampoRemoto().consultarLista(bean.getParamConsulta(0)));
            bean.setListaValorLista(new ArrayList<>());
            consultarListaMaestros(bean);
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de información.");
        }
    }

    private void verCampo(ProcesoBean bean) {
        try {
            bean.setObjetoCampo(getCntjCampoRemoto().consultar(bean.getObjetoCampo().getId()));
            if(bean.getObjetoCampo().getValoresLista() != null){
                JSONArray arrayJson = new JSONArray(bean.getObjetoCampo().getValoresLista());  
                List<String> listaValores = new ArrayList<>();
                for(int index=0; index < arrayJson.length(); index++){
                    JSONObject obj = arrayJson.getJSONObject(index);
                    listaValores.add(String.format("%s-%s", obj.getInt(CntjConstantes.VALOR), obj.getString(CntjConstantes.OPCION)));
                }
                bean.setListaValorLista(listaValores);
            }
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al consultar información. ");
        }
    }
    
    private void crearCampo(ProcesoBean bean) {
        try {
            bean.setObjetoCampo(new CntjCampo());
            bean.setAplicaMaestro(false);
            bean.setListaValorLista(new ArrayList<>());
            bean.setMaeValorLista(new Maestro());
            /*bean.setListaPlantilla(getCntjPlantillaRemoto().lista());
            bean.setHashListaPlantilla(CntjConstantes.obtenerHashPlantillas(bean.getListaPlantilla()));*/
        } catch (Exception e) {
            e.printStackTrace();
            bean.addError("Se presento inconvenientes al consultar informacion " + e.getMessage());
        }
    }

    private void guardarCampo(ProcesoBean bean) {
        try {
            bean.getObjetoCampo().setCntjProcesoId(bean.getObjeto());
            if (bean.getObjetoCampo().getNombre().isEmpty()) {
                bean.addError("Debe ingresar el nombre del campo.");
            }
            if (bean.getObjetoCampo().getEtiqueta().isEmpty()) {
                bean.addError("Debe ingresar la etiqueta del campo.");
            }
            if (bean.getObjetoCampo().getTipoDato() == null) {
                bean.addError("Debe seleccionar el tipo de dato.");
            }            
            if(bean.getObjetoCampo().getTipoDato().equals(CntjConstantes.TIPO_DATO_LISTA) && !bean.getObjetoCampo().isAplicaMaestro() && bean.getListaValorLista().isEmpty()){
                bean.addError("Debe agregar opciones a la lista de valores para este campo.");
            } 
            
            boolean existeNombre = getCntjCampoRemoto().existeNombreCampo(bean.getObjeto().getId(), bean.getObjetoCampo().getNombre());
            if(existeNombre){
                bean.addError("Ya existe un campo con el mismo nombre, debe ingresar un nombre diferente.");
            } 
            
            boolean existeEtiqueta = getCntjCampoRemoto().existeEtiquetaCampo(bean.getObjeto().getId(), bean.getObjetoCampo().getEtiqueta());
            if(existeEtiqueta){
                bean.addError("Ya existe un campo con la misma etiqueta, debe ingresar una etiqueta diferente.");
            } 
            
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            if(!bean.getListaValorLista().isEmpty()){
                JSONArray arrayjson = new JSONArray();
                for(String it : bean.getListaValorLista()){
                    String[] valores = it.split("-");
                    JSONObject jsonItem = new JSONObject();
                    jsonItem.put(CntjConstantes.VALOR, valores[0]);
                    jsonItem.put(CntjConstantes.OPCION, valores[1]);
                    arrayjson.put(jsonItem);
                }
                bean.getObjetoCampo().setValoresLista(arrayjson.toString());
            }
            
            
            bean.getObjetoCampo().setEtiqueta(CntjConstantes.setTag(bean.getObjetoCampo().getEtiqueta()));
            bean.auditoriaGuardar(bean.getObjetoCampo());
            int idcampo = getCntjCampoRemoto().insertar(bean.getObjetoCampo());            
            bean.addMensaje("Se creo el registro de manera exitosa");
        } catch (Exception e) {
            bean.addError("Se presento inconvenientes al guardar la información.");
        }
    }

    private void editarCampo(ProcesoBean bean) {
        try {
            bean.setAplicaMaestro(false);
            bean.setObjetoCampo(getCntjCampoRemoto().consultar(bean.getObjetoCampo().getId()));
            String etiqueta = bean.getObjetoCampo().getEtiqueta().replaceAll("\\{\\{", "").replaceAll("\\}\\}", "");
            bean.getObjetoCampo().setEtiqueta(etiqueta);
            bean.setMaeValorLista(new Maestro());
           if(bean.getObjetoCampo().getValoresLista() != null){
                JSONArray arrayJson = new JSONArray(bean.getObjetoCampo().getValoresLista());  
                List<String> listaValores = new ArrayList<>();
                for(int index=0; index < arrayJson.length(); index++){
                    JSONObject obj = arrayJson.getJSONObject(index);
                    listaValores.add(String.format("%s-%s", obj.getInt(CntjConstantes.VALOR), obj.getString(CntjConstantes.OPCION)));
                }
                bean.setListaValorLista(listaValores);
            }
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al consultar información. ");
        }
    }

    private void modificarCampo(ProcesoBean bean) {
        try {
            if (bean.getObjetoCampo().getNombre().isEmpty()) {
                bean.addError("Debe ingresar el nombre del campo.");
            }
            if (bean.getObjetoCampo().getEtiqueta().isEmpty()) {
                bean.addError("Debe ingresar la etiqueta del campo.");
            }
            if (bean.getObjetoCampo().getTipoDato() == null) {
                bean.addError("Debe seleccionar el tipo de dato.");
            }

            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            if(!bean.getListaValorLista().isEmpty()){
                JSONArray arrayjson = new JSONArray();
                for(String it : bean.getListaValorLista()){
                    String[] valores = it.split("-");
                    JSONObject jsonItem = new JSONObject();
                    jsonItem.put(CntjConstantes.VALOR, valores[0]);
                    jsonItem.put(CntjConstantes.OPCION, valores[1]);
                    arrayjson.put(jsonItem);
                }
                bean.getObjetoCampo().setValoresLista(arrayjson.toString());
            }
            
            bean.getObjetoCampo().setEtiqueta(CntjConstantes.setTag(bean.getObjetoCampo().getEtiqueta()));

            bean.auditoriaModificar(bean.getObjetoCampo());
            getCntjCampoRemoto().actualizar(bean.getObjetoCampo());
            bean.addMensaje("Se modificó el registro de manera exitosa.");
        } catch (Exception e) {
            e.printStackTrace();
            bean.addError("Se presento inconvenientes al modificar la información.");
        }
    }

    private void borrarPlantillaCampo(ProcesoBean bean) {
        try {
            
        } catch (Exception e) {
            bean.addError("Se presento inconvenientes al quitar la plantilla.");
        }
    }
    
    private void consultarListaMaestros(ProcesoBean bean) {
        try {
            MaestroSingle.getInstance().agregarMaestroTipoActivo();
            bean.setListaMaestroTipo(MaestroSingle.getInstance().getlistaMaestroTipo());   
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar información.");
        } 
    }
    
    private void consultarCamposTabla(ProcesoBean bean) {
        try {
            bean.setListaCampos(getCntjCampoRemoto().listaCamposTabla(bean.getObjetoCampo().getTabla()));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar información.");
        } 
    }

    private void listarplantillasCampo(ProcesoBean bean) {
        try {
            bean.setListaPlantilla(getCntjPlantillaRemoto().listaPlantillasProceso(bean.getObjetoCampo().getCntjProcesoId().getId()));
            bean.setObjetoCampo(getCntjCampoRemoto().consultar(bean.getObjetoCampo().getId()));
            bean.getObjetoCampo().setPlantillasCampo(getCntjPlantillaCampoRemoto().plantillasCampos(bean.getObjetoCampo().getId()));            
            bean.setHashListaPlantilla(CntjConstantes.obtenerHashPlantillas(bean.getListaPlantilla()));
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al consultar información. ");
        }
    }

    private void guardarPlantillasCampo(ProcesoBean bean) {
        try {            
            bean.auditoriaGuardar(bean.getObjetoPlantillaCampo());
            bean.getObjetoPlantillaCampo().setCntjCampoId(bean.getObjetoCampo());
            getCntjPlantillaCampoRemoto().insertar(bean.getObjetoPlantillaCampo());
            bean.addMensaje("Plantilla agregada correctamente");
        } catch (Exception e) {
            bean.addError("Se presento inconvenientes al agregar la plantilla.");
        }
    }

    private void borrarPlantillasCampo(ProcesoBean bean) {
        try {
            bean.setObjetoPlantillaCampo(getCntjPlantillaCampoRemoto().eliminar(bean.getObjetoPlantillaCampo().getId()));
            bean.addMensaje("Plantilla retirada correctamente.");
        } catch (Exception e) {
            bean.addError("Se presento inconvenientes al remover la plantilla.");
        }
    }

    private void listarGruposEstado(ProcesoBean bean) {
        try {
            bean.setObjetoEstado(getCtnjEstadoRemoto().consultar(bean.getObjetoEstado().getId()));
            bean.getObjetoEstado().setListaEstadoGrupo(getCntjEstadoGrupoRemoto().gruposEstado(bean.getObjetoEstado().getId()));
            bean.setListaGrupos(getCtnjGrupoRemoto().lista());
            bean.setListaEjecucion(CntjConstantes.listaejecucion());
        } catch (Exception e) {
            bean.addError("Se presento inconvenientes al consultar informacion.");
        }
    }

    private void guardarEstadoGrupo(ProcesoBean bean) {
        try {
            if (bean.getObjetoEstadoGrupo().getEstadoId().getId() == null) {
                bean.addError("Debe inidicar el estado al que va asociar el grupo.");
            }
            if (bean.getObjetoEstadoGrupo().getGrupoId().getId() == null) {
                bean.addError("Debe seleccionar el grupo que desea asociar al estado.");
            }
            
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            bean.auditoriaGuardar(bean.getObjetoEstadoGrupo());
            getCntjEstadoGrupoRemoto().insertar(bean.getObjetoEstadoGrupo());
            bean.getObjetoEstado().setListaEstadoGrupo(getCntjEstadoGrupoRemoto().gruposEstado(bean.getObjetoEstado().getId()));
            bean.addMensaje("Se creo el registro de manera exitosa");
        } catch (Exception e) {
            bean.addError("Se presento inconvenientes al guardar la información.");
        }
    }

    private void borrarEstadoGrupo(ProcesoBean bean) {
        try {
            getCntjEstadoGrupoRemoto().eliminar(bean.getObjetoEstadoGrupo().getId());
            bean.addMensaje("Se quitó el grupo de manera exitosa");
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconvenientes al realizar la acción.. %s", e.getMessage()), e);
            bean.addError("Se presento inconvenientes al realizar la acción.");
        }
    }

    private void listarDocumentos(ProcesoBean bean) {
        try {
            bean.getParamConsulta(3).setCantidadRegistros(getCntjProcesoDocumentoRemoto().consultarCantidadLista(bean.getParamConsulta(3)));
            bean.setRegistrosDocumentos(getCntjProcesoDocumentoRemoto().consultarLista(bean.getParamConsulta(3)));            
            bean.setObjetoDocumento(new CntjProcesoDocumento());
            bean.setListaEtapaDesignacion(CntjConstantes.getMaestroEtapaDesignacion());
            bean.setListaTipoDocumentos(CntjConstantes.getMaestroTipoDocumento());
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconvenientes al consultar la informacion. %s", e.getMessage()), e);
            bean.addError("Se presento inconvenientes al consultar la informacion.");
        }
    }

    private void crearDocumentos(ProcesoBean bean) {
        try {
            bean.setObjetoDocumento(new CntjProcesoDocumento());
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconvenientes al realizar la acción. %s", e.getMessage()), e);
            bean.addError("Se presento inconvenientes al realizar la acción.");
        }
    }

    private void guardarDocumentos(ProcesoBean bean) {
        try {
            bean.getObjetoDocumento().setProcesoId(bean.getObjeto());
            
            if (bean.getObjetoDocumento().getProcesoId() == null) {
                bean.addError("Debe asociar el proceso al que pertenese el documento a crear.");
            }
            if (bean.getObjetoDocumento().getNombre() == null) {
                bean.addError("Debe agregar el nombre del documento.");
            }
            if (bean.getObjetoDocumento().getEtapaContratacion() == null) {
                bean.addError("Debe agregar la etapa de contratación del documento.");
            }
            if (bean.getObjetoDocumento().getTipoDocumento() == null) {
                bean.addError("Debe agregar el tipo del documento.");
            }
            
            boolean existeNombre = bean.getRegistrosDocumentos().stream().anyMatch(item -> item.getNombre().equals(bean.getObjetoDocumento().getNombre()));
            if(existeNombre){
                bean.addError("Ya existe un documento con el mismo nombre, debe ingresar un nombre diferente.");
            }  

            if (!bean.getErrores().isEmpty()) {
                return;
            }
            bean.auditoriaGuardar(bean.getObjetoDocumento());
            getCntjProcesoDocumentoRemoto().insertar(bean.getObjetoDocumento());
            bean.addMensaje("Información guardada correctamente.");            
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconvenientes al realizar la acción. %s", e.getMessage()), e);
            bean.addError("Se presento inconvenientes al realizar la acción.");
        }
    }

    private void verDocumentos(ProcesoBean bean) {
        try {
           bean.setObjetoDocumento(getCntjProcesoDocumentoRemoto().consultar(bean.getObjetoDocumento().getId()));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconvenientes al realizar la acción. %s", e.getMessage()), e);
            bean.addError("Se presento inconvenientes al realizar la acción.");
        }
    }

    private void editarDocumentos(ProcesoBean bean) {
        try {
           bean.setObjetoDocumento(getCntjProcesoDocumentoRemoto().consultar(bean.getObjetoDocumento().getId()));             
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconvenientes al realizar la acción. %s", e.getMessage()), e);
            bean.addError("Se presento inconvenientes al realizar la acción.");
        }
    }

    private void modificarDocumentos(ProcesoBean bean) {
        try {
           if (bean.getObjetoDocumento().getProcesoId() == null) {
                bean.addError("Debe asociar el proceso al que pertenese el documento a crear.");
            }
            if (bean.getObjetoDocumento().getNombre() == null) {
                bean.addError("Debe agregar el nombre del documento.");
            }
            if (bean.getObjetoDocumento().getEtapaContratacion() == null) {
                bean.addError("Debe agregar la etapa de contratación del documento.");
            }
            if (bean.getObjetoDocumento().getTipoDocumento() == null) {
                bean.addError("Debe agregar el tipo del documento.");
            }

            boolean existeNombre = bean.getRegistrosDocumentos().stream().anyMatch(item -> (item.getNombre().equals(bean.getObjetoDocumento().getNombre()) && !item.getId().equals(bean.getObjetoDocumento().getId())));
            if(existeNombre){
                bean.addError("Ya existe un documento con el mismo nombre, debe ingresar un nombre diferente.");
            } 
            
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            bean.auditoriaModificar(bean.getObjetoDocumento());
            getCntjProcesoDocumentoRemoto().actualizar(bean.getObjetoDocumento());
            bean.addMensaje("Información modificada correctamente.");   
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconvenientes al realizar la acción. %s", e.getMessage()), e);
            bean.addError("Se presento inconvenientes al realizar la acción.");
        }
    }

    private void consultarCamposReferenciados(ProcesoBean bean) {
        try {
            bean.setListaCamposReferenciados(getCntjCampoRemoto().listaCamposReferenciados(bean.getObjeto().getId()));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconvenientes al listar informacion. %s", e.getMessage()), e);
            bean.addError("Se presento inconvenientes al listar informacion.");
        }
    }

    private void consultarValoresCamposReferenciados(ProcesoBean bean) {
        try {
            if(bean.getObjetoCampo().getCampoReferencia() != null){
                Optional<CntjCampo> campo = bean.getListaCamposReferenciados().stream()
                    .filter(item -> item.getNombre().equals(bean.getObjetoCampo().getCampoReferencia()))
                    .findFirst();
                if(campo.isPresent()){
                    if(campo.get().getTipoDato().equals(CntjConstantes.TIPO_DATO_TERCERO)){
                        bean.setListaValoresReferencias(CntjConstantes.getValoresRefTerceros());
                    }else if(campo.get().getTipoDato().equals(CntjConstantes.TIPO_DATO_USUARIO)){
                        bean.setListaValoresReferencias(CntjConstantes.getValoresRefUsuario());
                    }
                }
            }  
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconvenientes al listar informacion. %s", e.getMessage()), e);
            bean.addError("Se presento inconvenientes al listar informacion.");
        }
    }
    

    
}
