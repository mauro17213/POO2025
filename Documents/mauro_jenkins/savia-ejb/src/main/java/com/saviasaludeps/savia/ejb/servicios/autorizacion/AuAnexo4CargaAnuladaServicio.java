/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4CargaAnulada;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4CargaAnuladas;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4CargaAnuladaRemoto;
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
@Remote(AuAnexo4CargaAnuladaRemoto.class)
public class AuAnexo4CargaAnuladaServicio extends GenericoServicio implements AuAnexo4CargaAnuladaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(u) FROM AuAnexo4CargaAnuladas u ";
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
                        case "nombre":
                            strQuery.append("AND u.nombre LIKE '%").append(e.getValue()).append("%' ");
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
    public List<AuAnexo4CargaAnulada> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo4CargaAnulada> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuAnexo4CargaAnuladas u ";
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
                        case "nombre":
                            strQuery.append("AND u.nombre LIKE '%").append(e.getValue()).append("%' ");
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
            List<AuAnexo4CargaAnuladas> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexo4CargaAnuladas per : list) {
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
    public AuAnexo4CargaAnulada consultar(int id) throws Exception {
        AuAnexo4CargaAnulada objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AuAnexo4CargaAnuladas) getEntityManager().find(AuAnexo4CargaAnuladas.class, id));
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
    public int insertar(AuAnexo4CargaAnulada obj) throws Exception {
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
    public void actualizar(AuAnexo4CargaAnulada obj) throws Exception {
        try {
            String hql = "UPDATE AuAnexo4CargaAnuladas SET "
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
    public void actualizarProceso(AuAnexo4CargaAnulada obj) throws Exception {
        try {
            String hql = "UPDATE AuAnexo4CargaAnuladas SET "
                    + "estado = :estado "
                    + ", exitosos = :exitosos "
                    + ", fallidos = :fallidos "
                    + ((obj.getFechaHoraFin() != null) ? ", fechaHoraFin = :fechaHoraFin " : "")
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", obj.getEstado());
           
            query.setParameter("exitosos", obj.getExitosos());
            query.setParameter("fallidos", obj.getFallidos());
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
    public void actualizarEstado(AuAnexo4CargaAnulada obj) throws Exception {
        try {
            String hql = "UPDATE AuAnexo4CargaAnuladas SET "
                    + "estado = :estado "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", obj.getEstado());
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
    public List<AuAnexo4CargaAnulada> consultarArchivoNombre(String nombre) throws java.lang.Exception {
        List<AuAnexo4CargaAnulada> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo4CargaAnuladas c WHERE c.nombre = '" + nombre + "' AND c.estado <> 2";
            List<AuAnexo4CargaAnuladas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuAnexo4CargaAnuladas anexo3Carga : list) {
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
    public List<AuAnexo4CargaAnulada> consultarEstadoProceso(int idEmpresa) throws java.lang.Exception {
        List<AuAnexo4CargaAnulada> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo4CargaAnuladas c WHERE c.estado IN (0, 1) AND c.gnEmpresasId.id = '" + idEmpresa + "'"
                    + " ORDER BY c.id DESC";
            List<AuAnexo4CargaAnuladas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuAnexo4CargaAnuladas anexo3Carga : list) {
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
    public AuAnexo4CargaAnulada consultarSiguienteCarga(int estado) throws Exception {
        AuAnexo4CargaAnulada result = null;
        try {
            String strQuery = "FROM AuAnexo4CargaAnuladas c WHERE c.estado = " + estado + " ORDER BY c.id DESC";
            List<AuAnexo4CargaAnuladas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuAnexo4CargaAnuladas anexo3Carga : list) {
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
    
    public static AuAnexo4CargaAnulada castEntidadNegocioCorto(AuAnexo4CargaAnuladas ent) {
        AuAnexo4CargaAnulada obj = new AuAnexo4CargaAnulada();
        obj.setId(ent.getId());
        obj.setEstado(ent.getEstado());
        obj.setNombre(ent.getNombre());
        obj.setRuta(ent.getRuta());
        obj.setArchivo(ent.getArchivo());
        obj.setRegistros(ent.getRegistros());
        obj.setExitosos(ent.getExitosos());
        obj.setFallidos(ent.getFallidos());
        obj.setFechaHoraInicio(ent.getFechaHoraInicio());
        obj.setFechaHoraFin(ent.getFechaHoraFin());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }

    public static AuAnexo4CargaAnulada castEntidadNegocioLargo(AuAnexo4CargaAnuladas ent) {
        AuAnexo4CargaAnulada obj = new AuAnexo4CargaAnulada();
        obj.setId(ent.getId());
        if(ent.getGnEmpresasId() != null){
            Empresa empresa = new Empresa();
            empresa.setId(ent.getGnEmpresasId().getId());
            obj.setGnEmpresasId(empresa);
        }
        obj.setEstado(ent.getEstado());
        obj.setNombre(ent.getNombre());
        obj.setRuta(ent.getRuta());
        obj.setArchivo(ent.getArchivo());
        obj.setExiste(ent.getExiste());
        obj.setRegistros(ent.getRegistros());
        obj.setExitosos(ent.getExitosos());
        obj.setFallidos(ent.getFallidos());
        obj.setFechaHoraInicio(ent.getFechaHoraInicio());
        obj.setFechaHoraFin(ent.getFechaHoraFin());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }
    
    public static AuAnexo4CargaAnuladas castNegocioEntidad(AuAnexo4CargaAnulada obj) {
        AuAnexo4CargaAnuladas ent = new AuAnexo4CargaAnuladas();
        ent.setId(obj.getId());
        if(obj.getGnEmpresasId() != null){
            GnEmpresas empresa = new GnEmpresas();
            empresa.setId(obj.getGnEmpresasId().getId());
            ent.setGnEmpresasId(empresa);
        }
        ent.setEstado(obj.getEstado());
        ent.setNombre(obj.getNombre());
        ent.setRuta(obj.getRuta());
        ent.setArchivo(obj.getArchivo());
        ent.setExiste(obj.isExiste());
        ent.setRegistros(obj.getRegistros());
        ent.setExitosos(obj.getExitosos());
        ent.setFallidos(obj.getFallidos());
        ent.setFechaHoraInicio(obj.getFechaHoraInicio());
        ent.setFechaHoraFin(obj.getFechaHoraFin());
        //Auditoria
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }
}
