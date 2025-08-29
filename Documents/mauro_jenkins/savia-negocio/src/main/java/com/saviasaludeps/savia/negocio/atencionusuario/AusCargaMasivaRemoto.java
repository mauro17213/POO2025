/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.atencionusuario;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCargaMasiva;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCaso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jperezn
 */
public interface AusCargaMasivaRemoto {

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta Parametros de la consulta de registros
     * @return Lista de registros consultados
     * @throws Exception
     */
    List<AusCargaMasiva> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Inserta registro carga masiva
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AusCargaMasiva obj) throws Exception;

    /**
     * actualiza todo el registro carga masiva
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AusCargaMasiva obj) throws Exception;
    
     /**
     * actualiza todo el registro carga masiva
     *
     * @param obj
     * @throws Exception
     */
    void actualizarObservacion(AusCargaMasiva obj) throws Exception;
    
    /**
     * actualiza todo el registro carga masiva
     *
     * @param obj
     * @throws Exception
     */
    void actualizarEstadoProcesado(AusCargaMasiva obj) throws Exception;
    
    /**
     * Actualizar nombre de archivo almacenado
     *
     * @param obj
     * @throws Exception
     */
    void actualizarArchivo(AusCargaMasiva obj) throws Exception;
    
    /**
     * Actualizar nombre de archivo almacenado
     *
     * @param obj
     * @throws Exception
     */
    void actualizarArchivoResultado(AusCargaMasiva obj) throws Exception;
    
    /**
     * consulta carga masiva
     *
     * @param id
     * @return
     * @throws Exception
     */
    AusCargaMasiva consultar(int id) throws Exception;

    /**
     * actualiza la fecha fin y estado.
     *
     * @param obj
     * @throws Exception
     */
    void actualizarFechaMasEstado(AusCargaMasiva obj) throws Exception;

    /**
     * Permite consultar datos asegAfiliado :
     * paramConsulta.getParametroConsulta1(): String numeros documentos opcional
     * separados por coma ej: 1,3,4 paramConsulta.getParametroConsulta2():
     * String id afiliadoAseg ej: 1,2,3
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<AsegAfiliado> consultarDatosAsegAfiliadoLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite consultar AsegAfiliadoLista
     *
     * @param paramConsulta: paramConsulta.getParametroConsulta1(): String
     * numeros documentos opcional separados por coma ej: 1,3,4
     * @return
     * @throws Exception
     */
    List<AsegAfiliado> consultarUltimosRegistroAsegAfiliadoLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Funcion que pemite obtener casos que pertenecen a numeros de radicado
     *
     * @param paramConsulta: paramConsulta.getParametroConsulta1(): Sting numero
     * radicado; ej: 2222,oko3333
     * @return
     * @throws Exception
     */
    List<AusCaso> consultarCasosPorRadicadoRegistrado(ParamConsulta paramConsulta) throws Exception;

    /**
     * Funcion que pemite obtener casos que pertenecen a numeros de radicado
     *
     * @param numeroMaximoRegistros
     * @return
     * @throws Exception
     */
    List<AusCargaMasiva> consulltarPorEstadoCola(Integer numeroMaximoRegistros) throws Exception;
}
