/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.Recurrencia;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.GnRecurrencias;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.administracion.RecurrenciaRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;


/**
 *
 * @author jjmosquera
 */
@Stateless
@Remote(RecurrenciaRemoto.class)
public class RecurrenciaServicio extends GenericoServicio implements RecurrenciaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(DISTINCT e) FROM GnRecurrencias e "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND e.nombre LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "script":
                            strQuery += "AND e.script = " + (String) e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += "AND e.activo = " +  e.getValue() + " ";
                            break;                      
                        case "tipoPeriodicidad":
                            strQuery += "AND e.tipoPeriodicidad = " +  e.getValue() + " ";
                            break;
                        case "periodicidad":
                            strQuery += "AND e.periodicidad = " +  e.getValue() + " ";
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
    public List<Recurrencia> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<Recurrencia> listResult = new ArrayList();
        try {
            String strQuery = "FROM GnRecurrencias e " 
                     + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND e.nombre LIKE  '" + (String) e.getValue() + "%' ";
                            break;
                        case "script":
                            strQuery += "AND e.script = " + (String) e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += "AND e.activo = " + e.getValue() + " ";
                            break;                   
                        case "tipoPeriodicidad":
                            strQuery += "AND e.tipoPeriodicidad = " +  e.getValue() + " ";
                            break;
                        case "periodicidad":
                            strQuery += "AND e.periodicidad = " +  e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "e." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "e.nombre ASC ";
            }
            List<GnRecurrencias> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GnRecurrencias per : list) {
                listResult.add(castRecurrenciaNegocio(per));
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

    public static Recurrencia castRecurrenciaNegocio(GnRecurrencias rec) {
        Recurrencia obj = new Recurrencia();
        obj.setId(rec.getId());
        obj.setNombre(rec.getNombre());
        obj.setDescripcion(rec.getDescripcion());
        obj.setScript(rec.getScript());
        obj.setActivo(rec.getActivo());
        obj.setFechaInicio(rec.getFechaInicio());
        obj.setFechaHoraEjecucion(rec.getFechaHoraEjecucion());
        obj.setFechaFin(rec.getFechaFin());
        obj.setTipoPeriodicidad(rec.getTipoPeriodicidad());       
        obj.setPeriodicidad(rec.getPeriodicidad());
        obj.setUsuarioCrea(rec.getUsuarioCrea());
        obj.setTerminalCrea(rec.getTerminalCrea());
        obj.setFechaHoraCrea(rec.getFechaHoraCrea());
        obj.setUsuarioModifica(rec.getUsuarioModifica());
        obj.setTerminalModifica(rec.getTerminalModifica());
        obj.setFechaHoraModifica(rec.getFechaHoraModifica());

        return obj;
    }

    @Override
    public Recurrencia consultar(int id) throws java.lang.Exception {
          Recurrencia objRes = null;
        try {
            objRes = castRecurenciaConsulta((GnRecurrencias) getEntityManager().find(GnRecurrencias.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }
    
        public static Recurrencia castRecurenciaConsulta(GnRecurrencias rec) {
        Recurrencia obj = new Recurrencia();
        obj.setId(rec.getId());
        obj.setNombre(rec.getNombre());
        obj.setDescripcion(rec.getDescripcion());
        obj.setScript(rec.getScript());
        obj.setActivo(rec.getActivo());
        obj.setFechaInicio(rec.getFechaInicio());
        obj.setFechaHoraEjecucion(rec.getFechaHoraEjecucion());
        obj.setFechaFin(rec.getFechaFin());
        obj.setTipoPeriodicidad(rec.getTipoPeriodicidad());
        obj.setPeriodicidad(rec.getPeriodicidad());     
        obj.setUsuarioCrea(rec.getUsuarioCrea());
        obj.setTerminalCrea(rec.getTerminalCrea());
        obj.setFechaHoraCrea(rec.getFechaHoraCrea());
        obj.setUsuarioModifica(rec.getUsuarioModifica());
        obj.setTerminalModifica(rec.getTerminalModifica());
        obj.setFechaHoraModifica(rec.getFechaHoraModifica());

        return obj;
    }

    @Override
    public int insertar(Recurrencia obj) throws java.lang.Exception {
           int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castRecurrenciaEntidad(obj)).getId();
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
    
     public static GnRecurrencias  castRecurrenciaEntidad(Recurrencia rec) {
        GnRecurrencias obj = new GnRecurrencias();     
        obj.setId(rec.getId());
        obj.setNombre(rec.getNombre());
        obj.setDescripcion(rec.getDescripcion());
        obj.setScript(rec.getScript());
        obj.setActivo(rec.isActivo());
        obj.setFechaInicio(rec.getFechaInicio());
        obj.setFechaHoraEjecucion(rec.getFechaHoraEjecucion());
        obj.setFechaFin(rec.getFechaFin());
        obj.setPeriodicidad(rec.getPeriodicidad());
        obj.setTipoPeriodicidad(rec.getTipoPeriodicidad());
        obj.setUsuarioCrea(rec.getUsuarioCrea());
        obj.setTerminalCrea(rec.getTerminalCrea());
        obj.setFechaHoraCrea(rec.getFechaHoraCrea());
        obj.setUsuarioModifica(rec.getUsuarioModifica());
        obj.setTerminalModifica(rec.getTerminalModifica());
        obj.setFechaHoraModifica(rec.getFechaHoraModifica());

        return obj;
    }

    @Override
    public void actualizar(Recurrencia obj) throws java.lang.Exception {
              try {
                  
            String hql = "UPDATE GnRecurrencias SET "                    
                    + "nombre = :nombre, "                  
                    + "descripcion = :descripcion, "
                    + "script = :script, "
                    + "activo = :activo, "
                    + "fechaInicio = :fechaInicio, "
                    + "fechaHoraEjecucion = :fechaHoraEjecucion, "
                    + "fechaFin = :fechaFin, "
                    + "tipoPeriodicidad = :tipoPeriodicidad, "
                    + "periodicidad = :periodicidad, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("nombre", obj.getNombre());            
            query.setParameter("descripcion", obj.getDescripcion());            
            query.setParameter("script", obj.getScript());
            query.setParameter("activo", obj.isActivo());
            query.setParameter("fechaInicio", obj.getFechaInicio());
            query.setParameter("fechaHoraEjecucion", obj.getFechaHoraEjecucion());
            query.setParameter("fechaFin", obj.getFechaFin());
            query.setParameter("tipoPeriodicidad", obj.getTipoPeriodicidad());
            query.setParameter("periodicidad", obj.getPeriodicidad());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());                      
            query.executeUpdate();
          
      
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public Recurrencia eliminar(int id) throws java.lang.Exception {
       
             Recurrencia obj = null;
        try {
            GnRecurrencias ent = getEntityManager().find(GnRecurrencias.class, id);
            if (ent != null) {
                obj = castRecurenciaConsulta(ent);
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
    public Recurrencia consultarPorRecurrencia(String nombre) throws java.lang.Exception {
      
           Recurrencia obj = null;
        String strQuery = "FROM GnRecurrencias"
                + " WHERE nombre = :nombre";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("nombre", nombre);
            obj = castRecurrenciaNegocio((GnRecurrencias) query.getSingleResult());
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            obj = null;
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

}
