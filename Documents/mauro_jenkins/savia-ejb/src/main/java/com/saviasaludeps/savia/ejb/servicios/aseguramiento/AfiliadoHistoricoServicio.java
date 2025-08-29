/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.aseguramiento;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoHistorico;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliadoHistoricos;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoHistoricoRemoto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jose perez hernandez
 */
@Stateless
@Remote(AfiliadoHistoricoRemoto.class)
public class AfiliadoHistoricoServicio extends GenericoServicio implements AfiliadoHistoricoRemoto {

    public final static String POST_FIJO_BUSQUEDA_FECHA_HORA_LIMITE = " 23:59:59";
    public final static String POST_FIJO_BUSQUEDA_FECHA_HORA_INICIO = " 00:00:00";
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AsegAfiliadoHistoricos p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public List<AsegAfiliadoHistorico> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AsegAfiliadoHistorico> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM AsegAfiliadoHistoricos p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea LIKE '%" + (String) e.getValue() + "%' ";
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
            List<AsegAfiliadoHistoricos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (AsegAfiliadoHistoricos per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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
    public AsegAfiliadoHistorico consultar(int id) throws Exception {
        AsegAfiliadoHistorico objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AsegAfiliadoHistoricos) getEntityManager().find(AsegAfiliadoHistoricos.class, id));
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
    public int insertar(AsegAfiliadoHistorico obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidadLargo(obj)).getId();
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
    public void actualizar(AsegAfiliadoHistorico obj) throws Exception {
        try {
            getEntityManager().merge(castNegocioEntidadLargo(obj));
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AsegAfiliadoHistorico eliminar(int id) throws Exception {
        AsegAfiliadoHistorico obj = null;
        try {
            AsegAfiliadoHistoricos ent = getEntityManager().find(AsegAfiliadoHistoricos.class, id);
            if (ent != null) {
                obj = castEntidadNegocioLargo(ent);
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
    public List<AsegAfiliadoHistorico> consultarTodos() throws Exception {
        List<AsegAfiliadoHistorico> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegAfiliadoHistoricos p "
                    + "ORDER BY p.fechaHoraCrea DESC";
            List<AsegAfiliadoHistoricos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegAfiliadoHistoricos per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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
    public int consultarCantidadListaPorDocumentoFecha(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        SimpleDateFormat std = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String strQuery = "SELECT COUNT(p) FROM AsegAfiliadoHistoricos p "
                    + "WHERE p.id > 0 ";
            
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND p.asegAfiliadosId.numeroDocumento = "+paramConsulta.getParametroConsulta1()+"  ";
            } 
            
            if ( paramConsulta.getParametroConsulta2() != null) {
                strQuery +=  " AND p.fechaHoraCrea  <= '" + std.format( (Date) paramConsulta.getParametroConsulta2() ) + POST_FIJO_BUSQUEDA_FECHA_HORA_LIMITE + "' ";
            } 
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "documento":
                            strQuery += "AND p.asegAfiliadosId.numeroDocumento = " + e.getValue() + " ";
                            break;
                        case "fecha":
                            strQuery +=  " AND p.fechaHoraCrea  <= '" + std.format( (Date) e.getValue()  ) + "' ";
                            break;
                    }
                }
            }
            
            
           Query query = getEntityManager().createQuery(strQuery);
            
           cant = (int) (long) query.getSingleResult();

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
    public List<AsegAfiliadoHistorico> consultarListaPorDocumentoFecha(ParamConsulta paramConsulta) throws Exception {
        List<AsegAfiliadoHistorico> listResult = new ArrayList();
        SimpleDateFormat std = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String strQuery = "FROM AsegAfiliadoHistoricos p "
                    + "WHERE p.id > 0 ";
            
            if (paramConsulta.getParametroConsulta3() != null) {
                String subQuery = " SELECT COUNT(ap.id) FROM AsegAfiliadoHistoricos ap WHERE " +
                                  " (ap.fechaHoraCrea >= '" + std.format( (Date) paramConsulta.getParametroConsulta3()) + POST_FIJO_BUSQUEDA_FECHA_HORA_INICIO + "') " +
                                  " AND ( ap.fechaHoraCrea < '" + std.format( (Date) paramConsulta.getParametroConsulta3()) + POST_FIJO_BUSQUEDA_FECHA_HORA_LIMITE + "') AND" +
                                  " ap.id = p.id ";
                strQuery = " SELECT p , (" + subQuery + ") AS hayFechaSeleccionada FROM AsegAfiliadoHistoricos p "
                         + " WHERE p.id > 0 ";
            }
                     
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND p.asegAfiliadosId.numeroDocumento = "+paramConsulta.getParametroConsulta1()+"  ";
            } 
            
            if ( paramConsulta.getParametroConsulta2() != null) {
                strQuery +=  " AND p.fechaHoraCrea  <= '" + std.format( (Date) paramConsulta.getParametroConsulta2() ) + POST_FIJO_BUSQUEDA_FECHA_HORA_LIMITE + "' ";
            } 
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "documento":
                            strQuery += "AND p.asegAfiliadosId.numeroDocumento = " + e.getValue() + " ";
                            break;
                        case "fecha":
                            strQuery +=  " AND p.fechaHoraCrea  <= '" + std.format( (Date) e.getValue()  ) + "' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                if (paramConsulta.getParametroConsulta3() != null) {
                    strQuery += " hayFechaSeleccionada DESC, p.id DESC";
                } else {
                    strQuery += "p.id DESC";
                }

            }
                    
            Query query = getEntityManager().createQuery(strQuery);
            
            if ( paramConsulta.getParametroConsulta3() != null ) {
                List<Object[]> list = query
                        .setFirstResult(paramConsulta.getPrimerRegistro())
                        .setMaxResults(paramConsulta.getRegistrosPagina())
                        .getResultList();
                for (Object[] perM : list) {
                    AsegAfiliadoHistoricos per;
                    per = (AsegAfiliadoHistoricos) perM[0];
                    listResult.add(castEntidadNegocioLargo(per));
                }
            } else {
                List<AsegAfiliadoHistoricos> list = query
                        .setFirstResult(paramConsulta.getPrimerRegistro())
                        .setMaxResults(paramConsulta.getRegistrosPagina())
                        .getResultList();
                for (AsegAfiliadoHistoricos per : list) {
                    listResult.add(castEntidadNegocioLargo(per));
                }
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

    public static AsegAfiliadoHistorico castEntidadNegocioLargo(AsegAfiliadoHistoricos per) {
        AsegAfiliadoHistorico obj = new AsegAfiliadoHistorico();
        obj.setId(per.getId());
        obj.setTostringAfiliado(per.getTostringAfiliado());
        // auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        //objetos
        if (per.getAsegAfiliadosId() != null) {
            obj.setAsegAfiliado(new AsegAfiliado(per.getAsegAfiliadosId().getId()));
        }
        return obj;
    }

    public static AsegAfiliadoHistoricos castNegocioEntidadLargo(AsegAfiliadoHistorico obj) {
        AsegAfiliadoHistoricos per = new AsegAfiliadoHistoricos();
        per.setId(obj.getId());
        per.setTostringAfiliado(obj.getTostringAfiliado());
        // auditoria
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        //objetos
        if (obj.getAsegAfiliado() != null) {
            per.setAsegAfiliadosId(new AsegAfiliados(obj.getAsegAfiliado().getId()));
        }
        return per;
    }
}
