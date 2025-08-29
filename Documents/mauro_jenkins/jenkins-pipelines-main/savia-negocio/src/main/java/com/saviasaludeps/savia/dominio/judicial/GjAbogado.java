package com.saviasaludeps.savia.dominio.judicial;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author bsteven_gomez
 */
public class GjAbogado extends Auditoria {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private int maeTipoDocumentoId;
    private String maeTipoDocumentoCodigo;
    private String maeTipoDocumentoValor;
    private String documento;
    private String tarjetaProfecional;
    private String nombre;
    private Short tipo;
    private String tipoAbogado;

    private Usuario gnUsuario;
    //aux
    private Integer gnUsuarioId;
    private Boolean esAfiliado;
    private GjAbogado gjPersona;

    public GjAbogado() {
    }

    public GjAbogado(Integer id, int maeTipoDocumentoId, String maeTipoDocumentoCodigo, String maeTipoDocumentoValor, String documento, String tarjetaProfecional, String nombre, Short tipo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea, String usuarioModifica, String terminalModifica, Date fechaHoraModifica, Usuario gnUsuario, Integer gnUsuarioId) {
        this.id = id;
        this.maeTipoDocumentoId = maeTipoDocumentoId;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
        this.documento = documento;
        this.tarjetaProfecional = tarjetaProfecional;
        this.nombre = nombre;
        this.tipo = tipo;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
        this.usuarioModifica = usuarioModifica;
        this.terminalModifica = terminalModifica;
        this.fechaHoraModifica = fechaHoraModifica;
        this.gnUsuario = gnUsuario;
        this.gnUsuarioId = gnUsuarioId;
    }

    public GjAbogado(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(int maeTipoDocumentoId) {
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTarjetaProfecional() {
        return tarjetaProfecional;
    }

    public void setTarjetaProfecional(String tarjetaProfecional) {
        this.tarjetaProfecional = tarjetaProfecional;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Short getTipo() {
        return tipo;
    }

    public void setTipo(Short tipo) {
        this.tipo = tipo;
    }

    public String getTipoAbogado() {
        if (getTipo() != null) {
            switch (this.getTipo()) {                
                case 0:
                    tipoAbogado = "Planta";
                    break;
                case 1:
                    tipoAbogado = "Contratista";
                    break;
            }
        }else{
            tipoAbogado = "";
        }
        return tipoAbogado;
    }

    public Usuario getGnUsuario() {
        if (gnUsuario == null) {
            gnUsuario = new Usuario();
        }
        return gnUsuario;
    }

    public void setGnUsuario(Usuario gnUsuario) {
        this.gnUsuario = gnUsuario;
    }

    public Integer getGnUsuarioId() {
        return gnUsuarioId;
    }

    public void setGnUsuarioId(Integer gnUsuarioId) {
        this.gnUsuarioId = gnUsuarioId;
    }

    public Boolean getEsAfiliado() {
        return esAfiliado;
    }

    public void setEsAfiliado(Boolean esAfiliado) {
        this.esAfiliado = esAfiliado;
    }

    public GjAbogado getGjPersona() {
        if (gjPersona == null) {
            gjPersona = new GjAbogado();

        }
        return gjPersona;
    }

    public void setGjPersona(GjAbogado gjPersona) {
        this.gjPersona = gjPersona;
    }

    public boolean exiteAbogado() {
        return this.getId() != null && this.getId() > 0;
    }

    public boolean exitePersona() {
        return this.getId() != null && this.getId() > 0; //aca deberia traernos el id y deberian ser iguales 
    }

    @Override
    public String toString() {
        return "GjAbogado{" + "id=" + id + ", maeTipoDocumentoId=" + maeTipoDocumentoId + ", maeTipoDocumentoCodigo=" + maeTipoDocumentoCodigo + ", maeTipoDocumentoValor=" + maeTipoDocumentoValor + ", documento=" + documento + ", tarjetaProfecional=" + tarjetaProfecional + ", nombre=" + nombre + ", tipo=" + tipo + ", usuarioCrea=" + usuarioCrea + ", terminalCrea=" + terminalCrea + ", fechaHoraCrea=" + fechaHoraCrea + ", usuarioModifica=" + usuarioModifica + ", terminalModifica=" + terminalModifica + ", fechaHoraModifica=" + fechaHoraModifica + ", gnUsuario=" + gnUsuario + '}';
    }
}
