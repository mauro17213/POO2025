/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.configuracionSistemaServicio;

import com.saviasaludeps.savia.dominio.configuracionSistema.CsTopeModeradora;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CsTopesModeradoras;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.configuracionSistema.CsTopeModeradoraRemoto;
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
@Remote(CsTopeModeradoraRemoto.class)
public class CsTopeModeradoraServicio extends GenericoServicio implements CsTopeModeradoraRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(u) FROM CsTopesModeradoras u "
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
    public List<CsTopeModeradora> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CsTopeModeradora> listResult = new ArrayList();
        try {
            String strQuery = "SELECT u FROM CsTopesModeradoras u "
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
            List<CsTopesModeradoras> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CsTopesModeradoras per : list) {
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
    public CsTopeModeradora consultar(int id) throws Exception {
        CsTopeModeradora objRes = null;
        try {
            objRes = castEntidadNegocio((CsTopesModeradoras) getEntityManager().find(CsTopesModeradoras.class, id));
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
    public List<CsTopeModeradora> consultarTopesByAnio(int anio) throws Exception {
        List<CsTopeModeradora> listResult = new ArrayList();
        try {
            String strQuery = "SELECT u FROM CsTopesModeradoras u "
                    + "WHERE u.agno=:agno ";

            List<CsTopesModeradoras> list = getEntityManager().createQuery(strQuery)
                    .setParameter("agno", anio)
                    .getResultList();
            for (CsTopesModeradoras per : list) {
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

    public static CsTopeModeradora castEntidadNegocio(CsTopesModeradoras per) {
        CsTopeModeradora obj = new CsTopeModeradora();
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
        obj.setValor(per.getValor());
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static CsTopesModeradoras castNegocioEntidad(CsTopeModeradora obj) {
        CsTopesModeradoras ent = new CsTopesModeradoras();
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
        ent.setValor(obj.getValor());
        //Auditoría
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        return ent;
    }

}
