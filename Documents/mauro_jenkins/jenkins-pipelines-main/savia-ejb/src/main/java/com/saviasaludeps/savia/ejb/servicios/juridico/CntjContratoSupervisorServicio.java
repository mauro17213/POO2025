package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjContrato;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoSupervisor;
import com.saviasaludeps.savia.dominio.juridico.CntjTercero;
import com.saviasaludeps.savia.ejb.entidades.CntjContratoSupervisores;
import com.saviasaludeps.savia.ejb.entidades.CntjContratos;
import com.saviasaludeps.savia.ejb.entidades.CntjTerceros;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoSupervisorRemoto;
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
@Remote(CntjContratoSupervisorRemoto.class)
public class CntjContratoSupervisorServicio  extends GenericoServicio implements CntjContratoSupervisorRemoto{

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjContratoSupervisores c WHERE c.id > 0 ");
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
    public List<CntjContratoSupervisor> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntjContratoSupervisor> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjContratoSupervisores c WHERE c.id > 0 ");
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
            List<CntjContratoSupervisores> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjContratoSupervisores supervisor : list) {
                listResult.add(castEntidadNegocio(supervisor));
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
    public int insertar(CntjContratoSupervisor objeto) throws java.lang.Exception {
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
    public CntjContratoSupervisor consultar(int id) throws java.lang.Exception {
        CntjContratoSupervisor objRes = null;
        try {
            objRes = castEntidadNegocio((CntjContratoSupervisores) getEntityManager().find(CntjContratoSupervisores.class, id));
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
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjContratoSupervisores c WHERE c.id > 0 and c.cntjContratosId.id =  ").append(idcontrato);      
            List<CntjContratoSupervisores> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (CntjContratoSupervisores supervisor : list) {
                getEntityManager().remove(supervisor);
            }
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public List<CntjContratoSupervisor> supervisoresContrato(int idcontrato) throws Exception {
        List<CntjContratoSupervisor> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjContratoSupervisores c WHERE c.id > 0 and c.cntjContratosId.id =  ").append(idcontrato);      
            List<CntjContratoSupervisores> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (CntjContratoSupervisores supervisor : list) {
                listResult.add(castEntidadNegocio(supervisor));
            }
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
    @Override
    public CntjContratoSupervisor eliminar(int id) throws Exception {
        CntjContratoSupervisor obj = null;
        try {
            CntjContratoSupervisores ent = getEntityManager().find(CntjContratoSupervisores.class, id);
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
   
    
    private CntjContratoSupervisor castEntidadNegocio(CntjContratoSupervisores entidad) {
        CntjContratoSupervisor objeto = new CntjContratoSupervisor();
        objeto.setId(entidad.getId());
        objeto.setCntjContratosId(new CntjContrato(entidad.getCntjContratosId().getId()));
        CntjTercero supervisor = new CntjTercero(entidad.getCntjTercerosId().getId());
        supervisor.setMaeTipoDocumentoId(entidad.getCntjTercerosId().getMaeTipoDocumentoId());
        supervisor.setMaeTipoDocumentoCodigo(entidad.getCntjTercerosId().getMaeTipoDocumentoCodigo());
        supervisor.setMaeTipoDocumentoValor(entidad.getCntjTercerosId().getMaeTipoDocumentoValor());
        supervisor.setNumeroDocumento(entidad.getCntjTercerosId().getNumeroDocumento());
        supervisor.setMaeCargoValor(entidad.getCntjTercerosId().getMaeCargoValor());
        supervisor.setMaeAreaValor(entidad.getCntjTercerosId().getMaeAreaValor());
        supervisor.setCorreoElectronico(entidad.getCntjTercerosId().getCorreoElectronico());
        supervisor.setTelefonoTercero(entidad.getCntjTercerosId().getTelefonoTercero());    
        supervisor.setRazonSocial(entidad.getCntjTercerosId().getRazonSocial());
        supervisor.setTipoTercero(Short.valueOf(entidad.getCntjTercerosId().getTipoTercero()).intValue());
        objeto.setCntjTercerosId(supervisor);
        objeto.setFechaInicio(entidad.getFechaInicio());
        objeto.setFechaFin(entidad.getFechaFin());    
        objeto.setEtapaDesignacion(entidad.getEtapaDesignacion());
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        objeto.setUsuarioModifica(entidad.getUsuarioModifica());
        objeto.setFechaHoraModifica(entidad.getFechaHoraModifica());
        objeto.setTerminalModifica(entidad.getTerminalModifica());
        return objeto;
    }
    
    private CntjContratoSupervisores castNegocioEntidad(CntjContratoSupervisor obj){
        CntjContratoSupervisores ent = new CntjContratoSupervisores();
        ent.setCntjContratosId(new CntjContratos(obj.getCntjContratosId().getId()));              
        ent.setCntjTercerosId(new CntjTerceros(obj.getCntjTercerosId().getId()));
        ent.setFechaInicio(obj.getFechaInicio());
        ent.setFechaFin(obj.getFechaFin());      
        ent.setEtapaDesignacion(obj.getEtapaDesignacion());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }
   
    
}
