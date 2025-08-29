/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.saviasaludeps.savia.negocio.cargas;

import com.saviasaludeps.savia.dominio.cargas.CarCargaRegistro;
import com.saviasaludeps.savia.dominio.cargas.CarProceso;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author aguevara
 */
public interface CarCargaGestionRemoto {
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    List<CarCargaRegistro> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    CarCargaRegistro consultar(int id) throws Exception;
    
    boolean validarExiste(String nombre) throws Exception;
    
    int insertar(CarCargaRegistro obj) throws Exception;
    
    CarCargaRegistro eliminar(int id) throws Exception;
    
    void actualizar(CarCargaRegistro per) throws Exception;
    
    List<CarCargaRegistro> consultarPorIdCarga(int id) throws Exception;
    
    List<CarCargaRegistro> consultarListaTipoCarga(int tipo, int idCarga) throws Exception;    
    
    List<CarProceso> listarProcesosUnicosPorUsuario(Long idUsuario, ParamConsulta paramConsulta) throws Exception;    
    
    List<CntPrestador> listarPrestadoresUnicosPorRegistros(ParamConsulta paramConsulta) throws Exception;
}
