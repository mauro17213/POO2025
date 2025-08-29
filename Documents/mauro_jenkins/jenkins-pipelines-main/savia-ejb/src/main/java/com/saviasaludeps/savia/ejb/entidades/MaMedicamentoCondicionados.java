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
@Table(name = "ma_medicamento_condicionados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaMedicamentoCondicionados.findAll", query = "SELECT m FROM MaMedicamentoCondicionados m"),
    @NamedQuery(name = "MaMedicamentoCondicionados.findById", query = "SELECT m FROM MaMedicamentoCondicionados m WHERE m.id = :id"),
    @NamedQuery(name = "MaMedicamentoCondicionados.findByCodigo", query = "SELECT m FROM MaMedicamentoCondicionados m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "MaMedicamentoCondicionados.findByNombre", query = "SELECT m FROM MaMedicamentoCondicionados m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MaMedicamentoCondicionados.findByEdadMinima", query = "SELECT m FROM MaMedicamentoCondicionados m WHERE m.edadMinima = :edadMinima"),
    @NamedQuery(name = "MaMedicamentoCondicionados.findByEdadMaxima", query = "SELECT m FROM MaMedicamentoCondicionados m WHERE m.edadMaxima = :edadMaxima"),
    @NamedQuery(name = "MaMedicamentoCondicionados.findByMaeGeneroId", query = "SELECT m FROM MaMedicamentoCondicionados m WHERE m.maeGeneroId = :maeGeneroId"),
    @NamedQuery(name = "MaMedicamentoCondicionados.findByMaeGeneroCodigo", query = "SELECT m FROM MaMedicamentoCondicionados m WHERE m.maeGeneroCodigo = :maeGeneroCodigo"),
    @NamedQuery(name = "MaMedicamentoCondicionados.findByMaeGeneroValor", query = "SELECT m FROM MaMedicamentoCondicionados m WHERE m.maeGeneroValor = :maeGeneroValor"),
    @NamedQuery(name = "MaMedicamentoCondicionados.findByUsuarioCrea", query = "SELECT m FROM MaMedicamentoCondicionados m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MaMedicamentoCondicionados.findByTerminalCrea", query = "SELECT m FROM MaMedicamentoCondicionados m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MaMedicamentoCondicionados.findByFechaHoraCrea", query = "SELECT m FROM MaMedicamentoCondicionados m WHERE m.fechaHoraCrea = :fechaHoraCrea")})
public class MaMedicamentoCondicionados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "edad_minima")
    private Integer edadMinima;
    @Column(name = "edad_maxima")
    private Integer edadMaxima;
    @Column(name = "mae_genero_id")
    private Integer maeGeneroId;
    @Size(max = 8)
    @Column(name = "mae_genero_codigo")
    private String maeGeneroCodigo;
    @Size(max = 128)
    @Column(name = "mae_genero_valor")
    private String maeGeneroValor;
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
    @JoinColumn(name = "ma_diagnosticos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MaDiagnosticos maDiagnosticosId;
    @JoinColumn(name = "ma_medicamentos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MaMedicamentos maMedicamentosId;

    public MaMedicamentoCondicionados() {
    }

    public MaMedicamentoCondicionados(Integer id) {
        this.id = id;
    }

    public MaMedicamentoCondicionados(Integer id, String codigo, String nombre, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(Integer edadMinima) {
        this.edadMinima = edadMinima;
    }

    public Integer getEdadMaxima() {
        return edadMaxima;
    }

    public void setEdadMaxima(Integer edadMaxima) {
        this.edadMaxima = edadMaxima;
    }

    public Integer getMaeGeneroId() {
        return maeGeneroId;
    }

    public void setMaeGeneroId(Integer maeGeneroId) {
        this.maeGeneroId = maeGeneroId;
    }

    public String getMaeGeneroCodigo() {
        return maeGeneroCodigo;
    }

    public void setMaeGeneroCodigo(String maeGeneroCodigo) {
        this.maeGeneroCodigo = maeGeneroCodigo;
    }

    public String getMaeGeneroValor() {
        return maeGeneroValor;
    }

    public void setMaeGeneroValor(String maeGeneroValor) {
        this.maeGeneroValor = maeGeneroValor;
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

    public MaDiagnosticos getMaDiagnosticosId() {
        return maDiagnosticosId;
    }

    public void setMaDiagnosticosId(MaDiagnosticos maDiagnosticosId) {
        this.maDiagnosticosId = maDiagnosticosId;
    }

    public MaMedicamentos getMaMedicamentosId() {
        return maMedicamentosId;
    }

    public void setMaMedicamentosId(MaMedicamentos maMedicamentosId) {
        this.maMedicamentosId = maMedicamentosId;
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
        if (!(object instanceof MaMedicamentoCondicionados)) {
            return false;
        }
        MaMedicamentoCondicionados other = (MaMedicamentoCondicionados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaMedicamentoCondicionados[ id=" + id + " ]";
    }
    
}
