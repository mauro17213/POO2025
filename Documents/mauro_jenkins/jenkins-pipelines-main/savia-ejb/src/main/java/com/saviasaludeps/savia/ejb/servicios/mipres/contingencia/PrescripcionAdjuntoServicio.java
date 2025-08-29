/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.mipres.contingencia;

import com.saviasaludeps.savia.dominio.mipres.contingencia.MpcPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.contingencia.MpcPrescripcionAdjunto;
import com.saviasaludeps.savia.ejb.entidades.MpcPrescripcionAdjuntos;
import com.saviasaludeps.savia.ejb.entidades.MpcPrescripciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.mipres.contingencia.PrescripcionAdjuntoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author stive
 */
@Stateless
@Remote(PrescripcionAdjuntoRemoto.class)
public class PrescripcionAdjuntoServicio extends GenericoServicio implements PrescripcionAdjuntoRemoto {

    @Override
    public List<MpcPrescripcionAdjunto> consultarLista(int idPrescripcion) throws Exception {
        List<MpcPrescripcionAdjunto> listResult = new ArrayList();
        try {
            String strQuery = "SELECT a "
                    + "FROM MpcPrescripcionAdjuntos a "
                    + "WHERE a.mpcPrescripcionesId.id = :prescripcion_id ";
            strQuery += "ORDER BY a.tipo";
            List<MpcPrescripcionAdjuntos> list = getEntityManager().createQuery(strQuery)
                    .setParameter("prescripcion_id", idPrescripcion)
                    .getResultList();
            for (MpcPrescripcionAdjuntos per : list) {
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
    public int insertar(MpcPrescripcionAdjunto obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Este adjunto para dicha prescripción ya existe.");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public MpcPrescripcionAdjunto eliminar(int id) throws Exception {
        MpcPrescripcionAdjunto obj = null;
        try {
            MpcPrescripcionAdjuntos ent = getEntityManager().find(MpcPrescripcionAdjuntos.class, id);
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
    
    public MpcPrescripcionAdjuntos castNegocioEntidad(MpcPrescripcionAdjunto negocio) {
        MpcPrescripcionAdjuntos entidad = new MpcPrescripcionAdjuntos();
        entidad.setId(negocio.getId());
        entidad.setMpcPrescripcionesId(new MpcPrescripciones(negocio.getMpcPrescripcion().getId()));
        entidad.setTipo(negocio.getTipo());
        entidad.setNombreArchivo(negocio.getNombreArchivo());
        entidad.setRuta(negocio.getRuta());
        entidad.setArchivo(negocio.getArchivo());        
        entidad.setExiste(negocio.isExiste());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        return entidad;
    }
    
    public MpcPrescripcionAdjunto castEntidadNegocio(MpcPrescripcionAdjuntos entidad) {
        MpcPrescripcionAdjunto negocio = new MpcPrescripcionAdjunto();
        negocio.setId(entidad.getId());
        negocio.setMpcPrescripcionId(new MpcPrescripcion(entidad.getMpcPrescripcionesId().getId()));
        negocio.setTipo(entidad.getTipo());
        negocio.setNombreArchivo(entidad.getNombreArchivo());
        negocio.setRuta(entidad.getRuta());
        negocio.setArchivo(entidad.getArchivo());        
        negocio.setExiste(entidad.getExiste());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }
    
}
