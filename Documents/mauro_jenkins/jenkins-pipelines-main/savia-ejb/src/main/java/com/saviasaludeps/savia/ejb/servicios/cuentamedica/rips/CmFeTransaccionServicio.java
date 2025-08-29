package com.saviasaludeps.savia.ejb.servicios.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCarga;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeTransaccion;
import com.saviasaludeps.savia.ejb.entidades.CmFeRipsCargas;
import com.saviasaludeps.savia.ejb.entidades.CmFeTransacciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmFeTransaccionRemoto;

@Stateless
@Remote(CmFeTransaccionRemoto.class)
public class CmFeTransaccionServicio extends GenericoServicio implements CmFeTransaccionRemoto{


    @Override
    public int insertar(CmFeTransaccion obj) throws java.lang.Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(CmFeTransaccion obj) throws java.lang.Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public CmFeTransaccion eliminar(int id) throws java.lang.Exception {
        CmFeTransaccion obj = null;
        try {
            CmFeTransacciones ent = getEntityManager().find(CmFeTransacciones.class, id);
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
    public CmFeTransaccion consultar(int id) throws java.lang.Exception {
        CmFeTransaccion objRes = null;
        try {
            String hql = "SELECT t FROM CmFeTransacciones t "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("id", id);
            CmFeTransacciones carga = (CmFeTransacciones) query.getSingleResult();
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
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(t.id) FROM CmFeTransacciones t  "
                    + " WHERE t.id > 0 AND t.cmFeRipsCargasId.id = :id ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "estado":
                            strQuery += " AND t.estado = " + e.getValue() + " ";
                            break;
                    }
                }
            }

            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("id", paramConsulta.getParametroConsulta1());
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
    public List<CmFeTransaccion> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CmFeTransaccion> listaCargas = new ArrayList();
        try {
            String strQuery = "FROM CmFeTransacciones t "
                    + " WHERE t.id > 0 AND t.cmFeRipsCargasId.id= :id";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                           case "estado":
                            strQuery += " AND t.estado = " + e.getValue() + " ";
                            break;
                    }
                }
            }
           
            strQuery += " ORDER BY t.id desc";
            Query query = getEntityManager().createQuery(strQuery);
            
            if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("id", paramConsulta.getParametroConsulta1());
            }
            
            List<CmFeTransacciones> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();

            for (CmFeTransacciones per : list) {
                listaCargas.add(castEntidadNegocio(per));
            }
        } catch (NoResultException e) {
            listaCargas = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaCargas;
    }
    
    
    
    public static CmFeTransaccion castEntidadNegocio(CmFeTransacciones ent) {
        CmFeTransaccion neg = new CmFeTransaccion();
        if (ent.getId() != null) {
            neg.setId(ent.getId());
        }
        neg.setCmFeRipsCarga(new CmFeRipsCarga(ent.getCmFeRipsCargasId().getId()));
        neg.setFechaHoraEnvio(ent.getFechaHoraEnvio());
        neg.setJsonEnvio(ent.getJsonEnvio());
        neg.setEstado(ent.getEstado());
        neg.setFechaHoraRespuesta(ent.getFechaHoraRespuesta());
        neg.setJsonRespuesta(ent.getJsonRespuesta());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        return neg;
    }
    public static CmFeTransacciones castNegocioEntidad(CmFeTransaccion neg) {
        CmFeTransacciones ent = new CmFeTransacciones();
        if (neg.getId() != null) {
            ent.setId(neg.getId());
        }
        ent.setCmFeRipsCargasId(new CmFeRipsCargas(neg.getCmFeRipsCarga().getId()));
        ent.setFechaHoraEnvio(neg.getFechaHoraEnvio());
        ent.setJsonEnvio(neg.getJsonEnvio());
        ent.setEstado(neg.getEstado());
        ent.setFechaHoraRespuesta(neg.getFechaHoraRespuesta());
        ent.setJsonRespuesta(neg.getJsonRespuesta());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        return ent;
    }
    
}
