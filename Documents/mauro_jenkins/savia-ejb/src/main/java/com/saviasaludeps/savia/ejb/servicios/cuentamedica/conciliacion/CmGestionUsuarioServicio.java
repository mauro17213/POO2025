/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGestionUsuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmDetalles;
import com.saviasaludeps.savia.ejb.entidades.CmGestionUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmGestionUsuarioRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jperezn
 */
@Stateless
@Remote(CmGestionUsuarioRemoto.class)
@Local(CmGestionUsuarioLocal.class)
public class CmGestionUsuarioServicio extends GenericoServicio implements CmGestionUsuarioLocal, CmGestionUsuarioRemoto {

    @Override
    public  int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception{
         int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cmi) FROM CmGestionUsuarios cmu "
                    + "WHERE cmu.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery += "AND cmu.tipo = " + (String) e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND cmu.estado = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND cmu.fechaHoraInicio = '" + (String) e.getValue() + "' ";
                            break;
                        case "registros":
                            strQuery += "AND cmu.registros = '" + (String) e.getValue() + "' ";
                            break;
                        case "ruta":
                            strQuery += "AND cmu.ruta = '" + (String) e.getValue() + "' ";
                            break;
                        case "archivo":
                            strQuery += "AND cmu.archivo = '" + (String) e.getValue() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND cmu.usuarioCrea = '" + (String) e.getValue() + "' ";
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
    public List<CmGestionUsuario> consultarLista(ParamConsulta paramConsulta) throws Exception{
         List<CmGestionUsuario> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmInformes cmu "
                    + "WHERE cmu.id > 0  ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery += "AND cmu.tipo = " + (String) e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND cmu.estado = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND cmu.fechaHoraInicio = '" + (String) e.getValue() + "' ";
                            break;
                        case "registros":
                            strQuery += "AND cmu.registros = '" + (String) e.getValue() + "' ";
                            break;
                        case "ruta":
                            strQuery += "AND cmu.ruta = '" + (String) e.getValue() + "' ";
                            break;
                        case "archivo":
                            strQuery += "AND cmu.archivo = '" + (String) e.getValue() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND cmu.usuarioCrea = '" + (String) e.getValue() + "' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "cmu." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "cmu.id DESC";
            }
            List<CmGestionUsuarios> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CmGestionUsuarios per : list) {
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
    public CmGestionUsuario consultar(int id) throws Exception {
        CmGestionUsuario obj = null;
        try {
            CmGestionUsuarios per = (CmGestionUsuarios) getEntityManager().find(CmGestionUsuarios.class, id);
            obj = castEntidadNegocio(per);
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public int insertar(CmGestionUsuario obj) throws Exception {
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
    public void actualizar(CmGestionUsuario obj) throws Exception {
        try {
           CmGestionUsuarios usuarioGestiona =  castNegocioEntidad(obj);
           
           String hql = "UPDATE CmGestionUsuarios SET"
               + " estado = :estado,"
               + " valorPagadoEps = :valorPagadoEps,"
               + " porcentajePagadoEps = :porcentajePagadoEps,"
               + " valorAceptadoIps  = :valorAceptadoIps,"
               + " porcentajeAceptadoIps = :porcentajeAceptadoIps,"
               + " usuarioGestiona = :usuarioGestiona"
               + " WHERE id = :id";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", usuarioGestiona.getEstado());
            query.setParameter("valorPagadoEps", usuarioGestiona.getValorPagadoEps());
            query.setParameter("porcentajePagadoEps", usuarioGestiona.getPorcentajePagadoEps());
            query.setParameter("valorAceptadoIps",usuarioGestiona.getValorAceptadoIps());
            query.setParameter("porcentajeAceptadoIps", usuarioGestiona.getPorcentajeAceptadoIps());
            query.setParameter("usuarioGestiona", usuarioGestiona.getUsuarioGestiona());
            query.setParameter("id", usuarioGestiona.getId());
            query.executeUpdate(); 
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CmGestionUsuario eliminar(int id) throws Exception {
        CmGestionUsuario obj = null;
        try {
            CmGestionUsuarios per = getEntityManager().find(CmGestionUsuarios.class, id);
            if (per != null) {
                obj = castEntidadNegocio(per);
                getEntityManager().remove(per);
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

    public static CmGestionUsuarios castNegocioEntidad(CmGestionUsuario ent) {
        CmGestionUsuarios neg = new CmGestionUsuarios();
        neg.setId(ent.getId());
        neg.setCmDetallesId(new CmDetalles(ent.getCmDetalle().getId()));
        neg.setEstado(ent.getEstado()); 
        neg.setValorAceptadoIps(ent.getValorAceptadoIps());
        neg.setPorcentajeAceptadoIps(ent.getPorcentajeAceptadoIps());
        neg.setPorcentajePagadoEps(ent.getPorcentajePagadoEps());
        neg.setValorPagadoEps(ent.getValorPagadoEps());
        neg.setUsuarioGestiona(ent.getUsuarioGestiona());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        return neg;
    }

    private CmGestionUsuario castEntidadNegocio(CmGestionUsuarios neg) {
        CmGestionUsuario ent = new CmGestionUsuario();
        ent.setId(neg.getId());
        ent.setCmDetalle( new CmDetalle(neg.getCmDetallesId().getId()) );
        ent.setEstado(neg.getEstado()); 
        ent.setValorAceptadoIps(neg.getValorAceptadoIps());
        ent.setPorcentajeAceptadoIps(neg.getPorcentajeAceptadoIps());
        ent.setPorcentajePagadoEps(neg.getPorcentajePagadoEps());
        ent.setValorPagadoEps(neg.getValorPagadoEps());
        ent.setUsuarioGestiona(neg.getUsuarioGestiona());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        return ent;
    }
}
