/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.aseguramiento;

import com.saviasaludeps.savia.dominio.aseguramiento.CsContribucionSolidaria;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CsContribucionesSolidarias;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.aseguramiento.CsContribucionSolidariaRemoto;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Session;

/**
 *
 * @author jose perez hernandez
 */
@Stateless
@Remote(CsContribucionSolidariaRemoto.class)
public class CsContribucionSolidariaServicio extends GenericoServicio implements CsContribucionSolidariaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM CsContribucionesSolidarias p ";
            //Enviaremos en el parámetro de la empresa, el id del prestador si lo posee. Sólo cuando es null, pertenece a Savia Salud EPS
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "WHERE p.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            } else {
                strQuery += "WHERE p.id > 0 ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "agno":
                            strQuery += "AND p.agno = " + e.getValue() + " ";
                            break;
                        case "valor":
                            strQuery += "AND p.valor = " + (BigDecimal) e.getValue() + " ";
                            break;
                        case "porcentaje":
                            strQuery += "AND p.porcentaje = " + (BigDecimal) e.getValue() + " ";
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
    public List<CsContribucionSolidaria> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CsContribucionSolidaria> listResult = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String strQuery = "FROM CsContribucionesSolidarias p ";
            //Enviaremos en el parámetro de la empresa, el id del prestador si lo posee. Sólo cuando es null, pertenece a Savia Salud EPS
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "WHERE p.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            } else {
                strQuery += "WHERE p.id > 0 ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "agno":
                            strQuery += "AND p.agno = " + e.getValue() + " ";
                            break;
                        case "valor":
                            strQuery += "AND p.valor = " + (BigDecimal) e.getValue() + " ";
                            break;
                        case "porcentaje":
                            strQuery += "AND p.porcentaje = " + (BigDecimal) e.getValue() + " ";
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
            List<CsContribucionesSolidarias> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (CsContribucionesSolidarias per : list) {
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
    public CsContribucionSolidaria consultar(int id) throws Exception {
        CsContribucionSolidaria objRes = null;
        try {
            objRes = castEntidadNegocio((CsContribucionesSolidarias) getEntityManager().find(CsContribucionesSolidarias.class, id));
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
    public int insertar(CsContribucionSolidaria obj) throws Exception {
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
    public void actualizar(CsContribucionSolidaria obj) throws Exception {
        try {
            CsContribucionesSolidarias per = castNegocioEntidad(obj);
            //getEntityManager().merge(castEntidadNegocio(obj));
            Session session = getEntityManager().unwrap(Session.class);
            //session.update(per);
            String strQuery = "UPDATE CsContribucionesSolidarias a SET ";
            strQuery += " a.maeContribucionSolidariaId = :maeContribucionSolidariaId ,";
            strQuery += " a.maeContribucionSolidariaCodigo = :maeContribucionSolidariaCodigo ,";
            strQuery += " a.maeContribucionSolidariaValor = :maeContribucionSolidariaValor ,";
            strQuery += " a.agno = :agno ,";
            strQuery += " a.valor = :valor ,";
            strQuery += " a.porcentaje = :porcentaje, ";
            strQuery += " a.usuarioModifica = :usuarioModifica, ";
            strQuery += " a.terminalModifica = :terminalModifica, ";
            strQuery += " a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(per);
            query.executeUpdate();
            
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CsContribucionSolidaria eliminar(int id) throws Exception {
        CsContribucionSolidaria obj = null;
        try {
            CsContribucionesSolidarias ent = getEntityManager().find(CsContribucionesSolidarias.class, id);
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
    public List<CsContribucionSolidaria> consultarTodos() throws Exception {
        List<CsContribucionSolidaria> listResult = new ArrayList();
        try {
            String strQuery = "FROM CsContribucionesSolidarias p "
                    + "ORDER BY p.fechaHoraCrea DESC";
            List<CsContribucionesSolidarias> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CsContribucionesSolidarias per : list) {
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

    public static CsContribucionSolidaria castEntidadNegocio(CsContribucionesSolidarias per) {
        CsContribucionSolidaria obj = new CsContribucionSolidaria();
        obj.setId(per.getId());
        obj.setMaeContribucionSolidariaId(per.getMaeContribucionSolidariaId());
        obj.setMaeContribucionSolidariaCodigo(per.getMaeContribucionSolidariaCodigo());
        obj.setMaeContribucionSolidariaValor(per.getMaeContribucionSolidariaValor());
        obj.setAgno(per.getAgno());
        obj.setValor(per.getValor());
        obj.setPorcentaje(per.getPorcentaje());
        // auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        //objetos

        return obj;
    }

    public static CsContribucionesSolidarias castNegocioEntidad(CsContribucionSolidaria obj) {
        CsContribucionesSolidarias per = new CsContribucionesSolidarias();
        per.setId(obj.getId());
        per.setMaeContribucionSolidariaId(obj.getMaeContribucionSolidariaId());
        per.setMaeContribucionSolidariaCodigo(obj.getMaeContribucionSolidariaCodigo());
        per.setMaeContribucionSolidariaValor(obj.getMaeContribucionSolidariaValor());
        per.setAgno(obj.getAgno());
        per.setValor(obj.getValor());
        per.setPorcentaje(obj.getPorcentaje());
        // auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        return per;
    }

    @Override
    public CsContribucionSolidaria consultarPorPorcentaje(int idmaestroPorcentaje) throws java.lang.Exception {
        CsContribucionSolidaria objResult = null;
        try {
            SimpleDateFormat formatoAgno = new SimpleDateFormat("yyyy");
            String strQuery = "FROM CsContribucionesSolidarias p "
                    + "WHERE p.maeContribucionSolidariaId = " + idmaestroPorcentaje + " "
                    + "AND p.agno = " + formatoAgno.format(new Date()) + " "
                    + "ORDER BY p.id DESC ";
            List<CsContribucionesSolidarias> list = getEntityManager().createQuery(strQuery)
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

}
