/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.aseguramiento;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegMaNovedad;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegRadicadoNovedad;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegRegistroNovedad;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegReporteNovedad;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegMaNovedades;
import com.saviasaludeps.savia.ejb.entidades.AsegRadicadoNovedades;
import com.saviasaludeps.savia.ejb.entidades.AsegRegistroNovedades;
import com.saviasaludeps.savia.ejb.entidades.AsegReporteNovedades;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.aseguramiento.NovedadAfiliadoRemoto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Session;

@Stateless
@Remote(NovedadAfiliadoRemoto.class)
public class NovedadAfiliadoServicio extends GenericoServicio implements NovedadAfiliadoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AsegRegistroNovedades p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getEmpresaId() != null && paramConsulta.getEmpresaId() != 0) {
                strQuery += "AND p.radicadoNovedadesId.asegAfiliadosId.id =  " + paramConsulta.getEmpresaId() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "asegMaNovedad.descripcionNovedad":
                            strQuery += "AND p.asegMaNovedadesId.descripcionNovedad LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "asegMaNovedad.codigoNovedad":
                            strQuery += "AND p.asegMaNovedadesId.codigoNovedad LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicadoNovedad.id":
                            strQuery += "AND p.radicadoNovedadesId.id = " + e.getValue() + " ";
                            break;
                        case "fechaNovedad":
                            strQuery += "AND p.fechaNovedad = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += "AND p.fechaHoraCrea = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaMarcacion":
                            strQuery += "AND p.fechaMarcacion = '" + (String) e.getValue() + "' ";
                            break;
                        case "descripcionValorAnterior":
                            strQuery += "AND p.descripcionValorAnterior LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "descripcionValorNuevo":
                            strQuery += "AND p.descripcionValorNuevo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public List<AsegRegistroNovedad> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<AsegRegistroNovedad> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegRegistroNovedades p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getEmpresaId() != null && paramConsulta.getEmpresaId() != 0) {
                strQuery += "AND p.radicadoNovedadesId.asegAfiliadosId.id =  " + paramConsulta.getEmpresaId() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "asegMaNovedad.descripcionNovedad":
                            strQuery += "AND p.asegMaNovedadesId.descripcionNovedad LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "asegMaNovedad.codigoNovedad":
                            strQuery += "AND p.asegMaNovedadesId.codigoNovedad LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "radicadoNovedad.id":
                            strQuery += "AND p.radicadoNovedadesId.id = " + e.getValue() + " ";
                            break;
                        case "fechaNovedad":
                            strQuery += "AND p.fechaNovedad = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += "AND p.fechaHoraCrea = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaMarcacion":
                            strQuery += "AND p.fechaMarcacion = '" + (String) e.getValue() + "' ";
                            break;
                        case "descripcionValorAnterior":
                            strQuery += "AND p.descripcionValorAnterior LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "descripcionValorNuevo":
                            strQuery += "AND p.descripcionValorNuevo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    case "asegMaNovedad.descripcionNovedad":
                            strQuery += "p.asegMaNovedadesId.descripcionNovedad "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                            break;
                    case "asegMaNovedad.codigoNovedad":
                        strQuery += "p.asegMaNovedadesId.codigoNovedad "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "radicadoNovedad.id":
                        strQuery += "p.radicadoNovedadesId.id "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    default:
                        strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                }
            } else {
                strQuery += "p.fechaHoraCrea DESC";
            }
            List<AsegRegistroNovedades> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (AsegRegistroNovedades per : list) {
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
    public AsegRegistroNovedad consultar(int id) throws java.lang.Exception {
        AsegRegistroNovedad objRes = null;
        try {
            objRes = castEntidadNegocio((AsegRegistroNovedades) getEntityManager().find(AsegRegistroNovedades.class, id));
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
    public int insertar(AsegRegistroNovedad obj) throws java.lang.Exception {
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
    public void actualizar(AsegRegistroNovedad obj) throws java.lang.Exception {
        try {
            //getEntityManager().merge(castNegocioEntidad(obj));
            AsegRegistroNovedades regn = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AsegRegistroNovedades a SET ";
            strQuery += " a.idAfiliado = :idAfiliado ,";
            strQuery += " a.valorAnterior = :valorAnterior ,";
            strQuery += " a.valorNuevo = :valorNuevo ,";
            strQuery += " a.fechaMarcacion = :fechaMarcacion ,";
            strQuery += " a.maeEstadoNovedad = :maeEstadoNovedad ,";
            strQuery += " a.observacion = :observacion ,";
            strQuery += " a.sincronizado = :sincronizado ,";
            strQuery += " a.usuarioCrea = :usuarioCrea ,";
            strQuery += " a.fechaHoraCrea = :fechaHoraCrea ,";
            strQuery += " a.terminalCrea = :terminalCrea ,";
            strQuery += " a.usuarioModifica = :usuarioModifica ,";
            strQuery += " a.fechaHoraModifica = :fechaHoraModifica ,";
            strQuery += " a.terminalModifica = :terminalModifica ";
            //campos objetos
            if (regn.getRadicadoNovedadesId()!= null) {
                strQuery += ", a.radicadoNovedadesId.id = " + regn.getRadicadoNovedadesId().getId() + " ";
            }
            if (regn.getAsegMaNovedadesId() != null) {
                strQuery += ", a.asegMaNovedadesId.id = " + regn.getAsegMaNovedadesId().getId() + " ";
            }
            if (regn.getAsegReporteNovedadesId()!= null) {
                strQuery += ", a.asegReporteNovedadesId.id = " + regn.getAsegReporteNovedadesId().getId() + " ";
            }
            
            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(regn);
            query.executeUpdate();
            
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AsegRegistroNovedad eliminar(int id) throws java.lang.Exception {
        AsegRegistroNovedad obj = null;
        try {
            AsegRegistroNovedades ent = getEntityManager().find(AsegRegistroNovedades.class, id);
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

    public static AsegRegistroNovedad castEntidadNegocio(AsegRegistroNovedades per) {
        AsegRegistroNovedad obj = new AsegRegistroNovedad();
        obj.setId(per.getId());
        obj.setIdAfiliado(per.getIdAfiliado());
        obj.setFechaNovedad(per.getFechaNovedad());
        obj.setValorAnterior(per.getValorAnterior());
        obj.setDescripcionValorAnterior(per.getDescripcionValorAnterior());
        obj.setValorNuevo(per.getValorNuevo());
        obj.setDescripcionValorNuevo(per.getDescripcionValorNuevo());
        obj.setFechaMarcacion(per.getFechaMarcacion());
        obj.setAsegInformesIdMarcacion(per.getAsegInformesIdMarcacion());
        obj.setMaeEstadoNovedad(per.getMaeEstadoNovedad());// validar porqué esta este campo si tenemos una relación con la tabla maestra
        obj.setObservacion(per.getObservacion());
        obj.setSincronizado(per.getSincronizado());
        obj.setOrigenUltimoRegistro(per.getOrigenUltimoRegistro());
        obj.setDescripcionOrigenUltimoRegistro(per.getDescripcionOrigenUltimoRegistro());
        //auditoria
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        //objetos
        if (per.getRadicadoNovedadesId() != null) {
            obj.setRadicadoNovedad(new AsegRadicadoNovedad(
                    per.getRadicadoNovedadesId().getId(),
                    per.getRadicadoNovedadesId().getSoporteNovedad(),
                    per.getRadicadoNovedadesId().getHistoricoAfiliado(),
                    per.getRadicadoNovedadesId().getFechaHoraCrea()
            ));
        }
        if (per.getAsegMaNovedadesId() != null) {
            obj.setAsegMaNovedad(castMaestroNovedadesEntidadNegocio(per.getAsegMaNovedadesId()));
        }
        if (per.getAsegReporteNovedadesId() != null) {
            obj.setAsegReporteNovedad(new AsegReporteNovedad(per.getAsegReporteNovedadesId().getId()));
        }

        return obj;
    }

    @Override
    public List<AsegRadicadoNovedad> consultarPorAfiliado(int id) throws java.lang.Exception {
        List<AsegRadicadoNovedad> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegRadicadoNovedades a "
                    + " WHERE a.asegAfiliadosId.id = " + id
                    + " ORDER BY a.fechaHoraCrea DESC";
            List<AsegRadicadoNovedades> list = getEntityManager().createQuery(strQuery).getResultList();
            for (AsegRadicadoNovedades per : list) {
                listResult.add(castRadicadoNovedadesEntidadNegocio(per));
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

    public static AsegRegistroNovedades castNegocioEntidad(AsegRegistroNovedad obj) {
        AsegRegistroNovedades per = new AsegRegistroNovedades();
        per.setId(obj.getId());
        per.setIdAfiliado(obj.getIdAfiliado());
        per.setFechaNovedad(obj.getFechaNovedad());
        per.setValorAnterior(obj.getValorAnterior());
        per.setDescripcionValorAnterior(obj.getDescripcionValorAnterior());
        per.setValorNuevo(obj.getValorNuevo());
        per.setDescripcionValorNuevo(obj.getDescripcionValorNuevo());
        per.setFechaMarcacion(obj.getFechaMarcacion());
        per.setAsegInformesIdMarcacion(obj.getAsegInformesIdMarcacion());
        per.setMaeEstadoNovedad(obj.getMaeEstadoNovedad());// validar porqué esta este campo si tenemos una relación con la tabla maestra
        per.setObservacion(obj.getObservacion());
        per.setSincronizado(obj.getSincronizado());
        per.setOrigenUltimoRegistro(obj.getOrigenUltimoRegistro());
        per.setDescripcionOrigenUltimoRegistro(obj.getDescripcionOrigenUltimoRegistro());
        //auditoria
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        //objetos
        if (obj.getRadicadoNovedad() != null) {
            per.setRadicadoNovedadesId(new AsegRadicadoNovedades(obj.getRadicadoNovedad().getId()));
        }
        if (obj.getAsegMaNovedad() != null) {
            per.setAsegMaNovedadesId(new AsegMaNovedades(obj.getAsegMaNovedad().getId()));
        }
        if (obj.getAsegReporteNovedad() != null) {
            per.setAsegReporteNovedadesId(new AsegReporteNovedades(obj.getAsegReporteNovedad().getId()));
        }
        //COMPLETAR
        return per;
    }

    public static AsegRadicadoNovedad castRadicadoNovedadesEntidadNegocio(AsegRadicadoNovedades per) {
        AsegRadicadoNovedad obj = new AsegRadicadoNovedad();
        obj.setId(per.getId());
        obj.setSoporteNovedad(per.getSoporteNovedad());
        obj.setHistoricoAfiliado(per.getHistoricoAfiliado());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        if (per.getAsegAfiliadosId() != null) {
            obj.setAsegAfiliado(AfiliadoServicio.castEntidadNegocioLargo(per.getAsegAfiliadosId()));
        }
        return obj;
    }

    public static AsegMaNovedad castMaestroNovedadesEntidadNegocio(AsegMaNovedades per) {
        AsegMaNovedad obj = new AsegMaNovedad();
        obj.setId(per.getId());
        obj.setCodigoNovedad(per.getCodigoNovedad());
        obj.setDescripcionNovedad(per.getDescripcionNovedad());
        obj.setRegimen(per.getRegimen());
        obj.setActivo(per.getActivo());
        obj.setCodigoNovedadPadre(per.getCodigoNovedadPadre());
        obj.setReporteNormativo(per.getReporteNormativo());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalMofica());// nombre malo
        return obj;
    }

    @Override
    public AsegRegistroNovedad consultar(int idAfiliado, String estado, String codigoNovedad) throws java.lang.Exception {
        AsegRegistroNovedad novedadResult = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String strQuery = "FROM AsegRegistroNovedades p "
                    + " WHERE p.fechaMarcacion IS NULL "
                    + " AND p.idAfiliado = " + idAfiliado + " "
                    + " AND p.asegMaNovedadesId.codigoNovedad = '" + codigoNovedad + "' ";
                    /*+ " AND p.maeEstadoNovedad = '" + estado + "' " -- Se elimina porque el valor no esta siendo usado en PDN - no esta configurado el maestro y hay datos diferentes en Pruebas */
                    
            AsegRegistroNovedades obj = (AsegRegistroNovedades) getEntityManager().createQuery(strQuery).getSingleResult();
            novedadResult = castEntidadNegocio(obj);
        } catch (NoResultException e) {
            novedadResult = null;
        } catch (Exception e) {
            novedadResult = null;
        } finally {
            cerrarEntityManager();
        }
        return novedadResult;
    }
    
    @Override
    public AsegRegistroNovedad consultarPorReporteNovedad(int idAfiliado, Date fechaHoraCrea, String codigoNovedad) throws java.lang.Exception {
        AsegRegistroNovedad novedadResult = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String strQuery = "FROM AsegRegistroNovedades p "
                    + " WHERE p.fechaMarcacion IS NULL "
                    + " AND p.asegReporteNovedadesId.asegAfiliadosId.id = " + idAfiliado
                    + " AND p.asegReporteNovedadesId.codigoNovedad = '" + codigoNovedad + "' "
                    + " AND p.asegReporteNovedadesId.fechaHoraCrea = '" + sdf.format(fechaHoraCrea) + "' ";
            
                    /*+ " AND p.maeEstadoNovedad = '" + estado + "' " -- Se elimina porque el valor no esta siendo usado en PDN - no esta configurado el maestro y hay datos diferentes en Pruebas */
                    
            AsegRegistroNovedades obj = (AsegRegistroNovedades) getEntityManager().createQuery(strQuery).getSingleResult();
            novedadResult = castEntidadNegocio(obj);
        } catch (NoResultException e) {
            novedadResult = null;
        } catch (Exception e) {
            novedadResult = null;
        } finally {
            cerrarEntityManager();
        }
        return novedadResult;
    }
    
    @Override
    public void actualizarNovedadesReporteAfiliadosNuevosMS (AsegRegistroNovedad novedad, Date fechaDesde, Date fechaHasta) throws java.lang.Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfCorto = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //getEntityManager().merge(castNegocioEntidad(obj));
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AsegRegistroNovedades K SET ";
            strQuery += " K.fechaMarcacion = '" + sdf.format(novedad.getFechaMarcacion()) + "' ,";
            strQuery += " K.asegInformesIdMarcacion = " + novedad.getAsegInformesIdMarcacion() + " ,";
            strQuery += " K.usuarioModifica = '" + novedad.getUsuarioModifica()+ "' ,";
            strQuery += " K.fechaHoraModifica = '"+ sdf.format(novedad.getFechaHoraModifica()) + "' ,";
            strQuery += " K.terminalModifica = '" + novedad.getTerminalModifica() + "' ";
            strQuery += " WHERE K.fechaMarcacion IS NULL ";
            strQuery += " AND K.fechaHoraCrea >= '" + sdfCorto.format(fechaDesde) + "' ";
            strQuery += " AND K.fechaHoraCrea < '" + sdfCorto.format(fechaHasta) + "' ";
            strQuery += " AND K.asegMaNovedadesId.id = 233 ";
            strQuery += " AND K.idAfiliado IN (SELECT A.id FROM  AsegAfiliados A JOIN GnUbicaciones U ON A.afiliacionUbicacionesId.id = U.id " +
                        " WHERE A.fechaHoraCrea >= '" + sdfCorto.format(fechaDesde) +
                        "' AND A.fechaHoraCrea < '"+ sdfCorto.format(fechaHasta) + "' " +
                        " AND A.maeEstadoAfiliacionId = 134 " +
                        " AND U.cobertura = TRUE) ";
            
            org.hibernate.Query query = session.createQuery(strQuery);
            
            //query.setProperties(regn);
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
    public void actualizarNovedadesReporteNovedadesNS (AsegRegistroNovedad novedad, Date fechaDesde, Date fechaHasta) throws java.lang.Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfCorto = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //getEntityManager().merge(castNegocioEntidad(obj));
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AsegRegistroNovedades K SET ";
            strQuery += " K.fechaMarcacion = '" + sdf.format(novedad.getFechaMarcacion()) + "' ,";
            strQuery += " K.asegInformesIdMarcacion = " + novedad.getAsegInformesIdMarcacion() + " ,";
            strQuery += " K.usuarioModifica = '" + novedad.getUsuarioModifica()+ "' ,";
            strQuery += " K.fechaHoraModifica = '"+ sdf.format(novedad.getFechaHoraModifica()) + "' ,";
            strQuery += " K.terminalModifica = '" + novedad.getTerminalModifica() + "' ";
            strQuery += " WHERE K.fechaMarcacion IS NULL ";
            strQuery += " AND K.fechaHoraCrea >= '" + sdfCorto.format(fechaDesde) + "' ";
            strQuery += " AND K.fechaHoraCrea < '" + sdfCorto.format(fechaHasta) + "' ";
            strQuery += " AND K.asegReporteNovedadesId.id IN (SELECT R.id FROM AsegReporteNovedades R JOIN " +
                        " AsegAfiliados A ON R.asegAfiliadosId.id = A.id " +
                        " WHERE R.fechaHoraCrea >= '"+ sdfCorto.format(fechaDesde) + "' " +
                        " AND R.fechaHoraCrea < '" + sdfCorto.format(fechaHasta) + "' " +
                        " AND R.codigoEps = 'EPSS40' " +
                        " AND A.maeCausaNovedadId NOT IN (244,264,288,253)) ";
            
            org.hibernate.Query query = session.createQuery(strQuery);
            
            //query.setProperties(regn);
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
    public void actualizarNovedadesReporteTrasladosS1 (AsegRegistroNovedad novedad, Date fechaDesde, Date fechaHasta) throws java.lang.Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfCorto = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //getEntityManager().merge(castNegocioEntidad(obj));
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AsegRegistroNovedades K SET ";
            strQuery += " K.fechaMarcacion = '" + sdf.format(novedad.getFechaMarcacion()) + "' ,";
            strQuery += " K.asegInformesIdMarcacion = " + novedad.getAsegInformesIdMarcacion() + " ,";
            strQuery += " K.usuarioModifica = '" + novedad.getUsuarioModifica()+ "' ,";
            strQuery += " K.fechaHoraModifica = '"+ sdf.format(novedad.getFechaHoraModifica()) + "' ,";
            strQuery += " K.terminalModifica = '" + novedad.getTerminalModifica() + "' ";
            strQuery += " WHERE K.fechaMarcacion IS NULL ";
            strQuery += " AND K.fechaHoraCrea >= '" + sdfCorto.format(fechaDesde) + "' ";
            strQuery += " AND K.fechaHoraCrea < '" + sdfCorto.format(fechaHasta) + "' ";
            strQuery += " AND K.asegMaNovedadesId.id IN (145,298) ";
            strQuery += " AND K.idAfiliado IN (SELECT A.id FROM AsegTraslados T " +
                        " JOIN AsegAfiliados A ON T.asegAfiliadosId.id = A.id " +
                        " WHERE T.fechaHoraCrea >= '"+ sdfCorto.format(fechaDesde) +"' " +
                        " AND T.fechaHoraCrea < '" + sdfCorto.format(fechaHasta) + "' " +
                        " AND A.maeEstadoAfiliacionId IN (134,135))";
            
            org.hibernate.Query query = session.createQuery(strQuery);
            
            //query.setProperties(regn);
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
    public void actualizarNovedadesPorIdReporte (int idReporte) throws java.lang.Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfCorto = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //getEntityManager().merge(castNegocioEntidad(obj));
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AsegRegistroNovedades a SET ";
            strQuery += " a.fechaMarcacion = NULL , ";
            strQuery += " a.asegInformesIdMarcacion = NULL  ";
            strQuery += " WHERE a.asegInformesIdMarcacion = " + idReporte; 
            org.hibernate.Query query = session.createQuery(strQuery);
            
            //query.setProperties(regn);
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
    public AsegRegistroNovedad consultarUltimoRegistroUsuarioInactivoPorAfiliado(int idAfiliado) throws java.lang.Exception {
        AsegRegistroNovedad novedadResult = null;
        try {
            String strQuery = "FROM AsegRegistroNovedades p "
                    + "WHERE p.radicadoNovedadesId.asegAfiliadosId.id =  " + idAfiliado + " "
                    + "AND p.asegMaNovedadesId.codigoNovedad IN ('N14-233','N09-194','IS-06','IS-71','IS-73') "
                    + " ORDER BY p.id DESC ";
            
            AsegRegistroNovedades obj = (AsegRegistroNovedades) getEntityManager().createQuery(strQuery).setMaxResults(1).getSingleResult();
            novedadResult = castEntidadNegocio(obj);
        } catch (NoResultException e) {
            novedadResult = null;
        } catch (Exception e) {
            novedadResult = null;
        } finally {
            cerrarEntityManager();
        }
        return novedadResult;
    }

}
