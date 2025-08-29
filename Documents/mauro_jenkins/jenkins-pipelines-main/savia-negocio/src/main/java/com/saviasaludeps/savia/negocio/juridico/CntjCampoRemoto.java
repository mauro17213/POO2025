package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjCampo;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CntjCampoRemoto {
    
    /**
     * Consulta de cantidad de registros 
     * @author idbohorquez
     * @creacion 19/07/2024
     * @param paramConsulta
     * @return int
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 19/07/2024
     * @param paramConsulta
     * @return List<CntjPlantilla>
     * @throws Exception 
     */
    List<CntjCampo> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
     /**
     * Guardar nuevo registro
     * @author idbohorquez
     * @return int
     * @creacion 19/07/2024
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjCampo objeto) throws Exception;

    /**
     * Consultar campo por id
     * @author idbohorquez
     * @param idcampo
     * @return int
     * @creacion 19/07/2024
     * @throws Exception 
     */
    CntjCampo consultar(int idcampo) throws Exception;
    
    /**
     * Actualizar proceso
     * @author idbohorquez
     * @param objeto
     * @creacion 19/07/2024
     * @throws Exception 
     */
    void actualizar(CntjCampo objeto) throws Exception;
    
    /**
     * Lista de registros por proceso
     * @author idbohorquez
     * @param idproceso
     * @return 
     * @creacion 19/07/2024
     * @throws Exception 
     */
    List<CntjCampo> consultarCamposProceso(int idproceso) throws java.lang.Exception;
    
    /**
     * Lista de registros 
     * @author idbohorquez
     * @param idestado
     * @return 
     * @creacion 19/07/2024
     * @throws Exception 
     */
    List<CntjCampo> listaCamposDocumentoEstadoGenerados(int idestado) throws java.lang.Exception;
    
    /**
     * Lista de registros 
     * @author idbohorquez
     * @return 
     * @creacion 19/07/2024
     * @throws Exception 
     */
    List<String> listaTablasDb() throws java.lang.Exception ;
    
    /**
     * Lista de registros 
     * @author idbohorquez
     * @param tbl
     * @return 
     * @creacion 19/07/2024
     * @throws Exception 
     */
    List<String> listaCamposTabla(String tbl) throws java.lang.Exception;
    
    /**
     * Lista referenciados 
     * @author idbohorquez
     * @param idproceso
     * @return 
     * @creacion 25/03/2025
     * @throws Exception 
     */
    List<CntjCampo> listaCamposReferenciados(int idproceso) throws java.lang.Exception;
    
    /**
     * Valida si existe el nombre del campo 
     * @author idbohorquez
     * @param idproceso
     * @param nombre
     * @return 
     * @creacion 26/03/2025
     * @throws Exception 
     */
    boolean existeNombreCampo(int idproceso, String nombre) throws java.lang.Exception;
    
    /**
     * Valida si existe la etiqueta del campo 
     * @author idbohorquez
     * @param idproceso
     * @param etiqueta
     * @return 
     * @creacion 26/03/2025
     * @throws Exception 
     */
    boolean existeEtiquetaCampo(int idproceso, String etiqueta) throws java.lang.Exception ;
    
    /**
     * Consulta referencias del campo
     * @author idbohorquez
     * @param idestado
     * @param nombreCampo
     * @return 
     * @creacion 26/03/2025
     * @throws Exception 
     */
    List<CntjCampo> camposPorReferencia(int idestado, String nombreCampo) throws java.lang.Exception ;
    
    /**
     * Consulta referencias del campo
     * @author idbohorquez
     * @param campo
     * @return 
     * @creacion 26/03/2025
     * @throws Exception 
     */
    List<CntjCampo> camposPorReferenciaCampo(String campo) throws java.lang.Exception;
    
}
