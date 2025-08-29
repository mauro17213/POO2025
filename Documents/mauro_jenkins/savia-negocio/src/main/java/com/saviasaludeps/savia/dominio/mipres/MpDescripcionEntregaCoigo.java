package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

public class MpDescripcionEntregaCoigo extends Auditoria {

    private String descripcion;
    private Integer codigo;

    public MpDescripcionEntregaCoigo() {
    }

    public MpDescripcionEntregaCoigo(String descripcion, Integer codigo) {
        this.descripcion = descripcion;
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

   

}
