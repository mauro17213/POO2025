/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.conciliacion;





import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionPaquete;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.CmSincronizacionPaquetes;
import com.saviasaludeps.savia.ejb.entidades.CmSincronizaciones;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionPaqueteRemoto;
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
@Remote(CmSincronizacionPaqueteRemoto.class)
@Local(CmSincronizacionPaqueteLocal.class)
public class CmSincronizacionPaqueteServicio extends GenericoServicio implements CmSincronizacionPaqueteLocal, CmSincronizacionPaqueteRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cmsp) FROM CmSincronizacionPaquetes cmsp ";         
            strQuery += " WHERE cmsp.id > 0  ";
                           
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                     switch ((String) e.getKey()) {  
                         case "id":
                             strQuery += " AND cmsp.id = '" + e.getValue() + "' ";
                         case "cmSincronizacionesId":
                             strQuery += " AND cmsp.cmSincronizacionesId.id = '" + e.getValue() + "' ";
                             break;
                         case "estadoTransacion":
                             strQuery += " AND cmsp.estadoTransacion = '" + e.getValue() + "' ";
                             break;
                         case "fechaHoraEnvio":
                             strQuery += " AND cmsp.fechaHoraEnvio = '" + e.getValue() + "' ";
                             break;
                         case "fechaHoraRespuesta":
                             strQuery += " AND cmsp.fechaHoraRespuesta = '" + e.getValue() + "' ";
                             break;
                         case "codigoRetorno":
                             strQuery += " AND cmsp.codigoRetorno = '" + e.getValue() + "' ";
                             break;
                         case "codigoRespuesta":
                             strQuery += " AND cmsp.codigoRespuesta = '" + e.getValue() + "' ";
                             break;
                    }
                }
            }
            
           Query query = getEntityManager().createQuery(strQuery);            
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
    public List<CmSincronizacionPaquete> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmSincronizacionPaquete> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmSincronizacionPaquetes cmsp ";        
            strQuery += " WHERE cmsp.id > 0  ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                     switch ((String) e.getKey()) {  
                         case "id":
                             strQuery += " AND cmsp.id = '" + e.getValue() + "' ";
                         case "cmSincronizacionesId":
                             strQuery += " AND cmsp.cmSincronizacionesId.id = '" + e.getValue() + "' ";
                             break;
                         case "estadoTransacion":
                             strQuery += " AND cmsp.estadoTransacion = '" + e.getValue() + "' ";
                             break;
                         case "fechaHoraEnvio":
                             strQuery += " AND cmsp.fechaHoraEnvio = '" + e.getValue() + "' ";
                             break;
                         case "fechaHoraRespuesta":
                             strQuery += " AND cmsp.fechaHoraRespuesta = '" + e.getValue() + "' ";
                             break;
                         case "codigoRetorno":
                             strQuery += " AND cmsp.codigoRetorno = '" + e.getValue() + "' ";
                             break;
                         case "codigoRespuesta":
                             strQuery += " AND cmsp.codigoRespuesta = '" + e.getValue() + "' ";
                             break;
                    }
                }
            }

            strQuery += " ORDER BY ";
            
            if (paramConsulta.getOrden() != null) {
                
                String order = paramConsulta.getOrden().replace("cmGlosaRespuesta", "cmGlosaRespuestasId").
                                                        replace("cmDetalle", "cmDetallesId");
                strQuery += " cmsp." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " cmsp.fechaHoraRespuesta DESC";
            }      
            Query query = getEntityManager().createQuery(strQuery);
            List<CmSincronizacionPaquetes> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CmSincronizacionPaquetes neg : list) {
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
    public List<CmSincronizacionPaquete> consultarPorSincronizacion(ParamConsulta paramConsulta) throws Exception {
        List<CmSincronizacionPaquete> listResult = new ArrayList();
        try {
            if (paramConsulta.getParametroConsulta1() != null
                    && !paramConsulta.getParametroConsulta1().equals("")) {
                String strQuery = "FROM CmSincronizacionPaquetes cmsp WHERE cmsp.id > 0 AND"
                        + " cmsp.cmSincronizacionesId.id = :idSincronizacion ORDER BY cmsp.fechaHoraEnvio DESC";
                Query query = getEntityManager().createQuery(strQuery);
                if (paramConsulta.getParametroConsulta1() != null) {
                    query.setParameter("idSincronizacion", paramConsulta.getParametroConsulta1());
                }
                List<CmSincronizacionPaquetes> list = query
                        .getResultList();
                for (CmSincronizacionPaquetes neg : list) {
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
    public CmSincronizacionPaquete consultar(int id) throws Exception {
        CmSincronizacionPaquete obj = null;
        try {
            CmSincronizacionPaquetes neg = (CmSincronizacionPaquetes) getEntityManager().find(CmSincronizacionPaquetes.class, id);
            if(neg != null){
                obj = castEntidadNegocio(neg);
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
    public int insertar(CmSincronizacionPaquete obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al insertar la sincronizacion paquete");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(CmSincronizacionPaquete obj) throws Exception {
        try {
            //obj.setId((int) getEntityManager().merge(castNegocioEntidad(obj)).getId());  
            CmSincronizacionPaquetes sincronizacionPaquete = castNegocioEntidad(obj);

            String hql = "UPDATE CmSincronizacionPaquetes SET"
                    + " estadoTransacion = :estadoTransacion,"
                    + " jsonEnvio = :jsonEnvio,"
                    + " fechaHoraEnvio = :fechaHoraEnvio,"
                    + " jsonRespuesta  = :jsonRespuesta,"
                    + " fechaHoraRespuesta = :fechaHoraRespuesta,"
                    + " codigoRetorno = :codigoRetorno,"
                    + " codigoRespuesta = :codigoRespuesta,"
                    + " mensajeRespuesta = :mensajeRespuesta,"
                    + " cmSincronizacionesId.id = :cmSincronizacionesId "
                    + " WHERE id = :id";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estadoTransacion", sincronizacionPaquete.getEstadoTransacion());
            query.setParameter("jsonEnvio", sincronizacionPaquete.getJsonEnvio());
            query.setParameter("fechaHoraEnvio", sincronizacionPaquete.getFechaHoraEnvio());
            query.setParameter("jsonRespuesta", sincronizacionPaquete.getJsonRespuesta());
            query.setParameter("fechaHoraRespuesta", sincronizacionPaquete.getFechaHoraRespuesta());
            query.setParameter("codigoRetorno", sincronizacionPaquete.getCodigoRetorno());
            query.setParameter("codigoRespuesta", sincronizacionPaquete.getCodigoRespuesta());
            query.setParameter("mensajeRespuesta", sincronizacionPaquete.getMensajeRespuesta());
            query.setParameter("cmSincronizacionesId", sincronizacionPaquete.getCmSincronizacionesId().getId());
            query.setParameter("id", sincronizacionPaquete.getId());
            query.executeUpdate();
            
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CmSincronizacionPaquete eliminar(int id) throws Exception {
        CmSincronizacionPaquete obj = null;
        try {
            CmSincronizacionPaquetes neg = getEntityManager().find(CmSincronizacionPaquetes.class, id);
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

    public static CmSincronizacionPaquete castEntidadNegocio(CmSincronizacionPaquetes neg) {
        CmSincronizacionPaquete ent = new CmSincronizacionPaquete(); 
        ent.setId(neg.getId());
        if (neg.getCmSincronizacionesId()!= null) {
            ent.setCmSincronizacion(new CmSincronizacion(neg.getCmSincronizacionesId().getId()));
        }
        ent.setEstadoTransacion(neg.getEstadoTransacion());
        ent.setJsonEnvio(neg.getJsonEnvio());
        ent.setFechaHoraEnvio(neg.getFechaHoraEnvio());
        ent.setJsonRespuesta(neg.getJsonRespuesta());
        ent.setFechaHoraRespuesta(neg.getFechaHoraRespuesta());
        ent.setCodigoRetorno(neg.getCodigoRetorno());
        ent.setCodigoRespuesta(neg.getCodigoRespuesta());
        ent.setMensajeRespuesta(neg.getMensajeRespuesta());
        return ent;
    }

    public static CmSincronizacionPaquetes castNegocioEntidad(CmSincronizacionPaquete ent) {
        CmSincronizacionPaquetes neg = new CmSincronizacionPaquetes();
        neg.setId(ent.getId());
        if (ent.getCmSincronizacion()!= null) {
            neg.setCmSincronizacionesId(new CmSincronizaciones(ent.getCmSincronizacion().getId()));
        }
        neg.setEstadoTransacion(ent.getEstadoTransacion());
        neg.setJsonEnvio(ent.getJsonEnvio());
        neg.setFechaHoraEnvio(ent.getFechaHoraEnvio());
        neg.setJsonRespuesta(ent.getJsonRespuesta());
        neg.setFechaHoraRespuesta(ent.getFechaHoraRespuesta());
        neg.setCodigoRetorno(ent.getCodigoRetorno());
        neg.setCodigoRespuesta(ent.getCodigoRespuesta());
        neg.setMensajeRespuesta(ent.getMensajeRespuesta());
        return neg;
    }

}
