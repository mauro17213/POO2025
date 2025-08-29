package com.saviasaludeps.savia.solicitud.negocio;

import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaA4AutomaticoDTO;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaCopagoDTO;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaDTO;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaGrupoAsignado;

public interface ValidadorRemoto {

    /**
     * Función para validar el origen de la atención
     *
     * @param codigoAtencion
     * @return
     */
    ValidaRespuestaDTO validarOrigenAtencion(String codigoAtencion);

    /**
     * Función para validar el tipo de servicio
     *
     * @param codigoTipoServicio
     * @return
     */
    ValidaRespuestaDTO validarTipoServicio(String codigoTipoServicio);

    /**
     * Función para validar el codigo de ubicación
     *
     * @param codigoUbicacion
     * @return
     */
    ValidaRespuestaDTO validarUbicacion(String codigoUbicacion);

    /**
     * Función para validar la prioridad de la atención
     *
     * @param prioridadAtencion
     * @return
     */
    ValidaRespuestaDTO validarPrioridad(String prioridadAtencion);

    /**
     * Función para validar el tipo diagnostico
     *
     * @param tipoDiagnostico
     * @return
     */
    ValidaRespuestaDTO validarTipoDiagnostico(String tipoDiagnostico);

    /**
     * Función para vadalir el diagnostico principal
     *
     * @param indicadorDiagnostico
     * @return
     */
    ValidaRespuestaDTO validarDiagnosticoPrincipal(String indicadorDiagnostico);

    /**
     * Función para validar la sede prestadora
     *
     * @param codigoPrestador
     * @return
     */
    ValidaRespuestaDTO validarPrestadorSede(String codigoPrestador);

    /**
     * Función para validar el tipo de frecuencia
     *
     * @param tipoTecnologia
     * @param tipoFrecuencia
     * @return
     */
    ValidaRespuestaDTO validarTipoFrecuencia(String tipoTecnologia, String tipoFrecuencia);

    /**
     * Función para validar la vía de administración
     *
     * @param tipoTecnologia
     * @param tipoFrecuencia
     * @return
     */
    ValidaRespuestaDTO validarViaAdministracion(String tipoTecnologia, String tipoFrecuencia);

    /**
     * Función para validar la dosis
     *
     * @param tipoTecnologia
     * @param campoAsociado
     * @return
     */
    ValidaRespuestaDTO validarDosis(String tipoTecnologia, String campoAsociado);

    /**
     * Función para validar la frecuencia
     *
     * @param tipoTecnologia
     * @param campoAsociado
     * @return
     */
    ValidaRespuestaDTO validarFrecuencia(String tipoTecnologia, String campoAsociado);

    /**
     * Función para validar la duración del tratamiento
     *
     * @param tipoTecnologia
     * @param campoAsociado
     * @return
     */
    ValidaRespuestaDTO validarDuracionTratamiento(String tipoTecnologia, String campoAsociado);

    /**
     * Función para validar la cantidad de tecnologias
     *
     * @param tipoTecnologia
     * @param campoAsociado
     * @return
     */
    ValidaRespuestaDTO validarCantidad(String tipoTecnologia, String campoAsociado);

    /**
     * Función para validar la fecha de orden medica
     *
     * @param fecha
     * @return
     */
    ValidaRespuestaDTO validarFechaOrdenMedica(String fecha);

    /**
     * Función para validar el ambito
     *
     * @param ambito
     * @return
     */
    ValidaRespuestaDTO validarAmbito(String ambito);

    /**
     * Función para validar el servicio habilitado
     *
     * @param codigoServicioHabilitado
     * @return
     */
    ValidaRespuestaDTO validarServicioHabilitado(String codigoServicioHabilitado);

    /**
     * Función para validar un profesional
     *
     * @param tipoProfesional
     * @param documento
     * @param codigoEspecialidad
     * @param codigoPrestador
     * @return
     */
    ValidaRespuestaDTO validarProfesional(String tipoProfesional, String documento, String codigoEspecialidad, String codigoPrestador);

    /**
     * Función para validar un diagnostico de un afiliado
     *
     * @param codigoDiagnostico
     * @param tipoDocumento
     * @param numeroDocumento
     * @return
     */
    ValidaRespuestaDTO validarDiagnostico(String codigoDiagnostico, String tipoDocumento, String numeroDocumento);

    /**
     * Función para validar el tipo de documento
     *
     * @param tipoDocumento
     * @return
     */
    ValidaRespuestaDTO validarTipoDocumento(String tipoDocumento);

    /**
     * Función para validar un codigo de tecnologia
     *
     * @param tipoTecnologia
     * @param codigoTecnologia
     * @param tipoDocumento
     * @param numeroDocumento
     * @return
     */
    ValidaRespuestaDTO validarCodigoTecnologia(String tipoTecnologia, String codigoTecnologia, String tipoDocumento, String numeroDocumento);

    /**
     * Función para calcular el copago
     *
     * @param idAfiliado
     * @param tipoTecnologia
     * @param idTecnologia
     * @param valorTecnologia
     * @param ambito
     * @param idTutela
     * @param idPrograma
     * @return
     */
    ValidaRespuestaCopagoDTO validarCalCopagoModeradora(String idAfiliado, String tipoTecnologia, String idTecnologia,
            String valorTecnologia, String ambito, String idTutela, String idPrograma);

    /**
     * Función para validar la especialidad de un profesional
     *
     * @param nit
     * @param tipoProfesional
     * @param numeroProfesional
     * @param registroMedico
     * @param primerNombre
     * @param segundoNombre
     * @param primerApellido
     * @param segundoApellido
     * @param idEspecialidad
     * @return
     */
    ValidaRespuestaDTO validarProfesionalEspecialidad(String nit, String tipoProfesional,
            String numeroProfesional, String registroMedico, String primerNombre, String segundoNombre,
            String primerApellido, String segundoApellido, String idEspecialidad);

    /**
     * Función para validar el contrato
     *
     * @param codigoHabilitacionSede
     * @param codigoTecnologia
     * @param idRegimen
     * @return
     */
    ValidaRespuestaDTO validarValContrato(String codigoHabilitacionSede, String codigoTecnologia, String idRegimen);

    /**
     * Función para validar la cantidad de tecnologías autorizadas
     *
     * @param tipoTecnologia
     * @param cantidad
     * @param codigoTecnologia
     * @return
     */
    ValidaRespuestaDTO validarAuCantidadTecnologia(String tipoTecnologia, String cantidad, String codigoTecnologia);

    /**
     * Función para validar el origen de la atención
     *
     * @param codigoAtencion
     * @return
     */
    String validarCargaOrigenAtencion(String codigoAtencion);

    /**
     * Función para validar el tipo de servicio
     *
     * @param codigoTipoServicio
     * @return
     */
    String validarCargaTipoServicio(String codigoTipoServicio);

    /**
     * Función para validar el codigo de ubicación
     *
     * @param codigoUbicacion
     * @return
     */
    String validarCargaUbicacion(String codigoUbicacion);

    /**
     * Función para validar la prioridad de la atención
     *
     * @param prioridadAtencion
     * @return
     */
    String validarCargaPrioridad(String prioridadAtencion);

    /**
     * Función para validar el tipo diagnostico
     *
     * @param tipoDiagnostico
     * @return
     */
    String validarCargaTipoDiagnostico(String tipoDiagnostico);

    /**
     * Función para vadalir el diagnostico principal
     *
     * @param indicadorDiagnostico
     * @return
     */
    String validarCargaDiagnosticoPrincipal(String indicadorDiagnostico);

    /**
     * Función para validar la sede prestadora
     *
     * @param codigoPrestador
     * @return
     */
    String validarCargaPrestadorSede(String codigoPrestador);

    /**
     * Función para validar el tipo de frecuencia
     *
     * @param tipoTecnologia
     * @param tipoFrecuencia
     * @return
     */
    String validarCargaTipoFrecuencia(String tipoTecnologia, String tipoFrecuencia);

    /**
     * Función para validar la vía de administración
     *
     * @param tipoTecnologia
     * @param tipoFrecuencia
     * @return
     */
    String validarCargaViaAdministracion(String tipoTecnologia, String tipoFrecuencia);

    /**
     * Función para validar la dosis
     *
     * @param tipoTecnologia
     * @param campoAsociado
     * @return
     */
    String validarCargaDosis(String tipoTecnologia, String campoAsociado);

    /**
     * Función para validar la frecuencia
     *
     * @param tipoTecnologia
     * @param campoAsociado
     * @return
     */
    String validarCargaFrecuencia(String tipoTecnologia, String campoAsociado);

    /**
     * Función para validar la duración del tratamiento
     *
     * @param tipoTecnologia
     * @param campoAsociado
     * @return
     */
    String validarCargaDuracionTratamiento(String tipoTecnologia, String campoAsociado);

    /**
     * Función para validar la cantidad de tecnologias
     *
     * @param tipoTecnologia
     * @param campoAsociado
     * @return
     */
    String validarCargaCantidad(String tipoTecnologia, String campoAsociado);

    /**
     * Función para validar la fecha de orden medica
     *
     * @param fecha
     * @return
     */
    String validarCargaFechaOrdenMedica(String fecha);

    /**
     * Función para validar el ambito
     *
     * @param ambito
     * @return
     */
    String validarCargaAmbito(String ambito);

    /**
     * Función para validar el servicio habilitado
     *
     * @param codigoServicioHabilitado
     * @return
     */
    String validarCargaServicioHabilitado(String codigoServicioHabilitado);

    /**
     * Función para validar un profesional
     *
     * @param tipoProfesional
     * @param documento
     * @param codigoEspecialidad
     * @param codigoPrestador
     * @return
     */
    String validarCargaProfesional(String tipoProfesional, String documento, String codigoEspecialidad, String codigoPrestador);

    /**
     * Función para validar un diagnostico de un afiliado
     *
     * @param codigoDiagnostico
     * @param tipoDocumento
     * @param numeroDocumento
     * @return
     */
    String validarCargaDiagnostico(String codigoDiagnostico, String tipoDocumento, String numeroDocumento);

    /**
     * Función para validar el tipo de documento
     *
     * @param tipoDocumento
     * @return
     */
    String validarCargaTipoDocumento(String tipoDocumento);

    /**
     * Función para validar un codigo de tecnologia
     *
     * @param tipoTecnologia
     * @param codigoTecnologia
     * @param tipoDocumento
     * @param numeroDocumento
     * @return
     */
    String validarCargaCodigoTecnologia(String tipoTecnologia, String codigoTecnologia, String tipoDocumento, String numeroDocumento);

    /**
     * Función para calcular el copago
     *
     * @param idAfiliado
     * @param tipoTecnologia
     * @param idTecnologia
     * @param valorTecnologia
     * @param ambito
     * @param idTutela
     * @param idPrograma
     * @return
     */
    String validarCargaCalCopagoModeradora(String idAfiliado, String tipoTecnologia, String idTecnologia,
            String valorTecnologia, String ambito, String idTutela, String idPrograma);

    /**
     * Función para validar la especialidad de un profesional
     *
     * @param nit
     * @param tipoProfesional
     * @param numeroProfesional
     * @param registroMedico
     * @param primerNombre
     * @param segundoNombre
     * @param primerApellido
     * @param segundoApellido
     * @param idEspecialidad
     * @return
     */
    String validarCargaProfesionalEspecialidad(String nit, String tipoProfesional,
            String numeroProfesional, String registroMedico, String primerNombre, String segundoNombre,
            String primerApellido, String segundoApellido, String idEspecialidad);

    /**
     * Función para validar el contrato
     *
     * @param codigoHabilitacionSede
     * @param codigoTecnologia
     * @param idRegimen
     * @return
     */
    String validarCargaValContrato(String codigoHabilitacionSede, String codigoTecnologia, String idRegimen);

    /**
     * Función para validar la cantidad de tecnologías autorizadas
     *
     * @param tipoTecnologia
     * @param cantidad
     * @param codigoTecnologia
     * @return
     */
    String validarCargaAuCantidadTecnologia(String tipoTecnologia, String cantidad, String codigoTecnologia);

    /**
     *
     * @param idAfiliodo
     * @param tipoTecnologia
     * @param idTecnologia
     * @param idSede
     * @param idAnexo3
     * @return
     */
    ValidaRespuestaA4AutomaticoDTO validarAprobacionAutomatica(int idAfiliodo, int tipoTecnologia, int idTecnologia, int idSede, int idAnexo3);

    /**
     * Obtiene grupo y usuario para asignación de anexo3
     * @param prestadorSedeId
     * @param tecnologiaId
     * @param diagnosticoId
     * @return
     */
    ValidaRespuestaGrupoAsignado validarGrupoUsuario(boolean tutela, String ambito, boolean programa, int idPrograma, int idPrestador, int tecnologiaTipo, int tecnologiaId);
    
    ValidaRespuestaDTO validarPosfechado(int afiliadoId, int tecnologiaId);
    
    String validarCargaPosfechado(int afiliadoId, int tecnologiaId);
}
