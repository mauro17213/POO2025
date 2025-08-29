/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.mapa;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.mapa.MapaAfiliado;
import com.saviasaludeps.savia.dominio.mapa.MapaPrestadorSede;
import java.util.List;

public interface MapaAfiliadoRemoto {
    
    /**
     * Consulta de lista de afiliados con georreferenciacion
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    public List<MapaAfiliado> consultarListaMapaAfiliados(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consulta de lista de afiliados con distancia a partir de una georeferenciacion
     * @param latitud
     * @param longitud
     * @param distancia
     * @return
     * @throws Exception 
     */
    public List<MapaAfiliado> consultarListaMapaAfiliados(double latitud, double longitud, int distancia) throws Exception;
    
    /**
     * 
     * @param id
     * @param municipioId
     * @return
     * @throws Exception 
     */
    public List<MapaPrestadorSede> consultarListaMapaPrestadorSedesIdMunicipio(Integer id, Integer municipioId) throws Exception;
    
}
