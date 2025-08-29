/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoItem;
import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitudAdjunto;
import com.saviasaludeps.savia.dominio.autorizacion.AuTopeAfiliado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;
import com.saviasaludeps.savia.web.autorizacion.servicio.AuTopeAfiliadoIface;

/**
 *
 * @author NEXOS
 */
@Named
@ViewScoped
public class AuTopeAfiliadoBean extends Url {

    @Autowired
    private AuTopeAfiliadoIface auTopeAfiliadoServicio;
    private AuTopeAfiliado objeto;
    private List<AuTopeAfiliado> registros;
    private LazyDataModel<AuTopeAfiliado> lazyTopeAfiliados;
    //lista maestros
    private List<Maestro> listaTipoDocumentoPersona;
    private HashMap<Integer, Maestro> hashTipoDocumentoPersona;
    private List<Maestro> listaTipoDocumentoEmpresa;
    private List<Maestro> listaGeneroAfiliado;
    private List<Maestro> listaEstadosAfiliado;
    private List<Maestro> listaRegimenAfiliado;
    private List<Ubicacion> listaCiudades;
    private List<Ubicacion> listaUbicaciones;
    private HashMap<Integer, Ubicacion> hashUbicaciones;
    private List<Maestro> listaClasificacion;
    private HashMap<Integer, Maestro> hashClasificacion;
    private List<Maestro> listaTiposAdjuntos;
    private HashMap<Integer, Maestro> hashTiposAdjuntos;
   

    /// lazy adicionales
    private List<AsegAfiliado> listaAfiliados;
    private LazyDataModel<AsegAfiliado> lazyAfiliados;
  

    // variables adicionales
    private AuSolicitudAdjunto objectoAdjunto;
    private AntAnticipoItem objetoItem;
    private UploadedFile archivoAdjunto;
   
    private List<AuSolicitudAdjunto> listaAntAdjuntos;
    
    public AuTopeAfiliadoBean() {
        this.objeto = new AuTopeAfiliado();
        Modulo _mod = super.validarModulo(Modulo.ID_AUTORIZACIONES_TOPES);
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyTopeAfiliados = new LazyDataModel<AuTopeAfiliado>() {

                private List<AuTopeAfiliado> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AuTopeAfiliado> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    lista = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(AuTopeAfiliado objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public AuTopeAfiliado getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (AuTopeAfiliado objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };
        }
    }

    @PostConstruct
    public void cargaInicial() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        getAuTopeAfiliadoServicio().cargaInicial(this);
        listar();
    }

    public AuTopeAfiliadoIface getAuTopeAfiliadoServicio() {
        return auTopeAfiliadoServicio;
    }

    public void setAuTopeAfiliadoServicio(AuTopeAfiliadoIface auTopeAfiliadoServicio) {
        this.auTopeAfiliadoServicio = auTopeAfiliadoServicio;
    }

    public AuTopeAfiliado getObjeto() {
        return objeto;
    }

    public void setObjeto(AuTopeAfiliado objeto) {
        this.objeto = objeto;
    }

    public List<AuTopeAfiliado> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AuTopeAfiliado> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AuTopeAfiliado> getLazyTopeAfiliados() {
        return lazyTopeAfiliados;
    }

    public void setLazyTopeAfiliados(LazyDataModel<AuTopeAfiliado> lazyTopeAfiliados) {
        this.lazyTopeAfiliados = lazyTopeAfiliados;
    }

    public List<Ubicacion> getListaUbicaciones() {
        return listaUbicaciones;
    }

    public void setListaUbicaciones(List<Ubicacion> listaUbicaciones) {
        this.listaUbicaciones = listaUbicaciones;
    }

    public List<Maestro> getListaTipoDocumentoPersona() {
        return listaTipoDocumentoPersona;
    }

    public void setListaTipoDocumentoPersona(List<Maestro> listaTipoDocumentoPersona) {
        this.listaTipoDocumentoPersona = listaTipoDocumentoPersona;
    }

    public HashMap<Integer, Maestro> getHashTipoDocumentoPersona() {
        return hashTipoDocumentoPersona;
    }

    public void setHashTipoDocumentoPersona(HashMap<Integer, Maestro> hashTipoDocumentoPersona) {
        this.hashTipoDocumentoPersona = hashTipoDocumentoPersona;
    }

    public List<Maestro> getListaTipoDocumentoEmpresa() {
        return listaTipoDocumentoEmpresa;
    }

    public void setListaTipoDocumentoEmpresa(List<Maestro> listaTipoDocumentoEmpresa) {
        this.listaTipoDocumentoEmpresa = listaTipoDocumentoEmpresa;
    }

    public List<Maestro> getListaGeneroAfiliado() {
        return listaGeneroAfiliado;
    }

    public void setListaGeneroAfiliado(List<Maestro> listaGeneroAfiliado) {
        this.listaGeneroAfiliado = listaGeneroAfiliado;
    }

    public List<Maestro> getListaEstadosAfiliado() {
        return listaEstadosAfiliado;
    }

    public void setListaEstadosAfiliado(List<Maestro> listaEstadosAfiliado) {
        this.listaEstadosAfiliado = listaEstadosAfiliado;
    }

    public List<Maestro> getListaRegimenAfiliado() {
        return listaRegimenAfiliado;
    }

    public void setListaRegimenAfiliado(List<Maestro> listaRegimenAfiliado) {
        this.listaRegimenAfiliado = listaRegimenAfiliado;
    }

    public List<Ubicacion> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<Ubicacion> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public HashMap<Integer, Ubicacion> getHashUbicaciones() {
        return hashUbicaciones;
    }

    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashUbicaciones) {
        this.hashUbicaciones = hashUbicaciones;
    }

    public List<Maestro> getListaClasificacion() {
        return listaClasificacion;
    }

    public void setListaClasificacion(List<Maestro> listaClasificacion) {
        this.listaClasificacion = listaClasificacion;
    }

    public HashMap<Integer, Maestro> getHashClasificacion() {
        return hashClasificacion;
    }

    public void setHashClasificacion(HashMap<Integer, Maestro> hashClasificacion) {
        this.hashClasificacion = hashClasificacion;
    }

    public List<Maestro> getListaTiposAdjuntos() {
        return listaTiposAdjuntos;
    }

    public void setListaTiposAdjuntos(List<Maestro> listaTiposAdjuntos) {
        this.listaTiposAdjuntos = listaTiposAdjuntos;
    }

    public HashMap<Integer, Maestro> getHashTiposAdjuntos() {
        return hashTiposAdjuntos;
    }

    public void setHashTiposAdjuntos(HashMap<Integer, Maestro> hashTiposAdjuntos) {
        this.hashTiposAdjuntos = hashTiposAdjuntos;
    }

    public List<AsegAfiliado> getListaAfiliados() {
        return listaAfiliados;
    }

    public void setListaAfiliados(List<AsegAfiliado> listaAfiliados) {
        this.listaAfiliados = listaAfiliados;
    }

    public LazyDataModel<AsegAfiliado> getLazyAfiliados() {
        return lazyAfiliados;
    }

    public void setLazyAfiliados(LazyDataModel<AsegAfiliado> lazyAfiliados) {
        this.lazyAfiliados = lazyAfiliados;
    }

    public AuSolicitudAdjunto getObjectoAdjunto() {
        return objectoAdjunto;
    }

    public void setObjectoAdjunto(AuSolicitudAdjunto objectoAdjunto) {
        this.objectoAdjunto = objectoAdjunto;
    }

    public AntAnticipoItem getObjetoItem() {
        return objetoItem;
    }

    public void setObjetoItem(AntAnticipoItem objetoItem) {
        this.objetoItem = objetoItem;
    }

    public UploadedFile getArchivoAdjunto() {
        return archivoAdjunto;
    }

    public void setArchivoAdjunto(UploadedFile archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }

    public List<AuSolicitudAdjunto> getListaAntAdjuntos() {
        return listaAntAdjuntos;
    }

    public void setListaAntAdjuntos(List<AuSolicitudAdjunto> listaAntAdjuntos) {
        this.listaAntAdjuntos = listaAntAdjuntos;
    }
    
    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAuTopeAfiliadoServicio().Accion(this);
    }

    public void refrescarAfiliado() {
        getAuTopeAfiliadoServicio().listarAfiliado(this);
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getAuTopeAfiliadoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        if (getObjeto().getAuSolicitudAdjuntoList() == null) {
            addError("Por lo menos debe tener un adjunto");
        }
        if(getObjeto().getAseAfiliadosId() == null){
            addError("El afiliado es obligatorio");
        }
        if (!super.isError()) {
            super.setAccion(ACCION_GUARDAR);
            getAuTopeAfiliadoServicio().Accion(this);
        }
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getAuTopeAfiliadoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmVer");
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        super.setDoAccion(ACCION_EDITAR);
        getAuTopeAfiliadoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getAuTopeAfiliadoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }
    
    public void ventanaGestionar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_ADICIONAL_1);
        getAuTopeAfiliadoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmInactivar");
            PrimeFaces.current().ajax().update("frmInactivar");
            PrimeFaces.current().executeScript("PF('dialogoInactivar').show();");
        }
        procesoFinal();
    }
    
    public void ventanaAdjunto() {
        
        if (!super.isError()) {
            setObjectoAdjunto(new AuSolicitudAdjunto());
            setListaAntAdjuntos(new ArrayList<>());
            PrimeFaces.current().ajax().update("frmAuditoriaAdjuntoProgramas");
            PrimeFaces.current().resetInputs("frmAdjunto");
            PrimeFaces.current().ajax().update("frmAdjunto");
            PrimeFaces.current().executeScript("PF('dialogoAdjuntos').show();");
        }
        procesoFinal();
    }

    public void guardarGestionar() {
        if (!super.isError()) {
            super.setAccion(Url.ACCION_ADICIONAL_1);
            super.setDoAccion(Url.ACCION_GUARDAR);
            getAuTopeAfiliadoServicio().Accion(this);
        }
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoInactivar').hide();");
        }
        procesoFinal();
    }
    
    public void guardarTopeAdjuntoEditar() {
        if (getListaAntAdjuntos().isEmpty()) {
            addError("No tiene adjuntos para guardar");
        }
        if (!super.isError()) {
            super.setAccion(Url.ACCION_EDITAR);
            super.setDoAccion(Url.ACCION_ADICIONAL_1);
            getAuTopeAfiliadoServicio().Accion(this);
        }
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmAdjunto");
            PrimeFaces.current().ajax().update("frmEditar:pAdjuntoEditar");
            PrimeFaces.current().executeScript("PF('dialogoAdjuntos').hide();");
        }
        procesoFinal();
    }
    
    public void consultarAfiliado() {
        cargaLazyAfiliado();
        PrimeFaces.current().ajax().update("frmAfiliadoLista");
        PrimeFaces.current().executeScript("PF('dialogoAfiliadoLista').show()");
        procesoFinal();
    }

    public void cargaLazyAfiliado() {
        //Afiliado
        lazyAfiliados = new LazyDataModel<AsegAfiliado>() {
            private List<AsegAfiliado> afiliados;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(0).getCantidadRegistros();
            }

            @Override
            public List<AsegAfiliado> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(0).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(0).setPrimerRegistro(primerRegistro);
                getParamConsulta(0).setRegistrosPagina(registrosPagina);
                getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarAfiliado();
                afiliados = getListaAfiliados();
                setRowCount(getParamConsulta(0).getCantidadRegistros());
                return afiliados;
            }

            @Override
            public String getRowKey(AsegAfiliado afiliado) {
                return afiliado.getId().toString();
            }

            @Override
            public AsegAfiliado getRowData(String afiliadoId) {
                Integer id = Integer.valueOf(afiliadoId);
                for (AsegAfiliado afiliado : afiliados) {
                    if (id.equals(afiliado.getId())) {
                        return afiliado;
                    }
                }
                return null;
            }
        };
    }

    public void onRowSelectAfiliado(SelectEvent event) {
        AsegAfiliado afiliado = (AsegAfiliado) event.getObject();
        boolean isActivo = getAuTopeAfiliadoServicio().validarEstadoAfiliado(afiliado.getMaeEstadoAfiliacion(), afiliado.getId(), this);
        if (isActivo) {
            getObjeto().setAseAfiliadosId(afiliado);
            getParamConsulta(0).setFiltros(new HashMap<>());
            PrimeFaces.current().ajax().update("frmCrear:pAfiliadoCrear");
            PrimeFaces.current().executeScript("PF('dialogoAfiliadoLista').hide()");
        }
        generarMensajes();
    }


    public String convertirFecha(Date fecha) {
        try {
            return AuConstantes.formato2.format(fecha);
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public String calcularEdad(Date fecha) {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaNacimiento = AuConstantes.formato5.format(fecha);
            Period periodo = Period.between(LocalDate.parse(fechaNacimiento, fmt), LocalDate.now());
            String texto = periodo.getYears() + " años " + periodo.getMonths() + " meses " + periodo.getDays() + " dias";
            return texto;
        } catch (Exception e) {
            return "";
        }
    }

    public String obtenerDepartamento(int id) {
        try {
            int idPadre = hashUbicaciones.get(id).getUbicacionPadre().getId();
            return hashUbicaciones.get(idPadre).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public String obtenerUbicacion(int id) {
        if (id == 0) {
            return "";
        }
        String ubi = getHashUbicaciones().get(id).getNombreDepartamentoCiudad();
        return ubi;
    }

    public String obtenerMunicipio(int id) {
        try {
            return hashUbicaciones.get(id).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public void mostrarAfiliadoGeneral() {
        PrimeFaces.current().resetInputs("frmAfiliadoGeneral");
        PrimeFaces.current().ajax().update("frmAfiliadoGeneral");
        PrimeFaces.current().executeScript("PF('dlgInfoAfiliado').show();");
    }
    
    public void borrarTopeAfiliadoAdjuntoCrear(String nombre) {
        AuSolicitudAdjunto borraObjecto = new AuSolicitudAdjunto();
        for (AuSolicitudAdjunto peAdjunto : getObjeto().getAuSolicitudAdjuntoList()) {
            if (peAdjunto.getNombre().equals(nombre)) {
                borraObjecto = peAdjunto;
                break;
            }
        }
        getObjeto().getAuSolicitudAdjuntoList().remove(borraObjecto);
        PrimeFaces.current().ajax().update("frmCrear:pAdjuntosCrear");
    }
    
    public void cargarArchivoDocumentoCrear(FileUploadEvent event) throws IOException {
        archivoAdjunto = event.getFile();
        try {
            if (objectoAdjunto.getMaeTipoArchivoId() == null) {
                addError("Tipo Archivo: se necesita un valor.");
                generarMensajes();
                return;
            }
           

            String ruta = PropApl.getInstance().get(PropApl.AU_A3_ADJUNTOS);
            if (ruta == null || ruta.isEmpty()) {
                addError("No esta configurada la ruta para los anticipos del sistema");
                return;
            }
            
            Maestro tipoArchivo = hashTiposAdjuntos.get(objectoAdjunto.getMaeTipoArchivoId());
            if (tipoArchivo != null) {
                objectoAdjunto.setMaeTipoArchivoCodigo(tipoArchivo.getValor());
                objectoAdjunto.setMaeTipoArchivoValor(tipoArchivo.getNombre());
                
            }
           
            objectoAdjunto.setAdjuntoStream(archivoAdjunto.getInputStream());
            String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
            int indiceExtension = nombreAdjunto.lastIndexOf(".");
            objectoAdjunto.setExtension(nombreAdjunto.substring(indiceExtension, nombreAdjunto.length()));
            objectoAdjunto.setNombre(nombreAdjunto);
            objectoAdjunto.setOrigen(AuSolicitudAdjunto.ORIGEN_TOPES);
            objectoAdjunto.setRuta(ruta);
            objectoAdjunto.setExiste(Boolean.TRUE);
            
            //generar nombre archivo
            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
            StringBuilder nombreArchivo = new StringBuilder();
            nombreArchivo.append("au_tope_afiliado_").append(objectoAdjunto.getMaeTipoArchivoCodigo())
                    .append("_").append(sdf.format(new Date()));
            nombreArchivo = new StringBuilder(Util.reemplazarCaracteresEspeciales(nombreArchivo.toString()));
            objectoAdjunto.setArchivo(nombreArchivo.append(objectoAdjunto.getExtension()).toString());
            if(this.getObjeto().getAuSolicitudAdjuntoList() == null){
                this.getObjeto().setAuSolicitudAdjuntoList(new ArrayList<>());
            }
            this.getObjeto().getAuSolicitudAdjuntoList().add(objectoAdjunto);
            objectoAdjunto = new AuSolicitudAdjunto();
            PrimeFaces.current().ajax().update("frmCrear:pAdjuntosCrear");
        } catch (IOException ex) {
            this.addError(ex.toString());
            generarMensajes();
        }
    }
    
    public void borrarAnticiposAdjuntoEditar(String nombre) {
        AuSolicitudAdjunto borraObjecto = new AuSolicitudAdjunto();
        for (AuSolicitudAdjunto peAdjunto : listaAntAdjuntos) {
            if (peAdjunto.getNombre().equals(nombre)) {
                borraObjecto = peAdjunto;
                break;
            }
        }
        listaAntAdjuntos.remove(borraObjecto);
        PrimeFaces.current().ajax().update("frmAdjunto");
    }
    
   public void cargarArchivoDocumentoEditar(FileUploadEvent event) throws IOException {
        archivoAdjunto = event.getFile();
        try {
         
            if (objectoAdjunto.getMaeTipoArchivoId() == null) {
                addError("Tipo: Error de validación: se necesita un valor.");
                generarMensajes();
                return;
            }
           

            String ruta = PropApl.getInstance().get(PropApl.AU_A3_ADJUNTOS);
            if (ruta == null || ruta.isEmpty()) {
                addError("No esta configurada la ruta para los anticipos del sistema");
                return;
            }

            AuSolicitudAdjunto borrarObj = new AuSolicitudAdjunto();
            for (AuSolicitudAdjunto peAdjunto : listaAntAdjuntos) {
                if (peAdjunto.getMaeTipoArchivoId().equals(objectoAdjunto.getMaeTipoArchivoId())) {
                    borrarObj = peAdjunto;
                    break;
                }
            }
            listaAntAdjuntos.remove(borrarObj);
            objectoAdjunto.setAdjuntoStream(archivoAdjunto.getInputStream());
            String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
            int indiceExtension = nombreAdjunto.lastIndexOf(".");
            objectoAdjunto.setExtension(nombreAdjunto.substring(indiceExtension, nombreAdjunto.length()));
            objectoAdjunto.setNombre(nombreAdjunto);
            objectoAdjunto.setOrigen(AuSolicitudAdjunto.ORIGEN_TOPES);
            objectoAdjunto.setRuta(ruta);
            objectoAdjunto.setExiste(Boolean.TRUE);
            Maestro tipoArchivo = hashTiposAdjuntos.get(objectoAdjunto.getMaeTipoArchivoId());
            if (tipoArchivo != null) {
                objectoAdjunto.setMaeTipoArchivoCodigo(tipoArchivo.getValor());
                objectoAdjunto.setMaeTipoArchivoValor(tipoArchivo.getNombre());
            }
            //generar nombre archivo
            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
            StringBuilder nombreArchivo = new StringBuilder();
            nombreArchivo.append("au_tope_afiliado_").append(objectoAdjunto.getMaeTipoArchivoCodigo())
                    .append("_").append(sdf.format(new Date()));
            nombreArchivo = new StringBuilder(Util.reemplazarCaracteresEspeciales(nombreArchivo.toString()));
            objectoAdjunto.setArchivo(nombreArchivo.append(objectoAdjunto.getExtension()).toString());
            listaAntAdjuntos.add(objectoAdjunto);
            objectoAdjunto = new AuSolicitudAdjunto();
            PrimeFaces.current().ajax().update("frmAdjunto");
        } catch (IOException ex) {
            this.addError(ex.toString());
            generarMensajes();
        }
    }
    
    public void descargarAnticipoAdjunto(AuSolicitudAdjunto adjunto) {
        String rutaCompleta = adjunto.getRuta() + adjunto.getArchivo();
        try {
            File file = new File(rutaCompleta);
            byte[] exportContent = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(exportContent);
            int i = rutaCompleta.lastIndexOf(".");
            String ext = rutaCompleta.substring(i, rutaCompleta.length());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseContentLength(exportContent.length);
            String attachmentName = "attachment; filename=\"" + adjunto.getNombre()+ "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".pdf")) {
                ec.setResponseContentType("application/pdf");
            } else {
                throw new Exception();
            }
            try (OutputStream output = ec.getResponseOutputStream()) {
                output.write(exportContent);
            }
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        }
        procesoFinal();
    }
    
    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmTopeAfiliados");
                    crearLog("Listar");
                    break;
                case Url.ACCION_GUARDAR:
                    PrimeFaces.current().ajax().update("frmTopeAfiliados");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmTopeAfiliados");
                case Url.ACCION_VER:
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    switch (getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            crearLog("Editar");
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            crearLog("Guardar Archivos");
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case Url.ACCION_ADICIONAL_1:
                            crearLog("Gestionar");
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().ajax().update("frmTopeAfiliados");
                            crearLog("Guardar gestión", getObjeto().toString());
                            break;
                    }
                    break;
                
            }
        }
        generarMensajes();
    }
}
