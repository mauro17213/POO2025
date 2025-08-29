/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMasivaN;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionEncabezado;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmFactura;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaMasiva;
import com.saviasaludeps.savia.ejb.entidades.CmRadicados;
import com.saviasaludeps.savia.ejb.entidades.CmSincronizacionEncabezados;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.WsCmFacturas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaMasivaRemoto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jperezn
 */
@Stateless
@Remote(CmAuditoriaMasivaRemoto.class)
@Local(CmAuditoriaMasivaLocal.class)
public class CmAuditoriaMasivaServicio extends GenericoServicio implements CmAuditoriaMasivaLocal, CmAuditoriaMasivaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cmam) "
                    + "FROM CmAuditoriaMasiva cmam ";
            strQuery += " WHERE cmam.id > 0 ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND cmam.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estadoProceso":
                            strQuery += " AND cmam.estadoProceso LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nit":
                            strQuery += " AND cmam.nit = '" + e.getValue() + "' ";
                            break;
                        case "ips":
                            strQuery += " AND cmam.ips LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cmRadicado":
                            strQuery += " AND cmam.cmRadicado LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }

            Query query = getEntityManager().createQuery(strQuery);
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
    public List<CmAuditoriaMasivaN> consultarLista(ParamConsulta paramConsulta) throws Exception {
        int CANTIDAD_SIN_ITEMS = 2;
        List<CmAuditoriaMasivaN> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmAuditoriaMasiva cmam ";
            strQuery += " WHERE cmam > 0  ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND cmam.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estadoProceso":
                            strQuery += " AND cmam.estadoProceso LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nit":
                            strQuery += " AND cmam.nit = '" + e.getValue() + "' ";
                            break;
                        case "ips":
                            strQuery += " AND cmam.ips LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cmRadicado":
                            strQuery += " AND cmam.cmRadicado LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }

            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {

                String order = paramConsulta.getOrden().replace("gsAfiliado", "gsAfiliadosId").
                        replace("gsZona", "gsZonasId");

                strQuery += " cmam." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " cmam.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);

            List<CmAuditoriaMasiva> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CmAuditoriaMasiva neg : list) {
                CmAuditoriaMasivaN auditoriaMasiva = castEntidadNegocio(neg);

                if (paramConsulta.getParametroConsulta1() != null) {
                    boolean pendiente = neg.getCmRadicadosList().isEmpty() ? true
                            : !neg.getCmRadicadosList().get(0).getEstadoRadicado();
                    auditoriaMasiva.setTieneFactuasPendientesProcesarEnSap(pendiente);
                    CmRadicados radicado = neg.getCmRadicadosList().isEmpty() ? 
                                           new CmRadicados() : neg.getCmRadicadosList().get(0);
                    String descripcion = getDescripcioSincronizacionWsCmFacturas(radicado);
                    if (descripcion.length() <= CANTIDAD_SIN_ITEMS) {
                        descripcion = getDescripcioSincronizacionFacturas(radicado);
                    }
                    if(radicado.getId() != null){
                        auditoriaMasiva.setCmRadicado(String.valueOf(radicado.getId()));
                    }
                    auditoriaMasiva.setDescripcionFacturasSincronizadas(descripcion);
                }
                listResult.add(auditoriaMasiva);
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
    public int insertar(CmAuditoriaMasivaN obj) throws java.lang.Exception {
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
    public CmAuditoriaMasivaN consultar(int id) throws Exception {
        CmAuditoriaMasivaN obj = null;
        try {
            CmAuditoriaMasiva per = (CmAuditoriaMasiva) getEntityManager().find(CmAuditoriaMasiva.class, id);
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
    public void actualizar(CmAuditoriaMasivaN obj) throws Exception {
        try {

            CmAuditoriaMasiva auditoriaMasiva = castNegocioEntidad(obj);
            String hql = "UPDATE CmAuditoriaMasiva SET"
                    + " nit = :nit, "
                    + " ips = :ips, "
                    + " sumaValorFacturado = :sumaValorFacturado, "
                    + " sumaValorCopago = :sumaValorCopago, "
                    + " sumaValorBruto = :sumaValorBruto, "
                    + " valorAuditoriaExitosa = :valorAuditoriaExitosa, "
                    + " valorGlosado = :valorGlosado, "
                    + " cmRadicado = :cmRadicado, "
                    + " estadoProceso = :estadoProceso, "
                    + " cantidadFacturas = :cantidadFacturas, "
                    + " cantidadFacturasRegistradas = :cantidadFacturasRegistradas, "
                    + " horaFinalizacionRegistro = :horaFinalizacionRegistro, "
                    + " observacion = :observacion "
                    + " WHERE id = :id";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("nit", auditoriaMasiva.getNit());
            query.setParameter("ips", auditoriaMasiva.getIps());
            query.setParameter("sumaValorFacturado", auditoriaMasiva.getSumaValorFacturado());
            query.setParameter("sumaValorCopago", auditoriaMasiva.getSumaValorCopago());
            query.setParameter("sumaValorBruto", auditoriaMasiva.getSumaValorBruto());
            query.setParameter("valorAuditoriaExitosa", auditoriaMasiva.getValorAuditoriaExitosa());
            query.setParameter("valorGlosado", auditoriaMasiva.getValorGlosado());
            query.setParameter("cmRadicado", auditoriaMasiva.getCmRadicado());
            query.setParameter("estadoProceso", auditoriaMasiva.getEstadoProceso());
            query.setParameter("cantidadFacturas", auditoriaMasiva.getCantidadFacturas());
            query.setParameter("cantidadFacturasRegistradas", auditoriaMasiva.getCantidadFacturasRegistradas());
            query.setParameter("horaFinalizacionRegistro", auditoriaMasiva.getHoraFinalizacionRegistro());
            query.setParameter("observacion", auditoriaMasiva.getObservacion());
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
    public CmAuditoriaMasivaN eliminar(int id) throws Exception {
        CmAuditoriaMasivaN obj = null;
        try {
            CmAuditoriaMasiva per = getEntityManager().find(CmAuditoriaMasiva.class, id);
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

    public static CmAuditoriaMasivaN castEntidadNegocio(CmAuditoriaMasiva neg) {
        CmAuditoriaMasivaN ent = new CmAuditoriaMasivaN();
        ent.setId(neg.getId());
        ent.setCntPrestadores(new CntPrestador(neg.getCntPrestadoresId().getId()));
        ent.setNit(neg.getNit());
        ent.setIps(neg.getIps());
        ent.setSumaValorFacturado(neg.getSumaValorFacturado());
        ent.setSumaValorCopago(neg.getSumaValorCopago());
        ent.setSumaValorBruto(neg.getSumaValorBruto());
        ent.setValorAuditoriaExitosa(neg.getValorAuditoriaExitosa());
        ent.setValorGlosado(neg.getValorGlosado());
        ent.setCmRadicado(neg.getCmRadicado());
        ent.setEstadoProceso(neg.getEstadoProceso());
        ent.setCantidadFacturas(neg.getCantidadFacturas());
        ent.setCantidadFacturasRegistradas(neg.getCantidadFacturasRegistradas());
        ent.setHoraFinalizacionRegistro(neg.getHoraFinalizacionRegistro());
        ent.setObservacion(neg.getObservacion());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        return ent;
    }

    public static CmAuditoriaMasiva castNegocioEntidad(CmAuditoriaMasivaN ent) {
        CmAuditoriaMasiva neg = new CmAuditoriaMasiva();
        neg.setId(ent.getId());
        neg.setCntPrestadoresId(new CntPrestadores(ent.getCntPrestadores().getId()));
        neg.setNit(ent.getNit());
        neg.setIps(ent.getIps());
        neg.setSumaValorFacturado(ent.getSumaValorFacturado());
        neg.setSumaValorCopago(ent.getSumaValorCopago());
        neg.setSumaValorBruto(ent.getSumaValorBruto());
        neg.setValorAuditoriaExitosa(ent.getValorAuditoriaExitosa());
        neg.setValorGlosado(ent.getValorGlosado());
        neg.setCmRadicado(ent.getCmRadicado());
        neg.setEstadoProceso(ent.getEstadoProceso());
        neg.setCantidadFacturas(ent.getCantidadFacturas());
        neg.setCantidadFacturasRegistradas(ent.getCantidadFacturasRegistradas());
        neg.setHoraFinalizacionRegistro(ent.getHoraFinalizacionRegistro());
        neg.setObservacion(ent.getObservacion());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        return neg;
    }

    private String getDescripcioSincronizacionFacturas(CmRadicados radicado) {
        Map<Integer, Integer> tipoEstados = new HashMap<>();
        Map<String, Integer> estadosProcesados = new HashMap<>();
        try {

            List<CmSincronizacionEncabezados> encabezados = radicado.getCmSincronizacionEncabezadosList() != null
                    ? radicado.getCmSincronizacionEncabezadosList() : new ArrayList<>();

            for (CmSincronizacionEncabezados encabezado : encabezados) {
                tipoEstados.compute(encabezado.getEstado(), (key, val) -> (val == null) ? 1 : val + 1);
            }

            for (Map.Entry<Integer, Integer> entry : tipoEstados.entrySet()) {
                String llave = CmSincronizacionEncabezado.getDescripcionEstadoControlStr(entry.getKey());
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

}
