package com.saviasaludeps.savia.dominio.constantes;

public enum Constantes {
    //Remotos
    MP_DIRECCIONAMIENTO_WS_SERVICIO("MpDireccionamientoWSServicio"),
    MP_CONSUMO_FALLO_WS_SERVICIO("MpConsumoFalloWsServicio"),
    MP_PRESCRIPCION_TECNOLOGIA_WS_SERVICIO("MpPrescripcionTecnologiaWSServicio"),
    MP_PRESCRIPCION_WS_SERVICIO("PrescripcionWsServicio"),
    //Mensajes
    MP_MSJ_ERROR_CONSULTANDO_WS_FACTURACION("Error consultando registros WS Facturación. "),
    MP_MSJ_ERROR_SETEAR_LISTA("Error llenando listas."),
    MP_MSJ_ERROR_CONSUMIENDO_SERVICIO_CONSULTA_FACTURACION("No fue posible consumir WS de consulta facturación o no se obtuvo respuesta. "),
    MP_MSJ_ERROR_CONSUMIENDO_SERVICIO_ENVIO_FACTURACION("No fue posible consumir WS de envio facturación. "),
    MP_MSJ_ERROR_SINTAXIS_JSON("Error de sintaxis JSON al consumir WS Consulta Facturacion. "),
    MP_MSJ_ERROR_INSERCION_DIRECCIONAMIENTO_ENTREGADO("Error al insertar MpDireccionamientoEntregado. "),
    MP_MSJ_ERROR_CONSUMO("Error al consumir servicio que inserta/actualiza el consumo. "),
    MP_MSJ_ERROR_CONSUMIENDO_ENTREGA_FACTURA_REMOTO("Error al consumir servicio que inserta/actualiza entrega factura. "),
    MP_MSJ_ERROR_SINCRONIZAR_ENTREGA_FACTURA("Error al sincronizar entrega factura. "),
    MP_MSJ_ERROR_ESTADO_RESPUESTA_ENVIO_FACTURAS("La respuesta del estado es diferente al código 200. "),
    MP_MSJ_ERROR_OBTENER_TOKEN("Error obteniendo token. "),
    MP_MSJ_ERROR_CONSULTANDO_FACTURA_ENTREGA("Error al consultar factura entrega existente. "),
    MP_MSJ_ERROR_INICIANDO_CONSUMO("Error iniciando consumo. "),
    MP_MSJ_ERROR_CONSULTAR_FACTURAS_ENVIO("Error al consultar facturas a enviar. "),
    MP_MSJ_ERROR_ENVIAR_FACTURAS_PUT("Error al enviar Datos Facturados. "),
    MP_MSJ_INFO_SIN_REGISTROS("Sin registros."),
    MP_MSJ_INFO_INSERCION_BD_GENERICO("Inicio insercion BD."),
    MP_MSJ_INFO_FIN_INSERCION_BD_GENERICO("Fin insercion BD."),
    MP_MSJ_INFO_ID_TRANSACCION_NO_ENCONTRADO("No se encontró registro con idTransaccion: "),
    //Excepciones
    MP_MSJ_EXCEPCION_URL_WS_FACTURACION("URL mal formada al consumir WS Consulta Facturacion. "),
    MP_MSJ_EXCEPCION_IO_WS_FACTURACION("Error de IO al consumir WS Consulta Facturacion. "),
    //General
    MP_USUARIO_CREA("mipres sincroniza"),
    LOCALHOST("127.0.0.1"),
    CADENA_VACIA(""),
    CONTENT_TYPE("application/json; charset=utf-8"),
    BEARER("Bearer "),
    DESCONOCIDO("Desconocido"),
    SHORT_DATE_FORMAT("yyyy-MM-dd"),
    LARGE_DATE_FORMAT("yyyy-MM-dd HH:mm"),
    EXITOSO("Exitoso"),
    PUT("PUT"),
    UTF_8("utf8");

    private final String value;

    Constantes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
