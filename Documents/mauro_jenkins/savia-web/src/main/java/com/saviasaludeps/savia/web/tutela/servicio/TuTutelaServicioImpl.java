package com.saviasaludeps.savia.web.tutela.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuAdjunto;
import com.saviasaludeps.savia.dominio.tutela.TuDiagnostico;
import com.saviasaludeps.savia.dominio.tutela.TuGrupo;
import com.saviasaludeps.savia.dominio.tutela.TuGrupoUsuario;
import com.saviasaludeps.savia.dominio.tutela.TuGrupoUsuariosAutomatico;
import com.saviasaludeps.savia.dominio.tutela.TuJuzgado;
import com.saviasaludeps.savia.dominio.tutela.TuPersona;
import com.saviasaludeps.savia.dominio.tutela.TuPersonaContacto;
import com.saviasaludeps.savia.dominio.tutela.TuRepresentante;
import com.saviasaludeps.savia.dominio.tutela.TuSeguimiento;
import com.saviasaludeps.savia.dominio.tutela.TuTutela;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaEstado;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaEstadoRepresentante;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaItem;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaItemGestion;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaRespuesta;
import com.saviasaludeps.savia.negocio.administracion.CalendarioRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4Remoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaAutorizacionRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuDiagnosticoRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuEstadoRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuGrupoRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuGrupoUsuarioRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuItemRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuJuzgadoRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuMemorialFirmaRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuMemorialPersonaRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuPersonaContactoRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuPersonaRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuRepresentanteRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuSeguimientoRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuTutelaEstadoRepresentanteRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuTutelaItemGestionRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuTutelaRemoto;
import com.saviasaludeps.savia.web.atencionusuario.servicio.AusCasoServicioImpl;
import com.saviasaludeps.savia.web.atencionusuario.servicio.AusPersonaServicioImpl;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.tutela.bean.TuTutelaBean;
import com.saviasaludeps.savia.web.tutela.utilidades.PropTutelasUsuario;
import com.saviasaludeps.savia.web.tutela.utilidades.TuTulelaUtilidades;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TuTutelaServicioImpl extends AccionesBO implements TuTutelaServicioIface {

    private TuTutelaRemoto getTutelaRemoto() throws Exception {
        return (TuTutelaRemoto) RemotoEJB.getEJBRemoto("TuTutelaServicio", TuTutelaRemoto.class.getName());
    }

    private TuJuzgadoRemoto getTuJuzgadoRemoto() throws Exception {
        return (TuJuzgadoRemoto) RemotoEJB.getEJBRemoto("TuJuzgadoServicio", TuJuzgadoRemoto.class.getName());
    }

    private TuEstadoRemoto getTuEstadoRemoto() throws Exception {
        return (TuEstadoRemoto) RemotoEJB.getEJBRemoto("TuEstadoTutelaServicio", TuEstadoRemoto.class.getName());
    }

    private TuPersonaRemoto getTuPersonaRemoto() throws Exception {
        return (TuPersonaRemoto) RemotoEJB.getEJBRemoto("TuPersonaServicio", TuPersonaRemoto.class.getName());
    }

    private TuSeguimientoRemoto getTuSeguimientoRemoto() throws Exception {
        return (TuSeguimientoRemoto) RemotoEJB.getEJBRemoto("TuSeguimientoServicio", TuSeguimientoRemoto.class.getName());
    }

    private TuItemRemoto getTuItemRemoto() throws Exception {
        return (TuItemRemoto) RemotoEJB.getEJBRemoto("TuTutelaItemServicio", TuItemRemoto.class.getName());
    }

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
        return (MaestroRemoto) object;
    }

    private TuGrupoRemoto getTuGrupoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("TuGrupoServicio", TuGrupoRemoto.class.getName());
        return (TuGrupoRemoto) object;
    }

    private TuGrupoUsuarioRemoto getTuGrupoUsuarioRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("TuGrupoUsuarioServicio", TuGrupoUsuarioRemoto.class.getName());
        return (TuGrupoUsuarioRemoto) object;
    }

    private TuAdjuntoRemoto getTuAdjuntoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("TuAdjuntoServicio", TuAdjuntoRemoto.class.getName());
        return (TuAdjuntoRemoto) object;
    }

    private CntPrestadorRemoto getCntPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private TuRepresentanteRemoto getTuRepresentanteRemoto() throws Exception {
        return (TuRepresentanteRemoto) RemotoEJB.getEJBRemoto("TuRepresentanteServicio", TuRepresentanteRemoto.class.getName());
    }

    private CalendarioRemoto getCalendarioRemoto() throws Exception {
        return (CalendarioRemoto) RemotoEJB.getEJBRemoto("CalendarioServicio", CalendarioRemoto.class.getName());
    }

    private TuTutelaEstadoRepresentanteRemoto getTuTutelaEstadoRepresentanteRemoto() throws Exception {
        return (TuTutelaEstadoRepresentanteRemoto) RemotoEJB.getEJBRemoto("TuTutelaEstadoRepresentanteServicio", TuTutelaEstadoRepresentanteRemoto.class.getName());
    }

    private TuDiagnosticoRemoto getTuDiagnosticoRemoto() throws Exception {
        return (TuDiagnosticoRemoto) RemotoEJB.getEJBRemoto("TuDiagnosticoServicio", TuDiagnosticoRemoto.class.getName());
    }

    private AuAnexo4Remoto getAuAnexo4Remoto() throws Exception {
        return (AuAnexo4Remoto) RemotoEJB.getEJBRemoto(("AuAnexo4Servicio"), AuAnexo4Remoto.class.getName());
    }

    private TuPersonaContactoRemoto getTuPersonaContactoRemoto() throws Exception {
        return (TuPersonaContactoRemoto) RemotoEJB.getEJBRemoto(("TuPersonaContactoServicio"), TuPersonaContactoRemoto.class.getName());
    }

    private CmAuditoriaAutorizacionRemoto getCmAuditoriaAutorizacionRemoto() throws Exception {
        return (CmAuditoriaAutorizacionRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaAutorizacionServicio", CmAuditoriaAutorizacionRemoto.class.getName());
    }
    
    private TuMemorialPersonaRemoto getTuMemorialPersonaRemoto() throws Exception {
        return (TuMemorialPersonaRemoto) RemotoEJB.getEJBRemoto("TuMemorialPersonaServicio", TuMemorialPersonaRemoto.class.getName());
    }
    
    private TuMemorialFirmaRemoto getTuMemorialFirmaRemoto() throws Exception {
        return (TuMemorialFirmaRemoto) RemotoEJB.getEJBRemoto("TuMemorialFirmaServicio", TuMemorialFirmaRemoto.class.getName());
    }
    
    private TuTutelaItemGestionRemoto getTuTutelaItemGestionRemoto() throws Exception {
        return (TuTutelaItemGestionRemoto) RemotoEJB.getEJBRemoto("TuTutelaItemGestionServicio", TuTutelaItemGestionRemoto.class.getName());
    }
    
    @Override
    public void Accion(TuTutelaBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listar(bean);
                            break;
                        case TuTutelaBean.ACCION_LISTAR_ESTADOS:
                            listarEstados(bean);
                            break;
                        case TuTutelaBean.ACCION_BUSCAR_SEDES:
                            listarSedesPrescriptora(bean);
                            break;
                        case TuTutelaBean.ACCION_LISTAR_ANEXOS_4:
                            listarAnexo4(bean);
                            break;
                        case TuTutelaBean.ACCION_LISTAR_HISTORIAL_PERSONA:
                            consultarHistorialPersona(bean);
                            break;
                        case TuTutelaBean.ACCION_LISTAR_CONTACTOS_PERSONAS:
                            listarTuPersonaContactos(bean);
                            break;
                        case TuTutelaBean.ACCION_LISTAR_SEGUIMIENTO:
                            listarSeguimientos(bean);
                            break;
                    }
                    break;
                case Url.ACCION_VER:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            ver(bean);
                            listarTuPersonaContactos(bean);
                            break;
                        case TuTutelaBean.VER_ESTADOS:
                            consultarEstado(bean);
                            break;
                        case TuTutelaBean.VER_ITEMS:
                            consultarItem(bean);
                            break;
                        case TuTutelaBean.VER_SEGUIMIENTO:
                            consultarSeguimiento(bean);
                            break;
                        case TuTutelaBean.ACCION_VER_ENVIOS_SEGUIMIENTO:
                            consultarlistarTuGrupoUsuarioEnviar(bean);
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_GUARDAR:
                            guardar(bean);
                            break;
                    }
                    break;
                case Url.ACCION_EDITAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            editar(bean);
                            listarTuPersonaContactos(bean);
                            break;
                        case TuTutelaBean.EDITAR_TUTELA_ESTADO:
                            editarEstado(bean);
                            break;
                        case TuTutelaBean.EDITAR_TUTELA_ESTADOS_ITEM:
                            editarItem(bean);
                            break;
                        case TuTutelaBean.EDITAR_TUTELA_ESTADOS_SEGUIMIENTO:
                            editarSeguimiento(bean);
                            break;
                    }
                    break;
                case Url.ACCION_MODIFICAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_MODIFICAR:
                            modificar(bean);
                            guardarTuPersonaContactos(bean);
                            break;
                        case TuTutelaBean.MODIFICAR_TUTELA_ESTADO:
                            modificarEstado(bean);
                            break;
                        case TuTutelaBean.MODIFICAR_TUTELA_ESTADO_ITEM:
                            modificarItem(bean);
                            break;
                        case TuTutelaBean.MODIFICAR_TUTELA_ESTADO_ITEM_DIAGNOSTICO:
                            modificarDiagnostico(bean);
                            break;
                        case TuTutelaBean.MODIFICAR_TUTELA_ESTADO_SEGUIMIENTO:
                            modificarSeguimiento(bean);
                            break;

                    }
                    break;
                case Url.ACCION_BORRAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_BORRAR:
                            borrar(bean);
                            break;
                        case TuTutelaBean.BORRAR_ESTADO:
                            borrarEstado(bean);
                            break;
                        case TuTutelaBean.BORRAR_ITEM:
                            borrarItem(bean);
                            break;
                        case TuTutelaBean.BORRAR_SEGUIMIENTO:
                            borrarSeguimiento(bean);
                            break;
                        case TuTutelaBean.ACCION_BORRAR_CONTACTOS_PERSONAS:
                            borrarContactoPersona(bean);
                            break;
                        case TuTutelaBean.BORRAR_DIAGNOSTICO:
                            borrarDiagnostico(bean);
                            break;
                        case TuTutelaBean.BORRAR_ADJUNTO:
                            borrarAdjunto(bean);
                            break;
                        case TuTutelaBean.BORRAR_AUTORIZACION:
                            borrarAutorizacion(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case TuTutelaBean.ACCION_EDITAR_GESTION:
                            ver(bean);
                            listarTuPersonaContactos(bean);
                            break;
                        case TuTutelaBean.EDITAR_TUTELA_ESTADOS_ITEM:
                            editarItem(bean);
                            break;
                        case TuTutelaBean.ACCION_ADICIONAL_1:
                            modificarItem(bean);
                            break;
                        case TuTutelaBean.MODIFICAR_TUTELA_ESTADO_ITEM_DIAGNOSTICO:
                            modificarDiagnostico(bean);
                            break;
                        case TuTutelaBean.ACCION_MODIFICAR_GESTION:
                            modificarGestion(bean);
                            guardarTuPersonaContactos(bean);
                            break;
                        case TuTutelaBean.ACCION_LISTAR_ANEXOS4_ITEMS:
                            listarAnexo4Items(bean);
                            break;
                        case TuTutelaBean.BORRAR_ADJUNTO:
                            borrarAdjunto(bean);
                            break;
                        case TuTutelaBean.BORRAR_AUTORIZACION:
                            borrarAutorizacion(bean);
                            break;
                        case TuTutelaBean.DESCARGAR_DOCUMENTO_MEMORIAL:
                            consultarMaestroDocumentoMemorial(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case TuTutelaBean.VER_ESTADOS:
                            consultarEstado(bean);
                            break;
                        case TuTutelaBean.ACCION_GUARDAR_ESTADO_ITEM:
                            guardarEstadoItem(bean);
                            break;
                        case TuTutelaBean.ACCION_GUARDAR_ESTADO_SEGUIMIENTO:
                            guardarEstadoSeguimiento(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    guardarEstadoNuevo(bean);
                    break;
                case Url.ACCION_ADICIONAL_5:
                    switch (bean.getDoAccion()) {
                        case TuTutelaBean.ACCION_EDITAR_RESPONSABLE:
                            editar(bean);
                            listarUsuarioGrupo(bean);
                            break;
                        case TuTutelaBean.ACCION_MODIFICAR_RESPONSABLE_ESTADO_ACTUAL:
                            modificarResponsable(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_6:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_6:
                            break;
                        case TuTutelaBean.ACCION_LISTAR_SEGUIMIENTO:
                            listarGestionSeguimientos(bean);
                            break;
                        case TuTutelaBean.VER_SEGUIMIENTO:
                            consultarSeguimiento(bean);
                            break;
                    }
                    break;
                default:
                    break;
            }

//            cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(TuTutelaBean bean) {
        try {
            if (bean.getParamConsulta().getFiltros() == null) {
                bean.getParamConsulta().setFiltros(new HashMap());
            }
            if (!bean.isAccionAdicional4()) {
                if (bean.getParamConsulta().getFiltros().get("id") == null
                        && bean.getParamConsulta().getFiltros().get("radicadoNumero") == null
                        && bean.getParamConsulta().getFiltros().get("tuPersona.numeroDocumento") == null
                        && bean.getParamConsulta().getFiltros().get("actualTuTutelaEstadoId.responsableGnUsuarioId.nombre") == null) {
                    bean.getParamConsulta().setParametroConsulta1(bean.getConexion().getUsuario().getId());
                } else {
                    bean.getParamConsulta().setParametroConsulta1(null);
                }
            }
            bean.getParamConsulta().getFiltros().put("borradoTutela", "1");
            bean.getParamConsulta().setCantidadRegistros(getTutelaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getTutelaRemoto().consultarLista(bean.getParamConsulta()));
            //bean.setObjPersona(getTuPersonaRemoto().consultar(bean.getObjeto().getTuPersonaId()));
//            bean.setObjJuzgado(getTuJuzgadoRemoto().consultar(bean.getObjEstados().getTuJuzgadoId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());

        }
    }

    private void listarEstados(TuTutelaBean bean) {
        try {
            if (bean.getParamConsulta(0).getFiltros() == null) {
                bean.getParamConsulta(0).setFiltros(new HashMap());
            }
            bean.setObjeto(getTutelaRemoto().consultar(bean.getObjeto().getId()));
            /*bean.getParamConsultaServiciosEstado().getFiltros().put("tuTutelasId.id", bean.getObjeto().getId());
            bean.getParamConsultaServiciosEstado().getFiltros().put("borrado", "0");
            bean.getParamConsultaServiciosEstado().setCantidadRegistros(getTuEstadoRemoto().consultarCantidadLista(bean.getParamConsultaServiciosEstado()));*/
            bean.getParamConsulta(0).getFiltros().put("tuTutelasId.id", bean.getObjeto().getId());
            bean.getParamConsulta(0).getFiltros().put("borrado", "0");
            bean.getParamConsulta(0).setCantidadRegistros(getTuEstadoRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistrosEstados(getTuEstadoRemoto().consultarLista(bean.getParamConsulta(0)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarSeguimientos(TuTutelaBean bean) {
        try {
            if (bean.getParamConsulta(8).getFiltros() == null) {
                bean.getParamConsulta(8).setFiltros(new HashMap());
            }
            bean.getParamConsulta(8).getFiltros().put("tuTutelaEstadosId.id", bean.getObjEstados().getId());
            bean.getParamConsulta(8).setCantidadRegistros(getTuSeguimientoRemoto().consultarCantidadLista(bean.getParamConsulta(8)));
            bean.setRegistroSeguimiento(getTuSeguimientoRemoto().consultarLista(bean.getParamConsulta(8)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarGestionSeguimientos(TuTutelaBean bean) {
        try {
            if (bean.getParamConsulta(9).getFiltros() == null) {
                bean.getParamConsulta(9).setFiltros(new HashMap());
            }
            bean.getParamConsulta(9).getFiltros().put("tuTutelaEstadosId.tuTutelasId.id", bean.getObjeto().getId());
            bean.getParamConsulta(9).setCantidadRegistros(getTuSeguimientoRemoto().consultarCantidadLista(bean.getParamConsulta(9)));
            bean.setRegistroSeguimiento(getTuSeguimientoRemoto().consultarLista(bean.getParamConsulta(9)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(TuTutelaBean bean) {
        try {
            //bean.setObjeto(getTutelaRemoto().consultar(bean.getObjeto().getId()));
            //bean.getObjeto().setListaTuTutelaEstado(getTuEstadoRemoto().consultarEstadosPorTutela(bean.getObjeto().getId()));
            /*if (bean.getParamConsultaServiciosEstado().getFiltros() == null) {
                bean.getParamConsultaServiciosEstado().setFiltros(new HashMap());
            }*/
            if (bean.getParamConsulta(0).getFiltros() == null) {
                bean.getParamConsulta(0).setFiltros(new HashMap());
            }
            /*bean.setObjeto(getTutelaRemoto().consultar(bean.getObjeto().getId()));
            bean.getParamConsultaServiciosEstado().getFiltros().put("tuTutelasId.id", bean.getObjeto().getId());
            bean.getParamConsultaServiciosEstado().setCantidadRegistros(getTuEstadoRemoto().consultarCantidadLista(bean.getParamConsultaServiciosEstado()));*/
            bean.setObjeto(getTutelaRemoto().consultar(bean.getObjeto().getId()));
            bean.getParamConsulta(0).getFiltros().put("tuTutelasId.id", bean.getObjeto().getId());
            bean.getParamConsulta(0).setCantidadRegistros(getTuEstadoRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistrosEstados(getTuEstadoRemoto().consultarLista(bean.getParamConsulta(0)));
            bean.getTuPersonaContacto().setTuPersona(new TuPersona(bean.getObjeto().getTuPersona().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(TuTutelaBean bean) {
        try {
            bean.setObjeto(new TuTutela());
            bean.setListaJuzgados(new ArrayList<>());
            bean.setHashJuzgados(new HashMap<>());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(TuTutelaBean bean) {
        try {
            TuTutela tuTutela = getTutelaRemoto().consultar(bean.getObjeto().getId());
            bean.setObjeto(tuTutela);
            bean.getTuPersonaContacto().setTuPersona(new TuPersona(bean.getObjeto().getTuPersona().getId()));
            bean.setModicarResponsable(bean.getObjeto().getActualTuTutelaEstadoId());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void editarEstado(TuTutelaBean bean) {
        try {
            TuTutelaEstado tutelaEstado = getTuEstadoRemoto().consultar(bean.getObjEstados().getId());
            List<TuTutelaEstadoRepresentante> listaTuTutelaEstadoRepresentante = getTuTutelaEstadoRepresentanteRemoto().consultarRespresentantesPorEstadoTutela(tutelaEstado.getId());
            if (!listaTuTutelaEstadoRepresentante.isEmpty()) {
                Integer[] ids = new Integer[listaTuTutelaEstadoRepresentante.size()];
                for (int i = 0; i < listaTuTutelaEstadoRepresentante.size(); i++) {
                    ids[i] = listaTuTutelaEstadoRepresentante.get(i).getTuRepresentantesId().getId();
                }
                tutelaEstado.setListaFiltrosRepresentanteDemandado(ids);
            }
            bean.setObjEstados(tutelaEstado);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void editarItem(TuTutelaBean bean) {
        try {
            TuTutelaItem tutelaItem = getTuItemRemoto().consultar(bean.getObjTutulaItem().getId());
            if (tutelaItem.getConsultarAutorizacion() != null) {
                AuAnexo4 auAnexo4 = getAuAnexo4Remoto().consultar(tutelaItem.getConsultarAutorizacion());
                if (auAnexo4 != null) {
                    List<AuAnexo4> listaAutoriacion = new ArrayList<>();
                    listaAutoriacion.add(auAnexo4);
                    tutelaItem.setRegistrosAuditoriaAutorizacion(listaAutoriacion);
                }

            }
            bean.setObjTutulaItem(tutelaItem);
            //2025-07-01 jyperez cargamos lista usuarios
            this.listarUsuariosPorRol(bean);
            //2025-07-15 jyperez consultamos la gestión correspondiente al ítem
            bean.getObjTutulaItem().setTuTutelaItemGestionesList(getTuTutelaItemGestionRemoto().consultarLista(bean.getObjTutulaItem().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void editarSeguimiento(TuTutelaBean bean) {
        try {
            TuSeguimiento tuSeguimiento = getTuSeguimientoRemoto().consultar(bean.getObjEstadosSeguimiento().getId());
            bean.setObjEstadosSeguimiento(tuSeguimiento);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(TuTutelaBean bean) {
        try {
            int idTutela = 0;
            TuTutela tuTutela = bean.getObjeto();
            TuPersona tuPersona = tuTutela.getTuPersona();
            bean.auditoriaGuardar(bean.getObjeto());
            Maestro sexo = bean.getHashSexo().get(tuPersona.getMaeGeneroId());
            Maestro tipoDocumento = bean.getHashTiposDocumento().get(tuPersona.getMaeTipoDocumentoId());
            if (tipoDocumento != null) {
                tuPersona.setMaeTipoDocumentoCodigo(tipoDocumento.getValor());
                tuPersona.setMaeTipoDocumentoValor(tipoDocumento.getNombre());
            }
            if (sexo != null) {
                tuPersona.setMaeGeneroCodigo(sexo.getValor());
                tuPersona.setMaeGeneroValor(sexo.getNombre());
            }
            if (tuPersona.getId() == null || tuPersona.getId() <= 0) {
                bean.auditoriaGuardar(tuPersona);
                int idPersona = getTuPersonaRemoto().insertar(tuPersona);
                tuPersona.setId(idPersona);
            } else {
                bean.auditoriaGuardar(tuPersona);
                tuPersona.setId(null);
                int idPersona = getTuPersonaRemoto().insertar(tuPersona);
                tuPersona.setId(idPersona);
            }
            if (tuPersona.getId() > 0) {
                idTutela = getTutelaRemoto().insertar(tuTutela);
                tuTutela.setId(idTutela);
                if (tuTutela.getId() > 0) {
                    insertarAdjuntos(tuTutela.getAdjuntosList(), bean, idTutela, 0, 0, 0);
                    guardarTutelaEstadoPorDefecto(bean, tuTutela);
                    if (!bean.isError()) {
                        bean.auditoriaModificar(tuTutela);
                        getTutelaRemoto().actualizar(tuTutela);
                    }
                    guardarTuPersonaContactos(bean);
                    //guardarTutelaTutelaEstadoAdicional(bean, tuTutela);
                    //guardarTutelaSeguimientos(bean, tuTutela);
                }
            }
            if (!bean.isError()) {
                bean.addMensaje("Se creo la tutela con id (" + idTutela + ") de manera exitosa \n ");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarEstadoNuevo(TuTutelaBean bean) {
        try {
            TuTutela tuTutela = bean.getObjeto();
            bean.auditoriaGuardar(bean.getObjeto());
            guardarTutelaEstadoNuevo(bean, tuTutela);
            if (!bean.isError()) {
                bean.addMensaje("Se creo la estado con id (" + bean.getObjEstados().getId() + ") de manera exitosa \n ");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarEstadoItem(TuTutelaBean bean) {
        int contItemCerrados = 0;
        int contItems = 0;
        try {
            TuTutelaEstado tuTutelaEstado = bean.getObjEstados();
            bean.auditoriaGuardar(bean.getObjeto());
            contItemCerrados = procesarItem(tuTutelaEstado.getListaTuTutelaItem(), tuTutelaEstado, bean);
            //2025-07-22 jyperez actualizamos los items de los estados
            //2025-07-17 jyperez actualizamos el valor del estado
            contItems = tuTutelaEstado.getListaTuTutelaItem().size();
            tuTutelaEstado.setCantidadItems(contItems);
            tuTutelaEstado.setCantidadItemsCerrados(contItemCerrados);
            getTuEstadoRemoto().actualizar(tuTutelaEstado);
            //actualización de tutela
            bean.getObjeto().setCantidadItems(getTuEstadoRemoto().contarItemsTutela(bean.getObjeto().getId()));
            bean.getObjeto().setCantidadItemsCerrados(getTuEstadoRemoto().contarItemsCerradosTutela(bean.getObjeto().getId()));
            //acá no se está actualizando la información de la tutela, entonces actualizamos solo los campos
            //getTutelaRemoto().actualizar(bean.getObjeto());
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarEstadoSeguimiento(TuTutelaBean bean) {
        try {
            TuTutelaEstado tuTutelaEstado = bean.getObjEstados();
            bean.auditoriaGuardar(bean.getObjeto());
            procesarSeguimiento(tuTutelaEstado.getTuSeguimientosList(), tuTutelaEstado, bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(TuTutelaBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            TuPersona persona = bean.getObjeto().getTuPersona();
            bean.auditoriaModificar(persona);
            Maestro tipoDocumento = bean.getHashTiposDocumento().get(persona.getMaeTipoDocumentoId());
            if (tipoDocumento != null) {
                persona.setMaeTipoDocumentoCodigo(tipoDocumento.getValor());
                persona.setMaeTipoDocumentoValor(tipoDocumento.getNombre());
            }
            Maestro sexo = bean.getHashSexo().get(persona.getMaeGeneroId());
            if (sexo != null) {
                persona.setMaeGeneroCodigo(sexo.getValor());
                persona.setMaeGeneroValor(sexo.getNombre());
            }
            Maestro estado = bean.getHashTipoEstadoPersona().get(persona.getMaeEstadoAfiliadoId());
            if (estado != null) {
                persona.setMaeEstadoAfiliadoCodigo(estado.getValor());
                persona.setMaeEstadoAfiliadoValor(estado.getNombre());
            }
            getTuPersonaRemoto().actualizar(persona);
            getTutelaRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void modificarGestion(TuTutelaBean bean) {
        try {
            TuTutela tuTutela = bean.getObjeto();
            bean.auditoriaModificar(tuTutela);
            //getTutelaRemoto().actualizar(tuTutela);
            
            List<TuTutelaEstado> listaServicios = procesarEstados(tuTutela.getListaTuTutelaEstado(), bean);
            
            //2025-07-17 actualizar cantidad items a nivel de tutela.
            tuTutela.setCantidadItems(getTuEstadoRemoto().contarItemsTutela(tuTutela.getId()));
            tuTutela.setCantidadItemsCerrados(getTuEstadoRemoto().contarItemsCerradosTutela(tuTutela.getId()));
            
            getTutelaRemoto().actualizar(tuTutela);
            if (!bean.isError()) {
                bean.addMensaje("Se actualizó un registro de manera exitosa");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void modificarEstado(TuTutelaBean bean) {
        try {
            TuTutelaEstado tutelaEstado = bean.getObjEstados();
            bean.auditoriaModificar(tutelaEstado);
            getTuEstadoRemoto().actualizar(tutelaEstado);
            List<TuTutelaEstadoRepresentante> tuTutelaEstadoRepresentante;
            if (tutelaEstado.getListaFiltrosRepresentanteDemandado() != null) {
                tuTutelaEstadoRepresentante = getTuTutelaEstadoRepresentanteRemoto().consultarRespresentantesPorEstadoTutela(tutelaEstado.getId());
                if (!tuTutelaEstadoRepresentante.isEmpty()) {
                    getTuTutelaEstadoRepresentanteRemoto().eliminarResprentantes(tutelaEstado.getId());
                    guardarRepresentanteDemandado(tutelaEstado.getListaFiltrosRepresentanteDemandado(), tutelaEstado, bean);
                } else {
                    guardarRepresentanteDemandado(tutelaEstado.getListaFiltrosRepresentanteDemandado(), tutelaEstado, bean);
                }
            } else {
                tuTutelaEstadoRepresentante = getTuTutelaEstadoRepresentanteRemoto().consultarRespresentantesPorEstadoTutela(tutelaEstado.getId());
                if (!tuTutelaEstadoRepresentante.isEmpty()) {
                    getTuTutelaEstadoRepresentanteRemoto().eliminarResprentantes(tutelaEstado.getId());
                }
            }
            insertarAdjuntos(tutelaEstado.getTuAdjuntosList(), bean, 0, tutelaEstado.getId(), 0, 0);
            if (!bean.isError()) {
                bean.getObjeto().setActualTuTutelaEstadoId(tutelaEstado);
                getTutelaRemoto().actualizar(bean.getObjeto());
                bean.addMensaje("Se actualizó un registro de manera exitosa");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void modificarItem(TuTutelaBean bean) {
        TuTutela tutela = null;
        TuTutelaEstado tutelaEstado = null;
        try {
            TuTutelaItem tuTutelaItem = bean.getObjTutulaItem();
            bean.auditoriaModificar(tuTutelaItem);
            if (!tuTutelaItem.getTuTutelaDiagnosticosId().isEmpty()) {
                procesarTuDiagnostico(tuTutelaItem.getTuTutelaDiagnosticosId(), tuTutelaItem, bean);
            }
            //PENDIENTE aca se podria actualizar los estados tambien, esto cuando modifique un ítem.
            //2025-07-22 jyperez datos para la gestion item tabla
            TuTutelaItemGestion gestionItem = new TuTutelaItemGestion();
            gestionItem.setTuTutelaItemId(tuTutelaItem);
            gestionItem.setObservacion(tuTutelaItem.getObservacion());
            //el campo observacionIps no puede ser nulo. entonces adjuntamos el dato de observacion
            gestionItem.setObservacionIps(tuTutelaItem.getObservacion());
            gestionItem.setMaeEstadoItemId(tuTutelaItem.getMaeEstadoItemId());
            gestionItem.setMaeEstadoItemCodigo(tuTutelaItem.getMaeEstadoItemCodigo());
            gestionItem.setMaeEstadoItemValor(tuTutelaItem.getMaeEstadoItemValor());
            bean.auditoriaGuardar(gestionItem);
            ////2025-07-22 jyperez actualizamos los valores de conteo de items en TuTutela y TuTutelaEstado
            tutela = getTutelaRemoto().consultar(tuTutelaItem.getTuTutelasId().getId());
            if (tutela == null) {
                bean.addError("Error obteniendo los datos de la tutela");
            }
            tutelaEstado = getTuEstadoRemoto().consultar(tuTutelaItem.getTuTutelaEstadosId().getId());
            if (tutelaEstado == null) {
                bean.addError("Error obteniendo los datos del estado de la tutela");
            }
            if(!bean.isError()){
                getTuItemRemoto().actualizar(tuTutelaItem);
                insertarAdjuntos(tuTutelaItem.getTuAdjuntosList(), bean, 0, tuTutelaItem.getTuTutelaEstadosId().getId(), 0, tuTutelaItem.getId());
                getTuTutelaItemGestionRemoto().insertar(gestionItem);
                //2025-07-22 jyperez actualizamos los valores de conteo de items en TuTutela y TuTutelaEstado
                tutela.setCantidadItems(getTuEstadoRemoto().contarItemsTutela(tutela.getId()));
                tutela.setCantidadItemsCerrados(getTuEstadoRemoto().contarItemsCerradosTutela(tutela.getId()));
                tutelaEstado.setCantidadItems(getTuEstadoRemoto().contarItemsEstado(tutelaEstado.getId()));
                tutelaEstado.setCantidadItemsCerrados(getTuEstadoRemoto().contarItemsCerradosEstado(tutelaEstado.getId()));
                getTutelaRemoto().actualizar(tutela);
                getTuEstadoRemoto().actualizar(tutelaEstado);
                bean.addMensaje("Se actualizó un registro de manera exitosa");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void modificarDiagnostico(TuTutelaBean bean) {
        try {
            TuDiagnostico tuDiagnostico = bean.getEditarDiagnostico();
            bean.auditoriaModificar(tuDiagnostico);
            getTuDiagnosticoRemoto().actualizar(tuDiagnostico);
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void modificarSeguimiento(TuTutelaBean bean) {
        try {
            TuSeguimiento seguimiento = bean.getObjEstadosSeguimiento();
            bean.auditoriaModificar(seguimiento);
            getTuSeguimientoRemoto().actualizar(seguimiento);
            insertarAdjuntos(seguimiento.getTuAdjuntosList(), bean, 0, seguimiento.getTuTutelaEstadosId().getId(), seguimiento.getId(), 0);
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void modificarResponsable(TuTutelaBean bean) {
        try {
            TuTutelaEstado tuTutelaEstado = bean.getModicarResponsable();
            bean.auditoriaModificar(tuTutelaEstado);
            tuTutelaEstado.setResponsableGnUsuarioId(new Usuario(tuTutelaEstado.getResponsableGnUsuarioId().getId()));
            getTuEstadoRemoto().actualizarResponsableGnUsuario(tuTutelaEstado);

            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(TuTutelaBean bean) {
        try {
            TuTutela tuTutela = bean.getObjeto();
            bean.auditoriaModificar(tuTutela);
            tuTutela.setUsuarioBorra(tuTutela.getUsuarioModifica());
            tuTutela.setTerminalBorra(tuTutela.getTerminalModifica());
            tuTutela.setFechaHoraBorra(tuTutela.getFechaHoraModifica());
            getTutelaRemoto().actualizar(tuTutela);
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarEstado(TuTutelaBean bean) {
        try {
            TuTutelaEstado tuTutelaEstado = getTuEstadoRemoto().consultar(bean.getObjEstados().getId());
            tuTutelaEstado.setBorrado(Boolean.TRUE);
            bean.auditoriaModificar(tuTutelaEstado);
            tuTutelaEstado.setUsuarioBorra(tuTutelaEstado.getUsuarioModifica());
            tuTutelaEstado.setTerminalBorra(tuTutelaEstado.getTerminalModifica());
            tuTutelaEstado.setFechaHoraBorra(tuTutelaEstado.getFechaHoraModifica());
            getTuEstadoRemoto().actualizar(tuTutelaEstado);

            //Actualizar esto de la tutela
            TuTutelaEstado anterior = null;
            if (bean.getRegistrosEstados().size() > 1) {
                for (TuTutelaEstado estado : bean.getRegistrosEstados()) {
                    if (estado.getId().intValue() != tuTutelaEstado.getId().intValue()) {
                        anterior = estado;
                    } else {
                        break;
                    }
                }
            }
            if (anterior != null) {
                bean.getObjeto().setActualTuTutelaEstadoId(anterior);
                bean.auditoriaModificar(bean.getObjeto());
                getTutelaRemoto().actualizarEstado(bean.getObjeto());
            }
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarItem(TuTutelaBean bean) {
        try {
            TuTutelaItem tuTutelaItem = getTuItemRemoto().consultar(bean.getObjTutulaItem().getId());

            for (TuAdjunto tuAdjunto : tuTutelaItem.getTuAdjuntosList()) {
                getTuAdjuntoRemoto().eliminar(tuAdjunto.getId());
            }
            /*for(TuDiagnostico tuDiagnostico: tuTutelaItem.getTuTutelaDiagnosticosId()){
                tuDiagnostico = getTuDianosticoRemoto.eliminar(tuDiagnostico.getId());
            }*/
            bean.setObjTutulaItem(getTuItemRemoto().eliminar(bean.getObjTutulaItem().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarSeguimiento(TuTutelaBean bean) {
        try {
            TuSeguimiento TuSeguimiento = getTuSeguimientoRemoto().consultar(bean.getObjEstadosSeguimiento().getId());
            for (TuAdjunto tuAdjunto : TuSeguimiento.getTuAdjuntosList()) {
                getTuAdjuntoRemoto().eliminar(tuAdjunto.getId());
            }
            bean.setObjEstadosSeguimiento(getTuSeguimientoRemoto().eliminar(bean.getObjEstadosSeguimiento().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarDiagnostico(TuTutelaBean bean) {
        try {
            TuDiagnostico tuDignostico = getTuDiagnosticoRemoto().consultar(bean.getCmAuditoriaDiagnostico().getId());
            if (tuDignostico != null) {
                bean.setCmAuditoriaDiagnostico(getTuDiagnosticoRemoto().eliminar(tuDignostico.getId()));
                bean.addMensaje("Se eliminó un registro de manera exitosa");
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarAdjunto(TuTutelaBean bean) {
        try {
            if (bean.getBorrarAdjunto() != null) {
                bean.setBorrarAdjunto(getTuAdjuntoRemoto().eliminar(bean.getBorrarAdjunto().getId()));
                bean.addMensaje("Se eliminó un registro de manera exitosa");
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarAutorizacion(TuTutelaBean bean) {
        try {
            if (bean.getObjTutulaItem() != null) {
                getTuItemRemoto().eliminarAutorizacion(bean.getObjTutulaItem());
                bean.addMensaje("Se eliminó un registro de manera exitosa");
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void borrarContactoPersona(TuTutelaBean bean) {
        try {
            TuPersonaContacto contacto = getTuPersonaContactoRemoto().consultar(bean.getTuPersonaContacto().getId());
            if (contacto != null) {
                getTuPersonaContactoRemoto().eliminar(contacto.getId());
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void consultarMaestroDocumentoMemorial(TuTutelaBean bean) {
        try {
            bean.setListApoderados(getTuMemorialPersonaRemoto().consultarListaApoderados());
            bean.setListAsistentes(getTuMemorialPersonaRemoto().consultarListaAsistenten());
            bean.setListaArgumentos(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_MEMORIAL_ARGUMENTOS));
            bean.setHashArgumentos(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.TU_MEMORIAL_ARGUMENTOS));
            bean.setListaPretensiones(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_MEMORIAL_PENSIONES));
            bean.setHashPretensiones(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.TU_MEMORIAL_PENSIONES));
            bean.setListaReferencias(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_MEMORIAL_REFERENCIA));
            bean.setHashReferencias(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.TU_MEMORIAL_REFERENCIA));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void consultarEstado(TuTutelaBean bean) {
        try {
            bean.setObjEstados(getTuEstadoRemoto().consultar(bean.getObjEstados().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void consultarSeguimiento(TuTutelaBean bean) {
        try {
            bean.setObjEstadosSeguimiento(getTuSeguimientoRemoto().consultar(bean.getObjEstadosSeguimiento().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarAnexo4(TuTutelaBean bean) {
        try {
            Integer idAsegAfiliado;

            idAsegAfiliado = bean.getObjeto().getTuPersona().getAsegAfiliadoId() != null
                    ? bean.getObjeto().getTuPersona().getAsegAfiliadoId() : null;

            if (idAsegAfiliado != null) {
                bean.getParamConsulta(5).setRegistrosPagina(30);
                bean.getParamConsulta(5).setParametroConsulta1(idAsegAfiliado);
                bean.getParamConsulta(5).setCantidadRegistros(getAuAnexo4Remoto().consultarCantidadListaPorAfiliado(bean.getParamConsulta(5)));
                bean.setRegistrosAnexo4(getAuAnexo4Remoto().consultarListaPorAfiliado(bean.getParamConsulta(5)));
            } else {
                bean.addError("Se han presentado problemas para obtener el numero de documento para consultar anexo4");
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarSedesPrescriptora(TuTutelaBean bean) {
        try {
            bean.getParamConsulta(4).setCantidadRegistros(getCntPrestadorRemoto().consultarCantidadListaSede(bean.getParamConsulta(4)));
            bean.setListaPrestadorSedes(getCntPrestadorRemoto().consultarListaSede(bean.getParamConsulta(4)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void consultarItem(TuTutelaBean bean) {
        try {
            bean.setObjTutulaItem(getTuItemRemoto().consultar(bean.getObjTutulaItem().getId()));
            if (bean.getObjTutulaItem().getConsultarAutorizacion() != null) {
                AuAnexo4 auAnexo4 = getAuAnexo4Remoto().consultar(bean.getObjTutulaItem().getConsultarAutorizacion());
                if (auAnexo4 != null) {
                    List<AuAnexo4> listaAutoriacion = new ArrayList<>();
                    listaAutoriacion.add(auAnexo4);
                    bean.getObjTutulaItem().setRegistrosAuditoriaAutorizacion(listaAutoriacion);
                }

            }
            //2025-07-15 jyperez consultamos la gestión correspondiente al ítem
            bean.getObjTutulaItem().setTuTutelaItemGestionesList(getTuTutelaItemGestionRemoto().consultarLista(bean.getObjTutulaItem().getId()));

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private List<TuTutelaEstado> procesarEstados(List<TuTutelaEstado> tutelaEstadoProcesar, TuTutelaBean bean) {
        List<TuTutelaEstado> listaServicios = new ArrayList();
        int itemsTotal = 0;
        int itemsCerrados = 0;
        try {
                for (TuTutelaEstado tuTutelaEstado : tutelaEstadoProcesar) {
                    bean.auditoriaModificar(tuTutelaEstado);
                    //if (servicio.getAccion() == AusCasoServicio.ACCION_INSERTAR) {
                    //bean.auditoriaGuardar(tuTutelaEstado);
                    if (!tuTutelaEstado.getListaTuTutelaItem().isEmpty()) {
                        itemsCerrados = procesarItem(tuTutelaEstado.getListaTuTutelaItem(), tuTutelaEstado, bean);
                    }
                    //2025-07-17 jyperez actualizamos los valores de cantidad de items
                    itemsTotal = tuTutelaEstado.getListaTuTutelaItem().size();
                    
                    if (!tuTutelaEstado.getTuSeguimientosList().isEmpty()) {
                        procesarSeguimiento(tuTutelaEstado.getTuSeguimientosList(), tuTutelaEstado, bean);
                    }
                    //2025-07-17 jyperez actualizamos el valor del estado
                    tuTutelaEstado.setCantidadItems(itemsTotal);
                    tuTutelaEstado.setCantidadItemsCerrados(itemsCerrados);
                    getTuEstadoRemoto().actualizar(tuTutelaEstado);
                }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }

        return listaServicios;
    }

    public int procesarItem(List<TuTutelaItem> tutelaItemProcesar, TuTutelaEstado tuTutelaEstado, TuTutelaBean bean) {
        int cerrados = 0;
        try {
            for (TuTutelaItem tuTutelaItem : tutelaItemProcesar) {
                if (tuTutelaItem.getId() == null) {
                    bean.auditoriaGuardar(tuTutelaItem);
                    tuTutelaItem.setTuTutelasId(bean.getObjeto());
                    tuTutelaItem.setTuPersonasId(bean.getObjeto().getTuPersona());
                    tuTutelaItem.setTuTutelaEstadosId(tuTutelaEstado);
                    int idTutelaItem = getTuItemRemoto().insertar(tuTutelaItem);
                    tuTutelaItem.setId(idTutelaItem);
                    //2025-07-22 jyperez adicionamos la gestión inicial al crear para cada item
                    TuTutelaItemGestion gestionItem = new TuTutelaItemGestion();
                    gestionItem.setTuTutelaItemId(tuTutelaItem);
                    gestionItem.setObservacion(tuTutelaItem.getObservacion());
                    //el campo observacionIps no puede ser nulo. entonces adjuntamos el dato de observacion
                    gestionItem.setObservacionIps(tuTutelaItem.getObservacion());
                    gestionItem.setMaeEstadoItemId(tuTutelaItem.getMaeEstadoItemId());
                    gestionItem.setMaeEstadoItemCodigo(tuTutelaItem.getMaeEstadoItemCodigo());
                    gestionItem.setMaeEstadoItemValor(tuTutelaItem.getMaeEstadoItemValor());
                    bean.auditoriaGuardar(gestionItem);
                    getTuTutelaItemGestionRemoto().insertar(gestionItem);
                    if (!tuTutelaItem.getTuTutelaDiagnosticosId().isEmpty()) {
                        procesarTuDiagnostico(tuTutelaItem.getTuTutelaDiagnosticosId(), tuTutelaItem, bean);
                    }
                    insertarAdjuntos(tuTutelaItem.getTuAdjuntosList(), bean, 0, tuTutelaItem.getTuTutelaEstadosId().getId(), 0, idTutelaItem);
                    if (!bean.isError()) {
                        bean.addMensaje("Se creo el Item con id (" + tuTutelaItem.getId() + ") de manera exitosa \n ");
                    }
                }
                if (tuTutelaItem.getMaeEstadoItemCodigo() != null && tuTutelaItem.getMaeEstadoItemCodigo().equals(TuTutelaItem.ESTADO_ITEM_CERRADO)) {
                    cerrados++;
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return cerrados;
    }

    public void procesarTuDiagnostico(List<TuDiagnostico> listaDiagnosticos, TuTutelaItem TuTutelaItem, TuTutelaBean bean) {
        try {
            for (TuDiagnostico tuDiagnostico : listaDiagnosticos) {
                if (tuDiagnostico.getId() == null) {
                    bean.auditoriaGuardar(tuDiagnostico);
                    tuDiagnostico.setTuTutelaItemsId(TuTutelaItem);
                    tuDiagnostico.setTuTutelasId(bean.getObjeto());
                    int idDiagnostico = getTuDiagnosticoRemoto().insertar(tuDiagnostico);
                    tuDiagnostico.setId(idDiagnostico);
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void procesarSeguimiento(List<TuSeguimiento> tutelaSeguimientoProcesar, TuTutelaEstado tuTutelaEstado, TuTutelaBean bean) {
        try {
            for (TuSeguimiento tuSeguimiento : tutelaSeguimientoProcesar) {
                if (tuSeguimiento.getId() == null) {
                    bean.auditoriaGuardar(tuSeguimiento);
                    tuSeguimiento.setTuTutelaEstadosId(tuTutelaEstado);
                    tuSeguimiento.setFechaSeguimiento(new Date());
                    int idSeguimiento = getTuSeguimientoRemoto().insertar(tuSeguimiento);
                    tuSeguimiento.setId(idSeguimiento);
                    insertarAdjuntos(tuSeguimiento.getTuAdjuntosList(), bean, 0, tuSeguimiento.getTuTutelaEstadosId().getId(), idSeguimiento, 0);
                    actualizarResponsableUsuario(tuSeguimiento, tuTutelaEstado, bean);
                    actualizarEstadoProceso(tuSeguimiento, tuTutelaEstado, bean);
                    if (!bean.isError()) {
                        bean.addMensaje("Se creo el Seguimiento con id (" + tuSeguimiento.getId() + ") de manera exitosa \n ");
                    }
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private boolean asignarUltimoUsuarioAGrupos(TuTutelaEstado tuTutelaEstado) {
        int idAuditorInicial = 0;
        switch (tuTutelaEstado.getTipoAuditorInicial()) {
            case TuGrupoUsuario.TIPO_ABOGADO:
                tuTutelaEstado.setResponsableGnUsuarioId(tuTutelaEstado.getAbogadoGnUsuarioId());
                idAuditorInicial = tuTutelaEstado.getAbogadoGnUsuarioId().getId();
                break;
            case TuGrupoUsuario.TIPO_GESTOR:
                tuTutelaEstado.setResponsableGnUsuarioId(tuTutelaEstado.getGestorGnUsuarioId());
                idAuditorInicial = tuTutelaEstado.getGestorGnUsuarioId().getId();
                break;
            case TuGrupoUsuario.TIPO_MEDICO:
                tuTutelaEstado.setResponsableGnUsuarioId(tuTutelaEstado.getMedicoGnUsuarioId());
                idAuditorInicial = tuTutelaEstado.getMedicoGnUsuarioId().getId();
                break;

        }
        return TuTulelaUtilidades.actualizarUltimoUsurioGrupo(tuTutelaEstado.getTuGrupoAutomaticoId(), tuTutelaEstado.getMaeEstadoId(), idAuditorInicial);

    }

    public void asignarResponsableManual(TuGrupo tuGrupo, TuTutelaEstado tuTutelaEstado) {
        switch (tuGrupo.getTipoAuditorInicial()) {
            case TuGrupo.TIPO_AUDITOR_INICIAL_ABOGADO:
                tuTutelaEstado.setResponsableGnUsuarioId(tuTutelaEstado.getAbogadoGnUsuarioId());
                break;
            case TuGrupo.TIPO_AUDITOR_INICIAL_GESTOR:
                tuTutelaEstado.setResponsableGnUsuarioId(tuTutelaEstado.getGestorGnUsuarioId());
                break;
            case TuGrupo.TIPO_AUDITOR_INICIAL_MEDICO:
                tuTutelaEstado.setResponsableGnUsuarioId(tuTutelaEstado.getMedicoGnUsuarioId());
                break;
        }
    }

    private void guardarTutelaEstadoPorDefecto(TuTutelaBean bean, TuTutela tuTutela) {
        try {
            TuTutelaEstado tuTutelaEstado = bean.getObjeto().getEstadoInicial();
            bean.auditoriaGuardar(tuTutelaEstado);
            Maestro estadoTutela = bean.getHashEstadoTutela().get(tuTutelaEstado.getMaeEstadoId());
            if (estadoTutela != null) {
                tuTutelaEstado.setMaeEstadoCodigo(estadoTutela.getValor());
                tuTutelaEstado.setMaeEstadoValor(estadoTutela.getNombre());
            }
            Maestro estadoProceso = bean.getHashEstadoProceso().get(tuTutelaEstado.getMaeProcesoId());
            if (estadoProceso != null) {
                tuTutelaEstado.setMaeProcesoCodigo(estadoProceso.getValor());
                tuTutelaEstado.setMaeProcesoValor(estadoProceso.getNombre());
            }
            Maestro tipoFallo = bean.getHashEstadoFallo().get(tuTutelaEstado.getMaeTipoFalloId());
            if (tipoFallo != null) {
                tuTutelaEstado.setMaeTipoFalloCodigo(tipoFallo.getValor());
                tuTutelaEstado.setMaeTipoFalloValor(tipoFallo.getNombre());
            }
            Maestro claseSancion = bean.getHashClaseSancion().get(tuTutelaEstado.getMaeClaseSancionId());
            if (claseSancion != null) {
                tuTutelaEstado.setMaeClaseSancionCodigo(claseSancion.getValor());
                tuTutelaEstado.setMaeClaseSancionValor(claseSancion.getNombre());
            }
            Maestro smlv = bean.getHashSmlv().get(tuTutelaEstado.getMaeSmlvId());
            if (smlv != null) {
                tuTutelaEstado.setMaeSmlvCodigo(smlv.getValor());
                tuTutelaEstado.setMaeSmlvValor(smlv.getNombre());
            }
            Maestro claseArresto = bean.getHashClaseArresto().get(tuTutelaEstado.getMaeClaseArrestoId());
            if (claseArresto != null) {
                tuTutelaEstado.setMaeClaseArrestoCodigo(claseArresto.getValor());
                tuTutelaEstado.setMaeClaseArrestoValor(claseArresto.getNombre());
            }
            tuTutelaEstado.setTuTutelaId(new TuTutela(tuTutela.getId()));
            if (!tuTutelaEstado.getTipoReparto()) {
                boolean actualizado = asignarUltimoUsuarioAGrupos(tuTutelaEstado);
                if (!actualizado) {
                    bean.addError("No se pudo realizar la actualizacion ultimo usuario grupo");
                }
            } else {
                TuGrupo tuGrupo = getTuGrupoRemoto().consultar(tuTutelaEstado.getTuGrupoId());
                asignarResponsableManual(tuGrupo, tuTutelaEstado);
            }

            if (!bean.isError()) {
                int idTutelaEstado = getTuEstadoRemoto().insertar(tuTutelaEstado);
                tuTutelaEstado.setId(idTutelaEstado);
                tuTutela.setActualTuTutelaEstadoId(tuTutelaEstado);
                insertarAdjuntos(tuTutelaEstado.getTuAdjuntosList(), bean, 0, idTutelaEstado, 0, 0);
                if (tuTutelaEstado.getListaFiltrosRepresentanteDemandado() != null) {
                    guardarRepresentanteDemandado(tuTutelaEstado.getListaFiltrosRepresentanteDemandado(), tuTutelaEstado, bean);
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void guardarRepresentanteDemandado(Integer[] idRepresentanteDemandado, TuTutelaEstado tuTutelaEstado, TuTutelaBean bean) {
        try {
            for (int id : idRepresentanteDemandado) {
                TuTutelaEstadoRepresentante tuTutelaEstadoRepresentante = new TuTutelaEstadoRepresentante();
                tuTutelaEstadoRepresentante.setTuRepresentantesId(new TuRepresentante(id));
                tuTutelaEstadoRepresentante.setTuTutelaEstadosId(tuTutelaEstado);
                bean.auditoriaGuardar(tuTutelaEstadoRepresentante);
                getTuTutelaEstadoRepresentanteRemoto().insertar(tuTutelaEstadoRepresentante);
            }
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void guardarTutelaEstadoNuevo(TuTutelaBean bean, TuTutela tuTutela) {
        try {
            TuTutelaEstado tuTutelaEstado = bean.getObjEstados();
            bean.auditoriaGuardar(tuTutelaEstado);
            //tuTutelaEstado.setResponsableGnUsuarioId(bean.getConexion().getUsuario());
            tuTutelaEstado.setTuTutelaId(new TuTutela(tuTutela.getId()));
            if (tuTutelaEstado.getTipoReparto() != null) {
                if (!tuTutelaEstado.getTipoReparto()) {
                    if (tuTutelaEstado.getTuGrupoAutomaticoId() != null) {
                        boolean actualizado = asignarUltimoUsuarioAGrupos(tuTutelaEstado);
                        if (!actualizado) {
                            bean.addError("No se pudo realizar la actualizacion ultimo usuario grupo");
                        }
                    }
                } else {
                    TuGrupo tuGrupo = getTuGrupoRemoto().consultar(tuTutelaEstado.getTuGrupoId());
                    asignarResponsableManual(tuGrupo, tuTutelaEstado);
                }
            }

            if (!bean.isError()) {
                int idTutelaEstado = getTuEstadoRemoto().insertar(tuTutelaEstado);
                tuTutelaEstado.setId(idTutelaEstado);
                tuTutela.setActualTuTutelaEstadoId(tuTutelaEstado);
                insertarAdjuntos(tuTutelaEstado.getTuAdjuntosList(), bean, 0, idTutelaEstado, 0, 0);
                if (tuTutelaEstado.getListaFiltrosRepresentanteDemandado() != null) {
                    guardarRepresentanteDemandado(tuTutelaEstado.getListaFiltrosRepresentanteDemandado(), tuTutelaEstado, bean);
                }
                bean.auditoriaModificar(tuTutela);
                getTutelaRemoto().actualizar(tuTutela);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void guardarTuPersonaContactos(TuTutelaBean bean) {
        try {
            for (TuPersonaContacto tuPersonaContacto : bean.getListaTuPersonaContacto()) {
                if (tuPersonaContacto.getId() == null) {
                    tuPersonaContacto.setTuPersona(new TuPersona(bean.getObjeto().getTuPersona().getId()));
                    bean.auditoriaGuardar(tuPersonaContacto);
                    getTuPersonaContactoRemoto().insertar(tuPersonaContacto);
                }
            }
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void listarAnexo4Items(TuTutelaBean bean) {
        try {
            if (bean.getAuAnexo4() != null && bean.getAuAnexo4().getId() != null) {
                bean.getParamConsulta(6).setParametroConsulta2(bean.getAuAnexo4().getId());
                bean.getParamConsulta(6).setCantidadRegistros(getCmAuditoriaAutorizacionRemoto().consultarCantidadAnexo4ItemsPorAtributos(bean.getParamConsulta(6)));
                bean.setRegistrosAnexo4Item(getCmAuditoriaAutorizacionRemoto().consultarListaAnexo4ItemsPorAtributos(bean.getParamConsulta(6)));
            } else {
                bean.addError("Se han presentado problemas para obtener el id anexo4 para consultar anexo4 Items");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void insertarAdjuntos(List<TuAdjunto> adjuntosIn, TuTutelaBean bean,
            int idTutela, int idTutelaEstado, int idSeguimiento, int idTutelaItem) {

        try {
            for (TuAdjunto adjunto : adjuntosIn) {
                if (adjunto.getId() == null) {
                    bean.auditoriaGuardar(adjunto);
                    /*if (idTutela > 0) {
                        adjunto.setTuTutelaId(new TuTutela(idTutela));
                    }*/

                    if (idTutelaEstado > 0) {
                        adjunto.setTuTutelaEstadosId(new TuTutelaEstado(idTutelaEstado));
                    }

                    if (idSeguimiento > 0) {
                        adjunto.setTuSeguimientosId(new TuSeguimiento(idSeguimiento));
                    }

                    if (idTutelaItem > 0) {
                        adjunto.setTuTutelaItemsId(new TuTutelaItem(idTutelaItem));
                    }

                    int idAdjunto = getTuAdjuntoRemoto().insertar(adjunto);
                    adjunto.setId(idAdjunto);
                }

            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void consultarPersona(TuTutelaBean bean) {
        try {
            //bean.getObjeto().setEmpresa(bean.getConexion().getEmpresa());
            TuPersona ausPersona = getTuPersonaRemoto().consultarPersona(bean.getObjeto().getTuPersona());
            //AsegAfiliado asegAfiliado = getAfiliadoRemoto().consultar(bean.getObjeto());
            if (ausPersona.exitePersona()) {
                bean.getObjeto().setTuPersona(ausPersona);
            }
            /*if (ausPersona.exitePersona()) {
                List<AusPersonaTelefono> telefonosPersona = getPersonaRemoto().consultarTelefonosPersonas(ausPersona.getDocumento());
                ausPersona.setListaTelefonos(telefonosPersona);
                bean.setObjeto(ausPersona);

                bean.setAusPersonaTelefono((AusPersonaTelefono) ausPersona.getListaTelefonos());
            }*/
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void consultarPersonaAfiliada(TuTutelaBean bean) {
        try {
            //AsegAfiliado ausPersona = getAfiliadoRemoto().consultar(bean.getObjeto().getTuPersona().getMaeTipoDocumentoId(), bean.getObjeto().getTuPersona().getNumeroDocumento());
            List<AsegAfiliado> ausPersona = getAfiliadoRemoto().consultarListaAfiliados(bean.getObjeto().getTuPersona().getMaeTipoDocumentoCodigo(), bean.getObjeto().getTuPersona().getNumeroDocumento());
            if (ausPersona != null && !ausPersona.isEmpty()) {
                if (ausPersona.size() == 1) {
                    castEntidadNegocio(bean, ausPersona.get(0));
                } else {
                    Collections.sort(ausPersona, (o1, o2) -> o1.getFechaHoraCrea().compareTo(o2.getFechaHoraCrea()));
                    castEntidadNegocio(bean, ausPersona.get(ausPersona.size() - 1));
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void consultarGrupos(TuTutelaBean bean) {
        try {
//          bean.getObjeto().setEmpresa(bean.getConexion().getEmpresa());
            if (bean.getObjeto().getEstadoInicial().getMaeEstadoId() != null) {
                bean.getParamConsulta(2).setParametroConsulta1(bean.getObjeto().getEstadoInicial().getMaeEstadoId());
            }
            List<TuGrupo> tuGrupos = getTuGrupoRemoto().consultarListaPorEstado(bean.getParamConsulta(2));
            //AsegAfiliado asegAfiliado = getAfiliadoRemoto().consultar(bean.getObjeto());
            if (tuGrupos != null && !tuGrupos.isEmpty()) {
                bean.setListaGrupos(tuGrupos);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void consultarGruposAdicional(TuTutelaBean bean) {
        try {
            if (bean.getObjEstados().getMaeEstadoId() != null) {
                bean.getParamConsulta(2).setParametroConsulta1(bean.getObjEstados().getMaeEstadoId());
            }
            List<TuGrupo> tuGrupos = getTuGrupoRemoto().consultarListaPorEstado(bean.getParamConsulta(2));
            //AsegAfiliado asegAfiliado = getAfiliadoRemoto().consultar(bean.getObjeto());
            if (tuGrupos != null && !tuGrupos.isEmpty()) {
                bean.setListaGrupos(tuGrupos);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public List<TuGrupoUsuario> consultarUsuariosGrupos(TuTutelaBean bean) {
        List<TuGrupoUsuario> tuGrupoUsuarios = new ArrayList<>();
        try {
//          bean.getObjeto().setEmpresa(bean.getConexion().getEmpresa());
            if (bean.getObjeto().getEstadoInicial().getTuGrupoId() != null) {
                bean.getParamConsulta(3).setParametroConsulta1(bean.getObjeto().getEstadoInicial().getTuGrupoId());
                bean.getParamConsulta(3).setParametroConsulta3(true);
                bean.getParamConsulta(3).setParametroConsulta5(null);
            }
            tuGrupoUsuarios = getTuGrupoUsuarioRemoto().consultarListaPorGrupo(bean.getParamConsulta(3));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return tuGrupoUsuarios;
    }

    @Override
    public List<TuGrupoUsuario> consultarUsuariosGruposAdicional(TuTutelaBean bean) {
        List<TuGrupoUsuario> tuGrupoUsuarios = new ArrayList<>();
        try {
            if (bean.getObjEstados().getTuGrupoId() != null) {
                bean.getParamConsulta(3).setParametroConsulta1(bean.getObjEstados().getTuGrupoId());
                bean.getParamConsulta(3).setParametroConsulta3(true);
                bean.getParamConsulta(3).setParametroConsulta5(null);
            }
            tuGrupoUsuarios = getTuGrupoUsuarioRemoto().consultarListaPorGrupo(bean.getParamConsulta(3));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return tuGrupoUsuarios;
    }

    @Override
    public List<TuGrupoUsuario> consultarUsuarioGrupoPorTipo(TuTutelaBean bean) {
        List<TuGrupoUsuario> tuGrupoUsuarios = new ArrayList<>();
        try {
//          bean.getObjeto().setEmpresa(bean.getConexion().getEmpresa());
            if (bean.getConexion().getUsuario().getId() != null) {
                bean.getParamConsulta(3).setParametroConsulta1(null);
                bean.getParamConsulta(3).setParametroConsulta3(true);
                bean.getParamConsulta(3).setParametroConsulta5(bean.getConexion().getUsuario().getId());
            }
            tuGrupoUsuarios = getTuGrupoUsuarioRemoto().consultarListaPorGrupo(bean.getParamConsulta(3));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return tuGrupoUsuarios;
    }

    @Override
    public void consultarEstadoTutela(TuTutelaBean bean) {
        try {
            //2022-04-25 jyperez se actualiza el nuevo padre grupo a evaluar.
            if (bean.getObjeto().getActualTuTutelaEstadoId() != null) {
                bean.setListaEstadoTutela(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.TU_TUTELA_ESTADO, bean.getObjeto().getActualTuTutelaEstadoId().getMaeEstadoId()));
                bean.setHashEstadoTutela(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.TU_TUTELA_ESTADO));
            }
        } catch (Exception ex) {
            //cargamos las listas vacias si ocurre algun error.
            bean.setListaEstadoTutela(new ArrayList<>());
            bean.setHashEstadoTutela(new HashMap<>());
        }
    }

    @Override
    public void consultarTodosEstadoTutela(TuTutelaBean bean) {
        try {
            bean.setListaEstadoTutela(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_TUTELA_ESTADO));
            bean.setHashEstadoTutela(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.TU_TUTELA_ESTADO));
        } catch (Exception ex) {
            //cargamos las listas vacias si ocurre algun error.
            bean.setListaEstadoTutela(new ArrayList<>());
            bean.setHashEstadoTutela(new HashMap<>());
            bean.addError("Error al consultar lista de estados de tutela");
        }
    }

    @Override
    public void listarJuzgados(TuTutelaBean bean) {
        try {
            if (bean.getObjeto().getGnUbicacionId() != null) {
                int idJuzgado = (bean.getObjEstados() != null 
                            && bean.getObjEstados().getTuJuzgadoId() != null 
                            && bean.getObjEstados().getTuJuzgadoId().getId() != null) ? bean.getObjEstados().getTuJuzgadoId().getId() : 0;
                if (bean.getObjeto().getGnUbicacionId().getId() != null) {
                    bean.setListaJuzgados(getTuJuzgadoRemoto().consultarJuzgadoPorUbicacion(bean.getObjeto().getGnUbicacionId().getId(), idJuzgado));
                    bean.setHashJuzgados(convertToHashJuzgados(bean.getListaJuzgados()));
                }
            }
        } catch (Exception ex) {
            bean.addError("Error al consultar lista de juzgados");
        }
    }

    @Override
    public void listarUsuarios(TuTutelaBean bean) {
        try {
            bean.setListaUsuarios(getUsuarioRemoto().consultarPorEmpresa(bean.getConexion().getEmpresa().getId()));
            bean.setHashUsuarios(convertToHashUsuarios(bean.getListaUsuarios()));
        } catch (Exception ex) {
            bean.setListaUsuarios(new ArrayList<>());
            bean.setHashUsuarios(new HashMap<>());
            bean.addError("Error al consultar lista de usuarios");
        }
    }
    
    @Override
    public void listarUsuariosPorRol(TuTutelaBean bean) {
        try {
            if ( bean.getObjTutulaItem().getMaeTipoAsignacionId() != null) {
                Maestro tipoAsignacion = bean.getHashTipoAsignacion().get(bean.getObjTutulaItem().getMaeTipoAsignacionId());
                if (tipoAsignacion.getValor().equals(TuTutelaBean.TIPO_ASIGNACION_ITEM_IPS)) {
                    bean.setListaUsuarios(getUsuarioRemoto().consultarPorRol(TuTutelaBean.ROL_EXTERNOS_ID));
                } else {
                    bean.setListaUsuarios(getUsuarioRemoto().consultarPorRol(TuTutelaBean.ROL_INTERNOS_ID));
                }
            } else {
                bean.setListaUsuarios(new ArrayList<>());
                bean.setHashUsuarios(new HashMap<>());
            }
        } catch (Exception ex) {
            bean.setListaUsuarios(new ArrayList<>());
            bean.setHashUsuarios(new HashMap<>());
            bean.addError("Error al consultar lista de usuarios");
        }
    }

    @Override
    public void listarTipoSeguimiento(TuTutelaBean bean, Integer saberSiGestorOMedicoObogado) {
        try {
            switch (saberSiGestorOMedicoObogado) {
                case 1:
                    //bean.setListaTipoSeguimiento(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_SEGUIMIENTO_TIPO));
                    bean.setListaTipoSeguimiento(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.TU_SEGUIMIENTO_TIPO, Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.TIPO_SEGUIMIENTO_GESTOR))));
                    break;
                case 2:
                    //bean.setListaTipoSeguimiento(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_SEGUIMIENTO_TIPO));
                    bean.setListaTipoSeguimiento(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.TU_SEGUIMIENTO_TIPO, Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.TIPO_SEGUIMIENTO_ABOGADO))));
                    break;
                case 3:
                    //bean.setListaTipoSeguimiento(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_SEGUIMIENTO_TIPO));
                    bean.setListaTipoSeguimiento(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.TU_SEGUIMIENTO_TIPO, Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.TIPO_SEGUIMIENTO_MEDICO))));
                    break;
                /*case 4:
                    bean.setListaTipoSeguimiento(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_SEGUIMIENTO_TIPO));
                    break;*/
            }
            bean.setHashTipoSeguimiento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.TU_SEGUIMIENTO_TIPO));
        } catch (Exception ex) {
            Logger.getLogger(AusCasoServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean consultarDiaHabil(Date fecha) {
        boolean diaHabil;
        try {
            diaHabil = getCalendarioRemoto().esDiaHabil(fecha);
        } catch (Exception e) {
            diaHabil = false;
        }
        return diaHabil;
    }

    public void castEntidadNegocio(TuTutelaBean bean, AsegAfiliado afiliadoDto) {
        TuPersona persona = bean.getObjeto().getTuPersona();
        persona.setId(afiliadoDto.getId());
        persona.setNombres(afiliadoDto.getNombres());
        persona.setApellidos(afiliadoDto.getApellidos());
        persona.setAsegAfiliadoId(afiliadoDto.getId());
        int idMaeTipoDoc = 0;
        String tipoDocumentoCodigo = "";
        String tipoDocumentoValor = "";
        for (Map.Entry<Integer, Maestro> entry : bean.getHashTiposDocumento().entrySet()) {
            Maestro maestro = entry.getValue();
            if (afiliadoDto.getMaeTipoDocumentoCodigo().equals(maestro.getValor())) {
                idMaeTipoDoc = maestro.getId();
                tipoDocumentoCodigo = maestro.getValor();
                tipoDocumentoValor = maestro.getNombre();
                break;
            }
        }
        persona.setMaeTipoDocumentoId(idMaeTipoDoc);
        persona.setMaeTipoDocumentoCodigo(tipoDocumentoCodigo);
        persona.setMaeTipoDocumentoValor(tipoDocumentoValor);

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse(afiliadoDto.getFechaNacimiento().toString());
        } catch (ParseException ex) {
            Logger.getLogger(AusPersonaServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        persona.setFechaNacimento(date);
        int idSexo = 0;
        String codigoSexo = "";
        String valorSexo = "";
        if (afiliadoDto.getMaeGeneroValor() != null && bean.getHashSexo() != null) {
            for (Map.Entry<Integer, Maestro> entry : bean.getHashSexo().entrySet()) {
                Maestro maestro = entry.getValue();
                if (afiliadoDto.getMaeGeneroValor().equalsIgnoreCase(maestro.getDescripcion())) {
                    idSexo = maestro.getId();
                    codigoSexo = maestro.getValor();
                    valorSexo = maestro.getNombre();
                    break;
                }
            }
        }
        persona.setMaeGeneroId(idSexo);
        persona.setMaeGeneroCodigo(codigoSexo);
        persona.setMaeGeneroValor(valorSexo);
        int idEstadoAfiliacion = 0;
        if (bean.getHashTipoEstadoPersona() != null && afiliadoDto.getMaeEstadoAfiliacionValor() != null) {
            for (Map.Entry<Integer, Maestro> entry : bean.getHashTipoEstadoPersona().entrySet()) {
                Maestro maestro = entry.getValue();
                if (afiliadoDto.getMaeEstadoAfiliacionValor().equalsIgnoreCase(maestro.getDescripcion())) {
                    idEstadoAfiliacion = maestro.getId();
                    break;
                }
            }
        }

        persona.setMaeEstadoAfiliadoId(afiliadoDto.getMaeEstadoAfiliacion());
        persona.setMaeEstadoAfiliadoCodigo(afiliadoDto.getMaeEstadoAfiliacionCodigo());
        persona.setMaeEstadoAfiliadoValor(afiliadoDto.getMaeEstadoAfiliacionValor());
        persona.setCorreoElectronico(afiliadoDto.getEmail());
        if (afiliadoDto.getResidenciaUbicacion() != null) {
            persona.setUbicacionAfiliadoId(new Ubicacion(afiliadoDto.getResidenciaUbicacion().getId()));
        }
        boolean diascapacidad = !afiliadoDto.getDiscapacidadStr().equalsIgnoreCase("NO");

        persona.setDireccion(afiliadoDto.getDireccionResidencia());
        //persona.setContrato(afiliadoDto.getC ContratoInternoAfilado());
        //int regimen = afiliadoDto.getRegimen().equalsIgnoreCase("Subsidiado") ? 1 : 0;
        Boolean regimen = afiliadoDto.getRegimen().equalsIgnoreCase("1") ? true : false;
        //Boolean regimen = afiliadoDto.getRegimen().equalsIgnoreCase("Subsidiado") ? true : false;

        String telefono = afiliadoDto.getTelefonoMovil();
        /*boolean agregarListaTel = (telefono != null && !telefono.trim().equals(""));
        if (agregarListaTel) {
            List<AusPersonaTelefono> listaTelefonos = new ArrayList<>();
            AusPersonaTelefono personatTelefono = new AusPersonaTelefono();
            personatTelefono.setNumero(telefono);
            listaTelefonos.add(personatTelefono);
            persona.setListaTelefonos(listaTelefonos);
            bean.setListaausPersonaTelefono(persona.getListaTelefonos());     
        }*/
        persona.setEsAfiliado(true);
    }

    @Override
    public TuDiagnostico consultarDiagnostico(int id) {
        TuDiagnostico tuDiagnostico;
        try {
            tuDiagnostico = getTuDiagnosticoRemoto().consultar(id);
        } catch (Exception e) {
            tuDiagnostico = null;
        }
        return tuDiagnostico;
    }

//    private void cargas(TuTutelaBean bean) {
//        try {
//            switch (bean.getAccion()) {
//                case Url.ACCION_LISTAR:
////                     bean.setUbicacionesRecursiva(getUbicacionRemoto().consultarMunicipios());
////                     bean.setUbicaciones(getUbicacionRemoto().consultarPorTipo(Ubicacion.TIPO_MUNICIPIO));
//                    break;
//                case Url.ACCION_VER:
//
//                    break;
//                case Url.ACCION_CREAR:
//                case Url.ACCION_EDITAR:
////                    bean.setUbicacionesRecursiva(getUbicacionRemoto().consultarMunicipios());
////                    bean.setUbicaciones(getUbicacionRemoto().consultarPorTipo(Ubicacion.TIPO_MUNICIPIO));
//                    break;
//                default:
//                    break;
//            }
//        } catch (Exception ex) {
//            bean.addError(ex.getMessage());
//        }
//    }
    public HashMap<Integer, Usuario> convertToHashUsuarios(List<Usuario> list) {
        HashMap<Integer, Usuario> map = new HashMap<>();
        for (Usuario i : list) {
            map.put(i.getId(), i);
        }
        return map;
    }

    public HashMap<Integer, TuJuzgado> convertToHashJuzgados(List<TuJuzgado> list) {
        HashMap<Integer, TuJuzgado> map = new HashMap<>();
        for (TuJuzgado i : list) {
            map.put(i.getId(), i);
        }
        return map;
    }

    @Override
    public void crearEstadoBloquearYcalarCamposObligatoriosParaEstadosTutelas(TuTutelaBean bean) {
        try {
            TuTutela tutela = bean.getObjeto();
            bean.desabilitarCamposEstadoInicial();
            bean.limpiarAsignacionAutomatica();
            bean.setListaGrupos(new ArrayList<>());
            bean.setListaAbogados(new ArrayList<>());
            bean.setListaGestores(new ArrayList<>());
            bean.setListaMedicos(new ArrayList<>());
            int estado = tutela.getEstadoInicial().getMaeEstadoId();
            Maestro estadoTuMaestro = bean.getHashEstadoTutela().get(estado);
            if (estadoTuMaestro != null) {
                tutela.getEstadoInicial().setMaeEstadoCodigo(estadoTuMaestro.getValor());
                tutela.getEstadoInicial().setMaeEstadoValor(estadoTuMaestro.getNombre());
            }
            tutela.getEstadoInicial().setTipoReparto(false);
            TuGrupoUsuariosAutomatico tuGrupo = TuTulelaUtilidades.consultarGrupoAutomatico(tutela.getEstadoInicial());
            tutela.getEstadoInicial().setTipoAuditorInicial(tuGrupo.getTuGrupo().getTipoAuditorInicial());
            agisnacionAutomaticoGrupo(tuGrupo, bean);

            Maestro estadoTutela = getMaestroRemoto().consultar(estado);
            if (estadoTutela != null) {
                if (estadoTutela.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_FALLO)) {
                    bean.setDeshabilitarCampoEstadoInicialFallo(false);
                    bean.setCampoObligatotioFallo(true);
                } else if (estadoTutela.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_SANCION_COMFIRMA)) {
                    bean.setDeshabilitarCampoEstadoInicialClaseSancion(false);
                    bean.setDeshabilitarCampoEstadoInicialRepresentanteDemandado(false);
                    bean.setCampoObligatotioClaseSansion(true);
                    bean.setCampoObligatotioRepresentanteDemandado(true);
                } else if (estadoTutela.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_TUTELA_NUEVA)) {
                    bean.setDeshabilitarCampoEstadoInicialMedidaProvisional(false);
                    bean.setCampoObligatorioMedidaProvisional(true);
                } else if (estadoTutela.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_SANSION)) {
                    bean.setDeshabilitarCampoEstadoInicialClaseSancion(false);
                    bean.setDeshabilitarCampoEstadoInicialRepresentanteDemandado(false);
                    bean.setCampoObligatotioClaseSansion(true);
                    bean.setCampoObligatotioRepresentanteDemandado(true);
                }
            }

        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    @Override
    public void crearEstadoBloquearYcalarCamposObligatoriosParaEstadosTutelasAdicionales(TuTutelaBean bean) {
        try {
            TuTutelaEstado objEstados = bean.getObjEstados();
            bean.desabilitarCamposEstadoInicialAdicional();
            bean.limpiarAsignacionAdicionalesAutomatica();
            bean.setListaGrupos(new ArrayList<>());
            bean.setListaAbogados(new ArrayList<>());
            bean.setListaGestores(new ArrayList<>());
            bean.setListaMedicos(new ArrayList<>());
            Integer idTutelaEstado = objEstados.getMaeEstadoId();
            Maestro estadoTuMaestro = bean.getHashEstadoTutela().get(idTutelaEstado);
            if (estadoTuMaestro != null) {
                objEstados.setMaeEstadoCodigo(estadoTuMaestro.getValor());
                objEstados.setMaeEstadoValor(estadoTuMaestro.getNombre());
            }

            Maestro estadoTutelaAnterior = getMaestroRemoto().consultar(bean.getLazyTuTutelaEstado().getWrappedData().get(bean.getLazyTuTutelaEstado().getWrappedData().size() - 1).getMaeEstadoId());
            if (estadoTutelaAnterior != null) {
                if (estadoTutelaAnterior.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_FALLO)) {
                    bean.setBloquearTipoReparto(false);
                    asignarCasoGrupoAutomatico(bean.getLazyTuTutelaEstado().getWrappedData().get(bean.getLazyTuTutelaEstado().getWrappedData().size() - 1).getMaeEstadoId(), bean);
                } else if (estadoTutelaAnterior.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_FALLO_SEGUNDA_INSTANCIA)) {
                    bean.setBloquearTipoReparto(false);
                    asignarCasoGrupoAutomatico(idTutelaEstado, bean);
                } else if (estadoTutelaAnterior.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_TERMINA_NULIDAD)) {
                    bean.setBloquearTipoReparto(false);
                    asignarCasoGrupoAutomatico(idTutelaEstado, bean);
                } else if (estadoTutelaAnterior.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_TUTELA_NUEVA)) {
                    bean.setBloquearTipoReparto(false);
                    asignarCasoGrupoAutomatico(idTutelaEstado, bean);
                } else if (estadoTutelaAnterior.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_REQUERIMIENTO_MP)) {
                    bean.setBloquearTipoReparto(false);
                    asignarCasoGrupoAutomatico(idTutelaEstado, bean);
                } else if (estadoTutelaAnterior.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_DESACATO_MP)) {
                    bean.setBloquearTipoReparto(false);
                    asignarCasoGrupoAutomatico(idTutelaEstado, bean);
                } else if (estadoTutelaAnterior.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_SANCION_MP)) {
                    bean.setBloquearTipoReparto(false);
                    asignarCasoGrupoAutomatico(idTutelaEstado, bean);
                } else if (estadoTutelaAnterior.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_INAPLICA_SANCION_MP)) {
                    bean.setBloquearTipoReparto(false);
                    asignarCasoGrupoAutomatico(idTutelaEstado, bean);
                } else if (estadoTutelaAnterior.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_TERMINA_DESACATO_MP)) {
                    bean.setBloquearTipoReparto(false);
                    asignarCasoGrupoAutomatico(idTutelaEstado, bean);
                } else if (estadoTutelaAnterior.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_DESISTIMIENTO)) {
                    bean.setBloquearTipoReparto(false);
                    asignarCasoGrupoAutomatico(idTutelaEstado, bean);
                } else {
                    asignarCasoGrupoAutomaticoNoaplica(bean);
                }
            }
            Maestro estadoTutelaActual = getMaestroRemoto().consultar(idTutelaEstado);
            if (estadoTutelaActual != null) {
                if (estadoTutelaActual.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_FALLO)) {
                    bean.setDeshabilitarCampoEstadoInicialFallo(false);
                    bean.setCampoObligatotioFallo(true);
                } else if (estadoTutelaActual.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_SANCION_COMFIRMA)) {
                    bean.setDeshabilitarCampoEstadoInicialClaseSancion(false);
                    bean.setDeshabilitarCampoEstadoInicialRepresentanteDemandado(false);
                    bean.setCampoObligatotioClaseSansion(true);
                    bean.setCampoObligatotioRepresentanteDemandado(true);
                } else if (estadoTutelaActual.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_TUTELA_NUEVA)) {
                    bean.setDeshabilitarCampoEstadoInicialMedidaProvisional(false);
                    bean.setCampoObligatorioMedidaProvisional(true);
                } else if (estadoTutelaActual.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_SANSION)) {
                    bean.setDeshabilitarCampoEstadoInicialClaseSancion(false);
                    bean.setDeshabilitarCampoEstadoInicialRepresentanteDemandado(false);
                    bean.setCampoObligatotioClaseSansion(true);
                    bean.setCampoObligatotioRepresentanteDemandado(true);
                }
            }
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    @Override
    public void crearHabilitarCamposTipoFallo(TuTutelaBean bean) {
        try {
            TuTutela objecto = bean.getObjeto();
            if (objecto.getEstadoInicial().getMaeTipoFalloId() != null) {
                Integer tipoFallo = objecto.getEstadoInicial().getMaeTipoFalloId();
                Maestro estadoTutelaActual = getMaestroRemoto().consultar(tipoFallo);
                if (estadoTutelaActual != null) {
                    if (estadoTutelaActual.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_FALLO_EN_CONTRA)) {
                        bean.setDeshabilitarCampoEstadoInicialImpugnacion(false);
                        bean.setDeshabilitarCampoEstadoInicialIntegralidad(false);
                        //2025-04-08 jyperez configura campo Integralidad como obligatorio
                        bean.setRequeridoCampoEstadoIInicialIntegralidad(true);
                        bean.setDeshabilitarCampoEstadoInicialEntregaSucesiva(false);
                        bean.setDeshabilitarCampoEstadoInicialExoneracion(false);
                    } else {
                        bean.setDeshabilitarCampoEstadoInicialImpugnacion(true);
                        bean.setDeshabilitarCampoEstadoInicialIntegralidad(true);
                        //2025-04-08 jyperez configura campo Integralidad como no obligatorio
                        bean.setRequeridoCampoEstadoIInicialIntegralidad(false);
                        bean.setDeshabilitarCampoEstadoInicialEntregaSucesiva(true);
                        bean.setDeshabilitarCampoEstadoInicialExoneracion(true);
                    }
                }
            }
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    @Override
    public void crearHabilitarCamposTipoFalloAdicional(TuTutelaBean bean) {
        try {
            TuTutelaEstado objecto = bean.getObjEstados();
            if (objecto.getMaeTipoFalloId() != null) {
                Integer tipoFallo = objecto.getMaeTipoFalloId();
                Maestro estadoTutelaActual = getMaestroRemoto().consultar(tipoFallo);
                if (estadoTutelaActual != null) {
                    if (estadoTutelaActual.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_FALLO_EN_CONTRA)) {
                        bean.setDeshabilitarCampoEstadoInicialImpugnacion(false);
                        bean.setDeshabilitarCampoEstadoInicialIntegralidad(false);
                        //2025-04-08 jyperez configura campo Integralidad como obligatorio
                        bean.setRequeridoCampoEstadoIInicialIntegralidad(true);
                        bean.setDeshabilitarCampoEstadoInicialEntregaSucesiva(false);
                        bean.setDeshabilitarCampoEstadoInicialExoneracion(false);
                    } else {
                        bean.setDeshabilitarCampoEstadoInicialImpugnacion(true);
                        bean.setDeshabilitarCampoEstadoInicialIntegralidad(true);
                        //2025-04-08 jyperez configura campo Integralidad como no obligatorio
                        bean.setRequeridoCampoEstadoIInicialIntegralidad(false);
                        bean.setDeshabilitarCampoEstadoInicialEntregaSucesiva(true);
                        bean.setDeshabilitarCampoEstadoInicialExoneracion(true);
                    }
                }
            }
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    @Override
    public void crearHabilitarCamposTipoClaseSancion(TuTutelaBean bean) {
        try {
            TuTutela objecto = bean.getObjeto();
            if (objecto.getEstadoInicial().getMaeClaseSancionId() != null) {
                Integer claseSancion = objecto.getEstadoInicial().getMaeClaseSancionId();
                Maestro estadoTutelaActual = getMaestroRemoto().consultar(claseSancion);
                if (estadoTutelaActual != null) {
                    if (estadoTutelaActual.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_CLASE_SANCION_MULTA)) {
                        bean.setDeshabilitarCampoEstadoInicialSMLV(false);
                        bean.setDeshabilitarCampoEstadoInicialUVT(false);
                        bean.setDeshabilitarCampoEstadoInicialClaseArresto(true);
                        bean.setDeshabilitarCampoEstadoInicialDiasArresto(true);
                        bean.setCampoObligatotioSmlv(true);
                        bean.setCampoObligatotioUVT(true);
                        bean.setCampoObligatotioClaseArresto(false);
                        bean.setCampoObligatotioDiasArresto(false);
                    } else if (estadoTutelaActual.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_CLASE_SANCION_ARRESTO)) {
                        bean.setDeshabilitarCampoEstadoInicialSMLV(true);
                        bean.setDeshabilitarCampoEstadoInicialUVT(true);
                        bean.setDeshabilitarCampoEstadoInicialClaseArresto(false);
                        bean.setDeshabilitarCampoEstadoInicialDiasArresto(false);
                        bean.setCampoObligatotioClaseArresto(true);
                        bean.setCampoObligatotioDiasArresto(true);
                        bean.setCampoObligatotioSmlv(false);
                        bean.setCampoObligatotioUVT(false);
                    } else if (estadoTutelaActual.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_CLASE_SANCION_MULTA_Y_ARRESTO)) {
                        bean.setDeshabilitarCampoEstadoInicialSMLV(false);
                        bean.setDeshabilitarCampoEstadoInicialUVT(false);
                        bean.setDeshabilitarCampoEstadoInicialClaseArresto(false);
                        bean.setDeshabilitarCampoEstadoInicialDiasArresto(false);
                        bean.setCampoObligatotioClaseArresto(true);
                        bean.setCampoObligatotioDiasArresto(true);
                        bean.setCampoObligatotioSmlv(true);
                        bean.setCampoObligatotioUVT(true);
                    }
                }
            }
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    @Override
    public void crearHabilitarCamposTipoClaseSancionAdicional(TuTutelaBean bean) {
        try {
            TuTutelaEstado objecto = bean.getObjEstados();
            if (objecto.getMaeClaseSancionId() != null) {
                Integer claseSancion = objecto.getMaeClaseSancionId();
                Maestro estadoTutelaActual = getMaestroRemoto().consultar(claseSancion);
                if (estadoTutelaActual != null) {
                    if (estadoTutelaActual.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_CLASE_SANCION_MULTA)) {
                        bean.setDeshabilitarCampoEstadoInicialSMLV(false);
                        bean.setDeshabilitarCampoEstadoInicialUVT(false);
                        bean.setDeshabilitarCampoEstadoInicialClaseArresto(true);
                        bean.setDeshabilitarCampoEstadoInicialDiasArresto(true);
                        bean.setCampoObligatotioSmlv(true);
                        bean.setCampoObligatotioUVT(true);
                        bean.setCampoObligatotioClaseArresto(false);
                        bean.setCampoObligatotioDiasArresto(false);
                    } else if (estadoTutelaActual.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_CLASE_SANCION_ARRESTO)) {
                        bean.setDeshabilitarCampoEstadoInicialSMLV(true);
                        bean.setDeshabilitarCampoEstadoInicialUVT(true);
                        bean.setDeshabilitarCampoEstadoInicialClaseArresto(false);
                        bean.setDeshabilitarCampoEstadoInicialDiasArresto(false);
                        bean.setCampoObligatotioClaseArresto(true);
                        bean.setCampoObligatotioDiasArresto(true);
                        bean.setCampoObligatotioSmlv(false);
                        bean.setCampoObligatotioUVT(false);
                    } else if (estadoTutelaActual.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_CLASE_SANCION_MULTA_Y_ARRESTO)) {
                        bean.setDeshabilitarCampoEstadoInicialSMLV(false);
                        bean.setDeshabilitarCampoEstadoInicialUVT(false);
                        bean.setDeshabilitarCampoEstadoInicialClaseArresto(false);
                        bean.setDeshabilitarCampoEstadoInicialDiasArresto(false);
                        bean.setCampoObligatotioClaseArresto(true);
                        bean.setCampoObligatotioDiasArresto(true);
                        bean.setCampoObligatotioSmlv(true);
                        bean.setCampoObligatotioUVT(true);
                    }
                }
            }
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    @Override
    public void consultarGruposEstadoInicialTutelas(TuTutelaBean bean) {
        try {
            TuTutela objecto = bean.getObjeto();
            bean.limpiarAsignacionAutomatica();
            bean.setListaGrupos(new ArrayList<>());
            if (objecto.getEstadoInicial().getTipoReparto() != null) {
                if (objecto.getEstadoInicial().getTipoReparto() == true) {
                    bean.setDeshabilitarCampoEstadoInicialGrupo(false);
                    consultarGrupos(bean);
                } else if (objecto.getEstadoInicial().getTipoReparto() == false) {
                    bean.setDeshabilitarCampoEstadoInicialGrupo(true);
                    TuGrupoUsuariosAutomatico tuGrupo = TuTulelaUtilidades.consultarGrupoAutomatico(objecto.getEstadoInicial());
                    objecto.getEstadoInicial().setTipoAuditorInicial(tuGrupo.getTuGrupo().getTipoAuditorInicial());
                    agisnacionAutomaticoGrupo(tuGrupo, bean);
                }
            }
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    @Override
    public void consultarGruposEstadosAdicionalesTutelas(TuTutelaBean bean) {
        try {
            TuTutelaEstado objEstados = bean.getObjEstados();
            bean.limpiarAsignacionAdicionalesAutomatica();
            bean.setListaGrupos(new ArrayList<>());
            bean.setListaAbogados(new ArrayList<>());
            bean.setListaGestores(new ArrayList<>());
            bean.setListaMedicos(new ArrayList<>());
            if (objEstados.getTipoReparto() != null) {
                if (objEstados.getTipoReparto() == true) {
                    bean.setDeshabilitarCampoEstadoInicialGrupo(false);
                    consultarGruposAdicional(bean);
                } else if (objEstados.getTipoReparto() == false) {
                    bean.setDeshabilitarCampoEstadoInicialGrupo(true);
                    TuGrupoUsuariosAutomatico tuGrupo = TuTulelaUtilidades.consultarGrupoAutomatico(objEstados);
                    objEstados.setTipoAuditorInicial(tuGrupo.getTuGrupo().getTipoAuditorInicial());
                    agisnacionAdicionalAutomaticoGrupo(tuGrupo, bean);
                }
            }
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    @Override
    public void consultarUsuariosGruposTutelasEstadoInicial(TuTutelaBean bean) {
        try {
            TuTutela objeto = bean.getObjeto();
            if (objeto.getEstadoInicial().getTuGrupoId() == null) {
                bean.limpiarAsignacionAutomatica();
            }
            bean.setListaAbogados(new ArrayList<>());
            bean.setListaGestores(new ArrayList<>());
            bean.setListaMedicos(new ArrayList<>());
            if (objeto.getEstadoInicial().getTuGrupoId() != null) {
                List<TuGrupoUsuario> tuGrupoUsuarios = consultarUsuariosGrupos(bean);
                for (TuGrupoUsuario tuGrupoUsuario : tuGrupoUsuarios) {
                    Usuario usuario = new Usuario();
                    usuario.setId(tuGrupoUsuario.getUsuario().getId());
                    usuario.setUsuario(tuGrupoUsuario.getUsuario().getUsuario());
                    usuario.setNombre(tuGrupoUsuario.getUsuario().getNombre());
                    switch (tuGrupoUsuario.getTipo()) {
                        case TuGrupoUsuario.TIPO_ABOGADO:
                            //this.objeto.getEstadoInicial().setAbogadoGnUsuarioId(usuario);
                            bean.getListaAbogados().add(usuario);
                            break;
                        case TuGrupoUsuario.TIPO_GESTOR:
                            //this.objeto.getEstadoInicial().setGestorGnUsuarioId(usuario);
                            bean.getListaGestores().add(usuario);
                            break;
                        case TuGrupoUsuario.TIPO_MEDICO:
                            //this.objeto.getEstadoInicial().setMedicoGnUsuarioId(usuario);
                            bean.getListaMedicos().add(usuario);
                            break;
                    }
                }
            }
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    public void agisnacionAutomaticoGrupo(TuGrupoUsuariosAutomatico tuGrupoUsuariosAutomatico, TuTutelaBean bean) {
        if (tuGrupoUsuariosAutomatico.isEstadoOperacion()) {
            bean.getObjeto().getEstadoInicial().setTuGrupoAutomaticoId(tuGrupoUsuariosAutomatico.getTuGrupo().getId());
            if (tuGrupoUsuariosAutomatico.getTuGrupoUsuarioAbogado() != null && tuGrupoUsuariosAutomatico.getTuGrupoUsuarioAbogado().getId() != null) {
                bean.getObjeto().getEstadoInicial().setAbogadoGnUsuarioId(tuGrupoUsuariosAutomatico.getTuGrupoUsuarioAbogado().getUsuario());
                bean.getListaAbogados().add(tuGrupoUsuariosAutomatico.getTuGrupoUsuarioAbogado().getUsuario());
            }
            if (tuGrupoUsuariosAutomatico.getTuGrupoUsuarioGestor() != null && tuGrupoUsuariosAutomatico.getTuGrupoUsuarioGestor().getId() != null) {
                bean.getObjeto().getEstadoInicial().setGestorGnUsuarioId(tuGrupoUsuariosAutomatico.getTuGrupoUsuarioGestor().getUsuario());
                bean.getListaGestores().add(tuGrupoUsuariosAutomatico.getTuGrupoUsuarioGestor().getUsuario());
            }
            if (tuGrupoUsuariosAutomatico.getTuGrupoUsuarioMedico() != null && tuGrupoUsuariosAutomatico.getTuGrupoUsuarioMedico().getId() != null) {
                bean.getObjeto().getEstadoInicial().setMedicoGnUsuarioId(tuGrupoUsuariosAutomatico.getTuGrupoUsuarioMedico().getUsuario());
                bean.getListaMedicos().add(tuGrupoUsuariosAutomatico.getTuGrupoUsuarioMedico().getUsuario());
            }
        } else {
            bean.addError(tuGrupoUsuariosAutomatico.getMensajeOperacion());
        }
    }

    public void asignarCasoGrupoAutomatico(Integer idTutelaEstado, TuTutelaBean bean) throws Exception {
        Maestro estadoTutela = getMaestroRemoto().consultar(idTutelaEstado);
        if (estadoTutela != null) {
            if (estadoTutela.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_FALLO)) {
                agisnacionAdicionalAutomaticoGrupoAnterioEstado(bean.getLazyTuTutelaEstado().getWrappedData().get(bean.getLazyTuTutelaEstado().getWrappedData().size() - 1), bean);
            } else if (estadoTutela.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_FALLO_SEGUNDA_INSTANCIA)) {
                agisnacionAdicionalAutomaticoGrupoAnterioEstado(bean.getLazyTuTutelaEstado().getWrappedData().get(bean.getLazyTuTutelaEstado().getWrappedData().size() - 1), bean);
            } else if (estadoTutela.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_TERMINA_NULIDAD)) {
                agisnacionAdicionalAutomaticoGrupoAnterioEstado(bean.getLazyTuTutelaEstado().getWrappedData().get(bean.getLazyTuTutelaEstado().getWrappedData().size() - 1), bean);
            } else if (estadoTutela.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_TUTELA_NUEVA)) {
                agisnacionAdicionalAutomaticoGrupoAnterioEstado(bean.getLazyTuTutelaEstado().getWrappedData().get(bean.getLazyTuTutelaEstado().getWrappedData().size() - 1), bean);
            } else if (estadoTutela.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_REQUERIMIENTO_MP)) {
                agisnacionAdicionalAutomaticoGrupoAnterioEstado(bean.getLazyTuTutelaEstado().getWrappedData().get(bean.getLazyTuTutelaEstado().getWrappedData().size() - 1), bean);
            } else if (estadoTutela.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_DESACATO_MP)) {
                agisnacionAdicionalAutomaticoGrupoAnterioEstado(bean.getLazyTuTutelaEstado().getWrappedData().get(bean.getLazyTuTutelaEstado().getWrappedData().size() - 1), bean);
            } else if (estadoTutela.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_SANCION_MP)) {
                agisnacionAdicionalAutomaticoGrupoAnterioEstado(bean.getLazyTuTutelaEstado().getWrappedData().get(bean.getLazyTuTutelaEstado().getWrappedData().size() - 1), bean);
            } else if (estadoTutela.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_INAPLICA_SANCION_MP)) {
                agisnacionAdicionalAutomaticoGrupoAnterioEstado(bean.getLazyTuTutelaEstado().getWrappedData().get(bean.getLazyTuTutelaEstado().getWrappedData().size() - 1), bean);
            } else if (estadoTutela.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_TERMINA_DESACATO_MP)) {
                agisnacionAdicionalAutomaticoGrupoAnterioEstado(bean.getLazyTuTutelaEstado().getWrappedData().get(bean.getLazyTuTutelaEstado().getWrappedData().size() - 1), bean);
            } else if (estadoTutela.contieneAccion(MaestroAccion.TU_TUTELA_ESTADO_HABILITAR_DESISTIMIENTO)) {
                agisnacionAdicionalAutomaticoGrupoAnterioEstado(bean.getLazyTuTutelaEstado().getWrappedData().get(bean.getLazyTuTutelaEstado().getWrappedData().size() - 1), bean);
            } else {
                asignarCasoGrupoAutomaticoNoaplica(bean);
            }
        }

    }

    public void asignarCasoGrupoAutomaticoNoaplica(TuTutelaBean bean) {
        bean.getObjEstados().setTipoReparto(false);
        bean.setBloquearTipoReparto(false);
        TuGrupoUsuariosAutomatico tuGrupo = TuTulelaUtilidades.consultarGrupoAutomatico(bean.getObjEstados());
        bean.getObjEstados().setTipoAuditorInicial(tuGrupo.getTuGrupo().getTipoAuditorInicial());
        if (tuGrupo.getEstadoOperacion()) {
            agisnacionAdicionalAutomaticoGrupo(tuGrupo, bean);
        } else {
            bean.addError("No se ha encontrado un reparto automático para este estado: " + bean.getObjEstados().getMaeEstadoValor());
            //agisnacionAdicionalAutomaticoGrupoAnterioEstado(this.lazyTuTutelaEstado.getWrappedData().get(this.lazyTuTutelaEstado.getWrappedData().size() - 1));
        }
    }

    public void agisnacionAdicionalAutomaticoGrupo(TuGrupoUsuariosAutomatico tuGrupoUsuariosAutomatico, TuTutelaBean bean) {
        if (tuGrupoUsuariosAutomatico.isEstadoOperacion()) {
            bean.getObjEstados().setTuGrupoAutomaticoId(tuGrupoUsuariosAutomatico.getTuGrupo().getId());
            if (tuGrupoUsuariosAutomatico.getTuGrupoUsuarioAbogado() != null && tuGrupoUsuariosAutomatico.getTuGrupoUsuarioAbogado().getId() != null) {

                bean.getObjEstados().setAbogadoGnUsuarioId(tuGrupoUsuariosAutomatico.getTuGrupoUsuarioAbogado().getUsuario());
                bean.getListaAbogados().add(tuGrupoUsuariosAutomatico.getTuGrupoUsuarioAbogado().getUsuario());

            }
            if (tuGrupoUsuariosAutomatico.getTuGrupoUsuarioGestor() != null && tuGrupoUsuariosAutomatico.getTuGrupoUsuarioGestor().getId() != null) {
                bean.getObjEstados().setGestorGnUsuarioId(tuGrupoUsuariosAutomatico.getTuGrupoUsuarioGestor().getUsuario());
                bean.getListaGestores().add(tuGrupoUsuariosAutomatico.getTuGrupoUsuarioGestor().getUsuario());

            }
            if (tuGrupoUsuariosAutomatico.getTuGrupoUsuarioMedico() != null && tuGrupoUsuariosAutomatico.getTuGrupoUsuarioMedico().getId() != null) {
                bean.getObjEstados().setMedicoGnUsuarioId(tuGrupoUsuariosAutomatico.getTuGrupoUsuarioMedico().getUsuario());
                bean.getListaMedicos().add(tuGrupoUsuariosAutomatico.getTuGrupoUsuarioMedico().getUsuario());
            }
        } else {
            bean.addError("No se ha encontrado un reparto automático para este estado: " + bean.getObjEstados().getMaeEstadoValor());
        }
    }

    public void agisnacionAdicionalAutomaticoGrupoAnterioEstado(TuTutelaEstado TuTutelaEstadoAnterior, TuTutelaBean bean) {
        if (TuTutelaEstadoAnterior.getGestorGnUsuarioId() != null) {
            bean.getObjEstados().setResponsableGnUsuarioId(TuTutelaEstadoAnterior.getGestorGnUsuarioId());
        }
        if (TuTutelaEstadoAnterior.getAbogadoGnUsuarioId() != null) {
            bean.getObjEstados().setAbogadoGnUsuarioId(TuTutelaEstadoAnterior.getAbogadoGnUsuarioId());
            bean.getListaAbogados().add(TuTutelaEstadoAnterior.getAbogadoGnUsuarioId());
        }
        if (TuTutelaEstadoAnterior.getGestorGnUsuarioId() != null) {
            bean.getObjEstados().setGestorGnUsuarioId(TuTutelaEstadoAnterior.getGestorGnUsuarioId());
            bean.getListaGestores().add(TuTutelaEstadoAnterior.getGestorGnUsuarioId());
        }
        if (TuTutelaEstadoAnterior.getMedicoGnUsuarioId() != null) {
            bean.getObjEstados().setMedicoGnUsuarioId(TuTutelaEstadoAnterior.getMedicoGnUsuarioId());
            bean.getListaMedicos().add(TuTutelaEstadoAnterior.getMedicoGnUsuarioId());
        }

    }
    
    @Override
    public void generarDocumentoMemorial(TuTutelaBean bean) {
      
        try {
            bean.getDocumentoMemorial().setTuTutelas(getTutelaRemoto().consultar(bean.getObjeto().getId()));
            bean.getDocumentoMemorial().setTuTutelasEstados(getTuEstadoRemoto().consultar(bean.getObjEstados().getId()));
            bean.getDocumentoMemorial().setTuPersonaApoderado(getTuMemorialPersonaRemoto().consultar(bean.getDocumentoMemorial().getTuPersonaApoderado().getId()));
            bean.getDocumentoMemorial().setTuPersonaAsistente(getTuMemorialPersonaRemoto().consultar(bean.getDocumentoMemorial().getTuPersonaAsistente().getId()));
            bean.getDocumentoMemorial().setTuMemorialFirma(getTuMemorialFirmaRemoto().consultarMemorialFirmaPorPersonal(bean.getDocumentoMemorial().getTuPersonaApoderado().getId()));
            Maestro referencia = bean.getHashReferencias().get(bean.getDocumentoMemorial().getMaeReferenciaId());
            if(referencia != null){
                bean.getDocumentoMemorial().setMaeReferenciaCodigo(referencia.getValor());
                bean.getDocumentoMemorial().setMaeReferenciaValor(referencia.getDescripcion());
            }
            
            for(Integer idMaestro: bean.getDocumentoMemorial().getListaFiltrosArgumentos()){
                Maestro argumentos = bean.getHashArgumentos().get(idMaestro);
                if(argumentos != null){
                    bean.getDocumentoMemorial().getArgumentos().add(argumentos.getDescripcion());
                }
            }
            
            for(Integer idMaestro: bean.getDocumentoMemorial().getListaFiltrosPretensiones()){
                Maestro pretensiones = bean.getHashPretensiones().get(idMaestro);
                if(pretensiones != null){
                    bean.getDocumentoMemorial().getPretensiones().add(pretensiones.getDescripcion());
                }
            }
            
        } catch (Exception e) {
            bean.addError("No se ha podido generar docuemento memorial ");
        }
       
    }
    
    @Override
    public void cargaInial(TuTutelaBean bean) {
        try {
            bean.setListaTiposDocumentoEmpresa(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));

            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            bean.setHashTiposDocumento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));

            bean.setListaTipoEstadoPersona(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            bean.setHashTipoEstadoPersona(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));

            bean.setListaSexo(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_SEXO));
            bean.setHashSexo(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_SEXO));

            bean.setListaEstadoProceso(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_TUTELA_PROCESO));
            bean.setHashEstadoProceso(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.TU_TUTELA_PROCESO));

            bean.setListaClaseSancion(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_CLASE_SANCION));
            bean.setHashClaseSancion(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.TU_CLASE_SANCION));

            bean.setListaClaseArresto(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_CLASE_ARRESTO));
            bean.setHashClaseArresto(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.TU_CLASE_ARRESTO));

            bean.setListaEstadoFallo(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_TIPO_FALLO));
            bean.setHashEstadoFallo(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.TU_TIPO_FALLO));

            bean.setListaSmlv(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_TUTELA_SMLV));
            bean.setHashSmlv(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.TU_TUTELA_SMLV));

            bean.setListaTipoPresentacion(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_SERVICIO_TIPO_PRESTACION));
            bean.setHashTipoPresentacion(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.TU_SERVICIO_TIPO_PRESTACION));

            bean.setListaPresentacion(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_SERVICIO_PRESENTACION));
            bean.setHashPresentacion(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.TU_SERVICIO_PRESENTACION));

            bean.setListaTutelaCausa(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_PRESTACION_CAUSA));
            bean.setHashTutelaCausa(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.TU_PRESTACION_CAUSA));

            //bean.setUbicaciones(getUbicacionRemoto().consultarPorTipo(Ubicacion.TIPO_MUNICIPIO));
            //bean.setUbicacionesRecursiva(getUbicacionRemoto().consultarMunicipios());

            bean.setUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());
            bean.setUbicacionesRecursiva(UbicacionSingle.getInstance().getHashMunicipios());
            
            bean.setListaEstadoTutela(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_TUTELA_ESTADO));
            bean.setHashEstadoTutela(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.TU_TUTELA_ESTADO));
            
            //2024-06-04 jyperez adición lista tipo servicio
            bean.setListaTipoServicio(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_PRESTACION_TIPO_SERVICIO));
            bean.setHashTipoServicio(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.TU_PRESTACION_TIPO_SERVICIO));
            //2024-06-04 jyperez adición lista tipo servicio
            bean.setListaTipoAsignacion(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_TIPO_ASIGNACION_ITEM));
            bean.setHashTipoAsignacion(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.TU_TIPO_ASIGNACION_ITEM));
            bean.setListaEstadoSolicitud(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_ESTADO_ASIGNACION_ITEM));
            bean.setHashEstadoSolicitud(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.TU_ESTADO_ASIGNACION_ITEM));
            bean.setHashValorEstadoSolicitud(getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.TU_ESTADO_ASIGNACION_ITEM));
             
            bean.setListaUsuariosRepresentanteDemandado(getTuRepresentanteRemoto().consultarRepresentantesActivos());

        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private List<TuGrupoUsuario> listarTuGrupoUsuarios(ParamConsulta paramConsulta) {
        List<TuGrupoUsuario> tuGrupoUsuario;
        try {
            tuGrupoUsuario = getTuGrupoUsuarioRemoto().consultarListaPorGrupo(paramConsulta);
        } catch (Exception ex) {
            tuGrupoUsuario = new ArrayList<>();
        }
        return tuGrupoUsuario;
    }

    private void listarTuPersonaContactos(TuTutelaBean bean) {
        ParamConsulta paramConsulta = new ParamConsulta();
        try {
            if (bean.getTuPersonaContacto().getTuPersona().getId() != null) {
                paramConsulta.setParametroConsulta2(bean.getTuPersonaContacto().getTuPersona().getId());
                bean.setListaTuPersonaContacto(getTuPersonaContactoRemoto().consultarLista(paramConsulta));
            }
        } catch (Exception ex) {
            bean.setListaTuPersonaContacto(new ArrayList<>());
        }
    }

    private void listarUsuarioGrupo(TuTutelaBean bean) {
        ParamConsulta paramConsulta = new ParamConsulta();
        try {
            paramConsulta.setParametroConsulta3(true);
            paramConsulta.setParametroConsulta4(bean.getModicarResponsable().getMaeEstadoId());
            bean.setListaUsuarioGrupos(listarTuGrupoUsuarios(paramConsulta));

        } catch (Exception ex) {
            bean.setListaUsuarioGrupos(new ArrayList<>());
        }
    }

    private void consultarlistarTuGrupoUsuarioEnviar(TuTutelaBean bean) {
        try {
            List<TuGrupoUsuario> tuGrupoUsuarios = new ArrayList<>();

            int idCriterioGrupoUsuarios = consultarCriterioGrupoUsuarios(bean);

            ParamConsulta paramConsulta = new ParamConsulta();
            if (TuTutelaBean.TIPO_ENVIO_SEGUIMIENTO_ASIGNAR == bean.getObjEstadosSeguimiento().getTipoSeguimientoEnviarAsignacion()
                    && idCriterioGrupoUsuarios > 0) {
                paramConsulta.setParametroConsulta3(true);
                paramConsulta.setParametroConsulta4(idCriterioGrupoUsuarios);
                tuGrupoUsuarios = listarTuGrupoUsuarios(paramConsulta);
            }

            if (TuTutelaBean.TIPO_ENVIO_SEGUIMIENTO_CIERRE_PARCIAL == bean.getObjEstadosSeguimiento().getTipoSeguimientoEnviarAsignacion()
                    && idCriterioGrupoUsuarios > 0) {
                paramConsulta.setParametroConsulta1(idCriterioGrupoUsuarios);
                paramConsulta.setParametroConsulta3(true);
                tuGrupoUsuarios = listarTuGrupoUsuarios(paramConsulta);
            }
            bean.setListaTuGrupoUsuarios(tuGrupoUsuarios);
            if (tuGrupoUsuarios.isEmpty()) {
                bean.addError("No se han encontrado tipos de envio.");
            }
        } catch (Exception ex) {
        }
    }

    private void consultarHistorialPersona(TuTutelaBean bean) {
        try {
            bean.getParamConsulta(7).setCantidadRegistros(getTuPersonaRemoto().consultarCantidadLista(bean.getParamConsulta(7)));
            bean.setRegistrosHistorialPersona(getTuPersonaRemoto().consultarLista(bean.getParamConsulta(7)));
        } catch (Exception ex) {
        }
    }

    private int consultarCriterioGrupoUsuarios(TuTutelaBean bean) {
        int tipoConsulta = 0;
        try {
            int idUsuario = bean.getConexion().getUsuario().getId();
            if (idUsuario > 0) {
                ParamConsulta paramConsulta = new ParamConsulta();
                paramConsulta.setParametroConsulta5(idUsuario);
                paramConsulta.setParametroConsulta3(true);
                List<TuGrupoUsuario> tuGrupoUsuarios = listarTuGrupoUsuarios(paramConsulta);
                if (!tuGrupoUsuarios.isEmpty()) {
                    TuGrupoUsuario tuGrupoUsuario = tuGrupoUsuarios.get(0);
                    if (TuTutelaBean.TIPO_ENVIO_SEGUIMIENTO_CIERRE_PARCIAL
                            == bean.getObjEstadosSeguimiento().getTipoSeguimientoEnviarAsignacion()) {
                        tipoConsulta = tuGrupoUsuario.getTuGrupo().getId();
                    }

                    if (TuTutelaBean.TIPO_ENVIO_SEGUIMIENTO_ASIGNAR
                            == bean.getObjEstadosSeguimiento().getTipoSeguimientoEnviarAsignacion()) {
                        tipoConsulta = tuGrupoUsuario.getIdMaEstadoGrupo();
                    }
                }
            }
        } catch (Exception ex) {
            tipoConsulta = 0;
        }
        return tipoConsulta;
    }

    private void actualizarResponsableUsuario(TuSeguimiento tuSeguimiento, TuTutelaEstado tuTutelaEstado, TuTutelaBean bean) {
        try {
            if (tuSeguimiento.getTipoSeguimientoEnviarAsignacion() > 0
                    && tuSeguimiento.getIdUsuarioSeguimientoEnviarAsignacion() > 0) {
                TuTutelaEstado tuTutelaEstadoNuevo = new TuTutelaEstado();
                tuTutelaEstadoNuevo.setId(tuTutelaEstado.getId());
                tuTutelaEstadoNuevo.setResponsableGnUsuarioId(new Usuario(tuSeguimiento.getIdUsuarioSeguimientoEnviarAsignacion()));
                getTuEstadoRemoto().actualizarResponsableGnUsuario(tuTutelaEstadoNuevo);
            }
        } catch (Exception e) {
            bean.addError("No pudo actualizar el responsable");
        }
    }

    private void actualizarEstadoProceso(TuSeguimiento tuSeguimiento, TuTutelaEstado tuTutelaEstado, TuTutelaBean bean) {
        try {
            boolean cambioTipoProceso = false;
            TuTutelaEstado tuTutelaEstadoNuevo = new TuTutelaEstado();
            tuTutelaEstadoNuevo.setId(tuTutelaEstado.getId());
            Maestro tipoSeguimiento = getMaestroRemoto().consultar(tuSeguimiento.getMaeTipoSeguimientoId());
            if (tipoSeguimiento != null) {
                if (tipoSeguimiento.contieneAccion(MaestroAccion.TU_TUTELA_TIPO_SEGUMIENTO_CUMPLIMIENTO)) {
                    Maestro proceso = bean.getHashEstadoProceso().get(Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.TIPO_PROCESO_CUMPLIMIENTO)));
                    tuTutelaEstadoNuevo.setMaeProcesoId(Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.TIPO_PROCESO_CUMPLIMIENTO)));
                    if (proceso != null) {
                        tuTutelaEstadoNuevo.setMaeProcesoCodigo(proceso.getValor());
                        tuTutelaEstadoNuevo.setMaeProcesoValor(proceso.getNombre());
                    }
                    cambioTipoProceso = true;
                } else if (tipoSeguimiento.contieneAccion(MaestroAccion.TU_TUTELA_TIPO_SEGUMIENTO_ARCHIVADO)) {
                    Maestro proceso = bean.getHashEstadoProceso().get(Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.TIPO_PROCESO_ARCHIVADO)));
                    tuTutelaEstadoNuevo.setMaeProcesoId(Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.TIPO_PROCESO_ARCHIVADO)));
                    if (proceso != null) {
                        tuTutelaEstadoNuevo.setMaeProcesoCodigo(proceso.getValor());
                        tuTutelaEstadoNuevo.setMaeProcesoValor(proceso.getNombre());
                    }
                    cambioTipoProceso = true;
                } else if (tipoSeguimiento.contieneAccion(MaestroAccion.TU_TUTELA_TIPO_SEGUMIENTO_CIERRE_PARCIAL)) {
                    Maestro proceso = bean.getHashEstadoProceso().get(Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.TIPO_PROCESO_CIERRE_PARCIAL)));
                    tuTutelaEstadoNuevo.setMaeProcesoId(Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.TIPO_PROCESO_CIERRE_PARCIAL)));
                    if (proceso != null) {
                        tuTutelaEstadoNuevo.setMaeProcesoCodigo(proceso.getValor());
                        tuTutelaEstadoNuevo.setMaeProcesoValor(proceso.getNombre());
                    }
                    cambioTipoProceso = true;
                } else if (tipoSeguimiento.contieneAccion(MaestroAccion.TU_TUTELA_TIPO_SEGUMIENTO_GESTION_GENERAL_E_JUZGADO_E_REPARTO)) {
                    Maestro proceso = bean.getHashEstadoProceso().get(Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.TIPO_PROCESO_EN_TRAMITE)));
                    tuTutelaEstadoNuevo.setMaeProcesoId(Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.TIPO_PROCESO_EN_TRAMITE)));
                    if (proceso != null) {
                        tuTutelaEstadoNuevo.setMaeProcesoCodigo(proceso.getValor());
                        tuTutelaEstadoNuevo.setMaeProcesoValor(proceso.getNombre());
                    }
                    cambioTipoProceso = true;
                } else if (tipoSeguimiento.contieneAccion(MaestroAccion.TU_TUTELA_TIPO_SEGUMIENTO_INACTIVO)) {
                    Maestro proceso = bean.getHashEstadoProceso().get(Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.TIPO_PROCESO_INACTIVO)));
                    tuTutelaEstadoNuevo.setMaeProcesoId(Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.TIPO_PROCESO_INACTIVO)));
                    if (proceso != null) {
                        tuTutelaEstadoNuevo.setMaeProcesoCodigo(proceso.getValor());
                        tuTutelaEstadoNuevo.setMaeProcesoValor(proceso.getNombre());
                    }
                    cambioTipoProceso = true;
                }
            }
            if (cambioTipoProceso) {
                getTuEstadoRemoto().actualizarProcesoEstado(tuTutelaEstadoNuevo);
            }
        } catch (Exception e) {
            bean.addError("No pudo actualizar el proceso");
        }
    }

    @Override
    public List<TuTutelaRespuesta> consultarTutelaPorDocumento(String tipoDocumento, String numeroDocumento, Integer idEstadoFallo) {
        List<TuTutelaRespuesta> tutelas = new ArrayList<>();
        try {
            if (tipoDocumento != null && numeroDocumento != null) {
                tutelas = getTutelaRemoto().consultarConExoneracionAfiliado(tipoDocumento, numeroDocumento, idEstadoFallo);
            }
        } catch (Exception e) {
            tutelas = new ArrayList<>();
        }
        return tutelas;
    }

    @Override
    public void consultarMaestroCausaTutelaPorTipoPresentacion(TuTutelaBean bean) {
        try {
            bean.setListaTutelaCausa(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.TU_PRESTACION_CAUSA, bean.getObjTutulaItem().getMaeTipoPrestacionId()));
            bean.setHashTutelaCausa(Util.convertToHash(bean.getListaTutelaCausa()));
        } catch(Exception ex) {
            //cargamos las listas vacias si ocurre algun error.
            bean.setListaTutelaCausa(new ArrayList());
            bean.setHashTutelaCausa(new HashMap());
        }
    }
}
