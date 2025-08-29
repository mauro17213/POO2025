/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Perfil;
import com.saviasaludeps.savia.dominio.administracion.Permiso;
import com.saviasaludeps.savia.negocio.administracion.PermisoRemoto;
import com.saviasaludeps.savia.ejb.entidades.GnModulos;
import com.saviasaludeps.savia.ejb.entidades.GnPerfiles;
import com.saviasaludeps.savia.ejb.entidades.GnPermisos;
import com.saviasaludeps.savia.ejb.entidades.GnPermisosPK;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(PermisoRemoto.class)
public class PermisoServicio extends GenericoServicio implements PermisoRemoto {

    @Override
    public Permiso consultar(int idModulo, int idPerfil) throws Exception {
        GnPermisosPK pk = new GnPermisosPK(idModulo, idPerfil);
        Permiso objRes = null;
        try {
            GnPermisos permiso = (GnPermisos) getEntityManager().find(GnPermisos.class, pk);
            if (permiso != null) {
                objRes = castEntidadNegocio((GnPermisos) getEntityManager().find(GnPermisos.class, pk));
            }
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
    public void insertar(Permiso obj) throws Exception {
        try {
//            PermisosPK pk = new PermisosPK(obj.getModulo().getId(), obj.getPerfil().getId());
            getEntityManager().merge(castNegocioEntidad(obj));
        } catch (NoResultException e) {
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizar(Permiso obj) throws Exception {
        try {
//            getEntityManager().merge(castNegocioEntidad(obj));
            String hql = "UPDATE GnPermisos SET "
                    + "privilegios = :privilegios "
                    + "WHERE gnModulos.id = :moduloId "
                    + "AND gnPerfiles.id = :perfilId ";
            Query query = getEntityManager().createQuery(hql);
            String perm = privilegiosAString(obj);
            query.setParameter("privilegios", perm);
            query.setParameter("moduloId", obj.getModulo().getId());
            query.setParameter("perfilId", obj.getPerfil().getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public Permiso eliminar(int idModulo, int idPerfil) throws Exception {
        Permiso obj = null;
        try {
            GnPermisosPK pk = new GnPermisosPK(idModulo, idPerfil);
            GnPermisos ent = getEntityManager().find(GnPermisos.class, pk);
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

    private List<Permiso> permisosModulos = new ArrayList();

    @Override
    public List<Permiso> consultarPermisosModulos(int idPerfil) throws Exception {
        GnPerfiles _per = (GnPerfiles) getEntityManager().find(GnPerfiles.class, idPerfil);
        GnModulos _modPadre = (GnModulos) getEntityManager().find(GnModulos.class, 1);
        permisosModulos = new ArrayList();
        try {
            if (_modPadre.getActivo()) {
                consultarPermisosModulos(_per, _modPadre);
            }
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return permisosModulos;
    }

    private void consultarPermisosModulos(GnPerfiles _per, GnModulos _modPadre) throws Exception {
        if (_modPadre == null) {
            _modPadre = (GnModulos) getEntityManager().find(GnModulos.class, 1);
            permisosModulos = new ArrayList();
        }
        String strQuery1 = "FROM GnModulos m "
                + "WHERE m.gnModulosId.id = " + _modPadre.getId() + " "
                + "AND m.activo = 1 "
                + "ORDER BY m.orden";
        Query query1 = getEntityManager().createQuery(strQuery1);
        List<GnModulos> list1 = query1.getResultList();
        for (GnModulos _mod : list1) {
            if (_mod.getActivo()) {
                String strQuery2 = "FROM GnPermisos p "
                        + "WHERE p.gnPerfiles.id = " + _per.getId() + " "
                        + "AND p.gnModulos.id = " + _mod.getId();
                Query query2 = getEntityManager().createQuery(strQuery2);
                List<GnPermisos> list2 = query2.getResultList();
                GnPermisos _permiso = new GnPermisos();
                if (list2.size() > 0) {
                    _permiso = list2.get(0);
                } else {
                    _permiso.setPrivilegios("");
                }
                _mod.setNombre(_mod.getNombre());
                _permiso.setGnModulos(_mod);
                _permiso.setGnPerfiles(_per);
                Permiso objetoPermiso = castEntidadNegocio(_permiso);
                objetoPermiso.setModulo(ModuloServicio.castEntidadNegocio(_mod));
                permisosModulos.add(objetoPermiso);
                consultarPermisosModulos(_per, _mod);
            }
        }
    }

    public static Permiso castEntidadNegocio(GnPermisos _per) {
        Permiso _obj = new Permiso();
        //Modulo Padre
        Modulo _mod = new Modulo();
        _mod.setId(_per.getGnModulos().getId());
        _mod.setNombre(_per.getGnModulos().getNombre());
        _mod.setDescripcion(_per.getGnModulos().getDescripcion());
        _mod.setTipo(_per.getGnModulos().getTipo().charAt(0));
        _mod.setPrivilegios(_per.getGnModulos().getPrivilegios());
        _obj.setModulo(_mod);
        //Perfil Padre
        _obj.setPerfil(new Perfil(_per.getGnPerfiles().getId()));
        //Privilegios
        _obj.setPrivilegios(_per.getPrivilegios());
        privilegiosABoolean(_obj, _per.getPrivilegios());
        return _obj;
    }

    public static GnPermisos castNegocioEntidad(Permiso _obj) {
        GnPermisos _per = new GnPermisos();
        //Id
        GnPermisosPK _perId = new GnPermisosPK(_obj.getModulo().getId(), _obj.getPerfil().getId());
        _per.setGnPermisosPK(_perId);
        //Modulo Padre
        GnModulos _mod = new GnModulos();
        _mod.setId(_obj.getModulo().getId());
        _per.setGnModulos(_mod);
        //Perfil Padre
        GnPerfiles _perf = new GnPerfiles();
        _perf.setId(_obj.getPerfil().getId());
        _per.setGnPerfiles(_perf);
        //Privilegios 
        String privilegios = privilegiosAString(_obj);
        privilegios = privilegios.length() <= 0 ? " " : privilegios;
        _per.setPrivilegios(privilegios);
        return _per;
    }

    private static String privilegiosAString(Permiso _obj) {
        String privilegios = "";
        privilegios = (_obj.isListarPermiso() ? "L" : "");
        privilegios = (_obj.isVerPermiso() ? privilegios + "V" : privilegios);
        privilegios = (_obj.isCrearPermiso() ? privilegios + "C" : privilegios);
        privilegios = (_obj.isEditarPermiso() ? privilegios + "E" : privilegios);
        privilegios = (_obj.isBorrarPermiso() ? privilegios + "B" : privilegios);
        privilegios = (_obj.isAccionAdicional1Permiso() ? privilegios + "O" : privilegios);
        privilegios = (_obj.isAccionAdicional2Permiso() ? privilegios + "P" : privilegios);
        privilegios = (_obj.isAccionAdicional3Permiso() ? privilegios + "Q" : privilegios);
        privilegios = (_obj.isAccionAdicional4Permiso() ? privilegios + "R" : privilegios);
        privilegios = (_obj.isAccionAdicional5Permiso() ? privilegios + "S" : privilegios);
        privilegios = (_obj.isAccionAdicional6Permiso() ? privilegios + "T" : privilegios);
        privilegios = (_obj.isAccionAdicional7Permiso() ? privilegios + "U" : privilegios);
        privilegios = (_obj.isAccionAdicional8Permiso() ? privilegios + "W" : privilegios);
        privilegios = (_obj.isAccionAdicional9Permiso() ? privilegios + "X" : privilegios);
        privilegios = (_obj.isAccionAdicional10Permiso() ? privilegios + "Y" : privilegios);
        privilegios = (_obj.isAccionAdicional11Permiso() ? privilegios + "Z" : privilegios);
        privilegios = (_obj.isAccionAdicional12Permiso() ? privilegios + "A" : privilegios);
        privilegios = (_obj.isAccionAdicional13Permiso() ? privilegios + "D" : privilegios);
        privilegios = (_obj.isAccionAdicional14Permiso() ? privilegios + "F" : privilegios);
        privilegios = (_obj.isAccionAdicional15Permiso() ? privilegios + "H" : privilegios);
        privilegios = (_obj.isAccionAdicional16Permiso() ? privilegios + "I" : privilegios);
        privilegios = (_obj.isAccionAdicional17Permiso() ? privilegios + "J" : privilegios);
        privilegios = (_obj.isAccionAdicional18Permiso() ? privilegios + "K" : privilegios);
        privilegios = (_obj.isAccionAdicional19Permiso() ? privilegios + "N" : privilegios);
        return privilegios;
    }

    private static void privilegiosABoolean(Permiso _obj, String _priv) {
        _obj.setListarPermiso(_priv.contains("L"));
        _obj.setVerPermiso(_priv.contains("V"));
        _obj.setCrearPermiso(_priv.contains("C"));
        _obj.setEditarPermiso(_priv.contains("E"));
        _obj.setBorrarPermiso(_priv.contains("B"));
        _obj.setAccionAdicional1Permiso(_priv.contains("O"));
        _obj.setAccionAdicional2Permiso(_priv.contains("P"));
        _obj.setAccionAdicional3Permiso(_priv.contains("Q"));
        _obj.setAccionAdicional4Permiso(_priv.contains("R"));
        _obj.setAccionAdicional5Permiso(_priv.contains("S"));
        _obj.setAccionAdicional6Permiso(_priv.contains("T"));
        _obj.setAccionAdicional7Permiso(_priv.contains("U"));
        _obj.setAccionAdicional8Permiso(_priv.contains("W"));
        _obj.setAccionAdicional9Permiso(_priv.contains("X"));
        _obj.setAccionAdicional10Permiso(_priv.contains("Y"));
        _obj.setAccionAdicional11Permiso(_priv.contains("Z"));
        _obj.setAccionAdicional12Permiso(_priv.contains("A"));
        _obj.setAccionAdicional13Permiso(_priv.contains("D"));
        _obj.setAccionAdicional14Permiso(_priv.contains("F"));
        _obj.setAccionAdicional15Permiso(_priv.contains("H"));
        _obj.setAccionAdicional16Permiso(_priv.contains("I"));
        _obj.setAccionAdicional17Permiso(_priv.contains("J"));
        _obj.setAccionAdicional18Permiso(_priv.contains("K"));
        _obj.setAccionAdicional19Permiso(_priv.contains("N"));
    }

}
