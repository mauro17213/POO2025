/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.maestro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaSoatTarifario;
import com.saviasaludeps.savia.dominio.maestro.MaSoatTarifarioValor;
import com.saviasaludeps.savia.ejb.entidades.MaSoatTarifarioValores;
import com.saviasaludeps.savia.ejb.entidades.MaSoatTarifarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.maestro.MaSoatTarifarioValorRemoto;
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
@Remote(MaSoatTarifarioValorRemoto.class)
public class MaSoatTarifarioValorServicio extends GenericoServicio implements MaSoatTarifarioValorRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaSoatTarifarioValores p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "agno":
                            strQuery += " AND p.agno = " + e.getValue() + " ";
                            break;
                        case "puntos":
                            strQuery += " AND p.puntos = " + e.getValue() + " ";
                            break;
                        case "valor":
                            strQuery += " AND p.valor = " + e.getValue() + " ";
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
    public List<MaSoatTarifarioValor> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<MaSoatTarifarioValor> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaSoatTarifarioValores p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "agno":
                            strQuery += " AND p.agno = " + e.getValue() + " ";
                            break;
                        case "puntos":
                            strQuery += " AND p.puntos = " + e.getValue() + " ";
                            break;
                        case "valor":
                            strQuery += " AND p.valor = " + e.getValue() + " ";
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
            List<MaSoatTarifarioValores> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (MaSoatTarifarioValores per : list) {
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
    public MaSoatTarifarioValor consultar(int id) throws java.lang.Exception {
        MaSoatTarifarioValor objRes = null;
        try {
            objRes = castEntidadNegocio((MaSoatTarifarioValores) getEntityManager().find(MaSoatTarifarioValores.class, id));
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
    public int insertar(MaSoatTarifarioValor obj) throws java.lang.Exception {
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
    public void actualizar(MaSoatTarifarioValor obj) throws java.lang.Exception {
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
    public MaSoatTarifarioValor eliminar(int id) throws java.lang.Exception {
        MaSoatTarifarioValor obj = null;
        try {
            MaSoatTarifarioValores ent = getEntityManager().find(MaSoatTarifarioValores.class, id);
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
    public List<MaSoatTarifarioValor> consultarTodos() throws java.lang.Exception {
        List<MaSoatTarifarioValor> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaSoatTarifarioValores p "
                    + "ORDER BY p.id ";
            List<MaSoatTarifarioValores> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaSoatTarifarioValores per : list) {
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

    private MaSoatTarifarioValor castEntidadNegocio(MaSoatTarifarioValores per) {
        MaSoatTarifarioValor obj = new MaSoatTarifarioValor();

        obj.setId(per.getId());
        obj.setAgno(per.getAgno());
        obj.setValor(per.getValor());
        //objetos
        if (per.getMaSoatTarifariosId() != null) {
            obj.setMaSoatTarifario(new MaSoatTarifario(per.getMaSoatTarifariosId().getId()));
        }
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        //obj.setFechaHoraModifica(per.getFechaHoraModifica());
        //obj.setTerminalModifica(per.getTerminalModifica());
        //obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }

    private MaSoatTarifarioValores castNegocioEntidad(MaSoatTarifarioValor obj) {
        MaSoatTarifarioValores per = new MaSoatTarifarioValores();

        per.setId(obj.getId());
        per.setAgno(obj.getAgno());
        per.setValor(obj.getValor());
        //objetos
        if (obj.getMaSoatTarifario() != null) {
            per.setMaSoatTarifariosId(new MaSoatTarifarios(obj.getMaSoatTarifario().getId()));
        }
        //auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        //per.setFechaHoraModifica(obj.getFechaHoraModifica());
        //per.setTerminalModifica(obj.getTerminalModifica());
        //per.setUsuarioModifica(obj.getUsuarioModifica());
        return per;
    }

    @Override
    public List<MaSoatTarifarioValor> consultarPorSoatTarifario(int idMaSoatTarifario) throws java.lang.Exception {
        List<MaSoatTarifarioValor> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaSoatTarifarioValores p "
                    + " WHERE p.maSoatTarifariosId.id = " + idMaSoatTarifario
                    + " ORDER BY p.agno DESC ";
            List<MaSoatTarifarioValores> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaSoatTarifarioValores per : list) {
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
    public MaSoatTarifarioValor consultarPorSoatTarifarioYAgno(Integer IdmanualTarifario, String agno) throws java.lang.Exception {
        MaSoatTarifarioValor objRes = null;
        int i = 0;
        try {
            String strQuery = "FROM MaSoatTarifarioValores p "
                    + "WHERE p.maSoatTarifariosId.id = " + IdmanualTarifario
                    + " AND p.agno = " + agno;
            List<MaSoatTarifarioValores> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaSoatTarifarioValores per : list) {
                if (i == 0) {
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

    @Override
    public MaSoatTarifarioValor consultarTarifarioTecnolgia(String codigoPropio) throws java.lang.Exception {
        MaSoatTarifarioValor objRes = null;
        int i = 0;
        try {
            String strQuery = "SELECT p FROM MaSoatTarifarioValores p "
                    + "INNER JOIN p.maSoatTarifariosId.maTecnologiasList t "
                    + "WHERE t.codigoPropio in("+codigoPropio+")"
                    + "ORDER BY p.agno DESC ";
            List<MaSoatTarifarioValores> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaSoatTarifarioValores per : list) {
                objRes = castEntidadNegocio(per);
                break;
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
