/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitud;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudEntrega;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudEntregaDetalle;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudItem;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudEntregaDetalles;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudEntregas;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudItems;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudEntregaRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(AuNoSolicitudEntregaRemoto.class)
public class AuNoSolicitudEntregaServicio extends GenericoServicio implements AuNoSolicitudEntregaRemoto {

    @SuppressWarnings("UnusedAssignment")
    private String agregarJoin(String join, String sql) {
        if (sql.contains(join)) {
            return sql;
        } else {
            return sql += join;
        }
    }
    
    @Override
    public AuNoSolicitudEntrega consultar(int id) throws Exception {
        AuNoSolicitudEntrega objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AuNoSolicitudEntregas) getEntityManager().find(AuNoSolicitudEntregas.class, id));
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
    public int insertar(AuNoSolicitudEntrega obj) throws Exception {
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
    public void actualizarAnular(AuNoSolicitudEntrega obj) throws Exception {
        try {
            String hql = "UPDATE AuNoSolicitudEntregas SET "
                    + "anulada = :anulada, "
                    + "anuladaObservacion = :anuladaObservacion, "
                    + "tipoEntrega = :tipoEntrega, "
                    + "cantidadEntregada = :cantidadEntregada, "
                    + "cantidadPendiente = :cantidadPendiente, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("anulada", obj.isAnulada());
            query.setParameter("anuladaObservacion", obj.getAnuladaObservacion());
            query.setParameter("tipoEntrega", obj.getTipoEntrega());
            query.setParameter("cantidadEntregada", obj.getCantidadEntregada());
            query.setParameter("cantidadPendiente", obj.getCantidadPendiente());
            //auditoria
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public List<AuNoSolicitudEntrega> consultarEntregasPorAuNosolicitudesId(int idAuNoSolicitudes) throws Exception {
        List<AuNoSolicitudEntrega> obj = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuNoSolicitudEntregas u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            strTitulo = agregarJoin("INNER JOIN AuNoSolicitudes aun ON u.auNoSolicitudesId = aun.id ", strTitulo);
            strQuery.append("AND aun.id = ").append(idAuNoSolicitudes).append(" ");
            sql.append(strTitulo).append(strQuery);
            sql.append("ORDER BY u.id DESC");
           
            List<AuNoSolicitudEntregas> list = getEntityManager().createQuery(sql.toString()).getResultList();
            int posicion = 0;
            for (AuNoSolicitudEntregas diagnostico : list) {
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
    
    @Override
    public List<AuNoSolicitudEntrega> consultarEntregasPorAuNosolicitudesItemId(int idAuNoSolicitudItem) throws Exception {
        List<AuNoSolicitudEntrega> obj = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuNoSolicitudEntregas u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            strTitulo = agregarJoin("INNER JOIN AuNoSolicitudItems aun ON u.auNoSolicitudItemsId = aun.id ", strTitulo);
            strQuery.append("AND aun.id = ").append(idAuNoSolicitudItem).append(" ");
            sql.append(strTitulo).append(strQuery);
            sql.append("ORDER BY u.id DESC");
           
            List<AuNoSolicitudEntregas> list = getEntityManager().createQuery(sql.toString()).getResultList();
            int posicion = 0;
            for (AuNoSolicitudEntregas diagnostico : list) {
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
    
    @Override
    public List<AuNoSolicitudEntrega> consultarEntregasPorAuNosolicitudesItemIdWithNumeroEntregas(int idAuNoSolicitudItem, int numeroEntregas) throws Exception {
        List<AuNoSolicitudEntrega> obj = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuNoSolicitudEntregas u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            strTitulo = agregarJoin("INNER JOIN AuNoSolicitudItems aun ON u.auNoSolicitudItemsId = aun.id ", strTitulo);
            strQuery.append("AND aun.id = ").append(idAuNoSolicitudItem).append(" ");
            strQuery.append("AND u.numeroEntrega = ").append(numeroEntregas).append(" ");
            sql.append(strTitulo).append(strQuery);
            sql.append("ORDER BY u.id DESC");
           
            List<AuNoSolicitudEntregas> list = getEntityManager().createQuery(sql.toString()).getResultList();
            int posicion = 0;
            for (AuNoSolicitudEntregas diagnostico : list) {
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
    
    @Override
    public List<AuNoSolicitudEntrega> consultarEntregasPorAuNosolicitudesItemIdWithNoAnuladas(int idAuNoSolicitudItem) throws Exception {
        List<AuNoSolicitudEntrega> obj = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuNoSolicitudEntregas u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            strTitulo = agregarJoin("INNER JOIN AuNoSolicitudItems aun ON u.auNoSolicitudItemsId = aun.id ", strTitulo);
            strQuery.append("AND aun.id = ").append(idAuNoSolicitudItem).append(" ");
            strQuery.append("AND u.anulada = 0 ");
            sql.append(strTitulo).append(strQuery);
            sql.append("ORDER BY u.id DESC");
           
            List<AuNoSolicitudEntregas> list = getEntityManager().createQuery(sql.toString()).getResultList();
            int posicion = 0;
            for (AuNoSolicitudEntregas diagnostico : list) {
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
    
    @Override
    public List<AuNoSolicitudEntrega> consultarEntregasPorAuNosolicitudesItemIdWithNoAnuladasYnumeroEntregas(int idAuNoSolicitudItem, int numeroEntrega) throws Exception {
        List<AuNoSolicitudEntrega> obj = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuNoSolicitudEntregas u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            strTitulo = agregarJoin("INNER JOIN AuNoSolicitudItems aun ON u.auNoSolicitudItemsId = aun.id ", strTitulo);
            strQuery.append("AND aun.id = ").append(idAuNoSolicitudItem).append(" ");
            strQuery.append("AND u.numeroEntrega = ").append(numeroEntrega).append(" ");
            strQuery.append("AND u.anulada = 0 ");
            sql.append(strTitulo).append(strQuery);
            sql.append("ORDER BY u.id DESC");
           
            List<AuNoSolicitudEntregas> list = getEntityManager().createQuery(sql.toString()).getResultList();
            int posicion = 0;
            for (AuNoSolicitudEntregas diagnostico : list) {
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
    
    @Override
    public int consultarCantidadEstadoAnuladaloporAuNoSolicitudItem(int idAuNoSolicitudItem) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(m) FROM AuNoSolicitudEntregas m "
                    + "WHERE 1 = 1 "
                    + "AND m.anulada = 1"
                    + "AND m.auNoSolicitudItemsId = '" + idAuNoSolicitudItem +"' ";

            cant = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (NumberFormatException e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }
    
    public static AuNoSolicitudEntrega castEntidadNegocioCorto(AuNoSolicitudEntregas ent) {
        AuNoSolicitudEntrega obj = new AuNoSolicitudEntrega();
        obj.setId(ent.getId());
        if(ent.getAuNoSolicitudesId() != null){
            AuNoSolicitud auNoSolicitudes = new AuNoSolicitud();
            auNoSolicitudes.setId(ent.getAuNoSolicitudesId().getId());
            obj.setAuNoSolicitudesId(auNoSolicitudes);
        }
        if(ent.getAuNoSolicitudItemsId() != null){
            AuNoSolicitudItem auNoSolicitudItem = new AuNoSolicitudItem();
            auNoSolicitudItem.setId(ent.getAuNoSolicitudItemsId().getId());
            obj.setAuNoSolicitudItemsId(auNoSolicitudItem);
        }
        if(ent.getAuNoSolicitudEntregaDetallesId() != null){
            AuNoSolicitudEntregaDetalle entregaDetella = new AuNoSolicitudEntregaDetalle();
            entregaDetella.setId(ent.getAuNoSolicitudEntregaDetallesId().getId());
            obj.setAuNoSolicitudEntregaDetallesId(entregaDetella);
        }
        obj.setFuenteOrigen(ent.getFuenteOrigen());
        obj.setTipoEntrega(ent.getTipoEntrega());
        obj.setCantidadPorEntregar(ent.getCantidadPorEntregar());
        obj.setCantidadEntregada(ent.getCantidadEntregada());
        obj.setCantidadPendiente(ent.getCantidadPendiente());
        obj.setReclamaAfiliado(ent.getReclamaAfiliado());
        obj.setNombreReclama(ent.getNombreReclama());
        obj.setTelefonoReclama(ent.getTelefonoReclama());
        obj.setCelularReclama(ent.getCelularReclama());
        obj.setMaeCausaMoEntregaId(ent.getMaeCausaMoEntregaId());
        obj.setMaeCausaMoEntregaCodigo(ent.getMaeCausaMoEntregaCodigo());
        obj.setMaeCausaMoEntregaValor(ent.getMaeCausaMoEntregaValor());
        obj.setMaeCausaMoEntregaTipo(ent.getMaeCausaMoEntregaTipo());
        obj.setAnulada(ent.getAnulada());
        obj.setAnuladaObservacion(ent.getAnuladaObservacion());
        obj.setNoPrestadoObservacion(ent.getNoPrestadoObservacion());
        obj.setNumeroEntrega(ent.getNumeroEntrega());
        obj.setFaltantes(ent.getFaltantes());
         
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }

    public static AuNoSolicitudEntrega castEntidadNegocioLargo(AuNoSolicitudEntregas ent) {
        AuNoSolicitudEntrega obj = new AuNoSolicitudEntrega();
        obj.setId(ent.getId());
        if(ent.getAuNoSolicitudesId() != null){
            AuNoSolicitud auNoSolicitudes = new AuNoSolicitud();
            auNoSolicitudes.setId(ent.getAuNoSolicitudesId().getId());
            obj.setAuNoSolicitudesId(auNoSolicitudes);
        }
        if(ent.getAuNoSolicitudItemsId() != null){
            AuNoSolicitudItem auNoSolicitudItem = new AuNoSolicitudItem();
            auNoSolicitudItem.setId(ent.getAuNoSolicitudItemsId().getId());
            obj.setAuNoSolicitudItemsId(auNoSolicitudItem);
        }
        if(ent.getAuNoSolicitudEntregaDetallesId() != null){
            AuNoSolicitudEntregaDetalle entregaDetella = new AuNoSolicitudEntregaDetalle();
            entregaDetella.setId(ent.getAuNoSolicitudEntregaDetallesId().getId());
            entregaDetella.setCantidadEntregada(ent.getAuNoSolicitudEntregaDetallesId().getCantidadEntregada());
            entregaDetella.setFaltantes(ent.getAuNoSolicitudEntregaDetallesId().getFaltantes());
            entregaDetella.setFechaFin(ent.getAuNoSolicitudEntregaDetallesId().getFechaFin());
            obj.setAuNoSolicitudEntregaDetallesId(entregaDetella);
        }
        obj.setFuenteOrigen(ent.getFuenteOrigen());
        obj.setTipoEntrega(ent.getTipoEntrega());
        obj.setCantidadPorEntregar(ent.getCantidadPorEntregar());
        obj.setCantidadEntregada(ent.getCantidadEntregada());
        obj.setCantidadPendiente(ent.getCantidadPendiente());
        obj.setReclamaAfiliado(ent.getReclamaAfiliado());
        obj.setNombreReclama(ent.getNombreReclama());
        obj.setTelefonoReclama(ent.getTelefonoReclama());
        obj.setCelularReclama(ent.getCelularReclama());
        obj.setMaeCausaMoEntregaId(ent.getMaeCausaMoEntregaId());
        obj.setMaeCausaMoEntregaCodigo(ent.getMaeCausaMoEntregaCodigo());
        obj.setMaeCausaMoEntregaValor(ent.getMaeCausaMoEntregaValor());
        obj.setMaeCausaMoEntregaTipo(ent.getMaeCausaMoEntregaTipo());
        obj.setAnulada(ent.getAnulada());
        obj.setAnuladaObservacion(ent.getAnuladaObservacion());
        obj.setNoPrestadoObservacion(ent.getNoPrestadoObservacion());
        obj.setFechaHoraEntrega(ent.getFechaHoraEntrega());
        obj.setNumeroEntrega(ent.getNumeroEntrega());
        obj.setFaltantes(ent.getFaltantes());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        return obj;
    }
    
    public static AuNoSolicitudEntrega castEntidadNegocioLargo(AuNoSolicitudEntregas ent, int posicion) {
        AuNoSolicitudEntrega obj = new AuNoSolicitudEntrega();
        obj.setId(ent.getId());
        obj.setPosicion(posicion);
        if(ent.getAuNoSolicitudesId() != null){
            AuNoSolicitud auNoSolicitudes = new AuNoSolicitud();
            auNoSolicitudes.setId(ent.getAuNoSolicitudesId().getId());
            obj.setAuNoSolicitudesId(auNoSolicitudes);
        }
        if(ent.getAuNoSolicitudItemsId() != null){
            AuNoSolicitudItem auNoSolicitudItem = new AuNoSolicitudItem();
            auNoSolicitudItem.setId(ent.getAuNoSolicitudItemsId().getId());
            auNoSolicitudItem.setCantidadSolicitada(ent.getAuNoSolicitudItemsId().getCantidadSolicitada());
            obj.setAuNoSolicitudItemsId(auNoSolicitudItem);
        }
        if(ent.getAuNoSolicitudEntregaDetallesId()!= null){
            AuNoSolicitudEntregaDetalle entregaDetella = new AuNoSolicitudEntregaDetalle();
            entregaDetella.setId(ent.getAuNoSolicitudEntregaDetallesId().getId());
            entregaDetella.setCantidadEntregada(ent.getAuNoSolicitudEntregaDetallesId().getCantidadEntregada());
            obj.setAuNoSolicitudEntregaDetallesId(entregaDetella);
        }
        obj.setFuenteOrigen(ent.getFuenteOrigen());
        obj.setTipoEntrega(ent.getTipoEntrega());
        obj.setCantidadPorEntregar(ent.getCantidadPorEntregar());
        obj.setCantidadEntregada(ent.getCantidadEntregada());
        obj.setCantidadPendiente(ent.getCantidadPendiente());
        obj.setReclamaAfiliado(ent.getReclamaAfiliado());
        obj.setNombreReclama(ent.getNombreReclama());
        obj.setTelefonoReclama(ent.getTelefonoReclama());
        obj.setCelularReclama(ent.getCelularReclama());
        obj.setMaeCausaMoEntregaId(ent.getMaeCausaMoEntregaId());
        obj.setMaeCausaMoEntregaCodigo(ent.getMaeCausaMoEntregaCodigo());
        obj.setMaeCausaMoEntregaValor(ent.getMaeCausaMoEntregaValor());
        obj.setMaeCausaMoEntregaTipo(ent.getMaeCausaMoEntregaTipo());
        obj.setAnulada(ent.getAnulada());
        obj.setAnuladaObservacion(ent.getAnuladaObservacion());
        obj.setNoPrestadoObservacion(ent.getNoPrestadoObservacion());
        obj.setFechaHoraEntrega(ent.getFechaHoraEntrega());
        obj.setNumeroEntrega(ent.getNumeroEntrega());
        obj.setFaltantes(ent.getFaltantes());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        return obj;
    }
    
    public static AuNoSolicitudEntregas castNegocioEntidad(AuNoSolicitudEntrega obj) {
        AuNoSolicitudEntregas ent = new AuNoSolicitudEntregas();
        ent.setId(obj.getId());
        if(obj.getAuNoSolicitudesId() != null){
            AuNoSolicitudes auNoSolicitudes = new AuNoSolicitudes();
            auNoSolicitudes.setId(obj.getAuNoSolicitudesId().getId());
            ent.setAuNoSolicitudesId(auNoSolicitudes);
        }
        if(obj.getAuNoSolicitudItemsId() != null){
            AuNoSolicitudItems auNoSolicitudItem = new AuNoSolicitudItems();
            auNoSolicitudItem.setId(obj.getAuNoSolicitudItemsId().getId());
            ent.setAuNoSolicitudItemsId(auNoSolicitudItem);
        }
        if(obj.getAuNoSolicitudEntregaDetallesId() != null){
            AuNoSolicitudEntregaDetalles entregaDetella = new AuNoSolicitudEntregaDetalles();
            entregaDetella.setId(obj.getAuNoSolicitudEntregaDetallesId().getId());
            ent.setAuNoSolicitudEntregaDetallesId(entregaDetella);
        }
        ent.setFuenteOrigen(obj.getFuenteOrigen());
        ent.setTipoEntrega(obj.getTipoEntrega());
        ent.setCantidadPorEntregar(obj.getCantidadPorEntregar());
        ent.setCantidadEntregada(obj.getCantidadEntregada());
        ent.setCantidadPendiente(obj.getCantidadPendiente());
        ent.setReclamaAfiliado(obj.isReclamaAfiliado());
        ent.setNombreReclama(obj.getNombreReclama());
        ent.setTelefonoReclama(obj.getTelefonoReclama());
        ent.setCelularReclama(obj.getCelularReclama());
        ent.setMaeCausaMoEntregaId(obj.getMaeCausaMoEntregaId());
        ent.setMaeCausaMoEntregaCodigo(obj.getMaeCausaMoEntregaCodigo());
        ent.setMaeCausaMoEntregaValor(obj.getMaeCausaMoEntregaValor());
        ent.setMaeCausaMoEntregaTipo(obj.getMaeCausaMoEntregaTipo());
        ent.setAnulada(obj.isAnulada());
        ent.setAnuladaObservacion(obj.getAnuladaObservacion());
        ent.setNoPrestadoObservacion(obj.getNoPrestadoObservacion());
        ent.setFechaHoraEntrega(obj.getFechaHoraEntrega());
        ent.setNumeroEntrega(obj.getNumeroEntrega());
        ent.setFaltantes(obj.getFaltantes());
        //auditoria
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }
}
