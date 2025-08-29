package com.saviasaludeps.savia.negocio.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.Date;
import java.util.List;

public interface CntContratoDetalleRemoto {

    List<CntContratoDetalle> consultarLista(ParamConsulta paramConsulta) throws Exception;

    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    int consultarCantidadListaSinAutorizacion(ParamConsulta paramConsulta) throws Exception;

    List<CntContratoDetalle> consultarListaSinAutorizacion(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CntContratoDetalle) cargado
     * @throws java.lang.Exception
     */
    CntContratoDetalle consultar(int id) throws Exception;

    /**
     * Método para consultar registros por Tecnologia ID
     *
     * @param id
     * @return (CntContratoDetalle) cargado
     * @throws java.lang.Exception
     */
    int cantidadPorTecnologia(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CntContratoDetalle)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CntContratoDetalle obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CntContratoDetalle)
     * @throws java.lang.Exception
     */
    void actualizar(CntContratoDetalle obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CntContratoDetalle)
     * @throws java.lang.Exception
     */
    void actualizarMarcacion(CntContratoDetalle obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CntContratoDetalle) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CntContratoDetalle eliminar(int id) throws Exception;

    /**
     * Consultar todos los registros
     *
     * @return
     * @throws Exception
     */
    List<CntContratoDetalle> consultarTodos() throws Exception;

    /**
     * Consulta un registro con el los ids de contrato, contratoSede, tecnologia
     * y servicio de habilitación
     *
     * @param idContrato
     * @param idContratoSede
     * @param idTecnologia
     * @param idServicioHabilitacion
     * @return
     * @throws Exception
     */
    CntContratoDetalle consultarPorContratoSedeTecnologiaTipoYServicioHabilitacion(int idContrato, int idContratoSede, int idTecnologia, int idServicioHabilitacion, int tipoTecnologia) throws Exception;

    /**
     * Consulta un registro con el los ids de contrato, contratoSede y
     * tecnologia
     *
     * @param idContrato
     * @param idContratoSede
     * @param idTecnologia
     * @return
     * @throws java.lang.Exception
     */
    public CntContratoDetalle consultarPorContratoSedeTecnologiaYTipoTecnologia(int idContrato, int idContratoSede, int idTecnologia, int tipoTecnologia) throws Exception;

    /**
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<CntContratoDetalle> consultarListaTecnologias(ParamConsulta paramConsulta) throws Exception;

    /**
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadListaTecnologias(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para obtener los registros de contratos asociados a una tecnologia
     * entregada
     *
     * @param tipoTecnologia
     * @param idTecnologia
     * @return
     * @throws Exception
     */
    List<CntContrato> consultarPorTecnologia(int tipoTecnologia, int idTecnologia) throws Exception;

    /**
     * Método paa obtener los registros de Detalle Contrato asociados a una sede
     *
     * @param idContratoSede
     * @return
     * @throws Exception
     */
    List<CntContratoDetalle> consultarPorContratoSede(int idContratoSede) throws Exception;

    /**
     * Consulta un registro con el los ids de contrato, contratoSede, tecnologia
     * y servicio de habilitación
     *
     * @param idContrato
     * @param idContratoSede
     * @param idTecnologia
     * @param idServicioHabilitacion
     * @param tipoTecnologia
     * @param tipoManualTarifario
     * @param idManualTarifario
     * @param anioSoat
     * @param idAmbito
     * @return
     * @throws Exception
     */
    CntContratoDetalle consultar(int idContrato, int idContratoSede, int idTecnologia, int idServicioHabilitacion, int tipoTecnologia, int tipoManualTarifario, Integer idManualTarifario, Integer anioSoat, Integer idAmbito) throws Exception;

    /**
     * Consulta un registro con el los ids de contrato, contratoSede y
     * tecnologia
     *
     * @param idContrato
     * @param idContratoSede
     * @param idTecnologia
     * @param tipoTecnologia
     * @param tipoManualTarifario
     * @param idManualTarifario
     * @param anioSoat
     * @param idAmbito
     * @return
     * @throws java.lang.Exception
     */
    public CntContratoDetalle consultar(int idContrato, int idContratoSede, int idTecnologia, int tipoTecnologia, int tipoManualTarifario, Integer idManualTarifario, Integer anioSoat, Integer idAmbito) throws Exception;

    /**
     * consulta registro activo por tecnologia, servicio de habilitacion,
     * prestador sede y contrato activo
     *
     * @param idPrestadorSede
     * @param codigoTecnologia
     * @param codigoServicioHabilitacion
     * @param tipoTecnologia
     * @param modalidad del contrato
     * @return
     * @throws java.lang.Exception
     */
    CntContratoDetalle consultarPorSedeTecnologiaTipoYServicioHabilitacion(int idPrestadorSede, String codigoTecnologia, String codigoServicioHabilitacion, int tipoTecnologia, String modalidad) throws java.lang.Exception;

    /**
     * consulta registro activo por tipo tecnologia y sede entrega
     *
     * @param tipoTecnologia
     * @param sedeEntregaId
     * @param sedeEntregaId
     * @return
     * @throws java.lang.Exception
     */
    CntContratoDetalle consultarPorSedeTecnologiaYTipo(int tecnologiaId, int tipoTecnologia, int sedeEntregaId) throws java.lang.Exception;

    /**
     * función que actualiza la fecha fin de los contrato detalles de un
     * contrato especifico, siempre que esten activos.
     *
     * @param idContrato
     * @param FechaFinContrato
     * @throws Exception
     */
    public void actualizarProrrogaCntContratoDetalles(int idContrato, Date FechaFinContrato) throws Exception;

    List<CntContratoDetalle> consultarListaMarcaciones(ParamConsulta paramConsulta) throws Exception;

    int consultarCantidadListaMarcaciones(ParamConsulta paramConsulta) throws Exception;

    /**
     * Validar la cantidad de contrato detalles para los cuales una tecnología
     * se encuentra activa para una sede especifica
     *
     * @param tipoTecnologia
     * @param codigo_tecnologia
     * @param codigoTecnologia
     * @param cntPrestadorSedeId
     * @param maeModalidadContratoCodigo
     * @return
     * @throws Exception
     */
    public int consultarCantidadPorTipoTecnologiaYPrestadorSede(int tipoTecnologia, String codigoTecnologia,
            int cntPrestadorSedeId, String maeModalidadContratoCodigo) throws Exception;

    /**
     * consulta registro activo por tecnologia, servicio de habilitacion,
     * prestador sede y contrato activo
     *
     * @param tipoTecnologia
     * @param idTecnologia
     * @param idPresetadorSede
     * @return
     * @throws java.lang.Exception
     */
    public List<CntContratoDetalle> consultarListaSinAutorizacionCarga(int tipoTecnologia, int idTecnologia, int idPresetadorSede) throws Exception;
}
