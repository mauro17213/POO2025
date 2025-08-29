package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.MaestroRelacion;
import java.util.List;



public interface MaestroRelacionRemoto {
    
    /**
     * Método para crear una nueva Relacion entre tablas tipomaestro
     *
     * @param obj (MaestroTipoRelaciones)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(MaestroRelacion obj) throws Exception;
    
    /**
     * Método para consultar los registros activos
     * @param id
     * @return
     * @throws Exception
     */
    List<MaestroRelacion> consultarPadresPorIdMaestroHijo(int id)throws Exception;
    
    /**
     * Método para eliminar un MaestroTipoRelacion
     * @param id
     * @return (Empresa) objeto eliminado
     * @throws java.lang.Exception
     */
    MaestroRelacion eliminar(int id)throws Exception;

}
