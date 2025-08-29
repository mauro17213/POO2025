/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.utilidades;

/**
 *
 * @author jyperez
 */
public class MaestroParametro {
    
    //1. Tecnologias
    public final static int TIPO_TECNOLOGIA_TECNOLOGIA = 1;
    //2. Medicamentos
    public final static int TIPO_TECNOLOGIA_MEDICAMENTO = 2;
    //3. Insumos
    public final static int TIPO_TECNOLOGIA_INSUMO = 3;
    //4. Paquetes
    public final static int TIPO_TECNOLOGIA_PAQUETE = 4;
    //public final static int TIPO_TECNOLOGIA_TECNOLOGIA = 1;
    //5. Especialidades
    //6. Diagnosticos
    //7. Servicios Habilitacion
    //8. Agrupadores Medicamentos
    //9. Carga Masiva

    public final static int ESTADO_EN_COLA = 0;
    public final static int ESTADO_PROCESANDO = 1;
    public final static int ESTADO_PROCESADO = 2;
    public final static int ESTADO_ABORTADO = 3;
    
    public final static int ESTADO_DETALLE_CARGA_INGRESADO = 3;
    public final static int ESTADO_DETALLE_CARGA_FALLIDO = 4;
    
    //
    public final static int MATECNOLOGIAS = 1;
    public final static int MAMEDICAMENTOS = 2;
    public final static int MAINSUMOS = 3;
    public final static int MAPAQUETES = 4;
    public final static int MAESPECIALIDADES = 5;
    public final static int MADIAGNOSTICOS = 6;
    public final static int MASERVICIOSHABILITACION = 7;
    public final static int MARELACIONINSUMOSMIPRES = 8;

    
    public final static int IDENTIFICADOR_DEPARTAMENTO_POR_DEFECTO = 2;
    public final static String IDENTIFICADOR_DIVIPOLA_POR_DEFECTO = "05001";
    public final static int IDENTIFICADOR_ESTADO_ACTIVO_AFILIACION = 134;
    public final static String IDENTIFICADOR_REGIMEN_SUBSIDIADO = "1";
    
    public final static String ENCABEZADO_VALIDACION_ARCHIVO_MEDICAMENTOS = "Consecutivo,actualizar,ma_agrupadores_medicamento_codigo,cum,descripcion_invima,descripcion_estandarizada,descripcion_larga_estandarizada,mae_cobertura_codigo,mae_concentracion_codigo,mae_principio_activo_codigo,mae_forma_farmaceutica_codigo,mae_tipo_ppm_codigo,es_alto_costo,aplica_subsidiado,aplica_contributivo,sexo_aplica,codigo_ium,edad_desde,edad_hasta,es_regulado,valor_maximo_regulado,valor_referente_minimo,valor_referete,expediente,nombre_comercial,laboratorio,registro_sanitario,fecha_expedicion,fecha_vencimiento,mae_estado_registro_sanitario_codigo,cantidad_cum,fecha_activo,fecha_inactivo,mae_atc_1_codigo,mae_atc_2_codigo,mae_atc_3_codigo,norma_regulacion,mce,monopolio_estado,estrecho_margen_terapeutico,aclaracion,valor_fraccion,valor_presentacion_comercial,cantidad,dias_tratamiento,mae_grupo_anatomico_ppal_codigo,mae_grupo_terapeutico_ppal_codigo,dci,descripcion_dci,proveniente_invima,agrupador_condicion_pbs,valor_comercial,lista_unirs,codigo_suficiencia_upc,cantidad_concentracion_suf_upc,unidad_concentracion_suf_upc,unidad_medida_suficiencia,no_pbs_menor_valor,muestra_medica,activo,forma_farmaceutica";
    public final static String ENCABEZADO_VALIDACION_ARCHIVO_INSUMOS = "consecutivo,actualizar,mae_tipo_codigo,automatico,codigo_habilitacion,codigo,descripcion,abreviatura,activo,cobertura";
    public final static String ENCABEZADO_VALIDACION_ARCHIVO_TECNOLOGIAS = "consecutivo,actualizar,mae_grupo_tecnologia,cups,cups_descipcion,codigo_propio,propio_descripcion,aplica_subsidiado,aplica_contributivo,sexo_aplica,mae_cobertura_codigo,cobertura,ma_iss2001_tarifarios_codigo,ma_iss2000_tarifarios_codigo,ma_soat_tarifarios_codigo,nivel_complejidad,edad_desde,unidad_desde,edad_hasta,unidad_hasta,codigo_financiador,tipo_frecuencia,frecuencia,tipo_frecuencia_2,frecuencia_2,evento_unico,activo,tipo_pago,aclaracion,condicion,ma_servicio_habilitacion,vigencia_autorizacion";
    public final static String ENCABEZADO_VALIDACION_ARCHIVO_PAQUETES = "consecutivo,actualizar,codigo,nombre,activo,incluye,excluye,observacion,mae_ambito_codigo,ma_tecnologias_codigo_propio,es_ato_costo,requisitos_tecnicos";
    public final static String ENCABEZADO_VALIDACION_ARCHIVO_ESPECIALIDADES = "consecutivo,actualizar,codigo,nombre,descripcion";
    public final static String ENCABEZADO_VALIDACION_ARCHIVO_DIAGNOSTICOS = "consecutivo,actualizar,mae_diagnostico_capitulo_codigo,mae_diagnostico_categoria_codigo,codigo,nombre,activo,sexo_aplica,edad_inferior,edad_superior,grupo_mortalidad,grupo_mortalidad_lista,valor_limite_inferior,valor_limite_superior,excento_cobro,alto_costo,priorizar_crue,denominacion_cac";
    public final static String ENCABEZADO_VALIDACION_ARCHIVO_SERV_HABILITACION = "consecutivo,actualizar,codigo,nombre,activo,grupo";
    public final static String ENCABEZADO_VALIDACION_ARCHIVO_RE_INSUMOS_MIPRES = "consecutivo,actualizar,tipo_tecnologia,codigo_tecnologia,codigo_mipres,borrado";
    //cantidad campos
    public final static int CANTIDAD_CAMPOS_ARCHIVO_MEDICAMENTOS = 60;
    public final static int CANTIDAD_CAMPOS_ARCHIVO_INSUMOS = 9;
    public final static int CANTIDAD_CAMPOS_ARCHIVO_TECNOLOGIAS = 31;
    public final static int CANTIDAD_CAMPOS_ARCHIVO_PAQUETES = 11;
    public final static int CANTIDAD_CAMPOS_ARCHIVO_ESPECIALIDADES = 4;
    public final static int CANTIDAD_CAMPOS_ARCHIVO_DIAGNOSTICOS = 17;
    public final static int CANTIDAD_CAMPOS_ARCHIVO_SERV_HABILITACION = 5;
    public final static int CANTIDAD_CAMPOS_ARCHIVO_RE_INSUMOS_MIPRES = 5;
    //IMPRESION
    public final static String ENCABEZADO_IMPRESION_ARCHIVO_MEDICAMENTOS ="id,consecutivo,actualizar,ma_agrupadores_medicamento_codigo,cum,descripcion_invima,descripcion_estandarizada,descripcion_larga_estandarizada,mae_cobertura_codigo,mae_concentracion_codigo,mae_principio_activo_codigo,mae_forma_farmaceutica_codigo,mae_tipo_ppm_codigo,es_alto_costo,aplica_subsidiado,aplica_contributivo,sexo_aplica,codigo_ium,edad_desde,edad_hasta,es_regulado,valor_maximo_regulado,valor_referente_minimo,valor_referete,expediente,nombre_comercial,laboratorio,registro_sanitario,fecha_expedicion,fecha_vencimiento,mae_estado_registro_sanitario_codigo,cantidad_cum,fecha_activo,fecha_inactivo,mae_atc_1_codigo,mae_atc_2_codigo,mae_atc_3_codigo,norma_regulacion,mce,monopolio_estado,estrecho_margen_terapeutico,aclaracion,valor_fraccion,valor_presentacion_comercial,cantidad,dias_tratamiento,mae_grupo_anatomico_ppal_codigo,mae_grupo_terapeutico_ppal_codigo,dci,descripcion_dci,proveniente_invima,agrupador_condicion_pbs,valor_comercial,lista_unirs,codigo_suficiencia_upc,cantidad_concentracion_suf_upc,unidad_concentracion_suf_upc,unidad_medida_suficiencia,no_pbs_menor_valor,muestra_medica,activo,forma_farmaceutica,estado,detalle_fallo,fecha_hora_proceso\n";
    public final static String ENCABEZADO_IMPRESION_ARCHIVO_INSUMOS ="id,consecutivo,actualizar,mae_tipo_codigo,automatico,codigo_habilitacion,codigo,descripcion,abreviatura,activo,cobertura,estado,detalle_fallo,fecha_hora_proceso\n";
    public final static String ENCABEZADO_IMPRESION_ARCHIVO_TECNOLOGIAS ="id,consecutivo,actualizar,mae_grupo_tecnologia,cups,cups_descipcion,codigo_propio,propio_descripcion,aplica_subsidiado,aplica_contributivo,sexo_aplica,mae_cobertura_codigo,cobertura,ma_iss2001_tarifarios_codigo,ma_iss2000_tarifarios_codigo,ma_soat_tarifarios_codigo,nivel_complejidad,edad_desde,unidad_desde,edad_hasta,unidad_hasta,codigo_financiador,tipo_frecuencia,frecuencia,tipo_frecuencia_2,frecuencia_2,evento_unico,activo,tipo_pago,aclaracion,condicion,ma_servicio_habilitacion,vigencia_autorizacion,estado,detalle_fallo,fecha_hora_proceso\n";
    public final static String ENCABEZADO_IMPRESION_ARCHIVO_PAQUETES ="id,consecutivo,actualizar,codigo,nombre,activo,incluye,excluye,observacion,mae_ambito_codigo,ma_tecnologias_codigo_propio,es_ato_costo,requisitos_tecnicos,estado,detalle_fallo,fecha_hora_proceso\n";
    public final static String ENCABEZADO_IMPRESION_ARCHIVO_ESPECIALIDADES ="id,consecutivo,actualizar,codigo,nombre,descripcion,estado,detalle_fallo,fecha_hora_proceso\n";
    public final static String ENCABEZADO_IMPRESION_ARCHIVO_DIAGNOSTICOS ="id,consecutivo,actualizar,mae_diagnostico_capitulo_codigo,mae_diagnostico_categoria_codigo,codigo,nombre,activo,sexo_aplica,edad_inferior,edad_superior,grupo_mortalidad,grupo_mortalidad_lista,valor_limite_inferior,valor_limite_superior,excento_cobro,alto_costo,priorizar_crue,denominacion_cac,estado,detalle_fallo,fecha_hora_proceso\n";
    public final static String ENCABEZADO_IMPRESION_ARCHIVO_SERV_HABILITACION ="id,consecutivo,actualizar,codigo,nombre,activo,grupo,estado,detalle_fallo,fecha_hora_proceso\n";
    public final static String ENCABEZADO_IMPRESION_ARCHIVO_RE_INSUMOS_MIPRES = "id,consecutivo,actualizar,tipo_tecnologia,codigo_tecnologia,codigo_mipres,borrado,estado,detalle_fallo,fecha_hora_proceso\n";
    // 10. Carga Masiva Generico Ear
    
    //constantes
    public final static int SEXO_APLICA_MASCULINO = 0;
    public final static int SEXO_APLICA_FEMENINO = 1;
    public final static int SEXO_APLICA_AMBOS = 2;
    //tipo del maestros
    public final static int TIPO_MAESTRO_TECNOLOGIAS = 1;
    public final static int TIPO_MAESTRO_MEDICAMENTOS = 2;
    public final static int TIPO_MAESTRO_INSUMOS = 3;
    public final static int TIPO_MAESTRO_PAQUETES = 4;
    public final static int TIPO_MAESTRO_ESPECIALIDADES = 5;
    public final static int TIPO_MAESTRO_DIAGNOSTICOS = 6;
    public final static int TIPO_MAESTRO_SERVICIOS_HABILITACION = 7;
    public final static int TIPO_MAESTRO_RELACION_INSUMO_MIPRES = 8;

//    public final static int TIPO_TECNOLOGIA_TECNOLOGIA = 1;
//    public final static int TIPO_TECNOLOGIA_MEDICAMENTO = 2;
//    public final static int TIPO_TECNOLOGIA_INSUMO = 3;
//    public final static int TIPO_TECNOLOGIA_PAQUETE = 4;
    public final static int TIPO_MANUAL_TARIFARIO_PROPIA = 0;
    public final static int TIPO_MANUAL_TARIFARIO_SOAT = 1;
    public final static int TIPO_MANUAL_TARIFARIO_ISS2000 = 2;
    public final static int TIPO_MANUAL_TARIFARIO_ISS2001 = 3;
    public final static int TIPO_COMPLEJIDAD_ALTA = 1;
    public final static int TIPO_COMPLEJIDAD_MEDIANA = 2;
    public final static int TIPO_COMPLEJIDAD_BAJA = 3;
    public static final String REGIMEN_SUBSIDIADO = "1";
    public static final String MODELO_LIQUIDACION_EVENTO = "1";
    public static final String EXITOSO_STR = "Exitoso";
    public static final String FALLIDO_STR = "Fallido";
    public static final int ESTADO_CARGA_PROCESANDO = 1;
    public static final int ESTADO_CARGA_PROCESADO = 2;
    public static final int ESTADO_CARGA_ABORTADO = 3;
//    public static final int ESTADO_DETALLE_CARGA_INGRESADO = 3;
//    public static final int ESTADO_DETALLE_CARGA_FALLIDO = 4;
    
    public static final int CANTIDAD_CAMPOS_MEDICAMENTOS = 60;
    public static final int CANTIDAD_CAMPOS_INSUMOS = 10;
    public static final int CANTIDAD_CAMPOS_TECNOLOGIAS = 32;
    public static final int CANTIDAD_CAMPOS_PAQUETES = 12;
    public final static int CANTIDAD_CAMPOS_ESPECIALIDADES = 5;
    public final static int CANTIDAD_CAMPOS_DIAGNOSTICOS = 17;
    public final static int CANTIDAD_CAMPOS_SERVICIOS_HABILITACION = 6;
    public final static int CANTIDAD_CAMPOS_RE_INSUMOS_MIPRES = 6;
    
    public static final int NIVEL_COMPLEJIDAD_UNO = 1;
    public static final int NIVEL_COMPLEJIDAD_DOS = 2;
    public static final int NIVEL_COMPLEJIDAD_TRES = 3;
    public static final int NIVEL_COMPLEJIDAD_CUATRO = 4;
    
    public static final int UNIDAD_ANIO = 1;
    public static final int UNIDAD_MES = 2;
    public static final int UNIDAD_DIA = 3;
    
    public static final int TIPO_FRECUENCIA_ANIO = 1;
    public static final int TIPO_FRECUENCIA_MES = 2;
    public static final int TIPO_FRECUENCIA_DIA = 3;
    
    public static final String TIPO_PAGO_NO_APLICA_COPAGO_Y_MODERADORA = "0000";
    public static final String TIPO_PAGO_APLICA_COPAGO_SUBSIDIADO = "0100";
    public static final String TIPO_PAGO_APLICA_COPAGO_SUBS_MODERADORA_CONT = "0110";
    public static final String TIPO_PAGO_APLICA_COPAGO_SUBSIDIADO_Y_CONTRIBUTIVO = "0101";
    public static final String TIPO_PAGO_APLICA_MODERADORA_CONTRIBUTIVO = "0010";
    public static final String TIPO_PAGO_APLICA_COPAGO_CONTRIBUTIVO = "0001";

    public MaestroParametro() {
    }
}
