/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.servicios.reserva;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.reserva.RtReservaArchivo;
import com.saviasaludeps.savia.ejb.entidades.RtReservaArchivos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.reserva.RtReservaArchivoRemoto;
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
 * @author idbohorquez
 */
@Stateless
@Remote(RtReservaArchivoRemoto.class)
@Local(ReservaArchivoLocal.class)
public class ReservaArchivoServicio extends GenericoServicio implements ReservaArchivoLocal,RtReservaArchivoRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(p) FROM RtReservaArchivos p ");
            strQuery.append(" WHERE p.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND p.id = '").append((String) e.getValue()).append("' ");
                            break;
                        case "idReserva":
                            strQuery.append("AND p.rtReservasId = '").append((String) e.getValue()).append("' ");
                            break;
                        case "archivoNombre":
                            strQuery.append("AND p.archivoNombre like '%").append((String) e.getValue()).append("%' ");
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
    public List<RtReservaArchivo> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<RtReservaArchivo> listResult = new ArrayList();
        try {
            StringBuilder strQuery = new  StringBuilder("SELECT p  ");
            strQuery.append(" FROM RtReservaArchivos p WHERE p.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND p.id = '").append((String) e.getValue()).append("' ");
                            break;  
                        case "idReserva":
                            strQuery.append("AND p.rtReservasId = '").append((String) e.getValue()).append("' ");
                            break;   
                        case "archivoNombre":
                            strQuery.append("AND p.archivoNombre like '%").append((String) e.getValue()).append("%' ");
                            break;
                    }
                }
            }
            strQuery.append("ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                strQuery.append("p.").append(paramConsulta.getOrden()).append(" ");
                strQuery.append((paramConsulta.isAscendente() ? "ASC" : "DESC"));
            } else {
                strQuery.append("p.id");
            }
            List<RtReservaArchivos> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (RtReservaArchivos reserva : list) {
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
    public RtReservaArchivo consultar(int id) throws Exception {
        RtReservaArchivo objResult = new RtReservaArchivo();

        try {
            objResult = castearEntidadNegocio((RtReservaArchivos) getEntityManager().find(RtReservaArchivos.class, id));
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
    public List<RtReservaArchivo> consultarListaTipo(Integer id, Integer tipo) throws java.lang.Exception {
        List<RtReservaArchivo> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT p FROM RtReservaArchivos p  ");
            strQuery.append(" inner join RtReservas r on p.rtReservasId = r.id and r.id = :id and p.tipo = :tipo");

            List<RtReservaArchivos> list = getEntityManager().createQuery(strQuery.toString())
                    .setParameter("id", id)
                    .setParameter("tipo", tipo)
                    .getResultList();
            for (RtReservaArchivos datos : list) {
                listResult.add(castearEntidadNegocio(datos));
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
    public void cambiarEstadoArchivo(RtReservaArchivo obj) throws java.lang.Exception {
        try {
            String sql = "UPDATE RtReservaArchivos SET "
                    + "estado = :estado, "
                    + "descargado = :descargado, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "where id = :id ";

            Query query = getEntityManager().createQuery(sql);            
            query.setParameter("estado", obj.getEstado());
            query.setParameter("descargado", obj.getDescargado());
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

    private RtReservaArchivo castearEntidadNegocio(RtReservaArchivos ent) throws Exception  {
        RtReservaArchivo obj = new RtReservaArchivo();
        obj.setId(ent.getId());
        obj.setTipo(ent.getTipo());
        obj.setEstado(ent.getEstado());
        obj.setObservacion(ent.getObservacion());
        obj.setDescargado(ent.getDescargado());
        obj.setTieneArchivo(ent.getTieneArchivo());
        obj.setArchivoNombre(ent.getArchivoNombre());
        obj.setRuta(ent.getRuta());
        obj.setArchivo(ent.getArchivo());
        obj.setFechaHoraInicio(ent.getFechaHoraInicio());
        obj.setFechaHoraFin(ent.getFechaHoraFin());
        obj.setTiempo(ent.getTiempo());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getUsuarioModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        return obj;
    }

}
