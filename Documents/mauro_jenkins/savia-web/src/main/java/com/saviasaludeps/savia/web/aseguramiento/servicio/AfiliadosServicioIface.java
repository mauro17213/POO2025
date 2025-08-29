/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.servicio;
import com.saviasaludeps.savia.web.aseguramiento.bean.AfiliadosBean;

/**
 *
 * @author Jose Perez Hernandez
 */
public interface AfiliadosServicioIface {
    
    void Accion(AfiliadosBean bean);
    
    /**
     * Cargar lista 
     * @param bean 
     */
    void cargaInicial(AfiliadosBean bean);
    
    /**
     * 
     * @param bean 
     */
    void consultarSedesMunicipio(AfiliadosBean bean);
    
    /**
     * 
     * @param bean 
     */
    public void consultarAfiliadoExistente(AfiliadosBean bean);
    
    /**
     * 
     * @param bean 
     */
    public void consultarAfiliadoCabezaFamiliaVer(AfiliadosBean bean);
    
    /**
     * 
     * @param bean 
     */
    public void consultarAfiliadoCabezaFamilia(AfiliadosBean bean);
    
    /**
     * 
     * @param bean 
     */
    public void consultarAfiliadoCabezaFamiliaEditar(AfiliadosBean bean);
    
    /**
     * 
     * @param bean 
     */
    public void guardarAnexosNovedades(AfiliadosBean bean);
    
    /**
     * Genera el reporte de derechos y deberes
     * @param bean 
     */
    public void reporte016(AfiliadosBean bean);
    
    /**
     * Genera el reporte formulario afiliacion
     * @param bean 
     */
    public void reporteAfiliacion(AfiliadosBean bean);
    
    /**
     * 
     * @param bean 
     */
    public void consultarAfiliadosHomonimos(AfiliadosBean bean);
    
    /**
     * 
     * @param bean 
     */
    public void consultarAfiliadoExistenteDatosBasicos(AfiliadosBean bean);
    
    /**
     * Función para consultar el maestro relacion de tipo Afiliado, cada vez que
     * se modifique el campo regimen
     * @param bean 
     */
    public void consultarMaestroTipoAfiliadoPorRegimen(AfiliadosBean bean);
    /**
     * Función para consultar el maestro relacion de parentesco, cada vez que
     * se modifique el campo regimen
     * @param bean 
     */
    public void consultarMaestroParentescoPorRegimen(AfiliadosBean bean);
    /**
     * Función para consultar el maestro relacion de Etnia, cada vez que
     * se modifique el campo Grupo Poblacional
     * @param bean 
     */
    public void consultarMaestroEtniaPorGrupoPoblacional(AfiliadosBean bean);
    /**
     * Función para consultar el maestro relacion de grupo Sisben, cada vez que
     * se modifique el campo Grupo Poblacional
     * @param bean 
     */
    public void consultarMaestroGrupoSisbenPorGrupoPoblacional(AfiliadosBean bean);
    /**
     * Función para consultar el maestro relacion de Grupo Sisben, cada vez que
     * se modifique el campo Grupo Poblacional y el campo Grupo Sisben
     * @param bean 
     */
    public void consultarMaestroNivelSisbenPorGrupoPoblacionalYGrupoSisben(AfiliadosBean bean);
    /**
     * Función para consultar el maestro relacion de Estado, cada vez que
     * se modifique el campo Estado y el campo Regimén (Funcionalidad Editar)
     * @param bean 
     */
    public void consultarMaestroEstadoPorEstadoYRegimen(AfiliadosBean bean);
    
    /**
     * Función para consultar el maestro relacion de Causa Novedad, cada vez que
     * se modifique el campo Estado (Funcionalidad Editar)
     * @param bean 
     */
    public void consultarMaestroCausaNovedadPorEstado(AfiliadosBean bean);
    
    /**
     * Función para consultar el maestro relacion de Comunidad Etnica,cada vez que se modifique
     * el campo etnia
     * @param bean 
     */
    public void consultarMaestroComunidadEtnicaPorEtnia(AfiliadosBean bean);
    
    /**
     * Función para consultar el maestro relacion de Metodología Grupo Poblacional,cada vez que se modifique
     * el campo Grupo Poblacional
     * @param bean 
     */
    public void consultarMaestroMetodGrupoPoblacionalPorGrupoPob(AfiliadosBean bean);
    
    /**
     * Función para consultar el maestro relación de SubGrupo Sisben, cada vez que se modifique
     * el campo Grupo Sisben
     * @param bean 
     */
    public void consultarMaestroSubGrupoSisbenPorGrupoSisben(AfiliadosBean bean);
    
    /**
     * Función para consultar el maestro relación de Porcentaje Distribucion, cada vez que se modifique
     * el campo SubGrupo Sisben
     * @param bean 
     */
    public void consultarMaestroPorcentajeDistribucionPorSubGrupoSisben(AfiliadosBean bean);
    
    /**
     * Función para consultar el valor de la contribución solidaria cuando se seleccione
     * o se tenga un valor de porcentaje definido para el afiliado
     * @param bean 
     */
    public void consultarValorContribucionSolidaria(AfiliadosBean bean);
    
    /**
     * Función para consultar el listado de Barrios pertenecientes a la ciudad de Medellín ( Inicialmente cargados)
     * @param bean 
     */
    public void consultarListaBarriosMedellin(AfiliadosBean bean);
    
}
