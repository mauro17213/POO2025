/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionHistorico;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionSeguimiento;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizacionHistoricos;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizacionSeguimientos;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizaciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionHistoricoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author pavacca
 */
@Stateless
@Remote(AucHospitalizacionHistoricoRemoto.class)
public class AucHospitalizacionHistoricoServicio extends GenericoServicio implements AucHospitalizacionHistoricoRemoto{
    
    @Override
    public AucHospitalizacionHistorico consultar(int id) throws Exception {
        AucHospitalizacionHistorico objRes = null;
        try {
            objRes = castEntidadNegocio((AucHospitalizacionHistoricos) getEntityManager().find(AucHospitalizacionHistoricos.class, id));
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
    public int insertar(AucHospitalizacionHistorico obj) throws Exception {
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
    public void actualizar(AucHospitalizacionHistorico obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizacionHistoricos a SET ";
            strQuery += "a.tostringHospitalizacion = :tostringHospitalizacion ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("tostringHospitalizacion", obj.getTostringHospitalizacion());
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
    public AucHospitalizacionHistorico eliminar(int id) throws Exception {
        AucHospitalizacionHistorico obj = null;
        try {
            AucHospitalizacionHistoricos ent = getEntityManager().find(AucHospitalizacionHistoricos.class, id);
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
    
    @Override
    public List<AucHospitalizacionHistorico> consultarHistoricoIdHopitalizacionSoloEgresos(AucHospitalizacionHistorico obj) throws Exception {
        List<AucHospitalizacionHistorico> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AucHospitalizacionHistoricos p "
                    + "WHERE p.aucHospitalizacionesId.id = " + obj.getAucHospitalizacionesId().getId() + " "
                    + "AND p.tostringHospitalizacion LIKE '%AucEgreso{%' "
                    + "ORDER BY p.id DESC";

            List<AucHospitalizacionHistoricos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AucHospitalizacionHistoricos seguimiento : list) {
                listaResultados.add(castEntidadNegocio(seguimiento));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }
    
    private AucHospitalizacionHistorico castEntidadNegocio(AucHospitalizacionHistoricos entidad) {
        AucHospitalizacionHistorico negocio = new AucHospitalizacionHistorico();
        negocio.setId(entidad.getId());
        if(entidad.getAucHospitalizacionesId() != null){
            negocio.setAucHospitalizacionesId(new AucHospitalizacion(entidad.getAucHospitalizacionesId().getId()));
        } 
        negocio.setTostringHospitalizacion(entidad.getTostringHospitalizacion());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }
    
    private AucHospitalizacionHistoricos castNegocioEntidad(AucHospitalizacionHistorico negocio) {
        AucHospitalizacionHistoricos entidad = new AucHospitalizacionHistoricos();
        negocio.setId(entidad.getId());
        if(negocio.getAucHospitalizacionesId() != null){
            entidad.setAucHospitalizacionesId(new AucHospitalizaciones(negocio.getAucHospitalizacionesId().getId()));
        }
        entidad.setTostringHospitalizacion(negocio.getTostringHospitalizacion());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());

        return entidad;
    }
}
