/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.tutela;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuGrupo;
import com.saviasaludeps.savia.dominio.tutela.TuGrupoUsuario;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.entidades.TuGrupoEstados;
import com.saviasaludeps.savia.ejb.entidades.TuGrupoUsuarios;
import com.saviasaludeps.savia.ejb.entidades.TuGrupos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.tutela.TuGrupoUsuarioRemoto;
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
@Remote(TuGrupoUsuarioRemoto.class)
public class TuGrupoUsuarioServicio extends GenericoServicio implements TuGrupoUsuarioRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(t.id) FROM TuGrupoUsuarios t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND t.nombre LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += "AND t.descripcion LIKE '" + (String) e.getValue() + "%' ";
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
    public List<TuGrupoUsuario> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<TuGrupoUsuario> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t FROM TuGrupoUsuarios t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND t.nombre LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += "AND t.descripcion LIKE '" + (String) e.getValue() + "%' ";
                            break;

                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "t." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "t.id ASC";
            }
            List<TuGrupoUsuarios> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (TuGrupoUsuarios per : list) {
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
            String strQuery = "SELECT COUNT(t.id) FROM TuGrupoUsuarios t "
                    + "WHERE t.id > 0 AND t.tuGruposId.id = :tuGrupoId ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tuGruposId.id":
                            strQuery += "AND t.tuGruposId.id = '" + (String) e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += "AND t.tipo = '" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery).
                    setParameter("tuGrupoId", paramConsulta.getParametroConsulta1()).getSingleResult();
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
    public List<TuGrupoUsuario> consultarListaPorGrupo(ParamConsulta paramConsulta) throws Exception {
        List<TuGrupoUsuario> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t FROM TuGrupoUsuarios t "
                    + "WHERE t.id > 0 ";

            if (paramConsulta.getParametroConsulta4() != null) {
                strQuery = "SELECT t FROM TuGrupoUsuarios t LEFT JOIN TuGrupoEstados tge on tge.tuGruposId.id = t.id  AND tge.maeEstadoId = :maeEstadoId ";
            }
            
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND t.tuGruposId.id = :tuGrupoId ";
            }
            
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += " AND t.tipo = :tipo ";
            }
            
            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += " AND t.activo = :activo ";
            }
            
            if (paramConsulta.getParametroConsulta5() != null) {
                strQuery += " AND t.gnUsuariosId.id = :idUsuario ";
            }
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tuGruposId.id":
                            strQuery += "AND t.tuGruposId.id = '" + (String) e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += "AND t.tipo = '" + (String) e.getValue() + "%' ";
                            break;

                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "t." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "t.id DESC";
            }

            Query query = getEntityManager().createQuery(strQuery);

            if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("tuGrupoId", paramConsulta.getParametroConsulta1());
            }
            
            if (paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("tipo", paramConsulta.getParametroConsulta2());
            }
            
            if (paramConsulta.getParametroConsulta3() != null) {
                query.setParameter("activo", paramConsulta.getParametroConsulta3());
            }
            
            if (paramConsulta.getParametroConsulta4() != null) {
                query.setParameter("maeEstadoId", paramConsulta.getParametroConsulta4());
            }
            
            if (paramConsulta.getParametroConsulta5() != null) {
                query.setParameter("idUsuario", paramConsulta.getParametroConsulta5());
            }

            List<TuGrupoUsuarios> list = query
                    .getResultList();
            for (TuGrupoUsuarios per : list) {
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
    public TuGrupoUsuario consultar(int id) throws Exception {
        TuGrupoUsuario objRes = null;
        try {
            TuGrupoUsuarios per = getEntityManager().find(TuGrupoUsuarios.class, id);
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
    public int insertar(TuGrupoUsuario obj) throws Exception {
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
    public void actualizar(TuGrupoUsuario obj) throws Exception {
        try {
            String hql = "UPDATE TuGrupoUsuarios SET "
                    + " tipo = :tipo,"
                    + " activo = :activo,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("tipo", obj.getTipo());
            query.setParameter("activo", obj.getActivo());
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
    public TuGrupoUsuario eliminar(int id) throws Exception {
        TuGrupoUsuario obj = null;
        try {
            TuGrupoUsuarios ent = getEntityManager().find(TuGrupoUsuarios.class, id);
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
    
    
    public static TuGrupoUsuario castEntidadNegocio(TuGrupoUsuarios per) {
        TuGrupoUsuario obj = new TuGrupoUsuario();
        if(per.getTuGruposId() != null){
           obj.setTuGrupo(new TuGrupo(per.getTuGruposId().getId()));
        }
        if (per.getGnUsuariosId() != null) {
            Usuario usuario = new Usuario(per.getGnUsuariosId().getId());
            usuario.setNombre(per.getGnUsuariosId().getNombre());
            obj.setUsuario(usuario);
        }
        obj.setId(per.getId());
        obj.setActivo(per.getActivo());
        obj.setTipo(per.getTipo());
        
        if(per.getTuGruposId() != null){
           if(per.getTuGruposId().getTuGrupoEstadosList() != null && !per.getTuGruposId().getTuGrupoEstadosList().isEmpty() ){
               TuGrupoEstados estadoGrupo = per.getTuGruposId().getTuGrupoEstadosList().get(0);
               obj.setIdMaEstadoGrupo(estadoGrupo.getMaeEstadoId());
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
    
    public static TuGrupoUsuarios castNegocioEntidad(TuGrupoUsuario obj) {
        TuGrupoUsuarios per = new TuGrupoUsuarios();
        if(obj.getUsuario() != null){
           per.setGnUsuariosId(new GnUsuarios(obj.getUsuario().getId()));
        }
        if(obj.getTuGrupo() != null){
           per.setTuGruposId(new TuGrupos(obj.getTuGrupo().getId()));
        }
        per.setId(obj.getId());
        per.setTipo(obj.getTipo());
        per.setActivo(obj.getActivo());
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
