
package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.juridico.CntjUsuarioGrupo;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CtnjUsuarioGrupoRemoto {
    
    
    /**
     * Guardar nuevo registros
     * @author idbohorquez
     * @return int
     * @creacion 09/08/2024
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjUsuarioGrupo objeto) throws Exception;

    /**
     * Consultar registro por id
     * @author idbohorquez
     * @param idUsuarioGrupo
     * @return int
     * @creacion 09/08/2024
     * @throws Exception 
     */
    CntjUsuarioGrupo consultar(int idUsuarioGrupo) throws Exception;
    
    /**
     * Actualizar registro
     * @author idbohorquez
     * @param objeto
     * @creacion 09/08/2024
     * @throws Exception 
     */
    void actualizar(CntjUsuarioGrupo objeto) throws Exception;
    
    /**
     * Eliminar registro
     * @author idbohorquez
     * @param id
     * @return 
     * @creacion 09/08/2024
     * @throws Exception 
     */
    CntjUsuarioGrupo eliminar(int id) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @param idgrupo
     * @return 
     * @creacion 09/08/2024
     * @throws Exception 
     */
    List<CntjUsuarioGrupo> listaUsuariosGrupo(int idgrupo) throws java.lang.Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @param idestado
     * @return 
     * @creacion 09/08/2024
     * @throws Exception 
     */
    List<CntjUsuarioGrupo> listarUsuariosGrupoPermisos(int idestado) throws java.lang.Exception;
    
    /**
     * Consultar registro
     * @author idbohorquez
     * @param idusuario
     * @return CntjUsuarioGrupo
     * @creacion 10/04/2025
     * @throws Exception 
     */
    CntjUsuarioGrupo consultarGrupoUsuario(int idusuario) throws java.lang.Exception;
    
}
