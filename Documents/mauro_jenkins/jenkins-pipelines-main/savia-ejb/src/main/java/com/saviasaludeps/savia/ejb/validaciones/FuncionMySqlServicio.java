package com.saviasaludeps.savia.ejb.validaciones;

import com.saviasaludeps.savia.ejb.utilidades.ConnectionManager1;
import com.saviasaludeps.savia.ejb.utilidades.ConnectionManager2;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaA4AutomaticoDTO;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaCopagoDTO;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaDTO;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaGrupoAsignado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FuncionMySqlServicio {

    public Connection getConnTransaccional() throws SQLException, ClassNotFoundException {
        //BASE DE DATOS 01
        return ConnectionManager1.getInstance().getConnection();
//        //BASE DE DATOS 02
//        return ConnectionManager2.getInstance().getConnection();
    }

    public Connection getConnValidacion() throws SQLException, ClassNotFoundException {
        //BASE DE DATOS 01
        return ConnectionManager1.getInstance().getConnection();
//        //BASE DE DATOS 02
//        return ConnectionManager2.getInstance().getConnection();
    }

    //Ejecuta un query
    public ValidaRespuestaDTO ejecutarFuncionAu(String strQuery) throws ClassNotFoundException, SQLException {
        ValidaRespuestaDTO neg = new ValidaRespuestaDTO();
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            conn = getConnValidacion();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(strQuery);
            while (rs.next()) {
                String strSplit = rs.getString(1);
                String[] strSplitPartes = strSplit.split("\\|");
                neg.setCodigo(Integer.parseInt(strSplitPartes[0]));
                neg.setMensaje(strSplitPartes[1]);
                if (strSplitPartes.length == 3) {
                    neg.setMensaje(strSplitPartes[2]);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
            System.out.println("Error ejecutando validación de funciones Autorizaciones: " + strQuery + " error: " + ex.toString());
        } finally {
            closeStatement(stmt);
            closeResultSet(rs);
            closeConnection(conn);
        }
        return neg;
    }

    //Ejecuta un query
    public ValidaRespuestaCopagoDTO ejecutarFuncionCopago(String strQuery) throws ClassNotFoundException, SQLException {
        ValidaRespuestaCopagoDTO neg = new ValidaRespuestaCopagoDTO();
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            conn = getConnValidacion();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(strQuery);
            while (rs.next()) {
                String strSplit = rs.getString(1);
                String[] strSplitPartes = strSplit.split("\\|");
                neg.setCodigo(Double.parseDouble(strSplitPartes[0]));
                neg.setValor(strSplitPartes[1]);
                neg.setCodigo3(Double.parseDouble(strSplitPartes[2]));
                neg.setCodigo4(Double.parseDouble(strSplitPartes[3]));
                neg.setCodigo5(Double.parseDouble(strSplitPartes[4]));
                neg.setCodigo6(Double.parseDouble(strSplitPartes[5]));
                neg.setCodigo7(Double.parseDouble(strSplitPartes[6]));
                if(strSplitPartes.length > 7){
                    neg.setMotivoExentoCobro(strSplitPartes[7]);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            neg.setCodigo(99);
            System.out.println("Error ejecutando validación de funciones Autorizaciones: " + strQuery + " error: " + ex.toString());
        } finally {
            closeStatement(stmt);
            closeResultSet(rs);
            closeConnection(conn);
        }
        return neg;
    }

    public ValidaRespuestaA4AutomaticoDTO ejecutarAprobacionAutomatica(String strQuery) throws ClassNotFoundException, SQLException {
        ValidaRespuestaA4AutomaticoDTO neg = new ValidaRespuestaA4AutomaticoDTO();
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            conn = getConnValidacion();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(strQuery);
            while (rs.next()) {
                String strSplit = rs.getString(1);
                String[] strSplitPartes = strSplit.split("\\|");
                if (strSplitPartes.length == 5) {
                    neg.setCodigo(Integer.parseInt(strSplitPartes[0]));
                    neg.setMensaje(strSplitPartes[1]);
                    try {
                        neg.setCntContratoAutorizadoSedeId(Integer.parseInt(strSplitPartes[2]));
                        neg.setCntPrestadorAutorizadoSedeId(Integer.parseInt(strSplitPartes[3]));
                    } catch (NumberFormatException ex) {
                        neg.setCodigo(1);
                        neg.setCntContratoAutorizadoSedeId(0);
                        neg.setCntPrestadorAutorizadoSedeId(0);
                    }
                    neg.setValorContratado((strSplitPartes[4]));
                } else {
                    neg.setCodigo(Integer.parseInt(strSplitPartes[0]));
                    neg.setMensaje(strSplitPartes[1]);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            neg.setCodigo(99);
            System.out.println("Error ejecutando validación de funciones Autorizaciones: " + strQuery + " error: " + ex.toString());
        } finally {
            closeStatement(stmt);
            closeResultSet(rs);
            closeConnection(conn);
        }
        return neg;
    }
    
    public ValidaRespuestaGrupoAsignado ejecutarAsignacionGrupo(String strQuery) throws ClassNotFoundException, SQLException {
        ValidaRespuestaGrupoAsignado neg = new ValidaRespuestaGrupoAsignado();
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            conn = getConnValidacion();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(strQuery);
            while (rs.next()) {
                String strSplit = rs.getString(1);
                String[] strSplitPartes = strSplit.split("\\|");
                if (strSplitPartes.length == 3) {
                    neg.setCodigo(Integer.parseInt(strSplitPartes[0]));
                    neg.setMensaje(strSplitPartes[1]);
                    try {
                        neg.setAuGrupoId(Integer.parseInt(strSplitPartes[2]));
                    } catch (NumberFormatException ex) {
                        neg.setAuGrupoId(0);
                        neg.setAuGrupoUsuarioId(0);
                        neg.setMensaje("Error: " + ex.toString());
                        neg.setCodigo(ValidaRespuestaGrupoAsignado.CODIGO_ESTADO_ERROR);
                    }
                } else {
                    neg.setCodigo(Integer.parseInt(strSplitPartes[0]));
                    neg.setMensaje(strSplitPartes[1]);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            neg.setCodigo(99);
            System.out.println("Error ejecutando validación de funciones Autorizaciones: " + strQuery + " error: " + ex.toString());
        } finally {
            closeStatement(stmt);
            closeResultSet(rs);
            closeConnection(conn);
        }
        return neg;
    }

//METODOS PARA CERRAR OBJETOS CONECTADOS A LA DB
    public static void closeResultSet(ResultSet rs) throws ClassNotFoundException, SQLException {
        if (rs != null) {
            rs.close();
        }
    }

    public static void closePreparedStatement(PreparedStatement ps) throws ClassNotFoundException, SQLException {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando la conexión del prepared statement");
            }
        }
    }

    public static void closeConnection(Connection conn) throws ClassNotFoundException, SQLException {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando la conexión");
            }
        }
    }

    public static void closeStatement(Statement stmt) throws ClassNotFoundException, SQLException {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando el statement");
            }
        }
    }

}
