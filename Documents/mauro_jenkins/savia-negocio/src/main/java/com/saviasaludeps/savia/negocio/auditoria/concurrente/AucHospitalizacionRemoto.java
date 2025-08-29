/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface AucHospitalizacionRemoto {

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
    List<AucHospitalizacion> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta la cantidad de datos de parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadListaConsultaTrescientosSesenta(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta la lista dado los parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<AucHospitalizacion> consultarListaConsultaTrescientosSesenta(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucHospitalizacion consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AucHospitalizacion obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AucHospitalizacion obj) throws Exception;
    
    /**
     * Actualiza la fecha inicio de la hospitalizacion
     *
     * @param obj
     * @throws Exception
     */
    void actualizaFechaInicioHospitalizacion(AucHospitalizacion obj) throws Exception;
    
    /**
     * Actualiza la fecha fin de la hospitalizacion
     *
     * @param obj
     * @throws Exception
     */
    void actualizaFechaFinHospitalizacion(AucHospitalizacion obj) throws Exception;
    
    /**
     * Actualiza los dias de la hospitalizacion
     *
     * @param obj
     * @throws Exception
     */
    void actualizaDiasHospitalizacion(AucHospitalizacion obj) throws Exception;
    
    /**
     * Actualiza los dias de la hospitalizacion
     *
     * @param obj
     * @throws Exception
     */
    void actualizaReveirHospitalizacion(AucHospitalizacion obj) throws Exception;
    
    /**
     * Actualiza el estado de la auditoria
     *
     * @param obj
     * @throws Exception
     */
    void actualizarDiagnosticoEspecialidad(AucHospitalizacion obj) throws Exception;
    
    /**
     * Actualiza el estado de la auditoria
     *
     * @param obj
     * @throws Exception
     */
    void actualizarEstadoAauditoria(AucHospitalizacion obj) throws Exception;
    
    /**
     * Actualiza el estado de la del rescate
     *
     * @param obj
     * @throws Exception
     */
    void actualizarAplicaRescate(AucHospitalizacion obj) throws Exception;
    
     /**
     * Actualiza el estado de la de no acto rescate
     *
     * @param obj
     * @throws Exception
     */
    void actualizarNoAptoRescate(AucHospitalizacion obj) throws Exception;

    /**
     * Actualiza el estado de la auditoria
     *
     * @param obj
     * @throws Exception
     */
    void actualizarEstadoAnulacion(AucHospitalizacion obj) throws Exception;

    /**
     * Actualiza estado
     *
     * @param obj
     * @throws Exception
     */
    void actualizarEstado(AucHospitalizacion obj) throws Exception;
    
    /**
     * Actualiza estado
     *
     * @param obj
     * @throws Exception
     */
    void actualizarFechaUltimaNota(AucHospitalizacion obj) throws Exception;
    
    /**
     * Actualiza estado
     *
     * @param obj
     * @throws Exception
     */
    void actualizarValorDiarioAcomulado(AucHospitalizacion obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucHospitalizacion eliminar(int id) throws Exception;

    /**
     * Consulta la ultima hosmitalizacion del alfiliado
     *
     * @param idAfiliado
     * @param idHospitalizacion
     * @return
     * @throws Exception
     */
    AucHospitalizacion consultarUltimaHospitalizacionAfiliado(Integer idAfiliado, Integer idHospitalizacion) throws Exception;

    /**
     * Metodo que se encarga de consultar las hospitalizaciones activas del
     * afiliado por sedes
     *
     * @param idAfiliado
     * @param idPrestadorSede
     * @return
     * @throws Exception
     */
    int consultarHozpitalizacionActivas(Integer idAfiliado, Integer idPrestadorSede) throws Exception;

    /**
     * Metodo que se encarga de consultar las hospitalizaciones activas del
     * afiliado por sedes
     *
     * @param idAfiliado
     * @return
     * @throws Exception
     */
    int consultarHospitalizacionIpsSoloDosActivas(Integer idAfiliado) throws Exception;

    /**
     * Actualiza el estado de la auditoria
     *
     * @param codigoEvento
     * @return
     * @throws Exception
     */
    List<AucHospitalizacion> consultarCodigoEventoHosítalizacion(int codigoEvento) throws Exception;
    
    /**
     * Valida si el codigo de evento existe
     *
     * @param codigoEvento
     * @return
     */
    boolean validarCodigoEvento(String codigoEvento);
    
    /**
     * Valida si aplica rescate
     *
     * @param id
     * @return
     */
    int consultarAplicaRescate(Integer id);
    
    /**
     * Valida si aplica rescate
     *
     * @param id
     * @return
     */
    boolean consultarNoAptoRescate(Integer id);
    /**
     * Valida si hay una hospitalizacion activa para el usuario
     *
     * @param idAfiliado
     * @param idPrestadorSede
     * @return
     */
    boolean validarActivaAfiliado(int idAfiliado, int idPrestadorSede);
    
    /**
     * consulta si hay una hospitalizacion activa para el usuario
     *
     * @param idAfiliado
     * @param idPrestadorSede
     * @return
     */
    AucHospitalizacion consultarHospitalizacionPorAfiliadoAndPrestador(int idAfiliado, int idPrestadorSede);

    /**
     * Consulta la fecha de ingreso sea menor a la de ingreso en las
     * hospitalizaciones para saber si permiter crear la hospitalizacion
     *
     * @param idAfiliado
     * @param fechaIngreso
     * @return
     * @throws Exception
     */
    Integer consultarFechaIngresoSeaMenor(Integer idAfiliado, Date fechaIngreso) throws Exception;

    /**
     * Consulta la fecha de ingreso sea menor a la de ingreso en las
     * hospitalizaciones para saber si permiter crear la hospitalizacion
     *
     * @param idAfiliado
     * @param IdHospitalizacion
     * @param IdPrestadorSedeId
     * @return
     * @throws Exception
     */
    List<AucHospitalizacion> consultarHospitalizacionExceptoAnuladas(Integer idAfiliado, Integer IdHospitalizacion, Integer IdPrestadorSedeId) throws Exception;

    /**
     * Consulta la fecha de ingreso sea menor a la de ingreso en las
     * hospitalizaciones para saber si permiter crear la hospitalizacion
     *
     * @param idPrestadorSede
     * @return
     * @throws Exception
     */
    List<AucHospitalizacion> consultarHospitalizacionPorSedes(Integer idPrestadorSede) throws Exception;

    /**
     * Consulta la hospitalizacion actual por afiliado
     *
     * @param idAfiliado
     * @return
     * @throws Exception
     */
    AucHospitalizacion consultarHospitalizacionActualAfiliado(Integer idAfiliado) throws Exception;

    /**
     * hospitalizaciones abiertas de afiliados no cargados en censo
     * @param idAfiliados
     * @param sede
     * @return
     * @throws Exception
     */
    List<AucHospitalizacion> consultarHospitalizacionesAbiertasCargaMasiva(String idAfiliados, int sede) throws Exception;
    
    /**
     * Actualiza el estado de la auditoria
     *
     * @param codigoEvento
     * @param idPrestadorSede
     * @return
     * @throws Exception
     */
    List<AucHospitalizacion> consultarCodigoEventoHosítalizacionConEstadoEgreso(int codigoEvento, int idPrestadorSede) throws Exception;
    
    
    /**
     * Valida si hay una hospitalizacion activa para NO apto rescate
     *
     * @param idHospitalizacion
     * @return
     */
    Boolean consultaNoAptoRescate(int idHospitalizacion) throws Exception;
    
    /**
     * Consultar hospitalizacion del alfiliado por fecha ingreso y sede
     *
     * @param idAfiliado
     * @param idPrestadorSede
     * @param fechaIngreso
     * @return
     * @throws Exception
     */
    AucHospitalizacion consultarHospitalizacionAfiliado(int idAfiliado, int idPrestadorSede, Date fechaIngreso) throws Exception;
    
}
