package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.math.BigDecimal;
import java.util.List;

public interface AuCotizacionRemoto {

    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AuCotizacion consultar(int id) throws Exception;

    /**
     * Consulta la ultima cotización de la tecnología relacionada
     *
     * @param tipo 
     * @param codigoTecnologia 
     * 
     * @return
     * @throws Exception
     */
    AuCotizacion consultarUltimoPorTipoYCodigo(int tipo, String codigoTecnologia) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AuCotizacion obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AuCotizacion obj) throws Exception;
    
    /**
     * Cuenta la cantidad de cotizaciones
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Lista las cotizaciones
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<AuCotizacion> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consulta dado el id del item de anexo3
     * @param idItemAnexo3
     * @return
     * @throws Exception 
     */
    AuCotizacion consultarPorIdItemAnexo3(int idItemAnexo3) throws Exception;
    
    
    /**
     * Consulta dado el id del item de anexo3
     * @param idAnexo3
     * @return
     * @throws Exception 
     */
    AuCotizacion consultarPorIdAnexo3(int idAnexo3) throws Exception;
    
    /**
     * Trae el valor del soat de este año por el id del tarifario
     * @return
     * @throws Exception 
     */
    BigDecimal consultarValorSoat(int idTarifario) throws Exception;

}
