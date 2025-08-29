package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjOtrosi;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CtnjOtrosiRemoto {
    
    /**
     * Consulta de cantidad de registros 
     * @author idbohorquez
     * @creacion 31/10/2024
     * @param paramConsulta
     * @return int
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 31/10/2024
     * @param paramConsulta
     * @return List<CntjContrato>
     * @throws Exception 
     */
    List<CntjOtrosi> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Guardar nuevo registros
     * @author idbohorquez
     * @return int
     * @creacion 31/10/2024
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjOtrosi objeto) throws Exception;

    /**
     * Consultar registro por id
     * @author idbohorquez
     * @param id
     * @return int
     * @creacion 31/10/2024
     * @throws Exception 
     */
    CntjOtrosi consultar(int id) throws Exception;
    
    /**
     * Actualizar registro
     * @author idbohorquez
     * @param objeto
     * @creacion 31/10/2024
     * @throws Exception 
     */
    void actualizar(CntjOtrosi objeto) throws Exception;
    
    /**
     * consultar ultimo numero del registro
     * @author idbohorquez
     * @param idcontrato
     * @return 
     * @creacion 31/10/2024
     * @throws Exception 
     */
    CntjOtrosi ultimoNumeroOtroSi(int idcontrato) throws Exception;
    
    /**
     * consultar si existe acta de inicio
     * @author idbohorquez
     * @param idcontrato
     * @return 
     * @creacion 31/10/2024
     * @throws Exception 
     */
    CntjOtrosi otrosiActaInicio(int idcontrato) throws Exception;
    
    /**
     * eliminar registros
     * @author idbohorquez
     * @param id
     * @return 
     * @creacion 30/04/2025
     * @throws Exception 
     */
    CntjOtrosi eliminar(int id) throws Exception;
    
    /**
     * consulta otrosi vigentes
     * @author idbohorquez
     * @param idcontrato
     * @return 
     * @creacion 01/07/2025
     * @throws Exception 
     */
    Integer otrosiVigentes(int idcontrato) throws Exception;
    
}
