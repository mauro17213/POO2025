/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCierre;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDevolucion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMasivaN;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmDevolucionMasivaN;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmConciliacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionEncabezado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionEncabezadoResumen;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCarga;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaCierres;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaDevoluciones;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaMasiva;
import com.saviasaludeps.savia.ejb.entidades.CmConciliaciones;
import com.saviasaludeps.savia.ejb.entidades.CmFacturas;
import com.saviasaludeps.savia.ejb.entidades.CmGlosaRespuestas;
import com.saviasaludeps.savia.ejb.entidades.CmRadicados;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargas;
import com.saviasaludeps.savia.ejb.entidades.CmSincronizacionEncabezados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionEncabezadoRemoto;
import java.math.BigDecimal;
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
 * @author yjimenez
 */
@Stateless
@Remote(CmSincronizacionEncabezadoRemoto.class)
@Local(CmSincronizacionEncabezadoLocal.class)
public class CmSincronizacionEncabezadoServicio extends GenericoServicio implements CmSincronizacionEncabezadoLocal, CmSincronizacionEncabezadoRemoto {

    @Override
    public  int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception{
         int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cmse) FROM CmSincronizacionEncabezados cmse "
                    + "WHERE cmse.cmRadicadosId.id = :cmRadicadosId ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                      switch ((String) e.getKey()) {
                        case "cmRadicadosId":
                            strQuery += "AND cmse.cmRadicadosId.id = " + (String) e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND cmse.estado = '" + (String) e.getValue() + "' ";
                            break;
                        case "numeroDocumento":
                            strQuery += "AND cmse.numeroDocumento = '" + (String) e.getValue() + "' ";
                            break;
                        case "numeroRadicado":
                            strQuery += "AND cmse.numeroRadicado = '" + (String) e.getValue() + "' ";
                            break;
                        case "regimen":
                            strQuery += "AND cmse.regimen = '" + (String) e.getValue() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND cmse.usuarioCrea = '" + (String) e.getValue() + "' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                     .setParameter("cmRadicadosId", paramConsulta.getParametroConsulta1())
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
    public List<CmSincronizacionEncabezado> consultarLista(ParamConsulta paramConsulta) throws Exception{
         List<CmSincronizacionEncabezado> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmSincronizacionEncabezados cmse "
                    + "WHERE cmse.cmRadicadosId.id = :cmRadicadosId  ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cmRadicadosId":
                            strQuery += "AND cmse.cmRadicadosId.id = " + (String) e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND cmse.estado = '" + (String) e.getValue() + "' ";
                            break;
                        case "numeroDocumento":
                            strQuery += "AND cmse.numeroDocumento = '" + (String) e.getValue() + "' ";
                            break;
                        case "numeroRadicado":
                            strQuery += "AND cmse.numeroRadicado = '" + (String) e.getValue() + "' ";
                            break;
                        case "regimen":
                            strQuery += "AND cmse.regimen = '" + (String) e.getValue() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND cmse.usuarioCrea = '" + (String) e.getValue() + "' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "cmse." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "cmse.fechaHoraCrea DESC";
            }
            List<CmSincronizacionEncabezados> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .setParameter("cmRadicadosId", paramConsulta.getParametroConsulta1())
                    .getResultList();
            for (CmSincronizacionEncabezados per : list) {
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
    public CmSincronizacionEncabezado consultar(int id) throws Exception {
        CmSincronizacionEncabezado obj = null;
        try {
            CmSincronizacionEncabezados per = (CmSincronizacionEncabezados) getEntityManager().find(CmSincronizacionEncabezados.class, id);
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
    public int insertar(CmSincronizacionEncabezado obj) throws Exception {
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
    public void actualizar(CmSincronizacionEncabezado obj) throws Exception {
       try {  
            CmSincronizacionEncabezados encabezados = castNegocioEntidad(obj);       
            
            String hql = "UPDATE CmSincronizacionEncabezados SET"
                    + " estado = :estado,"
                    + " proveedorNit = :proveedorNit,"
                    + " numeroDocumento = :numeroDocumento,"
                    + " numeroRadicado  = :numeroRadicado,"                  
                    + " regimen = :regimen,"
                    + " valorDocumento = :valorDocumento,"
                    + " valorPagado = :valorPagado,"
                    + " valorGlosado = :valorGlosado,"
                    + " fechaHoraDocumento = :fechaHoraDocumento,"
                    + " fechaHoraProceso = :fechaHoraProceso,"    
                    + " usuarioCrea = :usuarioCrea, "
                    + " terminalCrea = :terminalCrea, "
                    + " fechaHoraCrea = :fechaHoraCrea, "
                    + " cmRadicadosId.id = :cmRadicadosId "
                    + " WHERE id = :id";
             
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado",encabezados.getEstado());
            query.setParameter("proveedorNit", encabezados.getProveedorNit());
            query.setParameter("numeroDocumento", encabezados.getNumeroDocumento());
            query.setParameter("numeroRadicado", encabezados.getNumeroRadicado());     
            query.setParameter("regimen",encabezados.getRegimen());
            query.setParameter("valorDocumento", encabezados.getValorDocumento());
            query.setParameter("valorPagado", encabezados.getValorPagado());
            query.setParameter("valorGlosado", encabezados.getValorGlosado());
            query.setParameter("fechaHoraDocumento", encabezados.getFechaHoraDocumento());
            query.setParameter("fechaHoraProceso", encabezados.getFechaHoraProceso());
            query.setParameter("usuarioCrea", encabezados.getUsuarioCrea());
            query.setParameter("terminalCrea", encabezados.getTerminalCrea());
            query.setParameter("fechaHoraCrea", encabezados.getFechaHoraCrea());
            query.setParameter("cmRadicadosId", encabezados.getCmRadicadosId().getId()); 
            query.setParameter("id", encabezados.getId());
            query.executeUpdate();
           
           // obj.setId((int) getEntityManager().merge(castNegocioEntidad(obj)).getId());
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
  
    @Override
    public void actualizarEstadoExitoso(int idCmRadicado, int idFactura) throws Exception {
        try {

            String hql = "UPDATE CmSincronizacionEncabezados cms SET "
                    + " cms.estado = 1 "
                    + " WHERE cms.facturaId = :idFactura AND cms.cmRadicadosId.id = : idCmRadicado";
             
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("idFactura", idFactura);
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
    public CmSincronizacionEncabezado eliminar(int id) throws Exception {
        CmSincronizacionEncabezado obj = null;
        try {
            CmSincronizacionEncabezados per = getEntityManager().find(CmSincronizacionEncabezados.class, id);
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
    public List<CmSincronizacionEncabezado> consultarDetalles(int id) throws Exception {
        List<CmSincronizacionEncabezado> lista = new ArrayList();

        try {
            String query = "SELECT csr FROM CmSincronizacionEncabezados csr WHERE csr.cmRadicadosId.id='" + id + "' AND csr.estado='0'";

            List<CmSincronizacionEncabezados> resultado = getEntityManager().createQuery(query.toString()).getResultList();

            for (CmSincronizacionEncabezados enca : resultado) {
                lista.add(castEntidadNegocio(enca));
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
    public List<CmSincronizacionEncabezado> consultarTodos(int id) throws Exception {
        List<CmSincronizacionEncabezado> lista = new ArrayList();

        try {
            String query = "SELECT csr FROM CmSincronizacionEncabezados csr WHERE csr.cmRadicadosId.id='" + id + "' order by csr.id DESC";

            List<CmSincronizacionEncabezados> resultado = getEntityManager().createQuery(query.toString()).getResultList();

            for (CmSincronizacionEncabezados enca : resultado) {
                lista.add(castEntidadNegocio(enca));
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
    public List<CmSincronizacionEncabezadoResumen> consultarResumenEncabezado(int idRadicado) {
        
        List<CmSincronizacionEncabezadoResumen> lista = new ArrayList();
         
        String strQuery = "SELECT SUM(cms.valorDocumento) AS totalDocumeto, "
                + " SUM(cms.valorGlosado) AS totalGlosado, "
                + " SUM(cms.valorPagado) as totalPagado, "
                + " cms.estado  FROM CmSincronizacionEncabezados cms ";
        strQuery += " WHERE cms.cmRadicadosId.id = " + idRadicado + "  group by cms.estado ";

        List<Object> resultado = getEntityManager().createQuery(strQuery).getResultList();
        
        for (Object item : resultado) {
            CmSincronizacionEncabezadoResumen resumen = new CmSincronizacionEncabezadoResumen();
            Object sumas[] = (Object[]) item;
            resumen.setValorDocumento((BigDecimal) sumas[0]);
            resumen.setValorGlosado((BigDecimal) sumas[1]);
            resumen.setValorPagado((BigDecimal) sumas[2]);
            resumen.setTipoEstadoStr(CmSincronizacionEncabezado.getDescripcionEstadoControlStr((int) sumas[3]));
            lista.add(resumen);
        }
        return lista;
    }
    
    @Override
    public CmSincronizacionEncabezado consultarEnacabezadoNitNumeroDocumento(String nitProveedor, String numeroDocumento) throws Exception {
        CmSincronizacionEncabezado encabezado = null;

        try {
            String query = "SELECT csr FROM CmSincronizacionEncabezados csr WHERE csr.proveedorNit='" + nitProveedor + "' AND csr.numeroDocumento='" + numeroDocumento + "' AND csr.estado='0' ORDER BY csr.id DESC";

            List<CmSincronizacionEncabezados> resultado = getEntityManager().createQuery(query.toString()).getResultList();

            if (resultado != null) {
                encabezado = castEntidadNegocio(resultado.get(0));
            }
            

        } catch (NoResultException e) {
            encabezado = null;
        } catch (Exception e) {
            encabezado = null;
        } finally {
            cerrarEntityManager();
        }

        return encabezado;
    }
    
     @Override
    public CmSincronizacionEncabezado consultarEnacabezadoNitNumeroRadicado(String nitProveedor, String numeroRadicado) throws Exception {
        CmSincronizacionEncabezado encabezado = null;

        try {
            String query = "SELECT csr FROM CmSincronizacionEncabezados csr WHERE csr.proveedorNit='" + nitProveedor + "' AND csr.numeroRadicado='" + numeroRadicado + "' ORDER BY csr.id DESC";

            List<CmSincronizacionEncabezados> resultado = getEntityManager().createQuery(query.toString()).getResultList();

            if (resultado != null) {
                encabezado = castEntidadNegocio(resultado.get(0));
            }
            

        } catch (NoResultException e) {
            encabezado = null;
        } catch (Exception e) {
            encabezado = null;
        } finally {
            cerrarEntityManager();
        }

        return encabezado;
    }
    
     @Override
    public CmSincronizacionEncabezado consultarEnacabezadoPorNitConcecutivo(String nitProveedor, int concecutivo) throws Exception {
        CmSincronizacionEncabezado encabezado = null;

        try {
            String query = "SELECT csr FROM CmSincronizacionEncabezados csr WHERE csr.proveedorNit='" + nitProveedor + "' AND csr.facturaId =" + concecutivo + " AND csr.estado='0' ORDER BY csr.id DESC";

            List<CmSincronizacionEncabezados> resultado = getEntityManager().createQuery(query.toString()).getResultList();

            if (resultado != null) {
                encabezado = castEntidadNegocio(resultado.get(0));
            }
            

        } catch (NoResultException e) {
            encabezado = null;
        } catch (Exception e) {
            encabezado = null;
        } finally {
            cerrarEntityManager();
        }

        return encabezado;
    }
    
     @Override
    public CmSincronizacionEncabezado consultarPorRadicadoFactura(int idCmRadicado, int idCmFactura) throws Exception {

        CmSincronizacionEncabezado encabezado = new CmSincronizacionEncabezado();

        try {
            if ( idCmRadicado > 0 && idCmFactura > 0 ) {
                String query = "SELECT csr FROM CmSincronizacionEncabezados csr WHERE csr.cmRadicadosId.id=" + idCmRadicado + " AND csr.facturaId=" + idCmFactura + " ORDER BY csr.id DESC";
                List<CmSincronizacionEncabezados> encabezadosEncontrados = getEntityManager().createQuery(query.toString()).getResultList();
                if (encabezadosEncontrados != null && !encabezadosEncontrados.isEmpty()) {
                    encabezado = castEntidadNegocio(encabezadosEncontrados.get(0));
                }
            }
        } catch (NoResultException e) {
            encabezado = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }

        return encabezado;
    }
    
    
   @Override
    public CmSincronizacionEncabezado consultarPorRadicadoFacturaSinProcesar(int idCmRadicado, int idCmFactura) throws Exception {

        CmSincronizacionEncabezado encabezado = new CmSincronizacionEncabezado();

        try {
            if ( idCmRadicado > 0 && idCmFactura > 0 ) {
                String query = "SELECT csr FROM CmSincronizacionEncabezados csr WHERE csr.cmRadicadosId.id=" + idCmRadicado + " AND csr.facturaId=" + idCmFactura + " AND csr.estado='0' ORDER BY csr.id DESC";
                List<CmSincronizacionEncabezados> encabezadosEncontrados = getEntityManager().createQuery(query.toString()).getResultList();
                if (encabezadosEncontrados != null && !encabezadosEncontrados.isEmpty()) {
                    encabezado = castEntidadNegocio(encabezadosEncontrados.get(0));
                }
            }
        } catch (NoResultException e) {
            encabezado = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }

        return encabezado;
    }

    public static CmSincronizacionEncabezados castNegocioEntidad(CmSincronizacionEncabezado obj) {
        CmSincronizacionEncabezados per = new CmSincronizacionEncabezados();
        per.setId(obj.getId());
        per.setCmRadicadosId(new CmRadicados(obj.getCmRadicadosId().getId()));
        per.setEstado(obj.getEstado());
        per.setProveedorNit(obj.getProveedorNit());
        per.setNumeroDocumento(obj.getNumeroDocumento());
        per.setNumeroRadicado(obj.getNumeroRadicado());
        per.setRegimen(obj.getRegimen());
        per.setFacturaId(obj.getFactura());
        per.setValorDocumento(obj.getValorDocumento());
        per.setValorPagado(obj.getValorPagado());
        per.setValorGlosado(obj.getValorGlosado());
        per.setValorCopago(obj.getValorCopago());
        per.setFechaHoraDocumento(obj.getFechaHoraDocumento());
        per.setFechaHoraProceso(obj.getFechaHoraProceso());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        return per;
    }

    private CmSincronizacionEncabezado castEntidadNegocio(CmSincronizacionEncabezados enca) {
        CmSincronizacionEncabezado encabezado = new CmSincronizacionEncabezado();
        encabezado.setId(enca.getId());
        encabezado.setEstado(enca.getEstado());
        encabezado.setProveedorNit(enca.getProveedorNit());
        encabezado.setNumeroDocumento(enca.getNumeroDocumento());
        encabezado.setNumeroRadicado(enca.getNumeroRadicado());
        encabezado.setFactura(enca.getFacturaId());
        encabezado.setRegimen(enca.getRegimen());
        encabezado.setValorDocumento(enca.getValorDocumento());
        encabezado.setValorPagado(enca.getValorPagado());
        encabezado.setValorGlosado(enca.getValorGlosado());
        encabezado.setValorCopago(enca.getValorCopago());
        encabezado.setCuotaModeradora(enca.getCuotaModeradora());
        encabezado.setContrato(enca.getContrato());
        encabezado.setFechaHoraDocumento(enca.getFechaHoraDocumento());
        encabezado.setFechaHoraProceso(enca.getFechaHoraProceso());
        encabezado.setUsuarioCrea(enca.getUsuarioCrea());
        encabezado.setFechaHoraCrea(enca.getFechaHoraCrea());
        encabezado.setTerminalCrea(enca.getTerminalCrea());
        
        CmRadicado radicado = new CmRadicado();
        if(enca.getCmRadicadosId().getId() != null){
            radicado.setId(enca.getCmRadicadosId().getId());
            
            CmGlosaRespuestas glosaRespuestas = enca.getCmRadicadosId().getCmGlosaRespuestasId() ;
            if(glosaRespuestas != null){
                radicado.setCmGlosaRespuesta( new CmGlosaRespuesta(glosaRespuestas.getId()) );
                CmFacturas facturas = glosaRespuestas.getCmFacturasId();
                CmRipsCargas ripsCargas = facturas.getCmRipsCargasId();
                if (ripsCargas != null && ripsCargas.getId() != null && ripsCargas.getCntContratosId() != null) {
                   encabezado.setIdContrato(ripsCargas.getCntContratosId().getId());
                }
            }

            CmAuditoriaCierres auditoriaCierres = enca.getCmRadicadosId().getCmAuditoriaCierresId();
            if( auditoriaCierres != null ){
                 radicado.setCmAuditoriaCierre(new CmAuditoriaCierre(auditoriaCierres.getId()));
                 CmFacturas facturas = auditoriaCierres.getCmFacturasId();
                 CmRipsCargas ripsCargas = facturas.getCmRipsCargasId();
                 if (ripsCargas != null && ripsCargas.getId() != null && ripsCargas.getCntContratosId() != null) {
                   encabezado.setIdContrato(ripsCargas.getCntContratosId().getId());
                 } 
            }

            CmAuditoriaDevoluciones auditoriaDevoluciones = enca.getCmRadicadosId().getCmAuditoriaDevolucionesId() ;
            if(auditoriaDevoluciones!= null){
               radicado.setCmAuditoriaDevolucion(new CmAuditoriaDevolucion(auditoriaDevoluciones.getId()));
               CmFacturas facturas = auditoriaDevoluciones.getCmFacturasId();
               CmRipsCargas ripsCargas = facturas.getCmRipsCargasId();
               if (ripsCargas != null && ripsCargas.getId() != null && ripsCargas.getCntContratosId() != null) {
                  encabezado.setIdContrato(ripsCargas.getCntContratosId().getId());
               } 
            }
            
            CmConciliaciones conciliaciones =  enca.getCmRadicadosId().getCmConciliacionesId();
            if(conciliaciones!= null){
               radicado.setCmConciliacion(new CmConciliacion(conciliaciones.getId()));
            }
            
            CmAuditoriaMasiva auditoriaMasivaN =  enca.getCmRadicadosId().getCmAuditoriaMasivaId();
            if(auditoriaMasivaN!= null){
               radicado.setCmAuditoriaMasivaN(new CmAuditoriaMasivaN(auditoriaMasivaN.getId()));
            }
            
            CmAuditoriaDevoluciones devolucionMasivaN =  enca.getCmRadicadosId().getCmAuditoriaDevolucionesId();
            if(devolucionMasivaN!= null){
               radicado.setCmDevolucionMasivaN(new CmDevolucionMasivaN(devolucionMasivaN.getId()));
            }
            
            Integer cmRipsCargaId = enca.getCmRadicadosId().getCmRipsCargasId() != null ? 
                                    enca.getCmRadicadosId().getCmRipsCargasId().getId() : null;
            if(cmRipsCargaId != null){
               radicado.setCmRipsCarga(new CmRipsCarga(cmRipsCargaId));
            }
        }
        
        encabezado.setCmRadicadosId(radicado);
        return encabezado;
    }

}
