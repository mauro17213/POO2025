/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.solicitud;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.solicitud.GsAfiliado;
import com.saviasaludeps.savia.dominio.solicitud.GsAsignacionUsuario;
import com.saviasaludeps.savia.dominio.solicitud.GsMensaje;
import com.saviasaludeps.savia.dominio.solicitud.GsSolicitud;
import com.saviasaludeps.savia.dominio.solicitud.GsZona;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.entidades.GsAfiliados;
import com.saviasaludeps.savia.ejb.entidades.GsAsignacionUsuarios;
import com.saviasaludeps.savia.ejb.entidades.GsMensajes;
import com.saviasaludeps.savia.ejb.entidades.GsSolicitudes;
import com.saviasaludeps.savia.ejb.entidades.GsZonaUsuarios;
import com.saviasaludeps.savia.ejb.entidades.GsZonas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.solicitud.GsSolicitudRemoto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author jramirez
 */
@Stateless
@Remote(GsSolicitudRemoto.class)
@Local(GsSolicitudLocal.class)
public class GsSolicitudServicio extends GenericoServicio implements GsSolicitudLocal, GsSolicitudRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(s) FROM GsSolicitudes s ";
            if (paramConsulta.getParametroConsulta3() != null) {//Usuario Asignado
                strQuery += " WHERE s.usuariosId.id = :usuarioId ";
            } else {
                strQuery += " WHERE 1 = 1 ";
            }
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                strQuery += " AND s.fechaHoraCrea BETWEEN :fhInicio AND :fhFin ";
            } else if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND s.fechaHoraCrea >= :fhInicio ";
            } else if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += " AND s.fechaHoraCrea <= :fhFin ";
            }
            if (paramConsulta.getParametroConsulta4() != null) {//consulta de solicitud externo
                strQuery += " AND s.gsAfiliadosId.fechaNacimiento = :fhNacimiento ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND s.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += " AND s.tipo = " + e.getValue() + " ";
                            break;
                        case "gsAfiliado.documentoTipo":
                            strQuery += " AND s.gsAfiliadosId.documentoTipo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "gsAfiliado.documentoNumero":
                            strQuery += " AND s.gsAfiliadosId.documentoNumero LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "gsAfiliado.primerNombre":
                            strQuery += " AND s.gsAfiliadosId.primerNombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "gsAfiliado.segundoNombre":
                            strQuery += " AND s.gsAfiliadosId.segundoNombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "gsAfiliado.primerApellido":
                            strQuery += " AND s.gsAfiliadosId.primerApellido LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "gsAfiliado.segundoApellido":
                            strQuery += " AND s.gsAfiliadosId.segundoApellido LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += " AND s.estado = " + e.getValue() + " ";
                            break;
                        case "usuario.nombre":
                            strQuery += " AND (s.usuariosId.usuario LIKE '%" + e.getValue() + "%' "
                                    + " OR s.usuariosId.nombre LIKE '%" + e.getValue() + "%') ";
                            break;
                        case "gsZona.id":
                            if (e.getValue().equals("**")) {
                                strQuery += " AND (s.gsZonasId.id IS NULL "
                                        + "OR s.gsZonasId.id = '')";
                            } else {
                                strQuery += " AND s.gsZonasId.id = " + e.getValue() + " ";
                            }
                    }
                }
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getParametroConsulta3() != null) {//Usuario Asignado
                query.setParameter("usuarioId", paramConsulta.getParametroConsulta3());
            }
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fhInicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
                query.setParameter("fhFin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("fhInicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fhFin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            }

            if (paramConsulta.getParametroConsulta4() != null) {//Usuario Asignado
                query.setParameter("fhNacimiento", ((Date) paramConsulta.getParametroConsulta4()), TemporalType.TIMESTAMP);
            }
            cant = (int) (long) query.getSingleResult();
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
    public List<GsSolicitud> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GsSolicitud> listResult = new ArrayList();
        try {
            String strQuery = "FROM GsSolicitudes s ";
            if (paramConsulta.getParametroConsulta3() != null) {//Usuario Asignado
                strQuery += " WHERE s.usuariosId.id = :usuarioId ";
            } else {
                strQuery += " WHERE 1 = 1 ";
            }
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                strQuery += " AND s.fechaHoraCrea BETWEEN :fhInicio AND :fhFin ";
            } else if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND s.fechaHoraCrea >= :fhInicio ";
            } else if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += " AND s.fechaHoraCrea <= :fhFin ";
            }
            if (paramConsulta.getParametroConsulta4() != null) {//consulta de solicitud externo
                strQuery += " AND s.gsAfiliadosId.fechaNacimiento = :fhNacimiento ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND s.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += " AND s.tipo = " + e.getValue() + " ";
                            break;
                        case "gsAfiliado.documentoTipo":
                            strQuery += " AND s.gsAfiliadosId.documentoTipo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "gsAfiliado.documentoNumero":
                            strQuery += " AND s.gsAfiliadosId.documentoNumero LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "gsAfiliado.primerNombre":
                            strQuery += " AND s.gsAfiliadosId.primerNombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "gsAfiliado.segundoNombre":
                            strQuery += " AND s.gsAfiliadosId.segundoNombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "gsAfiliado.primerApellido":
                            strQuery += " AND s.gsAfiliadosId.primerApellido LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "gsAfiliado.segundoApellido":
                            strQuery += " AND s.gsAfiliadosId.segundoApellido LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += " AND s.estado = " + e.getValue() + " ";
                            break;
                        case "usuario.nombre":
                            strQuery += " AND (s.usuariosId.usuario LIKE '%" + e.getValue() + "%' "
                                    + " OR s.usuariosId.nombre LIKE '%" + e.getValue() + "%') ";
                            break;
                        case "gsZona.id":
                            if (e.getValue().equals("**")) {
                                strQuery += " AND (s.gsZonasId.id IS NULL "
                                        + "OR s.gsZonasId.id = '')";
                            } else {
                                strQuery += " AND s.gsZonasId.id = " + e.getValue() + " ";
                            }
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                String order = paramConsulta.getOrden().replace("gsAfiliado", "gsAfiliadosId").
                        replace("gsZona", "gsZonasId");

                strQuery += " s." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " s.fechaHoraCrea DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getParametroConsulta3() != null) {//Usuario Asignado
                query.setParameter("usuarioId", paramConsulta.getParametroConsulta3());
            }
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fhInicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
                query.setParameter("fhFin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("fhInicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fhFin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            }

            if (paramConsulta.getParametroConsulta4() != null) {//Usuario Asignado
                query.setParameter("fhNacimiento", ((Date) paramConsulta.getParametroConsulta4()), TemporalType.TIMESTAMP);
            }
            List<GsSolicitudes> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GsSolicitudes per : list) {
                listResult.add(castEntidadNegocioLista(per));
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
    public GsSolicitud consultar(int id) throws Exception {
        GsSolicitud obj = null;
        try {
            GsSolicitudes per = (GsSolicitudes) getEntityManager().find(GsSolicitudes.class, id);
            obj = castEntidadNegocio(per);
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public int insertar(GsSolicitud obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al insertar la solicitud");
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    @Override
    public void actualizar(GsSolicitud obj) throws Exception {
        try {
            String hql = "UPDATE GsSolicitudes SET"
                    + " tipo = :tipo,"
                    + " nombre = :nombre,"
                    + " descripcion = :descripcion,"
                    + " observacion = :observacion,"
                    + " estado = :estado,"
                    + " notificacion = :notificacion,"
                    + " contactoTelefono = :contactoTelefono,"
                    + " contactoCelular = :contactoCelular,"
                    + " contactoCorreoElectronico = :contactoCorreoElectronico,"
                    + " tramiteInterno = :tramiteInterno,"
                    + " respuestaReferencia = :respuestaReferencia,"
                    + " fechaHoraAtiende = :fechaHoraAtiende,"
                    + " usuarioAtiende = :usuarioAtiende,"
                    + " fechaHoraReasigna = :fechaHoraReasigna,"
                    + " usuarioReasigna = :usuarioReasigna,"
                    + " fechaHoraFinaliza = :fechaHoraFinaliza,"
                    + " usuarioFinaliza = :usuarioFinaliza"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("tipo", obj.getTipo());
            if (obj.getNombre() == null) {
                query.setParameter("nombre", null);
            } else {
                query.setParameter("nombre", obj.getNombre());
            }
            if (obj.getDescripcion() == null) {
                query.setParameter("descripcion", null);
            } else {
                query.setParameter("descripcion", obj.getDescripcion());
            }
            if (obj.getObservacion() == null) {
                query.setParameter("observacion", null);
            } else {
                query.setParameter("observacion", obj.getObservacion());
            }
            query.setParameter("estado", obj.getEstado());
            query.setParameter("notificacion", obj.getNotificacion());
            if (obj.getContactoTelefono() == null) {
                query.setParameter("contactoTelefono", null);
            } else {
                query.setParameter("contactoTelefono", obj.getContactoTelefono());
            }
            if (obj.getContactoCelular() == null) {
                query.setParameter("contactoCelular", null);
            } else {
                query.setParameter("contactoCelular", obj.getContactoCelular());
            }
            if (obj.getContactoCorreoElectronico() == null) {
                query.setParameter("contactoCorreoElectronico", null);
            } else {
                query.setParameter("contactoCorreoElectronico", obj.getContactoCorreoElectronico());
            }
            if (obj.getTramiteInterno() == null) {
                query.setParameter("tramiteInterno", null);
            } else {
                query.setParameter("tramiteInterno", obj.getTramiteInterno());
            }
            if (obj.getRespuestaReferencia() == null) {
                query.setParameter("respuestaReferencia", null);
            } else {
                query.setParameter("respuestaReferencia", obj.getRespuestaReferencia());
            }
            if (obj.getFechaHoraAtiende() == null) {
                query.setParameter("fechaHoraAtiende", null);
            } else {
                query.setParameter("fechaHoraAtiende", obj.getFechaHoraAtiende());
            }
            if (obj.getUsuarioAtiende() == null) {
                query.setParameter("usuarioAtiende", null);
            } else {
                query.setParameter("usuarioAtiende", obj.getUsuarioAtiende());
            }
            if (obj.getFechaHoraReasigna() == null) {
                query.setParameter("fechaHoraReasigna", null);
            } else {
                query.setParameter("fechaHoraReasigna", obj.getFechaHoraReasigna());
            }
            if (obj.getUsuarioReasigna() == null) {
                query.setParameter("usuarioReasigna", null);
            } else {
                query.setParameter("usuarioReasigna", obj.getUsuarioReasigna());
            }
            if (obj.getFechaHoraFinaliza() == null) {
                query.setParameter("fechaHoraFinaliza", null);
            } else {
                query.setParameter("fechaHoraFinaliza", obj.getFechaHoraFinaliza());
            }
            if (obj.getUsuarioFinaliza() == null) {
                query.setParameter("usuarioFinaliza", null);
            } else {
                query.setParameter("usuarioFinaliza", obj.getUsuarioFinaliza());
            }
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarEstado(GsSolicitud obj) throws Exception {
        try {
            String hql = "UPDATE GsSolicitudes SET"
                    + " usuariosId.id = :usuariosId,"
                    + " gsZonasId.id = :gsZonasId,"
                    + " estado = :estado,"
                    + " fechaHoraAtiende = :fechaHoraAtiende,"
                    + " usuarioAtiende = :usuarioAtiende,"
                    + " fechaHoraReasigna = :fechaHoraReasigna,"
                    + " usuarioReasigna = :usuarioReasigna,"
                    + " fechaHoraFinaliza = :fechaHoraFinaliza,"
                    + " usuarioFinaliza = :usuarioFinaliza,"
                    + " descripcion = :descripcion,"
                    + " gsMensajesId.id = :gsMensajesId"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("usuariosId", obj.getUsuario().getId());
            query.setParameter("gsZonasId", obj.getGsZona().getId());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("fechaHoraAtiende", obj.getFechaHoraAtiende());
            query.setParameter("usuarioAtiende", obj.getUsuarioAtiende());
            query.setParameter("fechaHoraReasigna", obj.getFechaHoraReasigna());
            query.setParameter("usuarioReasigna", obj.getUsuarioReasigna());
            query.setParameter("fechaHoraFinaliza", obj.getFechaHoraFinaliza());
            query.setParameter("usuarioFinaliza", obj.getUsuarioFinaliza());
            query.setParameter("descripcion", obj.getDescripcion());
            if (obj.getGsMensaje() == null) {
                query.setParameter("gsMensajesId", null);
            } else {
                query.setParameter("gsMensajesId", obj.getGsMensaje().getId());
            }
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public GsSolicitud eliminar(int id) throws Exception {
        GsSolicitud obj = null;
        try {
            GsSolicitudes per = getEntityManager().find(GsSolicitudes.class, id);
            if (per != null) {
                obj = castEntidadNegocio(per);
                getEntityManager().remove(per);
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

    public static GsSolicitud castEntidadNegocioLista(GsSolicitudes per) {
        GsSolicitud obj = new GsSolicitud();
        obj.setId(per.getId());
        //Afiliado
        if (per.getGsAfiliadosId() != null) {
            GsAfiliado afil = new GsAfiliado(
                    per.getGsAfiliadosId().getId(),
                    per.getGsAfiliadosId().getDocumentoTipo(),
                    per.getGsAfiliadosId().getDocumentoNumero(),
                    per.getGsAfiliadosId().getPrimerNombre(),
                    per.getGsAfiliadosId().getSegundoNombre(),
                    per.getGsAfiliadosId().getPrimerApellido(),
                    per.getGsAfiliadosId().getSegundoApellido()
            );
            obj.setGsAfiliado(afil);
        } else {
            obj.setGsAfiliado(new GsAfiliado());
        }
        //Usuario
        if (per.getUsuariosId() != null) {
            obj.setUsuario(
                    new Usuario(
                            per.getUsuariosId().getId(),
                            per.getUsuariosId().getUsuario(),
                            per.getUsuariosId().getNombre()
                    )
            );
        }
        //Zona
        if (per.getGsZonasId() != null) {
            obj.setGsZona(
                    new GsZona(
                            per.getGsZonasId().getId(),
                            per.getGsZonasId().getNombre(),
                            per.getGsZonasId().getDescripcion(),
                            new Ubicacion(per.getGsZonasId().getGnUbicacionesId().getId())
                    )
            );
        }
        obj.setTipo(per.getTipo());
        obj.setNombre(per.getNombre());
        obj.setDescripcion(per.getDescripcion());
        obj.setObservacion(per.getObservacion());
        obj.setEstado(per.getEstado());
        obj.setNotificacion(per.getNotificacion());
        obj.setContactoTelefono(per.getContactoTelefono());
        obj.setContactoCelular(per.getContactoCelular());
        obj.setContactoCorreoElectronico(per.getContactoCorreoElectronico());
        obj.setRespuestaReferencia(per.getRespuestaReferencia());
        //Auditoría
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioAtiende(per.getUsuarioAtiende());
        obj.setFechaHoraAtiende(per.getFechaHoraAtiende());
        obj.setUsuarioFinaliza(per.getUsuarioFinaliza());
        obj.setFechaHoraFinaliza(per.getFechaHoraFinaliza());
        return obj;
    }

    public static GsSolicitud castEntidadNegocio(GsSolicitudes per) {
        GsSolicitud obj = new GsSolicitud();
        obj.setId(per.getId());
        //Afiliado
        if (per.getGsAfiliadosId() != null) {
            GsAfiliado afiliado = new GsAfiliado();
            afiliado.setId(per.getGsAfiliadosId().getId());
            afiliado.setDocumentoTipo(per.getGsAfiliadosId().getDocumentoTipo());
            afiliado.setDocumentoNumero(per.getGsAfiliadosId().getDocumentoNumero());
            afiliado.setPrimerNombre(per.getGsAfiliadosId().getPrimerNombre());
            afiliado.setSegundoNombre(per.getGsAfiliadosId().getSegundoNombre());
            afiliado.setPrimerApellido(per.getGsAfiliadosId().getPrimerApellido());
            afiliado.setSegundoApellido(per.getGsAfiliadosId().getSegundoApellido());
            afiliado.setFechaNacimiento(per.getGsAfiliadosId().getFechaNacimiento());
            afiliado.setSexo(per.getGsAfiliadosId().getSexo());
            if (per.getGsAfiliadosId().getResidenciaUbicacionesId() != null) {
                afiliado.setResidenciaUbicacion(new Ubicacion(per.getGsAfiliadosId().getResidenciaUbicacionesId().getId()));
            }
            afiliado.setResidenciaUbicacionNombre(per.getGsAfiliadosId().getResidenciaUbicacionNombre());
            afiliado.setResidenciaDireccion(per.getGsAfiliadosId().getResidenciaDireccion());
            if (per.getGsAfiliadosId().getAtencionUbicacionesId() != null) {
                afiliado.setAtencionUbicacion(new Ubicacion(per.getGsAfiliadosId().getAtencionUbicacionesId().getId()));
            }
            afiliado.setAtencionUbicacionNombre(per.getGsAfiliadosId().getAtencionUbicacionNombre());
            obj.setGsAfiliado(afiliado);
        } else {
            obj.setGsAfiliado(new GsAfiliado());
        }
        //Usuario
        if (per.getUsuariosId() != null) {
            obj.setUsuario(
                    new Usuario(
                            per.getUsuariosId().getId(),
                            per.getUsuariosId().getUsuario(),
                            per.getUsuariosId().getNombre()
                    )
            );
        }
        //Zona
        if (per.getGsZonasId() != null) {
            obj.setGsZona(
                    new GsZona(
                            per.getGsZonasId().getId(),
                            per.getGsZonasId().getNombre(),
                            per.getGsZonasId().getDescripcion(),
                            new Ubicacion(per.getGsZonasId().getGnUbicacionesId().getId())
                    )
            );
        }
        //Mensaje respuesta
        if (per.getGsMensajesId() != null) {
            obj.setGsMensaje(new GsMensaje(
                    per.getGsMensajesId().getId(),
                    per.getGsMensajesId().getNombre(),
                    per.getGsMensajesId().getTipo(),
                    per.getGsMensajesId().getEstado()
            ));
        }
        obj.setTipo(per.getTipo());
        obj.setNombre(per.getNombre());
        obj.setDescripcion(per.getDescripcion());
        obj.setObservacion(per.getObservacion());
        obj.setEstado(per.getEstado());
        obj.setNotificacion(per.getNotificacion());
        obj.setContactoTelefono(per.getContactoTelefono());
        obj.setContactoCelular(per.getContactoCelular());
        obj.setContactoCorreoElectronico(per.getContactoCorreoElectronico());
        obj.setRespuestaReferencia(per.getRespuestaReferencia());
        obj.setTramiteInterno(per.getTramiteInterno());
        //Auditoría
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioAtiende(per.getUsuarioAtiende());
        obj.setFechaHoraAtiende(per.getFechaHoraAtiende());
        obj.setUsuarioReasigna(per.getUsuarioReasigna());
        obj.setFechaHoraReasigna(per.getFechaHoraReasigna());
        obj.setUsuarioFinaliza(per.getUsuarioFinaliza());
        obj.setFechaHoraFinaliza(per.getFechaHoraFinaliza());
        return obj;
    }

    public static GsSolicitudes castNegocioEntidad(GsSolicitud obj) {
        GsSolicitudes per = new GsSolicitudes();
        per.setId(obj.getId());
        if (obj.getGsAfiliado() != null) {
            per.setGsAfiliadosId(new GsAfiliados(obj.getGsAfiliado().getId()));
        }
        if (obj.getGsZona() != null && (obj.getGsZona().getId() != null
                && obj.getGsZona().getId() > 0)) {
            per.setGsZonasId(new GsZonas(obj.getGsZona().getId()));
        }
        if (obj.getUsuario() != null && (obj.getUsuario().getId() != null
                && obj.getUsuario().getId() > 0)) {
            per.setUsuariosId(new GnUsuarios(obj.getUsuario().getId()));
        }
        //Mensaje respuesta
        if (obj.getGsMensaje() != null && obj.getGsMensaje().getId() != null) {
            per.setGsMensajesId(new GsMensajes(
                    obj.getGsMensaje().getId()
            ));
        }
        per.setTipo(obj.getTipo());
        per.setNombre(obj.getNombre());
        per.setDescripcion(obj.getDescripcion());
        per.setObservacion(obj.getObservacion());
        per.setEstado(obj.getEstado());
        per.setNotificacion(obj.getNotificacion());
        per.setContactoTelefono(obj.getContactoTelefono());
        per.setContactoCelular(obj.getContactoCelular());
        per.setContactoCorreoElectronico(obj.getContactoCorreoElectronico());
        per.setTramiteInterno(obj.getTramiteInterno());
        per.setRespuestaReferencia(obj.getRespuestaReferencia());
        //Auditoría
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioAtiende(obj.getUsuarioAtiende());
        per.setFechaHoraAtiende(obj.getFechaHoraAtiende());
        per.setUsuarioReasigna(obj.getUsuarioReasigna());
        per.setFechaHoraReasigna(obj.getFechaHoraReasigna());
        per.setUsuarioFinaliza(obj.getUsuarioFinaliza());
        per.setFechaHoraFinaliza(obj.getFechaHoraFinaliza());
        return per;
    }

    @Override
    public GsAsignacionUsuario proximaAsignacion(int tipoSolicitud, int ubicacionId) throws Exception {
        GsZonaLocal zonaServicio = new GsZonaServicio();
        GsAsignacionUsuario objRes = null;
        //Consulta Zona y lista de usuarios de la zona
        GsZonas zonas;
        List<GsZonaUsuarios> listaUsuarios = new ArrayList();
        zonas = zonaServicio.consultarPorUbicacion(ubicacionId, getEntityManager());
        if (zonas != null) {
            listaUsuarios = zonaServicio.consultarUsuariosActivos(zonas.getId(), tipoSolicitud, getEntityManager());
            if (listaUsuarios.isEmpty()) {
                zonas = null;
            }
        }
        if (zonas == null) {
            zonas = zonaServicio.consultarPorDefecto(getEntityManager());
            if (zonas == null) {
                //ERROR FINAL
                throw new Exception("No fue posible asignar el caso a una zona");
            } else {
                listaUsuarios = zonaServicio.consultarUsuariosActivos(zonas.getId(), tipoSolicitud, getEntityManager());
                if (listaUsuarios.isEmpty()) {
                    //ERROR FINAL
                    throw new Exception("No fue posible asignar el caso a un usuario");
                }
            }
        }
        //Consulta Asignación Usuario
        String strQuery = "FROM GsAsignacionUsuarios"
                + " WHERE tipoSolicitud = :tipoSolicitud"
                //                + " AND ubicacionesId = :ubicacionesId"
                + " AND zonasId = :zonasId";
        GsAsignacionUsuarios perUsu;
        try {
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("tipoSolicitud", tipoSolicitud);
//            query.setParameter("ubicacionesId", ubicacionId);
            query.setParameter("zonasId", zonas.getId());
            perUsu = (GsAsignacionUsuarios) query.getSingleResult();
        } catch (NoResultException e) {
            perUsu = null;
        } catch (Exception e) {
            perUsu = null;
        }
        if (perUsu == null) {
            perUsu = new GsAsignacionUsuarios();
            perUsu.setTipoSolicitud(tipoSolicitud);
            GsZonaUsuarios usu = listaUsuarios.get(0);
            perUsu.setZonasId(usu.getGsZonasId().getId());
            perUsu.setUsuarioId(usu.getGnUsuariosId().getId());
            perUsu.setUbicacionesId(usu.getGsZonasId().getGnUbicacionesId().getId());
        } else {
            if (listaUsuarios.size() == 1) {
                GsZonaUsuarios usu = listaUsuarios.get(0);
                perUsu.setUsuarioId(usu.getGnUsuariosId().getId());
                perUsu.setZonasId(usu.getGsZonasId().getId());
                perUsu.setUbicacionesId(usu.getGsZonasId().getGnUbicacionesId().getId());
            } else {
                boolean encontro = false;
                for (int i = 0; i < listaUsuarios.size(); i++) {
                    GsZonaUsuarios usu = listaUsuarios.get(i);
                    if (usu.getGnUsuariosId().getId() == perUsu.getUsuarioId()) {
                        if (i == listaUsuarios.size() - 1) {//El último
                            usu = listaUsuarios.get(0);
                        } else {
                            usu = listaUsuarios.get(i + 1);
                        }
                        perUsu.setUsuarioId(usu.getGnUsuariosId().getId());
                        perUsu.setZonasId(usu.getGsZonasId().getId());
                        perUsu.setUbicacionesId(usu.getGsZonasId().getGnUbicacionesId().getId());
                        encontro = true;
                        break;
                    }
                }
                if (!encontro) {
                    GsZonaUsuarios usu = listaUsuarios.get(0);
                    perUsu.setUsuarioId(usu.getGnUsuariosId().getId());
                    perUsu.setZonasId(usu.getGsZonasId().getId());
                    perUsu.setUbicacionesId(usu.getGsZonasId().getGnUbicacionesId().getId());
                }
            }
        }
        if (perUsu.getId() == null) {
            perUsu.setId(getEntityManager().merge(perUsu).getId());
        } else {
            getEntityManager().merge(perUsu);
        }
        objRes = new GsAsignacionUsuario();
        objRes.setId(perUsu.getId());
        objRes.setTipoSolicitud(perUsu.getTipoSolicitud());
        objRes.setUsuarioId(perUsu.getUsuarioId());
        objRes.setZonasId(perUsu.getZonasId());
        objRes.setUbicacionesId(perUsu.getUbicacionesId());
        return objRes;
    }

    @Override
    public List<GsMensaje> consultarMensajesPorTipoEstado(int tipo, int estado) throws Exception {
        List<GsMensaje> listResult = new ArrayList();
        try {
            String strQuery = "FROM GsMensajes m "
                    + "WHERE m.tipo = :tipo "
                    + "AND m.estado = :estado "
                    + "ORDER BY m.nombre ASC ";
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("tipo", tipo);
            query.setParameter("estado", estado);
            List<GsMensajes> list = query.getResultList();
            GsMensaje obj;
            for (GsMensajes per : list) {
                obj = new GsMensaje();
                obj.setId(per.getId());
                obj.setTipo(per.getTipo());
                obj.setEstado(per.getEstado());
                obj.setNombre(per.getNombre());
                obj.setEncabezado(per.getEncabezado());
                obj.setMensajeLargo(per.getMensajeLargo());
                obj.setMensajeCorto(per.getMensajeCorto());
                listResult.add(obj);
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
    public int consultarCantidadListaExterna(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(s) FROM GsSolicitudes s "
                    + " WHERE s.gsAfiliadosId.documentoTipo = :documentoTipo "
                    + " AND s.gsAfiliadosId.documentoNumero = :documentoNumero "
                    + " AND s.gsAfiliadosId.fechaNacimiento = :fhNacimiento ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND s.id LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("documentoTipo", paramConsulta.getParametroConsulta1());
            query.setParameter("documentoNumero", paramConsulta.getParametroConsulta2());
            query.setParameter("fhNacimiento", ((Date) paramConsulta.getParametroConsulta3()), TemporalType.TIMESTAMP);
            cant = (int) (long) query.getSingleResult();
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
    public List<GsSolicitud> consultarListaExterna(ParamConsulta paramConsulta) throws Exception {
        List<GsSolicitud> listResult = new ArrayList();
        try {
            String strQuery = "FROM GsSolicitudes s "
                    + " WHERE s.gsAfiliadosId.documentoTipo = :documentoTipo "
                    + " AND s.gsAfiliadosId.documentoNumero = :documentoNumero "
                    + " AND s.gsAfiliadosId.fechaNacimiento = :fhNacimiento ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND s.id LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                String order = paramConsulta.getOrden().replace("gsAfiliado", "gsAfiliadosId").
                        replace("gsZona", "gsZonasId");

                strQuery += " s." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " s.fechaHoraCrea DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("documentoTipo", paramConsulta.getParametroConsulta1());
            query.setParameter("documentoNumero", paramConsulta.getParametroConsulta2());
            query.setParameter("fhNacimiento", ((Date) paramConsulta.getParametroConsulta3()), TemporalType.TIMESTAMP);
            List<GsSolicitudes> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GsSolicitudes per : list) {
                listResult.add(castEntidadNegocioLista(per));
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
}
