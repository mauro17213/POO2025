/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.tutela;

import com.saviasaludeps.savia.dominio.tutela.TuMemorialFirma;
import com.saviasaludeps.savia.dominio.tutela.TuMemorialPersona;
import com.saviasaludeps.savia.ejb.entidades.TuMemorialFirmas;
import com.saviasaludeps.savia.ejb.entidades.TuMemorialPersonas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.tutela.TuMemorialFirmaRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author pavacca
 */
@Stateless
@Remote(TuMemorialFirmaRemoto.class)
public class TuMemorialFirmaServicio extends GenericoServicio implements TuMemorialFirmaRemoto{
    
    
    @Override
    public TuMemorialFirma consultar(int id) throws Exception {
        TuMemorialFirma objRes = null;
        try {
            objRes = castEntidadNegocio((TuMemorialFirmas) getEntityManager().find(TuMemorialFirmas.class, id));
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
    public int insertar(TuMemorialFirma obj) throws Exception {
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
    public TuMemorialFirma eliminar(int id) throws Exception {
        TuMemorialFirma obj = null;
        try {
            TuMemorialFirmas ent = getEntityManager().find(TuMemorialFirmas.class, id);
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
    
    @Override
    public TuMemorialFirma consultarMemorialFirmaPorPersonal(Integer id) throws Exception {
        TuMemorialFirma listResult = new TuMemorialFirma();
        try {
            String strQuery = "SELECT j FROM TuMemorialFirmas j "
                    + "INNER JOIN TuMemorialPersonas p ON j.tuMemorialPersonaId = p.id "
                    + "WHERE p.id = " + id + " ";
            
            TuMemorialFirmas list = (TuMemorialFirmas) getEntityManager().createQuery(strQuery).getSingleResult();
            if(list != null){
                listResult = castEntidadNegocio(list);
            }
        } catch (NoResultException e) {
            listResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
    public static List<TuMemorialFirma> castEntidadNegocio(List<TuMemorialFirmas> serviciosNegocio) {
        List<TuMemorialFirma> listaServicios = new ArrayList<>();
        for(TuMemorialFirmas servicioNegocio:serviciosNegocio){
            listaServicios.add(castEntidadNegocio(servicioNegocio));
        }
        return listaServicios;
    }
    
    public static TuMemorialFirma castEntidadNegocio(TuMemorialFirmas per) {
        TuMemorialFirma obj = new TuMemorialFirma();
        obj.setId(per.getId());
        obj.setNombre(per.getNombre());
        obj.setFirma(per.getFirma());
        if(per.getTuMemorialPersonaId() != null){
            obj.setTuMemorialPersonaId(new TuMemorialPersona(per.getTuMemorialPersonaId().getId()));
        }
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }
    
    public static TuMemorialFirmas castNegocioEntidad(TuMemorialFirma obj) {
        TuMemorialFirmas per = new TuMemorialFirmas();
        per.setNombre(obj.getNombre());
        per.setFirma(obj.getFirma());
        if(obj.getTuMemorialPersonaId() != null){
            per.setTuMemorialPersonaId(new TuMemorialPersonas(obj.getTuMemorialPersonaId().getId()));
        }
        //Auditoría
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        return per;
    }
}
