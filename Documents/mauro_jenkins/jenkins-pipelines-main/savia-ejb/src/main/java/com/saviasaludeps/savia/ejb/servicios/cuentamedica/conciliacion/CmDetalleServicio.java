/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.conciliacion;


import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAutorizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCapitaDescuento;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaConceptoContable;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDiagnostico;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMotivoGlosa;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuestaDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaAutorizaciones;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaCapitaDescuentos;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaConceptosContables;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaDiagnosticos;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaMotivosGlosas;
import com.saviasaludeps.savia.ejb.entidades.CmDetalles;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.CmFacturas;
import com.saviasaludeps.savia.ejb.entidades.CmGlosaRespuestaDetalles;
import com.saviasaludeps.savia.ejb.entidades.MaInsumosMipres;
import com.saviasaludeps.savia.ejb.entidades.MaTecnologiasMipres;
import com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria.CmAuditoriaAutorizacionServicio;
import com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria.CmAuditoriaConceptoContableServicio;
import com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria.CmAuditoriaDescuentoCapitaServicio;
import com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria.CmAuditoriaDiagnosticoServicio;
import com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria.CmAuditoriaMotivosGlosaServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmDetalleRemoto;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(CmDetalleRemoto.class)
@Local(CmDetalleLocal.class)
public class CmDetalleServicio extends GenericoServicio implements CmDetalleLocal, CmDetalleRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cmd) FROM CmDetalles cmd ";
            strQuery += " WHERE cmd.id > 0  ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "numeroRadicado":
                            strQuery += " AND cmd.numeroRadicado LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nit":
                            strQuery += " AND cmd.nit LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "ips":
                            strQuery += " AND cmd.ips LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "numeroFacturado":
                            strQuery += " AND cmd.numeroFacturado LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "fechaPrestacion":
                            strQuery += " AND cmd.fechaPrestacion = " + e.getValue() + " ";
                            break;
                        case "tipoContrato":
                            strQuery += " AND cmd.tipoContrato = '" + e.getValue() + "' ";
                            break;
                        case "regimen":
                            strQuery += " AND cmd.regimen = '" + e.getValue() + "' ";
                            break;
                        case "valorFactura":
                            strQuery += " AND cmd.valorFactura  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "valorInicialGlosa":
                            strQuery += " AND cmd.valorInicialGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "valorPendienteActual":
                            strQuery += " AND cmd.valorPendienteActual LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "valorPendienteGlosa":
                            strQuery += " AND cmd.cmGlosasList.valorPendiente LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estadoFactura":
                            strQuery += " AND cmd.estadoFactura = '" + e.getValue() + "' ";
                            break;
                        case "cmDetalleFactura.nombreCompletoAfiliado":
                            strQuery += "";
                            break;
                        case "cmDetalleFactura.documento":
                            strQuery += "";
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
    public List<CmDetalle> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmDetalle> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmDetalles cmd ";
            strQuery += " WHERE 1= 1  ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "numeroRadicado":
                            strQuery += " AND cmd.numeroRadicado LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nit":
                            strQuery += " AND cmd.nit LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "ips":
                            strQuery += " AND cmd.ips LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "numeroFacturado":
                            strQuery += " AND cmd.numeroFacturado LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "fechaPrestacion":
                            strQuery += " AND cmd.fechaPrestacion = " + e.getValue() + " ";
                            break;
                        case "tipoContrato":
                            strQuery += " AND cmd.tipoContrato = '" + e.getValue() + "' ";
                            break;
                        case "regimen":
                            strQuery += " AND cmd.regimen = '" + e.getValue() + "' ";
                            break;
                        case "valorFactura":
                            strQuery += " AND cmd.valorFactura LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "valorInicialGlosa":
                            strQuery += " AND cmd.valorInicialGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "valorPendienteGlosa":
                            strQuery += " AND cmd.cmGlosasList.valorPendiente LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "valorPendienteActual":
                            strQuery += " AND cmd.valorPendienteActual LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estadoFactura":
                            strQuery += " AND cmd.estadoFactura = '" + e.getValue() + "' ";
                            break;
                        case "cmDetalleFactura.nombreCompletoAfiliado":
                            strQuery += "";
                            break;
                        case "cmDetalleFactura.documento":
                            strQuery += "";
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
                strQuery += " cmd.fechaHoraCrea DESC,  cmd.id DESC ";
            }
            Query query = getEntityManager().createQuery(strQuery);

            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fhInicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
                query.setParameter("fhFin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            }

            List<CmDetalles> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CmDetalles neg : list) {
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
    public int consultarCantidadListaDetallesPorFactura(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cmd) FROM CmDetalles cmd ";
            strQuery += " WHERE cmd.cmFacturasId.id = :facuturaId ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoServicio":
                            strQuery += " AND cmd.codigoServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreServicio":
                            strQuery += " AND cmd.nombreServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "documento":
                            strQuery += " AND cmd.documento LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicadoGlosa":
                            strQuery += " AND cmd.radicadoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "motivoGlosa":
                            strQuery += " AND cmd.motivoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "conceptoContable":
                            strQuery += " AND cmd.conceptoContable LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreDx":
                            strQuery += " AND cmd.nombreDx LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "id":
                            strQuery += " AND cmd.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maServicioCodigo":
                            strQuery += " AND cmd.maServicioCodigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maServicioValor":
                            strQuery += " AND cmd.maServicioValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "tipoServicio":
                            strQuery += " AND cmd.tipoServicio  = '" + e.getValue() + "' ";
                            break;
                        case "nombreCompletoAfiliado":
                            strQuery += " AND cmd.nombreCompletoAfiliado  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "fechaPrestacion":
                            strQuery += " AND cmd.fechaPrestacion  BETWEEN '" + e.getValue() + " 00:00:00' "
                                      + " AND '"+e.getValue() +" 23:59:59'";
                            break;
                    }
                }
            }

            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += " AND cmd.valorPendienteActual > 0 ";
            }
            
            if (paramConsulta.getParametroConsulta4() != null) {
                strQuery += " AND cmd.id IN ("+paramConsulta.getParametroConsulta4()+")";
            }

            cant = (int) (long) getEntityManager().createQuery(strQuery).
                    setParameter("facuturaId", paramConsulta.getParametroConsulta1()).getSingleResult();

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
    public List<CmDetalle> consultarListaDetallesPorFactura(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CmDetalle> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmDetalles cmd ";
            strQuery += " WHERE cmd.cmFacturasId.id = :facuturaId";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoServicio":
                            strQuery += " AND cmd.codigoServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreServicio":
                            strQuery += " AND cmd.nombreServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "documento":
                            strQuery += " AND cmd.documento LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicadoGlosa":
                            strQuery += " AND cmd.radicadoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "motivoGlosa":
                            strQuery += " AND cmd.motivoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "conceptoContable":
                            strQuery += " AND cmd.conceptoContable LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreDx":
                            strQuery += " AND cmd.nombreDx LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "id":
                            strQuery += " AND cmd.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maServicioCodigo":
                            strQuery += " AND cmd.maServicioCodigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maServicioValor":
                            strQuery += " AND cmd.maServicioValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "tipoServicio":
                            strQuery += " AND cmd.tipoServicio  = '" + e.getValue() + "' ";
                            break;
                        case "nombreCompletoAfiliado":
                            strQuery += " AND cmd.nombreCompletoAfiliado  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "fechaPrestacion":
                            strQuery += " AND cmd.fechaPrestacion  BETWEEN '" + e.getValue() + " 00:00:00' "
                                     + " AND '"+e.getValue() +" 23:59:59'";
                            break;
                    }
                }
            }

            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += " AND cmd.valorPendienteActual > 0 ";
            }
            
            if (paramConsulta.getParametroConsulta4() != null) {
                strQuery += " AND cmd.id IN ( "+paramConsulta.getParametroConsulta4()+")";
            }

            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {

                String order = paramConsulta.getOrden().replace("cmGlosas", "cmGlosasId").
                        replace("gsZona", "gsZonasId");
                strQuery += " cmd." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " cmd.fechaHoraCrea DESC , cmd.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);

            query.setParameter("facuturaId", paramConsulta.getParametroConsulta1());

            List<CmDetalles> list;

            if (paramConsulta.getParametroConsulta2() != null) {
                list = query
                        .getResultList();
            } else {
                list = query
                        .setFirstResult(paramConsulta.getPrimerRegistro())
                        .setMaxResults(paramConsulta.getRegistrosPagina())
                        .getResultList();
            }
            
            for (CmDetalles per : list) {
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
    public List<CmDetalle> consultarTodosDetallesPorFactura(int idFactura) throws java.lang.Exception {
        List<CmDetalle> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmDetalles cmd ";
            strQuery += " WHERE cmd.cmFacturasId.id = :facuturaId ORDER BY cmd.id DESC ";
            Query query = getEntityManager().createQuery(strQuery);

            query.setParameter("facuturaId", idFactura);

            List<CmDetalles> list = query
                        .getResultList();
   
            for (CmDetalles per : list) {
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
    public List<CmDetalle> consultarListaDetallesPorFacturaHistorico(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CmDetalle> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmDetalles cmd ";
            strQuery += " WHERE cmd.cmFacturasId.id = :facuturaId";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoServicio":
                            strQuery += " AND cmd.codigoServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreServicio":
                            strQuery += " AND cmd.nombreServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "documento":
                            strQuery += " AND cmd.documento LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicadoGlosa":
                            strQuery += " AND cmd.radicadoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "motivoGlosa":
                            strQuery += " AND cmd.motivoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "conceptoContable":
                            strQuery += " AND cmd.conceptoContable LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreDx":
                            strQuery += " AND cmd.nombreDx LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "id":
                            strQuery += " AND cmd.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maServicioCodigo":
                            strQuery += " AND cmd.maServicioCodigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maServicioValor":
                            strQuery += " AND cmd.maServicioValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "tipoServicio":
                            strQuery += " AND cmd.tipoServicio  = '" + e.getValue() + "' ";
                            break;
                        case "nombreCompletoAfiliado":
                            strQuery += " AND cmd.nombreCompletoAfiliado  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "fechaPrestacion":
                            strQuery += " AND cmd.fechaPrestacion  BETWEEN '" + e.getValue() + " 00:00:00' "
                                     + " AND '"+e.getValue() +" 23:59:59'";
                            break;
                    }
                }
            }

            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += " AND cmd.valorPendienteActual > 0 ";
            }
            
            if (paramConsulta.getParametroConsulta4() != null) {
                strQuery += " AND cmd.id IN ( "+paramConsulta.getParametroConsulta4()+")";
            }

            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {

                String order = paramConsulta.getOrden().replace("cmGlosas", "cmGlosasId").
                        replace("gsZona", "gsZonasId");
                strQuery += " cmd." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " cmd.fechaHoraCrea DESC , cmd.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);

            query.setParameter("facuturaId", paramConsulta.getParametroConsulta1());

            List<CmDetalles> list;

            if (paramConsulta.getParametroConsulta2() != null) {
                list = query
                        .getResultList();
            } else {
                list = query
                        .setFirstResult(paramConsulta.getPrimerRegistro())
                        .setMaxResults(paramConsulta.getRegistrosPagina())
                        .getResultList();
            }
            
            for (CmDetalles per : list) {
                listResult.add(castEntidadNegocioHistorico(per));
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
    public List<CmDetalle> consultarPorEstadoAutorizacion(String idFacturas,String tiposAutorizacion) throws java.lang.Exception {
        List<CmDetalle> listResult = new ArrayList();
        try {
            if (idFacturas != null && tiposAutorizacion != null) {
                
                String strQuery = "FROM CmAuditoriaAutorizaciones cma ";
                strQuery += " WHERE cma.auAnexos4Id.estado IN(" + tiposAutorizacion + ") AND"
                        + "  cma.cmFacturasId.id IN(" + idFacturas + ") ";

                Query query = getEntityManager().createQuery(strQuery);
                List<CmAuditoriaAutorizaciones> list = query
                        .getResultList();

                for (CmAuditoriaAutorizaciones per : list) {
                    listResult.add(castEntidadNegocioHistorico(per.getCmDetallesId()));
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
    public List<CmDetalle> consultarDetallesPorFacturaSoloValores(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CmDetalle> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmDetalles cmd ";
            strQuery += " WHERE cmd.cmFacturasId.id = :facuturaId";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoServicio":
                            strQuery += " AND cmd.codigoServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreServicio":
                            strQuery += " AND cmd.nombreServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "documento":
                            strQuery += " AND cmd.documento LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicadoGlosa":
                            strQuery += " AND cmd.radicadoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "motivoGlosa":
                            strQuery += " AND cmd.motivoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "conceptoContable":
                            strQuery += " AND cmd.conceptoContable LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreDx":
                            strQuery += " AND cmd.nombreDx LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "id":
                            strQuery += " AND cmd.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maServicioCodigo":
                            strQuery += " AND cmd.maServicioCodigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maServicioValor":
                            strQuery += " AND cmd.maServicioValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "tipoServicio":
                            strQuery += " AND cmd.tipoServicio  = '" + e.getValue() + "' ";
                            break;
                        case "nombreCompletoAfiliado":
                            strQuery += " AND cmd.nombreCompletoAfiliado  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "fechaPrestacion":
                            strQuery += " AND cmd.fechaPrestacion  BETWEEN '" + e.getValue() + " 00:00:00' "
                                    + " AND '" + e.getValue() + " 23:59:59'";
                            break;
                    }
                }
            }

            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += " AND cmd.valorPendienteActual > 0 ";
            }

            if (paramConsulta.getParametroConsulta4() != null) {
                strQuery += " AND cmd.id IN ( " + paramConsulta.getParametroConsulta4() + ")";
            }

            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {

                String order = paramConsulta.getOrden().replace("cmGlosas", "cmGlosasId").
                        replace("gsZona", "gsZonasId");
                strQuery += " cmd." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " cmd.fechaHoraCrea DESC , cmd.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);

            query.setParameter("facuturaId", paramConsulta.getParametroConsulta1());

            List<CmDetalles> list;

            if (paramConsulta.getParametroConsulta2() != null) {
                list = query
                        .getResultList();
            } else {
                list = query
                        .setFirstResult(paramConsulta.getPrimerRegistro())
                        .setMaxResults(paramConsulta.getRegistrosPagina())
                        .getResultList();
            }

            listResult = obtenerDetalleValoresSegunCast(list, paramConsulta);
            
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
    public int consultarCantidadListaDetallesMultiFactura(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            
            if (paramConsulta.getParametroConsulta1() != null) {

                String strQuery = "SELECT COUNT(cmd) FROM CmDetalles cmd ";
                strQuery += " WHERE cmd.cmFacturasId.id IN( "+paramConsulta.getParametroConsulta1()+" ) ";

                if (paramConsulta.getFiltros() != null) {
                    for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                        switch ((String) e.getKey()) {
                            case "codigoServicio":
                                strQuery += " AND cmd.codigoServicio LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "nombreServicio":
                                strQuery += " AND cmd.nombreServicio LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "documento":
                                strQuery += " AND cmd.documento LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "radicadoGlosa":
                                strQuery += " AND cmd.radicadoGlosa LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "motivoGlosa":
                                strQuery += " AND cmd.motivoGlosa LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "conceptoContable":
                                strQuery += " AND cmd.conceptoContable LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "nombreDx":
                                strQuery += " AND cmd.nombreDx LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "id":
                                strQuery += " AND cmd.id LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "maServicioCodigo":
                                strQuery += " AND cmd.maServicioCodigo LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "maServicioValor":
                                strQuery += " AND cmd.maServicioValor LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "tipoServicio":
                                strQuery += " AND cmd.tipoServicio  = '" + e.getValue() + "' ";
                                break;
                            case "nombreCompletoAfiliado":
                                strQuery += " AND cmd.nombreCompletoAfiliado  LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "fechaPrestacion":
                                strQuery += " AND cmd.fechaPrestacion  BETWEEN '" + e.getValue() + " 00:00:00' "
                                        + " AND '" + e.getValue() + " 23:59:59'";
                                break;
                            case "cmFacturas.id":
                                strQuery += " AND cmd.cmFacturasId.id LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "cmFacturas.numeroFacturado":
                                strQuery += " AND cmd.cmFacturasId.numeroFacturado LIKE '%" + e.getValue() + "%' ";
                                break;
                        }
                    }
                }

                if (paramConsulta.getParametroConsulta3() != null) {
                    strQuery += " AND cmd.valorPendienteActual > 0 ";
                }

                if (paramConsulta.getParametroConsulta4() != null) {
                    strQuery += " AND cmd.id IN (" + paramConsulta.getParametroConsulta4() + ")";
                }

                cant = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
            }
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
    public List<CmDetalle> consultarListaDetallesMultiFactura(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CmDetalle> listResult = new ArrayList();
        try {
            
            if (paramConsulta.getParametroConsulta1() != null) {

                String strQuery = "FROM CmDetalles cmd ";
                strQuery += " WHERE cmd.cmFacturasId.id IN( "+paramConsulta.getParametroConsulta1()+" )";

                if (paramConsulta.getFiltros() != null) {
                    for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                        switch ((String) e.getKey()) {
                            case "codigoServicio":
                                strQuery += " AND cmd.codigoServicio LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "nombreServicio":
                                strQuery += " AND cmd.nombreServicio LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "documento":
                                strQuery += " AND cmd.documento LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "radicadoGlosa":
                                strQuery += " AND cmd.radicadoGlosa LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "motivoGlosa":
                                strQuery += " AND cmd.motivoGlosa LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "conceptoContable":
                                strQuery += " AND cmd.conceptoContable LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "nombreDx":
                                strQuery += " AND cmd.nombreDx LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "id":
                                strQuery += " AND cmd.id LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "maServicioCodigo":
                                strQuery += " AND cmd.maServicioCodigo LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "maServicioValor":
                                strQuery += " AND cmd.maServicioValor LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "tipoServicio":
                                strQuery += " AND cmd.tipoServicio  = '" + e.getValue() + "' ";
                                break;
                            case "nombreCompletoAfiliado":
                                strQuery += " AND cmd.nombreCompletoAfiliado  LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "fechaPrestacion":
                                strQuery += " AND cmd.fechaPrestacion  BETWEEN '" + e.getValue() + " 00:00:00' "
                                        + " AND '" + e.getValue() + " 23:59:59'";
                                break;
                            case "cmFacturas.id":
                                strQuery += " AND cmd.cmFacturasId.id LIKE '%" + e.getValue() + "%' ";
                                break;
                            case "cmFacturas.numeroFacturado":
                                strQuery += " AND cmd.cmFacturasId.numeroFacturado LIKE '%" + e.getValue() + "%' ";
                                break;
                        }
                    }
                }

                if (paramConsulta.getParametroConsulta3() != null) {
                    strQuery += " AND cmd.valorPendienteActual > 0 ";
                }

                if (paramConsulta.getParametroConsulta4() != null) {
                    strQuery += " AND cmd.id IN ( " + paramConsulta.getParametroConsulta4() + ")";
                }

                strQuery += " ORDER BY ";
                if (paramConsulta.getOrden() != null) {

                    String order = paramConsulta.getOrden().replace("cmGlosas", "cmGlosasId").
                            replace("gsZona", "gsZonasId").replace("cmFacturas.id", "cmFacturasId.id").
                            replace("cmFacturas.numeroFacturado", "cmFacturasId.numeroFacturado");
                    strQuery += " cmd." + order + " "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else {
                    strQuery += " cmd.fechaHoraCrea DESC , cmd.id DESC";
                }
                Query query = getEntityManager().createQuery(strQuery);

                List<CmDetalles> list;

                if (paramConsulta.getParametroConsulta2() != null) {
                    list = query
                            .getResultList();
                } else {
                    list = query
                            .setFirstResult(paramConsulta.getPrimerRegistro())
                            .setMaxResults(paramConsulta.getRegistrosPagina())
                            .getResultList();
                }

               for (CmDetalles per : list) {
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
    public List<CmDetalle> consultarTodosDetallesMultiFactura(String idsFacturas) throws java.lang.Exception {
          List<CmDetalle> listResult = new ArrayList();
          try {

              if (idsFacturas != null) {

                  String strQuery = "FROM CmDetalles cmd ";
                  strQuery += " WHERE cmd.cmFacturasId.id IN( " + idsFacturas + " )";
                  strQuery += " ORDER BY id DESC";
                  Query query = getEntityManager().createQuery(strQuery);

                  List<CmDetalles> list = query
                          .getResultList();

                  for (CmDetalles per : list) {
                      listResult.add(castEntidadNegocioCorto(per));
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
    public List<CmDetalle> consultarListaDetallesMultiFacturaFaltoInsumosParaAuditar(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CmDetalle> listResult = new ArrayList();
        List<CmDetalles> list;
        try {

            if (paramConsulta.getParametroConsulta1() != null) {

                String strQuery = "FROM CmDetalles cmd ";
                strQuery += " WHERE cmd.cmFacturasId.id IN( " + paramConsulta.getParametroConsulta1() + " ) ";
                strQuery +=  " and ( (cmd.aplicaConcepto is null or cmd.aplicaConcepto = 0) or (cmd.aplicaDx is null or cmd.aplicaDx = 0) )  ";
                strQuery += " ORDER BY cmd.id DESC  ";
                Query query = getEntityManager().createQuery(strQuery);
       
                list = query
                            .setFirstResult(0)
                            .setMaxResults(1)
                            .getResultList();
     
                for (CmDetalles per : list) {
                    listResult.add(castEntidadNegocioMultiFacturaValoresInsumosAuditoria(per));
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
    public List<CmDetalle> consultarListaDetallesPorFacturaCastCorto(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CmDetalle> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmDetalles cmd ";
            strQuery += " WHERE cmd.cmFacturasId.id = :facuturaId";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoServicio":
                            strQuery += " AND cmd.codigoServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreServicio":
                            strQuery += " AND cmd.nombreServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "documento":
                            strQuery += " AND cmd.documento LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicadoGlosa":
                            strQuery += " AND cmd.radicadoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "motivoGlosa":
                            strQuery += " AND cmd.motivoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "conceptoContable":
                            strQuery += " AND cmd.conceptoContable LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreDx":
                            strQuery += " AND cmd.nombreDx LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "id":
                            strQuery += " AND cmd.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maServicioCodigo":
                            strQuery += " AND cmd.maServicioCodigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maServicioValor":
                            strQuery += " AND cmd.maServicioValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "tipoServicio":
                            strQuery += " AND cmd.tipoServicio  = '" + e.getValue() + "' ";
                            break;
                        case "nombreCompletoAfiliado":
                            strQuery += " AND cmd.nombreCompletoAfiliado  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "fechaPrestacion":
                            strQuery += " AND cmd.fechaPrestacion  BETWEEN '" + e.getValue() + " 00:00:00' "
                                     + " AND '"+e.getValue() +" 23:59:59'";
                            break;
                    }
                }
            }

            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += " AND cmd.valorPendienteActual > 0 ";
            }
            
            if (paramConsulta.getParametroConsulta4() != null) {
                strQuery += " AND cmd.id IN ( "+paramConsulta.getParametroConsulta4()+")";
            }

            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {

                String order = paramConsulta.getOrden().replace("cmGlosas", "cmGlosasId").
                        replace("gsZona", "gsZonasId");
                strQuery += " cmd." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " cmd.fechaHoraCrea DESC , cmd.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);

            query.setParameter("facuturaId", paramConsulta.getParametroConsulta1());

            List<CmDetalles> list;

            if (paramConsulta.getParametroConsulta2() != null) {
                list = query
                        .getResultList();
            } else {
                list = query
                        .setFirstResult(paramConsulta.getPrimerRegistro())
                        .setMaxResults(paramConsulta.getRegistrosPagina())
                        .getResultList();
            }
            
            for (CmDetalles per : list) {
                listResult.add(castEntidadNegocioCastCorto(per));
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
    public CmDetalle consultar(int id) throws Exception {
        CmDetalle obj = null;
        try {
            CmDetalles per = (CmDetalles) getEntityManager().find(CmDetalles.class, id);
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
    public int insertar(CmDetalle obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al insertar el detalle");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(CmDetalle obj) throws Exception {
        try {

            CmDetalles detalles = castNegocioEntidad(obj);

            String hql = "UPDATE CmDetalles SET"
                    + " cmFacturasId.id = :cmFacturasId,"
                    + " maeTipoDocumentoId = :maeTipoDocumentoId,"
                    + " maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo,"
                    + " maeTipoDocumentoValor = :maeTipoDocumentoValor,"
                    + " documento  = :documento,"
                    + " nombreCompletoAfiliado = :nombreCompletoAfiliado,"
                    + " consecutivoItem = :consecutivoItem,"
                    + " maServicioId = :maServicioId,"
                    + " maServicioCodigo = :maServicioCodigo,"
                    + " maServicioValor = :maServicioValor,"
                    + " radicadoGlosa = :radicadoGlosa,"
                    + " valorCopago = :valorCopago,"
                    + " valorFacturado = :valorFacturado,"
                    + " valorPagado = :valorPagado,"
                    + " valorPendiente = :valorPendiente,"
                    + " valorPendienteActual = :valorPendienteActual,"
                    + " valorAceptadoIps = :valorAceptadoIps,"
                    + " valorPagadoEps = :valorPagadoEps,"
                    + " porcentajePagadoEps = :porcentajePagadoEps,"
                    + " porcentajeAceptadoIps = :porcentajeAceptadoIps,"
                    + " observacion = :observacion,"
                    + " observacionRespuestaDetalles = :observacionRespuestaDetalles,"
                    + " aplicaAc = :aplicaAc,"
                    + " aplicaDc = :aplicaDc,"
                    + " aplicaPbs = :aplicaPbs,"
                    + " aplicaGlosa = :aplicaGlosa,"
                    + " aplicaConcepto = :aplicaConcepto,"
                    + " aplicaDx = :aplicaDx,"
                    + " valorGlosa = :valorGlosa,"
                    + " conceptoContable = :conceptoContable,"
                    + " nombreDx = :nombreDx,"
                    + " motivoGlosa = :motivoGlosa,"
                    + " aplicaAutorizacion = :aplicaAutorizacion,"
                    + " estado = :estado,"
                    + " tipoServicio = :tipoServicio,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("cmFacturasId", detalles.getCmFacturasId().getId());
            query.setParameter("maeTipoDocumentoId", detalles.getMaeTipoDocumentoId());
            query.setParameter("maeTipoDocumentoCodigo", detalles.getMaeTipoDocumentoCodigo());
            query.setParameter("maeTipoDocumentoValor", detalles.getMaeTipoDocumentoValor());
            query.setParameter("documento", detalles.getDocumento());
            query.setParameter("nombreCompletoAfiliado", detalles.getNombreCompletoAfiliado());
            query.setParameter("consecutivoItem", detalles.getConsecutivoItem());
            query.setParameter("maServicioId", detalles.getMaServicioId());
            query.setParameter("maServicioCodigo", detalles.getMaServicioCodigo());
            query.setParameter("maServicioValor", detalles.getMaServicioValor());
            query.setParameter("radicadoGlosa", detalles.getRadicadoGlosa());
            query.setParameter("valorCopago", detalles.getValorCopago());
            query.setParameter("valorFacturado", detalles.getValorFacturado());
            query.setParameter("valorPagado", detalles.getValorPagado());
            query.setParameter("valorPendiente", detalles.getValorPendiente());
            query.setParameter("valorPendienteActual", detalles.getValorPendienteActual());
            query.setParameter("valorAceptadoIps", detalles.getValorAceptadoIps());
            query.setParameter("valorPagadoEps", detalles.getValorPagadoEps());
            query.setParameter("porcentajePagadoEps", detalles.getPorcentajePagadoEps());
            query.setParameter("porcentajeAceptadoIps", detalles.getPorcentajeAceptadoIps());
            query.setParameter("observacion", detalles.getObservacion());
            query.setParameter("observacionRespuestaDetalles", detalles.getObservacionRespuestaDetalles());
            query.setParameter("aplicaAc", detalles.getAplicaAc());
            query.setParameter("aplicaDc", detalles.getAplicaDc());
            query.setParameter("aplicaPbs", detalles.getAplicaPbs());
            query.setParameter("aplicaGlosa", detalles.getAplicaGlosa());
            query.setParameter("aplicaConcepto", detalles.getAplicaConcepto());
            query.setParameter("aplicaDx", detalles.getAplicaDx());
            query.setParameter("valorGlosa", detalles.getValorGlosa());
            query.setParameter("conceptoContable", StringUtils.abbreviate(detalles.getConceptoContable(), 1023));
            query.setParameter("nombreDx", StringUtils.abbreviate(detalles.getNombreDx(), 1023));
            query.setParameter("motivoGlosa", StringUtils.abbreviate(detalles.getMotivoGlosa(), 1023));
            query.setParameter("aplicaAutorizacion", detalles.getAplicaAutorizacion());
            query.setParameter("estado", detalles.getEstado());
            query.setParameter("tipoServicio", detalles.getTipoServicio());
            query.setParameter("usuarioModifica", detalles.getUsuarioModifica());
            query.setParameter("terminalModifica", detalles.getTerminalModifica());
            query.setParameter("fechaHoraModifica", detalles.getFechaHoraModifica());

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
    public void actualizarExistenciaGlosado(CmDetalle obj) throws Exception {
        try {

            if (obj != null) {
                String hql = "UPDATE CmDetalles SET"
                        + " aplicaGlosa = :aplicaGlosa,"
                        + " aplicaAutorizacion = :aplicaAutorizacion,"
                        + " aplicaConcepto = :aplicaConcepto,"
                        + " conceptoContable = :conceptoContable,"
                        + " observacion = :observacion,"
                        + " motivoGlosa = :motivoGlosa"
                        + " WHERE id = :id";

                Query query = getEntityManager().createQuery(hql);

                query.setParameter("aplicaGlosa", obj.getAplicaGlosa());
                query.setParameter("aplicaAutorizacion", obj.getAplicaAutorizacion());
                query.setParameter("aplicaConcepto", obj.getAplicaConcepto());
                query.setParameter("conceptoContable", obj.getConceptoContable());
                query.setParameter("observacion", obj.getObservacion());
                query.setParameter("motivoGlosa", obj.getMotivoGlosa());
                query.setParameter("id", obj.getId());
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
    public void actualizarValorPendienteActual(CmDetalle obj) throws java.lang.Exception {
        try {

            String hql = "UPDATE CmDetalles SET"
                    + " valorPendienteActual = :valorPendienteActual,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("valorPendienteActual", obj.getValorPendienteActual());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
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
    public void actualizarCopagoNoEfectivo(String idsCmDetalle, boolean marcacion) throws java.lang.Exception {
        try {

            idsCmDetalle = idsCmDetalle.replaceAll("'", "").replaceAll("\\*", "");
            String hql = "UPDATE CmDetalles SET "
                    + " copagoNoEfectivo = :marcacion"
                    + " WHERE id IN ( "+idsCmDetalle+" )  ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("marcacion", marcacion);
            query.executeUpdate();

        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarRecobro(String idsCmDetalle, boolean hayRecobro) throws java.lang.Exception {
        try {

            idsCmDetalle = idsCmDetalle.replaceAll("'", "").replaceAll("\\*", "");
            String hql = "UPDATE CmDetalles SET "
                    + " aplicaRecobro = :recobro"
                    + " WHERE id IN ( "+idsCmDetalle+" )  ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("recobro", hayRecobro);
            query.executeUpdate();

        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
      
    @Override
    public BigDecimal totalizarValorFacturadoDetallesPorFactura(ParamConsulta paramConsulta) throws Exception {
        BigDecimal valorTotal = new BigDecimal(BigInteger.ZERO);
        BigDecimal valorSumaFacturado = new BigDecimal(BigInteger.ZERO);
        BigDecimal valorSumaCopago = new BigDecimal(BigInteger.ZERO);
        try {
            if ( paramConsulta.getParametroConsulta1() != null ) {
                String strQuery = "SELECT SUM(cmd.valorFacturado) AS totalFacturado, "
                                      + " SUM(cmd.valorCopago) AS totalCopago FROM CmDetalles cmd ";
                strQuery += " WHERE cmd.cmFacturasId.id = :facuturaId ";
                List<Object> list =  getEntityManager().createQuery(strQuery).
                        setParameter("facuturaId", paramConsulta.getParametroConsulta1()).getResultList();
                
                Object sumas[ ];
                if(list != null && list.size() > 0 ){
                     sumas = (Object[]) list.get(0);
                     valorSumaFacturado = (BigDecimal) sumas[0];
                     valorSumaCopago = (BigDecimal) sumas[1];
                }
                
                valorSumaFacturado = valorSumaFacturado == null ? new BigDecimal(BigInteger.ZERO) : valorSumaFacturado;
                valorSumaCopago = valorSumaCopago == null ? new BigDecimal(BigInteger.ZERO) : valorSumaCopago;
                valorTotal = valorSumaFacturado.add(valorSumaCopago).setScale(2, RoundingMode.DOWN);

                if (paramConsulta.getParametroConsulta2() != null) {
                    valorTotal = valorSumaFacturado.setScale(2, RoundingMode.DOWN);
                }   
            }
        } catch (NoResultException e) {
            valorTotal = new BigDecimal(BigInteger.ZERO);
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return valorTotal;
    }
    
    @Override
    public BigDecimal totalizarValorGlosadoDetallesPorFactura(ParamConsulta paramConsulta) throws Exception {
        BigDecimal valorTotalGlosado = new BigDecimal(BigInteger.ZERO);
        try {
             if ( paramConsulta.getParametroConsulta1() != null ) {
                 String strQuery = "SELECT SUM(cmd.valorGlosa) AS totalGlosado "
                                + " FROM CmDetalles cmd ";
                strQuery += " WHERE cmd.cmFacturasId.id = :facuturaId ";
                List<Object> list =  getEntityManager().createQuery(strQuery).
                        setParameter("facuturaId", paramConsulta.getParametroConsulta1()).getResultList();
                
                if(list != null && list.size() > 0 ){
                   valorTotalGlosado  = (BigDecimal) list.get(0); 
                }
             }
        } catch (NoResultException e) {
            valorTotalGlosado = new BigDecimal(BigInteger.ZERO);
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return valorTotalGlosado;
    }
    
    @Override
    public int cantidadConsultarPorDocumento(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
        String strQuery = " SELECT  count(distinct cmd.cm_facturas_id) as cantidad "
                        + " FROM cm_detalles cmd JOIN cm_facturas cmf ON cmf.id = cmd.cm_facturas_id "
                        + " WHERE cmd.mae_tipo_documento_id = ? and cmd.documento = ? ";

            javax.persistence.Query nativeQuery = getEntityManager().createNativeQuery(strQuery);
            BigInteger count  =  (BigInteger) nativeQuery.
                    setParameter(1, paramConsulta.getParametroConsulta1()).
                    setParameter(2, paramConsulta.getParametroConsulta2()).
                    getSingleResult();
            
            cantidad = (int) count.longValue();

        } catch (NoResultException e) {
           cantidad = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cantidad;
    }
    
    @Override
    public List<CmDetalle> consultarPorDocumento(ParamConsulta paramConsulta) throws Exception {
        List<CmDetalle> listaResultados = new ArrayList();
        try {

            List<Object[]> results;

            String strQuery = "SELECT  distinct cmd.cm_facturas_id, cmf.numero_radicado,"
                    + " cmf.numero_facturado, cmf.nit ,cmf.ips, cmf.fecha_radicacion, "
                    + " cmf.valor_factura, cmd.nombre_completo_afiliado, cmf.mae_tipo_contrato_valor, cmf.estado_factura, cma.au_anexos4_id, cmd.mae_tipo_documento_valor, cmd.documento "
                    + " FROM cm_detalles cmd Join cm_facturas cmf ON cmf.id = cmd.cm_facturas_id "
                    + " LEFT JOIN cm_auditoria_autorizaciones cma ON cma.cm_detalles_id = cmd.id "
                    + " WHERE cmd.mae_tipo_documento_id = ? and cmd.documento = ? ";

            javax.persistence.Query nativeQuery = getEntityManager().createNativeQuery(strQuery);
            results = nativeQuery
                    .setParameter(1, paramConsulta.getParametroConsulta1())
                    .setParameter(2, paramConsulta.getParametroConsulta2())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();

            for (Object[] result : results) {
                CmDetalle cmDetalle = new CmDetalle();
                CmFactura cmFactura = new CmFactura();
                cmFactura.setId((Integer) result[0]);
                cmFactura.setNumeroRadicado((int) result[1]);
                cmFactura.setNumeroFacturado((String) result[2]);
                cmFactura.setNit((String) result[3]);
                cmFactura.setIps((String) result[4]);
                cmFactura.setFechaRadicacion((Date) result[5]);
                cmFactura.setValorFactura((BigDecimal) result[6]);
                cmDetalle.setNombreCompletoAfiliado((String) result[7]);
                cmFactura.setMaeTipoContratoValor((String) result[8]);
                cmFactura.setEstadoFactura((int) result[9]);
                cmDetalle.setNumeroAutorizacion(result[10] != null ? String.valueOf(result[10]) : null);
                cmDetalle.setCmFacturas(cmFactura);
                cmDetalle.setMaeTipoDocumentoValor((String) result[11]);
                cmDetalle.setDocumento((String) result[12]);
                listaResultados.add(cmDetalle);
            }

        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }
    
    
    @Override
    public int cantidadConsultarPorAutorizacion(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT  count(distinct cma.cm_facturas_id) as cantidad"
                    + " FROM cm_auditoria_autorizaciones cma JOIN cm_facturas cmf ON cmf.id = cma.cm_facturas_id "
                    + " JOIN cm_detalles cmd On cmd.id = cma.cm_detalles_id"
                    + " WHERE cma.au_anexos4_id = ? ";

            javax.persistence.Query nativeQuery = getEntityManager().createNativeQuery(strQuery);
            BigInteger count  =  (BigInteger) nativeQuery
                    .setParameter(1, paramConsulta.getParametroConsulta1()).
                    getSingleResult();
            
            cantidad = (int) count.longValue();

        } catch (NoResultException e) {
           cantidad = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cantidad;
    }
    
    @Override
    public List<CmDetalle> consultarPorAutorizacion(ParamConsulta paramConsulta) throws Exception {
        List<CmDetalle> listaResultados = new ArrayList();
        try {

            List<Object[]> results;

            String strQuery = "SELECT  distinct cma.cm_facturas_id, cmf.numero_radicado, "
                    + " cmf.numero_facturado, cmf.nit ,cmf.ips, cmf.fecha_radicacion, cmf.valor_factura, "
                    + " cmd.nombre_completo_afiliado, cmf.mae_tipo_contrato_valor, cmf.estado_factura, cma.au_anexos4_id, cmd.mae_tipo_documento_valor, cmd.documento "
                    + " FROM cm_auditoria_autorizaciones cma JOIN cm_facturas cmf ON cmf.id = cma.cm_facturas_id "
                    + " JOIN cm_detalles cmd On cmd.id = cma.cm_detalles_id"
                    + " WHERE cma.au_anexos4_id = ? ";

            javax.persistence.Query nativeQuery = getEntityManager().createNativeQuery(strQuery);
            results = nativeQuery
                    .setParameter(1, paramConsulta.getParametroConsulta1())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();

            for (Object[] result : results) {
                CmDetalle cmDetalle = new CmDetalle();
                CmFactura cmFactura = new CmFactura();
                cmFactura.setId((Integer) result[0]);
                cmFactura.setNumeroRadicado((int) result[1]);
                cmFactura.setNumeroFacturado((String) result[2]);
                cmFactura.setNit((String) result[3]);
                cmFactura.setIps((String) result[4]);
                cmFactura.setFechaRadicacion((Date) result[5]);
                cmFactura.setValorFactura((BigDecimal) result[6]);
                cmDetalle.setCmFacturas(cmFactura);
                cmDetalle.setNombreCompletoAfiliado((String) result[7]);
                cmFactura.setMaeTipoContratoValor((String) result[8]);
                cmFactura.setEstadoFactura((int) result[9]);
                cmDetalle.setNumeroAutorizacion(result[10] != null ? String.valueOf(result[10]) : null);
                cmDetalle.setMaeTipoDocumentoValor((String) result[11]);
                cmDetalle.setDocumento((String) result[12]);
                listaResultados.add(cmDetalle);
            }

        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }
    
    
    
    
    public int consultarUltimoIdDetalle() throws Exception {
        int idMaximoDetalle = 0;
        try {
            String strQuery = "SELECT max(id) FROM cm_detalles" ;                          
            javax.persistence.Query nativeQuery = getEntityManager().createNativeQuery(strQuery);
            idMaximoDetalle = (int) nativeQuery.getSingleResult();
        } catch (NoResultException e) {
            idMaximoDetalle = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return idMaximoDetalle;
    }  
    
    @Override
    public Map<String, String> consultarInfoProcedimientosMiPres(ParamConsulta paramConsulta) throws Exception {
        Map<String, String> mapInfoTec = new HashMap<>();
        try {
            String strQuery = "FROM MaTecnologiasMipres p "
                    + "WHERE p.id > 0 AND p.maTecnologiasId.activo = 1 ";

            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND p.maTecnologiasId.codigoPropio  IN (" + paramConsulta.getParametroConsulta1() + ") ";
            }

            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }

            List<MaTecnologiasMipres> list = getEntityManager().createQuery(strQuery).getResultList();
            for (MaTecnologiasMipres tec : list) {
                String padreCodigoPropio = tec.getMaTecnologiasId().getCodigoPropio();
                String valorActual = mapInfoTec.getOrDefault(padreCodigoPropio, "");
                String valorConcatenado = valorActual.isEmpty() ? tec.getCodigoMipres() : valorActual + " - " + tec.getCodigoMipres();
                mapInfoTec.put(padreCodigoPropio, valorConcatenado);
            }
        } catch (NoResultException e) {
            mapInfoTec = new HashMap<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return mapInfoTec;
    }
    
     @Override
    public Map<String, String> consultarInfoInsumosMiPres(ParamConsulta paramConsulta) throws Exception {
        Map<String, String> mapInfoTec = new HashMap<>();
        try {
            String strQuery = "FROM MaInsumosMipres p "
                    + "WHERE p.id > 0 AND p.maInsumosId.activo = 1 AND p.maInsumosId.automatico = 0";

            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND p.maInsumosId.codigo  IN (" + paramConsulta.getParametroConsulta1() + ") ";
            }

            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }

            List<MaInsumosMipres> list = getEntityManager().createQuery(strQuery).getResultList();
            for (MaInsumosMipres tec : list) {
                String padreCodigoPropio = tec.getMaInsumosId().getCodigo();
                String valorActual = mapInfoTec.getOrDefault(padreCodigoPropio, "");
                String valorConcatenado = valorActual.isEmpty() ? tec.getCodigoMipres() : valorActual + " - " + tec.getCodigoMipres();
                mapInfoTec.put(padreCodigoPropio, valorConcatenado);
            }
        } catch (NoResultException e) {
            mapInfoTec = new HashMap<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return mapInfoTec;
    }

    @Override
    public CmDetalle eliminar(int id) throws Exception {
        CmDetalle obj = null;
        try {
            CmDetalles per = getEntityManager().find(CmDetalles.class, id);
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

    public static CmDetalle castEntidadNegocio(CmDetalles neg) {
        CmDetalle ent = new CmDetalle();
        ent.setId(neg.getId());
        if (neg.getCmFacturasId() != null) {
            CmFactura factura = new CmFactura(neg.getCmFacturasId().getId());
            factura.setNumeroFacturado(neg.getCmFacturasId().getNumeroFacturado());
            factura.setVersion(neg.getCmFacturasId().getVersion());
            ent.setCmFacturas(factura);
        }
        ent.setDocumento(neg.getDocumento());
        ent.setMaeTipoDocumentoId(neg.getMaeTipoDocumentoId());
        ent.setMaeTipoDocumentoCodigo(neg.getMaeTipoDocumentoCodigo());
        ent.setMaeTipoDocumentoValor(neg.getMaeTipoDocumentoValor());
        ent.setNombreCompletoAfiliado(neg.getNombreCompletoAfiliado());
        ent.setMaServicioCodigo(neg.getMaServicioCodigo());
        ent.setMaServicioId(neg.getMaServicioId());
        ent.setMaServicioValor(neg.getMaServicioValor());
        ent.setRadicadoGlosa(neg.getRadicadoGlosa());

        String acumuladorMotivosStr = neg.getMotivoGlosa();
        String acumuladorObservacionStr = "";
        if (neg.getCmAuditoriaMotivosGlosasList() != null
                && !neg.getCmAuditoriaMotivosGlosasList().isEmpty()) {
            ent.setCantidadMotivosAsociadas(neg.getCmAuditoriaMotivosGlosasList().size());
            List<CmAuditoriaMotivoGlosa> motivos = new ArrayList<>();
            for (CmAuditoriaMotivosGlosas motivo : neg.getCmAuditoriaMotivosGlosasList()) {
                motivos.add(CmAuditoriaMotivosGlosaServicio.castEntidadNegocio(motivo));
                String obsTemp = motivo.getObservacion() != null && motivo.getObservacion().length() > 2 ?
                                 motivo.getObservacion() + " -- " : "";
                acumuladorObservacionStr += obsTemp;
            }
            ent.setListaCmAuditoriaMotivosGlosa(motivos);
        }
        
        if (neg.getCmAuditoriaAutorizacionesList() != null
                && !neg.getCmAuditoriaAutorizacionesList().isEmpty()) {
            ent.setCantidadAutorizacionesAsociadas(neg.getCmAuditoriaAutorizacionesList().size());
            List<CmAuditoriaAutorizacion> autorizaciones = new ArrayList<>();
            for (CmAuditoriaAutorizaciones autorizacion : neg.getCmAuditoriaAutorizacionesList()) {
                autorizaciones.add(CmAuditoriaAutorizacionServicio.castEntidadNegocio(autorizacion));
            }
            ent.setListacmAuditoriaAutorizacion(autorizaciones);
        }
        
        if(neg.getCmAuditoriaCapitaDescuentosList()!= null && 
           !neg.getCmAuditoriaCapitaDescuentosList().isEmpty()){
           ent.setCantidadCapitaDescuentosAsociados(neg.getCmAuditoriaCapitaDescuentosList().size());       
           List<CmAuditoriaCapitaDescuento> descuentoCapitaList = new ArrayList<>();
            for (CmAuditoriaCapitaDescuentos capitaDescuentos : neg.getCmAuditoriaCapitaDescuentosList()) {
                descuentoCapitaList.add(CmAuditoriaDescuentoCapitaServicio.castEntidadNegocio(capitaDescuentos));
            }
            ent.setListaCmAuditoriaDescuentoCapita(descuentoCapitaList);
        }
           
        if(neg.getCmAuditoriaConceptosContablesList()!= null && 
           !neg.getCmAuditoriaConceptosContablesList().isEmpty()){
           ent.setCantidadConceptosContablesAsociados(neg.getCmAuditoriaConceptosContablesList().size());
            List<CmAuditoriaConceptoContable> conceptos = new ArrayList<>(); 
            for (CmAuditoriaConceptosContables concepto : neg.getCmAuditoriaConceptosContablesList()) {
                conceptos.add(CmAuditoriaConceptoContableServicio.castEntidadNegocio(concepto));
            }
            ent.setListaCmAuditoriaConceptoContable(conceptos);
        }
        
        if(neg.getCmAuditoriaDiagnosticosList()!= null && 
           !neg.getCmAuditoriaDiagnosticosList().isEmpty()){
           ent.setCantidadDiagnosticosAsociados(neg.getCmAuditoriaDiagnosticosList().size());
            List<CmAuditoriaDiagnostico> diagnosticos = new ArrayList<>();
            for (CmAuditoriaDiagnosticos diagnostico : neg.getCmAuditoriaDiagnosticosList()) {
                diagnosticos.add(CmAuditoriaDiagnosticoServicio.castEntidadNegocio(diagnostico));
            }
            ent.setListaCmAuditoriaDiagnosticos(diagnosticos);
        }
        
        ent.setCantidad(neg.getCantidad());
        ent.setValorUnitario(neg.getValorUnitario());
        ent.setMotivoGlosa(acumuladorMotivosStr);
        ent.setValorCopago(neg.getValorCopago());
        ent.setValorCopagoItem(neg.getValorCopagoItem());
        ent.setValorFacturado(neg.getValorFacturado());
        ent.setValorPendiente(neg.getValorPendiente());
        ent.setValorAceptadoIps(neg.getValorAceptadoIps());
        ent.setValorPagado(neg.getValorPagado());
        ent.setValorPendienteActual(neg.getValorPendienteActual());
        String observacionTemporal = neg.getObservacion() != null &&
                                     neg.getObservacion().length() > 2 ? neg.getObservacion() : 
                                     acumuladorObservacionStr;
        ent.setObservacion(observacionTemporal);
        ent.setValorPagadoEPS(neg.getValorPagadoEps());
        ent.setPorcentajePagadoEPS(neg.getPorcentajePagadoEps());
        BigDecimal valorAceptadoIps = neg.getValorAceptadoIps() != null
                && neg.getValorAceptadoIps().compareTo(new BigDecimal("0.0000")) == 0 ? null : neg.getValorAceptadoIps();
        ent.setValorAceptadoIPS(valorAceptadoIps);
        ent.setPorcentajeAceptadoIPS(neg.getPorcentajeAceptadoIps());
        ent.setObservacionRespuestaDetalles(neg.getObservacionRespuestaDetalles());
        
        if(neg.getObservacionRespuestaDetalles()!=null){
            ent.setObservacionGlosa(neg.getObservacionRespuestaDetalles());
        }
        
        ent.setAplicaRecobro(neg.getAplicaRecobro());       
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTipoServicio(neg.getTipoServicio());
        ent.setAplicaAc(neg.getAplicaAc());
        ent.setAplicaDc(neg.getAplicaDc());
        ent.setAplicaPbs(neg.getAplicaPbs());
        ent.setEstado(neg.getEstado());
        
        boolean aplicaGlosa = neg.getAplicaGlosa() == null ? 
                              false :  neg.getAplicaGlosa() ;
        ent.setAplicaGlosa(aplicaGlosa);
        
        boolean aplicaConcepto = neg.getAplicaConcepto() == null ?
                              false : neg.getAplicaConcepto();
        ent.setAplicaConcepto(aplicaConcepto);
        
        boolean aplicaDx = neg.getAplicaDx() == null ?
                           false : neg.getAplicaDx();
        ent.setAplicaDx(aplicaDx);
        
        boolean aplicaAutorizacion = neg.getAplicaAutorizacion() == null ?
                           false : neg.getAplicaAutorizacion();     
        ent.setAplicaAutorizacion(aplicaAutorizacion);
        
        ent.setValorGlosa(neg.getValorGlosa());
        ent.setConceptoContable(neg.getConceptoContable());
        ent.setPorcentajeConcepto(neg.getPorcentajeConcepto());
        ent.setNumeroAutorizacion(neg.getNumeroAutorizacion());
        ent.setCodigoDx(neg.getCodigoDx());
        ent.setNombreDx(neg.getNombreDx());
        ent.setCopagoNoEfectivo(neg.getCopagoNoEfectivo());
        
        ent.setConsecutivoItem(neg.getConsecutivoItem());
        ent.setFechaPrestacion(neg.getFechaPrestacion());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setUsuarioModifica(neg.getUsuarioModifica());
        ent.setTerminalModifica(neg.getTerminalModifica());
        ent.setFechaHoraModifica(neg.getFechaHoraModifica());
        return ent;
    }
    
      public static CmDetalle castEntidadNegocioCorto(CmDetalles neg) {
        CmDetalle ent = new CmDetalle();
        ent.setId(neg.getId());
        ent.setDocumento(neg.getDocumento());
        ent.setNombreCompletoAfiliado(neg.getNombreCompletoAfiliado());
        return ent;
      }
    
    public static CmDetalle castEntidadNegocioHistorico(CmDetalles neg) {
        CmDetalle ent = new CmDetalle();
        ent.setId(neg.getId());
        if (neg.getCmFacturasId() != null) {
            CmFactura factura = new CmFactura(neg.getCmFacturasId().getId());
            factura.setNumeroFacturado(neg.getCmFacturasId().getNumeroFacturado());
            ent.setCmFacturas(factura);
        }
        ent.setDocumento(neg.getDocumento());
        ent.setMaeTipoDocumentoId(neg.getMaeTipoDocumentoId());
        ent.setMaeTipoDocumentoCodigo(neg.getMaeTipoDocumentoCodigo());
        ent.setMaeTipoDocumentoValor(neg.getMaeTipoDocumentoValor());
        ent.setNombreCompletoAfiliado(neg.getNombreCompletoAfiliado());
        ent.setMaServicioCodigo(neg.getMaServicioCodigo());
        ent.setMaServicioId(neg.getMaServicioId());
        ent.setMaServicioValor(neg.getMaServicioValor());
        ent.setRadicadoGlosa(neg.getRadicadoGlosa());
        ent.setObservacion(neg.getObservacion());

        ent.setListaRespuestaDetalle(new ArrayList<>());
        for (CmGlosaRespuestaDetalles cmGlosaRespuestaDetalles : neg.getCmGlosaRespuestaDetallesList()) {
            CmGlosaRespuestaDetalle glosaDetalle = CmGlosaRespuestaDetalleServicio.castEntidadNegocio(cmGlosaRespuestaDetalles);
            castEntidadNegocionRespuestaGlosa(glosaDetalle , cmGlosaRespuestaDetalles);      
            ent.getListaRespuestaDetalle().add(glosaDetalle);
        }
            
        ent.setCantidad(neg.getCantidad());
        ent.setValorUnitario(neg.getValorUnitario());
        ent.setValorCopago(neg.getValorCopago());
        ent.setValorCopagoItem(neg.getValorCopagoItem());
        ent.setValorFacturado(neg.getValorFacturado());
        ent.setValorPendiente(neg.getValorPendiente());
        ent.setValorAceptadoIps(neg.getValorAceptadoIps());
        ent.setValorPagado(neg.getValorPagado());
        ent.setValorPendienteActual(neg.getValorPendienteActual());
        ent.setValorPagadoEPS(neg.getValorPagadoEps());
        ent.setPorcentajePagadoEPS(neg.getPorcentajePagadoEps());
        BigDecimal valorAceptadoIps = neg.getValorAceptadoIps() != null
                && neg.getValorAceptadoIps().compareTo(new BigDecimal("0.0000")) == 0 ? null : neg.getValorAceptadoIps();
        ent.setValorAceptadoIPS(valorAceptadoIps);
        ent.setPorcentajeAceptadoIPS(neg.getPorcentajeAceptadoIps());
        ent.setObservacionRespuestaDetalles(neg.getObservacionRespuestaDetalles());
        
        if(neg.getObservacionRespuestaDetalles()!=null){
            ent.setObservacionGlosa(neg.getObservacionRespuestaDetalles());
        }
        
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTipoServicio(neg.getTipoServicio());
        ent.setAplicaAc(neg.getAplicaAc());
        ent.setAplicaDc(neg.getAplicaDc());
        ent.setAplicaPbs(neg.getAplicaPbs());
        ent.setEstado(neg.getEstado());
        
        ent.setAplicaGlosa(Optional.ofNullable(neg.getAplicaGlosa()).orElse(false));
        ent.setAplicaConcepto(Optional.ofNullable(neg.getAplicaConcepto()).orElse(false)); 
        ent.setAplicaDx(Optional.ofNullable(neg.getAplicaDx()).orElse(false));  
        ent.setAplicaAutorizacion(Optional.ofNullable(neg.getAplicaAutorizacion()).orElse(false));
        
        ent.setValorGlosa(neg.getValorGlosa());
        ent.setConceptoContable(neg.getConceptoContable());
        ent.setPorcentajeConcepto(neg.getPorcentajeConcepto());
        ent.setNumeroAutorizacion(neg.getNumeroAutorizacion());
        ent.setCodigoDx(neg.getCodigoDx());
        ent.setNombreDx(neg.getNombreDx());
        ent.setMotivoGlosa(neg.getMotivoGlosa());
        ent.setConsecutivoItem(neg.getConsecutivoItem());
        ent.setFechaPrestacion(neg.getFechaPrestacion());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setUsuarioModifica(neg.getUsuarioModifica());
        ent.setTerminalModifica(neg.getTerminalModifica());
        ent.setFechaHoraModifica(neg.getFechaHoraModifica());
        return ent;
    }

    public static List<CmDetalle> obtenerDetalleValoresSegunCast(List<CmDetalles> list, ParamConsulta paramConsulta) {
        final int TIPO_CAST_VALOR_DEFECTO = 0;
        final int TIPO_CAST_VALOR_AUDITORIA = 1;
        List<CmDetalle> listResult = new ArrayList();
        int tipoCast = paramConsulta != null && paramConsulta.getParametroConsulta5() != null
                ? (int) paramConsulta.getParametroConsulta5() : TIPO_CAST_VALOR_DEFECTO;
        switch (tipoCast) {
            case TIPO_CAST_VALOR_DEFECTO:
                list.forEach(per -> {
                    listResult.add(castEntidadNegocioSoloValores(per));
                });
                break;

            case TIPO_CAST_VALOR_AUDITORIA:
                list.forEach(per -> {
                    listResult.add(castEntidadNegocioValoresInsumosAuditoria(per));
                });
                break;

        }
        return listResult;
    }
    
    public static CmDetalle castEntidadNegocioSoloValores(CmDetalles neg) {
        CmDetalle ent = new CmDetalle();
        ent.setId(neg.getId());
        if (neg.getCmFacturasId() != null) {
            CmFactura factura = new CmFactura(neg.getCmFacturasId().getId());
            factura.setNumeroFacturado(neg.getCmFacturasId().getNumeroFacturado());
            ent.setCmFacturas(factura);
        }
        ent.setDocumento(neg.getDocumento());
        ent.setCantidad(neg.getCantidad());
        ent.setValorUnitario(neg.getValorUnitario());
        ent.setValorCopago(neg.getValorCopago());
        ent.setValorCopagoItem(neg.getValorCopagoItem());
        ent.setValorFacturado(neg.getValorFacturado());
        ent.setValorPendiente(neg.getValorPendiente());
        ent.setValorAceptadoIps(neg.getValorAceptadoIps());
        ent.setValorPagado(neg.getValorPagado());
        ent.setValorPendienteActual(neg.getValorPendienteActual());
        ent.setValorPagadoEPS(neg.getValorPagadoEps());
        ent.setPorcentajePagadoEPS(neg.getPorcentajePagadoEps());
        BigDecimal valorAceptadoIps = neg.getValorAceptadoIps() != null
                && neg.getValorAceptadoIps().compareTo(new BigDecimal("0.0000")) == 0 ? null : neg.getValorAceptadoIps();
        ent.setValorAceptadoIPS(valorAceptadoIps);
        ent.setPorcentajeAceptadoIPS(neg.getPorcentajeAceptadoIps());
        ent.setValorGlosa(neg.getValorGlosa());
        ent.setPorcentajeConcepto(neg.getPorcentajeConcepto());
        return ent;
    }
    
    public static CmDetalle castEntidadNegocioValoresInsumosAuditoria(CmDetalles neg) {
        CmDetalle ent = new CmDetalle();
        ent.setId(neg.getId());
        if (neg.getCmFacturasId() != null) {
            CmFactura factura = new CmFactura(neg.getCmFacturasId().getId());
            factura.setNumeroFacturado(neg.getCmFacturasId().getNumeroFacturado());
            ent.setCmFacturas(factura);
        }

        ent.setCantidadConceptosContablesAsociados(neg.getCmAuditoriaConceptosContablesList().size());
        ent.setCantidadDiagnosticosAsociados(neg.getCmAuditoriaDiagnosticosList().size());

        return ent;
    }
    
     public static CmDetalle castEntidadNegocioMultiFacturaValoresInsumosAuditoria(CmDetalles neg) {
        CmDetalle ent = new CmDetalle();
        ent.setId(neg.getId());
        if (neg.getCmFacturasId() != null) {
            CmFactura factura = new CmFactura(neg.getCmFacturasId().getId());
            factura.setNumeroFacturado(neg.getCmFacturasId().getNumeroFacturado());
            ent.setCmFacturas(factura);
        }

        boolean aplicaGlosa =  Optional.ofNullable(neg.getAplicaGlosa()).orElse(false);
        ent.setAplicaGlosa(aplicaGlosa);
        
        boolean aplicaConcepto = Optional.ofNullable(neg.getAplicaConcepto()).orElse(false); 
        ent.setAplicaConcepto(aplicaConcepto);
        
        boolean aplicaDx = Optional.ofNullable(neg.getAplicaDx()).orElse(false);  
        ent.setAplicaDx(aplicaDx);
        
        boolean aplicaAutorizacion =  Optional.ofNullable(neg.getAplicaAutorizacion()).orElse(false);    
        ent.setAplicaAutorizacion(aplicaAutorizacion);

        return ent;
    }
    
    public static CmDetalle castEntidadNegocioCastCorto(CmDetalles neg) {
        CmDetalle ent = new CmDetalle();
        if(neg.getCmAuditoriaAutorizacionesList()!= null && 
           !neg.getCmAuditoriaAutorizacionesList().isEmpty()){
           ent.setCantidadAutorizacionesAsociadas(neg.getCmAuditoriaAutorizacionesList().size());
        }
        
        if(neg.getCmAuditoriaDiagnosticosList()!= null && 
           !neg.getCmAuditoriaDiagnosticosList().isEmpty()){
           ent.setCantidadDiagnosticosAsociados(neg.getCmAuditoriaDiagnosticosList().size());
        }
        
        if(neg.getCmAuditoriaCapitaDescuentosList()!= null && 
           !neg.getCmAuditoriaCapitaDescuentosList().isEmpty()){
           ent.setCantidadCapitaDescuentosAsociados(neg.getCmAuditoriaCapitaDescuentosList().size());
        }
        
        if (neg.getCmAuditoriaMotivosGlosasList() != null
                && !neg.getCmAuditoriaMotivosGlosasList().isEmpty()) {
            ent.setCantidadMotivosAsociadas(neg.getCmAuditoriaMotivosGlosasList().size());
        }
        ent.setValorFacturado(neg.getValorFacturado());
        ent.setId(neg.getId());
        return ent;
    }

    public static CmDetalles castNegocioEntidad(CmDetalle obj) {
        CmDetalles ent = new CmDetalles();
        if (obj.getId() != null) {
            obj.setId(obj.getId());
        }
        if (obj.getCmFacturas() != null) {
            ent.setCmFacturasId(new CmFacturas(obj.getCmFacturas().getId()));
        }
        String documento = obj.getDocumento().equals("") ? " "
                : obj.getDocumento();
        ent.setDocumento(documento);
        ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
        ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
        ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
        String nombreCompleto = obj.getNombreCompletoAfiliado().equals("") ? " "
                : obj.getNombreCompletoAfiliado();
        ent.setNombreCompletoAfiliado(nombreCompleto);
        ent.setMaServicioCodigo(obj.getMaServicioCodigo());
        ent.setMaServicioId(obj.getMaServicioId());
        ent.setMaServicioValor(obj.getMaServicioValor());
        if (obj.getRadicadoGlosa() != null) {
            ent.setRadicadoGlosa(obj.getRadicadoGlosa());
        }
        ent.setValorCopago(obj.getValorCopago());
        ent.setValorCopagoItem(obj.getValorCopagoItem());
        ent.setValorFacturado(obj.getValorFacturado());
        if (obj.getValorPendiente() != null) {
            ent.setValorPendiente(obj.getValorPendiente());
        }
        if (obj.getValorAceptadoIps() != null) {
            ent.setValorAceptadoIps(obj.getValorAceptadoIps());
        }
        ent.setValorPagado(obj.getValorPagado());
        if (obj.getValorPendienteActual() != null) {
            ent.setValorPendienteActual(obj.getValorPendienteActual());
        }
        if (obj.getObservacion() != null) {
            ent.setObservacion(obj.getObservacion().equals("") ? " " : obj.getObservacion());
        }
        if (obj.getValorPagadoEPS() != null) {
            ent.setValorPagadoEps(obj.getValorPagadoEPS());
        }
        if (obj.getPorcentajePagadoEPS() != null) {
            ent.setPorcentajePagadoEps(obj.getPorcentajePagadoEPS());
        }
        if (obj.getValorAceptadoIPS() != null) {
            ent.setValorAceptadoIps(obj.getValorAceptadoIPS());
        }
        if (obj.getPorcentajeAceptadoIPS() != null) {
            ent.setPorcentajeAceptadoIps(obj.getPorcentajeAceptadoIPS());
        }
        ent.setTipoServicio(obj.getTipoServicio());
        if (obj.getAplicaAc() != null) {
            ent.setAplicaAc(obj.getAplicaAc());
        }
        if (obj.getAplicaDc() != null) {
            ent.setAplicaDc(obj.getAplicaDc());
        }
        if (obj.getAplicaPbs() != null) {
            ent.setAplicaPbs(obj.getAplicaPbs());
        }
    
        if (obj.getAplicaGlosa() != null) {
            ent.setAplicaGlosa(obj.getAplicaGlosa());
        }
        if (obj.getAplicaConcepto()!= null) {
            ent.setAplicaConcepto(obj.getAplicaConcepto());
        }
        if (obj.getAplicaDx() != null) {
            ent.setAplicaDx(obj.getAplicaDx());
        }
        if (obj.getAplicaAutorizacion() != null) {
            ent.setAplicaAutorizacion(obj.getAplicaAutorizacion());
        }
        
        ent.setValorGlosa(obj.getValorGlosa());
        ent.setConceptoContable(obj.getConceptoContable());
        ent.setNombreDx(obj.getNombreDx());
        ent.setMotivoGlosa(obj.getMotivoGlosa());
        ent.setCantidad(obj.getCantidad());
        ent.setValorUnitario(obj.getValorUnitario());
        ent.setEstado(obj.getEstado());
        ent.setConsecutivoItem(obj.getConsecutivoItem());
        ent.setFechaPrestacion(obj.getFechaPrestacion());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        if (obj.getUsuarioModifica() != null) {
            ent.setUsuarioModifica(obj.getUsuarioModifica());
        }
        if (obj.getTerminalModifica() != null) {
            ent.setTerminalModifica(obj.getTerminalModifica());
        }
        if (obj.getFechaHoraModifica() != null) {
            ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        }
        return ent;
    }

    public static void castEntidadNegocionRespuestaGlosa(CmGlosaRespuestaDetalle glosaDetalleNegocio, CmGlosaRespuestaDetalles glosaDetalleEntidad) {
        int tipo = glosaDetalleEntidad.getCmGlosaRespuestasId().getTipoRespuesta();
        glosaDetalleNegocio.setTipoRespuestaStr(CmGlosaRespuesta.getTipoRespuestaStr(tipo));
    }

    private Object String(String idsCmDetalle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int consultarCantidadListaConsultaAfiliado(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cmd) FROM CmDetalles cmd ";
            strQuery += " WHERE cmd.id > 0  ";
            
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND cmd.asegAfiliadosId.id =  " + (Integer) paramConsulta.getParametroConsulta1() + " " ;
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cmFacturas.id":
                            strQuery += " AND cmd.cmFacturasId.id = " + e.getValue() + " ";
                            break;
                        case "maServicioCodigo":
                            strQuery += " AND cmd.maServicioCodigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maServicioValor":
                            strQuery += " AND cmd.maServicioValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cmFacturas.cntPrestador.numeroDocumento":
                            strQuery += " AND cmd.cmFacturasId.cntPrestadoresId.numeroDocumento LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cmFacturas.cntPrestador.codigoMinSalud":
                            strQuery += " AND cmd.cmFacturasId.cntPrestadoresId.codigoMinSalud LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cmFacturas.cntPrestador.razonSocial":
                            strQuery += " AND cmd.cmFacturasId.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }

            Query query = getEntityManager().createQuery(strQuery);

            /*if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fhInicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
                query.setParameter("fhFin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            }*/

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
    public List<CmDetalle> consultarListaConsultaAfiliado(ParamConsulta paramConsulta) throws Exception {
        List<CmDetalle> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmDetalles cmd ";
            strQuery += " WHERE 1= 1  ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND cmd.asegAfiliadosId.id =  " + (Integer) paramConsulta.getParametroConsulta1() + " " ;
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cmFacturas.id":
                            strQuery += " AND cmd.cmFacturasId.id = " + e.getValue() + " ";
                            break;
                        case "maServicioCodigo":
                            strQuery += " AND cmd.maServicioCodigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maServicioValor":
                            strQuery += " AND cmd.maServicioValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cmFacturas.cntPrestador.numeroDocumento":
                            strQuery += " AND cmd.cmFacturasId.cntPrestadoresId.numeroDocumento LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cmFacturas.cntPrestador.codigoMinSalud":
                            strQuery += " AND cmd.cmFacturasId.cntPrestadoresId.codigoMinSalud LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cmFacturas.cntPrestador.razonSocial":
                            strQuery += " AND cmd.cmFacturasId.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }

            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {

                String order = paramConsulta.getOrden().replace("cmFacturas", "cmFacturasId").replace("cntPrestador", "cntPrestadoresId");

                strQuery += " cmd." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " cmd.id DESC ";
            }
            Query query = getEntityManager().createQuery(strQuery);

            /*if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fhInicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
                query.setParameter("fhFin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            }*/

            List<CmDetalles> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CmDetalles neg : list) {
                listResult.add(castEntidadNegocioConsultaAfiliado(neg));
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
    
    public static CmDetalle castEntidadNegocioConsultaAfiliado(CmDetalles neg) {
        CmDetalle ent = new CmDetalle();
        ent.setId(neg.getId());
        if (neg.getCmFacturasId() != null) {
            CmFactura factura = new CmFactura(neg.getCmFacturasId().getId());
            if(neg.getCmFacturasId().getCntPrestadoresId() != null) {
                CntPrestador prestador = new CntPrestador(neg.getCmFacturasId().getCntPrestadoresId().getId(),neg.getCmFacturasId().getCntPrestadoresId().getRazonSocial());
                prestador.setNumeroDocumento(neg.getCmFacturasId().getCntPrestadoresId().getNumeroDocumento());
                prestador.setCodigoMinSalud(neg.getCmFacturasId().getCntPrestadoresId().getCodigoMinSalud());
                factura.setCntPrestador(prestador);
            }
            factura.setMaeTipoContratoId(neg.getCmFacturasId().getMaeTipoContratoId());
            factura.setMaeTipoContratoCodigo(neg.getCmFacturasId().getMaeTipoContratoCodigo());
            factura.setMaeTipoContratoValor(neg.getCmFacturasId().getMaeTipoContratoValor());
            factura.setPbs(neg.getCmFacturasId().getPbs());
            factura.setValorFactura(neg.getCmFacturasId().getValorFactura());
            ent.setCmFacturas(factura);
        }
        ent.setDocumento(neg.getDocumento());
        ent.setMaeTipoDocumentoId(neg.getMaeTipoDocumentoId());
        ent.setMaeTipoDocumentoCodigo(neg.getMaeTipoDocumentoCodigo());
        ent.setMaeTipoDocumentoValor(neg.getMaeTipoDocumentoValor());
        ent.setNombreCompletoAfiliado(neg.getNombreCompletoAfiliado());
        ent.setMaServicioCodigo(neg.getMaServicioCodigo());
        ent.setMaServicioId(neg.getMaServicioId());
        ent.setMaServicioValor(neg.getMaServicioValor());
        ent.setRadicadoGlosa(neg.getRadicadoGlosa());

        String acumuladorMotivosStr = neg.getMotivoGlosa();
        String acumuladorObservacionStr = "";
        if (neg.getCmAuditoriaMotivosGlosasList() != null
                && !neg.getCmAuditoriaMotivosGlosasList().isEmpty()) {
            ent.setCantidadMotivosAsociadas(neg.getCmAuditoriaMotivosGlosasList().size());
            List<CmAuditoriaMotivoGlosa> motivos = new ArrayList<>();
            for (CmAuditoriaMotivosGlosas motivo : neg.getCmAuditoriaMotivosGlosasList()) {
                motivos.add(CmAuditoriaMotivosGlosaServicio.castEntidadNegocio(motivo));
                String obsTemp = motivo.getObservacion() != null && motivo.getObservacion().length() > 2 ?
                                 motivo.getObservacion() + " -- " : "";
                acumuladorObservacionStr += obsTemp;
            }
            ent.setListaCmAuditoriaMotivosGlosa(motivos);
        }
        
        if (neg.getCmAuditoriaAutorizacionesList() != null
                && !neg.getCmAuditoriaAutorizacionesList().isEmpty()) {
            ent.setCantidadAutorizacionesAsociadas(neg.getCmAuditoriaAutorizacionesList().size());
            List<CmAuditoriaAutorizacion> autorizaciones = new ArrayList<>();
            for (CmAuditoriaAutorizaciones autorizacion : neg.getCmAuditoriaAutorizacionesList()) {
                autorizaciones.add(CmAuditoriaAutorizacionServicio.castEntidadNegocio(autorizacion));
            }
            ent.setListacmAuditoriaAutorizacion(autorizaciones);
        }
        
        if(neg.getCmAuditoriaCapitaDescuentosList()!= null && 
           !neg.getCmAuditoriaCapitaDescuentosList().isEmpty()){
           ent.setCantidadCapitaDescuentosAsociados(neg.getCmAuditoriaCapitaDescuentosList().size());       
           List<CmAuditoriaCapitaDescuento> descuentoCapitaList = new ArrayList<>();
            for (CmAuditoriaCapitaDescuentos capitaDescuentos : neg.getCmAuditoriaCapitaDescuentosList()) {
                descuentoCapitaList.add(CmAuditoriaDescuentoCapitaServicio.castEntidadNegocio(capitaDescuentos));
            }
            ent.setListaCmAuditoriaDescuentoCapita(descuentoCapitaList);
        }
           
        if(neg.getCmAuditoriaConceptosContablesList()!= null && 
           !neg.getCmAuditoriaConceptosContablesList().isEmpty()){
           ent.setCantidadConceptosContablesAsociados(neg.getCmAuditoriaConceptosContablesList().size());
            List<CmAuditoriaConceptoContable> conceptos = new ArrayList<>(); 
            for (CmAuditoriaConceptosContables concepto : neg.getCmAuditoriaConceptosContablesList()) {
                conceptos.add(CmAuditoriaConceptoContableServicio.castEntidadNegocio(concepto));
            }
            ent.setListaCmAuditoriaConceptoContable(conceptos);
        }
        
        if(neg.getCmAuditoriaDiagnosticosList()!= null && 
           !neg.getCmAuditoriaDiagnosticosList().isEmpty()){
           ent.setCantidadDiagnosticosAsociados(neg.getCmAuditoriaDiagnosticosList().size());
            List<CmAuditoriaDiagnostico> diagnosticos = new ArrayList<>();
            for (CmAuditoriaDiagnosticos diagnostico : neg.getCmAuditoriaDiagnosticosList()) {
                diagnosticos.add(CmAuditoriaDiagnosticoServicio.castEntidadNegocio(diagnostico));
            }
            ent.setListaCmAuditoriaDiagnosticos(diagnosticos);
        }
        
        ent.setCantidad(neg.getCantidad());
        ent.setValorUnitario(neg.getValorUnitario());
        ent.setMotivoGlosa(acumuladorMotivosStr);
        ent.setValorCopago(neg.getValorCopago());
        ent.setValorCopagoItem(neg.getValorCopagoItem());
        ent.setValorFacturado(neg.getValorFacturado());
        ent.setValorPendiente(neg.getValorPendiente());
        ent.setValorAceptadoIps(neg.getValorAceptadoIps());
        ent.setValorPagado(neg.getValorPagado());
        ent.setValorPendienteActual(neg.getValorPendienteActual());
        String observacionTemporal = neg.getObservacion() != null &&
                                     neg.getObservacion().length() > 2 ? neg.getObservacion() : 
                                     acumuladorObservacionStr;
        ent.setObservacion(observacionTemporal);
        ent.setValorPagadoEPS(neg.getValorPagadoEps());
        ent.setPorcentajePagadoEPS(neg.getPorcentajePagadoEps());
        BigDecimal valorAceptadoIps = neg.getValorAceptadoIps() != null
                && neg.getValorAceptadoIps().compareTo(new BigDecimal("0.0000")) == 0 ? null : neg.getValorAceptadoIps();
        ent.setValorAceptadoIPS(valorAceptadoIps);
        ent.setPorcentajeAceptadoIPS(neg.getPorcentajeAceptadoIps());
        ent.setObservacionRespuestaDetalles(neg.getObservacionRespuestaDetalles());
        
        if(neg.getObservacionRespuestaDetalles()!=null){
            ent.setObservacionGlosa(neg.getObservacionRespuestaDetalles());
        }
        
        ent.setAplicaRecobro(neg.getAplicaRecobro());       
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTipoServicio(neg.getTipoServicio());
        ent.setAplicaAc(neg.getAplicaAc());
        ent.setAplicaDc(neg.getAplicaDc());
        ent.setAplicaPbs(neg.getAplicaPbs());
        ent.setEstado(neg.getEstado());
        
        boolean aplicaGlosa = neg.getAplicaGlosa() == null ? 
                              false :  neg.getAplicaGlosa() ;
        ent.setAplicaGlosa(aplicaGlosa);
        
        boolean aplicaConcepto = neg.getAplicaConcepto() == null ?
                              false : neg.getAplicaConcepto();
        ent.setAplicaConcepto(aplicaConcepto);
        
        boolean aplicaDx = neg.getAplicaDx() == null ?
                           false : neg.getAplicaDx();
        ent.setAplicaDx(aplicaDx);
        
        boolean aplicaAutorizacion = neg.getAplicaAutorizacion() == null ?
                           false : neg.getAplicaAutorizacion();     
        ent.setAplicaAutorizacion(aplicaAutorizacion);
        
        ent.setValorGlosa(neg.getValorGlosa());
        ent.setConceptoContable(neg.getConceptoContable());
        ent.setPorcentajeConcepto(neg.getPorcentajeConcepto());
        ent.setNumeroAutorizacion(neg.getNumeroAutorizacion());
        ent.setCodigoDx(neg.getCodigoDx());
        ent.setNombreDx(neg.getNombreDx());
        ent.setCopagoNoEfectivo(neg.getCopagoNoEfectivo());
        
        ent.setConsecutivoItem(neg.getConsecutivoItem());
        ent.setFechaPrestacion(neg.getFechaPrestacion());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setUsuarioModifica(neg.getUsuarioModifica());
        ent.setTerminalModifica(neg.getTerminalModifica());
        ent.setFechaHoraModifica(neg.getFechaHoraModifica());
        return ent;
    }

}
