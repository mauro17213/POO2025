/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.maestro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaEspecialidad;
import com.saviasaludeps.savia.ejb.entidades.MaEspecialidades;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.maestro.MaEspecialidadRemoto;
import java.util.ArrayList;
import java.util.HashMap;
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
@Remote(MaEspecialidadRemoto.class)
public class MaEspecialidadServicio extends GenericoServicio implements MaEspecialidadRemoto {
    @Override
    public List<MaEspecialidad> consultarTodos() throws Exception {
        List<MaEspecialidad> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaEspecialidades m "
                    + "ORDER BY m.id ";
            List<MaEspecialidades> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaEspecialidades per : list) {
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
    
    
    public static MaEspecialidad castEntidadNegocio(MaEspecialidades ent) {
        MaEspecialidad obj = new MaEspecialidad();
        obj.setId(ent.getId());
        obj.setCodigo(ent.getCodigo());
        obj.setNombre(ent.getNombre());
        obj.setDescripcion(ent.getDescripcion());
        obj.setActivo(ent.getActivo());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraModifica(ent.getFechaHporaModifica());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        return obj;
    }
    
        @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaEspecialidades p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigo":
                            strQuery += " AND p.codigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += " AND p.nombre  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += " AND p.descripcion  LIKE '%" + e.getValue() + "%' ";
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
    public List<MaEspecialidad> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<MaEspecialidad> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaEspecialidades p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigo":
                            strQuery += " AND p.codigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += " AND p.nombre  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += " AND p.descripcion  LIKE '%" + e.getValue() + "%' ";
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
            List<MaEspecialidades> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (MaEspecialidades per : list) {
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
    public MaEspecialidad consultar(int id) throws Exception {
        MaEspecialidad objRes = null;
        try {
            objRes = castEntidadNegocio((MaEspecialidades) getEntityManager().find(MaEspecialidades.class, id));
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
    public int insertar(MaEspecialidad obj) throws Exception {
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
    public void actualizar(MaEspecialidad obj) throws Exception {
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
    public MaEspecialidad eliminar(int id) throws Exception {
        MaEspecialidad obj = null;
        try {
            MaEspecialidades ent = getEntityManager().find(MaEspecialidades.class, id);
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

    public static MaEspecialidades castNegocioEntidad(MaEspecialidad obj) {
        MaEspecialidades per = new MaEspecialidades();
        per.setId(obj.getId());
        per.setCodigo(obj.getCodigo());
        per.setNombre(obj.getNombre());
        per.setDescripcion(obj.getDescripcion());
        per.setActivo(obj.isActivo());
        //auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHporaModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        return per;
    }

    @Override
    public MaEspecialidad consultarPorCodigo(String codigo) throws java.lang.Exception {
        MaEspecialidad objResult = null;
        try {
            String strQuery = "FROM MaEspecialidades m "
                    + "WHERE m.codigo = '"+ codigo +"' ";
            List<MaEspecialidades> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if (list != null && !list.isEmpty()) {
                objResult = castEntidadNegocio(list.get(0));
            }
        } catch (NoResultException e) {
            objResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objResult;
    }

    @Override
    public HashMap<String, MaEspecialidad> consultarHash() throws Exception {
        HashMap<String,MaEspecialidad> hashResult = new HashMap();
        try {
            String strQuery = "FROM MaEspecialidades m "
                    + "ORDER BY m.id ";
            List<MaEspecialidades> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaEspecialidades per : list) {
                MaEspecialidad obj = castEntidadNegocio(per);
                hashResult.put(obj.getCodigo(), obj);
            }
        } catch (NoResultException e) {
            hashResult = new HashMap();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return hashResult;
    }
}
