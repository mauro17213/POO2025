/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.especial.utilidades;

import com.saviasaludeps.savia.dominio.especial.PeCarga;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.especial.PeProgramaDiagnostico;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.negocio.especial.PeCargaMasivaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaDiagnosticosRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaRemoto;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.saviasaludeps.savia.negocio.especial.PeProgramaTecnologiasRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaDiagnosticoRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaInsumoRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaMedicamentoRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaPaqueteRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaTecnologiaRemoto;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;

/**
 *
 * @author Jaime Andres Olarte
 */
public class CargaMasivaProgramaDiagnosticoHilo extends Thread {

    public static final String EXITOSO_STR = "Exitoso";
    public static final String FALLIDO_STR = "Fallido";
    public static final int ESTADO_CARGA_PROCESANDO = 1;
    public static final int ESTADO_CARGA_PROCESADO = 2;
    public static final int ESTADO_CARGA_ABORTADO = 3;

    private PeCarga carga;
    private List<PeProgramaDiagnostico> listaCarga;

    private Integer id_programa;
    private Date fechaActual;

    public CargaMasivaProgramaDiagnosticoHilo(PeCarga carga, Integer id_programa) {
        try {
            this.fechaActual = new Date();
            this.carga = carga;
            this.id_programa = id_programa;
        } catch (Exception ex) {
            Logger.getLogger(CargaMasivaProgramaDiagnosticoHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private PeProgramaRemoto getPeProgramaRemoto() throws Exception {
        return (PeProgramaRemoto) RemotoEJB.getEJBRemoto("PeProgramaServicio", PeProgramaRemoto.class.getName());
    }

    private PeCargaMasivaRemoto getCargaMasivaRemoto() throws Exception {
        return (PeCargaMasivaRemoto) RemotoEJB.getEJBRemoto("PeCargaMasivaServicio", PeCargaMasivaRemoto.class.getName());
    }
    
    private PeProgramaTecnologiasRemoto getPeProgramaTecnologiasRemoto() throws Exception {
        return (PeProgramaTecnologiasRemoto) RemotoEJB.getEJBRemoto("PeProgramaTecnologiasServicio", PeProgramaTecnologiasRemoto.class.getName());
    }
    
    private MaTecnologiaRemoto getMaTecnologiaRemoto() throws Exception {
        return (MaTecnologiaRemoto) RemotoEJB.getEJBRemoto("MaTecnologiaServicio", MaTecnologiaRemoto.class.getName());
    }

    private MaMedicamentoRemoto getMedicamentoRemoto() throws Exception {
        return (MaMedicamentoRemoto) RemotoEJB.getEJBRemoto(("MaMedicamentoServicio"), MaMedicamentoRemoto.class.getName());
    }

    private MaInsumoRemoto getMaInsumoRemoto() throws Exception {
        return (MaInsumoRemoto) RemotoEJB.getEJBRemoto("MaInsumoServicio", MaInsumoRemoto.class.getName());
    }

    private MaPaqueteRemoto getMaPaqueteRemoto() throws Exception {
        return (MaPaqueteRemoto) RemotoEJB.getEJBRemoto("MaPaqueteServicio", MaPaqueteRemoto.class.getName());
    }
    
    private MaDiagnosticoRemoto getMaDiagnosticoRemoto() throws Exception {
        return (MaDiagnosticoRemoto) RemotoEJB.getEJBRemoto("MaDiagnosticoServicio", MaDiagnosticoRemoto.class.getName());
    }
    
    private PeProgramaDiagnosticosRemoto getPeProgramaDiagnosticosRemoto() throws Exception {
        return (PeProgramaDiagnosticosRemoto) RemotoEJB.getEJBRemoto("PeProgramaDiagnosticosServicio", PeProgramaDiagnosticosRemoto.class.getName());
    }
    

    @Override
    public void run() {
        cargaDiagnosticosPrograma();
    }

    private void cargaDiagnosticosPrograma() {
        int pos = 0;
        int exitosos = 0;
        int fallidos = 0;
        StringBuilder error = new StringBuilder();
        StringBuilder contenidoArchivo = new StringBuilder();
        // Obtener registros de tecnologias del archivo de carga
        obtenerListaTecnologias();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (PeProgramaDiagnostico objeto : listaCarga) {
            try {
                Date fecha_actual = sdf.parse(sdf.format(new Date()));
                // validamos si el registro en la lista, no contiene errores
                if (objeto.getErrorCarga().equals("")) {
                    //ejecutamos validaciones de negocio
                    error = new StringBuilder();

                    objeto.setPeProgramasId(getPeProgramaRemoto().consultar(id_programa));
                    if (objeto.getPeProgramasId() == null) {
                        error.append("No se encontro ningún programa especial. ");
                    }
                    
                    //Validar si existe un diagnostico con el codigo indicado.
                    MaDiagnostico maDiagnostico = getMaDiagnosticoRemoto().consultarPorCodigo(objeto.getMaDiagnosticoCodigo());
                    if(maDiagnostico != null){
                        objeto.setMaDiagnosticoId(maDiagnostico.getId());
                        objeto.setMaDiagnosticoCodigo(maDiagnostico.getCodigo());
                        objeto.setMaDiagnosticoValor(maDiagnostico.getNombre());
                    }else {
                        error.append("No se encontro ningún diagnostico con el codigo: " + objeto.getMaDiagnosticoCodigo()+". ");
                    }

                    PeProgramaDiagnostico peProgramaDiagnostico = getPeProgramaDiagnosticosRemoto().consultarDiagnosticoPrograma(objeto);                    
                                        
                    if(objeto.getAccion() == PeConstantes.ACCION_CREAR && peProgramaDiagnostico != null){
                        error.append("El diagnostico con codigo ").append(objeto.getMaDiagnosticoCodigo()).append(" ya se encuentra agregado al programa.");
                    }
                    if(objeto.getAccion() == PeConstantes.ACCION_MODIFICAR && peProgramaDiagnostico == null){
                        error.append("No se encontró diagnostico para actualizar con el código indicado");
                    }
                    if((objeto.getAccion() == PeConstantes.ACCION_BORRAR && peProgramaDiagnostico == null)){
                        error.append("No se encontró diagnostico para borrar con el código indicado");
                    }else if(objeto.getAccion() == PeConstantes.ACCION_BORRAR && peProgramaDiagnostico.getBorrado()){
                        error.append("No se encontró diagnostico para borrar con el código indicado");
                    }

                    if (!error.toString().isEmpty()) {
                        throw new Exception(error.toString());
                    }
                    
                    switch(objeto.getAccion()){
                        case PeConstantes.ACCION_CREAR:
                            objeto.setFechaHoraCrea(carga.getFechaHoraCrea());
                            objeto.setTerminalCrea(carga.getTerminalCrea());
                            objeto.setUsuarioCrea(carga.getUsuarioCrea());
                            getPeProgramaDiagnosticosRemoto().insertar(objeto);                            
                            break;                        
                        case PeConstantes.ACCION_MODIFICAR:
                            objeto.setId(peProgramaDiagnostico.getId());
                            objeto.setFechaHoraModifica(carga.getFechaHoraCrea());
                            objeto.setTerminalModifica(carga.getTerminalCrea());
                            objeto.setUsuarioModifica(carga.getUsuarioCrea());
                            getPeProgramaDiagnosticosRemoto().actualizar(objeto);                            
                            break;                        
                        case PeConstantes.ACCION_BORRAR:
                            peProgramaDiagnostico.setId(peProgramaDiagnostico.getId());
                            peProgramaDiagnostico.setFechaHoraModifica(carga.getFechaHoraCrea());
                            peProgramaDiagnostico.setTerminalModifica(carga.getTerminalCrea());
                            peProgramaDiagnostico.setUsuarioModifica(carga.getUsuarioCrea());
                            getPeProgramaDiagnosticosRemoto().eliminar(peProgramaDiagnostico);  
                            break;                        
                    }
                    
                    contenidoArchivo.append(objeto.getProgramaCargaMasiva());
                    contenidoArchivo.append(" ");
                    contenidoArchivo.append(EXITOSO_STR);
                    contenidoArchivo.append(" ");
                    contenidoArchivo.append("\n");
                    exitosos++;
                } else {
                    fallidos++;
                    contenidoArchivo.append(objeto.getProgramaCargaMasiva());
                    contenidoArchivo.append(" ");
                    contenidoArchivo.append(FALLIDO_STR);
                    contenidoArchivo.append(" ").append(objeto.getErrorCarga());
                    contenidoArchivo.append("\n");
                }
            } catch (Exception ex) {
                //Aqui se controla el error                
                fallidos++;
                contenidoArchivo.append(objeto.getProgramaCargaMasiva());
                contenidoArchivo.append(" ");
                contenidoArchivo.append(FALLIDO_STR);
                contenidoArchivo.append(" ");
                contenidoArchivo.append(ex.getMessage());
                contenidoArchivo.append("\n");
            }
            if (pos == 0) {                
                //Actualizar estado de carga
                //carga.setExitosos(exitosos);
                //carga.setFallidos(fallidos);
                carga.setEstado(ESTADO_CARGA_PROCESANDO);
                try {
                    getCargaMasivaRemoto().actualizar(carga);
                } catch (Exception ex) {
                    Logger.getLogger(CargaMasivaProgramaDiagnosticoHilo.class.getName()).log(Level.SEVERE, null, ex);
                }
                pos = 1;
            }
        }

        generarArchivo(contenidoArchivo);
        if (pos > 0) {
            //Actualizar estado de carga
            carga.setExitosos(exitosos);
            carga.setFallidos(fallidos);
            carga.setFechaHoraFin(new Date());
            carga.setEstado(ESTADO_CARGA_PROCESADO);
            try {
                getCargaMasivaRemoto().actualizar(carga);
            } catch (Exception ex) {
                Logger.getLogger(CargaMasivaProgramaDiagnosticoHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void obtenerListaTecnologias() {
        try {
            listaCarga = new ArrayList<>();
            FileReader archivo;
            BufferedReader buffer;
            PeProgramaDiagnostico objeto;
            // lectura del archivo en ruta
            String texto;
            archivo = new FileReader(this.carga.getRuta() + this.carga.getArchivo());
            buffer = new BufferedReader(new InputStreamReader(new FileInputStream(this.carga.getRuta() + this.carga.getArchivo()), "utf-8"));
            // leemos la primera linea pero no se usará. esto debido a que es el encabezado.
            texto = buffer.readLine();
            while ((texto = buffer.readLine()) != null) {
                // obtención de campo a campo del valor y transformación
                objeto = new PeProgramaDiagnostico();
                objeto = obtenerDiagnosticoPrograma(texto);
                objeto.setProgramaCargaMasiva(texto);
                objeto.setSincronizado(0);
                objeto.setUsuarioCrea(this.carga.getUsuarioCrea());
                objeto.setTerminalCrea(this.carga.getTerminalCrea());
                objeto.setFechaHoraCrea(this.carga.getFechaHoraCrea());
                // se guarda el objeto programa tecnologia creado a partir de la lista en el archivo de carga
                listaCarga.add(objeto);
            }
            // termina el proceso.
            buffer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CargaMasivaProgramaDiagnosticoHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargaMasivaProgramaDiagnosticoHilo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

    private PeProgramaDiagnostico obtenerDiagnosticoPrograma(String texto) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        PeProgramaDiagnostico obj = new PeProgramaDiagnostico();
        obj.setPeProgramasId(new PePrograma(id_programa));
        int i = 0;
        String[] campos;
        String aux;
        obj.setErrorCarga("");
        //guardamos el registro completo en el objeto
        obj.setRegistroArchivo(texto);
        campos = texto.split(",", 5);

        //codigo_diagnostico
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoTextoNumero(campos[i].trim());
            if (aux.equals("")) {
                obj.setMaDiagnosticoCodigo(campos[i].trim());
            } else {
                aux = obj.getErrorCarga() + "[codigo_diagnostico]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[codigo_diagnostico]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //marca_automatica
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoActualiza(campos[i].trim());
            if (aux.equals("")) {
                obj.setMarcaAutomatico(convertirNumeroBoolean(Integer.parseInt(campos[i].trim())));
            } else {
                aux = obj.getErrorCarga() + "[marca_automatica]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[marca_automatica]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //direcciona
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoActualiza(campos[i].trim());
            if (aux.equals("")) {
                obj.setDirecciona(convertirNumeroBoolean(Integer.parseInt(campos[i].trim())));
            } else {
                aux = obj.getErrorCarga() + "[direcciona]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[direcciona]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;
        
        //aplica rescate
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoActualiza(campos[i].trim());
            if (aux.equals("")) {
                obj.setAplicaRescate(convertirNumeroBoolean(Integer.parseInt(campos[i].trim())));
            } else {
                aux = obj.getErrorCarga() + "[aplica_rescate]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[aplica_rescate]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //accion
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoNumerico(campos[i].trim(), PeConstantes.ACCION_CREAR, PeConstantes.ACCION_BORRAR);
            if (aux.equals("")) {
                obj.setAccion(Integer.parseInt(campos[i].trim()));
            } else {
                aux = obj.getErrorCarga() + "[accion]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[accion]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        return obj;
    }

    private String validarCampoTexto(String texto) {
        String mensaje = "";
        Pattern patron = Pattern.compile("[A-ZÑÁÀÂÄÉÈÊËÍÌÎÏÓÒÔÖÚÙÛÜ.\\s]+");
        Matcher emparejador = patron.matcher(texto);
        if (!emparejador.matches()) {
            mensaje = "no cumple con el formato Texto.";
        }
        return mensaje;
    }

    private String validarCampoTextoNumero(String texto) {
        String mensaje = "";
        Pattern patron = Pattern.compile("[a-zA-Z0-9]+");
        Matcher emparejador = patron.matcher(texto);
        if (!emparejador.matches()) {
            mensaje = "no cumple con el formato Texto.";
        }
        return mensaje;
    }
    
    private String validarCampoNumerico(String numero, int minimo, int maximo) {
        String mensaje;
        if (numero.matches("\\d*")) {
            if(Integer.parseInt(numero) < minimo || Integer.parseInt(numero) > maximo){
                mensaje = "No cumple con el formato, el valor debe estar entre "+minimo + " y " + maximo;
            }else{
                mensaje = "";
            }            
        } else if (!numero.matches("\\d*")) {
            mensaje = "no cumple con el formato numérico.";
        } else {
            mensaje = "no cumple con el formato permitido.";
        }

        return mensaje;
    }

    private String validarCampoTextoNumeroGuion(String texto) {
        String mensaje = "";
        Pattern patron = Pattern.compile("[a-zA-Z0-9-]+");
        Matcher emparejador = patron.matcher(texto);
        if (!emparejador.matches()) {
            mensaje = "no cumple con el formato Texto.";
        }
        return mensaje;
    }

    private String validarCampoNumerico(String numero, int tamanio) {
        String mensaje;
        if (numero.matches("\\d*") && numero.length() == tamanio) {
            mensaje = "";
        } else if (!numero.matches("\\d*")) {
            mensaje = "no cumple con el formato numérico.";
        } else {
            mensaje = "no cumple con el formato igual a " + tamanio + " dígitos.";
        }

        return mensaje;
    }

    private String validarCampoNumerico(String numero) {
        String mensaje;
        if (numero.matches("\\d*")) {
            mensaje = "";
        } else {
            mensaje = "no cumple con el formato numérico.";
        }
        return mensaje;
    }

    private String validarCampoBoolean(String numero) {
        String mensaje;
        if (numero.matches("\\d*") && (numero.equals("1") || numero.equals("0"))) {
            mensaje = "";
        } else if (!numero.matches("\\d*")) {
            mensaje = "no cumple con el formato boolean.";
        } else {
            mensaje = "no cumple con el formato igual a " + 1 + " dígito.";
        }

        return mensaje;
    }

    private String validarCampoActualiza(String numero) {
        String mensaje;
        if (numero.matches("\\d*")) {
            Integer valor = Integer.parseInt(numero);
            if (valor < 0 || valor > 1) {
                mensaje = "No cumple con el formato, el valor debe ser 0-1";
            } else {
                mensaje = "";
            }
        } else {
            mensaje = "no cumple con el formato numérico.";
        }
        return mensaje;
    }

    private Boolean convertirNumeroBoolean(int numero) {
        return numero == 1;
    }

    private void generarArchivo(StringBuilder contenidoArchivo) {
        File archivo;
        BufferedWriter bw = null;
        try {
            archivo = new File(carga.getRuta(), carga.getArchivo());
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write("codigo_diagnostico,marca_automatica,direcciona,aplica_rescate,accion");
            bw.write("\n");
            bw.write(contenidoArchivo.toString());
            bw.close();
        } catch (Exception e) {
            Logger.getLogger(CargaMasivaProgramaDiagnosticoHilo.class.getName()).log(Level.SEVERE, null, "Error creando el archivo " + carga.getRuta() + e.getMessage());
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ex1) {
                }
            }
        }
    }

}
