
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.saviasaludeps.savia.dominio.especial.PeGestion;
import com.saviasaludeps.savia.dominio.especial.PeGestionHistorico;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.PeGestiones;
import com.saviasaludeps.savia.ejb.entidades.PeGestionesHistorico;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.especial.PeGestionHistoricoRemoto;
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
@Remote(PeGestionHistoricoRemoto.class)
public class PeGestionHistoricoServicio extends GenericoServicio implements PeGestionHistoricoRemoto {
    
    /**
     * Consulta de cantidad de registros en una lista de historico gestiones
     *
     * @author idbohorquez
     * @creado 30/11/2023
     * @param paramConsulta
     * @return int
     * @throws Exception
     */
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            StringBuilder strTitulo = new StringBuilder();
            strTitulo.append("SELECT COUNT(p.id) FROM PeGestionesHistorico p WHERE p.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strTitulo.append(" AND p.id = '" + (String) e.getValue() + "' ");
                            break;                        
                    }
                }
            }            
            Query query = getEntityManager().createQuery(strTitulo.toString());            
            cant = (int) (long) query.getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

    /**
     * Consultar lista de registros de historico
     *
     * @author idbohorquez
     * @return 
     * @creado 29/08/2022
     * @param paramConsulta
     * @throws Exception
     */
    @Override
    public List<PeGestionHistorico> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<PeGestionHistorico> listResult = new ArrayList<>();
        try {
            StringBuilder strTitulo = new StringBuilder();
            strTitulo.append("SELECT p FROM PeGestionesHistorico p WHERE p.id > 0  ");
           
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strTitulo.append(" AND p.id = '" + (String) e.getValue() + "' ");
                            break;                        
                    }
                }
            }
            strTitulo.append(" ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                strTitulo.append("p." + paramConsulta.getOrden() + " ").append((paramConsulta.isAscendente() ? "ASC" : "DESC"));
            } else {
                strTitulo.append(" p.id DESC ");
            }
            Query query = getEntityManager().createQuery(strTitulo.toString());
            query.setFirstResult(paramConsulta.getPrimerRegistro());
            query.setMaxResults(paramConsulta.getRegistrosPagina());
            List<PeGestionesHistorico> list = query.getResultList();
            for (PeGestionesHistorico dir : list) {
                listResult.add(castEntidadNegocio(dir));
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
    public List<PeGestionHistorico> consultarListaHistorico(Integer idGestion) throws Exception {
        List<PeGestionHistorico> listResult = new ArrayList<>();
        try {
            StringBuilder strTitulo = new StringBuilder();
            strTitulo.append("SELECT p FROM PeGestionesHistorico p WHERE p.peGestionesId.id = :idGestion order by id desc ");           
            
            Query query = getEntityManager().createQuery(strTitulo.toString());
            query.setParameter("idGestion", idGestion);
            List<PeGestionesHistorico> list = query.getResultList();
            for (PeGestionesHistorico dir : list) {
                listResult.add(castEntidadNegocio(dir));
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
    
    /**
     * Funcion encargada de guardar un nuevo registro de historico
     *
     * @author idbohorquez
     * @param obj
     * @creado 30/08/2022
     * @return int
     * @throws Exception
     */
    @Override
    public int insertar(PeGestionHistorico obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }
    
    
    public PeGestionHistorico castEntidadNegocio(PeGestionesHistorico ent) throws Exception {
        PeGestionHistorico obj = new PeGestionHistorico();
        obj.setId(ent.getId());
        obj.setDescripcion(ent.getDescripcion());
        obj.setPeGestionId(new PeGestion(ent.getPeGestionesId().getId()));
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        return obj;
    }
    
    public PeGestionesHistorico castNegocioEntidad(PeGestionHistorico obj) throws Exception {
        PeGestionesHistorico ent = new PeGestionesHistorico();
        ent.setId(obj.getId());
        ent.setDescripcion(obj.getDescripcion());
        ent.setPeGestionesId(new PeGestiones(obj.getPeGestionId().getId()));
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }
    
}
