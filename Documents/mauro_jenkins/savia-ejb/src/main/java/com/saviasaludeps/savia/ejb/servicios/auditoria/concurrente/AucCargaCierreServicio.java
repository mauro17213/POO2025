/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCargaCierre;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCargaCierreSuceso;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AucCargaCierreSucesos;
import com.saviasaludeps.savia.ejb.entidades.AucCargaCierres;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucCargaCierreRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
@Remote(AucCargaCierreRemoto.class)
public class AucCargaCierreServicio extends GenericoServicio implements AucCargaCierreRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(u) FROM AucCargaCierres u ";
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
                        case "usuarioCrea":
                            strQuery.append("AND u.usuarioCrea LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "cntPrestadorSedeId.nombreSede":
                            strTitulo = agregarJoin("INNER JOIN CntPrestadorSedes cnp ON u.cntPrestadorSedesId = cnp.id ", strTitulo);
                            strQuery.append("AND cnp.nombre LIKE '%").append(e.getValue()).append("%' ");
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
    public List<AucCargaCierre> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AucCargaCierre> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AucCargaCierres u ";
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
                        case "usuarioCrea":
                            strQuery.append("AND u.usuarioCrea LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "cntPrestadorSedeId.nombreSede":
                            strTitulo = agregarJoin("INNER JOIN CntPrestadorSedes cnp ON u.cntPrestadorSedesId = cnp.id ", strTitulo);
                            strQuery.append("AND cnp.nombre LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        
                    }
                }
            }
            sql.append(strTitulo).append(strQuery);
            sql.append("ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                if (paramConsulta.getOrden().equals("cntPrestadorSedeId.nombreSede")) {
                    sql.append("u.").append("cntPrestadorSedesId.nombre").append((paramConsulta.isAscendente() ? " ASC" : " DESC"));
                } else {
                    sql.append("u.").append(paramConsulta.getOrden()).append((paramConsulta.isAscendente() ? " ASC" : " DESC"));
                }
            } else {
                sql.append("u.id DESC");
            }
            List<AucCargaCierres> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AucCargaCierres per : list) {
                listResult.add(castEntidadNegocio(per));
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
    
    /*@Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AucCargaCierres p ";

            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "WHERE p.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            } else {
                strQuery += "WHERE p.id > 0 ";
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "nombreArchivo":
                            strQuery += "AND p.nombreArchivo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntPrestadorSedeId.nombreSede":
                            strQuery += "AND p.cntPrestadorSedesId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
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
    public List<AucCargaCierre> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AucCargaCierre> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AucCargaCierres p ";

            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "WHERE p.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            } else {
                strQuery += "WHERE p.id > 0 ";
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "nombreArchivo":
                            strQuery += "AND p.nombreArchivo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntPrestadorSedeId.nombreSede":
                            strQuery += "AND p.cntPrestadorSedesId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                if (paramConsulta.getOrden().equals("cntPrestadorSedeId.nombreSede")) {
                    strQuery += "p.cntPrestadorSedesId.nombre "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else {
                    strQuery += "p." + paramConsulta.getOrden() + " "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                }
            } else {
                strQuery += "p.id DESC";
            }
            List<AucCargaCierres> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AucCargaCierres afiliado : list) {
                listaResultados.add(castEntidadNegocio(afiliado));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }*/

    @Override
    public AucCargaCierre consultar(int id) throws Exception {
        AucCargaCierre objRes = null;
        try {
            objRes = castEntidadNegocio((AucCargaCierres) getEntityManager().find(AucCargaCierres.class, id));
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
    public int insertar(AucCargaCierre obj) throws Exception {
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
    public void actualizar(AucCargaCierre obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucCargaCierres a SET ";
            strQuery += "a.gnEmpresasId.id = :gnEmpresasId ,";
            strQuery += "a.cntPrestadorSedesId.id = :cntPrestadorSedesId ,";
            strQuery += "a.estado = :estado ,";
            strQuery += "a.fechaHoraInicio = :fechaHoraInicio ,";
            strQuery += "a.fechaHoraFin = :fechaHoraFin ,";
            strQuery += "a.registrosTotal = :registroTotal ,";
            strQuery += "a.registrosExitosos = :registrosExitosos ,";
            strQuery += "a.registrosRechazados = :registrosRechazados ,";
            strQuery += "a.nombreArchivo = :nombreArchivo ,";
            strQuery += "a.ruta = :ruta ,";
            strQuery += "a.archivo = :archivo ,";
            strQuery += "a.existe = :existe ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("gnEmpresasId", obj.getGnEmpresaId().getId());
            query.setParameter("cntPrestadorSedesId", obj.getCntPrestadorSedeId().getId());
            query.setParameter("estado", (short) obj.getEstado());
            query.setParameter("fechaHoraInicio", obj.getFechaHoraInicio());
            query.setParameter("fechaHoraFin", obj.getFechaHoraFin());
            query.setParameter("registroTotal", (short) obj.getRegistrosTotal());
            query.setParameter("registrosExitosos", (short) obj.getRegistrosExitosos());
            query.setParameter("registrosRechazados", (short) obj.getRegistrosRechazados());
            query.setParameter("nombreArchivo", obj.getNombreArchivo());
            query.setParameter("ruta", obj.getRuta());
            query.setParameter("archivo", obj.getArchivo());
            query.setParameter("existe", obj.isExiste());
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
    public void actualizarEstadoProcesado(AucCargaCierre obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucCargaCierres a SET ";
            strQuery += "a.estado = :estado ";
            strQuery += "WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("estado", (short) obj.getEstado());
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
    public AucCargaCierre eliminar(int id) throws Exception {
        AucCargaCierre obj = null;
        try {
            AucCargaCierres ent = getEntityManager().find(AucCargaCierres.class, id);
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

    @Override
    public List<AucCargaCierre> consulltarPorEstadoCola(Integer numeroMaximoRegistro) throws Exception {
        List<AucCargaCierre> listResult = new ArrayList();
        try {

            String strQuery = "FROM AucCargaCierres auc "
                    + "WHERE auc.id > 0 "
                    + "AND auc.estado = 0 "
                    + "ORDER BY auc.id ASC ";

            List<AucCargaCierres> list = getEntityManager().createQuery(strQuery).setMaxResults(numeroMaximoRegistro)
                    .getResultList();
            for (AucCargaCierres per : list) {
                listResult.add(castEntidadNegocio(per));
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

    private AucCargaCierre castEntidadNegocio(AucCargaCierres entidad) {
        AucCargaCierre negocio = new AucCargaCierre();
        negocio.setId(entidad.getId());
        negocio.setGnEmpresaId(new Empresa(entidad.getGnEmpresasId().getId()));
        negocio.setCntPrestadorId(new CntPrestador(entidad.getCntPrestadorSedesId().getId()));
        negocio.setCntPrestadorSedeId(new CntPrestadorSede(entidad.getCntPrestadorSedesId().getId(), entidad.getCntPrestadorSedesId().getNombre(), negocio.getCntPrestadorId()));
        negocio.setEstado(entidad.getEstado());
        negocio.setFechaHoraInicio(entidad.getFechaHoraInicio());
        negocio.setFechaHoraFin(entidad.getFechaHoraFin());
        negocio.setRegistrosTotal(entidad.getRegistrosTotal());
        negocio.setRegistrosExitosos(entidad.getRegistrosExitosos());
        negocio.setRegistrosRechazados(entidad.getRegistrosRechazados());
        negocio.setNombreArchivo(entidad.getNombreArchivo());
        negocio.setRuta(entidad.getRuta());
        negocio.setArchivo(entidad.getArchivo());
        negocio.setExiste(entidad.getExiste());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrear());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }

    private AucCargaCierres castNegocioEntidad(AucCargaCierre negocio) {
        AucCargaCierres entidad = new AucCargaCierres();
        entidad.setGnEmpresasId(new GnEmpresas(negocio.getGnEmpresaId().getId()));
        entidad.setCntPrestadorSedesId(new CntPrestadorSedes(negocio.getCntPrestadorSedeId().getId()));
        entidad.setEstado((short) negocio.getEstado());
        entidad.setFechaHoraInicio(negocio.getFechaHoraInicio());
        entidad.setFechaHoraFin(negocio.getFechaHoraFin());
        entidad.setRegistrosTotal((short) negocio.getRegistrosTotal());
        entidad.setRegistrosExitosos((short) negocio.getRegistrosExitosos());
        entidad.setRegistrosRechazados((short) negocio.getRegistrosRechazados());
        entidad.setNombreArchivo(negocio.getNombreArchivo());
        entidad.setRuta(negocio.getRuta());
        entidad.setArchivo(negocio.getArchivo());
        entidad.setExiste(negocio.isExiste());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrear(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

}
