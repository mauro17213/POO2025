/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitud;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimiento;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoGestion;
import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitudAdjunto;
import com.saviasaludeps.savia.dominio.autorizacion.AuTopeAfiliado;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Rescate;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo2Rescates;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos2;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos3;
import com.saviasaludeps.savia.ejb.entidades.AuCotizaciones;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudEntregas;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudes;
import com.saviasaludeps.savia.ejb.entidades.AuSeguimientoAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AuSeguimientoGestiones;
import com.saviasaludeps.savia.ejb.entidades.AuSeguimientos;
import com.saviasaludeps.savia.ejb.entidades.AuSolicitudAdjuntos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuSolicitudAdjuntoRemoto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.ejb.Remote;
import javax.persistence.NoResultException;

/**
 *
 * @author iavenegas
 */
@Stateless
@Remote(AuSolicitudAdjuntoRemoto.class)
public class AuSolicitudAdjuntoServicio extends GenericoServicio implements AuSolicitudAdjuntoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuSolicitudAdjuntos p "
                    + "WHERE p.id > 0";
            //2023-12-04 jyperez se adiciona uno de parámetros para discriminarlos por las foráneas que pueden relacionarse
            // paramConsulta1 = Seguimientos No usado por ahora
            // paramConsulta2 = SeguimientoAfiliados No usado por ahora
            // paramConsulta3 = Gestion Seguimiento
            if(paramConsulta.getParametroConsulta3() != null ) {
                strQuery+= " AND p.auSeguimientoGestionesId.id = " + paramConsulta.getParametroConsulta3() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "maeTipoArchivoId":
                            strQuery += "AND p.maeTipoArchivoId = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            cantidad = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            cantidad = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cantidad;
    }

    @Override
    public List<AuSolicitudAdjunto> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuSolicitudAdjunto> listaResultados = new ArrayList();
        try {
            String strQuery = "SELECT p FROM AuSolicitudAdjuntos p "
                    + "WHERE p.id > 0";
            //2023-12-04 jyperez se adiciona uno de parámetros para discriminarlos por las foráneas que pueden relacionarse
            // paramConsulta1 = Seguimientos No usado por ahora
            // paramConsulta2 = SeguimientoAfiliados No usado por ahora
            // paramConsulta3 = Gestion Seguimiento
            if(paramConsulta.getParametroConsulta3() != null ) {
                strQuery+= " AND p.auSeguimientoGestionesId.id = " + paramConsulta.getParametroConsulta3() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "maeTipoArchivoId":
                            strQuery += "AND p.maeTipoArchivoId = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<AuSolicitudAdjuntos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuSolicitudAdjuntos solicitudAdjunto : list) {
                listaResultados.add(castEntidadNegocio(solicitudAdjunto));
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
    public AuSolicitudAdjunto consultar(int id) throws Exception {
        AuSolicitudAdjunto objRes = null;
        try {
            objRes = castEntidadNegocio((AuSolicitudAdjuntos) getEntityManager().find(AuSolicitudAdjuntos.class, id));
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
    public int insertar(AuSolicitudAdjunto obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public AuSolicitudAdjunto eliminar(int id) throws Exception {
        AuSolicitudAdjunto obj = null;
        try {
            AuSolicitudAdjuntos ent = getEntityManager().find(AuSolicitudAdjuntos.class, id);
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
    public List<AuSolicitudAdjunto> listarAdjuntosByIdCotizacion(int idCotizacion) throws Exception {
        List<AuSolicitudAdjunto> adjuntos = new ArrayList();
        try {
            String strQuery = "SELECT p FROM AuSolicitudAdjuntos p "
                    + " WHERE p.auCotizacionesId.id =:auCotizacionesId"
                    + " AND p.auCotizacionesId.activo =1"
                    + " ORDER BY p.id DESC";

            List<AuSolicitudAdjuntos> list = getEntityManager().createQuery(strQuery)
                    .setParameter("auCotizacionesId", idCotizacion)
                    .getResultList();
            for (AuSolicitudAdjuntos adjunto : list) {
                adjuntos.add(castEntidadNegocio(adjunto));
            }
        } catch (NoResultException e) {
            adjuntos = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return adjuntos;
    }
    
    @Override
    public List<AuSolicitudAdjunto> listarAdjuntosByIdTopeAfiliado(int idTopeAfiliados) throws Exception {
        List<AuSolicitudAdjunto> adjuntos = new ArrayList();
        try {
            String strQuery = "SELECT p FROM AuSolicitudAdjuntos p "
                    + " WHERE p.auTopeAfiliadosId =:auTopeAfiliadosId"
                    + " ORDER BY p.id DESC";

            List<AuSolicitudAdjuntos> list = getEntityManager().createQuery(strQuery)
                    .setParameter("auTopeAfiliadosId", idTopeAfiliados)
                    .getResultList();
            for (AuSolicitudAdjuntos adjunto : list) {
                adjuntos.add(castEntidadNegocio(adjunto));
            }
        } catch (NoResultException e) {
            adjuntos = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return adjuntos;
    }
    
    @Override
    public List<AuSolicitudAdjunto> listarAdjuntosByIdNoSolicitudes(int idNoSolicitudes) throws Exception {
        List<AuSolicitudAdjunto> adjuntos = new ArrayList();
        try {
            String strQuery = "SELECT p FROM AuSolicitudAdjuntos p "
                    + " WHERE p.auNoSolicitudesId.id =:auNoSolicitudesId"
                    + " ORDER BY p.id DESC";

            List<AuSolicitudAdjuntos> list = getEntityManager().createQuery(strQuery)
                    .setParameter("auNoSolicitudesId", idNoSolicitudes)
                    .getResultList();
            for (AuSolicitudAdjuntos adjunto : list) {
                adjuntos.add(castEntidadNegocio(adjunto));
            }
        } catch (NoResultException e) {
            adjuntos = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return adjuntos;
    }
    
    @Override
    public List<AuSolicitudAdjunto> listarAdjuntosByIdNoSollicitudesEntregas(int idNoSolicitudesEntregas) throws Exception {
        List<AuSolicitudAdjunto> adjuntos = new ArrayList();
        try {
            String strQuery = "SELECT p FROM AuSolicitudAdjuntos p "
                    + " WHERE p.auNoSolicitudEntregasId.id =:auNoSolicitudEntregasId"
                    + " ORDER BY p.id DESC";

            List<AuSolicitudAdjuntos> list = getEntityManager().createQuery(strQuery)
                    .setParameter("auNoSolicitudEntregasId", idNoSolicitudesEntregas)
                    .getResultList();
            for (AuSolicitudAdjuntos adjunto : list) {
                adjuntos.add(castEntidadNegocio(adjunto));
            }
        } catch (NoResultException e) {
            adjuntos = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return adjuntos;
    }
    
    private static AuSolicitudAdjuntos castNegocioEntidad(AuSolicitudAdjunto negocio) {
        AuSolicitudAdjuntos entidad = new AuSolicitudAdjuntos();
        entidad.setId(negocio.getId());
        entidad.setOrigen(negocio.getOrigen());
        entidad.setMaeTipoArchivoId(negocio.getMaeTipoArchivoId());
        entidad.setMaeTipoArchivoCodigo(negocio.getMaeTipoArchivoCodigo());
        entidad.setMaeTipoArchivoValor(negocio.getMaeTipoArchivoValor());
        entidad.setNombre(negocio.getNombre());
        entidad.setRuta(negocio.getRuta());
        entidad.setArchivo(negocio.getArchivo());
        entidad.setExiste(negocio.isExiste());
        if (negocio.getAuAnexo2() != null && negocio.getAuAnexo2().getId() != null) {
            entidad.setAuAnexos2Id(new AuAnexos2(negocio.getAuAnexo2().getId()));
        }
        if (negocio.getAuAnexo3() != null && negocio.getAuAnexo3().getId() != null) {
            entidad.setAuAnexos3Id(new AuAnexos3(negocio.getAuAnexo3().getId()));
        }
        if (negocio.getAuCotizacion() != null && negocio.getAuCotizacion().getId() != null) {
            entidad.setAuCotizacionesId(new AuCotizaciones(negocio.getAuCotizacion().getId()));
        }
        if (negocio.getAuSeguimientoAfiliado() != null && negocio.getAuSeguimientoAfiliado().getId() != null) {
            entidad.setAuSeguimientoAfiliadoId(new AuSeguimientoAfiliados(negocio.getAuSeguimientoAfiliado().getId()));
        }
        if (negocio.getAuSeguimiento() != null && negocio.getAuSeguimiento().getId() != null) {
            entidad.setAuSeguimientosId(new AuSeguimientos(negocio.getAuSeguimiento().getId()));
        }
        if (negocio.getAuAnexo2Rescate() != null && negocio.getAuAnexo2Rescate().getId() != null) {
            entidad.setAuAnexo2RescatesId(new AuAnexo2Rescates(negocio.getAuAnexo2Rescate().getId()));
        }
        if(negocio.getAuTopeAfiliadosId() != null && negocio.getAuTopeAfiliadosId().getId() != null){
            entidad.setAuTopeAfiliadosId(negocio.getAuTopeAfiliadosId().getId());
        }
        //2023-12-04 jyperez nuevos campos
        if (negocio.getAuSeguimientoGestion() != null) {
            entidad.setAuSeguimientoGestionesId(new AuSeguimientoGestiones(negocio.getAuSeguimientoGestion().getId()));
        }
        if(negocio.getAuNoSolicitud() != null){
            entidad.setAuNoSolicitudesId(new AuNoSolicitudes(negocio.getAuNoSolicitud().getId()));
        }
        if(negocio.getAuNoSolicitudEntregasId() != null){
            entidad.setAuNoSolicitudEntregasId(new AuNoSolicitudEntregas(negocio.getAuNoSolicitudEntregasId().getId())); 
        }
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

    private static AuSolicitudAdjunto castEntidadNegocio(AuSolicitudAdjuntos entidad) {

        AuSolicitudAdjunto negocio = new AuSolicitudAdjunto();
        negocio.setId(entidad.getId());
        negocio.setOrigen(entidad.getOrigen());
        negocio.setMaeTipoArchivoId(entidad.getMaeTipoArchivoId());
        negocio.setMaeTipoArchivoCodigo(entidad.getMaeTipoArchivoCodigo());
        negocio.setMaeTipoArchivoValor(entidad.getMaeTipoArchivoValor());
        negocio.setNombre(entidad.getNombre());
        negocio.setRuta(entidad.getRuta());
        negocio.setArchivo(entidad.getArchivo());
        negocio.setExiste(entidad.getExiste());
        if (entidad.getAuAnexos2Id() != null) {
            negocio.setAuAnexo2(new AuAnexo2(entidad.getAuAnexos2Id().getId()));
        }
        if (entidad.getAuAnexos3Id() != null) {
            negocio.setAuAnexo3(new AuAnexo3(entidad.getAuAnexos3Id().getId()));
        }
        if (entidad.getAuCotizacionesId() != null) {
            negocio.setAuCotizacion(new AuCotizacion(entidad.getAuCotizacionesId().getId()));
        }
        if (entidad.getAuSeguimientoAfiliadoId() != null) {
            negocio.setAuSeguimientoAfiliado(new AuSeguimientoAfiliado(entidad.getAuSeguimientoAfiliadoId().getId()));
        }
        if (entidad.getAuSeguimientosId() != null) {
            negocio.setAuSeguimiento(new AuSeguimiento(entidad.getAuSeguimientosId().getId()));
        }
        if (entidad.getAuAnexo2RescatesId() != null) {
            negocio.setAuAnexo2Rescate(new AuAnexo2Rescate(entidad.getAuAnexo2RescatesId().getId()));
        }
        if (entidad.getAuTopeAfiliadosId() != null) {
            negocio.setAuTopeAfiliadosId(new AuTopeAfiliado(entidad.getAuTopeAfiliadosId()));
        }
        //2023-12-04 jyperez nuevos campos
        if (entidad.getAuSeguimientoGestionesId() != null) {
            negocio.setAuSeguimientoGestion(new AuSeguimientoGestion(entidad.getAuSeguimientoGestionesId().getId()));
        }
        if(entidad.getAuNoSolicitudesId() != null){
            negocio.setAuNoSolicitud(new AuNoSolicitud(entidad.getAuNoSolicitudesId().getId()));
        }
        
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }

    public static List<AuSolicitudAdjunto> casteoListaEntidadNegocio(List<AuSolicitudAdjuntos> listaEntidad) {
        List<AuSolicitudAdjunto> listaNegocio = new ArrayList();
        listaEntidad.forEach(entidad -> {
            listaNegocio.add(castEntidadNegocio(entidad));
        });
        return listaNegocio;
    }
}
