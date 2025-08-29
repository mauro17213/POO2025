package com.saviasaludeps.savia.negocio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsReglaEntrada;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsReglaSalida;
import java.util.List;

public interface CmRipsReglaSalidaRemoto {

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (Personas)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmRipsReglaSalida obj) throws Exception;

    List<CmRipsReglaSalida> consultarLista(int idRegla) throws Exception;
    
        
    /**
     * Método para eliminar un registro
     * @param id
     * @throws java.lang.Exception
     */
    void eliminar(int id) throws Exception;

}
