/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria;


import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAdjunto;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmHistoricoFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmFacturas;
import com.saviasaludeps.savia.ejb.entidades.CmHistoricoFacturas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmHistoricoFacturaRemoto;
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
 * @author jperezn
 */
@Stateless
@Remote(CmHistoricoFacturaRemoto.class)
@Local(CmHistoricoFacturaLocal.class)
public class CmHistoricoFacturaServicio extends GenericoServicio implements CmHistoricoFacturaLocal,CmHistoricoFacturaRemoto {

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
    public List<CmHistoricoFactura> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmHistoricoFactura> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmHistoricoFacturas cmc";
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
       
            
            List<CmHistoricoFacturas> list;
            
            if (paramConsulta.getParametroConsulta2() != null) {
                list = query
                        .getResultList();
            } else {
                list = query
                        .setFirstResult(paramConsulta.getPrimerRegistro())
                        .setMaxResults(paramConsulta.getRegistrosPagina())
                        .getResultList();
            }

            for (CmHistoricoFacturas per : list) {
                CmHistoricoFactura obj = castEntidadNegocio(per);
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
    public CmHistoricoFactura consultar(int id) throws Exception {
        CmHistoricoFactura obj = null;
        try {
            CmHistoricoFacturas per = (CmHistoricoFacturas) getEntityManager().find(CmHistoricoFacturas.class, id);
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
    public int insertar(CmHistoricoFactura obj) throws Exception {
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
    public void actualizar(CmHistoricoFactura obj) throws Exception {
        try {
            CmHistoricoFacturas  historico =  castNegocioEntidad(obj);       
            String hql = "UPDATE CmHistoricoFacturas SET"
                    + " cmHistoricoFacturas = :cmHistoricoFacturas,"
                    + " tipos = :tipos,"
                    + " descripcion = :descripcion,"
                    + " cmFacturasId.id = :cmFacturasId"
                    + " WHERE id = :id";
            
            Query query = getEntityManager().createQuery(hql);
            
            query.setParameter("cmHistoricoFacturascol", historico.getCmHistoricoFacturas());
            query.setParameter("tipos", historico.getTipos());
            query.setParameter("descripcion", historico.getDescripcion());
            query.setParameter("cmFacturasId", historico.getCmFacturasId().getId());
            query.setParameter("id", historico.getId());
            query.executeUpdate();
            
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CmHistoricoFactura eliminar(int id) throws Exception {
        CmHistoricoFactura obj = null;
        try {
            CmHistoricoFacturas per = getEntityManager().find(CmHistoricoFacturas.class, id);
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
   
    
    public static CmHistoricoFactura castEntidadNegocio(CmHistoricoFacturas neg) {
        CmHistoricoFactura ent = new CmHistoricoFactura();
        ent.setId(neg.getId());
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
                neg.getCmFacturasId().getValorCopago()
        );
        ent.setCmFactura(factura);
        ent.setCmHistoricoFacturas( neg.getCmHistoricoFacturas() );
        ent.setTipos(neg.getTipos());
        ent.setDescripcion(neg.getDescripcion());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        return ent;
    }

    public static CmHistoricoFacturas castNegocioEntidad(CmHistoricoFactura ent) {
        CmHistoricoFacturas neg = new CmHistoricoFacturas();
        neg.setId(ent.getId());
        neg.setCmFacturasId(new CmFacturas(ent.getCmFactura().getId()));
        neg.setCmHistoricoFacturas(ent.getCmHistoricoFacturas());
        neg.setTipos(ent.getTipos());
        neg.setDescripcion(ent.getDescripcion());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        return neg;
    }

    @Override
    public int consultarCantidadPorFactura(ParamConsulta paramConsulta) throws java.lang.Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CmAuditoriaAdjunto> consultarListaPorFactura(ParamConsulta paramConsulta) throws java.lang.Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
 }
