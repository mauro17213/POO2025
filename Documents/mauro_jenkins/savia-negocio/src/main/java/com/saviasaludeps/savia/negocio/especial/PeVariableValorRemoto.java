/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.especial.PeVariableValor;
import com.saviasaludeps.savia.dominio.especial.PeVariableValorAlmacenado;
import java.util.List;

/**
 *
 * @author jdlopez
 */
public interface PeVariableValorRemoto {

    /**
     * Guarda un registro en PeVariablesValores
     * @param valor
     * @return 
     * @throws Exception 
     */
    int insertar(PeVariableValor valor) throws Exception;
    
    List<PeVariableValor> insertarVariablesValoresPorLotes(List<PeVariableValor> variablesValores);
    /**
     * Guarda varios registros en PeVariablesValores
     * @param valores
     * @param idAfiliado
     * @return 
     * @throws Exception 
     */
    List<PeVariableValor> insertarListado(List<PeVariableValor> valores, int idAfiliado) throws Exception;
    
     /**
     * Consulta registros de acuerdo al id en PeVariablesValores
     * @param idVariableValor
     * @return 
     * @throws Exception 
     */
    PeVariableValor consultar(int idVariableValor)throws Exception;
    
     /**
     * Consulta registro de acuerdo al id del afiliado y de la vairbale
     * @param idVariable
     * @param idAfiliado
     * @return 
     * @throws Exception 
     */
    PeVariableValor consultarIdVariableIdAfiliado(int idVariable, int idAfiliado)throws Exception;
    
    /**
     * Consulta el valor de una variable valor  con su id
     * @param tipo
     * @param idVariable
     * @param idAfiliado
     * @return 
     * @throws Exception 
     */
    PeVariableValorAlmacenado consultarValorPorIdVariableIdAfiliado(int tipo, int idVariable, int idAfiliado)throws Exception;
    
    /**
     * Consulta registros de acuerdo al id del afiliado relacionado en PeVariablesValores
     * @param idAfiliado
     * @return 
     * @throws Exception 
     */
    List<PeVariableValor> consultarListadoIdAfiliado(int idAfiliado)throws Exception;
    
    /**
     * Actualiza un registro de PeVariablesValores
     * @param valor
     * @throws Exception 
     */
    void actualizar(PeVariableValor valor) throws Exception;
    
    /**
     * Actualiza varios registros en PeVariablesValores
     * @param valores
     * @throws Exception 
     */
    void actualizarListado(List<PeVariableValor> valores) throws Exception;
    /**
     * Actualiza varios registros en PeVariablesValores por lotes
     * @param valores
     * @throws Exception 
     */
    void actualizarPorLotes(List<PeVariableValor> valores) throws Exception;
}
