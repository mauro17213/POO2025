package com.saviasaludeps.savia.web.financiera.utilidades;


import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.financiera.FinCarga;
import com.saviasaludeps.savia.dominio.financiera.FinGiro;
import com.saviasaludeps.savia.dominio.financiera.FinPostulacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.financiera.FinCargaRemoto;
import com.saviasaludeps.savia.negocio.financiera.FinGiroRemoto;
import com.saviasaludeps.savia.negocio.financiera.FinPostulacionRemoto;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author jepn
 */
public class HiloCargasMasivaFinanciera extends Thread {

    private int idCargaMasiva = 0;
    public static final String SEPARADOR_COMA = ",";
    public static final String EXITOSO_STR = "Exitoso";
    public static final String FALLIDO_STR = "Fallido";
    public static final int ID_EMPRESA_SAVIA = 1;
    public static final int NUMERO_REGISTROS_PROCESADOS_CANCELAR = 50;
    private HashMap<Integer, FinGiro> cacheGiros = new HashMap<>();
    private HashMap<String, CntPrestador> cacheCntPrestadores = new HashMap<>();
    private HashMap<String, Integer> cachePrestadorGiroMemoria = new HashMap<>();
    private HashMap<String, Integer> cachePrestadorGiroDB = new HashMap<>();

    private FinCargaRemoto getFinCargaRemoto() throws Exception {
        return (FinCargaRemoto) RemotoEJB.getEJBRemoto("FinCargaServicio", FinCargaRemoto.class.getName());
    }
    
    private FinPostulacionRemoto getFinPostulacionRemoto() throws Exception {
        return (FinPostulacionRemoto) RemotoEJB.getEJBRemoto("FinPostulacionServicio", FinPostulacionRemoto.class.getName());
    }
    
     private FinGiroRemoto getFinGiroRemoto() throws Exception {
        return (FinGiroRemoto) RemotoEJB.getEJBRemoto("FinGiroServicio", FinGiroRemoto.class.getName());
    }
    
    public HiloCargasMasivaFinanciera(int idCarga) {
        setIdCargaMasiva(idCarga);
    }

    public HashMap<Integer, FinGiro> getCacheGiros() {
        return cacheGiros;
    }

    public void setCacheGiros(HashMap<Integer, FinGiro> cacheGiros) {
        this.cacheGiros = cacheGiros;
    }

    public HashMap<String, CntPrestador> getCacheCntPrestadores() {
        return cacheCntPrestadores;
    }

    public void setCacheCntPrestadores(HashMap<String, CntPrestador> cacheCntPrestadores) {
        this.cacheCntPrestadores = cacheCntPrestadores;
    }

    public HashMap<String, Integer> getCachePrestadorGiroMemoria() {
        return cachePrestadorGiroMemoria;
    }

    public void setCachePrestadorGiroMemoria(HashMap<String, Integer> cachePrestadorGiroMemoria) {
        this.cachePrestadorGiroMemoria = cachePrestadorGiroMemoria;
    }

    public HashMap<String, Integer> getCachePrestadorGiroDB() {
        return cachePrestadorGiroDB;
    }

    public void setCachePrestadorGiroDB(HashMap<String, Integer> cachePrestadorGiroDB) {
        this.cachePrestadorGiroDB = cachePrestadorGiroDB;
    }
    

    @Override
    public void run() {

        FinCarga carga = new FinCarga();
        StringBuilder contenidoArchivo = new StringBuilder();
        limpiarCaches();
        
        try {
           
            int fallidos = 0;
            int exitosos = 0;
            int procesados = 0;

            carga = getFinCargaRemoto().consultar(getIdCargaMasiva());

            actualizarEstadoEnProceso(carga);

            List<FinPostulacion> postulaciones = obtenerPostulaciones(carga);

            for (FinPostulacion postulacion : postulaciones) {
                try {
                    if (postulacion.getNoHayPosulacionTextoError()) {
                        postulacion.setId(getFinPostulacionRemoto().insertar(postulacion));
                        exitosos++;
                        contenidoArchivo.append(Utilidades.agregarNuevoRegistroParaArchivo(
                                postulacion.getPostulacionTexto(),
                                EXITOSO_STR,
                                " - id postulacion : " + postulacion.getId())
                        );

                    } else {
                        fallidos++;
                        contenidoArchivo.append(Utilidades.agregarNuevoRegistroParaArchivo(
                                postulacion.getPostulacionTexto(),
                                FALLIDO_STR,
                                postulacion.getPostulacionTextoError())
                        );
                    }

                    if (verificarCancelacionCarga(getIdCargaMasiva(), procesados)) {
                        break;
                    }

                    procesados++;
                } catch (Exception e) {
                    fallidos++;
                    contenidoArchivo.append(Utilidades.agregarNuevoRegistroParaArchivo(
                            postulacion.getPostulacionTexto(),
                            FALLIDO_STR,
                            e.toString())
                    );
                }
            }
            
            
            actualizarEstadisticaCarga(carga, exitosos, fallidos);
            
            actualizarEstadoFinProceso(carga);
            
            actualizarProcesoRespuesta(contenidoArchivo, carga);
                        
        } catch (Exception ex) {
              try {
                  actualizarEstadoFallidoProceso(carga);
                  actualizarProcesoRespuesta(contenidoArchivo.append(ex.toString()), carga);
                  actualizarFechaFinProcesoFallido(carga);
              } catch (Exception ex1) {}
        }finally{
            limpiarCaches();
        }
        
    }

    private boolean verificarCancelacionCarga(int idCarga, int procesados) throws Exception {
        boolean hayCancelacionCarga = false;

        if ( procesados % NUMERO_REGISTROS_PROCESADOS_CANCELAR == 0 ) {

            FinCarga carga = getFinCargaRemoto().consultar(idCarga);
            if (carga.getEstado() == FinCarga.ESTADO_CANCELADO) {
                hayCancelacionCarga = true;
            }
        }
        return hayCancelacionCarga;
    }

    public int getIdCargaMasiva() {
        return idCargaMasiva;
    }

    private void setIdCargaMasiva(int idCargaMasiva) {
        this.idCargaMasiva = idCargaMasiva;
    }

    public List<FinPostulacion> obtenerPostulaciones(FinCarga finCarga) throws Exception {

        List<FinPostulacion> listaPostulaciones = new ArrayList<>();
        try {

            if (finCarga != null) {

                File archivo = new File(finCarga.getRuta() + finCarga.getArchivo());
                BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "utf-8"));
                String postulacionTexto;

                while ((postulacionTexto = buffer.readLine()) != null) {
                    FinPostulacion postulacion = new FinPostulacion();
                    postulacion = obtenerPostulacionDeTexto(postulacionTexto);
                    postulacion.setPostulacionTexto(postulacionTexto + SEPARADOR_COMA);
                    postulacion.setUsuarioCrea(finCarga.getUsuarioCrea());
                    postulacion.setTerminalCrea(finCarga.getTerminalCrea());
                    postulacion.setFechaHoraCrea(finCarga.getFechaHoraCrea());
                    listaPostulaciones.add(postulacion);
                }
                buffer.close();
            }
               
        } catch (Exception ex) {
            throw ex;
        }
        
        return listaPostulaciones;
    }
    
    public FinPostulacion obtenerPostulacionDeTexto(String registroTexto) {
        FinPostulacion postulacion = new FinPostulacion();
        String[] campos = registroTexto.split(SEPARADOR_COMA);
        String errorTotalPostulacion = "";
        String respuestaParcial ;
        postulacion.setPostulacionTexto(registroTexto);
        postulacion.setPostulacionTextoError("");
        postulacion.setEmpresa(new Empresa(ID_EMPRESA_SAVIA));
        String campo;
    
   
        campo = Utilidades.obtenerCampo(campos,Utilidades.COLUMNA_CONSECUTIVO);
        respuestaParcial = Utilidades.validarNumeroObligatorio(campo);
        if (Utilidades.validarRespuestaError(respuestaParcial)) {
            errorTotalPostulacion += ", [consecutivo]: " + respuestaParcial;
        }
        
        campo = Utilidades.obtenerCampo(campos,Utilidades.COLUMNA_ID_GIRO );
        respuestaParcial = Utilidades.validarNumeroObligatorio(campo);
        if (Utilidades.validarRespuestaError(respuestaParcial)) {
            errorTotalPostulacion += ", [id Giro]: " + respuestaParcial;
        } else {
            postulacion.setFinGiro(new FinGiro(Integer.parseInt(campo)));
            respuestaParcial = obtenerInformacionGiro(postulacion.getFinGiro().getId());
            if (Utilidades.validarRespuestaError(respuestaParcial)) {
                errorTotalPostulacion += ", [id Giro]: " + respuestaParcial;
            }
        }  
        
        campo = Utilidades.obtenerCampo(campos, Utilidades.COLUMNA_TIPO_POSTULACION);
        respuestaParcial = Utilidades.validarTipoPostulacion(campo);
        if (Utilidades.validarRespuestaError(respuestaParcial)) {
            errorTotalPostulacion += ", [tipo postulacion]: " + respuestaParcial;
        } else {
            postulacion.setTipoPostulacion(Integer.parseInt(campo));
        }

        campo = Utilidades.obtenerCampo(campos, Utilidades.COLUMNA_NIT_PRESTADOR);
        respuestaParcial = Utilidades.validarExistenciaCampo(campo);
        if (Utilidades.validarRespuestaError(respuestaParcial)) {
            errorTotalPostulacion += ", [nit Prestador]: " + respuestaParcial;
        } else {
            postulacion.setPrestadorNit(campo);
            respuestaParcial = obtenerInformacionPrestador(campo, postulacion);
            if (Utilidades.validarRespuestaError(respuestaParcial)) {
                errorTotalPostulacion += ", [nit Prestador]: " + respuestaParcial;
            } else {
                respuestaParcial = Utilidades.validarGiroPrestadorExistente(getCachePrestadorGiroMemoria(), postulacion, Utilidades.TIPO_OPERACION_MEMORIA);
                if (Utilidades.validarRespuestaError(respuestaParcial)) {
                    errorTotalPostulacion += ", [nit Prestador]: " + respuestaParcial;
                } else {
                    obtenerInformacionGiroPrestador(postulacion.getFinGiro(), postulacion.getPrestadorNit());
                    respuestaParcial = Utilidades.validarGiroPrestadorExistente(getCachePrestadorGiroDB(), postulacion, Utilidades.TIPO_OPERACION_DB);
                    if (Utilidades.validarRespuestaError(respuestaParcial)) {
                        errorTotalPostulacion += ", [nit Prestador]: " + respuestaParcial;
                    }
                }
            }
        }
         
        campo = Utilidades.obtenerCampo(campos, Utilidades.COLUMNA_MUNICIPIO);
        respuestaParcial = Utilidades.validarExistenciaCampo(campo);
        if (Utilidades.validarRespuestaError(respuestaParcial)) {
            errorTotalPostulacion += ", [municipo]: " + respuestaParcial;
        }else{
            postulacion.setPrestadorMunicipio(campo);
        }
        
        campo = Utilidades.obtenerCampo(campos,Utilidades.COLUMNA_DEPARTAMENTO);
        respuestaParcial = Utilidades.validarExistenciaCampo(campo);
        if (Utilidades.validarRespuestaError(respuestaParcial)) {
            errorTotalPostulacion += ", [Departamento]: " + respuestaParcial;
        } else {
            postulacion.setPrestadorDepartamento(campo);
        }
        
        campo = Utilidades.obtenerCampo(campos, Utilidades.COLUMNA_POSTULACION_ADRES);
        respuestaParcial = Utilidades.validarExistenciaCampo(campo);
        if (Utilidades.validarRespuestaError(respuestaParcial)) {
            errorTotalPostulacion += ", [estado adres]: " + respuestaParcial;
        }else{
            postulacion.setPrestadorEstadoAdres(campo.toUpperCase());
        }
        
        campo = Utilidades.obtenerCampo(campos, Utilidades.COLUMNA_VALOR_CAPITA);
        respuestaParcial = Utilidades.validarDecimalNoObligatorio(campo);
        if (Utilidades.validarRespuestaError(respuestaParcial)) {
              errorTotalPostulacion += ", [valor capita]: " + respuestaParcial;
        }else{
           postulacion.setValorCapita(Utilidades.formatearBigDecimalVacio(campo));
        }
        
        campo = Utilidades.obtenerCampo(campos,Utilidades.COLUMNA_VALOR_REAJUSTE);
        respuestaParcial = Utilidades.validarDecimalNoObligatorio(campo);
        if (Utilidades.validarRespuestaError(respuestaParcial)) {
              errorTotalPostulacion += ", [valor capita reajuste]: " + respuestaParcial;
        }else{
           postulacion.setValorCapitaReajuste(Utilidades.formatearBigDecimalVacio(campo));
        }
        
        campo = Utilidades.obtenerCampo(campos, Utilidades.COLUMNA_VALOR_PGP);
        respuestaParcial = Utilidades.validarDecimalNoObligatorio(campo);
        if (Utilidades.validarRespuestaError(respuestaParcial)) {
            errorTotalPostulacion += ", [valor pgp ]: " + respuestaParcial;
        } else {
            postulacion.setValorPgp(Utilidades.formatearBigDecimalVacio(campo));
        }
        
        campo = Utilidades.obtenerCampo(campos,Utilidades.COLUMNA_VALOR_COMPROMISOS);
        respuestaParcial = Utilidades.validarDecimalNoObligatorio(campo);
        if (Utilidades.validarRespuestaError(respuestaParcial)) {
                errorTotalPostulacion += ", [valor compromisos ]: " + respuestaParcial;
        } else {
            postulacion.setValorCompromisos(Utilidades.formatearBigDecimalVacio(campo));
        }
        
        campo = Utilidades.obtenerCampo(campos,Utilidades.COLUMNA_VALOR_EVENTO);
        respuestaParcial = Utilidades.validarDecimalNoObligatorio(campo);
        if (Utilidades.validarRespuestaError(respuestaParcial)) {
            errorTotalPostulacion += ", [valor evento ]: " + respuestaParcial;
        } else {         
            postulacion.setValorEvento(Utilidades.formatearBigDecimalVacio(campo));
        }
         
        campo = Utilidades.obtenerCampo(campos,Utilidades.COLUMNA_VALOR_TOTAL_PROGRAMADO);
        respuestaParcial = Utilidades.validarDecimalObligatorio(campo);
        if (Utilidades.validarRespuestaError(respuestaParcial)) {
            errorTotalPostulacion += ", [valor programado total ]: " + respuestaParcial;
        } else {
            postulacion.setValorProgramadoTotal(Utilidades.formatearBigDecimalVacio(campo));
        }
       
        postulacion.setTotalPagado(new BigDecimal("0"));
        
        postulacion.setPostulacionTextoError(errorTotalPostulacion);
        
        return postulacion;
    }

    private void actualizarProcesoRespuesta(StringBuilder contenidoArchivo, FinCarga carga) throws Exception {
        if(Utilidades.generarArchivoRespuesta(contenidoArchivo, carga.getRuta(), carga.getRespArchivo())){
            carga.setRespExiste(Boolean.TRUE);
            getFinCargaRemoto().actualizarRespuestaExiste(carga);
        }
    }
    
    
    private void actualizarEstadoEnProceso(FinCarga carga) throws Exception {
        carga.setEstado(FinCarga.ESTADO_EN_PROCESO);
        getFinCargaRemoto().actualizarEstado(carga);
    }
    

    private void actualizarEstadoFinProceso(FinCarga carga) throws Exception {
        carga.setEstado(FinCarga.ESTADO_PROCESADO);
        getFinCargaRemoto().actualizarEstado(carga);
    }
    
    private void actualizarEstadoFallidoProceso(FinCarga carga) throws Exception {
        carga.setEstado(FinCarga.ESTADO_FALLIDO);
        getFinCargaRemoto().actualizarEstado(carga);
    }
    
    private void actualizarFechaFinProcesoFallido(FinCarga carga) throws Exception {
        carga.setFechaHoraFin(new Date());
        getFinCargaRemoto().actualizarFechaFin(carga);
    }

    private void actualizarEstadisticaCarga(FinCarga carga, int exitosos, int fallidos) throws Exception {
        carga.setExitosos(exitosos);
        carga.setFallidos(fallidos);
        carga.setFechaHoraFin(new Date());
        getFinCargaRemoto().actualizarNumeroEjecucion(carga);
    }

    private String obtenerInformacionPrestador(String nitPrestador, FinPostulacion postulacion) {
        String respuesta = "";
         CntPrestador perstador;
        try {
            
            if (getCacheCntPrestadores().get(nitPrestador) == null) {
                perstador = getFinCargaRemoto().consultarPrestadorCarga(nitPrestador);
                getCacheCntPrestadores().put(nitPrestador, perstador);
            } else {
                perstador = getCacheCntPrestadores().get(nitPrestador);
            }
            
            if (perstador != null) {
                postulacion.setCntPrestador(new CntPrestador(perstador.getId()));
                postulacion.setPrestadorRazonSocial(perstador.getRazonSocial());
                postulacion.setPrestadorNaturaleza(perstador.getNaturalezaJuridica());
            } else {
                respuesta = "El prestador no se encuentra en el sistema o esta inactivo.";
            }
        } catch (Exception ex) {
            respuesta = ex.toString();
        }
        return respuesta;
    }
    
    private String obtenerInformacionGiro(int idGiro) {
        String respuesta = "";
        FinGiro giro;
        try {

            if (getCacheGiros().get(idGiro) == null) {
                giro = getFinGiroRemoto().consultar(idGiro);
                getCacheGiros().put(idGiro, giro);
            } else {
                giro = getCacheGiros().get(idGiro);
            }

            if (giro == null) {
                respuesta = "El giro no se encuentra en el sistema";
            }
        } catch (Exception ex) {
            respuesta = ex.toString();
        }
        return respuesta;
    }
    
     private boolean obtenerInformacionGiroPrestador(FinGiro giro, String nitPrestador) {
         
         ParamConsulta paramConsulta = new ParamConsulta();
         paramConsulta.setFiltros(new HashMap<>());
         paramConsulta.setRegistrosPagina(1);
         String clavePrestadorGiro;
         giro = Optional.ofNullable(giro). orElse(new FinGiro());
         nitPrestador = Optional.ofNullable(nitPrestador).orElse(""); 
         
         try {
          
             if (giro.getId() != null && !nitPrestador.equals("")) {
                 clavePrestadorGiro = nitPrestador + "-" + giro.getId();
                 paramConsulta.getFiltros().put("finGiro.id", giro.getId());
                 paramConsulta.getFiltros().put("prestadorNit", nitPrestador);

                 if (getCachePrestadorGiroDB().get(clavePrestadorGiro) == null) {
                     List<FinPostulacion> postulaciones = getFinPostulacionRemoto().consultarLista(paramConsulta);
                     if (!postulaciones.isEmpty()) {
                         getCachePrestadorGiroDB().put(clavePrestadorGiro, Utilidades.TIPO_OPERACION_DB);
                         return true;
                     }
                 }
             }
         } catch (Exception ex) {
             return false;
         }
         return false;
    }
    
    
    
    private void limpiarCaches(){
        getCacheCntPrestadores().clear();
        getCacheGiros().clear();
        getCachePrestadorGiroDB().clear();
        getCachePrestadorGiroMemoria().clear();
    }

}
