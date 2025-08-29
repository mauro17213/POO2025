/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.mensajes;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author rpalacic
 */
//@Stateless
public class Correo extends Thread {
    
    private final String SMTP_HOST_NAME = "smtp.gmail.com";
    private final int SMTP_HOST_PORT = 465;
    private final String SMTP_AUTH_USER = "info@systemtech.com.co";
    private final String SMTP_AUTH_PWD = "raulpaco.82";

    private static final String SEPARADOR_CORREO = ",";

    private String destinos = "";
    private String encabezado = "";
    private String contenido = "";

    public Correo(String destinos, String encabezado, String contenido) {
        this.destinos = destinos;
        this.encabezado = encabezado;
        this.contenido = contenido;
    }

    @Override
    public void run() {
        Transport transport = null;
        try {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtps");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", SMTP_HOST_NAME);
            props.put("mail.smtp.port", SMTP_HOST_PORT);

            Session mailSession = Session.getDefaultInstance(props);
            // mailSession.setDebug(true);
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject(encabezado);
            message.setText(contenido);
            if (destinos != null || !destinos.equals("")) {
                String[] destino = destinos.split(SEPARADOR_CORREO);
                for (String dest : destino) {
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(dest));
                }
                transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);
                transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            }
        } catch (MessagingException e) {
        } catch (Exception e) {
        } finally {
            if (transport != null && transport.isConnected()) {
                try {
                    transport.close();
                } catch (MessagingException exe) {
                }
            }
        }
    }
    
//    @Resource(name = "java:jboss/mail/gmail")
//    private Session session;
//
//    public Correo() {
//    }
//    
//    public void envio(String destino, String encabezado, String contenido) throws Exception{
//        try{
//            Message message = new MimeMessage(session);
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));
//            message.setSubject(encabezado);
//            message.setText(contenido);
//            Transport.send(message);
//        }catch(MessagingException e){
//            throw new Exception("No se pudo eviar el correo a " + destino + " - " + e.getMessage());
//        }
//    }
    
}
