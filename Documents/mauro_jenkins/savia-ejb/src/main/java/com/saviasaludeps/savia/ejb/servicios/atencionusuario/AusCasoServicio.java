/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.atencionusuario;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersona;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.atencionusuario.AusSede;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCaso;
import com.saviasaludeps.savia.ejb.entidades.AusCargaMasivas;
import com.saviasaludeps.savia.ejb.entidades.AusCasos;
import com.saviasaludeps.savia.ejb.entidades.AusPersonas;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.entidades.GnSedes;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.servicios.administracion.UbicacionServicio;
import com.saviasaludeps.savia.ejb.servicios.administracion.UsuarioServicio;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.utilidades.Util;
import com.saviasaludeps.savia.negocio.atencionusuario.AusCasoRemoto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
 * @author raul-palacios
 */
@Stateless
@Remote(AusCasoRemoto.class)
@Local(AusCasoLocal.class)
public class AusCasoServicio extends GenericoServicio implements AusCasoLocal, AusCasoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(DISTINCT c) FROM AusCasos c "
                    + " LEFT JOIN c.ausCasoServiciosList ls WHERE c.gnEmpresasId.id = :gnEmpresasId ";
            boolean consultaConFechas = false;
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta3() != null) {
                strQuery += " AND c.fechaHoraCrea BETWEEN :fh_inicio AND :fh_fin ";
                consultaConFechas = true;
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicado":
                            strQuery += "AND c.radicado LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeSolicitudEstadoId":
                            String cadena = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND c.maeSolicitudEstadoId IN ( " + cadena + ") ";
                            break;
                        case "asuPersonasId.mae_tipo_documento_id":
                            strQuery += "AND c.ausPersonasId.maeTipoDocumentoId =" + (String) e.getValue() + " ";
                            break;
                        case "asuPersonasId.documento":
                            strQuery += "AND c.ausPersonasId.documento  LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "asuPersonasId.nombres":
                            strQuery += "AND c.ausPersonasId.nombres LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "asuPersonasId.apellidos":
                            strQuery += "AND c.ausPersonasId.apellidos LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "responsableUsuariosId.nombre":
                            strQuery += "AND c.gnUsuariosResponsableId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        //2025-04-04 jyperez adición filtro usuario cierre
                        case "usuarioCierre.nombre":
                            strQuery += "AND c.gnUsuarioCierreId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "pendienteCierre":
                            if (((String) e.getValue()).equals("1")) {
                                strQuery += "AND c.cantidadServicios > 0 AND c.cantidadServiciosCerrados > 0 AND (c.cantidadServicios = c.cantidadServiciosCerrados) ";
                            } else {
                                strQuery += "AND ( IFNULL(c.cantidadServicios,0) <= 0 OR IFNULL(c.cantidadServiciosCerrados,0) <= 0 OR (IFNULL(c.cantidadServicios,0) <> IFNULL(c.cantidadServiciosCerrados,0))) ";
                            }
                            break;
                        case "casoMedicamento":
                            if (((String) e.getValue()).equals("1")) {
                                strQuery += "AND (ls.tipoTecnologia = " + AusCaso.TIPO_TECNOLOGIA_MEDICAMENTO + ") ";
                            } else {
                                strQuery += "AND (COALESCE(ls.tipoTecnologia,0) <> " + AusCaso.TIPO_TECNOLOGIA_MEDICAMENTO + ") ";
                            }
                            break;
                        case "fechaVencimiento":
                            strQuery += "AND c.fechaVencimiento < '" + (String) e.getValue() + "' ";
                            break;
                        case "maeSolicitudEstado2":
                            strQuery += "AND c.maeSolicitudEstadoId = " + (String) e.getValue() + " ";
                            break;
                        case "maeSolicitudOrigenId":
                            String cadenaOrigen = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND c.maeSolicitudOrigenId IN ( " + cadenaOrigen + ") ";
                            break;
                        case "maeSolicitudRiesgoVidalId":
                            String cadenaRiesgo = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND c.maeSolicitudRiesgoVidalId IN ( " + cadenaRiesgo + ") ";
                            break;
                        case "cantidadServicios":
                            strQuery += "AND c.cantidadServicios = (CASE WHEN c.cantidadServicios = c.cantidadServiciosCerrados THEN " + (String) e.getValue() + " END ) ";
                            break;
                        case "usuarioCierra":
                            strQuery += "AND c.usuarioCierra LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND c.gnUsuariosResponsableId.id = " + (int) paramConsulta.getParametroConsulta2() + " ";
            }
            strQuery += "AND c.borrado = 0 ";
            if (consultaConFechas) {
                Calendar calIni = GenericoServicio.addHourToParam1(paramConsulta.getParametroConsulta1());
                Calendar calFin = GenericoServicio.addHourToParam2(paramConsulta.getParametroConsulta3());
                cant = (int) (long) getEntityManager().createQuery(strQuery)
                        .setParameter("gnEmpresasId", paramConsulta.getEmpresaId())
                        .setParameter("fh_inicio", ((Date) calIni.getTime()), TemporalType.TIMESTAMP)
                        .setParameter("fh_fin", ((Date) calFin.getTime()), TemporalType.TIMESTAMP)
                        .getSingleResult();
            } else {
                cant = (int) (long) getEntityManager().createQuery(strQuery)
                        .setParameter("gnEmpresasId", paramConsulta.getEmpresaId())
                        .getSingleResult();
            }

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
    public List<AusCaso> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AusCaso> listResult = new ArrayList();
        try {
            String strQuery = "SELECT DISTINCT(c) FROM AusCasos c "
                    + " LEFT JOIN c.ausCasoServiciosList ls WHERE c.gnEmpresasId.id = :gnEmpresasId ";
            boolean consultaConFechas = false;
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta3() != null) {
                strQuery += " AND c.fechaHoraCrea BETWEEN :fh_inicio AND :fh_fin ";
                consultaConFechas = true;
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicado":
                            strQuery += "AND c.radicado LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeSolicitudEstadoId":
                            String cadena = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND c.maeSolicitudEstadoId IN ( " + cadena + ") ";
                            break;
                        case "asuPersonasId.mae_tipo_documento_id":
                            strQuery += "AND c.ausPersonasId.maeTipoDocumentoId =" + (String) e.getValue() + " ";
                            break;
                        case "asuPersonasId.documento":
                            strQuery += "AND c.ausPersonasId.documento  LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "asuPersonasId.nombres":
                            strQuery += "AND c.ausPersonasId.nombres LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "asuPersonasId.apellidos":
                            strQuery += "AND c.ausPersonasId.apellidos LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "responsableUsuariosId.nombre":
                            strQuery += "AND c.gnUsuariosResponsableId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        //2025-04-04 jyperez adición filtro usuario cierre
                        case "usuarioCierre.nombre":
                            strQuery += "AND c.gnUsuarioCierreId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "pendienteCierre":
                            if (((String) e.getValue()).equals("1")) {
                                strQuery += "AND c.cantidadServicios > 0 AND c.cantidadServiciosCerrados > 0 AND (c.cantidadServicios = c.cantidadServiciosCerrados) ";
                            } else {
                                strQuery += "AND ( IFNULL(c.cantidadServicios,0) <= 0 OR IFNULL(c.cantidadServiciosCerrados,0) <= 0 OR (IFNULL(c.cantidadServicios,0) <> IFNULL(c.cantidadServiciosCerrados,0))) ";
                            }
                            break;
                        case "casoMedicamento":
                            if (((String) e.getValue()).equals("1")) {
                                strQuery += "AND (ls.tipoTecnologia = " + AusCaso.TIPO_TECNOLOGIA_MEDICAMENTO + ") ";
                            } else {
                                strQuery += "AND (COALESCE(ls.tipoTecnologia,0) <> " + AusCaso.TIPO_TECNOLOGIA_MEDICAMENTO + ") ";
                            }
                            break;
                        case "fechaVencimiento":
                            strQuery += "AND c.fechaVencimiento < '" + (String) e.getValue() + "' ";
                            break;
                        case "maeSolicitudEstado2":
                            strQuery += "AND c.maeSolicitudEstadoId = " + (int) e.getValue() + " ";
                            break;
                        case "maeSolicitudOrigenId":
                            String cadenaOrigen = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND c.maeSolicitudOrigenId IN ( " + cadenaOrigen + ") ";
                            break;
                        case "maeSolicitudRiesgoVidalId":
                            String cadenaRiesgo = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND c.maeSolicitudRiesgoVidalId IN ( " + cadenaRiesgo + ") ";
                            break;
                        case "cantidadServicios":
                            strQuery += "AND c.cantidadServicios = (CASE WHEN c.cantidadServicios = c.cantidadServiciosCerrados THEN " + (String) e.getValue() + " END ) ";
                            break;
                        case "usuarioCierra":
                            strQuery += "AND c.usuarioCierra LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND c.gnUsuariosResponsableId.id = " + (int) paramConsulta.getParametroConsulta2() + " ";
            }
            strQuery += "AND c.borrado = 0 ";

            if (paramConsulta.getOrden() != null) {
                if (paramConsulta.getOrden().equals("cantidadServicios")) {
                    strQuery += "AND c.cantidadServicios = c.cantidadServiciosCerrados  ";
                    strQuery += "ORDER BY ";
                    strQuery += "c.fechaHoraCrea DESC";
                } else {
                    strQuery += "ORDER BY ";
                    String order = paramConsulta.getOrden().
                            replace("asuPersonasId.mae_tipo_documento_id", "ausPersonasId.maeTipoDocumentoId").
                            replace("asuPersonasId.documento", "ausPersonasId.documento").
                            replace("asuPersonasId.nombres", "ausPersonasId.nombres").
                            replace("asuPersonasId.apellidos", "ausPersonasId.apellidos").
                            replace("responsableUsuariosId.nombre", "gnUsuariosResponsableId.nombre").
                            replace("usuarioCierre.nombre", "gnUsuarioCierreId.nombre").
                            replace("maeSolicitudEstado2", "maeSolicitudEstadoId");
                    strQuery += "c." + order + " "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                }

            } else {
                strQuery += "ORDER BY ";
                strQuery += "c.fechaHoraCrea DESC";
            }
            List<AusCasos> list = null;
            if (consultaConFechas) {
                Calendar calIni = GenericoServicio.addHourToParam1(paramConsulta.getParametroConsulta1());
                Calendar calFin = GenericoServicio.addHourToParam2(paramConsulta.getParametroConsulta3());
                list = getEntityManager().createQuery(strQuery)
                        .setFirstResult(paramConsulta.getPrimerRegistro())
                        .setMaxResults(paramConsulta.getRegistrosPagina())
                        .setParameter("gnEmpresasId", paramConsulta.getEmpresaId())
                        .setParameter("fh_inicio", ((Date) calIni.getTime()), TemporalType.TIMESTAMP)
                        .setParameter("fh_fin", ((Date) calFin.getTime()), TemporalType.TIMESTAMP)
                        .getResultList();
            } else {
                list = getEntityManager().createQuery(strQuery)
                        .setFirstResult(paramConsulta.getPrimerRegistro())
                        .setMaxResults(paramConsulta.getRegistrosPagina())
                        .setParameter("gnEmpresasId", paramConsulta.getEmpresaId())
                        .getResultList();
            }
            for (AusCasos casoNegocion : list) {
                listResult.add(castEntidadNegocioLista(casoNegocion));
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
    public List<AusCaso> consultarPorFiltros(ParamConsulta paramConsulta) throws Exception {
        List<AusCaso> listResult = new ArrayList();
        try {
            String strQuery = "FROM AusCasos c "
                    + " WHERE 1 = 1 ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipoDocumento":
                            strQuery += " AND c.ausPersonasId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "documento":
                            strQuery += " AND c.ausPersonasId.documento = " + e.getValue() + " ";
                            break;
                        case "idCaso":
                            strQuery += " AND c.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += " AND c.borrado = 0 ";
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "c." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "c.fechaHoraCrea DESC";
            }
            List<AusCasos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();

            for (AusCasos casoNegocion : list) {
                listResult.add(castEntidadNegocio(casoNegocion));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public List<Usuario> consultarTodosUsuarios() throws Exception {
        List<Usuario> listResult = new ArrayList();
        String strQuery = "FROM GnUsuarios"
                + " ORDER BY nombre, usuario";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnUsuarios> list = query.getResultList();
            for (GnUsuarios obj : list) {
                listResult.add(CastEntidadNegocionSimple(obj));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception("Consulta de registros", e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public AusCaso consultarCasosPorRadicado(String radicado) throws Exception {
        AusCaso afiliadoResult = new AusCaso();
        try {
            String strQuery = "SELECT p "
                    + "FROM AusCasos p "
                    + "WHERE p.id > 0 "
                    + "AND p.radicado = '" + radicado + "' ";
            List<AusCasos> obj = getEntityManager().createQuery(strQuery).getResultList();
            if (!obj.isEmpty()) {
                afiliadoResult = castEntidadNegocio(obj.get(0));
            }
        } catch (NoResultException e) {
            afiliadoResult = null;
        } catch (Exception e) {
            afiliadoResult = null;
        } finally {
            cerrarEntityManager();
        }
        return afiliadoResult;
    }

    @Override
    public AusCaso consultarCasoNoBorrado(Integer id) throws Exception {
        AusCaso afiliadoResult = new AusCaso();
        try {
            String strQuery = "SELECT p "
                    + "FROM AusCasos p "
                    + "WHERE p.id > 0 "
                    + "AND p.borrado = 0"
                    + "AND p.id = '" + id + "' ";
            List<AusCasos> obj = getEntityManager().createQuery(strQuery).getResultList();
            if (!obj.isEmpty()) {
                afiliadoResult = castEntidadNegocio(obj.get(0));
            }
        } catch (NoResultException e) {
            afiliadoResult = null;
        } catch (Exception e) {
            afiliadoResult = null;
        } finally {
            cerrarEntityManager();
        }
        return afiliadoResult;
    }

    @Override
    public int insertar(AusCaso caso) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(caso)).getId();
            caso.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al insertar un caso en gestión casos");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(AusCaso obj) throws Exception {
        try {
            String hql = "UPDATE AusCasos SET"
                    + " ausPersonasId.id = :ausPersonasId,"
                    + " maeSolicitudEstadoId = :maeSolicitudEstadoId,"
                    + " maeSolicitudEstadoCodigo = :maeSolicitudEstadoCodigo,"
                    + " maeSolicitudEstadoValor = :maeSolicitudEstadoValor,"
                    + " maeSolicitudTipoId = :maeSolicitudTipoId,"
                    + " maeSolicitudTipoCodigo = :maeSolicitudTipoCodigo,"
                    + " maeSolicitudTipoValor = :maeSolicitudTipoValor,"
                    + " maeSolicitudOrigenId = :maeSolicitudOrigenId,"
                    + " maeSolicitudOrigenCodigo = :maeSolicitudOrigenCodigo,"
                    + " maeSolicitudOrigenValor = :maeSolicitudOrigenValor,"
                    + " maeCanalSupersaludId = :maeCanalSupersaludId,"
                    + " maeCanalSupersaludCodigo = :maeCanalSupersaludCodigo,"
                    + " maeCanalSupersaludValor = :maeCanalSupersaludValor,"
                    + " radicado = :radicado,"
                    + " pertinencia = :pertinencia,"
                    + " maeSolicitudPrioridadId = :maeSolicitudPrioridadId,"
                    + " maeSolicitudPrioridadCodigo = :maeSolicitudPrioridadCodigo,"
                    + " maeSolicitudPrioridadValor = :maeSolicitudPrioridadValor,"
                    + " maeSolicitudEnteControlId = :maeSolicitudEnteControlId,"
                    + " maeSolicitudEnteControlCodigo = :maeSolicitudEnteControlCodigo,"
                    + " maeSolicitudEnteControValor = :maeSolicitudEnteControlValor,"
                    + " maeSolicitudRiesgoVidalId = :maeSolicitudRiesgoVidalId,"
                    + " maeSolicitudRiesgoVidalCodigo = :maeSolicitudRiesgoVidalCodigo,"
                    + " maeSolicitudRiesgoVidalValor = :maeSolicitudRiesgoVidalValor,"
                    + " fechaNotificacion = :fechaNotificacion,"
                    + " fechaVencimiento = :fechaVencimiento,"
                    // + " afiliadoEdad = :afiliadoEdad,"
                    + " multireparto = :multireparto,"
                    + " instruccion = :instruccion,"
                    + " reabierto = :reabierto,"
                    + " falloTutela = :falloTutela,"
                    + " redireccionado = :redireccionado,"
                    //+ ((obj.getPeticionario() != null && obj.getPeticionario().getDocumento() != null) ? " ausAccionantePersonasId.id = :accionanteId, accionanteEdad = :accionanteEdad, parentesco = :parentesco," : "")
                    + ((obj.getPeticionario() != null && obj.getPeticionario().getDocumento() != null) ? " ausAccionantePersonasId.id = :accionanteId, parentesco = :parentesco," : "")
                    + ((obj.getUbicacion() != null && obj.getUbicacion().getId() != null && obj.getUbicacion().getId() > 0) ? " gnUbicacionesId.id = :gnUbicacionesId," : "")
                    + ((obj.getSedesId() != null && obj.getSedesId().getId() != null && obj.getSedesId().getId() > 0) ? " gnSedesId.id = :sedesId," : "")
                    + " gnUsuariosResponsableId.id = :gnUsuariosResponsableId,"
                    + " fechaHoraResponsable = :fechaHoraResponsable,"
                    + " maeTecnologiaAltoCostoId = :maeTecnologiaAltoCostoId,"
                    + " maeTecnologiaAltoCostoCodigo = :maeTecnologiaAltoCostoCodigo,"
                    + " maeTecnologiaAltoCostoValor = :maeTecnologiaAltoCostoValor,"
                    + " maeMotivoEspecificoId = :maeMotivoEspecificoId,"
                    + " maeMotivoEspecificoCodigo = :maeMotivoEspecificoCodigo,"
                    + " maeMotivoEspecificoValor = :maeMotivoEspecificoValor,"
                    + " maeTipoMotivoEspecificoId = :maeTipoMotivoEspecificoId,"
                    + " maeTipoMotivoEspecificoCodigo = :maeTipoMotivoEspecificoCodigo,"
                    + " maeTipoMotivoEspecificoValor = :maeTipoMotivoEspecificoValor,"
                    + " maeSubtipoMotivoEspecificoId = :maeSubtipoMotivoEspecificoId,"
                    + " maeSubtipoMotivoEspecificoCodigo = :maeSubtipoMotivoEspecificoCodigo,"
                    + " maeSubtipoMotivoEspecificoValor = :maeSubtipoMotivoEspecificoValor,"
                    + " proteccionDatos = :proteccionDatos,"
                    + " usuarioPluripatologico = :usuarioPluripatologico,"
                    + " fechaCreacionCaso = :fechaCreacionCaso,"
                    //2025-04-03 jyperez se debe actualizar el campo del usuario cierra, ya que cada vez que lo modifiquemos o gestionemos se actualizará al usuario responsable
                    + " usuarioCierra = :usuarioCierra,"
                    + ((obj.getUsuarioCierre()!= null && obj.getUsuarioCierre().getId() != null && obj.getUsuarioCierre().getId() > 0) ? " gnUsuarioCierreId.id = :gnUsuarioCierreId," : "")
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("ausPersonasId", obj.getAusPersonasId().getId());
            query.setParameter("maeSolicitudEstadoId", obj.getMaeSolicitudEstadoId());
            query.setParameter("maeSolicitudEstadoCodigo", obj.getMaeSolicitudEstadoCodigo());
            query.setParameter("maeSolicitudEstadoValor", obj.getMaeSolicitudEstadoValor());
            query.setParameter("maeSolicitudTipoId", obj.getMaeSolicitudTipoId());
            query.setParameter("maeSolicitudTipoCodigo", obj.getMaeSolicitudTipoCodigo());
            query.setParameter("maeSolicitudTipoValor", obj.getMaeSolicitudTipoValor());
            query.setParameter("maeSolicitudOrigenId", obj.getMaeSolicitudOrigenId());
            query.setParameter("maeSolicitudOrigenCodigo", obj.getMaeSolicitudOrigenCodigo());
            query.setParameter("maeSolicitudOrigenValor", obj.getMaeSolicitudOrigenValor());
            query.setParameter("maeCanalSupersaludId", obj.getMaeCanalSupersaludId());
            query.setParameter("maeCanalSupersaludCodigo", obj.getMaeCanalSupersaludCodigo());
            query.setParameter("maeCanalSupersaludValor", obj.getMaeCanalSupersaludValor());
            query.setParameter("radicado", obj.getRadicado());
            query.setParameter("pertinencia", obj.getPertinencia());
            query.setParameter("maeSolicitudPrioridadId", obj.getMaeSolicitudPrioridadId());
            query.setParameter("maeSolicitudPrioridadCodigo", obj.getMaeSolicitudPrioridadCodigo());
            query.setParameter("maeSolicitudPrioridadValor", obj.getMaeSolicitudPrioridadValor());
            query.setParameter("maeSolicitudEnteControlId", obj.getMaeSolicitudEnteControlId());
            query.setParameter("maeSolicitudEnteControlCodigo", obj.getMaeSolicitudEnteControlCodigo());
            query.setParameter("maeSolicitudEnteControlValor", obj.getMaeSolicitudEnteControlValor());
            query.setParameter("maeSolicitudRiesgoVidalId", obj.getMaeSolicitudRiesgoVidalId());
            query.setParameter("maeSolicitudRiesgoVidalCodigo", obj.getMaeSolicitudRiesgoVidalCodigo());
            query.setParameter("maeSolicitudRiesgoVidalValor", obj.getMaeSolicitudRiesgoVidalValor());
            query.setParameter("fechaNotificacion", obj.getFechaNotificacion());
            query.setParameter("fechaVencimiento", obj.getFechaVencimiento());
            // query.setParameter("afiliadoEdad", obj.getAusPersonasId().getEdad());
            query.setParameter("multireparto", obj.getMultireparto());
            query.setParameter("instruccion", obj.getInstruccion());
            query.setParameter("reabierto", obj.getReabierto());
            query.setParameter("falloTutela", obj.getFalloTutela());
            query.setParameter("redireccionado",obj.getRedireccionado());
            if (obj.getPeticionario() != null && obj.getPeticionario().getDocumento() != null) {
                query.setParameter("accionanteId", obj.getPeticionario().getId());
                //query.setParameter("accionanteEdad", obj.getPeticionario().getEdad());
                query.setParameter("parentesco", obj.getParentesco());
            }

            if (obj.getUbicacion() != null && obj.getUbicacion().getId() != null && obj.getUbicacion().getId() > 0) {
                query.setParameter("gnUbicacionesId", obj.getUbicacion().getId());
            }
            if (obj.getSedesId() != null && obj.getSedesId().getId() != null && obj.getSedesId().getId() > 0) {
                query.setParameter("sedesId", obj.getSedesId().getId());
            }
            query.setParameter("gnUsuariosResponsableId", obj.getResponsableUsuariosId().getId());
            query.setParameter("fechaHoraResponsable", obj.getFechaHoraModifica());

            query.setParameter("maeTecnologiaAltoCostoId", obj.getMaeTecnologiaAltoCostoId());
            query.setParameter("maeTecnologiaAltoCostoCodigo", obj.getMaeTecnologiaAltoCostoCodigo());
            query.setParameter("maeTecnologiaAltoCostoValor", obj.getMaeTecnologiaAltoCostoValor());

            query.setParameter("maeMotivoEspecificoId", obj.getMaeMotivoEspecificoId());
            query.setParameter("maeMotivoEspecificoCodigo", obj.getMaeMotivoEspecificoCodigo());
            query.setParameter("maeMotivoEspecificoValor", obj.getMaeMotivoEspecificoValor());
            query.setParameter("maeTipoMotivoEspecificoId", obj.getMaeTipoMotivoEspecificoId());
            query.setParameter("maeTipoMotivoEspecificoCodigo", obj.getMaeTipoMotivoEspecificoCodigo());
            query.setParameter("maeTipoMotivoEspecificoValor", obj.getMaeTipoMotivoEspecificoValor());
            query.setParameter("maeSubtipoMotivoEspecificoId", obj.getMaeSubtipoMotivoEspecificoId());
            query.setParameter("maeSubtipoMotivoEspecificoCodigo", obj.getMaeSubtipoMotivoEspecificoCodigo());
            query.setParameter("maeSubtipoMotivoEspecificoValor", obj.getMaeSubtipoMotivoEspecificoValor());
            query.setParameter("proteccionDatos", obj.getProteccionDatos());
            query.setParameter("usuarioPluripatologico", obj.getUsuarioPluripatologico());
            query.setParameter("fechaCreacionCaso", obj.getFechaCreacionCaso());
            //2025-04-03 jyperez se debe actualizar el campo del usuario cierra, ya que cada vez que lo modifiquemos o gestionemos se actualizará al usuario responsable
            query.setParameter("usuarioCierra", obj.getUsuarioCierra());
            if (obj.getUsuarioCierre()!= null && obj.getUsuarioCierre().getId() != null && obj.getUsuarioCierre().getId() > 0) {
                query.setParameter("gnUsuarioCierreId", obj.getUsuarioCierre().getId());
            }
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
//            int id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
//            obj.setId(id);
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarCantidadServicios(AusCaso obj) throws Exception {
        try {
            String hql = "UPDATE AusCasos SET"
                    + " cantidadServicios = :cantidadServicios,"
                    + " cantidadServiciosCerrados = :cantidadServiciosCerrados "
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("cantidadServicios", obj.getCantidadServicios());
            query.setParameter("cantidadServiciosCerrados", obj.getCantidadServiciosCerrados());
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
    public void actualizarCantidadServiciosBorrado(AusCaso obj) throws Exception {
        try {
            String hql = "UPDATE AusCasos SET"
                    + " cantidadServicios = :cantidadServicios"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("cantidadServicios", obj.getCantidadServicios());
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
    public void actualizarCantidadServiciosCerrados(AusCaso obj) throws Exception {
        try {
            String hql = "UPDATE AusCasos SET"
                    + " cantidadServiciosCerrados = :cantidadServiciosCerrados "
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("cantidadServiciosCerrados", obj.getCantidadServiciosCerrados());
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
    public void actualizarBorrarCaso(AusCaso obj) throws Exception {
        try {
            String hql = "UPDATE AusCasos SET"
                    + " borrado = :borrado,"
                    + " observacionBorra = :observacionBorra,"
                    + " usuarioBorra = :usuarioBorra,"
                    + " terminalBorra = :terminalBorra,"
                    + " fechaHoraBorra = :fechaHoraBorra"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("borrado", obj.getBorrado());
            query.setParameter("observacionBorra", obj.getObservacionBorrar());
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
    public void actualizarReabrirCaso(AusCaso obj) throws Exception {
        try {
            String hql = "UPDATE AusCasos SET"
                    + " maeMotivoReabreId = :maeMotivoReabreId,"
                    + " maeMotivoReabreCodigo = :maeMotivoReabreCodigo,"
                    + " maeMotivoReabreValor = :maeMotivoReabreValor,"
                    + " observacionReabre = :observacionReabre,"
                    + " reabierto = :reabierto,"
                    + " maeSolicitudEstadoId = :maeSolicitudEstadoId,"
                    + " maeSolicitudEstadoCodigo = :maeSolicitudEstadoCodigo,"
                    + " maeSolicitudEstadoValor = :maeSolicitudEstadoValor,"
                    + " usuarioReabre = :usuarioReabre,"
                    + " terminalReabre = :terminalReabre,"
                    + " fechaHoraReabre = :fechaHoraReabre"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("maeMotivoReabreId", obj.getMaeMotivoReabreId());
            query.setParameter("maeMotivoReabreCodigo", obj.getMaeMotivoReabreCodigo());
            query.setParameter("maeMotivoReabreValor", obj.getMaeMotivoReabreValor());
            query.setParameter("observacionReabre", obj.getObservacionReabre());
            query.setParameter("reabierto", Boolean.TRUE);
            query.setParameter("maeSolicitudEstadoId", obj.getMaeSolicitudEstadoId());
            query.setParameter("maeSolicitudEstadoCodigo", obj.getMaeSolicitudEstadoCodigo());
            query.setParameter("maeSolicitudEstadoValor", obj.getMaeSolicitudEstadoValor());
            query.setParameter("usuarioReabre", obj.getUsuarioModifica());
            query.setParameter("terminalReabre", obj.getTerminalModifica());
            query.setParameter("fechaHoraReabre", obj.getFechaHoraModifica());
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
    public void actualizarCerrarCaso(AusCaso obj) throws Exception {
        try {
            String hql = "UPDATE AusCasos SET"
                    + " maeCasoCerradoId = :maeCasoCerradoId,"
                    + " maeCasoCerradoCodigo = :maeCasoCerradoCodigo,"
                    + " maeCasoCerradoValor = :maeCasoCerradoValor,"
                    + " cerrado = :cerrado,"
                    + " maeSolicitudEstadoId = :maeSolicitudEstadoId,"
                    + " maeSolicitudEstadoCodigo = :maeSolicitudEstadoCodigo,"
                    + " maeSolicitudEstadoValor = :maeSolicitudEstadoValor,"
                    + " origenCierre = :origenCierre,"
                    + " usuarioCierra = :usuarioCierra,"
                    + " terminalCierra = :terminalCierra,"
                    + " fechaHoraCierra = :fechaHoraCierra"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("maeCasoCerradoId", obj.getMaeCasoCerradoId());
            query.setParameter("maeCasoCerradoCodigo", obj.getMaeCasoCerradoCodigo());
            query.setParameter("maeCasoCerradoValor", obj.getMaeCasoCerradoValor());
            query.setParameter("cerrado", Boolean.TRUE);
            query.setParameter("maeSolicitudEstadoId", obj.getMaeSolicitudEstadoId());
            query.setParameter("maeSolicitudEstadoCodigo", obj.getMaeSolicitudEstadoCodigo());
            query.setParameter("maeSolicitudEstadoValor", obj.getMaeSolicitudEstadoValor());
            query.setParameter("origenCierre", obj.getOrigenCierre());
            query.setParameter("usuarioCierra", obj.getUsuarioModifica());
            query.setParameter("terminalCierra", obj.getTerminalModifica());
            query.setParameter("fechaHoraCierra", obj.getFechaHoraModifica());
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
    public AusCaso eliminar(int id) throws Exception {
        AusCaso obj = null;
        try {
            AusCasos ent = getEntityManager().find(AusCasos.class, id);
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
    public AusCaso consultar(int id) throws Exception {
        AusCaso objRes = null;
        try {
            AusCasos casoNegocio = (AusCasos) getEntityManager().find(AusCasos.class, id);
            objRes = castEntidadNegocio(casoNegocio);
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
    public List<Date> consultarFechasNoHabiles(Date fecha) {
        List<Date> listResult = new ArrayList();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaConsulta = formato.format(fecha);
        try {
            String strQuery = "SELECT "
                    + "dh.fecha FROM GnDiasHabiles dh "
                    + "WHERE dh.fecha >= '" + fechaConsulta + "' ORDER BY dh.fecha ASC";
            listResult = getEntityManager().createQuery(strQuery).getResultList();
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public List<Usuario> consultarUsuarioPorEmpresa(int empresaId) throws Exception {
        List<Usuario> listResult = new ArrayList();
        String strQuery = "FROM GnUsuarios"
                + " WHERE gnEmpresasId.id = :empresa_id"
                + " ORDER BY nombre, usuario";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("empresa_id", empresaId);
            List<GnUsuarios> list = query.getResultList();
            for (GnUsuarios obj : list) {
                listResult.add(castEntidadNegocioUsuario(obj));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception("Consulta de registros", e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public List<Usuario> consultarUsuario(int usuarioId) throws Exception {
        List<Usuario> listResult = new ArrayList();
        String strQuery = "FROM GnUsuarios"
                + " WHERE id = :usuario_id"
                + " ORDER BY nombre, usuario";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("usuario_id", usuarioId);
            List<GnUsuarios> list = query.getResultList();
            for (GnUsuarios obj : list) {
                listResult.add(castEntidadNegocioUsuario(obj));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception("Consulta de registros", e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public int consultarCantidadListaExterna(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(s) FROM AusCasos s "
                    + " WHERE s.ausPersonasId.maeTipoDocumentoCodigo = '" + paramConsulta.getParametroConsulta1() + "' "
                    + " AND s.ausPersonasId.documento = :documentoNumero ";
                    //+ " AND s.ausPersonasId.fechaNacimento = :fhNacimiento ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND s.id LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += " AND s.borrado = 0 ";
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("documentoNumero", paramConsulta.getParametroConsulta2());
            //query.setParameter("fhNacimiento", ((Date) paramConsulta.getParametroConsulta3()), TemporalType.TIMESTAMP);
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
    public List<AusCaso> consultarListaExterna(ParamConsulta paramConsulta) throws Exception {
        List<AusCaso> listResult = new ArrayList();
        try {
            String strQuery = "FROM AusCasos s "
                    + " WHERE s.ausPersonasId.maeTipoDocumentoCodigo = '" + paramConsulta.getParametroConsulta1() + "' "
                    + " AND s.ausPersonasId.documento = :documentoNumero ";
                    //+ " AND s.ausPersonasId.fechaNacimento = :fhNacimiento ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND s.id LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += " AND s.borrado = 0 ";
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "s." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "s.fechaHoraCrea DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("documentoNumero", paramConsulta.getParametroConsulta2());
            //query.setParameter("fhNacimiento", ((Date) paramConsulta.getParametroConsulta3()), TemporalType.TIMESTAMP);
            List<AusCasos> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AusCasos per : list) {
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

    public static Usuario CastEntidadNegocionSimple(GnUsuarios per) {
        Usuario obj = new Usuario();
        obj.setEmpresa(new Empresa(per.getGnEmpresasId().getId()));
        obj.setId(per.getId());
        obj.setUsuario(per.getUsuario());
        obj.setNombre(per.getNombre());
        obj.setCorreoElectronico(per.getCorreoElectronico());
        obj.setTelefono(per.getTelefono());
        obj.setCelular(per.getCelular());
        obj.setActivo(per.getActivo());
        obj.setFechaInicio(per.getFechaInicio());
        obj.setFechaFin(per.getFechaFin());
        obj.setFechaUltimoIngreso(per.getFechaUltimoIngreso());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static AusCaso castEntidadNegocioLista(AusCasos per) {
        AusCaso obj = new AusCaso();
        obj.setEmpresasId(new Empresa(per.getGnEmpresasId().getId()));
        obj.setId(per.getId());
        obj.setRadicado(per.getRadicado());
        obj.setFechaNotificacion(per.getFechaNotificacion());
        obj.setFechaVencimiento(per.getFechaVencimiento());
        obj.setFechaHoraResponsable(per.getFechaHoraResponsable());
        obj.setParentesco(per.getParentesco());
        //cast de objetos asociados
        if (per.getGnUsuariosResponsableId() != null) {
            obj.setResponsableUsuariosId(
                    new Usuario(
                            per.getGnUsuariosResponsableId().getId(),
                            per.getGnUsuariosResponsableId().getUsuario(),
                            per.getGnUsuariosResponsableId().getNombre()
                    )
            );
        }
        if (per.getGnSedesId() != null) {
            obj.setSedesId(new AusSede(
                    per.getGnSedesId().getId(),
                    per.getGnSedesId().getNombre(),
                    per.getGnSedesId().getDescripcion(),
                    new Ubicacion(per.getGnUbicacionesId().getId())
            )
            );
        }
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
            obj.setAusPersonasId(
                    new AusPersona(
                            per.getAusPersonasId().getId(),
                            per.getAusPersonasId().getMaeTipoDocumentoId(),
                            per.getAusPersonasId().getMaeTipoDocumentoCodigo(),
                            per.getAusPersonasId().getMaeTipoDocumentoValor(),
                            per.getAusPersonasId().getDocumento(),
                            per.getAusPersonasId().getNombres(),
                            per.getAusPersonasId().getApellidos(),
                            per.getAusPersonasId().getCorreoElectronico(),
                            per.getAusPersonasId().getNombres() + " " + per.getAusPersonasId().getApellidos()
                    )
            );
        } else {
            obj.setAusPersonasId(new AusPersona());
        }
        obj.setServiciosList(AusCasoServicioServicio.castEntidadNegocioLista(per.getAusCasoServiciosList()));
//        obj.setAdjuntosList(AusAdjuntoServicio.castEntidadNegocio(per.getAdjuntosList()));
        obj.setSeguimientosList(AusSeguimientoServicio.castEntidadNegocio(per.getAusSeguimientosList()));
        if (obj.getSeguimientosList() != null && !obj.getSeguimientosList().isEmpty()) {
            obj.setSeguimientoExterno(obj.getSeguimientosList().get(0).getMaeEstadoValor());
        }
        obj.setMaeSolicitudRiesgoVidalId(per.getMaeSolicitudRiesgoVidalId());
        obj.setMaeSolicitudRiesgoVidalCodigo(per.getMaeSolicitudRiesgoVidalCodigo());
        obj.setMaeSolicitudRiesgoVidalValor(per.getMaeSolicitudRiesgoVidalValor());
        obj.setMaeSolicitudEnteControlId(per.getMaeSolicitudEnteControlId());
        obj.setMaeSolicitudEnteControlCodigo(per.getMaeSolicitudEnteControlCodigo());
        obj.setMaeSolicitudEnteControlValor(per.getMaeSolicitudEnteControValor());
        obj.setMaeSolicitudPrioridadId(per.getMaeSolicitudPrioridadId());
        obj.setMaeSolicitudPrioridadCodigo(per.getMaeSolicitudPrioridadCodigo());
        obj.setMaeSolicitudPrioridadValor(per.getMaeSolicitudPrioridadValor());
        obj.setMaeSolicitudOrigenId(per.getMaeSolicitudOrigenId());
        obj.setMaeSolicitudOrigenCodigo(per.getMaeSolicitudOrigenCodigo());
        obj.setMaeSolicitudOrigenValor(per.getMaeSolicitudOrigenValor());
        obj.setMaeCanalSupersaludId(per.getMaeCanalSupersaludId());
        obj.setMaeCanalSupersaludCodigo(per.getMaeCanalSupersaludCodigo());
        obj.setMaeCanalSupersaludValor(per.getMaeCanalSupersaludValor());
        obj.setMaeSolicitudTipoId(per.getMaeSolicitudTipoId());
        obj.setMaeSolicitudTipoCodigo(per.getMaeSolicitudTipoCodigo());
        obj.setMaeSolicitudTipoValor(per.getMaeSolicitudTipoValor());
        obj.setPertinencia(per.getPertinencia());
        obj.setMaeSolicitudEstadoId(per.getMaeSolicitudEstadoId());
        obj.setMaeSolicitudEstadoCodigo(per.getMaeSolicitudEstadoCodigo());
        obj.setMaeSolicitudEstadoValor(per.getMaeSolicitudEstadoValor());
        obj.setModalidadEntrega(per.getModalidadEntrega());
        obj.setCantidadServicios(per.getCantidadServicios());
        obj.setCantidadServiciosCerrados(per.getCantidadServiciosCerrados());
        //2025-03-17 jyperez nuevo campo
        obj.setFechaCreacionCaso(per.getFechaCreacionCaso());
        if (per.getGnUsuarioCierreId() != null) {
            obj.setUsuarioCierre(new Usuario(
                            per.getGnUsuarioCierreId().getId(),
                            per.getGnUsuarioCierreId().getUsuario(),
                            per.getGnUsuarioCierreId().getNombre()
                    )
            );
        }
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setUsuarioCierra(per.getUsuarioCierra());
        obj.setFechaHoraCierra(per.getFechaHoraCierra());
        obj.setTerminalCierra(per.getTerminalCierra());
        return obj;
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
        if (per.getGnUsuariosResponsableId() != null) {
            obj.setResponsableUsuariosId(
                    new Usuario(
                            per.getGnUsuariosResponsableId().getId(),
                            per.getGnUsuariosResponsableId().getUsuario(),
                            per.getGnUsuariosResponsableId().getNombre()
                    )
            );
        }
        if (per.getGnSedesId() != null) {
            obj.setSedesId(new AusSede(
                    per.getGnSedesId().getId(),
                    per.getGnSedesId().getNombre(),
                    per.getGnSedesId().getDescripcion(),
                    new Ubicacion(per.getGnUbicacionesId().getId())
            )
            );
        }
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
        if (per.getAusAccionantePersonasId() != null) {
            obj.setPeticionario(AusPersonaServicio.castEntidadNegocio(per.getAusAccionantePersonasId()));
        } else {
            obj.setPeticionario(new AusPersona());
        }
        //obj.setServiciosList(AusCasoServicioServicio.castEntidadNegocio(per.getAusCasoServiciosList()));
        obj.setAdjuntosList(AusAdjuntoServicio.castEntidadNegocio(per.getAusAdjuntosList()));
        obj.setSeguimientosList(AusSeguimientoServicio.castEntidadNegocio(per.getAusSeguimientosList()));
        obj.setMaeSolicitudRiesgoVidalId(per.getMaeSolicitudRiesgoVidalId());
        obj.setMaeSolicitudRiesgoVidalCodigo(per.getMaeSolicitudRiesgoVidalCodigo());
        obj.setMaeSolicitudRiesgoVidalValor(per.getMaeSolicitudRiesgoVidalValor());
        obj.setMaeSolicitudEnteControlId(per.getMaeSolicitudEnteControlId());
        obj.setMaeSolicitudEnteControlCodigo(per.getMaeSolicitudEnteControlCodigo());
        obj.setMaeSolicitudEnteControlValor(per.getMaeSolicitudEnteControValor());
        obj.setMaeSolicitudPrioridadId(per.getMaeSolicitudPrioridadId());
        obj.setMaeSolicitudPrioridadCodigo(per.getMaeSolicitudPrioridadCodigo());
        obj.setMaeSolicitudPrioridadValor(per.getMaeSolicitudPrioridadValor());
        obj.setMaeSolicitudOrigenId(per.getMaeSolicitudOrigenId());
        obj.setMaeSolicitudOrigenCodigo(per.getMaeSolicitudOrigenCodigo());
        obj.setMaeSolicitudOrigenValor(per.getMaeSolicitudOrigenValor());
        obj.setMaeCanalSupersaludId(per.getMaeCanalSupersaludId());
        obj.setMaeCanalSupersaludCodigo(per.getMaeCanalSupersaludCodigo());
        obj.setMaeCanalSupersaludValor(per.getMaeCanalSupersaludValor());
        //obj.setMaeCanalSupersaludId(per.getMaeCanalSupersaludId());
        //obj.setMaeCanalSupersaludCodigo(per.getMaeCanalSupersaludCodigo());
        //obj.setMaeCanalSupersaludValor(per.getMaeCanalSupersaludValor());
        obj.setMaeSolicitudTipoId(per.getMaeSolicitudTipoId());
        obj.setMaeSolicitudTipoCodigo(per.getMaeSolicitudTipoCodigo());
        obj.setMaeSolicitudTipoValor(per.getMaeSolicitudTipoValor());
        obj.setPertinencia(per.getPertinencia());
        obj.setMaeSolicitudEstadoId(per.getMaeSolicitudEstadoId());
        obj.setMaeSolicitudEstadoCodigo(per.getMaeSolicitudEstadoCodigo());
        obj.setMaeSolicitudEstadoValor(per.getMaeSolicitudEstadoValor());
        obj.setParentesco(per.getParentesco());
        obj.setCantidadServicios(per.getCantidadServicios());
        obj.setCantidadServiciosCerrados(per.getCantidadServiciosCerrados());
        obj.setMaeTecnologiaAltoCostoId(per.getMaeTecnologiaAltoCostoId());
        obj.setMaeTecnologiaAltoCostoCodigo(per.getMaeTecnologiaAltoCostoCodigo());
        obj.setMaeTecnologiaAltoCostoValor(per.getMaeTecnologiaAltoCostoValor());
        obj.setMaeMotivoEspecificoId(per.getMaeMotivoEspecificoId());
        obj.setMaeMotivoEspecificoCodigo(per.getMaeMotivoEspecificoCodigo());
        obj.setMaeMotivoEspecificoValor(per.getMaeMotivoEspecificoValor());
        obj.setMaeTipoMotivoEspecificoId(per.getMaeTipoMotivoEspecificoId());
        obj.setMaeTipoMotivoEspecificoCodigo(per.getMaeTipoMotivoEspecificoCodigo());
        obj.setMaeTipoMotivoEspecificoValor(per.getMaeTipoMotivoEspecificoValor());
        obj.setMaeSubtipoMotivoEspecificoId(per.getMaeSubtipoMotivoEspecificoId());
        obj.setMaeSubtipoMotivoEspecificoCodigo(per.getMaeSubtipoMotivoEspecificoCodigo());
        obj.setMaeSubtipoMotivoEspecificoValor(per.getMaeSubtipoMotivoEspecificoValor());
        obj.setUsuarioPluripatologico(per.getUsuarioPluripatologico());
        obj.setProteccionDatos(per.getProteccionDatos());
        obj.setMaeCasoCerradoId(per.getMaeCasoCerradoId());
        obj.setMaeCasoCerradoCodigo(per.getMaeCasoCerradoCodigo());
        obj.setMaeCasoCerradoValor(per.getMaeCasoCerradoValor());
        obj.setInstruccion(per.getInstruccion());
        obj.setReabierto(per.getReabierto());
        obj.setFalloTutela(per.getFalloTutela());
        obj.setRedireccionado(per.getRedireccionado());
        obj.setMultireparto(per.getMultireparto());
        obj.setModalidadEntrega(per.getModalidadEntrega());
        obj.setDireccionResidencia(per.getDireccionResidencia());
        //2024-10-31 jyperez nuevo campo
        obj.setOrigenCierre(per.getOrigenCierre());
        //2025-03-17 jyperez nuevo campo
        obj.setFechaCreacionCaso(per.getFechaCreacionCaso());
        //2025-04-04 jyperez nuevo campo
        if (per.getGnUsuarioCierreId() != null) {
            obj.setUsuarioCierre(new Usuario(
                            per.getGnUsuarioCierreId().getId(),
                            per.getGnUsuarioCierreId().getUsuario(),
                            per.getGnUsuarioCierreId().getNombre()
                    )
            );
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

    public static AusSede castNegocioEntidad(GnSedes sedeEntidad) {
        if (sedeEntidad != null) {
            GnEmpresas empresa = sedeEntidad.getGnEmpresasId() == null ? new GnEmpresas() : sedeEntidad.getGnEmpresasId();
            sedeEntidad.setGnEmpresasId(empresa);
        }
        return (sedeEntidad != null) ? AusSedeServicio.castEntidadNegocio(sedeEntidad) : new AusSede();
    }

    public static Usuario castEntidadNegocio(GnUsuarios usuarioNegocio) {
        return (usuarioNegocio != null) ? UsuarioServicio.castEntidadNegocio(usuarioNegocio) : new Usuario();
    }

    public static GnUsuarios castNegocioEntidad(Usuario usuarioEntidad) {
        if (usuarioEntidad != null) {
            Empresa emp = usuarioEntidad.getEmpresa() != null ? usuarioEntidad.getEmpresa() : new Empresa();
            usuarioEntidad.setEmpresa(emp);
        }
        return (usuarioEntidad != null) ? UsuarioServicio.castNegocioEntidad(usuarioEntidad) : new GnUsuarios();
    }

    public static Ubicacion castEntidadNegocio(GnUbicaciones ubicacionNegiocio) {
        return (ubicacionNegiocio != null) ? UbicacionServicio.castEntidadNegocio(ubicacionNegiocio) : new Ubicacion();
    }

    public static GnUbicaciones castNegocioEntidad(Ubicacion ubicacionEntidad) {
        return (ubicacionEntidad != null) ? UbicacionServicio.castNegocioEntidad(ubicacionEntidad) : new GnUbicaciones();
    }

    public static AusCasos castNegocioEntidad(AusCaso obj) {
        AusCasos per = new AusCasos();
        per.setId(obj.getId());
        if (obj.getEmpresa() != null) {
            per.setGnEmpresasId(new GnEmpresas(obj.getEmpresa().getId()));
        }
        if (obj.getAusPersonasId() != null) {
            per.setAusPersonasId(new AusPersonas(obj.getAusPersonasId().getId()));
        }
        if (obj.getPeticionario() != null && obj.getPeticionario().getId() != null && obj.getPeticionario().getDocumento() != null) {
            per.setAusAccionantePersonasId(new AusPersonas(obj.getPeticionario().getId()));
            //           per.setAccionanteEdad(obj.getPeticionario().getEdad());
            per.setParentesco(obj.getParentesco());
        }
        if (obj.getIdAusCargaMasiva() != null) {
            per.setAusCargaMasivasId(new AusCargaMasivas(obj.getIdAusCargaMasiva()));
        }
        per.setPertinencia(obj.getPertinencia());
        per.setMaeSolicitudEstadoId(obj.getMaeSolicitudEstadoId());
        per.setMaeSolicitudEstadoCodigo(obj.getMaeSolicitudEstadoCodigo());
        per.setMaeSolicitudEstadoValor(obj.getMaeSolicitudEstadoValor());
        per.setMaeSolicitudTipoId(obj.getMaeSolicitudTipoId());
        per.setMaeSolicitudTipoCodigo(obj.getMaeSolicitudTipoCodigo());
        per.setMaeSolicitudTipoValor(obj.getMaeSolicitudTipoValor());
        per.setRadicado(obj.getRadicado());
        per.setInstruccion(obj.getInstruccion());
        per.setReabierto(obj.getReabierto());
        per.setFalloTutela(obj.getFalloTutela());
        per.setRedireccionado(obj.getRedireccionado());
        per.setMaeSolicitudPrioridadId(obj.getMaeSolicitudPrioridadId());
        per.setMaeSolicitudPrioridadCodigo(obj.getMaeSolicitudPrioridadCodigo());
        per.setMaeSolicitudPrioridadValor(obj.getMaeSolicitudPrioridadValor());
        per.setMaeSolicitudEnteControlId(obj.getMaeSolicitudEnteControlId());
        per.setMaeSolicitudEnteControlCodigo(obj.getMaeSolicitudEnteControlCodigo());
        per.setMaeSolicitudEnteControValor(obj.getMaeSolicitudEnteControlValor());
        per.setMaeSolicitudRiesgoVidalId(obj.getMaeSolicitudRiesgoVidalId());
        per.setMaeSolicitudRiesgoVidalCodigo(obj.getMaeSolicitudRiesgoVidalCodigo());
        per.setMaeSolicitudRiesgoVidalValor(obj.getMaeSolicitudRiesgoVidalValor());
        per.setMaeSolicitudOrigenId(obj.getMaeSolicitudOrigenId());
        per.setMaeSolicitudOrigenCodigo(obj.getMaeSolicitudOrigenCodigo());
        per.setMaeSolicitudOrigenValor(obj.getMaeSolicitudOrigenValor());
        per.setMaeCanalSupersaludId(obj.getMaeCanalSupersaludId());
        per.setMaeCanalSupersaludCodigo(obj.getMaeCanalSupersaludCodigo());
        per.setMaeCanalSupersaludValor(obj.getMaeCanalSupersaludValor());
        per.setFechaNotificacion(obj.getFechaNotificacion());
        per.setFechaVencimiento(obj.getFechaVencimiento());
        per.setMultireparto(obj.getMultireparto());
        per.setCantidadServicios(obj.getCantidadServicios());
        per.setCantidadServiciosCerrados(obj.getCantidadServiciosCerrados());
        per.setBorrado(obj.getBorrado());

        per.setMaeTecnologiaAltoCostoId(obj.getMaeTecnologiaAltoCostoId());
        per.setMaeTecnologiaAltoCostoCodigo(obj.getMaeTecnologiaAltoCostoCodigo());
        per.setMaeTecnologiaAltoCostoValor(obj.getMaeTecnologiaAltoCostoValor());
        per.setMaeMotivoEspecificoId(obj.getMaeMotivoEspecificoId());
        per.setMaeMotivoEspecificoCodigo(obj.getMaeMotivoEspecificoCodigo());
        per.setMaeMotivoEspecificoValor(obj.getMaeMotivoEspecificoValor());
        per.setMaeTipoMotivoEspecificoId(obj.getMaeTipoMotivoEspecificoId());
        per.setMaeTipoMotivoEspecificoCodigo(obj.getMaeTipoMotivoEspecificoCodigo());
        per.setMaeTipoMotivoEspecificoValor(obj.getMaeTipoMotivoEspecificoValor());
        per.setMaeSubtipoMotivoEspecificoId(obj.getMaeSubtipoMotivoEspecificoId());
        per.setMaeSubtipoMotivoEspecificoCodigo(obj.getMaeSubtipoMotivoEspecificoCodigo());
        per.setMaeSubtipoMotivoEspecificoValor(obj.getMaeSubtipoMotivoEspecificoValor());
        per.setUsuarioPluripatologico(obj.getUsuarioPluripatologico());
        per.setProteccionDatos(obj.getProteccionDatos());
        if (obj.getSedesId() != null && (obj.getSedesId().getId() != null
                && obj.getSedesId().getId() > 0)) {
            per.setGnSedesId(new GnSedes(obj.getSedesId().getId()));
        }
        if (obj.getResponsableUsuariosId() != null && (obj.getResponsableUsuariosId().getId() != null
                && obj.getResponsableUsuariosId().getId() > 0)) {
            per.setGnUsuariosResponsableId(new GnUsuarios(obj.getResponsableUsuariosId().getId()));
        }
        if (obj.getUbicacion() != null && (obj.getUbicacion().getId() != null
                && obj.getUbicacion().getId() > 0)) {
            per.setGnUbicacionesId(new GnUbicaciones(obj.getUbicacion().getId()));
        }
        if (obj.getModalidadEntrega() != null) {
            per.setModalidadEntrega(obj.getModalidadEntrega());
        }
        per.setDireccionResidencia(obj.getDireccionResidencia());
        //2024-10-31 jyperez nuevo campo
        per.setOrigenCierre(obj.getOrigenCierre());
        //2025-04-03 jyperez campo cierre no se inserta al inicio
        per.setUsuarioCierra(obj.getUsuarioCierra());
        //2025-03-17 jyperez nuevo campo
        per.setFechaCreacionCaso(obj.getFechaCreacionCaso());
        //2025-04-04 jyperez nuevo campo
        if (obj.getUsuarioCierre() != null && (obj.getUsuarioCierre().getId() != null
                && obj.getUsuarioCierre().getId() > 0)) {
            per.setGnUsuarioCierreId(new GnUsuarios(obj.getUsuarioCierre().getId()));
        }
        per.setFechaHoraResponsable(obj.getFechaHoraCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        //per.setAfiliadoEdad(obj.getAusPersonasId().getEdad());
        return per;
    }

    public static Usuario castEntidadNegocioUsuario(GnUsuarios per) {
        Usuario obj = new Usuario();
        obj.setEmpresa(new Empresa(per.getGnEmpresasId().getId()));
        obj.setId(per.getId());
        obj.setUsuario(per.getUsuario());
        obj.setNombre(per.getNombre());
        obj.setCorreoElectronico(per.getCorreoElectronico());
        obj.setTelefono(per.getTelefono());
        obj.setCelular(per.getCelular());
        obj.setActivo(per.getActivo());
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        return obj;
    }

    private void Exception(java.lang.Exception e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int consultarCantidadListaPorAfiliado(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM AusCasos c "
                    + " WHERE 1 = 1 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND c.ausPersonasId.asegAfiliadosId.id = " + paramConsulta.getParametroConsulta1() + " ";
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicado":
                            strQuery += "AND c.radicado LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeSolicitudEstadoId":
                            String cadena = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND c.maeSolicitudEstadoId IN ( " + cadena + ") ";
                            break;

                    }
                }
            }
            strQuery += " AND c.borrado = 0 ";
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
    public List<AusCaso> consultarListaPorAfiliado(ParamConsulta paramConsulta) throws Exception {
        List<AusCaso> listResult = new ArrayList();
        try {
            String strQuery = "FROM AusCasos c "
                    + " WHERE 1 = 1 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND c.ausPersonasId.asegAfiliadosId.id = " + paramConsulta.getParametroConsulta1() + " ";
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicado":
                            strQuery += "AND c.radicado LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeSolicitudEstadoId":
                            String cadena = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND c.maeSolicitudEstadoId IN ( " + cadena + ") ";
                            break;

                    }
                }
            }
            strQuery += " AND c.borrado = 0 ";
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "c." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "c.fechaHoraCrea DESC";
            }
            List<AusCasos> list = null;
            list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AusCasos casoNegocion : list) {
                listResult.add(castEntidadNegocioLista(casoNegocion));
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
    public List<AusCaso> consultarPorAfiliadoTipoTecnologiaYEstado(AusCaso caso) throws java.lang.Exception {
        List<AusCaso> listResult = new ArrayList();
        String strQuery = "SELECT c FROM AusCasos c JOIN c.ausCasoServiciosList cs "
                + " WHERE c.ausPersonasId.maeTipoDocumentoId = " + caso.getAusPersonasId().getMae_tipo_documento_id() + " "
                + " AND c.ausPersonasId.documento LIKE '" + caso.getAusPersonasId().getDocumento() + "' "
                + " AND cs.tipoTecnologia = " + caso.getServicio().getTipoTecnologia() + " "
                + " AND cs.maTecnologiaId = " + caso.getServicio().getMaTecnologiaId() + " "
                + " AND c.maeSolicitudEstadoCodigo LIKE '" + AusCaso.SOLICITUD_ESTADO_EN_GESTION + "' "
                + " ORDER BY c.id DESC";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<AusCasos> list = query.getResultList();
            for (AusCasos obj : list) {
                listResult.add(castEntidadNegocio(obj));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception("Consulta de registros", e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

}
