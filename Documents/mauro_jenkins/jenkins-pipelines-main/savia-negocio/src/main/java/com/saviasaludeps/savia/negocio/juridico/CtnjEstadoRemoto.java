
package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjEstado;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CtnjEstadoRemoto {
    
    /**
     * Consulta de cantidad de registros 
     * @author idbohorquez
     * @creacion 08/07/2024
     * @param paramConsulta
     * @return int
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 08/07/2024
     * @param paramConsulta
     * @return List<CntjEstado>
     * @throws Exception 
     */
    List<CntjEstado> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Guardar nuevo proceso
     * @author idbohorquez
     * @return int
     * @creacion 08/07/2024
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjEstado objeto) throws Exception;

    /**
     * Consultar proceso por id
     * @author idbohorquez
     * @param idestado
     * @return int
     * @creacion 08/07/2024
     * @throws Exception 
     */
    CntjEstado consultar(int idestado) throws Exception;
    
    /**
     * Actualizar proceso
     * @author idbohorquez
     * @param objeto
     * @creacion 08/07/2024
     * @throws Exception 
     */
    void actualizar(CntjEstado objeto) throws Exception;
    
    /**
     * Consultar estados de un proceso activos
     * @author idbohorquez
     * @param idproceso
     * @return 
     * @creacion 08/07/2024
     * @throws Exception 
     */
    List<CntjEstado> listaEstadosProceso(int idproceso) throws java.lang.Exception;
    
    /**
     * Consultar estados inicial de un proceso
     * @author idbohorquez
     * @param idproceso
     * @param idusuario
     * @return 
     * @creacion 03/02/2025
     * @throws Exception 
     */
    CntjEstado consultarEstadoInicio(int idproceso, int idusuario) throws java.lang.Exception;
    
    /**
     * Consultar lista de estados siguientes
     * @author idbohorquez
     * @param idexpediente
     * @param idusuario
     * @return 
     * @creacion 10/02/2025
     * @throws Exception 
     */
    List<CntjEstado> consultarEstadoSiguientes(int idexpediente,int idusuario) throws java.lang.Exception;
    
    /**
     * Consultar estado 
     * @author idbohorquez
     * @param idproceso
     * @param resultado
     * @param estadoActual
     * @return 
     * @creacion 25/06/2025
     * @throws Exception 
     */
    CntjEstado consultarEstadoResultadoLinea(int idproceso, int resultado, int estadoActual) throws Exception;
    
}
