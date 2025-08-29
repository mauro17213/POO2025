/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.servicio;

import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipoRelacion;
import com.saviasaludeps.savia.negocio.administracion.MaestroTipoRelacionRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroTipoRemoto;
import com.saviasaludeps.savia.negocio.administracion.PerfilRemoto;
import com.saviasaludeps.savia.web.administracion.bean.MaestroTipoBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author raul-palacios
 */
public class MaestroTipoServicioImpl extends AccionesBO implements MaestroTipoServicioIface {

    private MaestroTipoRemoto getMaestroTipoRemoto() throws Exception {
        return (MaestroTipoRemoto) RemotoEJB.getEJBRemoto("MaestroTipoServicio", MaestroTipoRemoto.class.getName());
    }

    private PerfilRemoto getPerfilRemoto() throws Exception {
        return (PerfilRemoto) RemotoEJB.getEJBRemoto("PerfilServicio", PerfilRemoto.class.getName());
    }

    private MaestroTipoRelacionRemoto getMaestroTipoRelacionRemoto() throws Exception {
        return (MaestroTipoRelacionRemoto) RemotoEJB.getEJBRemoto("MaestroTipoRelacionServicio", MaestroTipoRelacionRemoto.class.getName());
    }

    @Override
    public void Accion(MaestroTipoBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    ver(bean);
                    break;
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_GUARDAR:

                    break;
                case Url.ACCION_EDITAR:
                    editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
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
            cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(MaestroTipoBean bean) {
        try {
            bean.setListaMaestroTipoRelacion(new ArrayList());
            bean.getParamConsulta().setCantidadRegistros(getMaestroTipoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getMaestroTipoRemoto().consultarLista(bean.getParamConsulta()));
            for(MaestroTipo maestrosPadre: bean.getRegistros()){
                maestrosPadre.setMaestroTipo(new ArrayList());
                List<MaestroTipoRelacion> relaciones = getMaestroTipoRelacionRemoto()
                        .consultarPadresPorTipoHijo(maestrosPadre.getTipo());
                if (!relaciones.isEmpty()) {
                    relaciones.forEach(padre -> {
                        for(MaestroTipo maestrosTipo:bean.getListaMaestroTiposPadre()){
                            if(maestrosTipo.getTipo().equals(padre.getMaestroTipoPadre().getTipo()))
                                padre.getMaestroTipoPadre().setNombre(maestrosTipo.getNombre());
                        }
                        maestrosPadre.getMaestroTipo().add(padre.getMaestroTipoPadre());
                    });
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(MaestroTipoBean bean) {
        try {
            bean.setObjeto(getMaestroTipoRemoto().consultar(bean.getObjeto().getTipo()));
            bean.getObjeto().setMaestroTipo(new ArrayList());
                List<MaestroTipoRelacion> relaciones = getMaestroTipoRelacionRemoto()
                        .consultarPadresPorTipoHijo(bean.getObjeto().getTipo());
                if (!relaciones.isEmpty()) {
                    relaciones.forEach(padre -> {
                        for(MaestroTipo maestrosTipo:bean.getListaMaestroTiposPadre()){
                            if(maestrosTipo.getTipo().equals(padre.getMaestroTipoPadre().getTipo()))
                                padre.getMaestroTipoPadre().setNombre(maestrosTipo.getNombre());
                        }
                        bean.getObjeto().getMaestroTipo().add(padre.getMaestroTipoPadre());
                    });
                }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(MaestroTipoBean bean) {
        try {
            bean.setObjeto(new MaestroTipo());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(MaestroTipoBean bean) {
        try {
            bean.setObjeto(getMaestroTipoRemoto().consultar(bean.getObjeto().getTipo()));
            bean.setListaMaestroTipoRelacion(getMaestroTipoRelacionRemoto().consultarPadresPorTipoHijo(bean.getObjeto().getTipo()));
            if (!bean.getListaMaestroTipoRelacion().isEmpty()) {
                bean.getListaMaestroTipoRelacion().forEach(maestroTipoRelacion -> {
                    bean.getSelectedMaestrosPadres().add(maestroTipoRelacion.getMaestroTipoPadre().getTipo());
                });
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(MaestroTipoBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            //busca los elementos que ya no estan para eliminarlos de la base de datos
            if (!bean.getListaMaestroTipoRelacion().isEmpty()) {
                bean.getListaMaestroTipoRelacion().forEach(maestroTipoRelacion -> {
                        try {
                            getMaestroTipoRelacionRemoto().eliminar(maestroTipoRelacion.getId());
                        } catch (Exception ex) {
                            Logger.getLogger(MaestroTipoServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                });
            }
            
            for (var padreTipoMaestro : bean.getSelectedMaestrosPadres()) {
                //recorre la lista de elementos que se selecciono registrando los que no estan todavia
                MaestroTipoRelacion maestroTipoRelaciones = new MaestroTipoRelacion();
                for (MaestroTipo maestroTipo : bean.getListaMaestroTiposPadre()) {
                    if (padreTipoMaestro.equals(maestroTipo.getTipo())) {
                        maestroTipoRelaciones.setMaestroTipoPadre(maestroTipo);
                        maestroTipoRelaciones.setMaestroTipoHijo(bean.getObjeto());
                        bean.auditoriaGuardar(maestroTipoRelaciones);
                        getMaestroTipoRelacionRemoto().insertar(maestroTipoRelaciones);
                    }
                }
            }
            getMaestroTipoRemoto().actualizar(bean.getObjeto());
            bean.setSelectedMaestrosPadres(new ArrayList<>());
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(MaestroTipoBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_EDITAR:
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void cargasInicial(MaestroTipoBean bean) {
        try {
            bean.setListaPerfiles(getPerfilRemoto().consultarTodos());
            
            List<MaestroTipo> listaMaestroTipos = new ArrayList();
            for (MaestroTipo obj : getMaestroTipoRemoto().consultarTodos()) {
                    listaMaestroTipos.add(obj);
            }
            bean.setListaMaestroTiposPadre(listaMaestroTipos);
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de apoyo");
        }
    }

}
