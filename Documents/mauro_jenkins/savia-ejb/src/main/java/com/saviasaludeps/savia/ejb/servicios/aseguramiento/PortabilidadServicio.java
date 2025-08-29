/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.aseguramiento;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegPortabilidad;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.aseguramiento.ReportePortabilidad;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AsegPortabilidades;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.utilidades.Util;
import com.saviasaludeps.savia.negocio.aseguramiento.PortabilidadRemoto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

@Stateless
@Remote(PortabilidadRemoto.class)
@Local(PortabilidadLocal.class)
public class PortabilidadServicio extends GenericoServicio implements PortabilidadRemoto, PortabilidadLocal {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(a) FROM AsegPortabilidades a "
                    + "WHERE a.id > 0 ";
            if (paramConsulta.getFiltros() != null && !paramConsulta.getFiltros().isEmpty()) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND a.id = " + e.getValue() + " ";
                            break;
                        case "estadoPortabilidad":
                            strQuery += "AND a.estadoPortabilidad = " + e.getValue() + " ";
                            break;
                        case "maeEstadoPortabilidadId":
                            strQuery += "AND a.maeEstadoPortabilidadId = " + e.getValue() + " ";
                            break;
                        case "maeEstadoPortabilidadValor":
                            strQuery += "AND a.maeEstadoPortabilidadValor LIKE '" + e.getValue() + "' ";
                            break;
                        case "asegAfiliado.maeTipoDocumento":
                            strQuery += "AND a.asegAfiliadosId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "asegAfiliado.numeroDocumento":
                            strQuery += "AND a.asegAfiliadosId.numeroDocumento = '" + e.getValue() + "' ";
                            break;
                        case "asegAfiliado.nombres":
                            strQuery += "AND (a.asegAfiliadosId.primerNombre = '" + e.getValue() + "' "
                                    + "OR a.asegAfiliadosId.segundoNombre = '" + e.getValue() + "') ";
                            break;
                        case "asegAfiliado.apellidos":
                            strQuery += "AND (a.asegAfiliadosId.primerApellido = '" + e.getValue() + "' "
                                    + "OR a.asegAfiliadosId.segundoApellido = '" + e.getValue() + "') ";
                            break;
                        case "asegAfiliado.maeGeneroId":
                            strQuery += "AND a.asegAfiliadosId.maeGeneroId = " + e.getValue() + " ";
                            break;
                        case "periodoInicial":
                            strQuery += "AND a.periodoInicial = " + e.getValue() + " ";
                            break;
                        case "periodoFinal":
                            strQuery += "AND a.periodoFinal = " + e.getValue() + " ";
                            break;
                        case "tipoPortabilidad":
                            strQuery += "AND a.tipoPortabilidad = " + e.getValue() + " ";
                            break;
                        case "maeTipoPortabilidadId":
                            strQuery += "AND a.maeTipoPortabilidadId = " + e.getValue() + " ";
                            break;
                        case "maeTipoPortabilidadValor":
                            strQuery += "AND a.maeTipoPortabilidadValor LIKE '" + e.getValue() + "' ";
                            break;
                        case "origenSolicitud":
                            strQuery += "AND a.origenSolicitud = " + e.getValue() + " ";
                            break;
                        case "maeOrigenSolicitudId":
                            strQuery += "AND a.maeOrigenSolicitudId = " + e.getValue() + " ";
                            break;
                        case "maeOrigenSolicitudValor":
                            strQuery += "AND a.maeOrigenSolicitudValor LIKE '" + e.getValue() + "' ";
                            break;
                        case "ubicacion.nombre":
                            strQuery += "AND a.ubicacionesId.nombre = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestadorSede.nombreSede":
                            strQuery += "AND a.cntPrestadorSedesId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (NumberFormatException e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

    @Override
    public List<AsegPortabilidad> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<AsegPortabilidad> listResult = new ArrayList();
        try {
            String strQuery = "SELECT a.id, a.periodoInicial, a.periodoFinal, a.fechaCancelacion, a.maeOrigenSolicitudId, a.maeOrigenSolicitudValor "
                    + ", a.maeTipoPortabilidadId, a.maeTipoPortabilidadValor, a.maeEstadoPortabilidadId, a.maeEstadoPortabilidadCodigo, a.maeEstadoPortabilidadValor "
                    + ", af.id , af.idAfiliado , af.primerNombre, af.segundoNombre "
                    + ", af.primerApellido , af.segundoApellido , af.maeGeneroId, af.maeGeneroValor "
                    + ", af.maeTipoDocumentoId , af.maeTipoDocumentoValor , af.maeEstadoAfiliacionId, af.maeEstadoAfiliacionValor "
                    + ", af.numeroDocumento , ub.id , sdr.id , sdr.nombre, ips.id, ips.razonSocial, a.envioCorreo "
                    + "FROM AsegPortabilidades a "
                    + "INNER JOIN a.asegAfiliadosId af "
                    + "INNER JOIN a.ubicacionesId ub "
                    + "LEFT OUTER JOIN a.cntPrestadorSedesId sdr "
                    + "LEFT OUTER JOIN sdr.cntPrestadoresId ips "
                    + "WHERE a.id > 0 ";
            if (paramConsulta.getFiltros() != null && !paramConsulta.getFiltros().isEmpty()) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                                strQuery += "AND a.id = " + e.getValue() + " ";
                                break;
                            case "estadoPortabilidad":
                                strQuery += "AND a.estadoPortabilidad = " + e.getValue() + " ";
                                break;
                            case "maeEstadoPortabilidadId":
                                strQuery += "AND a.maeEstadoPortabilidadId = " + e.getValue() + " ";
                                break;
                            case "maeEstadoPortabilidadValor":
                                strQuery += "AND a.maeEstadoPortabilidadValor LIKE '" + e.getValue() + "' ";
                                break;
                            case "asegAfiliado.maeTipoDocumento":
                                strQuery += "AND af.maeTipoDocumentoId = " + e.getValue() + " ";
                                break;
                            case "asegAfiliado.numeroDocumento":
                                strQuery += "AND af.numeroDocumento = '" + e.getValue() + "' ";
                                break;
                            case "asegAfiliado.nombres":
                                strQuery += "AND (af.primerNombre = '" + e.getValue() + "' "
                                        + "OR af.segundoNombre = '" + e.getValue() + "') ";
                                break;
                            case "asegAfiliado.apellidos":
                                strQuery += "AND (af.primerApellido = '" + e.getValue() + "' "
                                        + "OR af.segundoApellido = '" + e.getValue() + "') ";
                                break;
                            case "asegAfiliado.maeGeneroId":
                                strQuery += "AND af.maeGeneroId = " + e.getValue() + " ";
                                break;
                            case "periodoInicial":
                                strQuery += "AND a.periodoInicial = " + e.getValue() + " ";
                                break;
                            case "periodoFinal":
                                strQuery += "AND a.periodoFinal = " + e.getValue() + " ";
                                break;
                            case "tipoPortabilidad":
                                strQuery += "AND a.tipoPortabilidad = " + e.getValue() + " ";
                                break;
                            case "maeTipoPortabilidadId":
                                strQuery += "AND a.maeTipoPortabilidadId = " + e.getValue() + " ";
                                break;
                            case "maeTipoPortabilidadValor":
                                strQuery += "AND a.maeTipoPortabilidadValor LIKE '" + e.getValue() + "' ";
                                break;
                            case "origenSolicitud":
                                strQuery += "AND a.origenSolicitud = " + e.getValue() + " ";
                                break;
                            case "maeOrigenSolicitudId":
                                strQuery += "AND a.maeOrigenSolicitudId = " + e.getValue() + " ";
                                break;
                            case "maeOrigenSolicitudValor":
                                strQuery += "AND a.maeOrigenSolicitudValor LIKE '" + e.getValue() + "' ";
                                break;
                            case "ubicacion.nombre":
                                strQuery += "AND ub.nombre = '" + e.getValue() + "' ";
                                break;
                            case "cntPrestadorSede.nombreSede":
                                strQuery += "AND sdr.nombre LIKE '%" + e.getValue() + "%' ";
                                break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                if (paramConsulta.getOrden().contains("asegAfiliado.nombres")) {
                    strQuery += "af.primerNombre "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                    /* 2020-11-11 jyperez -INC 331 - Se cambia para que solo ordene por el primer nombre
                    strQuery += ", af.segundoNombre "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC"); */
                } else if (paramConsulta.getOrden().contains("asegAfiliado.apellidos")) {
                    strQuery += "af.primerApellido "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                    /* 2020-11-11 jyperez -INC 332 - Se cambia para que solo ordene por el primer apellido
                    strQuery += ", af.segundoApellido "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC"); */
                } else if (paramConsulta.getOrden().contains("asegAfiliado.maeTipoDocumento")) {
                    strQuery += "af.maeTipoDocumentoId "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else if (paramConsulta.getOrden().contains("ubicacion.nombre")) {
                    strQuery += "ub.nombre "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else if (paramConsulta.getOrden().contains("cntPrestadorSede.nombreSede")) {
                    strQuery += "sdr.nombre "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else {
                    strQuery += "a." + paramConsulta.getOrden().replace("asegAfiliado.", "asegAfiliadosId.") + " "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                }
            } else {
                strQuery += "a.id DESC";
            }
            List<Object[]> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (Object[] port : list) {
                    int i = 0;
                    AsegPortabilidad portabilidad = new AsegPortabilidad();
                    portabilidad.setId((Integer) port[i]);
                    i++;
                    portabilidad.setPeriodoInicial((Date) port[i]);
                    i++;
                    portabilidad.setPeriodoFinal((Date) port[i]);
                    i++;
                    portabilidad.setFechaCancelacion((Date) port[i]);
                    i++;
                    portabilidad.setMaeOrigenSolicitudId((Integer) port[i]);
                    i++;
                    if (port[i] != null) {
                        portabilidad.setMaeOrigenSolicitudValor(port[i].toString());
                    }
                    i++;
                    portabilidad.setMaeTipoPortabilidadId((Integer) port[i]);
                    i++;
                    if (port[i] != null) {
                        portabilidad.setMaeTipoPortabilidadValor(port[i].toString());
                    }
                    i++;
                    portabilidad.setMaeEstadoPortabilidadId((Integer) port[i]);
                    i++;
                    if (port[i] != null) {
                        portabilidad.setMaeEstadoPortabilidadCodigo(port[i].toString());
                    }
                    i++;
                    if (port[i] != null) {
                        portabilidad.setMaeEstadoPortabilidadValor(port[i].toString());
                    }
                    i++;
                    //afiliado
                    portabilidad.setAsegAfiliado(new AsegAfiliado());
                    portabilidad.getAsegAfiliado().setId((Integer) port[i]);
                    i++;
                    if (port[i] != null) {
                        portabilidad.getAsegAfiliado().setIdAfiliado(port[i].toString());
                    }
                    i++;
                    if (port[i] != null) {
                        portabilidad.getAsegAfiliado().setPrimerNombre(port[i].toString());
                    }
                    i++;
                    if (port[i] != null) {
                        portabilidad.getAsegAfiliado().setSegundoNombre(port[i].toString());
                    }
                    i++;
                    if (port[i] != null) {
                        portabilidad.getAsegAfiliado().setPrimerApellido(port[i].toString());
                    }
                    i++;
                    if (port[i] != null) {
                        portabilidad.getAsegAfiliado().setSegundoApellido(port[i].toString());
                    }
                    i++;
                    portabilidad.getAsegAfiliado().setMaeGeneroId((Integer) port[i]);
                    i++;
                    if (port[i] != null) {
                        portabilidad.getAsegAfiliado().setMaeGeneroValor(port[i].toString());
                    }
                    i++;
                    portabilidad.getAsegAfiliado().setMaeTipoDocumento((Integer) port[i]);
                    i++;
                    if (port[i] != null) {
                        portabilidad.getAsegAfiliado().setMaeTipoDocumentoValor(port[i].toString());
                    }
                    i++;
                    portabilidad.getAsegAfiliado().setMaeEstadoAfiliacion((Integer) port[i]);
                    i++;
                    if (port[i] != null) {
                        portabilidad.getAsegAfiliado().setMaeEstadoAfiliacionValor(port[i].toString());
                    }
                    i++;
                    if (port[i] != null) {
                        portabilidad.getAsegAfiliado().setNumeroDocumento(port[i].toString());
                    }
                    i++;
                    //ubicaciones
                    portabilidad.setUbicacion(new Ubicacion((Integer) port[i]));
                    i++;
                    //cntPrestadorSede
                    portabilidad.setCntPrestadorSede(new CntPrestadorSede());
                    portabilidad.getCntPrestadorSede().setCntPrestador(new CntPrestador());
                    portabilidad.getCntPrestadorSede().setId((Integer) port[i]);
                    i++;
                    if (port[i] != null) {
                        portabilidad.getCntPrestadorSede().setNombreSede(port[i].toString());
                    }
                    i++;
                    portabilidad.getCntPrestadorSede().getCntPrestador().setId((Integer) port[i]);
                    i++;
                    if (port[i] != null) {
                        portabilidad.getCntPrestadorSede().getCntPrestador().setRazonSocial(port[i].toString());
                    }
                    i++;
                    if(port[i] != null){
                        portabilidad.setEnvioCorreo(Integer.parseInt(port[i].toString()));
                    }
                    listResult.add(portabilidad);
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
    public AsegPortabilidad consultar(int id) throws Exception {
        AsegPortabilidad objRes = null;
        try {
            AsegPortabilidades obj = (AsegPortabilidades) getEntityManager().find(AsegPortabilidades.class, id);
            if (obj != null) {
                objRes = castEntidadNegocio(obj);
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
    public int insertar(AsegPortabilidad obj) throws Exception {
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
    public void actualizar(AsegPortabilidad obj) throws Exception {
        try {
//            getEntityManager().merge(castNegocioEntidad(obj)).getId();            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AsegPortabilidades a SET ";
            if (obj.getCntPrestadorSede() != null) {
                strQuery += " a.cntPrestadorSedesId.id = '" + obj.getCntPrestadorSede().getId() + "',";
            } else {
                strQuery += " a.cntPrestadorSedesId.id = NULL,";
            }
            strQuery += " a.estadoPortabilidad = " + obj.getEstadoPortabilidad() + ", ";
            if(obj.getMaeEstadoPortabilidadId() != null) {
                strQuery += " a.maeEstadoPortabilidadId = " + obj.getMaeEstadoPortabilidadId() + ", ";
                strQuery += " a.maeEstadoPortabilidadCodigo = '" + obj.getMaeEstadoPortabilidadCodigo()+ "', ";
                strQuery += " a.maeEstadoPortabilidadValor = '" + obj.getMaeEstadoPortabilidadValor()+ "', ";
            }
            strQuery += " a.envioCorreo = " + obj.getEnvioCorreo()+ ", ";
            strQuery += " a.observacionAseguramiento = '" + obj.getObservacionAseguramiento() + "', ";
            strQuery += " a.fechaHoraModifica = '" + sdf.format(obj.getFechaHoraModifica()) + "', ";
            strQuery += " a.usuarioModifica = '" + obj.getUsuarioModifica() + "', ";
            strQuery += " a.terminalModifica = '" + obj.getTerminalModifica() + "' ";
            // campos objetos 
            if (obj.getMaeEstadoPortabilidadCodigo().equals(AsegPortabilidad.ESTADO_CANCELADA_USUARIO)) {
                strQuery += ", a.fechaSolicitudCancelacion = '" + Util.fechaDateAString(obj.getFechaSolicitudCancelacion()) + "'";
                strQuery += ", a.fechaCancelacion = '" + Util.fechaDateAString(obj.getFechaCancelacion()) + "'";
                strQuery += ", a.usuarioCancela = '" + obj.getUsuarioCancela() + "'";
                strQuery += ", a.observacionCancelacion = '" + obj.getObservacionCancelacion() + "'";
            }

            if (obj.getMaeEstadoPortabilidadCodigo().equals(AsegPortabilidad.ESTADO_PRORROGA)) {
                strQuery += ", a.periodoFinal = '" + Util.fechaDateAString(obj.getPeriodoFinal()) + "' ";
                strQuery += ", a.tipoPortabilidad = '" + obj.getTipoPortabilidad() + "' ";
                if (obj.getMaeTipoPortabilidadId() != null) {
                    strQuery += ", a.maeTipoPortabilidadId = " + obj.getMaeTipoPortabilidadId()+ " ";
                    strQuery += ", a.maeTipoPortabilidadCodigo = '" + obj.getMaeTipoPortabilidadCodigo()+ "' ";
                    strQuery += ", a.maeTipoPortabilidadValor = '" + obj.getMaeTipoPortabilidadValor()+ "' ";
                }
                strQuery += ", a.numeroProrroga = '" + obj.getNumeroProrroga() + "' ";
                strQuery += ", a.mesesProrroga = '" + obj.getMesesProrroga() + "' ";
                strQuery += ", a.fechaProrroga = '" + sdf.format(obj.getFechaSolicitudProrroga()) + "' ";
                strQuery += ", a.observacionAseguramiento = '" + obj.getObservacionProrroga() + "' ";
            }
            //2024-09-27 jyperez adicionamos los campos para actualización
            strQuery += ", a.tipoPortabilidad = " + obj.getTipoPortabilidad()+ " ";
            strQuery += ", a.maeMotivoId = " + obj.getMaeMotivoPortabilidadId() + " ";
            strQuery += ", a.maeMotivoCodigo = '" + obj.getMaeMotivoPortabilidadCodigo()+ "' ";
            strQuery += ", a.maeMotivoValor = '" + obj.getMaeMotivoPortabilidadValor()+ "' ";
            strQuery += ", a.telefonoContacto = '" + obj.getTelefonoContacto()+ "' ";
            strQuery += ", a.telefonoContacto2 = '" + obj.getTelefonoContacto2()+ "' ";
            strQuery += ", a.correoElectronico = '" + obj.getCorreoElectronico()+ "' ";
            strQuery += ", a.observacionUsuario = '" + obj.getObservacionUsuario()+ "' ";
            strQuery += ", a.maeTipoPortabilidadId = " + obj.getMaeTipoPortabilidadId()+ " ";
            strQuery += ", a.maeTipoPortabilidadCodigo = '" + obj.getMaeTipoPortabilidadCodigo()+ "' ";
            strQuery += ", a.maeTipoPortabilidadValor = '" + obj.getMaeTipoPortabilidadValor()+ "' ";
            strQuery += ", a.origenSolicitud = '" + obj.getOrigenSolicitud()+ "' ";
            strQuery += ", a.maeOrigenSolicitudId = " + obj.getMaeOrigenSolicitudId()+ " ";
            strQuery += ", a.maeOrigenSolicitudCodigo = '" + obj.getMaeOrigenSolicitudCodigo()+ "' ";
            strQuery += ", a.maeOrigenSolicitudValor = '" + obj.getMaeOrigenSolicitudValor()+ "' ";
            strQuery += ", a.periodoInicial = '" + Util.fechaDateAString(obj.getPeriodoInicial()) + "' ";
            strQuery += ", a.periodoFinal = '" + Util.fechaDateAString(obj.getPeriodoFinal()) + "' ";
            strQuery += ", a.direccion = '" + obj.getDireccion()+ "' ";

            strQuery += " WHERE a.id = " + obj.getId();
            org.hibernate.Query query = session.createQuery(strQuery);
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AsegPortabilidad eliminar(int id) throws Exception {
        AsegPortabilidad obj = null;
        try {
            AsegPortabilidades ent = getEntityManager().find(AsegPortabilidades.class, id);
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
    public List<AsegPortabilidad> consultarPorAfiliado(int id) throws Exception {
        List<AsegPortabilidad> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegPortabilidades a "
                    + " WHERE a.asegAfiliadosId.id = " + id
                    + " ORDER BY a.fechaHoraCrea DESC";
            List<AsegPortabilidades> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegPortabilidades per : list) {
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
    public AsegPortabilidad consultarPorAfiliadoIdPortabilidad(int idAfiliado, int idPortabilidad) throws Exception {
        AsegPortabilidad objRes = null;
        try {
            String strQuery = "FROM AsegPortabilidades a "
                    + " WHERE a.asegAfiliadosId.id = " + idAfiliado
                    + " AND a.id = " + idPortabilidad
                    + " ORDER BY a.fechaHoraCrea DESC";
            List<AsegPortabilidades> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if (!list.isEmpty()) {
                objRes = castEntidadNegocio(list.get(0));
            } else {
                objRes = null;
            }
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public AsegAfiliado consultarAfiliado(Integer tipodocumento, String numeroDocumento) throws Exception {
        AsegAfiliado afiliadoResult = null;
        try {
            String strQuery = "FROM AsegAfiliados p "
                    + " WHERE p.maeTipoDocumentoId = " + tipodocumento
                    + " AND p.numeroDocumento = '" + numeroDocumento + "' "
                    + " ORDER BY p.maeEstadoAfiliacionId ASC";
            List<AsegAfiliados> list = getEntityManager().createQuery(strQuery).getResultList();
            if (!list.isEmpty()) {
                afiliadoResult = castAfiliadoEntidadNegocioLargo(list.get(0));
            } else {
                afiliadoResult = null;
            }
//            AsegAfiliados obj = (AsegAfiliados) getEntityManager().createQuery(strQuery).getSingleResult();
//            afiliadoResult = castEntidadNegocioLargo(obj);
        } catch (NoResultException e) {
            afiliadoResult = null;
        } catch (Exception e) {
            afiliadoResult = null;
        } finally {
            cerrarEntityManager();
        }
        return afiliadoResult;
    }

    public static AsegPortabilidad castEntidadNegocioCorto(AsegPortabilidades per) {
        AsegPortabilidad obj = new AsegPortabilidad();
        obj.setId(per.getId());
        obj.setPeriodoInicial(per.getPeriodoInicial());
        obj.setPeriodoFinal(per.getPeriodoFinal());
        obj.setFechaCancelacion(per.getFechaCancelacion());
        obj.setOrigenSolicitud(per.getOrigenSolicitud());
        obj.setMaeOrigenSolicitudId(per.getMaeOrigenSolicitudId());
        obj.setMaeOrigenSolicitudCodigo(per.getMaeOrigenSolicitudCodigo());
        obj.setMaeOrigenSolicitudValor(per.getMaeOrigenSolicitudValor());
        obj.setTipoPortabilidad(per.getTipoPortabilidad());
        obj.setMaeTipoPortabilidadId(per.getMaeTipoPortabilidadId());
        obj.setMaeTipoPortabilidadCodigo(per.getMaeTipoPortabilidadCodigo());
        obj.setMaeTipoPortabilidadValor(per.getMaeTipoPortabilidadValor());
        obj.setEstadoPortabilidad(per.getEstadoPortabilidad());
        obj.setMaeEstadoPortabilidadId(per.getMaeEstadoPortabilidadId());
        obj.setMaeEstadoPortabilidadCodigo(per.getMaeEstadoPortabilidadCodigo());
        obj.setMaeEstadoPortabilidadValor(per.getMaeEstadoPortabilidadValor());
        if (per.getAsegAfiliadosId() != null) {
            AsegAfiliado afiliado = new AsegAfiliado(
                            per.getAsegAfiliadosId().getId(),
                            per.getAsegAfiliadosId().getIdAfiliado(),
                            per.getAsegAfiliadosId().getPrimerNombre(),
                            per.getAsegAfiliadosId().getSegundoNombre(),
                            per.getAsegAfiliadosId().getPrimerApellido(),
                            per.getAsegAfiliadosId().getSegundoApellido(),
                            per.getAsegAfiliadosId().getGenero(),
                            per.getAsegAfiliadosId().getMaeTipoDocumentoId(),
                            per.getAsegAfiliadosId().getNumeroDocumento(),
                            per.getAsegAfiliadosId().getMaeEstadoAfiliacionId()
                    );
            afiliado.setMaeEstadoAfiliacionCodigo(per.getAsegAfiliadosId().getMaeEstadoAfiliacionCodigo());
            afiliado.setMaeEstadoAfiliacionValor(per.getAsegAfiliadosId().getMaeEstadoAfiliacionValor());
            afiliado.setMaeTipoDocumentoCodigo(per.getAsegAfiliadosId().getMaeTipoDocumentoCodigo());
            afiliado.setMaeTipoDocumentoValor(per.getAsegAfiliadosId().getMaeTipoDocumentoValor());
            afiliado.setMaeGeneroId(per.getAsegAfiliadosId().getMaeGeneroId());
            afiliado.setMaeGeneroCodigo(per.getAsegAfiliadosId().getMaeGeneroCodigo());
            afiliado.setMaeGeneroValor(per.getAsegAfiliadosId().getMaeGeneroValor());
            obj.setAsegAfiliado(afiliado);
        }
        if (per.getUbicacionesId() != null) {
            obj.setUbicacion(new Ubicacion(per.getUbicacionesId().getId()));
        }
        if (per.getCntPrestadorSedesId() != null) {
            CntPrestador prestador = new CntPrestador(
                    per.getCntPrestadorSedesId().getCntPrestadoresId().getId(),
                    per.getCntPrestadorSedesId().getCntPrestadoresId().getRazonSocial()
            );
            obj.setCntPrestadorSede(
                    new CntPrestadorSede(
                            per.getCntPrestadorSedesId().getId(),
                            per.getCntPrestadorSedesId().getNombre(),
                            prestador
                    )
            );
        }
        return obj;
    }

    public static AsegPortabilidad castEntidadNegocio(AsegPortabilidades per) {
        AsegPortabilidad obj = new AsegPortabilidad();
        obj.setId(per.getId());
        if (per.getAsegAfiliadosId() != null) {
            obj.setAsegAfiliado(castAfiliadoEntidadNegocioLargo(per.getAsegAfiliadosId()));
        }
        obj.setTipoPortabilidad(per.getTipoPortabilidad());
        obj.setMaeTipoPortabilidadId(per.getMaeTipoPortabilidadId());
        obj.setMaeTipoPortabilidadCodigo(per.getMaeTipoPortabilidadCodigo());
        obj.setMaeTipoPortabilidadValor(per.getMaeTipoPortabilidadValor());
        obj.setOrigenSolicitud(per.getOrigenSolicitud());
        obj.setMaeOrigenSolicitudId(per.getMaeOrigenSolicitudId());
        obj.setMaeOrigenSolicitudCodigo(per.getMaeOrigenSolicitudCodigo());
        obj.setMaeOrigenSolicitudValor(per.getMaeOrigenSolicitudValor());
        obj.setPeriodoInicial(per.getPeriodoInicial());
        obj.setPeriodoFinal(per.getPeriodoFinal());
        if (per.getUbicacionesId() != null) {
            obj.setUbicacion(new Ubicacion(per.getUbicacionesId().getId()));
            obj.getUbicacion().setNombre(per.getUbicacionesId().getNombre());
        }
        obj.setDireccion(per.getDireccion());
        obj.setTelefonoContacto(per.getTelefonoContacto());
        obj.setTelefonoContacto2(per.getTelefonoContacto2());
        obj.setCorreoElectronico(per.getCorreoElectronico());
        obj.setObservacionUsuario(per.getObservacionUsuario());
        if (per.getCntPrestadorSedesId() != null) {
            CntPrestador prestador = new CntPrestador(
                    per.getCntPrestadorSedesId().getCntPrestadoresId().getId(),
                    per.getCntPrestadorSedesId().getCntPrestadoresId().getRazonSocial()
            );
            obj.setCntPrestadorSede(
                    new CntPrestadorSede(
                            per.getCntPrestadorSedesId().getId(),
                            per.getCntPrestadorSedesId().getNombre(),
                            prestador
                    )
            );
        }
        obj.setEstadoPortabilidad(per.getEstadoPortabilidad());
        obj.setMaeEstadoPortabilidadId(per.getMaeEstadoPortabilidadId());
        obj.setMaeEstadoPortabilidadCodigo(per.getMaeEstadoPortabilidadCodigo());
        obj.setMaeEstadoPortabilidadValor(per.getMaeEstadoPortabilidadValor());
        obj.setObservacionAseguramiento(per.getObservacionAseguramiento());
        obj.setObservacionProrrogaAnterior(per.getObservacionAseguramiento());
        obj.setNumeroProrroga(per.getNumeroProrroga());
        obj.setMesesProrrogaAnterior(per.getMesesProrroga());
        obj.setFechaSolicitudProrrogaAnterior(per.getFechaProrroga());
        obj.setFechaSolicitudCancelacion(per.getFechaSolicitudCancelacion());
        obj.setFechaCancelacion(per.getFechaCancelacion());
        obj.setUsuarioCancela(per.getUsuarioCancela());
        obj.setObservacionCancelacion(per.getObservacionCancelacion());
        //Auditoría
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setMaeMotivoPortabilidadId(per.getMaeMotivoId());
        obj.setMaeMotivoPortabilidadCodigo(per.getMaeMotivoCodigo());
        obj.setMaeMotivoPortabilidadValor(per.getMaeMotivoValor());
        obj.setEnvioCorreo(Integer.valueOf(per.getEnvioCorreo()));
        return obj;
    }

    public static AsegPortabilidades castNegocioEntidad(AsegPortabilidad obj) {
        AsegPortabilidades per = new AsegPortabilidades();
        per.setId(obj.getId());
        if (obj.getAsegAfiliado() != null) {
            per.setAsegAfiliadosId(new AsegAfiliados(obj.getAsegAfiliado().getId()));
        }
        per.setTipoPortabilidad(obj.getTipoPortabilidad());
        per.setMaeTipoPortabilidadId(obj.getMaeTipoPortabilidadId());
        per.setMaeTipoPortabilidadCodigo(obj.getMaeTipoPortabilidadCodigo());
        per.setMaeTipoPortabilidadValor(obj.getMaeTipoPortabilidadValor());
        per.setOrigenSolicitud(obj.getOrigenSolicitud());
        per.setMaeOrigenSolicitudId(obj.getMaeOrigenSolicitudId());
        per.setMaeOrigenSolicitudCodigo(obj.getMaeOrigenSolicitudCodigo());
        per.setMaeOrigenSolicitudValor(obj.getMaeOrigenSolicitudValor());
        per.setPeriodoInicial(obj.getPeriodoInicial());
        per.setPeriodoFinal(obj.getPeriodoFinal());
        if (obj.getUbicacion() != null) {
            per.setUbicacionesId(new GnUbicaciones(obj.getUbicacion().getId()));
        }
        per.setDireccion(obj.getDireccion());
        per.setTelefonoContacto(obj.getTelefonoContacto());
        per.setTelefonoContacto2(obj.getTelefonoContacto2());
        per.setCorreoElectronico(obj.getCorreoElectronico());
        per.setObservacionUsuario(obj.getObservacionUsuario());
        if (obj.getCntPrestadorSede() != null) {
            per.setCntPrestadorSedesId(new CntPrestadorSedes(obj.getCntPrestadorSede().getId()));
        }
        per.setEstadoPortabilidad(obj.getEstadoPortabilidad());
        per.setMaeEstadoPortabilidadId(obj.getMaeEstadoPortabilidadId());
        per.setMaeEstadoPortabilidadCodigo(obj.getMaeEstadoPortabilidadCodigo());
        per.setMaeEstadoPortabilidadValor(obj.getMaeEstadoPortabilidadValor());
        per.setObservacionAseguramiento(obj.getObservacionAseguramiento());
        per.setFechaSolicitudCancelacion(obj.getFechaSolicitudCancelacion());
        per.setFechaCancelacion(obj.getFechaCancelacion());
        per.setUsuarioCancela(obj.getUsuarioCancela());
        per.setNumeroProrroga(obj.getNumeroProrroga());
        per.setMesesProrroga(obj.getMesesProrroga());
        per.setFechaProrroga(obj.getFechaSolicitudProrroga());
        //Audotiría
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setMaeMotivoId(obj.getMaeMotivoPortabilidadId());
        per.setMaeMotivoCodigo(obj.getMaeMotivoPortabilidadCodigo());
        per.setMaeMotivoValor(obj.getMaeMotivoPortabilidadValor());
        per.setEnvioCorreo(Short.parseShort(obj.getEnvioCorreo().toString()));
        return per;
    }

    @Override
    public ReportePortabilidad reportePortabilidad(String id) {
        ReportePortabilidad reporte = new ReportePortabilidad();
        try {

            String strQuery = "SELECT "
                    + "af.primerNombre, "
                    + "af.segundoNombre, "
                    + "af.primerApellido, "
                    + "af.segundoApellido, "
                    + "af.idAfiliado, "
                    //2022-09-23 jyperez ajustamos campo genero para que reciba simplemente el maeGenerovalor.
                    + "af.maeGeneroValor, "
                    + "af.numeroDocumento, "
                    + "DATE_FORMAT(af.fechaNacimiento, '%d-%m-%Y'), "
                    + "DATE_FORMAT(af.fechaSisben, '%d-%m-%Y'), "
                    + "ub.nombre, "
                    + "po.maeEstadoPortabilidadValor, "
                    + "ips.razonSocial, "
                    + "sdr.nombre, "
                    + "DATE_FORMAT(po.periodoInicial, '%d-%m-%Y'), "
                    + "DATE_FORMAT(po.periodoFinal, '%d-%m-%Y'), "
                    + "ub.gnUbicacionesId.nombre "
                    + "FROM AsegPortabilidades po "
                    + "INNER JOIN po.asegAfiliadosId af "
                    + "INNER JOIN po.ubicacionesId ub "
                    + "LEFT OUTER JOIN po.cntPrestadorSedesId sdr "
                    + "LEFT OUTER JOIN sdr.cntPrestadoresId ips "
                    + "WHERE po.id = " + id + " ";

            Query query = getEntityManager().createQuery(strQuery);
            List<Object[]> lista = query.getResultList();
            if (lista.size() > 0) {
                Object[] objeto = lista.get(0);
                reporte.setId(id);
                String nombreCompleto = objeto[0].toString() + " ";
                if (objeto[1] != null) {
                    nombreCompleto = nombreCompleto + objeto[1].toString() + " ";
                }
                nombreCompleto = nombreCompleto + objeto[2].toString() + " " + objeto[3].toString();
                reporte.setStrAfiliado(nombreCompleto);
                reporte.setStrIdentificacion(objeto[6].toString());
                if (objeto[5] != null) {
                    reporte.setStrGenero(objeto[5].toString());
                } else {
                    reporte.setStrGenero("");
                }
                reporte.setStrFechaNacimiento(objeto[7].toString());
                String fechaSisben = " ";
                if (objeto[8] != null) {
                    fechaSisben = objeto[8].toString();
                }
                reporte.setStrFechaSisben(fechaSisben);
                reporte.setStrMunicipioAfiliacion(objeto[9].toString() + " - " + objeto[15].toString());
                reporte.setStrEstadoAfiliacion(objeto[10].toString());
                if (objeto[11] != null) {
                    reporte.setStrIpsPortabilidad(objeto[11].toString());
                }else {
                    reporte.setStrIpsPortabilidad("");
                }
                if (objeto[12] != null) {
                    reporte.setStrSedeIpsPortabilidad(objeto[12].toString());
                }else {
                    reporte.setStrSedeIpsPortabilidad("");
                }
                reporte.setStrFechaInicial(objeto[13].toString());
                reporte.setStrFechaFinal(objeto[14].toString());
            }
        } catch (NoResultException e) {
            reporte = new ReportePortabilidad();
        } catch (ParseException e) {
            System.out.println(e);
        } finally {
            cerrarEntityManager();
        }
        return reporte;
    }
    
    @Override
    public ReportePortabilidad reporteUtimaPortabilidadAfiliado(String id) {
        ReportePortabilidad reporte = null;
        try {

            String strQuery = "SELECT "
                    + "af.primerNombre, "
                    + "af.segundoNombre, "
                    + "af.primerApellido, "
                    + "af.segundoApellido, "
                    + "af.idAfiliado, "
                    //2022-09-23 jyperez ajustamos campo genero para que reciba simplemente el maeGenerovalor.
                    + "af.maeGeneroValor, "
                    + "af.numeroDocumento, "
                    + "DATE_FORMAT(af.fechaNacimiento, '%d-%m-%Y'), "
                    + "DATE_FORMAT(af.fechaSisben, '%d-%m-%Y'), "
                    + "ub.nombre, "
                    + "po.maeEstadoPortabilidadValor, "
                    + "ips.razonSocial, "
                    + "sdr.nombre, "
                    + "DATE_FORMAT(po.periodoInicial, '%d-%m-%Y'), "
                    + "DATE_FORMAT(po.periodoFinal, '%d-%m-%Y'), "
                    + "ub.gnUbicacionesId.nombre "
                    + "FROM AsegPortabilidades po "
                    + "INNER JOIN po.asegAfiliadosId af "
                    + "INNER JOIN po.ubicacionesId ub "
                    + "LEFT OUTER JOIN po.cntPrestadorSedesId sdr "
                    + "LEFT OUTER JOIN sdr.cntPrestadoresId ips "
                    + "WHERE af.id = " + id + " "
                    + "ORDER BY po.id DESC ";

            Query query = getEntityManager().createQuery(strQuery);
            List<Object[]> lista = query.getResultList();
            if (lista.size() > 0) {
                Object[] objeto = lista.get(0);
                reporte = new ReportePortabilidad();
                reporte.setId(id);
                String nombreCompleto = objeto[0].toString() + " ";
                if (objeto[1] != null) {
                    nombreCompleto = nombreCompleto + objeto[1].toString() + " ";
                }
                nombreCompleto = nombreCompleto + objeto[2].toString() + " " + objeto[3].toString();
                reporte.setStrAfiliado(nombreCompleto);
                reporte.setStrIdentificacion(objeto[6].toString());
                if (objeto[5] != null) {
                    reporte.setStrGenero(objeto[5].toString());
                } else {
                    reporte.setStrGenero("");
                }
                reporte.setStrFechaNacimiento(objeto[7].toString());
                String fechaSisben = " ";
                if (objeto[8] != null) {
                    fechaSisben = objeto[8].toString();
                }
                reporte.setStrFechaSisben(fechaSisben);
                reporte.setStrMunicipioAfiliacion(objeto[9].toString() + " - " + objeto[15].toString());
                reporte.setStrEstadoAfiliacion(objeto[10].toString());
                if (objeto[11] != null) {
                    reporte.setStrIpsPortabilidad(objeto[11].toString());
                }else {
                    reporte.setStrIpsPortabilidad("");
                }
                if (objeto[12] != null) {
                    reporte.setStrSedeIpsPortabilidad(objeto[12].toString());
                }else {
                    reporte.setStrSedeIpsPortabilidad("");
                }
                reporte.setStrFechaInicial(objeto[13].toString());
                reporte.setStrFechaFinal(objeto[14].toString());
            }
        } catch (NoResultException e) {
            reporte = null;
        } catch (ParseException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            cerrarEntityManager();
        }
        return reporte;
    }

    public static AsegAfiliado castAfiliadoEntidadNegocioLargo(AsegAfiliados per) {
        AsegAfiliado obj = new AsegAfiliado();
        obj.setId(per.getId());
        obj.setIdAfiliado(per.getIdAfiliado());
        obj.setSerialBdua(per.getSerialBdua());
        obj.setRegistroBdua(per.getRegistroBdua());
        obj.setPrimerNombre(per.getPrimerNombre());
        obj.setSegundoNombre(per.getSegundoNombre());
        obj.setPrimerApellido(per.getPrimerApellido());
        obj.setSegundoApellido(per.getSegundoApellido());
        obj.setFechaNacimiento(per.getFechaNacimiento());
        obj.setGenero(per.getGenero());
        obj.setFechaExpedicionCedula(per.getFechaExpedicionCedula());
        obj.setMaeTipoDocumento(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setNumeroDocumento(per.getNumeroDocumento());
        obj.setMaeTipoDocumentoCf(per.getMaeTipoDocumentoCfId());
        obj.setMaeTipoDocumentoCfCodigo(per.getMaeTipoDocumentoCfCodigo());
        obj.setMaeTipoDocumentoCfValor(per.getMaeTipoDocumentoCfValor());
        obj.setNumeroDocumentoCf(per.getNumeroDocumentoCf());
        obj.setFechaAfiliacionSgsss(per.getFechaAfiliacionSgsss());
        obj.setFechaAfiliacionEps(per.getFechaAfiliacionEps());
        obj.setFechaEgresoEps(per.getFechaEgresoEps());
        obj.setFechaCambioEstadoEps(per.getFechaCambioEstadoEps());
        obj.setTipoBeneficiario(per.getTipoBeneficiario());
        obj.setMaeTipoAfiliadoId(per.getMaeTipoAfiliadoId());
        obj.setMaeTipoAfiliadoCodigo(per.getMaeTipoAfiliadoCodigo());
        obj.setMaeTipoAfiliadoValor(per.getMaeTipoAfiliadoValor());
        obj.setMaeEstadoAfiliacion(per.getMaeEstadoAfiliacionId());
        obj.setMaeEstadoAfiliacionCodigo(per.getMaeEstadoAfiliacionCodigo());
        obj.setMaeEstadoAfiliacionValor(per.getMaeEstadoAfiliacionValor());
        obj.setMaeOrigenAfiliado(per.getMaeOrigenAfiliadoId());
        obj.setMaeOrigenAfiliadoCodigo(per.getMaeOrigenAfiliadoCodigo());
        obj.setMaeOrigenAfiliadoValor(per.getMaeOrigenAfiliadoCodigo());
        obj.setParentescoCotizante(per.getParentescoCotizante());
        obj.setMaeParentescoCotizanteId(per.getMaeParentescoCotizanteId());
        obj.setMaeParentescoCotizanteCodigo(per.getMaeParentescoCotizanteCodigo());
        obj.setMaeParentescoCotizanteValor(per.getMaeParentescoCotizanteValor());
        obj.setAutorizaEnvioSms(per.getAutorizaEnvioSms());
        obj.setAutorizaEnvioEmail(per.getAutorizaEnvioEmail());
        obj.setTelefonoFijo(per.getTelefonoFijo());
        obj.setTelefonoMovil(per.getTelefonoMovil());
        obj.setEmail(per.getEmail());
        obj.setZona(per.getZona());
        obj.setMaeZonaId(per.getMaeZonaId());
        obj.setMaeZonaCodigo(per.getMaeZonaCodigo());
        obj.setMaeZonaValor(per.getMaeZonaValor());
        obj.setDireccionResidencia(per.getDireccionResidencia());
        obj.setBarrio(per.getBarrio());
        obj.setRegimen(per.getRegimen());
        obj.setMaeRegimenId(per.getMaeRegimenId());
        obj.setMaeRegimenCodigo(per.getMaeRegimenCodigo());
        obj.setMaeRegimenValor(per.getMaeRegimenValor());
        obj.setNivelSisben(per.getNivelSisben());
        obj.setMaeNivelSisbenId(per.getMaeNivelSisbenId());
        obj.setMaeNivelSisbenCodigo(per.getMaeNivelSisbenCodigo());
        obj.setMaeNivelSisbenValor(per.getMaeNivelSisbenValor());
        if (per.getPuntajeSisben() != null) {
            obj.setPuntajeSisben(per.getPuntajeSisben().doubleValue());
        }
        obj.setFichaSisben(per.getFichaSisben());
        obj.setFechaSisben(per.getFechaSisben());
        obj.setCategoriaIbc(per.getCategoriaIbc());
        obj.setMaeCategoriaIbcId(per.getMaeCategoriaIbcId());
        obj.setMaeCategoriaIbcCodigo(per.getMaeCategoriaIbcCodigo());
        obj.setMaeCategoriaIbcValor(per.getMaeCategoriaIbcValor());
        obj.setTienePortabilidad(per.getTienePortabilidad());
        obj.setFechaPortabilidad(per.getFechaPortabilidad());
        obj.setTipoCotizante(per.getTipoCotizante());
        obj.setMaeTipoCotizanteId(per.getMaeTipoCotizanteId());
        obj.setMaeTipoCotizanteCodigo(per.getMaeTipoCotizanteCodigo());
        obj.setMaeTipoCotizanteValor(per.getMaeTipoCotizanteValor());
        obj.setDiscapacidad(per.getDiscapacidad());
        obj.setTipoDiscapacidad(per.getTipoDiscapacidad());
        obj.setCondicionDiscapacidad(per.getCondicionDiscapacidad());
        obj.setMaeCondicionDiscapacidadId(per.getMaeDiscapacidadCondicionId());
        obj.setMaeCondicionDiscapacidadCodigo(per.getMaeDiscapacidadCondicionCodigo());
        obj.setMaeCondicionDiscapacidadValor(per.getMaeDiscapacidadCondicionValor());
        obj.setFechaIniciodiscapacidad(per.getFechaInicioDiscapacidad());
        obj.setFechaFinDiscapacidad(per.getFechaFinDiscapacidad());
        obj.setMaeGrupoPoblacional(per.getMaeGrupoPoblacionalId());
        obj.setMaeGrupoPoblacionalCodigo(per.getMaeGrupoPoblacionalCodigo());
        obj.setMaeGrupoPoblacionalValor(per.getMaeGrupoPoblacionalValor());
        obj.setVictima(per.getVictima());
        obj.setEtnia(per.getEtnia());
        obj.setMaeEtniaId(per.getMaeEtniaId());
        obj.setMaeEtniaCodigo(per.getMaeEtniaCodigo());
        obj.setMaeEtniaValor(per.getMaeEtniaValor());
        obj.setMaeCausaNovedad(per.getMaeCausaNovedadId());
        obj.setMaeCausaNovedadCodigo(per.getMaeCausaNovedadCodigo());
        obj.setMaeCausaNovedadValor(per.getMaeCausaNovedadValor());
        obj.setFechaReactivacion(per.getFechaReactivacion());
        obj.setEstadoCivil(per.getEstadoCivil());
        obj.setMaeEstadoCivilId(per.getMaeEstadoCivilId());
        obj.setMaeEstadoCivilCodigo(per.getMaeEstadoCivilCodigo());
        obj.setMaeEstadoCivilValor(per.getMaeEstadoCivilValor());
        obj.setFechaMovilidad(per.getFechaMovilidad());
        obj.setModeloLiquidacion(per.getModeloLiquidacion());
        obj.setMaeModeloLiquidacionId(per.getMaeModeloLiquidacionId());
        obj.setMaeModeloLiquidacionCodigo(per.getMaeModeloLiquidacionCodigo());
        obj.setMaeModeloLiquidacionValor(per.getMaeModeloLiquidacionValor());
        obj.setMaeTipoDocumentoAportante(per.getMaeTipoDocumentoAportanteId());
        obj.setMaeTipoDocumentoAportanteCodigo(per.getMaeTipoDocumentoAportanteCodigo());
        obj.setMaeTipoDocumentoAportanteValor(per.getMaeTipoDocumentoAportanteValor());
        obj.setNumeroDocumentoAportante(per.getNumeroDocumentoAportante());
        obj.setMaeActividadEconomica(per.getMaeActividadEconomicaId());
        obj.setMaeActividadEconomicaCodigo(per.getMaeActividadEconomicaCodigo());
        obj.setMaeActividadEconomicaValor(per.getMaeActividadEconomicaValor());
        obj.setMaeArl(per.getMaeArlId());
        obj.setMaeArlCodigo(per.getMaeArlCodigo());
        obj.setMaeArlValor(per.getMaeArlValor());
        obj.setMaeAfp(per.getMaeAfpId());
        obj.setMaeAfpCodigo(per.getMaeAfpCodigo());
        obj.setMaeAfpValor(per.getMaeAfpValor());
        obj.setMaeCajaCompensacion(per.getMaeCajaCompensacionId());
        obj.setMaeCajaCompensacionCodigo(per.getMaeCajaCompensacionCodigo());
        obj.setMaeCajaCompensacionValor(per.getMaeCajaCompensacionValor());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setObservacionNovedad(per.getObservacion());
        if (per.getPrimariaCntPrestadorSedesId() != null) {
            obj.setPrimariaPrestadorSede(castPrestadorSedesEntidadNegocio(per.getPrimariaCntPrestadorSedesId()));
        }
        if (per.getPortabilidadCntPrestadorSedesId() != null) {
            obj.setPortabilidadPrestadorSede(castPrestadorSedesEntidadNegocio(per.getPortabilidadCntPrestadorSedesId()));
        }
        if (per.getNacimientoUbicacionesId() != null) {
            obj.setNacimientoUbicacion(castUbicacionEntidadNegocio(per.getNacimientoUbicacionesId()));
        }
        if (per.getAfiliacionUbicacionesId() != null) {
            obj.setAfiliacionUbicacion(castUbicacionEntidadNegocio(per.getAfiliacionUbicacionesId()));
        }
        return obj;
    }
    
    public static Ubicacion castUbicacionEntidadNegocio(GnUbicaciones per) {
        Ubicacion obj = new Ubicacion();
        if (per != null) {
            obj.setId(per.getId());
            obj.setNombre(per.getNombre());
            obj.setTipo(per.getTipo());
            obj.setPrefijo(per.getPrefijo());
            // 2020-10-21 jyperez ajuste para soportar datos de la ubicación padre - Req Validacion Derechos de Afiliado
            if (per.getGnUbicacionesId() != null) {
                obj.setUbicacionPadre(new Ubicacion(null, per.getGnUbicacionesId().getId(), per.getGnUbicacionesId().getTipo(), per.getGnUbicacionesId().getCodigoPostal(), per.getGnUbicacionesId().getNombre()));
                obj.getUbicacionPadre().setPrefijo(per.getGnUbicacionesId().getPrefijo());
            }
        }
        return obj;
    }

    public static CntPrestadorSede castPrestadorSedesEntidadNegocio(CntPrestadorSedes per) {
        CntPrestadorSede obj = new CntPrestadorSede();
        if (per != null) {
            obj.setId(per.getId());
            obj.setUbicacionId(per.getUbicacionId());
            obj.setCodigoPrestador(per.getCodigoPrestador());
            obj.setDireccion(per.getDireccion());
            obj.setNombreSede(per.getNombre());
            obj.setCodigoSede(per.getCodigo());
            obj.setCodigoHabilitacionSede(per.getCodigoHabilitacion());
            obj.setZonaPrecedencia(per.getZonaPrecedencia());
            obj.setEstadoSede(per.getEstadoSede());
            obj.setNivelAtencion(per.getNivelAtencion());
            obj.setClasePrestador(per.getMaeClasePrestadorId());
            obj.setFax(per.getFax());
            obj.setTelefonoCitas(per.getTelefonoCitas());
            obj.setCorreoElectronico(per.getCorreoElectronico());
            obj.setTelefonoAdministrativo(per.getTelefonoAdministrativo());
            obj.setCapitacion(per.getCapitacion());
            if (per.getCntPrestadoresId() != null) {
                CntPrestador cntPrestador = new CntPrestador();
                cntPrestador.setId(per.getCntPrestadoresId().getId());
                cntPrestador.setMaeTipoDocumentoCodigo(per.getCntPrestadoresId().getMaeTipoDocumentoCodigo());
                cntPrestador.setMaeTipoDocumentoValor(per.getCntPrestadoresId().getMaeTipoDocumentoValor());
                cntPrestador.setNumeroDocumento(per.getCntPrestadoresId().getNumeroDocumento());
                cntPrestador.setRazonSocial(per.getCntPrestadoresId().getRazonSocial());
                obj.setCntPrestador(cntPrestador);
            }
            // no se mapea prestador
        }
        return obj;
    }
    
    @Override
    public AsegPortabilidad consultarPortabilidadAfiliadoVigente(int idAfiliado) throws Exception {
        AsegPortabilidad resultado = null;
        try {
            String strQuery = "SELECT a FROM AsegPortabilidades a "
                    + " WHERE a.asegAfiliadosId.id = " + idAfiliado
                    + " AND a.maeEstadoPortabilidadId IN ( SELECT mr.gnMaestrosId.id FROM GnMaestroAccionRelaciones mr WHERE mr.gnMaestroAccionesId.id = 5) "
                    + " ORDER BY a.periodoFinal DESC";
            List<AsegPortabilidades> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if(!list.isEmpty()){
                resultado = castEntidadNegocio(list.get(0));
            }else{
                resultado = null;
            }
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return resultado;
    }
    
    /**
     *
     * @param idUbicacion
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public List<CntPrestadorSede> municipioAplicaPortabilidad(int idUbicacion) throws java.lang.Exception{
        List<CntPrestadorSede> listado = new ArrayList<>();
         try {
            StringBuilder strQuery = new StringBuilder("FROM CntPrestadorSedes ps ");
                    strQuery.append(" WHERE ps.capitacion = 1 ");
                    strQuery.append(" AND ps.ubicacionId in(").append(idUbicacion).append(") ") ;
            List<CntPrestadorSedes> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for(CntPrestadorSedes sd : list){
                listado.add(castPrestadorSedesEntidadNegocio(sd));
            }
        } catch (NoResultException e) {
            listado = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listado;
    }
    
    
}
