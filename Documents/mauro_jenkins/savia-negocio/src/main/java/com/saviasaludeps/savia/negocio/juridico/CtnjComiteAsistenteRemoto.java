package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjComiteAsistente;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CtnjComiteAsistenteRemoto {
    
    /**
     * Consulta de cantidad de registros 
     * @author idbohorquez
     * @creacion 18/09/2024
     * @param paramConsulta
     * @return int
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 18/09/2024
     * @param paramConsulta
     * @return List<CntjComite>
     * @throws Exception 
     */
    List<CntjComiteAsistente> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Guardar nuevo registros
     * @author idbohorquez
     * @return int
     * @creacion 18/09/2024
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjComiteAsistente objeto) throws Exception;

    /**
     * Consultar registro por id
     * @author idbohorquez
     * @param id
     * @return int
     * @creacion 18/09/2024
     * @throws Exception 
     */
    CntjComiteAsistente consultar(int id) throws Exception;
    
    /**
     * Actualizar registro
     * @author idbohorquez
     * @param objeto
     * @creacion 18/09/2024
     * @throws Exception 
     */
    void actualizar(CntjComiteAsistente objeto) throws Exception;
    
    /**
     * Eliminar registro
     * @author idbohorquez
     * @param id
     * @return 
     * @creacion 18/09/2024
     * @throws Exception 
     */
    CntjComiteAsistente eliminar(int id) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @param idcomite
     * @return 
     * @creacion 18/09/2024
     * @throws Exception 
     */
    List<CntjComiteAsistente> asistentesComite(int idcomite) throws java.lang.Exception;
    
    
}
