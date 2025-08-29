/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionEstado;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizacionEstados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizaciones;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionEstadoRemoto;
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
@Remote(AucHospitalizacionEstadoRemoto.class)
public class AucHospitalizacionEstadoServicio extends GenericoServicio implements AucHospitalizacionEstadoRemoto {

    @Override
    public AucHospitalizacionEstado consultar(int id) throws Exception {
        AucHospitalizacionEstado objRes = null;
        try {
            objRes = castEntidadNegocio((AucHospitalizacionEstados) getEntityManager().find(AucHospitalizacionEstados.class, id));
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
    public int insertar(AucHospitalizacionEstado obj) throws Exception {
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
    public void actualizar(AucHospitalizacionEstado obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizacionEstados a SET ";
            strQuery += "a.aucHospitalizacionesId.id = :aucHospitalizacionesId ,";
            strQuery += "a.estado = :estado ,";
            strQuery += "a.estadoAuditoria = :estadoAuditoria ,";
            strQuery += "a.fuenteOrigen = :fuenteOrigen ,";
            strQuery += "a.Observacion = :Observacion ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("aucHospitalizacionesId", obj.getAucHospitalizacionId().getId());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("estadoAuditoria", obj.getEstadoAuditoria());
            query.setParameter("fuenteOrigen", obj.getFuenteOrigen());
            query.setParameter("Observacion", obj.getObservacion());
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
    public AucHospitalizacionEstado consultarHospitalizacionYestados(Integer idHospitalizacion) throws Exception {
        AucHospitalizacionEstado afiliadoResult = new AucHospitalizacionEstado();
        try {
            String strQuery = "SELECT p "
                            + "FROM AucHospitalizacionEstados p "
                            + "WHERE p.id > 0 "
                            + "AND p.aucHospitalizacionesId.id = " + idHospitalizacion +" "
                            + "ORDER BY p.id DESC ";
            List<AucHospitalizacionEstados> obj = getEntityManager().createQuery(strQuery).getResultList();
            if(!obj.isEmpty()){
               afiliadoResult = castEntidadNegocio(obj.get(0)) ;
            }
        } catch (NoResultException e) {
            afiliadoResult = new AucHospitalizacionEstado();
        } catch (Exception e) {
            afiliadoResult = new AucHospitalizacionEstado();
        } finally {
            cerrarEntityManager();
        }
        return afiliadoResult;
    }
    
    @Override
    public AucHospitalizacionEstado consultarHospitalizacionYUltimoEstadoEgreso(Integer idHospitalizacion) throws Exception {
        AucHospitalizacionEstado afiliadoResult = new AucHospitalizacionEstado();
        try {
            String strQuery = "SELECT p "
                            + "FROM AucHospitalizacionEstados p "
                            + "WHERE p.id > 0 "
                            + "AND p.estado = 2 "
                            + "AND p.estadoAuditoria = 3 "
                            + "AND p.aucHospitalizacionesId.id = " + idHospitalizacion +" "
                            + "ORDER BY p.id DESC ";
            List<AucHospitalizacionEstados> obj = getEntityManager().createQuery(strQuery).getResultList();
            if(!obj.isEmpty()){
               afiliadoResult = castEntidadNegocio(obj.get(0)) ;
            }else{
                afiliadoResult = null;
            }
        } catch (NoResultException e) {
            afiliadoResult = new AucHospitalizacionEstado();
        } catch (Exception e) {
            afiliadoResult = new AucHospitalizacionEstado();
        } finally {
            cerrarEntityManager();
        }
        return afiliadoResult;
    }
    
    @Override
    public AucHospitalizacionEstado consultarHospitalizacionYUltimoEstadoDevuelto(Integer idHospitalizacion) throws Exception {
        AucHospitalizacionEstado afiliadoResult = new AucHospitalizacionEstado();
        try {
            String strQuery = "SELECT p "
                            + "FROM AucHospitalizacionEstados p "
                            + "WHERE p.id > 0 "
                            + "AND p.estado = 4 "
                            + "AND p.estadoAuditoria = 6 "
                            + "AND p.aucHospitalizacionesId.id = " + idHospitalizacion +" "
                            + "ORDER BY p.id DESC ";
            List<AucHospitalizacionEstados> obj = getEntityManager().createQuery(strQuery).getResultList();
            if(!obj.isEmpty()){
               afiliadoResult = castEntidadNegocio(obj.get(0)) ;
            }else{
                afiliadoResult = null;
            }
        } catch (NoResultException e) {
            afiliadoResult = new AucHospitalizacionEstado();
        } catch (Exception e) {
            afiliadoResult = new AucHospitalizacionEstado();
        } finally {
            cerrarEntityManager();
        }
        return afiliadoResult;
    }
    
    
    @Override
    public AucHospitalizacionEstado eliminar(int id) throws Exception {
        AucHospitalizacionEstado obj = null;
        try {
            AucHospitalizacionEstados ent = getEntityManager().find(AucHospitalizacionEstados.class, id);
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
    
    private AucHospitalizacionEstado castEntidadNegocio(AucHospitalizacionEstados entidad) {
        AucHospitalizacionEstado negocio = new AucHospitalizacionEstado();
        negocio.setId(entidad.getId());
        if(entidad.getAucHospitalizacionesId() != null){
            negocio.setAucHospitalizacionId(new AucHospitalizacion(entidad.getAucHospitalizacionesId().getId()));
        }
        negocio.setEstado(entidad.getEstado());
        negocio.setEstadoAuditoria(entidad.getEstadoAuditoria());
        negocio.setFuenteOrigen(entidad.getFuenteOrigen());
        negocio.setObservacion(entidad.getObservacion());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }
    
    private AucHospitalizacionEstados castNegocioEntidad(AucHospitalizacionEstado negocio) {
        AucHospitalizacionEstados entidad = new AucHospitalizacionEstados();
        if(negocio.getAucHospitalizacionId() != null){
            entidad.setAucHospitalizacionesId(new AucHospitalizaciones(negocio.getAucHospitalizacionId().getId()));
        }
        entidad.setEstado(negocio.getEstado());
        entidad.setEstadoAuditoria(negocio.getEstadoAuditoria());
        entidad.setFuenteOrigen(negocio.getFuenteOrigen());
        entidad.setObservacion(negocio.getObservacion());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }
}
