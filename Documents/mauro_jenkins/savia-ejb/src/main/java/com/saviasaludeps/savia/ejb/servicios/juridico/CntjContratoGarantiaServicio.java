package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjContrato;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoGarantia;
import com.saviasaludeps.savia.ejb.entidades.CntjContratoGarantias;
import com.saviasaludeps.savia.ejb.entidades.CntjContratos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoGarantiaRemoto;
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
@Remote(CntjContratoGarantiaRemoto.class)
public class CntjContratoGarantiaServicio  extends GenericoServicio implements CntjContratoGarantiaRemoto{

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjContratoGarantias c WHERE c.id > 0 ");
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
    public List<CntjContratoGarantia> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntjContratoGarantia> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjContratoGarantias c WHERE c.id > 0 ");
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
            List<CntjContratoGarantias> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjContratoGarantias garantia : list) {
                listResult.add(castEntidadNegocio(garantia));
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
    public int insertar(CntjContratoGarantia objeto) throws java.lang.Exception {
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
    public CntjContratoGarantia consultar(int id) throws java.lang.Exception {
        CntjContratoGarantia objRes = null;
        try {
            objRes = castEntidadNegocio((CntjContratoGarantias) getEntityManager().find(CntjContratoGarantias.class, id));
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
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjContratoGarantias c WHERE c.id > 0 and c.cntjContratosId.id =  ").append(idcontrato);      
            List<CntjContratoGarantias> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (CntjContratoGarantias garantia : list) {
                getEntityManager().remove(garantia);
            }
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public CntjContratoGarantia eliminar(int id) throws Exception {
        CntjContratoGarantia obj = null;
        try {
            CntjContratoGarantias ent = getEntityManager().find(CntjContratoGarantias.class, id);
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
    public List<CntjContratoGarantia> garantiasContrato(int idcontrato) throws Exception {
        List<CntjContratoGarantia> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjContratoGarantias c WHERE c.id > 0 and c.cntjContratosId.id =  ").append(idcontrato);      
            List<CntjContratoGarantias> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (CntjContratoGarantias garantia : list) {
                listResult.add(castEntidadNegocio(garantia));
            }
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
   
    private CntjContratoGarantia castEntidadNegocio(CntjContratoGarantias entidad) {
        CntjContratoGarantia objeto = new CntjContratoGarantia();
        objeto.setId(entidad.getId());        
        objeto.setCntjContratoId(new CntjContrato(entidad.getCntjContratosId().getId()));                
        objeto.setMaeGarantiaContratoId(entidad.getMaeGarantiaContratoId());
        objeto.setMaeGarantiaContratoCodigo(entidad.getMaeGarantiaContratoCodigo());
        objeto.setMaeGarantiaContratoValor(entidad.getMaeGarantiaContratoValor());
        objeto.setFechaExpedicion(entidad.getFechaExpedicion());
        objeto.setPorcentajeValorContrato(entidad.getPorcentajeValorContrato());
        objeto.setPorcentajeValorAnticipo(entidad.getPorcentajeValorAnticipo());
        objeto.setValorAsegurado(entidad.getValorAsegurado());
        objeto.setVigenciaDesde(entidad.getVigenciaDesde());
        objeto.setVigenciaHasta(entidad.getVigenciaHasta());
        objeto.setEstado(entidad.getEstado());   
        objeto.setFechaAprobacion(entidad.getFechaAprobacion());
        objeto.setRequiereRenovacion(entidad.getRequiereRenovacion());
        
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        objeto.setUsuarioModifica(entidad.getUsuarioModifica());
        objeto.setFechaHoraModifica(entidad.getFechaHoraModifica());
        objeto.setTerminalModifica(entidad.getTerminalModifica());
        return objeto;
    }
    
    private CntjContratoGarantias castNegocioEntidad(CntjContratoGarantia obj){
        CntjContratoGarantias ent = new CntjContratoGarantias();
        ent.setCntjContratosId(new CntjContratos(obj.getCntjContratoId().getId()));                
        ent.setMaeGarantiaContratoId(obj.getMaeGarantiaContratoId());
        ent.setMaeGarantiaContratoCodigo(obj.getMaeGarantiaContratoCodigo());
        ent.setMaeGarantiaContratoValor(obj.getMaeGarantiaContratoValor());
        ent.setFechaExpedicion(obj.getFechaExpedicion());
        ent.setPorcentajeValorContrato(obj.getPorcentajeValorContrato());
        ent.setPorcentajeValorAnticipo(obj.getPorcentajeValorAnticipo());
        ent.setValorAsegurado(obj.getValorAsegurado());
        ent.setVigenciaDesde(obj.getVigenciaDesde());
        ent.setVigenciaHasta(obj.getVigenciaHasta());
        ent.setEstado(obj.getEstado());    
        ent.setFechaAprobacion(obj.getFechaAprobacion());
        ent.setRequiereRenovacion(obj.isRequiereRenovacion());
        
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }
   
    
}
