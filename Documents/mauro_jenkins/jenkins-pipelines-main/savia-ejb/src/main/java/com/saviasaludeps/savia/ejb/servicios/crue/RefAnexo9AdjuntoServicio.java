/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.crue;

import com.saviasaludeps.savia.dominio.crue.RefAnexo9;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9Adjunto;
import com.saviasaludeps.savia.ejb.entidades.RefAnexo9Adjuntos;
import com.saviasaludeps.savia.ejb.entidades.RefAnexos9;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.crue.RefAnexo9AdjuntoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(RefAnexo9AdjuntoRemoto.class)
@Local(RefAnexo9AdjuntoLocal.class)
public class RefAnexo9AdjuntoServicio extends GenericoServicio implements RefAnexo9AdjuntoRemoto, RefAnexo9AdjuntoLocal {

    @Override
    public int insertar(RefAnexo9Adjunto obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public List<RefAnexo9Adjunto> consultarPorRefAnexo9(int idRefAnexo9) throws Exception {
        List<RefAnexo9Adjunto> listaResultado = new ArrayList<>();

        try {
            String strQuery = "SELECT r FROM RefAnexo9Adjuntos r "
                    + "WHERE R.refAnexos9Id.id = :id "
                    + "ORDER BY r.fechaHoraCrea desc ";
            List<RefAnexo9Adjuntos> list = getEntityManager().createQuery(strQuery)
                    .setParameter("id", idRefAnexo9)
                    .getResultList();

            for (RefAnexo9Adjuntos refAnexo9Adjuntos : list) {
                listaResultado.add(castEntidadNegocio(refAnexo9Adjuntos));
            }
        } catch (NoResultException e) {
            listaResultado = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultado;
    }
    
    @Override
    public void borradoLogico(RefAnexo9Adjunto obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE RefAnexo9Adjuntos a SET ";
            strQuery += "a.borrado = :borrado ,";
            strQuery += "a.borradoObservacion = :borradoObservacion ,";
            strQuery += "a.usuarioBorra = :usuarioBorra ,";
            strQuery += "a.terminalBorra = :terminalBorra ,";
            strQuery += "a.fechaHoraBorra = :fechaHoraBorra ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("borrado", obj.getBorrado());
            query.setParameter("borradoObservacion", obj.getBorradoObservacion());
            query.setParameter("usuarioBorra", obj.getUsuarioModifica());
            query.setParameter("terminalBorra", obj.getTerminalModifica());
            query.setParameter("fechaHoraBorra", obj.getFechaHoraModifica());
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
    
    public static RefAnexo9Adjuntos castNegocioEntidad(RefAnexo9Adjunto obj) {
        RefAnexo9Adjuntos ent = new RefAnexo9Adjuntos();
        ent.setArchivo(obj.getArchivo());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setId(obj.getId());
        ent.setMaeTipoArchivoCodigo(obj.getMaeTipoArchivoCodigo());
        ent.setMaeTipoArchivoId(obj.getMaeTipoArchivoId());
        ent.setMaeTipoArchivoValor(obj.getMaeTipoArchivoValor());
        ent.setNombreArchivo(obj.getNombreArchivo());
        ent.setExiste(obj.getExiste());
        if (obj.getRefAnexo9() != null && obj.getRefAnexo9().getId() != null) {
            ent.setRefAnexos9Id(new RefAnexos9(obj.getRefAnexo9().getId()));
        }
        ent.setRuta(obj.getRuta());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        return ent;
    }

    public static RefAnexo9Adjunto castEntidadNegocio(RefAnexo9Adjuntos ent) {
        RefAnexo9Adjunto obj = new RefAnexo9Adjunto();
        obj.setArchivo(ent.getArchivo());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setId(ent.getId());
        obj.setMaeTipoArchivoCodigo(ent.getMaeTipoArchivoCodigo());
        obj.setMaeTipoArchivoId(ent.getMaeTipoArchivoId());
        obj.setMaeTipoArchivoValor(ent.getMaeTipoArchivoValor());
        obj.setNombreArchivo(ent.getNombreArchivo());
        obj.setNombre(ent.getNombreArchivo());
        obj.setBorrado(ent.getBorrado());
        obj.setUsuarioBorra(ent.getUsuarioBorra());
        obj.setTerminalBorra(ent.getTerminalBorra());
        obj.setFechaHoraBorra(ent.getFechaHoraBorra());
        obj.setBorradoObservacion(ent.getBorradoObservacion());
        obj.setExiste(ent.getExiste());
        if (ent.getRefAnexos9Id() != null) {
            obj.setRefAnexo9(new RefAnexo9(ent.getRefAnexos9Id().getId()));
        }
        obj.setRuta(ent.getRuta());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        return obj;
    }
}
