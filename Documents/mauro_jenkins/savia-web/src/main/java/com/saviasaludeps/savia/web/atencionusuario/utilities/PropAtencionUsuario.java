/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.atencionusuario.utilities;

import com.saviasaludeps.savia.web.utilidades.*;
import com.saviasaludeps.savia.negocio.atencionusuario.AusCargaFijoRemoto;

import java.util.HashMap;

/**
 *
 * @author jperezn
 */
public class PropAtencionUsuario {
    
    public static final int SERVICIO_ESTADO_POR_DEFECTO  = 1;
    public static final int SERVICIO_ESPECIALIDAD_DEFECTO = 2;
    public static final int CASO_PRIORIDAD_POR_DEFECTO    = 3;
    public static final int CASO_UBICACION_POR_DEFECTO  = 4;
    public static final int CASO_SEDE_POR_DEFECTO    = 5;
    public static final int SERVICIO_SEDE_PRESCRIPTORA  = 6;
    public static final int SERVICIO_PRESTADOR_SEDE_DESTINO = 7;
    public static final int AUS_HORAS_SERVIC = 8;
    public static final int CASO_ESTADO_POR_DEFECTO  = 9;
    public static final int PERSONA_ESTADO_POR_DEFECTO  = 10;
    public static final int SEGUIMIENTO_ESTADO_POR_DEFECTO   = 11;
    public static final int CASO_ORIGEN_POR_DEFECTO  = 12;
    public static final int DESC_SEGUI_CERRA  = 13;
    public static final int DESC_SEGUI_RADIC  = 14;
    public static final int TIEMP_MENS_DEFEC  = 15;
    public static final int TIEMP_MENS_CREAC  = 16;
    public static final int DES_CASO_EST_RAD  = 17;
    public static final int MAX_CANT_ANEXOS  = 18;
    public static final int MAX_TAM_ANEXO  = 19;
    public static final int CASO_SERVICIO_ESTADO_CERRADO  = 20;
    public static final int CASO_SERVICIO_ESTADO_RESUELTO  = 21;
    public static final int CASO_SERVICIO_ESTADO_ASIGNADO  = 22;
    public static final int CASO_ESTADO_CERRADO  = 23;
    public static final int CASO_SEGUIMIENTO_ESTADO_CERRADO  = 24;
    public static final int CARGA_MASIVA_REGISTROS_VALIDOS = 25;
    public static final int CASO_ESTADO_RADICADO = 26;
    public static final int CASO_ENTE_CONTROL_SUPER_SALUD = 27;
    public static final int CASO_SERVICIO_ESTADO_ANULADO = 28;
    public static final int TEXTO_NO_INCLUSION_PETICIONARIO = 29;
    public static final int CASO_SERVICIO_ESTADO_ESTUDIO = 30;
    public static final int EXTERNO_CASO_PRIORIDAD = 31;
    public static final int EXTERNO_CASO_RIESGO_VIDA = 32;
    public static final int EXTERNO_CASO_ORIGEN_PAGINA_WEB = 33;
    public static final int EXTERNO_CASO_RESPONSABLE = 34;
    public static final int EXTERNO_CASO_PERSONA_ANONIMA = 35;
    public static final int EXTERNO_CASO_EMPRESA_DEFECTO = 36;
    public static final int CASO_ESTADO_CANCELADO = 37;
    public static final int CASO_ESTADO_GESTION = 38;
    public static final int CASO_ESTADO_SOLUCIONADO = 39;
    public static final int CASO_SEGUIMI_ESTADO_CANCELADO = 40;
    public static final int CASO_SEGUIMI_ESTADO_GESTION = 41;
    public static final int CASO_SEGUIMI_ESTADO_SOLUCIONADO = 42;
    public static final int AUS_CASO_RE_ABRIR_CASO = 43;
    public static final int AUS_CASO_REABIERTO = 44;
    public static final int AUS_CASO_EMAIL_SERVICIO_CERRADO = 45;
    public static final int AUS_CASO_MOTIVO = 46;
    
    
    private AusCargaFijoRemoto getConfiguracionRemoto() throws Exception {
        return (AusCargaFijoRemoto) RemotoEJB.getEJBRemoto("AusCargaFijoServicio", AusCargaFijoRemoto.class.getName());
    }

    private static PropAtencionUsuario propAplInstance = null;
    private HashMap<Integer, String> hashConfiguraciones = new HashMap();

    public PropAtencionUsuario() {
        try {
            hashConfiguraciones = getConfiguracionRemoto().consultarHash();
        } catch (Exception ex) {
            hashConfiguraciones = new HashMap();
        }
    }

    public static PropAtencionUsuario getInstance() {
        if (propAplInstance == null) {
            propAplInstance = new PropAtencionUsuario();
        }
        return propAplInstance;
    }

    public static PropAtencionUsuario getInstanceLimpiar() {
        propAplInstance = new PropAtencionUsuario();
        return propAplInstance;
    }

    public String get(int id) {
        return hashConfiguraciones.get(id);
    }

}
