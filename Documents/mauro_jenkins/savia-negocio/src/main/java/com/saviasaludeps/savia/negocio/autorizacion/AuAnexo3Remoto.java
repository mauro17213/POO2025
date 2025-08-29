/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesionalPrestador;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuAnexo3Remoto {

    /**
     * Consulta la cantidad de datos de parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta la lista dado los parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<AuAnexo3> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AuAnexo3 consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AuAnexo3 obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AuAnexo3 obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AuAnexo3 eliminar(int id) throws Exception;

    /**
     * Servicio que trae las autorizaciones del afiliado segun los parametros
     *
     * @param tipoDocumento
     * @param numeroDocumento
     * @param fechaNacimiento
     * @return
     * @throws Exception
     */
    List<AuAnexo3> consultarAutorizacion(String tipoDocumento, String numeroDocumento, Date fechaNacimiento) throws Exception;

    /**
     * Funcion que trae los programas especiales asociados al afiliado
     *
     * @param idAfiliado
     * @return
     * @throws Exception
     */
    List<PePrograma> consultarProgramasAfiliado(int idAfiliado) throws Exception;

    /**
     * Consulta la cantidad de prestador sedes
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadPrestadorSedes(ParamConsulta paramConsulta) throws Exception;

    /**
     * Funcion que trae el contrato entre ips y profesional
     *
     * @param prestadorId
     * @param profesionalId
     * @return
     * @throws Exception
     */
    CntProfesionalPrestador buscarEspeciliadad(int prestadorId, int profesionalId) throws Exception;

    /**
     * Funcion que cuenta el contrato detalle
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadListaContratos(ParamConsulta paramConsulta) throws Exception;

    /**
     * Funcion que trae la lista de contrato detalle
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<CntContratoDetalle> consultarListaContratos(ParamConsulta paramConsulta) throws Exception;

    /**
     * Devuelve el contrato detalle
     *
     * @param idIps
     * @param idTecnologia
     * @param tipoTecnologia
     * @return
     * @throws Exception
     */
    CntContratoDetalle consultarContratoDetalle(int idIps, int idTecnologia, int tipoTecnologia) throws Exception;

    /**
     * Actualiza el estado de la solicitud
     *
     * @param obj
     * @throws Exception
     */
    void actualizarEstado(AuAnexo3 obj) throws Exception;

    /**
     * actualizar estado_proceso_actual
     *
     * @param obj
     */
    void actualizarEstadoProcesoActual(AuAnexo3 obj) throws Exception;
    
    /**
     * consulta estado_proceso_actual de una solicitud
     * @param idSolicitud
     * @return
     * @throws Exception 
     */
    int consultarProcesoActual(int idSolicitud) throws Exception;
    
    /**
     * Consulta la cantidad de datos de parametros - Consulta Afiliado 360
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadListaAfiliado(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta la lista dado los parametros - Consulta Afiliado 360
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<AuAnexo3> consultarListaAfiliado(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Actualiza el consecutivo segun formato resolucion 2335
     * @param obj
     * @throws Exception
     */
    void actualizarConsecutivo(AuAnexo3 obj) throws Exception;
}
