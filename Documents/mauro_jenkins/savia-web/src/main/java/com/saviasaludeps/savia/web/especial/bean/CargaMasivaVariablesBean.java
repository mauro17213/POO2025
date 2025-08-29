/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.especial.bean;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.especial.PeCargaVariable;
import com.saviasaludeps.savia.dominio.especial.PeIpsPrograma;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.contratacion.seleccion.bean.SelPrestadorBean;
import com.saviasaludeps.savia.web.especial.servicio.CargaMasivaVariablesServicioIface;
import com.saviasaludeps.savia.web.especial.utilidades.PeConstantes;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_CREAR;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.FileUploadEvent;
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
 * @author jdlopez
 */
@ManagedBean
@ViewScoped
public class CargaMasivaVariablesBean extends Url {

    @Autowired
    private CargaMasivaVariablesServicioIface cargaMasivaVariablesServicio;
    private PeCargaVariable objeto;
    private List<PePrograma> programas;
    private List<PeCargaVariable> registros;
    private LazyDataModel<PeCargaVariable> lazyCargaMasiva;
    private LazyDataModel<CntPrestadorSede> lazyIps;
    private List<CntPrestadorSede> listaIps;
    private List<Maestro> listaEstadoCarga;
    private List<Maestro> listaPeriodoCarga;
    private List<Maestro> listaMeses;
    private List<Maestro> listaMesesCarga;
    private transient InputStream adjuntoStream;

    private CntPrestador prestadorSelec;

    //objeto Buscador Prestador
    private SelPrestadorBean selPrestadorBean;
    private PeIpsPrograma objetoIps;
    private boolean errorEnGeneracionCertificado = false;

    /*
     Metodos de inicializacion
     */
    public CargaMasivaVariablesBean() {
        Modulo mod = super.validarModulo(Modulo.ID_PROGRAMA_ESPECIAL_CARGA_MASIVA_VARIABLES_ESPECIFICAS);

        super.addListaParamConsultas(new ParamConsulta());
        if (mod == null) {
            super.redireccionar("/savia/home.faces");

        } else {
            super.setModulo(mod);
            if (!super.getConexion().getEmpresa().isAdministradora()) {
                super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            }
            lazyCargaMasiva = new LazyDataModel<PeCargaVariable>() {
                private List<PeCargaVariable> listaCargas;

                @Override
                public int count(Map<String, FilterMeta> map) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<PeCargaVariable> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    listar();
                    listaCargas = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaCargas;
                }

                @Override
                public String getRowKey(PeCargaVariable carga) {
                    return carga.getId().toString();
                }

                @Override
                public PeCargaVariable getRowData(String idStr) {
                    Integer id = Integer.valueOf(idStr);
                    for (PeCargaVariable carga : listaCargas) {
                        if (id.equals(carga.getId())) {
                            return carga;
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
        cargaMasivaVariablesServicio.cargaInicial(this);
    }

    /*
     Metodos de logica y eventos
     */
    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        cargaMasivaVariablesServicio.Accion(this);
        this.procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        cargaMasivaVariablesServicio.Accion(this);
        PrimeFaces.current().resetInputs("frmCrearAdjunto");
        PrimeFaces.current().ajax().update("frmCrearAdjunto");
        PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').show()");
        procesoFinal();
    }

    public void cargarAdjunto(FileUploadEvent event) {
        try {
            if (this.getObjeto().getPrograma().getId() != null) {//verificca el id programa

                if (this.getObjeto().getPeriodoCargue() != null) {//verifica el periodo

                    if (cargaMasivaVariablesServicio.verificarCierreCarga(this)) {//verifica si no hay un cierre de cargue activo
                        UploadedFile archivo = event.getFile();
                        if (this.validarNombreArchivo(FilenameUtils.getBaseName(event.getFile().getFileName()))) {

                            this.getObjeto().setAdjuntoStream(archivo.getInputStream());
                            this.getObjeto().setNombre(FilenameUtils.getName(event.getFile().getFileName()));
                            // llamamos al guardar
                            if (this.getObjeto().getAdjuntoStream() != null) {
                                super.setAccion(ACCION_GUARDAR);
                                cargaMasivaVariablesServicio.Accion(this);
                                PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').hide()");
                            } else {
                                this.addError("No se ha cargado el archivo.");
                            }
                        } else {
                            addMensaje("El nombre del archivo debe cumplir con el formato RCV_AAAAMMDD donde el año y mes sea igual al periodo de carga con el ultimo dia correspondiente. ");
                            PrimeFaces.current().ajax().update("frmCrearAdjunto");
                        }
                    } else {
                        this.addError("El proceso de cargue para el periodo con corte " + this.getFechaPeriodo(this.getObjeto().getPeriodoCargue()) + " ha culminado.");
                        PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').hide()");
                    }
                } else {
                    addMensaje("Debe seleccionar el periodo de carga. ");
                    PrimeFaces.current().ajax().update("frmCrearAdjunto");
                }
            } else {
                addMensaje("Debe seleccionar el programa especial al cual realizara la carga. ");
                PrimeFaces.current().ajax().update("frmCrearAdjunto");
            }
        } catch (IOException ex) {
            addError(ex.toString());
        }
        procesoFinal();
    }

    private void descargarArchivo(String rutaCompleta, String nombreArchivo) {
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
            String attachmentName = "attachment; filename=\"" + nombreArchivo + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".txt")) {
                ec.setResponseContentType("text/plain");
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

    public void generarArchivoResultados(int idcarga) {
        this.setObjeto(new PeCargaVariable(idcarga));
        super.setAccion(ACCION_ADICIONAL_1);
        cargaMasivaVariablesServicio.Accion(this);
        if (objeto.getRespExiste()) {
            String rutaCompleta = this.objeto.getRespRuta() + this.objeto.getRespArchivo();
            descargarArchivo(rutaCompleta, this.objeto.getRespArchivo());
        } else {
            this.addError("El archivo de resultados no existe en la ruta de descarga.");
        }
        procesoFinal();
    }

    public void generarArchivoCarga(int idcarga) {
        this.setObjeto(new PeCargaVariable(idcarga));
        super.setAccion(ACCION_ADICIONAL_1);
        cargaMasivaVariablesServicio.Accion(this);
        if (objeto.getExiste()) {
            String rutaCompleta = this.objeto.getRuta() + this.objeto.getArchivo();
            descargarArchivo(rutaCompleta, this.objeto.getArchivo());
        } else {
            this.addError("El archivo de carga no existe en la ruta de descarga.");
        }
        procesoFinal();
    }

    public boolean validarEstadoCargaProcesando(int estado) {
        boolean deshabilitar = true;
        if (estado == PeConstantes.ESTADO_EN_PROCESO) {
            deshabilitar = false;
        }
        return deshabilitar;
    }

    public void abortar(int id) {
        super.setAccion(ACCION_ADICIONAL_2);
        this.objeto = new PeCargaVariable(id);
        cargaMasivaVariablesServicio.Accion(this);
        procesoFinal();
    }

    public String getEstado(Integer tipo) {
        return PeConstantes.obtenerEstadoCargaMasivaStr(tipo);
    }

    public String getPeriodoStr(int periodo) {
        if (periodo < 1 || periodo > 12) {
            return "";
        }
        Month mes = Month.of(periodo);
        return mes.getDisplayName(TextStyle.FULL, new Locale("es", "CO")).toUpperCase();
    }

    private boolean validarNombreArchivo(String nombreArchivo) {
        String regex = "^RCV_\\d{8}$";
        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(nombreArchivo).matches()) {
            return false;
        }

        String fechaStr = nombreArchivo.substring(4);
        try {
            SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd");
            formateador.setLenient(false);
            Date fechaExtraida = formateador.parse(fechaStr);

            Calendar calendarioFechaExtraida = Calendar.getInstance();
            calendarioFechaExtraida.setTime(fechaExtraida);
            int anioExtraido = calendarioFechaExtraida.get(Calendar.YEAR);

            Calendar calendarioActual = Calendar.getInstance();
            calendarioActual.setTime(new Date());
            int anioActual = calendarioActual.get(Calendar.YEAR);
            int mesActual = calendarioActual.get(Calendar.MONTH);
            if (mesActual == Calendar.JANUARY) {//cuando sea el mes de enero permitira el año anterior 
                int anioAnterior = anioActual - 1;
                if (anioExtraido != anioAnterior) {
                    return false;
                }
            }

            if (mesActual != Calendar.JANUARY) {
                if (anioExtraido != anioActual) {
                    return false;
                }
            }
            int mesExtraido = calendarioFechaExtraida.get(Calendar.MONTH) + 1;//Calendar entrega los meses con el formato 0 - 11
            if (mesExtraido != this.getObjeto().getPeriodoCargue()) {
                return false;
            }
            int diaExtraido = calendarioFechaExtraida.get(Calendar.DAY_OF_MONTH);
            calendarioActual.add(Calendar.MONTH, -1);//nos devolvemos un mes atras
            calendarioActual.set(Calendar.DAY_OF_MONTH, calendarioActual.getActualMaximum(Calendar.DAY_OF_MONTH));//tomamos el ultimo dia del mes en el que estemos
            int ultimoDiaPeriodoCargue = calendarioActual.get(Calendar.DAY_OF_MONTH);

            if (diaExtraido != ultimoDiaPeriodoCargue) {
                return false;
            }

        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * Obtiene la fecha a partir del periodo (1-12)
     */
    private String getFechaPeriodo(Integer periodoCargue) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, periodoCargue - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        Date fecha = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(fecha);
    }

 public void validarYPrepararCertificado() {
    try {
        if (this.objeto.getPeriodoCargue() == null) {
            addError("Debe seleccionar un periodo de carga.");
            generarMensajes();
            FacesContext.getCurrentInstance().validationFailed();
            return;
        }

        if (this.objetoIps == null) {
            addError("Debe seleccionar un prestador.");
            generarMensajes();
            FacesContext.getCurrentInstance().validationFailed();
            return;
        }

        // ⚠️ Intentar generar el certificado
        StreamedContent certificado;
        try {
            certificado = this.generarCertificadoPorPeriodo();
        } catch (Exception e) {
            // Solo mostrar mensaje 1 vez (si es que no fue manejado internamente)
            addError("Ocurrió un error al generar el certificado.");
            generarMensajes();
            FacesContext.getCurrentInstance().validationFailed();
            return;
        }

        if (certificado == null) {
            FacesContext.getCurrentInstance().validationFailed(); // Detiene flujo Ajax
            return;
        }

        // Guardar en sesión (si estás usando sessionMap)
        FacesContext.getCurrentInstance().getExternalContext()
            .getSessionMap().put("certificadoGenerado", certificado);

        // Lanzar el botón oculto para la descarga
        PrimeFaces.current().executeScript("document.getElementById('frmGenerarCertificado:hiddenDownloadBtn').click();");

    } catch (Exception e) {
        // Mensaje genérico por si algo se escapa
        addError("Error inesperado al generar el certificado.");
        generarMensajes();
        FacesContext.getCurrentInstance().validationFailed();
    }
}

  public StreamedContent generarCertificadoPorPeriodo() {
    try {
        this.setAdjuntoStream(null);
        this.reiniciarEstadoCertificado(); // limpiar bandera antes

        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_CREAR);
        cargaMasivaVariablesServicio.Accion(this);

        InputStream stream = this.getAdjuntoStream();

        if (stream == null) {
            if (!this.isErrorEnGeneracionCertificado()) { // ✅ solo si no hubo error controlado
                addError("No se pudo generar el certificado.");
                generarMensajes();
            }
            return null;
        }

        stream.mark(0);
        String nombreArchivo = this.obtenerNombreArchivoCertificado();

        return DefaultStreamedContent.builder()
                .contentType("application/pdf")
                .name(nombreArchivo)
                .stream(() -> {
                    try {
                        return this.getAdjuntoStream();
                    } finally {
                        this.setAdjuntoStream(null);
                    }
                })
                .build();

    } catch (Exception ex) {
        if (!this.isErrorEnGeneracionCertificado()) {
            addError("Error inesperado al generar el certificado.");
            generarMensajes();
        }
        return null;
    } finally {
        procesoFinal();
    }
}



    public void reiniciarFormularioGenerarCertificado() {
        this.objeto.setPeriodoCargue(null);
        this.objetoIps = null;
        this.setAdjuntoStream(null);
    }

    public void generarCertificado() {
        this.objeto.setEmpresa(super.getConexion().getEmpresa());
        PrimeFaces.current().ajax().update("frmGenerarCertificado");
        PrimeFaces.current().executeScript("PF('dialogoGenerarCertificado').show()");
    }

    private String obtenerNombreArchivoCertificado() {
        String nombre = "PRECURSORAS_%s_Certificado.pdf";
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(this.getObjeto().getFechaHoraCrea());
        calendario.set(Calendar.MONTH, this.getObjeto().getPeriodoCargue() - 1);
        calendario.set(Calendar.DAY_OF_MONTH, calendario.getActualMaximum(Calendar.DAY_OF_MONTH));
        String fechaFormateada = PeConstantes.formato7.format(calendario.getTime());
        String nombreArchivo = String.format(nombre, fechaFormateada);
        return nombreArchivo;
    }

    public void refrescarIps() {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_LISTAR);
        cargaMasivaVariablesServicio.Accion(this);
    }

    public void seleccionarIps() {
        cargarLazyIps();
    }

    private void cargarLazyIps() {
        lazyIps = new LazyDataModel<CntPrestadorSede>() {
            private List<CntPrestadorSede> listaIps;
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(0).getCantidadRegistros();
            }
            
            @Override
            public List<CntPrestadorSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(0).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(0).setPrimerRegistro(primerRegistro);
                getParamConsulta(0).setRegistrosPagina(registrosPagina);
                getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarIps();
                listaIps = getListaIps();
                setRowCount(getParamConsulta(0).getCantidadRegistros());
                return listaIps;
            }

            @Override
            public String getRowKey(CntPrestadorSede ips) {
                return ips.getId().toString();
            }

            @Override
            public CntPrestadorSede getRowData(String ipsId) {
                Integer id = Integer.valueOf(ipsId);
                for (CntPrestadorSede ips : listaIps) {
                    if (id.equals(ips.getId())) {
                        return ips;
                    }
                }
                return null;
            }
        };
    }

    public void onRowSelectIps(SelectEvent event) {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_GUARDAR);
        cargaMasivaVariablesServicio.Accion(this);
        CntPrestadorSede ips = (CntPrestadorSede) event.getObject();
        getObjetoIps().setCntPrestadorSedesId(ips);
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').hide()");
        PrimeFaces.current().ajax().update("frmGenerarCertificado");
    }

    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_VER:
            case Url.ACCION_CREAR:
            case Url.ACCION_EDITAR:
                break;
            case Url.ACCION_GUARDAR:
                PrimeFaces.current().ajax().update("frmTablaCarga");
                break;
            case Url.ACCION_MODIFICAR:
            case Url.ACCION_BORRAR:
                break;
            case Url.ACCION_LISTAR:
                break;
            case Url.ACCION_ADICIONAL_3:
                switch (getDoAccion()) {
                    case Url.ACCION_LISTAR:
                    case Url.ACCION_CREAR:
                    case Url.ACCION_GUARDAR:
                }
                break;

        }
        generarMensajes();
    }

    /*
     Metodos getters y setters de atributos relacionados al bean
     */
    public PeCargaVariable getObjeto() {
        return objeto;
    }

    public void setObjeto(PeCargaVariable objeto) {
        this.objeto = objeto;
    }

    public List<PePrograma> getProgramas() {
        return programas;
    }

    public void setProgramas(List<PePrograma> programas) {
        this.programas = programas;
    }

    public LazyDataModel<PeCargaVariable> getLazyCargaMasiva() {
        return lazyCargaMasiva;
    }

    public void setLazyCargaMasiva(LazyDataModel<PeCargaVariable> lazyCargaMasiva) {
        this.lazyCargaMasiva = lazyCargaMasiva;
    }

    public List<PeCargaVariable> getRegistros() {
        return registros;
    }

    public void setRegistros(List<PeCargaVariable> registros) {
        this.registros = registros;
    }

    public List<Maestro> getListaEstadoCarga() {
        return listaEstadoCarga;
    }

    public void setListaEstadoCarga(List<Maestro> listaEstadoCarga) {
        this.listaEstadoCarga = listaEstadoCarga;
    }

    public List<Maestro> getListaPeriodoCarga() {
        return listaPeriodoCarga;
    }

    public void setListaPeriodoCarga(List<Maestro> listaPeriodoCarga) {
        this.listaPeriodoCarga = listaPeriodoCarga;
    }

    public List<Maestro> getListaMeses() {
        return listaMeses;
    }

    public void setListaMeses(List<Maestro> listaMeses) {
        this.listaMeses = listaMeses;
    }

    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }

    public List<Maestro> getListaMesesCarga() {
        return listaMesesCarga;
    }

    public void setListaMesesCarga(List<Maestro> listaMesesCarga) {
        this.listaMesesCarga = listaMesesCarga;
    }

    /**
     * @return the selPrestadorBean
     */
    public SelPrestadorBean getSelPrestadorBean() {
        selPrestadorBean = (SelPrestadorBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selPrestadorBean");
        return selPrestadorBean;
    }

    /**
     * @param selPrestadorBean the selPrestadorBean to set
     */
    public void setSelPrestadorBean(SelPrestadorBean selPrestadorBean) {
        this.selPrestadorBean = selPrestadorBean;
    }

    public CntPrestador getPrestadorSelec() {
        return prestadorSelec;
    }

    public void setPrestadorSelec(CntPrestador prestadorSelec) {
        this.prestadorSelec = prestadorSelec;
    }

    public LazyDataModel<CntPrestadorSede> getLazyIps() {
        return lazyIps;
    }

    public void setLazyIps(LazyDataModel<CntPrestadorSede> lazyIps) {
        this.lazyIps = lazyIps;
    }

    public List<CntPrestadorSede> getListaIps() {
        return listaIps;
    }

    public void setListaIps(List<CntPrestadorSede> listaIps) {
        this.listaIps = listaIps;
    }

    public PeIpsPrograma getObjetoIps() {
        return objetoIps;
    }

    public void setObjetoIps(PeIpsPrograma objetoIps) {
        this.objetoIps = objetoIps;
    }
    
  public void marcarErrorCertificado() {
    this.errorEnGeneracionCertificado = true;
}

public boolean isErrorEnGeneracionCertificado() {
    return errorEnGeneracionCertificado;
}

public void reiniciarEstadoCertificado() {
    this.errorEnGeneracionCertificado = false;
}


}
