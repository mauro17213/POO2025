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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "cntj_campos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjCampos.findAll", query = "SELECT c FROM CntjCampos c"),
    @NamedQuery(name = "CntjCampos.findById", query = "SELECT c FROM CntjCampos c WHERE c.id = :id"),
    @NamedQuery(name = "CntjCampos.findByNombre", query = "SELECT c FROM CntjCampos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CntjCampos.findByDescripcion", query = "SELECT c FROM CntjCampos c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CntjCampos.findByEtiqueta", query = "SELECT c FROM CntjCampos c WHERE c.etiqueta = :etiqueta"),
    @NamedQuery(name = "CntjCampos.findByEstatico", query = "SELECT c FROM CntjCampos c WHERE c.estatico = :estatico"),
    @NamedQuery(name = "CntjCampos.findByTipoDato", query = "SELECT c FROM CntjCampos c WHERE c.tipoDato = :tipoDato"),
    @NamedQuery(name = "CntjCampos.findByAplicaMaestro", query = "SELECT c FROM CntjCampos c WHERE c.aplicaMaestro = :aplicaMaestro"),
    @NamedQuery(name = "CntjCampos.findByMaestro", query = "SELECT c FROM CntjCampos c WHERE c.maestro = :maestro"),
    @NamedQuery(name = "CntjCampos.findByCampoDestino", query = "SELECT c FROM CntjCampos c WHERE c.campoDestino = :campoDestino"),
    @NamedQuery(name = "CntjCampos.findByTablaDestino", query = "SELECT c FROM CntjCampos c WHERE c.tablaDestino = :tablaDestino"),
    @NamedQuery(name = "CntjCampos.findByValoresLista", query = "SELECT c FROM CntjCampos c WHERE c.valoresLista = :valoresLista"),
    @NamedQuery(name = "CntjCampos.findByCampoReferencia", query = "SELECT c FROM CntjCampos c WHERE c.campoReferencia = :campoReferencia"),
    @NamedQuery(name = "CntjCampos.findByValorReferencia", query = "SELECT c FROM CntjCampos c WHERE c.valorReferencia = :valorReferencia"),
    @NamedQuery(name = "CntjCampos.findByUsuarioCrea", query = "SELECT c FROM CntjCampos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjCampos.findByTerminalCrea", query = "SELECT c FROM CntjCampos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjCampos.findByFechaHoraCrea", query = "SELECT c FROM CntjCampos c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjCampos.findByUsuarioModifica", query = "SELECT c FROM CntjCampos c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjCampos.findByTerminalModifica", query = "SELECT c FROM CntjCampos c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjCampos.findByFechaHoraModifica", query = "SELECT c FROM CntjCampos c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntjCampos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 128)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "etiqueta")
    private String etiqueta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estatico")
    private boolean estatico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_dato")
    private int tipoDato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_maestro")
    private boolean aplicaMaestro;
    @Column(name = "maestro")
    private Integer maestro;
    @Size(max = 64)
    @Column(name = "campo_destino")
    private String campoDestino;
    @Size(max = 64)
    @Column(name = "tabla_destino")
    private String tablaDestino;
    @Size(max = 512)
    @Column(name = "valores_lista")
    private String valoresLista;
    @Size(max = 64)
    @Column(name = "campo_referencia")
    private String campoReferencia;
    @Size(max = 512)
    @Column(name = "valor_referencia")
    private String valorReferencia;
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
    @JoinColumn(name = "cntj_procesos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjProcesos cntjProcesosId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjCampoId", fetch = FetchType.LAZY)
    private List<CntjPlantillaCampos> cntjPlantillaCamposList;

    public CntjCampos() {
    }

    public CntjCampos(Integer id) {
        this.id = id;
    }

    public CntjCampos(Integer id, String nombre, String etiqueta, boolean estatico, int tipoDato, boolean aplicaMaestro, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.etiqueta = etiqueta;
        this.estatico = estatico;
        this.tipoDato = tipoDato;
        this.aplicaMaestro = aplicaMaestro;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public boolean getEstatico() {
        return estatico;
    }

    public void setEstatico(boolean estatico) {
        this.estatico = estatico;
    }

    public int getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(int tipoDato) {
        this.tipoDato = tipoDato;
    }

    public boolean getAplicaMaestro() {
        return aplicaMaestro;
    }

    public void setAplicaMaestro(boolean aplicaMaestro) {
        this.aplicaMaestro = aplicaMaestro;
    }

    public Integer getMaestro() {
        return maestro;
    }

    public void setMaestro(Integer maestro) {
        this.maestro = maestro;
    }

    public String getCampoDestino() {
        return campoDestino;
    }

    public void setCampoDestino(String campoDestino) {
        this.campoDestino = campoDestino;
    }

    public String getTablaDestino() {
        return tablaDestino;
    }

    public void setTablaDestino(String tablaDestino) {
        this.tablaDestino = tablaDestino;
    }

    public String getValoresLista() {
        return valoresLista;
    }

    public void setValoresLista(String valoresLista) {
        this.valoresLista = valoresLista;
    }

    public String getCampoReferencia() {
        return campoReferencia;
    }

    public void setCampoReferencia(String campoReferencia) {
        this.campoReferencia = campoReferencia;
    }

    public String getValorReferencia() {
        return valorReferencia;
    }

    public void setValorReferencia(String valorReferencia) {
        this.valorReferencia = valorReferencia;
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

    public CntjProcesos getCntjProcesosId() {
        return cntjProcesosId;
    }

    public void setCntjProcesosId(CntjProcesos cntjProcesosId) {
        this.cntjProcesosId = cntjProcesosId;
    }

    @XmlTransient
    public List<CntjPlantillaCampos> getCntjPlantillaCamposList() {
        return cntjPlantillaCamposList;
    }

    public void setCntjPlantillaCamposList(List<CntjPlantillaCampos> cntjPlantillaCamposList) {
        this.cntjPlantillaCamposList = cntjPlantillaCamposList;
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
        if (!(object instanceof CntjCampos)) {
            return false;
        }
        CntjCampos other = (CntjCampos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjCampos[ id=" + id + " ]";
    }
    
}
