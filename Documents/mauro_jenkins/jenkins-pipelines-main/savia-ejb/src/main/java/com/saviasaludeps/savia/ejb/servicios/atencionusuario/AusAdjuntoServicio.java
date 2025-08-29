/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.atencionusuario;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.atencionusuario.AusAdjunto;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCaso;
import com.saviasaludeps.savia.dominio.atencionusuario.AusSeguimiento;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCasoServicio;
import com.saviasaludeps.savia.dominio.atencionusuario.AusSolicitud;
import com.saviasaludeps.savia.ejb.entidades.AusAdjuntos;
import com.saviasaludeps.savia.ejb.entidades.AusCasoServicios;
import com.saviasaludeps.savia.ejb.entidades.AusCasos;
import com.saviasaludeps.savia.ejb.entidades.AusSeguimientos;
import com.saviasaludeps.savia.ejb.entidades.AusSolicitudes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.atencionusuario.AusAdjuntoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;


/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(AusAdjuntoRemoto.class)
@Local(AusAdjuntoLocal.class)
public class AusAdjuntoServicio extends GenericoServicio implements AusAdjuntoLocal, AusAdjuntoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(a) FROM Adjuntos a "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "casosId":
                            strQuery += "AND a.casosId = " + (String) e.getValue() + " ";
                            break;
                        case "serviciosId":
                            strQuery += "AND a.serviciosId = " + (String) e.getValue() + " ";
                            break;
                        case "seguimientosId":
                            strQuery += "AND a.seguimientosId =" + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

    @Override
    public List<AusAdjunto> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
       List<AusAdjunto> listResult = new ArrayList();
        try {
            String strQuery = "FROM Adjuntos a "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "casosId":
                            strQuery += "AND a.casosId = " + (String) e.getValue() + " ";
                            break;
                        case "serviciosId":
                            strQuery += "AND a.serviciosId = " + (String) e.getValue() + " ";
                            break;
                        case "seguimientosId":
                            strQuery += "AND a.seguimientosId =" + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "a." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "a.fechaHoraCrea DESC";
            }
            List<AusAdjuntos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AusAdjuntos per : list) {
                listResult.add(castEntidadNegocio(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public AusAdjunto consultar(int id) throws java.lang.Exception {
        AusAdjunto objRes = null;
        try {        
            AusAdjuntos adjuntodNegocio =  (AusAdjuntos) getEntityManager().find(AusAdjuntos.class, id);
            objRes = castEntidadNegocio(adjuntodNegocio);
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e, "Error al consultar un adjunto");
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public int insertar(AusAdjunto obj) throws java.lang.Exception {
        int id = 0;
        try { 
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al insertar un adjunto");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(AusAdjunto obj) throws java.lang.Exception {
       try {
            int id = 0;
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e, "Error al actualizar un adjunto");
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AusAdjunto eliminar(int id) throws java.lang.Exception {
        AusAdjunto obj = null;
        try {
            AusAdjuntos ent = getEntityManager().find(AusAdjuntos.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e, "Error al eliminar un adjunto");
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }
    
     public static AusAdjunto castEntidadNegocio(AusAdjuntos entidad){
        AusAdjunto adjunto = new AusAdjunto();
        AusCaso caso = new AusCaso();
        AusCasoServicio servicio = new AusCasoServicio();
        AusSeguimiento seguimiento = new AusSeguimiento();
        AusSolicitud solicitud = new AusSolicitud();
     
         if (entidad.getAusCasosId()!= null) {
             caso.setId(entidad.getAusCasosId().getId());
         }

         if (entidad.getAusServiciosId() != null) {
             servicio.setId(entidad.getAusServiciosId().getId());
         }

         if (entidad.getAusSeguimientosId() != null) {
             seguimiento.setId(entidad.getAusSeguimientosId().getId());
         }
         
         if (entidad.getAusSolicitudesId()!= null) {
             solicitud.setId(entidad.getAusSolicitudesId().getId());
         }
         adjunto.setId(entidad.getId());
         adjunto.setCasosId(caso);
         adjunto.setServiciosId(servicio);
         adjunto.setSeguimientosId(seguimiento);
         adjunto.setSolicitudId(solicitud);
         adjunto.setRuta(entidad.getRuta());
         adjunto.setArchivo(entidad.getArchivo());
         adjunto.setNombre(entidad.getNombre());
         adjunto.setUsuarioCrea(entidad.getUsuarioCrea());
         adjunto.setTerminalCrea(entidad.getTerminalCrea());
         adjunto.setFechaHoraCrea(entidad.getFechaHoraCrea());
         adjunto.setUsuarioModifica(entidad.getUsuarioModifica());
         adjunto.setTerminalModifica(entidad.getTerminalModifica());
         adjunto.setFechaHoraModifica(entidad.getFechaHoraModifica());
         return adjunto;  
     }
     
     public static AusAdjuntos castNegocioEntidad(AusAdjunto negocio){
        AusAdjuntos entidad = new AusAdjuntos();
        entidad.setId(negocio.getId());
        if( negocio.getCasosId() != null && negocio.getCasosId().getId() != null ){
             entidad.setAusCasosId(new AusCasos(negocio.getCasosId().getId()));
        }
        if( negocio.getServiciosId() != null && negocio.getServiciosId().getId() != null ){
             entidad.setAusServiciosId(new AusCasoServicios(negocio.getServiciosId().getId()));
        }
        if( negocio.getSeguimientosId() != null && negocio.getSeguimientosId().getId() != null ){
             entidad.setAusSeguimientosId(new AusSeguimientos(negocio.getSeguimientosId().getId()));
        }
        if( negocio.getSolicitudId()!= null && negocio.getSolicitudId().getId() != null ){
             entidad.setAusSolicitudesId(new AusSolicitudes(negocio.getSolicitudId().getId()));
        }
        entidad.setRuta(negocio.getRuta());
        entidad.setArchivo(negocio.getArchivo());
        entidad.setNombre(negocio.getNombre());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        return entidad;  
     }
     
   
    public static List<AusAdjunto> castEntidadNegocio(List<AusAdjuntos>  adjuntosNegocio){
           List<AusAdjunto> listaAdjuntos = new ArrayList();
           for (AusAdjuntos adjuntoNegocio : adjuntosNegocio) {
              listaAdjuntos.add(AusAdjuntoServicio.castEntidadNegocio(adjuntoNegocio));
           }
           return listaAdjuntos;  
    }
}
