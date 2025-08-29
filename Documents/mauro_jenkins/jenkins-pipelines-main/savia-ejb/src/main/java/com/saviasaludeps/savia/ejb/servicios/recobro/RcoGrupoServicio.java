package com.saviasaludeps.savia.ejb.servicios.recobro;

import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.recobro.RcoGrupo;
import com.saviasaludeps.savia.ejb.entidades.PeProgramas;
import com.saviasaludeps.savia.ejb.entidades.RcoGrupos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.recobro.RcoGrupoRemoto;
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
@Remote(RcoGrupoRemoto.class)
public class RcoGrupoServicio extends GenericoServicio implements RcoGrupoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(m) FROM RcoGrupos m  WHERE id > 0 ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND m.id = " + e.getValue() + " ";
                            break;
                        case "nombre":
                            strQuery += "AND m.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += "AND m.tipo = " + e.getValue() + " ";
                            break;
                        case "peProgramaId.descripcionPrograma":
                            strQuery += "AND m.peProgramasId.id LIKE '%" + e.getValue() + "%' ";
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
//
    @Override
    public List<RcoGrupo> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<RcoGrupo> listResult = new ArrayList();
        try {
            String strQuery = "SELECT m FROM RcoGrupos m  WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND m.id = " + e.getValue() + " ";
                            break;
                        case "nombre":
                            strQuery += "AND m.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += "AND m.tipo = " + e.getValue() + " ";
                            break;
                        case "peProgramaId.descripcionPrograma":
                            strQuery += "AND m.peProgramasId.id LIKE '%" + e.getValue() + "%' ";
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
            List<RcoGrupos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (RcoGrupos per : list) {
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
    public RcoGrupo consultar(int id) throws Exception {
        RcoGrupo objRes = null;
        try {
            objRes = castEntidadNegocio((RcoGrupos) getEntityManager().find(RcoGrupos.class, id));
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
    public int insertar(RcoGrupo obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "La combinación nombre, tipo, valor ya existe.");
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    @Override
    public void actualizar(RcoGrupo obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE RcoGrupos a SET ";
            strQuery += "a.nombre = :nombre ,";
            strQuery += "a.tipo = :tipo ,";
            strQuery += "a.descripcion = :descripcion ,";

            if (obj.getPeProgramaId().getId() != null) {
                strQuery += "a.peProgramasId.id = :peProgramasId ,";
            }

            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("tipo", obj.getTipo());
            query.setParameter("descripcion", obj.getDescripcion());

            if (obj.getPeProgramaId().getId() != null) {
                query.setParameter("peProgramasId", obj.getPeProgramaId().getId());
            }

            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
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
    public RcoGrupo eliminar(int id) throws Exception {
        RcoGrupo obj = null;
        try {
            RcoGrupos ent = getEntityManager().find(RcoGrupos.class, id);
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

    private RcoGrupo castEntidadNegocio(RcoGrupos entidad) {
        RcoGrupo negocio = new RcoGrupo();
        negocio.setId(entidad.getId());
        negocio.setNombre(entidad.getNombre());
        negocio.setTipo(entidad.getTipo());
        negocio.setDescripcion(entidad.getDescripcion());
        negocio.setPeProgramaId(new PePrograma(entidad.getPeProgramasId().getId()));
        negocio.getPeProgramaId().setDescripcionPrograma(entidad.getPeProgramasId().getDescripcionPrograma());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }

    private RcoGrupos castNegocioEntidad(RcoGrupo negocio) {
        RcoGrupos entidad = new RcoGrupos();
        entidad.setId(negocio.getId());
        entidad.setNombre(negocio.getNombre());
        entidad.setTipo(negocio.getTipo());
        entidad.setDescripcion(negocio.getDescripcion());
        entidad.setPeProgramasId(new PeProgramas(negocio.getPeProgramaId().getId()));
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());

        return entidad;
    }
}
