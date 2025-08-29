package com.saviasaludeps.savia.web.mipres.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacionAdjunto;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaInsumoMipres;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaPaqueteMipres;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologiaMipres;
import com.saviasaludeps.savia.dominio.mipres.MpCotizacionDetalle;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.negocio.maestro.MaTecnologiaRemoto;
import com.saviasaludeps.savia.web.autorizacion.servicio.AuCotizacionServicioIface;
import com.saviasaludeps.savia.web.mipres.servicio.MipresCotizacionIface;
import com.saviasaludeps.savia.web.mipres.servicio.MipresIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
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

/**
 * @author bsgomez
 */
@ManagedBean
@ViewScoped
public class CotizacionMipresBean extends Url {

    @Autowired
    private MipresCotizacionIface cotizacionServicio;

    private AuCotizacionServicioIface auCotizacionServicio;
    private MipresIface MipresServicio;

    private MpCotizacionDetalle objeto;
    private List<MpCotizacionDetalle> registros;
    private LazyDataModel<MpCotizacionDetalle> lazyRegistros;
    private List<CntPrestadorSede> listaPrestadoresSedes;
    private List<AuCotizacionAdjunto> mpCotizacionAdjuntosList;
    private AuCotizacion objetoCotizacion;
    private MpPrescripcion objetoPrescripcion;
    private CntPrestadorSede objetoPrestadorSede;
    private boolean tipoEditable;
    private boolean valorEditable;
    private ParamConsulta paramConsulta2;
    private LazyDataModel<CntPrestadorSede> lazyIPS;

    private LazyDataModel<MaMedicamento> lazyMaMedicamento;
    private ParamConsulta paramConsultaMaMedicamento;
    private List<MaMedicamento> registroMaMedicamento;

    private LazyDataModel<MaTecnologia> lazyMaTecnologia;
    private ParamConsulta paramConsultaMaTecnologia;
    private List<MaTecnologia> registroMaTecnologia;

    private LazyDataModel<MaTecnologiaMipres> lazyMaTecnologiaMipres;
    private ParamConsulta paramConsultaMaTecnologiaMipres;
    private List<MaTecnologiaMipres> registroMaTecnologiaMipres;

    private LazyDataModel<MaInsumoMipres> lazyMaInsumoMipres;
    private ParamConsulta paramConsultaMaInsumo;
    private List<MaInsumoMipres> registroMaInsumoMipres;

    private LazyDataModel<MaPaqueteMipres> lazyMaPaqueteMipres;
    private ParamConsulta paramConsultaMaPaquete;
    private List<MaPaqueteMipres> registroMaPaqueteMipres;

    public final static int ESTADO_SIN_COTIZACION = 11;
    public final static int ESTADO_CON_COTIZACION = 12;
    public final static int ESTADO_RECHAZO_COTIZACION = 13;

    public final static int FUENTE_ORIGEN_MIPRES = 2;
    public final static int TIPO_TARIFA_PROPIA = 0;
    public final static int TIPO_TECNOLOGIA_PROCEDIMIENTO = 2;

    public final static int TIPO_TARIFA_SOAT = 1;
    public final static int TIPO_TARIFA_ISS2000 = 2;
    public final static int TIPO_TARIFA_ISS2001 = 3;

    private MaTecnologiaRemoto getMaTecnologiaRemoto() throws Exception {
        return (MaTecnologiaRemoto) RemotoEJB.getEJBRemoto("MaTecnologiaServicio", MaTecnologiaRemoto.class.getName());
    }

    public CotizacionMipresBean() {
        this.objeto = new MpCotizacionDetalle();
        Modulo mod = super.validarModulo(Modulo.ID_COTIZACION_MIPRES);

        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyRegistros = new LazyDataModel<MpCotizacionDetalle>() {
                private List<MpCotizacionDetalle> listaCotizacionMpPrescripcion;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<MpCotizacionDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaCotizacionMpPrescripcion = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaCotizacionMpPrescripcion;
                }

                @Override
                public String getRowKey(MpCotizacionDetalle mpCotizacion) {
                    return mpCotizacion.getId().toString();
                }

                @Override
                public MpCotizacionDetalle getRowData(String ref) {
                    Integer id = Integer.valueOf(ref);
                    for (MpCotizacionDetalle refe : listaCotizacionMpPrescripcion) {
                        if (id.equals(refe.getId())) {
                            return refe;
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
        listar();
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    //Acciones
    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCotizacionServicio().Accion(this);
//        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmCotizaciones");
                    PrimeFaces.current().ajax().update("frmCotizaciones:tablaRegistros");
                    crearLog("Listar", getObjeto().toString());
                    break;
                case Url.ACCION_VER:
                    PrimeFaces.current().ajax().update("frmVer");
                    PrimeFaces.current().ajax().update("frmVer:pVer");
                    crearLog("Listar", getObjetoCotizacion().toString());
                    break;
                case Url.ACCION_GUARDAR:
                    PrimeFaces.current().ajax().update("frmCotizaciones");
                    break;
                case Url.ACCION_ADICIONAL_1:
                    PrimeFaces.current().ajax().update("frmGestionar");
                    PrimeFaces.current().ajax().update("frmAuditoriaGestionar:labelDatosAuditoria");
                    PrimeFaces.current().executeScript("PF('dialogoGestionar').show()");
                    crearLog("Gestionar", getObjeto().toString());
                    break;

                default:
                    break;
            }
        }
        generarMensajes();

    }

    public MipresCotizacionIface getCotizacionServicio() {
        return cotizacionServicio;
    }

    public void setCotizacionServicio(MipresCotizacionIface cotizacionServicio) {
        this.cotizacionServicio = cotizacionServicio;
    }

    public MpCotizacionDetalle getObjeto() {
        return objeto;
    }

    public void setObjeto(MpCotizacionDetalle objeto) {
        this.objeto = objeto;
    }

    public LazyDataModel<MpCotizacionDetalle> getLazyRegistros() {
        return lazyRegistros;
    }

    public void setLazyRegistros(LazyDataModel<MpCotizacionDetalle> lazyRegistros) {
        this.lazyRegistros = lazyRegistros;
    }

    public List<MpCotizacionDetalle> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MpCotizacionDetalle> registros) {
        this.registros = registros;
    }

    public List<CntPrestadorSede> getListaPrestadoresSedes() {
        return listaPrestadoresSedes;
    }

    public ParamConsulta getParamConsultaMaMedicamento() {
        if (paramConsultaMaMedicamento == null) {
            paramConsultaMaMedicamento = new ParamConsulta();
        }
        return paramConsultaMaMedicamento;
    }

    public void setParamConsultaMaMedicamento(ParamConsulta paramConsultaMaMedicamento) {
        this.paramConsultaMaMedicamento = paramConsultaMaMedicamento;
    }

    public ParamConsulta getParamConsultaMaTecnologia() {
        if (paramConsultaMaTecnologia == null) {
            paramConsultaMaTecnologia = new ParamConsulta();
        }
        return paramConsultaMaTecnologia;
    }

    public ParamConsulta getParamConsultaMaTecnologiaMipres() {
        if (paramConsultaMaTecnologiaMipres == null) {
            paramConsultaMaTecnologiaMipres = new ParamConsulta();
        }
        return paramConsultaMaTecnologiaMipres;
    }

    public void setParamConsultaMaTecnologiaMipres(ParamConsulta paramConsultaMaTecnologiaMipres) {
        this.paramConsultaMaTecnologiaMipres = paramConsultaMaTecnologiaMipres;
    }

    public void setParamConsultaMaTecnologia(ParamConsulta paramConsultaMaTecnologia) {
        this.paramConsultaMaTecnologia = paramConsultaMaTecnologia;
    }

    public List<MaMedicamento> getRegistroMaMedicamento() {
        return registroMaMedicamento;
    }

    public void setRegistroMaMedicamento(List<MaMedicamento> registroMaMedicamento) {
        this.registroMaMedicamento = registroMaMedicamento;
    }

    public LazyDataModel<MaTecnologia> getLazyMaTecnologia() {
        return lazyMaTecnologia;
    }

    public void setLazyMaTecnologia(LazyDataModel<MaTecnologia> lazyMaTecnologia) {
        this.lazyMaTecnologia = lazyMaTecnologia;
    }

    public List<MaTecnologia> getRegistroMaTecnologia() {
        return registroMaTecnologia;
    }

    public void setRegistroMaTecnologia(List<MaTecnologia> registroMaTecnologia) {
        this.registroMaTecnologia = registroMaTecnologia;
    }

    public LazyDataModel<MaTecnologiaMipres> getLazyMaTecnologiaMipres() {
        return lazyMaTecnologiaMipres;
    }

    public void setLazyMaTecnologiaMipres(LazyDataModel<MaTecnologiaMipres> lazyMaTecnologiaMipres) {
        this.lazyMaTecnologiaMipres = lazyMaTecnologiaMipres;
    }

    public ParamConsulta getParamConsultaMaInsumo() {
        if (paramConsultaMaInsumo == null) {
            paramConsultaMaInsumo = new ParamConsulta();
        }
        return paramConsultaMaInsumo;
    }

    public void setParamConsultaMaInsumo(ParamConsulta paramConsultaMaInsumo) {
        this.paramConsultaMaInsumo = paramConsultaMaInsumo;
    }

    public ParamConsulta getParamConsultaMaPaquete() {
        if (paramConsultaMaPaquete == null) {
            paramConsultaMaPaquete = new ParamConsulta();
        }
        return paramConsultaMaPaquete;
    }

    public void setParamConsultaMaPaquete(ParamConsulta paramConsultaMaPaquete) {
        this.paramConsultaMaPaquete = paramConsultaMaPaquete;
    }

    public LazyDataModel<MaPaqueteMipres> getLazyMaPaqueteMipres() {
        return lazyMaPaqueteMipres;
    }

    public void setLazyMaPaqueteMipres(LazyDataModel<MaPaqueteMipres> lazyMaPaqueteMipres) {
        this.lazyMaPaqueteMipres = lazyMaPaqueteMipres;
    }

    public List<MaPaqueteMipres> getRegistroMaPaqueteMipres() {
        return registroMaPaqueteMipres;
    }

    public void setRegistroMaPaqueteMipres(List<MaPaqueteMipres> registroMaPaqueteMipres) {
        this.registroMaPaqueteMipres = registroMaPaqueteMipres;
    }

    public LazyDataModel<MaInsumoMipres> getLazyMaInsumoMipres() {
        return lazyMaInsumoMipres;
    }

    public void setLazyMaInsumoMipres(LazyDataModel<MaInsumoMipres> lazyMaInsumoMipres) {
        this.lazyMaInsumoMipres = lazyMaInsumoMipres;
    }

    public List<MaInsumoMipres> getRegistroMaInsumoMipres() {
        return registroMaInsumoMipres;
    }

    public void setRegistroMaInsumoMipres(List<MaInsumoMipres> registroMaInsumoMipres) {
        this.registroMaInsumoMipres = registroMaInsumoMipres;
    }

    public List<MaTecnologiaMipres> getRegistroMaTecnologiaMipres() {
        return registroMaTecnologiaMipres;
    }

    public void setRegistroMaTecnologiaMipres(List<MaTecnologiaMipres> registroMaTecnologiaMipres) {
        this.registroMaTecnologiaMipres = registroMaTecnologiaMipres;
    }

    public LazyDataModel<MaMedicamento> getLazyMaMedicamento() {
        return lazyMaMedicamento;
    }

    public void setLazyMaMedicamento(LazyDataModel<MaMedicamento> lazyMaMedicamento) {
        this.lazyMaMedicamento = lazyMaMedicamento;
    }

    public boolean isValorEditable() {
        return valorEditable;
    }

    public void setValorEditable(boolean valorEditable) {
        this.valorEditable = valorEditable;
    }

    public ParamConsulta getParamConsulta2() {
        return paramConsulta2;
    }

    public void setParamConsulta2(ParamConsulta paramConsulta2) {
        this.paramConsulta2 = paramConsulta2;
    }

    public LazyDataModel<CntPrestadorSede> getLazyIPS() {
        return lazyIPS;
    }

    public void setLazyIPS(LazyDataModel<CntPrestadorSede> lazyIPS) {
        this.lazyIPS = lazyIPS;
    }

    public void setListaPrestadoresSedes(List<CntPrestadorSede> listaPrestadoresSedes) {
        this.listaPrestadoresSedes = listaPrestadoresSedes;
    }

    public List<AuCotizacionAdjunto> getMpCotizacionAdjuntosList() {
        return mpCotizacionAdjuntosList;
    }

    public void setMpCotizacionAdjuntosList(List<AuCotizacionAdjunto> mpCotizacionAdjuntosList) {
        this.mpCotizacionAdjuntosList = mpCotizacionAdjuntosList;
    }

    public AuCotizacion getObjetoCotizacion() {
        return objetoCotizacion;
    }

    public void setObjetoCotizacion(AuCotizacion objetoCotizacion) {
        this.objetoCotizacion = objetoCotizacion;
    }

    public MpPrescripcion getObjetoPrescripcion() {
        return objetoPrescripcion;
    }

    public void setObjetoPrescripcion(MpPrescripcion objetoPrescripcion) {
        this.objetoPrescripcion = objetoPrescripcion;
    }

    public CntPrestadorSede getObjetoPrestadorSede() {
        return objetoPrestadorSede;
    }

    public void setObjetoPrestadorSede(CntPrestadorSede objetoPrestadorSede) {
        this.objetoPrestadorSede = objetoPrestadorSede;
    }

    public boolean isTipoEditable() {
        return tipoEditable;
    }

    public void setTipoEditable(boolean tipoEditable) {
        this.tipoEditable = tipoEditable;
    }

    public AuCotizacionServicioIface getAuCotizacionServicio() {
        return auCotizacionServicio;
    }

    public void setAuCotizacionServicio(AuCotizacionServicioIface auCotizacionServicio) {
        this.auCotizacionServicio = auCotizacionServicio;
    }

    public MipresIface getMipresServicio() {
        return MipresServicio;
    }

    public void setMipresServicio(MipresIface MipresServicio) {
        this.MipresServicio = MipresServicio;
    }

    public String establecerColorEstado(Integer estado) {
        String color = "";
        try {
            int st = estado;
            switch (st) {
                case 11:
                    color = "naranja";
                    break;
                case 12:
                    color = "verde";
                    break;
                case 13:
                    color = "rojo";
                    break;
                case 14:
                    color = "verde";
                    break;
                case 15:
                    color = "amarillo";
                    break;
                case 17:
                    color = "verde";
                    break;
                case 18:
                    color = "naranja";
                    break;
                case 19:
                    color = "verde";
                    break;
                default:
                    color = "amarillo";
                    break;
            }
        } catch (Exception e) {
            color = "amarillo";
        }
        return color;
    }

    public String establecerColorTecnologia(int tipo) {
        String colorTecnologia = "";
        try {
            int tT = tipo;
            if (tT >= 1 && tT <= 5) {
                switch (tT) {
                    case 1:
                        colorTecnologia = "azul";
                        break;
                    case 2:
                        colorTecnologia = "verde";
                        break;
                    case 3:
                        colorTecnologia = "celeste";
                        break;
                    case 4:
                        colorTecnologia = "morado";
                        break;
                    case 5:
                        colorTecnologia = "naranja";
                        break;
                    default:
                        break;
                }
            } else {
                colorTecnologia = "amarillo";
            }
        } catch (Exception e) {
            colorTecnologia = "amarillo";
        }
        return colorTecnologia;
    }

    public Boolean validarEstadoSinCotizacion(Integer estado) {
        Boolean valor = false;
        if (estado == 11) {
            valor = true;
        } else {
            valor = false;
        }
        return valor;
    }

    public Boolean validarEstadoRechazoCotizacion(Integer estado) {
        Boolean valor = false;
        if (estado == 13) {
            valor = true;
        } else {
            valor = false;
        }
        return valor;
    }

    public void ver(int id) {
        setObjeto(new MpCotizacionDetalle());
        setObjetoCotizacion(new AuCotizacion());
        setMpCotizacionAdjuntosList(new ArrayList());
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getCotizacionServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmVer");
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void gestionar(int id) {
        setListaPrestadoresSedes(new ArrayList<>());
        setObjetoCotizacion(new AuCotizacion());
        setObjeto(new MpCotizacionDetalle());
        setObjetoPrescripcion(new MpPrescripcion());
        setObjetoPrestadorSede(new CntPrestadorSede());
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_1);
        getCotizacionServicio().Accion(this);
        procesoFinal();
    }

    public void rechazar(int id) {
        setObjeto(new MpCotizacionDetalle());
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_2);
        getCotizacionServicio().Accion(this);
        procesoFinal();
    }

    public void guardar() {
        if (objetoCotizacion.getMaTecnologiaCodigo() == null) {
            addError("La Tecnologia Es Obligatoria");
        }
        if (objetoPrestadorSede.getNombreSede() == null) {
            addError("El Prestador Es Obligatorio");
        }
        if (!super.isError()) {
            super.setAccion(ACCION_GUARDAR);
            getCotizacionServicio().Accion(this);
        }
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoGestionar').hide()");
        }
        procesoFinal();
    }

    public void mostrarAfiliadoGeneral() {
        getCotizacionServicio().completarAfiliado(this);
        PrimeFaces.current().resetInputs("frmAfiliadoGeneral");
        PrimeFaces.current().ajax().update("frmAfiliadoGeneral");
        PrimeFaces.current().executeScript("PF('dlgInfoAfiliado').show();");
        generarMensajes();
    }

    public boolean habilitarTarifa() {
        boolean validate = true;
        if (tipoEditable) {
            validate = tipoEditable;
        } else if (!getObjetoCotizacion().isHabilitarCamposAnticipos()) {
            validate = false;
        }
        return validate;
    }

    public void obtenerValorTecnologia() {
        try {
            //Desbloquear inserción de valor manual si el tipo PROPIA
            if (getObjetoCotizacion().getTipoTarifa() == TIPO_TARIFA_PROPIA) {
                setValorEditable(false);
                getObjetoCotizacion().setValorTecnologia(BigDecimal.ZERO);
            } else {
                setValorEditable(true);
                BigDecimal valor = BigDecimal.ZERO;
                MaTecnologia tecnologia = getMaTecnologiaRemoto().consultar(getObjetoCotizacion().getMaTecnologiaId());
                if (tecnologia == null) {
                    addError("No se encuentra la tecnología con ID:" + getObjetoCotizacion().getMaTecnologiaId());
                }
                if (getObjeto().getTipoTecnologia() == TIPO_TECNOLOGIA_PROCEDIMIENTO) {
                    switch (getObjetoCotizacion().getTipoTarifa()) {
                        case TIPO_TARIFA_ISS2000:
                            if (tecnologia.getMaIss2000Tarifario() != null) {
                                valor = tecnologia.getMaIss2000Tarifario().getMonto();
                            }
                            break;
                        case TIPO_TARIFA_ISS2001:
                            if (tecnologia.getMaIss2001Tarifario() != null) {
                                valor = tecnologia.getMaIss2001Tarifario().getMonto();
                            }
                            break;
                        case TIPO_TARIFA_SOAT:
                            if (tecnologia.getMaSoatTarifario() != null) {
                                valor = getAuCotizacionServicio().consultarValorSoat(tecnologia.getMaSoatTarifario().getId());
                            }
                            break;
                        default:
                            break;
                    }
                }
                getObjetoCotizacion().setValorTecnologia(valor);
            }
            PrimeFaces.current().ajax().update("frmGestionar:valorTotal");
            PrimeFaces.current().ajax().update("frmGestionar:tipoTarifa");
        } catch (Exception ex) {
            addError("ERROR: El Id De Tecnología Solo Se Le Puede Asignar Una Tarifa Propia");
        } finally {
            generarMensajes();
        }
    }

    public void listarPrestador() {
        this.getCotizacionServicio().listarPrestador(this);
    }

    public void consultarIPS() {
        try {
            this.setParamConsulta2(new ParamConsulta());
            this.getParamConsulta2().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyIPS = new LazyDataModel<CntPrestadorSede>() {

                private List<CntPrestadorSede> listaIPS;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta2().getCantidadRegistros();
                }

                @Override
                public List<CntPrestadorSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta2().setPrimerRegistro(primerRegistro);
                    getParamConsulta2().setRegistrosPagina(registrosPagina);
                    getParamConsulta2().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta2().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    listarPrestador();
                    listaIPS = getListaPrestadoresSedes();
                    setRowCount(getParamConsulta2().getCantidadRegistros());
                    return listaIPS;
                }

                @Override
                public String getRowKey(CntPrestadorSede ips) {
                    return ips.getId().toString();
                }

                @Override
                public CntPrestadorSede getRowData(String ipsId) {
                    Integer id = Integer.valueOf(ipsId);
                    for (CntPrestadorSede ips : listaIPS) {
                        if (id.equals(ips.getId())) {
                            return ips;
                        }
                    }
                    return null;
                }
            };
            PrimeFaces.current().ajax().update("frmIpsLista");
            PrimeFaces.current().executeScript("PF('dialogoIpsLista').show()");

        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void onRowSelectIps(SelectEvent event) {
        CntPrestadorSede ips = (CntPrestadorSede) event.getObject();
        setObjetoPrestadorSede(ips);

        this.objetoPrestadorSede.setNombreSede(ips.getNombreSede());
        this.objetoPrestadorSede.setCodigoPrestador(ips.getCodigoPrestador());
        this.objetoPrestadorSede.setCodigoHabilitacionSede(ips.getCodigoHabilitacionSede());
        this.objetoPrestadorSede.setTelefonoAdministrativo(ips.getTelefonoAdministrativo());
        this.objetoPrestadorSede.setCorreoElectronico(ips.getCorreoElectronico());
        this.objetoCotizacion.setCntPrestadorSede(new CntPrestadorSede(ips.getId()));
        PrimeFaces.current().ajax().update("frmGestionar");
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').hide()");
    }

    public void calcularValor() {
        //Obtener el valor y adicionar el porcentaje
        obtenerValorTecnologia();
        if (getObjetoCotizacion().getValorTecnologia() != null) {
            getObjetoCotizacion().setValorTecnologia(
                    getObjetoCotizacion().getValorTecnologia()
                            .multiply(getObjetoCotizacion().getPorcentajeNegociacion())
                            .divide(new BigDecimal(AuCotizacion.CIEN), RoundingMode.HALF_UP)
                            .add(getObjetoCotizacion().getValorTecnologia())
            );
            PrimeFaces.current().ajax().update("frmGestionar:valorTotal");
        }
    }

    public void ventanaAdjunto() {
        PrimeFaces.current().resetInputs("frmAdjuntoGestionar");
        PrimeFaces.current().ajax().update("frmAdjuntoGestionar");
        PrimeFaces.current().executeScript("PF('dialogoAdjuntoGestionar').show()");
    }

    public void cargarAdjunto(FileUploadEvent event) throws IOException {
        try {

            AuCotizacionAdjunto adjuntoDocumento = new AuCotizacionAdjunto();
            UploadedFile archivo = event.getFile();
            String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
            adjuntoDocumento.setAdjuntoStream(archivo.getInputStream());
            int indiceExtension = nombreAdjunto.lastIndexOf(".");
            String extension = nombreAdjunto.substring(indiceExtension, nombreAdjunto.length());
            adjuntoDocumento.setNombreArchivo(nombreAdjunto);
            adjuntoDocumento.setArchivo(this.getObjeto().getNumeroPrescripcion());
            adjuntoDocumento.setExtension(extension);
            adjuntoDocumento.setMaeTipoArchivoId(3199);
            adjuntoDocumento.setMaeTipoArchivoCodigo("14");
            adjuntoDocumento.setMaeTipoArchivoValor("Otros soportes");
            getMpCotizacionAdjuntosList().add(adjuntoDocumento);
            PrimeFaces.current().executeScript("PF('dialogoAdjuntoGestionar').hide()");
            PrimeFaces.current().ajax().update("frmGestionar:pSoporteGestionar");
            PrimeFaces.current().ajax().update("frmGestionarAdjuntos:pSoportesGestionarAdjuntos");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void consultarMaMedicamento() {
        try {
            inicializarLazyMaMedicamento();
            PrimeFaces.current().executeScript("PF('dialogoMaMedicamento').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void inicializarLazyMaMedicamento() {
        lazyMaMedicamento = new LazyDataModel<MaMedicamento>() {

            private List<MaMedicamento> medicamentos;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaMaMedicamento().getCantidadRegistros();
            }

            @Override
            public List<MaMedicamento> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                setParamConsultaMaMedicamento(new ParamConsulta());
                getParamConsultaMaMedicamento().setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsultaMaMedicamento().setPrimerRegistro(primerRegistro);
                getParamConsultaMaMedicamento().setRegistrosPagina(registrosPagina);
                getParamConsultaMaMedicamento().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaMaMedicamento().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarMaMedicamento();
                medicamentos = getRegistroMaMedicamento();
                setRowCount(getParamConsultaMaMedicamento().getCantidadRegistros());
                return medicamentos;
            }

            @Override
            public String getRowKey(MaMedicamento sede) {
                return sede.getId().toString();
            }

            @Override
            public MaMedicamento getRowData(String maMedicamentoId) {
                Integer id = Integer.valueOf(maMedicamentoId);
                for (MaMedicamento med : medicamentos) {
                    if (id.equals(med.getId())) {
                        return med;
                    }
                }
                return null;
            }
        };
    }

    public void refrescarMaMedicamento() {
        try {
            getCotizacionServicio().listarMaMedicamento(this);
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void consultarMaTecnologia() {
        try {
            inicializarLazyMaTecnologia();
            PrimeFaces.current().executeScript("PF('dialogoMaTecnologia').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void inicializarLazyMaTecnologia() {
        lazyMaTecnologia = new LazyDataModel<MaTecnologia>() {

            private List<MaTecnologia> tecnologias;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaMaTecnologia().getCantidadRegistros();
            }

            @Override
            public List<MaTecnologia> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                setParamConsultaMaTecnologia(new ParamConsulta());
                getParamConsultaMaTecnologia().setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsultaMaTecnologia().setPrimerRegistro(primerRegistro);
                getParamConsultaMaTecnologia().setRegistrosPagina(registrosPagina);
                getParamConsultaMaTecnologia().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaMaTecnologia().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarMaTecnologia();
                tecnologias = getRegistroMaTecnologia();
                setRowCount(getParamConsultaMaTecnologia().getCantidadRegistros());
                return tecnologias;
            }

            @Override
            public String getRowKey(MaTecnologia tecnologia) {
                return tecnologia.getId().toString();
            }

            @Override
            public MaTecnologia getRowData(String maTecnologiaId) {
                Integer id = Integer.valueOf(maTecnologiaId);
                for (MaTecnologia tec : tecnologias) {
                    if (id.equals(tec.getId())) {
                        return tec;
                    }
                }
                return null;
            }
        };
    }

    public void refrescarMaTecnologia() {
        try {
            getCotizacionServicio().listarMaTecnologia(this);
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void consultarMpMaTecnologia() {
        try {
            inicializarLazyMipresTecnologia();
            PrimeFaces.current().executeScript("PF('dialogoMaMipresTecnologia').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void inicializarLazyMipresTecnologia() {

        lazyMaTecnologiaMipres = new LazyDataModel<MaTecnologiaMipres>() {

            private List<MaTecnologiaMipres> tecnologiasMipres;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaMaTecnologiaMipres().getCantidadRegistros();
            }

            @Override
            public List<MaTecnologiaMipres> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                setParamConsultaMaTecnologiaMipres(new ParamConsulta());
                getParamConsultaMaTecnologiaMipres().setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsultaMaTecnologiaMipres().setPrimerRegistro(primerRegistro);
                getParamConsultaMaTecnologiaMipres().setRegistrosPagina(registrosPagina);
                getParamConsultaMaTecnologiaMipres().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaMaTecnologiaMipres().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarMaTecnologiaMipres();
                tecnologiasMipres = getRegistroMaTecnologiaMipres();
                setRowCount(getParamConsultaMaTecnologiaMipres().getCantidadRegistros());
                return tecnologiasMipres;
            }

            @Override
            public String getRowKey(MaTecnologiaMipres tecnologia) {
                return tecnologia.getId().toString();
            }

            @Override
            public MaTecnologiaMipres getRowData(String maMpId) {
                Integer id = Integer.valueOf(maMpId);
                for (MaTecnologiaMipres ins : tecnologiasMipres) {
                    if (id.equals(ins.getId())) {
                        return ins;
                    }
                }
                return null;
            }
        };
    }

    public void refrescarMaTecnologiaMipres() {
        try {
            getCotizacionServicio().listarMaTecnologiaMipres(this);
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void consultarMaInsumo() {
        try {
            inicializarLazyInsumoMipres();
            PrimeFaces.current().executeScript("PF('dialogoMaInsumoMipres').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void inicializarLazyInsumoMipres() {
        lazyMaInsumoMipres = new LazyDataModel<MaInsumoMipres>() {

            private List<MaInsumoMipres> insumos;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaMaInsumo().getCantidadRegistros();
            }

            @Override
            public List<MaInsumoMipres> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                setParamConsultaMaInsumo(new ParamConsulta());
                getParamConsultaMaInsumo().setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsultaMaInsumo().setPrimerRegistro(primerRegistro);
                getParamConsultaMaInsumo().setRegistrosPagina(registrosPagina);
                getParamConsultaMaInsumo().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaMaInsumo().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarMaInsumoMipres();
                insumos = getRegistroMaInsumoMipres();
                setRowCount(getParamConsultaMaInsumo().getCantidadRegistros());
                return insumos;
            }

            @Override
            public String getRowKey(MaInsumoMipres insumo) {
                return insumo.getId().toString();
            }

            @Override
            public MaInsumoMipres getRowData(String maInsumoId) {
                Integer id = Integer.valueOf(maInsumoId);
                for (MaInsumoMipres ins : insumos) {
                    if (id.equals(ins.getId())) {
                        return ins;
                    }
                }
                return null;
            }
        };
    }

    public void refrescarMaInsumoMipres() {
        try {
            getCotizacionServicio().listarMaInsumoMipres(this);
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void consultarMaPaquete() {
        try {
            inicializarLazyPaqueteMipres();
            PrimeFaces.current().executeScript("PF('dialogoMaPaqueteMipres').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void inicializarLazyPaqueteMipres() {
        lazyMaPaqueteMipres = new LazyDataModel<MaPaqueteMipres>() {

            private List<MaPaqueteMipres> paquetes;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaMaPaquete().getCantidadRegistros();
            }

            @Override
            public List<MaPaqueteMipres> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                setParamConsultaMaPaquete(new ParamConsulta());
                getParamConsultaMaPaquete().setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsultaMaPaquete().setPrimerRegistro(primerRegistro);
                getParamConsultaMaPaquete().setRegistrosPagina(registrosPagina);
                getParamConsultaMaPaquete().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaMaPaquete().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarMaPaqueteMipres();
                paquetes = getRegistroMaPaqueteMipres();
                setRowCount(getParamConsultaMaPaquete().getCantidadRegistros());
                return paquetes;
            }

            @Override
            public String getRowKey(MaPaqueteMipres paquete) {
                return paquete.getId().toString();
            }

            @Override
            public MaPaqueteMipres getRowData(String paqueteId) {
                Integer id = Integer.valueOf(paqueteId);
                for (MaPaqueteMipres ins : paquetes) {
                    if (id.equals(ins.getId())) {
                        return ins;
                    }
                }
                return null;
            }
        };
    }

    public void refrescarMaPaqueteMipres() {
        try {
            getCotizacionServicio().listarMaPaqueteMipres(this);
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void onRowSelectMaMedicamento(SelectEvent event) {
        MaMedicamento medicamento = (MaMedicamento) event.getObject();
        this.getObjetoCotizacion().setMaTecnologiaId(medicamento.getId());//tecnologia esta buscando por contrato tambien
        this.getObjetoCotizacion().setTipoTecnologiaMipres(2);
        this.getObjetoCotizacion().setMaTecnologiaCodigo(medicamento.getCum());
        this.getObjetoCotizacion().setMaTecnologiaValor(medicamento.getDescripcionInvima());
        if (this.getObjetoCotizacion()//validar si desde aca o mejor con el tipo de tecnologia mipres cuandlo elija la tecnologia mejor 
                .getTipoTecnologiaMipres() == CotizacionMipresBean.TIPO_TECNOLOGIA_PROCEDIMIENTO) {
            this.setTipoEditable(false);
            this.setValorEditable(true);
        } else {
            this.getObjetoCotizacion().setTipoTarifa(CotizacionMipresBean.TIPO_TARIFA_PROPIA);
            this.setTipoEditable(true);
            this.setValorEditable(false);
            this.getObjetoCotizacion().setPorcentajeNegociacion(BigDecimal.ZERO);
        }
        PrimeFaces.current().ajax().update("frmGestionar");
        PrimeFaces.current().executeScript("PF('dialogoMaMedicamento').hide()");
    }

    public void onRowSelectMaTecnologia(SelectEvent event) {
        MaTecnologia tecnologia = (MaTecnologia) event.getObject();
        this.getObjetoCotizacion().setMaTecnologiaId(tecnologia.getId());//tecnologia esta buscando por contrato tambien
        this.getObjetoCotizacion().setTipoTecnologiaMipres(1);
        this.getObjetoCotizacion().setMaTecnologiaCodigo(tecnologia.getCups());
        this.getObjetoCotizacion().setMaTecnologiaValor(tecnologia.getCupsDescipcion());
        if (this.getObjetoCotizacion()//validar si desde aca o mejor con el tipo de tecnologia mipres cuandlo elija la tecnologia mejor 
                .getTipoTecnologiaMipres() == CotizacionMipresBean.TIPO_TECNOLOGIA_PROCEDIMIENTO) {
            this.setTipoEditable(false);
            this.setValorEditable(true);
        } else {
            this.getObjetoCotizacion().setTipoTarifa(CotizacionMipresBean.TIPO_TARIFA_PROPIA);
            this.setTipoEditable(true);
            this.setValorEditable(false);
            this.getObjetoCotizacion().setPorcentajeNegociacion(BigDecimal.ZERO);
        }
        PrimeFaces.current().ajax().update("frmGestionar");
        PrimeFaces.current().executeScript("PF('dialogoMaTecnologia').hide()");
    }

    public void onRowSelectMaMpMipres(SelectEvent event) {
        MaTecnologiaMipres tecno = (MaTecnologiaMipres) event.getObject();
        this.getObjetoCotizacion().setMaTecnologiaId(tecno.getMaTecnologia().getId());
        this.getObjetoCotizacion().setTipoTecnologiaMipres(1);
        this.getObjetoCotizacion().setMaTecnologiaCodigo(tecno.getCodigoMipres());
        this.getObjetoCotizacion().setMaTecnologiaValor(tecno.getMaTecnologia().getPropioDescripcion());
        if (this.getObjetoCotizacion()//validar si desde aca o mejor con el tipo de tecnologia mipres cuandlo elija la tecnologia mejor 
                .getTipoTecnologiaMipres() == CotizacionMipresBean.TIPO_TECNOLOGIA_PROCEDIMIENTO) {
            this.setTipoEditable(false);
            this.setValorEditable(true);
        } else {
            this.getObjetoCotizacion().setTipoTarifa(CotizacionMipresBean.TIPO_TARIFA_PROPIA);
            this.setTipoEditable(true);
            this.setValorEditable(false);
            this.getObjetoCotizacion().setPorcentajeNegociacion(BigDecimal.ZERO);
        }
        PrimeFaces.current().ajax().update("frmGestionar");
        PrimeFaces.current().executeScript("PF('dialogoMaMipresTecnologia').hide()");
    }

    public void onRowSelectMaInsumoMipres(SelectEvent event) {
        MaInsumoMipres insumo = (MaInsumoMipres) event.getObject();
        this.getObjetoCotizacion().setMaTecnologiaId(insumo.getMaInsumo().getId());
        this.getObjetoCotizacion().setTipoTecnologiaMipres(3);
        this.getObjetoCotizacion().setMaTecnologiaCodigo(insumo.getCodigoMipres());
        this.getObjetoCotizacion().setMaTecnologiaValor(insumo.getMaInsumo().getDescripcion());
        if (this.getObjetoCotizacion()//validar si desde aca o mejor con el tipo de tecnologia mipres cuandlo elija la tecnologia mejor 
                .getTipoTecnologiaMipres() == CotizacionMipresBean.TIPO_TECNOLOGIA_PROCEDIMIENTO) {
            this.setTipoEditable(false);
            this.setValorEditable(true);
        } else {
            this.getObjetoCotizacion().setTipoTarifa(CotizacionMipresBean.TIPO_TARIFA_PROPIA);
            this.setTipoEditable(true);
            this.setValorEditable(false);
            this.getObjetoCotizacion().setPorcentajeNegociacion(BigDecimal.ZERO);
        }
        PrimeFaces.current().ajax().update("frmGestionar");
        PrimeFaces.current().executeScript("PF('dialogoMaInsumoMipres').hide()");
    }

    public void onRowSelectMaPaqueteMipres(SelectEvent event) {
        MaPaqueteMipres paquete = (MaPaqueteMipres) event.getObject();
        this.getObjetoCotizacion().setMaTecnologiaId(paquete.getMaPaquete().getId());
        this.getObjetoCotizacion().setTipoTecnologiaMipres(4);
        this.getObjetoCotizacion().setMaTecnologiaCodigo(paquete.getCodigoMipres());
        this.getObjetoCotizacion().setMaTecnologiaValor(paquete.getDescripcionMipres());
        if (this.getObjetoCotizacion()//validar si desde aca o mejor con el tipo de tecnologia mipres cuandlo elija la tecnologia mejor 
                .getTipoTecnologiaMipres() == CotizacionMipresBean.TIPO_TECNOLOGIA_PROCEDIMIENTO) {
            this.setTipoEditable(false);
            this.setValorEditable(true);
        } else {
            this.getObjetoCotizacion().setTipoTarifa(CotizacionMipresBean.TIPO_TARIFA_PROPIA);
            this.setTipoEditable(true);
            this.setValorEditable(false);
            this.getObjetoCotizacion().setPorcentajeNegociacion(BigDecimal.ZERO);
        }
        PrimeFaces.current().ajax().update("frmGestionar");
        PrimeFaces.current().executeScript("PF('dialogoMaPaqueteMipres').hide()");
    }

    public void descargarAdjunto(AuCotizacionAdjunto adjunto) {
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

    public String tipoTari(int val) {
        switch (val) {
            case 0:
                return "PROPIA";
            case 1:
                return "SOAT";
            case 2:
                return "ISS2000";
            case 3:
                return "ISS2001";
            default:
                return "DESCONOCIDO";
        }
    }

    public String obtenerAmbito(Integer valor) {
        String obtenerAmbito = "";
        if (valor != null) {
            switch (valor) {
                case 11:
                    obtenerAmbito = "Ambulatorio - Priorizado";
                    break;
                case 12:
                    obtenerAmbito = "Ambulatorio - No Priorizado";
                    break;
                case 21:
                    obtenerAmbito = "Hospitalario - Domiciliario";
                    break;
                case 22:
                    obtenerAmbito = "Hospitalario - Internacion";
                    break;
                case 30:
                    obtenerAmbito = "Urgencias";
                    break;
            }
        } else {
            obtenerAmbito = "Recobrante";
        }
        return obtenerAmbito;
    }

    public boolean isFueraDeVigencia(java.sql.Date fecha) {
        if (fecha != null) {
            LocalDate fechaComparar = fecha.toLocalDate(); // java.sql.Date lo tiene directo
            LocalDate hoy = LocalDate.now();
            return fechaComparar.isBefore(hoy);
        }
        return false;
    }

    public void borrarSoporteCrear(AuCotizacionAdjunto adjunto) {
        getMpCotizacionAdjuntosList().remove(adjunto);
        PrimeFaces.current().ajax().update("frmGestionar:pSoporteGestionar");
    }

}
