
package com.saviasaludeps.savia.ejb.servicios.gestionAtencion;


import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatAtencion;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTiempo;
import com.saviasaludeps.savia.ejb.entidades.GatAtenciones;
import com.saviasaludeps.savia.ejb.entidades.GatTiempos;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatTiempoRemoto;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author idbohorquez
 */
@Stateless
@Remote(GatTiempoRemoto.class)
public class GatTiempoServicio extends GenericoServicio implements GatTiempoRemoto {
    
    
    @Override
    public int insertar(GatTiempo obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return _id;        
    }
    
    @Override
    public GatTiempo consultar(int id) throws Exception {
        GatTiempo objRes = null;
        try {
            objRes = castEntidadNegocio((GatTiempos) getEntityManager().find(GatTiempos.class, id));
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
    public Integer cantidadReposos(String fecha, int idUsuario) throws Exception {
        Integer cantidad = 0;
        String strQuery = "select COUNT(id) from gat_tiempos gt where gn_usuarios_id = :usuario and DATE_FORMAT(fecha_inicio, '%Y-%m-%d') = :fecha  ";
        try {
            BigInteger resul = (BigInteger) getEntityManager().createNativeQuery(strQuery)
                    .setParameter("usuario", idUsuario)
                    .setParameter("fecha", fecha)
                    .setMaxResults(1)
                    .getSingleResult();
            if(resul != null){
                cantidad = resul.intValue();
            }
        } catch (NoResultException e) {
            cantidad = 0;
        }
        return cantidad;
    }
    
    @Override
    public void actualizarTiempo(GatTiempo obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GatTiempos a SET ";
            strQuery += "a.activo = :activo ,";
            strQuery += "a.fechaFin = :fechaFin ,";
            strQuery += "a.tiempoTotal = :tiempoTotal "; 
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("activo", obj.isActivo());
            query.setParameter("fechaFin", obj.getFechaFin());
            query.setParameter("tiempoTotal", obj.getTiempoTotal());
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
    
    
    private GatTiempos castNegocioEntidad(GatTiempo negocio) {
        GatTiempos entidad = new GatTiempos();
        entidad.setFechaInicio(negocio.getFechaInicio());
        entidad.setFechaFin(negocio.getFechaFin());
        entidad.setTiempoTotal(negocio.getTiempoTotal());
        entidad.setTipo(negocio.getTipo());
        if(negocio.getAtencionesId() != null){
            entidad.setGatAtencionesId(new GatAtenciones(negocio.getAtencionesId().getId()));
        }
        entidad.setActivo(negocio.isActivo());
        entidad.setGnUsuariosId(new GnUsuarios(negocio.getUsuariosId().getId()));
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());        
        return entidad;
    }
    
    private GatTiempo castEntidadNegocio(GatTiempos entidad) {
        GatTiempo negocio = new GatTiempo();
        negocio.setId(entidad.getId());
        negocio.setFechaInicio(entidad.getFechaInicio());
        negocio.setFechaFin(entidad.getFechaFin());
        negocio.setTiempoTotal(entidad.getTiempoTotal());
        negocio.setTipo(entidad.getTipo());
        if(entidad.getGatAtencionesId() != null){
            negocio.setAtencionesId(new GatAtencion(entidad.getGatAtencionesId().getId()));  
        }
        negocio.setActivo(entidad.getActivo() == null ? false : entidad.getActivo());
        negocio.setUsuariosId(new Usuario(entidad.getGnUsuariosId().getId()));
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());        
        return negocio;
    }

    @Override
    public GatTiempo consultarActivo(int idUsuario) throws Exception {
        GatTiempo tiempo = null;
        try {
            String strQuery = "FROM GatTiempos c "
                        + " WHERE c.activo = 1"
                        + " AND c.gnUsuariosId.id = "+idUsuario
                        + " ORDER BY c.id ASC";
            List<GatTiempos>  list = getEntityManager().createQuery(strQuery)
                        .getResultList();
            for (GatTiempos tiempos : list) {
                tiempo = castEntidadNegocio(tiempos);
                break;
            }
        } catch (NoResultException e) {
            tiempo = null;
        } catch (Exception e) {
            tiempo = null;
        } finally {
            cerrarEntityManager();
        }
        return tiempo;
    }

    @Override
    public int consultarTaquillasEnReposo(String idUsuarios) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GatTiempos c "
                    + " WHERE c.activo = 1 and c.gnUsuariosId.id IN ("+idUsuarios+") ";
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            cant = 0;
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }
    
}
