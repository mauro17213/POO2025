package com.saviasaludeps.savia.ejb.servicios.judicial;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.judicial.GjProceso;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoGarantia;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoHistorico;
import com.saviasaludeps.savia.ejb.entidades.GjProcesoGarantias;
import com.saviasaludeps.savia.ejb.entidades.GjProcesoHistoricos;
import com.saviasaludeps.savia.ejb.entidades.GjProcesos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.judicial.GjProcesoGarantiaRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author BStevenG
 */
@Stateless
@Remote(GjProcesoGarantiaRemoto.class)
public class GjProcesoGarantiaServicio extends GenericoServicio implements GjProcesoGarantiaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(t.id) FROM GjProcesoGarantias t "
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
    public List<GjProcesoGarantia> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GjProcesoGarantia> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t FROM GjProcesoGarantias t "
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
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "t." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "t.id ASC";
            }
            List<GjProcesoGarantias> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GjProcesoGarantias per : list) {
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
    public List<GjProcesoGarantia> consultarListaPorIdProceso(int idProceso) throws Exception {
        List<GjProcesoGarantia> listaResultados = new ArrayList<>();
        try {
            String strQuery = "FROM GjProcesoGarantias p WHERE p.gjProcesosId.id = :idProceso ORDER BY p.id DESC";
            TypedQuery<GjProcesoGarantias> query = getEntityManager().createQuery(strQuery, GjProcesoGarantias.class);
            query.setParameter("idProceso", idProceso);
            List<GjProcesoGarantias> list = query.getResultList();
            for (GjProcesoGarantias usuarios : list) {
                listaResultados.add(castEntidadNegocio(usuarios));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList<>();
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar la lista de procesos de garantía por ID de proceso", e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }

    @Override
    public GjProcesoGarantia consultar(int id) throws Exception {
        GjProcesoGarantia objRes = null;
        try {
            GjProcesoGarantias per = getEntityManager().find(GjProcesoGarantias.class, id);
            if (per != null) {
                objRes = castEntidadNegocio(per);
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
    public int insertar(GjProcesoGarantia obj) throws Exception {
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

    public static GjProcesoGarantia castEntidadNegocio(GjProcesoGarantias per) {
        GjProcesoGarantia obj = new GjProcesoGarantia();

        obj.setId(per.getId());

        obj.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setDocumento(per.getDocumento());
        obj.setNombres(per.getNombres());
        obj.setApellidos(per.getApellidos());

        if (per.getGjProcesosId() != null) {
            obj.setGjProcesosId(new GjProceso(per.getGjProcesosId().getId()));
        }
        if (per.getGjProcesoHistoricosId() != null) {
            obj.setGjProcesoHistoricosId(new GjProcesoHistorico(per.getGjProcesoHistoricosId().getId()));
        }
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static GjProcesoGarantias castNegocioEntidad(GjProcesoGarantia obj) {
        GjProcesoGarantias per = new GjProcesoGarantias();

        per.setId(obj.getId());

        per.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
        per.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
        per.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
        per.setDocumento(obj.getDocumento());
        per.setNombres(obj.getNombres());
        per.setApellidos(obj.getApellidos());

        if (obj.getGjProcesosId() != null) {
            per.setGjProcesosId(new GjProcesos(obj.getGjProcesosId().getId()));
        }
        if (obj.getGjProcesoHistoricosId() != null) {
            per.setGjProcesoHistoricosId(new GjProcesoHistoricos(obj.getGjProcesoHistoricosId().getId()));
        }
        //Auditoría
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraModifica(obj.getFechaHoraCrea());
        return per;
    }
}
