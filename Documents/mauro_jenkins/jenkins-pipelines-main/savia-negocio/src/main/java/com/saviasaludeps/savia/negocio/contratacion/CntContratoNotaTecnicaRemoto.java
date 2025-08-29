package com.saviasaludeps.savia.negocio.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntContratoNotaTecnica;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface CntContratoNotaTecnicaRemoto {

    List<CntContratoNotaTecnica> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CntContratoNotaTecnica) cargado
     * @throws java.lang.Exception
     */
    CntContratoNotaTecnica consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CntContratoNotaTecnica)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CntContratoNotaTecnica obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CntContratoNotaTecnica)
     * @throws java.lang.Exception
     */
    void actualizar(CntContratoNotaTecnica obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CntContratoNotaTecnica) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CntContratoNotaTecnica eliminar(int id) throws Exception;
    
    /**
     * Consultar todos los registros
     *
     * @return
     * @throws Exception
     */
    List<CntContratoNotaTecnica> consultarTodos() throws Exception;
    
    /**
     * 
     * Método para consultar un registro por los parámetros indicados.
     * 
     * @param contratoId
     * @param tecnologiaId
     * @param frecuenciaUso
     * @param tipoFrecuencia
     * @param cantidadAfiliados
     * @param costoPromedio
     * @param fechaInicio
     * @param fechaFin
     * @param ambitoId
     * @return
     * @throws Exception 
     */
    CntContratoNotaTecnica consultar(int contratoId, int tecnologiaId, BigDecimal frecuenciaUso, int tipoFrecuencia,int cantidadAfiliados, BigDecimal costoPromedio, Date fechaInicio, Date fechaFin, int ambitoId) throws Exception;
    
    /**
     * 
     * @param contratoId
     * @param tecnologiaId
     * @return
     * @throws Exception 
     */
    int contarCantidadTecnologiasEnContrato(int contratoId, int tecnologiaId) throws Exception;
    
    /**
     * 
     * @param contratoId
     * @return
     * @throws Exception 
     */
    List<CntContratoNotaTecnica> consultarPorContrato(int contratoId) throws Exception;
    
}
