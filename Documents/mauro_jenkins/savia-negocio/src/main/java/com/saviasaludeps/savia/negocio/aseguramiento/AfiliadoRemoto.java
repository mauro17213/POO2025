/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.ReporteAfiliacion;
import com.saviasaludeps.savia.dominio.aseguramiento.ReporteEncuesta016;
import java.util.Date;
import java.util.List;

public interface AfiliadoRemoto {
    
    /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<AsegAfiliado> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (ReConciliacionRecobros) cargado
     * @throws java.lang.Exception
     */
    AsegAfiliado consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (ReConciliacionRecobros)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AsegAfiliado obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (AfAfiliados)
     * @throws java.lang.Exception
     */
    void actualizar(AsegAfiliado obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (AfAfiliados) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    AsegAfiliado eliminar(int id) throws Exception;
    
    /**
     * Consultar todas las AfAfiliados
     * @return
     * @throws Exception 
     */
    List<AsegAfiliado> consultarTodos() throws Exception;
    
    /**
     * Método que permite consultar el grupo Familiar asociado al Afiliado
     * @param tipoDocumentoCF
     * @param numeroDocumentoCF
     * @return
     * @throws Exception 
     */
    public List<AsegAfiliado> consultarGrupoFamiliar(Integer tipoDocumentoCF,String numeroDocumentoCF) throws Exception;
    

    /**
     * Método que permite consultar un afiliado por su tipo de documento y numero de documento
     * @param tipodocumento
     * @param numeroDocumento
     * @return
     * @throws Exception 
     */
    AsegAfiliado consultar(Integer tipodocumento,String numeroDocumento) throws Exception;
    
    /**
     * consulta afiliado por aseg_afiliados_documentos y maestro acción activo
     * @param tipodocumento
     * @param numeroDocumento
     * @return
     * @throws Exception 
     */
    AsegAfiliado consultarPorAfiliadoDocumentoHistorico(Integer tipodocumento, String numeroDocumento) throws Exception;
    
    /**
     * Método que permite consultar un afiliado por su primer apellido, primer nombre y fecha de nacimiento
     * 
     * @param primerApellido
     * @param primerNombre
     * @param fechaNacimiento
     * @return
     * @throws Exception 
     */
    AsegAfiliado consultar(String primerApellido,String primerNombre, Date fechaNacimiento) throws Exception;
    
    /**
     * Metodo que trae los datos para el reporte 016
     * @param id
     * @return 
     * @throws java.lang.Exception 
     */
    ReporteEncuesta016 reporte016(String id) throws Exception;
    
    /**
     * Actualiza el afiliado cuando se cancela una portabilidad.
     * @param obj
     * @throws Exception 
     */
    void actualizarAfiliadoPortabilidad(AsegAfiliado obj) throws Exception;
    
    /**
     * Metodo que trae los datos para el reporte afiliacion
     * @param id
     * @return
     * @throws Exception 
     */
    List<ReporteAfiliacion> reporteAfiliacion(String id) throws Exception;
    
    /**
     * Método que permite consultar un afiliado por su numero de contrato ( idAfiliado)
     * @param idAfiliado
     * @return El registro del afiliado. Si no lo encuentra retorna null
     * @throws Exception 
     */
    AsegAfiliado consultar(String idAfiliado) throws Exception;
    
    /**
     * Método que permite consultar un afiliado por su primer apellido, primer nombre y fecha de nacimiento
     * 
     * @param primerApellido
     * @param segundoApellido
     * @param primerNombre
     * @param segundoNombre
     * @param fechaNacimiento
     * @return
     * @throws Exception 
     */
    AsegAfiliado consultar(String primerApellido,String segundoApellido,String primerNombre,String segundoNombre, Date fechaNacimiento) throws Exception;
    
    /**
     * Método que permite consultar un afiliado por su primer apellido, primer nombre y fecha de nacimiento
     * 
     * @param numeroDocumento
     * @param primerApellido
     * @param segundoApellido
     * @param primerNombre
     * @param segundoNombre
     * @param fechaNacimiento
     * @return
     * @throws Exception 
     */
    AsegAfiliado consultar(String numeroDocumento, String primerApellido,String segundoApellido,String primerNombre,String segundoNombre, Date fechaNacimiento) throws Exception;
    
    /**
     * Método que permite consultar un afiliado por sus datos básicos ( tipo documento, numero documento,
     * primer apellido, segundo apellido, primer nombre, segundo nombre, fecha nacimiento
     * 
     * @param codigoDocumento
     * @param numeroDocumento
     * @param primerApellido
     * @param segundoApellido
     * @param primerNombre
     * @param segundoNombre
     * @param fechaNacimiento
     * @return
     * @throws Exception 
     */
    AsegAfiliado consultar(int codigoDocumento, String numeroDocumento, String primerApellido,String segundoApellido,String primerNombre,String segundoNombre, Date fechaNacimiento) throws Exception;
    
    /**
     * Método que permite consultar un afiliado o afiliados por su numero de documento.
     * @param numeroDocumento
     * @return La lista de registros correspondientes a los afiliados consultados
     * @throws Exception 
     */
    List<AsegAfiliado> consultarPorNumeroDocumento(String numeroDocumento) throws Exception;
    
    /**
     * Método que permite consultar un afiliado por su primer apellido, primer nombre y fecha de nacimiento
     * 
     * @param primerApellido
     * @param segundoApellido
     * @param primerNombre
     * @param segundoNombre
     * @return
     * @throws Exception 
     */
    List<AsegAfiliado> consultarPorFoneticos(String primerApellido,String segundoApellido,String primerNombre,String segundoNombre) throws Exception;

    /**
     * 
     * @param tipodocumento
     * @param numeroDocumento
     * @param fechaNacimiento
     * @param primerNombre
     * @param segundoNombre
     * @param primerApellido
     * @param segundoApellido
     * @return
     * @throws Exception 
     */
    List<AsegAfiliado> consultar(String tipodocumento, String numeroDocumento, String fechaNacimiento, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido) throws Exception;
    
    /**
     * Consulta de cantidad de registros en una lista - Buscador
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadListaBuscador(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros - Buscador
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<AsegAfiliado> consultarListaBuscador(ParamConsulta paramConsulta) throws Exception;
    
    /**
     *
     * @param tipodocumento
     * @param numeroDocumento
     * @return
     * @throws Exception
     */
    AsegAfiliado consultarActivo(Integer tipodocumento,String numeroDocumento) throws Exception;
    
    /**
     * Método que permite consultar varios afiliados por su tipo de documento y numero de documento
     * @param tipodocumento
     * @param numeroDocumento
     * @return
     * @throws Exception 
     */
    List<AsegAfiliado> consultarListaAfiliados(String tipodocumento, String numeroDocumento) throws Exception;
    
    /**
     * Método que permite consultar una lista de afiliados que tengan asociado los valores de tipo de documento
     * y número de documento
     * @param tipoDocumento
     * @param numeroDocumento
     * @return
     * @throws Exception 
     */
    public List<AsegAfiliado> consultarPorTipoDocumentoYNumeroDocumento(Integer tipoDocumento, String numeroDocumento) throws Exception;
    
    /**
     * Método que permite consultar una lista de afiliados que tengan asociado los valores de tipo de documento
     * y número de documento
     * @param codigoTipoDocumento
     * @param numeroDocumento
     * @return
     * @throws Exception 
     */
    public List<AsegAfiliado> consultarPorTipoDocumentoYNumeroDocumento(String codigoTipoDocumento, String numeroDocumento) throws Exception;
    
}
