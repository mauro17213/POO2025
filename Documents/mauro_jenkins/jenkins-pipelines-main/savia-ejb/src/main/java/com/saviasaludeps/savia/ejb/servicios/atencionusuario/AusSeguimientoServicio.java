/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.atencionusuario;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCaso;
import com.saviasaludeps.savia.dominio.atencionusuario.AusSeguimiento;
import com.saviasaludeps.savia.ejb.entidades.AusCasos;
import com.saviasaludeps.savia.ejb.entidades.AusSeguimientos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.atencionusuario.AusSeguimientoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(AusSeguimientoRemoto.class)
@Local(AusSeguimientoLocal.class)
public class AusSeguimientoServicio extends GenericoServicio implements AusSeguimientoLocal, AusSeguimientoRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(s) FROM AusSeguimientos s "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "casosId":
                            strQuery += "AND s.ausCasosId = " + (String) e.getValue() + " ";
                            break;
                        case "maeSolicitudEstado":
                            strQuery += "AND s.maeEstadoId = " + (String) e.getValue() + " ";
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
    public List<AusSeguimiento> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AusSeguimiento> listResult = new ArrayList();
        try {
            String strQuery = "FROM AusSeguimientos s "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "casosId":
                            strQuery += "AND s.ausCasosId = " + (String) e.getValue() + " ";
                            break;
                        case "maeSolicitudEstado":
                            strQuery += "AND s.maeEstadoId = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "s." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "s.fechaHoraCrea DESC";
            }
            List<AusSeguimientos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AusSeguimientos per : list) {
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
    public AusSeguimiento consultar(int id) throws Exception {
        AusSeguimiento objRes = null;
        try {
            AusSeguimientos seguimientoNegocio = (AusSeguimientos) getEntityManager().find(AusSeguimientos.class, id);
            objRes = castEntidadNegocio(seguimientoNegocio);
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e, "Error al consultar un seguimiento");
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }
    
    @Override
    public int insertar(AusSeguimiento obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al insertar un seguimiento");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }
    
    @Override
    public void actualizar(AusSeguimiento obj) throws Exception {
        try {
            String hql = "UPDATE AusSeguimientos SET"
                    + " ausCasosId.id = :casosId,"                    
                    + " maeEstadoId = :maeEstadoId,"
                    + " maeEstadoCodigo = :maeEstadoCodigo,"
                    + " maeEstadoValor = :maeEstadoValor,"
                    + " observacion = :observacion,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("casosId", obj.getCasosId().getId());
            query.setParameter("maeEstadoId", obj.getMaeEstadoId());
            query.setParameter("maeEstadoCodigo", obj.getMaeEstadoCodigo());
            query.setParameter("maeEstadoValor", obj.getMaeEstadoValor());
            query.setParameter("observacion", obj.getObservacion());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e, "Error al actualizar un seguimiento");
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public AusSeguimiento eliminar(int id) throws Exception {
        AusSeguimiento obj = null;
        try {
            AusSeguimientos ent = getEntityManager().find(AusSeguimientos.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e, "Error al eliminar un seguimiento");
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }
    
    public static AusSeguimiento castEntidadNegocio(AusSeguimientos seguimientoNegocio) {
        AusSeguimiento seguimiento = new AusSeguimiento();
        AusCaso caso = new AusCaso();
        caso.setId(seguimientoNegocio.getAusCasosId().getId());
        seguimiento.setId(seguimientoNegocio.getId());
        seguimiento.setCasosId(caso);
        seguimiento.setMaeEstadoId(seguimientoNegocio.getMaeEstadoId());
        seguimiento.setMaeEstadoCodigo(seguimientoNegocio.getMaeEstadoCodigo());
        seguimiento.setMaeEstadoValor(seguimientoNegocio.getMaeEstadoValor());
        seguimiento.setObservacion(seguimientoNegocio.getObservacion());
        seguimiento.setTerminalCrea(seguimientoNegocio.getTerminalCrea());
        seguimiento.setFechaHoraCrea(seguimientoNegocio.getFechaHoraCrea());
        seguimiento.setUsuarioCrea(seguimientoNegocio.getUsuarioCrea());
        seguimiento.setUsuarioModifica(seguimientoNegocio.getUsuarioModifica());
        seguimiento.setTerminalModifica(seguimientoNegocio.getTerminalModifica());
        seguimiento.setFechaHoraModifica(seguimientoNegocio.getFechaHoraModifica());
        seguimiento.setAdjuntosList(AusAdjuntoServicio.castEntidadNegocio(seguimientoNegocio.getAusAdjuntosList()));
        return seguimiento;
    }
    
    public static AusSeguimientos castNegocioEntidad(AusSeguimiento obj) {
        AusSeguimientos per = new AusSeguimientos();
        per.setId(obj.getId());
        per.setAusCasosId(new AusCasos(obj.getCasosId().getId()));
        per.setMaeEstadoId(obj.getMaeEstadoId());
        per.setMaeEstadoCodigo(obj.getMaeEstadoCodigo());
        per.setMaeEstadoValor(obj.getMaeEstadoValor());
        String observacion = obj.getObservacion().isEmpty() ? " " : obj.getObservacion().replace(" ", " ");
        per.setObservacion(observacion);
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        return per;
    }
    
    public static List<AusSeguimiento> castEntidadNegocio(List<AusSeguimientos> seguimientosNegocio) {
        List<AusSeguimiento> listaSeguimiento = new ArrayList();
        for (AusSeguimientos seguimientoNegocio : seguimientosNegocio) {
            listaSeguimiento.add(castEntidadNegocio(seguimientoNegocio));
        }
        return listaSeguimiento;
    }

    @Override
    public AusSeguimiento consultarUltimoPorEstadoYCaso(int idCaso, String codigoEstado) throws java.lang.Exception {
        AusSeguimiento ausSeguimiento = null;
        try {
            String strQuery = "SELECT seg FROM AusSeguimientos seg "
                    + "WHERE seg.ausCasosId.id = :id "
                    + "AND seg.maeEstadoCodigo = :estado "
                    + "ORDER BY seg.id DESC ";

            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("id", idCaso);
            query.setParameter("estado", codigoEstado);
            query.setMaxResults(1);

            AusSeguimientos resultado = (AusSeguimientos) query.getSingleResult();
            ausSeguimiento = castEntidadNegocio(resultado);

        } catch (NoResultException e) {
            ausSeguimiento = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return ausSeguimiento;
    }

}
