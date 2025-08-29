/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Reporte;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4Reportes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4ReporteRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Stiven Giraldo
 */
@Stateless
@Remote(AuAnexo4ReporteRemoto.class)
public class AuAnexo4ReporteServicio extends GenericoServicio implements AuAnexo4ReporteRemoto {

    @Override
    public int insertar(AuAnexo4Reporte obj) throws Exception {
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
    public List<AuAnexo4Reporte> listarPorUsuario(int idUsuario) throws Exception {
        List<AuAnexo4Reporte> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo4Reportes p "
                    + "WHERE p.usuarioCreaId = "+idUsuario+" ORDER BY p.id DESC";
            List<AuAnexo4Reportes> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuAnexo4Reportes anexo4Reporte: list){
                listaResultados.add(castEntidadNegocio(anexo4Reporte));
            }
        } catch (NoResultException e){
            listaResultados = new ArrayList();
        }catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally{
            cerrarEntityManager();
        }
        return listaResultados;
    }
    
    private AuAnexo4Reportes castNegocioEntidad(AuAnexo4Reporte negocio) {
        AuAnexo4Reportes entidad = new AuAnexo4Reportes();
        entidad.setArchivo(negocio.getArchivo());
        entidad.setCantidadProcesada(negocio.getCantidadProcesada());
        entidad.setCantidadTotal(negocio.getCantidadTotal());
        entidad.setDescripcion(negocio.getDescripcion());
        entidad.setEstado(negocio.getEstado());
        entidad.setFechaFin(negocio.getFechaFin());
        entidad.setFechaInicio(negocio.getFechaInicio());
        entidad.setRuta(negocio.getRuta());
        entidad.setUsuarioCreaId(negocio.getUsuarioCreaId());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }
    
    private AuAnexo4Reporte castEntidadNegocio(AuAnexo4Reportes entidad) {
        AuAnexo4Reporte negocio = new AuAnexo4Reporte();
        negocio.setId(entidad.getId());
        negocio.setArchivo(entidad.getArchivo());
        negocio.setCantidadProcesada(entidad.getCantidadProcesada());
        negocio.setCantidadTotal(entidad.getCantidadTotal());
        negocio.setDescripcion(entidad.getDescripcion());
        negocio.setEstado(entidad.getEstado());
        negocio.setFechaFin(entidad.getFechaFin());
        negocio.setFechaInicio(entidad.getFechaInicio());
        negocio.setRuta(entidad.getRuta());
        negocio.setUsuarioCreaId(entidad.getUsuarioCreaId());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }

    @Override
    public void actualizar(AuAnexo4Reporte obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexo4Reportes a SET ";
            strQuery += "a.archivo = :archivo, ";
            strQuery += "a.ruta = :ruta, ";
            strQuery += "a.estado = :estado, ";
            strQuery += "a.cantidadProcesada = :cantidadProcesada, ";
            strQuery += "a.cantidadTotal = :cantidadTotal, ";
            strQuery += "a.descripcion = :descripcion ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("archivo", obj.getArchivo());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("ruta", obj.getRuta());
            query.setParameter("cantidadProcesada", obj.getCantidadProcesada());
            query.setParameter("cantidadTotal", obj.getCantidadTotal());
            query.setParameter("descripcion", obj.getDescripcion());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
}
