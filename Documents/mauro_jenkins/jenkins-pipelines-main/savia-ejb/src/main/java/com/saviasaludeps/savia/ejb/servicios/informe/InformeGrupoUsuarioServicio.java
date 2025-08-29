/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.informe;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.informe.InfGrupo;
import com.saviasaludeps.savia.dominio.informe.InfGrupoUsuario;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.entidades.InfGrupoUsuarios;
import com.saviasaludeps.savia.ejb.entidades.InfGrupos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.informe.InformeGrupoUsuarioRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(InformeGrupoUsuarioRemoto.class)
public class InformeGrupoUsuarioServicio extends GenericoServicio implements InformeGrupoUsuarioRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(g) FROM InfGrupoUsuarios g "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND g.infGruposId.id = " + paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND g.gnUsuariosId.nombre LIKE '%" + e.getValue() + "%' ";
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
    public List<InfGrupoUsuario> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<InfGrupoUsuario> listResult = new ArrayList();
        try {
            String strQuery = "FROM InfGrupoUsuarios g "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND g.infGruposId.id = " + paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND g.gnUsuariosId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "g." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "g.id ASC ";
            }
            List<InfGrupoUsuarios> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (InfGrupoUsuarios ent : list) {
                listResult.add(castEntidadNegocioCorto(ent));
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
    public InfGrupoUsuario consultar(int id) throws Exception {
        InfGrupoUsuario objRes = null;
        try {
            objRes = castEntidadNegocioCorto((InfGrupoUsuarios) getEntityManager().find(InfGrupoUsuarios.class, id));
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
    public List<InfGrupo> consultarListaDeGruposPorUsuario(Integer id) throws Exception {
        List<InfGrupo> listResult = new ArrayList();
        try {
            String strQuery = "FROM InfGrupoUsuarios g "
                    + "WHERE g.gnUsuariosId.id = " + id;
            List<InfGrupoUsuarios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (InfGrupoUsuarios ent : list) {
                listResult.add(castEntidadNegocio(ent));
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
    public int insertar(InfGrupoUsuario obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidadCorto(obj)).getId();
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
    public InfGrupoUsuario eliminar(int id) throws Exception {
        InfGrupoUsuario obj = null;
        try {
            InfGrupoUsuarios ent = getEntityManager().find(InfGrupoUsuarios.class, id);
            if (ent != null) {
                obj = castEntidadNegocioCorto(ent);
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

    private InfGrupo castEntidadNegocio(InfGrupoUsuarios ent) {
        InfGrupo negocio = new InfGrupo(
                ent.getInfGruposId().getId(),
                ent.getInfGruposId().getNombre()
        );
        return negocio;
    }

    private InfGrupoUsuario castEntidadNegocioCorto(InfGrupoUsuarios ent) {
        InfGrupoUsuario negocio = new InfGrupoUsuario();
        negocio.setId(ent.getId());
        negocio.setUsuario(new Usuario(ent.getGnUsuariosId().getId()));
        negocio.getUsuario().setNombre(ent.getGnUsuariosId().getNombre());
        negocio.setGrupo(new InfGrupo());
        negocio.getGrupo().setId(ent.getInfGruposId().getId());
        negocio.setActivo(ent.getActivo());
        negocio.setUsuarioCrea(ent.getUsuarioCrea());
        negocio.setTerminalCrea(ent.getTerminalCrea());
        negocio.setFechaHoraCrea(ent.getFechaHoraCrea());
        return negocio;
    }

    private InfGrupoUsuarios castNegocioEntidadCorto(InfGrupoUsuario neg) {
        InfGrupoUsuarios ent = new InfGrupoUsuarios();
        if (neg.getId() != null) {
            ent.setId(neg.getId());
        }
        ent.setGnUsuariosId(new GnUsuarios(neg.getUsuario().getId()));
        ent.setInfGruposId(new InfGrupos(neg.getGrupo().getId()));
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        return ent;
    }

    @Override
    public List<InfGrupoUsuario> consultarPorIdGrupo(Integer idGrupo) throws Exception {
        List<InfGrupoUsuario> listResult = new ArrayList();
        try {
            String strQuery = "FROM InfGrupoUsuarios g "
                    + "WHERE g.infGruposId.id = " + idGrupo;
            List<InfGrupoUsuarios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (InfGrupoUsuarios ent : list) {
                listResult.add(castEntidadNegocioCorto(ent));
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
