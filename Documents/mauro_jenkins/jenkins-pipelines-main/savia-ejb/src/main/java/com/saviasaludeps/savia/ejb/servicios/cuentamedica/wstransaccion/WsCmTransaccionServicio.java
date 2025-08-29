/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.wstransaccion;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCierre;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmConciliacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmTransaccion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaCierres;
import com.saviasaludeps.savia.ejb.entidades.CmConciliaciones;
import com.saviasaludeps.savia.ejb.entidades.CmGlosaRespuestas;
import com.saviasaludeps.savia.ejb.entidades.CmRadicados;
import com.saviasaludeps.savia.ejb.entidades.WsCmTransacciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.wstransaccion.WsCmTransaccionRemoto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jperezn
 */
@Stateless
@Remote(WsCmTransaccionRemoto.class)
public class WsCmTransaccionServicio extends GenericoServicio implements WsCmTransaccionRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(wst) FROM WsCmTransacciones wst "
                    + " WHERE wst.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND wst.id = '" + (String) e.getValue() + "' ";
                            break;
                         case "estado":
                            strQuery += "AND wst.estado = " + (String) e.getValue() + " ";
                            break;

                    }
                }
            }

            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND wst.cmRadicadosId.id = " + paramConsulta.getParametroConsulta1();
            }

            cant = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
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
    public List<WsCmTransaccion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<WsCmTransaccion> listResult = new ArrayList();
        try {
            String strQuery = "FROM WsCmTransacciones wst "
                    + "WHERE wst.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND wst.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "estado":
                            strQuery += "AND wst.estado = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND wst.cmRadicadosId.id = " + paramConsulta.getParametroConsulta1();
            }
                     
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "wst." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "wst.id DESC ";
            }
            List<WsCmTransacciones> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (WsCmTransacciones ent : list) {
                listResult.add(castEntidadNegocio(ent));
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
    public WsCmTransaccion consultarPorEstadoIdRadicado(int idCmRadicado, int estado) throws Exception {
       WsCmTransaccion wsCmTransaccion = new WsCmTransaccion();
        try {
            if (idCmRadicado > 0 && estado > 0) {
                String strQuery = "FROM WsCmTransacciones wst "
                        + "WHERE wst.id > 0 AND wst.cmRadicadosId.id = "+idCmRadicado+ " AND wst.estado = "+estado;
                strQuery += " ORDER BY wst.id DESC ";
                WsCmTransacciones wsCmTransacciones = (WsCmTransacciones) getEntityManager().createQuery(strQuery).setMaxResults(1).getSingleResult();
                if (wsCmTransacciones != null) {
                   wsCmTransaccion  = castEntidadNegocio(wsCmTransacciones);
                }
            }
        } catch (NoResultException e) {
            wsCmTransaccion = new WsCmTransaccion();;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return wsCmTransaccion;
    }
    
    @Override
    public List<WsCmTransaccion> consultarListaPorEstadoIdRadicado(int idCmRadicado, int estado) throws Exception {
        List<WsCmTransaccion> listResult = new ArrayList();
        try {
            String strQuery = "FROM WsCmTransacciones wst "
                          + " WHERE wst.id > 0 AND wst.cmRadicadosId.id = "+idCmRadicado+ " AND wst.estado = "+estado;
                 strQuery += " ORDER BY wst.id DESC ";
      
            List<WsCmTransacciones> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (WsCmTransacciones ent : list) {
                listResult.add(castEntidadNegocio(ent));
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
    public int insertar(WsCmTransaccion neg) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(neg)).getId();
            neg.setId(_id);
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
    public void actualizar(WsCmTransaccion neg) throws Exception {
        try {
            String hql = "UPDATE WsCmTransacciones SET "
                    + "radicado = :radicado, "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("radicado", neg.getCmRadicado());
            query.setParameter("id", neg.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarEstado(int idWsCmFactura, short estado) throws Exception {
        try {
            String hql = "UPDATE WsCmTransacciones SET "
                    + "estado = :estado "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", estado);
            query.setParameter("id", idWsCmFactura);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarDatosInicioTransaccion(int idWstrandaccion, short estado, Date fechaEnvio) throws Exception {
        try {
            String hql = "UPDATE WsCmTransacciones SET "
                    + "fechaHoraEnvio = :fechaHoraEnvio, "
                    + "estado = :estado "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", estado);
            query.setParameter("fechaHoraEnvio", fechaEnvio);
            query.setParameter("id", idWstrandaccion);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarDatosRecibidosTransaccion(WsCmTransaccion wsCmTransaccion) throws Exception {
        try {
            String hql = "UPDATE WsCmTransacciones SET "
                    + "estado = :estado, "
                    + "codigoRetorno = :codigoRetorno, "
                    + "codigoRespuesta = :codigoRespuesta, "
                    + "mensajeRespuesta = :mensajeRespuesta, "
                    + "fechaHoraRespuesta = :fechaHoraRespuesta "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", wsCmTransaccion.getEstado());
            query.setParameter("codigoRetorno", wsCmTransaccion.getCodigoRetorno());
            query.setParameter("codigoRespuesta", wsCmTransaccion.getCodigoRespuesta());
            query.setParameter("mensajeRespuesta", wsCmTransaccion.getMensajeRespuesta());
            query.setParameter("fechaHoraRespuesta", wsCmTransaccion.getFechaHoraRespuesta());
            query.setParameter("id", wsCmTransaccion.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarPaquetesExitosos(int idWsTransaccion, short paquetesExitosos) throws Exception {
        try {
            if (paquetesExitosos > 0 && idWsTransaccion > 0) {
                String hql = "UPDATE WsCmTransacciones SET "
                        + "paquetesExitosos = :paquetesExitosos "
                        + "WHERE id = :id ";
                Query query = getEntityManager().createQuery(hql);
                query.setParameter("paquetesExitosos", paquetesExitosos);
                query.setParameter("id", idWsTransaccion);
                query.executeUpdate();
            }
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public WsCmTransaccion eliminar(int id) throws Exception {
        WsCmTransaccion obj = null;
        try {
            WsCmTransacciones ent = getEntityManager().find(WsCmTransacciones.class, id);
            if (ent != null) {
                obj  = castEntidadNegocio(ent);
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
    public WsCmTransaccion consultar(int id) throws Exception {
        WsCmTransaccion objRes = null;
        try {
            WsCmTransacciones per = (WsCmTransacciones) getEntityManager().find(WsCmTransacciones.class, id);
            if (per != null) {
                objRes = castEntidadNegocio(per);
            }

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
    public WsCmTransaccion  consultarUltimaWsTransaccionPorCmConciliacion(int idCmConciliacion) throws Exception {
        WsCmTransaccion wsfactura = new WsCmTransaccion();
        try {
            if (idCmConciliacion > 0) {
                String strQuery = "FROM WsCmTransacciones wst "
                         + " WHERE wst.id > 0 AND wst.cmRadicadosId.cmConciliacionesId.id = "+idCmConciliacion ;
                strQuery += " ORDER BY wst.id DESC ";

                WsCmTransacciones ent =  (WsCmTransacciones) getEntityManager().createQuery(strQuery).setMaxResults(1)
                        .getSingleResult();
                
                if(ent != null){
                  wsfactura =  castEntidadNegocio(ent);
                }  
            }
        } catch (NoResultException e) {
            wsfactura = new WsCmTransaccion();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return wsfactura;
    }
    
    @Override
    public WsCmTransaccion  consultarUltimaWsTransaccionPorCmAuditoriaMasiva(int idCmAuditoria) throws Exception {
        WsCmTransaccion wsfactura = new WsCmTransaccion();
        try {
            if (idCmAuditoria > 0) {
                String strQuery = "FROM WsCmTransacciones wst "
                         + " WHERE wst.id > 0 AND wst.cmRadicadosId.cmAuditoriaMasivaId.id = "+idCmAuditoria ;
                strQuery += " ORDER BY wst.id DESC ";

                WsCmTransacciones ent =  (WsCmTransacciones) getEntityManager().createQuery(strQuery).setMaxResults(1)
                        .getSingleResult();
                
                if(ent != null){
                  wsfactura =  castEntidadNegocio(ent);
                }  
            }
        } catch (NoResultException e) {
            wsfactura = new WsCmTransaccion();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return wsfactura;
    }
    
    public static WsCmTransaccion castEntidadNegocio(WsCmTransacciones ent) {
        WsCmTransaccion neg = new WsCmTransaccion();
              
        CmRadicados radicado = Optional.ofNullable(ent.getCmRadicadosId()).orElse(new CmRadicados());
        CmGlosaRespuestas glosa = Optional.ofNullable(ent.getCmGlosaRespuestasId()).orElse(new CmGlosaRespuestas());
        CmAuditoriaCierres cierre = Optional.ofNullable(ent.getCmAuditoriaCierresId()).orElse(new CmAuditoriaCierres());
        CmConciliaciones conciliacion = Optional.ofNullable(ent.getCmConciliacionesId() ).orElse(new CmConciliaciones());
        
        neg.setId(ent.getId());
        neg.setCmRadicado(new CmRadicado(radicado.getId()));
        neg.setCmGlosaRespuesta(new CmGlosaRespuesta(glosa.getId()));
        neg.setCmAuditoriaCierre(new CmAuditoriaCierre(cierre.getId()));
        neg.setCmConciliacion(new CmConciliacion(conciliacion.getId()));
        neg.setCmFacturasId(neg.getCmFacturasId());
        neg.setEstado(ent.getEstado());
        neg.setMomento(ent.getMomento());
        neg.setFechaHoraEnvio(ent.getFechaHoraEnvio());
        neg.setCodigoRetorno(ent.getCodigoRetorno());
        neg.setCodigoRespuesta(ent.getCodigoRespuesta());
        neg.setMensajeRespuesta(ent.getMensajeRespuesta());
        neg.setFechaHoraRespuesta(ent.getFechaHoraRespuesta());
        neg.setPaquetes(ent.getPaquetes());
        neg.setPaquetesExitosos(ent.getPaquetesExitosos());

        return neg;
    }

    public static WsCmTransacciones castNegocioEntidad(WsCmTransaccion neg) {
       
        WsCmTransacciones ent = new WsCmTransacciones();
        CmRadicado radicado = Optional.ofNullable(neg.getCmRadicado()).orElse(new CmRadicado());
        ent.setId(neg.getId());
        ent.setCmRadicadosId(new CmRadicados(radicado.getId()));;
        ent.setEstado(neg.getEstado());
        ent.setMomento(neg.getMomento());
        ent.setFechaHoraEnvio(neg.getFechaHoraEnvio());
        ent.setCodigoRetorno(neg.getCodigoRetorno());
        ent.setCodigoRespuesta(neg.getCodigoRespuesta());
        ent.setMensajeRespuesta(neg.getMensajeRespuesta());
        ent.setFechaHoraRespuesta(neg.getFechaHoraRespuesta());
        ent.setPaquetes(neg.getPaquetes());
        ent.setPaquetesExitosos(neg.getPaquetesExitosos());

        return ent;
    }
}
