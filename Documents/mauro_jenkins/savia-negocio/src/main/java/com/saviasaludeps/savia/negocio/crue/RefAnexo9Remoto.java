/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.crue;

import com.saviasaludeps.savia.dominio.crue.RefAnexo9;
import com.saviasaludeps.savia.dominio.crue.ReporteReferencia;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface RefAnexo9Remoto {

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
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<RefAnexo9> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta un registro de RefAnexos9 por id
     *
     * @param id
     * @return RefAnexo9
     * @throws Exception
     */
    RefAnexo9 consultar(int id) throws Exception;

    int insertar(RefAnexo9 obj) throws Exception;

    void actualizar(RefAnexo9 obj) throws Exception;
    
    void actualizarFechaHoraInicioGestion(RefAnexo9 obj) throws Exception;
    
    void actualizarFechaHoraFinGestion(RefAnexo9 obj) throws Exception;
    
    void actualizarUltimaGestion(RefAnexo9 obj) throws Exception;
    
    void actualizarUltimaEvolucion(RefAnexo9 obj) throws Exception;
    
    RefAnexo9 eliminar(int id) throws Exception;

    void actualizarEstado(RefAnexo9 obj) throws Exception;

    void actualizarAuditoriaModifica(RefAnexo9 obj) throws Exception;

    /**
     * consulta anexos 9 por afiliado y estados prestablecidos
     *
     * @param idAfiliado
     * @param estados
     * @return
     * @throws java.lang.Exception
     */
    List<RefAnexo9> anexos9ByAfiliadoByEstados(int idAfiliado, String estados) throws Exception;

//    ReporteReferencia reporteReferencia(String id);
    /**
     * Consulta de cantidad de registros en una lista por afiliado
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadListaPorAfiliado(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros por afiliado
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<RefAnexo9> consultarListaPorAfiliado(ParamConsulta paramConsulta) throws Exception;
}
