package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Objects;

public class CmRipsReglaSalida extends Auditoria {

    private Integer id;
    private int codigo;
    private String descripcion;
    private CmRipsRegla cmRipsRegla;
    private Integer idInsertar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CmRipsRegla getCmRipsRegla() {
        return cmRipsRegla;
    }

    public void setCmRipsRegla(CmRipsRegla cmRipsRegla) {
        this.cmRipsRegla = cmRipsRegla;
    }

    public Integer getIdInsertar() {
        return idInsertar;
    }

    public void setIdInsertar(Integer idInsertar) {
        this.idInsertar = idInsertar;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + this.codigo;
        hash = 29 * hash + Objects.hashCode(this.descripcion);
        hash = 29 * hash + Objects.hashCode(this.cmRipsRegla);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CmRipsReglaSalida other = (CmRipsReglaSalida) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.cmRipsRegla, other.cmRipsRegla)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CmRipsReglaSalida{" + "id=" + id + ", codigo=" + codigo + ", descripcion=" + descripcion + ", cmRipsRegla=" + cmRipsRegla.getId() + '}';
    }

}
