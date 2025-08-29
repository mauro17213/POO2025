package com.saviasaludeps.savia.negocio.informe;

import com.saviasaludeps.savia.dominio.informe.InfInformeVariable;

public interface InformeVariableRemoto {

    /**
     * Guardar una variable de informe
     *
     * @param variable
     * @return
     * @throws java.lang.Exception
     */
    int insertar(InfInformeVariable variable) throws Exception;

    /**
     * Actualizar una variable de informe
     *
     * @param variable
     * @throws java.lang.Exception
     */
    void actualizar(InfInformeVariable variable) throws Exception;
    
    /**
     * Eliminar un registro
     * @param id
     * @return
     * @throws Exception 
     */
    InfInformeVariable eliminar(int id) throws Exception;

}
