/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.conciliacion;




import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuestaDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmDetalles;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.CmGlosaRespuestaDetalles;
import com.saviasaludeps.savia.ejb.entidades.CmGlosaRespuestas;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmGlosaRespuestaDetalleRemoto;
import java.math.BigDecimal;
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
@Remote(CmGlosaRespuestaDetalleRemoto.class)
@Local(CmGlosaRespuestaDetalleLocal.class)
public class CmGlosaRespuestaDetalleServicio extends GenericoServicio implements CmGlosaRespuestaDetalleLocal, CmGlosaRespuestaDetalleRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cmgr) FROM CmGlosaRespuestaDetalles cmgr ";         
            strQuery += " WHERE cmgr.cmGlosaRespuestasId.id = :glosaRespuestaId ";
                           
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "documento":
                            strQuery += " AND cmgr.documento LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "id":
                            strQuery += " AND cmgr.id LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            
           Query query = getEntityManager().createQuery(strQuery).
                         setParameter("glosaRespuestaId", paramConsulta.getParametroConsulta1());   
                    
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

    @Override
    public List<CmGlosaRespuestaDetalle> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmGlosaRespuestaDetalle> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmGlosaRespuestaDetalles cmgr ";        
            strQuery += " WHERE cmgr.cmGlosaRespuestasId.id = :glosaRespuestaId ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                     switch ((String) e.getKey()) {
                      
                         case "id":
                             strQuery += " AND cmgr.id = '" + e.getValue() + "' ";
                         case "documento":
                             strQuery += " AND cmgr.documento LIKE '%" + e.getValue() + "%' ";
                             break;
                         case "cmGlosaRespuesta.id":
                             strQuery += " AND cmgr.cmGlosaRespuestasId.id LIKE '%" + e.getValue() + "%' ";
                             break;
                         case "cmDetalle.id":
                             strQuery += " AND cmgr.cmDetallesId.id LIKE '%" + e.getValue() + "%' ";
                             break;
                         case "valorCobroDetalle":
                             strQuery += " AND cmgr.valorCobroDetalle LIKE '%" + e.getValue() + "%' ";
                             break;
                         case "valorFacturado":
                             strQuery += " AND cmgr.valorFacturado LIKE '%" + e.getValue() + "%' ";
                             break;
                         case "valorPagado":
                             strQuery += " AND cmgr.valorPagado LIKE '%" + e.getValue() + "%' ";
                             break;
                         case "valorPagadoEps":
                             strQuery += " AND cmgr.valorPagadoEps LIKE '%" + e.getValue() + "%' ";
                             break;
                         case "obj.valorPendiente":
                             strQuery += " AND cmgr.valorPendiente LIKE '%" + e.getValue() + "%' ";
                             break;
                    }
                }
            }

            strQuery += " ORDER BY ";
            
            if (paramConsulta.getOrden() != null) {
                
                String order = paramConsulta.getOrden().replace("cmGlosaRespuesta", "cmGlosaRespuestasId").
                                                        replace("cmDetalle", "cmDetallesId");
                strQuery += " cmgr." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " cmgr.fechaHoraCrea DESC";
            }
            Query query = getEntityManager().createQuery(strQuery). 
                    setParameter("glosaRespuestaId", paramConsulta.getParametroConsulta1());          
            
            List<CmGlosaRespuestaDetalles> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CmGlosaRespuestaDetalles neg : list) {
                listResult.add(castEntidadNegocio(neg));
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
    public CmGlosaRespuestaDetalle consultar(int id) throws Exception {
        CmGlosaRespuestaDetalle obj = null;
        try {
            CmGlosaRespuestaDetalles neg = (CmGlosaRespuestaDetalles) getEntityManager().find(CmGlosaRespuestaDetalles.class, id);
            obj = castEntidadNegocio(neg);
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public int insertar(CmGlosaRespuestaDetalle obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al insertar la solicitud");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(CmGlosaRespuestaDetalle obj) throws Exception {
        try {
            obj.setId((int) getEntityManager().merge(castNegocioEntidad(obj)).getId());
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CmGlosaRespuestaDetalle eliminar(int id) throws Exception {
        CmGlosaRespuestaDetalle obj = null;
        try {
            CmGlosaRespuestaDetalles neg = getEntityManager().find(CmGlosaRespuestaDetalles.class, id);
            if (neg != null) {
                obj = castEntidadNegocio(neg);
                getEntityManager().remove(neg);
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

    public static CmGlosaRespuestaDetalle castEntidadNegocio(CmGlosaRespuestaDetalles neg) {
        CmGlosaRespuestaDetalle ent = new CmGlosaRespuestaDetalle(); 
        ent.setId(neg.getId());
        if(neg.getCmGlosaRespuestasId() != null){
            ent.setCmGlosaRespuesta(new CmGlosaRespuesta( 
                                         neg.getCmGlosaRespuestasId().getId(), 
                                         neg.getCmGlosaRespuestasId().getTipoRespuesta() 
            ));
        }
        if(neg.getCmDetallesId() != null){
          ent.setCmDetalle(new CmDetalle(neg.getCmDetallesId().getId()));
        }
        ent.setDocumento(neg.getDocumento());
        BigDecimal valorCobroDetalle = neg.getValorCobroDetalle() == null ? 
                                       BigDecimal.ZERO : neg.getValorCobroDetalle();
        ent.setValorCobroDetalle(valorCobroDetalle);
        ent.setValorFacturado(neg.getValorFacturado());
        ent.setValorPagado(neg.getValorPagado());
        ent.setValorPagadoEps(neg.getValorPagadoEps());
        ent.setPorcentajePagadoEps(neg.getPorcentajePagadoEps());
        ent.setValorPendiente(neg.getValorPendiente());
        ent.setValorAceptadoIps(neg.getValorAceptadoIps());
        ent.setPorcentajeAceptadoIps(neg.getPorcentajeAceptadoIps());
        ent.setObservacion(neg.getObservacion());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        return ent;
    }

    public static CmGlosaRespuestaDetalles castNegocioEntidad(CmGlosaRespuestaDetalle ent) {
        CmGlosaRespuestaDetalles neg = new CmGlosaRespuestaDetalles();
        neg.setId(ent.getId());
        if (ent.getCmGlosaRespuesta() != null) {
            neg.setCmGlosaRespuestasId(new CmGlosaRespuestas(ent.getCmGlosaRespuesta().getId()));
        }
        if (ent.getCmDetalle() != null) {
            neg.setCmDetallesId(new CmDetalles(ent.getCmDetalle().getId()));
        } 
        neg.setDocumento(ent.getDocumento());
        BigDecimal valorCobroDetalle = ent.getValorCobroDetalle() == null ? 
                                       BigDecimal.ZERO : ent.getValorCobroDetalle();
        neg.setValorCobroDetalle(valorCobroDetalle);
        neg.setValorFacturado(ent.getValorFacturado());
        neg.setValorPagado(ent.getValorPagado());
        neg.setValorPagadoEps(ent.getValorPagadoEps());
        neg.setPorcentajePagadoEps(ent.getPorcentajePagadoEps());
        neg.setValorPendiente(ent.getValorPendiente());
        neg.setValorAceptadoIps(ent.getValorAceptadoIps());
        neg.setPorcentajeAceptadoIps(ent.getPorcentajeAceptadoIps());
        neg.setObservacion(ent.getObservacion());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        return neg;
    }

}
