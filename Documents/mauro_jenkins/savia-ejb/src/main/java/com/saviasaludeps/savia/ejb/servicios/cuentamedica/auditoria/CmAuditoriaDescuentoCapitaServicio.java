/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCapitaDescuento;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaCapitaDescuentos;
import com.saviasaludeps.savia.ejb.entidades.CmDetalles;
import com.saviasaludeps.savia.ejb.entidades.CntContratos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaDescuentoCapitaRemoto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
@Remote(CmAuditoriaDescuentoCapitaRemoto.class)
@Local(CmAuditoriaDescuentoCapitaLocal.class)
public class CmAuditoriaDescuentoCapitaServicio extends GenericoServicio implements CmAuditoriaDescuentoCapitaLocal, CmAuditoriaDescuentoCapitaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        return cant;
    }
    
    @Override
    public List<CmAuditoriaCapitaDescuento> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmAuditoriaCapitaDescuento> listResult = new ArrayList();
        return listResult;
    }
    
    @Override
    public CmAuditoriaCapitaDescuento consultar(int id) throws Exception {
        CmAuditoriaCapitaDescuento obj = null;
        try {
            CmAuditoriaCapitaDescuentos per = (CmAuditoriaCapitaDescuentos) getEntityManager().find(CmAuditoriaCapitaDescuentos.class, id);
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
    public int insertar(CmAuditoriaCapitaDescuento obj) throws Exception {
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
    public void actualizar(CmAuditoriaCapitaDescuento obj) throws Exception {
        try {
            CmAuditoriaCapitaDescuentos  descuento =  castNegocioEntidad(obj);       
            String hql = "UPDATE CmAuditoriaCapitaDescuentos SET"
                    + " marcacion = :marcacion,"
                    + " contrato = :contrato,"
                    + " observacion = :observacion,"
                    + " usuarioCrea = :usuarioCrea,"
                    + " terminalCrea  = :terminalCrea,"
                    + " fechaHoraCrea = :fechaHoraCrea,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica,"
                    + " cmDetallesId.id = :cmDetallesId,"
                    + " cntContratosId.id = :cntContratosId"
                    + " WHERE id = :id";
            
            Query query = getEntityManager().createQuery(hql); 
            query.setParameter("marcacion", descuento.getMarcacion());
            query.setParameter("contrato", descuento.getContrato());
            query.setParameter("observacion", descuento.getObservacion());    
            query.setParameter("usuarioCrea", descuento.getUsuarioCrea());
            query.setParameter("terminalCrea", descuento.getTerminalCrea());
            query.setParameter("fechaHoraCrea", descuento.getFechaHoraCrea());
            query.setParameter("usuarioModifica", descuento.getUsuarioModifica());
            query.setParameter("terminalModifica", descuento.getTerminalModifica());
            query.setParameter("fechaHoraModifica", descuento.getFechaHoraModifica());
            query.setParameter("cmDetallesId", descuento.getCmDetallesId().getId());
            query.setParameter("cntContratosId", descuento.getCntContratosId().getId());
            query.setParameter("id", descuento.getId());
            query.executeUpdate();
            
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CmAuditoriaCapitaDescuento eliminar(int id) throws Exception {
        CmAuditoriaCapitaDescuento obj = null;
        try {     
            CmAuditoriaCapitaDescuentos per = getEntityManager().find(CmAuditoriaCapitaDescuentos.class, id);
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
    public int consultarCantidadPorDetalle(ParamConsulta paramConsulta) throws java.lang.Exception {
      int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cmdc) FROM CmAuditoriaCapitaDescuentos cmdc ";         
            strQuery += " WHERE cmdc.cmDetallesId.id = :detalleId ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "marcacion":
                            strQuery += " AND cmdc.marcacion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "contrato":
                            strQuery += " AND cmdc.contrato LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "observacion":
                            strQuery += " AND cmdc.observacion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntContratosId.id":
                            strQuery += " AND cmdc.cntContratosId.id LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            } 
            
            cant = (int) (long)  getEntityManager().createQuery(strQuery).
                          setParameter("detalleId", paramConsulta.getParametroConsulta1()).getSingleResult();                

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
    public List<CmAuditoriaCapitaDescuento> consultarListaPorDetalle(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CmAuditoriaCapitaDescuento> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmAuditoriaCapitaDescuentos cmdc";
            strQuery += " WHERE cmdc.cmDetallesId.id = :detalleId";
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "marcacion":
                            strQuery += " AND cmdc.marcacion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "contrato":
                            strQuery += " AND cmdc.contrato LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "observacion":
                            strQuery += " AND cmdc.observacion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntContratosId.id":
                            strQuery += " AND cmdc.cntContratosId.id LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
              
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {     
                String order = paramConsulta.getOrden().replace("cmGlosas", "cmGlosasId").
                                                        replace("gsZona", "gsZonasId");
                strQuery += " cmdc." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " cmdc.fechaHoraCrea DESC , cmdc.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("detalleId", paramConsulta.getParametroConsulta1());
               
            List<CmAuditoriaCapitaDescuentos> list;
            
            if (paramConsulta.getParametroConsulta2() != null) {
                list = query
                        .getResultList();
            } else {
                list = query
                        .setFirstResult(paramConsulta.getPrimerRegistro())
                        .setMaxResults(paramConsulta.getRegistrosPagina())
                        .getResultList();
            }
            int postInsertar = 1;
            for (CmAuditoriaCapitaDescuentos per : list) {
                CmAuditoriaCapitaDescuento obj = castEntidadNegocio(per);
                obj.setPosInsertar(postInsertar);
                listResult.add(obj);
                postInsertar++;
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

    public static CmAuditoriaCapitaDescuento castEntidadNegocio(CmAuditoriaCapitaDescuentos neg) {
        CmAuditoriaCapitaDescuento ent = new CmAuditoriaCapitaDescuento();
        ent.setId(neg.getId());
        ent.setCmDetalle(new CmDetalle(neg.getCmDetallesId().getId()));
        CntContrato contrato = new CntContrato();
        if (neg.getCntContratosId() != null && neg.getCntContratosId().getId() != null) {
            contrato.setId(neg.getCntContratosId().getId());
            Date fechaInicio = neg.getCntContratosId().getFechaInicio();
            Date fechaFin =  neg.getCntContratosId().getFechaFin();
            contrato.setFechaInicio(fechaInicio);
            contrato.setFechaFin(fechaFin);
            contrato.setValor(neg.getCntContratosId().getValor());
            contrato.setValorPresupuestoTotal(neg.getCntContratosId().getValorPresupuestoTotal());
            // TODO: Evaluar la eliminacion de valor tope en auditoria.
            //contrato.setValorTope(neg.getCntContratosId().getValorTope());
            contrato.setMaeEstadoContratoValor(neg.getCntContratosId().getMaeEstadoContratoValor());
            contrato.setDiasLimitePago(neg.getCntContratosId().getDiasLimitePago());
            CntPrestador prestador = new CntPrestador();
            if(neg.getCntContratosId().getCntPrestadoresId() != null &&
                neg.getCntContratosId().getCntPrestadoresId().getId() != null){
                prestador.setId(neg.getCntContratosId().getCntPrestadoresId().getId());
                prestador.setRazonSocial(neg.getCntContratosId().getCntPrestadoresId().getRazonSocial());
                prestador.setNumeroDocumento(neg.getCntContratosId().getCntPrestadoresId().getNumeroDocumento());
                prestador.setNombreRepresentanteLegal(neg.getCntContratosId().getCntPrestadoresId().getNombreRepresentanteLegal());
            }
            contrato.setCntPrestador(prestador);
        }
        ent.setCntContrato(contrato);
        ent.setMarcacion(neg.getMarcacion());
        ent.setContrato(neg.getContrato());
        ent.setObservacion(neg.getObservacion());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setUsuarioModifica(neg.getUsuarioModifica());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setTerminalModifica(neg.getTerminalModifica());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setFechaHoraModifica(neg.getFechaHoraModifica());
        return ent;
    }

    public static CmAuditoriaCapitaDescuentos castNegocioEntidad(CmAuditoriaCapitaDescuento ent) {
        CmAuditoriaCapitaDescuentos neg = new CmAuditoriaCapitaDescuentos();
        neg.setId(ent.getId());
        neg.setCmDetallesId(new CmDetalles(ent.getCmDetalle().getId()));
        neg.setCntContratosId(new CntContratos(ent.getCntContrato().getId()));
        neg.setMarcacion(ent.getMarcacion());
        neg.setContrato(ent.getContrato());
        neg.setObservacion(ent.getObservacion());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setUsuarioModifica(ent.getUsuarioModifica());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setTerminalModifica(ent.getTerminalModifica());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        neg.setFechaHoraModifica(ent.getFechaHoraModifica());
        return neg;
    }
    
 }
