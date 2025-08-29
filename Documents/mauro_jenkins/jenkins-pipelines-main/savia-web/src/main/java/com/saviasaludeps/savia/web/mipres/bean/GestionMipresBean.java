/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.mipres.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.mipres.MpEntregaSuministro;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionProgramada;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionRecobrante;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.dominio.mipres.MpProgramadaEntrega;
import com.saviasaludeps.savia.web.mipres.bean.DTO.MpDetalleDTO;
import com.saviasaludeps.savia.web.mipres.servicio.GestionMipresIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author Jaime Andres Olarte
 */
@ManagedBean
@ViewScoped
public class GestionMipresBean extends Url {

    @Autowired
    private GestionMipresIface gestionMipresServicio;
    private MpPrescripcion objeto;
    private MpDetalleDTO objetoDto;
    private MpPrescripcionTecnologia objetoTecnologia;
    private MpPrescripcionInsumo objetoInsumo;
    private MpPrescripcionMedicamento objetoMedicamento;
    private MpProgramadaEntrega objetoEntrega;
    private MpPrescripcionProgramada objetoProgramada;
    private MpPrescripcionRecobrante objetoRecobrante;
    private List<MpPrescripcion> registros;
    private LazyDataModel<MpPrescripcion> lazyRegistros;
    private List<Maestro> listaTiposDocumentoPersona;
    private List<MpPrescripcionProgramada> listaMpPrescripcionProgramadas;
    private List<MpProgramadaEntrega> listaMpPrescripcionEntregadas;
    private List<MpEntregaSuministro> listaEntregaSuministros;
    private HashMap<Integer, Maestro> hashTiposDocumentoPersona;
    private HashMap<Integer, Maestro> hashTiposGenero;
    private HashMap<Integer, Maestro> hashEstadosAfiliacion;
    private List<MpDetalleDTO> listaMpDetalleDTO;
    
    //Variables para ubicaciones
    private HashMap<Integer, Ubicacion> hashUbicaciones;

    public GestionMipresBean() {
        this.objeto = new MpPrescripcion();
        Modulo mod = super.validarModulo(Modulo.ID_GESTION_MIPRES);

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
                public MpPrescripcion getRowData(String refAnexo9Id) {
                    Integer id = Integer.valueOf(refAnexo9Id);
                    for (MpPrescripcion refAnexo9 : listaMpPrescripcion) {
                        if (id.equals(refAnexo9.getId())) {
                            return refAnexo9;
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
        getGestionMipresServicio().cargaInicial(this);
    }

    //Acciones
    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getGestionMipresServicio().accion(this);
        procesoFinal();
    }

    public void ver(int id) {
        setObjeto(new MpPrescripcion());
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER);
        getGestionMipresServicio().accion(this);
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void verProgramadas(int id, int tipoTecnologia) {
        setObjetoDTO(new MpDetalleDTO());
        getObjetoDto().setId(id);
        getObjetoDto().setTipoTecnologiaId(tipoTecnologia);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR);
        getGestionMipresServicio().accion(this);

        if (getObjetoDto().getTipoTecnologiaId() == MpDetalleDTO.ID_TIPO_TECNOLOGIA_MEDICAMENTO1 || getObjetoDto().getTipoTecnologiaId() == MpDetalleDTO.ID_TIPO_TECNOLOGIA_PRODUCTOS_NUTRICIONALES1) {
            PrimeFaces.current().executeScript("PF('dialogoProgramadasMed').show()");
        } else if (getObjetoDto().getTipoTecnologiaId() == MpDetalleDTO.ID_TIPO_TECNOLOGIA_PROCEDIMIENTOS1) {
            PrimeFaces.current().executeScript("PF('dialogoProgramadasTecn').show()");
        } else if (getObjetoDto().getTipoTecnologiaId() == MpDetalleDTO.ID_TIPO_TECNOLOGIA_DISPOSITIVO1 || getObjetoDto().getTipoTecnologiaId() == MpDetalleDTO.ID_TIPO_TECNOLOGIA_SERVICIO_COMPLEMENTARIO1) {
            PrimeFaces.current().executeScript("PF('dialogoProgramadasInsu').show()");
        }
        procesoFinal();
    }

    public void verEntregadas(int id, int tipoTecnologia) {
        setObjetoDTO(new MpDetalleDTO());
        getObjetoDto().setId(id);
        getObjetoDto().setTipoTecnologiaId(tipoTecnologia);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_ADICIONAL_1);
        getGestionMipresServicio().accion(this);
        PrimeFaces.current().executeScript("PF('dialogoEntregada').show()");
        procesoFinal();
    }

    public void verSuministradas(int id) {
        setObjetoEntrega(new MpProgramadaEntrega(id));
        //getObjetoEntrega().setMpPrescripcionProgramada(new MpPrescripcionProgramada(id));
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_ADICIONAL_2);
        getGestionMipresServicio().accion(this);
        PrimeFaces.current().executeScript("PF('dialogoSuministrada').show()");
        procesoFinal();
    }

    //Obtener maestro
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
    
    public String obtenerRecobrante(String id) {
        String resultado;
        switch (id) {
            case "0":
                resultado = "NO";
                break;
            case "1":
                resultado = "SI";
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

    public String obtenerTipoGenero(String id) {
        try {
            return hashTiposGenero.get(Integer.parseInt(id)).getNombre();
        } catch (NumberFormatException e) {
            return id;
        }
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

    public String obtenerAmbito(String id) {
        String resultado;
        switch (id) {
            case MpPrescripcion.ID_AMBITO_AMBULATORIO_PRIORIZADO:
                resultado = "Ambulatorio - Priorizado";
                break;
            case MpPrescripcion.ID_AMBITO_AMBULATORIO_NO_PRIORIZADO:
                resultado = "Ambulatorio - No Priorizado";
                break;
            case MpPrescripcion.ID_AMBITO_HOSPITALARIO_DOMICILIARIO:
                resultado = "Hospitalario - Domiciliario";
                break;
            case MpPrescripcion.ID_AMBITO_HOSPITALARIO_INTERNACION:
                resultado = "Hospitalario - Internacion";
                break;
            case MpPrescripcion.ID_AMBITO_URGENCIAS:
                resultado = "Urgencias";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
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
                resultado = "Anulado Direccionado";
                break;
            case MpDetalleDTO.ID_ESTADO_ANULADO_NO_DIRECCIONADO:
                resultado = "Anulado no Direccionado";
                break;
            case MpDetalleDTO.ID_ESTADO_PROGRAMADO:
                resultado = "Programado";
                break;
            case MpDetalleDTO.ID_ESTADO_ENTREGADO:
                resultado = "Entregado";
                break;
            case MpDetalleDTO.ID_ESTADO_REPORTADO:
                resultado = "Reportado";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String obtenerFrecuenciaTratamiento(int id) {
        String resultado = "";
        switch (id) {
            case MpDetalleDTO.ID_FRECUENCIA_TRATAMIENTO_MINUTOS:
                resultado = "Minuto(s)";
                break;
            case MpDetalleDTO.ID_FRECUENCIA_TRATAMIENTO_HORAS:
                resultado = "Hora(s)";
                break;
            case MpDetalleDTO.ID_FRECUENCIA_TRATAMIENTO_DIAS:
                resultado = "Día(s)";
                break;
            case MpDetalleDTO.ID_FRECUENCIA_TRATAMIENTO_SEMANAS:
                resultado = "Semana(s)";
                break;
            case MpDetalleDTO.ID_FRECUENCIA_TRATAMIENTO_MESES:
                resultado = "Mes(es)";
                break;
            case MpDetalleDTO.ID_FRECUENCIA_TRATAMIENTO_ANIO:
                resultado = "Año";
                break;
            case MpDetalleDTO.ID_FRECUENCIA_TRATAMIENTO_RTA:
                resultado = "Según respuesta al tratamiento";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String obtenerIndicacionEspacial(int id) {
        String resultado = "";
        switch (id) {
            case MpDetalleDTO.ID_INDICACIONES_UNICA:
                resultado = "Administración en dosis única";
                break;
            case MpDetalleDTO.ID_INDICACIONES_INMEDIATA:
                resultado = "Administración inmediata";
                break;
            case MpDetalleDTO.ID_INDICACIONES_BOLO:
                resultado = "Administrar en Bolo";
                break;
            case MpDetalleDTO.ID_INDICACIONES_GOTEO:
                resultado = "Administrar en Goteo";
                break;
            case MpDetalleDTO.ID_INDICACIONES_CONTINUA:
                resultado = "Infusión continua";
                break;
            case MpDetalleDTO.ID_INDICACIONES_INTERMITENTE:
                resultado = "Infusión intermitente";
                break;
            case MpDetalleDTO.ID_INDICACIONES_INTERMITENTE_OTRA:
                resultado = "Infusión intermitente simultánea con perfusión de otra solución";
                break;
            case MpDetalleDTO.ID_INDICACIONES_MICROGOTEO:
                resultado = "Microgoteo";
                break;
            case MpDetalleDTO.ID_INDICACIONES_PERFUSION:
                resultado = "Perfusión";
                break;
            case MpDetalleDTO.ID_INDICACIONES_SIN_INDICACION:
                resultado = "Sin indicación especial";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String obtenerEstadoEntregada(int id) {
        String resultado = "";
        switch (id) {
            case MpDetalleDTO.ID_ESTADO_ENTREGA_PARCIAL:
                resultado = "Parcial";
                break;
            case MpDetalleDTO.ID_ESTADO_ENTREGA_TOTAL:
                resultado = "Total";
                break;
            case MpDetalleDTO.ID_ESTADO_ENTREGA_DIFERIDA:
                resultado = "Diferida";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_VER:
                switch (getDoAccion()) {
                    case Url.ACCION_VER:
                        PrimeFaces.current().ajax().update("frmVer");
                        PrimeFaces.current().ajax().update("frmAuditoriaVer");
                        crearLog(getObjeto().toString());
                        break;
                    case Url.ACCION_LISTAR:
                        if (getObjetoDto().getTipoTecnologiaId() == MpDetalleDTO.ID_TIPO_TECNOLOGIA_MEDICAMENTO1 || getObjetoDto().getTipoTecnologiaId() == MpDetalleDTO.ID_TIPO_TECNOLOGIA_PRODUCTOS_NUTRICIONALES1) {
                            PrimeFaces.current().ajax().update("frmProgramadasMed");
                            PrimeFaces.current().ajax().update("frmAuditoriaProgramadasMed");
                            if (getObjetoMedicamento() != null) {
                                crearLog("Ver Programadas Medicamentos", getObjetoMedicamento().toString());
                            }
                        } else if (getObjetoDto().getTipoTecnologiaId() == MpDetalleDTO.ID_TIPO_TECNOLOGIA_PROCEDIMIENTOS1) {
                            PrimeFaces.current().ajax().update("frmProgramadasTecn");
                            PrimeFaces.current().ajax().update("frmAuditoriaProgramadasTecn");
                            if (getObjetoTecnologia() != null) {
                                crearLog("Ver Programadas Medicamentos", getObjetoTecnologia().toString());
                            }
                        } else if (getObjetoDto().getTipoTecnologiaId() == MpDetalleDTO.ID_TIPO_TECNOLOGIA_DISPOSITIVO1 || getObjetoDto().getTipoTecnologiaId() == MpDetalleDTO.ID_TIPO_TECNOLOGIA_SERVICIO_COMPLEMENTARIO1) {
                            PrimeFaces.current().ajax().update("frmProgramadasInsu");
                            PrimeFaces.current().ajax().update("frmAuditoriaProgramadasInsu");
                            if (getObjetoEntrega() != null) {
                                crearLog("Ver Programadas Insumos", getObjetoInsumo().toString());
                            }
                        }
                        break;

                    case Url.ACCION_ADICIONAL_1:
                        PrimeFaces.current().ajax().update("frmEntregadas");
                        PrimeFaces.current().ajax().update("frmAuditoriaEntregada");
                        if (getObjetoEntrega() != null) {
                            crearLog("Ver Programadas Entregadas", getObjetoEntrega().toString());
                        }
                        break;
                    case Url.ACCION_ADICIONAL_2:
                        PrimeFaces.current().ajax().update("frmSuministradas");
                        if (getObjetoEntrega() != null) {
                            crearLog("Ver Entregas Suministradas", getObjetoEntrega().toString());
                        }
                        break;
                    default:
                        break;
                }
                break;
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                break;
            default:
                break;
        }
    }

    public List<MpProgramadaEntrega> getListaMpPrescripcionEntregadas() {
        return listaMpPrescripcionEntregadas;
    }

    /* GETTER - SETTERS */
    public GestionMipresIface getGestionMipresServicio() {
        return gestionMipresServicio;
    }

    public void setGestionMipresServicio(GestionMipresIface gestionMipresServicio) {
        this.gestionMipresServicio = gestionMipresServicio;
    }

    public MpPrescripcion getObjeto() {
        return objeto;
    }

    public void setObjeto(MpPrescripcion objeto) {
        this.objeto = objeto;
    }

    public List<MpPrescripcion> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MpPrescripcion> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MpPrescripcion> getLazyRegistros() {
        return lazyRegistros;
    }

    public void setLazyRegistros(LazyDataModel<MpPrescripcion> lazyRegistros) {
        this.lazyRegistros = lazyRegistros;
    }

    public List<Maestro> getListaTiposDocumentoPersona() {
        return listaTiposDocumentoPersona;
    }

    public void setListaTiposDocumentoPersona(List<Maestro> listaTiposDocumentoPersona) {
        this.listaTiposDocumentoPersona = listaTiposDocumentoPersona;
    }

    public HashMap<Integer, Maestro> getHashTiposDocumentoPersona() {
        return hashTiposDocumentoPersona;
    }

    public void setHashTiposDocumentoPersona(HashMap<Integer, Maestro> hashTiposDocumentoPersona) {
        this.hashTiposDocumentoPersona = hashTiposDocumentoPersona;
    }

    public HashMap<Integer, Maestro> getHashTiposGenero() {
        return hashTiposGenero;
    }

    public void setHashTiposGenero(HashMap<Integer, Maestro> hashTiposGenero) {
        this.hashTiposGenero = hashTiposGenero;
    }

    public HashMap<Integer, Maestro> getHashEstadosAfiliacion() {
        return hashEstadosAfiliacion;
    }

    public void setHashEstadosAfiliacion(HashMap<Integer, Maestro> hashEstadosAfiliacion) {
        this.hashEstadosAfiliacion = hashEstadosAfiliacion;
    }

    public List<MpDetalleDTO> getListaMpDetalleDTO() {
        return listaMpDetalleDTO;
    }

    public void setListaMpDetalleDTO(List<MpDetalleDTO> listaMpDetalleDTO) {
        this.listaMpDetalleDTO = listaMpDetalleDTO;
    }

    public MpDetalleDTO getObjetoDto() {
        return objetoDto;
    }

    public void setObjetoDTO(MpDetalleDTO objetoDto) {
        this.objetoDto = objetoDto;
    }

    public List<MpPrescripcionProgramada> getListaMpPrescripcionProgramadas() {
        return listaMpPrescripcionProgramadas;
    }

    public void setListaMpPrescripcionProgramadas(List<MpPrescripcionProgramada> listaMpPrescripcionProgramadas) {
        this.listaMpPrescripcionProgramadas = listaMpPrescripcionProgramadas;
    }

    public MpPrescripcionTecnologia getObjetoTecnologia() {
        return objetoTecnologia;
    }

    public void setObjetoTecnologia(MpPrescripcionTecnologia objetoTecnologia) {
        this.objetoTecnologia = objetoTecnologia;
    }

    public MpPrescripcionInsumo getObjetoInsumo() {
        return objetoInsumo;
    }

    public void setObjetoInsumo(MpPrescripcionInsumo objetoInsumo) {
        this.objetoInsumo = objetoInsumo;
    }

    public MpPrescripcionMedicamento getObjetoMedicamento() {
        return objetoMedicamento;
    }

    public void setObjetoMedicamento(MpPrescripcionMedicamento objetoMedicamento) {
        this.objetoMedicamento = objetoMedicamento;
    }

    public MpProgramadaEntrega getObjetoEntrega() {
        return objetoEntrega;
    }

    public void setObjetoEntrega(MpProgramadaEntrega objetoEntrega) {
        this.objetoEntrega = objetoEntrega;
    }

    public MpPrescripcionProgramada getObjetoProgramada() {
        return objetoProgramada;
    }

    public void setObjetoProgramada(MpPrescripcionProgramada objetoProgramada) {
        this.objetoProgramada = objetoProgramada;
    }

    public void setListaMpPrescripcionEntregadas(List<MpProgramadaEntrega> listaMpPrescripcionEntregadas) {
        this.listaMpPrescripcionEntregadas = listaMpPrescripcionEntregadas;
    }

    public List<MpEntregaSuministro> getListaEntregaSuministros() {
        return listaEntregaSuministros;
    }

    public void setListaEntregaSuministros(List<MpEntregaSuministro> listaEntregaSuministros) {
        this.listaEntregaSuministros = listaEntregaSuministros;
    }

    public MpPrescripcionRecobrante getObjetoRecobrante() {
        return objetoRecobrante;
    }

    public void setObjetoRecobrante(MpPrescripcionRecobrante objetoRecobrante) {
        this.objetoRecobrante = objetoRecobrante;
    }

    public HashMap<Integer, Ubicacion> getHashUbicaciones() {
        return hashUbicaciones;
    }

    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashUbicaciones) {
        this.hashUbicaciones = hashUbicaciones;
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
    
    public void mostrarAfiliadoGeneral() {
        PrimeFaces.current().resetInputs("frmAfiliadoGeneral");
        PrimeFaces.current().ajax().update("frmAfiliadoGeneral");
        PrimeFaces.current().executeScript("PF('dlgInfoAfiliado').show();");
    }
    
    public String obtenerMunicipio(int id) {
        try {
            return hashUbicaciones.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }
}
