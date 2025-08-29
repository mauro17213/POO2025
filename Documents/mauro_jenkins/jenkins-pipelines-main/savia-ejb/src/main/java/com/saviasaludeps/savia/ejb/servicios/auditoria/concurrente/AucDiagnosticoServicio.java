/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucDiagnostico;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucEgreso;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucIngreso;
import com.saviasaludeps.savia.ejb.entidades.AucDiagnosticos;
import com.saviasaludeps.savia.ejb.entidades.AucEgresos;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizaciones;
import com.saviasaludeps.savia.ejb.entidades.AucIngresos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucDiagnosticoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author pavacca
 */
@Stateless
@Remote(AucDiagnosticoRemoto.class)
public class AucDiagnosticoServicio extends GenericoServicio implements AucDiagnosticoRemoto{
    
    @Override
    public AucDiagnostico consultar(int id) throws Exception {
        AucDiagnostico objRes = null;
        try {
            objRes = castEntidadNegocio((AucDiagnosticos) getEntityManager().find(AucDiagnosticos.class, id));
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
    public int insertar(AucDiagnostico aucDiagnostico) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(aucDiagnostico)).getId();
            aucDiagnostico.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al insertar un seguimiento en gestión tutelas");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }
    
    @Override
    public void actualizar(AucDiagnostico obj) throws Exception {
        try {
            String hql = "UPDATE AucDiagnosticos SET"
                + " maeTipoDiagnosticoId = :maeTipoDiagnosticoId,"
                + " maeTipoDiagnosticoCodigo = :maeTipoDiagnosticoCodigo,"
                + " maeTipoDiagnosticoValor = :maeTipoDiagnosticoValor,"
                + " principal = :principal,"
                + " maDiagnosticoId = :maDiagnosticoId," 
                + " maDiagnosticoCodigo = :maDiagnosticoCodigo," 
                + " maDiagnosticoValor = :maDiagnosticoValor,"
                + ((obj.getAucEgresosId() != null && obj.getAucEgresosId().getId() != null) ? " aucEgresosId.id = :aucEgresosId" : "")
                + ((obj.getAucIngresosId() != null && obj.getAucIngresosId().getId() != null) ? " aucIngresosId.id = :aucIngresosId"  : "")
                //+ " usuarioModifica = :usuarioModifica,"
                //+ " terminalModifica = :terminalModifica,"
                //+ " fechaHoraModifica = :fechaHoraModifica"
                + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("maeTipoDiagnosticoId", obj.getMaeTipoDiagnosticoId());
            query.setParameter("maeTipoDiagnosticoCodigo", obj.getMaeTipoDiagnosticoCodigo());
            query.setParameter("maeTipoDiagnosticoValor", obj.getMaeTipoDiagnosticoValor());
            query.setParameter("principal", obj.isPrincipal());
            query.setParameter("maDiagnosticoId", obj.getMaDiagnosticoId());
            query.setParameter("maDiagnosticoCodigo", obj.getMaDiagnosticoCodigo());
            query.setParameter("maDiagnosticoValor", obj.getMaDiagnosticoValor());
            if(obj.getAucEgresosId() != null && obj.getAucEgresosId().getId() != null){
                query.setParameter("aucEgresosId", obj.getAucEgresosId().getId());
            }
            if(obj.getAucIngresosId() != null && obj.getAucIngresosId().getId() != null){
                 query.setParameter("aucIngresosId", obj.getAucIngresosId().getId());
            }
            //query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            //query.setParameter("terminalModifica", obj.getTerminalModifica());
            //query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
          
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public AucDiagnostico eliminar(int id) throws Exception {
        AucDiagnostico obj = null;
        try {
            AucDiagnosticos ent = getEntityManager().find(AucDiagnosticos.class, id);
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
    public List<AucDiagnostico> consultarPorIdIngreso(int idIngreso) throws Exception {
        List<AucDiagnostico> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AucDiagnosticos p "
                    + "WHERE p.aucIngresosId.id = " + idIngreso + " "
                    + "ORDER BY p.id ASC";

            List<AucDiagnosticos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AucDiagnosticos seguimiento : list) {
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
    public List<AucDiagnostico> consultarPorIdEgreso(int idEgreso) throws Exception {
        List<AucDiagnostico> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AucDiagnosticos p "
                    + "WHERE p.aucEgresosId.id = " + idEgreso + " "
                    + "ORDER BY p.id ASC";

            List<AucDiagnosticos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AucDiagnosticos seguimiento : list) {
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
    public List<AucDiagnostico> consultarPorIdHospitalizacion(int idEgreso) throws Exception {
        List<AucDiagnostico> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AucDiagnosticos p "
                    + "WHERE p.aucHospitalizacionId.id = " + idEgreso + " "
                    + "ORDER BY p.id ASC";

            List<AucDiagnosticos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AucDiagnosticos seguimiento : list) {
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
    
    private AucDiagnosticos castNegocioEntidad(AucDiagnostico negocio) {
        AucDiagnosticos entidad = new AucDiagnosticos();
        if(negocio.getAucEgresosId() != null){
            entidad.setAucEgresosId(new AucEgresos(negocio.getAucEgresosId().getId()));
        }
        if(negocio.getAucIngresosId() != null){
            entidad.setAucIngresosId(new AucIngresos(negocio.getAucIngresosId().getId())); //Revisar
        }
        if(negocio.getAucHospitalizacionId() != null){
            entidad.setAucHospitalizacionId(new AucHospitalizaciones(negocio.getAucHospitalizacionId().getId()));
        }
        entidad.setMaeTipoDiagnosticoId(negocio.getMaeTipoDiagnosticoId());
        entidad.setMaeTipoDiagnosticoCodigo(negocio.getMaeTipoDiagnosticoCodigo());
        entidad.setMaeTipoDiagnosticoValor(negocio.getMaeTipoDiagnosticoValor());
        entidad.setMaDiagnosticoId(negocio.getMaDiagnosticoId());
        entidad.setMaDiagnosticoCodigo(negocio.getMaDiagnosticoCodigo());
        entidad.setMaDiagnosticoValor(negocio.getMaDiagnosticoValor());
        entidad.setPrincipal(negocio.isPrincipal());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }
    
    public static List<AucDiagnostico> castEntidadNegocio(List<AucDiagnosticos> entidad) {
        List<AucDiagnostico> listaServicios = new ArrayList<>();
        for(AucDiagnosticos servicioNegocio:entidad){
            listaServicios.add(castEntidadNegocio(servicioNegocio));
        }
        /*serviciosNegocio.forEach(servicioNegocio -> {
            listaServicios.add(castEntidadNegocio(servicioNegocio));
        });*/
        return listaServicios;
    }
    
    public static AucDiagnostico castEntidadNegocio(AucDiagnosticos entidad) {
        AucDiagnostico negocio = new AucDiagnostico();
        negocio.setId(entidad.getId());
        if(entidad.getAucEgresosId() != null){
            negocio.setAucEgresosId(new AucEgreso(entidad.getAucEgresosId().getId()));
        }
        if(entidad.getAucIngresosId() != null){
            negocio.setAucIngresosId(new AucIngreso(entidad.getAucIngresosId().getId()));
        }
        if(entidad.getAucHospitalizacionId() != null){
            negocio.setAucHospitalizacionId(new AucHospitalizacion(entidad.getAucHospitalizacionId().getId()));
        }
        negocio.setMaeTipoDiagnosticoId(entidad.getMaeTipoDiagnosticoId());
        negocio.setMaeTipoDiagnosticoCodigo(entidad.getMaeTipoDiagnosticoCodigo());
        negocio.setMaeTipoDiagnosticoValor(entidad.getMaeTipoDiagnosticoValor());
        negocio.setMaDiagnosticoId(entidad.getMaDiagnosticoId());
        negocio.setMaDiagnosticoCodigo(entidad.getMaDiagnosticoCodigo());
        negocio.setMaDiagnosticoValor(entidad.getMaDiagnosticoValor());
        negocio.setPrincipal(entidad.getPrincipal());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        ///negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        //negocio.setTerminalModifica(entidad.getTerminalModifica());
        //negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }
}