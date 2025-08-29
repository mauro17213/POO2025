/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.saviasaludeps.savia.negocio.reserva;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.reserva.RtReserva;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface RtReservaRemoto {
    
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    public List<RtReserva> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    RtReserva consultar(int id) throws Exception;
    
    void cambiarEstadoReserva(RtReserva obj) throws Exception;
    
    
    
}
