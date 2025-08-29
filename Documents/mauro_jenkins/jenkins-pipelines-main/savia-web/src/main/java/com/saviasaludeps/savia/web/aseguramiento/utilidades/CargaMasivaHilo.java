/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.utilidades;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegCarga;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegDetalleCarga;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegTraslado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoHistorico;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoHistoricoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.CargaRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.DetalleCargaRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.TrasladoRemoto;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;

/**
 *
 * @author rpalacios
 */
public class CargaMasivaHilo extends Thread {

    public static final int CARGA_AFILIADOS = 1;
    public static final int CARGA_NOVEDADES = 2;
    public static final String GENERO_MASCULINO = "M";
    public static final String GENERO_FEMENINO = "F";
    public static final String ETNIA_INDIGENA = "1";
    public static final String ETNIA_ROM_GITANOS = "2";
    public static final String ETNIA_RAIZAL = "3";
    public static final String ETNIA_PALENQUERO = "4";
    public static final String ETNIA_AFROCOLOMBIANO = "5";
    public static final String ETNIA_OTRAS = "6";
    public static final int ESTADO_CARGA_EN_COLA = 0;
    public static final int ESTADO_CARGA_PROCESANDO = 1;
    public static final int ESTADO_CARGA_PROCESADO = 2;
    public static final int ESTADO_CARGA_ABORTADO = 3;
    public static final int ESTADO_DETALLE_CARGA_INGRESADO = 3;
    public static final int ESTADO_DETALLE_CARGA_FALLIDO = 4;
    public static final String REGIMEN_SUBSIDIADO = "1";
    public static final String REGIMEN_CONTRIBUTIVO = "2";
    public static final String MODELO_LIQUIDACION_CAPITA = "0";
    public static final String MODELO_LIQUIDACION_EVENTO = "1";
    public static final String ESTADO_CIVIL_SOLTERO = "1";
    public static final String ESTADO_CIVIL_CASADO = "2";
    public static final String ESTADO_CIVIL_VIUDO = "3";
    public static final String ESTADO_CIVIL_DIVORCIADO = "4";
    public static final String ESTADO_CIVIL_UNION_LIBRE = "5";
    public static final String ESTADO_CIVIL_DIVORCIADO_SEPARADO = "6";
    public static final String ESTADO_CIVIL_NO_REPORTADO = "7";
    public static final String ESTADO_CIVIL_OTRO = "8";
    public final static String TIPO_AFILIADO_COTIZANTE = "101";
    public final static String TIPO_AFILIADO_BENEFICIARIO = "102";
    public final static String TIPO_AFILIADO_CABEZA_DE_HOGAR = "103";
    public final static String TIPO_AFILIADO_ADICIONAL = "104";
    public final static String PARENTESCO_CONYUGE = "1";
    public final static String PARENTESCO_HIJOS_DEL_COTIZANTE = "2";
    public final static String PARENTESCO_PADRE_O_MADRE = "3";
    public final static String PARENTESCO_HIJOS_BENEFICIARIO = "4";
    public final static String PARENTESCO_TERCER_GRADO_CONSANGUINIDAD = "5";
    public final static String PARENTESCO_PADRES_DEPENDIENTES = "7";
    public final static String PARENTESCO_AFILIADO_ADICIONAL = "8";
    public final static String PARENTESCO_HIJOS_COTIZANTE_CON_DISCAPACIDAD = "9";
    public final static String ZONA_RURAL = "R";
    public final static String ZONA_URBANA = "U";
    public final static String CATEGORIA_IBC_CATEGORIA_A = "101";
    public final static String CATEGORIA_IBC_CATEGORIA_B = "102";
    public final static String CATEGORIA_IBC_CATEGORIA_C = "103";
    public final static String NIVEL_SISBEN_1 = "1";
    public final static String NIVEL_SISBEN_2 = "2";
    public final static String NIVEL_SISBEN_3 = "3";
    public final static String NIVEL_SISBEN_NO_APLICA = "N";
    public final static String TIPO_DISCAPACIDAD_FISICA = "1";
    public final static String TIPO_DISCAPACIDAD_NEURO_SENSORIAL = "2";
    public final static String TIPO_DISCAPACIDAD_MENTAL = "3";
    public final static String CONDICION_DISCAPACIDAD_PERMANENTE = "1";
    public final static String CONDICION_DISCAPACIDAD_TEMPORAL = "2";
    public final static String ESTADO_AFILIACION_PENDIENTE_SOPORTE = "777";
    public final static String ESTADO_AFILIACION_ACTIVO = "101";
    public final static String ESTADO_AFILIACION_INACTIVO = "102";
    public final static String ESTADO_AFILIACION_SUSPENDIDO = "103";
    public final static String ESTADO_AFILIACION_RETIRADO = "104";
    public final static String ESTADO_AFILIACION_FALLECIDO = "107";

    private final int tipoCarga;
    private List<AsegAfiliado> listaCarga;
    private AsegCarga carga;
    private AsegAfiliado objetoAnterior;
    private AfiliadoValidacion validacion;
    private Date fechaActual;
    //Maestros para obtención de datos
    private HashMap<String, Maestro> hashValorGenero;
    private HashMap<String, Maestro> hashValorTipoDocumento;
    private HashMap<String, Maestro> hashValorEstadoAfiliacion;
    private HashMap<Integer, Maestro> hashEstadoAfiliacion;
    private HashMap<Integer, Maestro> hashOrigenAfiliado;
    private HashMap<String, Maestro> hashValorGrupoPoblacional;
    private HashMap<String, Maestro> hashValorActividadEconomica;
    private HashMap<Integer, Maestro> hashCausaNovedadActivacion;
    private HashMap<Integer, Maestro> hashCausaNovedadFallecido;
    private HashMap<Integer, Maestro> hashCausaNovedadRetiro;
    private HashMap<Integer, Maestro> hashCausaNovedadSuspension;
    private HashMap<Integer, Maestro> hashCausaNovedadPendienteSoporte;
    private HashMap<String, Maestro> hashValorARL;
    private HashMap<String, Maestro> hashValorAFP;
    private HashMap<String, Maestro> hashValorCCF;

    public CargaMasivaHilo(int tipoCarga, AsegCarga carga) {
        this.tipoCarga = tipoCarga;
        this.carga = carga;
        validacion = new AfiliadoValidacion();
        fechaActual = new Date();
        try {
            //Cargar
            validacion.setHashCausaNovedad(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.ASEG_CAUSA_NOVEDAD));
            validacion.setHashEstadosAfiliacion(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            validacion.setHashGrupoPoblacional(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.ASEG_GRUPO_POBLACIONAL));
            validacion.setHashOrigenAfiliado(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.ASEG_ORIGEN_AFILIADO));
            validacion.setHashTiposDocumento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            this.hashValorGenero = getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.GN_SEXO);
            this.hashValorTipoDocumento = getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA);
            this.hashValorEstadoAfiliacion = getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.ASEG_ESTADO_AFILIACION);
            this.hashEstadoAfiliacion = getMaestroRemoto().consultarHashPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION);
            this.hashOrigenAfiliado = getMaestroRemoto().consultarHashPorTipo(MaestroTipo.ASEG_ORIGEN_AFILIADO);
            this.hashValorGrupoPoblacional = getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.ASEG_GRUPO_POBLACIONAL);
            this.hashValorActividadEconomica = getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.ASEG_ACTIVIDAD_ECONOMICA);
            this.hashCausaNovedadActivacion = getMaestroRemoto().consultarHashPorTipo(MaestroTipo.ASEG_CAUSA_NOVEDAD);
            this.hashCausaNovedadFallecido = getMaestroRemoto().consultarHashPorTipo(MaestroTipo.ASEG_CAUSA_NOVEDAD);
            this.hashCausaNovedadRetiro = getMaestroRemoto().consultarHashPorTipo(MaestroTipo.ASEG_CAUSA_NOVEDAD);
            this.hashCausaNovedadSuspension = getMaestroRemoto().consultarHashPorTipo(MaestroTipo.ASEG_CAUSA_NOVEDAD);
            this.hashCausaNovedadPendienteSoporte = getMaestroRemoto().consultarHashPorTipo(MaestroTipo.ASEG_CAUSA_NOVEDAD);
            this.hashValorARL = getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.ASEG_ARL);
            this.hashValorAFP = getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.ASEG_AFP);
            this.hashValorCCF = getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.ASEG_CAJA_COMPENSACION);

        } catch (Exception ex) {

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

    private CargaRemoto getCargaRemoto() throws Exception {
        return (CargaRemoto) RemotoEJB.getEJBRemoto(("CargaServicio"), CargaRemoto.class.getName());
    }

    private DetalleCargaRemoto getDetalleCargaRemoto() throws Exception {
        return (DetalleCargaRemoto) RemotoEJB.getEJBRemoto(("DetalleCargaServicio"), DetalleCargaRemoto.class.getName());
    }

    private CntPrestadorRemoto getPrestadoresRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private TrasladoRemoto getTrasladoRemoto() throws Exception {
        return (TrasladoRemoto) RemotoEJB.getEJBRemoto("TrasladoServicio", TrasladoRemoto.class.getName());
    }
    
    private AfiliadoHistoricoRemoto getAfiliadoHistoricoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("AfiliadoHistoricoServicio", AfiliadoHistoricoRemoto.class.getName());
        return (AfiliadoHistoricoRemoto) object;
    }

    @Override
    public void run() {
        if (tipoCarga == CARGA_AFILIADOS) {
            cargaAfiliados();
        } else if (tipoCarga == CARGA_NOVEDADES) {
            cargaNovedades();
        }
    }

    private void cargaAfiliados() {
        int pos = 0;
        int exitosos = 0;
        int fallidos = 0;
        char accionAdicional = 0;// Se inicializa en 0 que es un valor que no esta entre los que se definen para acciones adicionales
        AsegDetalleCarga detalleCarga = null;
        AsegAfiliadoHistorico afiliadoHistorico;
        // Obtener registros de afiliado del archivo de AsegCarga
        obtenerListaCargaAfiliados();

        for (AsegAfiliado objeto : listaCarga) {
            try {
                detalleCarga = new AsegDetalleCarga();
                // validamos si el registro en la lista, no contiene errores
                if (objeto.getErrorCarga().equals("")) {
                    //ejecutamos validaciones de negocio
                    validacion.validarCreacion(objeto, AfiliadoValidacion.TIPO_VALIDACION_CARGA_MASIVA,accionAdicional);
                    if (validacion.isError()) {
                        throw new Exception(validacion.getErroresStr());
                    }
                    // se almacena el registro en AsegAfiliados
                    objeto.setId(getAfiliadoRemoto().insertar(objeto));
                    // dependiendo si se registro datos de BDUA, insertamos en asegTraslados
                    if (objeto.isRegistroBdua()) {
                        objeto.getAsegTraslado().setAsegAfiliado(new AsegAfiliado(objeto.getId()));
                        objeto.getAsegTraslado().setId(getTrasladoRemoto().insertar(objeto.getAsegTraslado()));
                    }
                    //2020-11-20 jyperez adicion de almacenamiento de histórico afiliado
                    afiliadoHistorico = new AsegAfiliadoHistorico();
                    afiliadoHistorico.setTostringAfiliado(objeto.toString());
                    afiliadoHistorico.setUsuarioCrea(objeto.getUsuarioCrea());
                    afiliadoHistorico.setTerminalCrea(objeto.getTerminalCrea());
                    afiliadoHistorico.setFechaHoraCrea(objeto.getFechaHoraCrea());
                    afiliadoHistorico.setAsegAfiliado(new AsegAfiliado(objeto.getId()));
                    getAfiliadoHistoricoRemoto().insertar(afiliadoHistorico);
                    //Aqui se controla el exitoso
                    // se guarda el registro de asegDetalleCarga
                    detalleCarga.setAsegCarga(carga);
                    detalleCarga.setEstado(ESTADO_DETALLE_CARGA_INGRESADO);// Ingresado
                    detalleCarga.setDetalleFallo("");
                    detalleCarga.setData(objeto.getRegistroArchivo().getBytes());// validar que si se guarde como es
                    detalleCarga.setFechaHoraProceso(new Date());
                    getDetalleCargaRemoto().insertar(detalleCarga);
                    exitosos++;
                } else {
                    // el registro contiene errores de transformación de datos, guardamos el detalle carga como error
                    detalleCarga.setAsegCarga(carga);
                    detalleCarga.setEstado(ESTADO_DETALLE_CARGA_FALLIDO);// Fallido
                    detalleCarga.setDetalleFallo("Error: " + objeto.getErrorCarga());
                    detalleCarga.setData(objeto.getRegistroArchivo().getBytes());// validar que si se guarde como es
                    detalleCarga.setFechaHoraProceso(new Date());
                    getDetalleCargaRemoto().insertar(detalleCarga);
                    fallidos++;
                }
            } catch (Exception ex) {
                //Aqui se controla el error
                if (detalleCarga == null) {
                    detalleCarga = new AsegDetalleCarga();
                }
                detalleCarga.setId(null);
                detalleCarga.setAsegCarga(carga);
                detalleCarga.setEstado(ESTADO_DETALLE_CARGA_FALLIDO);// Fallido
                detalleCarga.setDetalleFallo("Error: " + ex.getMessage());
                detalleCarga.setData(objeto.getRegistroArchivo().getBytes());// validar que si se guarde como es
                detalleCarga.setFechaHoraProceso(new Date());
                try {
                    getDetalleCargaRemoto().insertar(detalleCarga);
                    fallidos++;
                } catch (Exception e) {
                    // no deberia fallar al insertar el detalleCarga
                    Logger.getLogger(CargaMasivaHilo.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (pos++ == 50) {
                try {
                    //2020-07-17 jyperez Se valida el estado de carga. Si es Abortado, entonces se procede a finalizar la carga
                    AsegCarga cargaAux = getCargaRemoto().consultar(carga.getId());
                    if (cargaAux.getEstado() == ESTADO_CARGA_ABORTADO) {
                        carga.setEstado(ESTADO_CARGA_ABORTADO);
                        carga.setUsuarioModifica(cargaAux.getUsuarioModifica());
                        carga.setExitosos(exitosos);
                        carga.setFallidos(fallidos);
                        carga.setFechaHoraFin(new Date());
                        getCargaRemoto().actualizar(carga);
                        return;
                    }
                } catch (Exception ex) {
                    Logger.getLogger(CargaMasivaHilo.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Actualizar estado de carga
                carga.setExitosos(exitosos);
                carga.setFallidos(fallidos);
                carga.setEstado(ESTADO_CARGA_PROCESANDO);
                try {
                    getCargaRemoto().actualizar(carga);
                } catch (Exception ex) {
                    Logger.getLogger(CargaMasivaHilo.class.getName()).log(Level.SEVERE, null, ex);
                }
                pos = 1;
            }
        }
        if (pos > 0) {
            //Actualizar estado de carga
            carga.setExitosos(exitosos);
            carga.setFallidos(fallidos);
            carga.setFechaHoraFin(new Date());
            carga.setEstado(ESTADO_CARGA_PROCESADO);
            try {
                getCargaRemoto().actualizar(carga);
            } catch (Exception ex) {
                Logger.getLogger(CargaMasivaHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void cargaNovedades() {
        int pos = 0;
        int exitosos = 0;
        int fallidos = 0;
        AsegDetalleCarga detalleCarga = null;
        AsegAfiliadoHistorico afiliadoHistorico;
        char accionAdicional = 0;// Se inicializa en 0 que es un valor que no esta entre los que se definen para acciones adicionales
        // Obtener registros de afiliado del archivo de AsegCarga
        obtenerListaCargaNovedades();

        for (AsegAfiliado objeto : listaCarga) {
            try {
                detalleCarga = new AsegDetalleCarga();
                // validamos si el registro en la lista, no contiene errores
                if (objeto.getErrorCarga().equals("")) {
                    // obtenemos los datos del afiliado anterior
                    try {
                        objetoAnterior = getAfiliadoRemoto().consultar(objeto.getId());
                    } catch (Exception e) {
                        throw new Exception("Error consultando el afiliado a editar.");
                    }
                    //ejecutamos validaciones de negocio
                    //2020-08-13 se adiciona campo de accion adicional, necesario para validaciones con permisos de usuario. Por el momento enviaremos 0
                    validacion.validarEdicion(objeto, objetoAnterior, AfiliadoValidacion.TIPO_VALIDACION_CARGA_MASIVA, accionAdicional);
                    if (validacion.isError()) {
                        throw new Exception(validacion.getErroresStr());
                    }
                    // actualizamos las fechas de reactivación o de egreso, según sea el caso del afiliado
                    if (objetoAnterior.getMaeEstadoAfiliacion() != objeto.getMaeEstadoAfiliacion()
                            && hashEstadoAfiliacion.get(objeto.getMaeEstadoAfiliacion()).getValor().equals(ESTADO_AFILIACION_ACTIVO)) {
                        objeto.setFechaReactivacion(objeto.getFechaNovedad());
                    } else if (objetoAnterior.getMaeEstadoAfiliacion() != objeto.getMaeEstadoAfiliacion()
                            && hashEstadoAfiliacion.get(objeto.getMaeEstadoAfiliacion()).getValor().equals(ESTADO_AFILIACION_RETIRADO)) {
                        objeto.setFechaEgresoEps(objeto.getFechaNovedad());
                    } else if (objetoAnterior.getMaeEstadoAfiliacion() != objeto.getMaeEstadoAfiliacion()
                            && hashEstadoAfiliacion.get(objeto.getMaeEstadoAfiliacion()).getValor().equals(ESTADO_AFILIACION_FALLECIDO)) {
                        objeto.setFechaEgresoEps(objeto.getFechaNovedad());
                    }
                    // se actualiza el registro en AsegAfiliados
                    getAfiliadoRemoto().actualizar(objeto);
                    //2020-11-20 jyperez adicion de almacenamiento de histórico afiliado
                    afiliadoHistorico = new AsegAfiliadoHistorico();
                    afiliadoHistorico.setTostringAfiliado(objetoAnterior.toString());
                    // se copia la información de auditoria del usuario a modificar, debido a que no existen campos de auditoria de modificación
                    afiliadoHistorico.setUsuarioCrea(objeto.getUsuarioModifica());
                    afiliadoHistorico.setTerminalCrea(objeto.getTerminalModifica());
                    afiliadoHistorico.setFechaHoraCrea(objeto.getFechaHoraModifica());
                    afiliadoHistorico.setAsegAfiliado(new AsegAfiliado(objetoAnterior.getId()));
                    getAfiliadoHistoricoRemoto().insertar(afiliadoHistorico);
                    //Aqui se controla el exitoso
                    // se guarda el registro de asegDetalleCarga
                    detalleCarga.setAsegCarga(carga);
                    detalleCarga.setEstado(ESTADO_DETALLE_CARGA_INGRESADO);// Ingresado
                    detalleCarga.setDetalleFallo("");
                    detalleCarga.setData(objeto.getRegistroArchivo().getBytes());// validar que si se guarde como es
                    detalleCarga.setFechaHoraProceso(new Date());
                    getDetalleCargaRemoto().insertar(detalleCarga);
                    exitosos++;
                } else {
                    // el registro contiene errores de transformación de datos, guardamos el detalle carga como error
                    detalleCarga.setAsegCarga(carga);
                    detalleCarga.setEstado(ESTADO_DETALLE_CARGA_FALLIDO);// Fallido
                    detalleCarga.setDetalleFallo("Error: " + objeto.getErrorCarga());
                    detalleCarga.setData(objeto.getRegistroArchivo().getBytes());// validar que si se guarde como es
                    detalleCarga.setFechaHoraProceso(new Date());
                    getDetalleCargaRemoto().insertar(detalleCarga);
                    fallidos++;
                }
            } catch (Exception ex) {
                //Aqui se controla el error
                if (detalleCarga == null) {
                    detalleCarga = new AsegDetalleCarga();
                }
                detalleCarga.setId(null);
                detalleCarga.setAsegCarga(carga);
                detalleCarga.setEstado(ESTADO_DETALLE_CARGA_FALLIDO);// Fallido
                if (ex.getMessage() == null) {
                    detalleCarga.setDetalleFallo("Error: Error no controlado en el Sistema. Puede ocurrir por inconsistencia de Datos. Descripción: " + ex.toString());
                } else {
                    detalleCarga.setDetalleFallo("Error: " + ex.getMessage());
                }
                detalleCarga.setData(objeto.getRegistroArchivo().getBytes());// validar que si se guarde como es
                detalleCarga.setFechaHoraProceso(new Date());
                try {
                    getDetalleCargaRemoto().insertar(detalleCarga);
                    fallidos++;
                } catch (Exception e) {
                    // no deberia fallar al insertar el detalleCarga
                    Logger.getLogger(CargaMasivaHilo.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (pos++ == 50) {
                try {
                    //2020-07-17 jyperez Se valida el estado de carga. Si es Abortado, entonces se procede a finalizar la carga
                    AsegCarga cargaAux = getCargaRemoto().consultar(carga.getId());
                    if (cargaAux.getEstado() == ESTADO_CARGA_ABORTADO) {
                        carga.setEstado(ESTADO_CARGA_ABORTADO);
                        carga.setUsuarioModifica(cargaAux.getUsuarioModifica());
                        carga.setExitosos(exitosos);
                        carga.setFallidos(fallidos);
                        carga.setFechaHoraFin(new Date());
                        getCargaRemoto().actualizar(carga);
                        return;
                    }
                } catch (Exception ex) {
                    Logger.getLogger(CargaMasivaHilo.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Actualizar estado de carga
                carga.setExitosos(exitosos);
                carga.setFallidos(fallidos);
                carga.setEstado(ESTADO_CARGA_PROCESANDO);
                try {
                    getCargaRemoto().actualizar(carga);
                } catch (Exception ex) {
                    Logger.getLogger(CargaMasivaHilo.class.getName()).log(Level.SEVERE, null, ex);
                }
                pos = 1;
            }
        }
        if (pos > 0) {
            //Actualizar estado de carga
            carga.setExitosos(exitosos);
            carga.setFallidos(fallidos);
            carga.setFechaHoraFin(new Date());
            carga.setEstado(ESTADO_CARGA_PROCESADO);
            try {
                getCargaRemoto().actualizar(carga);
            } catch (Exception ex) {
                Logger.getLogger(CargaMasivaHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Función que obtiene del archivo cargado, los registros y los transforma
     * uno a uno en un objeto de Afiliado
     *
     */
    private void obtenerListaCargaAfiliados() {
        try {
            listaCarga = new ArrayList<>();
            FileReader archivo;
            BufferedReader buffer;
            AsegAfiliado objeto;
            // lectura del archivo en ruta
            String texto;
            archivo = new FileReader(this.carga.getRuta() + this.carga.getArchivo());
            buffer = new BufferedReader(new InputStreamReader(new FileInputStream(this.carga.getRuta() + this.carga.getArchivo()), "utf-8"));
            // leemos la primera linea pero no se usará. esto debido a que es el encabezado.
            texto = buffer.readLine();
            while ((texto = buffer.readLine()) != null) {
                // obtención de campo a campo del valor y transformación
                objeto = obtenerAfiliado(texto);
                // 2020-07-23 jyperez incidente 259 se adiciona el valor de sincronizado en 0
                objeto.setSincronizado(0);
                // copiamos en el objeto los datos de auditoria cargados en carga
                objeto.setUsuarioCrea(this.carga.getUsuarioCrea());
                objeto.setTerminalCrea(this.carga.getTerminalCrea());
                objeto.setFechaHoraCrea(this.carga.getFechaHoraCrea());
                if (objeto.getAsegTraslado() != null) {
                    objeto.getAsegTraslado().setUsuarioCrea(this.carga.getUsuarioCrea());
                    objeto.getAsegTraslado().setTerminalCrea(this.carga.getTerminalCrea());
                    objeto.getAsegTraslado().setFechaHoraCrea(this.carga.getFechaHoraCrea());
                }
                // se guarda el objeto afiliado creado a partir de la lista en listaCarga.
                listaCarga.add(objeto);
            }
            // termina el proceso.
            buffer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CargaMasivaHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargaMasivaHilo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

    private AsegAfiliado obtenerAfiliado(String texto) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = new Date();
        Calendar cal;
        AsegAfiliado obj = new AsegAfiliado();
        int i = 0;
        String[] campos;
        String aux;
        obj.setErrorCarga("");
        // valores por defecto de los campos obligatorios
        obj.setRegimen(REGIMEN_SUBSIDIADO);
        obj.setModeloLiquidacion(MODELO_LIQUIDACION_EVENTO);
        obj.setIdAfiliado("0");

        //guardamos el registro completo en el objeto
        obj.setRegistroArchivo(texto);
        campos = texto.split(",",50);
        //consecutivo
        i++;
        //existe_bdua
        if (campos[i].trim().equals("1")) {
            obj.setRegistroBdua(true);
            obj.setAsegTraslado(new AsegTraslado());
            i++;
            //serial_bdua
            if (campos[i] != null && !campos[i].trim().equals("")) {
                try {
                    obj.setSerialBdua(BigInteger.valueOf(Long.valueOf(campos[i].trim())));
                } catch (NumberFormatException e) {
                    //obj.setSerialBdua(new BigInteger("0"));
                    aux = obj.getErrorCarga() + "[serial_bdua]: valor no válido.";
                    obj.setErrorCarga(aux);
                }
            }
            i++;
            //tipo_documento_bdua
            obj.getAsegTraslado().setTipoDocumentoBdua(String.valueOf(getTipoDocumento(campos[i].trim())));
            i++;
            //numero_documento_bdua
            obj.getAsegTraslado().setNumeroDocumentoBdua(campos[i].trim());
            i++;
            //primer_apellido_bdua
            obj.getAsegTraslado().setPrimerApellidoBdua(campos[i].trim());
            i++;
            //segundo_apellido_bdua
            obj.getAsegTraslado().setSegundoApellidoBdua(campos[i].trim());
            i++;
            //primer_nombre_bdua
            obj.getAsegTraslado().setPrimerNombreBdua(campos[i].trim());
            i++;
            //segundo_nombre_bdua
            obj.getAsegTraslado().setSegundoNombreBdua(campos[i].trim());
            i++;
            //fecha_nacimiento_bdua
            try {
                obj.getAsegTraslado().setFechaNacimientoBdua(sdf.parse(campos[i].trim()));
            } catch (ParseException ex) {
                //Logger.getLogger(CargaMasivaHilo.class.getName()).log(Level.SEVERE, null, ex);
                obj.getAsegTraslado().setFechaNacimientoBdua(null);
            }
            i++;
            //genero_bdua
            obj.getAsegTraslado().setGeneroBdua(getGenero(campos[i].trim()));
            i++;
        } else {
            obj.setRegistroBdua(false);
            i = i + 10;// los campos que se corren porque no vendría la información del registro
        }
        //tipo_documento
        if (getTipoDocumento(campos[i].trim()) != 0) {
            obj.setMaeTipoDocumento(getTipoDocumento(campos[i].trim()));
        } else {
            aux = obj.getErrorCarga() + "[tipo_documento]: valor nulo.";
            obj.setErrorCarga(aux);
        }
        i++;
        //numero_documento
        obj.setNumeroDocumento(campos[i].trim());
        i++;
        //primer_apellido
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoTexto(campos[i].trim());
            if (aux.equals("")) {
                obj.setPrimerApellido(campos[i].trim());
            } else {
                aux = obj.getErrorCarga() + "[primer_apellido]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[primer_apellido]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;
        //segundo_apellido
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoTexto(campos[i].trim());
            if (aux.equals("")) {
                obj.setSegundoApellido(campos[i].trim());
            } else {
                aux = obj.getErrorCarga() + "[segundo_apellido]: " + aux;
                obj.setErrorCarga(aux);
            }
        }
        i++;
        //primer_nombre
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoTexto(campos[i].trim());
            if (aux.equals("")) {
                obj.setPrimerNombre(campos[i].trim());
            } else {
                aux = obj.getErrorCarga() + "[primer_nombre]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[primer_nombre]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;
        //segundo_nombre
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoTexto(campos[i].trim());
            if (aux.equals("")) {
                obj.setSegundoNombre(campos[i].trim());
            } else {
                aux = obj.getErrorCarga() + "[segundo_nombre]: " + aux;
                obj.setErrorCarga(aux);
            }
        }
        i++;
        //fecha_nacimiento
        try {
            obj.setFechaNacimiento(sdf.parse(campos[i].trim()));
        } catch (ParseException ex) {
            //Logger.getLogger(CargaMasivaHilo.class.getName()).log(Level.SEVERE, null, ex);
            obj.setFechaNacimiento(null);
            aux = obj.getErrorCarga() + "[fecha_nacimiento]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;
        //genero
        if (getGenero(campos[i]) != null) {
            obj.setGenero(getGenero(campos[i].trim()));
        } else {
            aux = obj.getErrorCarga() + "[genero]: valor erróneo.";
            obj.setErrorCarga(aux);
        }
        i++;
        //fecha_expedicion_cedula
        try {
            obj.setFechaExpedicionCedula(sdf.parse(campos[i].trim()));
        } catch (ParseException ex) {
            //Logger.getLogger(CargaMasivaHilo.class.getName()).log(Level.SEVERE, null, ex);
            obj.setFechaExpedicionCedula(null);
        }
        i++;
        //tipo_documento_cf
        obj.setMaeTipoDocumentoCf(getTipoDocumento(campos[i].trim()));
        i++;
        //numero_documento_cf
        obj.setNumeroDocumentoCf(campos[i].trim());
        i++;
        //fecha_afiliacion_sgsss
        try {
            obj.setFechaAfiliacionSgsss(sdf.parse(campos[i].trim()));
        } catch (ParseException ex) {
            //Logger.getLogger(CargaMasivaHilo.class.getName()).log(Level.SEVERE, null, ex);
            obj.setFechaAfiliacionSgsss(null);
        }
        i++;
        //fecha_afiliacion_eps
        try {
            obj.setFechaAfiliacionEps(sdf.parse(campos[i].trim()));
        } catch (ParseException ex) {
            //Logger.getLogger(CargaMasivaHilo.class.getName()).log(Level.SEVERE, null, ex);
            obj.setFechaAfiliacionEps(null);
        }
        i++;
        //tipo_beneficiario (tipo Afiliado)
        obj.setTipoBeneficiario(getTipoAfiliado(campos[i].trim()));
        if (obj.getTipoBeneficiario().equals("")) {
            aux = obj.getErrorCarga() + "[tipo_beneficiario]: valor no válido.";
            obj.setErrorCarga(aux);
        }
        i++;
        //estado_afiliacion
        obj.setMaeEstadoAfiliacion(getEstadoAfiliacion(campos[i].trim()));
        if (obj.getMaeEstadoAfiliacion() == 0) {
            aux = obj.getErrorCarga() + "[estado_afiliacion]: valor erróneo.";
            obj.setErrorCarga(aux);
        }
        i++;
        //origen_afiliado
        obj.setMaeOrigenAfiliado(getOrigenAfiliado(campos[i].trim()));
        if (obj.getMaeOrigenAfiliado() == 0) {
            aux = obj.getErrorCarga() + "[origen_afiliado]: valor erróneo.";
            obj.setErrorCarga(aux);
        }
        i++;
        //parentesco_cotizante
        obj.setParentescoCotizante(getParentesco(campos[i].trim()));
        i++;
        //autoriza_envio_sms
        switch (campos[i].trim()) {
            case "S":
                obj.setAutorizaEnvioSms(true);
                break;
            default:
                obj.setAutorizaEnvioSms(false);
                break;
        }
        i++;
        //autoriza_envio_email
        switch (campos[i].trim()) {
            case "S":
                obj.setAutorizaEnvioEmail(true);
                break;
            default:
                obj.setAutorizaEnvioEmail(false);
                break;
        }
        i++;
        //telefono_fijo
        if (!campos[i].trim().equals("")) {
            aux = validarCampoNumerico(campos[i].trim(), 7);
            if (aux.equals("")) {
                obj.setTelefonoFijo(campos[i].trim());
            } else {
                aux = obj.getErrorCarga() + "[telefono_fijo]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            obj.setTelefonoFijo(campos[i].trim());
        }
        i++;
        //telefono_movil
        if (!campos[i].trim().equals("")) {
            aux = validarCampoNumerico(campos[i].trim(), 10);
            if (aux.equals("")) {
                obj.setTelefonoMovil(campos[i].trim());
            } else {
                aux = obj.getErrorCarga() + "[telefono_movil]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            obj.setTelefonoMovil(campos[i].trim());
        }
        i++;
        //email
        obj.setEmail(campos[i].trim());
        i++;
        //afiliacion_ubicaciones
        obj.setAfiliacionUbicacion(obtenerUbicacion(campos[i].trim()));
        obj.setResidenciaUbicacion(obj.getAfiliacionUbicacion());
        if (obj.getAfiliacionUbicacion() == null) {
            aux = obj.getErrorCarga() + "[afiliacion_ubicaciones]: valor no válido.";
            obj.setErrorCarga(aux);
        }
        i++;
        //zona
        obj.setZona(getZona(campos[i].trim()));
        if (obj.getZona().equals("")) {
            aux = obj.getErrorCarga() + "[zona]: valor no válido.";
            obj.setErrorCarga(aux);
        }
        i++;
        //direccion_residencia
        if (campos[i] != null && !campos[i].trim().equals("")) {
            obj.setDireccionResidencia(campos[i].trim());
        } else {
            aux = obj.getErrorCarga() + "[direccion_residencia]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;
        //nivel_sisben
        obj.setNivelSisben(getNivelSisben(campos[i].trim()));
        if (obj.getNivelSisben().equals("")) {
            aux = obj.getErrorCarga() + "[nivel_sisben]: valor no válido.";
            obj.setErrorCarga(aux);
        }
        i++;
        //puntaje_sisben
        try {
            obj.setPuntajeSisben(Double.parseDouble(campos[i].trim()));
        } catch (Exception e) {
            // si el valor llega malo o nulo, se ingresará el valor 0. Las validaciones de negocio se encargan de determinar 
            //la obligatoriedad del campo y si el valor es correcto para el nivel
            obj.setPuntajeSisben(new Double(0));
            //aux = obj.getErrorCarga() + "[puntaje_sisben]: valor no válido.";
            //obj.setErrorCarga(aux);
        }
        i++;
        //ficha_sisben
        obj.setFichaSisben(campos[i].trim());
        i++;
        //fecha_sisben
        try {
            obj.setFechaSisben(sdf.parse(campos[i].trim()));
        } catch (ParseException ex) {
            //Logger.getLogger(CargaMasivaHilo.class.getName()).log(Level.SEVERE, null, ex);
            obj.setFechaSisben(null);
        }
        i++;
        //primaria_prestador_sedes_id
        obj.setPrimariaPrestadorSede(getPrestadorSede(campos[i].trim()));
        if (obj.getPrimariaPrestadorSede() == null) {
            aux = obj.getErrorCarga() + "[primaria_prestador_sedes_id]: valor no válido.";
            obj.setErrorCarga(aux);
        }
        i++;
        //discapacidad
        switch (campos[i].trim()) {
            case "S":
                obj.setDiscapacidad(true);
                break;
            default:
                obj.setDiscapacidad(false);
                break;
        }
        i++;
        //tipo_discapacidad
        // VALIDAR
        obj.setTipoDiscapacidad(campos[i].trim());
        i++;
        //condicion_discapacidad
        // VALIDAR
        obj.setCondicionDiscapacidad(campos[i].trim());
        i++;
        //fecha_Inicio_discapacidad
        try {
            obj.setFechaIniciodiscapacidad(sdf.parse(campos[i].trim()));
        } catch (ParseException ex) {
            //Logger.getLogger(CargaMasivaHilo.class.getName()).log(Level.SEVERE, null, ex);
            obj.setFechaIniciodiscapacidad(null);
        }
        i++;
        //fecha_fin_discapacidad
        try {
            obj.setFechaFinDiscapacidad(sdf.parse(campos[i].trim()));
        } catch (ParseException ex) {
            //Logger.getLogger(CargaMasivaHilo.class.getName()).log(Level.SEVERE, null, ex);
            obj.setFechaFinDiscapacidad(null);
        }
        i++;
        //grupo_poblacional
        obj.setMaeGrupoPoblacional(getGrupoPoblacional(campos[i].trim()));
        if (obj.getMaeGrupoPoblacional() == 0) {
            aux = obj.getErrorCarga() + "[grupo_poblacional]: valor no válido.";
            obj.setErrorCarga(aux);
        }
        i++;
        //victima
        switch (campos[i].trim()) {
            case "S":
                obj.setVictima(true);
                break;
            case "N":
                obj.setVictima(false);
                break;
            default:
                aux = obj.getErrorCarga() + "[victima]: valor no válido.";
                obj.setErrorCarga(aux);
                break;
        }
        i++;
        //etnia
        obj.setEtnia(getEtnia(campos[i].trim()));
        if (obj.getEtnia().equals("")) {
            aux = obj.getErrorCarga() + "[etnia]: valor no válido.";
            obj.setErrorCarga(aux);
        }
        i++;
        //estado_civil
        obj.setEstadoCivil(getEstadoCivil(campos[i].trim()));
        if (obj.getEstadoCivil().equals("")) {
            aux = obj.getErrorCarga() + "[estado_civil]: valor no válido.";
            obj.setErrorCarga(aux);
        }
        i++;
        return obj;
    }

    /**
     * Función que obtiene del archivo cargado, los registros y los transforma
     * uno a uno en un objeto de Afiliado
     *
     */
    private void obtenerListaCargaNovedades() {
        try {
            listaCarga = new ArrayList<>();
            FileReader archivo;
            BufferedReader buffer;
            AsegAfiliado objeto;
            // lectura del archivo en ruta
            String texto;
            archivo = new FileReader(this.carga.getRuta() + this.carga.getArchivo());
            buffer = new BufferedReader(new InputStreamReader(new FileInputStream(this.carga.getRuta() + this.carga.getArchivo()), "utf-8"));
            // leemos la primera linea pero no se usará. esto debido a que es el encabezado.
            buffer.readLine();
            while ((texto = buffer.readLine()) != null) {
                // obtención de campo a campo del valor y transformación
                objeto = obtenerNovedadesAfiliado(texto);
                // copiamos en el objeto los datos de auditoria cargados en carga
                objeto.setUsuarioModifica(this.carga.getUsuarioModifica());
                objeto.setTerminalModifica(this.carga.getTerminalModifica());
                objeto.setFechaHoraModifica(this.carga.getFechaHoraModifica());
                // 2020-07-28 jyperez incidente 26X se adiciona el valor de sincronizado en 0
                objeto.setSincronizado(0);
                // se guarda el objeto afiliado creado a partir de la lista en listaCarga.
                listaCarga.add(objeto);
            }
            // termina el proceso.
            buffer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CargaMasivaHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargaMasivaHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private AsegAfiliado obtenerNovedadesAfiliado(String texto) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = new Date();
        Calendar cal;
        AsegAfiliado obj;
        int i = 0;
        String[] campos;
        String aux;
        campos = texto.split(",",59);
        //Consecutivo
        i++;
        //contrato_afiliado ( llega pero no debemos cambiarlo, con este consultamos el afiliado)
        // obtenemos el afiliado
        try {
            obj = getAfiliadoRemoto().consultar(campos[i].trim());
            if (obj == null) {
                throw new Exception("Usuario no existe");
            }
        } catch (Exception e) {
            obj = new AsegAfiliado();
            obj.setErrorCarga("El contrato_afiliado no está registrado en el sistema");
            //guardamos el registro completo en el objeto
            obj.setRegistroArchivo(texto);
            return obj;
        }
        obj.setErrorCarga("");
        //guardamos el registro completo en el objeto
        obj.setRegistroArchivo(texto);
        i++;
        //registro_bdua
        switch (campos[i].trim()) {
            case "1":
                obj.setRegistroBdua(true);
                break;
            default:
                obj.setRegistroBdua(false);
                break;
        }
        i++;
        //serial_bdua
        if (campos[i] != null && !campos[i].trim().equals("")) {
            try {
                obj.setSerialBdua(BigInteger.valueOf(Long.valueOf(campos[i].trim())));
            } catch (Exception e) {
                //obj.setSerialBdua(new BigInteger("0"));
                aux = obj.getErrorCarga() + "[serial_bdua]: valor no válido.";
                obj.setErrorCarga(aux);
            }
        }
        i++;
        //tipo_documento
        obj.setMaeTipoDocumento(getTipoDocumento(campos[i].trim()));
        if (obj.getMaeTipoDocumento() == 0) {
            aux = obj.getErrorCarga() + "[tipo_documento]: valor nulo.";
            obj.setErrorCarga(aux);
        }
        i++;
        //numero_documento
        obj.setNumeroDocumento(campos[i].trim());
        i++;
        //fecha_expedicion_cedula
        try {
            obj.setFechaExpedicionCedula(sdf.parse(campos[i].trim()));
        } catch (ParseException ex) {
            //Logger.getLogger(CargaMasivaHilo.class.getName()).log(Level.SEVERE, null, ex);
            obj.setFechaExpedicionCedula(null);
        }
        i++;
        //primer_apellido
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoTexto(campos[i].trim());
            if (aux.equals("")) {
                obj.setPrimerApellido(campos[i].trim());
            } else {
                aux = obj.getErrorCarga() + "[primer_apellido]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[primer_apellido]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;
        //segundo_apellido
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoTexto(campos[i].trim());
            if (aux.equals("")) {
                obj.setSegundoApellido(campos[i].trim());
            } else {
                aux = obj.getErrorCarga() + "[segundo_apellido]: " + aux;
                obj.setErrorCarga(aux);
            }
            //2020-09-15 jyperez INC 300 permitir valor vacio en segundo apellido
        } else if (campos[i] != null && campos[i].trim().equals("")) {
            obj.setSegundoApellido(campos[i].trim());
        }
        i++;
        //primer_nombre
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoTexto(campos[i].trim());
            if (aux.equals("")) {
                obj.setPrimerNombre(campos[i].trim());
            } else {
                aux = obj.getErrorCarga() + "[primer_nombre]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[primer_nombre]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;
        //segundo_nombre
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoTexto(campos[i].trim());
            if (aux.equals("")) {
                obj.setSegundoNombre(campos[i].trim());
            } else {
                aux = obj.getErrorCarga() + "[segundo_nombre]: " + aux;
                obj.setErrorCarga(aux);
            }
            //2020-09-15 jyperez INC 300 permitir valor vacio en segundo apellido
        } else if (campos[i] != null && campos[i].trim().equals("")) {
            obj.setSegundoNombre(campos[i].trim());
        }
        i++;
        //fecha_nacimiento
        try {
            obj.setFechaNacimiento(sdf.parse(campos[i].trim()));
        } catch (ParseException ex) {
            obj.setFechaNacimiento(null);
            aux = obj.getErrorCarga() + "[fecha_nacimiento]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;
        //genero
        if (getGenero(campos[i].trim()) != null) {
            obj.setGenero(getGenero(campos[i].trim()));
        } else {
            aux = obj.getErrorCarga() + "[genero]: valor erróneo.";
            obj.setErrorCarga(aux);
        }
        i++;
        //estado_civil
        obj.setEstadoCivil(getEstadoCivil(campos[i].trim()));
        if (obj.getEstadoCivil().equals("")) {
            aux = obj.getErrorCarga() + "[estado_civil]: valor no válido.";
            obj.setErrorCarga(aux);
        }
        i++;
        //origen_afiliado
        obj.setMaeOrigenAfiliado(getOrigenAfiliado(campos[i].trim()));
        if (obj.getMaeOrigenAfiliado() == 0) {
            aux = obj.getErrorCarga() + "[origen_afiliado]: valor erróneo.";
            obj.setErrorCarga(aux);
        }
        i++;
        //tipo_beneficiario
        obj.setTipoBeneficiario(getTipoAfiliado(campos[i].trim()));
        if (obj.getTipoBeneficiario().equals("")) {
            aux = obj.getErrorCarga() + "[tipo_beneficiario]: valor no válido.";
            obj.setErrorCarga(aux);
        }
        i++;
        //parentesco_cotizante
        obj.setParentescoCotizante(getParentesco(campos[i].trim()));
        i++;
        //tipo_documento_cf
        obj.setMaeTipoDocumentoCf(getTipoDocumento(campos[i].trim()));
        i++;
        //numero_documento_cf
        obj.setNumeroDocumentoCf(campos[i].trim());
        i++;
        //regimen
        obj.setRegimen(getRegimen(campos[i].trim()));
        if (obj.getRegimen().equals("")) {
            aux = obj.getErrorCarga() + "[regimen]: valor no válido.";
            obj.setErrorCarga(aux);
        }
        i++;
        //fecha_movilidad
        try {
            obj.setFechaMovilidad(sdf.parse(campos[i].trim()));
        } catch (ParseException ex) {
            obj.setFechaMovilidad(null);
        }
        i++;
        //categoria_ibc
        obj.setCategoriaIbc(getCategoriaIbc(campos[i].trim()));
        i++;
        //tipo_cotizante
        //obj.setTipoCotizante(campos[i].trim());
        if (obj.getRegimen().equals(REGIMEN_CONTRIBUTIVO) && !campos[i].trim().equals("")) {
            obj.setTipoCotizante(getTipoAfiliadoCotizante(campos[i].trim()));
            if (obj.getTipoCotizante().equals("")) {
                aux = obj.getErrorCarga() + "[tipo_cotizante]: Se debe ingresar un valor válido cuando el regimen es Contributivo";
                obj.setErrorCarga(aux);
            }
        }
        // 2020-08-18 Control de Cambios Carga Masiva:
        // si el tipo_cotizante es "", debe dejarlo ingresar. Se levantan entonces las restricciones para el valor vacio
        /*else if (obj.getRegimen().equals(REGIMEN_CONTRIBUTIVO)) {
            aux = obj.getErrorCarga() + "[tipo_cotizante]: Se debe ingresar un valor cuando el regimen es Contributivo";
            obj.setErrorCarga(aux);
        } else if (!campos[i].trim().equals("")) {
            aux = obj.getErrorCarga() + "[tipo_cotizante]: No se debe ingresar un valor cuando el regimen es Subsidiado";
            obj.setErrorCarga(aux);
        }*/
        i++;
        //tipo_documento_aportante
        //VALIDAR- solo si el regimen es contributivo
        obj.setMaeTipoDocumentoAportante(getTipoDocumento(campos[i].trim()));
        i++;
        //numero_documento_aportante
        obj.setNumeroDocumentoAportante(campos[i].trim());
        i++;
        //actividad_economica
        obj.setMaeActividadEconomica(getActividadEconomica(campos[i].trim()));
        i++;
        //arl
        // VALIDAR -- se dicen enteros pero no sé que me llegará aqui
        if (!campos[i].trim().equals("")) {
            obj.setMaeArl(getArl(campos[i].trim()));
            if (obj.getMaeArl() == 0) {
                aux = obj.getErrorCarga() + "[arl]: Se debe ingresar un valor válido.";
                obj.setErrorCarga(aux);
            }
        }
        i++;
        //afp
        // VALIDAR -- se dicen enteros pero no sé que me llegará aqui
        if (!campos[i].trim().equals("")) {
            obj.setMaeAfp(getAfp(campos[i].trim()));
            if (obj.getMaeAfp() == 0) {
                aux = obj.getErrorCarga() + "[afp]: Se debe ingresar un valor válido.";
                obj.setErrorCarga(aux);
            }
        }
        i++;
        //ccf
        // VALIDAR -- se dicen enteros pero no sé que me llegará aqui
        // es caja de compensación familiar??
        if (!campos[i].trim().equals("")) {
            obj.setMaeCajaCompensacion(getCcf(campos[i].trim()));
            if (obj.getMaeCajaCompensacion() == 0) {
                aux = obj.getErrorCarga() + "[ccf]: Se debe ingresar un valor válido.";
                obj.setErrorCarga(aux);
            }
        }
        i++;
        //fecha_afiliacion_sgsss
        try {
            obj.setFechaAfiliacionSgsss(sdf.parse(campos[i].trim()));
        } catch (ParseException ex) {
            //Logger.getLogger(CargaMasivaHilo.class.getName()).log(Level.SEVERE, null, ex);
            obj.setFechaAfiliacionSgsss(null);
        }
        i++;
        //fecha_afiliacion_eps
        try {
            obj.setFechaAfiliacionEps(sdf.parse(campos[i].trim()));
        } catch (ParseException ex) {
            //Logger.getLogger(CargaMasivaHilo.class.getName()).log(Level.SEVERE, null, ex);
            obj.setFechaAfiliacionEps(null);
        }
        i++;
        //municipio_afiliacion
        obj.setAfiliacionUbicacion(obtenerUbicacion(campos[i].trim()));
        if (obj.getAfiliacionUbicacion() == null) {
            aux = obj.getErrorCarga() + "[municipio_afiliacion]: valor no válido.";
            obj.setErrorCarga(aux);
        }
        i++;
        //municipio_residencia
        // VALIDAR si este municipio debe tener cobertura o no
        obj.setResidenciaUbicacion(obtenerUbicacion(campos[i].trim()));
        if (obj.getResidenciaUbicacion() == null) {
            aux = obj.getErrorCarga() + "[municipio_residencia]: valor no válido.";
            obj.setErrorCarga(aux);
        }
        i++;
        //direccion_residencia
        if (campos[i] != null && !campos[i].trim().equals("")) {
            obj.setDireccionResidencia(campos[i].trim());
        }/* else {
            aux = obj.getErrorCarga() + "[direccion_residencia]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }*/
        i++;
        //barrio
        obj.setBarrio(campos[i].trim());
        i++;
        //zona
        obj.setZona(getZona(campos[i].trim()));
        if (obj.getZona().equals("")) {
            aux = obj.getErrorCarga() + "[zona]: valor no válido.";
            obj.setErrorCarga(aux);
        }
        i++;
        //telefono_fijo
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoNumerico(campos[i].trim(), 7);
            if (aux.equals("")) {
                obj.setTelefonoFijo(campos[i].trim());
            } else {
                aux = obj.getErrorCarga() + "[telefono_fijo]: " + aux;
                obj.setErrorCarga(aux);
            }
        }
        i++;
        //telefono_movil
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoNumerico(campos[i].trim(), 10);
            if (aux.equals("")) {
                obj.setTelefonoMovil(campos[i].trim());
            } else {
                aux = obj.getErrorCarga() + "[telefono_movil]: " + aux;
                obj.setErrorCarga(aux);
            }
        }
        i++;
        //email
        obj.setEmail(campos[i].trim());
        i++;
        //autoriza_envio_sms
        switch (campos[i].trim()) {
            case "S":
                obj.setAutorizaEnvioSms(true);
                break;
            default:
                obj.setAutorizaEnvioSms(false);
                break;
        }
        i++;
        //autoriza_envio_email
        switch (campos[i].trim()) {
            case "S":
                obj.setAutorizaEnvioEmail(true);
                break;
            default:
                obj.setAutorizaEnvioEmail(false);
                break;
        }
        i++;
        //codigo_habilitacion_ips_primaria
        obj.setPrimariaPrestadorSede(getPrestadorSede(campos[i].trim()));
        if (obj.getPrimariaPrestadorSede() == null) {
            aux = obj.getErrorCarga() + "[codigo_habilitacion_ips_primaria]: valor no válido.";
            obj.setErrorCarga(aux);
        }
        i++;
        //grupo_poblacional
        obj.setMaeGrupoPoblacional(getGrupoPoblacional(campos[i].trim()));
        if (obj.getMaeGrupoPoblacional() == 0) {
            aux = obj.getErrorCarga() + "[grupo_poblacional]: valor no válido.";
            obj.setErrorCarga(aux);
        }
        i++;
        //victima
        switch (campos[i].trim()) {
            case "S":
                obj.setVictima(true);
                break;
            case "N":
                obj.setVictima(false);
                break;
            default:
                aux = obj.getErrorCarga() + "[victima]: valor no válido.";
                obj.setErrorCarga(aux);
                break;
        }
        i++;
        //etnia
        obj.setEtnia(getEtnia(campos[i].trim()));
        if (obj.getEtnia().equals("")) {
            aux = obj.getErrorCarga() + "[etnia]: valor no válido.";
            obj.setErrorCarga(aux);
        }
        i++;
        //nivel_sisben
        obj.setNivelSisben(getNivelSisben(campos[i].trim()));
        if (obj.getNivelSisben().equals("")) {
            aux = obj.getErrorCarga() + "[nivel_sisben]: valor no válido.";
            obj.setErrorCarga(aux);
        }
        i++;
        //puntaje_sisben
        if (campos[i] != null && !campos[i].trim().equals("")) {
            try {
                obj.setPuntajeSisben(Double.parseDouble(campos[i].trim()));
            } catch (NumberFormatException e) {
                // si el valor llega malo o nulo, se ingresará el valor 0. Las validaciones de negocio se encargan de determinar 
                //la obligatoriedad del campo y si el valor es correcto para el nivel
                obj.setPuntajeSisben(new Double(0));
                //aux = obj.getErrorCarga() + "[puntaje_sisben]: valor no válido.";
                //obj.setErrorCarga(aux);
            }
        }
        i++;
        //ficha_sisben
        obj.setFichaSisben(campos[i].trim());
        i++;
        //fecha_sisben
        try {
            obj.setFechaSisben(sdf.parse(campos[i].trim()));
        } catch (ParseException ex) {
            obj.setFechaSisben(null);
        }
        i++;
        //discapacidad
        switch (campos[i].trim()) {
            case "S":
                obj.setDiscapacidad(true);
                break;
            default:
                obj.setDiscapacidad(false);
                break;
        }
        i++;
        if (obj.isDiscapacidad()) {
            //tipo_discapacidad
            obj.setTipoDiscapacidad(getTipoDiscapacidad(campos[i].trim()));
            if (obj.getTipoDiscapacidad().equals("ERROR")) {
                aux = obj.getErrorCarga() + "[tipo_discapacidad]: valor no válido.";
                obj.setErrorCarga(aux);
            }
            i++;
            //condicion_discapacidad
            obj.setCondicionDiscapacidad(getCondicionDiscapacidad(campos[i].trim()));
            if (obj.getCondicionDiscapacidad().equals("")) {
                aux = obj.getErrorCarga() + "[condicion_discapacidad]: valor no válido.";
                obj.setErrorCarga(aux);
            }
            i++;
            //fecha_Inicio_discapacidad -- ESTE VALOR PUEDE SER NULO
            try {
                if (obj.isDiscapacidad() && !campos[i].trim().equals("")) {
                    obj.setFechaIniciodiscapacidad(sdf.parse(campos[i].trim()));
                } else if (obj.isDiscapacidad()) {
                    aux = obj.getErrorCarga() + "[fecha_Inicio_discapacidad]: el valor no debe ser vacio si tiene discapacidad.";
                    obj.setErrorCarga(aux);
                } else {
                    // es vacia entonces se actualiza a vacio
                    obj.setFechaIniciodiscapacidad(null);
                }
            } catch (ParseException ex) {
                aux = obj.getErrorCarga() + "[fecha_Inicio_discapacidad]: valor no válido.";
                obj.setErrorCarga(aux);
            }
            i++;
            //fecha_fin_discapacidad -- ESTE VALOR PUEDE SER NULO
            try {
                if (obj.isDiscapacidad() && !campos[i].trim().equals("")) {
                    obj.setFechaFinDiscapacidad(sdf.parse(campos[i].trim()));
                } else if (obj.isDiscapacidad()) {
                    aux = obj.getErrorCarga() + "[fecha_fin_discapacidad]: el valor no debe ser vacio si tiene discapacidad.";
                    obj.setErrorCarga(aux);
                } else {
                    // es vacia entonces se actualiza a vacio
                    obj.setFechaFinDiscapacidad(null);
                }
            } catch (ParseException ex) {
                aux = obj.getErrorCarga() + "[fecha_fin_discapacidad]: valor no válido.";
                obj.setErrorCarga(aux);
            }
            i++;
        } else {
            // movemos la cantidad de campos que no se leeran (tipo_discapacidad,condicion_discapacidad,fecha_Inicio_discapacidad,fecha_fin_discapacidad)
            i = i + 4;
        }
        //modelo_liquidacion
        obj.setModeloLiquidacion(getModeloLiquidacion(campos[i].trim()));
        i++;
        //estado_afiliacion
        obj.setMaeEstadoAfiliacion(getEstadoAfiliacion(campos[i].trim()));
        if (obj.getMaeEstadoAfiliacion() == 0) {
            aux = obj.getErrorCarga() + "[estado_afiliacion]: valor erróneo.";
            obj.setErrorCarga(aux);
        }
        i++;
        //fecha_novedad
        try {
            obj.setFechaNovedad(sdf.parse(campos[i].trim()));
            if (obj.getFechaNovedad().compareTo(fechaActual) > 0) {
                aux = obj.getErrorCarga() + "[fecha_novedad]: el valor de fecha no puede ser mayor a la fecha de hoy: " + sdf.format(fechaActual) + ". ";
                obj.setErrorCarga(aux);
            }
        } catch (ParseException ex) {
            obj.setFechaNovedad(null);
        }
        i++;
        //causa_novedad
        obj.setMaeCausaNovedad(getCausaNovedad(campos[i].trim(), obj.getMaeEstadoAfiliacion()));
        if (obj.getMaeCausaNovedad() == 0) {
            aux = obj.getErrorCarga() + "[causa_novedad]: valor erróneo.";
            obj.setErrorCarga(aux);
        }
        i++;
        //observacion
        try {
            obj.setObservacionNovedad(campos[i].trim());
        } catch (Exception ex) {
            // se controla la excepción debido a que, cuando se da split y no se envia campo observación, el sistema
            // no genera el ultimo campo, por lo tanto causaría una excepción. No se modifica el campo
        }
        return obj;
    }

    private String getGenero(String campo) {
        String mensaje = null;
        try {
            if (campo.equals("M")) {
                mensaje = this.hashValorGenero.get(GENERO_MASCULINO).getId().toString();

            } else if (campo.equals("F")) {
                mensaje = this.hashValorGenero.get(GENERO_FEMENINO).getId().toString();
            }
        } catch (Exception e) {
            mensaje = null;
        }
        return mensaje;
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

    private int getEstadoAfiliacion(String campo) {
        int resultado = 0;
        try {
            resultado = this.hashValorEstadoAfiliacion.get(campo).getId();
        } catch (Exception e) {

        }
        return resultado;
    }

    private int getOrigenAfiliado(String campo) {
        int resultado = 0;
        Integer valor;
        try {
            valor = Integer.valueOf(campo);
            resultado = this.hashOrigenAfiliado.get(valor).getId();
        } catch (Exception e) {

        }
        return resultado;
    }

    /**
     * Funcion para obtener el registro de Ubicacion, a partir del valor de
     * divipola
     *
     * @param campo valor de la divipola
     * @return Ubicacion ingresada
     */
    private Ubicacion obtenerUbicacion(String campo) {
        Ubicacion obj;
        String prefDepartamento;
        String prefMunicipio;
        try {
            prefDepartamento = campo.substring(0, 2);
            prefMunicipio = campo.substring(2, 5);
            obj = UbicacionSingle.getInstance().consultarMunicipiPorPrefijos(prefDepartamento, prefMunicipio);
        } catch (Exception ex) {
            obj = null;
        }
        return obj;
    }

    /**
     * Función para obtener el registro de CntPrestadorSede a partir del valor
     * de codigo_habilitacion_sede
     *
     * @param campo
     * @return
     */
    private CntPrestadorSede getPrestadorSede(String campo) {
        CntPrestadorSede obj;
        try {
            obj = getPrestadoresRemoto().consultarSedePorCodigoHabilitacion(campo);
        } catch (Exception ex) {
            obj = null;
        }
        return obj;
    }

    private int getGrupoPoblacional(String campo) {
        int resultado = 0;
        try {
            resultado = this.hashValorGrupoPoblacional.get(campo).getId();
        } catch (Exception e) {

        }
        return resultado;
    }

    private String getEtnia(String campo) {
        String mensaje;
        switch (campo) {
            case ETNIA_INDIGENA:
                mensaje = campo;
                break;
            case ETNIA_ROM_GITANOS:
                mensaje = campo;
                break;
            case ETNIA_RAIZAL:
                mensaje = campo;
                break;
            case ETNIA_PALENQUERO:
                mensaje = campo;
                break;
            case ETNIA_AFROCOLOMBIANO:
                mensaje = campo;
                break;
            case ETNIA_OTRAS:
                mensaje = campo;
                break;
            default:
                mensaje = "";
                break;
        }
        return mensaje;
    }

    private String getEstadoCivil(String campo) {
        String mensaje;
        switch (campo) {
            case ESTADO_CIVIL_SOLTERO:
                mensaje = campo;
                break;
            case ESTADO_CIVIL_CASADO:
                mensaje = campo;
                break;
            case ESTADO_CIVIL_VIUDO:
                mensaje = campo;
                break;
            case ESTADO_CIVIL_DIVORCIADO:
                mensaje = campo;
                break;
            case ESTADO_CIVIL_UNION_LIBRE:
                mensaje = campo;
                break;
            case ESTADO_CIVIL_DIVORCIADO_SEPARADO:
                mensaje = campo;
                break;
            case ESTADO_CIVIL_NO_REPORTADO:
                mensaje = campo;
                break;
            case ESTADO_CIVIL_OTRO:
                mensaje = campo;
                break;
            default:
                mensaje = "";
                break;
        }
        return mensaje;
    }

    public String getTipoAfiliado(String valor) {
        String equivalente;
        switch (valor) {
            case TIPO_AFILIADO_COTIZANTE:
                equivalente = valor;
                break;
            case TIPO_AFILIADO_BENEFICIARIO:
                equivalente = valor;
                break;
            case TIPO_AFILIADO_CABEZA_DE_HOGAR:
                equivalente = valor;
                break;
            case TIPO_AFILIADO_ADICIONAL:
                equivalente = valor;
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    public String getTipoAfiliadoCotizante(String valor) {
        String equivalente;
        try {
            if( Integer.valueOf(valor)>=1 && Integer.valueOf(valor)<=63 ){
                equivalente = valor;
            }else{
                equivalente = "";
            }
        } catch (NumberFormatException nfe) {
            equivalente =  "";
        }
        return equivalente;   
        /*
        String equivalente;
        switch (valor) {
            case "1":
                equivalente = valor;
                break;
            case "2":
                equivalente = valor;
                break;
            case "3":
                equivalente = valor;
                break;
            case "4":
                equivalente = valor;
                break;
            case "5":
                equivalente = valor;
                break;
            case "6":
                equivalente = valor;
                break;
            case "7":
                equivalente = valor;
                break;
            case "8":
                equivalente = valor;
                break;
            case "9":
                equivalente = valor;
                break;
            case "10":
                equivalente = valor;
                break;
            case "11":
                equivalente = valor;
                break;
            case "12":
                equivalente = valor;
                break;
            case "13":
                equivalente = valor;
                break;
            case "14":
                equivalente = valor;
                break;
            case "15":
                equivalente = valor;
                break;
            case "16":
                equivalente = valor;
                break;
            case "17":
                equivalente = valor;
                break;
            case "18":
                equivalente = valor;
                break;
            case "19":
                equivalente = valor;
                break;
            case "20":
                equivalente = valor;
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
        */
    }

    private String getParentesco(String campo) {
        String equivalente;
        switch (campo) {
            case PARENTESCO_CONYUGE:
                equivalente = campo;
                break;
            case PARENTESCO_HIJOS_DEL_COTIZANTE:
                equivalente = campo;
                break;
            case PARENTESCO_PADRE_O_MADRE:
                equivalente = campo;
                break;
            case PARENTESCO_HIJOS_BENEFICIARIO:
                equivalente = campo;
                break;
            case PARENTESCO_TERCER_GRADO_CONSANGUINIDAD:
                equivalente = campo;
                break;
            case PARENTESCO_PADRES_DEPENDIENTES:
                equivalente = campo;
                break;
            case PARENTESCO_AFILIADO_ADICIONAL:
                equivalente = campo;
                break;
            case PARENTESCO_HIJOS_COTIZANTE_CON_DISCAPACIDAD:
                equivalente = campo;
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    private String getZona(String campo) {
        String equivalente;
        switch (campo) {
            case ZONA_RURAL:
                equivalente = campo;
                break;
            case ZONA_URBANA:
                equivalente = campo;
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    private String getRegimen(String campo) {
        String equivalente;
        switch (campo) {
            case REGIMEN_CONTRIBUTIVO:
                equivalente = campo;
                break;
            case REGIMEN_SUBSIDIADO:
                equivalente = campo;
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    private String getCategoriaIbc(String campo) {
        String equivalente;
        switch (campo) {
            case CATEGORIA_IBC_CATEGORIA_A:
                equivalente = campo;
                break;
            case CATEGORIA_IBC_CATEGORIA_B:
                equivalente = campo;
                break;
            case CATEGORIA_IBC_CATEGORIA_C:
                equivalente = campo;
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    private String getNivelSisben(String campo) {
        String equivalente;
        switch (campo) {
            case NIVEL_SISBEN_1:
                equivalente = campo;
                break;
            case NIVEL_SISBEN_2:
                equivalente = campo;
                break;
            case NIVEL_SISBEN_3:
                equivalente = campo;
                break;
            case NIVEL_SISBEN_NO_APLICA:
                equivalente = campo;
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    private int getActividadEconomica(String campo) {
        int resultado = 0;
        try {
            resultado = this.hashValorActividadEconomica.get(campo).getId();
        } catch (Exception e) {

        }
        return resultado;
    }

    private String getTipoDiscapacidad(String campo) {
        String equivalente;
        switch (campo) {
            case TIPO_DISCAPACIDAD_FISICA:
                equivalente = campo;
                break;
            case TIPO_DISCAPACIDAD_MENTAL:
                equivalente = campo;
                break;
            case TIPO_DISCAPACIDAD_NEURO_SENSORIAL:
                equivalente = campo;
                break;
            case "":
                equivalente = campo;
                break;
            default:
                equivalente = "ERROR";
                break;
        }
        return equivalente;
    }

    private String getCondicionDiscapacidad(String campo) {
        String equivalente;
        switch (campo) {
            case CONDICION_DISCAPACIDAD_PERMANENTE:
                equivalente = campo;
                break;
            case CONDICION_DISCAPACIDAD_TEMPORAL:
                equivalente = campo;
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    private String getModeloLiquidacion(String campo) {
        String equivalente;
        switch (campo) {
            case MODELO_LIQUIDACION_CAPITA:
                equivalente = campo;
                break;
            case MODELO_LIQUIDACION_EVENTO:
                equivalente = campo;
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    private int getCausaNovedad(String campo, int estado) {
        int resultado = 0;
        Integer valor;
        try {
            valor = Integer.valueOf(campo);
            switch (this.hashEstadoAfiliacion.get(estado).getValor()) {
                case ESTADO_AFILIACION_ACTIVO:
                    resultado = this.hashCausaNovedadActivacion.get(valor).getId();
                    break;
                case ESTADO_AFILIACION_FALLECIDO:
                    resultado = this.hashCausaNovedadFallecido.get(valor).getId();
                    break;
                case ESTADO_AFILIACION_RETIRADO:
                    resultado = this.hashCausaNovedadRetiro.get(valor).getId();
                    break;
                case ESTADO_AFILIACION_SUSPENDIDO:
                    resultado = this.hashCausaNovedadSuspension.get(valor).getId();
                    break;
                case ESTADO_AFILIACION_PENDIENTE_SOPORTE:
                    resultado = this.hashCausaNovedadPendienteSoporte.get(valor).getId();
                    break;
                default:
                    resultado = 0;
                    break;
            }
        } catch (NumberFormatException e) {
            return resultado;
        } catch (Exception ex) {
            return resultado;
        }
        return resultado;
    }

    private Integer getArl(String campo) {
        int resultado = 0;
        try {
            resultado = this.hashValorARL.get(campo).getId();
        } catch (Exception e) {

        }
        return resultado;
    }

    private Integer getAfp(String campo) {
        int resultado = 0;
        try {
            resultado = this.hashValorAFP.get(campo).getId();
        } catch (Exception e) {

        }
        return resultado;
    }

    private Integer getCcf(String campo) {
        int resultado = 0;
        try {
            resultado = this.hashValorCCF.get(campo).getId();
        } catch (Exception e) {

        }
        return resultado;
    }

    private String validarCampoNumerico(String numero, int tamanio) {
        String mensaje;
        //2020-09-18 jyperez INC 306 - Modificar para que el tamaño sea igual al que se está validando.
        if (numero.matches("\\d*") && numero.length() == tamanio) {
            mensaje = "";
        } else if (!numero.matches("\\d*")) {
            mensaje = "no cumple con el formato numérico.";
        } else {
            mensaje = "no cumple con el formato igual a " + tamanio + " dígitos.";
        }

        return mensaje;
    }

    private String validarCampoTexto(String texto) {
        String mensaje = "";
        Pattern patron = Pattern.compile("[0-9A-ZÑÁÀÂÄÉÈÊËÍÌÎÏÓÒÔÖÚÙÛÜ.\\s\\/\\(\\)\\-\\_\\+\\*\\\"\\'\\|\\[\\]\\{\\}\\'\\\\\\.\\´]+");
        Matcher emparejador = patron.matcher(texto);
        if (!emparejador.matches()) {
            mensaje = "no cumple con el formato Texto.";
        }
        return mensaje;
    }

}
