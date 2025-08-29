package com.saviasaludeps.savia.web.utilidades;

import com.saviasaludeps.savia.negocio.administracion.ConfiguracionRemoto;
import java.util.HashMap;

/**
 *
 * @author raul-palacios
 */
public class PropApl extends com.saviasaludeps.savia.utilidades.PropApl {

    private ConfiguracionRemoto getConfiguracionRemoto() throws Exception {
        return (ConfiguracionRemoto) RemotoEJB.getEJBRemoto("ConfiguracionServicio", ConfiguracionRemoto.class.getName());
    }

    private static PropApl propAplInstance = null;
    private HashMap<Integer, String> hashConfiguraciones = new HashMap();

    //mipres
    public static final int MP_WS_TOKEN_USUARIO = 82;
    public static final int MP_WS_TOKEN_CONTRASENA = 83;
    public static final int MP_WS_TOKEN_URL = 84;
    public static final int MP_WS_URL_SINCRO_DIRECCIONAMIENTOS_SUB = 213;
    public static final int MP_WS_URL_SINCRO_DIRECCIONAMIENTOS_CON = 214;

    public static final int MP_WS_URL_SINCRO_ANULACIONXPRESCRIPCION_SUB = 219;
    public static final int MP_WS_URL_SINCRO_ANULACIONXPRESCRIPCION_CON = 218;
    public static final int MP_WS_URL_SINCRO_JUNTAS_SUB = 221;
    public static final int MP_WS_URL_SINCRO_JUNTAS_CON = 220;
    public static final int MP_WS_URL_SINCRO_NO_DIRECCIONAMIENTOS_SUB = 222;
    public static final int MP_WS_URL_SINCRO_NO_DIRECCIONAMIENTOS_CON = 223;
    public static final int MP_WS_URL_SINCRO_REPORTEENTREGAXPRESCRIPCION_SUB = 224;
    public static final int MP_WS_URL_SINCRO_REPORTEENTREGAXPRESCRIPCION_CON = 225;
    public static final int MP_WS_URL_SINCRO_FACTURAXPRESCRIPCION_SUB = 228;
    public static final int MP_WS_URL_SINCRO_FACTURAXPRESCRIPCION_CON = 229;
    
    
    //Esquema Informes
    public static final int GN_ESQUEMA_AMBIENTE = 13;

    private PropApl() {

    }

    public static PropApl getInstance() {
        if (propAplInstance == null) {
            propAplInstance = new PropApl();
        }
        return propAplInstance;
    }

    public String get(int id) {
        return hashConfiguraciones.get(id);
    }

    public boolean isEmpty() {
        return hashConfiguraciones.isEmpty();
    }

    public void actualizar() {
        try {
            HashMap<Integer, String> hashConfiguracionesLocal = getConfiguracionRemoto().consultarHash();
            hashConfiguraciones = (HashMap<Integer, String>) hashConfiguracionesLocal.clone();
        } catch (Exception ex) {
            hashConfiguraciones = new HashMap();
        }
    }

    public int cantidad() {
        return hashConfiguraciones.size();
    }

}
