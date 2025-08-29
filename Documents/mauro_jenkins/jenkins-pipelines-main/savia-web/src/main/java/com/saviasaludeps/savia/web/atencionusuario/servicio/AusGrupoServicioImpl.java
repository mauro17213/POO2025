package com.saviasaludeps.savia.web.atencionusuario.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.atencionusuario.AusGrupo;
import com.saviasaludeps.savia.dominio.atencionusuario.AusGrupoUsuario;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusGrupoRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusGrupoUsuarioRemoto;
import com.saviasaludeps.savia.web.atencionusuario.bean.AusGrupoBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AusGrupoServicioImpl extends AccionesBO implements AusGrupoServicioIface {

    private AusGrupoRemoto getGrupoRemoto() throws Exception {
        return (AusGrupoRemoto) RemotoEJB.getEJBRemoto("AusGrupoServicio", AusGrupoRemoto.class.getName());
    }

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    private AusGrupoUsuarioRemoto getGrupoUsuarioRemoto() throws Exception {
        return (AusGrupoUsuarioRemoto) RemotoEJB.getEJBRemoto("AusGrupoUsuarioServicio", AusGrupoUsuarioRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
        return (MaestroRemoto) object;
    }

    @Override
    public void Accion(AusGrupoBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    switch (bean.getDoAccion()) {
                        case AusGrupoBean.ACCION_LISTAR_GRUPOS:
                            listar(bean);
                            break;
                        case AusGrupoBean.ACCION_LISTAR_USUARIOS:
                            listarUsuarios(bean);
                            break;
                        case AusGrupoBean.ACCION_LISTAR_GRUPO_USUARIOS:
                            listarGrupoUsuarios(bean);
                            break;
                    }
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
                    listarGrupoUsuarios(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    switch (bean.getDoAccion()) {
                        case AusGrupoBean.ACCION_CAMBIAR_ORDER:
                            break;
                        case AusGrupoBean.ACCION_MODIFICAR_GRUPO:
                            modificar(bean);
                            break;
                    }
                    break;
                case Url.ACCION_BORRAR:
                    switch (bean.getDoAccion()) {
                        case AusGrupoBean.ACCION_BORRAR_GRUPO:
                            borrar(bean);
                            break;
                        case AusGrupoBean.ACCION_BORRAR_USUARIO:
                            borrarUsuarioGrupo(bean);
                            break;
                    }
                    break;
                default:
                    break;
            }
            cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(AusGrupoBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getGrupoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getGrupoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());

        }
    }

    private void listarUsuarios(AusGrupoBean bean) {
        try {
            bean.getParamConsultaUsuarios().setEmpresaId(bean.getConexion().getEmpresa().getId());
            bean.getParamConsultaUsuarios().setCantidadRegistros(getUsuarioRemoto().consultarCantidadLista(bean.getParamConsultaUsuarios()));
            bean.setRegistrosUsuarios(getUsuarioRemoto().consultarLista(bean.getParamConsultaUsuarios()));
        } catch (Exception e) {
            bean.addError("Error al listar usuarios auditores, favor comunicarse con el administrador : " + e.getMessage());
        }
    }

    private void listarGrupoUsuarios(AusGrupoBean bean) {
        try {
            bean.getParamConsultaGrupoUsuarios().setEmpresaId(bean.getConexion().getEmpresa().getId());
            bean.getParamConsultaGrupoUsuarios().setCantidadRegistros(getGrupoUsuarioRemoto().consultarCantidadListaPorGrupo(bean.getParamConsultaGrupoUsuarios()));
            bean.getParamConsultaGrupoUsuarios().setRegistrosPagina(bean.getParamConsultaGrupoUsuarios().getCantidadRegistros() + 1);
            bean.setRegistrosGrupoUsuarios(getGrupoUsuarioRemoto().consultarListaPorGrupo(bean.getParamConsultaGrupoUsuarios()));
        } catch (Exception e) {
            bean.addError("Error al listar grupo de usuarios, favor comunicarse con el administrador : " + e.getMessage());
        }
    }

    private void ver(AusGrupoBean bean) {
        try {
            bean.setObjeto(getGrupoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Error al visualizar un grupo:" + e.getMessage());
        }
    }

    private void crear(AusGrupoBean bean) {
        try {
            //bean.getObjeto().setOrden(1);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(AusGrupoBean bean) {
        try {
            bean.setObjeto(getGrupoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Error al editar un grupo: " + e.getMessage());
        }
    }

    private void guardar(AusGrupoBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjeto());
            bean.getObjeto().setId(getGrupoRemoto().insertar(bean.getObjeto()));

            if (bean.getObjeto().getId() != null) {

                for (AusGrupoUsuario grupoUsuario : bean.getRegistrosGrupoUsuarios()) {
                    bean.auditoriaGuardar(grupoUsuario);
                    grupoUsuario.setAusGrupo(new AusGrupo(bean.getObjeto().getId()));
                    getGrupoUsuarioRemoto().insertar(grupoUsuario);
                }

                bean.getObjeto().setListaGrupoUsuarios(bean.getRegistrosGrupoUsuarios());
            }
        } catch (Exception e) {
            bean.addError("Error al guardar grupo, por favor contacte administrador : " + e.getMessage());
        }
    }

    private void modificar(AusGrupoBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            getGrupoRemoto().actualizar(bean.getObjeto());

            for (AusGrupoUsuario grupoUsuario : bean.getRegistrosGrupoUsuarios()) {
                if (grupoUsuario.getId() == null) {
                    bean.auditoriaGuardar(grupoUsuario);
                    grupoUsuario.setAusGrupo(new AusGrupo(bean.getObjeto().getId()));
                    getGrupoUsuarioRemoto().insertar(grupoUsuario);
                } else {
                    bean.auditoriaModificar(grupoUsuario);
                    getGrupoUsuarioRemoto().actualizar(grupoUsuario);
                }
            }

            for (AusGrupoUsuario tuGrupoUsuario : bean.getRegistrosGrupoUsuariosParaBorrarDB()) {
                if (tuGrupoUsuario.getId() != null) {
                    getGrupoUsuarioRemoto().eliminar(tuGrupoUsuario.getId());
                }
            }

            bean.setRegistrosGrupoUsuariosParaBorrarDB(new ArrayList<>());

        } catch (Exception e) {
            bean.addError("Error al modificar grupo, por favor contacte administrador : " + e.getMessage());
        }
    }

    private void borrar(AusGrupoBean bean) {
        try {

            int idGrupo = bean.getObjeto().getId();
            
            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(idGrupo);
            List<AusGrupoUsuario> gruposUsuarios = getGrupoUsuarioRemoto().consultarListaPorGrupo(paramConsulta);
            for (AusGrupoUsuario gruposUsuario : gruposUsuarios) {
                getGrupoUsuarioRemoto().eliminar(gruposUsuario.getId());
            }

            bean.setObjeto(getGrupoRemoto().eliminar(bean.getObjeto().getId()));

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarUsuarioGrupo(AusGrupoBean bean) {
        try {
            getGrupoUsuarioRemoto().eliminar(bean.getObjetoGrupoUsuario().getId());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(AusGrupoBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_VER:

                    break;
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private HashMap<Integer, Maestro> convertToHash(List<Maestro> list) {
        HashMap<Integer, Maestro> map = new HashMap<>();
        for (Maestro i : list) {
            map.put(i.getId(), i);
        }
        return map;
    }

    @Override
    public void cargaInial(AusGrupoBean bean) {
        try {
            
        } catch (Exception ex) {
        }
    }
}
