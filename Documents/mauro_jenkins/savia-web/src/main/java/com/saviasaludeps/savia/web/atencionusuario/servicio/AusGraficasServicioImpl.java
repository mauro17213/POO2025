/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.atencionusuario.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.atencionusuario.AusDatoGrafica;
import com.saviasaludeps.savia.dominio.atencionusuario.AusGrafica;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusCasoRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusGraficaRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.web.atencionusuario.bean.AusCasoBean;
import com.saviasaludeps.savia.web.atencionusuario.bean.AusGraficasBean;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.FH;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.line.LineChartDataSet;

/**
 *
 * @author pavacca
 */
public class AusGraficasServicioImpl extends AccionesBO implements AusGraficasServicioIface {

    private static final String MENSAJE_SESION_INACTIVA = "Sesion inactiva";
    private static final String GRAFICO_SERVICIO = "AusGraficaServicio";
    private static final String MAESTRO_SERVICIO = "MaestroServicio";
    private static final String USUARIO_SERVICIO = "UsuarioServicio";
    private static final String TEXTO_CERRADO = "Cerrado";
    private static final String TEXTO_RECHAZADO = "Rechazado";
    private static final String ID_RESPONSABLE = "idResponsable";
    private static final String ID_IPS_DESTINO = "idIps";
    private static final String ID_IPS_PRESCRIPTORA = "idIpsPrescriptora";
    private static final String FECHA = "fechas";
    private static final String TEXTO_VACIO = "";
    private static final String ESPACIO = " ";
    private static final int DIAS = 31;

    private AusGraficaRemoto getGraficaRemoto() throws Exception {
        return (AusGraficaRemoto) RemotoEJB.getEJBRemoto(GRAFICO_SERVICIO, AusGraficaRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto(MAESTRO_SERVICIO, MaestroRemoto.class.getName());
    }

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto(USUARIO_SERVICIO, UsuarioRemoto.class.getName());
    }

    private CntPrestadorRemoto getCntPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private AusCasoRemoto getCasoRemoto() throws Exception {
        return (AusCasoRemoto) RemotoEJB.getEJBRemoto("AusCasoServicio", AusCasoRemoto.class.getName());
    }

    @Override
    public void Accion(AusGraficasBean bean) throws Exception {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listar(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            break;
                        case Url.ACCION_EDITAR:
                            break;
                        case AusCasoBean.ACCION_BUSCAR_SEDES:
                            listarSedesPrescriptora(bean);
                            break;
                        default:
                            break;
                    }
                    //listar(bean);
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_GUARDAR:
                    break;
                case Url.ACCION_EDITAR:
                    break;
                case Url.ACCION_MODIFICAR:
                    break;
                case Url.ACCION_BORRAR:
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                case Url.ACCION_ADICIONAL_3:
                    break;
                default:
                    break;
            }
        } else {
            System.err.println(MENSAJE_SESION_INACTIVA);
        }
    }

    public void listar(AusGraficasBean bean) throws Exception {
        switch (bean.getObjeto().getTipoReporte()) {
            case AusGrafica.REPORTE_CASOS:
                switch (bean.getObjeto().getTipoOperacion()) {
                    case AusGrafica.OPERACION_RESPONSABLE:
                        serieGraficasResponsable(bean);
                        break;
                    default:
                        break;
                }
                break;
            case AusGrafica.REPORTE_SERVICIO:
                switch (bean.getObjeto().getTipoOperacion()) {
                    case AusGrafica.OPERACION_RESPONSABLE:
                        serieGraficasResponsable(bean);
                        break;
                    case AusGrafica.OPERACION_IPS_DESTINO:
                        serieGraficasIpsDestino(bean);
                        break;
                    case AusGrafica.OPERACION_IPS_PRESCRIPTORA:
                        serieGraficasIpsPrescriptora(bean);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

    }

    private void listarSedesPrescriptora(AusGraficasBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getCntPrestadorRemoto().consultarCantidadListaSede(bean.getParamConsulta()));
            bean.setListaPrestadorSedes(getCntPrestadorRemoto().consultarListaSede(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void serieGraficasResponsable(AusGraficasBean bean) throws Exception {
        bean.getObjeto().generarTitulos();
        bean.createGraficaModel1();
        if (bean.getParamConsulta().getFiltros() == null) {
            bean.getParamConsulta().setFiltros(new HashMap());
        }
        ChartData dataSerieBarra = new ChartData();
        ChartData dataSerieBarraHorizontal = new ChartData();
        ChartData dataSerieSerieLinea = new ChartData();
        for (int idResponsable : bean.getObjeto().getListaFiltrosResponsable()) {
            bean.getParamConsulta().getFiltros().put(ID_RESPONSABLE, idResponsable);
            String nombre = bean.getObjeto().obtenerNombreResponsable(idResponsable);
            bean.getObjeto().setDatoActual(new AusDatoGrafica());
            bean.getObjeto().getDatoActual().setNombre(nombre);
            for (int idTipoPulsador : bean.getObjeto().getListaIdTipoConsulta()) {
                calcularSerieBarra(bean, idTipoPulsador, nombre, bean.getObjeto().getTipoReporte(), dataSerieBarra);
                calcularSerieBarraHorizontal(bean, idTipoPulsador, nombre, bean.getObjeto().getTipoReporte(), dataSerieBarraHorizontal);
                calcularSerieLinea(bean, idTipoPulsador, nombre, bean.getObjeto().getTipoReporte(), dataSerieSerieLinea);
            }
            bean.getObjeto().agregarDatoALista();
        }
        //dataSerieBarra.setLabels(labelsSerieBarra);
        bean.getBarModel().setData(dataSerieBarra);
        bean.getBarModelUnida().setData(dataSerieBarraHorizontal);
        bean.getLineModel().setData(dataSerieSerieLinea);

    }

    private void serieGraficasIpsDestino(AusGraficasBean bean) throws Exception {
        bean.getObjeto().generarTitulos();
        bean.createGraficaModel1();
        if (bean.getParamConsulta().getFiltros() == null) {
            bean.getParamConsulta().setFiltros(new HashMap());
        }
        if (bean.getObjeto().getListaSedesIps() != null) {
            ChartData dataSerieBarra = new ChartData();
            ChartData dataSerieBarraHorizontal = new ChartData();
            ChartData dataSerieSerieLinea = new ChartData();
            for (CntPrestadorSede ipsDestino : bean.getObjeto().getListaSedesIps()) {
                bean.getParamConsulta().getFiltros().put(ID_IPS_DESTINO, ipsDestino.getId());
                String nombre = ipsDestino.getNombreSede();
                bean.getObjeto().setDatoActual(new AusDatoGrafica());
                bean.getObjeto().getDatoActual().setNombre(nombre);
                for (int idTipoPulsador : bean.getObjeto().getListaIdTipoConsulta()) {
                    calcularSerieBarra(bean, idTipoPulsador, nombre, bean.getObjeto().getTipoReporte(), dataSerieBarra);
                    calcularSerieBarraHorizontal(bean, idTipoPulsador, nombre, bean.getObjeto().getTipoReporte(), dataSerieBarraHorizontal);
                    calcularSerieLinea(bean, idTipoPulsador, nombre, bean.getObjeto().getTipoReporte(), dataSerieSerieLinea);
                }
                bean.getObjeto().agregarDatoALista();
            }
            bean.getBarModel().setData(dataSerieBarra);
            bean.getBarModelUnida().setData(dataSerieBarraHorizontal);
            bean.getLineModel().setData(dataSerieSerieLinea);
        } else {
            bean.addError("Debes seleccionar al menos una Ips");
        }

    }

    private void serieGraficasIpsPrescriptora(AusGraficasBean bean) throws Exception {
        bean.getObjeto().generarTitulos();
        bean.createGraficaModel1();
        if (bean.getParamConsulta().getFiltros() == null) {
            bean.getParamConsulta().setFiltros(new HashMap());
        }
        if (bean.getObjeto().getListaSedesIps() != null) {
            ChartData dataSerieBarra = new ChartData();
            ChartData dataSerieBarraHorizontal = new ChartData();
            ChartData dataSerieSerieLinea = new ChartData();
            for (CntPrestadorSede ipDestino : bean.getObjeto().getListaSedesIps()) {
                bean.getParamConsulta().getFiltros().put(ID_IPS_PRESCRIPTORA, ipDestino.getId());
                String nombre = ipDestino.getNombreSede();
                bean.getObjeto().setDatoActual(new AusDatoGrafica());
                bean.getObjeto().getDatoActual().setNombre(nombre);
                for (int idTipoPulsador : bean.getObjeto().getListaIdTipoConsulta()) {
                    calcularSerieBarra(bean, idTipoPulsador, nombre, bean.getObjeto().getTipoReporte(), dataSerieBarra);
                    calcularSerieBarraHorizontal(bean, idTipoPulsador, nombre, bean.getObjeto().getTipoReporte(), dataSerieBarraHorizontal);
                    calcularSerieLinea(bean, idTipoPulsador, nombre, bean.getObjeto().getTipoReporte(), dataSerieSerieLinea);
                }
                bean.getObjeto().agregarDatoALista();
            }
            bean.getBarModel().setData(dataSerieBarra);
            bean.getBarModelUnida().setData(dataSerieBarraHorizontal);
            bean.getLineModel().setData(dataSerieSerieLinea);
        } else {
            bean.addError("Debes seleccionar al menos una Ips");
        }

    }

    public void calcularSerieBarra(AusGraficasBean bean, int tipoConsulta, String nombre, String tipoOperacion, ChartData dataSerieBarra) throws Exception {
        String label = TEXTO_VACIO;
        List<String> bgColor = new ArrayList<>();
        List<String> borderColor = new ArrayList<>();
        List<String> labelsSerieBarra = new ArrayList<>();
        switch (tipoConsulta) {
            case AusGrafica.ID_PULSADOR_CERRADO:
                bgColor.add("rgba(255, 159, 64, 0.2)");
                borderColor.add("rgb(255, 159, 64)");
                label = nombre + ESPACIO + bean.getObjeto().getTipoReporte() + ESPACIO + AusGrafica.PULSADOR_CERRADO;
                break;
            case AusGrafica.ID_PULSADOR_VENCIDO:
                bgColor.add("rgba(255, 205, 86, 0.2)");
                borderColor.add("rgb(255, 205, 86)");
                label = nombre + ESPACIO + bean.getObjeto().getTipoReporte() + ESPACIO + AusGrafica.PULSADOR_VENCIDO;
                break;
            case AusGrafica.ID_PULSADOR_PENDIENTE:
                bgColor.add("rgba(255, 99, 132, 0.2)");
                borderColor.add("rgb(255, 99, 132)");
                label = nombre + ESPACIO + bean.getObjeto().getTipoReporte() + ESPACIO + AusGrafica.PULSADOR_PENDIENTE;
                break;
            default:
                break;
        }

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel(label);
        bean.getParamConsulta().getFiltros().put(FECHA, bean.getObjeto().obtenerFecha());
        List<Number> values = new ArrayList<>();
        values.add(0);
        labelsSerieBarra.add(bean.getObjeto().obtenerMesAnterior());
        int cantidad = 0;
        switch (tipoOperacion) {
            case AusGrafica.REPORTE_CASOS:
                cantidad = getGraficaRemoto().contarCasos(bean.getParamConsulta(), tipoConsulta, bean.getObjeto().getEstadoCerrado(), bean.getObjeto().getEstadoRechazado());
                break;
            case AusGrafica.REPORTE_SERVICIO:
                cantidad = getGraficaRemoto().contarServicios(bean.getParamConsulta(), tipoConsulta, bean.getObjeto().getEstadoServicioCerrado(), bean.getObjeto().getEstadoServicioRechazado());
                break;
        }
        bean.getObjeto().agregarCantidad(tipoConsulta, cantidad);
        values.add(cantidad);
        labelsSerieBarra.add(bean.getObjeto().obtenerRangoMeses());
        values.add(0);
        labelsSerieBarra.add(bean.getObjeto().obtenerMesSiguiente());
        //barDataSet.setData(values);
        barDataSet.setBackgroundColor(bgColor);
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);
        dataSerieBarra.addChartDataSet(barDataSet);
        dataSerieBarra.setLabels(labelsSerieBarra);
       
    }

    public void calcularSerieBarraHorizontal(AusGraficasBean bean, int tipoConsulta, String nombre, String tipoOperacion, ChartData dataSerieBarraHorizontal) throws Exception {
        BarChartDataSet barDataSet = new BarChartDataSet();
        String label = TEXTO_VACIO;
        List<String> labelsSerieBarraHorizontal = new ArrayList<>();
        
        switch (tipoConsulta) {
            case AusGrafica.ID_PULSADOR_CERRADO:
                barDataSet.setBackgroundColor("rgb(255, 159, 64)");
                label = nombre + ESPACIO + bean.getObjeto().getTipoReporte() + ESPACIO + AusGrafica.PULSADOR_CERRADO;
                break;
            case AusGrafica.ID_PULSADOR_VENCIDO:
                barDataSet.setBackgroundColor("rgb(255, 205, 86)");
                label = nombre + ESPACIO + bean.getObjeto().getTipoReporte() + ESPACIO + AusGrafica.PULSADOR_VENCIDO;
                break;
            case AusGrafica.ID_PULSADOR_PENDIENTE:
                barDataSet.setBackgroundColor("rgb(255, 99, 132)");
                label = nombre + ESPACIO + bean.getObjeto().getTipoReporte() + ESPACIO + AusGrafica.PULSADOR_PENDIENTE;
                break;
            default:
                break;
        }
        bean.getParamConsulta().getFiltros().put(FECHA, bean.getObjeto().obtenerFecha());

        barDataSet.setLabel(label);
        List<Number> dataVal = new ArrayList<>();
        int cantidad = 0;
        switch (tipoOperacion) {
            case AusGrafica.REPORTE_CASOS:
                cantidad = getGraficaRemoto().contarCasos(bean.getParamConsulta(), tipoConsulta, bean.getObjeto().getEstadoCerrado(), bean.getObjeto().getEstadoRechazado());
                break;
            case AusGrafica.REPORTE_SERVICIO:
                cantidad = getGraficaRemoto().contarServicios(bean.getParamConsulta(), tipoConsulta, bean.getObjeto().getEstadoServicioCerrado(), bean.getObjeto().getEstadoServicioRechazado());
                break;
        }
        dataVal.add(cantidad);
        labelsSerieBarraHorizontal.add(bean.getObjeto().obtenerRangoMeses());
//        barDataSet.setData(dataVal);
        dataSerieBarraHorizontal.setLabels(labelsSerieBarraHorizontal);
        dataSerieBarraHorizontal.addChartDataSet(barDataSet);
        //return barDataSet;
    }

    private void calcularSerieLinea(AusGraficasBean bean, int tipoConsulta, String nombre, String tipoOperacion, ChartData dataSerieSerieLinea) throws Exception {
        String label = TEXTO_VACIO;
        switch (tipoConsulta) {
            case AusGrafica.ID_PULSADOR_CERRADO:
                label = nombre + ESPACIO + bean.getObjeto().getTipoReporte() + ESPACIO + AusGrafica.PULSADOR_CERRADO;
                break;
            case AusGrafica.ID_PULSADOR_VENCIDO:
                label = nombre + ESPACIO + bean.getObjeto().getTipoReporte() + ESPACIO + AusGrafica.PULSADOR_VENCIDO;
                break;
            case AusGrafica.ID_PULSADOR_PENDIENTE:
                label = nombre + ESPACIO + bean.getObjeto().getTipoReporte() + ESPACIO + AusGrafica.PULSADOR_PENDIENTE;
                break;
            default:
                break;
        }
        //ChartSeries serie = new ChartSeries();
        int cantidadDias = FH.contarDias(bean.getObjeto().getFechaDesde(), bean.getObjeto().getFechaHasta());
        if (cantidadDias <= DIAS) {
            calcularSeriePorDias(bean, label, cantidadDias, tipoOperacion, tipoConsulta, dataSerieSerieLinea);
        } else {
            calcularSeriePorMeses(bean,label, tipoOperacion, tipoConsulta, dataSerieSerieLinea);
        }
        //bean.getLineModel().addSeries(serie);    
        //return lineChartDataSet;
    }

    private void calcularSeriePorDias(AusGraficasBean bean, String label, int dias, String tipoOperacion, int tipoConsulta, ChartData dataSerieSerieLinea) throws Exception {
        LineChartDataSet serie = new LineChartDataSet();
        serie.setLabel(label);
        List<Object> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for (int i = 0; i < dias; i++) {
            Date fecha = FH.agregarDias(bean.getObjeto().getFechaDesde(), i);
            String rango = FH.crearRangoFechaCompleto(fecha);
            bean.getParamConsulta().getFiltros().put(FECHA, rango);
            String nombre = FH.obtenerDiaMesFecha(fecha);
            labels.add(nombre);
            int cantidad = 0;
            switch (tipoOperacion) {
                case AusGrafica.REPORTE_CASOS:
                    cantidad = getGraficaRemoto().contarCasos(bean.getParamConsulta(), tipoConsulta, bean.getObjeto().getEstadoCerrado(), bean.getObjeto().getEstadoRechazado());
                    break;
                case AusGrafica.REPORTE_SERVICIO:
                    cantidad = getGraficaRemoto().contarServicios(bean.getParamConsulta(), tipoConsulta, bean.getObjeto().getEstadoServicioCerrado(), bean.getObjeto().getEstadoServicioRechazado());
                    break;
            }
            values.add(cantidad);
            switch (tipoConsulta) {
                case AusGrafica.ID_PULSADOR_CERRADO:
                    serie.setBorderColor("rgb(255, 159, 64)");
                    serie.setBackgroundColor("rgb(255, 159, 64)");
                    break;
                case AusGrafica.ID_PULSADOR_VENCIDO:
                    serie.setBorderColor("rgb(255, 205, 86)");
                    serie.setBackgroundColor("rgb(255, 205, 86)");
                    break;
                case AusGrafica.ID_PULSADOR_PENDIENTE:
                     serie.setBorderColor("rgb(255, 99, 132)");
                    serie.setBackgroundColor("rgb(255, 99, 132)");
                    break;
                default:
                    break;
            }
            serie.setTension(0.5);
            //serie.set(nombre, cantidad);
        }
        serie.setData(values);
        serie.setFill(false);

        dataSerieSerieLinea.addChartDataSet(serie);
        dataSerieSerieLinea.setLabels(labels);
        //return serie;
    }

    private void calcularSeriePorMeses(AusGraficasBean bean, String label, String tipoOperacion, int tipoConsulta , ChartData dataSerieSerieLinea) throws Exception {
        LineChartDataSet serie = new LineChartDataSet();
        serie.setLabel(label);
        List<Object> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        Date fechaInicio = bean.getObjeto().getFechaDesde();
        while(fechaInicio.before(bean.getObjeto().getFechaHasta())){
            Date fechaFinRango = FH.calcularFechaFinMes(fechaInicio);
            if(fechaFinRango.after(bean.getObjeto().getFechaHasta())){
                fechaFinRango = bean.getObjeto().getFechaHasta();
            }
            String rango = FH.rangoEntreDosFechasCompleto(fechaInicio, fechaFinRango);
            bean.getParamConsulta().getFiltros().put(FECHA, rango);
            String nombre = FH.obtenerMesFecha(fechaInicio);
            labels.add(nombre);
            int cantidad = 0;
            switch(tipoOperacion){
                case AusGrafica.REPORTE_CASOS:
                    cantidad = getGraficaRemoto().contarCasos(bean.getParamConsulta(), tipoConsulta, bean.getObjeto().getEstadoCerrado(), bean.getObjeto().getEstadoRechazado());
                    break;
                case AusGrafica.REPORTE_SERVICIO:
                    cantidad = getGraficaRemoto().contarServicios(bean.getParamConsulta(), tipoConsulta, bean.getObjeto().getEstadoServicioCerrado(), bean.getObjeto().getEstadoServicioRechazado());
                    break;                
            }
            values.add(cantidad);
            //serie.set(nombre,cantidad);
            fechaInicio = FH.agregarDias(fechaFinRango, 1);
            switch (tipoConsulta) {
                case AusGrafica.ID_PULSADOR_CERRADO:
                    serie.setBorderColor("rgb(255, 159, 64)");
                    break;
                case AusGrafica.ID_PULSADOR_VENCIDO:
                    serie.setBorderColor("rgb(255, 205, 86)");
                    break;
                case AusGrafica.ID_PULSADOR_PENDIENTE:
                    serie.setBorderColor("rgb(255, 99, 132)");
                    break;
                default:
                    break;
            }
            serie.setTension(0.5);
        } 
        serie.setData(values);
        serie.setFill(false);

        dataSerieSerieLinea.addChartDataSet(serie);
        dataSerieSerieLinea.setLabels(labels);
        //return serie;
    }
    @Override
    public void cargaInicial(AusGraficasBean bean) {
        try {
            bean.getObjeto().setListaUsuarios(getCasoRemoto().consultarUsuarioPorEmpresa(bean.getConexion().getEmpresa().getId()));
            bean.getObjeto().setHashUsuarios(convertToHashUsuarios(bean.getObjeto().getListaUsuarios()));
            //bean.getObjeto().setListaUsuarios(getUsuarioRemoto().consultarPorEmpresa(bean.getConexion().getEmpresa().getId()));
            //bean.getObjeto().setHashUsuarios(getUsuarioRemoto().consultarHashTodos(bean.getConexion().getEmpresa().getId()));
            List<Maestro> listaEstadosServicios = getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SERVICIO_ESTADO);
            List<Maestro> listaEstados = getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_ESTADO);

            bean.setUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());
            //bean.setUbicaciones(bean.getUbicacionSingle().getListaHijosSegunIdPadreCobertura());
            //bean.setUbicaciones(getUbicacionRemoto().consultarPorTipo(Ubicacion.TIPO_MUNICIPIO));
            bean.setListaTiposDocumentoEmpresa(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
            for (Maestro maestro : listaEstados) {
                if (maestro.getNombre().equals(TEXTO_CERRADO)) {
                    bean.getObjeto().setEstadoCerrado(maestro.getId());
                    break;
                }
                if (maestro.getNombre().equals(TEXTO_RECHAZADO)) {
                    bean.getObjeto().setEstadoRechazado(maestro.getId());
                    break;
                }
            }

            for (Maestro maestro : listaEstadosServicios) {
                if (maestro.getNombre().equals(TEXTO_CERRADO)) {
                    bean.getObjeto().setEstadoServicioCerrado(maestro.getId());
                    break;
                }
                if (maestro.getNombre().equals(TEXTO_RECHAZADO)) {
                    bean.getObjeto().setEstadoServicioRechazado(maestro.getId());
                    break;
                }
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }

    }

    public HashMap<Integer, Usuario> convertToHashUsuarios(List<Usuario> list) {
        HashMap<Integer, Usuario> map = new HashMap<>();
        for (Usuario i : list) {
            map.put(i.getId(), i);
        }
        return map;
    }
}
