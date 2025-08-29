/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.utilidades;

import java.util.Locale;

/**
 *
 * @author jyperez
 */
public class AfiliadoParametro {
    
    //1. Opción Afiliados
    public final static String ESTADO_AFILIACION_PENDIENTE_SOPORTE = "777";
    public final static String ESTADO_AFILIACION_SUSPENDIDO = "103";
    public final static String ESTADO_AFILIACION_RETIRADO = "104";
    public final static String ESTADO_AFILIACION_FALLECIDO = "107";
    //2024-01-10 jyperez nuevo estado
    public final static String ESTADO_AFILIACION_DUPLICADO = "109";
    public final static String ESTADO_AFILIACION_RETIRADO_RES_MUNICIPIO = "110";
    //2021-09-27 jyperez nuevos estados REQ 140
    public final static String ESTADO_AFILIACION_PROTECCION_LABORAL = "779";
    public final static String ESTADO_AFILIACION_ACTIVO_POR_EMERGENCIA = "778";

    //2021-05-20 jyperez se actualiza el código al valor que tiene el maestro de régimen
    public static final String REGIMEN_SUBSIDIADO = "01";
    public static final String REGIMEN_CONTRIBUTIVO = "02";
    public static final String REGIMEN_SUBSIDIADO_ANTIGUO = "1";
    public static final String REGIMEN_CONTRIBUTIVO_ANTIGUO = "2";

    public static final String MODELO_LIQUIDACION_CAPITA = "0";
    public static final String MODELO_LIQUIDACION_EVENTO = "1";

    public final static int IDENTIFICADOR_DEPARTAMENTO_POR_DEFECTO = 2;
    public final static int IDENTIFICADOR_MEDELLIN = 3;

    public final static String NIVEL_1_SISBEN = "1";
    public final static String NIVEL_2_SISBEN = "2";

    public final static String ZONA_URBANA = "U";
    public final static String ZONA_RURAL = "R";

    //public final static String IDENTIFICADOR_DIVIPOLA_POR_DEFECTO = "05001";
    public final static String IDENTIFICADOR_UBICACION_ID_POR_DEFECTO = "3";
    //2023-03-13 jyperez configuración prefijos de Antioquia y Medellin
    public final static String UBICACION_PREFIJO_ANTIOQUIA = "05";
    public final static String UBICACION_PREFIJO_MEDELLIN = "001";
    
    public final static String TIPO_AFILIADO_BENEFICIARIO = "102";
    public final static String TIPO_AFILIADO_ADICIONAL = "104";
    public final static String ORIGEN_AFILIADO_NACIMIENTO = "NPNA";
    public final static String ORIGEN_AFILIADO_TRASLADO_OTRA_EPS = "TSOE";
    public final static String ORIGEN_AFILIADO_TRASLADO_EPS_LIQUIDADA = "296";
    public final static String ORIGEN_AFILIADO_AFILIACION_TRANSACCIONAL_SAT = "ASAT";

    public final static String TIPO_DOCUMENTO_CEDULA_DE_CIUDADANIA = "CC";
    public final static String TIPO_DOCUMENTO_ADULTO_SIN_IDENTIFICACION = "AS";
    public final static String TIPO_DOCUMENTO_CARNE_DIPLOMATICO = "CD";
    public final static String TIPO_DOCUMENTO_CEDULA_EXTRANGERIA = "CE";
    public final static String TIPO_DOCUMENTO_CERTIFICADO_NACIDO_VIVO = "CN";
    public final static String TIPO_DOCUMENTO_MENOR_SIN_IDENTIFICACION = "MS";
    public final static String TIPO_DOCUMENTO_NIT = "NI";
    public final static String TIPO_DOCUMENTO_NIUP = "NU";
    public final static String TIPO_DOCUMENTO_PASAPORTE = "PA";
    public final static String TIPO_DOCUMENTO_PERMISO_ESPECIAL_PERMANENCIA = "PE";
    public final static String TIPO_DOCUMENTO_REGISTRO_CIVIL = "RC";
    public final static String TIPO_DOCUMENTO_RUT = "RUT";
    public final static String TIPO_DOCUMENTO_SALVOCONDUCTO = "SC";
    public final static String TIPO_DOCUMENTO_TARJETA_IDENTIDAD = "TI";
    //2021-09-27 jyperez REQ 143 nuevo tipo documento
    public final static String TIPO_DOCUMENTO_PERMISO_PROTECCION_TEMPORAL = "PT";
    public final static String GRUPO_POBLACIONAL_POBLACION_SISBENIZADA = "5";
    public final static String GRUPO_POBLACIONAL_POBLACION_31 = "31";
    public final static String CONDICION_DISCAPACIDAD_TEMPORAL = "2";
    public final static String CAUSA_NOVEDAD_MOVILIDAD_INGRESO_SUBSIDIADO = "IS-36";
    
    //2021-10-29 jyperez se definen valores para origen ultimo registro
    public final static int ORIGEN_ULTIMO_REGISTRO_PANTALLA = 1;
    
    //2021-05-14 jyperez Se adicionan los tipos de certificados a gestionar
    public final static int TIPO_CERTIFICADO_AFILIACION = 1;
    public final static int TIPO_CERTIFICADO_PORTABILIDAD = 2;
    public final static int TIPO_CERTIFICADO_FISCALIA = 3;
    public final static int CERTIFICADO_DIAS_VIGENCIA = 30;
    
    //2022-01-13 jyperez se adiciona etnia indigena
    public final static String ETNIA_INDIGENA = "1";
    
    public final static String TIPO_CONTACTO_TELEFONO = "1";
    public final static String TIPO_CONTACTO_CELULAR = "2";
    public final static String TIPO_CONTACTO_CORREO_ELECTRONICO = "3";
    //CAMBIO NOMBRE OK
    public final static int TIPO_ARCHIVO_NOVEDADES_AFILIADO = 50;
    
    //2022-04-25 jyperez se adiciona los niveles de sisben
    public final static String GRUPO_SISBEN_A = "A";
    public final static String GRUPO_SISBEN_B = "B";
    public final static String GRUPO_SISBEN_C = "C";
    public final static String GRUPO_SISBEN_D = "D";
    
    //2022-07-21 jyperez adicionamos parámetro población no pobre no vulnerable
    public final static String GRUPO_POBLACIONAL_POBLACION_NO_POBRE_NO_VULNERABLE = "34";
    
    //2022-09-15 jyperez adición parámetros para validación de afiliado sin encuesta sisben 4
    public final static String METODOLOGIA_GRUPO_POBLACIONAL_SISBEN_3 = "1";
    
    //2022-12-22 jyperez parametro validación pais Venezuela (se configura codigo prefijo)
    public final static String GN_UBICACIONES_PAIS_VENEZUELA = "862";
    
    //2023-06-15 jyperez parámetro validación pais nacimiento Colombia (se configura código)
    public final static String GN_UBICACIONES_PAIS_COLOMBIA = "170";

    //2 Opción Portabilidad
    
    //public final static int TIPO_CERTIFICADO_PORTABILIDAD = 2;
    //public final static int CERTIFICADO_DIAS_VIGENCIA = 30;
    
    public static final String ESTADO_EN_TRAMITE = "1";
    public static final String ESTADO_APROBADA = "2";

    //public final static int IDENTIFICADOR_DEPARTAMENTO_POR_DEFECTO = 2;
    public final static String IDENTIFICADOR_DIVIPOLA_POR_DEFECTO = "05001";
    public final static int IDENTIFICADOR_ESTADO_ACTIVO_AFILIACION = 134;
    public final static String AFILIADO_ESTADO_ACTIVO_AFILIACION = "101";
    public final static String IDENTIFICADOR_REGIMEN_SUBSIDIADO = "1";

    public final static int PERIODO_MAXIMO_PORTABILIDAD_CREACION = 12;
    public final static int PERIODO_MAXIMO_PORTABILIDAD_PRORROGA = 24;
    public final static int LIMITE_HABILITACION_ESTADO_TERMINADO = 10;
    public final static int LIMITE_HABILITACION_ESTADO_APROBADO = 4;
    public final static int LIMITE_HABILITACION_ESTADO_PRORROGA = 3;
    
    //public static final String MODELO_LIQUIDACION_EVENTO = "1";
    
    //3 Opción Anexo 1
    
    public final static int ID_SAVIA_SALUD = 1;
    
    //public final static int ESTADO_EN_COLA = 0;
    public final static int ESTADO_PENDIENTE = 1;
    public final static int ESTADO_GESTIONADO = 2;
    public final static int ESTADO_RECHAZADO = 3;
    
    public final static String TIPO_DOCUMENTO_POR_DEFECTO = "CC";
    public final static String NOMBRE_EPS = "SAVIASALUD EPS-S";
    public final static String CODIGO_EPS = "EPSS40";
    public final static String TIPO_INCONSISTENCIA = "2";
    public final static int ESTADO_INCONSISTENCIA_NO_APLICA = 0;
    public final static int ESTADO_INCONSISTENCIA_PENDIENTE_GESTION = 1;
    public final static int ESTADO_INCONSISTENCIA_ACEPTADO = 2;
    public final static int ESTADO_INCONSISTENCIA_RECHAZADO = 3;
    
    public final static String NO_APLICA_INCONSISTENCIA = "0";
    public final static String APLICA_INCONSISTENCIA = "1";
    
//    public final static String TIPO_DOCUMENTO_CEDULA_DE_CIUDADANIA = "CC";
//    public final static String TIPO_DOCUMENTO_ADULTO_SIN_IDENTIFICACION = "AS";
//    public final static String TIPO_DOCUMENTO_CARNE_DIPLOMATICO = "CD";
//    public final static String TIPO_DOCUMENTO_CEDULA_EXTRANGERIA = "CE";
//    public final static String TIPO_DOCUMENTO_CERTIFICADO_NACIDO_VIVO = "CN";
//    public final static String TIPO_DOCUMENTO_MENOR_SIN_IDENTIFICACION = "MS";
//    public final static String TIPO_DOCUMENTO_NIT = "NI";
//    public final static String TIPO_DOCUMENTO_NIUP = "NU";
//    public final static String TIPO_DOCUMENTO_PASAPORTE = "PA";
//    public final static String TIPO_DOCUMENTO_PERMISO_ESPECIAL_PERMANENCIA = "PE";
//    public final static String TIPO_DOCUMENTO_REGISTRO_CIVIL = "RC";
//    public final static String TIPO_DOCUMENTO_RUT = "RUT";
//    public final static String TIPO_DOCUMENTO_SALVOCONDUCTO = "SC";
//    public final static String TIPO_DOCUMENTO_TARJETA_IDENTIDAD = "TI";
//    public final static String TIPO_DOCUMENTO_PERMISO_PROTECCION_TEMPORAL = "PT";
    //2021-11-03 jyperez se definen valores para origen ultimo registro
    public final static int ORIGEN_ULTIMO_REGISTRO_ANEXO_1 = 4;
    
    //2022-11-25 jyperez tipos de contacto
//    public final static String TIPO_CONTACTO_TELEFONO = "1";
//    public final static String TIPO_CONTACTO_CELULAR = "2";
    
    //4 Opción Carga Masiva
    
    public final static int ESTADO_EN_COLA = 0;
    public final static int ESTADO_PROCESANDO = 1;
    public final static int ESTADO_PROCESADO = 2;
    public final static int ESTADO_ABORTADO = 3;
    
    public final static int ESTADO_DETALLE_CARGA_INGRESADO = 3;
    public final static int ESTADO_DETALLE_CARGA_FALLIDO = 4;
    
    public final static int TIPO_ARCHIVO_AFILIADOS = 1;
    public final static int TIPO_ARCHIVO_NOVEDADES = 2;
    public final static int TIPO_ARCHIVO_NOVEDADES_V2 = 3;
    
//    public final static int IDENTIFICADOR_DEPARTAMENTO_POR_DEFECTO = 2;
//    public final static String IDENTIFICADOR_DIVIPOLA_POR_DEFECTO = "05001";
//    public final static int IDENTIFICADOR_ESTADO_ACTIVO_AFILIACION = 134;
//    public final static String IDENTIFICADOR_REGIMEN_SUBSIDIADO = "1";
    
    public final static String ENCABEZADO_IMPRESION_ARCHIVO_AFILIADOS = "id,consecutivo,existe_bdua,serial_bdua,tipo_documento_bdua,numero_documento_bdua,primer_apellido_bdua,segundo_apellido_bdua,primer_nombre_bdua,segundo_nombre_bdua,fecha_nacimiento_bdua,genero_bdua,tipo_documento,numero_documento,primer_apellido,segundo_apellido,primer_nombre,segundo_nombre,fecha_nacimiento,genero,fecha_expedicion_cedula,tipo_documento_cf,numero_documento_cf,fecha_afiliacion_sgsss,fecha_afiliacion_eps,tipo_beneficiario,estado_afiliacion,origen_afiliado,parentesco_cotizante,autoriza_envio_sms,autoriza_envio_email,telefono_fijo,telefono_movil,email,afiliacion_ubicaciones,zona,direccion_residencia,nivel_sisben,puntaje_sisben,ficha_sisben,fecha_sisben,primaria_prestador_sedes_id,discapacidad,tipo_discapacidad,condicion_discapacidad,fecha_Inicio_discapacidad,fecha_fin_discapacidad,grupo_poblacional,victima,etnia,estado_civil,subgrupo_sisben,barrio,pais_nacimiento,pais_nacionalidad,genero_identificacion,lugar_nacimiento,estado,detalle_fallo,fecha_hora_proceso\n";
    public final static String ENCABEZADO_IMPRESION_ARCHIVO_NOVEDADES = "id,Consecutivo,contrato_afiliado,registro_bdua,serial_bdua,tipo_documento,numero_documento,fecha_expedicion_cedula,primer_apellido,segundo_apellido,primer_nombre,segundo_nombre,fecha_nacimiento,genero,estado_civil,origen_afiliado,tipo_beneficiario,parentesco_cotizante,tipo_documento_cf,numero_documento_cf,regimen,fecha_movilidad,categoria_ibc,tipo_cotizante,tipo_documento_aportante,numero_documento_aportante,actividad_economica,arl,afp,ccf,fecha_afiliacion_sgsss,fecha_afiliacion_eps,municipio_afiliacion,municipio_residencia,direccion_residencia,barrio,zona,telefono_fijo,telefono_movil,email,autoriza_envio_sms,autoriza_envio_email,codigo_habilitacion_ips_primaria,grupo_poblacional,victima,etnia,nivel_sisben,puntaje_sisben,ficha_sisben,fecha_sisben,discapacidad,tipo_discapacidad,condicion_discapacidad,fecha_Inicio_discapacidad,fecha_fin_discapacidad,modelo_liquidacion,estado_afiliacion,fecha_novedad,causa_novedad,observacion,subgrupo_sisben,estado,detalle_fallo,fecha_hora_proceso\n";
    public final static String ENCABEZADO_IMPRESION_ARCHIVO_NOVEDADES_V2 = "id,id_afiliado,codigo_novedad,fecha_novedad,observacion,Valor1,Valor2,Valor3,Valor4,Valor5,Valor6,Valor7,estado,detalle_fallo,fecha_hora_proceso\n";
    public final static String ENCABEZADO_VALIDACION_ARCHIVO_AFILIADOS = "consecutivo,existe_bdua,serial_bdua,tipo_documento_bdua,numero_documento_bdua,primer_apellido_bdua,segundo_apellido_bdua,primer_nombre_bdua,segundo_nombre_bdua,fecha_nacimiento_bdua,genero_bdua,tipo_documento,numero_documento,primer_apellido,segundo_apellido,primer_nombre,segundo_nombre,fecha_nacimiento,genero,fecha_expedicion_cedula,tipo_documento_cf,numero_documento_cf,fecha_afiliacion_sgsss,fecha_afiliacion_eps,tipo_beneficiario,estado_afiliacion,origen_afiliado,parentesco_cotizante,autoriza_envio_sms,autoriza_envio_email,telefono_fijo,telefono_movil,email,afiliacion_ubicaciones,zona,direccion_residencia,nivel_sisben,puntaje_sisben,ficha_sisben,fecha_sisben,primaria_prestador_sedes_id,discapacidad,tipo_discapacidad,condicion_discapacidad,fecha_Inicio_discapacidad,fecha_fin_discapacidad,grupo_poblacional,victima,etnia,estado_civil,subgrupo_sisben,barrio,pais_nacimiento,pais_nacionalidad,genero_identificacion,lugar_nacimiento";
    public final static String ENCABEZADO_VALIDACION_ARCHIVO_NOVEDADES = "Consecutivo,contrato_afiliado,registro_bdua,serial_bdua,tipo_documento,numero_documento,fecha_expedicion_cedula,primer_apellido,segundo_apellido,primer_nombre,segundo_nombre,fecha_nacimiento,genero,estado_civil,origen_afiliado,tipo_beneficiario,parentesco_cotizante,tipo_documento_cf,numero_documento_cf,regimen,fecha_movilidad,categoria_ibc,tipo_cotizante,tipo_documento_aportante,numero_documento_aportante,actividad_economica,arl,afp,ccf,fecha_afiliacion_sgsss,fecha_afiliacion_eps,municipio_afiliacion,municipio_residencia,direccion_residencia,barrio,zona,telefono_fijo,telefono_movil,email,autoriza_envio_sms,autoriza_envio_email,codigo_habilitacion_ips_primaria,grupo_poblacional,victima,etnia,nivel_sisben,puntaje_sisben,ficha_sisben,fecha_sisben,discapacidad,tipo_discapacidad,condicion_discapacidad,fecha_Inicio_discapacidad,fecha_fin_discapacidad,modelo_liquidacion,estado_afiliacion,fecha_novedad,causa_novedad,observacion,subgrupo_sisben";
    public final static String ENCABEZADO_VALIDACION_ARCHIVO_NOVEDADES_V2 = "id_afiliado,codigo_novedad,fecha_novedad,observacion,Valor1,Valor2,Valor3,Valor4,Valor5,Valor6,Valor7";
    
    public final static int CANTIDAD_CAMPOS_ARCHIVO_AFILIADOS = 55;
    public final static int CANTIDAD_CAMPOS_ARCHIVO_NOVEDADES = 59; 
    public final static int CANTIDAD_CAMPOS_ARCHIVO_NOVEDADES_V2 = 10; 
    
    // 5 Opción Certificados
    
    //public final static int TIPO_CERTIFICADO_AFILIACION = 1;
    //public final static int TIPO_CERTIFICADO_PORTABILIDAD = 2;
    //public final static int TIPO_CERTIFICADO_FISCALIA = 3;
    
    // 6 opción Consultar Afiliado
    
    //public final static String GRUPO_SISBEN_D = "D";
    
    //2022-09-15 jyperez adicion variables validación afiliación sin encuesta sisben 4
    //public final static String GRUPO_POBLACIONAL_POBLACION_SISBENIZADA = "5";
    //public final static String METODOLOGIA_GRUPO_POBLACIONAL_SISBEN_3 = "1";

    //2021-05-18 jyperez Se adicionan los tipos de certificados a gestionar
//    public final static int TIPO_CERTIFICADO_AFILIACION = 1;
//    public final static int TIPO_CERTIFICADO_PORTABILIDAD = 2;
//    public final static int TIPO_CERTIFICADO_FISCALIA = 3;
//    public final static int CERTIFICADO_DIAS_VIGENCIA = 30;
    
    // 7 Opción Reportes
    
    //CAMBIAR
    public final static int ESTADO_REPORTE_EN_PROCESO = 1;
    public final static int ESTADO_REPORTE_PROCESADO = 2;
    public final static int ESTADO_REPORTE_RECHAZADO = 3;
    public final static int ESTADO_DETALLE_CARGA_REPORTE_INGRESADO = 3;
    public final static int ESTADO_DETALLE_CARGA_REPORTE_FALLIDO = 4;
    public final static int TIPO_ARCHIVO_REPORTE_AFILIADOS_NUEVOS = 1;
    public final static int TIPO_ARCHIVO_REPORTE_NOVEDADES = 2;
    public final static int TIPO_ARCHIVO_REPORTE_TRASLADOS = 3;
    //no normativos
    public final static int TIPO_ARCHIVO_REPORTE_PORTABILIDAD = 4;
    public final static int TIPO_ARCHIVO_REPORTE_DIGITACION_USUARIOS = 5;
    public final static int TIPO_ARCHIVO_REPORTE_ENCUESTAS_AFILIADOS = 6;
    public final static int TIPO_ARCHIVO_REPORTE_NOVEDADES_ASEGURAMIENTO = 7;
    //2020-09-17 jyperez adición nuevo reporte respaldo - No se imprime
    public final static int TIPO_ARCHIVO_REPORTE_AFILIADOS_RESPALDO = 8;
    
    
    //public final static int IDENTIFICADOR_DEPARTAMENTO_POR_DEFECTO = 2;
    //public final static String IDENTIFICADOR_DIVIPOLA_POR_DEFECTO = "05001";
    //public final static int IDENTIFICADOR_ESTADO_ACTIVO_AFILIACION = 134;
    //public final static String IDENTIFICADOR_REGIMEN_SUBSIDIADO = "1";
    
    //8 opcion validación de derechos
    //CAMBIAR
    public final static int ESTADO_VALIDACION_DERECHOS_EN_PROCESO = 1;
    public final static int ESTADO_VALIDACION_DERECHOS_PROCESADO = 2;
    public final static int ESTADO_VALIDACION_DERECHOS_ABORTADO = 3;
    public final static int ESTADO_VALIDACION_DERECHOS_RECHAZADO = 4;
    
    public final static int CANTIDAD_MAXIMA_REGISTROS = 5000;
    
    //09 Cargas Masivas - Generico Ear
    
    public final static int TIPO_VALIDACION_APLICACION_WEB = 0;
    public final static int TIPO_VALIDACION_CARGA_MASIVA = 1;
    
    //Url
    public static final char ACCION_ADICIONAL_5 = 'S';
    
    //AfiliadosBean
    
    public final static String ESTADO_AFILIACION_ACTIVO = "101";
    public final static String ESTADO_AFILIACION_INACTIVO = "102";
    
    public final static String NIVEL_SISBEN_CATEGORIA_A = "A";
    public final static String NIVEL_SISBEN_CATEGORIA_B = "B";
    public final static String NIVEL_SISBEN_CATEGORIA_C = "C";
    public final static String NIVEL_SISBEN_CATEGORIA_O = "O";
    
    // 10 Carga Masiva - Afiliado Hilo
    
    public static final int CARGA_AFILIADOS = 1;
    public static final int CARGA_NOVEDADES = 2;
    public static final int CARGA_NOVEDADES_V2 = 3;
    public static final String GENERO_MASCULINO = "M";
    public static final String GENERO_FEMENINO = "F";
    public static final String ETNIA_ROM_GITANOS = "2";
    public static final String ETNIA_RAIZAL = "3";
    public static final String ETNIA_PALENQUERO = "4";
    public static final String ETNIA_AFROCOLOMBIANO = "5";
    public static final String ETNIA_OTRAS = "6";
    public static final int ESTADO_CARGA_EN_COLA = 0;
    public static final int ESTADO_CARGA_PROCESANDO = 1;
    public static final int ESTADO_CARGA_PROCESADO = 2;
    public static final int ESTADO_CARGA_ABORTADO = 3;
    public static final String ESTADO_CIVIL_SOLTERO = "1";
    public static final String ESTADO_CIVIL_CASADO = "2";
    public static final String ESTADO_CIVIL_VIUDO = "3";
    public static final String ESTADO_CIVIL_DIVORCIADO = "4";
    public static final String ESTADO_CIVIL_UNION_LIBRE = "5";
    public static final String ESTADO_CIVIL_DIVORCIADO_SEPARADO = "6";
    public static final String ESTADO_CIVIL_NO_REPORTADO = "7";
    public static final String ESTADO_CIVIL_OTRO = "8";
    public final static String TIPO_AFILIADO_COTIZANTE = "101";
    //public final static String TIPO_AFILIADO_BENEFICIARIO = "102";
    public final static String TIPO_AFILIADO_CABEZA_DE_HOGAR = "103";
    //public final static String TIPO_AFILIADO_ADICIONAL = "104";
    public final static String PARENTESCO_CONYUGE = "1";
    public final static String PARENTESCO_HIJOS_DEL_COTIZANTE = "2";
    public final static String PARENTESCO_PADRE_O_MADRE = "3";
    public final static String PARENTESCO_HIJOS_BENEFICIARIO = "4";
    public final static String PARENTESCO_TERCER_GRADO_CONSANGUINIDAD = "5";
    public final static String PARENTESCO_PADRES_DEPENDIENTES = "7";
    public final static String PARENTESCO_AFILIADO_ADICIONAL = "8";
    public final static String PARENTESCO_HIJOS_COTIZANTE_CON_DISCAPACIDAD = "9";
    public final static String CATEGORIA_IBC_CATEGORIA_A = "101";
    public final static String CATEGORIA_IBC_CATEGORIA_B = "102";
    public final static String CATEGORIA_IBC_CATEGORIA_C = "103";
    public final static String CATEGORIA_IBC_NO_APLICA = "NO APLICA";
    public final static String NIVEL_SISBEN_1 = "1";
    public final static String NIVEL_SISBEN_2 = "2";
    public final static String NIVEL_SISBEN_3 = "3";
    public final static String NIVEL_SISBEN_NO_APLICA = "N";
    public final static String TIPO_DISCAPACIDAD_FISICA = "1";
    public final static String TIPO_DISCAPACIDAD_NEURO_SENSORIAL = "2";
    public final static String TIPO_DISCAPACIDAD_MENTAL = "3";
    public final static String CONDICION_DISCAPACIDAD_PERMANENTE = "1";
    //2023-06-07 jyperez nuevo estado afiliación - debe estar asociado a la acción AFILIADO ACTIVO
    public final static String ESTADO_AFILIACION_TRASLADO_ANULADO = "108";
    //2021-11-02 jyperez se definen valores para origen ultimo registro
    public final static int ORIGEN_ULTIMO_REGISTRO_CARGA_MASIVA = 2;

    //2021-07-10 jyperez nuevas variables Novedades Versión 2
    public final static String CODIGO_NOVEDAD_01 = "N01";
    public final static String CODIGO_NOVEDAD_02 = "N02";
    public final static String CODIGO_NOVEDAD_03 = "N03";
    public final static String CODIGO_NOVEDAD_04 = "N04";
    //2021-10-09 jyperez INC XXX nueva novedad similar a N04
    public final static String CODIGO_NOVEDAD_04_86 = "N04-86";
    public final static String CODIGO_NOVEDAD_05 = "N05";
    public final static String CODIGO_NOVEDAD_06 = "N06";
    public final static String CODIGO_NOVEDAD_07 = "N07";
    public final static String CODIGO_NOVEDAD_08 = "N08";
    public final static String CODIGO_NOVEDAD_09 = "N09";
    public final static String CODIGO_NOVEDAD_12 = "N12";
    public final static String CODIGO_NOVEDAD_14 = "N14";
    public final static String CODIGO_NOVEDAD_15 = "N15";
    public final static String CODIGO_NOVEDAD_16 = "N16";
    public final static String CODIGO_NOVEDAD_17 = "N17";
    public final static String CODIGO_NOVEDAD_19 = "N19";
    public final static String CODIGO_NOVEDAD_20 = "N20";
    public final static String CODIGO_NOVEDAD_21 = "N21";
    public final static String CODIGO_NOVEDAD_25 = "N25";
    public final static String CODIGO_NOVEDAD_31 = "N31";
    public final static String CODIGO_NOVEDAD_32 = "N32";
    public final static String CODIGO_NOVEDAD_33 = "N33";
    public final static String CODIGO_NOVEDAD_35 = "N35";
    //Novedades de operacion - No normativas
    public final static String CODIGO_NOVEDAD_IS_03 = "IS-03";
    public final static String CODIGO_NOVEDAD_N00_11 = "N00-11";
    public final static String CODIGO_NOVEDAD_N00_100 = "N00-100";
    public final static String CODIGO_NOVEDAD_N00_157 = "N00-157";
    public final static String CODIGO_NOVEDAD_N31_157 = "N31-157";
    public final static String CODIGO_NOVEDAD_N31_156 = "N31-156";
    public final static String CODIGO_NOVEDAD_IS_70 = "IS-70";
    public final static String CODIGO_NOVEDAD_N19_64 = "N19-64";
    public final static String CODIGO_NOVEDAD_N19_83 = "N19-83";
    public final static String CODIGO_NOVEDAD_N19_56 = "N19-56";
    //2021-09-17 jyperez nuevas novedades
    public final static String CODIGO_NOVEDAD_N00_170 = "N00-170";
    public final static String CODIGO_NOVEDAD_N14_234 = "N14-234";
    //2021-12-30 jyperez nuevas novedades
    public final static String CODIGO_NOVEDAD_N00_98 = "N00-98";
    public final static String CODIGO_NOVEDAD_N00_13 = "N00-13";
    public final static String CODIGO_NOVEDAD_N00 = "N00";
    //2022-06-23 jyperez nuevas novedades
    public final static String CODIGO_NOVEDAD_IS_71 = "IS-71";
    public final static String CODIGO_NOVEDAD_IS_72 = "IS-72";
    public final static String CODIGO_NOVEDAD_IS_09 = "IS-09";
    //2022-08-17 jyperez nuevas novedades
    public final static String CODIGO_NOVEDAD_N37_1 = "N37-1";
    //2023-01-03 jyperez nuevas novedades
    public final static String CODIGO_NOVEDAD_N41 = "N41";
    public final static String CODIGO_NOVEDAD_N43 = "N43";
    //2023-01-19 jyperez nuevas novedades
    public final static String CODIGO_NOVEDAD_N45 = "N45";
    //2023-01-19 jyperez Res 762 nuevas novedades
    public final static String CODIGO_NOVEDAD_N40 = "N40";
    public final static String CODIGO_NOVEDAD_N42 = "N42";
    public final static String CODIGO_NOVEDAD_N44 = "N44";
    //public final static String CODIGO_NOVEDAD_N46 = "N46";
    //public final static String CODIGO_NOVEDAD_N47 = "N47";
    public final static String CODIGO_NOVEDAD_N48 = "N48";
    //2023-06-29 jyperez 
    public final static String CODIGO_NOVEDAD_N46_1 = "N46-1";
    public final static String CODIGO_NOVEDAD_N46_2 = "N46-2";
    public final static String CODIGO_NOVEDAD_N46_3 = "N46-3";
    public final static String CODIGO_NOVEDAD_N46_4 = "N46-4";
    //2023-07-25 jyperez
    public final static String CODIGO_NOVEDAD_N00_99 = "N00-99";
    //2023-08-10 jyperez nueva novedad
    public final static String CODIGO_NOVEDAD_IS_73 = "IS-73";
    //2024-01-10 jyperez nueva novedad
    public final static String CODIGO_NOVEDAD_IS_58 = "IS-58";
    //nuevos campos maestros condicion discapacidad
    public final static String CONDICION_DISCAPACIDAD_FISICA_TEMPORAL = "D1";
    public final static String CONDICION_DISCAPACIDAD_NEURO_SENSORIAL_TEMPORAL = "D2";
    public final static String CONDICION_DISCAPACIDAD_MENTAL_TEMPORAL = "D3";
    public final static String CONDICION_DISCAPACIDAD_FISICA_PERMANENTE = "D4";
    public final static String CONDICION_DISCAPACIDAD_NEURO_SENSORIAL_PERMANENTE = "D5";
    public final static String CONDICION_DISCAPACIDAD_MENTAL_PERMANENTE = "D6";
    //2022-06-06 jyperez nuevos parámetros contactos
    //public final static String TIPO_CONTACTO_TELEFONO = "1";
    //public final static String TIPO_CONTACTO_CELULAR = "2";
    //public final static String TIPO_CONTACTO_CORREO_ELECTRONICO = "3";
    
    //CAMBIAR OK
    public final static int CANTIDAD_CAMPOS_ARCHIVO_AFILIADOS_CM = 56; 
    public final static int CANTIDAD_CAMPOS_ARCHIVO_NOVEDADES_CM = 60; 
    public final static int CANTIDAD_CAMPOS_ARCHIVO_NOVEDADES_V2_CM = 11;
    
    //2024-01-25 jyperez configuración causa novedad retiro en desmarcar duplicado
    public final static String CAUSA_NOVEDAD_RETIRO_REALIZADO_POR_AUDITOR = "N14-240";
    
    //2024-03-27 jyperez se crea parametrización para manejo de direcciones de afiliado
    public final static int TIPO_DIRECCION_PRINCIPAL = 1;
    public final static int TIPO_DIRECCION_ALTERNA_CONTACTO = 2;
    
    //2024-10-29 jyperez configuración variable local para imprimir meses en texto español.
    public static final Locale LOCALE_CO = new Locale("es", "CO");
    
    //2024-12-05 jyperez nuevos parámetros formulario de afiliación
    public final static String TIPO_TRAMITE_AFILIACION = "1";
    public final static String TIPO_TRAMITE_REPORTE_NOVEDADES = "2";
    public final static String TIPO_AFILIACION_INDIVIDUAL_COTIZANTE = "1";
    public final static String TIPO_AFILIACION_INDIVIDUAL_BENEFICIARIO = "2";
    public final static String TIPO_AFILIACION_COLECTIVA = "3";
    public final static String TIPO_AFILIACION_INSTITUCIONAL = "4";
    public final static String TIPO_AFILIACION_DE_OFICIO= "5";
    public final static String TIPO_COTIZANTE_DEPENDIENTE= "A";
    public final static String TIPO_COTIZANTE_INDEPENDIENTE= "B";
    public final static String TIPO_COTIZANTE_PENSIONADO= "C";
    
    //2025-08-04 jyperez georrefenciación
    public static final String ICONO_RUTA = "https://www.saviasaludeps.com/sitioweb/images/mapa/";
    public static final String ICONO_01 = "afiliado.png";
    public static final String ICONO_02 = "prestador_sede.png";

    public static final double CENTRO_LATITUD_INICIAL = 6.200034400;
    public static final double CENTRO_LONGITUD_INICIAL = -75.575125400;
    public static final int PROFUNDIDAD_INICIAL = 20;
    
    public static final String RUTA_GOOGLE_MAPS_KEY = "https://maps.google.com/maps/api/js?key=AIzaSyAPkA-W1H52mE2yeu9oyltsvxpbOS-U5XM&amp;sensor=false";
    
    public AfiliadoParametro() {
    }
}
