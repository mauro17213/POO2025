/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudCarga;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudCargas;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudCargaRemoto;
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
@Remote(AuNoSolicitudCargaRemoto.class)
public class AuNoSolicitudCargaServicio extends GenericoServicio implements AuNoSolicitudCargaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(u) FROM AuNoSolicitudCargas u ";
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
    public List<AuNoSolicitudCarga> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuNoSolicitudCarga> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuNoSolicitudCargas u ";
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
            List<AuNoSolicitudCargas> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuNoSolicitudCargas per : list) {
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
    
    @SuppressWarnings("UnusedAssignment")
    private String agregarJoin(String join, String sql) {
        if (sql.contains(join)) {
            return sql;
        } else {
            return sql += join;
        }
    }
    
    @Override
    public AuNoSolicitudCarga consultar(int id) throws Exception {
        AuNoSolicitudCarga objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AuNoSolicitudCargas) getEntityManager().find(AuNoSolicitudCargas.class, id));
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
    public int insertar(AuNoSolicitudCarga obj) throws Exception {
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
    public void actualizar(AuNoSolicitudCarga obj) throws Exception {
        try {
            String hql = "UPDATE AuNoSolicitudCargas SET "
                    + "estado = :estado "
                    + ", registrosTotal = :registrosTotal "
                    + ", registrosExitosos = :registrosExitosos "
                    + ", registrosRechazados = :registrosRechazados "
                    + ((obj.getFechaHoraFin() != null) ? ", fechaHoraFin = NOW() ": "")
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("registrosTotal", obj.getRegistrosTotal());
            query.setParameter("registrosExitosos", obj.getRegistrosExitosos());
            query.setParameter("registrosRechazados", obj.getRegistrosRechazados());
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
    public void actualizarProceso(AuNoSolicitudCarga obj) throws Exception {
        try {
            String hql = "UPDATE AuNoSolicitudCargas SET "
                    + "estado = :estado "
                    + ", registrosExitosos = :registrosExitosos "
                    + ", registrosRechazados = :registrosRechazados "
                    + ((obj.getFechaHoraFin() != null) ? ", fechaHoraFin = :fechaHoraFin " : "")
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", obj.getEstado());
           
            query.setParameter("registrosExitosos", obj.getRegistrosExitosos());
            query.setParameter("registrosRechazados", obj.getRegistrosRechazados());
            if(obj.getFechaHoraFin() != null){
                query.setParameter("fechaHoraFin", obj.getFechaHoraFin());
            }
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
    public void actualizarEstado(AuNoSolicitudCarga obj) throws Exception {
        try {
            String hql = "UPDATE AuNoSolicitudCargas SET "
                    + "estado = :estado, "
                    + "estadoObservacion = :estadoObservacion, "
                    + "usuarioGestionEstado = :usuarioGestionEstado, "
                    + "terminalGestionEstado = :terminalGestionEstado, "
                    + "fechaHoraGestionEstado = :fechaHoraGestionEstado "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("estadoObservacion", obj.getEstadoObservacion());
            //auditoria
            query.setParameter("usuarioGestionEstado", obj.getUsuarioGestionEstado());
            query.setParameter("terminalGestionEstado", obj.getTerminalGestionEstado());
            query.setParameter("fechaHoraGestionEstado", obj.getFechaHoraGestionEstado());
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
    public List<AuNoSolicitudCarga> consultarArchivoNombre(String nombre) throws java.lang.Exception {
        List<AuNoSolicitudCarga> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuNoSolicitudCargas c WHERE c.nombreArchivo = '" + nombre + "' AND c.estado <> 8";
            List<AuNoSolicitudCargas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuNoSolicitudCargas anexo3Carga : list) {
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
    public List<AuNoSolicitudCarga> consultarEstadoProceso(int idEmpresa) throws java.lang.Exception {
        List<AuNoSolicitudCarga> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuNoSolicitudCargas c WHERE c.estado IN (0, 1, 3, 4, 6, 7) AND c.gnEmpresasId.id = '" + idEmpresa + "'"
                    + " ORDER BY c.id DESC";
            List<AuNoSolicitudCargas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuNoSolicitudCargas anexo3Carga : list) {
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
    public AuNoSolicitudCarga consultarSiguienteCarga(int estado) throws Exception {
        AuNoSolicitudCarga result = null;
        try {
            String strQuery = "FROM AuNoSolicitudCargas c WHERE c.estado = " + estado + " ORDER BY c.id DESC";
            List<AuNoSolicitudCargas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuNoSolicitudCargas anexo3Carga : list) {
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
    
    public static AuNoSolicitudCarga castEntidadNegocioCorto(AuNoSolicitudCargas ent) {
        AuNoSolicitudCarga obj = new AuNoSolicitudCarga();
        obj.setId(ent.getId());
        if(ent.getGnEmpresasId() != null){
            Empresa empresa = new Empresa();
            empresa.setId(ent.getGnEmpresasId().getId());
            obj.setGnEmpresasId(empresa);
        }
         if(ent.getCntPrestadorSedesId() != null){
            CntPrestadorSede prestadorSede = new CntPrestadorSede();
            prestadorSede.setId(ent.getCntPrestadorSedesId().getId());
            obj.setCntPrestadorSedesId(prestadorSede);
        }
        obj.setEstado(ent.getEstado());
        obj.setEstadoObservacion(ent.getEstadoObservacion());
        obj.setFechaHoraInicio(ent.getFechaHoraInicio());
        obj.setFechaHoraFin(ent.getFechaHoraFin());
        obj.setRegistrosTotal(ent.getRegistrosTotal());
        obj.setRegistrosExitosos(ent.getRegistrosExitosos());
        obj.setRegistrosRechazados(ent.getRegistrosRechazados());
        obj.setNombreArchivo(ent.getNombreArchivo());
        obj.setRuta(ent.getRuta());
        obj.setArchivo(ent.getArchivo());
        obj.setExiste(ent.getExiste());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }

    public static AuNoSolicitudCarga castEntidadNegocioLargo(AuNoSolicitudCargas ent) {
        AuNoSolicitudCarga obj = new AuNoSolicitudCarga();
        obj.setId(ent.getId());
        if(ent.getGnEmpresasId() != null){
            Empresa empresa = new Empresa();
            empresa.setId(ent.getGnEmpresasId().getId());
            obj.setGnEmpresasId(empresa);
        }
        if(ent.getCntPrestadorSedesId() != null){
            CntPrestadorSede prestadorSede = new CntPrestadorSede();
            prestadorSede.setId(ent.getCntPrestadorSedesId().getId());
            obj.setCntPrestadorSedesId(prestadorSede);
        }
        obj.setEstado(ent.getEstado());
        obj.setEstadoObservacion(ent.getEstadoObservacion());
        obj.setFechaHoraInicio(ent.getFechaHoraInicio());
        obj.setFechaHoraFin(ent.getFechaHoraFin());
        obj.setRegistrosTotal(ent.getRegistrosTotal());
        obj.setRegistrosExitosos(ent.getRegistrosExitosos());
        obj.setRegistrosRechazados(ent.getRegistrosRechazados());
        obj.setNombreArchivo(ent.getNombreArchivo());
        obj.setRuta(ent.getRuta());
        obj.setArchivo(ent.getArchivo());
        obj.setExiste(ent.getExiste());
        obj.setUsuarioGestionEstado(ent.getUsuarioGestionEstado());
        obj.setTerminalGestionEstado(ent.getTerminalGestionEstado());
        obj.setFechaHoraGestionEstado(ent.getFechaHoraGestionEstado());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }
    
  
    
    public static AuNoSolicitudCargas castNegocioEntidad(AuNoSolicitudCarga obj) {
        AuNoSolicitudCargas ent = new AuNoSolicitudCargas();
        ent.setId(obj.getId());
        if(obj.getGnEmpresasId() != null){
            GnEmpresas empresa = new GnEmpresas();
            empresa.setId(obj.getGnEmpresasId().getId());
            ent.setGnEmpresasId(empresa);
        }
        if(obj.getCntPrestadorSedesId() != null){
            CntPrestadorSedes prestadorSede = new CntPrestadorSedes();
            prestadorSede.setId(obj.getCntPrestadorSedesId().getId());
            ent.setCntPrestadorSedesId(prestadorSede);
        }
        ent.setEstado(obj.getEstado());
        ent.setEstadoObservacion(obj.getEstadoObservacion());
        ent.setFechaHoraInicio(obj.getFechaHoraInicio());
        ent.setFechaHoraFin(obj.getFechaHoraFin());
        ent.setRegistrosTotal(obj.getRegistrosTotal());
        ent.setRegistrosExitosos(obj.getRegistrosExitosos());
        ent.setRegistrosRechazados(obj.getRegistrosRechazados());
        ent.setNombreArchivo(obj.getNombreArchivo());
        ent.setRuta(obj.getRuta());
        ent.setArchivo(obj.getArchivo());
        ent.setExiste(obj.isExiste());
        ent.setUsuarioGestionEstado(obj.getUsuarioGestionEstado());
        ent.setTerminalGestionEstado(obj.getTerminalGestionEstado());
        ent.setFechaHoraGestionEstado(obj.getFechaHoraGestionEstado());
        //Auditoria
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }
}
