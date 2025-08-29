/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacionAdjunto;
import com.saviasaludeps.savia.ejb.entidades.AuCotizacionAdjuntos;
import com.saviasaludeps.savia.ejb.entidades.AuCotizaciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuCotizacionAdjuntoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author StivenGV
 */
@Stateless
@Remote(AuCotizacionAdjuntoRemoto.class)
public class AuCotizacionAdjuntoServicio extends GenericoServicio implements AuCotizacionAdjuntoRemoto {

    @Override
    public int insertar(AuCotizacionAdjunto obj) throws Exception {
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
    public List<AuCotizacionAdjunto> listarAdjuntosByIdCotizacion(int idCotizacion) throws Exception {
        List<AuCotizacionAdjunto> adjuntos = new ArrayList();
        try {
            String strQuery = "FROM AuCotizacionAdjuntos p "
                    + "WHERE p.auCotizacionesId.id =" + idCotizacion
                    + " ORDER BY p.id DESC";

            List<AuCotizacionAdjuntos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuCotizacionAdjuntos adjunto : list) {
                adjuntos.add(castEntidadNegocio(adjunto));
            }
        } catch (NoResultException e) {
            adjuntos = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return adjuntos;
    }

    @Override
    public AuCotizacionAdjunto eliminar(int id) throws Exception {
        AuCotizacionAdjunto obj = null;
        try {
            AuCotizacionAdjuntos ent = getEntityManager().find(AuCotizacionAdjuntos.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            Exception(ELIMINAR, e);
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }
    
    private AuCotizacionAdjuntos castNegocioEntidad(AuCotizacionAdjunto negocio){
        AuCotizacionAdjuntos entidad = new AuCotizacionAdjuntos();
        entidad.setAuCotizacionesId(new AuCotizaciones(negocio.getAuCotizacionId().getId()));
        entidad.setArchivo(negocio.getArchivo());
        entidad.setNombreArchivo(negocio.getNombreArchivo());
        entidad.setRuta(negocio.getRuta());
        entidad.setMaeTipoArchivoId(negocio.getMaeTipoArchivoId());
        entidad.setMaeTipoArchivoCodigo(negocio.getMaeTipoArchivoCodigo());
        entidad.setMaeTipoArchivoValor(negocio.getMaeTipoArchivoValor());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        return entidad;
    }
    
    private AuCotizacionAdjunto castEntidadNegocio(AuCotizacionAdjuntos entidad){
        AuCotizacionAdjunto negocio = new AuCotizacionAdjunto();
        negocio.setId(entidad.getId());
        negocio.setAuCotizacionId(new AuCotizacion(entidad.getAuCotizacionesId().getId()));
        negocio.setArchivo(entidad.getArchivo());
        negocio.setNombreArchivo(entidad.getNombreArchivo());
        negocio.setRuta(entidad.getRuta());
        negocio.setMaeTipoArchivoId(entidad.getMaeTipoArchivoId());
        negocio.setMaeTipoArchivoCodigo(entidad.getMaeTipoArchivoCodigo());
        negocio.setMaeTipoArchivoValor(entidad.getMaeTipoArchivoValor());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        return negocio;
    }
}
