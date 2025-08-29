package com.saviasaludeps.savia.web.tutela.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuGrupo;
import com.saviasaludeps.savia.dominio.tutela.TuGrupoEstado;
import com.saviasaludeps.savia.dominio.tutela.TuGrupoHistorico;
import com.saviasaludeps.savia.dominio.tutela.TuGrupoUsuario;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuGrupoEstadoRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuGrupoHistoricoRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuGrupoRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuGrupoUsuarioRemoto;
import com.saviasaludeps.savia.web.tutela.bean.TuGrupoBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TuGrupoServicioImpl extends AccionesBO implements TuGrupoServicioIface {

    private TuGrupoRemoto getGrupoRemoto() throws Exception {
        return (TuGrupoRemoto) RemotoEJB.getEJBRemoto("TuGrupoServicio", TuGrupoRemoto.class.getName());
    }

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    private TuGrupoUsuarioRemoto getGrupoUsuarioRemoto() throws Exception {
        return (TuGrupoUsuarioRemoto) RemotoEJB.getEJBRemoto("TuGrupoUsuarioServicio", TuGrupoUsuarioRemoto.class.getName());
    }

    private TuGrupoEstadoRemoto getGrupoEstadoRemoto() throws Exception {
        return (TuGrupoEstadoRemoto) RemotoEJB.getEJBRemoto("TuGrupoEstadoServicio", TuGrupoEstadoRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
        return (MaestroRemoto) object;
    }

    private TuGrupoHistoricoRemoto geGrupoHistoricoRemoto() throws Exception {
        return (TuGrupoHistoricoRemoto) RemotoEJB.getEJBRemoto("TuGrupoHistoricoServicio", TuGrupoHistoricoRemoto.class.getName());
    }

    @Override
    public void Accion(TuGrupoBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    switch (bean.getDoAccion()) {
                        case TuGrupoBean.ACCION_LISTAR_GRUPOS:
                            listar(bean);
                            break;
                        case TuGrupoBean.ACCION_LISTAR_USUARIOS:
                            listarUsuarios(bean);
                            break;
                        case TuGrupoBean.ACCION_LISTAR_GRUPO_USUARIOS:
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
                        case TuGrupoBean.ACCION_CAMBIAR_ORDER:
                            modificarOrder(bean);
                            break;
                        case TuGrupoBean.ACCION_MODIFICAR_GRUPO:
                            modificar(bean);
                            break;
                    }
                    break;
                case Url.ACCION_BORRAR:
                    switch (bean.getDoAccion()) {
                        case TuGrupoBean.ACCION_BORRAR_GRUPO:
                            borrar(bean);
                            break;
                        case TuGrupoBean.ACCION_BORRAR_USUARIO:
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

    private void listar(TuGrupoBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getGrupoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getGrupoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());

        }
    }

    private void listarUsuarios(TuGrupoBean bean) {
        try {
            bean.getParamConsultaUsuarios().setEmpresaId(bean.getConexion().getEmpresa().getId());
            bean.getParamConsultaUsuarios().setCantidadRegistros(getUsuarioRemoto().consultarCantidadLista(bean.getParamConsultaUsuarios()));
            bean.setRegistrosUsuarios(getUsuarioRemoto().consultarLista(bean.getParamConsultaUsuarios()));
        } catch (Exception e) {
            bean.addError("Error al listar usuarios auditores, favor comunicarse con el administrador : " + e.getMessage());
        }
    }

    private void listarGrupoUsuarios(TuGrupoBean bean) {
        try {
            bean.getParamConsultaGrupoUsuarios().setEmpresaId(bean.getConexion().getEmpresa().getId());
            bean.getParamConsultaGrupoUsuarios().setCantidadRegistros(getGrupoUsuarioRemoto().consultarCantidadListaPorGrupo(bean.getParamConsultaGrupoUsuarios()));
            bean.getParamConsultaGrupoUsuarios().setRegistrosPagina(bean.getParamConsultaGrupoUsuarios().getCantidadRegistros() + 1);
            bean.setRegistrosGrupoUsuarios(getGrupoUsuarioRemoto().consultarListaPorGrupo(bean.getParamConsultaGrupoUsuarios()));
        } catch (Exception e) {
            bean.addError("Error al listar grupo de usuarios, favor comunicarse con el administrador : " + e.getMessage());
        }
    }

    private void ver(TuGrupoBean bean) {
        try {
            bean.setObjeto(getGrupoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Error al visualizar un grupo:" + e.getMessage());
        }
    }

    private void crear(TuGrupoBean bean) {
        try {
            bean.getObjeto().setOrden(1);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(TuGrupoBean bean) {
        try {
            bean.setObjeto(getGrupoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Error al editar un grupo: " + e.getMessage());
        }
    }

    private void guardar(TuGrupoBean bean) {
        try {

            TuGrupoHistorico grupoHistorico = bean.getObjeto().getGrupoHistorico();

            bean.auditoriaGuardar(bean.getObjeto());
            asignarOrdenSiNoExiste(bean);
            bean.getObjeto().setId(getGrupoRemoto().insertar(bean.getObjeto()));
            boolean esRepartoAutomatico = bean.getObjeto().getGrupoEstado().isReparto();

            if (bean.getObjeto().getId() != null) {

                for (Integer IdEstado : bean.getObjeto().getGrupoEstado().getSelectedEstados()) {
                    TuGrupoEstado estado = new TuGrupoEstado();
                    estado.setMaeEstadoId(IdEstado);
                    estado.setTuGrupo(new TuGrupo(bean.getObjeto().getId()));
                    formatoMaestroEstadoGrupo(bean, estado);
                    estado.setReparto(esRepartoAutomatico);
                    bean.auditoriaGuardar(estado);
                    getGrupoEstadoRemoto().insertar(estado);
                }

                for (TuGrupoUsuario grupoUsuario : bean.getRegistrosGrupoUsuarios()) {
                    bean.auditoriaGuardar(grupoUsuario);
                    grupoUsuario.setTuGrupo(new TuGrupo(bean.getObjeto().getId()));
                    getGrupoUsuarioRemoto().insertar(grupoUsuario);
                }

                StringBuilder builderInsercion = new StringBuilder();
                for (TuGrupoUsuario registrosGrupoUsuario : bean.getRegistrosGrupoUsuarios()) {
                    builderInsercion.append(registrosGrupoUsuario.toString());
                    builderInsercion.append(",");
                }
                grupoHistorico.setTuGrupo(new TuGrupo(bean.getObjeto().getId()));
                bean.getObjeto().setGrupoUsuarios(bean.getRegistrosGrupoUsuarios());
//                grupoHistorico.setToString("Insercion: "+bean.getObjeto().toString()+" usuarios: "+ builderInsercion);
                grupoHistorico.setToString("Insercion: " + ((bean.getObjeto().toString().length() > 8192) ? bean.getObjeto().toString().substring(0, 8191) : bean.getObjeto().toString()));
                bean.auditoriaGuardar(grupoHistorico);
                geGrupoHistoricoRemoto().insertar(grupoHistorico);
            }
        } catch (Exception e) {
            bean.addError("Error al guardar grupo, por favor contacte administrador : " + e.getMessage());
        }
    }

    private void modificar(TuGrupoBean bean) {
        try {
            TuGrupoEstado tuGrupoEstado = bean.getObjeto().getGrupoEstado();
            TuGrupoHistorico grupoHistorico = bean.getObjeto().getGrupoHistorico();
            asignarOrdenSiNoExiste(bean);
            bean.auditoriaModificar(bean.getObjeto());
            getGrupoRemoto().actualizar(bean.getObjeto());
            List<Integer> idsGrupoEstadoNoTenidoEncuenta = new ArrayList<>();
            List<Integer> idMaeGrupoEstadoExistente = new ArrayList<>();
            List<Integer> idsGrupoEstadoExistente = new ArrayList<>();
            boolean esRepartoAutomatico = tuGrupoEstado.isReparto();

            for (TuGrupoEstado estado : bean.getObjeto().getGrupoEstados()) {
                if (tuGrupoEstado.getSelectedEstados().contains(estado.getMaeEstadoId())) {
                    idMaeGrupoEstadoExistente.add(estado.getMaeEstadoId());
                    idsGrupoEstadoExistente.add(estado.getId());
                } else {
                    idsGrupoEstadoNoTenidoEncuenta.add(estado.getId());
                }
            }
            for (Integer idMaeEstado : tuGrupoEstado.getSelectedEstados()) {
                TuGrupoEstado estadoNuevo = new TuGrupoEstado();
                estadoNuevo.setMaeEstadoId(idMaeEstado);
                if (!idMaeGrupoEstadoExistente.contains(idMaeEstado)) {
                    bean.auditoriaGuardar(estadoNuevo);
                    estadoNuevo.setTuGrupo(new TuGrupo(bean.getObjeto().getId()));
                    estadoNuevo.setReparto(esRepartoAutomatico);
                    formatoMaestroEstadoGrupo(bean, estadoNuevo);
                    getGrupoEstadoRemoto().insertar(estadoNuevo);
                }
            }
            for (Integer idGrupoEstado : idsGrupoEstadoNoTenidoEncuenta) {
                getGrupoEstadoRemoto().eliminar(idGrupoEstado);
            }

            for (Integer idGrupoEstado : idsGrupoEstadoExistente) {
                TuGrupoEstado grupoEstado = getGrupoEstadoRemoto().consultar(idGrupoEstado);
                bean.auditoriaModificar(grupoEstado);
                grupoEstado.setReparto(esRepartoAutomatico);
                getGrupoEstadoRemoto().actualizar(grupoEstado);
            }

            for (TuGrupoUsuario grupoUsuario : bean.getRegistrosGrupoUsuarios()) {
                if (grupoUsuario.getId() == null) {
                    bean.auditoriaGuardar(grupoUsuario);
                    grupoUsuario.setTuGrupo(new TuGrupo(bean.getObjeto().getId()));
                    getGrupoUsuarioRemoto().insertar(grupoUsuario);
                } else {
                    bean.auditoriaModificar(grupoUsuario);
                    getGrupoUsuarioRemoto().actualizar(grupoUsuario);
                }
            }

            for (TuGrupoUsuario tuGrupoUsuario : bean.getRegistrosGrupoUsuariosParaBorrarDB()) {
                if (tuGrupoUsuario.getId() != null) {
                    getGrupoUsuarioRemoto().eliminar(tuGrupoUsuario.getId());
                }
            }

//             StringBuilder builderActualizacion = new StringBuilder();
//             for (TuGrupoUsuario registrosGrupoUsuario : bean.getRegistrosGrupoUsuarios()) {
//                 builderActualizacion.append(registrosGrupoUsuario.toString());
//                 builderActualizacion.append(",");
//             }
            grupoHistorico.setTuGrupo(new TuGrupo(bean.getObjeto().getId()));
            bean.getObjeto().setGrupoUsuarios(bean.getRegistrosGrupoUsuarios());
            grupoHistorico.setToString("Modificacion: " + ((bean.getObjeto().toString().length() >= 8178) ? bean.getObjeto().toString().substring(0, 8177) : bean.getObjeto().toString()));
//            grupoHistorico.setToString("Modificacion: "+bean.getObjeto().toString()+" usuarios : "+builderActualizacion.toString());
            bean.auditoriaGuardar(grupoHistorico);
            geGrupoHistoricoRemoto().insertar(grupoHistorico);

            bean.setRegistrosGrupoUsuariosParaBorrarDB(new ArrayList<>());

        } catch (Exception e) {
            bean.addError("Error al modificar grupo, por favor contacte administrador : " + e.getMessage());
        }
    }

    private void modificarOrder(TuGrupoBean bean) {
        try {
            getGrupoRemoto().actualizarOrden(bean.getObjeto());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void asignarOrdenSiNoExiste(TuGrupoBean bean) {
        try {
            if (bean.getObjeto().getOrden() <= 0) {
                ParamConsulta paramConsulta = new ParamConsulta();
                int nuevoOrden = getGrupoRemoto().consultarCantidadLista(paramConsulta) + 1;
                bean.getObjeto().setOrden(nuevoOrden);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(TuGrupoBean bean) {
        try {

            int idGrupo = bean.getObjeto().getId();
            List<TuGrupoHistorico> grupoHistoricos = geGrupoHistoricoRemoto().consultarListaPorIdGrupo(idGrupo);
            for (TuGrupoHistorico grupoHistorico : grupoHistoricos) {
                geGrupoHistoricoRemoto().eliminar(grupoHistorico.getId());
            }

            List<TuGrupoEstado> gruposEstados = getGrupoEstadoRemoto().consultarListaPorIdGrupo(idGrupo);
            for (TuGrupoEstado gruposEstado : gruposEstados) {
                getGrupoEstadoRemoto().eliminar(gruposEstado.getId());
            }

            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(idGrupo);
            List<TuGrupoUsuario> gruposUsuarios = getGrupoUsuarioRemoto().consultarListaPorGrupo(paramConsulta);
            for (TuGrupoUsuario gruposUsuario : gruposUsuarios) {
                getGrupoUsuarioRemoto().eliminar(gruposUsuario.getId());
            }

            bean.setObjeto(getGrupoRemoto().eliminar(bean.getObjeto().getId()));

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarUsuarioGrupo(TuGrupoBean bean) {
        try {
            getGrupoUsuarioRemoto().eliminar(bean.getObjetoGrupoUsuario().getId());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(TuGrupoBean bean) {
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

    private void formatoMaestroEstadoGrupo(TuGrupoBean bean, TuGrupoEstado grupoEstado) {
        try {
            for (Map.Entry<Integer, Maestro> mae : bean.getHashGrupoEstados().entrySet()) {
                if (mae.getValue().getId().equals(grupoEstado.getMaeEstadoId())) {
                    grupoEstado.setMaeEstadoId(mae.getValue().getId());
                    grupoEstado.setMaeEstadoCodigo(mae.getValue().getValor());
                    grupoEstado.setMaeEstadoValor(mae.getValue().getNombre());
                    break;
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void cargaInial(TuGrupoBean bean) {
        try {
            bean.setListaGrupoEstados(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_TUTELA_ESTADO));
            bean.setHashGrupoEstados(convertToHash(bean.getListaGrupoEstados()));
        } catch (Exception ex) {
        }
    }
}
