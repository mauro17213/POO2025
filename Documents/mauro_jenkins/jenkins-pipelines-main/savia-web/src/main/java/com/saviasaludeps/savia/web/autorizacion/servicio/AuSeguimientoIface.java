/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.web.autorizacion.bean.AuSeguimientoBean;

/**
 *
 * @author iavenegas
 */
public interface AuSeguimientoIface {

    /**
     *
     * @param bean
     */
    void Accion(AuSeguimientoBean bean);

    /**
     *
     * @param bean
     */
    void cargaInicial(AuSeguimientoBean bean);

    /**
     * lista para lazy gestiones
     *
     * @param bean
     */
    void listarGestiones(AuSeguimientoBean bean);

    /**
     * borrado logico de contacto
     *
     * @param bean
     */
    void borrarContacto(AuSeguimientoBean bean);

    /**
     * Borra el adjunto de base de datos
     *
     * @param id
     * @param bean
     */
    void borrarAdjunto(int id, AuSeguimientoBean bean);

    /**
     * maestros motivo gestion por tipo y padre estado gestion
     *
     * @param bean
     */
    void motivosGestionEstado(AuSeguimientoBean bean);

    /**
     * Listar prestador sede para lazy
     *
     * @param bean
     */
    void listarIPS(AuSeguimientoBean bean);
    
    /**
     * Listar contratos detalle para lazy selecionar ips
     * @param bean 
     */
    void listarContratoDetalle(AuSeguimientoBean bean);

    /**
     * consulta anexo 3 asociado
     *
     * @param bean
     */
    void verAnexo3(AuSeguimientoBean bean);
    
    void validarEstadoCancelar(AuSeguimientoBean bean);

}
