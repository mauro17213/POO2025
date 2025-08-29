/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmConciliacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionEncabezado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.ReporteConciliacion;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmFactura;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmConciliaciones;
import com.saviasaludeps.savia.ejb.entidades.CmFacturas;
import com.saviasaludeps.savia.ejb.entidades.CmGlosaRespuestas;
import com.saviasaludeps.savia.ejb.entidades.CmRadicados;
import com.saviasaludeps.savia.ejb.entidades.CmSincronizacionEncabezados;
import com.saviasaludeps.savia.ejb.entidades.CmSincronizaciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.WsCmFacturas;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmConciliacionRemoto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
@Remote(CmConciliacionRemoto.class)
@Local(CmConciliacionLocal.class)
public class CmConciliacionServicio extends GenericoServicio implements CmConciliacionLocal, CmConciliacionRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cmd) FROM CmConciliaciones cmd ";
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
    public List<CmConciliacion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmConciliacion> listResult = new ArrayList();
        int CANTIDAD_SIN_ITEMS = 2;
        try {
            String strQuery = "FROM CmConciliaciones cmd ";
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

            List<CmConciliaciones> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CmConciliaciones neg : list) {
                CmConciliacion conciliacion = castEntidadNegocio(neg);
                if (paramConsulta.getParametroConsulta3() != null) {
                    CmRadicados radicado = obtenerRadicado(neg);
                    conciliacion.setCmRadicado(new CmRadicado(radicado.getId()));
                    
                    String descripcion = getDescripcioSincronizacionWsCmFacturas(radicado);
                    if (descripcion.length() <= CANTIDAD_SIN_ITEMS) {
                        descripcion = getDescripcioSincronizacionEncabezadosFacturas(radicado);
                    }
                    if( CANTIDAD_SIN_ITEMS == descripcion.length() ){
                        descripcion  = "{Sin registro en CmRadicacion para envio sap}";
                    }
    
                    conciliacion.setDescripcionFacturasSincronizadas(descripcion);
                    boolean facturasPendintes = radicado.getEstadoRadicado() == null ? true : !radicado.getEstadoRadicado();
                    conciliacion.setFacturasPendientes(facturasPendintes);

                    CmFacturas factura = neg.getCmGlosaRespuestasList().isEmpty() ? new CmFacturas()
                            : neg.getCmGlosaRespuestasList().get(0).getCmFacturasId();
                    conciliacion.setNit(factura.getNit());
                    conciliacion.setIps(factura.getIps());
                }
                listResult.add(conciliacion);
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
    public CmConciliacion consultar(int id) throws Exception {
        CmConciliacion obj = null;
        try {
            CmConciliaciones per = (CmConciliaciones) getEntityManager().find(CmConciliaciones.class, id);
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
    public int insertar(CmConciliacion obj) throws Exception {
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
    public void actualizar(CmConciliacion obj) throws Exception {
        try {

            CmConciliaciones neg = castNegocioEntidad(obj);
            //obj.setId((int) getEntityManager().merge(castNegocioEntidad(obj)).getId());

            String hql = "UPDATE CmConciliaciones SET"
                    + " valor = :valor,"
                    + " porcentaje  = :porcentaje,"
                    + " valorPagadoEps = :valorPagadoEps,"
                    + " porcentajePagadoEps  = :porcentajePagadoEps,"
                    + " valorAceptadoIps = :valorAceptadoIps,"
                    + " porcentajeAceptadoIps = :porcentajeAceptadoIps,"
                    + " estadoProceso = :estadoProceso,"
                    + " cantidadFacturas = :cantidadFacturas,"
                    + " cantidadFacturasRegistradas = :cantidadFacturasRegistradas,"
                    + " horaFinalizacionRegistro = : horaFinalizacionRegistro ,"
                    + " archivo = : archivo ,"
                    + " archivoNombre = : archivoNombre ,"
                    + " archivoRuta = : archivoRuta ,"
                    + " archivoExiste = : archivoExiste ,"
                    + " usuarioCrea = : usuarioCrea ,"
                    + " terminalCrea = :terminalCrea, "
                    + " fechaHoraCrea = :fechaHoraCrea,"
                    + " cntPrestadoresId.id = : cntPrestadoresId";
            hql += " WHERE id = :id";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("valor", neg.getValor());
            query.setParameter("porcentaje", neg.getPorcentaje());
            query.setParameter("valorPagadoEps", neg.getValorPagadoEps());
            query.setParameter("porcentajePagadoEps", neg.getPorcentajePagadoEps());
            query.setParameter("valorAceptadoIps", neg.getValorAceptadoIps());
            query.setParameter("porcentajeAceptadoIps", neg.getPorcentajeAceptadoIps());
            query.setParameter("estadoProceso", neg.getEstadoProceso());
            query.setParameter("cantidadFacturas", neg.getCantidadFacturas());
            query.setParameter("cantidadFacturasRegistradas", neg.getCantidadFacturasRegistradas());
            query.setParameter("horaFinalizacionRegistro", neg.getHoraFinalizacionRegistro());
            query.setParameter("archivo", neg.getArchivo());
            query.setParameter("archivoNombre", neg.getArchivoNombre());
            query.setParameter("archivoRuta", neg.getArchivoRuta());
            query.setParameter("archivoExiste", neg.getArchivoExiste());
            query.setParameter("usuarioCrea", neg.getUsuarioCrea());
            query.setParameter("terminalCrea", neg.getTerminalCrea());
            query.setParameter("fechaHoraCrea", neg.getFechaHoraCrea());
            Integer idPrestador = neg.getCntPrestadoresId() != null && neg.getCntPrestadoresId().getId() != null
                    ? neg.getCntPrestadoresId().getId() : 0;
            query.setParameter("cntPrestadoresId", idPrestador);
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
    public CmConciliacion eliminar(int id) throws Exception {
        CmConciliacion obj = null;
        try {
            CmConciliaciones per = getEntityManager().find(CmConciliaciones.class, id);
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
    public List<CmConciliacion> consultarAnexos(int id) throws Exception {
        List<CmConciliacion> listResult = new ArrayList();
        try {
            String hql = "SELECT a FROM CmConciliaciones a "
                    + "WHERE a.id.id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("id", id);
            List<CmConciliaciones> list = query.getResultList();
            for (CmConciliaciones ent : list) {
                listResult.add(castEntidadNegocio(ent));
            }
        } catch (NoResultException e) {
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    public static CmConciliacion castEntidadNegocio(CmConciliaciones neg) {
        CmConciliacion ent = new CmConciliacion();
        ent.setId(neg.getId());
        ent.setCntPrestadores(new CntPrestador(neg.getCntPrestadoresId().getId()));
        ent.setValor(neg.getValor());
        ent.setPorcentaje(neg.getPorcentaje());
        ent.setValorPagadoEps(neg.getValorPagadoEps());
        ent.setPorcentajePagadoEps(neg.getPorcentajePagadoEps());
        ent.setPorcentajeAceptadoIps(neg.getPorcentajeAceptadoIps());
        ent.setValorAceptadoIps(neg.getValorAceptadoIps());
        ent.setEstadoProceso(neg.getEstadoProceso());
        ent.setCantidadFacturas(neg.getCantidadFacturas());
        ent.setCantidadFacturasRegistradas(neg.getCantidadFacturasRegistradas());
        ent.setHoraFinalizacionRegistro(neg.getHoraFinalizacionRegistro());
        ent.setArchivo(neg.getArchivo());
        ent.setArchivoNombre(neg.getArchivoNombre());
        ent.setArchivoRuta(neg.getArchivoRuta());
        boolean existe = neg.getArchivoExiste() == null? false: neg.getArchivoExiste();
        ent.setArchivoExiste((existe));
        Integer idRadicado = neg.getCmRadicadosList() != null
                && neg.getCmRadicadosList().size() > 0
                ? neg.getCmRadicadosList().get(0).getId() : null;
        ent.setCmRadicado(new CmRadicado(idRadicado));
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        return ent;
    }

    public static CmConciliaciones castNegocioEntidad(CmConciliacion ent) {
        CmConciliaciones neg = new CmConciliaciones();
        neg.setId(ent.getId());
        neg.setCntPrestadoresId(new CntPrestadores(ent.getCntPrestadores().getId()));
        neg.setValor(ent.getValor());
        neg.setPorcentaje(ent.getPorcentaje());
        neg.setValorPagadoEps(ent.getValorPagadoEps());
        neg.setPorcentajePagadoEps(ent.getPorcentajePagadoEps());
        neg.setValorAceptadoIps(ent.getValorAceptadoIps());
        neg.setEstadoProceso(ent.getEstadoProceso());
        neg.setCantidadFacturas(ent.getCantidadFacturas());
        neg.setCantidadFacturasRegistradas(ent.getCantidadFacturasRegistradas());
        neg.setHoraFinalizacionRegistro(ent.getHoraFinalizacionRegistro());
        neg.setPorcentajeAceptadoIps(ent.getPorcentajeAceptadoIps());
        neg.setArchivo(ent.getArchivo());
        neg.setArchivoNombre(ent.getArchivoNombre());
        neg.setArchivoRuta(ent.getArchivoRuta());
        neg.setArchivoExiste(ent.getArchivoExiste());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        return neg;
    }

    @Override
    public List<ReporteConciliacion> reporteConciliacion(int id) throws java.lang.Exception {
        List<ReporteConciliacion> listaReporteConciliacion = new ArrayList();

        try {
            String strQuery = "FROM CmConciliaciones cmd WHERE cmd.id = " + id;

            List<CmConciliaciones> listaResultado = getEntityManager().createQuery(strQuery).getResultList();
            if (listaResultado.size() > 0) {
                for (CmConciliaciones conciliacion : listaResultado) {
                    if (!conciliacion.getCmGlosaRespuestasList().isEmpty()) {
                        List<CmGlosaRespuestas> listaRespuestaGlosa = conciliacion.getCmGlosaRespuestasList();
                        for (CmGlosaRespuestas glosaRespuesta : listaRespuestaGlosa) {
                            CmFacturas factura = glosaRespuesta.getCmFacturasId();
                            int idFactura = factura.getId();
                            String institucion = factura.getIps() != null ? factura.getIps() : "";
                            Date fecha = conciliacion.getFechaHoraCrea();
                            String representanteIps = glosaRespuesta.getRepresentanteIps();
                            String representanteEps = glosaRespuesta.getRepresentanteEps();
                            int radicado = factura.getNumeroRadicado();
                            String numeroFactura = factura.getNumeroFacturado();
                            String documento = glosaRespuesta.getCmGlosaRespuestaDetallesList().size() > 0 ? glosaRespuesta.getCmGlosaRespuestaDetallesList().get(0).getCmDetallesId().getDocumento() : "";
                            BigDecimal valorFactura = factura.getValorFactura();
                            BigDecimal valorGlosa = factura.getValorInicialGlosa();
                            String observacion = glosaRespuesta.getObservacion();
                            BigDecimal valorConciliado = glosaRespuesta.getValorPagado();
                            BigDecimal valorEps = glosaRespuesta.getVaorPagadoEps();
                            BigDecimal valorIps = glosaRespuesta.getValorAceptadoIps();
                            String rangoFechas = "";
                            String nit = factura.getNit();

                            ReporteConciliacion reporte = new ReporteConciliacion();
                            reporte.setId(idFactura);
                            reporte.setIntIdFactura(idFactura);
                            reporte.setStrInstitucion(institucion);
                            reporte.setDtmFecha(fecha);
                            reporte.setStrRepresentanteIPS(representanteIps);
                            reporte.setStrRepresentanteSAVIA(representanteEps);
                            reporte.setIntRadicacion(radicado);
                            reporte.setStrFactura(numeroFactura);
                            reporte.setStrDocumentoPaciente(documento);
                            reporte.setDblValorFactura(valorFactura);
                            reporte.setDblValorGlosa(valorGlosa);
                            reporte.setStrObservacion(observacion);
                            reporte.setDblValorConsolidado(valorConciliado);
                            reporte.setDblValorAceptadoEPS(valorEps);
                            reporte.setDblValorAceptadoIPS(valorIps);
                            reporte.setStrRangoConciliacion(rangoFechas);
                            reporte.setIntNumeroActa(id);
                            reporte.setStrNit(nit);
                            reporte.setStrObservacionGlosa(observacion);
                            listaReporteConciliacion.add(reporte);

                        }
                    }
                }
            }

        } catch (NoResultException e) {
            listaReporteConciliacion = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaReporteConciliacion;
    }

    @Override
    public List<ReporteConciliacion> reporteConciliacionEnvioSapExitoso(int id, Map<String,Integer> wsFacturasExitosas) throws java.lang.Exception {
        List<ReporteConciliacion> listaReporteConciliacion = new ArrayList();
        Map<String, Integer> facturasEnviadasExitosas = new HashMap<>();

        try {
            String strQuery = "FROM CmConciliaciones cmd WHERE cmd.id = " + id;

            List<CmConciliaciones> listaResultado = getEntityManager().createQuery(strQuery).getResultList();
            if (listaResultado.size() > 0) {
                for (CmConciliaciones conciliacion : listaResultado) {
                    if (!conciliacion.getCmGlosaRespuestasList().isEmpty()) {

                        CmRadicados radicadoConciliacion = conciliacion.getCmRadicadosList() != null
                                && !conciliacion.getCmRadicadosList().isEmpty()
                                ? conciliacion.getCmRadicadosList().get(0) : new CmRadicados();
                        List<CmSincronizacionEncabezados> encabezados = radicadoConciliacion.getCmSincronizacionEncabezadosList() != null
                                ? radicadoConciliacion.getCmSincronizacionEncabezadosList() : new ArrayList<>();

                        if (encabezados.isEmpty()) {
                            CmSincronizaciones sincronizaciones = conciliacion.getCmSincronizacionesList() != null
                                    && conciliacion.getCmSincronizacionesList().size() > 0
                                    ? conciliacion.getCmSincronizacionesList().get(0)
                                    : new CmSincronizaciones();
                            encabezados = sincronizaciones.getCmRadicadosId() != null
                                    ? sincronizaciones.getCmRadicadosId().getCmSincronizacionEncabezadosList() : new ArrayList<>();
                        }
                      
                        for (CmSincronizacionEncabezados encabezado : encabezados) {
                            if (CmSincronizacionEncabezado.ESTADO_FINALIZADO == encabezado.getEstado()
                                    || CmSincronizacionEncabezado.ESTADO_SIN_VALORES_EPS == encabezado.getEstado()) {
                                String llave = encabezado.getNumeroDocumento() + "-" + encabezado.getNumeroRadicado();
                                if (facturasEnviadasExitosas.get(llave) == null) {
                                    facturasEnviadasExitosas.put(llave, encabezado.getEstado());
                                }
                            }
                        }
                                                
                        if (encabezados.isEmpty()) {
                          facturasEnviadasExitosas = wsFacturasExitosas;
                        }

                        List<CmGlosaRespuestas> listaRespuestaGlosa = conciliacion.getCmGlosaRespuestasList();
                        for (CmGlosaRespuestas glosaRespuesta : listaRespuestaGlosa) {
                            CmFacturas factura = glosaRespuesta.getCmFacturasId();
                            String llave = factura.getNumeroFacturado() + "-" + factura.getNumeroRadicado();
                            if (facturasEnviadasExitosas.get(llave) != null) {
                                int idFactura = factura.getId();
                                String institucion = factura.getIps() != null ? factura.getIps() : "";
                                Date fecha = conciliacion.getFechaHoraCrea();
                                String representanteIps = glosaRespuesta.getRepresentanteIps();
                                String representanteEps = glosaRespuesta.getRepresentanteEps();
                                int radicado = factura.getNumeroRadicado();
                                String numeroFactura = factura.getNumeroFacturado();
                                String documento = glosaRespuesta.getCmGlosaRespuestaDetallesList().size() > 0 ? glosaRespuesta.getCmGlosaRespuestaDetallesList().get(0).getCmDetallesId().getDocumento() : "";
                                BigDecimal valorFactura = factura.getValorFactura();
                                BigDecimal valorGlosa = factura.getValorInicialGlosa();
                                String observacion = glosaRespuesta.getObservacion();
                                BigDecimal valorConciliado = glosaRespuesta.getValorPagado();
                                BigDecimal valorEps = glosaRespuesta.getVaorPagadoEps();
                                BigDecimal valorIps = glosaRespuesta.getValorAceptadoIps();
                                String rangoFechas = "";
                                String nit = factura.getNit();

                                ReporteConciliacion reporte = new ReporteConciliacion();
                                reporte.setId(idFactura);
                                reporte.setIntIdFactura(idFactura);
                                reporte.setStrInstitucion(institucion);
                                reporte.setDtmFecha(fecha);
                                reporte.setStrRepresentanteIPS(representanteIps);
                                reporte.setStrRepresentanteSAVIA(representanteEps);
                                reporte.setIntRadicacion(radicado);
                                reporte.setStrFactura(numeroFactura);
                                reporte.setStrDocumentoPaciente(documento);
                                reporte.setDblValorFactura(valorFactura);
                                reporte.setDblValorGlosa(valorGlosa);
                                reporte.setStrObservacion(observacion);
                                reporte.setDblValorConsolidado(valorConciliado);
                                reporte.setDblValorAceptadoEPS(valorEps);
                                reporte.setDblValorAceptadoIPS(valorIps);
                                reporte.setStrRangoConciliacion(rangoFechas);
                                reporte.setIntNumeroActa(id);
                                reporte.setStrNit(nit);
                                reporte.setStrObservacionGlosa(observacion);
                                listaReporteConciliacion.add(reporte);
                            }
                        }
                    }
                }
            }

        } catch (NoResultException e) {
            listaReporteConciliacion = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaReporteConciliacion;
    }

    private String getDescripcioSincronizacionEncabezadosFacturas(CmRadicados radicado) {
        Map<Integer, Integer> tipoEstados = new HashMap<>();
        Map<String, Integer> estadosProcesados = new HashMap<>();
        try {

            List<CmSincronizacionEncabezados> encabezados = radicado.getCmSincronizacionEncabezadosList() != null
                    ? radicado.getCmSincronizacionEncabezadosList() : new ArrayList<>();

            for (CmSincronizacionEncabezados encabezado : encabezados) {
                tipoEstados.compute(encabezado.getEstado(), (key, val) -> (val == null) ? 1 : val + 1);
            }

            for (Map.Entry<Integer, Integer> entry : tipoEstados.entrySet()) {
                String llave = CmSincronizacionEncabezado.getEstadoStr(entry.getKey());
                Integer value = entry.getValue();
                estadosProcesados.put(llave, value);
            }
        } catch (Exception e) {
        }
        return estadosProcesados.toString();
    }
    
    private String getDescripcioSincronizacionWsCmFacturas(CmRadicados radicado) {
        Map<Short, Integer> tipoEstados = new HashMap<>();
        Map<String, Integer> estadosProcesados = new HashMap<>();
        try {

            List<WsCmFacturas> wsCmFacturas = radicado.getWsCmFacturasList();

            for (WsCmFacturas wsCmFactura : wsCmFacturas) {
                tipoEstados.compute(wsCmFactura.getEstado(), (key, val) -> (val == null) ? 1 : val + 1);
            }

            for (Map.Entry<Short, Integer> entry : tipoEstados.entrySet()) {
                String llave = WsCmFactura.getEstadoStr(entry.getKey());
                Integer value = entry.getValue();
                estadosProcesados.put(llave, value);
            }
        } catch (Exception e) {
        }
        return estadosProcesados.toString();
    }
       

    private CmSincronizacion getUltimaSincronizacion(CmRadicados radicado) {
        CmSincronizacion sincronizacionOut = new CmSincronizacion();
        try {
            CmSincronizaciones sincronizacionIn = radicado.getCmSincronizacionesList() != null
                    && radicado.getCmSincronizacionesList().size() > 0
                    ? radicado.getCmSincronizacionesList().
                            get(radicado.getCmSincronizacionesList().size() - 1)
                    : new CmSincronizaciones();

            if (sincronizacionIn.getId() != null) {
                sincronizacionOut = CmSincronizacionServicio.castEntidadNegocio(sincronizacionIn);
            }
        } catch (Exception e) {
        }

        return sincronizacionOut;
    }

    private CmRadicados obtenerRadicado(CmConciliaciones neg) {
        CmRadicados radicado = new CmRadicados();
        try {
            radicado = neg.getCmRadicadosList() != null
                    && neg.getCmRadicadosList().size() > 0
                    ? neg.getCmRadicadosList().get(0) : new CmRadicados();

            if (radicado.getId() == null) {
                CmSincronizaciones sincronizaciones = neg.getCmSincronizacionesList() != null
                        && neg.getCmSincronizacionesList().size() > 0
                        ? neg.getCmSincronizacionesList().get(0)
                        : new CmSincronizaciones();

                radicado = sincronizaciones.getCmRadicadosId() != null
                        ? sincronizaciones.getCmRadicadosId() : new CmRadicados();
            }
        } catch (Exception e) {
        }
        return radicado;
    }

}
