/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.atencionusuario.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.atencionusuario.AusAdjunto;
import com.saviasaludeps.savia.dominio.atencionusuario.AusSolicitud;
import com.saviasaludeps.savia.web.atencionusuario.servicio.SolicitudServicioIface;
import com.saviasaludeps.savia.web.atencionusuario.servicio.SolicitudServicioImpl;
import com.saviasaludeps.savia.web.atencionusuario.utilities.PropAtencionUsuario;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_EDITAR;
import java.io.ByteArrayInputStream;
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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;
import org.springframework.web.jsf.FacesContextUtils;

@ManagedBean
@ViewScoped
public class SolicitudBean extends Url {
    
    public static String PREFIJO_MEDELLIN = "001";
    
    public static String ESTADO_SOLICITUD_EN_TRAMITE = "1";
    public static String SOLICITUD_ORIGEN_PAGINA_WEB = "8";
    public static String TECNOLOGIA_ALTO_COSTO_NO_APLICA = "133";
    public static String MOTIVO_ESPECIFICO_SIN_ESPECIFICAR = "050101";
    public static String SEXO_SIN_IDENTIFICAR = "NA";
    public static String ESTADO_SEGUIMIENTO_EN_GESTION = "4";
    public static int MAX_ARCHIVOS_CORREO = 3;
    
    public static final String SESION_ADJUNTO_SOLICITUDES = "adjuntosSolicitud";

    private SolicitudServicioIface solicitudServicio;
    private AusSolicitud objeto;
    private List<AusSolicitud> registros;
    private LazyDataModel<AusSolicitud> lazySolicitudes;
    //lista de Maestros
    private List<Maestro> listaEstadoSolicitud;
    private HashMap<Integer, Maestro> hashEstadoSolicitud;
    private HashMap<String, Maestro> hashEstadoSolicitudValor;
    private List<Maestro> listaTipoSolicitud;
    private HashMap<Integer, Maestro> hashTipoSolicitud;
    private List<Maestro> listaTipoDocumento;
    private HashMap<Integer, Maestro> hashTipoDocumento;
    private HashMap<Integer, Maestro> hashSexo;
    private HashMap<String, Maestro> hashSexoValor;
    private List<Ubicacion> listaUbicacion;
    private HashMap<Integer, Ubicacion> hashUbicacion;
    private HashMap<String, Maestro> hashTipoEstadoSolicitudValor;
    private HashMap<String, Maestro> hashTipoSolicitudOrigenValor;
    private HashMap<String, Maestro> hashTecnologiaAltoCostoValor;
    private HashMap<String, Maestro> hashMotivoValor;
    private HashMap<String, Maestro> hashTipoSeguimientoValor;
    
    private String descripcionGenerica;
    
    private String asuntoCorreo;
    private String mensajeCorreo;
    
    private int sizeLimitByAnexo;
    private int maxCantAnexos;
    private int contadorArchivos = 0;
    
    private List<AusAdjunto> listaAdjuntosCorreo;

    public SolicitudBean() {
        this.objeto = new AusSolicitud();
        setMaxCantAnexos(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.MAX_CANT_ANEXOS)));
        //setSizeLimitByAnexo(MAX_TAM_ANEXO);
        setSizeLimitByAnexo(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.MAX_TAM_ANEXO)));
        Modulo mod = super.validarModulo(Modulo.ID_AUS_SOLICITUDES_CASOS);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            //super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazySolicitudes = new LazyDataModel<AusSolicitud>() {
                private List<AusSolicitud> listaSolicitudes;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AusSolicitud> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaSolicitudes = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaSolicitudes;
                }

                @Override
                public String getRowKey(AusSolicitud reporte) {
                    return reporte.getId().toString();
                }

                @Override
                public AusSolicitud getRowData(String reporteId) {
                    Integer id = Integer.valueOf(reporteId);
                    for (AusSolicitud solicitudes : listaSolicitudes) {
                        if (id.equals(solicitudes.getId())) {
                            return solicitudes;
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
        //jyperez aca se llama la carga inicial
        getSolicitudServicio().cargaInicial(this);
        listar();
    }

    public SolicitudServicioIface getSolicitudServicio() {
        if (solicitudServicio == null) {
            solicitudServicio = new SolicitudServicioImpl();
        }
        return solicitudServicio;
    }

    public void setSolicitudServicio(SolicitudServicioIface solicitudServicio) {
        this.solicitudServicio = solicitudServicio;
    }

    public AusSolicitud getObjeto() {
        return objeto;
    }

    public void setObjeto(AusSolicitud objeto) {
        this.objeto = objeto;
    }

    public List<AusSolicitud> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AusSolicitud> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AusSolicitud> getLazySolicitudes() {
        return lazySolicitudes;
    }

    public void setLazySolicitudes(LazyDataModel<AusSolicitud> lazySolicitudes) {
        this.lazySolicitudes = lazySolicitudes;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getSolicitudServicio().Accion(this);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getSolicitudServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getSolicitudServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear:panelCrear");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getSolicitudServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
        }
        procesoFinal();
    }

    public void editar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_EDITAR);
        getSolicitudServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getSolicitudServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_BORRAR);
        getSolicitudServicio().Accion(this);
        procesoFinal();
    }
    
    public void crearCorreo(AusSolicitud obj) {
        setObjeto(obj);
        //super.setAccion(ACCION_ADICIONAL_1);
        //getSolicitudServicio().Accion(this);
        contadorArchivos = 0;
        //inicializamos la lista nuevamente
        this.listaAdjuntosCorreo = new ArrayList();
        //inicializamos nuevamente la sesión con los elementos a cargar para el nuevo envio de correo
        llenarSesionAdjuntos(SESION_ADJUNTO_SOLICITUDES, new ArrayList());
        PrimeFaces.current().resetInputs("frmEnviarCorreo");
        PrimeFaces.current().ajax().update("frmEnviarCorreo");
        PrimeFaces.current().executeScript("PF('dialogoEnviarCorreo').show()");
        procesoFinal();
    }
    
    public void enviarCorreo() {
        super.setAccion(ACCION_ADICIONAL_1);
        getSolicitudServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEnviarCorreo').hide();");
        }
        procesoFinal();
    }
    
    public boolean estadoValidoSolicitud(AusSolicitud objeto) {
        boolean valido = false;
        if (objeto.getMaeEstadoSolicitudCodigo() != null && objeto.getMaeEstadoSolicitudCodigo().equals(AusSolicitud.ESTADO_SOLICITUD_RADICADO)) {
            valido = true;
        }
        return valido;
    }
    
    public boolean estadoValidoSolicitudCrearCaso(AusSolicitud objeto) {
        boolean valido = false;
        if (objeto.getMaeEstadoSolicitudCodigo() != null && objeto.getMaeEstadoSolicitudCodigo().equals(AusSolicitud.ESTADO_SOLICITUD_RADICADO)
                && !objeto.getMaeTipoSolicitudCodigo().equals(AusSolicitud.TIPO_SOLICITUD_PETICION_ANONIMA)) {
            valido = true;
        }
        return valido;
    }
    
    public boolean getEstadoValidoCorreo(AusSolicitud objeto) {
        boolean valido = false;
        if (objeto.getMaeEstadoSolicitudCodigo() != null && objeto.getMaeEstadoSolicitudCodigo().equals(AusSolicitud.ESTADO_SOLICITUD_CERRADO)) {
            valido = true;
        }
        return valido;
    }
    
    public void crearCaso(AusSolicitud obj) {
        setObjeto(obj);
        super.setAccion(ACCION_ADICIONAL_2);
        getSolicitudServicio().Accion(this);
        /*PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");*/
        procesoFinal();
    }
    
    

    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_VER:
            case Url.ACCION_CREAR:
            case Url.ACCION_EDITAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_GUARDAR:
            case Url.ACCION_MODIFICAR:
            case Url.ACCION_BORRAR:
                crearLog(getObjeto().toString());
                PrimeFaces.current().ajax().update("frmSolicitudes");
                break;
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_1:
                crearLog("Envio de Correo",getObjeto().toString());
                PrimeFaces.current().ajax().update("frmSolicitudes");
                break;
            case Url.ACCION_ADICIONAL_2:
                crearLog("Creación Caso",getObjeto().toString());
                PrimeFaces.current().ajax().update("frmSolicitudes");
                break;
        }
        generarMensajes();
    }

    public String getActivo(boolean valor) {
        if (valor) {
            return "Si";
        } else {
            return "No";
        }
    }

    public String getValorBandera(Boolean valor) {
        String mensaje = "";
        if (valor != null) {
            if (valor) {
                mensaje = "Si";
            } else {
                mensaje = "No";
            }
        }
        return mensaje;
    }

    public String getSexoAplica(int valor) {
        String mensaje = "";
        switch (valor) {
            case 0:
                mensaje = "Masculino";
                break;
            case 1:
                mensaje = "Femenino";
                break;
            case 2:
                mensaje = "Ambos";
                break;
        }
        return mensaje;
    }
    
    public String getTipoSolicitud(int valor) {
        String mensaje = "";
        try {
            Maestro mae = this.hashTipoSolicitud.get(valor);
            mensaje = mae.getValor();
        } catch(Exception ex) {
            mensaje = "";
        }
        return mensaje;
    }
    
    public String getEstadoSolicitud(int valor) {
        String mensaje = "";
        try {
            Maestro mae = this.hashEstadoSolicitud.get(valor);
            mensaje = mae.getValor();
        } catch(Exception ex) {
            mensaje = "";
        }
        return mensaje;
    }
    public String getTipoDocumento(int valor) {
        String mensaje = "";
        try {
            Maestro mae = this.hashTipoDocumento.get(valor);
            mensaje = mae.getValor();
        } catch(Exception ex) {
            mensaje = "";
        }
        return mensaje;
    }
    
    public void mostrarMensaje(String mensaje) {
        setDescripcionGenerica(mensaje);
        PrimeFaces.current().resetInputs("frmObservacion");
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }
    
    public String convertirBytesParaMegasTamAdjunto() {
        int valor = getSizeLimitByAnexo() <= 0 ? 5242880 : getSizeLimitByAnexo();
        float MEGABYTE = 1024L * 1024L;
        float b = valor / MEGABYTE;
        return (b + " MB");
    }
    
    public void handleFileUpload(FileUploadEvent event) throws IOException {
        try {

            String file = FilenameUtils.getName(event.getFile().getFileName());
            int i = file.lastIndexOf(".");
            String ext = file.substring(i, file.length());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String nombre = "adjunto_respuesta_solicitud_";
            if (this.contadorArchivos == MAX_ARCHIVOS_CORREO) {
                throw new IOException(" El máximo de archivos a cargar es " + MAX_ARCHIVOS_CORREO);
            }
            contadorArchivos++;
            String filename = nombre + this.objeto.getId() + "_" + sdf.format(new Date()) + ext;
            UploadedFile archivo = event.getFile();
            byte[] arreglo = archivo.getInputStream().readAllBytes();
            InputStream input = new ByteArrayInputStream(arreglo);

            String ruta = PropApl.getInstance().get(PropApl.AUS_RUTA_CARGA);
            if (ruta == null) {
                throw new IOException("Configura la ruta de los adjuntos  en tabla parametros");
            } else {
                File directorios = new File(ruta);
                if (!directorios.exists()) {
                    if (directorios.mkdirs()) {
                        System.out.println("Multiples directorios fueron creados");
                    } else {
                        System.out.println("Error al crear directorios");
                    }
                }
            }
            OutputStream output = new FileOutputStream(new File(ruta, filename));

            IOUtils.copy(input, output);
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);

            List<AusAdjunto> listaAnexos = obtenerSesionAdjuntos(SESION_ADJUNTO_SOLICITUDES);
            if (listaAnexos == null) {
                listaAnexos = new ArrayList<>();
                this.listaAdjuntosCorreo.addAll(listaAnexos);
            }

            //agraegar el nuevo anexo a la lista de anexos de tutela
            AusAdjunto adjunto = new AusAdjunto();
            adjunto.setId(null);
            adjunto.setSolicitudId(this.getObjeto());
            adjunto.setNombre(filename);
            adjunto.setArchivo(event.getFile().getFileName());
            adjunto.setRuta(ruta);
            this.auditoriaGuardar(adjunto);
            adjunto.setPos(this.listaAdjuntosCorreo.size());

            listaAnexos.add(adjunto);

            this.listaAdjuntosCorreo.add(adjunto);

            llenarSesionAdjuntos(SESION_ADJUNTO_SOLICITUDES, listaAnexos);

            PrimeFaces.current().resetInputs("frmEnviarCorreo:tablaAnexoSolicitud");
            PrimeFaces.current().ajax().update("frmEnviarCorreo:tablaAnexoSolicitud");
        } catch (IOException e) {
            this.addError("Error al cargar archivo adjunto caso : " + e.getMessage());
            generarMensajes();
        }
    }
    
    public void borrarAdjuntoCasoDeMemoria(int pos) {
        try {
            delAdjuntoCasoMemoria(pos);
        } catch (Exception e) {
            super.addError("No es posible borrar el Adjunto");
        }
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmEnviarCorreo:tablaAnexoSolicitud");
        }
    }
    
    public void delAdjuntoCasoMemoria(int pos) {
        List<AusAdjunto> lista = new ArrayList();
        int i = 0, j = 0;
        for (AusAdjunto det : getListaAdjuntosCorreo()) {
            if (j != pos) {
                det.setPos(i);
                lista.add(det);
                i++;
            }
            j++;
        }
        llenarSesionAdjuntos(SESION_ADJUNTO_SOLICITUDES, lista);
        setContadorArchivos(i);
        //inicializamos la lista
        setListaAdjuntosCorreo(new ArrayList());
        getListaAdjuntosCorreo().addAll(lista);
    }
    
    private void llenarSesionAdjuntos(String nombreSession, List<AusAdjunto> listaAnexos) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(nombreSession, listaAnexos);
    }

    private List<AusAdjunto> obtenerSesionAdjuntos(String nombreSesion) {
        List<AusAdjunto> listaAdjuntos = (List<AusAdjunto>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(nombreSesion);
        return listaAdjuntos;
    }
    
    public void descargarAnexo(AusAdjunto adjunto) {
        String rutaCompleta = adjunto.getRuta() + File.separator + adjunto.getNombre();
        try {
            File file = new File(rutaCompleta);
            byte[] exportContent = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);;
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
            } else if (ext.equalsIgnoreCase(".png")) {
                ec.setResponseContentType("image/png");
            } else if (ext.equalsIgnoreCase(".mp3")) {
                ec.setResponseContentType("audio/mpeg");
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

    /**
     * @return the listaEstadoSolicitud
     */
    public List<Maestro> getListaEstadoSolicitud() {
        return listaEstadoSolicitud;
    }

    /**
     * @param listaEstadoSolicitud the listaEstadoSolicitud to set
     */
    public void setListaEstadoSolicitud(List<Maestro> listaEstadoSolicitud) {
        this.listaEstadoSolicitud = listaEstadoSolicitud;
    }

    /**
     * @return the hashEstadoSolicitud
     */
    public HashMap<Integer, Maestro> getHashEstadoSolicitud() {
        return hashEstadoSolicitud;
    }

    /**
     * @param hashEstadoSolicitud the hashEstadoSolicitud to set
     */
    public void setHashEstadoSolicitud(HashMap<Integer, Maestro> hashEstadoSolicitud) {
        this.hashEstadoSolicitud = hashEstadoSolicitud;
    }

    /**
     * @return the listaTipoSolicitud
     */
    public List<Maestro> getListaTipoSolicitud() {
        return listaTipoSolicitud;
    }

    /**
     * @param listaTipoSolicitud the listaTipoSolicitud to set
     */
    public void setListaTipoSolicitud(List<Maestro> listaTipoSolicitud) {
        this.listaTipoSolicitud = listaTipoSolicitud;
    }

    /**
     * @return the hashTipoSolicitud
     */
    public HashMap<Integer, Maestro> getHashTipoSolicitud() {
        return hashTipoSolicitud;
    }

    /**
     * @param hashTipoSolicitud the hashTipoSolicitud to set
     */
    public void setHashTipoSolicitud(HashMap<Integer, Maestro> hashTipoSolicitud) {
        this.hashTipoSolicitud = hashTipoSolicitud;
    }

    /**
     * @return the listaTipoDocumento
     */
    public List<Maestro> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    /**
     * @param listaTipoDocumento the listaTipoDocumento to set
     */
    public void setListaTipoDocumento(List<Maestro> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }

    /**
     * @return the hashTipoDocumento
     */
    public HashMap<Integer, Maestro> getHashTipoDocumento() {
        return hashTipoDocumento;
    }

    /**
     * @param hashTipoDocumento the hashTipoDocumento to set
     */
    public void setHashTipoDocumento(HashMap<Integer, Maestro> hashTipoDocumento) {
        this.hashTipoDocumento = hashTipoDocumento;
    }

    /**
     * @return the descripcionGenerica
     */
    public String getDescripcionGenerica() {
        return descripcionGenerica;
    }

    /**
     * @param descripcionGenerica the descripcionGenerica to set
     */
    public void setDescripcionGenerica(String descripcionGenerica) {
        this.descripcionGenerica = descripcionGenerica;
    }

    /**
     * @return the hashSexo
     */
    public HashMap<Integer, Maestro> getHashSexo() {
        return hashSexo;
    }

    /**
     * @param hashSexo the hashSexo to set
     */
    public void setHashSexo(HashMap<Integer, Maestro> hashSexo) {
        this.hashSexo = hashSexo;
    }

    /**
     * @return the hashSexoValor
     */
    public HashMap<String, Maestro> getHashSexoValor() {
        return hashSexoValor;
    }

    /**
     * @param hashSexoValor the hashSexoValor to set
     */
    public void setHashSexoValor(HashMap<String, Maestro> hashSexoValor) {
        this.hashSexoValor = hashSexoValor;
    }

    /**
     * @return the hashUbicacion
     */
    public HashMap<Integer, Ubicacion> getHashUbicacion() {
        return hashUbicacion;
    }

    /**
     * @param hashUbicacion the hashUbicacion to set
     */
    public void setHashUbicacion(HashMap<Integer, Ubicacion> hashUbicacion) {
        this.hashUbicacion = hashUbicacion;
    }

    /**
     * @return the listaUbicacion
     */
    public List<Ubicacion> getListaUbicacion() {
        return listaUbicacion;
    }

    /**
     * @param listaUbicacion the listaUbicacion to set
     */
    public void setListaUbicacion(List<Ubicacion> listaUbicacion) {
        this.listaUbicacion = listaUbicacion;
    }

    /**
     * @return the hashTipoEstadoSolicitudValor
     */
    public HashMap<String, Maestro> getHashTipoEstadoSolicitudValor() {
        return hashTipoEstadoSolicitudValor;
    }

    /**
     * @param hashTipoEstadoSolicitudValor the hashTipoEstadoSolicitudValor to set
     */
    public void setHashTipoEstadoSolicitudValor(HashMap<String, Maestro> hashTipoEstadoSolicitudValor) {
        this.hashTipoEstadoSolicitudValor = hashTipoEstadoSolicitudValor;
    }

    /**
     * @return the hashTipoSolicitudOrigenValor
     */
    public HashMap<String, Maestro> getHashTipoSolicitudOrigenValor() {
        return hashTipoSolicitudOrigenValor;
    }

    /**
     * @param hashTipoSolicitudOrigenValor the hashTipoSolicitudOrigenValor to set
     */
    public void setHashTipoSolicitudOrigenValor(HashMap<String, Maestro> hashTipoSolicitudOrigenValor) {
        this.hashTipoSolicitudOrigenValor = hashTipoSolicitudOrigenValor;
    }

    /**
     * @return the hashTecnologiaAltoCostoValor
     */
    public HashMap<String, Maestro> getHashTecnologiaAltoCostoValor() {
        return hashTecnologiaAltoCostoValor;
    }

    /**
     * @param hashTecnologiaAltoCostoValor the hashTecnologiaAltoCostoValor to set
     */
    public void setHashTecnologiaAltoCostoValor(HashMap<String, Maestro> hashTecnologiaAltoCostoValor) {
        this.hashTecnologiaAltoCostoValor = hashTecnologiaAltoCostoValor;
    }

    /**
     * @return the hashMotivoValor
     */
    public HashMap<String, Maestro> getHashMotivoValor() {
        return hashMotivoValor;
    }

    /**
     * @param hashMotivoValor the hashMotivoValor to set
     */
    public void setHashMotivoValor(HashMap<String, Maestro> hashMotivoValor) {
        this.hashMotivoValor = hashMotivoValor;
    }

    /**
     * @return the hashEstadoSolicitudValor
     */
    public HashMap<String, Maestro> getHashEstadoSolicitudValor() {
        return hashEstadoSolicitudValor;
    }

    /**
     * @param hashEstadoSolicitudValor the hashEstadoSolicitudValor to set
     */
    public void setHashEstadoSolicitudValor(HashMap<String, Maestro> hashEstadoSolicitudValor) {
        this.hashEstadoSolicitudValor = hashEstadoSolicitudValor;
    }

    /**
     * @return the asuntoCorreo
     */
    public String getAsuntoCorreo() {
        return asuntoCorreo;
    }

    /**
     * @param asuntoCorreo the asuntoCorreo to set
     */
    public void setAsuntoCorreo(String asuntoCorreo) {
        this.asuntoCorreo = asuntoCorreo;
    }

    /**
     * @return the mensajeCorreo
     */
    public String getMensajeCorreo() {
        return mensajeCorreo;
    }

    /**
     * @param mensajeCorreo the mensajeCorreo to set
     */
    public void setMensajeCorreo(String mensajeCorreo) {
        this.mensajeCorreo = mensajeCorreo;
    }

    /**
     * @return the sizeLimitByAnexo
     */
    public int getSizeLimitByAnexo() {
        return sizeLimitByAnexo;
    }

    /**
     * @param sizeLimitByAnexo the sizeLimitByAnexo to set
     */
    public void setSizeLimitByAnexo(int sizeLimitByAnexo) {
        this.sizeLimitByAnexo = sizeLimitByAnexo;
    }

    /**
     * @return the maxCantAnexos
     */
    public int getMaxCantAnexos() {
        return maxCantAnexos;
    }

    /**
     * @param maxCantAnexos the maxCantAnexos to set
     */
    public void setMaxCantAnexos(int maxCantAnexos) {
        this.maxCantAnexos = maxCantAnexos;
    }

    /**
     * @return the contadorArchivos
     */
    public int getContadorArchivos() {
        return contadorArchivos;
    }

    /**
     * @param contadorArchivos the contadorArchivos to set
     */
    public void setContadorArchivos(int contadorArchivos) {
        this.contadorArchivos = contadorArchivos;
    }

    /**
     * @return the listaAdjuntosCorreo
     */
    public List<AusAdjunto> getListaAdjuntosCorreo() {
        return listaAdjuntosCorreo;
    }

    /**
     * @param listaAdjuntosCorreo the listaAdjuntosCorreo to set
     */
    public void setListaAdjuntosCorreo(List<AusAdjunto> listaAdjuntosCorreo) {
        this.listaAdjuntosCorreo = listaAdjuntosCorreo;
    }

    /**
     * @return the hashTipoSeguimientoValor
     */
    public HashMap<String, Maestro> getHashTipoSeguimientoValor() {
        return hashTipoSeguimientoValor;
    }

    /**
     * @param hashTipoSeguimientoValor the hashTipoSeguimiento to set
     */
    public void setHashTipoSeguimientoValor(HashMap<String, Maestro> hashTipoSeguimientoValor) {
        this.hashTipoSeguimientoValor = hashTipoSeguimientoValor;
    }

    
}
