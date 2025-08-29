/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.webservices;

import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.webservices.FinancieroRemoto;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author yjimenez
 */
@Stateless
@Remote(FinancieroRemoto.class)
public class FinancieroServicio extends GenericoServicio implements FinancieroRemoto {

}
