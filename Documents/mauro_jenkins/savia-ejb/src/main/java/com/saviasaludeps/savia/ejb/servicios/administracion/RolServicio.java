/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.Perfil;
import com.saviasaludeps.savia.dominio.administracion.Rol;
import com.saviasaludeps.savia.dominio.administracion.RolPerfil;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.GnPerfiles;
import com.saviasaludeps.savia.negocio.administracion.RolRemoto;
import com.saviasaludeps.savia.ejb.entidades.GnRoles;
import com.saviasaludeps.savia.ejb.entidades.GnRolesPerfiles;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
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
@Remote(RolRemoto.class)
public class RolServicio extends GenericoServicio implements RolRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(DISTINCT r) FROM GnRoles r "
                    + " LEFT JOIN GnRolesPerfiles rp ON r.id = rp.gnRolesId.id "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND r.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND r.activo = " + (String) e.getValue() + " ";
                            break;
                        case "descripcion":
                            strQuery += "AND r.descripcion LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "rolesPerfilesList":
                            strQuery += "AND rp.gnPerfilesId.id = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
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
    public List<Rol> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<Rol> listResult = new ArrayList();
        try {
            String strQuery = "SELECT DISTINCT r FROM GnRoles r "
                    + "LEFT JOIN GnRolesPerfiles rp ON r.id = rp.gnRolesId.id "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND r.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND r.activo = " + (String) e.getValue() + " ";
                            break;
                        case "descripcion":
                            strQuery += "AND r.descripcion LIKE  '%" + (String) e.getValue() + "%' ";
                            break;
                        case "rolesPerfilesList":
                            strQuery += "AND rp.gnPerfilesId.id = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "r." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {

                strQuery += "r.id ASC ";
            }
            List<GnRoles> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GnRoles obj : list) {
                Rol rol = castEntidadNegocio(obj);
                listResult.add(rol);
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
    public Rol consultar(int id) throws Exception {
        Rol objRes = null;
        try {
            objRes = castEntidadNegocio((GnRoles) getEntityManager().find(GnRoles.class, id));
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
    public int insertar(Rol rol) throws Exception {
        int id = 0;
        try {
            GnRoles roles = castNegocioEntidad(rol);
            id = (int) getEntityManager().merge(roles).getId();
            rol.setId(id);
            roles.setId(id);
            for (RolPerfil rolPerfil : rol.getRolesPerfilesList()) {
                GnRolesPerfiles perfilEntity = new GnRolesPerfiles();
                perfilEntity.setGnRolesId(new GnRoles(id));
                perfilEntity.setGnPerfilesId(new GnPerfiles(rolPerfil.getPerfiles().getId()));
                getEntityManager().merge(perfilEntity);
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
    public void actualizar(Rol rol) throws Exception {
        try {
            GnRoles roles = castNegocioEntidad(rol);
            for (RolPerfil rolPerfil : rol.getRolesPerfilesList()) {
                GnRolesPerfiles perfilEntity = new GnRolesPerfiles();
                perfilEntity.setGnRolesId(new GnRoles(roles.getId()));
                perfilEntity.setGnPerfilesId(new GnPerfiles(rolPerfil.getPerfiles().getId()));
                roles.getGnRolesPerfilesList().add(perfilEntity);
            }
            Query q = getEntityManager().createQuery("DELETE GnRolesPerfiles WHERE gnRolesId.id = " + roles.getId());
            q.executeUpdate();
            getEntityManager().merge(roles);
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public Rol eliminar(int id) throws Exception {
        Rol obj = null;
        try {
            GnRoles ent = getEntityManager().find(GnRoles.class, id);
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
    public Rol consultarPorRol(String usuario) throws Exception {
        Rol obj = null;
        String strQuery = "FROM GnRoles"
                + " WHERE usuario = :usuario"
                + " OREDER BY nombre";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("usuario", usuario);
            obj = castEntidadNegocio((GnRoles) query.getSingleResult());
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
    public HashMap<Integer, Rol> consultarHashTodos(int empresaId) throws Exception {
        HashMap<Integer, Rol> hashRoles = new HashMap();
        String strQuery = "FROM GnRoles u"
                + " LEFT JOIN u.gnPerfilesId p"
                + " WHERE u.gnEmpresasId.id = :empresa_id";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("empresa_id", empresaId);
            List<Object> list = query.getResultList();
            for (Object obj : list) {
                Rol usu = RolServicio.castEntidadNegocio((GnRoles) ((Object[]) obj)[0]);
                hashRoles.put(usu.getId(), usu);
            }
        } catch (NoResultException e) {
            hashRoles = new HashMap();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return hashRoles;
    }
    
    @Override
    public List<Rol> consultarTodos() throws Exception{
        List<Rol> listResult = new ArrayList();
        String strQuery = "FROM GnRoles WHERE activo = true ORDER BY nombre";
        try{
            Query query = getEntityManager().createQuery(strQuery);
            List<GnRoles> list = query.getResultList();
            for (GnRoles obj : list) {
                listResult.add(castEntidadNegocioLista(obj));
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

    public static Rol castEntidadNegocio(GnRoles ent) {
        Rol obj = new Rol();
        obj.setId(ent.getId());
        obj.setNombre(ent.getNombre());
        obj.setDescripcion(ent.getDescripcion());
        obj.setActivo(ent.getActivo());
        //GnPerfiles
        obj.setRolesPerfilesList(new ArrayList<>());
        for (GnRolesPerfiles rolPerfil : ent.getGnRolesPerfilesList()) {
            RolPerfil perfilEntity = new RolPerfil();
            perfilEntity.setRolesId(new Rol(rolPerfil.getGnRolesId().getId()));
            perfilEntity.setPerfiles(castEntidadNegocio(rolPerfil.getGnPerfilesId()));
            obj.getRolesPerfilesList().add(perfilEntity);
        }
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        return obj;
    }
    
    public static Rol castEntidadNegocioLista(GnRoles ent) {
        Rol obj = new Rol();
        obj.setId(ent.getId());
        obj.setNombre(ent.getNombre());
        obj.setDescripcion(ent.getDescripcion());
        obj.setActivo(ent.getActivo());
        obj.setRolesPerfilesList(castEntidadNegocio(ent.getGnRolesPerfilesList()));
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        return obj;
    }
    
    public static List<RolPerfil> castEntidadNegocio(List<GnRolesPerfiles> rolesPerfilesList) {
        List<RolPerfil>  objLista = new ArrayList<>();
        for (GnRolesPerfiles rolesPerfiles : rolesPerfilesList) {
            RolPerfil  obj = new RolPerfil();
            obj.setId(rolesPerfiles.getId());
            obj.setPerfiles(new Perfil(rolesPerfiles.getGnPerfilesId().getId()));
            objLista.add(obj);
        }
        return objLista;
    }

    public static Perfil castEntidadNegocio(GnPerfiles ent) {
        Perfil obj = new Perfil();
        obj.setId(ent.getId());
        obj.setNombre(ent.getNombre());
        return obj;
    }

    public static GnRoles castNegocioEntidad(Rol obj) {
        GnRoles ent = new GnRoles();
        ent.setId(obj.getId());
        ent.setNombre(obj.getNombre());
        ent.setDescripcion(obj.getDescripcion());
        ent.setActivo(obj.getActivo());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        ent.setGnRolesPerfilesList(new ArrayList<>());
        return ent;
    }

}
