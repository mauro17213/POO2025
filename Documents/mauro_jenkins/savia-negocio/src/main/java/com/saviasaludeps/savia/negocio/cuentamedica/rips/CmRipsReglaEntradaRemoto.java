package com.saviasaludeps.savia.negocio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsReglaEntrada;
import java.util.List;

public interface CmRipsReglaEntradaRemoto {
   
    /**
     * Método para consultar las reglas de entrada por id de regla
     * @param idRegla id de la regla asociada a la entrada
     * @return obj CmRipsReglaEntrad
     * @throws java.lang.Exception
     */
    List<CmRipsReglaEntrada> consultarLista(int idRegla) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (CmRipsReglaEntrad)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmRipsReglaEntrada obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @throws java.lang.Exception
     */
    void eliminar(int id) throws Exception;

}
