package com.saviasaludeps.savia.negocio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupoUsuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CmGrupoUsuarioRemoto {

    /**
     * Método para consultar una lista de registros por ID del GRUpo
     *
     * @param id del Grupo
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    List<CmGrupoUsuario> consultarLista(int id) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    CmGrupoUsuario consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (Personas)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmGrupoUsuario obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (Personas)
     * @throws java.lang.Exception
     */
    void actualizar(CmGrupoUsuario obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (Personas) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmGrupoUsuario eliminar(int id) throws Exception;

    /**
     * Método para buscar usuario por tipo y codigo Prestador
     *
     * @param idTipo tipo Lider, Técnico o Médico
     * @param nit NIT del prestador
     * @return (CmGrupoUsuario) el usuario encontrado
     * @throws java.lang.Exception
     */
    CmGrupoUsuario consultarPorTipoYNit(int idTipo, String nit) throws Exception;

    /**
     * Metodo para buscar los grupos a los que pertenece un usuario actualmente.
     *
     * @param idUsuario usuario del sistema
     * @return
     * @throws Exception
     */
    List<CmGrupoUsuario> consultarPorUsuarioLista(int idUsuario) throws Exception;

    /**
     * Método para buscar usuario por usuario, tipo y codigo Prestador
     *
     * @param idEmpresa : Empresa donde se realiza la busqueda de usuario.
     * @param idTipo tipo Lider(1), Técnico(2) o Médico(3)
     * @param usuario
     * @param idGrupos : conjunto de grupos para buscar usuarios.
     * @return (CmGrupoUsuario) el usuario encontrado
     * @throws java.lang.Exception
     */
    List<Usuario> consultarListaByNitTipoUsuario(int idEmpresa, int idTipo, String usuario, String idGrupos) throws Exception;

    /**
     * Método para buscar lider técnico de grupo
     *
     * @param idTipo tipo Lider, Técnico o Médico
     * @param nit NIT del prestador
     * @param camaFija si tiene o no cama fija
     * @param pbs si tiene o no pbs
     * @return List(CmGrupoUsuario) el usuario encontrado
     * @throws java.lang.Exception
     */
    CmGrupoUsuario consultarUsuarioLider(int idTipo, String nit, boolean camaFija, boolean pbs) throws Exception;
    /**
     * permite concultar grupos 
     * @param paramConsulta param1: idEmpresa, param2: tipo usuario, param3:idGrupo
     * @return
     * @throws Exception 
     */
    List<CmGrupoUsuario> consultarPorAtributosLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite consultar grupo
     * @param idGrupo
     * @param idUsuario
     * @param idTipo
     * @return
     * @throws Exception 
     */
     CmGrupoUsuario consultar(int idGrupo, int idUsuario, int idTipo) throws Exception;

     /**
      * Permite consultar cantidad usuarios grupo
      * @param paramConsulta
      * @return
      * @throws Exception 
      */
     int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

     /**
      * Permite consular los usuarios grupo
      * @param paramConsulta; paramConsulta.getParametroConsulta1() : activo , 
      * paramConsulta.getParametroConsulta2(): idgrupo
      * @return
      * @throws Exception 
      */
     List<CmGrupoUsuario> consultarLista(ParamConsulta paramConsulta) throws Exception;

     /**
      * permite consultar usuarios segun parametros
      * @param paramConsulta;  paramConsulta.getParametroConsulta1() : activo,
      * paramConsulta.getParametroConsulta2(): idgrupo, 
      * paramConsulta.getParametroConsulta3() tipo usuario
      * @return
      * @throws Exception 
      */
     List<CmGrupoUsuario> consultarListaParametrizada(ParamConsulta paramConsulta) throws Exception;

}
