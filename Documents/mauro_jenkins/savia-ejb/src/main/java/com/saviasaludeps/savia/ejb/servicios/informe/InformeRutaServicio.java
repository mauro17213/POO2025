/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.informe;

import com.saviasaludeps.savia.dominio.informe.InfRuta;
import com.saviasaludeps.savia.ejb.entidades.InfRutas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.informe.InformeRutaRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author sgiraldov
 */
@Stateless
@Remote(InformeRutaRemoto.class)
public class InformeRutaServicio extends GenericoServicio implements InformeRutaRemoto {

    @Override
    public List<InfRuta> consultarTodas() throws Exception {
        List<InfRuta> listResult = new ArrayList();
        try {
            String strQuery = "FROM InfRutas g "
                    + "ORDER BY g.nombre ASC";

            List<InfRutas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (InfRutas ent : list) {
                listResult.add(castEntidadNegocio(ent));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
    private InfRuta castEntidadNegocio(InfRutas entidad) {
        InfRuta negocio = new InfRuta();
        negocio.setId(entidad.getId());
        negocio.setNombre(entidad.getNombre());
        negocio.setRuta(entidad.getRuta() != null ? entidad.getRuta().toLowerCase() : "");
        return negocio;
    }
}
