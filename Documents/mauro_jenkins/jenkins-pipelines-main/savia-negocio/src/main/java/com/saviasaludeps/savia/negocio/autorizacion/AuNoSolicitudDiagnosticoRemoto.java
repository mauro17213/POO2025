/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.negocio.anticipo.*;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipo;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitud;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudDiagnostico;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface AuNoSolicitudDiagnosticoRemoto {

    /**
     * Método para consultar un AuNoSolicitudDiagnostico por ID
     *
     * @param id
     * @return (AuNoSolicitudDiagnostico) cargado
     * @throws java.lang.Exception
     */
    AuNoSolicitudDiagnostico consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo AuNoSolicitudDiagnostico
     *
     * @param per (AuNoSolicitudDiagnostico)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AuNoSolicitudDiagnostico per) throws Exception;

    /**
     * Método para actualizar la información de una AuNoSolicitudDiagnostico
     *
     * @param per (AuNoSolicitudDiagnostico)
     * @throws java.lang.Exception
     */
    void actualizar(AuNoSolicitudDiagnostico per) throws Exception;
    
    /**
     * Consultar lista de registros
     *
     * @param idAuNoSolicitudes
     * @return
     * @throws Exception
     */
    List<AuNoSolicitudDiagnostico> consultarDiagnosticoPorAuNosolicitudesId(int idAuNoSolicitudes) throws Exception;
    
    /**
     * Consultar lista de registros
     *
     * @param listaNegocio
     * @throws Exception
     */
    void insertarDiagnosticos(List<AuNoSolicitud> listaNegocio) throws Exception;
}
