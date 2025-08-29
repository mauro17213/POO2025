/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionAdverso;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizacionAdversos;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizaciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionAdversoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author sgiraldov
 */
@Stateless
@Remote(AucHospitalizacionAdversoRemoto.class)
public class AucHospitalizacionAdversoServicio extends GenericoServicio implements AucHospitalizacionAdversoRemoto {

    @Override
    public AucHospitalizacionAdverso consultar(int id) throws Exception {
        AucHospitalizacionAdverso objRes = null;
        try {
            objRes = castEntidadNegocio((AucHospitalizacionAdversos) getEntityManager().find(AucHospitalizacionAdversos.class, id));
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
    public int insertar(AucHospitalizacionAdverso obj) throws Exception {
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
    public void actualizar(AucHospitalizacionAdverso obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizacionAdversos a SET ";
            strQuery += "a.aucHospitalizacionesId.id = :aucHospitalizacionesId ,";
            strQuery += "a.fechaEvento = :fechaEvento ,";
            strQuery += "a.descripcionEvento = :descripcionEvento ,";
            strQuery += "a.maeCategoriaEventoId = :maeCategoriaEventoId ,";
            strQuery += "a.maeCategoriaEventoCodigo = :maeCategoriaEventoCodigo ,";
            strQuery += "a.maeCategoriaEventoValor = :maeCategoriaEventoValor ,";
            strQuery += "a.maeSubcategoriaEventoId = :maeSubcategoriaEventoId ,";
            strQuery += "a.maeSubcategoriaEventoCodigo = :maeSubcategoriaEventoCodigo ,";
            strQuery += "a.maeSubcategoriaEventoValor = :maeSubcategoriaEventoValor ,";
            strQuery += "a.fechaAnalisis = :fechaAnalisis ,";
            strQuery += "a.descripcionPlanMejora = :descripcionPlanMejora ,";
            strQuery += "a.fechaSolicitudAnalisis = :fechaSolicitudAnalisis ,";
            strQuery += "a.descripcionAnalisis = :descripcionAnalisis ,";
            strQuery += "a.maeConclusionEventoId = :maeConclusionEventoId ,";
            strQuery += "a.maeConclusionEventoCodigo = :maeConclusionEventoCodigo ,";
            strQuery += "a.maeConclusionEventoValor = :maeConclusionEventoValor ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("aucHospitalizacionesId", obj.getAucHospitalizacionId().getId());
            query.setParameter("fechaEvento", obj.getFechaEvento());
            query.setParameter("descripcionEvento", obj.getDescripcionEvento());
            query.setParameter("maeCategoriaEventoId", obj.getMaeCategoriaEventoId());
            query.setParameter("maeCategoriaEventoCodigo", obj.getMaeCategoriaEventoCodigo());
            query.setParameter("maeCategoriaEventoValor", obj.getMaeCategoriaEventoValor());
            query.setParameter("maeSubcategoriaEventoId", obj.getMaeSubcategoriaEventoId());
            query.setParameter("maeSubcategoriaEventoCodigo", obj.getMaeSubcategoriaEventoCodigo());
            query.setParameter("maeSubcategoriaEventoValor", obj.getMaeSubcategoriaEventoValor());
            query.setParameter("fechaAnalisis", obj.getFechaAnalisis());
            query.setParameter("descripcionPlanMejora", obj.getDescripcionPlanMejora());
            query.setParameter("fechaSolicitudAnalisis", obj.getFechaSolicitudAnalisis());
            query.setParameter("descripcionAnalisis", obj.getDescripcionAnalisis());
            query.setParameter("maeConclusionEventoId", obj.getMaeConclusionEventoId());
            query.setParameter("maeConclusionEventoCodigo", obj.getMaeConclusionEventoCodigo());
            query.setParameter("maeConclusionEventoValor", obj.getMaeConclusionEventoValor());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
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
    public void borradoLogico(AucHospitalizacionAdverso obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizacionAdversos a SET ";
            strQuery += "a.borrado = :borrado ,";
            strQuery += "a.borradoObservacion = :borradoObservacion ,";
            strQuery += "a.usuarioBorra = :usuarioBorra ,";
            strQuery += "a.terminalBorra = :terminalBorra ,";
            strQuery += "a.fechaHoraBorra = :fechaHoraBorra ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("borrado", obj.isBorrado());
            query.setParameter("borradoObservacion", obj.getObservacionBorrado());
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
    
    @Override
    public AucHospitalizacionAdverso eliminar(int id) throws Exception {
        AucHospitalizacionAdverso obj = null;
        try {
            AucHospitalizacionAdversos ent = getEntityManager().find(AucHospitalizacionAdversos.class, id);
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
    public List<AucHospitalizacionAdverso> consultarPorIdHospitalizacion(int idHospitalizacion) throws Exception {
        List<AucHospitalizacionAdverso> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AucHospitalizacionAdversos p "
                    + "WHERE p.borrado = 0 AND p.aucHospitalizacionesId.id = " + idHospitalizacion + " ";

            List<AucHospitalizacionAdversos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AucHospitalizacionAdversos adverso : list) {
                listaResultados.add(castEntidadNegocio(adverso));
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
    
    private AucHospitalizacionAdverso castEntidadNegocio(AucHospitalizacionAdversos entidad) {
        AucHospitalizacionAdverso negocio = new AucHospitalizacionAdverso();
        negocio.setId(entidad.getId());
        if(entidad.getAucHospitalizacionesId() != null){
            negocio.setAucHospitalizacionId(new AucHospitalizacion(entidad.getAucHospitalizacionesId().getId()));
        }
        negocio.setFechaEvento(entidad.getFechaEvento());
        negocio.setDescripcionEvento(entidad.getDescripcionEvento());
        negocio.setMaeCategoriaEventoId(entidad.getMaeCategoriaEventoId());
        negocio.setMaeCategoriaEventoCodigo(entidad.getMaeCategoriaEventoCodigo());
        negocio.setMaeCategoriaEventoValor(entidad.getMaeCategoriaEventoValor());
        negocio.setMaeSubcategoriaEventoId(entidad.getMaeSubcategoriaEventoId());
        negocio.setMaeSubcategoriaEventoCodigo(entidad.getMaeSubcategoriaEventoCodigo());
        negocio.setMaeSubcategoriaEventoValor(entidad.getMaeSubcategoriaEventoValor());
        negocio.setFechaAnalisis(entidad.getFechaAnalisis());
        negocio.setDescripcionPlanMejora(entidad.getDescripcionPlanMejora());
        negocio.setFechaSolicitudAnalisis(entidad.getFechaSolicitudAnalisis());
        negocio.setDescripcionAnalisis(entidad.getDescripcionAnalisis());
        negocio.setMaeConclusionEventoId(entidad.getMaeConclusionEventoId());
        negocio.setMaeConclusionEventoCodigo(entidad.getMaeConclusionEventoCodigo());
        negocio.setMaeConclusionEventoValor(entidad.getMaeConclusionEventoValor());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }
    
    private AucHospitalizacionAdversos castNegocioEntidad(AucHospitalizacionAdverso negocio) {
        AucHospitalizacionAdversos entidad = new AucHospitalizacionAdversos();
        entidad.setAucHospitalizacionesId(new AucHospitalizaciones(negocio.getAucHospitalizacionId().getId()));
        entidad.setFechaEvento(negocio.getFechaEvento());
        entidad.setDescripcionEvento(negocio.getDescripcionEvento());
        entidad.setMaeCategoriaEventoId(negocio.getMaeCategoriaEventoId());
        entidad.setMaeCategoriaEventoCodigo(negocio.getMaeCategoriaEventoCodigo());
        entidad.setMaeCategoriaEventoValor(negocio.getMaeCategoriaEventoValor());
        entidad.setMaeSubcategoriaEventoId(negocio.getMaeSubcategoriaEventoId());
        entidad.setMaeSubcategoriaEventoCodigo(negocio.getMaeSubcategoriaEventoCodigo());
        entidad.setMaeSubcategoriaEventoValor(negocio.getMaeSubcategoriaEventoValor());
        entidad.setFechaAnalisis(negocio.getFechaAnalisis());
        entidad.setDescripcionPlanMejora(negocio.getDescripcionPlanMejora());
        entidad.setFechaSolicitudAnalisis(negocio.getFechaSolicitudAnalisis());
        entidad.setDescripcionAnalisis(negocio.getDescripcionAnalisis());
        entidad.setMaeConclusionEventoId(negocio.getMaeConclusionEventoId());
        entidad.setMaeConclusionEventoCodigo(negocio.getMaeConclusionEventoCodigo());
        entidad.setMaeConclusionEventoValor(negocio.getMaeConclusionEventoValor());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }
}
