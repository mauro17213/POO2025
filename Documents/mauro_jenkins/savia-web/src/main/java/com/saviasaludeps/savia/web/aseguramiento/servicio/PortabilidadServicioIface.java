/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.servicio;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.web.aseguramiento.bean.PortabilidadBean;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface PortabilidadServicioIface {
    
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(PortabilidadBean bean);
    
    /**
     * Métodopara carhas inicial de variables
     * @param bean 
     */
    void cargaInial(PortabilidadBean bean);
    
    /**
     * Método para consultar lista de sedes por divipoli-Municipio
     * @param divipoliMunicipio 
     * @return  
     * @throws java.lang.Exception  
     */
    List<CntPrestadorSede> listarSedesPorMunicipio(String divipoliMunicipio) throws Exception;
    
    /**
     * Consultya de un afiliado por documento
     * @param tipoDocumento
     * @param numeroDocumento
     * @return
     * @throws Exception 
     */
    AsegAfiliado consultarAfiliado(int tipoDocumento, String numeroDocumento) throws Exception;  
  
    
    /**
     * 
     * @param bean 
     */
    public void reportePortabilidad(PortabilidadBean bean);
    
    /**
     * 
     * @param maeEstadoAfiliacion
     * @param bean
     * @return 
     */
    public boolean validarEstadoAfiliado(int maeEstadoAfiliacion, PortabilidadBean bean);
    
    /**
     * 
     * @param afiliado
     * @param bean
     * @return 
     */
    public boolean validarPortabilidadesPendientesAfiliado(AsegAfiliado afiliado, PortabilidadBean bean);

    /**
     * Metodo encargado de listar las ips segun el municipio seleccionado
     * @author idbohorquez
     * @fechaCreacion 04/04/2023
     * @param bean
     * 
     */
    public void consultarSedesMunicipio(PortabilidadBean bean);
}
