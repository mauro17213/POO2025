/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.utilidades;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContacto;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegTraslado;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author rpalacios
 */
public class AfiliadoValidacion {

    private List<String> errores = new ArrayList();
    private List<String> valoresNoPermitidosTelefonoFijo = new ArrayList();

    private HashMap<Integer, Maestro> hashTiposDocumento;
    private HashMap<Integer, Maestro> hashEstadosAfiliacion;
    private HashMap<Integer, Maestro> hashOrigenAfiliado;
    private HashMap<Integer, Maestro> hashGrupoPoblacional;
    private HashMap<Integer, Maestro> hashCausaNovedad;

    private final Date fechaActual;
    
    public final static int TIPO_VALIDACION_APLICACION_WEB = 0;
    public final static int TIPO_VALIDACION_CARGA_MASIVA = 1;

    public AfiliadoValidacion() {
        valoresNoPermitidosTelefonoFijo = new ArrayList<>();
        valoresNoPermitidosTelefonoFijo.add("0000000000");
        valoresNoPermitidosTelefonoFijo.add("1111111111");
        valoresNoPermitidosTelefonoFijo.add("2222222222");
        valoresNoPermitidosTelefonoFijo.add("3333333333");
        valoresNoPermitidosTelefonoFijo.add("4444444444");
        valoresNoPermitidosTelefonoFijo.add("5555555555");
        valoresNoPermitidosTelefonoFijo.add("6666666666");
        valoresNoPermitidosTelefonoFijo.add("7777777777");
        valoresNoPermitidosTelefonoFijo.add("8888888888");
        valoresNoPermitidosTelefonoFijo.add("9999999999");
        fechaActual = new Date();
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
        return (AfiliadoRemoto) object;
    }

    public List<String> getErrores() {
        return errores;
    }
    
    public String getErroresStr() {
        String strError = "";
        for(String str : errores){
            String cambio = str.replace(',','|');
            strError += cambio + ". ";
        }
        return strError;
    }
    
    public boolean isError() {
        return (!this.errores.isEmpty());
    }    

    public void setErrores(List<String> errores) {
        this.errores = errores;
    }

    public void setHashTiposDocumento(HashMap<Integer, Maestro> hashTiposDocumento) {
        this.hashTiposDocumento = hashTiposDocumento;
    }

    public HashMap<Integer, Maestro> getHashEstadosAfiliacion() {
        return hashEstadosAfiliacion;
    }

    public void setHashEstadosAfiliacion(HashMap<Integer, Maestro> hashEstadosAfiliacion) {
        this.hashEstadosAfiliacion = hashEstadosAfiliacion;
    }

    public HashMap<Integer, Maestro> getHashOrigenAfiliado() {
        return hashOrigenAfiliado;
    }

    public void setHashOrigenAfiliado(HashMap<Integer, Maestro> hashOrigenAfiliado) {
        this.hashOrigenAfiliado = hashOrigenAfiliado;
    }

    public HashMap<Integer, Maestro> getHashGrupoPoblacional() {
        return hashGrupoPoblacional;
    }

    public void setHashGrupoPoblacional(HashMap<Integer, Maestro> hashGrupoPoblacional) {
        this.hashGrupoPoblacional = hashGrupoPoblacional;
    }

    public HashMap<Integer, Maestro> getHashCausaNovedad() {
        return hashCausaNovedad;
    }

    public void setHashCausaNovedad(HashMap<Integer, Maestro> hashCausaNovedad) {
        this.hashCausaNovedad = hashCausaNovedad;
    }

    public void validarCreacion(AsegAfiliado objeto, int tipoValidacion, char accionAdicional) {
        errores = new ArrayList();
        // SE INCLUYE VALIDACION AFILIADO EXISTENTE - CARGA MASIVA
        
        // SE INCLUYE VALIDACIÓN AFILIADO CABEZA FAMILIA EXISTENTE
        //addError(consultarAfiliadoCabezaFamilia(objeto));
        addError(validarEnvioSMS(objeto));
        addError(validarEnvioEmail(objeto));
        if (tipoValidacion == TIPO_VALIDACION_APLICACION_WEB) {
            addError(validarFechaAfiliacionEPS(objeto));
            addError(validarFechaExpedicionCedulaCiudadania(objeto));
            addError(validarFechaExpedicionCedulaCiudadFechaNac(objeto));
            //2020-08-21 jyperez INC 277 adicionar validacion EPS BDUA
            addError(validarRegistroBDUA(objeto, objeto.getAsegTraslado()));
            //2020-07-21 jyperez adición validaciones INC 306
            //2022-06-03 jyperez se elimina validación debido a que se eliminaron campos de la tabla de afiliados
            //addError(validarCantidadDigitosTelefonoFijo(objeto));
            //addError(validarCantidadDigitosTelefonoMovil(objeto));
            //2020-09-24 jyperez INC 312
            addError(consultarAfiliadoExistenteCrear(objeto));
            if (accionAdicional != Url.ACCION_ADICIONAL_5) {
                addError(validarAfiliadoExistenteDatosBasicos(objeto));
            }
            //2021-11-11 jyperez adicion nueva validación
            addError(validarDiscapacidadTemporal(objeto));
            //2023-01-19 jyperez nueva validación
            addError(validarFechasDiscapacidad(objeto));
            //2022-01-13 jyperez nueva validación grupo etnico
            addError(validarGrupoEtnicoIndigena(objeto));
            //2023-06-20 jyperez N43 lugar de nacimiento
            addError(validarPaisNacimientoColombia(objeto));
        }
        //2020-08-21 jyperez INC 277 adicionar validacion EPS BDUA
        if (tipoValidacion == TIPO_VALIDACION_CARGA_MASIVA) {
            addError(validarRegistroBDUACargaMasiva(objeto, objeto.getAsegTraslado()));
            //2020-09-24 jyperez INC 312
            addError(consultarAfiliadoExistenteCrearCargaMasiva(objeto));
            addError(validarAfiliadoExistenteDatosBasicosCrearCargaMasiva(objeto));
            //2023-06-20 jyperez N43 lugar de nacimiento
            addError(validarPaisNacimientoColombia(objeto));
        }
        //2024-05-30 jyperez se haabilita validación para toda creación
        addError(validarTipoDocumentoEdad(objeto));
        addError(validarNumeroDocumentoPermiteLetras(objeto));
        addError(validarDireccion(objeto));
        addError(validarEmailCorrecto(objeto));
        addError(validarMunicipioAfiliacion(objeto));
        addError(validarTipoAfiliadoBeneficiario(objeto));
        addError(validarOrigenAfiliadoNacimiento(objeto));
        addError(validarOrigenAfiliadoFechaNacimiento(objeto));
        addError(validarIngresoCelularTelefono(objeto));
        addError(validarFechaAfiliacionFechaIngreso(objeto));
        addError(validarFechaNacimientoNoMayorAnio(objeto));
        // 2021-05-04 jyperez INC 797 comentamos la validación de puntaje sisben
        //addError(validarPuntajeSisben(objeto));
        addError(validarGrupoPoblacional(objeto));
        //2020-07-21 jyperez adición nuevas validaciones solicitadas en el mantis 253
        //2022-06-03 jyperez se comentan validaciones ya que eliminan los campos
        //addError(validarTelefonosFijosNoPermitidos(objeto));
        //addError(validarRangoTelefonoMovil(objeto));
        //2022-09-28 se adiciona validación de cabeza de familia
        addError(validarAfiliadoCabezaFamilia(objeto));
        //2023-01-13 jyperez se adiciona validacion de pais nacimiento para los Venezolanos
        addError(validarTipoDocumentoPaisNacimientoVenezuela(objeto));
        //2022-01-13 jyperez se adiciona validacion de pais nacionalidad para los Venezolanos
        addError(validarTipoDocumentoPaisNacionalidadVenezuela(objeto));
    }

    public void validarEdicion(AsegAfiliado objeto, AsegAfiliado objetoAnterior, int tipoValidacion, char accionAdicional) {
        errores = new ArrayList();
        
        if (tipoValidacion == TIPO_VALIDACION_APLICACION_WEB) {
            addError(consultarAfiliadoExistenteEditar(objeto,objetoAnterior));
            if (accionAdicional != Url.ACCION_ADICIONAL_5) {
                addError(validarAfiliadoExistenteDatosBasicosEditar(objeto, objetoAnterior));
            }
            addError(validarFechaExpedicionCedulaCiudadania(objeto));
            addError(validarFechaExpedicionCedulaCiudadFechaNac(objeto));
            addError(validarIngresoCelularTelefono(objeto));
            addError(validarDireccion(objeto));
            addError(validarEnvioSMS(objeto));
            // 2021-05-04 jyperez INC 797 comentamos la validación de puntaje sisben
            //addError(validarPuntajeSisben(objeto));
            //2020-07-14 jyperez se adiciona validación relacionada a los campos BDUA
            addError(validarRegistroBDUA(objeto, objeto.getAsegTraslado()));
            //2020-08-24 jyperez INC 278 : validación de estados Retirado a Afiliado y fecha de afiliación de EPS en el mes actual
            addError(validarAfliadoRetiradoFechaAfiliacion(objeto,objetoAnterior));
            //2020-07-21 jyperez adición validaciones INC 306
            //2022-06-03 jyperez se elimina validación debido a que se eliminaron campos de la tabla de afiliados
            //addError(validarCantidadDigitosTelefonoFijo(objeto));
            //addError(validarCantidadDigitosTelefonoMovil(objeto));
            //2021-09-27jyperez REQ 140 adición validación cambio de estado
            addError(validarRegimenCambioEstado(objeto, objetoAnterior));
            //2022-01-21 jyperez se comenta la validación por solicitud en INC 1140
            //2021-11-11 jyperez se adiciona validación para el panel Movilidad
            //addError(validarMovilidadRegimenContributivo(objeto));
            //2022-01-13 jyperez nueva validación grupo etnico
            addError(validarGrupoEtnicoIndigena(objeto));
            //2022-12-22 jyperez se adiciona validacion de pais nacimiento para los Venezolanos
            addError(validarTipoDocumentoPaisNacimientoVenezuela(objeto));
            //2022-12-22 jyperez se adiciona validacion de pais nacionalidad para los Venezolanos
            addError(validarTipoDocumentoPaisNacionalidadVenezuela(objeto));
            //2023-06-20 jyperez N43 lugar de nacimiento
            addError(validarPaisNacimientoColombia(objeto));
            //2024-01-10 jyperez IS-58 retiro por duplicado
            addError(validarEstadoDuplicado(objeto));
        }
        if (tipoValidacion == TIPO_VALIDACION_CARGA_MASIVA) {
            addError(consultarAfiliadoExistenteEditarCargaMasiva(objeto,objetoAnterior));
            addError(validarAfiliadoExistenteDatosBasicosEditarCargaMasiva(objeto, objetoAnterior));
            //2020-08-05 jyperez INC 263 No se debe permitir la reactivación de un usuario que tiene otro registro Activo.
            addError(validarAfiliadoActivoExistenteDatosBasicos(objeto,objetoAnterior));
        }
        //2024-05-30 jyperez se haabilita validación para toda edición
        addError(validarTipoDocumentoEdad(objeto));
        addError(validarNumeroDocumentoPermiteLetras(objeto));
        //addError(consultarAfiliadoCabezaFamilia(objeto));
        addError(validarEnvioEmail(objeto));
        addError(validarOrigenAfiliadoCausaNovedad(objeto));
        //2022-06-22 jyperez Inc 1256 se comenta la validación teniendo en cuenta que el estado Suspendido aplicará a ambos régimenes.
        //addError(validarRegimenEstadoAfiliacion(objeto));
        addError(validarCategoriaIBC(objeto));
        addError(validarCambioRegimenAfiliado(objeto, objetoAnterior));
        addError(validarCambioEstadoAfiliado(objeto, objetoAnterior,accionAdicional));
        addError(validarDiscapacidadTemporal(objeto));
        //2023-01-19 jyperez nueva validación
        addError(validarFechasDiscapacidad(objeto));
        addError(validarEmailCorrecto(objeto));
        addError(validarMunicipioAfiliacion(objeto));
        addError(validarMunicipioResidencia(objeto));
        addError(validarCambioMunicipioAfiliado(objeto, objetoAnterior));
        addError(validarTipoAfiliadoBeneficiario(objeto));
        addError(validarOrigenAfiliadoNacimiento(objeto));
        addError(validarOrigenAfiliadoFechaNacimiento(objeto));
        addError(validarFechaAfiliacionFechaIngreso(objeto));
        /*addError(validarFechaNacimientoNoMayorAnio(objeto));*/
        addError(validarGrupoPoblacional(objeto));
        //2020-07-21 jyperez adición nuevas validaciones solicitadas en el mantis 253
        //2022-06-03 jyperez se comentan validaciones ya que eliminan los campos
        //addError(validarTelefonosFijosNoPermitidos(objeto));
        //addError(validarRangoTelefonoMovil(objeto));
        //2020-09-21 jyperez INC 303 - Validar que sólo se permita el cambio de municipio si el usuario es de regimen contributivo
        addError(validarCambioMunicipioAfiliacion(objeto));
        //2022-09-28 se adiciona validación de cabeza de familia
        addError(validarAfiliadoCabezaFamilia(objeto));
        //2024-03-13 jyperez N00-42 traslado preaprobado
        addError(validarTrasladoPreaprobado(objeto));
    }

    /**
     * Función para validar el tipo afiliado Beneficiario y su relación no los
     * otros datos necesarios
     *
     * @param objeto
     * @return
     */
    private String validarTipoAfiliadoBeneficiario(AsegAfiliado objeto) {
        String mensaje;
        if (objeto.getMaeTipoAfiliadoCodigo().equals(AfiliadoParametro.TIPO_AFILIADO_BENEFICIARIO)
                && (objeto.getMaeTipoDocumentoCf() != null && objeto.getMaeTipoDocumentoCf() != 0)
                && (objeto.getNumeroDocumentoCf() != null && !objeto.getNumeroDocumentoCf().trim().equals(""))) {
            mensaje = "";
        } else if (!objeto.getMaeTipoAfiliadoCodigo().equals(AfiliadoParametro.TIPO_AFILIADO_BENEFICIARIO)) {
            mensaje = "";
        } else {
            mensaje = "Si Tipo de Afiliado es Beneficiario, deben ingresarse los campos Tipo Documento CF y Número Documento CF";
        }
        return mensaje;
    }

    /**
     * Funcion para validar que cuando se seleccione Nacimiento, los valores de
     * Tipo Documento CF y Numero Documento CF tengan valores
     *
     * @param objeto
     * @return
     */
    private String validarOrigenAfiliadoNacimiento(AsegAfiliado objeto) {
        String mensaje;
        if (hashOrigenAfiliado.get(objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadoParametro.ORIGEN_AFILIADO_NACIMIENTO)
                && (objeto.getMaeTipoDocumentoCf() != null && objeto.getMaeTipoDocumentoCf() != 0)
                && (objeto.getNumeroDocumentoCf() != null && !objeto.getNumeroDocumentoCf().trim().equals(""))) {
            mensaje = "";
        } else if (!hashOrigenAfiliado.get(objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadoParametro.ORIGEN_AFILIADO_NACIMIENTO)) {
            mensaje = "";
        } else {
            mensaje = "Si Origen Afiliado es "
                    + hashOrigenAfiliado.get(objeto.getMaeOrigenAfiliado()).getNombre()
                    + ", deben ingresarse los campos Tipo Documento CF y Número Documento CF";
        }
        return mensaje;
    }

    /**
     * Funcion para validar que cuando se seleccione Nacimiento, los valores de
     * Fecha de Nacimiento, Fecha Afiliacion SFSSS y Fecha afiliación EPS sean
     * iguales
     *
     * @param objeto
     * @return
     */
    private String validarOrigenAfiliadoFechaNacimiento(AsegAfiliado objeto) {
        String mensaje;
        if (hashOrigenAfiliado.get(objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadoParametro.ORIGEN_AFILIADO_NACIMIENTO)
                && (objeto.getFechaNacimiento().compareTo(objeto.getFechaAfiliacionSgsss()) == 0)
                && (objeto.getFechaNacimiento().compareTo(objeto.getFechaAfiliacionEps()) == 0)) {
            mensaje = "";
        } else if (!hashOrigenAfiliado.get(objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadoParametro.ORIGEN_AFILIADO_NACIMIENTO)) {
            mensaje = "";
        } else {
            mensaje = "Si Origen Afiliado es "
                    + hashOrigenAfiliado.get(objeto.getMaeOrigenAfiliado()).getNombre()
                    + ", los campos Fecha Nacimiento, Fecha Afiliacion SGSSS y Fecha Afiliación EPS deben ser iguales";
        }
        return mensaje;
    }

    /**
     * Función para validar el puntaje del sisben teniendo en cuenta: la Zona-
     * Rural o Urbana y el nivel seleccionado Si el municipio es Medellín La
     * validación aplica para Población Sisbenizada
     *
     * @param objeto
     * @return
     */
    private String validarPuntajeSisben(AsegAfiliado objeto) {
        String mensaje = "";
        double puntajeMinimo = 0.0;
        double puntajeNivel1Medellin = 47.99;
        double puntajeNivel2MedellinMin = 48;
        double puntajeNivel2MedellinMax = 54.86;
        double puntajeNivel1Urbano = 44.79;
        double puntajeNivel2UrbanoMin = 44.80;
        double puntajeNivel2UrbanoMax = 51.57;
        double puntajeNivel1Rural = 32.98;
        double puntajeNivel2RuralMin = 32.99;
        double puntajeNivel2RuralMax = 37.80;
        // validar inicialmente si el grupo poblacional seleccionado es Población Sisbenizada
        // 2020-08-20 jpyerez incluimos que se valide que el valor de puntaje sisben
        if (hashGrupoPoblacional.get(objeto.getMaeGrupoPoblacional()).getValor().equals(AfiliadoParametro.GRUPO_POBLACIONAL_POBLACION_SISBENIZADA)
                && objeto.getPuntajeSisben() !=  null) {
            //validamos si el municipio de afiliacion es medellin
            if (objeto.getAfiliacionUbicacion().getId() == AfiliadoParametro.IDENTIFICADOR_MEDELLIN) {

                if (objeto.getMaeNivelSisbenCodigo().equals(AfiliadoParametro.NIVEL_1_SISBEN)
                        && (objeto.getPuntajeSisben() >= puntajeMinimo && objeto.getPuntajeSisben() <= puntajeNivel1Medellin)) {
                    mensaje = "";
                } else if (objeto.getMaeNivelSisbenCodigo().equals(AfiliadoParametro.NIVEL_1_SISBEN)) {
                    mensaje = "El puntaje del Sisben para Nivel 1 - Medellín debe estar entre " + puntajeMinimo + " y " + puntajeNivel1Medellin;
                } else if (objeto.getMaeNivelSisbenCodigo().equals(AfiliadoParametro.NIVEL_2_SISBEN)
                        && (objeto.getPuntajeSisben() >= puntajeNivel2MedellinMin && objeto.getPuntajeSisben() <= puntajeNivel2MedellinMax)) {
                    mensaje = "";
                } else if (objeto.getMaeNivelSisbenCodigo().equals(AfiliadoParametro.NIVEL_2_SISBEN)) {
                    mensaje = "El puntaje del Sisben para Nivel 2 - Medellín debe estar entre " + puntajeNivel2MedellinMin + " y " + puntajeNivel2MedellinMax;
                }
            } else {
                // sino es medelin, validamos la zona
                if (objeto.getZona().equals(AfiliadoParametro.ZONA_URBANA)) {
                    if (objeto.getMaeNivelSisbenCodigo().equals(AfiliadoParametro.NIVEL_1_SISBEN)
                            && (objeto.getPuntajeSisben() >= puntajeMinimo && objeto.getPuntajeSisben() <= puntajeNivel1Urbano)) {
                        mensaje = "";
                    } else if (objeto.getMaeNivelSisbenCodigo().equals(AfiliadoParametro.NIVEL_1_SISBEN)) {
                        mensaje = "El puntaje del Sisben para Nivel 1 - Otras Cabeceras debe estar entre " + puntajeMinimo + " y " + puntajeNivel1Urbano;
                    } else if (objeto.getMaeNivelSisbenCodigo().equals(AfiliadoParametro.NIVEL_2_SISBEN)
                            && (objeto.getPuntajeSisben() >= puntajeNivel2UrbanoMin && objeto.getPuntajeSisben() <= puntajeNivel2UrbanoMax)) {
                        mensaje = "";
                    } else if (objeto.getMaeNivelSisbenCodigo().equals(AfiliadoParametro.NIVEL_2_SISBEN)) {
                        mensaje = "El puntaje del Sisben para Nivel 2 - Otras Cabeceras debe estar entre " + puntajeNivel2UrbanoMin + " y " + puntajeNivel2UrbanoMax;
                    }
                } else if (objeto.getZona().equals(AfiliadoParametro.ZONA_RURAL)) {
                    if (objeto.getMaeNivelSisbenCodigo().equals(AfiliadoParametro.NIVEL_1_SISBEN)
                            && (objeto.getPuntajeSisben() >= puntajeMinimo && objeto.getPuntajeSisben() <= puntajeNivel1Rural)) {
                        mensaje = "";
                    } else if (objeto.getMaeNivelSisbenCodigo().equals(AfiliadoParametro.NIVEL_1_SISBEN)) {
                        mensaje = "El puntaje del Sisben para Nivel 1 - Rural debe estar entre " + puntajeMinimo + " y " + puntajeNivel1Rural;
                    } else if (objeto.getMaeNivelSisbenCodigo().equals(AfiliadoParametro.NIVEL_2_SISBEN)
                            && (objeto.getPuntajeSisben() >= puntajeNivel2RuralMin && objeto.getPuntajeSisben() <= puntajeNivel2RuralMax)) {
                        mensaje = "";
                    } else if (objeto.getMaeNivelSisbenCodigo().equals(AfiliadoParametro.NIVEL_2_SISBEN)) {
                        mensaje = "El puntaje del Sisben para Nivel 2 - Rural debe estar entre " + puntajeNivel2RuralMin + " y " + puntajeNivel2RuralMax;
                    }
                }
            }
        } else if (hashGrupoPoblacional.get(objeto.getMaeGrupoPoblacional()).getValor().equals(AfiliadoParametro.GRUPO_POBLACIONAL_POBLACION_SISBENIZADA)
                && ( objeto.getMaeNivelSisbenCodigo().equals(AfiliadoParametro.NIVEL_1_SISBEN) || objeto.getMaeNivelSisbenCodigo().equals(AfiliadoParametro.NIVEL_2_SISBEN))
                ) {
            mensaje = "El valor del puntaje del Sisben para Nivel " + objeto.getMaeNivelSisbenCodigo() + " es obligatorio";
        }
        return mensaje;
    }

    /**
     * Funcion para validar que cuando se seleccione Nacimiento, la fecha de
     * actual no sea mayor en un año que la fecha de nacimiento
     *
     * @param objeto
     * @return
     */
    private String validarFechaNacimientoNoMayorAnio(AsegAfiliado objeto) {
        String mensaje;
        if (hashOrigenAfiliado.get(objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadoParametro.ORIGEN_AFILIADO_NACIMIENTO)
                && fechaActual.compareTo(calcularFechaCumpleanos(1,0, objeto.getFechaNacimiento())) < 0) {
            mensaje = "";
        } else if (!hashOrigenAfiliado.get(objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadoParametro.ORIGEN_AFILIADO_NACIMIENTO)) {
            mensaje = "";
        } else {
            mensaje = "Si Origen Afiliado es "
                    + hashOrigenAfiliado.get(objeto.getMaeOrigenAfiliado()).getNombre()
                    + ", la fecha de nacimiento no debe superar en 1 año a la fecha actual";
        }
        return mensaje;
    }

    /**
     * Función para validar que se ingrese la Fecha de Expedición, si se
     * seleccionó Tipo de Documento "Cedula de Ciudadania"
     *
     * @param objeto
     * @return
     */
    private String validarFechaExpedicionCedulaCiudadania(AsegAfiliado objeto) {
        String mensaje;
        if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CEDULA_DE_CIUDADANIA)
                && objeto.getFechaExpedicionCedula() != null) {
            mensaje = "";
        } else if (!hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CEDULA_DE_CIUDADANIA)) {
            mensaje = "";
        } else {
            mensaje = "Si el Tipo Documento es "
                    + hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getNombre()
                    + " Fecha exp Documento es obligatorio";
        }
        return mensaje;
    }

    /**
     * Metodo para validar
     *
     * @param objeto
     * @param objetoTraslado
     * @return
     */
    private String validarRegistroBDUA(AsegAfiliado objeto, AsegTraslado objetoTraslado) {
        String mensaje;
        // 2020-08-21 jyperez INC 277 adiciona validacion EPS BDUA
        if (objeto.isRegistroBdua()
                && (objetoTraslado.getTipoDocumentoBdua() != null && !objetoTraslado.getTipoDocumentoBdua().trim().equals(""))
                && (objetoTraslado.getNumeroDocumentoBdua() != null && !objetoTraslado.getNumeroDocumentoBdua().trim().equals(""))
                && (objetoTraslado.getPrimerApellidoBdua() != null && !objetoTraslado.getPrimerApellidoBdua().trim().equals(""))
                && (objetoTraslado.getPrimerNombreBdua() != null && !objetoTraslado.getPrimerNombreBdua().trim().equals(""))
                && (objetoTraslado.getFechaNacimientoBdua() != null)
                && objetoTraslado.getMaeEpsOrigenId() != null) {
            mensaje = "";
        } else if (!objeto.isRegistroBdua()) {
            mensaje = "";
        } else {
            mensaje = "Si seleccionaste 'Si' en Registra en BDUA, los campos Tipo Documento,Número Documento,"
                    + "Primer Apellido, Primer Nombre, Fecha Nacimiento y EPS en esa sección deben tener valores";
        }

        return mensaje;

    }
    
    /**
     * Metodo para validar
     *
     * @param objeto
     * @param objetoTraslado
     * @return
     */
    private String validarRegistroBDUACargaMasiva(AsegAfiliado objeto, AsegTraslado objetoTraslado) {
        String mensaje;
        if (objeto.isRegistroBdua()
                && (objetoTraslado.getTipoDocumentoBdua() != null && !objetoTraslado.getTipoDocumentoBdua().trim().equals(""))
                && (objetoTraslado.getNumeroDocumentoBdua() != null && !objetoTraslado.getNumeroDocumentoBdua().trim().equals(""))
                && (objetoTraslado.getPrimerApellidoBdua() != null && !objetoTraslado.getPrimerApellidoBdua().trim().equals(""))
                && (objetoTraslado.getPrimerNombreBdua() != null && !objetoTraslado.getPrimerNombreBdua().trim().equals(""))
                && (objetoTraslado.getFechaNacimientoBdua() != null)) {
            mensaje = "";
        } else if (!objeto.isRegistroBdua()) {
            mensaje = "";
        } else {
            mensaje = "Si seleccionaste 'Si' en Registra en BDUA, los campos Tipo Documento,Número Documento,"
                    + "Primer Apellido, Primer Nombre y Fecha Nacimiento es esa sección deben tener valores";
        }

        return mensaje;

    }

    /**
     * Función para validar que se ingrese por lo menos, uno de los siguientes
     * campos: Telefono o Celular
     *
     * @param objeto
     * @return
     */
    private String validarIngresoCelularTelefono(AsegAfiliado objeto) {
        String mensaje = "";
        if (objeto.getListaAsegAfiliadoContacto() != null && !objeto.getListaAsegAfiliadoContacto().isEmpty()) {
            mensaje = "";
        } else {
            mensaje = "Se debe ingresar al menos un contacto.";
        }
        return mensaje;
    }

    /**
     * Se debe validar que la fecha de afiliación de la EPS sea mayor o igual
     * que la fecha de afiliacion al SGSSS
     *
     * @param objeto
     * @return
     */
    private String validarFechaAfiliacionFechaIngreso(AsegAfiliado objeto) {
        String mensaje = "";
        if (objeto.getFechaAfiliacionEps().compareTo(objeto.getFechaAfiliacionSgsss()) < 0) {
            mensaje = "La Fecha Afiliación EPS debe ser mayor o igual a la Fecha Afiliación SGSSS";
        }
        return mensaje;
    }

    /**
     * Se debe validar que cuando se escoja el valor de grupo poblacional
     * diferente a población sisbenizada, el valor del nivel de sisben sea No
     * aplica
     *
     * @param objeto
     * @return
     */
    private String validarGrupoPoblacional(AsegAfiliado objeto) {
        String mensaje = "";
        //2020-08-20 solo se aplicará validación de grupo poblacional cuando no sea grupo poblacional 31
        //2022-07-21 jyperez adicionamos a la validación el grupo población población no pobre no vulnerable. Aplicará las mismas reglas que población sisbenizada
        if (!hashGrupoPoblacional.get(objeto.getMaeGrupoPoblacional()).getValor().equals(AfiliadoParametro.GRUPO_POBLACIONAL_POBLACION_31)) {
            if (!hashGrupoPoblacional.get(objeto.getMaeGrupoPoblacional()).getValor().equals(AfiliadoParametro.GRUPO_POBLACIONAL_POBLACION_SISBENIZADA) &&
                    !hashGrupoPoblacional.get(objeto.getMaeGrupoPoblacional()).getValor().equals(AfiliadoParametro.GRUPO_POBLACIONAL_POBLACION_NO_POBRE_NO_VULNERABLE)
                    && objeto.getMaeNivelSisbenCodigo().equals("N")) {
                mensaje = "";
            } else if (!hashGrupoPoblacional.get(objeto.getMaeGrupoPoblacional()).getValor().equals(AfiliadoParametro.GRUPO_POBLACIONAL_POBLACION_SISBENIZADA) &&
                    !hashGrupoPoblacional.get(objeto.getMaeGrupoPoblacional()).getValor().equals(AfiliadoParametro.GRUPO_POBLACIONAL_POBLACION_NO_POBRE_NO_VULNERABLE)) {
                mensaje = "El valor del Nivel de Sisben debe ser No Aplica si el Grupo Poblacional es " + hashGrupoPoblacional.get(objeto.getMaeGrupoPoblacional()).getNombre();
            } else {
                mensaje = "";
            }
        }
        return mensaje;
    }

    /**
     * Función para validar que si se ingresó la Fecha de Expedición y si se
     * seleccionó Tipo de Documento "Cedula de Ciudadania", la fecha de
     * expedición no sea inferior a la fecha del cumpleaños numero 18 del
     * usuario
     *
     * @param objeto
     * @return
     */
    private String validarFechaExpedicionCedulaCiudadFechaNac(AsegAfiliado objeto) {
        String mensaje;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CEDULA_DE_CIUDADANIA)
                && objeto.getFechaExpedicionCedula() != null
                && ( ((objeto.getFechaExpedicionCedula()).compareTo(calcularFechaCumpleanos(18, 0, objeto.getFechaNacimiento())) >= 0) 
                || sdf.format(objeto.getFechaExpedicionCedula()).equals(sdf.format(calcularFechaCumpleanos(18, 0, objeto.getFechaNacimiento()))))) {
            mensaje = "";
        } else if (!hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CEDULA_DE_CIUDADANIA)) {
            mensaje = "";
        } else {
            mensaje = "Si el Tipo Documento es "
                    + hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getNombre()
                    + "la Fecha exp Documento debe ser mayor o igual a la fecha del cumpleaños 18 : " + calcularFechaCumpleanos(18, 0, objeto.getFechaNacimiento()).toString();
        }
        return mensaje;
    }

    /**
     * Se debe validar que el tipo Documento coincida en Longitud y Edad a los
     * valores predefinidos para ello
     *
     * @param objeto
     * @return
     */
    private String validarTipoDocumentoEdad(AsegAfiliado objeto) {
        String mensaje = "";
        if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_REGISTRO_CIVIL)
                && (objeto.getNumeroDocumento().length() == 10 || objeto.getNumeroDocumento().length() == 11)
                && (calcularEdad(objeto.getFechaNacimiento()) < 7 || validarProrroga(objeto.getFechaNacimiento())) ) {
            mensaje = "";
        } else if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_REGISTRO_CIVIL)) {
            mensaje = "No se cumplen las  condiciones para Tipo Documento "
                    + hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getNombre()
                    + ": Numero Documento debe estar entre 10 y 11 dígitos, Edad del Afiliado debe ser menor a 7 años";
        }
        //2022-07-26 jyperez se amplia el campo certificado de nacido vivo para que permita de 1 a 14 dígitos.
        if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CERTIFICADO_NACIDO_VIVO)
                && (objeto.getNumeroDocumento().length() >= 1 && objeto.getNumeroDocumento().length() <= 14)
                && (calcularEdad(objeto.getFechaNacimiento()) < 7 || validarProrroga(objeto.getFechaNacimiento()))) {
            mensaje = "";
        } else if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CERTIFICADO_NACIDO_VIVO)) {
            mensaje = "No se cumplen las  condiciones para Tipo Documento "
                    + hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getNombre()
                    + ": Numero Documento debe estar entre 1 y 14 dígitos, Edad del Afiliado debe ser menor a 7 años";
        }
        if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_TARJETA_IDENTIDAD)
                && (objeto.getNumeroDocumento().length() == 10 || objeto.getNumeroDocumento().length() == 11)
                && ((calcularEdad(objeto.getFechaNacimiento()) >= 7 && calcularEdad(objeto.getFechaNacimiento()) < 18) ||
                validarProrroga(objeto.getFechaNacimiento()))) {
            mensaje = "";
        } else if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_TARJETA_IDENTIDAD)) {
            mensaje = "No se cumplen las  condiciones para Tipo Documento "
                    + hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getNombre()
                    + ": Numero Documento debe estar entre 10 y 11 dígitos, Edad del Afiliado debe estar entre 7 y 17 años";
        }
        if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CEDULA_DE_CIUDADANIA)
                && (objeto.getNumeroDocumento().length() >= 3 && objeto.getNumeroDocumento().length() <= 10)
                && (objeto.getNumeroDocumento().length() != 9)
                && (calcularEdad(objeto.getFechaNacimiento()) >= 18 || validarProrroga(objeto.getFechaNacimiento()))) {
            mensaje = "";
        } else if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CEDULA_DE_CIUDADANIA)) {
            mensaje = "No se cumplen las  condiciones para Tipo Documento "
                    + hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getNombre()
                    + ": Numero Documento debe estar entre 3 y 10 dígitos excluyendo 9 dígitos, Edad del Afiliado debe ser mayor o igual a 18 años";
        }
        if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_PERMISO_ESPECIAL_PERMANENCIA)
                && (objeto.getNumeroDocumento().length() == 15)) {
            mensaje = "";
        } else if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_PERMISO_ESPECIAL_PERMANENCIA)) {
            mensaje = "No se cumplen las  condiciones para Tipo Documento "
                    + hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getNombre()
                    + ": Numero Documento debe ser de 15 dígitos";
        }
        if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CEDULA_EXTRANGERIA)
                && (objeto.getNumeroDocumento().length() >= 3 && objeto.getNumeroDocumento().length() <= 7)) {
            mensaje = "";
        } else if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CEDULA_EXTRANGERIA)) {
            mensaje = "No se cumplen las  condiciones para Tipo Documento "
                    + hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getNombre()
                    + ": Numero Documento debe estar entre 3 y 7 dígitos";
        }
        if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_PASAPORTE)
                && (objeto.getNumeroDocumento().length() >= 3 && objeto.getNumeroDocumento().length() <= 16)
                && (calcularEdad(objeto.getFechaNacimiento()) <= 7)) {
            mensaje = "";
        } else if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_PASAPORTE)) {
            mensaje = "No se cumplen las  condiciones para Tipo Documento "
                    + hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getNombre()
                    + ": Numero Documento debe estar entre 3 y 16 dígitos, , Edad del Afiliado debe ser menor o igual a 7 años";
        }
        if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_MENOR_SIN_IDENTIFICACION)
                && (objeto.getNumeroDocumento().length() == 10 || objeto.getNumeroDocumento().length() == 12)) {
            mensaje = "";
        } else if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_MENOR_SIN_IDENTIFICACION)) {
            mensaje = "No se cumplen las  condiciones para Tipo Documento "
                    + hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getNombre()
                    + ": Numero Documento debe ser de 10 o 12 dígitos";
        }
        if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_SALVOCONDUCTO)
                && (objeto.getNumeroDocumento().length() >= 1 && objeto.getNumeroDocumento().length() <= 8)) {
            mensaje = "";
        } else if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_SALVOCONDUCTO)) {
            mensaje = "No se cumplen las  condiciones para Tipo Documento "
                    + hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getNombre()
                    + ": Numero Documento debe estar entre 1 y 8 dígitos";
        }
        if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_ADULTO_SIN_IDENTIFICACION)
                && (objeto.getNumeroDocumento().length() == 6 || objeto.getNumeroDocumento().length() == 10 || objeto.getNumeroDocumento().length() == 12)) {
            mensaje = "";
        } else if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_ADULTO_SIN_IDENTIFICACION)) {
            mensaje = "No se cumplen las  condiciones para Tipo Documento "
                    + hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getNombre()
                    + ": Numero Documento debe ser de 6, 10 o 12 dígitos";
        }
        if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CARNE_DIPLOMATICO)
                && (objeto.getNumeroDocumento().length() >= 3 && objeto.getNumeroDocumento().length() <= 11)) {
            mensaje = "";
        } else if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CARNE_DIPLOMATICO)) {
            mensaje = "No se cumplen las  condiciones para Tipo Documento "
                    + hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getNombre()
                    + ": Numero Documento debe estar entre 3 y 11 dígitos";
        }
        //2021-09-27 jyperez REQ 143 
        //2022-03-25 jyperez REQ Ajuste Longitud Documento PT
        if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_PERMISO_PROTECCION_TEMPORAL)
                && (objeto.getNumeroDocumento().length() >= 1 && objeto.getNumeroDocumento().length() <= 8)) {
            mensaje = "";
        } else if (hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_PERMISO_PROTECCION_TEMPORAL)) {
            mensaje = "No se cumplen las  condiciones para Tipo Documento "
                    + hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getNombre()
                    + ": Numero Documento debe estar entre 1 y 8 dígitos";
        }
        return mensaje;
    }
    
    /**
     * Se debe validar que el numero de documento permite letras, en los casos de 
     * los tipos de Documento Adulto sin Identificación (AS) y Menor Sin identificación (MS)
     * @return 
     */
    private String validarNumeroDocumentoPermiteLetras(AsegAfiliado objeto) {
        String mensaje = "";
        
        if ( !(hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_ADULTO_SIN_IDENTIFICACION) ||
                hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_REGISTRO_CIVIL) ||
                hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CEDULA_EXTRANGERIA) ||
                hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_PASAPORTE) ||
                hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CARNE_DIPLOMATICO) ||
                hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_MENOR_SIN_IDENTIFICACION) )
            && objeto.getNumeroDocumento().matches("\\d*")
                ) {
            mensaje = "";
        } else if ( !(hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_ADULTO_SIN_IDENTIFICACION) ||
                hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_REGISTRO_CIVIL) ||
                hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CEDULA_EXTRANGERIA) ||
                hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_PASAPORTE) ||
                hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CARNE_DIPLOMATICO) ||
                hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_MENOR_SIN_IDENTIFICACION)) ) {
            mensaje = "El tipo de Documento " +
                    hashTiposDocumento.get(objeto.getMaeTipoDocumento()).getNombre()
                    + " no admite letras en el Número de Documento.";
        }
        return mensaje;
    }

    private String validarMunicipioAfiliacion(AsegAfiliado objeto) {
        String mensaje = "";
        if (objeto.getAfiliacionUbicacion() == null) {
            mensaje = "Municipio Afiliación: Este campo es obligatorio";
        }

        return mensaje;
    }

    private String validarMunicipioResidencia(AsegAfiliado objeto) {
        String mensaje = "";
        if (objeto.getResidenciaUbicacion() == null) {
            mensaje = "Municipio Residencia: Este campo es obligatorio";
        }

        return mensaje;
    }

    private String validarDireccion(AsegAfiliado objeto) {
        String mensaje;
        if (!(objeto.getDireccionResidencia() == null)
                && !objeto.getDireccionResidencia().trim().equals("")) {
            mensaje = "";
        } else {
            mensaje = "Dirección: Este campo es obligatorio";
        }

        return mensaje;
    }

    /**
     * Función que permite validar si la discapacidad de un afiliado es
     * temporal, para solicitar que se digiten los campos fecha de Inicio y
     * Fecha Fin de dicha discapacidad
     *
     * @param objeto
     * @return
     */
    private String validarDiscapacidadTemporal(AsegAfiliado objeto) {
        String mensaje;
        if (objeto.getMaeCondicionDiscapacidadCodigo() != null
                && !objeto.getMaeCondicionDiscapacidadCodigo().trim().equals("")
                && objeto.getMaeCondicionDiscapacidadCodigo().equals(AfiliadoParametro.CONDICION_DISCAPACIDAD_TEMPORAL)
                && objeto.getFechaIniciodiscapacidad() != null
                && objeto.getFechaFinDiscapacidad() != null) {
            mensaje = "";
        } else if (objeto.getMaeCondicionDiscapacidadCodigo() != null
                && !objeto.getMaeCondicionDiscapacidadCodigo().trim().equals("")
                && objeto.getMaeCondicionDiscapacidadCodigo().equals(AfiliadoParametro.CONDICION_DISCAPACIDAD_TEMPORAL)) {

            mensaje = "Si la condición de discapacidad es Temporal, debe digitarse una fecha de inicio y una fecha fin obligatoria";
        } else {
            mensaje = "";
        }

        return mensaje;
    }
    
    /**
     * Función para validar que la fecha de inicio de discapacidad sea menor o 
     * igual a la fecha fin de discapacidad
     * @param objeto
     * @return 
     */
    public String validarFechasDiscapacidad(AsegAfiliado objeto) {
        String mensaje = "";
        if (objeto.isDiscapacidad() && objeto.getFechaIniciodiscapacidad() != null && objeto.getFechaFinDiscapacidad() != null) {
            if (objeto.getFechaIniciodiscapacidad().compareTo(objeto.getFechaFinDiscapacidad()) > 0) {
                mensaje = "La fecha inicio discapacidad debe ser menor o igual a la fecha fin de discapacidad";
            }
        }
        return mensaje;
    }

    /**
     * Función para validar en caso de que se modifique el estado de un
     * afiliado, que se digiten valores de causa novedad y fecha novedad.
     *
     * @param objeto
     * @return
     */
    private String validarCambioEstadoAfiliado(AsegAfiliado objeto, AsegAfiliado objetoAnterior, char accionAdicional) {
        String mensaje = "";
        // 2020-08-13 jyperez Se incluye el campo AccionAdicional para validar el rol "Rol Analista"
        // se validará que si el cambio de estado es Retiro y la accion Adicional es la 5 (Rol Analista) se omitirá la validación de cambio de estado
        if (objeto.getMaeEstadoAfiliacion() != objetoAnterior.getMaeEstadoAfiliacion()
                && accionAdicional == Url.ACCION_ADICIONAL_5 
                && hashEstadosAfiliacion.get(objeto.getMaeEstadoAfiliacion()).getValor().equals(AfiliadoParametro.ESTADO_AFILIACION_RETIRADO)) {
            return mensaje;
        }
        
        if (objeto.getMaeEstadoAfiliacion() != objetoAnterior.getMaeEstadoAfiliacion()
                && objeto.getFechaNovedad() != null
                && objeto.getMaeCausaNovedad() != 0) {
            mensaje = "";
        } else if (objeto.getMaeEstadoAfiliacion() != objetoAnterior.getMaeEstadoAfiliacion()) {
            mensaje = "Cuando se modifica el estado del afiliado, debe seleccionarse una causa de Novedad y una fecha de Novedad obligatoria";
        } else {
            mensaje = "";
        }
        return mensaje;
    }

    /**
     * Función para validar en caso de que se modifique el estado de un
     * afiliado, que se digiten valores de causa novedad y fecha novedad.
     *
     * @param objeto
     * @return
     */
    private String validarCambioRegimenAfiliado(AsegAfiliado objeto, AsegAfiliado objetoAnterior) {
        String mensaje = "";
        if (objeto.getMaeRegimenCodigo() != null
                && !objeto.getMaeRegimenCodigo().trim().equals("")
                && !objeto.getMaeRegimenCodigo().equals(objetoAnterior.getMaeRegimenCodigo())
                && objeto.getFechaMovilidad() != null) {
            mensaje = "";
        } else if (objeto.getMaeRegimenCodigo() != null
                && !objeto.getMaeRegimenCodigo().trim().equals("")
                && !objeto.getMaeRegimenCodigo().equals(objetoAnterior.getMaeRegimenCodigo())) {
            mensaje = "Cuando se modifica el régimen del afiliado, la fecha de Movilidad es obligatoria";
        } else {
            mensaje = "";
        }
        return mensaje;
    }

    /**
     * Función para validar que la escritura de un email sea correcta.
     *
     * @param objeto
     * @return
     */
    private String validarEmailCorrecto(AsegAfiliado objeto) {
        String mensaje = "";
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(objeto.getEmail());
        if (objeto.getEmail() != null
                && !objeto.getEmail().trim().equals("")
                && mather.find()) {
            mensaje = "";

        } else if (objeto.getEmail() != null
                && !objeto.getEmail().trim().equals("")
                && !contieneSoloCaracteresEspacio(objeto.getEmail())) {
            mensaje = "Email: Debe ingresarse un email válido";
        }

        return mensaje;
    }

    /**
     * Función que valida cuando se selecciona el valor de régimen contributivo
     * se digiten los valores de Categoría IBC y fecha de Movilidad
     *
     * @param objeto
     * @return
     */
    private String validarCategoriaIBC(AsegAfiliado objeto) {
        String mensaje = "";
        if (objeto.getMaeRegimenCodigo().equals(AfiliadoParametro.REGIMEN_CONTRIBUTIVO)
                && objeto.getMaeCategoriaIbcId() != null
                && objeto.getFechaMovilidad() != null) {
            mensaje = "";
        } else if (objeto.getMaeRegimenCodigo().equals(AfiliadoParametro.REGIMEN_CONTRIBUTIVO)) {
            mensaje = "Si el régimen seleccionado es Contributivo, debe seleccionarse una categoría IBC y una fecha de Movilidad obligatoria";
        }
        return mensaje;
    }
    
    /**
     * Función que valida que los campos Actividad Económica, Tipo documento Aportante,
     * número de Documento aportante, ARL, AFP y CCF en la pantalla de Editar sean diligenciados
     * si el Régimen es Contributivo.
     * @param objeto
     * @return 
     */
    private String validarMovilidadRegimenContributivo(AsegAfiliado objeto) {
        String mensaje = "";
        if (objeto.getMaeRegimenCodigo().equals(AfiliadoParametro.REGIMEN_CONTRIBUTIVO)
                && objeto.getMaeActividadEconomica()!= null
                && objeto.getMaeTipoDocumentoAportante()!= null
                && (objeto.getNumeroDocumentoAportante() != null && !objeto.getNumeroDocumentoAportante().trim().equals(""))
                && objeto.getMaeArl()!= null
                && objeto.getMaeAfp()!= null
                && objeto.getMaeCajaCompensacion()!= null) {
            mensaje = "";
        } else if (objeto.getMaeRegimenCodigo().equals(AfiliadoParametro.REGIMEN_CONTRIBUTIVO)) {
            mensaje = "Si el régimen seleccionado es Contributivo, se deben diligenciar todos los campos en el panel Movilidad.";
        }
        return mensaje;
    }

    /**
     * Función que valida cuando se cambie el municipio de residencia del
     * afiliado, se realice tambien cambio en la ips a seleccionar.
     *
     * @param objeto
     * @return
     */
    private String validarCambioMunicipioAfiliado(AsegAfiliado objeto, AsegAfiliado objetoAnterior) {
        String mensaje = "";
        if (objeto.getResidenciaUbicacion() != null
                && objetoAnterior.getPrimariaPrestadorSede() != null
                && objetoAnterior.getResidenciaUbicacion().getId().compareTo(objeto.getResidenciaUbicacion().getId()) != 0 ) {
            mensaje = "";
        } else if (objeto.getResidenciaUbicacion() != null
                && objetoAnterior.getResidenciaUbicacion() != null
                && objetoAnterior.getResidenciaUbicacion().getId().compareTo(objeto.getResidenciaUbicacion().getId()) != 0) {
            mensaje = "Si se realiza cambio de Municipio de Residencia, debe seleccionarse una nueva IPS Primaria";
        }
        return mensaje;
    }

    /**
     * Función que valida en la creación de afiliado, que no se permita el
     * registro de fechas de afiliacion EPS diferentes a las del mes actual
     * exceptuando si el afiliado es registrado con origen Nacimiento de Padres
     * Afiliados.
     *
     * @param objeto
     * @return
     */
    private String validarFechaAfiliacionEPS(AsegAfiliado objeto) {
        String mensaje = "";
        if (!hashOrigenAfiliado.get(objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadoParametro.ORIGEN_AFILIADO_NACIMIENTO)
                && validarMesFecha(objeto.getFechaAfiliacionEps(), fechaActual) == 0) {
            mensaje = "";

        } else if (!hashOrigenAfiliado.get(objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadoParametro.ORIGEN_AFILIADO_NACIMIENTO)) {
            mensaje = "Se debe ingresar una fecha de Afiliacion EPS correspondiente al mes actual";
        }
        return mensaje;
    }

    /**
     * función que valida que cuando la causa sea 'INGRESO CAMBIO MPIO VIENE DE
     * OTRA EPSS' el origen debe ser 'Traslado de otra EPS'
     *
     * @param objeto
     * @return
     */
    private String validarOrigenAfiliadoCausaNovedad(AsegAfiliado objeto) {
        String mensaje = "";
        if (hashOrigenAfiliado.get(objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadoParametro.ORIGEN_AFILIADO_TRASLADO_OTRA_EPS)
                && objeto.getMaeCausaNovedad() != 0
                && hashCausaNovedad.get(objeto.getMaeCausaNovedad()) != null
                && hashCausaNovedad.get(objeto.getMaeCausaNovedad()).getNombre().equals("INGRESO CAMBIO MPIO VIENE DE OTRA EPSS")) {
            mensaje = "";
        } else if (objeto.getMaeCausaNovedad() != 0
                && hashCausaNovedad.get(objeto.getMaeCausaNovedad()) != null
                && hashCausaNovedad.get(objeto.getMaeCausaNovedad()).getNombre().equals("INGRESO CAMBIO MPIO VIENE DE OTRA EPSS")) {
            mensaje = "Se debe seleccionar el origen Traslado de otra EPS para la Causa Novedad seleccionada";
        }
        return mensaje;
    }

    /**
     * Función para validar que si se seleccionó Autoriza Envio SMS, se haya
     * ingresado un celular
     *
     * @param objeto
     * @return
     */
    private String validarEnvioSMS(AsegAfiliado objeto) {
        String mensaje = "";
        boolean validar = false;
        if (objeto.getAutorizaEnvioSms() && objeto.getListaAsegAfiliadoContacto() != null && !objeto.getListaAsegAfiliadoContacto().isEmpty()) {
            //2022-06-03 jyperez validamos que exista un contacto de telefóno movil ingresado
            for (AsegAfiliadoContacto contacto : objeto.getListaAsegAfiliadoContacto()) {
                if (contacto.getMaeTipoContactoCodigo().equals(AfiliadoParametro.TIPO_CONTACTO_CELULAR)) {
                    validar = true;
                }
            }
            if (!validar) {
                mensaje = "Si se selecciona Autorizo Envio SMS, debe ingresarse un contacto Celular";
            }
        } else if (objeto.getAutorizaEnvioSms()) {
            mensaje = "Si se selecciona Autorizo Envio SMS, debe ingresarse un contacto Celular";
        }
        return mensaje;
    }

    /**
     * Función para validar que si se seleccionó Autoriza Envio Email, se haya
     * ingresado un email
     *
     * @param objeto
     * @return
     */
    private String validarEnvioEmail(AsegAfiliado objeto) {
        String mensaje = "";
        if (objeto.getAutorizaEnvioEmail()
                && objeto.getEmail() != null
                && !objeto.getEmail().trim().equals("")) {
            mensaje = "";
        } else if (objeto.getAutorizaEnvioEmail()) {
            mensaje = "Si se selecciona Autorizo Envio Email, debe ingresarse un Email";
        }
        return mensaje;
    }

    /**
     * Función para validar que cuando se edita un registro en estado
     * ‘Suspendido (Contributivo)’ solo debe registrar cuando el usuario se
     * encuentra en régimen contributivo.
     *
     * @param objeto
     * @return
     */
    private String validarRegimenEstadoAfiliacion(AsegAfiliado objeto) {
        String mensaje = "";
        if (objeto.getMaeRegimenCodigo().equals(AfiliadoParametro.REGIMEN_CONTRIBUTIVO)
                && objeto.getMaeEstadoAfiliacion() != 0
                && hashEstadosAfiliacion.get(objeto.getMaeEstadoAfiliacion()).getValor().equals(AfiliadoParametro.ESTADO_AFILIACION_SUSPENDIDO)) {
            mensaje = "";
        } else if (objeto.getMaeEstadoAfiliacion() != 0
                && hashEstadosAfiliacion.get(objeto.getMaeEstadoAfiliacion()).getValor().equals(AfiliadoParametro.ESTADO_AFILIACION_SUSPENDIDO)) {
            mensaje = "Para el estado de afiliación " + hashEstadosAfiliacion.get(objeto.getMaeEstadoAfiliacion()).getNombre()
                    + " el Regimen del afiliado debe ser Contributivo";
        }
        return mensaje;
    }

    private boolean validarEstadoFallecido(int estado) {
        boolean habilitar = false;
        try {
            if (hashEstadosAfiliacion.get(estado).getValor().equals(AfiliadoParametro.ESTADO_AFILIACION_FALLECIDO)) {
                habilitar = true;
            }
        } catch (Exception ex) {
            habilitar = false;
        }
        return habilitar;
    }

    private String validarAfiliadoExistenteDatosBasicos(AsegAfiliado objeto) {
        String mensaje;
        AsegAfiliado afiliadoConsulta;
        try {
            //2020-07-24 jyperez Se ajusta la consulta para adicionar los campos segundoApellido y SegundoNombre, solicitados en el inc 260
            afiliadoConsulta = getAfiliadoRemoto().consultar(objeto.getPrimerApellido(),objeto.getSegundoApellido() , objeto.getPrimerNombre(), objeto.getSegundoNombre(), objeto.getFechaNacimiento());
            if (afiliadoConsulta != null
                    && afiliadoConsulta.getId() != null) {
                mensaje = "Existe un afiliado registrado con los datos ingresados: Primer Apellido, Segundo Apellido, Primer Nombre, Segundo Nombre, Fecha de Nacimiento. Póngase en contacto con el Analista de Aseguramiento para validar el ingreso";
            } else {
                mensaje = "";
            }
        } catch (Exception ex) {
            mensaje = "Ocurrió un error consultando afliado Existente. Mensaje: " + ex.getMessage();
        }
        return mensaje;
    }
    
    private String validarAfiliadoExistenteDatosBasicosCrearCargaMasiva(AsegAfiliado objeto) {
        String mensaje;
        AsegAfiliado afiliadoConsulta;
        try {
            //2020-07-24 jyperez Se ajusta la consulta para adicionar los campos segundoApellido y SegundoNombre, solicitados en el inc 260
            afiliadoConsulta = getAfiliadoRemoto().consultar(objeto.getPrimerApellido(),objeto.getSegundoApellido() , objeto.getPrimerNombre(),objeto.getSegundoNombre(), objeto.getFechaNacimiento());
            if (afiliadoConsulta != null
                    && afiliadoConsulta.getId() != null) {
                mensaje = "Existe un afiliado registrado con los datos ingresados: Primer Apellido| Segundo Apellido| Primer Nombre| Segundo Nombre| Fecha de Nacimiento con Contrato " + afiliadoConsulta.getIdAfiliado();
            } else {
                mensaje = "";
            }
        } catch (Exception ex) {
            mensaje = "Ocurrió un error consultando afliado Existente. Mensaje: " + ex.getMessage();
        }
        return mensaje;
    }
    
    public String consultarAfiliadoExistenteEditar(AsegAfiliado objeto, AsegAfiliado objetoAnterior) {
        String mensaje = "";
        AsegAfiliado afiliadoResultado = null;
        List<AsegAfiliado> listaAfiliados = null;
        try {
            if (!objeto.getNumeroDocumento().equals(objetoAnterior.getNumeroDocumento())) {
                //2022-04-27 jyperez consultamos por tipo de documento y numero de documento en general
                listaAfiliados = getAfiliadoRemoto().consultarPorTipoDocumentoYNumeroDocumento(objeto.getMaeTipoDocumento(),objeto.getNumeroDocumento());
                if (listaAfiliados != null && listaAfiliados.size() > 0) {
                    afiliadoResultado = listaAfiliados.get(0);
                }
                if (afiliadoResultado != null
                        && afiliadoResultado.getId() != null && afiliadoResultado.getId() != 0) {
                    mensaje = "Existe un afiliado registrado con el Tipo de Documento y Número Documento ingresados";
                } else {
                    mensaje = "";
                }
            //2022-04-27 jyperez validamos el cambio de tipo de documento en caso de que no se hizo cambio de número de documento pero si de tipo de documento
            } else if (objeto.getMaeTipoDocumento() != 0 && objeto.getMaeTipoDocumento() != objetoAnterior.getMaeTipoDocumento()) {
                    listaAfiliados = getAfiliadoRemoto().consultarPorTipoDocumentoYNumeroDocumento(objeto.getMaeTipoDocumento(),objeto.getNumeroDocumento());
                    if (listaAfiliados != null && listaAfiliados.size() > 0) {
                        afiliadoResultado = listaAfiliados.get(0);
                    }
                    if (afiliadoResultado != null
                            && afiliadoResultado.getId() != null && afiliadoResultado.getId() != 0) {
                        mensaje = "Existe un afiliado registrado con el Tipo de Documento y Número Documento ingresados";
                    } else {
                        mensaje = "";
                    }
            }
        } catch (Exception ex) {
            mensaje = "Ocurrió un error consultando afliado Existente. Mensaje: " + ex.getMessage();
        }
        return mensaje;
    }
    
    public String consultarAfiliadoExistenteCrear(AsegAfiliado objeto) {
        String mensaje;
        AsegAfiliado afiliadoResultado = null;
        List<AsegAfiliado> listaAfiliados = null;
        try {
                //2022-04-27 jyperez consultamos por tipo de documento y numero de documento en general
                listaAfiliados = getAfiliadoRemoto().consultarPorTipoDocumentoYNumeroDocumento(objeto.getMaeTipoDocumento(),objeto.getNumeroDocumento());
                if (listaAfiliados != null && listaAfiliados.size() > 0) {
                    afiliadoResultado = listaAfiliados.get(0);
                }
                if (afiliadoResultado != null
                        && afiliadoResultado.getId() != null && afiliadoResultado.getId() != 0) {
                    mensaje = "Existe un afiliado registrado con el Tipo de Documento y Número Documento ingresados";
                } else {
                    mensaje = "";
                }
        } catch (Exception ex) {
            mensaje = "Ocurrió un error consultando afliado Existente. Mensaje: " + ex.getMessage();
        }
        return mensaje;
    }
    
    public String consultarAfiliadoExistenteCrearCargaMasiva(AsegAfiliado objeto) {
        String mensaje;
        AsegAfiliado afiliadoResultado = null;
        List<AsegAfiliado> listaAfiliados = null;
        try {
                //2022-04-27 jyperez consultamos por tipo de documento y numero de documento en general
                listaAfiliados = getAfiliadoRemoto().consultarPorTipoDocumentoYNumeroDocumento(objeto.getMaeTipoDocumento(),objeto.getNumeroDocumento());
                if (listaAfiliados != null && listaAfiliados.size() > 0) {
                    afiliadoResultado = listaAfiliados.get(0);
                }
                if (afiliadoResultado != null
                        && afiliadoResultado.getId() != null && afiliadoResultado.getId() != 0) {
                    mensaje = "Existe un afiliado registrado con el Tipo de Documento y Número Documento ingresados con Contrato " + afiliadoResultado.getIdAfiliado();
                } else {
                    mensaje = "";
                }
        } catch (Exception ex) {
            mensaje = "Ocurrió un error consultando afliado Existente. Mensaje: " + ex.getMessage();
        }
        return mensaje;
    }

    private String validarAfiliadoExistenteDatosBasicosEditar(AsegAfiliado objeto, AsegAfiliado objetoAnterior) {
        String mensaje = "";
        AsegAfiliado afiliadoConsulta;
        String segundoApellido = "";
        String segundoApellidoAnterior = "";
        String segundoNombre = "";
        String segundoNombreAnterior = "";
        
        // 2020-08-25 jyperez INC 281 erro con valores nulos en los campos segundo nombre y segundo apellido
        if (objeto.getSegundoApellido() != null) {
            segundoApellido = objeto.getSegundoApellido();
        }
        if (objetoAnterior.getSegundoApellido() != null) {
            segundoApellidoAnterior = objetoAnterior.getSegundoApellido();
        }
        if (objeto.getSegundoNombre() != null) {
            segundoNombre = objeto.getSegundoNombre();
        }
        if (objetoAnterior.getSegundoNombre() != null) {
            segundoNombreAnterior = objetoAnterior.getSegundoNombre();
        }
        try {
            //validamos que sus datos básicos hayan cambiado para ejecutar la consulta
            //2020-07-24 jyperez Se ajusta la consulta para adicionar los campos segundoApellido y SegundoNombre, solicitados en el inc 260
            if (!objeto.getPrimerApellido().equals(objetoAnterior.getPrimerApellido())
                    || !segundoApellido.equals(segundoApellidoAnterior)
                    || !objeto.getPrimerNombre().equals(objetoAnterior.getPrimerNombre())
                    || !segundoNombre.equals(segundoNombreAnterior)
                    || !(objeto.getFechaNacimiento().compareTo(objetoAnterior.getFechaNacimiento()) == 0)) {
                    //2020-07-24 jyperez Se ajusta la consulta para adicionar los campos segundoApellido y SegundoNombre, solicitados en el inc 260
                    afiliadoConsulta = getAfiliadoRemoto().consultar(objeto.getPrimerApellido(),objeto.getSegundoApellido() , objeto.getPrimerNombre(), objeto.getSegundoNombre(), objeto.getFechaNacimiento());
                if (afiliadoConsulta != null
                        && afiliadoConsulta.getId() != null
                        && afiliadoConsulta.getId().compareTo(objeto.getId()) != 0) {
                    mensaje = "Existe un afiliado registrado con los datos ingresados: Primer Apellido, Segundo Apellido, Primer Nombre, Segundo Nombre, Fecha de Nacimiento. Póngase en contacto con el Analista de Aseguramiento para validar el ingreso";
                } else {
                    mensaje = "";
                }
            }
        } catch (Exception ex) {
            mensaje = "Ocurrió un error consultando afliado Existente por Datos Básicos. Mensaje: " + ex.getMessage();
        }
        return mensaje;
    }
    
    public String consultarAfiliadoCabezaFamilia(AsegAfiliado objeto) {
        AsegAfiliado afiliadoResultado;
        String mensaje;
        try {
            if (objeto.getTipoBeneficiario().equals(AfiliadoParametro.TIPO_AFILIADO_BENEFICIARIO)) {
                afiliadoResultado = getAfiliadoRemoto().consultar(objeto.getMaeTipoDocumentoCf(), objeto.getNumeroDocumentoCf());
                if (afiliadoResultado != null && afiliadoResultado.getId() != null && afiliadoResultado.getId() != 0
                        && hashEstadosAfiliacion.get(afiliadoResultado.getMaeEstadoAfiliacion()).contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO)) {
                    //se encontró un afiliado
                    mensaje = "";
                } else {
                    mensaje = "El afiliado registrado como Cabeza de Familia no se encuentra activo o registrado en el sistema";
                }
            } else {
                mensaje = "";
            }

        } catch (Exception ex) {
            mensaje = "Ocurrio un error consultando los datos del Afiliado Cabeza de Familia: " + ex.getMessage();
        }
        return mensaje;
    }

    /**
     * Calcular fecha en la que se cumplio el cumpleaños 18
     *
     * @param anio
     * @param fecha
     * @return
     */
    private static Date calcularFechaCumpleanos(int anio,int dias, Date fecha) {
        Date cumpleanos18;
        Calendar fechaNac = Calendar.getInstance();
        Calendar fechaCumpleanos18 = Calendar.getInstance();
        //Calendar fechaActual = Calendar.getInstance();

        // cargamos la fecha a evaluar
        fechaNac.setTime(fecha);
        //2022-09-13 jyperez adicionamos parámetro para sumarle unos días
        if (dias <= 0) {
            dias = 0;
        }
        // Cálculo de las diferencias.
        int years = fechaNac.get(Calendar.YEAR) + anio;
        int months = fechaNac.get(Calendar.MONTH);
        int days = fechaNac.get(Calendar.DAY_OF_MONTH) + dias;

        fechaCumpleanos18.set(years, months, days,0,0,0);
        cumpleanos18 = fechaCumpleanos18.getTime();
        return cumpleanos18;
    }

    private static int calcularEdad(Date fecha) {
        Calendar fechaNac = Calendar.getInstance();
        Calendar fechaCalActual = Calendar.getInstance();
        // cargamos la fecha a evaluar
        fechaNac.setTime(fecha);
        // Cálculo de las diferencias.
        int years = fechaCalActual.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
        int months = fechaCalActual.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
        int days = fechaCalActual.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);
        // Hay que comprobar si el día de su cumpleaños es posterior
        // a la fecha actual, para restar 1 a la diferencia de años,
        // pues aún no ha sido su cumpleaños.
        if (months < 0 // Aún no es el mes de su cumpleaños
                || (months == 0 && days < 0)) { // o es el mes pero no ha llegado el día.
            years--;
        }
        return years;
    }
    
    /**
     * Función para adicionar unos meses a una fecha actual
     * @param fecha
     * @param meses
     * @return 
     */
    private static Date calcularFechaProrroga(Date fecha,int anios, int meses, int dias) {
        Calendar fechaNac = Calendar.getInstance();
        Calendar fechaCalActual = Calendar.getInstance();
        // cargamos la fecha a evaluar
        fechaNac.setTime(fecha);
        // Cálculo de las diferencias.
        int years = fechaNac.get(Calendar.YEAR) + anios;
        int months = fechaNac.get(Calendar.MONTH) + meses;
        int days = fechaNac.get(Calendar.DAY_OF_MONTH) + dias;
        // Actualizamos los valores en la fecha actual
        fechaCalActual.set(years, months, days, 0 ,0 ,0);
        return fechaCalActual.getTime();
    }
    
    private static boolean validarProrroga(Date fecha) {
        boolean estado = false;
        // validamos que la fecha actual sea menor o igual a la fecha de prorroga
        Calendar fechaCalActual = Calendar.getInstance();
        Date fechaProrroga = calcularFechaProrroga(fecha, 18, 3, 0);
        fechaCalActual.add(Calendar.HOUR_OF_DAY, 0);
        fechaCalActual.add(Calendar.MINUTE, 0);
        fechaCalActual.add(Calendar.MILLISECOND, 0);
        Date fechaActual = calcularFechaProrroga(fechaCalActual.getTime(), 0,0,0);
        // verificamos que la fecha actual sea menor o igual a la fecha de prorroga
        if (fechaActual.compareTo(fechaProrroga) <= 0) {
            estado = true;
        }
        return estado;
    }

    /**
     * Función para validar si dos fechas se encuentran en el mismo mes y el
     * mismo año
     *
     * @param fecha1
     * @param fecha2
     * @return si el valor es 0, las fechas son iguales. Si es 1 las fechas no
     * cumplen
     */
    private static int validarMesFecha(Date fecha1, Date fecha2) {
        int resultado;
        Calendar fechaA = Calendar.getInstance();
        Calendar fechaB = Calendar.getInstance();

        // cargamos las fechas a evaluar
        fechaA.setTime(fecha1);
        fechaB.setTime(fecha2);

        if (fechaA.get(Calendar.YEAR) == fechaB.get(Calendar.YEAR)
                && fechaA.get(Calendar.MONTH) == fechaB.get(Calendar.MONTH)) {
            resultado = 0;
        } else {
            resultado = 1;
        }
        return resultado;
    }

    private void addError(String msj) {
        if (msj != null && !msj.trim().equals("")) {
            errores.add(msj);
        }
    }

    private String consultarAfiliadoExistenteEditarCargaMasiva(AsegAfiliado objeto, AsegAfiliado objetoAnterior) {
        String mensaje = "";
        AsegAfiliado afiliadoResultado = null;
        List<AsegAfiliado> listaAfiliados = null;
        
        try {
            if (!objeto.getNumeroDocumento().equals(objetoAnterior.getNumeroDocumento())) {
                //2022-04-27 jyperez consultamos por tipo de documento y numero de documento en general
                listaAfiliados = getAfiliadoRemoto().consultarPorTipoDocumentoYNumeroDocumento(objeto.getMaeTipoDocumento(),objeto.getNumeroDocumento());
                if (listaAfiliados != null && listaAfiliados.size() > 0) {
                    afiliadoResultado = listaAfiliados.get(0);
                }
                if (afiliadoResultado != null
                        && afiliadoResultado.getId() != null && afiliadoResultado.getId() != 0
                        && ( hashEstadosAfiliacion.get(afiliadoResultado.getMaeEstadoAfiliacion()).contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO) ||
                             hashEstadosAfiliacion.get(afiliadoResultado.getMaeEstadoAfiliacion()).getValor().equals(AfiliadoParametro.ESTADO_AFILIACION_PENDIENTE_SOPORTE) ||
                             hashEstadosAfiliacion.get(afiliadoResultado.getMaeEstadoAfiliacion()).contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_INACTIVO))
                    ) {
                    mensaje = "Existe un afiliado registrado con el Tipo de Documento y Número Documento ingresados";
                } else {
                    mensaje = "";
                }
            //2022-04-27 jyperez validamos el cambio de tipo de documento en caso de que no se hizo cambio de número de documento pero si de tipo de documento
            } else if (objeto.getMaeTipoDocumento() != 0 && objeto.getMaeTipoDocumento() != objetoAnterior.getMaeTipoDocumento()) {
                    listaAfiliados = getAfiliadoRemoto().consultarPorTipoDocumentoYNumeroDocumento(objeto.getMaeTipoDocumento(),objeto.getNumeroDocumento());
                    if (listaAfiliados != null && listaAfiliados.size() > 0) {
                        afiliadoResultado = listaAfiliados.get(0);
                    }
                    if (afiliadoResultado != null
                            && afiliadoResultado.getId() != null && afiliadoResultado.getId() != 0) {
                        mensaje = "Existe un afiliado registrado con el Tipo de Documento y Número Documento ingresados";
                    } else {
                        mensaje = "";
                    }
            }
        } catch (Exception ex) {
            mensaje = "Ocurrió un error consultando afliado Existente. Mensaje: " + ex.getMessage();
        }
        return mensaje;
    }

    private String validarAfiliadoExistenteDatosBasicosEditarCargaMasiva(AsegAfiliado objeto, AsegAfiliado objetoAnterior) {
        String mensaje = "";
        AsegAfiliado afiliadoConsulta;
        String segundoApellido = "";
        String segundoApellidoAnterior = "";
        String segundoNombre = "";
        String segundoNombreAnterior = "";
        
        // 2020-08-25 jyperez INC 281 erro con valores nulos en los campos segundo nombre y segundo apellido
        if (objeto.getSegundoApellido() != null) {
            segundoApellido = objeto.getSegundoApellido();
        }
        if (objetoAnterior.getSegundoApellido() != null) {
            segundoApellidoAnterior = objetoAnterior.getSegundoApellido();
        }
        if (objeto.getSegundoNombre() != null) {
            segundoNombre = objeto.getSegundoNombre();
        }
        if (objetoAnterior.getSegundoNombre() != null) {
            segundoNombreAnterior = objetoAnterior.getSegundoNombre();
        }
        try {
            //validamos que sus datos básicos hayan cambiado para ejecutar la consulta
            //2020-07-24 jyperez Se ajusta la consulta para adicionar los campos segundoApellido y SegundoNombre, solicitados en el inc 260
            if (!objeto.getPrimerApellido().equals(objetoAnterior.getPrimerApellido())
                    || !segundoApellido.equals(segundoApellidoAnterior)
                    || !objeto.getPrimerNombre().equals(objetoAnterior.getPrimerNombre())
                    || !segundoNombre.equals(segundoNombreAnterior)
                    || !(objeto.getFechaNacimiento().compareTo(objetoAnterior.getFechaNacimiento()) == 0)) {
                //2020-07-24 jyperez Se ajusta la consulta para adicionar los campos segundoApellido y SegundoNombre, solicitados en el inc 260
                afiliadoConsulta = getAfiliadoRemoto().consultar(objeto.getPrimerApellido(),objeto.getSegundoApellido() , objeto.getPrimerNombre(), objeto.getSegundoNombre(), objeto.getFechaNacimiento());
                if (afiliadoConsulta != null
                        && afiliadoConsulta.getId() != null
                        && afiliadoConsulta.getId().compareTo(objeto.getId()) != 0
                        && ( hashEstadosAfiliacion.get(afiliadoConsulta.getMaeEstadoAfiliacion()).contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO) ||
                             hashEstadosAfiliacion.get(afiliadoConsulta.getMaeEstadoAfiliacion()).getValor().equals(AfiliadoParametro.ESTADO_AFILIACION_PENDIENTE_SOPORTE) ||
                             hashEstadosAfiliacion.get(afiliadoConsulta.getMaeEstadoAfiliacion()).contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_INACTIVO))
                        ) {
                    mensaje = "Existe un afiliado registrado con los datos ingresados: Primer Apellido, Segundo Apellido, Primer Nombre, Segundo Nombre, Fecha de Nacimiento";
                } else {
                    mensaje = "";
                }
            }
        } catch (Exception ex) {
            mensaje = "Ocurrió un error consultando afliado Existente por Datos Básicos. Mensaje: " + ex.getMessage();
        }
        return mensaje;
    }
    
    /**
     * Función para validar que el numero del telefono movil se encuentre entre un rango de 300 y 350 inicialmente.
     * @param objeto
     * @return 
     * /
    private String validarRangoTelefonoMovil(AsegAfiliado objeto) {
        String mensaje = "";
        int prefijo = 0;
        //2020-07-27 jyperez se controla excepción debido a que hay datos erróneos ingresados por carga masiva - INC 261
        try{
            if(objeto.getTelefonoMovil() != null && !objeto.getTelefonoMovil().equals("") && !contieneSoloCaracteresEspacio(objeto.getTelefonoMovil())) {
                if (objeto.getTelefonoMovil().length() >= 3) {
                    prefijo = Integer.valueOf(objeto.getTelefonoMovil().substring(0, 3));
                    if (prefijo >= 300 && prefijo <= 350) {
                        mensaje = "";
                    } else {
                        mensaje = "El teléfono móvil no inicia entre el rango permitido de valores (300 - 350) ";
                    }

                } else {
                    mensaje = "El teléfono móvil no inicia entre el rango permitido de valores (300 - 350) ";
                }

            }
        } catch (Exception e) {
            mensaje = "El teléfono móvil no contiene un valor numérico.";
        }
        return mensaje;
        
    }*/
    
    /**
     * Se valida que no se ingresen los valores no permitidos ( numeros repetitivos) para el campo
     * teléfono fijo.
     * @param objeto
     * @return 
     * /
    private String validarTelefonosFijosNoPermitidos(AsegAfiliado objeto) {
        String mensaje = "";
        if (objeto.getTelefonoFijo() != null && !objeto.getTelefonoFijo().equals("") && !contieneSoloCaracteresEspacio(objeto.getTelefonoFijo())) {
            if (valoresNoPermitidosTelefonoFijo.contains(objeto.getTelefonoFijo())) {
                mensaje = "el telefono fijo no puede contener una secuencia de números repetitivos, lo cual lo hace inválido.";
            }
        }
        return mensaje;
        
    }*/

    private String validarAfiliadoActivoExistenteDatosBasicos(AsegAfiliado objeto, AsegAfiliado objetoAnterior) {
        String mensaje = "";
        AsegAfiliado afiliadoConsulta;
        try {
            //validamos si se esta realizando un cambio de estado de Retirado a Activo
            if (hashEstadosAfiliacion.get(objetoAnterior.getMaeEstadoAfiliacion()).getValor().equals(AfiliadoParametro.ESTADO_AFILIACION_RETIRADO) &&
                    hashEstadosAfiliacion.get(objeto.getMaeEstadoAfiliacion()).contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO)) {
                // validamos si existe un afiliado con los mismos datos del objeto
                afiliadoConsulta = getAfiliadoRemoto().consultar(objeto.getMaeTipoDocumento(),objeto.getNumeroDocumento(), objeto.getPrimerApellido(),
                        objeto.getSegundoApellido() , objeto.getPrimerNombre(), objeto.getSegundoNombre(), objeto.getFechaNacimiento());
                if (afiliadoConsulta != null
                        && afiliadoConsulta.getId() != null
                        && afiliadoConsulta.getId().compareTo(objeto.getId()) != 0 // registro afiliado diferente al que estamos modificando
                        && ( hashEstadosAfiliacion.get(afiliadoConsulta.getMaeEstadoAfiliacion()).contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO))
                        ) {
                    mensaje = "Existe un afiliado Activo registrado con los datos ingresados: Tipo Documento, Numero Documento, Primer Apellido, Segundo Apellido, Primer Nombre, Segundo Nombre, Fecha de Nacimiento";
                } else {
                    mensaje = "";
                }
            }
        } catch (Exception ex) {
            mensaje = "Ocurrió un error consultando afliado Existente Activo por Datos Básicos. Mensaje: " + ex.getMessage();
        }
        return mensaje;
    }
    
    private String validarAfliadoRetiradoFechaAfiliacion(AsegAfiliado objeto, AsegAfiliado objetoAnterior) {
        String mensaje = "";
        //2020-09-28 INC 313 validamos que no corresponda el cambio de estado a un afiliado con Novedad "Movilidad Ingreso Subsidiado Decreto 3047"
        if (objeto.getMaeEstadoAfiliacion() != objetoAnterior.getMaeEstadoAfiliacion()
            && hashEstadosAfiliacion.get(objeto.getMaeEstadoAfiliacion()).getValor().equals(AfiliadoParametro.ESTADO_AFILIACION_PENDIENTE_SOPORTE) 
            && hashEstadosAfiliacion.get(objetoAnterior.getMaeEstadoAfiliacion()).getValor().equals(AfiliadoParametro.ESTADO_AFILIACION_RETIRADO)
            && validarMesFecha(objeto.getFechaAfiliacionEps(), fechaActual) == 0) {
            mensaje = "";
            
        }else if (objeto.getMaeEstadoAfiliacion() != objetoAnterior.getMaeEstadoAfiliacion()
            && hashEstadosAfiliacion.get(objeto.getMaeEstadoAfiliacion()).getValor().equals(AfiliadoParametro.ESTADO_AFILIACION_PENDIENTE_SOPORTE) 
            && hashEstadosAfiliacion.get(objetoAnterior.getMaeEstadoAfiliacion()).getValor().equals(AfiliadoParametro.ESTADO_AFILIACION_RETIRADO)
                && objeto.getMaeCausaNovedad() != 0
                && !hashCausaNovedad.get(objeto.getMaeCausaNovedad()).getValor().equals(AfiliadoParametro.CAUSA_NOVEDAD_MOVILIDAD_INGRESO_SUBSIDIADO) ) {
            mensaje = "Es necesario que la fecha de afiliación EPS se encuentre en el mes actual para realizar la actualización de estado.";
        }
        
        return mensaje;
    }
    
    /**
     * Función para validar que una cadena sólo contenga caracteres espacio
     * @param texto
     * @return 
     */
    private boolean contieneSoloCaracteresEspacio(String texto) {
        return texto.isBlank();
    }

    /*2022-06-03 jyperez se eliminan funciones debido a que no aplican para los campos eliminados
    private String validarCantidadDigitosTelefonoFijo(AsegAfiliado objeto) {
        String mensaje = "";
        if (objeto.getTelefonoFijo() != null && !objeto.getTelefonoFijo().trim().equals("") && objeto.getTelefonoFijo().length() != 10) {
            mensaje = "El teléfono fijo debe tener 10 dígitos.";
        }
        return mensaje;
    }

    private String validarCantidadDigitosTelefonoMovil(AsegAfiliado objeto) {
        String mensaje = "";
        if (objeto.getTelefonoMovil() != null && !objeto.getTelefonoMovil().trim().equals("") && objeto.getTelefonoMovil().length() != 10) {
            mensaje = "El teléfono móvil debe tener 10 dígitos.";
        }
        return mensaje;
    }*/

    private String validarCambioMunicipioAfiliacion(AsegAfiliado objeto) {
        String mensaje = "";
        try {
            if (objeto.getMaeRegimenCodigo().equals(AfiliadoParametro.REGIMEN_SUBSIDIADO) 
                    && objeto.getAfiliacionUbicacion().getId().compareTo(objeto.getResidenciaUbicacion().getId()) != 0) {
                mensaje = "El municipio de afiliación y el municipio de residencia no puede ser diferentes si el régimen del afiliado es Subsidiado.";
            }
        }catch (Exception e) {
            mensaje = "Ocurrió un error realizando la validación de Cambio de Municipio Afiliación. Validar los valores de Régimen, Municipio Afiliación y Municipio Residencia.";
        }
        return mensaje;
    }

    public String validarRegimenCambioEstado(AsegAfiliado objeto, AsegAfiliado objetoAnterior) {
        String mensaje = "";
        try {
            if (objeto.getMaeRegimenCodigo().equals(AfiliadoParametro.REGIMEN_SUBSIDIADO) &&
                    (hashEstadosAfiliacion.get(objeto.getMaeEstadoAfiliacion()).getValor().equals(AfiliadoParametro.ESTADO_AFILIACION_ACTIVO_POR_EMERGENCIA) ||
                            hashEstadosAfiliacion.get(objeto.getMaeEstadoAfiliacion()).getValor().equals(AfiliadoParametro.ESTADO_AFILIACION_PROTECCION_LABORAL))) {
                mensaje = "El estado de afiliación no puede ser " + hashEstadosAfiliacion.get(objeto.getMaeEstadoAfiliacion()).getNombre() + " si el régimen del afiliado es Subsidiado.";
            }
        }catch (Exception e) {
            mensaje = "Ocurrió un error realizando la validación del Estado de Afiliación con el Régimen. Validar los valores de Régimen y Estado de Afiliación.";
        }
        return mensaje;
    }
    
    public String validarGrupoEtnicoIndigena(AsegAfiliado objeto) {
        String mensaje = "";
        try {
            if (objeto.getMaeEtniaCodigo().equals(AfiliadoParametro.ETNIA_INDIGENA)) {
                if (objeto.getMaeComunidadEtniaId() != null && objeto.getMaeComunidadEtniaId() != 0) {
                    mensaje = "";
                } else {
                    mensaje = "El campo Comunidad Etnica es obligatorio si el Grupo Etnico es Indigena.";
                }
            }
        }catch (Exception e) {
            mensaje = "Ocurrió un error realizando la validación del Grupo Etnico Indígena. Validar los valores de Régimen y Estado de Afiliación.";
        }
        return mensaje;
    }
    
    /**
     * Función para validar que no se ingresen los mismos datos del afiliado como su propio cabeza de familia
     * @param objeto
     * @return 
     */
    public String validarAfiliadoCabezaFamilia(AsegAfiliado objeto) {
        String mensaje = "";
        try {
            if (objeto.getMaeTipoDocumentoCf() != null && objeto.getNumeroDocumentoCf() != null &&
                    objeto.getMaeTipoDocumento() == objeto.getMaeTipoDocumentoCf() && 
                        objeto.getNumeroDocumento().equals(objeto.getNumeroDocumentoCf())) {
                    mensaje = "El afiliado no puede ser su propio cabeza de familia";
                } else {
                    mensaje = "";
                }
        }catch (Exception e) {
            mensaje = "Ocurrió un error realizando la validación de Cabeza de Familia";
        }
        return mensaje;
    }
    
    /**
     * Función para validar que para los afiliados Venezolanos identificados con el tipo de documento
     * Permiso Por Protección Temporal y Permiso Especial Permanencia se configure su país de nacimiento como Venezuela
     * @param objeto
     * @return 
     */
    public String validarTipoDocumentoPaisNacimientoVenezuela(AsegAfiliado objeto) {
        String mensaje = "";
        try {
            if ((objeto.getMaeTipoDocumentoCodigo().equals(AfiliadoParametro.TIPO_DOCUMENTO_PERMISO_ESPECIAL_PERMANENCIA) ||
                    objeto.getMaeTipoDocumentoCodigo().equals(AfiliadoParametro.TIPO_DOCUMENTO_PERMISO_PROTECCION_TEMPORAL))) {
                    if (!objeto.getNacimientoUbicacion().getPrefijo().equals(AfiliadoParametro.GN_UBICACIONES_PAIS_VENEZUELA)) {
                        mensaje = "Si el tipo de documento del afiliado es " + objeto.getMaeTipoDocumentoValor() + " , el país de Nacimiento debe ser Venezuela";
                    }
                }
        }catch (Exception e) {
            mensaje = "Ocurrió un error realizando la validación de Tipo Documento Pais Nacimiento Venezuela";
        }
        return mensaje;
    }
    
    /**
     * Función para validar que para los afiliados Venezolanos identificados con el tipo de documento
     * Permiso Por Protección Temporal y Permiso Especial Permanencia se configure su país de nacionalidad como Venezuela
     * @param objeto
     * @return 
     */
    public String validarTipoDocumentoPaisNacionalidadVenezuela(AsegAfiliado objeto) {
        String mensaje = "";
        try {
            if ((objeto.getMaeTipoDocumentoCodigo().equals(AfiliadoParametro.TIPO_DOCUMENTO_PERMISO_ESPECIAL_PERMANENCIA) ||
                    objeto.getMaeTipoDocumentoCodigo().equals(AfiliadoParametro.TIPO_DOCUMENTO_PERMISO_PROTECCION_TEMPORAL))) {
                    if (!objeto.getNacionalidadUbicacion().getPrefijo().equals(AfiliadoParametro.GN_UBICACIONES_PAIS_VENEZUELA)) {
                        mensaje = "Si el tipo de documento del afiliado es " + objeto.getMaeTipoDocumentoValor() + " , el país de Nacionalidad debe ser Venezuela";
                    }
                }
        }catch (Exception e) {
            mensaje = "Ocurrió un error realizando la validación de Tipo Documento Pais Nacionalidad Venezuela";
        }
        return mensaje;
    }
    
    /**
     * 
     * @param objeto
     * @return 
     */
    public String validarPaisNacimientoColombia(AsegAfiliado objeto) {
        String mensaje = "";
        if (objeto.getGnUbicacionesLugarNacimientoId() != null && !objeto.getNacimientoUbicacion().getPrefijo().equals(AfiliadoParametro.GN_UBICACIONES_PAIS_COLOMBIA)) {
            mensaje = "el país de Nacimiento debe ser Colombia para configurar el lugar de nacimiento.";
        }

        return mensaje;
    }
    
    /**
     * Validamos que si se marca un afiliado como Duplicado, esten actualizados los valores de Estado y Causa Novedad correctos
     * @param objeto
     * @return 
     */
    public String validarEstadoDuplicado(AsegAfiliado objeto) {
        String mensaje = "";
        if (objeto.getDuplicado() != null && objeto.getDuplicado()) {
            if (objeto.getMaeEstadoAfiliacionCodigo().equals(AfiliadoParametro.ESTADO_AFILIACION_DUPLICADO) &&
                    objeto.getMaeCausaNovedadCodigo().equals(AfiliadoParametro.CODIGO_NOVEDAD_IS_58)) {
                mensaje = "";
            } else if (!objeto.getMaeEstadoAfiliacionCodigo().equals(AfiliadoParametro.ESTADO_AFILIACION_DUPLICADO)) {
                mensaje = "Cuando se marca un afiliado como Duplicado, debe configurarse el estado correspondiente.";
            } else {
                mensaje = "Cuando se marca un afiliado como Duplicado, debe configurarse la causa novedad correspondiente.";
            }
        }
        return mensaje;
    }
    
    /**
     * Validamos que si un afiliado está marcado como trasladoPreaprobado, el estado del afiliado pertenezca al maestro acción de estados Activos
     * @param objeto
     * @return 
     */
    public String validarTrasladoPreaprobado(AsegAfiliado objeto) {
        String mensaje = "";
        if (objeto.isTrasladoPreaprobado()) {
            Maestro maestro = this.hashEstadosAfiliacion.get(objeto.getMaeEstadoAfiliacion());
            if (maestro != null && maestro.contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_INACTIVO)) {
                mensaje = "Cuando se marca un afiliado como Traslado Preaprobado, debe configurarse un estado Activo.";
            } else if (maestro ==null) {
                mensaje = "No se pudo determinar el estado de afiliación del usuario para validar si aplica traslado preaprobado.";
            }
        }
        return mensaje;
    }

}
