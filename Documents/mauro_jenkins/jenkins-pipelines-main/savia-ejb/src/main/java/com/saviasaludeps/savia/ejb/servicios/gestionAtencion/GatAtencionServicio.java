/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.gestionAtencion;

import com.saviasaludeps.savia.dominio.administracion.GnSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatAtencion;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeFuncionario;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeTaquilla;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTiquete;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatUsuario;
import com.saviasaludeps.savia.ejb.entidades.GatAtenciones;
import com.saviasaludeps.savia.ejb.entidades.GatSedeFuncionarios;
import com.saviasaludeps.savia.ejb.entidades.GatSedeTaquillas;
import com.saviasaludeps.savia.ejb.entidades.GatTiquetes;
import com.saviasaludeps.savia.ejb.entidades.GatUsuarios;
import com.saviasaludeps.savia.ejb.entidades.GnSedes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatAtencionRemoto;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author sgiraldv
 */
@Stateless
@Remote(GatAtencionRemoto.class)
public class GatAtencionServicio extends GenericoServicio implements GatAtencionRemoto {
    
    private static final SimpleDateFormat formatoCorto = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GatAtenciones c "
                    + " WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id LIKE '" + e.getValue() + "%' ";
                            break;
                        case "gatTaquillaId.nombre":
                            strQuery += "AND c.gatTaquillasId.nombre LIKE '" + e.getValue() + "%' ";
                            break;
                        case "gatSedeFuncionarioId.nombre":
                            strQuery += "AND c.gatSedeFuncionarios.nombre LIKE '" + e.getValue() + "%' ";
                            break;
                        case "gatUsuarioId.nombre":
                            strQuery += "AND c.gatUsuariosId.nombre LIKE '" + e.getValue() + "%' ";
                            break;
                        case "gatSedeId.nombre":
                            strQuery += "AND c.gatSedesId.nombre LIKE '" + e.getValue() + "%' ";
                            break;
                        case "gatTiqueteId.nombre":
                            strQuery += "AND c.gatTiquetesId.nombre LIKE '" + e.getValue() + "%' ";
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
    public List<GatAtencion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GatAtencion> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM GatAtenciones c WHERE 1 = 1 ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id LIKE '" + e.getValue() + "%' ";
                            break;
                        case "gatTaquillaId.nombre":
                            strQuery += "AND c.gatTaquillasId.nombre LIKE '" + e.getValue() + "%' ";
                            break;
                        case "gatSedeFuncionarioId.nombre":
                            strQuery += "AND c.gatSedeFuncionariosId.nombre LIKE '" + e.getValue() + "%' ";
                            break;
                        case "gatUsuarioId.numeroDocumento":
                            strQuery += "AND c.gatUsuariosId.numeroDocumento LIKE '" + e.getValue() + "%' ";
                            break;
                        case "gnSedeId.nombre":
                            strQuery += "AND c.gnSedesId.nombre LIKE '" + e.getValue() + "%' ";
                            break;
                        case "gatTiqueteId.valor":
                            strQuery += "AND c.gatTiqueteId.valor  LIKE '" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "c." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "c.id DESC";
            }
            List<GatAtenciones> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GatAtenciones atenciones : list) {
                listaResultados.add(castEntidadNegocio(atenciones));
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
    public GatAtencion consultar(int id) throws Exception {
        GatAtencion objRes = null;
        try {
            objRes = castEntidadNegocio((GatAtenciones) getEntityManager().find(GatAtenciones.class, id));
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
    public int insertar(GatAtencion obj) throws Exception {
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
    public void actualizar(GatAtencion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GatAtenciones a SET ";
            strQuery += "a.gatTaquillasId.id = :gatTaquillasId ,";
            strQuery += "a.gatSedeFuncionariosId.id = :gatSedeFuncionariosId ,";
            if (obj.getGatUsuario() != null) {
                strQuery += "a.gatUsuariosId.id = :gatUsuariosId ,";
            }
            strQuery += "a.gnSedesId.id = :gnSedesId ,";
            strQuery += "a.estado = :estado ,";
            strQuery += "a.gatTiquetesId.id = :gatTiquetesId ,";
            strQuery += "a.fechaHoraTiquete = :fechaHoraTiquete ,";
            strQuery += "a.fechaHoraInicio = :fechaHoraInicio ,";
            strQuery += "a.fechaHoraCancela = :fechaHoraCancela ,";
            strQuery += "a.fechaHoraFin = :fechaHoraFin ,";
            strQuery += "a.fechaHoraCalificacion = :fechaHoraCalificacion ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("gatTaquillasId", obj.getGatTaquilla().getId());
            query.setParameter("gatSedeFuncionariosId", obj.getGatSedeFuncionario().getId());
            if (obj.getGatUsuario() != null) {
                query.setParameter("gatUsuariosId", obj.getGatUsuario().getId());
            }
            query.setParameter("gnSedesId", obj.getGnSede().getId());
            query.setParameter("gatTiquetesId", obj.getGatTiquete().getId());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("fechaHoraTiquete", obj.getFechaHoraTiquete());
            query.setParameter("fechaHoraInicio", obj.getFechaHoraInicio());
            query.setParameter("fechaHoraCancela", obj.getFechaHoraCancela());
            query.setParameter("fechaHoraFin", obj.getFechaHoraFin());
            query.setParameter("fechaHoraCalificacion", obj.getFechaHoraCalificacion());
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
    public GatAtencion eliminar(int id) throws Exception {
        GatAtencion obj = null;
        try {
            GatAtenciones ent = getEntityManager().find(GatAtenciones.class, id);
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

    private GatAtencion castEntidadNegocio(GatAtenciones entidad) {
        GatAtencion negocio = new GatAtencion();
        negocio.setId(entidad.getId());
        negocio.setGatTaquilla(new GatSedeTaquilla(entidad.getGatTaquillasId().getId()));
        negocio.getGatTaquilla().setNombre(entidad.getGatTaquillasId().getNombre());
        negocio.setGatSedeFuncionario(new GatSedeFuncionario(entidad.getGatSedeFuncionariosId().getId()));
        negocio.getGatSedeFuncionario().setActivo(entidad.getGatSedeFuncionariosId().getActivo());
        if (entidad.getGatUsuariosId()!= null) {
            negocio.setGatUsuario(new GatUsuario(entidad.getGatUsuariosId().getId(), entidad.getGatUsuariosId().getNombres(), entidad.getGatUsuariosId().getApellidos()));
        }
        negocio.setGnSede(new GnSede(entidad.getGnSedesId().getId()));
        negocio.setGatTiquete(new GatTiquete(entidad.getGatTiquetesId().getId(), entidad.getGatTiquetesId().getNumero(), entidad.getGatTiquetesId().getMaeTipoServicioId()));
        negocio.getGatTiquete().setPrioritario(entidad.getGatTiquetesId().getPrioritario());
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
        return negocio;
    }

    private GatAtenciones castNegocioEntidad(GatAtencion negocio) {
        GatAtenciones entidad = new GatAtenciones();
        entidad.setId(entidad.getId());
        entidad.setGatTaquillasId(new GatSedeTaquillas(negocio.getGatTaquilla().getId()));
        entidad.setGatSedeFuncionariosId(new GatSedeFuncionarios(negocio.getGatSedeFuncionario().getId()));
        if (negocio.getGatUsuario() != null && negocio.getGatUsuario().getId() != null) {
            entidad.setGatUsuariosId(new GatUsuarios(negocio.getGatUsuario().getId()));
        }
        entidad.setGnSedesId(new GnSedes(negocio.getGnSede().getId()));
        entidad.setGatTiquetesId(new GatTiquetes(negocio.getGatTiquete().getId()));
        entidad.setFechaHoraTiquete(negocio.getFechaHoraTiquete());
        entidad.setFechaHoraInicio(negocio.getFechaHoraInicio());
        entidad.setFechaHoraCancela(negocio.getFechaHoraCancela());
        entidad.setFechaHoraFin(negocio.getFechaHoraFin());
        entidad.setFechaHoraCalificacion(negocio.getFechaHoraCalificacion());
        entidad.setEstado(negocio.getEstado());
        //Auditoria
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        return entidad;
    }

    @Override
    public GatAtencion consultarPorTiqueteYTaquilla(int idTiquete, int idTaquilla) throws Exception {
        GatAtencion obj = new GatAtencion();
        try {
            String strQuery = "FROM GatAtenciones c "
                    + " WHERE c.gatTiquetesId.id = " + idTiquete
                    + " AND c.gatTaquillasId.id = " + idTaquilla
                    + " ORDER BY c.id DESC";
            List<GatAtenciones> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if (list != null) {
                for (GatAtenciones atencion : list) {
                    obj = castEntidadNegocio(atencion);
                    break;
                }
            }
        } catch (NoResultException e) {
            obj = new GatAtencion();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public List<GatAtencion> listarActivasPorFuncionarioYTaquilla(int idFuncionario, int idTaquilla) throws Exception {
        List<GatAtencion> objResult = new ArrayList<>();
        try {
            String strQuery = "FROM GatAtenciones c "
                    + " WHERE c.gatSedeFuncionariosId.id = " + idFuncionario
                    + " AND c.gatTaquillasId.id = " + idTaquilla
                    + " AND c.fechaHoraFin is NULL"
                    + " ORDER BY c.id DESC";
            List<GatAtenciones> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if (list != null) {
                for (GatAtenciones atencion : list) {
                    objResult.add(castEntidadNegocio(atencion));
                }
            }
        } catch (NoResultException e) {
            objResult = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objResult;
    }

    @Override
    public Integer consultarTipoFecha(int estado, String fecha, int idUsuario) throws java.lang.Exception {
        Integer cantidad = 0;
        StringBuilder strQuery = new StringBuilder("select COUNT(id) as cantidad  from gat_atenciones ga ");
        strQuery.append(" where ga.gat_sede_funcionarios_id = :usuario and ga.estado = :estado  ");
        strQuery.append(" and DATE_FORMAT(fecha_hora_tiquete , '%Y-%m-%d') = :fecha  ");
        try {
            BigInteger resul = (BigInteger) getEntityManager().createNativeQuery(strQuery.toString())
                    .setParameter("usuario", idUsuario)
                    .setParameter("estado", estado)
                    .setParameter("fecha", fecha)
                    .setMaxResults(1)
                    .getSingleResult();
            if (resul != null) {
                cantidad = resul.intValue();
            }
        } catch (NoResultException e) {
            cantidad = 0;
        }
        return cantidad;
    }

    @Override
    public Integer consultarSobreAtendidos(String fecha, int idUsuario) throws java.lang.Exception {
        Integer cantidad = 0;
        StringBuilder strQuery = new StringBuilder("select COUNT(ga.id) cantidad from  gat_atenciones ga ");
        strQuery.append(" inner join gat_tiquetes gt on ga.gat_tiquetes_id = gt.id and ga.estado = 2 and ga.gat_sede_funcionarios_id = :usuario ");
        strQuery.append(" INNER join gat_servicio_umbrales gsu on gt.mae_tipo_servicio_id = gsu.mae_tipo_servicio_id ");
        strQuery.append(" where DATE_FORMAT(fecha_hora_inicio , '%Y-%m-%d') = :fecha AND TIMESTAMPDIFF(MINUTE,fecha_hora_inicio ,fecha_hora_fin) > gsu.tiempo  ");
        try {
            BigInteger resul = (BigInteger) getEntityManager().createNativeQuery(strQuery.toString())
                    .setParameter("usuario", idUsuario)
                    .setParameter("fecha", fecha)
                    .setMaxResults(1)
                    .getSingleResult();
            if (resul != null) {
                cantidad = resul.intValue();
            }
        } catch (NoResultException e) {
            cantidad = 0;
        }
        return cantidad;
    }

    @Override
    public int consultarTotalAtentidosHoy(int idSede) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GatAtenciones c "
                    + " WHERE c.estado = 2 and c.gnSedesId.id = :idSede and c.fechaHoraCrea >= '" + formatoCorto.format(new Date()) + " 00:00:00' ";
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("idSede", idSede)
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

    @Override
    public int consultarTotalAbandonadosHoy(int idSede) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GatAtenciones c "
                    + " WHERE c.estado = 3 and c.gnSedesId.id = :idSede and c.fechaHoraCrea >= '" + formatoCorto.format(new Date()) + " 00:00:00' ";
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("idSede", idSede)
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

    @Override
    public int consultarTotalSobreAtendidosHoy(int idSede) throws Exception {
        Integer cantidad = 0;
        StringBuilder strQuery = new StringBuilder("select COUNT(ga.id) cantidad from  gat_atenciones ga ");
        strQuery.append(" inner join gat_tiquetes gt on ga.gat_tiquetes_id = gt.id and ga.estado = 2 and ga.gn_sedes_id = :sede ");
        strQuery.append(" INNER join gat_servicio_umbrales gsu on gt.mae_tipo_servicio_id = gsu.mae_tipo_servicio_id ");
        strQuery.append(" where DATE_FORMAT(fecha_hora_inicio , '%Y-%m-%d') = :fecha AND TIMESTAMPDIFF(MINUTE,fecha_hora_inicio ,fecha_hora_fin) > gsu.tiempo  ");
        try {
            BigInteger resul = (BigInteger) getEntityManager().createNativeQuery(strQuery.toString())
                    .setParameter("sede", idSede)
                    .setParameter("fecha", formatoCorto.format(new Date()))
                    .setMaxResults(1)
                    .getSingleResult();
            if (resul != null) {
                cantidad = resul.intValue();
            }
        } catch (Exception e) {
            cantidad = 0;
        }
        return cantidad;
    }

    @Override
    public int consultarTotalAtendidoTaquilla(int idTaquilla) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GatAtenciones c "
                    + " WHERE c.estado = 2 and c.gatTaquillasId.id = :idTaquilla and c.fechaHoraCrea >= '" + formatoCorto.format(new Date()) + " 00:00:00' ";
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("idTaquilla", idTaquilla)
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

    @Override
    public int consultaTotalUsuariosTaquilla(int idTaquilla) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c.gatUsuariosId.id) FROM GatAtenciones c "
                    + " WHERE c.estado = 1 and c.gatTaquillasId.id = :idTaquilla and c.fechaHoraCrea >= '" + formatoCorto.format(new Date()) + " 00:00:00' ";
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("idTaquilla", idTaquilla)
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

    @Override
    public int consultarTotalAbandonosTaquilla(int idTaquilla) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GatAtenciones c "
                    + " WHERE c.estado = 3 and c.gatTaquillasId.id = :idTaquilla and c.fechaHoraCrea >= '" + formatoCorto.format(new Date()) + " 00:00:00' ";
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("idTaquilla", idTaquilla)
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

    @Override
    public int calcularPromedioHoy(Integer idSede, Integer idTaquilla) throws Exception {
        Integer cantidad = 0;
        StringBuilder strQuery = new StringBuilder("SELECT AVG(TIMESTAMPDIFF(MINUTE, fecha_hora_inicio, fecha_hora_fin)) AS promedio FROM gat_atenciones WHERE ");
        if (idSede != null) {
            strQuery.append(" gn_sedes_id = " + idSede + " ");
        } else {
            strQuery.append(" gat_taquillas_id = " + idTaquilla + " ");
        }
        strQuery.append(" AND estado = 2 AND fecha_hora_inicio IS NOT NULL  AND fecha_hora_fin IS NOT NULL");
        strQuery.append(" AND DATE_FORMAT(fecha_hora_crea , '%Y-%m-%d') = :fecha ");
        try {
            BigInteger resul = (BigInteger) getEntityManager().createNativeQuery(strQuery.toString())
                    .setParameter("fecha", formatoCorto.format(new Date()))
                    .setMaxResults(1)
                    .getSingleResult();
            if (resul != null) {
                cantidad = resul.intValue();
            }
        } catch (Exception e) {
            cantidad = 0;
        }
        return cantidad;
    }

    @Override
    public int consultarTotalAtencionesPorEstadoYSedeHoy(int estado, int idSede) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GatAtenciones c "
                    + " WHERE c.estado = :estado and c.gnSedesId.id = :idSede and c.fechaHoraCrea >= '" + formatoCorto.format(new Date()) + " 00:00:00' ";
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("estado", estado)
                    .setParameter("idSede", idSede)
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

    @Override
    public int consultarTotalSobreatendidoPorFecha(int idSede, Date fecha) throws Exception {
        Integer cantidad = 0;
        StringBuilder strQuery = new StringBuilder("select COUNT(ga.id) cantidad from  gat_atenciones ga ");
        strQuery.append(" inner join gat_tiquetes gt on ga.gat_tiquetes_id = gt.id and ga.estado = 2 and ga.gn_sedes_id = :sede ");
        strQuery.append(" INNER join gat_servicio_umbrales gsu on gt.mae_tipo_servicio_id = gsu.mae_tipo_servicio_id ");
        strQuery.append(" where DATE_FORMAT(fecha_hora_inicio , '%Y-%m-%d') = :fecha AND TIMESTAMPDIFF(MINUTE,fecha_hora_inicio ,fecha_hora_fin) > gsu.tiempo  ");
        try {
            BigInteger resul = (BigInteger) getEntityManager().createNativeQuery(strQuery.toString())
                    .setParameter("sede", idSede)
                    .setParameter("fecha", formatoCorto.format(fecha))
                    .setMaxResults(1)
                    .getSingleResult();
            if (resul != null) {
                cantidad = resul.intValue();
            }
        } catch (Exception e) {
            cantidad = 0;
        }
        return cantidad;
    }

    @Override
    public int calcularPromedioPorFecha(Integer idSede, Date fecha) throws Exception {
        Integer cantidad = 0;
        StringBuilder strQuery = new StringBuilder("SELECT AVG(TIMESTAMPDIFF(MINUTE, fecha_hora_inicio, fecha_hora_fin)) AS promedio FROM gat_atenciones WHERE ");
        strQuery.append(" gn_sedes_id = " + idSede + " ");
        strQuery.append(" AND estado = 2 AND fecha_hora_inicio IS NOT NULL  AND fecha_hora_fin IS NOT NULL");
        strQuery.append(" AND DATE_FORMAT(fecha_hora_crea , '%Y-%m-%d') = :fecha ");
        try {
            BigInteger resul = (BigInteger) getEntityManager().createNativeQuery(strQuery.toString())
                    .setParameter("fecha", formatoCorto.format(fecha))
                    .setMaxResults(1)
                    .getSingleResult();
            if (resul != null) {
                cantidad = resul.intValue();
            }
        } catch (Exception e) {
            cantidad = 0;
        }
        return cantidad;
    }

}
