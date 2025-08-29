/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.utilidades;

import com.saviasaludeps.savia.negocio.administracion.ConfiguracionRemoto;
import java.util.HashMap;

/**
 *
 * @author raul-palacios
 */
public class PropApl {

    private ConfiguracionRemoto getConfiguracionRemoto() throws Exception {
        return (ConfiguracionRemoto) RemotoEJB.getEJBRemoto("ConfiguracionServicio", ConfiguracionRemoto.class.getName());
    }

    private static PropApl propAplInstance = null;
    private HashMap<Integer, String> hashConfiguraciones = new HashMap();

    public static final int BD1_CONEXION_URL = 70;
    public static final int BD1_CONEXION_USUARIO = 71;
    public static final int BD1_CONEXION_CONTRASENA = 72;

    public static final int BD2_CONEXION_URL = 73;
    public static final int BD2_CONEXION_USUARIO = 74;
    public static final int BD2_CONEXION_CONTRASENA = 75;

    public static final int BD3_CONEXION_URL = 76;
    public static final int BD3_CONEXION_USUARIO = 77;
    public static final int BD3_CONEXION_CONTRASENA = 78;
    //adjuntos
    public static final int REF_RUTA_ADJUNTOS = 43;
    public static final int AU_A3_ADJUNTOS = 44;
    public static final int AU_A4_ANEXOS = 47;
    //ma tecnologia
    public static final int REF_URGENCIA_MEDICA_TECNOLOGIA = 100;
    public static final int REF_URGENCIA_ODONTOLOGICA_TECNOLOGIA = 101;
    
    //inf_Procedimientos
    public static final int GN_ESQUEMA_AMBIENTE = 13;
  
    private PropApl() {
        try {
            hashConfiguraciones = getConfiguracionRemoto().consultarHash();
        } catch (Exception ex) {
            hashConfiguraciones = new HashMap();
        }
    }

    public static PropApl getInstance() {
        if (propAplInstance == null) {
            propAplInstance = new PropApl();
        }
        return propAplInstance;
    }

    public static PropApl getInstanceLimpiar() {
        propAplInstance = new PropApl();
        return propAplInstance;
    }

    public String get(int id) {
        return hashConfiguraciones.get(id);
    }

}
