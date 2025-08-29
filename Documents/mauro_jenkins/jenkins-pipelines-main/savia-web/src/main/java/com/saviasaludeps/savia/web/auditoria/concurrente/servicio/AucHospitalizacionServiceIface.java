/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.auditoria.concurrente.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucDiagnostico;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.ReporteActaJustificacion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.ReporteHospitalizacion;
import com.saviasaludeps.savia.web.auditoria.concurrente.bean.AucHospitalizacionBean;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface AucHospitalizacionServiceIface {

    void Accion(AucHospitalizacionBean bean);

    void cargasInicial(AucHospitalizacionBean bean);

    void listarAfiliado(AucHospitalizacionBean bean);

    void listarSeguimientos(AucHospitalizacionBean bean);
    
    void listarGestorasRegionales(AucHospitalizacionBean bean);
    
    void listarDiagnosticoEstancia(AucHospitalizacionBean bean);

    void listarDiagnosticosIngreso(AucHospitalizacionBean bean);

    void listarDiagnosticosEgreso(AucHospitalizacionBean bean);

    void listarEstancias(AucHospitalizacionBean bean);

    void listarJustifiacionEstanciasProlongada(AucHospitalizacionBean bean);

    void listarPalogias(AucHospitalizacionBean bean);

    void listarInoportunidades(AucHospitalizacionBean bean);

    void listarAdversos(AucHospitalizacionBean bean);

    void listarObjeciones(AucHospitalizacionBean bean);

    void listarServicios(AucHospitalizacionBean bean);

    void completarAfiliado(AucHospitalizacionBean bean);

    boolean validarEstadoAfiliado(int maeEstadoAfiliacion, AucHospitalizacionBean bean);

    boolean validarHospitalizacionActivas(AsegAfiliado afiliado, AucHospitalizacionBean bean);

    boolean validarHospitalizacionIpsSoloDosActivas(AsegAfiliado afiliado, AucHospitalizacionBean bean);

    void listarIps(AucHospitalizacionBean bean);

    void guardarAfiliadoContacto(AucHospitalizacionBean bean);

    void guardarSeguimientos(AucHospitalizacionBean bean);
    
    void guardarGestionRegionales(AucHospitalizacionBean bean);

    void guardarEstancias(AucHospitalizacionBean bean);
    
    void guardarDiagnosticoEspecialidad(AucHospitalizacionBean bean);
    
    void guardarDiagnosticoEstancia(AucHospitalizacionBean bean);

    void guardarJustificacionEstanciaProlongada(AucHospitalizacionBean bean);

    void actualizarEstanciasDescripcion(AucHospitalizacionBean bean);

    void guardarPatologias(AucHospitalizacionBean bean);

    void guardarInoportunidad(AucHospitalizacionBean bean);

    void guardarAdversos(AucHospitalizacionBean bean);

    void guardarObjeciones(AucHospitalizacionBean bean);

    void guardarServicios(AucHospitalizacionBean bean);

    void guardarDiagnostico(AucHospitalizacionBean bean);

    void calcularRegingreso(AucHospitalizacionBean bean);

    boolean validarCodigoEvento(AucHospitalizacionBean bean);

    void listarEstadosDeSubCategoria(AucHospitalizacionBean bean);

    void consultarFechaIngresoSeaMenor(AucHospitalizacionBean bean);

    void consultarHospitalizacionExceptoAnuladas(AucHospitalizacionBean bean);

    void consultarHospitalizacionExceptoAnuladasEgreso(AucHospitalizacionBean bean);

    void consultarHospitalizacionInoportunidad(AucHospitalizacionBean bean);

    void consultarHospitalizacionAdverso(AucHospitalizacionBean bean);

    void consultarHospitalizacionServicio(AucHospitalizacionBean bean);

    List<ReporteActaJustificacion> generarReporteActas(AucHospitalizacionBean bean);

    void validarDiagnosticosNoHayaMasPrincipales(AucHospitalizacionBean bean, AucDiagnostico diagnostico);

    void consultarGestionRiegosSugerido(AucHospitalizacionBean bean);

    void listarGestionRiesgo(AucHospitalizacionBean bean);

    void consultarTelefonosAfiliadoAseguradoYhospitalizacion(AucHospitalizacionBean bean);

    /**
     * Consulta la hospitalizacion dado el id
     *
     * @param idHosipitalizacion
     * @param bean
     * @return
     */
    AucHospitalizacion consultarhospitalizacionId(int idHosipitalizacion, AucHospitalizacionBean bean);

    /**
     * Consulta la hospitalizacion dado el id
     *
     * @param bean
     * @return
     */
    void consultarLimpiarMaestroDestion(AucHospitalizacionBean bean);
    
    /**
     * Consulta la hospitalizacion dado el id
     *
     * @param bean
     * @return
     */
    void consultarMaestroDestino(AucHospitalizacionBean bean);
    
    /**
     * Consulta el sugerido para saber si se puede borrar de la base datos
     *
     * @param bean
     */
    void validarSugeridoParaBorrar(AucHospitalizacionBean bean);
    
    /**
     * Consulta la hospitalizacion para generar el documento de descarga de la hospitalizacion
     *
     * @param bean
     * @return 
     */
    List<ReporteHospitalizacion> descargarHospitalizacion(AucHospitalizacionBean bean);
    
    /**
     * Consulta el historial del recate por el afiliado
     *
     * @param bean
     */
    void consultarHistorialRescate(AucHospitalizacionBean bean);
    
    
    /**
     * Consulta el historial de los egresos de la hospitalizacion por el id 
     *
     * @param bean
     */
    void consultarHistorialHospitalizacionesEgreso(AucHospitalizacionBean bean);
    
    /**
     * Consulta el los programa que aplican para el rescate
     *
     * @param bean
     */
    void consultarListaAplicaRescateHospitalizacion(AucHospitalizacionBean bean);
    
    /**
     * Consulta el los programa que aplican para el rescate
     *
     * @param bean
     * @return 
     */
    boolean consultarAplicaRescateHospitalizacionPrograma(AucHospitalizacionBean bean);
    
     /**
     * Consulta si aplica rescate en una hospitalizacion
     *
     * @param bean
     * @return 
     */
    int consultarSiAplicaRescate(AucHospitalizacionBean bean);
    
    /**
     * Consulta el los programa que aplican para el rescate
     *
     * @param bean
     */
    void consultarMaestrosSeguimiento(AucHospitalizacionBean bean);
    
    
    /**
     * Consulta el los programa que aplican para el rescate
     *
     * @param bean
     */
    void consultarNoAptoRescate(AucHospitalizacionBean bean);
    
     /**
     * Consulta el maestro tipo de adjuntos para rescates
     *
     * @param bean
     * @return 
     */
    Maestro consultarMaestroPeadjuntoTipo(AucHospitalizacionBean bean);
    
    void consultarMaestroMotivoReingreso(AucHospitalizacionBean bean);
    
    void consultarMaestroPosibleAltaTemprana(AucHospitalizacionBean bean);
    
    /**
     * Consulta el maestro 
     *
     * @param bean
     * @return 
     */
    Maestro consultarMaestro(int id, AucHospitalizacionBean bean);
}
