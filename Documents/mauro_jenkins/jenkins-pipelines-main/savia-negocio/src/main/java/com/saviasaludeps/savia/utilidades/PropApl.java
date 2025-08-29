
package com.saviasaludeps.savia.utilidades;

/**
 *
 * @author rpalacios
 */
public class PropApl {
    
    public static final int BD1_CONEXION_URL = 70;
    public static final int BD1_CONEXION_USUARIO = 71;
    public static final int BD1_CONEXION_CONTRASENA = 72;

    public static final int BD2_CONEXION_URL = 73;
    public static final int BD2_CONEXION_USUARIO = 74;
    public static final int BD2_CONEXION_CONTRASENA = 75;

    public static final int BD3_CONEXION_URL = 76;
    public static final int BD3_CONEXION_USUARIO = 77;
    public static final int BD3_CONEXION_CONTRASENA = 78;
    
    public static final int RUTA_HISTORICO = 1;
    public static final int GN_RUTA_MANUALES_AYUDA = 1;
    public static final int GN_SOCKET_BACK_3 = 120;
    public static final int GN_LOGIN_INTENTOS = 141;
    public static final int GN_VENCIMIENTO_CONTRASEGNA = 142;
    public static final int GN_HTTPS = 160;
    public static final int GN_MAPS_KEY = 238;
    public static final int GN_MAPS_IMAGENES = 239;
    //Correo electrónico 1 (Genérico)
    public static final int SMTP_BASE_AUTH_USER = 2;
    public static final int SMTP_BASE_AUTH_PWD = 3;
    public static final int SMTP_BASE_HOST_NAME = 4;
    public static final int SMTP_BASE_HOST_PORT = 5;
    public static final int SMTP_BASE_SECURITY = 6;
    //Correo electrónico 2 (Solicitudes)
    public static final int SMTP_WEB_AUTH_USER = 136;
    public static final int SMTP_WEB_AUTH_PWD = 137;
    public static final int SMTP_WEB_HOST_NAME = 138;
    public static final int SMTP_WEB_HOST_PORT = 139;
    public static final int SMTP_WEB_SECURITY = 140;
    //Correo electronico 3 (Portabilidad)
    public static final int SMTP_PORTABILIDAD_AUTH_USER = 154;
    public static final int SMTP_PORTABILIDAD_AUTH_PWD = 155;
    public static final int SMTP_PORTABILIDAD_HOST_NAME = 156;
    public static final int SMTP_PORTABILIDAD_HOST_PORT = 157;
    public static final int SMTP_PORTABILIDAD_SECURITY = 158;
    //Mensajes de texto
    public static final int SMS_REST_URL = 7;
    public static final int SMS_REST_COUNTRY = 8;
    public static final int SMS_REST_USER = 9;
    public static final int SMS_REST_PWD = 10;
    public static final int SMS_WS_CONSUMO = 11;
    public static final int SMS_WS_TOKEN = 12;
    //Logs
    public static final int LOG_RUTA_SUCESOS = 95;
    public static final int LOG_RUTA_ERRORES = 96;
    /**
     * ASEGURAMIENTO
     */
    public static final int RUTA_ASEGURAMIENTO_ANEXO1_SOPORTES = 17;
    public static final int RUTA_ASEGURAMIENTO_CERTIFICADOS = 18;
    public static final int RUTA_ASEGURAMIENTO = 19;
    public static final int RUTA_ASEGURAMIENTO_REPORTES = 20;
    public static final int RUTA_ASEGURAMIENTO_CARGA_MASIVA_AFILIADOS = 21;
    public static final int RUTA_ASEGURAMIENTO_CARGA_MASIVA_NOVEDADES = 22;
    public static final int RUTA_ASEGURAMIENTO_VALIDACION_DERECHOS = 23;
    /**
     * CONTRATACION
     */
    public static final int CNT_RUTA_CARGA_MASIVA_CONTRATOS = 80;
    /**
     * MAESTROS (PARAMETRIZACION)
     */
    public static final int MA_RUTA_CARGA_MAESTROS = 122;
    /**
     * CENTRO REGULADOR
     */
    public static final int REF_RUTA_ANEXOS = 42;
    public static final int REF_RUTA_ADJUNTOS = 43;
    public static final int REF_URGENCIA_MEDICA_TECNOLOGIA = 100;
    public static final int REF_URGENCIA_ODONTOLOGICA_TECNOLOGIA = 101;
    public static final int REF_URGENCIA_HABILITACION = 102;
    /**
     * RIPS
     */
    public static final int CM_RUTA_RIPS_CARGA = 24;
    public static final int CM_RUTA_RIPS_DESCARGA = 32;
    public static final int CM_RIPS_RANGO_FECHA_PRESTACION = 0;
    public static final int CM_FE_RUTA_RIPS_CARGA = 204;
    public static final int CM_FE_RUTA_INFORMACION_CUV_MINISTERIO = 207;
    public static final int CM_FE_RUTA_SOPORTE_CARGA = 217;
    /**
     * Rango en meses que poermite el despliegue en las fechas de carga de RIPS
     */
    public static final int CM_RIPS_RANGO_FECHA_CARGA = 135;
    /**
     * CUENTAS MÉDICAS (Web Services)
     */
    public static final int WS_USUARIO_TOKEN_CONCILIACION_FACTURA = 25;
    public static final int WS_CONTRASENA_TOKEN_CONCILIACION_FACTURA = 26;
    public static final int RUTA_SERVICIO_TOKEN_CONCILIACION_FACTURA = 27;
    public static final int RUTA_SERVICIO_CONCILIACION_FACTURA = 28;
    /**
     * CUENTAS MEDICAS
     */
    public static final int CM_WS_RUTA_SERVICIO_DEVOLUCION_FACTURA = 41;
    public static final int CM_RUTA_REPORTES = 29;
    public static final int CM_RUTA_AUDITORIA_DESCARGA = 40;
    public static final int CM_WS_NOTIFICACION_FACTURA_USUARIO_TOKEN = 34;
    public static final int CM_WS_NOTIFICACION_FACTURA_PASSWORD_TOKEN = 35;
    public static final int CM_WS_NOTIFICACION_FACTURA_URL = 36;
    public static final int CM_RUTA_CONCILIACION_CARGA_MASIVA = 121;
    public static final int CM_REINTENTO_CANTIDAD_MAXIMA = 146;
    public static final int CM_RUTA_MARCACION_MASIVA_IPS = 172;
    //Nuevos servicios
    public static final int CM_WS_NOTIFICACION_FACTURA_PROVISION_M1 = 148;
    public static final int CM_WS_NOTIFICACION_FACTURA_CAUSACION_M2 = 149;
    public static final int CM_WS_NOTIFICACION_FACTURA_RESP_GLOSA_M3 = 150;
    public static final int CM_WS_NOTIFICACION_FACTURA_CONCILIACION_M4 = 151;

    public static final int CM_WS_NOTIFICACION_FACTURA_INTEGRA_URL = 144;
    /**
     * GESTIÓN DEL RIESGO (Programas especiales)
     */
    public static final int PE_RUTA_ADJUNTOS_GESTIONES = 30;
    public static final int PE_RUTA_CARGA_MASIVA = 31;
    public static final int PE_RUTA_SUGERIDOS_ADJUNTOS = 147;
    public static final int PE_CARGA_GESTIONES_RUTA = 168;
    public static final int PE_RUTA_CARGA_MASIVA_VALORES_VARIABLES = 205;
    /**
     * AUTORIZACIONES
     */
    public static final int AU_TUTELA_CONSULTA_USUARIO_TOKEN = 37;
    public static final int AU_TUTELA_CONSULTA_PASSWORD_TOKEN = 38;
    public static final int AU_TUTELA_CONSULTA_URL = 39;
    public static final int AU_A3_ADJUNTOS = 44;
    public static final int AU_A3_CARGA_MASIVA = 45;
    public static final int AU_A3_ANEXOS = 46;
    public static final int AU_A4_ANEXOS = 47;
    public static final int AU_A2_ANEXOS = 48;
    public static final int AU_A4_REPORTES = 49;
    public static final int AU_RUTA_ADJUNTOS_COTIZACIONES = 81;
    public static final int AU_AUTORIZACION_AUTOMATICA = 97;
    public static final int AU_RUTA_CARGA_ENTREGA = 153;
    public static final int AU_RUTA_ADJUNTOS_SEGUIMIENTOS = 162;
    public static final int AU_RUTA_ADJUNTOS_SEGUIMIENTO_AFILIADOS = 163;
    public static final int AU_RUTA_ADJUNTOS_RESCATES = 167;

    /**
     * INFORMES
     */
    public static final int INF_RUTA_REPORTES = 110;
    public static final int INF_INFORMES_LIMITE_GENERADO = 174;
    //Rutas BD
//    public static final int BD1_CONEXION_URL = 70;
//    public static final int BD1_CONEXION_USUARIO = 71;
//    public static final int BD1_CONEXION_CONTRASENA = 72;
//    public static final int BD2_CONEXION_URL = 73;
//    public static final int BD2_CONEXION_USUARIO = 74;
//    public static final int BD2_CONEXION_CONTRASENA = 75;
    /**
     * ATENCION AL USUARIO
     */
    public static final int AUS_RUTA_CARGA = 124;
    public static final int AUS_DEFECTO_DESTINO = 127;
    public static final int AUS_DEFECTO_ESPECIALIDAD = 128;
    public static final int AUS_DEFECTO_ESTADO_SERVICIO = 129;
    public static final int AUS_DEFECTO_PRESCRIPTORA = 130;
    public static final int AUS_DEFECTO_PRIORIDAD = 131;
    public static final int AUS_DEFECTO_SEDE = 132;
    public static final int AUS_DEFECTO_UBICACION = 133;
    public static final int AUS_HORAS_SERVICIO = 134;
    public static final int AUS_ID_FILTRO_USUARIOS = 159;
    /**
     * SOLICITUDES
     */
    public static final int GS_SOLICITUDES_CARGA = 126;
    /**
     * TUTELAS
     */
    public static final int TU_RUTA_CARGA = 143;
    /**
     * AUDITORIA CONCURRENTE
     */
    public static final int AUC_RUTA_CARGA_MASIVA_HOSPITALIZACIONES = 145;
    public static final int AUC_RUTA_CARGA_MASIVA_CIERRES = 152;

    /**
     * GESTION DE ATENCION
     */
    public static final int GAT_MAXIMO_ATENCIONES = 161;
    public static final int GAT_MAXIMO_LLAMADOS = 166;
    /**
     * LIMITES CARGAS MASIVAS
     */
    public static final int CARGAS_LIMITES_PARAMETRIZACION = 198;
    public static final int CARGAS_LIMITES_PRESTACION = 199;
    /**
     * MIPRES WS
     */
    public static final int MP_WS_CONSULTAANULAR_PRESCRIPCION_SUB = 186;
    public static final int MP_WS_CONSULTAANULAR_PRESCRIPCION_CON = 187;
    /**
     * ANTICIPOS
     */
    public static final int ANT_RUTA_ANTICIPOS_ADJUNTOS = 203;
    /**
     * CARGAS
     */
    public static final int CAR_RUTA_CARGAS_MASIVAS = 206;
    /**
     * CONTRATACION JURIDICA
     */
    public static final int CNTJ_RUTA_LINEAS_ADJUNTOS = 208;
    public static final int CNTJ_RUTA_OTROSIES_ADJUNTOS = 209;
    public static final int CNTJ_RUTA_SEGUIMIENTOS_ADJUNTOS = 210;
    public static final int CNTJ_RUTA_EXPEDIENTES_ADJUNTOS = 216;
    /**
     * FINANCIERA
     */
    public static final int FIN_RUTA_CARGA_ARCHIVOS = 215;
    /**
     * RECOBROS
     */
    public static final int RCO_RUTA_ENVIO_COMPRIMIDOS = 241;
    public static final int RCO_RUTA_CARGA_ADJUNTO = 242;
}
