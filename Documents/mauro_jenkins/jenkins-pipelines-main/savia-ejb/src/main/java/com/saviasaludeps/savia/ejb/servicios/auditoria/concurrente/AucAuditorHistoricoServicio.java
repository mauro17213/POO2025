/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucAuditor;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucAuditorHistorico;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.AucAuditorHistoricos;
import com.saviasaludeps.savia.ejb.entidades.AucAuditores;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucAuditorHistoricoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author sgiraldov
 */
@Stateless
@Remote(AucAuditorHistoricoRemoto.class)
public class AucAuditorHistoricoServicio extends GenericoServicio implements AucAuditorHistoricoRemoto {

    @Override
    public AucAuditorHistorico consultar(int id) throws Exception {
        AucAuditorHistorico objRes = null;
        try {
            objRes = castEntidadNegocio((AucAuditorHistoricos) getEntityManager().find(AucAuditorHistoricos.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public int insertar(AucAuditorHistorico obj) throws Exception {
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

    @Override
    public void actualizar(AucAuditorHistorico obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucAuditorHistoricos a SET ";
            strQuery += "a.aucAuditoresId.id = :aucAuditoresId ,";
            strQuery += "a.fechaInicio = :fechaInicio ,";
            strQuery += "a.fechaFin = :fechaFin ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("aucAuditoresId", obj.getAucAuditorId().getId());
            query.setParameter("fechaInicio", obj.getFechaInicio());
            query.setParameter("fechaFin", obj.getFechaFin());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AucAuditorHistorico eliminar(int id) throws Exception {
        AucAuditorHistorico obj = null;
        try {
            AucAuditorHistoricos ent = getEntityManager().find(AucAuditorHistoricos.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            Exception(ELIMINAR, e);
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }
    
    private AucAuditorHistorico castEntidadNegocio(AucAuditorHistoricos entidad) {
        AucAuditorHistorico negocio = new AucAuditorHistorico();
        negocio.setId(entidad.getId());
        negocio.setAucAuditorId(new AucAuditor(entidad.getAucAuditoresId().getId()));
        negocio.setFechaInicio(entidad.getFechaInicio());
        negocio.setFechaFin(entidad.getFechaFin());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }
    
    private AucAuditorHistoricos castNegocioEntidad(AucAuditorHistorico negocio) {
        AucAuditorHistoricos entidad = new AucAuditorHistoricos();
        entidad.setAucAuditoresId(new AucAuditores(negocio.getAucAuditorId().getId()));
        entidad.setFechaInicio(negocio.getFechaInicio());
        entidad.setFechaFin(negocio.getFechaFin());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }
    
}
