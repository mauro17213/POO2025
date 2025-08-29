/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.utilidades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCargaAdjunto;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeSoporte;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeSoporteAdjunto;
import com.saviasaludeps.savia.web.cuentamedica.rips.DTO.CmFeRipsCargaFacturaDTO;
import com.saviasaludeps.savia.web.cuentamedica.rips.DTO.CmFeRipsCuvJsonDTO;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.json.Json;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.mozilla.universalchardet.UniversalDetector;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author JEPEREZML-140741
 */
public class CmUtilidades {
    
    public final static int TIPO_JSON_RIP = 1;
    public final static int TIPO_JSON_CUV = 2;
    public final static String POSTFIJO_ARCHIVO_UNICO_VALIDACION = "_CUV.json";
    public final static  String ESTADOS_AGREGAR_SOPORTES = CmFeRipsCarga.ESTADO_EN_COLA + ","
                    + CmFeRipsCarga.ESTADO_EN_PROCESO + ","
                    + CmFeRipsCarga.ESTADO_VALIDACION_PROCESO;
    
    // Expresión regular para validar el patrón: prefijo_texto_texto.extension soportes
    public static final Pattern NOMBRAMIENTO_SOPORTE_MONO_USUARIO = 
        Pattern.compile("^(?<prefijo>[A-Za-z0-9]+)_(?<nit>[A-Za-z0-9]+)_(?<factura>[A-Za-z0-9]+)\\.(?<extension>[A-Za-z]{3,4})$");
    
     public static final Pattern NOMBRAMIENTO_SOPORTE_MULTI_USUARIO = 
        Pattern.compile("^(?<prefijo>[A-Za-z0-9]+)_(?<nit>[A-Za-z0-9]+)_(?<factura>[A-Za-z0-9]+)_(?<documento>[A-Za-z0-9]+)\\.(?<extension>[A-Za-z]{3,4})$");
     
// Método para validar si un InputStream tiene contenido
    public static boolean inputStreamTieneContenido(InputStream inputStream) throws Exception {
        try {
            if (inputStream == null) {
                return false;
            }
            return inputStream.available() > 0;
        } catch (IOException e) {
            throw new Exception("Error en inputStreamTieneContenido : " + e.toString(), e);
        }
    }
 
    public static boolean zipTieneContenido(InputStream inputStream) throws Exception {
        if (inputStream == null) {
            return false;
        }

        try (ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
            return zipInputStream.getNextEntry() != null;
        } catch (IOException e) {
            throw new Exception("Error al verificar el contenido del ZIP: " + e.getMessage(), e);
        }
    }
       
    public static boolean esJsonValido(InputStream inputStream) throws Exception{
        try  {
            javax.json.JsonReader reader = Json.createReader(inputStream);
            reader.read();
            return true;
        } catch (Exception e) {
            throw new Exception("Error en esJsonValido : " + e.toString(), e);
        }             
    }
    
    public static String validarJsonExtructuraBase(InputStream inputStream, CmFeRipsCargaAdjunto anexo) throws Exception {

        byte[] buffer = inputStream.readAllBytes();

        try ( InputStream inputStreamLectura = new ByteArrayInputStream(buffer);
              InputStream inputStreamCharset = new ByteArrayInputStream(buffer);
              InputStreamReader reader = new InputStreamReader(inputStreamLectura,
                      CmUtilidades.getCharset(inputStreamCharset))) {
            
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            
            if (CmUtilidades.esArchivoJsonCuv(anexo.getArchivoNombre())) {

                String jsonContent = new BufferedReader(reader).lines().collect(Collectors.joining("\n"));
                jsonContent = CmUtilidades.filtroContenidoJson(jsonContent);

                CmFeRipsCuvJsonDTO cuvDto = gson.fromJson(jsonContent, CmFeRipsCuvJsonDTO.class);

                if (cuvDto == null) {
                    return "El objeto json está vacío.";
                }

                if (cuvDto.codigoUnicoValidacion == null || cuvDto.codigoUnicoValidacion.isEmpty()) {
                    return "El campo 'codigoUnicoValidacion' es obligatorio y está vacío o faltante.";
                }
                if (cuvDto.fechaRadicacion == null || cuvDto.fechaRadicacion.isEmpty()) {
                    return "El campo 'fechaRadicacion' es obligatorio y está vacío o faltante.";
                }

            } else {
                String jsonContent = new BufferedReader(reader).lines().collect(Collectors.joining("\n"));
                jsonContent = CmUtilidades.filtroContenidoJson(jsonContent);   

                CmFeRipsCargaFacturaDTO facturaCargaDto = gson.fromJson(jsonContent, CmFeRipsCargaFacturaDTO.class);

                if (facturaCargaDto == null) {
                    return "El objeto json está vacío.";
                }

                if (facturaCargaDto.numFactura == null || facturaCargaDto.numFactura.isEmpty()) {
                    return "El campo 'numFactura' es obligatorio y está vacío o faltante.";
                }

                if (facturaCargaDto.usuarios == null || facturaCargaDto.usuarios.isEmpty()) {
                    return "El campo 'usuarios' es obligatorio y está vacío o faltante.";
                }
            }

        } catch (FileNotFoundException ex) {
            return ("El archivo no fue encontrado : " + obtenerErrorStrFormateado(ex));
        } catch (JsonSyntaxException ex) {
            return "Error de sintaxis en el JSON: " + anexo.getArchivoNombre() + " : " + obtenerErrorStrFormateado(ex);
        } catch (IOException ex) {
            return "Error al leer el JSON: " + anexo.getArchivoNombre() + " : " + obtenerErrorStrFormateado(ex);
        } catch (Exception ex) {
            return "Error inesperado al procesar el JSON: " + anexo.getArchivoNombre() + " : " + obtenerErrorStrFormateado(ex);
        }
        return "";
    }
    
     public static CmFeRipsCargaFacturaDTO obtenerCmFeRipsCargaFacturaDTO (InputStream inputStream, CmFeRipsCargaAdjunto anexo) throws Exception {
              
         CmFeRipsCargaFacturaDTO facturaCargaDto = null;
         byte[] buffer = inputStream.readAllBytes();
          
         try (InputStream inputStreamLectura = new ByteArrayInputStream(buffer);
             InputStream inputStreamCharset = new ByteArrayInputStream(buffer);
             InputStreamReader reader = new InputStreamReader(inputStreamLectura,
                         CmUtilidades.getCharset(inputStreamCharset))) {
             
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            if (CmUtilidades.esArchivoJsonCuv(anexo.getArchivoNombre())) {
                return facturaCargaDto;
            } else {
                 String jsonContent = new BufferedReader(reader).lines().collect(Collectors.joining("\n"));
                 jsonContent = CmUtilidades.filtroContenidoJson(jsonContent);            
                 facturaCargaDto = gson.fromJson(jsonContent, CmFeRipsCargaFacturaDTO.class);

                if (facturaCargaDto == null) {
                     throw new Exception( "El objeto json está vacío.");
                }

                if (facturaCargaDto.numFactura == null || facturaCargaDto.numFactura.isEmpty()) {
                   throw new Exception( "El campo 'numFactura' es obligatorio y está vacío o faltante.");
                }

                if (facturaCargaDto.usuarios == null || facturaCargaDto.usuarios.isEmpty()) {
                    throw new Exception(  "El campo 'usuarios' es obligatorio y está vacío o faltante.");
                }
                
                return facturaCargaDto;
            }

        } catch (FileNotFoundException ex) {
            throw new Exception( "El archivo no fue encontrado : " + obtenerErrorStrFormateado(ex));
        }  catch (JsonSyntaxException ex) {
            throw new Exception( "Error de sintaxis en el JSON: " + anexo.getArchivoNombre() + " : " + obtenerErrorStrFormateado(ex));
        } catch (IOException ex) {
            throw new Exception(  "Error al leer el JSON: " +   anexo.getArchivoNombre() + " : " + obtenerErrorStrFormateado(ex));
        } catch (Exception ex) {
            throw new Exception(  "Error inesperado al procesar el JSON: " + anexo.getArchivoNombre() + " : " + obtenerErrorStrFormateado(ex));
        } 
    }
    
    public static String validarJsonExtructuraBase(InputStream inputStream,  int tipoJson) throws Exception {

        try {
            InputStreamReader reader;
            if (inputStream == null) {
                return "No hay fuente de datos para validar json";
            }

            reader = new InputStreamReader(inputStream);
        
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(reader);
            if (jsonElement == null || !jsonElement.isJsonObject()) {
                return ("El archivo json esta vacio o no tiene formato válido");
            }

            com.google.gson.JsonObject jsonObject = jsonElement.getAsJsonObject();

            if (jsonObject == null) {
                return "No hay fuente de datos para validar json";
            }

            jsonObject = normalizarAtributosJsonMinuscula(jsonObject);

            if (TIPO_JSON_RIP == tipoJson) {
                return validarCamposArchivoJsonRip(jsonObject);
            }

            if (TIPO_JSON_CUV == tipoJson) {
                return validarCamposArchivoJsonCuv(jsonObject);
            }

        } catch (JsonIOException | JsonSyntaxException e) {
            return ("Error en esJsonValido: " + e.getMessage());
        }

        return "";
    }

    public static String validarCamposArchivoJsonRip(com.google.gson.JsonObject jsonObject) {
       
        if (jsonObject == null) {
            return "No hay fuente de datos para validar json";
        }
        
        if (!jsonObject.has("numfactura") || jsonObject.get("numfactura").isJsonNull()) {
            return ("El archivo json no contiene el campo 'numFactura' o está vacío.");
        }
        if (!jsonObject.has("usuarios") || jsonObject.get("usuarios").isJsonNull()) {
            return ("El archivo json no contiene el campo 'usuarios' o está vacío.");
        } else {
            com.google.gson.JsonArray usuariosArray = jsonObject.getAsJsonArray("usuarios");
            if (usuariosArray.size() == 0) {
                return ("El campo 'usuarios' en el archivo json está vacío.");
            }
        }
        return "";
    }

    public static String validarCamposArchivoJsonCuv(com.google.gson.JsonObject jsonObject) {
        
        if (jsonObject == null) {
            return "No hay fuente de datos para validar json";
        }
         
        if (!jsonObject.has("codigounicovalidacion") || jsonObject.get("codigounicovalidacion").isJsonNull()) {
            return ("El archivo json no contiene el campo 'codigoUnicoValidacion' o está vacío.");
        }
        if (!jsonObject.has("fecharadicacion") || jsonObject.get("fecharadicacion").isJsonNull()) {
            return ("El archivo json no contiene el campo 'fechaRadicacion' o está vacío.");
        }
        return "";
    }
    
    public static JsonObject normalizarAtributosJsonMinuscula(JsonObject jsonObject) {
        JsonObject normalizedJsonObject = new JsonObject();
        for (String key : jsonObject.keySet()) {
            String normalizedKey = key.toLowerCase(); 
            normalizedJsonObject.add(normalizedKey, jsonObject.get(key));
        }
        return normalizedJsonObject;
    }
    
    
    
    public static boolean esXmlValido(InputStream inputStream) throws Exception {
        try {
            Document documento = obtenerDocumentoFiltradoXML(inputStream);  
            documento.getDocumentElement().normalize();
            if (!documento.hasChildNodes()) {
                return false;
            }
            return true;
        } catch (Exception e) {
             throw new Exception("Error en esXmlValido : " + e.toString(), e);
        }
    }

    public static Document obtenerDocumentoFiltradoXML(InputStream inputStream) throws ParserConfigurationException, SAXException, IOException {
        byte[] buffer = inputStream.readAllBytes();
        InputStream inputStreamcharset = new ByteArrayInputStream(buffer);
        InputStream inputUtilizar = new ByteArrayInputStream(buffer);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStreamReader isr = new InputStreamReader(inputUtilizar, getCharset(inputStreamcharset));
        String jsonContent = new BufferedReader(isr).lines().collect(Collectors.joining("\n"));
        jsonContent = CmUtilidades.filtroReconocimientoArchivoXml(jsonContent);
        return  builder.parse(new InputSource(new StringReader(jsonContent)));
    }

    public static String obtenerExtensionArchivo(String nombreArchivo) {
        if (nombreArchivo != null) {
            return nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1);
        }
        return "";
    }
    
     public static String obtenerNombreArchivoSinExtension(String nombreArchivo) {
        int puntoIndex = nombreArchivo.lastIndexOf('.');
        return (puntoIndex == -1) ? nombreArchivo : nombreArchivo.substring(0, puntoIndex);
    }
     
    public static InputStream cloneInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) > 0) {
            baos.write(buffer, 0, len);
        }
        return new ByteArrayInputStream(baos.toByteArray());
    }
    
     public static InputStream obtenerCopiaInputStream(CmFeRipsCargaAdjunto anexoXML) throws IOException {
        InputStream inputEntrada = anexoXML.getInputStream();
        byte[] buffer = inputEntrada.readAllBytes();
        InputStream inputUtilizar = new ByteArrayInputStream(buffer);
        InputStream inputRemplazar = new ByteArrayInputStream(buffer);
        anexoXML.setInputStream(inputRemplazar);
        return inputUtilizar;
    }
     
      public static InputStream obtenerCopiaInputStreamSoporteAdjunto(CmFeSoporteAdjunto adjunto) throws IOException {
        InputStream inputEntrada = adjunto.getInputStream();
        byte[] buffer = inputEntrada.readAllBytes();
        InputStream inputUtilizar = new ByteArrayInputStream(buffer);
        InputStream inputRemplazar = new ByteArrayInputStream(buffer);
        adjunto.setInputStream(inputRemplazar);
        return inputUtilizar;
    }
      
    public static InputStream obtenerCopiaInputStreamSoporte(CmFeSoporte soporte) throws IOException {
        InputStream inputEntrada = soporte.getInputStream();
        byte[] buffer = inputEntrada.readAllBytes();
        InputStream inputUtilizar = new ByteArrayInputStream(buffer);
        InputStream inputRemplazar = new ByteArrayInputStream(buffer);
        soporte.setInputStream(inputRemplazar);
        return inputUtilizar;
    }
     
    public static void reiniciarInputStream(InputStream inputStreamIn) throws IOException {
        if (inputStreamIn.markSupported()) {
            inputStreamIn.reset();
        }
    }
    
     public static boolean esExtensionValida(String nombre, String extensionValida) {
        String[] extensiones = extensionValida.split("\\|");
        return Arrays.stream(extensiones).anyMatch(ext -> nombre.endsWith("." + ext));
    }
 
    public static String leerDatoArchivoJsonCuv(InputStream inputStream, String nombreCampo) throws IOException, Exception {

        if (nombreCampo == null) {
            throw new Exception("El campo a buscar no esta vacio.");
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        try (InputStreamReader reader = new InputStreamReader(inputStream)) {

            String jsonContent = new BufferedReader(reader).lines().collect(Collectors.joining("\n"));

            CmFeRipsCuvJsonDTO cuvDto = gson.fromJson(jsonContent, CmFeRipsCuvJsonDTO.class);

            if (cuvDto == null) {
                return "El objeto json está vacío.";
            }

            Class<?> classDtoCuv = cuvDto.getClass();

            Field campo = classDtoCuv.getDeclaredField(nombreCampo);
            campo.setAccessible(true);

            Object valor = campo.get(cuvDto);

            return (valor != null) ? valor.toString() : null;

        } catch (NoSuchFieldException ex) {
            throw new Exception("El campo '" + nombreCampo + "' no existe en el DTO." + obtenerErrorStrFormateado(ex), ex);
        } catch (IllegalAccessException ex) {
            throw new Exception("No se pudo acceder al campo '" + nombreCampo + "'." + obtenerErrorStrFormateado(ex), ex);
        } catch (FileNotFoundException ex) {
            throw new Exception("El archivo no fue encontrado : " + nombreCampo + " : " + obtenerErrorStrFormateado(ex), ex);
        } catch (JsonSyntaxException ex) {
            throw new Exception("Error de sintaxis en el archivo JSON: " + nombreCampo + " : " + obtenerErrorStrFormateado(ex), ex);
        } catch (IOException ex) {
            throw new Exception("Error al leer el archivo JSON: " + nombreCampo + " : " + obtenerErrorStrFormateado(ex), ex);
        } catch (Exception ex) {
            throw new Exception("Error inesperado al procesar el archivo JSON: " + nombreCampo + " : " + obtenerErrorStrFormateado(ex), ex);
        }

    }

     public static boolean esArchivoJsonCuv(String nombreArchivo) {
        if (nombreArchivo == null) {
            return false;
        }   
        return nombreArchivo.endsWith(POSTFIJO_ARCHIVO_UNICO_VALIDACION); 
    }
     
    public static boolean esArchivoJsonRips(String nombreArchivo) {
        return !esArchivoJsonCuv(nombreArchivo); 
    }
     
    public final static String obtenerErrorStrFormateado(Exception excepcion) {
        return Optional.ofNullable(excepcion.getMessage()).orElse(excepcion.toString());
    }
    
    public static final String getCharset(InputStream inputStream) throws IOException {
        String result = "UTF-8"; // Valor por defecto
        UniversalDetector detector = new UniversalDetector(null);
        byte[] buf = new byte[4096];

        try {
            int nread;
            while ((nread = inputStream.read(buf)) > 0 && !detector.isDone()) {
                detector.handleData(buf, 0, nread);
            }
            detector.dataEnd();

            String encoding = detector.getDetectedCharset();
            if (encoding != null) {
                result = encoding;
            }
        } catch (IOException e) {
            throw e;
        } finally {
            detector.reset();
            inputStream.close();
        }

        return result;
    }
    
    
    public static String filtroReconocimientoArchivoXml(String cadena) {
        if (cadena == null) {
            return "";
        }

        cadena = cadena.replaceFirst("ÿ", "");
        cadena = cadena.replaceFirst("�", "");
        return cadena;
    }
     
    public static String filtroContenidoXml(String cadena,   byte[] buffer ) {
        if (cadena == null) {
            return "";
        }
        
        if(cadena.contains("<Unreadable>") || cadena.contains("�")){
          cadena = new String(buffer, StandardCharsets.UTF_8).
                  replace("�", "").
                  replace("<Unreadable>", "");
          return cadena;
        } 
        // Eliminar caracteres no imprimibles (excepto tab, newline, return)
        cadena = Normalizer.normalize(cadena, Normalizer.Form.NFC);
        cadena = cadena.replaceAll("[\\p{Cntrl}&&[^\t\n\r]]", "");
        cadena = cadena.replaceAll("[^\\x00-\\xFFFF]", "");
        cadena = cadena.replaceFirst("ÿ", "");
        
        // 4. Eliminar caracteres problemáticos específicos Replacement chars y BOM
        cadena = cadena.replaceAll("[\uFFFD\uFEFF\uFFFF]", "");

        // 5. Reemplazar guiones "raros" por guiones normales
        cadena = cadena.replaceAll("[\u0091\u0092\u0093\u0094\u0095\u0096\u0097\u0098\u0099\u009A]", "");
         cadena = cadena.replaceAll("[\u2010-\u2015]", "-"); // Variantes de guión

        // 6. Eliminar otros caracteres especiales problemáticos
        cadena = cadena.replaceAll("[\u00A0\u2007\u202F]", " "); // Espacios no estándar
        cadena = cadena.replaceAll("[\u00AD]", ""); // Guión blando
        
         //7. Comillas curvas y otros caracteres problemáticos
         cadena = cadena.replaceAll("[\\u2018\\u2019\u0091\u0092]", "'")  // Comillas simples
                  .replaceAll("[\\u201C\\u201D\u0093\u0094]", "\""); // Comillas dobles
        
        //8. : Reemplazar comillas inteligentes y otros caracteres problemáticos
        cadena = cadena.replaceAll("[\\u2018\\u2019]", "'")  // Comillas simples inteligentes
                        .replaceAll("[\\u201C\\u201D]", "\""); // Comillas dobles inteligentes

        // Verificar si queda algún carácter problemático
        if (cadena.matches(".*[\\xC2\\x80-\\xC2\\x9F].*")) {
            cadena = cadena.replaceAll("[\\xC2\\x80-\\xC2\\x9F]", "");
        }
        //  Permite:
        //  Todos los caracteres ASCII (0-127),
        //  Caracteres Latin1 extendidos (128-255)
        //  Caracteres de control permitidos (\t, \n, \r)
         cadena = cadena.replaceAll("[^\\x00-\\xFF\\t\\n\\r]", "");
         
         // Permite:
        // - Caracteres imprimibles ASCII (32-126)
        // - Caracteres Latin1 extendidos (160-255)
        // - Tabulación, nueva línea y retorno de carro
         cadena = cadena.replaceAll("[^\\x20-\\x7E\\xA0-\\xFF\\t\\n\\r]", "");
        
        return cadena;
    }
    
    public static String filtroContenidoJson(String cadena) {
        if (cadena == null) {
            return "";
        }
        // Elimina específicamente la secuencia "ï»¿"
        cadena = cadena.replaceFirst("^ï»¿", "");

        // O elimina cualquier combinación de BOMs y espacios
        cadena = cadena.replaceFirst("^[\\sï»¿\uFEFF\uFFFE]+", "");
        cadena = cadena.replaceFirst("^ÿþ", "");  // UTF-16 LE BOM

        return cadena;
    }
    
    public static int obtenerTipoFeDocumento(int tipo) {
        switch (tipo) {
            case CmFeRipsCarga.TIPO_CARGA_FACTURA:
            case CmFeRipsCarga.TIPO_CARGA_CAPITA_INICIAL:
            case CmFeRipsCarga.TIPO_CARGA_CAPITA_PERIODO:
            case CmFeRipsCarga.TIPO_CARGA_CAPITA_FINAL:
                return 0;
            case CmFeRipsCarga.TIPO_CARGA_NOTA_CREDITO_CAPITA :
            case CmFeRipsCarga.TIPO_CARGA_NOTA_CREDITO_PARCIAL :
            case CmFeRipsCarga.TIPO_CARGA_NOTA_CREDITO_TOTAL :
            case CmFeRipsCarga.TIPO_CARGA_NOTA_CREDITO_ACUERDO_VOLUNTAD:
                return 2;
            case CmFeRipsCarga.TIPO_CARGA_NOTA_DEBITO:
                return 1;
            default:
               return 0;
        }
    }

    public static List<Maestro> listarCapitaPeriodo() {
        List<Maestro> periodos = new ArrayList();
        LocalDate fechaMesTransAnterior2 = LocalDate.now().minusMonths(3);
        LocalDate fechaMesTransAnterior = LocalDate.now().minusMonths(2);
        LocalDate fechaMesAnterior = LocalDate.now().minusMonths(1);
        LocalDate fechaMesActualr = LocalDate.now();
        
        Month mesTransAnterior2 = fechaMesTransAnterior2.getMonth();
        Month mesTransAnterior = fechaMesTransAnterior.getMonth();
        Month mesAnterior = fechaMesAnterior.getMonth();
        Month mesActual = fechaMesActualr.getMonth();
        
        String nombreMesTransAntrior2 = mesTransAnterior2.getDisplayName(TextStyle.FULL, new Locale("es", "CO"));
        String nombreMesTransAntrior = mesTransAnterior.getDisplayName(TextStyle.FULL, new Locale("es", "CO"));
        String nombreMesAntrior = mesAnterior.getDisplayName(TextStyle.FULL, new Locale("es", "CO"));
        String nombreMesActual = mesActual.getDisplayName(TextStyle.FULL, new Locale("es", "CO"));
        
        Maestro periodoTransAnterior2 = new Maestro(fechaMesTransAnterior2.getMonthValue());
        periodoTransAnterior2.setNombre(nombreMesTransAntrior2.toUpperCase());
        Maestro periodoTransAnterior = new Maestro(fechaMesTransAnterior.getMonthValue());
        periodoTransAnterior.setNombre(nombreMesTransAntrior.toUpperCase());
        Maestro periodoAnterior = new Maestro(fechaMesAnterior.getMonthValue());
        periodoAnterior.setNombre(nombreMesAntrior.toUpperCase());
        Maestro periodoActual = new Maestro(fechaMesActualr.getMonthValue());
        periodoActual.setNombre(nombreMesActual.toUpperCase());
        
        
        periodos.add(periodoActual);
        periodos.add(periodoAnterior);
        periodos.add(periodoTransAnterior);
        periodos.add(periodoTransAnterior2);
    
        
        return periodos;
    }
    
    public static List<Maestro> listarMeses() {
        List<Maestro> lista = new ArrayList();
        int contador = 1;
        while (contador < 13) {
            Maestro mae = new Maestro(contador);
            Month mes = Month.of(contador);
            mae.setNombre(mes.getDisplayName(TextStyle.FULL, new Locale("es", "CO")).toUpperCase());
            lista.add(mae);
            contador++;
        }
        return lista;
    }
    
     public static void getAuditoriaModificacion(String str) {
        if (str != null && !str.equals("")) {
          String[] strs = str.split("&&");
          if (strs.length > 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ÚLTIMA EDICIÓN", strs[1]));
            }
        }
    }
     
    public static String obtenerNumeroNotaDeNombreArchivo(String nombreArchivo) {
        if (nombreArchivo.startsWith("NA") && nombreArchivo.contains(".")) {
            return  nombreArchivo.substring(2, nombreArchivo.lastIndexOf('.'));
        }
        return "";
    }
    
    public static String validarEstructuraNombreSoportes(String nombreArchivo, Set<String> tipos, 
            Map<String, String> nitFacturaUnicos ) {
        
        if(nombreArchivo == null || tipos  == null){
            return "El nombre de archivo o los tipos de soporte no se han encontrado.";
        }
        
        String nombreFiltrado = nombreArchivo.substring(nombreArchivo.lastIndexOf('/') + 1);
        Matcher matcherMonoUsuario = NOMBRAMIENTO_SOPORTE_MONO_USUARIO.matcher(nombreFiltrado);
        Matcher matcherMultiUsuario = NOMBRAMIENTO_SOPORTE_MULTI_USUARIO.matcher(nombreFiltrado);
        Matcher matcherSoporte;

        if (!matcherMonoUsuario.matches() && !matcherMultiUsuario.matches() ) {
            return ("Nombre de archivo inválido: " + nombreArchivo
                    + ", debe seguir el formato mono usuario ( prefijo_nit_factura.extension ) o"
                    + " multi usuario ( prefijo_nit_factura_documento.extension )");
        }

    
        matcherSoporte = matcherMonoUsuario.matches() ? matcherMonoUsuario : matcherMultiUsuario;

        // Validar que el prefijo esté en la lista permitida
        String prefijo = matcherSoporte.group("prefijo");

        if (!tipos.contains(prefijo)) {
            return ("El archivo : " + nombreArchivo
                    + ", contiene un un prefijo (" + prefijo + ") no definido en el sistema");
        }
        String llave = matcherSoporte.group("nit") + "_" + matcherSoporte.group("factura");

        nitFacturaUnicos.putIfAbsent(llave, nombreArchivo);

        return "";
    }
    
    public static void copyInputStreamToFile(InputStream inputStream, File file)
            throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }
    }

}
