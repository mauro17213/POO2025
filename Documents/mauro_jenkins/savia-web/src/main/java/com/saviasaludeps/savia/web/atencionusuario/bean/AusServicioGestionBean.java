/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.atencionusuario.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.atencionusuario.AusAdjunto;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCasoServicio;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersona;
import com.saviasaludeps.savia.dominio.atencionusuario.AusSeguimiento;
import com.saviasaludeps.savia.dominio.atencionusuario.AusServicioGestionHistorico;
import com.saviasaludeps.savia.web.atencionusuario.servicio.AusServicioGestionServicioIface;
import com.saviasaludeps.savia.web.atencionusuario.utilities.PropAtencionUsuario;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
//import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import javax.faces.context.ExternalContext;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author pavacca
 */
@ManagedBean
@ViewScoped
public class AusServicioGestionBean extends Url {

    private AusServicioGestionServicioIface ausServicioGestionServicio;
    private AusCasoServicio objeto;
    private List<AusCasoServicio> registros;
    private LazyDataModel<AusCasoServicio> lazyServicioGestion;

    public static final String SESION_ADJUNTO_SERVICIOS = "ajuntoServicios";
    private int contadorArchivos = 0;

    private List<Maestro> listaEstadoServicio;
    private List<Maestro> listaEstadoServicioEstadoAsignado;
    private HashMap<Integer, Maestro> hashEstadoServicio;

    private List<Maestro> listaTiposDocumento;
    private HashMap<Integer, Maestro> hashTiposDocumento;

    private List<Maestro> listaTipoEspecialidadServicio;
    private HashMap<Integer, Maestro> hashTipoEspecialidadServicio;

    private List<Usuario> listaUsuarios;
    private HashMap<Integer, Usuario> hashUsuarios;

    private List<Maestro> listaEstadosCaso;
    private HashMap<Integer, Maestro> HashEstadosCaso;

    private List<Maestro> listaEstadoSeguimiento;
    private HashMap<Integer, Maestro> hashEstadoSeguimiento;

    private List<Ubicacion> ubicaciones;
    private HashMap<Integer, Ubicacion> ubicacionesRecursiva;

    private List<Maestro> listaEstadosServicioGestion;
    private HashMap<String, Maestro> hashEstadosServicioGestionValor;
    private HashMap<Integer, Maestro> hashEstadosServicioGestion;

    private List<Maestro> listaEntesControl;

    private List<Maestro> listaTipoMotivoServicio;
    private HashMap<Integer, Maestro> hashTipoServicioMotivo;

    private List<Maestro> listaSexo;
    private HashMap<Integer, Maestro> hashSexo;

    private List<Maestro> listaTipoEstadoPersona;
    private HashMap<Integer, Maestro> hashTipoEstadosPersona;

    private List<AusPersona> listaPersonasHistorial;
    private List<AusSeguimiento> seguimientoRadicado;

    private List<Maestro> listaTipoSolicitudOrigen;
    private HashMap<Integer, Maestro> hashTipoSolicitudOrigen;

    private List<Maestro> listaTipoSolicitudRiesgoVida;
    private HashMap<Integer, Maestro> hashTipoSolicitudRiesgoVida;

    public AusSeguimiento seguimientoProcesar;

    private int idCaso;
    private int cantidadVencidos;
    private boolean listarVencidos;
    private String descripcion;
    private String colorVencimiento;

    public static final char ACCION_MODIFICAR_CIERRE_AUTOMATICO = 'a';

    private String urlDescargaAdjuntosCaso;
    private String mensajeGeneral;

    public AusServicioGestionBean() {

        vaciarSesionAdjuntos(SESION_ADJUNTO_SERVICIOS);
        Modulo mod = super.validarModulo(Modulo.ID_AUS_SERVICIOS);
        this.objeto = new AusCasoServicio();
        this.seguimientoProcesar = new AusSeguimiento();
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        //super.getParamConsulta().setParametroConsulta1(super.getConexion().getUsuario().getId());
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyServicioGestion = new LazyDataModel<AusCasoServicio>() {

                private List<AusCasoServicio> serviciosGestion;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AusCasoServicio> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    serviciosGestion = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    //agrega registros hash datos

                    return serviciosGestion;
                }

                @Override
                public String getRowKey(AusCasoServicio externa) {
                    return externa.getId().toString();
                }

                @Override
                public AusCasoServicio getRowData(String personaId) {
                    Integer id = Integer.valueOf(personaId);
                    for (AusCasoServicio servicio : serviciosGestion) {
                        if (id.equals(servicio.getId())) {
                            return servicio;
                        }
                    }
                    return null;
                }
            };
        }
    }

    @PostConstruct
    public void postConstruct() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        getAusServicioGestionServicio().cargaInial(this);
    }

    public AusCasoServicio getObjeto() {
        return objeto;
    }

    public void setObjeto(AusCasoServicio objeto) {
        this.objeto = objeto;
    }

    public List<AusCasoServicio> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AusCasoServicio> registros) {
        this.registros = registros;
    }

    public AusServicioGestionServicioIface getAusServicioGestionServicio() {
        return ausServicioGestionServicio;
    }

    public void setAusServicioGestionServicio(AusServicioGestionServicioIface ausServicioGestionServicio) {
        this.ausServicioGestionServicio = ausServicioGestionServicio;
    }

    public LazyDataModel<AusCasoServicio> getLazyServicioGestion() {
        return lazyServicioGestion;
    }

    public void setLazyServicioGestion(LazyDataModel<AusCasoServicio> lazyServicioGestion) {
        this.lazyServicioGestion = lazyServicioGestion;
    }

    public List<Maestro> getListaEstadosServicioGestion() {
        return listaEstadosServicioGestion;
    }

    public void setListaEstadosServicioGestion(List<Maestro> listaEstadosServicioGestion) {
        this.listaEstadosServicioGestion = listaEstadosServicioGestion;
    }

    public List<Maestro> getListaEstadoServicio() {
        return listaEstadoServicio;
    }

    public void setListaEstadoServicio(List<Maestro> listaEstadoServicio) {
        this.listaEstadoServicio = listaEstadoServicio;
    }

    public List<Maestro> getListaEstadoServicioEstadoAsignado() {
        if (listaEstadoServicioEstadoAsignado == null) {
            listaEstadoServicioEstadoAsignado = new ArrayList<>();
        }
        return listaEstadoServicioEstadoAsignado;
    }

    public void setListaEstadoServicioEstadoAsignado(List<Maestro> listaEstadoServicioEstadoAsignado) {
        this.listaEstadoServicioEstadoAsignado = listaEstadoServicioEstadoAsignado;
    }

    public String getMensajeGeneral() {
        return mensajeGeneral;
    }

    public void setMensajeGeneral(String mensajeGeneral) {
        this.mensajeGeneral = mensajeGeneral;
    }

    public HashMap<Integer, Maestro> getHashEstadoServicio() {
        return hashEstadoServicio;
    }

    public void setHashEstadoServicio(HashMap<Integer, Maestro> hashEstadoServicio) {
        this.hashEstadoServicio = hashEstadoServicio;
    }

    public AusSeguimiento getSeguimientoProcesar() {
        return seguimientoProcesar;
    }

    public void setSeguimientoProcesar(AusSeguimiento seguimientoProcesar) {
        this.seguimientoProcesar = seguimientoProcesar;
    }

    public List<AusSeguimiento> getSeguimientoRadicado() {
        return seguimientoRadicado;
    }

    public void setSeguimientoRadicado(List<AusSeguimiento> seguimientoRadicado) {
        this.seguimientoRadicado = seguimientoRadicado;
    }

    public List<Maestro> getListaTipoSolicitudOrigen() {
        return listaTipoSolicitudOrigen;
    }

    public void setListaTipoSolicitudOrigen(List<Maestro> listaTipoSolicitudOrigen) {
        this.listaTipoSolicitudOrigen = listaTipoSolicitudOrigen;
    }

    public HashMap<Integer, Maestro> getHashTipoSolicitudOrigen() {
        return hashTipoSolicitudOrigen;
    }

    public void setHashTipoSolicitudOrigen(HashMap<Integer, Maestro> hashTipoSolicitudOrigen) {
        this.hashTipoSolicitudOrigen = hashTipoSolicitudOrigen;
    }

    public List<Maestro> getListaTipoSolicitudRiesgoVida() {
        return listaTipoSolicitudRiesgoVida;
    }

    public void setListaTipoSolicitudRiesgoVida(List<Maestro> listaTipoSolicitudRiesgoVida) {
        this.listaTipoSolicitudRiesgoVida = listaTipoSolicitudRiesgoVida;
    }

    public HashMap<Integer, Maestro> getHashTipoSolicitudRiesgoVida() {
        return hashTipoSolicitudRiesgoVida;
    }

    public void setHashTipoSolicitudRiesgoVida(HashMap<Integer, Maestro> hashTipoSolicitudRiesgoVida) {
        this.hashTipoSolicitudRiesgoVida = hashTipoSolicitudRiesgoVida;
    }

    public List<Maestro> getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    public void setListaTiposDocumento(List<Maestro> listaTiposDocumento) {
        this.listaTiposDocumento = listaTiposDocumento;
    }

    public HashMap<Integer, Maestro> getHashTiposDocumento() {
        return hashTiposDocumento;
    }

    public void setHashTiposDocumento(HashMap<Integer, Maestro> hashTiposDocumento) {
        this.hashTiposDocumento = hashTiposDocumento;
    }

    public List<AusPersona> getListaPersonasHistorial() {
        return listaPersonasHistorial;
    }

    public List<Maestro> getListaSexo() {
        return listaSexo;
    }

    public void setListaSexo(List<Maestro> listaSexo) {
        this.listaSexo = listaSexo;
    }

    public HashMap<Integer, Maestro> getHashSexo() {
        return hashSexo;
    }

    public void setHashSexo(HashMap<Integer, Maestro> hashSexo) {
        this.hashSexo = hashSexo;
    }

    public List<Maestro> getListaTipoEstadoPersona() {
        return listaTipoEstadoPersona;
    }

    public void setListaTipoEstadoPersona(List<Maestro> listaTipoEstadoPersona) {
        this.listaTipoEstadoPersona = listaTipoEstadoPersona;
    }

    public HashMap<Integer, Maestro> getHashTipoEstadosPersona() {
        return hashTipoEstadosPersona;
    }

    public void setHashTipoEstadosPersona(HashMap<Integer, Maestro> hashTipoEstadosPersona) {
        this.hashTipoEstadosPersona = hashTipoEstadosPersona;
    }

    public void setListaPersonasHistorial(List<AusPersona> listaPersonasHistorial) {
        this.listaPersonasHistorial = listaPersonasHistorial;
    }

    public List<Maestro> getListaTipoEspecialidadServicio() {
        return listaTipoEspecialidadServicio;
    }

    public void setListaTipoEspecialidadServicio(List<Maestro> listaTipoEspecialidadServicio) {
        this.listaTipoEspecialidadServicio = listaTipoEspecialidadServicio;
    }

    public HashMap<Integer, Maestro> getHashTipoEspecialidadServicio() {
        return hashTipoEspecialidadServicio;
    }

    public void setHashTipoEspecialidadServicio(HashMap<Integer, Maestro> hashTipoEspecialidadServicio) {
        this.hashTipoEspecialidadServicio = hashTipoEspecialidadServicio;
    }

    public List<Maestro> getListaTipoMotivoServicio() {
        return listaTipoMotivoServicio;
    }

    public void setListaTipoMotivoServicio(List<Maestro> listaTipoMotivoServicio) {
        this.listaTipoMotivoServicio = listaTipoMotivoServicio;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public HashMap<Integer, Usuario> getHashUsuarios() {
        return hashUsuarios;
    }

    public void setHashUsuarios(HashMap<Integer, Usuario> hashUsuarios) {
        this.hashUsuarios = hashUsuarios;
    }

    public HashMap<Integer, Ubicacion> getUbicacionesRecursiva() {
        if (ubicacionesRecursiva == null) {
            ubicacionesRecursiva = new HashMap<>();
        }
        return ubicacionesRecursiva;
    }

    public void setUbicacionesRecursiva(HashMap<Integer, Ubicacion> ubicacionesRecursiva) {
        this.ubicacionesRecursiva = ubicacionesRecursiva;
    }

    public List<Ubicacion> getListaUbicacion() throws Exception {
        return UbicacionSingle.getInstance().getListaMunicipios();
    }

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public String getDescripcion() {
        if (descripcion == null) {
            descripcion = "Caso cerrado automaticamente por el sistema";
        }
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void refrescar() {
        if (!getListarVencidos()) {
            super.setAccion(Url.ACCION_LISTAR);
            getAusServicioGestionServicio().Accion(this);
            procesoFinal();
        } else {
            setListarVencidos(false);
        }
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAusServicioGestionServicio().Accion(this);
        procesoFinal();
    }
    
    public void borrar(int _id) {
        setObjeto(new AusCasoServicio());
        getObjeto().setId(_id);
        super.setAccion(Url.ACCION_BORRAR);
        getAusServicioGestionServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmServicioGestion");
        PrimeFaces.current().ajax().update("frmServicioGestion");
        procesoFinal();
        //PrimeFaces.current().executeScript("PF('dialogoRechazarCaso').show()");
    }
    
    public void verGestion(int _id) {
        List<AusSeguimiento> seguimientoRadi = new ArrayList<>();
        getObjeto().setId(_id);
        vaciarSesionAdjuntos(SESION_ADJUNTO_SERVICIOS);
        super.setAccion(ACCION_VER);
        getAusServicioGestionServicio().Accion(this);
        if (getObjeto().getCasosId() != null && getObjeto().getCasosId().getSeguimientosList() != null
                && !getObjeto().getCasosId().getSeguimientosList().isEmpty()) {
            seguimientoRadi.add(getObjeto().getCasosId().getSeguimientosList().get(0));
            setSeguimientoRadicado(seguimientoRadi);
        }

        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmGestion");
            PrimeFaces.current().executeScript("PF('dialogoGestion').show()");
        }
        procesoFinal();
    }

    public void modificarGestion() {
        Maestro estadoServicio = null;
        boolean validarCamposVsEstado = true;
        int idEstadoCerrado = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO));
        boolean validar = false;
        if (objeto.getMaeEstadoId() == idEstadoCerrado) {
            validar = true;
            setIdCaso(this.objeto.getCasosId().getId());
            setDescripcion(this.objeto.getDescripcion());
            objeto.setFechaCumplimiento(new Date());
        }
        //2025-03-21 jyperez adicionamos nuevas validaciones con respecto a lista de adjuntos y asignación de cita
        estadoServicio = this.hashEstadosServicioGestion.get(objeto.getMaeEstadoId());
        if (estadoServicio != null) {
            if (objeto.getTipoTecnologia() == null) {
                if (estadoServicio.getValor().equals(AusCasoServicio.ESTADO_CASO_SERVICIO_ESTUDIO) ||
                        estadoServicio.getValor().equals(AusCasoServicio.ESTADO_CASO_SERVICIO_RECHAZADO)) {
                    if(!validarCampoTexto(objeto.getDescripcion())) {
                        super.addError("Es necesario agregar una Observación a este servicio.");
                        validarCamposVsEstado = false;
                    }
                }
            } else if ((objeto.getTipoTecnologia() == AusCasoServicio.TIPO_TECNOLOGIA_TECNOLOGIA) && objeto.isAsignacionCita()) {
                // validamos para el estado Cerrado
                if (estadoServicio.getValor().equals(AusCasoServicio.ESTADO_CASO_SERVICIO_CERRADO)) {
                    if (objeto.getFechaAplica() == null || !validarCampoTexto(objeto.getDescripcion()) ) {
                        super.addError("Para cerrar este servicio diligenciar el campo de fecha cita y la observación.");
                        validarCamposVsEstado = false;
                    }
                } else if (estadoServicio.getValor().equals(AusCasoServicio.ESTADO_CASO_SERVICIO_ESTUDIO) ||
                        estadoServicio.getValor().equals(AusCasoServicio.ESTADO_CASO_SERVICIO_RECHAZADO)) {
                    if(!validarCampoTexto(objeto.getDescripcion())) {
                        super.addError("Es necesario agregar una Observación a este servicio.");
                        validarCamposVsEstado = false;
                    }
                }
                // validamos las tecnologias diferentes ( medicamentos e insumos)
            } else if (objeto.getTipoTecnologia() != AusCasoServicio.TIPO_TECNOLOGIA_TECNOLOGIA) {
                if (estadoServicio.getValor().equals(AusCasoServicio.ESTADO_CASO_SERVICIO_CERRADO)) {
                    if (objeto.getAdjuntosList() != null && objeto.getAdjuntosList().isEmpty()) {
                        super.addError("Para cerrar este servicio es necesario adjuntar un soporte.");
                        validarCamposVsEstado = false;
                    }
                //validamos los estados diferentes a cerrado (estudio o rechazado)
                } else if (estadoServicio.getValor().equals(AusCasoServicio.ESTADO_CASO_SERVICIO_ESTUDIO) ||
                        estadoServicio.getValor().equals(AusCasoServicio.ESTADO_CASO_SERVICIO_RECHAZADO)) {
                    if(!validarCampoTexto(objeto.getDescripcion())) {
                        super.addError("Es necesario agregar una Observación a este servicio.");
                        validarCamposVsEstado = false;
                    }
                }
            }
        }
        
        if (validarCamposVsEstado) {
            //super.setAccion(ACCION_GUARDAR);
            super.setAccion(Url.ACCION_MODIFICAR);
            super.setDoAccion(Url.ACCION_MODIFICAR);
            getAusServicioGestionServicio().Accion(this);
        }
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoGestion').hide()");
            if (validar) {
                cerrarCasoAutomaticamente(getIdCaso());
            }
        }
        PrimeFaces.current().ajax().update("frmServicioGestion");
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_MODIFICAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_BORRAR:
                    crearLog(getObjeto().toString());
                case Url.ACCION_LISTAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_CREAR:
                case Url.ACCION_VER:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
            }
        }
        generarMensajes();
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        try {
            String file = FilenameUtils.getName(event.getFile().getFileName());
            int i = file.lastIndexOf(".");
            String ext = file.substring(i, file.length());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String nombre = "adjunto_servicio_";
            String filename = nombre + sdf.format(new Date()) + "_" + (++contadorArchivos) + ext;
            InputStream input = event.getFile().getInputStream();

            String ruta = this.getUrlDescargaAdjuntosCaso();
            if (ruta == null) {
                /*throw new IOException("Configura el atributo " +
                         Parametrizacion.PARAMETRO_RUTA_DESCARGAS_CASOS +" en tabla parametros");*/
                throw new IOException("Configura el atributo "
                        + PropApl.getInstance().get(PropApl.AUS_RUTA_CARGA) + " en tabla parametros");
            }

            OutputStream output = new FileOutputStream(new File(ruta, filename));

            IOUtils.copy(input, output);
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);

            List<AusAdjunto> listaAnexos = obtenerSesionAdjuntos(SESION_ADJUNTO_SERVICIOS);
            if (listaAnexos == null) {
                listaAnexos = new ArrayList<>();
                this.getObjeto().getAdjuntosList().addAll(listaAnexos);
            }

            //agraegar el nuevo anexo a la lista de anexos de tutela
            AusAdjunto adjunto = new AusAdjunto();
            adjunto.setId(null);
            adjunto.setArchivo(file);
            adjunto.setNombre(filename);
            adjunto.setRuta(ruta);
            this.auditoriaGuardar(adjunto);
            adjunto.setPos(this.getObjeto().getAdjuntosList().size());

            this.getObjeto().getAdjuntosList().add(adjunto);
            llenarSesionAdjuntos(SESION_ADJUNTO_SERVICIOS, listaAnexos);

            //RequestContext.getCurrentInstance().reset("frmGestion:tablaAnexosServicios");
            //RequestContext.getCurrentInstance().update("frmGestion:tablaAnexosServicios");
            PrimeFaces.current().ajax().update("frmGestion:tablaAnexosServicios");
        } catch (IOException e) {
            this.addError("Error al cargar archivo adjunto : " + e.getMessage());
            generarMensajes();
        }
    }
    
    /**
     * función para validar que el campo de texto tenga datos escritos ( no nulo, no vacio, no espacios)
     * @param texto
     * @return 
     */
    private boolean validarCampoTexto(String texto) {
        boolean valido = true;
        if (texto != null) {
            if (texto.isBlank() || texto.isEmpty()) {
                valido = false;
            }
        } else {
            valido = false;
        }
        return valido;
    }

    private void vaciarSesionAdjuntos(String nombreSession) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(nombreSession, null);
    }

    private void llenarSesionAdjuntos(String nombreSession, List<AusAdjunto> listaAnexos) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(nombreSession, listaAnexos);
    }

    private List<AusAdjunto> obtenerSesionAdjuntos(String nombreSesion) {
        List<AusAdjunto> listaAnexos = (List<AusAdjunto>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(nombreSesion);
        return listaAnexos;
    }

    public int getContadorArchivos() {
        return contadorArchivos;
    }

    public void setContadorArchivos(int contadorArchivos) {
        this.contadorArchivos = contadorArchivos;
    }

    public String getUrlDescargaAdjuntosCaso() {
        return urlDescargaAdjuntosCaso;
    }

    public void setUrlDescargaAdjuntosCaso(String urlDescargaAdjuntosCaso) {
        this.urlDescargaAdjuntosCaso = urlDescargaAdjuntosCaso;
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public void descargarAnexo(AusAdjunto adjunto) {
        String rutaCompleta = adjunto.getRuta() + File.separator + adjunto.getNombre();
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
            String attachmentName = "attachment; filename=\"" + adjunto.getNombre() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".pdf")) {
                ec.setResponseContentType("application/pdf");
            } else if (ext.equalsIgnoreCase(".jpg")) {
                ec.setResponseContentType("image/jpg");
            } else {
                throw new Exception();
            }
            OutputStream output = ec.getResponseOutputStream();
            output.write(exportContent);
            output.close();
            fc.responseComplete();
        } catch (Exception e) {

        }
    }

    public String getTipoDocumentoStr(int id) {
        try {
            return hashTiposDocumento.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public String getTipoEstadoServicioStr(int id) {
        try {
            return AusCasoServicio.getEstadoStr(id);
        } catch (Exception e) {
            return "";
        }
    }

    public String getTipoEspecialidadServiciosStr(int id) {
        try {
            return hashTipoEspecialidadServicio.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public String getEstadoServicioStr(int idEstado) {
        return AusCasoServicio.getEstadoStr(idEstado);
    }

    public void borrarAdjuntoServicioMemoria(int pos) {
        try {
            delAdjuntoServicioMemoria(pos);
        } catch (Exception e) {
            super.addError("No es posible borrar Adjunto");
        }
        if (!super.isError()) {
            //RequestContext.getCurrentInstance().update("frmGestion:tablaAnexosServicios");
            //RequestContext.getCurrentInstance().update("frmGestion:tablaAnexosServicios");
            PrimeFaces.current().ajax().update("frmGestion:tablaAnexosServicios");
        }
    }

    public void delAdjuntoServicioMemoria(int pos) {
        List<AusAdjunto> lista = new ArrayList();
        int i = 0, j = 0;
        for (AusAdjunto det : this.getObjeto().getAdjuntosList()) {
            if (j != pos) {
                det.setPos(i);
                lista.add(det);
                i++;
            }
            j++;
        }
        this.getObjeto().setAdjuntosList(lista);
    }

    public void cerrarCasoAutomaticamente(int idCaso) {
        super.setAccion(Url.ACCION_MODIFICAR);
        super.setDoAccion(ACCION_MODIFICAR_CIERRE_AUTOMATICO);
        getAusServicioGestionServicio().Accion(this);
    }

    public int getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(int idCaso) {
        this.idCaso = idCaso;
    }

    public List<Maestro> getListaEntesControl() {
        return listaEntesControl;
    }

    public void setListaEntesControl(List<Maestro> listaEntesControl) {
        this.listaEntesControl = listaEntesControl;
    }

    public List<Maestro> getListaEstadosCaso() {
        return listaEstadosCaso;
    }

    public void setListaEstadosCaso(List<Maestro> listaEstadosCaso) {
        this.listaEstadosCaso = listaEstadosCaso;
    }

    public List<Maestro> getListaEstadoSeguimiento() {
        return listaEstadoSeguimiento;
    }

    public void setListaEstadoSeguimiento(List<Maestro> listaEstadoSeguimiento) {
        this.listaEstadoSeguimiento = listaEstadoSeguimiento;
    }

    public HashMap<Integer, Maestro> getHashEstadosCaso() {
        return HashEstadosCaso;
    }

    public void setHashEstadosCaso(HashMap<Integer, Maestro> HashEstadosCaso) {
        this.HashEstadosCaso = HashEstadosCaso;
    }

    public HashMap<Integer, Maestro> getHashEstadoSeguimiento() {
        return hashEstadoSeguimiento;
    }

    public void setHashEstadoSeguimiento(HashMap<Integer, Maestro> hashEstadoSeguimiento) {
        this.hashEstadoSeguimiento = hashEstadoSeguimiento;
    }

    public int getCantidadVencidos() {
        return cantidadVencidos;
    }

    public void setCantidadVencidos(int cantidadVencidos) {
        this.cantidadVencidos = cantidadVencidos;
    }

    public boolean getListarVencidos() {
        return listarVencidos;
    }

    public void setListarVencidos(boolean listarVencidos) {
        this.listarVencidos = listarVencidos;
    }

    public int obtenerIdEnteControlCaso(String enteControl) {
        int id = 0;
        for (Maestro maestro : getListaEntesControl()) {
            if (maestro.getNombre().equals(enteControl)) {
                id = maestro.getId();
                break;
            }
        }
        return id;
    }

    public int obtenerIdEstadoCaso(String estado) {
        int id = 0;
        for (Maestro maestro : getListaEstadosCaso()) {
            if (maestro.getNombre().equals(estado)) {
                id = maestro.getId();
                break;
            }
        }
        return id;
    }

    public int obtenerIdEstadoSeguimiento(String estado) {
        int id = 0;
        for (Maestro maestro : getListaEstadoSeguimiento()) {
            if (maestro.getNombre().equals(estado)) {
                id = maestro.getId();
                break;
            }
        }
        return id;
    }

    public void contarVencidos() {

        //this.getParamConsulta().getFiltros().put("estadoMenor", this.getObjeto().ESTADO_RESUELTO);
        this.getParamConsulta().getFiltros().put("estadoMenor", Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_RESUELTO)));
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.getParamConsulta().getFiltros().put("fechaVencimiento", formato.format(new Date()));
        super.setAccion(ACCION_ADICIONAL_3);
        getAusServicioGestionServicio().Accion(this);
        //RequestContext.getCurrentInstance().update("frmServiciosVencidos");
        //RequestContext.getCurrentInstance().execute("PF('dialogoServicioVencidos').show()");
        PrimeFaces.current().ajax().update("frmServiciosVencidos");
        PrimeFaces.current().executeScript("PF('dialogoServicioVencidos').show()");
    }

    public void listarVencidos() {
        //this.getParamConsulta().getFiltros().put("estadoMenor", this.getObjeto().ESTADO_RESUELTO);
        this.getParamConsulta().getFiltros().put("estadoMenor", Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_RESUELTO)));
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.getParamConsulta().getFiltros().put("fechaVencimiento", formato.format(new Date()));
        super.setAccion(Url.ACCION_LISTAR);
        getAusServicioGestionServicio().Accion(this);
        setListarVencidos(true);
        //RequestContext.getCurrentInstance().execute("PF('dialogoServicioVencidos').hide()");
        //RequestContext.getCurrentInstance().update("frmServicioGestion");
        PrimeFaces.current().executeScript("PF('dialogoServicioVencidos').hide()");
        PrimeFaces.current().ajax().update("frmServicioGestion");
    }

    public HashMap<Integer, Maestro> getHashTipoServicioMotivo() {
        return hashTipoServicioMotivo;
    }

    public void setHashTipoServicioMotivo(HashMap<Integer, Maestro> hashTipoServicioMotivo) {
        this.hashTipoServicioMotivo = hashTipoServicioMotivo;
    }

    public String getTipoServicioMotivo(int id) {
        try {
            return hashTipoServicioMotivo.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * *********************************
     *****PROCESOS SOBRE UBICACIONES
     *
     ***** **********************************
     * @param id
     * @return
     */
    public String getUbicacionRecursiva(int id) {
        String ubicacionStr = "";
        if (!ubicacionesRecursiva.isEmpty() && ubicacionesRecursiva != null) {
            Ubicacion _municipio = ubicacionesRecursiva.get(id);
            if (_municipio != null && _municipio.getUbicacionPadre() != null) {
                Ubicacion _departamento = _municipio.getUbicacionPadre();
                if (_departamento.getUbicacionPadre() != null) {
                    Ubicacion _pais = _departamento.getUbicacionPadre();
                    ubicacionStr = _pais.getNombre();
                }
                ubicacionStr = _departamento.getNombre() + " - " + ubicacionStr;
                ubicacionStr = _municipio.getNombre() + " - " + ubicacionStr;
            }
        }

        return ubicacionStr;
    }

    public String getColorVencimiento(AusCasoServicio bean) {

        if (bean != null) {
            int dias = getDiasVencimiento(bean);

            if (bean.getFechaCumplimiento() == null) {
                if (dias > 0) {
                    colorVencimiento = "red";
                } else {
                    colorVencimiento = "yellow";
                }
            } else {
                colorVencimiento = "green";
            }
        }
        PrimeFaces.current().ajax().update("frmServicioGestion");
        return colorVencimiento;
    }

    public void setColorVencimiento(String colorVencimiento) {
        this.colorVencimiento = colorVencimiento;
    }

    public int getDiasVencimiento(AusCasoServicio ausCasoServicio) {
        int diasVencidos = 0;

        if (ausCasoServicio.getFechaCumplimiento() == null) {
            if (ausCasoServicio.getServicioGestionesList() != null
                    && !ausCasoServicio.getServicioGestionesList().isEmpty() && ausCasoServicio.getServicioGestionesList().size() > 0) {
                AusServicioGestionHistorico ausServicioGestionHistorico = ausCasoServicio.getServicioGestionesList().get(ausCasoServicio.getServicioGestionesList().size() - 1);
                if (ausServicioGestionHistorico.getMaeEstadoId() == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_ASIGNADO))) {
                    Date fechaActual = new Date();
                    diasVencidos = (int) ((fechaActual.getTime() - ausServicioGestionHistorico.getFechaHoraCrea().getTime()) / 86400000);
                }
            }
        } else {
            if (ausCasoServicio.getServicioGestionesList() != null
                    && !ausCasoServicio.getServicioGestionesList().isEmpty() && ausCasoServicio.getServicioGestionesList().size() > 0) {
                AusServicioGestionHistorico ausServicioGestionHistorico = ausCasoServicio.getServicioGestionesList().get(ausCasoServicio.getServicioGestionesList().size() - 1);
                if (ausServicioGestionHistorico.getMaeEstadoId() == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_ASIGNADO))) {
                    diasVencidos = (int) ((ausCasoServicio.getFechaCumplimiento().getTime() - ausServicioGestionHistorico.getFechaHoraCrea().getTime()) / 86400000);
                }
            }
        }
        if (diasVencidos < 0) {
            diasVencidos = 0;
        }
        ausCasoServicio.setDiasVencidos(diasVencidos);
        return diasVencidos;

    }

    public void mostrarMensajeGeneral(String mensaje) {
        setMensajeGeneral(mensaje);
        PrimeFaces.current().resetInputs("frmObservacion");
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }

    /**
     * @return the hashEstadosServicioGestionValor
     */
    public HashMap<String, Maestro> getHashEstadosServicioGestionValor() {
        return hashEstadosServicioGestionValor;
    }

    /**
     * @param hashEstadosServicioGestionValor the hashEstadosServicioGestionValor to set
     */
    public void setHashEstadosServicioGestionValor(HashMap<String, Maestro> hashEstadosServicioGestionValor) {
        this.hashEstadosServicioGestionValor = hashEstadosServicioGestionValor;
    }

    /**
     * @return the hashEstadosServicioGestion
     */
    public HashMap<Integer, Maestro> getHashEstadosServicioGestion() {
        return hashEstadosServicioGestion;
    }

    /**
     * @param hashEstadosServicioGestion the hashEstadosServicioGestion to set
     */
    public void setHashEstadosServicioGestion(HashMap<Integer, Maestro> hashEstadosServicioGestion) {
        this.hashEstadosServicioGestion = hashEstadosServicioGestion;
    }
}
