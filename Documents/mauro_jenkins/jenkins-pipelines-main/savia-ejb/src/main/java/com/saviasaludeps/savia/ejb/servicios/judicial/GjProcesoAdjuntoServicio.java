/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.judicial;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.judicial.GjProceso;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoHistorico;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoAdjunto;
import com.saviasaludeps.savia.ejb.entidades.GjProcesoAdjuntos;
import com.saviasaludeps.savia.ejb.entidades.GjProcesoHistoricos;
import com.saviasaludeps.savia.ejb.entidades.GjProcesos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.judicial.GjProcesoAdjuntoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author BsGomez
 */
@Stateless
@Remote(GjProcesoAdjuntoRemoto.class)
public class GjProcesoAdjuntoServicio extends GenericoServicio implements GjProcesoAdjuntoRemoto {

    @Override
    public int insertar(GjProcesoAdjunto obj) throws Exception {
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
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(t.id) FROM GjProcesoAdjuntos t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "xxx":
                            strQuery += "AND t.xxx LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "xx":
                            strQuery += "AND t.xxx LIKE '" + (String) e.getValue() + "%' ";
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
    public List<GjProcesoAdjunto> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GjProcesoAdjunto> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t FROM GjProcesoAdjuntos t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "xxx":
                            strQuery += "AND t.xxx LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "xx":
                            strQuery += "AND t.xx LIKE '" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND t.gjProcesosId = '" + (int) paramConsulta.getParametroConsulta1() + "' ";
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND t.gjProcesoHistoricosId = '" + (int) paramConsulta.getParametroConsulta2() + "' ";
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "t." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "t.id DESC";
            }
            List<GjProcesoAdjuntos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GjProcesoAdjuntos per : list) {
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
    public GjProcesoAdjunto eliminar(int id) throws Exception {
        GjProcesoAdjunto obj = null;
        try {
            GjProcesoAdjuntos ent = getEntityManager().find(GjProcesoAdjuntos.class, id);
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

    public static GjProcesoAdjuntos castNegocioEntidad(GjProcesoAdjunto obj) {
        GjProcesoAdjuntos per = new GjProcesoAdjuntos();
        per.setId(obj.getId());
        if (obj.getGjProcesosId() != null) {
            per.setGjProcesosId(new GjProcesos(obj.getGjProcesosId().getId()));
        }
        if (obj.getGjProcesoHistoricosId() != null) {
            per.setGjProcesoHistoricosId(new GjProcesoHistoricos(obj.getGjProcesoHistoricosId().getId()));
        }
        per.setNombreArchivo(obj.getNombreArchivo());
        per.setRuta(obj.getRuta());
        per.setArchivo(obj.getArchivo());
        per.setMaeTipoId(obj.getMaeTipoId());
        per.setMaeTipoCodigo(obj.getMaeTipoCodigo());
        per.setMaeTipoValor(obj.getMaeTipoValor());

        //Auditoría
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());

        return per;
    }

    public static GjProcesoAdjunto castEntidadNegocio(GjProcesoAdjuntos per) {
        GjProcesoAdjunto obj = new GjProcesoAdjunto();

        GjProceso proceso = new GjProceso();
        GjProcesoHistorico historico = new GjProcesoHistorico();
        obj.setId(per.getId());
        if (per.getGjProcesosId() != null) {
            proceso.setId(per.getGjProcesosId().getId());
        }
        if (per.getGjProcesoHistoricosId() != null) {
            historico.setId(per.getGjProcesoHistoricosId().getId());
        }
        obj.setNombreArchivo(per.getNombreArchivo());
        obj.setRuta(per.getRuta());
        obj.setArchivo(per.getArchivo());
        obj.setMaeTipoId(per.getMaeTipoId());
        obj.setMaeTipoCodigo(per.getMaeTipoCodigo());
        obj.setMaeTipoValor(per.getMaeTipoValor());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        return obj;
    }

    public static List<GjProcesoAdjunto> castEntidadNegocio(List<GjProcesoAdjuntos> adjuntosNegocio) {
        List<GjProcesoAdjunto> listaAdjuntos = new ArrayList();
        for (GjProcesoAdjuntos adjuntoNegocio : adjuntosNegocio) {
            listaAdjuntos.add(castEntidadNegocio(adjuntoNegocio));
        }

        return listaAdjuntos;
    }

    @Override
    public List<GjProcesoAdjunto> consultarPorProceso(int idGjProceso) throws java.lang.Exception {
        List<GjProcesoAdjunto> listResult = new ArrayList();
        try {
            String strQuery = "FROM GjProcesoAdjuntos p "
                    + "WHERE ";
            strQuery += " p.gjProcesosId.gjProcesosId.id = " + idGjProceso + " ";

            List<GjProcesoAdjuntos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GjProcesoAdjuntos cont : list) {
                listResult.add(castEntidadNegocio(cont));
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
    public List<GjProcesoAdjunto> consultarPorHistorico(int idGjProceso) throws java.lang.Exception {
        List<GjProcesoAdjunto> listResult = new ArrayList();
        try {
            String strQuery = "FROM GjProcesoAdjuntos p "
                    + "WHERE ";
            strQuery += " p.gjProcesoHistoricosId.gjProcesoHistoricosid.id = " + idGjProceso + " ";

            List<GjProcesoAdjuntos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GjProcesoAdjuntos cont : list) {
                listResult.add(castEntidadNegocio(cont));
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
