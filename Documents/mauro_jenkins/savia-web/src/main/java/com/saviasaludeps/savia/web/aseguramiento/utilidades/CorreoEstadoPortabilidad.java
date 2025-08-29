/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.aseguramiento.utilidades;

import com.saviasaludeps.savia.dominio.administracion.GnCorreoEnvio;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegPortabilidad;
import com.saviasaludeps.savia.dominio.aseguramiento.ReportePortabilidad;
import com.saviasaludeps.savia.negocio.administracion.GnCorreoEnvioRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.PortabilidadRemoto;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author idbohorquez
 */
public class CorreoEstadoPortabilidad extends Thread {
    
    private PortabilidadRemoto getPortabilidadRemoto() throws Exception {
        return (PortabilidadRemoto) RemotoEJB.getEJBRemoto(("PortabilidadServicio"), PortabilidadRemoto.class.getName());
    }
    
    private GnCorreoEnvioRemoto getGnCorreoEnvioRemoto() throws Exception {
        return (GnCorreoEnvioRemoto) RemotoEJB.getEJBRemoto(("GnCorreoEnvioServicio"), GnCorreoEnvioRemoto.class.getName());
    }

    private static final String SALTO = "\n";
    private static final String DOBLE_SALTO = "\n\n";
    private static final String ASUNTO = "Estado de portabilidad";
    private static final int TIPO_CORREO = 1;

    private AsegPortabilidad portabilidad;
    private ReportePortabilidad certificado;
    private Ubicacion ubicacionPortabilidad;
    private boolean aprobacion = true;

    public CorreoEstadoPortabilidad(AsegPortabilidad portabilidad, ReportePortabilidad certificado, Ubicacion ubicacionPortabilidad, boolean aprobacion) {
        this.portabilidad = portabilidad;
        this.certificado = certificado;
        this.ubicacionPortabilidad = ubicacionPortabilidad;
        this.aprobacion = aprobacion;
    }

    @Override
    public void run() {
        try {
            if (aprobacion) {
                enviarCorreoAprobacion(portabilidad, certificado, ubicacionPortabilidad);
            } else {
                enviarcorreoRechazo(portabilidad, certificado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enviarCorreoAprobacion(AsegPortabilidad portabilidad, ReportePortabilidad certificado, Ubicacion ubicacionPortabilidad) throws Exception {
        StringBuilder cuerpo = new StringBuilder("Cordial saludo.").append(DOBLE_SALTO);
        cuerpo.append("La portabilidad fue aplicada para ");
        if(portabilidad.getCntPrestadorSede() != null){
            cuerpo.append("la IPS").append(portabilidad.getCntPrestadorSede().getNombreSede()).append(", en el municipio");
        }else{
            cuerpo.append("el municipio ");
        }
        cuerpo.append(ubicacionPortabilidad.getNombre());
        cuerpo.append(" por un periodo de ").append(getMesesDirefencia(portabilidad.getPeriodoInicial(), portabilidad.getPeriodoFinal())).append(" meses.");
        cuerpo.append(" Señor usuario, si decide radicarse en el lugar donde se aplica la portabilidad, debe");
        cuerpo.append(" tramitar encuesta de Sisbén para realizar traslado de EPS.").append(SALTO);
        cuerpo.append("Adjunto certificado.").append(DOBLE_SALTO);
        cuerpo.append("Savia Salud EPS le informa, que ha dispuesto canales exclusivos para las siguientes solicitudes:");
        cuerpo.append(SALTO);
        cuerpo.append("Portabilidad: portabilidad@saviasaludeps.com").append(SALTO);
        cuerpo.append("Solicitud de datos demográficos: solicitudinfo.aseguramiento@saviasaludeps.com").append(SALTO);
        cuerpo.append("PQRDF: atencionalciudadano@saviasaludeps.com").append(SALTO);
        cuerpo.append("Autorizaciones: notificaciones@saviasaludeps.com, supervisoras@saviaregulador.com").append(DOBLE_SALTO);
        cuerpo.append("Podrá consultar en la página web www.saviasaludeps.com, los trámites de autorización de servicios y afiliaciones, ");
        cuerpo.append("en el link https://www.saviasaludeps.com/sitioweb/index.php/afiliados/careers/solicitudes-y-tramites. ");
        cuerpo.append("En caso de requerir comunicación directa, podrá hacerlo en las líneas telefónicas: 4481747 o 018000423683 Op.1");
        cuerpo.append(DOBLE_SALTO);
        cuerpo.append("Quedamos atentos a sus comentarios e inquietudes.");
        GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_PORTABILIDAD, portabilidad.getCorreoElectronico(), ASUNTO, cuerpo.toString(), GnCorreoEnvio.TIPO_TEXTO);
        envio.setAdjunto1(certificado.getRuta()+certificado.getNombreArchivo());
        getGnCorreoEnvioRemoto().insertar(envio);
        this.portabilidad.setEnvioCorreo(1);
        getPortabilidadRemoto().actualizar(portabilidad);
    }

    public void enviarcorreoRechazo(AsegPortabilidad portabilidad, ReportePortabilidad certificado) throws Exception {
        StringBuilder cuerpo = new StringBuilder("Cordial saludo.").append(DOBLE_SALTO);
        cuerpo.append("Señor usuario su solicitud de portabilidad fue rechazada ya que no cumple con los ");
        cuerpo.append("parámetros establecidos por el Decreto 780 de 2016 para las portabilidades, por ");
        cuerpo.append("favor enviar nuevamente la solicitud, recordando que esta no puede superar un (1) año.").append(SALTO);
        cuerpo.append("Adjunto certificado. ").append(DOBLE_SALTO);
        cuerpo.append("Savia Salud EPS le informa, que ha dispuesto canales exclusivos para las siguientes solicitudes:");
        cuerpo.append(SALTO);
        cuerpo.append("Portabilidad: portabilidad@saviasaludeps.com").append(SALTO);
        cuerpo.append("Solicitud de datos demográficos: solicitudinfo.aseguramiento@saviasaludeps.com").append(SALTO);
        cuerpo.append("PQRDF: atencionalciudadano@saviasaludeps.com").append(SALTO);
        cuerpo.append("Autorizaciones: notificaciones@saviasaludeps.com, supervisoras@saviaregulador.com").append(DOBLE_SALTO);
        cuerpo.append("Podrá consultar en la página web www.saviasaludeps.com, los trámites de autorización de servicios y afiliaciones, ");
        cuerpo.append("en el link https://www.saviasaludeps.com/sitioweb/index.php/afiliados/careers/solicitudes-y-tramites. ");
        cuerpo.append("En caso de requerir comunicación directa, podrá hacerlo en las líneas telefónicas: 4481747 o 018000423683 Op.1");
        cuerpo.append(DOBLE_SALTO);
        cuerpo.append("Quedamos atentos a sus comentarios e inquietudes.");

        GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_PORTABILIDAD, portabilidad.getCorreoElectronico(), ASUNTO, cuerpo.toString(), GnCorreoEnvio.TIPO_TEXTO);
        envio.setAdjunto1(certificado.getRuta()+certificado.getNombreArchivo());
        getGnCorreoEnvioRemoto().insertar(envio);
        portabilidad.setEnvioCorreo(1);
        getPortabilidadRemoto().actualizar(portabilidad);
    }

    private static Integer getMesesDirefencia(Date fechaInicio, Date fechaFin) {
        Integer respuesta = 0;
        try {
            Calendar inicio = new GregorianCalendar();
            Calendar fin = new GregorianCalendar();
            inicio.setTime(fechaInicio);
            fin.setTime(fechaFin);
            int diferenciaAnio = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
            respuesta = (diferenciaAnio * 12) + (fin.get(Calendar.MONTH) - inicio.get(Calendar.MONTH));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return respuesta;
    }

}
