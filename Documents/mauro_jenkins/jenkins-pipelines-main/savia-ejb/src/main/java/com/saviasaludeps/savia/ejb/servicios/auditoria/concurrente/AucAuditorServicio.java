/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucAuditor;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.AucAuditores;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucAuditorRemoto;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author sgiraldov
 */
@Stateless
@Remote(AucAuditorRemoto.class)
public class AucAuditorServicio extends GenericoServicio implements AucAuditorRemoto {

    @Override
    public AucAuditor consultar(int id) throws Exception {
        AucAuditor objRes = null;
        try {
            objRes = castEntidadNegocio((AucAuditores) getEntityManager().find(AucAuditores.class, id));
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
    public int insertar(AucAuditor obj) throws Exception {
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
    public void actualizar(AucAuditor obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucAuditors a SET ";
            strQuery += "a.gnUsuariosId = :gnUsuariosId ,";
            strQuery += "a.cntPrestadoresId = :cntPrestadoresId ,";
            strQuery += "a.cntPrestadorSedesId = :cntPrestadorSedesId ,";
            strQuery += "a.activo = :activo ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("gnUsuariosId", obj.getGnUsuarioId());
            query.setParameter("cntPrestadoresId", obj.getCntPrestadorId());
            query.setParameter("cntPrestadorSedesId", obj.getCntPrestadorSedeId());
            query.setParameter("activo", obj.isActivo());
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
    public AucAuditor eliminar(int id) throws Exception {
        AucAuditor obj = null;
        try {
            AucAuditores ent = getEntityManager().find(AucAuditores.class, id);
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
    
    private AucAuditor castEntidadNegocio(AucAuditores entidad) {
        AucAuditor negocio = new AucAuditor();
        negocio.setId(entidad.getId());
        if(entidad.getGnUsuariosId() != null){
            Usuario usuario = new Usuario();
            usuario.setId(entidad.getGnUsuariosId().getId());
            usuario.setUsuario(entidad.getGnUsuariosId().getUsuario());
            usuario.setNombre(entidad.getGnUsuariosId().getNombre());
            negocio.setGnUsuarioId(usuario);
        }
        if(entidad.getCntPrestadoresId() != null){
            CntPrestador prestador = new CntPrestador();
            prestador.setId(entidad.getCntPrestadoresId().getId());
            negocio.setCntPrestadorId(prestador);
        }
        if(entidad.getCntPrestadorSedesId() != null){
            CntPrestadorSede prestadorSede = new CntPrestadorSede();
            prestadorSede.setId(entidad.getCntPrestadorSedesId().getId());
            negocio.setCntPrestadorSedeId(prestadorSede);
        }
        
        negocio.setActivo(entidad.getActivo());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }
    
    private AucAuditores castNegocioEntidad(AucAuditor negocio) {
        AucAuditores entidad = new AucAuditores();
        entidad.setId(negocio.getId());
        if(negocio.getGnUsuarioId() != null){
            entidad.setGnUsuariosId(new GnUsuarios(negocio.getGnUsuarioId().getId()));
        }
        if(negocio.getCntPrestadorId() != null){
            entidad.setCntPrestadoresId(new CntPrestadores(negocio.getCntPrestadorId().getId()));
        }
        if(negocio.getCntPrestadorSedeId() != null){
            entidad.setCntPrestadorSedesId(new CntPrestadorSedes(negocio.getCntPrestadorSedeId().getId()));
        }
        entidad.setActivo(negocio.isActivo());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }
    
}
