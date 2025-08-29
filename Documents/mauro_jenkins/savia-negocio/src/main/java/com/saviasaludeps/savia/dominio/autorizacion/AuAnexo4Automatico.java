/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaA4AutomaticoDTO;

/**
 *
 * @author Stiven Giraldo
 */
public class AuAnexo4Automatico {
    
    private AuAnexo3 anexo3;
    private AuAnexo3Item item;
    private ValidaRespuestaA4AutomaticoDTO validacion;

    public AuAnexo4Automatico(AuAnexo3 anexo3, AuAnexo3Item item, ValidaRespuestaA4AutomaticoDTO validacion) {
        this.anexo3 = anexo3;
        this.item = item;
        this.validacion = validacion;
    }

    public AuAnexo3 getAnexo3() {
        return anexo3;
    }

    public void setAnexo3(AuAnexo3 anexo3) {
        this.anexo3 = anexo3;
    }

    public AuAnexo3Item getItem() {
        return item;
    }

    public void setItem(AuAnexo3Item item) {
        this.item = item;
    }

    public ValidaRespuestaA4AutomaticoDTO getValidacion() {
        return validacion;
    }

    public void setValidacion(ValidaRespuestaA4AutomaticoDTO validacion) {
        this.validacion = validacion;
    }

    
    
    
}
