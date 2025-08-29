/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.especial.PeAfiliadoDiagnostico;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface PeAfiliadoDiagnosticoRemoto {
    
    /**
     * Funcion encargada de insertar un nuevo diagnostico
     * @author idbohorquez
     * @creacion 08/11/2022
     * @param afiliadoDiagnostico
     * @return Integer
     * @throws Exception
     */
    Integer insertar(PeAfiliadoDiagnostico afiliadoDiagnostico) throws Exception;
    
    
    /**
     * Metodo encargada de eliminar registros de diagnosticos
     * @author idbohorquez
     * @creacion 08/11/2022
     * @param afiliadoDiagnostico
     * @throws Exception
     */
    void eliminar(PeAfiliadoDiagnostico diagnostico) throws Exception;
    
    /**
     * Metodo encargado de actualizar un registros de diagnosticos
     * @author idbohorquez
     * @creacion 15/11/2022
     * @param PeAfiliadoDiagnostico
     * @throws Exception
     */
    void actualizar(PeAfiliadoDiagnostico obj) throws Exception;
    
    /**
     * Funcion encargada de consultar el listado de diagnosticos existentes para un afiliado
     * en el programa
     * @author idbohorquez
     * @param afiliadoPrograma
     * @creacion 08/11/2022
     * @param afiliadoDiagnostico
     * @return List<PeAfiliadoDiagnostico>
     * @throws Exception
     */
    List<PeAfiliadoDiagnostico> getDialDiagnosticosAfiliadoPrograma(int afiliadoPrograma) throws Exception;
    
    /**
     * Funcion encargada de consultar diagnostico por idAfiliado programa y por
     * el codigo el diagnostico
     * @author idbohorquez
     * @creacion 15/11/2022
     * @param idAfiliadoPrograma 
     * @param maeCodigoDiagnostico
     * @return PeAfiliadoDiagnostico
     * @throws Exception
     */
    PeAfiliadoDiagnostico consultarAfiliadoPrograma(Integer idAfiliadoPrograma, String maeCodigoDiagnostico) throws Exception;
    
    /**
     * Funcion encargada de consultar el diagnostico principal
     * @author idbohorquez
     * @creacion 15/06/2023
     * @param idAfiliadoPrograma
     * @return PeAfiliadoDiagnostico
     * @throws Exception
     */
    PeAfiliadoDiagnostico consultarDiagnosticoPrincipal(Integer idAfiliadoPrograma) throws Exception;
    
    /**
     * Funcion encargada de remover el diagnostico principal del afilaido programa
     * @author idbohorquez
     * @creacion 15/06/2023
     * @param obj
     * @throws Exception
     */
    void removerDiagnosticoPrincipal(PeAfiliadoDiagnostico obj) throws Exception;
    
}
