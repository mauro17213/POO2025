/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.utilidades;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoCargaRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoCargaSucesoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoDetalleRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoSedeRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaSoatTarifarioValorRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaTecnologiaServicioHabilitacionRemoto;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Pérez
 */
public class ContratoDetalleValidacion {

    private List<String> errores = new ArrayList();
    private HashMap<Integer, Maestro> hashEstadoContrato;

    private final Date fechaActual;
    
    public final static int TIPO_VALIDACION_APLICACION_WEB = 0;
    public final static int TIPO_VALIDACION_CARGA_MASIVA = 1;
    public final static String ESTADO_CONTRATO_VIGENTE = "01";
    public final static String ESTADO_CONTRATO_NO_VIGENTE = "02";
    public final static int TIPO_TECNOLOGIA_TECNOLOGIA = 1;
    public final static int TIPO_TECNOLOGIA_MEDICAMENTO = 2;
    public final static int TIPO_TECNOLOGIA_INSUMO = 3;
    public final static int TIPO_TECNOLOGIA_PAQUETE = 4;
    

    public ContratoDetalleValidacion() {
        fechaActual = new Date();
        //carga de maestros
        try {
            hashEstadoContrato = getMaestroRemoto().consultarHashPorTipo(MaestroTipo.CNT_ESTADO);
        }catch (Exception ex) {
            Logger.getLogger(ContratoDetalleValidacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
        return (MaestroRemoto) object;
    }

    private CntContratoCargaRemoto getContratoCargaRemoto() throws Exception {
        return (CntContratoCargaRemoto) RemotoEJB.getEJBRemoto(("CntContratoCargaServicio"), CntContratoCargaRemoto.class.getName());
    }

    private CntContratoCargaSucesoRemoto getCargaMasivaContratoSucesoRemoto() throws Exception {
        return (CntContratoCargaSucesoRemoto) RemotoEJB.getEJBRemoto(("CntContratoCargaSucesoServicio"), CntContratoCargaSucesoRemoto.class.getName());
    }

    private CntContratoDetalleRemoto getContratoDetalleRemoto() throws Exception {
        return (CntContratoDetalleRemoto) RemotoEJB.getEJBRemoto(("CntContratoDetalleServicio"), CntContratoDetalleRemoto.class.getName());
    }

    private CntContratoSedeRemoto getContratoSedeRemoto() throws Exception {
        return (CntContratoSedeRemoto) RemotoEJB.getEJBRemoto(("CntContratoSedeServicio"), CntContratoSedeRemoto.class.getName());
    }

    private CntContratoRemoto getContratoRemoto() throws Exception {
        return (CntContratoRemoto) RemotoEJB.getEJBRemoto(("CntContratoServicio"), CntContratoRemoto.class.getName());
    }

    private MaTecnologiaServicioHabilitacionRemoto getTecnologiaServicioHabilitacionRemoto() throws Exception {
        return (MaTecnologiaServicioHabilitacionRemoto) RemotoEJB.getEJBRemoto(("MaTecnologiaServicioHabilitacionServicio"), MaTecnologiaServicioHabilitacionRemoto.class.getName());
    }

    private MaSoatTarifarioValorRemoto getTarifarioValorRemoto() throws Exception {
        return (MaSoatTarifarioValorRemoto) RemotoEJB.getEJBRemoto(("MaSoatTarifarioValorServicio"), MaSoatTarifarioValorRemoto.class.getName());
    }

    public List<String> getErrores() {
        return errores;
    }
    
    public String getErroresStr() {
        String strError = "";
        for(String str : errores){
            strError += str + ". ";
        }
        return strError;
    }
    
    public boolean isError() {
        return (!this.errores.isEmpty());
    }    

    public void setErrores(List<String> errores) {
        this.errores = errores;
    }
    
    public void validar(CntContratoDetalle objeto, int tipoValidacion, boolean crear) {
        errores = new ArrayList();
        if (tipoValidacion == TIPO_VALIDACION_CARGA_MASIVA) {
            addError(validarContratoExistenteYVigente(objeto));
            //addError(validarContratoSedeExistente(objeto));
            //validaciones de fecha
            addError(validarFechas(objeto));
            addError(validarFechainicioContrato(objeto));
            addError(validarFechaFinContrato(objeto));
            if (crear) {
                if (objeto.getTipoTecnologia() == TIPO_TECNOLOGIA_TECNOLOGIA) {
                    addError(validarContratoDetalleTecnologiaExistente(objeto));
                }else {
                    addError(validarContratoDetalleExistente(objeto));
                }
            }
        }
    }
    
    private String validarContratoDetalleTecnologiaExistente(CntContratoDetalle objeto) {
        String mensaje = "";
        CntContratoDetalle detalle = null;
        try {
            detalle = getContratoDetalleRemoto().consultarPorContratoSedeTecnologiaTipoYServicioHabilitacion(objeto.getCntContrato().getId(),
                    objeto.getCntContratoSede().getId(),objeto.getMaTecnologiaId(), objeto.getMaServicioHabilitacionId(), objeto.getTipoTecnologia());
            if (detalle != null) {
                mensaje = "Existe un contrato detalle para el mismo contrato, sede, tecnologia y servicio ingresado";
            }
        }catch (Exception ex) {
            mensaje = "Ocurrió un error al validar Contrato Detalle Existente. Error: " + ex.getMessage();
        }
        return mensaje;
    }
    
    private String validarContratoDetalleExistente(CntContratoDetalle objeto) {
        String mensaje = "";
        CntContratoDetalle detalle = null;
        try {
            detalle = getContratoDetalleRemoto().consultarPorContratoSedeTecnologiaYTipoTecnologia(objeto.getCntContrato().getId(),
                    objeto.getCntContratoSede().getId(),objeto.getMaTecnologiaId(),objeto.getTipoTecnologia());
            if (detalle != null) {
                mensaje = "Existe un contrato detalle para el mismo contrato, sede y tecnologia ingresado";
            }
        }catch (Exception ex) {
            mensaje = "Ocurrió un error al validar Contrato Detalle Existente. Error: " + ex.getMessage();
        }
        return mensaje;
    }
    
    private String validarContratoExistenteYVigente(CntContratoDetalle objeto) {
        String mensaje = "";
        Maestro maestro = null;
        try {
            maestro = hashEstadoContrato.get(objeto.getCntContrato().getMaeEstadoContratoId());
            if (maestro == null) {
                mensaje = "No es posible validar la vigencia del contrato";
            } else if (maestro.getValor().equals(ESTADO_CONTRATO_NO_VIGENTE)) {
                mensaje = "El contrato ingresado no esta vigente";
            }
        }catch (Exception ex) {
            mensaje = "El contrato ingresado no pudo ser consultado";
        }
        return mensaje;
    }
    
    private String validarContratoSedeExistente(CntContratoDetalle objeto) {
        String mensaje = "";
        int cantidad = 0;
        try {
            cantidad = getContratoSedeRemoto().consultarCantidadPorContratoYCodigoHabilitacionPrestador(objeto.getCntContrato().getContrato(),objeto.getCntContratoSede().getCntPrestadorSede().getCodigoHabilitacionSede());
            if (cantidad == 0) {
                mensaje = "El Código Habilitación de la sede ingresada no se encuentra asociado al contrato";
            }
        }catch (Exception ex) {
            mensaje = " El Código Habilitación de la sede ingresada no pudo ser consultado";
        }
        return mensaje;
    }
    
    private String validarFechainicioContrato(CntContratoDetalle objeto) {
        String mensaje = "";
        CntContrato obj;
        try {
            //obj = getContratoRemoto().consultarPorContrato(objeto.getCntContrato().getContrato());
            obj = objeto.getCntContrato();
            if (obj != null) {
                if (!(objeto.getFechaHoraInicio().compareTo(obj.getFechaInicio()) >= 0
                        && objeto.getFechaHoraInicio().compareTo(obj.getFechaFin()) <= 0 )) {
                    mensaje = "La Fecha Hora Inicio no se encuentra entre las fechas inicial y final del contrato";
                }
            }
            //la validación inicial sabrá si existe el contrato ingresado o no
        }catch (Exception ex) {
            mensaje = "No se pudo validar la Fecha Hora Inicio";
        }
        return mensaje;    
    }
    
    private String validarFechaFinContrato(CntContratoDetalle objeto) {
        String mensaje = "";
        CntContrato obj;
        try {
            //obj = getContratoRemoto().consultarPorContrato(objeto.getCntContrato().getContrato());
            obj = objeto.getCntContrato();
            if (obj != null) {
                if (!(objeto.getFechaHoraFin().compareTo(obj.getFechaInicio()) >= 0
                        && objeto.getFechaHoraFin().compareTo(obj.getFechaFin()) <= 0 )) {
                    mensaje = "La Fecha Hora Fin no se encuentra entre las fechas inicial y final del contrato";
                }
            }
            //la validación inicial sabrá si existe el contrato ingresado o no
        }catch (Exception ex) {
            mensaje = "No se pudo validar la Fecha Hora Fin";
        }
        return mensaje;    
    }
    
    /**
     * función para determinar si las fecha inicio es menor a la fecha fin del detalle.
     * @param objeto
     * @return 
     */
    private String validarFechas(CntContratoDetalle objeto) {
        String mensaje = "";
        if (objeto.getFechaHoraInicio().after(objeto.getFechaHoraFin())) {
            mensaje = "La Fecha Hora Inicio debe ser menor a la Fecha Hora Fin";
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
    private static Date calcularFechaCumpleanos(int anio, Date fecha) {
        Date cumpleanos18;
        Calendar fechaNac = Calendar.getInstance();
        Calendar fechaCumpleanos18 = Calendar.getInstance();
        //Calendar fechaActual = Calendar.getInstance();

        // cargamos la fecha a evaluar
        fechaNac.setTime(fecha);
        // Cálculo de las diferencias.
        int years = fechaNac.get(Calendar.YEAR) + anio;
        int months = fechaNac.get(Calendar.MONTH);
        int days = fechaNac.get(Calendar.DAY_OF_MONTH);

        fechaCumpleanos18.set(years, months, days);
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
    
    /**
     * Función para validar que una cadena sólo contenga caracteres espacio
     * @param texto
     * @return 
     */
    private boolean contieneSoloCaracteresEspacio(String texto) {
        boolean resultado = false;
        int cantidad = 0;
        if (texto.replace(' ','_').length() == texto.length()){
            resultado = true;
        }
        return resultado;
        
    }

}
