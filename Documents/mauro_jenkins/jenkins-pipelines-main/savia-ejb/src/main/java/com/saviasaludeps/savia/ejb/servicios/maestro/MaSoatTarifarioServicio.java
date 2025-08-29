/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.maestro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaSoatTarifario;
import com.saviasaludeps.savia.ejb.entidades.MaSoatTarifarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.maestro.MaSoatTarifarioRemoto;
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
@Remote(MaSoatTarifarioRemoto.class)
public class MaSoatTarifarioServicio extends GenericoServicio implements MaSoatTarifarioRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaSoatTarifarios p WHERE p.id > 0 ";
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
                            
                        case "grupo":
                            strQuery += " AND p.grupo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "puntos":
                            strQuery += " AND p.puntos  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "valor":
                            strQuery += " AND p.valor  LIKE '%" + e.getValue() + "%' ";
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
    public List<MaSoatTarifario> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<MaSoatTarifario> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaSoatTarifarios p WHERE p.id > 0 ";
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
                            
                        case "grupo":
                            strQuery += " AND p.grupo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "puntos":
                            strQuery += " AND p.puntos  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "valor":
                            strQuery += " AND p.valor  LIKE '%" + e.getValue() + "%' ";
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
            List<MaSoatTarifarios> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (MaSoatTarifarios per : list) {
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
    public MaSoatTarifario consultar(int id) throws java.lang.Exception {
        MaSoatTarifario objRes = null;
        try {
            objRes = castEntidadNegocio((MaSoatTarifarios) getEntityManager().find(MaSoatTarifarios.class, id));
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
    public int insertar(MaSoatTarifario obj) throws java.lang.Exception {
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
    public void actualizar(MaSoatTarifario obj) throws java.lang.Exception {
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
    public MaSoatTarifario eliminar(int id) throws java.lang.Exception {
        MaSoatTarifario obj = null;
        try {
            MaSoatTarifarios ent = getEntityManager().find(MaSoatTarifarios.class, id);
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
    public List<MaSoatTarifario> consultarTodos() throws java.lang.Exception {
        List<MaSoatTarifario> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaSoatTarifarios p "
                    + "ORDER BY p.id ";
            List<MaSoatTarifarios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaSoatTarifarios per : list) {
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

    private MaSoatTarifario castEntidadNegocio(MaSoatTarifarios per) {
        MaSoatTarifario obj = new MaSoatTarifario();
        
        obj.setId(per.getId());
        obj.setCodigo(per.getCodigo());
        obj.setDescripcion(per.getDescripcion());
        obj.setTipo(per.getTipo());
        obj.setGrupo(per.getGrupo());
        obj.setPuntos(per.getPuntos());
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }

    private MaSoatTarifarios castNegocioEntidad(MaSoatTarifario obj) {
        MaSoatTarifarios per = new MaSoatTarifarios();
        
        per.setId(obj.getId());
        per.setCodigo(obj.getCodigo());
        per.setDescripcion(obj.getDescripcion());
        per.setTipo(obj.getTipo());
        per.setGrupo(obj.getGrupo());
        per.setPuntos(obj.getPuntos());
        
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
    public MaSoatTarifario consultarPorCodigo(String codigo) throws java.lang.Exception {
        MaSoatTarifario objRes = null;
        int i = 0;
        try {
            String strQuery = "FROM MaSoatTarifarios p "
                    + "WHERE p.codigo = '" + codigo + "' " 
                    + " ORDER BY p.id DESC ";
            List<MaSoatTarifarios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaSoatTarifarios per : list) {
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
