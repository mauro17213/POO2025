/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.Anexo4Reporte;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Reporte;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuAnexo4Remoto {

    /**
     * Consulta la cantidad de datos de parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta la lista dado los parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<AuAnexo4> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AuAnexo4 consultar(int id) throws Exception;
    
    /**
     * Consulta anexo4 recortado
     * @param id
     * @return
     * @throws Exception 
     */
    AuAnexo4 consultarCorto(int id) throws Exception;
    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AuAnexo4 consultarByIdAnexo2(int id) throws Exception;
    
    /**
     * Consulta el objeto dado el id
     *
     * @param idCotizacion
     * @return
     * @throws Exception
     */
    AuAnexo4 consultarByIdCotizacion(int idCotizacion) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AuAnexo4 obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AuAnexo4 obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizarEstado(AuAnexo4 obj) throws Exception;
    
    /**
     * Actualiza valor copago permiso especial
     * @param obj
     * @throws Exception 
     */
    void actualizarValorCopago(AuAnexo4 obj) throws Exception;

    /**
     * Actualiza estado y campos relacionados con impresion.
     * @param obj
     * @return
     * @throws Exception 
     */
    public int autorizarPreautorizacion(AuAnexo4 obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizarArchivoReporte(AuAnexo4 obj) throws Exception;

    /**
     * Anular autorizacion
     *
     * @param obj
     * @throws Exception
     */
    void anular(AuAnexo4 obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AuAnexo4 eliminar(int id) throws Exception;

    /**
     * Consulta el AuAnexo4 por numero de autorizacion
     *
     * @param autorizacion
     * @return
     * @throws Exception
     */
    AuAnexo4 consultarPorAutorizacion(String autorizacion) throws Exception;
    
    /**
     * Consultar anexo 4 por id anexo 3
     *
     * @param idAnexo3
     * @return
     * @throws Exception
     */
    AuAnexo4 consultarPorAnexo3(int idAnexo3) throws Exception;

    /**
     * Funcion que actualiza las impresiones autorizadas
     *
     * @param obj
     * @throws Exception
     */
    void actualizarImpresion(AuAnexo4 obj) throws Exception;

    /**
     * Consulta la cantidad de datos de parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadListaPorAfiliado(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta la lista dado los parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<AuAnexo4> consultarListaPorAfiliado(ParamConsulta paramConsulta) throws Exception;

    /**
     * Devulve la lista de autorizaciones dado el anexo4
     *
     * @param reporte
     * @return
     * @throws Exception
     */
    List<Anexo4Reporte> consultarListaParaReporte(AuAnexo4Reporte reporte) throws Exception;
    
    /**
     *
     * @param fechaInicio
     * @param fechaFinal
     * @param codHabPrestador
     * @return
     * @throws Exception
     */
    List<AuAnexo4> consultarAutorizacionFecha(
            String fechaInicio, 
            String fechaFinal, 
            String codHabPrestador) throws Exception;
    
    /**
     *
     * @param fechaInicio
     * @param fechaFinal
     * @param codHabPrestador
     * @return
     * @throws Exception
     */
    Integer consultarCantidadAutorizacionesFecha(
            String fechaInicio, 
            String fechaFinal, 
            String codHabPrestador) throws Exception;
    
    void actualizarConsecutivo(AuAnexo4 obj) throws Exception;
}
