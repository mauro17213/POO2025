/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.atencionusuario.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.atencionusuario.AusGrafica;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import static com.saviasaludeps.savia.web.atencionusuario.bean.AusCasoBean.ACCION_BUSCAR_SEDES;
import com.saviasaludeps.savia.web.atencionusuario.servicio.AusGraficasServicioIface;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
//import org.primefaces.model.chart.Axis;
//import org.primefaces.model.chart.AxisType;

//import org.primefaces.model.chart.CategoryAxis;


import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.optionconfig.tooltip.Tooltip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;
import org.springframework.web.servlet.support.RequestContext;

/**
 *
 * @author pavacca
 */
@ManagedBean
@ViewScoped
public class AusGraficasBean extends Url{
    //TEXTOS
    private static final String TEXTO_VACIO = "";
    private static final String HORA_INICIO = " 00:00:00";
    private static final String HORA_FINAL = " 23:59:59";
    private static final String TEXTO_ERROR_VACIO_TIPO_REPORTE = "El tipo de reporte no se ha seleccionado";
    private static final String TEXTO_ERROR_VACIO_TIPO_OPERACION = "El tipo de operacion no se ha seleccionado";
    private static final String TEXTO_ERROR_VACIO_FECHA_DESDE = "Se debe seleccionar una fecha desde";
    private static final String TEXTO_ERROR_VACIO_FECHA_HASTA = "Se debe seleccion una fecha hasta";
    private static final String TEXTO_ERROR_VACIO_PULSADORES = "Se debe seleccionar al menos un pulsador";
    private static final String TEXTO_ERROR_VACIO_RESPONSABLES = "Se debe seleccionar al menos un responsable";
    private static final String TEXTO_ERROR_VACIO_IPS_PRESCRIPTORA = "Se debe seleccionar al menos una ips prescriptora";
    private static final String TEXTO_ERROR_VACIO_IPS_DESTINO = "Se debe seleccionar al menos una ips de destino";
    private static final String SET_LEGEND_POSITION_NE = "ne";
    private static final String SET_LEGEND_POSITION_E = "e";
    
    private AusGraficasServicioIface ausGraficasServicio;
    private AusGrafica objeto;
 
    private CntPrestador objetoPrestador;
    private CntPrestadorSede objetoPrestadorSede;
    private BarChartModel barModel;
    private BarChartModel barModelUnida;
    private LineChartModel lineModel;
    
  
    private LazyDataModel<CntPrestadorSede> lazySedes;
    public static final char ACCION_BUSCAR_SEDES = 'a'; 
    private List<CntPrestadorSede> ListaPrestadorSedes;
    private List<Maestro> listaTiposDocumentoEmpresa;
    private List<Ubicacion> listaUbicacion;
    private List<Ubicacion> ubicaciones;
    private HashMap<Integer, Ubicacion> hashUbicacion;
    private List<CntPrestadorSede> prestadorSedes;
    public AusGraficasBean(){
        this.objeto = new AusGrafica();
        this.prestadorSedes = new ArrayList<>();
        this.barModel = new BarChartModel();
        this.lineModel = new LineChartModel();
        this.barModelUnida = new BarChartModel();
        
        Modulo _mod = super.validarModulo(Modulo.ID_AUS_GRAFICAS);
        //super.addListaParamConsultas(new ParamConsulta());
        //super.addListaParamConsultas(new ParamConsulta()); 
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        super.getParamConsulta().setFiltros(new HashMap<>());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        }else{
            super.setModulo(_mod);
             lazySedes = new LazyDataModel<CntPrestadorSede>() {

                private List<CntPrestadorSede> listaSedes;
                
                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }
                
                @Override
                public List<CntPrestadorSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarSedes();
                    listaSedes = getListaPrestadorSedes();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaSedes;
                }

                @Override
                public String getRowKey(CntPrestadorSede ips) {
                    return ips.getId().toString();
                }

                @Override
                public CntPrestadorSede getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CntPrestadorSede objeto : listaSedes) {
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
        getAusGraficasServicio().cargaInicial(this);
        listar();
    }
    
    public AusGraficasServicioIface getAusGraficasServicio() {
        return ausGraficasServicio;
    }

    public void setAusGraficasServicio(AusGraficasServicioIface ausGraficasServicio) {
        this.ausGraficasServicio = ausGraficasServicio;
    }

    public AusGrafica getObjeto() {
        return objeto;
    }

    public void setObjeto(AusGrafica objeto) {
        this.objeto = objeto;
    }
    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public BarChartModel getBarModelUnida() {
        return barModelUnida;
    }

    public void setBarModelUnida(BarChartModel barModelUnida) {
        this.barModelUnida = barModelUnida;
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    } 

    public LazyDataModel<CntPrestadorSede> getLazySedes() {
        return lazySedes;
    }

    public void setLazySedes(LazyDataModel<CntPrestadorSede> lazySedes) {
        this.lazySedes = lazySedes;
    }

    public List<CntPrestadorSede> getListaPrestadorSedes() {
        return ListaPrestadorSedes;
    }

    public void setListaPrestadorSedes(List<CntPrestadorSede> ListaPrestadorSedes) {
        this.ListaPrestadorSedes = ListaPrestadorSedes;
    }

    public List<Maestro> getListaTiposDocumentoEmpresa() {
        return listaTiposDocumentoEmpresa;
    }

    public void setListaTiposDocumentoEmpresa(List<Maestro> listaTiposDocumentoEmpresa) {
        this.listaTiposDocumentoEmpresa = listaTiposDocumentoEmpresa;
    }

    public List<Ubicacion> getListaUbicacion() throws Exception {
        return UbicacionSingle.getInstance().getListaMunicipios();
    }

    public void setListaUbicacion(List<Ubicacion> listaUbicacion) {
        this.listaUbicacion = listaUbicacion;
    }

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }
    
    public HashMap<Integer, Ubicacion> getHashUbicacion() {
        return hashUbicacion;
    }

    public void setHashUbicacion(HashMap<Integer, Ubicacion> hashUbicacion) {
        this.hashUbicacion = hashUbicacion;
    }

    public CntPrestador getObjetoPrestador() {
        return objetoPrestador;
    }

    public void setObjetoPrestador(CntPrestador objetoPrestador) {
        this.objetoPrestador = objetoPrestador;
    }

    public CntPrestadorSede getObjetoPrestadorSede() {
        return objetoPrestadorSede;
    }

    public void setObjetoPrestadorSede(CntPrestadorSede objetoPrestadorSede) {
        this.objetoPrestadorSede = objetoPrestadorSede;
    }

    public List<CntPrestadorSede> getPrestadorSedes() {
        return prestadorSedes;
    }

    public void setPrestadorSedes(List<CntPrestadorSede> prestadorSedes) {
        this.prestadorSedes = prestadorSedes;
    }
    
    public void guardarFechaDesde(SelectEvent event) throws ParseException{
        String fecha = getObjeto().getFormato().format(event.getObject());
        fecha = fecha + HORA_INICIO;
        Date fecha2 = getObjeto().getFormato2().parse(fecha);
        getObjeto().setFechaDesde(fecha2);
    }
    
    public void guardarFechaHasta(SelectEvent event) throws ParseException{
        String fecha = getObjeto().getFormato().format(event.getObject());
        fecha = fecha + HORA_FINAL;
        Date fecha2 = getObjeto().getFormato2().parse(fecha);
        getObjeto().setFechaHasta(fecha2);
    }
    
    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }
    
    public void generar() throws Exception{
        if(validarDatos()){
            super.setAccion(ACCION_LISTAR);
            super.setDoAccion(ACCION_LISTAR);
            getAusGraficasServicio().Accion(this);
            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoConsultar').hide()");
                PrimeFaces.current().ajax().update("frmGraficas");
            }
            //RequestContext.getCurrentInstance().execute("PF('dialogoConsultar').hide()");
            //RequestContext.getCurrentInstance().update("frmGraficas");
            procesoFinal();
            this.generarMensajes();
        }else{
            this.generarMensajes();
        }
    }   
    public boolean validarDatos(){
        boolean validar = true;
        if(getObjeto().getTipoReporte() == null || getObjeto().getTipoReporte().equals(TEXTO_VACIO)){
            this.addError(TEXTO_ERROR_VACIO_TIPO_REPORTE);
            validar = false;
        }
        if(getObjeto().getFechaDesde() == null){
            this.addError(TEXTO_ERROR_VACIO_FECHA_DESDE);
            validar = false;
        }
        if(getObjeto().getFechaHasta() == null){
            this.addError(TEXTO_ERROR_VACIO_FECHA_HASTA);
            validar = false;
        }
        if(getObjeto().getTipoOperacion() == null || getObjeto().getTipoOperacion().equals(TEXTO_VACIO)){
            this.addError(TEXTO_ERROR_VACIO_TIPO_OPERACION);
            validar = false;
        }
        
        return validar;
    }
    public void consultar(){
        inicializarVariables();
        PrimeFaces.current().executeScript("PF('dialogoConsultar').show()");
        //RequestContext.ge getCurrentInstance().execute("PF('dialogoConsultar').show()");
    }
    public void createGraficaModel1(){
        boolean isLegend = this.getObjeto().validarLegend();
        
        //Grafico de barras normal
        BarChartOptions options = new BarChartOptions();
        options.setMaintainAspectRatio(false);
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        linearAxes.setBeginAtZero(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);
        
        Title title = new Title();
        title.setDisplay(true);
        title.setText(getObjeto().getTituloGraficaModel1());
        options.setTitle(title);
        //barModel.setTitle(getObjeto().getTituloGraficaModel1());
        Legend legend = new Legend();
        if(isLegend){  
            legend.setDisplay(true);
            legend.setPosition("top");
            LegendLabel legendLabels = new LegendLabel();
            legendLabels.setFontStyle("italic");
            legendLabels.setFontColor("#2980B9");
            legendLabels.setFontSize(10);
            legend.setLabels(legendLabels);
            options.setLegend(legend);
            //barModel.getData().SET setLegendPosition(SET_LEGEND_POSITION_NE);
        }else{
            //barModel.setLegendPlacement(LegendPlacement.OUTSIDE);
        }
        Animation animation = new Animation();  
        animation.setDuration(0);
        options.setAnimation(animation);
        barModel.setOptions(options);
        //barModel.setAnimate(true);
 
        /*Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel(getObjeto().getTituloBarraX());
 
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel(getObjeto().getTituloBarraY());
        yAxis.setMin(0);
        yAxis.setMax(50);*/
        
        //Grafico de barras unido
        //Options
        BarChartOptions optionsBarrasUnido = new BarChartOptions();
        optionsBarrasUnido.setMaintainAspectRatio(false);
        CartesianScales cScalesBarrasUnido = new CartesianScales();
        CartesianLinearAxes linearAxesBarrasUnido = new CartesianLinearAxes();
        linearAxesBarrasUnido.setStacked(true);
        linearAxesBarrasUnido.setOffset(true);
        cScalesBarrasUnido.addXAxesData(linearAxesBarrasUnido);
        cScalesBarrasUnido.addYAxesData(linearAxesBarrasUnido);
        optionsBarrasUnido.setScales(cScalesBarrasUnido);
          
        //barModelUnida.setTitle(getObjeto().getTituloGraficaModel1());
        optionsBarrasUnido.setTitle(title);
        Tooltip tooltip = new Tooltip();
        tooltip.setMode("index");
        tooltip.setIntersect(false);
        optionsBarrasUnido.setTooltip(tooltip);
        barModelUnida.setOptions(optionsBarrasUnido);
        if(isLegend){
            //barModelUnida.setLegendPosition(SET_LEGEND_POSITION_NE);
        }else{
            //barModelUnida.setLegendPlacement(LegendPlacement.OUTSIDE);
        }         
        //barModelUnida.setStacked(true);
        //barModelUnida.setAnimate(true);
 
       /* xAxis = barModelUnida.getAxis(AxisType.X);
        xAxis.setLabel(getObjeto().getTituloBarraX());
 
        yAxis = barModelUnida.getAxis(AxisType.Y);
        yAxis.setLabel(getObjeto().getTituloBarraY());
        yAxis.setMin(0);
        yAxis.setMax(50);*/
        
        //Grafico de linea
        LineChartOptions optionsSerieLinea = new LineChartOptions();
        optionsSerieLinea.setMaintainAspectRatio(false);
        
        optionsSerieLinea.setTitle(title);
        
        Title subtitle = new Title();
        subtitle.setDisplay(true);
        subtitle.setText("Line Chart Subtitle");
        optionsSerieLinea.setSubtitle(subtitle);
        lineModel.setOptions(optionsSerieLinea);
        if(isLegend){
            //lineModel.setLegendPosition(SET_LEGEND_POSITION_E);
        }else{
            //lineModel.setLegendPlacement(LegendPlacement.OUTSIDE);
        }          
        //lineModel.setShowPointLabels(true);
        //lineModel.setAnimate(true);
        //lineModel.getAxes().put(AxisType.X, new CategoryAxis(getObjeto().getTituloLineaX()));
        /*yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel(getObjeto().getTituloLineaY());
        yAxis.setMin(0);
        yAxis.setMax(50);*/
        
    }
    private void inicializarVariables() {
        barModel = new BarChartModel();
        lineModel = new LineChartModel();
        barModelUnida = new BarChartModel();
        this.getObjeto().inicializarVariables();
        prestadorSedes = new ArrayList<>();
    }
    public void onRowSelectIps(SelectEvent event) {
        try {
            setObjetoPrestadorSede((CntPrestadorSede) event.getObject());
            prestadorSedes.add(getObjetoPrestadorSede());
            getObjeto().setListaSedesIps(prestadorSedes);
            //getObjeto().setListaFiltrosIps(ip1); 
            setObjetoPrestador(getObjetoPrestadorSede().getCntPrestador());
            PrimeFaces.current().ajax().update("frmConsultar:listaIps");
            PrimeFaces.current().executeScript("PF('dialogoIpsLista').hide()");
           
        } catch (Exception ex) {
            addError("Ocurrio un error al seleccionar la IPS: " + ex.toString());
        }
    }
    public String obtenerDepartamento(int id) {
        try {
            int idPadre = hashUbicacion.get(id).getUbicacionPadre().getId();
            return hashUbicacion.get(idPadre).getNombre();
        } catch (Exception e) {
            return "";
        }
    }
    public String obtenerMunicipio(int id) {
        try {
            return hashUbicacion.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }
    public void refrescarSedes(){
        try {
            super.setAccion(Url.ACCION_LISTAR);
            super.setDoAccion(ACCION_BUSCAR_SEDES);
            getAusGraficasServicio().Accion(this);
        } catch (Exception ex) {
            addError(ex.getMessage());
        } 
    }
    public void consultarSedes() {
        try {
            DataTable dataTable2 = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmPrestadorLista:tablaRegistrosIps");
            //dataTable2.clearLazyCache();
            dataTable2.setFilterBy(null);
            dataTable2.reset();
            PrimeFaces.current().resetInputs("frmPrestadorLista:tablaRegistrosIps");
            PrimeFaces.current().ajax().update("frmPrestadorLista:hPanelIps");
            this.setParamConsulta(new ParamConsulta());
            this.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            PrimeFaces.current().ajax().update("frmPrestadorLista");
            PrimeFaces.current().executeScript("PF('dialogoIpsLista').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }
    public void borrarIPS(int pos) {
        //Retirar registro de la list
        Iterator<CntPrestadorSede> iterator = prestadorSedes.iterator();
        while (iterator.hasNext()) {
            CntPrestadorSede item = iterator.next();
            if (pos == item.getId()) {
                //iterator.remove();
                iterator.remove();
            } 
        }
        PrimeFaces.current().ajax().update("frmConsultar:listaIps");
    }
    private void procesoFinal() {
        if (!super.isError()) {
            //crearLog(descripcion);
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_MODIFICAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_BORRAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_LISTAR:
                    switch (super.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            crearLog(getObjeto().toString());
                            break;
                        case Url.ACCION_MODIFICAR:
                            break;
                        case Url.ACCION_EDITAR:
                            break;
                        case AusCasoBean.ACCION_BUSCAR_SEDES:
                             crearLog(getObjeto().toString());
                            break;
                        default:
                            break;
                    }
                   
                    break;
                case Url.ACCION_CREAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
            }
        }
        generarMensajes();
    }
}
