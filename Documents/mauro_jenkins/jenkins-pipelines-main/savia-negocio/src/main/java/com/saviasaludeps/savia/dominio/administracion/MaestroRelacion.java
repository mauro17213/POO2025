
package com.saviasaludeps.savia.dominio.administracion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

public class MaestroRelacion extends Auditoria {

    private int id;
    Maestro maestroPadre;
    Maestro maestroHijo;

    public MaestroRelacion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Maestro getMaestroPadre() {
        return maestroPadre;
    }

    public void setMaestroPadre(Maestro maestroPadre) {
        this.maestroPadre = maestroPadre;
    }

    public Maestro getMaestroHijo() {
        return maestroHijo;
    }

    public void setMaestroHijo(Maestro maestroHijo) {
        this.maestroHijo = maestroHijo;
    }

        
    @Override
    public String toString() {
        return "MaestroTipoRelacion{" + "id=" + id + ", maestroTipoPadre=" + maestroPadre + ", maestroTipoHijo=" + maestroHijo + '}';
    }

}
