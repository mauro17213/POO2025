/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.saviasaludeps.savia.negocio.cargas;

import com.saviasaludeps.savia.dominio.cargas.CarPeriodo;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author aguevara
 */
public interface CarPeriodoRemoto {
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    List<CarPeriodo> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    CarPeriodo consultar(int id) throws Exception;
    
    boolean validarExiste(String nombre) throws Exception;
    
    int insertar(CarPeriodo obj) throws Exception;
    
    CarPeriodo eliminar(int id) throws Exception;
    
    void actualizar(CarPeriodo per) throws Exception;
    
    List<CarPeriodo> listarPorIdProceso(int id) throws Exception;
    
    int consultarCantidadListaTotal(ParamConsulta paramConsulta) throws Exception;
    
    List<CarPeriodo> consultarListaTotal(ParamConsulta paramConsulta) throws Exception;
    
}
