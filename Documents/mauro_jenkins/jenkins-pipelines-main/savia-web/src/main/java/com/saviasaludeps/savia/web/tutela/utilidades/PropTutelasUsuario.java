/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.tutela.utilidades;

import com.saviasaludeps.savia.web.utilidades.*;
import com.saviasaludeps.savia.negocio.tutela.TuCargaFijoRemoto;

import java.util.HashMap;

/**
 *
 * @author pavacca
 */
public class PropTutelasUsuario {

    //public static final int MAESTRO_ACCI_ESTADO_FALLO = 1;
    //public static final int MAESTRO_ACCI_ESTADO_SANS_COMF = 2;
    //public static final int MAESTRO_ACCI_ESTADO_TUTELA_NUEVA = 3;
    //public static final int MAESTRO_ACCI_ESTADO_SANSION = 4;
    //public static final int MAESTRO_ACCI_ESTADO_FALLO_E_CONT = 5;
    //public static final int MAESTRO_ACCI_ESTADO_CLA_SAN_MULT = 6;
    //public static final int MAESTRO_ACCI_ESTADO_CLA_SAN_ARRE = 7;
    //public static final int MAESTRO_ACCI_ESTADO_CLA_SAN_AMB = 8;
    public static final int INCREMENTO_DIAS_TUTELA_FECHA_VEN = 9;
    public static final int TIPO_SEGUIMIENTO_ABOGADO = 10;
    public static final int TIPO_SEGUIMIENTO_GESTOR = 11;
    public static final int TIPO_SEGUIMIENTO_MEDICO = 12;
    public static final int TIPO_ESTADO_ASIGNAR = 13;
    public static final int TIPO_ESTADO_CIERRE_PARCIAL = 14;
    public static final int MAXIMO_CANTIDAD_ANEXOS = 15;
    public static final int MAXIMO_TAMANO_ANEXO = 16;
    public static final int REST_TIPO_ESTADO_FALLO = 17;
    //public static final int TIPO_ESTADO_CIERRE_FINAL = 18;
    public static final int TIPO_PROCESO_ARCHIVADO = 19;
    //public static final int MAESTRO_ACCI_ESTADO_FALLO_SEG_IN = 20;
    //public static final int MAESTRO_ACCI_ESTADO_TERMIN_NULI = 21;
    //public static final int MAESTRO_ACCI_ESTADO_REQUERI_MP = 22;
    //public static final int MAESTRO_ACCI_ESTADO_DESACAT_MP = 23;
    //public static final int MAESTRO_ACCI_ESTADO_SANSION_MP = 24;
    //public static final int MAESTRO_ACCI_ESTADO_INAP_SAN_MP = 25;
    //public static final int MAESTRO_ACCI_ESTADO_TER_DESA_MP = 26;
    //public static final int MAESTRO_ACCI_ESTADO_DESISTIMIENT = 27;
    public static final int ESTADO_PERSONA_DEFECTO_CREAR = 28;
    //public static final int TIPO_SEGUIMIENTO_GESTION_GENERAL = 29;
    //public static final int TIPO_SEGUIMIENTO_MEMOR_JUZGADO = 30;
    //public static final int TIPO_SEGUIMIENTO_REPARTO = 31;
    public static final int TIPO_PROCESO_EN_TRAMITE = 32;
    public static final int TIPO_PROCESO_INACTIVO = 33;
    //public static final int TIPO_SEGUIMIENTO_ARCHIVADO = 34; 
    //public static final int TIPO_SEGUIMIENTO_INACTIVO = 35;
    public static final int TIPO_PROCESO_CIERRE_PARCIAL = 36;
    public static final int TIPO_PROCESO_CUMPLIMIENTO = 37;
    public static final int TECNOLOGIA_POR_DEFECTO_CODIGO = 38;
    public static final int TECNOLOGIA_POR_DEFECTO_VALOR = 39;
    public static final int TU_JUZGADO_TELEFONO_O_EMAIL = 40;
    
    private TuCargaFijoRemoto getConfiguracionRemoto() throws Exception {
        return (TuCargaFijoRemoto) RemotoEJB.getEJBRemoto("TuCargaFijoServicio", TuCargaFijoRemoto.class.getName());
    }

    private static PropTutelasUsuario propAplInstance = null;
    private HashMap<Integer, String> hashConfiguraciones = new HashMap();

    public PropTutelasUsuario() {
        try {
            hashConfiguraciones = getConfiguracionRemoto().consultarHash();
        } catch (Exception ex) {
            hashConfiguraciones = new HashMap();
        }
    }

    public static PropTutelasUsuario getInstance() {
        if (propAplInstance == null) {
            propAplInstance = new PropTutelasUsuario();
        }
        return propAplInstance;
    }

    public static PropTutelasUsuario getInstanceLimpiar() {
        propAplInstance = new PropTutelasUsuario();
        return propAplInstance;
    }

    public String get(int id) {
        return hashConfiguraciones.get(id);
    }

}
