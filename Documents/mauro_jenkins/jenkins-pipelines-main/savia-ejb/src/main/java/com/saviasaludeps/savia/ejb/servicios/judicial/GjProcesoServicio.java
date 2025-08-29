/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.judicial;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.judicial.GjAbogado;
import com.saviasaludeps.savia.dominio.judicial.GjProceso;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoAbogado;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoAdjunto;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoPretencion;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoTercero;
import com.saviasaludeps.savia.dominio.judicial.GjTercero;
import com.saviasaludeps.savia.dominio.tutela.TuJuzgado;
import com.saviasaludeps.savia.ejb.entidades.GjProcesoAbogados;
import com.saviasaludeps.savia.ejb.entidades.GjProcesoAdjuntos;
import com.saviasaludeps.savia.ejb.entidades.GjProcesoPretenciones;
import com.saviasaludeps.savia.ejb.entidades.GjProcesoTerceros;
import com.saviasaludeps.savia.ejb.entidades.GjProcesos;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.entidades.TuJuzgados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.judicial.GjProcesoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author bsgomez
 */
@Stateless
@Remote(GjProcesoRemoto.class)
public class GjProcesoServicio extends GenericoServicio implements GjProcesoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(DISTINCT gj_p) FROM GjProcesos gj_p "                    
                    + "WHERE gj_p.id > 0 ";
            strQuery += "AND gj_p.borrado != 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "fechaRadicado":
                            strQuery += "AND gj_p.fechaRadicado = '" + (String) e.getValue() + "' ";
                            break;
                        case "radicado":
                            strQuery += "AND gj_p.radicado LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "estadoProceso":
                            strQuery += "AND gj_p.estadoProceso = '" + (String) e.getValue() + "' ";
                            break;
                        case "estado":
                            strQuery += "AND gj_p.estado = '" + (String) e.getValue() + "' ";
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
    public List<GjProceso> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GjProceso> listResult = new ArrayList();
        try {
            String strQuery = "SELECT DISTINCT gj_p FROM GjProcesos gj_p "                    
                    + "WHERE gj_p.id > 0 ";
            strQuery += "AND gj_p.borrado != 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "radicado":
                            strQuery += "AND gj_p.radicado LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaRadicado":
                            strQuery += "AND gj_p.fechaRadicado = '" + (String) e.getValue() + "' ";
                            break;
                        case "estadoProceso":
                            strQuery += "AND gj_p.estadoProceso = '" + (String) e.getValue() + "' ";
                            break;
                        case "estado":
                            strQuery += "AND gj_p.estado = '" + (String) e.getValue() + "' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "gj_p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "gj_p.id DESC";
            }
            List<GjProcesos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GjProcesos per : list) {
                listResult.add(castEntidadNegocioCorto(per));
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
    public GjProceso consultarRadicado(GjProceso obj) throws Exception {
        GjProceso proces = new GjProceso();
        try {
            String strQuery = "FROM GjProcesos p "
                    + "WHERE";
            strQuery += " p.radicado = '" + obj.getRadicado() + "' ";

            List<GjProcesos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GjProcesos per : list) {
                proces = castEntidadNegocioCorto(per);
            }
        } catch (NoResultException e) {
            proces = new GjProceso();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return proces;
    }

    @Override
    public List<GjProceso> consultarListaPorIdJuzgado(int idJuzgado) throws Exception {
        List<GjProceso> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM GjProcesos  p "
                    + "WHERE p.tuJuzgadosId.id = " + idJuzgado + " ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<GjProcesos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GjProcesos usuarios : list) {
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
    public GjProceso consultar(int id) throws Exception {
        GjProceso objRes = null;
        try {
            GjProcesos per = getEntityManager().find(GjProcesos.class, id);
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
    public List<GjProceso> consultarProces(ParamConsulta paramConsulta) throws Exception {
        List<GjProceso> listResult = new ArrayList();
        try {
            String strQuery = "SELECT DISTINCT gj_p FROM GjProcesos gj_p "
                    + " LEFT JOIN GjProcesoTerceros gj_pt ON gj_pt.gjProcesosId.id = gj_p.id "
                    + " LEFT JOIN GjTerceros gj_t ON gj_t.id = gj_pt.gjTercerosId.id "
                    + " LEFT JOIN GjProcesoAbogados gj_pa ON gj_pa.id = gj_p.id "
                    + " LEFT JOIN GjAbogados gj_a ON gj_a.id = gj_pa.gjAbogadosId.id "
                    + "WHERE gj_p.id = id ";

            List<GjProcesos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();

            for (GjProcesos per : list) {
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
    public int insertar(GjProceso obj) throws Exception {
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
    public void actualizar(GjProceso obj) throws Exception {
        try {
            String hql = "UPDATE GjProcesos SET "
                    + "radicado = :radicado, "
                    + "fechaRadicado = :fechaRadicado, "
                    + "fechaAdmision = :fechaAdmision, "
                    + "fechaNotificacion = :fechaNotificacion, "
                    + "maeJurisdiccionId = :maeJurisdiccionId, "
                    + "maeJurisdiccionCodigo = :maeJurisdiccionCodigo, "
                    + "maeJurisdiccionValor = :maeJurisdiccionValor, "
                    + "maeClaseId = :maeClaseId, "
                    + "maeClaseCodigo = :maeClaseCodigo, "
                    + "maeClaseValor = :maeClaseValor, "
                    + "maeClaseDescripcionId = :maeClaseDescripcionId, "
                    + "maeClaseDescripcionCodigo = :maeClaseDescripcionCodigo, "
                    + "maeClaseDescripcionValor = :maeClaseDescripcionValor, "
                    + "gnUbicacionesProcesoId.id = :gnUbicaionesProcesoId, "
                    + "tuJuzgadosId.id = :tuJuzgadosId, "
                    + "pretencionDescripcion = :pretencionDescripcion, "
                    + "cuantia = :cuantia, "
                    + "cuantiaObjetiva = :cuantiaObjetiva, "
                    + "estado = :estado, "
                    + "ultimaActuacion = :ultimaActuacion, "
                    + "estadoProceso = :estadoProceso, "
                    + "maeInstanciaId = :maeInstanciaId, "
                    + "maeInstanciaCodigo = :maeInstanciaCodigo, "
                    + "maeInstanciaValor = :maeInstanciaValor, "
                    + "llamamientoGarantia = :llamamientoGarantia, "
                    + "maeMedicaCautelarId = :maeMedicaCautelarId, "
                    + "maeMedicaCautelarCodigo = :maeMedicaCautelarCodigo, "
                    + "maeMedicaCautelarValor = :maeMedicaCautelarValor, "
                    + "montoMedida = :montoMedida, "
                    + "probabilidad = :probabilidad, "
                    + "riesgoClasificacion = :riesgoClasificacion, "
                    + "claseProvision = :claseProvision, "
                    + "fechaTerminacion = :fechaTerminacion, "
                    + "sentidoSentencia = :sentidoSentencia, "
                    + "valorSentenciaEjecutoria = :valorSentenciaEjecutoria, "
                    + "valorRiesgoCondena = :valorRiesgoCondena, "
                    + "estadoCumplimientoCondena = :estadoCumplimientoCondena, "
                    + "maeActuacionTerminacionId = :maeActuacionTerminacionId, "
                    + "maeActuacionTerminacionCodigo = :maeActuacionTerminacionCodigo, "
                    + "maeActuacionTerminacionValor = :maeActuacionTerminacionValor, "
                    + "valorAcuerdoTransaccion = :valorAcuerdoTransaccion, "
                    + "fechaUltimaActuacion = :fechaUltimaActuacion, "
                    + "ultimaActuacion = :ultimaActuacion, "
                    + "fechaContestacion = :fechaContestacion, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica ";
            hql += "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);

            query.setParameter("radicado", obj.getRadicado());
            query.setParameter("fechaRadicado", obj.getFechaRadicado());
            query.setParameter("fechaAdmision", obj.getFechaAdmision());
            query.setParameter("fechaNotificacion", obj.getFechaNotificacion());
            query.setParameter("maeJurisdiccionId", obj.getMaeJurisdiccionId());
            query.setParameter("maeJurisdiccionCodigo", obj.getMaeJurisdiccionCodigo());
            query.setParameter("maeJurisdiccionValor", obj.getMaeJurisdiccionValor());
            query.setParameter("maeClaseId", obj.getMaeClaseId());
            query.setParameter("maeClaseCodigo", obj.getMaeClaseCodigo());
            query.setParameter("maeClaseValor", obj.getMaeClaseValor());
            query.setParameter("maeClaseDescripcionId", obj.getMaeClaseDescripcionId());
            query.setParameter("maeClaseDescripcionCodigo", obj.getMaeClaseDescripcionCodigo());
            query.setParameter("maeClaseDescripcionValor", obj.getMaeClaseDescripcionValor());
            query.setParameter("gnUbicaionesProcesoId", obj.getGnUbicacionesProcesoId().getId());
            query.setParameter("tuJuzgadosId", obj.getTuJuzgadosId().getId());
            query.setParameter("pretencionDescripcion", obj.getPretencionDescripcion());
            query.setParameter("cuantia", obj.getCuantia());
            query.setParameter("cuantiaObjetiva", obj.getCuantiaObjetiva());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("ultimaActuacion", obj.getUltimaActuacion());
            query.setParameter("estadoProceso", obj.getEstadoProceso());
            query.setParameter("maeInstanciaId", obj.getMaeInstanciaId());
            query.setParameter("maeInstanciaCodigo", obj.getMaeInstanciaCodigo());
            query.setParameter("maeInstanciaValor", obj.getMaeInstanciaValor());
            query.setParameter("llamamientoGarantia", obj.getLlamamientoGarantia());
            query.setParameter("maeMedicaCautelarId", obj.getMaeMedicaCautelarId());
            query.setParameter("maeMedicaCautelarCodigo", obj.getMaeMedicaCautelarCodigo());
            query.setParameter("maeMedicaCautelarValor", obj.getMaeMedicaCautelarValor());
            query.setParameter("montoMedida", obj.getMontoMedida());
            query.setParameter("probabilidad", obj.getProbabilidad());
            query.setParameter("riesgoClasificacion", obj.getRiesgoClasificacion());
            query.setParameter("claseProvision", obj.getClaseProvision());
            query.setParameter("fechaTerminacion", obj.getFechaTerminacion());
            query.setParameter("sentidoSentencia", obj.getSentidoSentencia());
            query.setParameter("valorSentenciaEjecutoria", obj.getValorSentenciaEjecutoria());
            query.setParameter("valorRiesgoCondena", obj.getValorRiesgoCondena());
            query.setParameter("estadoCumplimientoCondena", obj.getEstadoCumplimientoCondena());
            query.setParameter("maeActuacionTerminacionId", obj.getMaeActuacionTerminacionId());
            query.setParameter("maeActuacionTerminacionCodigo", obj.getMaeActuacionTerminacionCodigo());
            query.setParameter("maeActuacionTerminacionValor", obj.getMaeActuacionTerminacionValor());
            query.setParameter("valorAcuerdoTransaccion", obj.getValorAcuerdoTransaccion());
            query.setParameter("fechaUltimaActuacion", obj.getFechaUltimaActuacion());
            query.setParameter("ultimaActuacion", obj.getUltimaActuacion());
            query.setParameter("fechaContestacion", obj.getFechaContestacion());

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
    public void borradoActualizar(GjProceso obj) throws Exception {
        try {
            String hql = "UPDATE GjProcesos SET "
                    + "borrado = :borrado, "
                    + "usuarioBorra = :usuarioBorra, "
                    + "terminalBorra = :terminalBorra, "
                    + "fechaHoraBorra = :fechaHoraBorra ";
            hql += "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);

            query.setParameter("borrado", obj.getBorrado());
            query.setParameter("usuarioBorra", obj.getUsuarioBorra());
            query.setParameter("terminalBorra", obj.getTerminalBorra());
            query.setParameter("fechaHoraBorra", obj.getFechaHoraBorra());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public GjProceso eliminar(int id) throws Exception {
        GjProceso obj = null;
        try {
            GjProcesos ent = getEntityManager().find(GjProcesos.class, id);
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

    public static GjProceso castEntidadNegocio(GjProcesos per) {
        GjProceso obj = new GjProceso();

        obj.setId(per.getId());
        obj.setRadicado(per.getRadicado());
        obj.setFechaRadicado(per.getFechaRadicado());
        obj.setFechaAdmision(per.getFechaAdmision());

        if (per.getTuJuzgadosId() != null) {
            TuJuzgado juzgado = new TuJuzgado();
            juzgado.setId(per.getTuJuzgadosId().getId());
            juzgado.setNombre(per.getTuJuzgadosId().getNombre());
            obj.setTuJuzgadosId(juzgado);
        }

        if (per.getGnUbicacionesProcesoId() != null) {
            Ubicacion ubica = new Ubicacion();
            ubica.setId(per.getGnUbicacionesProcesoId().getId());
            ubica.setNombre(per.getGnUbicacionesProcesoId().getNombre());
            ubica.setNombre(per.getGnUbicacionesProcesoId().getMaeRegionValor());
            obj.setGnUbicacionesProcesoId(ubica);
        }

        obj.setGjProcesoAbogado(new GjProcesoAbogado());
        if (per.getGjProcesoAbogadosList() != null) {
            int contador = 0;
            for (GjProcesoAbogados pa : per.getGjProcesoAbogadosList()) {
                if (contador != per.getGjProcesoAbogadosList().size()) {
                    //LLenar la informacion a mostrar abogado                 
                    GjAbogado abogado = new GjAbogado();
                    abogado.setId(pa.getGjAbogadosId().getId());
                    abogado.setMaeTipoDocumentoValor(pa.getGjAbogadosId().getMaeTipoDocumentoValor());
                    abogado.setDocumento(pa.getGjAbogadosId().getDocumento());
                    abogado.setNombre(pa.getGjAbogadosId().getNombre());
                    abogado.setTipo(pa.getGjAbogadosId().getTipo());
                    abogado.setTarjetaProfecional(pa.getGjAbogadosId().getTarjetaProfecional());
                    //LLenado abogado
                    obj.getGjProcesoAbogado().setGjAbogadosId(abogado);
                    //informacion del proceso abogado  
                    obj.getGjProcesoAbogado().setId(pa.getId());
                    obj.getGjProcesoAbogado().setFechaInicio(pa.getFechaInicio());
                    obj.getGjProcesoAbogado().setFechaFin(pa.getFechaFin());
                    obj.getGjProcesoAbogado().setActivo(pa.getActivo());
                }
            }
        }

        obj.setGjProcesoAdjunto(new GjProcesoAdjunto());
        if (per.getGjProcesoAdjuntosList() != null) {
            int contador = 0;
            for (GjProcesoAdjuntos pa : per.getGjProcesoAdjuntosList()) {
                if (contador != per.getGjProcesoAdjuntosList().size()) {
                    //LLenado                  
                    obj.getGjProcesoAdjunto().setId(pa.getId());
                    obj.getGjProcesoAdjunto().setMaeTipoValor(pa.getMaeTipoValor());
                    obj.getGjProcesoAdjunto().setNombreArchivo(pa.getNombreArchivo());
                    obj.getGjProcesoAdjunto().setFechaHoraCrea(pa.getFechaHoraCrea());
                }
            }
        }

        obj.setGjProcesoPretencion(new GjProcesoPretencion());
        if (per.getGjProcesoPretencionesList() != null) {
            int contador = 0;
            for (GjProcesoPretenciones pp : per.getGjProcesoPretencionesList()) {
                if (contador != per.getGjProcesoPretencionesList().size()) {
                    //LLenado                  
                    obj.getGjProcesoPretencion().setId(pp.getId());
                    obj.getGjProcesoPretencion().setMaePretencionId(pp.getMaePretencionId());
                    obj.getGjProcesoPretencion().setMaePretencionCodigo(pp.getMaePretencionCodigo());
                    obj.getGjProcesoPretencion().setMaePretencionValor(pp.getMaePretencionValor());
                }
            }
        }
        obj.setFechaNotificacion(per.getFechaNotificacion());
        obj.setMaeJurisdiccionId(per.getMaeJurisdiccionId());
        obj.setMaeJurisdiccionCodigo(per.getMaeJurisdiccionCodigo());
        obj.setMaeJurisdiccionValor(per.getMaeJurisdiccionValor());
        obj.setMaeClaseId(per.getMaeClaseId());
        obj.setMaeClaseCodigo(per.getMaeClaseCodigo());
        obj.setMaeClaseValor(per.getMaeClaseValor());
        obj.setMaeClaseDescripcionId(per.getMaeClaseDescripcionId());
        obj.setMaeClaseDescripcionCodigo(per.getMaeClaseDescripcionCodigo());
        obj.setMaeClaseDescripcionValor(per.getMaeClaseDescripcionValor());
        obj.setPretencionDescripcion(per.getPretencionDescripcion());
        obj.setCuantia(per.getCuantia());
        obj.setCuantiaObjetiva(per.getCuantiaObjetiva());
        obj.setEstado(per.getEstado());
        obj.setEstadoProceso(per.getEstadoProceso());
        obj.setMaeInstanciaId(per.getMaeInstanciaId());
        obj.setMaeInstanciaCodigo(per.getMaeInstanciaCodigo());
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
        obj.setFechaUltimaActuacion(per.getFechaUltimaActuacion());
        if (per.getUltimaActuacion() != null) {
            obj.setUltimaActuacionStr(new String(per.getUltimaActuacion()));
        }
        obj.setFechaContestacion(per.getFechaContestacion());
        obj.setValorRiesgoCondena(per.getValorRiesgoCondena());

        //Auditoría
        obj.setUsuarioCrea(per.getUsuariosCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        return obj;
    }

    public static GjProceso castEntidadNegocioCorto(GjProcesos per) {
        GjProceso obj = new GjProceso();

        obj.setId(per.getId());
        obj.setRadicado(per.getRadicado());
        obj.setFechaRadicado(per.getFechaRadicado());
        obj.setFechaTerminacion(per.getFechaTerminacion());
        obj.setEstado(per.getEstado());
        obj.setEstadoProceso(per.getEstadoProceso());
        if (per.getTuJuzgadosId() != null) {
            TuJuzgado juzgado = new TuJuzgado();
            juzgado.setId(per.getTuJuzgadosId().getId());
            juzgado.setNombre(per.getTuJuzgadosId().getNombre());
            obj.setTuJuzgadosId(juzgado);
        }
        obj.setGjProcesoTercero(new GjProcesoTercero());
        if (per.getGjProcesoTercerosList() != null) {
            int contador = 0;
            for (GjProcesoTerceros pt : per.getGjProcesoTercerosList()) {
                if (contador != per.getGjProcesoTercerosList().size()) {
                    //LLenar la informacion a mostrar tercero                 
                    GjTercero tercero = new GjTercero();
                    tercero.setTipo(pt.getGjTercerosId().getTipo());
                    tercero.setMaeTipoDocumentoValor(pt.getGjTercerosId().getMaeTipoDocumentoValor());
                    tercero.setDocumento(pt.getGjTercerosId().getDocumento());
                    tercero.setNombres(pt.getGjTercerosId().getNombres());
                    tercero.setApellidos(pt.getGjTercerosId().getApellidos());
                    //LLenado tercero
                    obj.getGjProcesoTercero().setGjTercerosId(tercero);
                    //Informacion del proceso tercero
                }
            }
        }
        //Auditoría
        obj.setUsuarioCrea(per.getUsuariosCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        return obj;
    }

    public static GjProcesos castNegocioEntidad(GjProceso obj) {
        GjProcesos per = new GjProcesos();
        per.setId(obj.getId());
        if (obj.getTuJuzgadosId() != null) {
            per.setTuJuzgadosId(new TuJuzgados(obj.getTuJuzgadosId().getId()));
        }
        per.setRadicado(obj.getRadicado());
        per.setFechaRadicado(obj.getFechaRadicado());
        per.setFechaAdmision(obj.getFechaAdmision());
        per.setFechaNotificacion(obj.getFechaNotificacion());
        per.setMaeJurisdiccionId(obj.getMaeJurisdiccionId());
        per.setMaeJurisdiccionCodigo(obj.getMaeJurisdiccionCodigo());
        per.setMaeJurisdiccionValor(obj.getMaeJurisdiccionValor());
        per.setMaeClaseId(obj.getMaeClaseId());
        per.setMaeClaseCodigo(obj.getMaeClaseCodigo());
        per.setMaeClaseValor(obj.getMaeClaseValor());
        per.setMaeClaseDescripcionId(obj.getMaeClaseDescripcionId());
        per.setMaeClaseDescripcionCodigo(obj.getMaeClaseDescripcionCodigo());
        per.setMaeClaseDescripcionValor(obj.getMaeClaseDescripcionValor());
        if (obj.getGnUbicacionesProcesoId() != null) {
            per.setGnUbicacionesProcesoId(new GnUbicaciones(obj.getGnUbicacionesProcesoId().getId()));
        }
        per.setCuantia(obj.getCuantia());
        per.setCuantiaObjetiva(obj.getCuantiaObjetiva());
        per.setEstado(obj.getEstado());
        per.setEstadoProceso(obj.getEstadoProceso());
        per.setMaeInstanciaId(obj.getMaeInstanciaId());
        per.setMaeInstanciaCodigo(obj.getMaeInstanciaCodigo());
        per.setMaeInstanciaValor(obj.getMaeInstanciaValor());
        per.setLlamamientoGarantia(obj.getLlamamientoGarantia());
        per.setMaeMedicaCautelarId(obj.getMaeMedicaCautelarId());
        per.setMaeMedicaCautelarCodigo(obj.getMaeMedicaCautelarCodigo());
        per.setMaeMedicaCautelarValor(obj.getMaeMedicaCautelarValor());
        per.setMontoMedida(obj.getMontoMedida());
        per.setProbabilidad(obj.getProbabilidad());
        per.setRiesgoClasificacion(obj.getRiesgoClasificacion());
        per.setClaseProvision(obj.getClaseProvision());
        per.setFechaTerminacion(obj.getFechaTerminacion());
        per.setSentidoSentencia(obj.getSentidoSentencia());
        per.setValorSentenciaEjecutoria(obj.getValorSentenciaEjecutoria());
        per.setEstadoCumplimientoCondena(obj.getEstadoCumplimientoCondena());
        per.setMaeActuacionTerminacionId(obj.getMaeActuacionTerminacionId());
        per.setMaeActuacionTerminacionCodigo(obj.getMaeActuacionTerminacionCodigo());
        per.setMaeActuacionTerminacionValor(obj.getMaeActuacionTerminacionValor());
        per.setValorAcuerdoTransaccion(obj.getValorAcuerdoTransaccion());
        per.setFechaUltimaActuacion(obj.getFechaUltimaActuacion());
        per.setUltimaActuacion(obj.getUltimaActuacion());
        per.setFechaContestacion(obj.getFechaContestacion());
        per.setPretencionDescripcion(obj.getPretencionDescripcion());
        per.setValorRiesgoCondena(obj.getValorRiesgoCondena());
        //Auditoría
        per.setUsuariosCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        return per;
    }
}
