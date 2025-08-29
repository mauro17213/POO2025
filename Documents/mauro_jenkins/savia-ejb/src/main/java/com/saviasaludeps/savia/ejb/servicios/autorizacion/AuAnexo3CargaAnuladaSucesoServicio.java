/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3CargaAnulada;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3CargaAnuladaSuceso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo3CargaAnuladaSucesos;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo3CargaAnuladas;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos3;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3CargaAnuladaSucesoRemoto;
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
@Remote(AuAnexo3CargaAnuladaSucesoRemoto.class)
public class AuAnexo3CargaAnuladaSucesoServicio extends GenericoServicio implements AuAnexo3CargaAnuladaSucesoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(u) FROM AuAnexo3CargaAnuladaSucesos u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();     
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND u.id = ").append(e.getValue()).append(" ");
                            break;
                        case "auAnexo3CargaAnuladasId.id":
                            strTitulo = agregarJoin("INNER JOIN AuAnexo3CargaAnuladas auc ON u.auAnexo3CargaAnuladasId = auc.id ", strTitulo);
                            strQuery.append("AND auc.id = ").append(e.getValue()).append(" ");
                            break;
                        case "auAnexos3Id.id":
                            strTitulo = agregarJoin("INNER JOIN AuAnexos3 au ON u.auAnexos3Id = au.id ", strTitulo);
                            strQuery.append("AND au.id = ").append(e.getValue()).append(" ");
                            break;
                        case "estado":
                            strQuery.append("AND u.estado = ").append(e.getValue()).append(" ");
                            break;
                        case "fila":
                            strQuery.append("AND u.fila = ").append(e.getValue()).append(" ");
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
    public List<AuAnexo3CargaAnuladaSuceso> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo3CargaAnuladaSuceso> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuAnexo3CargaAnuladaSucesos u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND u.id = ").append(e.getValue()).append(" ");
                            break;
                        case "auAnexo3CargaAnuladasId.id":
                            strTitulo = agregarJoin("INNER JOIN AuAnexo3CargaAnuladas auc ON u.auAnexo3CargaAnuladasId = auc.id ", strTitulo);
                            strQuery.append("AND auc.id = ").append(e.getValue()).append(" ");
                            break;
                        case "auAnexos3Id.id":
                            strTitulo = agregarJoin("INNER JOIN AuAnexos3 au ON u.auAnexos3Id = au.id ", strTitulo);
                            strQuery.append("AND au.id = ").append(e.getValue()).append(" ");
                            break;
                        case "estado":
                            strQuery.append("AND u.estado = ").append(e.getValue()).append(" ");
                            break;
                        case "fila":
                            strQuery.append("AND u.fila = ").append(e.getValue()).append(" ");
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
            List<AuAnexo3CargaAnuladaSucesos> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexo3CargaAnuladaSucesos per : list) {
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
    public int consultarCantidadSucesoLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(u) FROM AuAnexo3CargaAnuladaSucesos u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();     
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND u.id = ").append(e.getValue()).append(" ");
                            break;
                        case "auAnexo3CargaAnuladasId.id":
                            strTitulo = agregarJoin("INNER JOIN AuAnexo3CargaAnuladas auc ON u.auAnexo3CargaAnuladasId = auc.id ", strTitulo);
                            strQuery.append("AND auc.id = ").append(e.getValue()).append(" ");
                            break;
                        case "auAnexos3Id.id":
                            strTitulo = agregarJoin("INNER JOIN AuAnexos3 au ON u.auAnexos3Id = au.id ", strTitulo);
                            strQuery.append("AND au.id = ").append(e.getValue()).append(" ");
                            break;
                        case "estado":
                            strQuery.append("AND u.estado = ").append(e.getValue()).append(" ");
                            break;
                        case "fila":
                            strQuery.append("AND u.fila = ").append(e.getValue()).append(" ");
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
    public List<AuAnexo3CargaAnuladaSuceso> consultarSucesoLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo3CargaAnuladaSuceso> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuAnexo3CargaAnuladaSucesos u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND u.id = ").append(e.getValue()).append(" ");
                            break;
                        case "auAnexo3CargaAnuladasId.id":
                            strTitulo = agregarJoin("INNER JOIN AuAnexo3CargaAnuladas auc ON u.auAnexo3CargaAnuladasId = auc.id ", strTitulo);
                            strQuery.append("AND auc.id = ").append(e.getValue()).append(" ");
                            break;
                        case "auAnexos3Id.id":
                            strTitulo = agregarJoin("INNER JOIN AuAnexos3 au ON u.auAnexos3Id = au.id ", strTitulo);
                            strQuery.append("AND au.id = ").append(e.getValue()).append(" ");
                            break;
                        case "estado":
                            strQuery.append("AND u.estado = ").append(e.getValue()).append(" ");
                            break;
                        case "fila":
                            strQuery.append("AND u.fila = ").append(e.getValue()).append(" ");
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
            List<AuAnexo3CargaAnuladaSucesos> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexo3CargaAnuladaSucesos per : list) {
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
    public AuAnexo3CargaAnuladaSuceso consultar(int id) throws Exception {
        AuAnexo3CargaAnuladaSuceso objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AuAnexo3CargaAnuladaSucesos) getEntityManager().find(AuAnexo3CargaAnuladaSucesos.class, id));
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
    public int insertar(AuAnexo3CargaAnuladaSuceso obj) throws Exception {
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
    public void actualizar(AuAnexo3CargaAnuladaSuceso obj) throws Exception {
        try {
            String hql = "UPDATE AuAnexo3CargaAnuladaSucesos SET "
                    + "estado = :estado "
                    + ", data = :data "
                    + ", fila = :fila "
                    + ", columna = :columna "
                    + ", detalleFallo = :detalleFallo "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("data", obj.getData());
            query.setParameter("fila", obj.getFila());
            query.setParameter("columna", obj.getColumna());
            query.setParameter("v", obj.getDetalleFallo());
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
    
    public static AuAnexo3CargaAnuladaSuceso castEntidadNegocioCorto(AuAnexo3CargaAnuladaSucesos ent) {
        AuAnexo3CargaAnuladaSuceso obj = new AuAnexo3CargaAnuladaSuceso();
        obj.setId(ent.getId());
        if(ent.getAuAnexo3CargaAnuladasId()!= null){
            AuAnexo3CargaAnulada carga = new AuAnexo3CargaAnulada();
            carga.setId(ent.getAuAnexo3CargaAnuladasId().getId());
            obj.setAuAnexo3CargaAnuladasId(carga);
        }
        if(ent.getAuAnexos3Id()!= null){
            AuAnexo3 empresa = new AuAnexo3();
            empresa.setId(ent.getAuAnexos3Id().getId());
            obj.setAuAnexos3Id(empresa);
        }
        obj.setEstado(ent.getEstado());
        obj.setData(ent.getData());
        obj.setFila(ent.getFila());
        obj.setColumna(ent.getColumna());
        obj.setDetalleFallo(ent.getDetalleFallo());
        obj.setFechaHora(ent.getFechaHora());
        return obj;
    }

    public static AuAnexo3CargaAnuladaSuceso castEntidadNegocioLargo(AuAnexo3CargaAnuladaSucesos ent) {
        AuAnexo3CargaAnuladaSuceso obj = new AuAnexo3CargaAnuladaSuceso();
        obj.setId(ent.getId());
        if(ent.getAuAnexo3CargaAnuladasId()!= null){
            AuAnexo3CargaAnulada carga = new AuAnexo3CargaAnulada();
            carga.setId(ent.getAuAnexo3CargaAnuladasId().getId());
            obj.setAuAnexo3CargaAnuladasId(carga);
        }
        if(ent.getAuAnexos3Id()!= null){
            AuAnexo3 anexo3 = new AuAnexo3();
            anexo3.setId(ent.getAuAnexos3Id().getId());
            obj.setAuAnexos3Id(anexo3);
        }
        obj.setEstado(ent.getEstado());
        obj.setData(ent.getData());
        obj.setFila(ent.getFila());
        obj.setColumna(ent.getColumna());
        obj.setDetalleFallo(ent.getDetalleFallo());
        obj.setFechaHora(ent.getFechaHora());
        return obj;
    }
    
    public static AuAnexo3CargaAnuladaSucesos castNegocioEntidad(AuAnexo3CargaAnuladaSuceso obj) {
        AuAnexo3CargaAnuladaSucesos ent = new AuAnexo3CargaAnuladaSucesos();
        ent.setId(obj.getId());
        if(obj.getAuAnexo3CargaAnuladasId() != null){
            AuAnexo3CargaAnuladas carga = new AuAnexo3CargaAnuladas();
            carga.setId(obj.getAuAnexo3CargaAnuladasId().getId());
            ent.setAuAnexo3CargaAnuladasId(carga);
        }
        if(obj.getAuAnexos3Id() != null){
            AuAnexos3 anexo3 = new AuAnexos3();
            anexo3.setId(obj.getAuAnexos3Id().getId());
            ent.setAuAnexos3Id(anexo3);
        }
        ent.setEstado(obj.getEstado());
        ent.setData(obj.getData());
        ent.setFila(obj.getFila());
        ent.setColumna(obj.getColumna());
        ent.setDetalleFallo(obj.getDetalleFallo());
        ent.setFechaHora(obj.getFechaHora());
        return ent;
    }
}
