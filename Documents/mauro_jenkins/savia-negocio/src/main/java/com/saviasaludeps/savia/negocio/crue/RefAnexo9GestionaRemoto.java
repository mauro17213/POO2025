/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.crue;

import com.saviasaludeps.savia.dominio.crue.RefAnexo9Gestion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface RefAnexo9GestionaRemoto {
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    List<RefAnexo9Gestion> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    int insertar(RefAnexo9Gestion obj) throws Exception;
    
    RefAnexo9Gestion consultarPorRefAnexo9(int RefAnexo9Id) throws Exception;
    
    RefAnexo9Gestion consultarPorRefAnexo9GestionRegulacion(int RefAnexo9Id) throws Exception;
    
    List<RefAnexo9Gestion> consultarPorRefAnexo9YEstado(int refAnexo9Id, String maeTipoTipo, String maeTipo) throws Exception;
    
    List<RefAnexo9Gestion> consultarPorRefAnexo9PorId(int refAnexo9Id) throws Exception;
      
    RefAnexo9Gestion consultarPorRefAnexo9UltimoDireccionamiento(int RefAnexo9Id) throws Exception;
    
    RefAnexo9Gestion consultarPorRefAnexo9EstadoCancelada(int RefAnexo9Id) throws Exception;
    
    RefAnexo9Gestion consultarPorRefAnexo9EstadoCerrado(int RefAnexo9Id) throws Exception;
}
