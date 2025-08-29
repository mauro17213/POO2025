/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.configuracionSistema;

import com.saviasaludeps.savia.dominio.configuracionSistema.CsTopeCopago;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author iavenegas
 */
public interface CsTopeCopagoRemoto {

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
    List<CsTopeCopago> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    CsTopeCopago consultar(int id) throws Exception;

    /**
     * retorna registros filtador por año
     * @param anio
     * @return
     * @throws Exception
     */
    List<CsTopeCopago> consultarTopesByAnio(int anio) throws Exception;
    
    /**
     *  Consultar el tope segun los valores entregados: para subisidiado aplica año, regimen y nivel sisben
     * para contributivo aplica año, regimen,  tipo de afiliado y categoria Ibc
     * @param agno
     * @param maeRegimenCodigo
     * @param maeTipoAfiliadoCodigo
     * @return
     * @throws Exception 
     */
    CsTopeCopago consultar(String agno, String maeRegimenCodigo, String maeNivelSisbenCodigo , String maeTipoAfiliadoCodigo, String categoriaIbc) throws Exception;
        
}
