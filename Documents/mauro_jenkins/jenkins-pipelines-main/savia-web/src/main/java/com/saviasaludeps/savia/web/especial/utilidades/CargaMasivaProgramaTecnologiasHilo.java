/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.especial.utilidades;

import com.saviasaludeps.savia.dominio.especial.PeCarga;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.especial.PeProgramaTecnologia;
import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaPaquete;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.negocio.especial.PeCargaMasivaRemoto;
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
import com.saviasaludeps.savia.negocio.maestro.MaInsumoRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaMedicamentoRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaPaqueteRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaTecnologiaRemoto;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import java.util.Objects;

/**
 *
 * @author Jaime Andres Olarte
 */
public class CargaMasivaProgramaTecnologiasHilo extends Thread {

    public static final String EXITOSO_STR = "Exitoso";
    public static final String FALLIDO_STR = "Fallido";
    public static final int ESTADO_CARGA_PROCESANDO = 1;
    public static final int ESTADO_CARGA_PROCESADO = 2;
    public static final int ESTADO_CARGA_ABORTADO = 3;

    private PeCarga carga;
    private List<PeProgramaTecnologia> listaCarga;

    private Integer id_programa;
    private Date fechaActual;

    public CargaMasivaProgramaTecnologiasHilo(PeCarga carga, Integer id_programa) {
        try {
            this.fechaActual = new Date();
            this.carga = carga;
            this.id_programa = id_programa;
        } catch (Exception ex) {
            Logger.getLogger(CargaMasivaProgramaTecnologiasHilo.class.getName()).log(Level.SEVERE, null, ex);
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

    @Override
    public void run() {
        cargaTecnologiasPrograma();
    }

    private void cargaTecnologiasPrograma() {
        int pos = 0;
        int exitosos = 0;
        int fallidos = 0;
        StringBuilder error = new StringBuilder();
        StringBuilder contenidoArchivo = new StringBuilder();
        // Obtener registros de tecnologias del archivo de carga
        obtenerListaTecnologias();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (PeProgramaTecnologia objeto : listaCarga) {
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

                    // Validar si existe la tecnologia con el codigo indicado
                    switch (objeto.getTipoTecnologia()) {
                        case AuConstantes.ID_TECNOLOGIA:
                            MaTecnologia maTecnologia = getMaTecnologiaRemoto().consultarPorCodigo(objeto.getMaTecnologiaCodigo());
                            if (maTecnologia == null) {
                                error.append("No existe una tecnología con el código indicado.");
                            }else if(!maTecnologia.isActivo()){
                                error.append("La tecnología no se encuentra activa.");
                            }else {
                                objeto.setMaTecnologiaId(maTecnologia.getId());
                                objeto.setMaTecnologiaCodigo(maTecnologia.getCodigoPropio());
                                objeto.setMaTecnologiaValor(maTecnologia.getCupsDescipcion());
                            }
                            break;
                        case AuConstantes.ID_MEDICAMENTO:
                            MaMedicamento maMedicamento = getMedicamentoRemoto().consultarPorCodigoCum(objeto.getMaTecnologiaCodigo());
                            if (maMedicamento == null) {
                                error.append("No existe un medicamento con el código indicado.");
                            }else if(!maMedicamento.isActivo()){
                                error.append("La tecnología no se encuentra activa.");
                            } else {
                                objeto.setMaTecnologiaId(maMedicamento.getId());
                                objeto.setMaTecnologiaCodigo(maMedicamento.getCum());
                                objeto.setMaTecnologiaValor(maMedicamento.getDescripcionEstandarizada());
                            }
                            break;
                        case AuConstantes.ID_INSUMO:
                            MaInsumo maInsumo = getMaInsumoRemoto().consultarPorCodigo(objeto.getMaTecnologiaCodigo());
                            if (maInsumo == null) {
                                error.append("No existe un insumo con el código indicado.");
                            }else if(!maInsumo.isActivo()){
                                error.append("La tecnología no se encuentra activa.");
                            } else {
                                objeto.setMaTecnologiaId(maInsumo.getId());
                                objeto.setMaTecnologiaCodigo(maInsumo.getCodigo());
                                objeto.setMaTecnologiaValor(maInsumo.getDescripcion());
                            }
                            break;
                        case AuConstantes.ID_PAQUETE:
                            MaPaquete maPaquete = getMaPaqueteRemoto().consultarPorCodigo(objeto.getMaTecnologiaCodigo());
                            if (maPaquete == null) {
                                error.append("No existe un paquete con el código indicado.");
                            }else if(!maPaquete.isActivo()){
                                error.append("La tecnología no se encuentra activa.");
                            } else {
                                objeto.setMaTecnologiaId(maPaquete.getId());
                                objeto.setMaTecnologiaCodigo(maPaquete.getCodigo());
                                objeto.setMaTecnologiaValor(maPaquete.getNombre());
                            }
                            break;
                        default:
                            error.append("El tipo_tecnologia no correspoende a un valor valido.");
                            break;
                    }
                    
                    PeProgramaTecnologia peProgramaTecnologia = getPeProgramaTecnologiasRemoto().consultarTecnologiaPrograma(objeto);
                    if(objeto.getAccion() == PeConstantes.ACCION_CREAR && peProgramaTecnologia.getId() != null){
                        error.append("La tecnología con codigo ").append(objeto.getMaTecnologiaCodigo()).append(" ya se encuentra agregada al programa.");
                    }
                    if(objeto.getAccion() == PeConstantes.ACCION_MODIFICAR && peProgramaTecnologia.getId() == null){
                        error.append("No se encontró tecnologia para modificar con el código indicado");
                    }
                    if(objeto.getAccion() == PeConstantes.ACCION_BORRAR && peProgramaTecnologia.getId() == null){
                        error.append("No se encontró tecnologia para borrar con el código indicado");
                    }else{
                        if(peProgramaTecnologia.isBorrado()){
                            error.append("No se encontró tecnologia para borrar con el código indicado");
                        }
                    }
                                        

                    if (!error.toString().isEmpty()) {
                        throw new Exception(error.toString());
                    }
                    
                    if(Objects.equals(objeto.getAccion(), PeConstantes.ACCION_CREAR)){
                        objeto.setFechaHoraCrea(carga.getFechaHoraCrea());
                        objeto.setTerminalCrea(carga.getTerminalCrea());
                        objeto.setUsuarioCrea(carga.getUsuarioCrea());
                        getPeProgramaTecnologiasRemoto().insertar(objeto);
                    }else if(Objects.equals(objeto.getAccion(), PeConstantes.ACCION_MODIFICAR) ){
                        objeto.setId(peProgramaTecnologia.getId());
                        objeto.setFechaHoraModifica(carga.getFechaHoraCrea());
                        objeto.setTerminalModifica(carga.getTerminalCrea());
                        objeto.setUsuarioModifica(carga.getUsuarioCrea());
                        getPeProgramaTecnologiasRemoto().actualizarMarcaAutomatica(objeto);
                    }else if(Objects.equals(objeto.getAccion(), PeConstantes.ACCION_BORRAR)){
                        objeto.setId(peProgramaTecnologia.getId());
                        objeto.setFechaHoraModifica(carga.getFechaHoraCrea());
                        objeto.setTerminalModifica(carga.getTerminalCrea());
                        objeto.setUsuarioModifica(carga.getUsuarioCrea());
                        getPeProgramaTecnologiasRemoto().eliminar(objeto);  
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
                    Logger.getLogger(CargaMasivaProgramaTecnologiasHilo.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(CargaMasivaProgramaTecnologiasHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void obtenerListaTecnologias() {
        try {
            listaCarga = new ArrayList<>();
            FileReader archivo;
            BufferedReader buffer;
            PeProgramaTecnologia objeto;
            // lectura del archivo en ruta
            String texto;
            archivo = new FileReader(this.carga.getRuta() + this.carga.getArchivo());
            buffer = new BufferedReader(new InputStreamReader(new FileInputStream(this.carga.getRuta() + this.carga.getArchivo()), "utf-8"));
            // leemos la primera linea pero no se usará. esto debido a que es el encabezado.
            texto = buffer.readLine();
            while ((texto = buffer.readLine()) != null) {
                // obtención de campo a campo del valor y transformación
                objeto = new PeProgramaTecnologia();
                objeto = obtenerTenologiasPrograma(texto);
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
            Logger.getLogger(CargaMasivaProgramaTecnologiasHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargaMasivaProgramaTecnologiasHilo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

    private PeProgramaTecnologia obtenerTenologiasPrograma(String texto) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        PeProgramaTecnologia obj = new PeProgramaTecnologia();
        obj.setPeProgramasId(new PePrograma(id_programa));
        int i = 0;
        String[] campos;
        String aux;
        obj.setErrorCarga("");
        //guardamos el registro completo en el objeto
        obj.setRegistroArchivo(texto);
        campos = texto.split(",", 50);

        //tipo_tecnologia
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoNumerico(campos[i].trim(), 1);
            if (aux.equals("")) {
                obj.setTipoTecnologia(Short.parseShort(campos[i].trim()));
            } else {
                aux = obj.getErrorCarga() + "[tipo_tecnologia]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[tipo_tecnologia]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //codigo_tecnologia
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoTextoNumeroGuion(campos[i].trim());
            if (aux.equals("")) {
                obj.setMaTecnologiaCodigo(campos[i].trim());
            } else {
                aux = obj.getErrorCarga() + "[codigo_tecnologia]: " + aux;
                obj.setErrorCarga(aux);
            }
        }
        i++;
        
         //marca automatico
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
            if (valor > 1 || valor < 0) {
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
            bw.write("tipo_tecnologia,codigo_tecnologia,marca_automatica,direcciona,accion,observacion");
            bw.write("\n");
            bw.write(contenidoArchivo.toString());
            bw.close();
        } catch (Exception e) {
            Logger.getLogger(CargaMasivaProgramaTecnologiasHilo.class.getName()).log(Level.SEVERE, null, "Error creando el archivo " + carga.getRuta() + e.getMessage());
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ex1) {
                }
            }
        }
    }

}
