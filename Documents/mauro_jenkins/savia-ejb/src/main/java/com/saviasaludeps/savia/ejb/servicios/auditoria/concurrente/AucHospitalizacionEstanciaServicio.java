/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionEstancia;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizacionEstancias;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizaciones;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionEstanciaRemoto;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@Remote(AucHospitalizacionEstanciaRemoto.class)
public class AucHospitalizacionEstanciaServicio extends GenericoServicio implements AucHospitalizacionEstanciaRemoto {

    @Override
    public AucHospitalizacionEstancia consultar(int id) throws Exception {
        AucHospitalizacionEstancia objRes = null;
        try {
            objRes = castEntidadNegocio((AucHospitalizacionEstancias) getEntityManager().find(AucHospitalizacionEstancias.class, id));
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
    public int insertar(AucHospitalizacionEstancia obj) throws Exception {
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
    public void actualizar(AucHospitalizacionEstancia obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizacionEstancias a SET ";
            strQuery += "a.aucHospitalizacionesId.id = :aucHospitalizacionesId ,";
            strQuery += "a.fechaIngreso = :fechaIngreso ,";
            strQuery += "a.fechaEgreso = :fechaEgreso ,";
            strQuery += "a.dias = :dias ,";
            strQuery += "a.maeServicioId = :maeServicioId ,";
            strQuery += "a.maeServicioCodigo = :maeServicioCodigo ,";
            strQuery += "a.maeServicioValor = :maeServicioValor ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("aucHospitalizacionesId", obj.getAucHospitalizacionId().getId());
            query.setParameter("fechaIngreso", obj.getFechaIngreso());
            query.setParameter("fechaEgreso", obj.getFechaEgreso());
            query.setParameter("dias", obj.getDias());
            query.setParameter("maeServicioId", obj.getMaeServicioId());
            query.setParameter("maeServicioCodigo", obj.getMaeServicioCodigo());
            query.setParameter("maeServicioValor", obj.getMaeServicioValor());
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

    /* @Override
    public void actualizarEsctanciaDescripcion(AucHospitalizacionEstancia obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizacionEstancias a SET ";
            strQuery += "a.maeCausaEstanciaId = :maeCausaEstanciaId ,";
            strQuery += "a.maeCausaEstanciaCodigo = :maeCausaEstanciaCodigo ,";
            strQuery += "a.maeCausaEstanciaValor = :maeCausaEstanciaValor ,";
            strQuery += "a.maePropuestaIntervensionId = :maePropuestaIntervencionId ,";
            strQuery += "a.maePropuestaIntervensionCodigo = :maePropuestaIntervencionCodigo ,";
            strQuery += "a.maePropuestaIntervensionValor = :maePropuestaIntervencionValor ,";
            strQuery += "a.resumenClinico = :resumenClinico ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("maeCausaEstanciaId", obj.getMaeCausaEstanciaId());
            query.setParameter("maeCausaEstanciaCodigo", obj.getMaeCausaEstanciaCodigo());
            query.setParameter("maeCausaEstanciaValor", obj.getMaeCausaEstanciaValor());
            query.setParameter("maePropuestaIntervencionId", obj.getMaePropuestaIntervensionId());
            query.setParameter("maePropuestaIntervencionCodigo", obj.getMaePropuestaIntervensionCodigo());
            query.setParameter("maePropuestaIntervencionValor", obj.getMaePropuestaIntervensionValor());
            query.setParameter("resumenClinico", obj.getResumenClinico());
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
    }*/
    @Override
    public AucHospitalizacionEstancia eliminar(int id) throws Exception {
        AucHospitalizacionEstancia obj = null;
        try {
            AucHospitalizacionEstancias ent = getEntityManager().find(AucHospitalizacionEstancias.class, id);
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
    public int validarRangoFechasParaInsertar(Integer idHospitalizacion, Date fechaIngreso) throws Exception {
        int cant = 0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(fechaIngreso);
        try {
            String strQuery = "SELECT CASE "
                    + " WHEN p.fechaEgreso <= '" + strDate + "' THEN 0 ELSE 1 END "
                    + "FROM AucHospitalizacionEstancias p "
                    + "WHERE  p.aucHospitalizacionesId.id = " + idHospitalizacion + " "
                    + "ORDER BY p.fechaEgreso DESC ";
            //+ "LIMIT 1 ";

            cant = (int) getEntityManager().createQuery(strQuery)
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

    private AucHospitalizacionEstancia castEntidadNegocio(AucHospitalizacionEstancias entidad) {
        AucHospitalizacionEstancia negocio = new AucHospitalizacionEstancia();
        negocio.setId(entidad.getId());
        negocio.setAucHospitalizacionId(new AucHospitalizacion(entidad.getAucHospitalizacionesId().getId()));
        negocio.setFechaIngreso(entidad.getFechaIngreso());
        negocio.setFechaEgreso(entidad.getFechaEgreso());
        negocio.setDias(entidad.getDias());
        negocio.setMaeServicioId(entidad.getMaeServicioId());
        negocio.setMaeServicioCodigo(entidad.getMaeServicioCodigo());
        negocio.setMaeServicioValor(entidad.getMaeServicioValor());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }

    private AucHospitalizacionEstancias castNegocioEntidad(AucHospitalizacionEstancia negocio) {
        AucHospitalizacionEstancias entidad = new AucHospitalizacionEstancias();
        if (negocio.getAucHospitalizacionId() != null) {
            entidad.setAucHospitalizacionesId(new AucHospitalizaciones(negocio.getAucHospitalizacionId().getId()));
        }
        entidad.setFechaIngreso(negocio.getFechaIngreso());
        entidad.setFechaEgreso(negocio.getFechaEgreso());
        entidad.setDias(negocio.getDias());
        entidad.setMaeServicioId(negocio.getMaeServicioId());
        entidad.setMaeServicioCodigo(negocio.getMaeServicioCodigo());
        entidad.setMaeServicioValor(negocio.getMaeServicioValor());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

    @Override
    public List<AucHospitalizacionEstancia> consultarPorIdHospitalizacion(int idHospitalizacion) throws Exception {
        List<AucHospitalizacionEstancia> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AucHospitalizacionEstancias p "
                    + "WHERE p.aucHospitalizacionesId.id = " + idHospitalizacion + " ";

            List<AucHospitalizacionEstancias> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AucHospitalizacionEstancias estancia : list) {
                listaResultados.add(castEntidadNegocio(estancia));
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
    public List<AucHospitalizacionEstancia> consultarActivas() throws Exception {
        List<AucHospitalizacionEstancia> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AucHospitalizacionEstancias p "
                    + "WHERE p.fechaEgreso = NULL ";

            List<AucHospitalizacionEstancias> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AucHospitalizacionEstancias estancia : list) {
                listaResultados.add(castEntidadNegocio(estancia));
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
    public List<AucHospitalizacionEstancia> consultarActivasExcel(String idAfiliados, int sede) throws Exception {
        List<AucHospitalizacionEstancia> listaResultados = new ArrayList();
        try {
            String strQuery = "SELECT p FROM AucHospitalizacionEstancias p "
                    + "INNER JOIN p.aucHospitalizacionesId a "
                    + "WHERE p.fechaEgreso IS NULL "
                    + "AND a.cntPrestadorSedesId.id=:sede "
                    + "AND a.estado=:estado "
                    + "AND a.aucAfiliadosId.asegAfiliadosId.id NOT IN(" + idAfiliados + ")";

            List<AucHospitalizacionEstancias> list = getEntityManager().createQuery(strQuery)
                    .setParameter("sede", sede)
                    .setParameter("estado", AucHospitalizacion.ESTADO_AFILIADO_HOSPITALIZADO)
                    .getResultList();
            for (AucHospitalizacionEstancias estancia : list) {
                listaResultados.add(castEntidadNegocio(estancia));
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
    public AucHospitalizacionEstancia consultarUltimaHospitalizacionAfiliado(int idAfiliado) throws Exception {
        AucHospitalizacionEstancia estancia = null;
        try {
            String strQuery = "SELECT p FROM AucHospitalizacionEstancias p "
                    + "INNER JOIN p.aucHospitalizacionesId a "
                    + "WHERE a.aucAfiliadosId.asegAfiliadosId.id =:idAfiliado "
                    + "AND p.fechaEgreso IS NOT NULL "
                    + "AND a.estado<>:estado "
                    + "ORDER BY p.fechaEgreso DESC";

            AucHospitalizacionEstancias obj = (AucHospitalizacionEstancias) getEntityManager().createQuery(strQuery)
                    .setParameter("idAfiliado", idAfiliado)
                    .setParameter("estado", AucHospitalizacion.ESTADO_AFILIADO_ANULADO)
                    .setMaxResults(1)
                    .getSingleResult();
            estancia = castEntidadNegocio(obj);
        } catch (NoResultException e) {
            estancia = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return estancia;
    }

    @Override
    public void actualizarFechaEgreso(AucHospitalizacionEstancia obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucHospitalizacionEstancias a SET ";
            strQuery += "a.fechaEgreso = :fechaEgreso ,";
            strQuery += "a.dias = :dias ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("fechaEgreso", obj.getFechaEgreso());
            query.setParameter("dias", obj.getDias());
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

}
