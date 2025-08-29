/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContactoConsulta;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucAfiliadoContacto;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface AucAfiliadoContactoRemoto {
    
    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucAfiliadoContacto consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AucAfiliadoContacto obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AucAfiliadoContacto obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucAfiliadoContacto eliminar(int id) throws Exception;
    
    /**
     * consulta la lista de contactos por id de la tabla auc_afiliado
     *
     * @param id
     * @return
     * @throws Exception
     */
    List<AucAfiliadoContacto> consultarLista(int id) throws Exception;
    
     /**
     * consulta la lista de contactos por id de la tabla aseg_afiliados
     *
     * @param id
     * @return
     * @throws Exception
     */
    List<AucAfiliadoContacto> consultarListaContactosPorAsegAfiliado(int id) throws Exception;
    
    
     /**
     * consulta la lista de contactos por id de la tabla aseg_afiliados
     *
     * @param id del aseg afiliado
     * @param numeroExcluidos para no tener en cuenta los numeros de telefonos
     * @return
     * @throws Exception
     */
    List<AsegAfiliadoContactoConsulta> consultarListaContactosPorAsegAfiliado(int id,  String numeroExcluidos) throws Exception;
}
