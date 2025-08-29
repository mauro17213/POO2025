/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.gestionAtencion;

import com.saviasaludeps.savia.dominio.gestionAtencion.GatServicioUmbral;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface GatServicioUmbralRemoto {
    
    /**
     * Inserta el objeto
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(GatServicioUmbral obj) throws Exception;
    
    /**
     * Elimina por id  del objeto
     * @param id
     * @return
     * @throws Exception 
     */
    GatServicioUmbral eliminar(int id) throws Exception;
    
    /**
     * Actualiza el objeto
     * @param obj
     * @throws Exception 
     */
    void actualizar(GatServicioUmbral obj) throws Exception;
    
    /**
     * Lista todos los objetos
     * @return
     * @throws Exception 
     */
    List<GatServicioUmbral> listarTodos() throws Exception;
    
    /**
     * Lista los objetos segun un tipo especifico
     * @param tipo
     * @return
     * @throws Exception
     */
    List<GatServicioUmbral> listarTipo(int tipo) throws Exception;
    
    public GatServicioUmbral consultar(int id) throws Exception;
    
    
}
