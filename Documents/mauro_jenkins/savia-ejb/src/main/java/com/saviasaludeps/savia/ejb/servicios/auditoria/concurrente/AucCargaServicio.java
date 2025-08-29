/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCarga;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.AucCargas;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucCargaRemoto;
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
@Remote(AucCargaRemoto.class)
public class AucCargaServicio extends GenericoServicio implements AucCargaRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(u) FROM AucCargas u ";
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
                        case "tipo":
                            strQuery.append("AND u.tipo = ").append(e.getValue()).append(" ");
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
    public List<AucCarga> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AucCarga> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AucCargas u ";
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
                        case "tipo":
                            strQuery.append("AND u.tipo = ").append(e.getValue()).append(" ");
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
            List<AucCargas> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AucCargas per : list) {
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
    
    @Override
    public AucCarga consultar(int id) throws Exception {
        AucCarga objRes = null;
        try {
            objRes = castEntidadNegocio((AucCargas) getEntityManager().find(AucCargas.class, id));
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
    public int insertar(AucCarga obj) throws Exception {
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
    public void actualizar(AucCarga obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucCargas a SET ";
            strQuery += "a.gnEmpresasId.id = :gnEmpresasId ,";
            strQuery += "a.cntPrestadoresId.id = :cntPrestadoresId ,";
            strQuery += "a.cntPrestadorSedesId.id = :cntPrestadorSedesId ,";
            strQuery += "a.estado = :estado ,";
            strQuery += "a.fechaHoraInicio = :fechaHoraInicio ,";
            strQuery += "a.fechaHoraFin = :fechaHoraFin ,";
            strQuery += "a.registrosTotal = :registroTotal ,";
            strQuery += "a.registrosExitosos = :registrosExitosos ,";
            strQuery += "a.registrosRechazados = :registrosRechazados ,";
            strQuery += "a.hopitalizados = :hopitalizados ,";
            strQuery += "a.nombreArchivo = :nombreArchivo ,";
            strQuery += "a.ruta = :ruta ,";
            strQuery += "a.archivo = :archivo ,";
            strQuery += "a.existe = :existe ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("gnEmpresasId", obj.getGnEmpresaId().getId());
            query.setParameter("cntPrestadoresId", obj.getCntPrestadorId().getId());
            query.setParameter("cntPrestadorSedesId", obj.getCntPrestadorSedeId().getId());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("fechaHoraInicio", obj.getFechaHoraInicio());
            query.setParameter("fechaHoraFin", obj.getFechaHoraFin());
            query.setParameter("registroTotal", obj.getRegistrosTotal());
            query.setParameter("registrosExitosos", obj.getRegistrosExitosos());
            query.setParameter("registrosRechazados", obj.getRegistrosRechazados());
            query.setParameter("hopitalizados", obj.getHopitalizados());
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
    public void actualizarEstadoProcesado(AucCarga obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucCargas a SET ";
            strQuery += "a.estado = :estado ";
            strQuery += "WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("estado", obj.getEstado());
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
    public AucCarga eliminar(int id) throws Exception {
        AucCarga obj = null;
        try {
            AucCargas ent = getEntityManager().find(AucCargas.class, id);
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
    public List<AucCarga> consulltarPorEstadoCola(Integer numeroMaximoRegistro) throws Exception {
        List<AucCarga> listResult = new ArrayList();
        try {

            String strQuery = "FROM AucCargas auc "
                    + "WHERE auc.id > 0 "
                    + "AND auc.estado = 0 "
                    + "ORDER BY auc.id ASC ";

            List<AucCargas> list = getEntityManager().createQuery(strQuery).setMaxResults(numeroMaximoRegistro)
                    .getResultList();
            for (AucCargas per : list) {
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

    private AucCarga castEntidadNegocio(AucCargas entidad) {
        AucCarga negocio = new AucCarga();
        negocio.setId(entidad.getId());
        negocio.setGnEmpresaId(new Empresa(entidad.getGnEmpresasId().getId()));
        negocio.setCntPrestadorId(new CntPrestador(entidad.getCntPrestadoresId().getId()));
        negocio.setCntPrestadorSedeId(new CntPrestadorSede(entidad.getCntPrestadorSedesId().getId(), entidad.getCntPrestadorSedesId().getNombre(), negocio.getCntPrestadorId()));
        negocio.setEstado(entidad.getEstado());
        negocio.setFechaHoraInicio(entidad.getFechaHoraInicio());
        negocio.setFechaHoraFin(entidad.getFechaHoraFin());
        negocio.setRegistrosTotal(entidad.getRegistrosTotal());
        negocio.setRegistrosExitosos(entidad.getRegistrosExitosos());
        negocio.setRegistrosRechazados(entidad.getRegistrosRechazados());
        negocio.setHopitalizados(entidad.getHopitalizados());
        negocio.setNombreArchivo(entidad.getNombreArchivo());
        negocio.setRuta(entidad.getRuta());
        negocio.setArchivo(entidad.getArchivo());
        negocio.setExiste(entidad.getExiste());
        if (entidad.getTipo() != null) {
            negocio.setTipo(entidad.getTipo());
        }
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }

    private AucCargas castNegocioEntidad(AucCarga negocio) {
        AucCargas entidad = new AucCargas();
        entidad.setGnEmpresasId(new GnEmpresas(negocio.getGnEmpresaId().getId()));
        entidad.setCntPrestadoresId(new CntPrestadores(negocio.getCntPrestadorId().getId()));
        entidad.setCntPrestadorSedesId(new CntPrestadorSedes(negocio.getCntPrestadorSedeId().getId()));
        entidad.setEstado(negocio.getEstado());
        entidad.setFechaHoraInicio(negocio.getFechaHoraInicio());
        entidad.setFechaHoraFin(negocio.getFechaHoraFin());
        entidad.setRegistrosTotal(negocio.getRegistrosTotal());
        entidad.setRegistrosExitosos(negocio.getRegistrosExitosos());
        entidad.setRegistrosRechazados(negocio.getRegistrosRechazados());
        entidad.setHopitalizados(negocio.getHopitalizados());
        entidad.setNombreArchivo(negocio.getNombreArchivo());
        entidad.setRuta(negocio.getRuta());
        entidad.setArchivo(negocio.getArchivo());
        entidad.setExiste(negocio.isExiste());
        entidad.setTipo(negocio.getTipo());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

}
