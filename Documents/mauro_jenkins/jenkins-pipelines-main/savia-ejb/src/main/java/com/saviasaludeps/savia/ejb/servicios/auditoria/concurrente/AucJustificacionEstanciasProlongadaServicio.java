/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucJustificacionEstanciasProlongada;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizaciones;
import com.saviasaludeps.savia.ejb.entidades.AucJustificacionEstanciasProlongadas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucJustificacionEstanciasProlongadaRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author pavacca
 */
@Stateless
@Remote(AucJustificacionEstanciasProlongadaRemoto.class)
public class AucJustificacionEstanciasProlongadaServicio extends GenericoServicio implements AucJustificacionEstanciasProlongadaRemoto{
    @Override
    public AucJustificacionEstanciasProlongada consultar(int id) throws Exception {
        AucJustificacionEstanciasProlongada objRes = null;
        try {
            objRes = castEntidadNegocio((AucJustificacionEstanciasProlongadas) getEntityManager().find(AucJustificacionEstanciasProlongadas.class, id));
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
    public int insertar(AucJustificacionEstanciasProlongada obj) throws Exception {
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
    public void actualizar(AucJustificacionEstanciasProlongada obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucJustificacionEstanciasProlongadas a SET ";
            strQuery += "a.maeCausaEstanciaProlongadaId = :maeCausaEstanciaId ,";
            strQuery += "a.maeCausaEstanciaProlongadaCodigo = :maeCausaEstanciaCodigo ,";
            strQuery += "a.maeCausaEstanciaProlongadaValor = :maeCausaEstanciaValor ,";
            strQuery += "a.maePropuestaIntervencionId = :maePropuestaIntervencionId ,";
            strQuery += "a.maePropuestaIntervencionCodigo = :maePropuestaIntervencionCodigo ,";
            strQuery += "a.maePropuestaIntervencionValor = :maePropuestaIntervencionValor ,";
            strQuery += "a.resumenClinico = :resumenClinico ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("maeCausaEstanciaId", obj.getMaeCausaEstanciaProlongadaId());
            query.setParameter("maeCausaEstanciaCodigo", obj.getMaeCausaEstanciaProlongadaCodigo());
            query.setParameter("maeCausaEstanciaValor", obj.getMaeCausaEstanciaProlongadaValor());
            query.setParameter("maePropuestaIntervencionId", obj.getMaePropuestaIntervensionId());
            query.setParameter("maePropuestaIntervencionCodigo", obj.getMaePropuestaIntervensionCodigo());
            query.setParameter("maePropuestaIntervencionValor", obj.getMaePropuestaIntervensionValor());
            query.setParameter("resumenClinico", obj.getResumenClinico());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public AucJustificacionEstanciasProlongada eliminar(int id) throws Exception {
        AucJustificacionEstanciasProlongada obj = null;
        try {
            AucJustificacionEstanciasProlongadas ent = getEntityManager().find(AucJustificacionEstanciasProlongadas.class, id);
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
    
    @Override
    public List<AucJustificacionEstanciasProlongada> consultarPorIdHospitalizacion(int idHospitalizacion) throws Exception {
        List<AucJustificacionEstanciasProlongada> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AucJustificacionEstanciasProlongadas p "
                    + "WHERE p.aucHospitalizacionesId.id = " + idHospitalizacion + " "
                    + "ORDER BY p.fechaHoraCrea DESC";

            List<AucJustificacionEstanciasProlongadas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AucJustificacionEstanciasProlongadas estancia : list) {
                listaResultados.add(castEntidadNegocio(estancia));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }
    
    private AucJustificacionEstanciasProlongada castEntidadNegocio(AucJustificacionEstanciasProlongadas entidad) {
        AucJustificacionEstanciasProlongada negocio = new AucJustificacionEstanciasProlongada();
        negocio.setId(entidad.getId());
        if(entidad.getAucHospitalizacionesId() != null){
            negocio.setAucHospitalizacionId(new AucHospitalizacion(entidad.getAucHospitalizacionesId().getId()));
        }
        negocio.setMaeCausaEstanciaProlongadaId(entidad.getMaeCausaEstanciaProlongadaId());
        negocio.setMaeCausaEstanciaProlongadaCodigo(entidad.getMaeCausaEstanciaProlongadaCodigo());
        negocio.setMaeCausaEstanciaProlongadaValor(entidad.getMaeCausaEstanciaProlongadaValor());
        negocio.setMaePropuestaIntervensionId(entidad.getMaePropuestaIntervencionId());
        negocio.setMaePropuestaIntervensionCodigo(entidad.getMaePropuestaIntervencionCodigo());
        negocio.setMaePropuestaIntervensionValor(entidad.getMaePropuestaIntervencionValor());
        negocio.setResumenClinico(entidad.getResumenClinico());
        
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }
    
    private AucJustificacionEstanciasProlongadas castNegocioEntidad(AucJustificacionEstanciasProlongada negocio) {
        AucJustificacionEstanciasProlongadas entidad = new AucJustificacionEstanciasProlongadas();
        negocio.setId(entidad.getId());
        if(negocio.getAucHospitalizacionId() != null){
            entidad.setAucHospitalizacionesId(new AucHospitalizaciones(negocio.getAucHospitalizacionId().getId()));
        }
        entidad.setMaeCausaEstanciaProlongadaId(negocio.getMaeCausaEstanciaProlongadaId());
        entidad.setMaeCausaEstanciaProlongadaCodigo(negocio.getMaeCausaEstanciaProlongadaCodigo());
        entidad.setMaeCausaEstanciaProlongadaValor(negocio.getMaeCausaEstanciaProlongadaValor());
        entidad.setMaePropuestaIntervencionId(negocio.getMaePropuestaIntervensionId());
        entidad.setMaePropuestaIntervencionCodigo(negocio.getMaePropuestaIntervensionCodigo());
        entidad.setMaePropuestaIntervencionValor(negocio.getMaePropuestaIntervensionValor());
        entidad.setResumenClinico(negocio.getResumenClinico());
        
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());

        return entidad;
    }
}
