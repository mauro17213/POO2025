/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Permiso;
import com.saviasaludeps.savia.dominio.administracion.RolPerfil;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.administracion.UsuarioRol;
import com.saviasaludeps.savia.negocio.administracion.ModuloRemoto;
import com.saviasaludeps.savia.ejb.entidades.GnModulos;
import com.saviasaludeps.savia.ejb.entidades.GnPermisos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(ModuloRemoto.class)
public class ModuloServicio extends GenericoServicio implements ModuloRemoto {

    @Override
    public List<Modulo> consultarTodos() throws Exception {
        List<Modulo> listResult = new ArrayList();
        String strQuery = "FROM GnModulos";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnModulos> list = query.getResultList();
            for (GnModulos obj : list) {
                listResult.add(castEntidadNegocio(obj));
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
    public Modulo consultar(int id) throws Exception {
        Modulo objRes = null;
        try {
            objRes = castEntidadNegocio((GnModulos) getEntityManager().find(GnModulos.class, id));
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
    public int insertar(Modulo obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    @Override
    public void actualizar(Modulo obj) throws Exception {
        try {
            getEntityManager().merge(castNegocioEntidad(obj));
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public Modulo eliminar(int id) throws Exception {
        Modulo obj = null;
        try {
            GnModulos ent = getEntityManager().find(GnModulos.class, id);
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
    public List<Modulo> consultarPorTipo(char _tipo) throws Exception {
        List<Modulo> listResult = new ArrayList();
        String strQuery = "FROM GnModulos e"
                + " WHERE e.tipo = '" + _tipo + "'"
                + " ORDER BY e.orden";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnModulos> list = query.getResultList();
            for (GnModulos obj : list) {
                listResult.add(castEntidadNegocio(obj));
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
    public List<Modulo> consultarModulosPorUsuario(Usuario usuario) throws Exception {
        List<Modulo> list = new ArrayList();
        Map<Integer, PermisosMap> permisosMap = new LinkedHashMap();
        try {
            LinkedHashMap<Integer, Integer> hashPerfiles = new LinkedHashMap();
            String perfiles = "";
            if (usuario.getListaRoles() != null && !usuario.getListaRoles().isEmpty()) {
                for (UsuarioRol usuRol : usuario.getListaRoles()) {
                    if (usuRol.getRol() != null && usuRol.getRol().getRolesPerfilesList() != null) {
                        for (RolPerfil rolPerfil : usuRol.getRol().getRolesPerfilesList()) {
                            hashPerfiles.put(rolPerfil.getPerfiles().getId(), rolPerfil.getPerfiles().getId());
                        }
                    }
                }
            }
            boolean esPrimero = true;
            for (Map.Entry<Integer, Integer> entry : hashPerfiles.entrySet()) {
                if (esPrimero) {
                    perfiles = String.valueOf(entry.getKey());
                    esPrimero = false;
                } else {
                    perfiles += "," + entry.getKey();
                }
            }
            String strQuery = "FROM GnPermisos AS p "
                    + "INNER JOIN p.gnModulos AS m "
                    + "WHERE p.gnPerfiles.id IN (" + perfiles + ") "
                    + "AND m.activo = :modulo_activo "
                    + "ORDER BY m.tipo, m.nombre, m.orden";
            Query query = getEntityManager().createQuery(strQuery)
                    .setParameter("modulo_activo", true);
            List<Object> listObjs = query.getResultList();
            for (Object list1 : listObjs) {
                Permiso per = PermisoServicio.castEntidadNegocio((GnPermisos) ((Object[]) list1)[0]);
                Modulo _mod = castEntidadNegocioSinHerencia((GnModulos) ((Object[]) list1)[1]);
                if (_mod.isActivo() && per.getPrivilegios().contains("L")) {
                    if (permisosMap.containsKey(_mod.getId())) {
                        PermisosMap value = permisosMap.get(_mod.getId());
                        permisosMap.put(_mod.getId(), new PermisosMap(value.getPrivilegios() + per.getPrivilegios(), _mod));
                    } else {
                        permisosMap.put(_mod.getId(), new PermisosMap(per.getPrivilegios(), _mod));
                    }
                }
            }
            for (Map.Entry<Integer, PermisosMap> entry : permisosMap.entrySet()) {
                Modulo _mod = entry.getValue().getMod();
                _mod.setPrivilegios(entry.getValue().getPrivilegios());
                list.add(_mod);
            }
        } catch (NoResultException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    @Override
    public Modulo consultarArbolModulo() throws Exception {
        Modulo _modPadre = new Modulo();
        try {
            _modPadre = consultar(1);
            _modPadre.setModulosHijos(consultaRecursivaModulosPorPadre(_modPadre));
        } catch (NoResultException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return _modPadre;
    }

    @Override
    public Modulo consultarArbolModuloPorUsuario(Usuario usuario) throws Exception {
        Modulo _modPadre = new Modulo();
        try {
            _modPadre = consultar(1);
            _modPadre.setModulosHijos(consultaRecursivaModulosPorPadreYPermisos(_modPadre, usuario));
        } catch (NoResultException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return _modPadre;
    }

    /**
     * Método para consultar de manera recursiva los módulos segun si padre y
     * los permisos asignados al usuario
     *
     * @param moduleFather
     * @param usuario
     * @return
     * @throws java.lang.Exception
     */
    private List<Modulo> consultaRecursivaModulosPorPadreYPermisos(Modulo moduloPadre, Usuario usuario) throws java.lang.Exception {
        List<Modulo> listaElement = new ArrayList<>();
        Map<Integer, PermisosMap> permisosMap = new LinkedHashMap();
        try {
            LinkedHashMap<Integer, Integer> hashPerfiles = new LinkedHashMap();
            String perfiles = "";
            if (usuario.getListaRoles() != null && !usuario.getListaRoles().isEmpty()) {
                for (UsuarioRol usuRol : usuario.getListaRoles()) {
                    if (usuRol.getRol() != null && usuRol.getRol().getRolesPerfilesList() != null) {
                        for (RolPerfil rolPerfil : usuRol.getRol().getRolesPerfilesList()) {
                            hashPerfiles.put(rolPerfil.getPerfiles().getId(), rolPerfil.getPerfiles().getId());
                        }
                    }
                }
            }
            boolean esPrimero = true;
            for (Map.Entry<Integer, Integer> entry : hashPerfiles.entrySet()) {
                if (esPrimero) {
                    perfiles = String.valueOf(entry.getKey());
                    esPrimero = false;
                } else {
                    perfiles += "," + entry.getKey();
                }
            }
            String strQuery = "FROM GnPermisos AS p "
                    + "INNER JOIN p.gnModulos m "
                    + "WHERE p.gnPerfiles.id IN (" + perfiles + ") "
                    + "AND m.gnModulosId.id = " + moduloPadre.getId() + " "
                    + "AND m.activo = :modulo_activo "
                    + "ORDER BY m.orden, m.nombre";
            Query query = getEntityManager().createQuery(strQuery)
                    .setParameter("modulo_activo", true);
            List<Object> list = query.getResultList();
            for (Object list1 : list) {
                GnPermisos _permit = (GnPermisos) ((Object[]) list1)[0];
                Modulo _mod = castEntidadNegocioSinHerencia((GnModulos) ((Object[]) list1)[1]);
                if (_mod.getTipo() == Modulo.TIPO_MODULO) {
                    _mod.setModulosHijos(consultaRecursivaModulosPorPadreYPermisos(_mod, usuario));
                }
                if (permisosMap.containsKey(_mod.getId())) {
                    PermisosMap value = permisosMap.get(_mod.getId());
                    permisosMap.put(_mod.getId(), new PermisosMap(value.getPrivilegios() + _permit.getPrivilegios(), _mod));
                } else {
                    permisosMap.put(_mod.getId(), new PermisosMap(_permit.getPrivilegios(), _mod));
                }
            }
            for (Map.Entry<Integer, PermisosMap> entry : permisosMap.entrySet()) {
                Integer key = entry.getKey();
                PermisosMap value = entry.getValue();
                if (value.getPrivilegios().contains("L")) {
                    if (value.getMod().getTipo() == Modulo.TIPO_MODULO) {
                        value.getMod().setModulosHijos(consultaRecursivaModulosPorPadreYPermisos(value.getMod(), usuario));
                    }
                    listaElement.add(value.getMod());
                }
            }

        } catch (NoResultException e) {
            throw e;
        } catch (PersistenceException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return listaElement;
    }

    /**
     * Método para consultar de manera recursiva los módulos segun si padre y
     * los permisos asignados al usuario
     *
     * @param moduleFather
     * @param usuario
     * @return
     * @throws java.lang.Exception
     */
    private List<Modulo> consultaRecursivaModulosPorPadre(Modulo moduloPadre) throws java.lang.Exception {
        List<Modulo> listaElement = new ArrayList<>();
        try {
            String strQuery = "SELECT DISTINCT m "
                    + "FROM GnPermisos p "
                    + "INNER JOIN p.gnModulos m "
                    + "WHERE m.gnModulosId.id = " + moduloPadre.getId() + " "
                    + "ORDER BY m.orden, m.nombre";
            Query query = getEntityManager().createQuery(strQuery);
            List<GnModulos> list = query.getResultList();
            for (GnModulos per : list) {
                Modulo _mod = castEntidadNegocio(per);
                if (_mod.getTipo() == Modulo.TIPO_MODULO) {
                    _mod.setModulosHijos(consultaRecursivaModulosPorPadre(_mod));
                }
                listaElement.add(_mod);
            }

        } catch (NoResultException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return listaElement;
    }
    
    public static Modulo castEntidadNegocioSinHerencia(GnModulos per) {
        Modulo obj = new Modulo();
        obj.setModuloPadre(per.getGnModulosId() != null ? new Modulo(per.getGnModulosId().getId(), per.getGnModulosId().getNombre()) : null);
        obj.setId(per.getId());
        obj.setTipo(per.getTipo() != null ? per.getTipo().charAt(0) : 0);
        obj.setNombre(per.getNombre());
        obj.setDescripcion(per.getDescripcion());
        obj.setActivo(per.getActivo());
        obj.setPrivilegios(per.getPrivilegios());
        obj.setOrden(per.getOrden());
        obj.setUrl(per.getUrl());
        obj.setIcono(per.getIcono());
        obj.setVersion(per.getVersion());
        obj.setFechaVersion(per.getFechaVersion());
        obj.setAdicional1Nombre(per.getAdicional1Nombre());
        obj.setAdicional2Nombre(per.getAdicional2Nombre());
        obj.setAdicional3Nombre(per.getAdicional3Nombre());
        obj.setAdicional4Nombre(per.getAdicional4Nombre());
        obj.setAdicional5Nombre(per.getAdicional5Nombre());
        obj.setAdicional6Nombre(per.getAdicional6Nombre());
        obj.setAdicional7Nombre(per.getAdicional7Nombre());
        obj.setAdicional8Nombre(per.getAdicional8Nombre());
        obj.setAdicional9Nombre(per.getAdicional9Nombre());
        obj.setAdicional10Nombre(per.getAdicional10Nombre());
        obj.setAdicional11Nombre(per.getAdicional11Nombre());
        obj.setAdicional12Nombre(per.getAdicional12Nombre());
        obj.setAdicional13Nombre(per.getAdicional13Nombre());
        obj.setAdicional14Nombre(per.getAdicional14Nombre());
        obj.setAdicional15Nombre(per.getAdicional15Nombre());
        obj.setAdicional16Nombre(per.getAdicional16Nombre());
        obj.setAdicional17Nombre(per.getAdicional17Nombre());
        obj.setAdicional18Nombre(per.getAdicional18Nombre());
        obj.setAdicional19Nombre(per.getAdicional19Nombre());
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static Modulo castEntidadNegocio(GnModulos per) {
        Modulo obj = new Modulo();
        obj.setModuloPadre(per.getGnModulosId() != null ? castEntidadNegocio(per.getGnModulosId()) : null);
        obj.setId(per.getId());
        obj.setTipo(per.getTipo() != null ? per.getTipo().charAt(0) : 0);
        obj.setNombre(per.getNombre());
        obj.setDescripcion(per.getDescripcion());
        obj.setActivo(per.getActivo());
        obj.setPrivilegios(per.getPrivilegios());
        obj.setOrden(per.getOrden());
        obj.setUrl(per.getUrl());
        obj.setIcono(per.getIcono());
        obj.setVersion(per.getVersion());
        obj.setFechaVersion(per.getFechaVersion());
        obj.setAdicional1Nombre(per.getAdicional1Nombre());
        obj.setAdicional2Nombre(per.getAdicional2Nombre());
        obj.setAdicional3Nombre(per.getAdicional3Nombre());
        obj.setAdicional4Nombre(per.getAdicional4Nombre());
        obj.setAdicional5Nombre(per.getAdicional5Nombre());
        obj.setAdicional6Nombre(per.getAdicional6Nombre());
        obj.setAdicional7Nombre(per.getAdicional7Nombre());
        obj.setAdicional8Nombre(per.getAdicional8Nombre());
        obj.setAdicional9Nombre(per.getAdicional9Nombre());
        obj.setAdicional10Nombre(per.getAdicional10Nombre());
        obj.setAdicional11Nombre(per.getAdicional11Nombre());
        obj.setAdicional12Nombre(per.getAdicional12Nombre());
        obj.setAdicional13Nombre(per.getAdicional13Nombre());
        obj.setAdicional14Nombre(per.getAdicional14Nombre());
        obj.setAdicional15Nombre(per.getAdicional15Nombre());
        obj.setAdicional16Nombre(per.getAdicional16Nombre());
        obj.setAdicional17Nombre(per.getAdicional17Nombre());
        obj.setAdicional18Nombre(per.getAdicional18Nombre());
        obj.setAdicional19Nombre(per.getAdicional19Nombre());
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static GnModulos castNegocioEntidad(Modulo obj) {
        GnModulos per = new GnModulos();
        per.setGnModulosId((obj.getModuloPadre() != null) ? castNegocioEntidad(obj.getModuloPadre()) : null);
        per.setId(obj.getId());
        per.setTipo(String.valueOf(obj.getTipo()));
        per.setNombre(obj.getNombre());
        per.setDescripcion(obj.getDescripcion());
        per.setActivo(obj.isActivo());
        per.setPrivilegios(obj.getPrivilegios());
        per.setOrden(obj.getOrden());
        per.setUrl(obj.getUrl());
        per.setIcono(obj.getIcono());
        per.setVersion(obj.getVersion());
        per.setFechaVersion(obj.getFechaVersion());
        //Auditoría
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }

}
