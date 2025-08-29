package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MpConsumo extends Auditoria {

    public final static int ESTADO_CONSUMO_CONSULTANDO = 0;
    public final static int ESTADO_CONSUMO_GUARDANDO = 1;
    public final static int ESTADO_CONSUMO_EXITOSO = 2;
    public final static int ESTADO_CONSUMO_CONSULTANDO_ERROR = 3;
    public final static int ESTADO_CONSUMO_GUARDANDO_ERROR = 4;

    // SERVICIOS MASIVOS SUBSIDIADO
    public static final String SUB_CONSULTA_PRESCRIPCION = "00";
    public static final String SUB_CONSULTA_RECOBRANTE = "01";
    public static final String SUB_CONSULTA_PRESCRIPCION_ANULADA = "02";
    public static final String SUB_CONSULTA_PRESCRIPCION_ANULADA_SOLICITUD = "99";
    public static final String SUB_CONSULTA_JUNTA_MEDICA = "03";
    public static final String SUB_CONSULTA_DIRECCIONAMIENTO = "04";
    public static final String SUB_CONSULTA_NO_DIRECCIONAMIENTO = "05";
    public static final String SUB_ENVIO_ANULARDIRECCIONAMIENTO = "06";
    public static final String SUB_CONSULTA_ENTREGAS = "07";
    public static final String SUB_CONSULTA_SUMINISTROS = "08";
    public static final String SUB_ENVIO_DIRECCIONAMIENTO = "09";
    public static final String SUB_ENVIO_NO_DIRECCIONAMIENTO = "0A";
    public static final String SUB_ENVIO_SUMINISTROS = "0B";
    public static final String SUB_CONSULTA_DATOSFACTURA = "0C";
    public static final String SUB_CONSULTA_FACTURACION = "0D";
    public static final String SUB_ENVIO_DATOSFACTURADOS = "0E";
    public static final String SUB_ENVIO_ANULARSUMINISTRO = "0G";
    public static final String SUB_CONSULTA_NRO_DIRECCIONAMIENTO = "0H";
    public static final String SUB_ENVIO_ANULAR_NO_DIRECCIONAMIENTO = "0I";
    public static final String SUB_CONSULTA_NRO_JUNTA = "0J";
    public static final String SUB_CONSULTA_NRO_NO_DIRECCIONAMIENTO = "0K";
    public static final String SUB_CONSULTA_NRO_ENTREGA = "0L";
    public static final String SUB_CONSULTA_NRO_FACTURA = "0M";
    public static final String SUB_CONSULTA_NRO_ANULACION = "0N";
    // SERVICIOS MASIVOS CONTRIBUTIVO
    public static final String CON_CONSULTA_PRESCRIPCION = "10";
    public static final String CON_CONSULTA_RECOBRANTE = "11";
    public static final String CON_CONSULTA_PRESCRIPCION_ANULADA = "12";
    public static final String CON_CONSULTA_PRESCRIPCION_ANULADA_SOLICITUD = "88";
    public static final String CON_CONSULTA_JUNTA_MEDICA = "13";
    public static final String CON_CONSULTA_DIRECCIONAMIENTO = "14";
    public static final String CON_CONSULTA_NO_DIRECCIONAMIENTO = "15";
    public static final String CON_ENVIO_ANULARDIRECCIONAMIENTO = "16";
    public static final String CON_CONSULTA_ENTREGAS = "17";
    public static final String CON_CONSULTA_SUMINISTROS = "18";
    public static final String CON_ENVIO_DIRECCIONAMIENTO = "19";
    public static final String CON_ENVIO_NO_DIRECCIONAMIENTO = "1A";
    public static final String CON_ENVIO_SUMINISTROS = "1B";
    public static final String CON_CONSULTA_DATOSFACTURA = "1C";
    public static final String CON_CONSULTA_FACTURACION = "1D";
    public static final String CON_ENVIO_DATOSFACTURADOS = "1E";
    public static final String CON_ENVIO_ANULARSUMINISTRO = "1G";
    public static final String CON_CONSULTA_NRO_DIRECCIONAMIENTO = "1H";
    public static final String CON_ENVIO_ANULAR_NO_DIRECCIONAMIENTO = "1I";
    public static final String CON_CONSULTA_NRO_JUNTA = "1J";
    public static final String CON_CONSULTA_NRO_NO_DIRECCIONAMIENTO = "1K";
    public static final String CON_CONSULTA_NRO_ENTREGA = "1L";
    public static final String CON_CONSULTA_NRO_FACTURA = "1M";
    public static final String CON_CONSULTA_NRO_ANULACION = "1N";
    // SERVICIOS MASIVOS AMBOS
    public static final String AMB_CONSULTA_ENTREGAS = "27";
    public static final String AMB_CONSULTA_FACTURACION = "2D";

    // SERVICIOS MASIVOS AMBOS (SUBSIDIADO/CONTRIBUTIVO)
    // SERVICIOS INDIVIDUALES SUBSIDIADO
    public static final boolean CONSUMO_SUBSIDIADO = true;
    public static final boolean CONSUMO_CONTRIBUTIVO = false;

    private Integer id;
    private int registros;
    private int registrosExitosos;
    private String servicio;
    private String observacion;
    private String servicioDireccion;
    private String servicioDescripcion;
    private int estado;
    private String periodo;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;

    public MpConsumo() {
    }

    public MpConsumo(int registros, int registrosExitosos, String servicio, String observacion, String servicioDireccion, String servicioDescripcion, int estado, String periodo, Date fechaHoraInicio, Date fechaHoraFin) {
        this.registros = registros;
        this.registrosExitosos = registrosExitosos;
        this.servicio = servicio;
        this.observacion = observacion;
        this.servicioDireccion = servicioDireccion;
        this.servicioDescripcion = servicioDescripcion;
        this.estado = estado;
        this.periodo = periodo;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        completarServicio(servicio);
    }

    public MpConsumo(String servicio, Date fechaPeriodo) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        this.estado = MpConsumo.ESTADO_CONSUMO_CONSULTANDO;
        this.fechaHoraInicio = new Date();
        this.observacion = "Inicio servicio " + formato.format(new Date());
        this.servicio = servicio;
        this.periodo = formato.format(fechaPeriodo);
        this.registros = 0;
        this.registrosExitosos = 0;
        completarServicio(servicio);
    }

    public MpConsumo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRegistros() {
        return registros;
    }

    public void setRegistros(int registros) {
        this.registros = registros;
    }

    public int getRegistrosExitosos() {
        return registrosExitosos;
    }

    public void setRegistrosExitosos(int registrosExitosos) {
        this.registrosExitosos = registrosExitosos;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
        completarServicio(servicio);
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getServicioDireccion() {
        return servicioDireccion;
    }

    public void setServicioDireccion(String servicioDireccion) {
        this.servicioDireccion = servicioDireccion;
    }

    public String getServicioDescripcion() {
        return servicioDescripcion;
    }

    public void setServicioDescripcion(String servicioDescripcion) {
        this.servicioDescripcion = servicioDescripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getPeriodo() {
        return periodo;
    }

    public Date getPeriodoDate() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formato.parse(periodo);
        } catch (ParseException ex) {
            return null;
        }
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public Date getFechaInicioDate() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formato.parse(formato.format(fechaHoraInicio));
        } catch (ParseException ex) {
            return null;
        }
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    private void completarServicio(String servicio) {
        if (servicio != null) {
            switch (servicio) {
                case SUB_CONSULTA_PRESCRIPCION://00
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Prescripción Sub";
                    break;
                case CON_CONSULTA_PRESCRIPCION://10
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Prescripción Con";
                    break;
                case SUB_CONSULTA_RECOBRANTE://01
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Recobrante Sub";
                    break;
                case CON_CONSULTA_RECOBRANTE://11
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Recobrante Con";
                    break;
                case SUB_CONSULTA_PRESCRIPCION_ANULADA://02
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Prescripción Anulacion Sub";
                    break;
                case CON_CONSULTA_PRESCRIPCION_ANULADA://12
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Prescripción Anulacion Con";
                    break;
                case SUB_CONSULTA_JUNTA_MEDICA://03
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Junta Médica SUb";
                    break;
                case CON_CONSULTA_JUNTA_MEDICA://13
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Junta Médica Con";
                    break;
                case SUB_CONSULTA_DIRECCIONAMIENTO://04
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Direccionamiento";
                    break;
                case CON_CONSULTA_DIRECCIONAMIENTO://14
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Direccionamiento";
                    break;
                case SUB_CONSULTA_NO_DIRECCIONAMIENTO://05
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "No Direccionamiento";
                    break;
                case CON_CONSULTA_NO_DIRECCIONAMIENTO://15
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "No Direccionamiento";
                    break;
                case SUB_ENVIO_ANULARDIRECCIONAMIENTO://06
                    servicioDireccion = "Envío";
                    servicioDescripcion = "Direccionamiento Anulación Sub";
                    break;
                case CON_ENVIO_ANULARDIRECCIONAMIENTO://16
                    servicioDireccion = "Envío";
                    servicioDescripcion = "Direccionamiento Anulación Con";
                    break;
                case SUB_ENVIO_ANULAR_NO_DIRECCIONAMIENTO://0I
                    servicioDireccion = "Envío";
                    servicioDescripcion = "No Direcc Anulacion Sub";
                    break;
                case CON_ENVIO_ANULAR_NO_DIRECCIONAMIENTO://1I
                    servicioDireccion = "Envío";
                    servicioDescripcion = "No Direcc Anulacion Con";
                    break;
//                case SUB_CONSULTA_ENTREGAS://07
//                    servicioDireccion = "Consulta";
//                    servicioDescripcion = "Entregas Sub-Con";
//                    break;
//                case CON_CONSULTA_ENTREGAS://17 - Inactivo
//                    servicioDireccion = "Consulta";
//                    servicioDescripcion = "Entregas Sub-Con";
//                    break;
                case AMB_CONSULTA_ENTREGAS://27
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Entregas Sub-Con";
                    break;
                case SUB_CONSULTA_SUMINISTROS://08
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Suministros Sub";
                    break;
                case CON_CONSULTA_SUMINISTROS://18
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Suministros Con";
                    break;
                case SUB_ENVIO_DIRECCIONAMIENTO://09
                    servicioDireccion = "Envío";
                    servicioDescripcion = "Direccionamiento Sub";
                    break;
                case CON_ENVIO_DIRECCIONAMIENTO://19
                    servicioDireccion = "Envío";
                    servicioDescripcion = "Direccionamiento Con";
                    break;
                case SUB_ENVIO_NO_DIRECCIONAMIENTO://0A
                    servicioDireccion = "Envío";
                    servicioDescripcion = "No Direccionamiento Sub";
                    break;
                case CON_ENVIO_NO_DIRECCIONAMIENTO://1A
                    servicioDireccion = "Envío";
                    servicioDescripcion = "No Direccionamiento Con";
                    break;
                case SUB_ENVIO_SUMINISTROS://0B
                    servicioDireccion = "Envío";
                    servicioDescripcion = "Sumnistro Sub";
                    break;
                case CON_ENVIO_SUMINISTROS://1B
                    servicioDireccion = "Envío";
                    servicioDescripcion = "Sumnistro Con";
                    break;
                case SUB_CONSULTA_DATOSFACTURA://0C
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Datos Factura Sub";
                    break;
                case CON_CONSULTA_DATOSFACTURA://1C
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Datos Factura Con";
                    break;
//                case SUB_CONSULTA_FACTURACION://0D
//                    servicioDireccion = "Consulta";
//                    servicioDescripcion = "Factura Sub-Con";
//                    break;
//                case CON_CONSULTA_FACTURACION://1D - Inactivo
//                    servicioDireccion = "Consulta";
//                    servicioDescripcion = "Factura Sub-Con";
//                    break;
                case AMB_CONSULTA_FACTURACION://2D
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Factura Sub-Con";
                    break;
                case SUB_ENVIO_DATOSFACTURADOS://0E
                    servicioDireccion = "Envío";
                    servicioDescripcion = "Datos Factura Sub";
                    break;
                case CON_ENVIO_DATOSFACTURADOS://1E
                    servicioDireccion = "Envío";
                    servicioDescripcion = "Datos Factura Con";
                    break;
//                case ://0F
//                    servicioDireccion = "";
//                    servicioDescripcion = "Datos Factura Anular Sub";
//                    break;
//                case ://1F
//                    servicioDireccion = "";
//                    servicioDescripcion = "Datos Factura Anular Con";
//                    break;
                case SUB_ENVIO_ANULARSUMINISTRO://0G
                    servicioDireccion = "Envío";
                    servicioDescripcion = "Suministro Anulación Sub";
                    break;
                case CON_ENVIO_ANULARSUMINISTRO://1G
                    servicioDireccion = "Envío";
                    servicioDescripcion = "Suministro Anulación Con";
                    break;
                case SUB_CONSULTA_NRO_DIRECCIONAMIENTO://0H
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Direccionamiento Sincro Sub";
                    break;
                case CON_CONSULTA_NRO_DIRECCIONAMIENTO://1H
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Direccionamiento Sincro Con";
                    break;
                case SUB_CONSULTA_NRO_NO_DIRECCIONAMIENTO://0H
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "No Direccionamiento Sincro Sub";
                    break;
                case CON_CONSULTA_NRO_NO_DIRECCIONAMIENTO://1H
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "No Direccionamiento Sincro Con";
                    break;
                case SUB_CONSULTA_NRO_JUNTA://0J
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Juntas Sincro Sub";
                    break;
                case CON_CONSULTA_NRO_JUNTA://1J
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Juntas Sincro Con";
                    break;
                case SUB_CONSULTA_NRO_ENTREGA://0L
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Entregas Sincro Sub";
                    break;
                case CON_CONSULTA_NRO_ENTREGA://1L
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Entregas Sincro Con";
                    break;
                case SUB_CONSULTA_NRO_FACTURA://0M
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Facturas Sincro Sub";
                    break;
                case CON_CONSULTA_NRO_FACTURA://1M
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "Facturas Sincro Con";
                    break;
                case SUB_CONSULTA_NRO_ANULACION://0N
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "AnulacionPrescripcion Sincro Sub";
                    break;
                case CON_CONSULTA_NRO_ANULACION://1N
                    servicioDireccion = "Consulta";
                    servicioDescripcion = "AnulacionPrescripcion Sincro Con";
                    break;
                default:
                    servicioDireccion = "Desconocido";
                    servicioDescripcion = "Desconocido";
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return "MpConsumo{" + "id=" + id + ", registros=" + registros + ", registrosExitosos=" + registrosExitosos + ", servicio=" + servicio + ", observacion=" + observacion + ", servicioDireccion=" + servicioDireccion + ", servicioDescripcion=" + servicioDescripcion + ", estado=" + estado + ", periodo=" + periodo + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + '}';
    }

}
