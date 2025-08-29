package com.saviasaludeps.savia.dominio.administracion;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author jeperez
 */
public class GnUbicacionBarrio extends Auditoria {
    private Integer id;
    private int gnUbicacionesId;
    private String codigoBarrio;
    private String nombre;
    private String codigoComuna;
    private String comuna;
    //objetos
    private List<AsegAfiliado> listaAfiliados;

    public GnUbicacionBarrio() {
    }
    
    public GnUbicacionBarrio(Integer id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the gnUbicacionesId
     */
    public int getGnUbicacionesId() {
        return gnUbicacionesId;
    }

    /**
     * @param gnUbicacionesId the gnUbicacionesId to set
     */
    public void setGnUbicacionesId(int gnUbicacionesId) {
        this.gnUbicacionesId = gnUbicacionesId;
    }

    /**
     * @return the codigoBarrio
     */
    public String getCodigoBarrio() {
        return codigoBarrio;
    }

    /**
     * @param codigoBarrio the codigoBarrio to set
     */
    public void setCodigoBarrio(String codigoBarrio) {
        this.codigoBarrio = codigoBarrio;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the codigoComuna
     */
    public String getCodigoComuna() {
        return codigoComuna;
    }

    /**
     * @param codigoComuna the codigoComuna to set
     */
    public void setCodigoComuna(String codigoComuna) {
        this.codigoComuna = codigoComuna;
    }

    /**
     * @return the comuna
     */
    public String getComuna() {
        return comuna;
    }

    /**
     * @param comuna the comuna to set
     */
    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    /**
     * @return the listaAfiliados
     */
    public List<AsegAfiliado> getListaAfiliados() {
        return listaAfiliados;
    }

    /**
     * @param listaAfiliados the listaAfiliados to set
     */
    public void setListaAfiliados(List<AsegAfiliado> listaAfiliados) {
        this.listaAfiliados = listaAfiliados;
    }
    
    public String getNombreCompleto() {
        String mensaje = nombre;
        if (comuna != null && !comuna.equals("")) {
            mensaje = comuna + " - " + nombre;
        }
        return mensaje;
    }

    @Override
    public String toString() {
        return "GnUbicacionBarrio{" + "id=" + id + ", gnUbicacionesId=" + gnUbicacionesId + ", codigoBarrio=" + codigoBarrio + ", nombre=" + nombre + ", codigoComuna=" + codigoComuna + ", comuna=" + comuna + '}';
    }
}
