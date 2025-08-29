/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntProfesionalPrestador;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface CntPrestadorProfesionalRemoto {
    
    /**
     * Consulta todos los CntProfesionalPrestador por CntProfesional
     * @param id
     * @return
     * @throws Exception 
     */
    List<CntProfesionalPrestador> consultarPorProfesional(int id) throws Exception;
    
    CntProfesionalPrestador consultarPorProfesionalYPrestador(int idProfesional, int idPrestador) throws Exception;
    
    int insertar(CntProfesionalPrestador obj) throws Exception;
    
    void actualizar(CntProfesionalPrestador obj) throws Exception;
    
    CntProfesionalPrestador eliminar(int id) throws Exception;
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    List<CntProfesionalPrestador> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    List<CntProfesionalPrestador> consultarListaPorProfesionalYPrestador(int idProfesional, int idPrestador) throws Exception;
    
    CntProfesionalPrestador consultar(int id) throws Exception;
}
