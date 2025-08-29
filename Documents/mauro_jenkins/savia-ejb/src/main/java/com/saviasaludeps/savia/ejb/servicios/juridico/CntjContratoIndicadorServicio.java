package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjContrato;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoIndicador;
import com.saviasaludeps.savia.ejb.entidades.CntjContratoIndicadores;
import com.saviasaludeps.savia.ejb.entidades.CntjContratos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoIndicadorRemoto;
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
@Remote(CntjContratoIndicadorRemoto.class)
public class CntjContratoIndicadorServicio  extends GenericoServicio implements CntjContratoIndicadorRemoto{

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjContratoIndicadores c WHERE c.id > 0 ");
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
    public List<CntjContratoIndicador> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntjContratoIndicador> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjContratoIndicadores c WHERE c.id > 0 ");
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
            List<CntjContratoIndicadores> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjContratoIndicadores indicador : list) {
                listResult.add(castEntidadNegocio(indicador));
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
    public int insertar(CntjContratoIndicador objeto) throws java.lang.Exception {
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
    public CntjContratoIndicador consultar(int id) throws java.lang.Exception {
        CntjContratoIndicador objRes = null;
        try {
            objRes = castEntidadNegocio((CntjContratoIndicadores) getEntityManager().find(CntjContratoIndicadores.class, id));
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
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjContratoIndicadores c WHERE c.id > 0 and c.cntjContratosId.id =  ").append(idcontrato);      
            List<CntjContratoIndicadores> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (CntjContratoIndicadores indicador : list) {
                getEntityManager().remove(indicador);
            }
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public List<CntjContratoIndicador> indicadoresContrato(int idcontrato) throws Exception {
        List<CntjContratoIndicador> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjContratoIndicadores c WHERE c.id > 0 and c.cntjContratosId.id =  ").append(idcontrato);      
            List<CntjContratoIndicadores> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (CntjContratoIndicadores inidicador : list) {
                listResult.add(castEntidadNegocio(inidicador));
            }
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
    @Override
    public CntjContratoIndicador eliminar(int id) throws Exception {
        CntjContratoIndicador obj = null;
        try {
            CntjContratoIndicadores ent = getEntityManager().find(CntjContratoIndicadores.class, id);
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
   
    private CntjContratoIndicador castEntidadNegocio(CntjContratoIndicadores entidad) {
        CntjContratoIndicador objeto = new CntjContratoIndicador();
        objeto.setId(entidad.getId());        
        objeto.setCntjContratosId(new CntjContrato(entidad.getCntjContratosId().getId()));
        objeto.setTipoIndicador(entidad.getTipoIndicador());
        objeto.setDescripcion(entidad.getDescripcion());
        objeto.setMeta(entidad.getMeta());
        objeto.setAplicaDescuento(entidad.getAplicaDescuento());
        objeto.setPorcentajeDescuento(entidad.getPorcentajeDescuento());
        objeto.setValorDescuento(entidad.getValorDescuento());        
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        objeto.setUsuarioModifica(entidad.getUsuarioModifica());
        objeto.setFechaHoraModifica(entidad.getFechaHoraModifica());
        objeto.setTerminalModifica(entidad.getTerminalModifica());
        return objeto;
    }
    
    private CntjContratoIndicadores castNegocioEntidad(CntjContratoIndicador obj){
        CntjContratoIndicadores ent = new CntjContratoIndicadores();
        ent.setCntjContratosId(new CntjContratos(obj.getCntjContratosId().getId()));
        ent.setTipoIndicador(obj.getTipoIndicador());
        ent.setDescripcion(obj.getDescripcion());
        ent.setMeta(obj.getMeta());
        ent.setAplicaDescuento(obj.getAplicaDescuento());
        ent.setPorcentajeDescuento(obj.getPorcentajeDescuento());
        ent.setValorDescuento(obj.getValorDescuento());        
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }
   
    
}
