/*
 * To change this license header, choose License Headers in Project Proobjties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.servicio;

import com.saviasaludeps.savia.dominio.administracion.Perfil;
import com.saviasaludeps.savia.dominio.administracion.Rol;
import com.saviasaludeps.savia.dominio.administracion.RolPerfil;
import com.saviasaludeps.savia.web.administracion.bean.RolBean;
import com.saviasaludeps.savia.negocio.administracion.PerfilRemoto;
import com.saviasaludeps.savia.negocio.administracion.RolRemoto;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raul-palacios
 */
public class RolServicioImpl extends AccionesBO implements RolServicioIface{
    

    private RolRemoto getRolRemoto() throws Exception {
        return (RolRemoto) RemotoEJB.getEJBRemoto("RolServicio", RolRemoto.class.getName());
    }
    private PerfilRemoto getPerfilRemoto() throws Exception {
        return (PerfilRemoto) RemotoEJB.getEJBRemoto("PerfilServicio", PerfilRemoto.class.getName());
    }
    
    @Override
    public void Accion(RolBean bean) {
        if(super.ValidarSesion(bean)){
            switch(bean.getAccion()){
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
                case Url.ACCION_BORRAR:
                    borrar(bean);
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
        }else{
            System.err.println("Sesion inactiva");
        }
    }
    
    private void listar(RolBean bean){
        try{
            bean.getParamConsulta().setCantidadRegistros(getRolRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getRolRemoto().consultarLista(bean.getParamConsulta()));
        }catch(Exception e){
            bean.addError(e.getMessage());
        }
    }
    
    private void ver(RolBean bean){
        try{
            bean.setObjeto(getRolRemoto().consultar(bean.getObjeto().getId()));
        }catch(Exception e){
            bean.addError(e.getMessage());
        }
    }
    
    private void crear(RolBean bean){
        try{
            bean.setObjeto(new Rol());
        }catch(Exception e){
            bean.addError(e.getMessage());
        }
    }
    
    private void editar(RolBean bean){
        try{
            bean.setObjeto(getRolRemoto().consultar(bean.getObjeto().getId()));
        }catch(Exception e){
            bean.addError(e.getMessage());
        }
    }
    
    private void guardar(RolBean bean){
        try{
            bean.auditoriaGuardar(bean.getObjeto());
            List<RolPerfil> rolesPerfiles = new ArrayList<>();
            for (Integer perfiels : bean.getSelectedPerfiles()) {
                RolPerfil perfil = new RolPerfil(new Perfil(perfiels));
                rolesPerfiles.add(perfil);
            }
            bean.getObjeto().setRolesPerfilesList(rolesPerfiles);
            bean.getObjeto().setId(getRolRemoto().insertar(bean.getObjeto()));
            
        }catch(Exception e){
            bean.addError(e.getMessage());
        }
    }
    
    private void modificar(RolBean bean){
        try{
            bean.auditoriaModificar(bean.getObjeto());
            List<RolPerfil> rolesPerfiles = new ArrayList<>();
            for (Integer perfiels : bean.getSelectedPerfiles()) {
                RolPerfil perfil = new RolPerfil(new Perfil(perfiels));
                rolesPerfiles.add(perfil);
            }
            bean.getObjeto().setRolesPerfilesList(rolesPerfiles);
            getRolRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        }catch(Exception e){
            bean.addError(e.getMessage());
        }
    }
    
    private void borrar(RolBean bean){
        try{
            bean.setObjeto(getRolRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        }catch(Exception e){
            bean.addError(e.getMessage());
        }
    }
    
    
    
    private void cargas(RolBean bean){
        try{
            switch(bean.getAccion()){
                case Url.ACCION_LISTAR:
                    bean.setPerfilesList(getPerfilRemoto().consultarTodos());
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    bean.setPerfilesList(getPerfilRemoto().consultarTodos());
                    break;
                default:
                    break;
            }
        }catch(Exception e){
            bean.addError("No fue posible cargar las listas de apoyo");
        }
    }
    
    
    
    
    
}
