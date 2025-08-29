/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public class AucIngreso extends Auditoria {

    //Fuente origen
    public static final int FUENTE_ORIGEN_MANUAL = 0;
    public static final int FUENTE_ORIGEN_CARGA = 1;
    public static final int FUENTE_ORIGEN_INTEROPERABILIDAD = 2;

    public static final String MANUAL = "Manual";
    public static final String CARGA = "Censo";
    public static final String INTEROPERABILIDAD = "Interoperabilidad";
    public static final String INDICECHARLSON = "> 3";
    
    private Integer id;
    private Date fechaIngreso;
    private Short ingreso;
    private int maeTipoIngresoId;
    private String maeTipoIngresoCodigo;
    private String maeTipoIngresoValor;
    private int maeCntModalidadId;
    private String maeCntModalidadCodigo;
    private String maeCntModalidadValor;
    private Integer maeRemisionNoPertinenteId;
    private String maeRemisionNoPertinenteCodigo;
    private String maeRemisionNoPertinenteValor;
    private Integer maeServicioInicialId;
    private String descripcion;
    private Integer maeCausaIngresoPrevalenteId;
    private String maeCausaIngresoPrevalenteCodigo;
    private String maeCausaIngresoPrevalenteValor;
    private Integer maeAreaIngresoPrevenibleId;
    private String maeAreaIngresoPrevenibleCodigo;
    private String maeAreaIngresoPrevenibleValor;
    private String descripcionIngresoPrevenible;
    private Integer maeReingresoMotivoId;
    private String maeReingresoMotivoCodigo;
    private String maeReingresoMotivoValor;
    private String maeReingresoMotivoTipo;
    private Short altaTemprana;
    private Integer maeAltaTempranaId;
    private String maeAltaTempranaCodigo;
    private String maeAltaTempranaValor;
    private String maeAltaTempranaTipo;
    private int fuenteOrigen;
    private int habilitarDescripcion;
    private int habilitarDescripcionIngresoPrevenible;
   
    private Integer indiceCharlson;
    private List<AucHospitalizacion> aucHospitalizacionList;
    private List<AucDiagnostico> aucDiagnosticosList;

    //Variables auxiliares
    private Date ultimaFechaIngreso;
    private boolean campoObligatorioMotivoReingreso;
    private boolean campoObligatorioProgramaAltaTemprana;

    public AucIngreso() {
    }

    public AucIngreso(Integer id) {
        this.id = id;
    }

    public AucIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getFechaIngresoStr(){
        String fecha = "";
        if(fechaIngreso != null){
            fecha = fechaIngreso.toString().substring(0, 10);
        }
        return fecha;
    }
    
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Short getIngreso() {
        return ingreso;
    }

    public void setIngreso(Short ingreso) {
        this.ingreso = ingreso;
    }

    public int getMaeTipoIngresoId() {
        return maeTipoIngresoId;
    }

    public void setMaeTipoIngresoId(int maeTipoIngresoId) {
        this.maeTipoIngresoId = maeTipoIngresoId;
    }

    public String getMaeTipoIngresoCodigo() {
        return maeTipoIngresoCodigo;
    }

    public void setMaeTipoIngresoCodigo(String maeTipoIngresoCodigo) {
        this.maeTipoIngresoCodigo = maeTipoIngresoCodigo;
    }

    public String getMaeTipoIngresoValor() {
        return maeTipoIngresoValor;
    }

    public void setMaeTipoIngresoValor(String maeTipoIngresoValor) {
        this.maeTipoIngresoValor = maeTipoIngresoValor;
    }

    public int getMaeCntModalidadId() {
        return maeCntModalidadId;
    }

    public void setMaeCntModalidadId(int maeCntModalidadId) {
        this.maeCntModalidadId = maeCntModalidadId;
    }

    public String getMaeCntModalidadCodigo() {
        return maeCntModalidadCodigo;
    }

    public void setMaeCntModalidadCodigo(String maeCntModalidadCodigo) {
        this.maeCntModalidadCodigo = maeCntModalidadCodigo;
    }

    public String getMaeCntModalidadValor() {
        return maeCntModalidadValor;
    }

    public void setMaeCntModalidadValor(String maeCntModalidadValor) {
        this.maeCntModalidadValor = maeCntModalidadValor;
    }

    public Integer getMaeRemisionNoPertinenteId() {
        return maeRemisionNoPertinenteId;
    }

    public void setMaeRemisionNoPertinenteId(Integer maeRemisionNoPertinenteId) {
        this.maeRemisionNoPertinenteId = maeRemisionNoPertinenteId;
    }

    public String getMaeRemisionNoPertinenteCodigo() {
        return maeRemisionNoPertinenteCodigo;
    }

    public void setMaeRemisionNoPertinenteCodigo(String maeRemisionNoPertinenteCodigo) {
        this.maeRemisionNoPertinenteCodigo = maeRemisionNoPertinenteCodigo;
    }

    public String getMaeRemisionNoPertinenteValor() {
        return maeRemisionNoPertinenteValor;
    }

    public void setMaeRemisionNoPertinenteValor(String maeRemisionNoPertinenteValor) {
        this.maeRemisionNoPertinenteValor = maeRemisionNoPertinenteValor;
    }

    public Integer getIndiceCharlson() {
        return indiceCharlson;
    }

    public void setIndiceCharlson(Integer indiceCharlson) {
        this.indiceCharlson = indiceCharlson;
    }

    public List<AucHospitalizacion> getAucHospitalizacionList() {
        if (aucHospitalizacionList == null) {
            aucHospitalizacionList = new ArrayList<>();
        }
        return aucHospitalizacionList;
    }

    public void setAucHospitalizacionList(List<AucHospitalizacion> aucHospitalizacionList) {
        this.aucHospitalizacionList = aucHospitalizacionList;
    }

    public List<AucDiagnostico> getAucDiagnosticosList() {
        if (aucDiagnosticosList == null) {
            aucDiagnosticosList = new ArrayList<>();
        }
        return aucDiagnosticosList;
    }

    public void setAucDiagnosticosList(List<AucDiagnostico> aucDiagnosticosList) {
        this.aucDiagnosticosList = aucDiagnosticosList;
    }

    public Integer getMaeServicioInicialId() {
        return maeServicioInicialId;
    }

    public void setMaeServicioInicialId(Integer maeServicioInicialId) {
        this.maeServicioInicialId = maeServicioInicialId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getMaeCausaIngresoPrevalenteId() {
        return maeCausaIngresoPrevalenteId;
    }

    public void setMaeCausaIngresoPrevalenteId(Integer maeCausaIngresoPrevalenteId) {
        this.maeCausaIngresoPrevalenteId = maeCausaIngresoPrevalenteId;
    }

    public String getMaeCausaIngresoPrevalenteCodigo() {
        return maeCausaIngresoPrevalenteCodigo;
    }

    public void setMaeCausaIngresoPrevalenteCodigo(String maeCausaIngresoPrevalenteCodigo) {
        this.maeCausaIngresoPrevalenteCodigo = maeCausaIngresoPrevalenteCodigo;
    }

    public String getMaeCausaIngresoPrevalenteValor() {
        return maeCausaIngresoPrevalenteValor;
    }

    public void setMaeCausaIngresoPrevalenteValor(String maeCausaIngresoPrevalenteValor) {
        this.maeCausaIngresoPrevalenteValor = maeCausaIngresoPrevalenteValor;
    }

    public Integer getMaeAreaIngresoPrevenibleId() {
        return maeAreaIngresoPrevenibleId;
    }

    public void setMaeAreaIngresoPrevenibleId(Integer maeAreaIngresoPrevenibleId) {
        this.maeAreaIngresoPrevenibleId = maeAreaIngresoPrevenibleId;
    }

    public String getMaeAreaIngresoPrevenibleCodigo() {
        return maeAreaIngresoPrevenibleCodigo;
    }

    public void setMaeAreaIngresoPrevenibleCodigo(String maeAreaIngresoPrevenibleCodigo) {
        this.maeAreaIngresoPrevenibleCodigo = maeAreaIngresoPrevenibleCodigo;
    }

    public String getMaeAreaIngresoPrevenibleValor() {
        return maeAreaIngresoPrevenibleValor;
    }

    public void setMaeAreaIngresoPrevenibleValor(String maeAreaIngresoPrevenibleValor) {
        this.maeAreaIngresoPrevenibleValor = maeAreaIngresoPrevenibleValor;
    }

    public Integer getMaeReingresoMotivoId() {
        return maeReingresoMotivoId;
    }

    public void setMaeReingresoMotivoId(Integer maeReingresoMotivoId) {
        this.maeReingresoMotivoId = maeReingresoMotivoId;
    }

    public String getMaeReingresoMotivoCodigo() {
        return maeReingresoMotivoCodigo;
    }

    public void setMaeReingresoMotivoCodigo(String maeReingresoMotivoCodigo) {
        this.maeReingresoMotivoCodigo = maeReingresoMotivoCodigo;
    }

    public String getMaeReingresoMotivoValor() {
        return maeReingresoMotivoValor;
    }

    public void setMaeReingresoMotivoValor(String maeReingresoMotivoValor) {
        this.maeReingresoMotivoValor = maeReingresoMotivoValor;
    }

    public String getMaeReingresoMotivoTipo() {
        return maeReingresoMotivoTipo;
    }

    public void setMaeReingresoMotivoTipo(String maeReingresoMotivoTipo) {
        this.maeReingresoMotivoTipo = maeReingresoMotivoTipo;
    }

    public String getDescripcionIngresoPrevenible() {
        return descripcionIngresoPrevenible;
    }

    public void setDescripcionIngresoPrevenible(String descripcionIngresoPrevenible) {
        this.descripcionIngresoPrevenible = descripcionIngresoPrevenible;
    }

    public Short getAltaTemprana() {
        return altaTemprana;
    }

    public void setAltaTemprana(Short altaTemprana) {
        this.altaTemprana = altaTemprana;
    }

    public Integer getMaeAltaTempranaId() {
        return maeAltaTempranaId;
    }

    public void setMaeAltaTempranaId(Integer maeAltaTempranaId) {
        this.maeAltaTempranaId = maeAltaTempranaId;
    }

    public String getMaeAltaTempranaCodigo() {
        return maeAltaTempranaCodigo;
    }

    public void setMaeAltaTempranaCodigo(String maeAltaTempranaCodigo) {
        this.maeAltaTempranaCodigo = maeAltaTempranaCodigo;
    }

    public String getMaeAltaTempranaValor() {
        return maeAltaTempranaValor;
    }

    public void setMaeAltaTempranaValor(String maeAltaTempranaValor) {
        this.maeAltaTempranaValor = maeAltaTempranaValor;
    }

    public String getMaeAltaTempranaTipo() {
        return maeAltaTempranaTipo;
    }

    public void setMaeAltaTempranaTipo(String maeAltaTempranaTipo) {
        this.maeAltaTempranaTipo = maeAltaTempranaTipo;
    }

    public int getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(int fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
    }

    public int getHabilitarDescripcion() {
        return habilitarDescripcion;
    }

    public void setHabilitarDescripcion(int habilitarDescripcion) {
        this.habilitarDescripcion = habilitarDescripcion;
    }

    public int getHabilitarDescripcionIngresoPrevenible() {
        return habilitarDescripcionIngresoPrevenible;
    }

    public void setHabilitarDescripcionIngresoPrevenible(int habilitarDescripcionIngresoPrevenible) {
        this.habilitarDescripcionIngresoPrevenible = habilitarDescripcionIngresoPrevenible;
    }

    public Date getUltimaFechaIngreso() {
        return ultimaFechaIngreso;
    }

    //Metodo auxiliares
    public void setUltimaFechaIngreso(Date ultimaFechaIngreso) {
        this.ultimaFechaIngreso = ultimaFechaIngreso;
    }

    public boolean isCampoObligatorioMotivoReingreso() {
        return campoObligatorioMotivoReingreso;
    }

    public void setCampoObligatorioMotivoReingreso(boolean campoObligatorioMotivoReingreso) {
        this.campoObligatorioMotivoReingreso = campoObligatorioMotivoReingreso;
    }

    public boolean isCampoObligatorioProgramaAltaTemprana() {
        return campoObligatorioProgramaAltaTemprana;
    }

    public void setCampoObligatorioProgramaAltaTemprana(boolean campoObligatorioProgramaAltaTemprana) {
        this.campoObligatorioProgramaAltaTemprana = campoObligatorioProgramaAltaTemprana;
    }
    
    public String getAltaTanempranaStr() {
        String altaTempranaStr = "";
        if(altaTemprana != null){
            if (altaTemprana == 0) {
                altaTempranaStr = "No";
            }else{
                altaTempranaStr = "Si";
            }
        }
        return altaTempranaStr;
    }
    
    public String getIngresoStr() {
        String ingresoStr = "";
        if(ingreso != null){
            if (ingreso == 0) {
                ingresoStr = "Sí";
            }else{
                ingresoStr = "No";
            }
        }
        return ingresoStr;
    }

    public String getFuenteOrigenStr() {
        switch (getFuenteOrigen()) {
            case FUENTE_ORIGEN_MANUAL:
                return MANUAL;
            case FUENTE_ORIGEN_CARGA:
                return CARGA;
            case FUENTE_ORIGEN_INTEROPERABILIDAD:
                return INTEROPERABILIDAD;
            default:
                return "";
        }
    }
    
    public String getIndiceCharlsonStr() {
        String indice = "";
        if(indiceCharlson != null){
            switch (indiceCharlson) {
                case 0:
                    indice = String.valueOf(indiceCharlson);
                    break;
                case 1:
                    indice = String.valueOf(indiceCharlson);
                    break;
                case 2:
                    indice = String.valueOf(indiceCharlson);
                    break;
                case 3:
                    indice = String.valueOf(indiceCharlson);
                    break;
                case 4:
                    indice = INDICECHARLSON;
                    break;
            }
        }
        return indice;
    }
    
    public String getAltaTempranaHospitalizado(int estadoHopitalizacion){
        String altaTempranaStr = "";
        if(estadoHopitalizacion == AucHospitalizacion.ESTADO_AFILIADO_HOSPITALIZADO){
            altaTempranaStr = maeAltaTempranaValor;
        }
        return altaTempranaStr;
    }
    
    @Override
    public String toString() {
        return "AucIngreso{" + "id=" + id + ", fechaIngreso=" + fechaIngreso + ", ingreso=" + ingreso + ", maeTipoIngresoId=" + maeTipoIngresoId + ", maeTipoIngresoCodigo=" + maeTipoIngresoCodigo + ", maeTipoIngresoValor=" + maeTipoIngresoValor + ", maeCntModalidadId=" + maeCntModalidadId + ", maeCntModalidadCodigo=" + maeCntModalidadCodigo + ", maeCntModalidadValor=" + maeCntModalidadValor + ", maeRemisionNoPertinenteId=" + maeRemisionNoPertinenteId + ", maeRemisionNoPertinenteCodigo=" + maeRemisionNoPertinenteCodigo + ", maeRemisionNoPertinenteValor=" + maeRemisionNoPertinenteValor + ", maeServicioInicialId=" + maeServicioInicialId + ", descripcion=" + descripcion + ", fuenteOrigen=" + fuenteOrigen + ", habilitarDescripcion=" + habilitarDescripcion + ", indiceCharlson=" + indiceCharlson + ", ultimaFechaIngreso=" + ultimaFechaIngreso + '}';
    }
}
