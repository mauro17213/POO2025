/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.tutela;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pavacca
 */
public class TuMemorialPersona extends Auditoria {
    
    public final static int TIPO_PERSONAL_ABOGADO = 1;
    public final static int TIPO_PERSONAL_ASISTENTE = 2;
    private Integer id;
    private Integer tipoPersonal;
    private Integer maeTipoDocumentoId;
    private String maeTipoDocumentoCodigo;
    private String maeTipoDocumentoValor;
    private String numeroDocumento;
    private Integer maeGnCargoId;
    private String maeGnCargoCodigo;
    private String maeGnCargoValor;
    private String numeroTarjetaProfesional;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private List<TuMemorialFirma> tuMemorialFirmasList;

    public TuMemorialPersona() {

    }

    public TuMemorialPersona(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipoPersonal() {
        return tipoPersonal;
    }

    public void setTipoPersonal(Integer tipoPersonal) {
        this.tipoPersonal = tipoPersonal;
    }

    public Integer getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(Integer maeTipoDocumentoId) {
        this.maeTipoDocumentoId = maeTipoDocumentoId;
    }

    public String getMaeTipoDocumentoCodigo() {
        return maeTipoDocumentoCodigo;
    }

    public void setMaeTipoDocumentoCodigo(String maeTipoDocumentoCodigo) {
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
    }

    public String getMaeTipoDocumentoValor() {
        return maeTipoDocumentoValor;
    }

    public void setMaeTipoDocumentoValor(String maeTipoDocumentoValor) {
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Integer getMaeGnCargoId() {
        return maeGnCargoId;
    }

    public void setMaeGnCargoId(Integer maeGnCargoId) {
        this.maeGnCargoId = maeGnCargoId;
    }

    public String getMaeGnCargoCodigo() {
        return maeGnCargoCodigo;
    }

    public void setMaeGnCargoCodigo(String maeGnCargoCodigo) {
        this.maeGnCargoCodigo = maeGnCargoCodigo;
    }

    public String getMaeGnCargoValor() {
        return maeGnCargoValor;
    }

    public void setMaeGnCargoValor(String maeGnCargoValor) {
        this.maeGnCargoValor = maeGnCargoValor;
    }

    public String getNumeroTarjetaProfesional() {
        return numeroTarjetaProfesional;
    }

    public void setNumeroTarjetaProfesional(String numeroTarjetaProfesional) {
        this.numeroTarjetaProfesional = numeroTarjetaProfesional;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public List<TuMemorialFirma> getTuMemorialFirmasList() {
        if(tuMemorialFirmasList == null){
            tuMemorialFirmasList = new ArrayList<>();
        }
        return tuMemorialFirmasList;
    }

    public void setTuMemorialFirmasList(List<TuMemorialFirma> tuMemorialFirmasList) {
        this.tuMemorialFirmasList = tuMemorialFirmasList;
    }
    
    //Metodos auxiliares   
    
    public String getTipoPersonalStr() {
        switch (this.tipoPersonal) {
            case TIPO_PERSONAL_ABOGADO:
                return "Abogado";
            case TIPO_PERSONAL_ASISTENTE:
                return "Asistente";
            default:
                return "";
        }
    }
    
    public String getNombreCompleto() {
        String nombre = getPrimerNombre() + " ";
        if (getSegundoNombre() != null) {
            nombre += getSegundoNombre() + " ";
        }
        nombre += getPrimerApellido();
        if (getSegundoApellido() != null) {
            nombre += " " + getSegundoApellido();
        }
        return nombre;
    }
    
    public String getDatosAuditoria() {
        String datos = "";
        if (getPrimerNombre() != null) {
            datos += " " + getPrimerNombre();
        }
        if (getSegundoNombre() != null) {
            datos += " " + getSegundoNombre();
        }
        if (getPrimerApellido() != null) {
            datos += " " + getPrimerApellido();
        }
        if (getSegundoApellido() != null) {
            datos += " " + getSegundoApellido();
        }
        if (getMaeTipoDocumentoCodigo() != null) {
            datos += " " + getMaeTipoDocumentoCodigo();
        }
        if (getNumeroDocumento() != null) {
            datos += " " + getNumeroDocumento() + " ";
        }
        return datos;
    }

    @Override
    public String toString() {
        return "TuMemorialPersona{" + "id=" + id + ", tipoPersonal=" + tipoPersonal + ", maeTipoDocumentoId=" + maeTipoDocumentoId + ", maeTipoDocumentoCodigo=" + maeTipoDocumentoCodigo + ", maeTipoDocumentoValor=" + maeTipoDocumentoValor + ", numeroDocumento=" + numeroDocumento + ", maeGnCargoId=" + maeGnCargoId + ", maeGnCargoCodigo=" + maeGnCargoCodigo + ", maeGnCargoValor=" + maeGnCargoValor + ", numeroTarjetaProfesional=" + numeroTarjetaProfesional + ", primerNombre=" + primerNombre + ", segundoNombre=" + segundoNombre + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido + '}';
    }
    
}
