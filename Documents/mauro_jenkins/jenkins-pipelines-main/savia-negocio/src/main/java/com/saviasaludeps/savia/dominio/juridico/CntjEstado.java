
package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public class CntjEstado extends Auditoria {
    
    private Integer id;    
    private Integer tipo;    
    private String nombre;    
    private String descripcion;    
    private boolean activo;        
    private CntjProceso cntjProcesoId;   
    private CntjTransicion cntjTransicionId;
    private List<CntjEstadoGrupo> listaEstadoGrupo;
    private boolean modificaFecha;
    private boolean modificaDatos;
    private boolean validaGrupo;
    private Integer resultadoComite;

    public CntjEstado() {
        this.listaEstadoGrupo = new ArrayList<>();
    }

    public CntjEstado(Integer id) {
        this.id = id;
        this.listaEstadoGrupo = new ArrayList<>();
    }

    public CntjEstado(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.listaEstadoGrupo = new ArrayList<>();
    }

    public CntjEstado(Integer id, Integer tipo, String nombre, String descripcion, boolean activo, CntjProceso cntjProcesoId, CntjTransicion cntjTransicionId, boolean modificaFecha, boolean modificaDatos) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
        this.cntjProcesoId = cntjProcesoId;
        this.cntjTransicionId = cntjTransicionId;
        this.modificaFecha = modificaFecha;
        this.modificaDatos = modificaDatos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
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

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public String getActivoStr(){
        if(activo){
            return "Si";
        }
        return "No";
    }

    public CntjProceso getCntjProcesoId() {
        return cntjProcesoId;
    }

    public void setCntjProcesoId(CntjProceso cntjProcesoId) {
        this.cntjProcesoId = cntjProcesoId;
    }

    public CntjTransicion getCntjTransicionId() {
        return cntjTransicionId;
    }

    public void setCntjTransicionId(CntjTransicion cntjTransicionId) {
        this.cntjTransicionId = cntjTransicionId;
    }

    public List<CntjEstadoGrupo> getListaEstadoGrupo() {
        return listaEstadoGrupo;
    }

    public void setListaEstadoGrupo(List<CntjEstadoGrupo> listaEstadoGrupo) {
        this.listaEstadoGrupo = listaEstadoGrupo;
    }

    public boolean isModificaFecha() {
        return modificaFecha;
    }

    public void setModificaFecha(boolean modificaFecha) {
        this.modificaFecha = modificaFecha;
    }

    public boolean isModificaDatos() {
        return modificaDatos;
    }

    public void setModificaDatos(boolean modificaDatos) {
        this.modificaDatos = modificaDatos;
    }

    public boolean isValidaGrupo() {
        return validaGrupo;
    }

    public void setValidaGrupo(boolean validaGrupo) {
        this.validaGrupo = validaGrupo;
    }

    public Integer getResultadoComite() {
        return resultadoComite;
    }

    public void setResultadoComite(Integer resultadoComite) {
        this.resultadoComite = resultadoComite;
    }
    
    
    
    public String getModificaFechaStr(){
        if(modificaFecha){
            return "Si";
        }
        return "No";
    }
    
    public String getModificaDatoStr(){
        if(modificaDatos){
            return "Si";
        }
        return "No";
    }
    
    public String getValidaGrupoStr(){
        if(validaGrupo){
            return "Si";
        }
        return "No";
    }

    @Override
    public String toString() {
        return "CntjEstado{" + "id=" + id + ", tipo=" + tipo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", activo=" + activo + ", cntjProcesoId=" + cntjProcesoId + ", cntjTransicionId=" + cntjTransicionId + ", listaEstadoGrupo=" + listaEstadoGrupo + ", modificaFecha=" + modificaFecha + ", modificaDatos=" + modificaDatos + ", validaGrupo=" + validaGrupo + ", resultadoComite=" + resultadoComite + '}';
    }

}
