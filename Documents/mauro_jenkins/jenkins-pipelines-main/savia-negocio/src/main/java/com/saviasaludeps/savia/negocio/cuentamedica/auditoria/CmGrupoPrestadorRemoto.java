package com.saviasaludeps.savia.negocio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupoPrestador;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CmGrupoPrestadorRemoto {
    /**
     * Consultar lista de registros por id del grupo
     * @param id del grupo
     * @return
     * @throws Exception 
     */
    List<CmGrupoPrestador> consultarLista(int id) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    CmGrupoPrestador consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (Personas)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmGrupoPrestador obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (Personas)
     * @throws java.lang.Exception
     */
    void actualizar(CmGrupoPrestador obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (Personas) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmGrupoPrestador eliminar(int id) throws Exception;

    /**
     * Permite consultar catidad prestadores por grupo
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    /**
     * permite cuscar grupo prestador
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmGrupoPrestador> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite consutlar por grupo y prestador
     * @param idGrupo
     * @param idPrestador
     * @return
     * @throws Exception 
     */
    CmGrupoPrestador consultar(int idGrupo, int idPrestador) throws Exception;

}
