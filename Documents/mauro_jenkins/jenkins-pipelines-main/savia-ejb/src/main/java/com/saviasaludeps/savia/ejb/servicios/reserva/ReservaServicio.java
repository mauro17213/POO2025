/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.servicios.reserva;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.reserva.RtReserva;
import com.saviasaludeps.savia.ejb.entidades.RtReservas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.reserva.RtReservaRemoto;
import javax.persistence.Query;

/**
 *
 * @author idbohorquez
 */
@Stateless
@Remote(RtReservaRemoto.class)
@Local(ReservaLocal.class)
public class ReservaServicio extends GenericoServicio implements ReservaLocal,RtReservaRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(p) FROM RtReservas p ");
            strQuery.append(" WHERE p.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND p.id = '").append((String) e.getValue()).append("' ");
                            break;
                        case "nombre":
                            strQuery.append("AND p.nombre like '%").append((String) e.getValue()).append("%' ");
                            break;
                        case "estado":
                            strQuery.append("AND p.estados = '").append((String) e.getValue()).append("' ");
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery.toString())
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
    public List<RtReserva> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<RtReserva> listResult = new ArrayList();
        try {
            StringBuilder strQuery = new  StringBuilder("SELECT p  ");
            strQuery.append(" FROM RtReservas p WHERE p.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND p.id = '").append((String) e.getValue()).append("' ");
                            break;    
                        case "nombre":
                            strQuery.append("AND p.nombre = '").append((String) e.getValue()).append("' ");
                            break;
                        case "estado":
                            strQuery.append("AND p.estados = '").append((String) e.getValue()).append("' ");
                            break;
                    }
                }
            }
            strQuery.append("ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                strQuery.append("p.").append(paramConsulta.getOrden()).append(" ");
                strQuery.append((paramConsulta.isAscendente() ? "ASC" : "DESC"));
            } else {
                strQuery.append("p.nombre DESC");
            }
            List<RtReservas> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (RtReservas reserva : list) {
                listResult.add(castearEntidadNegocio(reserva));
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
    public RtReserva consultar(int id) throws Exception {
        RtReserva objResult = new RtReserva();

        try {
            objResult = castearEntidadNegocio((RtReservas) getEntityManager().find(RtReservas.class, id));
        } catch (NoResultException e) {
            objResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return objResult;
    }
    
    @Override
    public void cambiarEstadoReserva(RtReserva obj) throws java.lang.Exception {
        try {
            StringBuilder sql = new StringBuilder("UPDATE RtReservas SET ");
            sql.append(" estados = :estado, ");
            sql.append(" observacion = :observacion, ");
            sql.append(" usuarioModifica = :usuarioModifica, ");
            sql.append(" terminalModifica = :terminalModifica, ");
            sql.append(" fechaHoraModifica = :fechaHoraModifica ");
            sql.append(" where id = :id ");
            Query query = getEntityManager().createQuery(sql.toString());            
            query.setParameter("estado", obj.getEstado());
            query.setParameter("observacion", obj.getObservacion());
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
    
    private RtReserva castearEntidadNegocio(RtReservas ent) throws Exception  {
        RtReserva obj = new RtReserva();
        obj.setId(ent.getId());
        obj.setNombre(ent.getNombre());
        obj.setEstado(ent.getEstados());
        obj.setObservacion(ent.getObservacion());
        obj.setFecha(ent.getFecha());
        obj.setFechaHoraInicio(ent.getFechaHoraInicio());
        obj.setFechaHoraFin(ent.getFechaHoraFin());
        obj.setTiempo(ent.getTiempo());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        return obj;
    }

}
