package com.saviasaludeps.savia.web.tutela.servicio;

import com.saviasaludeps.savia.dominio.tutela.TuDiagnostico;
import com.saviasaludeps.savia.dominio.tutela.TuGrupoUsuario;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaRespuesta;
import com.saviasaludeps.savia.web.tutela.bean.TuTutelaBean;
import java.util.Date;
import java.util.List;

public interface TuTutelaServicioIface {

    void Accion(TuTutelaBean bean);

    /**
     * Cargar lista Tutelas
     *
     * @param bean
     */
    void cargaInial(TuTutelaBean bean);

    void consultarPersona(TuTutelaBean bean);

    void consultarPersonaAfiliada(TuTutelaBean bean);

    void consultarGrupos(TuTutelaBean bean);

    void consultarGruposAdicional(TuTutelaBean bean);

    /**
     * Función para consultar el maestro relacion de Grupo estado tutela, cada
     * vez que se modifique estado actual de la tutela.
     *
     * @param bean
     */
    void consultarEstadoTutela(TuTutelaBean bean);

    void consultarTodosEstadoTutela(TuTutelaBean bean);

    void listarJuzgados(TuTutelaBean bean);

    void listarUsuarios(TuTutelaBean bean);

    void listarTipoSeguimiento(TuTutelaBean bean, Integer saberSiGestorOMedicoObogado);

    boolean consultarDiaHabil(Date fecha) throws Exception;

    List<TuGrupoUsuario> consultarUsuariosGrupos(TuTutelaBean bean);

    List<TuGrupoUsuario> consultarUsuariosGruposAdicional(TuTutelaBean bean);
    
    List<TuTutelaRespuesta> consultarTutelaPorDocumento(String tipo,String numeroDocumento, Integer idEstadoFallo);
    
    TuDiagnostico consultarDiagnostico(int id) throws Exception;
    
    List<TuGrupoUsuario> consultarUsuarioGrupoPorTipo(TuTutelaBean bean);
    
    void crearEstadoBloquearYcalarCamposObligatoriosParaEstadosTutelas(TuTutelaBean bean) throws Exception ;
    
    void crearEstadoBloquearYcalarCamposObligatoriosParaEstadosTutelasAdicionales(TuTutelaBean bean) throws Exception ;
    
    void crearHabilitarCamposTipoFallo(TuTutelaBean bean) throws Exception ;
    
    void crearHabilitarCamposTipoFalloAdicional(TuTutelaBean bean) throws Exception ;
    
    void crearHabilitarCamposTipoClaseSancion(TuTutelaBean bean) throws Exception;
    
    void crearHabilitarCamposTipoClaseSancionAdicional(TuTutelaBean bean) throws Exception;
    
    void consultarGruposEstadoInicialTutelas(TuTutelaBean bean) throws Exception;
    
    void consultarGruposEstadosAdicionalesTutelas(TuTutelaBean bean) throws Exception;
    
    void consultarUsuariosGruposTutelasEstadoInicial(TuTutelaBean bean) throws Exception;
    
    /**
     * Función para generar el doumento memorial del estado
     *
     * @param bean
     * @throws java.lang.Exception
     */
    void generarDocumentoMemorial(TuTutelaBean bean) throws Exception;
    
    /**
     * Función para consultar el maestro relacion de Causa Tutela,cada vez que se modifique
     * el campo Tipo Presentación
     * @param bean 
     */
    void consultarMaestroCausaTutelaPorTipoPresentacion(TuTutelaBean bean);
    
    /**
     * listamos los usuarios según un rol seleccionado.
     * @param bean 
     */
    void listarUsuariosPorRol(TuTutelaBean bean);
}
