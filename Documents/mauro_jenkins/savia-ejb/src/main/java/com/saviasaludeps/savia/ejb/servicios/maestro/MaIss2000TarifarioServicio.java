/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.maestro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaIss2000Tarifario;
import com.saviasaludeps.savia.ejb.entidades.MaIss2000Tarifarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.maestro.MaIss2000TarifarioRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author AlexanderDiaz
 */
@Stateless
@Remote(MaIss2000TarifarioRemoto.class)
public class MaIss2000TarifarioServicio extends GenericoServicio implements MaIss2000TarifarioRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaIss2000Tarifarios p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigo":
                            strQuery += " AND p.codigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += " AND p.descripcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += " AND p.tipo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "uvr":
                            strQuery += " AND p.uvr  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "monto":
                            strQuery += " AND p.monto  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += " AND p.fechaHoraCrea  LIKE '" + e.getValue() + "' ";
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
    public List<MaIss2000Tarifario> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<MaIss2000Tarifario> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaIss2000Tarifarios p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigo":
                            strQuery += " AND p.codigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += " AND p.descripcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += " AND p.tipo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "uvr":
                            strQuery += " AND p.uvr  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "monto":
                            strQuery += " AND p.monto  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += " AND p.fechaHoraCrea  LIKE '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<MaIss2000Tarifarios> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (MaIss2000Tarifarios per : list) {
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
    public MaIss2000Tarifario consultar(int id) throws java.lang.Exception {
        MaIss2000Tarifario objRes = null;
        try {
            objRes = castEntidadNegocio((MaIss2000Tarifarios) getEntityManager().find(MaIss2000Tarifarios.class, id));
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
    public int insertar(MaIss2000Tarifario obj) throws java.lang.Exception {
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
    public void actualizar(MaIss2000Tarifario obj) throws java.lang.Exception {
        try {
            getEntityManager().merge(castNegocioEntidad(obj));
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public MaIss2000Tarifario eliminar(int id) throws java.lang.Exception {
        MaIss2000Tarifario obj = null;
        try {
            MaIss2000Tarifarios ent = getEntityManager().find(MaIss2000Tarifarios.class, id);
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

    @Override
    public List<MaIss2000Tarifario> consultarTodos() throws java.lang.Exception {
        List<MaIss2000Tarifario> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaIss2000Tarifarios p "
                    + "ORDER BY p.id ";
            List<MaIss2000Tarifarios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaIss2000Tarifarios per : list) {
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

    private MaIss2000Tarifario castEntidadNegocio(MaIss2000Tarifarios per) {
        MaIss2000Tarifario obj = new MaIss2000Tarifario();
        
        obj.setId(per.getId());
        obj.setCodigo(per.getCodigo());
        obj.setDescripcion(per.getDescripcion());
        obj.setTipo(per.getTipo());
        obj.setUvr(per.getUvr());
        obj.setMonto(per.getMonto());
        
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }

    private MaIss2000Tarifarios castNegocioEntidad(MaIss2000Tarifario obj) {
        MaIss2000Tarifarios per = new MaIss2000Tarifarios();
        per.setId(obj.getId());
        per.setCodigo(obj.getCodigo());
        per.setDescripcion(obj.getDescripcion());
        per.setTipo(obj.getTipo());
        per.setUvr(obj.getUvr());
        per.setMonto(obj.getMonto());
        
        //auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        return per;
    }

    @Override
    public MaIss2000Tarifario consultarPorCodigo(String codigo) throws java.lang.Exception {
        MaIss2000Tarifario objRes = null;
        int i = 0;
        try {
            String strQuery = "FROM MaIss2000Tarifarios p "
                    + "WHERE p.codigo = '" + codigo+ "' "
                    + " ORDER BY p.id DESC ";
            List<MaIss2000Tarifarios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaIss2000Tarifarios per : list) {
                if (i==0) {
                    objRes = castEntidadNegocio(per);
                    i++;
                }
            }
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }
    
}
