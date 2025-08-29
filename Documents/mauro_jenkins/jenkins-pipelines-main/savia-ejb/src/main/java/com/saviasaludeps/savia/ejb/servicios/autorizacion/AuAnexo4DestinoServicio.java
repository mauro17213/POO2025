/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Destino;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Zona;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4Destinos;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4Zonas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4DestinoRemoto;
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
@Remote(AuAnexo4DestinoRemoto.class)
public class AuAnexo4DestinoServicio extends GenericoServicio implements AuAnexo4DestinoRemoto {

    @Override
    public int insertar(AuAnexo4Destino obj) throws Exception {
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
    public List<AuAnexo4Destino> consultarListaPorIdZona(int idZona) throws Exception {
        List<AuAnexo4Destino> anexo4Destinos = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo4Destinos p "
                    + "WHERE p.auAnexo3ZonasId.id = " + idZona + " "
                    + "ORDER BY p.orden ASC";
            List<AuAnexo4Destinos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();

            for (AuAnexo4Destinos anexos4Zona : list) {
                anexo4Destinos.add(castEntidadNegocio(anexos4Zona));
            }
        } catch (NoResultException e) {
            anexo4Destinos = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
            anexo4Destinos = new ArrayList();
        } finally {
            cerrarEntityManager();
        }
        return anexo4Destinos;
    }

    @Override
    public AuAnexo4Destino eliminar(int id) throws Exception {
        AuAnexo4Destino obj = null;
        try {
            AuAnexo4Destinos ent = getEntityManager().find(AuAnexo4Destinos.class, id);
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

    @Override
    public void actualizar(AuAnexo4Destino obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexo4Destinos a SET ";
            strQuery += "a.ubicacionId = :ubicacionId ,";
            strQuery += "a.ubicacionValor = :ubicacionValor ,";
            strQuery += "a.orden = :orden ,";
            strQuery += "a.activo = :activo ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("ubicacionId", obj.getUbicacionId());
            query.setParameter("ubicacionValor", obj.getUbicacionValor());
            query.setParameter("orden", obj.getOrden());
            query.setParameter("activo", obj.isActivo());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
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
    public AuAnexo4Destino consultar(int id) throws Exception {
        AuAnexo4Destino objRes = null;
        try {
            objRes = castEntidadNegocio((AuAnexo4Destinos) getEntityManager().find(AuAnexo4Destinos.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }
    
    private AuAnexo4Destino castEntidadNegocio(AuAnexo4Destinos entidad) {
        AuAnexo4Destino negocio = new AuAnexo4Destino();
        negocio.setId(entidad.getId());
        negocio.setUbicacionId(entidad.getUbicacionId());
        negocio.setUbicacionValor(entidad.getUbicacionValor());
        negocio.setOrden(entidad.getOrden());
        negocio.setActivo(entidad.getActivo());
        negocio.setAuAnexo3ZonaId(new AuAnexo4Zona(entidad.getAuAnexo3ZonasId().getId()));
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }
    
    private AuAnexo4Destinos castNegocioEntidad(AuAnexo4Destino negocio) {
        AuAnexo4Destinos entidad = new AuAnexo4Destinos();
        entidad.setId(negocio.getId());
        entidad.setUbicacionId(negocio.getUbicacionId());
        entidad.setUbicacionValor(negocio.getUbicacionValor());
        entidad.setOrden(negocio.getOrden());
        entidad.setActivo(negocio.isActivo());
        entidad.setAuAnexo3ZonasId(new AuAnexo4Zonas(negocio.getAuAnexo3ZonaId().getId()));
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        return entidad;
    }
}
