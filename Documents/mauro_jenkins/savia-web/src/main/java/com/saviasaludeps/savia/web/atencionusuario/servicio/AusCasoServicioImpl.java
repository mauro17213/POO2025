/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.atencionusuario.servicio;

import com.saviasaludeps.savia.dominio.administracion.GnCorreoEnvio;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersona;
import com.saviasaludeps.savia.dominio.atencionusuario.AusAdjunto;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCaso;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCasoServicio;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersonaTelefono;
import com.saviasaludeps.savia.dominio.atencionusuario.AusSeguimiento;
import com.saviasaludeps.savia.dominio.atencionusuario.AusServicioGestionHistorico;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.negocio.administracion.GnCorreoEnvioRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusCasoRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusCasoServicioRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusPersonaRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusSedeRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusSeguimientoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusServicioGestionHistoricoRemoto;
import com.saviasaludeps.savia.web.atencionusuario.bean.AusCasoBean;
import com.saviasaludeps.savia.web.atencionusuario.bean.AusPersonaBean;
import com.saviasaludeps.savia.web.atencionusuario.utilities.PropAtencionUsuario;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
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
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Jhonatan Jimenez
 */
public class AusCasoServicioImpl extends AccionesBO implements AusCasoServicioIface {

    private AusPersonaRemoto getPersonaRemoto() throws Exception {
        return (AusPersonaRemoto) RemotoEJB.getEJBRemoto("AusPersonaServicio", AusPersonaRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
        return (MaestroRemoto) object;
    }

    private AusCasoRemoto getCasoRemoto() throws Exception {
        return (AusCasoRemoto) RemotoEJB.getEJBRemoto("AusCasoServicio", AusCasoRemoto.class.getName());
    }

    private AusSedeRemoto getSedeRemoto() throws Exception {
        return (AusSedeRemoto) RemotoEJB.getEJBRemoto("AusSedeServicio", AusSedeRemoto.class.getName());
    }

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    private AusCasoServicioRemoto getServicioRemoto() throws Exception {
        return (AusCasoServicioRemoto) RemotoEJB.getEJBRemoto("AusCasoServicioServicio", AusCasoServicioRemoto.class.getName());
    }

    private AusAdjuntoRemoto getAdjuntoRemoto() throws Exception {
        return (AusAdjuntoRemoto) RemotoEJB.getEJBRemoto("AusAdjuntoServicio", AusAdjuntoRemoto.class.getName());
    }

    private AusSeguimientoRemoto getSeguimientoRemoto() throws Exception {
        return (AusSeguimientoRemoto) RemotoEJB.getEJBRemoto("AusSeguimientoServicio", AusSeguimientoRemoto.class.getName());
    }

    private AusServicioGestionHistoricoRemoto getServicioGestionRemotoRemoto() throws Exception {
        return (AusServicioGestionHistoricoRemoto) RemotoEJB.getEJBRemoto("AusServicioGestionHistoricoServicio", AusServicioGestionHistoricoRemoto.class.getName());
    }

    private AusServicioGestionHistoricoRemoto getServicioGestionHistoricoRemotoRemoto() throws Exception {
        return (AusServicioGestionHistoricoRemoto) RemotoEJB.getEJBRemoto("AusServicioGestionHistoricoServicio", AusServicioGestionHistoricoRemoto.class.getName());
    }
//
//    private ParametroRemoto getParametroRemoto() throws Exception {
//        return (ParametroRemoto) RemotoEJB.getEJBRemoto("ParametroServicio", ParametroRemoto.class.getName());
//    }

    private CntPrestadorRemoto getCntPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    private GnCorreoEnvioRemoto getGnCorreoEnvioRemoto() throws Exception {
        return (GnCorreoEnvioRemoto) RemotoEJB.getEJBRemoto("GnCorreoEnvioServicio", GnCorreoEnvioRemoto.class.getName());
    }

    @Override
    public void Accion(AusCasoBean bean) {
        if (super.ValidarSesion(bean)) {
            bean.getObjeto().setEmpresa(bean.getConexion().getEmpresa());
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listar(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            actualizarListaCie(bean);
                            break;
                        case Url.ACCION_EDITAR:
                            actualizarListaCie2(bean);
                            break;
                        case AusCasoBean.ACCION_BUSCAR_SEDES:
                            listarSedesPrescriptora(bean);
                            break;
                        case AusCasoBean.ACCION_BUSCAR_PERSONAS:
                            listarPersonasHistorial(bean);
                            break;
                        default:
                            break;
                    }
                    break;
                case Url.ACCION_VER:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            ver(bean);
                            break;
                        case AusCasoBean.ACCION_VER_SERVICIO:
                            verServicio(bean);
                            break;
                        case AusCasoBean.ACCION_VER_SEGUIMIENTO:
                            verSeguimiento(bean);
                            break;
                    }
                    break;
                case AusCasoBean.ACCION_BUSCAR_TELEFONOS_PERSONA:
                    verTelefonosPersona(bean);
                    break;
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            editar(bean);
                            break;
                        case AusCasoBean.ACCION_VER_SERVICIO:
                            verServicio(bean);
                            break;
                        case AusCasoBean.ACCION_DUPLICAR_SERVICIO:
                            editarServicio(bean);
                            break;
                        case AusCasoBean.ACCION_EDITAR_SEGUIMIENTO:
                            editarSeguimiento(bean);
                            break;
                    }
                    break;
                case Url.ACCION_MODIFICAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_MODIFICAR:
                            modificar(bean);
                            break;
                        case AusCasoBean.ACCION_MODIFICAR_SERVICIO:
                            modificarServicio(bean);
                            break;
                        case AusCasoBean.ACCION_MODIFICAR_SEGUIMIENTO:
                            modificarSeguimiento(bean);
                            break;
                    }
                    break;
                case Url.ACCION_BORRAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_BORRAR:
                            borrar(bean);
                            break;
                        case AusCasoBean.ACCION_BORRAR_SERVICIO:
                            borrarServicio(bean);
                            break;
                        
                    }
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_1:
                            verGestion(bean);
                            break;
                        case AusCasoBean.ACCION_CERRAR_CASO_AUTOMATICO:
                            cierreAutomaticoCaso(bean);
                            break;
                        case AusCasoBean.ACCION_DUPLICAR_SERVICIO:
                            editarServicio(bean);
                            break;
                        case AusCasoBean.ACCION_MODIFICAR_GESTION:
                            modificarGestion(bean);
                            break;
                        case AusCasoBean.ACCION_MODIFICAR_SERVICIO:
                            modificarServicio(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                case Url.ACCION_ADICIONAL_3:
                    verServiciosResueltos(bean);
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_4:
                            crearRevertirCaso(bean);
                            break;
                        case AusCasoBean.ACCION_REVERTIR_CASO:
                            revertirCaso(bean);
                            break;

                    }
                    break;
                case Url.ACCION_ADICIONAL_5:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_5:
                            crearCerrarCaso(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarCerrarCaso(bean);
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

    private void listar(AusCasoBean bean) {
        try {
            //Permite filtrar por todos casos propios si no tiene adicional
            if (bean.getParamConsulta().getFiltros() == null) {
                bean.getParamConsulta().setFiltros(new HashMap());
            }
            if (bean.getParamConsulta().getFiltros().isEmpty()) {
                if (!bean.isAccionAdicional3()) {
//                    bean.getParamConsulta().setParametroConsulta3(bean.getConexion().getUsuario().getId());
                }
            }

            if (!bean.isAccionAdicional2()) {
                bean.getParamConsulta().setParametroConsulta2(bean.getConexion().getUsuario().getId());
            }
            bean.getParamConsulta().setEmpresaId(bean.getConexion().getEmpresa().getId());
            bean.getParamConsulta().setCantidadRegistros(getCasoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            List<AusCaso> ausCasos = getCasoRemoto().consultarLista(bean.getParamConsulta());
            List<AusCaso> ausNewCasos = new ArrayList<>();
            if (bean.isIsListarVencidos()) {
                for (AusCaso ausCaso : ausCasos) {
                    ausCaso = getCasoRemoto().consultar(ausCaso.getId());
                    List<AusCasoServicio> servicios = ausCaso.getServiciosList();
                    //int idCerrado = bean.obtenerEstadoServicio("Cerrado");
                    int idCerrado = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO));
                    //int idResuelto = bean.obtenerEstadoServicio("Resuelto");
                    int idResuelto = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_RESUELTO));
                    boolean validar = true;
                    if (servicios.size() > 0) {
                        for (AusCasoServicio service : servicios) {
                            if (service.getMaeEstadoId() != idCerrado && service.getMaeEstadoId() != idResuelto) {
                                validar = false;
                            }
                        }
                    } else {
                        validar = false;
                    }

                    if (validar) {
                        ausNewCasos.add(ausCaso);
                    }
                }
                bean.setRegistros(ausNewCasos);
            } else {
                bean.setRegistros(ausCasos);
                //bean.getParamConsulta().setParametroConsulta3(null);
                //bean.getParamConsulta().setParametroConsulta2(null);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(AusCasoBean bean) {
        try {
            bean.setObjeto(getCasoRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setServiciosList(getServicioRemoto().consultarServiciosTodoServicios(bean.getObjeto().getId()));
            if (bean.getObjeto().getAsuPersonasId() != null) {
                //List<AusPersonaTelefono> listaTelefonosPersona = getPersonaRemoto().consultarTelefonosPorAusPersona(bean.getObjeto().getAsuPersonasId().getId());
                List<AusPersonaTelefono> listaTelefonosPersona = getPersonaRemoto().consultarTelefonosPersonas(bean.getObjeto().getAsuPersonasId().getDocumento());
                bean.getObjeto().getAsuPersonasId().setListaTelefonos(listaTelefonosPersona);
            }
            bean.precargaPeticionario();
            /*if(bean.getObjeto().getPeticionario() != null){
                //List<AusPersonaTelefono> listaTelefonosPersona = getPersonaRemoto().consultarTelefonosPorAusPersona(bean.getObjeto().getPeticionario().getId());
                List<AusPersonaTelefono> listaTelefonosPersona = getPersonaRemoto().consultarTelefonosPersonas(bean.getObjeto().getPeticionario().getDocumento());
                bean.getObjeto().getPeticionario().setListaTelefonos(listaTelefonosPersona);
            }*/

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void verGestion(AusCasoBean bean) {
        try {
            bean.setObjeto(getCasoRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setServiciosList(getServicioRemoto().consultarServiciosTodoServicios(bean.getObjeto().getId()));
            if (bean.getObjeto().getAsuPersonasId() != null) {
                List<AusPersonaTelefono> listaTelefonosPersona = getPersonaRemoto().consultarTelefonosPersonas(bean.getObjeto().getAsuPersonasId().getDocumento());
                bean.getObjeto().getAsuPersonasId().setListaTelefonos(listaTelefonosPersona);
            }
            bean.precargaPeticionario();
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void verTelefonosPersona(AusCasoBean bean) {
        try {
            if (bean.getObjeto().getAsuPersonasId() != null) {
                List<AusPersonaTelefono> listaTelefonosPersona = getPersonaRemoto().consultarTelefonosPersonas(bean.getObjeto().getAsuPersonasId().getDocumento());
                bean.getObjeto().getAsuPersonasId().setListaTelefonos(listaTelefonosPersona);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(AusCasoBean bean) {
        try {
            bean.setObjeto(new AusCaso());

            bean.setListaTecnologiaAltoCosto(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_TECNOLOGIA_ALTO_COSTO));
            bean.setHashTecnologiaAltoCosto(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_TECNOLOGIA_ALTO_COSTO));

            bean.setListaMotivo(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_CASO_MOTIVO_ESPECIFICO));
            bean.setHashMotivo(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_CASO_MOTIVO_ESPECIFICO));

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(AusCasoBean bean) {
        try {
            AusCaso caso = getCasoRemoto().consultar(bean.getObjeto().getId());
            bean.setObjeto(caso);
            bean.getObjeto().setServiciosList(getServicioRemoto().consultarServiciosTodoServicios(bean.getObjeto().getId()));
            if (bean.getObjeto().getAsuPersonasId() != null) {
                List<AusPersonaTelefono> listaTelefonosPersona = getPersonaRemoto().consultarTelefonosPersonas(bean.getObjeto().getAsuPersonasId().getDocumento());
                bean.getObjeto().getAsuPersonasId().setListaTelefonos(listaTelefonosPersona);
            }
            bean.precargaPeticionario();
            /*if(bean.getObjeto().getPeticionario() != null){
                List<AusPersonaTelefono> listaTelefonosPersona = getPersonaRemoto().consultarTelefonosPersonas(bean.getObjeto().getPeticionario().getDocumento());
                bean.getObjeto().getPeticionario().setListaTelefonos(listaTelefonosPersona);
            }*/
            bean.setListaTecnologiaAltoCosto(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_TECNOLOGIA_ALTO_COSTO));
            bean.setHashTecnologiaAltoCosto(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_TECNOLOGIA_ALTO_COSTO));
            bean.setListaMotivo(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_CASO_MOTIVO_ESPECIFICO));
            bean.setHashMotivo(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_CASO_MOTIVO_ESPECIFICO));
            if (bean.getObjeto().getMaeTipoMotivoEspecificoId() != null) {
                bean.setListaTipoMotivo(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.AUS_CASO_TIPO_MOTIVO_ESPECIFICO, bean.getObjeto().getMaeMotivoEspecificoId()));
                bean.setHashTipoMotivo(convertToHashMaestroTipoMotivo(bean.getListaTipoMotivo()));
            }

            if (bean.getObjeto().getMaeSubtipoMotivoEspecificoId() != null) {
                bean.setListaSubMotivo(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.AUS_CASO_SUBTIPO_MOTIVO_ESPECIFICO, bean.getObjeto().getMaeTipoMotivoEspecificoId()));
                bean.setHashSubMotivo(convertToHashMaestroSubTipoMotivo(bean.getListaSubMotivo()));
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(AusCasoBean bean) {
        try {
            int idCaso = 0;
            AusCaso ausCaso = bean.getObjeto();
            AusPersona ausPersona = ausCaso.getAusPersonasId();
            //ausPersona.setEmpresa(bean.getConexion().getEmpresa());
            bean.auditoriaGuardar(ausCaso);
            Maestro tipoDocumento = bean.getHashTiposDocumento().get(ausPersona.getMae_tipo_documento_id());
            if (tipoDocumento != null) {
                ausPersona.setMae_tipo_documento_codigo(tipoDocumento.getValor());
                ausPersona.setMae_tipo_documento_valor(tipoDocumento.getNombre());
            }
            Maestro sexo = bean.getHashSexo().get(ausPersona.getMae_sexo_id());
            if (sexo != null) {
                ausPersona.setMae_sexo_codigo(sexo.getValor());
                ausPersona.setMae_sexo_valor(sexo.getNombre());
            }
            Maestro estadoPersona = bean.getHashTipoEstadosPersona().get(ausPersona.getMae_estado_id());
            if (estadoPersona != null) {
                ausPersona.setMae_estado_codigo(estadoPersona.getValor());
                ausPersona.setMae_estado_valor(estadoPersona.getNombre());
            }

            if (ausPersona.getId() == null || ausPersona.getId() <= 0) {
                bean.auditoriaGuardar(ausPersona);
                int idPersona = getPersonaRemoto().insertar(ausPersona);
                ausPersona.setId(idPersona);
            } else {
                bean.auditoriaGuardar(ausPersona);
                ausPersona.setId(null);
                int idPersona = getPersonaRemoto().insertar(ausPersona);
                ausPersona.setId(idPersona);
                //bean.auditoriaModificar(ausPersona);
                //getPersonaRemoto().actualizar(ausPersona);
            }
            AusPersona ausPeticionario = new AusPersona();
            if (bean.getPeticionario().equals("Si")) {
                ausPeticionario = ausCaso.getPeticionario();
                //peticionario.setEmpresa(bean.getConexion().getEmpresa());
                bean.auditoriaGuardar(ausCaso);
                Maestro tipoDocumentoPeticionario = bean.getHashTiposDocumento().get(ausPeticionario.getMae_tipo_documento_id());
                if (tipoDocumentoPeticionario != null) {
                    ausPeticionario.setMae_tipo_documento_codigo(tipoDocumentoPeticionario.getValor());
                    ausPeticionario.setMae_tipo_documento_valor(tipoDocumentoPeticionario.getNombre());
                }
                Maestro sexoPeticionario = bean.getHashSexo().get(ausPeticionario.getMae_sexo_id());
                if (sexoPeticionario != null) {
                    ausPeticionario.setMae_sexo_codigo(sexoPeticionario.getValor());
                    ausPeticionario.setMae_sexo_valor(sexoPeticionario.getNombre());
                }
                if (ausPeticionario.getId() == null || ausPeticionario.getId() <= 0) {
                    bean.auditoriaGuardar(ausPeticionario);
                    int idPersona = getPersonaRemoto().insertar(ausPeticionario);
                    ausPeticionario.setId(idPersona);
                } else {
                    //bean.auditoriaModificar(ausPeticionario);
                    //getPersonaRemoto().actualizar(ausPeticionario);
                    bean.auditoriaGuardar(ausPeticionario);
                    ausPeticionario.setId(null);
                    int idPersona = getPersonaRemoto().insertar(ausPeticionario);
                    ausPeticionario.setId(idPersona);
                }
            }
            if (ausPersona.getId() > 0) {
                Maestro estadoCaso = bean.getHashTipoEstadoSolicitud().get(ausCaso.getMaeSolicitudEstadoId());
                if (estadoCaso != null) {
                    ausCaso.setMaeSolicitudEstadoCodigo(estadoCaso.getValor());
                    ausCaso.setMaeSolicitudEstadoValor(estadoCaso.getNombre());
                }
                Maestro tipoSolicitudCaso = bean.getHashTipoSolicitud().get(ausCaso.getMaeSolicitudTipoId());
                if (tipoSolicitudCaso != null) {
                    ausCaso.setMaeSolicitudTipoCodigo(tipoSolicitudCaso.getValor());
                    ausCaso.setMaeSolicitudTipoValor(tipoSolicitudCaso.getNombre());
                }
                Maestro tipoSolicitudOrigenCaso = bean.getHashTipoSolicitudOrigen().get(ausCaso.getMaeSolicitudOrigenId());
                if (tipoSolicitudOrigenCaso != null) {
                    ausCaso.setMaeSolicitudOrigenCodigo(tipoSolicitudOrigenCaso.getValor());
                    ausCaso.setMaeSolicitudOrigenValor(tipoSolicitudOrigenCaso.getNombre());
                }
                Maestro tipoSolicitudPrioridadCaso = bean.getHashTipoSolicitudPrioridad().get(ausCaso.getMaeSolicitudPrioridadId());
                if (tipoSolicitudPrioridadCaso != null) {
                    ausCaso.setMaeSolicitudPrioridadCodigo(tipoSolicitudPrioridadCaso.getValor());
                    ausCaso.setMaeSolicitudPrioridadValor(tipoSolicitudPrioridadCaso.getNombre());
                }
                Maestro tipoSolicitudEnteControlCaso = bean.getHashTipoSolicitudEnteControl().get(ausCaso.getMaeSolicitudEnteControlId());
                if (tipoSolicitudEnteControlCaso != null) {
                    ausCaso.setMaeSolicitudEnteControlCodigo(tipoSolicitudEnteControlCaso.getValor());
                    ausCaso.setMaeSolicitudEnteControlValor(tipoSolicitudEnteControlCaso.getNombre());
                }
                Maestro tipoSolicitudRiesgoVidalCaso = bean.getHashTipoSolicitudRiesgoVida().get(ausCaso.getMaeSolicitudRiesgoVidalId());
                if (tipoSolicitudRiesgoVidalCaso != null) {
                    ausCaso.setMaeSolicitudRiesgoVidalCodigo(tipoSolicitudRiesgoVidalCaso.getValor());
                    ausCaso.setMaeSolicitudRiesgoVidalValor(tipoSolicitudRiesgoVidalCaso.getNombre());
                }
                Maestro canalSuperSaludCaso = bean.getHashCanalSuperSalud().get(ausCaso.getMaeCanalSupersaludId());
                if (canalSuperSaludCaso != null) {
                    ausCaso.setMaeCanalSupersaludCodigo(canalSuperSaludCaso.getValor());
                    ausCaso.setMaeCanalSupersaludValor(canalSuperSaludCaso.getNombre());
                }
                Maestro tecnologiaAltoCosto = bean.getHashTecnologiaAltoCosto().get(ausCaso.getMaeTecnologiaAltoCostoId());
                if (tecnologiaAltoCosto != null) {
                    ausCaso.setMaeTecnologiaAltoCostoCodigo(tecnologiaAltoCosto.getValor());
                    ausCaso.setMaeTecnologiaAltoCostoValor(tecnologiaAltoCosto.getNombre());
                }
                Maestro motivoCaso = bean.getHashMotivo().get(ausCaso.getMaeMotivoEspecificoId());
                if (motivoCaso != null) {
                    ausCaso.setMaeMotivoEspecificoCodigo(motivoCaso.getValor());
                    ausCaso.setMaeMotivoEspecificoValor(motivoCaso.getNombre());
                }
                Maestro tipoMotivo = bean.getHashTipoMotivo().get(ausCaso.getMaeTipoMotivoEspecificoId());
                if (tipoMotivo != null) {
                    ausCaso.setMaeTipoMotivoEspecificoCodigo(tipoMotivo.getValor());
                    ausCaso.setMaeTipoMotivoEspecificoValor(tipoMotivo.getNombre());
                }
                Maestro subtipoMotivo = bean.getHashSubMotivo().get(ausCaso.getMaeSubtipoMotivoEspecificoId());
                if (subtipoMotivo != null) {
                    ausCaso.setMaeSubtipoMotivoEspecificoCodigo(subtipoMotivo.getValor());
                    ausCaso.setMaeSubtipoMotivoEspecificoValor(subtipoMotivo.getNombre());
                }
                ausCaso.setResponsableUsuariosId(bean.getConexion().getUsuario());
                //2025-04-04 jyperez se asigna como usuario de cierre al mismo usuario responsable - Corrección por Usuario
                ausCaso.setUsuarioCierre(bean.getConexion().getUsuario());
                //ausCaso.setUsuarioCierra(bean.getConexion().getUsuario().getUsuarioNombre());
                ausCaso.setCantidadServicios(ausCaso.getServiciosList().size());
                int idEstadoResuelto = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_RESUELTO));
                int idEstadoCerrado = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO));
                int cantidad = 0;
                for (AusCasoServicio servicio : ausCaso.getServiciosList()) {
                    if (servicio.getMaeEstadoId() == idEstadoResuelto || servicio.getMaeEstadoId() == idEstadoCerrado) {
                        cantidad = cantidad + 1;
                    }
                }
                ausCaso.setCantidadServiciosCerrados(cantidad);
                ausCaso.setBorrado(Boolean.FALSE);
                if (bean.getPeticionario().equals("Si")) {
                    if (ausPeticionario.getId() > 0) {
                        idCaso = getCasoRemoto().insertar(ausCaso);
                        ausCaso.setId(idCaso);
                        if (ausCaso.getId() > 0) {
                            insertarAdjuntos(ausCaso.getAdjuntosList(), bean, idCaso, 0, 0);
                            guardarSeguimientoPorDefecto(bean, ausCaso);
                            guardarSeguimientoAdicional(bean, ausCaso);
                            guardarServicios(bean, ausCaso);
                        }
                    }
                } else {
                    idCaso = getCasoRemoto().insertar(ausCaso);
                    ausCaso.setId(idCaso);

                    if (ausCaso.getId() > 0) {
                        insertarAdjuntos(ausCaso.getAdjuntosList(), bean, idCaso, 0, 0);
                        guardarSeguimientoPorDefecto(bean, ausCaso);
                        guardarSeguimientoAdicional(bean, ausCaso);
                        guardarServicios(bean, ausCaso);
                        /*String motivos = "''";
                        for (AusCasoServicio ausCasoService : ausCaso.getServiciosList()) {
                            motivos += bean.getTipoServicioMotivo(ausCasoService.getMaeServicioMotivoId()) + ",";
                        }
                        motivos = StringUtils.removeEnd(motivos, ",");
                        motivos += "''";*/
                        notificarPorCorreoCasoCreado(ausCaso.getResponsableUsuariosId(), ausCaso.getAusPersonasId(), idCaso, ausCaso.getMaeMotivoEspecificoValor());
                        //notificarPorCorreoCasoCreadoAlterno(ausCaso, idCaso, motivos);
                    }
                }

            }
            bean.addMensaje("Se creo el caso con id (" + idCaso + ") de manera exitosa \n ");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(AusCasoBean bean) {
        try {
            AusCaso ausCaso = bean.getObjeto();
            AusPersona persona = ausCaso.getAusPersonasId();
            AusPersona peticionario = ausCaso.getPeticionario();
//            persona.setEmpresa(bean.getConexion().getEmpresa());
//            peticionario.setEmpresa(bean.getConexion().getEmpresa());
            bean.auditoriaModificar(ausCaso);
            bean.auditoriaModificar(persona);
            Maestro tipoDocumento = bean.getHashTiposDocumento().get(persona.getMae_tipo_documento_id());
            if (tipoDocumento != null) {
                persona.setMae_tipo_documento_codigo(tipoDocumento.getValor());
                persona.setMae_tipo_documento_valor(tipoDocumento.getNombre());
            }
            Maestro sexo = bean.getHashSexo().get(persona.getMae_sexo_id());
            if (sexo != null) {
                persona.setMae_sexo_codigo(sexo.getValor());
                persona.setMae_sexo_valor(sexo.getNombre());
            }

            getPersonaRemoto().actualizar(persona);
            if (bean.getPeticionario().equals("Si")) {
                Maestro tipoDocumentoPeticionario = bean.getHashTiposDocumento().get(peticionario.getMae_tipo_documento_id());
                if (tipoDocumentoPeticionario != null) {
                    peticionario.setMae_tipo_documento_codigo(tipoDocumentoPeticionario.getValor());
                    peticionario.setMae_tipo_documento_valor(tipoDocumentoPeticionario.getNombre());
                }
                Maestro sexoPeticionario = bean.getHashSexo().get(peticionario.getMae_sexo_id());
                if (sexoPeticionario != null) {
                    peticionario.setMae_sexo_codigo(sexoPeticionario.getValor());
                    peticionario.setMae_sexo_valor(sexoPeticionario.getNombre());
                }
                bean.auditoriaModificar(peticionario);
                getPersonaRemoto().actualizar(peticionario);
            }
            Maestro tipoSolicitudOrigenCaso = bean.getHashTipoSolicitudOrigen().get(ausCaso.getMaeSolicitudOrigenId());
            if (tipoSolicitudOrigenCaso != null) {
                ausCaso.setMaeSolicitudOrigenCodigo(tipoSolicitudOrigenCaso.getValor());
                ausCaso.setMaeSolicitudOrigenValor(tipoSolicitudOrigenCaso.getNombre());
            }
            Maestro tipoSolicitudCaso = bean.getHashTipoSolicitud().get(ausCaso.getMaeSolicitudTipoId());
            if (tipoSolicitudCaso != null) {
                ausCaso.setMaeSolicitudTipoCodigo(tipoSolicitudCaso.getValor());
                ausCaso.setMaeSolicitudTipoValor(tipoSolicitudCaso.getNombre());
            }
            Maestro tipoSolicitudPrioridadCaso = bean.getHashTipoSolicitudPrioridad().get(ausCaso.getMaeSolicitudPrioridadId());
            if (tipoSolicitudPrioridadCaso != null) {
                ausCaso.setMaeSolicitudPrioridadCodigo(tipoSolicitudPrioridadCaso.getValor());
                ausCaso.setMaeSolicitudPrioridadValor(tipoSolicitudPrioridadCaso.getNombre());
            }
            Maestro tipoSolicitudEnteControlCaso = bean.getHashTipoSolicitudEnteControl().get(ausCaso.getMaeSolicitudEnteControlId());
            if (tipoSolicitudEnteControlCaso != null) {
                ausCaso.setMaeSolicitudEnteControlCodigo(tipoSolicitudEnteControlCaso.getValor());
                ausCaso.setMaeSolicitudEnteControlValor(tipoSolicitudEnteControlCaso.getNombre());
            }
            Maestro tipoSolicitudRiesgoVidalCaso = bean.getHashTipoSolicitudRiesgoVida().get(ausCaso.getMaeSolicitudRiesgoVidalId());
            if (tipoSolicitudRiesgoVidalCaso != null) {
                ausCaso.setMaeSolicitudRiesgoVidalCodigo(tipoSolicitudRiesgoVidalCaso.getValor());
                ausCaso.setMaeSolicitudRiesgoVidalValor(tipoSolicitudRiesgoVidalCaso.getNombre());
            }
            Maestro canalSuperSaludCaso = bean.getHashCanalSuperSalud().get(ausCaso.getMaeCanalSupersaludId());
            if (canalSuperSaludCaso != null) {
                ausCaso.setMaeCanalSupersaludCodigo(canalSuperSaludCaso.getValor());
                ausCaso.setMaeCanalSupersaludValor(canalSuperSaludCaso.getNombre());
            }
            Maestro tecnologiaAltoCosto = bean.getHashTecnologiaAltoCosto().get(ausCaso.getMaeTecnologiaAltoCostoId());
            if (tecnologiaAltoCosto != null) {
                ausCaso.setMaeTecnologiaAltoCostoCodigo(tecnologiaAltoCosto.getValor());
                ausCaso.setMaeTecnologiaAltoCostoValor(tecnologiaAltoCosto.getNombre());
            }
            Maestro motivoCaso = bean.getHashMotivo().get(ausCaso.getMaeMotivoEspecificoId());
            if (motivoCaso != null) {
                ausCaso.setMaeMotivoEspecificoCodigo(motivoCaso.getValor());
                ausCaso.setMaeMotivoEspecificoValor(motivoCaso.getNombre());
            }
            Maestro tipoMotivo = bean.getHashTipoMotivo().get(ausCaso.getMaeTipoMotivoEspecificoId());
            if (tipoMotivo != null) {
                ausCaso.setMaeTipoMotivoEspecificoCodigo(tipoMotivo.getValor());
                ausCaso.setMaeTipoMotivoEspecificoValor(tipoMotivo.getNombre());
            } else {
                ausCaso.setMaeTipoMotivoEspecificoCodigo(null);
                ausCaso.setMaeTipoMotivoEspecificoValor(null);
            }
            Maestro subtipoMotivo = bean.getHashSubMotivo().get(ausCaso.getMaeSubtipoMotivoEspecificoId());
            if (subtipoMotivo != null) {
                ausCaso.setMaeSubtipoMotivoEspecificoCodigo(subtipoMotivo.getValor());
                ausCaso.setMaeSubtipoMotivoEspecificoValor(subtipoMotivo.getNombre());
            } else {
                ausCaso.setMaeSubtipoMotivoEspecificoCodigo(null);
                ausCaso.setMaeSubtipoMotivoEspecificoValor(null);
            }
            ausCaso.setCantidadServicios(ausCaso.getServiciosList().size());
            int idEstadoResuelto = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_RESUELTO));
            int idEstadoCerrado = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO));
            int cantidad = 0;
            for (AusCasoServicio servicio : ausCaso.getServiciosList()) {
                if (servicio.getAccion() == AusCasoServicio.ACCION_INSERTAR) {
                    if (servicio.getMaeEstadoId() == idEstadoResuelto || servicio.getMaeEstadoId() == idEstadoCerrado) {
                        cantidad = cantidad + 1;
                    }
                } else if (servicio.getAccion() == AusCasoServicio.ACCION_NINGUNO) {
                    if (ausCaso.getCantidadServiciosCerrados() == null) {
                        if (servicio.getMaeEstadoId() == idEstadoResuelto || servicio.getMaeEstadoId() == idEstadoCerrado) {
                            cantidad = cantidad + 1;
                        }
                    }
                }
            }
            int cantidadServiciosCerrados = (ausCaso.getCantidadServiciosCerrados() != null) ? ausCaso.getCantidadServiciosCerrados() + cantidad : cantidad;
            ausCaso.setCantidadServiciosCerrados(cantidadServiciosCerrados);
            //2025-04-04 jyperez se asigna como usuario de cierre al mismo usuario responsable - Corrección por Usuario
            ausCaso.setUsuarioCierre(ausCaso.getResponsableUsuariosId());
            //ausCaso.setUsuarioCierra(ausCaso.getResponsableUsuariosId().getUsuarioNombre());
            getCasoRemoto().actualizar(ausCaso);
            getCasoRemoto().actualizarCantidadServicios(ausCaso);
            ausCaso.setAdjuntosList(procesarAdjuntos(ausCaso.getAdjuntosList(), bean, ausCaso.getId(), 0, 0));
            ausCaso.setServiciosList(procesarServicios(ausCaso.getServiciosList(), bean));

            guardarSeguimientoAdicional(bean, ausCaso);

            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(AusCasoBean bean) {
        try {

            AusCaso ausCaso = bean.getObjBorrarCaso();
            bean.auditoriaModificar(ausCaso);
            ausCaso.setBorrado(Boolean.TRUE);
            ausCaso.setUsuarioBorra(ausCaso.getUsuarioModifica());
            ausCaso.setTerminalBorra(ausCaso.getTerminalModifica());
            ausCaso.setFechaHoraBorra(ausCaso.getFechaHoraModifica());
            getCasoRemoto().actualizarBorrarCaso(ausCaso);
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void modificarGestion(AusCasoBean bean) {
        try {
            AusCaso ausCaso = bean.getObjeto();
            ausCaso.setEmpresa(bean.getConexion().getEmpresa());
            bean.auditoriaModificar(ausCaso);
            ausCaso.setCantidadServicios(ausCaso.getServiciosList().size());
            int idEstadoResuelto = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_RESUELTO));
            int idEstadoCerrado = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO));
            int cantidad = 0;
            for (AusCasoServicio servicio : ausCaso.getServiciosList()) {
                if (servicio.getAccion() == AusCasoServicio.ACCION_INSERTAR) {
                    if (servicio.getMaeEstadoId() == idEstadoResuelto || servicio.getMaeEstadoId() == idEstadoCerrado) {
                        cantidad = cantidad + 1;
                    }
                } else if (servicio.getAccion() == AusCasoServicio.ACCION_NINGUNO) {
                    if (ausCaso.getCantidadServiciosCerrados() == null) {
                        if (servicio.getMaeEstadoId() == idEstadoResuelto || servicio.getMaeEstadoId() == idEstadoCerrado) {
                            cantidad = cantidad + 1;
                        }
                    }
                }
            }
            int cantidadServiciosCerrados = (ausCaso.getCantidadServiciosCerrados() != null) ? ausCaso.getCantidadServiciosCerrados() + cantidad : cantidad;
            ausCaso.setCantidadServiciosCerrados(cantidadServiciosCerrados);
            getCasoRemoto().actualizar(ausCaso);
            getCasoRemoto().actualizarCantidadServicios(ausCaso);
            List<AusCasoServicio> listaServicios = procesarServicios(ausCaso.getServiciosList(), bean);
            ausCaso.setServiciosList(listaServicios);

            procesarSeguimientos(bean, ausCaso);

            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarSeguimientoPorDefecto(AusCasoBean bean, AusCaso caso) {
        try {
            AusSeguimiento seguimiento = bean.getObjeto().getSeguimientoInicial();
            bean.auditoriaGuardar(seguimiento);
            //seguimiento.setMaeEstadoId(bean.getIdTipoSeguimiento(AusCasoBean.DESC_SEGUIMIENTO_RADICADO));
            seguimiento.setMaeEstadoId(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.DESC_SEGUI_RADIC)));
            Maestro estadoSeguimiento = bean.getHashTipoSeguimiento().get(seguimiento.getMaeEstadoId());
            if (estadoSeguimiento != null) {
                seguimiento.setMaeEstadoCodigo(estadoSeguimiento.getValor());
                seguimiento.setMaeEstadoValor(estadoSeguimiento.getNombre());
            }
            //Integer idMaestro= seguimiento.getMaeEstadoId();
            //Maestro maestroSeguimiento = bean.getMaestroSeguimiento(idMaestro);
            //seguimiento.setMaeEstadoCodigo(maestroSeguimiento.getValor());
            //seguimiento.setMaeEstadoValor(maestroSeguimiento.getNombre());
            seguimiento.setCasosId(caso);
            int idSeguimiento = getSeguimientoRemoto().insertar(seguimiento);
            seguimiento.setId(idSeguimiento);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarSeguimientoReabrirCaso(AusCasoBean bean, AusCaso caso) {
        try {
            AusSeguimiento seguimiento = new AusSeguimiento();
            bean.auditoriaGuardar(seguimiento);
            //seguimiento.setMaeEstadoId(bean.getIdTipoSeguimiento(AusCasoBean.DESC_SEGUIMIENTO_RADICADO));
            seguimiento.setMaeEstadoId(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SEGUIMI_ESTADO_GESTION)));
            if (caso.getObservacionReabre() != null) {
                seguimiento.setObservacion(caso.getObservacionReabre());
            } else {
                seguimiento.setObservacion(caso.getMaeMotivoReabreValor());
            }
            Maestro estadoSeguimiento = bean.getHashTipoSeguimiento().get(seguimiento.getMaeEstadoId());
            if (estadoSeguimiento != null) {
                seguimiento.setMaeEstadoCodigo(estadoSeguimiento.getValor());
                seguimiento.setMaeEstadoValor(estadoSeguimiento.getNombre());
            }
            seguimiento.setCasosId(caso);
            int idSeguimiento = getSeguimientoRemoto().insertar(seguimiento);
            seguimiento.setId(idSeguimiento);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarSeguimientoCerrarCaso(AusCasoBean bean, AusCaso caso) {
        try {
            AusSeguimiento seguimiento = new AusSeguimiento();
            bean.auditoriaGuardar(seguimiento);
            //seguimiento.setMaeEstadoId(bean.getIdTipoSeguimiento(AusCasoBean.DESC_SEGUIMIENTO_RADICADO));
            seguimiento.setMaeEstadoId(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SEGUIMIENTO_ESTADO_CERRADO)));
            Maestro estadoSeguimiento = getMaestroRemoto().consultar(seguimiento.getMaeEstadoId());
            if (estadoSeguimiento != null) {
                seguimiento.setMaeEstadoCodigo(estadoSeguimiento.getValor());
                seguimiento.setMaeEstadoValor(estadoSeguimiento.getNombre());
            }
            seguimiento.setObservacion(caso.getObservacionReabre());
            seguimiento.setCasosId(caso);
            int idSeguimiento = getSeguimientoRemoto().insertar(seguimiento);
            seguimiento.setId(idSeguimiento);
            procesarAdjuntos(bean.getSeguimientoProcesar().getAdjuntosList(), bean, 0, 0, idSeguimiento);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void verServicio(AusCasoBean bean) {
        try {
            bean.setServicioParaCrear(getServicioRemoto().consultar(bean.getServicioParaCrear().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    
    public void editarServicio(AusCasoBean bean) {
        try {
            AusCasoServicio servicio = getServicioRemoto().consultar(bean.getServicioParaCrear().getId());
            //2025-07-25 jyperez almacenamos el id del estado del servicio anterior
            bean.setIdEstadoServicioAnterior(servicio.getMaeEstadoId());
            bean.setServicioParaCrear(servicio);
            if (bean.getServicioParaCrear().getMaTecnologiaId() != null) {
                bean.getServicioParaCrear().setHabiltarCampoProcedimiento(true);
            } else {
                bean.getServicioParaCrear().setHabiltarCampoProcedimiento(false);
            }
            if (bean.getServicioParaCrear().getMedicamento() != null) {
                bean.desabilidarCamposAplicaMedicamentoCobertura();
            }
            /*if (bean.getServicioParaCrear().getObjetoDiagnostico().getId() != null) {
                List<Maestro> lista = new ArrayList();
                Maestro mae = getMaestroRemoto().consultar(bean.getServicioParaCrear().getObjetoDiagnostico().getId());
                lista.add(mae);
                bean.setListaTipoDiagnostico(lista);
                HashMap<Integer, Maestro> hash = new HashMap();
                hash.put(mae.getId(), mae);
                bean.setHashTipoDiagnostico(hash);
            }*/

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }

    }

    public void modificarServicio(AusCasoBean bean) {
        try {
            AusCasoServicio servicio = bean.getServicioParaCrear();
            Maestro estadoServicio = bean.getHashEstadoServicio().get(servicio.getMaeEstadoId());
            if (estadoServicio != null) {
                servicio.setMaeEstadoCodigo(estadoServicio.getValor());
                servicio.setMaeEstadoValor(estadoServicio.getNombre());
            }
            if (servicio.getIdUsuarioResponsable().getId() != null) {
                servicio.setAsignadoUsuariosId(new Usuario(servicio.getIdUsuarioResponsable().getId()));
            }
            bean.auditoriaModificar(servicio);
            AusServicioGestionHistorico servicioHistorico = asignarValoresServicioHistorico(servicio);
            //Registro de Histórico
            bean.auditoriaGuardar(servicioHistorico);
            if (servicioHistorico.getUsuarioAsignado() != null) {
                servicioHistorico.setObservacion("Asignado: " + servicioHistorico.getUsuarioAsignado() + " - " + servicioHistorico.getObservacion());
            }
            getServicioGestionHistoricoRemotoRemoto().insertar(servicioHistorico);

            Maestro ambitoServicio = bean.getHashTipoServicioAmbito().get(servicio.getMaeServicioAmbitoId());
            if (ambitoServicio != null) {
                servicio.setMaeServicioAmbitoCodigo(ambitoServicio.getValor());
                servicio.setMaeServicioAmbitoValor(ambitoServicio.getNombre());
            }
            Maestro tipoServicioMotivo = bean.getHashTipoServicioMotivo().get(servicio.getMaeServicioMotivoId());
            if (tipoServicioMotivo != null) {
                servicio.setMaeServicioMotivoCodigo(tipoServicioMotivo.getValor());
                servicio.setMaeServicioMotivoValor(tipoServicioMotivo.getNombre());
            }

            Maestro tipoAdministrativo = bean.getHashTipoAdministrativo().get(servicio.getMaeTipoAdministrativoId());
            if (tipoAdministrativo != null) {
                servicio.setMaeTipoAdministrativoCodigo(tipoAdministrativo.getValor());
                servicio.setMaeTipoAdministrativoValor(tipoAdministrativo.getNombre());
            }

            servicio.setObjetoPrestadorSedeValor(servicio.getObjetoPrestadorSede().getNombreSede());
            servicio.setObjetoPrestadorIpsValor(servicio.getObjetoPrestadorIps().getNombreSede());

            getServicioRemoto().actualizar(servicio);
            //if (servicio.getMaeEstadoId() == bean.getListaEstadosServicio().get("Asignado")) {
            if (servicio.getMaeEstadoId() == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_ASIGNADO))) {
                notificarPorCorreoServicio(servicio.getIdUsuarioResponsable().getId(), servicio.getId(), servicio.getCasosId().getId(), bean.getTipoServicioMotivo(servicio.getMaeServicioMotivoId()));
            }

            servicio.setAdjuntosList(procesarAdjuntos(servicio.getAdjuntosList(), bean, 0, servicio.getId(), 0));

            AusCaso ausCaso = bean.getObjeto();
            int idEstadoResuelto = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_RESUELTO));
            int idEstadoCerrado = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO));
            int cantidad = 0;
            if (servicio.getMaeEstadoId() == idEstadoResuelto || servicio.getMaeEstadoId() == idEstadoCerrado) {
                cantidad = cantidad + 1;
            } else {
                //2025-07-25 jyperez disminuimos el conteo si el estado es diferente a los anteriores ( se reabre el servicio). validamos que haya algún cambio en el estado.
                if (servicio.getMaeEstadoId() != bean.getIdEstadoServicioAnterior() && 
                        (bean.getIdEstadoServicioAnterior() == idEstadoResuelto || bean.getIdEstadoServicioAnterior() == idEstadoCerrado)) {
                    cantidad = cantidad - 1;
                }
            }
            ausCaso.setCantidadServiciosCerrados(ausCaso.getCantidadServiciosCerrados() + cantidad);
            getCasoRemoto().actualizarCantidadServicios(ausCaso);
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void borrarServicio(AusCasoBean bean) {
        try {
            AusCasoServicio ausCasoServicio =  getServicioRemoto().consultar(bean.getServicioParaCrear().getId());
            bean.auditoriaModificar(ausCasoServicio);
            ausCasoServicio.setBorrado(Boolean.TRUE);
            ausCasoServicio.setUsuarioBorra(ausCasoServicio.getUsuarioModifica());
            ausCasoServicio.setTerminalBorra(ausCasoServicio.getTerminalModifica());
            ausCasoServicio.setFechaHoraBorra(ausCasoServicio.getFechaHoraModifica());
            getServicioRemoto().actualizarBorrarServicio(ausCasoServicio);
            AusCaso caso = getCasoRemoto().consultar(ausCasoServicio.getCasosId().getId());
            Integer cantidadServicios = caso.getCantidadServicios();
            caso.setCantidadServicios(cantidadServicios - 1);
            getCasoRemoto().actualizarCantidadServiciosBorrado(caso);
            bean.getObjeto().setServiciosList(getServicioRemoto().consultarServiciosTodoServicios(ausCasoServicio.getCasosId().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void guardarServicios(AusCasoBean bean, AusCaso caso) {
        try {
            for (AusCasoServicio servicio : caso.getServiciosList()) {
                bean.auditoriaGuardar(servicio);
                Date fechaActual = new Date();
                List<Date> fechasHabiles = new ArrayList<>();
                fechasHabiles = getCasoRemoto().consultarFechasNoHabiles(fechaActual);
                servicio.asignarHorasVencimiento(fechaActual, bean.getHorasSeguimiento(), fechasHabiles);
                servicio.setCasosId(caso);
                Maestro estadoServicio = bean.getHashEstadoServicio().get(servicio.getMaeEstadoId());
                if (estadoServicio != null) {
                    servicio.setMaeEstadoCodigo(estadoServicio.getValor());
                    servicio.setMaeEstadoValor(estadoServicio.getNombre());
                }
                Maestro ambitoServicio = bean.getHashTipoServicioAmbito().get(servicio.getMaeServicioAmbitoId());
                if (ambitoServicio != null) {
                    servicio.setMaeServicioAmbitoCodigo(ambitoServicio.getValor());
                    servicio.setMaeServicioAmbitoValor(ambitoServicio.getNombre());
                }
                Maestro tipoServicioMotivo = bean.getHashTipoServicioMotivo().get(servicio.getMaeServicioMotivoId());
                if (tipoServicioMotivo != null) {
                    servicio.setMaeServicioMotivoCodigo(tipoServicioMotivo.getValor());
                    servicio.setMaeServicioMotivoValor(tipoServicioMotivo.getNombre());
                }
                Maestro patologiaServicio = bean.getHashTipoPatologia().get(servicio.getMaePatologiaId());
                if (patologiaServicio != null) {
                    servicio.setMaePatologiaCodigo(patologiaServicio.getValor());
                    servicio.setMaePatologiaValor(patologiaServicio.getNombre());
                }
                Maestro tipoAdministrativo = bean.getHashTipoAdministrativo().get(servicio.getMaeTipoAdministrativoId());
                if (tipoAdministrativo != null) {
                    servicio.setMaeTipoAdministrativoCodigo(tipoAdministrativo.getValor());
                    servicio.setMaeTipoAdministrativoValor(tipoAdministrativo.getNombre());
                }
                
                int idServicio = getServicioRemoto().insertar(servicio);
                int idCaso = servicio.getCasosId().getId();
                //notificarPorCorreoServicio(servicio.getIdUsuarioResponsable().getId(), idServicio, idCaso, bean.getTipoServicioMotivo(servicio.getMaeServicioMotivoId()));
                if (servicio.getIdUsuarioResponsable() != null
                        && servicio.getIdUsuarioResponsable().getId() != null) {
                    if (servicio.getMaeEstadoId() != Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_RESUELTO))) {
                        notificarPorCorreoServicio(servicio.getIdUsuarioResponsable().getId(), idServicio, idCaso, caso.getMaeMotivoEspecificoValor());
                    }
                }
                servicio.setId(idServicio);
                insertarAdjuntos(servicio.getAdjuntosList(), bean, 0, idServicio, 0);
                insertarServicioHistorico(bean, servicio);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

   
    public void editarSeguimiento(AusCasoBean bean) {
        try {
            AusSeguimiento seguimiento = getSeguimientoRemoto().consultar(bean.getSeguimientoProcesar().getId());
            bean.setSeguimientoProcesar(seguimiento);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    
    public void modificarSeguimiento(AusCasoBean bean) {
        try {
            AusSeguimiento seguimiento = bean.getSeguimientoProcesar();
            Maestro estadoSeguimiento = bean.getHashTipoSeguimiento().get(seguimiento.getMaeEstadoId());
            if (estadoSeguimiento != null) {
                seguimiento.setMaeEstadoCodigo(estadoSeguimiento.getValor());
                seguimiento.setMaeEstadoValor(estadoSeguimiento.getNombre());
            }
            bean.auditoriaModificar(seguimiento);
            getSeguimientoRemoto().actualizar(seguimiento);
            seguimiento.setAdjuntosList(procesarAdjuntos(seguimiento.getAdjuntosList(), bean, 0, 0, seguimiento.getId()));
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void verSeguimiento(AusCasoBean bean) {
        try {
            bean.setSeguimientoProcesar(getSeguimientoRemoto().consultar(bean.getSeguimientoProcesar().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void procesarSeguimientos(AusCasoBean bean, AusCaso caso) {
        try {
            for (AusSeguimiento seguimiento : caso.getSeguimientosList()) {
                seguimiento.setCasosId(caso);
                Maestro estadoSeguimiento = bean.getHashTipoSeguimiento().get(seguimiento.getMaeEstadoId());
                if (estadoSeguimiento != null) {
                    seguimiento.setMaeEstadoCodigo(estadoSeguimiento.getValor());
                    seguimiento.setMaeEstadoValor(estadoSeguimiento.getNombre());
                }
                if (seguimiento.getId() != null) {
                    bean.auditoriaModificar(seguimiento);
                    getSeguimientoRemoto().actualizar(seguimiento);
                } else {
                    bean.auditoriaGuardar(seguimiento);
                    int idSeg = getSeguimientoRemoto().insertar(seguimiento);
                    seguimiento.setId(idSeg);
                }
                seguimiento.setAdjuntosList(procesarAdjuntos(seguimiento.getAdjuntosList(), bean, 0, 0, seguimiento.getId()));
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarSeguimientoAdicional(AusCasoBean bean, AusCaso caso) {
        try {
            AusSeguimiento seguimientoAdicional = bean.getObjeto().getSeguimientoAdicional();
            bean.auditoriaGuardar(seguimientoAdicional);
            if (seguimientoAdicional.getMaeEstadoId() > 0
                    && seguimientoAdicional.getObservacion() != null
                    && seguimientoAdicional.getObservacion().length() > 1) {
                seguimientoAdicional.setCasosId(caso);
                //if (bean.obtenerEstado("Cerrado") == seguimientoAdicional.getMaeEstadoId()) {
                if (Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ESTADO_CERRADO)) == seguimientoAdicional.getMaeEstadoId()) {
                    String motivos = "''";
                    for (AusCasoServicio services : caso.getServiciosList()) {
                        motivos += bean.getTipoServicioMotivo(services.getMaeServicioMotivoId()) + ",";
                    }
                    motivos = StringUtils.removeEnd(motivos, ",");
                    motivos += "''";
                    notificarPorCorreoCasoCerrado(caso.getResponsableUsuariosId(), caso.getAusPersonasId(), caso.getId(), motivos);
                }
                seguimientoAdicional.setMaeEstadoId(seguimientoAdicional.getMaeEstadoId());
                Maestro estadoSeguimiento = bean.getHashTipoSeguimiento().get(seguimientoAdicional.getMaeEstadoId());
                if (estadoSeguimiento != null) {
                    seguimientoAdicional.setMaeEstadoCodigo(estadoSeguimiento.getValor());
                    seguimientoAdicional.setMaeEstadoValor(estadoSeguimiento.getNombre());
                }
                int idSeguimientoAdicional = getSeguimientoRemoto().insertar(seguimientoAdicional);
                if (idSeguimientoAdicional > 0) {
                    insertarAdjuntos(seguimientoAdicional.getAdjuntosList(), bean, 0, 0, idSeguimientoAdicional);
                    //insertarAdjuntos(seguimientoAdicional.getAdjuntosList(), bean, seguimientoAdicional.getCasosId().getId(), 0, idSeguimientoAdicional);
                } else {
                    if (seguimientoAdicional.getAdjuntosList().size() > 0) {
                        insertarAdjuntos(seguimientoAdicional.getAdjuntosList(), bean, 0, 0, bean.getObjeto().getSeguimientosList().get(0).getId());
                        //insertarAdjuntos(seguimientoAdicional.getAdjuntosList(), bean, seguimientoAdicional.getCasosId().getId(), 0, bean.getObjeto().getSeguimientosList().get(0).getId());
                    }
                }
            } else {
                if (seguimientoAdicional.getAdjuntosList().size() > 0
                        && seguimientoAdicional.getMaeEstadoId() <= 0
                        && seguimientoAdicional.getId() == null) {
                    insertarAdjuntos(seguimientoAdicional.getAdjuntosList(), bean, 0, 0, bean.getObjeto().getSeguimientosList().get(0).getId());
                    //insertarAdjuntos(seguimientoAdicional.getAdjuntosList(), bean, seguimientoAdicional.getCasosId().getId(), 0, bean.getObjeto().getSeguimientosList().get(0).getId());
                }
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private AusServicioGestionHistorico asignarValoresServicioHistorico(AusCasoServicio servicioMemoria) {
        AusServicioGestionHistorico servicioHistorico = new AusServicioGestionHistorico();
        servicioHistorico.setObservacion(servicioMemoria.getDescripcion());
        servicioHistorico.setMaeEstadoId(servicioMemoria.getMaeEstadoId());
        servicioHistorico.setMaeEstadoCodigo(servicioMemoria.getMaeEstadoCodigo());
        servicioHistorico.setMaeEstadoValor(servicioMemoria.getMaeEstadoValor());
        servicioHistorico.setCasoServicios(new AusCasoServicio(servicioMemoria.getId()));
        servicioHistorico.setFechaHoraCrea(servicioMemoria.getFechaHoraCrea());
        servicioHistorico.setTerminalCrea(servicioMemoria.getTerminalCrea());
        servicioHistorico.setUsuarioCrea(servicioMemoria.getUsuarioCrea());
        return servicioHistorico;
    }

    private int buscarServicioHistoricoPorEstadoID(AusCasoBean bean, AusCasoServicio servicioDB) {
        int idHistorico = 0;
        try {
            bean.getParamConsulta().getFiltros().put("estado", servicioDB.getMaeEstadoId());
            bean.getParamConsulta().getFiltros().put("serviciosId", servicioDB.getId());
            List<AusServicioGestionHistorico> serHistorico = getServicioGestionHistoricoRemotoRemoto().consultarLista(bean.getParamConsulta());
            idHistorico = serHistorico.isEmpty() ? 0 : serHistorico.get(0).getId();
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return idHistorico;
    }

    private void insertarServicioHistorico(Url bean, AusCasoServicio servicio) {
        try {
            AusServicioGestionHistorico servicioHistorico = new AusServicioGestionHistorico();
            bean.auditoriaGuardar(servicioHistorico);
            servicioHistorico.setObservacion(servicio.getDescripcion());
            servicioHistorico.setMaeEstadoId(servicio.getMaeEstadoId());
            servicioHistorico.setMaeEstadoCodigo(servicio.getMaeEstadoCodigo());
            servicioHistorico.setMaeEstadoValor(servicio.getMaeEstadoValor());
            servicioHistorico.setCasoServicios(new AusCasoServicio(servicio.getId()));
            getServicioGestionRemotoRemoto().insertar(servicioHistorico);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private List<AusCasoServicio> procesarServicios(List<AusCasoServicio> serviciosProcesar, AusCasoBean bean) {
        List<AusCasoServicio> listaServicios = new ArrayList();
        try {
            for (AusCasoServicio servicio : serviciosProcesar) {
                bean.auditoriaModificar(servicio);
                if (servicio.getAccion() == AusCasoServicio.ACCION_INSERTAR) {
                    bean.auditoriaGuardar(servicio);
                    servicio.setCasosId(bean.getObjeto());
                    int idServicio = getServicioRemoto().insertar(servicio);
                    int idCaso = servicio.getCasosId().getId();
                    int idResponsable = (servicio.getIdUsuarioResponsable().getId() != null && servicio.getIdUsuarioResponsable().getId() > 0) ? servicio.getIdUsuarioResponsable().getId() : 0;
                    if (servicio.getMaeEstadoId() != Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_RESUELTO))) {
                        notificarPorCorreoServicio(idResponsable, idServicio, idCaso, bean.getTipoServicioMotivo(servicio.getMaeServicioMotivoId()));
                    }
                    servicio.setId(idServicio);
                    listaServicios.add(servicio);
                    servicio.setAdjuntosList(procesarAdjuntos(servicio.getAdjuntosList(), bean, 0, idServicio, 0));
                    insertarServicioHistorico(bean, servicio);
                } else if (servicio.getAccion() == AusCasoServicio.ACCION_BORRAR) {
                    getServicioRemoto().eliminar(servicio.getId());
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }

        return listaServicios;
    }

    private void insertarAdjuntos(List<AusAdjunto> adjuntosIn, AusCasoBean bean,
            int idCaso, int idSer, int idSeg) {

        try {
            for (AusAdjunto adjunto : adjuntosIn) {
                bean.auditoriaGuardar(adjunto);
                if (idCaso > 0) {
                    adjunto.setCasosId(new AusCaso(idCaso));
                }
                if (idSer > 0) {
                    adjunto.setServiciosId(new AusCasoServicio(idSer));
                }
                if (idSeg > 0) {
                    adjunto.setSeguimientosId(new AusSeguimiento(idSeg));
                }
                int idAdjunto = getAdjuntoRemoto().insertar(adjunto);
                adjunto.setId(idAdjunto);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private List<AusAdjunto> procesarAdjuntos(List<AusAdjunto> adjuntosIn, AusCasoBean bean,
            int idCaso, int idSer, int idSeg) {
        List<AusAdjunto> listaAdjuntoInsertados = new ArrayList();
        try {
            for (AusAdjunto adjunto : adjuntosIn) {
                bean.auditoriaModificar(adjunto);
                if (adjunto.getAccion() == AusAdjunto.ACCION_INSERTAR) {
                    if (adjunto.getId() == null) {
                        bean.auditoriaGuardar(adjunto);
                        if (idCaso > 0) {
                            adjunto.setCasosId(new AusCaso(idCaso));
                        }
                        if (idSer > 0) {
                            adjunto.setServiciosId(new AusCasoServicio(idSer));
                        }
                        if (idSeg > 0) {
                            adjunto.setSeguimientosId(new AusSeguimiento(idSeg));
                        }
                        int idAdjunto = getAdjuntoRemoto().insertar(adjunto);
                        adjunto.setId(idAdjunto);
                        listaAdjuntoInsertados.add(adjunto);
                    }
                } else if (adjunto.getAccion() == AusAdjunto.ACCION_BORRAR) {
                    getAdjuntoRemoto().eliminar(adjunto.getId());
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return listaAdjuntoInsertados;
    }

    @Override
    public void consultarPersona(AusPersonaBean bean) {
        try {
            //bean.getObjeto().setEmpresa(bean.getConexion().getEmpresa());
            AusPersona ausPersona = getPersonaRemoto().consultarPersona(bean.getObjeto());
            //AsegAfiliado asegAfiliado = getAfiliadoRemoto().consultar(bean.getObjeto());
            if (ausPersona.exitePersona()) {
                List<AusPersonaTelefono> telefonosPersona = getPersonaRemoto().consultarTelefonosPersonas(ausPersona.getDocumento());
                ausPersona.setListaTelefonos(telefonosPersona);
                bean.setObjeto(ausPersona);

                bean.setListaausPersonaTelefono(ausPersona.getListaTelefonos());
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void consultarPersonaAfiliada(AusPersonaBean bean) {
        try {
//          bean.getObjeto().setEmpresa(bean.getConexion().getEmpresa());
            List<AsegAfiliado> ausPersona = getAfiliadoRemoto().consultarListaAfiliados(bean.getObjeto().getMae_tipo_documento_codigo(), bean.getObjeto().getDocumento());// consultarListaAfiliados (bean.getObjeto().getDocumento());
            //AsegAfiliado asegAfiliado = getAfiliadoRemoto().consultar(bean.getObjeto());
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
    public void consultarUsuarioPorEstados(AusCasoBean bean) {
        consultarUsuarioParaEstadosAmbitos(bean);
        consultarUsuariosParaTipoAdministracion(bean);

    }

    @Override
    public void consultarUsuario(AusCasoBean bean) {
        try {
            bean.setListaUsuarios(getCasoRemoto().consultarTodosUsuarios());
            bean.setHashUsuarios(convertToHashUsuarios(bean.getListaUsuarios()));
        } catch (Exception ex) {
            Logger.getLogger(AusCasoServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void maestroTipoMotivo(AusCasoBean bean) {
        try {
            bean.setListaTipoMotivo(new ArrayList<>());
            bean.setHashTipoMotivo(new HashMap<>());
            bean.setListaSubMotivo(new ArrayList<>());
            bean.setHashSubMotivo(new HashMap<>());
            bean.setListaTipoMotivo(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.AUS_CASO_TIPO_MOTIVO_ESPECIFICO, bean.getObjeto().getMaeMotivoEspecificoId()));
            bean.setHashTipoMotivo(convertToHashMaestroTipoMotivo(bean.getListaTipoMotivo()));
        } catch (Exception ex) {
            Logger.getLogger(AusCasoServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void maestroSubTipoMotivo(AusCasoBean bean) {
        try {
            bean.setListaSubMotivo(new ArrayList<>());
            bean.setHashSubMotivo(new HashMap<>());
            bean.setListaSubMotivo(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.AUS_CASO_SUBTIPO_MOTIVO_ESPECIFICO, bean.getObjeto().getMaeTipoMotivoEspecificoId()));
            bean.setHashSubMotivo(convertToHashMaestroSubTipoMotivo(bean.getListaSubMotivo()));
        } catch (Exception ex) {
            Logger.getLogger(AusCasoServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public HashMap<Integer, Maestro> convertToHashMaestroTipoMotivo(List<Maestro> list) {
        HashMap<Integer, Maestro> map = new HashMap<>();
        for (Maestro i : list) {
            map.put(i.getId(), i);
        }
        return map;
    }

    public HashMap<Integer, Maestro> convertToHashMaestroSubTipoMotivo(List<Maestro> list) {
        HashMap<Integer, Maestro> map = new HashMap<>();
        for (Maestro i : list) {
            map.put(i.getId(), i);
        }
        return map;
    }

    public void consultarUsuarioParaEstadosAmbitos(AusCasoBean bean) {
        try {
            AusCasoServicio servicio = bean.getServicioParaCrear();
            Maestro servicioEstado = null;
            Maestro servicioMotivo = null;
            if (servicio.getMaeEstadoId() > 0) {
                servicioEstado = getMaestroRemoto().consultar(servicio.getMaeEstadoId());
            }
            if (servicio.getMaeServicioAmbitoId() > 0) {
                servicioMotivo = getMaestroRemoto().consultar(servicio.getMaeServicioAmbitoId());
            }
            if (servicioEstado != null && servicioMotivo != null) {

                if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ASIGNADO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_PRESTACION_SERVICIO)) {
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    bean.setListaUsuarios(getUsuarioRemoto().consultarPorRol(Integer.parseInt(PropApl.getInstance().get(PropApl.AUS_ID_FILTRO_USUARIOS))));
                    bean.setHashUsuarios(convertToHashUsuarios(bean.getListaUsuarios()));
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ASIGNADO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)) {
                    servicio.setHabilitarServicioTipoAdministracion(false);
                    //servicio.setDeshabilitarCampoAsignado(true);
                    //servicio.setIdUsuarioResponsable(new Usuario());
                    //bean.setListaUsuarios(new ArrayList<>());
                    //bean.setHashUsuarios(new HashMap<>());
                    servicio.setHabilitarServicioDestino(true);
                    servicio.setObjetoPrestadorIps(new CntPrestadorSede());
                    servicio.setObjetoPrestadorIpsValor(null);
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ESTUDIO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_PRESTACION_SERVICIO)) {
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ESTUDIO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)) {
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_AUDITAR)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_SIN_AUDITORIA)) {
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_RESUELTO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)) {
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_RESUELTO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_PRESTACION_SERVICIO)) {
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                }
            }

        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    public void consultarUsuariosParaTipoAdministracion(AusCasoBean bean) {
        try {
            AusCasoServicio servicio = bean.getServicioParaCrear();
            Maestro servicioEstado = null;
            Maestro servicioMotivo = null;
            Maestro servicioTipoADministracion = null;
            if (servicio.getMaeEstadoId() > 0) {
                servicioEstado = getMaestroRemoto().consultar(servicio.getMaeEstadoId());
            }
            if (servicio.getMaeServicioAmbitoId() > 0) {
                servicioMotivo = getMaestroRemoto().consultar(servicio.getMaeServicioAmbitoId());
            }
            if (servicio.getMaeTipoAdministrativoId() != null) {
                servicioTipoADministracion = getMaestroRemoto().consultar(servicio.getMaeTipoAdministrativoId());
            }
            if (servicioEstado != null && servicioMotivo != null && servicioTipoADministracion != null) {

                if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ASIGNADO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)
                        && servicioTipoADministracion.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_TIPO_ADMINISTRATIVO_EPS)) {
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    bean.setListaUsuarios(getUsuarioRemoto().consultarPorEmpresa(bean.getConexion().getEmpresa().getId()));
                    bean.setHashUsuarios(convertToHashUsuarios(bean.getListaUsuarios()));
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ESTUDIO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)
                        && servicioTipoADministracion.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_TIPO_ADMINISTRATIVO_IPS)) {
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    //bean.setListaUsuarios(getUsuarioRemoto().consultarPorRol(Integer.parseInt(PropApl.getInstance().get(PropApl.AUS_ID_FILTRO_USUARIOS))));
                    //bean.setHashUsuarios(convertToHashUsuarios(bean.getListaUsuarios()));
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ASIGNADO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)
                        && servicioTipoADministracion.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_TIPO_ADMINISTRATIVO_IPS)) {

                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    bean.setListaUsuarios(getUsuarioRemoto().consultarPorRol(Integer.parseInt(PropApl.getInstance().get(PropApl.AUS_ID_FILTRO_USUARIOS))));
                    bean.setHashUsuarios(convertToHashUsuarios(bean.getListaUsuarios()));
                }
            }

        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    @Override
    public void crearServicioCamposObligatoriosParaEstados(AusCasoBean bean) {
        try {
            AusCasoServicio servicio = bean.getServicioParaCrear();
            Maestro servicioEstado = null;
            Maestro servicioMotivo = null;
            if (servicio.getMaeEstadoId() > 0) {
                servicioEstado = getMaestroRemoto().consultar(servicio.getMaeEstadoId());
            }
            if (servicio.getMaeServicioAmbitoId() > 0) {
                servicioMotivo = getMaestroRemoto().consultar(servicio.getMaeServicioAmbitoId());
            }
            if (servicioEstado != null && servicioMotivo != null) {

                if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ASIGNADO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_PRESTACION_SERVICIO)) {
                    servicio.setHabilitarServicioTipoAdministracion(true);
                    servicio.setMaeTipoAdministrativoId(null);
                    servicio.setHabilitarServicioProcedimiento(false);
                    servicio.setHabilitarServicioEspecialidad(false);
                    servicio.setHabilitarServicioCIEX(false);
                    servicio.setHabilitarServicioPatologia(false);
                    //2024-10-16 jyperez habilitar
                    servicio.setHabilitarServicioDestino(false);
                    servicio.setObjetoPrestadorIps(new CntPrestadorSede());
                    servicio.setObjetoPrestadorIpsValor(null);
                    servicio.setDeshabilitarCampoAsignado(false);
                    servicio.setIdUsuarioResponsable(new Usuario());
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    bean.setListaUsuarios(getUsuarioRemoto().consultarPorRol(Integer.parseInt(PropApl.getInstance().get(PropApl.AUS_ID_FILTRO_USUARIOS))));
                    bean.setHashUsuarios(convertToHashUsuarios(bean.getListaUsuarios()));
                    servicio.setHabilitarMedicamento(false);
                    servicio.setHabilitarMedicamentoCobertura(true);
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ASIGNADO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)) {
                    servicio.setHabilitarServicioTipoAdministracion(false);
                    //servicio.setDeshabilitarCampoAsignado(true);
                    //servicio.setIdUsuarioResponsable(new Usuario());
                    //bean.setListaUsuarios(new ArrayList<>());
                    //bean.setHashUsuarios(new HashMap<>());
                    //2024-10-16 jyperez habilitar
                    servicio.setHabilitarServicioDestino(false);
                    servicio.setObjetoPrestadorIps(new CntPrestadorSede());
                    servicio.setObjetoPrestadorIpsValor(null);
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ESTUDIO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_PRESTACION_SERVICIO)) {
                    servicio.setHabilitarServicioTipoAdministracion(true);
                    servicio.setMaeTipoAdministrativoId(null);
                    servicio.setHabilitarServicioProcedimiento(false);
                    servicio.setHabilitarServicioEspecialidad(false);
                    servicio.setHabilitarServicioCIEX(false);
                    servicio.setHabilitarServicioPatologia(false);
                    servicio.setHabilitarServicioDestino(false);
                    servicio.setDeshabilitarCampoAsignado(true);
                    servicio.setIdUsuarioResponsable(new Usuario());
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    servicio.setHabilitarMedicamento(false);
                    servicio.setHabilitarMedicamentoCobertura(true);
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ESTUDIO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)) {
                    servicio.setHabilitarServicioTipoAdministracion(false);
                    servicio.setHabilitarServicioDestino(false);
                    servicio.setHabilitarServicioProcedimiento(true);
                    servicio.setHabiltarCampoProcedimiento(false);
                    servicio.setMaTecnologiaId(null);
                    servicio.setMaTecnologiaCodigo(null);
                    servicio.setMaTecnologiaValor(null);
                    servicio.setHabilitarServicioEspecialidad(true);
                    servicio.setMaServicioId(0);
                    servicio.setMaServicioCodigo(null);
                    servicio.setMaServicioValor(null);
                    servicio.setHabilitarServicioCIEX(true);
                    servicio.setObjetoDiagnostico(new Maestro());
                    servicio.setHabilitarServicioPatologia(true);
                    servicio.setObjetoPatologia(new Maestro());
                    servicio.setDeshabilitarCampoAsignado(true);
                    servicio.setIdUsuarioResponsable(new Usuario());
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    servicio.setMedicamento(null);
                    servicio.setMedicamentoCobertura(null);
                    servicio.setHabilitarMedicamento(true);
                    servicio.setHabilitarMedicamentoCobertura(true);
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_AUDITAR)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_SIN_AUDITORIA)) {
                    servicio.setHabilitarServicioTipoAdministracion(true);
                    servicio.setMaeTipoAdministrativoId(null);
                    servicio.setHabilitarServicioDestino(false);
                    servicio.setHabilitarServicioProcedimiento(false);
                    servicio.setHabilitarServicioEspecialidad(false);
                    servicio.setHabilitarServicioCIEX(false);
                    servicio.setHabilitarServicioPatologia(false);
                    servicio.setDeshabilitarCampoAsignado(true);
                    servicio.setIdUsuarioResponsable(new Usuario());
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    servicio.setHabilitarMedicamento(false);
                    servicio.setHabilitarMedicamentoCobertura(true);
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_RESUELTO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)) {
                    servicio.setHabilitarServicioTipoAdministracion(false);
                    servicio.setHabilitarServicioProcedimiento(false);
                    servicio.setHabilitarServicioEspecialidad(false);
                    servicio.setHabilitarServicioCIEX(false);
                    servicio.setHabilitarServicioPatologia(false);
                    servicio.setHabilitarServicioDestino(false);
                    servicio.setDeshabilitarCampoAsignado(true);
                    servicio.setIdUsuarioResponsable(new Usuario());
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    servicio.setHabilitarMedicamento(false);
                    servicio.setHabilitarMedicamentoCobertura(true);
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_RESUELTO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_PRESTACION_SERVICIO)) {
                    servicio.setHabilitarServicioTipoAdministracion(true);
                    servicio.setMaeTipoAdministrativoId(null);
                    servicio.setHabilitarServicioDestino(false);
                    servicio.setHabilitarServicioProcedimiento(false);
                    servicio.setHabilitarServicioEspecialidad(false);
                    servicio.setHabilitarServicioCIEX(false);
                    servicio.setHabilitarServicioPatologia(false);
                    servicio.setDeshabilitarCampoAsignado(true);
                    servicio.setIdUsuarioResponsable(new Usuario());
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    //bean.setListaUsuarios(getUsuarioRemoto().consultarPorEmpresa(bean.getConexion().getEmpresa().getId()));
                    //bean.setHashUsuarios(convertToHashUsuarios(bean.getListaUsuarios()));
                    servicio.setHabilitarMedicamento(false);
                    servicio.setHabilitarMedicamentoCobertura(true);
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_RESUELTO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_SIN_AUDITORIA)) {
                    servicio.setHabilitarServicioTipoAdministracion(true);
                    servicio.setMaeTipoAdministrativoId(null);
                    servicio.setHabilitarServicioDestino(false);
                    servicio.setHabilitarServicioProcedimiento(false);
                    servicio.setHabilitarServicioEspecialidad(false);
                    servicio.setHabilitarServicioCIEX(false);
                    servicio.setHabilitarServicioPatologia(false);
                    servicio.setDeshabilitarCampoAsignado(true);
                    servicio.setIdUsuarioResponsable(new Usuario());
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    servicio.setHabilitarMedicamento(false);
                    servicio.setHabilitarMedicamentoCobertura(true);
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ESTUDIO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_SOLICITUD_REPO)) {
                    servicio.setHabilitarServicioTipoAdministracion(true);
                    servicio.setMaeTipoAdministrativoId(null);
                    servicio.setHabilitarServicioProcedimiento(false);
                    servicio.setHabilitarServicioEspecialidad(false);
                    servicio.setHabilitarServicioCIEX(false);
                    servicio.setHabilitarServicioPatologia(false);
                    servicio.setHabilitarServicioDestino(true);
                    servicio.setObjetoPrestadorIps(new CntPrestadorSede());
                    servicio.setObjetoPrestadorIpsValor(null);
                    servicio.setDeshabilitarCampoAsignado(true);
                    servicio.setIdUsuarioResponsable(new Usuario());
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    servicio.setHabilitarMedicamento(false);
                    servicio.setHabilitarMedicamentoCobertura(true);
                }
            }

        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    @Override
    public void crearServicioCamposObligatoriosParaTipoAdministracion(AusCasoBean bean) {
        try {
            AusCasoServicio servicio = bean.getServicioParaCrear();
            Maestro servicioEstado = null;
            Maestro servicioMotivo = null;
            Maestro servicioTipoADministracion = null;
            if (servicio.getMaeEstadoId() > 0) {
                servicioEstado = getMaestroRemoto().consultar(servicio.getMaeEstadoId());
            }
            if (servicio.getMaeServicioAmbitoId() > 0) {
                servicioMotivo = getMaestroRemoto().consultar(servicio.getMaeServicioAmbitoId());
            }
            if (servicio.getMaeTipoAdministrativoId() != null) {
                servicioTipoADministracion = getMaestroRemoto().consultar(servicio.getMaeTipoAdministrativoId());
            }
            if (servicioEstado != null && servicioMotivo != null && servicioTipoADministracion != null) {

                if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ASIGNADO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)
                        && servicioTipoADministracion.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_TIPO_ADMINISTRATIVO_EPS)) {
                    servicio.setHabilitarServicioProcedimiento(false);
                    servicio.setHabilitarServicioEspecialidad(false);
                    servicio.setHabilitarServicioCIEX(false);
                    servicio.setHabilitarServicioPatologia(false);
                    servicio.setDeshabilitarCampoAsignado(false);
                    servicio.setIdUsuarioResponsable(new Usuario());
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    bean.setListaUsuarios(getUsuarioRemoto().consultarPorEmpresa(bean.getConexion().getEmpresa().getId()));
                    bean.setHashUsuarios(convertToHashUsuarios(bean.getListaUsuarios()));
                    //servicio.setHabilitarMedicamento(false);
                    //servicio.setHabilitarMedicamentoCobertura(true);
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ESTUDIO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)
                        && servicioTipoADministracion.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_TIPO_ADMINISTRATIVO_IPS)) {
                    servicio.setHabilitarServicioProcedimiento(false);
                    servicio.setHabilitarServicioEspecialidad(true);
                    servicio.setMaServicioId(0);
                    servicio.setMaServicioCodigo(null);
                    servicio.setMaServicioValor(null);
                    servicio.setHabilitarServicioCIEX(true);
                    servicio.setObjetoDiagnostico(new Maestro());
                    servicio.setHabilitarServicioPatologia(true);
                    servicio.setObjetoPatologia(new Maestro());
                    servicio.setDeshabilitarCampoAsignado(true);
                    servicio.setIdUsuarioResponsable(new Usuario());
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    //servicio.setHabilitarMedicamento(false);
                    //servicio.setHabilitarMedicamentoCobertura(true);
                    //bean.setListaUsuarios(getUsuarioRemoto().consultarPorRol(Integer.parseInt(PropApl.getInstance().get(PropApl.AUS_ID_FILTRO_USUARIOS))));
                    //bean.setHashUsuarios(convertToHashUsuarios(bean.getListaUsuarios()));
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ASIGNADO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)
                        && servicioTipoADministracion.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_TIPO_ADMINISTRATIVO_IPS)) {
                    servicio.setHabilitarServicioProcedimiento(false);
                    servicio.setHabilitarServicioEspecialidad(true);
                    servicio.setMaServicioId(0);
                    servicio.setMaServicioCodigo(null);
                    servicio.setMaServicioValor(null);
                    servicio.setHabilitarServicioCIEX(true);
                    servicio.setObjetoDiagnostico(new Maestro());
                    servicio.setHabilitarServicioPatologia(true);
                    servicio.setObjetoPatologia(new Maestro());
                    servicio.setDeshabilitarCampoAsignado(false);
                    servicio.setIdUsuarioResponsable(new Usuario());
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    bean.setListaUsuarios(getUsuarioRemoto().consultarPorRol(Integer.parseInt(PropApl.getInstance().get(PropApl.AUS_ID_FILTRO_USUARIOS))));
                    bean.setHashUsuarios(convertToHashUsuarios(bean.getListaUsuarios()));
                    //servicio.setHabilitarMedicamento(false);
                    //servicio.setHabilitarMedicamentoCobertura(true);
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_RESUELTO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)
                        && servicioTipoADministracion.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_TIPO_ADMINISTRATIVO_IPS)) {
                    servicio.setHabilitarServicioTipoAdministracion(false);
                    servicio.setHabilitarServicioProcedimiento(false);
                    servicio.setHabilitarServicioEspecialidad(false);
                    servicio.setHabilitarServicioCIEX(false);
                    servicio.setHabilitarServicioPatologia(false);
                    servicio.setHabilitarServicioDestino(false);
                    servicio.setDeshabilitarCampoAsignado(true);
                    servicio.setIdUsuarioResponsable(new Usuario());
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    //bean.setListaUsuarios(getUsuarioRemoto().consultarPorRol(Integer.parseInt(PropApl.getInstance().get(PropApl.AUS_ID_FILTRO_USUARIOS))));
                    //bean.setHashUsuarios(convertToHashUsuarios(bean.getListaUsuarios()));

                    //servicio.setHabilitarMedicamento(false);
                    //servicio.setHabilitarMedicamentoCobertura(true);
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_RESUELTO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)
                        && servicioTipoADministracion.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_TIPO_ADMINISTRATIVO_EPS)) {
                    servicio.setHabilitarServicioTipoAdministracion(false);
                    servicio.setHabilitarServicioProcedimiento(false);
                    servicio.setHabilitarServicioEspecialidad(false);
                    servicio.setHabilitarServicioCIEX(false);
                    servicio.setHabilitarServicioPatologia(false);
                    servicio.setHabilitarServicioDestino(false);
                    servicio.setDeshabilitarCampoAsignado(true);
                    servicio.setIdUsuarioResponsable(new Usuario());
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    //bean.setListaUsuarios(getUsuarioRemoto().consultarPorEmpresa(bean.getConexion().getEmpresa().getId()));
                    //bean.setHashUsuarios(convertToHashUsuarios(bean.getListaUsuarios()));
                    //servicio.setHabilitarMedicamento(false);
                    //servicio.setHabilitarMedicamentoCobertura(true);
                }
            }

        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    @Override
    public void editarServicioCamposObligatorios(AusCasoBean bean) {
        try {
            editarServicioCamposObligatoriosParaEstados(bean);
            editarServicioCamposObligatoriosParaTipoAdministracion(bean);
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    public void editarServicioCamposObligatoriosParaEstados(AusCasoBean bean) {
        try {
            AusCasoServicio servicio = bean.getServicioParaCrear();
            Maestro servicioEstado = null;
            Maestro servicioMotivo = null;
            if (servicio.getMaeEstadoId() > 0) {
                servicioEstado = getMaestroRemoto().consultar(servicio.getMaeEstadoId());
            }
            if (servicio.getMaeServicioAmbitoId() > 0) {
                servicioMotivo = getMaestroRemoto().consultar(servicio.getMaeServicioAmbitoId());
            }
            if (servicioEstado != null && servicioMotivo != null) {

                if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ASIGNADO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_PRESTACION_SERVICIO)) {
                    servicio.setHabilitarServicioTipoAdministracion(true);
                    servicio.setMaeTipoAdministrativoId(null);
                    servicio.setHabilitarServicioProcedimiento(false);
                    servicio.setHabilitarServicioEspecialidad(false);
                    servicio.setHabilitarServicioCIEX(false);
                    servicio.setHabilitarServicioPatologia(false);
                    servicio.setHabilitarServicioDestino(true);
                    servicio.setObjetoPrestadorIps(new CntPrestadorSede());
                    servicio.setObjetoPrestadorIpsValor(null);
                    servicio.setDeshabilitarCampoAsignado(false);
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    bean.setListaUsuarios(getUsuarioRemoto().consultarPorRol(Integer.parseInt(PropApl.getInstance().get(PropApl.AUS_ID_FILTRO_USUARIOS))));
                    bean.setHashUsuarios(convertToHashUsuarios(bean.getListaUsuarios()));
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ASIGNADO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)) {
                    servicio.setHabilitarServicioTipoAdministracion(false);
                    //servicio.setDeshabilitarCampoAsignado(true);
                    //servicio.setIdUsuarioResponsable(new Usuario());
                    //bean.setListaUsuarios(new ArrayList<>());
                    //bean.setHashUsuarios(new HashMap<>());
                    servicio.setHabilitarServicioDestino(true);
                    servicio.setObjetoPrestadorIps(new CntPrestadorSede());
                    servicio.setObjetoPrestadorIpsValor(null);
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ESTUDIO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_PRESTACION_SERVICIO)) {
                    servicio.setHabilitarServicioTipoAdministracion(true);
                    servicio.setMaeTipoAdministrativoId(null);
                    servicio.setHabilitarServicioProcedimiento(false);
                    servicio.setHabilitarServicioEspecialidad(false);
                    servicio.setHabilitarServicioCIEX(false);
                    servicio.setHabilitarServicioPatologia(false);
                    servicio.setHabilitarServicioDestino(false);
                    servicio.setDeshabilitarCampoAsignado(true);
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ESTUDIO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)) {
                    servicio.setHabilitarServicioTipoAdministracion(false);
                    servicio.setHabilitarServicioDestino(false);
                    servicio.setHabilitarServicioProcedimiento(true);
                    servicio.setHabiltarCampoProcedimiento(false);
                    servicio.setMaTecnologiaId(null);
                    servicio.setMaTecnologiaCodigo(null);
                    servicio.setMaTecnologiaValor(null);
                    servicio.setHabilitarServicioEspecialidad(true);
                    servicio.setMaServicioId(0);
                    servicio.setMaServicioCodigo(null);
                    servicio.setMaServicioValor(null);
                    servicio.setHabilitarServicioCIEX(true);
                    servicio.setObjetoDiagnostico(new Maestro());
                    servicio.setHabilitarServicioPatologia(true);
                    servicio.setObjetoPatologia(new Maestro());
                    servicio.setDeshabilitarCampoAsignado(true);
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_AUDITAR)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_SIN_AUDITORIA)) {
                    servicio.setHabilitarServicioTipoAdministracion(true);
                    servicio.setMaeTipoAdministrativoId(null);
                    servicio.setHabilitarServicioDestino(false);
                    servicio.setHabilitarServicioProcedimiento(false);
                    servicio.setHabilitarServicioEspecialidad(false);
                    servicio.setHabilitarServicioCIEX(false);
                    servicio.setHabilitarServicioPatologia(false);
                    servicio.setDeshabilitarCampoAsignado(true);
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_RESUELTO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)) {
                    servicio.setHabilitarServicioTipoAdministracion(false);
                    servicio.setHabilitarServicioProcedimiento(false);
                    servicio.setHabilitarServicioEspecialidad(false);
                    servicio.setHabilitarServicioCIEX(false);
                    servicio.setHabilitarServicioPatologia(false);
                    servicio.setHabilitarServicioDestino(false);
                    servicio.setDeshabilitarCampoAsignado(true);
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_RESUELTO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_PRESTACION_SERVICIO)) {
                    servicio.setHabilitarServicioTipoAdministracion(true);
                    servicio.setMaeTipoAdministrativoId(null);
                    servicio.setHabilitarServicioDestino(false);
                    servicio.setHabilitarServicioProcedimiento(false);
                    servicio.setHabilitarServicioEspecialidad(false);
                    servicio.setHabilitarServicioCIEX(false);
                    servicio.setHabilitarServicioPatologia(false);
                    servicio.setDeshabilitarCampoAsignado(true);
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    //bean.setListaUsuarios(getUsuarioRemoto().consultarPorEmpresa(bean.getConexion().getEmpresa().getId()));
                    //bean.setHashUsuarios(convertToHashUsuarios(bean.getListaUsuarios()));
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_RESUELTO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_SIN_AUDITORIA)) {
                    servicio.setHabilitarServicioTipoAdministracion(true);
                    servicio.setMaeTipoAdministrativoId(null);
                    servicio.setHabilitarServicioDestino(false);
                    servicio.setHabilitarServicioProcedimiento(false);
                    servicio.setHabilitarServicioEspecialidad(false);
                    servicio.setHabilitarServicioCIEX(false);
                    servicio.setHabilitarServicioPatologia(false);
                    servicio.setDeshabilitarCampoAsignado(true);
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ESTUDIO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_SOLICITUD_REPO)) {
                    servicio.setHabilitarServicioTipoAdministracion(true);
                    servicio.setMaeTipoAdministrativoId(null);
                    servicio.setHabilitarServicioProcedimiento(false);
                    servicio.setHabilitarServicioEspecialidad(false);
                    servicio.setHabilitarServicioCIEX(false);
                    servicio.setHabilitarServicioPatologia(false);
                    servicio.setHabilitarServicioDestino(true);
                    servicio.setObjetoPrestadorIps(new CntPrestadorSede());
                    servicio.setObjetoPrestadorIpsValor(null);
                    servicio.setDeshabilitarCampoAsignado(true);
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                }
            }

        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    public void editarServicioCamposObligatoriosParaTipoAdministracion(AusCasoBean bean) {
        try {
            AusCasoServicio servicio = bean.getServicioParaCrear();
            Maestro servicioEstado = null;
            Maestro servicioMotivo = null;
            Maestro servicioTipoADministracion = null;
            if (servicio.getMaeEstadoId() > 0) {
                servicioEstado = getMaestroRemoto().consultar(servicio.getMaeEstadoId());
            }
            if (servicio.getMaeServicioAmbitoId() > 0) {
                servicioMotivo = getMaestroRemoto().consultar(servicio.getMaeServicioAmbitoId());
            }
            if (servicio.getMaeTipoAdministrativoId() != null) {
                servicioTipoADministracion = getMaestroRemoto().consultar(servicio.getMaeTipoAdministrativoId());
            }
            if (servicioEstado != null && servicioMotivo != null && servicioTipoADministracion != null) {

                if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ASIGNADO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)
                        && servicioTipoADministracion.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_TIPO_ADMINISTRATIVO_EPS)) {
                    servicio.setHabilitarServicioProcedimiento(false);
                    servicio.setHabilitarServicioEspecialidad(false);
                    servicio.setHabilitarServicioCIEX(false);
                    servicio.setHabilitarServicioPatologia(false);
                    servicio.setDeshabilitarCampoAsignado(false);
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    bean.setListaUsuarios(getUsuarioRemoto().consultarPorEmpresa(bean.getConexion().getEmpresa().getId()));
                    bean.setHashUsuarios(convertToHashUsuarios(bean.getListaUsuarios()));
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ESTUDIO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)
                        && servicioTipoADministracion.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_TIPO_ADMINISTRATIVO_IPS)) {
                    servicio.setHabilitarServicioProcedimiento(true);
                    servicio.setHabiltarCampoProcedimiento(false);
                    servicio.setMaTecnologiaId(null);
                    servicio.setMaTecnologiaCodigo(null);
                    servicio.setMaTecnologiaValor(null);
                    servicio.setHabilitarServicioEspecialidad(true);
                    servicio.setMaServicioId(0);
                    servicio.setMaServicioCodigo(null);
                    servicio.setMaServicioValor(null);
                    servicio.setHabilitarServicioCIEX(true);
                    servicio.setObjetoDiagnostico(new Maestro());
                    servicio.setHabilitarServicioPatologia(true);
                    servicio.setObjetoPatologia(new Maestro());
                    servicio.setDeshabilitarCampoAsignado(true);
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    //bean.setListaUsuarios(getUsuarioRemoto().consultarPorRol(Integer.parseInt(PropApl.getInstance().get(PropApl.AUS_ID_FILTRO_USUARIOS))));
                    //bean.setHashUsuarios(convertToHashUsuarios(bean.getListaUsuarios()));
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ASIGNADO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)
                        && servicioTipoADministracion.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_TIPO_ADMINISTRATIVO_IPS)) {
                    servicio.setHabilitarServicioProcedimiento(true);
                    servicio.setHabiltarCampoProcedimiento(false);
                    servicio.setMaTecnologiaId(null);
                    servicio.setMaTecnologiaCodigo(null);
                    servicio.setMaTecnologiaValor(null);
                    servicio.setHabilitarServicioEspecialidad(true);
                    servicio.setMaServicioId(0);
                    servicio.setMaServicioCodigo(null);
                    servicio.setMaServicioValor(null);
                    servicio.setHabilitarServicioCIEX(true);
                    servicio.setObjetoDiagnostico(new Maestro());
                    servicio.setHabilitarServicioPatologia(true);
                    servicio.setObjetoPatologia(new Maestro());
                    servicio.setDeshabilitarCampoAsignado(false);
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    bean.setListaUsuarios(getUsuarioRemoto().consultarPorRol(Integer.parseInt(PropApl.getInstance().get(PropApl.AUS_ID_FILTRO_USUARIOS))));
                    bean.setHashUsuarios(convertToHashUsuarios(bean.getListaUsuarios()));
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_RESUELTO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)
                        && servicioTipoADministracion.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_TIPO_ADMINISTRATIVO_IPS)) {
                    servicio.setHabilitarServicioTipoAdministracion(false);
                    servicio.setHabilitarServicioProcedimiento(false);
                    servicio.setHabilitarServicioEspecialidad(false);
                    servicio.setHabilitarServicioCIEX(false);
                    servicio.setHabilitarServicioPatologia(false);
                    servicio.setHabilitarServicioDestino(false);
                    servicio.setDeshabilitarCampoAsignado(true);
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    //bean.setListaUsuarios(getUsuarioRemoto().consultarPorRol(Integer.parseInt(PropApl.getInstance().get(PropApl.AUS_ID_FILTRO_USUARIOS))));
                   // bean.setHashUsuarios(convertToHashUsuarios(bean.getListaUsuarios()));
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_RESUELTO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)
                        && servicioTipoADministracion.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_TIPO_ADMINISTRATIVO_EPS)) {
                    servicio.setHabilitarServicioTipoAdministracion(false);
                    servicio.setHabilitarServicioProcedimiento(false);
                    servicio.setHabilitarServicioEspecialidad(false);
                    servicio.setHabilitarServicioCIEX(false);
                    servicio.setHabilitarServicioPatologia(false);
                    servicio.setHabilitarServicioDestino(false);
                    servicio.setDeshabilitarCampoAsignado(true);
                    bean.setListaUsuarios(new ArrayList<>());
                    bean.setHashUsuarios(new HashMap<>());
                    //bean.setListaUsuarios(getUsuarioRemoto().consultarPorEmpresa(bean.getConexion().getEmpresa().getId()));
                    //bean.setHashUsuarios(convertToHashUsuarios(bean.getListaUsuarios()));
                }
            }

        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    @Override
    public void validarCamposObligatorios(AusCasoBean bean) {
        try {
            validarCamposEstadoAmbito(bean);
            validarCamposEstadoAmbitoTipoAministrativo(bean);
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    public void validarCamposEstadoAmbito(AusCasoBean bean) {
        try {

            AusCasoServicio servicio = bean.getServicioParaCrear();
            Maestro servicioEstado = null;
            Maestro servicioMotivo = null;
            if (servicio.getMaeEstadoId() > 0) {
                servicioEstado = getMaestroRemoto().consultar(servicio.getMaeEstadoId());
            }
            if (servicio.getMaeServicioAmbitoId() > 0) {
                servicioMotivo = getMaestroRemoto().consultar(servicio.getMaeServicioAmbitoId());
            }
            if (servicioEstado != null && servicioMotivo != null) {
                if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ASIGNADO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_PRESTACION_SERVICIO)) {
                    if (servicio.getMaServicioId() == 0) {
                        bean.addError("El campo Especialidad es obligatorio");
                    }
                    if (servicio.getMaDiagnosticosId() == null) {
                        bean.addError("El campo CIE-X es obligatorio");
                    }
                    if (servicio.getIdUsuarioResponsable() == null || servicio.getIdUsuarioResponsable().getId() == null) {
                        bean.addError("El campo asignado es obligatorio");
                    }
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ESTUDIO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_PRESTACION_SERVICIO)) {
                    if (servicio.getMaServicioId() == 0) {
                        bean.addError("El campo Especialidad es obligatorio");
                    }
                    if (servicio.getMaDiagnosticosId() == null) {
                        bean.addError("El campo CIE-X es obligatorio");
                    }
                    if (servicio.getObjetoPrestadorIps() == null || servicio.getObjetoPrestadorIps().getId() == null) {
                        bean.addError("El campo destino es obligatorio");
                    }

                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_RESUELTO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_PRESTACION_SERVICIO)) {
                    if (servicio.getMaServicioId() == 0) {
                        bean.addError("El campo Especialidad es obligatorio");
                    }
                    if (servicio.getMaDiagnosticosId() == null) {
                        bean.addError("El campo CIE-X es obligatorio");
                    }
                    if (servicio.getObjetoPrestadorIps() == null || servicio.getObjetoPrestadorIps().getId() == null) {
                        bean.addError("El campo destino es obligatorio");
                    }
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_AUDITAR)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_SIN_AUDITORIA)) {
                    if (servicio.getMaServicioId() == 0) {
                        bean.addError("El campo Especialidad es obligatorio");
                    }
                    if (servicio.getMaDiagnosticosId() == null) {
                        bean.addError("El campo CIE-X es obligatorio");
                    }
                    if (servicio.getObjetoPrestadorIps() == null || servicio.getObjetoPrestadorIps().getId() == null) {
                        bean.addError("El campo destino es obligatorio");
                    }
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_RESUELTO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_SIN_AUDITORIA)) {
                    if (servicio.getMaServicioId() == 0) {
                        bean.addError("El campo Especialidad es obligatorio");
                    }
                    if (servicio.getMaDiagnosticosId() == null) {
                        bean.addError("El campo CIE-X es obligatorio");
                    }
                    if (servicio.getObjetoPrestadorIps() == null || servicio.getObjetoPrestadorIps().getId() == null) {
                        bean.addError("El campo destino es obligatorio");
                    }
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ESTUDIO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_SOLICITUD_REPO)) {
                    if (servicio.getMaServicioId() == 0) {
                        bean.addError("El campo Especialidad es obligatorio");
                    }
                    if (servicio.getMaDiagnosticosId() == null) {
                        bean.addError("El campo CIE-X es obligatorio");
                    }
                }
            }
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    public void validarCamposEstadoAmbitoTipoAministrativo(AusCasoBean bean) {
        try {
            AusCasoServicio servicio = bean.getServicioParaCrear();
            Maestro servicioEstado = null;
            Maestro servicioMotivo = null;
            Maestro servicioTipoADministracion = null;
            if (servicio.getMaeEstadoId() > 0) {
                servicioEstado = getMaestroRemoto().consultar(servicio.getMaeEstadoId());
            }
            if (servicio.getMaeServicioAmbitoId() > 0) {
                servicioMotivo = getMaestroRemoto().consultar(servicio.getMaeServicioAmbitoId());
            }
            if (servicio.getMaeTipoAdministrativoId() != null) {
                servicioTipoADministracion = getMaestroRemoto().consultar(servicio.getMaeTipoAdministrativoId());
            }
            if (servicioEstado != null && servicioMotivo != null && servicioTipoADministracion != null) {

                if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ASIGNADO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)
                        && servicioTipoADministracion.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_TIPO_ADMINISTRATIVO_EPS)) {
                    if (servicio.getMaServicioId() == 0) {
                        bean.addError("El campo Especialidad es obligatorio");
                    }
                    if (servicio.getMaDiagnosticosId() == null) {
                        bean.addError("El campo CIE-X es obligatorio");
                    }
                    if (servicio.getIdUsuarioResponsable() == null || servicio.getIdUsuarioResponsable().getId() == null) {
                        bean.addError("El campo asignado es obligatorio");
                    }
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ESTUDIO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)
                        && servicioTipoADministracion.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_TIPO_ADMINISTRATIVO_IPS)) {
                    if (servicio.getObjetoPrestadorIps() == null || servicio.getObjetoPrestadorIps().getId() == null) {
                        bean.addError("El campo destino es obligatorio");
                    }
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_ASIGNADO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)
                        && servicioTipoADministracion.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_TIPO_ADMINISTRATIVO_IPS)) {
                    if (servicio.getIdUsuarioResponsable() == null || servicio.getIdUsuarioResponsable().getId() == null) {
                        bean.addError("El campo asignado es obligatorio");
                    }
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_RESUELTO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)
                        && servicioTipoADministracion.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_TIPO_ADMINISTRATIVO_IPS)) {
                    if (servicio.getMaServicioId() == 0) {
                        bean.addError("El campo Especialidad es obligatorio");
                    }
                    if (servicio.getMaDiagnosticosId() == null) {
                        bean.addError("El campo CIE-X es obligatorio");
                    }
                    if (servicio.getObjetoPrestadorIps() == null || servicio.getObjetoPrestadorIps().getId() == null) {
                        bean.addError("El campo destino es obligatorio");
                    }
                } else if (servicioEstado.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_ESTADO_RESUELTO)
                        && servicioMotivo.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO)
                        && servicioTipoADministracion.contieneAccion(MaestroAccion.AUS_CASOS_SERVICIOS_TIPO_ADMINISTRATIVO_EPS)) {
                    if (servicio.getMaServicioId() == 0) {
                        bean.addError("El campo Especialidad es obligatorio");
                    }
                    if (servicio.getMaDiagnosticosId() == null) {
                        bean.addError("El campo CIE-X es obligatorio");
                    }
                    if (servicio.getObjetoPrestadorIps() == null || servicio.getObjetoPrestadorIps().getId() == null) {
                        bean.addError("El campo destino es obligatorio");
                    }
                }
            }

        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void cargas(AusCasoBean bean) {
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

    @Override
    public void listarSedes(AusCasoBean bean) {
        if (bean.getObjeto().getUbicacion().getId() != null) {
            /*if (bean.getParamConsulta(3).getFiltros() == null) {
                bean.getParamConsulta(3).setFiltros(new HashMap());
            }*/
            //bean.getParamConsulta(3).getFiltros().put("ubicacion.id", bean.getObjeto().getUbicacion().getId());
            //bean.getParamConsulta(3).setOrden(null);
            //bean.getParamConsulta(3).setEmpresaId(bean.getConexion().getEmpresa().getId());
            try {
                bean.getObjeto().setListaSedes(getSedeRemoto().consultarListaPorUbicacion(bean.getObjeto().getUbicacion().getId(), bean.getConexion().getEmpresa().getId()));
            } catch (Exception ex) {
                Logger.getLogger(AusCasoServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void listarPersonasHistorial(AusCasoBean bean) {
        try {
            if (bean.getDocumentoPersona() != null && !bean.getDocumentoPersona().equals("")) {
                if (bean.getParamConsulta(1).getFiltros() == null) {
                    bean.getParamConsulta(1).setFiltros(new HashMap());
                }
                bean.getParamConsulta(1).getFiltros().put("documento", bean.getDocumentoPersona());
            }
            bean.getParamConsulta(1).setCantidadRegistros(getPersonaRemoto().consultarCantidadLista(bean.getParamConsulta(1)));
            bean.setListaPersonasHistorial(getPersonaRemoto().consultarLista(bean.getParamConsulta(1)));
        } catch (Exception e) {
            bean.addError(e.getMessage());

        }
    }

    private void listarSedesPrescriptora(AusCasoBean bean) {
        try {
            bean.getParamConsulta(0).setCantidadRegistros(getCntPrestadorRemoto().consultarCantidadListaSede(bean.getParamConsulta(0)));
            bean.setListaPrestadorSedes(getCntPrestadorRemoto().consultarListaSede(bean.getParamConsulta(0)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarMotivoPorAmbito(AusCasoBean bean) {
        try {
            String idAmbito = String.valueOf(bean.getServicioParaCrear().getMaeServicioAmbitoId());
            bean.setListaTipoServicioMotivo(getMaestroRemoto().consultarPorTipo(idAmbito));
        } catch (Exception ex) {
            Logger.getLogger(AusCasoServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modificarInfoBasicaCaso(AusCasoBean bean) {
        try {
            AusCaso caso = bean.getObjeto();
            caso.setEmpresa(bean.getConexion().getEmpresa());
            bean.auditoriaModificar(caso);
            getCasoRemoto().actualizar(caso);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public List<Date> obtenerFechas(Date fecha) {
        List<Date> resultado = new ArrayList<>();
        try {
            resultado = getCasoRemoto().consultarFechasNoHabiles(fecha);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return resultado;
    }

    private void notificarPorCorreoServicio(int idUsuario, int idServicio, int idCaso, String motivo) {
        try {
            if (idUsuario > 0) {
                Usuario responsable = getUsuarioRemoto().consultar(idUsuario);
                if (responsable != null) {
                    if (responsable.getCorreoElectronico() != null) {
                        String encabezado = "Asignación de servicio";
                        StringBuilder mensaje = new StringBuilder();
                        mensaje.append("SAVIA SALUD EPS a través de su sistema de información CONEXIONES le informa que su PQRD queda radicada con el número de solicitud ");
                        mensaje.append(idCaso);
                        String email = responsable.getCorreoElectronico();
                        GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_AUS_CASOS, email, encabezado, mensaje.toString(), GnCorreoEnvio.TIPO_TEXTO);
                        getGnCorreoEnvioRemoto().insertar(envio);
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AusCasoServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void notificarPorCorreoCasoCreado(Usuario responsable, AusPersona persona, int idCaso, String motivo) {
        try {
            String encabezado = "Nuevo Caso";
            StringBuilder mensaje = new StringBuilder();
            mensaje.append("SAVIA SALUD EPS a través de su sistema de información CONEXIONES le informa que su PQRD queda radicada con el número de solicitud ");
            mensaje.append(idCaso);
            if (persona.getCorreoElectronico() != null && !persona.getCorreoElectronico().equals("")) {
                String email = persona.getCorreoElectronico();
                GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_AUS_CASOS, email, encabezado, mensaje.toString(), GnCorreoEnvio.TIPO_TEXTO);
                getGnCorreoEnvioRemoto().insertar(envio);
            }
            if (responsable.getCorreoElectronico() != null && !responsable.getCorreoElectronico().equals("")) {
                String email = responsable.getCorreoElectronico();
                GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_AUS_CASOS, email, encabezado, mensaje.toString(), GnCorreoEnvio.TIPO_TEXTO);
                getGnCorreoEnvioRemoto().insertar(envio);

            }
        } catch (Exception ex) {
            Logger.getLogger(AusCasoServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void notificarPorCorreoCasoCerrado(Usuario responsable, AusPersona persona, int idCaso, String motivo) {
        try {
            String encabezado = "Cierre caso";
            StringBuilder mensaje = new StringBuilder();
            mensaje.append("SAVIA SALUD EPS a través de su sistema de información CONEXIONES le informa que su PQRD queda radicada con el número de solicitud ");
            mensaje.append(idCaso);
            if (persona.getCorreoElectronico() != null) {
                String email = persona.getCorreoElectronico();
                GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_AUS_CASOS, email, encabezado, mensaje.toString(), GnCorreoEnvio.TIPO_TEXTO);
                getGnCorreoEnvioRemoto().insertar(envio);

            }
            responsable = getUsuarioRemoto().consultar(responsable.getId());
            if (responsable.getCorreoElectronico() != null) {
                String email = responsable.getCorreoElectronico();
                GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_AUS_CASOS, email, encabezado, mensaje.toString(), GnCorreoEnvio.TIPO_TEXTO);
                getGnCorreoEnvioRemoto().insertar(envio);

                /*new Thread(new Correo(email, "Cierre caso",
                        "SAVIA SALUD EPS a través de su sistema de información CONEXIONES le informa que su PQRD queda radicada con el número de solicitud " + idCaso)).start();*/
            }

        } catch (Exception ex) {
            Logger.getLogger(AusCasoServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contarVencidos(AusCasoBean bean) {
        try {
            if (bean.getConexion().getEmpresa().getId() != 1) {
                bean.getParamConsulta(0).setParametroConsulta3(bean.getConexion().getUsuario().getId());
            }
            bean.setCantidadVencidos(getCasoRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cierreAutomaticoCaso(AusCasoBean bean) {
        try {
            boolean validar = true;
            //int idCierre = bean.getListaEstadosServicio().get("Cerrado");
            int idCierre = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO));
            int idEnteControl = bean.obtenerSuperSalud();
            AusCaso caso = new AusCaso();
            caso = getCasoRemoto().consultar(bean.getIdCasoCierre());
            if (!caso.getMultireparto()) {
                if (caso.getMaeSolicitudEnteControlId() != idEnteControl) {
                    for (AusCasoServicio service : caso.getServiciosList()) {
                        if (service.getMaeEstadoId() != idCierre) {
                            validar = false;
                        }
                    }
                    if (validar) {
                        //caso.setMaeSolicitudEstadoId(bean.getIdTipoEstadoSolicitud("Cerrado"));
                        caso.setMaeSolicitudEstadoId(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ESTADO_CERRADO)));
                        getCasoRemoto().actualizar(caso);

                        AusSeguimiento seguimiento = new AusSeguimiento();
                        seguimiento.setCasosId(caso);
                        //seguimiento.setMaeEstadoId(bean.getIdTipoSeguimiento("Cerrado"));
                        seguimiento.setMaeEstadoId(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SEGUIMIENTO_ESTADO_CERRADO)));
                        seguimiento.setObservacion(bean.getDescripcionServicio() == null ? "Cierre automatico" : bean.getDescripcionServicio());
                        seguimiento.setTerminalCrea(bean.getConexion().getIp());
                        seguimiento.setUsuarioCrea(bean.getConexion().getUsuario().getNombre());
                        seguimiento.setFechaHoraCrea(new Date());
                        getSeguimientoRemoto().insertar(seguimiento);
                        String motivos = "''";
                        for (AusCasoServicio service : caso.getServiciosList()) {
                            motivos += bean.getTipoServicioMotivo(service.getMaeServicioMotivoId()) + ",";
                        }
                        motivos = StringUtils.removeEnd(motivos, ",");
                        motivos += "''";
                        notificarPorCorreoCasoCerrado(caso.getResponsableUsuariosId(), caso.getAusPersonasId(), caso.getId(), motivos);
                        bean.addMensaje("El caso se cerro automaticamente por el sistema");
                    } else {
                        bean.addError("El caso no se cerro automaticamente ya que hay servicios sin cerrar");
                    }
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void verServiciosResueltos(AusCasoBean bean) {
        try {
            bean.setListaServiciosResultos(getServicioRemoto().consultarServiciosCerradoOresueltos(bean.getObjServicioResuelto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crearRevertirCaso(AusCasoBean bean) {
        try {
            bean.setObjRevertirCaso(getCasoRemoto().consultar(bean.getObjRevertirCaso().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crearCerrarCaso(AusCasoBean bean) {
        try {
            bean.setListaCerrarCaso(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_CASO_CAUSA_CERRADO));
            bean.setHashCerrarCaso(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_CASO_CAUSA_CERRADO));
            bean.setObjCerrarCaso(getCasoRemoto().consultar(bean.getObjCerrarCaso().getId()));
            bean.getObjCerrarCaso().setServiciosList(getServicioRemoto().consultarServiciosTodoServicios(bean.getObjCerrarCaso().getId()));
            for (AusCasoServicio servicio : bean.getObjCerrarCaso().getServiciosList()) {
                if (servicio.getMaeEstadoId() != Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO))
                        && servicio.getMaeEstadoId() != Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_RESUELTO))) {
                    bean.addError("No puede cerrar el caso todavia no a resuelto servicios");
                    break;
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void revertirCaso(AusCasoBean bean) {
        try {
            AusCaso caso = bean.getObjRevertirCaso();
            bean.auditoriaModificar(caso);
            Maestro estadoCaso = bean.getHashTipoEstadoSolicitud().get(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.AUS_CASO_REABIERTO)));
            if (estadoCaso != null) {
                caso.setMaeSolicitudEstadoId(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.AUS_CASO_REABIERTO)));
                caso.setMaeSolicitudEstadoCodigo(estadoCaso.getValor());
                caso.setMaeSolicitudEstadoValor(estadoCaso.getNombre());
            }
            Maestro motivoReveritirCaso = bean.getHashMotivoReabrirCaso().get(caso.getMaeMotivoReabreId());
            if (motivoReveritirCaso != null) {
                caso.setMaeMotivoReabreCodigo(motivoReveritirCaso.getValor());
                caso.setMaeMotivoReabreValor(motivoReveritirCaso.getNombre());
            }
            getCasoRemoto().actualizarReabrirCaso(caso);
            guardarSeguimientoReabrirCaso(bean, caso);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarCerrarCaso(AusCasoBean bean) {
        try {
            AusCaso caso = bean.getObjCerrarCaso();
            bean.auditoriaModificar(caso);
            Maestro estadoCaso = bean.getHashTipoEstadoSolicitud().get(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ESTADO_CERRADO)));
            if (estadoCaso != null) {
                caso.setMaeSolicitudEstadoId(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ESTADO_CERRADO)));
                caso.setMaeSolicitudEstadoCodigo(estadoCaso.getValor());
                caso.setMaeSolicitudEstadoValor(estadoCaso.getNombre());
            }
            Maestro estadoCasoCerrado = bean.getHashCerrarCaso().get(caso.getMaeCasoCerradoId());
            if (estadoCasoCerrado != null) {
                caso.setMaeCasoCerradoCodigo(estadoCasoCerrado.getValor());
                caso.setMaeCasoCerradoValor(estadoCasoCerrado.getNombre());
            }
            //2024-10-31 jyperez cierre de caso
            caso.setOrigenCierre(AusCasoBean.ORIGEN_CIERRE_MANUAL);
            getCasoRemoto().actualizarCerrarCaso(caso);
            guardarSeguimientoCerrarCaso(bean, caso);
            bean.addMensaje("El caso se cerró exitosamente");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void actualizarListaCie(AusCasoBean bean) {
        boolean validar = false;
        try {
            if (bean.getParamConsulta(4).getFiltros() == null) {
                bean.getParamConsulta(4).setFiltros(new HashMap<>());
            }
            if (bean.getCodigoCie() != null || bean.getNombreCie() != null) {
                if (bean.getCodigoCie() != null) {
                    bean.getParamConsulta(4).getFiltros().put("valor", bean.getCodigoCie());
                    validar = true;
                }
                if (bean.getNombreCie() != null && bean.getNombreCie().length() >= 7) {
                    bean.getParamConsulta(4).getFiltros().put("nombre", bean.getNombreCie());
                    validar = true;
                }
            }
            if (validar) {
                bean.setListaTipoDiagnostico(getMaestroRemoto().consultarLista(bean.getParamConsulta(4)));
                HashMap<Integer, Maestro> newHash = new HashMap<>();
                for (Maestro mae : bean.getListaTipoDiagnostico()) {
                    newHash.put(mae.getId(), mae);
                }
                bean.setHashTipoDiagnostico(newHash);
            } else {
                if (bean.getIdCie() != null) {
                    HashMap<Integer, Maestro> newHash = new HashMap<>();
                    Maestro mae = getMaestroRemoto().consultar(bean.getIdCie());
                    newHash.put(mae.getId(), mae);
                    bean.setHashTipoDiagnostico(newHash);
                }
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void actualizarListaCie2(AusCasoBean bean) {
        boolean validar = false;
        try {
            if (bean.getParamConsulta().getFiltros() == null) {
                bean.getParamConsulta().setFiltros(new HashMap<>());
            }
//            bean.getParamConsulta().getFiltros().put("tipo", MaestroTipo.DIAGNOSTICO);
            bean.setListaTipoDiagnostico(getMaestroRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public HashMap<Integer, Usuario> convertToHashUsuarios(List<Usuario> list) {
        HashMap<Integer, Usuario> map = new HashMap<>();
        for (Usuario i : list) {
            map.put(i.getId(), i);
        }
        return map;
    }

    public void castEntidadNegocio(AusPersonaBean bean, AsegAfiliado afiliadoDto) {
        AusPersona persona = bean.getObjeto();
        persona.setNombres(afiliadoDto.getNombres());
        persona.setApellidos(afiliadoDto.getApellidos());
        persona.setAseg_afiliados_id(afiliadoDto.getId());
        int idMaeTipoDoc = 0;
        String tipoDocumentoCodigo = "";
        String tipoDocumentoValor = "";
        /*for (Map.Entry<Integer, Maestro> entry : bean.getHashTiposDocumento().entrySet()) {
            Maestro maestro = entry.getValue();
            if (afiliadoDto.getMaeTipoDocumentoValor().equalsIgnoreCase(maestro.getValor())) {
                idMaeTipoDoc = maestro.getId();
                break;
            }
        }*/
        for (Map.Entry<Integer, Maestro> entry : bean.getHashTiposDocumento().entrySet()) {
            Maestro maestro = entry.getValue();
            if (afiliadoDto.getMaeTipoDocumentoCodigo().equals(maestro.getValor())) {
                idMaeTipoDoc = maestro.getId();
                tipoDocumentoCodigo = maestro.getValor();
                tipoDocumentoValor = maestro.getNombre();
                break;
            }
        }
        persona.setMae_tipo_documento_id(idMaeTipoDoc);
        persona.setMae_tipo_documento_codigo(tipoDocumentoCodigo);
        persona.setMae_tipo_documento_valor(tipoDocumentoValor);

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse(afiliadoDto.getFechaNacimiento().toString());
        } catch (ParseException ex) {
            Logger.getLogger(AusPersonaServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        persona.setFechaNacimiento(date);
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
        persona.setMae_sexo_id(idSexo);
        persona.setMae_sexo_codigo(codigoSexo);
        persona.setMae_sexo_valor(valorSexo);
        int idEstadoAfiliacion = 0;
        if (bean.getHashEstadosPersona() != null && afiliadoDto.getMaeEstadoAfiliacionValor() != null) {
            for (Map.Entry<Integer, Maestro> entry : bean.getHashEstadosPersona().entrySet()) {
                Maestro maestro = entry.getValue();
                if (afiliadoDto.getMaeEstadoAfiliacionValor().equalsIgnoreCase(maestro.getDescripcion())) {
                    idEstadoAfiliacion = maestro.getId();
                    break;
                }
            }
        }
        //persona.setMae_estado_id(idEstadoAfiliacion);
        persona.setMae_estado_id(afiliadoDto.getMaeEstadoAfiliacion());
        persona.setMae_estado_codigo(afiliadoDto.getMaeEstadoAfiliacionCodigo());
        persona.setMae_estado_valor(afiliadoDto.getMaeEstadoAfiliacionValor());
        persona.setCorreoElectronico(afiliadoDto.getEmail());
        if (afiliadoDto.getResidenciaUbicacion() != null) {
            persona.setPersonaUbicacion(new Ubicacion(afiliadoDto.getResidenciaUbicacion().getId()));
        }
        boolean diascapacidad = !afiliadoDto.getDiscapacidadStr().equalsIgnoreCase("NO");
        persona.setDiscapacidad(diascapacidad);
        persona.setDireccion(afiliadoDto.getDireccionResidencia());
        //persona.setContrato(afiliadoDto.getC ContratoInternoAfilado());
        //int regimen = afiliadoDto.getRegimen().equalsIgnoreCase("Subsidiado") ? 1 : 0;
        Boolean regimen = afiliadoDto.getRegimen().equalsIgnoreCase("1") ? true : false;
        //Boolean regimen = afiliadoDto.getRegimen().equalsIgnoreCase("Subsidiado") ? true : false;
        persona.setRegimen(regimen);
        persona.setGestante(false);
        String telefono = afiliadoDto.getTelefonoMovil();
        boolean agregarListaTel = (telefono != null && !telefono.trim().equals(""));
        if (agregarListaTel) {
            //if (telefono.length() <= 10){
            //try {
            //if(Integer.parseInt(telefono) < 2147483647){
            List<AusPersonaTelefono> listaTelefonos = new ArrayList<>();
            AusPersonaTelefono personatTelefono = new AusPersonaTelefono();
            personatTelefono.setNumero(telefono);
            listaTelefonos.add(personatTelefono);
            persona.setListaTelefonos(listaTelefonos);
            bean.setListaausPersonaTelefono(persona.getListaTelefonos());
            //}
            //} catch (Exception e) {
            //bean.addError("El numero de telefono es muy grande");
            //}
            //}  
        }
        persona.setEsAfiliado(true);
    }

    @Override
    public void cargaInial(AusCasoBean bean) {
        try {
            bean.setListaEstadosServicioEnCreacion(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.AUS_SERVICIO_ESTADO, Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_ASIGNADO))));

            bean.setUbicaciones(UbicacionSingle.getInstance().getListaMunicipiosAntioquia());
            bean.setUbicacionesRecursiva(UbicacionSingle.getInstance().getHashMunicipiosAntioquia());

            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            bean.setHashTiposDocumento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));

            bean.setListaSexo(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_SEXO));
            bean.setHashSexo(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_SEXO));

            bean.setListaTipoEstadoSolicitud(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_ESTADO));
            bean.setHashTipoEstadoSolicitud(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SOLICITUD_ESTADO));

            bean.setListaTipoEstadoPersona(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            bean.setHashTipoEstadosPersona(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));

            bean.setListaTipoSolicitud(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_TIPO));
            bean.setHashTipoSolicitud(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SOLICITUD_TIPO));

            bean.setListaTipoSolicitudOrigen(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_ORIGEN));
            bean.setHashTipoSolicitudOrigen(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SOLICITUD_ORIGEN));

            bean.setListaTipoSolicitudPrioridad(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_PRIORIDAD));
            bean.setHashTipoSolicitudPrioridad(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SOLICITUD_PRIORIDAD));

            bean.setListaCanalSuperSalud(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_CANAL_SUPER_SALUD));
            bean.setHashCanalSuperSalud(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_CANAL_SUPER_SALUD));

            bean.setListaTipoSolicitudEnteControl(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_ENTE_CONTROL));
            bean.setHashTipoSolicitudEnteControl(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SOLICITUD_ENTE_CONTROL));

            bean.setListaTipoSolicitudRiesgoVida(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_RIESGO_VIDA));
            bean.setHashTipoSolicitudRiesgoVida(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SOLICITUD_RIESGO_VIDA));

            bean.setListaEstadoServicio(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SERVICIO_ESTADO));
            bean.setHashEstadoServicio(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SERVICIO_ESTADO));

            bean.setListaTipoServicioAmbito(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SERVICIO_AMBITO));
            bean.setHashTipoServicioAmbito(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SERVICIO_AMBITO));

            bean.setListaTipoServicioMotivo(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SERVICIO_MOTIVO));
            bean.setHashTipoServicioMotivo(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SERVICIO_MOTIVO));

            bean.setListaTipoPatologia(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_PATOLOGIA));
            bean.setHashTipoPatologia(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_PATOLOGIA));

            bean.setListaTipoSeguimiento(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SEGUIMIENTO_TIPO));
            bean.setHashTipoSeguimiento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SEGUIMIENTO_TIPO));

            bean.setListaTipoAdministrativo(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SERVICIO_TIPO_ADMINISTRATIVO));
            bean.setHashTipoAdministrativo(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SERVICIO_TIPO_ADMINISTRATIVO));

            bean.setListaMotivoReabrirCaso(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_CASO_MOTIVO_REABIERTO));
            bean.setHashMotivoReabrirCaso(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_CASO_MOTIVO_REABIERTO));

            bean.setUrlDescargaAdjuntosCaso(PropApl.getInstance().get(PropApl.AUS_RUTA_CARGA));
            bean.setHorasSeguimiento(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.AUS_HORAS_SERVIC)));

            bean.setListaTiposDocumentoEmpresa(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));

        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    @Override
    public void consultarCasosTecnologia(AusCasoBean bean) {
        try {
            AusCaso caso = new AusCaso();
            //le asignados los datos necesarios para la validación
            caso.setAusPersonasId(bean.getObjeto().getAsuPersonasId());
            //le asignamos el objeto que contiene la información del caso consultado o a crear.
            caso.setServicio(bean.getServicioParaConsultar());
            bean.setListaCasosSimilares(getCasoRemoto().consultarPorAfiliadoTipoTecnologiaYEstado(caso));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
}
