package com.saviasaludeps.savia.negocio.informe;

import com.saviasaludeps.savia.dominio.informe.InfInformeValor;
import java.util.List;

public interface InformeValorRemoto {

    /**
     * Guardar una valor para una variable
     *
     * @param variable
     * @return
     * @throws java.lang.Exception
     */
    int insertar(InfInformeValor variable) throws Exception;

    /**
     * Consulta una lista de valores de un informe generado
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    List<InfInformeValor> consultarListaValores(int id) throws Exception;
    
    /**
     * Consulta los valors de un informe por id variable
     * @param idVariable
     * @return
     * @throws Exception 
     */
    List<InfInformeValor> consultarPorIdVariable(int idVariable) throws Exception;
    
    /**
     * Borra un valor
     * @param id
     * @return
     * @throws Exception 
     */
    InfInformeValor borrar(int id) throws Exception;

}
