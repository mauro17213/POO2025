/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.bean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoHistorico;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import com.saviasaludeps.savia.web.contratacion.servicio.ConsultarTecnologiasIface;
import com.saviasaludeps.savia.web.contratacion.servicio.ConsultarTecnologiasImpl;
import com.saviasaludeps.savia.web.contratacion.utilidades.ContratacionParametro;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.primefaces.model.SortMeta;

/**
 *
 * @author Jose Perez
 */
@ManagedBean
@ViewScoped
public class ConsultarTecnologiasBean extends Url {

    private ConsultarTecnologiasIface consultarTecnologiasServicio;
    private CntContratoDetalle objeto;
    private List<CntContratoDetalle> registros;
    private LazyDataModel<CntContratoDetalle> lazyContratoDetalle;
    private boolean ejecutoBusqueda;
    private CntContratoHistorico objetoHistorico;
    private LazyDataModel<CntContratoHistorico> lazyHistorico;
    private List<CntContratoHistorico> registrosHistorico;
    private ParamConsulta paramConsultaHistorico;
    private Date fechaInicio;
    private Date fechaFin;

    public ConsultarTecnologiasBean() {
        this.objeto = new CntContratoDetalle();
        ejecutoBusqueda = false;
        //cambiar el módulo
        Modulo mod = super.validarModulo(Modulo.ID_CONTRATACION_CONSULTA_TECNOLOGIAS);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyContratoDetalle = new LazyDataModel<CntContratoDetalle>() {
                private List<CntContratoDetalle> listaCargas;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CntContratoDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaCargas = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaCargas;
                }

                @Override
                public String getRowKey(CntContratoDetalle portabilidad) {
                    return portabilidad.getId().toString();
                }

                @Override
                public CntContratoDetalle getRowData(String portabilidadId) {
                    Integer id = Integer.valueOf(portabilidadId);
                    for (CntContratoDetalle portabilidad : listaCargas) {
                        if (id.equals(portabilidad.getId())) {
                            return portabilidad;
                        }
                    }
                    return null;
                }

            };
        }
    }

    @PostConstruct
    public void postConstruct() {
        getConsultarTecnologiasServicio().cargaInicial(this);
//        listar();
    }

    public ConsultarTecnologiasIface getConsultarTecnologiasServicio() {
        if (consultarTecnologiasServicio == null) {
            consultarTecnologiasServicio = new ConsultarTecnologiasImpl();
        }
        return consultarTecnologiasServicio;
    }

    public void setConsultarTecnologiasServicio(ConsultarTecnologiasIface consultarTecnologiasServicio) {
        this.consultarTecnologiasServicio = consultarTecnologiasServicio;
    }

    public CntContratoDetalle getObjeto() {
        return objeto;
    }

    public void setObjeto(CntContratoDetalle objeto) {
        this.objeto = objeto;
    }

    public List<CntContratoDetalle> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CntContratoDetalle> registros) {
        this.registros = registros;
    }

    public LazyDataModel<CntContratoDetalle> getLazyContratoDetalle() {
        return lazyContratoDetalle;
    }

    public void setLazyContratoDetalle(LazyDataModel<CntContratoDetalle> lazyContratoDetalle) {
        this.lazyContratoDetalle = lazyContratoDetalle;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getConsultarTecnologiasServicio().Accion(this);
//        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getConsultarTecnologiasServicio().Accion(this);
        //PrimeFaces.current().resetInputs("frmCrear");
        //PrimeFaces.current().ajax().update("frmCrear");
        //PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getConsultarTecnologiasServicio().Accion(this);
        //PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
        procesoFinal();
    }

    public void ver(int id) {
        this.objeto = new CntContratoDetalle(id);
        super.setAccion(ACCION_VER);
        getConsultarTecnologiasServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmVer");
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void verHistorico(CntContratoDetalle obj) {
        this.objeto = obj;
        paramConsultaHistorico = new ParamConsulta();
        paramConsultaHistorico.setParametroConsulta1(this.objeto.getCntContrato().getId());
        paramConsultaHistorico.setParametroConsulta2(this.objeto.getCntContratoSede().getId());
        paramConsultaHistorico.setParametroConsulta3(this.objeto.getId());
        super.setAccion(ACCION_ADICIONAL_1);
        getConsultarTecnologiasServicio().Accion(this);
        // debemos cargar la lista de novedades CAMBIAR
        inicializarTablaHistorico();
        PrimeFaces.current().resetInputs("frmHistoricos");
        PrimeFaces.current().ajax().update("frmHistoricos");
        PrimeFaces.current().executeScript("PF('dialogoHistoricos').show()");
        procesoFinal();
    }

    public void inicializarTablaHistorico() {
        lazyHistorico = new LazyDataModel<CntContratoHistorico>() {
            private List<CntContratoHistorico> listaCargasHist;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<CntContratoHistorico> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaHistorico().setPrimerRegistro(primerRegistro);
                getParamConsultaHistorico().setRegistrosPagina(registrosPagina);
                getParamConsultaHistorico().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaHistorico().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarHistorico();
                listaCargasHist = getRegistrosHistorico();
                setRowCount(getParamConsultaHistorico().getCantidadRegistros());
                return listaCargasHist;
            }

            @Override
            public String getRowKey(CntContratoHistorico historico) {
                return historico.getId().toString();
            }

            @Override
            public CntContratoHistorico getRowData(String historicoId) {
                Integer id = Integer.valueOf(historicoId);
                for (CntContratoHistorico historico : listaCargasHist) {
                    if (id.equals(historico.getId())) {
                        return historico;
                    }
                }
                return null;
            }

        };
    }

    public void refrescarHistorico() {
        super.setAccion(Url.ACCION_ADICIONAL_1);
        getConsultarTecnologiasServicio().Accion(this);
        procesoFinal();
    }

    public void verDetalleHistorico(CntContratoHistorico obj) {
        // hacemos transformación del objeto en el de la pantalla
        GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
        gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd HH:mm:ss"); //Formato fecha 
        gsonBuilderRespuesta.serializeNulls();
        Gson gson = gsonBuilderRespuesta.create();
        try {
            this.objeto = gson.fromJson(obj.getToString(), CntContratoDetalle.class);
            PrimeFaces.current().resetInputs("frmVerHistorico");
            PrimeFaces.current().ajax().update("frmVerHistorico");
            PrimeFaces.current().executeScript("PF('dialogoVerHistorico').show()");
        } catch (JsonSyntaxException e) {
            addError("Ocurrió un error. Mensaje: " + e.getMessage());
        }
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
                PrimeFaces.current().ajax().update("frmConsultarTecnologia");
                break;
            case Url.ACCION_LISTAR:
                PrimeFaces.current().ajax().update("frmVerTecnologiasEncontradas");
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_1:
                crearLog("Ver Histórico", getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_2:
                //crearLog("Cancelar",getObjeto().toString());
                //PrimeFaces.current().ajax().update("frmConsultarTecnologia");
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

    public String getTipoTecnologia(Integer id) {
        String mensaje = "";
        if (id != null) {
            switch (id) {
                case ContratacionParametro.TIPO_TECNOLOGIA_TECNOLOGIA:
                    mensaje = "Tecnologia (CUP)";
                    break;
                case ContratacionParametro.TIPO_TECNOLOGIA_MEDICAMENTO:
                    mensaje = "Medicamento (CUM)";
                    break;
                case ContratacionParametro.TIPO_TECNOLOGIA_INSUMO:
                    mensaje = "Insumo";
                    break;
                case ContratacionParametro.TIPO_TECNOLOGIA_PAQUETE:
                    mensaje = "Paquete";
                    break;
            }
        }
        return mensaje;
    }

    public String getManualTarifario(Integer id) {
        String mensaje = "";
        if (id != null) {
            switch (id) {
                case 0:
                    mensaje = "PROPIA";
                    break;
                case 1:
                    mensaje = "SOAT";
                    break;
                case 2:
                    mensaje = "ISS2000";
                    break;
                case 3:
                    mensaje = "ISS2001";
                    break;
            }
        }
        return mensaje;
    }

    public void buscarTecnologias() {
        boolean validarBusqueda = true;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //validaciones
        if (getParamConsulta().getParametroConsulta1() == null
                && "".equals(getParamConsulta().getParametroConsulta2())
                && "".equals(getParamConsulta().getParametroConsulta3())
                && "".equals(getParamConsulta().getParametroConsulta4())
                && (this.fechaInicio == null)
                && (this.fechaFin == null)) {
            this.addError("Para realizar la búsqueda es necesario ingresar alguno de los campos.");
            validarBusqueda = false;
        }
        if (getFechaInicio() != null) {
            getParamConsulta().setParametroConsulta5(sdf.format(getFechaInicio()));
        }
        if (getFechaFin() != null) {
            getParamConsulta().setParametroConsulta6(sdf.format(getFechaFin()));
        }
        if (getFechaInicio() != null && getFechaFin() != null) {
            if (getFechaFin().before(getFechaInicio())) {
                this.addError("El campo Fecha Inicial debe ser Menor a la Fecha Final.");
                validarBusqueda = false;
            }
        }
        if (validarBusqueda) {
            //para controlar el inicio de la tabla, ya en edelante
            if (!isEjecutoBusqueda()) {
                ejecutoBusqueda = true;
            }
            super.setAccion(Url.ACCION_LISTAR);
//(2022-04-29 (rpalacic) Se comenta para evitar la carga doble para la consulta de tecnologías
            listar();
//            getConsultarTecnologiasServicio().Accion(this);
            //validamos si hay datos para mostrar
//            if (!super.isError()) {
//                int cantidadRegistros = this.getRegistros() != null ? this.getRegistros().size() : 0;
//                if (cantidadRegistros <= 0) {
//                    this.addMensaje("No se encontraron tecnologías con los criterios ingresados.");
//                }
//            }
//            procesoFinal();
        } else {
            generarMensajes();
        }

    }

    public void limpiarFormulario() {
        //limpiamos los parámetros de consulta
        getParamConsulta().setParametroConsulta1(null);
        getParamConsulta().setParametroConsulta2(null);
        getParamConsulta().setParametroConsulta3(null);
        //inicializamos la tabla.
        ejecutoBusqueda = false;
        getParamConsulta().setCantidadRegistros(0);
        setRegistros(new ArrayList<CntContratoDetalle>());
        PrimeFaces.current().ajax().update("frmConsultarTecnologia");
        PrimeFaces.current().ajax().update("frmVerTecnologiasEncontradas");
    }

    /**
     * @return the ejecutoBusqueda
     */
    public boolean isEjecutoBusqueda() {
        return ejecutoBusqueda;
    }

    /**
     * @param ejecutoBusqueda the ejecutoBusqueda to set
     */
    public void setEjecutoBusqueda(boolean ejecutoBusqueda) {
        this.ejecutoBusqueda = ejecutoBusqueda;
    }

    /**
     * @return the objetoHistorico
     */
    public CntContratoHistorico getObjetoHistorico() {
        return objetoHistorico;
    }

    /**
     * @param objetoHistorico the objetoHistorico to set
     */
    public void setObjetoHistorico(CntContratoHistorico objetoHistorico) {
        this.objetoHistorico = objetoHistorico;
    }

    /**
     * @return the lazyHistorico
     */
    public LazyDataModel<CntContratoHistorico> getLazyHistorico() {
        return lazyHistorico;
    }

    /**
     * @param lazyHistorico the lazyHistorico to set
     */
    public void setLazyHistorico(LazyDataModel<CntContratoHistorico> lazyHistorico) {
        this.lazyHistorico = lazyHistorico;
    }

    /**
     * @return the registrosHistorico
     */
    public List<CntContratoHistorico> getRegistrosHistorico() {
        return registrosHistorico;
    }

    /**
     * @param registrosHistorico the registrosHistorico to set
     */
    public void setRegistrosHistorico(List<CntContratoHistorico> registrosHistorico) {
        this.registrosHistorico = registrosHistorico;
    }

    /**
     * @return the paramConsultaHistorico
     */
    public ParamConsulta getParamConsultaHistorico() {
        return paramConsultaHistorico;
    }

    /**
     * @param paramConsultaHistorico the paramConsultaHistorico to set
     */
    public void setParamConsultaHistorico(ParamConsulta paramConsultaHistorico) {
        this.paramConsultaHistorico = paramConsultaHistorico;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}
