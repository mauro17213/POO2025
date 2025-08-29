/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.especial.PeProgramaDiagnostico;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface PeProgramaDiagnosticosRemoto {
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    List<PeProgramaDiagnostico> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Guarda un registro de PeProgramas
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(PeProgramaDiagnostico obj) throws Exception;
    
    /**
     *
     * @param idPrograma
     * @param idAfiliado
     * @return
     * @throws Exception
     */
    List<PeProgramaDiagnostico> consultarDiagnosticosPrograma(int idPrograma) throws Exception;
    
    
    void actualizar(PeProgramaDiagnostico obj) throws java.lang.Exception;
    
    /**
     *
     * @param obj
     * @throws Exception
     */
    void eliminar(PeProgramaDiagnostico obj) throws Exception;
    
    /**
     *
     * @param obj
     * @throws Exception
     */
    void actualizarMarcaAutomatica(PeProgramaDiagnostico obj) throws Exception;
    
    /**
     * @authot Isaac Bohorquez
     * @fechaCreacion 23/06/2022
     * @param idDiagnosticos
     * @return
     * @throws Exception
     */
    List<PeProgramaDiagnostico> consultarDiagnosticosProgramaMarcacionAutomatica(String idDiagnosticos) throws java.lang.Exception;
    
    /**
     * @authot Isaac Bohorquez
     * @fechaCreacion 18/08/2022
     * @param idDiagnosticos
     * @return List<PeProgramaDiagnostico>
     * @throws Exception
     */
    List<PeProgramaDiagnostico> consultarDiagnosticosProgramaDireccionados(String idDiagnosticos) throws java.lang.Exception;
    
    /**
     * Metodo encargado de consultar si existe un registro con el id de programa
     * y el id de maetro diagnostico.
     * 
     * @authot idbohorquez
     * @fechaCreacion 02/12/2022
     * @param objeto
     * @return PeProgramaDiagnostico
     * @throws Exception
     */
    PeProgramaDiagnostico consultarDiagnosticoPrograma(PeProgramaDiagnostico objeto) throws java.lang.Exception;
}
