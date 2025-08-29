/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.web.autorizacion.bean.AuTopeAfiliadoBean;

/**
 *
 * @author NEXOS
 */
public interface AuTopeAfiliadoIface {

    void Accion(AuTopeAfiliadoBean bean);

    void cargaInicial(AuTopeAfiliadoBean bean);

    void listarAfiliado(AuTopeAfiliadoBean bean);

    boolean validarEstadoAfiliado(int maeEstadoAfiliacion, int idAfiliado, AuTopeAfiliadoBean bean);

}
