/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsValidacion;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsValidacionHistorico;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmFeRipsValidaciones;
import com.saviasaludeps.savia.ejb.entidades.CmFeRipsValidacionesHistoricos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmFeRipsValidacionHistoricoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Jepn
 */
@Stateless
@Remote(CmFeRipsValidacionHistoricoRemoto.class)
public class CmFeRipsValidacionHistoricoServicio extends GenericoServicio implements CmFeRipsValidacionHistoricoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(h) FROM CmFeRipsValidacionesHistoricos h "
                    + "WHERE h.id > 0 AND h.cmFeRipsValidacionesId.id = :id  ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND h.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "maeContratoModalidadId":
                            strQuery += "AND h.maeContratoModalidadId = " +  e.getValue() + " ";
                            break;
                         case "coberturaCierre":
                            strQuery += "AND h.coberturaCierre = " +  e.getValue() + " ";
                            break;
                    }
                }
            }
            
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("id", paramConsulta.getParametroConsulta1());
            }
            cant = (int) (long) query.getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (NumberFormatException e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }
    
    @Override
    public List<CmFeRipsValidacionHistorico> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmFeRipsValidacionHistorico> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmFeRipsValidacionesHistoricos h "
                    + "WHERE h.id > 0 AND h.cmFeRipsValidacionesId.id = :id  ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND h.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "maeContratoModalidadId":
                            strQuery += "AND h.maeContratoModalidadId = " +  e.getValue() + " ";
                            break;
                        case "coberturaCierre":
                            strQuery += "AND h.coberturaCierre = " +  e.getValue() + " ";
                            break;

                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "h." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "h.id DESC ";
            }
            
             Query query = getEntityManager().createQuery(strQuery);
            
            if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("id", paramConsulta.getParametroConsulta1());
            }
            
            
            List<CmFeRipsValidacionesHistoricos> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CmFeRipsValidacionesHistoricos per : list) {
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
    public int insertar(CmFeRipsValidacionHistorico obj) throws Exception {
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
    public void actualizar(CmFeRipsValidacionHistorico obj) throws Exception {
        try {
            String hql = "UPDATE CmFeRipsValidacionesHistoricos SET "
                    + "estado = :estado, "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CmFeRipsValidacionHistorico eliminar(int id) throws Exception {
        CmFeRipsValidacionHistorico obj = null;
        try {
            CmFeRipsValidacionesHistoricos ent = getEntityManager().find(CmFeRipsValidacionesHistoricos.class, id);
            if (ent != null) {
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
    public CmFeRipsValidacionHistorico consultar(int id) throws Exception {
        CmFeRipsValidacionHistorico objRes = null;
        try {
            objRes = castEntidadNegocio((CmFeRipsValidacionesHistoricos) getEntityManager().find(CmFeRipsValidacionesHistoricos.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    

    public static CmFeRipsValidacionHistorico castEntidadNegocio(CmFeRipsValidacionesHistoricos ent) {
        CmFeRipsValidacionHistorico neg = new CmFeRipsValidacionHistorico();
        neg.setId(ent.getId());
        if (ent.getCmFeRipsValidacionesId() != null) {
            CmFeRipsValidacion validacion = new CmFeRipsValidacion(ent.getCmFeRipsValidacionesId().getId());
            validacion.setNombreValidacion(ent.getCmFeRipsValidacionesId().getNombreValidacion());
            neg.setCmFeRipsValidacion(validacion);
        }   
        neg.setEstado(ent.getEstado());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        return neg;
    }

    public static CmFeRipsValidacionesHistoricos castNegocioEntidad(CmFeRipsValidacionHistorico neg) {
        CmFeRipsValidacionesHistoricos ent = new CmFeRipsValidacionesHistoricos();
        ent.setId(neg.getId());
        if (neg.getCmFeRipsValidacion()!= null) {
            ent.setCmFeRipsValidacionesId(new CmFeRipsValidaciones(neg.getCmFeRipsValidacion().getId()));
        }
        ent.setEstado(neg.getEstado());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        return ent;
    }
}
