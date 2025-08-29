package com.saviasaludeps.savia.ejb.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Copyright (c) 1998 by Gefion software.
 *
 * Permission to use, copy, and distribute this software for
 * NON-COMMERCIAL purposes and without fee is hereby granted
 * provided that this copyright notice appears in all copies.
 *
 */

/** 
 * Esta clase permite el acceso a uno o varios pools de conexion, que son definidos en
 * un archivo de Propiedades (Pool.properties). Un cliente puede conectarse al pool a 
 * traés del metodo getInstance(), lo cual le da la posibilidad de registrar y
 * comprobar conexiones del pool. Para desconectarse completamente, el cliente debe  
 * llamar al metodo release() para cerrar todas las conexiones abiertas y dejar el pool  
 * "limpio".
 */
public class ConnectionManager1{
    
    private static ConnectionManager1 instance=null;
    private static String url = "";
    private static String usr = "";
    private static String pass = "";
    
   /**
     * Retorna una instancia de conexion, creandola si es la primera
     * vez que el metodo es llamado.
     *
     * @return DBConnectionManager La instancia.
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static ConnectionManager1 getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null){         
            instance = new ConnectionManager1();
            url = PropApl.getInstance().get(PropApl.BD1_CONEXION_URL);
            usr = PropApl.getInstance().get(PropApl.BD1_CONEXION_USUARIO);
            pass = PropApl.getInstance().get(PropApl.BD1_CONEXION_CONTRASENA);
        }
        return instance;
    }
    
    /**
     * Carga el archivo de propiedades Pool.properties e inicializa las instancias
     * con dichos valores.
     */
    private ConnectionManager1() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");        
    }
    
    /**
     * Retorna una conexion abierta. Si no hay conexion disponible, y no se ha 
     * alcanzado el numero maximo de conexiones, se crea una nueva conexion.
     *
     * @return Connection Nombre de la conexion
     * @throws java.sql.SQLException
     */
    public Connection getConnection() throws SQLException{
        Connection conn;
        conn = DriverManager.getConnection(url, usr, pass);
        return conn;
    }
}