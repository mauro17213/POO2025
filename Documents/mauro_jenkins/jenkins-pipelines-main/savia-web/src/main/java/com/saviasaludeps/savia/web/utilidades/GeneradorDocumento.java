/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.utilidades;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author stive
 */
public class GeneradorDocumento {

    /**
     * Crea un archivo de word con los datos ingresados
     *
     * @param numeroTutela
     * @param juzgado
     * @param tipoReferencia
     * @param usuario
     * @param numeroDocumento
     * @param radicado
     * @param nombreApoderado
     * @param numeroDocumentoApoderado
     * @param tarjetaProfesional
     * @param argumentos
     * @param pretenciones
     * @param rutaFirma
     * @param cargoApoderado
     * @param nombreAsistente
     * @param ruta
     * @param nombreArchivo
     */
    public void generarWord(String numeroTutela, String juzgado, String tipoReferencia, String usuario, String numeroDocumento,
            String radicado, String nombreApoderado, String numeroDocumentoApoderado, String tarjetaProfesional, List<String> argumentos,
            List<String> pretenciones, byte[] rutaFirma, String cargoApoderado, String nombreAsistente, String ruta, String nombreArchivo) {

        XWPFDocument documento = new XWPFDocument();

        XWPFParagraph parrafoSimple = documento.createParagraph();
        parrafoSimple.setSpacingAfter(0);
        XWPFRun run = parrafoSimple.createRun();
        run.setFontSize(10);
        //run.setFontFamily("Arial");
        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd MMMMM yyyy", new Locale("es"));
        run.setText("Medellín, " + formato.format(fechaActual) + "");
        run.addBreak();

        parrafoSimple = documento.createParagraph();
        parrafoSimple.setSpacingAfter(0);
        parrafoSimple.setAlignment(ParagraphAlignment.RIGHT);
        run = parrafoSimple.createRun();
        run.setFontSize(10);
        //run.setFontFamily("Arial");
        run.setBold(true);
        run.setText("Número Interno " + numeroTutela);
        run.addBreak();

        parrafoSimple = documento.createParagraph();
        parrafoSimple.setSpacingAfter(0);
        run = parrafoSimple.createRun();
        run.setFontSize(10);
        //run.setFontFamily("Arial");
        run.setText("Señores");
        run.addBreak();
        run.setText(juzgado);
        run.addBreak();
        run.setText("E.S.D");
        run.addBreak();

        parrafoSimple = documento.createParagraph();
        parrafoSimple.setSpacingAfter(0);
        run = parrafoSimple.createRun();
        run.setBold(true);
        run.setFontSize(10);
        //run.setFontFamily("Arial");
        run.setText("REFERENCIA         : " + tipoReferencia);
        run.addBreak();
        run.setText("ACCIONANTE       : " + usuario);
        run.addBreak();
        run.setText("AFECTADO            : " + usuario);
        run.addBreak();
        run.setText("DOCUMENTO         : " + numeroDocumento);
        run.addBreak();
        run.setText("ACCIONADO         : SAVIA SALUD E.P.S");
        run.addBreak();
        run.setText("RADICADO            : " + radicado);
        run.addBreak();

        parrafoSimple = documento.createParagraph();
        parrafoSimple.setSpacingAfter(0);
        parrafoSimple.setAlignment(ParagraphAlignment.DISTRIBUTE);
        run = parrafoSimple.createRun();
        run.setFontSize(10);
        //run.setFontFamily("Arial");
        run.setBold(false);
        run.setText(nombreApoderado + ", identificado con cédula de ciudadanía No. " + numeroDocumentoApoderado + " y "
                + "tarjeta profesional No. " + tarjetaProfesional + " del Consejo Superior de la Judicatura, en "
                + "calidad de Apoderado judicial de la ALIANZA MEDELLÍN – ANTIOQUIA E.P.S. S.A.S. "
                + "ante las autoridades Judiciales, otorgada por el Representante Legal de la ALIANZA "
                + "MEDELLÍN – ANTIOQUIA E.P.S. S.A.S., con NIT 900604350-0 y con certificado de "
                + "Existencia y Representación Legal de Matrícula 21-485892-12, me permito manifestar:");
        
        for(String argumento: argumentos){
            parrafoSimple = documento.createParagraph();
            parrafoSimple.setSpacingAfter(0);
            parrafoSimple.setAlignment(ParagraphAlignment.DISTRIBUTE);
            run = parrafoSimple.createRun();
            run.setFontSize(10);
            //run.setFontFamily("Arial");
            run.addBreak();
            run.setText(argumento);
            run.addBreak();
        }
        

        parrafoSimple = documento.createParagraph();
        parrafoSimple.setSpacingAfter(0);
        parrafoSimple.setAlignment(ParagraphAlignment.DISTRIBUTE);
        run = parrafoSimple.createRun();
        run.setFontSize(10);
        //run.setFontFamily("Arial");
        parrafoSimple.setAlignment(ParagraphAlignment.CENTER);
        run.setBold(true);
        run.setText("PRETENSIONES");
        run.addBreak();
        
        for(String pretencion: pretenciones){
            parrafoSimple = documento.createParagraph();
            parrafoSimple.setSpacingAfter(0);
            parrafoSimple.setAlignment(ParagraphAlignment.DISTRIBUTE);
            run = parrafoSimple.createRun();
            run.setFontSize(10);
            //run.setFontFamily("Arial");
            run.setBold(false);
            run.setText(pretencion);
            run.addBreak();
        }

        parrafoSimple = documento.createParagraph();
        parrafoSimple.setSpacingAfter(0);
        run = parrafoSimple.createRun();
        run.setFontSize(10);
        //run.setFontFamily("Arial");
        parrafoSimple.setAlignment(ParagraphAlignment.CENTER);
        run.setBold(true);
        run.setText("NOTIFICACIONES");
        run.addBreak();

        parrafoSimple = documento.createParagraph();
        parrafoSimple.setSpacingAfter(0);
        parrafoSimple.setAlignment(ParagraphAlignment.DISTRIBUTE);
        run = parrafoSimple.createRun();
        run.setFontSize(10);
        //run.setFontFamily("Arial");
        run.setBold(false);
        run.setText("Para notificaciones de oficios y demás comunicaciones relacionadas con este trámite "
                + "deberán hacerse en cualquiera de los siguientes medios dispuestos para el efecto, esto "
                + "es al correo electrónico: o a la dirección: ");
        run.setBold(true);
        run.setText("notificacionestutelas@saviasaludeps.com ");
        run.setBold(false);
        run.setText(" Calle 44 No 52 – 165 Sótano de la Alcaldía Taquilla 59.");
        run.addBreak();

        parrafoSimple = documento.createParagraph();
        parrafoSimple.setSpacingAfter(0);
        //parrafoSimple.setAlignment(ParagraphAlignment.DISTRIBUTE);
        run = parrafoSimple.createRun();
        run.setFontSize(10);
        //run.setFontFamily("Arial");
        run.setText("Respetuosamente,");
        run.addBreak();

        try {
            if(rutaFirma != null){
                InputStream stream = new ByteArrayInputStream(rutaFirma);
                stream.mark(0);
                //FileInputStream is = new FileInputStream(rutaFirma);
                run.addBreak();
                run.addPicture(stream, XWPFDocument.PICTURE_TYPE_JPEG, radicado, Units.toEMU(50), Units.toEMU(50));
            // is.close();
            }
        } catch (IOException | InvalidFormatException e) {
            
        }

        run.addBreak();
        run.setText(nombreApoderado);
        run.addBreak();
        run.setText(cargoApoderado);
        run.addBreak();
        run.setText(tarjetaProfesional);
        run.addBreak();
        run.setText("SAVIA SALUD E.P.S.");
        run.addBreak();
        run.setText("Proyectó: " + nombreAsistente);
        run.addBreak();
        run.setText("Cargo: Asistente Legal");

        try {
            FileOutputStream out = new FileOutputStream(new File(ruta + nombreArchivo));
            documento.write(out);
            out.close();
        } catch (IOException e) {
        }

    }

}
