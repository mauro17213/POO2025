/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.tutela;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuJuzgado;
import com.saviasaludeps.savia.dominio.tutela.TuTutela;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaEstado;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaRespuesta;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.entidades.TuAdjuntos;
import com.saviasaludeps.savia.ejb.entidades.TuPersonas;
import com.saviasaludeps.savia.ejb.entidades.TuTutelaDiagnosticos;
import com.saviasaludeps.savia.ejb.entidades.TuTutelaEstados;
import com.saviasaludeps.savia.ejb.entidades.TuTutelaItems;
import com.saviasaludeps.savia.ejb.entidades.TuTutelas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.utilidades.Util;
import com.saviasaludeps.savia.negocio.tutela.TuTutelaRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jramirer
 */
@Stateless
@Remote(TuTutelaRemoto.class)
public class TuTutelaServicio extends GenericoServicio implements TuTutelaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(t.id) FROM TuTutelas t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND t.id LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "radicadoNumero":
                            strQuery += "AND t.radicadoNumero = '" + (String) e.getValue() + "' ";
                            break;
                        case "gnUbicacionId.id":
                            strQuery += "AND t.gnUbicacionId = '" + (String) e.getValue() + "' ";
                            break;
                        case "actualTuTutelaEstadoId.maeEstadoId":
                            String cadenaMaestado = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND t.actualTuTutelaEstadosId.maeEstadoId IN ( " + cadenaMaestado + ") ";
                            break;
                        case "actualTuTutelaEstadoId.maeProcesoId":
                            String cadenaProceso = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND t.actualTuTutelaEstadosId.maeProcesoId IN ( " + cadenaProceso + ") ";
                            break;
                        case "tuPersona.maeTipoDocumentoId":
                            strQuery += "AND t.tuPersonasId.maeTipoDocumentoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "tuPersona.numeroDocumento":
                            strQuery += "AND t.tuPersonasId.numeroDocumento = '" + (String) e.getValue() + "' ";
                            break;
                        case "tuPersona.nombres":
                            strQuery += "AND t.tuPersonasId.nombres LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "tuPersona.apellidos":
                            strQuery += "AND t.tuPersonasId.apellidos LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "actualTuTutelaEstadoId.responsableGnUsuarioId.nombre":
                            strQuery += "AND REPLACE(REPLACE(t.actualTuTutelaEstadosId.responsableGnUsuarioId.nombre, ' ' , ''), ' ', '') LIKE REPLACE(REPLACE('%"+ (String) e.getValue() +"%', ' ' , ''), ' ', '') ";
                            break;
                        case "actualTuTutelaEstadoId.tuJuzgadoId.nombre":
                            strQuery += "AND t.actualTuTutelaEstadosId.tuJuzgadosId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "borradoTutela":
                            strQuery += "AND t.borrado != '" + (String) e.getValue() + "' ";
                            break;
                    }
                }
            }
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND t.actualTuTutelaEstadosId.responsableGnUsuarioId.id = " + paramConsulta.getParametroConsulta1();
            }
            //strQuery += "AND t.borrado != 1 ";
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //                    .setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public List<TuTutela> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<TuTutela> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t FROM TuTutelas t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND t.id LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "radicadoNumero":
                            strQuery += "AND t.radicadoNumero = '" + (String) e.getValue() + "' ";
                            break;
                        case "gnUbicacionId.id":
                            strQuery += "AND t.gnUbicacionId.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "actualTuTutelaEstadoId.maeEstadoId":
                            String cadenaMaestado = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND t.actualTuTutelaEstadosId.maeEstadoId IN ( " + cadenaMaestado + ") ";
                            break;
                        case "actualTuTutelaEstadoId.maeProcesoId":
                            String cadenaProceso = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND t.actualTuTutelaEstadosId.maeProcesoId IN ( " + cadenaProceso + ") ";
                            break;
                        case "tuPersona.maeTipoDocumentoId":
                            strQuery += "AND t.tuPersonasId.maeTipoDocumentoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "tuPersona.numeroDocumento":
                            strQuery += "AND t.tuPersonasId.numeroDocumento = '" + (String) e.getValue() + "' ";
                            break;
                        case "tuPersona.nombres":
                            strQuery += "AND t.tuPersonasId.nombres LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "tuPersona.apellidos":
                            strQuery += "AND t.tuPersonasId.apellidos LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "actualTuTutelaEstadoId.responsableGnUsuarioId.nombre":
                            strQuery += "AND REPLACE(REPLACE(t.actualTuTutelaEstadosId.responsableGnUsuarioId.nombre, ' ' , ''), ' ', '') LIKE REPLACE(REPLACE('%"+ (String) e.getValue() +"%', ' ' , ''), ' ', '') ";
                            break;
                        case "actualTuTutelaEstadoId.tuJuzgadoId.nombre":
                            strQuery += "AND t.actualTuTutelaEstadosId.tuJuzgadosId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "borradoTutela":
                            strQuery += "AND t.borrado != '" + (String) e.getValue() + "' ";
                    }
                }
            }
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND t.actualTuTutelaEstadosId.responsableGnUsuarioId.id = " + paramConsulta.getParametroConsulta1();
            }

            //strQuery += "AND t.borrado != 1 ";
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                String order = paramConsulta.getOrden().
                        replace("actualTuTutelaEstadoId.tuJuzgadoId.nombre", "actualTuTutelaEstadosId.tuJuzgadosId.nombre").
                        replace("actualTuTutelaEstadoId.responsableGnUsuarioId.nombre", "actualTuTutelaEstadosId.responsableGnUsuarioId.nombre").
                        replace("actualTuTutelaEstadoId.gestorGnUsuarioId.nombre", "actualTuTutelaEstadosId.gestorGnUsuarioId.nombre").
                        replace("actualTuTutelaEstadoId.abogadoGnUsuarioId.nombre", "actualTuTutelaEstadosId.abogadoGnUsuarioId.nombre").
                        replace("actualTuTutelaEstadoId.medicoGnUsuarioId.nombre", "actualTuTutelaEstadosId.medicoGnUsuarioId.nombre").
                        replace("tuPersona.maeTipoDocumentoValor", "tuPersonasId.maeTipoDocumentoValor").
                        replace("tuPersona.numeroDocumento", "tuPersonasId.numeroDocumento").
                        replace("tuPersona.nombres", "tuPersonasId.nombres").
                        replace("tuPersona.apellidos", "tuPersonasId.apellidos");
                strQuery += "t." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "t.actualTuTutelaEstadosId.fechaHoraCrea DESC";
            }
            List<TuTutelas> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //                    .setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (TuTutelas per : list) {
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
    public TuTutela consultar(int id) throws Exception {
        TuTutela objRes = null;
        try {
            objRes = castEntidadNegocio((TuTutelas) getEntityManager().find(TuTutelas.class, id));
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
    public int insertar(TuTutela obj) throws Exception {
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
    public void actualizar(TuTutela obj) throws Exception {
        try {
            String hql = "UPDATE TuTutelas SET"
                    + ((obj.getActualTuTutelaEstadoId() != null && obj.getActualTuTutelaEstadoId().getId() != null) ? " actualTuTutelaEstadosId.id = :actualTuTutelaEstadosId," : "")
                    + ((obj.getTuPersona() != null && obj.getTuPersona().getId() != null) ? " tuPersonasId.id = :tuPersonasId," : "")
                    + ((obj.getRadicadoNumero() != null) ? " radicadoNumero = :radicadoNumero," : "")
                    + ((obj.getGnUbicacionId() != null && obj.getGnUbicacionId().getId() != null) ? " gnUbicacionId.id = :gnUbicacionId," : "")
                    + ((obj.getBorrado() != null) ? " borrado = :borrado," : "")
                    + ((obj.getUsuarioBorra() != null) ? " usuarioBorra = :usuarioBorra," : "")
                    + ((obj.getTerminalBorra() != null) ? " terminalBorra = :terminalBorra," : "")
                    + ((obj.getFechaHoraBorra() != null) ? " fechaHoraBorra = :fechaHoraBorra," : "")
                    + " cantidadItems = :cantidadItems,"
                    + " cantidadItemsCerrados = :cantidadItemsCerrados,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            if (obj.getActualTuTutelaEstadoId() != null && obj.getActualTuTutelaEstadoId().getId() != null) {
                query.setParameter("actualTuTutelaEstadosId", obj.getActualTuTutelaEstadoId().getId());
            }

            if (obj.getTuPersona() != null && obj.getTuPersona().getId() != null) {
                query.setParameter("tuPersonasId", obj.getTuPersona().getId());
            }

            if (obj.getRadicadoNumero() != null) {
                query.setParameter("radicadoNumero", obj.getRadicadoNumero());
            }

            if (obj.getGnUbicacionId() != null && obj.getGnUbicacionId().getId() != null) {
                query.setParameter("gnUbicacionId", obj.getGnUbicacionId().getId());
            }

            if (obj.getBorrado() != null) {
                query.setParameter("borrado", obj.getBorrado());
            }

            if (obj.getUsuarioBorra() != null) {
                query.setParameter("usuarioBorra", obj.getUsuarioBorra());
            }

            if (obj.getTerminalBorra() != null) {
                query.setParameter("terminalBorra", obj.getTerminalBorra());
            }

            if (obj.getFechaHoraBorra() != null) {
                query.setParameter("fechaHoraBorra", obj.getFechaHoraBorra());
            }

            query.setParameter("cantidadItems", obj.getCantidadItems());
            query.setParameter("cantidadItemsCerrados", obj.getCantidadItemsCerrados());
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
    public TuTutela eliminar(int id) throws Exception {
        TuTutela obj = null;
        try {
            TuTutelas ent = getEntityManager().find(TuTutelas.class, id);
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
    public List<TuTutelaRespuesta> consultarConExoneracionAfiliado(String tipoDocumento, String numeroDocumento, Integer idEstadoFallo) throws Exception {
        List<TuTutelaRespuesta> tutelas = new ArrayList();
        try {
            String strQuery = "SELECT t FROM TuTutelas t "
                    + "WHERE t.id > 0 ";
            if (tipoDocumento != null && numeroDocumento != null) {
                strQuery += "  AND t.tuPersonasId.maeTipoDocumentoCodigo = '" + tipoDocumento+
                            "' AND t.tuPersonasId.numeroDocumento= '"+numeroDocumento+"'";
            }
            strQuery += " ORDER BY t.id DESC ";
            List<TuTutelas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (TuTutelas per : list) {
                tutelas.add(castEntidadNegocioRespuesta(per, idEstadoFallo));
            }
        } catch (NoResultException e) {
            tutelas = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return tutelas;
    }
    
    @Override
    public List<TuTutelaRespuesta> consultarConExoneracionAfiliadoPorId(Integer id, Integer idEstadoFallo) throws Exception {
        List<TuTutelaRespuesta> tutelas = new ArrayList();
        try {
            String strQuery = "SELECT t FROM TuTutelas t "
                    + "WHERE t.id > 0 ";
            if (id != null) {
                strQuery += " AND t.tuPersonasId.asegAfiliadoId = " + id;
            }
            strQuery += " ORDER BY t.id DESC ";
            List<TuTutelas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (TuTutelas per : list) {
                tutelas.add(castEntidadNegocioRespuesta(per, idEstadoFallo));
            }
        } catch (NoResultException e) {
            tutelas = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return tutelas;
    }
    
    @Override
    public int consultarCantidadListaPorAfiliado(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(t.id) FROM TuTutelas t "
                    + "WHERE t.id > 0 ";
            
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND t.tuPersonasId.asegAfiliadoId = " + paramConsulta.getParametroConsulta1();
            }
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND t.id LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "radicadoNumero":
                            strQuery += "AND t.radicadoNumero = '" + (String) e.getValue() + "' ";
                            break;
                        case "gnUbicacionId.id":
                            strQuery += "AND t.gnUbicacionId = '" + (String) e.getValue() + "' ";
                            break;
                        case "actualTuTutelaEstadoId.maeEstadoId":
                            String cadenaMaestado = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND t.actualTuTutelaEstadosId.maeEstadoId IN ( " + cadenaMaestado + ") ";
                            break;
                        case "actualTuTutelaEstadoId.maeProcesoId":
                            String cadenaProceso = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND t.actualTuTutelaEstadosId.maeProcesoId IN ( " + cadenaProceso + ") ";
                            break;
                        case "tuPersona.maeTipoDocumentoId":
                            strQuery += "AND t.tuPersonasId.maeTipoDocumentoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "tuPersona.numeroDocumento":
                            strQuery += "AND t.tuPersonasId.numeroDocumento = '" + (String) e.getValue() + "' ";
                            break;
                        case "tuPersona.nombres":
                            strQuery += "AND t.tuPersonasId.nombres LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "tuPersona.apellidos":
                            strQuery += "AND t.tuPersonasId.apellidos LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "actualTuTutelaEstadoId.responsableGnUsuarioId.nombre":
                            strQuery += "AND t.actualTuTutelaEstadosId.responsableGnUsuarioId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "actualTuTutelaEstadoId.tuJuzgadoId.nombre":
                            strQuery += "AND t.actualTuTutelaEstadosId.tuJuzgadosId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND t.actualTuTutelaEstadosId.responsableGnUsuarioId.id = " + paramConsulta.getParametroConsulta2();
            }
            strQuery += "AND t.borrado != 1 ";
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //                    .setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public List<TuTutela> consultarListaPorAfiliado(ParamConsulta paramConsulta) throws Exception {
        List<TuTutela> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t FROM TuTutelas t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND t.tuPersonasId.asegAfiliadoId = " + paramConsulta.getParametroConsulta1();
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND t.id LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "radicadoNumero":
                            strQuery += "AND t.radicadoNumero = '" + (String) e.getValue() + "' ";
                            break;
                        case "gnUbicacionId.id":
                            strQuery += "AND t.gnUbicacionId.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "actualTuTutelaEstadoId.maeEstadoId":
                            String cadenaMaestado = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND t.actualTuTutelaEstadosId.maeEstadoId IN ( " + cadenaMaestado + ") ";
                            break;
                        case "actualTuTutelaEstadoId.maeProcesoId":
                            String cadenaProceso = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND t.actualTuTutelaEstadosId.maeProcesoId IN ( " + cadenaProceso + ") ";
                            break;
                        case "tuPersona.maeTipoDocumentoId":
                            strQuery += "AND t.tuPersonasId.maeTipoDocumentoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "tuPersona.numeroDocumento":
                            strQuery += "AND t.tuPersonasId.numeroDocumento = '" + (String) e.getValue() + "' ";
                            break;
                        case "tuPersona.nombres":
                            strQuery += "AND t.tuPersonasId.nombres LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "tuPersona.apellidos":
                            strQuery += "AND t.tuPersonasId.apellidos LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "actualTuTutelaEstadoId.responsableGnUsuarioId.nombre":
                            strQuery += "AND t.actualTuTutelaEstadosId.responsableGnUsuarioId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "actualTuTutelaEstadoId.tuJuzgadoId.nombre":
                            strQuery += "AND t.actualTuTutelaEstadosId.tuJuzgadosId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND t.actualTuTutelaEstadosId.responsableGnUsuarioId.id = " + paramConsulta.getParametroConsulta2();
            }

            strQuery += "AND t.borrado != 1 ";
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                String order = paramConsulta.getOrden().
                        replace("actualTuTutelaEstadoId.tuJuzgadoId.nombre", "actualTuTutelaEstadosId.tuJuzgadosId.nombre").
                        replace("actualTuTutelaEstadoId.responsableGnUsuarioId.nombre", "actualTuTutelaEstadosId.responsableGnUsuarioId.nombre").
                        replace("actualTuTutelaEstadoId.gestorGnUsuarioId.nombre", "actualTuTutelaEstadosId.gestorGnUsuarioId.nombre").
                        replace("actualTuTutelaEstadoId.abogadoGnUsuarioId.nombre", "actualTuTutelaEstadosId.abogadoGnUsuarioId.nombre").
                        replace("actualTuTutelaEstadoId.medicoGnUsuarioId.nombre", "actualTuTutelaEstadosId.medicoGnUsuarioId.nombre").
                        replace("tuPersona.maeTipoDocumentoValor", "tuPersonasId.maeTipoDocumentoValor").
                        replace("tuPersona.numeroDocumento", "tuPersonasId.numeroDocumento").
                        replace("tuPersona.nombres", "tuPersonasId.nombres").
                        replace("tuPersona.apellidos", "tuPersonasId.apellidos");
                strQuery += "t." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "t.actualTuTutelaEstadosId.fechaHoraCrea DESC";
            }
            List<TuTutelas> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //                    .setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (TuTutelas per : list) {
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
    public int consultarCantidadTutelasParaSolicitutesAnexoTres(Integer idAfiliado) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(t.id) FROM TuTutelas t "
                    + "WHERE t.tuPersonasId.asegAfiliadoId = " + idAfiliado + " "
                    + "AND t.borrado != 1 ";
            
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
    
    public static TuTutela castEntidadNegocio(TuTutelas per) {
        TuTutela obj = new TuTutela();
        obj.setId(per.getId());
        obj.setRadicadoNumero(per.getRadicadoNumero());
        if (per.getActualTuTutelaEstadosId() != null) {
            TuTutelaEstado tuTutelaEstado = new TuTutelaEstado();
            tuTutelaEstado.setId(per.getActualTuTutelaEstadosId().getId());
            tuTutelaEstado.setMaeEstadoId(per.getActualTuTutelaEstadosId().getMaeEstadoId());
            tuTutelaEstado.setMaeEstadoCodigo(per.getActualTuTutelaEstadosId().getMaeEstadoCodigo());
            tuTutelaEstado.setMaeEstadoValor(per.getActualTuTutelaEstadosId().getMaeEstadoValor());
            tuTutelaEstado.setMaeProcesoId(per.getActualTuTutelaEstadosId().getMaeProcesoId());
            tuTutelaEstado.setMaeProcesoCodigo(per.getActualTuTutelaEstadosId().getMaeProcesoCodigo());
            tuTutelaEstado.setMaeProcesoValor(per.getActualTuTutelaEstadosId().getMaeProcesoValor());
            tuTutelaEstado.setFechaVencimiento(per.getActualTuTutelaEstadosId().getFechaVencimiento());
            if(per.getActualTuTutelaEstadosId().getTuJuzgadosId() != null){
                TuJuzgado tuJuzgado = new TuJuzgado();
                tuJuzgado.setId(per.getActualTuTutelaEstadosId().getTuJuzgadosId().getId());
                tuJuzgado.setNombre(per.getActualTuTutelaEstadosId().getTuJuzgadosId().getNombre());
                tuJuzgado.setCodigo_despacho(per.getActualTuTutelaEstadosId().getTuJuzgadosId().getCodigoDespacho());
                tuTutelaEstado.setTuJuzgadoId(tuJuzgado);
            }
            if (per.getActualTuTutelaEstadosId().getResponsableGnUsuarioId() != null) {
                tuTutelaEstado.setResponsableGnUsuarioId(new Usuario(
                        per.getActualTuTutelaEstadosId().getResponsableGnUsuarioId().getId(),
                        per.getActualTuTutelaEstadosId().getResponsableGnUsuarioId().getUsuario(),
                        per.getActualTuTutelaEstadosId().getResponsableGnUsuarioId().getNombre()));
            }
            if (per.getActualTuTutelaEstadosId().getGestorGnUsuarioId() != null) {
                tuTutelaEstado.setGestorGnUsuarioId(new Usuario(
                        per.getActualTuTutelaEstadosId().getGestorGnUsuarioId().getId(),
                        per.getActualTuTutelaEstadosId().getGestorGnUsuarioId().getUsuario(),
                        per.getActualTuTutelaEstadosId().getGestorGnUsuarioId().getNombre()));
            }
            if (per.getActualTuTutelaEstadosId().getAbogadoGnUsuarioId() != null) {
                tuTutelaEstado.setAbogadoGnUsuarioId(new Usuario(
                        per.getActualTuTutelaEstadosId().getAbogadoGnUsuarioId().getId(),
                        per.getActualTuTutelaEstadosId().getAbogadoGnUsuarioId().getUsuario(),
                        per.getActualTuTutelaEstadosId().getAbogadoGnUsuarioId().getNombre()));
            }
            if (per.getActualTuTutelaEstadosId().getMedicoGnUsuarioId() != null) {
                tuTutelaEstado.setMedicoGnUsuarioId(new Usuario(
                        per.getActualTuTutelaEstadosId().getMedicoGnUsuarioId().getId(),
                        per.getActualTuTutelaEstadosId().getMedicoGnUsuarioId().getUsuario(),
                        per.getActualTuTutelaEstadosId().getMedicoGnUsuarioId().getNombre()));
            }
            //2025-07-17 jyperez nuevos campos PENDIENTE
            //tuTutelaEstado.setCantidadItems(per.getActualTuTutelaEstadosId().getCantidadItems());
            //tuTutelaEstado.setCantidadItemsCerrados(per.getActualTuTutelaEstadosId().getCantidadItemsCerrados());
            obj.setActualTuTutelaEstadoId(tuTutelaEstado);
            //obj.setMaeSolicitudEstadoCodigo(per.getActualTuTutelaEstadosId());
            //obj.setMaeSolicitudEstadoValor(per.getActualTuTutelaEstadosId());
        }
        //2025-07-17 jyperez nuevos campos
        obj.setCantidadItems(per.getCantidadItems());
        obj.setCantidadItemsCerrados(per.getCantidadItemsCerrados());
        if (obj.getGnUbicacionId() != null) {
            obj.setGnUbicacionId(new Ubicacion(per.getGnUbicacionId().getId()));
        }
        if (per.getTuPersonasId() != null) {
            obj.setTuPersona(TuPersonaServicio.castEntidadNegocio(per.getTuPersonasId()));
        }

        /*List<TuTutelaEstado> listaEstado = new ArrayList<>();

        if (per.getTuTutelaEstadosList() != null) {
            for (TuTutelaEstados tuTutelaEstado : per.getTuTutelaEstadosList()) {
                listaEstado.add(TuEstadoTutelaServicio.castEntidadNegocio(tuTutelaEstado));
            }
        }
        obj.setListaTuTutelaEstado(listaEstado);*/
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());

        return obj;
    }

    public static TuTutelas castNegocioEntidad(TuTutela obj) {
        TuTutelas per = new TuTutelas();
        per.setId(obj.getId());
        if (obj.getTuPersona() != null) {
            per.setTuPersonasId(new TuPersonas(obj.getTuPersona().getId()));
        }
        per.setRadicadoNumero(obj.getRadicadoNumero());
        if (obj.getActualTuTutelaEstadoId() != null) {
            per.setActualTuTutelaEstadosId(new TuTutelaEstados(obj.getActualTuTutelaEstadoId().getId()));
        }
        if (obj.getGnUbicacionId() != null) {
            per.setGnUbicacionId(new GnUbicaciones(obj.getGnUbicacionId().getId()));
        }
        //2025-07-17 jyperez nuevos campos
        per.setCantidadItems(obj.getCantidadItems());
        per.setCantidadItemsCerrados(obj.getCantidadItemsCerrados());
        //Auditoría
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }
    
    public static TuTutelaRespuesta castEntidadNegocioRespuesta(TuTutelas per, Integer idEstadoFallo) {
        TuTutelaRespuesta neg = new TuTutelaRespuesta();
        neg.setNumeroTutela(String.valueOf(per.getId()));
        neg.setNumeroRadicadoJuzgado(per.getRadicadoNumero());
        //TODO: neg.setNumeroRadicadoJuzgado(per.getRadicadoNumero());
        //TODO: neg.setTipoFase(REPORTE);
        //TODO: neg.setNumeroFallo(INSERTAR);
        //TODO: neg.setFechaFallo(CONSULTAR);
        //TODO: neg.setTipoFase(REPORTE);
        
        if (per.getActualTuTutelaEstadosId() != null) {
            neg.setFeachaVencimiento(per.getActualTuTutelaEstadosId().getFechaVencimiento().toString());
            neg.setFeachaNotificacion(per.getActualTuTutelaEstadosId().getFechaNotificacion().toString());
            neg.setEstado(per.getActualTuTutelaEstadosId().getMaeEstadoValor());
            neg.setEstadoProceso(per.getActualTuTutelaEstadosId().getMaeProcesoValor());
            boolean exoneracion = Optional.ofNullable(per.getActualTuTutelaEstadosId().getExoneracion()).orElse(false);
            neg.setExoneracionCopago(exoneracion ? "Si" : "No");
            boolean integralidad = Optional.ofNullable(per.getActualTuTutelaEstadosId().getIntegralidad()).orElse(false);
            neg.setIntegralidad(integralidad ? "Si" : "No");
            boolean medidaProvisional = Optional.ofNullable(per.getActualTuTutelaEstadosId().getMedidaProvisional()).orElse(false);
            neg.setMedidaProvisional(medidaProvisional ? "Si" : "No");
        }

        for (TuTutelaItems tuTutelaItems : per.getTuTutelaItemsList()) {
            neg.getServicios().add(TuTutelaItemServicio.castEntidadNegocio(tuTutelaItems));
        }
        for (TuTutelaDiagnosticos tuTutelaDiagnosticos : per.getTuTutelaDiagnosticosList()) {
            neg.getDiagnosticos().add(TuDiagnosticoServicio.castEntidadNegocio(tuTutelaDiagnosticos));
        }
        boolean encontroUnEstadoFallo = false;
        for (TuTutelaEstados tuTutelaEstado : per.getTuTutelaEstadosList()) {
            if(!tuTutelaEstado.getBorrado()){
                boolean exoneracion = Optional.ofNullable(tuTutelaEstado.getExoneracion()).orElse(false);
                if(idEstadoFallo == tuTutelaEstado.getMaeEstadoId() && !encontroUnEstadoFallo && exoneracion){
                    neg.setNumeroFallo(String.valueOf(per.getId()));
                    neg.setExoneracionCopago(exoneracion ? "Si" : "No");
                    neg.setFechaFallo(tuTutelaEstado.getFechaHoraCrea().toString());
                    encontroUnEstadoFallo = true;
                    neg.setExoneracionTutela(true);
                }
                for (TuAdjuntos tuAdjuntos : tuTutelaEstado.getTuAdjuntosList()) {
                    neg.getAdjuntos().add(TuAdjuntoServicio.castEntidadNegocio(tuAdjuntos));
                }
            }
            
        }

        return neg;
    }

    @Override
    public void actualizarEstado(TuTutela obj) throws Exception {
        try {
            String hql = "UPDATE TuTutelas SET"
                    + ((obj.getActualTuTutelaEstadoId() != null && obj.getActualTuTutelaEstadoId().getId() != null) ? " actualTuTutelaEstadosId.id = :actualTuTutelaEstadosId," : "")
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            if (obj.getActualTuTutelaEstadoId() != null && obj.getActualTuTutelaEstadoId().getId() != null) {
                query.setParameter("actualTuTutelaEstadosId", obj.getActualTuTutelaEstadoId().getId());
            }
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

    /**
     * Consultar lista de tutelas que tengan integralidad
     * @param idAfiliado
     * @return List<TuTutela>
     * @throws Exception 
     */
    @Override
    public List<TuTutela> consultarListaPorAfiliadoIntegralidad(int idAfiliado) throws java.lang.Exception {
         List<TuTutela> listResult = new ArrayList();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT t FROM TuTutelas t ");
            strQuery.append(" INNER JOIN TuTutelaEstados te ON t.id = te.tuTutelasId AND te.integralidad = 1 ");
            strQuery.append(" INNER JOIN TuPersonas tp ON t.tuPersonasId = tp.id ");
            strQuery.append(" AND tp.asegAfiliadoId = ").append(idAfiliado);
            
             List<TuTutelas> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (TuTutelas per : list) {
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
}
