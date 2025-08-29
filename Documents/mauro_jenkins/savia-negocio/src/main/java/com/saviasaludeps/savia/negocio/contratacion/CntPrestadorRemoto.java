/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.webservices.rest.objeto.contratacion.IpsDTO;
import java.util.HashMap;
import java.util.List;

public interface CntPrestadorRemoto {

    /**
     * Consultar todos por nombre
     *
     * @param nombre nombre de la ips
     * @return (CntPrestador)
     * @throws Exception
     */
    List<CntPrestador> consultarPorNombre(String nombre) throws Exception;

    /**
     * Consultar todos por nombre
     *
     * @param nit
     * @return (CntPrestador)
     * @throws Exception
     */
    CntPrestador consultarPorNit(String nit) throws Exception;

    /**
     * Consultar todos por id
     *
     * @param id prestador
     * @return (CntPrestadorSedes)
     * @throws Exception
     */
    CntPrestador consultarPorPrestador(int id) throws Exception;

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<CntPrestador> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
     /**
     * Método que permite consultar varios prestadores por su tipo de documento y numero de documento
     * @param tipodocumento
     * @param numeroDocumento
     * @return
     * @throws Exception 
     */
    List<CntPrestador> consultarListaPrestador(String tipodocumento, String numeroDocumento) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (ReConciliacionRecobros) cargado
     * @throws java.lang.Exception
     */
    CntPrestador consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (ReConciliacionRecobros)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CntPrestador obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (AfAfiliados)
     * @throws java.lang.Exception
     */
    void actualizar(CntPrestador obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (AfAfiliados) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CntPrestador eliminar(int id) throws Exception;

    /**
     * Consultar todos los registros
     *
     * @return
     * @throws Exception
     */
    List<CntPrestador> consultarTodos() throws Exception;

    /**
     * Método que permite obtener las sedes pertenecientes a un prestador
     *
     * @param divipola
     * @return
     * @throws Exception
     */
    public List<CntPrestadorSede> consultarListaSedes(String divipola) throws Exception;

    /**
     *
     * @param divipola
     * @return
     * @throws Exception
     */
    public HashMap<String, CntPrestadorSede> consultarHashSedesPorId(String divipola) throws Exception;

    /**
     * Método que permite obtener las sedes pertenecientes a un prestador
     *
     * @param divipola
     * @return
     * @throws Exception
     */
    public List<CntPrestadorSede> consultarListaSedesCapitadas(String divipola) throws Exception;

    /**
     *
     * @param divipola
     * @return
     * @throws Exception
     */
    public HashMap<String, CntPrestadorSede> consultarHashSedesCapitadasPorId(String divipola) throws Exception;

    /**
     * Método para consultar un registro de CntPrestadorSede por codigo
     * habilitacion Sede
     *
     * @param codigo
     * @return (ReConciliacionRecobros) cargado
     * @throws java.lang.Exception
     */
    CntPrestadorSede consultarSedePorCodigoHabilitacion(String codigo) throws Exception;

    /**
     *
     * @param divipola
     * @return
     * @throws Exception
     */
    //HashMap<Integer, CntPrestadorSede> consultarHashSedesPorDiviPoli(String divipola) throws Exception;

    /**
     * Método que consulta un registro de CntPrestadorSede por id
     *
     * @param id
     * @return
     * @throws Exception
     */
    CntPrestadorSede consultarSede(int id) throws Exception;

    /**
     * Método que consulta un registro por código habilitación y
     *
     * @param codigo
     * @return
     * @throws Exception
     */
    CntPrestadorSede consultarSedePorCodigoHabilitacionTodos(String codigo) throws Exception;
    
    /**
     * Método que consulta un registro por código habilitación y que la sede este activa
     *
     * @param codigo
     * @return
     * @throws Exception
     */
    CntPrestadorSede consultarSedePorCodigoHabilitacionActivo(String codigo) throws Exception;

    /**
     * Método que consulta todos los registros de CntPrestadorSede
     *
     * @return
     * @throws Exception
     */
    List<CntPrestadorSede> consultarSedesTodos() throws Exception;

    /**
     * Método que consulta ips
     *
     * @param codigoHabilitacionPrestador
     * @param documentoPrestador
     * @param codigoSedePrestador
     * @return
     * @throws Exception
     */
    public IpsDTO consultarIps(String codigoHabilitacionPrestador, String documentoPrestador, String codigoSedePrestador) throws java.lang.Exception;

    public int consultarCantidadListaSede(ParamConsulta paramConsulta) throws Exception;

    public List<CntPrestadorSede> consultarListaSede(ParamConsulta paramConsulta) throws Exception;

    int consultarCantidadBuscadorSede(ParamConsulta paramConsulta) throws Exception;

    List<CntPrestadorSede> consultarListaBuscadorSede(ParamConsulta paramConsulta) throws Exception;

    CntPrestador consultarPorCodigoHabilitacion(String codigoHabilitacion);
    
    /**
     * Consulta un prestador por su código MinSalud
     * @param codMinSalud
     * @return
     * @throws Exception 
     */
    CntPrestador consultarPorCodigoMinSalud(String codMinSalud) throws Exception;
    
    /**
     * Consulta un prestador por su código MinSalud
     * @param id
     * @param codMinSalud
     * @return
     * @throws Exception 
     */
    CntPrestador consultarPorIdYCodigoMinSalud(int id,String codMinSalud) throws Exception;
    
    
    /**
     * Consulta por tipo y numero de documento
     * @param id
     * @param numeroDocumento
     * @return
     * @throws Exception 
     */
    CntPrestador consultarPorTipoDocumentoYNumero(int id, String numeroDocumento) throws Exception;
    
    /**
     * Consulta listado de ips segun ubicación sin importar el nivel de atención
     * @param divipola
     * @return
     * @throws Exception 
     */
    List<CntPrestadorSede> consultarListaSedesSinNivelAtencion(String divipola) throws java.lang.Exception;

    /**
     * Consulta la cantidad de sedes pertenecientes a los prestadores asociados a una unión temporal
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    public int consultarCantidadListaSedeUnionTemporal(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta las sedes pertenecientes a los prestadores asociados a una unión temporal
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    public List<CntPrestadorSede> consultarListaSedeUnionTemporal(ParamConsulta paramConsulta) throws Exception;
}
