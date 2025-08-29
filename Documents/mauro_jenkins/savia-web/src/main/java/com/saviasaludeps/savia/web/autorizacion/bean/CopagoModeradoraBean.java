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
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.configuracionSistema.CsCopagoModeradoraHistorico;
import com.saviasaludeps.savia.dominio.configuracionSistema.CsTopeCopago;
import com.saviasaludeps.savia.dominio.configuracionSistema.CsTopeModeradora;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaPaquete;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.dominio.tutela.TuAdjunto;
import com.saviasaludeps.savia.dominio.tutela.TuDiagnostico;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaRespuesta;
import com.saviasaludeps.savia.web.autorizacion.servicio.CopagoModeradoraIface;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelInsumosBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelMedicamentoBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelPaquetesBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelTecnologiasBean;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author iavenegas
 */
@ManagedBean
@ViewScoped
public class CopagoModeradoraBean extends Url {

    CopagoModeradoraIface copagoModeradoraServicio;

    private SelTecnologiasBean tecnologiasBean;
    private SelMedicamentoBean medicamentosBean;
    private SelInsumosBean insumosBean;
    private SelPaquetesBean paquetesBean;

    private AsegAfiliado objeto;
    private TuTutelaRespuesta tuTutelaRespuesta;
    private CntContratoDetalle cntContratoDetalle;
    private AuAnexo3Item objetoTecnologia;

    private List<Maestro> listaTiposDocumento;
    private List<Maestro> listaCategoria;
    private List<Maestro> listaTipoAmbito;
    private List<PePrograma> listaProgramasEspeciales;
    private List<CsTopeCopago> listaTopeCopagos;
    private List<CsTopeModeradora> listaTopeModeradoras;
    private LazyDataModel<CntContratoDetalle> lazyContratoDetalles;
    private List<CntContratoDetalle> listaContratosDetalles;
    private LazyDataModel<CsCopagoModeradoraHistorico> lazyCopagoModeradoraHistorico;
    private List<CsCopagoModeradoraHistorico> listaCopagoModeradoraHistorico;
    private List<TuTutelaRespuesta> listaTutelas;

    private HashMap<Integer, Maestro> hashTiposDocumento;
    private HashMap<String, Maestro> hashCategoria;
    private HashMap<Integer, Ubicacion> hashUbicaciones;

    private String aplicaCuotaModeradora;
    private String apliacaTopeAfiliado;
    private String aplicaCopago;
    private String aplicaSM;
    private String aplicaSC;
    private String aplicaCM;
    private String aplicaCC;
    private String codigoAmbito;
    private BigDecimal valorCuotaModeradora;
    private BigDecimal valorCopago;
    private BigDecimal porcentajeRecuperacion;
    private BigDecimal totalCopagoHistorico;
    private BigDecimal totalModeradoraHistorico;
    private boolean aplicaProgramasEspecial;
    private boolean aplicaTutelas;
    private int anioActual;

    public CopagoModeradoraBean() {
        this.objeto = new AsegAfiliado();
        Modulo _mod = super.validarModulo(Modulo.ID_AUTORIZACIONES_VALIDAR_COBROS);
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.getParamConsulta(0).setEmpresaId(super.getConexion().getEmpresa().getId() == 1 ? null : super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            LocalDate hoy = LocalDate.now();
            setAnioActual(hoy.getYear());
            //contratos
            lazyContratoDetalles = new LazyDataModel<CntContratoDetalle>() {
                private List<CntContratoDetalle> listaContrato;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CntContratoDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(0).setEmpresaId(getConexion().getEmpresa().getId());
                    getParamConsulta(0).setPrimerRegistro(primerRegistro);
                    getParamConsulta(0).setRegistrosPagina(registrosPagina);
                    getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));

                    getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarContratoDetalle();
                    listaContrato = getListaContratosDetalles();
                    setRowCount(getParamConsulta(0).getCantidadRegistros());
                    return listaContrato;
                }

                @Override
                public String getRowKey(CntContratoDetalle ips) {
                    return ips.getId().toString();
                }

                @Override
                public CntContratoDetalle getRowData(String ipsId) {
                    Integer id = Integer.valueOf(ipsId);
                    for (CntContratoDetalle contrato : listaContrato) {
                        if (id.equals(contrato.getId())) {
                            return contrato;
                        }
                    }
                    return null;
                }
            };
            //copago moderadora historico
            lazyCopagoModeradoraHistorico = new LazyDataModel<CsCopagoModeradoraHistorico>() {
                private List<CsCopagoModeradoraHistorico> listaCopagoModeradora;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CsCopagoModeradoraHistorico> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(1).setEmpresaId(getConexion().getEmpresa().getId());
                    getParamConsulta(1).setPrimerRegistro(primerRegistro);
                    getParamConsulta(1).setRegistrosPagina(registrosPagina);
                    getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(1).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarCopagoModeradoraHistorico();
                    listaCopagoModeradora = getListaCopagoModeradoraHistorico();
                    setRowCount(getParamConsulta(1).getCantidadRegistros());
                    return listaCopagoModeradora;
                }

                @Override
                public String getRowKey(CsCopagoModeradoraHistorico ips) {
                    return ips.getId().toString();
                }

                @Override
                public CsCopagoModeradoraHistorico getRowData(String ipsId) {
                    Integer id = Integer.valueOf(ipsId);
                    for (CsCopagoModeradoraHistorico contrato : listaCopagoModeradora) {
                        if (id.equals(contrato.getId())) {
                            return contrato;
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
        getCopagoModeradoraServicio().cargaInicial(this);
        listar();
    }

    public SelTecnologiasBean getTecnologiasBean() {
        tecnologiasBean = (SelTecnologiasBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selTecnologiasBean");
        return tecnologiasBean;
    }

    public void setTecnologiasBean(SelTecnologiasBean tecnologiasBean) {
        this.tecnologiasBean = tecnologiasBean;
    }

    public SelMedicamentoBean getMedicamentosBean() {
        medicamentosBean = (SelMedicamentoBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selMedicamentosBean");
        return medicamentosBean;
    }

    public void setMedicamentosBean(SelMedicamentoBean medicamentosBean) {
        this.medicamentosBean = medicamentosBean;
    }

    public SelInsumosBean getInsumosBean() {
        insumosBean = (SelInsumosBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selInsumosBean");
        return insumosBean;
    }

    public void setInsumosBean(SelInsumosBean insumosBean) {
        this.insumosBean = insumosBean;
    }

    public SelPaquetesBean getPaquetesBean() {
        paquetesBean = (SelPaquetesBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selPaquetesBean");
        return paquetesBean;
    }

    public void setPaquetesBean(SelPaquetesBean paquetesBean) {
        this.paquetesBean = paquetesBean;
    }

    public CopagoModeradoraIface getCopagoModeradoraServicio() {
        return copagoModeradoraServicio;
    }

    public void setCopagoModeradoraServicio(CopagoModeradoraIface copagoModeradoraServicio) {
        this.copagoModeradoraServicio = copagoModeradoraServicio;
    }

    public AsegAfiliado getObjeto() {
        return objeto;
    }

    public void setObjeto(AsegAfiliado objeto) {
        this.objeto = objeto;
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

    public List<TuTutelaRespuesta> getListaTutelas() {
        return listaTutelas;
    }

    public void setListaTutelas(List<TuTutelaRespuesta> listaTutelas) {
        this.listaTutelas = listaTutelas;
    }

    public TuTutelaRespuesta getTuTutelaRespuesta() {
        return tuTutelaRespuesta;
    }

    public void setTuTutelaRespuesta(TuTutelaRespuesta tuTutelaRespuesta) {
        this.tuTutelaRespuesta = tuTutelaRespuesta;
    }

    public List<PePrograma> getListaProgramasEspeciales() {
        return listaProgramasEspeciales;
    }

    public void setListaProgramasEspeciales(List<PePrograma> listaProgramasEspeciales) {
        this.listaProgramasEspeciales = listaProgramasEspeciales;
    }

    public CntContratoDetalle getCntContratoDetalle() {
        return cntContratoDetalle;
    }

    public void setCntContratoDetalle(CntContratoDetalle cntContratoDetalle) {
        this.cntContratoDetalle = cntContratoDetalle;
    }

    public LazyDataModel<CntContratoDetalle> getLazyContratoDetalles() {
        return lazyContratoDetalles;
    }

    public void setLazyContratoDetalles(LazyDataModel<CntContratoDetalle> lazyContratoDetalles) {
        this.lazyContratoDetalles = lazyContratoDetalles;
    }

    public List<CntContratoDetalle> getListaContratosDetalles() {
        return listaContratosDetalles;
    }

    public void setListaContratosDetalles(List<CntContratoDetalle> listaContratosDetalles) {
        this.listaContratosDetalles = listaContratosDetalles;
    }

    public AuAnexo3Item getObjetoTecnologia() {
        return objetoTecnologia;
    }

    public void setObjetoTecnologia(AuAnexo3Item objetoTecnologia) {
        this.objetoTecnologia = objetoTecnologia;
    }

    public HashMap<Integer, Ubicacion> getHashUbicaciones() {
        return hashUbicaciones;
    }

    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashUbicaciones) {
        this.hashUbicaciones = hashUbicaciones;
    }

    public String getAplicaCuotaModeradora() {
        return aplicaCuotaModeradora;
    }

    public void setAplicaCuotaModeradora(String aplicaCuotaModeradora) {
        this.aplicaCuotaModeradora = aplicaCuotaModeradora;
    }

    public String getApliacaTopeAfiliado() {
        return apliacaTopeAfiliado;
    }

    public void setApliacaTopeAfiliado(String apliacaTopeAfiliado) {
        this.apliacaTopeAfiliado = apliacaTopeAfiliado;
    }

    public String getAplicaCopago() {
        return aplicaCopago;
    }

    public void setAplicaCopago(String aplicaCopago) {
        this.aplicaCopago = aplicaCopago;
    }

    public BigDecimal getValorCuotaModeradora() {
        return valorCuotaModeradora;
    }

    public void setValorCuotaModeradora(BigDecimal valorCuotaModeradora) {
        this.valorCuotaModeradora = valorCuotaModeradora;
    }

    public BigDecimal getValorCopago() {
        return valorCopago;
    }

    public void setValorCopago(BigDecimal valorCopago) {
        this.valorCopago = valorCopago;
    }

    public boolean isAplicaProgramasEspecial() {
        return aplicaProgramasEspecial;
    }

    public void setAplicaProgramasEspecial(boolean aplicaProgramasEspecial) {
        this.aplicaProgramasEspecial = aplicaProgramasEspecial;
    }

    public boolean isAplicaTutelas() {
        return aplicaTutelas;
    }

    public void setAplicaTutelas(boolean aplicaTutelas) {
        this.aplicaTutelas = aplicaTutelas;
    }

    public BigDecimal getPorcentajeRecuperacion() {
        return porcentajeRecuperacion;
    }

    public void setPorcentajeRecuperacion(BigDecimal porcentajeRecuperacion) {
        this.porcentajeRecuperacion = porcentajeRecuperacion;
    }

    public List<Maestro> getListaTipoAmbito() {
        return listaTipoAmbito;
    }

    public void setListaTipoAmbito(List<Maestro> listaTipoAmbito) {
        this.listaTipoAmbito = listaTipoAmbito;
    }

    public String getCodigoAmbito() {
        return codigoAmbito;
    }

    public void setCodigoAmbito(String codigoAmbito) {
        this.codigoAmbito = codigoAmbito;
    }

    public String getAplicaSM() {
        return aplicaSM;
    }

    public void setAplicaSM(String aplicaSM) {
        this.aplicaSM = aplicaSM;
    }

    public String getAplicaSC() {
        return aplicaSC;
    }

    public void setAplicaSC(String aplicaSC) {
        this.aplicaSC = aplicaSC;
    }

    public String getAplicaCM() {
        return aplicaCM;
    }

    public void setAplicaCM(String aplicaCM) {
        this.aplicaCM = aplicaCM;
    }

    public String getAplicaCC() {
        return aplicaCC;
    }

    public void setAplicaCC(String aplicaCC) {
        this.aplicaCC = aplicaCC;
    }

    public List<CsTopeCopago> getListaTopeCopagos() {
        return listaTopeCopagos;
    }

    public void setListaTopeCopagos(List<CsTopeCopago> listaTopeCopagos) {
        this.listaTopeCopagos = listaTopeCopagos;
    }

    public LazyDataModel<CsCopagoModeradoraHistorico> getLazyCopagoModeradoraHistorico() {
        return lazyCopagoModeradoraHistorico;
    }

    public void setLazyCopagoModeradoraHistorico(LazyDataModel<CsCopagoModeradoraHistorico> lazyCopagoModeradoraHistorico) {
        this.lazyCopagoModeradoraHistorico = lazyCopagoModeradoraHistorico;
    }

    public List<CsCopagoModeradoraHistorico> getListaCopagoModeradoraHistorico() {
        return listaCopagoModeradoraHistorico;
    }

    public void setListaCopagoModeradoraHistorico(List<CsCopagoModeradoraHistorico> listaCopagoModeradoraHistorico) {
        this.listaCopagoModeradoraHistorico = listaCopagoModeradoraHistorico;
    }

    public int getAnioActual() {
        return anioActual;
    }

    public void setAnioActual(int anioActual) {
        this.anioActual = anioActual;
    }

    public BigDecimal getTotalCopagoHistorico() {
        return totalCopagoHistorico;
    }

    public void setTotalCopagoHistorico(BigDecimal totalCopagoHistorico) {
        this.totalCopagoHistorico = totalCopagoHistorico;
    }

    public BigDecimal getTotalModeradoraHistorico() {
        return totalModeradoraHistorico;
    }

    public void setTotalModeradoraHistorico(BigDecimal totalModeradoraHistorico) {
        this.totalModeradoraHistorico = totalModeradoraHistorico;
    }

    public List<Maestro> getListaCategoria() {
        return listaCategoria;
    }

    public void setListaCategoria(List<Maestro> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public HashMap<String, Maestro> getHashCategoria() {
        return hashCategoria;
    }

    public void setHashCategoria(HashMap<String, Maestro> hashCategoria) {
        this.hashCategoria = hashCategoria;
    }

    public List<CsTopeModeradora> getListaTopeModeradoras() {
        return listaTopeModeradoras;
    }

    public void setListaTopeModeradoras(List<CsTopeModeradora> listaTopeModeradoras) {
        this.listaTopeModeradoras = listaTopeModeradoras;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
//                    crearLog(getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmCopagoModeradora");
                    break;
                case Url.ACCION_VER:
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            PrimeFaces.current().ajax().update("frmVer");
                            PrimeFaces.current().executeScript("PF('dialogoVer').show()");
                            crearLog(getObjeto().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            PrimeFaces.current().ajax().update("frmVer:panelCobros");
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
            }
        }
        generarMensajes();
    }

    public void ver() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_VER);
        getCopagoModeradoraServicio().Accion(this);
        procesoFinal();
    }

    public void resetFormulario() {
        setObjetoTecnologia(null);
        setCntContratoDetalle(null);
        setObjeto(new AsegAfiliado());
        PrimeFaces.current().ajax().update("frmCopagoModeradora");
    }

    public void resetFormularioTecnologia() {
        setObjetoTecnologia(null);
        setCntContratoDetalle(null);
        PrimeFaces.current().ajax().update("frmCopagoModeradora");
    }

    public void refrescarValores() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        getCopagoModeradoraServicio().Accion(this);
        procesoFinal();
    }

    public String diagnosticoPrincipalTutela(TuTutelaRespuesta tutela) {
        String tuDiagnostico = "";
        if (!tutela.getDiagnosticos().isEmpty()) {
            tuDiagnostico = tutela.getDiagnosticos().stream()
                    .filter(diag -> diag.isEsPrincipal())
                    .findFirst().orElse(new TuDiagnostico())
                    .getMaDiagnosticosValor();
        }
        return tuDiagnostico;
    }

    public void verTutela(TuTutelaRespuesta tutela) {
        setTuTutelaRespuesta(tutela);
        PrimeFaces.current().ajax().update("frmTutelaServicio");
        PrimeFaces.current().executeScript("PF('dialogoTutelaServicio').show()");
    }

    public void descargarAdjunto(TuAdjunto adjunto) {
        String rutaCompleta = adjunto.getRuta() + adjunto.getNombreArchivo();
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
            OutputStream output = ec.getResponseOutputStream();
            output.write(exportContent);
            output.close();
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        }
        procesoFinal();
    }

    public void refrescarContratoDetalle() {
        getCopagoModeradoraServicio().listarContratoDetalle(this);
        generarMensajes();
    }

    public void refrescarCopagoModeradoraHistorico() {
        getCopagoModeradoraServicio().listarCopagoModeradoraHistorico(this);
        generarMensajes();
    }

    public void listarContratos() {
        if (getObjetoTecnologia() != null) {
            String tecnologias = String.valueOf(getObjetoTecnologia().getMaTecnologiaId());
            getParamConsulta(0).setParametroConsulta1(getObjetoTecnologia().getTipoTecnologia());
            getParamConsulta(0).setParametroConsulta2(tecnologias);
            PrimeFaces.current().executeScript("PF('dialogoContratoLista').show()");
            PrimeFaces.current().ajax().update("frmContratoLista");
        } else {
            addError("No se encontro la tecnologia");
        }
        generarMensajes();
    }

    public void onRowSelectContrato(SelectEvent event) {
        CntContratoDetalle contrato = (CntContratoDetalle) event.getObject();
        setCntContratoDetalle(contrato);
        PrimeFaces.current().executeScript("PF('dialogoContratoLista').hide()");
        PrimeFaces.current().ajax().update("frmCopagoModeradora:panelTecnologia");
    }

    public void consultarMedicamento() {
        PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').show()");
        PrimeFaces.current().ajax().update("frmMedicamentoBusqueda");
    }

    public void consultarProcedimiento() {
        PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').show()");
        PrimeFaces.current().ajax().update("frmTecnologiaBusqueda");
    }

    public void consultarInsumo() {
        PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').show()");
        PrimeFaces.current().ajax().update("frmInsumoBusqueda");
    }

    public void consultarPaquete() {
        PrimeFaces.current().executeScript("PF('dialogoPaqueteBusqueda').show()");
        PrimeFaces.current().ajax().update("frmPaqueteBusqueda");
    }

    public void cerrarDialogoMedicamento() {
        AuAnexo3Item item = new AuAnexo3Item();
        //intercambia medicamento por agrupador
        item.setTipoTecnologia(AuAnexo3Item.TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO);
        MaMedicamento medicamento = getMedicamentosBean().getObjeto();
        item.setMaTecnologiaId(medicamento.getMaAgrupadorMedicamento().getId());
        item.setMaTecnologiaCodigo(medicamento.getMaAgrupadorMedicamento().getCodigo());
        item.setMaTecnologiaValor(medicamento.getMaAgrupadorMedicamento().getNombre());

        setObjetoTecnologia(item);
        setCntContratoDetalle(null);
        PrimeFaces.current().ajax().update("frmCopagoModeradora:panelTecnologia");
        PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').hide()");

    }

    public void cerrarDialogoPaquete() {
        AuAnexo3Item item = new AuAnexo3Item();
        item.setTipoTecnologia(AuAnexo3Item.TIPO_TECNOLOGIA_PAQUETE);
        MaPaquete paquete = getPaquetesBean().getObjeto();
        item.setMaTecnologiaId(paquete.getId());
        item.setMaTecnologiaCodigo(paquete.getCodigo());
        item.setMaTecnologiaValor(paquete.getNombre());
        item.setTipoPago(paquete.getMaTecnologia().getTipoPago());
        setObjetoTecnologia(item);
        setCntContratoDetalle(null);
        PrimeFaces.current().ajax().update("frmCopagoModeradora:panelTecnologia");
        PrimeFaces.current().executeScript("PF('dialogoPaqueteBusqueda').hide()");
    }

    public void cerrarDialogoTecnologia() {
        AuAnexo3Item item = new AuAnexo3Item();
        item.setTipoTecnologia(AuAnexo3Item.TIPO_TECNOLOGIA_CUP);
        MaTecnologia tecnologia = getTecnologiasBean().getObjeto();
        item.setMaTecnologiaId(tecnologia.getId());
        item.setMaTecnologiaCodigo(tecnologia.getCodigoPropio());
        item.setMaTecnologiaValor(tecnologia.getCupsDescipcion());
        item.setTipoPago(tecnologia.getTipoPago());

        setObjetoTecnologia(item);
        setCntContratoDetalle(null);
        PrimeFaces.current().ajax().update("frmCopagoModeradora:panelTecnologia");
        PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').hide()");

    }

    public void cerrarDialogoInsumo() {
        AuAnexo3Item item = new AuAnexo3Item();
        item.setTipoTecnologia(AuAnexo3Item.TIPO_TECNOLOGIA_INSUMO);
        MaInsumo insumo = getInsumosBean().getObjeto();
        item.setMaTecnologiaId(insumo.getId());
        item.setMaTecnologiaCodigo(insumo.getCodigo());
        item.setMaTecnologiaValor(insumo.getDescripcion());

        setObjetoTecnologia(item);
        setCntContratoDetalle(null);
        PrimeFaces.current().ajax().update("frmCopagoModeradora:panelTecnologia");
        PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').hide()");
    }

    public String obtenerMunicipio(int id) {
        try {
            return hashUbicaciones.get(id).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public String obtenerBoolean(boolean bool) {
        return bool == true ? AuConstantes.TEXTO_SI : AuConstantes.TEXTO_NO;
    }

    public String obtenerCategoria(String valor) {
        try {
            return getHashCategoria().get(valor).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }
}
