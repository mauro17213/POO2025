/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.aseguramiento;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegRadicadoNovedad;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegRegistroNovedad;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.Date;
import java.util.List;

public interface NovedadAfiliadoRemoto {    
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    List<AsegRegistroNovedad> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    AsegRegistroNovedad consultar(int id) throws Exception;
    
    int insertar(AsegRegistroNovedad obj) throws Exception;
    
    void actualizar(AsegRegistroNovedad obj) throws Exception;
    
    AsegRegistroNovedad eliminar(int id) throws Exception;
    
    /**
     * Función para consultar la lista de RadicadoNovedades asociadas a un afiliado
     * @param id Id del Afiliado
     * @return
     * @throws Exception 
     */
    List<AsegRadicadoNovedad> consultarPorAfiliado(int id)throws Exception;
    
    AsegRegistroNovedad consultar(int idAfiliado, String estado, String codigoNovedad) throws Exception;
    
    AsegRegistroNovedad consultarPorReporteNovedad(int idAfiliado, Date fechaHoraCrea, String codigoNovedad) throws Exception;
    
    void actualizarNovedadesReporteAfiliadosNuevosMS (AsegRegistroNovedad novedad, Date fechaDesde, Date fechaHasta) throws Exception;
    
    void actualizarNovedadesReporteNovedadesNS (AsegRegistroNovedad novedad, Date fechaDesde, Date fechaHasta) throws Exception;
    
    void actualizarNovedadesReporteTrasladosS1 (AsegRegistroNovedad novedad, Date fechaDesde, Date fechaHasta) throws Exception;
    
    void actualizarNovedadesPorIdReporte (int idReporte) throws Exception;
    
    AsegRegistroNovedad consultarUltimoRegistroUsuarioInactivoPorAfiliado(int idAfiliado) throws Exception;
}
