/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMotivoGlosa;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaMotivosGlosas;
import com.saviasaludeps.savia.ejb.entidades.CmDetalles;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaMotivosGlosaRemoto;
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
@Remote(CmAuditoriaMotivosGlosaRemoto.class)
@Local(CmAuditoriaMotivosGlosaLocal.class)
public class CmAuditoriaMotivosGlosaServicio extends GenericoServicio implements CmAuditoriaMotivosGlosaLocal, CmAuditoriaMotivosGlosaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        return cant;
    }
    
    @Override
    public List<CmAuditoriaMotivoGlosa> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmAuditoriaMotivoGlosa> listResult = new ArrayList();
        return listResult;
    }
    
    @Override
    public CmAuditoriaMotivoGlosa consultar(int id) throws Exception {
        CmAuditoriaMotivoGlosa obj = null;
        try {
            
            CmAuditoriaMotivosGlosas per = (CmAuditoriaMotivosGlosas) getEntityManager().find(CmAuditoriaMotivosGlosas.class, id);
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
    public int insertar(CmAuditoriaMotivoGlosa obj) throws Exception {
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
    public void actualizar(CmAuditoriaMotivoGlosa obj) throws Exception {
        try {
      
            CmAuditoriaMotivosGlosas  motivoGlosa =  castNegocioEntidad(obj);       
            String hql = "UPDATE CmAuditoriaMotivosGlosas SET"
                    + " maeMotivoId = :maeMotivoId,"
                    + " maeMotivoCodigo = :maeMotivoCodigo,"
                    + " maeMotivoValor = :maeMotivoValor,"
                    + " maeMotivoEspecificoId  = :maeMotivoEspecificoId,"
                    + " maeMotivoEspecificoCodigo = :maeMotivoEspecificoCodigo,"
                    + " maeMotivoEspecificoValor = :maeMotivoEspecificoValor,"
                    + " maeMotivoGlosaAplicacionId = :maeMotivoGlosaAplicacionId,"
                    + " maeMotivoGlosaAplicacionCodigo = :maeMotivoGlosaAplicacionCodigo,"
                    + " maeMotivoGlosaAplicacionValor = :maeMotivoGlosaAplicacionValor,"
                    + " maeMotivoGlosaAplicacionDescripcion = :maeMotivoGlosaAplicacionDescripcion,"     
                    + " cmDetallesId.id = :cmDetallesId,"
                    + " porcentaje = :porcentaje,"
                    + " valorMotivo = :valorMotivo,"
                    + " observacion = :observacion "
                    + " WHERE id = :id";
            
            Query query = getEntityManager().createQuery(hql);
           
            query.setParameter("maeMotivoId", motivoGlosa.getMaeMotivoId());
            query.setParameter("maeMotivoCodigo", motivoGlosa.getMaeMotivoCodigo());
            query.setParameter("maeMotivoValor", motivoGlosa.getMaeMotivoValor());
            query.setParameter("maeMotivoEspecificoId",motivoGlosa.getMaeMotivoEspecificoId());
            query.setParameter("maeMotivoEspecificoCodigo", motivoGlosa.getMaeMotivoEspecificoCodigo());
            query.setParameter("maeMotivoEspecificoValor", motivoGlosa.getMaeMotivoEspecificoValor());     
            query.setParameter("maeMotivoGlosaAplicacionId",motivoGlosa.getMaeMotivoGlosaAplicacionId());
            query.setParameter("maeMotivoGlosaAplicacionCodigo", motivoGlosa.getMaeMotivoGlosaAplicacionCodigo());
            query.setParameter("maeMotivoGlosaAplicacionValor", motivoGlosa.getMaeMotivoGlosaAplicacionValor());
            query.setParameter("maeMotivoGlosaAplicacionDescripcion", motivoGlosa.getMaeMotivoGlosaAplicacionDescripcion());                  
            query.setParameter("cmDetallesId", motivoGlosa.getCmDetallesId().getId());
            query.setParameter("porcentaje", motivoGlosa.getPorcentaje());
            query.setParameter("valorMotivo", motivoGlosa.getValorMotivo());
            query.setParameter("observacion", motivoGlosa.getObservacion());
            query.setParameter("id", motivoGlosa.getId());
            query.executeUpdate();
            
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CmAuditoriaMotivoGlosa eliminar(int id) throws Exception {
        CmAuditoriaMotivoGlosa obj = null;
        try {
           
            CmAuditoriaMotivosGlosas per = getEntityManager().find(CmAuditoriaMotivosGlosas.class, id);
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
    public void eliminarPorCodigoEspecifico(String idsMotivos, int idDetalle) throws Exception {
        try {
            if (idDetalle > 0 && !"".equals(idsMotivos)) {
                Query q = getEntityManager().createQuery("DELETE CmAuditoriaMotivosGlosas WHERE "
                        + " maeMotivoEspecificoId IN( " + idsMotivos + ") AND cmDetallesId.id = " + idDetalle + "");
                q.executeUpdate();
            }
        } catch (NoResultException e) {
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
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
    
    
    @Override
    public int consultarCantidadPorDetalle(ParamConsulta paramConsulta) throws java.lang.Exception {
      int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cmmg) FROM CmAuditoriaMotivosGlosas cmmg ";         
            strQuery += " WHERE cmmg.cmDetallesId.id = :detalleId ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoServicio":
                            strQuery += " AND cmmg.codigoServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreServicio":
                            strQuery += " AND cmmg.nombreServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "documento":
                            strQuery += " AND cmmg.documento LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicadoGlosa":
                            strQuery += " AND cmmg.radicadoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "motivoGlosa":
                            strQuery += " AND cmmg.motivoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            } 
            
            cant = (int) (long)  getEntityManager().createQuery(strQuery).
                          setParameter("detalleId", paramConsulta.getParametroConsulta1()).getSingleResult();                

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
    public List<CmAuditoriaMotivoGlosa> consultarPorMultiDetalles(String idsDetalles) throws java.lang.Exception {
        List<CmAuditoriaMotivoGlosa> listResult = new ArrayList();
        try {
            if (idsDetalles != null) {
                String strQuery = "SELECT distinct cmam.mae_motivo_especifico_id, cmam.mae_motivo_especifico_valor  FROM cm_auditoria_motivos_glosas cmam  "
                        + "WHERE 1 = 1 ";
                strQuery += " and  cmam.cm_detalles_id IN (" + idsDetalles + ")  ";
                List<Object[]> listMotivos = getEntityManager().createNativeQuery(strQuery).getResultList();
                 if (listMotivos != null) {
                    for (Object[] motivo : listMotivos) {
                      CmAuditoriaMotivoGlosa afiliadoIn = new CmAuditoriaMotivoGlosa();
                      afiliadoIn.setMaeMotivoEspecificoId((int) motivo[0]);
                      afiliadoIn.setMaeMotivoEspecificoValor(String.valueOf(motivo[1]));
                      listResult.add(afiliadoIn);
                    }
                }
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
    public List<CmAuditoriaMotivoGlosa> consultarPorMultiFacturas(String idsCmFacturas) throws java.lang.Exception {
        List<CmAuditoriaMotivoGlosa> listResult = new ArrayList();
        try {
            if (idsCmFacturas != null) {
                
                String strQuery = "SELECT distinct cmam.mae_motivo_especifico_id, cmam.mae_motivo_especifico_valor,"
                          + " cmam.mae_motivo_especifico_codigo FROM cm_auditoria_motivos_glosas cmam INNER JOIN cm_detalles cmd ON cmd.id = cmam.cm_detalles_id "
                          + " WHERE cmam.mae_motivo_especifico_id > 1 ";
                strQuery += " AND cmd.cm_facturas_id IN (" + idsCmFacturas + ") AND cmd.valor_pendiente> 0 ";
                List<Object[]> listMotivos = getEntityManager().createNativeQuery(strQuery).getResultList();
                 if (listMotivos != null) {
                    for (Object[] motivo : listMotivos) {
                      int idMotivo =  (int) Optional.ofNullable(motivo[0]).orElse(0);
                      String motivoValor = (String) Optional.ofNullable(motivo[1]).orElse("");
                      String motivoCodigo = (String) Optional.ofNullable(motivo[2]).orElse("");
                      CmAuditoriaMotivoGlosa afiliadoIn = new CmAuditoriaMotivoGlosa();
                      afiliadoIn.setMaeMotivoEspecificoId(idMotivo);
                      afiliadoIn.setMaeMotivoEspecificoValor(motivoValor);
                      afiliadoIn.setMaeMotivoEspecificoCodigo(motivoCodigo);
                      listResult.add(afiliadoIn);
                    }
                }
                
                
                /*
                String strQuery = "FROM CmAuditoriaMotivosGlosas cmmg     "
                        + "WHERE  cmmg.id > 0 and cmmg.cmDetallesId.cmFacturasId.id IN (" + idsCmFacturas + ") ";
                List<CmAuditoriaMotivosGlosas> listMotivos = getEntityManager().createQuery(strQuery).getResultList();
                if (listMotivos != null) {
                    for (CmAuditoriaMotivosGlosas ent : listMotivos) {
                        CmAuditoriaMotivoGlosa obj = castEntidadNegocio(ent);
                        listResult.add(obj);
                    }

                }*/
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
    public List<CmAuditoriaMotivoGlosa> consultarListaPorDetalle(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CmAuditoriaMotivoGlosa> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmAuditoriaMotivosGlosas cmmg";
            strQuery += " WHERE cmmg.cmDetallesId.id = :detalleId";
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoServicio":
                            strQuery += " AND cmmg.codigoServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreServicio":
                            strQuery += " AND cmmg.nombreServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "documento":
                            strQuery += " AND cmmg.documento LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicadoGlosa":
                            strQuery += " AND cmmg.radicadoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "motivoGlosa":
                            strQuery += " AND cmmg.motivoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
              
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {     
                String order = paramConsulta.getOrden().replace("cmGlosas", "cmGlosasId").
                                                        replace("gsZona", "gsZonasId");
                strQuery += " cmmg." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " cmmg.fechaHoraCrea DESC , cmmg.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("detalleId", paramConsulta.getParametroConsulta1());
     
            List<CmAuditoriaMotivosGlosas> list;
            
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
            for (CmAuditoriaMotivosGlosas per : list) {
                CmAuditoriaMotivoGlosa obj = castEntidadNegocio(per);
                obj.setPosInsertar(postInsertar);
                listResult.add(obj);
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
    
    public static CmAuditoriaMotivoGlosa castEntidadNegocio(CmAuditoriaMotivosGlosas neg) {
        CmAuditoriaMotivoGlosa ent = new CmAuditoriaMotivoGlosa();
        ent.setId(neg.getId());
        ent.setCmDetalle(new CmDetalle(neg.getCmDetallesId().getId()));
        
        ent.setMaeMotivoId(neg.getMaeMotivoId());
        ent.setMaeMotivoCodigo(neg.getMaeMotivoCodigo());
        ent.setMaeMotivoValor(neg.getMaeMotivoValor());
        Maestro motivo = new Maestro();
            motivo.setId(neg.getMaeMotivoId());
            motivo.setValor(neg.getMaeMotivoCodigo());
            motivo.setNombre(neg.getMaeMotivoValor());
        ent.setMaestroMotivo(motivo);
        
        ent.setMaeMotivoEspecificoId(neg.getMaeMotivoEspecificoId());
        ent.setMaeMotivoEspecificoValor(neg.getMaeMotivoEspecificoValor());
        ent.setMaeMotivoEspecificoCodigo(neg.getMaeMotivoEspecificoCodigo());
        Maestro motivoEspecifico = new Maestro();
            motivoEspecifico.setId(neg.getMaeMotivoEspecificoId());
            motivoEspecifico.setValor(neg.getMaeMotivoEspecificoCodigo());
            motivoEspecifico.setNombre(neg.getMaeMotivoEspecificoValor());
        ent.setMaestroMotivoEspecifico(motivoEspecifico);
        
        ent.setMaeMotivoGlosaAplicacionId(neg.getMaeMotivoGlosaAplicacionId());
        ent.setMaeMotivoGlosaAplicacionValor(neg.getMaeMotivoGlosaAplicacionValor());
        ent.setMaeMotivoGlosaAplicacionCodigo(neg.getMaeMotivoGlosaAplicacionCodigo());
        ent.setMaeMotivoGlosaAplicacionDescripcion(neg.getMaeMotivoGlosaAplicacionDescripcion());
        Maestro motivoAplicacion = new Maestro();
            motivoAplicacion.setId(neg.getMaeMotivoGlosaAplicacionId());
            motivoAplicacion.setValor(neg.getMaeMotivoGlosaAplicacionCodigo());
            motivoAplicacion.setNombre(neg.getMaeMotivoGlosaAplicacionValor());
            motivoAplicacion.setDescripcion(neg.getMaeMotivoGlosaAplicacionDescripcion());
        ent.setMaestroMotivoAplicacion(motivoAplicacion);
              
        ent.setPorcentaje(neg.getPorcentaje());
        ent.setValorMotivo(neg.getValorMotivo());
        
        String observacion = neg.getObservacion() != null ?
                             neg.getObservacion().replaceAll("\\p{C}", "") : "";
        ent.setObservacion(observacion);
        ent.setTipologia(neg.getTipologia());     
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        return ent;
    }
    
    public static CmAuditoriaMotivosGlosas castNegocioEntidad(CmAuditoriaMotivoGlosa ent) {
      CmAuditoriaMotivosGlosas neg = new CmAuditoriaMotivosGlosas();
      neg.setId(ent.getId());
      neg.setCmDetallesId(new CmDetalles(ent.getCmDetalle().getId()));
      neg.setMaeMotivoId(ent.getMaeMotivoId());
      neg.setMaeMotivoCodigo(ent.getMaeMotivoCodigo());
      neg.setMaeMotivoValor(ent.getMaeMotivoValor());
      neg.setMaeMotivoEspecificoId(ent.getMaeMotivoEspecificoId());
      neg.setMaeMotivoEspecificoCodigo(ent.getMaeMotivoEspecificoCodigo());
      neg.setMaeMotivoEspecificoValor(ent.getMaeMotivoEspecificoValor());
      neg.setMaeMotivoGlosaAplicacionId(ent.getMaeMotivoGlosaAplicacionId());
      neg.setMaeMotivoGlosaAplicacionValor(ent.getMaeMotivoGlosaAplicacionValor());
      neg.setMaeMotivoGlosaAplicacionCodigo(ent.getMaeMotivoGlosaAplicacionCodigo());
      neg.setMaeMotivoGlosaAplicacionDescripcion(ent.getMaeMotivoGlosaAplicacionDescripcion());
      neg.setPorcentaje(ent.getPorcentaje());
      neg.setValorMotivo(ent.getValorMotivo());
      String observacion = ent.getObservacion() != null ?
                           ent.getObservacion().replaceAll("\\p{C}", "") : "";
      neg.setObservacion(observacion);
      neg.setTipologia(ent.getTipologia());
      neg.setUsuarioCrea(ent.getUsuarioCrea());
      neg.setFechaHoraCrea(ent.getFechaHoraCrea());
      neg.setTerminalCrea(ent.getTerminalCrea());
      return neg;
    }
  }
