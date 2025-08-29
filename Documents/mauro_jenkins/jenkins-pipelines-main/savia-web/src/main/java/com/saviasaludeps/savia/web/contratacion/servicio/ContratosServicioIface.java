/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.servicio;

import com.saviasaludeps.savia.dominio.maestro.MaSoatTarifarioValor;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologiaServicioHabilitacion;
import com.saviasaludeps.savia.web.contratacion.bean.ContratosBean;
import java.util.List;

/**
 *
 * @author José Pérez
 */
public interface ContratosServicioIface {
    
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(ContratosBean bean);
    
    /**
     * Método para carga inicial de variables
     * @param bean 
     */
    void cargaInicial(ContratosBean bean);
    
    /**
     * Método para obtener la lista de Servicios de Habilitación relacionados a la tecnología
     * @param idTecnologia 
     * @return  
     */
    List<MaTecnologiaServicioHabilitacion> consultarServiciosTecnologia (int idTecnologia);
    
    /**
     * Método para obtener la lista de Valores para un Tarifario de Soat
     * @param idSoatTarifario
     * @return 
     */
    List<MaSoatTarifarioValor> consultarValoresSoat(int idSoatTarifario);
    
}
