/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionInoportunidad;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizacionInoportunidades;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizaciones;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionInoportunidadRemoto;
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
@Remote(AucHospitalizacionInoportunidadRemoto.class)
public class AucHospitalizacionInoportunidadServicio extends GenericoServicio implements AucHospitalizacionInoportunidadRemoto {

    @Override
    public AucHospitalizacionInoportunidad consultar(int id) throws Exception {
        AucHospitalizacionInoportunidad objRes = null;
        try {
            objRes = castEntidadNegocio((AucHospitalizacionInoportunidades) getEntityManager().find(AucHospitalizacionInoportunidades.class, id));
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
    public int insertar(AucHospitalizacionInoportunidad obj) throws Exception {
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
    public void actualizar(AucHospitalizacionInoportunidad obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizacionInoportunidades a SET ";
            strQuery += "a.aucHospitalizacionesId.id = :aucHospitalizacionesId ,";
            strQuery += "a.maeTipoInoportunidadId = :maeTipoInoportunidadId ,";
            strQuery += "a.maeTipoInoportunidadCodigo = :maeTipoInoportunidadCodigo ,";
            strQuery += "a.maeTipoInoportunidadValor = :maeTipoInoportunidadValor ,";
            strQuery += "a.maeMotivoFinInoportunidadId = :maeMotivoFinInoportunidadId ,";
            strQuery += "a.maeMotivoFinInoportunidadCodigo = :maeMotivoFinInoportunidadCodigo ,";
            strQuery += "a.maeMotivoFinInoportunidadValor = :maeMotivoFinInoportunidadValor ,";
            strQuery += "a.fechaInicioInoportunidad = :fechaInicioInoportunidad ,";
            strQuery += "a.fechaFinInoportunidad = :fechaFinInoportunidad ,";
            strQuery += "a.diasInoportunidad = :diasInoportunidad ,";
            strQuery += "a.observacion = :observacion ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("aucHospitalizacionesId", obj.getAucHospitalizacionId().getId());
            query.setParameter("maeTipoInoportunidadId", obj.getMaeTipoInoportunidadId());
            query.setParameter("maeTipoInoportunidadCodigo", obj.getMaeTipoInoportunidadCodigo());
            query.setParameter("maeTipoInoportunidadValor", obj.getMaeTipoInoportunidadValor());
            query.setParameter("maeMotivoFinInoportunidadId", obj.getMaeMotivoInoportunidadId());
            query.setParameter("maeMotivoFinInoportunidadCodigo", obj.getMaeMotivoInoportunidadCodigo());
            query.setParameter("maeMotivoFinInoportunidadValor", obj.getMaeMotivoInoportunidadValor());
            query.setParameter("fechaInicioInoportunidad", obj.getFechaInicioInoportunidad());
            query.setParameter("fechaFinInoportunidad", obj.getFechaFinInoportunidad());
            query.setParameter("diasInoportunidad", obj.getDiasInoportunidad());
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
    public void borradoLogico(AucHospitalizacionInoportunidad obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizacionInoportunidades a SET ";
            strQuery += "a.borrado = :borrado ,";
            strQuery += "a.observacionBorrado = :borradoObservacion ,";
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
    public AucHospitalizacionInoportunidad eliminar(int id) throws Exception {
        AucHospitalizacionInoportunidad obj = null;
        try {
            AucHospitalizacionInoportunidades ent = getEntityManager().find(AucHospitalizacionInoportunidades.class, id);
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
    
    private AucHospitalizacionInoportunidad castEntidadNegocio(AucHospitalizacionInoportunidades entidad) {
        AucHospitalizacionInoportunidad negocio = new AucHospitalizacionInoportunidad();
        negocio.setId(entidad.getId());
        negocio.setAucHospitalizacionId(new AucHospitalizacion(entidad.getAucHospitalizacionesId().getId()));
        negocio.setMaeTipoInoportunidadId(entidad.getMaeTipoInoportunidadId());
        negocio.setMaeTipoInoportunidadCodigo(entidad.getMaeTipoInoportunidadCodigo());
        negocio.setMaeTipoInoportunidadValor(entidad.getMaeTipoInoportunidadValor());
        negocio.setFechaInicioInoportunidad(entidad.getFechaInicioInoportunidad());
        negocio.setFechaFinInoportunidad(entidad.getFechaFinInoportunidad());
        negocio.setMaeMotivoInoportunidadId(entidad.getMaeMotivoFinInoportunidadId());
        negocio.setMaeMotivoInoportunidadCodigo(entidad.getMaeMotivoFinInoportunidadCodigo());
        negocio.setMaeMotivoInoportunidadValor(entidad.getMaeMotivoFinInoportunidadValor());
        negocio.setDiasInoportunidad(entidad.getDiasInoportunidad());
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
    
    private AucHospitalizacionInoportunidades castNegocioEntidad(AucHospitalizacionInoportunidad negocio) {
        AucHospitalizacionInoportunidades entidad = new AucHospitalizacionInoportunidades();
        entidad.setAucHospitalizacionesId(new AucHospitalizaciones(negocio.getAucHospitalizacionId().getId()));
        entidad.setMaeTipoInoportunidadId(negocio.getMaeTipoInoportunidadId());
        entidad.setMaeTipoInoportunidadCodigo(negocio.getMaeTipoInoportunidadCodigo());
        entidad.setMaeTipoInoportunidadValor(negocio.getMaeTipoInoportunidadValor());
        entidad.setFechaInicioInoportunidad(negocio.getFechaInicioInoportunidad());
        entidad.setFechaFinInoportunidad(negocio.getFechaFinInoportunidad());
        entidad.setMaeMotivoFinInoportunidadId(negocio.getMaeMotivoInoportunidadId());
        entidad.setMaeMotivoFinInoportunidadCodigo(negocio.getMaeMotivoInoportunidadCodigo());
        entidad.setMaeMotivoFinInoportunidadValor(negocio.getMaeMotivoInoportunidadValor());
        entidad.setDiasInoportunidad(negocio.getDiasInoportunidad());
        entidad.setObservacion(negocio.getObservacion());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

    @Override
    public List<AucHospitalizacionInoportunidad> consultarPorIdHospitalizacion(int idHospitalizacion) throws Exception {
        List<AucHospitalizacionInoportunidad> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AucHospitalizacionInoportunidades p "
                    + "WHERE p.borrado = 0 AND p.aucHospitalizacionesId.id = " + idHospitalizacion + " "
                    + "ORDER BY p.fechaHoraCrea DESC";

            List<AucHospitalizacionInoportunidades> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AucHospitalizacionInoportunidades inoportunidad : list) {
                listaResultados.add(castEntidadNegocio(inoportunidad));
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
