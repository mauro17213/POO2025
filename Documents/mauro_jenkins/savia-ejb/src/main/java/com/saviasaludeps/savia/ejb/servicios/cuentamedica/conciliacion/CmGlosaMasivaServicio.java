/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.ejb.entidades.CmGlosaMasiva;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaMasivaN;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmGlosaMasivaRemoto;
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
 * @author jeperez
 */
@Stateless
@Remote(CmGlosaMasivaRemoto.class)
@Local(CmGlosaMasivaLocal.class)
public class CmGlosaMasivaServicio extends GenericoServicio implements CmGlosaMasivaLocal, CmGlosaMasivaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cmd) FROM CmGlosaMasiva cmd ";
            strQuery += " WHERE cmd.id > 0 ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "numeroRadicado":
                            strQuery += " AND cmd.numeroRadicado LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nit":
                            strQuery += " AND cmd.nit LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estadoProceso":
                            strQuery += " AND cmd.estadoProceso = '" + e.getValue() + "' ";
                            break;
                        case "id":
                            strQuery += " AND cmd.id = '" + e.getValue() + "' ";
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
    public List<CmGlosaMasivaN> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmGlosaMasivaN> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmGlosaMasiva cmd ";
            strQuery += " WHERE cmd.id > 0  ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "numeroRadicado":
                            strQuery += " AND cmd.numeroRadicado LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nit":
                            strQuery += " AND cmd.nit LIKE '%" + e.getValue() + "%' ";
                        case "estadoProceso":
                            strQuery += " AND cmd.estadoProceso = '" + e.getValue() + "' ";
                            break;
                        case "id":
                            strQuery += " AND cmd.id = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }

            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {

                String order = paramConsulta.getOrden().replace("gsAfiliado", "gsAfiliadosId").
                        replace("gsZona", "gsZonasId");

                strQuery += " cmd." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " cmd.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);

            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fhInicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
                query.setParameter("fhFin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            }

            List<CmGlosaMasiva> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CmGlosaMasiva neg : list) {
                CmGlosaMasivaN glosaMasiva = castEntidadNegocio(neg);
                listResult.add(glosaMasiva);
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
    public CmGlosaMasivaN consultar(int id) throws Exception {
        CmGlosaMasivaN obj = null;
        try {
            CmGlosaMasiva per = (CmGlosaMasiva) getEntityManager().find(CmGlosaMasiva.class, id);
            if (per != null) {
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
    public int insertar(CmGlosaMasivaN obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al insertar la detalle");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(CmGlosaMasivaN obj) throws Exception {
        try {

            CmGlosaMasiva neg = castNegocioEntidad(obj);

            String hql = "UPDATE CmGlosaMasiva SET"
                    + " estadoProceso = :estadoProceso,"
                    + " cantidadFacturasConRespuestaGlosa  = :cantidadFacturasConRespuestaGlosa,"
                    + " cantidadFacturasConRatificacionGlosa = :cantidadFacturasConRatificacionGlosa,"
                    + " horaFinalizacionRegistro = :horaFinalizacionRegistro";
            hql += " WHERE id = :id";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estadoProceso", neg.getEstadoProceso());
            query.setParameter("cantidadFacturasConRespuestaGlosa", neg.getCantidadFacturasConRespuestaGlosa());
            query.setParameter("cantidadFacturasConRatificacionGlosa", neg.getCantidadFacturasConRatificacionGlosa());
            query.setParameter("horaFinalizacionRegistro", neg.getHoraFinalizacionRegistro());
            query.setParameter("id", obj.getId());
            query.executeUpdate();

        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }


    public static CmGlosaMasivaN castEntidadNegocio(CmGlosaMasiva ent) {
        CmGlosaMasivaN neg = new CmGlosaMasivaN();
        neg.setId(ent.getId());
        neg.setCntPrestadores(new CntPrestador(ent.getCntPrestadoresId().getId()));
        neg.setValorFacturas(ent.getValorFacturas());
        neg.setValorTotalPagadoEps(ent.getValorTotalPagadoEps());
        neg.setPorcentajePagadoEps(ent.getPorcentajePagadoEps());
        neg.setValorTotalAceptadoIps(ent.getValorTotalAceptadoIps());
        neg.setPorcentajeAceptadoIps(ent.getPorcentajeAceptadoIps());
        neg.setEstadoProceso(ent.getEstadoProceso());
        neg.setCantidadFacturas(ent.getCantidadFacturas());
        neg.setCantidadFacturasRespuestaGlosa(ent.getCantidadFacturasConRespuestaGlosa());
        neg.setCantidadFacturasRatificacionGlosa(ent.getCantidadFacturasConRatificacionGlosa());
        neg.setHoraFinalizacionRegistro(ent.getHoraFinalizacionRegistro());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        return neg;
    }

    public static CmGlosaMasiva castNegocioEntidad(CmGlosaMasivaN neg) {
        CmGlosaMasiva ent = new CmGlosaMasiva();
        ent.setId(neg.getId());
        ent.setCntPrestadoresId(new CntPrestadores(neg.getCntPrestadores().getId()));
        ent.setValorFacturas(neg.getValorFacturas());
        ent.setValorTotalPagadoEps(neg.getValorTotalPagadoEps());
        ent.setPorcentajePagadoEps(neg.getPorcentajePagadoEps());
        ent.setValorTotalAceptadoIps(neg.getValorTotalAceptadoIps());
        ent.setPorcentajeAceptadoIps(neg.getPorcentajeAceptadoIps());
        ent.setEstadoProceso(neg.getEstadoProceso());
        ent.setCantidadFacturas(neg.getCantidadFacturas());
        ent.setCantidadFacturasConRespuestaGlosa(neg.getCantidadFacturasRespuestaGlosa());
        ent.setCantidadFacturasConRatificacionGlosa(neg.getCantidadFacturasRatificacionGlosa());
        ent.setHoraFinalizacionRegistro(neg.getHoraFinalizacionRegistro());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        return ent;
    }


}
