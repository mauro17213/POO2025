/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.crue;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9;
import com.saviasaludeps.savia.dominio.crue.RefTransporte;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.RefAnexos9;
import com.saviasaludeps.savia.ejb.entidades.RefTransportes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.crue.RefTransporteRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(RefTransporteRemoto.class)
@Local(RefTransporteLocal.class)
public class RefTransporteServicio extends GenericoServicio implements RefTransporteRemoto, RefTransporteLocal {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(r) FROM RefTransportes r "
                    + "WHERE r.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND r.refAnexos9Id.id = '" + paramConsulta.getParametroConsulta1() + "' ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeTipoTransporteId":
                            strQuery += "AND r.maeTipoTransporteId = '" + (String) e.getValue() + "' ";
                            break;
                        case "maeClaseTransporteId":
                            strQuery += "AND r.maeClaseTransporteId = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaHoraOrigen":
                            strQuery += "AND r.fechaHoraOrigen LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaHoraDestino":
                            strQuery += "AND r.fechaHoraDestino LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "maeTransporteLiquidacionId":
                            strQuery += "AND r.maeTransporteLiquidacionId = '" + (String) e.getValue() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND r.usuarioCrea LIKE '%" + (String) e.getValue() + "%' ";
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
    public List<RefTransporte> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<RefTransporte> listResult = new ArrayList<>();
        try {
            String strQuery = "SELECT r FROM RefTransportes r "
                    + "WHERE r.id > 0 ";
//            this.getParamConsulta3().setParametroConsulta1(id);
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND r.refAnexos9Id.id = '" + paramConsulta.getParametroConsulta1() + "' ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeTipoTransporteId":
                            strQuery += "AND r.maeTipoTransporteId = '" + (String) e.getValue() + "' ";
                            break;
                        case "maeClaseTransporteId":
                            strQuery += "AND r.maeClaseTransporteId = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaHoraOrigen":
                            strQuery += "AND r.fechaHoraOrigen LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaHoraDestino":
                            strQuery += "AND r.fechaHoraDestino LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "maeTransporteLiquidacionId":
                            strQuery += "AND r.maeTransporteLiquidacionId = '" + (String) e.getValue() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND r.usuarioCrea LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "r." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "r.id";
            }
            List<RefTransportes> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (RefTransportes per : list) {
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
    public int insertar(RefTransporte obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public RefTransporte consultar(int id) throws Exception {
        RefTransporte objetoResultado = new RefTransporte();
        try {
            objetoResultado = castEntidadNegocio((RefTransportes) getEntityManager().find(RefTransportes.class, id));
        } catch (NoResultException e) {
            objetoResultado = null;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objetoResultado;
    }

    public static RefTransporte castEntidadNegocio(RefTransportes ent) {
        RefTransporte obj = new RefTransporte();
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraDestino(ent.getFechaHoraDestino());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setFechaHoraOrigen(ent.getFechaHoraOrigen());
        obj.setId(ent.getId());
        obj.setMaeClaseTransporteCodigo(ent.getMaeClaseTransporteCodigo());
        obj.setMaeClaseTransporteId(ent.getMaeClaseTransporteId());
        obj.setMaeClaseTransporteValor(ent.getMaeClaseTransporteValor());
        obj.setMaeTipoTransporteCodigo(ent.getMaeTipoTransporteCodigo());
        obj.setMaeTipoTransporteId(ent.getMaeTipoTransporteId());
        obj.setMaeTipoTransporteValor(ent.getMaeTipoTransporteValor());
        obj.setMaeTransporteLiquidacionId(ent.getMaeTransporteLiquidacionId());
        obj.setMaeTransporteLiquidacionValor(ent.getMaeTransporteLiquidacionValor());
        obj.setMaeTransporteLiquidacionCodigo(ent.getMaeTransporteLiquidacionCodigo());
        obj.setObservacion(ent.getObservacion());
        if (ent.getRefAnexos9Id() != null) {
            obj.setRefAnexo9(new RefAnexo9(ent.getRefAnexos9Id().getId()));
        }
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        if (ent.getCntPrestadorSedesId() != null) {
            obj.setCntPrestadorSede(new CntPrestadorSede(ent.getCntPrestadorSedesId().getId()));
        }
        return obj;
    }

    public static RefTransportes castNegocioEntidad(RefTransporte obj) {
        RefTransportes ent = new RefTransportes();
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setFechaHoraDestino(obj.getFechaHoraDestino());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        ent.setFechaHoraOrigen(obj.getFechaHoraOrigen());
        ent.setId(obj.getId());
        ent.setMaeClaseTransporteCodigo(obj.getMaeClaseTransporteCodigo());
        ent.setMaeClaseTransporteId(obj.getMaeClaseTransporteId());
        ent.setMaeClaseTransporteValor(obj.getMaeClaseTransporteValor());
        ent.setMaeTipoTransporteCodigo(obj.getMaeTipoTransporteCodigo());
        ent.setMaeTipoTransporteId(obj.getMaeTipoTransporteId());
        ent.setMaeTipoTransporteValor(obj.getMaeTipoTransporteValor());
        ent.setMaeTransporteLiquidacionId(obj.getMaeTransporteLiquidacionId());
        ent.setMaeTransporteLiquidacionValor(obj.getMaeTransporteLiquidacionValor());
        ent.setMaeTransporteLiquidacionCodigo(obj.getMaeTransporteLiquidacionCodigo());
        ent.setObservacion(obj.getObservacion());
        if (obj.getRefAnexo9() != null) {
            ent.setRefAnexos9Id(new RefAnexos9(obj.getRefAnexo9().getId()));
        }
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        if (obj.getCntPrestadorSede() != null && obj.getCntPrestadorSede().getId() != null) {
            ent.setCntPrestadorSedesId(new CntPrestadorSedes(obj.getCntPrestadorSede().getId()));
        }
        return ent;
    }

}
