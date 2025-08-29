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
@Table(name = "ma_diagnosticos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaDiagnosticos.findAll", query = "SELECT m FROM MaDiagnosticos m"),
    @NamedQuery(name = "MaDiagnosticos.findById", query = "SELECT m FROM MaDiagnosticos m WHERE m.id = :id"),
    @NamedQuery(name = "MaDiagnosticos.findByMaeDiagnosticoCapituloId", query = "SELECT m FROM MaDiagnosticos m WHERE m.maeDiagnosticoCapituloId = :maeDiagnosticoCapituloId"),
    @NamedQuery(name = "MaDiagnosticos.findByMaeDiagnosticoCapituloCodigo", query = "SELECT m FROM MaDiagnosticos m WHERE m.maeDiagnosticoCapituloCodigo = :maeDiagnosticoCapituloCodigo"),
    @NamedQuery(name = "MaDiagnosticos.findByMaeDiagnosticoCapituloValor", query = "SELECT m FROM MaDiagnosticos m WHERE m.maeDiagnosticoCapituloValor = :maeDiagnosticoCapituloValor"),
    @NamedQuery(name = "MaDiagnosticos.findByMaeDiagnosticoCategoriaId", query = "SELECT m FROM MaDiagnosticos m WHERE m.maeDiagnosticoCategoriaId = :maeDiagnosticoCategoriaId"),
    @NamedQuery(name = "MaDiagnosticos.findByMaeDiagnosticoCategoriaCodigo", query = "SELECT m FROM MaDiagnosticos m WHERE m.maeDiagnosticoCategoriaCodigo = :maeDiagnosticoCategoriaCodigo"),
    @NamedQuery(name = "MaDiagnosticos.findByMaeDiagnosticoCategoriaValor", query = "SELECT m FROM MaDiagnosticos m WHERE m.maeDiagnosticoCategoriaValor = :maeDiagnosticoCategoriaValor"),
    @NamedQuery(name = "MaDiagnosticos.findByCodigo", query = "SELECT m FROM MaDiagnosticos m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "MaDiagnosticos.findByNombre", query = "SELECT m FROM MaDiagnosticos m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MaDiagnosticos.findByActivo", query = "SELECT m FROM MaDiagnosticos m WHERE m.activo = :activo"),
    @NamedQuery(name = "MaDiagnosticos.findBySexoAplica", query = "SELECT m FROM MaDiagnosticos m WHERE m.sexoAplica = :sexoAplica"),
    @NamedQuery(name = "MaDiagnosticos.findByEdadInferior", query = "SELECT m FROM MaDiagnosticos m WHERE m.edadInferior = :edadInferior"),
    @NamedQuery(name = "MaDiagnosticos.findByEdadSuperior", query = "SELECT m FROM MaDiagnosticos m WHERE m.edadSuperior = :edadSuperior"),
    @NamedQuery(name = "MaDiagnosticos.findByGrupoMortalidad", query = "SELECT m FROM MaDiagnosticos m WHERE m.grupoMortalidad = :grupoMortalidad"),
    @NamedQuery(name = "MaDiagnosticos.findByGrupoMortalidadLista", query = "SELECT m FROM MaDiagnosticos m WHERE m.grupoMortalidadLista = :grupoMortalidadLista"),
    @NamedQuery(name = "MaDiagnosticos.findByValorLimiteInferior", query = "SELECT m FROM MaDiagnosticos m WHERE m.valorLimiteInferior = :valorLimiteInferior"),
    @NamedQuery(name = "MaDiagnosticos.findByValorLimiteSuperior", query = "SELECT m FROM MaDiagnosticos m WHERE m.valorLimiteSuperior = :valorLimiteSuperior"),
    @NamedQuery(name = "MaDiagnosticos.findByExcentoCobro", query = "SELECT m FROM MaDiagnosticos m WHERE m.excentoCobro = :excentoCobro"),
    @NamedQuery(name = "MaDiagnosticos.findByAltoCosto", query = "SELECT m FROM MaDiagnosticos m WHERE m.altoCosto = :altoCosto"),
    @NamedQuery(name = "MaDiagnosticos.findByPriorizarCrue", query = "SELECT m FROM MaDiagnosticos m WHERE m.priorizarCrue = :priorizarCrue"),
    @NamedQuery(name = "MaDiagnosticos.findByMaCacId", query = "SELECT m FROM MaDiagnosticos m WHERE m.maCacId = :maCacId"),
    @NamedQuery(name = "MaDiagnosticos.findByMaCacCodigo", query = "SELECT m FROM MaDiagnosticos m WHERE m.maCacCodigo = :maCacCodigo"),
    @NamedQuery(name = "MaDiagnosticos.findByMaCacValor", query = "SELECT m FROM MaDiagnosticos m WHERE m.maCacValor = :maCacValor"),
    @NamedQuery(name = "MaDiagnosticos.findByUsuarioCrea", query = "SELECT m FROM MaDiagnosticos m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MaDiagnosticos.findByTerminalCrea", query = "SELECT m FROM MaDiagnosticos m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MaDiagnosticos.findByFechaHoraCrea", query = "SELECT m FROM MaDiagnosticos m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MaDiagnosticos.findByUsuarioModifica", query = "SELECT m FROM MaDiagnosticos m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MaDiagnosticos.findByTerminalModifica", query = "SELECT m FROM MaDiagnosticos m WHERE m.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "MaDiagnosticos.findByFechaHoraModifica", query = "SELECT m FROM MaDiagnosticos m WHERE m.fechaHoraModifica = :fechaHoraModifica")})
public class MaDiagnosticos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_diagnostico_capitulo_id")
    private int maeDiagnosticoCapituloId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_diagnostico_capitulo_codigo")
    private String maeDiagnosticoCapituloCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_diagnostico_capitulo_valor")
    private String maeDiagnosticoCapituloValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_diagnostico_categoria_id")
    private int maeDiagnosticoCategoriaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_diagnostico_categoria_codigo")
    private String maeDiagnosticoCategoriaCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_diagnostico_categoria_valor")
    private String maeDiagnosticoCategoriaValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sexo_aplica")
    private int sexoAplica;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "edad_inferior")
    private String edadInferior;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "edad_superior")
    private String edadSuperior;
    @Size(max = 3)
    @Column(name = "grupo_mortalidad")
    private String grupoMortalidad;
    @Size(max = 3)
    @Column(name = "grupo_mortalidad_lista")
    private String grupoMortalidadLista;
    @Column(name = "valor_limite_inferior")
    private Integer valorLimiteInferior;
    @Column(name = "valor_limite_superior")
    private Integer valorLimiteSuperior;
    @Column(name = "excento_cobro")
    private Boolean excentoCobro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "alto_costo")
    private boolean altoCosto;
    @Column(name = "priorizar_crue")
    private Boolean priorizarCrue;
    @Column(name = "ma_cac_id")
    private Integer maCacId;
    @Size(max = 8)
    @Column(name = "ma_cac_codigo")
    private String maCacCodigo;
    @Size(max = 128)
    @Column(name = "ma_cac_valor")
    private String maCacValor;
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
    @OneToMany(mappedBy = "maDiagnosticosId", fetch = FetchType.LAZY)
    private List<AusCasoServicios> ausCasoServiciosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maDiagniosticosId", fetch = FetchType.LAZY)
    private List<MaDiagnosticoMedicamentos> maDiagnosticoMedicamentosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maDiagnosticosId", fetch = FetchType.LAZY)
    private List<MaMedicamentoCondicionados> maMedicamentoCondicionadosList;

    public MaDiagnosticos() {
    }

    public MaDiagnosticos(Integer id) {
        this.id = id;
    }

    public MaDiagnosticos(Integer id, int maeDiagnosticoCapituloId, String maeDiagnosticoCapituloCodigo, String maeDiagnosticoCapituloValor, int maeDiagnosticoCategoriaId, String maeDiagnosticoCategoriaCodigo, String maeDiagnosticoCategoriaValor, String codigo, String nombre, boolean activo, int sexoAplica, String edadInferior, String edadSuperior, boolean altoCosto, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeDiagnosticoCapituloId = maeDiagnosticoCapituloId;
        this.maeDiagnosticoCapituloCodigo = maeDiagnosticoCapituloCodigo;
        this.maeDiagnosticoCapituloValor = maeDiagnosticoCapituloValor;
        this.maeDiagnosticoCategoriaId = maeDiagnosticoCategoriaId;
        this.maeDiagnosticoCategoriaCodigo = maeDiagnosticoCategoriaCodigo;
        this.maeDiagnosticoCategoriaValor = maeDiagnosticoCategoriaValor;
        this.codigo = codigo;
        this.nombre = nombre;
        this.activo = activo;
        this.sexoAplica = sexoAplica;
        this.edadInferior = edadInferior;
        this.edadSuperior = edadSuperior;
        this.altoCosto = altoCosto;
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

    public int getMaeDiagnosticoCapituloId() {
        return maeDiagnosticoCapituloId;
    }

    public void setMaeDiagnosticoCapituloId(int maeDiagnosticoCapituloId) {
        this.maeDiagnosticoCapituloId = maeDiagnosticoCapituloId;
    }

    public String getMaeDiagnosticoCapituloCodigo() {
        return maeDiagnosticoCapituloCodigo;
    }

    public void setMaeDiagnosticoCapituloCodigo(String maeDiagnosticoCapituloCodigo) {
        this.maeDiagnosticoCapituloCodigo = maeDiagnosticoCapituloCodigo;
    }

    public String getMaeDiagnosticoCapituloValor() {
        return maeDiagnosticoCapituloValor;
    }

    public void setMaeDiagnosticoCapituloValor(String maeDiagnosticoCapituloValor) {
        this.maeDiagnosticoCapituloValor = maeDiagnosticoCapituloValor;
    }

    public int getMaeDiagnosticoCategoriaId() {
        return maeDiagnosticoCategoriaId;
    }

    public void setMaeDiagnosticoCategoriaId(int maeDiagnosticoCategoriaId) {
        this.maeDiagnosticoCategoriaId = maeDiagnosticoCategoriaId;
    }

    public String getMaeDiagnosticoCategoriaCodigo() {
        return maeDiagnosticoCategoriaCodigo;
    }

    public void setMaeDiagnosticoCategoriaCodigo(String maeDiagnosticoCategoriaCodigo) {
        this.maeDiagnosticoCategoriaCodigo = maeDiagnosticoCategoriaCodigo;
    }

    public String getMaeDiagnosticoCategoriaValor() {
        return maeDiagnosticoCategoriaValor;
    }

    public void setMaeDiagnosticoCategoriaValor(String maeDiagnosticoCategoriaValor) {
        this.maeDiagnosticoCategoriaValor = maeDiagnosticoCategoriaValor;
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

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getSexoAplica() {
        return sexoAplica;
    }

    public void setSexoAplica(int sexoAplica) {
        this.sexoAplica = sexoAplica;
    }

    public String getEdadInferior() {
        return edadInferior;
    }

    public void setEdadInferior(String edadInferior) {
        this.edadInferior = edadInferior;
    }

    public String getEdadSuperior() {
        return edadSuperior;
    }

    public void setEdadSuperior(String edadSuperior) {
        this.edadSuperior = edadSuperior;
    }

    public String getGrupoMortalidad() {
        return grupoMortalidad;
    }

    public void setGrupoMortalidad(String grupoMortalidad) {
        this.grupoMortalidad = grupoMortalidad;
    }

    public String getGrupoMortalidadLista() {
        return grupoMortalidadLista;
    }

    public void setGrupoMortalidadLista(String grupoMortalidadLista) {
        this.grupoMortalidadLista = grupoMortalidadLista;
    }

    public Integer getValorLimiteInferior() {
        return valorLimiteInferior;
    }

    public void setValorLimiteInferior(Integer valorLimiteInferior) {
        this.valorLimiteInferior = valorLimiteInferior;
    }

    public Integer getValorLimiteSuperior() {
        return valorLimiteSuperior;
    }

    public void setValorLimiteSuperior(Integer valorLimiteSuperior) {
        this.valorLimiteSuperior = valorLimiteSuperior;
    }

    public Boolean getExcentoCobro() {
        return excentoCobro;
    }

    public void setExcentoCobro(Boolean excentoCobro) {
        this.excentoCobro = excentoCobro;
    }

    public boolean getAltoCosto() {
        return altoCosto;
    }

    public void setAltoCosto(boolean altoCosto) {
        this.altoCosto = altoCosto;
    }

    public Boolean getPriorizarCrue() {
        return priorizarCrue;
    }

    public void setPriorizarCrue(Boolean priorizarCrue) {
        this.priorizarCrue = priorizarCrue;
    }

    public Integer getMaCacId() {
        return maCacId;
    }

    public void setMaCacId(Integer maCacId) {
        this.maCacId = maCacId;
    }

    public String getMaCacCodigo() {
        return maCacCodigo;
    }

    public void setMaCacCodigo(String maCacCodigo) {
        this.maCacCodigo = maCacCodigo;
    }

    public String getMaCacValor() {
        return maCacValor;
    }

    public void setMaCacValor(String maCacValor) {
        this.maCacValor = maCacValor;
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
    public List<AusCasoServicios> getAusCasoServiciosList() {
        return ausCasoServiciosList;
    }

    public void setAusCasoServiciosList(List<AusCasoServicios> ausCasoServiciosList) {
        this.ausCasoServiciosList = ausCasoServiciosList;
    }

    @XmlTransient
    public List<MaDiagnosticoMedicamentos> getMaDiagnosticoMedicamentosList() {
        return maDiagnosticoMedicamentosList;
    }

    public void setMaDiagnosticoMedicamentosList(List<MaDiagnosticoMedicamentos> maDiagnosticoMedicamentosList) {
        this.maDiagnosticoMedicamentosList = maDiagnosticoMedicamentosList;
    }

    @XmlTransient
    public List<MaMedicamentoCondicionados> getMaMedicamentoCondicionadosList() {
        return maMedicamentoCondicionadosList;
    }

    public void setMaMedicamentoCondicionadosList(List<MaMedicamentoCondicionados> maMedicamentoCondicionadosList) {
        this.maMedicamentoCondicionadosList = maMedicamentoCondicionadosList;
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
        if (!(object instanceof MaDiagnosticos)) {
            return false;
        }
        MaDiagnosticos other = (MaDiagnosticos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaDiagnosticos[ id=" + id + " ]";
    }
    
}
