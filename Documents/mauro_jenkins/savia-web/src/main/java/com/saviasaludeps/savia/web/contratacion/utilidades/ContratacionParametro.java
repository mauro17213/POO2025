/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.utilidades;

/**
 *
 * @author jyperez
 */
public class ContratacionParametro {
    
    //1. Contratos
    public final static String MODALIDAD_CONTRATO_CAPITA = "01";
    public final static int TIPO_TECNOLOGIA_TECNOLOGIA = 1;
    public final static int TIPO_TECNOLOGIA_MEDICAMENTO = 2;
    public final static int TIPO_TECNOLOGIA_INSUMO = 3;
    public final static int TIPO_TECNOLOGIA_PAQUETE = 4;
    public final static int TIPO_MANUAL_TARIFARIO_PROPIA = 0;
    public final static int TIPO_MANUAL_TARIFARIO_SOAT = 1;
    public final static int TIPO_MANUAL_TARIFARIO_ISS2000 = 2;
    public final static int TIPO_MANUAL_TARIFARIO_ISS2001 = 3;
    public final static String ESTADO_CONTRATO_VIGENTE = "01";
    public final static String ESTADO_CONTRATO_NO_VIGENTE = "02";
    
    //2. Prestadores - Sedes
    public final static String TIPO_CONTACTO_TELEFONO = "2";
    public final static String TIPO_CONTACTO_CELULAR = "1";
    public final static String TIPO_CONTACTO_CORREO_ELECTRONICO = "3";
    //public final static String TIPO_CONTACTO_FAX = "4";
    public final static String TIPO_CONTACTO_PBX = "4";
    //2023-07-04 jyperez código unión temporal
    public final static String TIPO_PRESTADOR_UNION_TEMPORAL = "9";
    //3. Marcaciones
//    
//    public final static int TIPO_TECNOLOGIA_TECNOLOGIA = 1;
//    public final static int TIPO_TECNOLOGIA_MEDICAMENTO = 2;
//    public final static int TIPO_TECNOLOGIA_INSUMO = 3;
//    public final static int TIPO_TECNOLOGIA_PAQUETE = 4;

    //4. Profesionales
    public static final int ID_EMPRESA_SAVIA = 1;
    public static final String DIALOGO_CREAR = "Crear";
    public static final String DIALOGO_EDITAR = "Editar";
    public static final int ID_TIPO_DOCUMENTO_PE = 9741;
    public static final int ID_TIPO_DOCUMENTO_CE = 9737;
    public static final int ID_TIPO_DOCUMENTO_CC = 9736;
    
    //5. Consultar Tecnologia
    
//    public final static int ESTADO_EN_COLA = 0;
//    public final static int ESTADO_PROCESANDO = 1;
//    public final static int ESTADO_PROCESADO = 2;
//    public final static int ESTADO_ABORTADO = 3;
//    public final static int TIPO_TECNOLOGIA_TECNOLOGIA = 1;
//    public final static int TIPO_TECNOLOGIA_MEDICAMENTO = 2;
//    public final static int TIPO_TECNOLOGIA_INSUMO = 3;
//    public final static int TIPO_TECNOLOGIA_PAQUETE = 4;
    
    //6. Carga Masiva
    public final static int ESTADO_INGRESADO = -1;
    public final static int ESTADO_EN_COLA = 0;
    public final static int ESTADO_PROCESANDO = 1;
    public final static int ESTADO_PROCESADO = 2;
    public final static int ESTADO_ABORTADO = 3;
    
    //2022-10-05 jyperez se elimina el campo automatico del archivo de contrato detalle
    public final static String ENCABEZADO_VALIDACION_ARCHIVO_CONTRATO_DETALLE = "consecutivo,actualizar,numero_contrato,codigo_habilitacion_sede,modalidad_contrato,fecha_hora_inicio,fecha_hora_fin,activo,tipo_tecnologia,codigo_tecnologia,codigo_servicio_habilitacion,tipo_manual_tarifario,manual_tarifario_codigo,ma_manual_tarifario_agno,tipo_manual_tarifario_nuevo,manual_tarifario_codigo_nuevo,ma_manual_tarifario_agno_nuevo,valor_manual,porcentaje_variacion,complejidad,observacion_incluye,observacion_excluye,interdependencia,codigo_habilitacion_sedes,codigo_ambito,codigo_ambito_nuevo";
    public final static String ENCABEZADO_VALIDACION_ARCHIVO_NOTA_TECNICA = "consecutivo,actualizar,id_nota_tecnica,contrato,tipo_tecnologia,codigo_tecnologia,costo_promedio,frecuencia_uso,tipo_frecuencia,poblacion,ambito,observacion,fecha_inicio,fecha_fin";
    public final static String ENCABEZADO_VALIDACION_ARCHIVO_COBERTURA = "consecutivo,contrato,modalidad,codigo_habilitacion_sede,coberturas";
    public final static String ENCABEZADO_VALIDACION_ARCHIVO_MARCACION = "consecutivo,contrato,codigo_habilitacion_sede,modalidad_contrato,tipo_tecnologia,codigo_tecnologia,codigo_servicio_habilitacion,tipo_manual_tarifario,codigo_manual_tarifario,agno_manual_tarifario,codigo_ambito,automatico_manual,automatico_masivo,automatico_interoperabilidad,preautorizacion";
    //2024-02-19 jyperez carga masiva servicios reps
    public final static String ENCABEZADO_VALIDACION_ARCHIVO_REPS_PRESTADORES = "consecutivo,actualizar,codigo_habilitacion_sede,ma_servicios_habilitacion_codigo,activo,aplica_urgencia,aplica_domiciliaria,aplica_cirugia,aplica_hospitalizacion,aplica_laboratorio,aplica_imagenes,aplica_odontologia,aplica_transporte,aplica_trasplante,aplica_consulta,aplica_alternativa,aplica_oncologia,aplica_terapia,aplica_proceso,aplica_pedt,aplica_ambulatorio,aplica_hospitalario,aplica_unidad_movil,aplica_otras_extramural,aplica_centro_referencia,aplica_institucion_remisora,aplica_complejidad_baja,aplica_complejidad_media,aplica_complejidad_alta,aplica_intramural,aplica_jornada_salud,aplica_telemedicina,aplica_ref_telemed_interactiva,aplica_ref_telemed_no_interactiva,aplica_referencia_tele_experticia,aplica_referencia_tele_monitoreo,aplicap_remisor_tele_experticia,aplica_remisor_tele_monitoreo,aplica_sin_complejidad,aplica_trasplante_osteomuscular,aplica_trasplante_piel,aplica_trasplante_cardiovascular,aplica_trasplante_tejido_ocular,aplica_atencion_paciente_quemado,aplica_salud_mental,aplica_spa,aplica_otras_patologias,aplica_tx_cel_proge_hematopoy,aplica_proc_quirur_ambulatorios,aplica_organo_rinon,aplica_organo_higado,aplica_organo_pancreas,aplica_organo_intestino,aplica_organo_multivisceral,aplica_organo_corazon,aplica_organo_pulmon,aplica_sustancias_psicoactivas,aplica_trasplante_renal,fecha_apertura";
    //cantidad de campos
    //2022-10-05 jyperez se disminuye en 1 la cantidad de campos en contrato detalle
    public final static int CANTIDAD_CAMPOS_ARCHIVO_CONTRATO_DETALLE = 25;
    public final static int CANTIDAD_CAMPOS_NOTA_TECNICA = 13;
    public final static int CANTIDAD_CAMPOS_COBERTURA = 4;
    public final static int CANTIDAD_CAMPOS_MARCACION = 14;
    //2024-02-19 jyperez carga masiva servicios reps
    public final static int CANTIDAD_CAMPOS_REPS_PRESTADORES = 58;
    
    //2022-10-05 jyperez se elimina el campo automatico del archivo de contrato detalle
    public final static String ENCABEZADO_IMPRESION_CONTRATO_DETALLE = "id,consecutivo,actualizar,numero_contrato,codigo_habilitacion_sede,modalidad_contrato,fecha_hora_inicio,fecha_hora_fin,activo,tipo_tecnologia,codigo_tecnologia,codigo_servicio_habilitacion,tipo_manual_tarifario,manual_tarifario_codigo,ma_manual_tarifario_agno,tipo_manual_tarifario_nuevo,manual_tarifario_codigo_nuevo,ma_manual_tarifario_agno_nuevo,valor_manual,porcentaje_variacion,complejidad,observacion_incluye,observacion_excluye,interdependencia,codigo_habilitacion_sedes,codigo_ambito,codigo_ambito_nuevo,estado,detalle_fallo,fecha_hora_proceso\n";
    public final static String ENCABEZADO_IMPRESION_NOTA_TECNICA ="id,consecutivo,actualizar,id_nota_tecnica,contrato,tipo_tecnologia,codigo_tecnologia,costo_promedio,frecuencia_uso,tipo_frecuencia,poblacion,ambito,observacion,fecha_inicio,fecha_fin,estado,detalle_fallo,fecha_hora_proceso\n";
    public final static String ENCABEZADO_IMPRESION_COBERTURA ="id,consecutivo,contrato,modalidad,codigo_habilitacion_sede,coberturas,estado,detalle_fallo,fecha_hora_proceso\n";
    public final static String ENCABEZADO_IMPRESION_MARCACION ="id,consecutivo,contrato,codigo_habilitacion_sede,modalidad_contrato,tipo_tecnologia,codigo_tecnologia,codigo_servicio_habilitacion,tipo_manual_tarifario,codigo_manual_tarifario,agno_manual_tarifario,codigo_ambito,automatico_manual,automatico_masivo,automatico_interoperabilidad,preautorizacion,estado,detalle_fallo,fecha_hora_proceso\n";
    //2024-02-19 jyperez carga masiva servicios reps
    public final static String ENCABEZADO_IMPRESION_ARCHIVO_REPS_PRESTADORES = "id,consecutivo,actualizar,codigo_habilitacion_sede,ma_servicios_habilitacion_codigo,activo,aplica_urgencia,aplica_domiciliaria,aplica_cirugia,aplica_hospitalizacion,aplica_laboratorio,aplica_imagenes,aplica_odontologia,aplica_transporte,aplica_trasplante,aplica_consulta,aplica_alternativa,aplica_oncologia,aplica_terapia,aplica_proceso,aplica_pedt,aplica_ambulatorio,aplica_hospitalario,aplica_unidad_movil,aplica_otras_extramural,aplica_centro_referencia,aplica_institucion_remisora,aplica_complejidad_baja,aplica_complejidad_media,aplica_complejidad_alta,aplica_intramural,aplica_jornada_salud,aplica_telemedicina,aplica_ref_telemed_interactiva,aplica_ref_telemed_no_interactiva,aplica_referencia_tele_experticia,aplica_referencia_tele_monitoreo,aplicap_remisor_tele_experticia,aplica_remisor_tele_monitoreo,aplica_sin_complejidad,aplica_trasplante_osteomuscular,aplica_trasplante_piel,aplica_trasplante_cardiovascular,aplica_trasplante_tejido_ocular,aplica_atencion_paciente_quemado,aplica_salud_mental,aplica_spa,aplica_otras_patologias,aplica_tx_cel_proge_hematopoy,aplica_proc_quirur_ambulatorios,aplica_organo_rinon,aplica_organo_higado,aplica_organo_pancreas,aplica_organo_intestino,aplica_organo_multivisceral,aplica_organo_corazon,aplica_organo_pulmon,aplica_sustancias_psicoactivas,aplica_trasplante_renal,fecha_apertura,estado,detalle_fallo,fecha_hora_proceso\n";
    
    public ContratacionParametro() {
    }
}
