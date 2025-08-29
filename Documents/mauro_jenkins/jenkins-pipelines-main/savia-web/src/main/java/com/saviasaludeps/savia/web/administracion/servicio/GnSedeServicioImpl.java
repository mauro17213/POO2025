/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.servicio;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.GnSede;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.negocio.administracion.GnSedeRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.web.administracion.bean.GnSedeBean;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.util.ArrayList;

/**
 *
 * @author acuartas
 */
public class GnSedeServicioImpl extends AccionesBO implements GnSedeServicioIface {
    
    
    private GnSedeRemoto getGnSedeRemoto() throws Exception {
        return (GnSedeRemoto) RemotoEJB.getEJBRemoto("GnSedeServicio", GnSedeRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }
    
    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    @Override
    public void Accion(GnSedeBean bean) {
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
                    guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
                    editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;
                
            }
        }
    }

    @Override
    public void cargasInicial(GnSedeBean bean) {
        try {
            bean.setListaTiposSede(getMaestroRemoto().consultarPorTipo(MaestroTipo.GAT_SEDE_TIPO));
            bean.setHashTiposSede(Util.convertToHash(bean.getListaTiposSede()));
            bean.setListaUbicaciones(UbicacionSingle.getInstance().getListaUbicaciones());
            bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashUbicaciones());
            bean.setListaUsuarios(getUsuarioRemoto().consultarPorEmpresa(bean.getConexion().getEmpresa().getId()));
        } catch (Exception e) {
        }
    }

    private void listar(GnSedeBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getGnSedeRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getGnSedeRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al listar, favor contactar al administrador");
        }
    }

    private void ver(GnSedeBean bean) {
        try {
            bean.setObjeto(getGnSedeRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo en el ver, favor contactar al administrador");
        }
    }

    private void crear(GnSedeBean bean) {
        try {
            bean.setObjeto(new GnSede());
            bean.getObjeto().setGnEmpresaId(new Empresa());
            bean.getObjeto().setGnUbicacionId(new Ubicacion());
            bean.getObjeto().setGnSedeHorarioList(new ArrayList<>());
        } catch (Exception e) {
            bean.addError("Hubo un fallo al crear, favor contactar al adminitrador");
        }
    }

    private void guardar(GnSedeBean bean) {
        try {
            //Guardar Sede
            Maestro tipo = bean.getHashTiposSede().get(bean.getObjeto().getMaeTipoId());
            bean.getObjeto().setMaeTipoCodigo(tipo.getValor());
            bean.getObjeto().setMaeTipoValor(tipo.getNombre());
            bean.auditoriaGuardar(bean.getObjeto());
            if (!getGnSedeRemoto().validarExiste(bean.getObjeto().getNombre())) {
                getGnSedeRemoto().insertar(bean.getObjeto());
                bean.addMensaje("Se guardo la sede exitosamente");
            } else {
                bean.addError("El nombre usado para la sede ya existe, favor cambiarlo");
            }
            
        } catch (Exception e) {
            bean.addError("Hubo un fallo guardar, favor contactar al administrador ");
        }
    }

    private void editar(GnSedeBean bean) {
        try {
            bean.setObjeto(getGnSedeRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al editar, favor contactar al administrador");
        }
    }

    private void modificar(GnSedeBean bean) {
        try {
            //Modificar Sede
            if (bean.getErrores().isEmpty()) {
                if (!getGnSedeRemoto().validarExisteConSede(bean.getObjeto().getNombre(), bean.getObjeto().getId())) {
                    Maestro tipo = bean.getHashTiposSede().get(bean.getObjeto().getMaeTipoId());
                    bean.getObjeto().setMaeTipoCodigo(tipo.getValor());
                    bean.getObjeto().setMaeTipoValor(tipo.getNombre());
                    bean.auditoriaModificar(bean.getObjeto());
                    getGnSedeRemoto().actualizar(bean.getObjeto());
                    bean.addMensaje("Se modifico exitosamente");
                } else {
                    bean.addError("El nombre ya existe");
                }                
            }            
        } catch (Exception e) {
            bean.addError("Hubo un fallo al modificar, favor contactar al administrador");
        }
    }
    
}

