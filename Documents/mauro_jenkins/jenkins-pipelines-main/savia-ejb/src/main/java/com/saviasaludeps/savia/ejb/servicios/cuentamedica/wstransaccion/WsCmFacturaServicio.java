/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.wstransaccion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmFactura;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmFacturas;
import com.saviasaludeps.savia.ejb.entidades.CmFeRipsCargas;
import com.saviasaludeps.savia.ejb.entidades.WsCmFacturas;
import com.saviasaludeps.savia.ejb.entidades.CmRadicados;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.wstransaccion.WsCmFacturaRemoto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jperez
 */
@Stateless
@Remote(WsCmFacturaRemoto.class)
public class WsCmFacturaServicio extends GenericoServicio implements WsCmFacturaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(wsf) FROM WsCmFacturas wsf "
                    + "WHERE wsf.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND wsf.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "estado":
                            strQuery += "AND wsf.estado = " + (String) e.getValue() + " ";
                            break;
                        case "contrato":
                            strQuery += "AND wsf.contrato = '" + (String) e.getValue() + "' ";
                            break;
                        case "numeroDocumento":
                            strQuery += "AND wsf.numeroDocumento = '" + (String) e.getValue() + "' ";
                            break;

                    }
                }
            }
            
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND wsf.cmRadicadosId.id = " + paramConsulta.getParametroConsulta1();
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
    public List<WsCmFactura> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<WsCmFactura> listResult = new ArrayList();
        try {
            String strQuery = "FROM WsCmFacturas wsf "
                    + "WHERE wsf.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND wsf.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "estado":
                            strQuery += "AND wsf.estado = " + (String) e.getValue() + " ";
                            break;
                        case "contrato":
                            strQuery += "AND wsf.contrato = '" + (String) e.getValue() + "' ";
                            break;
                        case "numeroDocumento":
                            strQuery += "AND wsf.numeroDocumento = '" + (String) e.getValue() + "' ";
                            break;
                    }
                }
            }
            
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND wsf.cmRadicadosId.id = " + paramConsulta.getParametroConsulta1();
            }
              
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "wsf." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "wsf.id ASC ";
            }
            List<WsCmFacturas> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (WsCmFacturas ent : list) {
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
    public List<WsCmFactura> consultarTodasPorIdCmRadicado(int idCmRadicado) throws Exception {
        List<WsCmFactura> listResult = new ArrayList();
        try {
            if (idCmRadicado > 0) {
                String strQuery = "FROM WsCmFacturas wsf "
                         + " WHERE wsf.id > 0 AND wsf.cmRadicadosId.id = "+idCmRadicado ;
                strQuery += " ORDER BY wsf.id ASC";

                List<WsCmFacturas> list = getEntityManager().createQuery(strQuery)
                        .getResultList();
                for (WsCmFacturas ent : list) {
                    listResult.add(castEntidadNegocio(ent));
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
    public WsCmFactura  consultarUltimaWsCmFacturaPorCmRadicado(int idCmRadicado) throws Exception {
        WsCmFactura wsfactura = new WsCmFactura();
        try {
            if (idCmRadicado > 0) {
                String strQuery = "FROM WsCmFacturas wsf "
                         + " WHERE wsf.id > 0 AND wsf.cmRadicadosId.id = "+idCmRadicado ;
                strQuery += " ORDER BY wsf.id DESC ";

                WsCmFacturas ent = (WsCmFacturas) getEntityManager().createQuery(strQuery).setMaxResults(1)
                        .getSingleResult();
                
                if(ent != null){
                  wsfactura =  castEntidadNegocio(ent);
                }  
            }
        } catch (NoResultException e) {
            wsfactura = new WsCmFactura();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return wsfactura;
    }
    
    @Override
    public List<WsCmFactura> consultarPorEstadoIdCmRadicado(int idCmRadicado, int estado) throws Exception {
        List<WsCmFactura> listResult = new ArrayList();
        try {
            if (idCmRadicado > 0 && estado > 0) {
                String strQuery = "FROM WsCmFacturas wsf "
                         + " WHERE wsf.id > 0 AND wsf.cmRadicadosId.id = "+idCmRadicado+ " AND wsf.estado = "+estado;
                strQuery += " ORDER BY wsf.id ASC";

                List<WsCmFacturas> list = getEntityManager().createQuery(strQuery)
                        .getResultList();
                for (WsCmFacturas ent : list) {
                    listResult.add(castEntidadNegocio(ent));
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
    public List<WsCmFactura> consultarPorIdCmRadicadoEstadoPendientes(int idCmRadicado) throws Exception {
        List<WsCmFactura> listResult = new ArrayList();
        try {
            if (idCmRadicado > 0 ) {
                String strQuery = "FROM WsCmFacturas wsf "
                         + " WHERE wsf.id > 0 AND wsf.cmRadicadosId.id = "+idCmRadicado+ " AND"
                         + " (wsf.estado != "+WsCmFactura.ESTADO_EXITOSO+" AND wsf.estado != "+WsCmFactura.ESTADO_SIN_VALORES_EPS+" )";
                strQuery += " ORDER BY wsf.id ASC";

                List<WsCmFacturas> list = getEntityManager().createQuery(strQuery)
                        .getResultList();
                for (WsCmFacturas ent : list) {
                    listResult.add(castEntidadNegocio(ent));
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
    public int insertar(WsCmFactura neg) throws Exception {
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
    public void actualizar(WsCmFactura neg) throws Exception {
        try {
            String hql = "UPDATE WsCmFacturas SET "
                    + "contrato = :contrato, "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("contrato", neg.getContrato());
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
            String hql = "UPDATE WsCmFacturas SET "
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
    public void actualizarEstado(int idCmRadicado, int idCmFactura, short estado) throws Exception {
        try {
            String hql = "UPDATE WsCmFacturas SET "
                    + "estado = :estado "
                    + "WHERE facturaId = :facturaId AND cmRadicadosId.id = :idCmRadicado";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", estado);
            query.setParameter("facturaId", idCmFactura);
            query.setParameter("idCmRadicado", idCmRadicado);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarValoresProceso(int idWsCmFactura,  BigDecimal valorPagado , BigDecimal valorGlosado ) throws Exception {
        try {
            String hql = "UPDATE WsCmFacturas SET "
                    + " valorPagado  = :valorPagado ,"
                    + " valorGlosado = :valorGlosado "
                    + " WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("valorPagado", valorPagado);
            query.setParameter("valorGlosado", valorGlosado);
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
    public WsCmFactura eliminar(int id) throws Exception {
        WsCmFactura obj = null;
        try {
            WsCmFacturas ent = getEntityManager().find(WsCmFacturas.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
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
    public WsCmFactura consultar(int id) throws Exception {
        WsCmFactura objRes = null;
        try {
            WsCmFacturas per = (WsCmFacturas) getEntityManager().find(WsCmFacturas.class, id);
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

    public static WsCmFactura castEntidadNegocio(WsCmFacturas ent) {
        WsCmFactura neg = new WsCmFactura();
        neg.setId(ent.getId());
        CmRadicados radicado = Optional.ofNullable(ent.getCmRadicadosId()).orElse(new CmRadicados());
        neg.setCmRadicado(new CmRadicado(radicado.getId()));
        CmFacturas factura = Optional.ofNullable(ent.getCmFacturasId()).orElse(new CmFacturas());
        
        CmFeRipsCargas cmFeRipsCarga = Optional.ofNullable(factura.getCmFeRipsCargasId()).orElse(new CmFeRipsCargas());
        CmRipsCargas cmRipCarga = Optional.ofNullable(factura.getCmRipsCargasId()).orElse(new CmRipsCargas());
        CmFactura cmFactura = new CmFactura(factura.getId());
        cmFactura.setCmFeRipsCarga(new CmFeRipsCarga(cmFeRipsCarga.getId()));
        cmFactura.setCmRipCarga(new CmRipsCarga(cmRipCarga.getId()));
        
        neg.setCmFactura(cmFactura);
        neg.setEstado(ent.getEstado());
        neg.setProveedorNit(ent.getProveedorNit());
        neg.setNumeroDocumento(ent.getNumeroDocumento());
        neg.setNumeroRadicado(ent.getNumeroRadicado());
        neg.setFacturaId(ent.getFacturaId());
        neg.setRegimen(ent.getRegimen());
        neg.setValorDocumento(ent.getValorDocumento());
        neg.setValorGlosado(ent.getValorGlosado());
        neg.setValorPagado(ent.getValorPagado());
        neg.setValorCopago(ent.getValorCopago());
        neg.setFechaHoraDocumento(ent.getFechaHoraDocumento());
        neg.setFechaHoraProceso(ent.getFechaHoraProceso());
        neg.setCuotaModeradora(ent.getCuotaModeradora());
        neg.setContrato(ent.getContrato());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        return neg;
    }

    public static WsCmFacturas castNegocioEntidad(WsCmFactura neg) {
        WsCmFacturas ent = new WsCmFacturas();
        ent.setId(neg.getId());
        CmRadicado radicado = Optional.ofNullable(neg.getCmRadicado()).orElse(new CmRadicado());
        ent.setCmRadicadosId(new CmRadicados(radicado.getId()));
        CmFactura factura = Optional.ofNullable(neg.getCmFactura()).orElse(new CmFactura());
        ent.setCmFacturasId(new CmFacturas(factura.getId()));
        ent.setEstado(neg.getEstado());
        ent.setProveedorNit(neg.getProveedorNit());
        ent.setNumeroDocumento(neg.getNumeroDocumento());
        ent.setNumeroRadicado(neg.getNumeroRadicado());
        ent.setFacturaId(neg.getFacturaId());
        ent.setRegimen(neg.getRegimen());
        ent.setValorDocumento(neg.getValorDocumento());
        ent.setValorGlosado(neg.getValorGlosado());
        ent.setValorPagado(neg.getValorPagado());
        ent.setValorCopago(neg.getValorCopago());
        ent.setFechaHoraDocumento(neg.getFechaHoraDocumento());
        ent.setFechaHoraProceso(neg.getFechaHoraProceso());
        ent.setCuotaModeradora(neg.getCuotaModeradora());
        ent.setContrato(neg.getContrato());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        return ent;
    }
}
