/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionEncabezado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmSincronizacionDetalles;
import com.saviasaludeps.savia.ejb.entidades.CmSincronizacionEncabezados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionDetalleRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author yjimenez
 */
@Stateless
@Remote(CmSincronizacionDetalleRemoto.class)
@Local(CmSincronizacionDetalleLocal.class)
public class CmSincronizacionDetalleServicio extends GenericoServicio implements CmSincronizacionDetalleLocal, CmSincronizacionDetalleRemoto {

    @Override
    public  int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception{
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cmsd) FROM CmSincronizacionDetalles cmsd "
                    + "WHERE cmsd.cmSincronizacionEncabezadosId.id = :cmSincronizacionEncabezadoId  ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoMunicipio":
                            strQuery += "AND cmsd.codigoMunicipio = " + (String) e.getValue() + " ";
                            break;
                        case "conceptoContable":
                            strQuery += "AND cmsd.conceptoContable = '" + (String) e.getValue() + "' ";
                            break;
                        case "valorOperacion":
                            strQuery += "AND cmsd.valorOperacion = '" + (String) e.getValue() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND cmsd.usuarioCrea = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += "AND cmsd.fechaHoraCrea = " + (String) e.getValue() + " ";
                            break;
                        case "idDetalles":
                            strQuery += "AND cmsd.idDetalles = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("cmSincronizacionEncabezadoId", paramConsulta.getParametroConsulta1())
                    .getSingleResult();
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
    public List<CmSincronizacionDetalle> consultarLista(ParamConsulta paramConsulta) throws Exception{
        List<CmSincronizacionDetalle> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmSincronizacionDetalles cmsd "
                    + "WHERE cmsd.cmSincronizacionEncabezadosId.id = :cmSincronizacionEncabezadoId ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                   switch ((String) e.getKey()) {
                        case "codigoMunicipio":
                            strQuery += "AND cmsd.codigoMunicipio = " + (String) e.getValue() + " ";
                            break;
                        case "conceptoContable":
                            strQuery += "AND cmsd.conceptoContable = '" + (String) e.getValue() + "' ";
                            break;
                        case "valorOperacion":
                            strQuery += "AND cmsd.valorOperacion = '" + (String) e.getValue() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND cmsd.usuarioCrea = '" + (String) e.getValue() + "' ";
                           break;
                       case "fechaHoraCrea":
                           strQuery += "AND cmsd.fechaHoraCrea = " + (String) e.getValue() + " ";
                           break;
                       case "idDetalles":
                           strQuery += "AND cmsd.idDetalles = " + (String) e.getValue() + " ";
                           break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "cmsd." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "cmsd.fechaHoraCrea DESC";
            }
            List<CmSincronizacionDetalles> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .setParameter("cmSincronizacionEncabezadoId", paramConsulta.getParametroConsulta1())
                    .getResultList();
            for (CmSincronizacionDetalles per : list) {
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
    public CmSincronizacionDetalle consultar(int id) throws Exception {
        CmSincronizacionDetalle obj = null;
        try {
            CmSincronizacionDetalles per = (CmSincronizacionDetalles) getEntityManager().find(CmSincronizacionDetalles.class, id);
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
    public List<CmSincronizacionDetalle> consultarDetallesEncabezado(int idEncabezado) throws Exception {
        List<CmSincronizacionDetalle> lista = new ArrayList();

        try {
            String query = "SELECT csd FROM CmSincronizacionDetalles csd WHERE csd.cmSincronizacionEncabezadosId='" + idEncabezado + "'";

            List<CmSincronizacionDetalles> resultado = getEntityManager().createQuery(query.toString()).getResultList();

            for (CmSincronizacionDetalles detalle : resultado) {
                lista.add(castEntidadNegocio(detalle));
            }

        } catch (NoResultException e) {
            lista = new ArrayList();
        } catch (Exception e) {
            lista = new ArrayList();
        } finally {
            cerrarEntityManager();
        }

        return lista;
    }

    @Override
    public int insertar(CmSincronizacionDetalle obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(CmSincronizacionDetalle obj) throws Exception {
        try {
            obj.setId((int) getEntityManager().merge(castNegocioEntidad(obj)).getId());
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CmSincronizacionDetalle eliminar(int id) throws Exception {
        CmSincronizacionDetalle obj = null;
        try {
            CmSincronizacionDetalles per = getEntityManager().find(CmSincronizacionDetalles.class, id);
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

    public static CmSincronizacionDetalles castNegocioEntidad(CmSincronizacionDetalle obj) {
        CmSincronizacionDetalles per = new CmSincronizacionDetalles();
        per.setId(obj.getId());
        per.setTipologia(obj.getTipologia());
        per.setCmSincronizacionEncabezadosId(new CmSincronizacionEncabezados(obj.getCmSincronizacionEncabezadosId().getId()));
        per.setConsecutivo(obj.getConsecutivo());
        per.setCodigoMunicipio(obj.getCodigoMunicipio());
        per.setConceptoContable(obj.getConceptoContable());
        per.setValorOperacion(obj.getValorOperacion());
        per.setIdDetalles(obj.getIdDetalles());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        return per;
    }

    private CmSincronizacionDetalle castEntidadNegocio(CmSincronizacionDetalles obj) {
        CmSincronizacionDetalle detalle = new CmSincronizacionDetalle();
        detalle.setId(obj.getId());
        detalle.setTipologia(obj.getTipologia());
        detalle.setCmSincronizacionEncabezadosId(new CmSincronizacionEncabezado(obj.getCmSincronizacionEncabezadosId().getId()));
        detalle.setConsecutivo(obj.getConsecutivo());
        detalle.setCodigoMunicipio(obj.getCodigoMunicipio());
        detalle.setConceptoContable(obj.getConceptoContable());
        detalle.setValorOperacion(obj.getValorOperacion());
        detalle.setIdDetalles(obj.getIdDetalles());
        detalle.setUsuarioCrea(obj.getUsuarioCrea());
        detalle.setTerminalCrea(obj.getTerminalCrea());
        detalle.setFechaHoraCrea(obj.getFechaHoraCrea());
        return detalle;
    }

}
