/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.administracion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author sgiraldov
 */
public class GnSedeHorario extends Auditoria {
    
    private static final String SI = "Sí";
    private static final String NO = "No";
    private static final SimpleDateFormat FORMATO_HORA = new SimpleDateFormat("HH:mm");

    private Integer id;
    private String dias;
    private Date horaDesde;
    private Date horaHasta;
    private boolean activo;
    private GnSede gnSedeId;
    
    //Variables auxiliares
    private boolean modificado;

    public GnSedeHorario() {
    }

    public GnSedeHorario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public Date getHoraDesde() {
        return horaDesde;
    }

    public void setHoraDesde(Date horaDesde) {
        this.horaDesde = horaDesde;
    }

    public Date getHoraHasta() {
        return horaHasta;
    }

    public void setHoraHasta(Date horaHasta) {
        this.horaHasta = horaHasta;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public GnSede getGnSedeId() {
        return gnSedeId;
    }

    public void setGnSedeId(GnSede gnSedeId) {
        this.gnSedeId = gnSedeId;
    }

    //Metodos Auxiliares
    public String getActivoStr() {
        if (isActivo()) {
            return SI;
        } else {
            return NO;
        }
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }
    
    //Metodos auxiliares
    public String getHoraDesdeStr() {
        return FORMATO_HORA.format(getHoraDesde());
    }
    
    public String getHoraHastaStr() {
        return FORMATO_HORA.format(getHoraHasta());
    }
    
    
}
