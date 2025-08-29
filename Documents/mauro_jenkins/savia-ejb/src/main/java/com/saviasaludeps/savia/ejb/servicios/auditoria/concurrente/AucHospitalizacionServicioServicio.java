/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionServicio;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoSede;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizaciones;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizacionServicios;
import com.saviasaludeps.savia.ejb.entidades.CntContratoSedes;
import com.saviasaludeps.savia.ejb.entidades.CntContratos;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionServicioRemoto;
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
@Remote(AucHospitalizacionServicioRemoto.class)
public class AucHospitalizacionServicioServicio extends GenericoServicio implements AucHospitalizacionServicioRemoto {

    @Override
    public AucHospitalizacionServicio consultar(int id) throws Exception {
        AucHospitalizacionServicio objRes = null;
        try {
            objRes = castEntidadNegocio((AucHospitalizacionServicios) getEntityManager().find(AucHospitalizacionServicios.class, id));
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
    public int insertar(AucHospitalizacionServicio obj) throws Exception {
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
    public void actualizar(AucHospitalizacionServicio obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizacionServicios a SET ";
            strQuery += "a.aucHospitalizacionesId.id = :aucHospitalizacionesId ,";
            strQuery += "a.tipoTecnologia = :tipoTecnologia ,";
            strQuery += ((obj.getCntContratoId()  != null && obj.getCntContratoId().getId() != null) ? "a.cntContratosId.id = :cntContratosId ," : "");
            strQuery += ((obj.getCntContratoSedeId() != null && obj.getCntContratoSedeId().getId() != null) ? "a.cntContratoSedesId.id = :cntContratoSedesId ," : "");
            strQuery += "a.maTecnologiaId = :maTecnologiaId ,";
            strQuery += "a.maTecnologiaCodigo = :maTecnologiaCodigo ,";
            strQuery += "a.maTecnologiaValor = :maTecnologiaValor ,";
            strQuery += "a.fechaPrestacion = :fechaPrestacion ,";
            strQuery += "a.observacion = :observacion ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("aucHospitalizacionesId", obj.getAucHospitalizacionId().getId());
            query.setParameter("tipoTecnologia", obj.getTipoTecnologia());
            if(obj.getCntContratoId() != null && obj.getCntContratoId().getId() != null){
                query.setParameter("cntContratosId", obj.getCntContratoId().getId());
            }
            if(obj.getCntContratoSedeId() != null && obj.getCntContratoSedeId().getId() != null){
                query.setParameter("cntContratoSedesId", obj.getCntContratoSedeId().getId());
            }     
            query.setParameter("maTecnologiaId", obj.getMaTecnologiaId());
            query.setParameter("maTecnologiaCodigo", obj.getMaTecnologiaCodigo());
            query.setParameter("maTecnologiaValor", obj.getMaTecnologiaValor());
            query.setParameter("fechaPrestacion", obj.getFechaPrestacion());
            query.setParameter("observacion", obj.getObservacion());
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
    public void borradoLogico(AucHospitalizacionServicio obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizacionServicios a SET ";
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
    public AucHospitalizacionServicio eliminar(int id) throws Exception {
        AucHospitalizacionServicio obj = null;
        try {
            AucHospitalizacionServicios ent = getEntityManager().find(AucHospitalizacionServicios.class, id);
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
    
    private AucHospitalizacionServicio castEntidadNegocio(AucHospitalizacionServicios entidad) {
        AucHospitalizacionServicio negocio = new AucHospitalizacionServicio();
        negocio.setId(entidad.getId());
        if(entidad.getAucHospitalizacionesId() != null){
            negocio.setAucHospitalizacionId(new AucHospitalizacion(entidad.getAucHospitalizacionesId().getId()));
        }
        negocio.setTipoTecnologia(entidad.getTipoTecnologia());
        if(entidad.getCntContratosId()!= null){
            negocio.setCntContratoId(new CntContrato(entidad.getCntContratosId().getId()));
        }
        if(entidad.getCntContratoSedesId() != null){
            negocio.setCntContratoSedeId(new CntContratoSede(entidad.getCntContratoSedesId().getId())); //Revisar
        }
        negocio.setMaTecnologiaId(entidad.getMaTecnologiaId());
        negocio.setMaTecnologiaCodigo(entidad.getMaTecnologiaCodigo());
        negocio.setMaTecnologiaValor(entidad.getMaTecnologiaValor());
        negocio.setFechaPrestacion(entidad.getFechaPrestacion());
        negocio.setObservacion(entidad.getObservacion());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }
    
    private AucHospitalizacionServicios castNegocioEntidad(AucHospitalizacionServicio negocio) {
        AucHospitalizacionServicios entidad = new AucHospitalizacionServicios();
        entidad.setAucHospitalizacionesId(new AucHospitalizaciones(negocio.getAucHospitalizacionId().getId()));
        entidad.setTipoTecnologia(negocio.getTipoTecnologia());
        if(negocio.getCntContratoId() != null){
            entidad.setCntContratosId(new CntContratos(negocio.getCntContratoId().getId()));
        }
        if(negocio.getCntContratoSedeId() != null){
            entidad.setCntContratoSedesId(new CntContratoSedes(negocio.getCntContratoSedeId().getId())); //Revisar
        }
        entidad.setMaTecnologiaId(negocio.getMaTecnologiaId());
        entidad.setMaTecnologiaCodigo(negocio.getMaTecnologiaCodigo());
        entidad.setMaTecnologiaValor(negocio.getMaTecnologiaValor());
        entidad.setFechaPrestacion(negocio.getFechaPrestacion());
        entidad.setObservacion(negocio.getObservacion());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

    @Override
    public List<AucHospitalizacionServicio> consultarPorIdHospitalizacion(int idHospitalizacion) throws Exception {
        List<AucHospitalizacionServicio> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AucHospitalizacionServicios p "
                    + "WHERE p.borrado = 0 AND p.aucHospitalizacionesId.id = " + idHospitalizacion + " ";

            List<AucHospitalizacionServicios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AucHospitalizacionServicios servicio : list) {
                listaResultados.add(castEntidadNegocio(servicio));
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
