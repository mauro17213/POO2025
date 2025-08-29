/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.generico;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Carga;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3CargaDetalle;
import com.saviasaludeps.savia.dominio.autorizacion.AuEntregaCarga;
import com.saviasaludeps.savia.dominio.autorizacion.AuEntregaCargaDetalle;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author iavenegas
 */
public interface AuEntregaCargaMasivaRemoto {

    void cargaMasivaEntregas(AuEntregaCarga carga) throws Exception;
    
    AuEntregaCarga consultarSiguienteCarga(int estado) throws Exception;
    
    AuEntregaCarga consultarCarga(int idCarga) throws Exception;
    
    boolean actualizarEstado(int estado, int id) throws Exception;
    
    List<AuEntregaCargaDetalle> consultarDetalles(int id) 
            throws ClassNotFoundException, SQLException;
    
}
