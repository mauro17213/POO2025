/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.tutela;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuGrupo;
import com.saviasaludeps.savia.dominio.tutela.TuGrupoHistorico;
import com.saviasaludeps.savia.ejb.entidades.TuGrupoHistoricos;
import com.saviasaludeps.savia.ejb.entidades.TuGrupos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.tutela.TuGrupoHistoricoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jperezn
 */
@Stateless
@Remote(TuGrupoHistoricoRemoto.class)
public class TuGrupoHistoricoServicio extends GenericoServicio implements TuGrupoHistoricoRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(t.id) FROM TuGrupoHistoricos t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND t.nombre LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += "AND t.descripcion LIKE '" + (String) e.getValue() + "%' ";
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
    public List<TuGrupoHistorico> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<TuGrupoHistorico> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t FROM TuGrupoHistoricos t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND t.nombre LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += "AND t.descripcion LIKE '" + (String) e.getValue() + "%' ";
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
            List<TuGrupoHistoricos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (TuGrupoHistoricos per : list) {
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
    public List<TuGrupoHistorico> consultarListaPorIdGrupo(int idGrupo) throws Exception {
        List<TuGrupoHistorico> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM TuGrupoHistoricos p "
                    + "WHERE p.tuGruposId.id = " + idGrupo + " ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<TuGrupoHistoricos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (TuGrupoHistoricos usuarios : list) {
                listaResultados.add(castEntidadNegocio(usuarios));
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
    public TuGrupoHistorico consultar(int id) throws Exception {
        TuGrupoHistorico objRes = null;
        try {
            TuGrupoHistoricos per = getEntityManager().find(TuGrupoHistoricos.class, id);
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
    public int insertar(TuGrupoHistorico obj) throws Exception {
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
    public void actualizar(TuGrupoHistorico obj) throws Exception {
        try {
            String hql = "UPDATE TuGrupoHistoricos SET "
                    + " nombre = :nombre,"
                    + " descripcion = :descripcion,"
                    + " orden = :orden,"
                    + " ultimoUsuarioId = :ultimoUsuarioId,"
                    + " activo = :activo,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
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
    public TuGrupoHistorico eliminar(int id) throws Exception {
        TuGrupoHistorico obj = null;
        try {
            TuGrupoHistoricos ent = getEntityManager().find(TuGrupoHistoricos.class, id);
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
    
    
    public static TuGrupoHistorico castEntidadNegocio(TuGrupoHistoricos per) {
        TuGrupoHistorico obj = new TuGrupoHistorico();
        obj.setId(per.getId());
        if (per.getTuGruposId() != null) {
            obj.setTuGrupo(new TuGrupo(per.getTuGruposId().getId()));
        }
        obj.setToString(per.getToString());
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        return obj;
    }
    
    public static TuGrupoHistoricos castNegocioEntidad(TuGrupoHistorico obj) {
        TuGrupoHistoricos per = new TuGrupoHistoricos();
        per.setId(obj.getId());
        if (obj.getTuGrupo() != null) {
            per.setTuGruposId(new TuGrupos(obj.getTuGrupo().getId()));
        }
        per.setToString(obj.getToString());
        //Auditoría
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        return per;
    }
}
