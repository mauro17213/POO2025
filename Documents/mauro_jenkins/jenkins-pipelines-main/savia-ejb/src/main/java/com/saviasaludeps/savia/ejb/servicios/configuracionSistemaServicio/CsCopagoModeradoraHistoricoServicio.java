/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.configuracionSistemaServicio;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.configuracionSistema.CsCopagoModeradoraHistorico;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos4;
import com.saviasaludeps.savia.ejb.entidades.CmFacturas;
import com.saviasaludeps.savia.ejb.entidades.CsCopagoModeradoraHistoricos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.configuracionSistema.CsCopagoModeradoraHistoricoRemoto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author sgiraldo
 */
@Stateless
@Remote(CsCopagoModeradoraHistoricoRemoto.class)
public class CsCopagoModeradoraHistoricoServicio extends GenericoServicio implements CsCopagoModeradoraHistoricoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(u) FROM CsCopagoModeradoraHistoricos u "
                    + "WHERE u.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND u.agno =" + paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND u.asegAfiliadosId.id =" + paramConsulta.getParametroConsulta2() + " "
                        + " AND u.valorProyectado IS NOT NULL "
                        + " AND u.valorProyectado > 0 ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "agno":
                            strQuery += "AND u.agno =" + e.getValue() + " ";
                            break;
                        case "asegAfiliadosId.id":
                            strQuery += "AND u.asegAfiliadosId.id =" + e.getValue() + " ";
                            break;
                        case "moderadoraCopago":
                            strQuery += "AND u.moderadoraCopago=" + e.getValue() + " ";
                            break;
                        case "valorProyectado":
                            strQuery += "AND u.valorProyectado=" + e.getValue() + " ";
                            break;
                        case "auAnexos4Id.id":
                            strQuery += "AND u.auAnexos4Id.id=" + e.getValue() + " ";
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
    public List<CsCopagoModeradoraHistorico> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CsCopagoModeradoraHistorico> listResult = new ArrayList();
        try {
            String strQuery = "SELECT u FROM CsCopagoModeradoraHistoricos u "
                    + "WHERE u.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND u.agno =" + paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND u.asegAfiliadosId.id =" + paramConsulta.getParametroConsulta2() + " "
                        + " AND u.valorProyectado IS NOT NULL "
                        + " AND u.valorProyectado > 0 ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "agno":
                            strQuery += "AND u.agno =" + e.getValue() + " ";
                            break;
                        case "asegAfiliadosId.id":
                            strQuery += "AND u.asegAfiliadosId.id =" + e.getValue() + " ";
                            break;
                        case "moderadoraCopago":
                            strQuery += "AND u.moderadoraCopago=" + e.getValue() + " ";
                            break;
                        case "valorProyectado":
                            strQuery += "AND u.valorProyectado=" + e.getValue() + " ";
                            break;
                        case "auAnexos4Id.id":
                            strQuery += "AND u.auAnexos4Id.id=" + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "u." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "u.id ASC";
            }
            List<CsCopagoModeradoraHistoricos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CsCopagoModeradoraHistoricos per : list) {
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
    public BigDecimal consultarTotalCopagoModeradora(ParamConsulta paramConsulta) throws Exception {
        BigDecimal valor = BigDecimal.ZERO;
        try {
            String strQuery = "SELECT COALESCE(SUM(u.valorProyectado),0) FROM CsCopagoModeradoraHistoricos u "
                    + "WHERE u.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND u.agno =" + paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND u.asegAfiliadosId.id =" + paramConsulta.getParametroConsulta2() + " "
                        + " AND u.valorProyectado IS NOT NULL "
                        + " AND u.valorProyectado > 0 ";
            }
            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += " AND u.moderadoraCopago= " + paramConsulta.getParametroConsulta3() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "agno":
                            strQuery += "AND u.agno =" + e.getValue() + " ";
                            break;
                        case "asegAfiliadosId.id":
                            strQuery += "AND u.asegAfiliadosId.id =" + e.getValue() + " ";
                            break;
                        case "valorProyectado":
                            strQuery += "AND u.valorProyectado=" + e.getValue() + " ";
                            break;
                        case "auAnexos4Id.id":
                            strQuery += "AND u.auAnexos4Id.id=" + e.getValue() + " ";
                            break;
                    }
                }
            }
            valor = BigDecimal.valueOf((long) getEntityManager().createQuery(strQuery)
                    .getSingleResult());
        } catch (NoResultException e) {
            valor = BigDecimal.ZERO;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return valor;
    }

    @Override
    public int insertar(CsCopagoModeradoraHistorico obj) throws Exception {
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
    public void actualizarValorProyectado(AuAnexo4 obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("UPDATE CsCopagoModeradoraHistoricos a SET  a.valorProyectado = 0  WHERE a.auAnexos4Id = ").append(obj.getId());
            Query query = session.createQuery(strQuery.toString());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public int valorTotalAfiliadoCopago(int idAfiliado,int anio) throws Exception {
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT SUM(a.valorProyectado) FROM CsCopagoModeradoraHistoricos a WHERE a.moderadoraCopago=TRUE AND a.asegAfiliadosId.id=:idAfiliado AND a.agno=:anio");
            Query query = getEntityManager().createQuery(strQuery.toString());
            query.setParameter("idAfiliado", idAfiliado);
            query.setParameter("anio", anio);
            return (int) (long) query.getSingleResult();
        } catch (NoResultException e) {
            Exception(CONSULTAR, e);
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return 0;
    }

    @Override
    public void actualizarValorCopago(AuAnexo4 obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("UPDATE CsCopagoModeradoraHistoricos a SET  a.valorModeradora = :valorCopago,a.valorProyectado = :valorCopago WHERE a.auAnexos4Id = ").append(obj.getId());
            Query query = session.createQuery(strQuery.toString());
            query.setParameter("valorCopago", obj.getValorCopago().intValue());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    private CsCopagoModeradoraHistoricos castNegocioEntidad(CsCopagoModeradoraHistorico negocio) {
        CsCopagoModeradoraHistoricos entidad = new CsCopagoModeradoraHistoricos();
        entidad.setIdAfiliado(negocio.getIdAfiliado());
        entidad.setAgno(negocio.getAgno());
        entidad.setMaeRegimenId(negocio.getMaeRegimenId());
        entidad.setMaeRegimenCodigo(negocio.getMaeRegimenCodigo());
        entidad.setMaeRegimenValor(negocio.getMaeRegimenValor());
        entidad.setModeradoraCopago(negocio.isModeradoraCopago());
        entidad.setCategoriaIbc(negocio.getCategoriaIbc());
        entidad.setMaeTipoAfiliadoId(negocio.getMaeTipoAfiliadoId());
        entidad.setMaeTipoAfiliadoCodigo(negocio.getMaeTipoAfiliadoCodigo());
        entidad.setMaeTipoAfiliadoValor(negocio.getMaeTipoAfiliadoValor());
        entidad.setMaeNivelSisbenId(negocio.getMaeNivelSisbenId());
        entidad.setMaeNivelSisbenCodigo(negocio.getMaeNivelSisbenCodigo());
        entidad.setMaeNivelSisbenValor(negocio.getMaeNivelSisbenValor());
        entidad.setValorModeradora(negocio.getValorModeradora());
        entidad.setPorcentajeCopago(negocio.getPorcentajeCopago());
        entidad.setValorProyectado(negocio.getValorProyectado());
        entidad.setValorEjecutado(negocio.getValorEjecutado());
        entidad.setAsegAfiliadosId(new AsegAfiliados(negocio.getAsegAfiliadosId().getId()));
        entidad.setAuAnexos4Id(new AuAnexos4(negocio.getAuAnexos4Id().getId()));
        if (negocio.getCmFacturasId() != null && negocio.getCmFacturasId().getId() != null) {
            entidad.setCmFacturasId(new CmFacturas(negocio.getCmFacturasId().getId()));
        }
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        return entidad;
    }

    public static CsCopagoModeradoraHistorico castEntidadNegocio(CsCopagoModeradoraHistoricos per) {
        CsCopagoModeradoraHistorico obj = new CsCopagoModeradoraHistorico();
        obj.setIdAfiliado(per.getIdAfiliado());
        obj.setAgno(per.getAgno());
        obj.setMaeRegimenId(per.getMaeRegimenId());
        obj.setMaeRegimenCodigo(per.getMaeRegimenCodigo());
        obj.setMaeRegimenValor(per.getMaeRegimenValor());
        obj.setModeradoraCopago(per.getModeradoraCopago());
        obj.setCategoriaIbc(per.getCategoriaIbc());
        obj.setMaeTipoAfiliadoId(per.getMaeTipoAfiliadoId());
        obj.setMaeTipoAfiliadoCodigo(per.getMaeTipoAfiliadoCodigo());
        obj.setMaeTipoAfiliadoValor(per.getMaeTipoAfiliadoValor());
        obj.setMaeNivelSisbenId(per.getMaeNivelSisbenId());
        obj.setMaeNivelSisbenCodigo(per.getMaeNivelSisbenCodigo());
        obj.setMaeNivelSisbenValor(per.getMaeNivelSisbenValor());
        obj.setValorModeradora(per.getValorModeradora());
        obj.setPorcentajeCopago(per.getPorcentajeCopago());
        obj.setValorProyectado(per.getValorProyectado());
        obj.setValorEjecutado(per.getValorEjecutado());
        obj.setAsegAfiliadosId(new AsegAfiliado(per.getAsegAfiliadosId().getId()));
        obj.setAuAnexos4Id(new AuAnexo4(per.getAuAnexos4Id().getId()));
        if (obj.getCmFacturasId() != null && per.getCmFacturasId().getId() != null) {
            obj.setCmFacturasId(new CmFactura(per.getCmFacturasId().getId()));
        }
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        return obj;
    }
}
