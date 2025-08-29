/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.atencionusuario;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author pavacca
 */
public class AusGrafica extends Auditoria{
    //Formato de fecha
    private static final SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat formatoMes = new SimpleDateFormat("MMMM-yyyy", new Locale("es","ES"));
    
    //Se crean los nombres para las opciones en tipos de reportes
    public static final String REPORTE_CASOS = "Casos";
    public static final String REPORTE_SERVICIO = "Servicios";
    
    //Se crean las operaciones
    public static final String OPERACION_RESPONSABLE = "Responsables";    
    public static final String OPERACION_IPS_PRESCRIPTORA = "Ips Prescriptora";
    public static final String OPERACION_IPS_DESTINO = "Ips Destino";
    
    //Estados para los pulsadores
    public static final String PULSADOR_CERRADO= "Cerrados";
    public static final String PULSADOR_VENCIDO = "Cerrados Vencidos";
    public static final String PULSADOR_PENDIENTE = "Pendientes";
    
    //IDENTIFICADORES DE OPERACION
    public static final int ID_PULSADOR_CERRADO = 1;
    public static final int ID_PULSADOR_VENCIDO = 2;
    public static final int ID_PULSADOR_PENDIENTE = 3;
    
    //Textos utilizados
    private static final String TEXTO_VS = " VS ";
    private static final String TEXTO_VACIO = "";
    private static final String TEXTO_AND_COMILLAS = "' AND '";
    private static final String TEXTO_FECHA = "Fechas";
    private static final String ESPACIO = " ";
    private static final String TITULO_CASOS = "Reportes - Casos";
    private static final String TITULO_INICIAL = "Reportes";
    private static final String GUION = "-";
    
    //Valores numericos utilizados
    private static final int TAMANO_LISTA = 6;
    
    //Variables del objeto
    private String tipoReporte;
    private Date fechaDesde;
    private Date fechaHasta;
    private String tipoOperacion;
    private List<String> listaOperaciones;
    private Integer[] listaFiltrosResponsable;
    private Integer[] listaFiltrosIps;
    private List<Usuario> listaUsuarios;
    private HashMap<Integer, Usuario> hashUsuarios;
    private List<Maestro> listaIps;
    private HashMap<Integer, Maestro> hashIps;
    private List<CntPrestadorSede> listaSedesIps;
    
    private int estadoCerrado;
    private int estadoRechazado;
    private int estadoServicioCerrado;
    private int estadoServicioRechazado;
    private List<String> listaPulsadores;
    private String[] listaFiltrosPulsadores;
    private List<AusDatoGrafica> listaDatos;
    private AusDatoGrafica datoActual;
    
    //Variables para titulos
    private String tituloPrincipal;
    private String tituloGraficaModel1;
    private String tituloBarraX;
    private String tituloBarraY;
    private String tituloLineaX;
    private String tituloLineaY;
    private String tituloGraficaModel2;
    
    
    //Variables que permiten visibilidad
    private boolean opcionUsuario;
    private boolean opcionIps;
    
    //Variable que muestra las graficas
    private boolean dataResponsables;
    private boolean dataIpsPrescriptora;
    private boolean dataIpsDestino;
    private boolean graficasBarras;
    private boolean graficasLinea;

    public AusGrafica() {
        this.listaIps = new ArrayList();
        this.listaUsuarios = new ArrayList();
        this.listaOperaciones = new ArrayList();
        this.opcionIps = false;
        this.opcionUsuario = false;
        this.dataResponsables = false;
        this.dataIpsPrescriptora = false;
        this.dataIpsDestino = false;
        this.graficasBarras = false;
        this.graficasLinea = false;
        this.listaDatos = new ArrayList();
        this.datoActual = new AusDatoGrafica();
        this.tituloPrincipal = TITULO_INICIAL;
    }  

    public SimpleDateFormat getFormato() {
        return formato;
    }

    public SimpleDateFormat getFormato2() {
        return formato2;
    }

    public static SimpleDateFormat getFormatoMes() {
        return formatoMes;
    }
    
    public static List<String> getListaReportes(){
        List<String> listaReportes = new ArrayList();
        listaReportes.add(REPORTE_CASOS);
        listaReportes.add(REPORTE_SERVICIO);
        return listaReportes;
    }
    
    public static List<String> getListOperacionesCasos(){
        List<String> listaOperacionesCasos = new ArrayList();
        listaOperacionesCasos.add(OPERACION_RESPONSABLE);
        return listaOperacionesCasos;
    }
    
    public static List<String> getListOperacionesServicios(){
        List<String> listaOperacionesServicios = new ArrayList();
        listaOperacionesServicios.add(OPERACION_RESPONSABLE);
        listaOperacionesServicios.add(OPERACION_IPS_PRESCRIPTORA);
        listaOperacionesServicios.add(OPERACION_IPS_DESTINO);
        return listaOperacionesServicios;
    }
    
    public static List<String> getListaPulsadoresEstados(){
        List<String> listaPulsadoresCasos = new ArrayList();
        listaPulsadoresCasos.add(PULSADOR_CERRADO);
        listaPulsadoresCasos.add(PULSADOR_VENCIDO);
        listaPulsadoresCasos.add(PULSADOR_PENDIENTE);
        return listaPulsadoresCasos;
    }


    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public List<String> getListaOperaciones() {
        return listaOperaciones;
    }

    public void setListaOperaciones(List<String> listaOperaciones) {
        this.listaOperaciones = listaOperaciones;
    }

    public Integer[] getListaFiltrosResponsable() {
        return listaFiltrosResponsable;
    }

    public void setListaFiltrosResponsable(Integer[] listaFiltrosResponsable) {
        this.listaFiltrosResponsable = listaFiltrosResponsable;
    }

    public Integer[] getListaFiltrosIps() {
        return listaFiltrosIps;
    }

    public void setListaFiltrosIps(Integer[] listaFiltrosIps) {
        this.listaFiltrosIps = listaFiltrosIps;
    }    
    
    public boolean isOpcionUsuario() {
        return opcionUsuario;
    }

    public void setOpcionUsuario(boolean opcionUsuario) {
        this.opcionUsuario = opcionUsuario;
    }

    public boolean isOpcionIps() {
        return opcionIps;
    }

    public void setOpcionIps(boolean opcionIps) {
        this.opcionIps = opcionIps;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public HashMap<Integer, Usuario> getHashUsuarios() {
        return hashUsuarios;
    }

    public void setHashUsuarios(HashMap<Integer, Usuario> hashUsuarios) {
        this.hashUsuarios = hashUsuarios;
    }

    public List<Maestro> getListaIps() {
        return listaIps;
    }

    public void setListaIps(List<Maestro> listaIps) {
        this.listaIps = listaIps;
    }    

    public HashMap<Integer, Maestro> getHashIps() {
        return hashIps;
    }

    public void setHashIps(HashMap<Integer, Maestro> hashIps) {
        this.hashIps = hashIps;
    }

    public List<CntPrestadorSede> getListaSedesIps() {
        return listaSedesIps;
    }

    public void setListaSedesIps(List<CntPrestadorSede> listaSedesIps) {
        this.listaSedesIps = listaSedesIps;
    }
    
    public int getEstadoCerrado() {
        return estadoCerrado;
    }

    public void setEstadoCerrado(int estadoCerrado) {
        this.estadoCerrado = estadoCerrado;
    }

    public int getEstadoServicioCerrado() {
        return estadoServicioCerrado;
    }

    public void setEstadoServicioCerrado(int estadoServicioCerrado) {
        this.estadoServicioCerrado = estadoServicioCerrado;
    }

    public int getEstadoServicioRechazado() {
        return estadoServicioRechazado;
    }

    public void setEstadoServicioRechazado(int estadoServicioRechazado) {
        this.estadoServicioRechazado = estadoServicioRechazado;
    }

    public int getEstadoRechazado() {
        return estadoRechazado;
    }

    public void setEstadoRechazado(int estadoRechazado) {
        this.estadoRechazado = estadoRechazado;
    }

    public List<String> getListaPulsadores() {
        return listaPulsadores;
    }

    public void setListaPulsadores(List<String> listaPulsadores) {
        this.listaPulsadores = listaPulsadores;
    }

    public String[] getListaFiltrosPulsadores() {
        return listaFiltrosPulsadores;
    }

    public void setListaFiltrosPulsadores(String[] listaFiltrosPulsadores) {
        this.listaFiltrosPulsadores = listaFiltrosPulsadores;
    }

    public String getTituloPrincipal() {
        return tituloPrincipal;
    }

    public void setTituloPrincipal(String tituloPrincipal) {
        this.tituloPrincipal = tituloPrincipal;
    }

    public String getTituloGraficaModel1() {
        return tituloGraficaModel1;
    }

    public void setTituloGraficaModel1(String tituloGraficaModel1) {
        this.tituloGraficaModel1 = tituloGraficaModel1;
    }

    public String getTituloBarraX() {
        return tituloBarraX;
    }

    public void setTituloBarraX(String tituloBarraX) {
        this.tituloBarraX = tituloBarraX;
    }

    public String getTituloBarraY() {
        return tituloBarraY;
    }

    public void setTituloBarraY(String tituloBarraY) {
        this.tituloBarraY = tituloBarraY;
    }

    public String getTituloLineaX() {
        return tituloLineaX;
    }

    public void setTituloLineaX(String tituloLineaX) {
        this.tituloLineaX = tituloLineaX;
    }

    public String getTituloLineaY() {
        return tituloLineaY;
    }

    public void setTituloLineaY(String tituloLineaY) {
        this.tituloLineaY = tituloLineaY;
    }

    public String getTituloGraficaModel2() {
        return tituloGraficaModel2;
    }

    public void setTituloGraficaModel2(String tituloGraficaModel2) {
        this.tituloGraficaModel2 = tituloGraficaModel2;
    }

    public boolean isDataResponsables() {
        return dataResponsables;
    }

    public void setDataResponsables(boolean dataResponsables) {
        this.dataResponsables = dataResponsables;
    }

    public boolean isDataIpsPrescriptora() {
        return dataIpsPrescriptora;
    }

    public void setDataIpsPrescriptora(boolean dataIpsPrescriptora) {
        this.dataIpsPrescriptora = dataIpsPrescriptora;
    }

    public boolean isDataIpsDestino() {
        return dataIpsDestino;
    }

    public void setDataIpsDestino(boolean dataIpsDestino) {
        this.dataIpsDestino = dataIpsDestino;
    }

    public boolean isGraficasBarras() {
        return graficasBarras;
    }

    public void setGraficasBarras(boolean graficasBarras) {
        this.graficasBarras = graficasBarras;
    }

    public boolean isGraficasLinea() {
        return graficasLinea;
    }

    public void setGraficasLinea(boolean graficasLinea) {
        this.graficasLinea = graficasLinea;
    }
    
    public List<AusDatoGrafica> getListaDatos() {
        return listaDatos;
    }

    public void setListaDatos(List<AusDatoGrafica> listaDatos) {
        this.listaDatos = listaDatos;
    }

    public AusDatoGrafica getDatoActual() {
        return datoActual;
    }

    public void setDatoActual(AusDatoGrafica datoActual) {
        this.datoActual = datoActual;
    }
    
    public void generarListaOperaciones(){
        switch(this.tipoReporte){
            case REPORTE_CASOS:
                setTituloPrincipal(TITULO_CASOS);
                setListaOperaciones(getListOperacionesCasos());
                break;
            case REPORTE_SERVICIO:
                setListaOperaciones(getListOperacionesServicios());
                break;
            default:
                setListaOperaciones(new ArrayList());
                break;
        }
    }    
    
    public void generarOpciones(){
        switch(this.tipoOperacion){
            case OPERACION_RESPONSABLE:
                setOpcionUsuario(true);
                setOpcionIps(false);
                setListaPulsadores(getListaPulsadoresEstados());
                break;
            case OPERACION_IPS_PRESCRIPTORA:
                setOpcionUsuario(false);
                setOpcionIps(true);
                setListaPulsadores(getListaPulsadoresEstados());
                break;
            case OPERACION_IPS_DESTINO:
                setOpcionUsuario(false);
                setOpcionIps(true);
                setListaPulsadores(getListaPulsadoresEstados());
                break;
            default:
                setOpcionUsuario(false);
                setOpcionIps(false);
                setListaPulsadores(new ArrayList());
                break;
        }
    }
    
    public List<Integer> getListaIdTipoConsulta(){
        List<Integer> listaIds = new ArrayList();
        for(String string : getListaFiltrosPulsadores()){
            switch(string){
                case PULSADOR_CERRADO:
                    listaIds.add(ID_PULSADOR_CERRADO);
                    break;
                case PULSADOR_VENCIDO:
                    listaIds.add(ID_PULSADOR_VENCIDO);
                    break;
                case PULSADOR_PENDIENTE:
                    listaIds.add(ID_PULSADOR_PENDIENTE);
                    break;
            }
        }
        return listaIds;
    }
    
    public String obtenerFecha(){
        String fecha = TEXTO_VACIO;
        if(getFechaDesde() != null && getFechaHasta() != null){
            fecha = formato2.format(fechaDesde) + TEXTO_AND_COMILLAS + formato2.format(fechaHasta);
        }
        return fecha;
    }
    
    public void generarTitulos(){
        limpiarTitulos();
        limpiarBoolean();
        switch(getTipoOperacion()){
            case OPERACION_RESPONSABLE:   
                configurarGraficasResponsable();                
                break;
            case OPERACION_IPS_PRESCRIPTORA:
                configurarGraficasIpsPrescriptora();
                break;
            case OPERACION_IPS_DESTINO:
                configurarGraficasIpsDestino();
                break;
            default:                               
                break;
        }        
    }
    
    public String obtenerNombreResponsable(int idResponsable){
        return hashUsuarios.get(idResponsable) != null ? hashUsuarios.get(idResponsable).getNombre() : TEXTO_VACIO;
    }
    
    public String obtenerNombreIps(int idIps){
        return hashIps.get(idIps) != null ? hashIps.get(idIps).getNombre() : TEXTO_VACIO;
    }
    
    public String obtenerMesAnterior(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getFechaDesde());
        calendar.add(Calendar.MONTH, -1);
        return getFormatoMes().format(calendar.getTime());
    }
    
    public String obtenerMesSiguiente(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getFechaDesde());
        calendar.add(Calendar.MONTH, 1);
        return getFormatoMes().format(calendar.getTime());
    }
    
    public String obtenerRangoMeses(){
        String fecha1= getFormatoMes().format(fechaDesde);
        String fecha2= getFormatoMes().format(fechaHasta);
        if(fecha1.equals(fecha2)){
            return fecha1;
        }else{            
            return fecha1 + ESPACIO + fecha2 ;
        }
    }
    
    public void agregarCantidad(int idPulsador, int cantidad){
        switch(idPulsador){
            case ID_PULSADOR_CERRADO:
                getDatoActual().setCantidadCerrados(cantidad);
                break;
            case ID_PULSADOR_PENDIENTE:
                getDatoActual().setCantidadPendientes(cantidad);
                break;
            case ID_PULSADOR_VENCIDO:
                getDatoActual().setCantidadVencidos(cantidad);
                break;
        }
    }
    
    public void agregarDatoALista(){
        getListaDatos().add(getDatoActual());
    }
    
    public void inicializarVariables(){
        setListaDatos(new ArrayList());
        setTituloPrincipal(TITULO_INICIAL);
        setDataResponsables(false);
        setDataIpsPrescriptora(false);
        setDataIpsDestino(false);
        setGraficasBarras(false);
        setGraficasLinea(false);
    }
    
    public void limpiarTitulos(){
        setTituloPrincipal(TITULO_INICIAL);
        setTituloGraficaModel1(TEXTO_VACIO);
        setTituloGraficaModel2(TEXTO_VACIO);
        setTituloBarraX(TEXTO_VACIO);
        setTituloBarraY(TEXTO_VACIO);
        setTituloLineaX(TEXTO_VACIO);
        setTituloBarraY(TEXTO_VACIO);
    }

    private void configurarGraficasResponsable() {
        String titulo = getTipoReporte() + TEXTO_VS + getTipoOperacion();
        setTituloGraficaModel1(titulo);
        setTituloBarraX(TEXTO_FECHA);
        setTituloBarraY(getTipoReporte());
        setTituloLineaX(TEXTO_FECHA);
        setTituloLineaY(getTipoReporte());
        setGraficasBarras(true);
        setGraficasLinea(true);
        setDataResponsables(true);
    }

    private void limpiarBoolean() {
        setDataResponsables(false);
        setDataIpsPrescriptora(false);
        setDataIpsDestino(false);
        setGraficasBarras(false);
        setGraficasLinea(false);
    }

    private void configurarGraficasIpsPrescriptora() {
        String titulo = getTipoReporte() + TEXTO_VS + getTipoOperacion();
        setTituloGraficaModel1(titulo);
        titulo = TITULO_INICIAL + ESPACIO + GUION + ESPACIO + getTipoReporte();
        setTituloPrincipal(titulo);
        setTituloBarraX(TEXTO_FECHA);
        setTituloBarraY(getTipoReporte());
        setTituloLineaX(TEXTO_FECHA);
        setTituloLineaY(getTipoReporte());
        setGraficasBarras(true);
        setGraficasLinea(true);
        setDataIpsPrescriptora(true);
    }

    private void configurarGraficasIpsDestino() {
        String titulo = getTipoReporte() + TEXTO_VS + getTipoOperacion();
        setTituloGraficaModel1(titulo);
        titulo = TITULO_INICIAL + ESPACIO + GUION + ESPACIO + getTipoReporte();
        setTituloPrincipal(titulo);
        setTituloBarraX(TEXTO_FECHA);
        setTituloBarraY(getTipoReporte());
        setTituloLineaX(TEXTO_FECHA);
        setTituloLineaY(getTipoReporte());
        setGraficasBarras(true);
        setGraficasLinea(true);
        setDataIpsDestino(true);
    }
    
    public boolean validarLegend(){
        boolean validar = false;
        int tamanoResponsable = getListaFiltrosResponsable() != null && getListaFiltrosPulsadores() != null ?
                getListaFiltrosResponsable().length * getListaFiltrosPulsadores().length : 0;
        int tamanoIps = getListaFiltrosIps() != null && getListaFiltrosPulsadores() != null ? 
                getListaFiltrosIps().length * getListaFiltrosPulsadores().length : 0;
        if((tamanoResponsable > 0 && tamanoResponsable <= TAMANO_LISTA) || (tamanoIps > 0 && tamanoIps <= TAMANO_LISTA)){
            validar = true;
        }
        return validar;
    }

    @Override
    public String toString() {
        return "AusGrafica{" + "tipoReporte=" + tipoReporte + ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + ", tipoOperacion=" + tipoOperacion + ", listaOperaciones=" + listaOperaciones + ", listaFiltrosResponsable=" + listaFiltrosResponsable + ", listaFiltrosIps=" + listaFiltrosIps + ", listaUsuarios=" + listaUsuarios + ", hashUsuarios=" + hashUsuarios + ", listaIps=" + listaIps + ", hashIps=" + hashIps + ", listaSedesIps=" + listaSedesIps + ", estadoCerrado=" + estadoCerrado + ", estadoRechazado=" + estadoRechazado + ", listaPulsadores=" + listaPulsadores + ", listaFiltrosPulsadores=" + listaFiltrosPulsadores + ", listaDatos=" + listaDatos + ", datoActual=" + datoActual + ", tituloPrincipal=" + tituloPrincipal + ", tituloGraficaModel1=" + tituloGraficaModel1 + ", tituloBarraX=" + tituloBarraX + ", tituloBarraY=" + tituloBarraY + ", tituloLineaX=" + tituloLineaX + ", tituloLineaY=" + tituloLineaY + ", tituloGraficaModel2=" + tituloGraficaModel2 + ", opcionUsuario=" + opcionUsuario + ", opcionIps=" + opcionIps + ", dataResponsables=" + dataResponsables + ", dataIpsPrescriptora=" + dataIpsPrescriptora + ", dataIpsDestino=" + dataIpsDestino + ", graficasBarras=" + graficasBarras + ", graficasLinea=" + graficasLinea + '}';
    }
    
}
