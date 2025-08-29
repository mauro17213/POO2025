package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.especial.PeGestion;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface PeGestionProgramaRemoto {

    /**
     * Inserta un registro de gestion al afiliado 
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(PeGestion obj) throws Exception;
    
    /**
     * Consultar una gestion mediante su id 
     *
     * @param id
     * @return
     * @throws Exception
     */
    public PeGestion consultar(int id) throws Exception;
    
    /**
     * consulta las gestiones de un afiliado especifico
     *
     * @param idAfiliadosProg
     * @return
     * @throws Exception
     */
    List<PeGestion> consultarPorIdAfiliadoPrograma(int idAfiliadosProg) throws Exception;
    
    /**
     * Permite modificar la opcion de una gestion
     *
     * @param objeto
     * @throws Exception
     */
    void modificar(PeGestion objeto) throws Exception;
    
     /**
     * Permite hacer borrado logico de la gestiones mediante el id de la gestion
     *
     * @param obj
     * @throws Exception
     */
    void eliminar(PeGestion obj) throws Exception;
}
