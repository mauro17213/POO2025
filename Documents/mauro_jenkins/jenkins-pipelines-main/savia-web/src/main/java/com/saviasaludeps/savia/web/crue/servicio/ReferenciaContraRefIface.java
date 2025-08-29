/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.crue.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9Gestion;
import com.saviasaludeps.savia.dominio.maestro.MaRelacion;
import com.saviasaludeps.savia.web.crue.bean.ReferenciaContraRefBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelServiciosHabilitacionBean;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface ReferenciaContraRefIface {

    /**
     * Método de acciones central
     *
     * @param bean
     */
    void Accion(ReferenciaContraRefBean bean);

    /**
     * Método para cargar inicial del Bean
     *
     * @param bean
     */
    void cargaInicial(ReferenciaContraRefBean bean);

    void listarAfiliado(ReferenciaContraRefBean bean);

    void listarIPS(ReferenciaContraRefBean bean);

    void consultarProfesional(ReferenciaContraRefBean bean);

    void listarGestionar(ReferenciaContraRefBean bean);

    void listarTransporte(ReferenciaContraRefBean bean);
    
    void listarAdjuntos(ReferenciaContraRefBean bean);

    void consultarTransporte(ReferenciaContraRefBean bean);
    
    Maestro consultarMaestroTipoDocumento(ReferenciaContraRefBean bean);
    
    Maestro consultarMaestroGestionEstado(int maeEstadoId, ReferenciaContraRefBean bean);

    /**
     * consulta los motivos por estado
     *
     * @param bean
     * @return 
     */
    List<Maestro> motivosGestionEstado(ReferenciaContraRefBean bean);

    /**
     * Consulta clase transporte por tipo de transporte
     *
     * @param bean
     */
    void claseTransporteTipo(ReferenciaContraRefBean bean);

    /**
     * asigna afiliado activo con datos completos
     *
     * @param idAfiliado
     * @param bean
     */
    void asignarAfiliado(int idAfiliado, ReferenciaContraRefBean bean);
    
     /**
     * asigna afiliado activo con datos completos
     *
     * @param bean
     * @return 
     */
    boolean consultarEstadoAfiliado(ReferenciaContraRefBean bean);
     /**
     * consulta ultima gestion de un anexo 9
     *
     * @param anexo9
     * @return 
     */
    RefAnexo9Gestion consultarUltimaGestionAnexo9(RefAnexo9 anexo9);
    /**
     * consulta todas la ultima gestion tipo direccionamiento de un anexo9
     *
     * @param anexo9
     * @return 
     */
    RefAnexo9Gestion consultarUltimoDireccionamiento(RefAnexo9 anexo9);
    
    /**
     * consulta todas la ultima gestion tipo direccionamiento de un anexo9
     *
     * @param servicioHabilitacion
     * @return 
     */
    List<MaRelacion> consultarServiciosHabilitacionId(SelServiciosHabilitacionBean servicioHabilitacion);
}
