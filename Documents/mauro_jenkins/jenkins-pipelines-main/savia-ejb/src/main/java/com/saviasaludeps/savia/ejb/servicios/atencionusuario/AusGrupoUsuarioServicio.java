/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.atencionusuario;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.atencionusuario.AusGrupo;
import com.saviasaludeps.savia.dominio.atencionusuario.AusGrupoUsuario;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.entidades.AusGrupoUsuarios;
import com.saviasaludeps.savia.ejb.entidades.AusGrupos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.atencionusuario.AusGrupoUsuarioRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jperez
 */
@Stateless
@Remote(AusGrupoUsuarioRemoto.class)
public class AusGrupoUsuarioServicio extends GenericoServicio implements AusGrupoUsuarioRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(agu.id) FROM AusGrupoUsuarios agu "
                    + "WHERE agu.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "activo":
                            strQuery += "AND agu.nombre LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += "AND agu.descripcion LIKE '" + (String) e.getValue() + "%' ";
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
    public List<AusGrupoUsuario> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AusGrupoUsuario> listResult = new ArrayList();
        try {
            String strQuery = "SELECT agu FROM AusGrupoUsuarios agu "
                    + "WHERE agu.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND agu.nombre LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += "AND agu.descripcion LIKE '" + (String) e.getValue() + "%' ";
                            break;

                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "agu." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "agu.id ASC";
            }
            List<AusGrupoUsuarios> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AusGrupoUsuarios per : list) {
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
    public int consultarCantidadListaPorGrupo(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(agu.id) FROM AusGrupoUsuarios agu "
                    + "WHERE agu.id > 0 AND agu.ausGruposId.id = :ausGrupoId ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tuGruposId.id":
                            strQuery += "AND agu.ausGruposId.id = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery).
                    setParameter("ausGrupoId", paramConsulta.getParametroConsulta1()).getSingleResult();
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
    public List<AusGrupoUsuario> consultarListaPorGrupo(ParamConsulta paramConsulta) throws Exception {
        List<AusGrupoUsuario> listResult = new ArrayList();
        try {
            String strQuery = "SELECT agu FROM AusGrupoUsuarios agu "
                    + "WHERE agu.id > 0 AND agu.ausGruposId.id = :ausGrupoId ";
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tuGruposId.id":
                            strQuery += "AND agu.ausGruposId.id = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "agu." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "agu.id DESC";
            }

            Query query = getEntityManager().createQuery(strQuery);

            if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("ausGrupoId", paramConsulta.getParametroConsulta1());
            }
            
            List<AusGrupoUsuarios> list = query
                    .getResultList();
            for (AusGrupoUsuarios per : list) {
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
    public AusGrupoUsuario consultar(int id) throws Exception {
        AusGrupoUsuario objRes = null;
        try {
            AusGrupoUsuarios per = getEntityManager().find(AusGrupoUsuarios.class, id);
            if (per != null) {
                objRes = castEntidadNegocio(per);
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
    public int insertar(AusGrupoUsuario obj) throws Exception {
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
    public void actualizar(AusGrupoUsuario obj) throws Exception {
        try {
            String hql = "UPDATE AusGrupoUsuarios SET "
                    + " activo = :activo,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("activo", obj.isActivo());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
   
    @Override
    public AusGrupoUsuario eliminar(int id) throws Exception {
        AusGrupoUsuario obj = null;
        try {
            AusGrupoUsuarios ent = getEntityManager().find(AusGrupoUsuarios.class, id);
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
    
    
    @SuppressWarnings("null")
    public static AusGrupoUsuario castEntidadNegocio(AusGrupoUsuarios per) {
        AusGrupoUsuario obj = new AusGrupoUsuario();
        if(per.getAusGruposId() != null){
           obj.setAusGrupo(new AusGrupo(per.getAusGruposId().getId()));
        }
        if (per.getGnUsuariosId() != null) {
            Usuario usuario = new Usuario(per.getGnUsuariosId().getId());
            usuario.setNombre(per.getGnUsuariosId().getNombre());
            usuario.setUsuario(per.getGnUsuariosId().getUsuario());
            obj.setUsuario(usuario);
        }
        obj.setId(per.getId());
        obj.setActivo(per.getActivo());
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }
    
    public static AusGrupoUsuarios castNegocioEntidad(AusGrupoUsuario obj) {
        AusGrupoUsuarios per = new AusGrupoUsuarios();
        if(obj.getUsuario() != null){
           per.setGnUsuariosId(new GnUsuarios(obj.getUsuario().getId()));
        }
        if(obj.getAusGrupo() != null){
           per.setAusGruposId(new AusGrupos(obj.getAusGrupo().getId()));
        }
        per.setId(obj.getId());
        per.setActivo(obj.isActivo());
        //Auditoría
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }

    @Override
    public AusGrupoUsuario consultarUsuarioCierreGrupo(int idGrupo) throws java.lang.Exception {
        AusGrupoUsuario usuarioResult = null;
        try {
            String strQuery = "SELECT agu FROM AusGrupoUsuarios agu "
                    + " WHERE agu.activo = 1 AND agu.ausGruposId.tipo = " + AusGrupo.TIPO_CIERRE
                    + " AND agu.ausGruposId.id = " + idGrupo
                    + " ORDER BY agu.id DESC";

            Query query = getEntityManager().createQuery(strQuery);
            
            List<AusGrupoUsuarios> list = query
                    .getResultList();
            //configuramos el primer registro actualizado
            if (list != null && !list.isEmpty()) {
               usuarioResult = castEntidadNegocio(list.get(0));
            }
        } catch (NoResultException e) {
            usuarioResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return usuarioResult;
    }

    @Override
    public List<AusGrupoUsuario> consultarListaPorGrupo(int idGrupo) throws java.lang.Exception {
        List<AusGrupoUsuario> listResult = new ArrayList();
        try {
            String strQuery = "SELECT agu FROM AusGrupoUsuarios agu "
                    + " WHERE agu.activo = 1 AND agu.ausGruposId.id = :ausGrupoId "
                    + " ORDER BY agu.id DESC";

            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("ausGrupoId", idGrupo);
            
            List<AusGrupoUsuarios> list = query
                    .getResultList();
            for (AusGrupoUsuarios per : list) {
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
}
