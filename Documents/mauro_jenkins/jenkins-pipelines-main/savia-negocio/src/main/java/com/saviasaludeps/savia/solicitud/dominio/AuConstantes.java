package com.saviasaludeps.savia.solicitud.dominio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Item;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public class AuConstantes implements Serializable {

    //NUMEROS
    public static final int CERO = 0;
    public static final int UNO = 1;
    public static final int DOS = 2;
    public static final int TRES = 3;
    public static final int CUATRO = 4;
    public static final int CINCO = 5;

    //TEXTOS
    public final static String TEXTO_VACIO = "";
    public final static String PUNTO = ".";
    public final static String GUION_BAJO = "_";
    public final static String ESPACIO = " ";
    public final static String SALTO_LINEA = "/n";
    public final static String TEXTO_SI = "Si";
    public final static String TEXTO_NO = "No";
    public final static String LLAVE_ABRE = "{";
    public final static String LLAVE_CIERRA = "}";
    public final static String COMILLA_DOBLE = "\"";
    public final static String DOS_PUNTOS = ":";
    public final static String COMA = ",";

    //Textos para webService
    public final static String TEXTO_WS_TIPO_DOCUMENTO = "tipoDocumento";
    public final static String TEXTO_WS_NUMERO_DOCUMENTO = "numeroDocumento";

    //Variables para solicitud
    public final static int MAXIMO_DIAGNOSTICOS = 3;
    public final static int MAXIMA_CANTIDAD_ANEXOS = 1;
    public final static int TAMANO_LIMITE_ANEXO = 5000;

    //Textos para solicitud
    public final static String ERROR_DIAGNOSTICO_PRINCIPAL = "Ya se ha seleccionado como principal otro diagnostico";
    public final static String ERROR_NO_MAESTRO = "El maestro no fue encontrado";
    //Textos para carga masiva
    public final static String TEXTO_CARGA_CON_EXITO = "El archivo %s se cargó con exito, con número de radicado %s";
    public final static String TEXTO_CARGA_FALLO = "Error en formato del archivo: %s";
    public final static String TEXTO_ERROR = "Error : %s";
    public final static String TEXTO_LINEA_MALA = "En la fila #%s en la columan %s no tiene el formato correspondiente";

    //Formato de fecha orden
    public final static SimpleDateFormat formato1 = new SimpleDateFormat("dd/MM/yyyy");
    public final static SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
    public final static SimpleDateFormat formato3 = new SimpleDateFormat("dd/MM/yy");
    public final static SimpleDateFormat formato4 = new SimpleDateFormat("YYYYMMddHHmmssSSS");
    public final static SimpleDateFormat formato5 = new SimpleDateFormat("dd/MM/yyyy");
    public final static SimpleDateFormat formato6 = new SimpleDateFormat("YYYYMMddHHmmss");
    public final static SimpleDateFormat formato7 = new SimpleDateFormat("YYYYMMdd");

    //Tipos de cargue
    public final static String CARGUE_MANUAL = "Manual";
    public final static String CARGUE_CARGA_MASAVIA = "Carga Masiva";
    public final static String CARGUE_WEB_SERVICE = "Interoperabilidad";

    public final static String SAP_FINANCIERO = "SAP_Financiero";

    public final static int ID_CARGUE_MANUAL = 1;
    public final static int ID_CARGUE_CARGA_MASIVA = 2;
    public final static int ID_CARGUE_WEB_SERVICES = 3;

    //Estados carga detalle
    public final static int ESTADO_DETALLE_CARGA_INGRESADO = 4;
    public final static String TEXTO_ESTADO_DETALLE_CARGA_INGRESADO = "INGRESADO";
    public final static int ESTADO_DETALLE_CARGA_FALLIDO = 5;
    public final static String TEXTO_ESTADO_DETALLE_CARGA_FALLIDO = "FALLIDO";

    //Estados carga
    public static final int ESTADO_CARGA_EN_COLA = 0;
    public static final int ESTADO_CARGA_EN_PROCESO = 1;
    public static final int ESTADO_CARGA_PROCESADO = 2;
    public static final int ESTADO_CARGA_ABORTADO = 3;
    public static final String TEXTO_ESTADO_CARGA_EN_COLA = "EN COLA";
    public static final String TEXTO_ESTADO_CARGA_EN_PROCESO = "EN PROCESO";
    public static final String TEXTO_ESTADO_CARGA_PROCESADO = "PROCESADO";
    public static final String TEXTO_ESTADO_CARGA_ABORTADO = "CANCELADO";

    //Estados Solicitud
    public static final int ESTADO_SOLICITUD_PENDIENTE = 1;
    public static final int ESTADO_SOLICITUD_GESTIONADO = 2;
    public static final int ESTADO_SOLICITUD_ANULADO = 3;
    public static final int ESTADO_SOLICITUD_EN_GESTION = 4;
    public static final String TEXTO_ESTADO_SOLICITUD_PENDIENTE = "PENDIENTE";
    public static final String TEXTO_ESTADO_SOLICITUD_GESTIONADO = "GESTIONADO";
    public static final String TEXTO_ESTADO_SOLICITUD_ANULADO = "ANULADO";
    public static final String TEXTO_ESTADO_SOLICITUD_EN_GESTION = "EN GESTION";

    //Ubicaciones documento carga masiva documentos
    public static final int CONSECUTIVO_UBICACION = 0;
    public static final int TIPO_DOCUMENTO_AFILIADO_UBICACION = 1;
    public static final int NUMERO_DOCUMENTO_AFILIADO_UBICACION = 2;
    public static final int TELEFONO_UBICACION = 3;
    public static final int CELULAR_UBICACION = 4;
    public static final int AMBITO_UBICACION = 5;
    public static final int SERVICIO_ATENCION_UBICACION = 6;
    public static final int FECHA_ORDEN_MEDICA_UBICACION = 7;
    public static final int CODIGO_HABILITACION_UBICACION = 8;
    public static final int TIPO_DOCUMENTO_PROFESIONAL_UBICACION = 9;
    public static final int NUMERO_DOCUMENTO_PROFESIONAL_UBICACION = 10;
    public static final int ESPECIALIDAD_UBICACION = 11;
    public static final int ORIGEN_ATENCION_UBICACION = 12;
    public static final int PRIORIDAD_ATENCION_UBICACION = 13;
    public static final int SERVICIO_SOLICITADO_UBICACION = 14;
    public static final int UBICACION_PACIENTE_UBICACION = 15;
    public static final int CODIGO_DIAGNOSTICO_UBICACION = 16;
    public static final int TIPO_DIAGNOSTICO_UBICACION = 17;
    public static final int PRICIPAL_DIAGNOSTICO_UBICACION = 18;
    public static final int TIPO_TECNOLOGIA_UBICACION = 19;
    public static final int CODIGO_TECNOLOGIA_UBICACION = 20;
    public static final int CANTIDAD_TECNOLOGIA_UBICACION = 21;
    public static final int DURACION_TECNOLOGIA_UBICACION = 22;
    public static final int CODIGO_SERVICIO_TECNOLOGIA_UBICACION = 23;
    public static final int DOSIS_TECNOLOGIA_UBICACION = 24;
    public static final int FRECUENCIA_TECNOLOGIA_UBICACION = 25;
    public static final int TIPO_FRECUENCIA_TECNOLOGIA_UBICACION = 26;
    public static final int VIA_ADMINITRACION_TECNOLGIA_UBICACION = 27;
    public static final int JUSTICIFIACION_UBICACION = 28;

    public static final String TEXTO_CONSECUTIVO = "Consecutivo";
    public static final String TEXTO_TIPO_DOCUMENTO_AFILIADO = "Tipo Documento Afiliado";
    public static final String TEXTO_NUMERO_AFILIADO = "Número Documento Afiliado";
    public static final String TEXTO_TELEFONO_AFILIADO = "Teléfono Afiliado";
    public static final String TEXTO_CELULAR_AFILIADO = "Celular Afiliado";
    public static final String TEXTO_AMBITO = "Ámbito";
    public static final String TEXTO_CODIGO_SERVICIO_ATENCION = "Código servicio de atención";
    public static final String TEXTO_FECHA_ORDEN = "Fecha de orden médica";
    public static final String TEXTO_CODIGO_HABILITACION = "Código habilitación";
    public static final String TEXTO_TIPO_DOCUMENTO_PROFESIONAL = "Tipo Documento Profesional";
    public static final String TEXTO_NUMERO_DOCUMENTO_PROFESIONAL = "Número Documento Profesional";
    public static final String TEXTO_ESPECIALIDAD = "Especilidad profesional";
    public static final String TEXTO_ORIGEN = "Origen de la atención";
    public static final String TEXTO_PRIORIDAD = "Prioridad de la atención";
    public static final String TEXTO_TIPO_SERVICIO_SOLICITADO = "Tipo servicio solicitado";
    public static final String TEXTO_UBICACION_PACIENTE = "Ubicación del paciente";
    public static final String TEXTO_CODIGO_DIAGNOSTICO = "Código Diagnostico";
    public static final String TEXTO_TIPO_DIAGNOSTICO = "Tipo Diagnostico";
    public static final String TEXTO_PRINCIAPAL = "Principal Diangostico";
    public static final String TEXTO_TIPO_TECNOLOGIA = "Tipo Tecnologia";
    public static final String TEXTO_CODIGO_TECNOLOGIA = "Código Tecnologia";
    public static final String TEXTO_CANTIDAD_TECNOLOGIA = "Cantidad Tecnología";
    public static final String TEXTO_DURACION_TECNOLOGIA = "Duración del tratamiento";
    public static final String TEXTO_CODIGO_SERVICIO_TECNOLOGIA = "Código Servicio Tecnologia";
    public static final String TEXTO_DOSIS_TECNOLOGIA = "Dosis Tecnologia";
    public static final String TEXTO_FRECUENCIA_TECNOLOGIA = "Frecuencia Tecnologia";
    public static final String TEXTO_TIPO_FRECUENCIA_TECNOLOGIA = "Tipo Frecuencia Tecnologia";
    public static final String TEXTO_VIA_TECNOLOGIA = "Vía administración Tecnologia";
    public static final String TEXTO_JUSTIFICACION = "Justificación clínica";

    //Variables para prescipciones
    public static final String TEXTO_TECNOLOGIA = "Procedimiento";
    public static final String TEXTO_MEDICAMENTO = "Medicamento";
    public static final String TEXTO_INSUMO = "Insumo";
    public static final String TEXTO_PAQUETE = "Paquete";

    public static final int ID_TECNOLOGIA = 1;
    public static final int ID_MEDICAMENTO = 2;
    public static final int ID_INSUMO = 3;
    public static final int ID_PAQUETE = 4;
    public static final int ID_AGRUPADOR_MEDICAMENTO = 5;

    public static final int EMPRESA_SAVIA = 1;

    public static final String ERROR_ADICION_TECNOLOGIA = "El procedimiento %s ya fue agregado";
    public static final String ERROR_ADICION_MEDICAMENTO = "El medicamento %s ya fue agregado";
    public static final String ERROR_ADICION_INSUMO = "El insumo %s ya fue agregado";
    public static final String ERROR_ADICION_PAQUETE = "El paquete %s ya fue agregado";

    //Prioritario
    public static final String PRIORITARIO = "Prioritario";
    public static final String NO_PRIORITARIO = "No Prioritario";

    //Niveles
    public static final String NIVEL_0 = "Nivel 0";
    public static final String NIVEL_1 = "Nivel 1";
    public static final String NIVEL_2 = "Nivel 2";
    public static final String NIVEL_3 = "Nivel 3";
    public static final String NIVEL_4 = "Nivel 4";
    public static final String NIVEL_5 = "Nivel 5";

    //Estados item
    public static final String PENDIENTE_AUDITORIA = "2";
    public static final String ANULADO = "11";
    public static final String PENDIENTE_COTIZACION = "13";
    public static final String RECHAZADO_AUDITORIA = "9";
    public static final String APROBADO_AUDITORIA = "4";
    public static final String APROBADO_AUTOMATICAMENTE = "5";
    public static final String CON_COTIZACION = "14";
    public static final String DEVUELTO_AUDITORIA = "12";

    //TEXTO FILTROS
    public static final String FILTRO_CONTRATO_TECNOLOGIA_ID = "maTecnologiaId";
    public static final String FILTRO_CONTRATO_SEDE = "cntPrestadorSedesId";

    //Tipo contacto afiliado
    public static final String CODIGO_CONTACTO_TELEFONO = "1";
    public static final String CODIGO_CONTACTO_CELULAR = "2";

    //Contactos prestador   
    public static final String AREA_CONTACTO_AUTORIZACION = "1";
    public static final String AREA_CONTACTO_CRUE = "3";
    public static final String TIPO_CONTACTO_TELEFONO_MOVIL = "1";
    public static final String TIPO_CONTACTO_TELEFONO_FIJO = "2";
    public static final String TIPO_CONTACTO_CORREO = "3";
    public static final String TIPO_CONTACTO_TELEFONO_PBX = "4";

    //Motivos automatico
    public static final String VALOR_RECHAZADO_CAPITADO = "6";
    public static final String VALOR_RECHAZADO_POSFECHADO = "22";

    //Usuario sistema auditorias automaticas
    public static final String USUARIO_SISTEMA = "Sistema";

    //Estado solicitud direccionado
    public static final int PE_DIRECCIONADO_ITEM_PENDIENTE = 1;
    public static final int PE_DIRECCIONADO_PENDIENTE = 1;
    //Origen marcacion automatica
    public final static int ORIGEN_ANEXO3 = 3;
    //capita/pgp
    public final static String MODALIDAD_CONTRATO_CAPITA = "01";
    public final static String MODELO_LIQUIDACION_CAPITA = "0";
    //codigos ambito
    public final static String CODIGO_AMBITO_AMBULATORIO = "A";
    public final static String CODIGO_AMBITO_HOSPITALARIO = "H";

    //Constante para version de informacion
    public final static boolean VERSION_0 = false;
    public final static boolean VERSION_1 = true;
    public final static SimpleDateFormat FORMATO_CONSECUTIVO = new SimpleDateFormat("yyyyMMdd");

    public static List<Maestro> obtenerEstadoCarga() {
        List<Maestro> listaMaestro = new ArrayList();
        int i = 0;
        while (i <= 5) {
            Maestro mae = new Maestro();
            mae.setId(i);
            switch (i) {
//                case CERO :
//                    mae.setNombre(TEXTO_ESTADO_CARGA_EN_COLA);
//                    listaMaestro.add(mae);
//                    break;
                case UNO:
                    mae.setNombre(TEXTO_ESTADO_CARGA_EN_PROCESO);
                    listaMaestro.add(mae);
                    break;
                case DOS:
                    mae.setNombre(TEXTO_ESTADO_CARGA_PROCESADO);
                    listaMaestro.add(mae);
                    break;
                case TRES:
                    mae.setNombre(TEXTO_ESTADO_CARGA_ABORTADO);
                    listaMaestro.add(mae);
                    break;
//                case CUATRO :
//                    mae.setNombre(TEXTO_ESTADO_DETALLE_CARGA_INGRESADO);
//                    listaMaestro.add(mae);
//                    break;
//                case CINCO :
//                    mae.setNombre(TEXTO_ESTADO_DETALLE_CARGA_FALLIDO);
//                    listaMaestro.add(mae);
//                    break;
            }
//            listaMaestro.add(mae);
            i++;
        }
        return listaMaestro;
    }

    public static List<Maestro> obtenerMaestroTipoTecnologias() {
        List<Maestro> listaMaestro = new ArrayList();
        int i = 1;
        while (i <= 4) {
            Maestro mae = new Maestro();
            mae.setId(i);
            switch (i) {
                case UNO:
                    mae.setNombre(TEXTO_TECNOLOGIA);
                    mae.setDescripcion(TEXTO_TECNOLOGIA);
                    mae.setValor("" + i);
                    break;
                case DOS:
                    mae.setNombre(TEXTO_MEDICAMENTO);
                    mae.setDescripcion(TEXTO_MEDICAMENTO);
                    mae.setValor("" + i);
                    break;
                case TRES:
                    mae.setNombre(TEXTO_INSUMO);
                    mae.setDescripcion(TEXTO_INSUMO);
                    mae.setValor("" + i);
                    break;
                case CUATRO:
                    mae.setNombre(TEXTO_PAQUETE);
                    mae.setDescripcion(TEXTO_PAQUETE);
                    mae.setValor("" + i);
                    break;
            }
            listaMaestro.add(mae);
        }
        return listaMaestro;
    }

    public static HashMap<Integer, Maestro> obtenerHashMaestro(List<Maestro> listaMaestros) {
        HashMap<Integer, Maestro> hash = new HashMap();
        listaMaestros.forEach(maestro -> {
            hash.put(maestro.getId(), maestro);
        });
        return hash;
    }

    public static List<Maestro> obtenerTiposCargue() {
        List<Maestro> lista = new ArrayList();
        int contador = 0;
        while (contador <= 2) {
            Maestro maestro = new Maestro();
            maestro.setId(contador);
            switch (contador) {
                case UNO:
                    maestro.setNombre(CARGUE_MANUAL);
                    maestro.setDescripcion(CARGUE_MANUAL);
                    lista.add(maestro);
                    break;
                case DOS:
                    maestro.setNombre(CARGUE_CARGA_MASAVIA);
                    maestro.setDescripcion(CARGUE_CARGA_MASAVIA);
                    lista.add(maestro);
                    break;
            }
            contador++;
        }
        return lista;
    }

    public static String obtenerNombreFuenteOrigen(int id) {
        switch (id) {
            case ID_CARGUE_MANUAL:
                return CARGUE_MANUAL;
            case ID_CARGUE_CARGA_MASIVA:
                return CARGUE_CARGA_MASAVIA;
            case ID_CARGUE_WEB_SERVICES:
                return CARGUE_WEB_SERVICE;
            default:
                return TEXTO_VACIO;
        }
    }

    public static int obtenerIdFuenteOrigen(String nombre) {
        switch (nombre) {
            case CARGUE_MANUAL:
                return ID_CARGUE_MANUAL;
            case CARGUE_CARGA_MASAVIA:
                return ID_CARGUE_CARGA_MASIVA;
            case CARGUE_WEB_SERVICE:
                return ID_CARGUE_WEB_SERVICES;
            default:
                return CERO;
        }
    }

    public static List<Maestro> obtenerMaestrosPrioritario() {
        List<Maestro> listaMaestros = new ArrayList();
        int contador = 1;
        while (contador <= 2) {
            Maestro maestro = new Maestro();
            maestro.setId(contador);
            switch (contador) {
                case UNO:
                    maestro.setNombre(PRIORITARIO);
                    maestro.setDescripcion(PRIORITARIO);
                    break;
                case DOS:
                    maestro.setNombre(NO_PRIORITARIO);
                    maestro.setDescripcion(NO_PRIORITARIO);
                    break;
            }
            listaMaestros.add(maestro);
            contador++;
        }
        return listaMaestros;
    }

    public static HashMap<Integer, Ubicacion> obtenerHashUbicacion(List<Ubicacion> listaUbicaciones) {
        HashMap<Integer, Ubicacion> hash = new HashMap();
        listaUbicaciones.forEach(ubicacion -> {
            hash.put(ubicacion.getId(), ubicacion);
        });
        return hash;
    }

    public static List<Maestro> obtenerNiveles() {
        List<Maestro> lista = new ArrayList();
        int contador = 0;
        while (contador <= 5) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case CERO:
                    mae.setNombre(NIVEL_0);
                    mae.setDescripcion(NIVEL_0);
                    mae.setValor(COMILLA_DOBLE + contador);
                    lista.add(mae);
                    break;
                case UNO:
                    mae.setNombre(NIVEL_1);
                    mae.setDescripcion(NIVEL_1);
                    mae.setValor(COMILLA_DOBLE + contador);
                    lista.add(mae);
                    break;
                case DOS:
                    mae.setNombre(NIVEL_2);
                    mae.setDescripcion(NIVEL_2);
                    mae.setValor(COMILLA_DOBLE + contador);
                    lista.add(mae);
                    break;
                case TRES:
                    mae.setNombre(NIVEL_3);
                    mae.setDescripcion(NIVEL_3);
                    mae.setValor(COMILLA_DOBLE + contador);
                    lista.add(mae);
                    break;
                case CUATRO:
                    mae.setNombre(NIVEL_4);
                    mae.setDescripcion(NIVEL_4);
                    mae.setValor(COMILLA_DOBLE + contador);
                    lista.add(mae);
                    break;
                case CINCO:
                    mae.setNombre(NIVEL_5);
                    mae.setDescripcion(NIVEL_5);
                    mae.setValor(COMILLA_DOBLE + contador);
                    lista.add(mae);
                    break;
            }
            contador++;
        }
        return lista;
    }

    public static String obtenerTipoTecnologia(int id) {
        switch (id) {
            case UNO:
                return TEXTO_TECNOLOGIA;
            case DOS:
                return TEXTO_MEDICAMENTO;
            case TRES:
                return TEXTO_INSUMO;
            case CUATRO:
                return TEXTO_PAQUETE;
        }
        return TEXTO_VACIO;
    }

    public static List<Maestro> obtenerEstadosSolicitud() {
        List<Maestro> lista = new ArrayList();
        int contador = 1;
        while (contador <= 4) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case UNO:
                    mae.setNombre(TEXTO_ESTADO_SOLICITUD_PENDIENTE);
                    mae.setDescripcion(TEXTO_ESTADO_SOLICITUD_PENDIENTE);
                    mae.setValor(COMILLA_DOBLE + contador);
                    lista.add(mae);
                    break;
                case DOS:
                    mae.setNombre(TEXTO_ESTADO_SOLICITUD_GESTIONADO);
                    mae.setDescripcion(TEXTO_ESTADO_SOLICITUD_GESTIONADO);
                    mae.setValor(COMILLA_DOBLE + contador);
                    lista.add(mae);
                    break;
                case TRES:
                    mae.setNombre(TEXTO_ESTADO_SOLICITUD_ANULADO);
                    mae.setDescripcion(TEXTO_ESTADO_SOLICITUD_ANULADO);
                    mae.setValor(COMILLA_DOBLE + contador);
                    lista.add(mae);
                    break;
                case CUATRO:
                    mae.setNombre(TEXTO_ESTADO_SOLICITUD_EN_GESTION);
                    mae.setDescripcion(TEXTO_ESTADO_SOLICITUD_EN_GESTION);
                    mae.setValor(COMILLA_DOBLE + contador);
                    lista.add(mae);
                    break;

            }
            contador++;
        }
        return lista;
    }

    public static String nombreReporteAnexo4_3(AuAnexo4 anexo) {
        int idAnexo4 = anexo.getId();
        String fecha = formato6.format(anexo.getFechaHoraCrea());
        return "anexo4-3_" + idAnexo4 + fecha + ".pdf";
    }

    public static String nombreReporteAnexo4_2(AuAnexo4 anexo) {
        int idAnexo4 = anexo.getId();
        String fecha = formato6.format(anexo.getFechaHoraCrea());
        return "anexo4-2_" + idAnexo4 + "_" + fecha + ".pdf";
    }

    public static String nombreArchivoReporteAnexo4(AuAnexo4 anexo) {
        return "anexo4-" + anexo.getId() + "_" + anexo.getAfiliadoNumeroDocumento() + "_" + formato2.format(anexo.getFechaHoraCrea()) + ".pdf";
    }

    public static String mensajeAutorizacionSMS(AuAnexo4Item anexo) {
        StringBuilder builder = new StringBuilder();
        builder.append("SAVIA SALUD EPS informa autorización ");
        builder.append(anexo.getAuAnexo4Id().getId());
        builder.append(" de ");
        builder.append(anexo.getMaTecnologiaValor());
        builder.append(" para ");
        builder.append(anexo.getAuAnexo4Id().getPrestadorNombre());
        builder.append(" Tel ");
        builder.append(anexo.getAuAnexo4Id().getPrestadorTelefonoCita() == null ? "" : anexo.getAuAnexo4Id().getPrestadorTelefonoCita());
        builder.append(" vigente por ");
        builder.append(anexo.getAuAnexo4Id().getDiasVigencia());
        builder.append(" días.");
        String mensaje = builder.toString();
        if (mensaje.length() > 160) {//mensaje de texto adminte 160
            mensaje = mensaje.substring(0, 160);
        }
        return mensaje;
    }
}
