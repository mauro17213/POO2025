
package com.saviasaludeps.savia.dominio.administracion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.ArrayList;
import java.util.List;

public class MaestroTipoRelacion extends Auditoria {

    private int id;
    MaestroTipo maestroTipoPadre;
    MaestroTipo maestroTipoHijo;
    List<Maestro> maestros;
    List<Integer> seleccion;

    public MaestroTipoRelacion() {
        setSeleccion(new ArrayList());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MaestroTipo getMaestroTipoPadre() {
        return maestroTipoPadre;
    }

    public void setMaestroTipoPadre(MaestroTipo maestroTipoPadre) {
        this.maestroTipoPadre = maestroTipoPadre;
    }

    public MaestroTipo getMaestroTipoHijo() {
        return maestroTipoHijo;
    }

    public void setMaestroTipoHijo(MaestroTipo maestroTipoHijo) {
        this.maestroTipoHijo = maestroTipoHijo;
    }

    public List<Maestro> getMaestros() {
        return maestros;
    }

    public void setMaestros(List<Maestro> maestros) {
        this.maestros = maestros;
    }

    public List<Integer> getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(List<Integer> seleccion) {
        this.seleccion = seleccion;
    }

    @Override
    public String toString() {
        return "MaestroTipoRelacion{" + "id=" + id + ", maestroTipoPadre=" + maestroTipoPadre + ", maestroTipoHijo=" + maestroTipoHijo + '}';
    }

}
