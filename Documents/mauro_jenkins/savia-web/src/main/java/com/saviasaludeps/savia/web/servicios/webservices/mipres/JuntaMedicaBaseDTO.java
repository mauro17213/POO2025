package com.saviasaludeps.savia.web.servicios.webservices.mipres;

import java.io.Serializable;

public class JuntaMedicaBaseDTO implements Serializable {

    private JuntaMedicaDetalleDTO junta_profesional;

    public JuntaMedicaDetalleDTO getJunta_profesional() {
        return junta_profesional;
    }

    public void setJunta_profesional(JuntaMedicaDetalleDTO junta_profesional) {
        this.junta_profesional = junta_profesional;
    }

}
