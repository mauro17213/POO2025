/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.mipres.contingencia;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.mipres.contingencia.MpcPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.contingencia.MpcProgramacionEntrega;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.entidades.MpcPrescripciones;
import com.saviasaludeps.savia.ejb.entidades.MpcProgramacionEntregas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.mipres.contingencia.EntregaRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(EntregaRemoto.class)
public class EntregaServicio extends GenericoServicio implements EntregaRemoto {

    @Override
    public List<MpcProgramacionEntrega> consultarLista(int idPrescripcion) throws Exception {
        List<MpcProgramacionEntrega> listResult = new ArrayList();
        try {
            String strQuery = "SELECT e "
                    + "FROM MpcProgramacionEntregas e "
                    + "WHERE e.mpcPrescripcionesId.id = :prescripcion_id ";
            strQuery += "ORDER BY e.fechaEntrega, e.fechaHoraCrea";
            List<MpcProgramacionEntregas> list = getEntityManager().createQuery(strQuery)
                    .setParameter("prescripcion_id", idPrescripcion)
                    .getResultList();
            for (MpcProgramacionEntregas per : list) {
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
    public MpcProgramacionEntrega consultar(int id) throws Exception {
        MpcProgramacionEntrega objRes = null;
        try {
            objRes = castEntidadNegocio((MpcProgramacionEntregas) getEntityManager().find(MpcProgramacionEntregas.class, id));
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
    public int insertar(MpcProgramacionEntrega obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public MpcProgramacionEntrega eliminar(int id) throws Exception {
        MpcProgramacionEntrega obj = null;
        try {
            MpcProgramacionEntregas ent = getEntityManager().find(MpcProgramacionEntregas.class, id);
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

    public static MpcProgramacionEntrega castEntidadNegocio(MpcProgramacionEntregas per) {
        MpcProgramacionEntrega obj = new MpcProgramacionEntrega();
        if (per.getGnEmpresasId() != null) {
            obj.setEmpresa(new Empresa(per.getGnEmpresasId().getId()));
        }
        obj.setMpcPrescripcion(new MpcPrescripcion(per.getMpcPrescripcionesId().getId()));
        obj.setId(per.getId());
        obj.setFechaEntrega(per.getFechaEntrega());
        obj.setEstado(per.getEstado());
        obj.setEntregaTotal(per.getEntregaTotal());
        obj.setCantidad(per.getCantidad());
        obj.setNumeroEntrega(per.getNumeroEntrega());
        obj.setNumeroEntregaTotal(per.getNumeroEntregaTotal());
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static MpcProgramacionEntregas castNegocioEntidad(MpcProgramacionEntrega obj) {
        MpcProgramacionEntregas per = new MpcProgramacionEntregas();
        if (obj.getEmpresa() != null) {
            per.setGnEmpresasId(new GnEmpresas(obj.getEmpresa().getId()));
        }        
        per.setId(obj.getId());
        per.setMpcPrescripcionesId(new MpcPrescripciones(obj.getMpcPrescripcion().getId()));
        per.setId(obj.getId());
        per.setFechaEntrega(obj.getFechaEntrega());
        per.setEstado(obj.getEstado());
        per.setEntregaTotal(obj.isEntregaTotal());
        per.setCantidad(obj.getCantidad());
        per.setNumeroEntrega(obj.getNumeroEntrega());
        per.setNumeroEntregaTotal(obj.getNumeroEntregaTotal());
        //Auditoria
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }

}
