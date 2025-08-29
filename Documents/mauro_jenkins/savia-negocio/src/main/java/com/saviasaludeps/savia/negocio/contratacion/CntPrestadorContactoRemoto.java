package com.saviasaludeps.savia.negocio.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorContacto;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CntPrestadorContactoRemoto {

    List<CntPrestadorContacto> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CntPrestadorContacto) cargado
     * @throws java.lang.Exception
     */
    CntPrestadorContacto consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CntPrestadorContacto)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CntPrestadorContacto obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CntPrestadorContacto)
     * @throws java.lang.Exception
     */
    void actualizar(CntPrestadorContacto obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CntPrestadorContacto) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CntPrestadorContacto eliminar(int id) throws Exception;
    
    /**
     * Consultar todos los registros
     *
     * @return
     * @throws Exception
     */
    List<CntPrestadorContacto> consultarTodos() throws Exception;
    
    /**
     * Consultar todos los registros asociados a la sede
     *
     * @param prestador
     * @param prestadorSede
     * @return
     * @throws Exception
     */
    List<CntPrestadorContacto> consultarPorCntContratoSede(int prestador, int prestadorSede) throws Exception;
    
    /**
     * Consultar todos los registros asociados a la sede
     *
     * @param prestador
     * @param prestadorSede
     * @param tipo
     * @return
     * @throws Exception
     */
    List<CntPrestadorContacto> consultarPorCntContratoSedeYTipo(int prestador, int prestadorSede, String tipo) throws Exception;
    
    /**
     * Consultar todos los registros asociados a la sede
     *
     * @param prestador
     * @param prestadorSede
     * @param area
     * @return
     * @throws Exception
     */
    List<CntPrestadorContacto> consultarPorCntContratoSedeYArea(int prestador, int prestadorSede, String area) throws Exception;
    
    /**
     * Consultar todos los registros asociados a la sede
     *
     * @param prestador
     * @param prestadorSede
     * @param tipo
     * @param area
     * @return
     * @throws Exception
     */
    List<CntPrestadorContacto> consultarPorCntContratoSedeTipoYArea(int prestador, int prestadorSede, String tipo, String area) throws Exception;
}
