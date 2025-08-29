/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCierre;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmConciliacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaCierres;
import com.saviasaludeps.savia.ejb.entidades.CmConciliaciones;
import com.saviasaludeps.savia.ejb.entidades.CmGlosaRespuestas;
import com.saviasaludeps.savia.ejb.entidades.CmRadicados;
import com.saviasaludeps.savia.ejb.entidades.CmSincronizaciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionRemoto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(CmSincronizacionRemoto.class)
@Local(CmSincronizacionLocal.class)
public class CmSincronizacionServicio extends GenericoServicio implements CmSincronizacionLocal, CmSincronizacionRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(s) FROM CmSincronizaciones s ";
            strQuery += " WHERE s.id > 0 ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "numeroRadicado":
                            strQuery += " AND s.numeroRadicado LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nit":
                            strQuery += " AND s.nit LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }

            Query query = getEntityManager().createQuery(strQuery);

            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fhInicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
                query.setParameter("fhFin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
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
    public List<CmSincronizacion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmSincronizacion> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmSincronizaciones s ";
            strQuery += " WHERE 1= 1  ";
            
            if (paramConsulta.getParametroConsulta1() != null){
                 strQuery += " AND s.cmRadicadosId.id = " + paramConsulta.getParametroConsulta1() + " ";
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "numeroRadicado":
                            strQuery += " AND s.numeroRadicado LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nit":
                            strQuery += " AND s.nit LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }

            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                String order = paramConsulta.getOrden().replace("gsAfiliado", "gsAfiliadosId").
                        replace("gsZona", "gsZonasId");
                strQuery += " s." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " s.fechaHoraEnvio DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);

            List<CmSincronizaciones> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CmSincronizaciones neg : list) {
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
    public CmSincronizacion consultarUltimaSincronizacionPorCmConciliacion(int idCmConciliacion) throws Exception {
        CmSincronizacion cmSincronizacion = new CmSincronizacion();
        try {
            String strQuery = "FROM CmSincronizaciones s ";
            strQuery += " WHERE s.cmRadicadosId.cmConciliacionesId.id= "+idCmConciliacion;

            strQuery += " ORDER BY s.id DESC ";
            Query query = getEntityManager().createQuery(strQuery);

            List<CmSincronizaciones> list = query
                    .getResultList();
            for (CmSincronizaciones neg : list) {
                cmSincronizacion = castEntidadNegocio(neg);
                break;
            }
        } catch (NoResultException e) {
            cmSincronizacion = new CmSincronizacion();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cmSincronizacion;
    }
    
    @Override
    public CmSincronizacion consultarUltimaSincronizacionPorCmAuditoriaMasiva(int idCmAuditoriaMasiva) throws Exception {
        CmSincronizacion cmSincronizacion = new CmSincronizacion();
        try {
            String strQuery = "FROM CmSincronizaciones s ";
            strQuery += " WHERE s.cmRadicadosId.cmAuditoriaMasivaId.id= "+idCmAuditoriaMasiva;

            strQuery += " ORDER BY s.id DESC ";
            Query query = getEntityManager().createQuery(strQuery);

            List<CmSincronizaciones> list = query
                    .getResultList();
            for (CmSincronizaciones neg : list) {
                cmSincronizacion = castEntidadNegocio(neg);
                break;
            }
        } catch (NoResultException e) {
            cmSincronizacion = new CmSincronizacion();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cmSincronizacion;
    }

    @Override
    public CmSincronizacion consultar(int id) throws Exception {
        CmSincronizacion obj = null;
        try {
            CmSincronizaciones per = (CmSincronizaciones) getEntityManager().find(CmSincronizaciones.class, id);
            obj = castEntidadNegocio(per);
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
    public int insertar(CmSincronizacion obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al insertar una CmSincronizacion");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(CmSincronizacion obj) throws Exception {
        try {
            
              CmSincronizaciones sincronizaciones =  castNegocioEntidad(obj);
                    
               String hql = "UPDATE CmSincronizaciones SET"
                    + " jsonEnvio = :jsonEnvio,"
                    + " fechaHoraEnvio = :fechaHoraEnvio,"
                    + " jsonRespuesta = :jsonRespuesta,"
                    + " codigoRetorno  = :codigoRetorno,"       
                    + " codigoRespuesta = :codigoRespuesta,"
                    + " mensajeRespuesta = :mensajeRespuesta,"
                    + " fechaHoraRespuesta = :fechaHoraRespuesta,"
                    + " cmGlosaRespuestasId.id = :cmGlosaRespuestasId,"   
                    + " cmConciliacionesId.id = :cmConciliacionesId,"
                    + " cmRadicadosId.id = :cmRadicadosId,"
                    + " cmAuditoriaCierresId.id = :cmAuditoriaCierresId,"
                    + " estadoTransacion = :estadoTransacion,"
                    + " paquetes = :paquetes,"
                    + " paquetesExitosos = :paquetesExitosos"
                    + " WHERE id = :id";
            
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("jsonEnvio", sincronizaciones.getJsonEnvio());
            query.setParameter("fechaHoraEnvio",sincronizaciones.getFechaHoraEnvio());
            query.setParameter("jsonRespuesta", sincronizaciones.getJsonRespuesta());
            query.setParameter("codigoRetorno", sincronizaciones.getCodigoRetorno());
            query.setParameter("codigoRespuesta", sincronizaciones.getCodigoRespuesta());
            query.setParameter("mensajeRespuesta", sincronizaciones.getMensajeRespuesta());
            query.setParameter("fechaHoraRespuesta", sincronizaciones.getFechaHoraRespuesta());
            
            Integer idGlosaRespuesta = sincronizaciones.getCmGlosaRespuestasId() != null && 
                                     sincronizaciones.getCmGlosaRespuestasId().getId() != null ?  
                                     sincronizaciones.getCmGlosaRespuestasId().getId() : null;
               
            Integer idConciliaciones = sincronizaciones.getCmConciliacionesId() != null && 
                                     sincronizaciones.getCmConciliacionesId().getId() != null ?  
                                     sincronizaciones.getCmConciliacionesId().getId() : null;
            
            Integer idRadicado = sincronizaciones.getCmRadicadosId() != null && 
                                 sincronizaciones.getCmRadicadosId().getId() != null ?  
                                 sincronizaciones.getCmRadicadosId().getId() : null;     
            
            Integer idAuditoraiCierre = sincronizaciones.getCmAuditoriaCierresId()!= null && 
                                 sincronizaciones.getCmAuditoriaCierresId().getId() != null ?  
                                 sincronizaciones.getCmAuditoriaCierresId().getId() : null;    
            
            query.setParameter("cmGlosaRespuestasId", idGlosaRespuesta);
            query.setParameter("cmConciliacionesId", idConciliaciones );
            query.setParameter("cmRadicadosId", idRadicado);
            query.setParameter("cmAuditoriaCierresId", idAuditoraiCierre);
            query.setParameter("estadoTransacion", sincronizaciones.getEstadoTransacion());
            query.setParameter("paquetes", sincronizaciones.getPaquetes());
            query.setParameter("paquetesExitosos", sincronizaciones.getPaquetesExitosos());
            
            query.setParameter("id", sincronizaciones.getId());
            query.executeUpdate(); 
            
            //obj.setId((int) getEntityManager().merge(castNegocioEntidad(obj)).getId());
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public List<CmSincronizacion> consultarPorRadicado(ParamConsulta paramConsulta) throws Exception {
        List<CmSincronizacion> listResult = new ArrayList();
        try {
            if (paramConsulta.getParametroConsulta1() != null
                    && !paramConsulta.getParametroConsulta1().equals("")) {
                String strQuery = "FROM CmSincronizaciones s WHERE s.id > 0 AND"
                        + " s.cmRadicadosId.id = :idRadicado ORDER BY s.fechaHoraEnvio DESC";
                Query query = getEntityManager().createQuery(strQuery);
                if (paramConsulta.getParametroConsulta1() != null) {
                    query.setParameter("idRadicado", paramConsulta.getParametroConsulta1());
                }
                List<CmSincronizaciones> list = query
                        .getResultList();
                for (CmSincronizaciones neg : list) {
                    listResult.add(castEntidadNegocio(neg));
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
    public int consultarCantidadPorConciliacion(int cm_conciliaciones_id) throws Exception {
       int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cms) FROM CmSincronizaciones cms ";         
            strQuery += " WHERE cms.cmConciliacionesId.id = :idConciliacion";
            Query query = getEntityManager().createQuery(strQuery).setParameter("idConciliacion",cm_conciliaciones_id);   
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
    public CmSincronizacion eliminar(int id) throws Exception {
        CmSincronizacion obj = null;
        try {
            CmSincronizaciones per = getEntityManager().find(CmSincronizaciones.class, id);
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

    public static CmSincronizacion castEntidadNegocio(CmSincronizaciones neg) {
        boolean existePaquetes = false;
        CmSincronizacion ent = new CmSincronizacion();
        ent.setId(neg.getId());
        if (neg.getCmGlosaRespuestasId() != null) {
            ent.setCmGlosaRespuesta(new CmGlosaRespuesta(neg.getCmGlosaRespuestasId().getId()));
        }
        if (neg.getCmRadicadosId() != null) {
            ent.setCmRadicado(new CmRadicado(neg.getCmRadicadosId().getId()));
        }

        if (neg.getCmConciliacionesId() != null) {
            ent.setCmConciliacion(new CmConciliacion(neg.getCmConciliacionesId().getId()));
        }
        
        if (neg.getCmAuditoriaCierresId() != null) {
            ent.setCmAuditoriaCierre(new CmAuditoriaCierre(neg.getCmAuditoriaCierresId().getId()));
        }  
        ent.setJsonEnvio(neg.getJsonEnvio());
        ent.setFechaHoraEnvio(neg.getFechaHoraEnvio());
        ent.setJsonRespuesta(neg.getJsonRespuesta());
        ent.setMomento(neg.getMomento());
        ent.setCodigoRetorno(neg.getCodigoRetorno());
        ent.setCodigoRespuesta(neg.getCodigoRespuesta());
        ent.setMensajeRespuesta(neg.getMensajeRespuesta());
        ent.setFechaHoraRespuesta(neg.getFechaHoraRespuesta());
        ent.setPaquetes(neg.getPaquetes());
        ent.setPaquetesExitosos(neg.getPaquetesExitosos());
        if(neg.getCmSincronizacionPaquetesList() != null &&
           neg.getCmSincronizacionPaquetesList().size()>0 ){
           existePaquetes = true;
        }
        ent.setPoseePaquetes(existePaquetes);
        ent.setEstadoTransacion(neg.getEstadoTransacion());
        return ent;
    }

    public static CmSincronizaciones castNegocioEntidad(CmSincronizacion ent) {
        CmSincronizaciones neg = new CmSincronizaciones();
        neg.setId(ent.getId());
        if (ent.getCmGlosaRespuesta() != null && ent.getCmGlosaRespuesta().getId() != null) {
            neg.setCmGlosaRespuestasId(new CmGlosaRespuestas(ent.getCmGlosaRespuesta().getId()));
        }
        if (ent.getCmRadicado() != null && ent.getCmRadicado().getId() != null) {
            neg.setCmRadicadosId(new CmRadicados(ent.getCmRadicado().getId()));
        }
        if (ent.getCmConciliacion() != null && ent.getCmConciliacion().getId() != null) {
            neg.setCmConciliacionesId(new CmConciliaciones(ent.getCmConciliacion().getId()));
        }
        if (ent.getCmAuditoriaCierre() != null && ent.getCmAuditoriaCierre().getId() != null) {
            neg.setCmAuditoriaCierresId( new CmAuditoriaCierres (ent.getCmAuditoriaCierre().getId()));
        }
        if (ent.getCmFactura()!=null){
            neg.setCmFacturasId(ent.getCmFactura().getId());
        }
        neg.setJsonEnvio(ent.getJsonEnvio());
        neg.setMomento(ent.getMomento());
        neg.setFechaHoraEnvio(ent.getFechaHoraEnvio());
        neg.setJsonRespuesta(ent.getJsonRespuesta());
        neg.setEstadoTransacion(ent.getEstadoTransacion());
        neg.setCodigoRetorno(ent.getCodigoRetorno());
        neg.setCodigoRespuesta(ent.getCodigoRespuesta());
        neg.setMensajeRespuesta(ent.getMensajeRespuesta());
        neg.setFechaHoraRespuesta(ent.getFechaHoraRespuesta());
        neg.setPaquetes(ent.getPaquetes());
        neg.setPaquetesExitosos(ent.getPaquetesExitosos());
        return neg;
    }

}
