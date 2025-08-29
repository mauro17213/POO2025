package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Objects;

public class CmRipsReglaEntrada extends Auditoria {

    private Integer id;
    private Integer tipo;
    private String archivo;
    private int posicion;
    private String campo;
    private CmRipsRegla cmRipsRegla;
    private Integer idInsertar;
    private Integer orden;
    
    private final static int TIPO_VARCHAR = 0;
    private final static int TIPO_NUMERICO = 1;

    public CmRipsReglaEntrada() {
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

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public CmRipsRegla getCmRipsRegla() {
        return cmRipsRegla;
    }

    public void setCmRipsRegla(CmRipsRegla cmRipsRegla) {
        this.cmRipsRegla = cmRipsRegla;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.tipo);
        hash = 79 * hash + Objects.hashCode(this.archivo);
        hash = 79 * hash + Objects.hashCode(this.campo);
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
        final CmRipsReglaEntrada other = (CmRipsReglaEntrada) obj;
        if (!Objects.equals(this.archivo, other.archivo)) {
            return false;
        }
        if (!Objects.equals(this.campo, other.campo)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.cmRipsRegla, other.cmRipsRegla)) {
            return false;
        }
        return true;
    }

    public Integer getIdInsertar() {
        return idInsertar;
    }

    public void setIdInsertar(Integer idInsertar) {
        this.idInsertar = idInsertar;
    }

    public String getTipoStr() {
        String str = "";
        switch (this.tipo) {
            case TIPO_VARCHAR:
                str = "VARCHAR";
                break;
            case TIPO_NUMERICO:
                str = "NUMERICO";
                break;
        }
        return str;
    }

}
