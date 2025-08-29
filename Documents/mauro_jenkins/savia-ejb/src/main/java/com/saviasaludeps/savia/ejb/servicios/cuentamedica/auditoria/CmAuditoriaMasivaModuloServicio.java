/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria;


import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegRegistroNovedad;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCierre;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupo;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFacturasSinAutorizaciones;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionEncabezado;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCarga;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegRegistroNovedades;
import com.saviasaludeps.savia.ejb.entidades.CmFacturas;
import com.saviasaludeps.savia.ejb.entidades.CmRadicados;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargas;
import com.saviasaludeps.savia.ejb.entidades.CmSincronizacionEncabezados;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.entidades.GnMaestros;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.servicios.administracion.MaestroServicio;
import com.saviasaludeps.savia.ejb.servicios.aseguramiento.NovedadAfiliadoServicio;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaMasivaModuloRemoto;
import java.util.Optional;

/**
 *
 * @author jperezn
 */
@Stateless
@Remote(CmAuditoriaMasivaModuloRemoto.class)
@Local(CmAuditoriaMasivaModuloLocal.class)
public class CmAuditoriaMasivaModuloServicio extends GenericoServicio implements CmAuditoriaMasivaModuloLocal, CmAuditoriaMasivaModuloRemoto {

    public final static String ESTADOS_FACTURA_BUSQUEDA_STANDARD =  
            CmFactura.ESTADO_FACTURA_SIN_AUDITORIA + "," +
            CmFactura.ESTADO_FACTURA_EN_AUDITORIA + ","  +
            CmFactura.ESTADO_FACTURA_GLOSADA + ","  +
            CmFactura.ESTADO_FACTURA_ESPERA_SAP_PARA_CIERRE_AUDITORIA+ ","  +
            CmFactura.ESTADO_FACTURA_SIN_PROCESAR + ","  +
            CmFactura.ESTADO_FACTURA_ESPERA_SAP_PARA_DEVOLUCION ;
    
     public final static String ESTADOS_AUDITORIA_BUSQUEDA_STANDARD =  
            CmFactura.TIPO_AUDITORIA_SIN_AUDITORIA +  "," +
            CmFactura.TIPO_AUDITORIA_PERTINENCIA_TECNICA +","+
            CmFactura.TIPO_AUDITORIA_PERTINENCIA_MEDICA;
     
    public final static String POST_FIJO_BUSQUEDA_FECHA_HORA_LIMITE = " 23:59:59";
    public final static String POST_FIJO_BUSQUEDA_FECHA_HORA_INICIO = " 00:00:00";
    
    @Override
    public int consultarCantidadFacturasLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cmf) "
                    + "FROM CmFacturas cmf ";
            strQuery += " WHERE cmf.id> 0 AND cmf.gnEmpresasId.id = :empresaid ";

            strQuery += " AND cmf.estadoFactura "
                    + " IN (" + ESTADOS_FACTURA_BUSQUEDA_STANDARD + ") ";

        
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "numeroRadicado":
                            strQuery += " AND cmf.numeroRadicado = " + e.getValue() + "  ";
                            break;
                        case "nit":
                            strQuery += " AND cmf.nit = '" + e.getValue() + "' ";
                            break;
                        case "ips":
                            strQuery += " AND cmf.ips LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "numeroFacturado":
                            strQuery += " AND cmf.numeroFacturado = '" + e.getValue() + "' ";
                            break;
                        case "fechaPrestacion":
                            strQuery += " AND cmf.fechaPrestacion = " + e.getValue() + " ";
                            break;
                        case "tipoContrato":
                            strQuery += " AND cmf.tipoContrato = '" + e.getValue() + "' ";
                            break;
                        case "regimen":
                            strQuery += " AND cmf.regimen = '" + e.getValue() + "' ";
                            break;
                        case "valorFactura":
                            strQuery += " AND cmf.valorFactura  = '" + e.getValue() + "' ";
                            break;
                        case "valorInicialGlosa":
                            strQuery += " AND cmf.valorInicialGlosa = '" + e.getValue() + "' ";
                            break;
                        case "valorPendienteActual":
                            strQuery += " AND cmf.valorPendienteActual = '" + e.getValue() + "' ";
                            break;
                        case "valorPendienteGlosa":
                            strQuery += " AND cmf.cmGlosasList.valorPendiente LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "tipoRespuesta":
                            if (e.getValue().equals("0")) {
                                strQuery += " AND cmf.cmGlosaRespuestasList  IS EMPTY ";
                            } else {
                                strQuery += " AND 1 <=  "
                                        + "( SELECT cmg.id  FROM CmGlosaRespuestas cmg WHERE cmg.id = (SELECT MAX( cmg2.id ) FROM CmGlosaRespuestas cmg2 WHERE cmg2.cmFacturasId.id = cmf.id) "
                                        + " AND cmg.tipoRespuesta = '" + e.getValue() + "'"
                                        + "  ) ";
                            }
                            break;
                        case "cmDetalleFactura.nombreCompletoAfiliado":
                            strQuery += "";
                            break;
                        case "cmDetalleFactura.documento":
                            strQuery += "";
                            break;
                        case "tipoAuditoria":
                            strQuery += " AND cmf.tipoAuditoria = '" + e.getValue() + "' ";
                            break;
                        case "estadoFactura":
                            strQuery += " AND cmf.estadoFactura = '" + e.getValue() + "' ";
                            break;
                        case "maeTipoContratoId":
                            strQuery += " AND cmf.maeTipoContratoId = '" + e.getValue() + "' ";
                            break;
                        case "usuarioLider":
                            strQuery += " AND cmf.gnUsuariosLiderId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "usuarioMedico":
                            strQuery += " AND cmf.gnUsuariosMedicoId.nombre LIKE '%" + e.getValue() + "%'";
                            break;
                        case "usuarioTecnico":
                            strQuery += " AND cmf.gnUsuariosTecnicoId.nombre LIKE '%" + e.getValue() + "%'";
                            break;
                        case "usuarioGestiona":
                            strQuery += " AND cmf.gnUsuariosGestionaId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "valorCopago":
                            strQuery += " AND cmf.valorCopago = '" + e.getValue() + "' ";
                            break;
                        case "cmRipCarga.id":
                            strQuery += " AND cmf.cmRipsCargasId.id =  '" + e.getValue() + "' ";
                            break;
                        case "maeTipoContratoCodigo":
                            strQuery += " AND cmf.maeTipoContratoCodigo = '" + e.getValue() + "' ";
                            break;
                        case "respuestaIps":
                            strQuery += " AND cmf.respuestaIps = " + e.getValue() + "";
                            break;
                        case "usuarioGestiona.id":
                            strQuery += " AND cmf.gnUsuariosGestionaId.id = " + e.getValue() + "";
                            break;
                        case "version":
                            strQuery += " AND cmf.version = " + e.getValue() + "";
                            break;  
                    }
                }
            }
  
            Query query = getEntityManager().createQuery(strQuery).setParameter("empresaid", paramConsulta.getEmpresaId());
                 
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
    public List<CmFactura> consultarFacturasLista(ParamConsulta paramConsulta) throws Exception {
        List<CmFactura> listResult = new ArrayList();
        try {
            String strQuery = "SELECT cmf  "
                    + "FROM CmFacturas cmf ";
            strQuery += " WHERE cmf.gnEmpresasId.id = :empresaid ";

            strQuery += " AND cmf.estadoFactura "
                    + " IN (" + ESTADOS_FACTURA_BUSQUEDA_STANDARD + ") ";


            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "numeroRadicado":
                            strQuery += " AND cmf.numeroRadicado= " + e.getValue() + " ";
                            break;
                        case "nit":
                            strQuery += " AND cmf.nit = '" + e.getValue() + "' ";
                            break;
                        case "ips":
                            strQuery += " AND cmf.ips LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "numeroFacturado":
                            strQuery += " AND cmf.numeroFacturado = '" + e.getValue() + "' ";
                            break;
                        case "fechaPrestacion":
                            strQuery += " AND cmf.fechaPrestacion = " + e.getValue() + " ";
                            break;
                        case "tipoContrato":
                            strQuery += " AND cmf.tipoContrato = '" + e.getValue() + "' ";
                            break;
                        case "regimen":
                            strQuery += " AND cmf.regimen = '" + e.getValue() + "' ";
                            break;
                        case "valorFactura":
                            strQuery += " AND cmf.valorFactura = '" + e.getValue() + "' ";
                            break;
                        case "valorInicialGlosa":
                            strQuery += " AND cmf.valorInicialGlosa = '" + e.getValue() + "' ";
                            break;
                        case "valorPendienteGlosa":
                            strQuery += " AND cmf.cmGlosasList.valorPendiente = '" + e.getValue() + "' ";
                            break;
                        case "valorPendienteActual":
                            strQuery += " AND cmf.valorPendienteActual = '" + e.getValue() + "' ";
                            break;
                        case "tipoRespuesta":
                            if (e.getValue().equals("0")) {
                                strQuery += " AND cmf.cmGlosaRespuestasList  IS EMPTY ";
                            } else {
                                strQuery += " AND 1 <=  "
                                        + "( SELECT cmg.id  FROM CmGlosaRespuestas cmg WHERE cmg.id = (SELECT MAX( cmg2.id ) FROM CmGlosaRespuestas cmg2 WHERE cmg2.cmFacturasId.id = cmf.id) "
                                        + " AND cmg.tipoRespuesta = '" + e.getValue() + "'"
                                        + "  ) ";
                            }
                            break;
                        case "cmDetalleFactura.nombreCompletoAfiliado":
                            strQuery += "";
                            break;
                        case "cmDetalleFactura.documento":
                            strQuery += "";
                            break;
                        case "tipoAuditoria":
                            strQuery += " AND cmf.tipoAuditoria = '" + e.getValue() + "' ";
                            break;
                        case "estadoFactura":
                            strQuery += " AND cmf.estadoFactura = '" + e.getValue() + "' ";
                            break;
                        case "maeTipoContratoId":
                            strQuery += " AND cmf.maeTipoContratoId = '" + e.getValue() + "' ";
                            break;
                        case "usuarioLider":
                            strQuery += " AND cmf.gnUsuariosLiderId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "usuarioMedico":
                            strQuery += " AND cmf.gnUsuariosMedicoId.nombre LIKE '%" + e.getValue() + "%'";
                            break;
                        case "usuarioTecnico":
                            strQuery += " AND cmf.gnUsuariosTecnicoId.nombre LIKE '%" + e.getValue() + "%'";
                            break;
                        case "usuarioGestiona":
                            strQuery += " AND cmf.gnUsuariosGestionaId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "valorCopago":
                            strQuery += " AND cmf.valorCopago = '" + e.getValue() + "' ";
                            break;
                        case "cmRipCarga.id":
                            strQuery += " AND cmf.cmRipsCargasId.id = '" + e.getValue() + "' ";
                            break;
                        case "maeTipoContratoCodigo":
                            strQuery += " AND cmf.maeTipoContratoCodigo = '" + e.getValue() + "' ";
                            break;
                        case "respuestaIps":
                            strQuery += " AND cmf.respuestaIps = " + e.getValue() + "";
                            break;
                        case "usuarioGestiona.id":
                            strQuery += " AND cmf.gnUsuariosGestionaId.id = " + e.getValue() + "";
                            break;
                        case "version":
                            strQuery += " AND cmf.version = " + e.getValue() + "";
                            break;  
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                String order = paramConsulta.getOrden().replace("gsAfiliado", "gsAfiliadosId").
                        replace("gsZona", "gsZonasId");
         
                order =  order. replace("usuarioLider", "gnUsuariosLiderId");
                order =  order. replace("usuarioMedico", "gnUsuariosMedicoId");
                order =  order. replace("usuarioTecnico", "gnUsuariosTecnicoId");
                order =  order. replace("usuarioGestiona", "gnUsuariosGestionaId");
                order =  order. replace("cmGrupo", "cmGruposId");
                order =  order. replace("cmRipCarga", "cmRipsCargasId");
                              
                strQuery += " cmf." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " cmf.id  DESC";
            }
            Query query = getEntityManager().createQuery(strQuery).setParameter("empresaid", paramConsulta.getEmpresaId());
            
            List<CmFacturas> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();         
            
            for (CmFacturas neg : list) {
                CmFactura ent = castEntidadNegocio(neg);
                listResult.add(ent);
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
    public int consultarCantidadRadicado(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT COUNT(cr) FROM CmRadicados cr WHERE cr.id > 0 AND cr.estadoRadicado = '0'");
            if (paramConsulta.getFiltros() != null && !paramConsulta.getFiltros().isEmpty()) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND cr.id = ");
                            strQuery.append(e.getValue());
                            strQuery.append(" ");
                            break;
                        case "radicado":
                            strQuery.append("AND cr.radicado = ");
                            strQuery.append(e.getValue());
                            strQuery.append(" ");
                            break;
                        case "usuario":
                            strQuery.append("AND cr.usuarioCrea LIKE '%");
                            strQuery.append(e.getValue());
                            strQuery.append("%' ");
                            break;
                        case "idConciliacionMasiva":
                            strQuery.append("AND cr.cmConciliacionesId.id  = ");
                            strQuery.append(e.getValue());
                            strQuery.append(" ");
                            break;
                        case "idGlosaRespuesta":
                            strQuery.append("AND cr.cmGlosaRespuestasId.id = ");
                            strQuery.append(e.getValue());
                            strQuery.append(" ");
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery.toString()).getSingleResult();
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
    public int consultarCantidadListaNovedades(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AsegRegistroNovedades p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND p.radicadoNovedadesId.asegAfiliadosId.id =  " + paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "asegMaNovedad.descripcionNovedad":
                            strQuery += "AND p.asegMaNovedadesId.descripcionNovedad LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "asegMaNovedad.codigoNovedad":
                            strQuery += "AND p.asegMaNovedadesId.codigoNovedad LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicadoNovedad.id":
                            strQuery += "AND p.radicadoNovedadesId.id = " + e.getValue() + " ";
                            break;
                        case "fechaNovedad":
                            strQuery += "AND p.fechaNovedad = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += "AND p.fechaHoraCrea = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaMarcacion":
                            strQuery += "AND p.fechaMarcacion = '" + (String) e.getValue() + "' ";
                            break;
                        case "descripcionValorAnterior":
                            strQuery += "AND p.descripcionValorAnterior LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "descripcionValorNuevo":
                            strQuery += "AND p.descripcionValorNuevo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
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
    public List<AsegRegistroNovedad> consultarListaNovedades(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<AsegRegistroNovedad> listResult = new ArrayList();
        SimpleDateFormat std = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            String strQuery = "FROM AsegRegistroNovedades p "
                    + "WHERE p.id > 0 ";
            
            if (paramConsulta.getParametroConsulta2() != null) {
              String subQuery = " SELECT COUNT(ap.id) FROM AsegRegistroNovedades ap WHERE " +
                                  " (ap.fechaNovedad >= '" + std.format( (Date) paramConsulta.getParametroConsulta2()) + POST_FIJO_BUSQUEDA_FECHA_HORA_INICIO + "') " +
                                  " AND ( ap.fechaNovedad < '" + std.format( (Date) paramConsulta.getParametroConsulta2()) + POST_FIJO_BUSQUEDA_FECHA_HORA_LIMITE + "') AND" +
                                  " ap.id = p.id ";
                strQuery = " SELECT p , (" + subQuery + ") AS hayFechaSeleccionada FROM AsegRegistroNovedades p "
                         + " WHERE p.id > 0 ";
            }
            
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND p.radicadoNovedadesId.asegAfiliadosId.id = " + paramConsulta.getParametroConsulta1() + " ";
            }
            
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "asegMaNovedad.descripcionNovedad":
                            strQuery += "AND p.asegMaNovedadesId.descripcionNovedad LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "asegMaNovedad.codigoNovedad":
                            strQuery += "AND p.asegMaNovedadesId.codigoNovedad LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicadoNovedad.id":
                            strQuery += "AND p.radicadoNovedadesId.id = " + e.getValue() + " ";
                            break;
                        case "fechaNovedad":
                            strQuery += "AND p.fechaNovedad = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += "AND p.fechaHoraCrea = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaMarcacion":
                            strQuery += "AND p.fechaMarcacion = '" + (String) e.getValue() + "' ";
                            break;
                        case "descripcionValorAnterior":
                            strQuery += "AND p.descripcionValorAnterior LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "descripcionValorNuevo":
                            strQuery += "AND p.descripcionValorNuevo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    case "asegMaNovedad.descripcionNovedad":
                            strQuery += "p.asegMaNovedadesId.descripcionNovedad "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                            break;
                    case "asegMaNovedad.codigoNovedad":
                        strQuery += "p.asegMaNovedadesId.codigoNovedad "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "radicadoNovedad.id":
                        strQuery += "p.radicadoNovedadesId.id "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    default:
                        strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                }
            } else {
                if(paramConsulta.getParametroConsulta2() != null){
                      strQuery += " hayFechaSeleccionada DESC, p.id DESC";
                }else{
                      strQuery += "p.fechaHoraCrea DESC";
                }
            }
            
            Query query = getEntityManager().createQuery(strQuery);
      
            if (paramConsulta.getParametroConsulta2() != null) {
                List<Object[]> list = query
                        .setFirstResult(paramConsulta.getPrimerRegistro())
                        .setMaxResults(paramConsulta.getRegistrosPagina())
                        .getResultList();
                for (Object[] perM : list) {
                    AsegRegistroNovedades per;
                    per = (AsegRegistroNovedades) perM[0];
                    listResult.add(NovedadAfiliadoServicio.castEntidadNegocio(per));
                }
            } else {
                List<AsegRegistroNovedades> list = query
                        .setFirstResult(paramConsulta.getPrimerRegistro())
                        .setMaxResults(paramConsulta.getRegistrosPagina())
                        .getResultList();
                for (AsegRegistroNovedades per : list) {
                    listResult.add(NovedadAfiliadoServicio.castEntidadNegocio(per));
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
    public List<Maestro> consultarMaestroPorParametro(ParamConsulta paramConsulta) throws Exception {
        List<Maestro> listResult = new ArrayList();
        String strQuery = "FROM GnMaestros e "
                + "WHERE e.activo = 1 ";
                
                if(paramConsulta.getParametroConsulta1() != null){
                   strQuery += " AND  e.tipo = '" + paramConsulta.getParametroConsulta1() + "' ";
                }
                
                if(paramConsulta.getParametroConsulta2() != null){
                   strQuery += " AND  e.gnMaestrosId.id = " + paramConsulta.getParametroConsulta2() + " ";
                }
                
                strQuery +=  "ORDER BY e.nombre ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnMaestros> list = query.getResultList();
            for (GnMaestros obj : list) {
                listResult.add(MaestroServicio.castEntidadNegocio(obj));
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
    public List<CmFacturasSinAutorizaciones> consultarFacturasSinAutorizacion(ParamConsulta paramConsulta) throws Exception{
        List<CmFacturasSinAutorizaciones> listResult = new ArrayList();
        try {
            String strQuery = " SELECT DISTINCT cmf.numero_facturado, cmf.numero_radicado,"
                    +" GROUP_CONCAT(distinct concat(cmd.documento ,' - ',cmd.nombre_completo_afiliado, '') separator ' | ') as usuario FROM "
                    +" cm_facturas cmf " 
                    +" INNER JOIN cm_detalles cmd On cmd.cm_facturas_id = cmf.id " 
                    +" LEFT JOIN cm_auditoria_autorizaciones cma ON cma.cm_detalles_id = cmd.id "
                    +" WHERE cmf.id IN ("+(String) paramConsulta.getParametroConsulta1()+") and  cma.cm_detalles_id is null GROUP BY cmf.numero_facturado, cmf.numero_radicado";
            Query query = getEntityManager().createNativeQuery(strQuery);
            List<Object[]> lstObj = query.getResultList();
                       
            for (Object[] object : lstObj) {
                if (object[0] != null) {
                    CmFacturasSinAutorizaciones facturaAuditoria = new CmFacturasSinAutorizaciones();
                    facturaAuditoria.setNumeroFactuado((String) object[0]);
                    facturaAuditoria.setNumeroRadicado((int) object[1]);
                    facturaAuditoria.setDocumentoNombreCliente((String) object[2]);
                    listResult.add(facturaAuditoria);
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
    public void actualizarEstadoAuditoriaMasivo(ParamConsulta paramConsulta) throws java.lang.Exception {
        try {
            if (paramConsulta.getParametroConsulta1() != null) {
                String hql = "UPDATE CmFacturas f SET";
                if (paramConsulta.getParametroConsulta2() != null) {
                    hql += " f.tipoAuditoria = :tipoAuditoria ";
                }

                if (paramConsulta.getParametroConsulta2() != null
                        && paramConsulta.getParametroConsulta3() != null) {
                    hql += ",";
                }

                if (paramConsulta.getParametroConsulta3() != null) {
                    hql += " f.estadoFactura = :estadoFactura ";
                }
                
                if(paramConsulta.getParametroConsulta1() != null){
                 hql += " WHERE f.id IN ( "+(String)paramConsulta.getParametroConsulta1()+" ) ";
                }
               

                Query query = getEntityManager().createQuery(hql);
                if (paramConsulta.getParametroConsulta2() != null) {
                    query.setParameter("tipoAuditoria", paramConsulta.getParametroConsulta2());
                }
                if (paramConsulta.getParametroConsulta3() != null) {
                    query.setParameter("estadoFactura", paramConsulta.getParametroConsulta3());
                }
                query.executeUpdate();
            }
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }


    public static CmFactura castEntidadNegocio(CmFacturas neg) {
        CmFactura ent = new CmFactura();
        ent.setId(neg.getId());
        CmRipsCarga ripCarga = new CmRipsCarga();
        if(neg.getCmRipsCargasId() != null){
            ripCarga = castEntidadNegocio(neg.getCmRipsCargasId());
        }
        ent.setCmRipCarga(ripCarga);
        if (neg.getGnEmpresasId() != null) {
            ent.setEmpresa(new Empresa(neg.getGnEmpresasId().getId()));
        }
        ent.setCmFeRipsCarga(new CmFeRipsCarga(0));
        if(neg.getCmFeRipsCargasId() != null){
            ent.setCmFeRipsCarga(new CmFeRipsCarga(neg.getCmFeRipsCargasId().getId()));
            ent.getCmFeRipsCarga().setSoportesNumero(neg.getCmFeRipsCargasId().getSoportesNumero());
        }
        if (neg.getCntPrestadoresId() != null) {
            ent.setCntPrestador(new CntPrestador(neg.getCntPrestadoresId().getId(),
                    neg.getCntPrestadoresId().getRazonSocial(),
                    neg.getCntPrestadoresId().getNombreRepresentanteLegal()
            ));
        } 
        if(neg.getCmGruposId() != null){
           ent.setCmGrupo(new CmGrupo(neg.getCmGruposId().getId()));
        }
        ent.setNumeroRadicado(neg.getNumeroRadicado());
        ent.setNit(neg.getNit());
        ent.setIps(neg.getIps());
        ent.setNumeroFacturado(neg.getNumeroFacturado());
        ent.setFechaRadicacion(neg.getFechaRadicacion());
        ent.setFechaPrestacion(neg.getFechaPrestacion());
        ent.setMaeTipoContratoId(neg.getMaeTipoContratoId());
        ent.setMaeTipoContratoCodigo(neg.getMaeTipoContratoCodigo());
        ent.setMaeTipoContratoValor(neg.getMaeTipoContratoValor());
        ent.setMaeRegimenId(neg.getMaeRegimenId());
        ent.setMaeRegimenCodigo(neg.getMaeRegimenCodigo());
        ent.setMaeRegimenValor(neg.getMaeRegimenValor());
        ent.setValorFactura(neg.getValorFactura());
        ent.setEstadoFactura(neg.getEstadoFactura());
        ent.setMultiUsuario(neg.getMultiusuario());
        ent.setValorInicialGlosa(neg.getValorInicialGlosa());
        ent.setValorPendienteActual(neg.getValorPendienteActual());
        Usuario usuarioLider = new Usuario();
        if(neg.getGnUsuariosLiderId() != null){
           usuarioLider = new Usuario(neg.getGnUsuariosLiderId().getId(), 
                                      neg.getGnUsuariosLiderId().getUsuario(),
                                      neg.getGnUsuariosLiderId().getNombre());
           usuarioLider.setCorreoElectronico(neg.getGnUsuariosLiderId().getCorreoElectronico());
        }
        ent.setUsuarioLider(usuarioLider);
        
        Usuario usuarioMedico = new Usuario();
        if(neg.getGnUsuariosMedicoId()!= null){
           usuarioMedico = new Usuario(neg.getGnUsuariosMedicoId().getId(), 
                                       neg.getGnUsuariosMedicoId().getUsuario(),
                                       neg.getGnUsuariosMedicoId().getNombre());
           usuarioMedico.setCorreoElectronico(neg.getGnUsuariosMedicoId().getCorreoElectronico());
        }
        ent.setUsuarioMedico(usuarioMedico);
        
        Usuario usuarioTecnico = new Usuario();
        if(neg.getGnUsuariosTecnicoId()!= null){
            usuarioTecnico = new Usuario(neg.getGnUsuariosTecnicoId().getId(), 
                                         neg.getGnUsuariosTecnicoId().getUsuario(),
                                         neg.getGnUsuariosTecnicoId().getNombre());
           usuarioTecnico.setCorreoElectronico(neg.getGnUsuariosTecnicoId().getCorreoElectronico());
        }
        ent.setUsuarioTecnico(usuarioTecnico);
         
        Usuario usuarioGestiona= new Usuario();
        if(neg.getGnUsuariosGestionaId() != null){
            usuarioGestiona = new Usuario(neg.getGnUsuariosGestionaId().getId(), 
                                       neg.getGnUsuariosGestionaId().getUsuario(),
                                       neg.getGnUsuariosGestionaId().getNombre());
            usuarioGestiona.setCorreoElectronico(neg.getGnUsuariosGestionaId().getCorreoElectronico());
        }
        
        CmAuditoriaCierre cmAuditoriaCierre = new CmAuditoriaCierre();
        if( ! neg.getCmAuditoriaCierresList().isEmpty() ){
            cmAuditoriaCierre  = new CmAuditoriaCierre(neg.getCmAuditoriaCierresList().get(0).getId());
            List<CmRadicados> cmRadicados = neg.getCmAuditoriaCierresList().get(0).getCmRadicadosList();
            int idRadicado = cmRadicados.isEmpty() ? 0 : cmRadicados.get(0).getId() ;
            CmRadicado cmRadicado = new CmRadicado(idRadicado);
            List<CmSincronizacionEncabezados> cmEncabezados = idRadicado > 0 ? cmRadicados.get(0).getCmSincronizacionEncabezadosList():
                                                             new ArrayList<>() ;
            int idEncabezado = cmEncabezados.isEmpty() ? 0 : cmEncabezados.get(0).getId(); 
            CmSincronizacionEncabezado cmSincronizacionEncabezado = new CmSincronizacionEncabezado(idEncabezado);
            cmRadicado.setCmSincronizacionEncabezado(cmSincronizacionEncabezado);
            cmAuditoriaCierre.setCmRadicado(cmRadicado);
        }
        ent.setCmAuditoriaCierre(cmAuditoriaCierre);
        ent.setCantidadDetalles(neg.getCmDetallesList() != null  ? neg.getCmDetallesList().size() : 0);
        ent.setPbs(Optional.ofNullable(neg.getPbs()).orElse(false));
        ent.setVersion(Optional.ofNullable(neg.getVersion()).orElse(false));
        ent.setUsuarioGestiona(usuarioGestiona);
        ent.setMarcacion(neg.getMarcacion());
        ent.setFechaMarcacion(neg.getFechaMarcacion());
        ent.setFechaMarcacionRespuestaIps(neg.getFechaMarcacionRespuestaIps());
        ent.setRespuestaIps(neg.getRespuestaIps());
        ent.setFechaVencimiento(neg.getFechaVencimiento());
        ent.setTipoAuditoria(neg.getTipoAuditoria());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setValorCopago(neg.getValorCopago());
        ent.setValorBruto(neg.getValorBruto());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraAudita(neg.getFechaHoraAudita());
        ent.setFechaAsignacionMedico(neg.getFechaAsignacionMedico());
        ent.setFechaAsignacionTecnico(neg.getFechaAsignacionTecnico());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setUsuarioModifica(neg.getUsuarioModifica());
        ent.setTerminalModifica(neg.getTerminalModifica());
        ent.setFechaHoraModifica(neg.getFechaHoraModifica());
        return ent;
    }

    public static CmFacturas castNegocioEntidad(CmFactura ent) {
        CmFacturas neg = new CmFacturas();
        neg.setId(ent.getId());
        
        CmRipsCargas ripCarga = new CmRipsCargas();
        if(ent.getCmRipCarga() != null && ent.getCmRipCarga().getId() != null){
           ripCarga = new CmRipsCargas(ent.getCmRipCarga().getId());
        }
        neg.setCmRipsCargasId(ripCarga);
               
        if (ent.getEmpresa() != null) {
            neg.setGnEmpresasId(new GnEmpresas(ent.getEmpresa().getId()));
        }
        if (ent.getCntPrestador() != null) {
            neg.setCntPrestadoresId(new CntPrestadores(ent.getCntPrestador().getId()));
        }
        if(neg.getCmGruposId() != null){
          ent.setCmGrupo(new CmGrupo(neg.getCmGruposId().getId(),
                           neg.getCmGruposId().getNombre(),
                           neg.getCmGruposId().getDescripcion()
          ));
        }
        neg.setNumeroRadicado(ent.getNumeroRadicado());
        neg.setNit(ent.getNit());
        neg.setIps(ent.getIps());
        neg.setNumeroFacturado(ent.getNumeroFacturado());
        neg.setFechaRadicacion(ent.getFechaRadicacion());
        neg.setFechaPrestacion(ent.getFechaPrestacion());
        neg.setMaeTipoContratoId(ent.getMaeTipoContratoId());
        neg.setMaeTipoContratoCodigo(ent.getMaeTipoContratoCodigo());
        neg.setMaeTipoContratoValor(ent.getMaeTipoContratoValor());
        neg.setMaeRegimenId(ent.getMaeRegimenId());
        neg.setMaeRegimenCodigo(ent.getMaeRegimenCodigo());
        neg.setMaeRegimenValor(ent.getMaeRegimenValor());
        neg.setValorFactura(ent.getValorFactura());
        neg.setEstadoFactura(ent.getEstadoFactura());
        neg.setMultiusuario(ent.isMultiUsuario());
        neg.setValorInicialGlosa(ent.getValorInicialGlosa());
        neg.setValorPendienteActual(ent.getValorPendienteActual());
        if(ent.getUsuarioLider() != null && ent.getUsuarioLider().getId() != null){
           neg.setGnUsuariosLiderId(new GnUsuarios(ent.getUsuarioLider().getId()));
        }
        
        if(ent.getUsuarioTecnico()!= null && ent.getUsuarioTecnico().getId() != null){
           neg.setGnUsuariosTecnicoId(new GnUsuarios(ent.getUsuarioTecnico().getId()));
        }
        
        if(ent.getUsuarioMedico() != null && ent.getUsuarioMedico().getId() != null){
           neg.setGnUsuariosMedicoId(new GnUsuarios(ent.getUsuarioMedico().getId()));
        }
        
        if(ent.getUsuarioGestiona() != null && ent.getUsuarioGestiona().getId() != null){
           neg.setGnUsuariosGestionaId(new GnUsuarios(ent.getUsuarioGestiona().getId()));
        }
        neg.setMarcacion(ent.getMarcacion());
        neg.setFechaMarcacion(ent.getFechaMarcacion());
        neg.setFechaVencimiento(ent.getFechaVencimiento());
        neg.setTipoAuditoria(ent.getTipoAuditoria());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        neg.setUsuarioModifica(ent.getUsuarioModifica());
        neg.setTerminalModifica(ent.getTerminalModifica());
        neg.setFechaHoraModifica(ent.getFechaHoraModifica());
        return neg;
    } 
    
    public static CmRipsCarga castEntidadNegocio(CmRipsCargas neg) {
        CmRipsCarga ent = new CmRipsCarga();
        ent.setId(neg.getId());
        ent.setCntTipoContratoId(neg.getCntTipoContratoId());
        Integer idCntContrato = neg.getCntContratosId() != null ? 
                                neg.getCntContratosId().getId() : null;
        ent.setCntContrato(new CntContrato(idCntContrato));
        ent.setMaeContratoModalidadCodigo(neg.getMaeContratoModalidadCodigo());
        ent.setMaeContratoModalidadId(neg.getMaeContratoModalidadId());
        ent.setMaeContratoModalidadValor(neg.getMaeContratoModalidadValor());
        return ent;
    }
     
  }
