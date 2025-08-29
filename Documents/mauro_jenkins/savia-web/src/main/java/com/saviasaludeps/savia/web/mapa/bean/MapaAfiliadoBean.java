/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.mapa.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.mapa.MapaAfiliado;
import com.saviasaludeps.savia.dominio.mapa.MapaPrestadorSede;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.saviasaludeps.savia.web.mapa.servicio.MapaAfiliadoServicioIface;
import java.util.ArrayList;
import java.util.HashMap;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.StateChangeEvent;
//import org.primefaces.model.map.Circle;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Symbol;

/**
 *
 * @author rpalacic (SystemTech Integral)
 */
@ManagedBean
@ViewScoped
public class MapaAfiliadoBean extends Url {

    private static final String ICONO_RUTA = "https://www.saviasaludeps.com/sitioweb/images/mapa/";
    private static final String ICONO_01 = "afiliado.png";
    private static final String ICONO_02 = "prestador_sede.png";

    private static final double CENTRO_LATITUD_INICIAL = 6.200034400;
    private static final double CENTRO_LONGITUD_INICIAL = -75.575125400;
    private static final int PROFUNDIDAD_INICIAL = 10;

    private MapaAfiliadoServicioIface mapaAfiliadoServicio;

    private List<MapaAfiliado> listaMapaAfiliados;
    private List<MapaPrestadorSede> listaMapaPrestadorSedes;
    
    private HashMap<Integer, Ubicacion> ubicacionesRecursiva;
    
    private List<Ubicacion> listaFiltroUbicaciones;
    private List<MapaPrestadorSede> listaFiltroPrestadorSedes;
    private List<Maestro> listaFiltroMaestrosRegimen;
    private List<Maestro> listaFiltroMaestrosGenero;
    private List<Maestro> listaFiltroMaestrosModeloLiquidacion;

    private MapModel<Long> registrosMapa;
    private double centroLatitud = CENTRO_LATITUD_INICIAL;
    private double centroLongitud = CENTRO_LONGITUD_INICIAL;
    private int profundidad = PROFUNDIDAD_INICIAL;
    
    //2025-08-13 jyperez creación de variables para reemplazar el uso de las direcciones directas y utilizar PropApl
    private String rutaGoogleMapsEmpresarial;// = AfiliadoParametro.RUTA_GOOGLE_MAPS_KEY;
    private String rutaImagenes;

    public MapaAfiliadoBean() {
        Modulo _mod = super.validarModulo(Modulo.ID_MAPA_AFILIADO);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
        }
    }

    @PostConstruct
    public void postConstruct() {
        getMapaAfiliadoServicio().cargasInicial(this);
        registrosMapa = new DefaultMapModel<>();
        filtros();
//        listar();
    }

    public MapaAfiliadoServicioIface getMapaAfiliadoServicio() {
        return mapaAfiliadoServicio;
    }

    public void setMapaAfiliadoServicio(MapaAfiliadoServicioIface mapaAfiliadoServicio) {
        this.mapaAfiliadoServicio = mapaAfiliadoServicio;
    }

    public List<MapaAfiliado> getListaMapaAfiliados() {
        return listaMapaAfiliados;
    }

    public void setListaMapaAfiliados(List<MapaAfiliado> listaMapaAfiliados) {
        this.listaMapaAfiliados = listaMapaAfiliados;
    }
    
    public List<MapaPrestadorSede> getListaMapaPrestadorSedes() {
        return listaMapaPrestadorSedes;
    }

    public void setListaMapaPrestadorSedes(List<MapaPrestadorSede> listaMapaPrestadorSedes) {
        this.listaMapaPrestadorSedes = listaMapaPrestadorSedes;
    }

    public List<Ubicacion> getListaFiltroUbicaciones() {
        return listaFiltroUbicaciones;
    }

    public void setListaFiltroUbicaciones(List<Ubicacion> listaFiltroUbicaciones) {
        this.listaFiltroUbicaciones = listaFiltroUbicaciones;
    }

    public List<MapaPrestadorSede> getListaFiltroPrestadorSedes() {
        return listaFiltroPrestadorSedes;
    }

    public void setListaFiltroPrestadorSedes(List<MapaPrestadorSede> listaFiltroPrestadorSedes) {
        this.listaFiltroPrestadorSedes = listaFiltroPrestadorSedes;
    }

    public HashMap<Integer, Ubicacion> getUbicacionesRecursiva() {
        return ubicacionesRecursiva;
    }

    public void setUbicacionesRecursiva(HashMap<Integer, Ubicacion> ubicacionesRecursiva) {
        this.ubicacionesRecursiva = ubicacionesRecursiva;
    }

    public List<Maestro> getListaFiltroMaestrosRegimen() {
        return listaFiltroMaestrosRegimen;
    }

    public void setListaFiltroMaestrosRegimen(List<Maestro> listaFiltroMaestrosRegimen) {
        this.listaFiltroMaestrosRegimen = listaFiltroMaestrosRegimen;
    }

    public List<Maestro> getListaFiltroMaestrosGenero() {
        return listaFiltroMaestrosGenero;
    }

    public void setListaFiltroMaestrosGenero(List<Maestro> listaFiltroMaestrosGenero) {
        this.listaFiltroMaestrosGenero = listaFiltroMaestrosGenero;
    }

    public List<Maestro> getListaFiltroMaestrosModeloLiquidacion() {
        return listaFiltroMaestrosModeloLiquidacion;
    }

    public void setListaFiltroMaestrosModeloLiquidacion(List<Maestro> listaFiltroMaestrosModeloLiquidacion) {
        this.listaFiltroMaestrosModeloLiquidacion = listaFiltroMaestrosModeloLiquidacion;
    }

    public double getCentroLatitud() {
        return centroLatitud;
    }

    public void setCentroLatitud(double centroLatitud) {
        this.centroLatitud = centroLatitud;
    }

    public double getCentroLongitud() {
        return centroLongitud;
    }

    public void setCentroLongitud(double centroLongitud) {
        this.centroLongitud = centroLongitud;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    public void setRegistrosMapa(List<MapaAfiliado> listaMapaAfiliados, List<MapaPrestadorSede> listaMapaPrestadorSedes) {
//        genSimbolo01();
        registrosMapa = new DefaultMapModel<>();
        String ruta01 = rutaImagenes + ICONO_01;
        String ruta02 = rutaImagenes + ICONO_02;
        for (MapaAfiliado obj : listaMapaAfiliados) {
            LatLng coordenadas = new LatLng(obj.getDireccionGeorefLatitud().doubleValue(), obj.getDireccionGeorefLongitud().doubleValue());
            Marker marcador = new Marker(coordenadas, obj.getNombreCompleto(), obj.getIdAfiliado(), ruta01);
//            marcador.setIcon(getSymbol());
            registrosMapa.addOverlay(marcador);
        }
        for (MapaPrestadorSede obj : listaMapaPrestadorSedes) {
            LatLng coordenadas = new LatLng(obj.getDireccionGeorefLatitud(), obj.getDireccionGeorefLongitud());
            Marker marcador = new Marker(coordenadas, obj.getNombreSede(), obj.getNombreSede(), ruta02);
//            marcador.setIcon(getSymbol());
            registrosMapa.addOverlay(marcador);
        }
//        //Circulo
//        LatLng coord = new LatLng(centroLatitud, centroLongitud);
//        Circle<Long> circle1 = new Circle<>(coord, 10000);
//        circle1.setStrokeColor("#d93c3c");
//        circle1.setFillColor("#d93c3c");
//        circle1.setFillOpacity(0.1);
//        circle1.setData(1L);
//        registrosMapa.addOverlay(circle1);
    }

    public MapModel<Long> getRegistrosMapa() {
        return registrosMapa;
    }

    public void setRegistrosMapa(MapModel<Long> registrosMapa) {
        this.registrosMapa = registrosMapa;
    }

    public void filtros() {
        PrimeFaces.current().ajax().update("frmFiltro:panelFiltro");
        PrimeFaces.current().executeScript("PF('dialogoFiltro').show()");
//        procesoFinal();
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        getMapaAfiliadoServicio().Accion(this);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getMapaAfiliadoServicio().Accion(this);
        procesoFinal();
    }

    public void ver(int _id) {
//        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getMapaAfiliadoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
//                    crearLog(getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmMapas");
                    PrimeFaces.current().executeScript("PF('dialogoFiltro').hide()");
                    break;
                case Url.ACCION_VER:
//                    crearLog(getObjeto().toString());
                    break;
            }
//            refrescar();
        }
        generarMensajes();
    }

    private Symbol symbol;

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public void onStateChange(StateChangeEvent event) {
        // Obtén el nuevo estado del mapa
        centroLatitud = event.getCenter().getLat();
        centroLongitud = event.getCenter().getLng();
        profundidad = event.getZoomLevel();
//        // Actualiza los marcadores
//        registrosMapa.addOverlay(new Marker(new LatLng(centroLatitud, centroLongitud), "Centro", 1001));
//        refrescar();
    }

    public List<Marker<Long>> getMarcadores() {
        return registrosMapa.getMarkers();
    }

    public void onMarkerSelect(OverlaySelectEvent<Long> event) {
        Marker<Long> marcador = (Marker) event.getOverlay();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        marcador.getData() + " ", marcador.getTitle()));
    }

    public String getUbicacionRecursiva(int id) {
        String ubicacionStr = "";
        Ubicacion _municipio = ubicacionesRecursiva.get(id);
        if (_municipio != null) {
            if (_municipio.getUbicacionPadre() != null) {
                if (_municipio.getUbicacionPadre().getUbicacionPadre() != null) {
                    ubicacionStr = _municipio.getUbicacionPadre().getUbicacionPadre().getNombre();
                }
                ubicacionStr = _municipio.getUbicacionPadre().getNombre() + ((!ubicacionStr.equals("")) ? " - " + ubicacionStr : "");
            }
            ubicacionStr = _municipio.getNombre() + ((!ubicacionStr.equals("")) ? " - " + ubicacionStr : "");
        }
        return ubicacionStr;
    }

    public List<Ubicacion> completarUbicacion(String query) {
        List<Ubicacion> ubicacionesFiltradas = new ArrayList<>();
        for (Ubicacion ubicacion : this.getListaFiltroUbicaciones()) {
            if (ubicacion.getNombre().toLowerCase().contains(query.toLowerCase())) {
                ubicacionesFiltradas.add(ubicacion);
            }
        }
        if (ubicacionesFiltradas.size() == 1) {
            getParamConsulta().setParametroConsulta1(ubicacionesFiltradas.get(0).getId());
        }
        return ubicacionesFiltradas;
    }

    /**
     * @return the rutaGoogleMapsEmpresarial
     */
    public String getRutaGoogleMapsEmpresarial() {
        return rutaGoogleMapsEmpresarial;
    }

    /**
     * @param rutaGoogleMapsEmpresarial the rutaGoogleMapsEmpresarial to set
     */
    public void setRutaGoogleMapsEmpresarial(String rutaGoogleMapsEmpresarial) {
        this.rutaGoogleMapsEmpresarial = rutaGoogleMapsEmpresarial;
    }

    /**
     * @return the rutaImagenes
     */
    public String getRutaImagenes() {
        return rutaImagenes;
    }

    /**
     * @param rutaImagenes the rutaImagenes to set
     */
    public void setRutaImagenes(String rutaImagenes) {
        this.rutaImagenes = rutaImagenes;
    }

}
