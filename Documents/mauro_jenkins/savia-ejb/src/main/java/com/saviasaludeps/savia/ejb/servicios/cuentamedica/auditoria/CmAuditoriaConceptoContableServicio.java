/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaConceptoContable;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaConceptosContables;
import com.saviasaludeps.savia.ejb.entidades.CmDetalles;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaConceptoContableRemoto;
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
@Remote(CmAuditoriaConceptoContableRemoto.class)
@Local(CmAuditoriaConceptoContableLocal.class)
public class CmAuditoriaConceptoContableServicio extends GenericoServicio implements  CmAuditoriaConceptoContableLocal, CmAuditoriaConceptoContableRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        return cant;
    }
    
 
    @Override
    public List<CmAuditoriaConceptoContable> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmAuditoriaConceptoContable> listResult = new ArrayList();
        return listResult;
    }
    
    @Override
    public int consultarCantidadPorIdDetalle(int idDetalle) throws Exception {
        int cant = 0;
          try {
            String strQuery = "SELECT COUNT(cmac) FROM CmAuditoriaConceptosContables cmac";         
            strQuery += " WHERE cmac.cmDetallesId.id = :idDetalle ";
            cant = (int) (long)  getEntityManager().createQuery(strQuery).
                          setParameter("idDetalle", idDetalle).getSingleResult();
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
    public CmAuditoriaConceptoContable consultar(int id) throws Exception {
        CmAuditoriaConceptoContable obj = null;
        try {
            CmAuditoriaConceptosContables per = (CmAuditoriaConceptosContables) getEntityManager().find(CmAuditoriaConceptosContables.class, id);
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
    public int insertar(CmAuditoriaConceptoContable obj) throws Exception {
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
    public void actualizar(CmAuditoriaConceptoContable obj) throws Exception {
        try {
            CmAuditoriaConceptosContables concepto =  castNegocioEntidad(obj);       
            String hql = "UPDATE CmAuditoriaConceptosContables SET"
                    + " maeConceptosId = :maeConceptosId,"
                    + " maeConceptosCodigo = :maeConceptosCodigo,"
                    + " maeConceptosValor = :maeConceptosValor,"
                    + " maeCentroCostoId  = :maeCentroCostoId,"
                    + " maeCentroCostoCodigo = :maeCentroCostoCodigo,"
                    + " maeCentroCostoValor = :maeCentroCostoValor,"
                    + " porcentaje = :porcentaje,"
                    + " codigoMunicipio = :codigoMunicipio,"
                    + " municipioAfiliado = :municipioAfiliado,"
                    + " cmDetallesId.id = :cmDetallesId"
                    + " WHERE id = :id";
           
            Query query = getEntityManager().createQuery(hql);
             
            query.setParameter("maeConceptosId", concepto.getMaeConceptosId());
            query.setParameter("maeConceptosCodigo", concepto.getMaeConceptosCodigo());
            query.setParameter("maeConceptosValor", concepto.getMaeConceptosValor());
            query.setParameter("maeCentroCostoId", concepto.getMaeCentroCostoId());
            query.setParameter("maeCentroCostoCodigo",concepto.getMaeCentroCostoCodigo());
            query.setParameter("maeCentroCostoValor", concepto.getMaeCentroCostoValor());
            query.setParameter("porcentaje", concepto.getPorcentaje());
            query.setParameter("codigoMunicipio", concepto.getCodigoMunicipio());
            query.setParameter("municipioAfiliado", concepto.getMunicipioAfiliado());
            query.setParameter("cmDetallesId", concepto.getCmDetallesId().getId());
            query.setParameter("id", concepto.getId());
            query.executeUpdate();
            
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CmAuditoriaConceptoContable eliminar(int id) throws Exception {
        CmAuditoriaConceptoContable obj = null;
        try {
            CmAuditoriaConceptosContables per = getEntityManager().find(CmAuditoriaConceptosContables.class, id);
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
            String strQuery = "SELECT COUNT(cmcc) FROM CmAuditoriaConceptosContables cmcc ";         
            strQuery += " WHERE cmcc.cmDetallesId.id = :detalleId ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoServicio":
                            strQuery += " AND cmcc.codigoServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreServicio":
                            strQuery += " AND cmcc.nombreServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "documento":
                            strQuery += " AND cmcc.documento LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicadoGlosa":
                            strQuery += " AND cmcc.radicadoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "motivoGlosa":
                            strQuery += " AND cmcc.motivoGlosa LIKE '%" + e.getValue() + "%' ";
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
    public List<CmAuditoriaConceptoContable> consultarListaPorDetalle(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CmAuditoriaConceptoContable> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmAuditoriaConceptosContables cmcc ";
            strQuery += " WHERE cmcc.cmDetallesId.id = :detalleId";
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoServicio":
                            strQuery += " AND cmcc.codigoServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreServicio":
                            strQuery += " AND cmcc.nombreServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "documento":
                            strQuery += " AND cmcc.documento LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicadoGlosa":
                            strQuery += " AND cmcc.radicadoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "motivoGlosa":
                            strQuery += " AND cmcc.motivoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
              
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {     
                String order = paramConsulta.getOrden().replace("cmGlosas", "cmGlosasId").
                                                        replace("gsZona", "gsZonasId");
                strQuery += " cmcc." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " cmcc.fechaHoraCrea DESC , cmcc.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("detalleId", paramConsulta.getParametroConsulta1());
       
            List<CmAuditoriaConceptosContables> list ;
            
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
            for (CmAuditoriaConceptosContables per : list) {
                CmAuditoriaConceptoContable concepto = castEntidadNegocio(per);
                concepto.setPosInsertar(postInsertar);
                listResult.add(concepto);
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
    
    public static CmAuditoriaConceptoContable castEntidadNegocio(CmAuditoriaConceptosContables neg) {
        CmAuditoriaConceptoContable ent = new CmAuditoriaConceptoContable();
        ent.setId(neg.getId());
        ent.setCmDetalle(new CmDetalle(neg.getCmDetallesId().getId()));
        
        ent.setMaeConceptosId(neg.getMaeConceptosId());
        ent.setMaeConceptosCodigo(neg.getMaeConceptosCodigo());
        ent.setMaeConceptosValor(neg.getMaeConceptosValor());
        Maestro conceptoMaestro = new Maestro();
        conceptoMaestro.setId(ent.getMaeConceptosId());
        conceptoMaestro.setValor(ent.getMaeConceptosCodigo());
        conceptoMaestro.setNombre(ent.getMaeConceptosValor());
        ent.setMaestroConceptos(conceptoMaestro);
        
        ent.setMaeCentroCostoId(neg.getMaeCentroCostoId());
        ent.setMaeCentroCostoCodigo(neg.getMaeCentroCostoCodigo());
        ent.setMaeCentroCostoValor(neg.getMaeCentroCostoValor());
        Maestro centroCosto = new Maestro();
        centroCosto.setId(ent.getMaeCentroCostoId());
        centroCosto.setValor(ent.getMaeCentroCostoCodigo());
        centroCosto.setNombre(ent.getMaeCentroCostoValor());
        ent.setMaestroCentroCosto(centroCosto);
        
        ent.setPorcentaje(neg.getPorcentaje());
        ent.setCodigoMunicipio(neg.getCodigoMunicipio());
        ent.setMunicipioAfiliado(neg.getMunicipioAfiliado());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());  
        return ent;
    }

    public static CmAuditoriaConceptosContables castNegocioEntidad(CmAuditoriaConceptoContable ent) {
        CmAuditoriaConceptosContables neg = new CmAuditoriaConceptosContables();
        neg.setId(ent.getId());
        neg.setCmDetallesId(new CmDetalles(ent.getCmDetalle().getId()));
        neg.setMaeConceptosId(ent.getMaeConceptosId());
        neg.setMaeConceptosCodigo(ent.getMaeConceptosCodigo());
        neg.setMaeConceptosValor(ent.getMaeConceptosValor());
        neg.setMaeCentroCostoId(ent.getMaeCentroCostoId());
        neg.setMaeCentroCostoCodigo(ent.getMaeCentroCostoCodigo());
        neg.setMaeCentroCostoValor(ent.getMaeCentroCostoValor());
        neg.setPorcentaje(ent.getPorcentaje());
        neg.setCodigoMunicipio(ent.getCodigoMunicipio());
        neg.setMunicipioAfiliado(ent.getMunicipioAfiliado());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        return neg;
    }
    
}
