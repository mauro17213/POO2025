/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4CargaAnulada;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4CargaAnuladaSuceso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4CargaAnuladaSucesos;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4CargaAnuladas;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos4;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4CargaAnuladaSucesoRemoto;
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
@Remote(AuAnexo4CargaAnuladaSucesoRemoto.class)
public class AuAnexo4CargaAnuladaSucesoServicio extends GenericoServicio implements AuAnexo4CargaAnuladaSucesoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(u) FROM AuAnexo4CargaAnuladaSucesos u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();     
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND u.id = ").append(e.getValue()).append(" ");
                            break;
                        case "auAnexo4CargaAnuladasId.id":
                            strTitulo = agregarJoin("INNER JOIN AuAnexo4CargaAnuladas auc ON u.auAnexo4CargaAnuladasId = auc.id ", strTitulo);
                            strQuery.append("AND auc.id = ").append(e.getValue()).append(" ");
                            break;
                        case "auAnexos4Id.id":
                            strTitulo = agregarJoin("INNER JOIN AuAnexos4 au ON u.auAnexos4Id = au.id ", strTitulo);
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
    public List<AuAnexo4CargaAnuladaSuceso> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo4CargaAnuladaSuceso> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuAnexo4CargaAnuladaSucesos u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND u.id = ").append(e.getValue()).append(" ");
                            break;
                        case "auAnexo4CargaAnuladasId.id":
                            strTitulo = agregarJoin("INNER JOIN AuAnexo4CargaAnuladas auc ON u.auAnexo4CargaAnuladasId = auc.id ", strTitulo);
                            strQuery.append("AND auc.id = ").append(e.getValue()).append(" ");
                            break;
                        case "auAnexos4Id.id":
                            strTitulo = agregarJoin("INNER JOIN AuAnexos4 au ON u.auAnexos4Id = au.id ", strTitulo);
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
            List<AuAnexo4CargaAnuladaSucesos> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexo4CargaAnuladaSucesos per : list) {
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
            String strTitulo = "SELECT COUNT(u) FROM AuAnexo4CargaAnuladaSucesos u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();     
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND u.id = ").append(e.getValue()).append(" ");
                            break;
                        case "auAnexo4CargaAnuladasId.id":
                            strTitulo = agregarJoin("INNER JOIN AuAnexo4CargaAnuladas auc ON u.auAnexo4CargaAnuladasId = auc.id ", strTitulo);
                            strQuery.append("AND auc.id = ").append(e.getValue()).append(" ");
                            break;
                        case "auAnexos4Id.id":
                            strTitulo = agregarJoin("INNER JOIN AuAnexos4 au ON u.auAnexos4Id = au.id ", strTitulo);
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
    public List<AuAnexo4CargaAnuladaSuceso> consultarSucesoLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo4CargaAnuladaSuceso> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuAnexo4CargaAnuladaSucesos u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND u.id = ").append(e.getValue()).append(" ");
                            break;
                        case "auAnexo4CargaAnuladasId.id":
                            strTitulo = agregarJoin("INNER JOIN AuAnexo4CargaAnuladas auc ON u.auAnexo4CargaAnuladasId = auc.id ", strTitulo);
                            strQuery.append("AND auc.id = ").append(e.getValue()).append(" ");
                            break;
                        case "auAnexos4Id.id":
                            strTitulo = agregarJoin("INNER JOIN AuAnexos4 au ON u.auAnexos4Id = au.id ", strTitulo);
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
            List<AuAnexo4CargaAnuladaSucesos> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexo4CargaAnuladaSucesos per : list) {
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
    public AuAnexo4CargaAnuladaSuceso consultar(int id) throws Exception {
        AuAnexo4CargaAnuladaSuceso objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AuAnexo4CargaAnuladaSucesos) getEntityManager().find(AuAnexo4CargaAnuladaSucesos.class, id));
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
    public int insertar(AuAnexo4CargaAnuladaSuceso obj) throws Exception {
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
    public void actualizar(AuAnexo4CargaAnuladaSuceso obj) throws Exception {
        try {
            String hql = "UPDATE AuAnexo4CargaAnuladaSucesos SET "
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
    
    public static AuAnexo4CargaAnuladaSuceso castEntidadNegocioCorto(AuAnexo4CargaAnuladaSucesos ent) {
        AuAnexo4CargaAnuladaSuceso obj = new AuAnexo4CargaAnuladaSuceso();
        obj.setId(ent.getId());
        if(ent.getAuAnexo4CargaAnuladasId()!= null){
            AuAnexo4CargaAnulada carga = new AuAnexo4CargaAnulada();
            carga.setId(ent.getAuAnexo4CargaAnuladasId().getId());
            obj.setAuAnexo4CargaAnuladasId(carga);
        }
        if(ent.getAuAnexos4Id()!= null){
            AuAnexo4 empresa = new AuAnexo4();
            empresa.setId(ent.getAuAnexos4Id().getId());
            obj.setAuAnexos4Id(empresa);
        }
        obj.setEstado(ent.getEstado());
        obj.setData(ent.getData());
        obj.setFila(ent.getFila());
        obj.setColumna(ent.getColumna());
        obj.setDetalleFallo(ent.getDetalleFallo());
        obj.setFechaHora(ent.getFechaHora());
        return obj;
    }

    public static AuAnexo4CargaAnuladaSuceso castEntidadNegocioLargo(AuAnexo4CargaAnuladaSucesos ent) {
        AuAnexo4CargaAnuladaSuceso obj = new AuAnexo4CargaAnuladaSuceso();
        obj.setId(ent.getId());
        if(ent.getAuAnexo4CargaAnuladasId()!= null){
            AuAnexo4CargaAnulada carga = new AuAnexo4CargaAnulada();
            carga.setId(ent.getAuAnexo4CargaAnuladasId().getId());
            obj.setAuAnexo4CargaAnuladasId(carga);
        }
        if(ent.getAuAnexos4Id()!= null){
            AuAnexo4 empresa = new AuAnexo4();
            empresa.setId(ent.getAuAnexos4Id().getId());
            obj.setAuAnexos4Id(empresa);
        }
        obj.setEstado(ent.getEstado());
        obj.setData(ent.getData());
        obj.setFila(ent.getFila());
        obj.setColumna(ent.getColumna());
        obj.setDetalleFallo(ent.getDetalleFallo());
        obj.setFechaHora(ent.getFechaHora());
        return obj;
    }
    
    public static AuAnexo4CargaAnuladaSucesos castNegocioEntidad(AuAnexo4CargaAnuladaSuceso obj) {
        AuAnexo4CargaAnuladaSucesos ent = new AuAnexo4CargaAnuladaSucesos();
        ent.setId(obj.getId());
        if(obj.getAuAnexo4CargaAnuladasId() != null){
            AuAnexo4CargaAnuladas carga = new AuAnexo4CargaAnuladas();
            carga.setId(obj.getAuAnexo4CargaAnuladasId().getId());
            ent.setAuAnexo4CargaAnuladasId(carga);
        }
        if(obj.getAuAnexos4Id() != null){
            AuAnexos4 empresa = new AuAnexos4();
            empresa.setId(obj.getAuAnexos4Id().getId());
            ent.setAuAnexos4Id(empresa);
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
