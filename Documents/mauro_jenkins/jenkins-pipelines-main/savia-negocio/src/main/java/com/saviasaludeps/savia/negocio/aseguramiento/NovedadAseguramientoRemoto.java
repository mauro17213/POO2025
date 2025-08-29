/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.aseguramiento;

import com.saviasaludeps.savia.dominio.aseguramiento.NovedadAseguramiento;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface NovedadAseguramientoRemoto {    
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    List<NovedadAseguramiento> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    NovedadAseguramiento consultar(int id) throws Exception;
    
    int insertar(NovedadAseguramiento obj) throws Exception;
    
    void actualizar(NovedadAseguramiento obj) throws Exception;
    
    NovedadAseguramiento eliminar(int id) throws Exception;

}
