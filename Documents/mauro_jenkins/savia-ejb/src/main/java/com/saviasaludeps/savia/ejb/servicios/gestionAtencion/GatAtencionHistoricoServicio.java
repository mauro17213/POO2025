/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.gestionAtencion;

import com.saviasaludeps.savia.dominio.gestionAtencion.GatAtencion;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatAtencionHistorico;
import com.saviasaludeps.savia.ejb.entidades.GatAtencionHistoricos;
import com.saviasaludeps.savia.ejb.entidades.GatAtenciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatAtencionHistoricoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author acuartas
 */
@Stateless
@Remote(GatAtencionHistoricoRemoto.class)
public class GatAtencionHistoricoServicio extends GenericoServicio implements GatAtencionHistoricoRemoto {

    @Override
    public List<GatAtencionHistorico> consultarListaPorAtencion(int idAtencion) throws Exception {
        List<GatAtencionHistorico> listaResultado = new ArrayList<>();
        try {
            String strQuery = "FROM GatAtencionHistoricos c "
                    + " WHERE c.gatAtencionesId.id = "+idAtencion
                    + " ORDER BY c.id DESC";
            List<GatAtencionHistoricos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GatAtencionHistoricos historico : list) {
                listaResultado.add(castEntidadNegocio(historico));
            }
        } catch (NoResultException e) {
            listaResultado = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultado;
    }

    @Override
    public GatAtencionHistorico consultar(int id) throws java.lang.Exception {
        GatAtencionHistorico objRes = null;
        try {
            objRes = castEntidadNegocio((GatAtencionHistoricos) getEntityManager().find(GatAtencionHistoricos.class, id));
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
    public int insertar(GatAtencionHistorico obj) throws java.lang.Exception {
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
    public void actualizar(GatAtencionHistorico obj) throws java.lang.Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GatAtencionHistoricos a SET ";
//            strQuery += "a.gatAtencionesId.id = :gatAtencionesId ,";
//            strQuery += "a.maeTipoServicioId = :maeTipoServicioId ,";
//            strQuery += "a.maeTipoServicioCodigo = :maeTipoServicioCodigo ,";
//            strQuery += "a.maeTipoServicioValor = :maeTipoServicioValor ,";
//            strQuery += "a.fechaHoraInicio = :fechaHoraInicio ,";
            strQuery += "a.comentario = :comentario ,";
            strQuery += "a.fechaHoraFin = :fechaHoraFin ,";
            strQuery += "a.tiempo = :tiempo ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
//            query.setParameter("gatAtencionesId", obj.getGatAtencionId().getId());
//            query.setParameter("maeTipoServicioId", obj.getMaeTipoServicioId());
//            query.setParameter("maeTipoServicioCodigo", obj.getMaeTipoServicioCodigo());
//            query.setParameter("maeTipoServicioValor", obj.getMaeTipoServicioValor());
//            query.setParameter("fechaHoraInicio", obj.getFechaHoraInicio());
            query.setParameter("comentario", obj.getComentario());
            query.setParameter("fechaHoraFin", obj.getFechaHoraFin());
            query.setParameter("tiempo", obj.getTiempo());
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
    public GatAtencionHistorico eliminar(int id) throws java.lang.Exception {
        GatAtencionHistorico obj = null;
        try {
            GatAtencionHistoricos ent = getEntityManager().find(GatAtencionHistoricos.class, id);
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

    private GatAtencionHistorico castEntidadNegocio(GatAtencionHistoricos entidad) {
        GatAtencionHistorico negocio = new GatAtencionHistorico();
        negocio.setId(entidad.getId());
        negocio.setGatAtencionId(new GatAtencion(entidad.getGatAtencionesId().getId()));
        negocio.setComentario(entidad.getComentario());
        negocio.setMaeTipoServicioId(entidad.getMaeTipoServicioId());
        negocio.setMaeTipoServicioCodigo(entidad.getMaeTipoServicioCodigo());
        negocio.setMaeTipoServicioValor(entidad.getMaeTipoServicioValor());
        negocio.setFechaHoraInicio(entidad.getFechaHoraInicio());
        negocio.setFechaHoraFin(entidad.getFechaHoraFin());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }
    
    private GatAtencionHistoricos castNegocioEntidad(GatAtencionHistorico negocio) {
        GatAtencionHistoricos entidad = new GatAtencionHistoricos();
        entidad.setId(negocio.getId());
        entidad.setGatAtencionesId(new GatAtenciones(negocio.getGatAtencionId().getId()));
        entidad.setComentario(negocio.getComentario());
        entidad.setMaeTipoServicioId(negocio.getMaeTipoServicioId());
        entidad.setMaeTipoServicioCodigo(negocio.getMaeTipoServicioCodigo());
        entidad.setMaeTipoServicioValor(negocio.getMaeTipoServicioValor());
        entidad.setFechaHoraInicio(negocio.getFechaHoraInicio());
        entidad.setFechaHoraFin(negocio.getFechaHoraFin());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }
    
}
