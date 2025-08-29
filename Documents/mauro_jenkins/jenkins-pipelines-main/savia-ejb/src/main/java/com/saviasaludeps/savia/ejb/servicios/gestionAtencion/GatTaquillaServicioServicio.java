/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.gestionAtencion;

import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeTaquilla;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTaquillaServicio;
import com.saviasaludeps.savia.ejb.entidades.GatTaquillaServicios;
import com.saviasaludeps.savia.ejb.entidades.GatSedeTaquillas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatTaquillaServicioRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author acuartas
 */
@Stateless
@Remote(GatTaquillaServicioRemoto.class)
public class GatTaquillaServicioServicio extends GenericoServicio implements GatTaquillaServicioRemoto {

    @Override
    public int insertar(GatTaquillaServicio obj) throws Exception {
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
    public GatTaquillaServicio eliminar(int id) throws Exception {
        GatTaquillaServicio obj = null;
        try {
            GatTaquillaServicios ent = getEntityManager().find(GatTaquillaServicios.class, id);
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
    
    private GatTaquillaServicio castEntidadNegocio(GatTaquillaServicios entidad) {
        GatTaquillaServicio negocio = new GatTaquillaServicio();
        negocio.setId(entidad.getId());
        negocio.setGatSedeTaquillaId(new GatSedeTaquilla(entidad.getGatSedeTaquillasId().getId()));
        negocio.setMaeTipoServicioId(entidad.getMaeTipoServicioId());
        negocio.setMaeTipoServicioCodigo(entidad.getMaeTipoServicioCodigo());
        negocio.setMaeTipoServicioValor(entidad.getMaeTipoServicioValor());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }
    
    private GatTaquillaServicios castNegocioEntidad(GatTaquillaServicio negocio) {
        GatTaquillaServicios entidad = new GatTaquillaServicios();
        entidad.setGatSedeTaquillasId(new GatSedeTaquillas(negocio.getGatSedeTaquillaId().getId()));
        entidad.setMaeTipoServicioId(negocio.getMaeTipoServicioId());
        entidad.setMaeTipoServicioCodigo(negocio.getMaeTipoServicioCodigo());
        entidad.setMaeTipoServicioValor(negocio.getMaeTipoServicioValor());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

    @Override
    public List<GatTaquillaServicio> listarPorIdTaquilla(int idTaquilla) throws Exception {
        List<GatTaquillaServicio> listaResultado = new ArrayList<>();
        try {
            String strQuery = "FROM GatTaquillaServicios c "
                    + " WHERE c.gatSedeTaquillasId.id = "+idTaquilla
                    + " ORDER BY c.id DESC";
            List<GatTaquillaServicios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GatTaquillaServicios servicio : list) {
                listaResultado.add(castEntidadNegocio(servicio));
            }
        } catch (NoResultException e) {
            listaResultado = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultado;
    }
    
    @Override
    public List<GatTaquillaServicio> listarPorIdSede(int idSede) throws Exception {
        List<GatTaquillaServicio> listaResultado = new ArrayList<>();
        try {
            String strQuery = "FROM GatTaquillaServicios c "
                    + " WHERE c.gatSedeTaquillasId.gnSedesId.id = "+idSede
                    + " ORDER BY c.id DESC";
            List<GatTaquillaServicios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GatTaquillaServicios servicio : list) {
                listaResultado.add(castEntidadNegocio(servicio));
            }
        } catch (NoResultException e) {
            listaResultado = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultado;
    }

    @Override
    public int cantidadTaquillasEnAtencion(int idSede) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GatSedeTaquillas c "
                    + " WHERE c.activo = 1 and c.gnSedesId.id = "+idSede;
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
