/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.Perfil;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.administracion.PerfilRemoto;
import com.saviasaludeps.savia.ejb.entidades.GnPerfiles;
import com.saviasaludeps.savia.ejb.entidades.GnRolesPerfiles;
import com.saviasaludeps.savia.ejb.entidades.GnRolesUsuario;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
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
@Remote(PerfilRemoto.class)
public class PerfilServicio extends GenericoServicio implements PerfilRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM GnPerfiles p "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND p.nombre LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND p.activo = " + (String) e.getValue() + " ";
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
    public List<Perfil> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<Perfil> listResult = new ArrayList();
        try {
            String strQuery = "FROM GnPerfiles p "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND p.nombre LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND p.activo = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.nombre ASC ";
            }
            List<GnPerfiles> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GnPerfiles per : list) {
                listResult.add(castEntidadNegocio(per));
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
    public Perfil consultar(int id) throws Exception {
        Perfil objRes = null;
        try {
            objRes = castEntidadNegocio((GnPerfiles) getEntityManager().find(GnPerfiles.class, id));
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
    public int insertar(Perfil obj) throws Exception {
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
    public void actualizar(Perfil obj) throws Exception {
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
    public Perfil eliminar(int id) throws Exception {
        Perfil obj = null;
        try {
            GnPerfiles per = getEntityManager().find(GnPerfiles.class, id);
            if (per != null) {
                obj = castEntidadNegocio(per);
                getEntityManager().remove(per);
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
    public List<Perfil> consultarTodos() throws Exception {
        List<Perfil> listResult = new ArrayList();
        String strQuery = "FROM GnPerfiles ORDER BY nombre";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnPerfiles> list = query.getResultList();
            for (GnPerfiles obj : list) {
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
    public List<Perfil> consultarListaPorUsuario(int idUsuario) throws java.lang.Exception {
        List<Perfil> listaPerfiles = new ArrayList();        
        try {
//            String strQuery = "FROM GnUsuarios u WHERE u.id = " + idUsuario;
//            Query query = getEntityManager().createQuery(strQuery);
//            GnUsuarios per = (GnUsuarios)query.getSingleResult();            
            GnUsuarios per = getEntityManager().find(GnUsuarios.class, idUsuario);
            if (per.getGnRolesUsuarioList() != null) {
                for (GnRolesUsuario rolUsu : per.getGnRolesUsuarioList()) {
                    if (rolUsu.getGnRolesId() != null 
                            && rolUsu.getGnRolesId().getGnRolesPerfilesList() != null) {
                        for (GnRolesPerfiles rolPer : rolUsu.getGnRolesId().getGnRolesPerfilesList()) {
                            listaPerfiles.add(castEntidadNegocio(rolPer.getGnPerfilesId()));
                        }
                    }
                }
            }
        } catch (NoResultException e) {
            listaPerfiles = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaPerfiles;
    }

    public static Perfil castEntidadNegocio(GnPerfiles per) {
        Perfil obj = new Perfil();
        if (per != null) {
            obj.setId(per.getId());
            obj.setNombre(per.getNombre());
            obj.setDescripcion(per.getDescripcion());
            obj.setActivo(per.getActivo());
            //Auditoria
            obj.setUsuarioCrea(per.getUsuarioCrea());
            obj.setTerminalCrea(per.getTerminalCrea());
            obj.setFechaHoraCrea(per.getFechaHoraCrea());
            obj.setUsuarioModifica(per.getUsuarioModifica());
            obj.setTerminalModifica(per.getTerminalModifica());
            obj.setFechaHoraModifica(per.getFechaHoraModifica());
        } else {
            obj.setId(0);
        }
        return obj;
    }

    public static GnPerfiles castNegocioEntidad(Perfil obj) {
        GnPerfiles per = new GnPerfiles();
        per.setId(obj.getId());
        per.setNombre(obj.getNombre());
        per.setDescripcion(obj.getDescripcion());
        per.setActivo(obj.isActivo());
        //Auditoria
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }

}
