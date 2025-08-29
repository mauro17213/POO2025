package com.saviasaludeps.savia.negocio.atencionusuario;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContactoConsulta;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersonaTelefono;
import java.util.List;

public interface AusPersonaTelefonoRemoto {
        
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    AusPersonaTelefono consultar(int id) throws Exception;
      
    /**
     * Método para eliminar un registro
     * @param id
     * @return (Personas) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    AusPersonaTelefono eliminar(int id) throws Exception;
    
    /**
     * Consultar todas las Personas
     * @param idAsegAfilido
     * @param numeroExcluidos Envian numero para no tenerlos en cuenta
     * @return
     * @throws Exception 
     */
    List<AsegAfiliadoContactoConsulta> consultarListaContactosPorAsegAfiliado(Integer idAsegAfilido, String numeroExcluidos) throws Exception;
    
    
  
}