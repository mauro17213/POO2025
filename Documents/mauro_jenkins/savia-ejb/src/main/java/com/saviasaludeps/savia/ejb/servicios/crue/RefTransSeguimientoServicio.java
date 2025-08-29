/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.crue;

import com.saviasaludeps.savia.dominio.crue.RefTransporte;
import com.saviasaludeps.savia.dominio.crue.RefTransporteSeguimiento;
import com.saviasaludeps.savia.ejb.entidades.RefTransporteSeguimientos;
import com.saviasaludeps.savia.ejb.entidades.RefTransportes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.crue.RefTransSeguimientoRemoto;
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
@Remote(RefTransSeguimientoRemoto.class)
@Local(RefTransSeguimientoLocal.class)
public class RefTransSeguimientoServicio extends GenericoServicio implements RefTransSeguimientoRemoto, RefTransSeguimientoLocal {

    @Override
    public List<RefTransporteSeguimiento> consultarByRefTransporte(int refTransporteId) throws Exception {
        List<RefTransporteSeguimiento> listResult = new ArrayList<>();
        try {
            String strQuery = "SELECT r FROM RefTransporteSeguimientos r "
                    + "WHERE r.refTransportesId.id = :id ";
            strQuery += "ORDER BY r.id";
            List<RefTransporteSeguimientos> list = getEntityManager().createQuery(strQuery)
                    .setParameter("id", refTransporteId)
                    .getResultList();
            for (RefTransporteSeguimientos per : list) {
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
    public int insertar(RefTransporteSeguimiento obj) throws Exception {
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

    public static RefTransporteSeguimiento castEntidadNegocio(RefTransporteSeguimientos ent) {
        RefTransporteSeguimiento obj = new RefTransporteSeguimiento();
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setId(ent.getId());
        obj.setMaeTipoReporteCodigo(ent.getMaeTipoReporteCodigo());
        obj.setMaeTipoReporteId(ent.getMaeTipoReporteId());
        obj.setMaeTipoReporteValor(ent.getMaeTipoReporteValor());
        obj.setObservacion(ent.getObservacion());
        if (ent.getRefTransportesId() != null) {
            obj.setRefTransporte(new RefTransporte(ent.getRefTransportesId().getId()));
        }
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setUsuarioCrea(ent.getUsuarioCrea());

        return obj;
    }
    
    public static RefTransporteSeguimientos castNegocioEntidad(RefTransporteSeguimiento obj) {
        RefTransporteSeguimientos ent = new RefTransporteSeguimientos();
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setId(obj.getId());
        ent.setMaeTipoReporteCodigo(obj.getMaeTipoReporteCodigo());
        ent.setMaeTipoReporteId(obj.getMaeTipoReporteId());
        ent.setMaeTipoReporteValor(obj.getMaeTipoReporteValor());
        ent.setObservacion(obj.getObservacion());
        if (obj.getRefTransporte() != null) {
            ent.setRefTransportesId(new RefTransportes(obj.getRefTransporte().getId()));
        }
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setUsuarioCrea(obj.getUsuarioCrea());        
        return ent;
    }
}
