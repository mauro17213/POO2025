/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudEntregaCarga;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudEntregaCargas;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudEntregaCargaRemoto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(AuNoSolicitudEntregaCargaRemoto.class)
public class AuNoSolicitudEntregaCargaServicio extends GenericoServicio implements AuNoSolicitudEntregaCargaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(u) FROM AuNoSolicitudEntregaCargas u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            if (paramConsulta.getEmpresaId() != null) {
                strTitulo = agregarJoin("INNER JOIN GnEmpresas gne ON u.gnEmpresasId = gne.id ", strTitulo);
                strQuery.append("AND gne.id = ").append(paramConsulta.getEmpresaId()).append(" ");
            }       
                    
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND u.id = ").append(e.getValue()).append(" ");
                            break;
                        case "nombreArchivo":
                            strQuery.append("AND u.nombreArchivo LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "estado":
                            strQuery.append("AND u.estado = ").append(e.getValue()).append(" ");
                            break;
                        case "fechaHoraInicio":
                            strQuery.append("AND u.fechaHoraInicio >= '").append(new java.sql.Timestamp(((Date) e.getValue()).getTime())).append("' ");
                            break;
                        case "fechaHoraFin":
                            strQuery.append("AND u.fechaHoraFin <= '").append(new java.sql.Timestamp(((Date) e.getValue()).getTime())).append("' ");
                            break;
                        case "usuarioCrea":
                            strQuery.append("AND u.usuarioCrea LIKE '%").append(e.getValue()).append("%' ");
                            break;
                    }
                }
            }
            sql.append(strTitulo).append(strQuery);
            cant = (int) (long) getEntityManager().createQuery(sql.toString())
                    .getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

    @Override
    public List<AuNoSolicitudEntregaCarga> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuNoSolicitudEntregaCarga> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuNoSolicitudEntregaCargas u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            if (paramConsulta.getEmpresaId() != null) {
                strTitulo = agregarJoin("INNER JOIN GnEmpresas gne ON u.gnEmpresasId = gne.id ", strTitulo);
                strQuery.append("AND gne.id = ").append(paramConsulta.getEmpresaId()).append(" ");
            }  
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND u.id = ").append(e.getValue()).append(" ");
                            break;
                        case "nombreArchivo":
                            strQuery.append("AND u.nombreArchivo LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "estado":
                            strQuery.append("AND u.estado = ").append(e.getValue()).append(" ");
                            break;
                        case "fechaHoraInicio":
                            strQuery.append("AND u.fechaHoraInicio >= '").append(new java.sql.Timestamp(((Date) e.getValue()).getTime())).append("' ");
                            break;
                        case "fechaHoraFin":
                            strQuery.append("AND u.fechaHoraFin <= '").append(new java.sql.Timestamp(((Date) e.getValue()).getTime())).append("' ");
                            break;
                        case "usuarioCrea":
                            strQuery.append("AND u.usuarioCrea LIKE '%").append(e.getValue()).append("%' ");
                            break;
                    }
                }
            }
            sql.append(strTitulo).append(strQuery);
            sql.append("ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                sql.append("u.").append(paramConsulta.getOrden()).append((paramConsulta.isAscendente() ? " ASC" : " DESC"));
            } else {
                sql.append("u.id DESC");
            }
            List<AuNoSolicitudEntregaCargas> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuNoSolicitudEntregaCargas per : list) {
                listResult.add(castEntidadNegocioCorto(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
    private String agregarJoin(String join, String sql) {
        if (sql.contains(join)) {
            return sql;
        } else {
            return sql += join;
        }
    }
    
    @Override
    public AuNoSolicitudEntregaCarga consultar(int id) throws Exception {
        AuNoSolicitudEntregaCarga objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AuNoSolicitudEntregaCargas) getEntityManager().find(AuNoSolicitudEntregaCargas.class, id));
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
    public int insertar(AuNoSolicitudEntregaCarga obj) throws Exception {
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
    public void actualizar(AuNoSolicitudEntregaCarga obj) throws Exception {
        try {
            String hql = "UPDATE AuNoSolicitudEntregaCargas SET "
                    + "estado = :estado "
                    + ", registros = :registros "
                    + ", exitosos = :exitosos "
                    + ", fallidos = :fallidos "
                    + ((obj.getFechaHoraFin() != null) ? ", fechaHoraFin = NOW() ": "")
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("registros", obj.getRegistros());
            query.setParameter("exitosos", obj.getExitosos());
            query.setParameter("fallidos", obj.getFallidos());
            //auditoria
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
    public void actualizarProceso(AuNoSolicitudEntregaCarga obj) throws Exception {
        try {
            String hql = "UPDATE AuNoSolicitudEntregaCargas SET "
                    + "estado = :estado "
                    + ", exitosos = :exitosos "
                    + ", fallidos = :fallidos "
                    + ", fechaHoraFin = :fechaHoraFin "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", obj.getEstado());
           
            query.setParameter("exitosos", obj.getExitosos());
            query.setParameter("fallidos", obj.getFallidos());
            query.setParameter("fechaHoraFin", obj.getFechaHoraFin());
            //auditoria
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
    public void actualizarEstado(AuNoSolicitudEntregaCarga obj) throws Exception {
        try {
            String hql = "UPDATE AuNoSolicitudEntregaCargas SET "
                    + "estado = :estado, "
                    + "detalle = :detalle "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("detalle", obj.getDetalle());
            //auditoria
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
    public List<AuNoSolicitudEntregaCarga> consultarArchivoNombre(String nombre) throws java.lang.Exception {
        List<AuNoSolicitudEntregaCarga> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuNoSolicitudEntregaCargas c WHERE c.nombre = '" + nombre + "' AND c.estado <> 8";
            List<AuNoSolicitudEntregaCargas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuNoSolicitudEntregaCargas anexo3Carga : list) {
                listaResultados.add(castEntidadNegocioLargo(anexo3Carga));
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
    
    @Override
    public List<AuNoSolicitudEntregaCarga> consultarEstadoProceso(int idEmpresa) throws java.lang.Exception {
        List<AuNoSolicitudEntregaCarga> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuNoSolicitudEntregaCargas c WHERE c.estado IN (0, 1) AND c.gnEmpresasId.id = '" + idEmpresa + "'"
                    + " ORDER BY c.id DESC";
            List<AuNoSolicitudEntregaCargas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuNoSolicitudEntregaCargas anexo3Carga : list) {
                listaResultados.add(castEntidadNegocioLargo(anexo3Carga));
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
    
    @Override
    public AuNoSolicitudEntregaCarga consultarSiguienteCarga(int estado) throws Exception {
        AuNoSolicitudEntregaCarga result = null;
        try {
            String strQuery = "FROM AuNoSolicitudEntregaCargas c WHERE c.estado = " + estado + " ORDER BY c.id DESC";
            List<AuNoSolicitudEntregaCargas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuNoSolicitudEntregaCargas anexo3Carga : list) {
                result = castEntidadNegocioLargo(anexo3Carga);
            }
        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return result;
    }
    
    public static AuNoSolicitudEntregaCarga castEntidadNegocioCorto(AuNoSolicitudEntregaCargas ent) {
        AuNoSolicitudEntregaCarga obj = new AuNoSolicitudEntregaCarga();
        obj.setId(ent.getId());
        if(ent.getGnEmpresasId() != null){
            Empresa empresa = new Empresa();
            empresa.setId(ent.getGnEmpresasId().getId());
            obj.setGnEmpresasId(empresa);
        }
        obj.setNombre(ent.getNombre()); 
        obj.setRuta(ent.getRuta());
        obj.setArchivo(ent.getArchivo());
        obj.setExiste(ent.getExiste());
        obj.setEstado(ent.getEstado()); 
        obj.setFechaHoraInicio(ent.getFechaHoraInicio());
        obj.setFechaHoraFin(ent.getFechaHoraFin());
        obj.setRegistros(ent.getRegistros());
        obj.setExitosos(ent.getExitosos());
        obj.setFallidos(ent.getFallidos());
        obj.setDetalle(ent.getDetalle());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }

    public static AuNoSolicitudEntregaCarga castEntidadNegocioLargo(AuNoSolicitudEntregaCargas ent) {
        AuNoSolicitudEntregaCarga obj = new AuNoSolicitudEntregaCarga();
        obj.setId(ent.getId());
        if(ent.getGnEmpresasId() != null){
            Empresa empresa = new Empresa();
            empresa.setId(ent.getGnEmpresasId().getId());
            obj.setGnEmpresasId(empresa);
        }
        obj.setNombre(ent.getNombre()); 
        obj.setRuta(ent.getRuta());
        obj.setArchivo(ent.getArchivo());
        obj.setExiste(ent.getExiste());
        obj.setEstado(ent.getEstado()); 
        obj.setFechaHoraInicio(ent.getFechaHoraInicio());
        obj.setFechaHoraFin(ent.getFechaHoraFin());
        obj.setRegistros(ent.getRegistros());
        obj.setExitosos(ent.getExitosos());
        obj.setFallidos(ent.getFallidos());
        obj.setDetalle(ent.getDetalle());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }
    
    public static AuNoSolicitudEntregaCargas castNegocioEntidad(AuNoSolicitudEntregaCarga obj) {
        AuNoSolicitudEntregaCargas ent = new AuNoSolicitudEntregaCargas();
        ent.setId(obj.getId());
        if(obj.getGnEmpresasId() != null){
            GnEmpresas empresa = new GnEmpresas();
            empresa.setId(obj.getGnEmpresasId().getId());
            ent.setGnEmpresasId(empresa);
        }
        ent.setNombre(obj.getNombre()); 
        ent.setRuta(obj.getRuta());
        ent.setArchivo(obj.getArchivo());
        ent.setExiste(obj.isExiste());
        ent.setEstado(obj.getEstado()); 
        ent.setFechaHoraInicio(obj.getFechaHoraInicio());
        ent.setFechaHoraFin(obj.getFechaHoraFin());
        ent.setRegistros(obj.getRegistros());
        ent.setExitosos(obj.getExitosos());
        ent.setFallidos(obj.getFallidos());
        ent.setDetalle(obj.getDetalle());

        //Auditoria
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }
}
