package com.saviasaludeps.savia.negocio.informe;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.informe.InfGenerado;
import java.util.List;

public interface InformeGeneradoRemoto {

    /**
     * Guardar un informe generado
     *
     * @param informeGenerado
     * @return
     * @throws java.lang.Exception
     */
    int insertar(InfGenerado informeGenerado) throws Exception;

    /**
     * Consultar lista de sucesos generados por informe
     *
     * @param paramConsulta
     * @return
     * @throws java.lang.Exception
     */
    List<InfGenerado> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar un informe generados
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    InfGenerado consultar(int id) throws Exception;

    /**
     * Consultar cantidad de sucesos generados por informe
     *
     * @param paramConsulta
     * @return
     * @throws java.lang.Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    InfGenerado eliminar(int id) throws Exception;
    
    /**
     * Lista los informes generados con sus valores segun el id del informe
     * @param idInforme
     * @return
     * @throws Exception 
     */
    List<InfGenerado> consultarPorInforme(int idInforme) throws Exception;
    
    /**
     * Valida el limite de informes generandose por usuario
     * @param usuario
     * @param cantidad
     * @return
     * @throws Exception 
     */
    boolean consultarLimiteGeneracion(String usuario, String cantidad) throws Exception;

}
