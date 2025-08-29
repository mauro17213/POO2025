/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Historico;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4Historicos;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos4;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4HistoricoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Stiven Giraldo
 */
@Stateless
@Remote(AuAnexo4HistoricoRemoto.class)
public class AuAnexo4HistoricoServicio extends GenericoServicio implements AuAnexo4HistoricoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuAnexo4Historicos p "
                    + "WHERE p.id > 0";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            cantidad = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            cantidad = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cantidad;
    }

    @Override
    public List<AuAnexo4Historico> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo4Historico> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo4Historicos p "
                    + "WHERE p.id > 0";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<AuAnexo4Historicos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexo4Historicos anexo4Historico : list) {
                listaResultados.add(castEntidadNegocio(anexo4Historico));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }

    @Override
    public AuAnexo4Historico consultar(int id) throws Exception {
        AuAnexo4Historico objRes = null;
        try {
            objRes = castEntidadNegocio((AuAnexo4Historicos) getEntityManager().find(AuAnexo4Historicos.class, id));
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
    public int insertar(AuAnexo4Historico obj) throws Exception {
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
    public void actualizar(AuAnexo4Historico obj) throws Exception {
        try {
            AuAnexo4Historicos per = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexo4Historicos a SET ";
            strQuery += "a.id = :id ,";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setProperties(per);
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
    public AuAnexo4Historico eliminar(int id) throws Exception {
        AuAnexo4Historico obj = null;
        try {
            AuAnexo4Historicos ent = getEntityManager().find(AuAnexo4Historicos.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public List<AuAnexo4Historico> historicosPorAnexo4(int idAnexo4) throws Exception {
        List<AuAnexo4Historico> listaResultados = new ArrayList();
        try {
            String strQuery = "SELECT p FROM AuAnexo4Historicos p INNER JOIN p.auAnexos4Id a ON p.auAnexos4Id.id = :idAnexo4";

            List<AuAnexo4Historicos> list = getEntityManager().createQuery(strQuery)
                    .setParameter("idAnexo4", idAnexo4)
                    .getResultList();
            for (AuAnexo4Historicos item : list) {
                listaResultados.add(castEntidadNegocio(item));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }

    private AuAnexo4Historico castEntidadNegocio(AuAnexo4Historicos entidad) {
        AuAnexo4Historico negocio = new AuAnexo4Historico();
        negocio.setId(entidad.getId());
        negocio.setAuAnexos4Id(new AuAnexo4(entidad.getAuAnexos4Id().getId()));
        negocio.setMaeCausaId(entidad.getMaeCausaId());
        negocio.setMaeCausaCodigo(entidad.getMaeCausaCodigo());
        negocio.setMaeCausaValor(entidad.getMaeCausaValor());
        negocio.setEstado(entidad.getEstado());
        negocio.setObservacionAutorizacion(entidad.getObservacionAutorizacion());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        return negocio;
    }

    private AuAnexo4Historicos castNegocioEntidad(AuAnexo4Historico negocio) {
        AuAnexo4Historicos entidad = new AuAnexo4Historicos();
        entidad.setId(negocio.getId());
        entidad.setAuAnexos4Id(new AuAnexos4(negocio.getAuAnexos4Id().getId()));
        entidad.setMaeCausaId(negocio.getMaeCausaId());
        entidad.setMaeCausaCodigo(negocio.getMaeCausaCodigo());
        entidad.setMaeCausaValor(negocio.getMaeCausaValor());
        entidad.setEstado(negocio.getEstado());
        entidad.setObservacionAutorizacion(negocio.getObservacionAutorizacion());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        return entidad;
    }

}
