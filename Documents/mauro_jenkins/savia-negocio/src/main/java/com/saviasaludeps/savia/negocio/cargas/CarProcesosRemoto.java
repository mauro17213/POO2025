/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.saviasaludeps.savia.negocio.cargas;

import com.saviasaludeps.savia.dominio.cargas.CarProceso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author aguevara
 */
public interface CarProcesosRemoto {
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    List<CarProceso> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    CarProceso consultar(int id) throws Exception;
    
    boolean validarExiste(String nombre) throws Exception;
    
    int insertar(CarProceso obj) throws Exception;
    
    CarProceso eliminar(int id) throws Exception;
    
    void actualizar(CarProceso per) throws Exception;
    
}
