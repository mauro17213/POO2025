/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.judicial;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.judicial.GjProceso;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoHistorico;
import com.saviasaludeps.savia.ejb.entidades.GjProcesos;
import com.saviasaludeps.savia.ejb.entidades.GjProcesoHistoricos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.judicial.GjProcesoHistoricoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jperezn
 */
@Stateless
@Remote(GjProcesoHistoricoRemoto.class)
public class GjProcesoHistoricoServicio extends GenericoServicio implements GjProcesoHistoricoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(t.id) FROM GjProcesoHistoricos t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "xxx":
                            strQuery += "AND t.xxx LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "xx":
                            strQuery += "AND t.xxx LIKE '" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
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
    public List<GjProcesoHistorico> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GjProcesoHistorico> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t FROM GjProcesoHistoricos t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "xxx":
                            strQuery += "AND t.xxx LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "xx":
                            strQuery += "AND t.xx LIKE '" + (String) e.getValue() + "%' ";
                            break;

                    }
                }
            }

            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND t.gjProcesosId = '" + (int) paramConsulta.getParametroConsulta1() + "' ";
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "t." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "t.id DESC";
            }
            List<GjProcesoHistoricos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GjProcesoHistoricos per : list) {
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
    public List<GjProcesoHistorico> consultarListaPorIdProceso(int idProceso) throws Exception {
        List<GjProcesoHistorico> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM GjProcesoHistoricos p "
                    + "WHERE p.gjProcesosId.id = " + idProceso + " ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<GjProcesoHistoricos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GjProcesoHistoricos usuarios : list) {
                listaResultados.add(castEntidadNegocio(usuarios));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }

    @Override
    public GjProcesoHistorico consultar(int id) throws Exception {
        GjProcesoHistorico objRes = null;
        try {
            GjProcesoHistoricos per = getEntityManager().find(GjProcesoHistoricos.class, id);
            if (per != null) {
                objRes = castEntidadNegocio(per);
            }
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
    public int insertar(GjProcesoHistorico obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    @Override
    public void actualizar(GjProcesoHistorico obj) throws Exception {
        try {
            String hql = "UPDATE GjProcesoHistoricos SET "
                    + " gjProcesosId.id = :gjProcesosId,"
                    + " estado = :estado,"
                    + " fechaContestacion = :fechaContestacion,"
                    + " estadoProceso = :estadoProceso,"
                    + " maeInstanciaId = :maeInstanciaId,"
                    + " maeInstanciaCodigo = :maeInstanciaCodigo,"
                    + " maeInstanciaValor = :maeInstanciaValor,"
                    + " llamamientoGarantia = :llamamientoGarantia"
                    + " maeMedicaCautelarId = :maeMedicaCautelarId"
                    + " maeMedicaCautelarCodigo = :maeMedicaCautelarCodigo"
                    + " maeMedicaCautelarValor = :maeMedicaCautelarValor"
                    + " montoMedida = :montoMedida"
                    + " probabilidad = :probabilidad"
                    + " riesgoClasificacion = :riesgoClasificacion"
                    + " claseProvision = :claseProvision"
                    + " fechaTerminacion = :fechaTerminacion"
                    + " sentidoSentencia = :sentidoSentencia"
                    + " valorSentenciaEjecutoria = :valorSentenciaEjecutoria"
                    + " estadoCumplimientoCondena = :estadoCumplimientoCondena"
                    + " maeActuacionTerminacionId = :maeActuacionTerminacionId"
                    + " maeActuacionTerminacionCodigo = :maeActuacionTerminacionCodigo"
                    + " maeActuacionTerminacionValor = :maeActuacionTerminacionValor"
                    + " valorAcuerdoTransaccion = :valorAcuerdoTransaccion"
                    + " fechaActuacion = :fechaActuacion"
                    + " actuacion = :actuacion"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);

            query.setParameter("gjProcesosId", obj.getGjProcesosId());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("fechaContestacion", obj.getFechaContestacion());
            query.setParameter("estadoProceso", obj.getEstadoProceso());
            query.setParameter("maeInstanciaId", obj.getMaeInstanciaId());
            query.setParameter("maeInstanciaCodigo", obj.getMaeInstanciaCodigo());
            query.setParameter("maeInstanciaValor", obj.getMaeInstanciaValor());
            query.setParameter("llamamientoGarantia", obj.getLlamamientoGarantia());
            query.setParameter("maeMedicaCautelarId", obj.getMaeMedicaCautelarId());
            query.setParameter("maeMedicaCautelarCodigo", obj.getMaeMedicaCautelarCodigo());
            query.setParameter("maeMedicaCautelarValor", obj.getMaeMedicaCautelarValor());
            query.setParameter("probabilidad", obj.getProbabilidad());
            query.setParameter("riesgoClasificacion", obj.getRiesgoClasificacion());
            query.setParameter("claseProvision", obj.getClaseProvision());
            query.setParameter("fechaTerminacion", obj.getFechaTerminacion());
            query.setParameter("sentidoSentencia", obj.getSentidoSentencia());
            query.setParameter("valorSentenciaEjecutoria", obj.getValorSentenciaEjecutoria());
            query.setParameter("estadoCumplimientoCondena", obj.getEstadoCumplimientoCondena());
            query.setParameter("maeActuacionTerminacionId", obj.getMaeActuacionTerminacionId());
            query.setParameter("maeActuacionTerminacionCodigo", obj.getMaeActuacionTerminacionCodigo());
            query.setParameter("maeActuacionTerminacionValor", obj.getMaeActuacionTerminacionValor());
            query.setParameter("valorAcuerdoTransaccion", obj.getValorAcuerdoTransaccion());
            query.setParameter("fechaActuacion", obj.getFechaActuacion());
            query.setParameter("actuacion", obj.getActuacion());

            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public GjProcesoHistorico eliminar(int id) throws Exception {
        GjProcesoHistorico obj = null;
        try {
            GjProcesoHistoricos ent = getEntityManager().find(GjProcesoHistoricos.class, id);
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

    public static GjProcesoHistorico castEntidadNegocio(GjProcesoHistoricos per) {
        GjProcesoHistorico obj = new GjProcesoHistorico();
        obj.setId(per.getId());
        if (per.getGjProcesosId() != null) {
            GjProceso proces = new GjProceso();
            proces.setId(per.getGjProcesosId().getId());
            proces.setRadicado(per.getGjProcesosId().getRadicado());

            obj.setGjProcesosId(proces);
        }
        obj.setEstado(per.getEstado());
        obj.setFechaContestacion(per.getFechaContestacion());
        obj.setEstadoProceso(per.getEstadoProceso());
        obj.setMaeInstanciaId(per.getMaeInstanciaId());
        obj.setMaeInstanciaCodigo(per.getMaeInstanciaValor());
        obj.setMaeInstanciaValor(per.getMaeInstanciaValor());
        obj.setLlamamientoGarantia(per.getLlamamientoGarantia());
        obj.setMaeMedicaCautelarId(per.getMaeMedicaCautelarId());
        obj.setMaeMedicaCautelarCodigo(per.getMaeMedicaCautelarCodigo());
        obj.setMaeMedicaCautelarValor(per.getMaeMedicaCautelarValor());
        obj.setMontoMedida(per.getMontoMedida());
        obj.setProbabilidad(per.getProbabilidad());
        obj.setRiesgoClasificacion(per.getRiesgoClasificacion());
        obj.setClaseProvision(per.getClaseProvision());
        obj.setFechaTerminacion(per.getFechaTerminacion());
        obj.setSentidoSentencia(per.getSentidoSentencia());
        obj.setValorSentenciaEjecutoria(per.getValorSentenciaEjecutoria());
        obj.setEstadoCumplimientoCondena(per.getEstadoCumplimientoCondena());
        obj.setMaeActuacionTerminacionId(per.getMaeActuacionTerminacionId());
        obj.setMaeActuacionTerminacionCodigo(per.getMaeActuacionTerminacionCodigo());
        obj.setMaeActuacionTerminacionValor(per.getMaeActuacionTerminacionValor());
        obj.setValorAcuerdoTransaccion(per.getValorAcuerdoTransaccion());
        obj.setFechaActuacion(per.getFechaActuacion());
        obj.setActuacion(per.getActuacion());
        //Auditoría
        obj.setUsuarioCrea(per.getUsuariosCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static GjProcesoHistoricos castNegocioEntidad(GjProcesoHistorico obj) {
        GjProcesoHistoricos per = new GjProcesoHistoricos();
        per.setId(per.getId());
        if (obj.getGjProcesosId() != null) {
            per.setGjProcesosId(new GjProcesos(obj.getGjProcesosId().getId()));
        }
        per.setEstado(obj.getGjProcesosId().getEstado());
        per.setFechaContestacion(obj.getGjProcesosId().getFechaContestacion());
        per.setEstadoProceso(obj.getGjProcesosId().getEstadoProceso());
        per.setMaeInstanciaId(obj.getGjProcesosId().getMaeInstanciaId());
        per.setMaeInstanciaCodigo(obj.getGjProcesosId().getMaeInstanciaValor());
        per.setMaeInstanciaValor(obj.getGjProcesosId().getMaeInstanciaValor());
        per.setLlamamientoGarantia(obj.getGjProcesosId().getLlamamientoGarantia());
        per.setMaeMedicaCautelarId(obj.getGjProcesosId().getMaeMedicaCautelarId());
        per.setMaeMedicaCautelarCodigo(obj.getGjProcesosId().getMaeMedicaCautelarCodigo());
        per.setMaeMedicaCautelarValor(obj.getGjProcesosId().getMaeMedicaCautelarValor());
        per.setMontoMedida(obj.getGjProcesosId().getMontoMedida());
        per.setProbabilidad(obj.getGjProcesosId().getProbabilidad());
        per.setRiesgoClasificacion(obj.getGjProcesosId().getRiesgoClasificacion());
        per.setClaseProvision(obj.getGjProcesosId().getClaseProvision());
        per.setFechaTerminacion(obj.getGjProcesosId().getFechaTerminacion());
        per.setSentidoSentencia(obj.getGjProcesosId().getSentidoSentencia());
        per.setValorSentenciaEjecutoria(obj.getGjProcesosId().getValorSentenciaEjecutoria());
        per.setEstadoCumplimientoCondena(obj.getGjProcesosId().getEstadoCumplimientoCondena());
        per.setMaeActuacionTerminacionId(obj.getGjProcesosId().getMaeActuacionTerminacionId());
        per.setMaeActuacionTerminacionCodigo(obj.getGjProcesosId().getMaeActuacionTerminacionCodigo());
        per.setMaeActuacionTerminacionValor(obj.getGjProcesosId().getMaeActuacionTerminacionValor());
        per.setValorAcuerdoTransaccion(obj.getGjProcesosId().getValorAcuerdoTransaccion());
        per.setFechaActuacion(obj.getGjProcesosId().getFechaUltimaActuacion());
        per.setActuacion(obj.getGjProcesosId().getUltimaActuacion());
        //Auditoría      

        if (obj.getGjProcesosId().getUsuarioCrea() != null && obj.getGjProcesosId().getUsuarioModifica() != null) {
            per.setUsuariosCrea(obj.getGjProcesosId().getUsuarioModifica());
            per.setTerminalCrea(obj.getGjProcesosId().getTerminalModifica());
            per.setFechaHoraCrea(obj.getGjProcesosId().getFechaHoraModifica());
        } else if (obj.getGjProcesosId().getUsuarioCrea() != null & obj.getGjProcesosId().getUsuarioModifica() == null) {
            per.setUsuariosCrea(obj.getGjProcesosId().getUsuarioCrea());
            per.setTerminalCrea(obj.getGjProcesosId().getTerminalCrea());
            per.setFechaHoraCrea(obj.getGjProcesosId().getFechaHoraCrea());
        }

        return per;
    }
}
