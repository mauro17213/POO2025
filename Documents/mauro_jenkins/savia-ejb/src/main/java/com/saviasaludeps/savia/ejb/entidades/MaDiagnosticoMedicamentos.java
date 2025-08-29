/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "ma_diagnostico_medicamentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaDiagnosticoMedicamentos.findAll", query = "SELECT m FROM MaDiagnosticoMedicamentos m"),
    @NamedQuery(name = "MaDiagnosticoMedicamentos.findById", query = "SELECT m FROM MaDiagnosticoMedicamentos m WHERE m.id = :id"),
    @NamedQuery(name = "MaDiagnosticoMedicamentos.findByActivo", query = "SELECT m FROM MaDiagnosticoMedicamentos m WHERE m.activo = :activo")})
public class MaDiagnosticoMedicamentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @JoinColumn(name = "ma_diagniosticos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MaDiagnosticos maDiagniosticosId;
    @JoinColumn(name = "ma_medicamentos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MaMedicamentos maMedicamentosId;

    public MaDiagnosticoMedicamentos() {
    }

    public MaDiagnosticoMedicamentos(Integer id) {
        this.id = id;
    }

    public MaDiagnosticoMedicamentos(Integer id, boolean activo) {
        this.id = id;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public MaDiagnosticos getMaDiagniosticosId() {
        return maDiagniosticosId;
    }

    public void setMaDiagniosticosId(MaDiagnosticos maDiagniosticosId) {
        this.maDiagniosticosId = maDiagniosticosId;
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
        if (!(object instanceof MaDiagnosticoMedicamentos)) {
            return false;
        }
        MaDiagnosticoMedicamentos other = (MaDiagnosticoMedicamentos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaDiagnosticoMedicamentos[ id=" + id + " ]";
    }
    
}
