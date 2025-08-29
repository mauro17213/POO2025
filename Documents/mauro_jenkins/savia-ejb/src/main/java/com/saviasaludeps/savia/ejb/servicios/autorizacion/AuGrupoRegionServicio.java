/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuGrupo;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoRegion;
import com.saviasaludeps.savia.ejb.entidades.AuGrupoRegiones;
import com.saviasaludeps.savia.ejb.entidades.AuGrupos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuGrupoRegionRemoto;
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
@Remote(AuGrupoRegionRemoto.class)
public class AuGrupoRegionServicio extends GenericoServicio implements AuGrupoRegionRemoto {

    @Override
    public AuGrupoRegion consultar(int id) throws Exception {
        AuGrupoRegion objRes = null;
        try {
            objRes = castEntidadNegocio((AuGrupoRegiones) getEntityManager().find(AuGrupoRegiones.class, id));
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
    public int insertar(AuGrupoRegion obj) throws Exception {
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
    public void actualizar(AuGrupoRegion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuGrupoRegiones SET ";
            strQuery += "a.auGruposId.id = :auGruposId ,";
            strQuery += "a.maeRegimenId = :maeRegimenId ,";
            strQuery += "a.maeRegimenCodigo = :maeRegimenId ,";
            strQuery += "a.maeRegimenValor = :maeRegimenValor ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("auGruposId", obj.getAuGrupo().getId());
            query.setParameter("maeRegimenId", obj.getMaeRegionId());
            query.setParameter("maeRegimenId", obj.getMaeRegionCodigo());
            query.setParameter("maeRegimenValor", obj.getMaeRegionValor());
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
    public AuGrupoRegion eliminar(int id) throws Exception {
        AuGrupoRegion obj = null;
        try {
            AuGrupoRegiones ent = getEntityManager().find(AuGrupoRegiones.class, id);
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
    
    private AuGrupoRegion castEntidadNegocio(AuGrupoRegiones entidad) {
        AuGrupoRegion negocio = new AuGrupoRegion();
        negocio.setId(entidad.getId());
        negocio.setAuGrupo(new AuGrupo(entidad.getAuGruposId().getId()));
        negocio.setMaeRegionId(entidad.getMaeRegionId());
        negocio.setMaeRegionCodigo(entidad.getMaeRegionCodigo());
        negocio.setMaeRegionValor(entidad.getMaeRegionValor());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }
    
    private AuGrupoRegiones castNegocioEntidad(AuGrupoRegion negocio) {
        AuGrupoRegiones entidad = new AuGrupoRegiones();
        entidad.setId(negocio.getId());
        entidad.setAuGruposId(new AuGrupos(negocio.getAuGrupo().getId()));
        entidad.setMaeRegionId(negocio.getMaeRegionId());
        entidad.setMaeRegionCodigo(negocio.getMaeRegionCodigo());
        entidad.setMaeRegionValor(negocio.getMaeRegionValor());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

    @Override
    public List<AuGrupoRegion> consultarListaPorIdGrupo(int idGrupo) throws Exception {
        List<AuGrupoRegion> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuGrupoRegiones p "
                    + "WHERE p.id > 0";
            strQuery += " AND p.auGruposId.id = " + idGrupo + " ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<AuGrupoRegiones> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuGrupoRegiones regiones : list) {
                listaResultados.add(castEntidadNegocio(regiones));
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
