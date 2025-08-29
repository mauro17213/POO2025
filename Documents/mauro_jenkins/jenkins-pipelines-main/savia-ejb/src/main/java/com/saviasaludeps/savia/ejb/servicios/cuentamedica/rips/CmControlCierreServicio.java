/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmControlCierre;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmRipsInhabilitados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.ControlCierreRemoto;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Jhoan-Ramirez
 */
@Stateless
@Remote(ControlCierreRemoto.class)
public class CmControlCierreServicio extends GenericoServicio implements ControlCierreRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CmRipsInhabilitados c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "maeContratoModalidadId":
                            strQuery += "AND c.maeContratoModalidadId = " +  e.getValue() + " ";
                            break;
                         case "coberturaCierre":
                            strQuery += "AND c.coberturaCierre = " +  e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
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
    public List<CmControlCierre> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmControlCierre> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmRipsInhabilitados c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "maeContratoModalidadId":
                            strQuery += "AND c.maeContratoModalidadId = " +  e.getValue() + " ";
                            break;
                        case "coberturaCierre":
                            strQuery += "AND c.coberturaCierre = " +  e.getValue() + " ";
                            break;

                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "c." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "c.id DESC ";
            }
            List<CmRipsInhabilitados> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CmRipsInhabilitados per : list) {
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
    public List<CmControlCierre> consultarPresenciaFechaEnIntervalo(ParamConsulta paramConsulta) throws Exception {
        List<CmControlCierre> listResult = new ArrayList();
        try {
             String strQuery = "SELECT i FROM CmRipsInhabilitados i "
                    + " WHERE i.id > 0 ";
            strQuery += " AND ( "
                    + "('" + new java.sql.Timestamp(((Date) paramConsulta.getParametroConsulta1()).getTime()) + "' BETWEEN  i.fechaHoraDesde AND i.fechaHoraHasta ) "
                    + " OR "
                    + " ('" + new java.sql.Timestamp(((Date) paramConsulta.getParametroConsulta4()).getTime()) + "' BETWEEN  i.fechaHoraDesde  AND i.fechaHoraHasta)"
                    + " OR "
                    + " ('" + new java.sql.Timestamp(((Date) paramConsulta.getParametroConsulta1()).getTime()) + "' <=  i.fechaHoraHasta  AND '" + new java.sql.Timestamp(((Date) paramConsulta.getParametroConsulta4()).getTime()) + "' >= i.fechaHoraDesde) "
                    + " )";
            strQuery += " AND i.maeContratoModalidadId = " + (int) paramConsulta.getParametroConsulta2();
            strQuery += " AND i.coberturaCierre = " + (int) paramConsulta.getParametroConsulta3();
            if (paramConsulta.getParametroConsulta5() != null) {
                strQuery += " AND i.id != " + (int) paramConsulta.getParametroConsulta5();
            }
            strQuery += " ORDER BY i.id DESC";
            
            List<CmRipsInhabilitados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();

            for (CmRipsInhabilitados per : list) {
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
    public int insertar(CmControlCierre obj) throws Exception {
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
    public void actualizar(CmControlCierre obj) throws Exception {
        try {
            String hql = "UPDATE CmRipsInhabilitados SET "
                    + "motivo = :motivo, "
                    + "fechaHoraDesde = :fechaHoraDesde, "
                    + "fechaHoraHasta = :fechaHoraHasta, "
                    + "maeContratoModalidadId = :maeContratoModalidadId, "
                    + "maeContratoModalidadCodigo = :maeContratoModalidadCodigo, "
                    + "maeContratoModalidadValor = :maeContratoModalidadValor, "
                    + "coberturaCierre = :coberturaCierre, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("motivo", obj.getMotivo());
            query.setParameter("fechaHoraDesde", obj.getFechaHoraDesde());
            query.setParameter("fechaHoraHasta", obj.getFechaHoraHasta());
            query.setParameter("maeContratoModalidadId", obj.getMaeContratoModalidadId());
            query.setParameter("maeContratoModalidadCodigo", obj.getMaeContratoModalidadCodigo());
            query.setParameter("maeContratoModalidadValor", obj.getMaeContratoModalidadValor());
            query.setParameter("coberturaCierre", obj.getCoberturaCierre());
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
    public CmControlCierre eliminar(int id) throws Exception {
        CmControlCierre obj = null;
        try {
            CmControlCierre ent = getEntityManager().find(CmControlCierre.class, id);
            if (ent != null) {
//                obj = castEntidadNegocio(ent);
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
    public CmControlCierre consultar(int id) throws Exception {
        CmControlCierre objRes = null;
        try {
            objRes = castEntidadNegocio((CmRipsInhabilitados) getEntityManager().find(CmRipsInhabilitados.class, id));
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
    public HashMap<Integer, String> consultarHash() {
        HashMap<Integer, String> hashResult = new HashMap();
        String strQuery = "FROM CmRipsInhabilitados ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<CmRipsInhabilitados> list = query.getResultList();
            for (CmRipsInhabilitados per : list) {
                hashResult.put(per.getId(), per.getMotivo());
            }
        } catch (NoResultException e) {
            hashResult = new HashMap();
        } catch (Exception e) {
            hashResult = new HashMap();
        } finally {
            cerrarEntityManager();
        }
        return hashResult;
    }

    public static CmControlCierre castEntidadNegocio(CmRipsInhabilitados per) {
        CmControlCierre obj = new CmControlCierre();
        obj.setId(per.getId());
        obj.setMotivo(per.getMotivo());
        obj.setMaeContratoModalidadCodigo(per.getMaeContratoModalidadCodigo());
        obj.setMaeContratoModalidadId(per.getMaeContratoModalidadId());
        obj.setMaeContratoModalidadValor(per.getMaeContratoModalidadValor());
        obj.setFechaHoraDesde(per.getFechaHoraDesde());
        obj.setCoberturaCierre(per.getCoberturaCierre());
        obj.setFechaHoraHasta(per.getFechaHoraHasta());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        return obj;
    }

    public static CmRipsInhabilitados castNegocioEntidad(CmControlCierre obj) {
        CmRipsInhabilitados per = new CmRipsInhabilitados();
         if (obj.getId() != null) {
         per.setId(obj.getId());
         }
        per.setMotivo(obj.getMotivo());
        per.setMaeContratoModalidadCodigo(obj.getMaeContratoModalidadCodigo());
        per.setMaeContratoModalidadId(obj.getMaeContratoModalidadId());
        per.setMaeContratoModalidadValor(obj.getMaeContratoModalidadValor());
        per.setFechaHoraDesde(obj.getFechaHoraDesde());
        per.setFechaHoraHasta(obj.getFechaHoraHasta());
        per.setCoberturaCierre(obj.getCoberturaCierre());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }
}
