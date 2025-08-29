/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.crue.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitudAdjunto;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesionalPrestador;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Rescate;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2RescateGestion;
import com.saviasaludeps.savia.dominio.crue.AuTipoRescate;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import com.saviasaludeps.savia.web.crue.servicio.RescateIface;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.annotation.PreDestroy;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author iavenegas
 */
@ManagedBean
@ViewScoped
public class RescateBean extends Url {

    private RescateIface rescateServicio;
   
    private List<AuAnexo2Rescate> registros;
    private LazyDataModel<AuAnexo2Rescate> lazyRegistros;
    private List<AuAnexo2RescateGestion> registrosGestiones;
    private LazyDataModel<AuAnexo2RescateGestion> lazyRegistrosGestiones;
    private List<Maestro> listaTiposDocumentoPersona;

    private HashMap<Integer, Ubicacion> hashUbicaciones;

    private AuAnexo2Rescate objeto;
    private AuAnexo2RescateGestion objetoGestion;
    private AuAnexo3 objetoAnexo3;
    private AuAnexo2 objetoAnexo2;
    private AucHospitalizacion objetoHospitalizacion;
    private CntProfesionalPrestador objetoProfesionalPrestador;
    private AuSolicitudAdjunto objectoAdjunto;
    private UploadedFile archivoAdjunto;
    private List<AuSolicitudAdjunto> auSolicitudAdjunto;
    private List<Maestro> listaTiposRescateAdjuntos;
    private List<Maestro> listaMaeMotivos;
    private HashMap<Integer, Maestro> hashTiposRescateAdjuntos;
    private String telefonoFijoAfiliado;
    private String telefonoMovilAfiliado;
    private String descripcionGenerica;
    private int estadoRescate;
    private List<AuTipoRescate> listadoTipoRescates;
    public static final char ACCION_CREAR_ADJUNTO_RESCATE = 'a';
    public static final char ACCION_GUARDAR_ADJUNTO_RESCATE = 'B';
    /**
     * Creates a new instance of PluripatologicoBean
     */
    public RescateBean() {
        this.objeto = new AuAnexo2Rescate();
        Modulo mod = super.validarModulo(Modulo.ID_REFERENCIA_RESCATES);
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.getParamConsulta(0).setEmpresaId(super.getConexion().getEmpresa().getId());
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyRegistros = new LazyDataModel<AuAnexo2Rescate>() {

                private List<AuAnexo2Rescate> lista;
                
                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

                @Override
                    public List<AuAnexo2Rescate> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(0).setPrimerRegistro(primerRegistro);
                    getParamConsulta(0).setRegistrosPagina(registrosPagina);
                    getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    lista = getRegistros();
                    setRowCount(getParamConsulta(0).getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(AuAnexo2Rescate objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public AuAnexo2Rescate getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (AuAnexo2Rescate objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };
            lazyRegistrosGestiones = new LazyDataModel<AuAnexo2RescateGestion>() {

                private List<AuAnexo2RescateGestion> lista;
                
                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

                @Override
                public List<AuAnexo2RescateGestion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(1).setPrimerRegistro(primerRegistro);
                    getParamConsulta(1).setRegistrosPagina(registrosPagina);
                    getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(1).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarGestion();
                    lista = getRegistrosGestiones();
                    setRowCount(getParamConsulta(1).getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(AuAnexo2RescateGestion objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public AuAnexo2RescateGestion getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (AuAnexo2RescateGestion objeto : lista) {
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
        getRescateServicio().cargaInicial(this);
        listar();
    }

    @PreDestroy
    public void preDestroy() {
        this.hashUbicaciones = null;
        this.objeto = null;
    }

    public RescateIface getRescateServicio() {
        return rescateServicio;
    }

    public void setRescateServicio(RescateIface rescateServicio) {
        this.rescateServicio = rescateServicio;
    }

    public List<AuAnexo2Rescate> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AuAnexo2Rescate> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AuAnexo2Rescate> getLazyRegistros() {
        return lazyRegistros;
    }

    public void setLazyRegistros(LazyDataModel<AuAnexo2Rescate> lazyRegistros) {
        this.lazyRegistros = lazyRegistros;
    }

    public List<AuAnexo2RescateGestion> getRegistrosGestiones() {
        return registrosGestiones;
    }

    public void setRegistrosGestiones(List<AuAnexo2RescateGestion> registrosGestiones) {
        this.registrosGestiones = registrosGestiones;
    }

    public LazyDataModel<AuAnexo2RescateGestion> getLazyRegistrosGestiones() {
        return lazyRegistrosGestiones;
    }

    public void setLazyRegistrosGestiones(LazyDataModel<AuAnexo2RescateGestion> lazyRegistrosGestiones) {
        this.lazyRegistrosGestiones = lazyRegistrosGestiones;
    }

    public AuAnexo2Rescate getObjeto() {
        return objeto;
    }

    public void setObjeto(AuAnexo2Rescate objeto) {
        this.objeto = objeto;
    }

    public List<Maestro> getListaTiposDocumentoPersona() {
        return listaTiposDocumentoPersona;
    }

    public void setListaTiposDocumentoPersona(List<Maestro> listaTiposDocumentoPersona) {
        this.listaTiposDocumentoPersona = listaTiposDocumentoPersona;
    }

    public String getTelefonoFijoAfiliado() {
        return telefonoFijoAfiliado;
    }

    public void setTelefonoFijoAfiliado(String telefonoFijoAfiliado) {
        this.telefonoFijoAfiliado = telefonoFijoAfiliado;
    }

    public String getTelefonoMovilAfiliado() {
        return telefonoMovilAfiliado;
    }

    public void setTelefonoMovilAfiliado(String telefonoMovilAfiliado) {
        this.telefonoMovilAfiliado = telefonoMovilAfiliado;
    }

    public HashMap<Integer, Ubicacion> getHashUbicaciones() {
        return hashUbicaciones;
    }

    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashUbicaciones) {
        this.hashUbicaciones = hashUbicaciones;
    }

    public AuAnexo2RescateGestion getObjetoGestion() {
        return objetoGestion;
    }

    public void setObjetoGestion(AuAnexo2RescateGestion objetoGestion) {
        this.objetoGestion = objetoGestion;
    }

    public String getDescripcionGenerica() {
        return descripcionGenerica;
    }

    public void setDescripcionGenerica(String descripcionGenerica) {
        this.descripcionGenerica = descripcionGenerica;
    }

    public int getEstadoRescate() {
        return estadoRescate;
    }

    public void setEstadoRescate(int estadoRescate) {
        this.estadoRescate = estadoRescate;
    }

    public AuAnexo3 getObjetoAnexo3() {
        return objetoAnexo3;
    }

    public void setObjetoAnexo3(AuAnexo3 objetoAnexo3) {
        this.objetoAnexo3 = objetoAnexo3;
    }

    public AuAnexo2 getObjetoAnexo2() {
        return objetoAnexo2;
    }

    public void setObjetoAnexo2(AuAnexo2 objetoAnexo2) {
        this.objetoAnexo2 = objetoAnexo2;
    }

    public AucHospitalizacion getObjetoHospitalizacion() {
        return objetoHospitalizacion;
    }

    public void setObjetoHospitalizacion(AucHospitalizacion objetoHospitalizacion) {
        this.objetoHospitalizacion = objetoHospitalizacion;
    }

    public CntProfesionalPrestador getObjetoProfesionalPrestador() {
        return objetoProfesionalPrestador;
    }

    public void setObjetoProfesionalPrestador(CntProfesionalPrestador objetoProfesionalPrestador) {
        this.objetoProfesionalPrestador = objetoProfesionalPrestador;
    }

    public AuSolicitudAdjunto getObjectoAdjunto() {
        return objectoAdjunto;
    }

    public void setObjectoAdjunto(AuSolicitudAdjunto objectoAdjunto) {
        this.objectoAdjunto = objectoAdjunto;
    }

    public List<Maestro> getListaTiposRescateAdjuntos() {
        return listaTiposRescateAdjuntos;
    }

    public void setListaTiposRescateAdjuntos(List<Maestro> listaTiposRescateAdjuntos) {
        this.listaTiposRescateAdjuntos = listaTiposRescateAdjuntos;
    }

    public HashMap<Integer, Maestro> getHashTiposRescateAdjuntos() {
        return hashTiposRescateAdjuntos;
    }

    public void setHashTiposRescateAdjuntos(HashMap<Integer, Maestro> hashTiposRescateAdjuntos) {
        this.hashTiposRescateAdjuntos = hashTiposRescateAdjuntos;
    }

    public UploadedFile getArchivoAdjunto() {
        return archivoAdjunto;
    }

    public void setArchivoAdjunto(UploadedFile archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }

    public List<AuSolicitudAdjunto> getAuSolicitudAdjunto() {
        return auSolicitudAdjunto;
    }

    public void setAuSolicitudAdjunto(List<AuSolicitudAdjunto> auSolicitudAdjunto) {
        this.auSolicitudAdjunto = auSolicitudAdjunto;
    }

    public List<AuTipoRescate> getListadoTipoRescates() {
        return listadoTipoRescates;
    }

    public void setListadoTipoRescates(List<AuTipoRescate> listadoTipoRescates) {
        this.listadoTipoRescates = listadoTipoRescates;
    }

    public List<Maestro> getListaMaeMotivos() {
        return listaMaeMotivos;
    }

    public void setListaMaeMotivos(List<Maestro> listaMaeMotivos) {
        this.listaMaeMotivos = listaMaeMotivos;
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getRescateServicio().Accion(this);
    }

    public void refrescarGestion() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_ADICIONAL_1);
        getRescateServicio().Accion(this);
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER);
        getRescateServicio().Accion(this);
        procesoFinal();
    }

    public void gestionar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_VER);
        getRescateServicio().Accion(this);
        procesoFinal();
    }

    public void gestionarEstado() {//sin uso
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_MODIFICAR);
        getRescateServicio().Accion(this);
        if (!this.getErrores().isEmpty()) {
            generarMensajes();
            return;
        }

        procesoFinal();
    }

    public void crearGestion() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_CREAR);
        getRescateServicio().Accion(this);
        procesoFinal();
    }

    public void guardarGestion() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_GUARDAR);
        getRescateServicio().Accion(this);
        procesoFinal();
    }

    public void cancelar(int id) {
        getObjeto().setId(id);
        getObjeto().setDescripcion(null);
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_EDITAR);
        getRescateServicio().Accion(this);
        procesoFinal();
    }

    public void cancelarRescate() {
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_MODIFICAR);
        getRescateServicio().Accion(this);
        procesoFinal();
    }

    public void cerrarGestionar() {
        PrimeFaces.current().ajax().update("frmRescate");
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_VER:
                    PrimeFaces.current().ajax().update("frmVer");
                    PrimeFaces.current().ajax().update("frmAuditoriaVerRes");
                    PrimeFaces.current().executeScript("PF('dialogoVer').show()");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmRescate");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:// Gestionar
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            PrimeFaces.current().ajax().update("frmGestionar1");
                            PrimeFaces.current().ajax().update("frmAuditoriaGesRes");
                            PrimeFaces.current().executeScript("PF('dialogoGestionar').show()");
                            crearLog("Gestionar ver",getObjeto().toString());
                            break;
                        case Url.ACCION_CREAR:
                            PrimeFaces.current().ajax().update("frmGestionar2");
                            PrimeFaces.current().executeScript("PF('dialogoAgregarGestionar').show()");
                            crearLog("Gestionar crear",getObjeto().toString());
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().ajax().update("frmGestionar1");
                            PrimeFaces.current().ajax().update("frmGestionar1:tablaGestion");
                            PrimeFaces.current().executeScript("PF('dialogoAgregarGestionar').hide()");
//                            PrimeFaces.current().executeScript("PF('tablaGestion').clearFilters();");
                            crearLog("Gestionar guardar", getObjeto().toString());
                            break;
                        case Url.ACCION_MODIFICAR:
                            PrimeFaces.current().resetInputs("frmGestionar1");
                            PrimeFaces.current().ajax().update("frmGestionar1");
                            PrimeFaces.current().executeScript("PF('dialogoGestionar').hide()");
                            PrimeFaces.current().ajax().update("frmRescate");
                            crearLog("Gestionar rescate ", getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:// Cancelar
                    switch (getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            PrimeFaces.current().executeScript("PF('dialogoCancelarRescate').show()");
                            PrimeFaces.current().ajax().update("frmCancelarRescate");
                            crearLog("Cancelar abrir",getObjeto().toString());
                            break;
                        case Url.ACCION_MODIFICAR:
                            PrimeFaces.current().executeScript("PF('dialogoCancelarRescate').hide()");
                            PrimeFaces.current().ajax().update("frmRescate");
                            crearLog("Cancelar rescate", getObjeto().toString());
                            break;
                    }
                    break;
            }
        }
        generarMensajes();
    }

    public void verSolicitud() {
        switch (getObjeto().getFuenteOrigen()) {
            case AuAnexo2Rescate.FUENTE_ORIGEN_ANEXO2:
                setObjetoAnexo2(getObjeto().getAuAnexo2());
                getRescateServicio().verAnexo2(this);
                if (!super.isError()) {
                    PrimeFaces.current().executeScript("PF('dialogoVerAnexo2').show()");
                    PrimeFaces.current().ajax().update("frmAuditoriaVer");
                    PrimeFaces.current().ajax().update("frmVerAnexo2");
                }
                break;
            case AuAnexo2Rescate.FUENTE_ORIGEN_ANEXO3:
                setObjetoAnexo3(getObjeto().getAuAnexo3());
                getRescateServicio().verAnexo3(this);
                if (!super.isError()) {
                    PrimeFaces.current().executeScript("PF('dialogoVerSolicitud').show()");
                    PrimeFaces.current().ajax().update("frmAuditoriaVerSolicitud");
                    PrimeFaces.current().ajax().update("frmVerSolicitud");
                }
                break;
            case AuAnexo2Rescate.FUENTE_ORIGEN_HOSPITALIZACION:
                setObjetoHospitalizacion(getObjeto().getAucHospitalizacion());
                getRescateServicio().verHospitalizacion(this);
                if (!super.isError()) {
                    PrimeFaces.current().ajax().update("frmVerHospitalizacion");
                    PrimeFaces.current().ajax().update("frmAuditoriaVerHsp");
                    PrimeFaces.current().executeScript("PF('dialogoVerHospitalizacion').show()");
                }
                break;
        }
        generarMensajes();
    }

    public String obtenerDepartamento(int id) {
        try {
            int idPadre = hashUbicaciones.get(id).getUbicacionPadre().getId();
            return hashUbicaciones.get(idPadre).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public String obtenerMunicipio(int id) {
        try {
            return hashUbicaciones.get(id).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public String obtenerMunicipioDepartamento(int id) {
        try {
            String municipio = obtenerMunicipio(id);
            String departamento = obtenerDepartamento(id);
            return municipio + " - " + departamento;
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public void verBitacoras(AuAnexo3Item item) {
        getObjetoAnexo3().setObjetoTecnologia(item);
        PrimeFaces.current().executeScript("PF('dialogoVerBitacorass').show()");
        PrimeFaces.current().ajax().update("frmVerBitacoras");
        generarMensajes();
    }

    public String obtenerValorBoolean(boolean bool) {

        return bool == true ? AuConstantes.TEXTO_SI : AuConstantes.TEXTO_NO;

    }

    public boolean validarGestionar(int estado) {
        return AuAnexo2Rescate.ESTADO_PENDIENTE == estado
                || AuAnexo2Rescate.ESTADO_GESTION == estado;
    }

    public boolean validarEstadoAnuladoAnexo2(int codigo) {
        return AuAnexo2.ESTADO_ANULADA == codigo;
    }

    public Date obtenerFechaActual() {

        Date fechaActual = new Date();

        return fechaActual;
    }

    public void mostrarMensaje(String desc) {
        setDescripcionGenerica(desc);
        PrimeFaces.current().resetInputs("frmObservacion");
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }

    public void salirGestion() {
        setEstadoRescate(getObjeto().getEstado());
    }

    public boolean validarAgregarGestion() {
        return objeto.getEstado() != AuAnexo2Rescate.ESTADO_RECHAZADO && objeto.getEstado() != AuAnexo2Rescate.ESTADO_RESCATADO;
    }

    public void mostrarAfiliadoGeneral() {
        PrimeFaces.current().resetInputs("frmAfiliadoGeneral");
        PrimeFaces.current().ajax().update("frmAfiliadoGeneral");
        PrimeFaces.current().executeScript("PF('dlgInfoAfiliado').show();");
    }

    public String obtenerTriage(int triage) {
        String ruta = "";
        switch (triage) {
            case 0:
                ruta = "../resources/images/triage0.png";
                break;
            case 1:
                ruta = "../resources/images/triage1.png";
                break;
            case 2:
                ruta = "../resources/images/triage2.png";
                break;
            case 3:
                ruta = "../resources/images/triage3.png";
                break;
            case 4:
                ruta = "../resources/images/triage4.png";
                break;
            case 5:
                ruta = "../resources/images/triage5.png";
                break;
            default:
                ruta = "";
                break;
        }

        return ruta;
    }

    public void descargarAdjuntoAnexo(AuSolicitudAdjunto adjunto) {
        descargarAdjunto(adjunto.getRuta(), adjunto.getArchivo(), adjunto.getNombre());
        procesoFinal();
    }

    public void descargarAdjunto(String ruta, String archivo, String nombre) {
        String rutaCompleta = ruta + archivo;
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
            String attachmentName = "attachment; filename=\"" + nombre + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".pdf")) {
                ec.setResponseContentType("application/pdf");
            } else if (ext.equalsIgnoreCase(".jpg")) {
                ec.setResponseContentType("image/jpg");
            } else if (ext.equalsIgnoreCase(".jpeg")) {
                ec.setResponseContentType("image/jpeg");
            } else if (ext.equalsIgnoreCase(".png")) {
                ec.setResponseContentType("image/png");
            } else if (ext.equalsIgnoreCase(".doc")) {
                ec.setResponseContentType("application/msword");
            } else {
                throw new Exception();
            }
            OutputStream output = ec.getResponseOutputStream();
            output.write(exportContent);
            output.close();
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        }

    }
    
    public void abrirAdjuntos() {   
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_CREAR_ADJUNTO_RESCATE);
        getRescateServicio().Accion(this);
        if(!super.isError()){
            PrimeFaces.current().resetInputs("frmAdjunto");
            PrimeFaces.current().ajax().update("frmAdjunto");
            PrimeFaces.current().ajax().update("frmAuditoriaAdjuntoProgramas");
            PrimeFaces.current().executeScript("PF('dialogoAdjuntos').show()");
        }
        generarMensajes();
    }
    
    public void guardarAdjuntoPrograma() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_GUARDAR_ADJUNTO_RESCATE);
        getRescateServicio().Accion(this);
        setAuSolicitudAdjunto(new ArrayList<>());
        PrimeFaces.current().ajax().update("frmAdjunto");
        PrimeFaces.current().ajax().update("frmGestionar1:pnlAdjuntosGestion");
        procesoFinal();
    }
    
    public void cargarArchivoDocumento(FileUploadEvent event) throws IOException {
        archivoAdjunto = event.getFile();
        try {
            if (objectoAdjunto.getMaeTipoArchivoId() == null) {
                addError("Tipo: Error de validación: se necesita un valor.");
                generarMensajes();
                return;
            }
            AuSolicitudAdjunto borrarObj = new AuSolicitudAdjunto();
            for (AuSolicitudAdjunto peAdjunto : auSolicitudAdjunto) {
                if (peAdjunto.getMaeTipoArchivoId().equals(objectoAdjunto.getMaeTipoArchivoId())) {
                    borrarObj = peAdjunto;
                    break;
                }
            }
            auSolicitudAdjunto.remove(borrarObj);
            objectoAdjunto.setAdjuntoStream(archivoAdjunto.getInputStream());
            String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
            int indiceExtension = nombreAdjunto.lastIndexOf(".");
            objectoAdjunto.setExtension(nombreAdjunto.substring(indiceExtension, nombreAdjunto.length()));
            objectoAdjunto.setNombre(nombreAdjunto);
            auSolicitudAdjunto.add(objectoAdjunto);
            objectoAdjunto = new AuSolicitudAdjunto();
            PrimeFaces.current().ajax().update("frmAdjunto");
        } catch (IOException ex) {
            this.addError(ex.toString());
            generarMensajes();
        }
    }
    
    public String getTipoAdjuntoRecursiva(int id) {
        return getHashTiposRescateAdjuntos().get(id).getNombre();
    }
    
    public void borrarAdjuntoPrograma(String nombre) {
        AuSolicitudAdjunto borraObjecto = new AuSolicitudAdjunto();
        for (AuSolicitudAdjunto peAdjunto : auSolicitudAdjunto) {
            if (peAdjunto.getNombre().equals(nombre)) {
                borraObjecto = peAdjunto;
                break;
            }
        }
        auSolicitudAdjunto.remove(borraObjecto);
        PrimeFaces.current().ajax().update("frmAdjunto");
    }
    
    public String obtenerDiasVencimiento(AuAnexo2Rescate anexo2Rescate) {
        if (anexo2Rescate.getEstado() == AuAnexo2Rescate.ESTADO_PENDIENTE || anexo2Rescate.getEstado() == AuAnexo2Rescate.ESTADO_GESTION) {
            
            LocalDateTime fechaCreacion = Instant.ofEpochMilli(anexo2Rescate.getFechaHoraCrea().getTime())
                                       .atZone(ZoneId.systemDefault())
                                       .toLocalDateTime();
            LocalDateTime fechaActual = LocalDateTime.now();
            long dias = ChronoUnit.DAYS.between(fechaCreacion, fechaActual);
            return String.valueOf(dias);
            
        }
        return null;
    }
    
    public String obtenerMotivo(List<AuAnexo2RescateGestion> listadoRescateGestion) {
        if (listadoRescateGestion == null || listadoRescateGestion.isEmpty()) {
            return null;
        }
        return listadoRescateGestion.stream()
                .filter(g -> g.getMaeMotivoRescateValor() != null)
                .map(g -> g.getMaeMotivoRescateValor())
                .findFirst()
                .orElse(null);
    }
}
