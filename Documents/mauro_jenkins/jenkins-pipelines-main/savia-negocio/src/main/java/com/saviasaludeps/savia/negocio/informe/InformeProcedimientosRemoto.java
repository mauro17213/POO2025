/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.saviasaludeps.savia.negocio.informe;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.informe.InfProcedimiento;
import java.util.List;

/**
 *
 * @author aguevara
 */
public interface InformeProcedimientosRemoto {
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
    List<InfProcedimiento> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Metodo para listar procedimientos
     *
     * @return
     * @throws java.lang.Exception
     */
    List<InfProcedimiento> consultarTodosPorNombre(String name) throws Exception;

    /**
     * Consultar un procedimiento por id
     *
     * @param name
     * @return
     * @throws java.lang.Exception
     */
    InfProcedimiento consultarPorNombreScript(String name) throws Exception;

    /**
     * Guardar un procedimiento
     *
     * @param procedimiento
     * @return
     * @throws java.lang.Exception
     */
    int insertar(InfProcedimiento procedimiento) throws Exception;

    /**
     * Modificar un procedimiento
     *
     * @param procedimiento
     * @throws java.lang.Exception
     */
    void actualizar(InfProcedimiento procedimiento) throws Exception;

    /**
     * Borrar un procedimiento por id
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    InfProcedimiento eliminar(int id) throws Exception;
    
    /**
     * Método para listar procedimientos almacenados SQL tipo 'sp_inf%'.
     * @return 
     * @throws java.lang.Exception
     */
    List<Object[]> consultarProcedimientosSql() throws Exception;
}
