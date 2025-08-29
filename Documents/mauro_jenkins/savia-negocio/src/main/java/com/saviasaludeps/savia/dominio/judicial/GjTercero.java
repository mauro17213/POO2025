/**
 *
 * @author bsteven_gomez
 */
package com.saviasaludeps.savia.dominio.judicial;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

public class GjTercero extends Auditoria {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer asegAfiliadoId;
    private Integer cntPrestadorId;
    private Short tipo;
    private String tipoTercero;
    private int maeTipoDocumentoId;
    private String maeTipoDocumentoCodigo;
    private String maeTipoDocumentoValor;
    private String documento;
    private String nombres;
    private String apellidos;
    private String razonSocial;
    private String telefono;
    private Ubicacion gnUbicacionesId;
    private String direccion;
    private String correoElectronico;

    private Boolean esAfiliado;
    private Boolean esPrestador;
    private Boolean esTercero;
    private Ubicacion ubicacionAfiliadoId;

    private GjTercero gjPersona;
    private AsegAfiliado asegAfiliado;
    private CntPrestador cntPrestador;
     private GjTerceroContacto gjTerceroContacto;

    public GjTercero() {
    }

    public GjTercero(Integer id) {
        this.id = id;
    }

    public GjTercero(Integer id, Short tipo, String maeTipoDocumentoValor, String documento, String nombres, String apellidos, String razonSocial, String telefono, String correoElectronico) {
        this.id = id;
        this.tipo = tipo;
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
        this.documento = documento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.razonSocial = razonSocial;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAsegAfiliadoId() {
        return asegAfiliadoId;
    }

    public void setAsegAfiliadoId(Integer asegAfiliadoId) {
        this.asegAfiliadoId = asegAfiliadoId;
    }

    public Integer getCntPrestadorId() {
        return cntPrestadorId;
    }

    public void setCntPrestadorId(Integer cntPrestadorId) {
        this.cntPrestadorId = cntPrestadorId;
    }

    public Short getTipo() {
        return tipo;
    }

    public void setTipo(Short tipo) {
        this.tipo = tipo;
    }

    public String getTipoTercero() {
        if (getTipo() >= 0) {
            switch (this.getTipo()) {              
                case 0:
                    tipoTercero = "Externo";
                    break;
                case 1:
                    tipoTercero = "Afiliado";
                    break;
                case 2:
                    tipoTercero = "Prestador";
                    break;
                case 3:
                    tipoTercero = "Empleado";
                    break;
                case 4:
                    tipoTercero = "Exempleado";
                    break;

            }
        }
        return tipoTercero;
    }

    public void setTipoTercero(String tipoTercero) {
        this.tipoTercero = tipoTercero;
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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Ubicacion getGnUbicacionesId() {
        return gnUbicacionesId;
    }

    public void setGnUbicacionesId(Ubicacion gnUbicacionesId) {
        this.gnUbicacionesId = gnUbicacionesId;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public GjTercero getGjPersona() {
        if (gjPersona == null) {
            gjPersona = new GjTercero();
        }
        return gjPersona;
    }

    public void setGjPersona(GjTercero gjPersona) {
        this.gjPersona = gjPersona;
    }

    public AsegAfiliado getAsegAfiliado() {
        if (asegAfiliado == null) {
            asegAfiliado = new AsegAfiliado();
        }
        return asegAfiliado;
    }

    public void setAsegAfiliado(AsegAfiliado asegAfiliado) {
        this.asegAfiliado = asegAfiliado;
    }

    public CntPrestador getCntPrestador() {
        if (cntPrestador == null) {
            cntPrestador = new CntPrestador();
        }
        return cntPrestador;
    }

    public void setCntPrestador(CntPrestador cntPrestador) {
        this.cntPrestador = cntPrestador;
    }
    
    public GjTerceroContacto getGjTerceroContacto() {
         if (gjTerceroContacto == null) {
            gjTerceroContacto = new GjTerceroContacto();
        }
        return gjTerceroContacto;
    }

    public void setGjTerceroContacto(GjTerceroContacto gjTerceroContacto) {
        this.gjTerceroContacto = gjTerceroContacto;
    }

    public Boolean getEsAfiliado() {
        return esAfiliado;
    }

    public void setEsAfiliado(Boolean esAfiliado) {
        this.esAfiliado = esAfiliado;
    }

    public Boolean getEsPrestador() {
        return esPrestador;
    }

    public void setEsPrestador(Boolean esPrestador) {
        this.esPrestador = esPrestador;
    }

    public Boolean getEsTercero() {
        return esTercero;
    }

    public void setEsTercero(Boolean esTercero) {
        this.esTercero = esTercero;
    }

    public Ubicacion getUbicacionAfiliadoId() {
        return ubicacionAfiliadoId;
    }

    public void setUbicacionAfiliadoId(Ubicacion ubicacionAfiliadoId) {
        this.ubicacionAfiliadoId = ubicacionAfiliadoId;
    }

    public boolean exitePersona() {
        return this.getId() != null && this.getId() > 0;
    }

    public boolean exiteTercero() {
        return this.getId() != null && this.getId() > 0;
    }

    @Override
    public String toString() {
        return "GjTercero{" + "id=" + id + ", tipo=" + tipo + ", maeTipoDocumentoId=" + maeTipoDocumentoId + ", maeTipoDocumentoCodigo=" + maeTipoDocumentoCodigo + ", maeTipoDocumentoValor=" + maeTipoDocumentoValor + ", documento=" + documento + ", nombres=" + nombres + ", apellidos=" + apellidos + ", razonSocial=" + razonSocial + ", telefono=" + telefono + ", gnUbicacionesId=" + gnUbicacionesId + ", direccion=" + direccion + ", correoElectronico=" + correoElectronico + ", usuarioCrea=" + usuarioCrea + ", terminalCrea=" + terminalCrea + ", fechaHoraCrea=" + fechaHoraCrea + ", usuarioModifica=" + usuarioModifica + ", terminalModifica=" + terminalModifica + ", fechaHoraModifica=" + fechaHoraModifica + ", esAfiliado=" + esAfiliado + ", esPrestador=" + esPrestador + ", ubicacionAfiliadoId=" + ubicacionAfiliadoId + ", asegAfiliado=" + asegAfiliado + ", cntPrestador=" + cntPrestador + '}';
    }

}
