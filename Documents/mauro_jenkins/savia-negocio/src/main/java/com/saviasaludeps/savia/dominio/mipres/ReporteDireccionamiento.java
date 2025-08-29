package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author stive
 */
public class ReporteDireccionamiento extends Auditoria {

    private Integer strIdCiclo;
    private String strFecha;
    private String strHora;
    private String strNumeroPrescripcion;
    private String strRegimen;
    private String strAmbito;
    private String strTipoTecnologia;
    private String strNombrePaciente;
    private String strNombreProfesional;
    private String strFechaDireccionamiento;
    private String strIdDireccionamiento;
    private String strEstado;
    private String strFechaMaximaEntrega;
    private String strCantidad;
    private String strMunicipio;
    private String strFechaAnulacion;
    private String strPrestador;
    private String strCodigoEntrega;
    private String strNombreDirecciona;
    private String strNota;

    public ReporteDireccionamiento() {
    }

    public ReporteDireccionamiento(Integer strIdCiclo, String strFecha, String strHora, String strNumeroPrescripcion, String strRegimen, String strAmbito, String strTipoTecnologia, String strNombrePaciente, String strNombreProfesional, String strFechaDireccionamiento, String strIdDireccionamiento, String strEstado, String strFechaMaximaEntrega, String strCantidad, String strMunicipio, String strFechaAnulacion, String strPrestador, String strCodigoEntrega, String strNombreDirecciona, String strNota) {
        this.strIdCiclo = strIdCiclo;
        this.strFecha = strFecha;
        this.strHora = strHora;
        this.strNumeroPrescripcion = strNumeroPrescripcion;
        this.strRegimen = strRegimen;
        this.strAmbito = strAmbito;
        this.strTipoTecnologia = strTipoTecnologia;
        this.strNombrePaciente = strNombrePaciente;
        this.strNombreProfesional = strNombreProfesional;
        this.strFechaDireccionamiento = strFechaDireccionamiento;
        this.strIdDireccionamiento = strIdDireccionamiento;
        this.strEstado = strEstado;
        this.strFechaMaximaEntrega = strFechaMaximaEntrega;
        this.strCantidad = strCantidad;
        this.strMunicipio = strMunicipio;
        this.strFechaAnulacion = strFechaAnulacion;
        this.strPrestador = strPrestador;
        this.strCodigoEntrega = strCodigoEntrega;
        this.strNombreDirecciona = strNombreDirecciona;
        this.strNota = strNota;
    }

    public Integer getStrIdCiclo() {
        return strIdCiclo;
    }

    public void setStrIdCiclo(Integer strIdCiclo) {
        this.strIdCiclo = strIdCiclo;
    }

    public String getStrFecha() {
        return strFecha;
    }

    public void setStrFecha(String strFecha) {
        this.strFecha = strFecha;
    }

    public String getStrHora() {
        return strHora;
    }

    public void setStrHora(String strHora) {
        this.strHora = strHora;
    }

    public String getStrNumeroPrescripcion() {
        return strNumeroPrescripcion;
    }

    public void setStrNumeroPrescripcion(String strNumeroPrescripcion) {
        this.strNumeroPrescripcion = strNumeroPrescripcion;
    }

    public String getStrRegimen() {
        return strRegimen;
    }

    public void setStrRegimen(String strRegimen) {
        this.strRegimen = strRegimen;
    }

    public String getStrAmbito() {
        return strAmbito;
    }

    public void setStrAmbito(String strAmbito) {
        this.strAmbito = strAmbito;
    }

    public String getStrTipoTecnologia() {
        return strTipoTecnologia;
    }

    public void setStrTipoTecnologia(String strTipoTecnologia) {
        this.strTipoTecnologia = strTipoTecnologia;
    }

    public String getStrNombrePaciente() {
        return strNombrePaciente;
    }

    public void setStrNombrePaciente(String strNombrePaciente) {
        this.strNombrePaciente = strNombrePaciente;
    }

    public String getStrNombreProfesional() {
        return strNombreProfesional;
    }

    public void setStrNombreProfesional(String strNombreProfesional) {
        this.strNombreProfesional = strNombreProfesional;
    }

    public String getStrFechaDireccionamiento() {
        return strFechaDireccionamiento;
    }

    public void setStrFechaDireccionamiento(String strFechaDireccionamiento) {
        this.strFechaDireccionamiento = strFechaDireccionamiento;
    }

    public String getStrIdDireccionamiento() {
        return strIdDireccionamiento;
    }

    public void setStrIdDireccionamiento(String strIdDireccionamiento) {
        this.strIdDireccionamiento = strIdDireccionamiento;
    }

    public String getStrEstado() {
        return strEstado;
    }

    public void setStrEstado(String strEstado) {
        this.strEstado = strEstado;
    }

    public String getStrFechaMaximaEntrega() {
        return strFechaMaximaEntrega;
    }

    public void setStrFechaMaximaEntrega(String strFechaMaximaEntrega) {
        this.strFechaMaximaEntrega = strFechaMaximaEntrega;
    }

    public String getStrCantidad() {
        return strCantidad;
    }

    public void setStrCantidad(String strCantidad) {
        this.strCantidad = strCantidad;
    }

    public String getStrMunicipio() {
        return strMunicipio;
    }

    public void setStrMunicipio(String strMunicipio) {
        this.strMunicipio = strMunicipio;
    }

    public String getStrFechaAnulacion() {
        return strFechaAnulacion;
    }

    public void setStrFechaAnulacion(String strFechaAnulacion) {
        this.strFechaAnulacion = strFechaAnulacion;
    }

    public String getStrPrestador() {
        return strPrestador;
    }

    public void setStrPrestador(String strPrestador) {
        this.strPrestador = strPrestador;
    }

    public String getStrCodigoEntrega() {
        return strCodigoEntrega;
    }

    public void setStrCodigoEntrega(String strCodigoEntrega) {
        this.strCodigoEntrega = strCodigoEntrega;
    }

    public String getStrNombreDirecciona() {
        return strNombreDirecciona;
    }

    public void setStrNombreDirecciona(String strNombreDirecciona) {
        this.strNombreDirecciona = strNombreDirecciona;
    }

    public String getStrNota() {
        return strNota;
    }

    public void setStrNota(String strNota) {
        this.strNota = strNota;
    }
    
    

}
