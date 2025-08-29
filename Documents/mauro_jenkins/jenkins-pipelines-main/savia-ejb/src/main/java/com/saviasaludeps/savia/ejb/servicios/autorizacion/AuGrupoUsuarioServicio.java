/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupo;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoUsuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuGrupoUsuarios;
import com.saviasaludeps.savia.ejb.entidades.AuGrupos;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuGrupoUsuarioRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Stiven Giraldo
 */
@Stateless
@Remote(AuGrupoUsuarioRemoto.class)
public class AuGrupoUsuarioServicio extends GenericoServicio implements AuGrupoUsuarioRemoto {

    @Override
    public AuGrupoUsuario consultar(int id) throws Exception {
        AuGrupoUsuario objRes = null;
        try {
            objRes = castEntidadNegocio((AuGrupoUsuarios) getEntityManager().find(AuGrupoUsuarios.class, id));
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
    public int insertar(AuGrupoUsuario obj) throws Exception {
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
    public void actualizar(AuGrupoUsuario obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuGrupoUsuarios a SET ";
            strQuery += "a.auGruposId.id = :auGruposId ,";
            strQuery += "a.gnUsuariosId.id = :gnUsuariosId ,";
            strQuery += "a.activo = :activo ,";
            strQuery += "a.tipo = :tipo, ";
            strQuery += "a.maeTipoAuditorId = :maeTipoAuditorId,";
            strQuery += "a.maeTipoAuditorCodigo = :maeTipoAuditorCodigo,";
            strQuery += "a.maeTipoAuditorValor = :maeTipoAuditorValor";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("auGruposId", obj.getAuGrupo().getId());
            query.setParameter("gnUsuariosId", obj.getUsuario().getId());
            query.setParameter("activo", obj.isActivo());
            query.setParameter("tipo", obj.getTipo());
            query.setParameter("maeTipoAuditorId", obj.getMaeTipoAuditorId());
            query.setParameter("maeTipoAuditorCodigo", obj.getMaeTipoAuditorCodigo());
            query.setParameter("maeTipoAuditorValor", obj.getMaeTipoAuditorValor());
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
    public AuGrupoUsuario eliminar(int id) throws Exception {
        AuGrupoUsuario obj = null;
        try {
            AuGrupoUsuarios ent = getEntityManager().find(AuGrupoUsuarios.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            Exception(ELIMINAR, e);
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    private AuGrupoUsuario castEntidadNegocio(AuGrupoUsuarios entidad) {
        AuGrupoUsuario negocio = new AuGrupoUsuario();
        negocio.setId(entidad.getId());
        negocio.setAuGrupo(new AuGrupo(entidad.getAuGruposId().getId()));
        negocio.setUsuario(new Usuario(entidad.getGnUsuariosId().getId()));
        negocio.getUsuario().setNombre(entidad.getGnUsuariosId().getNombre());
        negocio.getUsuario().setUsuario(entidad.getGnUsuariosId().getUsuario());
        negocio.setActivo(entidad.getActivo());
        negocio.setTipo(entidad.getTipo());
        negocio.setMaeTipoAuditorId(entidad.getMaeTipoAuditorId());
        negocio.setMaeTipoAuditorCodigo(entidad.getMaeTipoAuditorCodigo());
        negocio.setMaeTipoAuditorValor(entidad.getMaeTipoAuditorValor());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }

    private AuGrupoUsuarios castNegocioEntidad(AuGrupoUsuario negocio) {
        AuGrupoUsuarios entidad = new AuGrupoUsuarios();
        entidad.setId(negocio.getId());
        entidad.setAuGruposId(new AuGrupos(negocio.getAuGrupo().getId()));
        entidad.setGnUsuariosId(new GnUsuarios(negocio.getUsuario().getId()));
        entidad.setActivo(negocio.isActivo());
        entidad.setTipo(negocio.getTipo());
        entidad.setMaeTipoAuditorId(negocio.getMaeTipoAuditorId());
        entidad.setMaeTipoAuditorCodigo(negocio.getMaeTipoAuditorCodigo());
        entidad.setMaeTipoAuditorValor(negocio.getMaeTipoAuditorValor());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

    @Override
    public List<AuGrupoUsuario> consultarListaPorIdGrupo(int idGrupo) throws Exception {
        List<AuGrupoUsuario> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuGrupoUsuarios p "
                    + "WHERE p.id > 0";
            strQuery += " AND p.auGruposId.id = " + idGrupo + " ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<AuGrupoUsuarios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuGrupoUsuarios usuarios : list) {
                listaResultados.add(castEntidadNegocio(usuarios));
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

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuGrupoUsuarios p ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "WHERE p.auGruposId.id = " + paramConsulta.getParametroConsulta1() + " ";
            } else {
                strQuery += "WHERE p.id > 0 ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "usuario.nombre":
                            strQuery += "AND p.gnUsuariosId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "usuario.usuario":
                            strQuery += "AND p.gnUsuariosId.usuario LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            cantidad = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            cantidad = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cantidad;
    }

    @Override
    public List<AuGrupoUsuario> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuGrupoUsuario> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuGrupoUsuarios p ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "WHERE p.auGruposId.id = " + paramConsulta.getParametroConsulta1() + " ";
            } else {
                strQuery += "WHERE p.id > 0 ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "usuario.nombre":
                            strQuery += "AND p.gnUsuariosId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "usuario.usuario":
                            strQuery += "AND p.gnUsuariosId.usuario LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }

            List<AuGrupoUsuarios> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuGrupoUsuarios grupoUsuario : list) {
                listaResultados.add(castEntidadNegocio(grupoUsuario));
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

    @Override
    public boolean validarUsuario(int idUsuario, int idGrupo) throws Exception {
        boolean existe = false;
        try {
            String strQuery = "FROM AuGrupoUsuarios p "
                    + "WHERE p.id > 0";
            strQuery += " AND p.auGruposId.id = " + idGrupo + " ";
            strQuery += " AND p.gnUsuariosId.id = " + idUsuario + " ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<AuGrupoUsuarios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            existe = list != null && !list.isEmpty();
        } catch (NoResultException e) {
            existe = false;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return existe;
    }

    @Override
    public boolean validarUsuarioTipo(int idUsuario, int idGrupo, int maeTipoAuditorId) throws Exception {
        boolean existe = false;
        try {
            String strQuery = "SELECT p FROM AuGrupoUsuarios p "
                    + "WHERE ";
            strQuery += " p.auGruposId.id =:idGrupo";
            strQuery += " AND p.gnUsuariosId.id =:idUsuario";
            strQuery += " AND p.maeTipoAuditorId =:maeTipoAuditorId";
            List<AuGrupoUsuarios> list = getEntityManager().createQuery(strQuery)
                    .setParameter("idGrupo", idGrupo)
                    .setParameter("idUsuario", idUsuario)
                    .setParameter("maeTipoAuditorId", maeTipoAuditorId)
                    .getResultList();
            existe = list != null && !list.isEmpty();
        } catch (NoResultException e) {
            existe = false;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return existe;
    }

    @Override
    public List<AuGrupoUsuario> consultarListaPorIdGrupoActivos(int idGrupo) throws Exception {
        List<AuGrupoUsuario> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuGrupoUsuarios p "
                    + "WHERE p.id > 0";
            strQuery += " AND p.auGruposId.id = " + idGrupo + " ";
            strQuery += " AND p.activo =  1 ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<AuGrupoUsuarios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuGrupoUsuarios usuarios : list) {
                listaResultados.add(castEntidadNegocio(usuarios));
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

    @Override
    public List<Integer> consultarGruposUsuario(Integer idUsuario) throws Exception {
        List<Integer> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuGrupoUsuarios p ";
            strQuery += " WHERE p.gnUsuariosId.id = " + idUsuario + " ";
            List<AuGrupoUsuarios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuGrupoUsuarios usuarios : list) {
                if (usuarios.getGnUsuariosId() != null) {
                    listaResultados.add(usuarios.getAuGruposId().getId());
                }
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
