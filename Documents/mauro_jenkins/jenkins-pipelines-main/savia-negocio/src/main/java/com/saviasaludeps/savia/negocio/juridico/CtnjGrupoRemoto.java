
package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjGrupo;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CtnjGrupoRemoto {
    
    /**
     * Consulta de cantidad de registros 
     * @author idbohorquez
     * @creacion 09/08/2024
     * @param paramConsulta
     * @return int
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 09/08/2024
     * @param paramConsulta
     * @return List<CntjGrupo>
     * @throws Exception 
     */
    List<CntjGrupo> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Guardar nuevo registros
     * @author idbohorquez
     * @return int
     * @creacion 09/08/2024
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjGrupo objeto) throws Exception;

    /**
     * Consultar registro por id
     * @author idbohorquez
     * @param idgrupo
     * @return int
     * @creacion 09/08/2024
     * @throws Exception 
     */
    CntjGrupo consultar(int idgrupo) throws Exception;
    
    /**
     * Actualizar registro
     * @author idbohorquez
     * @param objeto
     * @creacion 09/08/2024
     * @throws Exception 
     */
    void actualizar(CntjGrupo objeto) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @return 
     * @creacion 09/08/2024
     * @throws Exception 
     */
    public List<CntjGrupo> lista() throws java.lang.Exception;
    
}
