/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionSeguimiento;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizacionSeguimientos;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizaciones;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionSeguimientoRemoto;
import java.util.ArrayList;
import java.util.List;
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
@Remote(AucHospitalizacionSeguimientoRemoto.class)
public class AucHospitalizacionSeguimientoServicio extends GenericoServicio implements AucHospitalizacionSeguimientoRemoto {

    @Override
    public AucHospitalizacionSeguimiento consultar(int id) throws Exception {
        AucHospitalizacionSeguimiento objRes = null;
        try {
            objRes = castEntidadNegocio((AucHospitalizacionSeguimientos) getEntityManager().find(AucHospitalizacionSeguimientos.class, id));
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
    public int insertar(AucHospitalizacionSeguimiento obj) throws Exception {
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
    public void actualizar(AucHospitalizacionSeguimiento obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizacionSeguimientos a SET ";
            strQuery += "a.aucHospitalizacionesId.id = :aucHospitalizacionesId ,";
            strQuery += "a.maeTipoSeguimientoId = :maeTipoSeguimientoId ,";
            strQuery += "a.maeTipoSeguimientoCodigo = :maeTipoSeguimientoCodigo ,";
            strQuery += "a.maeTipoSeguimientoValor = :maeTipoSeguimientoValor ,";
            strQuery += "a.maeTipoSeguimientoTipo = :maeTipoSeguimientoTipo ,";
            strQuery += "a.descripcion = :descripcion ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("aucHospitalizacionesId", obj.getAucHospitalizacionId().getId());
            query.setParameter("maeTipoSeguimientoId", obj.getMaeTipoSeguimientoId());
            query.setParameter("maeTipoSeguimientoCodigo", obj.getMaeTipoSeguimientoCodigo());
            query.setParameter("maeTipoSeguimientoValor", obj.getMaeTipoSeguimientoValor());
            query.setParameter("maeTipoSeguimientoTipo", obj.getMaeTipoSeguimientoTipo());
            query.setParameter("descripcion", obj.getDescripcion());
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
    public void actualizarGestorasRegionales(AucHospitalizacionSeguimiento obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizacionSeguimientos a SET ";
            strQuery += "a.maeTipoGestionId = :maeTipoGestionId, ";
            strQuery += "a.maeTipoGestionCodigo = :maeTipoGestionCodigo, ";
            strQuery += "a.maeTipoGestionValor = :maeTipoGestionValor, ";
            strQuery += "a.maeTipoGestionTipo = :maeTipoGestionTipo, ";
            strQuery += "a.maeTipoGestionEstadoId = :maeTipoGestionEstadoId, ";
            strQuery += "a.maeTipoGestionEstadoCodigo = :maeTipoGestionEstadoCodigo, ";
            strQuery += "a.maeTipoGestionEstadoValor = :maeTipoGestionEstadoValor, ";
            strQuery += "a.maeTipoGestionEstadoTipo = :maeTipoGestionEstadoTipo, ";
            strQuery += "a.cntPrestadorSedesId = :cntPrestadorSedesId, ";
            strQuery += "a.cntPrestadoresId = :cntPrestadoresId, ";
            strQuery += "a.fechaCierreGestion = :fechaCierreGestion, ";
            strQuery += "a.maeDestinoId = :maeDestinoId, ";
            strQuery += "a.maeDestinoCodigo = :maeDestinoCodigo, ";
            strQuery += "a.maeDestinoValor = :maeDestinoValor, ";
            strQuery += "a.maeDestinoTipo = :maeDestinoTipo, ";
            strQuery += "a.descripcion = :descripcion, ";
            strQuery += "a.usuarioModifica = :usuarioModifica, ";
            strQuery += "a.terminalModifica = :terminalModifica, ";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += "WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("maeTipoGestionId", obj.getMaeTipoGestionId());
            query.setParameter("maeTipoGestionCodigo", obj.getMaeTipoGestionCodigo());
            query.setParameter("maeTipoGestionValor", obj.getMaeTipoGestionValor());
            query.setParameter("maeTipoGestionTipo", obj.getMaeTipoGestionTipo());
            query.setParameter("maeTipoGestionEstadoId", obj.getMaeTipoGestionEstadoId());
            query.setParameter("maeTipoGestionEstadoCodigo", obj.getMaeTipoGestionEstadoCodigo());
            query.setParameter("maeTipoGestionEstadoValor", obj.getMaeTipoGestionEstadoValor());
            query.setParameter("maeTipoGestionEstadoTipo", obj.getMaeTipoGestionEstadoTipo());
            if(obj.getCntPrestadorSedesId() != null){
                query.setParameter("cntPrestadorSedesId", obj.getCntPrestadorSedesId().getId());
            }else{
                query.setParameter("cntPrestadorSedesId", null);
            }
            if(obj.getCntPrestadoresId() != null){
                query.setParameter("cntPrestadoresId", obj.getCntPrestadoresId().getId());
            }else{
                 query.setParameter("cntPrestadoresId", null);
            }
            query.setParameter("fechaCierreGestion", obj.getFechaCierreGestion());
            query.setParameter("maeDestinoId", obj.getMaeDestinoId());
            query.setParameter("maeDestinoCodigo", obj.getMaeDestinoCodigo());
            query.setParameter("maeDestinoValor", obj.getMaeDestinoValor());
            query.setParameter("maeDestinoTipo", obj.getMaeDestinoTipo());
            query.setParameter("descripcion", obj.getDescripcion());
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
    public void actualizarBorradoLogico(AucHospitalizacionSeguimiento obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizacionSeguimientos a SET ";
            strQuery += "a.borrado = :borrado,";
            strQuery += "a.borradoObservacion = :borradoObservacion,";
            strQuery += "a.usuarioBorra = :usuarioBorra,";
            strQuery += "a.terminalBorra = :terminalBorra,";
            strQuery += "a.fechaHoraBorra = :fechaHoraBorra ";
            strQuery += "WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("borrado", obj.getBorrado());
            query.setParameter("borradoObservacion", obj.getBorradoObservacion());
            query.setParameter("usuarioBorra", obj.getUsuarioBorra());
            query.setParameter("terminalBorra", obj.getTerminalBorra());
            query.setParameter("fechaHoraBorra", obj.getFechaHoraBorra());
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
    public AucHospitalizacionSeguimiento eliminar(int id) throws Exception {
        AucHospitalizacionSeguimiento obj = null;
        try {
            AucHospitalizacionSeguimientos ent = getEntityManager().find(AucHospitalizacionSeguimientos.class, id);
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
    public List<AucHospitalizacionSeguimiento> consultarPorIdHospitalizacion(int idHospitalizacion) throws Exception {
        List<AucHospitalizacionSeguimiento> listaResultados = new ArrayList();
        try {
            String strQuery = "SELECT ahs FROM AucHospitalizacionSeguimientos ahs "
                    + "INNER JOIN GnMaestros gm ON ahs.maeTipoSeguimientoId = gm.id "
                    + "INNER JOIN GnMaestroAccionRelaciones gmar ON gmar.gnMaestrosId = gm.id "
                    + "INNER JOIN GnMaestroAcciones gma ON gma.id = gmar.gnMaestroAccionesId "
                    + "WHERE ahs.aucHospitalizacionesId = " + idHospitalizacion + " "
                    + "AND ahs.borrado = 0 " 
                    + "AND gma.id = " + MaestroAccion.AUC_TIPO_SEGUIMIENTO_AUDITORES + " "
                    + "ORDER BY ahs.fechaHoraCrea DESC";

            List<AucHospitalizacionSeguimientos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AucHospitalizacionSeguimientos seguimiento : list) {
                listaResultados.add(castEntidadNegocio(seguimiento));
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
    
    @Override
    public List<AucHospitalizacionSeguimiento> consultarPorIdHospitalizacionGestorasRegionales(int idHospitalizacion) throws Exception {
        List<AucHospitalizacionSeguimiento> listaResultados = new ArrayList();
        try {
            String strQuery = "SELECT ahs FROM AucHospitalizacionSeguimientos ahs "
                    + "INNER JOIN GnMaestros gm ON ahs.maeTipoSeguimientoId = gm.id "
                    + "INNER JOIN GnMaestroAccionRelaciones gmar ON gmar.gnMaestrosId = gm.id "
                    + "INNER JOIN GnMaestroAcciones gma ON gma.id = gmar.gnMaestroAccionesId "
                    + "WHERE ahs.aucHospitalizacionesId = " + idHospitalizacion + " "
                    + "AND ahs.borrado = 0 " 
                    + "AND gma.id = " + MaestroAccion.AUC_TIPO_SEGUIMIENTO_GESTORES + " "
                    + "ORDER BY ahs.fechaHoraCrea DESC";

            List<AucHospitalizacionSeguimientos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AucHospitalizacionSeguimientos seguimiento : list) {
                listaResultados.add(castEntidadNegocio(seguimiento));
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
    
    private AucHospitalizacionSeguimiento castEntidadNegocio(AucHospitalizacionSeguimientos entidad) {
        AucHospitalizacionSeguimiento negocio = new AucHospitalizacionSeguimiento();
        negocio.setId(entidad.getId());
        negocio.setAucHospitalizacionId(new AucHospitalizacion(entidad.getAucHospitalizacionesId().getId()));
        negocio.setMaeTipoSeguimientoId(entidad.getMaeTipoSeguimientoId());
        negocio.setMaeTipoSeguimientoCodigo(entidad.getMaeTipoSeguimientoCodigo());
        negocio.setMaeTipoSeguimientoValor(entidad.getMaeTipoSeguimientoValor());
        negocio.setMaeTipoSeguimientoTipo(entidad.getMaeTipoSeguimientoTipo());
        negocio.setDescripcion(entidad.getDescripcion());
        if (entidad.getOrigen() != null) {
            negocio.setOrigen(entidad.getOrigen());
        }
        negocio.setMaeTipoGestionId(entidad.getMaeTipoGestionId());
        negocio.setMaeTipoGestionCodigo(entidad.getMaeTipoGestionCodigo());
        negocio.setMaeTipoGestionValor(entidad.getMaeTipoGestionValor());
        negocio.setMaeTipoGestionTipo(entidad.getMaeTipoGestionTipo());
        negocio.setMaeTipoGestionEstadoId(entidad.getMaeTipoGestionEstadoId());
        negocio.setMaeTipoGestionEstadoCodigo(entidad.getMaeTipoGestionEstadoCodigo());
        negocio.setMaeTipoGestionEstadoValor(entidad.getMaeTipoGestionEstadoValor());
        negocio.setMaeTipoGestionEstadoTipo(entidad.getMaeTipoGestionEstadoTipo()); 
        if(entidad.getCntPrestadoresId() != null){
            negocio.setCntPrestadoresId(new CntPrestador(entidad.getCntPrestadoresId()));
        }
        if(entidad.getCntPrestadorSedesId() != null){
            negocio.setCntPrestadorSedesId(new CntPrestadorSede(entidad.getCntPrestadorSedesId()));
        }
        negocio.setFechaCierreGestion(entidad.getFechaCierreGestion());
        negocio.setMaeDestinoId(entidad.getMaeDestinoId());
        negocio.setMaeDestinoCodigo(entidad.getMaeDestinoCodigo());
        negocio.setMaeDestinoValor(entidad.getMaeDestinoValor());
        negocio.setMaeDestinoTipo(entidad.getMaeDestinoTipo());
        negocio.setBorrado(entidad.getBorrado());
        negocio.setBorradoObservacion(entidad.getBorradoObservacion());
    
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        negocio.setUsuarioBorra(entidad.getUsuarioBorra());
        negocio.setTerminalBorra(entidad.getTerminalBorra());
        negocio.setFechaHoraBorra(entidad.getFechaHoraBorra());
        return negocio;
    }
    
    private AucHospitalizacionSeguimientos castNegocioEntidad(AucHospitalizacionSeguimiento negocio) {
        AucHospitalizacionSeguimientos entidad = new AucHospitalizacionSeguimientos();
        if(negocio.getAucHospitalizacionId() != null){
            entidad.setAucHospitalizacionesId(new AucHospitalizaciones(negocio.getAucHospitalizacionId().getId()));
        }
        entidad.setMaeTipoSeguimientoId(negocio.getMaeTipoSeguimientoId());
        entidad.setMaeTipoSeguimientoCodigo(negocio.getMaeTipoSeguimientoCodigo());
        entidad.setMaeTipoSeguimientoValor(negocio.getMaeTipoSeguimientoValor());
        entidad.setMaeTipoSeguimientoTipo(negocio.getMaeTipoSeguimientoTipo());
        entidad.setDescripcion(negocio.getDescripcion());
        entidad.setOrigen(negocio.getOrigen());
        entidad.setMaeTipoGestionId(negocio.getMaeTipoGestionId());
        entidad.setMaeTipoGestionCodigo(negocio.getMaeTipoGestionCodigo());
        entidad.setMaeTipoGestionValor(negocio.getMaeTipoGestionValor());
        entidad.setMaeTipoGestionTipo(negocio.getMaeTipoGestionTipo());
        entidad.setMaeTipoGestionEstadoId(negocio.getMaeTipoGestionEstadoId());
        entidad.setMaeTipoGestionEstadoCodigo(negocio.getMaeTipoGestionEstadoCodigo());
        entidad.setMaeTipoGestionEstadoValor(negocio.getMaeTipoGestionEstadoValor());
        entidad.setMaeTipoGestionEstadoTipo(negocio.getMaeTipoGestionEstadoTipo());
        if(negocio.getCntPrestadoresId() != null){
            entidad.setCntPrestadoresId(negocio.getCntPrestadoresId().getId());
        }
        if(negocio.getCntPrestadorSedesId() != null){
            entidad.setCntPrestadorSedesId(negocio.getCntPrestadorSedesId().getId());
        }
        entidad.setFechaCierreGestion(negocio.getFechaCierreGestion());
        entidad.setMaeDestinoId(negocio.getMaeDestinoId());
        entidad.setMaeDestinoCodigo(negocio.getMaeDestinoCodigo());
        entidad.setMaeDestinoValor(negocio.getMaeDestinoValor());
        entidad.setMaeDestinoTipo(negocio.getMaeDestinoTipo());
        entidad.setBorrado(negocio.getBorrado());
        entidad.setBorradoObservacion(negocio.getBorradoObservacion());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }
}
