package com.saviasaludeps.savia.web.juridico.utilidades;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfReader;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.juridico.CntjCampo;
import com.saviasaludeps.savia.dominio.juridico.CntjComite;
import com.saviasaludeps.savia.dominio.juridico.CntjContrato;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoGarantia;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoIndicador;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoObligacion;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoSupervisor;
import com.saviasaludeps.savia.dominio.juridico.CntjDocumento;
import com.saviasaludeps.savia.dominio.juridico.CntjExpediente;
import com.saviasaludeps.savia.dominio.juridico.CntjLinea;
import com.saviasaludeps.savia.dominio.juridico.CntjOtrosi;
import com.saviasaludeps.savia.dominio.juridico.CntjTercero;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

/**
 *
 * @author idbohorquez
 */
public class CntjUtilidades {

    public static CntjContrato parsearJsonContrato(String objetostr) throws JsonProcessingException, IllegalArgumentException, IllegalAccessException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(objetostr);
        CntjContrato datos = new CntjContrato();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Map<String, String> mapeoCampos = new HashMap<>();
        mapeoCampos.put("cntj_terceros_id", "cntjTerceroId");
        mapeoCampos.put("mae_tipo_contrato_id", "maeTipoContratoId");
        mapeoCampos.put("mae_tipo_contrato_codigo", "maeTipoContratoCodigo");
        mapeoCampos.put("mae_tipo_contrato_valor", "maeTipoContratoValor");
        mapeoCampos.put("fecha_inicio", "fechaInicio");
        mapeoCampos.put("fecha_fin", "fechaFin");
        mapeoCampos.put("valor_inicial", "valorInicial");
        mapeoCampos.put("valor_pagado_total", "valorPagadoTotal");
        mapeoCampos.put("valor_contrato_mas_adiciones", "valorContratoMasAdiciones");
        mapeoCampos.put("valor_total_otrosies", "valorTotalOtrosies");
        mapeoCampos.put("contrato", "contrato");
        mapeoCampos.put("mae_estado_contrato_id", "maeEstadoContratoId");
        mapeoCampos.put("mae_estado_contrato_codigo", "maeEstadoContratoCodigo");
        mapeoCampos.put("mae_estado_contrato_valor", "maeEstadoContratoValor");
        mapeoCampos.put("proceso", "proceso");
        mapeoCampos.put("mae_clase_contrato_id", "maeClaseContratoId");
        mapeoCampos.put("mae_clase_contrato_codigo", "maeClaseContratoCodigo");
        mapeoCampos.put("mae_clase_contrato_valor", "maeClaseContratoValor");
        mapeoCampos.put("estado_legalizacion", "estadoLegalizacion");
        mapeoCampos.put("mae_modalidad_contrato_id", "maeModalidadContratoId");
        mapeoCampos.put("mae_modalidad_contrato_codigo", "maeModalidadContratoCodigo");
        mapeoCampos.put("mae_modalidad_contrato_valor", "maeModalidadContratoValor");
        mapeoCampos.put("complejidad", "complejidad");
        mapeoCampos.put("mae_regimen_id", "maeRegimenId");
        mapeoCampos.put("mae_regimen_codigo", "maeRegimenCodigo");
        mapeoCampos.put("mae_regimen_valor", "maeRegimenValor");
        mapeoCampos.put("plazo_inicial_meses", "plazoInicialMeses");
        mapeoCampos.put("plazo_inicial_dias", "plazoInicialDias");
        mapeoCampos.put("plazo_prorrogas", "plazoProrrogas");
        mapeoCampos.put("plazo_total_dias", "plazoTotalDias");
        mapeoCampos.put("valor_mes", "valorMes");
        mapeoCampos.put("valor_dia", "valorDia");
        mapeoCampos.put("valor_upc", "valorUpc");
        mapeoCampos.put("valor_adiciones", "valorAdiciones");
        mapeoCampos.put("valor_total", "valorTotal");
        mapeoCampos.put("forma_pago", "formaPago");
        mapeoCampos.put("tipo_anticipo", "tipoAnticipo");
        mapeoCampos.put("valor_anticipo", "valorAnticipo");
        mapeoCampos.put("objeto", "objeto");
        mapeoCampos.put("fecha_suscripcion", "fechaSuscripcion");
        mapeoCampos.put("tipo_gasto", "tipoGasto");
        mapeoCampos.put("fecha_suspension", "fechaSuspension");
        mapeoCampos.put("motivo_suspension", "motivoSuspension");
        mapeoCampos.put("periodicidad_seguimiento", "periodicidadSeguimiento");
        mapeoCampos.put("motivo_terminacion_anticipada", "motivoTerminaAnticipada");
        mapeoCampos.put("enlace_publica_secop", "enlacePublicaSecop");
        mapeoCampos.put("fecha_publica_secop", "fechaPublicaSecop");
        mapeoCampos.put("responsable_publica_secop", "responsablePublicaSecop");
        mapeoCampos.put("fecha_liquidacion", "fechaLiquidacion");
        mapeoCampos.put("valor_facturado", "valorFacturado");

        CntjContratoSupervisor supervisor = new CntjContratoSupervisor();
        boolean existeSupervisor = false;
        Map<String, String> camposResponsable = new HashMap<>();
        camposResponsable.put("cntj_contratos_id", "cntjContratosId");
        camposResponsable.put("cntj_terceros_id", "cntjTercerosId");
        camposResponsable.put("fecha_inicio", "fechaInicio");
        camposResponsable.put("fecha_fin", "fechaFin");
        camposResponsable.put("etapa_designacion", "etapaDesignacion");

        CntjContratoGarantia garantia = new CntjContratoGarantia();
        boolean existeGarantia = false;
        Map<String, String> camposGarantia = new HashMap<>();
        camposGarantia.put("cntj_contratos_id", "cntjContratoId");
        camposGarantia.put("mae_garantia_contrato_id", "maeGarantiaContratoId");
        camposGarantia.put("fecha_expedicion", "fechaExpedicion");
        camposGarantia.put("porcentaje_valor_contrato", "porcentajeValorContrato");
        camposGarantia.put("porcentaje_valor_anticipo", "porcentajeValorAnticipo");
        camposGarantia.put("valor_asegurado", "valorAsegurado");
        camposGarantia.put("vigencia_desde", "vigenciaDesde");
        camposGarantia.put("vigencia_hasta", "vigenciaHasta");
        camposGarantia.put("estado", "estado");
        camposGarantia.put("requiere_renovacion", "requiereRenovacion");
        camposGarantia.put("fecha_aprobacion", "fechaAprobacion");

        CntjContratoIndicador indicador = new CntjContratoIndicador();
        boolean existeIndicador = false;
        Map<String, String> camposIndicador = new HashMap<>();
        camposIndicador.put("cntj_contratos_id", "cntjContratosId");
        camposIndicador.put("tipo_indicador", "tipoIndicador");
        camposIndicador.put("descripcion", "descripcion");
        camposIndicador.put("meta", "meta");
        camposIndicador.put("aplica_descuento", "aplicaDescuento");
        camposIndicador.put("porcentaje_descuento", "porcentajeDescuento");
        camposIndicador.put("valor_descuento", "valorDescuento");

        CntjContratoObligacion obligacion = new CntjContratoObligacion();
        boolean existeObligacion = false;
        Map<String, String> camposObligaciones = new HashMap<>();
        camposObligaciones.put("cntj_contratos_id", "cntjContratosId");
        camposObligaciones.put("numero_obligacion", "numeroObligacion");
        camposObligaciones.put("descripcion", "descripcion");

        // Recorrer la lista "campos"
        for (JsonNode campoNode : rootNode.get(CntjConstantes.CAMPOS)) {
            String campo = campoNode.has(CntjConstantes.CAMPO) ? campoNode.get(CntjConstantes.CAMPO).asText() : null;
            String valor = campoNode.has(CntjConstantes.VALOR) ? campoNode.get(CntjConstantes.VALOR).asText() : null;
            String tabla = campoNode.has(CntjConstantes.TABLA) ? campoNode.get(CntjConstantes.TABLA).asText() : null;
            boolean isborrador = campoNode.has(CntjConstantes.BORRADOR) ? campoNode.get(CntjConstantes.BORRADOR).asBoolean(): false;
            
            if (tabla != null && isborrador == false) {
                if (tabla.equals(CntjConstantes.TABLA_CONTRATO)) {
                    if (campo != null) {
                        // Obtener el nombre real del atributo en la clase contrato
                        String nombreAtributo = mapeoCampos.getOrDefault(campo, campo);
                        try {
                            Field field = CntjContrato.class.getDeclaredField(nombreAtributo);
                            field.setAccessible(true);
                            field.set(datos, valorTipoCampo(field.getType(), valor));
                        } catch (NoSuchFieldException e) {
                            Logger.getLogger(CntjUtilidades.class.getName()).log(Level.SEVERE, String.format("Inconveniente al castear clase de datos %s", e.getMessage()), e);
                        }
                    }
                }
                if (tabla.equals(CntjConstantes.TABLA_SUPERVISOR)) {
                    if (campo != null) {
                        existeSupervisor = true;
                        String nombreAtributo = camposResponsable.getOrDefault(campo, campo);
                        try {
                            Field field = CntjContratoSupervisor.class.getDeclaredField(nombreAtributo);
                            field.setAccessible(true);
                            field.set(supervisor, valorTipoCampo(field.getType(), valor));
                        } catch (NoSuchFieldException e) {
                            Logger.getLogger(CntjUtilidades.class.getName()).log(Level.SEVERE, String.format("Inconveniente al castear clase de datos supervisores. %s", e.getMessage()), e);
                        }
                    }
                }
                if (tabla.equals(CntjConstantes.TABLA_GARANTIAS)) {
                    if (campo != null) {
                        existeGarantia = true;
                        String nombreAtributo = camposGarantia.getOrDefault(campo, campo);
                        try {
                            Field field = CntjContratoGarantia.class.getDeclaredField(nombreAtributo);
                            field.setAccessible(true);
                            field.set(garantia, valorTipoCampo(field.getType(), valor));
                        } catch (NoSuchFieldException e) {
                            Logger.getLogger(CntjUtilidades.class.getName()).log(Level.SEVERE, String.format("Inconveniente al castear clase de datos garantias. %s", e.getMessage()), e);
                        }
                    }
                }
                if (tabla.equals(CntjConstantes.TABLA_INDICADORES)) {
                    if (campo != null) {
                        existeIndicador = true;
                        String nombreAtributo = camposIndicador.getOrDefault(campo, campo);
                        try {
                            Field field = CntjContratoIndicador.class.getDeclaredField(nombreAtributo);
                            field.setAccessible(true);
                            field.set(indicador, valorTipoCampo(field.getType(), valor));
                        } catch (NoSuchFieldException e) {
                            Logger.getLogger(CntjUtilidades.class.getName()).log(Level.SEVERE, String.format("Inconveniente al castear clase de datos indicadores. %s", e.getMessage()), e);
                        }
                    }
                }
                if (tabla.equals(CntjConstantes.TABLA_OBLIGACIONES)) {
                    if (campo != null) {
                        existeObligacion = true;
                        String nombreAtributo = camposObligaciones.getOrDefault(campo, campo);
                        try {
                            Field field = CntjContratoObligacion.class.getDeclaredField(nombreAtributo);
                            field.setAccessible(true);
                            field.set(obligacion, valorTipoCampo(field.getType(), valor));
                            /*if (field.getType().equals(Date.class)) {
                                field.set(obligacion, sdf.parse(valor));
                            } else if (field.getType().equals(Integer.class)) {
                                field.set(obligacion, Integer.valueOf(valor));
                            } else if (field.getType().equals(BigDecimal.class)) {
                                field.set(obligacion, new BigDecimal(valor));
                            } else if (field.getType().equals(CntjTercero.class)) {
                                field.set(obligacion, new CntjTercero(Integer.valueOf(valor)));
                            } else {
                                field.set(obligacion, valor);
                            }*/
                        } catch (NoSuchFieldException e) {
                            Logger.getLogger(CntjUtilidades.class.getName()).log(Level.SEVERE, String.format("Inconveniente al castear clase de datos obligaciones. %s", e.getMessage()), e);
                        }
                    }
                }
            }
        }
        if (existeSupervisor) {
            datos.getCntjContratoSupervisorList().add(supervisor);
        }
        if (existeGarantia) {
            datos.getCntjContratoGarantiaList().add(garantia);
        }
        if (existeIndicador) {
            datos.getCntjContratoIndicadorList().add(indicador);
        }
        if (existeObligacion) {
            datos.getCntjContratoObligacionList().add(obligacion);
        }
        return datos;
    }

    public static CntjOtrosi parsearJsonOtrosi(String objetostr) throws JsonProcessingException, IllegalArgumentException, IllegalAccessException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(objetostr);
        CntjOtrosi datos = new CntjOtrosi();

        Map<String, String> mapeoCampos = new HashMap<>();
        mapeoCampos.put("cntj_contratos_id", "contratoId");
        mapeoCampos.put("numero", "numero");
        mapeoCampos.put("tipo", "tipo");
        mapeoCampos.put("fecha_suscripcion", "fechasuscripcion");
        mapeoCampos.put("fecha_inicio", "fechaInicio");
        mapeoCampos.put("fecha_terminacion", "fechaTerminacion");
        mapeoCampos.put("plazo_prorroga_meses", "plazoMeses");
        mapeoCampos.put("plazo_prorroga_dias", "plazoDias");
        mapeoCampos.put("valor", "valor");
        mapeoCampos.put("justificacion", "justificacion");
        mapeoCampos.put("elementos_adicionales", "elementoAdicional");
        mapeoCampos.put("estado", "estado");
        mapeoCampos.put("estado_legalizacion", "estadoLegalizacion");

        // Recorrer la lista "campos"
        for (JsonNode campoNode : rootNode.get(CntjConstantes.CAMPOS)) {
            String campo = campoNode.has(CntjConstantes.CAMPO) ? campoNode.get(CntjConstantes.CAMPO).asText() : null;
            String valor = campoNode.has(CntjConstantes.VALOR) ? campoNode.get(CntjConstantes.VALOR).asText() : null;
            String tabla = campoNode.has(CntjConstantes.TABLA) ? campoNode.get(CntjConstantes.TABLA).asText() : null;

            if (tabla != null) {
                if (tabla.equals(CntjConstantes.TABLA_OTROSI)) {
                    if (campo != null) {
                        // Obtener el nombre real del atributo en la clase contrato
                        String nombreAtributo = mapeoCampos.getOrDefault(campo, campo);
                        try {
                            Field field = CntjOtrosi.class.getDeclaredField(nombreAtributo);
                            field.setAccessible(true);
                            field.set(datos, valorTipoCampo(field.getType(), valor));
                        } catch (NoSuchFieldException e) {
                            Logger.getLogger(CntjUtilidades.class.getName()).log(Level.SEVERE, String.format("Inconveniente al castear clase de datos %s", e.getMessage()), e);
                        }
                    }
                }
            }
        }

        return datos;
    }

    public static CntjLinea parsearJsonLinea(String objetostr) throws JsonProcessingException, IllegalArgumentException, IllegalAccessException, Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(objetostr);

        Map<String, String> mapeoCampos = new HashMap<>();
        mapeoCampos.put("cntj_comites_id", "cntjComiteId");
        mapeoCampos.put("gn_usuarios_id", "usuariosId");
        mapeoCampos.put("cntj_expedientes_id", "expedienteId");
        mapeoCampos.put("tipo", "tipo");
        mapeoCampos.put("estado", "estado");
        mapeoCampos.put("descripcion", "descripcion");
        mapeoCampos.put("area", "area");
        mapeoCampos.put("observaciones", "observacion");

        CntjLinea datos = getCamposTable(CntjLinea.class, rootNode, CntjConstantes.TABLA_LINEA, mapeoCampos);
        return datos;
    }

    private static Object valorTipoCampo(Class<?> tipo, String valor) throws ParseException {
        if (valor == null) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (tipo.equals(Date.class)) {
            return sdf.parse(valor);
        } else if (tipo.equals(Integer.class)) {
            return Integer.valueOf(valor);
        } else if (tipo.equals(BigDecimal.class)) {
            String limpio = valor.replace(".", "");
            return new BigDecimal(limpio);
        } else if (tipo.equals(CntjTercero.class)) {
            return new CntjTercero(Integer.valueOf(valor));
        } else if (tipo.equals(Boolean.class) || tipo.equals(boolean.class)) {
            if(valor.equals(CntjConstantes.SI)){
                return true;
            }else if(valor.equals(CntjConstantes.NO)){
                return false;
            }
            return Boolean.valueOf(valor);
        } else if (tipo.equals(CntjContrato.class)) {
            return new CntjContrato(Integer.valueOf(valor));
        } else if (tipo.equals(CntjComite.class)) {
            return new CntjComite(Integer.valueOf(valor));
        } else if (tipo.equals(Usuario.class)) {
            return new Usuario(Integer.valueOf(valor));
        } else if (tipo.equals(CntjExpediente.class)) {
            return new CntjExpediente(Integer.valueOf(valor));
        } else {
            return valor;
        }
    }

    public static String parsearJsonCampo(String objetostr, String campoo) throws JsonProcessingException, IllegalArgumentException, IllegalAccessException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(objetostr);
        String datos = "";
        // Recorrer la lista "campos"
        for (JsonNode campoNode : rootNode.get(CntjConstantes.CAMPOS)) {
            String campojson = campoNode.has(CntjConstantes.CAMPO) ? campoNode.get(CntjConstantes.CAMPO).asText() : null;
            String valor = campoNode.get(CntjConstantes.VALOR).asText();
            if (valor != null) {
                datos = valor;
            }
        }
        return datos;
    }

    public static JSONObject actualizarJson(String json, String nombre, String etiqueta, String nuevoValor) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json); // Convertir String JSON a JsonNode

        if (rootNode.has("campos") && rootNode.get("campos").isArray()) {
            ArrayNode camposArray = (ArrayNode) rootNode.get("campos");
            boolean actualizado = false;

            for (JsonNode campo : camposArray) {
                if (campo.has("nombre") && campo.has("etiqueta")) {
                    if (campo.get("nombre").asText().equals(nombre) && campo.get("etiqueta").asText().equals(etiqueta)) {
                        ((ObjectNode) campo).put("valor", nuevoValor); // Actualizar el valor
                        actualizado = true;
                        break; // Salir del bucle ya que encontramos el objeto
                    }
                }
            }

            if (!actualizado) {
                // Crear nuevo objeto si no existe
                ObjectNode nuevoCampo = objectMapper.createObjectNode();
                nuevoCampo.put("campo", "nuevo_campo"); // Puedes cambiar esto según necesidad
                nuevoCampo.put("tabla", "nueva_tabla");
                nuevoCampo.put("valor", nuevoValor);
                nuevoCampo.put("nombre", nombre);
                nuevoCampo.put("etiqueta", etiqueta);

                camposArray.add(nuevoCampo);
            }
        }

        // Convertir JsonNode a JSONObject antes de retornar
        return new JSONObject(objectMapper.writeValueAsString(rootNode));
    }

    public static String getValorTercero(CntjTercero tercero, String campo) {
        switch (campo) {
            case CntjConstantes.VALOR_REF_NATURALEZ_JURIDICA:
                return CntjConstantes.getTipoNaturaleza(tercero.getNaturalezaJuridica());
            case CntjConstantes.VALOR_REF_TIPO_DOCUMENTO:
                return tercero.getMaeTipoDocumentoValor();
            case CntjConstantes.VALOR_REF_NUMERO_DOCUMENTO:
                return tercero.getNumeroDocumento();
            case CntjConstantes.VALOR_REF_RAZON_SOCIAL:
                return tercero.getRazonSocial();
            case CntjConstantes.VALOR_REF_TIPO_DOC_REPRESENTANTE:
                return tercero.getMaeRepresentanteTipoDocumentoValor();
            case CntjConstantes.VALOR_REF_NUMERO_DOC_REPRESENTANTE:
                return tercero.getRepresentanteNumeroDocumento();
            case CntjConstantes.VALOR_REF_NOMBRE_REPRESENTANTE:
                return tercero.getNombreRepresentanteLegal();
            case CntjConstantes.VALOR_REF_CODIGO_HABILITACION:
                return tercero.getCodigoHabilitacion();
            case CntjConstantes.VALOR_REF_DIRECCION:
                return tercero.getDireccion();
            case CntjConstantes.VALOR_REF_EMAIL:
                return tercero.getCorreoElectronico();
            case CntjConstantes.VALOR_REF_TELEFONO:
                return tercero.getTelefonoTercero();
            case CntjConstantes.VALOR_REF_CARGO:
                return tercero.getMaeCargoValor();
            case CntjConstantes.VALOR_REF_AREA:
                return tercero.getMaeAreaValor();
            case CntjConstantes.VALOR_REF_UNION_TEMPORAL:
                return tercero.getUnionTemporalstr();
            default:
                return "";
        }
    }

    public static String getValorUsuario(Usuario usuario, String campo) {
        switch (campo) {
            case CntjConstantes.VALOR_REF_USUARIO_NOMBRE:
                return usuario.getNombre();
            case CntjConstantes.VALOR_REF_USUARIO_EMAIL:
                return usuario.getCorreoElectronico();
            case CntjConstantes.VALOR_REF_USUARIO_TIPO_DOCUMENTO_COD:
                return usuario.getMaeTipoDocumentoCodigo();
            case CntjConstantes.VALOR_REF_USUARIO_TIPO_DOCUMENTO_VAL:
                return usuario.getMaeTipoDocumentoValor();
            case CntjConstantes.VALOR_REF_USUARIO_DOCUMENTO:
                return usuario.getDocumento();
            case CntjConstantes.VALOR_REF_USUARIO_AREA:
                return usuario.getMaeAreaValor();
            case CntjConstantes.VALOR_REF_USUARIO_CARGO:
                return usuario.getMaeCargoValor();
            default:
                return "";
        }
    }

    public static JSONObject actualizarCampo(String objetostr, JSONObject jsonCampo) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(objetostr);
        ArrayNode campos = (ArrayNode) rootNode.get(CntjConstantes.CAMPOS);
        ObjectNode nuevoCampo = (ObjectNode) mapper.readTree(jsonCampo.toString());
        // Buscar y reemplazar si ya existe
        boolean reemplazado = false;
        for (int i = 0; i < campos.size(); i++) {
            JsonNode campo = campos.get(i);
            if (campo.get(CntjConstantes.IDCAMPO).asInt() == nuevoCampo.get(CntjConstantes.IDCAMPO).asInt()) {
                campos.set(i, nuevoCampo); // 👈 Reemplaza el objeto existente
                reemplazado = true;
                break;
            }
        }
        // Si no fue reemplazado, lo agrega al json como nuevo campo
        if (!reemplazado) {
            campos.add(nuevoCampo);
        }
        return new JSONObject(mapper.writeValueAsString(rootNode));
    }

    public static Integer getIndiceCampoExistente(String objetostr, Integer idcampo) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(objetostr);
        Integer indice = null;
        if (rootNode.has(CntjConstantes.CAMPOS) && rootNode.get(CntjConstantes.CAMPOS).isArray()) {
            ArrayNode camposArray = (ArrayNode) rootNode.get(CntjConstantes.CAMPOS);
            for (int i = 0; i < camposArray.size(); i++) {
                JsonNode campo = camposArray.get(i);
                if (campo.get(CntjConstantes.IDCAMPO).asInt() == idcampo) {
                    indice = i;
                    break;
                }
            }
        }
        return indice;
    }

    public static List<CntjCampo> getArrayCampos(String data) {
        List<CntjCampo> listaCampos = new ArrayList<>();
        JSONObject objeto = new JSONObject(data != null ? data : "{}");
        JSONArray arrayObjeto = new JSONArray();
        if (objeto.has(CntjConstantes.CAMPOS)) {
            arrayObjeto = objeto.getJSONArray(CntjConstantes.CAMPOS);
        }
        if (arrayObjeto.length() > 0) {
            listaCampos = new Gson().fromJson(arrayObjeto.toString(), new TypeToken<ArrayList<CntjCampo>>() {
            }.getType());
        }
        return listaCampos;
    }

    public static String getNombreDocumento(String numeroExpediente, String nombreDocumento) {
        String nombre = String.format("%s_%s", numeroExpediente, nombreDocumento);
        if (nombre.length() > 59) {
            nombre = nombre.substring(0, 59);
        }
        return String.format("%s.pdf", nombre);
    }

    private static <T> T getCamposTable(Class<T> clase, JsonNode rootNode, String nombreTabla, Map<String, String> mapeoCampos) throws ParseException, IllegalArgumentException, Exception {
        T instancia = clase.getDeclaredConstructor().newInstance();
        for (JsonNode campoNode : rootNode.get(CntjConstantes.CAMPOS)) {
            String campo = campoNode.has(CntjConstantes.CAMPO) ? campoNode.get(CntjConstantes.CAMPO).asText() : null;
            String valor = campoNode.has(CntjConstantes.VALOR) ? campoNode.get(CntjConstantes.VALOR).asText() : null;
            String tabla = campoNode.has(CntjConstantes.TABLA) ? campoNode.get(CntjConstantes.TABLA).asText() : null;
            boolean isborrador = campoNode.has(CntjConstantes.BORRADOR) ? campoNode.get(CntjConstantes.BORRADOR).asBoolean(): false;

            if (tabla != null && isborrador == false) {
                if (tabla.equals(nombreTabla)) {
                    if (campo != null) {
                        // Obtener el nombre real del atributo en la clase contrato
                        String nombreAtributo = mapeoCampos.getOrDefault(campo, campo);
                        try {
                            Field field = clase.getDeclaredField(nombreAtributo);
                            field.setAccessible(true);
                            field.set(instancia, valorTipoCampo(field.getType(), valor));
                        } catch (NoSuchFieldException e) {
                            Logger.getLogger(CntjUtilidades.class.getName()).log(Level.SEVERE, String.format("Inconveniente al castear clase de datos %s", e.getMessage()), e);
                        }
                    }
                }
            }
        }
        return instancia;
    }

    public static String getValorMoneda(String valor) {
        String resultado = ""; 
        if(valor == null || valor.isEmpty()){
            return resultado;
        }
        try {
            DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(new Locale("es", "CO"));
            decimalFormat.setMaximumFractionDigits(0); // No mostrar decimales
            decimalFormat.setMinimumFractionDigits(0);
            BigDecimal bdc = new BigDecimal(valor);
            resultado = decimalFormat.format(bdc);
        } catch (Exception e) {
            Logger.getLogger(CntjUtilidades.class.getName()).log(Level.SEVERE, String.format("Inconveniente al realizar proceso %s", e.getMessage()), e);
        }
        return resultado;
    }
    
    public static byte[] unirPDFsEnMemoria(List<CntjDocumento> documentos) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfCopy copy = new PdfCopy(document, baos);
        document.open();

        for (CntjDocumento doc : documentos) {
            PdfReader reader = new PdfReader(doc.getDocumentoRuta()+doc.getDocumentoArchivo());
            int n = reader.getNumberOfPages();
            for (int i = 1; i <= n; i++) {
                copy.addPage(copy.getImportedPage(reader, i));
            }
            reader.close();
        }

        document.close();
        return baos.toByteArray(); 
    }
    
    public static byte[] crearZipEnMemoria(Map<String, byte[]> archivos) throws IOException {
        ByteArrayOutputStream zipBaos = new ByteArrayOutputStream();
        ZipOutputStream zipOut = new ZipOutputStream(zipBaos);

        for (Map.Entry<String, byte[]> entry : archivos.entrySet()) {
            ZipEntry zipEntry = new ZipEntry(entry.getKey());
            zipOut.putNextEntry(zipEntry);
            zipOut.write(entry.getValue());
            zipOut.closeEntry();
        }

        zipOut.close();
        return zipBaos.toByteArray(); 
    }
     
}
