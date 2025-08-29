/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.administracion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author ripalacios
 */
public class MaestroAccion extends Auditoria {

    public static final int GN_SEXO_APLICA_MUJER = 1;
    public static final int GN_SEXO_APLICA_HOMBRE = 2;
    public static final int GN_AMBITO_APLICA_MENSAJE = 37;
    
    public static final int ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO = 3;
    public static final int ASEG_ESTADO_AFILIACION_AFILIADO_INACTIVO = 4;
    public static final int ASEG_PORTABILIDAD_ESTADO_PORTABILIDAD_VIGENTE = 5;
    public static final int ASEG_PORTABILIDAD_ESTADO_PORTABILIDAD_NO_VIGENTE = 6;

    public static final int AUS_SERVICIO_ESTADO_CREADO = 7;
    public static final int AUS_SERVICIO_ESTADO_GESTION = 8;
    public static final int AUS_SERVICIO_ESTADO_CERRADO = 9;    
        
    public static final int AU_TIPO_DIAGNOSTICO = 11;
    public static final int AU_MOTIVO_RECHAZO_ITEM_AUTOMATICO = 13;
    public static final int AU_TIPO_AUDITOR_REASIGNA_GRUPO_TECNOLOGIA = 48;
    public static final int AU_SEGUIMIENTO_SERVICIO_DETALLE_TALLA_MASCARA = 49;
    public static final int AU_SEGUIMIENTO_SERVICIO_DETALLE_TIPO_MASCARA = 50;
    public static final int AU_SEGUIMIENTO_ESTADO_DEVOLUCION_ITEM = 51;
    public static final int AU_SEGUIMIENTO_ESTADO_VALIDA_CANCELA = 52;
    public static final int AU_A3_ESTADO_MOTIVO_NEGAR_SERVICIO = 53;
    
    public static final int PE_CAUSA_ESTADO_ACTIVO_AUTOMATICO = 12;
    public static final int PE_CAUSA_ESTADO_INACTIVO_AUTOMATICO = 14;
    public static final int PE_GESTION_TIPO_DIRECCIONADO = 36;
    
    public static final int REF_CLASE_TRANSPORTE_CON_PROVEEDOR = 10;
    
    //ACCIONES PARA TUTELAS DEPENDIENDO EL ESTADO
    public static final int TU_TUTELA_ESTADO_HABILITAR_FALLO = 15;
    public static final int TU_TUTELA_ESTADO_HABILITAR_SANCION_COMFIRMA = 16;
    public static final int TU_TUTELA_ESTADO_HABILITAR_TUTELA_NUEVA = 17;
    public static final int TU_TUTELA_ESTADO_HABILITAR_SANSION = 18;
    public static final int TU_TUTELA_ESTADO_HABILITAR_FALLO_EN_CONTRA = 19;
    public static final int TU_TUTELA_ESTADO_HABILITAR_CLASE_SANCION_MULTA = 20;
    public static final int TU_TUTELA_ESTADO_HABILITAR_CLASE_SANCION_ARRESTO = 21;
    public static final int TU_TUTELA_ESTADO_HABILITAR_CLASE_SANCION_MULTA_Y_ARRESTO = 22;
    public static final int TU_TUTELA_ESTADO_HABILITAR_FALLO_SEGUNDA_INSTANCIA = 23;
    public static final int TU_TUTELA_ESTADO_HABILITAR_TERMINA_NULIDAD = 24;
    public static final int TU_TUTELA_ESTADO_HABILITAR_REQUERIMIENTO_MP = 25;
    public static final int TU_TUTELA_ESTADO_HABILITAR_DESACATO_MP = 26;
    public static final int TU_TUTELA_ESTADO_HABILITAR_SANCION_MP = 27;
    public static final int TU_TUTELA_ESTADO_HABILITAR_INAPLICA_SANCION_MP = 28;
    public static final int TU_TUTELA_ESTADO_HABILITAR_TERMINA_DESACATO_MP = 29;
    public static final int TU_TUTELA_ESTADO_HABILITAR_DESISTIMIENTO = 30;
    //ACCIONES DE TIPO SEGUMIENTO DE TUTELAS
    public static final int TU_TUTELA_TIPO_SEGUMIENTO_CUMPLIMIENTO = 31;
    public static final int TU_TUTELA_TIPO_SEGUMIENTO_ARCHIVADO = 32;
    public static final int TU_TUTELA_TIPO_SEGUMIENTO_CIERRE_PARCIAL = 33;
    public static final int TU_TUTELA_TIPO_SEGUMIENTO_GESTION_GENERAL_E_JUZGADO_E_REPARTO = 34;
    public static final int TU_TUTELA_TIPO_SEGUMIENTO_INACTIVO = 35;
    //ACCIONES PARA GESTIÓN DE TUTELAS
    public static final int TU_TUTELA_ESTADO_ITEM_LISTAR = 86;
    public static final int TU_TUTELA_ESTADO_ITEM_NO_LISTAR = 87;
    //ACCIONES DE CASOS SERVICIOS 
    public static final int AUS_CASOS_SERVICIOS_ESTADO_ASIGNADO = 38;
    public static final int AUS_CASOS_SERVICIOS_ESTADO_ESTUDIO = 39;
    public static final int AUS_CASOS_SERVICIOS_AMBITO_PRESTACION_SERVICIO = 40;
    public static final int AUS_CASOS_SERVICIOS_AMBITO_ADMINISTRATIVO = 41;
    public static final int AUS_CASOS_SERVICIOS_TIPO_ADMINISTRATIVO_IPS = 42;
    public static final int AUS_CASOS_SERVICIOS_TIPO_ADMINISTRATIVO_EPS = 43;
    public static final int AUS_CASOS_SERVICIOS_ESTADO_RESUELTO = 44;
    public static final int AUS_CASOS_SERVICIOS_ESTADO_AUDITAR = 45;
    public static final int AUS_CASOS_SERVICIOS_AMBITO_SIN_AUDITORIA = 46;
    public static final int AUS_CASOS_SERVICIOS_AMBITO_SOLICITUD_REPO = 47;
    //CUENTAS MEDICAS
    public static final int CM_DEVOLUCION_MOTIVO_RESOLUCION_3047 = 54;
    public static final int CM_DEVOLUCION_MOTIVO_RESOLUCION_2284 = 55;
    public static final int CM_MOTIVO_GLOSA_RESOLUCION_3047 = 56;
    public static final int CM_MOTIVO_GLOSA_RESOLUCION_2284 = 57;
    public static final int CM_MOTIVO_GLOSA_ESPEFIFICO_RESOLUCION_3047  = 58;
    public static final int CM_MOTIVO_GLOSA_ESPEFIFICO_RESOLUCION_2284  = 59;
    //CENTRO REGULADOR
    public static final int CR_A9_ESTADO_SEMAFORO_GESTION = 60;
    public static final int CR_ADJUNTO_TIPO_SEMAFORO_ADJUNTO = 61;
    public static final int CR_GESTION_TIPO_SEMAFORO_GESTION_TIPO = 62;
    public static final int CR_GESTION_TIPO_FECHA_HORA_EGRESO = 66;
    //AUDITORIA CONCURRENTE
    public static final int AUC_DESTINO_EGRESO_ALTA_TEMPRANA = 63;
    public static final int AUC_SEGUIMIENTO_GESTORES_IPS_RECEPTORA = 64;
    public static final int AUC_SEGUIMIENTO_ESTADO_FECHA_CIERRE_GESTION = 65;
    public static final int AUC_DESTINO_EGRESO_EGRESO = 82;
    public static final int AUC_DESTINO_EGRESO_GESTION = 83;
    public static final int AUC_TIPO_SEGUIMIENTO_AUDITORES = 84;
    public static final int AUC_TIPO_SEGUIMIENTO_GESTORES = 85;
    //CONTRATACION JURIDICA
    public static final int CNTJ_GARANTIA_APLICA_ANTICIPO = 67;
    public static final int CNTJ_GARANTIA_VIGENCIA_HASTA = 68;
    public static final int GARANTIA_VIG_HASTA_4_MES = 69;
    public static final int GARANTIA_VIG_HASTA_36_MES = 70;
    public static final int GARANTIA_VIG_HASTA_60_MES = 71;
    public static final int GARANTIA_VIG_HASTA_12_MES = 72;
    public static final int CNTJ_ESTADO_CONRATO_VIGENTE = 81;
    
    public static final int GN_ORIGEN_ATENCION_RESOLUCION_3047 = 73;
    public static final int GN_ORIGEN_ATENCION_RESOLUCION_2335 = 74;
    public static final int GN_TIPO_SERVICIO_RESOLUCION_3047 = 75;
    public static final int GN_TIPO_SERVICIO_RESOLUCION_2335 = 76;
    public static final int GN_UBICACION_RESOLUCION_304 = 77;
    public static final int GN_UBICACION_RESOLUCION_2335 = 78;
    public static final int CR_A2_DESTINO_PACIENTE_RESOLUCION_3047 = 79;
    public static final int CR_A2_DESTINO_PACIENTE_RESOLUCION_2335 = 80;
    
    
    private Integer id;
    private MaestroTipo maestrosTipo;
    private String nombre;
    private String descripcion;
    private Integer idGrupo;
    
    public MaestroAccion() {
        
    }
    
    public MaestroAccion(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MaestroTipo getMaestrosTipo() {
        return maestrosTipo;
    }

    public void setMaestrosTipo(MaestroTipo maestrosTipo) {
        this.maestrosTipo = maestrosTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

}
