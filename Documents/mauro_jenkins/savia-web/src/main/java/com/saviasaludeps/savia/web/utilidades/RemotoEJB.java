/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.utilidades;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author rpalacic (SystemTech Integral)
 */
public class RemotoEJB {

    public static Object getEJBRemoto(String nameEjb, String iface) throws Exception {
        Object object = null;
        try {
            Properties props = new Properties();
            props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
//            props.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:7070");
//            props.put(Context.SECURITY_PRINCIPAL, "savia");
//            props.put(Context.SECURITY_CREDENTIALS, "savia");
//            props.put("jboss.naming.client.ejb.context", true);
            Context context = new InitialContext(props);
            String preContext = "ejb:savia-ear/savia-ejb-1.0-SNAPSHOT//";
            String nombre = preContext + nameEjb + "!" + iface;
            object = context.lookup(nombre);
        } catch (NamingException ex) {
            throw new Exception("It was not possible to connect to the remote EJB " + nameEjb);
        } catch (Exception ex) {
            throw new Exception("It was not possible to connect to the remote EJB " + nameEjb);
        }
        return object;
    }
    
    public static Object getEJBRemotoMipres(String nameEjb, String iface) throws Exception {
        Object object = null;
        try {
            Properties props = new Properties();
            props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
//            props.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:7070");
//            props.put(Context.SECURITY_PRINCIPAL, "savia");
//            props.put(Context.SECURITY_CREDENTIALS, "savia");
//            props.put("jboss.naming.client.ejb.context", true);
            Context context = new InitialContext(props);
            String preContext = "ejb:savia-mipres-sincroniza-ear/savia-mipres-sincroniza-ejb-1.0-SNAPSHOT//";
            String nombre = preContext + nameEjb + "!" + iface;
            object = context.lookup(nombre);
        } catch (NamingException ex) {
            throw new Exception("It was not possible to connect to the remote EJB " + nameEjb);
        } catch (Exception ex) {
            throw new Exception("It was not possible to connect to the remote EJB " + nameEjb);
        }
        return object;
    }

    public static Object getEJBSolicitudRemoto(String nameEjb, String iface) throws Exception {
        Object object = null;
        try {
            Properties props = new Properties();
            props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            Context context = new InitialContext(props);
            String preContext = "ejb:savia-solicitud-ear/savia-solicitud-ejb-1.0-SNAPSHOT//";
            String nombre = preContext + nameEjb + "!" + iface;
            object = context.lookup(nombre);
        } catch (NamingException ex) {
            throw new Exception("It was not possible to connect to the remote EJB " + nameEjb);
        } catch (Exception ex) {
            throw new Exception("It was not possible to connect to the remote EJB " + nameEjb);
        }
        return object;
    }

    public static Object getGeneradorRemoto(String nameEjb, String iface) throws Exception {
        Object object = null;
        try {
            Properties props = new Properties();
            props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
//            props.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:7070");
//            props.put(Context.SECURITY_PRINCIPAL, "generador");
//            props.put(Context.SECURITY_CREDENTIALS, "generador");
//            props.put("jboss.naming.client.ejb.context", true);
            Context context = new InitialContext(props);
            String preContext = "ejb:generador-ear/generador-ejb-1.0-SNAPSHOT//";
            String nombre = preContext + nameEjb + "!" + iface;
            object = context.lookup(nombre);
        } catch (NamingException ex) {
            throw new Exception("It was not possible to connect to the remote EJB " + nameEjb);
        } catch (Exception ex) {
            throw new Exception("It was not possible to connect to the remote EJB " + nameEjb);
        }
        return object;
    }

    public static Object getEJBRemotoExterno(String nameEjb, String iface) throws Exception {
        Object object = null;
        try {
            Properties props = new Properties();
            props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
//            props.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:7070");
//            props.put(Context.SECURITY_PRINCIPAL, "savia");
//            props.put(Context.SECURITY_CREDENTIALS, "savia");
//            props.put("jboss.naming.client.ejb.context", true);
            Context context = new InitialContext(props);
            String preContext = "ejb:savia-externo-ear/savia-externo-ejb-1.0-SNAPSHOT//";
            String nombre = preContext + nameEjb + "!" + iface;
            object = context.lookup(nombre);
        } catch (NamingException ex) {
            throw new Exception("It was not possible to connect to the remote EJB " + nameEjb);
        } catch (Exception ex) {
            throw new Exception("It was not possible to connect to the remote EJB " + nameEjb);
        }
        return object;
    }

    public static Object getEJBRemotoHistorico(String nameEjb, String iface) throws Exception {
        Object object = null;
        try {
            Properties props = new Properties();
            props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
//            props.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:7070");
//            props.put(Context.SECURITY_PRINCIPAL, "savia");
//            props.put(Context.SECURITY_CREDENTIALS, "savia");
//            props.put("jboss.naming.client.ejb.context", true);
            Context context = new InitialContext(props);
            String preContext = "ejb:savia-historico-ear/savia-historico-ejb-1.0-SNAPSHOT//";
            String nombre = preContext + nameEjb + "!" + iface;
            object = context.lookup(nombre);
        } catch (NamingException ex) {
            throw new Exception("It was not possible to connect to the remote EJB " + nameEjb);
        } catch (Exception ex) {
            throw new Exception("It was not possible to connect to the remote EJB " + nameEjb);
        }
        return object;
    }

//    public static Object getEJBRemotoSumario(String nameEjb, String iface) throws Exception {
//        Object object = null;
//        try {
//            Properties props = new Properties();
//            props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//            Context context = new InitialContext(props);
//            String preContext = "ejb:savia-sumario-ear/savia-sumario-ejb-1.0-SNAPSHOT//";
//            String nombre = preContext + nameEjb + "!" + iface;
//            object = context.lookup(nombre);
//        } catch (NamingException ex) {
//            throw new Exception("It was not possible to connect to the remote EJB " + nameEjb);
//        } catch (Exception ex) {
//            throw new Exception("It was not possible to connect to the remote EJB " + nameEjb);
//        }
//        return object;
//    }
    
    public static Object getEJBRemotoGenerico(String nameEjb, String iface) throws Exception {
        Object object = null;
        try {
            Properties props = new Properties();
            props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
//            props.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:7070");
//            props.put(Context.SECURITY_PRINCIPAL, "savia");
//            props.put(Context.SECURITY_CREDENTIALS, "savia");
//            props.put("jboss.naming.client.ejb.context", true);
            Context context = new InitialContext(props);
            String preContext = "ejb:savia-generico-ear/savia-generico-ejb-1.0-SNAPSHOT//";
            String nombre = preContext + nameEjb + "!" + iface;
            object = context.lookup(nombre);
        } catch (NamingException ex) {
            throw new Exception("It was not possible to connect to the remote EJB " + nameEjb);
        } catch (Exception ex) {
            throw new Exception("It was not possible to connect to the remote EJB " + nameEjb);
        }
        return object;        
//        Object object = null;
//        try {
////            String socket = PropApl.getInstance().get(PropApl.GN_SOCKET_BACK_3);
//            String socket = "10.240.24.34:8080";
//            Properties props = new Properties();
//            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
//            props.put(Context.PROVIDER_URL, "http-remoting://" + socket);
//            props.put(Context.SECURITY_PRINCIPAL, "remote");
//            props.put(Context.SECURITY_CREDENTIALS, "R3Mn0iE)#p;XD_");
//            Context context = new InitialContext(props);
//            String preContext = "ejb:savia-generico-ear/savia-generico-ejb-1.0-SNAPSHOT//";
//            String nombre = preContext + nameEjb + "!" + iface;
//            object = context.lookup(nombre);
//        } catch (NamingException ex) {
//            throw new Exception("No fué posible conectarse al remoto EJB Genérico. Error: " + nameEjb);
//        } catch (Exception ex) {
//            throw new Exception("No fué posible conectarse al remoto EJB Genérico. Error: " + nameEjb);
//        }
//        return object;
    }

//    public static SingletonHiloRemoto getGenericoSingletonRemoto() throws NamingException {
//        String socket = PropApl.getInstance().get(PropApl.GN_SOCKET_BACK_3);
//        Properties properties = new Properties();
//        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
//        properties.put(Context.PROVIDER_URL, "http-remoting://" + socket);
//        InitialContext ctx = new InitialContext(properties);
//        return (SingletonHiloRemoto) ctx.lookup("ejb:savia-generico-ear/savia-generico-ejb-1.0-SNAPSHOT/SingletonControlHilos!com.saviasaludeps.savia.negocio.generico.SingletonHiloRemoto");
//    }

}
