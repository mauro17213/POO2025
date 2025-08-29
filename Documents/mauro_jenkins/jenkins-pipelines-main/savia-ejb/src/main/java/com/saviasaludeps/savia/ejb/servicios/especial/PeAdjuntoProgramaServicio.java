/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.saviasaludeps.savia.dominio.especial.PeAdjunto;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.especial.PeGestion;
import com.saviasaludeps.savia.ejb.entidades.PeAdjuntos;
import com.saviasaludeps.savia.ejb.entidades.PeAfiliadosProgramas;
import com.saviasaludeps.savia.ejb.entidades.PeGestiones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.especial.PeAdjuntoProgramaRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(PeAdjuntoProgramaRemoto.class)
@Local(PeAdjuntoProgramaLocal.class)
public class PeAdjuntoProgramaServicio extends GenericoServicio implements PeAdjuntoProgramaRemoto,PeAdjuntoProgramaLocal {

    @Override
    public int insertar(PeAdjunto obj) throws java.lang.Exception {
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
    
    public List<PeAdjunto> consultarPorIdAfiliadoPrograma(int idAfiliadosProg) throws Exception {
        List<PeAdjunto> listResult = new ArrayList<>();
        try {
            String strQuery = "SELECT p FROM PeAdjuntos p "
                    + "WHERE p.peAfiliadosId.id = :id "
                    + "ORDER BY p.id desc ";
            
            List<PeAdjuntos> list = getEntityManager().createQuery(strQuery)
                    .setParameter("id", idAfiliadosProg)
                    .getResultList();
            for (PeAdjuntos per : list) {
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
    
    public static PeAdjuntos castNegocioEntidad(PeAdjunto obj) {
        PeAdjuntos ent = new PeAdjuntos();
        
        ent.setArchivo(obj.getArchivo());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setId(obj.getId());
        ent.setMaeTipoArchivoId(obj.getMaeTipoArchivoId());
        ent.setMaeTipoArchivoCodigo(obj.getMaeTipoArchivoCodigo());
        ent.setMaeTipoArchivoValor(obj.getMaeTipoArchivoValor());
        ent.setNombre(obj.getNombre());
        ent.setObservacion(obj.getObservacion());
        if (obj.getPeAfiliadosId() != null) {
            ent.setPeAfiliadosId(new PeAfiliadosProgramas(obj.getPeAfiliadosId().getId()));
        }
        if (obj.getPeGestionesId() != null) {
            ent.setPeGestionesId(new PeGestiones(obj.getPeGestionesId().getId()));
        }
        ent.setRuta(obj.getRuta());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        
        return ent;
    }
    
    public static PeAdjunto castEntidadNegocio(PeAdjuntos ent) {
        PeAdjunto obj = new PeAdjunto();
        obj.setArchivo(ent.getArchivo());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setId(ent.getId());
        obj.setMaeTipoArchivoId(ent.getMaeTipoArchivoId());
        obj.setMaeTipoArchivoCodigo(ent.getMaeTipoArchivoCodigo());
        obj.setMaeTipoArchivoValor(ent.getMaeTipoArchivoValor());
        obj.setNombre(ent.getNombre());
        obj.setObservacion(ent.getObservacion());
        if (ent.getPeAfiliadosId().getId() != null) {
            obj.setPeAfiliadosId(new PeAfiliadosPrograma(ent.getPeAfiliadosId().getId()));
        }
        
        if (ent.getPeGestionesId() != null) {
            obj.setPeGestionesId(new PeGestion(ent.getPeGestionesId().getId()));
        }
        obj.setRuta(ent.getRuta());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        return obj;
    }
}
