/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCierre;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMasivaN;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaCierres;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaMasiva;
import com.saviasaludeps.savia.ejb.entidades.CmFacturas;
import com.saviasaludeps.savia.ejb.entidades.CmFeRipsCargas;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaCierreRemoto;
import java.util.ArrayList;
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
@Remote(CmAuditoriaCierreRemoto.class)
@Local(CmAuditoriaCierreLocal.class)
public class CmAuditoriaCierreServicio extends GenericoServicio implements CmAuditoriaCierreLocal,CmAuditoriaCierreRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
       int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cms) FROM CmAuditoriaCierres cms ";         
            strQuery += " WHERE cms.id > 0 ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoServicio":
                            strQuery += " AND cms.codigoServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreServicio":
                            strQuery += " AND cms.nombreServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "documento":
                            strQuery += " AND cms.documento LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicadoGlosa":
                            strQuery += " AND cms.radicadoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "motivoGlosa":
                            strQuery += " AND cma.motivoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "auAnexos4Id":
                            strQuery += " AND ccmsma.auAnexos4Id.id = " + e.getValue() + " ";
                            break;
                            
                    }
                }
            } 
            
            cant = (int) (long)  getEntityManager().createQuery(strQuery).getSingleResult();                

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
    public List<CmAuditoriaCierre> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmAuditoriaCierre> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmAuditoriaCierres cmc";
            strQuery += " WHERE cmc.id > 0 ";
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoServicio":
                            strQuery += " AND cmc.codigoServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreServicio":
                            strQuery += " AND cmc.nombreServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "documento":
                            strQuery += " AND cmc.documento LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicadoGlosa":
                            strQuery += " AND cmc.radicadoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "motivoGlosa":
                            strQuery += " AND cmc.motivoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "auAnexos4Id":
                            strQuery += " AND cmc.auAnexos4Id.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
              
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {     
                String order = paramConsulta.getOrden().replace("cmGlosas", "cmGlosasId").
                                                        replace("gsZona", "gsZonasId");
                strQuery += " cmc." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " cmc.fechaHoraCrea DESC , cmc.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("detalleId", paramConsulta.getParametroConsulta1());
       
            
            List<CmAuditoriaCierres> list;
            
            if (paramConsulta.getParametroConsulta2() != null) {
                list = query
                        .getResultList();
            } else {
                list = query
                        .setFirstResult(paramConsulta.getPrimerRegistro())
                        .setMaxResults(paramConsulta.getRegistrosPagina())
                        .getResultList();
            }

            for (CmAuditoriaCierres per : list) {
                CmAuditoriaCierre obj = castEntidadNegocio(per);
                listResult.add(obj);
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
    public CmAuditoriaCierre consultar(int id) throws Exception {
        CmAuditoriaCierre obj = null;
        try {
            CmAuditoriaCierres per = (CmAuditoriaCierres) getEntityManager().find(CmAuditoriaCierres.class, id);
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
    public CmAuditoriaCierre consultarPorIdCmFactura(int idCmFactura) throws Exception {
        CmAuditoriaCierre obj = new CmAuditoriaCierre();
        try {

            if (idCmFactura > 0) {
                String strQuery = "FROM CmAuditoriaCierres cmc";
                strQuery += " WHERE cmc.id > 0 and cmc.cmFacturasId.id  = :idFactura ";
                strQuery += " ORDER BY cmc.id DESC";

                Query query = getEntityManager().createQuery(strQuery);
                query.setParameter("idFactura", idCmFactura);

                CmAuditoriaCierres cierre = (CmAuditoriaCierres) query.setMaxResults(1).getSingleResult();
                if (cierre != null) {
                    obj = castEntidadNegocio(cierre);
                }
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
    public int insertar(CmAuditoriaCierre obj) throws Exception {
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
    public void actualizar(CmAuditoriaCierre obj) throws Exception {
        try {
            CmAuditoriaCierres  cierre =  castNegocioEntidad(obj);       
            String hql = "UPDATE CmAuditoriaCierres SET"
                    + " valorFacturado = :valorFacturado,"
                    + " valorPagado = :valorPagado,"
                    + " valorGlosado = :valorGlosado,"
                    + " cantidadDetalles = :cantidadDetalles,"
                    + " cantidadDetallesRegistradas  = :cantidadDetallesRegistradas,"
                    + " fechaHoraRegistroInicio = :fechaHoraRegistroInicio,"
                    + " fechaHoraRegistroFinalizacion = :fechaHoraRegistroFinalizacion"
                    + " WHERE id = :id";
            
            Query query = getEntityManager().createQuery(hql);
            
            query.setParameter("valorFacturado", cierre.getValorFacturado());
            query.setParameter("valorPagado", cierre.getValorPagado());
            query.setParameter("valorGlosado", cierre.getValorGlosado());
            query.setParameter("cantidadDetalles", cierre.getCantidadDetalles());
            query.setParameter("cantidadDetallesRegistradas", cierre.getCantidadDetallesRegistradas());
            query.setParameter("fechaHoraRegistroInicio", cierre.getFechaHoraRegistroInicio());
            query.setParameter("fechaHoraRegistroFinalizacion",cierre.getFechaHoraRegistroFinalizacion());
            query.setParameter("id", cierre.getId());
            query.executeUpdate();
            
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public List<CmAuditoriaCierre> consultarPorCmAuditoriaMasivaId(int cmAuditoriaMasivaId) throws Exception {
        List<CmAuditoriaCierre> listaResultado = new ArrayList<>();

        try {
            String strQuery = " FROM CmAuditoriaCierres cmac WHERE cmac.id > 0 AND  cmac.cmAuditoriaMasivaId.id = :id ";
            List<CmAuditoriaCierres> lista = getEntityManager().createQuery(strQuery).setParameter("id", cmAuditoriaMasivaId).getResultList();

            for (CmAuditoriaCierres cierre : lista) {
               listaResultado.add(castEntidadNegocio(cierre));
            }

        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    @Override
    public CmAuditoriaCierre eliminar(int id) throws Exception {
        CmAuditoriaCierre obj = null;
        try {
            CmAuditoriaCierres per = getEntityManager().find(CmAuditoriaCierres.class, id);
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
   
    
    public static CmAuditoriaCierre castEntidadNegocio(CmAuditoriaCierres neg) {
        CmAuditoriaCierre ent = new CmAuditoriaCierre();
        ent.setId(neg.getId());
        ent.setCmFactura(new CmFactura(neg.getCmFacturasId().getId()));
        CmRipsCargas cmRipsCarga = Optional.ofNullable(neg.getCmFacturasId().getCmRipsCargasId()).
                                    orElse(new CmRipsCargas());
        CmFeRipsCargas cmFeRipsCarga = Optional.ofNullable(neg.getCmFacturasId().getCmFeRipsCargasId()).
                                    orElse(new CmFeRipsCargas());
        CmFactura factura = new CmFactura(neg.getCmFacturasId().getId(),
                neg.getCmFacturasId().getMaeTipoContratoValor(),
                neg.getCmFacturasId().getNumeroRadicado(),
                neg.getCmFacturasId().getNumeroFacturado(),
                neg.getCmFacturasId().getNit(),
                neg.getCmFacturasId().getIps(),
                neg.getCmFacturasId().getFechaRadicacion(),
                neg.getCmFacturasId().getFechaPrestacion(),
                neg.getCmFacturasId().getMaeRegimenValor(),
                neg.getCmFacturasId().getValorFactura(),
                neg.getCmFacturasId().getEstadoFactura(),
                neg.getCmFacturasId().getValorCopago(),
                neg.getCmFacturasId().getValorPendienteActual(),
                cmRipsCarga.getId(), 
                cmFeRipsCarga.getId());
        factura.setNumeroContrato( neg.getCmFacturasId().getNumeroContrato());
        if(neg.getCmAuditoriaMasivaId() != null &&
                neg.getCmAuditoriaMasivaId().getId() != null){
         ent.setCmAuditoriaMasivaN(new CmAuditoriaMasivaN( neg.getCmAuditoriaMasivaId().getId()));
        }
       
        ent.setCmFactura(factura);
        ent.setValorPagado(neg.getValorPagado());
        ent.setValorGlosado(neg.getValorGlosado());
        ent.setValorFacturado(neg.getValorFacturado());
        ent.setCantidadDetalles(neg.getCantidadDetalles());
        ent.setCantidadDetallesRegistradas(neg.getCantidadDetallesRegistradas());
        ent.setFechaHoraRegistroInicio(neg.getFechaHoraRegistroInicio());
        ent.setFechaHoraRegistroFinalizacion(neg.getFechaHoraRegistroFinalizacion());
        ent.setUsuarioCrea(neg.getUsuariosCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        return ent;
    }

    public static CmAuditoriaCierres castNegocioEntidad(CmAuditoriaCierre ent) {
        CmAuditoriaCierres neg = new CmAuditoriaCierres();
        neg.setId(ent.getId());
        neg.setCmFacturasId(new CmFacturas(ent.getCmFactura().getId()));
        if( ent.getCmAuditoriaMasivaN()!= null && 
            ent.getCmAuditoriaMasivaN().getId() != null ){
          neg.setCmAuditoriaMasivaId(new CmAuditoriaMasiva(ent.getCmAuditoriaMasivaN().getId()));
        }
        neg.setValorPagado(ent.getValorPagado());
        neg.setValorFacturado(ent.getValorFacturado());
        neg.setValorGlosado(ent.getValorGlosado());
        neg.setCantidadDetalles(ent.getCantidadDetalles());
        neg.setCantidadDetallesRegistradas(ent.getCantidadDetallesRegistradas());
        neg.setFechaHoraRegistroInicio(ent.getFechaHoraRegistroInicio());
        neg.setFechaHoraRegistroFinalizacion(ent.getFechaHoraRegistroFinalizacion());
        neg.setUsuariosCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        return neg;
    }
    
 }
