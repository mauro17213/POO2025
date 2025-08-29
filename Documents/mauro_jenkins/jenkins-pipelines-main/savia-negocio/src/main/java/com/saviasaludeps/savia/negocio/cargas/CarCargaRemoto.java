/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.saviasaludeps.savia.negocio.cargas;

import com.saviasaludeps.savia.dominio.cargas.CarCarga;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author aguevara
 */
public interface CarCargaRemoto {
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    List<CarCarga> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    CarCarga consultar(int id) throws Exception;
    
    boolean validarExiste(String nombre) throws Exception;
    
    int insertar(CarCarga obj) throws Exception;
    
    CarCarga eliminar(int id) throws Exception;
    
    void actualizar(CarCarga per) throws Exception;
    
    List<CarCarga> consultarListaPorUsuario(int id) throws Exception;
    
    List<CarCarga> consultarListaPorId(int id)throws Exception;
    
    List<CarCarga> consultarPorEstadoCola(int numeroMaximoRegistros)throws Exception;
    
    void actualizarEstado(CarCarga obj) throws Exception;
}
