package com.saviasaludeps.savia.ejb.servicios.recobro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.recobro.RcoConciliacion;
import com.saviasaludeps.savia.dominio.recobro.RcoConciliacionGestion;
import com.saviasaludeps.savia.dominio.recobro.RcoFacturaDetalle;
import com.saviasaludeps.savia.ejb.entidades.RcoConciliacionGestiones;
import com.saviasaludeps.savia.ejb.entidades.RcoConciliaciones;
import com.saviasaludeps.savia.ejb.entidades.RcoFacturaDetalles;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.recobro.RcoConciliacionGestionRemoto;
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
@Remote(RcoConciliacionGestionRemoto.class)
public class RcoConciliacionGestionServicio extends GenericoServicio implements RcoConciliacionGestionRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(m) FROM RcoConciliacionGestionServicios m ";
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
    public List<RcoConciliacionGestion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<RcoConciliacionGestion> listResult = new ArrayList();
        try {
            String strQuery = "SELECT m FROM RcoConciliacionGestiones m ";
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
            List<RcoConciliacionGestiones> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (RcoConciliacionGestiones per : list) {
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
    public RcoConciliacionGestion consultar(int id) throws Exception {
        RcoConciliacionGestion objRes = null;
        try {
            objRes = castEntidadNegocio((RcoConciliacionGestiones) getEntityManager().find(RcoConciliacionGestiones.class, id));
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
    public int insertar(RcoConciliacionGestion obj) throws Exception {
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
    public void actualizar(RcoConciliacionGestion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE RcoConciliacionGestiones a SET ";
            strQuery += "a.acuerdoRecobro = :acuerdoRecobro ,";
            strQuery += "a.observacion = :observacion ,";
            strQuery += "a.usuarioModifica = :usuarioModifica , ";
            strQuery += "a.terminalModifica = :terminalModifica  ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";

            Query query = session.createQuery(strQuery);
            query.setParameter("acuerdoRecobro", obj.getAcuerdoRecobro());
            query.setParameter("observacion", obj.getObservacion());
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
    public RcoConciliacionGestion eliminar(int id) throws Exception {
        RcoConciliacionGestion obj = null;
        try {
            RcoConciliacionGestiones ent = getEntityManager().find(RcoConciliacionGestiones.class, id);
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
    
    @Override
    public RcoConciliacionGestion consultarByFacturaDetallesId(int rcoFacturaDetallesId) throws Exception {
        RcoConciliacionGestion ObjectResult = new RcoConciliacionGestion();
        String sql = "SELECT rcocg FROM RcoConciliacionGestiones rcocg "
                + "INNER JOIN RcoFacturaDetalles rcofd ON rcocg.rcoFacturaDetallesId = rcofd.id "
                + "WHERE rcofd.id = :rcoFacturaDetallesId "
                + "ORDER BY rcocg.id DESC";
        try {
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("rcoFacturaDetallesId", rcoFacturaDetallesId);
            
            List<RcoConciliacionGestiones> list = query.setMaxResults(1).getResultList();
            for (RcoConciliacionGestiones item : list) {
                ObjectResult = castEntidadNegocio(item);
            }
        } catch (NoResultException e) {
            ObjectResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return ObjectResult;
    }
    
    @Override
    public List<RcoConciliacionGestion> consultarByConciliacionId(int rcoConciliacionId) throws Exception {
        List<RcoConciliacionGestion> objectResult = new ArrayList<>();
        String sql = "SELECT rcocg FROM RcoConciliacionGestiones rcocg "
                + "INNER JOIN RcoConciliaciones rcoc ON rcocg.rcoConciliacionesId = rcoc.id "
                + "WHERE rcoc.id = :rcoConciliacionId "
                + "ORDER BY rcocg.id DESC";
        try {
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("rcoConciliacionId", rcoConciliacionId);
            
            List<RcoConciliacionGestiones> list = query.getResultList();
            for (RcoConciliacionGestiones item : list) {
                objectResult.add(castEntidadNegocio(item));
            }
        } catch (NoResultException e) {
            objectResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objectResult;
    }
    
    private RcoConciliacionGestion castEntidadNegocio(RcoConciliacionGestiones entidad) {
        RcoConciliacionGestion negocio = new RcoConciliacionGestion();
        negocio.setId(entidad.getId());
        if(entidad.getRcoConciliacionesId() != null){
            negocio.setRcoConciliacionesId(new RcoConciliacion(entidad.getRcoConciliacionesId().getId()));
        }
        
        if(entidad.getRcoFacturaDetallesId() != null){
            negocio.setRcoFacturaDetallesId(new RcoFacturaDetalle(entidad.getRcoFacturaDetallesId().getId()));
        }
        
        negocio.setAcuerdoRecobro(entidad.getAcuerdoRecobro());
        negocio.setObservacion(entidad.getObservacion());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        return negocio;
    }

    private RcoConciliacionGestiones castNegocioEntidad(RcoConciliacionGestion negocio) {
        RcoConciliacionGestiones entidad = new RcoConciliacionGestiones();
        entidad.setId(negocio.getId());
        if(negocio.getRcoConciliacionesId() != null){
            entidad.setRcoConciliacionesId(new RcoConciliaciones(negocio.getRcoConciliacionesId().getId()));
        }
        if(negocio.getRcoFacturaDetallesId() != null){
            entidad.setRcoFacturaDetallesId(new RcoFacturaDetalles(negocio.getRcoFacturaDetallesId().getId()));
        }
        entidad.setAcuerdoRecobro(negocio.getAcuerdoRecobro());
        entidad.setObservacion(negocio.getObservacion());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        return entidad;
    }
}
