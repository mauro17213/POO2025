package com.saviasaludeps.savia.web.recobro.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.recobro.RcoActa;
import com.saviasaludeps.savia.dominio.recobro.RcoActaAsistente;
import com.saviasaludeps.savia.dominio.recobro.RcoConciliacion;
import com.saviasaludeps.savia.dominio.recobro.RcoConciliacionAdjunto;
import com.saviasaludeps.savia.dominio.recobro.RcoConciliacionGestion;
import com.saviasaludeps.savia.dominio.recobro.RcoFactura;
import com.saviasaludeps.savia.dominio.recobro.RcoFacturaDetalle;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.recobro.servicio.RecobroConciliacionServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author sgiraldov
 */
@ManagedBean
@ViewScoped
public class RecobroConciliacionBean extends Url {

    @Autowired
    private RecobroConciliacionServicioIface rcoConciliacionServicio;
    private RcoConciliacion objeto;
    private RcoFactura objetoFactura;
    private RcoFacturaDetalle objetoFacturaDetalle;
    private RcoConciliacionGestion objetoConciliacionGestion;
    private RcoActa objetoRcoActa;
    private RcoActaAsistente objetoRcoActaAsistente;
   
    private LazyDataModel<RcoConciliacion> lazyRcoConciliaciones;
    private LazyDataModel<RcoFactura> lazyRcoFacturas;
    private LazyDataModel<RcoFacturaDetalle> lazyRcoFacturasDetalles;
    private List<CntPrestadorSede> listaPrestadores;
    private LazyDataModel<CntPrestadorSede> lazyPrestadores;
    private List<CntContrato> listaContratos;
    private LazyDataModel<CntContrato> lazyContratos;
   

    private List<RcoConciliacion> registros;
    private List<RcoFactura> registrosFacturas;
    private List<RcoFacturaDetalle> registrosFacturaDetalles;
   
    //Maestro
    private List<Maestro> listaTipoDocumento;
    private List<Maestro> listaTipoDocumentoEmpresa;
    private List<Ubicacion> listaCiudades;
    private List<Ubicacion> listaUbicaciones;
    private HashMap<Integer, Ubicacion> hashUbicaciones;
    
    //variables auxiliares
    private String mensajeGeneral;
    private List<RcoConciliacionAdjunto> listaAdjuntos;
    private List<RcoConciliacionAdjunto> listaAdjuntosEnvioCorreo;
    private List<RcoFacturaDetalle> listaFaturasDetalles;
    private RcoConciliacionAdjunto objetoAdjunto;
    private UploadedFile archivoAdjunto;
    private List<RcoActa> reporteActaConciliacion;
    
    //Variables envio correos
    private String email;
    private String asuntoCorreo;
    private String mensajeCorreo;
    
    public RecobroConciliacionBean() {
        this.objeto = new RcoConciliacion();
        Modulo _mod = super.validarModulo(Modulo.ID_RCO_CONCILIACIONES);
        //lazy principal conciliaciones
        super.addListaParamConsultas(new ParamConsulta());
        // lazy prestadores
        super.addListaParamConsultas(new ParamConsulta());
        //lazy contratos
        super.addListaParamConsultas(new ParamConsulta());
        //lazy facturas
        super.addListaParamConsultas(new ParamConsulta());
        //lazy faturas detalles
        super.addListaParamConsultas(new ParamConsulta());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyRcoConciliaciones = new LazyDataModel<RcoConciliacion>() {

                private List<RcoConciliacion> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta(0).getCantidadRegistros();
                }

                @Override
                public List<RcoConciliacion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(RcoConciliacion objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public RcoConciliacion getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (RcoConciliacion objeto : lista) {
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
    public void postConstruct() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        getRcoConciliacionServicio().cargaInicial(this);
        listar();
    }
    
    //getters and setters
    public RecobroConciliacionServicioIface getRcoConciliacionServicio() {
        return rcoConciliacionServicio;
    }

    public void setRcoConciliacionServicio(RecobroConciliacionServicioIface rcoConciliacionServicio) {
        this.rcoConciliacionServicio = rcoConciliacionServicio;
    }

    public RcoConciliacion getObjeto() {
        return objeto;
    }

    public void setObjeto(RcoConciliacion objeto) {
        this.objeto = objeto;
    }

    public RcoFactura getObjetoFactura() {
        return objetoFactura;
    }

    public void setObjetoFactura(RcoFactura objetoFactura) {
        this.objetoFactura = objetoFactura;
    }

    public RcoFacturaDetalle getObjetoFacturaDetalle() {
        return objetoFacturaDetalle;
    }

    public void setObjetoFacturaDetalle(RcoFacturaDetalle objetoFacturaDetalle) {
        this.objetoFacturaDetalle = objetoFacturaDetalle;
    }

    public RcoConciliacionGestion getObjetoConciliacionGestion() {
        return objetoConciliacionGestion;
    }

    public void setObjetoConciliacionGestion(RcoConciliacionGestion objetoConciliacionGestion) {
        this.objetoConciliacionGestion = objetoConciliacionGestion;
    }

    public LazyDataModel<RcoConciliacion> getLazyRcoConciliaciones() {
        return lazyRcoConciliaciones;
    }

    public void setLazyRcoConciliaciones(LazyDataModel<RcoConciliacion> lazyRcoConciliaciones) {
        this.lazyRcoConciliaciones = lazyRcoConciliaciones;
    }

    public LazyDataModel<RcoFactura> getLazyRcoFacturas() {
        return lazyRcoFacturas;
    }

    public void setLazyRcoFacturas(LazyDataModel<RcoFactura> lazyRcoFacturas) {
        this.lazyRcoFacturas = lazyRcoFacturas;
    }

    public LazyDataModel<RcoFacturaDetalle> getLazyRcoFacturasDetalles() {
        return lazyRcoFacturasDetalles;
    }

    public void setLazyRcoFacturasDetalles(LazyDataModel<RcoFacturaDetalle> lazyRcoFacturasDetalles) {
        this.lazyRcoFacturasDetalles = lazyRcoFacturasDetalles;
    }

    public List<RcoConciliacion> getRegistros() {
        return registros;
    }

    public void setRegistros(List<RcoConciliacion> registros) {
        this.registros = registros;
    }

    public List<RcoFactura> getRegistrosFacturas() {
        return registrosFacturas;
    }

    public void setRegistrosFacturas(List<RcoFactura> registrosFacturas) {
        this.registrosFacturas = registrosFacturas;
    }

    public List<RcoFacturaDetalle> getRegistrosFacturaDetalles() {
        return registrosFacturaDetalles;
    }

    public void setRegistrosFacturaDetalles(List<RcoFacturaDetalle> registrosFacturaDetalles) {
        this.registrosFacturaDetalles = registrosFacturaDetalles;
    }

    public List<Ubicacion> getListaUbicaciones() {
        return listaUbicaciones;
    }

    public void setListaUbicaciones(List<Ubicacion> listaUbicaciones) {
        this.listaUbicaciones = listaUbicaciones;
    }

    public HashMap<Integer, Ubicacion> getHashUbicaciones() {
        return hashUbicaciones;
    }

    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashUbicaciones) {
        this.hashUbicaciones = hashUbicaciones;
    }

    public List<CntPrestadorSede> getListaPrestadores() {
        return listaPrestadores;
    }

    public void setListaPrestadores(List<CntPrestadorSede> listaPrestadores) {
        this.listaPrestadores = listaPrestadores;
    }

    public LazyDataModel<CntPrestadorSede> getLazyPrestadores() {
        return lazyPrestadores;
    }

    public void setLazyPrestadores(LazyDataModel<CntPrestadorSede> lazyPrestadores) {
        this.lazyPrestadores = lazyPrestadores;
    }

    public List<Maestro> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    public void setListaTipoDocumento(List<Maestro> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }

    public List<Maestro> getListaTipoDocumentoEmpresa() {
        return listaTipoDocumentoEmpresa;
    }

    public void setListaTipoDocumentoEmpresa(List<Maestro> listaTipoDocumentoEmpresa) {
        this.listaTipoDocumentoEmpresa = listaTipoDocumentoEmpresa;
    }

    public List<Ubicacion> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<Ubicacion> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public List<CntContrato> getListaContratos() {
        return listaContratos;
    }

    public void setListaContratos(List<CntContrato> listaContratos) {
        this.listaContratos = listaContratos;
    }

    public LazyDataModel<CntContrato> getLazyContratos() {
        return lazyContratos;
    }

    public void setLazyContratos(LazyDataModel<CntContrato> lazyContratos) {
        this.lazyContratos = lazyContratos;
    }

    public String getMensajeGeneral() {
        return mensajeGeneral;
    }

    public void setMensajeGeneral(String mensajeGeneral) {
        this.mensajeGeneral = mensajeGeneral;
    }

    public List<RcoConciliacionAdjunto> getListaAdjuntos() {
        return listaAdjuntos;
    }

    public void setListaAdjuntos(List<RcoConciliacionAdjunto> listaAdjuntos) {
        this.listaAdjuntos = listaAdjuntos;
    }

    public List<RcoConciliacionAdjunto> getListaAdjuntosEnvioCorreo() {
        return listaAdjuntosEnvioCorreo;
    }

    public void setListaAdjuntosEnvioCorreo(List<RcoConciliacionAdjunto> listaAdjuntosEnvioCorreo) {
        this.listaAdjuntosEnvioCorreo = listaAdjuntosEnvioCorreo;
    }

    public List<RcoFacturaDetalle> getListaFaturasDetalles() {
        return listaFaturasDetalles;
    }

    public void setListaFaturasDetalles(List<RcoFacturaDetalle> listaFaturasDetalles) {
        this.listaFaturasDetalles = listaFaturasDetalles;
    }

    public RcoConciliacionAdjunto getObjetoAdjunto() {
        return objetoAdjunto;
    }

    public void setObjetoAdjunto(RcoConciliacionAdjunto objetoAdjunto) {
        this.objetoAdjunto = objetoAdjunto;
    }

    public UploadedFile getArchivoAdjunto() {
        return archivoAdjunto;
    }

    public void setArchivoAdjunto(UploadedFile archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }

    public List<RcoActa> getReporteActaConciliacion() {
        return reporteActaConciliacion;
    }

    public void setReporteActaConciliacion(List<RcoActa> reporteActaConciliacion) {
        this.reporteActaConciliacion = reporteActaConciliacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAsuntoCorreo() {
        return asuntoCorreo;
    }

    public void setAsuntoCorreo(String asuntoCorreo) {
        this.asuntoCorreo = asuntoCorreo;
    }

    public String getMensajeCorreo() {
        return mensajeCorreo;
    }

    public void setMensajeCorreo(String mensajeCorreo) {
        this.mensajeCorreo = mensajeCorreo;
    }

    public RcoActa getObjetoRcoActa() {
        return objetoRcoActa;
    }

    public void setObjetoRcoActa(RcoActa objetoRcoActa) {
        this.objetoRcoActa = objetoRcoActa;
    }

    public RcoActaAsistente getObjetoRcoActaAsistente() {
        return objetoRcoActaAsistente;
    }

    public void setObjetoRcoActaAsistente(RcoActaAsistente objetoRcoActaAsistente) {
        this.objetoRcoActaAsistente = objetoRcoActaAsistente;
    }

    //Metodos
    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getRcoConciliacionServicio().Accion(this);
    }
    
    public void refrescarContratos() {
        super.setAccion(Url.ACCION_CREAR);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        getRcoConciliacionServicio().Accion(this);
    }
    
    public void refrescarPrestador() {
        super.setAccion(Url.ACCION_CREAR);
        super.setDoAccion(Url.ACCION_LISTAR);
        getRcoConciliacionServicio().Accion(this);
    }
    
    public void refrescarFacturas() {
        getRcoConciliacionServicio().listarFacturas(this);
    }
    
    public void refrescarFacturasDetalles() {
        getRcoConciliacionServicio().listarFacturaDetalles(this);
    }
    
    public void gestionar(int id) {
        setObjeto(new RcoConciliacion(id));
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_ADICIONAL_2);
        getRcoConciliacionServicio().Accion(this);
        procesoFinal();
    }

    public void refrescarFacturaDetalle() {
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_LISTAR);
        getRcoConciliacionServicio().Accion(this);
        procesoFinal();
    }

    public void crear() {
        super.setAccion(Url.ACCION_CREAR);
        super.setDoAccion(Url.ACCION_CREAR);
        getRcoConciliacionServicio().Accion(this);        
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }
    
    public void guardar() {
        if(getObjeto().getCntPresadoresSedesId()== null){
            addError("El prestador es obligatorio");
        }
        if (!super.isError()) {
            super.setAccion(Url.ACCION_GUARDAR);
            getRcoConciliacionServicio().Accion(this);
        }
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }
    
    public void ver(int id) {
        setObjeto(new RcoConciliacion(id));
        cargaLazyFacturasDetalles();
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER);
        getRcoConciliacionServicio().Accion(this);
        if(!super.isError()){
            PrimeFaces.current().ajax().update("frmVer");
            PrimeFaces.current().executeScript("PF('dialogoVer').show();");
        }
        procesoFinal();
    }
    
    public void consultarAdjuntos(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_LISTAR);
        getRcoConciliacionServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmAdjuntos");
            PrimeFaces.current().ajax().update("frmAdjuntos");
            PrimeFaces.current().executeScript("PF('dialogoAdjuntos').show()");
        }
        procesoFinal();
    }
    
    public void crearAdjunto() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_CREAR);
        getRcoConciliacionServicio().Accion(this);
        if(!super.isError()){
            PrimeFaces.current().resetInputs("frmCrearAdjunto:panelCrearAdjunto");
            PrimeFaces.current().ajax().update("frmCrearAdjunto:panelCrearAdjunto");
            PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').show()");
        }
        procesoFinal();
    }

    public void cargarAdjunto(FileUploadEvent event) throws IOException {
        try {
            if (this.objetoAdjunto.getTipo() == null) {
                this.addError("Tipo: Este campo es obligatorio.");
                generarMensajes();
                return;
            }
            
            RcoConciliacionAdjunto adjuntoDocumento = new RcoConciliacionAdjunto();
            UploadedFile archivo = event.getFile();
            adjuntoDocumento.setAdjuntoStream(archivo.getInputStream());
            String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
            adjuntoDocumento.setNombreArchivo(nombreAdjunto);
            //2020-08-12 jyperez se incluye adicionarle los campos de evento debido a que, como estaba actualmente se cargaban siempre sobre el nuevo objeto.
            adjuntoDocumento.setRcoConciliacionId(objeto);
            adjuntoDocumento.setMaeTipoArchivoId(objetoAdjunto.getMaeTipoArchivoId());
            adjuntoDocumento.setTipo(objetoAdjunto.getTipo());
            adjuntoDocumento.setExiste(Boolean.TRUE);
            setObjetoAdjunto(adjuntoDocumento);
            //2020-08-12 jperez se adiciona el llamado a la funcionalidad que guarda el adjuto, para volver automático el proceso
            // y eliminar el botón guardar de la pantalla
            this.guardarAdjunto();
            
        } catch (IOException ex) {
            Logger.getLogger(RecobroConciliacionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardarAdjunto() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_GUARDAR);
        getRcoConciliacionServicio().Accion(this);
        if (!super.isError()) {
            // se adiciona esta opción para validar que actualice la lista de conciliaciones.
            PrimeFaces.current().ajax().update("frmConciliaciones");
            PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').hide();");
        }
        procesoFinal();
    }
    
    public void descargarAdjunto(RcoConciliacionAdjunto adjunto) {
        String rutaCompleta = adjunto.getRuta() + adjunto.getArchivo();
        this.objetoAdjunto = adjunto;
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_VER);
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
            String attachmentName = "attachment; filename=\"" + adjunto.getNombreArchivo() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".pdf")) {
                ec.setResponseContentType("application/pdf");
            } else if (ext.equalsIgnoreCase(".jpg")) {
                ec.setResponseContentType("image/jpg");
            } else if (ext.equalsIgnoreCase(".jpeg")) {
                ec.setResponseContentType("image/jpeg");
            } else if (ext.equalsIgnoreCase(".png")) {
                ec.setResponseContentType("image/png");
            } else {
                throw new Exception();
            }
            try (OutputStream output = ec.getResponseOutputStream()) {
                output.write(exportContent);
                output.close();
            }
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        }
        procesoFinal();
    }
    
    public void crearCorreo(int id) {
        setObjeto(new RcoConciliacion());
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_CREAR);
        getRcoConciliacionServicio().Accion(this);
        if(!super.isError()){
            PrimeFaces.current().resetInputs("frmEnviarCorreo");
            PrimeFaces.current().ajax().update("frmEnviarCorreo");
            PrimeFaces.current().executeScript("PF('dialogoEnviarCorreo').show()");
        }
        procesoFinal();
    }
    
    public void enviarCorreo() {
         
        if (!email.matches("^([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})(,\\s*[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})*$")) {
            addError("Correo Electrónico: no es un correo valido");
            generarMensajes();
            return;
        }
        
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_GUARDAR);
        getRcoConciliacionServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEnviarCorreo').hide();");
        }
        procesoFinal();
    }
    
    public void ventanaGestionar(int id) {
        getObjeto().setId(id);
        cargaLazyFacturasDetalles();
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_CREAR);
        getRcoConciliacionServicio().Accion(this);
        if(!super.isError()){
            PrimeFaces.current().ajax().update("frmGestionar");
            PrimeFaces.current().executeScript("PF('dialogoGestionar').show()");
        }
        procesoFinal();
    }
    
    public void verDetalledeDetalleFacturaVer(int id) {
        setObjetoFacturaDetalle(new RcoFacturaDetalle(id));
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        getRcoConciliacionServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmVerFacturaAuditada");
            PrimeFaces.current().executeScript("PF('dialogoVerFacturaAuditada').show();");
        }
        procesoFinal();
    }
    
    public void verDetalledeDetalleFactura(int id) {
        setObjetoFacturaDetalle(new RcoFacturaDetalle(id));
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_VER);
        getRcoConciliacionServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmVerFacturaAuditada");
            PrimeFaces.current().executeScript("PF('dialogoVerFacturaAuditada').show();");
        }
        procesoFinal();
    }
    
    public void verConciliacionGestionVer(int id) {
        setObjetoFacturaDetalle(new RcoFacturaDetalle(id));
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_CREAR);
        getRcoConciliacionServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmVerConciliacionGestion");
            PrimeFaces.current().executeScript("PF('dialogoVerConciliacionGestion').show();");
        }
        procesoFinal();
    }
    
    public void verConciliacionGestion(int id) {
        setObjetoConciliacionGestion(new RcoConciliacionGestion());
        getObjetoConciliacionGestion().setRcoFacturaDetallesId(new RcoFacturaDetalle(id));
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        getRcoConciliacionServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmVerConciliacionGestion");
            PrimeFaces.current().executeScript("PF('dialogoVerConciliacionGestion').show();");
        }
        procesoFinal();
    }
    
    public void crearConciliacionGestion(RcoFacturaDetalle objetoFacturaD) {
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_ADICIONAL_2);
        super.setTakeAccion(Url.ACCION_CREAR);
        getRcoConciliacionServicio().Accion(this);
        if (!super.isError()) {
            getObjetoConciliacionGestion().setRcoConciliacionesId(objeto);
            getObjetoConciliacionGestion().setRcoFacturaDetallesId(objetoFacturaD);
            PrimeFaces.current().ajax().update("frmCrearConciliacionGestion");
            PrimeFaces.current().executeScript("PF('dialogoCrearConciliacionGestion').show();");
        }
        procesoFinal();
    }
    
    public void guardarConciliacionGestion() {
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_ADICIONAL_2);
        super.setTakeAccion(Url.ACCION_GUARDAR);
        getRcoConciliacionServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmGestionar");
            PrimeFaces.current().executeScript("PF('dialogoCrearConciliacionGestion').hide();");
        }
        procesoFinal();
    }
    
    public void crearActaConciliacion(int id) {
        setObjeto(new RcoConciliacion());
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_CREAR);
        getRcoConciliacionServicio().Accion(this);
        if(!super.isError()){
            PrimeFaces.current().resetInputs("frmActaConciliacion");
            PrimeFaces.current().ajax().update("frmActaConciliacion");
            PrimeFaces.current().executeScript("PF('dialogoActaConciliacion').show()");
        }
        procesoFinal();
    }
    
    public void ventanaAsistente() {
        super.setAccion(Url.ACCION_ADICIONAL_4);
        super.setDoAccion(Url.ACCION_VER);
        getRcoConciliacionServicio().Accion(this);        
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmActaAsistente");
            PrimeFaces.current().ajax().update("frmActaAsistente");
            PrimeFaces.current().executeScript("PF('dialogoActaAsistente').show();");
        }
        procesoFinal();
    }
    
    public void guardarAsistente() {
        super.setAccion(Url.ACCION_ADICIONAL_4);
        super.setDoAccion(Url.ACCION_BORRAR);
        getRcoConciliacionServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmActaConciliacion:pAsistenteVer");
            PrimeFaces.current().executeScript("PF('dialogoActaAsistente').hide();");
        }
    }
    
    public void borrarAsistente(String nombre) {
        RcoActaAsistente borraObjecto = new RcoActaAsistente();
        for (RcoActaAsistente rcoActaAsistente : objetoRcoActa.getListaRcoActaAsistente()) {
            if (rcoActaAsistente.getNombre().equals(nombre)) {
                borraObjecto = rcoActaAsistente;
                break;
            }
        }
        objetoRcoActa.getListaRcoActaAsistente().remove(borraObjecto);
        PrimeFaces.current().ajax().update("frmActaConciliacion:pAsistenteVer");
    }
    
    public StreamedContent generarReporte(int conciliacionId) {
        StreamedContent streamContent = null;
        if(objetoRcoActa.getListaRcoActaAsistente().isEmpty()){
            addError("Los Asistentes son obligatorios");
            procesoFinal();
            return streamContent;
        }
        LocalDate fechaActl = LocalDate.now();
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_GUARDAR);
        getRcoConciliacionServicio().Accion(this);
        try {
            if (!super.isError()) {
                InputStream is = getClass().getResourceAsStream("/reportes/RcoActaConciliaciones.jasper");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource((Collection<?>)  reporteActaConciliacion);

                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));
                parameters.put(RcoActa.LISTA_ASISTENTES, new JRBeanCollectionDataSource((Collection<?>) reporteActaConciliacion.get(0).getListaRcoActaAsistente()));
               

                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0);
                RcoConciliacion conciliacion = getRcoConciliacionServicio().consultarConciliacion(getObjeto().getId(), this);
                StringBuilder nombreArchivo = new StringBuilder();
                nombreArchivo.append("rco_acta_coniliacion_");
                nombreArchivo.append(conciliacion.getCntPresadoresSedesId().getId());
                nombreArchivo.append(conciliacion.getCntPresadoresSedesId().getCodigoHabilitacionSede());
                nombreArchivo.append(getObjeto().getId());
                nombreArchivo.append("_");
                nombreArchivo.append(fechaActl.toString().replace("-", ""));
                streamContent = DefaultStreamedContent.builder().contentType("application/pdf").name(nombreArchivo + ".pdf").stream(() -> stream).build();
            }
        } catch (JRException ex) {
            streamContent = null;
            addError("Error Reporte: " + ex.toString() + ex.getMessage());
        }
        procesoFinal();
        return streamContent;
    }
    
    public void consultarPrestador() {
        if (!super.isError()) {
            cargaLazyPrestador();
            PrimeFaces.current().ajax().update("frmIpsLista");
            PrimeFaces.current().executeScript("PF('dialogoIpsLista').show()");
        }
        procesoFinal();
    }
    
    public void cargaLazyPrestador() {

        lazyPrestadores = new LazyDataModel<CntPrestadorSede>() {
            private List<CntPrestadorSede> listaPrestadores;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(1).getCantidadRegistros();
            }

            @Override
            public List<CntPrestadorSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(1).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(1).setPrimerRegistro(primerRegistro);
                getParamConsulta(1).setRegistrosPagina(registrosPagina);
                getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(1).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarPrestador();
                listaPrestadores = getListaPrestadores();
                setRowCount(getParamConsulta(1).getCantidadRegistros());
                return listaPrestadores;
            }

            @Override
            public String getRowKey(CntPrestadorSede ips) {
                return ips.getId().toString();
            }

            @Override
            public CntPrestadorSede getRowData(String ipsId) {
                Integer id = Integer.valueOf(ipsId);
                for (CntPrestadorSede ips : listaPrestadores) {
                    if (id.equals(ips.getId())) {
                        return ips;
                    }
                }
                return null;
            }
        };
    }
    
    public void onRowSelectPrestador(SelectEvent event) {
        CntPrestadorSede ips = (CntPrestadorSede) event.getObject();
        getObjeto().setCntPresadoresSedesId(ips); 
        setParamConsulta(new ParamConsulta());
        getParamConsulta(1).setFiltros(new HashMap<>());
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').hide()");
        PrimeFaces.current().ajax().update("frmCrear:pPrestadorCrear");
      
    }
    
    public void consultarContratos() {
        cargaLazyContratos();
        PrimeFaces.current().executeScript("PF('dialogoContratoLista').show()");
        PrimeFaces.current().ajax().update("frmContratoLista");
       
        generarMensajes();
    }
        
    public void cargaLazyContratos() {

        lazyContratos = new LazyDataModel<CntContrato>() {
            private List<CntContrato> listaContratos;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(2).getCantidadRegistros();
            }

            @Override
            public List<CntContrato> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(2).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(2).setPrimerRegistro(primerRegistro);
                getParamConsulta(2).setRegistrosPagina(registrosPagina);
                getParamConsulta(2).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(2).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarContratos();
                listaContratos = getListaContratos();
                setRowCount(getParamConsulta(2).getCantidadRegistros());
                return listaContratos;
            }

            @Override
            public String getRowKey(CntContrato ips) {
                return ips.getId().toString();
            }

            @Override
            public CntContrato getRowData(String ipsId) {
                Integer id = Integer.valueOf(ipsId);
                for (CntContrato ips : listaContratos) {
                    if (id.equals(ips.getId())) {
                        return ips;
                    }
                }
                return null;
            }
        };
    }
    
    public void onRowSelectContrato(SelectEvent event) {
        CntContrato contrato = (CntContrato) event.getObject();
        getObjeto().setCntContratoId(contrato);
        getObjeto().setNumeroContrato(contrato.getContrato());
        PrimeFaces.current().executeScript("PF('dialogoContratoLista').hide()");
        PrimeFaces.current().ajax().update("frmCrear:pContratroCrear");
    }
    
    public void cargaLazyFacturas() {

        lazyRcoFacturas = new LazyDataModel<RcoFactura>() {
            private List<RcoFactura> listaFacturas;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(3).getCantidadRegistros();
            }

            @Override
            public List<RcoFactura> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(3).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(3).setPrimerRegistro(primerRegistro);
                getParamConsulta(3).setRegistrosPagina(registrosPagina);
                getParamConsulta(3).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(3).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarFacturas();
                listaFacturas = getRegistrosFacturas();
                setRowCount(getParamConsulta(3).getCantidadRegistros());
                return listaFacturas;
            }

            @Override
            public String getRowKey(RcoFactura ips) {
                return ips.getId().toString();
            }

            @Override
            public RcoFactura getRowData(String ipsId) {
                Integer id = Integer.valueOf(ipsId);
                for (RcoFactura ips : listaFacturas) {
                    if (id.equals(ips.getId())) {
                        return ips;
                    }
                }
                return null;
            }
        };
    }
   
    public void cargaLazyFacturasDetalles() {

        lazyRcoFacturasDetalles = new LazyDataModel<RcoFacturaDetalle>() {
            private List<RcoFacturaDetalle> listaFacturasDetalles;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(4).getCantidadRegistros();
            }

            @Override
            public List<RcoFacturaDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(4).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(4).setPrimerRegistro(primerRegistro);
                getParamConsulta(4).setRegistrosPagina(registrosPagina);
                getParamConsulta(4).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(4).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarFacturasDetalles();
                listaFacturasDetalles = getRegistrosFacturaDetalles();
                setRowCount(getParamConsulta(4).getCantidadRegistros());
                return listaFacturasDetalles;
            }

            @Override
            public String getRowKey(RcoFacturaDetalle ips) {
                return ips.getId().toString();
            }

            @Override
            public RcoFacturaDetalle getRowData(String ipsId) {
                Integer id = Integer.valueOf(ipsId);
                for (RcoFacturaDetalle ips : listaFacturasDetalles) {
                    if (id.equals(ips.getId())) {
                        return ips;
                    }
                }
                return null;
            }
        };
    }
    
    public void mostrarMensajeGeneral(String mensaje) {
        setMensajeGeneral(mensaje);
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
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
    
    public boolean validarEstado(int estado){
        boolean validar = false;
        switch(estado){
            case RcoConciliacion.NOTIFICIACION_PRESTADOR:
                validar = true;
                break;
            case RcoConciliacion.EN_CONCILIACION:
                validar = true;
                break;
        }
        return validar; 
    }
    
    public boolean validarEstadoFinalizacion(int estado){
        boolean validar = true;
        switch(estado){
            case RcoConciliacion.FINALIZADO:
                validar = false;
                break;
        }
        return validar; 
    }
    
    public boolean validarEstadoEnvioCorreo(int estado){
        boolean validar = false;
        switch(estado){
            case RcoConciliacion.PENDIENTE_SOPORTE:
                validar = true;
                break;
        }
        return validar; 
    }
    
    public boolean validarEstadoGenerarActa(int estado){
        boolean validar = false;
        switch(estado){
            case RcoConciliacion.EN_CONCILIACION:
                validar = true;
                break;
        }
        return validar; 
    }
    
    public boolean validarTotalGestiones(int conciliacionId){
        boolean validar = true;
        List<RcoFacturaDetalle> facturasDetalles = getRcoConciliacionServicio().consultarListaFacturasDetalles(conciliacionId, this);
        for(RcoFacturaDetalle rcoFacturaDetalle:facturasDetalles ){
            if(!rcoFacturaDetalle.isEstadoConciliacion()){
                validar = false;
                break;
            }
        }
        return validar; 
    }
    
    public boolean validarSiTieneGestiones(int facturaDetalleId){
        boolean validate = false;
        RcoConciliacionGestion obj = getRcoConciliacionServicio().consultarConciliacionesGestion(facturaDetalleId, this);
        if(obj.getId() == null){
            validate = true;
        }
        return validate; 
    }
    
    public boolean validarGestion(int facturaDetalleId){
        boolean validate = false;
        RcoConciliacionGestion obj = getRcoConciliacionServicio().consultarConciliacionesGestion(facturaDetalleId, this);
        if(obj.getId() != null){
            validate = true;
        }
        return validate; 
    }
    
    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmConciliaciones");
                    crearLog("Listar");
                    break;
                case Url.ACCION_VER:
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            crearLog("Ver", getObjeto().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            crearLog("Ver Detalle Factura", getObjetoFacturaDetalle().toString());
                            break;
                         case Url.ACCION_CREAR:
                            crearLog("Ver Conciliacion Gestion", getObjetoConciliacionGestion().toString());
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    switch (getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crearLog("Crear");
                            break;
                        case Url.ACCION_LISTAR:
                            break;
                        case Url.ACCION_EDITAR:
                            break; 
                        case Url.ACCION_ADICIONAL_1:
                            break;
                    }
                    break;
                case Url.ACCION_GUARDAR:
                    PrimeFaces.current().ajax().update("frmConciliaciones");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crearLog("Gestionar");
                            break;
                        case Url.ACCION_VER:
                            crearLog("Ver Detalle Factura", getObjetoFacturaDetalle().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            crearLog("Ver Conciliación Gestion", getObjetoConciliacionGestion().toString());
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            switch (getTakeAccion()) {
                                case Url.ACCION_CREAR:
                                    crearLog("Crear Conciliación Gestion");
                                    break;
                                case Url.ACCION_GUARDAR:
                                    PrimeFaces.current().ajax().update("frmConciliaciones");
                                    crearLog("Guardar Conciliación Gestion", getObjetoConciliacionGestion().toString());
                                    break;
                            }
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            crearLog("Consultar Adjuntos");
                            break;
                        case Url.ACCION_CREAR:
                            crearLog("Crear Adjunto");
                            break; 
                        case Url.ACCION_GUARDAR:
                            crearLog("Guardar Adjunto", getObjetoAdjunto().toString());
                            break;
                        case Url.ACCION_VER:
                            crearLog("Descargar Adjunto");
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crearLog("Crear Correo");
                            break; 
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().ajax().update("frmConciliaciones");
                            crearLog("Envio Correo", getObjeto().toString());
                            break;
                        case Url.ACCION_VER:
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch (getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crearLog("Crear Acta");
                            break; 
                        case Url.ACCION_GUARDAR:
                            crearLog("Descargar Acta");
                            PrimeFaces.current().ajax().update("frmConciliaciones");
                            break;
                        case Url.ACCION_VER:
                            crearLog("Crear Asistente");
                            break;
                    }
                    break;
                    
            }
        }
        generarMensajes();
    }
}
