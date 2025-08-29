package com.saviasaludeps.savia.negocio.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSedeServicioHabilitacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CntPrestadorSedeServicioHabilitacionRemoto {

    List<CntPrestadorSedeServicioHabilitacion> consultarLista(ParamConsulta paramConsulta) throws Exception;

    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CntPrestadorSedeServicioHabilitacion) cargado
     * @throws java.lang.Exception
     */
    CntPrestadorSedeServicioHabilitacion consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CntPrestadorSedeServicioHabilitacion)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CntPrestadorSedeServicioHabilitacion obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CntPrestadorSedeServicioHabilitacion)
     * @throws java.lang.Exception
     */
    void actualizar(CntPrestadorSedeServicioHabilitacion obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CntPrestadorSedeServicioHabilitacion) La persistencia del objeto
     * eliminado
     * @throws java.lang.Exception
     */
    CntPrestadorSedeServicioHabilitacion eliminar(int id) throws Exception;

    /**
     * Consultar todos los registros
     *
     * @return
     * @throws Exception
     */
    List<CntPrestadorSedeServicioHabilitacion> consultarTodos() throws Exception;

    /**
     * consultar un registro por Prestador Sede y Servicio de Habilitación
     *
     * @param idSede
     * @param idServicioHabilitacion
     * @return
     * @throws Exception
     */
    CntPrestadorSedeServicioHabilitacion consultarPorSedeYServicioHabilitacion(int idSede, int idServicioHabilitacion) throws Exception;

    /**
     * Consulta registro por prestador sede,codigo tecnolgia y servicios de habilitación
     *
     * @param idSede
     * @param codigoTecnologia
     * @param servicios
     * @return
     * @throws Exception
     */
    List<CntPrestadorSedeServicioHabilitacion> consultarPorSedeTecnologiaYServiciosHabilitacion(int idSede, String codigoTecnologia, String servicios) throws Exception;

}
