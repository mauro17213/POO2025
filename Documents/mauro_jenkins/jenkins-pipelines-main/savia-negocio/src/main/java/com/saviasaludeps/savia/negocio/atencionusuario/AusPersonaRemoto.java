package com.saviasaludeps.savia.negocio.atencionusuario;

import com.saviasaludeps.savia.dominio.atencionusuario.AusPersona;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersonaTelefono;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface AusPersonaRemoto {
        
//    /**
//     * Método para consultar todos los registros
//     * @return 
//     * @throws java.lang.Exception 
//     */
//    List<Persona> consultarTodos() throws Exception;
    
    /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<AusPersona> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    AusPersona consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (Personas)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AusPersona obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (AusPersona)
     * @throws java.lang.Exception
     */
    void actualizar(AusPersona obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (Personas) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    AusPersona eliminar(int id) throws Exception;
    
    /**
     * Consultar todas las Personas
     * @param empresaId
     * @return
     * @throws Exception 
     */
    List<AusPersona> consultarTodas(int empresaId) throws Exception;
    
    List<AusPersona> consultarInternas(int empresaId) throws Exception;
    
    AusPersona consultarPersona(AusPersona obj) throws Exception;

    public AusPersona agregarPersona(AusPersona ausPersona) throws Exception;

    /**
     * permite consultar el ultimo registro de un usuario con documetno determinado
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<AusPersona> consultarUltimosRegistroPersonaLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar todos los telefonos de la persona
     * @param documentoPersona
     * @param documentoPeticionario
     * @return Retorna los telefonos de la persona
     * @throws Exception 
     */
    List<AusPersonaTelefono> consultarTelefonosPersonas(String documentoPersona) throws Exception;
    
    /**
     * Permite consulta personas con telefono
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<AusPersona> consultarPersonaConTelefonosLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite insercion de una persona sin validar telefonos repetidos
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertarSinValidacionTelefonoExistente(AusPersona obj) throws Exception;

    /**
     * Permite buscar los telefonos de una ausPersona
     * @param idAusPersona
     * @return
     * @throws Exception 
     */
    List<AusPersonaTelefono> consultarTelefonosPorAusPersona(Integer idAusPersona) throws Exception;
    
}