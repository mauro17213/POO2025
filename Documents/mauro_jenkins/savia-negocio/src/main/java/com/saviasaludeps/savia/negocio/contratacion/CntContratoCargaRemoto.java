package com.saviasaludeps.savia.negocio.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntContratoCarga;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CntContratoCargaRemoto {

    List<CntContratoCarga> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CntContratoCarga) cargado
     * @throws java.lang.Exception
     */
    CntContratoCarga consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CntContratoCarga)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CntContratoCarga obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CntContratoCarga)
     * @throws java.lang.Exception
     */
    void actualizar(CntContratoCarga obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CntContratoCarga) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CntContratoCarga eliminar(int id) throws Exception;
    
    /**
     * Consultar todos los registros
     *
     * @return
     * @throws Exception
     */
    List<CntContratoCarga> consultarTodos() throws Exception;
    
    /**
     * Método para consultar el registro en estado Pendiente que debe ser procesado
     *
     * @param id
     * @return (CntContratoCarga) cargado
     * @throws java.lang.Exception
     */
    CntContratoCarga consultarSiguienteCarga(int estado) throws Exception;
    
    /**
     * Método para consultar la cantidad de registros que tienen un nombre de Archivo dado.
     * @param nombreArchivo
     * @return
     * @throws Exception 
     */
    int consultarCantidadCargasArchivoExistente(String nombreArchivo) throws Exception;
}
