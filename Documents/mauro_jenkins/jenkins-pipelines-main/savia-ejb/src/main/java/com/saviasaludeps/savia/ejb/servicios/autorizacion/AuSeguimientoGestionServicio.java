/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimiento;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoGestion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuSeguimientoGestiones;
import com.saviasaludeps.savia.ejb.entidades.AuSeguimientos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuSeguimientoGestionRemoto;
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
 * @author iavenegas
 */
@Stateless
@Remote(AuSeguimientoGestionRemoto.class)
public class AuSeguimientoGestionServicio extends GenericoServicio implements AuSeguimientoGestionRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuSeguimientoGestiones p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND p.auSeguimientosId.id = " + paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "tipo":
                            strQuery += "AND p.tipo = " + e.getValue() + " ";
                            break;
                        case "maeEstadoSeguimientoGestionId":
                            strQuery += "AND p.maeEstadoSeguimientoGestionId = " + e.getValue() + " ";
                            break;
                        case "maeMotivoSeguimientoId":
                            strQuery += "AND p.maeMotivoSeguimientoId = " + e.getValue() + " ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += "AND p.fechaHoraCrea = " + e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea = '" + e.getValue() + "' ";
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
    public List<AuSeguimientoGestion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuSeguimientoGestion> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuSeguimientoGestiones p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND p.auSeguimientosId.id = " + paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "tipo":
                            strQuery += "AND p.tipo = " + e.getValue() + " ";
                            break;
                        case "maeEstadoSeguimientoGestionId":
                            strQuery += "AND p.maeEstadoSeguimientoGestionId = " + e.getValue() + " ";
                            break;
                        case "maeMotivoSeguimientoId":
                            strQuery += "AND p.maeMotivoSeguimientoId = " + e.getValue() + " ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += "AND p.fechaHoraCrea = " + e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea = '" + e.getValue() + "' ";
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
            List<AuSeguimientoGestiones> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuSeguimientoGestiones per : list) {
                listaResultados.add(castEntidadNegocio(per));
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
    public AuSeguimientoGestion consultar(int id) throws Exception {
        AuSeguimientoGestion objRes = null;
        try {
            objRes = castEntidadNegocio((AuSeguimientoGestiones) getEntityManager().find(AuSeguimientoGestiones.class, id));
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
    public int insertar(AuSeguimientoGestion obj) throws Exception {
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
    public void actualizar(AuSeguimientoGestion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuSeguimientoGestiones a SET ";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());

            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    private static AuSeguimientoGestion castEntidadNegocio(AuSeguimientoGestiones ent) {
        AuSeguimientoGestion negocio = new AuSeguimientoGestion();
        negocio.setId(ent.getId());
        negocio.setDescripcion(ent.getDescripcion());
        negocio.setTipo(ent.getTipo());
        negocio.setMaeEstadoSeguimientoGestionId(ent.getMaeEstadoSeguimientoGestionId());
        negocio.setMaeEstadoSeguimientoGestionCodigo(ent.getMaeEstadoSeguimientoGestionCodigo());
        negocio.setMaeEstadoSeguimientoGestionValor(ent.getMaeEstadoSeguimientoGestionValor());
        negocio.setMaeMotivoSeguimientoId(ent.getMaeMotivoSeguimientoId());
        negocio.setMaeMotivoSeguimientoCodigo(ent.getMaeMotivoSeguimientoCodigo());
        negocio.setMaeMotivoSeguimientoValor(ent.getMaeMotivoSeguimientoValor());
        negocio.setFechaEntregaPropuesta(ent.getFechaEntregaPropuesta());
        negocio.setFechaHoraEntregaReal(ent.getFechaHoraEntregaReal());
        negocio.setBorrado(ent.getBorrado());
        negocio.setAuSeguimiento(new AuSeguimiento(ent.getAuSeguimientosId().getId()));
        //2023-12-04 jyperez inicializamos cada lista de adjuntos
        negocio.setAuSolicitudAdjuntosList(new ArrayList());

        negocio.setUsuarioCrea(ent.getUsuarioCrea());
        negocio.setTerminalCrea(ent.getTerminalCrea());
        negocio.setFechaHoraCrea(ent.getFechaHoraCrea());
        negocio.setUsuarioModifica(ent.getUsuarioModifica());
        negocio.setTerminalModifica(ent.getTerminalModifica());
        negocio.setFechaHoraModifica(ent.getFechaHoraModifica());
        return negocio;
    }

    private AuSeguimientoGestiones castNegocioEntidad(AuSeguimientoGestion negocio) {
        AuSeguimientoGestiones entidad = new AuSeguimientoGestiones();
        entidad.setId(negocio.getId());
        entidad.setDescripcion(negocio.getDescripcion());
        entidad.setTipo(negocio.getTipo());
        entidad.setMaeEstadoSeguimientoGestionId(negocio.getMaeEstadoSeguimientoGestionId());
        entidad.setMaeEstadoSeguimientoGestionCodigo(negocio.getMaeEstadoSeguimientoGestionCodigo());
        entidad.setMaeEstadoSeguimientoGestionValor(negocio.getMaeEstadoSeguimientoGestionValor());
        entidad.setMaeMotivoSeguimientoId(negocio.getMaeMotivoSeguimientoId());
        entidad.setMaeMotivoSeguimientoCodigo(negocio.getMaeMotivoSeguimientoCodigo());
        entidad.setMaeMotivoSeguimientoValor(negocio.getMaeMotivoSeguimientoValor());
        entidad.setFechaEntregaPropuesta(negocio.getFechaEntregaPropuesta());
        entidad.setFechaHoraEntregaReal(negocio.getFechaHoraEntregaReal());
        entidad.setBorrado(negocio.getBorrado());
        entidad.setAuSeguimientosId(new AuSeguimientos(negocio.getAuSeguimiento().getId()));

        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        return entidad;
    }

    public static List<AuSeguimientoGestion> casteoListaEntidadNegocio(List<AuSeguimientoGestiones> listaEntidad) {
        List<AuSeguimientoGestion> listaNegocio = new ArrayList();
        listaEntidad.forEach(entidad -> {
            listaNegocio.add(castEntidadNegocio(entidad));
        });
        return listaNegocio;
    }

}
