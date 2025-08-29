package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.Recurrencia;
import com.saviasaludeps.savia.dominio.administracion.RecurrenciaHistorico;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.GnRecurrenciaHistoricos;
import com.saviasaludeps.savia.ejb.entidades.GnRecurrencias;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.administracion.RecurrenciaHistoricoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Stateless
@Remote(RecurrenciaHistoricoRemoto.class)
public class RecurrenciaHistoricoServicio extends GenericoServicio implements RecurrenciaHistoricoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(DISTINCT h) FROM GnRecurrenciaHistoricos h ";
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
    public List<RecurrenciaHistorico> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<RecurrenciaHistorico> listResult = new ArrayList();
        try {
            String strQuery = "FROM GnRecurrenciaHistoricos h ";
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "e." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "e.nombre ASC ";
            }
            List<GnRecurrenciaHistoricos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GnRecurrenciaHistoricos per : list) {
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
    public RecurrenciaHistorico consultar(int id) throws Exception {
        RecurrenciaHistorico objRes = null;
        try {
            objRes = castEntidadNegocio((GnRecurrenciaHistoricos) getEntityManager().find(GnRecurrenciaHistoricos.class, id));
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
    public int insertar(RecurrenciaHistorico obj) throws Exception {
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
    public void actualizar(RecurrenciaHistorico obj) throws Exception {
        try {
            String hql = "UPDATE GnRecurrenciaHistoricos SET "
                    + "gnRecurrenciasId = :id_recurrencia, "
                    + "exitoso = :exitoso, "
                    + "respuesta = :activo, "
                    + "fechaHoraInicio = :fecha_inicio, "
                    + "fechaHoraFin = :fecha_fin, "
                    + "tiempo = :tipoPeriodicidad "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("id_recurrencia", obj.getRecurrencia().getId());
            query.setParameter("exitoso", obj.isExitoso());
            query.setParameter("fecha_inicio", obj.getFechaHoraInicio());
            query.setParameter("fecha_fin", obj.getFechaHoraFin());
            query.setParameter("tiempo", obj.getTiempo());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    public static RecurrenciaHistorico castEntidadNegocio(GnRecurrenciaHistoricos ent) {
        RecurrenciaHistorico neg = new RecurrenciaHistorico();
        neg.setId(ent.getId());
        neg.setRecurrencia(new Recurrencia(ent.getGnRecurrenciasId().getId()));
        neg.setExitoso(ent.getExitoso());
        neg.setRespuesta(ent.getRespuesta());
        neg.setFechaHoraInicio(ent.getFechaHoraInicio());
        neg.setFechaHoraFin(ent.getFechaHoraFin());
        neg.setTiempo(ent.getTiempo());
        return neg;
    }

    public static GnRecurrenciaHistoricos castNegocioEntidad(RecurrenciaHistorico neg) {
        GnRecurrenciaHistoricos ent = new GnRecurrenciaHistoricos();
        ent.setGnRecurrenciasId(new GnRecurrencias(neg.getRecurrencia().getId()));
        ent.setExitoso(neg.isExitoso());
        ent.setRespuesta(neg.getRespuesta());
        ent.setFechaHoraInicio(neg.getFechaHoraInicio());
        ent.setFechaHoraFin(neg.getFechaHoraFin());
        ent.setTiempo(neg.getTiempo());
        return ent;
    }

}
