package com.saviasaludeps.savia.negocio.informe;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.informe.InfRoutine;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface SPRemoto {
    
        /**
     * Metodo para contar listar informes
     *
     * @param paramConsulta
     * @return
     * @throws java.lang.Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Metodo para listar un procedimientos
     *
     * @param paramConsulta
     * @return
     * @throws java.lang.Exception
     */
    List<InfRoutine> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    
    /**
     * Consultar un procedimiento por id
     *
     * @param specificName
     * @return
     * @throws java.lang.Exception
     */
    InfRoutine consultar(String specificName) throws Exception;

    /**
     * Guardar un procedimiento
     *
     * @param routine
     * @throws java.lang.Exception
     */
    void insertar(InfRoutine routine) throws Exception;

    /**
     * Modificar un procedimiento
     *
     * @param routine
     * @throws java.lang.Exception
     */
    void actualizar(InfRoutine routine) throws Exception;

    /**
     * Borrar un procedimiento por id
     *
     * @param specificName
     * @throws java.lang.Exception
     */
    void eliminar(String specificName) throws Exception;
    
    /**
     * Borrar un procedimiento por id
     *
     * @param routineName
     * @return 
     * @throws java.lang.Exception
     */
    boolean existeRoutinePorNombre(String routineName) throws Exception;
       
}
