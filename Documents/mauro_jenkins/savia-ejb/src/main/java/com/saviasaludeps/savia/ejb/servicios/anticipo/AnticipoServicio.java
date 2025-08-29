/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.anticipo;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipo;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AntAnticipos;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.entidades.GnMaestros;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.anticipo.AnticipoRemoto;
import java.util.ArrayList;
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
@Remote(AnticipoRemoto.class)
public class AnticipoServicio extends GenericoServicio implements AnticipoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(u) FROM AntAnticipos u ";
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
                        case "tipo":
                            strQuery.append("AND u.tipo = ").append(e.getValue()).append(" ");
                            break;
                        case "estado":
                            strQuery.append("AND u.estado = ").append(e.getValue()).append(" ");
                            break;
                        case "maeClasificacionId":
                            strTitulo = agregarJoin("INNER JOIN GnMaestros maes ON u.maeClasificacionId = maes.id ", strTitulo);
                            strQuery.append("AND maes.id = ").append(e.getValue()).append(" ");
                            break;
                        case "cntPrestadoresId.razonSocial":
                            strTitulo = agregarJoin("INNER JOIN CntPrestadores cnp ON u.cntPrestadoresId = cnp.id ", strTitulo);
                            strQuery.append("AND cnp.razonSocial LIKE '%").append(e.getValue()).append("%' ");
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
    public List<AntAnticipo> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AntAnticipo> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AntAnticipos u ";
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
                        case "tipo":
                            strQuery.append("AND u.tipo = ").append(e.getValue()).append(" ");
                            break;
                        case "estado":
                            strQuery.append("AND u.estado = ").append(e.getValue()).append(" ");
                            break;
                        case "maeClasificacionId":
                            strTitulo = agregarJoin("INNER JOIN GnMaestros maes ON u.maeClasificacionId = maes.id ", strTitulo);
                            strQuery.append("AND maes.id = ").append(e.getValue()).append(" ");
                            break;
                        case "cntPrestadoresId.razonSocial":
                            strTitulo = agregarJoin("INNER JOIN CntPrestadores cnp ON u.cntPrestadoresId = cnp.id ", strTitulo);
                            strQuery.append("AND cnp.razonSocial LIKE '%").append(e.getValue()).append("%' ");
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
            List<AntAnticipos> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AntAnticipos per : list) {
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
    
    @Override
    public int consultarCantidadContizacionesLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(u) FROM AntAnticipos u ";
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
                        case "tipo":
                            strQuery.append("AND u.tipo = ").append(e.getValue()).append(" ");
                            break;
                        case "estado":
                            strQuery.append("AND u.estado = ").append(e.getValue()).append(" ");
                            break;
                        case "maeClasificacionId":
                            strTitulo = agregarJoin("INNER JOIN GnMaestros maes ON u.maeClasificacionId = maes.id ", strTitulo);
                            strQuery.append("AND maes.id = ").append(e.getValue()).append(" ");
                            break;
                        case "cntPrestadoresId.razonSocial":
                            strTitulo = agregarJoin("INNER JOIN CntPrestadores cnp ON u.cntPrestadoresId = cnp.id ", strTitulo);
                            strQuery.append("AND cnp.razonSocial LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "idTecnologia":
                            strTitulo = agregarJoin("INNER JOIN AntAnticipoItems anti ON u.id = anti.antAnticiposId ", strTitulo);
                            strQuery.append("AND anti.maTecnologiaId = ").append(e.getValue()).append(" ");
                            break;
                        case "borrado":
                            strTitulo = agregarJoin("INNER JOIN AntAnticipoItems anti ON u.id = anti.antAnticiposId ", strTitulo);
                            strQuery.append("AND anti.borrado = ").append(e.getValue()).append(" ");
                            break;
                    }
                }
            }
            strQuery.append("AND u.valorDisponible > 0 ");
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
    public List<AntAnticipo> consultarContizacionesLista(ParamConsulta paramConsulta) throws Exception {
        List<AntAnticipo> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AntAnticipos u ";
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
                        case "tipo":
                            strQuery.append("AND u.tipo = ").append(e.getValue()).append(" ");
                            break;
                        case "estado":
                            strQuery.append("AND u.estado = ").append(e.getValue()).append(" ");
                            break;
                        case "maeClasificacionId":
                            strTitulo = agregarJoin("INNER JOIN GnMaestros maes ON u.maeClasificacionId = maes.id ", strTitulo);
                            strQuery.append("AND maes.id = ").append(e.getValue()).append(" ");
                            break;
                        case "cntPrestadoresId.razonSocial":
                            strTitulo = agregarJoin("INNER JOIN CntPrestadores cnp ON u.cntPrestadoresId = cnp.id ", strTitulo);
                            strQuery.append("AND cnp.razonSocial LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "idTecnologia":
                            strTitulo = agregarJoin("INNER JOIN AntAnticipoItems anti ON u.id = anti.antAnticiposId ", strTitulo);
                            strQuery.append("AND anti.maTecnologiaId = ").append(e.getValue()).append(" ");
                            break;
                        case "borrado":
                            strTitulo = agregarJoin("INNER JOIN AntAnticipoItems anti ON u.id = anti.antAnticiposId ", strTitulo);
                            strQuery.append("AND anti.borrado = ").append(e.getValue()).append(" ");
                            break;
                    }
                }
            }
            strQuery.append("AND u.valorDisponible > 0 ");
            sql.append(strTitulo).append(strQuery);
            sql.append("ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                sql.append("u.").append(paramConsulta.getOrden()).append((paramConsulta.isAscendente() ? " ASC" : " DESC"));
            } else {
                sql.append("u.id DESC");
            }
            List<AntAnticipos> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AntAnticipos per : list) {
                listResult.add(castEntidadNegocioCortoConcotizaciones(per));
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
    public AntAnticipo consultar(int id) throws Exception {
        AntAnticipo objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AntAnticipos) getEntityManager().find(AntAnticipos.class, id));
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
    public int insertar(AntAnticipo obj) throws Exception {
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
    public void actualizar(AntAnticipo obj) throws Exception {
        try {
            String hql = "UPDATE AntAnticipos SET "
                    + "estado = :estado, "
                    + "tipo = :tipo, "
                    + "pbs = :pbs, "
                    + "maeClasificacionId = :maeClasificacionId, "
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
            query.setParameter("maeClasificacionId", obj.getMaeClasificacionId());
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
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarAfiliado(AntAnticipo obj) throws Exception {
        try {
            String hql = "UPDATE AntAnticipos SET "
                    + "asegAfiliadosId.id = :asegAfiliadosId "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("asegAfiliadosId", obj.getAsegAfiliadosId().getId());
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
    public void actualizarPrestador(AntAnticipo obj) throws Exception {
        try {
            String hql = "UPDATE AntAnticipos SET "
                    + "cntPrestadorSedesId.id = :cntPrestadorSedesId, "
                    + "cntPrestadoresId.id = :cntPrestadoresId "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("cntPrestadorSedesId", obj.getCntPrestadorSedesId().getId());
            query.setParameter("cntPrestadoresId", obj.getCntPrestadoresId().getId());
 
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
    public void actualizarPago(AntAnticipo obj) throws Exception {
        try {
            String hql = "UPDATE AntAnticipos SET "
                    + "codigoCompensacionSap = :codigoCompensacionSap, "
                    + "codigoContabilizacionSap = :codigoContabilizacionSap, "
                    + "valorPagado = :valorPagado, "
                    + "retencionAplicada = :retencionAplicada, "
                    + "valorDisponible = :valorDisponible "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("codigoCompensacionSap", obj.getCodigoCompensacionSap());
            query.setParameter("codigoContabilizacionSap", obj.getCodigoContabilizacionSap());
            query.setParameter("valorPagado", obj.getValorPagado());
            query.setParameter("retencionAplicada", obj.getRetencionAplicada());
            query.setParameter("valorDisponible", obj.getValorDisponible());
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
    public void actualizarValorDisponible(AntAnticipo obj) throws Exception {
        try {
            String hql = "UPDATE AntAnticipos SET "
                    + "valorDisponible = :valorDisponible, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("valorDisponible", obj.getValorDisponible());
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
    public void actualizarConCotizacion(AntAnticipo obj) throws Exception {
        try {
            String hql = "UPDATE AntAnticipos SET "
                    + "estado = :estado, "
                    + "valorCotizado = :valorCotizado, "
                    + "retencionSugerida = :retencionSugerida, "
                    + "aplicaRetencion = :aplicaRetencion, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("valorCotizado", obj.getValorCotizado());
            query.setParameter("retencionSugerida", obj.getRetencionSugerida());
            query.setParameter("aplicaRetencion", obj.isAplicaRetencion());
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
    public void actualizarEstadoProceso(AntAnticipo obj) throws Exception {
        try {
            String hql = "UPDATE AntAnticipos SET "
                    + "estado = :estado, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", obj.getEstado());
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
    public AntAnticipo consultarAnticipoAfiliadoYTecnologia(int idAfiliado, int idTecnologia) throws Exception {
        AntAnticipo ObjectResult = null;
        String sql = "SELECT ant FROM AntAnticipos ant "
                + "INNER JOIN AsegAfiliados aseg ON ant.asegAfiliadosId = aseg.id "
                + "INNER JOIN AntAnticipoItems anti ON ant.id = anti.antAnticiposId "
                + "WHERE aseg.id = :idAfiliado "
                + "AND anti.maTecnologiaId = :idTecnologia "
                + "AND ant.estado = :estado "
                + "AND anti.borrado = :borrado "
                + "AND ant.valorDisponible > 0 "
                + "AND ant.tipo = 1 "
                + "ORDER BY ant.id DESC";
        try {
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("idAfiliado", idAfiliado);
            query.setParameter("idTecnologia", idTecnologia);
            query.setParameter("estado", AntAnticipo.ESTADO_PAGADO);
            query.setParameter("borrado", Boolean.FALSE);
            List<AntAnticipos> list = query.setMaxResults(1).getResultList();
            for (AntAnticipos item : list) {
                ObjectResult = castEntidadNegocioLargo(item);
            }
        } catch (NoResultException e) {
            ObjectResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return ObjectResult;
    }
    
    @Override
    public List<AntAnticipo> consultarAnticipoAfiliadoYTecnologiaList(int idAfiliado, int idTecnologia) throws Exception {
        List<AntAnticipo> ObjectResult = new ArrayList<>();
        String sql = "SELECT ant FROM AntAnticipos ant "
                + "INNER JOIN AsegAfiliados aseg ON ant.asegAfiliadosId = aseg.id "
                + "INNER JOIN AntAnticipoItems anti ON ant.id = anti.antAnticiposId "
                + "WHERE aseg.id = :idAfiliado "
                + "AND anti.maTecnologiaId = :idTecnologia "
                + "AND ant.estado = :estado "
                + "AND anti.borrado = :borrado "
                + "AND ant.valorDisponible > 0 "
                + "ORDER BY ant.id DESC";
        try {
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("idAfiliado", idAfiliado);
            query.setParameter("idTecnologia", idTecnologia);
            query.setParameter("estado", AntAnticipo.ESTADO_PAGADO);
            query.setParameter("borrado", Boolean.FALSE);
            List<AntAnticipos> list = query.getResultList();
            for (AntAnticipos item : list) {
                ObjectResult.add(castEntidadNegocioLargo(item));
            }
        } catch (NoResultException e) {
            ObjectResult = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return ObjectResult;
    }
    
    @Override
    public AntAnticipo consultarAnticipoByTecnologia(int idTecnologia) throws Exception {
        AntAnticipo ObjectResult = null;
        String sql = "SELECT ant FROM AntAnticipos ant "
                + "INNER JOIN AntAnticipoItems anti ON ant.id = anti.antAnticiposId "
                + "WHERE ant.id > 0 "
                + "AND anti.maTecnologiaId = :idTecnologia "
                + "AND ant.estado = :estado "
                + "AND anti.borrado = :borrado "
                + "AND ant.valorDisponible > 0 "
                + "AND ant.tipo = 2 "
                + "ORDER BY ant.id DESC";
        try {
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("idTecnologia", idTecnologia);
            query.setParameter("estado", AntAnticipo.ESTADO_PAGADO);
            query.setParameter("borrado", Boolean.FALSE);
            List<AntAnticipos> list = query.setMaxResults(1).getResultList();
            for (AntAnticipos item : list) {
                ObjectResult = castEntidadNegocioLargo(item);
            }
        } catch (NoResultException e) {
            ObjectResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return ObjectResult;
    }
    
    @Override
    public List<AntAnticipo> consultarAnticipoByTecnologiaList(int idTecnologia) throws Exception {
        List<AntAnticipo> ObjectResult = new ArrayList<>();
        String sql = "SELECT ant FROM AntAnticipos ant "
                + "INNER JOIN AntAnticipoItems anti ON ant.id = anti.antAnticiposId "
                + "WHERE ant.id > 0 "
                + "AND anti.maTecnologiaId = :idTecnologia "
                + "AND ant.estado = :estado "
                + "AND anti.borrado = :borrado "
                + "AND ant.valorDisponible > 0 "
                + "ORDER BY ant.id DESC";
        try {
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("idTecnologia", idTecnologia);
            query.setParameter("estado", AntAnticipo.ESTADO_PAGADO);
            query.setParameter("borrado", Boolean.FALSE);
            List<AntAnticipos> list = query.getResultList();
            for (AntAnticipos item : list) {
                ObjectResult.add(castEntidadNegocioLargo(item));
            }
        } catch (NoResultException e) {
            ObjectResult = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return ObjectResult;
    }
    
    public static AntAnticipo castEntidadNegocioCorto(AntAnticipos ent) {
        AntAnticipo obj = new AntAnticipo();
        obj.setId(ent.getId());
        obj.setTipo(ent.getTipo());
        obj.setEstado(ent.getEstado());
        obj.setMaeClasificacionId(ent.getMaeClasificacionId());
        obj.setMaeClasificacionValor(ent.getMaeClasificacionValor());
        if(ent.getCntPrestadoresId() != null){
            CntPrestador prestador = new CntPrestador();
            prestador.setRazonSocial(ent.getCntPrestadoresId().getRazonSocial());
            obj.setCntPrestadoresId(prestador);
        }
        obj.setValorCotizado(ent.getValorCotizado());
        obj.setValorPagado(ent.getValorPagado());
        obj.setValorDisponible(ent.getValorDisponible());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }

    public static AntAnticipo castEntidadNegocioLargo(AntAnticipos ent) {
        AntAnticipo obj = new AntAnticipo();
        obj.setId(ent.getId());
        if(ent.getGnEmpresasId() != null){
            Empresa empresa = new Empresa();
            empresa.setId(ent.getGnEmpresasId().getId());
            obj.setGnEmpresasId(empresa);
        }
        if(ent.getCntPrestadoresId() != null){
            CntPrestador prestador = new CntPrestador();
            prestador.setId(ent.getCntPrestadoresId().getId());
            obj.setCntPrestadoresId(prestador);
        }
        if(ent.getCntPrestadorSedesId() != null){
            CntPrestadorSede prestadorSede = new CntPrestadorSede();
            prestadorSede.setId(ent.getCntPrestadorSedesId().getId());
            obj.setCntPrestadorSedesId(prestadorSede);
        }
        if(ent.getAsegAfiliadosId() != null){
            AsegAfiliado afiliado = new AsegAfiliado();
            afiliado.setId(ent.getAsegAfiliadosId().getId());
            obj.setAsegAfiliadosId(afiliado);
        }
        if(ent.getGnUsuariosId() != null){
            Usuario usuario = new Usuario();
            usuario.setId(ent.getGnUsuariosId().getId());
            obj.setGnUsuariosId(usuario);
        }
        obj.setEstado(ent.getEstado());
        obj.setTipo(ent.getTipo());
        obj.setPbs(ent.getPbs());
        obj.setMaeClasificacionId(ent.getMaeClasificacionId());
        obj.setMaeClasificacionCodigo(ent.getMaeClasificacionCodigo());
        obj.setMaeClasificacionValor(ent.getMaeClasificacionValor());
        obj.setMaeClasificacionTipo(ent.getMaeClasificacionTipo());
        obj.setJustificacion(ent.getJustificacion());
        obj.setValorCotizado(ent.getValorCotizado());
        obj.setValorPagado(ent.getValorPagado());
        obj.setValorDisponible(ent.getValorDisponible());
        obj.setClasificacionObservacion(ent.getClasificacionObservacion());
        obj.setCodigoCompensacionSap(ent.getCodigoCompensacionSap());
        obj.setCodigoContabilizacionSap(ent.getCodigoContabilizacionSap());
        obj.setMaDiagnosticoId(ent.getMaDiagnosticoId());
        obj.setMaDiagnosticoCodigo(ent.getMaDiagnosticoCodigo());
        obj.setMaDiagnosticoValor(ent.getMaDiagnosticoValor());
        obj.setRetencionSugerida(ent.getRetencionSugerida());
        obj.setRetencionAplicada(ent.getRetencionAplicada());
        obj.setAplicaRetencion(ent.getAplicaRetencion());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        return obj;
    }
    
    public static AntAnticipo castEntidadNegocioCortoConcotizaciones(AntAnticipos ent) {
        AntAnticipo obj = new AntAnticipo();
        obj.setId(ent.getId());
        obj.setTipo(ent.getTipo());
        obj.setEstado(ent.getEstado());
        obj.setMaeClasificacionId(ent.getMaeClasificacionId());
        obj.setMaeClasificacionValor(ent.getMaeClasificacionValor());
        if(ent.getAsegAfiliadosId() != null){
            AsegAfiliado afiliado = new AsegAfiliado();
            afiliado.setId(ent.getAsegAfiliadosId().getId());
            obj.setAsegAfiliadosId(afiliado);
        }
        if(ent.getCntPrestadoresId() != null){
            CntPrestador prestador = new CntPrestador();
            prestador.setRazonSocial(ent.getCntPrestadoresId().getRazonSocial());
            obj.setCntPrestadoresId(prestador);
        }
        if(ent.getCntPrestadorSedesId() != null){
            CntPrestadorSede prestadorSede = new CntPrestadorSede();
            prestadorSede.setId(ent.getCntPrestadorSedesId().getId());
            prestadorSede.setNombreSede(ent.getCntPrestadorSedesId().getNombre());
            CntPrestador prestador = new CntPrestador();
            prestador.setId(ent.getCntPrestadorSedesId().getCntPrestadoresId().getId());
            prestador.setRazonSocial(ent.getCntPrestadorSedesId().getCntPrestadoresId().getRazonSocial());
            prestadorSede.setCntPrestador(prestador);
            obj.setCntPrestadorSedesId(prestadorSede);
        }
        obj.setValorCotizado(ent.getValorCotizado());
        obj.setValorPagado(ent.getValorPagado());
        obj.setValorDisponible(ent.getValorDisponible());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }
    
    public static AntAnticipos castNegocioEntidad(AntAnticipo obj) {
        AntAnticipos ent = new AntAnticipos();
        ent.setId(obj.getId());
        if(obj.getGnEmpresasId() != null){
            GnEmpresas empresa = new GnEmpresas();
            empresa.setId(obj.getGnEmpresasId().getId());
            ent.setGnEmpresasId(empresa);
        }
        if(obj.getCntPrestadoresId() != null){
            CntPrestadores prestador = new CntPrestadores();
            prestador.setId(obj.getCntPrestadoresId().getId());
            ent.setCntPrestadoresId(prestador);
        }
        if(obj.getCntPrestadorSedesId() != null){
            CntPrestadorSedes prestadorSede = new CntPrestadorSedes();
            prestadorSede.setId(obj.getCntPrestadorSedesId().getId());
            ent.setCntPrestadorSedesId(prestadorSede);
        }
        if(obj.getAsegAfiliadosId() != null){
            AsegAfiliados afiliado = new AsegAfiliados();
            afiliado.setId(obj.getAsegAfiliadosId().getId());
            ent.setAsegAfiliadosId(afiliado);
        }
        if(obj.getGnUsuariosId() != null){
            GnUsuarios usuario = new GnUsuarios();
            usuario.setId(obj.getGnUsuariosId().getId());
            ent.setGnUsuariosId(usuario);
        }
        ent.setEstado(obj.getEstado());
        ent.setTipo(obj.getTipo());
        ent.setPbs(obj.isPbs());
        ent.setMaeClasificacionId(obj.getMaeClasificacionId());
        ent.setMaeClasificacionCodigo(obj.getMaeClasificacionCodigo());
        ent.setMaeClasificacionValor(obj.getMaeClasificacionValor());
        ent.setMaeClasificacionTipo(obj.getMaeClasificacionTipo());
        ent.setJustificacion(obj.getJustificacion());
        ent.setValorCotizado(obj.getValorCotizado());
        ent.setValorPagado(obj.getValorPagado());
        ent.setValorDisponible(obj.getValorDisponible());
        ent.setRetencionSugerida(obj.getRetencionSugerida());
        ent.setClasificacionObservacion(obj.getClasificacionObservacion());
        ent.setCodigoCompensacionSap(obj.getCodigoCompensacionSap());
        ent.setCodigoContabilizacionSap(obj.getCodigoContabilizacionSap());
        ent.setMaDiagnosticoId(obj.getMaDiagnosticoId());
        ent.setMaDiagnosticoCodigo(obj.getMaDiagnosticoCodigo());
        ent.setMaDiagnosticoValor(obj.getMaDiagnosticoValor());
        ent.setAplicaRetencion(obj.isAplicaRetencion());
        //Auditoria
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }
}
