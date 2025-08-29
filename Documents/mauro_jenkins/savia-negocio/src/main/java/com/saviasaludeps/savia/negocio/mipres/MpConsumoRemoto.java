/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.saviasaludeps.savia.negocio.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpConsumo;
import com.saviasaludeps.savia.dominio.mipres.MpConsumoFallo;
import java.util.Date;

/**
 *
 * @author rpalacios
 */
public interface MpConsumoRemoto {
    
    /**
     * Consultar el último consumo exitoso de un servicio
     * @param servicio
     * @return
     * @throws Exception 
     */
    public MpConsumo consultarUltimoConsumoExitoso(String servicio) throws Exception;
    
    /**
     * Consultar Si Es El Primer consumo exitoso del dia
     * @param servicio
     * @return
     * @throws Exception 
     */
    public Boolean consultarPrimerConsumoExitosoDia(String servicio,Date fechao) throws Exception;
    
    /**
     * Insertar consumo
     * @param mpConsumo
     * @return
     * @throws Exception 
     */
    public Integer insertar(MpConsumo mpConsumo) throws Exception;
    
    /**
     * Actualizar consumo
     * @param obj
     * @throws Exception 
     */
    public void actualizar(MpConsumo obj) throws Exception;
    
    /**
     * Insertar consumo fallo
     * @param fallo
     * @return
     * @throws Exception 
     */
    public Integer insertarFallo(MpConsumoFallo fallo) throws Exception;
    
}
