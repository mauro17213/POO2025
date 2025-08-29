/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Rol;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.administracion.UsuarioRol;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.entidades.GnRoles;
import com.saviasaludeps.savia.ejb.entidades.GnRolesUsuario;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.mensajes.Correo;
import com.saviasaludeps.savia.ejb.utilidades.Encrypt;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(UsuarioRemoto.class)
public class UsuarioServicio extends GenericoServicio implements UsuarioRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(DISTINCT u) FROM GnUsuarios u "
                    + " LEFT JOIN GnRolesUsuario ru ON u.id = ru.gnUsuariosId.id "
                    + "WHERE u.gnEmpresasId.id = :empresa_id ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "usuario":
                            strQuery += "AND u.usuario LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "documento":
                            strQuery += "AND u.documento LIKE '%" + (String) e.getValue() + "' ";
                            break;
                        case "nombre":
                            strQuery += "AND u.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeAreaId":
                            strQuery += "AND u.maeAreaId = " + (String) e.getValue() + " ";
                            break;
                        case "correoElectronico":
                            strQuery += "AND u.correoElectronico LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND u.activo = " + (String) e.getValue() + " ";
                            break;
                        case "bloqueado":
                            strQuery += "AND u.bloqueado = " + e.getValue() + " ";
                            break;
                        case "listaRoles":
                            strQuery += "AND ru.gnRolesId.id = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

    @Override
    public List<Usuario> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<Usuario> listResult = new ArrayList();
        try {
            String strQuery = "SELECT DISTINCT u FROM GnUsuarios u "
                    + "LEFT JOIN GnRolesUsuario ru ON u.id = ru.gnUsuariosId.id "
                    + "WHERE u.gnEmpresasId.id = :empresa_id ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "usuario":
                            strQuery += "AND u.usuario LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "documento":
                            strQuery += "AND u.documento LIKE '%" + (String) e.getValue() + "' ";
                            break;
                        case "nombre":
                            strQuery += "AND u.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeAreaId":
                            strQuery += "AND u.maeAreaId = " + (String) e.getValue() + " ";
                            break;
                        case "correoElectronico":
                            strQuery += "AND u.correoElectronico LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND u.activo = " + (String) e.getValue() + " ";
                            break;
                        case "bloqueado":
                            strQuery += "AND u.bloqueado = " + e.getValue() + " ";
                            break;
                        case "listaRoles":
                            strQuery += "AND ru.gnRolesId.id = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "u." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "u.usuario ASC";
            }
            List<GnUsuarios> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (GnUsuarios per : list) {
                listResult.add(castEntidadNegocioCorto(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public Usuario consultar(int id) throws Exception {
        Usuario objRes = null;
        try {
            objRes = castEntidadNegocio((GnUsuarios) getEntityManager().find(GnUsuarios.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public int insertar(Usuario obj) throws Exception {
        int id = 0;
        try {
            GnUsuarios per = castNegocioEntidad(obj);
            id = (int) getEntityManager().merge(per).getId();
            per.setId(id);
            obj.setId(id);
            //Actualizar roles
            if (obj.getListaRoles() != null) {
                List<GnRolesUsuario> listaIngreso = new ArrayList();
                for (UsuarioRol rol : obj.getListaRoles()) {
                    GnRolesUsuario rolEntity = new GnRolesUsuario();
                    rolEntity.setGnUsuariosId(new GnUsuarios(per.getId()));
                    rolEntity.setGnRolesId(new GnRoles(rol.getRol().getId()));
                    rolEntity.setUsuarioCrea(per.getUsuarioCrea());
                    rolEntity.setTerminalCrea(per.getTerminalCrea());
                    rolEntity.setFechaHoraCrea(per.getFechaHoraCrea());
                    listaIngreso.add(rolEntity);
                }
                actualizarRoles(per, listaIngreso);
            }
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "El nombre de usuario ya existe.");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(Usuario obj) throws Exception {
        try {
            String hql = "UPDATE GnUsuarios SET "
                    + "usuario = :usuario, "
                    + "nombre = :nombre, "
                    + "correoElectronico = :correo_electronico, "
                    + "telefono = :telefono, "
                    + "celular = :celular, "
                    + "activo = :activo, "
                    + "intentos = :intentos, "
                    + "bloqueado = :bloqueado, "
                    + "maeTipoDocumentoId = :maeTipoDocumentoId, "
                    + "maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo, "
                    + "maeTipoDocumentoValor = :maeTipoDocumentoValor, "
                    + "documento = :documento, "
                    + "maeAreaId = :maeAreaId, "
                    + "maeAreaCodigo = :maeAreaCodigo, "
                    + "maeAreaValor = :maeAreaValor, "
                    + "sesiones = :sesiones, ";

            if (obj.getMaeCargoId() != null) {
                hql += "maeCargoId = :maeCargoId, "
                        + "maeCargoCodigo = :maeCargoCodigo, "
                        + "maeCargoValor = :maeCargoValor, ";
            }
            hql += "usuario_modifica = :usuario_modifica, "
                    + "terminal_modifica = :terminal_modifica, "
                    + "fecha_hora_modifica = :fecha_hora_modifica "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("usuario", obj.getUsuario());
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("correo_electronico", obj.getCorreoElectronico());
            query.setParameter("telefono", obj.getTelefono());
            query.setParameter("celular", obj.getCelular());
            query.setParameter("activo", obj.isActivo());
            query.setParameter("intentos", obj.getIntentos());
            query.setParameter("bloqueado", obj.isBloqueado());
            query.setParameter("maeTipoDocumentoId", obj.getMaeTipoDocumentoId());
            query.setParameter("maeTipoDocumentoCodigo", obj.getMaeTipoDocumentoCodigo());
            query.setParameter("maeTipoDocumentoValor", obj.getMaeTipoDocumentoValor());
            query.setParameter("documento", obj.getDocumento());
            query.setParameter("maeAreaId", obj.getMaeAreaId());
            query.setParameter("maeAreaCodigo", obj.getMaeAreaCodigo());
            query.setParameter("maeAreaValor", obj.getMaeAreaValor());
            query.setParameter("sesiones", obj.getSesiones());
            if (obj.getMaeCargoId() != null) {
                query.setParameter("maeCargoId", obj.getMaeCargoId());
                query.setParameter("maeCargoCodigo", obj.getMaeCargoCodigo());
                query.setParameter("maeCargoValor", obj.getMaeCargoValor());
            }
            query.setParameter("usuario_modifica", obj.getUsuarioModifica());
            query.setParameter("terminal_modifica", obj.getTerminalModifica());
            query.setParameter("fecha_hora_modifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
            //Actualizar roles
            List<GnRolesUsuario> listaIngreso = new ArrayList();
            for (UsuarioRol rol : obj.getListaRoles()) {
                GnRolesUsuario rolEntity = new GnRolesUsuario();
                rolEntity.setGnUsuariosId(new GnUsuarios(obj.getId()));
                rolEntity.setGnRolesId(new GnRoles(rol.getRol().getId()));
                rolEntity.setUsuarioCrea(obj.getUsuarioModifica());
                rolEntity.setTerminalCrea(obj.getTerminalModifica());
                rolEntity.setFechaHoraCrea(obj.getFechaHoraModifica());
                listaIngreso.add(rolEntity);
            }
            actualizarRoles(new GnUsuarios(obj.getId()), listaIngreso);
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    private void actualizarRoles(GnUsuarios usu, List<GnRolesUsuario> listaIngreso) {
        String strQuery = "FROM GnRolesUsuario ru "
                + "WHERE ru.gnUsuariosId.id = " + usu.getId();
        Query query = getEntityManager().createQuery(strQuery);
        List<GnRolesUsuario> listaExiste = query.getResultList();
        //BORRAR
        for (GnRolesUsuario existe : listaExiste) {
            boolean borrar = true;
            for (GnRolesUsuario ingreso : listaIngreso) {
                if (existe.getGnRolesId().getId().intValue() == ingreso.getGnRolesId().getId().intValue()) {
                    borrar = false;
                    break;
                }
            }
            if (borrar) {
                getEntityManager().remove(existe);
            }
        }
        query = getEntityManager().createQuery(strQuery);
        listaExiste = query.getResultList();
        //INSERTAR
        for (GnRolesUsuario ingreso : listaIngreso) {
            boolean crear = true;
            for (GnRolesUsuario existe : listaExiste) {
                if (existe.getGnRolesId().getId().intValue() == ingreso.getGnRolesId().getId().intValue()) {
                    crear = false;
                    break;
                }
            }
            if (crear) {
                getEntityManager().merge(ingreso);
            }
        }
    }

    @Override
    public void registroConexion(Usuario obj) throws Exception {
        try {
            String hql = "UPDATE GnUsuarios SET "
                    + "intentos = :intentos, "
                    + "bloqueado = :bloqueado, "
                    + "fechaUltimoIngreso = :fechaUltimoIngreso "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("fechaUltimoIngreso", new Date());
            query.setParameter("intentos", obj.getIntentos());
            query.setParameter("bloqueado", obj.isBloqueado());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public Usuario eliminar(int id) throws Exception {
        Usuario obj = null;
        try {
            GnUsuarios ent = getEntityManager().find(GnUsuarios.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public Usuario validarUsuario(Usuario per) throws Exception {
        Usuario objRes = null;
        try {
            String hql = "FROM GnUsuarios "
                    + "WHERE id = :id "
                    + "AND contrasena = :contrasena ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("id", per.getId());
            query.setParameter("contrasena", Encrypt.sha512(per.getContrasena()));
            objRes = castEntidadNegocio((GnUsuarios) query.getSingleResult());
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public void cambioContrasena(Usuario obj) throws Exception {
        try {
            String hql = "UPDATE GnUsuarios SET "
                    + "contrasena = :contrasena "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("id", obj.getId());
            query.setParameter("contrasena", Encrypt.sha512(obj.getContrasena()));
            query.executeUpdate();
        } catch (Exception e) {
            Exception("Cambio Contraseña", e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public List<Usuario> consultarPorPerfil(int empresaId, int idPerfil) throws Exception {
        List<Usuario> listResult = new ArrayList();
        String strQuery = "FROM GnUsuarios u "
                + "WHERE u.gnEmpresasId.id = :empresa_id "
                + "AND u.gnPerfilesId.id = :perfil_id "
                + "AND u.activo = 1 "
                + "ORDER BY nombre, usuario ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("empresa_id", empresaId);
            query.setParameter("perfil_id", idPerfil);
            List<GnUsuarios> list = query.getResultList();
            for (GnUsuarios obj : list) {
                listResult.add(castEntidadNegocio(obj));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception("Consulta de registros", e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public HashMap<Integer, Usuario> consultarHashPorPerfil(int empresaId, int idPerfil) throws Exception {
        HashMap<Integer, Usuario> hashUsuarios = new HashMap();
        String strQuery = "FROM GnUsuarios u "
                + "WHERE u.gnEmpresasId.id = :empresa_id "
                + "AND u.gnPerfilesId.id = :perfil_id "
                + "AND u.activo = 1 "
                + "ORDER BY nombre, usuario ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("empresa_id", empresaId);
            query.setParameter("perfil_id", idPerfil);
            List<GnUsuarios> list = query.getResultList();
            for (GnUsuarios obj : list) {
                Usuario usu = UsuarioServicio.castEntidadNegocio(obj);
                hashUsuarios.put(usu.getId(), usu);
            }
        } catch (NoResultException e) {
            hashUsuarios = new HashMap();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return hashUsuarios;
    }

    @Override
    public void restaurarContrasena(Usuario obj) throws Exception {
        try {
            String hql = "UPDATE GnUsuarios SET "
                    + "contrasena = :contrasena "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("id", obj.getId());
            query.setParameter("contrasena", Encrypt.sha512(obj.getContrasena()));
            query.executeUpdate();
            new Thread(new Correo(obj.getCorreoElectronico(), obj.getEncabezado(), obj.getMensaje())).start();
//            new Correo().envio(obj.getCorreoElectronico(), obj.getEncabezado(), obj.getMensaje());
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception("Cambio Contraseña", e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public List<Usuario> consultarPorEmpresa(int empresaId) throws Exception {
        List<Usuario> listResult = new ArrayList();
        String strQuery = "FROM GnUsuarios"
                + " WHERE gnEmpresasId.id = :empresa_id"
                + " ORDER BY nombre, usuario";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("empresa_id", empresaId);
            List<GnUsuarios> list = query.getResultList();
            for (GnUsuarios obj : list) {
                listResult.add(castEntidadNegocio(obj));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception("Consulta de registros", e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public List<Usuario> consultarPorEmpresaSimplificado(int empresaId) throws Exception {
        List<Usuario> listResult = new ArrayList();
        String strQuery = "FROM GnUsuarios"
                + " WHERE gnEmpresasId.id = :empresa_id"
                + " ORDER BY nombre, usuario";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("empresa_id", empresaId);
            List<GnUsuarios> list = query.getResultList();
            for (GnUsuarios obj : list) {
                listResult.add(CastEntidadNegocionSimple(obj));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception("Consulta de registros", e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public List<Usuario> consultarPorEmpresaSinOrdenamiento(int empresaId) throws Exception {
        List<Usuario> listResult = new ArrayList();
        String strQuery = "FROM GnUsuarios"
                + " WHERE gnEmpresasId.id = :empresa_id";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("empresa_id", empresaId);
            List<GnUsuarios> list = query.getResultList();
            for (GnUsuarios obj : list) {
                listResult.add(CastEntidadNegocionSimple(obj));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception("Consulta de registros", e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public Usuario consultarPorUsuario(String usuario) throws Exception {
        Usuario obj = null;
        String strQuery = "FROM GnUsuarios"
                + " WHERE usuario = :usuario";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("usuario", usuario);
            obj = castEntidadNegocio((GnUsuarios) query.getSingleResult());
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            obj = null;
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public HashMap<Integer, Usuario> consultarHashTodos(int empresaId) throws Exception {
        HashMap<Integer, Usuario> hashUsuarios = new HashMap();
        String strQuery = "FROM GnUsuarios u"
                + " WHERE u.gnEmpresasId.id = :empresa_id and activo = 1 ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("empresa_id", empresaId);
            List<GnUsuarios> list = query.getResultList();
            for (GnUsuarios obj : list) {
                Usuario usu = UsuarioServicio.castEntidadNegocio(obj);
                hashUsuarios.put(usu.getId(), usu);
            }
        } catch (NoResultException e) {
            hashUsuarios = new HashMap();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return hashUsuarios;
    }

    @Override
    public HashMap<Integer, Usuario> consultarIdNombreHashTodos(int empresaId) throws java.lang.Exception {
        HashMap<Integer, Usuario> hashUsuarios = new HashMap();
        String strQuery = "FROM GnUsuarios u"
                + " WHERE u.gnEmpresasId.id = :empresa_id and activo = 1 ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("empresa_id", empresaId);
            List<GnUsuarios> list = query.getResultList();
            for (GnUsuarios obj : list) {
                Usuario usu = UsuarioServicio.castEntidadNegocioNombreId(obj);
                hashUsuarios.put(usu.getId(), usu);
            }
        } catch (NoResultException e) {
            hashUsuarios = new HashMap();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return hashUsuarios;
    }

    @Override
    public List<Usuario> consultarSegmentado(int empresaId, int tipo, int tamano, String cadena) throws Exception {
        List<Usuario> listResult = new ArrayList();
        if (cadena != null && cadena.length() > tamano) {
            String strQuery = "FROM GnUsuarios u"
                    + " WHERE u.gnEmpresasId.id = :empresa_id";
            switch (tipo) {
                case 0:
                    strQuery += " AND u.usuario LIKE '%" + cadena + "%' "
                            + " ORDER BY u.usuario, u.nombre";
                    break;
                case 1:
                    strQuery += " AND u.nombre LIKE '%" + cadena + "%' "
                            + " ORDER BY u.nombre, u.usuario";
                    break;
                case 2:
                    strQuery += " AND (u.usuario LIKE '%" + cadena + "%' OR u.nombre LIKE '%" + cadena + "%')"
                            + " ORDER BY u.usuario, u.nombre";
                    break;
                default:
                    break;
            }
            try {
                Query query = getEntityManager().createQuery(strQuery);
                query.setParameter("empresa_id", empresaId);
                List<GnUsuarios> list = query.getResultList();
                for (GnUsuarios obj : list) {
                    listResult.add(castEntidadNegocio(obj));
                }
            } catch (NoResultException e) {
                listResult = new ArrayList();
            } catch (Exception e) {
                Exception("Consulta de registros", e);
            } finally {
                cerrarEntityManager();
            }
        }
        return listResult;
    }

    @Override
    public List<Usuario> consultarPorNombre(String nombre) throws Exception {
        List<Usuario> listResult = new ArrayList();
        String strQuery = "FROM GnUsuarios u "
                + "WHERE u.nombre LIKE :nombre "
                + "ORDER BY nombre ASC ";
        try {
            Query query = getEntityManager().createQuery(strQuery)
                    .setParameter("nombre", "%" + nombre + "%");
            List<GnUsuarios> list = query.getResultList();
            for (GnUsuarios obj : list) {
                listResult.add(castEntidadNegocio(obj));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception("Consulta de registros", e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    public static Usuario castEntidadNegocioCorto(GnUsuarios per) {
        Usuario obj = new Usuario();
        obj.setEmpresa(new Empresa(per.getGnEmpresasId().getId()));
        obj.setId(per.getId());
        obj.setUsuario(per.getUsuario());
        obj.setNombre(per.getNombre());
        obj.setCorreoElectronico(per.getCorreoElectronico());
        obj.setTelefono(per.getTelefono());
        obj.setCelular(per.getCelular());
        obj.setActivo(per.getActivo());
        obj.setFechaInicio(per.getFechaInicio());
        obj.setFechaFin(per.getFechaFin());
        obj.setFechaUltimoIngreso(per.getFechaUltimoIngreso());
        obj.setIntentos(per.getIntentos());
        obj.setBloqueado(per.getBloqueado());
        obj.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setDocumento(per.getDocumento());
        obj.setMaeAreaId(per.getMaeAreaId());
        obj.setMaeAreaCodigo(per.getMaeAreaCodigo());
        obj.setMaeAreaValor(per.getMaeAreaValor());
        obj.setSesiones(per.getSesiones());
        obj.setGnSedeTurno(per.getGnEmpresaTurnos());
        obj.setMaeCargoId(per.getMaeCargoId());
        obj.setMaeCargoCodigo(per.getMaeCargoCodigo());
        obj.setMaeCargoValor(per.getMaeCargoValor());
        //ROLES
        obj.setListaRoles(new ArrayList());
        for (GnRolesUsuario rolUsuario : per.getGnRolesUsuarioList()) {
            UsuarioRol rolObj = new UsuarioRol();
            rolObj.setRol(new Rol(
                    rolUsuario.getGnRolesId().getId(),
                    rolUsuario.getGnRolesId().getNombre(),
                    rolUsuario.getGnRolesId().getDescripcion(),
                    rolUsuario.getGnRolesId().getActivo()
            ));
            rolObj.setUsuario(new Usuario(rolUsuario.getGnUsuariosId().getId()));
            rolObj.setUsuarioCrea(rolUsuario.getUsuarioCrea());
            rolObj.setTerminalCrea(rolUsuario.getTerminalCrea());
            rolObj.setFechaHoraCrea(rolUsuario.getFechaHoraCrea());
            obj.getListaRoles().add(rolObj);
        }
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static Usuario CastEntidadNegocionSimple(GnUsuarios per) {
        Usuario obj = new Usuario();
        obj.setEmpresa(new Empresa(per.getGnEmpresasId().getId()));
        obj.setId(per.getId());
        obj.setUsuario(per.getUsuario());
        obj.setNombre(per.getNombre());
        obj.setCorreoElectronico(per.getCorreoElectronico());
        obj.setTelefono(per.getTelefono());
        obj.setCelular(per.getCelular());
        obj.setActivo(per.getActivo());
        obj.setFechaInicio(per.getFechaInicio());
        obj.setFechaFin(per.getFechaFin());
        obj.setFechaUltimoIngreso(per.getFechaUltimoIngreso());
        obj.setGnSedeTurno(per.getGnEmpresaTurnos());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static Usuario castEntidadNegocio(GnUsuarios per) {
        Usuario obj = new Usuario();
        obj.setEmpresa(new Empresa(per.getGnEmpresasId().getId()));
        obj.setId(per.getId());
        obj.setUsuario(per.getUsuario());
        obj.setNombre(per.getNombre());
        obj.setCorreoElectronico(per.getCorreoElectronico());
        obj.setTelefono(per.getTelefono());
        obj.setCelular(per.getCelular());
        obj.setActivo(per.getActivo());
        obj.setFechaInicio(per.getFechaInicio());
        obj.setFechaFin(per.getFechaFin());
        obj.setFechaUltimoIngreso(per.getFechaUltimoIngreso());
        obj.setIntentos(per.getIntentos());
        obj.setBloqueado(per.getBloqueado());
        obj.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setDocumento(per.getDocumento());
        obj.setMaeAreaId(per.getMaeAreaId());
        obj.setMaeAreaCodigo(per.getMaeAreaCodigo());
        obj.setMaeAreaValor(per.getMaeAreaValor());
        obj.setMaeCargoId(per.getMaeCargoId());
        obj.setMaeCargoCodigo(per.getMaeCargoCodigo());
        obj.setMaeCargoValor(per.getMaeCargoValor());
        obj.setSesiones(per.getSesiones());
        obj.setGnSedeTurno(per.getGnEmpresaTurnos());
        //ROLES
        obj.setListaRoles(new ArrayList());
        for (GnRolesUsuario rolUsuario : per.getGnRolesUsuarioList()) {
            if (rolUsuario.getGnRolesId().getActivo()) {
                UsuarioRol rolObj = new UsuarioRol();
                rolObj.setRol(new Rol(
                        rolUsuario.getGnRolesId().getId(),
                        rolUsuario.getGnRolesId().getNombre(),
                        rolUsuario.getGnRolesId().getDescripcion(),
                        rolUsuario.getGnRolesId().getActivo()
                ));
                rolObj.setUsuario(new Usuario(rolUsuario.getGnUsuariosId().getId()));
                rolObj.setUsuarioCrea(rolUsuario.getUsuarioCrea());
                rolObj.setTerminalCrea(rolUsuario.getTerminalCrea());
                rolObj.setFechaHoraCrea(rolUsuario.getFechaHoraCrea());
                obj.getListaRoles().add(rolObj);
            }
        }
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static GnUsuarios castNegocioEntidad(Usuario obj) {
        GnUsuarios per = new GnUsuarios();
        per.setGnEmpresasId(new GnEmpresas(obj.getEmpresa().getId()));
        per.setId(obj.getId());
        per.setUsuario(obj.getUsuario());
        per.setNombre(obj.getNombre());
        per.setCorreoElectronico(obj.getCorreoElectronico());
        per.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
        per.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
        per.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
        per.setDocumento(obj.getDocumento());
        per.setMaeAreaId(obj.getMaeAreaId());
        per.setMaeAreaCodigo(obj.getMaeAreaCodigo());
        per.setMaeAreaValor(obj.getMaeAreaValor());
        per.setMaeCargoId(obj.getMaeCargoId());
        per.setMaeCargoCodigo(obj.getMaeCargoCodigo());
        per.setMaeCargoValor(obj.getMaeCargoValor());
        per.setTelefono(obj.getTelefono());
        per.setCelular(obj.getCelular());
        per.setActivo(obj.isActivo());
        per.setFechaInicio(obj.getFechaInicio());
        per.setFechaFin(obj.getFechaFin());
        per.setContrasena(Encrypt.sha512(obj.getContrasena()));
        per.setFechaUltimoIngreso(obj.getFechaUltimoIngreso());
        per.setIntentos(obj.getIntentos());
        per.setBloqueado(obj.isBloqueado());
        per.setRestaurarContrasegna(obj.isRestaurarContrasegna());
        per.setFechaUltimaContrasegna(obj.getFechaUltimaContrasegna());
        per.setFechaRestaurarContrasegna(obj.getFechaRestaurarContrasegna());
        per.setSesiones(obj.getSesiones());
        per.setGnEmpresaTurnos(obj.getGnSedeTurno());
        //Auditoria
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }

    public static Usuario castEntidadNegocioNombreId(GnUsuarios per) {
        Usuario obj = new Usuario();
        obj.setId(per.getId());
        obj.setNombre(per.getNombre());
        return obj;
    }

    @Override
    public List<Usuario> consultarPorRol(int idRol) throws Exception {
        List<Usuario> listResult = new ArrayList();
        String strQuery = "SELECT u.gnUsuariosId FROM GnRolesUsuario u "
                + "WHERE u.gnRolesId.id = " + idRol
                + "ORDER BY u.id ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnUsuarios> list = query.getResultList();
            for (GnUsuarios obj : list) {
                listResult.add(castEntidadNegocio(obj));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception("Consulta de registros", e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public void actualizarSedeTurno(Usuario obj) throws Exception {
        try {
            String hql = "UPDATE GnUsuarios SET "
                    + "gnEmpresaTurnos = :sede, "
                    + "usuario_modifica = :usuario_modifica, "
                    + "terminal_modifica = :terminal_modifica, "
                    + "fecha_hora_modifica = :fecha_hora_modifica "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("sede", obj.getGnSedeTurno());
            query.setParameter("usuario_modifica", obj.getUsuarioModifica());
            query.setParameter("terminal_modifica", obj.getTerminalModifica());
            query.setParameter("fecha_hora_modifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

}
