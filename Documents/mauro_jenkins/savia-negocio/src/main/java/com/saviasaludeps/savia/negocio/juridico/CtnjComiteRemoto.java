package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjComite;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CtnjComiteRemoto {
    
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
     * @return List<CntjComite>
     * @throws Exception 
     */
    List<CntjComite> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Guardar nuevo registros
     * @author idbohorquez
     * @return int
     * @creacion 09/08/2024
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjComite objeto) throws Exception;

    /**
     * Consultar registro por id
     * @author idbohorquez
     * @param id
     * @return int
     * @creacion 09/08/2024
     * @throws Exception 
     */
    CntjComite consultar(int id) throws Exception;
    
    /**
     * Actualizar registro
     * @author idbohorquez
     * @param objeto
     * @creacion 09/08/2024
     * @throws Exception 
     */
    void actualizar(CntjComite objeto) throws Exception;
    
    /**
     * Consultar comite abierto
     * @author idbohorquez
     * @return CntjComite
     * @creacion 14/05/2025
     * @throws Exception 
     */
    CntjComite comiteAbierto() throws java.lang.Exception;
    
}
