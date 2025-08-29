/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria;


import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDiagnostico;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaDiagnosticos;
import com.saviasaludeps.savia.ejb.entidades.CmDetalles;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaDiagnosticoRemoto;
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
@Remote(CmAuditoriaDiagnosticoRemoto.class)
@Local(CmAuditoriaDiagnosticoLocal.class)
public class CmAuditoriaDiagnosticoServicio extends GenericoServicio implements CmAuditoriaDiagnosticoLocal, CmAuditoriaDiagnosticoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        return cant;
    }
    
    @Override
    public List<CmAuditoriaDiagnostico> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmAuditoriaDiagnostico> listResult = new ArrayList();
        return listResult;
    }
    
    @Override
    public CmAuditoriaDiagnostico consultar(int id) throws Exception {
        CmAuditoriaDiagnostico obj = null;
        try {
            CmAuditoriaDiagnosticos per = (CmAuditoriaDiagnosticos) getEntityManager().find(CmAuditoriaDiagnosticos.class, id);
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
    public int insertar(CmAuditoriaDiagnostico obj) throws Exception {
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
    public void actualizar(CmAuditoriaDiagnostico obj) throws Exception {
        try {
            CmAuditoriaDiagnosticos  dignostico =  castNegocioEntidad(obj);       
            String hql = "UPDATE CmAuditoriaDiagnosticos SET"
                    + " maDiagniosticosId = :maDiagniosticosId,"
                    + " maDiagnosticosCodigo = :maDiagnosticosCodigo,"
                    + " maDiagnosticosValor = :maDiagnosticosValor,"
                    + " usuarioModifica  = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica,"
                    + " cmDetallesId.id = :cmDetallesId"
                    + " WHERE id = :id";
            
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("maDiagniosticosId", dignostico.getMaDiagniosticosId());
            query.setParameter("maDiagnosticosCodigo", dignostico.getMaDiagnosticosCodigo());
            query.setParameter("maDiagnosticosValor", dignostico.getMaDiagnosticosValor());
            query.setParameter("usuarioModifica", dignostico.getUsuarioModifica());
            query.setParameter("terminalModifica", dignostico.getTerminalModifica());
            query.setParameter("fechaHoraModifica", dignostico.getFechaHoraModifica());
            query.setParameter("cmDetallesId", dignostico.getCmDetallesId().getId());
            query.setParameter("id", dignostico.getId());
            query.executeUpdate();
            
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CmAuditoriaDiagnostico eliminar(int id) throws Exception {
        CmAuditoriaDiagnostico obj = null;
        try {
            CmAuditoriaDiagnosticos per = getEntityManager().find(CmAuditoriaDiagnosticos.class, id);
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
            String strQuery = "SELECT COUNT(cma) FROM CmAuditoriaDiagnosticos cma ";         
            strQuery += " WHERE cma.cmDetallesId.id = :detalleId ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoServicio":
                            strQuery += " AND cmd.codigoServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreServicio":
                            strQuery += " AND cmd.nombreServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "documento":
                            strQuery += " AND cmd.documento LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicadoGlosa":
                            strQuery += " AND cmd.radicadoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "motivoGlosa":
                            strQuery += " AND cmd.motivoGlosa LIKE '%" + e.getValue() + "%' ";
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
    public List<CmAuditoriaDiagnostico> consultarListaPorDetalle(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CmAuditoriaDiagnostico> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmAuditoriaDiagnosticos cma ";
            strQuery += " WHERE cma.cmDetallesId.id = :detalleId";
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoServicio":
                            strQuery += " AND cmd.codigoServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreServicio":
                            strQuery += " AND cmd.nombreServicio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "documento":
                            strQuery += " AND cmd.documento LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicadoGlosa":
                            strQuery += " AND cmd.radicadoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "motivoGlosa":
                            strQuery += " AND cmd.motivoGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
              
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {     
                String order = paramConsulta.getOrden().replace("cmGlosas", "cmGlosasId").
                                                        replace("gsZona", "gsZonasId");
                strQuery += " cma." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " cma.fechaHoraCrea DESC , cma.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("detalleId", paramConsulta.getParametroConsulta1());
       
            List<CmAuditoriaDiagnosticos> list;
            
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
            for (CmAuditoriaDiagnosticos per : list) {
                CmAuditoriaDiagnostico  obj = castEntidadNegocio(per);
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
    
    public static CmAuditoriaDiagnostico castEntidadNegocio(CmAuditoriaDiagnosticos neg) {
           CmAuditoriaDiagnostico ent = new CmAuditoriaDiagnostico();
           ent.setId(neg.getId());
           ent.setCmDetalle(new CmDetalle(neg.getCmDetallesId().getId()));
           ent.setMaDiagniosticosId(neg.getMaDiagniosticosId());
           ent.setMaDiagnosticoCodigo(neg.getMaDiagnosticosCodigo());
           ent.setMaDiagnosticoValor(neg.getMaDiagnosticosValor());
           ent.setUsuarioCrea(neg.getUsuarioCrea());
           ent.setPrincipal(neg.getPrincipal());
           ent.setUsuarioModifica(neg.getUsuarioModifica());
           ent.setTerminalCrea(neg.getTerminalCrea());
           ent.setTerminalModifica(neg.getTerminalModifica());
           ent.setFechaHoraCrea(neg.getFechaHoraCrea());
           ent.setFechaHoraModifica(neg.getFechaHoraModifica());
           return ent;
    }

    public static CmAuditoriaDiagnosticos castNegocioEntidad(CmAuditoriaDiagnostico ent) {
           CmAuditoriaDiagnosticos neg = new CmAuditoriaDiagnosticos();
           neg.setId(ent.getId());
           neg.setCmDetallesId(new CmDetalles(ent.getCmDetalle().getId()));
           neg.setMaDiagniosticosId(ent.getMaDiagniosticosId());
           neg.setMaDiagnosticosCodigo(ent.getMaDiagnosticoCodigo());
           neg.setMaDiagnosticosValor(ent.getMaDiagnosticoValor());
           neg.setPrincipal(ent.isPrincipal());
           neg.setUsuarioCrea(ent.getUsuarioCrea());
           neg.setUsuarioModifica(ent.getUsuarioModifica());
           neg.setTerminalCrea(ent.getTerminalCrea());
           neg.setTerminalModifica(ent.getTerminalModifica());
           neg.setFechaHoraCrea(ent.getFechaHoraCrea());
           neg.setFechaHoraModifica(ent.getFechaHoraModifica());
          return neg;
    }
}
