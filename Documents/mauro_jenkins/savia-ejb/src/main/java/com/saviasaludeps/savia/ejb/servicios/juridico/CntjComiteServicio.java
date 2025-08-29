
package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjComite;
import com.saviasaludeps.savia.ejb.entidades.CntjComites;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CtnjComiteRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author idbohorquez
 */

@Stateless
@Remote(CtnjComiteRemoto.class)
public class CntjComiteServicio extends GenericoServicio implements CtnjComiteRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjComites c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append(" AND c.id = ").append(e.getValue());
                            break;
                        case "estado":
                            strQuery.append(" AND c.estado = ").append(e.getValue());
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
    public List<CntjComite> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntjComite> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjComites c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append(" AND c.id = ").append(e.getValue());
                            break;
                        case "estado":
                            strQuery.append(" AND c.estado = ").append(e.getValue());
                            break;
                    }
                }
            }
            strQuery.append(" ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                strQuery.append(" c." + paramConsulta.getOrden() + " " + (paramConsulta.isAscendente() ? "ASC" : "DESC"));
            } else {
                strQuery.append(" c.id DESC ");
            }
            List<CntjComites> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjComites comite  : list) {
                listResult.add(castEntidadNegocio(comite));
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
    public int insertar(CntjComite objeto) throws java.lang.Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(objeto)).getId();
            objeto.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }
    
    @Override
    public CntjComite consultar(int id) throws java.lang.Exception {
        CntjComite objRes = null;
        try {
            objRes = castEntidadNegocio((CntjComites) getEntityManager().find(CntjComites.class, id));
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
    public void actualizar(CntjComite objeto) throws java.lang.Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CntjComites SET estado = :estado, ");
            sql.append("fechaProgramacion = :fechaProgramacion,  ");
            sql.append("fechaHoraInicio = :fechaHoraInicio,  ");
            sql.append("fechaHoraFin = :fechaHoraFin,  ");
            sql.append("observaciones = :observaciones,  ");
            sql.append("conclusiones = :conclusiones,  ");            
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estado", objeto.getEstado().shortValue());
            query.setParameter("fechaProgramacion", objeto.getFechaProgramacion());
            query.setParameter("fechaHoraInicio", objeto.getFechaInicio());
            query.setParameter("fechaHoraFin", objeto.getFechaFin());
            query.setParameter("observaciones", objeto.getObservacion());
            query.setParameter("conclusiones", objeto.getConclusion());            
            query.setParameter("usuarioModifica", objeto.getUsuarioModifica());
            query.setParameter("terminalModifica", objeto.getTerminalModifica());
            query.setParameter("fechaHoraModifica", objeto.getFechaHoraModifica());            
            query.setParameter("id", objeto.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public CntjComite comiteAbierto() throws java.lang.Exception {
        CntjComite objRes = null;
        try {            
            StringBuilder query = new StringBuilder();
            query.append("SELECT c FROM CntjComites c WHERE c.id > 0 AND c.estado = 1 "); 
            CntjComites comites =  getEntityManager().createQuery(query.toString(), CntjComites.class)
                    .getSingleResult(); 
            objRes = castEntidadNegocio(comites);
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }
    
    
    private CntjComite castEntidadNegocio(CntjComites entidad) {
        CntjComite objeto = new CntjComite();
        objeto.setId(entidad.getId());
        objeto.setEstado((int) entidad.getEstado());
        objeto.setFechaProgramacion(entidad.getFechaProgramacion());
        objeto.setFechaInicio(entidad.getFechaHoraInicio());
        objeto.setFechaFin(entidad.getFechaHoraFin());    
        objeto.setObservacion(entidad.getObservaciones());    
        objeto.setConclusion(entidad.getConclusiones());    
        objeto.setAdjuntoNombre(entidad.getAdjuntoNombre());    
        objeto.setAdjuntoArchivo(entidad.getAdjuntoArchivo());    
        objeto.setAdjuntoRuta(entidad.getAdjuntoRuta());    
        objeto.setAdjuntoExiste(entidad.getAdjuntoExiste());
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        objeto.setUsuarioModifica(entidad.getUsuarioModifica());
        objeto.setFechaHoraModifica(entidad.getFechaHoraModifica());
        objeto.setTerminalModifica(entidad.getTerminalModifica());
        return objeto;
    }
    
    private CntjComites castNegocioEntidad(CntjComite obj){
        CntjComites ent = new CntjComites();
        ent.setFechaProgramacion(obj.getFechaProgramacion());        
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }

    
}
