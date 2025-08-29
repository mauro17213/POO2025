/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.servicio;

import com.saviasaludeps.savia.web.administracion.bean.PerfilBean;
import com.saviasaludeps.savia.dominio.administracion.Perfil;
import java.util.List;

/**
 *
 * @author raul-palacios
 */
public interface PerfilServicioIface {

    void Accion(PerfilBean bean);

    List<Perfil> consultarLista();
}
