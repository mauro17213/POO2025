package com.saviasaludeps.savia.ejb.servicios.recobro;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.recobro.RcoGrupo;
import com.saviasaludeps.savia.dominio.recobro.RcoGrupoUsuario;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.entidades.RcoGrupoUsuarios;
import com.saviasaludeps.savia.ejb.entidades.RcoGrupos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.recobro.RcoGrupoUsuarioRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Stiven Giraldo
 */
@Stateless
@Remote(RcoGrupoUsuarioRemoto.class)
public class RcoGrupoUsuarioServicio extends GenericoServicio implements RcoGrupoUsuarioRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(m) FROM RcoGrupoUsuarios m ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND m.id = " + (String) e.getValue() + " ";
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
    public List<RcoGrupoUsuario> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<RcoGrupoUsuario> listResult = new ArrayList();
        try {
            String strQuery = "SELECT m FROM RcoGrupoUsuarios m ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND m.id = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "m." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "m.id ASC ";
            }
            List<RcoGrupoUsuarios> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (RcoGrupoUsuarios per : list) {
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
    public RcoGrupoUsuario consultar(int id) throws Exception {
        RcoGrupoUsuario objRes = null;
        try {
            objRes = castEntidadNegocio((RcoGrupoUsuarios) getEntityManager().find(RcoGrupoUsuarios.class, id));
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
    public int insertar(RcoGrupoUsuario obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e.getMessage());
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    @Override
    public void actualizar(RcoGrupoUsuario obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE RcoGrupoUsuarios a SET ";
            strQuery += "a.activo = :activo ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);            
            query.setParameter("activo", obj.isActivo());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }

    }

    @Override
    public RcoGrupoUsuario eliminar(int id) throws java.lang.Exception {
        RcoGrupoUsuario obj = null;
        try {
            RcoGrupoUsuarios ent = getEntityManager().find(RcoGrupoUsuarios.class, id);
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

    private RcoGrupoUsuario castEntidadNegocio(RcoGrupoUsuarios entidad) {
        RcoGrupoUsuario negocio = new RcoGrupoUsuario();
        negocio.setId(entidad.getId());
        negocio.setRcoGrupoId(new RcoGrupo(entidad.getRcoGruposId().getId()));
        negocio.setGnUsuarioId(new Usuario(entidad.getGnUsuariosId().getId()));
        negocio.getGnUsuarioId().setNombre(entidad.getGnUsuariosId().getNombre());
        negocio.setActivo(entidad.getActivo());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;

    }

    private RcoGrupoUsuarios castNegocioEntidad(RcoGrupoUsuario negocio) {
        RcoGrupoUsuarios entidad = new RcoGrupoUsuarios();
        entidad.setRcoGruposId(new RcoGrupos(negocio.getRcoGrupoId().getId()));
        entidad.setGnUsuariosId(new GnUsuarios(negocio.getGnUsuarioId().getId()));
        entidad.setActivo(negocio.isActivo());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

    @Override
    public List<RcoGrupoUsuario> consultarListaPorIdGrupo(int idGrupo) throws Exception {
        List<RcoGrupoUsuario> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM RcoGrupoUsuarios p "
                    + "WHERE  p.rcoGruposId.id = " + idGrupo + " ORDER BY p.id DESC";
            List<RcoGrupoUsuarios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (RcoGrupoUsuarios rcoGrupoUsuarios : list) {
                listaResultados.add(castEntidadNegocio(rcoGrupoUsuarios));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }
}
