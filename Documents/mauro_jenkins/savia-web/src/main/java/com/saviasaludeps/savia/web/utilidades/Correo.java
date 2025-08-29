package com.saviasaludeps.savia.web.utilidades;

import java.io.File;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author rpalacic
 */
public class Correo implements Runnable {

    public Correo(String destinos, String asunto, String mensaje) {
        this.destinos = destinos;
        this.asunto = asunto;
        this.mensaje = mensaje;
        cargarDatosConexion(TIPO_GENERICO);
    }
    
    public Correo(String destinos, String asunto, String mensaje, String rutaAdjunto, String nombreAdjunto, int tipo) {
        this.destinos = destinos;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.adjunto = true;
        this.rutaAdjunto = rutaAdjunto;
        this.nombreAdjunto = nombreAdjunto;
        cargarDatosConexion(tipo);
    }

    private static final String SEPARADOR_CORREOS = ",";
    private static final int TIPO_GENERICO = 0;
    public static final int TIPO_PORTABILIDAD = 1;

    String destinos = "";
    String asunto = "";
    String mensaje = "";
    boolean adjunto = false;
    String rutaAdjunto = "";
    String nombreAdjunto = "";
    String usuario = "";
    String contrasena = "";
    String host = "";
    String puerto = "";
    String servidor = "";
    int tipo = 0;
    int resultado = 0;

    @Override
    public void run() {
        switch (servidor) {
            case "SMTPS":
                Transport transport = null;
                try {                    
                    Properties props = new Properties();
                    props.put("mail.transport.protocol", servidor.toLowerCase());
                    props.put("mail.smtp.host", host); //SMTPS Host
                    props.put("mail.smtp.socketFactory.port", puerto); //SSL Port
                    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
                    props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
                    props.put("mail.smtp.port", puerto); //SMTP Port
                    //Se autentica con las credenciales del remitente
                    Authenticator auth = new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(usuario, contrasena);
                        }
                    };
                    Session session = Session.getInstance(props, auth);
                    MimeMessage msg = new MimeMessage(session);
                    msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
                    msg.addHeader("format", "flowed");
                    msg.addHeader("Content-Transfer-Encoding", "8bit");
                    msg.setFrom(new InternetAddress(usuario));
                    msg.setSubject(asunto, "UTF-8");
                    msg.setSentDate(new Date()); 
                    BodyPart messageBodyPart = new MimeBodyPart();
                    messageBodyPart.setText(mensaje);
                    Multipart multipart = new MimeMultipart();
                    multipart.addBodyPart(messageBodyPart);
                    if (adjunto && rutaAdjunto!= null) {
                        messageBodyPart = new MimeBodyPart();
                        File fileAdjunto = new File(rutaAdjunto, nombreAdjunto);
                        DataSource source = new FileDataSource(fileAdjunto);
                        messageBodyPart.setDataHandler(new DataHandler(source));
                        messageBodyPart.setFileName(nombreAdjunto);
                        multipart.addBodyPart(messageBodyPart);
                    }
                    msg.setContent(multipart);
                    if (destinos != null || !destinos.equals("")) {
                        String[] destino = destinos.split(SEPARADOR_CORREOS);
                        for (String dest : destino) {
                            msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(dest, false));
                        }
                    } 
                    // Se envia el correo
                    Transport.send(msg);
                    resultado = 1;
                    System.out.println("Email smpts enviado correctamente!!");
                } catch (MessagingException e) {
                    resultado = 0;
                    System.out.println("ERROR ENVÍO SMTPS: " + e.getMessage());
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
                properties.put("mail.smtp.host", host);
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.port", puerto);
                properties.put("mail.smtp.mail.sender", usuario);
                properties.put("mail.smtp.user", usuario);
                properties.put("mail.smtp.auth", "true");
                Session session = Session.getDefaultInstance(properties);
                try {
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
                    if (destinos != null || !destinos.equals("")) {
                        String[] destino = destinos.split(SEPARADOR_CORREOS);
                        for (String dest : destino) {
                            message.addRecipient(Message.RecipientType.TO, new InternetAddress(dest));
                        }
                    }
                    message.setSubject(asunto);
                    message.setText(mensaje);
                    Transport t = session.getTransport("smtp");
                    t.connect((String) properties.get("mail.smtp.user"), contrasena);
                    t.sendMessage(message, message.getAllRecipients());
                    t.close();
                } catch (MessagingException me) {
                    System.out.println("ERROR ENVÍO: " + me.getMessage());
                }
                break;
        }
    }

    private void cargarDatosConexion(int tipo) {
        if (tipo == TIPO_GENERICO) {
            this.usuario = PropApl.getInstance().get(PropApl.SMTP_BASE_AUTH_USER);
            this.contrasena = PropApl.getInstance().get(PropApl.SMTP_BASE_AUTH_PWD);
            this.host = PropApl.getInstance().get(PropApl.SMTP_BASE_HOST_NAME);
            this.puerto = PropApl.getInstance().get(PropApl.SMTP_BASE_HOST_PORT);
            this.servidor = PropApl.getInstance().get(PropApl.SMTP_BASE_SECURITY);
        } else if (tipo == TIPO_PORTABILIDAD) {
            this.usuario = PropApl.getInstance().get(PropApl.SMTP_PORTABILIDAD_AUTH_USER);
            this.contrasena = PropApl.getInstance().get(PropApl.SMTP_PORTABILIDAD_AUTH_PWD);
            this.host = PropApl.getInstance().get(PropApl.SMTP_PORTABILIDAD_HOST_NAME);
            this.puerto = PropApl.getInstance().get(PropApl.SMTP_PORTABILIDAD_HOST_PORT);
            this.servidor = PropApl.getInstance().get(PropApl.SMTP_PORTABILIDAD_SECURITY);
        }
        
    }
    
    public int getResultadoEnvio(){
        return resultado;
    }
    
}
