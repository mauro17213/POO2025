package com.saviasaludeps.savia.ejb.servicios.webservices;

import com.saviasaludeps.savia.ejb.utilidades.ConnectionManager1;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaCopagoDTO;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlServicio {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        //BASE DE DATOS 01
        Connection conn = ConnectionManager1.getInstance().getConnection();
//        //BASE DE DATOS 02
//        Connection conn = ConnectionManager2.getInstance().getConnection();
        return conn;
    }

    //Ejecuta un query
    public static ValidaRespuestaDTO ejecutarFuncionAu(String strQuery) throws ClassNotFoundException, SQLException {
        ValidaRespuestaDTO neg = new ValidaRespuestaDTO();
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(strQuery);
            while (rs.next()) {
                String strSplit = rs.getString(1);
                String[] strSplitPartes = strSplit.split("\\|");
                neg.setCodigo(Integer.parseInt(strSplitPartes[0]));
                neg.setMensaje(strSplitPartes[1]);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
            System.out.println("Error ejecutando validación de funciones Autorizaciones: " + ex.toString());
        } finally {
            closeStatement(stmt);
            closeResultSet(rs);
            closeConnection(conn);
        }
        return neg;
    }

    public static ValidaRespuestaCopagoDTO ejecutarFuncionCopago(String strQuery) throws ClassNotFoundException, SQLException {
        ValidaRespuestaCopagoDTO neg = new ValidaRespuestaCopagoDTO();
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(strQuery);
            while (rs.next()) {
                String strSplit = rs.getString(1);
                String[] strSplitPartes = strSplit.split("\\|");
                neg.setCodigo(Integer.parseInt(strSplitPartes[0]));
                neg.setValor(strSplitPartes[1]);
                neg.setCodigo3(Integer.parseInt(strSplitPartes[2]));
                neg.setCodigo4(Integer.parseInt(strSplitPartes[3]));
                neg.setCodigo5(Integer.parseInt(strSplitPartes[4]));
                neg.setCodigo6(Integer.parseInt(strSplitPartes[5]));
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
