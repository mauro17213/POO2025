/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucEgreso;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.AucEgresos;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucEgresoRemoto;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author sgiraldov
 */
@Stateless
@Remote(AucEgresoRemoto.class)
public class AucEgresoServicio extends GenericoServicio implements AucEgresoRemoto {

    @Override
    public AucEgreso consultar(int id) throws Exception {
        AucEgreso objRes = null;
        try {
            objRes = castEntidadNegocio((AucEgresos) getEntityManager().find(AucEgresos.class, id));
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
    public int insertar(AucEgreso obj) throws Exception {
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
    public void actualizar(AucEgreso obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucEgresos a SET ";
            strQuery += "a.numCertificado = :numCertificado ,";
            strQuery += "a.fechaEgreso = :fechaEgreso ,";
            strQuery += "a.fallecido = :fallecido ,";
            strQuery += "a.maeDestinoEgresoId = :maeDestinoEgresoId ,";
            strQuery += "a.maeDestinoEgresoCodigo = :maeDestinoEgresoCodigo ,";
            strQuery += "a.maeDestinoEgresoValor = :maeDestinoEgresoValor ,";
            strQuery += "a.maeConductaEgresoId = :maeConductaEgresoId ,";
            strQuery += "a.maeConductaEgresoCodigo = :maeConductaEgresoCodigo ,";
            strQuery += "a.maeConductaEgresoValor = :maeConductaEgresoValor ,";
            strQuery += "a.maEspecialidadesId = :maEspecialidadesId ,";
            strQuery += "a.maEspecialidadesCodigo = :maEspecialidadesCodigo ,";
            strQuery += "a.maEspecialidadesValor = :maEspecialidadesValor ,";
            strQuery += "a.valorEstancia = :valorEstancia ,";
            strQuery += "a.facturado = :facturado ,";
            strQuery += "a.ipsEntregaValor = :ipsEntregaValor ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("numCertificado", obj.getNumCertificado());
            query.setParameter("fechaEgreso", obj.getFechaEgreso());
            query.setParameter("fallecido", obj.isFallecido());
            query.setParameter("maeDestinoEgresoId", obj.getMaeDestinoEgresoId());
            query.setParameter("maeDestinoEgresoCodigo", obj.getMaeDestinoEgresoCodigo());
            query.setParameter("maeDestinoEgresoValor", obj.getMaeDestinoEgresoValor());
            query.setParameter("maeConductaEgresoId", obj.getMaeConductaEgresoId());
            query.setParameter("maeConductaEgresoCodigo", obj.getMaeConductaEgresoCodigo());
            query.setParameter("maeConductaEgresoValor", obj.getMaeConductaEgresoValor());
            query.setParameter("maEspecialidadesId", obj.getMaEspecialidadesId());
            query.setParameter("maEspecialidadesCodigo", obj.getMaEspecialidadesCodigo());
            query.setParameter("maEspecialidadesValor", obj.getMaEspecialidadesValor());
            query.setParameter("valorEstancia", obj.getValorEstancia());
            query.setParameter("facturado", obj.getFacturado());
            query.setParameter("ipsEntregaValor", obj.isIpsEntregaValor());
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
    public AucEgreso eliminar(int id) throws Exception {
        AucEgreso obj = null;
        try {
            AucEgresos ent = getEntityManager().find(AucEgresos.class, id);
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
    
    private AucEgreso castEntidadNegocio(AucEgresos entidad) {
        AucEgreso negocio = new AucEgreso();
        negocio.setId(entidad.getId());
        negocio.setNumCertificado(entidad.getNumCertificado());
        negocio.setFechaEgreso(entidad.getFechaEgreso());
        negocio.setFallecido(entidad.getFallecido());
        negocio.setMaeDestinoEgresoId(entidad.getMaeDestinoEgresoId());
        negocio.setMaeDestinoEgresoCodigo(entidad.getMaeDestinoEgresoCodigo());
        negocio.setMaeDestinoEgresoValor(entidad.getMaeDestinoEgresoValor());
        negocio.setMaeConductaEgresoId(entidad.getMaeConductaEgresoId());
        negocio.setMaeConductaEgresoCodigo(entidad.getMaeConductaEgresoCodigo());
        negocio.setMaeConductaEgresoValor(entidad.getMaeConductaEgresoValor());
        negocio.setMaEspecialidadesId(entidad.getMaEspecialidadesId());
        negocio.setMaEspecialidadesCodigo(entidad.getMaEspecialidadesCodigo());
        negocio.setMaEspecialidadesValor(entidad.getMaEspecialidadesValor());
        negocio.setValorEstancia(entidad.getValorEstancia());
        negocio.setFacturado(entidad.getFacturado());
        negocio.setFuenteOrigen(entidad.getFuenteOrigen());
        negocio.setIpsEntregaValor(entidad.getIpsEntregaValor());
        if(!entidad.getAucDiagnosticosList().isEmpty()){
            negocio.setAucDiagnosticosList(AucDiagnosticoServicio.castEntidadNegocio(entidad.getAucDiagnosticosList()));
        }
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }
    
    private AucEgresos castNegocioEntidad(AucEgreso negocio) {
        AucEgresos entidad = new AucEgresos();
        entidad.setNumCertificado(negocio.getNumCertificado());
        entidad.setFechaEgreso(negocio.getFechaEgreso());
        entidad.setFallecido(negocio.isFallecido());
        entidad.setMaeDestinoEgresoId(negocio.getMaeDestinoEgresoId());
        entidad.setMaeDestinoEgresoCodigo(negocio.getMaeDestinoEgresoCodigo());
        entidad.setMaeDestinoEgresoValor(negocio.getMaeDestinoEgresoValor());
        entidad.setMaeConductaEgresoId(negocio.getMaeConductaEgresoId());
        entidad.setMaeConductaEgresoCodigo(negocio.getMaeConductaEgresoCodigo());
        entidad.setMaeConductaEgresoValor(negocio.getMaeConductaEgresoValor());
        entidad.setMaEspecialidadesId(negocio.getMaEspecialidadesId());
        entidad.setMaEspecialidadesCodigo(negocio.getMaEspecialidadesCodigo());
        entidad.setMaEspecialidadesValor(negocio.getMaEspecialidadesValor());
        entidad.setValorEstancia(negocio.getValorEstancia());
        entidad.setFacturado(negocio.getFacturado());
        entidad.setFuenteOrigen(negocio.getFuenteOrigen());
        entidad.setIpsEntregaValor(negocio.isIpsEntregaValor());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }
    
}
