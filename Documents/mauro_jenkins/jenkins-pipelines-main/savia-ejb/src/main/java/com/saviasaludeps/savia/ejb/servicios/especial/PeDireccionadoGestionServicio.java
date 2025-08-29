/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.saviasaludeps.savia.dominio.especial.PeDireccionado;
import com.saviasaludeps.savia.dominio.especial.PeDireccionadoGestion;
import com.saviasaludeps.savia.ejb.entidades.PeDireccionadoGestiones;
import com.saviasaludeps.savia.ejb.entidades.PeDireccionados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.especial.PeDireccionadoGestionRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author idbohorquez
 */
@Stateless
@Remote(PeDireccionadoGestionRemoto.class)
@Local(PeDireccionadoGestionLocal.class)
public class PeDireccionadoGestionServicio extends GenericoServicio implements PeDireccionadoGestionLocal, PeDireccionadoGestionRemoto {
    
    /**
     * Consulta listado de direccionado gestiones mediante el id de direccionado
     * @author idbohorquez
     * @creado 31/08/2022
     * @param idDireccionado
     * @return List<PeDireccionadoGestion>
     * @throws Exception 
     */
    @Override
    public List<PeDireccionadoGestion> listaGestionesDireccion(Integer idDireccionado) throws java.lang.Exception {
        List<PeDireccionadoGestion> lista = new ArrayList<>();
        try {
            String strQuery = "SELECT p FROM PeDireccionadoGestiones p "
                    + "WHERE p.peDireccionadosId.id = :id "
                    + "ORDER BY p.id ";
            
            List<PeDireccionadoGestiones> list = getEntityManager().createQuery(strQuery)
                    .setParameter("id", idDireccionado)
                    .getResultList();
            for (PeDireccionadoGestiones item : list) {
                lista.add(castEntidadNegocio(item));
            }
        } catch (NoResultException e) {
            lista = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return lista;
    }
    
    /**
     * Funcion que permite guardar nuevo registro de DireccionGestiones
     * @author idbohorquez
     * @creado 01/09/2022
     * @param PeDireccionadoGestion
     * @return int
     * @throws Exception 
     */
    @Override
    public int insertar(PeDireccionadoGestion obj) throws Exception {
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
     * Funcion que obtiene los elementos de entidad y los pasa a objeto de negocio
     * @author idbohorquez
     * @creado 31/08/2022
     * @param ent 
     * @return PeDireccionadoGestion
     */
    private static PeDireccionadoGestion castEntidadNegocio(PeDireccionadoGestiones ent) {
        PeDireccionadoGestion obj = new PeDireccionadoGestion();
        
        obj.setId(ent.getId());
        obj.setMaeTipoId(ent.getMaeTipoId());
        obj.setMaeTipoCodigo(ent.getMaeTipoCodigo());
        obj.setMaeTipoValor(ent.getMaeTipoValor());
        obj.setDescripcion(ent.getDescripcion());
        obj.setPeDireccionadosId(new PeDireccionado(ent.getPeDireccionadosId().getId()));
        
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        
        return obj;
    }
    
    /**
     * Funcion que permite pasar el objeto a entidad
     * @author idbohorquez
     * @creado 01/09/2022
     * @param PeDireccionadoGestion
     * @return PeDireccionadoGestiones
     */
    private static PeDireccionadoGestiones castNegocioEntidad(PeDireccionadoGestion obj) {
        PeDireccionadoGestiones ent = new PeDireccionadoGestiones();
        
        ent.setMaeTipoId(obj.getMaeTipoId());
        ent.setMaeTipoCodigo(obj.getMaeTipoCodigo());
        ent.setMaeTipoValor(obj.getMaeTipoValor());
        ent.setDescripcion(obj.getDescripcion());
        ent.setPeDireccionadosId(new PeDireccionados(obj.getPeDireccionadosId().getId()));
        
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        
        return ent;
    }
    
}
