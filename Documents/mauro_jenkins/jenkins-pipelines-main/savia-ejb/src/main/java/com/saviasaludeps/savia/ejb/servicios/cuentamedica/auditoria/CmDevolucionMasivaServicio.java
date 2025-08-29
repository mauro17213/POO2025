/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria;



import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmDevolucionMasivaN;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmDevolucionMasiva;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmDevolucionMasivaRemoto;
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
@Remote(CmDevolucionMasivaRemoto.class)
@Local(CmDevolucionMasivaLocal.class)
public class CmDevolucionMasivaServicio extends GenericoServicio implements  CmDevolucionMasivaLocal, CmDevolucionMasivaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cmam) FROM CmDevolucionMasiva cmam ";         
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
                            strQuery += " AND cmam.nit LIKE '%" + e.getValue() + "%' ";
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
    public List<CmDevolucionMasivaN> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmDevolucionMasivaN> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmDevolucionMasiva cmam ";        
            strQuery += " WHERE 1= 1  ";
            
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
                            strQuery += " AND cmam.nit LIKE '%" + e.getValue() + "%' ";
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
                strQuery += " cmam.fechaHoraCrea DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);          
            
            List<CmDevolucionMasiva> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CmDevolucionMasiva neg : list) {
                CmDevolucionMasivaN devolucionMasiva = castEntidadNegocio(neg);
                listResult.add(devolucionMasiva);
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
    public int insertar(CmDevolucionMasivaN obj) throws java.lang.Exception {
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
    public CmDevolucionMasivaN consultar(int id) throws Exception {
        CmDevolucionMasivaN obj = null;
        try {
            CmDevolucionMasiva per = (CmDevolucionMasiva) getEntityManager().find(CmDevolucionMasiva.class, id);
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
    public void actualizar(CmDevolucionMasivaN obj) throws Exception {
        try {
 
            CmDevolucionMasiva auditoriaMasiva =  castNegocioEntidad(obj);       
            String hql = "UPDATE CmDevolucionMasiva SET"
                    + " nit = :nit, "
                    + " ips = :ips, "
                    + " sumaValorFacturado = :sumaValorFacturado, "
                    + " sumaValorCopago = :sumaValorCopago, "
                    + " sumaValorBruto = :sumaValorBruto, "
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
    public CmDevolucionMasivaN eliminar(int id) throws Exception {
        CmDevolucionMasivaN obj = null;
        try {
            CmDevolucionMasiva per = getEntityManager().find(CmDevolucionMasiva.class, id);
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
    
     

    public static CmDevolucionMasivaN castEntidadNegocio(CmDevolucionMasiva neg) {
         CmDevolucionMasivaN ent = new CmDevolucionMasivaN();
         ent.setId(neg.getId());   
         ent.setCntPrestadores(new CntPrestador(neg.getCntPrestadoresId().getId()));
         ent.setNit(neg.getNit());
         ent.setIps(neg.getIps());
         ent.setSumaValorFacturado(neg.getSumaValorFacturado());
         ent.setSumaValorCopago(neg.getSumaValorCopago());
         ent.setSumaValorBruto(neg.getSumaValorBruto());
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

    public static CmDevolucionMasiva castNegocioEntidad(CmDevolucionMasivaN ent) {
         CmDevolucionMasiva neg = new CmDevolucionMasiva();
         neg.setId(ent.getId());  
         neg.setCntPrestadoresId(new CntPrestadores(ent.getCntPrestadores().getId()));
         neg.setNit(ent.getNit());
         neg.setIps(ent.getIps());
         neg.setSumaValorFacturado(ent.getSumaValorFacturado());
         neg.setSumaValorCopago(ent.getSumaValorCopago());
         neg.setSumaValorBruto(ent.getSumaValorBruto());
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
    

 }
