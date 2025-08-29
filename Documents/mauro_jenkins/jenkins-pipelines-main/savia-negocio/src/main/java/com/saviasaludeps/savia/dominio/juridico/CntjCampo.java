package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public class CntjCampo extends Auditoria {
    
    private Integer id;
    private CntjProceso cntjProcesoId;
    private String nombre;
    private String descripcion;
    private String etiqueta;
    private Integer tipoDato;
    private List<CntjPlantillaCampo> plantillasCampo;
    private Integer cntjPlantillaId;
    private String valor;
    private boolean valorBl;
    private Date valorDt;
    private boolean aplicaMaestro;
    private String maestroTipo;
    private String tabla;
    private String campo;
    private String valoresLista;
    private String campoReferencia;
    private String valorReferencia;
    private String valorStr;
    private boolean existente;
    private List<Maestro> listaValores;
    private HashMap<Integer, Maestro> hashlistaValores;
    private boolean borrador;
    

    public CntjCampo() {
        this.plantillasCampo = new ArrayList<>();
        this.listaValores = new ArrayList<>();
        this.hashlistaValores = new HashMap<>();
        this.cntjProcesoId = new CntjProceso();
    }

    public CntjCampo(Integer id) {
        this.id = id;
        this.plantillasCampo = new ArrayList<>();
        this.listaValores = new ArrayList<>();
        this.hashlistaValores = new HashMap<>();
        this.cntjProcesoId = new CntjProceso();
    }

    public List<Maestro> getListaValores() {
        return listaValores;
    }

    public void setListaValores(List<Maestro> listaValores) {
        this.listaValores = listaValores;
    }

    public HashMap<Integer, Maestro> getHashlistaValores() {
        return hashlistaValores;
    }

    public void setHashlistaValores(HashMap<Integer, Maestro> hashlistaValores) {
        this.hashlistaValores = hashlistaValores;
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

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Integer getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(Integer tipoDato) {
        this.tipoDato = tipoDato;
    }
    
    public List<CntjPlantillaCampo> getPlantillasCampo() {
        return plantillasCampo;
    }

    public void setPlantillasCampo(List<CntjPlantillaCampo> plantillasCampo) {
        this.plantillasCampo = plantillasCampo;
    }

    public Integer getCntjPlantillaId() {
        return cntjPlantillaId;
    }

    public void setCntjPlantillaId(Integer cntjPlantillaId) {
        this.cntjPlantillaId = cntjPlantillaId;
    }

    public CntjProceso getCntjProcesoId() {
        return cntjProcesoId;
    }

    public void setCntjProcesoId(CntjProceso cntjProcesoId) {
        this.cntjProcesoId = cntjProcesoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public boolean isValorBl() {
        return valorBl;
    }

    public void setValorBl(boolean valorBl) {
        this.valorBl = valorBl;
    }

    public Date getValorDt() {
        return valorDt;
    }

    public void setValorDt(Date valorDt) {
        this.valorDt = valorDt;
    }

    public boolean isAplicaMaestro() {
        return aplicaMaestro;
    }

    public void setAplicaMaestro(boolean aplicaMaestro) {
        this.aplicaMaestro = aplicaMaestro;
    }

    public String getMaestroTipo() {
        return maestroTipo;
    }

    public void setMaestroTipo(String maestroTipo) {
        this.maestroTipo = maestroTipo;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
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

    public String getValorStr() {
        return valorStr;
    }

    public void setValorStr(String valorStr) {
        this.valorStr = valorStr;
    }

    public boolean getExistente() {
        return existente;
    }

    public void setExistente(boolean existente) {
        this.existente = existente;
    }

    public boolean isBorrador() {
        return borrador;
    }

    public void setBorrador(boolean borrador) {
        this.borrador = borrador;
    }
    
    
    
    public String getMaestroTipoStr(){
        if(maestroTipo == null) {
            return "";
        }
        return maestroTipo;
    }
    
    public String aplicaMaestroStr(){
        if(aplicaMaestro){
            return "Si";
        }
        return "No";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CntjCampo item = (CntjCampo) obj;
        return nombre.equals(item.nombre); // Se compara por ID
    }

    @Override
    public String toString() {
        return "CntjCampo{" + "id=" + id + ", cntjProcesoId=" + cntjProcesoId + ", nombre=" + nombre + ", descripcion=" + descripcion + ", etiqueta=" + etiqueta + ", tipoDato=" + tipoDato + ", cntjPlantillaId=" + cntjPlantillaId + ", valor=" + valor + ", valorBl=" + valorBl + ", valorDt=" + valorDt + ", aplicaMaestro=" + aplicaMaestro + ", maestroTipo=" + maestroTipo + ", tabla=" + tabla + ", campo=" + campo + ", valoresLista=" + valoresLista + ", campoReferencia=" + campoReferencia + ", valorReferencia=" + valorReferencia + ", valorStr=" + valorStr + ", existente=" + existente + ", borrador=" + borrador + '}';
    }

}
