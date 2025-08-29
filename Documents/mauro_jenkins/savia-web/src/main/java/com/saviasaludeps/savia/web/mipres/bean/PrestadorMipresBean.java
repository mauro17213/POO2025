package com.saviasaludeps.savia.web.mipres.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.mipres.MpAnuladaPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamiento;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamientoEntregado;
import com.saviasaludeps.savia.dominio.mipres.MpHomologacion;
import com.saviasaludeps.savia.dominio.mipres.MpMedicamentoIndicacionUnirs;
import com.saviasaludeps.savia.dominio.mipres.MpMedicamentoPrincipioActivo;
import com.saviasaludeps.savia.dominio.mipres.MpNoDireccionado;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionAuditoria;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionDetalle;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionHistorico;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionProgramada;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionRecobrante;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.dominio.mipres.MpProgramadaEntrega;
import com.saviasaludeps.savia.dominio.mipres.ReporteDireccionamiento;
import com.saviasaludeps.savia.web.mipres.bean.DTO.MpDetalleDTO;
import com.saviasaludeps.savia.web.mipres.servicio.PrestadorMipresIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 * @author bsgomez
 */
@ManagedBean
@ViewScoped
public class PrestadorMipresBean extends Url {

    @Autowired
    private PrestadorMipresIface prestadorMipresvicio;

    private MpPrescripcion objetoPrescripcion;
    private MpPrescripcionRecobrante prescripcionRecobrante;
    private LazyDataModel<MpPrescripcion> lazyRegistros;
    private List<MpPrescripcion> registros;
    private List<MpDetalleDTO> listaPrescripcionDto;
    private List<MpPrescripcionHistorico> listaPrescripcionHistoricos;
    private List<MpPrescripcion> prescripcionListaHistoricos;
    private MpAnuladaPrescripcion MpPrescripcionAnulada;
    private Integer numeroDeTutelas;
    private HashMap<Integer, Maestro> hashTiposDocumentoPersona;
    private HashMap<Integer, Maestro> hashEstadosAfiliacion;
    private HashMap<Integer, Ubicacion> hashUbicaciones;
    private List<Ubicacion> ubicaciones;
    private HashMap<Integer, Ubicacion> ubicacionesRecursiva;
    private List<Maestro> listaTiposDocumentoPersona;
    private HashMap<Integer, Maestro> hashTiposGenero;
    private MpPrescripcionRecobrante prescripcionRecobranteH;
    private List<MpHomologacion> listaHomologaciones;
    private MpPrescripcionAuditoria prescripcionAuditoriaRespaldo;
    private MpPrescripcionInsumo prescripcionInsumo;
    private List<MpMedicamentoPrincipioActivo> listaPrincipiosActivos;
    private List<MpPrescripcionProgramada> listaPrescripcionProgramada;
    private List<MpProgramadaEntrega> listaprogramadaEntrega;
    private List<MpDireccionamientoEntregado> listaDireccionamientosEntregados;
    private List<MpMedicamentoIndicacionUnirs> listaMpIndicacionUnirs;
    private boolean tieneDireccionamiento;
    private boolean tienePrincipioActivo;
    private boolean tieneEntregas;
    private boolean tieneNoDireccionamientos;
    private MpPrescripcionMedicamento prescripcionMedicamento;
    private List<MpPrescripcionAuditoria> listaPrescripcionAuditoriaRespaldo;
    private List<MpDireccionamiento> listadireccionamientosAux;
    private List<MpNoDireccionado> listanoDireccionados;
    private MpPrescripcionTecnologia prescripcionTecnologia;
    private List<MpDetalleDTO> listaPrescripcionDtoH;
    private MpPrescripcion prescripcionH;

    private boolean tieneAnulacion = false;
    private boolean tieneAuditoria = false;
    private boolean mostrarAlertaPrimeraV;
    private Integer numeroDeTutelasH;
    String documentoAfiliado;
    Integer idPRescripcion;

    public static final String ID_AMBITO_AMBULATORIO_PRIORIZADO = "11";
    public static final String ID_AMBITO_AMBULATORIO_NO_PRIORIZADO = "12";
    public static final String ID_AMBITO_HOSPITALARIO_DOMICILIARIO = "21";
    public static final String ID_AMBITO_HOSPITALARIO_INTERNACION = "22";
    public static final String ID_AMBITO_URGENCIAS = "30";

    public PrestadorMipresBean() {
        this.objetoPrescripcion = new MpPrescripcion();
        Modulo mod = super.validarModulo(Modulo.ID_PRESTADORES_MIPRES);

        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyRegistros = new LazyDataModel<MpPrescripcion>() {
                private List<MpPrescripcion> listaMpPrescripcion;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<MpPrescripcion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaMpPrescripcion = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaMpPrescripcion;
                }

                @Override
                public String getRowKey(MpPrescripcion mpPrescripcion) {
                    return mpPrescripcion.getId().toString();
                }

                @Override
                public MpPrescripcion getRowData(String ref) {
                    Integer id = Integer.valueOf(ref);
                    for (MpPrescripcion refe : listaMpPrescripcion) {
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
        getPrestadorMipresvicio().CargaInicial(this);
        listar();
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    //Acciones
    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getPrestadorMipresvicio().Accion(this);
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
//                    PrimeFaces.current().ajax().update("frmPrestadoresMipres");
//                    PrimeFaces.current().ajax().update("frmPrestadoresMipres:tablaRegistros");
                    break;
                case Url.ACCION_VER:
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            PrimeFaces.current().resetInputs("frmVer");
                            PrimeFaces.current().ajax().update("frmVer");
                            PrimeFaces.current().ajax().update("frmAuditoriaVer");
                            crearLog("Ver", getObjetoPrescripcion().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            switch (getObjetoPrescripcion().getTipoTecnologiaItem()) {
                                case 1: {
                                    PrimeFaces.current().resetInputs("frmVerItemMM");
                                    PrimeFaces.current().ajax().update("frmVerItemMM");
                                    PrimeFaces.current().resetInputs("frmAuditoriaVerItemMM");
                                    PrimeFaces.current().ajax().update("frmAuditoriaVerItemMM");
                                    crearLog("Ver Medicamento", getPrescripcionMedicamento().toString());
                                }
                                break;
                                case 4: {
                                    PrimeFaces.current().resetInputs("frmVerItemM");
                                    PrimeFaces.current().ajax().update("frmVerItemM");
                                    PrimeFaces.current().resetInputs("frmAuditoriaVerItemM");
                                    PrimeFaces.current().ajax().update("frmAuditoriaVerItemM");
                                    crearLog("Ver Producto Nutricional", getPrescripcionMedicamento().toString());
                                }
                                break;
                                case 2: {
                                    PrimeFaces.current().resetInputs("frmVerItemT");
                                    PrimeFaces.current().ajax().update("frmVerItemT");
                                    PrimeFaces.current().resetInputs("frmAuditoriaVerItemT");
                                    PrimeFaces.current().ajax().update("frmAuditoriaVerItemT");
                                    crearLog("Ver Procedimiento", getPrescripcionTecnologia().toString());
                                }
                                break;
                                case 3: {
                                    PrimeFaces.current().resetInputs("frmVerItemII");
                                    PrimeFaces.current().ajax().update("frmVerItemII");
                                    PrimeFaces.current().resetInputs("frmAuditoriaVerItemII");
                                    PrimeFaces.current().ajax().update("frmAuditoriaVerItemII");
                                    crearLog("Ver Dispositivo", getPrescripcionInsumo().toString());
                                }
                                break;
                                case 5: {
                                    PrimeFaces.current().resetInputs("frmVerItemI");
                                    PrimeFaces.current().ajax().update("frmVerItemI");
                                    PrimeFaces.current().resetInputs("frmAuditoriaVerItemI");
                                    PrimeFaces.current().ajax().update("frmAuditoriaVerItemI");
                                    crearLog("Ver Servicio Complementario", getPrescripcionInsumo().toString());
                                }
                                break;
                            }
                            break;
                    }
                    break;
                case Url.ACCION_GUARDAR:
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;

                default:
                    break;
            }
        }
        generarMensajes();

    }

    public PrestadorMipresIface getPrestadorMipresvicio() {
        return prestadorMipresvicio;
    }

    public void setPrestadorMipresvicio(PrestadorMipresIface prestadorMipresvicio) {
        this.prestadorMipresvicio = prestadorMipresvicio;
    }

    public MpPrescripcion getObjetoPrescripcion() {
        return objetoPrescripcion;
    }

    public void setObjetoPrescripcion(MpPrescripcion objetoPrescripcion) {
        this.objetoPrescripcion = objetoPrescripcion;
    }

    public LazyDataModel<MpPrescripcion> getLazyRegistros() {
        return lazyRegistros;
    }

    public void setLazyRegistros(LazyDataModel<MpPrescripcion> lazyRegistros) {
        this.lazyRegistros = lazyRegistros;
    }

    public List<MpPrescripcion> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MpPrescripcion> registros) {
        this.registros = registros;
    }

    public Integer getNumeroDeTutelas() {
        return numeroDeTutelas;
    }

    public void setNumeroDeTutelas(Integer numeroDeTutelas) {
        this.numeroDeTutelas = numeroDeTutelas;
    }

    public boolean isTieneAnulacion() {
        return tieneAnulacion;
    }

    public void setTieneAnulacion(boolean tieneAnulacion) {
        this.tieneAnulacion = tieneAnulacion;
    }

    public boolean isTieneAuditoria() {
        return tieneAuditoria;
    }

    public void setTieneAuditoria(boolean tieneAuditoria) {
        this.tieneAuditoria = tieneAuditoria;
    }

    public boolean isMostrarAlertaPrimeraV() {
        return mostrarAlertaPrimeraV;
    }

    public void setMostrarAlertaPrimeraV(boolean mostrarAlertaPrimeraV) {
        this.mostrarAlertaPrimeraV = mostrarAlertaPrimeraV;
    }

    public Integer getNumeroDeTutelasH() {
        return numeroDeTutelasH;
    }

    public void setNumeroDeTutelasH(Integer numeroDeTutelasH) {
        this.numeroDeTutelasH = numeroDeTutelasH;
    }

    public MpAnuladaPrescripcion getMpPrescripcionAnulada() {
        return MpPrescripcionAnulada;
    }

    public void setMpPrescripcionAnulada(MpAnuladaPrescripcion MpPrescripcionAnulada) {
        this.MpPrescripcionAnulada = MpPrescripcionAnulada;
    }

    public HashMap<Integer, Ubicacion> getHashUbicaciones() {
        return hashUbicaciones;
    }

    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashUbicaciones) {
        this.hashUbicaciones = hashUbicaciones;
    }

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public HashMap<Integer, Ubicacion> getUbicacionesRecursiva() {
        return ubicacionesRecursiva;
    }

    public void setUbicacionesRecursiva(HashMap<Integer, Ubicacion> ubicacionesRecursiva) {
        this.ubicacionesRecursiva = ubicacionesRecursiva;
    }

    public List<Maestro> getListaTiposDocumentoPersona() {
        return listaTiposDocumentoPersona;
    }

    public void setListaTiposDocumentoPersona(List<Maestro> listaTiposDocumentoPersona) {
        this.listaTiposDocumentoPersona = listaTiposDocumentoPersona;
    }

    public HashMap<Integer, Maestro> getHashTiposGenero() {
        return hashTiposGenero;
    }

    public void setHashTiposGenero(HashMap<Integer, Maestro> hashTiposGenero) {
        this.hashTiposGenero = hashTiposGenero;
    }

    public MpPrescripcionAuditoria getPrescripcionAuditoriaRespaldo() {
        return prescripcionAuditoriaRespaldo;
    }

    public void setPrescripcionAuditoriaRespaldo(MpPrescripcionAuditoria prescripcionAuditoriaRespaldo) {
        this.prescripcionAuditoriaRespaldo = prescripcionAuditoriaRespaldo;
    }

    public MpPrescripcionRecobrante getPrescripcionRecobranteH() {
        return prescripcionRecobranteH;
    }

    public void setPrescripcionRecobranteH(MpPrescripcionRecobrante prescripcionRecobranteH) {
        this.prescripcionRecobranteH = prescripcionRecobranteH;
    }

    public MpPrescripcionInsumo getPrescripcionInsumo() {
        return prescripcionInsumo;
    }

    public void setPrescripcionInsumo(MpPrescripcionInsumo prescripcionInsumo) {
        this.prescripcionInsumo = prescripcionInsumo;
    }

    public List<MpMedicamentoPrincipioActivo> getListaPrincipiosActivos() {
        return listaPrincipiosActivos;
    }

    public void setListaPrincipiosActivos(List<MpMedicamentoPrincipioActivo> listaPrincipiosActivos) {
        this.listaPrincipiosActivos = listaPrincipiosActivos;
    }

    public List<MpPrescripcionProgramada> getListaPrescripcionProgramada() {
        return listaPrescripcionProgramada;
    }

    public void setListaPrescripcionProgramada(List<MpPrescripcionProgramada> listaPrescripcionProgramada) {
        this.listaPrescripcionProgramada = listaPrescripcionProgramada;
    }

    public List<MpProgramadaEntrega> getListaprogramadaEntrega() {
        return listaprogramadaEntrega;
    }

    public void setListaprogramadaEntrega(List<MpProgramadaEntrega> listaprogramadaEntrega) {
        this.listaprogramadaEntrega = listaprogramadaEntrega;
    }

    public List<MpDireccionamientoEntregado> getListaDireccionamientosEntregados() {
        return listaDireccionamientosEntregados;
    }

    public void setListaDireccionamientosEntregados(List<MpDireccionamientoEntregado> listaDireccionamientosEntregados) {
        this.listaDireccionamientosEntregados = listaDireccionamientosEntregados;
    }

    public List<MpNoDireccionado> getListanoDireccionados() {
        return listanoDireccionados;
    }

    public void setListanoDireccionados(List<MpNoDireccionado> listanoDireccionados) {
        this.listanoDireccionados = listanoDireccionados;
    }

    public MpPrescripcionTecnologia getPrescripcionTecnologia() {
        return prescripcionTecnologia;
    }

    public void setPrescripcionTecnologia(MpPrescripcionTecnologia prescripcionTecnologia) {
        this.prescripcionTecnologia = prescripcionTecnologia;
    }

    public List<MpDetalleDTO> getListaPrescripcionDtoH() {
        return listaPrescripcionDtoH;
    }

    public void setListaPrescripcionDtoH(List<MpDetalleDTO> listaPrescripcionDtoH) {
        this.listaPrescripcionDtoH = listaPrescripcionDtoH;
    }

    public MpPrescripcion getPrescripcionH() {
        return prescripcionH;
    }

    public void setPrescripcionH(MpPrescripcion prescripcionH) {
        this.prescripcionH = prescripcionH;
    }

    public boolean isTieneDireccionamiento() {
        return tieneDireccionamiento;
    }

    public void setTieneDireccionamiento(boolean tieneDireccionamiento) {
        this.tieneDireccionamiento = tieneDireccionamiento;
    }

    public boolean isTienePrincipioActivo() {
        return tienePrincipioActivo;
    }

    public void setTienePrincipioActivo(boolean tienePrincipioActivo) {
        this.tienePrincipioActivo = tienePrincipioActivo;
    }

    public boolean isTieneEntregas() {
        return tieneEntregas;
    }

    public void setTieneEntregas(boolean tieneEntregas) {
        this.tieneEntregas = tieneEntregas;
    }

    public boolean isTieneNoDireccionamientos() {
        return tieneNoDireccionamientos;
    }

    public void setTieneNoDireccionamientos(boolean tieneNoDireccionamientos) {
        this.tieneNoDireccionamientos = tieneNoDireccionamientos;
    }

    public MpPrescripcionMedicamento getPrescripcionMedicamento() {
        return prescripcionMedicamento;
    }

    public void setPrescripcionMedicamento(MpPrescripcionMedicamento prescripcionMedicamento) {
        this.prescripcionMedicamento = prescripcionMedicamento;
    }

    public List<MpPrescripcionAuditoria> getListaPrescripcionAuditoriaRespaldo() {
        return listaPrescripcionAuditoriaRespaldo;
    }

    public void setListaPrescripcionAuditoriaRespaldo(List<MpPrescripcionAuditoria> listaPrescripcionAuditoriaRespaldo) {
        this.listaPrescripcionAuditoriaRespaldo = listaPrescripcionAuditoriaRespaldo;
    }

    public List<MpDireccionamiento> getListadireccionamientosAux() {
        return listadireccionamientosAux;
    }

    public void setListadireccionamientosAux(List<MpDireccionamiento> listadireccionamientosAux) {
        this.listadireccionamientosAux = listadireccionamientosAux;
    }

    public List<MpMedicamentoIndicacionUnirs> getListaMpIndicacionUnirs() {
        return listaMpIndicacionUnirs;
    }

    public void setListaMpIndicacionUnirs(List<MpMedicamentoIndicacionUnirs> listaMpIndicacionUnirs) {
        this.listaMpIndicacionUnirs = listaMpIndicacionUnirs;
    }

    public List<MpHomologacion> getListaHomologaciones() {
        return listaHomologaciones;
    }

    public void setListaHomologaciones(List<MpHomologacion> listaHomologaciones) {
        this.listaHomologaciones = listaHomologaciones;
    }

    public HashMap<Integer, Maestro> getHashEstadosAfiliacion() {
        return hashEstadosAfiliacion;
    }

    public void setHashEstadosAfiliacion(HashMap<Integer, Maestro> hashEstadosAfiliacion) {
        this.hashEstadosAfiliacion = hashEstadosAfiliacion;
    }

    public HashMap<Integer, Maestro> getHashTiposDocumentoPersona() {
        return hashTiposDocumentoPersona;
    }

    public void setHashTiposDocumentoPersona(HashMap<Integer, Maestro> hashTiposDocumentoPersona) {
        this.hashTiposDocumentoPersona = hashTiposDocumentoPersona;
    }

    public List<MpDetalleDTO> getListaPrescripcionDto() {
        return listaPrescripcionDto;
    }

    public void setListaPrescripcionDto(List<MpDetalleDTO> listaPrescripcionDto) {
        this.listaPrescripcionDto = listaPrescripcionDto;
    }

    public List<MpPrescripcionHistorico> getListaPrescripcionHistoricos() {
        return listaPrescripcionHistoricos;
    }

    public void setListaPrescripcionHistoricos(List<MpPrescripcionHistorico> listaPrescripcionHistoricos) {
        this.listaPrescripcionHistoricos = listaPrescripcionHistoricos;
    }

    public MpPrescripcionRecobrante getPrescripcionRecobrante() {
        return prescripcionRecobrante;
    }

    public void setPrescripcionRecobrante(MpPrescripcionRecobrante prescripcionRecobrante) {
        this.prescripcionRecobrante = prescripcionRecobrante;
    }

    public List<MpPrescripcion> getPrescripcionListaHistoricos() {
        return prescripcionListaHistoricos;
    }

    public void setPrescripcionListaHistoricos(List<MpPrescripcion> prescripcionListaHistoricos) {
        this.prescripcionListaHistoricos = prescripcionListaHistoricos;
    }

    public String obtenerTipoEstadoPrescripcion(Integer valor) {
        String val = "";

        switch (valor) {
            case 1: {
                val = "Modificado";
            }
            break;
            case 2: {
                val = "Anulado";
            }
            break;
            case 4: {
                val = "Activo";
            }
            break;
            case 5: {
                val = "Solicitud Anulación";
            }
            break;

        }
        return val;
    }

    public String obtenerEstadoJuntaProfesional(int id) {
        String resultado;
        switch (id) {
            case MpDetalleDTO.ID_JUNTA_PROFESIONAL_NO_REQUIERE:
                resultado = "No requiere";
                break;
            case MpDetalleDTO.ID_JUNTA_PROFESIONAL_PENDIENTE:
                resultado = "Pendiente";
                break;
            case MpDetalleDTO.ID_JUNTA_PROFESIONAL_APROBADA:
                resultado = "Aprobada";
                break;
            case MpDetalleDTO.ID_JUNTA_PROFESIONAL_NO_APROBADA:
                resultado = "No Aprobada";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String obtenerEstado(int id) {
        String resultado = "";
        switch (id) {
            case MpDetalleDTO.ID_ESTADO_DIRECCIONADO:
                resultado = "Direccionado";
                break;
            case MpDetalleDTO.ID_ESTADO_NO_DIRECCIONADO:
                resultado = "No Direccionado";
                break;
            case MpDetalleDTO.ID_ESTADO_PENDIENTE:
                resultado = "Pendiente";
                break;
            case MpDetalleDTO.ID_ESTADO_ANULADO_DIRECCIONADO:
                resultado = "Auditado - Direccionado";
                break;
            case MpDetalleDTO.ID_ESTADO_ANULADO_NO_DIRECCIONADO:
                resultado = "Auditado - No direccionamiento";
                break;
            case MpDetalleDTO.ID_ESTADO_PROGRAMADO:
                resultado = "Ampliación justificación no pbs insuficiente";
                break;
            case MpDetalleDTO.ID_ESTADO_ENTREGADO:
                resultado = "Error en la prescripción: cantidad duración tratamiento";
                break;
            case MpDetalleDTO.ID_ESTADO_REPORTADO:
                resultado = "Avalado Hos_Urg";
                break;
            case 9:
                resultado = "No Avalado Hos_Urg";
                break;
            case 10:
                resultado = "No direccionamiento sin causa";
                break;
            case 11:
                resultado = "Pendiente De Cotizaciòn";//No direccionamiento sin causa
                break;
            case 12:
                resultado = "Con Cotización";//No direccionamiento sin causa
                break;
            case 13:
                resultado = "Rechazo De Cotización";//
                break;
            case 27:
                resultado = "Inicio de atencion";
                break;
            case 50:
                resultado = "Direccionamiento parcial";
                break;
            case 51:
                resultado = "Direccionamiento diferido";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String obtenerConfirmacion(boolean id) {
        String resultado;
        if (id) {
            resultado = "SI";
        } else {
            resultado = "NO";
        }
        return resultado;
    }

    public String calcularDecimales(BigDecimal valor) {
        if (valor == null) {
            return "-- -- --";
        }
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        numberFormat.setMaximumFractionDigits(2);

        String valorFormateado = numberFormat.format(valor);

        if (valor.scale() == 0) {
            return valor.toBigInteger().toString();
        } else if (valorFormateado.endsWith(".00")) {
            valorFormateado = valorFormateado.substring(0, valorFormateado.length() - 3);
        }

        return valorFormateado;
    }

    public String obtenerEstadoAnulacion(int valor) {
        String resultado = "";
        switch (valor) {
            case 1:
                resultado = "Modificado";
                break;
            case 2:
                resultado = "Anulado";
                break;
            case 4:
                resultado = "Activo";
                break;
            default:
                break;
        }
        return resultado;
    }

    public String obtenerTipoAnulacion(int valor) {
        String resultado = "";
        switch (valor) {
            case 1:
                resultado = "Por Solicitud De La IPS";
                break;
            case 2:
                resultado = "Por Solicitud De La EPS";
                break;
            case 3:
                resultado = "Por Solicitud Del Prescriptor";
                break;
            default:
                break;
        }
        return resultado;
    }

    public String obtenerTipoMedicamento(int id) {
        String resultado = "";
        switch (id) {
            case MpDetalleDTO.ID_TIPO_MEDICAMENTO_MEDICAMENTO:
                resultado = "Medicamento";
                break;
            case MpDetalleDTO.ID_TIPO_MEDICAMENTO_VITAL_NO_DISPO:
                resultado = "Vital No Disponible";
                break;
            case MpDetalleDTO.ID_TIPO_MEDICAMENTO_PREPARA_MAGISTRAL:
                resultado = "Preparación Magistral";
                break;
            case MpDetalleDTO.ID_TIPO_MEDICAMENTO_UNIRS:
                resultado = "UNIRS";
                break;
            case MpDetalleDTO.ID_TIPO_MEDICAMENTO_URGENCIA_MEDICA:
                resultado = "Urgencia Médica(Solo Transcripción)";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String obtenerTipoTecnologia(int id) {
        String resultado = "";
        switch (id) {
            case MpDetalleDTO.ID_TIPO_TECNOLOGIA_MEDICAMENTO1:
                resultado = "Medicamento";
                break;
            case MpDetalleDTO.ID_TIPO_TECNOLOGIA_PROCEDIMIENTOS1:
                resultado = "Procedimientos";
                break;
            case MpDetalleDTO.ID_TIPO_TECNOLOGIA_DISPOSITIVO1:
                resultado = "Dispositivos";
                break;
            case MpDetalleDTO.ID_TIPO_TECNOLOGIA_PRODUCTOS_NUTRICIONALES1:
                resultado = "Productos Nutricionales";
                break;
            case MpDetalleDTO.ID_TIPO_TECNOLOGIA_SERVICIO_COMPLEMENTARIO1:
                resultado = "Servicios Complementarios";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public void mostrarAfiliadoGeneral() {
        PrimeFaces.current().resetInputs("frmAfiliadoGeneral");
        PrimeFaces.current().ajax().update("frmAfiliadoGeneral");
        PrimeFaces.current().executeScript("PF('dlgInfoAfiliado').show();");
    }

    public String obtenerTipoPrestacion(int id) {
        String resultado = "";
        switch (id) {
            case MpDetalleDTO.ID_TIPO_PRESTACION_UNICA:
                resultado = "Unica";
                break;
            case MpDetalleDTO.ID_TIPO_PRESTACION_SUCESIVA:
                resultado = "Sucesiva";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String establecerColorPrecripcion(Integer valor) {
        String color = "";
        switch (valor) {
            case 1:
                color = "celeste";
                break;
            case 2:
                color = "rojo";
                break;
            case 4:
                color = "verde";
                break;
            case 5:
                color = "naranja";
                break;
            default:
                break;
        }

        return color;
    }

    public String obtenerAmbito(String id) {
        String resultado;

        switch (id) {
            case MpPrescripcionDetalle.ID_AMBITO_AMBULATORIO_PRIORIZADO:
                resultado = "Ambulatorio - Priorizado";
                break;
            case MpPrescripcionDetalle.ID_AMBITO_AMBULATORIO_NO_PRIORIZADO:
                resultado = "Ambulatorio - No Priorizado";
                break;
            case MpPrescripcionDetalle.ID_AMBITO_HOSPITALARIO_DOMICILIARIO:
                resultado = "Hospitalario - Domiciliario";
                break;
            case MpPrescripcionDetalle.ID_AMBITO_HOSPITALARIO_INTERNACION:
                resultado = "Hospitalario - Internacion";
                break;
            case MpPrescripcionDetalle.ID_AMBITO_URGENCIAS:
                resultado = "Urgencias";
                break;
            default:
                resultado = "Recobrante";
                break;
        }
        if (objetoPrescripcion.getReferenciaAmbitoAtencion() != null) {
            if (objetoPrescripcion.getReferenciaAmbitoAtencion() == true) {
                resultado = resultado + " - Referencia Contrarreferencia";
            }
        }

        return resultado;

    }

    public String obtenerTipoDocumentoPersona(Integer id) {
        try {
            if (id != null && id != 0) {
                return hashTiposDocumentoPersona.get(id).getNombre();
            } else {
                return "";
            }
        } catch (Exception e) {
            return String.valueOf(id);
        }
    }

    public String obtenerTipoGenero(String id) {
        String resultado;
        switch (id) {
            case "1":
                resultado = "Masculino";
                break;
            case "2":
                resultado = "Femenino";
                break;
            default:
                resultado = "Sin genero asignado";
                break;
        }
        return resultado;
    }

    public String obtenerEdad(Date fechaNacimiento) {
        String resultado = "";
        if (fechaNacimiento != null) {
            String fechaNacimientoStr = fechaNacimiento.toString();
            LocalDate fechaNacimientoD = LocalDate.parse(fechaNacimientoStr);
            LocalDate fechaActual = LocalDate.now();
            Period periodo = Period.between(fechaNacimientoD, fechaActual);

            if (periodo.getYears() > 0) {
                resultado = periodo.getYears() + " años";
            } else if (periodo.getMonths() > 0) {
                resultado = periodo.getMonths() + " meses";
            } else {
                resultado = periodo.getDays() + " días";
            }
        } else {
            resultado = "";
        }

        return resultado;
    }

    public String obtenerEstadoAfiliacion(int id) {
        try {
            if (id != 0) {
                return hashEstadosAfiliacion.get(id).getNombre();
            } else {
                return "";
            }
        } catch (Exception e) {
            return String.valueOf(id);
        }
    }

    public String obtenerMunicipio(int id) {
        try {
            return hashUbicaciones.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public String establecerAmbito(String codAmbito) {
        String obtenerAmbito = "";
        if (codAmbito != null && !codAmbito.isEmpty()) {
            switch (codAmbito) {
                case ID_AMBITO_AMBULATORIO_PRIORIZADO:
                    obtenerAmbito = "Ambulatorio - Priorizado";
                    break;
                case ID_AMBITO_AMBULATORIO_NO_PRIORIZADO:
                    obtenerAmbito = "Ambulatorio - No Priorizado";
                    break;
                case ID_AMBITO_HOSPITALARIO_DOMICILIARIO:
                    obtenerAmbito = "Hospitalario - Domiciliario";
                    break;
                case ID_AMBITO_HOSPITALARIO_INTERNACION:
                    obtenerAmbito = "Hospitalario - Internacion";
                    break;
                case ID_AMBITO_URGENCIAS:
                    obtenerAmbito = "Urgencias";
                    break;
                case "":
                    obtenerAmbito = "Tutela";
                    break;
            }
        } else {
            obtenerAmbito = "Tutela";
        }

        return obtenerAmbito;
    }

    public String asignarRegimen(String valor) {
        String val = "";
        if (valor != null) {
            if ("1".equals(valor)) {
                val = "Subsidiado";
            } else {
                val = "Contributivo";
            }

        }
        return val;
    }

    public void ver(int _id) {
        setNumeroDeTutelas(0);
        setObjetoPrescripcion(new MpPrescripcion());
        setMpPrescripcionAnulada(new MpAnuladaPrescripcion());
        listaPrescripcionDto = new ArrayList<>();
        listaPrescripcionHistoricos = new ArrayList<>();
        getObjetoPrescripcion().setId(_id);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER);
        getPrestadorMipresvicio().Accion(this);
        if (MpPrescripcionAnulada != null) {
            tieneAnulacion = true;
        } else {
            tieneAnulacion = false;
        }
        if (prescripcionListaHistoricos.size() == 1) {
            mostrarAlertaPrimeraV = true;
        } else {
            mostrarAlertaPrimeraV = false;
        }
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void verItem(int _id, int tipoTecnologia, int origen) {
        tieneAuditoria = false;
        prescripcionAuditoriaRespaldo = new MpPrescripcionAuditoria();
        listaPrescripcionHistoricos = new ArrayList<>();
        listaPrincipiosActivos = new ArrayList<>();
        listaPrescripcionProgramada = new ArrayList<>();
        listaprogramadaEntrega = new ArrayList<>();
        listaDireccionamientosEntregados = new ArrayList<>();
        listaMpIndicacionUnirs = new ArrayList<>();

        getObjetoPrescripcion().setIdItem(_id);
        getObjetoPrescripcion().setTipoTecnologiaItem((tipoTecnologia));

        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_ADICIONAL_1);

        getPrestadorMipresvicio().Accion(this);
        switch (tipoTecnologia) {
            case 1:
                PrimeFaces.current().ajax().update("frmVerItemMM");
                PrimeFaces.current().executeScript("PF('dialogoVerItemMM').show()");

                break;
            case 4:
                PrimeFaces.current().ajax().update("frmVerItemM");
                PrimeFaces.current().executeScript("PF('dialogoVerItemM').show()");

                break;
            case 2:
                PrimeFaces.current().ajax().update("frmVerItemT");
                PrimeFaces.current().executeScript("PF('dialogoVerItemT').show()");

                break;
            case 3:
                PrimeFaces.current().ajax().update("frmVerItemII");
                PrimeFaces.current().executeScript("PF('dialogoVerItemII').show()");

                break;
            case 5:
                PrimeFaces.current().ajax().update("frmVerItemI");
                PrimeFaces.current().executeScript("PF('dialogoVerItemI').show()");

                break;
        }
        procesoFinal();
    }

    public String optenerEstadoJunta(int id) {
        String resultado;
        switch (id) {
            case 1:
                resultado = "No Requiere";
                break;
            case 2:
                resultado = "Pendiente";
                break;
            case 3:
                resultado = "Aprobada";
                break;
            case 4:
                resultado = "No Aprobada";
                break;
            default:
                resultado = "Sin Estado Junta";
                break;
        }
        return resultado;
    }

    public String obtenerHomologacion(String codigo, int tipo) {
        String resultado = "";
        for (MpHomologacion homologacion : listaHomologaciones) {
            if (homologacion.getTipo() == tipo) {
                if (homologacion.getCodigo().equals(codigo)) {
                    resultado = homologacion.getNombre();
                }
            }
        }
        return resultado;
    }

    public String obtenerModJunta(String valor) {
        String resultado = "";
        if (valor != null) {
            switch (valor) {
                case "1":
                    resultado = "Presencial";
                    break;
                case "2":
                    resultado = "Virtual";
                    break;

            }
        } else {
            resultado = "";
        }

        return resultado;

    }

    public String optenerTipoPrestacion(int tipo) {
        String resultado;
        switch (tipo) {
            case 1:
                resultado = "Única";
                break;
            case 2:
                resultado = "Sucesiva";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public void verH(String documento, Integer id) {

        if (documento != null && id != null) {
            // Si no hay valores previos, asignar directamente
            if (documentoAfiliado == null && idPRescripcion == null) {
                documentoAfiliado = documento;
                idPRescripcion = id;
            } // Si ya existen valores pero son diferentes, actualizar
            else if (!documento.equals(documentoAfiliado) || !id.equals(idPRescripcion)) {
                documentoAfiliado = documento;
                idPRescripcion = id;
            }
        }

        setObjetoPrescripcion(new MpPrescripcion());
        listaPrescripcionHistoricos = new ArrayList<>();
        getObjetoPrescripcion().setId(idPRescripcion);
        getObjetoPrescripcion().setAsegAfiliado(new AsegAfiliado());
        getObjetoPrescripcion().getAsegAfiliado().setNumeroDocumento(documentoAfiliado);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_ADICIONAL_2);
        getPrestadorMipresvicio().Accion(this);
        PrimeFaces.current().ajax().update("frmHistori");
        PrimeFaces.current().executeScript("PF('dialogoVerHistoricoNuevo').show()");
        procesoFinal();
    }

    public String optenerFrecuenciaUso(int tipo) {
        String resultado;
        switch (tipo) {
            case 1:
                resultado = "Minuto(s)";
                break;
            case 2:
                resultado = "Hora(s)";
                break;
            case 3:
                resultado = "Día(s)";
                break;
            case 4:
                resultado = "Semana(s)";
                break;
            case 5:
                resultado = "Mes(es)";
                break;
            case 6:
                resultado = "Año(s)";
                break;
            case 7:
                resultado = "Seguns respuesta al tratamiento";
                break;
            case 8:
                resultado = "Única";
                break;
            case 0:
                resultado = "Única";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String optenerDuracionTratamiento(int tipo) {
        String resultado;
        switch (tipo) {
            case 1:
                resultado = "Minuto(s)";
                break;
            case 2:
                resultado = "Hora(s)";
                break;
            case 3:
                resultado = "Día(s)";
                break;
            case 4:
                resultado = "Semana(s)";
                break;
            case 5:
                resultado = "Mes(es)";
                break;
            case 6:
                resultado = "Año(s)";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String optenerViaAdminPNutri(String tipo) {
        String resultado;
        switch (tipo) {
            case "1":
                resultado = "Oral";
                break;
            case "2":
                resultado = "Sonda";
                break;
            default:
                resultado = "N/A";
                break;
        }
        return resultado;
    }

    public String optenerPresentacionPNutri(String tipo) {
        String resultado;
        switch (tipo) {
            case "1":
                resultado = "Bolsa";
                break;
            case "2":
                resultado = "Bolsa ultrapack";
                break;
            case "3":
                resultado = "Botella";
                break;
            case "4":
                resultado = "EasyBag";
                break;
            case "5":
                resultado = "Lata";
                break;
            case "6":
                resultado = "LOC";
                break;
            case "7":
                resultado = "LPC";
                break;
            case "8":
                resultado = "LPM";
                break;
            case "9":
                resultado = "Sobre";
                break;
            case "10":
                resultado = "Tetraprisma";
                break;
            case "11":
                resultado = "Ultrapack";
                break;
            case "12":
                resultado = "Frasco";
                break;
            default:
                resultado = "N/A";
                break;
        }
        return resultado;
    }

    public String optenerIndicacionesEspeciales(int tipo) {
        String resultado;
        switch (tipo) {
            case 1:
                resultado = "Administración en dosis única";
                break;
            case 2:
                resultado = "Administración inmediata";
                break;
            case 3:
                resultado = "Administrar en Bolo";
                break;
            case 4:
                resultado = "Administrar en Goteo";
                break;
            case 5:
                resultado = "Infusión continua";
                break;
            case 6:
                resultado = "Infusión intermitente";
                break;
            case 7:
                resultado = "Infusión intermitente simultánea con perfusión de otra solución";
                break;
            case 8:
                resultado = "Microgoteo";
                break;
            case 9:
                resultado = "Perfusión";
                break;
            case 10:
                resultado = "Sin indicación especial";
                break;
            default:
                resultado = "Sin indicación especial";
                break;
        }
        return resultado;
    }

    public String optenerTipoTransporte(int id) {
        String resultado;
        switch (id) {
            case 1:
                resultado = "Terrestre";
                break;
            case 2:
                resultado = "Maritimo";
                break;
            case 3:
                resultado = "Aereo";
                break;

            default:
                resultado = "Sin transporte asignado";
                break;
        }
        return resultado;
    }

    public Integer desimalConvertido(BigDecimal valor) {
        Integer resultado = valor.intValue();
        return resultado;
    }

    public String obtenerAmbitoHis(String id) {
        String resultado;
        if (id != null && !id.equals("")) {

            switch (id) {
                case MpPrescripcionDetalle.ID_AMBITO_AMBULATORIO_PRIORIZADO:
                    resultado = "Ambulatorio - Priorizado";
                    break;
                case MpPrescripcionDetalle.ID_AMBITO_AMBULATORIO_NO_PRIORIZADO:
                    resultado = "Ambulatorio - No Priorizado";
                    break;
                case MpPrescripcionDetalle.ID_AMBITO_HOSPITALARIO_DOMICILIARIO:
                    resultado = "Hospitalario - Domiciliario";
                    break;
                case MpPrescripcionDetalle.ID_AMBITO_HOSPITALARIO_INTERNACION:
                    resultado = "Hospitalario - Internacion";
                    break;
                case MpPrescripcionDetalle.ID_AMBITO_URGENCIAS:
                    resultado = "Urgencias";
                    break;
                default:
                    resultado = "";
                    break;
            }

        } else {
            resultado = "Recobrante";
        }
        return resultado;
    }

    public String fechaFormat(Date fecha) {
        String resultado = "";
        Date fechaPrescripcion = fecha;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("America/Bogota"));
        resultado = sdf.format(fechaPrescripcion);
        return resultado;
    }

    public void verHistoricoP(int id) {
        listaPrescripcionDtoH = new ArrayList<>();
        getPrestadorMipresvicio().verHistorico(this, id);
        PrimeFaces.current().ajax().update("frmVerH");
        PrimeFaces.current().executeScript("PF('dialogoVerPh').show()");
        crearLog("Ver Historico Prescripción", prescripcionH.toString());
        generarMensajes();
    }

    public String horaFormat(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        return sdf.format(fecha);
    }

    public Date asignarMinDate() {
        return new Date();
    }

    public Date asignarMaxDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        return cal.getTime();
    }

    public StreamedContent generarPdf(Integer id) {
        StreamedContent streamContent = null;
        try {
            ReporteDireccionamiento objeto = getPrestadorMipresvicio().consultarDireccionamientoReporte(id);

            if (objeto != null) {
                InputStream is = getClass().getResourceAsStream("/reportes/MipresDireccionamiento.jasper");

                Map<String, Object> parameters = new HashMap<>();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));

                // Crear la fuente de datos con el objeto
                List<ReporteDireccionamiento> dataList = Collections.singletonList(objeto);
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);

                // Generar el reporte
                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0);
                streamContent = DefaultStreamedContent.builder()
                        .contentType("application/pdf")
                        .stream(() -> stream)
                        .name("Reporte De Direccionamiento.pdf")
                        .build();
            } else {
                addError("Error: no hay datos para generar el reporte");
            }

        } catch (JRException ex) {
            streamContent = null;
            addError("Error Reporte: " + ex.toString() + " " + ex.getMessage());
            generarMensajes();
        }

        return streamContent;
    }

    public String obtenerTipoEstado(Integer valor) {
        String val = "";

        switch (valor) {
            case 0: {
                val = "Anulado";
            }
            break;
            case 1: {
                val = "Direccionado";
            }
            break;
            case 2: {
                val = "Programado";
            }
            break;
            case 3: {
                val = "Creado";
            }
            break;
            case 5: {
                val = "Anulado";
            }
            break;
            case 6: {
                val = "Programación-Anulada";
            }
            break;

        }
        return val;
    }

    public boolean validarResiduoDecimal(String valor) {
        BigDecimal valorDecimal;
        if (valor.isEmpty()) {
            return true;
        } else {
            valorDecimal = new BigDecimal(valor);
        }
        return this.validarResiduoDecimal(valorDecimal);
    }

    public boolean validarResiduoDecimal(BigDecimal valor) {
        if (valor.remainder(new BigDecimal(1)).compareTo(BigDecimal.ZERO) == 0) {
            return false;
        }
        return true;
    }

    public Integer convertirTextoAEntero(String valor) {
        BigDecimal valorDecimal = new BigDecimal(valor);
        return valorDecimal.intValue();
    }

    public String valorEstadoF(short id) {
        String valor = "";
        try {
            if (id != 0) {
                if (id == 1) {
                    valor = "Activo";
                } else if (id == 2) {
                    valor = "Procesado";
                } else if (id == 0) {
                    valor = "Anulado";
                }
            } else {
                return "";
            }
        } catch (Exception e) {
            return String.valueOf(id);
        }
        return valor;
    }

    public String obtenerRegimen(String id) {
        String resultado;
        switch (id) {
            case "1":
                resultado = "Subsidiado";
                break;
            case "2":
                resultado = "Contributivo";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String validarEstadoEntre(Integer valor) {
        String resultado = "";
        switch (valor) {
            case 0:
                resultado = "No Entregado";
                break;
            case 1:
                resultado = "Entregado";
                break;
            default:
                break;
        }
        return resultado;
    }
    
     public String obtenerEstadoAuditado(int id) {
        String resultado = "";
        switch (id) {
            case 1:
                resultado = "Auditado - Direccionado";
                break;
            case 2:
                resultado = "Auditado - No direccionamiento";
                break;
            case 3:
                resultado = "Ampliación justificación no pbs insuficiente o invalida para definir el aval de la tecnologí­a";
                break;
            case 5:
                resultado = "Avalado Hos_Urg";
                break;
        }
        return resultado;
    }
     public String obtenerTecno(int id) {
        String resultado = "";
        switch (id) {
            case 1:
                resultado = "Medicamento";
                break;
            case 2:
                resultado = "Procedimiento";
                break;

            case 3:
                resultado = "Dispositivo";
                break;

            case 4:
                resultado = "Producto nutricional";
                break;
            case 5:
                resultado = "Servicio complementario";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String validarEstadoReporteEntrega(Integer valor) {
        String resultado = "";
        switch (valor) {
            case 0:
                resultado = "Anulado";
                break;
            case 1:
                resultado = "Activo";
                break;
            case 2:
                resultado = "Procesado";
                break;
            default:
                break;
        }
        return resultado;
    }

    public String esUltimaEntrega(Integer valor) {
        String resultado = "";
        switch (valor) {
            case 0:
                resultado = "No";
                break;
            case 1:
                resultado = "Si";
                break;
            default:
                break;
        }
        return resultado;
    }

}
