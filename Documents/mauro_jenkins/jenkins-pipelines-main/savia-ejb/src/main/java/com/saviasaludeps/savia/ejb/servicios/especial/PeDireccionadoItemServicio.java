/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.especial.PeDireccionado;
import com.saviasaludeps.savia.dominio.especial.PeDireccionadoItem;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo3Items;
import com.saviasaludeps.savia.ejb.entidades.PeDireccionadoItems;
import com.saviasaludeps.savia.ejb.entidades.PeDireccionados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.especial.PeDireccionadoItemRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author idbohorquez
 */
@Stateless
@Remote(PeDireccionadoItemRemoto.class)
@Local(PeDireccionadoItemLocal.class)
public class PeDireccionadoItemServicio extends GenericoServicio implements PeDireccionadoItemLocal, PeDireccionadoItemRemoto {

    @Override
    public int insertar(PeDireccionadoItem obj) throws java.lang.Exception {
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
    
    
    /**
     * Funcion que permite guardar fecha de atención en un registro de DireccionItems
     * @author idbohorquez
     * @creado 27/09/2022
     * @param PeDireccionadoItem
     * @throws Exception 
     */
    @Override
    public void establecerFechaGestion(PeDireccionadoItem obj) throws java.lang.Exception {
        try {
            StringBuilder sql = new StringBuilder(" UPDATE PeDireccionadoItems SET ");
            sql.append(" estado = :estado, fechaPrestacion = :fechaPrestacion , observacion = :observacion, ");
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append(" WHERE id = :id");
            
            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("fechaPrestacion", obj.getFechaPrestacion());
            query.setParameter("observacion", obj.getObservacion());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public List<PeDireccionadoItem> getDireccionadoItems(Integer idDireccion) throws java.lang.Exception {
        List<PeDireccionadoItem> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT p FROM PeDireccionadoItems p WHERE p.peDireccionadosId.id = ").append(idDireccion);
            List<PeDireccionadoItems> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (PeDireccionadoItems per : list) {
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
    
    public static PeDireccionadoItems castNegocioEntidad(PeDireccionadoItem obj) {
        PeDireccionadoItems ent = new PeDireccionadoItems();        
        ent.setAuAnexo3ItemsId(new AuAnexo3Items(obj.getAuAnexo3ItemsId().getId()));
        ent.setPeDireccionadosId(new PeDireccionados(obj.getPeDireccionadosId().getId()));
        ent.setEstado(obj.getEstado());
        ent.setFechaPrestacion(obj.getFechaPrestacion());
        ent.setObservacion(obj.getObservacion());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());              
        return ent;
    }
    
    public static PeDireccionadoItem castEntidadNegocio(PeDireccionadoItems ent) {
        PeDireccionadoItem obj = new PeDireccionadoItem();        
        obj.setAuAnexo3ItemsId(new AuAnexo3Item(ent.getAuAnexo3ItemsId().getId()));
        obj.setPeDireccionadosId(new PeDireccionado(ent.getPeDireccionadosId().getId()));
        obj.setEstado(ent.getEstado());
        obj.setFechaPrestacion(ent.getFechaPrestacion());
        obj.setObservacion(ent.getObservacion());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());      
        AuAnexo3Item item = new AuAnexo3Item();
        item.setMaTecnologiaId(ent.getAuAnexo3ItemsId().getMaTecnologiaId());
        item.setMaTecnologiaCodigo(ent.getAuAnexo3ItemsId().getMaTecnologiaCodigo());
        item.setMaTecnologiaValor(ent.getAuAnexo3ItemsId().getMaTecnologiaValor());
        item.setCantidadSolicitada(ent.getAuAnexo3ItemsId().getCantidadSolicitada());      
        
        obj.setAuAnexo3ItemsId(item);
        return obj;
    }
   

}
