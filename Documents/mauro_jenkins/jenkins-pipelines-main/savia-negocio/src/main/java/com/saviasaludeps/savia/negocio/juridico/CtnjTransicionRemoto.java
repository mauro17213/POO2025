
package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjTransicion;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CtnjTransicionRemoto {
    
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
    List<CntjTransicion> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Guardar nuevo registro
     * @author idbohorquez
     * @return int
     * @creacion 08/07/2024
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjTransicion objeto) throws Exception;

    /**
     * Consultar registro por id
     * @author idbohorquez
     * @param idtransicion
     * @return int
     * @creacion 08/07/2024
     * @throws Exception 
     */
    CntjTransicion consultar(int idtransicion) throws Exception;
    
    /**
     * Actualizar registro
     * @author idbohorquez
     * @param objeto
     * @creacion 08/07/2024
     * @throws Exception 
     */
    void actualizar(CntjTransicion objeto) throws Exception;
    
   
    
}
