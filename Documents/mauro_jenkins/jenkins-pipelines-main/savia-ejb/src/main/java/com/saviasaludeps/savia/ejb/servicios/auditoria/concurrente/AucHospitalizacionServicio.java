/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucAfiliado;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucEgreso;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucIngreso;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.AucAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AucEgresos;
import com.saviasaludeps.savia.ejb.entidades.AucIngresos;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizaciones;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionRemoto;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
@Remote(AucHospitalizacionRemoto.class)
public class AucHospitalizacionServicio extends GenericoServicio implements AucHospitalizacionRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(p) FROM AucHospitalizaciones p ";
            StringBuilder strQuery = new StringBuilder(" WHERE p.id > 0 ");
            StringBuilder sql = new StringBuilder();
            if (paramConsulta.getEmpresaId() != null) {
                strTitulo = agregarJoin(" INNER JOIN GnEmpresas gne ON p.gnEmpresasId = gne.id ", strTitulo);
                strQuery.append(" AND gne.id = ").append(paramConsulta.getEmpresaId()).append(" ");
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append(" AND p.id = ").append(e.getValue()).append(" ");
                            break;
                        case "estadoAuditoria":
                            strQuery.append(" AND  p.estadoAuditoria = ").append(e.getValue()).append(" ");
                            break;
                        case "aucIngresoId.maeAltaTempranaCodigo":
                            strTitulo = agregarJoin(" INNER JOIN AucIngresos af ON p.aucIngresosId = af.id ", strTitulo);
                            strQuery.append(" AND af.maeAltaTempranaCodigo = '").append((String) e.getValue()).append("' ");
                            break;
                        case "aucIngresoId.fechaIngreso":
                            strTitulo = agregarJoin(" INNER JOIN AucIngresos af ON p.aucIngresosId = af.id ", strTitulo);
                            strQuery.append(" AND af.fechaIngreso = '").append((String) e.getValue()).append("' ");
                            break;
                        case "aucAfiliadoId.maeTipoDocumentoId":
                            strTitulo = agregarJoin(" INNER JOIN AucAfiliados auf ON p.aucAfiliadosId = auf.id ", strTitulo);
                            strQuery.append(" AND auf.maeTipoDocumentoId = ").append(e.getValue()).append(" ");
                            break;
                        case "aucAfiliadoId.numeroDocumento":
                            strTitulo = agregarJoin(" INNER JOIN AucAfiliados auf ON p.aucAfiliadosId = auf.id ", strTitulo);
                            strTitulo = agregarJoin(" INNER JOIN AsegAfiliados aseg ON auf.asegAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append(" AND aseg.id IN ( SELECT asega.id FROM AsegAfiliadoDocumentos ad INNER JOIN AsegAfiliados asega ON ad.asegAfiliadosId = asega.id WHERE ad.numeroDocumento = '").append((String) e.getValue()).append("') ");
                            break;
                        case "aucAfiliadoId.nombreCompleto":
                            strTitulo = agregarJoin(" INNER JOIN AucAfiliados auf ON p.aucAfiliadosId = auf.id ", strTitulo);
                            strQuery.append(" AND CONCAT(auf.primerNombre,' ',auf.segundoNombre,' ',auf.primerApellido, ' ' ,auf.segundoApellido ) LIKE '%")
                                    .append((String) e.getValue()).append("%' ");
                            break;
                        case "aucAfiliadoId.ubicacionAfiliacionId":
                            strTitulo = agregarJoin(" INNER JOIN AucAfiliados auf ON p.aucAfiliadosId = auf.id ", strTitulo);
                            strQuery.append(" AND auf.ubicacionAfiliacionId = ").append(e.getValue()).append(" ");
                            break;
                        case "estado":
                            strQuery.append(" AND p.estado = ").append(e.getValue()).append(" ");
                            break;
                        case "cntPrestadorId.razonSocial":
                            strTitulo = agregarJoin(" INNER JOIN CntPrestadores cnp ON p.cntPrestadoresId = cnp.id ", strTitulo);
                            strQuery.append(" AND cnp.razonSocial LIKE '%").append((String) e.getValue()).append("%'");
                            break;
                        case "cntPrestadorSedeId.nombreSede":
                            strTitulo = agregarJoin(" INNER JOIN CntPrestadorSedes cnps ON p.cntPrestadorSedesId = cnps.id ", strTitulo);
                            strQuery.append(" AND cnps.nombre LIKE '%").append((String) e.getValue()).append("%'");
                            break;
                        case "gnUsuariosAuditorId.nombre":
                            strTitulo = agregarJoin(" INNER JOIN GnUsuarios gnu ON p.gnUsuariosAuditorId = gnu.id ", strTitulo);
                            strQuery.append(" AND gnu.nombre LIKE '%").append((String) e.getValue()).append("%'");
                            break;
                        case "diasHospitalizacion":
                            strQuery.append(" AND p.diasHospitalizacion = '").append((String) e.getValue()).append("'");
                            break;
                        case "aplicaRescate":
                            strQuery.append(" AND p.aplicaRescate = '").append((String) e.getValue()).append("'");
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
    public List<AucHospitalizacion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AucHospitalizacion> listaResultados = new ArrayList();
        try {
            String strTitulo = "SELECT p FROM AucHospitalizaciones p ";
            StringBuilder strQuery = new StringBuilder(" WHERE p.id > 0 ");
            StringBuilder sql = new StringBuilder();
            if (paramConsulta.getEmpresaId() != null) {
                strTitulo = agregarJoin(" INNER JOIN GnEmpresas gne ON p.gnEmpresasId = gne.id ", strTitulo);
                strQuery.append(" AND gne.id = ").append(paramConsulta.getEmpresaId()).append(" ");
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append(" AND p.id = ").append(e.getValue()).append(" ");
                            break;
                        case "estadoAuditoria":
                            strQuery.append(" AND  p.estadoAuditoria = ").append(e.getValue()).append(" ");
                            break;
                        case "aucIngresoId.maeAltaTempranaCodigo":
                            strTitulo = agregarJoin(" INNER JOIN AucIngresos af ON p.aucIngresosId = af.id ", strTitulo);
                            strQuery.append(" AND af.maeAltaTempranaCodigo = '").append((String) e.getValue()).append("' ");
                            break;
                        case "aucIngresoId.fechaIngreso":
                            strTitulo = agregarJoin(" INNER JOIN AucIngresos af ON p.aucIngresosId = af.id ", strTitulo);
                            strQuery.append(" AND af.fechaIngreso = '").append((String) e.getValue()).append("' ");
                            break;
                        case "aucAfiliadoId.maeTipoDocumentoId":
                            strTitulo = agregarJoin(" INNER JOIN AucAfiliados auf ON p.aucAfiliadosId = auf.id ", strTitulo);
                            strQuery.append(" AND auf.maeTipoDocumentoId = ").append(e.getValue()).append(" ");
                            break;
                        case "aucAfiliadoId.numeroDocumento":
                            strTitulo = agregarJoin(" INNER JOIN AucAfiliados auf ON p.aucAfiliadosId = auf.id ", strTitulo);
                            strTitulo = agregarJoin(" INNER JOIN AsegAfiliados aseg ON auf.asegAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append(" AND aseg.id IN ( SELECT asega.id FROM AsegAfiliadoDocumentos ad INNER JOIN AsegAfiliados asega ON ad.asegAfiliadosId = asega.id WHERE ad.numeroDocumento = '").append((String) e.getValue()).append("') ");
                            break;
                        case "aucAfiliadoId.nombreCompleto":
                            strTitulo = agregarJoin(" INNER JOIN AucAfiliados auf ON p.aucAfiliadosId = auf.id ", strTitulo);
                            strQuery.append(" AND CONCAT(auf.primerNombre,' ',auf.segundoNombre,' ',auf.primerApellido, ' ' ,auf.segundoApellido ) LIKE '%")
                                    .append((String) e.getValue()).append("%' ");
                            break;
                        case "aucAfiliadoId.ubicacionAfiliacionId":
                            strTitulo = agregarJoin(" INNER JOIN AucAfiliados auf ON p.aucAfiliadosId = auf.id ", strTitulo);
                            strQuery.append(" AND auf.ubicacionAfiliacionId = ").append(e.getValue()).append(" ");
                            break;
                        case "estado":
                            strQuery.append(" AND p.estado = ").append(e.getValue()).append(" ");
                            break;
                        case "cntPrestadorId.razonSocial":
                            strTitulo = agregarJoin(" INNER JOIN CntPrestadores cnp ON p.cntPrestadoresId = cnp.id ", strTitulo);
                            strQuery.append(" AND cnp.razonSocial LIKE '%").append((String) e.getValue()).append("%'");
                            break;
                        case "cntPrestadorSedeId.nombreSede":
                            strTitulo = agregarJoin(" INNER JOIN CntPrestadorSedes cnps ON p.cntPrestadorSedesId = cnps.id ", strTitulo);
                            strQuery.append(" AND cnps.nombre LIKE '%").append((String) e.getValue()).append("%'");
                            break;
                        case "gnUsuariosAuditorId.nombre":
                            strTitulo = agregarJoin(" INNER JOIN GnUsuarios gnu ON p.gnUsuariosAuditorId = gnu.id ", strTitulo);
                            strQuery.append(" AND gnu.nombre LIKE '%").append((String) e.getValue()).append("%'");
                            break;
                        case "diasHospitalizacion":
                            strQuery.append(" AND p.diasHospitalizacion = '").append((String) e.getValue()).append("'");
                            break;
                        case "aplicaRescate":
                            strQuery.append(" AND p.aplicaRescate = '").append((String) e.getValue()).append("'");
                            break;

                    }
                }
            }
            sql.append(strTitulo).append(strQuery);
            sql.append(" ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                if (paramConsulta.getOrden().equals("aucIngresoId.fechaIngreso")) {
                    sql.append("p.aucIngresosId.fechaIngreso ").append((paramConsulta.isAscendente() ? "ASC" : "DESC"));
                } else {
                    sql.append("p.").append(paramConsulta.getOrden()).append((paramConsulta.isAscendente() ? " ASC" : " DESC"));
                }
            } else {
                sql.append("p.diasHospitalizacion DESC");
            }
            List<AucHospitalizaciones> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AucHospitalizaciones hospitalizacion : list) {
                listaResultados.add(castEntidadNegocioCorto(hospitalizacion));
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
    public int consultarCantidadListaConsultaTrescientosSesenta(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AucHospitalizaciones p "
                    + "WHERE p.id > 0 ";

            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "AND p.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            }

            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND p.aucAfiliadosId.asegAfiliadosId.id = " + paramConsulta.getParametroConsulta1() + " ";
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "estadoAuditoria":
                            strQuery += "AND p.estadoAuditoria = " + e.getValue() + " ";
                            break;
                        case "aucIngresoId.fechaIngreso":
                            strQuery += "AND p.aucIngresosId.fechaIngreso = '" + e.getValue() + "' ";
                            break;
                        case "aucAfiliadoId.maeTipoDocumentoId":
                            strQuery += "AND p.aucAfiliadosId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "aucAfiliadoId.numeroDocumento":
                            strQuery += "AND p.aucAfiliadosId.numeroDocumento = '" + e.getValue() + "' ";
                            break;
                        case "aucAfiliadoId.nombreCompleto":
                            strQuery += "AND (p.aucAfiliadosId.primerNombre LIKE '%" + e.getValue() + "' "
                                    + "OR p.aucAfiliadosId.segundoNombre LIKE '%" + e.getValue() + "' "
                                    + "OR p.aucAfiliadosId.primerApellido LIKE '%" + e.getValue() + "' "
                                    + "OR p.aucAfiliadosId.segundoApellido LIKE '%" + e.getValue() + "') ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "cntPrestadorId.razonSocial":
                            strQuery += "AND p.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntPrestadorSedeId.nombreSede":
                            strQuery += "AND p.cntPrestadorSedesId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "gnUsuariosAuditorId.nombre":
                            strQuery += "AND p.gnUsuariosAuditorId.nombre LIKE '%" + e.getValue() + "%' ";
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
    public List<AucHospitalizacion> consultarListaConsultaTrescientosSesenta(ParamConsulta paramConsulta) throws Exception {
        List<AucHospitalizacion> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AucHospitalizaciones p "
                    + "WHERE p.id > 0 ";

            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "AND p.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            }

            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND p.aucAfiliadosId.asegAfiliadosId.id = " + paramConsulta.getParametroConsulta1() + " ";
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "estadoAuditoria":
                            strQuery += "AND p.estadoAuditoria = " + e.getValue() + " ";
                            break;
                        case "aucIngresoId.fechaIngreso":
                            strQuery += "AND p.aucIngresosId.fechaIngreso = '" + e.getValue() + "' ";
                            break;
                        case "aucAfiliadoId.maeTipoDocumentoId":
                            strQuery += "AND p.aucAfiliadoId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "aucAfiliadoId.numeroDocumento":
                            strQuery += "AND p.aucAfiliadoId.numeroDocumento = '" + e.getValue() + "' ";
                            break;
                        case "aucAfiliadoId.nombreCompleto":
                            strQuery += "AND (p.aucAfiliadoId.primerNombre LIKE '%" + e.getValue() + "' "
                                    + "OR p.aucAfiliadoId.segundoNombre LIKE '%" + e.getValue() + "' "
                                    + "OR p.aucAfiliadoId.primerApellido LIKE '%" + e.getValue() + "' "
                                    + "OR p.aucAfiliadoId.segundoApellido LIKE '%" + e.getValue() + "') ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "cntPrestadorId.razonSocial":
                            strQuery += "AND p.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntPrestadorSedeId.nombreSede":
                            strQuery += "AND p.cntPrestadorSedesId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "gnUsuariosAuditorId.nombre":
                            strQuery += "AND p.gnUsuariosAuditorId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    case "aucIngresoId.fechaIngreso":
                        strQuery += "p.aucIngresosId.fechaIngreso "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    default:
                        strQuery += "p." + paramConsulta.getOrden() + " "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                }
            } else {
                strQuery += "p.id DESC";
            }
            List<AucHospitalizaciones> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AucHospitalizaciones hospitalizacion : list) {
                listaResultados.add(castEntidadNegocioCorto(hospitalizacion));
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
    public AucHospitalizacion consultar(int id) throws Exception {
        AucHospitalizacion objRes = null;
        try {
            objRes = castEntidadNegocio((AucHospitalizaciones) getEntityManager().find(AucHospitalizaciones.class, id));
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
    public int insertar(AucHospitalizacion obj) throws Exception {
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
    public void actualizar(AucHospitalizacion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizaciones a SET ";
            strQuery += "a.gnEmpresasId.id = :gnEmpresasId ,";
            strQuery += "a.aucAfiliadosId.id = :aucAfiliadosId ,";
            strQuery += "a.aucIngresosId.id = :aucIngresosId ,";
            strQuery += "a.aucEgresosId.id = :aucEgresosId ,";
            strQuery += "a.cntPrestadoresId.id = :cntPrestadoresId ,";
            strQuery += "a.cntPrestadorSedesId.id = :cntPrestadorSedesId ,";
            strQuery += "a.codigoEvento = :codigoEvento ,";
            strQuery += "a.estado = :estado ,";
            strQuery += "a.estadoAuditoria = :estadoAuditoria ,";
            strQuery += (obj.isCierreAuditoria()) ? "a.cierreAuditoria = :cierreAuditoria ," : "";
            strQuery += (obj.isCierreAuditoria()) ? "a.usuarioCierreAuditoria = :usuarioCierreAuditoria ," : "";
            strQuery += (obj.isCierreAuditoria()) ? "a.terminalCierreAuditoria = :terminalCierreAuditoria ," : "";
            strQuery += (obj.isCierreAuditoria()) ? "a.fechaHoraCierreAuditoria = :fechaHoraCierreAuditoria ," : "";
            strQuery += "a.gnUsuariosAuditorId.id = :gnUsuariosAuditorId ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("gnEmpresasId", obj.getGnEmpresaId().getId());
            query.setParameter("aucAfiliadosId", obj.getAucAfiliadoId().getId());
            query.setParameter("aucIngresosId", obj.getAucIngresoId().getId());
            query.setParameter("aucEgresosId", obj.getAucEgresoId().getId());
            query.setParameter("cntPrestadoresId", obj.getCntPrestadorId().getId());
            query.setParameter("cntPrestadorSedesId", obj.getCntPrestadorSedeId().getId());
            query.setParameter("codigoEvento", obj.getCodigoEvento());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("estadoAuditoria", obj.getEstadoAuditoria());
            if (obj.isCierreAuditoria()) {
                query.setParameter("cierreAuditoria", obj.isCierreAuditoria());
                query.setParameter("usuarioCierreAuditoria", obj.getUsuarioCierreAuditoria());
                query.setParameter("terminalCierreAuditoria", obj.getTerminalCierreAuditoria());
                query.setParameter("fechaHoraCierreAuditoria", obj.getFechaHoraCierreAuditoria());
            }
            query.setParameter("gnUsuariosAuditorId", obj.getGnUsuariosAuditorId().getId());
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
    public void actualizaFechaInicioHospitalizacion(AucHospitalizacion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizaciones a SET ";
            strQuery += "a.fechaInicioHospitalizacion = :fechaInicioHospitalizacion ";
            strQuery += "WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("fechaInicioHospitalizacion", obj.getAucIngresoId().getFechaIngreso());
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
    public void actualizaFechaFinHospitalizacion(AucHospitalizacion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizaciones a SET ";
            strQuery += "a.fechaFinHospitalizacion = :fechaFinHospitalizacion ";
            //strQuery += "a.diasHospitalizacion = :diasHospitalizacion ";
            strQuery += "WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("fechaFinHospitalizacion", obj.getFechaFinHospitalizacion());
            //query.setParameter("diasHospitalizacion", obj.getCalcularDiasHospitalizacion());
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
    public void actualizaDiasHospitalizacion(AucHospitalizacion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizaciones a SET ";
            strQuery += "a.diasHospitalizacion = :diasHospitalizacion ";
            strQuery += "WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("diasHospitalizacion", obj.getDiasHospitalizacion());
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
    public void actualizaReveirHospitalizacion(AucHospitalizacion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizaciones a SET ";
            strQuery += "a.diasHospitalizacion = :diasHospitalizacion, ";
            strQuery += "a.fechaFinHospitalizacion = :fechaFinHospitalizacion, ";
            strQuery += "a.aucEgresosId.id = :aucEgresoId, ";
            strQuery += "a.estado = :estado, ";
            strQuery += "a.estadoAuditoria = :estadoAuditoria ";
            strQuery += "WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("diasHospitalizacion", obj.getDiasHospitalizacion());
            query.setParameter("fechaFinHospitalizacion", obj.getFechaFinHospitalizacion());
            query.setParameter("aucEgresoId", obj.getAucEgresoId().getId());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("estadoAuditoria", obj.getEstadoAuditoria());
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
    public void actualizarDiagnosticoEspecialidad(AucHospitalizacion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizaciones a SET ";
            strQuery += "a.maEspecialidadesId = :maEspecialidadesId ,";
            strQuery += "a.maEspecialidadesCodigo = :maEspecialidadesCodigo ,";
            strQuery += "a.maEspecialidadesValor = :maEspecialidadesValor ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("maEspecialidadesId", obj.getMaEspecialidadesId());
            query.setParameter("maEspecialidadesCodigo", obj.getMaEspecialidadesCodigo());
            query.setParameter("maEspecialidadesValor", obj.getMaEspecialidadesValor());
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
    public void actualizarEstadoAauditoria(AucHospitalizacion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizaciones a SET ";
            strQuery += "a.estadoAuditoria = :estadoAuditoria ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("estadoAuditoria", obj.getEstadoAuditoria());
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
    public void actualizarAplicaRescate(AucHospitalizacion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizaciones a SET ";
            strQuery += "a.aplicaRescate = :aplicaRescate";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("aplicaRescate", AucHospitalizacion.GESTION_RESCATE);
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
    public void actualizarNoAptoRescate(AucHospitalizacion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizaciones a SET ";
            strQuery += "a.aplicaRescate = :aplicaRescate";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("aplicaRescate", AucHospitalizacion.NO_APTO_RESCATE);
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
    public void actualizarEstadoAnulacion(AucHospitalizacion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizaciones a SET ";
            strQuery += "a.estado = :estado ,";
            strQuery += "a.estadoAuditoria = :estadoAuditoria ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("estadoAuditoria", obj.getEstadoAuditoria());
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
    public void actualizarEstado(AucHospitalizacion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizaciones a SET ";
            strQuery += "a.estado = :estado ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
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
    public void actualizarFechaUltimaNota(AucHospitalizacion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizaciones a SET ";
            strQuery += "a.fechaUltimaNota = :fechaUltimaNota ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("fechaUltimaNota", obj.getFechaUltimaNota());
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
    public void actualizarValorDiarioAcomulado(AucHospitalizacion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizaciones a SET ";
            strQuery += "a.valorDiarioAcumulado = :valorDiarioAcumulado ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("valorDiarioAcumulado", obj.getValorDiarioAcumulado());
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
    public AucHospitalizacion eliminar(int id) throws Exception {
        AucHospitalizacion obj = null;
        try {
            AucHospitalizaciones ent = getEntityManager().find(AucHospitalizaciones.class, id);
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
    public AucHospitalizacion consultarUltimaHospitalizacionAfiliado(Integer idAfiliado, Integer idHospitalizacion) throws Exception {
        AucHospitalizacion hospitalizacion = null;

        try {
            String strQuery = "SELECT p "
                    + "FROM AucHospitalizaciones p "
                    + "WHERE p.aucAfiliadosId.asegAfiliadosId.id = " + idAfiliado + " "
                    + "AND p.estado = 2 "
                    + ((idHospitalizacion != null) ? "AND p.id != " + idHospitalizacion + " " : "")
                    + "ORDER BY p.fechaHoraCrea DESC ";
            List<AucHospitalizaciones> obj = getEntityManager().createQuery(strQuery).setMaxResults(1).getResultList();
            for (AucHospitalizaciones aucHospitalizacion : obj) {
                hospitalizacion = castEntidadNegocio(aucHospitalizacion);
            }
        } catch (NoResultException e) {
            hospitalizacion = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return hospitalizacion;
    }

    @Override
    public int consultarHozpitalizacionActivas(Integer idAfiliado, Integer idPrestadorSede) throws Exception {
        int cant = 0;

        try {
            String strQuery = "SELECT COUNT(p.id) "
                    + "FROM AucHospitalizaciones p "
                    + "WHERE p.aucAfiliadosId.asegAfiliadosId.id = " + idAfiliado + " "
                    + "AND  p.estado != 2 "
                    + "AND p.estado != 3 "
                    + ((idPrestadorSede != null) ? "AND p.cntPrestadorSedesId.id = " + idPrestadorSede + " " : "");
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
    public int consultarHospitalizacionIpsSoloDosActivas(Integer idAfiliado) throws Exception {
        int cant = 0;

        try {
            String strQuery = "SELECT COUNT(p.cntPrestadorSedesId.id) "
                    + "FROM AucHospitalizaciones p "
                    + "WHERE p.aucAfiliadosId.asegAfiliadosId.id = " + idAfiliado + " "
                    + "AND  p.estado != 2 "
                    + "AND p.estado != 3 ";
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
    public List<AucHospitalizacion> consultarCodigoEventoHosítalizacion(int codigoEvento) throws Exception {
        List<AucHospitalizacion> obj = new ArrayList();
        try {
            String strQuery = "SELECT p "
                    + "FROM AucHospitalizaciones p "
                    + "WHERE p.codigoEvento = " + codigoEvento + " ";
            List<AucHospitalizaciones> list = getEntityManager().createQuery(strQuery).setMaxResults(1).getResultList();
            for (AucHospitalizaciones aucHospitalizacion : list) {
                obj.add(castEntidadNegocio(aucHospitalizacion));
            }
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public AucHospitalizacion consultarHospitalizacionActualAfiliado(Integer idAfiliado) throws Exception {
        AucHospitalizacion afiliadoResult = null;
        try {
            String strQuery = "SELECT p "
                    + "FROM AucHospitalizaciones p "
                    + "WHERE p.aucAfiliadosId.asegAfiliadosId.id = " + idAfiliado + " "
                    + "AND p.estado = 1 ";
            AucHospitalizaciones obj = (AucHospitalizaciones) getEntityManager().createQuery(strQuery).getSingleResult();
            afiliadoResult = castEntidadNegocioCorto(obj);
        } catch (NoResultException e) {
            afiliadoResult = null;
        } catch (Exception e) {
            afiliadoResult = null;
        } finally {
            cerrarEntityManager();
        }
        return afiliadoResult;
    }

    @Override
    public List<AucHospitalizacion> consultarCodigoEventoHosítalizacionConEstadoEgreso(int codigoEvento, int idPrestadorSede) throws Exception {
        List<AucHospitalizacion> obj = new ArrayList();
        try {
            String strQuery = "SELECT p.id, p.codigo_evento "
                    + "FROM auc_hospitalizaciones p "
                    + "WHERE p.id > 0 "
                    + "AND p.id = ( SELECT CASE WHEN (ahe.estado = 2 AND (ahe.estado_auditoria = 3 OR ahe.estado_auditoria = 2)) "
                    + "THEN ahe.auc_hospitalizaciones_id ELSE 0 END "
                    + "FROM auc_hospitalizacion_estados ahe "
                    + "INNER JOIN auc_hospitalizaciones ah ON ahe.auc_hospitalizaciones_id = ah.id "
                    + "WHERE ahe.id > 0 "
                    + "AND ah.cnt_prestador_sedes_id = " + idPrestadorSede + " "
                    + "AND ah.codigo_evento = '" + codigoEvento + "' "
                    + "AND ah.auc_egresos_id IS NOT NULL "
                    + "ORDER BY ahe.id DESC "
                    + "LIMIT 1 "
                    + ")";
            Query query = getEntityManager().createNativeQuery(strQuery);
            List<Object[]> lstObj = query.getResultList();
            obj = lstObj
                    .stream()
                    .map(result -> new AucHospitalizacion(((Integer) result[0])
            )).collect(Collectors.toList());

        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }
    
    @Override
    public Boolean consultaNoAptoRescate(int idHospitalizacion) throws Exception {
        int cant = 0;

        String strQuery = "SELECT COUNT(a) FROM AucHospitalizaciones a"
                + " WHERE a.id = :idHospitalizacion"
                + " AND a.aplicaRescate = :aplicaRescate";
        try {
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("idHospitalizacion", idHospitalizacion)
                    .setParameter("aplicaRescate", AucHospitalizacion.NO_APTO_RESCATE)
                    .getSingleResult();

        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant > 0;
    }
   
    @Override
    public boolean validarCodigoEvento(String codigoEvento) {
        boolean existe = false;
        try {
            String strQuery = "SELECT p "
                    + "FROM AucHospitalizaciones p "
                    + "WHERE p.codigoEvento = " + codigoEvento + " ";
            List<AucHospitalizaciones> list = getEntityManager().createQuery(strQuery).setMaxResults(1).getResultList();
            if (list != null) {
                existe = true;
            }
        } catch (NoResultException e) {
            existe = false;
        } catch (Exception e) {
            existe = false;
        } finally {
            cerrarEntityManager();
        }
        return existe;
    }
    
    @Override
    public int consultarAplicaRescate(Integer id) {
        int existe = 0;
        try {
            String strQuery = "SELECT p "
                    + "FROM AucHospitalizaciones p "
                    + "WHERE p.id = " + id + " ";
            List<AucHospitalizaciones> list = getEntityManager().createQuery(strQuery).setMaxResults(1).getResultList();
            if (list != null) {
                switch (list.get(0).getAplicaRescate()) {
                    case AucHospitalizacion.APLICA_RESCATE:
                        existe = 1;
                        break;
                    case AucHospitalizacion.NO_APTO_RESCATE:
                        existe = 3;
                        break;
                    case AucHospitalizacion.GESTION_RESCATE:
                        existe = 2;
                        break;
                    default:
                        break;
                }
            }
        } catch (NoResultException e) {
            existe = 0;
        } catch (Exception e) {
            existe = 0;
        } finally {
            cerrarEntityManager();
        }
        return existe;
    }
    
    @Override
    public boolean consultarNoAptoRescate(Integer id) {
        boolean existe = false;
        try {
            String strQuery = "SELECT p "
                    + "FROM AucHospitalizaciones p "
                    + "WHERE p.id = " + id + " ";
            List<AucHospitalizaciones> list = getEntityManager().createQuery(strQuery).setMaxResults(1).getResultList();
            if (list != null) {
                if(list.get(0).getAplicaRescate() == AucHospitalizacion.NO_APTO_RESCATE){
                    existe = true;
                }else{
                    existe = false;
                }
            }
        } catch (NoResultException e) {
            existe = false;
        } catch (Exception e) {
            existe = false;
        } finally {
            cerrarEntityManager();
        }
        return existe;
    }
    
    @Override
    public boolean validarActivaAfiliado(int idAfiliado, int idPrestadorSede) {
        boolean existe = false;
        try {
            String strQuery = "SELECT p FROM AucHospitalizaciones p "
                    + "INNER JOIN p.aucAfiliadosId auc "
                    + "INNER JOIN auc.asegAfiliadosId a "
                    + "WHERE a.id =:idAfiliado "
                    + "AND p.estado=:estado "
                    + "ORDER BY p.id DESC";
            List<AucHospitalizaciones> list = getEntityManager().createQuery(strQuery)
                    .setParameter("idAfiliado", idAfiliado)
                    .setParameter("estado", AucHospitalizacion.ESTADO_AFILIADO_HOSPITALIZADO)
                    .getResultList();
            if (list != null) {
                if (list.size() >= 2) {
                    existe = true;
                } else {
                    for (AucHospitalizaciones hospitalizacion : list) {
                        if (hospitalizacion.getCntPrestadorSedesId().getId() == idPrestadorSede) {
                            existe = true;
                            break;
                        }
                    }
                }
            }
        } catch (NoResultException e) {
            existe = false;
        } catch (Exception e) {
            existe = false;
        } finally {
            cerrarEntityManager();
        }
        return existe;
    }
    
    @Override
    public AucHospitalizacion consultarHospitalizacionPorAfiliadoAndPrestador(int idAfiliado, int idPrestadorSede) {
        AucHospitalizacion hospitalizacion = null;
        try {
            String strQuery = "SELECT p FROM AucHospitalizaciones p "
                    + "INNER JOIN p.aucAfiliadosId auc "
                    + "INNER JOIN auc.asegAfiliadosId a "
                    + "WHERE a.id =:idAfiliado "
                    + "AND p.estado=:estado "
                    + "ORDER BY p.id DESC";
            List<AucHospitalizaciones> list = getEntityManager().createQuery(strQuery)
                    .setParameter("idAfiliado", idAfiliado)
                    .setParameter("estado", AucHospitalizacion.ESTADO_AFILIADO_HOSPITALIZADO)
                    .getResultList();
            if (list != null) {
                for (AucHospitalizaciones hospitalizacions : list) {
                    if (hospitalizacions.getCntPrestadorSedesId().getId() == idPrestadorSede) {
                        hospitalizacion = castEntidadNegocioCorto(hospitalizacions);
                        break;
                    }
                }
               
            }
        } catch (NoResultException e) {
            hospitalizacion = null;
        } catch (Exception e) {
            hospitalizacion = null;
        } finally {
            cerrarEntityManager();
        }
        return hospitalizacion;
    }
    
    @Override
    public Integer consultarFechaIngresoSeaMenor(Integer idAfiliado, Date fechaIngreso) {
        BigInteger permiteFecha = null;
        Integer prueba = null;
        try {
            SimpleDateFormat formatterIngreso = new SimpleDateFormat("yyyy-MM-dd");
            String formatFechaIngreso = formatterIngreso.format(fechaIngreso);
            /*String strQuery = "SELECT A " 
                                + "FROM ( SELECT CASE " 
                                                    + "WHEN ah.aucEgresosId.fechaEgreso < '" + formatFechaIngreso +"' THEN 0 "
                                                    + "WHEN ah.aucEgresosId.fechaEgreso = '" + formatFechaIngreso +"' THEN 1 "
                                               + "END "
                                         + "FROM AucHospitalizaciones ah " 
                                        + "WHERE ah.aucAfiliadosId.asegAfiliadosId.id = " + idAfiliado + " ) A " 
                               + "WHERE A = 0 ";*/
            String strQuery = "SELECT A.casoA "
                    + "FROM ( SELECT CASE "
                    + "WHEN ae.fecha_egreso < '" + formatFechaIngreso + "' THEN 0 "
                    + "WHEN ae.fecha_egreso = '" + formatFechaIngreso + "' THEN 1 "
                    + "END casoA "
                    + "FROM auc_hospitalizaciones ah "
                    + "INNER JOIN auc_ingresos ai ON ah.auc_ingresos_id = ai.id "
                    + "INNER JOIN auc_egresos ae ON ah.auc_egresos_id = ae.id "
                    + "INNER JOIN auc_afiliados aa2 ON ah.auc_afiliados_id = aa2.id "
                    + "INNER JOIN aseg_afiliados aa ON aa2.aseg_afiliados_id = aa.id "
                    + "WHERE aa2.aseg_afiliados_id = " + idAfiliado + " ) A "
                    + "WHERE A.casoA = 0 "
                    + "LIMIT 1";
            permiteFecha = (BigInteger) getEntityManager().createNativeQuery(strQuery)
                    .getSingleResult();
            if (permiteFecha != null) {
                prueba = permiteFecha.intValue();
            }
        } catch (NoResultException e) {
            permiteFecha = null;
        } catch (Exception e) {
            permiteFecha = null;
        } finally {
            cerrarEntityManager();
        }
        return prueba;
    }

    @Override
    @SuppressWarnings("null")
    public List<AucHospitalizacion> consultarHospitalizacionExceptoAnuladas(Integer idAfiliado, Integer idHospitalizacion, Integer idPrestadorSede) throws Exception {
        List<AucHospitalizacion> hospitalizacion = new ArrayList();

        try {
            String strQuery = "SELECT p "
                    + "FROM AucHospitalizaciones p "
                    + "WHERE p.aucAfiliadosId.asegAfiliadosId.id = " + idAfiliado + " "
                    + "AND p.estado != 3 "
                    + ((idHospitalizacion != null) ? "AND p.id != " + idHospitalizacion + " " : "")
                    + ((idPrestadorSede != null) ? "AND p.cntPrestadorSedesId.id = " + idPrestadorSede + " " : "")
                    + "ORDER BY p.fechaHoraCrea DESC ";
            List<AucHospitalizaciones> obj = getEntityManager().createQuery(strQuery).getResultList();
            for (AucHospitalizaciones aucHospitalizacion : obj) {
                hospitalizacion.add(castEntidadNegocio(aucHospitalizacion));
            }
        } catch (NoResultException e) {
            hospitalizacion = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return hospitalizacion;
    }

    @Override
    @SuppressWarnings("null")
    public List<AucHospitalizacion> consultarHospitalizacionPorSedes(Integer idPrestadorSede) throws Exception {
        List<AucHospitalizacion> hospitalizacion = new ArrayList();

        try {
            String strQuery = "SELECT p "
                    + "FROM AucHospitalizaciones p "
                    + "WHERE p.cntPrestadorSedesId.id = " + idPrestadorSede + " "
                    + "AND p.estado = 1 "
                    + "ORDER BY p.fechaHoraCrea DESC ";
            List<AucHospitalizaciones> obj = getEntityManager().createQuery(strQuery).getResultList();
            for (AucHospitalizaciones aucHospitalizacion : obj) {
                hospitalizacion.add(castEntidadNegocio(aucHospitalizacion));
            }
        } catch (NoResultException e) {
            hospitalizacion = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return hospitalizacion;
    }

    @Override
    public List<AucHospitalizacion> consultarHospitalizacionesAbiertasCargaMasiva(String idAfiliados, int sede) throws Exception {
        List<AucHospitalizacion> listaResultados = new ArrayList();
        try {
            String strQuery = "SELECT a FROM AucHospitalizaciones a "
                    + "WHERE a.cntPrestadorSedesId.id=:sede "
                    + "AND a.estado=:estado "
                    + "AND a.aucAfiliadosId.asegAfiliadosId.id NOT IN(" + idAfiliados + ")";

            List<AucHospitalizaciones> list = getEntityManager().createQuery(strQuery)
                    .setParameter("sede", sede)
                    .setParameter("estado", AucHospitalizacion.ESTADO_AFILIADO_HOSPITALIZADO)
                    .getResultList();
            for (AucHospitalizaciones aucHospitalizacion : list) {
                listaResultados.add(castEntidadNegocio(aucHospitalizacion));
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

    private String agregarJoin(String join, String sql) {
        if (sql.contains(join)) {
            return sql;
        } else {
            return sql += join;
        }
    }

    @Override
    public AucHospitalizacion consultarHospitalizacionAfiliado(int idAfiliado, int idPrestadorSede, Date fechaIngreso) throws java.lang.Exception {
        AucHospitalizacion afiliadoResult = null;
        SimpleDateFormat std = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String strQuery = "SELECT p "
                    + "FROM AucHospitalizaciones p "
                    + " WHERE p.aucAfiliadosId.asegAfiliadosId.id = " + idAfiliado
                    + " AND p.cntPrestadorSedesId.id = " + idPrestadorSede
                    + " AND p.fechaInicioHospitalizacion = '" + std.format(fechaIngreso) + "'"
                    + " AND p.estadoAuditoria NOT IN (4,5) ";
            AucHospitalizaciones obj = (AucHospitalizaciones) getEntityManager().createQuery(strQuery).getSingleResult();
            afiliadoResult = castEntidadNegocio(obj);
        } catch (NoResultException e) {
            afiliadoResult = null;
        } catch (Exception e) {
            afiliadoResult = null;
        } finally {
            cerrarEntityManager();
        }
        return afiliadoResult;
    }
    
    private AucHospitalizacion castEntidadNegocio(AucHospitalizaciones entidad) {
        AucHospitalizacion negocio = new AucHospitalizacion();
        negocio.setId(entidad.getId());
        if (entidad.getGnEmpresasId() != null) {
            negocio.setGnEmpresaId(new Empresa(entidad.getGnEmpresasId().getId()));
        }
        if (entidad.getAucAfiliadosId() != null) {
            negocio.setAucAfiliadoId(new AucAfiliado(
                    entidad.getAucAfiliadosId().getId(),
                    entidad.getAucAfiliadosId().getMaeTipoDocumentoId(),
                    entidad.getAucAfiliadosId().getMaeTipoDocumentoCodigo(),
                    entidad.getAucAfiliadosId().getMaeTipoDocumentoValor(),
                    entidad.getAucAfiliadosId().getNumeroDocumento(),
                    entidad.getAucAfiliadosId().getPrimerApellido(),
                    entidad.getAucAfiliadosId().getSegundoApellido(),
                    entidad.getAucAfiliadosId().getPrimerNombre(),
                    entidad.getAucAfiliadosId().getSegundoNombre())
            );
        }

        if (entidad.getAucIngresosId() != null) {
            AucIngreso ingreso = new AucIngreso();
            ingreso.setId(entidad.getAucIngresosId().getId());
            ingreso.setFechaIngreso(entidad.getAucIngresosId().getFechaIngreso());
            if (!entidad.getAucIngresosId().getAucDiagnosticosList().isEmpty()) {
                ingreso.setAucDiagnosticosList(AucDiagnosticoServicio.castEntidadNegocio(entidad.getAucIngresosId().getAucDiagnosticosList()));
            }
            negocio.setAucIngresoId(ingreso);
        }

        if (entidad.getAucEgresosId() != null) {
            AucEgreso aucEgreso = new AucEgreso();
            aucEgreso.setId(entidad.getAucEgresosId().getId());
            aucEgreso.setFechaEgreso(entidad.getAucEgresosId().getFechaEgreso());
            negocio.setAucEgresoId(aucEgreso);
        }

        if (entidad.getGnUsuariosAuditorId() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(entidad.getGnUsuariosAuditorId().getId());
            usuario.setUsuario(entidad.getGnUsuariosAuditorId().getUsuario());
            usuario.setNombre(entidad.getGnUsuariosAuditorId().getNombre());
            negocio.setGnUsuariosAuditorId(usuario);
        }

        if (entidad.getCntPrestadoresId() != null) {
            negocio.setCntPrestadorId(new CntPrestador(entidad.getCntPrestadoresId().getId()));
        }

        if (entidad.getCntPrestadorSedesId() != null) {
            negocio.setCntPrestadorSedeId(new CntPrestadorSede(entidad.getCntPrestadorSedesId().getId()));
        }

        negocio.setCodigoEvento(entidad.getCodigoEvento());
        negocio.setEstado(entidad.getEstado());
        negocio.setEstadoAuditoria(entidad.getEstadoAuditoria());
        negocio.setCierreAuditoria(entidad.getCierreAuditoria());
        negocio.setUsuarioCierreAuditoria(entidad.getUsuarioCierreAuditoria());
        negocio.setTerminalCierreAuditoria(entidad.getTerminalCierreAuditoria());
        negocio.setFechaHoraCierreAuditoria(entidad.getFechaHoraCierreAuditoria());
        negocio.setFechaInicioHospitalizacion(entidad.getFechaInicioHospitalizacion());
        negocio.setFechaFinHospitalizacion(entidad.getFechaFinHospitalizacion());
        negocio.setDiasHospitalizacion(entidad.getDiasHospitalizacion());
        negocio.setFechaUltimaNota(entidad.getFechaUltimaNota());
        negocio.setMaEspecialidadesId(entidad.getMaEspecialidadesId());
        negocio.setMaEspecialidadesCodigo(entidad.getMaEspecialidadesCodigo());
        negocio.setMaEspecialidadesValor(entidad.getMaEspecialidadesValor());
        negocio.setAplicaRescate(entidad.getAplicaRescate());
        negocio.setValorDiarioAcumulado(entidad.getValorDiarioAcumulado());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }

    private AucHospitalizacion castEntidadNegocioCorto(AucHospitalizaciones entidad) {
        AucHospitalizacion negocio = new AucHospitalizacion();
        negocio.setId(entidad.getId());
        if(entidad.getAucAfiliadosId() != null){
            AucAfiliado afiliado = new AucAfiliado();
            afiliado.setId(entidad.getAucAfiliadosId().getId());
            afiliado.setMaeTipoDocumentoId(entidad.getAucAfiliadosId().getMaeTipoDocumentoId());
            afiliado.setMaeTipoDocumentoCodigo(entidad.getAucAfiliadosId().getMaeTipoDocumentoCodigo());
            afiliado.setMaeTipoDocumentoValor(entidad.getAucAfiliadosId().getMaeTipoDocumentoValor());
            afiliado.setNumeroDocumento(entidad.getAucAfiliadosId().getNumeroDocumento());
            afiliado.setPrimerNombre(entidad.getAucAfiliadosId().getPrimerNombre());
            afiliado.setSegundoNombre(entidad.getAucAfiliadosId().getSegundoNombre());
            afiliado.setPrimerApellido(entidad.getAucAfiliadosId().getPrimerApellido());
            afiliado.setSegundoApellido(entidad.getAucAfiliadosId().getSegundoApellido());
            afiliado.setUbicacionAfiliacionId(entidad.getAucAfiliadosId().getUbicacionAfiliacionId());
            negocio.setAucAfiliadoId(afiliado);
            
        }
       
        if(entidad.getAucIngresosId() != null){
            AucIngreso ingreso = new AucIngreso();
            ingreso.setFechaIngreso(entidad.getAucIngresosId().getFechaIngreso());
            ingreso.setMaeAltaTempranaId(entidad.getAucIngresosId().getMaeAltaTempranaId());
            ingreso.setMaeAltaTempranaValor(entidad.getAucIngresosId().getMaeAltaTempranaValor());
            negocio.setAucIngresoId(ingreso);
        }
       
        if (entidad.getAucEgresosId() != null) {
            negocio.setAucEgresoId(new AucEgreso(entidad.getAucEgresosId().getFechaEgreso()));
        }
        negocio.setGnUsuariosAuditorId(new Usuario(entidad.getGnUsuariosAuditorId().getId(),
                entidad.getGnUsuariosAuditorId().getUsuario(),
                entidad.getGnUsuariosAuditorId().getNombre()
        ));
        negocio.setCntPrestadorId(new CntPrestador(entidad.getCntPrestadoresId().getId(),
                entidad.getCntPrestadoresId().getRazonSocial()
        ));
        negocio.setCntPrestadorSedeId(new CntPrestadorSede(entidad.getCntPrestadorSedesId().getId()));
        negocio.getCntPrestadorSedeId().setNombreSede(entidad.getCntPrestadorSedesId().getNombre());
        negocio.setEstado(entidad.getEstado());
        negocio.setEstadoAuditoria(entidad.getEstadoAuditoria());
        negocio.setCierreAuditoria(entidad.getCierreAuditoria());
        negocio.setUsuarioCierreAuditoria(entidad.getUsuarioCierreAuditoria());
        negocio.setTerminalCierreAuditoria(entidad.getTerminalCierreAuditoria());
        negocio.setFechaHoraCierreAuditoria(entidad.getFechaHoraCierreAuditoria());
        negocio.setFechaInicioHospitalizacion(entidad.getFechaInicioHospitalizacion());
        negocio.setFechaFinHospitalizacion(entidad.getFechaFinHospitalizacion());
        negocio.setDiasHospitalizacion(entidad.getDiasHospitalizacion());
        negocio.setFechaUltimaNota(entidad.getFechaUltimaNota());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }

    private AucHospitalizaciones castNegocioEntidad(AucHospitalizacion negocio) {
        AucHospitalizaciones entidad = new AucHospitalizaciones();
        if (negocio.getGnEmpresaId() != null) {
            entidad.setGnEmpresasId(new GnEmpresas(negocio.getGnEmpresaId().getId()));
        }
        if (negocio.getAucAfiliadoId() != null) {
            entidad.setAucAfiliadosId(new AucAfiliados(negocio.getAucAfiliadoId().getId()));
        }
        if (negocio.getAucIngresoId() != null) {
            entidad.setAucIngresosId(new AucIngresos(negocio.getAucIngresoId().getId()));
        }
        if (negocio.getAucEgresoId() != null && negocio.getAucEgresoId().getId() != null) {
            entidad.setAucEgresosId(new AucEgresos(negocio.getAucEgresoId().getId()));
        }
        if (negocio.getGnUsuariosAuditorId() != null) {
            entidad.setGnUsuariosAuditorId(new GnUsuarios(negocio.getGnUsuariosAuditorId().getId()));
        }
        if (negocio.getCntPrestadorId() != null) {
            entidad.setCntPrestadoresId(new CntPrestadores(negocio.getCntPrestadorId().getId()));
        }
        if (negocio.getCntPrestadorSedeId() != null) {
            entidad.setCntPrestadorSedesId(new CntPrestadorSedes(negocio.getCntPrestadorSedeId().getId()));
        }
        entidad.setCodigoEvento(negocio.getCodigoEvento());
        entidad.setEstado(negocio.getEstado());
        entidad.setEstadoAuditoria(negocio.getEstadoAuditoria());
        entidad.setCierreAuditoria(negocio.isCierreAuditoria());
        entidad.setFechaInicioHospitalizacion(negocio.getFechaInicioHospitalizacion());
        entidad.setDiasHospitalizacion(negocio.getDiasHospitalizacion());
        entidad.setFechaUltimaNota(negocio.getFechaUltimaNota());
        entidad.setAplicaRescate(negocio.getAplicaRescate());
        entidad.setValorDiarioAcumulado(negocio.getValorDiarioAcumulado());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

}
