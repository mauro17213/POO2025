/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Tutela;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo3Tutelas;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos3;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3TutelaRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author Stiven Giraldo
 */
@Stateless
@Remote(AuAnexo3TutelaRemoto.class)
public class AuAnexo3TutelaServicio extends GenericoServicio implements AuAnexo3TutelaRemoto {    

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AuAnexo3Tutela> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AuAnexo3Tutela consultar(int id) throws Exception {
        AuAnexo3Tutela objRes = null;
        try {
            objRes = castEntidadNegocio((AuAnexo3Tutelas) getEntityManager().find(AuAnexo3Tutelas.class, id));
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
    public int insertar(AuAnexo3Tutela obj) throws Exception {
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
    public void actualizar(AuAnexo3Tutela obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AuAnexo3Tutela eliminar(int id) throws Exception {
        AuAnexo3Tutela obj = null;
        try {
            AuAnexo3Tutelas ent = getEntityManager().find(AuAnexo3Tutelas.class, id);
            if (ent != null){
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
    
    public static AuAnexo3Tutela castEntidadNegocio(AuAnexo3Tutelas entidad){
        AuAnexo3Tutela negocio = new AuAnexo3Tutela();
        negocio.setId(entidad.getId());
        negocio.setNumeroTutela(entidad.getNumeroTutela());
        negocio.setEstadoProceso(entidad.getEstadoProceso());
        negocio.setExoneracionCopago(entidad.getExoneracionCopago());
        if(entidad.getFechaFallo() != null){
            negocio.setFechaFallo(entidad.getFechaFallo());
        }
        if(entidad.getFechaNotificacion() != null){
            negocio.setFechaNotificacion(entidad.getFechaNotificacion());
        }
        if(entidad.getFechaVencimiento() != null){
            negocio.setFechaVencimiento(entidad.getFechaVencimiento());
        }
        if(entidad.getIntegralidad() != null){
            negocio.setIntegralidad(entidad.getIntegralidad());
        }
        if(entidad.getMedidaProvisional() != null){
            negocio.setMedidaProvisional(entidad.getMedidaProvisional());
        }
        if(entidad.getNumeroFallo() != null){
            negocio.setNumeroFallo(entidad.getNumeroFallo());
        }
        if(entidad.getRadicadoJuzgado() != null){
            negocio.setRadicadoJuzgado(entidad.getRadicadoJuzgado());
        }
        if(entidad.getFase() != null){
            negocio.setFase(entidad.getFase());
        }        
        negocio.setAuAnexo3Id(new AuAnexo3(entidad.getAuAnexos3Id().getId()));
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        if(entidad.getUsuarioModifica() != null){
            negocio.setUsuarioModifica(entidad.getUsuarioModifica());
            negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
            negocio.setTerminalModifica(entidad.getTerminalModifica());
        }
        negocio.setFase(entidad.getFase());
        return negocio;
    }
    
    public AuAnexo3Tutelas castNegocioEntidad(AuAnexo3Tutela negocio){
        AuAnexo3Tutelas entidad = new AuAnexo3Tutelas();
        entidad.setId(negocio.getId());
        entidad.setNumeroTutela(negocio.getNumeroTutela());
        entidad.setEstadoProceso(negocio.getEstadoProceso());
        entidad.setExoneracionCopago(negocio.isExoneracionCopago());
        if(negocio.getFechaFallo() != null){
            entidad.setFechaFallo(negocio.getFechaFallo());
        }
        if(negocio.getFechaNotificacion() != null){
            entidad.setFechaNotificacion(negocio.getFechaNotificacion());
        }
        if(negocio.getFechaVencimiento() != null){
            entidad.setFechaVencimiento(negocio.getFechaVencimiento());
        }
        if(negocio.getIntegralidad() != null){
            entidad.setIntegralidad(negocio.getIntegralidad());
        }
        if(negocio.getMedidaProvisional() != null){
            entidad.setMedidaProvisional(negocio.getMedidaProvisional());
        }
        if(negocio.getNumeroFallo() != null){
            entidad.setNumeroFallo(negocio.getNumeroFallo());
        }
        if(negocio.getRadicadoJuzgado() != null){
            entidad.setRadicadoJuzgado(negocio.getRadicadoJuzgado());
        }
        if(negocio.getFase() != null){
            entidad.setFase(negocio.getFase());
        }        
        entidad.setAuAnexos3Id(new AuAnexos3(negocio.getAuAnexo3Id().getId()));
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        if(negocio.getUsuarioModifica() != null){
            entidad.setUsuarioModifica(negocio.getUsuarioModifica());
            entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
            entidad.setTerminalModifica(negocio.getTerminalModifica());
        }
        entidad.setFase(negocio.getFase());
        return entidad;
    }
    
    static List<AuAnexo3Tutela> casteoListaEntidadNegocio(List<AuAnexo3Tutelas> auAnexo3TutelasList) {
        List<AuAnexo3Tutela> lista = new ArrayList();
        for(AuAnexo3Tutelas tutela : auAnexo3TutelasList){
            lista.add(castEntidadNegocio(tutela));
        }
        return lista;
    }
}
