/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.maestro;


import com.saviasaludeps.savia.dominio.maestro.MaInsumoIps;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.MaInsumosIps;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.maestro.MaInsumoIpsRemoto;
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
@Remote(MaInsumoIpsRemoto.class)
public class MaInsumoIpsServicio extends GenericoServicio implements MaInsumoIpsRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaInsumosIps p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeTipoId":
                            strQuery += " AND p.maeTipoId = " + e.getValue() + " ";
                            break;
                        case "maeTipoCodigo":
                            strQuery += " AND p.maeTipoCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoValor":
                            strQuery += " AND p.maeTipoValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "codigo":
                            strQuery += " AND p.codigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += " AND p.descripcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "abreviatura":
                            strQuery += " AND p.abreviatura  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
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
    public List<MaInsumoIps> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<MaInsumoIps> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM MaInsumosIps p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeTipoId":
                            strQuery += " AND p.maeTipoId = " + e.getValue() + " ";
                            break;
                        case "maeTipoCodigo":
                            strQuery += " AND p.maeTipoCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoValor":
                            strQuery += " AND p.maeTipoValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "codigo":
                            strQuery += " AND p.codigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += " AND p.descripcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "abreviatura":
                            strQuery += " AND p.abreviatura  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
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
            List<MaInsumosIps> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (MaInsumosIps per : list) {
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
    public MaInsumoIps consultar(int id) throws Exception {
        MaInsumoIps objRes = null;
        try {
            objRes = castEntidadNegocio((MaInsumosIps) getEntityManager().find(MaInsumosIps.class, id));
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
    public int insertar(MaInsumoIps obj) throws Exception {
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
    public void actualizar(MaInsumoIps obj) throws Exception {
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
    public MaInsumoIps eliminar(int id) throws Exception {
        MaInsumoIps obj = null;
        try {
            MaInsumosIps ent = getEntityManager().find(MaInsumosIps.class, id);
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
    public List<MaInsumoIps> consultarTodos() throws Exception {
        List<MaInsumoIps> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaInsumosIps p "
                    + "ORDER BY p.id ";
            List<MaInsumosIps> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaInsumosIps per : list) {
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

    public static MaInsumoIps castEntidadNegocio(MaInsumosIps per) {
        MaInsumoIps obj = new MaInsumoIps();
        obj.setId(per.getId());
        obj.setCntPrestadorSedesId(per.getCntPrestadorSedesId());
        obj.setMaeTipoId(per.getMaeTipoId());
        obj.setMaeTipoCodigo(per.getMaeTipoCodigo());
        obj.setMaeTipoValor(per.getMaeTipoValor());
        obj.setCodigo(per.getCodigo());
        obj.setDescripcion(per.getDescripcion());
        obj.setAbreviatura(per.getAbreviatura());
        obj.setActivo(per.getActivo());
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }

    public static MaInsumosIps castNegocioEntidad(MaInsumoIps obj) {
        MaInsumosIps per = new MaInsumosIps();
        per.setId(obj.getId());
        per.setCntPrestadorSedesId(obj.getCntPrestadorSedesId());
        per.setMaeTipoId(obj.getMaeTipoId());
        per.setMaeTipoCodigo(obj.getMaeTipoCodigo());
        per.setMaeTipoValor(obj.getMaeTipoValor());
        per.setCodigo(obj.getCodigo());
        per.setDescripcion(obj.getDescripcion());
        per.setAbreviatura(obj.getAbreviatura());
        per.setActivo(obj.isActivo());
        //auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        return per;
    }
    
}
