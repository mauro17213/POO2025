/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.maestro;


import com.saviasaludeps.savia.dominio.maestro.MaTecnologiaGrupo;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.ejb.entidades.MaTecnologiaGrupos;
import com.saviasaludeps.savia.ejb.entidades.MaTecnologias;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.maestro.MaTecnologiaGrupoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author jyperez
 */
@Stateless
@Remote(MaTecnologiaGrupoRemoto.class)
public class MaTecnologiaGrupoServicio extends GenericoServicio implements MaTecnologiaGrupoRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaTecnologiaGrupos p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeGrupoTecnologiaId":
                            strQuery += " AND p.maeGrupoTecnologiaId = " + e.getValue() + " ";
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
    public List<MaTecnologiaGrupo> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<MaTecnologiaGrupo> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaTecnologiaGrupos p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                        case "maeGrupoTecnologiaId":
                            strQuery += " AND p.maeGrupoTecnologiaId = " + e.getValue() + " ";
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
            List<MaTecnologiaGrupos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (MaTecnologiaGrupos per : list) {
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
    public MaTecnologiaGrupo consultar(int id) throws Exception {
        MaTecnologiaGrupo objRes = null;
        try {
            objRes = castEntidadNegocio((MaTecnologiaGrupos) getEntityManager().find(MaTecnologiaGrupos.class, id));
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
    public int insertar(MaTecnologiaGrupo obj) throws Exception {
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
    public void actualizar(MaTecnologiaGrupo obj) throws Exception {
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
    public MaTecnologiaGrupo eliminar(int id) throws Exception {
        MaTecnologiaGrupo obj = null;
        try {
            MaTecnologiaGrupos ent = getEntityManager().find(MaTecnologiaGrupos.class, id);
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
    public List<MaTecnologiaGrupo> consultarTodos() throws Exception {
        List<MaTecnologiaGrupo> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaTecnologiaGrupos p "
                    + "ORDER BY p.id ";
            List<MaTecnologiaGrupos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaTecnologiaGrupos per : list) {
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
    public List<MaTecnologiaGrupo> consultarPorTecnologia(int id) throws Exception {
        List<MaTecnologiaGrupo> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaTecnologiaGrupos p WHERE p.maTecnologiasId.id = " + id
                    + " ORDER BY p.id ";
            List<MaTecnologiaGrupos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaTecnologiaGrupos per : list) {
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
    
    public static MaTecnologiaGrupo castEntidadNegocio(MaTecnologiaGrupos per) {
        MaTecnologiaGrupo obj = new MaTecnologiaGrupo();
        obj.setId(per.getId());
        obj.setMaeGrupoTecnologiaId(per.getMaeGrupoTecnologiaId());
        obj.setMaeGrupoTecnologiaCodigo(per.getMaeGrupoTecnologiaCodigo());
        obj.setMaeGrupoTecnologiaValor(per.getMaeGrupoTecnologiaValor());
        //objetos
        if(per.getMaTecnologiasId() != null) {
            obj.setMaTecnologia(new MaTecnologia(per.getMaTecnologiasId().getId()));
        }
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        return obj;
    }

    public static MaTecnologiaGrupos castNegocioEntidad(MaTecnologiaGrupo obj) {
        MaTecnologiaGrupos per = new MaTecnologiaGrupos();
        per.setId(obj.getId());
        per.setMaeGrupoTecnologiaId(obj.getMaeGrupoTecnologiaId());
        per.setMaeGrupoTecnologiaCodigo(obj.getMaeGrupoTecnologiaCodigo());
        per.setMaeGrupoTecnologiaValor(obj.getMaeGrupoTecnologiaValor());
        //objetos
        if(obj.getMaTecnologia() != null) {
            per.setMaTecnologiasId(new MaTecnologias(obj.getMaTecnologia().getId()));
        }
        //auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        return per;
    }

}
