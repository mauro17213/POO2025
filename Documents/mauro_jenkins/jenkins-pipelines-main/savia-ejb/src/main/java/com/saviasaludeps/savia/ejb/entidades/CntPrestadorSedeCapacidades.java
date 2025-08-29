/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "cnt_prestador_sede_capacidades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntPrestadorSedeCapacidades.findAll", query = "SELECT c FROM CntPrestadorSedeCapacidades c"),
    @NamedQuery(name = "CntPrestadorSedeCapacidades.findById", query = "SELECT c FROM CntPrestadorSedeCapacidades c WHERE c.id = :id"),
    @NamedQuery(name = "CntPrestadorSedeCapacidades.findByMaeUnidadCapacidadId", query = "SELECT c FROM CntPrestadorSedeCapacidades c WHERE c.maeUnidadCapacidadId = :maeUnidadCapacidadId"),
    @NamedQuery(name = "CntPrestadorSedeCapacidades.findByMaeUnidadCapacidadCodigo", query = "SELECT c FROM CntPrestadorSedeCapacidades c WHERE c.maeUnidadCapacidadCodigo = :maeUnidadCapacidadCodigo"),
    @NamedQuery(name = "CntPrestadorSedeCapacidades.findByMaeUnidadCapacidadValor", query = "SELECT c FROM CntPrestadorSedeCapacidades c WHERE c.maeUnidadCapacidadValor = :maeUnidadCapacidadValor"),
    @NamedQuery(name = "CntPrestadorSedeCapacidades.findByMaeGrupoCapacidadId", query = "SELECT c FROM CntPrestadorSedeCapacidades c WHERE c.maeGrupoCapacidadId = :maeGrupoCapacidadId"),
    @NamedQuery(name = "CntPrestadorSedeCapacidades.findByMaeGrupoCapacidadCodigo", query = "SELECT c FROM CntPrestadorSedeCapacidades c WHERE c.maeGrupoCapacidadCodigo = :maeGrupoCapacidadCodigo"),
    @NamedQuery(name = "CntPrestadorSedeCapacidades.findByMaeGrupoCapacidadValor", query = "SELECT c FROM CntPrestadorSedeCapacidades c WHERE c.maeGrupoCapacidadValor = :maeGrupoCapacidadValor"),
    @NamedQuery(name = "CntPrestadorSedeCapacidades.findByCantidad", query = "SELECT c FROM CntPrestadorSedeCapacidades c WHERE c.cantidad = :cantidad"),
    @NamedQuery(name = "CntPrestadorSedeCapacidades.findByAplicaEse", query = "SELECT c FROM CntPrestadorSedeCapacidades c WHERE c.aplicaEse = :aplicaEse"),
    @NamedQuery(name = "CntPrestadorSedeCapacidades.findByInformaNit", query = "SELECT c FROM CntPrestadorSedeCapacidades c WHERE c.informaNit = :informaNit"),
    @NamedQuery(name = "CntPrestadorSedeCapacidades.findByInformaCodigoHabilitacion", query = "SELECT c FROM CntPrestadorSedeCapacidades c WHERE c.informaCodigoHabilitacion = :informaCodigoHabilitacion"),
    @NamedQuery(name = "CntPrestadorSedeCapacidades.findByInformaNombreSede", query = "SELECT c FROM CntPrestadorSedeCapacidades c WHERE c.informaNombreSede = :informaNombreSede"),
    @NamedQuery(name = "CntPrestadorSedeCapacidades.findByInformaDireccion", query = "SELECT c FROM CntPrestadorSedeCapacidades c WHERE c.informaDireccion = :informaDireccion"),
    @NamedQuery(name = "CntPrestadorSedeCapacidades.findByInformaCorreoElectronico", query = "SELECT c FROM CntPrestadorSedeCapacidades c WHERE c.informaCorreoElectronico = :informaCorreoElectronico"),
    @NamedQuery(name = "CntPrestadorSedeCapacidades.findByInformaTelefono", query = "SELECT c FROM CntPrestadorSedeCapacidades c WHERE c.informaTelefono = :informaTelefono"),
    @NamedQuery(name = "CntPrestadorSedeCapacidades.findByInformaNivel", query = "SELECT c FROM CntPrestadorSedeCapacidades c WHERE c.informaNivel = :informaNivel"),
    @NamedQuery(name = "CntPrestadorSedeCapacidades.findByUsuarioCrea", query = "SELECT c FROM CntPrestadorSedeCapacidades c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntPrestadorSedeCapacidades.findByTerminalCrea", query = "SELECT c FROM CntPrestadorSedeCapacidades c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntPrestadorSedeCapacidades.findByFechaHoraCrea", query = "SELECT c FROM CntPrestadorSedeCapacidades c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntPrestadorSedeCapacidades.findByUsuarioModifica", query = "SELECT c FROM CntPrestadorSedeCapacidades c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntPrestadorSedeCapacidades.findByTerminalModifica", query = "SELECT c FROM CntPrestadorSedeCapacidades c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntPrestadorSedeCapacidades.findByFechaHoraModifica", query = "SELECT c FROM CntPrestadorSedeCapacidades c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntPrestadorSedeCapacidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_unidad_capacidad_id")
    private int maeUnidadCapacidadId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "mae_unidad_capacidad_codigo")
    private String maeUnidadCapacidadCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "mae_unidad_capacidad_valor")
    private String maeUnidadCapacidadValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_grupo_capacidad_id")
    private int maeGrupoCapacidadId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "mae_grupo_capacidad_codigo")
    private String maeGrupoCapacidadCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "mae_grupo_capacidad_valor")
    private String maeGrupoCapacidadValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_ese")
    private boolean aplicaEse;
    @Size(max = 32)
    @Column(name = "informa_nit")
    private String informaNit;
    @Size(max = 16)
    @Column(name = "informa_codigo_habilitacion")
    private String informaCodigoHabilitacion;
    @Size(max = 256)
    @Column(name = "informa_nombre_sede")
    private String informaNombreSede;
    @Size(max = 256)
    @Column(name = "informa_direccion")
    private String informaDireccion;
    @Size(max = 64)
    @Column(name = "informa_correo_electronico")
    private String informaCorreoElectronico;
    @Size(max = 64)
    @Column(name = "informa_telefono")
    private String informaTelefono;
    @Column(name = "informa_nivel")
    private Integer informaNivel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;

    public CntPrestadorSedeCapacidades() {
    }

    public CntPrestadorSedeCapacidades(Integer id) {
        this.id = id;
    }

    public CntPrestadorSedeCapacidades(Integer id, int maeUnidadCapacidadId, String maeUnidadCapacidadCodigo, String maeUnidadCapacidadValor, int maeGrupoCapacidadId, String maeGrupoCapacidadCodigo, String maeGrupoCapacidadValor, int cantidad, boolean aplicaEse, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeUnidadCapacidadId = maeUnidadCapacidadId;
        this.maeUnidadCapacidadCodigo = maeUnidadCapacidadCodigo;
        this.maeUnidadCapacidadValor = maeUnidadCapacidadValor;
        this.maeGrupoCapacidadId = maeGrupoCapacidadId;
        this.maeGrupoCapacidadCodigo = maeGrupoCapacidadCodigo;
        this.maeGrupoCapacidadValor = maeGrupoCapacidadValor;
        this.cantidad = cantidad;
        this.aplicaEse = aplicaEse;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeUnidadCapacidadId() {
        return maeUnidadCapacidadId;
    }

    public void setMaeUnidadCapacidadId(int maeUnidadCapacidadId) {
        this.maeUnidadCapacidadId = maeUnidadCapacidadId;
    }

    public String getMaeUnidadCapacidadCodigo() {
        return maeUnidadCapacidadCodigo;
    }

    public void setMaeUnidadCapacidadCodigo(String maeUnidadCapacidadCodigo) {
        this.maeUnidadCapacidadCodigo = maeUnidadCapacidadCodigo;
    }

    public String getMaeUnidadCapacidadValor() {
        return maeUnidadCapacidadValor;
    }

    public void setMaeUnidadCapacidadValor(String maeUnidadCapacidadValor) {
        this.maeUnidadCapacidadValor = maeUnidadCapacidadValor;
    }

    public int getMaeGrupoCapacidadId() {
        return maeGrupoCapacidadId;
    }

    public void setMaeGrupoCapacidadId(int maeGrupoCapacidadId) {
        this.maeGrupoCapacidadId = maeGrupoCapacidadId;
    }

    public String getMaeGrupoCapacidadCodigo() {
        return maeGrupoCapacidadCodigo;
    }

    public void setMaeGrupoCapacidadCodigo(String maeGrupoCapacidadCodigo) {
        this.maeGrupoCapacidadCodigo = maeGrupoCapacidadCodigo;
    }

    public String getMaeGrupoCapacidadValor() {
        return maeGrupoCapacidadValor;
    }

    public void setMaeGrupoCapacidadValor(String maeGrupoCapacidadValor) {
        this.maeGrupoCapacidadValor = maeGrupoCapacidadValor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean getAplicaEse() {
        return aplicaEse;
    }

    public void setAplicaEse(boolean aplicaEse) {
        this.aplicaEse = aplicaEse;
    }

    public String getInformaNit() {
        return informaNit;
    }

    public void setInformaNit(String informaNit) {
        this.informaNit = informaNit;
    }

    public String getInformaCodigoHabilitacion() {
        return informaCodigoHabilitacion;
    }

    public void setInformaCodigoHabilitacion(String informaCodigoHabilitacion) {
        this.informaCodigoHabilitacion = informaCodigoHabilitacion;
    }

    public String getInformaNombreSede() {
        return informaNombreSede;
    }

    public void setInformaNombreSede(String informaNombreSede) {
        this.informaNombreSede = informaNombreSede;
    }

    public String getInformaDireccion() {
        return informaDireccion;
    }

    public void setInformaDireccion(String informaDireccion) {
        this.informaDireccion = informaDireccion;
    }

    public String getInformaCorreoElectronico() {
        return informaCorreoElectronico;
    }

    public void setInformaCorreoElectronico(String informaCorreoElectronico) {
        this.informaCorreoElectronico = informaCorreoElectronico;
    }

    public String getInformaTelefono() {
        return informaTelefono;
    }

    public void setInformaTelefono(String informaTelefono) {
        this.informaTelefono = informaTelefono;
    }

    public Integer getInformaNivel() {
        return informaNivel;
    }

    public void setInformaNivel(Integer informaNivel) {
        this.informaNivel = informaNivel;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public String getTerminalModifica() {
        return terminalModifica;
    }

    public void setTerminalModifica(String terminalModifica) {
        this.terminalModifica = terminalModifica;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    public CntPrestadorSedes getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSedes cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CntPrestadorSedeCapacidades)) {
            return false;
        }
        CntPrestadorSedeCapacidades other = (CntPrestadorSedeCapacidades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedeCapacidades[ id=" + id + " ]";
    }
    
}
