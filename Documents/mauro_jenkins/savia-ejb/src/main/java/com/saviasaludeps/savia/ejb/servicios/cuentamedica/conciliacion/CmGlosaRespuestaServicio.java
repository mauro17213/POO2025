/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.conciliacion;


import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaConceptoContable;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmConciliacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaMasivaN;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuestaDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionEncabezado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.ReporteRespuestaGlosa;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmFactura;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaConceptosContables;
import com.saviasaludeps.savia.ejb.entidades.CmConciliaciones;
import com.saviasaludeps.savia.ejb.entidades.CmFacturas;
import com.saviasaludeps.savia.ejb.entidades.CmGlosaMasiva;
import com.saviasaludeps.savia.ejb.entidades.CmGlosaRespuestaDetalles;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.CmGlosaRespuestas;
import com.saviasaludeps.savia.ejb.entidades.CmRadicados;
import com.saviasaludeps.savia.ejb.entidades.CmSincronizacionEncabezados;
import com.saviasaludeps.savia.ejb.entidades.WsCmFacturas;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmGlosaRespuestaRemoto;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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
@Remote(CmGlosaRespuestaRemoto.class)
@Local(CmGlosaRespuestaLocal.class)
public class CmGlosaRespuestaServicio extends GenericoServicio implements CmGlosaRespuestaLocal, CmGlosaRespuestaRemoto {

    public static int NUMERO_GLOSAS_POR_LECTURA = 500;
     
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cms) FROM CmGlosaRespuestas cms ";         
            strQuery += " WHERE cms.cmFacturasId.id = :facuturaId ";
          
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                       case "tipoRespuesta":
                            strQuery += " AND cms.tipoRespuesta LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "id":
                            strQuery += " AND cms.nit LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
       
            Query query = getEntityManager().createQuery(strQuery).setParameter("facuturaId", paramConsulta.getParametroConsulta1());   
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
    public List<CmGlosaRespuesta> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmGlosaRespuesta> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmGlosaRespuestas cms ";         
            strQuery += " WHERE cms.cmFacturasId.id = :facuturaId ";
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                     switch ((String) e.getKey()) {
                         case "id":
                             strQuery += " AND cms.id = '" + e.getValue() + "' ";
                             break;
                             
                         case "cmFactura.id":
                             strQuery += " AND cms.cmFacturasId = '" + e.getValue() + "' ";
                             break;
                         case "tipoRespuesta":
                             strQuery += " AND cms.tipoRespuesta LIKE '%" + e.getValue() + "%' ";
                             break;

                         case "valorCobroDetalle":
                             strQuery += " AND cms.valorCobroDetalle LIKE '%" + e.getValue() + "%' ";
                             break;

                         case "cmConciliacion.id":
                             strQuery += " AND cms.cmConciliacionesId = '" + e.getValue() + "' ";
                             break;

                         case "valorFacturado":
                             strQuery += " AND cms.valorFacturado LIKE '%" + e.getValue() + "%' ";
                             break;

                         case "valorPagado":
                             strQuery += " AND cms.valorPagado LIKE '%" + e.getValue() + "%' ";
                             break;

                         case "vaorPagadoEps":
                             strQuery += " AND cms.vaorPagadoEps LIKE '%" + e.getValue() + "%' ";
                             break;

                         case "valorPendiente":
                             strQuery += " AND cms.valorPendiente LIKE '%" + e.getValue() + "%' ";
                             break;

                         case "valorAceptadoIps":
                             strQuery += " AND cms.valorAceptadoIps LIKE '%" + e.getValue() + "%' ";
                             break;

                         case "estadoSincronizacion":
                             strQuery += " AND cms.estadoSincronizacion = '%" + e.getValue() + "%' ";
                             break;

                         case "fechaHoraCrea":
                             strQuery += " AND cms.fechaHoraCrea LIKE '%" + e.getValue() + "%' ";
                             break;
  
                    }
                }
            }
                 
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                String order = paramConsulta.getOrden().replace("cmConciliacion", "cmConciliacionesId").
                                                        replace("cmFactura", "cmFacturasId").
                                                        replace("valorPagadoEps", "vaorPagadoEps");
                strQuery += " cms." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " cms.fechaHoraCrea DESC";
            }
            Query query = getEntityManager().createQuery(strQuery).
                          setParameter("facuturaId", paramConsulta.getParametroConsulta1());          
           
            List<CmGlosaRespuestas> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CmGlosaRespuestas neg : list) {
                listResult.add( castEntidadNegocio(neg) );
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
    public CmGlosaRespuesta consultar(int id) throws Exception {
        CmGlosaRespuesta obj = null;
        try {
            CmGlosaRespuestas per = (CmGlosaRespuestas) getEntityManager().find(CmGlosaRespuestas.class, id);
            if(per != null){
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
    public int insertar(CmGlosaRespuesta obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al insertar la solicitud");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(CmGlosaRespuesta obj) throws Exception {
        try {
            //obj.setId((int) getEntityManager().merge(castNegocioEntidad(obj)).getId());
            
            CmGlosaRespuestas respuestasGlosa  = castNegocioEntidad(obj);
            
             String hql = "UPDATE CmGlosaRespuestas SET"
                    + " cmFacturasId.id = :cmFacturasId,"
                      + ((respuestasGlosa.getCmConciliacionesId() != null &&
                        respuestasGlosa.getCmConciliacionesId().getId() != null &&
                        respuestasGlosa.getCmConciliacionesId().getId() > 0) ? 
                       " cmConciliacionesId.id = :cmConciliacionesId," : "")
                    + " tipoRespuesta  = :tipoRespuesta,"
                    + " valorCobroDetalle = :valorCobroDetalle,"           
                    + " valorFacturado = :valorFacturado,"               
                    + " valorPagado = :valorPagado,"      
                    + " vaorPagadoEps = :vaorPagadoEps,"            
                    + " valorPendiente = :valorPendiente,"
                    + " valorAceptadoIps = :valorAceptadoIps,"     
                    + " observacion = :observacion,"
                    + " estadoSincronizacion = :estadoSincronizacion"
                    + " WHERE id = :id";
            
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("cmFacturasId", respuestasGlosa.getCmFacturasId().getId());
            if ( respuestasGlosa.getCmConciliacionesId() != null && respuestasGlosa.getCmConciliacionesId().getId() != null &&
                 respuestasGlosa.getCmConciliacionesId().getId() > 0) {
                 query.setParameter("cmConciliacionesId", respuestasGlosa.getCmConciliacionesId().getId());
            }   
            query.setParameter("tipoRespuesta", respuestasGlosa.getTipoRespuesta());
            query.setParameter("valorCobroDetalle", respuestasGlosa.getValorCobroDetalle());
            query.setParameter("valorFacturado", respuestasGlosa.getValorFacturado());
            query.setParameter("valorPagado", respuestasGlosa.getValorPagado());
            query.setParameter("vaorPagadoEps",respuestasGlosa.getVaorPagadoEps());
            query.setParameter("valorPendiente", respuestasGlosa.getValorPendiente());
            query.setParameter("valorAceptadoIps", respuestasGlosa.getValorAceptadoIps());
            query.setParameter("observacion", respuestasGlosa.getObservacion());
            query.setParameter("estadoSincronizacion", respuestasGlosa.getEstadoSincronizacion());
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
    public void actualizaEstadoSincronizacion(CmGlosaRespuesta obj) throws Exception {
        try { 
  
            if (obj != null) {

                String hql = "UPDATE CmGlosaRespuestas SET "
                        + " estadoSincronizacion = :estadoSincronizacion"
                        + " WHERE id = :id";

                Query query = getEntityManager().createQuery(hql);
                query.setParameter("estadoSincronizacion", obj.getEstadoSincronizacion());
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
    public void actualizaEstadoSincronizacion(ParamConsulta paramConsulta) throws Exception {
        try { 
  

                String hql = "UPDATE CmGlosaRespuestas SET "
                        + " estadoSincronizacion = :estadoSincronizacion"
                        + " WHERE cmFacturasId.id = :cmFacturasId ";
                
                if(paramConsulta.getParametroConsulta3() != null){
                     hql += " AND tipoRespuesta = "+paramConsulta.getParametroConsulta3();
                }
                
                if(paramConsulta.getParametroConsulta4() != null){
                     hql += " AND cmGlosaMasivaId.id  = "+paramConsulta.getParametroConsulta4();
                }
                
                if(paramConsulta.getParametroConsulta5() != null){
                     hql += " AND cmConciliacionesId.id  = "+paramConsulta.getParametroConsulta5();
                }         

                Query query = getEntityManager().createQuery(hql);
                query.setParameter("estadoSincronizacion", paramConsulta.getParametroConsulta2());
                query.setParameter("cmFacturasId", paramConsulta.getParametroConsulta1());
                query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CmGlosaRespuesta eliminar(int id) throws Exception {
        CmGlosaRespuesta obj = null;
        try {
            CmGlosaRespuestas per = getEntityManager().find(CmGlosaRespuestas.class, id);
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
    public List<CmGlosaRespuesta> consultarXCmConciliacionesId(int cm_conciliaciones_id) throws Exception {
        List<CmGlosaRespuesta> listaResultado = new ArrayList<>();

        try {
            String strQuery = " FROM CmGlosaRespuestas cg WHERE cg.cmConciliacionesId.id = :id ";
            List<CmGlosaRespuestas> lista = getEntityManager().createQuery(strQuery).setParameter("id", cm_conciliaciones_id).getResultList();

            for (CmGlosaRespuestas cmGlosaRespuestas : lista) {
                listaResultado.add(castEntidadNegocioLargo(cmGlosaRespuestas));
            }

        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }
    
    @Override
    public List<CmGlosaRespuesta> consultarXCmFacturasId(int cm_facturas_id, int tipoRespuesta) throws Exception {
        List<CmGlosaRespuesta> listaResultado = new ArrayList<>();

        try {
            String strQuery = " FROM CmGlosaRespuestas cg WHERE cg.cmFacturasId.id = :id AND cg.tipoRespuesta = :tipoRespuesta ";
            List<CmGlosaRespuestas> lista = getEntityManager().createQuery(strQuery)
                    .setParameter("id", cm_facturas_id)
                    .setParameter("tipoRespuesta", tipoRespuesta)
                    .getResultList();

            for (CmGlosaRespuestas cmGlosaRespuestas : lista) {
                listaResultado.add(castEntidadNegocioLargo(cmGlosaRespuestas));
            }

        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }
    
    @Override
    public List<CmGlosaRespuesta> consultarXCmGlosaRta(int cm_glosa_respuestas_id) throws Exception {
        List<CmGlosaRespuesta> listaResultado = new ArrayList<>();

        try {
            String strQuery = " FROM CmGlosaRespuestas cg WHERE cg.id > 0 AND cg.id = :id ";
            List<CmGlosaRespuestas> lista = getEntityManager().createQuery(strQuery)
                    .setParameter("id", cm_glosa_respuestas_id)
                    .getResultList();

            for (CmGlosaRespuestas cmGlosaRespuestas : lista) {
                listaResultado.add(castEntidadNegocioLargo(cmGlosaRespuestas));
            }

        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }
    
    @Override
    public int consultarCantidadRespuestasPorCanciliacion(int idConciliacion) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cg) FROM CmGlosaRespuestas cg ";
            strQuery += " WHERE  cg.id > 0 AND cg.cmConciliacionesId.id = :id ";
            Query query = getEntityManager().createQuery(strQuery).setParameter("id", idConciliacion);
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
    public int consultarCantidadRespuestasGlosaMasiva(int idGlosaMasiva, int tipoRespuesta, int estadoSincronizacion) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cg) FROM CmGlosaRespuestas cg ";
            strQuery += " WHERE  cg.id > 0 AND cg.cmGlosaMasivaId.id = :id AND cg.tipoRespuesta = :tipo AND cg.estadoSincronizacion = :estadoSincronizacion ";
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("id", idGlosaMasiva);
            query.setParameter("tipo", tipoRespuesta);
            query.setParameter("estadoSincronizacion", estadoSincronizacion);
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
    public List<CmGlosaRespuesta> consultarXCmConciliacionesIdPorBloque(int cm_conciliaciones_id) throws Exception {
        List<CmGlosaRespuesta> listaResultado = new ArrayList<>();
        try {
            int cantidad = consultarCantidadRespuestasPorCanciliacion(cm_conciliaciones_id);
            if (cantidad > 0) {
                int paquetes = calcularNumeroPaquetes(cantidad);
                int paginaInicio  = 0;
                for (int incrementoPagina = 0; incrementoPagina < paquetes; incrementoPagina++) {
                    paginaInicio = incrementoPagina * NUMERO_GLOSAS_POR_LECTURA ;
                    listaResultado.addAll(consultarXCmConciliacionesIdPaginada(cm_conciliaciones_id, paginaInicio , NUMERO_GLOSAS_POR_LECTURA));
                }
            }
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }
    
    @Override
    public List<CmGlosaRespuesta> consultarRespuestasGlosasMasivasEnBloque(int idGlosaMasiva) throws Exception {
        List<CmGlosaRespuesta> listaResultado = new ArrayList<>();
        try {
            int cantidad = consultarCantidadRespuestasGlosaMasiva(idGlosaMasiva, CmGlosaRespuesta.TIPO_RESPUESTA_RESPUESTA, CmGlosaRespuesta.ESTADO_SINCRONIZACION_CREADA);
            if (cantidad > 0) {
                int paquetes = calcularNumeroPaquetes(cantidad);
                int paginaInicio  = 0;
                for (int incrementoPagina = 0; incrementoPagina < paquetes; incrementoPagina++) {
                    paginaInicio = incrementoPagina * NUMERO_GLOSAS_POR_LECTURA ;
                    listaResultado.addAll(consultarXGlosaMasivaIdPaginada(idGlosaMasiva,CmGlosaRespuesta.TIPO_RESPUESTA_RESPUESTA, CmGlosaRespuesta.ESTADO_SINCRONIZACION_CREADA, paginaInicio , NUMERO_GLOSAS_POR_LECTURA));
                }
            }
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    @Override
    public List<CmGlosaRespuesta> consultarXCmConciliacionesIdPaginada(int cm_conciliaciones_id, int paginaInicio, int paginaFinal) throws Exception {
        List<CmGlosaRespuesta> listaResultado = new ArrayList<>();

        try {

            String strQuery = " FROM CmGlosaRespuestas cg WHERE cg.id > 0 AND  cg.cmConciliacionesId.id = :id ";
            List<CmGlosaRespuestas> lista = getEntityManager().createQuery(strQuery)
                    .setParameter("id", cm_conciliaciones_id)
                    .setFirstResult(paginaInicio)
                    .setMaxResults(paginaFinal).getResultList();
            
            for (CmGlosaRespuestas cmGlosaRespuestas : lista) {
                listaResultado.add(castEntidadNegocioLargo(cmGlosaRespuestas));
            }

        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }
    
    @Override
    public List<CmGlosaRespuesta> consultarXGlosaMasivaIdPaginada(int idGlosaMasiva, int tipoRespuesta, int estadoSincronizacion,int paginaInicio, int paginaFinal) throws Exception {
        List<CmGlosaRespuesta> listaResultado = new ArrayList<>();

        try {

            String strQuery = " FROM CmGlosaRespuestas cg WHERE cg.id > 0 AND  cg.cmGlosaMasivaId.id = :id "
                    + " AND cg.tipoRespuesta = :tipo AND cg.estadoSincronizacion = :estadoSincronizacion ";
            List<CmGlosaRespuestas> lista = getEntityManager().createQuery(strQuery)
                    .setParameter("id", idGlosaMasiva)
                    .setParameter("tipo", tipoRespuesta)
                    .setParameter("estadoSincronizacion", estadoSincronizacion)
                    .setFirstResult(paginaInicio)
                    .setMaxResults(paginaFinal).getResultList();
            
            for (CmGlosaRespuestas cmGlosaRespuestas : lista) {
                listaResultado.add(castEntidadNegocioLargo(cmGlosaRespuestas));
            }

        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }
    
    private int calcularNumeroPaquetes(int numeroFacturas) throws Exception {
        int numeroPaquete;
        if (numeroFacturas <= NUMERO_GLOSAS_POR_LECTURA) {
            numeroPaquete = 1;
        } else {
            BigDecimal bigDecimal = new BigDecimal(numeroFacturas).divide(new BigDecimal(NUMERO_GLOSAS_POR_LECTURA));
            numeroPaquete = bigDecimal.intValue();
            bigDecimal = bigDecimal.subtract(new BigDecimal(numeroPaquete));
            if (bigDecimal.compareTo(new BigDecimal(BigInteger.ZERO)) > 0) {
                numeroPaquete = numeroPaquete + 1;
            }
        }
        return numeroPaquete;
    }

    public static CmGlosaRespuesta castEntidadNegocio(CmGlosaRespuestas neg) {
        CmGlosaRespuesta ent = new CmGlosaRespuesta();
        ent.setId(neg.getId());
        if(neg.getCmFacturasId() != null){
            ent.setCmFactura(new CmFactura(neg.getCmFacturasId().getId()));
        }
        ent.setTipoRespuesta(neg.getTipoRespuesta());
        if(neg.getCmConciliacionesId() != null){
             ent.setCmConciliacion(new CmConciliacion(neg.getCmConciliacionesId().getId()));
        }
        
        if(neg.getCmGlosaMasivaId() != null){
            ent.setCmGlosaMasiva(new CmGlosaMasivaN(neg.getCmGlosaMasivaId().getId()));
        }
        
        if (neg.getCmRadicadosList() != null) {
            
           CmRadicados radicado = obtenerUltimoCmRadicadoAsociado(neg);
           boolean visualizarPdf = true;
            if (radicado != null) {
                int cantidadEncabezados = radicado.getCmSincronizacionEncabezadosList().size();
                    cantidadEncabezados = cantidadEncabezados == 0 ? radicado.getWsCmFacturasList().size() : cantidadEncabezados;
                boolean esProcesadaSap = radicado.getEstadoRadicado() && cantidadEncabezados > 0;
                if (cantidadEncabezados > 1) {
                    for (CmSincronizacionEncabezados cmSincronizacionEncabezados : radicado.getCmSincronizacionEncabezadosList()) {
                        if (Objects.equals(cmSincronizacionEncabezados.getFacturaId(), neg.getCmFacturasId().getId())) {
                            esProcesadaSap = CmSincronizacionEncabezado.ESTADO_FINALIZADO == cmSincronizacionEncabezados.getEstado();
                            break;
                        }
                    }
                    
                    for (WsCmFacturas wsCmFacturas : radicado.getWsCmFacturasList()) {
                        if (Objects.equals(wsCmFacturas.getFacturaId(), neg.getCmFacturasId().getId())) {
                            esProcesadaSap = WsCmFactura.ESTADO_EXITOSO  == wsCmFacturas.getEstado();
                            break;
                        }
                    }
                }
                ent.setCmRadicado(new CmRadicado(radicado.getId()));
                visualizarPdf = esProcesadaSap;
                ent.setProcesadaSap(esProcesadaSap);
            }
            ent.setVisualizarPdf(visualizarPdf);   
        }

        ent.setValorCobroDetalle(neg.getValorCobroDetalle());
        ent.setValorFacturado(neg.getValorFacturado());
        ent.setValorPagado(neg.getValorPagado());
        ent.setValorPagadoEps(neg.getVaorPagadoEps());
        ent.setValorPendiente(neg.getValorPendiente());
        ent.setValorAceptadoIps(neg.getValorAceptadoIps());
        ent.setObservacion(neg.getObservacion());
        ent.setEstadoSincronizacion(neg.getEstadoSincronizacion());
        ent.setRepresentanteEps(neg.getRepresentanteEps());
        ent.setRepresentanteIps(neg.getRepresentanteIps());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        return ent;
    }
    
    private static CmRadicados obtenerUltimoCmRadicadoAsociado(CmGlosaRespuestas neg) {
        //es proceso conciliacion o respuesta glosa individual
        int cantidadRadicados = neg.getCmRadicadosList().size();
        CmRadicados radicado = cantidadRadicados > 0 ? neg.getCmRadicadosList().get(cantidadRadicados - 1) : null;

        //es proceso radicacion para conciliaciones masivas  
        if (radicado == null && neg.getCmConciliacionesId() != null) {
            cantidadRadicados = neg.getCmConciliacionesId().getCmRadicadosList().size();
            radicado = cantidadRadicados > 0 ? neg.getCmConciliacionesId().getCmRadicadosList().get(cantidadRadicados - 1) : null;
        }
        
        if(radicado == null){
            cantidadRadicados = neg.getCmRadicadosList1().size();
            radicado = cantidadRadicados > 0 ? neg.getCmRadicadosList1().get(cantidadRadicados - 1) : null;
        }
        
        return radicado;
    }

    public static CmGlosaRespuestas castNegocioEntidad(CmGlosaRespuesta ent) {
        CmGlosaRespuestas neg = new CmGlosaRespuestas();
        neg.setId(ent.getId());
        if (ent.getCmFactura() != null) {
            neg.setCmFacturasId(new CmFacturas(ent.getCmFactura().getId()));
        }
        if (ent.getCmConciliacion() != null) {
            neg.setCmConciliacionesId(new CmConciliaciones(ent.getCmConciliacion().getId()));
        }
        if (ent.getCmGlosaMasiva() != null) {
            neg.setCmGlosaMasivaId(new CmGlosaMasiva(ent.getCmGlosaMasiva().getId()));
        }
        neg.setTipoRespuesta(ent.getTipoRespuesta());
        neg.setValorCobroDetalle(ent.getValorCobroDetalle());
        neg.setValorFacturado(ent.getValorFacturado());
        neg.setValorPagado(ent.getValorPagado());
        neg.setVaorPagadoEps(ent.getValorPagadoEps());
        neg.setValorPendiente(ent.getValorPendiente());
        neg.setValorAceptadoIps(ent.getValorAceptadoIps());
        neg.setObservacion(ent.getObservacion());
        neg.setEstadoSincronizacion(ent.getEstadoSincronizacion());
        neg.setRepresentanteEps(ent.getRepresentanteEps());
        neg.setRepresentanteIps(ent.getRepresentanteIps());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        return neg;
    }
    
    public static CmGlosaRespuesta castEntidadNegocioLargo(CmGlosaRespuestas per) {
        CmGlosaRespuesta obj = new CmGlosaRespuesta();
        obj.setListaGlosaRespuestaDetalle(new ArrayList<>());
        obj.setId(per.getId());
        obj.setEstadoSincronizacion(per.getEstadoSincronizacion());
        obj.setTipoRespuesta(per.getTipoRespuesta());
        if (per.getCmFacturasId() != null) {
            CmFactura factura = new CmFactura(per.getCmFacturasId().getId(),
                    per.getCmFacturasId().getEstadoFactura(),
                    per.getCmFacturasId().getNumeroRadicado(),
                    per.getCmFacturasId().getNit(),
                    per.getCmFacturasId().getIps(),
                    per.getCmFacturasId().getMaeRegimenId(),
                    per.getCmFacturasId().getFechaRadicacion(),
                    per.getCmFacturasId().getFechaPrestacion(),
                    per.getCmFacturasId().getMaeRegimenValor(),
                    per.getCmFacturasId().getValorFactura(),
                    per.getCmFacturasId().getValorCopago()
            );
            factura.setNumeroFacturado(per.getCmFacturasId().getNumeroFacturado());
            factura.setNumeroContrato(per.getCmFacturasId().getNumeroContrato());
            obj.setCmFactura(factura);

            for (CmGlosaRespuestaDetalles glosaDetalles : per.getCmGlosaRespuestaDetallesList()) {
                
                List<CmAuditoriaConceptoContable> conceptosContables = new ArrayList<>();
                
                for (CmAuditoriaConceptosContables conceptoContableNeg : glosaDetalles.getCmDetallesId().getCmAuditoriaConceptosContablesList()) {
                    CmAuditoriaConceptoContable conceptoCantableEnt = new CmAuditoriaConceptoContable();
                    conceptoCantableEnt.setId(conceptoContableNeg.getId());
                    conceptoCantableEnt.setCmDetalle(new CmDetalle(conceptoContableNeg.getCmDetallesId().getId()));
                    conceptoCantableEnt.setMaeConceptosId(conceptoContableNeg.getMaeConceptosId());
                    conceptoCantableEnt.setMaeConceptosCodigo(conceptoContableNeg.getMaeConceptosCodigo());
                    conceptoCantableEnt.setMaeConceptosValor(conceptoContableNeg.getMaeConceptosValor());
                    conceptoCantableEnt.setMaeCentroCostoId(conceptoContableNeg.getMaeCentroCostoId());
                    conceptoCantableEnt.setMaeCentroCostoCodigo(conceptoContableNeg.getMaeCentroCostoCodigo());
                    conceptoCantableEnt.setMaeCentroCostoValor(conceptoContableNeg.getMaeCentroCostoValor());
                    conceptoCantableEnt.setPorcentaje(conceptoContableNeg.getPorcentaje());
                    conceptoCantableEnt.setMunicipioAfiliado(conceptoContableNeg.getMunicipioAfiliado());
                    conceptoCantableEnt.setCodigoMunicipio(conceptoContableNeg.getCodigoMunicipio());
                    conceptoCantableEnt.setUsuarioCrea(conceptoContableNeg.getUsuarioCrea());
                    conceptoCantableEnt.setFechaHoraCrea(conceptoContableNeg.getFechaHoraCrea());
                    conceptoCantableEnt.setTerminalCrea(conceptoContableNeg.getTerminalCrea());
                    conceptosContables.add(conceptoCantableEnt);   
                }

                obj.getListaGlosaRespuestaDetalle().add(new CmGlosaRespuestaDetalle(glosaDetalles.getId(), glosaDetalles.getDocumento(),
                        new CmGlosaRespuesta(glosaDetalles.getCmGlosaRespuestasId().getId()),
                        new CmDetalle(glosaDetalles.getCmDetallesId().getId(),
                                glosaDetalles.getCmDetallesId().getDocumento(),
                                glosaDetalles.getCmDetallesId().getNombreCompletoAfiliado(),
                                conceptosContables),
                         glosaDetalles.getValorPagadoEps()
                ));
            }

        }

        CmRadicados radicado = obtenerUltimoCmRadicadoAsociado(per);
        if (radicado != null) {
            obj.setCmRadicado(new CmRadicado(radicado.getId()));
        }
        
        obj.setValorPagadoEps(per.getVaorPagadoEps());
        obj.setValorAceptadoIps(per.getValorAceptadoIps());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        return obj;
    }

    @Override
    public List<ReporteRespuestaGlosa> reporteRespuestaGlosa(int idGlosa) {
        List<ReporteRespuestaGlosa> listaReportes = new ArrayList();
        try {
            String strQuery = "FROM CmGlosaRespuestas cgr WHERE cgr.id = "+ idGlosa;
            List<CmGlosaRespuestas> respuesta = getEntityManager().createQuery(strQuery).getResultList();
            for(CmGlosaRespuestas glosa : respuesta){
                CmFacturas factura = glosa.getCmFacturasId() != null ? glosa.getCmFacturasId() : null;
                List<CmGlosaRespuestaDetalles> glosaDetalles = glosa.getCmGlosaRespuestaDetallesList() != null ?
                                                               glosa.getCmGlosaRespuestaDetallesList() : new ArrayList();
                
                int id = idGlosa;
                String strNumeroDocumento = factura != null ? factura.getNumeroFacturado() : "";
                String strFactura = factura != null ? factura.getNumeroFacturado() : "";
                String strRadicacionRG = ""+id;
                Date dtmFechaFactura = factura != null ? factura.getFechaPrestacion() : null;
                String strProveedor = factura != null ? factura.getIps() : "";
                String strRadicacion = factura != null ? ""+factura.getNumeroRadicado() : "";
                Date dtmFechaRadicacion = factura != null ? factura.getFechaRadicacion() : null;
                String strNit = factura != null ? factura.getNit() : "";
                String strItem = "";
                for(CmGlosaRespuestaDetalles glosaDetalle : glosaDetalles){
                    String strDocumento = glosaDetalle.getDocumento();
                    String strServicio = glosaDetalle.getCmDetallesId() != null? glosaDetalle.getCmDetallesId().getMaServicioValor() : "";
                    BigDecimal dblValorFacturado = glosaDetalle.getCmDetallesId() != null? glosaDetalle.getCmDetallesId().getValorFacturado() : new BigDecimal("0");
                    BigDecimal dblValorPagado = glosaDetalle.getValorPagadoEps();
                    BigDecimal dblValorAceptado = glosaDetalle.getValorAceptadoIps();
                    BigDecimal dblValorPendiente = glosaDetalle.getValorPendiente();
                    String strObservacion = glosaDetalle.getObservacion();
                    String strGlosa =  glosaDetalle.getCmDetallesId() != null  ? 
                                       glosaDetalle.getCmDetallesId().getMotivoGlosa() : "" ;
                    strGlosa =  strGlosa == null ? "" : strGlosa;
                    String strDetalleGlosa = glosaDetalle.getObservacion() != null ? glosaDetalle.getObservacion() : "";
                    
                    //LLenado del reporte
                    ReporteRespuestaGlosa reporte = new ReporteRespuestaGlosa();
                    reporte.setId(id);
                    reporte.setStrNumeroDocumento(strNumeroDocumento);
                    reporte.setStrFactura(strFactura);
                    reporte.setStrRadicacionRG(strRadicacionRG);
                    reporte.setDtmFechaFactura(dtmFechaFactura);
                    reporte.setStrProveedor(strProveedor);
                    reporte.setStrRadicacion(strRadicacion);
                    reporte.setDtmFechaRadicacion(dtmFechaRadicacion);
                    reporte.setStrNit(strNit);
                    reporte.setStrItem(strItem);
                    reporte.setStrDocumento(strDocumento);
                    reporte.setStrServicio(strServicio);
                    reporte.setDblValorFacturado(dblValorFacturado);
                    reporte.setDblValorPagado(dblValorPagado);
                    reporte.setDblValorAceptado(dblValorAceptado);
                    reporte.setDblValorPendiente(dblValorPendiente);
                    reporte.setStrObservacion(strObservacion);
                    reporte.setStrGlosa(strGlosa);
                    reporte.setStrDetalleGlosa(strDetalleGlosa);
                    
                    listaReportes.add(reporte);
                }
            }
        }catch (NoResultException e) {
            listaReportes = new ArrayList();
        } catch (Exception e) {
            listaReportes = new ArrayList();
        }finally {
            cerrarEntityManager();
        }
        
        return listaReportes;
    }
    @Override
    public boolean verificacionEnvioSapExitoso(int idGlosa) {
        boolean verificacionExisosa = false;
        try {
            String strQuery = "FROM CmGlosaRespuestas cgr WHERE cgr.id = "+ idGlosa;
            List<CmGlosaRespuestas> respuesta = getEntityManager().createQuery(strQuery).getResultList();
            for(CmGlosaRespuestas glosa : respuesta){
                CmRadicados radicadoConciliacion = glosa.getCmRadicadosList() != null && !glosa.getCmRadicadosList().isEmpty() ?
                                                   glosa.getCmRadicadosList().get(0) : new CmRadicados();
                
                if(radicadoConciliacion.getId() == null){
                     radicadoConciliacion =  !glosa.getCmRadicadosList1().isEmpty() ? glosa.getCmRadicadosList1().get(0) : new CmRadicados();
                }
                  
                if(CmGlosaRespuesta.TIPO_RESPUESTA_RESPUESTA== glosa.getTipoRespuesta() && radicadoConciliacion.getId() == null){
                      BigDecimal eps = Optional.ofNullable(glosa.getVaorPagadoEps()).orElse(new BigDecimal(BigInteger.ZERO)); 
                      BigDecimal ips = Optional.ofNullable(glosa.getValorAceptadoIps()).orElse(new BigDecimal(BigInteger.ZERO)); 
                      if(eps.compareTo(new BigDecimal(BigInteger.ZERO)) == 0 && 
                         ips.compareTo(new BigDecimal(BigInteger.ZERO)) == 0){
                          verificacionExisosa = true;
                          return verificacionExisosa ;
                      }
                }
                
                CmFacturas factura = glosa.getCmFacturasId() != null ? glosa.getCmFacturasId() : null;
                
                
                List<CmSincronizacionEncabezados> encabezados = radicadoConciliacion.getCmSincronizacionEncabezadosList() != null ? 
                                                   radicadoConciliacion.getCmSincronizacionEncabezadosList() : new ArrayList<>();
                 
                if(factura != null && radicadoConciliacion.getId() == null ){
                  String numeroFacturado = factura.getNumeroFacturado();
                  int numeroRadicado  = factura.getNumeroRadicado();
                  String strQuery2 = "FROM CmSincronizacionEncabezados cse WHERE cse.numeroDocumento = '"+ numeroFacturado+"' AND cse.numeroRadicado = "+numeroRadicado;
                  encabezados = getEntityManager().createQuery(strQuery2).getResultList();
                }
                
                for (CmSincronizacionEncabezados encabezado : encabezados) {
                    if(CmSincronizacionEncabezado.ESTADO_FINALIZADO == encabezado.getEstado() ||
                       CmSincronizacionEncabezado.ESTADO_SIN_VALORES_EPS == encabezado.getEstado() ){
                       verificacionExisosa = true;
                    }
                }
                
               List<WsCmFacturas> wsFacturas = radicadoConciliacion.getWsCmFacturasList() != null ? radicadoConciliacion.getWsCmFacturasList():new ArrayList<>();
               for (WsCmFacturas wsFactura : wsFacturas) {
                    if(WsCmFactura.ESTADO_EXITOSO == wsFactura.getEstado() ||
                       WsCmFactura.ESTADO_SIN_VALORES_EPS == wsFactura.getEstado() ){
                       verificacionExisosa = true;
                    }
                    break;
               }            
            }
        }catch (NoResultException e) {
            verificacionExisosa = false;
        } catch (Exception e) {
             verificacionExisosa = false;
        }finally {
            cerrarEntityManager();
        }
        
        return verificacionExisosa;
    }
    
    @Override
    public int consultarCantidadPorConciliacion(int cm_conciliaciones_id) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cmg) FROM CmGlosaRespuestas cmg ";         
            strQuery += " WHERE cmg.cmConciliacionesId.id = :idConciliacion";
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
    
    
}
