/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.atencionusuario;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCaso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface AusCasoRemoto {
    
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
    List<AusCaso> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    AusCaso consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (Personas)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AusCaso obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (Personas)
     * @throws java.lang.Exception
     */
    void actualizar(AusCaso obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (Personas)
     * @throws java.lang.Exception
     */
    void actualizarCantidadServicios(AusCaso obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (Personas)
     * @throws java.lang.Exception
     */
    void actualizarCantidadServiciosBorrado(AusCaso obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (Personas)
     * @throws java.lang.Exception
     */
    void actualizarCantidadServiciosCerrados(AusCaso obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro para borrar caso
     * @param obj (Personas)
     * @throws java.lang.Exception
     */
    void actualizarBorrarCaso(AusCaso obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro para borrar caso
     * @param obj (Personas)
     * @throws java.lang.Exception
     */
    void actualizarReabrirCaso(AusCaso obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro para borrar caso
     * @param obj (Personas)
     * @throws java.lang.Exception
     */
    void actualizarCerrarCaso(AusCaso obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (Personas) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    AusCaso eliminar(int id) throws Exception;
    /**
     * Metodo que permite consultar casos por una seria de filtros entre ellos
     * (tipo documento, documeto, idcaso)
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    public List<AusCaso> consultarPorFiltros(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Metodo que trae los dias no habiles para la fecha de vencimiento y notificacion
     * @param fecha
     * @return 
     */
    public List<Date> consultarFechasNoHabiles(Date fecha);
    /**
     * Permite consultar usuarios de una empresa cast reducido
     * @param empresaId
     * @return
     * @throws Exception 
     */
    public List<Usuario> consultarUsuarioPorEmpresa(int empresaId) throws Exception;
    
    /**
     * Permite consultar usuarios de una empresa cast reducido
     * @param empresaId
     * @return
     * @throws Exception 
     */
    public List<Usuario> consultarUsuario(int empresaId) throws Exception;
    
    /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadListaExterna(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<AusCaso> consultarListaExterna(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @return
     * @throws Exception 
    */
    List<Usuario> consultarTodosUsuarios()throws Exception;
    
    /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadListaPorAfiliado(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<AusCaso> consultarListaPorAfiliado(ParamConsulta paramConsulta) throws Exception;
    
    
    /**
     * Funcion que pemite obtener casos que pertenecen a numeros de radicado
     * @param radicado
     * @return
     * @throws Exception 
     */
    AusCaso consultarCasosPorRadicado(String radicado) throws Exception;
    
     /**
     * Funcion que pemite obtener casos que pertenecen no borrado
     * @param id
     * @return
     * @throws Exception 
     */
    AusCaso consultarCasoNoBorrado(Integer id) throws Exception;
    
    /**
     * Función para consultar la lista de casos relacionados a un afiliado por tipo tecnologia y estado de Caso
     * @param caso
     * @return
     * @throws Exception 
     */
    List<AusCaso> consultarPorAfiliadoTipoTecnologiaYEstado(AusCaso caso) throws Exception;
}
