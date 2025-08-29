package com.saviasaludeps.savia.ejb.servicios.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCarga;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeSoporte;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmFacturas;
import com.saviasaludeps.savia.ejb.entidades.CmFeRipsCargas;
import com.saviasaludeps.savia.ejb.entidades.CmFeSoportes;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmFeSoporteRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

@Stateless
@Remote(CmFeSoporteRemoto.class)
public class CmFeSoporteServicio extends GenericoServicio implements CmFeSoporteRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(s.id) FROM CmFeSoportes s WHERE s.id > 0 ";
            
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " and s.cmFeRipsCargasId.id= :idCarga ";
            }
            
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += " AND s.gnEmpresasId.id = :empresa_id ";
            }
  
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND s.id = '" + e.getValue() + "' ";
                            break;
                       case "cmFeRipsCarga.id":
                            strQuery += "AND s.cmFeRipsCargasId.id = '" + e.getValue() + "' ";
                            break;
                        case "cmFeRipsCarga.facturaNumero":
                            strQuery += "AND s.cmFeRipsCargasId.facturaNumero = '" + e.getValue() + "' ";
                            break;
                        case "archivoNombre":
                            strQuery += "AND s.archivoNombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoSoporteId":
                            strQuery += "AND s.maeTipoSoporteId = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("idCarga", paramConsulta.getParametroConsulta1());
            }

            if (paramConsulta.getEmpresaId() != null) {
                query.setParameter("empresa_id", paramConsulta.getEmpresaId());
            }

            cant = (int) (long) query
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
    public List<CmFeSoporte> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmFeSoporte> listResult = new ArrayList();
        try {
            String strQuery = "SELECT s FROM CmFeSoportes s WHERE s.id > 0   ";

            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND s.cmFeRipsCargasId.id= :idCarga ";
            }

            if (paramConsulta.getEmpresaId() != null) {
                strQuery += " AND s.gnEmpresasId.id = :empresa_id ";
            }
                         
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND s.id = '" + e.getValue() + "' ";
                            break;
                       case "cmFeRipsCarga.id":
                            strQuery += "AND s.cmFeRipsCargasId.id = '" + e.getValue() + "' ";
                            break;
                        case "cmFeRipsCarga.facturaNumero":
                            strQuery += "AND s.cmFeRipsCargasId.facturaNumero = '" + e.getValue() + "' ";
                            break;
                        case "archivoNombre":
                            strQuery += "AND s.archivoNombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoSoporteId":
                            strQuery += "AND s.maeTipoSoporteId = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "s." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "s.id DESC";
            }
            
             Query query = getEntityManager().createQuery(strQuery);
            
            if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("idCarga", paramConsulta.getParametroConsulta1());
            }
            
            if (paramConsulta.getEmpresaId() != null) {
               query.setParameter("empresa_id",  paramConsulta.getEmpresaId());
            }
            
            List<CmFeSoportes> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CmFeSoportes per : list) {
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
    public CmFeSoporte consultar(int id) throws Exception {
        CmFeSoporte objRes = null;
        try {
            objRes = castEntidadNegocio((CmFeSoportes) getEntityManager().find(CmFeSoportes.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public int insertar(CmFeSoporte obj) throws java.lang.Exception {
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
    public void actualizar(CmFeSoporte obj) throws java.lang.Exception {
            try {
            Query query;
            String hql = "UPDATE CmFeSoportes SET "
                    + " cmFacturasId.id = :facturaId ,"
                    + " WHERE id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("facturaId", obj.getCmFactura().getId());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
            //Actualizar roles
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        } 
    }
    
    @Override
    public void actualizarFacturaId(CmFeSoporte obj) throws java.lang.Exception {
        try {
            if (obj.getCmFactura().getId() != null) {
                Query query;
                String hql = "UPDATE CmFeSoportes SET "
                        + " cmFacturasId.id = :facturaId ,"
                        + " WHERE id = :id ";
                query = getEntityManager().createQuery(hql);
                query.setParameter("facturaId", obj.getCmFactura().getId());
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
    

    public static CmFeSoportes castNegocioEntidad(CmFeSoporte neg) {
        CmFeSoportes ent = new CmFeSoportes(); 
        ent.setId(neg.getId());
        if (neg.getCmFeRipsCarga() != null && neg.getCmFeRipsCarga().getId() != null) {
            ent.setCmFeRipsCargasId(new CmFeRipsCargas(neg.getCmFeRipsCarga().getId()));
        }
        if (neg.getCmFactura()!= null && neg.getCmFactura().getId() != null) {
           ent.setCmFacturasId(new CmFacturas(neg.getCmFactura().getId()));
        }
        if (neg.getEmpresa()!= null && neg.getEmpresa().getId() != null) {
            ent.setGnEmpresasId(new GnEmpresas(neg.getEmpresa().getId()));
        }
        ent.setMaeTipoSoporteId(neg.getMaeTipoSoporteId());
        ent.setMaeTipoSoporteCodigo(neg.getMaeTipoSoporteCodigo());
        ent.setMaeTipoSoporteValor(neg.getMaeTipoSoporteValor());
        ent.setArchivoNombre(neg.getArchivoNombre());
        ent.setArchivoRuta(neg.getArchivoRuta());
        ent.setArchivo(neg.getArchivo());
        ent.setArchivoExiste(neg.getArchivoExiste());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        
        return ent;
    }

    public static CmFeSoporte castEntidadNegocio(CmFeSoportes ent) {
        CmFeSoporte neg = new CmFeSoporte();
        neg.setId(ent.getId());
        if (ent.getCmFeRipsCargasId() != null && ent.getCmFeRipsCargasId().getId() != null) {
            neg.setCmFeRipsCarga(new CmFeRipsCarga(ent.getCmFeRipsCargasId().getId()));
            neg.getCmFeRipsCarga().setFacturaNumero(ent.getCmFeRipsCargasId().getFacturaNumero());
        }
        if (ent.getCmFacturasId() != null && ent.getCmFacturasId().getId() != null) {
            neg.setCmFactura(new CmFactura(ent.getCmFacturasId().getId()));
            neg.getCmFactura().setNumeroFacturado(ent.getCmFacturasId().getNumeroFacturado());
        }
        
        if (ent.getGnEmpresasId() != null && ent.getGnEmpresasId().getId() != null) {
            neg.setEmpresa(new Empresa(ent.getGnEmpresasId().getId()));
        }
        
        neg.setMaeTipoSoporteId(ent.getMaeTipoSoporteId());
        neg.setMaeTipoSoporteCodigo(ent.getMaeTipoSoporteCodigo());
        neg.setMaeTipoSoporteValor(ent.getMaeTipoSoporteValor());
        neg.setArchivoNombre(ent.getArchivoNombre());
        neg.setArchivoRuta(ent.getArchivoRuta());
        neg.setArchivo(ent.getArchivo());
        neg.setArchivoExiste(ent.getArchivoExiste());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        return neg;
    }

    
}
