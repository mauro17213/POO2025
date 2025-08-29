/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.solicitud;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jramirez
 */
public class GsSolicitud extends Auditoria{
    
    public static final int TIPO_SOLICITUD_AFILIACION = 1;
    public static final int TIPO_SOLICITUD_DESAFILIACION = 2;
    public static final int TIPO_SOLICITUD_ACTUALIZACION_DATOS = 3;
    public static final int TIPO_SOLICITUD_PORTABILIDAD = 4;
    public static final int TIPO_SOLICITUD_MOVILIDAD = 5;
    public static final int TIPO_SOLICITUD_INCAPACIDAD = 6;
    public static final int TIPO_SOLICITUD_REGISTRO_EMPLEADOR = 7;
    public static final int TIPO_SOLICITUD_AUTORIZACION = 10;
    public static final int TIPO_SOLICITUD_MIPRES = 11;

    public static final int ESTADO_REGISTRADO = 0;
    public static final int ESTADO_GESTION = 1;
    public static final int ESTADO_RESUELTO = 2;
    public static final int ESTADO_NO_TRAMITADO = 3;

    public static final int TIPO_NOTIFICACION_NINGUNA = 0;
    public static final int TIPO_NOTIFICACION_EMAIL = 1;
    public static final int TIPO_NOTIFICACION_SMS = 2;
    public static final int TIPO_NOTIFICACION_AMBOS = 3;

    public static final int USUARIO_ID_DEFECTO = 2;

    private Integer id;
    private int tipo;
    private String nombre;
    private String descripcion;
    private String observacion;
    private int estado;
    private boolean cambioEstado = false;
    private int notificacion;
    private String contactoTelefono;
    private String contactoCelular;
    private String contactoCorreoElectronico;
    private String respuestaReferencia;
    private Date fechaHoraAtiende;
    private String usuarioAtiende;
    private Date fechaHoraReasigna;
    private String usuarioReasigna;
    private Date fechaHoraFinaliza;
    private String usuarioFinaliza;
    private GsAfiliado gsAfiliado;
    private GsZona gsZona;
    private Usuario usuario;
    private String tramiteInterno;

    private Boolean enviarMensaje;
    private Boolean enviarCorreo;

    private Integer diasVencimiento;
    private String colorVencimiento;
    
    private GsMensaje gsMensaje;

    private List<GsNotificacion> listaGsNotificacion;
    private List<GsAdjunto> listaGsAdjuntos;
    private List<GsGestion> listaGsGestiones;

    public GsSolicitud() {
    }

    public GsSolicitud(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public String getTipoStr() {
        switch (tipo) {
            case TIPO_SOLICITUD_AFILIACION:
                return "Afiliación";            
            case TIPO_SOLICITUD_DESAFILIACION:
                return "Desafiliación";
            case TIPO_SOLICITUD_ACTUALIZACION_DATOS:
                return "Actualización de datos";
            case TIPO_SOLICITUD_PORTABILIDAD:
                return "Portabilidad";
            case TIPO_SOLICITUD_MOVILIDAD:
                return "Movilidad";
            case TIPO_SOLICITUD_INCAPACIDAD:
                return "Incapacidad";
            case TIPO_SOLICITUD_REGISTRO_EMPLEADOR:
                return "Registro Empleador";
            case TIPO_SOLICITUD_AUTORIZACION:
                return "Autorización";
            case TIPO_SOLICITUD_MIPRES:
                return "Mipres";
            default:
                return "";
        }
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getEstado() {
        return estado;
    }

    public String getEstadoStr() {
        switch (estado) {
            case ESTADO_REGISTRADO:
                return "Registrado";
            case ESTADO_GESTION:
                return "En Gestión";
            case ESTADO_RESUELTO:
                return "Resuelto";
            case ESTADO_NO_TRAMITADO:
                return "No Tramitado";
            default:
                return "";
        }
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public boolean isCambioEstado() {
        return cambioEstado;
    }

    public void setCambioEstado(boolean cambioEstado) {
        this.cambioEstado = cambioEstado;
    }

    public int getNotificacion() {
        return notificacion;
    }

    public String getNotificacionStr() {
        switch (notificacion) {
            case TIPO_NOTIFICACION_NINGUNA:
                return "Ninguna";
            case TIPO_NOTIFICACION_EMAIL:
                return "Correo";
            case TIPO_NOTIFICACION_SMS:
                return "SMS";
            case TIPO_NOTIFICACION_AMBOS:
                return "Correo - SMS";
            default:
                return "";
        }
    }

    public void setNotificacion(int notificacion) {
        this.notificacion = notificacion;
    }

    public String getContactoTelefono() {
        return contactoTelefono;
    }

    public void setContactoTelefono(String contactoTelefono) {
        this.contactoTelefono = contactoTelefono;
    }

    public String getContactoCelular() {
        return contactoCelular;
    }

    public void setContactoCelular(String contactoCelular) {
        this.contactoCelular = contactoCelular;
    }

    public String getContactoCorreoElectronico() {
        return contactoCorreoElectronico;
    }

    public void setContactoCorreoElectronico(String contactoCorreoElectronico) {
        this.contactoCorreoElectronico = contactoCorreoElectronico;
    }

    public String getRespuestaReferencia() {
        return respuestaReferencia;
    }

    public void setRespuestaReferencia(String respuestaReferencia) {
        this.respuestaReferencia = respuestaReferencia;
    }

    public Date getFechaHoraAtiende() {
        return fechaHoraAtiende;
    }

    public void setFechaHoraAtiende(Date fechaHoraAtiende) {
        this.fechaHoraAtiende = fechaHoraAtiende;
    }

    public String getUsuarioAtiende() {
        return usuarioAtiende;
    }

    public void setUsuarioAtiende(String usuarioAtiende) {
        this.usuarioAtiende = usuarioAtiende;
    }

    public Date getFechaHoraReasigna() {
        return fechaHoraReasigna;
    }

    public void setFechaHoraReasigna(Date fechaHoraReasigna) {
        this.fechaHoraReasigna = fechaHoraReasigna;
    }

    public String getUsuarioReasigna() {
        return usuarioReasigna;
    }

    public void setUsuarioReasigna(String usuarioReasigna) {
        this.usuarioReasigna = usuarioReasigna;
    }

    public Date getFechaHoraFinaliza() {
        return fechaHoraFinaliza;
    }

    public void setFechaHoraFinaliza(Date fechaHoraFinaliza) {
        this.fechaHoraFinaliza = fechaHoraFinaliza;
    }

    public String getUsuarioFinaliza() {
        return usuarioFinaliza;
    }

    public void setUsuarioFinaliza(String usuarioFinaliza) {
        this.usuarioFinaliza = usuarioFinaliza;
    }

    public GsAfiliado getGsAfiliado() {
        return gsAfiliado;
    }

    public void setGsAfiliado(GsAfiliado gsAfiliado) {
        this.gsAfiliado = gsAfiliado;
    }

    public GsZona getGsZona() {
        return gsZona;
    }

    public void setGsZona(GsZona gsZona) {
        this.gsZona = gsZona;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<GsNotificacion> getListaGsNotificacion() {
        return listaGsNotificacion;
    }

    public void setListaGsNotificacion(List<GsNotificacion> listaGsNotificacion) {
        this.listaGsNotificacion = listaGsNotificacion;
    }

    public Integer getDiasVencimiento() throws ParseException {
        if (getFechaHoraCrea() != null) {
            diasVencimiento = diasEntreFechas(getFechaHoraCrea().toString(), new Date());
        }
        return diasVencimiento;
    }

    private static int diasEntreFechas(String fechaInicial, Date fechaFinal) throws ParseException {
        Date newFechaInicial = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicial);
        int dias = (int) ((fechaFinal.getTime() - newFechaInicial.getTime()) / 86400000);
        return dias;
    }

    public void setDiasVencimiento(Integer diasVencimiento) {
        this.diasVencimiento = diasVencimiento;
    }

    public String getColorVencimiento() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strFechaActal = simpleDateFormat.format(new Date());
            Date newDateVencimiento = new SimpleDateFormat("yyyy-MM-dd").parse(getFechaHoraCrea().toString());
            Date newDateActual = new SimpleDateFormat("yyyy-MM-dd").parse(strFechaActal);
            int posicion = newDateVencimiento.compareTo(newDateActual);
            if (posicion < 0) {
                colorVencimiento = "red";
            } else if (posicion == 0) {
                colorVencimiento = "yellow";
            } else {
                colorVencimiento = "green";
            }
        } catch (ParseException e) {

        }
//        return colorVencimiento;
        return "";
    }

    public void setColorVencimiento(String colorVencimiento) {
        this.colorVencimiento = colorVencimiento;
    }

    public GsMensaje getGsMensaje() {
        return gsMensaje;
    }

    public void setGsMensaje(GsMensaje gsMensaje) {
        this.gsMensaje = gsMensaje;
    }

    public List<GsAdjunto> getListaGsAdjuntos() {
        if (listaGsAdjuntos == null) {
            listaGsAdjuntos = new ArrayList<>();
        }
        return listaGsAdjuntos;
    }

    public void setListaGsAdjuntos(List<GsAdjunto> listaGsAdjuntos) {
        this.listaGsAdjuntos = listaGsAdjuntos;
    }

    public List<GsGestion> getListaGsGestiones() {
        return listaGsGestiones;
    }

    public void setListaGsGestiones(List<GsGestion> listaGsGestiones) {
        this.listaGsGestiones = listaGsGestiones;
    }

    public String getTramiteInterno() {
        return tramiteInterno;
    }

    public void setTramiteInterno(String tramiteInterno) {
        this.tramiteInterno = tramiteInterno;
    }

    public Boolean getEnviarMensaje() {
        return enviarMensaje;
    }

    public void setEnviarMensaje(Boolean enviarMensaje) {
        this.enviarMensaje = enviarMensaje;
    }

    public Boolean getEnviarCorreo() {
        return enviarCorreo;
    }

    public void setEnviarCorreo(Boolean enviarCorreo) {
        this.enviarCorreo = enviarCorreo;
    }

    @Override
    public String toString() {
        return "GsSolicitud{" + "id=" + id + ", tipo=" + tipo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", observacion=" + observacion + ", estado=" + estado + ", cambioEstado=" + cambioEstado + ", notificacion=" + notificacion + ", contactoTelefono=" + contactoTelefono + ", contactoCelular=" + contactoCelular + ", contactoCorreoElectronico=" + contactoCorreoElectronico + ", respuestaReferencia=" + respuestaReferencia + ", fechaHoraAtiende=" + fechaHoraAtiende + ", usuarioAtiende=" + usuarioAtiende + ", fechaHoraReasigna=" + fechaHoraReasigna + ", usuarioReasigna=" + usuarioReasigna + ", fechaHoraFinaliza=" + fechaHoraFinaliza + ", usuarioFinaliza=" + usuarioFinaliza + ", gsAfiliado=" + gsAfiliado + ", gsZona=" + gsZona + ", usuario=" + usuario + ", tramiteInterno=" + tramiteInterno + ", enviarMensaje=" + enviarMensaje + ", enviarCorreo=" + enviarCorreo + ", diasVencimiento=" + diasVencimiento + ", colorVencimiento=" + colorVencimiento + ", gsMensaje=" + gsMensaje + ", listaGsNotificacion=" + listaGsNotificacion + ", listaGsAdjuntos=" + listaGsAdjuntos + ", listaGsGestiones=" + listaGsGestiones + '}';
    }
}
