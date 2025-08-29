package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjContrato;
import com.saviasaludeps.savia.dominio.juridico.CntjOtrosi;
import com.saviasaludeps.savia.ejb.entidades.CntjContratos;
import com.saviasaludeps.savia.ejb.entidades.CntjOtrosies;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CtnjOtrosiRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author idbohorquez
 */
@Stateless
@Remote(CtnjOtrosiRemoto.class)
public class CntjOtrosiServicio extends GenericoServicio implements CtnjOtrosiRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjOtrosies c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id_contrato":
                            strQuery.append(" AND c.cntjContratosId.id = ").append(e.getValue());
                            break;
                        case "tipo":
                            strQuery.append(" AND c.tipo = ").append(e.getValue());
                            break;
                        case "plazoMeses":
                            strQuery.append(" AND c.plazoProrrogaMeses = ").append(e.getValue());
                            break;
                        case "valor":
                            strQuery.append(" AND c.valor = '").append(e.getValue()).append("' ");
                            break;
                        case "estado":
                            strQuery.append(" AND c.estado = '").append(e.getValue()).append("' ");
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
    public List<CntjOtrosi> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntjOtrosi> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjOtrosies c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id_contrato":
                            strQuery.append(" AND c.cntjContratosId.id = ").append(e.getValue());
                            break;
                        case "tipo":
                            strQuery.append(" AND c.tipo = ").append(e.getValue());
                            break;
                        case "plazoMeses":
                            strQuery.append(" AND c.plazoProrrogaMeses = ").append(e.getValue());
                            break;
                        case "valor":
                            strQuery.append(" AND c.valor = '").append(e.getValue()).append("' ");
                            break;
                        case "estado":
                            strQuery.append(" AND c.estado = '").append(e.getValue()).append("' ");
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
            List<CntjOtrosies> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjOtrosies ent : list) {
                listResult.add(castEntidadNegocio(ent));
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
    public int insertar(CntjOtrosi objeto) throws java.lang.Exception {
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
    public CntjOtrosi consultar(int id) throws java.lang.Exception {
        CntjOtrosi objRes = null;
        try {
            objRes = castEntidadNegocio((CntjOtrosies) getEntityManager().find(CntjOtrosies.class, id));
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
    public void actualizar(CntjOtrosi objeto) throws java.lang.Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CntjOtrosies SET  ");
            sql.append("tipo = :tipo,  ");
            sql.append("fechaSuscripcion = :fechaSuscripcion,  ");
            sql.append("fechaInicio = :fechaInicio,  ");
            sql.append("fechaTerminacion = :fechaTerminacion,  ");
            sql.append("plazoProrrogaMeses = :plazoProrrogaMeses,  ");
            sql.append("plazoProrrogaDias = :plazoProrrogaDias,  ");
            sql.append("justificacion = :justificacion,  ");
            sql.append("elementosAdicionales = :elementosAdicionales,  ");
            sql.append("valor = :valor,  ");
            sql.append("estado = :estado,  ");
            sql.append("estadoLegalizacion = :estadoLegalizacion,  ");
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("tipo", objeto.getTipo());
            query.setParameter("fechaSuscripcion", objeto.getFechasuscripcion());
            query.setParameter("fechaInicio", objeto.getFechaInicio());
            query.setParameter("fechaTerminacion", objeto.getFechaTerminacion());
            query.setParameter("plazoProrrogaMeses", objeto.getPlazoMeses());
            query.setParameter("plazoProrrogaDias", objeto.getPlazoDias());
            query.setParameter("justificacion", objeto.getJustificacion());
            query.setParameter("elementosAdicionales", objeto.getElementoAdicional());
            query.setParameter("valor", objeto.getValor());
            query.setParameter("estado", objeto.getEstado());
            query.setParameter("estadoLegalizacion", objeto.getEstadoLegalizacion());
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
    public CntjOtrosi ultimoNumeroOtroSi(int idcontrato) throws Exception {
        CntjOtrosi resultado = null;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjOtrosies c WHERE c.id > 0 AND c.cntjContratosId.id = :idcontrato ");
            strQuery.append(" and c.numero = ( select max(c2.numero) from CntjOtrosies c2 where c2.id > 0 and c2.cntjContratosId.id = :idcontrato ) ");
            resultado = castEntidadNegocio((CntjOtrosies) getEntityManager().createQuery(strQuery.toString())
                    .setParameter("idcontrato", idcontrato)
                    .getSingleResult());
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return resultado;
    }
    
    @Override
    public CntjOtrosi otrosiActaInicio(int idcontrato) throws Exception {
        CntjOtrosi resultado = null;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjOtrosies c WHERE c.id > 0 AND c.cntjContratosId.id = :idcontrato ");
            strQuery.append(" and c.tipo = 8 ");
            resultado = castEntidadNegocio((CntjOtrosies) getEntityManager().createQuery(strQuery.toString())
                    .setParameter("idcontrato", idcontrato)
                    .getSingleResult());
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return resultado;
    }
    
    @Override
    public CntjOtrosi eliminar(int id) throws Exception {
        CntjOtrosi obj = null;
        try {
            CntjOtrosies ent = getEntityManager().find(CntjOtrosies.class, id);
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
    public Integer otrosiVigentes(int idcontrato) throws Exception {
        Integer resultado = null;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT count(c) FROM CntjOtrosies c WHERE c.id > 0 AND c.cntjContratosId.id = :idcontrato ");
            strQuery.append(" and c.estado = 1 ");
            resultado = (int) (long) getEntityManager().createQuery(strQuery.toString())
                    .setParameter("idcontrato", idcontrato)
                    .getSingleResult();
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return resultado;
    }
    

    private CntjOtrosi castEntidadNegocio(CntjOtrosies entidad) {
        CntjOtrosi objeto = new CntjOtrosi();
        objeto.setId(entidad.getId());
        objeto.setContratoId(new CntjContrato(entidad.getCntjContratosId().getId()));
        objeto.setNumero(entidad.getNumero());
        objeto.setTipo(entidad.getTipo());
        objeto.setFechasuscripcion(entidad.getFechaSuscripcion());
        objeto.setFechaInicio(entidad.getFechaInicio());
        objeto.setFechaTerminacion(entidad.getFechaTerminacion());
        objeto.setPlazoMeses(entidad.getPlazoProrrogaMeses());
        objeto.setPlazoDias(entidad.getPlazoProrrogaDias());
        objeto.setValor(entidad.getValor());
        objeto.setJustificacion(entidad.getJustificacion());
        objeto.setElementoAdicional(entidad.getElementosAdicionales());
        objeto.setEstado(entidad.getEstado());
        objeto.setEstadoLegalizacion(entidad.getEstadoLegalizacion());
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        objeto.setUsuarioModifica(entidad.getUsuarioModifica());
        objeto.setFechaHoraModifica(entidad.getFechaHoraModifica());
        objeto.setTerminalModifica(entidad.getTerminalModifica());
        return objeto;
    }

    private CntjOtrosies castNegocioEntidad(CntjOtrosi obj) {
        CntjOtrosies ent = new CntjOtrosies();
        ent.setCntjContratosId(new CntjContratos(obj.getContratoId().getId()));
        ent.setNumero(obj.getNumero());
        ent.setTipo(obj.getTipo());
        ent.setFechaSuscripcion(obj.getFechasuscripcion());
        ent.setFechaInicio(obj.getFechaInicio());
        ent.setFechaTerminacion(obj.getFechaTerminacion());
        ent.setPlazoProrrogaMeses(obj.getPlazoMeses());
        ent.setPlazoProrrogaDias(obj.getPlazoDias());
        ent.setValor(obj.getValor());
        ent.setJustificacion(obj.getJustificacion());
        ent.setElementosAdicionales(obj.getElementoAdicional());
        ent.setEstado(obj.getEstado());
        ent.setEstadoLegalizacion(obj.getEstadoLegalizacion());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }

}
