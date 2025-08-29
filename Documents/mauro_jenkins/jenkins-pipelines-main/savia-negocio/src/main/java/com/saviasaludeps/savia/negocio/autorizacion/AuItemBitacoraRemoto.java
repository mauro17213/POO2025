/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuItemBitacora;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface AuItemBitacoraRemoto {

    /**
     * Inserta un objeto
     *
     * @param objeto
     * @return
     * @throws Exception
     */
    int insertar(AuItemBitacora objeto) throws Exception;

    /**
     * Lista bitacoras dado el id del item
     *
     * @param idItem
     * @return
     * @throws Exception
     */
    List<AuItemBitacora> listarPorIdItem(int idItem) throws Exception;

    /**
     * Punultima bitacora para regresar a estado anterior
     *
     * @param idItem
     * @return
     * @throws Exception
     */
    AuItemBitacora penultimaBitacora(int idItem) throws Exception;

}
