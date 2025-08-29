/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.aseguramiento;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegPortabilidad;
import com.saviasaludeps.savia.dominio.aseguramiento.ReportePortabilidad;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface PortabilidadRemoto {

    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    List<AsegPortabilidad> consultarLista(ParamConsulta paramConsulta) throws Exception;

    AsegPortabilidad consultar(int id) throws Exception;

    int insertar(AsegPortabilidad obj) throws Exception;

    void actualizar(AsegPortabilidad obj) throws Exception;

    AsegPortabilidad eliminar(int id) throws Exception;

    /**
     * Función para consultar la lista de Portabilidades asociadas a un afiliado
     *
     * @param id Id del Afiliado
     * @return
     * @throws Exception
     */
    List<AsegPortabilidad> consultarPorAfiliado(int id) throws Exception;

    AsegAfiliado consultarAfiliado(Integer tipodocumento, String numeroDocumento) throws Exception;

    ReportePortabilidad reportePortabilidad(String id);
    
    ReportePortabilidad reporteUtimaPortabilidadAfiliado(String id);

    AsegPortabilidad consultarPorAfiliadoIdPortabilidad(int idAfiliado, int idPortabilidad) throws Exception;
    
    AsegPortabilidad consultarPortabilidadAfiliadoVigente(int idAfiliado) throws Exception;
    
    List<CntPrestadorSede> municipioAplicaPortabilidad(int idUbicacion) throws java.lang.Exception;
}
