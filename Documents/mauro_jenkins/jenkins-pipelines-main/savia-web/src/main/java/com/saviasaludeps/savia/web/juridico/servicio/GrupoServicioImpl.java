
package com.saviasaludeps.savia.web.juridico.servicio;

import com.saviasaludeps.savia.dominio.juridico.CntjGrupo;
import com.saviasaludeps.savia.dominio.juridico.CntjUsuarioGrupo;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjGrupoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjUsuarioGrupoRemoto;
import com.saviasaludeps.savia.web.juridico.bean.GrupoBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.Objects;

/**
 *
 * @author idbohorquez
 */
public class GrupoServicioImpl extends AccionesBO implements GrupoServicioIface {
    
    private CtnjGrupoRemoto getCtnjGrupoRemoto() throws Exception {
        return (CtnjGrupoRemoto) RemotoEJB.getEJBRemoto("CntjGrupoServicio", CtnjGrupoRemoto.class.getName());
    }
    
    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }
    
    private CtnjUsuarioGrupoRemoto getCtnjUsuarioGrupoRemoto() throws Exception {
        return (CtnjUsuarioGrupoRemoto) RemotoEJB.getEJBRemoto("CntjUsuarioGrupoServicio", CtnjUsuarioGrupoRemoto.class.getName());
    }

    @Override
    public void Accion(GrupoBean bean) {
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
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarUsuarios(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarUsuariosGrupo(bean);
                            break;
                        case Url.ACCION_BORRAR:
                            borrarUsuariosGrupo(bean);
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    @Override
    public void cargasInicial(GrupoBean bean) {
        
    }

    private void listar(GrupoBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getCtnjGrupoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getCtnjGrupoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de información.");
        }
    }

    private void crear(GrupoBean bean) {
        try {
            bean.setObjeto(new CntjGrupo());
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al realizar la acción. ");
        }
    }

    private void guardar(GrupoBean bean) {
        try {
            if(bean.getObjeto().getNombre().isEmpty()){
                bean.addError("Debe indicar el nombre del grupo a crear.");
            }
            if(bean.getObjeto().getDescripcion().isEmpty()){
                bean.addError("Debe indicar la descripción.");
            }
            
            boolean existe = bean.getRegistros().stream()
                .anyMatch(elemento -> Objects.equals(elemento.getNombre(), bean.getObjeto().getNombre()));
            if(existe){
                bean.addError("Ya existe un grupo con el mismo nombre.");
            }
            
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            bean.auditoriaGuardar(bean.getObjeto());
            bean.getObjeto().setId(getCtnjGrupoRemoto().insertar(bean.getObjeto()));
            //guardado de usuarios grupos
            if(!bean.getObjeto().getListaUsuarioGrupo().isEmpty()){
                for(CntjUsuarioGrupo usuario : bean.getObjeto().getListaUsuarioGrupo()){
                    bean.auditoriaGuardar(usuario);
                    usuario.setCntjGruposId(bean.getObjeto());
                    getCtnjUsuarioGrupoRemoto().insertar(usuario);
                }
            }
            bean.addMensaje("Se creo el registro de manera exitosa");
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al realizar el guardado de la información. ");
        }
    }

    private void ver(GrupoBean bean) {
        try {
            bean.setObjeto(getCtnjGrupoRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setListaUsuarioGrupo(getCtnjUsuarioGrupoRemoto().listaUsuariosGrupo(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al consultar la información. ");
        }
    }

    private void editar(GrupoBean bean) {
        try {
            bean.setObjeto(getCtnjGrupoRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setListaUsuarioGrupo(getCtnjUsuarioGrupoRemoto().listaUsuariosGrupo(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al consultar la información. ");
        }
    }

    private void modificar(GrupoBean bean) {
        try {
            if(bean.getObjeto().getNombre().isEmpty()){
                bean.addError("Debe indicar el nombre del grupo a crear.");
            }
            if(bean.getObjeto().getDescripcion().isEmpty()){
                bean.addError("Debe indicar la descripción.");
            }
            
            boolean existe = bean.getRegistros().stream()
                .anyMatch(elemento -> (Objects.equals(elemento.getNombre(), bean.getObjeto().getNombre()) && !Objects.equals(elemento.getId(), bean.getObjeto().getId()) ));
            if(existe){
                bean.addError("Ya existe un grupo con el mismo nombre.");
            }
            
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            bean.auditoriaModificar(bean.getObjeto());
            getCtnjGrupoRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se modificó el registro de manera exitosa");
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al realizar modificación de la información. ");
        }
    }

    private void listarUsuarios(GrupoBean bean) {
        try { 
            if (!bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta(1).setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
            bean.getParamConsulta(1).getFiltros().put("activo", "1");
            bean.getParamConsulta(1).setCantidadRegistros(getUsuarioRemoto().consultarCantidadLista(bean.getParamConsulta(1)));
            bean.setListaUsuario(getUsuarioRemoto().consultarLista(bean.getParamConsulta(1)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarUsuariosGrupo(GrupoBean bean) {
        try {            
            CntjUsuarioGrupo usuarioGrupo = bean.getObjeto().getListaUsuarioGrupo().stream()
                    .filter(obj -> obj.getId() == -1)
                    .findFirst().orElse(null);

            if (usuarioGrupo == null) {
                return;
            }

            bean.auditoriaGuardar(usuarioGrupo);
            getCtnjUsuarioGrupoRemoto().insertar(usuarioGrupo);
            bean.addMensaje("Se creo el registro de manera exitosa");
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al realizar el guardado de la información. ");
        }
    }

    private void borrarUsuariosGrupo(GrupoBean bean) {
        try {  
            getCtnjUsuarioGrupoRemoto().eliminar(bean.getObjetoUsuarioGrupo().getId());
            bean.addMensaje("Registro eliminado de manera exitosa.");
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al realizar la acción. ");
        }
    }
    
}
