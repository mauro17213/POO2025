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
import com.saviasaludeps.savia.ejb.utilidades.Util;
import com.saviasaludeps.savia.negocio.tutela.TuGrupoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jramirer
 */
@Stateless
@Remote(TuGrupoRemoto.class)
public class TuGrupoServicio extends GenericoServicio implements TuGrupoRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
                String strQuery = "SELECT COUNT(t.id) FROM TuGrupos t JOIN t.tuGrupoEstadosList  tgs  "
                    + "WHERE tgs.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND tgs.nombre LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += "AND tgs.descripcion LIKE '" + (String) e.getValue() + "%' ";
                            break;
                         case "objeto.grupoEstado.maeEstadoValor":
                            String cadenaMaestado = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND tgs.maeEstadoId IN ( " + cadenaMaestado + ") ";
                            break;
                    }
                }
            }
            
            strQuery += " GROUP BY tgs.tuGruposId.id ORDER BY t.id ";
             
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .getResultList().size();
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
    public List<TuGrupo> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<TuGrupo> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t FROM TuGrupos t JOIN t.tuGrupoEstadosList  tgs "
                    + " WHERE tgs.id > 0   ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND t.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += "AND t.descripcion LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "objeto.grupoEstado.maeEstadoValor":
                            String cadenaMaestado = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND tgs.maeEstadoId IN ( " + cadenaMaestado + ") ";
                            break;
                    }
                }
            }
            
            strQuery += " GROUP BY tgs.tuGruposId.id ";
                        
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                String order = paramConsulta.getOrden();
                order = order.replace("grupoEstado.maeEstadoValor", "tgs.maeEstadoValor").
                        replace("grupoEstado.reparto", "tgs.reparto");
                strQuery += order
                        + (paramConsulta.isAscendente() ? " ASC " : " DESC ");
            } else {
                strQuery += "t.id DESC";
            }
            List<TuGrupos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (TuGrupos per : list) {
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
    public List<TuGrupo> consultarListaPorEstado(ParamConsulta paramConsulta) throws Exception {
        List<TuGrupo> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t FROM TuGrupos t JOIN t.tuGrupoEstadosList  tgs "
                    + " WHERE tgs.id > 0 AND tgs.maeEstadoId = :maeEstadoId AND t.activo = '1'  ";

            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += " AND tgs.reparto= :reparto ";
            }
            
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
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                String order = paramConsulta.getOrden();
                if (order.equals("grupoEstado.maeEstadoValor")) {
                    strQuery += " tgs.maeEstadoValor "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else {
                    strQuery += " t." + order + " "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                }
            } else {
                strQuery += "tgs.id DESC";
            }
            
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("maeEstadoId", paramConsulta.getParametroConsulta1());
            if( paramConsulta.getParametroConsulta2() != null ){
                query.setParameter("reparto", paramConsulta.getParametroConsulta2());
            }
            
            List<TuGrupos> list = query
                    .getResultList();
            
            for (TuGrupos per : list) {
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
    public TuGrupo consultar(int id) throws Exception {
        TuGrupo objRes = null;
        try {
            TuGrupos per = getEntityManager().find(TuGrupos.class, id);
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
    public int insertar(TuGrupo obj) throws Exception {
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
    public void actualizar(TuGrupo obj) throws Exception {
        try {
            String hql = "UPDATE TuGrupos SET "
                    + " nombre = :nombre,"
                    + " descripcion = :descripcion,"
                    + " orden = :orden,"
                    + " tipoAuditorInicial = :tipoAuditorInicial,"
                    + " ultimoUsuarioId = :ultimoUsuarioId,"
                    + " activo = :activo,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("descripcion", obj.getDescripcion());
            query.setParameter("orden", obj.getOrden());
            query.setParameter("tipoAuditorInicial", obj.getTipoAuditorInicial());
            query.setParameter("ultimoUsuarioId", obj.getUltimoUsuarioId());
            query.setParameter("activo", obj.isActivo());
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
    public void actualizarUltimoUsuario(TuGrupo obj) throws Exception {
        try {
            if (obj.getUltimoUsuarioId() > 0 && obj.getId() != null) {
                String hql = "UPDATE TuGrupos SET "
                        + " ultimoUsuarioId = :ultimoUsuarioId"
                        + " WHERE id = :id";
                Query query = getEntityManager().createQuery(hql);
                query.setParameter("ultimoUsuarioId", obj.getUltimoUsuarioId());
                query.setParameter("id", obj.getId());
                query.executeUpdate();
            }
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void reiniciarUltimosUsuarios(String ids) throws Exception {
        try {
            if (ids != null) {
                String hql = "UPDATE TuGrupos SET "
                        + " ultimoUsuarioId =  0"
                        + " WHERE id IN (" + ids + ")";
                Query query = getEntityManager().createQuery(hql);
                query.executeUpdate();
            }
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    
    @Override
    public void actualizarOrden(TuGrupo obj) throws Exception {
        try {
             String hql = " UPDATE TuGrupos SET "
                    + " orden = :orden "
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("orden", obj.getOrden());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public TuGrupo eliminar(int id) throws Exception {
        TuGrupo obj = null;
        try {
            TuGrupos ent = getEntityManager().find(TuGrupos.class, id);
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
    
    
    public static TuGrupo castEntidadNegocio(TuGrupos per) {
        String descripcionEstadosAsociados = "";
        TuGrupo obj = new TuGrupo();
        obj.setId(per.getId());
        obj.setNombre(per.getNombre());
        obj.setDescripcion(per.getDescripcion());
        obj.setOrden(per.getOrden());
        obj.setActivo(per.getActivo());
        obj.setUltimoUsuarioId(per.getUltimoUsuarioId());
        obj.setTipoAuditorInicial(per.getTipoAuditorInicial());
        
        if (per.getTuGrupoEstadosList() != null && !per.getTuGrupoEstadosList().isEmpty()) {
    
            List<TuGrupoEstado> tuGrupoEstados = new ArrayList<>();
            for (TuGrupoEstados tuGrupoEstado : per.getTuGrupoEstadosList()) {            
                TuGrupoEstado tuGrupoEstadoIn =   new TuGrupoEstado(
                            tuGrupoEstado.getId(),
                            tuGrupoEstado.getMaeEstadoId(),
                            tuGrupoEstado.getMaeEstadoCodigo(),
                            tuGrupoEstado.getMaeEstadoValor(),
                            tuGrupoEstado.getReparto()
                    );
               tuGrupoEstados.add(tuGrupoEstadoIn);   
               obj.getGrupoEstado().setReparto(tuGrupoEstado.getReparto());
               obj.getGrupoEstado().getSelectedEstados().add(tuGrupoEstado.getMaeEstadoId());   
               descripcionEstadosAsociados += " - "+tuGrupoEstado.getMaeEstadoValor();
            }
            obj.setGrupoEstados(tuGrupoEstados);
        } 
        
        obj.setDescriptonGroupEstados(descripcionEstadosAsociados);
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }
    
    public static TuGrupos castNegocioEntidad(TuGrupo obj) {
        TuGrupos per = new TuGrupos();
        per.setId(obj.getId());
        per.setNombre(obj.getNombre()); 
        per.setDescripcion(obj.getDescripcion());
        per.setOrden(obj.getOrden());
        per.setActivo(obj.isActivo());
        per.setUltimoUsuarioId(obj.getUltimoUsuarioId());
        per.setTipoAuditorInicial(obj.getTipoAuditorInicial());
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
