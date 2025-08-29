/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.configuracionSistemaServicio;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.configuracionSistema.CsTopeCopago;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CsTopesCopagos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.configuracionSistema.CsTopeCopagoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author iavenegas
 */
@Stateless
@Remote(CsTopeCopagoRemoto.class)
public class CsTopeCopagoServicio extends GenericoServicio implements CsTopeCopagoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(u) FROM CsTopesCopagos u "
                    + "WHERE u.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {

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
    public List<CsTopeCopago> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CsTopeCopago> listResult = new ArrayList();
        try {
            String strQuery = "SELECT u FROM CsTopesCopagos u "
                    + "WHERE u.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {

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
            List<CsTopesCopagos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CsTopesCopagos per : list) {
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
    public CsTopeCopago consultar(int id) throws Exception {
        CsTopeCopago objRes = null;
        try {
            objRes = castEntidadNegocio((CsTopesCopagos) getEntityManager().find(CsTopesCopagos.class, id));
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
    public List<CsTopeCopago> consultarTopesByAnio(int anio) throws Exception {
        List<CsTopeCopago> listResult = new ArrayList();
        try {
            String strQuery = "SELECT u FROM CsTopesCopagos u "
                    + "WHERE u.agno=:agno ";

            List<CsTopesCopagos> list = getEntityManager().createQuery(strQuery)
                    .setParameter("agno", anio)
                    .getResultList();
            for (CsTopesCopagos per : list) {
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

    public static CsTopeCopago castEntidadNegocio(CsTopesCopagos per) {
        CsTopeCopago obj = new CsTopeCopago();
        obj.setId(per.getId());
        obj.setAgno(per.getAgno());
        obj.setMaeRegimenId(per.getMaeRegimenId());
        obj.setMaeRegimenCodigo(per.getMaeRegimenCodigo());
        obj.setMaeRegimenValor(per.getMaeRegimenValor());
        obj.setMaeTipoAfiliadoId(per.getMaeTipoAfiliadoId());
        obj.setMaeTipoAfiliadoCodigo(per.getMaeTipoAfiliadoCodigo());
        obj.setMaeTipoAfiliadoValor(per.getMaeTipoAfiliadoValor());
        obj.setMaeNivelSisbenId(per.getMaeNivelSisbenId());
        obj.setMaeNivelSisbenCodigo(per.getMaeNivelSisbenCodigo());
        obj.setMaeNivelSisbenValor(per.getMaeNivelSisbenValor());
        obj.setCategoriaIbc(per.getCategoriaIbc());
        obj.setPorcentaje(per.getPorcentaje());
        obj.setValorTopeEvento(per.getValorTopeEvento());
        obj.setValorTopeAgno(per.getValorTopeAgno());
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static CsTopesCopagos castNegocioEntidad(CsTopeCopago obj) {
        CsTopesCopagos ent = new CsTopesCopagos();
        ent.setId(obj.getId());
        ent.setAgno(obj.getAgno());
        ent.setMaeRegimenId(obj.getMaeRegimenId());
        ent.setMaeRegimenCodigo(obj.getMaeRegimenCodigo());
        ent.setMaeRegimenValor(obj.getMaeRegimenValor());
        ent.setMaeTipoAfiliadoId(obj.getMaeTipoAfiliadoId());
        ent.setMaeTipoAfiliadoCodigo(obj.getMaeTipoAfiliadoCodigo());
        ent.setMaeTipoAfiliadoValor(obj.getMaeTipoAfiliadoValor());
        ent.setMaeNivelSisbenId(obj.getMaeNivelSisbenId());
        ent.setMaeNivelSisbenCodigo(obj.getMaeNivelSisbenCodigo());
        ent.setMaeNivelSisbenValor(obj.getMaeNivelSisbenValor());
        ent.setCategoriaIbc(obj.getCategoriaIbc());
        ent.setPorcentaje(obj.getPorcentaje());
        ent.setValorTopeEvento(obj.getValorTopeEvento());
        ent.setValorTopeAgno(obj.getValorTopeAgno());
        //Auditoría
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        return ent;
    }

    @Override
    public CsTopeCopago consultar(String agno, String maeRegimenCodigo, String maeNivelSisbenCodigo, String maeTipoAfiliadoCodigo, String categoriaIbc) throws java.lang.Exception {
        CsTopeCopago objResult = null;
        try {
            String strQuery = "SELECT u FROM CsTopesCopagos u "
                    + "WHERE u.agno = '" + agno + "' "
                    + "AND u.maeRegimenCodigo = '" + maeRegimenCodigo + "' ";
                    if (maeRegimenCodigo != null && maeRegimenCodigo.equals(AsegAfiliado.REGIMEN_SUBSIDIADO)) {
                        strQuery += "AND u.maeNivelSisbenCodigo = '" + maeNivelSisbenCodigo + "' ";
                    } else {
                        // asumimos que es contributivo
                        strQuery += "AND u.maeTipoAfiliadoCodigo = '" + maeTipoAfiliadoCodigo + "' ";
                        strQuery += "AND u.categoriaIbc = '" + categoriaIbc + "' ";
                    }

            CsTopesCopagos cpago = (CsTopesCopagos) getEntityManager().createQuery(strQuery)
                    .getSingleResult();
                objResult = castEntidadNegocio(cpago);
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
