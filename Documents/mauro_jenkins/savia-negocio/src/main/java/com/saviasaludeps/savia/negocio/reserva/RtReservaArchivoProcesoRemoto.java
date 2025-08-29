/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.saviasaludeps.savia.negocio.reserva;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.reserva.RtReservaArchivoProceso;
import java.util.List;

/**
 *
 * @author aguevara
 */
public interface RtReservaArchivoProcesoRemoto {
    
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    List<RtReservaArchivoProceso> consultarLista(int idReservaArchivo) throws Exception;
    
    RtReservaArchivoProceso consulta(int id) throws Exception;
    
}
