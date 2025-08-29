/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.tutela;

import com.saviasaludeps.savia.dominio.tutela.TuAdjunto;
import com.saviasaludeps.savia.dominio.tutela.TuSeguimiento;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaEstado;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaItem;
import com.saviasaludeps.savia.ejb.entidades.TuAdjuntos;
import com.saviasaludeps.savia.ejb.entidades.TuSeguimientos;
import com.saviasaludeps.savia.ejb.entidades.TuTutelaEstados;
import com.saviasaludeps.savia.ejb.entidades.TuTutelaItems;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.tutela.TuAdjuntoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author pavacca
 */
@Stateless
@Remote(TuAdjuntoRemoto.class)
@Local(TuAdjuntoLocal.class)
public class TuAdjuntoServicio extends GenericoServicio implements TuAdjuntoLocal, TuAdjuntoRemoto{
    
    @Override
    public int insertar(TuAdjunto obj) throws Exception {
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
    public TuAdjunto eliminar(int id) throws Exception {
        TuAdjunto obj = null;
        try {
            TuAdjuntos ent = getEntityManager().find(TuAdjuntos.class, id);
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
    
    public static TuAdjuntos castNegocioEntidad(TuAdjunto obj) {
        TuAdjuntos per = new TuAdjuntos();
        per.setId(obj.getId());
        if(obj.getTuTutelaEstadosId() != null){
            per.setTuTutelaEstadosId(new TuTutelaEstados(obj.getTuTutelaEstadosId().getId()));
        }
        per.setMaeTipoAnexoId(obj.getMaeTipoAnexoId());
        per.setMaeTipoAnexoCodigo(obj.getMaeTipoAnexoCodigo());
        per.setMaeTipoAnexoValor(obj.getMaeTipoAnexoValor());
        if(obj.getTuTutelaItemsId() != null){
            per.setTuTutelaItemsId(new TuTutelaItems(obj.getTuTutelaItemsId().getId()));
        }
        if(obj.getTuSeguimientosId() != null){
            per.setTuSeguimientosId(new TuSeguimientos(obj.getTuSeguimientosId().getId()));
        }
        per.setNombreArchivo(obj.getNombreArchivo());
        per.setArchivo(obj.getArchivo());
        per.setRuta(obj.getRuta());
        per.setObservacion(obj.getObservacion());
        //Auditoría
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }
    
    public static TuAdjunto castEntidadNegocio(TuAdjuntos adjuntoNegocio){
        TuAdjunto adjunto = new TuAdjunto();
        TuTutelaEstado estado = new TuTutelaEstado();
        TuTutelaItem servicio = new TuTutelaItem();
        TuSeguimiento seguimiento = new TuSeguimiento();  
     
        if (adjuntoNegocio.getTuTutelaEstadosId() != null) {
            estado.setId(adjuntoNegocio.getTuTutelaEstadosId().getId());
        }

        if (adjuntoNegocio.getTuTutelaItemsId() != null) {
            servicio.setId(adjuntoNegocio.getTuTutelaItemsId().getId());
        }

        if (adjuntoNegocio.getTuSeguimientosId() != null) {
            seguimiento.setId(adjuntoNegocio.getTuSeguimientosId().getId());
        }
        adjunto.setId(adjuntoNegocio.getId());
        adjunto.setTuTutelaEstadosId(estado);
        adjunto.setTuTutelaItemsId(servicio);
        adjunto.setTuSeguimientosId(seguimiento);
        adjunto.setRuta(adjuntoNegocio.getRuta());
        adjunto.setNombreArchivo(adjuntoNegocio.getNombreArchivo());
        adjunto.setUsuarioCrea(adjuntoNegocio.getUsuarioCrea());
        adjunto.setTerminalCrea(adjuntoNegocio.getTerminalCrea());
        adjunto.setFechaHoraCrea(adjuntoNegocio.getFechaHoraCrea());
        return adjunto;  
    }
    
    public static List<TuAdjunto> castEntidadNegocio(List<TuAdjuntos>  adjuntosNegocio){
        List<TuAdjunto> listaAdjuntos = new ArrayList();
        for(TuAdjuntos adjuntoNegocio : adjuntosNegocio){
            listaAdjuntos.add(castEntidadNegocio(adjuntoNegocio));
        }
        /*adjuntosNegocio.forEach(adjuntoNegocio -> {
            listaAdjuntos.add(castEntidadNegocio(adjuntoNegocio));
        });*/
        return listaAdjuntos;  
    }

    @Override
    public List<TuAdjunto>  consultarPorTutela(int idTutela) throws java.lang.Exception {
        List<TuAdjunto> listResult = new ArrayList();
        try {
            String strQuery = "FROM TuAdjuntos p "
                    + "WHERE ";
            strQuery += " p.tuTutelaEstadosId.tuTutelasId.id = " + idTutela + " ";

            List<TuAdjuntos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (TuAdjuntos cont : list) {
                listResult.add(castEntidadNegocio(cont));
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
    
}
