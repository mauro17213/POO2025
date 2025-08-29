/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.servicio;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.web.aseguramiento.bean.CargaMasivaBean;
import java.util.List;

/**
 *
 * @author José Pérez
 */
public interface CargaMasivaServicioIface {
    
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(CargaMasivaBean bean);
    
    /**
     * Métodopara carhas inicial de variables
     * @param bean 
     */
    void cargaInicial(CargaMasivaBean bean);
    
    /**
     * Método para consultar lista de sedes por divipoli-Municipio
     * @param divipoliMunicipio 
     * @return  
     * @throws java.lang.Exception  
     */
    List<CntPrestadorSede> listarSedesPorMunicipio(String divipoliMunicipio) throws Exception;
}
