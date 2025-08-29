/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.solicitud.mensajes.correo;

import com.saviasaludeps.savia.dominio.solicitud.GsNotificacion;
import com.saviasaludeps.savia.negocio.solicitud.GsNotificacionRemoto;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author jramirez
 */
public class CorreoSolicitudesWeb implements Runnable{
     private GsNotificacionRemoto getGsNotificacionRemoto() throws Exception {
        return (GsNotificacionRemoto) RemotoEJB.getEJBRemoto("GsNotificacionServicio", GsNotificacionRemoto.class.getName());
    }

    public CorreoSolicitudesWeb(GsNotificacion notificacion) {
        this.notificacion = notificacion;
    }

    private static final String SEPARADOR_CORREOS = ",";

    private static final String SMTP_AUTH_USER = PropApl.getInstance().get(PropApl.SMTP_WEB_AUTH_USER);
    private static final String SMTP_AUTH_PWD = PropApl.getInstance().get(PropApl.SMTP_WEB_AUTH_PWD);
    private static final String SMTP_HOST_NAME = PropApl.getInstance().get(PropApl.SMTP_WEB_HOST_NAME);
    private static final String SMTP_HOST_PORT = PropApl.getInstance().get(PropApl.SMTP_WEB_HOST_PORT);
    private static final String SERVER_TYPE = PropApl.getInstance().get(PropApl.SMTP_WEB_SECURITY);    
    private final GsNotificacion notificacion;

    @Override
    public void run() {
        switch (SERVER_TYPE) {
            case "SMTPS":
                Transport transport = null;
                try {
                    Properties props = new Properties();
                    props.put("mail.transport.protocol", "smtps");
                    props.put("mail.smtps.host", SMTP_HOST_NAME);
                    props.put("mail.smtps.auth", "true");

                    Session mailSession = Session.getDefaultInstance(props);
                    // mailSession.setDebug(true);
                    transport = mailSession.getTransport();
                    MimeMessage message = new MimeMessage(mailSession);
                    message.setSubject(notificacion.getEncabezado());
                    message.setContent(notificacion.getDetalle(), "text/plain");
                    if (notificacion.getCorreo() != null || !notificacion.getCorreo().equals("")) {
                        String[] destino = notificacion.getCorreo().split(SEPARADOR_CORREOS);
                        for (String dest : destino) {
                            message.addRecipient(Message.RecipientType.TO, new InternetAddress(dest));
                        }
                        transport.connect(SMTP_HOST_NAME, Integer.parseInt(SMTP_HOST_PORT), SMTP_AUTH_USER, SMTP_AUTH_PWD);
                        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
                    }
                    try {
                        notificacion.setFechaHoraModifica(new Date());
                        notificacion.setEstado(GsNotificacion.ESTADO_ENVIADO);
                        getGsNotificacionRemoto().actualizar(notificacion);
                    } catch (Exception ex) {

                    }
                } catch (MessagingException e) {
                    try {
                        notificacion.setFechaHoraModifica(new Date());
                        notificacion.setEstado(GsNotificacion.ESTADO_FALLIDO);
                        getGsNotificacionRemoto().actualizar(notificacion);
                    } catch (Exception ex) {

                    }
                } finally {
                    if (transport != null && transport.isConnected()) {
                        try {
                            transport.close();
                        } catch (MessagingException exe) {
                        }
                    }
                }
                break;
            case "SMTP":
                Properties properties = new Properties();
                properties.put("mail.smtp.host", SMTP_HOST_NAME);
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.port", SMTP_HOST_PORT);
                properties.put("mail.smtp.mail.sender", SMTP_AUTH_USER);
                properties.put("mail.smtp.user", SMTP_AUTH_USER);
                properties.put("mail.smtp.auth", "true");
                Session session = Session.getDefaultInstance(properties);
                try {
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
                    if (notificacion.getCorreo() != null || !notificacion.getCorreo().equals("")) {
                        String[] destino = notificacion.getCorreo().split(SEPARADOR_CORREOS);
                        for (String dest : destino) {
                            message.addRecipient(Message.RecipientType.TO, new InternetAddress(dest));
                        }
                    }
                    message.setSubject(notificacion.getEncabezado());
                    message.setText(notificacion.getDetalle());
                    Transport t = session.getTransport("smtp");
                    t.connect((String) properties.get("mail.smtp.user"), SMTP_AUTH_PWD);
                    t.sendMessage(message, message.getAllRecipients());
                    t.close();
                    try {
                        notificacion.setFechaHoraModifica(new Date());
                        notificacion.setEstado(GsNotificacion.ESTADO_ENVIADO);
                        getGsNotificacionRemoto().actualizar(notificacion);
                    } catch (Exception ex) {

                    }
                } catch (MessagingException me) {
                    try {
                        notificacion.setFechaHoraModifica(new Date());
                        notificacion.setEstado(GsNotificacion.ESTADO_FALLIDO);
                        getGsNotificacionRemoto().actualizar(notificacion);
                    } catch (Exception ex) {

                    }
                }
                break;
        }
    }
}
