package com.saviasaludeps.savia.web.juridico.utilidades;

import com.saviasaludeps.savia.dominio.administracion.GnAlerta;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.juridico.CntjCampo;
import com.saviasaludeps.savia.dominio.juridico.CntjEstado;
import com.saviasaludeps.savia.dominio.juridico.CntjPlantilla;
import com.saviasaludeps.savia.dominio.juridico.CntjProceso;
import com.saviasaludeps.savia.dominio.juridico.CntjProcesoDocumento;
import com.saviasaludeps.savia.dominio.juridico.CntjTercero;
import java.io.Serializable;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.primefaces.component.inputnumber.InputNumber;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectbooleanbutton.SelectBooleanButton;
import org.primefaces.component.selectonemenu.SelectOneMenu;

/**
 *
 * @author idbohorquez
 */
public class CntjConstantes implements Serializable {
    
    public final static String TEXTO_VACIO = "";
    public final static Integer ACTIVO = 1;
    public final static String ACTIVO_STR = "activo";
    public static final char ACCION_NA = '-';
    public final static String CAMPOS = "campos";
    public final static String NOMBRE = "nombre";
    public final static String VALOR = "valor";
    public final static String VALOR_STR = "valorstr";
    public final static String ETIQUETA = "etiqueta";
    public final static String TABLA = "tabla";
    public final static String CAMPO = "campo";
    public final static String OPCION = "opcion";
    public final static String SEPARADOR = "-";
    public final static String TABLA_CONTRATO= "cntj_contratos";
    public final static String TABLA_SUPERVISOR = "cntj_contrato_supervisores";
    public final static String TABLA_GARANTIAS = "cntj_contrato_garantias";
    public final static String TABLA_INDICADORES = "cntj_contrato_indicadores";
    public final static String TABLA_OBLIGACIONES = "cntj_contrato_obligaciones";
    public final static String TABLA_OTROSI = "cntj_otrosies";
    public final static String CAMPO_EXPEDIENTE = "expediente";
    public final static String CAMPO_EXPEDIENTE_ID = "expediente_id";
    public final static String TIPO = "tipo";
    public final static String IDCAMPO = "idcampo";
    public final static String BORRADOR = "borrador";
    public final static String TABLA_LINEA = "cntj_lineas";
    public final static String SI = "Si";
    public final static String NO = "No";
    public final static String MSJ_EVALUACION_LINEA = "Evaluacion de linea, resultado %s";

    //Severidad alerta 1 - Mensaje | 2 - Advertencia | 3 - Fallo
    public static final int SEVERIDAD_MENSAJE = 1;
    public static final int SEVERIDAD_ADVERTRNCIA = 2;
    public static final int SEVERIDAD_FALLO = 3;
    //estado alertas 1 - Generado | 2 - Leido | 0 - Descartado
    public static final int ESTADO_GENERADO = 1;
    
    public final static String VIGENTE = "01";
    //Formato de fecha
    public final static SimpleDateFormat formato1 = new SimpleDateFormat("dd/MM/yyyy");
    public final static SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
    public final static SimpleDateFormat formato3 = new SimpleDateFormat("dd/MM/yy");
    public final static SimpleDateFormat formato4 = new SimpleDateFormat("YYYYMMddHHmmssSSS");
    public final static SimpleDateFormat formato5 = new SimpleDateFormat("dd/MM/yyyy");
    public final static SimpleDateFormat formato6 = new SimpleDateFormat("YYYYMMddHHmmss");
    public final static SimpleDateFormat formato7 = new SimpleDateFormat("YYYYMMdd");
    //Codigo Tipo de dato
    public static final int TIPO_DATO_TEXTO = 0;
    public static final int TIPO_DATO_NUMERICO = 1;
    public static final int TIPO_DATO_FECHA = 2;
    public static final int TIPO_DATO_BOOLEAN = 3;
    public static final int TIPO_DATO_LISTA = 4;
    public static final int TIPO_DATO_TERCERO = 5;
    public static final int TIPO_DATO_TEXTO_LARGO = 6;
    public static final int TIPO_DATO_CAMPO_REFERENCIADO = 7;
    public static final int TIPO_DATO_USUARIO = 8;
    public static final int TIPO_DATO_MONEDA = 9;
    public static final int TIPO_DATO_FIRMA = 10;
    //Nombre Tipo de datos
    public static final String TIPO_DATO_TEXTO_STR = "Texto";
    public static final String TIPO_DATO_NUMERICO_STR = "Numérico";
    public static final String TIPO_DATO_FECHA_STR = "Fecha";
    public static final String TIPO_DATO_BOOLEAN_STR = "Booleano";
    public static final String TIPO_DATO_LISTA_STR = "Lista desplegable";
    public static final String TIPO_DATO_TERCERO_STR = "Tercero";
    public static final String TIPO_DATO_TEXTO_LARGO_STR = "Texto largo";
    public static final String TIPO_DATO_CAMPO_REFERENCIADO_STR = "Referenciado";
    public static final String TIPO_DATO_USUARIO_STR = "Usuario";
    public static final String TIPO_DATO_MONEDA_STR = "Moneda";
    public static final String TIPO_DATO_FIRMA_STR = "Firma";
    //Tipo estado - 0.Inicio | 1.Manual | 2. Automatico | 3.Comite | 4.Final
    public static final int TIPO_INICIO = 0;
    public static final int TIPO_MANUAL = 1;
    public static final int TIPO_AUTOMATICO = 2;
    public static final int TIPO_COMITE = 3;
    public static final int TIPO_FINAL = 4;
    public static final int TIPO_CONTRATO = 5;
    public static final int TIPO_OTROSI = 6;
    public static final int TIPO_LINEA = 7;
    public static final int TIPO_LINEA_APROBADA = 8;
    public static final int TIPO_LINEA_RECHAZADA = 9;
    public static final int TIPO_FIRMA_MANUAL = 10;
    //NOMBRE TIPO ESTADO
    public static final String TIPO_INICIO_STR = "Inicio";
    public static final String TIPO_MANUAL_STR = "Manual";
    public static final String TIPO_AUTOMATICO_STR = "Automático";
    public static final String TIPO_COMITE_STR = "Comite";
    public static final String TIPO_FINAL_STR = "Final";
    public static final String TIPO_CONTRATO_STR = "Contrato";
    public static final String TIPO_OTROSI_STR = "Otro si";
    public static final String TIPO_LINEA_STR = "Linea";
    public static final String TIPO_LINEA_APROBADA_STR = "Linea Aprobada";
    public static final String TIPO_LINEA_RECHAZADA_STR = "Linea Rechazada";
    public static final String TIPO_FIRMA_MANUAL_STR = "Firma Manual";
    //cONSTANTES 
    public static final String ID_PROCESO = "id_proceso";
    public static final String ID_TERCERO = "id_tercero";
    public static final String ID_COMITE = "id_comite";
    public static final String ID_CONTRATO = "id_contrato";
    public static final String ID_EXPEDIENTE = "id_expediente";
    //Ejecucion estado grupos    
    public static final int TIPO_EJECUCION_NO = 0;
    public static final int TIPO_EJECUCION_SI = 1;  
    public static final String TIPO_EJECUCION_NO_STR = "No";
    public static final String TIPO_EJECUCION_SI_STR = "Si";
    //Tipo seleccion usuario
    public static final int SELECCION_CREAR = 0;
    public static final int SELECCION_EDITAR = 1;  
    //tipos de terceros - 1 - Prestador | 2 - Proveedor | 3 - Supervisor
    public static final int TIPO_PRESTADOR = 1;
    public static final int TIPO_PROVEEDOR = 2;
    public static final int TIPO_SUPERVISOR = 3;
    public static final String TIPO_PRESTADOR_STR = "Prestador";
    public static final String TIPO_PROVEEDOR_STR = "Proveedor";
    public static final String TIPO_SUPERVISOR_STR = "Supervisor";
    //tipos naturalezas - 1. 1 PERSONA NATURAL | 2. 2 PERSONA JURÍDICA | 3. 3 P JURÍDICA - UNIÓN TEMPORAL o CONSORCIO
    public static final int TIPO_PERSONA_NATURAL = 1;
    public static final int TIPO_PERSONA_JURIDICA = 2;
    public static final int TIPO_PERSONA_JURIDICA_UNION_CONSORCIO = 3;
    public static final String TIPO_PERSONA_NATURAL_STR = "Persona Natural";
    public static final String TIPO_PERSONA_JURIDICA_STR = "Persona Jurídica";
    public static final String TIPO_PERSONA_JURIDICA_UNION_CONSORCIO_STR = "Persona Jurídica - Unión Temporal o Consorcio";
    //Etapa designacion 1. Precontractual | 2. Contractual
    public static final int TIPO_ETAPA_PRECONTRACTUAL = 1;
    public static final int TIPO_ETAPA_CONTRACTUAL = 2;
    public static final String TIPO_ETAPA_PRECONTRACTUAL_STR = "Precontractual";
    public static final String TIPO_ETAPA_CONTRACTUAL_STR = "Contractual";
    
    // estados comité 0 - Creado | 1- Habierto | 2 - Cerrado | 3 - En ejecucioon | 4 - En receso | 5 - Cancelado | 6 - Finalizado
    public static final int COMITE_CREADO = 0;
    public static final int COMITE_ABIERTO = 1;
    public static final int COMITE_CERRADO = 2;
    public static final int COMITE_EN_EJECUCION = 3;
    public static final int COMITE_EN_RECESO = 4;
    public static final int COMITE_CANCELADO = 5;
    public static final int COMITE_FINALIZADO = 6;
    public static final String COMITE_CREADO_STR = "Creado";
    public static final String COMITE_ABIERTO_STR = "Recepción de Línea Abierta";
    public static final String COMITE_CERRADO_STR = "Recepción de Línea Cerrada";
    public static final String COMITE_EN_EJECUCION_STR = "En Ejecución";
    public static final String COMITE_EN_RECESO_STR = "En Receso";
    public static final String COMITE_CANCELADO_STR = "Cancelado";
    public static final String COMITE_FINALIZADO_STR = "Finalizado";
    //Tipos de lineas  1 Contrato Nuevo | 2 Ivitaciones | 3 Contrato vigente | 4 Otrosi (modificación) | 5 Otrosi (adición) | 6 Otrosi (prórroga) | 7 Otrosi (adición-prórroga) | 8 Adición Códigos | 9 Ordenes de compra | 10 Otros Temas
    public static final int LINEA_CONTRATO_NUEVO = 1;
    public static final int LINEA_INVITACION = 2;
    public static final int LINEA_CONTRATO_VIGENTE = 3;
    public static final int LINEA_OTRO_SI_MODIFICACION = 4;
    public static final int LINEA_OTRO_SI_ADICICION = 5;
    public static final int LINEA_OTRO_SI_PRORROGA = 6;
    public static final int LINEA_OTRO_SI_ADICION_PRORROGA = 7;
    public static final int LINEA_ADICION_CODIGO = 8;
    public static final int LINEA_ORDEN_COMPRA = 9;
    public static final int LINEA_OTROS_TEMAS = 10;
    public static final String LINEA_CONTRATO_NUEVO_STR = "Contrato nuevo";
    public static final String LINEA_INVITACION_STR = "Invitaciones";
    public static final String LINEA_CONTRATO_VIGENTE_STR = "Contrato vigente";
    public static final String LINEA_OTRO_SI_MODIFICACION_STR = "Otrosi (modificación)";
    public static final String LINEA_OTRO_SI_ADICICION_STR = "Otrosi (adición)";
    public static final String LINEA_OTRO_SI_PRORROGA_STR = "Otrosi (prórroga)";
    public static final String LINEA_OTRO_SI_ADICION_PRORROGA_STR = "Otrosi (adición-prórroga)";
    public static final String LINEA_ADICION_CODIGO_STR = "Adición Códigos";
    public static final String LINEA_ORDEN_COMPRA_STR = "Ordenes de compra";
    public static final String LINEA_OTROS_TEMAS_STR = "Otros Temas";
    //Estados lineas 0 - Registrado | 1 - Aprobado | 2 - Rechazado | 3 - Pospuesto
    public static final int ESTADO_LINEA_REGISTRADO = 0;
    public static final int ESTADO_LINEA_APROBADO = 1;
    public static final int ESTADO_LINEA_RECHAZADO = 2;
    public static final int ESTADO_LINEA_POSPUESTO = 3;
    public static final String ESTADO_LINEA_REGISTRADO_STR = "Registrado";
    public static final String ESTADO_LINEA_APROBADO_STR = "Aprobado";
    public static final String ESTADO_LINEA_RECHAZADO_STR = "Rechazado";
    public static final String ESTADO_LINEA_POSPUESTO_STR = "Pospuesto";
    //Constantes para nombre de archivo adjuntos 
    public static final String NOMBRE_ADJUNTO_LINEA="adjunto_linea_";
    public static final String NOMBRE_ADJUNTO_OTROSI="adjunto_otrosi_";
    public static final String NOMBRE_ADJUNTO_INFORME="adjunto_informe_";
    public static final String NOMBRE_ADJUNTO_EXPEDIENTE="adjunto_expediente_";
    //tipos otrosi 1 -> Prórroga| 2 -> Adición| 3 -> Modificación| 4 -> Prórroga y Adición| 5-> Prórroga, Adición y Modificación| 6-> Prórroga y Modificación| 7-> Adición y Modificación
    public static final int OTROSI_PRORROGA = 1;
    public static final int OTROSI_ADICION = 2;
    public static final int OTROSI_MODIFICACION = 3;
    public static final int OTROSI_PRORROGA_ADICION = 4;
    public static final int OTROSI_PRORROGA_ADICION_MODIFICACION = 5;
    public static final int OTROSI_PRORROGA_MODIFICACION = 6;
    public static final int OTROSI_ADICION_MODIFICACION = 7;
    public static final int OTROSI_ACTA_INICIO = 8;
    public static final String OTROSI_PRORROGA_STR = "Prórroga";
    public static final String OTROSI_ADICION_STR = "Adición";
    public static final String OTROSI_MODIFICACION_STR = "Modificación";
    public static final String OTROSI_PRORROGA_ADICION_STR = "Prórroga y Adición";
    public static final String OTROSI_PRORROGA_ADICION_MODIFICACION_STR = "Prórroga, Adición y Modificación";
    public static final String OTROSI_PRORROGA_MODIFICACION_STR = "Prórroga y Modificación";
    public static final String OTROSI_ADICION_MODIFICACION_STR = "Adición y Modificación";
    public static final String OTROSI_ACTA_INICIO_STR = "Acta de inicio";
    //Estado otrosi 0.No vigente 1.Vigente
    public static final int OTROSI_NOVIGENTE = 0;
    public static final int OTROSI_VIGENTE = 1;
    public static final String OTROSI_NOVIGENTE_STR = "No vigente";
    public static final String OTROSI_VIGENTE_STR = "Vigente";
    //Estado garantia 1. Vigente |  2. Vencida
    public static final int GARANTIA_CONTRATO_VIGENTE = 1;
    public static final int GARANTIA_CONTRATO_VENCIDA = 2;
    public static final String GARANTIA_CONTRATO_VIGENTE_STR = "Vigente";
    public static final String GARANTIA_CONTRATO_VENCIDA_STR = "Vencida";
    //tipo de inidicadores contrato 1. Financieros |  2. PQ - Tutelas | 3. Técnicos
    public static final int INDICADOR_CONTRATO_FINANCIERO = 1;
    public static final int INDICADOR_CONTRATO_TUTELAS = 2;
    public static final int INDICADOR_CONTRATO_TECNICO = 3;
    public static final String INDICADOR_CONTRATO_FINANCIERO_STR = "Financieros";
    public static final String INDICADOR_CONTRATO_TUTELAS_STR = "PQ - Tutelas";
    public static final String INDICADOR_CONTRATO_TECNICO_STR = "Técnicos";
    //estado legalizacion 0. Sin Legalizar | 1. Legalizado
    public static final int ESTADO_LEGALIZACION_SINLEGALIZAR = 0;
    public static final int ESTADO_LEGALIZACION_LEGALIZADO = 1;
    public static final String ESTADO_LEGALIZACION_SINLEGALIZAR_STR = "Sin Legalizar";
    public static final String ESTADO_LEGALIZACION_LEGALIZADO_STR = "Legalizado";
    //Complejidad contrato 1. Baja | 2. Mediana | 3. Alta | 4. Baja y Mediana | 5. Mediana y Alta | 3. Baja, Mediana y Alta
    public static final int COMPLEJIDAD_CONTRATO_BAJA = 1;
    public static final int COMPLEJIDAD_CONTRATO_MEDIA = 2;
    public static final int COMPLEJIDAD_CONTRATO_ALTA = 3;
    public static final int COMPLEJIDAD_CONTRATO_BAJA_MEDIA = 4;
    public static final int COMPLEJIDAD_CONTRATO_MEDIA_ALTA = 5;
    public static final int COMPLEJIDAD_CONTRATO_BAJA_MEDIA_ALTA = 6;
    public static final String COMPLEJIDAD_CONTRATO_BAJA_STR = "Baja";
    public static final String COMPLEJIDAD_CONTRATO_MEDIA_STR = "Mediana";
    public static final String COMPLEJIDAD_CONTRATO_ALTA_STR = "Alta";
    public static final String COMPLEJIDAD_CONTRATO_BAJA_MEDIA_STR = "Baja y Mediana";
    public static final String COMPLEJIDAD_CONTRATO_MEDIA_ALTA_STR = "Mediana y Alta";
    public static final String COMPLEJIDAD_CONTRATO_BAJA_MEDIA_ALTA_STR = "Baja, Mediana y Alta";
    //forma de pago 1. Mensual Variable | 2. Mensual Fijo
    public static final int FORMA_PAGO_MENSUAL_VARIABLE = 1;
    public static final int FORMA_PAGO_MENSUAL_FIJO = 2;
    public static final String FORMA_PAGO_MENSUAL_VARIABLE_STR = "Mensual Variable";
    public static final String FORMA_PAGO_MENSUAL_FIJO_STR = "Mensual Fijo";
    //Tipo anticipo 1. Pago Anticipado | 2. No Pactados
    public static final int TIPO_ANTICIPO_ANTICIPADO = 1;
    public static final int TIPO_ANTICIPO_NO_PACTADO = 2;
    public static final int TIPO_ANTICIPADO = 3;
    public static final String TIPO_ANTICIPO_ANTICIPADO_STR = "Pago Anticipado";
    public static final String TIPO_ANTICIPO_NO_PACTADO_STR = "No Pactados";
    public static final String TIPO_ANTICIPADO_STR = "Anticipo";
    // Tipo  gasto 1-Salud 2-Administrativo
    public static final int TIPO_GASTO_SALUD = 1;
    public static final int TIPO_GASTO_ADMINISTRATIVO = 2;
    public static final String TIPO_GASTO_SALUD_STR = "Salud";
    public static final String TIPO_GASTO_ADMINISTRATIVO_STR = "Administrativo";
    // Tipo informe 1-Ordinario 2-Extraordinario 3-Último Informe
    public static final int TIPO_INFORME_ORDINARIO = 1;
    public static final int TIPO_INFORME_EXTRAORDINARIO = 2;
    public static final int TIPO_INFORME_FINAL = 3;
    public static final String TIPO_INFORME_ORDINARIO_STR = "Ordinario";
    public static final String TIPO_INFORME_EXTRAORDINARIO_STR = "Extraordinario";
    public static final String TIPO_INFORME_FINAL_STR = "Último Informe";
    //Tipo proceso
    public static final int TIPO_PROCESO_SALUD = 1;
    public static final int TIPO_PROCESO_ADMINISTRATIVO = 2;
    public static final int TIPO_PROCESO_OTROSI = 3;
    public static final int TIPO_PROCESO_INFORMES = 4;
    public static final String TIPO_PROCESO_SALUD_STR = "Salud";
    public static final String TIPO_PROCESO_ADMINISTRATIVO_STR = "Administrativo";
    public static final String TIPO_PROCESO_OTROSI_STR = "Otrosí";
    public static final String TIPO_PROCESO_INFORMES_STR = "Informes";
    //estado informe 1: Generado | 2: Aprobado | 3: Devuelto
    public static final int ESTADO_INFORME_GENERADO = 1;
    public static final int ESTADO_INFORME_APROBADO = 2;
    public static final int ESTADO_INFORME_DEVUELTO = 3;
    public static final String ESTADO_INFORME_GENERADO_STR = "Generado";
    public static final String ESTADO_INFORME_APROBADO_STR = "Aprobado";
    public static final String ESTADO_INFORME_DEVUELTO_STR = "Devuelto";
    
    public static final String OBSERVACION_CREACION_EXPEDIENTE = "Creación de expediente.";
    public static final String NOMBRE_CREACION_EXPEDIENTE = "¡Gestión de expediente!";
    public static final String MENSAJE_CREACION_EXPEDIENTE = "El Expediente: %s cambio al estado: %s por el usuario: %s, fecha de cambio: %s, observacion: %s y requiere de su Gestión.";
    //tipo documento 0 Generado | 1 Digitalizado | 2 Mixto
    public static final int TIPO_DOCUMENTO_GENERADO = 0;
    public static final int TIPO_DOCUMENTO_DIGITALIZADO = 1;
    public static final int TIPO_DOCUMENTO_MIXTO = 2;
    public static final String TIPO_DOCUMENTO_GENERADO_STR = "Generado";
    public static final String TIPO_DOCUMENTO_DIGITALIZADO_STR = "Digitalizado";
    public static final String TIPO_DOCUMENTO_MIXTO_STR = "Mixto";
    
    public static final String ESTILO_DOCUMENTO = "<style> table { width: 100%; border-collapse: collapse; border: 1px solid #e1e1e1; }  th, td { border: 1px solid black; padding: 5px; text-align: left; } </style>  ";
    
    //0 PRECONTRACTUAL | 1 CONTRACTUAL | 2 POSTCONTRACTUAL
    public static final int ESTAPA_PRECONTRACTUAL = 0;
    public static final int ESTAPA_CONTRACTUAL = 1;
    public static final int ESTAPA_POSTCONTRACTUAL = 2;
    public static final String ESTAPA_PRECONTRACTUAL_STR = "Precontractual";
    public static final String ESTAPA_CONTRACTUAL_STR = "Contractual";
    public static final String ESTAPA_POSTCONTRACTUAL_STR = "Postcontractual";
    // Periodicidad seguimientos contratos 1: Mensual | 2: Bimestral | 3: Semestral
    public static final int PERIODO_MENSUAL = 1;
    public static final int PERIODO_TRIMESTRAL = 2;
    public static final String PERIODO_MENSUAL_STR = "Mensual";
    public static final String PERIODO_TRIMESTRAL_STR = "Trimestral";
    //Valores referencia terceros
    public static final String VALOR_REF_NATURALEZ_JURIDICA = "naturaleza_juridica";
    public static final String VALOR_REF_TIPO_DOCUMENTO = "tipo_documento";
    public static final String VALOR_REF_NUMERO_DOCUMENTO = "numero_documento";
    public static final String VALOR_REF_RAZON_SOCIAL = "razon_social";
    public static final String VALOR_REF_TIPO_DOC_REPRESENTANTE= "tipo_documento_representante_legal";
    public static final String VALOR_REF_NUMERO_DOC_REPRESENTANTE= "numero_documento_representante_legal";
    public static final String VALOR_REF_NOMBRE_REPRESENTANTE= "nombre_representante_legal";
    public static final String VALOR_REF_CODIGO_HABILITACION= "codigo_habilitacion";
    public static final String VALOR_REF_DIRECCION= "direccion";
    public static final String VALOR_REF_EMAIL= "correo_electronico";
    public static final String VALOR_REF_TELEFONO= "telefono";
    public static final String VALOR_REF_CARGO= "cargo";
    public static final String VALOR_REF_AREA= "area";
    public static final String VALOR_REF_UNION_TEMPORAL= "Unión Temporal";
    //valor referencias usuarios
    public static final String VALOR_REF_USUARIO_NOMBRE = "nombre";
    public static final String VALOR_REF_USUARIO_EMAIL = "correo_electronico";
    public static final String VALOR_REF_USUARIO_TIPO_DOCUMENTO_COD = "tipo_documento_codigo";
    public static final String VALOR_REF_USUARIO_TIPO_DOCUMENTO_VAL = "tipo_documento_valor";
    public static final String VALOR_REF_USUARIO_DOCUMENTO = "documento";
    public static final String VALOR_REF_USUARIO_AREA = "area";
    public static final String VALOR_REF_USUARIO_CARGO = "cargo";
    //Valores rol deL responsable
    public static final int ROL_PROPIETARIO = 1;
    public static final int ROL_RESPONSABLE = 2;
    public static final String ROL_PROPIETARIO_STR= "Propietario";
    public static final String ROL_RESPONSABLE_STR= "Responsable";
    // estado legalizacion otrosies 0. SIN LEGALIZAR | 1. LEGALIZADO
    public static final int ESTADO_SIN_LEGALIZAR = 0;
    public static final int ESTADO_LEGALIZADO = 1;
    public static final String ESTADO_SIN_LEGALIZAR_STR = "Sin legalizar";
    public static final String ESTADO_LEGALIZADO_STR = "Legalizado";
    
    
    public static String getEstadoLegalizacionOtrosiStr(Integer id) {
        if (id == null) {
            return TEXTO_VACIO;
        }
        switch (id) {
            case ESTADO_SIN_LEGALIZAR:
                return ESTADO_SIN_LEGALIZAR_STR;
            case ESTADO_LEGALIZADO:
                return ESTADO_LEGALIZADO_STR;            
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static List<Maestro> getListaEstadoLegalizacionOtrosi() {
        List<Maestro> lista = new ArrayList();
        int contador = 0;
        while (contador < 2) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case ESTADO_SIN_LEGALIZAR:
                    mae.setNombre(ESTADO_SIN_LEGALIZAR_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;
                case ESTADO_LEGALIZADO:
                    mae.setNombre(ESTADO_LEGALIZADO_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;
                                
            }
            contador++;
        }
        return lista;
    }
    
    
    public static String getRolStr(Integer id) {
        if (id == null) {
            return TEXTO_VACIO;
        }
        switch (id) {
            case ROL_PROPIETARIO:
                return ROL_PROPIETARIO_STR;
            case ROL_RESPONSABLE:
                return ROL_RESPONSABLE_STR;            
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static List<Maestro> getListaRol() {
        List<Maestro> lista = new ArrayList();
        int contador = 1;
        while (contador < 3) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case ROL_PROPIETARIO:
                    mae.setNombre(ROL_PROPIETARIO_STR);
                    mae.setDescripcion(ROL_PROPIETARIO_STR);
                    lista.add(mae);
                    break;
                case ROL_RESPONSABLE:
                    mae.setNombre(ROL_RESPONSABLE_STR);
                    mae.setDescripcion(ROL_RESPONSABLE_STR);
                    lista.add(mae);
                    break;                 
            }
            contador++;
        }
        return lista;
    }
    
    
    public static List<Maestro> getValoresRefTerceros() {
        List<Maestro> lista = new ArrayList();
        int contador = 1;
        while (contador < 15) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case 1:
                    mae.setNombre(VALOR_REF_NATURALEZ_JURIDICA);
                    mae.setDescripcion(VALOR_REF_NATURALEZ_JURIDICA);
                    lista.add(mae);
                    break;
                case 2:
                    mae.setNombre(VALOR_REF_TIPO_DOCUMENTO);
                    mae.setDescripcion(VALOR_REF_TIPO_DOCUMENTO);
                    lista.add(mae);
                    break; 
                case 3:
                    mae.setNombre(VALOR_REF_NUMERO_DOCUMENTO);
                    mae.setDescripcion(VALOR_REF_NUMERO_DOCUMENTO);
                    lista.add(mae);
                    break; 
                case 4:
                    mae.setNombre(VALOR_REF_RAZON_SOCIAL);
                    mae.setDescripcion(VALOR_REF_RAZON_SOCIAL);
                    lista.add(mae);
                    break; 
                case 5:
                    mae.setNombre(VALOR_REF_TIPO_DOC_REPRESENTANTE);
                    mae.setDescripcion(VALOR_REF_TIPO_DOC_REPRESENTANTE);
                    lista.add(mae);
                    break; 
                case 6:
                    mae.setNombre(VALOR_REF_NUMERO_DOC_REPRESENTANTE);
                    mae.setDescripcion(VALOR_REF_NUMERO_DOC_REPRESENTANTE);
                    lista.add(mae);
                    break; 
                case 7:
                    mae.setNombre(VALOR_REF_NOMBRE_REPRESENTANTE);
                    mae.setDescripcion(VALOR_REF_NOMBRE_REPRESENTANTE);
                    lista.add(mae);
                    break; 
                case 8:
                    mae.setNombre(VALOR_REF_CODIGO_HABILITACION);
                    mae.setDescripcion(VALOR_REF_CODIGO_HABILITACION);
                    lista.add(mae);
                    break; 
                case 9:
                    mae.setNombre(VALOR_REF_DIRECCION);
                    mae.setDescripcion(VALOR_REF_DIRECCION);
                    lista.add(mae);
                    break; 
                case 10:
                    mae.setNombre(VALOR_REF_EMAIL);
                    mae.setDescripcion(VALOR_REF_EMAIL);
                    lista.add(mae);
                    break; 
                case 11:
                    mae.setNombre(VALOR_REF_TELEFONO);
                    mae.setDescripcion(VALOR_REF_TELEFONO);
                    lista.add(mae);
                    break; 
                case 12:
                    mae.setNombre(VALOR_REF_CARGO);
                    mae.setDescripcion(VALOR_REF_CARGO);
                    lista.add(mae);
                    break; 
                case 13:
                    mae.setNombre(VALOR_REF_AREA);
                    mae.setDescripcion(VALOR_REF_AREA);
                    lista.add(mae);
                    break; 
                case 14:
                    mae.setNombre(VALOR_REF_UNION_TEMPORAL);
                    mae.setDescripcion(VALOR_REF_UNION_TEMPORAL);
                    lista.add(mae);
                    break; 
            }
            contador++;
        }
        return lista;
    }
    
    public static List<Maestro> getValoresRefUsuario(){
        List<Maestro> lista = new ArrayList();
        int contador = 1;
        while (contador < 14) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case 1:
                    mae.setNombre(VALOR_REF_USUARIO_NOMBRE);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;
                case 2:
                    mae.setNombre(VALOR_REF_USUARIO_EMAIL);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;
                case 3:
                    mae.setNombre(VALOR_REF_USUARIO_TIPO_DOCUMENTO_COD);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;
                case 4:
                    mae.setNombre(VALOR_REF_USUARIO_TIPO_DOCUMENTO_VAL);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;
                case 5:
                    mae.setNombre(VALOR_REF_USUARIO_DOCUMENTO);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;
                case 6:
                    mae.setNombre(VALOR_REF_USUARIO_AREA);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;
                case 7:
                    mae.setNombre(VALOR_REF_USUARIO_CARGO);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;
                
            }
            contador++;
        }
        return lista;
    }
    
    public static String getPeriodoSeguimiento(Integer id) {
        if (id == null) {
            return TEXTO_VACIO;
        }
        switch (id) {
            case PERIODO_MENSUAL:
                return PERIODO_MENSUAL_STR;
            case PERIODO_TRIMESTRAL:
                return PERIODO_TRIMESTRAL_STR;
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static List<Maestro> getMaestroperiodoSeguimiento() {
        List<Maestro> lista = new ArrayList();
        int contador = 1;
        while (contador < 3) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case PERIODO_MENSUAL:
                    mae.setNombre(PERIODO_MENSUAL_STR);
                    mae.setDescripcion(PERIODO_MENSUAL_STR);
                    lista.add(mae);
                    break;
                case PERIODO_TRIMESTRAL:
                    mae.setNombre(PERIODO_TRIMESTRAL_STR);
                    mae.setDescripcion(PERIODO_TRIMESTRAL_STR);
                    lista.add(mae);
                    break;  
            }
            contador++;
        }
        return lista;
    }
    
     public static String getEtapaDeDesignacion(Integer id) {
        if (id == null) {
            return TEXTO_VACIO;
        }
        switch (id) {
            case ESTAPA_PRECONTRACTUAL:
                return ESTAPA_PRECONTRACTUAL_STR;
            case ESTAPA_CONTRACTUAL:
                return ESTAPA_CONTRACTUAL_STR;
            case ESTAPA_POSTCONTRACTUAL:
                return ESTAPA_POSTCONTRACTUAL_STR;
            default:
                return TEXTO_VACIO;
        }
    }
     
    public static List<Maestro> getMaestroEtapaDesignacion() {
        List<Maestro> lista = new ArrayList();
        int contador = 0;
        while (contador < 3) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case ESTAPA_PRECONTRACTUAL:
                    mae.setNombre(ESTAPA_PRECONTRACTUAL_STR);
                    mae.setDescripcion(ESTAPA_PRECONTRACTUAL_STR);
                    lista.add(mae);
                    break;
                case ESTAPA_CONTRACTUAL:
                    mae.setNombre(ESTAPA_CONTRACTUAL_STR);
                    mae.setDescripcion(ESTAPA_CONTRACTUAL_STR);
                    lista.add(mae);
                    break; 
                case ESTAPA_POSTCONTRACTUAL:
                    mae.setNombre(ESTAPA_POSTCONTRACTUAL_STR);
                    mae.setDescripcion(ESTAPA_POSTCONTRACTUAL_STR);
                    lista.add(mae);
                    break; 
            }
            contador++;
        }
        return lista;
    }
    
    public static String getTipoDocumento(Integer id) {
        if (id == null) {
            return TEXTO_VACIO;
        }
        switch (id) {
            case TIPO_DOCUMENTO_GENERADO:
                return TIPO_DOCUMENTO_GENERADO_STR;
            case TIPO_DOCUMENTO_DIGITALIZADO:
                return TIPO_DOCUMENTO_DIGITALIZADO_STR;
            case TIPO_DOCUMENTO_MIXTO:
                return TIPO_DOCUMENTO_MIXTO_STR;
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static List<Maestro> getMaestroTipoDocumento() {
        List<Maestro> lista = new ArrayList();
        int contador = 0;
        while (contador < 3) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case TIPO_DOCUMENTO_GENERADO:
                    mae.setNombre(TIPO_DOCUMENTO_GENERADO_STR);
                    mae.setDescripcion(TIPO_DOCUMENTO_GENERADO_STR);
                    lista.add(mae);
                    break;
                case TIPO_DOCUMENTO_DIGITALIZADO:
                    mae.setNombre(TIPO_DOCUMENTO_DIGITALIZADO_STR);
                    mae.setDescripcion(TIPO_DOCUMENTO_DIGITALIZADO_STR);
                    lista.add(mae);
                    break; 
                case TIPO_DOCUMENTO_MIXTO:
                    mae.setNombre(TIPO_DOCUMENTO_MIXTO_STR);
                    mae.setDescripcion(TIPO_DOCUMENTO_MIXTO_STR);
                    lista.add(mae);
                    break; 
            }
            contador++;
        }
        return lista;
    }
    
    public static String getEstadoInforme(Integer id) {
        if (id == null) {
            return TEXTO_VACIO;
        }
        switch (id) {
            case ESTADO_INFORME_GENERADO:
                return ESTADO_INFORME_GENERADO_STR;
            case ESTADO_INFORME_APROBADO:
                return ESTADO_INFORME_APROBADO_STR;
            case ESTADO_INFORME_DEVUELTO:
                return ESTADO_INFORME_DEVUELTO_STR;
            default:
                return TEXTO_VACIO;
        }
    }     
     
    public static List<Maestro> getMaestroEstadoInforme() {
        List<Maestro> lista = new ArrayList();
        int contador = 1;
        while (contador < 4) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case ESTADO_INFORME_GENERADO:
                    mae.setNombre(ESTADO_INFORME_GENERADO_STR);
                    mae.setDescripcion(ESTADO_INFORME_GENERADO_STR);
                    lista.add(mae);
                    break;
                case ESTADO_INFORME_APROBADO:
                    mae.setNombre(ESTADO_INFORME_APROBADO_STR);
                    mae.setDescripcion(ESTADO_INFORME_APROBADO_STR);
                    lista.add(mae);
                    break; 
                case ESTADO_INFORME_DEVUELTO:
                    mae.setNombre(ESTADO_INFORME_DEVUELTO_STR);
                    mae.setDescripcion(ESTADO_INFORME_DEVUELTO_STR);
                    lista.add(mae);
                    break; 
            }
            contador++;
        }
        return lista;
    }
    
    public static String getTipoProceso(Integer id) {
        if (id == null) {
            return TEXTO_VACIO;
        }
        switch (id) {
            case TIPO_PROCESO_SALUD:
                return TIPO_PROCESO_SALUD_STR;
            case TIPO_PROCESO_ADMINISTRATIVO:
                return TIPO_PROCESO_ADMINISTRATIVO_STR;
            case TIPO_PROCESO_OTROSI:
                return TIPO_PROCESO_OTROSI_STR;
            case TIPO_PROCESO_INFORMES:
                return TIPO_PROCESO_INFORMES_STR;
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static List<Maestro> getMaestroTipoProceso() {
        List<Maestro> lista = new ArrayList();
        int contador = 1;
        while (contador < 5) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case TIPO_PROCESO_SALUD:
                    mae.setNombre(TIPO_PROCESO_SALUD_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;
                case TIPO_PROCESO_ADMINISTRATIVO:
                    mae.setNombre(TIPO_PROCESO_ADMINISTRATIVO_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break; 
                case TIPO_PROCESO_OTROSI:
                    mae.setNombre(TIPO_PROCESO_OTROSI_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break; 
                case TIPO_PROCESO_INFORMES:
                    mae.setNombre(TIPO_PROCESO_INFORMES_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break; 
            }
            contador++;
        }
        return lista;
    }
    
    public static boolean isAdicion(Integer id){
        boolean aplica = false;
        if(id == null){
            return false;
        }
        switch (id) {
                case CntjConstantes.OTROSI_ADICION:
                case CntjConstantes.OTROSI_PRORROGA_ADICION:
                case CntjConstantes.OTROSI_PRORROGA_ADICION_MODIFICACION:
                case CntjConstantes.OTROSI_ADICION_MODIFICACION:
                    aplica = true;
                    break;
            }
        return aplica;
    }
    
    public static boolean isProrroga(Integer id){
        boolean aplica = false;
        if(id == null){
            return false;
        }
        switch (id) {
                case CntjConstantes.OTROSI_PRORROGA:
                case CntjConstantes.OTROSI_PRORROGA_MODIFICACION:
                case CntjConstantes.OTROSI_PRORROGA_ADICION:
                case CntjConstantes.OTROSI_PRORROGA_ADICION_MODIFICACION:
                case CntjConstantes.OTROSI_ACTA_INICIO:
                    aplica = true;
                    break;
            }
        return aplica;
    }
    
    public static String getTipoInformeStr(Integer id) {
        if (id == null) {
            return TEXTO_VACIO;
        }
        switch (id) {
            case TIPO_INFORME_ORDINARIO:
                return TIPO_INFORME_ORDINARIO_STR;
            case TIPO_INFORME_EXTRAORDINARIO:
                return TIPO_INFORME_EXTRAORDINARIO_STR;
            case TIPO_INFORME_FINAL:
                return TIPO_INFORME_FINAL_STR;
            default:
                return TEXTO_VACIO;
        }
    }

    public static List<Maestro> getMaestroTipoInforme() {
        List<Maestro> lista = new ArrayList();
        int contador = 1;
        while (contador < 4) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case TIPO_INFORME_ORDINARIO:
                    mae.setNombre(TIPO_INFORME_ORDINARIO_STR);
                    mae.setDescripcion(TIPO_INFORME_ORDINARIO_STR);
                    lista.add(mae);
                    break;
                case TIPO_INFORME_EXTRAORDINARIO:
                    mae.setNombre(TIPO_INFORME_EXTRAORDINARIO_STR);
                    mae.setDescripcion(TIPO_INFORME_EXTRAORDINARIO_STR);
                    lista.add(mae);
                    break; 
                case TIPO_INFORME_FINAL:
                    mae.setNombre(TIPO_INFORME_FINAL_STR);
                    mae.setDescripcion(TIPO_INFORME_FINAL_STR);
                    lista.add(mae);
                    break; 
            }
            contador++;
        }
        return lista;
    }
        
    public static String getTipoGastoStr(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case TIPO_GASTO_SALUD:
                return TIPO_GASTO_SALUD_STR;
            case TIPO_GASTO_ADMINISTRATIVO:
                return TIPO_GASTO_ADMINISTRATIVO_STR;                     
            default:
                return TEXTO_VACIO;
        }
    }
    
    
    public static List<Maestro> getMaestroTipoGasto() {
        List<Maestro> lista = new ArrayList();
        int contador = 1;
        while (contador < 3) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case TIPO_GASTO_SALUD:
                    mae.setNombre(TIPO_GASTO_SALUD_STR);
                    mae.setDescripcion(TIPO_GASTO_SALUD_STR);
                    lista.add(mae);
                    break;
                case TIPO_GASTO_ADMINISTRATIVO:
                    mae.setNombre(TIPO_GASTO_ADMINISTRATIVO_STR);
                    mae.setDescripcion(TIPO_GASTO_ADMINISTRATIVO_STR);
                    lista.add(mae);
                    break; 
            }
            contador++;
        }
        return lista;
    }
    
    public static List<Maestro> maestroTipoAnticipoContrato() {
        List<Maestro> lista = new ArrayList();
        int contador = 1;
        while (contador < 4) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case TIPO_ANTICIPO_ANTICIPADO:
                    mae.setNombre(TIPO_ANTICIPO_ANTICIPADO_STR);
                    mae.setDescripcion(TIPO_ANTICIPO_ANTICIPADO_STR);
                    lista.add(mae);
                    break;
                case TIPO_ANTICIPO_NO_PACTADO:
                    mae.setNombre(TIPO_ANTICIPO_NO_PACTADO_STR);
                    mae.setDescripcion(TIPO_ANTICIPO_NO_PACTADO_STR);
                    lista.add(mae);
                    break; 
                case TIPO_ANTICIPADO:
                    mae.setNombre(TIPO_ANTICIPADO_STR);
                    mae.setDescripcion(TIPO_ANTICIPADO_STR);
                    lista.add(mae);
                    break; 
            }
            contador++;
        }
        return lista;
    }
    
    public static String getTipoAnticipoContratoStr(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case TIPO_ANTICIPO_ANTICIPADO:
                return TIPO_ANTICIPO_ANTICIPADO_STR;
            case TIPO_ANTICIPO_NO_PACTADO:
                return TIPO_ANTICIPO_NO_PACTADO_STR;                     
            case TIPO_ANTICIPADO:
                return TIPO_ANTICIPADO_STR;                     
            default:
                return TEXTO_VACIO;
        }
    }
    
    
    public static List<Maestro> maestroFormaDePagoContrato() {
        List<Maestro> lista = new ArrayList();
        int contador = 1;
        while (contador < 3) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case FORMA_PAGO_MENSUAL_VARIABLE:
                    mae.setNombre(FORMA_PAGO_MENSUAL_VARIABLE_STR);
                    mae.setDescripcion(FORMA_PAGO_MENSUAL_VARIABLE_STR);
                    lista.add(mae);
                    break;
                case FORMA_PAGO_MENSUAL_FIJO:
                    mae.setNombre(FORMA_PAGO_MENSUAL_FIJO_STR);
                    mae.setDescripcion(FORMA_PAGO_MENSUAL_FIJO_STR);
                    lista.add(mae);
                    break; 
            }
            contador++;
        }
        return lista;
    }
    
    public static String getFormaDePagoContratoStr(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case FORMA_PAGO_MENSUAL_VARIABLE:
                return FORMA_PAGO_MENSUAL_VARIABLE_STR;
            case FORMA_PAGO_MENSUAL_FIJO:
                return FORMA_PAGO_MENSUAL_FIJO_STR;                     
            default:
                return TEXTO_VACIO;
        }
    }
    
    
    public static List<Maestro> maestroComplejidadContrato() {
        List<Maestro> lista = new ArrayList();
        int contador = 1;
        while (contador < 7) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case COMPLEJIDAD_CONTRATO_BAJA:
                    mae.setNombre(COMPLEJIDAD_CONTRATO_BAJA_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;
                case COMPLEJIDAD_CONTRATO_MEDIA:
                    mae.setNombre(COMPLEJIDAD_CONTRATO_MEDIA_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;  
                case COMPLEJIDAD_CONTRATO_ALTA:
                    mae.setNombre(COMPLEJIDAD_CONTRATO_ALTA_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;
                case COMPLEJIDAD_CONTRATO_BAJA_MEDIA:
                    mae.setNombre(COMPLEJIDAD_CONTRATO_BAJA_MEDIA_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;  
                case COMPLEJIDAD_CONTRATO_MEDIA_ALTA:
                    mae.setNombre(COMPLEJIDAD_CONTRATO_MEDIA_ALTA_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;  
                case COMPLEJIDAD_CONTRATO_BAJA_MEDIA_ALTA:
                    mae.setNombre(COMPLEJIDAD_CONTRATO_BAJA_MEDIA_ALTA_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;  
            }
            contador++;
        }
        return lista;
    }
    
    public static String getComplejidadContratoStr(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case COMPLEJIDAD_CONTRATO_BAJA:
                return COMPLEJIDAD_CONTRATO_BAJA_STR;
            case COMPLEJIDAD_CONTRATO_MEDIA:
                return COMPLEJIDAD_CONTRATO_MEDIA_STR;                     
            case COMPLEJIDAD_CONTRATO_ALTA:
                return COMPLEJIDAD_CONTRATO_ALTA_STR;                     
            case COMPLEJIDAD_CONTRATO_BAJA_MEDIA:
                return COMPLEJIDAD_CONTRATO_BAJA_MEDIA_STR;                     
            case COMPLEJIDAD_CONTRATO_MEDIA_ALTA:
                return COMPLEJIDAD_CONTRATO_MEDIA_ALTA_STR;                     
            case COMPLEJIDAD_CONTRATO_BAJA_MEDIA_ALTA:
                return COMPLEJIDAD_CONTRATO_BAJA_MEDIA_ALTA_STR;                     
            default:
                return TEXTO_VACIO;
        }
    }
    
    
    public static List<Maestro> maestroEstadoLegalizacionContrato() {
        List<Maestro> lista = new ArrayList();
        int contador = 0;
        while (contador < 2) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case ESTADO_LEGALIZACION_SINLEGALIZAR:
                    mae.setNombre(ESTADO_LEGALIZACION_SINLEGALIZAR_STR);
                    mae.setDescripcion(ESTADO_LEGALIZACION_SINLEGALIZAR_STR);
                    lista.add(mae);
                    break;
                case ESTADO_LEGALIZACION_LEGALIZADO:
                    mae.setNombre(ESTADO_LEGALIZACION_LEGALIZADO_STR);
                    mae.setDescripcion(ESTADO_LEGALIZACION_LEGALIZADO_STR);
                    lista.add(mae);
                    break;  
            }
            contador++;
        }
        return lista;
    }
    
    public static String getEstadoLegalizacionContratoStr(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case ESTADO_LEGALIZACION_SINLEGALIZAR:
                return ESTADO_LEGALIZACION_SINLEGALIZAR_STR;
            case ESTADO_LEGALIZACION_LEGALIZADO:
                return ESTADO_LEGALIZACION_LEGALIZADO_STR;                     
            default:
                return TEXTO_VACIO;
        }
    }
    
    
    public static List<Maestro> maestroIndicadorContrato() {
        List<Maestro> lista = new ArrayList();
        int contador = 0;
        while (contador < 4) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case INDICADOR_CONTRATO_FINANCIERO:
                    mae.setNombre(INDICADOR_CONTRATO_FINANCIERO_STR);
                    mae.setDescripcion(INDICADOR_CONTRATO_FINANCIERO_STR);
                    lista.add(mae);
                    break;
                case INDICADOR_CONTRATO_TUTELAS:
                    mae.setNombre(INDICADOR_CONTRATO_TUTELAS_STR);
                    mae.setDescripcion(INDICADOR_CONTRATO_TUTELAS_STR);
                    lista.add(mae);
                    break;  
                case INDICADOR_CONTRATO_TECNICO:
                    mae.setNombre(INDICADOR_CONTRATO_TECNICO_STR);
                    mae.setDescripcion(INDICADOR_CONTRATO_TECNICO_STR);
                    lista.add(mae);
                    break;  
            }
            contador++;
        }
        return lista;
    }
    
    public static String getIndicadorContratoStr(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case INDICADOR_CONTRATO_FINANCIERO:
                return INDICADOR_CONTRATO_FINANCIERO_STR;
            case INDICADOR_CONTRATO_TUTELAS:
                return INDICADOR_CONTRATO_TUTELAS_STR;                    
            case INDICADOR_CONTRATO_TECNICO:
                return INDICADOR_CONTRATO_TECNICO_STR;                    
            default:
                return TEXTO_VACIO;
        }
    }
    
    
    public static String getGarantiaContratoStr(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case GARANTIA_CONTRATO_VIGENTE:
                return GARANTIA_CONTRATO_VIGENTE_STR;
            case GARANTIA_CONTRATO_VENCIDA:
                return GARANTIA_CONTRATO_VENCIDA_STR;                    
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static List<Maestro> listaEstadoOtrosi() {
        List<Maestro> lista = new ArrayList();
        int contador = 0;
        while (contador < 2) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case OTROSI_NOVIGENTE:
                    mae.setNombre(OTROSI_NOVIGENTE_STR);
                    mae.setDescripcion(OTROSI_NOVIGENTE_STR);
                    lista.add(mae);
                    break;
                case OTROSI_VIGENTE:
                    mae.setNombre(OTROSI_VIGENTE_STR);
                    mae.setDescripcion(OTROSI_VIGENTE_STR);
                    lista.add(mae);
                    break;  
            }
            contador++;
        }
        return lista;
    }
    
    public static String getEstadoOtrosiStr(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case OTROSI_NOVIGENTE:
                return OTROSI_NOVIGENTE_STR;
            case OTROSI_VIGENTE:
                return OTROSI_VIGENTE_STR;                    
            default:
                return TEXTO_VACIO;
        }
    }
    
    
    public static List<Maestro> listaTipoOtrosi() {
        List<Maestro> lista = new ArrayList();
        int contador = 1;
        while (contador < 9) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case OTROSI_PRORROGA:
                    mae.setNombre(OTROSI_PRORROGA_STR);
                    mae.setDescripcion(OTROSI_PRORROGA_STR);
                    lista.add(mae);
                    break;
                case OTROSI_ADICION:
                    mae.setNombre(OTROSI_ADICION_STR);
                    mae.setDescripcion(OTROSI_ADICION_STR);
                    lista.add(mae);
                    break;  
                case OTROSI_MODIFICACION:
                    mae.setNombre(OTROSI_MODIFICACION_STR);
                    mae.setDescripcion(OTROSI_MODIFICACION_STR);
                    lista.add(mae);
                    break;              
                case OTROSI_PRORROGA_ADICION:
                    mae.setNombre(OTROSI_PRORROGA_ADICION_STR);
                    mae.setDescripcion(OTROSI_PRORROGA_ADICION_STR);
                    lista.add(mae);
                    break;  
                case OTROSI_PRORROGA_ADICION_MODIFICACION:
                    mae.setNombre(OTROSI_PRORROGA_ADICION_MODIFICACION_STR);
                    mae.setDescripcion(OTROSI_PRORROGA_ADICION_MODIFICACION_STR);
                    lista.add(mae);
                    break;  
                case OTROSI_PRORROGA_MODIFICACION:
                    mae.setNombre(OTROSI_PRORROGA_MODIFICACION_STR);
                    mae.setDescripcion(OTROSI_PRORROGA_MODIFICACION_STR);
                    lista.add(mae);
                    break;  
                case OTROSI_ADICION_MODIFICACION:
                    mae.setNombre(OTROSI_ADICION_MODIFICACION_STR);
                    mae.setDescripcion(OTROSI_ADICION_MODIFICACION_STR);
                    lista.add(mae);
                    break;  
                case OTROSI_ACTA_INICIO:
                    mae.setNombre(OTROSI_ACTA_INICIO_STR);
                    mae.setDescripcion(OTROSI_ACTA_INICIO_STR);
                    lista.add(mae);
                    break;  
            }
            contador++;
        }
        return lista;
    }
    
    public static String getTipoOtrosiStr(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case OTROSI_PRORROGA:
                return OTROSI_PRORROGA_STR;
            case OTROSI_ADICION:
                return OTROSI_ADICION_STR;
            case OTROSI_MODIFICACION:
                return OTROSI_MODIFICACION_STR;
            case OTROSI_PRORROGA_ADICION:
                return OTROSI_PRORROGA_ADICION_STR;    
            case OTROSI_PRORROGA_ADICION_MODIFICACION:
                return OTROSI_PRORROGA_ADICION_MODIFICACION_STR; 
            case OTROSI_PRORROGA_MODIFICACION:
                return OTROSI_PRORROGA_MODIFICACION_STR;    
            case OTROSI_ADICION_MODIFICACION:
                return OTROSI_ADICION_MODIFICACION_STR;                 
            case OTROSI_ACTA_INICIO:
                return OTROSI_ACTA_INICIO_STR;                 
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static String getEstadoLineaStr(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case ESTADO_LINEA_REGISTRADO:
                return ESTADO_LINEA_REGISTRADO_STR;
            case ESTADO_LINEA_APROBADO:
                return ESTADO_LINEA_APROBADO_STR;
            case ESTADO_LINEA_RECHAZADO:
                return ESTADO_LINEA_RECHAZADO_STR;
            case ESTADO_LINEA_POSPUESTO:
                return ESTADO_LINEA_POSPUESTO_STR;            
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static List<Maestro> listaEstadoLinea() {
        List<Maestro> lista = new ArrayList();
        int contador = 0;
        while (contador < 4) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case ESTADO_LINEA_REGISTRADO:
                    mae.setNombre(ESTADO_LINEA_REGISTRADO_STR);
                    mae.setDescripcion(ESTADO_LINEA_REGISTRADO_STR);
                    lista.add(mae);
                    break;
                case ESTADO_LINEA_APROBADO:
                    mae.setNombre(ESTADO_LINEA_APROBADO_STR);
                    mae.setDescripcion(ESTADO_LINEA_APROBADO_STR);
                    lista.add(mae);
                    break;  
                case ESTADO_LINEA_RECHAZADO:
                    mae.setNombre(ESTADO_LINEA_RECHAZADO_STR);
                    mae.setDescripcion(ESTADO_LINEA_RECHAZADO_STR);
                    lista.add(mae);
                    break;              
                case ESTADO_LINEA_POSPUESTO:
                    mae.setNombre(ESTADO_LINEA_POSPUESTO_STR);
                    mae.setDescripcion(ESTADO_LINEA_POSPUESTO_STR);
                    lista.add(mae);
                    break;  
            }
            contador++;
        }
        return lista;
    }
    
    public static List<Maestro> listaTipoLinea() {
        List<Maestro> lista = new ArrayList();
        int contador = 0;
        while (contador < 11) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case LINEA_CONTRATO_NUEVO:
                    mae.setNombre(LINEA_CONTRATO_NUEVO_STR);
                    mae.setDescripcion(LINEA_CONTRATO_NUEVO_STR);
                    lista.add(mae);
                    break;
                case LINEA_INVITACION:
                    mae.setNombre(LINEA_INVITACION_STR);
                    mae.setDescripcion(LINEA_INVITACION_STR);
                    lista.add(mae);
                    break;  
                case LINEA_CONTRATO_VIGENTE:
                    mae.setNombre(LINEA_CONTRATO_VIGENTE_STR);
                    mae.setDescripcion(LINEA_CONTRATO_VIGENTE_STR);
                    lista.add(mae);
                    break;              
                case LINEA_OTRO_SI_MODIFICACION:
                    mae.setNombre(LINEA_OTRO_SI_MODIFICACION_STR);
                    mae.setDescripcion(LINEA_OTRO_SI_MODIFICACION_STR);
                    lista.add(mae);
                    break;              
                case LINEA_OTRO_SI_ADICICION:
                    mae.setNombre(LINEA_OTRO_SI_ADICICION_STR);
                    mae.setDescripcion(LINEA_OTRO_SI_ADICICION_STR);
                    lista.add(mae);
                    break;              
                case LINEA_OTRO_SI_PRORROGA:
                    mae.setNombre(LINEA_OTRO_SI_PRORROGA_STR);
                    mae.setDescripcion(LINEA_OTRO_SI_PRORROGA_STR);
                    lista.add(mae);
                    break;              
                case LINEA_OTRO_SI_ADICION_PRORROGA:
                    mae.setNombre(LINEA_OTRO_SI_ADICION_PRORROGA_STR);
                    mae.setDescripcion(LINEA_OTRO_SI_ADICION_PRORROGA_STR);
                    lista.add(mae);
                    break;              
                case LINEA_ADICION_CODIGO:
                    mae.setNombre(LINEA_ADICION_CODIGO_STR);
                    mae.setDescripcion(LINEA_ADICION_CODIGO_STR);
                    lista.add(mae);
                    break;              
                case LINEA_ORDEN_COMPRA:
                    mae.setNombre(LINEA_ORDEN_COMPRA_STR);
                    mae.setDescripcion(LINEA_ORDEN_COMPRA_STR);
                    lista.add(mae);
                    break;              
                case LINEA_OTROS_TEMAS:
                    mae.setNombre(LINEA_OTROS_TEMAS_STR);
                    mae.setDescripcion(LINEA_OTROS_TEMAS_STR);
                    lista.add(mae);
                    break;              

            }
            contador++;
        }
        return lista;
    }
    
    public static String getTipoLineaStr(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case LINEA_CONTRATO_NUEVO:
                return LINEA_CONTRATO_NUEVO_STR;
            case LINEA_INVITACION:
                return LINEA_INVITACION_STR;
            case LINEA_CONTRATO_VIGENTE:
                return LINEA_CONTRATO_VIGENTE_STR;
            case LINEA_OTRO_SI_MODIFICACION:
                return LINEA_OTRO_SI_MODIFICACION_STR;
            case LINEA_OTRO_SI_ADICICION:
                return LINEA_OTRO_SI_ADICICION_STR;
            case LINEA_OTRO_SI_PRORROGA:
                return LINEA_OTRO_SI_PRORROGA_STR;
            case LINEA_OTRO_SI_ADICION_PRORROGA:
                return LINEA_OTRO_SI_ADICION_PRORROGA_STR;
            case LINEA_ADICION_CODIGO:
                return LINEA_ADICION_CODIGO_STR;
            case LINEA_ORDEN_COMPRA:
                return LINEA_ORDEN_COMPRA_STR;
            case LINEA_OTROS_TEMAS:
                return LINEA_OTROS_TEMAS_STR;
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static List<Maestro> listaEstadoComite() {
        List<Maestro> lista = new ArrayList();
        int contador = 0;
        while (contador < 8) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case COMITE_CREADO:
                    mae.setNombre(COMITE_CREADO_STR);
                    mae.setDescripcion(COMITE_CREADO_STR);
                    lista.add(mae);
                    break;
                case COMITE_ABIERTO:
                    mae.setNombre(COMITE_ABIERTO_STR);
                    mae.setDescripcion(COMITE_ABIERTO_STR);
                    lista.add(mae);
                    break;  
                case COMITE_CERRADO:
                    mae.setNombre(COMITE_CERRADO_STR);
                    mae.setDescripcion(COMITE_CERRADO_STR);
                    lista.add(mae);
                    break;              
                case COMITE_EN_EJECUCION:
                    mae.setNombre(COMITE_EN_EJECUCION_STR);
                    mae.setDescripcion(COMITE_EN_EJECUCION_STR);
                    lista.add(mae);
                    break;              
                case COMITE_EN_RECESO:
                    mae.setNombre(COMITE_EN_RECESO_STR);
                    mae.setDescripcion(COMITE_EN_RECESO_STR);
                    lista.add(mae);
                    break;              
                case COMITE_CANCELADO:
                    mae.setNombre(COMITE_CANCELADO_STR);
                    mae.setDescripcion(COMITE_CANCELADO_STR);
                    lista.add(mae);
                    break;              
                case COMITE_FINALIZADO:
                    mae.setNombre(COMITE_FINALIZADO_STR);
                    mae.setDescripcion(COMITE_FINALIZADO_STR);
                    lista.add(mae);
                    break;              

            }
            contador++;
        }
        return lista;
    }
    
    public static String getEstadoComiteStr(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case COMITE_CREADO:
                return COMITE_CREADO_STR;
            case COMITE_ABIERTO:
                return COMITE_ABIERTO_STR;
            case COMITE_CERRADO:
                return COMITE_CERRADO_STR;
            case COMITE_EN_EJECUCION:
                return COMITE_EN_EJECUCION_STR;
            case COMITE_EN_RECESO:
                return COMITE_EN_RECESO_STR;
            case COMITE_CANCELADO:
                return COMITE_CANCELADO_STR;
            case COMITE_FINALIZADO:
                return COMITE_FINALIZADO_STR;
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static List<Maestro> listaTipoDato() {
        List<Maestro> lista = new ArrayList();
        int contador = 0;
        while (contador < 11) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case TIPO_DATO_TEXTO:
                    mae.setNombre(TIPO_DATO_TEXTO_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;
                case TIPO_DATO_NUMERICO:
                    mae.setNombre(TIPO_DATO_NUMERICO_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;  
                case TIPO_DATO_FECHA:
                    mae.setNombre(TIPO_DATO_FECHA_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;              
                case TIPO_DATO_BOOLEAN:
                    mae.setNombre(TIPO_DATO_BOOLEAN_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;                 
                case TIPO_DATO_LISTA:
                    mae.setNombre(TIPO_DATO_LISTA_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;              
                case TIPO_DATO_TERCERO:
                    mae.setNombre(TIPO_DATO_TERCERO_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;              
                case TIPO_DATO_TEXTO_LARGO:
                    mae.setNombre(TIPO_DATO_TEXTO_LARGO_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;              
                case TIPO_DATO_CAMPO_REFERENCIADO:
                    mae.setNombre(TIPO_DATO_CAMPO_REFERENCIADO_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;              
                case TIPO_DATO_USUARIO:
                    mae.setNombre(TIPO_DATO_USUARIO_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;              
                case TIPO_DATO_MONEDA:
                    mae.setNombre(TIPO_DATO_MONEDA_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;              
                case TIPO_DATO_FIRMA:
                    mae.setNombre(TIPO_DATO_FIRMA_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;              

            }
            contador++;
        }
        return lista;
    }
    
    public static String getTipoDato(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case TIPO_DATO_TEXTO:
                return TIPO_DATO_TEXTO_STR;
            case TIPO_DATO_NUMERICO:
                return TIPO_DATO_NUMERICO_STR;
            case TIPO_DATO_FECHA:
                return TIPO_DATO_FECHA_STR;
            case TIPO_DATO_BOOLEAN:
                return TIPO_DATO_BOOLEAN_STR;
            case TIPO_DATO_LISTA:
                return TIPO_DATO_LISTA_STR;
            case TIPO_DATO_TERCERO:
                return TIPO_DATO_TERCERO_STR;
            case TIPO_DATO_TEXTO_LARGO:
                return TIPO_DATO_TEXTO_LARGO_STR;
            case TIPO_DATO_CAMPO_REFERENCIADO:
                return TIPO_DATO_CAMPO_REFERENCIADO_STR;
            case TIPO_DATO_USUARIO:
                return TIPO_DATO_USUARIO_STR;
            case TIPO_DATO_MONEDA:
                return TIPO_DATO_MONEDA_STR;
            case TIPO_DATO_FIRMA:
                return TIPO_DATO_FIRMA_STR;
            default:
                return TEXTO_VACIO;
        }
    }

    public static String setTag(String etiqueta) {
        return String.format("{{%s}}", etiqueta);
    }
    
    public static HashMap<Integer, CntjPlantilla> obtenerHashPlantillas(List<CntjPlantilla> listaPlantillas) {
        HashMap<Integer, CntjPlantilla> hash = new HashMap();
        listaPlantillas.forEach(plantilla -> {
            hash.put(plantilla.getId(), plantilla);
        });
        return hash;
    }
    
    public static HashMap<String, CntjCampo> obtenerHashCampos(List<CntjCampo> lista) {
        HashMap<String, CntjCampo> hash = new HashMap();
        lista.forEach(item -> {
            hash.put(item.getNombre(), item);
        });
        return hash;
    }
    
    public static HashMap<Integer, Maestro> obtenerHashMaestro(List<Maestro> listaMaestros) {
        HashMap<Integer, Maestro> hash = new HashMap();
        listaMaestros.forEach(maestro -> {
            hash.put(maestro.getId(), maestro);
        });
        return hash;
    }
    
    public static List<Maestro> listaTipoEstados() {
        List<Maestro> lista = new ArrayList();
        int contador = 0;
        while (contador < 11) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case TIPO_INICIO:
                    mae.setNombre(TIPO_INICIO_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;
                case TIPO_MANUAL:
                    mae.setNombre(TIPO_MANUAL_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;  
                case TIPO_AUTOMATICO:
                    mae.setNombre(TIPO_DATO_FECHA_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;              
                case TIPO_COMITE:
                    mae.setNombre(TIPO_COMITE_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;              
                case TIPO_FINAL:
                    mae.setNombre(TIPO_FINAL_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;              
                case TIPO_CONTRATO:
                    mae.setNombre(TIPO_CONTRATO_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;              
                case TIPO_OTROSI:
                    mae.setNombre(TIPO_OTROSI_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;              
                case TIPO_LINEA:
                    mae.setNombre(TIPO_LINEA_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;              
                case TIPO_LINEA_APROBADA:
                    mae.setNombre(TIPO_LINEA_APROBADA_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;              
                case TIPO_LINEA_RECHAZADA:
                    mae.setNombre(TIPO_LINEA_RECHAZADA_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;              
                case TIPO_FIRMA_MANUAL:
                    mae.setNombre(TIPO_FIRMA_MANUAL_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;              

            }
            contador++;
        }
        return lista;
    }
    
    public static String getTipoEstado(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case TIPO_INICIO:
                return TIPO_INICIO_STR;
            case TIPO_MANUAL:
                return TIPO_MANUAL_STR;
            case TIPO_AUTOMATICO:
                return TIPO_AUTOMATICO_STR;
            case TIPO_COMITE:
                return TIPO_COMITE_STR;
            case TIPO_FINAL:
                return TIPO_FINAL_STR;
            case TIPO_CONTRATO:
                return TIPO_CONTRATO_STR;
            case TIPO_OTROSI:
                return TIPO_OTROSI_STR;
            case TIPO_LINEA:
                return TIPO_LINEA_STR;
            case TIPO_LINEA_APROBADA:
                return TIPO_LINEA_APROBADA_STR;
            case TIPO_LINEA_RECHAZADA:
                return TIPO_LINEA_RECHAZADA_STR;
            case TIPO_FIRMA_MANUAL:
                return TIPO_FIRMA_MANUAL_STR;
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static List<Maestro> listaejecucion() {
        List<Maestro> lista = new ArrayList();
        int contador = 0;
        while (contador < 2) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case TIPO_EJECUCION_NO:
                    mae.setNombre(TIPO_EJECUCION_NO_STR);
                    mae.setDescripcion(TIPO_EJECUCION_NO_STR);
                    lista.add(mae);
                    break;
                case TIPO_EJECUCION_SI:
                    mae.setNombre(TIPO_EJECUCION_SI_STR);
                    mae.setDescripcion(TIPO_EJECUCION_SI_STR);
                    lista.add(mae);
                    break;  
            }
            contador++;
        }
        return lista;
    }
    
    public static List<Maestro> listaTipoTercero() {
        List<Maestro> lista = new ArrayList();
        int contador = 1;
        while (contador < 4) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case TIPO_PRESTADOR:
                    mae.setNombre(TIPO_PRESTADOR_STR);
                    mae.setDescripcion(TIPO_PRESTADOR_STR);
                    lista.add(mae);
                    break;
                case TIPO_PROVEEDOR:
                    mae.setNombre(TIPO_PROVEEDOR_STR);
                    mae.setDescripcion(TIPO_PROVEEDOR_STR);
                    lista.add(mae);
                    break;  
                case TIPO_SUPERVISOR:
                    mae.setNombre(TIPO_SUPERVISOR_STR);
                    mae.setDescripcion(TIPO_SUPERVISOR_STR);
                    lista.add(mae);
                    break;  
            }
            contador++;
        }
        return lista;
    }
    
    public static String getTipoTercero(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case TIPO_PRESTADOR:
                return TIPO_PRESTADOR_STR;
            case TIPO_PROVEEDOR:
                return TIPO_PROVEEDOR_STR;
            case TIPO_SUPERVISOR:
                return TIPO_SUPERVISOR_STR;            
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static List<Maestro> listaTipoNaturaleza() {
        List<Maestro> lista = new ArrayList();
        int contador = 1;
        while (contador < 5) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case TIPO_PERSONA_NATURAL:
                    mae.setNombre(TIPO_PERSONA_NATURAL_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;
                case TIPO_PERSONA_JURIDICA:
                    mae.setNombre(TIPO_PERSONA_JURIDICA_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;  
                case TIPO_PERSONA_JURIDICA_UNION_CONSORCIO:
                    mae.setNombre(TIPO_PERSONA_JURIDICA_UNION_CONSORCIO_STR);
                    mae.setDescripcion(mae.getNombre());
                    lista.add(mae);
                    break;   
            }
            contador++;
        }
        return lista;
    }
     
     public static String getTipoNaturaleza(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case TIPO_PERSONA_NATURAL:
                return TIPO_PERSONA_NATURAL_STR;
            case TIPO_PERSONA_JURIDICA:
                return TIPO_PERSONA_JURIDICA_STR;
            case TIPO_PERSONA_JURIDICA_UNION_CONSORCIO:
                return TIPO_PERSONA_JURIDICA_UNION_CONSORCIO_STR;                
            default:
                return TEXTO_VACIO;
        }
    }
     
     public static List<Maestro> listaEtapadesignacion() {
        List<Maestro> lista = new ArrayList();
        int contador = 1;
        while (contador < 3) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case TIPO_ETAPA_PRECONTRACTUAL:
                    mae.setNombre(TIPO_ETAPA_PRECONTRACTUAL_STR);
                    mae.setDescripcion(TIPO_ETAPA_PRECONTRACTUAL_STR);
                    lista.add(mae);
                    break;
                case TIPO_ETAPA_CONTRACTUAL:
                    mae.setNombre(TIPO_ETAPA_CONTRACTUAL_STR);
                    mae.setDescripcion(TIPO_ETAPA_CONTRACTUAL_STR);
                    lista.add(mae);
                    break;                  
            }
            contador++;
        }
        return lista;
    }
     
     public static String getEtapaDesignacion(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case TIPO_ETAPA_PRECONTRACTUAL:
                return TIPO_ETAPA_PRECONTRACTUAL_STR;
            case TIPO_ETAPA_CONTRACTUAL:
                return TIPO_ETAPA_CONTRACTUAL_STR;   
            default:
                return TEXTO_VACIO;
        }
    }

    public static void desactivarSelect(String frmCrearctipoDoc, FacesContext facesContext) {
        try {
            UIComponent component = facesContext.getViewRoot().findComponent(frmCrearctipoDoc);
            if (component != null && component instanceof SelectOneMenu) {
                SelectOneMenu selectMenu = (SelectOneMenu) component;
                // Setting editable to false programmatically
                //selectMenu.setEditable(false);
                // Setting the style to disable pointer events
                String currentStyle = (String) selectMenu.getAttributes().get("style");
                String newStyle = "pointer-events: none;";
                if (currentStyle != null) {
                    newStyle = currentStyle + " " + newStyle;
                }
                selectMenu.getAttributes().put("style", newStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void activarSelect(String frmCrearctipoDoc, FacesContext facesContext) {
        try {
            UIComponent component = facesContext.getViewRoot().findComponent(frmCrearctipoDoc);
            if (component != null && component instanceof SelectOneMenu) {
                SelectOneMenu selectMenu = (SelectOneMenu) component;
                // Setting editable to false programmatically
                //selectMenu.setEditable(true);
                // Setting the style to disable pointer events
                String currentStyle = (String) selectMenu.getAttributes().get("style");
                String newStyle = "";
                if (currentStyle != null) {
                    newStyle = currentStyle.replace("pointer-events: none;", " ");
                }
                selectMenu.getAttributes().put("style", newStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void activarSelectBooleanButton(String idcomponente, FacesContext facesContext) {
        try {
            UIComponent component = facesContext.getViewRoot().findComponent(idcomponente);
            if (component != null && component instanceof SelectBooleanButton) {
                SelectBooleanButton item = (SelectBooleanButton) component;
                // Setting editable to false programmatically
                //selectMenu.setEditable(true);
                // Setting the style to disable pointer events
                String currentStyle = (String) item.getAttributes().get("style");
                String newStyle = "";
                if (currentStyle != null) {
                    newStyle = currentStyle.replace("pointer-events: none;", " ");
                }
                item.getAttributes().put("style", newStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void desactivarInputNumber(String idcomponente, FacesContext facesContext) {
        try {
            UIComponent componente = facesContext.getViewRoot().findComponent(idcomponente);
            if (componente != null && componente instanceof InputNumber) {
                InputNumber input = (InputNumber) componente;
                input.setReadonly(true);
                
                // Setting the style to disable pointer events
                String currentStyle = (String) input.getAttributes().get("style");
                String newStyle = "pointer-events: none;";
                if (currentStyle != null) {
                    newStyle = currentStyle + " " + newStyle;
                }
                input.getAttributes().put("style", newStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void desactivarInput(String frmCrearcnuDoc, FacesContext facesContext) {
        try {
            UIComponent numDoc = facesContext.getViewRoot().findComponent(frmCrearcnuDoc);
            if (numDoc != null && numDoc instanceof InputText) {
                InputText input = (InputText) numDoc;
                input.setReadonly(true);
                
                // Setting the style to disable pointer events
                String currentStyle = (String) input.getAttributes().get("style");
                String newStyle = "pointer-events: none;";
                if (currentStyle != null) {
                    newStyle = currentStyle + " " + newStyle;
                }
                input.getAttributes().put("style", newStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void desactivarselectBooleanButton(String frmCrearcnuDoc, FacesContext facesContext) {
        try {
            UIComponent componente = facesContext.getViewRoot().findComponent(frmCrearcnuDoc);
            if (componente != null && componente instanceof SelectBooleanButton) {
                SelectBooleanButton component = (SelectBooleanButton) componente;
                component.setReadonly(true);
                
                // Setting the style to disable pointer events
                String currentStyle = (String) component.getAttributes().get("style");
                String newStyle = "pointer-events: none;";
                if (currentStyle != null) {
                    newStyle = currentStyle + " " + newStyle;
                }
                component.getAttributes().put("style", newStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void activarInputNumber(String idcomponente, FacesContext facesContext) {
        try {
            UIComponent componente = facesContext.getViewRoot().findComponent(idcomponente);
            if (componente != null && componente instanceof InputNumber) {
                InputNumber input = (InputNumber) componente;
                input.setReadonly(false);
                
                // Setting the style to disable pointer events
                String currentStyle = (String) input.getAttributes().get("style");
                String newStyle = "";
                if (currentStyle != null) {
                    newStyle = currentStyle.replace("pointer-events: none;", " ");
                }
                input.getAttributes().put("style", newStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void activarInput(String frmCrearcnuDoc, FacesContext facesContext) {
        try {
            UIComponent numDoc = facesContext.getViewRoot().findComponent(frmCrearcnuDoc);
            if (numDoc != null && numDoc instanceof InputText) {
                InputText input = (InputText) numDoc;
                input.setReadonly(false);
                
                // Setting the style to disable pointer events
                String currentStyle = (String) input.getAttributes().get("style");
                String newStyle = "";
                if (currentStyle != null) {
                    newStyle = currentStyle.replace("pointer-events: none;", " ");
                }
                input.getAttributes().put("style", newStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static String quitarTildes(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                         .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }
    
    public static int getIdNaturalezaValor(String textoRecibido) {
        System.err.println("razon social recibida: " + textoRecibido);
        List<String> valores = Arrays.asList(TIPO_PERSONA_NATURAL_STR,TIPO_PERSONA_JURIDICA_STR,TIPO_PERSONA_JURIDICA_UNION_CONSORCIO_STR);
        // Normalizamos el texto recibido
        String textoNormalizado = quitarTildes(textoRecibido.toLowerCase());
        // Comparamos con los valores posibles, usando contains
        String result =  valores.stream()
                .filter(dt -> quitarTildes(dt.toLowerCase()).contains(textoNormalizado))
                .findFirst().orElse("nn");
        System.err.println("Razon social que coinside: " + result);
        return valores.indexOf(result) + 1;
    }
    
    public static HashMap<Integer, CntjTercero> convertTerceroToHash(List<CntjTercero> list) {
        HashMap<Integer, CntjTercero> map = new HashMap<>();
        try {
            for (CntjTercero i : list) {
                map.put(i.getId(), i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    
    public static HashMap<Integer, CntjProcesoDocumento> convertProcesoDocumentoToHash(List<CntjProcesoDocumento> list) {
        HashMap<Integer, CntjProcesoDocumento> map = new HashMap<>();
        try {
            for (CntjProcesoDocumento i : list) {
                map.put(i.getId(), i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    
    public static HashMap<Integer, CntjEstado> convertEstadosToHash(List<CntjEstado> list) {
        HashMap<Integer, CntjEstado> map = new HashMap<>();
        try {
            for (CntjEstado i : list) {
                map.put(i.getId(), i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    
    public static HashMap<Integer, CntjProceso> convertProcesoToHash(List<CntjProceso> list) {
        HashMap<Integer, CntjProceso> map = new HashMap<>();
        try {
            for (CntjProceso i : list) {
                map.put(i.getId(), i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    
    public static GnAlerta getAlertaGestionExpediente(Usuario usuarioDestino, Date fechaEjecucion, String numeroExpediente, String estadoActual, String nombreUsuario, String observacion ){
        GnAlerta alerta = new GnAlerta();
        alerta.setGnUsuarioId(usuarioDestino);
        alerta.setSeveridad(SEVERIDAD_MENSAJE);
        alerta.setEstado(ESTADO_GENERADO);
        alerta.setNombre(NOMBRE_CREACION_EXPEDIENTE);
        java.util.Date fecha = new java.util.Date(fechaEjecucion.getTime());
        LocalDate fechaLocal = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        alerta.setDescripcion(String.format(CntjConstantes.MENSAJE_CREACION_EXPEDIENTE,numeroExpediente, estadoActual, nombreUsuario, fechaLocal.format(formato), observacion != null ? observacion : TEXTO_VACIO));
        alerta.setFechaHoraCrea(new Date());
        return alerta;
    }
    
    public static boolean htmlVacio(String html) {
        if (html == null || html.trim().isEmpty()) {
            return true;
        }

        // Elimina etiquetas como <p><br></p>, <p></p>, espacios &nbsp;, etc.
        String limpio = html
                .replaceAll("<p><br></p>", "");

        return limpio.trim().isEmpty();
    }

    
}
