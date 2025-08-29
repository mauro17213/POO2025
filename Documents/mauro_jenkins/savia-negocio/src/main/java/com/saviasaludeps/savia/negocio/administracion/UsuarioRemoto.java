/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface UsuarioRemoto {

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<Usuario> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un usuario por ID
     *
     * @param id
     * @return (Usuarios) cargado
     * @throws java.lang.Exception
     */
    Usuario consultar(int id) throws Exception;

    /**
     * Método para crear una nueva Empresa
     *
     * @param per (Usuarios)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(Usuario per) throws Exception;

    /**
     * Método para actualizar la información de una Empresa
     *
     * @param per (Usuarios)
     * @throws java.lang.Exception
     */
    void actualizar(Usuario per) throws Exception;

    /**
     * Método para eliminar un usuario
     *
     * @param id
     * @return (Usuarios) Objetop eliminado
     * @throws java.lang.Exception
     */
    Usuario eliminar(int id) throws Exception;

    /**
     * Método para validar el usuario por Id y Contraseña
     *
     * @param per (Usuario) Datos a validar
     * @return (Usuarios) null --> SI no es exitosa
     * @throws Exception
     */
    Usuario validarUsuario(Usuario per) throws Exception;

    /**
     * Método para cambiar la contraseña del usuario actual
     *
     * @param per (Usuarios)
     * @throws java.lang.Exception
     */
    void cambioContrasena(Usuario per) throws Exception;

    /**
     * Metodo para consultar lista de usuario de una empresa
     *
     * @param empresaId (int) ID de Empresa
     * @return Lista de usuarios
     * @throws Exception
     */
    List<Usuario> consultarPorEmpresa(int empresaId) throws Exception;

    /**
     * Consultar usuario por nombre de usuario
     *
     * @param usuario
     * @return
     * @throws Exception
     */
    Usuario consultarPorUsuario(String usuario) throws Exception;

    /**
     * Registro de conexion de usuario
     *
     * @param obj
     * @throws Exception
     */
    void registroConexion(Usuario obj) throws Exception;

    /**
     * Método para restaurar contraseña de un usuario
     *
     * @param per
     * @throws Exception
     */
    void restaurarContrasena(Usuario per) throws Exception;

    /**
     *
     * @param empresaId
     * @return
     * @throws Exception
     */
    HashMap<Integer, Usuario> consultarHashTodos(int empresaId) throws Exception;

    /**
     *
     * @param empresaId
     * @return
     * @throws Exception
     */
    HashMap<Integer, Usuario> consultarIdNombreHashTodos(int empresaId) throws Exception;

    /**
     *
     * @param empresaId
     * @param idPerfil
     * @return
     * @throws Exception
     */
    List<Usuario> consultarPorPerfil(int empresaId, int idPerfil) throws Exception;

    /**
     *
     * @param empresaId
     * @param idPerfil
     * @return
     * @throws Exception
     */
    HashMap<Integer, Usuario> consultarHashPorPerfil(int empresaId, int idPerfil) throws Exception;

    /**
     * Método para consulta segmentada de usuarios según parámetros
     *
     * @param empresaId Id de la empresa a la que pertenece
     * @param tipo 0->Usuario | 2->Nombre | 2->Usuario (Nombre)
     * @param tamano tamaño minimo que requiere para ser consultado
     * @param cadena cadena de consulta para el where
     * @return
     * @throws Exception
     */
    List<Usuario> consultarSegmentado(int empresaId, int tipo, int tamano, String cadena) throws Exception;

    /**
     * Metodo para consultar lista de usuario de una empresa con datos basicos
     *
     * @param empresaId (int) ID de Empresa
     * @return Lista de usuarios
     * @throws Exception
     */
    List<Usuario> consultarPorEmpresaSimplificado(int empresaId) throws Exception;

    /**
     * Metodo para consultar lista de usuarios por nombre
     *
     * @param nombre (String) nombre de usuario
     * @return Lista de usuarios
     * @throws Exception
     */
    List<Usuario> consultarPorNombre(String nombre) throws Exception;

    /**
     * Metodo para consultar lista de usuario de una empresa con datos basicos
     *
     * @param empresaId (int) ID de Empresa
     * @return Lista de usuarios
     * @throws Exception
     */
    List<Usuario> consultarPorEmpresaSinOrdenamiento(int empresaId) throws Exception;
    
    /**
     * Metodo que consulta los usuarios segun el rol
     * @param idRol
     * @return
     * @throws Exception 
     */
    List<Usuario> consultarPorRol(int idRol) throws Exception;
   
    /**
     * Actualiza la sede en el turno del usuario
     * @param obj
     * @throws Exception 
     */
    void actualizarSedeTurno(Usuario obj) throws Exception;
}
