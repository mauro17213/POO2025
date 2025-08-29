package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.juridico.CntjContrato;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoInforme;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoSupervisor;
import com.saviasaludeps.savia.dominio.juridico.CntjTercero;
import com.saviasaludeps.savia.ejb.entidades.CntjContratoInformes;
import com.saviasaludeps.savia.ejb.entidades.CntjContratoSupervisores;
import com.saviasaludeps.savia.ejb.entidades.CntjContratos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoInformeRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author StivenGV
 */
@Stateless
@Remote(CntjContratoInformeRemoto.class)
public class CntjContratoInformeServicio extends GenericoServicio implements CntjContratoInformeRemoto {

    @Override
    public int insertar(CntjContratoInforme objeto) throws Exception {
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
    public void actualizar(CntjContratoInforme objeto) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CntjContratoInformes SET fechaInicioPeriodo = :fechaInicioPeriodo, ");
            sql.append("fechaFinPeriodo = :fechaFinPeriodo,  ");
            sql.append("tipoInforme = :tipoInforme,  ");
            sql.append("fechaAprobacion = :fechaAprobacion,  ");
            sql.append("observaciones = :observaciones,  ");
            sql.append("estado = :estado,  "); 
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("fechaInicioPeriodo", objeto.getFechaInicioPeriodo());
            query.setParameter("fechaFinPeriodo", objeto.getFechaFinPeriodo());
            query.setParameter("tipoInforme", objeto.getTipoInforme());
            query.setParameter("fechaAprobacion", objeto.getFechaAprobacion());
            query.setParameter("observaciones", objeto.getObservaciones());
            query.setParameter("estado", objeto.getEstado());
            query.setParameter("usuarioModifica", objeto.getUsuarioModifica());
            query.setParameter("terminalModifica", objeto.getTerminalModifica());
            query.setParameter("fechaHoraModifica", objeto.getFechaHoraModifica());
            query.setParameter("id", objeto.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CntjContratoInforme eliminar(int id) throws Exception {
        CntjContratoInforme obj = null;
        try {
            CntjContratoInformes ent = getEntityManager().find(CntjContratoInformes.class, id);
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
    public List<CntjContratoInforme> consultarTodosPorIdContrato(int idContrato) throws Exception {
        List<CntjContratoInforme> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjContratoInformes c WHERE c.cntjContratosId.id =  "+idContrato + " order by c.id desc ");
            List<CntjContratoInformes> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            if (list != null) {
                for (CntjContratoInformes informe : list) {
                    listResult.add(castEntidadNegocio(informe));
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
    public CntjContratoInforme consultar(int id) throws java.lang.Exception {
        CntjContratoInforme objRes = null;
        try {
            objRes = castEntidadNegocio((CntjContratoInformes) getEntityManager().find(CntjContratoInformes.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }
    
    private CntjContratoInformes castNegocioEntidad(CntjContratoInforme negocio){
        CntjContratoInformes entidad = new CntjContratoInformes();
        entidad.setCntjContratosId(new CntjContratos(negocio.getCntjContratoId().getId()));
        entidad.setCntjContratoSupervisoresId(new CntjContratoSupervisores(negocio.getCntjContratoSupervisorId().getId()));
        entidad.setFechaInicioPeriodo(negocio.getFechaInicioPeriodo());
        entidad.setFechaFinPeriodo(negocio.getFechaFinPeriodo());
        entidad.setTipoInforme(negocio.getTipoInforme());
        entidad.setFechaAprobacion(negocio.getFechaAprobacion());
        entidad.setObservaciones(negocio.getObservaciones());
        entidad.setEstado(negocio.getEstado());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        return entidad;
    }
    
    private CntjContratoInforme castEntidadNegocio(CntjContratoInformes entidad){
        CntjContratoInforme negocio = new CntjContratoInforme();
        negocio.setId(entidad.getId());
        negocio.setCntjContratoId(new CntjContrato(entidad.getCntjContratosId().getId()));        
        CntjTercero tercero = new CntjTercero(entidad.getCntjContratoSupervisoresId().getCntjTercerosId().getId());
        tercero.setRazonSocial(entidad.getCntjContratoSupervisoresId().getCntjTercerosId().getRazonSocial());
        CntjContratoSupervisor supervisor = new CntjContratoSupervisor(entidad.getCntjContratoSupervisoresId().getId());
        supervisor.setCntjTercerosId(tercero);
        negocio.setCntjContratoSupervisorId(supervisor);
        
        negocio.setFechaInicioPeriodo(entidad.getFechaInicioPeriodo());
        negocio.setFechaFinPeriodo(entidad.getFechaFinPeriodo());
        negocio.setTipoInforme(entidad.getTipoInforme());
        negocio.setFechaAprobacion(entidad.getFechaAprobacion());
        negocio.setObservaciones(entidad.getObservaciones());
        negocio.setEstado(entidad.getEstado());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }
    
}
