package com.saviasaludeps.savia.negocio.recobro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.recobro.RcoFacturaDetalle;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface RcoFacturaDetalleRemoto {

    /**
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<RcoFacturaDetalle> consultarLista(ParamConsulta paramConsulta) throws Exception;

    int consultarCantidadListaConciliacion(ParamConsulta paramConsulta) throws Exception;

    List<RcoFacturaDetalle> consultarFacturaDetallesPorPrestadoryCodigo(int prestadorId, String codigo) throws Exception;

    List<RcoFacturaDetalle> consultarListaConciliacion(ParamConsulta paramConsulta) throws Exception;
    
    void actulizarEstadoConciliacion(RcoFacturaDetalle obj)throws Exception;

    /**
     * Método para consultar un maestro por ID
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    RcoFacturaDetalle consultar(int id) throws Exception;

    List<RcoFacturaDetalle> consultarConciliacionPorId(int id) throws Exception;

    /**
     * Método para crear un nuev maestro
     *
     * @param obj
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(RcoFacturaDetalle obj) throws Exception;

    /**
     * Método para actualizar la información de un maestro
     *
     * @param obj
     * @throws java.lang.Exception
     */
    void actualizar(RcoFacturaDetalle obj) throws Exception;

    /**
     * Método para actualizar masivamente los detalles de las facturas
     *
     * @param obj
     * @throws java.lang.Exception
     */
    void actualizarRecobroSiAplica(RcoFacturaDetalle obj) throws Exception;

    void actualizarRecobroNoAplica(RcoFacturaDetalle obj) throws Exception;

    void actulizarIdConciliacion(RcoFacturaDetalle obj) throws Exception;

    /**
     * Eliminar un maestro
     *
     * @param id
     * @return objeto eliminado
     * @throws java.lang.Exception
     */
    RcoFacturaDetalle eliminar(int id) throws Exception;

}
