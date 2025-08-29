/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.especial.PeVariableValorHistorico;
import java.util.List;

/**
 *
 * @author jdlopez
 */
public interface PeVariableValorHistoricoRemoto {

    /**
     * Guarda un registro en PeVariablesValoresHistoricos
     * @param historico
     * @return 
     * @throws Exception 
     */
    int insertar(PeVariableValorHistorico historico) throws Exception;
    
    /**
     * Inserta valores historicos por lotes
     * @param valoresHistoricos
     * @throws Exception 
     */
    void insertarPorLotes(List<PeVariableValorHistorico> valoresHistoricos) throws Exception;
    /**
     * consultaListado en PeVariablesValoresHistoricos de acuerdos a los parametros
     * @param idPrograma
     * @param idAfiliado
     * @param idVariable 
     * @param idValor
     * @return 
     * @throws Exception 
     */    
    List<PeVariableValorHistorico> consultarListadoIdAfiliadoIdProgramaIdValor(int idPrograma, int idAfiliado, int idVariable, int idValor) throws Exception;
}
