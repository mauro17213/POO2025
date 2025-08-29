/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.atencionusuario;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCaso;
import com.saviasaludeps.savia.dominio.atencionusuario.AusServicioGestionHistorico;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCasoServicio;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersona;
import com.saviasaludeps.savia.dominio.atencionusuario.AusServicioGestion;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.AusCasoServicios;
import com.saviasaludeps.savia.ejb.entidades.AusCasos;
import com.saviasaludeps.savia.ejb.entidades.AusServicioGestiones;
import com.saviasaludeps.savia.ejb.servicios.administracion.UsuarioServicio;
import com.saviasaludeps.savia.negocio.atencionusuario.AusServicioRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author pavacca
 */
@Stateless
@Remote(AusServicioRemoto.class)
@Local(AusServicioLocal.class)
public class AusServicioServicio extends GenericoServicio implements AusServicioLocal, AusServicioRemoto {
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(s) FROM AusCasoServicios s "
                    + "INNER JOIN AusCasos ac ON s.ausCasosId = ac.id "
                    + "WHERE 1 = 1 "
                    + "AND ac.borrado = 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeServicioAmbito":
                            strQuery += "AND s.maeServicioAmbitoId = " + (String) e.getValue() + " ";
                            break;
                        case "maeServicioMotivo":
                            strQuery += "AND s.maeServicioMotivoId = " + (String) e.getValue() + " ";
                            break;
                        case "asignadoUsuariosId":
                            strQuery += "AND s.gnUsuariosAsignadoId =" + (String) e.getValue() + " ";
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
    public List<AusCasoServicio> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<AusCasoServicio> listResult = new ArrayList();
        try {
            String strQuery = "FROM AusCasoServicios s "
                    + "INNER JOIN AusCasos ac ON s.ausCasosId = ac.id "
                    + "WHERE 1 = 1 "
                    + "AND ac.borrado = 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeServicioAmbito":
                            strQuery += "AND s.maeServicioAmbitoId = " + (String) e.getValue() + " ";
                            break;
                        case "maeServicioMotivo":
                            strQuery += "AND s.maeServicioMotivoId = " + (String) e.getValue() + " ";
                            break;
                        case "asignadoUsuariosId":
                            strQuery += "AND s.gnUsuariosAsignadoId =" + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "s." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "s.fechaHoraCrea DESC";
            }
            List<AusCasoServicios> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AusCasoServicios per : list) {
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
    public AusCasoServicio consultar(int id) throws java.lang.Exception {
        AusCasoServicio objRes = null;
        try {
            AusCasoServicios servicioNegocio = (AusCasoServicios) getEntityManager().find(AusCasoServicios.class, id);
            if (servicioNegocio != null){
                objRes = castEntidadNegocio(servicioNegocio);
            }
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e, "Error al consultar un servicio");
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public int insertar(AusCasoServicio servicio) throws java.lang.Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(servicio)).getId();
            servicio.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al insertar un servicio");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(AusCasoServicio obj) throws java.lang.Exception {
        try {
            String hql = "UPDATE AusCasoServicios SET"
                    + " ausCasosId.id = :casosId,"
                    + " maeServicioAmbitoId = :maeServicioAmbito,"
                    + " maeServicioMotivoId = :maeServicioMotivo,"
                    + " maeEstadoId = :maeEstadoId,"
                    + " maeEstadoCodigo = :maeEstadoCodigo,"
                    + " maeEstadoValor = :maeEstadoValor,"
                    + " fechaVencimiento = :fechaVencimiento,"
                    + " fechaCumplimiento = :fechaCumplimiento,"
                    + " maServicioId = :maeServicio,"
                    + " cantidad = :cantidad,"
                    + " descripcion = :descripcion,"
                    + " pertinencia = :pertinencia,"
                    +((obj.getMaDiagnostico() != null
                    && obj.getMaDiagnostico().getId() != null
                    && obj.getMaDiagnostico().getId() != 0) ? " maDiagnosticosId.id = :maeDiagnostico," : "") 
                    //+ " maDiagnosticosId.id = :maeDiagnostico,"
                    + " maePatologiaId = :maePatologia,"
                    +((obj.getObjetoPrestadorSede() != null 
                    && obj.getObjetoPrestadorSede().getId() != null 
                    && obj.getObjetoPrestadorSede().getId() != 0) ? " cntPrestadorSedePrescriptoraId.id = :maeIpsPrescriptora," : "")
                    //+ " cntPrestadorSedePrescriptoraId.id = :maeIpsPrescriptora,"
                    +((obj.getObjetoPrestadorIps() != null
                    && obj.getObjetoPrestadorIps().getId() != null 
                    && obj.getObjetoPrestadorIps().getId() != 0) ? " cntPrestadorSedeDestinoId.id = :maeIps," : "")
                    //+ " cntPrestadorSedeDestinoId.id = :maeIps,"
                    + ((obj.getAsignadoUsuariosId() != null
                    && obj.getAsignadoUsuariosId().getId() != null
                    && obj.getAsignadoUsuariosId().getId() != 0)
                    ? " gnUsuariosAsignadoId.id = :asignadoUsuariosId," : "")
//                    + " asignadoUsuariosId.id = :asignadoUsuariosId,"
                    + ((obj.getFechaAplica() != null) ? " fechaAplica = :fechaAplica," : "")
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("casosId", obj.getCasosId().getId());
            query.setParameter("maeServicioAmbito", obj.getMaeServicioAmbitoId());
            query.setParameter("maeServicioMotivo", obj.getMaeServicioMotivoId());
            query.setParameter("maeEstadoId", obj.getMaeEstadoId());
            query.setParameter("maeEstadoCodigo", obj.getMaeEstadoCodigo());
            query.setParameter("maeEstadoValor", obj.getMaeEstadoValor());
            query.setParameter("fechaVencimiento", obj.getFechaVencimiento());
            query.setParameter("fechaCumplimiento", obj.getFechaCumplimiento());
            query.setParameter("maeServicio", obj.getMaServicioId());
            query.setParameter("cantidad", obj.getCantidad());
            query.setParameter("descripcion", obj.getDescripcion());
            query.setParameter("pertinencia", obj.getPertinencia());
            if(obj.getMaDiagnostico() != null){
                query.setParameter("maeDiagnostico", obj.getMaDiagnostico().getId());
            }
            query.setParameter("maePatologia", obj.getMaePatologiaId());
            if(obj.getObjetoPrestadorSede() != null){
                query.setParameter("maeIpsPrescriptora", obj.getObjetoPrestadorSede().getId());
            }
            if (obj.getObjetoPrestadorIps() != null){
                query.setParameter("maeIps", obj.getObjetoPrestadorIps().getId());
            }
            if (obj.getAsignadoUsuariosId() != null && obj.getAsignadoUsuariosId().getId() != null && obj.getAsignadoUsuariosId().getId() != 0) {
                query.setParameter("asignadoUsuariosId", obj.getAsignadoUsuariosId().getId());
            }
            if (obj.getFechaAplica() != null) {
                query.setParameter("fechaAplica", obj.getFechaAplica());
            }
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e, "Error al actualizar un servicio");
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarBorrarServicio(AusCasoServicio obj) throws Exception {
        try {
            String hql = "UPDATE AusCasoServicios SET"
                    + " borrado = :borrado,"
                    + " usuarioBorra = :usuarioBorra,"
                    + " terminalBorra = :terminalBorra,"
                    + " fechaHoraBorra = :fechaHoraBorra"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("borrado", obj.getBorrado());
            query.setParameter("usuarioBorra", obj.getUsuarioBorra());
            query.setParameter("terminalBorra", obj.getTerminalBorra());
            query.setParameter("fechaHoraBorra", obj.getFechaHoraBorra());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
             cerrarEntityManager();
        }
    }
    
    @Override
    public AusCasoServicio eliminar(int id) throws java.lang.Exception {
        AusCasoServicio obj = null;
        try {
            AusCasoServicios ent = getEntityManager().find(AusCasoServicios.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e, "Error al eliminar un servicio");
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    public static AusCasoServicio castEntidadNegocio(AusCasoServicios servicioNegocio) {
        AusCasoServicio servicioEntidad = new AusCasoServicio();
        servicioEntidad.setId(servicioNegocio.getId());
        //servicioEntidad.setCasosId(new AusCaso(servicioNegocio.getAusCasosId().getId()));
        servicioEntidad.setCasosId(castEntidadNegocio(servicioNegocio.getAusCasosId()));
        servicioEntidad.setMaeServicioAmbitoId(servicioNegocio.getMaeServicioAmbitoId());
        servicioEntidad.setMaeServicioMotivoId(servicioNegocio.getMaeServicioMotivoId());
        servicioEntidad.setMaeServicioMotivoValor(servicioNegocio.getMaeServicioMotivoValor());
        servicioEntidad.setMaeTipoAdministrativoId(servicioNegocio.getMaeTipoAdministrativoId());
        servicioEntidad.setMaeTipoAdministrativoCodigo(servicioNegocio.getMaeTipoAdministrativoCodigo());
        servicioEntidad.setMaeTipoAdministrativoValor(servicioNegocio.getMaeTipoAdministrativoValor());
        servicioEntidad.setMaeEstadoId(servicioNegocio.getMaeEstadoId());
        servicioEntidad.setMaServicioValor(servicioNegocio.getMaServicioValor());
        servicioEntidad.setFechaVencimiento(servicioNegocio.getFechaVencimiento());
        servicioEntidad.setFechaCumplimiento(servicioNegocio.getFechaCumplimiento());
        servicioEntidad.setMaServicioId(servicioNegocio.getMaServicioId());
        servicioEntidad.setCantidad(servicioNegocio.getCantidad());
        servicioEntidad.setDescripcion(servicioNegocio.getDescripcion());
        servicioEntidad.setPertinencia(servicioNegocio.getPertinencia()); 
        servicioEntidad.setTipoTecnologia(servicioNegocio.getTipoTecnologia());
        servicioEntidad.setMaTecnologiaId(servicioNegocio.getMaTecnologiaId());
        servicioEntidad.setMaTecnologiaCodigo(servicioNegocio.getMaTecnologiaCodigo());
        servicioEntidad.setMaTecnologiaValor(servicioNegocio.getMaTecnologiaValor());
        if (servicioNegocio.getMaDiagnosticosId() != null){
            servicioEntidad.setObjetoDiagnostico(new Maestro(servicioNegocio.getMaDiagnosticosId().getId()));
        }
        if (servicioNegocio.getMaDiagnosticosId() != null){
            MaDiagnostico diagnostico = new  MaDiagnostico(servicioNegocio.getMaDiagnosticosId().getId());
            diagnostico.setCodigo(servicioNegocio.getMaServicioCodigo());
            diagnostico.setNombre(servicioNegocio.getMaDiagnosticosValor());
            servicioEntidad.setMaDiagnostico(diagnostico);
        }
        servicioEntidad.setMaePatologiaId(servicioNegocio.getMaePatologiaId());
        //servicioEntidad.setMaeIpsPrescriptora(servicioNegocio.getMaeIpsPrescriptora());
        //servicioEntidad.setMaeIps(servicioNegocio.getMaeIps());
        //2025-03-21 jyperez nuevos campos
        servicioEntidad.setFechaInicioVigencia(servicioNegocio.getFechaInicioVigencia());
        servicioEntidad.setFechaFinVigencia(servicioNegocio.getFechaFinVigencia());
        servicioEntidad.setAsignacionCita(servicioNegocio.getAsignacionCita());
        servicioEntidad.setUsuarioCrea(servicioNegocio.getUsuarioCrea());
        servicioEntidad.setTerminalCrea(servicioNegocio.getTerminalCrea());
        servicioEntidad.setFechaHoraCrea(servicioNegocio.getFechaHoraCrea());
        servicioEntidad.setUsuarioModifica(servicioNegocio.getUsuarioModifica());
        servicioEntidad.setFechaHoraModifica(servicioNegocio.getFechaHoraModifica());
        servicioEntidad.setTerminalModifica(servicioNegocio.getTerminalModifica());
        servicioEntidad.setAdjuntosList(AusAdjuntoServicio.castEntidadNegocio(servicioNegocio.getAusAdjuntosList()));
        servicioEntidad.setServicioGestionesList(AusServicioGestionHistoricoServicio.castEntidadNegocio(servicioNegocio.getAusServicioGestionesList()));
        if (servicioNegocio.getGnUsuariosAsignadoId() != null) {
            servicioEntidad.setAsignadoUsuariosId(UsuarioServicio.castEntidadNegocio(servicioNegocio.getGnUsuariosAsignadoId()));
            servicioEntidad.setIdUsuarioResponsable(new Usuario(servicioNegocio.getGnUsuariosAsignadoId().getId()));
            realacionarUsuarioAsignadoAHistorico(servicioEntidad.getServicioGestionesList(), servicioEntidad);
        }
        if (servicioNegocio.getCntPrestadorSedeDestinoId() != null){
            CntPrestadorSede destino = new CntPrestadorSede();
            destino.setId(servicioNegocio.getCntPrestadorSedeDestinoId().getId());
            destino.setNombreSede(servicioNegocio.getCntPrestadorSedeDestinoId().getNombre());
            servicioEntidad.setObjetoPrestadorIps(destino);
        }
        servicioEntidad.setObjetoMotivo(new Maestro(servicioNegocio.getMaeServicioMotivoId()));
        if(servicioNegocio.getCntPrestadorSedePrescriptoraId() != null){
            servicioEntidad.setObjetoPrestadorSede(new CntPrestadorSede(servicioNegocio.getCntPrestadorSedePrescriptoraId().getId()));
        }
        servicioEntidad.setObjetoPatologia(new Maestro(servicioNegocio.getMaePatologiaId()));
        return servicioEntidad;
    }

    public static AusServicioGestion castEntidadNegocio(AusServicioGestiones servicioGestionEntidad) {
        AusServicioGestion servicioEntidad = new AusServicioGestion();
        servicioEntidad.setId(servicioGestionEntidad.getId());
        servicioEntidad.setEstado(servicioGestionEntidad.getMaeEstadoId());
        servicioEntidad.setFechaHoraCrea(servicioGestionEntidad.getFechaHoraCrea());
        servicioEntidad.setObservacion(servicioGestionEntidad.getObservacion());
        servicioEntidad.setUsuarioCrea(servicioGestionEntidad.getUsuarioCrea());
        servicioEntidad.setTerminalCrea(servicioGestionEntidad.getTerminalCrea());
        return servicioEntidad;
    }

    public static AusCasoServicio castNegocioEntidad(AusCasoServicio servicioEntidad) {
        AusCasoServicio servicioNegocio = new AusCasoServicio();
        servicioNegocio.setId(servicioEntidad.getId());
        servicioNegocio.setCasosId(new AusCaso(servicioEntidad.getCasosId().getId()));
        servicioNegocio.setMaeServicioAmbitoId(servicioEntidad.getMaeServicioAmbitoId());
        servicioNegocio.setMaeServicioMotivoId(servicioEntidad.getMaeServicioMotivoId());
        servicioNegocio.setMaeEstadoId(servicioEntidad.getMaeEstadoId());
        servicioNegocio.setFechaVencimiento(servicioEntidad.getFechaVencimiento());
        servicioNegocio.setFechaCumplimiento(servicioEntidad.getFechaCumplimiento());
        servicioNegocio.setMaServicioId(servicioEntidad.getMaServicioId());
        servicioNegocio.setCantidad(servicioEntidad.getCantidad());
        servicioNegocio.setDescripcion(servicioEntidad.getDescripcion());
        servicioNegocio.setPertinencia(servicioEntidad.getPertinencia());
        servicioNegocio.setMaDiagnostico(new MaDiagnostico(servicioEntidad.getMaDiagnostico().getId()));
        servicioNegocio.setMaePatologiaId(servicioEntidad.getMaePatologiaId());
        servicioNegocio.setObjetoPrestadorSede(new CntPrestadorSede(servicioEntidad.getObjetoPrestadorSede().getId()));
        servicioNegocio.setObjetoPrestadorIps(new CntPrestadorSede(servicioEntidad.getObjetoPrestadorIps().getId()));
        servicioNegocio.setTipoTecnologia(servicioEntidad.getTipoTecnologia());
        servicioNegocio.setMaTecnologiaId(servicioEntidad.getMaTecnologiaId());
        servicioNegocio.setMaTecnologiaCodigo(servicioEntidad.getMaTecnologiaCodigo());
        servicioNegocio.setMaTecnologiaValor(servicioEntidad.getMaTecnologiaValor());
        if (servicioEntidad.getIdUsuarioResponsable().getId() > 0) {
            servicioNegocio.setAsignadoUsuariosId(new Usuario(servicioEntidad.getIdUsuarioResponsable().getId()));
        }
        servicioNegocio.setUsuarioCrea(servicioEntidad.getUsuarioCrea());
        servicioNegocio.setTerminalCrea(servicioEntidad.getTerminalCrea());
        servicioNegocio.setFechaHoraCrea(servicioEntidad.getFechaHoraCrea());
        servicioNegocio.setUsuarioModifica(servicioEntidad.getUsuarioModifica());
        servicioNegocio.setTerminalModifica(servicioEntidad.getTerminalModifica());
        servicioNegocio.setFechaHoraModifica(servicioEntidad.getFechaHoraModifica());
        return servicioNegocio;
    }

    public static List<AusCasoServicio> castEntidadNegocio(List<AusCasoServicios> serviciosNegocio) {
        List<AusCasoServicio> listaServicios = new ArrayList();
        for (AusCasoServicios servicioNegocio : serviciosNegocio) {
            listaServicios.add(AusServicioServicio.castEntidadNegocio(servicioNegocio));
        }
        return listaServicios;
    }
    
    public static AusCaso castEntidadNegocio(AusCasos per) {
        AusCaso obj = new AusCaso();
        obj.setEmpresasId(new Empresa(per.getGnEmpresasId().getId()));
        obj.setId(per.getId());
        obj.setRadicado(per.getRadicado());
        obj.setFechaNotificacion(per.getFechaNotificacion());
        obj.setFechaVencimiento(per.getFechaVencimiento());
        obj.setFechaHoraResponsable(per.getFechaHoraResponsable());
        //cast de objetos asociados
        if (per.getGnUbicacionesId() != null) {
            obj.setUbicacion(
                    new Ubicacion(
                            null,
                            per.getGnUbicacionesId().getId(),
                            per.getGnUbicacionesId().getTipo(),
                            null,
                            per.getGnUbicacionesId().getNombre()
                    )
            );
        }
        if (per.getAusPersonasId() != null) {
            obj.setAsuPersonasId(AusPersonaServicio.castEntidadNegocio(per.getAusPersonasId()));
        } else {
            obj.setAusPersonasId(new AusPersona());
        }
        if(per.getAusSeguimientosList() != null && !per.getAusSeguimientosList().isEmpty()){
            obj.setSeguimientosList(AusSeguimientoServicio.castEntidadNegocio(per.getAusSeguimientosList()));
        }
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }
    
    public static void realacionarUsuarioAsignadoAHistorico(List<AusServicioGestionHistorico> servicioGestionesList,
            AusCasoServicio servicioEntidad) {
        // preguntarle a jorge
        for (AusServicioGestionHistorico historico : servicioGestionesList) {
            //Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO))
            if (historico.getMaeEstadoId()== AusCasoServicio.ESTADO_ASIGNADO) {
                historico.setUsuarioAsignado(servicioEntidad.getAsignadoUsuariosId().getNombre());
            }
        }
    }
}
