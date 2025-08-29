package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author Bsgomez
 */
public class MpHomologacion extends Auditoria {

    private Integer id;
    private int tipo;
    private String codigo;
    private String nombre;
    private String descripcion;

    public MpHomologacion() {
    }

    public MpHomologacion(Integer id) {
        this.id = id;
    }

    public MpHomologacion(Integer id, int tipo, String codigo, String nombre, String descripcion) {
        this.id = id;
        this.tipo = tipo;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "MpHomologacion{" + "id=" + id + ", tipo=" + tipo + ", codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }

}
