package com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion;

import com.saviasaludeps.savia.dominio.mipres.MpConsumoFallo;
import java.io.Serializable;

public class JuntaProfesional implements Serializable {

    private JuntaProfesional2 junta_profesional;

    public JuntaProfesional2 getJunta_profesional() {
        return junta_profesional;
    }

    public void setJunta_profesional(JuntaProfesional2 junta_profesional) {
        this.junta_profesional = junta_profesional;
    }

    public MpConsumoFallo getFallo() {
        return fallo;
    }

    public void setFallo(MpConsumoFallo fallo) {
        this.fallo = fallo;
    }

    private MpConsumoFallo fallo;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("JuntaProfesional{junta_profesional=").append(junta_profesional);
        sb.append(", fallo=").append(fallo);
        sb.append('}');
        return sb.toString();
    }
    

}
