/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Item;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuAnexo4ItemRemoto {

    /**
     * Consulta la cantidad de datos de parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta la lista dado los parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<AuAnexo4Item> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta la lista dado los parametros
     *
     * @param idAnexo4
     * @return
     * @throws Exception
     */
    List<AuAnexo4Item> consultarListaByIdAnexo4(int idAnexo4) throws Exception;

    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AuAnexo4Item consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AuAnexo4Item obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AuAnexo4Item obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AuAnexo4Item eliminar(int id) throws Exception;

    /**
     * consulta AuAnexo4Item por id anexo4 y codigo tecnologia
     * @param idAnexo
     * @param codigo
     * @return
     * @throws Exception
     */
    AuAnexo4Item consultarPorIdAnexo4PorCodigo(int idAnexo, String codigo) throws Exception;

    /**
     * Eliminar los Items del anexo4 desde el id del adexo4
     *
     * @param idAnexo4
     * @return
     * @throws Exception
     */
    AuAnexo4Item eliminarPorIdAnexo4(int idAnexo4) throws Exception;

    /**
     * Consulta la ultima autorizacion de una tecnologia para un afiliado
     *
     * @param idItem
     * @param idTipoItem
     * @param idAfiliado
     * @return
     * @throws Exception
     */
    AuAnexo4Item consultarUltimoPorItem(int idItem, int idTipoItem, int idAfiliado) throws Exception;
    
    /**
     * Consulta las autorizaciones de una tecnologia para un afiliado
     *
     * @param idItem
     * @param idTipoItem
     * @param idAfiliado
     * @return
     * @throws Exception
     */
    List<AuAnexo4Item> consultarPorItems(int idItem, int idTipoItem, int idAfiliado) throws Exception;
    
    /**
     * consulta AuAnexo4Item por id anexo4 y codigo agrupador de medicamento
     * @param idAnexo
     * @param codigoAgrupador
     * @return
     * @throws Exception
     */
    AuAnexo4Item consultarPorIdAnexo4PorAgrupadorMedicamento(int idAnexo, String codigoAgrupador) throws Exception;
    
    
    /**
     * Consulta la lista dado los parametros
     *
     * @param idContratoDetalle
     * @param idTecnologia
     * @return
     * @throws Exception
     */
    List<AuAnexo4Item> consultarAutorizacionByTecnologiaWithContratoDetalle(int idContratoDetalle, int idTecnologia) throws Exception;
    
    /**
     * Consulta la lista dado los parametros
     *
     * @param idContratoDetalle
     * @param idTecnologia
     * @return
     * @throws Exception
     */
    List<AuAnexo4Item> consultarAutorizacionByFechasWithContratoDetalle(int idContratoDetalle, int idTecnologia) throws Exception;
    
    /**
     * Consulta la lista dado los parametros
     *
     * @param idContratoDetalle
     * @param idTecnologia
     * @param idAsegAfiliadosId
     * @return
     * @throws Exception
     */
    List<AuAnexo4Item> consultarAutorizacionByTecnologiaWithContratoDetalleAsegAfiliado(int idContratoDetalle, int idTecnologia, int idAsegAfiliadosId) throws Exception;
}
