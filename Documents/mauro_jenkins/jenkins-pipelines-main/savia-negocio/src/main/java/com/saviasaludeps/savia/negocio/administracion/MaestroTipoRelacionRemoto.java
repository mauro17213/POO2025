package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.MaestroTipoRelacion;
import java.util.List;



public interface MaestroTipoRelacionRemoto {
    
    /**
     * Método para crear una nueva Relacion entre tablas tipomaestro
     *
     * @param obj (MaestroTipoRelaciones)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(MaestroTipoRelacion obj) throws Exception;
    
    /**
     * Método para consultar los registros activos
     * @param tipo
     * @return
     * @throws Exception
     */
    List<MaestroTipoRelacion> consultarPadresPorTipoHijo(String tipo)throws Exception;
    
    /**
     * Método para eliminar un MaestroTipoRelacion
     * @param id
     * @return (Empresa) objeto eliminado
     * @throws java.lang.Exception
     */
    MaestroTipoRelacion eliminar(int id)throws Exception;

}
