package com.saviasaludeps.savia.negocio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupo;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CmGrupoRemoto {
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmGrupo> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    CmGrupo consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (Personas)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmGrupo obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (Personas)
     * @throws java.lang.Exception
     */
    void actualizar(CmGrupo obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (Personas) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmGrupo eliminar(int id) throws Exception;

    /**
     * actualizar id asignacion del grupo
     * @param obj
     * @throws Exception 
     */
    void actualizarIdAsignacion(CmGrupo obj) throws Exception;

    /**
     * permite consultar con criterios como
     * @param paramConsulta: paramConsulta.getParametroConsulta1(): activo,
     *        paramConsulta.getParametroConsulta2(): tipo grupo, 
     *        paramConsulta.getParametroConsulta3(): categoria,
     * @return
     * @throws Exception 
     */
    CmGrupo consultarConParametrizacion(ParamConsulta paramConsulta) throws Exception;

}
