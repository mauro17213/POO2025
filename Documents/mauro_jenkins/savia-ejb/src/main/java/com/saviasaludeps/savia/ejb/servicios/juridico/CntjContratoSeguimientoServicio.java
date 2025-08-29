package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjContrato;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoSeguimiento;
import com.saviasaludeps.savia.dominio.juridico.CntjTercero;
import com.saviasaludeps.savia.ejb.entidades.CntjContratoSeguimientos;
import com.saviasaludeps.savia.ejb.entidades.CntjContratos;
import com.saviasaludeps.savia.ejb.entidades.CntjTerceros;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoSeguimientoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author StivenGV
 */
@Stateless
@Remote(CntjContratoSeguimientoRemoto.class)
public class CntjContratoSeguimientoServicio extends GenericoServicio implements CntjContratoSeguimientoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjContratoSeguimientos c WHERE c.id > 0 ");
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
    public List<CntjContratoSeguimiento> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CntjContratoSeguimiento> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjContratoSeguimientos c WHERE c.id > 0 ");
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
            List<CntjContratoSeguimientos> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjContratoSeguimientos seguimiento : list) {
                listResult.add(castEntidadNegocio(seguimiento));
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
    public int insertar(CntjContratoSeguimiento objeto) throws Exception {
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
    public CntjContratoSeguimiento consultar(int id) throws Exception {
        CntjContratoSeguimiento objRes = null;
        try {
            objRes = castEntidadNegocio((CntjContratoSeguimientos) getEntityManager().find(CntjContratoSeguimientos.class, id));
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
    public CntjContratoSeguimiento eliminar(int id) throws Exception {
        CntjContratoSeguimiento obj = null;
        try {
            CntjContratoSeguimientos ent = getEntityManager().find(CntjContratoSeguimientos.class, id);
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

    private CntjContratoSeguimiento castEntidadNegocio(CntjContratoSeguimientos entidad) {
        CntjContratoSeguimiento negocio = new CntjContratoSeguimiento();
        negocio.setId(entidad.getId());
        negocio.setCntjContratoId(new CntjContrato(entidad.getCntjContratosId().getId()));
        negocio.setCntjTerceroId(new CntjTercero(entidad.getCntjTercerosId().getId()));
        negocio.setPeriodicidad(entidad.getPeriodicidad());
        negocio.setFechaInicioSeguimiento(entidad.getFechaInicioSeguimiento());
        negocio.setFechaFinSeguimiento(entidad.getFechaFinSeguimiento());
        negocio.setEstadoSeguimiento(entidad.getEstadoSeguimiento());
        negocio.setObservaciones(entidad.getObservaciones());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }
    
    private CntjContratoSeguimientos castNegocioEntidad(CntjContratoSeguimiento negocio) {
        CntjContratoSeguimientos entidad = new CntjContratoSeguimientos();
        entidad.setCntjContratosId(new CntjContratos(negocio.getCntjContratoId().getId()));
        entidad.setCntjTercerosId(new CntjTerceros(negocio.getCntjTerceroId().getId()));
        entidad.setPeriodicidad(negocio.getPeriodicidad());
        entidad.setFechaInicioSeguimiento(negocio.getFechaInicioSeguimiento());
        entidad.setFechaFinSeguimiento(negocio.getFechaFinSeguimiento());
        entidad.setEstadoSeguimiento(negocio.getEstadoSeguimiento());
        entidad.setObservaciones(negocio.getObservaciones());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

    @Override
    public List<CntjContratoSeguimiento> consultarTodoPorContrato(int idContrato) throws Exception {
        List<CntjContratoSeguimiento> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjContratoSeguimientos c WHERE c.cntjContratosId.id =  "+idContrato);
            List<CntjContratoSeguimientos> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (CntjContratoSeguimientos seguimiento : list) {
                listResult.add(castEntidadNegocio(seguimiento));
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
    public void actualizar(CntjContratoSeguimiento objeto) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CntjContratoSeguimientos SET  ");
            sql.append("cntjContratosId.id = :cntjContratosId,  ");
            sql.append("cntjTercerosId.id = :cntjTercerosId,  ");
            sql.append("periodicidad = :periodicidad,  ");
            sql.append("fechaInicioSeguimiento = :fechaInicioSeguimiento,  ");
            sql.append("fechaFinSeguimiento = :fechaFinSeguimiento,  ");
            sql.append("estadoSeguimiento = :estadoSeguimiento,  ");
            sql.append("observaciones = :observaciones,  ");          
                       
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("cntjContratosId", objeto.getCntjContratoId().getId());
            query.setParameter("cntjContratosId", objeto.getCntjTerceroId().getId());
            query.setParameter("periodicidad", objeto.getPeriodicidad());
            query.setParameter("fechaInicioSeguimiento", objeto.getFechaInicioSeguimiento());
            query.setParameter("fechaFinSeguimiento", objeto.getFechaFinSeguimiento());
            query.setParameter("estadoSeguimiento", objeto.getEstadoSeguimiento());
            query.setParameter("observaciones", objeto.getObservaciones());
            
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
    
}
