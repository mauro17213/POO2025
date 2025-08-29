/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.atencionusuario;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

public class AusPersonaDocumento extends Auditoria {

     private Integer id;
    private int maeTipoDocumento;
    private String documento;
    private AusPersona ausPersona;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeTipoDocumento() {
        return maeTipoDocumento;
    }

    public void setMaeTipoDocumento(int maeTipoDocumento) {
        this.maeTipoDocumento = maeTipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public AusPersona getAusPersona() {
        return ausPersona;
    }

    public void setAusPersona(AusPersona ausPersona) {
        this.ausPersona = ausPersona;
    }

    @Override
    public String toString() {
        return "AusPersonaDocumento{" + "id=" + id + ", maeTipoDocumento=" + maeTipoDocumento + ", documento=" + documento + ", ausPersona=" + ausPersona + '}';
    }


    
}
