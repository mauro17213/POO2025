package com.saviasaludeps.savia.web.especial.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContacto;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitudAdjunto;
import com.saviasaludeps.savia.dominio.especial.PeDireccionado;
import com.saviasaludeps.savia.dominio.especial.PeDireccionadoGestion;
import com.saviasaludeps.savia.dominio.especial.PeDireccionadoItem;
import com.saviasaludeps.savia.dominio.especial.PeTelefono;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuReporte;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.saviasaludeps.savia.web.especial.servicio.GestionDireccionadoServicioIface;
import com.saviasaludeps.savia.web.especial.servicio.GestionDireccionadoServicioImpl;
import com.saviasaludeps.savia.web.especial.utilidades.PeConstantes;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author idbohorquez
 */
@ManagedBean
@ViewScoped
public class GestionDireccionadosBean extends Url {

    private GestionDireccionadoServicioIface gestionDireccionadoServicio;
    private PeDireccionado objeto;
    private AuAnexo3 objetoAnexo3;
    private PeDireccionadoGestion objetoGestion;
    private List<PeDireccionado> registros;
    private LazyDataModel<PeDireccionado> lazyDireccionados;
    private PeDireccionadoItem objetoDireccionadoItem;

    private HashMap<Integer, Ubicacion> hashUbicaciones;
    private List<Maestro> listaTipoDocumento;
    private HashMap<Integer, Maestro> hashTipoDocumento;
    private List<Maestro> listaTipoDocumentoEmpresa;
    private HashMap<Integer, Maestro> hashTipoDocumentoEmpresa;
    private List<Maestro> listaSiNo;
    private List<Maestro> listaEstadosDireccionado;
    private HashMap<Integer, Maestro> hashEstadosDireccionado;
    private List<AsegAfiliadoContacto> listaContactosAfiliado;
    private List<PeTelefono> listaContactosPrograma;
    private List<Maestro> listaTiposGenero;
    private HashMap<Integer, Maestro> hashTiposGenero;
    private List<Maestro> listaEstadosAfiliacion;
    private HashMap<Integer, Maestro> hashEstadosAfiliacion;
    private List<MaDiagnostico> listaDiagnostico;
    private HashMap<Integer, MaDiagnostico> hashDiagnostico;
    //private List<Maestro> listaGestionTipo;
    private List<Maestro> listaTipoGestion;
    private HashMap<Integer, Maestro> hashTipoGestion;

    public GestionDireccionadosBean() {
        this.objeto = new PeDireccionado();
        this.objetoGestion = new PeDireccionadoGestion();
        this.objetoDireccionadoItem = new PeDireccionadoItem();
        Modulo mod = super.validarModulo(Modulo.ID_PROGRAMA_ESPECIAL_DIRECCIONADOS);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            if (!super.getConexion().getEmpresa().isAdministradora()) {
                super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            }
            lazyDireccionados = new LazyDataModel<PeDireccionado>() {
                private List<PeDireccionado> direcionados;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<PeDireccionado> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    direcionados = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return direcionados;
                }

                @Override
                public String getRowKey(PeDireccionado direccionado) {
                    return direccionado.getId().toString();
                }

                @Override
                public PeDireccionado getRowData(String direccionadoId) {
                    Integer id = Integer.valueOf(direccionadoId);
                    for (PeDireccionado direccionado : direcionados) {
                        if (id.equals(direccionado.getId())) {
                            return direccionado;
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
        getGestionDireccionadoServicio().cargaInicial(this);
        listar();
    }

    public GestionDireccionadoServicioIface getGestionDireccionadoServicio() {
        if (gestionDireccionadoServicio == null) {
            gestionDireccionadoServicio = new GestionDireccionadoServicioImpl();
        }
        return gestionDireccionadoServicio;
    }

    public String getEstadoDireccionado(int estado) {
        return PeConstantes.obtenerEstadoDireccionado(estado).toUpperCase();
    }

    // getters and setters
    public PeDireccionado getObjeto() {
        return objeto;
    }

    public void setObjeto(PeDireccionado objeto) {
        this.objeto = objeto;
    }

    public List<PeDireccionado> getRegistros() {
        return registros;
    }

    public void setRegistros(List<PeDireccionado> registros) {
        this.registros = registros;
    }

    public LazyDataModel<PeDireccionado> getLazyDireccionados() {
        return lazyDireccionados;
    }

    public void setLazyDireccionados(LazyDataModel<PeDireccionado> lazyDireccionados) {
        this.lazyDireccionados = lazyDireccionados;
    }

    public AuAnexo3 getObjetoAnexo3() {
        return objetoAnexo3;
    }

    public void setObjetoAnexo3(AuAnexo3 objetoAnexo3) {
        this.objetoAnexo3 = objetoAnexo3;
    }

    public HashMap<Integer, Ubicacion> getHashUbicaciones() {
        return hashUbicaciones;
    }

    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashUbicaciones) {
        this.hashUbicaciones = hashUbicaciones;
    }

    public List<Maestro> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    public void setListaTipoDocumento(List<Maestro> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }

    public HashMap<Integer, Maestro> getHashTipoDocumento() {
        return hashTipoDocumento;
    }

    public void setHashTipoDocumento(HashMap<Integer, Maestro> hashTipoDocumento) {
        this.hashTipoDocumento = hashTipoDocumento;
    }

    public String obtenerBoolean(boolean bool) {
        return bool == true ? AuConstantes.TEXTO_SI : AuConstantes.TEXTO_NO;
    }

    public String obtenerTipoTencnologia(int id) {
        return AuConstantes.obtenerTipoTecnologia(id);
    }

    public List<Maestro> getListaTipoDocumentoEmpresa() {
        return listaTipoDocumentoEmpresa;
    }

    public void setListaTipoDocumentoEmpresa(List<Maestro> listaTipoDocumentoEmpresa) {
        this.listaTipoDocumentoEmpresa = listaTipoDocumentoEmpresa;
    }

    public HashMap<Integer, Maestro> getHashTipoDocumentoEmpresa() {
        return hashTipoDocumentoEmpresa;
    }

    public void setHashTipoDocumentoEmpresa(HashMap<Integer, Maestro> hashTipoDocumentoEmpresa) {
        this.hashTipoDocumentoEmpresa = hashTipoDocumentoEmpresa;
    }

    public List<Maestro> getListaSiNo() {
        return listaSiNo;
    }

    public void setListaSiNo(List<Maestro> listaSiNo) {
        this.listaSiNo = listaSiNo;
    }

    public List<Maestro> getListaEstadosDireccionado() {
        return listaEstadosDireccionado;
    }

    public void setListaEstadosDireccionado(List<Maestro> listaEstadosDireccionado) {
        this.listaEstadosDireccionado = listaEstadosDireccionado;
    }

    public HashMap<Integer, Maestro> getHashEstadosDireccionado() {
        return hashEstadosDireccionado;
    }

    public void setHashEstadosDireccionado(HashMap<Integer, Maestro> hashEstadosDireccionado) {
        this.hashEstadosDireccionado = hashEstadosDireccionado;
    }

    public List<AsegAfiliadoContacto> getListaContactosAfiliado() {
        return listaContactosAfiliado;
    }

    public void setListaContactosAfiliado(List<AsegAfiliadoContacto> listaContactosAfiliado) {
        this.listaContactosAfiliado = listaContactosAfiliado;
    }

    public List<PeTelefono> getListaContactosPrograma() {
        return listaContactosPrograma;
    }

    public void setListaContactosPrograma(List<PeTelefono> listaContactosPrograma) {
        this.listaContactosPrograma = listaContactosPrograma;
    }

    public List<Maestro> getListaTiposGenero() {
        return listaTiposGenero;
    }

    public void setListaTiposGenero(List<Maestro> listaTiposGenero) {
        this.listaTiposGenero = listaTiposGenero;
    }

    public HashMap<Integer, Maestro> getHashTiposGenero() {
        return hashTiposGenero;
    }

    public void setHashTiposGenero(HashMap<Integer, Maestro> hashTiposGenero) {
        this.hashTiposGenero = hashTiposGenero;
    }

    public List<Maestro> getListaEstadosAfiliacion() {
        return listaEstadosAfiliacion;
    }

    public void setListaEstadosAfiliacion(List<Maestro> listaEstadosAfiliacion) {
        this.listaEstadosAfiliacion = listaEstadosAfiliacion;
    }

    public HashMap<Integer, Maestro> getHashEstadosAfiliacion() {
        return hashEstadosAfiliacion;
    }

    public void setHashEstadosAfiliacion(HashMap<Integer, Maestro> hashEstadosAfiliacion) {
        this.hashEstadosAfiliacion = hashEstadosAfiliacion;
    }

    public List<MaDiagnostico> getListaDiagnostico() {
        return listaDiagnostico;
    }

    public void setListaDiagnostico(List<MaDiagnostico> listaDiagnostico) {
        this.listaDiagnostico = listaDiagnostico;
    }

    public HashMap<Integer, MaDiagnostico> getHashDiagnostico() {
        return hashDiagnostico;
    }

    public void setHashDiagnostico(HashMap<Integer, MaDiagnostico> hashDiagnostico) {
        this.hashDiagnostico = hashDiagnostico;
    }

    public PeDireccionadoGestion getObjetoGestion() {
        return objetoGestion;
    }

    public void setObjetoGestion(PeDireccionadoGestion objetoGestion) {
        this.objetoGestion = objetoGestion;
    }

    public List<Maestro> getListaTipoGestion() {
        return listaTipoGestion;
    }

    public void setListaTipoGestion(List<Maestro> listaTipoGestion) {
        this.listaTipoGestion = listaTipoGestion;
    }

    public HashMap<Integer, Maestro> getHashTipoGestion() {
        return hashTipoGestion;
    }

    public void setHashTipoGestion(HashMap<Integer, Maestro> hashTipoGestion) {
        this.hashTipoGestion = hashTipoGestion;
    }

    public PeDireccionadoItem getObjetoDireccionadoItem() {
        return objetoDireccionadoItem;
    }

    public void setObjetoDireccionadoItem(PeDireccionadoItem objetoDireccionadoItem) {
        this.objetoDireccionadoItem = objetoDireccionadoItem;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getGestionDireccionadoServicio().Accion(this);
    }

    // metodos y funciones
    public void ver() {
        super.setAccion(ACCION_VER);
        getGestionDireccionadoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerDireccionado");
        PrimeFaces.current().ajax().update("frmAuditoriaVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void abrirGestion() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_LISTAR);
        getGestionDireccionadoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmGestionAfiliado");
        PrimeFaces.current().ajax().update("frmGestionAfiliado");
        PrimeFaces.current().resetInputs("frmGestionDireccionado");
        PrimeFaces.current().ajax().update("frmGestionDireccionado");
        PrimeFaces.current().ajax().update("frmAuditoriaGestionar");
        PrimeFaces.current().executeScript("PF('dialogoGestiones').show()");
        procesoFinal();
    }

    public void crearGestion() {
        setObjetoGestion(new PeDireccionadoGestion());
        PrimeFaces.current().resetInputs("frmGestiones");
        PrimeFaces.current().ajax().update("frmGestiones");
        PrimeFaces.current().executeScript("PF('dialogoCrearGestion').show()");
    }

    public void guardarGestiones() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_GUARDAR);
        getGestionDireccionadoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmGestionAfiliado");
        PrimeFaces.current().ajax().update("frmGestionDireccionado");
        PrimeFaces.current().ajax().update("frmGestion");
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearGestion').hide();");
        }
        procesoFinal();
    }

    public void anular(int id) {
        getObjeto().setId(id);
        super.setAccion(Url.ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_EDITAR);
        getGestionDireccionadoServicio().Accion(this);
        PrimeFaces.current().executeScript("PF('dialogoAnular').show()");
        PrimeFaces.current().ajax().update("frmAnular");
        procesoFinal();
    }

    public void anularDireccionado() {
        super.setAccion(Url.ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_MODIFICAR);
        getGestionDireccionadoServicio().Accion(this);
        PrimeFaces.current().executeScript("PF('dialogoAnular').hide()");
        PrimeFaces.current().ajax().update("frmGestion");
        procesoFinal();
    }

    public void abrirRegistrarFecha(PeDireccionadoItem idItem) {
        this.objetoDireccionadoItem = new PeDireccionadoItem(idItem.getId());
        PrimeFaces.current().resetInputs("frmRegFecha");
        PrimeFaces.current().ajax().update("frmRegFecha");
        PrimeFaces.current().executeScript("PF('dialogoRegistrarFecha').show()");
    }

    public void establecerFechaAtencion(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_CREAR);
        getGestionDireccionadoServicio().Accion(this);
        PrimeFaces.current().executeScript("PF('dialogoGestionarFechas').show()");
        PrimeFaces.current().ajax().update("frmGestionarFecha");
    }

    public void guardarFechaAtencion() {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_GUARDAR);
        getGestionDireccionadoServicio().Accion(this);
        PrimeFaces.current().executeScript("PF('dialogoRegistrarFecha').hide()");
        PrimeFaces.current().ajax().update("frmGestion");
        PrimeFaces.current().ajax().update("frmGestionarFecha");
        procesoFinal();
    }

    public void abrirDialogoRechazarDireccionadoItem(PeDireccionadoItem idItem) {
        this.objetoDireccionadoItem = new PeDireccionadoItem(idItem.getId());
        PrimeFaces.current().resetInputs("frmRechazarItem");
        PrimeFaces.current().ajax().update("frmRechazarItem");
        PrimeFaces.current().executeScript("PF('dialogoRechazarItem').show()");
    }

    public void rechazarDireccionadoItam() {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_ADICIONAL_1);
        getGestionDireccionadoServicio().Accion(this);
        PrimeFaces.current().executeScript("PF('dialogoRechazarItem').hide()");
        PrimeFaces.current().ajax().update("frmGestion");
        PrimeFaces.current().ajax().update("frmGestionarFecha");
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
                PrimeFaces.current().ajax().update("frmGestion");
                break;
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                PrimeFaces.current().ajax().update("frmGestion");
                break;
            case Url.ACCION_ADICIONAL_1:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_2:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_3:
                crearLog("Anular", getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_4:
                crearLog(getObjeto().toString());
                break;
        }
        generarMensajes();
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

    public String obtenerTipoDocumentoIps(int id) {
        try {
            return hashTipoDocumentoEmpresa.get(id).getNombre();
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

    public String getTipoGenero(String id) {
        try {
            return hashTiposGenero.get(Integer.parseInt(id)).getNombre();
        } catch (NumberFormatException e) {
            return id;
        }
    }

    public String getEstadoAfiliacion(int id) {
        try {
            return hashEstadosAfiliacion.get(id).getNombre();
        } catch (Exception e) {
            return String.valueOf(id);
        }
    }

    public String getUbicacionRecursiva(Integer id) {
        String ubicacionStr = "";
        if (id == null) {
            return ubicacionStr;
        }
        Ubicacion municipio = hashUbicaciones.get(id);
        if (getHashUbicaciones() != null) {
            municipio = getHashUbicaciones().get(id);
        }
        if (municipio != null && municipio.getUbicacionPadre() != null) {
            Ubicacion departamento = municipio.getUbicacionPadre();
            ubicacionStr = departamento.getNombre();
            ubicacionStr = municipio.getNombre() + " - " + ubicacionStr;
        }
        return ubicacionStr;
    }

    public void descargarAdjuntoAnexo3(AuSolicitudAdjunto adjunto) {
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
            String attachmentName = "attachment; filename=\"" + adjunto.getNombre() + "\"";
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
        procesoFinal();
    }

    public String getDxRecursiva(int id) {
        String dxStr = "";
        MaDiagnostico maDiagnostico = hashDiagnostico.get(id);
        if (maDiagnostico != null && maDiagnostico.getId() != null) {
            dxStr = maDiagnostico.getCodigo() + " - " + maDiagnostico.getNombre();
        }
        return dxStr;
    }

    public StreamedContent generarReporte(PeDireccionado objeto) {
        super.setAccion(ACCION_ADICIONAL_2);
        getGestionDireccionadoServicio().Accion(this);
        StreamedContent streamContent = null;
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        try {
            //getObjeto().getAuAnexo4();
            AuReporte reporte = new AuReporte();
            //List<ReporteAnexo3> listaReportes = getAuSolicitudServicio().generarReporteAnexo3(obj.getId(), this);
            if (!getObjeto().getReporteDireccionados().isEmpty()) {
                String nombre = "Anexo direccionamiento.pdf";
                InputStream is = getClass().getResourceAsStream("/reportes/Direccionado.jasper");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(getObjeto().getReporteDireccionados());

                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));

                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0);
                streamContent = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(nombre).build();
                crearLog("Imprimir", getObjeto().toString());
            } else {
                addError("Error no hay datos para generar el reporte");
            }

        } catch (JRException ex) {
            streamContent = null;
            addError("Error Reporte: " + ex.toString() + ex.getMessage());
        }
        generarMensajes();
        return streamContent;
    }

    public Date obtenerFechaActual() throws ParseException {
        SimpleDateFormat formatoBasico = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoCompleto = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date fechaActual = new Date();
        String nuevaFecha = formatoBasico.format(fechaActual) + " 23:59:59";
        fechaActual = formatoCompleto.parse(nuevaFecha);
        return fechaActual;
    }

    public String getEstadoItem(int estado) {
        return PeConstantes.obtenerNombreEstadoItemDireccionado(estado);
    }

    public void mostrarAfiliadoGeneral() {
        PrimeFaces.current().resetInputs("frmAfiliadoGeneral");
        PrimeFaces.current().ajax().update("frmAfiliadoGeneral");
        PrimeFaces.current().executeScript("PF('dlgInfoAfiliado').show();");
    }

}
