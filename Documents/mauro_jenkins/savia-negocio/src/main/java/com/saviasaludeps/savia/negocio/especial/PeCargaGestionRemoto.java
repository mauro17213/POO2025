
package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.especial.PeCargaGestion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author  idbohorquez
 */
public interface PeCargaGestionRemoto {
    
    /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta Parametros de la consulta de registros
     * @return Lista de registros consultados
     * @throws Exception 
     */
    List<PeCargaGestion> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Inserta un registro de tipo PeCarga
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(PeCargaGestion obj) throws Exception;
    
    /**
     * Actualiza un registro de tipo PeCarga
     * @param obj
     * @throws Exception 
     */
    void actualizar(PeCargaGestion obj) throws Exception;
    
    /**
     * Consulta un registro por id
     * @param id
     * @return
     * @throws Exception 
     */
    PeCargaGestion consultar(int id) throws Exception;
    
    /**
     * Actualizar nombre de archivo almacenado
     * @param obj
     * @throws Exception 
     */
    void actualizarArchivo(PeCargaGestion obj) throws Exception;
    
    /**
     * Consultar la siguiente carga a procesar
     * @return PeCargaGestion 
     */
    PeCargaGestion consultarCargasSiguientes();
}
