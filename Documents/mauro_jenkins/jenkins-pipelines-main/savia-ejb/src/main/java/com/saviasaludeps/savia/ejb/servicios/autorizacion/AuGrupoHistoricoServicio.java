/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuGrupo;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoHistorico;
import com.saviasaludeps.savia.ejb.entidades.AuGrupoHistoricos;
import com.saviasaludeps.savia.ejb.entidades.AuGrupos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuGrupoHistoricoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Stiven Giraldo
 */
@Stateless
@Remote(AuGrupoHistoricoRemoto.class)
public class AuGrupoHistoricoServicio extends GenericoServicio implements AuGrupoHistoricoRemoto {

    @Override
    public AuGrupoHistorico consultar(int id) throws Exception {
        AuGrupoHistorico objRes = null;
        try {
            objRes = castEntidadNegocio((AuGrupoHistoricos) getEntityManager().find(AuGrupoHistoricos.class, id));
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
    public int insertar(AuGrupoHistorico obj) throws Exception {
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
    public void actualizar(AuGrupoHistorico obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuGrupoHistoricos SET ";
            strQuery += "a.auGruposId.id = :auGruposId ,";
            strQuery += "a.toString = :toString ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("auGruposId", obj.getAuGruposId().getId());
            query.setParameter("toString", obj.getToString());
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
    public AuGrupoHistorico eliminar(int id) throws Exception {
        AuGrupoHistorico obj = null;
        try {
            AuGrupoHistoricos ent = getEntityManager().find(AuGrupoHistoricos.class, id);
            if (ent != null){
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
    
    private AuGrupoHistorico castEntidadNegocio(AuGrupoHistoricos entidad) {
        AuGrupoHistorico negocio = new AuGrupoHistorico();
        negocio.setId(entidad.getId());
        negocio.setAuGruposId(new AuGrupo(entidad.getAuGruposId().getId()));
        negocio.setToString(entidad.getToString());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }
    
    private AuGrupoHistoricos castNegocioEntidad(AuGrupoHistorico negocio) {
        AuGrupoHistoricos entidad = new AuGrupoHistoricos();
        entidad.setId(negocio.getId());
        entidad.setAuGruposId(new AuGrupos(negocio.getAuGruposId().getId()));
        entidad.setToString(negocio.getToString());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

    @Override
    public List<AuGrupoHistorico> consultarListaPorIdGrupo(int idGrupo) throws Exception {
        List<AuGrupoHistorico> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuGrupoHistoricos p "
                    + "WHERE p.id > 0";
            strQuery += " AND p.auGruposId.id = " + idGrupo + " ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<AuGrupoHistoricos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuGrupoHistoricos historicos : list) {
                listaResultados.add(castEntidadNegocio(historicos));
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
}
