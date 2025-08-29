/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.tutela;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuGrupo;
import com.saviasaludeps.savia.dominio.tutela.TuGrupoEstado;
import com.saviasaludeps.savia.ejb.entidades.TuGrupoEstados;
import com.saviasaludeps.savia.ejb.entidades.TuGrupos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.tutela.TuGrupoEstadoRemoto;
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
@Remote(TuGrupoEstadoRemoto.class)
public class TuGrupoEstadoServicio extends GenericoServicio implements TuGrupoEstadoRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(t.id) FROM TuGrupoEstados t "
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
    public List<TuGrupoEstado> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<TuGrupoEstado> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t FROM TuGrupoEstados t "
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
            List<TuGrupoEstados> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (TuGrupoEstados per : list) {
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
    public List<TuGrupoEstado> consultarListaPorIdGrupo(int idGrupo) throws Exception {
        List<TuGrupoEstado> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM TuGrupoEstados p "
                    + "WHERE p.tuGruposId.id = " + idGrupo + " ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<TuGrupoEstados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (TuGrupoEstados usuarios : list) {
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
    public TuGrupoEstado consultar(int id) throws Exception {
        TuGrupoEstado objRes = null;
        try {
            TuGrupoEstados per = getEntityManager().find(TuGrupoEstados.class, id);
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
    public int insertar(TuGrupoEstado obj) throws Exception {
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
    public void actualizar(TuGrupoEstado obj) throws Exception {
        try {
            String hql = "UPDATE TuGrupoEstados SET "
                    + " maeEstadoId = :maeEstadoId,"
                    + " maeEstadoCodigo = :maeEstadoCodigo,"
                    + " maeEstadoValor = :maeEstadoValor,"
                    + " tuGruposId.id = :tuGruposId,"
                    + " reparto = :reparto,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("maeEstadoId", obj.getMaeEstadoId());
            query.setParameter("maeEstadoCodigo", obj.getMaeEstadoCodigo());
            query.setParameter("maeEstadoValor", obj.getMaeEstadoValor());
            query.setParameter("tuGruposId", obj.getTuGrupo().getId());
            query.setParameter("reparto", obj.isReparto());
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
    public TuGrupoEstado eliminar(int id) throws Exception {
        TuGrupoEstado obj = null;
        try {
            TuGrupoEstados ent = getEntityManager().find(TuGrupoEstados.class, id);
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
    
    
    public static TuGrupoEstado castEntidadNegocio(TuGrupoEstados per) {
        TuGrupoEstado obj = new TuGrupoEstado();
        obj.setId(per.getId());
        if (per.getTuGruposId() != null) {
            obj.setTuGrupo(new TuGrupo(per.getTuGruposId().getId()));
        }
        obj.setMaeEstadoId(per.getMaeEstadoId());
        obj.setMaeEstadoValor(per.getMaeEstadoValor());
        obj.setMaeEstadoCodigo(per.getMaeEstadoCodigo());
        obj.setReparto(per.getReparto());
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }
    
    public static TuGrupoEstados castNegocioEntidad(TuGrupoEstado obj) {
        TuGrupoEstados per = new TuGrupoEstados();
        per.setId(obj.getId());
        if(obj.getTuGrupo()!=null){
            per.setTuGruposId(new TuGrupos(obj.getTuGrupo().getId()));
        }
        per.setMaeEstadoId(obj.getMaeEstadoId());
        per.setMaeEstadoCodigo(obj.getMaeEstadoCodigo());
        per.setMaeEstadoValor(obj.getMaeEstadoValor());
        per.setReparto(obj.isReparto());
        //Auditoría
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }
}
