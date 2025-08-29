/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.servicios.reserva;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.reserva.RtReservaArchivoProceso;
import com.saviasaludeps.savia.ejb.entidades.RtReservaArchivoProcesos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.reserva.RtReservaArchivoProcesoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author aguevara
 */
@Stateless
@Remote(RtReservaArchivoProcesoRemoto.class)
@Local(ReservaArchivoProcesoLocal.class)
public class ReservaArchivoProcesoServicio extends GenericoServicio implements ReservaArchivoProcesoLocal, RtReservaArchivoProcesoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(p) FROM RtReservaArchivoProcesos p ");
            strQuery.append(" WHERE p.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND p.id = '").append((String) e.getValue()).append("' ");
                            break;
                        case "idReserva":
                            strQuery.append("AND p.rtReservasArchivoId = '").append((String) e.getValue()).append("' ");
                            break;
                        case "archivoNombre":
                            strQuery.append("AND p.nombre like '%").append((String) e.getValue()).append("%' ");
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
    public RtReservaArchivoProceso consulta(int id) throws java.lang.Exception {
        RtReservaArchivoProceso objResult = new RtReservaArchivoProceso();

        try {
            objResult = castearEntidadNegocio((RtReservaArchivoProcesos) getEntityManager().find(RtReservaArchivoProcesos.class, id));
        } catch (NoResultException e) {
            objResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return objResult;
    }

    

    private RtReservaArchivoProceso castearEntidadNegocio(RtReservaArchivoProcesos ent) {

        RtReservaArchivoProceso obj = new RtReservaArchivoProceso();
        obj.setId(ent.getId());
        obj.setNombre(ent.getNombre());
        obj.setEstado(ent.getEstado());
        obj.setFechaHoraInicio(ent.getFechaHoraInicio());
        obj.setFechaHoraFin(ent.getFechaHoraFin());
        obj.setTiempo(ent.getTiempo());
        obj.setDescripcion(ent.getDescripcion());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());

        return obj;

    }

    @Override
    public List<RtReservaArchivoProceso> consultarLista(int idReservaArchivo) throws java.lang.Exception {
        List<RtReservaArchivoProceso> listaResultado = new ArrayList<>();
        try {
            String strQuery = "FROM RtReservaArchivoProcesos c "
                    + " WHERE c.rtReservaArchivosId.id = "+idReservaArchivo
                    + " ORDER BY c.id ASC";
            List<RtReservaArchivoProcesos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (RtReservaArchivoProcesos proceso : list) {
                listaResultado.add(castearEntidadNegocio(proceso));
            }
        } catch (NoResultException e) {
            listaResultado = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultado;
    }

}
