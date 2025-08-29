/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.tutela;

import com.saviasaludeps.savia.dominio.tutela.TuRepresentante;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaEstado;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaEstadoRepresentante;
import com.saviasaludeps.savia.ejb.entidades.TuRepresentantes;
import com.saviasaludeps.savia.ejb.entidades.TuTutelaEstadoRepresentantes;
import com.saviasaludeps.savia.ejb.entidades.TuTutelaEstados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.tutela.TuTutelaEstadoRepresentanteRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author pavacca
 */
@Stateless
@Remote(TuTutelaEstadoRepresentanteRemoto.class)
@Local(TuTutelaEstadoRepresentanteLocal.class)
public class TuTutelaEstadoRepresentanteServicio extends GenericoServicio implements TuTutelaEstadoRepresentanteLocal, TuTutelaEstadoRepresentanteRemoto {

    @Override
    public TuTutelaEstadoRepresentante consultar(int id) throws Exception {
        TuTutelaEstadoRepresentante objRes = null;
        try {
            objRes = castEntidadNegocio((TuTutelaEstadoRepresentantes) getEntityManager().find(TuTutelaEstadoRepresentantes.class, id));
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
    public int insertar(TuTutelaEstadoRepresentante tuTutelaEstadoRepresentante) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(tuTutelaEstadoRepresentante)).getId();
            tuTutelaEstadoRepresentante.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al insertar un estado representante en tutelas");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }
    
    @Override
    public void actualizar(TuTutelaEstadoRepresentante obj) throws Exception {
        try {
            String hql = "UPDATE TuTutelaEstadoRepresentantes SET"
                    + " tuTutelaEstadosId.id = :tuTutelaEstadosId,"
                    + " tuRepresentantesId.id = :tuRepresentantesId,"
                    + " tuTutelaEstadoRepresentantescol = :tuTutelaEstadoRepresentantescol,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("tuTutelaEstadosId", obj.getTuTutelaEstadosId().getId());
            query.setParameter("tuRepresentantesId", obj.getTuRepresentantesId().getId());
            query.setParameter("tuTutelaEstadoRepresentantescol", obj.getTuTutelaEstadoRepresentantescol());
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
    public TuTutelaEstadoRepresentante eliminar(int id) throws Exception {
        TuTutelaEstadoRepresentante obj = null;
        try {
            TuTutelaEstadoRepresentantes ent = getEntityManager().find(TuTutelaEstadoRepresentantes.class, id);
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
    public void eliminarResprentantes(int idTutelaEstado) throws Exception {
        try {
            String hql = "DELETE FROM TuTutelaEstadoRepresentantes"
                 + " WHERE tuTutelaEstadosId.id = :tuTutelaEstadosId";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("tuTutelaEstadosId", idTutelaEstado);
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public List<TuTutelaEstadoRepresentante> consultarRespresentantesPorEstadoTutela(int id) throws Exception {
        List<TuTutelaEstadoRepresentante> listResult = new ArrayList();
        try {
            String strQuery = "FROM TuTutelaEstadoRepresentantes c "
                    + " WHERE c.tuTutelaEstadosId.id = :tuTutelaEstadosId ";

            List<TuTutelaEstadoRepresentantes> list = getEntityManager().createQuery(strQuery)
                    .setParameter("tuTutelaEstadosId", id)
                    .getResultList();
            for (TuTutelaEstadoRepresentantes casoNegocion : list) {
                listResult.add(castEntidadNegocio(casoNegocion));
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
    public List<TuTutelaEstadoRepresentante> consultarRespresentantesPorIdRepresentante(int id) throws Exception {
        List<TuTutelaEstadoRepresentante> listResult = new ArrayList();
        try {
            String strQuery = "FROM TuTutelaEstadoRepresentantes c "
                    + " WHERE c.tuRepresentantesId.id = :tuRepresentantesId ";

            List<TuTutelaEstadoRepresentantes> list = getEntityManager().createQuery(strQuery)
                    .setParameter("tuRepresentantesId", id)
                    .getResultList();
            for (TuTutelaEstadoRepresentantes casoNegocion : list) {
                listResult.add(castEntidadNegocio(casoNegocion));
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
    public TuTutelaEstadoRepresentante consultarRepresentantePorEstadoyYIdRepresentante(int idRepresentante, int idTutelaEstado) throws Exception {
        TuTutelaEstadoRepresentante objRes = null;
        try {
            String strQuery = "FROM TuTutelaEstadoRepresentantes m "
                    + "WHERE m.tuRepresentantesId.id = " + idRepresentante 
                    + " AND m.tuTutelaEstadosId.id = " + idTutelaEstado;
            objRes = castEntidadNegocio((TuTutelaEstadoRepresentantes) getEntityManager().createQuery(strQuery).getSingleResult());
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }
    
    public static TuTutelaEstadoRepresentantes castNegocioEntidad(TuTutelaEstadoRepresentante obj) {
        TuTutelaEstadoRepresentantes per = new TuTutelaEstadoRepresentantes();
        per.setId(obj.getId());
        if (obj.getTuTutelaEstadosId() != null) {
            per.setTuTutelaEstadosId(new TuTutelaEstados(obj.getTuTutelaEstadosId().getId()));
        }

        if (obj.getTuRepresentantesId() != null) {
            per.setTuRepresentantesId(new TuRepresentantes(obj.getTuRepresentantesId().getId()));
        }

        per.setTuTutelaEstadoRepresentantescol(obj.getTuTutelaEstadoRepresentantescol());

        //Auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        //per.setTerminalModifica(obj.getTerminalModifica());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        return per;
    }

    public static TuTutelaEstadoRepresentante castEntidadNegocio(TuTutelaEstadoRepresentantes servicioNegocio) {
        TuTutelaEstadoRepresentante servicioEntidad = new TuTutelaEstadoRepresentante();
        servicioEntidad.setId(servicioNegocio.getId());
        if (servicioNegocio.getTuTutelaEstadosId() != null) {
            servicioEntidad.setTuTutelaEstadosId(new TuTutelaEstado(servicioNegocio.getTuTutelaEstadosId().getId()));
        }

        if (servicioNegocio.getTuRepresentantesId() != null) {
            servicioEntidad.setTuRepresentantesId(new TuRepresentante(servicioNegocio.getTuRepresentantesId().getId()));
        }

        servicioEntidad.setTuTutelaEstadoRepresentantescol(servicioNegocio.getTuTutelaEstadoRepresentantescol());

        //Auditoria
        servicioEntidad.setUsuarioCrea(servicioNegocio.getUsuarioCrea());
        servicioEntidad.setTerminalCrea(servicioNegocio.getTerminalCrea());
        servicioEntidad.setFechaHoraCrea(servicioNegocio.getFechaHoraCrea());
        servicioEntidad.setUsuarioModifica(servicioNegocio.getUsuarioModifica());
        //servicioEntidad.setTerminalModifica(servicioNegocio.getTerminalModifica());
        servicioEntidad.setFechaHoraModifica(servicioNegocio.getFechaHoraModifica());
        return servicioEntidad;
    }
}
