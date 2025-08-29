/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitud;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudHistorico;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudHistoricos;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudHistoricoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(AuNoSolicitudHistoricoRemoto.class)
public class AuNoSolicitudHistoricoServicio extends GenericoServicio implements AuNoSolicitudHistoricoRemoto {

    private String agregarJoin(String join, String sql) {
        if (sql.contains(join)) {
            return sql;
        } else {
            return sql += join;
        }
    }
    
    @Override
    public AuNoSolicitudHistorico consultar(int id) throws Exception {
        AuNoSolicitudHistorico objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AuNoSolicitudHistoricos) getEntityManager().find(AuNoSolicitudHistoricos.class, id));
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
    public int insertar(AuNoSolicitudHistorico obj) throws Exception {
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
    public void actualizar(AuNoSolicitudHistorico obj) throws Exception {
        try {
            /*String hql = "UPDATE AuNoSolicitudHistoricos SET "
                    + "estado = :estado, "
                    + "tipo = :tipo, "
                    + "pbs = :pbs, "
                    + "maeClasificacionId.id = :maeClasificacionId, "
                    + "maeClasificacionCodigo = :maeClasificacionCodigo, "
                    + "maeClasificacionValor = :maeClasificacionValor, "
                    + "maeClasificacionTipo = :maeClasificacionTipo, "
                    + "clasificacionObservacion = :clasificacionObservacion, "
                    + "justificacion = :justificacion, "
                    + "asegAfiliadosId.id = :asegAfiliadosId, "
                    + "cntPrestadorSedesId.id = :cntPrestadorSedesId, " 
                    + "cntPrestadoresId.id = :cntPrestadoresId, " 
                    + "gnUsuariosId.id = :gnUsuariosId, " 
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("tipo", obj.getTipo());
            query.setParameter("pbs", obj.isPbs());
            query.setParameter("maeClasificacionId", obj.getMaeClasificacionId().getId());
            query.setParameter("maeClasificacionCodigo", obj.getMaeClasificacionCodigo());
            query.setParameter("maeClasificacionValor", obj.getMaeClasificacionValor());
            query.setParameter("maeClasificacionTipo", obj.getMaeClasificacionTipo());
            query.setParameter("clasificacionObservacion", obj.getClasificacionObservacion());
            query.setParameter("justificacion", obj.getJustificacion());
            if(obj.getAsegAfiliadosId() != null){
                query.setParameter("asegAfiliadosId", obj.getAsegAfiliadosId().getId());
            }else {
                query.setParameter("asegAfiliadosId", obj.getAsegAfiliadosId());
            }
            if(obj.getCntPrestadorSedesId() != null){
                query.setParameter("cntPrestadorSedesId", obj.getCntPrestadorSedesId().getId());
            }else{
                query.setParameter("cntPrestadorSedesId", obj.getCntPrestadorSedesId());
            }
            if(obj.getCntPrestadoresId() != null){
                query.setParameter("cntPrestadoresId", obj.getCntPrestadoresId().getId());
            }else{
                query.setParameter("cntPrestadoresId", obj.getCntPrestadoresId());
            }
            query.setParameter("gnUsuariosId", obj.getGnUsuariosId().getId());
            //auditoria
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();*/
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public List<AuNoSolicitudHistorico> consultarHistoricoPorAuNosolicitudesId(int idAuNoSolicitudes) throws Exception {
        List<AuNoSolicitudHistorico> obj = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuNoSolicitudHistoricos u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            strTitulo = agregarJoin("INNER JOIN AuNoSolicitudes aun ON u.auNoSolicitudesId = aun.id ", strTitulo);
            strQuery.append("AND aun.id = ").append(idAuNoSolicitudes).append(" ");
            sql.append(strTitulo).append(strQuery);
            sql.append("ORDER BY u.id DESC");
           
            List<AuNoSolicitudHistoricos> list = getEntityManager().createQuery(sql.toString()).getResultList();
            int posicion = 0;
            for (AuNoSolicitudHistoricos diagnostico : list) {
                obj.add(castEntidadNegocioLargo(diagnostico, posicion));
                posicion++;
            }
        } catch (NoResultException e) {
            obj = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }
    
    public static AuNoSolicitudHistorico castEntidadNegocioCorto(AuNoSolicitudHistoricos ent) {
        AuNoSolicitudHistorico obj = new AuNoSolicitudHistorico();
        obj.setId(ent.getId());
        if(ent.getAuNoSolicitudesId() != null){
            AuNoSolicitud prestador = new AuNoSolicitud();
            prestador.setId(ent.getAuNoSolicitudesId().getId());
            obj.setAuNoSolicitudesId(prestador);
        }
        obj.setEstado(ent.getEstado());
        obj.setTipo(ent.getTipo());
         
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }

    public static AuNoSolicitudHistorico castEntidadNegocioLargo(AuNoSolicitudHistoricos ent) {
        AuNoSolicitudHistorico obj = new AuNoSolicitudHistorico();
        obj.setId(ent.getId());
        if(ent.getAuNoSolicitudesId() != null){
            AuNoSolicitud prestador = new AuNoSolicitud();
            prestador.setId(ent.getAuNoSolicitudesId().getId());
            obj.setAuNoSolicitudesId(prestador);
        }
        obj.setEstado(ent.getEstado());
        obj.setTipo(ent.getTipo());
        obj.setObservacion(ent.getObservacion());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        
        return obj;
    }
    
    public static AuNoSolicitudHistorico castEntidadNegocioLargo(AuNoSolicitudHistoricos ent, int posicion) {
        AuNoSolicitudHistorico obj = new AuNoSolicitudHistorico();
        obj.setId(ent.getId());
        obj.setPosicion(posicion);
        if(ent.getAuNoSolicitudesId() != null){
            AuNoSolicitud prestador = new AuNoSolicitud();
            prestador.setId(ent.getAuNoSolicitudesId().getId());
            obj.setAuNoSolicitudesId(prestador);
        }
        obj.setEstado(ent.getEstado());
        obj.setTipo(ent.getTipo());
        obj.setObservacion(ent.getObservacion());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        
        return obj;
    }
    
    public static AuNoSolicitudHistoricos castNegocioEntidad(AuNoSolicitudHistorico obj) {
        AuNoSolicitudHistoricos ent = new AuNoSolicitudHistoricos();
        ent.setId(obj.getId());
        if(obj.getAuNoSolicitudesId() != null){
            AuNoSolicitudes auNoSolicitudes = new AuNoSolicitudes();
            auNoSolicitudes.setId(obj.getAuNoSolicitudesId().getId());
            ent.setAuNoSolicitudesId(auNoSolicitudes);
        }
        ent.setEstado(obj.getEstado());
        ent.setTipo(obj.getTipo());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }
}
