/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.utilidades;

/**
 *
 * @author pavacca
 */
public class Parametrizacion {
    
    public static final String PARAMETRO_URL_CONSULTAR_TOKEN = "url_servicio_consultar_token";
    public static final String PARAMETRO_URL_CONSULTAR_AFILIADO = "url_servicio_consultar_afiliados";
    public static final String PARAMETRO_PASS_ACCESO_SERV_TOKEN = "password_servicio_consultar_token";
    public static final String PARAMETRO_USUARIO_ACCESO_SERV_TOKEN = "usuario_servicio_consultar_token";
    public static final String PARAMETRO_RUTA_DESCARGAS_CASOS = "ruta_descargas_adjuntos_casos";
    public static final String PARAMETRO_HORAS_SERVICIOS = "horas_defecto_servicio";
    public static final String PARAMETRO_VALOR_DEFECTO_PRIORIDAD_CASO = "valor_defecto_prioridad";
    public static final String PARAMETRO_VALOR_DEFECTO_UBICACION_CASO = "valor_defecto_ubicacion_caso";
    public static final String PARAMETRO_VALOR_DEFECTO_SEDE_CASO = "valor_defecto_sede_caso";
    public static final String PARAMETRO_VALOR_DEFECTO_ESTADO_SERVICIO = "valor_defecto_estado_servicio";
    public static final String PARAMETRO_VALOR_DEFECTO_ESPECIALIDAD_SERVICIO = "valor_defecto_especialidad";
    public static final String PARAMETRO_VALOR_DEFECTO_PRESCRIPTORA_SERVICIO = "valor_defecto_ips_prescriptora";
    public static final String PARAMETRO_VALOR_DEFECTO_DESTINO_SERVICIO = "valor_defecto_ips_destino";
    //Almacena ruta si el sistema de parametrizacion falla
    public static final String URL_DESCARGA_ADJUNTOS_DEFECTO =  "/tutelas/anexos_pqrsf/";
    public static final int MAX_CANT_ANEXOS = 5;
    public static final int MAX_TAM_ANEXO = 5242880;
}
