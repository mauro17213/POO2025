/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudCarga;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudCargaDetalle;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudCargaSuceso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudCargaDetalles;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudCargaSucesos;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudCargas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudCargaSucesoRemoto;
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
@Remote(AuNoSolicitudCargaSucesoRemoto.class)
public class AuNoSolicitudCargaSucesoServicio extends GenericoServicio implements AuNoSolicitudCargaSucesoRemoto {
    final static int CANTIDAD_BATCH = 1000;
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(u) FROM AuNoSolicitudCargaSucesos u ";
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
    public List<AuNoSolicitudCargaSuceso> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuNoSolicitudCargaSuceso> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuNoSolicitudCargaSucesos u ";
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
            List<AuNoSolicitudCargaSucesos> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuNoSolicitudCargaSucesos per : list) {
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
            String strTitulo = "SELECT COUNT(u) FROM AuNoSolicitudCargaSucesos u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            
            if (paramConsulta.getParametroConsulta3() != null) {
                strTitulo = agregarJoin("INNER JOIN AuNoSolicitudCargas auc ON u.auNoSolicitudCargasId = auc.id ", strTitulo);
                strQuery.append("AND auc.id = ").append(paramConsulta.getParametroConsulta3()).append(" ");
            } 
            
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery.append("AND u.tipo = ").append(e.getValue()).append(" ");
                            break;
                        case "fila":
                            strQuery.append("AND u.fila = ").append(e.getValue()).append(" ");
                            break;
                        case "columna":
                            strQuery.append("AND u.columna = ").append(e.getValue()).append(" ");
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
    public List<AuNoSolicitudCargaSuceso> consultarSucesoLista(ParamConsulta paramConsulta) throws Exception {
        List<AuNoSolicitudCargaSuceso> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuNoSolicitudCargaSucesos u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            
            if (paramConsulta.getParametroConsulta3() != null) {
                strTitulo = agregarJoin("INNER JOIN AuNoSolicitudCargas auc ON u.auNoSolicitudCargasId = auc.id ", strTitulo);
                strQuery.append("AND auc.id = ").append(paramConsulta.getParametroConsulta3()).append(" ");
            } 
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery.append("AND u.tipo = ").append(e.getValue()).append(" ");
                            break;
                        case "fila":
                            strQuery.append("AND u.fila = ").append(e.getValue()).append(" ");
                            break;
                        case "columna":
                            strQuery.append("AND u.columna = ").append(e.getValue()).append(" ");
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
            List<AuNoSolicitudCargaSucesos> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuNoSolicitudCargaSucesos per : list) {
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
    public AuNoSolicitudCargaSuceso consultar(int id) throws Exception {
        AuNoSolicitudCargaSuceso objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AuNoSolicitudCargaSucesos) getEntityManager().find(AuNoSolicitudCargaSucesos.class, id));
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
    public int insertar(AuNoSolicitudCargaSuceso obj) throws Exception {
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
    public boolean insertarAuNoSolicitudCargaSucesos(List<AuNoSolicitudCargaSuceso> listaNegocio) throws ClassNotFoundException, SQLException {
        boolean respuesta = false;
        int contadorInserts = 0;
        StringBuilder stringBuilder = new StringBuilder(10000000);
        int cantidadLista = 0;
        String columnas = "au_no_solicitud_cargas_id,";
        columnas += "au_no_solicitud_carga_detalles_id,";
        columnas += "tipo,";
        columnas += "descripcion,";
        columnas += "fila,";
        columnas += "columna,";
        columnas += "fecha_hora";
        String query = "insert into au_no_solicitud_carga_sucesos (" + columnas + ") values ";
        stringBuilder.append(query);
        cantidadLista += listaNegocio.size();
        for (AuNoSolicitudCargaSuceso obj : listaNegocio) {
            contadorInserts++;
            stringBuilder.append("(");
            stringBuilder.append("").append(obj.getAuNoSolicitudCargasId().getId()).append(",");
            if(obj.getAuNoSolicitudCargaDetallesId() != null){
                stringBuilder.append("").append(obj.getAuNoSolicitudCargaDetallesId().getId()).append(",");
            }else{
                stringBuilder.append("NULL,");
            }
            stringBuilder.append("").append(obj.getTipo()).append(",");
            stringBuilder.append("'").append(obj.getDescripcion()).append("',");
            stringBuilder.append("").append(obj.getFila()).append(",");
            stringBuilder.append("").append(obj.getColumna()).append(",");
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
                    Logger.getLogger(AuNoSolicitudCargaSucesoServicio.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public static AuNoSolicitudCargaSuceso castEntidadNegocioCorto(AuNoSolicitudCargaSucesos ent) {
        AuNoSolicitudCargaSuceso obj = new AuNoSolicitudCargaSuceso();
        obj.setId(ent.getId());
        if(ent.getAuNoSolicitudCargaDetallesId() != null){
            AuNoSolicitudCargaDetalle detalle = new AuNoSolicitudCargaDetalle();
            detalle.setId(ent.getAuNoSolicitudCargaDetallesId().getId());
            obj.setAuNoSolicitudCargaDetallesId(detalle);
        }
        if(ent.getAuNoSolicitudCargasId() != null){
            AuNoSolicitudCarga carga = new AuNoSolicitudCarga();
            carga.setId(ent.getAuNoSolicitudCargasId().getId());
            obj.setAuNoSolicitudCargasId(carga);
        }
        obj.setTipo(ent.getTipo());
        obj.setDescripcion(ent.getDescripcion());
        obj.setFila(ent.getFila());
        obj.setColumna(ent.getColumna());
        obj.setFechaHora(ent.getFechaHora());
        
        return obj;
    }

    public static AuNoSolicitudCargaSuceso castEntidadNegocioLargo(AuNoSolicitudCargaSucesos ent) {
        AuNoSolicitudCargaSuceso obj = new AuNoSolicitudCargaSuceso();
        obj.setId(ent.getId());
        if(ent.getAuNoSolicitudCargaDetallesId() != null){
            AuNoSolicitudCargaDetalle detalle = new AuNoSolicitudCargaDetalle();
            detalle.setId(ent.getAuNoSolicitudCargaDetallesId().getId());
            obj.setAuNoSolicitudCargaDetallesId(detalle);
        }
        if(ent.getAuNoSolicitudCargasId() != null){
            AuNoSolicitudCarga carga = new AuNoSolicitudCarga();
            carga.setId(ent.getAuNoSolicitudCargasId().getId());
            obj.setAuNoSolicitudCargasId(carga);
        }
        obj.setTipo(ent.getTipo());
        obj.setDescripcion(ent.getDescripcion());
        obj.setFila(ent.getFila());
        obj.setColumna(ent.getColumna());
        obj.setFechaHora(ent.getFechaHora());
        return obj;
    }
    
    public static AuNoSolicitudCargaSucesos castNegocioEntidad(AuNoSolicitudCargaSuceso obj) {
        AuNoSolicitudCargaSucesos ent = new AuNoSolicitudCargaSucesos();
        ent.setId(obj.getId());
        if(obj.getAuNoSolicitudCargaDetallesId() != null){
            AuNoSolicitudCargaDetalles detalle = new AuNoSolicitudCargaDetalles();
            detalle.setId(obj.getAuNoSolicitudCargaDetallesId().getId());
            ent.setAuNoSolicitudCargaDetallesId(detalle);
        }
        if(obj.getAuNoSolicitudCargasId() != null){
            AuNoSolicitudCargas carga = new AuNoSolicitudCargas();
            carga.setId(obj.getAuNoSolicitudCargasId().getId());
            ent.setAuNoSolicitudCargasId(carga);
        }
        ent.setTipo(obj.getTipo());
        ent.setDescripcion(obj.getDescripcion());
        ent.setFila(obj.getFila());
        ent.setColumna(obj.getColumna());
        ent.setFechaHora(obj.getFechaHora());
        return ent;
    }
}
