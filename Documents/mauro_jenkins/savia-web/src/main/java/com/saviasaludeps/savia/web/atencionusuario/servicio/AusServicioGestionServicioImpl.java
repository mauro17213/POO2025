/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.atencionusuario.servicio;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.GnCorreoEnvio;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.atencionusuario.AusAdjunto;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCaso;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCasoServicio;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersona;
import com.saviasaludeps.savia.dominio.atencionusuario.AusSeguimiento;
import com.saviasaludeps.savia.dominio.atencionusuario.AusServicioGestionHistorico;
import com.saviasaludeps.savia.negocio.administracion.EmpresaRemoto;
import com.saviasaludeps.savia.negocio.administracion.GnCorreoEnvioRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusCasoRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusCasoServicioRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusPersonaRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusSeguimientoRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusServicioGestionHistoricoRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusServicioGestionRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusServicioRemoto;
import com.saviasaludeps.savia.web.atencionusuario.bean.AusServicioGestionBean;
import com.saviasaludeps.savia.web.atencionusuario.utilities.PropAtencionUsuario;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author pavacca
 */
public class AusServicioGestionServicioImpl extends AccionesBO implements AusServicioGestionServicioIface{
    
    private AusServicioRemoto getServicioRemoto() throws Exception {
        return (AusServicioRemoto) RemotoEJB.getEJBRemoto("AusServicioServicio", AusServicioRemoto.class.getName());
    }

    private AusAdjuntoRemoto getAdjuntoRemoto() throws Exception {
        return (AusAdjuntoRemoto) RemotoEJB.getEJBRemoto("AusAdjuntoServicio", AusAdjuntoRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }
 
    private AusServicioGestionRemoto getServicioGestionRemoto() throws Exception {
        return (AusServicioGestionRemoto) RemotoEJB.getEJBRemoto("AusServicioGestionServicio", AusServicioGestionRemoto.class.getName());
    }
    
    private AusServicioGestionHistoricoRemoto getServicioGestionHistoricoRemotoRemoto() throws Exception {
        return (AusServicioGestionHistoricoRemoto) RemotoEJB.getEJBRemoto("AusServicioGestionHistoricoServicio", AusServicioGestionHistoricoRemoto.class.getName());
    }
    
    private AusCasoRemoto getCasoRemoto() throws Exception {
        return (AusCasoRemoto) RemotoEJB.getEJBRemoto("AusCasoServicio", AusCasoRemoto.class.getName());
    }
    
    private AusSeguimientoRemoto getSeguimientoRemoto() throws Exception {
        return (AusSeguimientoRemoto) RemotoEJB.getEJBRemoto("AusSeguimientoServicio", AusSeguimientoRemoto.class.getName());
    }

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }
    
    private AusPersonaRemoto getPersonaRemoto() throws Exception {
        return (AusPersonaRemoto) RemotoEJB.getEJBRemoto("AusPersonaServicio", AusPersonaRemoto.class.getName());
    }
    
    private EmpresaRemoto getEmpresaRemoto() throws Exception {
        return (EmpresaRemoto) RemotoEJB.getEJBRemoto("EmpresaServicio", EmpresaRemoto.class.getName());
    }
    
    private GnCorreoEnvioRemoto getGnCorreoEnvioRemoto() throws Exception { 
        return (GnCorreoEnvioRemoto) RemotoEJB.getEJBRemoto("GnCorreoEnvioServicio", GnCorreoEnvioRemoto.class.getName());
    }
    
     private AusCasoServicioRemoto getAusCasoServicioRemoto() throws Exception {
        return (AusCasoServicioRemoto) RemotoEJB.getEJBRemoto("AusCasoServicioServicio", AusCasoServicioRemoto.class.getName());
    }
    
    @Override
    public void Accion(AusServicioGestionBean bean) {
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
                    switch(bean.getDoAccion()){
                        case Url.ACCION_MODIFICAR:
                            modificar(bean);
                            break;
                        case AusServicioGestionBean.ACCION_MODIFICAR_CIERRE_AUTOMATICO:
                            modificarCerradoAutomatico(bean);
                            break;
                    }
                    break;
                case Url.ACCION_BORRAR:
                    borrar(bean);
                    break;
                case Url.ACCION_ADICIONAL_3:
                    contarVencidos(bean);
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }
    
    private void listar(AusServicioGestionBean bean) {
        try {
            if (!bean.isAccionAdicional2()){
                bean.getParamConsulta().setParametroConsulta1(bean.getConexion().getUsuario().getId());
            }
            
            int estadoCerrado = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO));
            int estadoAsignado = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_ASIGNADO));
            int estadoEstudio = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_ESTUDIO));
                      
            bean.getParamConsulta().setCantidadRegistros(getServicioGestionRemoto().consultarCantidadListaEstadoAsignado(bean.getParamConsulta(),estadoAsignado, estadoCerrado,estadoEstudio));
            bean.setRegistros(getServicioGestionRemoto().consultarListaEstadoAsignado(bean.getParamConsulta(),estadoAsignado, estadoCerrado, estadoEstudio));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(AusServicioGestionBean bean) {
        try {
            bean.setObjeto(getServicioRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setDescripcion("");
            bean.getObjeto().setMaeEstadoId(0);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(AusServicioGestionBean bean) {
        try {
            bean.setObjeto(new AusCasoServicio());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(AusServicioGestionBean bean) {
        try {
         
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(AusServicioGestionBean bean) {
        try {
            int idUsuario  = 0;
            AusCaso caso = new AusCaso();
            if(bean.getIdCaso() != 0){
                caso = getCasoRemoto().consultar(bean.getIdCaso());
                idUsuario = caso.getResponsableUsuariosId().getId();
            }
           
            if(bean.getObjeto().getMaeEstadoId() == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO))){
                if(caso.getServiciosList().size() > 1){
                    if(bean.getObjeto().getMaeEstadoId() == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO))){
                        bean.getObjeto().setMaeEstadoId(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO)));
                        bean.addMensaje("El servicio cambio su estado a resuelto ya que no se puede cerrar porque hay otros servicios activos");
                    }
                }
            }
            
            AusCasoServicio servicioDB = getServicioRemoto().consultar(bean.getObjeto().getId());  
            AusCasoServicio servicioMemoria = bean.getObjeto();
            Maestro estadoCasoServicio = bean.getHashEstadoServicio().get(servicioMemoria.getMaeEstadoId()); 
            if(estadoCasoServicio != null){
                servicioMemoria.setMaeEstadoCodigo(estadoCasoServicio.getValor());
                servicioMemoria.setMaeEstadoValor(estadoCasoServicio.getNombre());
            }
            AusServicioGestionHistorico servicioHistorico = asignarValoresServicioHistorico(servicioMemoria);
            
            insertarServicioHistorico(bean, servicioDB, servicioHistorico);
            servicioMemoria.setAdjuntosList(procesarAdjuntos(servicioMemoria.getAdjuntosList(), bean, 0, servicioDB.getId(), 0));
           
            if(bean.getObjeto().getMaeEstadoId() == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_RESUELTO))
                    || bean.getObjeto().getMaeEstadoId() ==  Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO))){
                if(idUsuario > 0){
                    getServicioRemoto().actualizar(bean.getObjeto());
                    notificarPorCorreoServicio(idUsuario,bean.getIdCaso(),bean.getObjeto().getId(), bean.getTipoServicioMotivo(servicioDB.getMaeServicioMotivoId()));
                }
            }
            bean.addMensaje("Se ha editado el servicio de manera exitosa.");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
    private void insertarServicioHistorico(AusServicioGestionBean bean, AusCasoServicio servicioDB, AusServicioGestionHistorico servicioHistorico) {
        try {
            AusCasoServicio servicioMemoria = bean.getObjeto();
            bean.auditoriaGuardar(servicioHistorico);
            servicioHistorico.setId(null);
            getServicioGestionHistoricoRemotoRemoto().insertar(servicioHistorico);
            cambiarEstadoServicio(bean, servicioDB, servicioMemoria);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    
    public void cambiarEstadoServicio(AusServicioGestionBean bean, AusCasoServicio servicioDB, AusCasoServicio servicioMemoria){
        try {
            servicioDB.setMaeEstadoId(servicioMemoria.getMaeEstadoId());
            servicioDB.setMaeEstadoCodigo(servicioMemoria.getMaeEstadoCodigo());
            servicioDB.setMaeEstadoValor(servicioMemoria.getMaeEstadoValor());
            servicioDB.setDescripcion(servicioMemoria.getDescripcion());
            servicioDB.setFechaAplica(servicioMemoria.getFechaAplica());
            bean.auditoriaModificar(servicioDB);
            getServicioRemoto().actualizar(servicioDB);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    public void cambiarDescripcionServicio(AusServicioGestionBean bean, AusCasoServicio servicioDB, AusCasoServicio servicioMemoria){
        try {
            servicioDB.setDescripcion(servicioMemoria.getDescripcion());
            bean.auditoriaModificar(servicioDB);
            getServicioRemoto().actualizar(servicioDB);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private AusServicioGestionHistorico asignarValoresServicioHistorico(AusCasoServicio servicioMemoria){
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
    
    private void modificar(AusServicioGestionBean bean) {
        try {
            int idUsuario  = 0;
            AusCaso caso = new AusCaso();
            if(bean.getIdCaso() != 0){
                caso = getCasoRemoto().consultar(bean.getIdCaso());
                caso.setServiciosList(getAusCasoServicioRemoto().consultarServiciosTodoServicios(caso.getId()));
                //idUsuario = (caso.getResponsableUsuariosId().getId() != null)? caso.getResponsableUsuariosId().getId(): idUsuario;
                //2024-04-08 jyperez cambiamos definitivamente el usuario responsable por el usuario de cierre para la notificación de correo electronico
                idUsuario = (caso.getUsuarioCierre() != null && caso.getUsuarioCierre().getId() != null)? caso.getUsuarioCierre().getId(): idUsuario;
            }
            
            int idEstadoResuelto = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_RESUELTO));
            int idEstadoCerrado = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO));
            if(bean.getObjeto().getMaeEstadoId() == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO))){
                if(caso.getServiciosList().size() > 1){
                    
                    if(bean.getObjeto().getMaeEstadoId() == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO))){
                        bean.getObjeto().setMaeEstadoId(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO)));
                        bean.addMensaje("El servicio cambio su estado a resuelto ya que no se puede cerrar porque hay otros servicios activos");
                    }
                }
            }
            
            if(bean.getObjeto().getMaeEstadoId() == idEstadoResuelto || bean.getObjeto().getMaeEstadoId() == idEstadoCerrado){
                int cantidadServiciosCerrados = (caso.getCantidadServiciosCerrados() != null)? caso.getCantidadServiciosCerrados() + 1: 1;
                caso.setCantidadServiciosCerrados(cantidadServiciosCerrados);
                getCasoRemoto().actualizarCantidadServiciosCerrados(caso);
            }     
            
            AusCasoServicio servicioDB = getServicioRemoto().consultar(bean.getObjeto().getId());  
            AusCasoServicio servicioMemoria = bean.getObjeto();
            Maestro estadoCasoServicio = bean.getHashEstadoServicio().get(servicioMemoria.getMaeEstadoId()); 
            if(estadoCasoServicio != null){
                servicioMemoria.setMaeEstadoCodigo(estadoCasoServicio.getValor());
                servicioMemoria.setMaeEstadoValor(estadoCasoServicio.getNombre());
            }
            AusServicioGestionHistorico servicioHistorico = asignarValoresServicioHistorico(servicioMemoria);
            
            insertarServicioHistorico(bean, servicioDB, servicioHistorico);
            servicioMemoria.setAdjuntosList(procesarAdjuntos(servicioMemoria.getAdjuntosList(), bean, 0, servicioDB.getId(), 0));
          
            if(bean.getObjeto().getMaeEstadoId() == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_RESUELTO))
                    || bean.getObjeto().getMaeEstadoId() == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO))){
                if(idUsuario > 0){
                    getServicioRemoto().actualizar(bean.getObjeto());
                    notificarPorCorreoServicio(idUsuario,bean.getIdCaso(),bean.getObjeto().getId(), caso.getMaeMotivoEspecificoValor());
                    if(bean.getObjeto().getMaeEstadoId() == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO))){
                        notificarPorCorreoServicioCerrado(idUsuario, bean.getIdCaso(), caso.getMaeMotivoEspecificoValor());
                    }
                }
            }
           
            bean.addMensaje("Se ha editado el servicio de manera exitosa.");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    private void modificarCerradoAutomatico(AusServicioGestionBean bean) {
        try {        
            AusCaso caso = new AusCaso();
            caso = getCasoRemoto().consultar(bean.getIdCaso());
            boolean validar = false;
            if(caso.getMultireparto()){
                //if(caso.getMaeSolicitudEnteControlId() == bean.obtenerIdEnteControlCaso("SuperSalud") ){
                //if(caso.getMaeSolicitudEnteControlId() == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ENTE_CONTROL_SUPER_SALUD)) ){
                if(caso.getServiciosList().size() == 1){
                    validar = true;
                    for(AusCasoServicio service : caso.getServiciosList()){
                            
                        if(service.getMaeEstadoId() != Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO))){
                            validar = false;
                        }
                    }
                    if(validar){
                        caso.setMaeSolicitudEstadoId(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ESTADO_GESTION)));
                        Maestro estadoCaso = bean.getHashEstadosCaso().get(caso.getMaeSolicitudEstadoId()); 
                        if(estadoCaso != null){
                            caso.setMaeSolicitudEstadoCodigo(estadoCaso.getValor());
                            caso.setMaeSolicitudEstadoValor(estadoCaso.getNombre());
                        }
                        //getCasoRemoto().actualizar(caso);

                        AusSeguimiento seguimiento = new AusSeguimiento();
                        seguimiento.setCasosId(caso);
                        seguimiento.setMaeEstadoId(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SEGUIMIENTO_ESTADO_CERRADO)));
                        Maestro estadoSeguimiento = bean.getHashEstadoSeguimiento().get(seguimiento.getMaeEstadoId()); 
                        if(estadoSeguimiento != null){
                            seguimiento.setMaeEstadoCodigo(estadoCaso.getValor());
                            seguimiento.setMaeEstadoValor(estadoCaso.getNombre());
                        }
                        seguimiento.setObservacion(bean.getDescripcion());
                        seguimiento.setTerminalCrea(bean.getConexion().getIp());
                        seguimiento.setUsuarioCrea(bean.getConexion().getUsuario().getNombre());
                        seguimiento.setFechaHoraCrea(new Date());
                        getSeguimientoRemoto().insertar(seguimiento);

                        bean.addMensaje("Se cerro un registro de manera exitosa");
                        String motivos = "''";
                        for(AusCasoServicio service : caso.getServiciosList()){
                                motivos+=  bean.getTipoServicioMotivo(service.getMaeServicioMotivoId())+",";
                        }
                        motivos = StringUtils.removeEnd(motivos, ",");
                        motivos+= "''";
                        notificarPorCorreoCaso(caso.getResponsableUsuariosId().getId(),caso.getAusPersonasId().getId(),bean.getIdCaso(), motivos);
                    }
                }
               // }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private List<AusAdjunto> procesarAdjuntos(List<AusAdjunto> adjuntosIn, Url bean,
            int idCaso, int idSer, int idSeg) {
        List<AusAdjunto> listaAdjuntoInsertados = new ArrayList();
        try {
            for (AusAdjunto adjunto : adjuntosIn) {
                bean.auditoriaModificar(adjunto);
                if (adjunto.isAdjuntoMemoria() ) {
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
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return listaAdjuntoInsertados;
    }

    private void borrar(AusServicioGestionBean bean) {
        try {
           
            AusCasoServicio ausCasoServicio =  getServicioRemoto().consultar(bean.getObjeto().getId());
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
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
 
    @Override
    public AusCasoServicio consultarServicio(int id) {
        AusCasoServicio ausCasoServicio = null;
        try {
            ausCasoServicio = getServicioRemoto().consultar(id);   
        } catch (Exception ex) {
            Logger.getLogger(AusServicioGestionServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ausCasoServicio;
    }
    
    @Override
    public void cargaInial(AusServicioGestionBean bean) {
          try {
              
            bean.setUbicacionesRecursiva(UbicacionSingle.getInstance().getHashUbicaciones());
            bean.setUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());  
            
            //bean.setUbicacionesRecursiva(getUbicacionRemoto().consultarMunicipios());
            //bean.setUbicaciones(getUbicacionRemoto().consultarPorTipo(Ubicacion.TIPO_MUNICIPIO));
            
            bean.setListaEstadosServicioGestion(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.AUS_SERVICIO_ESTADO, Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_ESTUDIO))));
            bean.setHashEstadosServicioGestionValor(Util.convertToHashValor(bean.getListaEstadosServicioGestion()));
            bean.setHashEstadosServicioGestion(Util.convertToHash(bean.getListaEstadosServicioGestion()));
            bean.setListaEstadoServicio(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SERVICIO_ESTADO));
            bean.setHashEstadoServicio(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SERVICIO_ESTADO));
            bean.setListaEstadoServicioEstadoAsignado(obtenerListaEstadoServicioFiltrada(bean.getListaEstadoServicio()));
            
            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            bean.setHashTiposDocumento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            bean.setUrlDescargaAdjuntosCaso(PropApl.getInstance().get(PropApl.AUS_RUTA_CARGA));
            bean.setListaEntesControl(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_ENTE_CONTROL));
            
            bean.setListaEstadosCaso(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_ESTADO));
            bean.setHashEstadosCaso(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SOLICITUD_ESTADO));
            
            bean.setListaEstadoSeguimiento(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SEGUIMIENTO_TIPO));
            bean.setHashEstadoSeguimiento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SEGUIMIENTO_TIPO));
            
            bean.setListaTipoMotivoServicio(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SERVICIO_MOTIVO));
            bean.setHashTipoServicioMotivo(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SERVICIO_MOTIVO));
            
            bean.setListaUsuarios(getCasoRemoto().consultarUsuarioPorEmpresa(bean.getConexion().getEmpresa().getId()));
            bean.setHashUsuarios(convertToHashUsuarios(bean.getListaUsuarios()));
            
            bean.setListaSexo(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_SEXO));
            bean.setHashSexo(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_SEXO));
            
            bean.setListaTipoEstadoPersona(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            bean.setHashTipoEstadosPersona(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            
            bean.setListaTipoSolicitudOrigen(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_ORIGEN));
            bean.setHashTipoSolicitudOrigen(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SOLICITUD_ORIGEN));
            
            bean.setListaTipoSolicitudRiesgoVida(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_RIESGO_VIDA));
            bean.setHashTipoSolicitudRiesgoVida(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SOLICITUD_RIESGO_VIDA));
            
          } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }
    
    public HashMap<Integer, Usuario> convertToHashUsuarios(List<Usuario> list) {
       HashMap<Integer, Usuario> map = new HashMap<>();
       for (Usuario i : list) {
           map.put(i.getId(), i);
       }
       return map;
    }
    
    private void notificarPorCorreoServicio(int idUsuario, int idCaso, int idServicio, String motivo){
        try {
            Usuario user = getUsuarioRemoto().consultar(idUsuario);
            String correo = user.getCorreoElectronico() == null ? "" : user.getCorreoElectronico();
            String encabezado = "Servicio finalizado";
            StringBuilder mensaje = new StringBuilder();
            mensaje.append("SAVIA SALUD EPS a través de su sistema de información CONEXIONES informa la finalizacion de un servicio ASIGNADO al prestador ");
            
            if(!correo.equals("")){
                Empresa empresa = getEmpresaRemoto().consultar(user.getEmpresa().getId());
                mensaje.append(empresa.getNombreComercial());
                mensaje.append("relacionado a ");
                mensaje.append(motivo);
                mensaje.append(" con el número de solicitud ");
                mensaje.append(idCaso);
                GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_AUS_SERVICIOS, correo, encabezado, mensaje.toString(), GnCorreoEnvio.TIPO_TEXTO);
                getGnCorreoEnvioRemoto().insertar(envio);
            }
        } catch (Exception ex) {
            Logger.getLogger(AusServicioGestionServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void notificarPorCorreoServicioCerrado(int idUsuario, int idCaso, String motivo) {
        try {
            if (idUsuario > 0) {
                Usuario responsable = getUsuarioRemoto().consultar(idUsuario);
                String encabezado = "Servicio finalizado";
                StringBuilder mensaje = new StringBuilder();
                mensaje.append("SAVIA SALUD EPS a través de su sistema de información CONEXIONES informa la finalizacion de un servicio ASIGNADO al prestador ");
                if (responsable != null) {
                    String email = PropAtencionUsuario.getInstance().get(PropAtencionUsuario.AUS_CASO_EMAIL_SERVICIO_CERRADO);
                    Empresa empresa = getEmpresaRemoto().consultar(responsable.getEmpresa().getId());
                    mensaje.append(empresa.getNombreComercial());
                    mensaje.append("relacionado a ");
                    mensaje.append(motivo);
                    mensaje.append(" con el número de solicitud ");
                    mensaje.append(idCaso);
                    GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_AUS_SERVICIOS, email, encabezado, mensaje.toString(), GnCorreoEnvio.TIPO_TEXTO);
                    getGnCorreoEnvioRemoto().insertar(envio);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AusCasoServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void notificarPorCorreoCaso(int idUsuario, int idPersona, int idCaso, String motivo){
        try {
            Usuario user = getUsuarioRemoto().consultar(idUsuario);
            String correoUser = user.getCorreoElectronico() == null ? "" : user.getCorreoElectronico();
            String encabezado = "Servicio finalizado";
            if(!correoUser.equals("")){
                StringBuilder mensaje = new StringBuilder();
                mensaje.append("SAVIA SALUD EPS a través de su sistema de información CONEXIONES informa la Solucion del servicio relacionado ");
                mensaje.append(motivo);
                mensaje.append(" del servicio relacionado al radicado ");
                mensaje.append(idCaso);
                mensaje.append(" asignado a ");
                mensaje.append(user.getNombre());
                GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_AUS_SERVICIOS, correoUser, encabezado, mensaje.toString(), GnCorreoEnvio.TIPO_TEXTO);
                getGnCorreoEnvioRemoto().insertar(envio);

            }
            AusPersona persona = getPersonaRemoto().consultar(idPersona);
            String correoPersona = persona.getCorreoElectronico() == null ? "" : persona.getCorreoElectronico();
            if(!correoPersona.equals("")){
                StringBuilder mensaje = new StringBuilder();
                mensaje.append("SAVIA SALUD EPS a través de su sistema de información CONEXIONES informa la Solucion del servicio relacionado ");
                mensaje.append(motivo);
                mensaje.append(" bajo el radicado ");
                mensaje.append(idCaso);
                GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_AUS_SERVICIOS, correoPersona, encabezado, mensaje.toString(), GnCorreoEnvio.TIPO_TEXTO);
                getGnCorreoEnvioRemoto().insertar(envio);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(AusServicioGestionServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void contarVencidos(AusServicioGestionBean bean){
        try {//preguntar a Jhoan
            /*if (bean.getConexion().getUsuario().get getPerfil().getId() != 1) {
                bean.getParamConsulta().setParametroConsulta3(bean.getConexion().getUsuario().getId());
            }*/
            if (!bean.isAccionAdicional2()){
                bean.getParamConsulta().setParametroConsulta1(bean.getConexion().getUsuario().getId());
            }
            bean.setCantidadVencidos(getServicioGestionRemoto().consultarCantidadLista(bean.getParamConsulta(), Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO))));
        } catch (Exception e) {
             bean.addError(e.getMessage());
        }
    }
    
    private List<Maestro> obtenerListaEstadoServicioFiltrada(List<Maestro> listaEstadoServicio) {
        List<Maestro> listaEstadoServicioFiltrada = new ArrayList<>();
        Integer idServicioIncluido = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_ASIGNADO));
        int idServicioEstudio = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_ESTUDIO));

        if (listaEstadoServicio != null && !listaEstadoServicio.isEmpty()) {
            listaEstadoServicio.stream().filter(maestro -> (Objects.equals(maestro.getId(), idServicioIncluido) || Objects.equals(maestro.getId(), idServicioEstudio) )).forEachOrdered(maestro -> {
                listaEstadoServicioFiltrada.add(maestro);
            });
        }
        return listaEstadoServicioFiltrada;
    }
}
