/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public class PeAfiliadoSugerido extends Auditoria implements Serializable {

    public final static int ORIGEN_HOSPITALIZADO = 0;
    public final static int ORIGEN_ANEXO3 = 3;
    public final static int ORIGEN_ANEXO9 = 9;

    public final static int ESTADO_PENDIENTE = 1;
    public final static int ESTADO_MARCADO = 2;
    public final static int ESTADO_RECHAZADO = 3;

    private Integer id;
    private int origen;
    private int estado;
    private String observacion;
    private AsegAfiliado asegAfiliado;
    private AuAnexo3 auAnexos3;
    private AucHospitalizacion aucHospitalizaciones;
    private PePrograma pePrograma;
    private RefAnexo9 refAnexos9;
    private int posicion;
    private Integer origenRechazo;
    private String observacionRechazo;
    private Integer maeTipoAdjunto;
    private String maeTipoAdjuntoValor;
    private List<PeSugeridoAdjunto> listaAdjunto;
    
    public PeAfiliadoSugerido() {
    }

    public PeAfiliadoSugerido(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public AsegAfiliado getAsegAfiliado() {
        return asegAfiliado;
    }

    public void setAsegAfiliado(AsegAfiliado asegAfiliado) {
        this.asegAfiliado = asegAfiliado;
    }

    public AuAnexo3 getAuAnexos3() {
        return auAnexos3;
    }

    public void setAuAnexos3(AuAnexo3 auAnexos3) {
        this.auAnexos3 = auAnexos3;
    }

    public AucHospitalizacion getAucHospitalizaciones() {
        return aucHospitalizaciones;
    }

    public void setAucHospitalizaciones(AucHospitalizacion aucHospitalizaciones) {
        this.aucHospitalizaciones = aucHospitalizaciones;
    }

    public PePrograma getPePrograma() {
        if(pePrograma == null){
            pePrograma = new PePrograma();
        }
        return pePrograma;
    }

    public void setPePrograma(PePrograma pePrograma) {
        this.pePrograma = pePrograma;
    }

    public RefAnexo9 getRefAnexos9() {
        return refAnexos9;
    }

    public void setRefAnexos9(RefAnexo9 refAnexos9) {
        this.refAnexos9 = refAnexos9;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public Integer getOrigenRechazo() {
        return origenRechazo;
    }

    public void setOrigenRechazo(Integer origenRechazo) {
        this.origenRechazo = origenRechazo;
    }

    public String getObservacionRechazo() {
        return observacionRechazo;
    }

    public void setObservacionRechazo(String observacionRechazo) {
        this.observacionRechazo = observacionRechazo;
    }

    public Integer getMaeTipoAdjunto() {
        return maeTipoAdjunto;
    }

    public void setMaeTipoAdjunto(Integer maeTipoAdjunto) {
        this.maeTipoAdjunto = maeTipoAdjunto;
    }

    public String getMaeTipoAdjuntoValor() {
        return maeTipoAdjuntoValor;
    }

    public void setMaeTipoAdjuntoValor(String maeTipoAdjuntoValor) {
        this.maeTipoAdjuntoValor = maeTipoAdjuntoValor;
    }
    
    public List<PeSugeridoAdjunto> getListaAdjunto() {
        return listaAdjunto;
    }

    public void setListaAdjunto(List<PeSugeridoAdjunto> listaAdjunto) {
        this.listaAdjunto = listaAdjunto;
    }
    
    public String getDatosAuditoria() {
        String datos = "";
        if(getAsegAfiliado() == null){
            return datos;
        }
        if (getAsegAfiliado().getPrimerNombre() != null) {
            datos += " " + getAsegAfiliado().getPrimerNombre();
        }
        if (getAsegAfiliado().getSegundoNombre() != null) {
            datos += " " + getAsegAfiliado().getSegundoNombre();
        }
        if (getAsegAfiliado().getPrimerApellido() != null) {
            datos += " " + getAsegAfiliado().getPrimerApellido();
        }
        if (getAsegAfiliado().getSegundoApellido() != null) {
            datos += " " + getAsegAfiliado().getSegundoApellido();
        }
        if (getAsegAfiliado().getMaeTipoDocumentoCodigo() != null) {
            datos += " " + getAsegAfiliado().getMaeTipoDocumentoCodigo();
        }
        if (getAsegAfiliado().getNumeroDocumento() != null) {
            datos += " " + getAsegAfiliado().getNumeroDocumento() + " ";
        }
        return datos;
    }

    @Override
    public String toString() {
        return "PeAfiliadoSugerido{" + "id=" + id + ", origen=" + origen + ", estado=" + estado + ", observacion=" + observacion + ", asegAfiliado=" + asegAfiliado + ", auAnexos3=" + auAnexos3 + ", aucHospitalizaciones=" + aucHospitalizaciones + ", pePrograma=" + pePrograma + ", refAnexos9=" + refAnexos9 + '}';
    }

}
