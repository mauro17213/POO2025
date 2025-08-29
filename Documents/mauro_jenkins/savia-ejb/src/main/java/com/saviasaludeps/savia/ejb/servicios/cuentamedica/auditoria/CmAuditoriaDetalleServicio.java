/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmConciliacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmConciliaciones;
import com.saviasaludeps.savia.ejb.entidades.CmGlosaRespuestas;
import com.saviasaludeps.savia.ejb.entidades.CmRadicados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaDetalleRemoto;
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
 * @author jperezn
 */
@Stateless
@Remote(CmAuditoriaDetalleRemoto.class)
@Local(CmAuditoriaDetalleLocal.class)
public class CmAuditoriaDetalleServicio extends GenericoServicio implements CmAuditoriaDetalleLocal, CmAuditoriaDetalleRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        return cant;
    }
    
    @Override
    public List<CmAuditoriaDetalle> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmAuditoriaDetalle> listResult = new ArrayList();
        return listResult;
    }
    
    @Override
    public int consultarCantidadDetallesPorFactura(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cmd) FROM CmDetalles cmd ";         
            strQuery += " WHERE cmd.cmFacturasId.id = :facuturaId ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoServicio":
                            strQuery += " AND cmd.codigoServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreServicio":
                            strQuery += " AND cmd.nombreServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "documento":
                            strQuery += " AND cmd.documento LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicadoGlosa":
                            strQuery += " AND cmd.radicadoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "motivoGlosa":
                            strQuery += " AND cmd.motivoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            } 
            
            if (paramConsulta.getParametroConsulta3() != null ) {
                strQuery += " AND cmd.valorPendienteActual > 0 ";
            }
            
            cant = (int) (long)  getEntityManager().createQuery(strQuery).
                          setParameter("facuturaId", paramConsulta.getParametroConsulta1()).getSingleResult();                

        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        cant = 1;
        return cant;
    }
    
    @Override
    public List<CmAuditoriaDetalle> consultarListaDetallesPorFactura(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CmAuditoriaDetalle> listResult = new ArrayList();
       
        try { 
            
            CmAuditoriaDetalle detalle = new CmAuditoriaDetalle();
            detalle.setId(1);
            detalle.setAplicaDc(true);
            detalle.setAplicaDc(false);
            detalle.setAplicaPbs(true);
            listResult.add(detalle);
            
            /*
            String strQuery = "FROM CmDetalles cmd ";
            strQuery += " WHERE cmd.cmFacturasId.id = :facuturaId";
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoServicio":
                            strQuery += " AND cmd.codigoServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreServicio":
                            strQuery += " AND cmd.nombreServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "documento":
                            strQuery += " AND cmd.documento LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicadoGlosa":
                            strQuery += " AND cmd.radicadoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "motivoGlosa":
                            strQuery += " AND cmd.motivoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            
            if (paramConsulta.getParametroConsulta3() != null ) {
                strQuery += " AND cmd.valorPendienteActual > 0 ";
            }
            
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                
                String order = paramConsulta.getOrden().replace("cmGlosas", "cmGlosasId").
                                                        replace("gsZona", "gsZonasId");
                strQuery += " cmd." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " cmd.fechaHoraCrea DESC , cmd.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
         
            query.setParameter("facuturaId", paramConsulta.getParametroConsulta1());
           
            List<CmDetalles> list;

            if (paramConsulta.getParametroConsulta2() != null) {
                list = query
                        .getResultList();
            } else {
                list = query
                        .setFirstResult(paramConsulta.getPrimerRegistro())
                        .setMaxResults(paramConsulta.getRegistrosPagina())
                        .getResultList();
            }
            
            for (CmDetalles per : list) {
                listResult.add(castEntidadNegocio(per));
            }*/
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
    public CmAuditoriaDetalle consultar(int id) throws Exception {
        CmAuditoriaDetalle obj = null;
        try {
            obj = new CmAuditoriaDetalle();
            obj.setId(1);
            obj.setDocumento("12123");
            obj.setNombreCompletoAfiliado("Nombre completo afiliado");
            /*
            CmRadicados per = (CmRadicados) getEntityManager().find(CmRadicados.class, id);
            obj = castEntidadNegocio(per);*/
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
    public int insertar(CmRadicado obj) throws Exception {
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
    public void actualizar(CmRadicado obj) throws Exception {
        try {
            //obj.setId((int) getEntityManager().merge(castNegocioEntidad(obj)).getId()); 
            CmRadicados  cmRadicados =  castNegocioEntidad(obj);       
            String hql = "UPDATE CmRadicados SET"
                    + " radicado = :radicado,"
                    + " estadoRadicado = :estadoRadicado,"
                    + " usuarioCrea = :usuarioCrea,"
                    + " terminalCrea  = :terminalCrea,"
                    + " fechaHoraCrea = :fechaHoraCrea,"
                    + " cmConciliacionesId.id = :cmConciliacionesId,"
                    + " cmGlosaRespuestasId.id = :cmGlosaRespuestasId"
                    + " WHERE id = :id";
            
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("radicado", cmRadicados.getRadicado());
            query.setParameter("estadoRadicado", cmRadicados.getEstadoRadicado());
            query.setParameter("usuarioCrea", cmRadicados.getUsuarioCrea());
            query.setParameter("terminalCrea", cmRadicados.getTerminalCrea());
            query.setParameter("fechaHoraCrea", cmRadicados.getFechaHoraCrea());
            
            Integer idConciliacionMasiva = cmRadicados.getCmConciliacionesId() != null && 
                                    cmRadicados.getCmConciliacionesId().getId() != null ?  
                                    cmRadicados.getCmConciliacionesId().getId() : null;
               
            Integer idGlosaRespuesta = cmRadicados.getCmGlosaRespuestasId() != null && 
                                     cmRadicados.getCmGlosaRespuestasId().getId() != null ?  
                                     cmRadicados.getCmGlosaRespuestasId().getId() : null;
            
            query.setParameter("cmConciliacionesId", idConciliacionMasiva);
            query.setParameter("cmGlosaRespuestasId", idGlosaRespuesta);
            query.setParameter("id", cmRadicados.getId());
            query.executeUpdate();
            
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CmRadicado eliminar(int id) throws Exception {
        CmRadicado obj = null;
        try {
            CmRadicados per = getEntityManager().find(CmRadicados.class, id);
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
    public int consultarCantidadRadicado(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT COUNT(cr) FROM CmRadicados cr WHERE cr.id > 0 AND cr.estadoRadicado = '0'");
            if (paramConsulta.getFiltros() != null && !paramConsulta.getFiltros().isEmpty()) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND cr.id = ");
                            strQuery.append(e.getValue());
                            strQuery.append(" ");
                            break;
                        case "radicado":
                            strQuery.append("AND cr.radicado = ");
                            strQuery.append(e.getValue());
                            strQuery.append(" ");
                            break;
                        case "usuario":
                            strQuery.append("AND cr.usuarioCrea LIKE '%");
                            strQuery.append(e.getValue());
                            strQuery.append("%' ");
                            break;
                        case "idConciliacionMasiva":
                            strQuery.append("AND cr.cmConciliacionesId.id  = ");
                            strQuery.append(e.getValue());
                            strQuery.append(" ");
                            break;
                        case "idGlosaRespuesta":
                            strQuery.append("AND cr.cmGlosaRespuestasId.id = ");
                            strQuery.append(e.getValue());
                            strQuery.append(" ");
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery.toString()).getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (NumberFormatException e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }
    

    public static CmRadicados castNegocioEntidad(CmRadicado obj) {
        CmRadicados per = new CmRadicados();
        per.setId(obj.getId());
        per.setEstadoRadicado(obj.getEstado_radicado());
        per.setRadicado(obj.getRadicado());
        if(obj.getCmConciliacion()!= null){
            per.setCmConciliacionesId(new CmConciliaciones(obj.getCmConciliacion().getId()));
        }
        if(obj.getCmGlosaRespuesta() != null){
           per.setCmGlosaRespuestasId(new CmGlosaRespuestas(obj.getCmGlosaRespuesta().getId()));
        }
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        return per;
    }

    private CmRadicado castEntidadNegocio(CmRadicados obj) {
        CmRadicado per = new CmRadicado();
        per.setId(obj.getId());
        per.setEstado_radicado(obj.getEstadoRadicado());
        per.setRadicado(obj.getRadicado());
        if(obj.getCmConciliacionesId() != null){
             per.setCmConciliacion(new CmConciliacion(obj.getCmConciliacionesId().getId()));
        }
       
        if(obj.getCmGlosaRespuestasId() != null){
            per.setCmGlosaRespuesta(new CmGlosaRespuesta(obj.getCmGlosaRespuestasId().getId()));
        }
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        return per;
    }
    
    }
