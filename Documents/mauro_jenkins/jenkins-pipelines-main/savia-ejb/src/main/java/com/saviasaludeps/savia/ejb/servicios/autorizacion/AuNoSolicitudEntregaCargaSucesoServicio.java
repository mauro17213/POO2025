/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudEntrega;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudEntregaCarga;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudEntregaCargaSuceso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudEntregaCargaSucesos;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudEntregaCargas;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudEntregas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudEntregaCargaSucesoRemoto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.sql.DataSource;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(AuNoSolicitudEntregaCargaSucesoRemoto.class)
public class AuNoSolicitudEntregaCargaSucesoServicio extends GenericoServicio implements AuNoSolicitudEntregaCargaSucesoRemoto {
    final static int CANTIDAD_BATCH = 1000;
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(u) FROM AuNoSolicitudEntregaCargaSucesos u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
 
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
                        case "maeClasificacionId.id":
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
    public List<AuNoSolicitudEntregaCargaSuceso> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuNoSolicitudEntregaCargaSuceso> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuNoSolicitudEntregaCargaSucesos u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            
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
                        case "maeClasificacionId.id":
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
            List<AuNoSolicitudEntregaCargaSucesos> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuNoSolicitudEntregaCargaSucesos per : list) {
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
            String strTitulo = "SELECT COUNT(u) FROM AuNoSolicitudEntregaCargaSucesos u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            if (paramConsulta.getParametroConsulta3() != null) {
                strTitulo = agregarJoin("INNER JOIN AuNoSolicitudEntregaCargas auc ON u.auNoSolicitudEntregaCargasId = auc.id ", strTitulo);
                strQuery.append("AND auc.id = ").append(paramConsulta.getParametroConsulta3()).append(" ");
            } 
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "fila":
                            strQuery.append("AND u.fila = ").append(e.getValue()).append(" ");
                            break;
                        case "estado":
                            strQuery.append("AND u.estado = ").append(e.getValue()).append(" ");
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
    public List<AuNoSolicitudEntregaCargaSuceso> consultarSucesoLista(ParamConsulta paramConsulta) throws Exception {
        List<AuNoSolicitudEntregaCargaSuceso> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuNoSolicitudEntregaCargaSucesos u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            if (paramConsulta.getParametroConsulta3() != null) {
                strTitulo = agregarJoin("INNER JOIN AuNoSolicitudEntregaCargas auc ON u.auNoSolicitudEntregaCargasId = auc.id ", strTitulo);
                strQuery.append("AND auc.id = ").append(paramConsulta.getParametroConsulta3()).append(" ");
            } 
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "fila":
                            strQuery.append("AND u.fila = ").append(e.getValue()).append(" ");
                            break;
                        case "estado":
                            strQuery.append("AND u.estado = ").append(e.getValue()).append(" ");
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
            List<AuNoSolicitudEntregaCargaSucesos> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuNoSolicitudEntregaCargaSucesos per : list) {
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
            return sql = sql + join;
        }
    }
    
    @Override
    public AuNoSolicitudEntregaCargaSuceso consultar(int id) throws Exception {
        AuNoSolicitudEntregaCargaSuceso objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AuNoSolicitudEntregaCargaSucesos) getEntityManager().find(AuNoSolicitudEntregaCargaSucesos.class, id));
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
    public int insertar(AuNoSolicitudEntregaCargaSuceso obj) throws Exception {
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
    public boolean insertarAuNoSolicitudEntregaCargaSucesos(List<AuNoSolicitudEntregaCargaSuceso> listaNegocio) throws ClassNotFoundException, SQLException {
        boolean respuesta = false;
        int contadorInserts = 0;
        StringBuilder stringBuilder = new StringBuilder(10000000);
        int cantidadLista = 0;
        String columnas = "au_no_solicitud_entrega_cargas_id,";
        columnas += "au_no_solicitud_entregas_id,";
        columnas += "data,";
        columnas += "fila,";
        columnas += "estado,";
        columnas += "detalle_fallo,";
        columnas += "fecha_hora_proceso";
        String query = "insert into au_no_solicitud_entrega_carga_sucesos (" + columnas + ") values ";
        stringBuilder.append(query);
        cantidadLista += listaNegocio.size();
        for (AuNoSolicitudEntregaCargaSuceso obj : listaNegocio) {
            contadorInserts++;
            stringBuilder.append("(");
            stringBuilder.append("").append(obj.getAuNoSolicitudEntregaCargasId().getId()).append(",");
            if(obj.getAuNoSolicitudEntregaId()!= null){
                stringBuilder.append("").append(obj.getAuNoSolicitudEntregaId().getId()).append(",");
            }else{
                stringBuilder.append("NULL,");
            }
            stringBuilder.append("").append(obj.getData()).append(",");
            stringBuilder.append("'").append(obj.getFila()).append("',");
            stringBuilder.append("").append(obj.getEstado()).append(",");
            if(obj.getDetalleFallo() != null){
                stringBuilder.append("").append(obj.getDetalleFallo()).append(",");
            }else{
                stringBuilder.append("NULL,");
            }
            stringBuilder.append(" NOW() ");
            stringBuilder.append("),");
            if (contadorInserts % CANTIDAD_BATCH == 0 || contadorInserts == cantidadLista) {
                //Remover ultima coma y cerrar el paretensis
                query = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
                Connection conn = null;
                PreparedStatement ps = null;
                try {
                    conn = getConnection();
                    ps = conn.prepareStatement(query);
                    ps.executeUpdate();
                    respuesta = true;
                } catch (SQLException ex) {
                    System.out.println("Error insertando sucesos de carga de solicitudes: " + ex.toString());
                } catch (java.lang.Exception ex) {
                    Logger.getLogger(AuNoSolicitudEntregaCargaSucesoServicio.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    //stringBuilder = new StringBuilder(10000000);
                    //query = "insert into au_anexo3_carga_sucesos (" + columnas + ") values ";
                    //stringBuilder.append(query);
                    if(ps != null){
                        ps.close();
                    }
                    if(conn != null){
                        conn.close();
                    }
                }
            }
        }
        return respuesta;
        //finalizarProceso(idCarga, ESTADO_CARGA_PROCESADO);
    }
    
    public Connection getConnection() throws java.lang.Exception {
        Connection dbConnection = null;
        try {
            DataSource data = (DataSource) getEntityManager().getEntityManagerFactory().getProperties().get("hibernate.connection.datasource");
            if (data == null) {
                throw new Exception("No se pudo obtener el datasource ");
            }
            dbConnection = (Connection) data.getConnection();
            if (dbConnection == null) {
                throw new Exception("No se pudo obtener la conexion con DB");
            }
        } catch (SQLException e) {
            Exception("Error obtener conexion sql de GenericoServicio : ", e);
        } catch (Exception e) {
            Exception("Error general al obtener la conexion : ", e);
        }

        return dbConnection;
    }
    
    public static AuNoSolicitudEntregaCargaSuceso castEntidadNegocioCorto(AuNoSolicitudEntregaCargaSucesos ent) {
        AuNoSolicitudEntregaCargaSuceso obj = new AuNoSolicitudEntregaCargaSuceso();
        obj.setId(obj.getId());
        if(ent.getAuNoSolicitudEntregaCargasId() != null){
            AuNoSolicitudEntregaCarga detalle = new AuNoSolicitudEntregaCarga();
            detalle.setId(ent.getAuNoSolicitudEntregaCargasId().getId());
            obj.setAuNoSolicitudEntregaCargasId(detalle);
        }
        if(ent.getAuNoSolicitudEntregasId() != null){
            AuNoSolicitudEntrega carga = new AuNoSolicitudEntrega();
            carga.setId(ent.getAuNoSolicitudEntregasId().getId());
            obj.setAuNoSolicitudEntregaId(carga);
        }
        obj.setData(ent.getData());
        obj.setFila(ent.getFila());
        obj.setEstado(ent.getEstado());
       
        obj.setDetalleFallo(ent.getDetalleFallo());
        obj.setFechaHoraProceso(ent.getFechaHoraProceso());
        
        return obj;
    }

    public static AuNoSolicitudEntregaCargaSuceso castEntidadNegocioLargo(AuNoSolicitudEntregaCargaSucesos ent) {
        AuNoSolicitudEntregaCargaSuceso obj = new AuNoSolicitudEntregaCargaSuceso();
        obj.setId(obj.getId());
        if(ent.getAuNoSolicitudEntregaCargasId() != null){
            AuNoSolicitudEntregaCarga detalle = new AuNoSolicitudEntregaCarga();
            detalle.setId(ent.getAuNoSolicitudEntregaCargasId().getId());
            obj.setAuNoSolicitudEntregaCargasId(detalle);
        }
        if(ent.getAuNoSolicitudEntregasId() != null){
            AuNoSolicitudEntrega carga = new AuNoSolicitudEntrega();
            carga.setId(ent.getAuNoSolicitudEntregasId().getId());
            obj.setAuNoSolicitudEntregaId(carga);
        }
        obj.setData(ent.getData());
        obj.setFila(ent.getFila());
        obj.setEstado(ent.getEstado());
       
        obj.setDetalleFallo(ent.getDetalleFallo());
        obj.setFechaHoraProceso(ent.getFechaHoraProceso());
        return obj;
    }
    
    public static AuNoSolicitudEntregaCargaSucesos castNegocioEntidad(AuNoSolicitudEntregaCargaSuceso obj) {
        AuNoSolicitudEntregaCargaSucesos ent = new AuNoSolicitudEntregaCargaSucesos();
        ent.setId(obj.getId());
        if(obj.getAuNoSolicitudEntregaCargasId() != null){
            AuNoSolicitudEntregaCargas noSolicitudEntregaCargas = new AuNoSolicitudEntregaCargas();
            noSolicitudEntregaCargas.setId(obj.getAuNoSolicitudEntregaCargasId().getId());
            ent.setAuNoSolicitudEntregaCargasId(noSolicitudEntregaCargas);
        }
        if(obj.getAuNoSolicitudEntregaId() != null){
            AuNoSolicitudEntregas carga = new AuNoSolicitudEntregas();
            carga.setId(obj.getAuNoSolicitudEntregaId().getId());
            ent.setAuNoSolicitudEntregasId(carga);
        }
        ent.setData(obj.getData());
        ent.setFila(obj.getFila());
        ent.setEstado(obj.getEstado());
       
        ent.setDetalleFallo(obj.getDetalleFallo());
        ent.setFechaHoraProceso(obj.getFechaHoraProceso());
        return ent;
    }
}
