/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.configuracionSistema;

import com.saviasaludeps.savia.dominio.configuracionSistema.CsTopeModeradora;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author iavenegas
 */
public interface CsTopeModeradoraRemoto {

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
    List<CsTopeModeradora> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    CsTopeModeradora consultar(int id) throws Exception;

    /**
     * retorna registros filtador por año
     * @param anio
     * @return
     * @throws Exception
     */
    List<CsTopeModeradora> consultarTopesByAnio(int anio) throws Exception;
}
