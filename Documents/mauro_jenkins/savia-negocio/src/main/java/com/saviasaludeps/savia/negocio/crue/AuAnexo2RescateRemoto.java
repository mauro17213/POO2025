package com.saviasaludeps.savia.negocio.crue;

import com.saviasaludeps.savia.dominio.crue.AuAnexo2Rescate;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.Date;
import java.util.List;

/**
 *
 * @author iavenegas
 */
public interface AuAnexo2RescateRemoto {

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<AuAnexo2Rescate> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un AuAnexo2Rescate por ID
     *
     * @param id
     * @return (AuAnexo2Rescate) cargado
     * @throws java.lang.Exception
     */
    AuAnexo2Rescate consultar(int id) throws Exception;

    /**
     * Método para crear una nueva AuAnexo2Rescate
     *
     * @param obj (AuAnexo2Rescate)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AuAnexo2Rescate obj) throws Exception;

    /**
     * Método para cambiar el estado de un AuAnexo2Rescate
     *
     * @param obj
     * @throws Exception
     */
    void actualizarEstado(AuAnexo2Rescate obj) throws Exception;

    /**
     * Método para crear una nueva AuAnexo2Rescate
     *
     * @param desde
     * @param hasta
     * @param estado
     * @param codigoHabilitacion
     * @return
     * @throws java.lang.Exception
     */
    List<AuAnexo2Rescate> consultarxFechas(String desde, String hasta, Integer estado, String codigoHabilitacion) throws Exception;

    /**
     * Método para crear una nueva AuAnexo2Rescate Primer registro se rescate,
     * los demas se rechazan
     *
     * @param tipodoc
     * @param doc
     * @param codHabilitacion
     * @return
     * @throws java.lang.Exception
     */
    List<AuAnexo2Rescate> consultaPacientesWS(String tipodoc, String doc, String codHabilitacion) throws Exception;

    /**
     * actualiza fechaHoraRescate
     *
     * @param idRescate
     * @param fecha
     * @throws Exception
     */
    void actualizarFechaRescate(int idRescate, Date fecha) throws Exception;

    /**
     * actualiza descripcion para ultimo valor gestion
     *
     * @param idRescate
     * @param descripcion
     * @throws Exception
     */
    void actualizarDescripcion(int idRescate, String descripcion) throws Exception;

    /**
     * Consulta rescates por id de anexo2
     *
     * @param anexo2
     * @return
     * @throws Exception
     */
    List<AuAnexo2Rescate> consultarxAnexo2(int anexo2) throws Exception;

    /**
     * Rescates por id anexo3
     *
     * @param anexo3
     * @return
     * @throws Exception
     */
    List<AuAnexo2Rescate> consultarPorAnexo3(int anexo3) throws Exception;

    /**
     * Rescates por id idcntPrestadorSedesOrigen Y idAsegAfiliado
     *
     * @param idcntPrestadorSedesOrigen
     * @param idAsegAfiliado
     * @return
     * @throws Exception
     */
    List<AuAnexo2Rescate> consultarPorNoaptoRescateAnexo2(int idcntPrestadorSedesOrigen, int idAsegAfiliado) throws Exception;

    /**
     * Rescates por id idcntPrestadorSedesOrigen Y idAsegAfiliado
     *
     * @param idcntPrestadorSedesOrigen
     * @param idAsegAfiliado
     * @return
     * @throws Exception
     */
    List<AuAnexo2Rescate> consultarPorNoaptoRescateAnexo3(int idcntPrestadorSedesOrigen, int idAsegAfiliado) throws Exception;

    /**
     *
     * @param afiliado
     * @return
     * @throws Exception
     */
    List<AuAnexo2Rescate> rescatesAfiliadoHistorico(int afiliado) throws Exception;

    /**
     *
     * @param idHospitalizacion
     * @return
     * @throws Exception
     */
    List<AuAnexo2Rescate> rescatesPorHospitalizacion(int idHospitalizacion) throws Exception;

    /**
     * verifieca si existen rescates por programa,hospitalizacion en estado
     * rescatado o pendiente
     *
     * @param idHospitalizacion
     * @param idPrograma
     * @return
     * @throws Exception
     */
    Boolean aplicaRescateHospitalizacionPrograma(int idHospitalizacion, int idPrograma) throws Exception;

    /**
     * si la hospitalizacion tiene rescates en estado pendiente
     *
     * @param idHospitalizacion
     * @return
     * @throws Exception
     */
    Boolean rescatesPendientesHospitalizacion(int idHospitalizacion) throws Exception;

    /**
     * Consulta si existe un rescate de anexo3 con los mimos parametros del
     * rescate de anexo2
     *
     * @param idAfiliado
     * @param idSede
     * @param fecha
     * @return
     * @throws Exception
     */
    Boolean tieneRescateAnexo3(int idAfiliado, int idSede, Date fecha) throws Exception;
}
