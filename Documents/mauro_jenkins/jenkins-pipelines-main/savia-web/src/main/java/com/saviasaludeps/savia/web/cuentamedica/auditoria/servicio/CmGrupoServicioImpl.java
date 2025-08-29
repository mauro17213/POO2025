package com.saviasaludeps.savia.web.cuentamedica.auditoria.servicio;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupo;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupoPrestador;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupoUsuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmGrupoPrestadorRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmGrupoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmGrupoUsuarioRemoto;
import com.saviasaludeps.savia.web.cuentamedica.auditoria.bean.CmGrupoBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.HashMap;

public class CmGrupoServicioImpl extends AccionesBO implements CmGrupoServicioIface {

    private CmGrupoRemoto getCmGrupoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CmGrupoServicio", CmGrupoRemoto.class.getName());
        return (CmGrupoRemoto) object;
    }

    private CmGrupoUsuarioRemoto getCmGrupoUsuarioRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CmGrupoUsuarioServicio", CmGrupoUsuarioRemoto.class.getName());
        return (CmGrupoUsuarioRemoto) object;
    }

    private CmGrupoPrestadorRemoto getCmGrupoPrestadorRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CmGrupoPrestadorServicio", CmGrupoPrestadorRemoto.class.getName());
        return (CmGrupoPrestadorRemoto) object;
    }
    
     private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }
     
    private CntPrestadorRemoto getPrestadoresRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    @Override
    public void Accion(CmGrupoBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                      switch (bean.getDoAccion()) {
                        case CmGrupoBean.ACCION_LISTAR_GRUPOS:
                            listar(bean);
                            break;
                        case CmGrupoBean.ACCION_LISTAR_USUARIOS:
                            listarUsuariosActivosPorEmpresa(bean);
                            break;
                        case CmGrupoBean.ACCION_LISTAR_PRESTADORES:
                            listarCntPrestadoresActivos(bean);
                            break;
                        case CmGrupoBean.ACCION_LISTAR_USUARIOS_POR_GRUPO:
                            listarUsuariosPorGrupo(bean);
                            break;
                        case CmGrupoBean.ACCION_LISTAR_PRESTADORES_POR_GRUPO:
                            listarCntPrestadoresPorGrupo(bean);
                            break;
                        case CmGrupoBean.ACCION_LISTAR_USUARIOS_LIDER:
                            listarUsuarioLiderDeGrupo(bean);
                            break;
                    }
                    break;
                case Url.ACCION_VER:
                     switch (bean.getDoAccion()) {
                        case CmGrupoBean.ACCION_VER_GRUPO:
                            ver(bean);
                            break;
                        case CmGrupoBean.ACCION_VER_PRESTADOR_POR_GRUPO:
                            verPrestadorGrupo(bean);
                            break;
                        case CmGrupoBean.ACCION_VER_USUARIO_POR_GRUPO:
                            verUsuarioGrupo(bean);
                            break;
                    }
                    
                    break;
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    switch (bean.getDoAccion()) {
                        case CmGrupoBean.ACCION_GUARDAR_GRUPO:
                            guardar(bean);
                            break;
                        case CmGrupoBean.ACCION_GUARDAR_PRESTADOR_POR_GRUPO:
                            guardarPrestador(bean);
                            break;
                        case CmGrupoBean.ACCION_GUARDAR_USUARIO_POR_GRUPO:
                            guardarUsuario(bean);
                            break;
                    }
                  
                    break;
                case Url.ACCION_EDITAR:
                    editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;
                case Url.ACCION_BORRAR:
                    switch (bean.getDoAccion()) {
                        case CmGrupoBean.ACCION_BORRAR_GRUPO:
                            borrar(bean);
                            break;
                        case CmGrupoBean.ACCION_BORRAR_PRESTADOR_POR_GRUPO:
                            borrarPrestadorGrupo(bean);
                            break;
                        case CmGrupoBean.ACCION_BORRAR_USUARIO_POR_GRUPO:
                            borrarUsuarioGrupo(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                case Url.ACCION_ADICIONAL_3:
                    break;
                case Url.ACCION_ADICIONAL_4:
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    @Override
    public void listar(CmGrupoBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getCmGrupoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getCmGrupoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void guardar(CmGrupoBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjeto());
            bean.getObjeto().setId(getCmGrupoRemoto().insertar(bean.getObjeto()));
            if (bean.getObjeto().getId() != null) {
                for (CmGrupoPrestador prestador : bean.getListaGrupoPrestadores()) {
                    bean.auditoriaGuardar(prestador);
                    prestador.setCmGrupo(new CmGrupo(bean.getObjeto().getId()));
                    getCmGrupoPrestadorRemoto().insertar(prestador);
                }
                for (CmGrupoUsuario usuario : bean.getListaGrupoUsuarios()) {
                    bean.auditoriaGuardar(usuario);
                    usuario.setCmGrupo(new CmGrupo(bean.getObjeto().getId()));
                    getCmGrupoUsuarioRemoto().insertar(usuario);
                }
            }
            bean.addMensaje("Se guardo con exito el grupo");
        } catch (Exception ex) {
            bean.addError("Error: " + ex.toString());
        }
    }
   
    public void guardarPrestador(CmGrupoBean bean) {
        try {
            bean.auditoriaGuardar(bean.getPrestadorEncontrado());
            getCmGrupoPrestadorRemoto().insertar(bean.getPrestadorEncontrado());
            bean.addMensaje("Se guardo con exito el prestador");
        } catch (Exception ex) {
            bean.addError("Error: " + ex.toString());
        }
    }
    
    public void guardarUsuario(CmGrupoBean bean) {
        try {
            bean.auditoriaGuardar(bean.getUsuarioEncontrado());
            getCmGrupoUsuarioRemoto().insertar(bean.getUsuarioEncontrado());
            bean.addMensaje("Se guardo con exito el usuario");
        } catch (Exception ex) {
            bean.addError("Error: " + ex.toString());
        }
    }

    @Override
    public void ver(CmGrupoBean bean) {
        try {
            bean.setListaGrupoPrestadores(new ArrayList<>());
            bean.setListaGrupoUsuarios(new ArrayList<>());
            bean.setObjeto(getCmGrupoRemoto().consultar(bean.getObjeto().getId()));
            bean.setListaGrupoPrestadores(bean.getObjeto().getListaCmGrupoPrestadores());
            bean.setListaGrupoUsuarios(bean.getObjeto().getListaCmGrupoUsuarios());
            bean.setHabilitarCamposRadicacion(false);
            if (CmGrupo.TIPO_GRUPO_RADICACION == bean.getObjeto().getTipoGrupo()) {
                bean.setHabilitarCamposRadicacion(true);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    public void verPrestadorGrupo(CmGrupoBean bean) {
        try {
            bean.setPrestadorEncontrado(getCmGrupoPrestadorRemoto().consultar( bean.getObjeto().getId(), 
                                                               bean.getPrestador().getCntPrestador().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
     public void verUsuarioGrupo(CmGrupoBean bean) {
        try {
            bean.setUsuarioEncontrado(getCmGrupoUsuarioRemoto().consultar (bean.getObjeto().getId(),
                    bean.getUsuario().getGnUsuario().getId(),  bean.getUsuario().getTipo() ));    
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
    public void listarUsuarioLiderDeGrupo(CmGrupoBean bean) {
        try {
            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta2(CmGrupoUsuario.TIPO_LIDER);
            paramConsulta.setParametroConsulta3( bean.getObjeto().getId());  
            bean.setListaGrupoUsuariosLider(getCmGrupoUsuarioRemoto().consultarPorAtributosLista(paramConsulta));    
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
 
    @Override
    public void editar(CmGrupoBean bean) {
        try {
            bean.setListaGrupoUsuarios(new ArrayList<>());
            bean.setListaGrupoPrestadores(new ArrayList<>());
            bean.setUsuario(new CmGrupoUsuario());
            bean.setPrestador(new CmGrupoPrestador());
            bean.setGnUsuario(new Usuario());
            bean.setCntPrestador(new CntPrestador());
            bean.setObjeto(getCmGrupoRemoto().consultar(bean.getObjeto().getId()));
            bean.setHabilitarCamposRadicacion(false);
            if (CmGrupo.TIPO_GRUPO_RADICACION == bean.getObjeto().getTipoGrupo()) {
                bean.setHabilitarCamposRadicacion(true);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(CmGrupoBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            getCmGrupoRemoto().actualizar(bean.getObjeto());         
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
     private void listarUsuariosActivosPorEmpresa(CmGrupoBean bean){
        try { 
            bean.getParamConsulta(1).setEmpresaId(bean.getConexion().getEmpresa().getId());
            if( bean.getParamConsulta(1).getFiltros()==null){
                 bean.getParamConsulta(1).setFiltros(new HashMap<>());
            }
            bean.getParamConsulta(1).getFiltros().put("activo", "1");
            bean.getParamConsulta(1).setCantidadRegistros(getUsuarioRemoto().consultarCantidadLista( bean.getParamConsulta(1)));
            bean.setRegistroUsuarios(getUsuarioRemoto().consultarLista( bean.getParamConsulta(1)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
    private void listarCntPrestadoresActivos(CmGrupoBean bean){
        try { 
            bean.getParamConsulta(0).setEmpresaId(bean.getConexion().getEmpresa().getId());
            if( bean.getParamConsulta(0).getFiltros()==null){
                 bean.getParamConsulta(0).setFiltros(new HashMap<>());
            }
            bean.getParamConsulta(0).getFiltros().put("activo", "1");
            bean.getParamConsulta(0).setCantidadRegistros(getPrestadoresRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistroCntPrestadores(getPrestadoresRemoto().consultarLista(bean.getParamConsulta(0)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
     private void listarCntPrestadoresPorGrupo(CmGrupoBean bean){
        try { 
            bean.getParamConsulta(3).setEmpresaId(bean.getConexion().getEmpresa().getId());
            if( bean.getParamConsulta(3).getFiltros()==null){
                 bean.getParamConsulta(3).setFiltros(new HashMap<>());
            }
            bean.getParamConsulta(3).getFiltros().put("activo", "1");
            bean.getParamConsulta(3).getFiltros().put("idGrupo", bean.getObjeto().getId());
            bean.getParamConsulta(3).setCantidadRegistros(getCmGrupoPrestadorRemoto().consultarCantidadLista(bean.getParamConsulta(3)));
            bean.setListaGrupoPrestadores(getCmGrupoPrestadorRemoto().consultarLista(bean.getParamConsulta(3)));

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
     private void listarUsuariosPorGrupo(CmGrupoBean bean){
        try { 
            bean.getParamConsulta(2).setEmpresaId(bean.getConexion().getEmpresa().getId());
            if( bean.getParamConsulta(2).getFiltros()==null){
                 bean.getParamConsulta(2).setFiltros(new HashMap<>());
            }
            bean.getParamConsulta(2).getFiltros().put("idGrupo", bean.getObjeto().getId());
            bean.getParamConsulta(2).setCantidadRegistros(getCmGrupoUsuarioRemoto().consultarCantidadLista(bean.getParamConsulta(2)));
            bean.setListaGrupoUsuarios(getCmGrupoUsuarioRemoto().consultarLista(bean.getParamConsulta(2)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }


    private void borrar(CmGrupoBean bean) {
        try {
            bean.setObjeto(getCmGrupoRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void borrarPrestadorGrupo(CmGrupoBean bean) {
        try {
            getCmGrupoPrestadorRemoto().eliminar(bean.getPrestador().getId());
            bean.addMensaje("Se eliminó el prestador de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarUsuarioGrupo(CmGrupoBean bean) {
        try {
            getCmGrupoUsuarioRemoto().eliminar(bean.getUsuario().getId());
            bean.addMensaje("Se eliminó el usuario de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void cargasInicial(CmGrupoBean bean) {
        try {

        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    private void crear(CmGrupoBean bean) {
        bean.setListaGnUsuarios(new ArrayList<>());
        bean.setListaCntPrestadores(new ArrayList<>());
        bean.setListaGrupoUsuarios(new ArrayList<>());
        bean.setListaGrupoPrestadores(new ArrayList<>());
        bean.setGnUsuario(new Usuario());
        bean.setCntPrestador(new CntPrestador());
        bean.setUsuario(new CmGrupoUsuario());
        bean.setPrestador(new CmGrupoPrestador());
        bean.setObjeto(new CmGrupo());
        bean.setHabilitarCamposRadicacion(false);
    }

}
