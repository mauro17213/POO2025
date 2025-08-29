/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.atencionusuario;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCaso;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCasoServicio;
import com.saviasaludeps.savia.dominio.atencionusuario.AusServicioGestion;
import com.saviasaludeps.savia.dominio.atencionusuario.AusServicioGestionHistorico;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.AusCasoServicios;
import com.saviasaludeps.savia.ejb.entidades.AusCasos;
import com.saviasaludeps.savia.ejb.entidades.AusServicioGestiones;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.entidades.MaDiagnosticos;
import com.saviasaludeps.savia.ejb.servicios.administracion.UsuarioServicio;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.atencionusuario.AusCasoServicioRemoto;
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
 * @author raul-palacios
 */
@Stateless
@Remote(AusCasoServicioRemoto.class)
@Local(AusCasoServicioLocal.class)
public class AusCasoServicioServicio extends GenericoServicio implements AusCasoServicioLocal, AusCasoServicioRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(s) FROM AusCasos s "
                    + "WHERE 1 = 1 ";
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
                            strQuery += "AND s.asignadoUsuariosId.id =" + (String) e.getValue() + " ";
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
            String strQuery = "FROM AusCasos s "
                    + "WHERE 1 = 1 ";
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
                            strQuery += "AND s.asignadoUsuariosId.id =" + (String) e.getValue() + " ";
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
            objRes = castEntidadNegocio(servicioNegocio);
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
                    + " maeServicioAmbitoId = :maeServicioAmbitoId,"
                    + " maeServicioAmbitoCodigo = :maeServicioAmbitoCodigo,"
                    + " maeServicioAmbitoValor = :maeServicioAmbitoValor,"
                    + " maeServicioMotivoId = :maeServicioMotivoId,"
                    + " maeServicioMotivoCodigo = :maeServicioMotivoCodigo,"
                    + " maeServicioMotivoValor = :maeServicioMotivoValor,"
                    + " maeTipoAdministrativoId = :maeTipoAdministrativoId,"
                    + " maeTipoAdministrativoCodigo = :maeTipoAdministrativoCodigo,"
                    + " maeTipoAdministrativoValor = :maeTipoAdministrativoValor,"
                    + " maeEstadoId = :maeEstadoId,"
                    + " maeEstadoCodigo = :maeEstadoCodigo,"
                    + " maeEstadoValor = :maeEstadoValor,"
                    + " fechaVencimiento = :fechaVencimiento,"
                    + " fechaCumplimiento = :fechaCumplimiento,"
                    + " maServicioId = :maeServicioId,"
                    + " maServicioCodigo = :maeServicioCodigo,"
                    + " maServicioValor = :maeServicioValor,"
                    + " tipoTecnologia = :tipoTecnologia,"
                    + " maTecnologiaId = :maTecnologiaId,"
                    + " maTecnologiaCodigo = :maTecnologiaCodigo,"
                    + " maTecnologiaValor = :maTecnologiaValor,"
                    + " cantidad = :cantidad,"
                    + " descripcion = :descripcion,"
                    + " pertinencia = :pertinencia,"
                    + " maDiagnosticosId.id = :maeDiagnosticoId,"
                    + " maDiagnosticosCodigo = :maeDiagnosticoCodigo,"
                    + " maDiagnosticosValor = :maeDiagnosticoValor,"
                    + " maePatologiaId = :maePatologiaId,"
                    + " maePatologiaCodigo = :maePatologiaCodigo,"
                    + " maePatologiaValor = :maePatologiaValor,"
                    + " cntPrestadorSedePrescriptoraId.id = :maeIpsPrescriptoraId,"
                    + " cntPrestadorSedePrescriptoraValor = :objetoPrestadorSedeValor,"
                    + " cntPrestadorSedeDestinoId.id = :maeIpsId,"
                    + " cntPrestadorSedeDestinoValor = :objetoPrestadorIpsValor,"
                    
                    + " medicamento = :medicamento,"
                    + " medicamentoCobertura = :medicamentoCobertura,"
                    + " servicioAtribuidoIps = :servicioAtribuidoIps,"
                    + " fechaInicioVigencia = :fechaInicioVigencia,"
                    + " fechaFinVigencia = :fechaFinVigencia,"
                    + " asignacionCita = :asignacionCita,"
                   + ((obj.getAsignadoUsuariosId() != null
                    && obj.getAsignadoUsuariosId().getId() != null
                    && obj.getAsignadoUsuariosId().getId() != 0)
                    ? " gnUsuariosAsignadoId.id = :asignadoUsuariosId," : "")
//                    + " asignadoUsuariosId.id = :asignadoUsuariosId,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("casosId", obj.getCasosId().getId());
            query.setParameter("maeServicioAmbitoId", obj.getMaeServicioAmbitoId());
            query.setParameter("maeServicioAmbitoCodigo", obj.getMaeServicioAmbitoCodigo());
            query.setParameter("maeServicioAmbitoValor", obj.getMaeServicioAmbitoValor());
            query.setParameter("maeServicioMotivoId", obj.getMaeServicioMotivoId());
            query.setParameter("maeServicioMotivoCodigo", obj.getMaeServicioMotivoCodigo());
            query.setParameter("maeServicioMotivoValor", obj.getMaeServicioMotivoValor());
            query.setParameter("maeTipoAdministrativoId", obj.getMaeTipoAdministrativoId());
            query.setParameter("maeTipoAdministrativoCodigo", obj.getMaeTipoAdministrativoCodigo());
            query.setParameter("maeTipoAdministrativoValor", obj.getMaeTipoAdministrativoValor());
            query.setParameter("maeEstadoId", obj.getMaeEstadoId());
            query.setParameter("maeEstadoCodigo", obj.getMaeEstadoCodigo());
            query.setParameter("maeEstadoValor", obj.getMaeEstadoValor());
            query.setParameter("fechaVencimiento", obj.getFechaVencimiento());
            query.setParameter("fechaCumplimiento", obj.getFechaCumplimiento());
            query.setParameter("maeServicioId", obj.getMaServicioId());
            query.setParameter("maeServicioCodigo", obj.getMaServicioCodigo());
            query.setParameter("maeServicioValor", obj.getMaServicioValor());
            query.setParameter("tipoTecnologia", obj.getTipoTecnologia());
            query.setParameter("maTecnologiaId", obj.getMaTecnologiaId());
            query.setParameter("maTecnologiaCodigo", obj.getMaTecnologiaCodigo());
            query.setParameter("maTecnologiaValor", obj.getMaTecnologiaValor());
            query.setParameter("cantidad", obj.getCantidad());
            query.setParameter("descripcion", obj.getDescripcion());
            query.setParameter("pertinencia", obj.getPertinencia());
            //query.setParameter("maeDiagnosticoId", obj.getMaDiagnostico().getId());
            query.setParameter("maeDiagnosticoId", obj.getMaDiagnosticosId());
            query.setParameter("maeDiagnosticoCodigo", obj.getMaDiagnosticosCodigo());
            query.setParameter("maeDiagnosticoValor", obj.getMaDiagnosticosValor());
            //query.setParameter("maePatologiaId", obj.getMaePatologiaId());
            query.setParameter("maePatologiaId", obj.getObjetoPatologia().getId());
            //query.setParameter("maePatologiaCodigo", obj.getMaePatologiaCodigo());
            query.setParameter("maePatologiaCodigo",obj.getObjetoPatologia().getValor());
            //query.setParameter("maePatologiaValor", obj.getMaePatologiaValor());
            query.setParameter("maePatologiaValor", obj.getObjetoPatologia().getNombre());
            //uery.setParameter("cntIpsPrescriptoraId", obj.getCntIpsPrescriptoraId().getId());
            query.setParameter("maeIpsPrescriptoraId",obj.getObjetoPrestadorSede().getId());
            query.setParameter("objetoPrestadorSedeValor",obj.getObjetoPrestadorSedeValor());
            
            //query.setParameter("cntIpsId", obj.getCntIpsId().getId());
            query.setParameter("maeIpsId", obj.getObjetoPrestadorIps().getId());
            query.setParameter("objetoPrestadorIpsValor", obj.getObjetoPrestadorIpsValor());
            
            query.setParameter("medicamento", obj.getMedicamento());
            query.setParameter("medicamentoCobertura", obj.getMedicamentoCobertura());
            query.setParameter("servicioAtribuidoIps", obj.getServicioAtributoIps());
            query.setParameter("fechaInicioVigencia", obj.getFechaInicioVigencia());
            query.setParameter("fechaFinVigencia", obj.getFechaFinVigencia());
            query.setParameter("asignacionCita", obj.isAsignacionCita());
            if (obj.getAsignadoUsuariosId() != null && obj.getAsignadoUsuariosId().getId() != null && obj.getAsignadoUsuariosId().getId() != 0) {
                query.setParameter("asignadoUsuariosId", obj.getAsignadoUsuariosId().getId());
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
    public void actualizarBorrarCaso(AusCasoServicio obj) throws java.lang.Exception {
        try {
            String hql = "UPDATE AusCasoServicios SET"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            
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
    public void actualizarEstadoCerrado(AusCasoServicio obj) throws Exception {
    
        try {
            String hql = "UPDATE AusCasoServicios SET"
                    + " maeEstadoId = :maeEstadoId,"
                    + " maeEstadoCodigo = :maeEstadoCodigo,"
                    + " maeEstadoValor = :maeEstadoValor,"
                    + " fechaCumplimiento = :fechaCumplimiento,"
                    + " descripcion = :descripcion,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("maeEstadoId", obj.getMaeEstadoId());
            query.setParameter("maeEstadoCodigo", obj.getMaeEstadoCodigo());
            query.setParameter("maeEstadoValor", obj.getMaeEstadoValor());
            query.setParameter("fechaCumplimiento", obj.getFechaCumplimiento());
            query.setParameter("descripcion", obj.getDescripcion());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
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
    
    @Override
    public List<AusCasoServicio> consultarServiciosCerradoOresueltos(Integer idCaso) throws Exception {
        List<AusCasoServicio> hospitalizacion = new ArrayList<>();

        try {
            String strQuery = "SELECT p "
                    + "FROM AusCasoServicios p "
                    + "INNER JOIN AusCasos ac ON p.ausCasosId = ac.id "
                    + "WHERE ac.id = " + idCaso + " "
                    + "AND p.borrado = 0 "
                    + "ORDER BY p.fechaHoraCrea DESC ";
            List<AusCasoServicios> obj = getEntityManager().createQuery(strQuery).getResultList();
            for (AusCasoServicios aucHospitalizacion : obj) {
                hospitalizacion.add(castEntidadNegocio(aucHospitalizacion));
            }
        } catch (NoResultException e) {
            hospitalizacion = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return hospitalizacion;
    }
    
    @Override
    public List<AusCasoServicio> consultarServiciosTodoServicios(Integer idCaso) throws Exception {
        List<AusCasoServicio> hospitalizacion = new ArrayList<>();

        try {
            String strQuery = "SELECT p "
                    + "FROM AusCasoServicios p "
                    + "INNER JOIN AusCasos ac ON p.ausCasosId = ac.id "
                    + "WHERE ac.id = " + idCaso + " "
                    + "AND p.borrado = 0 "
                    + "ORDER BY p.fechaHoraCrea DESC ";
            List<AusCasoServicios> obj = getEntityManager().createQuery(strQuery).getResultList();
            int pos = 0;
            for (AusCasoServicios aucHospitalizacion : obj) {
                hospitalizacion.add(castEntidadNegocio(aucHospitalizacion, pos));
                pos++; 
            }
        } catch (NoResultException e) {
            hospitalizacion = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return hospitalizacion;
    }
    
    @Override
    public List<AusCasoServicio> consultarServiciosDiferenteCerradosoResultos(Integer idCaso) throws Exception {
        List<AusCasoServicio> hospitalizacion = new ArrayList<>();

        try {
            String strQuery = "SELECT p "
                    + "FROM AusCasoServicios p "
                    + "INNER JOIN AusCasos ac ON p.ausCasosId = ac.id "
                    + "WHERE ac.id = " + idCaso + " "
                    + "AND p.maeEstadoCodigo NOT IN (6, 4, 7)" 
                    + "AND p.borrado = 0 "
                    + "ORDER BY p.fechaHoraCrea DESC ";
            List<AusCasoServicios> obj = getEntityManager().createQuery(strQuery).getResultList();
            for (AusCasoServicios aucHospitalizacion : obj) {
                hospitalizacion.add(castEntidadNegocio(aucHospitalizacion));
            }
        } catch (NoResultException e) {
            hospitalizacion = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return hospitalizacion;
    }
    
    public static AusCasoServicio castEntidadNegocio(AusCasoServicios servicioNegocio) {
        AusCasoServicio servicioEntidad = new AusCasoServicio();
        servicioEntidad.setId(servicioNegocio.getId());
        servicioEntidad.setCasosId(new AusCaso(servicioNegocio.getAusCasosId().getId()));
        servicioEntidad.setMaeServicioAmbitoId(servicioNegocio.getMaeServicioAmbitoId());
        servicioEntidad.setMaeServicioAmbitoCodigo(servicioNegocio.getMaeServicioAmbitoCodigo());
        servicioEntidad.setMaeServicioAmbitoValor(servicioNegocio.getMaeServicioAmbitoValor());
        servicioEntidad.setMaeServicioMotivoId(servicioNegocio.getMaeServicioMotivoId());
        servicioEntidad.setMaeServicioMotivoValor(servicioNegocio.getMaeServicioMotivoValor());
        servicioEntidad.setMaeServicioMotivoCodigo(servicioNegocio.getMaeServicioMotivoCodigo());
        servicioEntidad.setMaeTipoAdministrativoId(servicioNegocio.getMaeTipoAdministrativoId());
        servicioEntidad.setMaeTipoAdministrativoCodigo(servicioNegocio.getMaeTipoAdministrativoCodigo());
        servicioEntidad.setMaeTipoAdministrativoValor(servicioNegocio.getMaeTipoAdministrativoValor());
        servicioEntidad.setMaeEstadoId(servicioNegocio.getMaeEstadoId());
        servicioEntidad.setMaeEstadoCodigo(servicioNegocio.getMaeEstadoCodigo());
        servicioEntidad.setMaeEstadoValor(servicioNegocio.getMaeEstadoValor());
        servicioEntidad.setFechaVencimiento(servicioNegocio.getFechaVencimiento());
        servicioEntidad.setFechaCumplimiento(servicioNegocio.getFechaCumplimiento());
        servicioEntidad.setMaServicioId(servicioNegocio.getMaServicioId());
        servicioEntidad.setMaServicioCodigo(servicioNegocio.getMaServicioCodigo());
        servicioEntidad.setMaServicioValor(servicioNegocio.getMaServicioValor());
        servicioEntidad.setCantidad(servicioNegocio.getCantidad());
        servicioEntidad.setDescripcion(servicioNegocio.getDescripcion());
        servicioEntidad.setPertinencia(servicioNegocio.getPertinencia());
        servicioEntidad.setTipoTecnologia(servicioNegocio.getTipoTecnologia());
        servicioEntidad.setMaTecnologiaId(servicioNegocio.getMaTecnologiaId());
        servicioEntidad.setMaTecnologiaCodigo(servicioNegocio.getMaTecnologiaCodigo());
        servicioEntidad.setMaTecnologiaValor(servicioNegocio.getMaTecnologiaValor());
        servicioEntidad.setFechaAplica(servicioNegocio.getFechaAplica());
        //2024-08-22 jyperez nuevos campos
        servicioEntidad.setFechaInicioVigencia(servicioNegocio.getFechaInicioVigencia());
        servicioEntidad.setFechaFinVigencia(servicioNegocio.getFechaFinVigencia());
        //2025-03-21 jyperez nuevo campo
        servicioEntidad.setAsignacionCita(servicioNegocio.getAsignacionCita());
        //servicioEntidad.setMaDiagnostico(new MaDiagnostico(servicioNegocio.getMaDiagnosticosId().getId()));
        /*Maestro dianostico = new Maestro();
        if (servicioNegocio.getMaDiagnosticosId() != null){
            dianostico.setId(servicioNegocio.getMaDiagnosticosId().getId());
            dianostico.setValor(servicioNegocio.getMaDiagnosticosId().getCodigo());
            dianostico.setNombre(servicioNegocio.getMaDiagnosticosId().getNombre());
        }*/
        if(servicioNegocio.getMaDiagnosticosId() != null){
            servicioEntidad.setMaDiagnosticosId(servicioNegocio.getMaDiagnosticosId().getId());
        }
        servicioEntidad.setMaDiagnosticosCodigo(servicioNegocio.getMaDiagnosticosCodigo());
        servicioEntidad.setMaDiagnosticosValor(servicioNegocio.getMaDiagnosticosValor());
        //servicioEntidad.setObjetoDiagnostico(dianostico);
        servicioEntidad.setMaePatologiaId(servicioNegocio.getMaePatologiaId());
        servicioEntidad.setMaePatologiaValor(servicioNegocio.getMaePatologiaValor());
        servicioEntidad.setMaePatologiaCodigo(servicioNegocio.getMaePatologiaCodigo());
        servicioEntidad.setUsuarioCrea(servicioNegocio.getUsuarioCrea());
        servicioEntidad.setTerminalCrea(servicioNegocio.getTerminalCrea());
        servicioEntidad.setFechaHoraCrea(servicioNegocio.getFechaHoraCrea());
        servicioEntidad.setUsuarioModifica(servicioNegocio.getUsuarioModifica());
        servicioEntidad.setFechaHoraModifica(servicioNegocio.getFechaHoraModifica());
        servicioEntidad.setTerminalModifica(servicioNegocio.getTerminalModifica());
        servicioEntidad.setAdjuntosList(AusAdjuntoServicio.castEntidadNegocio(servicioNegocio.getAusAdjuntosList()));
        servicioEntidad.setServicioGestionesList(AusServicioGestionHistoricoServicio.castEntidadNegocio(servicioNegocio.getAusServicioGestionesList()));
        //servicioEntidad.setCntIpsId(new CntPrestador(servicioNegocio.getCntIpsId().getId()));
        //servicioEntidad.setObjetoIps(new Maestro(servicioNegocio.getCntIpsId().getId()));
        servicioEntidad.setMedicamento(servicioNegocio.getMedicamento());
        servicioEntidad.setMedicamentoCobertura(servicioNegocio.getMedicamentoCobertura());
        servicioEntidad.setServicioAtributoIps(servicioNegocio.getServicioAtribuidoIps()); 
        CntPrestadorSede prestadorIps = new CntPrestadorSede();
        if (servicioNegocio.getCntPrestadorSedeDestinoId() != null) {
            prestadorIps.setId(servicioNegocio.getCntPrestadorSedeDestinoId().getId());
            prestadorIps.setCodigoSede(servicioNegocio.getCntPrestadorSedeDestinoId().getCodigo());
            prestadorIps.setNombreSede(servicioNegocio.getCntPrestadorSedeDestinoId().getNombre()); 
        }
        servicioEntidad.setObjetoPrestadorIpsValor(servicioNegocio.getCntPrestadorSedeDestinoValor());
        servicioEntidad.setObjetoPrestadorIps(prestadorIps);
        
        //servicioEntidad.setCntIpsPrescriptoraId(new CntPrestador(servicioNegocio.getCntIpsPrescriptoraId().getId()));
        //servicioEntidad.setObjetoIpsPrescriptora(new Maestro(servicioNegocio.getCntIpsPrescriptoraId().getId()));
        CntPrestadorSede prestadorIpsPrescriptora = new CntPrestadorSede();
        if (servicioNegocio.getCntPrestadorSedePrescriptoraId() != null) {
            prestadorIpsPrescriptora.setId(servicioNegocio.getCntPrestadorSedePrescriptoraId().getId());
            prestadorIpsPrescriptora.setCodigoSede(servicioNegocio.getCntPrestadorSedePrescriptoraId().getCodigo());
            prestadorIpsPrescriptora.setNombreSede(servicioNegocio.getCntPrestadorSedePrescriptoraId().getNombre());
            
        }
        servicioEntidad.setObjetoPrestadorSedeValor(servicioNegocio.getCntPrestadorSedePrescriptoraValor());
        servicioEntidad.setObjetoPrestadorSede(prestadorIpsPrescriptora);
        
        if (servicioNegocio.getGnUsuariosAsignadoId() != null) {
            servicioEntidad.setAsignadoUsuariosId(UsuarioServicio.castEntidadNegocio(servicioNegocio.getGnUsuariosAsignadoId()));
            Usuario usuarioResponsable = new Usuario();
            usuarioResponsable.setId(servicioNegocio.getGnUsuariosAsignadoId().getId());
            usuarioResponsable.setUsuario(servicioNegocio.getGnUsuariosAsignadoId().getUsuario());
            usuarioResponsable.setNombre(servicioNegocio.getGnUsuariosAsignadoId().getNombre());
            servicioEntidad.setIdUsuarioResponsable(usuarioResponsable);
            realacionarUsuarioAsignadoAHistorico(servicioEntidad.getServicioGestionesList(), servicioEntidad);
        }
        
        servicioEntidad.setObjetoMotivo(new Maestro(servicioNegocio.getMaeServicioMotivoId()));
        servicioEntidad.setObjetoPatologia(new Maestro(servicioNegocio.getMaePatologiaId()));
        return servicioEntidad;
    }
    
    public static AusCasoServicio castEntidadNegocioCorto(AusCasoServicios servicioNegocio) {
        AusCasoServicio servicioEntidad = new AusCasoServicio();
        servicioEntidad.setId(servicioNegocio.getId());
        servicioEntidad.setCasosId(new AusCaso(servicioNegocio.getAusCasosId().getId()));
        servicioEntidad.setMaeServicioAmbitoId(servicioNegocio.getMaeServicioAmbitoId());
        servicioEntidad.setMaeServicioAmbitoCodigo(servicioNegocio.getMaeServicioAmbitoCodigo());
        servicioEntidad.setMaeServicioAmbitoValor(servicioNegocio.getMaeServicioAmbitoValor());
        servicioEntidad.setMaeServicioMotivoId(servicioNegocio.getMaeServicioMotivoId());
        servicioEntidad.setMaeServicioMotivoValor(servicioNegocio.getMaeServicioMotivoValor());
        servicioEntidad.setMaeServicioMotivoCodigo(servicioNegocio.getMaeServicioMotivoCodigo());
        servicioEntidad.setMaeTipoAdministrativoId(servicioNegocio.getMaeTipoAdministrativoId());
        servicioEntidad.setMaeTipoAdministrativoCodigo(servicioNegocio.getMaeTipoAdministrativoCodigo());
        servicioEntidad.setMaeTipoAdministrativoValor(servicioNegocio.getMaeTipoAdministrativoValor());
        servicioEntidad.setMaeEstadoId(servicioNegocio.getMaeEstadoId());
        servicioEntidad.setMaeEstadoCodigo(servicioNegocio.getMaeEstadoCodigo());
        servicioEntidad.setMaeEstadoValor(servicioNegocio.getMaeEstadoValor());
        servicioEntidad.setFechaVencimiento(servicioNegocio.getFechaVencimiento());
        servicioEntidad.setFechaCumplimiento(servicioNegocio.getFechaCumplimiento());
        servicioEntidad.setMaServicioId(servicioNegocio.getMaServicioId());
        servicioEntidad.setMaServicioCodigo(servicioNegocio.getMaServicioCodigo());
        servicioEntidad.setMaServicioValor(servicioNegocio.getMaServicioValor());
        servicioEntidad.setCantidad(servicioNegocio.getCantidad());
        servicioEntidad.setDescripcion(servicioNegocio.getDescripcion());
        servicioEntidad.setPertinencia(servicioNegocio.getPertinencia());
        servicioEntidad.setTipoTecnologia(servicioNegocio.getTipoTecnologia());
        servicioEntidad.setMaTecnologiaId(servicioNegocio.getMaTecnologiaId());
        servicioEntidad.setMaTecnologiaCodigo(servicioNegocio.getMaTecnologiaCodigo());
        servicioEntidad.setMaTecnologiaValor(servicioNegocio.getMaTecnologiaValor());
        //2024-08-22 jyperez nuevos campos
        servicioEntidad.setFechaInicioVigencia(servicioNegocio.getFechaInicioVigencia());
        servicioEntidad.setFechaFinVigencia(servicioNegocio.getFechaFinVigencia());
        return servicioEntidad;
    }
    
    public static AusCasoServicio castEntidadNegocio(AusCasoServicios servicioNegocio, int pos) {
        AusCasoServicio servicioEntidad = new AusCasoServicio();
        servicioEntidad.setId(servicioNegocio.getId());
        servicioEntidad.setPos(pos);
        servicioEntidad.setCasosId(new AusCaso(servicioNegocio.getAusCasosId().getId()));
        servicioEntidad.setMaeServicioAmbitoId(servicioNegocio.getMaeServicioAmbitoId());
        servicioEntidad.setMaeServicioAmbitoCodigo(servicioNegocio.getMaeServicioAmbitoCodigo());
        servicioEntidad.setMaeServicioAmbitoValor(servicioNegocio.getMaeServicioAmbitoValor());
        servicioEntidad.setMaeServicioMotivoId(servicioNegocio.getMaeServicioMotivoId());
        servicioEntidad.setMaeServicioMotivoValor(servicioNegocio.getMaeServicioMotivoValor());
        servicioEntidad.setMaeServicioMotivoCodigo(servicioNegocio.getMaeServicioMotivoCodigo());
        servicioEntidad.setMaeTipoAdministrativoId(servicioNegocio.getMaeTipoAdministrativoId());
        servicioEntidad.setMaeTipoAdministrativoCodigo(servicioNegocio.getMaeTipoAdministrativoCodigo());
        servicioEntidad.setMaeTipoAdministrativoValor(servicioNegocio.getMaeTipoAdministrativoValor());
        servicioEntidad.setMaeEstadoId(servicioNegocio.getMaeEstadoId());
        servicioEntidad.setMaeEstadoCodigo(servicioNegocio.getMaeEstadoCodigo());
        servicioEntidad.setMaeEstadoValor(servicioNegocio.getMaeEstadoValor());
        servicioEntidad.setFechaVencimiento(servicioNegocio.getFechaVencimiento());
        servicioEntidad.setFechaCumplimiento(servicioNegocio.getFechaCumplimiento());
        servicioEntidad.setMaServicioId(servicioNegocio.getMaServicioId());
        servicioEntidad.setMaServicioCodigo(servicioNegocio.getMaServicioCodigo());
        servicioEntidad.setMaServicioValor(servicioNegocio.getMaServicioValor());
        servicioEntidad.setCantidad(servicioNegocio.getCantidad());
        servicioEntidad.setDescripcion(servicioNegocio.getDescripcion());
        servicioEntidad.setPertinencia(servicioNegocio.getPertinencia());
        servicioEntidad.setTipoTecnologia(servicioNegocio.getTipoTecnologia());
        servicioEntidad.setMaTecnologiaId(servicioNegocio.getMaTecnologiaId());
        servicioEntidad.setMaTecnologiaCodigo(servicioNegocio.getMaTecnologiaCodigo());
        servicioEntidad.setMaTecnologiaValor(servicioNegocio.getMaTecnologiaValor());
        servicioEntidad.setFechaAplica(servicioNegocio.getFechaAplica());
        //2024-08-22 jyperez nuevos campos
        servicioEntidad.setFechaInicioVigencia(servicioNegocio.getFechaInicioVigencia());
        servicioEntidad.setFechaFinVigencia(servicioNegocio.getFechaFinVigencia());
        //servicioEntidad.setMaDiagnostico(new MaDiagnostico(servicioNegocio.getMaDiagnosticosId().getId()));
       /* Maestro dianostico = new Maestro();
        if (servicioNegocio.getMaDiagnosticosId() != null){
            dianostico.setId(servicioNegocio.getMaDiagnosticosId().getId());
            dianostico.setValor(servicioNegocio.getMaDiagnosticosId().getCodigo());
            dianostico.setNombre(servicioNegocio.getMaDiagnosticosId().getNombre());
        }*/
        
        if(servicioNegocio.getMaDiagnosticosId() != null){
            servicioEntidad.setMaDiagnosticosId(servicioNegocio.getMaDiagnosticosId().getId());
        }
        servicioEntidad.setMaDiagnosticosCodigo(servicioNegocio.getMaDiagnosticosCodigo());
        servicioEntidad.setMaDiagnosticosValor(servicioNegocio.getMaDiagnosticosValor());
        
        //servicioEntidad.setObjetoDiagnostico(dianostico);
        servicioEntidad.setMaePatologiaId(servicioNegocio.getMaePatologiaId());
        servicioEntidad.setMaePatologiaValor(servicioNegocio.getMaePatologiaValor());
        servicioEntidad.setMaePatologiaCodigo(servicioNegocio.getMaePatologiaCodigo());
        servicioEntidad.setUsuarioCrea(servicioNegocio.getUsuarioCrea());
        servicioEntidad.setTerminalCrea(servicioNegocio.getTerminalCrea());
        servicioEntidad.setFechaHoraCrea(servicioNegocio.getFechaHoraCrea());
        servicioEntidad.setUsuarioModifica(servicioNegocio.getUsuarioModifica());
        servicioEntidad.setFechaHoraModifica(servicioNegocio.getFechaHoraModifica());
        servicioEntidad.setTerminalModifica(servicioNegocio.getTerminalModifica());
        servicioEntidad.setAdjuntosList(AusAdjuntoServicio.castEntidadNegocio(servicioNegocio.getAusAdjuntosList()));
        servicioEntidad.setServicioGestionesList(AusServicioGestionHistoricoServicio.castEntidadNegocio(servicioNegocio.getAusServicioGestionesList()));
        //servicioEntidad.setCntIpsId(new CntPrestador(servicioNegocio.getCntIpsId().getId()));
        //servicioEntidad.setObjetoIps(new Maestro(servicioNegocio.getCntIpsId().getId()));
        servicioEntidad.setMedicamento(servicioNegocio.getMedicamento());
        servicioEntidad.setMedicamentoCobertura(servicioNegocio.getMedicamentoCobertura());
        servicioEntidad.setServicioAtributoIps(servicioNegocio.getServicioAtribuidoIps()); 
        CntPrestadorSede prestadorIps = new CntPrestadorSede();
        if (servicioNegocio.getCntPrestadorSedeDestinoId() != null) {
            prestadorIps.setId(servicioNegocio.getCntPrestadorSedeDestinoId().getId());
            prestadorIps.setCodigoSede(servicioNegocio.getCntPrestadorSedeDestinoId().getCodigo());
            prestadorIps.setNombreSede(servicioNegocio.getCntPrestadorSedeDestinoId().getNombre()); 
        }
        servicioEntidad.setObjetoPrestadorIpsValor(servicioNegocio.getCntPrestadorSedeDestinoValor());
        servicioEntidad.setObjetoPrestadorIps(prestadorIps);
        
        //servicioEntidad.setCntIpsPrescriptoraId(new CntPrestador(servicioNegocio.getCntIpsPrescriptoraId().getId()));
        //servicioEntidad.setObjetoIpsPrescriptora(new Maestro(servicioNegocio.getCntIpsPrescriptoraId().getId()));
        CntPrestadorSede prestadorIpsPrescriptora = new CntPrestadorSede();
        if (servicioNegocio.getCntPrestadorSedePrescriptoraId() != null) {
            prestadorIpsPrescriptora.setId(servicioNegocio.getCntPrestadorSedePrescriptoraId().getId());
            prestadorIpsPrescriptora.setCodigoSede(servicioNegocio.getCntPrestadorSedePrescriptoraId().getCodigo());
            prestadorIpsPrescriptora.setNombreSede(servicioNegocio.getCntPrestadorSedePrescriptoraId().getNombre());
            
        }
        servicioEntidad.setObjetoPrestadorSedeValor(servicioNegocio.getCntPrestadorSedePrescriptoraValor());
        servicioEntidad.setObjetoPrestadorSede(prestadorIpsPrescriptora);
        
        if (servicioNegocio.getGnUsuariosAsignadoId() != null) {
            servicioEntidad.setAsignadoUsuariosId(UsuarioServicio.castEntidadNegocio(servicioNegocio.getGnUsuariosAsignadoId()));
            Usuario usuarioResponsable = new Usuario();
            usuarioResponsable.setId(servicioNegocio.getGnUsuariosAsignadoId().getId());
            usuarioResponsable.setUsuario(servicioNegocio.getGnUsuariosAsignadoId().getUsuario());
            usuarioResponsable.setNombre(servicioNegocio.getGnUsuariosAsignadoId().getNombre());
            servicioEntidad.setIdUsuarioResponsable(usuarioResponsable);
            realacionarUsuarioAsignadoAHistorico(servicioEntidad.getServicioGestionesList(), servicioEntidad);
        }
        
        servicioEntidad.setObjetoMotivo(new Maestro(servicioNegocio.getMaeServicioMotivoId()));
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

    public static AusCasoServicios castNegocioEntidad(AusCasoServicio servicioEntidad) {
        AusCasoServicios servicioNegocio = new AusCasoServicios();
        servicioNegocio.setId(servicioEntidad.getId());
        servicioNegocio.setAusCasosId(new AusCasos(servicioEntidad.getCasosId().getId()));
        servicioNegocio.setMaeServicioAmbitoId(servicioEntidad.getMaeServicioAmbitoId());
        servicioNegocio.setMaeServicioAmbitoCodigo(servicioEntidad.getMaeServicioAmbitoCodigo());
        servicioNegocio.setMaeServicioAmbitoValor(servicioEntidad.getMaeServicioAmbitoValor());
        servicioNegocio.setMaeServicioMotivoId(servicioEntidad.getMaeServicioMotivoId());
        servicioNegocio.setMaeServicioMotivoCodigo(servicioEntidad.getMaeServicioMotivoCodigo());
        servicioNegocio.setMaeServicioMotivoValor(servicioEntidad.getMaeServicioMotivoValor());
        servicioNegocio.setMaeTipoAdministrativoId(servicioEntidad.getMaeTipoAdministrativoId());
        servicioNegocio.setMaeTipoAdministrativoCodigo(servicioEntidad.getMaeTipoAdministrativoCodigo());
        servicioNegocio.setMaeTipoAdministrativoValor(servicioEntidad.getMaeTipoAdministrativoValor());
        servicioNegocio.setTipoTecnologia(servicioEntidad.getTipoTecnologia());
        servicioNegocio.setMaTecnologiaId(servicioEntidad.getMaTecnologiaId());
        servicioNegocio.setMaTecnologiaCodigo(servicioEntidad.getMaTecnologiaCodigo());
        servicioNegocio.setMaTecnologiaValor(servicioEntidad.getMaTecnologiaValor());
        servicioNegocio.setMaeEstadoId(servicioEntidad.getMaeEstadoId());
        servicioNegocio.setMaeEstadoCodigo(servicioEntidad.getMaeEstadoCodigo());
        servicioNegocio.setMaeEstadoValor(servicioEntidad.getMaeEstadoValor());
        servicioNegocio.setFechaVencimiento(servicioEntidad.getFechaVencimiento());
        servicioNegocio.setFechaCumplimiento(servicioEntidad.getFechaCumplimiento());
        servicioNegocio.setMaServicioId(servicioEntidad.getMaServicioId());
        servicioNegocio.setMaServicioCodigo(servicioEntidad.getMaServicioCodigo());
        servicioNegocio.setMaServicioValor(servicioEntidad.getMaServicioValor());
        servicioNegocio.setCantidad(servicioEntidad.getCantidad());
        servicioNegocio.setDescripcion(servicioEntidad.getDescripcion());
        servicioNegocio.setPertinencia(servicioEntidad.getPertinencia());
        servicioNegocio.setMedicamento(servicioEntidad.getMedicamento());
        servicioNegocio.setMedicamentoCobertura(servicioEntidad.getMedicamentoCobertura());
        servicioNegocio.setBorrado(servicioEntidad.getBorrado());
        //2024-08-22 jyperez nuevos campos
        servicioNegocio.setFechaInicioVigencia(servicioEntidad.getFechaInicioVigencia());
        servicioNegocio.setFechaFinVigencia(servicioEntidad.getFechaFinVigencia());
        if(servicioEntidad.getServicioAtributoIps() != null){
            servicioNegocio.setServicioAtribuidoIps(servicioEntidad.getServicioAtributoIps());
        }
        //2025-03-21 jyperez nuevo campo
        servicioNegocio.setAsignacionCita(servicioEntidad.isAsignacionCita());
        
        //servicioNegocio.setMaDiagnosticosId(new MaDiagnosticos(servicioEntidad.getMaDiagnostico().getId()));
        /*if (servicioEntidad.getObjetoDiagnostico() != null && servicioEntidad.getObjetoDiagnostico().getId() != null) {
            servicioNegocio.setMaDiagnosticosId(new MaDiagnosticos(servicioEntidad.getObjetoDiagnostico().getId()));
            servicioNegocio.setMaDiagnosticosCodigo(servicioEntidad.getObjetoDiagnostico().getValor());
            servicioNegocio.setMaDiagnosticosValor(servicioEntidad.getObjetoDiagnostico().getNombre());
        }*/
        if(servicioEntidad.getMaDiagnosticosId() != null){
            servicioNegocio.setMaDiagnosticosId(new MaDiagnosticos(servicioEntidad.getMaDiagnosticosId()));
        }
        servicioNegocio.setMaDiagnosticosCodigo(servicioEntidad.getMaDiagnosticosCodigo());
        servicioNegocio.setMaDiagnosticosValor(servicioEntidad.getMaDiagnosticosValor());
        
        servicioNegocio.setMaePatologiaId(servicioEntidad.getMaePatologiaId());
        servicioNegocio.setMaePatologiaCodigo(servicioEntidad.getMaePatologiaCodigo());
        servicioNegocio.setMaePatologiaValor(servicioEntidad.getMaePatologiaValor());
        
        //servicioNegocio.setCntIpsPrescriptoraId(new CntPrestadores(servicioEntidad.getCntIpsPrescriptoraId().getId()));
        //servicioNegocio.setCntIpsPrescriptoraId(new CntPrestadores(servicioEntidad.getObjetoIpsPrescriptora().getId()));
        CntPrestadorSedes prestadoresIpsPrescriptora = new CntPrestadorSedes();
        if (servicioEntidad.getObjetoPrestadorSede() != null && servicioEntidad.getObjetoPrestadorSede().getId() != null) {
            prestadoresIpsPrescriptora.setId(servicioEntidad.getObjetoPrestadorSede().getId());
            prestadoresIpsPrescriptora.setCodigo(servicioEntidad.getObjetoPrestadorSede().getCodigoSede());
            prestadoresIpsPrescriptora.setNombre(servicioEntidad.getObjetoPrestadorSede().getNombreSede());
            servicioNegocio.setCntPrestadorSedePrescriptoraId(prestadoresIpsPrescriptora);      
            servicioNegocio.setCntPrestadorSedePrescriptoraValor(servicioEntidad.getObjetoPrestadorSede().getNombreSede());
        }

        
        //servicioNegocio.setCntIpsId(new CntPrestadores(servicioEntidad.getObjetoIps().getId()));
        CntPrestadorSedes prestadoresIps = new CntPrestadorSedes();

        if (servicioEntidad.getObjetoPrestadorIps() != null && servicioEntidad.getObjetoPrestadorIps().getId() != null) {
            prestadoresIps.setId(servicioEntidad.getObjetoPrestadorIps().getId());
            prestadoresIps.setCodigo(servicioEntidad.getObjetoPrestadorIps().getCodigoSede());
            prestadoresIps.setNombre(servicioEntidad.getObjetoPrestadorIps().getNombreSede());
            servicioNegocio.setCntPrestadorSedeDestinoId(prestadoresIps);  
            servicioNegocio.setCntPrestadorSedeDestinoValor(servicioEntidad.getObjetoPrestadorIps().getNombreSede());
        }
             
        if (servicioEntidad.getIdUsuarioResponsable().getId() != null) {
            if(servicioEntidad.getIdUsuarioResponsable().getId() > 0){
                servicioNegocio.setGnUsuariosAsignadoId(new GnUsuarios(servicioEntidad.getIdUsuarioResponsable().getId()));
            }
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
            listaServicios.add(AusCasoServicioServicio.castEntidadNegocio(servicioNegocio));
        }
        return listaServicios;
    }
    
    public static List<AusCasoServicio> castEntidadNegocioLista(List<AusCasoServicios> serviciosNegocio) {
        List<AusCasoServicio> listaServicios = new ArrayList();
        for (AusCasoServicios servicioNegocio : serviciosNegocio) {
            listaServicios.add(AusCasoServicioServicio.castEntidadNegocioCorto(servicioNegocio));
        }
        return listaServicios;
    }

    public static void realacionarUsuarioAsignadoAHistorico(List<AusServicioGestionHistorico> servicioGestionesList,
            AusCasoServicio servicioEntidad) {
        /// preguntarle a jorge
        for (AusServicioGestionHistorico historico : servicioGestionesList) {
            if (historico.getMaeEstadoId() == AusCasoServicio.ESTADO_ASIGNADO) {
                historico.setUsuarioAsignado(servicioEntidad.getAsignadoUsuariosId().getNombre());
            }
        }
    } 
}
