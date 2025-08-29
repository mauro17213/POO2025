/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.gestionAtencion;

import com.saviasaludeps.savia.dominio.administracion.GnSede;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatAtencion;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeFuncionario;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeTaquilla;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTiempo;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTiquete;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatUsuario;
import com.saviasaludeps.savia.ejb.entidades.GatAtenciones;
import com.saviasaludeps.savia.ejb.entidades.GatSedeTaquillas;
import com.saviasaludeps.savia.ejb.entidades.GatTiempos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatReporteRemoto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author sgiraldv
 */
@Stateless
@Remote(GatReporteRemoto.class)
public class GatReporteServicio extends GenericoServicio implements GatReporteRemoto {

    private static final SimpleDateFormat formatoCorto = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public List<GatSedeTaquilla> listarTaquillasPorSede(int idSede) throws Exception {
        List<GatSedeTaquilla> listaResultado = new ArrayList<>();
        try {
            String strQuery = "FROM GatSedeTaquillas t "
                    + " WHERE t.gnSedesId.id = " + idSede
                    + " ORDER BY t.nombre ASC";
            List<GatSedeTaquillas> list = getEntityManager().createQuery(strQuery).getResultList();
            for (GatSedeTaquillas taquilla : list) {
                listaResultado.add(castEntidadNegocioSedeTaquilla(taquilla));
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
    public List<GatAtencion> listarAtencionesActivasPorTaquilla(int idTaquilla) throws Exception {
        List<GatAtencion> listaResultado = new ArrayList<>();
        try {
            String strQuery = "FROM GatAtenciones AS at "
                    + " WHERE at.gatTaquillasId.id = " + idTaquilla + " "
                    + " AND at.estado IN(0,1) ";
            List<GatAtenciones> list = getEntityManager().createQuery(strQuery).getResultList();
            for (GatAtenciones atencion : list) {
                listaResultado.add(castEntidadNegocioAtencion(atencion));
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
    public List<GatAtencion> listarAtencionesActivasPorSede(int idTaquilla, Date fecha) throws Exception {
        List<GatAtencion> listaResultado = new ArrayList<>();
        try {
            String strQuery = "FROM GatAtenciones AS at "
                    + " WHERE at.gnSedesId.id = " + idTaquilla + " "
                    + " AND DATE(at.fechaHoraCrea) = '" + formatoCorto.format(fecha) + "' ";
            List<GatAtenciones> list = getEntityManager().createQuery(strQuery).getResultList();
            for (GatAtenciones atencion : list) {
                listaResultado.add(castEntidadNegocioAtencion(atencion));
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
    public GatTiempo reposoActivo(int idUsuario) throws Exception {
        GatTiempo obj;
        String strQuery = "FROM GatTiempos AS tiem "
                + " WHERE tiem.gnUsuariosId.id = :usuario "
                + " AND tiem.activo = 1 ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("usuario", idUsuario);
            GatTiempos ent = (GatTiempos) query.getSingleResult();
            obj = new GatTiempo();
            obj.setId(ent.getId());
            obj.setFechaInicio(ent.getFechaInicio());
            obj.setFechaFin(ent.getFechaFin());
            obj.setTipo(ent.getTipo());
            obj.setTiempoTotal(ent.getTiempoTotal());
//            obj.setServicioUmbral(new GatServicioUmbral());
        } catch (Exception e) {
            obj = null;
        }
        return obj;
    }
    
//    @Override
//    public List<GatReporteServicio> listarReporteServicio(int idSede) throws Exception {
//        List<GatReporteServicio> listaResultado = new ArrayList<>();
//        try {
//            String strQuery = "FROM GatSedeTaquillas t "
//                    + " WHERE t.gnSedesId.id = " + idSede
//                    + " ORDER BY t.nombre ASC";
//            List<GatSedeTaquillas> list = getEntityManager().createQuery(strQuery).getResultList();
//            for (GatSedeTaquillas taquilla : list) {
//                listaResultado.add(castEntidadNegocioSedeTaquilla(taquilla));
//            }
//        } catch (NoResultException e) {
//            listaResultado = new ArrayList<>();
//        } catch (Exception e) {
//            Exception(CONSULTAR, e);
//        } finally {
//            cerrarEntityManager();
//        }
//        return listaResultado;
//    }

    private GatSedeTaquilla castEntidadNegocioSedeTaquilla(GatSedeTaquillas entidad) {
        GatSedeTaquilla negocio = new GatSedeTaquilla();
        negocio.setId(entidad.getId());
        negocio.setGnSedeId(new GnSede(entidad.getGnSedesId().getId()));
        negocio.getGnSedeId().setNombre(entidad.getGnSedesId().getNombre());
        negocio.setNombre(entidad.getNombre());
        negocio.setActivo(entidad.getActivo());
        negocio.setDisponible(entidad.getDisponible());
        if (entidad.getGnUsuariosId() != null) {
            negocio.setUsuarioId(new Usuario(entidad.getGnUsuariosId().getId()));
            negocio.getUsuarioId().setNombre(entidad.getGnUsuariosId().getNombre());
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

    private GatAtencion castEntidadNegocioAtencion(GatAtenciones entidad) {
        GatAtencion negocio = new GatAtencion();
        negocio.setGnSede(new GnSede(entidad.getGnSedesId().getId()));
        negocio.setId(entidad.getId());
        negocio.setGatTaquilla(new GatSedeTaquilla(entidad.getGatTaquillasId().getId()));
        negocio.getGatTaquilla().setNombre(entidad.getGatTaquillasId().getNombre());
        negocio.setGatSedeFuncionario(new GatSedeFuncionario(entidad.getGatSedeFuncionariosId().getId()));
        negocio.getGatSedeFuncionario().setActivo(entidad.getGatSedeFuncionariosId().getActivo());
        if (entidad.getGatUsuariosId() != null) {
//            negocio.setGatUsuario(new GatUsuario(entidad.getGatUsuariosId().getId(), entidad.getGatUsuariosId().getNombres(), entidad.getGatUsuariosId().getApellidos()));
            GatUsuario gatUsuario = new GatUsuario();
            gatUsuario.setId(entidad.getGatUsuariosId().getId());
            gatUsuario.setNombres(entidad.getGatUsuariosId().getNombres());
            gatUsuario.setApellidos(entidad.getGatUsuariosId().getApellidos());
            negocio.setGatUsuario(gatUsuario);
        }
        if (entidad.getGnUsuariosId() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(entidad.getGnUsuariosId().getId());
            usuario.setUsuario(entidad.getGnUsuariosId().getUsuario());
            usuario.setNombre(entidad.getGnUsuariosId().getNombre());
            negocio.setUsuario(usuario);
        }
        if (entidad.getGatTiquetesId() != null) {
            GatTiquete tikete = new GatTiquete();
            tikete.setId(entidad.getGatTiquetesId().getId());
            tikete.setNumero(entidad.getGatTiquetesId().getNumero());
            tikete.setMaeTipoServicio(entidad.getGatTiquetesId().getMaeTipoServicioId());
            tikete.setMaeTipoServicioCodigo(entidad.getGatTiquetesId().getMaeTipoServicioCodigo());
            tikete.setMaeTipoServicioValor(entidad.getGatTiquetesId().getMaeTipoServicioValor());
            tikete.setPrioritario(entidad.getGatTiquetesId().getPrioritario());
            tikete.setNumero(entidad.getGatTiquetesId().getNumero());
            tikete.setEstado(entidad.getGatTiquetesId().getEstado());
            negocio.setGatTiquete(tikete);
        }
//        negocio.setGatTiquete(new GatTiquete(entidad.getGatTiquetesId().getId(), entidad.getGatTiquetesId().getNumero(), entidad.getGatTiquetesId().getMaeTipoServicioId()));
//        negocio.getGatTiquete().setPrioritario(entidad.getGatTiquetesId().getPrioritario());
//        negocio.getGatTiquete().setMaeTipoServicioCodigo(entidad.getGatTiquetesId().getMaeTipoServicioCodigo());
//        negocio.getGatTiquete().setMaeTipoServicioValor(entidad.getGatTiquetesId().getMaeTipoServicioValor());
        negocio.setFechaHoraTiquete(entidad.getFechaHoraTiquete());
        negocio.setFechaHoraInicio(entidad.getFechaHoraInicio());
        negocio.setFechaHoraCancela(entidad.getFechaHoraCancela());
        negocio.setFechaHoraFin(entidad.getFechaHoraFin());
        negocio.setFechaHoraCalificacion(entidad.getFechaHoraCalificacion());
        negocio.setEstado(entidad.getEstado());
        //Auditoria
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }

}
