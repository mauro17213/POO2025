/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.externo.servicio;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.GnCorreoEnvio;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.atencionusuario.AusAdjunto;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCaso;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCasoServicio;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersona;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersonaTelefono;
import com.saviasaludeps.savia.dominio.atencionusuario.AusSeguimiento;
import com.saviasaludeps.savia.negocio.administracion.GnCorreoEnvioRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusCasoRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusPersonaRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusSeguimientoRemoto;
import com.saviasaludeps.savia.web.atencionusuario.bean.AusPersonaBean;
import com.saviasaludeps.savia.web.atencionusuario.servicio.AusCasoServicioImpl;
import com.saviasaludeps.savia.web.atencionusuario.servicio.AusPersonaServicioImpl;
import com.saviasaludeps.savia.web.atencionusuario.utilities.PropAtencionUsuario;
import com.saviasaludeps.savia.web.externo.bean.AtencionCreacionCasoBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
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
 * @author pavacca
 */
public class AtencionCreacionCasoServicioImpl extends AccionesBO implements AtencionCreacionCasoServicioIface{
    
    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }
    
    private AusCasoRemoto getCasoRemoto() throws Exception {
        return (AusCasoRemoto) RemotoEJB.getEJBRemoto("AusCasoServicio", AusCasoRemoto.class.getName());
    }
    
    private AusPersonaRemoto getPersonaRemoto() throws Exception {
        return (AusPersonaRemoto) RemotoEJB.getEJBRemoto("AusPersonaServicio", AusPersonaRemoto.class.getName());
    }
    
    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }
    
    private AusSeguimientoRemoto getSeguimientoRemoto() throws Exception {
        return (AusSeguimientoRemoto) RemotoEJB.getEJBRemoto("AusSeguimientoServicio", AusSeguimientoRemoto.class.getName());
    }
    
    private AusAdjuntoRemoto getAdjuntoRemoto() throws Exception {
        return (AusAdjuntoRemoto) RemotoEJB.getEJBRemoto("AusAdjuntoServicio", AusAdjuntoRemoto.class.getName());
    }
    
    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }
    
    private GnCorreoEnvioRemoto getGnCorreoEnvioRemoto() throws Exception {
        return (GnCorreoEnvioRemoto) RemotoEJB.getEJBRemoto("GnCorreoEnvioServicio", GnCorreoEnvioRemoto.class.getName());
    }
    
    @Override
    public void Accion(AtencionCreacionCasoBean bean) {
        switch (bean.getAccion()) {
            //case AtencionCreacionCasoBean.ACCION_BUSCAR_SOLICITUDES_CASO_SERVICE:
            case Url.ACCION_LISTAR:
                consultarServicios(bean);
                break;
            case Url.ACCION_CREAR:
                crear(bean);
                break;
            case Url.ACCION_GUARDAR:
                guardar(bean);
                break;
            default:
                break;
        }
    }
    
    private void crear(AtencionCreacionCasoBean bean) {
        try {
            bean.setObjeto(new AusCaso());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardar(AtencionCreacionCasoBean bean) {
        try {
            int idCaso = 0;
            AusCaso ausCaso = bean.getObjeto();
            AusPersona ausPersona = ausCaso.getAusPersonasId();
            //ausPersona.setEmpresa(bean.getConexion().getEmpresa());
            Usuario usuarioAuditoria = bean.getHashUsuarios().get(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.EXTERNO_CASO_RESPONSABLE)));
            if(usuarioAuditoria != null){
                ausCaso.setUsuarioCrea(usuarioAuditoria.getUsuarioNombre());
                ausCaso.setTerminalCrea("127.0.0.1");
                ausCaso.setFechaHoraCrea(new Date());
            }
           
            Maestro tipoDocumento = bean.getHashTiposDocumento().get(ausPersona.getMae_tipo_documento_id());
            if (tipoDocumento != null) {
                ausPersona.setMae_tipo_documento_codigo(tipoDocumento.getValor());
                ausPersona.setMae_tipo_documento_valor(tipoDocumento.getNombre());
            }
            Maestro sexo = bean.getHashSexo().get(ausPersona.getMae_sexo_id());
            if (sexo != null){
                ausPersona.setMae_sexo_codigo(sexo.getValor());
                ausPersona.setMae_sexo_valor(sexo.getNombre());
            }
            
            Maestro estadoPersona = bean.getHashTipoEstadosPersona().get(ausPersona.getMae_estado_id());
            if (estadoPersona != null){
                ausPersona.setMae_estado_codigo(estadoPersona.getValor());
                ausPersona.setMae_estado_valor(estadoPersona.getNombre());
            } 
            
            if(usuarioAuditoria != null){
                ausPersona.setUsuarioCrea(usuarioAuditoria.getUsuarioNombre());
                ausPersona.setTerminalCrea("127.0.0.1");
                ausPersona.setFechaHoraCrea(new Date());
            }
            if (ausPersona.getId() == null || ausPersona.getId() <= 0) {
                //bean.auditoriaGuardar(ausPersona);
                int idPersona = getPersonaRemoto().insertar(ausPersona);
                ausPersona.setId(idPersona);
            } else {
               // bean.auditoriaGuardar(ausPersona);
                ausPersona.setId(null);
                int idPersona = getPersonaRemoto().insertar(ausPersona);
                ausPersona.setId(idPersona);
                //bean.auditoriaModificar(ausPersona);
                //getPersonaRemoto().actualizar(ausPersona);
            }
            
            if (ausPersona.getId() > 0) {
                Maestro estadoCaso = bean.getHashTipoEstadoSolicitud().get(ausCaso.getMaeSolicitudEstadoId()); 
                if(estadoCaso != null){
                    ausCaso.setMaeSolicitudEstadoCodigo(estadoCaso.getValor());
                    ausCaso.setMaeSolicitudEstadoValor(estadoCaso.getNombre());
                }
                Maestro tipoSolicitudCaso = bean.getHashTipoSolicitud().get(ausCaso.getMaeSolicitudTipoId());
                if (tipoSolicitudCaso != null){
                    ausCaso.setMaeSolicitudTipoCodigo(tipoSolicitudCaso.getValor());
                    ausCaso.setMaeSolicitudTipoValor(tipoSolicitudCaso.getNombre());
                } 
                Maestro tipoSolicitudOrigenCaso = bean.getHashTipoSolicitudOrigen().get(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.EXTERNO_CASO_ORIGEN_PAGINA_WEB)));
                if (tipoSolicitudOrigenCaso != null){
                    ausCaso.setMaeSolicitudOrigenId(tipoSolicitudOrigenCaso.getId());
                    ausCaso.setMaeSolicitudOrigenCodigo(tipoSolicitudOrigenCaso.getValor());
                    ausCaso.setMaeSolicitudOrigenValor(tipoSolicitudOrigenCaso.getNombre());
                }
                Maestro tipoSolicitudPrioridadCaso = bean.getHashTipoSolicitudPrioridad().get(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.EXTERNO_CASO_PRIORIDAD)));
                if (tipoSolicitudPrioridadCaso != null){
                    ausCaso.setMaeSolicitudPrioridadId(tipoSolicitudPrioridadCaso.getId());
                    ausCaso.setMaeSolicitudPrioridadCodigo(tipoSolicitudPrioridadCaso.getValor());
                    ausCaso.setMaeSolicitudPrioridadValor(tipoSolicitudPrioridadCaso.getNombre());
                }
                Maestro tipoSolicitudEnteControlCaso = bean.getHashTipoSolicitudEnteControl().get(ausCaso.getMaeSolicitudEnteControlId());
                if (tipoSolicitudEnteControlCaso != null){
                    ausCaso.setMaeSolicitudEnteControlCodigo(tipoSolicitudEnteControlCaso.getValor());
                    ausCaso.setMaeSolicitudEnteControlValor(tipoSolicitudEnteControlCaso.getNombre());
                }
                Maestro tipoSolicitudRiesgoVidalCaso = bean.getHashTipoSolicitudRiesgoVida().get(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.EXTERNO_CASO_RIESGO_VIDA)));
                if (tipoSolicitudRiesgoVidalCaso != null){
                    ausCaso.setMaeSolicitudRiesgoVidalId(tipoSolicitudRiesgoVidalCaso.getId());
                    ausCaso.setMaeSolicitudRiesgoVidalCodigo(tipoSolicitudRiesgoVidalCaso.getValor());
                    ausCaso.setMaeSolicitudRiesgoVidalValor(tipoSolicitudRiesgoVidalCaso.getNombre());
                }
                Maestro canalSuperSaludCaso = bean.getHashCanalSuperSalud().get(ausCaso.getMaeCanalSupersaludId());
                if (canalSuperSaludCaso != null){
                    ausCaso.setMaeCanalSupersaludCodigo(canalSuperSaludCaso.getValor());
                    ausCaso.setMaeCanalSupersaludValor(canalSuperSaludCaso.getNombre());
                }
                
                ausCaso.setEmpresa(new Empresa(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.EXTERNO_CASO_EMPRESA_DEFECTO))));
                Usuario usuarioResponsable = bean.getHashUsuarios().get(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.EXTERNO_CASO_RESPONSABLE)));
                ausCaso.setResponsableUsuariosId(usuarioResponsable);
                ausCaso.setId(null);
                if(ausCaso.getModalidadEntrega() != null){
                    if(ausCaso.getModalidadEntrega() == 1){
                        ausCaso.setDireccionResidencia(ausPersona.getDireccion());
                    }
                }
                ausCaso.setCantidadServicios(ausCaso.getServiciosList() != null ? ausCaso.getServiciosList().size() : 0);
                int idEstadoResuelto = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_RESUELTO));
                int idEstadoCerrado = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO));
                int cantidad = 0;
                if (ausCaso.getServiciosList() != null) {
                    for(AusCasoServicio servicio: ausCaso.getServiciosList()){
                        if(servicio.getMaeEstadoId() == idEstadoResuelto || servicio.getMaeEstadoId() == idEstadoCerrado){
                            cantidad = cantidad + 1;
                        }
                    }
                }                    
                ausCaso.setCantidadServiciosCerrados(cantidad);
                ausCaso.setBorrado(Boolean.FALSE);
                idCaso = getCasoRemoto().insertar(ausCaso);
                ausCaso.setId(idCaso);
                if (ausCaso.getId() > 0) {
                    insertarAdjuntos(ausCaso.getAdjuntosList(), bean, idCaso, 0, 0);
                    guardarSeguimientoPorDefecto(bean, ausCaso);
                    //guardarSeguimientoAdicional(bean, ausCaso);    
                    String motivos = "''";
                    motivos += bean.getHashTipoSolicitud().get(bean.getObjeto().getMaeSolicitudEstadoId()) + ",";
                        
                    motivos = StringUtils.removeEnd(motivos, ",");
                    motivos += "''";
                    notificarPorCorreoCasoCreado(ausCaso.getResponsableUsuariosId(), ausCaso.getAusPersonasId(), idCaso, motivos);
                   
                }
            }
            bean.addMensaje("Se creo el caso con id (" + idCaso + ") de manera exitosa \n ");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    private void consultarServicios(AtencionCreacionCasoBean bean) {
        try {
            if (bean.getParamConsulta().getFiltros() == null) {
                bean.getParamConsulta().setFiltros(new HashMap<>());            }
            bean.getParamConsulta().setParametroConsulta1(bean.getObjeto().getAsuPersonasId().getMae_tipo_documento_id());
            bean.getParamConsulta().setParametroConsulta2(bean.getObjeto().getAsuPersonasId().getDocumento());
            bean.getParamConsulta().setParametroConsulta3(bean.getObjeto().getAsuPersonasId().getFechaNacimiento());
            bean.getParamConsulta().setRegistrosPagina(30);
            bean.getParamConsulta().setCantidadRegistros(getCasoRemoto().consultarCantidadListaExterna(bean.getParamConsulta()));
            //bean.setRegistros(getCasoRemoto().consultarListaExterna(bean.getParamConsulta()));
            //bean.setRegistros(getCasoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
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

                //bean.setAusPersonaTelefono((AusPersonaTelefono) ausPersona.getListaTelefonos());
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    @Override
    public void consultarPersonaAnonima(AusPersonaBean bean) {
        try {
            //bean.getObjeto().setEmpresa(bean.getConexion().getEmpresa());
            AusPersona ausPersona = getPersonaRemoto().consultar(bean.getObjeto().getId());
            //AsegAfiliado asegAfiliado = getAfiliadoRemoto().consultar(bean.getObjeto());
            if (ausPersona.exitePersona()) {
                List<AusPersonaTelefono> telefonosPersona = getPersonaRemoto().consultarTelefonosPersonas(ausPersona.getDocumento());
                ausPersona.setListaTelefonos(telefonosPersona);
                bean.setObjeto(ausPersona);

                //bean.setAusPersonaTelefono((AusPersonaTelefono) ausPersona.getListaTelefonos());
            }
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
    
    @Override
    public void consultarPersonaAfiliada(AusPersonaBean bean) {
        try {
//          bean.getObjeto().setEmpresa(bean.getConexion().getEmpresa());
            List<AsegAfiliado> ausPersona = getAfiliadoRemoto().consultarListaAfiliados(bean.getObjeto().getMae_tipo_documento_codigo(), bean.getObjeto().getDocumento());// consultarListaAfiliados (bean.getObjeto().getDocumento());
            //AsegAfiliado asegAfiliado = getAfiliadoRemoto().consultar(bean.getObjeto());
            if (ausPersona != null && !ausPersona.isEmpty()) {
                if(ausPersona.size() == 1){
                    castEntidadNegocio(bean, ausPersona.get(0));
                }else{
                    Collections.sort(ausPersona, (o1, o2) -> o1.getFechaHoraCrea().compareTo(o2.getFechaHoraCrea()));
                    castEntidadNegocio(bean, ausPersona.get(ausPersona.size() -1));
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
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
        if (afiliadoDto.getMaeGeneroValor()!= null && bean.getHashSexo() != null){
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
        if(bean.getHashEstadosPersona() != null && afiliadoDto.getMaeEstadoAfiliacionValor() != null){
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
        if (afiliadoDto.getResidenciaUbicacion() != null){
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
            List<AusPersonaTelefono> listaTelefonos = new ArrayList<>();
            AusPersonaTelefono personatTelefono = new AusPersonaTelefono();
            personatTelefono.setNumero(telefono);
            listaTelefonos.add(personatTelefono);
            persona.setListaTelefonos(listaTelefonos);
            bean.setListaausPersonaTelefono(persona.getListaTelefonos());    
        }
        persona.setEsAfiliado(true);
    }
    
    private void guardarSeguimientoPorDefecto(AtencionCreacionCasoBean bean, AusCaso caso) {
        try {
            AusSeguimiento seguimiento = bean.getObjeto().getSeguimientoInicial();
            Usuario usuarioAuditoria = bean.getHashUsuarios().get(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.EXTERNO_CASO_RESPONSABLE)));
            if(usuarioAuditoria != null){
                seguimiento.setUsuarioCrea(usuarioAuditoria.getUsuarioNombre());
                seguimiento.setTerminalCrea("127.0.0.1");
                seguimiento.setFechaHoraCrea(new Date());
            }
            //bean.auditoriaGuardar(seguimiento);
            //seguimiento.setMaeEstadoId(bean.getIdTipoSeguimiento(AusCasoBean.DESC_SEGUIMIENTO_RADICADO));
            seguimiento.setMaeEstadoId(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.DESC_SEGUI_RADIC)));
            Maestro estadoSeguimiento = bean.getHashTipoSeguimiento().get(seguimiento.getMaeEstadoId());
            if (estadoSeguimiento != null){
                seguimiento.setMaeEstadoCodigo(estadoSeguimiento.getValor());
                seguimiento.setMaeEstadoValor(estadoSeguimiento.getNombre());
            }
            //Integer idMaestro= seguimiento.getMaeEstadoId();
            //Maestro maestroSeguimiento = bean.getMaestroSeguimiento(idMaestro);
            //seguimiento.setMaeEstadoCodigo(maestroSeguimiento.getValor());
            //seguimiento.setMaeEstadoValor(maestroSeguimiento.getNombre());
            seguimiento.setCasosId(caso);
            seguimiento.setId(null);
            int idSeguimiento = getSeguimientoRemoto().insertar(seguimiento);
            seguimiento.setId(idSeguimiento);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarSeguimientoAdicional(AtencionCreacionCasoBean bean, AusCaso caso) {
        try {
            AusSeguimiento seguimientoAdicional = bean.getObjeto().getSeguimientoAdicional();
            Usuario usuarioAuditoria = bean.getHashUsuarios().get(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.EXTERNO_CASO_RESPONSABLE)));
            if(usuarioAuditoria != null){
                seguimientoAdicional.setUsuarioCrea(usuarioAuditoria.getUsuarioNombre());
                seguimientoAdicional.setTerminalCrea("127.0.0.1");
                seguimientoAdicional.setFechaHoraCrea(new Date());
            }
            //bean.auditoriaGuardar(seguimientoAdicional);
            if (seguimientoAdicional.getMaeEstadoId() > 0
                    && seguimientoAdicional.getObservacion() != null
                    && seguimientoAdicional.getObservacion().length() > 1) {
                seguimientoAdicional.setCasosId(caso);
                //if (bean.obtenerEstado("Cerrado") == seguimientoAdicional.getMaeEstadoId()) {
                if (Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ESTADO_CERRADO)) == seguimientoAdicional.getMaeEstadoId()) {
                    String motivos = "''";
                   
                    motivos += bean.getHashTipoSolicitud().get(bean.getObjeto().getMaeSolicitudEstadoId()) + ",";
                    
                    motivos = StringUtils.removeEnd(motivos, ",");
                    motivos += "''";
                    notificarPorCorreoCasoCerrado(caso.getResponsableUsuariosId(), caso.getAusPersonasId(), caso.getId(), motivos);
                }
                seguimientoAdicional.setMaeEstadoId(seguimientoAdicional.getMaeEstadoId());
                Maestro estadoSeguimiento = bean.getHashTipoSeguimiento().get(seguimientoAdicional.getMaeEstadoId());
                if (estadoSeguimiento != null){
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
    
    private void insertarAdjuntos(List<AusAdjunto> adjuntosIn, AtencionCreacionCasoBean bean,
            int idCaso, int idSer, int idSeg) {

        try {
            for (AusAdjunto adjunto : adjuntosIn) {
                Usuario usuarioAuditoria = bean.getHashUsuarios().get(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.EXTERNO_CASO_RESPONSABLE)));
                if(usuarioAuditoria != null){
                    adjunto.setUsuarioCrea(usuarioAuditoria.getUsuarioNombre());
                    adjunto.setTerminalCrea("127.0.0.1");
                    adjunto.setFechaHoraCrea(new Date());
                }
                //bean.auditoriaGuardar(adjunto);
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
    
    private void notificarPorCorreoCasoCreado(Usuario responsable, AusPersona persona, int idCaso, String motivo) {
        try {
            String encabezado = "Nuevo Caso";
            String mensaje = "Sistema Integral para el Monitoreo de la Satisfacción de Afiliados (SIMSA) informa la Radicacion del servicio relacionado a " + motivo
                        + " bajo el radicado " + idCaso;
            if (persona.getCorreoElectronico() != null && !persona.getCorreoElectronico().equals("")) {
                String email = persona.getCorreoElectronico();
                GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_EXTERNO_CASOS, email, encabezado, mensaje, GnCorreoEnvio.TIPO_TEXTO);
                getGnCorreoEnvioRemoto().insertar(envio);
            }
            if (responsable.getCorreoElectronico() != null && !responsable.getCorreoElectronico().equals("")) {
                String email = responsable.getCorreoElectronico();
                mensaje += " asignado a " + responsable.getNombre();
                GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_EXTERNO_CASOS, email, encabezado, mensaje, GnCorreoEnvio.TIPO_TEXTO);
                getGnCorreoEnvioRemoto().insertar(envio);
            }
        } catch (Exception ex) {
            Logger.getLogger(AusCasoServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void notificarPorCorreoCasoCerrado(Usuario responsable, AusPersona persona, int idCaso, String motivo) {
        try {
            String encabezado = "Cierre Caso";
            String mensaje = "Sistema Integral para el Monitoreo de la Satisfacción de Afiliados (SIMSA) informa el cierre del servicio relacionado a " + motivo
                        + " bajo el radicado " + idCaso;
            if (persona.getCorreoElectronico() != null) {
                String email = persona.getCorreoElectronico();
                GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_EXTERNO_CASOS, email, encabezado, mensaje, GnCorreoEnvio.TIPO_TEXTO);
                getGnCorreoEnvioRemoto().insertar(envio);
            }
            responsable = getUsuarioRemoto().consultar(responsable.getId());
            if (responsable.getCorreoElectronico() != null) {
                String email = responsable.getCorreoElectronico();
                mensaje += " asignado a " + responsable.getNombre();
                GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_EXTERNO_CASOS, email, encabezado, mensaje, GnCorreoEnvio.TIPO_TEXTO);
                getGnCorreoEnvioRemoto().insertar(envio);
            }

        } catch (Exception ex) {
            Logger.getLogger(AusCasoServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public HashMap<Integer, Usuario> convertToHashUsuarios(List<Usuario> list) {
       HashMap<Integer, Usuario> map = new HashMap<>();
       for (Usuario i : list) {
           map.put(i.getId(), i);
       }
       return map;
    }
    
    @Override
    public void cargaInicial(AtencionCreacionCasoBean bean) {
        try {
            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            bean.setHashTiposDocumento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            
            bean.setListaSexo(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_SEXO));
            bean.setHashSexo(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_SEXO));
            
            bean.setListaTipoEstadoPersona(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            bean.setHashTipoEstadosPersona(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            
            bean.setUbicacionesRecursiva(bean.getUbicacionSingle().getHashMunicipios());
            bean.setUbicaciones(bean.getUbicacionSingle().getListaMunicipios());
            
            bean.setListaTipoSolicitudOrigen(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_ORIGEN));
            bean.setHashTipoSolicitudOrigen(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SOLICITUD_ORIGEN));
            
            bean.setListaTipoSolicitudRiesgoVida(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_RIESGO_VIDA));
            bean.setHashTipoSolicitudRiesgoVida(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SOLICITUD_RIESGO_VIDA));
            
            bean.setListaTipoSolicitud(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_TIPO));
            bean.setHashTipoSolicitud(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SOLICITUD_TIPO));
            
            bean.setListaTipoEstadoSolicitud(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_ESTADO));
            bean.setHashTipoEstadoSolicitud(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SOLICITUD_ESTADO));
            
            bean.setListaTipoSolicitudPrioridad(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_PRIORIDAD));
            bean.setHashTipoSolicitudPrioridad(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SOLICITUD_PRIORIDAD));
            
            bean.setListaCanalSuperSalud(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_CANAL_SUPER_SALUD));
            bean.setHashCanalSuperSalud(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_CANAL_SUPER_SALUD));
            
            bean.setListaTipoSolicitudEnteControl(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_ENTE_CONTROL));
            bean.setHashTipoSolicitudEnteControl(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SOLICITUD_ENTE_CONTROL));
            
            bean.setListaTipoSeguimiento(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SEGUIMIENTO_TIPO));
            bean.setHashTipoSeguimiento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AUS_SEGUIMIENTO_TIPO));
            
            bean.setListaUsuarios(getCasoRemoto().consultarUsuario(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.EXTERNO_CASO_RESPONSABLE))));
            bean.setHashUsuarios(convertToHashUsuarios(bean.getListaUsuarios()));
            
        } catch (Exception ex) {
            bean.addError("Error carga inicial : " + ex.getMessage());
        }
    }
}
