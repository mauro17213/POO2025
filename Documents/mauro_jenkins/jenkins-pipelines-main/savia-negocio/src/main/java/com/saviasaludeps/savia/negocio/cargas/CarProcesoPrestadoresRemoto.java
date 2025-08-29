/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.saviasaludeps.savia.negocio.cargas;

import com.saviasaludeps.savia.dominio.cargas.CarProcesoPrestador;
import com.saviasaludeps.savia.dominio.cargas.CarProcesoUsuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author aguevara
 */
public interface CarProcesoPrestadoresRemoto {
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    List<CarProcesoPrestador> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    CarProcesoPrestador consultar(int id) throws Exception;
    
    boolean validarExiste(String nombre) throws Exception;
    
    int insertar(CarProcesoPrestador obj) throws Exception;
    
    CarProcesoPrestador eliminar(int id) throws Exception;
    
    void actualizar(CarProcesoPrestador per) throws Exception;
    
    List<CarProcesoPrestador> listarPorIdProceso(int id) throws Exception;
    
    List<CarProcesoPrestador> consultarListaTotal(ParamConsulta paramConsulta) throws Exception;

    int consultarCantidadListaTotal(ParamConsulta paramConsulta) throws Exception;

    
}
