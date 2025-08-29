/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.atencionusuario;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.atencionusuario.AusServicioGestionHistorico;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCasoServicio;
import com.saviasaludeps.savia.ejb.entidades.AusCasoServicios;
import com.saviasaludeps.savia.ejb.entidades.AusServicioGestiones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.atencionusuario.AusServicioGestionHistoricoRemoto;
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
@Remote(AusServicioGestionHistoricoRemoto.class)
@Local(AusServicioGestionHistoricoLocal.class)
public class AusServicioGestionHistoricoServicio extends GenericoServicio implements AusServicioGestionHistoricoLocal, AusServicioGestionHistoricoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(s) FROM AusServicioGestiones s "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "estado":
                            strQuery += "AND s.maeEstadoId = " + (String) e.getValue() + " ";
                            break;
                        case "id":
                            strQuery += "AND s.id = " + (String) e.getValue() + " ";
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
    public List<AusServicioGestionHistorico> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<AusServicioGestionHistorico> listResult = new ArrayList();
        try {
            String strQuery = "FROM AusServicioGestiones s "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "estado":
                            strQuery += "AND s.maeEstadoId = " + e.getValue() + " ";
                            break;
                        case "id":
                            strQuery += "AND s.id = " + (String) e.getValue() + " ";
                            break;
                        case "serviciosId":
                            strQuery += "AND s.ausServiciosId.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY s.fechaHoraCrea DESC";
            List<AusServicioGestiones> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            
            for (AusServicioGestiones per : list) {
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
    public int insertar(AusServicioGestionHistorico obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al insertar el Servicio Gestion");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(AusServicioGestionHistorico obj) throws java.lang.Exception {
        try {
            String hql = "UPDATE AusServicioGestiones SET"
                    + " maeEstadoId = :maeEstadoId,"
                    + " maeEstadoCodigo = :maeEstadoCodigo,"
                    + " maeEstadoValor = :maeEstadoValor,"
                    + " observacion = :observacion"
//                    + " usuarioCrea = :usuarioCrea,"
//                    + " terminalCrea = :terminalCrea,"
//                    + " fechaHoraCrea = :fechaHoraCrea"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);            
            query.setParameter("maeEstadoId", obj.getMaeEstadoId());
            query.setParameter("maeEstadoCodigo", obj.getMaeEstadoCodigo());
            query.setParameter("maeEstadoValor", obj.getMaeEstadoValor());
            query.setParameter("observacion", obj.getObservacion());
//            query.setParameter("usuarioCrea", obj.getUsuarioCrea());
//            query.setParameter("terminalCrea", obj.getTerminalCrea());
//            query.setParameter("fechaHoraCrea", obj.getFechaHoraCrea());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
//            getEntityManager().merge(castNegocioEntidad(obj));
        } catch (Exception e) {
            Exception(ACTUALIZAR, e, "Error al actualizar el servicio gestion");
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AusServicioGestionHistorico consultar(int id) throws Exception {
        AusServicioGestionHistorico objRes = null;
        try {
            AusServicioGestiones servicioGestionNegocio = (AusServicioGestiones) getEntityManager().find(AusServicioGestiones.class, id);
            objRes = castEntidadNegocio(servicioGestionNegocio);
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e, "Error al consultar un el Servicio Gestion");
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public AusServicioGestionHistorico eliminar(int id) throws java.lang.Exception {
        AusServicioGestionHistorico obj = null;
        try {
            AusServicioGestiones ent = getEntityManager().find(AusServicioGestiones.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e, "Error al eliminar un Servicio Gestion");
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    public static AusServicioGestiones castNegocioEntidad(AusServicioGestionHistorico servicioGestionEntidad) {
        AusServicioGestiones servicioGestiones = new AusServicioGestiones();
        servicioGestiones.setId(servicioGestionEntidad.getId());
        servicioGestiones.setAusServiciosId(new AusCasoServicios(servicioGestionEntidad.getCasoServicios().getId())); // revisar
        servicioGestiones.setMaeEstadoId(servicioGestionEntidad.getMaeEstadoId());
        servicioGestiones.setMaeEstadoCodigo(servicioGestionEntidad.getMaeEstadoCodigo());
        servicioGestiones.setMaeEstadoValor(servicioGestionEntidad.getMaeEstadoValor());
        servicioGestiones.setObservacion(servicioGestionEntidad.getObservacion());
        servicioGestiones.setUsuarioCrea(servicioGestionEntidad.getUsuarioCrea());
        servicioGestiones.setFechaHoraCrea(servicioGestionEntidad.getFechaHoraCrea());
        servicioGestiones.setTerminalCrea(servicioGestionEntidad.getTerminalCrea());
        return servicioGestiones;
    }

    public static List<AusServicioGestionHistorico> castEntidadNegocio(List<AusServicioGestiones> seguimientosNegocio) {
        List<AusServicioGestionHistorico> listaServiciosHistoricos = new ArrayList();
        for (AusServicioGestiones servicioGestionNegocio : seguimientosNegocio) {
            listaServiciosHistoricos.add(castEntidadNegocio(servicioGestionNegocio));
        }
        return listaServiciosHistoricos;
    }

    public static AusServicioGestionHistorico castEntidadNegocio(AusServicioGestiones servicioGestionNegocio) {
        AusServicioGestionHistorico servicioGestion = new AusServicioGestionHistorico();
        servicioGestion.setId(servicioGestionNegocio.getId());
        servicioGestion.setCasoServicios(new AusCasoServicio());
        //servicioGestion.setEstado(servicioGestionNegocio.getEstado());
        servicioGestion.setMaeEstadoId(servicioGestionNegocio.getMaeEstadoId());
        servicioGestion.setMaeEstadoCodigo(servicioGestionNegocio.getMaeEstadoCodigo());
        servicioGestion.setMaeEstadoValor(servicioGestionNegocio.getMaeEstadoValor());
        servicioGestion.setObservacion(servicioGestionNegocio.getObservacion());
        servicioGestion.setUsuarioCrea(servicioGestionNegocio.getUsuarioCrea());
        servicioGestion.setFechaHoraCrea(servicioGestionNegocio.getFechaHoraCrea());
        servicioGestion.setTerminalCrea(servicioGestionNegocio.getTerminalCrea());
        return servicioGestion;
    }

}
