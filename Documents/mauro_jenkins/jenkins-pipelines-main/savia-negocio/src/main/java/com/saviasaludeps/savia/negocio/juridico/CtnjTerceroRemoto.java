
package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjTercero;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CtnjTerceroRemoto {
    
    /**
     * Consulta de cantidad de registros 
     * @author idbohorquez
     * @creacion 22/08/2024
     * @param paramConsulta
     * @return int
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 22/08/2024
     * @param paramConsulta
     * @return List<CntjTercero>
     * @throws Exception 
     */
    List<CntjTercero> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Guardar nuevo registros
     * @author idbohorquez
     * @return int
     * @creacion 22/08/2024
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjTercero objeto) throws Exception;

    /**
     * Consultar registro por id
     * @author idbohorquez
     * @param idtercero
     * @return int
     * @creacion 22/08/2024
     * @throws Exception 
     */
    CntjTercero consultar(int idtercero) throws Exception;
    
    /**
     * Actualizar registro
     * @author idbohorquez
     * @param objeto
     * @creacion 22/08/2024
     * @throws Exception 
     */
    void actualizar(CntjTercero objeto) throws Exception;
    
    /**
     * listado de registros que esten marcado como integrante ut
     * @author idbohorquez
     * @return 
     * @creacion 22/08/2024
     * @throws Exception 
     */
    List<CntjTercero> listaTercerosUt() throws java.lang.Exception;
}
