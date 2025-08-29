/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.crue;

import com.saviasaludeps.savia.dominio.crue.AuAnexo2Rescate;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2RescateGestion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2RescateGestionRemoto;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo2RescateGestiones;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo2Rescates;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author iavenegas
 */
@Stateless
@Remote(AuAnexo2RescateGestionRemoto.class)
public class AuAnexo2RescateGestionServicio extends GenericoServicio implements AuAnexo2RescateGestionRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(a) FROM AuAnexo2RescateGestiones a WHERE a.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND  a.auAnexo2RescatesId.id = " + paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND a.id = " + e.getValue() + " ";
                            break;
                        case "tipo":
                            strQuery += " AND a.tipo = " + e.getValue() + " ";
                            break;
                        case "observacion":
                            strQuery += " AND a.observacion LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "auAnexo2Rescate.id":
                            strQuery += " AND a.auAnexo2RescatesId.id = " + e.getValue() + " ";
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
    public List<AuAnexo2RescateGestion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo2RescateGestion> listResult = new ArrayList();
        try {
            String strQuery = "SELECT a FROM AuAnexo2RescateGestiones a WHERE a.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND  a.auAnexo2RescatesId.id = " + paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND a.id = " + e.getValue() + " ";
                            break;
                        case "tipo":
                            strQuery += " AND a.tipo = " + e.getValue() + " ";
                            break;
                        case "observacion":
                            strQuery += " AND a.observacion LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "auAnexo2Rescate.id":
                            strQuery += " AND a.auAnexo2RescatesId.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    default:
                        strQuery += "a." + paramConsulta.getOrden() + " "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                }
            } else {
                strQuery += "a.id DESC";
            }
            List<AuAnexo2RescateGestiones> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexo2RescateGestiones entidad : list) {
                listResult.add(castEntidadNegocio(entidad));
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
    public AuAnexo2RescateGestion consultar(int id) throws Exception {
        AuAnexo2RescateGestion objResult = new AuAnexo2RescateGestion();
        try {
            objResult = castEntidadNegocio((AuAnexo2RescateGestiones) getEntityManager().find(AuAnexo2RescateGestiones.class, id));
        } catch (NoResultException e) {
            objResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objResult;
    }

    @Override
    public int insertar(AuAnexo2RescateGestion obj) throws Exception {
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
    public List<AuAnexo2RescateGestion> consutarGestionesPorRescate(int idRescate) throws Exception {
        List<AuAnexo2RescateGestion> rescates = new ArrayList();

        String strQuery = "SELECT p FROM AuAnexo2RescateGestiones AS p WHERE p.auAnexo2RescatesId.id=:idRescate ";
        try {
            List<AuAnexo2RescateGestiones> list = getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("idRescate", idRescate)
                    .getResultList();
            for (AuAnexo2RescateGestiones obj : list) {
                rescates.add(castEntidadNegocio(obj));
            }

        } catch (NoResultException e) {
            rescates = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return rescates;
    }

    public static AuAnexo2RescateGestion castEntidadNegocio(AuAnexo2RescateGestiones ent) {
        AuAnexo2RescateGestion obj = new AuAnexo2RescateGestion();
        obj.setAuAnexo2Rescate(new AuAnexo2Rescate(ent.getAuAnexo2RescatesId().getId()));
        obj.setTipo(ent.getTipo());
        obj.setObservacion(ent.getObservacion());
        obj.setFechaHoraGestion(ent.getFechaHoraGestion());
        obj.setFechaHoraDireccionamiento(ent.getFechaHoraDireccionamiento());
        obj.setMaeMotivoRescateId(ent.getMaeMotivoRescateId());
        obj.setMaeMotivoRescateCodigo(ent.getMaeMotivoRescateCodigo());
        obj.setMaeMotivoRescateValor(ent.getMaeMotivoRescateValor());
        obj.setMaeMotivoRescateTipo(ent.getMaeMotivoRescateTipo());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }

    public static AuAnexo2RescateGestiones castNegocioEntidad(AuAnexo2RescateGestion obj) {
        AuAnexo2RescateGestiones ent = new AuAnexo2RescateGestiones();
        ent.setAuAnexo2RescatesId(new AuAnexo2Rescates(obj.getAuAnexo2Rescate().getId()));
        ent.setTipo(obj.getTipo());
        ent.setObservacion(obj.getObservacion());
        ent.setFechaHoraGestion(obj.getFechaHoraGestion());
        ent.setFechaHoraDireccionamiento(obj.getFechaHoraDireccionamiento());
        ent.setMaeMotivoRescateId(obj.getMaeMotivoRescateId());
        ent.setMaeMotivoRescateCodigo(obj.getMaeMotivoRescateCodigo());
        ent.setMaeMotivoRescateValor(obj.getMaeMotivoRescateValor());
        ent.setMaeMotivoRescateTipo(obj.getMaeMotivoRescateTipo());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

}
