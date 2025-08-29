/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.especial.utilidades;

import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.especial.PeCargaGestion;
import com.saviasaludeps.savia.dominio.especial.PeCargaHistorico;
import com.saviasaludeps.savia.dominio.especial.PeGestion;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadosProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeIpsProgramaRemoto;
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
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadoDiagnosticoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeCargaGestionRemoto;
import com.saviasaludeps.savia.negocio.especial.PeCargaHistoricoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeGestionProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeTelefonosRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaDiagnosticoRemoto;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Jaime Andres Olarte
 */
public class CargaMasivaGestionHilo extends Thread {

    public static final int CARGA_PROGRAMAS_ESPECIALES = 3;
    public static final String REGIMEN_SUBSIDIADO = "1";
    public static final String MODELO_LIQUIDACION_EVENTO = "1";
    public static final String EXITOSO_STR = "Exitoso";
    public static final String FALLIDO_STR = "Fallido";
    public static final String CANCELADO_STR = "Cancelado";
    public static final int ESTADO_CARGA_PROCESANDO = 1;
    public static final int ESTADO_CARGA_PROCESADO = 2;
    public static final int ESTADO_CARGA_ABORTADO = 3;

    public static final int TIPO_TELEFONO_FIJO = 1;
    public static final int TIPO_TELEFONO_MOVIL = 2;
    public static final int TIPO_TELEFONO_OFICINA = 3;

    private HashMap<String, Maestro> hashValorTipoDocumento;
    private HashMap<String, Maestro> hashCausaActivo;
    private HashMap<String, Maestro> hashCausaInactivo;
    private HashMap<String, Maestro> hashTipoGestion;

    private PeCargaGestion carga;
    private List<PeGestion> listaCarga;
    private List<Maestro> listCausasActivo;
    private List<Maestro> listCausasInActivo;
    private Date fechaActual;
    private String codigoHabilitacion;
    private PePrograma pePrograma;
    private Usuario usuario;

    public CargaMasivaGestionHilo(PeCargaGestion carga, Usuario usuario) {
        try {
            this.fechaActual = new Date();
            this.carga = carga;
            this.hashValorTipoDocumento = getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA);
            this.hashCausaActivo = getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.PE_CAUSA_ESTADO_ACTIVO);
            this.hashCausaInactivo = getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.PE_CAUSA_ESTADO_INACTIVO);
            this.hashTipoGestion = getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.PE_GESTION_TIPO);
            this.usuario = usuario;
        } catch (Exception ex) {
            Logger.getLogger(CargaMasivaGestionHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
        return (MaestroRemoto) object;
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
        return (AfiliadoRemoto) object;
    }

    private PeProgramaRemoto getPeProgramaRemoto() throws Exception {
        return (PeProgramaRemoto) RemotoEJB.getEJBRemoto("PeProgramaServicio", PeProgramaRemoto.class.getName());
    }

    private CntPrestadorRemoto getPrestadoresRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private PeAfiliadosProgramaRemoto getPeAfiliadosProgramaRemoto() throws Exception {
        return (PeAfiliadosProgramaRemoto) RemotoEJB.getEJBRemoto("PeAfiliadosProgramaServicio", PeAfiliadosProgramaRemoto.class.getName());
    }

    private PeIpsProgramaRemoto getPeIpsProgramaRemoto() throws Exception {
        return (PeIpsProgramaRemoto) RemotoEJB.getEJBRemoto("PeIpsProgramaServicio", PeIpsProgramaRemoto.class.getName());
    }

    private PeCargaGestionRemoto getPeCargaGestionRemoto() throws Exception {
        return (PeCargaGestionRemoto) RemotoEJB.getEJBRemoto(("PeCargaGestionServicio"), PeCargaGestionRemoto.class.getName());
    }

    private MaDiagnosticoRemoto getDiagnosticoRemoto() throws Exception {
        return (MaDiagnosticoRemoto) RemotoEJB.getEJBRemoto("MaDiagnosticoServicio", MaDiagnosticoRemoto.class.getName());
    }

    private PeCargaHistoricoRemoto getPeCargaHistoricoRemoto() throws Exception {
        return (PeCargaHistoricoRemoto) RemotoEJB.getEJBRemoto("PeCargaHistoricoServicio", PeCargaHistoricoRemoto.class.getName());
    }

    private PeTelefonosRemoto getPeTelefonosRemoto() throws Exception {
        return (PeTelefonosRemoto) RemotoEJB.getEJBRemoto("PeTelefonosServicio", PeTelefonosRemoto.class.getName());
    }

    private PeAfiliadoDiagnosticoRemoto getPeAfiliadoDiagnosticoRemoto() throws Exception {
        return (PeAfiliadoDiagnosticoRemoto) RemotoEJB.getEJBRemoto("PeAfiliadoDiagnosticoServicio", PeAfiliadoDiagnosticoRemoto.class.getName());
    }
    
    private PeGestionProgramaRemoto getGestionProgramaRemoto() throws Exception {
        return (PeGestionProgramaRemoto) RemotoEJB.getEJBRemoto("PeGestionProgramaServicio", PeGestionProgramaRemoto.class.getName());
    }

    @Override
    public void run() {
        cargaMasivaGestiones();
    }

    private void cargaMasivaGestiones() {
        int pos = 0;
        int exitosos = 0;
        int fallidos = 0;
        StringBuilder error = new StringBuilder();
        StringBuilder contenidoArchivo = new StringBuilder();
        // Obtener registros de afiliado del archivo de AsegCarga
        obtenerListaCargaGestiones();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<PeGestion> procesados = new ArrayList<>();

        for (PeGestion objeto : listaCarga) {
            try {
                Date fecha_actual = sdf.parse(sdf.format(new Date()));
                // validamos si el registro en la lista, no contiene errores
                if (objeto.getErrorCarga().equals("")) {
                    //ejecutamos validaciones de negocio
                    error = new StringBuilder();
                    
                    PeGestion duplicado = procesados.stream()
                        .filter(x -> (x.getPeAfiliadosProgramasId().getAsegAfiliado().getMaeTipoDocumento() == objeto.getPeAfiliadosProgramasId().getAsegAfiliado().getMaeTipoDocumento() 
                                && x.getPeAfiliadosProgramasId().getAsegAfiliado().getNumeroDocumento().equals(objeto.getPeAfiliadosProgramasId().getAsegAfiliado().getNumeroDocumento())
                                && x.getMaeTipoId() == objeto.getMaeTipoId()
                                && x.getPeAfiliadosProgramasId().getPePrograma().getCodigoPrograma().equals(objeto.getPeAfiliadosProgramasId().getPePrograma().getCodigoPrograma())  ))
                        .findAny()
                        .orElse(null);
                    
                    if(duplicado != null){
                        error.append("Este registro es duplicado de otro ya procesado. ");
                    }
                    
                    //validar si existe el usuario
                    AsegAfiliado afiliado = getAfiliadoRemoto().consultar(objeto.getPeAfiliadosProgramasId().getAsegAfiliado().getMaeTipoDocumento(), objeto.getPeAfiliadosProgramasId().getAsegAfiliado().getNumeroDocumento());
                    if (afiliado == null) {
                        error.append("No se encontro ningún afiliado con estado Activo, por favor validar el estado de la afiliación. ");
                    } 
                    
                    //validar si existe el programa y que este activo
                    PePrograma pePrograma = getPeProgramaRemoto().consultarPorCodigo(objeto.getPeAfiliadosProgramasId().getPePrograma().getCodigoPrograma());
                    if (pePrograma == null) {
                        error.append(String.format("No se encontro ningún programa con el codigo %s  ", objeto.getPeAfiliadosProgramasId().getPePrograma().getCodigoPrograma()));
                    }else {
                        if (!pePrograma.isActivo()) {
                            error.append(String.format("El programa %s no se encuentra activo. ", pePrograma.getDescripcionPrograma()));
                        }
                    } 
                    
                    //validar si el usuario esta en el programa
                    if(afiliado != null && pePrograma != null) {
                        PeAfiliadosPrograma afilaidoPrograma = getPeAfiliadosProgramaRemoto().consultarAfiliadoPorPrograma(afiliado.getId(), pePrograma.getId());
                        if(afilaidoPrograma == null || afilaidoPrograma.getId() == null){
                            error.append("El afiliado no se encuentra matriculado en el programa indicado. ");
                        }else{
                            objeto.setPeAfiliadosProgramasId(afilaidoPrograma);
                        }
                    } 
                    
                    
                    if (!error.toString().isEmpty()) {
                        throw new Exception(error.toString());
                    }
                    
                    objeto.setFechaHoraCrea(carga.getFechaHoraCrea());
                    objeto.setUsuarioCrea(carga.getUsuarioCrea());
                    objeto.setTerminalCrea(carga.getTerminalCrea());
                    objeto.setUsuariosId(usuario);
                    objeto.setFuenteOriegen(PeConstantes.FUENTE_ORIGEN_CARGA);
                    objeto.setBorrado(false);
                    getGestionProgramaRemoto().insertar(objeto);
                    
                    procesados.add(objeto);

                    contenidoArchivo.append(objeto.getGestionCargaMasiva());
                    contenidoArchivo.append(" ");
                    contenidoArchivo.append(EXITOSO_STR);
                    contenidoArchivo.append(" ");
                    contenidoArchivo.append("\n");
                    exitosos++;
                } else {
                    fallidos++;
                    contenidoArchivo.append(objeto.getGestionCargaMasiva());
                    contenidoArchivo.append(" ");
                    contenidoArchivo.append(FALLIDO_STR);
                    contenidoArchivo.append(" ").append(objeto.getErrorCarga());
                    contenidoArchivo.append("\n");
                }
            } catch (Exception ex) {
                //Aqui se controla el error                
                fallidos++;
                contenidoArchivo.append(objeto.getGestionCargaMasiva());
                contenidoArchivo.append(" ");
                contenidoArchivo.append(FALLIDO_STR);
                contenidoArchivo.append(" ");
                contenidoArchivo.append(ex.getMessage());
                contenidoArchivo.append("\n");
            }
            pos++;
            try {
                //Si es Abortado, entonces se procede a finalizar la carga
                PeCargaGestion cargaAux = getPeCargaGestionRemoto().consultar(carga.getId());
                if (cargaAux.getEstado() == ESTADO_CARGA_ABORTADO) {
                    carga.setEstado(ESTADO_CARGA_ABORTADO);
                    carga.setUsuarioModifica(cargaAux.getUsuarioModifica());
                    carga.setExitosos(exitosos);
                    carga.setFallidos(fallidos);
                    carga.setFechaHoraFin(new Date());
                    getPeCargaGestionRemoto().actualizar(carga);
                    if (pos != listaCarga.size()) {
                        for (int i = pos++; i < listaCarga.size(); i++) {
                            PeGestion objetoFallido = listaCarga.get(i);
                            contenidoArchivo.append(objetoFallido.getGestionCargaMasiva());
                            contenidoArchivo.append(" ");
                            contenidoArchivo.append(CANCELADO_STR);
                            contenidoArchivo.append(" ");
                            contenidoArchivo.append("");
                            contenidoArchivo.append("\n");
                        }
                    }
                    generarArchivo(contenidoArchivo);
                    return;
                }
            } catch (Exception ex) {
                Logger.getLogger(CargaMasivaGestionHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Actualizar estado de carga
            carga.setExitosos(exitosos);
            carga.setFallidos(fallidos);
            carga.setEstado(ESTADO_CARGA_PROCESANDO);
            try {
                getPeCargaGestionRemoto().actualizar(carga);
            } catch (Exception ex) {
                Logger.getLogger(CargaMasivaGestionHilo.class.getName()).log(Level.SEVERE, null, ex);
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
                getPeCargaGestionRemoto().actualizar(carga);
            } catch (Exception ex) {
                Logger.getLogger(CargaMasivaGestionHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void obtenerListaCargaGestiones() {
        try {
            listaCarga = new ArrayList<>();
            FileReader archivo;
            BufferedReader buffer;
            PeGestion objeto;
            // lectura del archivo en ruta
            String texto;
            archivo = new FileReader(this.carga.getRuta() + this.carga.getArchivo());
            buffer = new BufferedReader(new InputStreamReader(new FileInputStream(this.carga.getRuta() + this.carga.getArchivo()), "utf-8"));
            // leemos la primera linea pero no se usará. esto debido a que es el encabezado.
            texto = buffer.readLine();
            while ((texto = buffer.readLine()) != null) {
                // obtención de campo a campo del valor y transformación
                objeto = new PeGestion();
                objeto = obtenerGestiones(texto);
                objeto.setGestionCargaMasiva(texto);
                objeto.setSincronizado(0);
                objeto.setUsuarioCrea(this.carga.getUsuarioCrea());
                objeto.setTerminalCrea(this.carga.getTerminalCrea());
                objeto.setFechaHoraCrea(this.carga.getFechaHoraCrea());
                // se guarda el objeto afiliado creado a partir de la lista en listaCarga.
                listaCarga.add(objeto);
            }
            // termina el proceso.
            buffer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CargaMasivaGestionHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargaMasivaGestionHilo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

    private PeGestion obtenerGestiones(String texto) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        PeGestion obj = new PeGestion();
        obj.setPeAfiliadosProgramasId(new PeAfiliadosPrograma());
        obj.getPeAfiliadosProgramasId().setAsegAfiliado(new AsegAfiliado());
        obj.getPeAfiliadosProgramasId().setPePrograma(new PePrograma());
        int i = 0;
        String[] campos;
        String aux;
        obj.setErrorCarga("");
        //guardamos el registro completo en el objeto
        obj.setRegistroArchivo(texto);
        campos = texto.split("\\|", 50);
        //consecutivo
        if (campos[i] == null || campos[i].trim().equals("")) {
            aux = obj.getErrorCarga() + "[consecutivo]: valor nulo. ";
            obj.setErrorCarga(aux);
        } else {            
            aux = validarCampoNumerico(campos[i].trim());
            if (!aux.equals("")) {
                aux = obj.getErrorCarga() + "[consecutivo]: " + aux;
                obj.setErrorCarga(aux);
            }else{
                int consecutivo = Integer.valueOf(campos[i]);
                long repetido = listaCarga.stream().filter(v-> v.getConsecutivo() == Integer.valueOf(consecutivo)).count();
                if(repetido > 0){
                    aux = obj.getErrorCarga() + "[consecutivo]: Ya existe otro registro con este valor. ";
                    obj.setErrorCarga(aux);
                }else{
                    obj.setConsecutivo(consecutivo);
                }
            }
        }
        i++;
        //tipo_documento
        if (getTipoDocumento(campos[i].trim()) != 0) {
            obj.getPeAfiliadosProgramasId().getAsegAfiliado().setMaeTipoDocumento(getTipoDocumento(campos[i].trim()));
        } else {
            aux = obj.getErrorCarga() + "[tipo_documento]: valor nulo.";
            obj.setErrorCarga(aux);
        }
        i++;

        //numero_documento
        if (campos[i] != null && !campos[i].trim().equals("")) {
            obj.getPeAfiliadosProgramasId().getAsegAfiliado().setNumeroDocumento(campos[i].trim());
        } else {
            aux = obj.getErrorCarga() + "[numero_documento]: " + "valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //codigo programa
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoTextoNumero(campos[i].trim());
            if (aux.equals("")) {
                obj.getPeAfiliadosProgramasId().getPePrograma().setCodigoPrograma(campos[i].trim());
            } else {
                aux = obj.getErrorCarga() + "[codigo_programa]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[codigo_programa]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //tipo de gestion
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoNumerico(campos[i].trim());
            if (aux.equals("")) {
                Maestro ma = getTipoGestion(campos[i].trim());
                if (ma != null) {
                    obj.setMaeTipoId(ma.getId());
                    obj.setMaeTipoCodigo(ma.getTipo());
                    obj.setMaeTipoValor(ma.getNombre());
                    obj.setTipo(ma.getId());
                } else {
                    aux = obj.getErrorCarga() + "[tipo_gestion]: El código de maestro no existe. ";
                    obj.setErrorCarga(aux);
                }
            } else {
                aux = obj.getErrorCarga() + "[tipo_gestion]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[tipo_gestion]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //observacion
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = tamanioTexto(campos[i].trim(), 1024);
            if (aux.equals("")) {
                obj.setDescripcion(campos[i].trim());
            } else {
                aux = obj.getErrorCarga() + "[observacion]: " + aux;
                obj.setErrorCarga(aux);
            }
            
        } else {
            aux = obj.getErrorCarga() + "[observacion]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        return obj;
    }

    private int getTipoDocumento(String campo) {
        int resultado = 0;
        try {
            resultado = this.hashValorTipoDocumento.get(campo).getId();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }

    private Maestro getTipoGestion(String campo) {
        Maestro resultado = null;
        try {
            resultado = this.hashTipoGestion.get(campo);
        } catch (Exception e) {
            resultado = null;
        }
        return resultado;
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

    private String validarCampoTextoNumeroSeprarador(String texto) {
        String mensaje = "";
        Pattern patron = Pattern.compile("[a-zA-Z0-9|]+");
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

    private String validarCampoNumericoTamanioMaximo(String numero, int tamanio) {
        String mensaje;
        if (numero.matches("\\d*") && (numero.length() > 0 && numero.length() <= tamanio)) {
            mensaje = "";
        } else if (!numero.matches("\\d*")) {
            mensaje = "no cumple con el formato numérico.";
        } else {
            mensaje = "no cumple con el formato menor que " + tamanio + " dígitos.";
        }

        return mensaje;
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
            bw.write("consecutivo|tipo_doc|numero_documento|codigo_programa|tipo_gestion|observacion");
            bw.write("\n");
            bw.write(contenidoArchivo.toString());
            bw.close();
        } catch (Exception e) {
            Logger.getLogger(CargaMasivaGestionHilo.class.getName()).log(Level.SEVERE, null, "Error creando el archivo " + carga.getRuta() + e.getMessage());
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ex1) {
                }
            }
        }
    }

    private PeCargaHistorico castearHistorico(PeAfiliadosPrograma objeto) {
        PeCargaHistorico historico = new PeCargaHistorico();
        historico.setTipo(PeCargaHistorico.ORIGINAL);
        historico.setPeAfiliadoProgramaId(objeto);
        historico.setCntPrestadorSedeId(objeto.getCntPrestadorSede());
        historico.setFechaDiagnostico(objeto.getFechaDiagnostico());
        historico.setFechaInicioPrograma(objeto.getFechaInicioPrograma());
        historico.setFechaFinPrograma(objeto.getFechaFinPrograma());
        historico.setMaeRegionCorporalId(objeto.getMaeRegionCorporalId());
        historico.setMaeRegionCorporalCodigo(objeto.getMaeRegionCorporalCodigo());
        historico.setMaeRegionCorporalValor(objeto.getMaeRegionCorporalValor());
        return historico;
    }

    private boolean validarEstadoAfiliado(int maeEstadoAfiliacion) {
        try {
            Maestro maeEstado = getMaestroRemoto().consultar(maeEstadoAfiliacion);
            return maeEstado.contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO);
        } catch (Exception ex) {
            return false;
        }
    }
    
    private Integer validarTipoPaciente(Date fechaInicio) {
        Integer tipoPaciente = 0;
        try {
            if (fechaInicio != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                String strDiaActual = sdf.format(new Date());
                String strDiaInicio = sdf.format(fechaInicio);
                Long dia = ChronoUnit.DAYS.between(LocalDate.parse(strDiaInicio), LocalDate.parse(strDiaActual));
                //System.err.println("Dias diferencia : " + dia);
                if (dia < 90) {
                    return PeConstantes.PE_TIPO_PACIENTE_NUEVO;
                }
                if (dia > 90) {
                    return PeConstantes.PE_TIPO_PACIENTE_PREVALENTE;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tipoPaciente;
    }

    private boolean validarSexoAplicaPrograma(AsegAfiliado afiliado, PePrograma programa) {
        if (programa.getSexoAplica() == PeConstantes.APLICA_SEXO_AMBOS) {
            return true;
        }
        if (!PeConstantes.getCodigoSexoAplica(programa.getSexoAplica()).equalsIgnoreCase(afiliado.getMaeGeneroCodigo())) {
            return false;
        }
        return true;
    }

    private boolean validarSexoAplicaDiagnostico(AsegAfiliado afiliado, MaDiagnostico diagnostico) {
        if (diagnostico.getSexoAplica() == PeConstantes.SEXO_APLICA_AMBOS) {
            return true;
        }
        if (!PeConstantes.getCodigoSexoAplicaDiagnostico(diagnostico.getSexoAplica()).equalsIgnoreCase(afiliado.getMaeGeneroCodigo())) {
            return false;
        }
        return true;

    }

    private String validarRangoTelefonoMovil(String movil) {
        String mensaje = "";
        int prefijo = 0;
        //2020-07-27 jyperez se controla excepción debido a que hay datos erróneos ingresados por carga masiva - INC 261
        try {
            if (movil != null && !movil.equals("") && !movil.isBlank()) {
                if (movil.length() >= 3) {
                    prefijo = Integer.valueOf(movil.substring(0, 3));
                    if (prefijo >= 300 && prefijo <= 350) {
                        mensaje = "";
                    } else {
                        mensaje = "El teléfono móvil no inicia entre el rango permitido de valores (300 - 350) ";
                    }

                } else {
                    mensaje = "El teléfono móvil no inicia entre el rango permitido de valores (300 - 350) ";
                }

            }
        } catch (Exception e) {
            mensaje = "El teléfono móvil no contiene un valor numérico.";
        }
        return mensaje;
    }

    private String validarTelefonosFijosNoPermitidos(String fijo) {
        String mensaje = "";
        if (fijo != null && !fijo.equals("") && !fijo.isBlank()) {
            if (PeConstantes.getNumerosFijosNoPermitidos().contains(fijo)) {
                mensaje = "el telefono fijo no puede contener una secuencia de números repetitivos, lo cual lo hace inválido.";
            }
        }
        return mensaje;
    }
    
    private String tamanioTexto(String texto, int tamanio){
        String resultado = "";
        if(texto.length() > tamanio){
            resultado = "El texto supera la cantidad de caracteres permitidad, cantidad maxima (" + tamanio+" caracteres)";
        }
        return resultado;
    }

}
