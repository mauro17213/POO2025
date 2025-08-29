/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.anticipo;

import com.saviasaludeps.savia.dominio.anticipo.AntAnticipo;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoItem;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AntAnticipoItems;
import com.saviasaludeps.savia.ejb.entidades.AntAnticipos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.anticipo.AnticipoItemRemoto;
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
@Remote(AnticipoItemRemoto.class)
public class AnticipoItemServicio extends GenericoServicio implements AnticipoItemRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(u) FROM AntAnticipoItems u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            /*if (paramConsulta.getEmpresaId() != null) {
                strTitulo = agregarJoin("INNER JOIN GnEmpresas gne ON u.gnEmpresasId = gne.id ", strTitulo);
                strQuery.append("AND gne.id = ").append(paramConsulta.getEmpresaId()).append(" ");
            }*/        
                    
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
                        case "antAnticiposId.id":
                            strTitulo = agregarJoin("INNER JOIN AntAnticipos ant ON u.antAnticiposId = ant.id ", strTitulo);
                            strQuery.append("AND ant.id = ").append(e.getValue()).append(" ");
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
    public List<AntAnticipoItem> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AntAnticipoItem> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AntAnticipoItems u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            /*if (paramConsulta.getEmpresaId() != null) {
                strTitulo = agregarJoin("INNER JOIN GnEmpresas gne ON u.gnEmpresasId = gne.id ", strTitulo);
                strQuery.append("AND gne.id = ").append(paramConsulta.getEmpresaId()).append(" ");
            }*/   
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
                        case "antAnticiposId.id":
                            strTitulo = agregarJoin("INNER JOIN AntAnticipos ant ON u.antAnticiposId = ant.id ", strTitulo);
                            strQuery.append("AND ant.id = ").append(e.getValue()).append(" ");
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
            List<AntAnticipoItems> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AntAnticipoItems per : list) {
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
    public List<AntAnticipoItem> consultarItemPorAnticipoId(int idAnticipo) throws Exception {
        List<AntAnticipoItem> obj = new ArrayList();
        try {
            String strQuery = "SELECT p "
                    + "FROM AntAnticipoItems p "
                    + "WHERE p.id > 0 "
                    + "AND p.antAnticiposId.id = " + idAnticipo + " "
                    + "AND p.borrado = 0 "
                    + "ORDER BY p.id DESC";
            List<AntAnticipoItems> list = getEntityManager().createQuery(strQuery).getResultList();
            for (AntAnticipoItems gestion : list) {
                obj.add(castEntidadNegocioLargo(gestion));
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
    public AntAnticipoItem consultarItemAnticipoYTecnologia(int idAnticipo, int idTecnologia) throws Exception {
        AntAnticipoItem ObjectResult = null;
        String sql = "SELECT itm FROM AntAnticipoItems itm "
                + "INNER JOIN AntAnticipos ant ON itm.antAnticiposId = ant.id "
                + "WHERE ant.id = :idAnticipo "
                + "AND itm.maTecnologiaId = :idTecnologia " 
                + "AND itm.borrado = :borrado "
                + "ORDER BY itm.id DESC";
        try {
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("idAnticipo", idAnticipo);
            query.setParameter("idTecnologia", idTecnologia);
            query.setParameter("borrado", Boolean.FALSE);
            List<AntAnticipoItems> list = query.setMaxResults(1).getResultList();
            for (AntAnticipoItems item : list) {
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
    public AntAnticipoItem consultar(int id) throws Exception {
        AntAnticipoItem objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AntAnticipoItems) getEntityManager().find(AntAnticipoItems.class, id));
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
    public int insertar(AntAnticipoItem obj) throws Exception {
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
    public void actualizar(AntAnticipoItem obj) throws Exception {
        try {
            String hql = "UPDATE AntAnticipoItems SET "
                    + "tipoTecnologia = :tipoTecnologia, "
                    + "maTecnologiaId = :maTecnologiaId, "
                    + "maTecnologiaCodigo = :maTecnologiaCodigo, "
                    + "maTecnologiaValor = :maTecnologiaValor, "
                    + "maMedicamentoId = :maMedicamentoId, "
                    + "maMedicamentoCodigo = :maMedicamentoCodigo, "
                    + "maMedicamentoValor = :maMedicamentoValor, "
                    + "valorTecnologiaCotizada = :valorTecnologiaCotizada, "
                    + "cantidad = :cantidad "
                    //+ "usuarioModifica = :usuarioModifica, "
                    //+ "terminalModifica = :terminalModifica, "
                    //+ "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("tipoTecnologia", obj.getTipoTecnologia());
            query.setParameter("maTecnologiaId", obj.getMaTecnologiaId());
            query.setParameter("maTecnologiaCodigo", obj.getMaTecnologiaCodigo());
            query.setParameter("maTecnologiaValor", obj.getMaTecnologiaValor());
            query.setParameter("maMedicamentoId", obj.getMaMedicamentoId());
            query.setParameter("maMedicamentoCodigo", obj.getMaMedicamentoCodigo()); 
            query.setParameter("maMedicamentoValor", obj.getMaMedicamentoValor());
            query.setParameter("valorTecnologiaCotizada", obj.getValorTecnologiaCotizada());
            query.setParameter("cantidad", obj.getCantidad());
            //auditoria
            //query.setParameter("fechaHoraModifica", obj.getUsuarioModifica());
            //query.setParameter("terminalModifica", obj.getTerminalModifica());
            //query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
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
    public void actualizarBorradoLogico(AntAnticipoItem obj) throws Exception {
        try {
            String hql = "UPDATE AntAnticipoItems SET "
                    + "borrado = :borrado, "
                    + "borradoObservacion = :borradoObservacion, "
                    + "usuarioBorra = :usuarioBorra, "
                    + "terminalBorra = :terminalBorra, "
                    + "fechaHoraBorra = :fechaHoraBorra "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("borrado", obj.isBorrado());
            query.setParameter("borradoObservacion", obj.getBorradoObservacion());
            query.setParameter("usuarioBorra", obj.getUsuarioBorra());
            query.setParameter("terminalBorra", obj.getTerminalBorra());
            query.setParameter("fechaHoraBorra", obj.getFechaHoraBorra());
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
    public void actualizarValorTecnologiaPagada(AntAnticipoItem obj) throws Exception {
        try {
            String hql = "UPDATE AntAnticipoItems SET "
                    + "valorTecnologiaPagada = :valorTecnologiaPagada "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("valorTecnologiaPagada", obj.getValorTecnologiaPagada());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    public static AntAnticipoItem castEntidadNegocioCorto(AntAnticipoItems ent) {
        AntAnticipoItem obj = new AntAnticipoItem();
        obj.setId(ent.getId());
        if(ent.getAntAnticiposId() != null){
            obj.setAntAnticiposId(new AntAnticipo(ent.getAntAnticiposId().getId()));
        }
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setMaTecnologiaId(ent.getMaTecnologiaId());
        obj.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        obj.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        obj.setMaMedicamentoId(ent.getMaMedicamentoId());
        obj.setMaMedicamentoCodigo(ent.getMaMedicamentoCodigo());
        obj.setMaMedicamentoValor(ent.getMaMedicamentoValor());
        obj.setValorTecnologiaCotizada(ent.getValorTecnologiaCotizada());
        obj.setValorTecnologiaPagada(ent.getValorTecnologiaPagada());
        obj.setMpNumeroPrescripcion(ent.getMpNumeroPrescripcion());
        obj.setBorrado(ent.getBorrado());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }

    public static AntAnticipoItem castEntidadNegocioLargo(AntAnticipoItems ent) {
        AntAnticipoItem obj = new AntAnticipoItem();
        obj.setId(ent.getId());
        if(ent.getAntAnticiposId() != null){
            obj.setAntAnticiposId(new AntAnticipo(ent.getAntAnticiposId().getId()));
        }
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setMaTecnologiaId(ent.getMaTecnologiaId());
        obj.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        obj.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        obj.setMaMedicamentoId(ent.getMaMedicamentoId());
        obj.setMaMedicamentoCodigo(ent.getMaMedicamentoCodigo());
        obj.setMaMedicamentoValor(ent.getMaMedicamentoValor());
        obj.setValorTecnologiaCotizada(ent.getValorTecnologiaCotizada());
        obj.setValorTecnologiaPagada(ent.getValorTecnologiaPagada());
        obj.setMpNumeroPrescripcion(ent.getMpNumeroPrescripcion());
        obj.setBorrado(ent.getBorrado());
        obj.setCantidad(ent.getCantidad());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioBorra(ent.getUsuarioBorra());
        obj.setTerminalBorra(ent.getTerminalBorra());
        obj.setFechaHoraBorra(ent.getFechaHoraBorra());
        
        return obj;
    }

    public static AntAnticipoItems castNegocioEntidad(AntAnticipoItem obj) {
        AntAnticipoItems ent = new AntAnticipoItems();
        ent.setId(obj.getId());
        if(obj.getAntAnticiposId() != null && obj.getAntAnticiposId().getId() != null){
            ent.setAntAnticiposId(new AntAnticipos(obj.getAntAnticiposId().getId()));
        }
        ent.setTipoTecnologia(obj.getTipoTecnologia());
        ent.setMaTecnologiaId(obj.getMaTecnologiaId());
        ent.setMaTecnologiaCodigo(obj.getMaTecnologiaCodigo());
        ent.setMaTecnologiaValor(obj.getMaTecnologiaValor());
        ent.setMaMedicamentoId(obj.getMaMedicamentoId());
        ent.setMaMedicamentoCodigo(obj.getMaMedicamentoCodigo());
        ent.setMaMedicamentoValor(obj.getMaMedicamentoValor());
        ent.setValorTecnologiaCotizada(obj.getValorTecnologiaCotizada());
        ent.setValorTecnologiaPagada(obj.getValorTecnologiaPagada());
        ent.setMpNumeroPrescripcion(obj.getMpNumeroPrescripcion());
        ent.setBorrado(obj.isBorrado());
        ent.setCantidad(obj.getCantidad());
        //Auditoria
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }
}
