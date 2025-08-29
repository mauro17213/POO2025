/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.anticipo;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipo;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoItem;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoValor;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AntAnticipoItems;
import com.saviasaludeps.savia.ejb.entidades.AntAnticipoValores;
import com.saviasaludeps.savia.ejb.entidades.AntAnticipos;
import com.saviasaludeps.savia.ejb.entidades.AuCotizaciones;
import com.saviasaludeps.savia.ejb.entidades.GnMaestros;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.anticipo.AnticipoItemRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import com.saviasaludeps.savia.negocio.anticipo.AnticipoValorRemoto;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(AnticipoValorRemoto.class)
public class AnticipoValorServicio extends GenericoServicio implements AnticipoValorRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(u) FROM AntAnticipoValores u ";
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
    public List<AntAnticipoValor> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AntAnticipoValor> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AntAnticipoValores u ";
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
            List<AntAnticipoValores> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AntAnticipoValores per : list) {
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
    public AntAnticipoValor consultar(int id) throws Exception {
        AntAnticipoValor objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AntAnticipoValores) getEntityManager().find(AntAnticipoValores.class, id));
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
    public int insertar(AntAnticipoValor obj) throws Exception {
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
    public void actualizar(AntAnticipoValor obj) throws Exception {
        try {
            String hql = "UPDATE AntAnticipoValores SET "
                    + "tipoTecnologia = :tipoTecnologia, "
                    + "maTecnologiaId = :maTecnologiaId, "
                    + "maTecnologiaCodigo = :maTecnologiaCodigo, "
                    + "maTecnologiaValor = :maTecnologiaValor, "
                    + "maMedicamentoId = :maMedicamentoId, "
                    + "maMedicamentoCodigo = :maMedicamentoCodigo, "
                    + "maMedicamentoValor = :maMedicamentoValor, "
                    + "valorTecnologia = :valorTecnologia, "
                    + "cantidad = :cantidad "
                    //+ "usuarioModifica = :usuarioModifica, "
                    //+ "terminalModifica = :terminalModifica, "
                    //+ "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
          
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
    public List<AntAnticipoValor> consultarPorAnticipoId(int idAnticipo) throws Exception {
        List<AntAnticipoValor> obj = new ArrayList<>();
        try {
            String strQuery = "SELECT p "
                    + "FROM AntAnticipoValores p "
                    + "WHERE p.id > 0 "
                    + "AND p.antAnticiposId.id = " + idAnticipo + " "
                    + "ORDER BY p.id DESC";
            List<AntAnticipoValores> list = getEntityManager().createQuery(strQuery).getResultList();
            for (AntAnticipoValores gestion : list) {
                obj.add(castEntidadNegocioCorto(gestion));
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
    public AntAnticipoValor consultarPorAnticipoItemId(int idAnticipoItem) throws Exception {
        AntAnticipoValor ObjectResult = null;
        String sql = "SELECT itm FROM AntAnticipoValores itv "
                + "INNER JOIN AntAnticipoItems ant ON itv.antAnticipoItemsId = ant.id "
                + "WHERE ant.id = :idAnticipoItem "
                + "ORDER BY itv.id DESC";
        try {
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("idAnticipoItem", idAnticipoItem);
            List<AntAnticipoValores> list = query.setMaxResults(1).getResultList();
            for (AntAnticipoValores item : list) {
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
    public AntAnticipoValor consultarBYCotizacionId(int idCotizacion) throws Exception {
        AntAnticipoValor ObjectResult = null;
        String sql = "SELECT itm FROM AntAnticipoValores itm "
                + "INNER JOIN AuCotizaciones auc ON itm.auCotizacionesId = auc.id "
                + "WHERE auc.id = :idCotizacion "
                + "ORDER BY itm.id DESC";
        try {
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("idAnticipo", idCotizacion);
            List<AntAnticipoValores> list = query.setMaxResults(1).getResultList();
            for (AntAnticipoValores item : list) {
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
    
    public static AntAnticipoValor castEntidadNegocioCorto(AntAnticipoValores ent) {
        AntAnticipoValor obj = new AntAnticipoValor();
        obj.setId(ent.getId());
        if(ent.getAntAnticiposId() != null){
            obj.setAntAnticiposId(new AntAnticipo(ent.getAntAnticiposId().getId()));
        }
        if(ent.getAntAnticipoItemsId() != null){
            AntAnticipoItem antAnticipoItem = new AntAnticipoItem();
            antAnticipoItem.setId(ent.getAntAnticipoItemsId().getId());
            antAnticipoItem.setTipoTecnologia(ent.getAntAnticipoItemsId().getTipoTecnologia());
            antAnticipoItem.setMaTecnologiaId(ent.getAntAnticipoItemsId().getMaTecnologiaId());
            antAnticipoItem.setMaTecnologiaCodigo(ent.getAntAnticipoItemsId().getMaTecnologiaCodigo());
            antAnticipoItem.setMaTecnologiaValor(ent.getAntAnticipoItemsId().getMaTecnologiaValor());
            obj.setAntAnticipoItemsId(antAnticipoItem);
            
        }
        if(ent.getAuCotizacionesId() !=  null){
            obj.setAuCotizacionesId(new AuCotizacion(ent.getAuCotizacionesId().getId()));
        }
        obj.setObservacion(ent.getObservacion());
        obj.setDevolucion(ent.getDevolucion());
        obj.setTipoDevolucion(ent.getTipoDevolucion());
        obj.setValor(ent.getValor());
       
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }

    public static AntAnticipoValor castEntidadNegocioLargo(AntAnticipoValores ent) {
        AntAnticipoValor obj = new AntAnticipoValor();
        obj.setId(ent.getId());
        if(ent.getAntAnticiposId() != null){
            obj.setAntAnticiposId(new AntAnticipo(ent.getAntAnticiposId().getId()));
        }
        if(ent.getAntAnticipoItemsId() != null){
            obj.setAntAnticipoItemsId(new AntAnticipoItem(ent.getAntAnticipoItemsId().getId()));
        }
        if(ent.getAuCotizacionesId() !=  null){
            obj.setAuCotizacionesId(new AuCotizacion(ent.getAuCotizacionesId().getId()));
        }
        obj.setObservacion(ent.getObservacion());
        obj.setDevolucion(ent.getDevolucion());
        obj.setTipoDevolucion(ent.getTipoDevolucion());
        obj.setValor(ent.getValor());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }

    public static AntAnticipoValores castNegocioEntidad(AntAnticipoValor obj) {
        AntAnticipoValores ent = new AntAnticipoValores();
        ent.setId(obj.getId());
        if(obj.getAntAnticiposId() != null && obj.getAntAnticiposId().getId() != null){
            ent.setAntAnticiposId(new AntAnticipos(obj.getAntAnticiposId().getId()));
        }
        if(obj.getAntAnticipoItemsId() != null){
            ent.setAntAnticipoItemsId(new AntAnticipoItems(obj.getAntAnticipoItemsId().getId()));
        }
        if(obj.getAuCotizacionesId() !=  null){
            ent.setAuCotizacionesId(new AuCotizaciones(obj.getAuCotizacionesId().getId()));
        }
        ent.setObservacion(obj.getObservacion());
        ent.setDevolucion(obj.isDevolucion());
        ent.setTipoDevolucion(obj.getTipoDevolucion());
        ent.setValor(obj.getValor());
        //Auditoria
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }
}
