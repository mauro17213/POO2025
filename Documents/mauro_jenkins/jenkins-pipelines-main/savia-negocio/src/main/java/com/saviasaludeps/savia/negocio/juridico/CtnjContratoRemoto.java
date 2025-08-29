package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjContrato;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CtnjContratoRemoto {
    
    /**
     * Consulta de cantidad de registros 
     * @author idbohorquez
     * @creacion 24/10/2024
     * @param paramConsulta
     * @return int
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 24/10/2024
     * @param paramConsulta
     * @return List<CntjContrato>
     * @throws Exception 
     */
    List<CntjContrato> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Guardar nuevo registros
     * @author idbohorquez
     * @return int
     * @creacion 24/10/2024
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjContrato objeto) throws Exception;

    /**
     * Consultar registro por id
     * @author idbohorquez
     * @param id
     * @return int
     * @creacion 24/10/2024
     * @throws Exception 
     */
    CntjContrato consultar(int id) throws Exception;
    
    /**
     * Actualizar registro
     * @author idbohorquez
     * @param objeto
     * @creacion 24/10/2024
     * @throws Exception 
     */
    void actualizar(CntjContrato objeto) throws Exception;
    
    /**
     * Eliminar registro por id
     * @author idbohorquez
     * @param id
     * @return 
     * @creacion 24/10/2024
     * @throws Exception 
     */
    CntjContrato eliminar(int id) throws Exception;
    
    /**
     * Actualizar plazo prorrogas
     * @author idbohorquez
     * @param objeto 
     * @creacion 17/01/2025
     * @throws Exception 
     */
    void actualizarPlazoProrroga(CntjContrato objeto) throws Exception ;
    
    /**
     * Actualizar valor adiciones
     * @author idbohorquez
     * @param objeto 
     * @creacion 17/01/2025
     * @throws Exception 
     */
    void actualizarValorAdiciones(CntjContrato objeto) throws Exception ;
    
    /**
     * Actualizar valor fecha fin contrato
     * @author idbohorquez
     * @param objeto 
     * @creacion 24/01/2025
     * @throws Exception 
     */
    void actualizarFechaFin(CntjContrato objeto) throws Exception;
    
    /**
     * Validar si ya existe el codigo
     * @author idbohorquez
     * @param codigo 
     * @return  
     * @creacion 14/03/2025
     * @throws Exception 
     */
    boolean existeContratoCodigo(String codigo) throws Exception ;
    
    /**
     * Consulta ultimo numero contrato año actual
     * @author idbohorquez
     * @param anio 
     * @return  
     * @creacion 27/03/2025
     * @throws Exception 
     */
    String ultimoNumeroContrato(String anio) throws Exception;
    
    /**
     * Consulta contrato por numero
     * @author idbohorquez
     * @param numero 
     * @return  
     * @creacion 30/04/2025
     * @throws Exception 
     */
    CntjContrato contratoPorNumero(String numero) throws Exception;
    
}
