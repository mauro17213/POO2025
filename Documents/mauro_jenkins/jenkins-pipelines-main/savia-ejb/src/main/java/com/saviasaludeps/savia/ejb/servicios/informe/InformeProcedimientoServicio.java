/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.servicios.informe;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.informe.InfProcedimiento;
import com.saviasaludeps.savia.ejb.entidades.InfProcedimientos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.utilidades.PropApl;
import com.saviasaludeps.savia.negocio.informe.InformeProcedimientosRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import org.hibernate.Session;

/**
 *
 * @author aguevara
 */
@Stateless
@Remote(InformeProcedimientosRemoto.class)
public class InformeProcedimientoServicio extends GenericoServicio implements InformeProcedimientosRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(g) FROM InfProcedimientos g "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND g.nombreScript LIKE '" + e.getValue() + "%' ";
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
    public List<InfProcedimiento> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<InfProcedimiento> listResult = new ArrayList();
        try {
            String strQuery = "FROM InfProcedimientos g "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND g.nombreScript LIKE '" + e.getValue() + "%' ";
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
            List<InfProcedimientos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (InfProcedimientos ent : list) {
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
public List<InfProcedimiento> consultarTodosPorNombre(String nombreScript) throws Exception {
    List<InfProcedimiento> listResult = new ArrayList<>();
    try {
        if (nombreScript == null || nombreScript.trim().isEmpty()) {
            return listResult; // sin criterio => vacío
        }

        String jpql = "SELECT g FROM InfProcedimientos g "
                + "WHERE TRIM(UPPER(g.nombreScript)) = :nombre "
                + "ORDER BY g.fechaHoraCrea DESC, g.id DESC";

        List<InfProcedimientos> list = getEntityManager()
                .createQuery(jpql, InfProcedimientos.class)
                .setParameter("nombre", nombreScript.trim().toUpperCase())
                .getResultList();

        for (InfProcedimientos ent : list) {
            listResult.add(castEntidadNegocioCorto(ent));
        }

    } catch (NoResultException e) {
        listResult = new ArrayList<>();
    } catch (Exception e) {
        Exception(CONSULTAR_TODOS, e);
    } finally {
        cerrarEntityManager();
    }
    return listResult;
}

    @Override
    public InfProcedimiento consultarPorNombreScript(String nombreScript) throws Exception {
        InfProcedimiento objRes = null;
        try {
            TypedQuery<InfProcedimientos> query = getEntityManager().createQuery(
                    "SELECT i FROM InfProcedimientos i WHERE i.nombreScript = :nombreScript", InfProcedimientos.class);
            query.setParameter("nombreScript", nombreScript);

            InfProcedimientos entidad = query.getSingleResult();
            objRes = castEntidadNegocioCorto(entidad);

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
    public int insertar(InfProcedimiento obj) throws java.lang.Exception {
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
    public void actualizar(InfProcedimiento obj) throws Exception {
        try {
            InfProcedimientos ent = castNegocioEntidadCorto(obj);
            Session session = getEntityManager().unwrap(Session.class);

            String strQuery = "UPDATE InfProcedimientos i SET "
                    + "i.nombreScript = :nombreScript, "
                    + "i.script = :script, "
                    + "i.descripcion = :descripcion, "
                    + "i.usuarioModifica = :usuarioModifica, "
                    + "i.terminalModifica = :terminalModifica, "
                    + "i.fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE i.id = :id";

            org.hibernate.query.Query<?> query = session.createQuery(strQuery);
            query.setParameter("nombreScript", ent.getNombreScript());
            query.setParameter("script", ent.getScript());
            query.setParameter("descripcion", ent.getDescripcion());
            query.setParameter("id", ent.getId());

            int filas = query.executeUpdate();
            if (filas == 0) {
                throw new IllegalStateException("No se encontró el registro a actualizar (id=" + ent.getId() + ").");
            }
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
            throw e;
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public InfProcedimiento eliminar(int id) throws java.lang.Exception {
        InfProcedimiento obj = null;
        try {
            InfProcedimientos ent = getEntityManager().find(InfProcedimientos.class, id);
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

    /**
     * Consulta nativa para obtener procedimientos tipo 'sp_inf%' del schema
     * dinámico (PropApl.GN_ESQUEMA_AMBIENTE).
     */
    @Override
    public List<Object[]> consultarProcedimientosSql() throws Exception {
        List<Object[]> resultados = new ArrayList<>();
        try {
            String gnEsquemaAmbiente = PropApl.getInstance().get(PropApl.GN_ESQUEMA_AMBIENTE);

            String sql = "SELECT SPECIFIC_NAME, ROUTINE_NAME, CREATED, LAST_ALTERED, DEFINER "
                    + "FROM information_schema.ROUTINES "
                    + "WHERE ROUTINE_TYPE = 'PROCEDURE' "
                    + "AND ROUTINE_SCHEMA = ?1 "
                    + "AND ROUTINE_NAME LIKE 'sp_inf%'";

            resultados = getEntityManager()
                    .createNativeQuery(sql)
                    .setParameter(1, gnEsquemaAmbiente)
                    .getResultList();

        } catch (Exception e) {
            Exception("CONSULTAR_PROCEDIMIENTOS_SQL", e);
        } finally {
            cerrarEntityManager();
        }
        return resultados;
    }

    private InfProcedimiento castEntidadNegocioCorto(InfProcedimientos ent) {
        InfProcedimiento negocio = new InfProcedimiento();
        negocio.setId(ent.getId());
        negocio.setNombreScript(ent.getNombreScript());
        negocio.setScript(ent.getScript());
        negocio.setExitoso(ent.getExitoso());
        negocio.setDescripcion(ent.getDescripcion());
        negocio.setUsuarioCrea(ent.getUsuariosCrear());
        negocio.setTerminalCrea(ent.getTerminalCrea());
        negocio.setFechaHoraCrea(ent.getFechaHoraCrea());
        return negocio;

    }

    private InfProcedimientos castNegocioEntidadCorto(InfProcedimiento neg) {
        InfProcedimientos ent = new InfProcedimientos();

        ent.setId(neg.getId());
        ent.setNombreScript(neg.getNombreScript());
        ent.setScript(neg.getScript());
        ent.setExitoso(neg.isExitoso());
        ent.setDescripcion(neg.getDescripcion());
        ent.setUsuariosCrear(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        return ent;
    }

}
