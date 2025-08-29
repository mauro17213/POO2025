package com.saviasaludeps.savia.ejb.servicios.cuentamedica.rips;

import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCargaAdjunto;
import com.saviasaludeps.savia.ejb.entidades.CmFeRipsCargaAdjuntos;
import com.saviasaludeps.savia.ejb.entidades.CmFeRipsCargas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmFeRipsCargaAdjuntoRemoto;
import java.util.ArrayList;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Stateless
@Remote(CmFeRipsCargaAdjuntoRemoto.class)
public class CmFeRipsCargaAdjuntoServicio extends GenericoServicio implements CmFeRipsCargaAdjuntoRemoto {



    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c.id) FROM CmFeRipsCargaAdjuntos c "
                    + " WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                     switch ((String) e.getKey()) {
                        case "numeroCuenta":
                            strQuery += "AND c.numeroCuenta = " + (String) e.getValue() + " ";
                            break;
                        case "id":
                            strQuery += "AND c.id = " + e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND c.estado = " + e.getValue() + " ";
                            break;
                        case "usuarioAudita":
                            strQuery += " AND c.usuarioAudita like '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
         
            Query query = getEntityManager().createQuery(strQuery);
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
    public List<CmFeRipsCargaAdjunto> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CmFeRipsCargaAdjunto> listResult = new ArrayList();
        try {
            String strQuery = "SELECT c FROM CmFeRipsCargaAdjuntos c"
                    + " WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "numeroCuenta":
                            strQuery += "AND c.numeroCuenta = " + (String) e.getValue() + " ";
                            break;
                        case "id":
                            strQuery += "AND c.id = " + e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND c.estado = " + e.getValue() + " ";
                            break;
                        case "usuarioAudita":
                            strQuery += " AND c.usuarioAudita like '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
       
            strQuery += "ORDER BY c.id desc";
            Query query = getEntityManager().createQuery(strQuery);
            
            List<CmFeRipsCargaAdjuntos> list= query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            if (list != null) {
                for (CmFeRipsCargaAdjuntos per : list) {
                    listResult.add(castEntidadNegocio(per));
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
    public List<CmFeRipsCargaAdjunto> consultarTodos(int id) throws Exception {
        List<CmFeRipsCargaAdjunto> listResult = new ArrayList();
        try {
            String hql = "SELECT a FROM CmFeRipsCargaAdjuntos a "
                    + "WHERE a.cmFeRipsCargasId.id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("id", id);
            List<CmFeRipsCargaAdjuntos> list = query.getResultList();
            for (CmFeRipsCargaAdjuntos ent : list) {
                listResult.add(castEntidadNegocio(ent));
            }
        } catch (NoResultException e) {
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }


    @Override
    public int insertar(CmFeRipsCargaAdjunto obj) throws java.lang.Exception {
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
    public CmFeRipsCargaAdjunto eliminar(int id) throws java.lang.Exception {
        CmFeRipsCargaAdjunto obj = null;
        try {
            CmFeRipsCargaAdjuntos ent = getEntityManager().find(CmFeRipsCargaAdjuntos.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
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
    public CmFeRipsCargaAdjunto consultar(int id) throws java.lang.Exception {
       CmFeRipsCargaAdjunto objRes = null;
        try {
            String hql = "SELECT c FROM CmFeRipsCargaAdjuntos c "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("id", id);
            CmFeRipsCargaAdjuntos carga = (CmFeRipsCargaAdjuntos) query.getSingleResult();
            objRes = castEntidadNegocio(carga);
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
    public void actualizar(CmFeRipsCargaAdjunto obj) throws java.lang.Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static CmFeRipsCargaAdjunto castEntidadNegocio(CmFeRipsCargaAdjuntos ent) {
        CmFeRipsCargaAdjunto neg = new CmFeRipsCargaAdjunto();
        if (ent.getId() != null) {
            neg.setId(ent.getId());
        }
        if (ent.getCmFeRipsCargasId() != null) {
            neg.setCmFeRipsCarga(new CmFeRipsCarga(ent.getCmFeRipsCargasId().getId()));
        }
        neg.setArchivo(ent.getArchivo());
        neg.setArchivoExiste(ent.getArchivoExiste());
        neg.setArchivoNombre(ent.getArchivoNombre());
        neg.setArchivoRuta(ent.getArchivoRuta());
        neg.setTipo(ent.getTipo());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        return neg;
    }
    public static CmFeRipsCargaAdjuntos castNegocioEntidad(CmFeRipsCargaAdjunto neg) {
        CmFeRipsCargaAdjuntos ent = new CmFeRipsCargaAdjuntos();
        if (neg.getId() != null) {
            ent.setId(neg.getId());
        }
        if (neg.getCmFeRipsCarga() != null) {
            ent.setCmFeRipsCargasId(new CmFeRipsCargas(neg.getCmFeRipsCarga().getId()));
        }
        ent.setTipo(neg.getTipo());
        ent.setArchivo(neg.getArchivo());
        ent.setArchivoExiste(neg.getArchivoExiste());
        ent.setArchivoNombre(neg.getArchivoNombre());
        ent.setArchivoRuta(neg.getArchivoRuta());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        return ent;
    }

    
}
