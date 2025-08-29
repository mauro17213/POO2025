/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.administracion;

import com.saviasaludeps.savia.dominio.autorizacion.AuGrupo;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public class Usuario extends Auditoria {

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(Integer id, String usuario, String nombre) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
    }

    private Integer id;
    private Empresa empresa = null;
    private String usuario = "";
    private String nombre = "";
    private String correoElectronico = "";
    private String contrasena = "";
    private String contrasenaNueva1 = "";
    private String contrasenaNueva2 = "";
    private String celular = "";
    private String telefono = "";
    private Date fechaInicio = null;
    private Date fechaFin = null;
    private boolean activo = true;
    private Date fechaUltimoIngreso;
    private int intentos = 0;
    private boolean bloqueado = false;
    private Integer maeTipoDocumentoId = null;
    private String maeTipoDocumentoCodigo = null;
    private String maeTipoDocumentoValor = null;
    private String documento;
    private Integer maeAreaId = null;
    private String maeAreaCodigo = null;
    private String maeAreaValor = null;
    private Integer maeCargoId = null;
    private String maeCargoCodigo = null;
    private String maeCargoValor = null;    
    private boolean restaurarContrasegna = false;
    private Date fechaUltimaContrasegna =  null;
    private Date fechaRestaurarContrasegna = null;
    private AuGrupo auGrupoId = null;
    private GnZonaHoraria gnZonaHorariaId = null;
    private Integer gnSedeTurno;

    private String correo = "";
    private String encabezado = "";
    private String mensaje = "";
    private List<UsuarioRol> listaRoles;
    private Integer sesiones;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuarioNombre() {
        if ((usuario != null && !usuario.equals("")) && (nombre != null && !nombre.equals(""))) {
            return usuario + " (" + nombre + ")";
        } else if (usuario != null && !usuario.equals("")) {
            return " (" + nombre + ")";
        } else if (nombre != null && !nombre.equals("")) {
            return usuario;
        } else {
            return "";
        }
    }
    
     public String getNombreUsuario() {
        if ((usuario != null && !usuario.equals("")) && (nombre != null && !nombre.equals(""))) {
            return nombre + " - " + usuario;
        } else if (usuario != null && !usuario.equals("")) {
            return nombre ;
        } else if (nombre != null && !nombre.equals("")) {
            return usuario;
        } else {
            return "";
        }
    }
    
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getContrasenaNueva1() {
        return contrasenaNueva1;
    }

    public void setContrasenaNueva1(String contrasenaNueva1) {
        this.contrasenaNueva1 = contrasenaNueva1;
    }

    public String getContrasenaNueva2() {
        return contrasenaNueva2;
    }

    public void setContrasenaNueva2(String contrasenaNueva2) {
        this.contrasenaNueva2 = contrasenaNueva2;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isActivo() {
        return activo;
    }

    public String getActivoStr() {
        return (activo) ? "SI" : "NO";
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date getFechaUltimoIngreso() {
        return fechaUltimoIngreso;
    }

    public void setFechaUltimoIngreso(Date fechaUltimoIngreso) {
        this.fechaUltimoIngreso = fechaUltimoIngreso;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<UsuarioRol> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<UsuarioRol> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public String getBloqueadoStr() {
        return (bloqueado) ? "SI" : "NO";
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getMaeAreaId() {
        return maeAreaId;
    }

    public void setMaeAreaId(Integer maeAreaId) {
        this.maeAreaId = maeAreaId;
    }

    public String getMaeAreaCodigo() {
        return maeAreaCodigo;
    }

    public void setMaeAreaCodigo(String maeAreaCodigo) {
        this.maeAreaCodigo = maeAreaCodigo;
    }

    public String getMaeAreaValor() {
        return maeAreaValor;
    }

    public void setMaeAreaValor(String maeAreaValor) {
        this.maeAreaValor = maeAreaValor;
    }

    public Integer getMaeCargoId() {
        return maeCargoId;
    }

    public void setMaeCargoId(Integer maeCargoId) {
        this.maeCargoId = maeCargoId;
    }

    public String getMaeCargoCodigo() {
        return maeCargoCodigo;
    }

    public void setMaeCargoCodigo(String maeCargoCodigo) {
        this.maeCargoCodigo = maeCargoCodigo;
    }

    public String getMaeCargoValor() {
        if (maeCargoValor == null) {
            return "";
        }
        return maeCargoValor;
    }

    public void setMaeCargoValor(String maeCargoValor) {
        this.maeCargoValor = maeCargoValor;
    }

    public boolean isRestaurarContrasegna() {
        return restaurarContrasegna;
    }

    public void setRestaurarContrasegna(boolean restaurarContrasegna) {
        this.restaurarContrasegna = restaurarContrasegna;
    }

    public Date getFechaUltimaContrasegna() {
        return fechaUltimaContrasegna;
    }

    public void setFechaUltimaContrasegna(Date fechaUltimaContrasegna) {
        this.fechaUltimaContrasegna = fechaUltimaContrasegna;
    }

    public Date getFechaRestaurarContrasegna() {
        return fechaRestaurarContrasegna;
    }

    public void setFechaRestaurarContrasegna(Date fechaRestaurarContrasegna) {
        this.fechaRestaurarContrasegna = fechaRestaurarContrasegna;
    }

    public AuGrupo getAuGrupoId() {
        return auGrupoId;
    }

    public void setAuGrupoId(AuGrupo auGrupoId) {
        this.auGrupoId = auGrupoId;
    }

    public GnZonaHoraria getGnZonaHorariaId() {
        return gnZonaHorariaId;
    }

    public void setGnZonaHorariaId(GnZonaHoraria gnZonaHorariaId) {
        this.gnZonaHorariaId = gnZonaHorariaId;
    }

    public Integer getSesiones() {
        return sesiones;
    }

    public void setSesiones(Integer sesiones) {
        this.sesiones = sesiones;
    }

    public Integer getGnSedeTurno() {
        return gnSedeTurno;
    }

    public void setGnSedeTurno(Integer gnSedeTurno) {
        this.gnSedeTurno = gnSedeTurno;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", empresa=" + empresa + ", usuario=" + usuario + ", nombre=" + nombre + ", correoElectronico=" + correoElectronico + ", contrasena=" + contrasena + ", contrasenaNueva1=" + contrasenaNueva1 + ", contrasenaNueva2=" + contrasenaNueva2 + ", celular=" + celular + ", telefono=" + telefono + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", activo=" + activo + ", fechaUltimoIngreso=" + fechaUltimoIngreso + ", intentos=" + intentos + ", bloqueado=" + bloqueado + ", maeTipoDocumentoId=" + maeTipoDocumentoId + ", maeTipoDocumentoCodigo=" + maeTipoDocumentoCodigo + ", maeTipoDocumentoValor=" + maeTipoDocumentoValor + ", documento=" + documento + ", maeAreaId=" + maeAreaId + ", maeAreaCodigo=" + maeAreaCodigo + ", maeAreaValor=" + maeAreaValor + ", maeCargoId=" + maeCargoId + ", maeCargoCodigo=" + maeCargoCodigo + ", maeCargoValor=" + maeCargoValor + ", restaurarContrasegna=" + restaurarContrasegna + ", fechaUltimaContrasegna=" + fechaUltimaContrasegna + ", fechaRestaurarContrasegna=" + fechaRestaurarContrasegna + ", listaRoles=" + listaRoles + '}';
    }

    public String toStringCorto() {
        return "Usuario{" + "id=" + id + ", usuario=" + usuario + ", nombre=" + nombre + '}';
    }

    public Object getObjetoRcoGrupoUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
