/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionPatologia;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizacionPatologias;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizaciones;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionPatologiaRemoto;
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
@Remote(AucHospitalizacionPatologiaRemoto.class)
public class AucHospitalizacionPatologiaServicio extends GenericoServicio implements AucHospitalizacionPatologiaRemoto {

    @Override
    public AucHospitalizacionPatologia consultar(int id) throws Exception {
        AucHospitalizacionPatologia objRes = null;
        try {
            objRes = castEntidadNegocio((AucHospitalizacionPatologias) getEntityManager().find(AucHospitalizacionPatologias.class, id));
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
    public int insertar(AucHospitalizacionPatologia obj) throws Exception {
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
    public void actualizar(AucHospitalizacionPatologia obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizacionPatologias a SET ";
            strQuery += "a.aucHospitalizacionesId.id = :aucHospitalizacionesId ,";
            strQuery += "a.maePatologiaId = :maePatologiaId ,";
            strQuery += "a.maePatologiaCodigo = :maePatologiaCodigo ,";
            strQuery += "a.maePatologiaValor = :maePatologiaValor ,";
            strQuery += "a.estado = :estado ,";
            strQuery += "a.descripcion = :descripcion ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("aucHospitalizacionesId", obj.getAucHospitalizacionId().getId());
            query.setParameter("maePatologiaId", obj.getMaePatologiaId());
            query.setParameter("maePatologiaCodigo", obj.getMaePatologiaCodigo());
            query.setParameter("maePatologiaValor", obj.getMaePatologiaValor());
            query.setParameter("descripcion", obj.getDescripcion());
            query.setParameter("estado", obj.getEstado());
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
    public AucHospitalizacionPatologia eliminar(int id) throws Exception {
        AucHospitalizacionPatologia obj = null;
        try {
            AucHospitalizacionPatologias ent = getEntityManager().find(AucHospitalizacionPatologias.class, id);
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
    
    private AucHospitalizacionPatologia castEntidadNegocio(AucHospitalizacionPatologias entidad) {
        AucHospitalizacionPatologia negocio = new AucHospitalizacionPatologia();
        negocio.setId(entidad.getId());
        negocio.setAucHospitalizacionId(new AucHospitalizacion(entidad.getAucHospitalizacionesId().getId()));
        negocio.setMaePatologiaId(entidad.getMaePatologiaId());
        negocio.setMaePatologiaCodigo(entidad.getMaePatologiaCodigo());
        negocio.setMaePatologiaValor(entidad.getMaePatologiaValor());
        negocio.setEstado(entidad.getEstado());
        negocio.setDescripcion(entidad.getDescripcion());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }
    
    private AucHospitalizacionPatologias castNegocioEntidad(AucHospitalizacionPatologia negocio) {
        AucHospitalizacionPatologias entidad = new AucHospitalizacionPatologias();
        entidad.setAucHospitalizacionesId(new AucHospitalizaciones(negocio.getAucHospitalizacionId().getId()));
        entidad.setMaePatologiaId(negocio.getMaePatologiaId());
        entidad.setMaePatologiaCodigo(negocio.getMaePatologiaCodigo());
        entidad.setMaePatologiaValor(negocio.getMaePatologiaValor());
        entidad.setEstado(negocio.getEstado());
        entidad.setDescripcion(negocio.getDescripcion());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

    @Override
    public List<AucHospitalizacionPatologia> consultarPorIdHospitalizacion(int idHospitalizacion) throws Exception {
        List<AucHospitalizacionPatologia> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AucHospitalizacionPatologias p "
                    + "WHERE p.aucHospitalizacionesId.id = " + idHospitalizacion + " ";

            List<AucHospitalizacionPatologias> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AucHospitalizacionPatologias patologia : list) {
                listaResultados.add(castEntidadNegocio(patologia));
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
