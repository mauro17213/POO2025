/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.wstransaccion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmTransaccion;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmTransaccionDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmRadicados;
import com.saviasaludeps.savia.ejb.entidades.WsCmTransaccionDetalles;
import com.saviasaludeps.savia.ejb.entidades.WsCmTransacciones;
import com.saviasaludeps.savia.ejb.entidades.WsCmFacturas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.wstransaccion.WsCmTransaccionDetalleRemoto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jeperez
 */
@Stateless
@Remote(WsCmTransaccionDetalleRemoto.class)
public class WsCmTransaccionDetalleServicio extends GenericoServicio implements WsCmTransaccionDetalleRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(wstd) FROM WsCmTransaccionDetalles wstd "
                    + "WHERE wstd.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND wstd.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "estado":
                            strQuery += "AND wstd.estado = " + (String) e.getValue() + " ";
                            break;

                    }
                }
            }
            
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND wstd.wsCmTransaccionesId.id = " + paramConsulta.getParametroConsulta1();
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
    public List<WsCmTransaccionDetalle> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<WsCmTransaccionDetalle> listResult = new ArrayList();
        try {
            String strQuery = "FROM WsCmTransaccionDetalles wstd "
                    + "WHERE wstd.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND wstd.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "estado":
                            strQuery += "AND wstd.estado = " + (String) e.getValue() + " ";
                            break;

                    }
                }
            }
            
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND wstd.wsCmTransaccionesId.id = " + paramConsulta.getParametroConsulta1();
            }
            
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "wstd." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "wstd.id ASC ";
            }
            List<WsCmTransaccionDetalles> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (WsCmTransaccionDetalles per : list) {
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
    public List<WsCmTransaccionDetalle> consultarPorIdWsCmTransaccion(int idWsCmTransaccion) throws Exception {
        List<WsCmTransaccionDetalle> listResult = new ArrayList();
        try {
            if (idWsCmTransaccion > 0) {
                String strQuery = "FROM WsCmTransaccionDetalles wstd "
                         + "  WHERE wstd.id > 0 AND wstd.wsCmTransaccionesId.id = "+idWsCmTransaccion;
                strQuery += " ORDER BY wstd.id ASC ";

                List<WsCmTransaccionDetalles> list = getEntityManager().createQuery(strQuery)
                        .getResultList();
                for (WsCmTransaccionDetalles per : list) {
                    listResult.add(castEntidadNegocio(per));
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
    public List<WsCmTransaccionDetalle> consultarPorIdCmFactura(ParamConsulta paramConsulta) throws Exception {
        List<WsCmTransaccionDetalle> listResult = new ArrayList();
        try {
     
            if ( paramConsulta.getParametroConsulta1() == null || 
                 paramConsulta.getParametroConsulta2()== null ) {
                return listResult;
            }

            String strQuery1 = "FROM WsCmFacturas wsf "
                    + "  WHERE wsf.id > 0  AND wsf.cmFacturasId.id = :facturaId ORDER BY wsf.id DESC";
            
            List<WsCmFacturas> list1 = getEntityManager().createQuery(strQuery1)
                    .setParameter("facturaId", paramConsulta.getParametroConsulta1())
                    .setMaxResults(1)
                    .getResultList();

            if (list1.isEmpty()) {
                return listResult;
            }

            int idCmRadicado = list1.get(0).getCmRadicadosId().getId();
            
            String strQuery = "FROM WsCmTransaccionDetalles wstd "
                    + "  WHERE wstd.id > 0 AND wstd.wsCmTransaccionesId.cmRadicadosId.id= :radicadoId AND"
                    + "  wstd.wsCmTransaccionesId.momento = :momento";
            strQuery += " ORDER BY wstd.id DESC ";

            List<WsCmTransaccionDetalles> list = getEntityManager().createQuery(strQuery)
                    .setParameter("radicadoId", idCmRadicado)
                    .setParameter("momento",  paramConsulta.getParametroConsulta2())
                    .getResultList();
            
            for (WsCmTransaccionDetalles per : list) {
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
    public List<WsCmTransaccionDetalle> consultarPorIdWsCmTransaccionEstado(int idWsCmTransaccion, short estado) throws Exception {
        List<WsCmTransaccionDetalle> listResult = new ArrayList();
        try {
            if (idWsCmTransaccion > 0 && estado > 0 ) {
                String strQuery = "FROM WsCmTransaccionDetalles wstd "
                         + "  WHERE wstd.id > 0 AND wstd.wsCmTransaccionesId.id = "+idWsCmTransaccion+" AND wstd.estado = "+estado;
                strQuery += " ORDER BY wstd.id ASC ";

                List<WsCmTransaccionDetalles> list = getEntityManager().createQuery(strQuery)
                        .getResultList();
                for (WsCmTransaccionDetalles per : list) {
                    listResult.add(castEntidadNegocio(per));
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
    public List<WsCmTransaccionDetalle> consultarPorIdWsCmTransaccionMultiEstado(int idWsCmTransaccion, short estado1, short estado2, short estado3) throws Exception {
        List<WsCmTransaccionDetalle> listResult = new ArrayList();
        try {
            if (idWsCmTransaccion > 0 && estado1 > 0 && estado2 > 0 && estado3 > 0  ) {
               
                
                String strQuery = "FROM WsCmTransaccionDetalles wstd "
                         + "  WHERE wstd.id > 0 AND wstd.wsCmTransaccionesId.id = "+idWsCmTransaccion + 
                           "  AND ( wstd.estado = "+estado1+" OR wstd.estado = "+estado2+" OR wstd.estado = "+estado3+" )";
                strQuery += " ORDER BY wstd.id ASC ";

                List<WsCmTransaccionDetalles> list = getEntityManager().createQuery(strQuery)
                        .getResultList();
                for (WsCmTransaccionDetalles per : list) {
                    listResult.add(castEntidadNegocio(per));
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
    public int insertar(WsCmTransaccionDetalle obj) throws Exception {
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
    public void actualizar(WsCmTransaccionDetalle obj) throws Exception {
        try {
            String hql = "UPDATE WsCmTransaccionDetalles SET "
                    + "codigoRetorno = :codigoRetorno "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("codigoRetorno", obj.getCodigoRetorno());
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
    public void actualizarJsonEnvio(byte[] jsonEnvio, int idWsCmTransaccionDetalle) throws Exception {
        try {
            if (idWsCmTransaccionDetalle > 0 && jsonEnvio != null) {
                String hql = "UPDATE WsCmTransaccionDetalles SET "
                        + "jsonEnvio = :jsonEnvio "
                        + "WHERE id = :id ";
                Query query = getEntityManager().createQuery(hql);
                query.setParameter("jsonEnvio", jsonEnvio);
                query.setParameter("id", idWsCmTransaccionDetalle);
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
    public void actualizarFechaEnvio(Date fechaHoraEnvio, int idWsCmTransaccionDetalle) throws Exception {
        try {
            if (idWsCmTransaccionDetalle > 0 && fechaHoraEnvio != null) {
                String hql = "UPDATE WsCmTransaccionDetalles SET "
                        + "fechaHoraEnvio = :fechaHoraEnvio "
                        + "WHERE id = :id ";
                Query query = getEntityManager().createQuery(hql);
                query.setParameter("fechaHoraEnvio", fechaHoraEnvio);
                query.setParameter("id", idWsCmTransaccionDetalle);
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
    public void actualizarEstado(short estado, int idWsCmTransaccionDetalle) throws Exception {
        try {
            if (idWsCmTransaccionDetalle > 0 && estado > 0) {
                String hql = "UPDATE WsCmTransaccionDetalles SET "
                        + "estado = :estado "
                        + "WHERE id = :id ";
                Query query = getEntityManager().createQuery(hql);
                query.setParameter("estado", estado);
                query.setParameter("id", idWsCmTransaccionDetalle);
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
    public void actualizarDatosRespuesta(WsCmTransaccionDetalle neg) throws Exception {
        try {
            if (neg.existeWsCmTransaccionDetalle()) {
                String hql = "UPDATE WsCmTransaccionDetalles SET "
                        + " jsonRespuesta = :jsonRespuesta , "
                        + " fechaHoraRespuesta = :fechaHoraRespuesta , "
                        + " codigoRetorno = :codigoRetorno , "
                        + " codigoRespuesta = :codigoRespuesta , "
                        + " mensajeRespuesta = :mensajeRespuesta  "
                        + "WHERE id = :id ";
                Query query = getEntityManager().createQuery(hql);
                query.setParameter("jsonRespuesta", neg.getJsonRespuesta());
                query.setParameter("fechaHoraRespuesta", neg.getFechaHoraRespuesta());
                query.setParameter("codigoRetorno", neg.getCodigoRetorno());
                query.setParameter("codigoRespuesta", neg.getCodigoRespuesta());
                query.setParameter("mensajeRespuesta", neg.getMensajeRespuesta());
                query.setParameter("id", neg.getId());
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
    public WsCmTransaccionDetalle eliminar(int id) throws Exception {
        WsCmTransaccionDetalle obj = null;
        try {
            WsCmTransaccionDetalles ent = getEntityManager().find(WsCmTransaccionDetalles.class, id);
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
    public WsCmTransaccionDetalle consultar(int id) throws Exception {
        WsCmTransaccionDetalle objRes = null;
        try {
            WsCmTransaccionDetalles per = (WsCmTransaccionDetalles) getEntityManager().find(WsCmTransaccionDetalles.class, id);
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

  
    public static WsCmTransaccionDetalle castEntidadNegocio(WsCmTransaccionDetalles ent) {
        WsCmTransaccionDetalle neg = new WsCmTransaccionDetalle();
        WsCmTransacciones  transaccionEntity = ent.getWsCmTransaccionesId();
        WsCmTransaccion transaccionNegocio = new WsCmTransaccion(transaccionEntity.getId());
        CmRadicados  cmRadicadoEntity = Optional.ofNullable(transaccionEntity.getCmRadicadosId()).orElse(new CmRadicados()) ;
        neg.setId(ent.getId()); 
        neg.setWsCmTransaccion(transaccionNegocio);
        transaccionNegocio.setCmRadicado(new CmRadicado(cmRadicadoEntity.getId()));
        neg.setEstado(ent.getEstado());
        neg.setJsonEnvio(ent.getJsonEnvio());
        neg.setFechaHoraEnvio(ent.getFechaHoraEnvio());
        byte[] jsonRespuesta = Optional.ofNullable( ent.getJsonRespuesta()).orElse(new byte[0]) ;
        neg.setJsonRespuesta(jsonRespuesta);
        neg.setFechaHoraRespuesta(ent.getFechaHoraRespuesta());
        Short codigoRetorno = Optional.ofNullable(ent.getCodigoRetorno()).orElse(Short.parseShort("0"));
        neg.setCodigoRetorno(codigoRetorno);
        Short codigoRespuesta = Optional.ofNullable(ent.getCodigoRespuesta()).orElse(Short.parseShort("0"));
        neg.setCodigoRespuesta(codigoRespuesta);
        neg.setMensajeRespuesta(ent.getMensajeRespuesta()); 
        return neg;
    }

    public static WsCmTransaccionDetalles castNegocioEntidad(WsCmTransaccionDetalle neg) {
        WsCmTransaccionDetalles ent = new WsCmTransaccionDetalles();
        ent.setId(neg.getId());
        ent.setWsCmTransaccionesId(new WsCmTransacciones(neg.getWsCmTransaccion().getId()));
        ent.setEstado(neg.getEstado());
        ent.setJsonEnvio(neg.getJsonEnvio());
        ent.setFechaHoraEnvio(neg.getFechaHoraEnvio());
        ent.setJsonRespuesta(neg.getJsonRespuesta());
        ent.setFechaHoraRespuesta(neg.getFechaHoraRespuesta());
        ent.setCodigoRetorno(neg.getCodigoRetorno());
        ent.setCodigoRespuesta(neg.getCodigoRespuesta());
        ent.setFechaHoraRespuesta(neg.getFechaHoraRespuesta());
        return ent;
    }
}
