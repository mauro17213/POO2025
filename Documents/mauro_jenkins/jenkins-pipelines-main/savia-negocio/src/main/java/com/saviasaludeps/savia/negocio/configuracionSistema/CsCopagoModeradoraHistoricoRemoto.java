/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.configuracionSistema;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.configuracionSistema.CsCopagoModeradoraHistorico;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author sgiraldo
 */
public interface CsCopagoModeradoraHistoricoRemoto {

    /**
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<CsCopagoModeradoraHistorico> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * calcula valor de valorProyectado dependiendo de moderadoraCopago
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    BigDecimal consultarTotalCopagoModeradora(ParamConsulta paramConsulta) throws Exception;

    /**
     * Inserta el historico de copago o cuota moderadora
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(CsCopagoModeradoraHistorico obj) throws Exception;

    /**
     * Actualiza el valor valor_proyectado cuando se anula la autorizacion
     *
     * @param obj
     * @throws Exception
     */
    void actualizarValorProyectado(AuAnexo4 obj) throws Exception;

    /**
     * valor total del afiliado copago
     *
     * @param idAfiliado
     * @param anio
     * @return
     * @throws Exception
     */
    int valorTotalAfiliadoCopago(int idAfiliado, int anio) throws Exception;

    /**
     * Actualiza valor valorModeradora permiso especial
     *
     * @param obj
     * @throws Exception
     */
    void actualizarValorCopago(AuAnexo4 obj) throws Exception;

}
