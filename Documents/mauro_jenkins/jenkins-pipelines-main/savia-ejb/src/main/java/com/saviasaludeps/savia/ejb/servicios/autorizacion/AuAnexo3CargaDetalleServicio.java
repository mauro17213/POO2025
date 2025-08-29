package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Carga;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3CargaDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo3CargaDetalles;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo3Cargas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3CargaDetalleRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author oquiroza
 */
@Stateless
@Remote(AuAnexo3CargaDetalleRemoto.class)
public class AuAnexo3CargaDetalleServicio extends GenericoServicio implements AuAnexo3CargaDetalleRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(cd) FROM AuAnexo3CargaDetalles cd "
                    + "WHERE cd.id > 0 AND cd.auAnexo3CargasId.id = " + paramConsulta.getParametroConsulta3() + " ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "consecutivo":
                            strQuery += "AND cd.consecutivo = " + e.getValue() + " ";
                            break;
                        case "auAnexo3CargasId.id":
                            strQuery += "AND cd.auAnexos3Id = " + e.getValue() + " ";
                            break;
                        case "fila":
                            strQuery += "AND cd.fila = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            cantidad = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            cantidad = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cantidad;
    }

    @Override
    public List<AuAnexo3CargaDetalle> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo3CargaDetalle> listaResultados = new ArrayList();
        try {
            String strQuery = "SELECT cd FROM AuAnexo3CargaDetalles cd "
                    + "WHERE cd.id > 0 AND cd.auAnexo3CargasId.id = " + paramConsulta.getParametroConsulta3() + " ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "consecutivo":
                            strQuery += "AND cd.consecutivo = " + e.getValue() + " ";
                            break;
                        case "auAnexo3CargasId.id":
                            strQuery += "AND cd.auAnexos3Id = " + e.getValue() + " ";
                            break;
                        case "fila":
                            strQuery += "AND cd.fila = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "cd." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "cd.id ASC";
            }
            List<AuAnexo3CargaDetalles> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexo3CargaDetalles anexo3CargaDetalle : list) {
                listaResultados.add(castEntidadNegocio(anexo3CargaDetalle));
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
    public AuAnexo3CargaDetalle consultar(int id) throws Exception {
        AuAnexo3CargaDetalle objRes = null;
        try {
            objRes = castEntidadNegocio((AuAnexo3CargaDetalles) getEntityManager().find(AuAnexo3CargaDetalles.class, id));
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
    public int insertar(AuAnexo3CargaDetalle obj) throws Exception {
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
    public void actualizar(AuAnexo3CargaDetalle obj) throws Exception {
        try {
            AuAnexo3CargaDetalles per = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexo3CargaDetalles a SET ";
            strQuery += "a.id = :id ,";
            strQuery += "a.auAnexos3Id = :auAnexos3Id ,";
            strQuery += "a.numeroSolicitud = :numeroSolicitud ,";
            strQuery += "a.maeTipoDocumentoId = :maeTipoDocumentoId ,";
            strQuery += "a.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo ,";
            strQuery += "a.maeTipoDocumentoValor = :maeTipoDocumentoValor ,";
            strQuery += "a.documentoAfiliado = :documentoAfiliado ,";
            strQuery += "a.telefonoAfiliado = :telefonoAfiliado ,";
            strQuery += "a.celularAfiliado = :celularAfiliado ,";
            strQuery += "a.maServicioSolicitadoId = :maServicioSolicitadoId ,";
            strQuery += "a.maServicioSolicitadoCodigo = :maServicioSolicitadoCodigo ,";
            strQuery += "a.maServicioSolicitadoValor = :maServicioSolicitadoValor ,";
            strQuery += "a.maeCausaExternaId = :maeCausaExternaId ,";
            strQuery += "a.maeCausaExternaCodigo = :maeCausaExternaCodigo ,";
            strQuery += "a.maeCausaExternaValor = :maeCausaExternaValor ,";
            strQuery += "a.maServicioHabilitadoId = :maServicioHabilitadoId ,";
            strQuery += "a.maServicioHabilitadoCodigo = :maServicioHabilitadoCodigo ,";
            strQuery += "a.maServicioHabilitadoValor = :maServicioHabilitadoValor ,";
            strQuery += "a.fechaSolicitud = :fechaSolicitud ,";
            strQuery += "a.codigoREPS = :codigoREPS ,";
            strQuery += "a.nombreProfesional = :nombreProfesional ,";
            strQuery += "a.maeTipoDocumentoProfesionalId = :maeTipoDocumentoProfesionalId ,";
            strQuery += "a.maeTipoDocumentoProfesionalCodigo = :maeTipoDocumentoProfesionalCodigo ,";
            strQuery += "a.maeTipoDocumentoProfesionalValor = :maeTipoDocumentoProfesionalValor ,";
            strQuery += "a.documentoProfesional = :documentoProfesional ,";
            strQuery += "a.telefonoProfesional = :telefonoProfesional ,";
            strQuery += "a.maeOrigenAtencionId = :maeOrigenAtencionId ,";
            strQuery += "a.maeOrigenAtencionCodigo = :maeOrigenAtencionCodigo ,";
            strQuery += "a.maeOrigenAtencionValor = :maeOrigenAtencionValor ,";
            strQuery += "a.prioridadAtencion = :prioridadAtencion ,";
            strQuery += "a.tipoServicioSolicitado = :tipoServicioSolicitado ,";
            strQuery += "a.maeAmbitoAtencionId = :maeAmbitoAtencionId ,";
            strQuery += "a.maeAmbitoAtencionCodigo = :maeAmbitoAtencionCodigo ,";
            strQuery += "a.maeAmbitoAtencionValor = :maeAmbitoAtencionValor ,";
            strQuery += "a.esPrincipal = :esPrincipal ,";
            strQuery += "a.tipoTecnologia = :tipoTecnologia ,";
            strQuery += "a.maTecnologiaId = :maTecnologiaId ,";
            strQuery += "a.maTecnologiaCodigo = :maTecnologiaCodigo ,";
            strQuery += "a.maTecnologiaValor = :maTecnologiaValor ,";
            strQuery += "a.maMedicamentoId = :maMedicamentoId ,";
            strQuery += "a.maMedicamentoCodigo = :maMedicamentoCodigo ,";
            strQuery += "a.maMedicamentoValor = :maMedicamentoValor ,";
            strQuery += "a.cantidadSolicitada = :cantidadSolicitada ,";
            strQuery += "a.duracionTratamiento = :duracionTratamiento ,";
            strQuery += "a.dosis = :dosis ,";
            strQuery += "a.frecuencia = :frecuencia ,";
            strQuery += "a.tipoFrecuencia = :tipoFrecuencia ,";
            strQuery += "a.viaAdministracion = :viaAdministracion ,";
            strQuery += "a.justificacionClinica = :justificacionClinica ,";
            strQuery += "a.maDiagnosticoId = :maDiagnosticoId ,";
            strQuery += "a.maDiagnosticoCodigo = :maDiagnosticoCodigo ,";
            strQuery += "a.maDiagnosticoValor = :maDiagnosticoValor ,";
            strQuery += "a.maeTipoDiagnosticoId = :maeTipoDiagnosticoId ,";
            strQuery += "a.maeTipoDiagnosticoCodigo = :maeTipoDiagnosticoCodigo ,";
            strQuery += "a.maeTipoDiagnosticoValor = :maeTipoDiagnosticoValor ,";
            strQuery += "a.maeUbicacionesId = :maeUbicacionesId ,";
            strQuery += "a.maeUbicacionCodigo = :maeUbicacionCodigo ,";
            strQuery += "a.maeUbicacionValor = :maeUbicacionValor ,";
            strQuery += "a.descripcion = :descripcion ,";
            strQuery += "a.maEspecialidadId = :maEspecialidadId ,";
            strQuery += "a.maEspecialidadCodigo = :maEspecialidadCodigo ,";
            strQuery += "a.maEspecialidadValor = :maEspecialidadValor ,";
            strQuery += "a.fila = :fila ,";
            strQuery += "a.consecutivo = :consecutivo ,";
            strQuery += "a.maeTipoServicioId = :maeTipoServicioId ,";
            strQuery += "a.maeTipoServicioCodigo = :maeTipoServicioCodigo ,";
            strQuery += "a.maeTipoServicioValor = :maeTipoServicioValor ,";
            //strQuery += "a.peProgramaEspecialId = :peProgramaEspecialId ,";
            strQuery += "a.peProgramaEspecialCodigo = :peProgramaEspecialCodigo ,";
            //strQuery += "a.peProgramaEspecialDescripcion = :peProgramaEspecialDescripcion ,";
            strQuery += "a.posfechado = :posfechado ,";
            //PENDIENTE NUEVOS CAMPOS POSFECHADO
            
            //objetos
            
            //auditoria
            strQuery += "a.id = :id ";
            //Falta completar
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setProperties(per);
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AuAnexo3CargaDetalle eliminar(int id) throws Exception {
        AuAnexo3CargaDetalle obj = null;
        try {
            AuAnexo3CargaDetalles ent = getEntityManager().find(AuAnexo3CargaDetalles.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    private AuAnexo3CargaDetalle castEntidadNegocio(AuAnexo3CargaDetalles entidad) {
        AuAnexo3CargaDetalle negocio = new AuAnexo3CargaDetalle();
        negocio.setAuAnexo3CargasId(new AuAnexo3Carga(entidad.getAuAnexos3Id()));
        negocio.setAuAnexo3Id(entidad.getAuAnexos3Id());
        negocio.setConsecutivo(entidad.getConsecutivo());
        negocio.setDescripcion(entidad.getDescripcion());
        //detalle.setNumeroSolicitud();
        negocio.setMaeTipoDocumentoId(entidad.getMaeTipoDocumentoId());
        negocio.setMaeTipoDocumentoCodigo(entidad.getMaeTipoDocumentoCodigo());
        negocio.setMaeTipoDocumentoValor(entidad.getMaeTipoDocumentoValor());
        negocio.setDocumentoAfiliado(entidad.getDocumentoAfiliado());
        negocio.setTelefonoAfiliado(entidad.getTelefonoAfiliado());
        negocio.setCelularAfiliado(entidad.getCelularAfiliado());
        negocio.setMaServicioHabilitadoId(entidad.getMaServicioHabilitadoId());
        negocio.setMaServicioHabilitadoCodigo(entidad.getMaServicioHabilitadoCodigo());
        negocio.setMaServicioHabilitadoValor(entidad.getMaServicioHabilitadoValor());
        negocio.setFechaSolicitud(entidad.getFechaSolicitud());
        negocio.setCodigoREPS(entidad.getCodigoREPS());
        negocio.setNombreProfesional(entidad.getNombreProfesional());
        negocio.setMaeTipoDocumentoProfesionalId(entidad.getMaeTipoDocumentoProfesionalId());
        negocio.setMaeTipoDocumentoProfesionalCodigo(entidad.getMaeTipoDocumentoProfesionalCodigo());
        negocio.setMaeTipoDocumentoProfesionalValor(entidad.getMaeTipoDocumentoProfesionalValor());
        negocio.setDocumentoProfesional(entidad.getDocumentoProfesional());
        //negocio.setTelefonoProfesional();
        negocio.setMaeOrigenAtencionId(entidad.getMaeOrigenAtencionId());
        negocio.setMaeOrigenAtencionCodigo(entidad.getMaeOrigenAtencionCodigo());
        negocio.setMaeOrigenAtencionValor(entidad.getMaeOrigenAtencionValor());
        negocio.setPrioridadAtencion(entidad.getPrioridadAtencion());
        //negocio.setTipoServicioSolicitado();
        negocio.setMaeAmbitoAtencionId(entidad.getMaeAmbitoAtencionId());
        negocio.setMaeAmbitoAtencionCodigo(entidad.getMaeAmbitoAtencionCodigo());
        negocio.setMaeAmbitoAtencionValor(entidad.getMaeAmbitoAtencionValor());
        negocio.setEsPrincipal(entidad.getEsPrincipal());
        negocio.setCantidadSolicitada(entidad.getCantidadSolicitada());
        negocio.setFila(entidad.getFila());
        if (entidad.getDuracionTratamiento() != null) {
            negocio.setDuracionTratamiento(entidad.getDuracionTratamiento());
            negocio.setDosis(entidad.getDosis());
            negocio.setFrecuencia(entidad.getFrecuencia());
            //negocio.setTipoFrecuencia(item.getTipoFrecuencia());
            negocio.setViaAdministracion(entidad.getViaAdministracion());
        }
        negocio.setJustificacionClinica(entidad.getJustificacionClinica());
        //2023-09-19 jyperez campos nuevos
        negocio.setPeProgramaEspecialId(entidad.getPeProgramaEspecialId());
        negocio.setPeProgramaEspecialCodigo(entidad.getPeProgramaEspecialCodigo());
        negocio.setPeProgramaEspecialDescripcion(entidad.getPeProgramaEspecialDescripcion());
        negocio.setPosfechado(entidad.getPosfechado());
        negocio.setPosfechadoDuracion(entidad.getPosfechadoDuracion());
        negocio.setPosfechadoEntregas(entidad.getPosfechadoEntregas());
        negocio.setVersion((entidad.getVersion() == 1));
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }

    private AuAnexo3CargaDetalles castNegocioEntidad(AuAnexo3CargaDetalle negocio) {
        AuAnexo3CargaDetalles entidad = new AuAnexo3CargaDetalles();
        entidad.setAuAnexo3CargasId(new AuAnexo3Cargas(negocio.getAuAnexo3CargasId().getId()));
        entidad.setDescripcion(negocio.getDescripcion());
        entidad.setMaeTipoDocumentoId(negocio.getMaeTipoDocumentoId());
        entidad.setMaeTipoDocumentoCodigo(negocio.getMaeTipoDocumentoCodigo());
        entidad.setMaeTipoDocumentoValor(negocio.getMaeTipoDocumentoValor());
        entidad.setDocumentoAfiliado(negocio.getDocumentoAfiliado());
        if (negocio.getTelefonoAfiliado() != null) {
            entidad.setTelefonoAfiliado(negocio.getTelefonoAfiliado());
        }
        entidad.setCelularAfiliado(negocio.getCelularAfiliado());
        entidad.setMaServicioHabilitadoId(negocio.getMaServicioHabilitadoId());
        entidad.setMaServicioHabilitadoCodigo(negocio.getMaServicioHabilitadoCodigo());
        entidad.setMaServicioHabilitadoValor(negocio.getMaServicioHabilitadoValor());
        entidad.setFechaSolicitud(negocio.getFechaSolicitud());
        entidad.setNombreProfesional(negocio.getNombreProfesional());
        entidad.setMaeTipoDocumentoProfesionalId(negocio.getMaeTipoDocumentoProfesionalId());
        entidad.setDocumentoProfesional(negocio.getDocumentoProfesional());
        entidad.setMaeOrigenAtencionId(negocio.getMaeOrigenAtencionId());
        entidad.setMaeOrigenAtencionCodigo(negocio.getMaeOrigenAtencionCodigo());
        entidad.setMaeOrigenAtencionValor(negocio.getMaeOrigenAtencionValor());
        entidad.setPrioridadAtencion(negocio.getPrioridadAtencion());
        entidad.setMaeAmbitoAtencionId(negocio.getMaeAmbitoAtencionId());
        entidad.setMaeAmbitoAtencionCodigo(negocio.getMaeAmbitoAtencionCodigo());
        entidad.setMaeAmbitoAtencionValor(negocio.getMaeAmbitoAtencionValor());
        entidad.setEsPrincipal(negocio.getEsPrincipal());
        entidad.setCantidadSolicitada(negocio.getCantidadSolicitada());
        if (entidad.getDuracionTratamiento() != null) {
            entidad.setDuracionTratamiento(negocio.getDuracionTratamiento());
            entidad.setDosis(negocio.getDosis());
            entidad.setFrecuencia(negocio.getFrecuencia());
            //entidad.setTipoFrecuencia(item.getTipoFrecuencia());
            entidad.setViaAdministracion(negocio.getViaAdministracion());
        }
        entidad.setJustificacionClinica(negocio.getJustificacionClinica());
        //2023-09-19 jyperez campos nuevos CAMBIAR
        entidad.setPeProgramaEspecialId(negocio.getPeProgramaEspecialId());
        entidad.setPeProgramaEspecialCodigo(negocio.getPeProgramaEspecialCodigo());
        entidad.setPeProgramaEspecialDescripcion(negocio.getPeProgramaEspecialDescripcion());
        entidad.setPosfechado(negocio.getPosfechado());
        entidad.setPosfechadoDuracion(negocio.getPosfechadoDuracion());
        entidad.setPosfechadoEntregas(negocio.getPosfechadoEntregas());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }
}
