/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContactoConsulta;
import com.saviasaludeps.savia.dominio.especial.PeTelefono;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface PeTelefonosRemoto {
    
    PeTelefono consultarTelefonoAfiliado(int id_afiliado, int tipo_telefono, String numero) throws Exception;
    
    int insertarTelefonosAfiliadosProgramas(PeTelefono peTelefono) throws Exception;
    
    void insertarTelefonosPorLotes(List<PeTelefono> telefonos);
            
    List<PeTelefono> getListaContatoAfiliado(int id_afiliado,String telefonos_existentes) throws Exception;
    
    PeTelefono eliminar(int id) throws Exception;
    
    List<AsegAfiliadoContactoConsulta> getListaContactoAfiliado(int id_afiliado, String telefonosExistentes) throws java.lang.Exception;
    
}
