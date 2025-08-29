/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.atencionusuario.servicio;
import com.saviasaludeps.savia.web.atencionusuario.bean.AusCasoBean;
import com.saviasaludeps.savia.web.atencionusuario.bean.AusPersonaBean;
import java.util.Date;
import java.util.List;

/**
 *
 * @author raul-palacios
 */
public interface AusCasoServicioIface {
    
    void Accion(AusCasoBean bean);
    void cargaInial(AusCasoBean bean);   
    void listarSedes(AusCasoBean bean);
    void listarMotivoPorAmbito(AusCasoBean bean);
    void modificarInfoBasicaCaso(AusCasoBean bean);
    List<Date> obtenerFechas(Date fecha);
    void contarVencidos(AusCasoBean bean);
    void consultarPersona(AusPersonaBean bean);
    void consultarPersonaAfiliada(AusPersonaBean bean);
    void crearServicioCamposObligatoriosParaEstados(AusCasoBean bean);
    void crearServicioCamposObligatoriosParaTipoAdministracion(AusCasoBean bean);
    void editarServicioCamposObligatorios(AusCasoBean bean);
    void validarCamposObligatorios(AusCasoBean bean);
    
    /**
     * Se encarga de consultar los usuario dependiendo el estado del servicio
     * @param bean 
     */
    void consultarUsuarioPorEstados(AusCasoBean bean);
    
    /**
     * Se encarga de consultar los usuario 
     * @param bean 
     */
    void consultarUsuario(AusCasoBean bean);
    
    /**
     * Se encarga de consultar El maestro tipo motivo del caso dependiendo la relacion del padre
     * @param bean 
     */
    void maestroTipoMotivo(AusCasoBean bean);
    
    /**
     * Se encarga de consultar El maestro sub tipo motivo del caso dependiendo la relacion del padre 
     * @param bean 
     */
    void maestroSubTipoMotivo(AusCasoBean bean);
    
    /**
     * Se consulta una lista de casos en gestión que pertenezcan al usuario
     * @param bean 
     */
    void consultarCasosTecnologia(AusCasoBean bean);
}