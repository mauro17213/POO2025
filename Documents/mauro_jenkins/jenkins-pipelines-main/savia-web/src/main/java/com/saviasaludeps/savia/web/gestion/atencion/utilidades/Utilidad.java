/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.gestion.atencion.utilidades;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import javax.xml.bind.DatatypeConverter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author idbohorquez
 */
public class Utilidad {

    public static byte[] streamedContentToByteArray(StreamedContent streamedContent) throws IOException {
        if (streamedContent != null) {
            InputStream inputStream = streamedContent.getStream().get();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024]; // Puedes ajustar el tamaño del búfer según tus necesidades

            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            return outputStream.toByteArray();
        }
        return null;
    }
    
    public static String streamedContentToBase64(StreamedContent streamedContent) {
        try {
            // Obtener el InputStream del StreamedContent
            InputStream inputStream = streamedContent.getStream().get();

            byte[] sourceBytes = IOUtils.toByteArray(inputStream);

            return Base64.getEncoder().encodeToString(sourceBytes);            
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String reporteString(JasperPrint jasperPrint) throws Exception{
        byte[] bytes = convertToByteArray(jasperPrint);
        String base64 = DatatypeConverter.printBase64Binary(bytes);
        return base64;
    }
    
    public static byte[] convertToByteArray(JasperPrint jasperPrint) throws JRException {
        byte[] byteArray;
        // Crear un objeto JRPdfExporter
        JRPdfExporter exporter = new JRPdfExporter();

        // Configurar la entrada y salida del exportador
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

        // Exportar el informe a PDF
        exporter.exportReport();

        // Obtener el resultado como un arreglo de bytes
        byteArray = outputStream.toByteArray();

        return byteArray;
    }
    
    public static String reporteByte(byte[] bytes) throws Exception{
        String base64 = DatatypeConverter.printBase64Binary(bytes);
        return base64;
    }
    
}
