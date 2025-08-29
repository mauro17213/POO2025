/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.maestro;


import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaAgrupadorMedicamento;
import com.saviasaludeps.savia.ejb.entidades.MaAgrupadoresMedicamento;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.maestro.MaAgrupadorMedicamentoRemoto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jyperez
 */
@Stateless
@Remote(MaAgrupadorMedicamentoRemoto.class)
public class MaAgrupadorMedicamentoServicio extends GenericoServicio implements MaAgrupadorMedicamentoRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaAgrupadoresMedicamento p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigo":
                            strQuery += " AND p.codigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += " AND p.nombre  LIKE '%" + e.getValue() + "%' ";
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
    public List<MaAgrupadorMedicamento> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<MaAgrupadorMedicamento> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaAgrupadoresMedicamento p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigo":
                            strQuery += " AND p.codigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += " AND p.nombre  LIKE '%" + e.getValue() + "%' ";
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
            List<MaAgrupadoresMedicamento> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (MaAgrupadoresMedicamento per : list) {
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
    public MaAgrupadorMedicamento consultar(int id) throws Exception {
        MaAgrupadorMedicamento objRes = null;
        try {
            objRes = castEntidadNegocio((MaAgrupadoresMedicamento) getEntityManager().find(MaAgrupadoresMedicamento.class, id));
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
    public int insertar(MaAgrupadorMedicamento obj) throws Exception {
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
    public void actualizar(MaAgrupadorMedicamento obj) throws Exception {
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
    public MaAgrupadorMedicamento eliminar(int id) throws Exception {
        MaAgrupadorMedicamento obj = null;
        try {
            MaAgrupadoresMedicamento ent = getEntityManager().find(MaAgrupadoresMedicamento.class, id);
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
    public List<MaAgrupadorMedicamento> consultarTodos() throws Exception {
        List<MaAgrupadorMedicamento> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaAgrupadoresMedicamento p "
                    + "ORDER BY p.id ";
            List<MaAgrupadoresMedicamento> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaAgrupadoresMedicamento per : list) {
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
    public HashMap<Integer, MaAgrupadorMedicamento> consultarTodosHashMap() throws Exception {
        HashMap<Integer, MaAgrupadorMedicamento> listResult = new HashMap();
        try {
            String strQuery = "FROM MaAgrupadoresMedicamento p "
                    + "ORDER BY p.id ";
            List<MaAgrupadoresMedicamento> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaAgrupadoresMedicamento per : list) {
                MaAgrupadorMedicamento agrupador;
                agrupador = castEntidadNegocio(per);
                listResult.put(agrupador.getId(),agrupador);
            }
        } catch (NoResultException e) {
            listResult = new HashMap();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
    @Override
    public HashMap<String, MaAgrupadorMedicamento> consultarTodosHashMapPorCodigo() throws Exception {
        HashMap<String, MaAgrupadorMedicamento> listResult = new HashMap();
        try {
            String strQuery = "FROM MaAgrupadoresMedicamento p "
                    + "ORDER BY p.id ";
            List<MaAgrupadoresMedicamento> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaAgrupadoresMedicamento per : list) {
                MaAgrupadorMedicamento agrupador;
                agrupador = castEntidadNegocio(per);
                listResult.put(agrupador.getCodigo(),agrupador);
            }
        } catch (NoResultException e) {
            listResult = new HashMap();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
     @Override
    public List<MaAgrupadorMedicamento> consultarTodoSingleton() throws Exception {
        List<MaAgrupadorMedicamento> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t.id, "
                    + "t.codigo, "
                    + "t.nombre "
                    + "FROM MaAgrupadoresMedicamento t "
                    + "ORDER by t.id ";
            Query q = getEntityManager().createQuery(strQuery);
            List<Object[]> lista = q.getResultList();
            for (Object[] per : lista) {
                MaAgrupadorMedicamento agrupadorMedicamento = new MaAgrupadorMedicamento();
                agrupadorMedicamento.setId((Integer) per[0]);
                agrupadorMedicamento.setCodigo(per[1].toString());
                agrupadorMedicamento.setNombre(per[2].toString());
                listResult.add(agrupadorMedicamento);
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
    
    public static MaAgrupadorMedicamento castEntidadNegocio(MaAgrupadoresMedicamento per) {
        MaAgrupadorMedicamento obj = new MaAgrupadorMedicamento();
        obj.setId(per.getId());
        obj.setCodigo(per.getCodigo());
        obj.setNombre(per.getNombre());
        //no tiene auditoria
        return obj;
    }

    public static MaAgrupadoresMedicamento castNegocioEntidad(MaAgrupadorMedicamento obj) {
        MaAgrupadoresMedicamento per = new MaAgrupadoresMedicamento();
        per.setId(obj.getId());
        per.setCodigo(obj.getCodigo());
        per.setNombre(obj.getNombre());
        return per;
    }
    
}
