package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjContrato;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoObligacion;
import com.saviasaludeps.savia.ejb.entidades.CntjContratoObligaciones;
import com.saviasaludeps.savia.ejb.entidades.CntjContratos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoObligacionRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author idbohorquez
 */
@Stateless
@Remote(CntjContratoObligacionRemoto.class)
public class CntjContratoObligacionServicio  extends GenericoServicio implements CntjContratoObligacionRemoto{

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjContratoObligaciones c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append(" AND c.id = " + e.getValue() + " ");
                            break;                       
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery.toString())
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
    public List<CntjContratoObligacion> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntjContratoObligacion> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjContratoObligaciones c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append(" AND c.id = " + e.getValue() + " ");
                            break;
                    }
                }
            }
            strQuery.append(" ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                strQuery.append(" c." + paramConsulta.getOrden() + " " + (paramConsulta.isAscendente() ? "ASC" : "DESC"));
            } else {
                strQuery.append(" c.id DESC ");
            }
            List<CntjContratoObligaciones> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjContratoObligaciones obligacion : list) {
                listResult.add(castEntidadNegocio(obligacion));
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
    public int insertar(CntjContratoObligacion objeto) throws java.lang.Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(objeto)).getId();
            objeto.setId(id);
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
    public CntjContratoObligacion consultar(int id) throws java.lang.Exception {
        CntjContratoObligacion objRes = null;
        try {
            objRes = castEntidadNegocio((CntjContratoObligaciones) getEntityManager().find(CntjContratoObligaciones.class, id));
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
    public void eliminarPorcontrato(int idcontrato) throws Exception {
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjContratoObligaciones c WHERE c.id > 0 and c.cntjContratosId.id =  ").append(idcontrato);      
            List<CntjContratoObligaciones> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (CntjContratoObligaciones obligacion : list) {
                getEntityManager().remove(obligacion);
            }
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public List<CntjContratoObligacion> obligacionesContrato(int idcontrato) throws Exception {
        List<CntjContratoObligacion> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjContratoObligaciones c WHERE c.id > 0 and c.cntjContratosId.id =  ").append(idcontrato);      
            List<CntjContratoObligaciones> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (CntjContratoObligaciones obligacion : list) {
                listResult.add(castEntidadNegocio(obligacion));
            }
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
    @Override
    public CntjContratoObligacion eliminar(int id) throws Exception {
        CntjContratoObligacion obj = null;
        try {
            CntjContratoObligaciones ent = getEntityManager().find(CntjContratoObligaciones.class, id);
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
   
    private CntjContratoObligacion castEntidadNegocio(CntjContratoObligaciones entidad) {
        CntjContratoObligacion objeto = new CntjContratoObligacion();
        objeto.setId(entidad.getId());        
        objeto.setCntjContratosId(new CntjContrato(entidad.getCntjContratosId().getId()));
        objeto.setNumeroObligacion(entidad.getNumeroObligacion());
        objeto.setDescripcion(entidad.getDescripcion());      
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        objeto.setUsuarioModifica(entidad.getUsuarioModifica());
        objeto.setFechaHoraModifica(entidad.getFechaHoraModifica());
        objeto.setTerminalModifica(entidad.getTerminalModifica());
        return objeto;
    }
    
    private CntjContratoObligaciones castNegocioEntidad(CntjContratoObligacion obj){
        CntjContratoObligaciones ent = new CntjContratoObligaciones();
        ent.setCntjContratosId(new CntjContratos(obj.getCntjContratosId().getId()));
        ent.setNumeroObligacion(obj.getNumeroObligacion());
        ent.setDescripcion(obj.getDescripcion());                
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }
   
    
}
