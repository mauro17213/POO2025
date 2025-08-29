
package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.juridico.CntjCampo;
import com.saviasaludeps.savia.dominio.juridico.CntjPlantillaCampo;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CntjPlantillaCampoRemoto {
    
    /**
     * Insertar nuevo registro
     * @author idbohorquez
     * @param objeto
     * @return 
     * @creacion 25/07/2024
     * @throws Exception 
     */
    int insertar(CntjPlantillaCampo objeto) throws java.lang.Exception;
    
    /**
     * Consultar lista de plantillas asociadas a un campo
     * @author idbohorquez
     * @param idcampo
     * @return 
     * @creacion 25/07/2024
     * @throws Exception 
     */
    List<CntjPlantillaCampo> plantillasCampos(int idcampo) throws java.lang.Exception;
    
    /**
     * elimar un registro
     * @author idbohorquez
     * @param id
     * @return 
     * @creacion 25/07/2024
     * @throws Exception 
     */
    CntjPlantillaCampo eliminar(int id) throws Exception;
    
    /**
     * Listado de campos segun plantilla
     * @author idbohorquez
     * @param idplantilla
     * @return 
     * @creacion 25/07/2024
     * @throws Exception 
     */
    List<CntjCampo> getCamposPlantilla(int idplantilla) throws java.lang.Exception;
    
    /**
     * Listado de campos segun el estado
     * @author idbohorquez
     * @param idestado
     * @return 
     * @throws java.lang.Exception 
     * @creacion 25/07/2024 
     */
    List<CntjPlantillaCampo> listaCamposDocumentoEstadoGenerados(int idestado)  throws java.lang.Exception;
    
}
