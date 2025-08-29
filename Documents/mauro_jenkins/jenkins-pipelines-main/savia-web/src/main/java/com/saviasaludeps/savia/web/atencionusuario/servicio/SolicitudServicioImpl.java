/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.atencionusuario.servicio;

import com.saviasaludeps.savia.dominio.administracion.GnCorreoEnvio;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.atencionusuario.AusAdjunto;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCaso;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersona;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersonaTelefono;
import com.saviasaludeps.savia.dominio.atencionusuario.AusSeguimiento;
import com.saviasaludeps.savia.dominio.atencionusuario.AusSolicitud;
import com.saviasaludeps.savia.negocio.administracion.GnCorreoEnvioRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusCasoRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusPersonaRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusSeguimientoRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusSolicitudRemoto;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.atencionusuario.bean.SolicitudBean;
import com.saviasaludeps.savia.web.singleton.MaestroSingle;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jyperez
 */
public class SolicitudServicioImpl extends AccionesBO implements SolicitudServicioIface {
    
    private AusSolicitudRemoto getSolicitudRemoto() throws Exception {
        return (AusSolicitudRemoto) RemotoEJB.getEJBRemoto(("AusSolicitudServicio"), AusSolicitudRemoto.class.getName());
    }
    
    private AusPersonaRemoto getPersonaRemoto() throws Exception {
        return (AusPersonaRemoto) RemotoEJB.getEJBRemoto("AusPersonaServicio", AusPersonaRemoto.class.getName());
    }
    
    private AusCasoRemoto getCasoRemoto() throws Exception {
        return (AusCasoRemoto) RemotoEJB.getEJBRemoto("AusCasoServicio", AusCasoRemoto.class.getName());
    }
    
    private AusAdjuntoRemoto getAdjuntoRemoto() throws Exception {
        return (AusAdjuntoRemoto) RemotoEJB.getEJBRemoto("AusAdjuntoServicio", AusAdjuntoRemoto.class.getName());
    }
    
    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    private GnCorreoEnvioRemoto getGnCorreoEnvioRemoto() throws Exception {
        return (GnCorreoEnvioRemoto) RemotoEJB.getEJBRemoto("GnCorreoEnvioServicio", GnCorreoEnvioRemoto.class.getName());
    }
    
    private AusSeguimientoRemoto getSeguimientoRemoto() throws Exception {
        return (AusSeguimientoRemoto) RemotoEJB.getEJBRemoto("AusSeguimientoServicio", AusSeguimientoRemoto.class.getName());
    }
    
    @Override
    public void Accion(SolicitudBean bean) {
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
                    enviarCorreo(bean);
                    break;
                case Url.ACCION_ADICIONAL_2:
                    crearCaso(bean);
                    break;
                
            }
            cargas(bean);
        }
    }

    private void listar(SolicitudBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getSolicitudRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getSolicitudRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(SolicitudBean bean) {
        try {
            bean.setObjeto(getSolicitudRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(SolicitudBean bean) {
        try {
            bean.setObjeto(new AusSolicitud());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(SolicitudBean bean) {
        Maestro maestro;
        try {
            // almacenamos los valores correspondientes al código y valor de los maestros
            if (bean.getObjeto().getMaeEstadoSolicitudId()!= 0) {
                maestro = bean.getHashEstadoSolicitud().get(bean.getObjeto().getMaeEstadoSolicitudId());
                bean.getObjeto().setMaeEstadoSolicitudCodigo(maestro.getValor());
                bean.getObjeto().setMaeEstadoSolicitudValor(maestro.getNombre());
            }
            if (bean.getObjeto().getMaeTipoSolicitudId()!= 0) {
                maestro = bean.getHashTipoSolicitud().get(bean.getObjeto().getMaeTipoSolicitudId());
                bean.getObjeto().setMaeTipoSolicitudCodigo(maestro.getValor());
                bean.getObjeto().setMaeTipoSolicitudValor(maestro.getNombre());
            }
            if (bean.getObjeto().getMaeTipoDocumentoId()!= 0) {
                maestro = bean.getHashTipoDocumento().get(bean.getObjeto().getMaeTipoDocumentoId());
                bean.getObjeto().setMaeTipoDocumentoCodigo(maestro.getValor());
                bean.getObjeto().setMaeTipoDocumentoValor(maestro.getNombre());
            }
            // validaciones
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                bean.auditoriaGuardar(bean.getObjeto());
                //guardamos el registro
                getSolicitudRemoto().insertar(bean.getObjeto());
                bean.addMensaje("Se creó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(SolicitudBean bean) {
        try {
            bean.setObjeto(getSolicitudRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(SolicitudBean bean) {
        Maestro maestro;
        try {
            // almacenamos los valores correspondientes al código y valor de los maestros
            if (bean.getObjeto().getMaeEstadoSolicitudId()!= 0) {
                maestro = bean.getHashEstadoSolicitud().get(bean.getObjeto().getMaeEstadoSolicitudId());
                bean.getObjeto().setMaeEstadoSolicitudCodigo(maestro.getValor());
                bean.getObjeto().setMaeEstadoSolicitudValor(maestro.getNombre());
            }
            if (bean.getObjeto().getMaeTipoSolicitudId()!= 0) {
                maestro = bean.getHashTipoSolicitud().get(bean.getObjeto().getMaeTipoSolicitudId());
                bean.getObjeto().setMaeTipoSolicitudCodigo(maestro.getValor());
                bean.getObjeto().setMaeTipoSolicitudValor(maestro.getNombre());
            }
            if (bean.getObjeto().getMaeTipoDocumentoId()!= 0) {
                maestro = bean.getHashTipoDocumento().get(bean.getObjeto().getMaeTipoDocumentoId());
                bean.getObjeto().setMaeTipoDocumentoCodigo(maestro.getValor());
                bean.getObjeto().setMaeTipoDocumentoValor(maestro.getNombre());
            }
            // validaciones
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                bean.auditoriaModificar(bean.getObjeto());
                //guardamos el registro
                getSolicitudRemoto().actualizar(bean.getObjeto());
                bean.addMensaje("Se actualizó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(SolicitudBean bean) {
        try {
            bean.setObjeto(getSolicitudRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void enviarCorreo(SolicitudBean bean) {
        try {
            //enviar Correo
            notificarPorCorreoServicio(bean);
            //actualizar datos asuSolicitudes
            bean.getObjeto().setAplicaCaso(false);
            bean.getObjeto().setDetalleEmail(bean.getAsuntoCorreo()+" : "+bean.getMensajeCorreo());
            Maestro estadoSol = bean.getHashEstadoSolicitudValor().get(AusSolicitud.ESTADO_SOLICITUD_CERRADO);
            if (estadoSol != null) {
                bean.getObjeto().setMaeEstadoSolicitudId(estadoSol.getId());
                bean.getObjeto().setMaeEstadoSolicitudCodigo(estadoSol.getValor());
                bean.getObjeto().setMaeEstadoSolicitudValor(estadoSol.getNombre());
            } else {
                throw new Exception("Error cargando el Maestro Estado Solicitud.");
            }
            bean.auditoriaModificar(bean.getObjeto());
            getSolicitudRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se envió el correo de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void notificarPorCorreoServicio(SolicitudBean bean) {
        try {
            int i = 1;
            StringBuilder mensaje = new StringBuilder();
            mensaje.append(bean.getMensajeCorreo());
            GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_SOLICITUDES_CASOS, bean.getObjeto().getEmail(), bean.getAsuntoCorreo(), mensaje.toString(), GnCorreoEnvio.TIPO_TEXTO);
            //se escribe la url donde se guardá el envio y se le adiciona el nombre del archivo
            for(AusAdjunto arch: bean.getListaAdjuntosCorreo()) {
                switch(i) {
                    case 1:
                        envio.setAdjunto1(arch.getRuta()+ "/" +arch.getNombre());
                        getAdjuntoRemoto().insertar(arch);
                    break;
                    case 2:
                        envio.setAdjunto2(arch.getRuta()+ "/" +arch.getNombre());
                        getAdjuntoRemoto().insertar(arch);
                    break;
                    case 3:
                        envio.setAdjunto3(arch.getRuta()+ "/" +arch.getNombre());
                        getAdjuntoRemoto().insertar(arch);
                    break;
                }
                //llamamos a la carga de archivo en el disco.
                i++;
            }
            envio.setFechaHoraCrea(new Date());
            envio.setFechaHoraEnvio(envio.getFechaHoraCrea());
            envio.setEstado(GnCorreoEnvio.ESTADO_PENDIENTE);
            int idEnvioCorreo = getGnCorreoEnvioRemoto().insertar(envio);
            
        } catch (Exception ex) {
            Logger.getLogger(SolicitudServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void crearCaso(SolicitudBean bean) {
        try {
            //crear AusCaso
            AusCaso caso = new AusCaso();
            AusPersona persona = new AusPersona();
            List<AsegAfiliado> listaAfiliado = new ArrayList();
            AusPersonaTelefono telefono = new AusPersonaTelefono();
            List<AusPersonaTelefono> listaTelefono = new ArrayList();
            //2024-10-04 jyperez campos seguimiento
            AusSeguimiento seguimiento = new AusSeguimiento();
            //1. buscamos en aseg_afiliados si existe un usuario con el tipo documento y número documento
            // de la persona a registrar. Si es así, copiamos sus datos en el objeto persona. De no serlo llenamos los datos
            //desde los enviados en el registro de seguimiento
            listaAfiliado = getAfiliadoRemoto().consultarPorTipoDocumentoYNumeroDocumento(bean.getObjeto().getMaeTipoDocumentoCodigo(), bean.getObjeto().getNumeroDocumento());
            if (!listaAfiliado.isEmpty()) {
                if (listaAfiliado.size() == 1) {
                    persona = castAfiliadoPersona(bean, listaAfiliado.get(0));
                } else {
                    Collections.sort(listaAfiliado, (o1, o2) -> o1.getFechaHoraCrea().compareTo(o2.getFechaHoraCrea()));
                    persona = castAfiliadoPersona(bean, listaAfiliado.get(listaAfiliado.size() - 1));
                }
            } else {
                //si no se encuentra afiliado, se actualiza el objeto con los datos en la silicitud
                persona.setMae_tipo_documento_id(bean.getObjeto().getMaeTipoDocumentoId());
                persona.setMae_tipo_documento_codigo(bean.getObjeto().getMaeTipoDocumentoCodigo());
                persona.setMae_tipo_documento_valor(bean.getObjeto().getMaeTipoDocumentoValor());
                persona.setDocumento(bean.getObjeto().getNumeroDocumento());
                persona.setNombres(bean.getObjeto().getNombres());
                persona.setApellidos(bean.getObjeto().getApellidos());
                persona.setCorreoElectronico(bean.getObjeto().getEmail());
                //obtenemos con el prefijo, el objeto de ubicaciones correspondiente a medellin
                Ubicacion medellin = new Ubicacion();
                for (Ubicacion ubi: bean.getListaUbicacion()) {
                    if (ubi.getPrefijo().equals(SolicitudBean.PREFIJO_MEDELLIN)) {
                        medellin = ubi;
                    }
                }
                if (medellin.getId()!= null) {
                    persona.setGn_ubicaciones_id(medellin.getId());
                    persona.setPersonaUbicacion(new Ubicacion(medellin.getId()));
                } else {
                    persona.setGn_ubicaciones_id(0);
                }
                //adicionamos a la lista de contactos el telefono enviado
                telefono.setNumero(bean.getObjeto().getTelefono());
                Maestro sexo = bean.getHashSexoValor().get(SolicitudBean.SEXO_SIN_IDENTIFICAR);
                if (sexo != null) {
                    persona.setMae_sexo_id(sexo.getId());
                    persona.setMae_sexo_codigo(sexo.getValor());
                    persona.setMae_sexo_valor(sexo.getNombre());
                } else {
                    throw new Exception("Error cargando el Maestro Sexo.");
                }
                bean.auditoriaGuardar(telefono);
                telefono.setAusPersona(persona);
                listaTelefono.add(telefono);
                persona.setListaTelefonos(listaTelefono);
            }
            bean.auditoriaGuardar(persona);
            //guardamos persona, internamente almacena los telefonos
            persona.setId(getPersonaRemoto().insertar(persona));
            //2. adicionamos la información pertinente a la creación del caso
            caso.setAusPersonasId(persona);
            // se agregará savia como empresa teniendo en cuent que el modulo lo administrará su personal
            caso.setEmpresa(bean.getConexion().getEmpresa());
            caso.setEmpresasId(bean.getConexion().getEmpresa());
            // estado solicitud: 1 en tramite
            Maestro estado = bean.getHashTipoEstadoSolicitudValor().get(SolicitudBean.ESTADO_SOLICITUD_EN_TRAMITE);
            if (estado != null) {
                caso.setMaeSolicitudEstadoId(estado.getId());
                caso.setMaeSolicitudEstadoCodigo(estado.getValor());
                caso.setMaeSolicitudEstadoValor(estado.getNombre());
            } else {
                throw new Exception("Error cargando el Maestro Estado Solicitud Caso.");
            }
            //se adiciona la ubicación por defecto que esta en persona - MEDELLIN
            caso.setUbicacion(new Ubicacion(persona.getGn_ubicaciones_id()));
            caso.setMaeSolicitudTipoId(bean.getObjeto().getMaeTipoSolicitudId());
            caso.setMaeSolicitudTipoCodigo(bean.getObjeto().getMaeTipoSolicitudCodigo());
            caso.setMaeSolicitudTipoValor(bean.getObjeto().getMaeTipoSolicitudValor());
            //origen: 8 - página web
            Maestro origen = bean.getHashTipoSolicitudOrigenValor().get(SolicitudBean.SOLICITUD_ORIGEN_PAGINA_WEB);
            if (origen != null) {
            caso.setMaeSolicitudOrigenId(origen.getId());
            caso.setMaeSolicitudOrigenCodigo(origen.getValor());
            caso.setMaeSolicitudOrigenValor(origen.getNombre());
            } else {
                throw new Exception("Error cargando el Maestro Origen Solicitud Caso.");
            }
            // tecnologia alto costo: 133 No aplica
            Maestro tecnologiaAC = bean.getHashTecnologiaAltoCostoValor().get(SolicitudBean.TECNOLOGIA_ALTO_COSTO_NO_APLICA);
            if (tecnologiaAC != null) {
                caso.setMaeTecnologiaAltoCostoId(tecnologiaAC.getId());
                caso.setMaeTecnologiaAltoCostoCodigo(tecnologiaAC.getValor());
                caso.setMaeTecnologiaAltoCostoValor(tecnologiaAC.getNombre());
            } else {
                throw new Exception("Error cargando el Maestro Tecnologia Alto Costo.");
            }
            // motivo especifico: 050101 Sin especificar
            Maestro motivo = bean.getHashMotivoValor().get(SolicitudBean.MOTIVO_ESPECIFICO_SIN_ESPECIFICAR);
            if (motivo != null) {
                caso.setMaeMotivoEspecificoId(motivo.getId());
                caso.setMaeMotivoEspecificoCodigo(motivo.getValor());
                caso.setMaeMotivoEspecificoValor(motivo.getNombre());
            } else {
                throw new Exception("Error cargando el Maestro Motivo.");
            }
            caso.setUsuarioPluripatologico(false);
            caso.setBorrado(false);
            //2025-03-18 jyperez se adiciona la fechaHoraCrea del seguimiento al campo fechaCreacionCaso
            caso.setFechaCreacionCaso(bean.getObjeto().getFechaHoraCrea());
            bean.auditoriaGuardar(caso);
            //3. creamos el caso
            caso.setId(getCasoRemoto().insertar(caso));
            bean.getObjeto().setAusCaso(caso);
            //2024-10-04 jyperez creación de seguimiento con la descripción de la solicitud
            //3.1 creamos el seguimiento del caso con estado en gestión
            seguimiento.setCasosId(caso);
            seguimiento.setObservacion(bean.getObjeto().getDescripcion());
            Maestro estadoSeg = bean.getHashTipoSeguimientoValor().get(SolicitudBean.ESTADO_SEGUIMIENTO_EN_GESTION);
            if (estadoSeg != null) {
                seguimiento.setMaeEstadoId(estadoSeg.getId());
                seguimiento.setMaeEstadoCodigo(estadoSeg.getValor());
                seguimiento.setMaeEstadoValor(estadoSeg.getNombre());
            } else {
                throw new Exception("Error cargando el Maestro Motivo.");
            }
            bean.auditoriaGuardar(seguimiento);
            getSeguimientoRemoto().insertar(seguimiento);
            //4. actualizamos el id del caso en los adjuntos asociados a la solicitud
            if (bean.getObjeto().getAusAdjuntosList() != null) {
                for (AusAdjunto adj: bean.getObjeto().getAusAdjuntosList()) {
                    adj.setCasosId(caso);
                    bean.auditoriaModificar(adj);
                    getAdjuntoRemoto().actualizar(adj);
                }
            }
            //5. adicionamos el campo de aplica caso en true y el id del caso a la solicitud y actualizamos
            bean.getObjeto().setAplicaCaso(true);
            bean.getObjeto().setAusCaso(caso);
            Maestro estadoSol = bean.getHashEstadoSolicitudValor().get(AusSolicitud.ESTADO_SOLICITUD_CREADO);
            if (estadoSol != null) {
                bean.getObjeto().setMaeEstadoSolicitudId(estadoSol.getId());
                bean.getObjeto().setMaeEstadoSolicitudCodigo(estadoSol.getValor());
                bean.getObjeto().setMaeEstadoSolicitudValor(estadoSol.getNombre());
            } else {
                throw new Exception("Error cargando el Maestro Estado Solicitud.");
            }
            bean.auditoriaModificar(bean.getObjeto());
            getSolicitudRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se creó el caso con id " + caso.getId() + " exitosamente. Puede gestionarlo en el módulo correspondiente.");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    public AusPersona castAfiliadoPersona(SolicitudBean bean, AsegAfiliado afiliadoDto) {
        AusPersona persona = new AusPersona();
        persona.setNombres(afiliadoDto.getNombres());
        persona.setApellidos(afiliadoDto.getApellidos());
        persona.setAseg_afiliados_id(afiliadoDto.getId());
        persona.setDocumento(afiliadoDto.getNumeroDocumento());
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
        for (Map.Entry<Integer, Maestro> entry : bean.getHashTipoDocumento().entrySet()) {
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
            bean.auditoriaGuardar(personatTelefono);
            //personatTelefono.setAusPersona(persona);
            listaTelefonos.add(personatTelefono);
            persona.setListaTelefonos(listaTelefonos);
        }
        persona.setEsAfiliado(true);
        return persona;
    }

    private void cargas(SolicitudBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_EDITAR:
                    //Estado
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void cargaInicial(SolicitudBean bean) {
        try {
            bean.setListaEstadoSolicitud(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.AUS_SOLICITUDES_CASOS_ESTADOS));
            bean.setHashEstadoSolicitud(Util.convertToHash(bean.getListaEstadoSolicitud()));
            bean.setHashEstadoSolicitudValor(Util.convertToHashValor(bean.getListaEstadoSolicitud()));
            bean.setListaTipoSolicitud(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.AUS_SOLICITUD_TIPO));
            bean.setHashTipoSolicitud(Util.convertToHash(bean.getListaTipoSolicitud()));
            bean.setListaTipoDocumento(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            bean.setHashTipoDocumento(Util.convertToHash(bean.getListaTipoDocumento()));
            bean.setHashSexo(MaestroSingle.getInstance().getHashPorTipo(MaestroTipo.GN_SEXO));
            bean.setHashSexoValor(MaestroSingle.getInstance().getHashValorPorTipo(MaestroTipo.GN_SEXO));
            bean.setHashUbicacion(UbicacionSingle.getInstance().getHashMunicipiosAntioquia());
            bean.setListaUbicacion(UbicacionSingle.getInstance().getListaMunicipiosAntioquia());
            //nuevos maestros casos
            bean.setHashTipoEstadoSolicitudValor(MaestroSingle.getInstance().getHashValorPorTipo(MaestroTipo.AUS_SOLICITUD_ESTADO));
            bean.setHashTipoSolicitudOrigenValor(MaestroSingle.getInstance().getHashValorPorTipo(MaestroTipo.AUS_SOLICITUD_ORIGEN));
            bean.setHashTecnologiaAltoCostoValor(MaestroSingle.getInstance().getHashValorPorTipo(MaestroTipo.AUS_TECNOLOGIA_ALTO_COSTO));
            bean.setHashMotivoValor(MaestroSingle.getInstance().getHashValorPorTipo(MaestroTipo.AUS_CASO_MOTIVO_ESPECIFICO));
            bean.setHashTipoSeguimientoValor(MaestroSingle.getInstance().getHashValorPorTipo(MaestroTipo.AUS_SEGUIMIENTO_TIPO));
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

}
