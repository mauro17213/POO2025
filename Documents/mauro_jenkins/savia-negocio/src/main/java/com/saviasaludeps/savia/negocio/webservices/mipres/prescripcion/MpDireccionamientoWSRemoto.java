package com.saviasaludeps.savia.negocio.webservices.mipres.prescripcion;

import com.saviasaludeps.savia.dominio.mipres.MpDescripcionEntregaCoigo;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamiento;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamientoEntregado;
import com.saviasaludeps.savia.dominio.mipres.MpEntregaFactura;
import com.saviasaludeps.savia.dominio.mipres.MpEntregaSuministro;
import com.saviasaludeps.savia.dominio.mipres.MpNoDireccionado;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MpDireccionamientoWSRemoto {

    int insertarMpDireccionamiento(MpDireccionamiento direccionamiento) throws Exception;

    int insertarSuministroEntrega(MpEntregaSuministro direccionamiento) throws Exception;

    int mergeEntregaFactura(MpEntregaFactura facturacion) throws Exception;

    int insertarMpNoDireccionamiento(MpNoDireccionado noDireccionamiento) throws Exception;

    Map<String, MpDireccionamiento> consultarListaMpDireccionamientos(
            List<String> listaPrescripciones) throws java.lang.Exception;

    Map<String, MpDireccionamiento> consultarListaMpDireccionamientosTransaccion(
            List<String> listaTransaccion) throws java.lang.Exception;

    int insertarMpDireccionamientoEntregado(
            MpDireccionamientoEntregado entrega) throws Exception;

    int insertarMpEntregaFacturada(
            MpEntregaFactura factura) throws Exception;

    Map<String, MpDireccionamientoEntregado> consultarListaMpDireccionamientoEntregado(
            List<Integer> listaTransacciones) throws java.lang.Exception;

    List<MpDireccionamientoEntregado> consultarListaMpDireccionamientoEntregadoList(Integer transaccion) throws java.lang.Exception;

    public Map<String, MpDireccionamientoEntregado> consultarListaMpDireccionamientoEntregadoLista(
            String pres, String tipo, String consecutivo, String entrega) throws java.lang.Exception;

    List<MpDireccionamientoEntregado> consultarListaMpEntregaSuministroLista(
            String pres, String tipo, String consecutivo, String entrega);

    List<MpEntregaFactura> consultarListaMpEntegaFactura(
            String transaccion) throws java.lang.Exception;

    List<MpEntregaFactura> consultarListaMpEntegaFacturaAnulada(
            String transaccion) throws java.lang.Exception;

    Map<String, MpDireccionamientoEntregado> consultarListaMpDireccionamientoEntregadoXFecha(
            Date fecha) throws java.lang.Exception;

    List<MpDireccionamientoEntregado> consultarSuministrosAEnviar(String regimen) throws java.lang.Exception;

    List<MpEntregaSuministro> consultarSuministrosAEnviar(Integer estado, String regimen) throws java.lang.Exception;

    List<MpEntregaSuministro> consultarSuministrosAAnular(Integer estado, String regimen) throws java.lang.Exception;

    void actualizarMpDireccionamientoEntregado(
            MpDireccionamientoEntregado entrega) throws Exception;

    void actualizarMpDireccionamientoEntregadoFacturacion(
            MpDireccionamientoEntregado entrega) throws Exception;

    void actualizarMpDireccionamientoEntregadoFactura(
            MpDireccionamientoEntregado entrega) throws Exception;

    void actualizarEntregaFactura(
            MpEntregaFactura dato) throws Exception;

    public void actualizaSuministro(
            Integer idTransaccion,
            String idSuministro) throws Exception;

    MpDireccionamientoEntregado consultarExistente(String idTransaccion, String idReporteEntrega, Integer numeroEntrega) throws java.lang.Exception;

    void actualizarExistente(Integer id, Integer estadoReporte, Date fechaAnulacion) throws Exception;

    void actualizarEntregaFacturaExistente(String id, Short estado, String idCicloFacturacion, Date fechaAnulacion) throws Exception;

    void actualizarIdEntregaFacturaExistente(Integer id, Integer idEntrega) throws Exception;
    
    void actualizarIdEntregaSuministroExistente(Integer id, Integer idEntrega) throws Exception;
    
    void actualizarCodigoFacturado(Integer id, String cod) throws Exception;

    public MpEntregaFactura consultarEntregaFactura(String idTransaccion, String idFacturacion);
    
    public MpEntregaSuministro consultarEntregaSuministro (Integer idTransaccion, Integer idSuministro);

    public String consultarDescripcion(String codigo, Integer tipo);

    public MpDescripcionEntregaCoigo consultarDescripciond(String codigo, Integer tipo);

    MpEntregaSuministro consultarIdExistente(String idSuministro) throws Exception;

    void ajustarSuministro(Integer id, String fecha, Integer estado, boolean anulado) throws Exception;
}
