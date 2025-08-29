package com.saviasaludeps.savia.web.utilidades;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RemotoEJBSolicitud {

    public static Object getEJBRemoto(String nameEjb, String iface) throws Exception {
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
    
}
