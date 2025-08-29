/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.crue;

import com.saviasaludeps.savia.dominio.crue.RefTransporte;
import com.saviasaludeps.savia.dominio.crue.RefTransporteInsumo;
import com.saviasaludeps.savia.ejb.entidades.RefTransporteInsumos;
import com.saviasaludeps.savia.ejb.entidades.RefTransportes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.crue.RefTransporteInsumoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(RefTransporteInsumoRemoto.class)
@Local(RefTransporteInsumoLocal.class)
public class RefTransporteInsumoServicio extends GenericoServicio implements RefTransporteInsumoRemoto, RefTransporteInsumoLocal {

    @Override
    public List<RefTransporteInsumo> consultarPorRefTransporteId(int id) throws Exception {
        List<RefTransporteInsumo> listResult = new ArrayList<>();
        try {
            String strQuery = "SELECT r FROM RefTransporteInsumos r "
                    + "WHERE r.refTransportesId.id = :id ";
            strQuery += "ORDER BY r.id";
            List<RefTransporteInsumos> list = getEntityManager().createQuery(strQuery)
                    .setParameter("id", id)
                    .getResultList();
            for (RefTransporteInsumos per : list) {
                listResult.add(castEntidadNegocio(per));
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

    @Override
    public int insertar(RefTransporteInsumo obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static RefTransporteInsumos castNegocioEntidad(RefTransporteInsumo obj) {
        RefTransporteInsumos ent = new RefTransporteInsumos();
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setId(obj.getId());
        ent.setMaInsumoCodigo(obj.getMaInsumoCodigo());
        ent.setMaInsumoId(obj.getMaInsumoId());
        ent.setMaInsumoValor(obj.getMaInsumoValor());
        if (obj.getRefTransporte() != null) {
            ent.setRefTransportesId(new RefTransportes(obj.getRefTransporte().getId()));
        }
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        return ent;
    }
    
    public static RefTransporteInsumo castEntidadNegocio(RefTransporteInsumos ent) {
        RefTransporteInsumo obj = new RefTransporteInsumo();
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setId(ent.getId());
        obj.setMaInsumoCodigo(ent.getMaInsumoCodigo());
        obj.setMaInsumoValor(ent.getMaInsumoValor());
        obj.setMaInsumoId(ent.getMaInsumoId());
        if (ent.getRefTransportesId() != null) {
            obj.setRefTransporte(new RefTransporte(ent.getRefTransportesId().getId()));
        }
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        return obj;
    }
}
