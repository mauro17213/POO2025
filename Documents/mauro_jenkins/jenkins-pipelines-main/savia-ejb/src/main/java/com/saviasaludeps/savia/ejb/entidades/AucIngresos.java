/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "auc_ingresos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AucIngresos.findAll", query = "SELECT a FROM AucIngresos a"),
    @NamedQuery(name = "AucIngresos.findById", query = "SELECT a FROM AucIngresos a WHERE a.id = :id"),
    @NamedQuery(name = "AucIngresos.findByFechaIngreso", query = "SELECT a FROM AucIngresos a WHERE a.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "AucIngresos.findByIngreso", query = "SELECT a FROM AucIngresos a WHERE a.ingreso = :ingreso"),
    @NamedQuery(name = "AucIngresos.findByMaeTipoIngresoId", query = "SELECT a FROM AucIngresos a WHERE a.maeTipoIngresoId = :maeTipoIngresoId"),
    @NamedQuery(name = "AucIngresos.findByMaeTipoIngresoCodigo", query = "SELECT a FROM AucIngresos a WHERE a.maeTipoIngresoCodigo = :maeTipoIngresoCodigo"),
    @NamedQuery(name = "AucIngresos.findByMaeTipoIngresoValor", query = "SELECT a FROM AucIngresos a WHERE a.maeTipoIngresoValor = :maeTipoIngresoValor"),
    @NamedQuery(name = "AucIngresos.findByMaeCntModalidadId", query = "SELECT a FROM AucIngresos a WHERE a.maeCntModalidadId = :maeCntModalidadId"),
    @NamedQuery(name = "AucIngresos.findByMaeCntModalidadCodigo", query = "SELECT a FROM AucIngresos a WHERE a.maeCntModalidadCodigo = :maeCntModalidadCodigo"),
    @NamedQuery(name = "AucIngresos.findByMaeCntModalidadValor", query = "SELECT a FROM AucIngresos a WHERE a.maeCntModalidadValor = :maeCntModalidadValor"),
    @NamedQuery(name = "AucIngresos.findByMaeRemisionNoPertinenteId", query = "SELECT a FROM AucIngresos a WHERE a.maeRemisionNoPertinenteId = :maeRemisionNoPertinenteId"),
    @NamedQuery(name = "AucIngresos.findByMaeRemisionNoPertinenteCodigo", query = "SELECT a FROM AucIngresos a WHERE a.maeRemisionNoPertinenteCodigo = :maeRemisionNoPertinenteCodigo"),
    @NamedQuery(name = "AucIngresos.findByMaeRemisionNoPertinenteValor", query = "SELECT a FROM AucIngresos a WHERE a.maeRemisionNoPertinenteValor = :maeRemisionNoPertinenteValor"),
    @NamedQuery(name = "AucIngresos.findByDescripcionRemisionNoPertinente", query = "SELECT a FROM AucIngresos a WHERE a.descripcionRemisionNoPertinente = :descripcionRemisionNoPertinente"),
    @NamedQuery(name = "AucIngresos.findByIndiceCharlson", query = "SELECT a FROM AucIngresos a WHERE a.indiceCharlson = :indiceCharlson"),
    @NamedQuery(name = "AucIngresos.findByFuenteOrigen", query = "SELECT a FROM AucIngresos a WHERE a.fuenteOrigen = :fuenteOrigen"),
    @NamedQuery(name = "AucIngresos.findByMaeCausaIngresoPrevalenteId", query = "SELECT a FROM AucIngresos a WHERE a.maeCausaIngresoPrevalenteId = :maeCausaIngresoPrevalenteId"),
    @NamedQuery(name = "AucIngresos.findByMaeCausaIngresoPrevalenteCodigo", query = "SELECT a FROM AucIngresos a WHERE a.maeCausaIngresoPrevalenteCodigo = :maeCausaIngresoPrevalenteCodigo"),
    @NamedQuery(name = "AucIngresos.findByMaeCausaIngresoPrevalenteValor", query = "SELECT a FROM AucIngresos a WHERE a.maeCausaIngresoPrevalenteValor = :maeCausaIngresoPrevalenteValor"),
    @NamedQuery(name = "AucIngresos.findByMaeAreaIngresoPrevenibleId", query = "SELECT a FROM AucIngresos a WHERE a.maeAreaIngresoPrevenibleId = :maeAreaIngresoPrevenibleId"),
    @NamedQuery(name = "AucIngresos.findByMaeAreaIngresoPrevenibleCodigo", query = "SELECT a FROM AucIngresos a WHERE a.maeAreaIngresoPrevenibleCodigo = :maeAreaIngresoPrevenibleCodigo"),
    @NamedQuery(name = "AucIngresos.findByMaeAreaIngresoPrevenibleValor", query = "SELECT a FROM AucIngresos a WHERE a.maeAreaIngresoPrevenibleValor = :maeAreaIngresoPrevenibleValor"),
    @NamedQuery(name = "AucIngresos.findByDescripcionIngresoPrevenible", query = "SELECT a FROM AucIngresos a WHERE a.descripcionIngresoPrevenible = :descripcionIngresoPrevenible"),
    @NamedQuery(name = "AucIngresos.findByMaeReingresoMotivoId", query = "SELECT a FROM AucIngresos a WHERE a.maeReingresoMotivoId = :maeReingresoMotivoId"),
    @NamedQuery(name = "AucIngresos.findByMaeReingresoMotivoCodigo", query = "SELECT a FROM AucIngresos a WHERE a.maeReingresoMotivoCodigo = :maeReingresoMotivoCodigo"),
    @NamedQuery(name = "AucIngresos.findByMaeReingresoMotivoValor", query = "SELECT a FROM AucIngresos a WHERE a.maeReingresoMotivoValor = :maeReingresoMotivoValor"),
    @NamedQuery(name = "AucIngresos.findByMaeReingresoMotivoTipo", query = "SELECT a FROM AucIngresos a WHERE a.maeReingresoMotivoTipo = :maeReingresoMotivoTipo"),
    @NamedQuery(name = "AucIngresos.findByAltaTemprana", query = "SELECT a FROM AucIngresos a WHERE a.altaTemprana = :altaTemprana"),
    @NamedQuery(name = "AucIngresos.findByMaeAltaTempranaId", query = "SELECT a FROM AucIngresos a WHERE a.maeAltaTempranaId = :maeAltaTempranaId"),
    @NamedQuery(name = "AucIngresos.findByMaeAltaTempranaCodigo", query = "SELECT a FROM AucIngresos a WHERE a.maeAltaTempranaCodigo = :maeAltaTempranaCodigo"),
    @NamedQuery(name = "AucIngresos.findByMaeAltaTempranaValor", query = "SELECT a FROM AucIngresos a WHERE a.maeAltaTempranaValor = :maeAltaTempranaValor"),
    @NamedQuery(name = "AucIngresos.findByMaeAltaTempranaTipo", query = "SELECT a FROM AucIngresos a WHERE a.maeAltaTempranaTipo = :maeAltaTempranaTipo"),
    @NamedQuery(name = "AucIngresos.findByUsuarioCrea", query = "SELECT a FROM AucIngresos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AucIngresos.findByTerminalCrea", query = "SELECT a FROM AucIngresos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AucIngresos.findByFechaHoraCrea", query = "SELECT a FROM AucIngresos a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AucIngresos.findByUsuarioModifica", query = "SELECT a FROM AucIngresos a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AucIngresos.findByTerminalModifica", query = "SELECT a FROM AucIngresos a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AucIngresos.findByFechaHoraModifica", query = "SELECT a FROM AucIngresos a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AucIngresos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ingreso")
    private short ingreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_ingreso_id")
    private int maeTipoIngresoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_ingreso_codigo")
    private String maeTipoIngresoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_tipo_ingreso_valor")
    private String maeTipoIngresoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_cnt_modalidad_id")
    private int maeCntModalidadId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_cnt_modalidad_codigo")
    private String maeCntModalidadCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_cnt_modalidad_valor")
    private String maeCntModalidadValor;
    @Column(name = "mae_remision_no_pertinente_id")
    private Integer maeRemisionNoPertinenteId;
    @Size(max = 8)
    @Column(name = "mae_remision_no_pertinente_codigo")
    private String maeRemisionNoPertinenteCodigo;
    @Size(max = 128)
    @Column(name = "mae_remision_no_pertinente_valor")
    private String maeRemisionNoPertinenteValor;
    @Size(max = 512)
    @Column(name = "descripcion_remision_no_pertinente")
    private String descripcionRemisionNoPertinente;
    @Column(name = "indice_charlson")
    private Integer indiceCharlson;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fuente_origen")
    private int fuenteOrigen;
    @Column(name = "mae_causa_ingreso_prevalente_id")
    private Integer maeCausaIngresoPrevalenteId;
    @Size(max = 8)
    @Column(name = "mae_causa_ingreso_prevalente_codigo")
    private String maeCausaIngresoPrevalenteCodigo;
    @Size(max = 128)
    @Column(name = "mae_causa_ingreso_prevalente_valor")
    private String maeCausaIngresoPrevalenteValor;
    @Column(name = "mae_area_ingreso_prevenible_id")
    private Integer maeAreaIngresoPrevenibleId;
    @Size(max = 8)
    @Column(name = "mae_area_ingreso_prevenible_codigo")
    private String maeAreaIngresoPrevenibleCodigo;
    @Size(max = 128)
    @Column(name = "mae_area_ingreso_prevenible_valor")
    private String maeAreaIngresoPrevenibleValor;
    @Size(max = 2048)
    @Column(name = "descripcion_ingreso_prevenible")
    private String descripcionIngresoPrevenible;
    @Column(name = "mae_reingreso_motivo_id")
    private Integer maeReingresoMotivoId;
    @Size(max = 8)
    @Column(name = "mae_reingreso_motivo_codigo")
    private String maeReingresoMotivoCodigo;
    @Size(max = 128)
    @Column(name = "mae_reingreso_motivo_valor")
    private String maeReingresoMotivoValor;
    @Size(max = 4)
    @Column(name = "mae_reingreso_motivo_tipo")
    private String maeReingresoMotivoTipo;
    @Column(name = "alta_temprana")
    private Short altaTemprana;
    @Column(name = "mae_alta_temprana_id")
    private Integer maeAltaTempranaId;
    @Size(max = 8)
    @Column(name = "mae_alta_temprana_codigo")
    private String maeAltaTempranaCodigo;
    @Size(max = 128)
    @Column(name = "mae_alta_temprana_valor")
    private String maeAltaTempranaValor;
    @Size(max = 4)
    @Column(name = "mae_alta_temprana_tipo")
    private String maeAltaTempranaTipo;
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
    @OneToMany(mappedBy = "aucIngresosId", fetch = FetchType.LAZY)
    private List<AucDiagnosticos> aucDiagnosticosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aucIngresosId", fetch = FetchType.LAZY)
    private List<AucHospitalizaciones> aucHospitalizacionesList;

    public AucIngresos() {
    }

    public AucIngresos(Integer id) {
        this.id = id;
    }

    public AucIngresos(Integer id, Date fechaIngreso, short ingreso, int maeTipoIngresoId, String maeTipoIngresoCodigo, String maeTipoIngresoValor, int maeCntModalidadId, String maeCntModalidadCodigo, String maeCntModalidadValor, int fuenteOrigen, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.ingreso = ingreso;
        this.maeTipoIngresoId = maeTipoIngresoId;
        this.maeTipoIngresoCodigo = maeTipoIngresoCodigo;
        this.maeTipoIngresoValor = maeTipoIngresoValor;
        this.maeCntModalidadId = maeCntModalidadId;
        this.maeCntModalidadCodigo = maeCntModalidadCodigo;
        this.maeCntModalidadValor = maeCntModalidadValor;
        this.fuenteOrigen = fuenteOrigen;
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

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public short getIngreso() {
        return ingreso;
    }

    public void setIngreso(short ingreso) {
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

    public String getDescripcionRemisionNoPertinente() {
        return descripcionRemisionNoPertinente;
    }

    public void setDescripcionRemisionNoPertinente(String descripcionRemisionNoPertinente) {
        this.descripcionRemisionNoPertinente = descripcionRemisionNoPertinente;
    }

    public Integer getIndiceCharlson() {
        return indiceCharlson;
    }

    public void setIndiceCharlson(Integer indiceCharlson) {
        this.indiceCharlson = indiceCharlson;
    }

    public int getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(int fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
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

    public String getDescripcionIngresoPrevenible() {
        return descripcionIngresoPrevenible;
    }

    public void setDescripcionIngresoPrevenible(String descripcionIngresoPrevenible) {
        this.descripcionIngresoPrevenible = descripcionIngresoPrevenible;
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

    @XmlTransient
    public List<AucDiagnosticos> getAucDiagnosticosList() {
        return aucDiagnosticosList;
    }

    public void setAucDiagnosticosList(List<AucDiagnosticos> aucDiagnosticosList) {
        this.aucDiagnosticosList = aucDiagnosticosList;
    }

    @XmlTransient
    public List<AucHospitalizaciones> getAucHospitalizacionesList() {
        return aucHospitalizacionesList;
    }

    public void setAucHospitalizacionesList(List<AucHospitalizaciones> aucHospitalizacionesList) {
        this.aucHospitalizacionesList = aucHospitalizacionesList;
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
        if (!(object instanceof AucIngresos)) {
            return false;
        }
        AucIngresos other = (AucIngresos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AucIngresos[ id=" + id + " ]";
    }
    
}
