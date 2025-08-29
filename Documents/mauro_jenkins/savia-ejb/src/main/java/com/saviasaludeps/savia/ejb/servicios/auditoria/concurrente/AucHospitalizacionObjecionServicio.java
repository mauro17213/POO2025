/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionObjecion;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizacionObjeciones;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizaciones;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionObjecionRemoto;
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
@Remote(AucHospitalizacionObjecionRemoto.class)
public class AucHospitalizacionObjecionServicio extends GenericoServicio implements AucHospitalizacionObjecionRemoto {

    @Override
    public AucHospitalizacionObjecion consultar(int id) throws Exception {
        AucHospitalizacionObjecion objRes = null;
        try {
            objRes = castEntidadNegocio((AucHospitalizacionObjeciones) getEntityManager().find(AucHospitalizacionObjeciones.class, id));
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
    public int insertar(AucHospitalizacionObjecion obj) throws Exception {
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
    public void actualizar(AucHospitalizacionObjecion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizacionObjeciones a SET ";
            strQuery += "a.aucHospitalizacionesId.id = :aucHospitalizacionesId ,";
            strQuery += "a.tipoTecnologia = :tipoTecnologia ,";
            strQuery += "a.maTecnologiaId = :maTecnologiaId ,";
            strQuery += "a.maTecnologiaCodigo = :maTecnologiaCodigo ,";
            strQuery += "a.maTecnologiaValor = :maTecnologiaValor ,";
            strQuery += "a.cantidadSolicitada = :cantidadSolicitada ,";
            strQuery += "a.observacion = :observacion ,";
            strQuery += "a.notaCm = :notaCm ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("aucHospitalizacionesId", obj.getAucHospitalizacionId().getId());
            query.setParameter("tipoTecnologia", obj.getTipoTecnologia());
            query.setParameter("maTecnologiaId", obj.getMaTecnologiaId());
            query.setParameter("maTecnologiaCodigo", obj.getMaTecnologiaCodigo());
            query.setParameter("maTecnologiaValor", obj.getMaTecnologiaValor());
            query.setParameter("cantidadSolicitada", obj.getCantidadSolicitada());
            query.setParameter("observacion", obj.getObservacion());
            query.setParameter("notaCm", obj.getNotaCm());
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
    public void borradoLogico(AucHospitalizacionObjecion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizacionObjeciones a SET ";
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
    public AucHospitalizacionObjecion eliminar(int id) throws Exception {
        AucHospitalizacionObjecion obj = null;
        try {
            AucHospitalizacionObjeciones ent = getEntityManager().find(AucHospitalizacionObjeciones.class, id);
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
    
    private AucHospitalizacionObjecion castEntidadNegocio(AucHospitalizacionObjeciones entidad) {
        AucHospitalizacionObjecion negocio = new AucHospitalizacionObjecion();
        negocio.setId(entidad.getId());
        negocio.setAucHospitalizacionId(new AucHospitalizacion(entidad.getAucHospitalizacionesId().getId()));
        negocio.setTipoTecnologia(entidad.getTipoTecnologia());
        negocio.setMaTecnologiaId(entidad.getMaTecnologiaId());
        negocio.setMaTecnologiaCodigo(entidad.getMaTecnologiaCodigo());
        negocio.setMaTecnologiaValor(entidad.getMaTecnologiaValor());
        negocio.setCantidadSolicitada(entidad.getCantidadSolicitada());
        negocio.setObservacion(entidad.getObservacion());
        negocio.setNotaCm(entidad.getNotaCm());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }
    
    private AucHospitalizacionObjeciones castNegocioEntidad(AucHospitalizacionObjecion negocio) {
        AucHospitalizacionObjeciones entidad = new AucHospitalizacionObjeciones();
        entidad.setAucHospitalizacionesId(new AucHospitalizaciones(negocio.getAucHospitalizacionId().getId()));
        entidad.setTipoTecnologia(negocio.getTipoTecnologia());
        entidad.setMaTecnologiaId(negocio.getMaTecnologiaId());
        entidad.setMaTecnologiaCodigo(negocio.getMaTecnologiaCodigo());
        entidad.setMaTecnologiaValor(negocio.getMaTecnologiaValor());
        entidad.setCantidadSolicitada(negocio.getCantidadSolicitada());
        entidad.setObservacion(negocio.getObservacion());
        entidad.setNotaCm(negocio.getNotaCm());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

    @Override
    public List<AucHospitalizacionObjecion> consultarPorIdHospitalizacion(int idHospitalizacion) throws Exception {
        List<AucHospitalizacionObjecion> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AucHospitalizacionObjeciones p "
                    + "WHERE p.borrado = 0 AND p.aucHospitalizacionesId.id = " + idHospitalizacion + " ";

            List<AucHospitalizacionObjeciones> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AucHospitalizacionObjeciones objecion : list) {
                listaResultados.add(castEntidadNegocio(objecion));
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
