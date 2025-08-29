/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.servicio;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.web.maestro.bean.MaCargaMasivaBean;
import java.util.List;

public interface MaCargaMasivaServicioIface {
    
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(MaCargaMasivaBean bean);
    
    /**
     * Métodopara carhas inicial de variables
     * @param bean 
     */
    void cargaInicial(MaCargaMasivaBean bean);
    
    /**
     * Método para consultar lista de sedes por divipoli-Municipio
     * @param divipoliMunicipio 
     * @return  
     * @throws java.lang.Exception  
     */
    List<CntPrestadorSede> listarSedesPorMunicipio(String divipoliMunicipio) throws Exception;
}
