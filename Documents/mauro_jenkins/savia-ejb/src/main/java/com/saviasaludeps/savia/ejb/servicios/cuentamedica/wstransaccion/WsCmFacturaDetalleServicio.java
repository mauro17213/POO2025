/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.wstransaccion;

import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmFacturaDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.WsCmFacturas;
import com.saviasaludeps.savia.ejb.entidades.WsCmFacturaDetalles;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.wstransaccion.WsCmFacturaDetalleRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jorge perez
 */
@Stateless
@Remote(WsCmFacturaDetalleRemoto.class)
public class WsCmFacturaDetalleServicio extends GenericoServicio implements WsCmFacturaDetalleRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(wsd) FROM WsCmFacturaDetalles wsd "
                    + "WHERE wsd.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND wsd.id = '" + (String) e.getValue() + "' ";
                            break;

                    }
                }
            }
            
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND wsd.wsCmFacturasId.id = " + paramConsulta.getParametroConsulta1();
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
    public List<WsCmFacturaDetalle> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<WsCmFacturaDetalle> listResult = new ArrayList();
        try {
            String strQuery = "FROM WsCmFacturaDetalles wsd "
                    + "WHERE wsd.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND wsd.id = '" + (String) e.getValue() + "' ";
                            break;

                    }
                }
            }
            
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND wsd.wsCmFacturasId.id = " + paramConsulta.getParametroConsulta1();
            }
              
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "wsd." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "wsd.id ASC ";
            }
            List<WsCmFacturaDetalles> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (WsCmFacturaDetalles ent : list) {
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
    public List<WsCmFacturaDetalle> consultarPorIdWsCmFactura(int idWsCmFactura) throws Exception {
        List<WsCmFacturaDetalle> listResult = new ArrayList();
        try {
            if (idWsCmFactura > 0) {
                String strQuery = "FROM WsCmFacturaDetalles wsd "
                        + " WHERE wsd.id > 0 AND wsd.wsCmFacturasId.id=" + idWsCmFactura;
                strQuery += " ORDER BY wsd.id ASC ";

                List<WsCmFacturaDetalles> list = getEntityManager().createQuery(strQuery)
                        .getResultList();
                for (WsCmFacturaDetalles ent : list) {
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
    public int insertar(WsCmFacturaDetalle neg) throws Exception {
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
    public void actualizar(WsCmFacturaDetalle neg) throws Exception {
        try {
            String hql = "UPDATE CmRipsInhabilitados SET "
                    + "consecutivo = :consecutivo, "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("consecutivo", neg.getConsecutivo());
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
    public WsCmFacturaDetalle eliminar(int id) throws Exception {
        WsCmFacturaDetalle obj = null;
        try {
            WsCmFacturaDetalles ent = getEntityManager().find(WsCmFacturaDetalles.class, id);
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
    public WsCmFacturaDetalle consultar(int id) throws Exception {
        WsCmFacturaDetalle objRes = null;
        try {
            WsCmFacturaDetalles per = (WsCmFacturaDetalles) getEntityManager().find(WsCmFacturaDetalles.class, id);
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

    public static WsCmFacturaDetalle castEntidadNegocio(WsCmFacturaDetalles ent) {
        WsCmFacturaDetalle neg = new WsCmFacturaDetalle();
        neg.setId(ent.getId());
        neg.setWsCmFactura(new WsCmFactura(ent.getWsCmFacturasId().getId()));
        neg.setIdDetalles(ent.getIdDetalles());
        neg.setTipologia(ent.getTipologia());
        neg.setConsecutivo(ent.getConsecutivo());
        neg.setCodigoMunicipio(ent.getCodigoMunicipio());
        neg.setConceptoContable(ent.getConceptoContable());
        neg.setValorOperacion(ent.getValorOperacion());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        return neg;
    }

    public static WsCmFacturaDetalles castNegocioEntidad(WsCmFacturaDetalle neg) {
        WsCmFacturaDetalles ent = new WsCmFacturaDetalles();
        ent.setId(neg.getId());
        ent.setWsCmFacturasId(new WsCmFacturas(neg.getWsCmFactura().getId()));
        ent.setIdDetalles(neg.getIdDetalles());
        ent.setTipologia(neg.getTipologia());
        ent.setConsecutivo(neg.getConsecutivo());
        ent.setCodigoMunicipio(neg.getCodigoMunicipio());
        ent.setConceptoContable(neg.getConceptoContable());
        ent.setValorOperacion(neg.getValorOperacion());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        return ent;
    }
}
