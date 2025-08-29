/*
 * To change this license header, choose License Headers in Project Proobjties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.mapa.servicio;

import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.mapa.MapaAfiliado;
import com.saviasaludeps.savia.dominio.mapa.MapaPrestadorSede;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.mapa.MapaAfiliadoRemoto;
import com.saviasaludeps.savia.web.mapa.bean.MapaAfiliadoBean;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;

/**
 *
 * @author raul-palacios
 */
public class MapaAfiliadoServicioImpl extends AccionesBO implements MapaAfiliadoServicioIface {

    private MapaAfiliadoRemoto getMapaAfiliadoRemoto() throws Exception {
        return (MapaAfiliadoRemoto) RemotoEJB.getEJBRemoto("MapaAfiliadoServicio", MapaAfiliadoRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    @Override
    public void Accion(MapaAfiliadoBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    ver(bean);
                    break;
                default:
                    break;
            }
            cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(MapaAfiliadoBean bean) {
        try {
            if (bean.getParamConsulta().getParametroConsulta1() != null) {
//                String aa = (String) bean.getParamConsulta().getParametroConsulta1();
//                Integer zz = (Integer)Integer.parseInt((String) bean.getParamConsulta().getParametroConsulta1());
                Ubicacion ubi = bean.getUbicacionesRecursiva().get((Integer) Integer.parseInt((String) bean.getParamConsulta().getParametroConsulta1()));
                bean.setCentroLatitud(ubi.getGpsLatitud());
                bean.setCentroLongitud(ubi.getGpsLongitud());
                bean.setProfundidad(ubi.getGpsProfundidad());
            }
            List<MapaAfiliado> listaAfiliados = getMapaAfiliadoRemoto().consultarListaMapaAfiliados(bean.getParamConsulta());
            List<MapaPrestadorSede> listaPrestadorSedes = getMapaAfiliadoRemoto().consultarListaMapaPrestadorSedesIdMunicipio(
                    (bean.getParamConsulta().getParametroConsulta2() == null) ? null : Integer.valueOf((String) bean.getParamConsulta().getParametroConsulta2()),
                    (bean.getParamConsulta().getParametroConsulta1() == null) ? null : Integer.valueOf((String) bean.getParamConsulta().getParametroConsulta1())
            );
            bean.setRegistrosMapa(listaAfiliados, listaPrestadorSedes);
//            bean.setRegistrosMapa(getMapaAfiliadoRemoto().consultarListaMapaAfiliados(bean.getCentroLatitud(), bean.getCentroLongitud(), 10));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(MapaAfiliadoBean bean) {
        try {

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(MapaAfiliadoBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_VER:
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de apoyo");
        }
    }

    @Override
    public void cargasInicial(MapaAfiliadoBean bean) {
        try {
            bean.setUbicacionesRecursiva(UbicacionSingle.getInstance().getHashMunicipiosAntioquia());
            bean.setListaFiltroUbicaciones(UbicacionSingle.getInstance().getListaMunicipiosAntioquia());
            bean.setListaFiltroPrestadorSedes(getMapaAfiliadoRemoto().consultarListaMapaPrestadorSedesIdMunicipio(null, null));
            bean.setListaFiltroMaestrosRegimen(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_REGIMEN));
            bean.setListaFiltroMaestrosGenero(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_SEXO));
            bean.setListaFiltroMaestrosModeloLiquidacion(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_MODELO_LIQUIDACION));
            //2025-08-13 jyperez actualizamos los campos del mapa, con los datos de PropApl
            bean.setRutaImagenes(PropApl.getInstance().get(PropApl.GN_MAPS_IMAGENES));
            bean.setRutaGoogleMapsEmpresarial(PropApl.getInstance().get(PropApl.GN_MAPS_KEY));
        } catch (Exception ex) {
            bean.addError("No fue posible cargar las listas de apoyo");
        }
    }

}
