/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.utilidades;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoCarga;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoCargaSuceso;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoSede;
import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.maestro.MaIss2000Tarifario;
import com.saviasaludeps.savia.dominio.maestro.MaIss2001Tarifario;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaPaquete;
import com.saviasaludeps.savia.dominio.maestro.MaSoatTarifario;
import com.saviasaludeps.savia.dominio.maestro.MaSoatTarifarioValor;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologiaServicioHabilitacion;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoCargaRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoCargaSucesoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoDetalleRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoSedeRemoto;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaInsumoRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaIss2000TarifarioRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaIss2001TarifarioRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaMedicamentoRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaPaqueteRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaSoatTarifarioRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaSoatTarifarioValorRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaTecnologiaRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaTecnologiaServicioHabilitacionRemoto;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 *
 * @author Jaime Andres Olarte
 */
public class CargaMasivaContratoHilo extends Thread {

    //constantes
    public final static int TIPO_TECNOLOGIA_TECNOLOGIA = 1;
    public final static int TIPO_TECNOLOGIA_MEDICAMENTO = 2;
    public final static int TIPO_TECNOLOGIA_INSUMO = 3;
    public final static int TIPO_TECNOLOGIA_PAQUETE = 4;
    public final static int TIPO_MANUAL_TARIFARIO_PROPIA = 0;
    public final static int TIPO_MANUAL_TARIFARIO_SOAT = 1;
    public final static int TIPO_MANUAL_TARIFARIO_ISS2000 = 2;
    public final static int TIPO_MANUAL_TARIFARIO_ISS2001 = 3;
    public final static int TIPO_COMPLEJIDAD_ALTA = 1;
    public final static int TIPO_COMPLEJIDAD_MEDIANA = 2;
    public final static int TIPO_COMPLEJIDAD_BAJA = 3;
    public static final String REGIMEN_SUBSIDIADO = "1";
    public static final String MODELO_LIQUIDACION_EVENTO = "1";
    public static final String EXITOSO_STR = "Exitoso";
    public static final String FALLIDO_STR = "Fallido";
    public static final int ESTADO_CARGA_PROCESANDO = 1;
    public static final int ESTADO_CARGA_PROCESADO = 2;
    public static final int ESTADO_CARGA_ABORTADO = 3;
    public static final int ESTADO_DETALLE_CARGA_INGRESADO = 3;
    public static final int ESTADO_DETALLE_CARGA_FALLIDO = 4;

    private CntContratoCarga carga;
    private List<CntContratoDetalle> listaCarga;
    private ContratoDetalleValidacion validacion;
    //maestros
    private HashMap<String, Maestro> hashAmbito;
    private HashMap<String, Maestro> hashModalidadContrato;

    public CargaMasivaContratoHilo(CntContratoCarga carga) {
        try {
            this.carga = carga;
            validacion = new ContratoDetalleValidacion();
            //carga de maestros
            hashAmbito = getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.GN_AMBITO);
            hashModalidadContrato = getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.CNT_MODALIDAD);
        } catch (Exception ex) {
            Logger.getLogger(CargaMasivaContratoHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
        return (MaestroRemoto) object;
    }

    private CntPrestadorSedeRemoto getPrestadorSedesRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }

    private CntContratoDetalleRemoto getContratoDetalleRemoto() throws Exception {
        return (CntContratoDetalleRemoto) RemotoEJB.getEJBRemoto("CntContratoDetalleServicio", CntContratoDetalleRemoto.class.getName());
    }

    private CntContratoCargaRemoto getContratoCargaRemoto() throws Exception {
        return (CntContratoCargaRemoto) RemotoEJB.getEJBRemoto(("CntContratoCargaServicio"), CntContratoCargaRemoto.class.getName());
    }
    
    private CntContratoCargaSucesoRemoto getContratoCargaSucesoRemoto() throws Exception {
        return (CntContratoCargaSucesoRemoto) RemotoEJB.getEJBRemoto(("CntContratoCargaSucesoServicio"), CntContratoCargaSucesoRemoto.class.getName());
    }
    
    private CntContratoSedeRemoto getContratoSedeRemoto() throws Exception {
        return (CntContratoSedeRemoto) RemotoEJB.getEJBRemoto(("CntContratoSedeServicio"), CntContratoSedeRemoto.class.getName());
    }
    
    private CntContratoRemoto getContratoRemoto() throws Exception {
        return (CntContratoRemoto) RemotoEJB.getEJBRemoto(("CntContratoServicio"), CntContratoRemoto.class.getName());
    }
    
    private MaInsumoRemoto getInsumoRemoto() throws Exception {
        return (MaInsumoRemoto) RemotoEJB.getEJBRemoto(("MaInsumoServicio"), MaInsumoRemoto.class.getName());
    }
    
    private MaMedicamentoRemoto getMedicamentoRemoto() throws Exception {
        return (MaMedicamentoRemoto) RemotoEJB.getEJBRemoto(("MaMedicamentoServicio"), MaMedicamentoRemoto.class.getName());
    }
    
    private MaPaqueteRemoto getPaqueteRemoto() throws Exception {
        return (MaPaqueteRemoto) RemotoEJB.getEJBRemoto(("MaPaqueteServicio"), MaPaqueteRemoto.class.getName());
    }
    
    private MaTecnologiaRemoto getTecnologiaRemoto() throws Exception {
        return (MaTecnologiaRemoto) RemotoEJB.getEJBRemoto(("MaTecnologiaServicio"), MaTecnologiaRemoto.class.getName());
    }
    
    private MaTecnologiaServicioHabilitacionRemoto getTecnologiaServicioHabilitacionRemoto() throws Exception {
        return (MaTecnologiaServicioHabilitacionRemoto) RemotoEJB.getEJBRemoto(("MaTecnologiaServicioHabilitacionServicio"), MaTecnologiaServicioHabilitacionRemoto.class.getName());
    }
    
    private MaSoatTarifarioValorRemoto getTarifarioValorRemoto() throws Exception {
        return (MaSoatTarifarioValorRemoto) RemotoEJB.getEJBRemoto(("MaSoatTarifarioValorServicio"), MaSoatTarifarioValorRemoto.class.getName());
    }
    
    private MaSoatTarifarioRemoto getSoatTarifarioRemoto() throws Exception {
        return (MaSoatTarifarioRemoto) RemotoEJB.getEJBRemoto(("MaSoatTarifarioServicio"), MaSoatTarifarioRemoto.class.getName());
    }
    
    private MaIss2000TarifarioRemoto getIss2000TarifarioRemoto() throws Exception {
        return (MaIss2000TarifarioRemoto) RemotoEJB.getEJBRemoto(("MaIss2000TarifarioServicio"), MaIss2000TarifarioRemoto.class.getName());
    }
    
    private MaIss2001TarifarioRemoto getIss2001TarifarioRemoto() throws Exception {
        return (MaIss2001TarifarioRemoto) RemotoEJB.getEJBRemoto(("MaIss2001TarifarioServicio"), MaIss2001TarifarioRemoto.class.getName());
    }

    @Override
    public void run() {
        cargaDetallesContratos();
    }

    private void cargaDetallesContratos() {
        int pos = 0;
        int exitosos = 0;
        int fallidos = 0;
        boolean crear = true;
        CntContratoCargaSuceso detalleCarga= null;
        CntContrato contrato = null;
        CntContratoSede contratoSede = null;
        MaInsumo maInsumo = null;
        MaMedicamento maMedicamento = null;
        MaTecnologia matecnologia = null;
        MaPaquete maPaquete = null;
        // Obtener registros de afiliado del archivo de AsegCarga
        obtenerListaCargaDetalles();

        for (CntContratoDetalle objeto : listaCarga) {
                maPaquete = null;
            try {
                detalleCarga = new CntContratoCargaSuceso();
                // validamos si el registro en la lista, no contiene errores
                if (objeto.getErrorCarga().equals("")) {
                    //cargamos información de los maestros y objetos necesarios
                    // numero_contrato
                    try {
                        contrato = getContratoRemoto().consultarPorContrato(objeto.getCntContrato().getContrato());
                        if (contrato != null) {
                            objeto.setCntContrato(contrato);
                        }else {
                            throw new Exception("El contrato ingresado no existe.");
                        }
                    } catch (Exception ex) {
                        throw new Exception("[numero_contrato] " + ex.getMessage());
                    }
                    //codigo_habilitacion_sede
                    try {
                        //2021-04-14 jyperez Se adiciona a la validación, el campo modalidad Contrato
                        contratoSede = getContratoSedeRemoto().consultarPorContratoCodigoHabilitacionPrestadorYModalidad(objeto.getCntContrato().getContrato(), objeto.getCntContratoSede().getCntPrestadorSede().getCodigoHabilitacionSede(), objeto.getMaeModalidadContratoCodigo());
                        if (contratoSede != null) {
                            objeto.setCntContratoSede(contratoSede);
                        }else {
                            throw new Exception("El Código Habilitación de la sede y la modalidad de contrato ingresados no se encuentran asociados al contrato.");
                        }
                    } catch (Exception ex) {
                        throw new Exception("[codigo_habilitacion_sede] " + ex.getMessage());
                    }
                    //codigo_tecnologia
                    try {
                        if (objeto.getTipoTecnologia() == TIPO_TECNOLOGIA_INSUMO) {
                            maInsumo = getInsumoRemoto().consultarPorCodigo(objeto.getMaTecnologiaCodigo());
                            if (maInsumo != null) {
                                if (!maInsumo.isActivo()) {
                                     throw new Exception("El código ingresado no se encuentra activo.");
                                }
                                objeto.setMaTecnologiaId(maInsumo.getId());
                                objeto.setMaTecnologiaValor(maInsumo.getDescripcion());
                            }else {
                                throw new Exception("El código ingresado no se encuentra asociado a una tecnología de Insumo.");
                            }
                        }else if (objeto.getTipoTecnologia() == TIPO_TECNOLOGIA_MEDICAMENTO) {
                            maMedicamento = getMedicamentoRemoto().consultarPorCodigoCum(objeto.getMaTecnologiaCodigo());
                            if (maMedicamento != null) {
                                if (!maMedicamento.isActivo()) {
                                     throw new Exception("El código ingresado no se encuentra activo.");
                                }
                                objeto.setMaTecnologiaId(maMedicamento.getId());
                                objeto.setMaTecnologiaValor(maMedicamento.getDescripcionInvima());
                            }else {
                                throw new Exception("El código ingresado no se encuentra asociado a una tecnología de Medicamento.");
                            }
                        }else if (objeto.getTipoTecnologia() == TIPO_TECNOLOGIA_PAQUETE) {
                            maPaquete = getPaqueteRemoto().consultarPorCodigo(objeto.getMaTecnologiaCodigo());
                            if (maPaquete != null) {
                                if (!maPaquete.isActivo()) {
                                     throw new Exception("El código ingresado no se encuentra activo.");
                                }
                                objeto.setMaTecnologiaId(maPaquete.getId());
                                objeto.setMaTecnologiaValor(maPaquete.getNombre());
                            }else {
                                throw new Exception("El código ingresado no se encuentra asociado a una tecnología de Paquete.");
                            }
                        }else if (objeto.getTipoTecnologia() == TIPO_TECNOLOGIA_TECNOLOGIA) {
                            matecnologia = getTecnologiaRemoto().consultarPorCodigo(objeto.getMaTecnologiaCodigo());
                            if (matecnologia != null) {
                                if (!matecnologia.isActivo()) {
                                     throw new Exception("El código ingresado no se encuentra activo.");
                                }
                                objeto.setMaTecnologiaId(matecnologia.getId());
                                objeto.setMaTecnologiaValor(matecnologia.getCupsDescipcion());
                            }else {
                                throw new Exception("El código ingresado no se encuentra asociado a una tecnología de Tecnologia.");
                            }
                        }
                    } catch (Exception ex) {
                        throw new Exception("[codigo_tecnologia] " + ex.getMessage());
                    }
                    //codigo_servicio_habilitacion
                    if (objeto.getTipoTecnologia() == TIPO_TECNOLOGIA_TECNOLOGIA) {
                        try{
                            MaTecnologiaServicioHabilitacion obj = getTecnologiaServicioHabilitacionRemoto().consultarPorTecnologiaYCodigoServicio(objeto.getMaTecnologiaId(), objeto.getMaServicioHabilitacionCodigo());
                            if(obj != null) {
                                objeto.setMaServicioHabilitacionId(obj.getMaServicioHabilitacion().getId());
                                objeto.setMaServicioHabilitacionValor(obj.getMaServicioHabilitacion().getNombre());
                            }else {
                                throw new Exception("El código ingresado no se encuentra asociado a la tecnologia ingresada.");
                            }
                        }catch (Exception ex) {
                            throw new Exception("[codigo_servicio_habilitacion] " + ex.getMessage());
                        }
                    } else if (objeto.getTipoTecnologia() == TIPO_TECNOLOGIA_PAQUETE) {
                        try{
                            // el objeto maPaquete estará lleno con el objeto consulta.
                            if (maPaquete.getTipoTecnologia() == TIPO_TECNOLOGIA_TECNOLOGIA) {
                                MaTecnologiaServicioHabilitacion obj = getTecnologiaServicioHabilitacionRemoto().consultarPorTecnologiaYCodigoServicio(maPaquete.getMaTecnologia().getId(), objeto.getMaServicioHabilitacionCodigo());
                                if(obj != null) {
                                    objeto.setMaServicioHabilitacionId(obj.getMaServicioHabilitacion().getId());
                                    objeto.setMaServicioHabilitacionValor(obj.getMaServicioHabilitacion().getNombre());
                                }else {
                                    throw new Exception("El código ingresado no se encuentra asociado a la tecnologia ingresada.");
                                }
                            }
                        }catch (Exception ex) {
                            throw new Exception("[codigo_servicio_habilitacion] " + ex.getMessage());
                        }
                    }
                    
                    //manual_tarifario_codigo
                    if (objeto.getTipoManualTarifario() != TIPO_MANUAL_TARIFARIO_PROPIA) {
                        try{
                           if(objeto.getTipoManualTarifario() == TIPO_MANUAL_TARIFARIO_SOAT) {
                               MaSoatTarifario soat = getSoatTarifarioRemoto().consultarPorCodigo(objeto.getMaManualTarifarioCodigo());
                               if (soat != null) {
                                   objeto.setMaManualTarifarioId(soat.getId());
                                   objeto.setMaManualTarifarioValor(soat.getDescripcion());
                               }else {
                                   throw new Exception("El código ingresado no se encuentra asociado a la tarifa Soat.");
                               }
                           }else if (objeto.getTipoManualTarifario() == TIPO_MANUAL_TARIFARIO_ISS2000) {
                               MaIss2000Tarifario iss2000 = getIss2000TarifarioRemoto().consultarPorCodigo(objeto.getMaManualTarifarioCodigo());
                               if (iss2000 != null) {
                                   objeto.setMaManualTarifarioId(iss2000.getId());
                                   objeto.setMaManualTarifarioValor(iss2000.getDescripcion());
                               }else {
                                   throw new Exception("El código ingresado no se encuentra asociado a la tarifa Iss2000.");
                               }
                           }else if (objeto.getTipoManualTarifario() == TIPO_MANUAL_TARIFARIO_ISS2001) {
                               MaIss2001Tarifario iss2001 = getIss2001TarifarioRemoto().consultarPorCodigo(objeto.getMaManualTarifarioCodigo());
                               if (iss2001 != null) {
                                   objeto.setMaManualTarifarioId(iss2001.getId());
                                   objeto.setMaManualTarifarioValor(iss2001.getDescripcion());
                               }else {
                                   throw new Exception("El código ingresado no se encuentra asociado a la tarifa Iss2001.");
                               }
                           }
                        }catch (Exception ex) {
                            throw new Exception("[manual_tarifario_codigo] " + ex.getMessage());
                        }
                    }
                    //ma_manual_tarifario_agno
                    if (objeto.getTipoManualTarifario() == TIPO_MANUAL_TARIFARIO_SOAT) {
                        try{
                            MaSoatTarifarioValor soatValor = getTarifarioValorRemoto().consultarPorSoatTarifarioYAgno(objeto.getMaManualTarifarioId(),objeto.getMaManualTarifarioAgno().toString());
                            if (soatValor == null) {
                                throw new Exception("El año ingresado no se encuentra asociado a la tarifa Soat.");
                            }
                        }catch (Exception ex) {
                            throw new Exception("[ma_manual_tarifario_agno] " + ex.getMessage());
                        }
                    }
                    //codigo_habilitacion_sedes
                    if (objeto.isInterdependencia()) {
                    try{
                            CntPrestadorSede sedeInter = getPrestadorSedesRemoto().consultarSedePorCodigoHabilitacionYEstado(objeto.getCntPrestadorSedesInterdependencia().getCodigoHabilitacionSede());
                            if (sedeInter != null) {
                                objeto.setCntPrestadorSedesInterdependencia(sedeInter);
                            }else {
                                throw new Exception("El código ingresado no se encuentra asociado a una Sede.");
                            }
                        }catch (Exception ex) {
                            throw new Exception("[codigo_habilitacion_sedes] " + ex.getMessage());
                        }
                    }
                    //codigo_ambito
                    try {
                        Maestro ambito = hashAmbito.get(objeto.getMaeAmbitoCodigo());
                        if (ambito != null) {
                            objeto.setMaeAmbitoId(ambito.getId());
                            objeto.setMaeAmbitoValor(ambito.getNombre());
                        }else {
                            throw new Exception("El código ingresado no se encuentra asociado al maestro de Ambito.");
                        }
                    }catch (Exception ex) {
                        throw new Exception("[codigo_ambito] " + ex.getMessage());
                    }
                    
                    //calcular valor contratado
                    objeto.setValorContratado(((objeto.getValorManual().multiply(objeto.getPorcentajeVariacion())).divide(new BigDecimal(100))).add(objeto.getValorManual()));
                    
                    // una vez cargada la información, procedemos a validar si vamos a actualizar el registro
                    if (objeto.isActualizar()) {
                        try {
                        //procedemos a obtener el registro de contrato detalle con los valores entregados
                        // contrato, sede, tecnologia (valores que se tomaron como únicos desde la BD)
                        //tener en cuenta que cuando es tecnología, debe incluirse el servicio de habilitacion (esto debido a que de esta forma se validan estos registros.
                        CntContratoDetalle det = null;
                        if (objeto.getTipoTecnologia() == TIPO_TECNOLOGIA_TECNOLOGIA) {
                            det = getContratoDetalleRemoto().consultarPorContratoSedeTecnologiaTipoYServicioHabilitacion(objeto.getCntContrato().getId(),objeto.getCntContratoSede().getId(),objeto.getMaTecnologiaId(),objeto.getMaServicioHabilitacionId(),objeto.getTipoTecnologia());
                        } else {
                            det = getContratoDetalleRemoto().consultarPorContratoSedeTecnologiaYTipoTecnologia(objeto.getCntContrato().getId(),objeto.getCntContratoSede().getId(),objeto.getMaTecnologiaId(),objeto.getTipoTecnologia());
                        }
                        if (det != null) {
                                //copiamos el id al objeto a actualizar
                                objeto.setId(det.getId());
                                objeto.setFechaHoraCrea(det.getFechaHoraCrea());
                                objeto.setTerminalCrea(det.getTerminalCrea());
                                objeto.setUsuarioCrea(det.getUsuarioCrea());
                                crear = false;
                            }else {
                                throw new Exception("No se encontró un registro con el contrato, la sede y la tecnologia ingresadas.");
                            }
                        } catch (Exception ex) {
                            throw new Exception("[actualizar] " + ex.getMessage());
                        }
                    }
                    
                    //ejecutamos validaciones de negocio
                    getValidacion().validar(objeto, ContratoDetalleValidacion.TIPO_VALIDACION_CARGA_MASIVA,crear);
                    if (getValidacion().isError()) {
                        throw new Exception(getValidacion().getErroresStr());
                    }
                    // se almacena el registro de Contrato detalle. Se evalua si se crea o se actualiza
                    if (objeto.isActualizar()) {
                        objeto.setFechaHoraModifica(new Date());
                        objeto.setTerminalModifica(carga.getTerminalCrea());
                        objeto.setUsuarioModifica(carga.getUsuarioCrea());
                        getContratoDetalleRemoto().actualizar(objeto);
                    } else {
                        objeto.setFechaHoraCrea(new Date());
                        objeto.setTerminalCrea(carga.getTerminalCrea());
                        objeto.setUsuarioCrea(carga.getUsuarioCrea());
                        objeto.setId(getContratoDetalleRemoto().insertar(objeto));
                    }
                    //Aqui se controla el exitoso
                    // se guarda el registro de asegDetalleCarga
                    detalleCarga.setCntContratoCarga(carga);
                    detalleCarga.setEstado(ESTADO_DETALLE_CARGA_INGRESADO);// Ingresado
                    detalleCarga.setDetalleFallo("");
                    detalleCarga.setData(objeto.getRegistroArchivo().getBytes());// validar que si se guarde como es
                    detalleCarga.setFechaHoraProceso(new Date());
                    getContratoCargaSucesoRemoto().insertar(detalleCarga);
                    exitosos++;
                } else {
                    // el registro contiene errores de transformación de datos, guardamos el detalle carga como error
                    detalleCarga.setCntContratoCarga(carga);
                    detalleCarga.setEstado(ESTADO_DETALLE_CARGA_FALLIDO);// Fallido
                    detalleCarga.setDetalleFallo("Error: " + objeto.getErrorCarga());
                    detalleCarga.setData(objeto.getRegistroArchivo().getBytes());// validar que si se guarde como es
                    detalleCarga.setFechaHoraProceso(new Date());
                    getContratoCargaSucesoRemoto().insertar(detalleCarga);
                    fallidos++;
                }
            } catch (Exception ex) {
                //Aqui se controla el error
                if (detalleCarga == null) {
                    detalleCarga = new CntContratoCargaSuceso();
                }
                detalleCarga.setId(null);
                detalleCarga.setCntContratoCarga(carga);
                detalleCarga.setEstado(ESTADO_DETALLE_CARGA_FALLIDO);// Fallido
                detalleCarga.setDetalleFallo("Error: " + ex.getMessage());
                detalleCarga.setData(objeto.getRegistroArchivo().getBytes());// validar que si se guarde como es
                detalleCarga.setFechaHoraProceso(new Date());
                try {
                    getContratoCargaSucesoRemoto().insertar(detalleCarga);
                    fallidos++;
                } catch (Exception e) {
                    // no deberia fallar al insertar el detalleCarga
                    Logger.getLogger(CargaMasivaContratoHilo.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (pos++ == 50) {
                try {
                    //2020-07-17 jyperez Se valida el estado de carga. Si es Abortado, entonces se procede a finalizar la carga
                    CntContratoCarga cargaAux = getContratoCargaRemoto().consultar(carga.getId());
                    if (cargaAux.getEstado() == ESTADO_CARGA_ABORTADO) {
                        carga.setEstado(ESTADO_CARGA_ABORTADO);
                        carga.setUsuarioModifica(cargaAux.getUsuarioModifica());
                        carga.setRegistrosExitosos(exitosos);
                        carga.setRegistrosRechazados(fallidos);
                        carga.setFechaHoraFin(new Date());
                        getContratoCargaRemoto().actualizar(carga);
                        return;
                    }
                } catch (Exception ex) {
                    Logger.getLogger(CargaMasivaContratoHilo.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Actualizar estado de carga
                carga.setRegistrosExitosos(exitosos);
                carga.setRegistrosRechazados(fallidos);
                carga.setEstado(ESTADO_CARGA_PROCESANDO);
                try {
                    getContratoCargaRemoto().actualizar(carga);
                } catch (Exception ex) {
                    Logger.getLogger(CargaMasivaContratoHilo.class.getName()).log(Level.SEVERE, null, ex);
                }
                pos = 1;
            }
        }

        //generarArchivo(contenidoArchivo);
        if (pos > 0) {
            //Actualizar estado de carga
            carga.setRegistrosExitosos(exitosos);
            carga.setRegistrosRechazados(fallidos);
            carga.setFechaHoraFin(new Date());
            carga.setEstado(ESTADO_CARGA_PROCESADO);
            try {
                getContratoCargaRemoto().actualizar(carga);
            } catch (Exception ex) {
                Logger.getLogger(CargaMasivaContratoHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void obtenerListaCargaDetalles() {
        try {
            listaCarga = new ArrayList<>();
            FileReader archivo;
            BufferedReader buffer;
            CntContratoDetalle objeto;
            // lectura del archivo en ruta
            String texto;
            archivo = new FileReader(this.carga.getRuta() + this.carga.getArchivo());
            buffer = new BufferedReader(new InputStreamReader(new FileInputStream(this.carga.getRuta() + this.carga.getArchivo()), "utf-8"));
            // leemos la primera linea pero no se usará. esto debido a que es el encabezado.
            texto = buffer.readLine();
            while ((texto = buffer.readLine()) != null) {
                // obtención de campo a campo del valor y transformación
                //objeto = new CntContratoDetalle();
                objeto = obtenerContratoDetalle(texto);
                //objeto.setSincronizado(0);
                objeto.setUsuarioCrea(this.carga.getUsuarioCrea());
                objeto.setTerminalCrea(this.carga.getTerminalCrea());
                objeto.setFechaHoraCrea(this.carga.getFechaHoraCrea());
                // se guarda el objeto afiliado creado a partir de la lista en listaCarga.
                listaCarga.add(objeto);
            }
            // termina el proceso.
            buffer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CargaMasivaContratoHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargaMasivaContratoHilo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

    private CntContratoDetalle obtenerContratoDetalle(String texto) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        CntContratoDetalle obj = new CntContratoDetalle();
        int i = 0;
        String[] campos;
        String aux;
        obj.setErrorCarga("");
        
        // valores por defecto de los campos obligatorios

        //guardamos el registro completo en el objeto
        obj.setRegistroArchivo(texto);
        campos = texto.split(",", 23);
        //consecutivo
        i++;
        //actualizar
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoBoolean(campos[i].trim());
            if (aux.equals("")) {
                obj.setActualizar(!campos[i].trim().equals("0"));
            } else {
                aux = obj.getErrorCarga() + "[actualizar]:  el valor ingresado no corresponde a 1: Actualizar Registro, o 0 Crear Registro.";
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[actualizar]: " + "valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //numero_contrato
        if (campos[i] != null && !campos[i].trim().equals("")) {
            obj.setCntContrato(new CntContrato());
            obj.getCntContrato().setContrato(campos[i]);
        } else {
            aux = obj.getErrorCarga() + "[numero_contrato]: " + "valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //codigo_habilitacion_sede 
        if (campos[i] != null && !campos[i].trim().equals("")) {
            obj.setCntContratoSede(new CntContratoSede());
            obj.getCntContratoSede().setCntPrestadorSede(new CntPrestadorSede());
            obj.getCntContratoSede().getCntPrestadorSede().setCodigoHabilitacionSede(campos[i]);
        } else {
            aux = obj.getErrorCarga() + "[codigo_habilitacion_sede ]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;
        //modalidad_contrato
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarModalidadContrato(campos[i]);
            if (aux.equals("")) {
                obj.setMaeModalidadContratoCodigo(campos[i]);
            }else {
                aux = obj.getErrorCarga() + "[modalidad_contrato ]: " + aux;
                obj.setErrorCarga(aux);
            }
            
        } else {
            aux = obj.getErrorCarga() + "[modalidad_contrato ]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;
        //fecha_hora_inicio
        if (campos[i] != null && !campos[i].trim().equals("")) {
            try {
                aux = validarFormatoFecha(campos[i]);
                if (aux.equals("")) {
                    obj.setFechaHoraInicio(sdf.parse(campos[i].trim()));
                } else {
                    obj.setFechaHoraInicio(null);
                    aux = obj.getErrorCarga() + "[fecha_hora_inicio]: el formato de la fecha es inválido.";
                obj.setErrorCarga(aux);
                }
            } catch (ParseException ex) {
                obj.setFechaHoraInicio(null);
                aux = obj.getErrorCarga() + "[fecha_hora_inicio]: el formato de la fecha es inválido.";
                obj.setErrorCarga(aux);
            }
        }else {
            obj.setFechaHoraInicio(null);
            aux = obj.getErrorCarga() + "[fecha_hora_inicio]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //fecha_hora_fin
        if (campos[i] != null && !campos[i].trim().equals("")) {
            try {
                aux = validarFormatoFecha(campos[i]);
                if (aux.equals("")) {
                    obj.setFechaHoraFin(sdf.parse(campos[i].trim()));
                } else {
                    obj.setFechaHoraFin(null);
                    aux = obj.getErrorCarga() + "[fecha_hora_fin]: el formato de la fecha es inválido.";
                    obj.setErrorCarga(aux);
                }
            } catch (ParseException ex) {
                obj.setFechaHoraFin(null);
                aux = obj.getErrorCarga() + "[fecha_hora_fin]: el formato de la fecha es inválido.";
                obj.setErrorCarga(aux);
            }
        } else {
            obj.setFechaHoraFin(null);
            aux = obj.getErrorCarga() + "[fecha_hora_fin]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //automatico
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoBoolean(campos[i].trim());
            if (aux.equals("")) {
                obj.setAutomaticoManual(!campos[i].trim().equals("0"));
            } else {
                aux = obj.getErrorCarga() + "[automatico]: el valor ingresado no corresponde a 1: Automática, o 0 No automático.";
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[automatico]: " + "valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //activo
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoBoolean(campos[i].trim());
            if (aux.equals("")) {
                obj.setActivo(!campos[i].trim().equals("0"));
            } else {
                aux = obj.getErrorCarga() + "[activo]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[activo]: " + "valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //tipo_tecnologia
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoNumerico(campos[i].trim(), 1);
            if (aux.equals("")) {
                if (esTipoTecnologiaValida(Integer.valueOf(campos[i]))) {
                    obj.setTipoTecnologia(Integer.valueOf(campos[i]));
                }else {
                    aux = obj.getErrorCarga() + "[tipo_tecnologia]: valor no válido.";
                    obj.setErrorCarga(aux);
                }
            } else {
                aux = obj.getErrorCarga() + "[tipo_tecnologia]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[tipo_tecnologia]: " + "valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //codigo_tecnologia
        if (campos[i] != null && !campos[i].trim().equals("")) {
            obj.setMaTecnologiaCodigo(campos[i]);
        } else {
            aux = obj.getErrorCarga() + "[codigo_tecnologia]: " + "valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //codigo_servicio_habilitacion
        if (campos[i] != null && !campos[i].trim().equals("")) {
                obj.setMaServicioHabilitacionCodigo(campos[i]);
        } else if (obj.getTipoTecnologia() == TIPO_TECNOLOGIA_TECNOLOGIA) {
                aux = obj.getErrorCarga() + "[codigo_servicio_habilitacion]: este valor es obligatorio para tipo_tecnologia igual a Tecnologia.";
                obj.setErrorCarga(aux);
        }
        i++;

        //tipo_manual_tarifario
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoNumerico(campos[i].trim(), 1);
            if (aux.equals("")) {
                if (esTipoManualTarifarioValido(Integer.valueOf(campos[i]))) {
                    obj.setTipoManualTarifario(Integer.valueOf(campos[i]));
                }else {
                    aux = obj.getErrorCarga() + "[tipo_manual_tarifario]: valor no válido.";
                    obj.setErrorCarga(aux);
                }
            } else {
                aux = obj.getErrorCarga() + "[tipo_manual_tarifario]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[tipo_manual_tarifario]: " + "valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;
        
        //manual_tarifario_codigo
        if (campos[i] != null && !campos[i].trim().equals("")) {
                obj.setMaManualTarifarioCodigo(campos[i]);
        } else {
            if (obj.getTipoManualTarifario() != null & obj.getTipoManualTarifario() != TIPO_MANUAL_TARIFARIO_PROPIA) {
                aux = obj.getErrorCarga() + "[manual_tarifario_codigo]: " + "este valor es obligatorio si el tipo de manual tarifario no es Propia.";
                obj.setErrorCarga(aux);
            }
        }
        i++;

        //ma_manual_tarifario_agno 
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoNumerico(campos[i],4);
            if (aux.equals("")) {
                //recordar que para nosotros el código es el año tambien
                obj.setMaManualTarifarioAgno(Integer.valueOf(campos[i]));
            } else {
                aux = obj.getErrorCarga() + "[ma_manual_tarifario_agno]: " + aux;
                obj.setErrorCarga(aux);
            }
        }else if (obj.getTipoManualTarifario() != null & obj.getTipoManualTarifario() == TIPO_MANUAL_TARIFARIO_SOAT) {
                aux = obj.getErrorCarga() + "[ma_manual_tarifario_agno]: este valor es obligatorio si el tipo de manual tarifario es SOAT.";
                obj.setErrorCarga(aux);
        }
        i++;

        //valor_manual
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoBigDecimal(campos[i].trim(),"16","2");
            if (aux.equals("")) {
                obj.setValorManual(new BigDecimal(campos[i]));
            } else {
                aux = obj.getErrorCarga() + "[valor_manual]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[valor_manual]: " + "valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;
        
        //porcentaje_variacion 
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoBigDecimal(campos[i].trim(),"3","2");
            if (aux.equals("")) {
                obj.setPorcentajeVariacion(new BigDecimal(campos[i]));
            } else {
                aux = obj.getErrorCarga() + "[porcentaje_variacion]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[porcentaje_variacion]: " + "valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //complejidad
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoNumerico(campos[i].trim(), 1);
            if (aux.equals("")) {
                if (esValorComplejidadValido(Integer.valueOf(campos[i]))) {
                    obj.setComplejidad(Integer.valueOf(campos[i]));
                }else {
                    aux = obj.getErrorCarga() + "[complejidad]: valor no válido.";
                    obj.setErrorCarga(aux);
                }
            } else {
                aux = obj.getErrorCarga() + "[complejidad]: " + aux;
                obj.setErrorCarga(aux);
            }
        }
        i++;

        //observacion_incluye
        if (campos[i] != null && !campos[i].trim().equals("")) {
            obj.setObservacionIncluye(campos[i].trim());
        }
        i++;

        //observacion_excluye
        if (campos[i] != null && !campos[i].trim().equals("")) {
            obj.setObservacionExcluye(campos[i].trim());
        }
        i++;

        //interdependencia
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoBoolean(campos[i].trim());
            if (aux.equals("")) {
                obj.setInterdependencia(!campos[i].trim().equals("0"));
            } else {
                aux = obj.getErrorCarga() + "[interdependencia]: " + aux;
                obj.setErrorCarga(aux);
            }
        }
        i++;

        //codigo_habilitacion_sedes
        if (campos[i] != null && !campos[i].trim().equals("")) {
            obj.setCntPrestadorSedesInterdependencia(new CntPrestadorSede());
            obj.getCntPrestadorSedesInterdependencia().setCodigoHabilitacionSede(campos[i]);
        } else if( obj.isInterdependencia()) {
            aux = obj.getErrorCarga() + "[codigo_habilitacion_sedes]: el valor es obligatorio si tiene interdependencia.";
            obj.setErrorCarga(aux);
        }
        i++;

        //codigo_ambito
        if (campos[i] != null && !campos[i].trim().equals("")) {
            obj.setMaeAmbitoCodigo(campos[i].trim());
        } else {
            aux = obj.getErrorCarga() + "[codigo_ambito]: " + "valor obligatorio.";
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

    private String validarFormatoFecha(String numero) {
        String mensaje;
        if (numero.matches("[1234567890]{4}\\-[01][\\d]\\-[0123][\\d]")) {
            mensaje = "";
        } else {
            mensaje = "el formato de Fecha es inválido.";
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

    private boolean esTipoTecnologiaValida(Integer valor) {
        boolean result = false;
        switch(valor) {
            case TIPO_TECNOLOGIA_INSUMO:
            case TIPO_TECNOLOGIA_MEDICAMENTO:
            case TIPO_TECNOLOGIA_PAQUETE:
            case TIPO_TECNOLOGIA_TECNOLOGIA:
                result = true;
                break;
        }
        return result;
    }

    private boolean esTipoManualTarifarioValido(Integer valor) {
        boolean result = false;
        switch(valor) {
            case TIPO_MANUAL_TARIFARIO_PROPIA:
            case TIPO_MANUAL_TARIFARIO_ISS2000:
            case TIPO_MANUAL_TARIFARIO_ISS2001:
            case TIPO_MANUAL_TARIFARIO_SOAT:
                result = true;
                break;
        }
        return result;
    }

    private String validarCampoBigDecimal(String valor,String cantEntero, String cantDecimal) {
        //recordar valor decimal separado por .
        String mensaje;
        String regex = "";
        regex = "-?[0-9]{1,"+ cantEntero +"}\\.?[0-9]{0,"+ cantDecimal+"}";
        if (valor.matches(regex)) {
            mensaje = "";
        } else {
            mensaje = "no cumple con el formato númerico.";
        }
        return mensaje;
    }

    private boolean esValorComplejidadValido(Integer valor) {
        boolean result = false;
        switch(valor) {
            case TIPO_COMPLEJIDAD_ALTA:
            case TIPO_COMPLEJIDAD_MEDIANA:
            case TIPO_COMPLEJIDAD_BAJA:
                result = true;
                break;
        }
        return result;
    }

    /**
     * @return the validacion
     */
    public ContratoDetalleValidacion getValidacion() {
        return validacion;
    }

    /**
     * @param validacion the validacion to set
     */
    public void setValidacion(ContratoDetalleValidacion validacion) {
        this.validacion = validacion;
    }

    /**
     * @return the hashAmbito
     */
    public HashMap<String, Maestro> getHashAmbito() {
        return hashAmbito;
    }

    /**
     * @param hashAmbito the hashAmbito to set
     */
    public void setHashAmbito(HashMap<String, Maestro> hashAmbito) {
        this.hashAmbito = hashAmbito;
    }

    private String validarModalidadContrato(String campo) {
        String mensaje = "";
        Maestro maestro;
        try{
          maestro = hashModalidadContrato.get(campo);
          if (maestro != null) {
              mensaje = "";
          } else {
              mensaje = "el valor no se encuentra configurado en el Maestro de Modalidad Contrato.";
          }
        }catch (Exception ex) {
          mensaje = "el valor no se encuentra configurado en el Maestro de Modalidad Contrato.";  
        }
        return mensaje;
    }
}
