/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.informe;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.informe.InfGrupo;
import com.saviasaludeps.savia.ejb.entidades.InfGrupos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.informe.InformeGrupoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Session;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(InformeGrupoRemoto.class)
public class InformeGrupoServicio extends GenericoServicio implements InformeGrupoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(g) FROM InfGrupos g "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND g.nombre LIKE '" + e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += "AND g.descripcion LIKE '%" + (String) e.getValue() + "%' ";
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
    public List<InfGrupo> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<InfGrupo> listResult = new ArrayList();
        try {
            String strQuery = "FROM InfGrupos g "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND g.nombre LIKE '" + e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += "AND g.descripcion LIKE '%" + (String) e.getValue() + "%' ";
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "g." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "g.descripcion ASC ";
            }
            List<InfGrupos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (InfGrupos ent : list) {
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
    public InfGrupo consultar(int id) throws Exception {
        InfGrupo objRes = null;
        try {
            objRes = castEntidadNegocioCorto((InfGrupos) getEntityManager().find(InfGrupos.class, id));
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
    public List<InfGrupo> consultarTodos() throws Exception {
        List<InfGrupo> listResult = new ArrayList();
        try {
            String strQuery = "FROM InfGrupos g "
                    + "WHERE 1 = 1 ";
            strQuery += "ORDER BY ";
            strQuery += "g.descripcion ASC ";
            List<InfGrupos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (InfGrupos ent : list) {
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
    public int insertar(InfGrupo obj) throws Exception {
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
    public void actualizar(InfGrupo obj) throws Exception {
        try {
            InfGrupos ent = castNegocioEntidadCorto(obj);
            Session session = getEntityManager().unwrap(Session.class);
            //session.update(per);
            String strQuery = "UPDATE InfGrupos i SET ";
            //strQuery += " a.id = :id";
            strQuery += " i.nombre = :nombre ,";
            strQuery += " i.descripcion = :descripcion, ";
            strQuery += " i.usuarioModifica = :usuarioModifica, ";
            strQuery += " i.terminalModifica = :terminalModifica, ";
            strQuery += " i.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE i.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(ent);
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(INSERTAR, e);
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public InfGrupo eliminar(int id) throws Exception {
        InfGrupo obj = null;
        try {
            InfGrupos ent = getEntityManager().find(InfGrupos.class, id);
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

    private InfGrupo castEntidadNegocioCorto(InfGrupos ent) {
        InfGrupo negocio = new InfGrupo();
        negocio.setId(ent.getId());
        negocio.setNombre(ent.getNombre());
        negocio.setDescripcion(ent.getDescripcion());
        negocio.setUsuarioCrea(ent.getUsuarioCrea());
        negocio.setTerminalCrea(ent.getTerminalCrea());
        negocio.setFechaHoraCrea(ent.getFechaHoraCrea());
        negocio.setUsuarioModifica(ent.getUsuarioModifica());
        negocio.setTerminalModifica(ent.getTerminalModifica());
        negocio.setFechaHoraModifica(ent.getFechaHoraModifica());
        return negocio;
    }

    private InfGrupos castNegocioEntidadCorto(InfGrupo neg) {
        InfGrupos ent = new InfGrupos();
        if (neg.getId() != null) {
            ent.setId(neg.getId());
        }
        ent.setNombre(neg.getNombre());
        ent.setDescripcion(neg.getDescripcion());
        ent.setUsuarioCrea(neg.getTerminalCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setUsuarioModifica(neg.getUsuarioModifica());
        ent.setTerminalModifica(neg.getTerminalModifica());
        ent.setFechaHoraModifica(neg.getFechaHoraModifica());
        return ent;
    }

}
