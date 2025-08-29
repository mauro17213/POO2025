package com.saviasaludeps.savia.web.recobro.servicio;

import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.recobro.RcoGrupo;
import com.saviasaludeps.savia.dominio.recobro.RcoGrupoUsuario;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaRemoto;
import com.saviasaludeps.savia.negocio.recobro.RcoGrupoRemoto;
import com.saviasaludeps.savia.negocio.recobro.RcoGrupoUsuarioRemoto;
import com.saviasaludeps.savia.web.recobro.bean.RecobroGrupoBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;

/**
 *
 * @author sgiraldov
 */
public class RecobroGrupoServicioImpl extends AccionesBO implements RecobroGrupoServicioIface {

    private RcoGrupoRemoto getRcoGrupoRemoto() throws Exception {
        return (RcoGrupoRemoto) RemotoEJB.getEJBRemoto("RcoGrupoServicio", RcoGrupoRemoto.class.getName());
    }

    private RcoGrupoUsuarioRemoto getRcoGrupoUsuarioRemoto() throws Exception {
        return (RcoGrupoUsuarioRemoto) RemotoEJB.getEJBRemoto("RcoGrupoUsuarioServicio", RcoGrupoUsuarioRemoto.class.getName());
    }

    private PeProgramaRemoto getPeProgramaRemoto() throws Exception {
        return (PeProgramaRemoto) RemotoEJB.getEJBRemoto("PeProgramaServicio", PeProgramaRemoto.class.getName());
    }

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    @Override
    public void Accion(RecobroGrupoBean bean) {
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
                case Url.ACCION_BORRAR:
                    borrar(bean);
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
    public void cargaInicial(RecobroGrupoBean bean) {
        try {
            bean.setListaProgramas(getPeProgramaRemoto().consultarTodos());
        } catch (Exception e) {
            bean.addError("Hubo un error en la carga inicial, favor contactar con el administrador");
        }
    }

    private void listar(RecobroGrupoBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getRcoGrupoRemoto()
                    .consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getRcoGrupoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError("Hubo un error al listar, favor contactar con el adminitrador");
        }
    }

    private void ver(RecobroGrupoBean bean) {
        try {
            bean.setObjeto(getRcoGrupoRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setlistaRcoGrupoUsuario(getRcoGrupoUsuarioRemoto().consultarListaPorIdGrupo(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un error al ver, favor contactar con el administrador");
        }
    }

    private void crear(RecobroGrupoBean bean) {
        try {
            bean.setObjeto(new RcoGrupo());
            bean.getObjeto().setPeProgramaId(new PePrograma());            
        } catch (Exception e) {
            bean.addError("Hubo un erro al crear, favor contactar al administrador");
        }

    }

    @Override
    public void listarUsuarios(RecobroGrupoBean bean) {
        try {
            bean.getParamConsultaUsuarios().setEmpresaId(bean.getConexion().getEmpresa().getId());
            bean.getParamConsultaUsuarios().setCantidadRegistros(getUsuarioRemoto()
                    .consultarCantidadLista(bean.getParamConsultaUsuarios()));
            bean.setRegistrosUsuarios(getUsuarioRemoto().consultarLista(bean.getParamConsultaUsuarios()));
        } catch (Exception e) {
            bean.addError("Error al listar usuarios auditores, favor comunicarse con el administrador : " + e.getMessage());
        }
    }

    private void guardar(RecobroGrupoBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjeto());
            bean.getObjeto().setId(getRcoGrupoRemoto().insertar(bean.getObjeto()));
            //Guardar los usuarios
            if (!bean.getObjeto().getListaRcoGrupoUsuario().isEmpty()) {
                for (RcoGrupoUsuario usuario : bean.getObjeto().getListaRcoGrupoUsuario()) {
                    usuario.setRcoGrupoId(bean.getObjeto());
                    bean.auditoriaGuardar(usuario);
                    getRcoGrupoUsuarioRemoto().insertar(usuario);
                }
            }

        } catch (Exception e) {
            bean.addError("Error al guardar, favor contactar al administrador");
        }
    }

    private void borrar(RecobroGrupoBean bean) {
        try {
            bean.setObjeto(getRcoGrupoRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setlistaRcoGrupoUsuario(getRcoGrupoUsuarioRemoto()
                    .consultarListaPorIdGrupo(bean.getObjeto().getId()));
            if (!bean.getObjeto().getListaRcoGrupoUsuario().isEmpty()) {
                for (RcoGrupoUsuario grupoUsuario : bean.getObjeto().getRcoGrupoUsuariosList()) {
                    getRcoGrupoUsuarioRemoto().eliminar(grupoUsuario.getId());
                }
            }
            getRcoGrupoRemoto().eliminar(bean.getObjeto().getId());
        } catch (Exception e) {
            bean.addError("Error al borrar, favor contactar al administrador");
        }
    }

    private void editar(RecobroGrupoBean bean) {
        try {
            bean.setObjeto(getRcoGrupoRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setlistaRcoGrupoUsuario(getRcoGrupoUsuarioRemoto()
                    .consultarListaPorIdGrupo(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Error al editar, favor contactar al administrador");
        }
    }

    private void modificar(RecobroGrupoBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            getRcoGrupoRemoto().actualizar(bean.getObjeto());
            if (!bean.getObjeto().getListaRcoGrupoUsuario().isEmpty()) {
                for (RcoGrupoUsuario usuario : bean.getObjeto().getListaRcoGrupoUsuario()) {
                    if (usuario.getId() == null) {
                        usuario.setRcoGrupoId(bean.getObjeto());
                        bean.auditoriaGuardar(usuario);
                        getRcoGrupoUsuarioRemoto().insertar(usuario);
                    } else {
                        bean.auditoriaModificar(usuario);
                        getRcoGrupoUsuarioRemoto().actualizar(usuario);
                    }
                }
            }
            if (!bean.getRegistrosUsuariosborrar().isEmpty()) {
                for (RcoGrupoUsuario usuarioEliminar : bean.getRegistrosUsuariosborrar()) {
                    getRcoGrupoUsuarioRemoto().eliminar(usuarioEliminar.getId());
                }
            }
        } catch (Exception e) {
            bean.addError("Error al modificar, favor contactar al administrador");
        }
    }

}
