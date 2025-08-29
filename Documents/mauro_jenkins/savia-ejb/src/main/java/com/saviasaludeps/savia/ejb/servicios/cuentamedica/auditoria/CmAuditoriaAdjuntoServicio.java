/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria;


import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAdjunto;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaAdjuntos;
import com.saviasaludeps.savia.ejb.entidades.CmDetalles;
import com.saviasaludeps.savia.ejb.entidades.CmFacturas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaAdjuntoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jperezn
 */
@Stateless
@Remote(CmAuditoriaAdjuntoRemoto.class)
@Local(CmAuditoriaAdjuntoLocal.class)
public class CmAuditoriaAdjuntoServicio extends GenericoServicio implements CmAuditoriaAdjuntoLocal, CmAuditoriaAdjuntoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        return cant;
    }
    
    @Override
    public List<CmAuditoriaAdjunto> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmAuditoriaAdjunto> listResult = new ArrayList();
        return listResult;
    }
    
    @Override
    public CmAuditoriaAdjunto consultar(int id) throws Exception {
        CmAuditoriaAdjunto obj = null;
        try {
            CmAuditoriaAdjuntos per = (CmAuditoriaAdjuntos) getEntityManager().find(CmAuditoriaAdjuntos.class, id);
            if(per != null){
                obj = castEntidadNegocio(per);
            }
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
    public int insertar(CmAuditoriaAdjunto obj) throws Exception {
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
    public void actualizar(CmAuditoriaAdjunto obj) throws Exception {
        try {
 
            CmAuditoriaAdjuntos adjuntos =  castNegocioEntidad(obj);       
            String hql = "UPDATE CmAuditoriaAdjuntos SET"
                    + " archivoTipo = :archivoTipo,"
                    + " archivoNombre = :archivoNombre,"
                    + " archivoRuta = :archivoRuta,"
                    + " cmFacturasId.id  = :cmFacturasId"
                    + " WHERE id = :id";
            
            Query query = getEntityManager().createQuery(hql);
      
            query.setParameter("archivoTipo", adjuntos.getArchivoTipo());
            query.setParameter("archivoNombre", adjuntos.getArchivoNombre());
            query.setParameter("archivoRuta", adjuntos.getArchivoRuta());
            query.setParameter("cmFacturasId", adjuntos.getCmFacturasId().getId());
            query.setParameter("id", adjuntos.getId());
            query.executeUpdate();
            
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CmAuditoriaAdjunto eliminar(int id) throws Exception {
        CmAuditoriaAdjunto obj = null;
        try {
            CmAuditoriaAdjuntos per = getEntityManager().find(CmAuditoriaAdjuntos.class, id);
            if (per != null) {
                obj = castEntidadNegocio(per);
                getEntityManager().remove(per);
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
    public CmAuditoriaAdjunto eliminarSegunFactura(int idAdjunto, int idFactura) throws Exception {
        CmAuditoriaAdjunto obj = null;
        try {
            String strQuery = "FROM CmAuditoriaAdjuntos cma ";
            strQuery += " WHERE cma.cmFacturasId.id = :cmFacturasId AND cma.id = :cmAdjuntoId";

            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("cmFacturasId", idFactura);
            query.setParameter("cmAdjuntoId", idAdjunto);

            List<CmAuditoriaAdjuntos> adjuntos = query.getResultList();

            if (!adjuntos.isEmpty()) {
                CmAuditoriaAdjuntos per = adjuntos.get(0);
                obj = castEntidadNegocio(per);
                getEntityManager().remove(per);
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
    public int consultarCantidadPorDetalle(ParamConsulta paramConsulta) throws java.lang.Exception {
      int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cma) FROM CmAuditoriaAdjuntos cma ";         
            strQuery += " WHERE cma.id > 0";
            
            if(paramConsulta.getParametroConsulta1() != null){
                strQuery += " AND cma.cmFacturasId.id = :cmFacturasId ";
            }
            if(paramConsulta.getParametroConsulta3() != null){
                strQuery += " AND cma.cmDetallesId.id = :cmDetallesId ";
            }
            if(paramConsulta.getParametroConsulta4() != null){
                strQuery += " AND cma.archivoTipo = :archivoTipo ";
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoServicio":
                            strQuery += " AND cma.codigoServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreServicio":
                            strQuery += " AND cma.nombreServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "documento":
                            strQuery += " AND cma.documento LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicadoGlosa":
                            strQuery += " AND cma.radicadoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "motivoGlosa":
                            strQuery += " AND cma.motivoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            } 
            
            Query query =   getEntityManager().createQuery(strQuery);
            
            if(paramConsulta.getParametroConsulta1() != null){
                query.setParameter("cmFacturasId", paramConsulta.getParametroConsulta1());
            }     
            if(paramConsulta.getParametroConsulta3() != null){
                query.setParameter("cmDetallesId", paramConsulta.getParametroConsulta3());
            }
            if(paramConsulta.getParametroConsulta4() != null){
               query.setParameter("archivoTipo", paramConsulta.getParametroConsulta4());
            }
            
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
    public List<CmAuditoriaAdjunto> consultarListaPorDetalle(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CmAuditoriaAdjunto> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmAuditoriaAdjuntos cma";
            strQuery += " WHERE cma.id > 0";
            
            if(paramConsulta.getParametroConsulta1() != null){
                strQuery += " AND cma.cmFacturasId.id = :cmFacturasId ";
            }
            if(paramConsulta.getParametroConsulta3() != null){
                strQuery += " AND cma.cmDetallesId.id = :cmDetallesId ";
            }
            if(paramConsulta.getParametroConsulta4() != null){
                strQuery += " AND cma.archivoTipo = :archivoTipo ";
            }
                       
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoServicio":
                            strQuery += " AND cma.codigoServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreServicio":
                            strQuery += " AND cma.nombreServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "documento":
                            strQuery += " AND cma.documento LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicadoGlosa":
                            strQuery += " AND cma.radicadoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "motivoGlosa":
                            strQuery += " AND cma.motivoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
              
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {     
                String order = paramConsulta.getOrden().replace("cmGlosas", "cmGlosasId").
                                                        replace("gsZona", "gsZonasId");
                strQuery += " cma." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " cma.fechaHoraCrea DESC , cma.id DESC";
            }
            
            Query query = getEntityManager().createQuery(strQuery);
            if(paramConsulta.getParametroConsulta1() != null){
                query.setParameter("cmFacturasId", paramConsulta.getParametroConsulta1());
            }           
            if(paramConsulta.getParametroConsulta3() != null){
               query.setParameter("cmDetallesId", paramConsulta.getParametroConsulta3());
            }
            if(paramConsulta.getParametroConsulta4() != null){
               query.setParameter("archivoTipo", paramConsulta.getParametroConsulta4());
            }
                       
            List<CmAuditoriaAdjuntos> list;
            
            if (paramConsulta.getParametroConsulta2() != null) {
                list = query
                        .getResultList();
            } else {
                list = query
                        .setFirstResult(paramConsulta.getPrimerRegistro())
                        .setMaxResults(paramConsulta.getRegistrosPagina())
                        .getResultList();
            }
            int postInsertar = 1;
            for (CmAuditoriaAdjuntos per : list) {
                CmAuditoriaAdjunto obj = castEntidadNegocio(per);
                listResult.add(obj);
                obj.setPosInsertar(postInsertar);
                postInsertar++;
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

    public static CmAuditoriaAdjunto castEntidadNegocio(CmAuditoriaAdjuntos neg) {
        CmFacturas cmFactura = Optional.ofNullable(neg.getCmFacturasId()).orElse(new CmFacturas());
        CmDetalles cmDetalle = Optional.ofNullable(neg.getCmDetallesId()).orElse(new CmDetalles());
        CmAuditoriaAdjunto ent = new CmAuditoriaAdjunto();
        ent.setId(neg.getId());
        ent.setCmFactura(new CmFactura(cmFactura.getId()));
        ent.setCmDetalle(new CmDetalle(cmDetalle.getId()));
        ent.setArchivoTipo(neg.getArchivoTipo());
        ent.setArchivoNombre(neg.getArchivoNombre());
        ent.setArchivoRuta(neg.getArchivoRuta());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        return ent;
    }

    public static CmAuditoriaAdjuntos castNegocioEntidad(CmAuditoriaAdjunto ent) {
        CmFactura cmFactura = Optional.ofNullable(ent.getCmFactura()).orElse(new CmFactura());
        CmDetalle cmDetalle = Optional.ofNullable(ent.getCmDetalle()).orElse(new CmDetalle());
        CmAuditoriaAdjuntos neg = new CmAuditoriaAdjuntos();
        neg.setId(ent.getId());
        neg.setCmFacturasId(new CmFacturas(cmFactura.getId()));
        if(cmDetalle.getId() != null){
            neg.setCmDetallesId(new CmDetalles(cmDetalle.getId()));
        }
        neg.setArchivoTipo(ent.getArchivoTipo());
        neg.setArchivoNombre(ent.getArchivoNombre());
        neg.setArchivoRuta(ent.getArchivoRuta());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        return neg;
    }
    
 }
