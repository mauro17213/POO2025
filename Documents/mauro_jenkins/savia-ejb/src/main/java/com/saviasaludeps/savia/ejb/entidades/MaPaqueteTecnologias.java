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
@Table(name = "ma_paquete_tecnologias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaPaqueteTecnologias.findAll", query = "SELECT m FROM MaPaqueteTecnologias m"),
    @NamedQuery(name = "MaPaqueteTecnologias.findById", query = "SELECT m FROM MaPaqueteTecnologias m WHERE m.id = :id"),
    @NamedQuery(name = "MaPaqueteTecnologias.findByTipoTecnologia", query = "SELECT m FROM MaPaqueteTecnologias m WHERE m.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "MaPaqueteTecnologias.findByUsuarioCrea", query = "SELECT m FROM MaPaqueteTecnologias m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MaPaqueteTecnologias.findByTerminalCrea", query = "SELECT m FROM MaPaqueteTecnologias m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MaPaqueteTecnologias.findByFechaHoraCrea", query = "SELECT m FROM MaPaqueteTecnologias m WHERE m.fechaHoraCrea = :fechaHoraCrea")})
public class MaPaqueteTecnologias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_tecnologia")
    private int tipoTecnologia;
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
    @JoinColumn(name = "ma_insumos_ips_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MaInsumosIps maInsumosIpsId;
    @JoinColumn(name = "ma_medicamentos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MaMedicamentos maMedicamentosId;
    @JoinColumn(name = "ma_paquetes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MaPaquetes maPaquetesId;
    @JoinColumn(name = "ma_tecnologias_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MaTecnologias maTecnologiasId;

    public MaPaqueteTecnologias() {
    }

    public MaPaqueteTecnologias(Integer id) {
        this.id = id;
    }

    public MaPaqueteTecnologias(Integer id, int tipoTecnologia, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipoTecnologia = tipoTecnologia;
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

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
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

    public MaInsumosIps getMaInsumosIpsId() {
        return maInsumosIpsId;
    }

    public void setMaInsumosIpsId(MaInsumosIps maInsumosIpsId) {
        this.maInsumosIpsId = maInsumosIpsId;
    }

    public MaMedicamentos getMaMedicamentosId() {
        return maMedicamentosId;
    }

    public void setMaMedicamentosId(MaMedicamentos maMedicamentosId) {
        this.maMedicamentosId = maMedicamentosId;
    }

    public MaPaquetes getMaPaquetesId() {
        return maPaquetesId;
    }

    public void setMaPaquetesId(MaPaquetes maPaquetesId) {
        this.maPaquetesId = maPaquetesId;
    }

    public MaTecnologias getMaTecnologiasId() {
        return maTecnologiasId;
    }

    public void setMaTecnologiasId(MaTecnologias maTecnologiasId) {
        this.maTecnologiasId = maTecnologiasId;
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
        if (!(object instanceof MaPaqueteTecnologias)) {
            return false;
        }
        MaPaqueteTecnologias other = (MaPaqueteTecnologias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaPaqueteTecnologias[ id=" + id + " ]";
    }
    
}
