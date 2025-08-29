/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Item;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Item;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAutorizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4Items;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos4;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaAutorizaciones;
import com.saviasaludeps.savia.ejb.entidades.CmDetalles;
import com.saviasaludeps.savia.ejb.entidades.CmFacturas;
import com.saviasaludeps.savia.ejb.entidades.CmRadicados;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaAutorizacionRemoto;
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
@Remote(CmAuditoriaAutorizacionRemoto.class)
@Local(CmAuditoriaAutorizacionLocal.class)
public class CmAuditoriaAutorizacionServicio extends GenericoServicio implements CmAuditoriaAutorizacionLocal, CmAuditoriaAutorizacionRemoto {
    
 
    public static final int ESTADO_ANEXO4_AUTORIZADO = 0;
    public static final int ESTADO_ANEXO4__AUTORIZADO_AUTOMATICO = 1;
    public static final int ESTADO_ANEXO4__AUTORIZADO_PREAUTORIZACION = 4;
    public static final int ESTADO_ANEXO4_AUTORIZADO_PAGO_ANTICIPADO = 6;

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
       int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cma) FROM CmAuditoriaAutorizaciones cma ";         
            strQuery += " WHERE cma.id > 0 ";

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
                        case "auAnexos4Id":
                            strQuery += " AND cma.auAnexos4Id.id = " + e.getValue() + " ";
                            break;
                            
                    }
                }
            } 
            
            cant = (int) (long)  getEntityManager().createQuery(strQuery).getSingleResult();                

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
    public List<CmAuditoriaAutorizacion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmAuditoriaAutorizacion> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmAuditoriaAutorizaciones cma";
            strQuery += " WHERE cma.id > 0 ";
            
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
                        case "auAnexos4Id":
                            strQuery += " AND cma.auAnexos4Id.id = " + e.getValue() + " ";
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
                query.setParameter("detalleId", paramConsulta.getParametroConsulta1());
            }
                      
            List<CmAuditoriaAutorizaciones> list;
            
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
            for (CmAuditoriaAutorizaciones per : list) {
                CmAuditoriaAutorizacion obj = castEntidadNegocio(per);
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
    
    @Override
    public CmAuditoriaAutorizacion consultar(int id) throws Exception {
        CmAuditoriaAutorizacion obj = null;
        try {
            CmAuditoriaAutorizaciones per = (CmAuditoriaAutorizaciones) getEntityManager().find(CmAuditoriaAutorizaciones.class, id);
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
    public int insertar(CmAuditoriaAutorizacion obj) throws Exception {
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
    public void actualizar(CmAuditoriaAutorizacion obj) throws Exception {
        try {
  
            CmAuditoriaAutorizaciones  autorizacion =  castNegocioEntidad(obj);       
            String hql = "UPDATE CmAuditoriaAutorizaciones SET"
                    + " numeroAutorizacion = :numeroAutorizacion,"
                    + " codigoServicio = :codigoServicio,"
                    + " nombreServicio = :nombreServicio,"
                    + " cantidad  = :cantidad,"
                    + " valorAutorizacion = :valorAutorizacion,"
                    + " fechaAutorizacion = :fechaAutorizacion,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica,"
                    + " auAnexos4Id.id = :auAnexos4Id,"
                    + " cmDetallesId.id = :cmDetallesId"
                    + " WHERE id = :id";
            
            Query query = getEntityManager().createQuery(hql);
            
            query.setParameter("numeroAutorizacion", autorizacion.getNumeroAutorizacion());
            query.setParameter("codigoServicio", autorizacion.getCodigoServicio());
            query.setParameter("nombreServicio", autorizacion.getNombreServicio());
            query.setParameter("cantidad", autorizacion.getCantidad());
            query.setParameter("valorAutorizacion", autorizacion.getValorAutorizacion());
            query.setParameter("fechaAutorizacion",autorizacion.getFechaAutorizacion());
            query.setParameter("usuarioModifica", autorizacion.getUsuarioModifica());
            query.setParameter("terminalModifica", autorizacion.getTerminalModifica());
            query.setParameter("fechaHoraModifica", autorizacion.getFechaHoraModifica());
            query.setParameter("auAnexos4Id.id", autorizacion.getAuAnexos4Id().getId());
            query.setParameter("cmDetallesId", autorizacion.getCmDetallesId().getId());
            query.setParameter("id", autorizacion.getId());
            query.executeUpdate();
            
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CmAuditoriaAutorizacion eliminar(int id) throws Exception {
        CmAuditoriaAutorizacion obj = null;
        try {
            CmAuditoriaAutorizaciones per = getEntityManager().find(CmAuditoriaAutorizaciones.class, id);
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
    public int consultarCantidadPorFactura(ParamConsulta paramConsulta) throws java.lang.Exception {
      int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cma) FROM CmAuditoriaAutorizaciones cma ";         
            strQuery += " WHERE cma.cmFacturasId.id = :cmFacturasId ";

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
            
            cant = (int) (long)  getEntityManager().createQuery(strQuery).
                          setParameter("cmFacturasId", paramConsulta.getParametroConsulta1()).getSingleResult();                

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
    public int consultarCantidadPorDetalle(ParamConsulta paramConsulta) throws java.lang.Exception {
      int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cma) FROM CmAuditoriaAutorizaciones cma ";         
            strQuery += " WHERE cma.cmDetallesId.id = :cmDetallesId ";

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
            
            cant = (int) (long)  getEntityManager().createQuery(strQuery).
                          setParameter("cmDetallesId", paramConsulta.getParametroConsulta1()).getSingleResult();                

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
    public List<CmAuditoriaAutorizacion> consultarListaPorFactura(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CmAuditoriaAutorizacion> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmAuditoriaAutorizaciones cma";
            strQuery += " WHERE cma.cmFacturasId.id = :cmFacturasId";
            
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
            query.setParameter("cmFacturasId", paramConsulta.getParametroConsulta1());
       
            
            List<CmAuditoriaAutorizaciones> list;
            
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
            for (CmAuditoriaAutorizaciones per : list) {
                CmAuditoriaAutorizacion obj = castEntidadNegocio(per);
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
    
    @Override
    public List<CmAuditoriaAutorizacion> consultarListaPorDetalle(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CmAuditoriaAutorizacion> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmAuditoriaAutorizaciones cma";
            strQuery += " WHERE cma.cmDetallesId.id = :cmDetallesId";
            
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
            query.setParameter("cmDetallesId", paramConsulta.getParametroConsulta1());
       
            
            List<CmAuditoriaAutorizaciones> list;
            
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
            for (CmAuditoriaAutorizaciones per : list) {
                CmAuditoriaAutorizacion obj = castEntidadNegocio(per);
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

     @Override
    public int consultarCantidadAnexo4PorDocumento(ParamConsulta paramConsulta) throws java.lang.Exception {
       int cant = 0;
       String SIN_PAGO_ANTICIPADO = "0";
        try {
            String strQuery = "SELECT COUNT(cma) FROM AuAnexos4 cma ";         
            strQuery += " WHERE cma.asegAfiliadosId.numeroDocumento = :numeroDocumento ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "numeroAutorizacion":
                            strQuery += " AND cma.numeroAutorizacion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeRegimenValor":
                            strQuery += " AND cma.maeRegimenValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maEspecialidadValor":
                            strQuery += " AND cma.maEspecialidadValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeAmbitoAtencionValor":
                            strQuery += " AND cma.maeAmbitoAtencionValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "id":
                            strQuery += " AND cma.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maServicioHabilitadoValor":
                            strQuery += " AND cma.maServicioHabilitadoValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntPrestadorSedeId.cntPrestador.razonSocial":
                            strQuery += " AND cma.cntPrestadorSedesId.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;    
                         case "cntPrestadorSedeId.cntPrestador.numeroDocumento":
                            strQuery += " AND cma.cntPrestadorSedesId.cntPrestadoresId.numeroDocumento LIKE '%" + e.getValue() + "%' ";
                            break;  
                        case "cntPrestadoresId.id":
                            strQuery += " AND cma.cntPrestadorSedesId.cntPrestadoresId.id = " + e.getValue() + " ";
                            break;
                        case "pagoAnticipado":                         
                            String subConsulta = " AND cma.pagoAnticipado = " + (String) e.getValue() + " ";
                            if(SIN_PAGO_ANTICIPADO.equals((String) e.getValue())){
                                subConsulta = " AND ( cma.pagoAnticipado = " + (String) e.getValue() + " OR cma.pagoAnticipado IS NULL )";
                            }
                            strQuery += subConsulta ;
                            break;
                    }
                }
            } 
            
            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += " AND cma.estado IN( "+ESTADO_ANEXO4_AUTORIZADO + "," + ESTADO_ANEXO4__AUTORIZADO_AUTOMATICO +
                        "," + ESTADO_ANEXO4__AUTORIZADO_PREAUTORIZACION + ","+ ESTADO_ANEXO4_AUTORIZADO_PAGO_ANTICIPADO +  ") ";
            }
            
            cant = (int) (long)  getEntityManager().createQuery(strQuery).
                          setParameter("numeroDocumento", paramConsulta.getParametroConsulta1()).getSingleResult();                

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
    public List<AuAnexo4> consultarListaAnexo4PorDocumento(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<AuAnexo4> listResult = new ArrayList();
        String SIN_PAGO_ANTICIPADO = "0";
        try {
            String strQuery = "FROM AuAnexos4 cma";
            strQuery += " WHERE cma.asegAfiliadosId.numeroDocumento = :numeroDocumento";
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "numeroAutorizacion":
                            strQuery += " AND cma.numeroAutorizacion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeRegimenValor":
                            strQuery += " AND cma.maeRegimenValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maEspecialidadValor":
                            strQuery += " AND cma.maEspecialidadValor LIKE '%" + e.getValue() + "%' ";
                            break;
                         case "id":
                            strQuery += " AND cma.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeAmbitoAtencionValor":
                            strQuery += " AND cma.maeAmbitoAtencionValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maServicioHabilitadoValor":
                            strQuery += " AND cma.maServicioHabilitadoValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntPrestadorSedeId.cntPrestador.razonSocial":
                            strQuery += " AND cma.cntPrestadorSedesId.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;    
                         case "cntPrestadorSedeId.cntPrestador.numeroDocumento":
                            strQuery += " AND cma.cntPrestadorSedesId.cntPrestadoresId.numeroDocumento LIKE '%" + e.getValue() + "%' ";
                            break;   
                         case "cntPrestadoresId.id":
                            strQuery += " AND cma.cntPrestadorSedesId.cntPrestadoresId.id = " + e.getValue() + " ";
                            break;  
                        case "pagoAnticipado":
                            String subConsulta = " AND cma.pagoAnticipado = " + (String) e.getValue() + " ";
                            if(SIN_PAGO_ANTICIPADO.equals((String) e.getValue())){
                                subConsulta = " AND ( cma.pagoAnticipado = " + (String) e.getValue() + " OR cma.pagoAnticipado IS NULL )";
                            }
                            strQuery += subConsulta ;
                            break;   
                    }
                }
            }
            
            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += " AND cma.estado IN( "+ESTADO_ANEXO4_AUTORIZADO + "," + ESTADO_ANEXO4__AUTORIZADO_AUTOMATICO + 
                            "," + ESTADO_ANEXO4__AUTORIZADO_PREAUTORIZACION + "," + ESTADO_ANEXO4_AUTORIZADO_PAGO_ANTICIPADO + ") ";
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
            query.setParameter("numeroDocumento", paramConsulta.getParametroConsulta1());
       
            
            List<AuAnexos4> list;
            
            if (paramConsulta.getParametroConsulta2() != null) {
                list = query
                        .getResultList();
            } else {
                list = query
                        .setFirstResult(paramConsulta.getPrimerRegistro())
                        .setMaxResults(paramConsulta.getRegistrosPagina())
                        .getResultList();
            }
            for (AuAnexos4 per : list) {
                AuAnexo4 obj = castEntidadNegocio(per);
                listResult.add(obj);
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
    public int consultarCantidadAnexo4ItemsPorAtributos(ParamConsulta paramConsulta) throws java.lang.Exception {
      int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cma) FROM AuAnexo4Items cma ";         
            strQuery += " WHERE cma.id > 0 ";

            
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND  cma.auAnexos4Id.asegAfiliadosId.numeroDocumento = " + paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += " AND cma.auAnexos4Id.id = " + paramConsulta.getParametroConsulta2() + " ";
            }

            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND cma.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "auAnexo4Id.id":
                            strQuery += " AND cma.auAnexos4Id.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "auAnexo4Id.numeroAutorizacion":
                            strQuery += " AND cma.auAnexos4Id.numeroAutorizacion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeRegimenValor":
                            strQuery += " AND cma.maeRegimenValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeEspecialidadValor":
                            strQuery += " AND cma.maEspecialidadValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeAmbitoAtencionValor":
                            strQuery += " AND cma.maeAmbitoAtencionValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeServicioHabilitadoValor":
                            strQuery += " AND cma.maServicioHabilitadoValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "auAnexo4Id.cntPrestadorSedeId.nombreSede":
                            strQuery += " AND cma.auAnexos4Id.cntPrestadorSedesId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maTecnologiaCodigo":
                            strQuery += " AND cma.maTecnologiaCodigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maTecnologiaValor":
                            strQuery += " AND cma.maTecnologiaValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cantidadAutorizada":
                            strQuery += " AND cma.cantidadAutorizada LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "costoServicio":
                            strQuery += " AND cma.costoServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            } 
            
            cant = (int) (long)  getEntityManager().createQuery(strQuery).getSingleResult();                

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
    public List<AuAnexo4Item> consultarListaAnexo4ItemsPorAtributos(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<AuAnexo4Item> listResult = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo4Items cma";
            strQuery += " WHERE cma.id > 0 ";
            
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND  cma.auAnexos4Id.asegAfiliadosId.numeroDocumento = " + paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += " AND cma.auAnexos4Id.id = " + paramConsulta.getParametroConsulta2() + " ";
            }
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND cma.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "auAnexo4Id.id":
                            strQuery += " AND cma.auAnexos4Id.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "auAnexo4Id.numeroAutorizacion":
                            strQuery += " AND cma.auAnexos4Id.numeroAutorizacion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeRegimenValor":
                            strQuery += " AND cma.maeRegimenValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeEspecialidadValor":
                            strQuery += " AND cma.maEspecialidadValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeAmbitoAtencionValor":
                            strQuery += " AND cma.maeAmbitoAtencionValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeServicioHabilitadoValor":
                            strQuery += " AND cma.maServicioHabilitadoValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "auAnexo4Id.cntPrestadorSedeId.nombreSede":
                            strQuery += " AND cma.auAnexos4Id.cntPrestadorSedesId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maTecnologiaCodigo":
                            strQuery += " AND cma.maTecnologiaCodigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maTecnologiaValor":
                            strQuery += " AND cma.maTecnologiaValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cantidadAutorizada":
                            strQuery += " AND cma.cantidadAutorizada LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "costoServicio":
                            strQuery += " AND cma.costoServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
              
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {     
                String order = paramConsulta.getOrden().replace("auAnexo4Id", "auAnexos4Id").
                                                        replace("gsZona", "gsZonasId");
                strQuery += " cma." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " cma.fechaHoraCrea DESC , cma.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
       
            
            List<AuAnexo4Items> list;
            
            if (paramConsulta.getParametroConsulta3() != null) {
                list = query
                        .getResultList();
            } else {
                list = query
                        .setFirstResult(paramConsulta.getPrimerRegistro())
                        .setMaxResults(paramConsulta.getRegistrosPagina())
                        .getResultList();
            }

            for (AuAnexo4Items per : list) {
                AuAnexo4Item obj = castEntidadNegocio(per);
                listResult.add(obj);
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


    public static AuAnexo4 castEntidadNegocio(AuAnexos4 neg) {
        AuAnexo4 ent = new AuAnexo4();
        ent.setId(neg.getId());
        ent.setNumeroAutorizacion(neg.getNumeroAutorizacion());;
        ent.setFechaAutorizacion(neg.getFechaAutorizacion());
        ent.setMaeRegimenId(neg.getMaeRegimenId());
        ent.setMaeRegimenCodigo(neg.getMaeRegimenCodigo());
        ent.setMaeRegimenValor(neg.getMaeRegimenValor());
        ent.setMaEspecialidadId(neg.getMaEspecialidadId());
        ent.setMaEspecialidadCodigo(neg.getMaEspecialidadCodigo());
        ent.setMaEspecialidadValor(neg.getMaEspecialidadValor());
        ent.setMaeAmbitoAtencionId(neg.getMaeAmbitoAtencionId());
        ent.setMaeAmbitoAtencionCodigo(neg.getMaeAmbitoAtencionCodigo());
        ent.setMaeAmbitoAtencionValor(neg.getMaeAmbitoAtencionValor());
        ent.setMaServicioHabilitadoId(neg.getMaServicioHabilitadoId());
        ent.setMaServicioHabilitadoCodigo(neg.getMaServicioHabilitadoCodigo());
        ent.setMaServicioHabilitadoValor(neg.getMaServicioHabilitadoValor());
        if (neg.getCntPrestadorSedesId() != null
                && neg.getCntPrestadorSedesId().getCntPrestadoresId() != null) {
            CntPrestadores prestadorEntidad = neg.getCntPrestadorSedesId().getCntPrestadoresId();
            CntPrestador cntPrestadorNegocio = new CntPrestador(prestadorEntidad.getId());
            cntPrestadorNegocio.setId(prestadorEntidad.getId());
            cntPrestadorNegocio.setRazonSocial(prestadorEntidad.getRazonSocial());
            cntPrestadorNegocio.setNumeroDocumento(prestadorEntidad.getNumeroDocumento());
            ent.setCntPrestadorSedeId(new CntPrestadorSede(neg.getCntPrestadorSedesId().getId(), prestadorEntidad.getRazonSocial(), cntPrestadorNegocio));
        }
        ent.setPagoAnticipado(Optional.ofNullable(neg.getPagoAnticipado()).orElse(false));
        ent.setUsuarioModifica(neg.getUsuarioModifica());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setTerminalModifica(neg.getTerminalModifica());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setFechaHoraModifica(neg.getFechaHoraModifica());
        return ent;
    }

    public static AuAnexos4 castNegocioEntidad(AuAnexo4 ent) {
        AuAnexos4 neg = new AuAnexos4();
        neg.setId(ent.getId());
        neg.setNumeroAutorizacion(ent.getNumeroAutorizacion());
        neg.setFechaAutorizacion(ent.getFechaAutorizacion());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setUsuarioModifica(ent.getUsuarioModifica());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setTerminalModifica(ent.getTerminalModifica());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        neg.setFechaHoraModifica(ent.getFechaHoraModifica());
        return neg;
    }
    
    public static CmAuditoriaAutorizacion castEntidadNegocio(CmAuditoriaAutorizaciones neg) {
        CmAuditoriaAutorizacion ent = new CmAuditoriaAutorizacion();
        ent.setId(neg.getId());
        AuAnexo4 auanexo4= new AuAnexo4();
        auanexo4.setId( neg.getAuAnexos4Id().getId());
        ent.setAnexo4( auanexo4 );
        auanexo4.setPagoAnticipado(Optional.ofNullable(neg.getAuAnexos4Id().getPagoAnticipado()).orElse(false));
        ent.setPagoAnticipado(auanexo4.isPagoAnticipado());
                
        Integer idFactura = neg.getCmFacturasId() != null && 
                            neg.getCmFacturasId().getId( ) != null ? neg.getCmFacturasId().getId( ) : null;
        if(idFactura!=null){
            CmFactura factura = new CmFactura(idFactura);
            factura.setNumeroFacturado(neg.getCmFacturasId().getNumeroFacturado());
            ent.setCmFactura(factura);
        }
        
        Integer idDetalle = neg.getCmDetallesId() != null && 
                            neg.getCmDetallesId().getId( ) != null ? neg.getCmDetallesId().getId( ) : null;
        if(idDetalle!=null){
            ent.setCmDetalle(new CmDetalle(idDetalle));
        }
        
        if (neg.getAuAnexos4Id().getCntPrestadorSedesId() != null
                && neg.getAuAnexos4Id().getCntPrestadorSedesId().getCntPrestadoresId() != null) {
            CntPrestadores prestador = neg.getAuAnexos4Id().getCntPrestadorSedesId().getCntPrestadoresId();
            ent.setRazonSocialPrestador(prestador.getRazonSocial());
            ent.setNitPrestador(prestador.getNumeroDocumento());
        }
        
        ent.setActiva(neg.getActiva());
        ent.setNumeroAutorizacion(neg.getNumeroAutorizacion());
        ent.setNombreServicio(neg.getNombreServicio());
        ent.setCodigoServicio(neg.getCodigoServicio());
        ent.setCantidad(neg.getCantidad());
        ent.setValorAutorizacion(neg.getValorAutorizacion());
        ent.setFechaAutorizacion(neg.getFechaAutorizacion());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setUsuarioModifica(neg.getUsuarioModifica());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setTerminalModifica(neg.getTerminalModifica());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setFechaHoraModifica(neg.getFechaHoraModifica());
        return ent;
    }

    public static CmAuditoriaAutorizaciones castNegocioEntidad(CmAuditoriaAutorizacion ent) {
        CmAuditoriaAutorizaciones neg = new CmAuditoriaAutorizaciones();
        neg.setId(ent.getId());
        neg.setAuAnexos4Id(new AuAnexos4(ent.getAnexo4().getId()));
        Integer idFactura = ent.getCmFactura() != null && 
                            ent.getCmFactura().getId( ) != null ? ent.getCmFactura().getId( ) : null;
        if(idFactura != null){
              neg.setCmFacturasId(new CmFacturas(idFactura));
        }
        Integer idDetalle = ent.getCmDetalle() != null && 
                            ent.getCmDetalle().getId( ) != null ? ent.getCmDetalle().getId( ) : null;
        if(idDetalle != null){
             neg.setCmDetallesId(new CmDetalles(idDetalle));
        } 
        neg.setActiva(ent.isActiva());
        neg.setNumeroAutorizacion(ent.getNumeroAutorizacion());
        neg.setNombreServicio(ent.getNombreServicio());
        neg.setCodigoServicio(ent.getCodigoServicio());
        neg.setCantidad(ent.getCantidad());
        neg.setValorAutorizacion(ent.getValorAutorizacion());
        neg.setFechaAutorizacion(ent.getFechaAutorizacion());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setUsuarioModifica(ent.getUsuarioModifica());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setTerminalModifica(ent.getTerminalModifica());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        neg.setFechaHoraModifica(ent.getFechaHoraModifica());
        return neg;
    }
    
    public static AuAnexo4Item castEntidadNegocio(AuAnexo4Items ent) {
        AuAnexo4Item neg = new AuAnexo4Item();
        if (ent.getAuAnexos4Id() != null) {
            AuAnexo4 au4 = new AuAnexo4();
            au4.setId(ent.getAuAnexos4Id().getId());
            au4.setFechaAutorizacion(ent.getAuAnexos4Id().getFechaAutorizacion());
            au4.setNumeroAutorizacion(ent.getAuAnexos4Id().getNumeroAutorizacion());
            CntPrestadorSede prestador = new CntPrestadorSede();
            if (ent.getAuAnexos4Id().getCntPrestadorSedesId() != null) {
                prestador.setNombreSede(ent.getAuAnexos4Id().getCntPrestadorSedesId().getNombre());
            }
            au4.setCntPrestadorSedeId(prestador);
            neg.setAuAnexo4Id(au4);
        }
        neg.setId(ent.getId());
        if (ent.getAuAnexo3ItemsId() != null) {
            AuAnexo3Item item3 = new AuAnexo3Item();
            item3.setId(ent.getAuAnexo3ItemsId().getId());
            neg.setAuAnexo3ItemId(item3);
        }
        if (ent.getAuAnexo2ItemsId() != null) {
            AuAnexo2Item item2 = new AuAnexo2Item();
            item2.setId(ent.getAuAnexo2ItemsId().getId());
            neg.setAuAnexo2ItemId(item2);
        }
        neg.setTipoTecnologia(ent.getTipoTecnologia());
        neg.setMaTecnologiaId(ent.getMaTecnologiaId());
        neg.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        neg.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        neg.setCantidadAutorizada(ent.getCantidadAutorizada());
        neg.setCostoServicio(ent.getCostoServicio());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        return neg;
    }
    
 }
