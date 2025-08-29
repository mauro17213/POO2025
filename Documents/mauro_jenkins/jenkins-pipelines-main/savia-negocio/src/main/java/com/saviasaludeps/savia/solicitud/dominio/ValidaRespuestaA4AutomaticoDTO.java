package com.saviasaludeps.savia.solicitud.dominio;

import java.io.Serializable;

public class ValidaRespuestaA4AutomaticoDTO implements Serializable {

    public final static int CODIGO_ESTADO_AUTORIZADO = 0;
    public final static int CODIGO_ESTADO_ITEM_PENDIENTE = 1;
    public final static int CODIGO_ESTADO_ITEM_RECHAZADO = 2;
    public final static int CODIGO_ESTADO_ERROR = 3;

    private int codigo;
    private String mensaje;
    private int cntPrestadorAutorizadoSedeId;
    private int cntContratoAutorizadoSedeId;
    private String valorContratado;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getCntPrestadorAutorizadoSedeId() {
        return cntPrestadorAutorizadoSedeId;
    }

    public void setCntPrestadorAutorizadoSedeId(int cntPrestadorAutorizadoSedeId) {
        this.cntPrestadorAutorizadoSedeId = cntPrestadorAutorizadoSedeId;
    }

    public int getCntContratoAutorizadoSedeId() {
        return cntContratoAutorizadoSedeId;
    }

    public void setCntContratoAutorizadoSedeId(int cntContratoAutorizadoSedeId) {
        this.cntContratoAutorizadoSedeId = cntContratoAutorizadoSedeId;
    }

    public String getValorContratado() {
        return valorContratado;
    }

    public void setValorContratado(String valorContratado) {
        this.valorContratado = valorContratado;
    }
    
    @Override
    public String toString() {
        return "ValidaRespuestaA4AutomaticoDTO{" + "codigo=" + codigo + ", mensaje=" + mensaje + ", cntPrestadorAutorizadoSedeId=" + cntPrestadorAutorizadoSedeId + ", cntContratoAutorizadoSedeId=" + cntContratoAutorizadoSedeId + '}';
    }
    
}
