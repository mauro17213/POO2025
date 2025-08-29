/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.utilidades;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.webservices.rest.objeto.aseguramiento.ConsumoAfiliadoDTO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author jjmosquera
 */
public class AfiliadoValidacion extends ConsumoAfiliadoDTO {

    public final static String TIPO_AFILIADO_BENEFICIARIO = "102";
    public final static String TIPO_AFILIADO_ADICIONAL = "104";
    public final static String ESTADO_AFILIACION_ACTIVO = "101";
    public final static String ESTADO_AFILIACION_INACTIVO = "102";
    public final static String ESTADO_AFILIACION_SUSPENDIDO = "103";
    public final static String ESTADO_AFILIACION_FALLECIDO = "107";
    public final static String TIPO_AFILIADO_RETIRADO = "104";
    public final static int POBLACION_SISBENIZADA = 5;

    public final static String ESTADO_AFILIACION_PENDIENTE_SOPORTE = "777";
    //public final static String ESTADO_AFILIACION_ACTIVO = "101";
    //public final static String ESTADO_AFILIACION_INACTIVO = "102";
    //public final static String ESTADO_AFILIACION_SUSPENDIDO = "103";
    public final static String ESTADO_AFILIACION_RETIRADO = "104";
    //public final static String ESTADO_AFILIACION_FALLECIDO = "107";

    //2021-05-20 jyperez se actualiza el código al valor que tiene el maestro de régimen
    public static final String REGIMEN_SUBSIDIADO = "01";
    public static final String REGIMEN_CONTRIBUTIVO = "02";
    public static final String REGIMEN_SUBSIDIADO_ANTIGUO = "1";
    public static final String REGIMEN_CONTRIBUTIVO_ANTIGUO = "2";

    public static final String MODELO_LIQUIDACION_CAPITA = "0";
    public static final String MODELO_LIQUIDACION_EVENTO = "1";

    public final static int IDENTIFICADOR_DEPARTAMENTO_POR_DEFECTO = 2;
    public final static int IDENTIFICADOR_MEDELLIN = 3;

    public final static String NIVEL_1_SISBEN = "1";
    public final static String NIVEL_2_SISBEN = "2";

    public final static String ZONA_URBANA = "U";
    public final static String ZONA_RURAL = "R";

    //public final static String IDENTIFICADOR_DIVIPOLA_POR_DEFECTO = "05001";
    public final static String IDENTIFICADOR_UBICACION_ID_POR_DEFECTO = "3";

    //public final static String TIPO_AFILIADO_BENEFICIARIO = "102";
    public final static String ORIGEN_AFILIADO_NACIMIENTO = "NPNA";
    public final static String ORIGEN_AFILIADO_TRASLADO_OTRA_EPS = "TSOE";
    public final static String ORIGEN_AFILIADO_TRASLADO_EPS_LIQUIDADA = "296";
    public final static String ORIGEN_AFILIADO_AFILIACION_TRANSACCIONAL_SAT = "ASAT";

    public final static String TIPO_DOCUMENTO_CEDULA_DE_CIUDADANIA = "CC";
    public final static String TIPO_DOCUMENTO_ADULTO_SIN_IDENTIFICACION = "AS";
    public final static String TIPO_DOCUMENTO_CARNE_DIPLOMATICO = "CD";
    public final static String TIPO_DOCUMENTO_CEDULA_EXTRANGERIA = "CE";
    public final static String TIPO_DOCUMENTO_CERTIFICADO_NACIDO_VIVO = "CN";
    public final static String TIPO_DOCUMENTO_MENOR_SIN_IDENTIFICACION = "MS";
    public final static String TIPO_DOCUMENTO_NIT = "NI";
    public final static String TIPO_DOCUMENTO_NIUP = "NU";
    public final static String TIPO_DOCUMENTO_PASAPORTE = "PA";
    public final static String TIPO_DOCUMENTO_PERMISO_ESPECIAL_PERMANENCIA = "PE";
    public final static String TIPO_DOCUMENTO_REGISTRO_CIVIL = "RC";
    public final static String TIPO_DOCUMENTO_RUT = "RUT";
    public final static String TIPO_DOCUMENTO_SALVOCONDUCTO = "SC";
    public final static String TIPO_DOCUMENTO_TARJETA_IDENTIDAD = "TI";
    public final static String GRUPO_POBLACIONAL_POBLACION_SISBENIZADA = "5";
    public final static String GRUPO_POBLACIONAL_POBLACION_31 = "31";
    public final static String CONDICION_DISCAPACIDAD_TEMPORAL = "2";
    public final static String CAUSA_NOVEDAD_MOVILIDAD_INGRESO_SUBSIDIADO = "IS-36";

    //2021-05-14 jyperez Se adicionan los tipos de certificados a gestionar
    public final static int TIPO_CERTIFICADO_AFILIACION = 1;
    public final static int TIPO_CERTIFICADO_PORTABILIDAD = 2;
    public final static int TIPO_CERTIFICADO_FISCALIA = 3;
    public final static int CERTIFICADO_DIAS_VIGENCIA = 30;

    public final static String TIPO_CONTACTO_TELEFONO = "1";
    public final static String TIPO_CONTACTO_CELULAR = "2";
    public final static String TIPO_CONTACTO_CORREO_ELECTRONICO = "3";
    public final static int TIPO_ARCHIVO_NOVEDADES = 50;

    public static final int CAPITA = 1;
    public static final int EVENTO = 2;
    public static final int CANTIDAD = 0;
    public static final int VALIDA_CANTIDAD = 1;

    private HashMap<Integer, Maestro> hashTiposDocumento;
    private HashMap<Integer, Maestro> hashEstadosAfiliacion;
    private HashMap<Integer, Maestro> hashOrigenAfiliado;
    private HashMap<Integer, Maestro> hashGrupoPoblacional;
    private HashMap<Integer, Maestro> hashCausaNovedad;

    public AfiliadoValidacion() {
        valoresNoPermitidosTelefonoFijo = new ArrayList<>();
        valoresNoPermitidosTelefonoFijo.add("0000000");
        valoresNoPermitidosTelefonoFijo.add("1111111");
        valoresNoPermitidosTelefonoFijo.add("2222222");
        valoresNoPermitidosTelefonoFijo.add("3333333");
        valoresNoPermitidosTelefonoFijo.add("4444444");
        valoresNoPermitidosTelefonoFijo.add("5555555");
        valoresNoPermitidosTelefonoFijo.add("6666666");
        valoresNoPermitidosTelefonoFijo.add("7777777");
        valoresNoPermitidosTelefonoFijo.add("8888888");
        valoresNoPermitidosTelefonoFijo.add("9999999");

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

    public boolean validacionDatos(ConsumoAfiliadoDTO solicitudAfiliado) throws Exception {
        boolean validado = false;
        try {
            if (!solicitudAfiliado.getNut().equals("") && (solicitudAfiliado.getCantidadRegistros() >= CANTIDAD)
                    && !solicitudAfiliado.getAfiliadoNovedades().getAfiliadoCausaNovedad().equals("")  
                    //&& solicitudAfiliado.getAfiliadoNovedades().getAfiliadoNovedades() > CANTIDAD
                    && solicitudAfiliado.getAfiliadoNovedades().getConsecutivo() > CANTIDAD) {
                validado = true;
            }
        } catch (Exception e) {

        }

        return validado;
    }

    public boolean validacionBdua(ConsumoAfiliadoDTO solicitudAfiliado) throws Exception {
        boolean validado = false;

        switch (solicitudAfiliado.getAfiliadoNovedades().getAfiliadoRegistroBDUA()) {
            case "1":
                if (!solicitudAfiliado.getAfiliadoNovedades().getAfiliadoRegistroBDUA().equals("")) {
                    if (solicitudAfiliado.getAfiliadoNovedades().getAfiliadoSerialBDUA().bitLength() > CANTIDAD) {
                        validado = true;
                    }
                } else {
                    if (solicitudAfiliado.getAfiliadoNovedades().getAfiliadoRegimen().equals("contributivo")) {
                        validado = true;
                    }
                }
                break;

            case "0":
                validado = true;
                break;
            default:
                validado = false;
                ;

        }

        return validado;
    }

    public boolean validacionAfiliado(ConsumoAfiliadoDTO solicitudAfiliado) throws Exception {
        boolean validado = false;
        try {
            if (solicitudAfiliado.getAfiliadoNovedades().getAfiliadoTipoDocumento().equals("") 
                    && !solicitudAfiliado.getAfiliadoNovedades().getAfiliadoNumeroDocumento().equals("") 
                    && !solicitudAfiliado.getAfiliadoNovedades().getAfiliadoPrimerApellido().equals("")
                    && !solicitudAfiliado.getAfiliadoNovedades().getAfiliadoPrimerNombre().equals("") 
                    && !solicitudAfiliado.getAfiliadoNovedades().getAfiliadoGenero().equals("") 
                    && !solicitudAfiliado.getAfiliadoNovedades().getAfiliadoEstadoCivil().equals("") 
                    && !solicitudAfiliado.getAfiliadoNovedades().getAfiliadoOrigen().equals("") 
                    && solicitudAfiliado.getAfiliadoNovedades().getAfiliadoTipoBeneficiario().equals("")
                    && !solicitudAfiliado.getAfiliadoNovedades().getAfiliadoParentescoCotizante().equals("") 
                    && !solicitudAfiliado.getAfiliadoNovedades().getAfiliadoMunicipioAfiliacion().equals("") 
                    && !solicitudAfiliado.getAfiliadoNovedades().getAfiliadoMunicipioResidencia().equals("")
                    && !solicitudAfiliado.getAfiliadoNovedades().getAfiliadoDireccionResidencia().equals("") 
                    && !solicitudAfiliado.getAfiliadoNovedades().getAfiliadoBarrio().equals("") 
                    && !solicitudAfiliado.getAfiliadoNovedades().getAfiliadoZona().equals("")
                    && (solicitudAfiliado.getAfiliadoNovedades().getAfiliadoGrupoPoblacional() >= VALIDA_CANTIDAD) 
                    && solicitudAfiliado.getAfiliadoNovedades().getAfiliadoEtnia().equals("")
                    && solicitudAfiliado.getAfiliadoNovedades().getAfiliadoModeloLiquidacion().equals("") 
                    && solicitudAfiliado.getAfiliadoNovedades().getAfiliadoEstadoAfiliacion().equals("") 
                    && !solicitudAfiliado.getAfiliadoNovedades().getAfiliadoOrigen().equals("")) {

                if (solicitudAfiliado.getAfiliadoNovedades().getAfiliadoTipoBeneficiario().equals(TIPO_AFILIADO_BENEFICIARIO) 
                        || solicitudAfiliado.getAfiliadoNovedades().getAfiliadoTipoBeneficiario().equals(TIPO_AFILIADO_ADICIONAL)) {
                    if (solicitudAfiliado.getAfiliadoNovedades().getAfiliadoTipoDocumentoCF().equals("") /*&& !solicitudAfiliado.getAfilaidoNumeroDocumentoCF().equals("")*/) {
                        validado = true;
                    }
                    validado = false;
                }
            }
            validado = true;
        } catch (Exception e) {

        }
        return validado;
    }

    public boolean validarFechas(ConsumoAfiliadoDTO solicitudAfiliado) {
        boolean validado = false;
        if (solicitudAfiliado.getFechaHoraTransaccion() != null 
                && solicitudAfiliado.getAfiliadoNovedades().getAfiliadoFechaNacimiento() != null 
                && solicitudAfiliado.getAfiliadoNovedades().getAfiliadoFechaAfiliacionEPS() != null 
                && solicitudAfiliado.getAfiliadoNovedades().getAfiliadoFechaAfiliacionSGSSS() != null) {
            validado = true;
        }

        return validado;
    }

    public boolean estadoAfiliacion(ConsumoAfiliadoDTO solicitudAfiliado) {
        boolean validado = false;
        if (solicitudAfiliado.getAfiliadoNovedades().getAfiliadoEstadoAfiliacion().equals(ESTADO_AFILIACION_ACTIVO) 
                || solicitudAfiliado.getAfiliadoNovedades().getAfiliadoEstadoAfiliacion().equals(ESTADO_AFILIACION_SUSPENDIDO)
                || solicitudAfiliado.getAfiliadoNovedades().getAfiliadoEstadoAfiliacion().equals(TIPO_AFILIADO_RETIRADO)) {
            if (!solicitudAfiliado.getAfiliadoNovedades().getAfiliadoCausaNovedad().equals("")) {
                validado = true;
            }
            validado = false;
        }

        return validado;
    }

    public boolean validarFechasNovedad(ConsumoAfiliadoDTO solicitudAfiliado) {
        boolean validado = false;
        if (solicitudAfiliado.getAfiliadoNovedades().getAfiliadoFechaNovedad() != null) {
            validado = true;
        }

        return validado;
    }

    public boolean validarSisben(ConsumoAfiliadoDTO solicitudAfiliado) {
        boolean validado = false;
        if (solicitudAfiliado.getAfiliadoNovedades().getAfiliadoGrupoPoblacional() == POBLACION_SISBENIZADA) {
            if (!solicitudAfiliado.getAfiliadoNovedades().getAfiliadoNivelSisben().equals("")) {
                validado = true;
            }
        }

        return validado;
    }

    public boolean validarFijo(ConsumoAfiliadoDTO solicitudAfiliado) throws Exception {

        boolean validado = false;
        try {
            if (!solicitudAfiliado.getAfiliadoNovedades().getAfiliadoTelefonoMovil().equals("") 
                    || !solicitudAfiliado.getAfiliadoNovedades().getAfiliadoTelefonoFijo().equals("")) {
                if (solicitudAfiliado.getAfiliadoNovedades().getAfiliadoTelefonoMovil().equals("")) {
                    if (!solicitudAfiliado.getAfiliadoNovedades().getAfiliadoTelefonoFijo().equals("")) {
                        validado = true;
                    }
                }

                if (!solicitudAfiliado.getAfiliadoNovedades().getAfiliadoTelefonoFijo().equals("")) {
                    if (!solicitudAfiliado.getAfiliadoNovedades().getAfiliadoTelefonoMovil().equals("")) {
                        validado = true;
                    }

                    if (!solicitudAfiliado.getAfiliadoNovedades().getAfiliadoTelefonoMovil().equals("")) {
                        validado = true;
                    }

                }

                if (!solicitudAfiliado.getAfiliadoNovedades().getAfiliadoTelefonoMovil().equals("")) {
                    if (!solicitudAfiliado.getAfiliadoNovedades().getAfiliadoTelefonoFijo().equals("")) {
                        validado = true;
                    }
                }

                if (solicitudAfiliado.getAfiliadoNovedades().getAfiliadoTelefonoFijo().equals("")) {
                    if (!solicitudAfiliado.getAfiliadoNovedades().getAfiliadoTelefonoMovil().equals("")) {
                        validado = true;
                    }

                    if (!solicitudAfiliado.getAfiliadoNovedades().getAfiliadoTelefonoMovil().equals("")) {
                        validado = true;
                    }

                }
            }

        } catch (Exception e) {

        }
        return validado;
    }

    public boolean validacionRegimen(ConsumoAfiliadoDTO solicitudAfiliado) throws Exception {
        boolean validado = false;
        try {
            switch (solicitudAfiliado.getAfiliadoNovedades().getAfiliadoRegimen()) {
                case "01":
                    if (!solicitudAfiliado.getAfiliadoNovedades().getAfiliadoRegimen().equals("")) {
                        validado = true;
                    }
                    break;
                case "02":
                    if (!solicitudAfiliado.getAfiliadoNovedades().getAfiliadoCategoríaIBC().equals("") 
                            && !solicitudAfiliado.getAfiliadoNovedades().getAfiliadoTipoCotizante().equals("") 
                            && !solicitudAfiliado.getAfiliadoNovedades().getAfiliadoTipoDocumentoAportante().equals("")
                            && !solicitudAfiliado.getAfiliadoNovedades().getAfiliadoNumeroDocumentoAportante().equals("") 
                            && (solicitudAfiliado.getAfiliadoNovedades().getAfiliadoActividadEconomica() >= VALIDA_CANTIDAD) 
                            //&& (solicitudAfiliado.getAfiliadoNovedades().getAfiliadoARL() >= VALIDA_CANTIDAD)
                            && (solicitudAfiliado.getAfiliadoNovedades().getAfiliadoAFP() >= VALIDA_CANTIDAD) 
                            && !solicitudAfiliado.getAfiliadoNovedades().getAfiliadoCCF().equals("")) {
                        validado = true;
                    }
                    break;
            }
        } catch (Exception e) {

        }
        return validado;
    }

    public boolean validacionDiscapacidad(ConsumoAfiliadoDTO solicitudAfiliado) throws Exception {
        boolean validado = false;
        try {
            if (!solicitudAfiliado.getAfiliadoNovedades().getAfiliadoDiscapacidad().equals("")) {
                if (!solicitudAfiliado.getAfiliadoNovedades().getAfiliadoTipoDiscapacidad().equals("") 
                        && (solicitudAfiliado.getAfiliadoNovedades().getAfiliadoCondicionDiscapacidad().equals("")) 
                        && solicitudAfiliado.getAfiliadoNovedades().getAfiliadoFechainicioDiscapacidad() != null
                        && solicitudAfiliado.getAfiliadoNovedades().getAfiliadoFechaFinDiscapacidad() != null) {
                    validado = true;
                }
            }

        } catch (Exception e) {

        }
        return validado;
    }

    public boolean validacionCorreo(ConsumoAfiliadoDTO solicitudAfiliado) throws Exception {
        boolean validado = false;
        try {
            if (!solicitudAfiliado.getAfiliadoNovedades().getAfiliadoCorreoElectronico().equals("")) {

                validado = true;
            }
        } catch (Exception e) {

        }
        return validado;
    }

    /**
     * Función para validar el tipo afiliado Beneficiario y su relación no los
     * otros datos necesarios
     *
     * @param objeto
     * @return
     */
    public boolean validarTipoBeneficiario(ConsumoAfiliadoDTO objeto) throws Exception {
        boolean validado = false;
        try {
            if (objeto.getAfiliadoNovedades().getAfiliadoTipoBeneficiario().equals(TIPO_AFILIADO_BENEFICIARIO)
                    && (objeto.getAfiliadoNovedades().getAfiliadoTipoDocumentoCF() != null 
                    && !objeto.getAfiliadoNovedades().getAfiliadoTipoDocumentoCF().equals(objeto))
                    /*&& (objeto.getAfiliadoTipoDocumentoCF() != null && !objeto.getAfiliadoNumeroDocumentoCF().trim().equals(""))*/) {
                validado = true;
            } else if (!objeto.getAfiliadoNovedades().getAfiliadoTipoBeneficiario().equals(TIPO_AFILIADO_BENEFICIARIO)) {
                validado = true;
            } else {
                validado = false;
            }
        } catch (Exception e) {

        }
        return validado;
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

    /**
     * Función para validar que el numero del telefono movil se encuentre entre
     * un rango de 300 y 350 inicialmente.
     *
     * @param objeto
     * @return
     */
    private boolean validarRangoTelefonoMovil(ConsumoAfiliadoDTO objeto) throws Exception {
        boolean validacion = true;
        int prefijo = 0;

        try {
            if (objeto.getAfiliadoNovedades().getAfiliadoTelefonoMovil() != null 
                    && !objeto.getAfiliadoNovedades().getAfiliadoTelefonoMovil().equals("") 
                    && !contieneSoloCaracteresEspacio(objeto.getAfiliadoNovedades().getAfiliadoTelefonoMovil())) {
                if (objeto.getAfiliadoNovedades().getAfiliadoTelefonoMovil().length() >= 3) {
                    prefijo = Integer.valueOf(objeto.getAfiliadoNovedades().getAfiliadoTelefonoMovil().substring(0, 3));
                    if (prefijo >= 300 && prefijo <= 350) {
                        validacion = true;
                    } else {
                        validacion = false;
                    }

                } else {
                    validacion = false;
                }

            }
        } catch (Exception e) {
            validacion = false;
        }
        return validacion;

    }

    /**
     * Función para validar que una cadena sólo contenga caracteres espacio
     *
     * @param texto
     * @return
     */
    private boolean contieneSoloCaracteresEspacio(String texto) {
        boolean resultado = false;
        int cantidad = 0;
        if (texto.replace(' ', '_').length() == texto.length()) {
            resultado = true;
        }
        return resultado;

    }

    /**
     * Se valida que no se ingresen los valores no permitidos ( numeros
     * repetitivos) para el campo teléfono fijo.
     *
     * @param objeto
     * @return
     */
    private String validarTelefonosFijosNoPermitidos(AsegAfiliado objeto) {
        String mensaje = "";
        if (objeto.getTelefonoFijo() != null && !objeto.getTelefonoFijo().equals("") && !contieneSoloCaracteresEspacio(objeto.getTelefonoFijo())) {
            if (valoresNoPermitidosTelefonoFijo.contains(objeto.getTelefonoFijo())) {
                mensaje = "el telefono fijo no puede contener una secuencia de números repetitivos, lo cual lo hace inválido.";
            }
        }
        return mensaje;

    }

    private String validarCantidadDigitosTelefonoFijo(AsegAfiliado objeto) {
        String mensaje = "";
        if (objeto.getTelefonoFijo() != null && !objeto.getTelefonoFijo().trim().equals("") && objeto.getTelefonoFijo().length() != 7) {
            mensaje = "El teléfono fijo debe tener 7 dígitos.";
        }
        return mensaje;
    }

    private String validarCantidadDigitosTelefonoMovil(AsegAfiliado objeto) {
        String mensaje = "";
        if (objeto.getTelefonoMovil() != null && !objeto.getTelefonoMovil().trim().equals("") && objeto.getTelefonoMovil().length() != 10) {
            mensaje = "El teléfono móvil debe tener 10 dígitos.";
        }
        return mensaje;
    }

    public String getEstadoCivil(String valor) {
        String equivalente;
        switch (valor) {
            case "1":
                equivalente = "Soltero";
                break;
            case "2":
                equivalente = "Casado";
                break;
            case "3":
                equivalente = "Viudo";
                break;
            case "4":
                equivalente = "Divorciado";
                break;
            case "5":
                equivalente = "Unión Libre";
                break;
            case "6":
                equivalente = "Divorciado";
                break;
            case "7":
                equivalente = "No reportado";
                break;
            case "8":
                equivalente = "Otro";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    /**
     * Se debe validar que el numero de documento permite letras, en los casos
     * de los tipos de Documento Adulto sin Identificación (AS) y Menor Sin
     * identificación (MS)
     *
     * @return
     */
    private String validarNumeroDocumentoPermiteLetras(ConsumoAfiliadoDTO objeto) {
        String mensaje = "";

        if (!(hashTiposDocumento.get(objeto.getAfiliadoNovedades().getAfiliadoTipoDocumento()).getValor().equals(TIPO_DOCUMENTO_ADULTO_SIN_IDENTIFICACION)
                || hashTiposDocumento.get(objeto.getAfiliadoNovedades().getAfiliadoTipoDocumento()).getValor().equals(TIPO_DOCUMENTO_MENOR_SIN_IDENTIFICACION))
                && objeto.getAfiliadoNovedades().getAfiliadoNumeroDocumento().matches("\\d*")) {
            mensaje = "";
        } else if (!(hashTiposDocumento.get(objeto.getAfiliadoNovedades().getAfiliadoTipoDocumento()).getValor().equals(TIPO_DOCUMENTO_ADULTO_SIN_IDENTIFICACION)
                || hashTiposDocumento.get(objeto.getAfiliadoNovedades().getAfiliadoTipoDocumento()).getValor().equals(TIPO_DOCUMENTO_MENOR_SIN_IDENTIFICACION))) {
            mensaje = "El tipo de Documento "
                    + hashTiposDocumento.get(objeto.getAfiliadoNovedades().getAfiliadoTipoDocumento()).getNombre()
                    + " no admite letras en el Número de Documento.";
        }
        return mensaje;
    }
//    
//      private String validarAfliadoRetiradoFechaAfiliacion(AsegAfiliado objeto, AsegAfiliado objetoAnterior) {
//        String mensaje = "";
//        //2020-09-28 INC 313 validamos que no corresponda el cambio de estado a un afiliado con Novedad "Movilidad Ingreso Subsidiado Decreto 3047"
//        if (objeto.getMaeEstadoAfiliacion() != objetoAnterior.getMaeEstadoAfiliacion()
//            && hashEstadosAfiliacion.get(objeto.getMaeEstadoAfiliacion()).getValor().equals(AfiliadosBean.ESTADO_AFILIACION_PENDIENTE_SOPORTE) 
//            && hashEstadosAfiliacion.get(objetoAnterior.getMaeEstadoAfiliacion()).getValor().equals(AfiliadosBean.ESTADO_AFILIACION_RETIRADO)
//            && validarMesFecha(objeto.getFechaAfiliacionEps(), fechaActual) == 0) {
//            mensaje = "";
//            
//        }else if (objeto.getMaeEstadoAfiliacion() != objetoAnterior.getMaeEstadoAfiliacion()
//            && hashEstadosAfiliacion.get(objeto.getMaeEstadoAfiliacion()).getValor().equals(AfiliadosBean.ESTADO_AFILIACION_PENDIENTE_SOPORTE) 
//            && hashEstadosAfiliacion.get(objetoAnterior.getMaeEstadoAfiliacion()).getValor().equals(AfiliadosBean.ESTADO_AFILIACION_RETIRADO)
//                && objeto.getMaeCausaNovedad() != 0
//                && !hashCausaNovedad.get(objeto.getMaeCausaNovedad()).getValor().equals(AfiliadosBean.CAUSA_NOVEDAD_MOVILIDAD_INGRESO_SUBSIDIADO) ) {
//            mensaje = "Es necesario que la fecha de afiliación EPS se encuentre en el mes actual para realizar la actualización de estado.";
//        }
//        
//        return mensaje;
//    }

    private String validarDireccion(ConsumoAfiliadoDTO objeto) {
        String mensaje;
        if (!(objeto.getAfiliadoNovedades().getAfiliadoDireccionResidencia() == null)
                && !objeto.getAfiliadoNovedades().getAfiliadoDireccionResidencia().trim().equals("")) {
            mensaje = "";
        } else {
            mensaje = "Dirección: Este campo es obligatorio";
        }

        return mensaje;
    }

    /**
     * Funcion para validar que cuando se seleccione Nacimiento, los valores de
     * Tipo Documento CF y Numero Documento CF tengan valores
     *
     * @param objeto
     * @return //
     */
//    private String validarOrigenAfiliadoNacimiento(AsegAfiliado objeto) {
//        String mensaje;
//        if (hashOrigenAfiliado.get(objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadosBean.ORIGEN_AFILIADO_NACIMIENTO)
//                && (objeto.getMaeTipoDocumentoCf() != null && objeto.getMaeTipoDocumentoCf() != 0)
//                && (objeto.getNumeroDocumentoCf() != null && !objeto.getNumeroDocumentoCf().trim().equals(""))) {
//            mensaje = "";
//        } else if (!hashOrigenAfiliado.get(objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadosBean.ORIGEN_AFILIADO_NACIMIENTO)) {
//            mensaje = "";
//        } else {
//            mensaje = "Si Origen Afiliado es "
//                    + hashOrigenAfiliado.get(objeto.getMaeOrigenAfiliado()).getNombre()
//                    + ", deben ingresarse los campos Tipo Documento CF y Número Documento CF";
//        }
//        return mensaje;
//    }
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
     * Funcion para validar que cuando se seleccione Nacimiento, la fecha de
     * actual no sea mayor en un año que la fecha de nacimiento
     *
     * @param objeto
     * @return
     */
//    private String validarFechaNacimientoNoMayorAnio(AsegAfiliado objeto) {
//        String mensaje;
//        if (hashOrigenAfiliado.get(objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadosBean.ORIGEN_AFILIADO_NACIMIENTO)
//                && fechaActual.compareTo(calcularFechaCumpleanos(1, objeto.getFechaNacimiento())) < 0) {
//            mensaje = "";
//        } else if (!hashOrigenAfiliado.get(objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadosBean.ORIGEN_AFILIADO_NACIMIENTO)) {
//            mensaje = "";
//        } else {
//            mensaje = "Si Origen Afiliado es "
//                    + hashOrigenAfiliado.get(objeto.getMaeOrigenAfiliado()).getNombre()
//                    + ", la fecha de nacimiento no debe superar en 1 año a la fecha actual";
//        }
//        return mensaje;
//    }
    /**
     * Se debe validar que cuando se escoja el valor de grupo poblacional
     * diferente a población sisbenizada, el valor del nivel de sisben sea No
     * aplica
     *
     * @param objeto
     * @return
     */
//    private String validarGrupoPoblacional(AsegAfiliado objeto) {
//        String mensaje = "";
//        //2020-08-20 solo se aplicará validación de grupo poblacional cuando no sea grupo poblacional 31
//        if (!hashGrupoPoblacional.get(objeto.getMaeGrupoPoblacional()).getValor().equals(AfiliadosBean.GRUPO_POBLACIONAL_POBLACION_31)) {
//            if (!hashGrupoPoblacional.get(objeto.getMaeGrupoPoblacional()).getValor().equals(AfiliadosBean.GRUPO_POBLACIONAL_POBLACION_SISBENIZADA)
//                    && objeto.getMaeNivelSisbenCodigo().equals("N")) {
//                mensaje = "";
//            } else if (!hashGrupoPoblacional.get(objeto.getMaeGrupoPoblacional()).getValor().equals(AfiliadosBean.GRUPO_POBLACIONAL_POBLACION_SISBENIZADA)) {
//                mensaje = "El valor del Nivel de Sisben debe ser No Aplica si el Grupo Poblacional es " + hashGrupoPoblacional.get(objeto.getMaeGrupoPoblacional()).getNombre();
//            } else {
//                mensaje = "";
//            }
//        }
//        return mensaje;
//    }
    /**
     * Se valida que no se ingresen los valores no permitidos ( numeros
     * repetitivos) para el campo teléfono fijo.
     *
     * @param objeto
     * @return
     */
//    private String validarTelefonosFijosNoPermitidos(AsegAfiliado objeto) {
//        String mensaje = "";
//        if (objeto.getTelefonoFijo() != null && !objeto.getTelefonoFijo().equals("") && !contieneSoloCaracteresEspacio(objeto.getTelefonoFijo())) {
//            if (valoresNoPermitidosTelefonoFijo.contains(objeto.getTelefonoFijo())) {
//                mensaje = "el telefono fijo no puede contener una secuencia de números repetitivos, lo cual lo hace inválido.";
//            }
//        }
//        return mensaje;
//        
//    }
    /**
     * Función para validar que el numero del telefono movil se encuentre entre
     * un rango de 300 y 350 inicialmente.
     *
     * @param objeto
     * @return
     */
    private String validarRangoTelefonoMovil(AsegAfiliado objeto) {
        String mensaje = "";
        int prefijo = 0;
        //2020-07-27 jyperez se controla excepción debido a que hay datos erróneos ingresados por carga masiva - INC 261
        try {
            if (objeto.getTelefonoMovil() != null && !objeto.getTelefonoMovil().equals("") && !contieneSoloCaracteresEspacio(objeto.getTelefonoMovil())) {
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

    }

    public void validarCreacion(ConsumoAfiliadoDTO objeto, int tipoValidacion, char accionAdicional) {
        errores = new ArrayList();

        addError(validarNumeroDocumentoPermiteLetras(objeto));
        addError(validarDireccion(objeto));

    }

    public boolean validarTipoDocumento(ConsumoAfiliadoDTO valor) {
        boolean equivalente = false;
        if (!valor.getAfiliadoNovedades().getAfiliadoTipoDocumento().equals("")) {
            switch (valor.getAfiliadoNovedades().getAfiliadoTipoDocumento()) {
                case "CC":
                   equivalente = true;
                    break;
                case "AS":
                    equivalente = true;
                    break;
                case "CD":
                    equivalente = true;
                    break;
                case "CE":
                   equivalente = true;
                    break;
                case "CN":
                    equivalente = true;
                    break;
                case "MS":
                    equivalente = true;
                    break;
                case "NI":
                    equivalente = true;
                    break;
                case "NU":
                   equivalente = true;
                    break;
                case "PA":
                    equivalente = true;
                    break;
                case "PE":
                    equivalente = true;
                    break;
                case "RUT":
                    equivalente = true;
                    break;
                case "SC":
                    equivalente = true;
                    break;
                case "TI":
                    equivalente = true;
                    break;
                default:
                    equivalente = false;
                    break;
            }
        }
        return equivalente;
    }

}
