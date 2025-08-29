/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.crue;

import com.saviasaludeps.savia.dominio.crue.AuAnexo2;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Adjunto;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo2Adjuntos;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos2;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2AdjuntoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author AlexanderDiaz
 */
@Stateless
@Remote(AuAnexo2AdjuntoRemoto.class)
@Local(AuAnexo2AdjuntoLocal.class)
public class AuAnexo2AdjuntoServicio extends GenericoServicio implements AuAnexo2AdjuntoRemoto, AuAnexo2AdjuntoLocal {

    @Override
    public int insertar(AuAnexo2Adjunto obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
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
    public List<AuAnexo2Adjunto> consultarPorAuAnexo2(int idAuAnexo2) throws Exception {
        List<AuAnexo2Adjunto> listaResultado = new ArrayList<>();

        try {
            String strQuery = "SELECT r FROM AuAnexo2Adjuntos r "
                    + "WHERE R.auAnexos2Id.id = :id "
                    + "ORDER BY r.fechaHoraCrea desc ";
            List<AuAnexo2Adjuntos> list = getEntityManager().createQuery(strQuery)
                    .setParameter("id", idAuAnexo2)
                    .getResultList();

            for (AuAnexo2Adjuntos auAnexo2Adjuntos : list) {
                listaResultado.add(castEntidadNegocio(auAnexo2Adjuntos));
            }
        } catch (NoResultException e) {
            listaResultado = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultado;
    }

    public static AuAnexo2Adjuntos castNegocioEntidad(AuAnexo2Adjunto obj) {
        AuAnexo2Adjuntos ent = new AuAnexo2Adjuntos();
        ent.setArchivo(obj.getArchivo());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setId(obj.getId());
        ent.setMaeTipoArchivoCodigo(obj.getMaeTipoArchivoCodigo());
        ent.setMaeTipoArchivoId(obj.getMaeTipoArchivoId());
        ent.setMaeTipoArchivoValor(obj.getMaeTipoArchivoValor());
        ent.setNombreArchivo(obj.getNombreArchivo());
        if (obj.getAuAnexo2() != null && obj.getAuAnexo2().getId() != null) {
            ent.setAuAnexos2Id(new AuAnexos2(obj.getAuAnexo2().getId()));
        }
        ent.setRuta(obj.getRuta());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        return ent;
    }

    public static AuAnexo2Adjunto castEntidadNegocio(AuAnexo2Adjuntos ent) {
        AuAnexo2Adjunto obj = new AuAnexo2Adjunto();
        obj.setArchivo(ent.getArchivo());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setId(ent.getId());
        obj.setMaeTipoArchivoCodigo(ent.getMaeTipoArchivoCodigo());
        obj.setMaeTipoArchivoId(ent.getMaeTipoArchivoId());
        obj.setMaeTipoArchivoValor(ent.getMaeTipoArchivoValor());
        obj.setNombreArchivo(ent.getNombreArchivo());
        if (ent.getAuAnexos2Id() != null) {
            obj.setAuAnexo2(new AuAnexo2(ent.getAuAnexos2Id().getId()));
        }
        obj.setRuta(ent.getRuta());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        return obj;
    }
}
