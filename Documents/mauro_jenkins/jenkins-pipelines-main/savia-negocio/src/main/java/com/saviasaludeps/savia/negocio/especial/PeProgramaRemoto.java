/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface PeProgramaRemoto {
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
    List<PePrograma> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registro
     * @return
     * @throws Exception 
     */
    List<PePrograma> consultarTodos() throws Exception;
    
    /**
     * Consulta un registro por Id
     * @param id
     * @return Objecto PePrograma
     * @throws Exception 
     */
    PePrograma consultar(int id) throws Exception;
    
    /**
     * Consultar un registro por codigo programa
     * @param codigoPrograma
     * @return 
     * @throws java.lang.Exception
     */
    PePrograma consultarPorCodigo(String codigoPrograma) throws Exception;
     /**
     * Consulta programas pertenencientes a un agrupador a partir del genero 
     * de afiliado, programas aquellos que apliquen en ambos generos y que
     * esten activos.
     * @param maeAgrupadorCodigo
     * @param generoAfiliado
     * @return 
     * @throws java.lang.Exception
     */
    List<PePrograma> consultarPorCodigoAgrupadorGeneroAfiliado(String maeAgrupadorCodigo, short generoAfiliado) throws Exception;
    
    /**
     * Guarda un registro de PeProgramas
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(PePrograma obj) throws Exception;
    
    /**
     * Actualiza un registro PePrograma
     * @param obj
     * @throws Exception 
     */
    void actualizar(PePrograma obj) throws Exception;
    
    /**
     * Elimina un registro PePrograma
     * @param id
     * @return
     * @throws Exception 
     */
    PePrograma eliminar(int id) throws Exception;
    
    /**
     * Metodo que consulta el listado de todos los programas que esten activos
     * @author Isaac Bohroquez
     * @fechaCreacion 13/07/2022
     * @return List<PePrograma>
     * @throws Exception
     */
    List<PePrograma> consultarTodosEstado(int estado) throws Exception;
    
    /**
     * Funcion encargada de consultar listado de programas en los que se encuentra
     * matriculado un afiliado y programas en los que está sugerido
     * @author idbohorquez
     * @fechaCreacion 18/11/2022
     * @param idAfiliado
     * @return List<PePrograma>
     * @throws Exception
     */
    public List<PePrograma> programasMatriculadosSugeridos(Integer idAfiliado) throws Exception;
    
    
    /**
     * Funcion encargada de consultar listado de programas en los que no se encuentra
     * matriculado un afiliado y programas en los que no está sugerido para hospitalizacion
     * @author idbohorquez
     * @fechaCreacion 18/11/2022
     * @param idAfiliado
     * @return List<PePrograma>
     * @throws Exception
     */
    public List<PePrograma> programasNoMatriculadosSugeridosHospitalizacion(Integer idAfiliado) throws Exception;
    
    
    /**
     * Función encargada de consultar listado programas en los que esta matriculado
     * un afilaido por id afilaido y tecnologia.
     * 
     * @author idbohorquez
     * @creado 24/29/2023
     * @param idAfiliado
     * @param maeTecnologiaId
     * @param tipoTecnologia
     * @return List<PePrograma>
     * @throws Exception 
     */
    List<PePrograma> programasAfiliadosTecnologia(int idAfiliado, int maeTecnologiaId, int tipoTecnologia) throws Exception;
    
    /**
     * Función encargada de consultar listado programas en los que aplica rescate
     * para hospitalización segun filtro de parametros
     * 
     * @author idbohorquez
     * @creado 17/04/2023
     * @param idAfilaido
     * @param idSede
     * @return List<PePrograma>
     * @throws Exception 
     */
    List<PePrograma> programasAplicaRescateHospitalizacion(int idAfilaido, int idSede) throws Exception;
    
    /**
     * Funcion encargada de consultar listado de programas en los que no se encuentra
     * matriculado un afiliado y programas en los que no está sugerido para solicitud
     * @author idbohorquez
     * @fechaCreacion 18/11/2022
     * @param idAfiliado
     * @return List<PePrograma>
     * @throws Exception
     */
    List<PePrograma> programasNoMatriculadosSugeridosSolicitud(Integer idAfiliado) throws Exception;
    
}
