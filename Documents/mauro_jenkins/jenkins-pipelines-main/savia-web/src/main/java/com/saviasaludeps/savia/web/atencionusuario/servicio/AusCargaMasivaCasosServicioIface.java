/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.atencionusuario.servicio;

import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.web.atencionusuario.bean.AusCargaMasivaCasosBean;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface AusCargaMasivaCasosServicioIface {
    
    void Accion(AusCargaMasivaCasosBean bean);
    void cargaInicial(AusCargaMasivaCasosBean bean);
    //void guardar(AusCargaMasivaCasosBean bean, int idCargaMasiva);
    List<Date> obtenerFechas(Date fecha);
    MaDiagnostico obtenerDiagnostico(String diagnostico);
    
}
