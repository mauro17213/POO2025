/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteImpresion;
import com.saviasaludeps.savia.web.autorizacion.servicio.ReporteImpresionServicioIface;
import com.saviasaludeps.savia.web.autorizacion.servicio.ReporteImpresionServicioImpl;
import com.saviasaludeps.savia.web.utilidades.DateUtil;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
//2023-10-18 DESCOMENTAR Y CORREGIR
//import org.primefaces.model.chart.Axis;
//import org.primefaces.model.chart.AxisType;
//import org.primefaces.model.chart.ChartSeries;
//import org.primefaces.model.chart.HorizontalBarChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.primefaces.PrimeFaces;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.title.Title;

/**
 *
 * @author Stiven Giraldo
 */
@Named
@ViewScoped
public class ReporteImpresionBean extends Url {

    @Autowired
    private ReporteImpresionServicioIface reporteImpresionServicio;
    private ReporteImpresion objeto;
    private List<ReporteImpresion> registros;
    private HorizontalBarChartModel modeloReporte;
    private Date fechaInicio;
    private Date fechaFin;
    private Map<String, String> chartDatatips;
    private String chartStyle;

    public ReporteImpresionBean() {
        this.objeto = new ReporteImpresion();
        Modulo _mod = super.validarModulo(Modulo.ID_AUTORIZACIONES_IMPRESIONES_REPORTE);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
        }
    }

    @PostConstruct
    public void postConstruct() {
        Calendar calSemana = Calendar.getInstance();
        calSemana.add(Calendar.DAY_OF_MONTH, -7);
        setFechaInicio(DateUtil.removerTiempo(calSemana.getTime()));
        setFechaFin(DateUtil.setFinalDia(new Date()));
        setRegistros(new ArrayList());
        createHorizontalBarModel();
        listar();
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        getReporteImpresionServicio().Accion(this);
        procesoFinal();
    }

    public void refrescar() {
        setFechaInicio(DateUtil.removerTiempo(getFechaInicio()));
        setFechaFin(DateUtil.setFinalDia(getFechaFin()));
        super.setAccion(Url.ACCION_LISTAR);
        getReporteImpresionServicio().Accion(this);
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                    break;
                case Url.ACCION_MODIFICAR:
                    break;
                case Url.ACCION_BORRAR:
                    break;
                case Url.ACCION_LISTAR:
                    createHorizontalBarModel();
                    PrimeFaces.current().ajax().update("frmImpresionAutorizacion:barrasHorizontales");
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_EDITAR:
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
            }
        }
        generarMensajes();
    }

    private void createHorizontalBarModel() {
        long max = 1;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        setModeloReporte(new HorizontalBarChartModel());
        List<Number> valores = new ArrayList<>();
        List<String> titulos = new ArrayList<>();
        ChartData data = new ChartData();
        HorizontalBarChartDataSet dataSet = new HorizontalBarChartDataSet();
        dataSet.setLabel("Impresiones");
        List<String> bgColor = new ArrayList<>();
        List<String> borderColor = new ArrayList<>();
        if (!getRegistros().isEmpty()) {
            if (this.registros.size() <= 10) {
            this.chartStyle += "height:400px;";
            }
            if (this.registros.size() > 10 && this.registros.size() <= 15) {
                this.chartStyle += "height:700px;";
            }
            if (this.registros.size() > 15 && this.registros.size() <= 25) {
                this.chartStyle += "height:900px;";
            }
            if (this.registros.size() > 25 && this.registros.size() <= 35) {
                this.chartStyle += "height:1200px;";
            }
            if (this.registros.size() > 35) {
                this.chartStyle += "height:1600px;";
            }
            //Asignacion de variables y titulos
            for (ReporteImpresion reporte : getRegistros()) {
                valores.add(reporte.getCantidad());
                titulos.add(dateFormat.format(reporte.getFecha()));
                bgColor.add("rgb(75, 178, 197)");
                borderColor.add("rgb(75, 178, 197)");
            }
            
            dataSet.setBorderColor(borderColor);
            dataSet.setBorderWidth(1);
            dataSet.setBackgroundColor(bgColor);
            
//            dataSet.setData(valores);
            data.addChartDataSet(dataSet);
            data.setLabels(titulos);
            getModeloReporte().setData(data);
        
            BarChartOptions opciones = new BarChartOptions();
           
            
            Title title = new Title();
            title.setDisplay(true);
            title.setText("Impresión de autorizaciones diarias desde " + dateFormat.format(fechaInicio) + " hasta " + dateFormat.format(fechaFin));
            opciones.setTitle(title);
            getModeloReporte().setOptions(opciones);
        } else {

        }
        
    }
    /*private void createHorizontalBarModel() {
        long max = 1;
        // Crear el modelo de datos para el gráfico
        HorizontalBarChartModel hbarModel = new HorizontalBarChartModel();
        ChartData data = new ChartData();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        List<String> bgColor = new ArrayList<>();
        HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
        hbarDataSet.setLabel("Impresiones");

        if (!this.registros.isEmpty()) {
            //Altura dinamica
            this.chartStyle = "margin-top: 7px;";
            if (this.registros.size() <= 10) {
                this.chartStyle += "height:400px;";
            }
            if (this.registros.size() > 10 && this.registros.size() <= 15) {
                this.chartStyle += "height:700px;";
            }
            if (this.registros.size() > 15 && this.registros.size() <= 25) {
                this.chartStyle += "height:900px;";
            }
            if (this.registros.size() > 25 && this.registros.size() <= 35) {
                this.chartStyle += "height:1200px;";
            }
            if (this.registros.size() > 35) {
                this.chartStyle += "height:1600px;";
            }
            for (ReporteImpresion reporte : getRegistros()) {
                values.add(reporte.getCantidad());
                labels.add(dateFormat.format(reporte.getFecha()));
                bgColor.add("rgb(75, 178, 197)");
            }
            hbarDataSet.setData(values);
            hbarDataSet.setBackgroundColor(bgColor);
            data.addChartDataSet(hbarDataSet);
            data.setLabels(labels);
            hbarModel.setData(data);
            
            BarChartOptions opciones = new BarChartOptions();
            Title title = new Title();
            title.setDisplay(true);
            title.setText("Impresión de autorizaciones diarias desde " + dateFormat.format(fechaInicio) + " hasta " + dateFormat.format(fechaFin));
            opciones.setTitle(title);
            hbarModel.setOptions(opciones);
        } else {

        }
        /*#4bb2c5 2023-10-18 DESCOMENTAR Y CORREGIR
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        modeloReporte = new HorizontalBarChartModel();
        ChartSeries impresiones = new ChartSeries();
        impresiones.setLabel("Reporte de impresiones");
        modeloReporte.setTitle("Impresión de autorizaciones diarias desde " + dateFormat.format(fechaInicio) + " hasta " + dateFormat.format(fechaFin));
        modeloReporte.setLegendPosition("e");
        modeloReporte.setStacked(true);
        modeloReporte.setLegendEscapeHtml(true);
        modeloReporte.setAnimate(true);
        chartDatatips = new HashMap<String, String>();
        modeloReporte.setShowPointLabels(true);
        if (!this.registros.isEmpty()) {
            //Altura dinamica
            this.chartStyle = "margin-top: 7px;";
            if (this.registros.size() <= 10) {
                this.chartStyle += "height:400px;";
            }
            if (this.registros.size() > 10 && this.registros.size() <= 15) {
                this.chartStyle += "height:700px;";
            }
            if (this.registros.size() > 15 && this.registros.size() <= 25) {
                this.chartStyle += "height:900px;";
            }
            if (this.registros.size() > 25 && this.registros.size() <= 35) {
                this.chartStyle += "height:1200px;";
            }
            if (this.registros.size() > 35) {
                this.chartStyle += "height:1600px;";
            }
            //Insertar las series
            for (ReporteImpresion contador : this.registros) {
                //Eje x primer parametro
                impresiones.set(dateFormat.format(contador.getFecha()), contador.getCantidad());
                //Máximo valor para el eje X
                if (max < contador.getCantidad()) {
                    max = contador.getCantidad();
                }
            }
            //Añadir el 5% del máximo
            max = Math.round((max * (1.05)));
            modeloReporte.addSeries(impresiones);
            //Definir eje X
            Axis xAxis = modeloReporte.getAxis(AxisType.X);
            xAxis.setLabel("Impresiones");
            xAxis.setMin(0);
            xAxis.setMax(max + 1);
            xAxis.setTickCount(1);
            //Definir eje Y
            Axis yAxis = modeloReporte.getAxis(AxisType.Y);
            yAxis.setLabel("Día");
            yAxis.setMin(0);
            yAxis.setTickAngle(1);
            yAxis.setMax(10);
            modeloReporte.setDatatipFormat(getDatatipFormatY());
        } else {
            //Modelo vacio
            modeloReporte.setTitle("No se encontraron resultados");
            impresiones.set("Sin resultados", 0);
            modeloReporte.addSeries(impresiones);
            Axis xAxis = modeloReporte.getAxis(AxisType.X);
            xAxis.setLabel("");
            xAxis.setMin(0);
            xAxis.setMax(1);
            //Definir eje Y
            Axis yAxis = modeloReporte.getAxis(AxisType.Y);
            yAxis.setLabel("");
        }

    }*/

    /**
     *
     * @throws IOException
     */
    public void exportar() throws IOException {
        if (!this.registros.isEmpty()) {
            int indiceFila = 0;
            String fileName = "Conexiones_Reporte_Impresiones" + new SimpleDateFormat("yyyyMMddHHmm'.xls'").format(new Date());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            //Crear el documento de excel
            HSSFWorkbook libro = new HSSFWorkbook();
            HSSFSheet hoja1 = libro.createSheet("Impresiones Por Fecha");
            //Formatos necesarios
            CellStyle formatoFecha = libro.createCellStyle();
            CreationHelper createHelper = libro.getCreationHelper();
            formatoFecha.setDataFormat(
                    createHelper.createDataFormat().getFormat("yyyy-mm-dd"));
            hoja1.setColumnWidth(0, 5800);
            hoja1.setColumnWidth(1, 7900);
            //Llenar la Hoja Impresiones por fecha
            HSSFRow filaTitulo = hoja1.createRow(0);
            HSSFCell celdaTituloFecha = filaTitulo.createCell(0);
            celdaTituloFecha.setCellValue("Fecha");
            HSSFCell celdaTituloCantidad = filaTitulo.createCell(1);
            celdaTituloCantidad.setCellValue("Cantidad Autorizaciones");
            indiceFila++;
            for (ReporteImpresion contador : this.registros) {
                HSSFRow fila = hoja1.createRow(indiceFila);
                HSSFCell celdaFecha = fila.createCell(0);
                celdaFecha.setCellValue(contador.getFecha());
                celdaFecha.setCellStyle(formatoFecha);
                HSSFCell celdaCantidad = fila.createCell(1);
                celdaCantidad.setCellValue(contador.getCantidad());
                indiceFila++;
            }
            OutputStream out = ec.getResponseOutputStream();
            libro.write(out);
            out.close();
            fc.responseComplete();
        } else {
            this.addError("No hay registros para exportar");
        }
    }

    public String getDatatipsJs() {
        StringBuilder sb = new StringBuilder("switch ( point ) {\n");
        for (String point : chartDatatips.keySet()) {
            sb.append("case '").append(point).append("': return '").append(chartDatatips.get(point)).append("'; break;\n");
        }
        sb.append("default: return 'Unknown point'; break; }");
        return sb.toString();
    }

    public ReporteImpresionServicioIface getReporteImpresionServicio() {
        if (reporteImpresionServicio == null) {
            this.reporteImpresionServicio = new ReporteImpresionServicioImpl();
        }
        return reporteImpresionServicio;
    }

    public void setReporteImpresionServicio(ReporteImpresionServicioIface reporteImpresionServicio) {
        this.reporteImpresionServicio = reporteImpresionServicio;
    }

    public ReporteImpresion getObjeto() {
        return objeto;
    }

    public void setObjeto(ReporteImpresion objeto) {
        this.objeto = objeto;
    }

    public List<ReporteImpresion> getRegistros() {
        return registros;
    }

    public void setRegistros(List<ReporteImpresion> registros) {
        this.registros = registros;
    }

    public HorizontalBarChartModel getModeloReporte() {
        return modeloReporte;
    }

    public void setModeloReporte(HorizontalBarChartModel modeloReporte) {
        this.modeloReporte = modeloReporte;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Map<String, String> getChartDatatips() {
        return chartDatatips;
    }

    public void setChartDatatips(Map<String, String> chartDatatips) {
        this.chartDatatips = chartDatatips;
    }

    public String getChartStyle() {
        return chartStyle;
    }

    public void setChartStyle(String chartStyle) {
        this.chartStyle = chartStyle;
    }

    public String getDatatipFormatY() {
        return "%s impresiones";
    }

}
