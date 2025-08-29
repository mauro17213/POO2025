package com.saviasaludeps.savia.web.informe.servicio;

import com.saviasaludeps.savia.dominio.informe.InfGrupo;
import com.saviasaludeps.savia.dominio.informe.InfGrupoUsuario;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.informe.InformeGrupoRemoto;
import com.saviasaludeps.savia.negocio.informe.InformeGrupoUsuarioRemoto;
import com.saviasaludeps.savia.web.informe.bean.InformeGrupoBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import java.util.Objects;

public class InformeGrupoServicioImpl extends AccionesBO implements InformeGrupoServicioIface {

    private InformeGrupoRemoto getGrupoRemoto() throws Exception {
        return (InformeGrupoRemoto) RemotoEJB.getEJBRemoto("InformeGrupoServicio", InformeGrupoRemoto.class.getName());
    }

    private InformeGrupoUsuarioRemoto getGrupoUsuarioRemoto() throws Exception {
        return (InformeGrupoUsuarioRemoto) RemotoEJB.getEJBRemoto("InformeGrupoUsuarioServicio", InformeGrupoUsuarioRemoto.class.getName());
    }

    private UsuarioRemoto getGnUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    @Override
    public void Accion(InformeGrupoBean bean) {
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
                case Url.ACCION_BORRAR:
                    borrar(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarUsuarios(bean);
                            break;
                        case Url.ACCION_CREAR:
                            crearUsuario(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarUsuario(bean);
                            break;
                        case Url.ACCION_BORRAR:
                            borrarUsuario(bean);
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

    private void listar(InformeGrupoBean bean) {
        try {
            bean.getParamConsultaGrupo().setCantidadRegistros(getGrupoRemoto().consultarCantidadLista(bean.getParamConsultaGrupo()));
            bean.setRegistros(getGrupoRemoto().consultarLista(bean.getParamConsultaGrupo()));
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    private void ver(InformeGrupoBean bean) {
        try {
            bean.setObjeto(getGrupoRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setListaGrupoUsuarios(getGrupoUsuarioRemoto().consultarPorIdGrupo(bean.getObjeto().getId()));
            bean.setListaFiltroUsuario(bean.getObjeto().getListaGrupoUsuarios());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(InformeGrupoBean bean) {
        try {
            bean.setObjeto(new InfGrupo());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(InformeGrupoBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjeto());
            getGrupoRemoto().insertar(bean.getObjeto());
            bean.addMensaje("Se ha creado el registro de manera exitosa");
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    private void editar(InformeGrupoBean bean) {
        try {
            bean.setObjeto(getGrupoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(InformeGrupoBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            getGrupoRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    private void borrar(InformeGrupoBean bean) {
        try {
            bean.setObjeto(getGrupoRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError("Hubo un error eliminando, favor contactar con el administrador");
        }
    }

    private void listarUsuarios(InformeGrupoBean bean) {
        if (bean.getObjeto() != null && bean.getObjeto().getId() != null) {
            try {
                bean.setObjeto(getGrupoRemoto().consultar(bean.getObjeto().getId()));
                bean.getObjeto().setListaGrupoUsuarios(getGrupoUsuarioRemoto().consultarPorIdGrupo(bean.getObjeto().getId()));
                bean.setListaFiltroUsuario(bean.getObjeto().getListaGrupoUsuarios());
            } catch (Exception ex) {
                bean.addError(ex.toString());
            }
        }
    }

    private void crearUsuario(InformeGrupoBean bean) {
        try {
            if (bean.getUsuarioRecursiva() == null || bean.getUsuarioRecursiva().isEmpty()) {
                bean.setUsuarioRecursiva(getGnUsuarioRemoto().consultarIdNombreHashTodos(bean.getConexion().getEmpresa().getId()));
            }
            bean.setObjetoUsuario(new InfGrupoUsuario());
            bean.getObjetoUsuario().setGrupo(new InfGrupo(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarUsuario(InformeGrupoBean bean) {
        try {
            //VALIDAR SI EL USUARIO EXISTE
            if (bean.getObjetoUsuario().getUsuario().getId() != null) {
                List<InfGrupo> listaGrupos = getGrupoUsuarioRemoto().consultarListaDeGruposPorUsuario(bean.getObjetoUsuario().getUsuario().getId());
                for (InfGrupo grupo : listaGrupos) {
                    if (Objects.equals(grupo.getId(), bean.getObjeto().getId())) {
                        bean.addError("El usuario ya se encuentra en el grupo");
                        break;
                    }
                }
            }
            if (!bean.isError()) {
                //Guardar el Grupo Uusuario
                bean.getObjetoUsuario().setGrupo(new InfGrupo(bean.getObjeto().getId()));
                bean.auditoriaGuardar(bean.getObjetoUsuario());
                bean.getObjetoUsuario().setId(getGrupoUsuarioRemoto().insertar(bean.getObjetoUsuario()));
                bean.addMensaje("Registro guardado de manera exitosa");
            }
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    private void borrarUsuario(InformeGrupoBean bean) {
        try {
            bean.setObjetoUsuario(getGrupoUsuarioRemoto().eliminar(bean.getObjetoUsuario().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
            bean.setObjeto(getGrupoRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setListaGrupoUsuarios(getGrupoUsuarioRemoto().consultarPorIdGrupo(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void cargaInicial(InformeGrupoBean bean) {
        try {
            bean.setListaGnUsuarios(getGnUsuarioRemoto().consultarPorEmpresa(bean.getConexion().getEmpresa().getId()));
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

}
