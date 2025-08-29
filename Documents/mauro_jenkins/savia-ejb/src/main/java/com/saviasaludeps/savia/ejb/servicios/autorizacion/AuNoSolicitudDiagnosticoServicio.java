/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitud;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudDiagnostico;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudDiagnosticos;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudDiagnosticoRemoto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
@Remote(AuNoSolicitudDiagnosticoRemoto.class)
public class AuNoSolicitudDiagnosticoServicio extends GenericoServicio implements AuNoSolicitudDiagnosticoRemoto {
    
    final static int CANTIDAD_BATCH = 1000;
    
    private String agregarJoin(String join, String sql) {
        if (sql.contains(join)) {
            return sql;
        } else {
            return sql += join;
        }
    }
    
    @Override
    public AuNoSolicitudDiagnostico consultar(int id) throws Exception {
        AuNoSolicitudDiagnostico objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AuNoSolicitudDiagnosticos) getEntityManager().find(AuNoSolicitudDiagnosticos.class, id));
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
    public int insertar(AuNoSolicitudDiagnostico obj) throws Exception {
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
    public void actualizar(AuNoSolicitudDiagnostico obj) throws Exception {
        try {
            /*String hql = "UPDATE AuNoSolicitudDiagnosticos SET "
                    + "estado = :estado, "
                    + "tipo = :tipo, "
                    + "pbs = :pbs, "
                    + "maeClasificacionId.id = :maeClasificacionId, "
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
            query.setParameter("maeClasificacionId", obj.getMaeClasificacionId().getId());
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
            query.executeUpdate();*/
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public List<AuNoSolicitudDiagnostico> consultarDiagnosticoPorAuNosolicitudesId(int idAuNoSolicitudes) throws Exception {
        List<AuNoSolicitudDiagnostico> obj = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuNoSolicitudDiagnosticos u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            strTitulo = agregarJoin("INNER JOIN AuNoSolicitudes aun ON u.auNoSolicitudesId = aun.id ", strTitulo);
            strQuery.append("AND aun.id = ").append(idAuNoSolicitudes).append(" ");
            sql.append(strTitulo).append(strQuery);
            sql.append("ORDER BY u.id DESC");
           
            List<AuNoSolicitudDiagnosticos> list = getEntityManager().createQuery(sql.toString()).getResultList();
            int posicion = 0;
            for (AuNoSolicitudDiagnosticos diagnostico : list) {
                obj.add(castEntidadNegocioLargo(diagnostico, posicion));
                posicion++;
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
    public void insertarDiagnosticos(List<AuNoSolicitud> listaNegocio) throws ClassNotFoundException, SQLException {
        int contadorInserts = 0;
        StringBuilder stringBuilder = new StringBuilder(10000000);
        int cantidadLista = 0;
        String columnas = "au_no_solicitudes_id,";
        columnas += "principal,";
        columnas += "ma_diagnosticos_id,";
        columnas += "ma_diagnosticos_codigo,";
        columnas += "ma_diagnosticos_valor,";
        columnas += "alto_costo,";
        columnas += "usuario_crea,";
        columnas += "terminal_crea,";
        columnas += "fecha_hora_crea";
        String query = "INSERT INTO au_no_solicitud_diagnosticos (" + columnas + ") VALUES ";
        stringBuilder.append(query);
        cantidadLista = listaNegocio.stream().map(auNoSolicitud -> auNoSolicitud.getListAuNoSolicitudDiagnostico().size())
                .reduce(cantidadLista, Integer::sum);
        for (AuNoSolicitud auNoSolicitud : listaNegocio) {
            for (AuNoSolicitudDiagnostico obj : auNoSolicitud.getListAuNoSolicitudDiagnostico()) {
                contadorInserts++;
                stringBuilder.append("(");
                stringBuilder.append("").append(auNoSolicitud.getId()).append(",");
                stringBuilder.append("b'").append(obj.isPrincipal() ? "1": "0").append("',");
                stringBuilder.append("").append(obj.getMaDiagnosticosId()).append(",");
                stringBuilder.append("'").append(obj.getMaDiagnosticosCodigo()).append("',");
                stringBuilder.append("'").append(obj.getMaDiagnosticosValor()).append("',");
                stringBuilder.append("b'").append(obj.isAltoCosto() ? "1":"0").append("',");
                stringBuilder.append("'").append(obj.getUsuarioCrea()).append("',");
                stringBuilder.append("'").append(obj.getTerminalCrea()).append("',");
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
                    } catch (SQLException ex) {
                        System.out.println("Error insertando diagnosticos: " + ex.toString());
                    } catch (java.lang.Exception ex) {
                        Logger.getLogger(AuNoSolicitudDiagnosticoServicio.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        //stringBuilder = new StringBuilder(10000000);
                        //query = "insert into au_anexo3_diagnosticos (" + columnas + ") values ";
                        //stringBuilder.append(query);
                        if(ps != null){
                            ps.close();
                        }
                        if(conn != null){
                            conn.close();
                        }
                    }
                    //TODO: Insertar detalles
                    //insertarDetalles;
                }
            }
        }
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
    
    public static AuNoSolicitudDiagnostico castEntidadNegocioCorto(AuNoSolicitudDiagnosticos ent) {
        AuNoSolicitudDiagnostico obj = new AuNoSolicitudDiagnostico();
        obj.setId(ent.getId());
        obj.setPrincipal(ent.getPrincipal());
        if(ent.getAuNoSolicitudesId() != null){
            AuNoSolicitud prestador = new AuNoSolicitud();
            prestador.setId(ent.getAuNoSolicitudesId().getId());
            obj.setAuNoSolicitudesId(prestador);
        }
        obj.setMaDiagnosticosId(ent.getMaDiagnosticosId());
        obj.setMaDiagnosticosCodigo(ent.getMaDiagnosticosCodigo());
        obj.setMaDiagnosticosValor(ent.getMaDiagnosticosValor());
        obj.setAltoCosto(ent.getAltoCosto());
        
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }

    public static AuNoSolicitudDiagnostico castEntidadNegocioLargo(AuNoSolicitudDiagnosticos ent) {
        AuNoSolicitudDiagnostico obj = new AuNoSolicitudDiagnostico();
        obj.setId(ent.getId());
        obj.setPrincipal(ent.getPrincipal());
        
        if(ent.getAuNoSolicitudesId() != null){
            AuNoSolicitud prestador = new AuNoSolicitud();
            prestador.setId(ent.getAuNoSolicitudesId().getId());
            obj.setAuNoSolicitudesId(prestador);
        }
        obj.setMaDiagnosticosId(ent.getMaDiagnosticosId());
        obj.setMaDiagnosticosCodigo(ent.getMaDiagnosticosCodigo());
        obj.setMaDiagnosticosValor(ent.getMaDiagnosticosValor());
        obj.setAltoCosto(ent.getAltoCosto());

        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        return obj;
    }
    
    public static AuNoSolicitudDiagnostico castEntidadNegocioLargo(AuNoSolicitudDiagnosticos ent, int posicion) {
        AuNoSolicitudDiagnostico obj = new AuNoSolicitudDiagnostico();
        obj.setId(ent.getId());
        obj.setPosicion(posicion);
        obj.setPrincipal(ent.getPrincipal());
        
        if(ent.getAuNoSolicitudesId() != null){
            AuNoSolicitud prestador = new AuNoSolicitud();
            prestador.setId(ent.getAuNoSolicitudesId().getId());
            obj.setAuNoSolicitudesId(prestador);
        }
        obj.setMaDiagnosticosId(ent.getMaDiagnosticosId());
        obj.setMaDiagnosticosCodigo(ent.getMaDiagnosticosCodigo());
        obj.setMaDiagnosticosValor(ent.getMaDiagnosticosValor());
        obj.setAltoCosto(ent.getAltoCosto());

        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        return obj;
    }
    
    public static AuNoSolicitudDiagnosticos castNegocioEntidad(AuNoSolicitudDiagnostico obj) {
        AuNoSolicitudDiagnosticos ent = new AuNoSolicitudDiagnosticos();
        ent.setId(obj.getId());
        if(obj.getAuNoSolicitudesId() != null){
            AuNoSolicitudes auNoSolicitudes = new AuNoSolicitudes();
            auNoSolicitudes.setId(obj.getAuNoSolicitudesId().getId());
            ent.setAuNoSolicitudesId(auNoSolicitudes);
        }
        ent.setPrincipal(obj.isPrincipal());
        ent.setMaDiagnosticosId(obj.getMaDiagnosticosId());
        ent.setMaDiagnosticosCodigo(obj.getMaDiagnosticosCodigo());
        ent.setMaDiagnosticosValor(obj.getMaDiagnosticosValor());
        ent.setAltoCosto(obj.isAltoCosto());
        //Auditoria
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }
}
