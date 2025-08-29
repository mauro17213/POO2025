package com.saviasaludeps.savia.ejb.validaciones;

import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaA4AutomaticoDTO;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaCopagoDTO;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaDTO;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaGrupoAsignado;
import com.saviasaludeps.savia.solicitud.negocio.ValidadorRemoto;
import java.sql.SQLException;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(ValidadorRemoto.class)
public class ValidadorServicio extends FuncionMySqlServicio implements ValidadorRemoto {

    private final static String FN_AU_ORIGEN_ATENCION = "fn_au_origenatencion";
    private final static String FN_AU_TIPOS_SERVICIO = "fn_au_tiposervicio";
    private final static String FN_AU_UBICACION_PACIENTE = "fn_au_ubicacionpaciente";
    private final static String FN_AU_PRIORITARIO = "fn_au_prioritario";
    private final static String FN_AU_TIPO_DIAGNOSTICO = "fn_au_tipodiagnostico";
    private final static String FN_AU_DIAGNOSTICO_PRINCIPAL = "fn_au_diagnosticop";
    private final static String FN_AU_PRESTADOR_SEDE = "fn_au_prestador_sede";
    private final static String FN_AU_TIPO_FRECUENCIA = "fn_au_tipofrecuencia";
    private final static String FN_AU_VIA_ADMINISTRACION = "fn_au_viaadministracion";
    private final static String FN_AU_VALOR_NUMERICO = "fn_au_valornumerico";
    private final static String FN_AU_FECHA_ACTUAL = "fn_au_fecha_actual";
    private final static String FN_AU_SERVICIO_HABILITADO = "fn_au_serviciohabilitado";
    private final static String FN_AU_PROFESIONAL = "fn_au_profesional";
    private final static String FN_AU_CODIGO_DIAGNOSTICO = "fn_au_codigo_diagnostico";
    private final static String FN_AU_TIPO_DOCUMENTO = "fn_au_tipo_documento";
    private final static String FN_AU_CODIGO_TECNOLOGIA = "fn_au_cod_tecnologia";
    private final static String FN_AU_AMBITO = "fn_au_ambito";
    private final static String FN_GN_ACT_PROFESIONAL = "fn_gn_act_profesional";
    private final static String FN_GN_CAL_COPAGOMODERADORA = "fn_gn_cal_copagomoderadora";
    private final static String FN_GN_VAL_CONTRATO = "fn_gn_val_contrato";
    private final static String FN_AU_CANTIDAD = "fn_au_cantidad";
    private final static String FN_AU_AUTORIZACION_AUTOMATICA = "fn_au_anexo4_automatico";
    private final static String FN_AU_ASIGNACION_GRUPO = "fn_au_asignar_grupo";
    private final static String FN_AU_VAL_POSFECHADO = "fn_au_validar_posfechado";

    private final static String VALIDAR_AMBITO = "Ambito";
    private final static String VALIDAR_AU_CANTIDAD = "Cantidad de autorizaciones";
    private final static String VALIDAR_CANTIDAD_TECNOLOGIA = "Cantidad de tecnologias";
    private final static String VALIDAR_CODIGO_TECNOLOGIA = "Tecnología";
    private final static String VALIDAR_DIAGNOSTICO = "Diagnostico";
    private final static String VALIDAR_DIAGNOSTICO_PRINCIPAL = "Diagnostico principal";
    private final static String VALIDAR_DOSIS = "Dosis";
    private final static String VALIDAR_DURACION_TRATAMIENTO = "Duración tratamiento";
    private final static String VALIDAR_FECHA_ORDEN_MEDICA = "Fecha orden medica";
    private final static String VALIDAR_FRECUENCIA = "Frecuencia";
    private final static String VALIDAR_ORIGEN_ATENCION = "Origen atención";
    private final static String VALIDAR_PRESTADOR_SEDE = "Sede prestador";
    private final static String VALIDAR_PRIORIDAD = "Prioridad";
    private final static String VALIDAR_PROFESIONAL = "Profesional";
    private final static String VALIDAR_PROFESIONAL_ESPECIALIDAD = "Profesional especialidad";
    private final static String VALIDAR_SERVICIO_HABILITADO = "Servicio habilitado";
    private final static String VALIDAR_TIPO_DIAGNOSTICO = "Tipo diagnostico";
    private final static String VALIDAR_TIPO_DOCUMENTO = "Tipo documento";
    private final static String VALIDAR_TIPO_FRECUENCIA = "Tipo frecuencia";
    private final static String VALIDAR_TIPO_SERVICIO = "Tipo servicio";
    private final static String VALIDAR_UBICACION = "Ubicación";
    private final static String VALIDAR_VAL_CONTRATO = "Valor contrato";
    private final static String VALIDAR_VIA_ADMINISTRACION = "Vía administración";

    @Override
    public ValidaRespuestaDTO validarOrigenAtencion(String codigoAtencion) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_ORIGEN_ATENCION + "('" + codigoAtencion + "')";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaDTO validarTipoServicio(String codigoTipoServicio) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_TIPOS_SERVICIO + "('" + codigoTipoServicio + "')";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaDTO validarUbicacion(String codigoUbicacion) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_UBICACION_PACIENTE + "('" + codigoUbicacion + "')";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaDTO validarPrioridad(String prioridadAtencion) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_PRIORITARIO + "('" + prioridadAtencion + "')";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaDTO validarTipoDiagnostico(String tipoDiagnostico) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_TIPO_DIAGNOSTICO + "('" + tipoDiagnostico + "')";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaDTO validarDiagnosticoPrincipal(String indicadorDiagnostico) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_DIAGNOSTICO_PRINCIPAL + "('" + indicadorDiagnostico + "')";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaDTO validarPrestadorSede(String codigoPrestador) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_PRESTADOR_SEDE + "('" + codigoPrestador + "')";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaDTO validarTipoFrecuencia(String tipoTecnologia, String tipoFrecuencia) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_TIPO_FRECUENCIA + "('" + tipoTecnologia + "','" + tipoFrecuencia + "')";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaDTO validarViaAdministracion(String tipoTecnologia, String tipoFrecuencia) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_VIA_ADMINISTRACION + "('" + tipoTecnologia + "','" + tipoFrecuencia + "')";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaDTO validarDosis(String tipoTecnologia, String campoAsociado) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_VALOR_NUMERICO + "('" + tipoTecnologia + "','" + campoAsociado + "')";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaDTO validarFrecuencia(String tipoTecnologia, String campoAsociado) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_VALOR_NUMERICO + "('" + tipoTecnologia + "','" + campoAsociado + "')";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaDTO validarDuracionTratamiento(String tipoTecnologia, String campoAsociado) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_VALOR_NUMERICO + "('" + tipoTecnologia + "','" + campoAsociado + "')";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaDTO validarCantidad(String tipoTecnologia, String campoAsociado) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_VALOR_NUMERICO + "('" + tipoTecnologia + "','" + campoAsociado + "')";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaDTO validarFechaOrdenMedica(String fecha) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_FECHA_ACTUAL + "('" + fecha + "')";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaDTO validarAmbito(String ambito) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_AMBITO + "('" + ambito + "')";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaDTO validarServicioHabilitado(String codigoServicioHabilitado) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_SERVICIO_HABILITADO + "('" + codigoServicioHabilitado + "')";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaDTO validarPosfechado(int afiliadoId, int tecnologiaId) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_VAL_POSFECHADO + "(" + afiliadoId + ", "+ tecnologiaId +")";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaDTO validarProfesional(String tipoProfesional, String documento, String codigoEspecialidad, String codigoPrestador) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_PROFESIONAL + "("
                    + "'" + tipoProfesional + "',"
                    + "'" + documento + "',"
                    + "'" + codigoEspecialidad + "',"
                    + "'" + codigoPrestador + "'"
                    + ")";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaDTO validarDiagnostico(String codigoDiagnostico, String tipoDocumento, String numeroDocumento) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_CODIGO_DIAGNOSTICO + "("
                    + "'" + codigoDiagnostico + "',"
                    + "'" + tipoDocumento + "',"
                    + "'" + numeroDocumento + "'"
                    + ")";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaDTO validarTipoDocumento(String tipoDocumento) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_TIPO_DOCUMENTO + "('" + tipoDocumento + "')";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaDTO validarCodigoTecnologia(String tipoTecnologia,
            String codigoTecnologia, String tipoDocumento, String numeroDocumento) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_CODIGO_TECNOLOGIA + "("
                    + "'" + tipoTecnologia + "',"
                    + "'" + codigoTecnologia + "',"
                    + "'" + tipoDocumento + "',"
                    + "'" + numeroDocumento + "'"
                    + ")";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaDTO validarProfesionalEspecialidad(String nit, String tipoProfesional,
            String numeroProfesional, String registroMedico, String primerNombre, String segundoNombre,
            String primerApellido, String segundoApellido, String idEspecialidad) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_GN_ACT_PROFESIONAL + "("
                    + "'" + nit + "',"
                    + "'" + tipoProfesional + "',"
                    + "'" + numeroProfesional + "',"
                    + "'" + registroMedico + "',"
                    + "'" + primerNombre + "',"
                    + "'" + segundoNombre + "',"
                    + "'" + primerApellido + "',"
                    + "'" + segundoApellido + "',"
                    + "'" + idEspecialidad + "'"
                    + ")";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaCopagoDTO validarCalCopagoModeradora(String idAfiliado, String tipoTecnologia, String idTecnologia,
            String valorTecnologia, String ambito, String idTutela, String idPrograma) {
        ValidaRespuestaCopagoDTO neg;
        try {
            String strQuery = "SELECT " + FN_GN_CAL_COPAGOMODERADORA + "("
                    + "'" + idAfiliado + "',"
                    + "'" + tipoTecnologia + "',"
                    + "'" + idTecnologia + "',"
                    + "'" + valorTecnologia + "',"
                    + "'" + ambito + "',"
                    + "'" + idTutela + "',"
                    + "'" + idPrograma + "'"
                    + ")";
            neg = ejecutarFuncionCopago(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaCopagoDTO();
            neg.setCodigo(99);
        }
        return neg;
    }

    @Override
    public ValidaRespuestaDTO validarAuCantidadTecnologia(String tipoTecnologia, String cantidad, String codigoTecnologia) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_CANTIDAD + "("
                    + "'" + tipoTecnologia + "',"
                    + "'" + cantidad + "',"
                    + "'" + codigoTecnologia + "'"
                    + ")";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaGrupoAsignado validarGrupoUsuario(boolean tutela, String ambito, boolean programa, int idPrograma, int idPrestador,int tecnologiaTipo, int tecnologiaId) {
        ValidaRespuestaGrupoAsignado neg;
        try {
            String strQuery = "SELECT " + FN_AU_ASIGNACION_GRUPO + "("
                    + "" + tutela + ","
                    + "'" + ambito + "',"
                    + "" + programa + ","
                    + "" + idPrograma + ","
                    + "" + idPrestador + ","
                    + "" + tecnologiaTipo + ","
                    + "" + tecnologiaId + ")";
                    
            neg = ejecutarAsignacionGrupo(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaGrupoAsignado();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaDTO validarValContrato(String codigoHabilitacionSede, String codigoTecnologia, String idRegimen) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_GN_VAL_CONTRATO + "("
                    + "'" + codigoHabilitacionSede + "',"
                    + "'" + codigoTecnologia + "',"
                    + "'" + idRegimen + "'"
                    + ")";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public ValidaRespuestaA4AutomaticoDTO validarAprobacionAutomatica(int idAfiliodo, int tipoTecnologia, int idTecnologia, int idSede, int fuenteOrigen) {
        ValidaRespuestaA4AutomaticoDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_AUTORIZACION_AUTOMATICA + "("
                    + "" + idAfiliodo + ","
                    + "" + tipoTecnologia + ","
                    + "" + idTecnologia + ","
                    + "" + idSede + ","
                    + "" + fuenteOrigen + ""
                    + ")";
            neg = ejecutarAprobacionAutomatica(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaA4AutomaticoDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    @Override
    public String validarCargaOrigenAtencion(String codigoAtencion) {
        return FN_AU_ORIGEN_ATENCION + "('" + codigoAtencion + "') As '" + VALIDAR_ORIGEN_ATENCION + "', ";
    }

    @Override
    public String validarCargaTipoServicio(String codigoTipoServicio) {
        return FN_AU_TIPOS_SERVICIO + "('" + codigoTipoServicio + "') As '" + VALIDAR_TIPO_SERVICIO + "', ";
    }

    @Override
    public String validarCargaUbicacion(String codigoUbicacion) {
        return FN_AU_UBICACION_PACIENTE + "('" + codigoUbicacion + "') As '" + VALIDAR_UBICACION + "', ";
    }

    @Override
    public String validarCargaPrioridad(String prioridadAtencion) {
        return FN_AU_PRIORITARIO + "('" + prioridadAtencion + "') As '" + VALIDAR_PRIORIDAD + "', ";
    }

    @Override
    public String validarCargaTipoDiagnostico(String tipoDiagnostico) {
        return FN_AU_TIPO_DIAGNOSTICO + "('" + tipoDiagnostico + "') As '" + VALIDAR_TIPO_DIAGNOSTICO + "', ";
    }

    @Override
    public String validarCargaDiagnosticoPrincipal(String indicadorDiagnostico) {
        return FN_AU_DIAGNOSTICO_PRINCIPAL + "('" + indicadorDiagnostico + "') As '" + VALIDAR_DIAGNOSTICO_PRINCIPAL + "', ";
    }

    @Override
    public String validarCargaPrestadorSede(String codigoPrestador) {
        return FN_AU_PRESTADOR_SEDE + "('" + codigoPrestador + "') As '" + VALIDAR_PRESTADOR_SEDE + "', ";
    }

    @Override
    public String validarCargaTipoFrecuencia(String tipoTecnologia, String tipoFrecuencia) {
        return FN_AU_TIPO_FRECUENCIA + "('" + tipoTecnologia + "','" + tipoFrecuencia + "') As '" + VALIDAR_TIPO_FRECUENCIA + "', ";
    }

    @Override
    public String validarCargaViaAdministracion(String tipoTecnologia, String tipoFrecuencia) {
        return FN_AU_VIA_ADMINISTRACION + "('" + tipoTecnologia + "','" + tipoFrecuencia + "') As '" + VALIDAR_VIA_ADMINISTRACION + "', ";
    }

    @Override
    public String validarCargaDosis(String tipoTecnologia, String campoAsociado) {
        return FN_AU_VALOR_NUMERICO + "('" + tipoTecnologia + "','" + campoAsociado + "') As '" + VALIDAR_DOSIS + "', ";
    }

    @Override
    public String validarCargaFrecuencia(String tipoTecnologia, String campoAsociado) {
        return FN_AU_VALOR_NUMERICO + "('" + tipoTecnologia + "','" + campoAsociado + "') As '" + VALIDAR_FRECUENCIA + "', ";
    }

    @Override
    public String validarCargaDuracionTratamiento(String tipoTecnologia, String campoAsociado) {
        return FN_AU_VALOR_NUMERICO + "('" + tipoTecnologia + "','" + campoAsociado + "') As '" + VALIDAR_DURACION_TRATAMIENTO + "', ";
    }

    @Override
    public String validarCargaCantidad(String tipoTecnologia, String campoAsociado) {
        return FN_AU_VALOR_NUMERICO + "('" + tipoTecnologia + "','" + campoAsociado + "') As '" + VALIDAR_AU_CANTIDAD + "', ";
    }

    @Override
    public String validarCargaFechaOrdenMedica(String fecha) {
        return FN_AU_FECHA_ACTUAL + "('" + fecha + "') As '" + VALIDAR_FECHA_ORDEN_MEDICA + "', ";
    }

    @Override
    public String validarCargaAmbito(String ambito) {
        return FN_AU_AMBITO + "('" + ambito + "') As '" + VALIDAR_AMBITO + "', ";
    }

    @Override
    public String validarCargaServicioHabilitado(String codigoServicioHabilitado) {
        return FN_AU_SERVICIO_HABILITADO + "('" + codigoServicioHabilitado + "') As '" + VALIDAR_SERVICIO_HABILITADO + "', ";
    }

    @Override
    public String validarCargaProfesional(String tipoProfesional, String documento, String codigoEspecialidad, String codigoPrestador) {
        return FN_AU_PROFESIONAL + "("
                + "'" + tipoProfesional + "',"
                + "'" + documento + "',"
                + "'" + codigoEspecialidad + "',"
                + "'" + codigoPrestador + "'"
                + ") As '" + VALIDAR_PROFESIONAL + "', ";
    }

    @Override
    public String validarCargaDiagnostico(String codigoDiagnostico, String tipoDocumento, String numeroDocumento) {
        return FN_AU_CODIGO_DIAGNOSTICO + "("
                + "'" + codigoDiagnostico + "',"
                + "'" + tipoDocumento + "',"
                + "'" + numeroDocumento + "'"
                + ") As '" + VALIDAR_DIAGNOSTICO + "', ";
    }

    @Override
    public String validarCargaTipoDocumento(String tipoDocumento) {
        return FN_AU_TIPO_DOCUMENTO + "('" + tipoDocumento + "') As '" + VALIDAR_TIPO_DOCUMENTO + "', ";
    }

    @Override
    public String validarCargaCodigoTecnologia(String tipoTecnologia,
            String codigoTecnologia, String tipoDocumento, String numeroDocumento) {
        return FN_AU_CODIGO_TECNOLOGIA + "("
                + "'" + tipoTecnologia + "',"
                + "'" + codigoTecnologia + "',"
                + "'" + tipoDocumento + "',"
                + "'" + numeroDocumento + "'"
                + ") As '" + VALIDAR_CODIGO_TECNOLOGIA + "', ";
    }

    @Override
    public String validarCargaProfesionalEspecialidad(String nit, String tipoProfesional,
            String numeroProfesional, String registroMedico, String primerNombre, String segundoNombre,
            String primerApellido, String segundoApellido, String idEspecialidad) {
        return FN_GN_ACT_PROFESIONAL + "("
                + "'" + nit + "',"
                + "'" + tipoProfesional + "',"
                + "'" + numeroProfesional + "',"
                + "'" + registroMedico + "',"
                + "'" + primerNombre + "',"
                + "'" + segundoNombre + "',"
                + "'" + primerApellido + "',"
                + "'" + segundoApellido + "',"
                + "'" + idEspecialidad + "'"
                + ") As '" + VALIDAR_PROFESIONAL_ESPECIALIDAD + "', ";
    }

    @Override
    public String validarCargaCalCopagoModeradora(String idAfiliado, String tipoTecnologia, String idTecnologia,
            String valorTecnologia, String ambito, String idTutela, String idPrograma) {
        return FN_GN_CAL_COPAGOMODERADORA + "("
                + "'" + idAfiliado + "',"
                + "'" + tipoTecnologia + "',"
                + "'" + idTecnologia + "',"
                + "'" + valorTecnologia + "',"
                + "'" + ambito + "',"
                + "'" + idTutela + "',"
                + "'" + idPrograma + "'"
                + ") As " + FN_AU_TIPO_DIAGNOSTICO + ", ";
    }

    @Override
    public String validarCargaAuCantidadTecnologia(String tipoTecnologia, String cantidad, String codigoTecnologia) {
        return FN_AU_CANTIDAD + "("
                + "'" + tipoTecnologia + "',"
                + "'" + cantidad + "',"
                + "'" + codigoTecnologia + "'"
                + ") As '" + VALIDAR_CANTIDAD_TECNOLOGIA + "', ";
    }

    @Override
    public String validarCargaValContrato(String codigoHabilitacionSede, String codigoTecnologia, String idRegimen) {
        return FN_GN_VAL_CONTRATO + "("
                + "'" + codigoHabilitacionSede + "',"
                + "'" + codigoTecnologia + "',"
                + "'" + idRegimen + "'"
                + ") As '" + VALIDAR_VAL_CONTRATO + "', ";
    }

    @Override
    public String validarCargaPosfechado(int afiliadoId, int tecnologiaId) {
        ValidaRespuestaDTO validacion = validarPosfechado(afiliadoId, tecnologiaId);
        if (validacion.getCodigo() == 1) {
            return "La Tecnología Que Está Cargando Ya Se Encuentra Autorizada De Manera Posfechada";
        } else if (validacion.getCodigo() == 99) {
            return validacion.getMensaje();
        } else {
            return "N/A";
        }
    }

}
