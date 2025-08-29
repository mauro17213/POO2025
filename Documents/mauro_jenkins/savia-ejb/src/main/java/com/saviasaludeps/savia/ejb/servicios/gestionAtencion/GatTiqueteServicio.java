/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.gestionAtencion;

import com.saviasaludeps.savia.dominio.administracion.GnSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTiquete;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatUsuario;
import com.saviasaludeps.savia.ejb.entidades.GatAtenciones;
import com.saviasaludeps.savia.ejb.entidades.GatTiquetes;
import com.saviasaludeps.savia.ejb.entidades.GnSedes;
import com.saviasaludeps.savia.ejb.entidades.GatUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatTiqueteRemoto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author acuartas
 */
@Stateless
@Remote(GatTiqueteRemoto.class)
public class GatTiqueteServicio extends GenericoServicio implements GatTiqueteRemoto {

    private static final SimpleDateFormat formatoCorto = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat formatoLargo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GatTiquetes c "
                    + " WHERE 1 = 1  and c.estado = 0  ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND c.gnSedesId.id = " + paramConsulta.getParametroConsulta1();
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += " AND c.fechaHoraCrea >= '" + formatoCorto.format(paramConsulta.getParametroConsulta2()) + " 00:00:00' ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "numero":
                            strQuery += " AND c.numero LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoServicio":
                            strQuery += " AND c.maeTipoServicioId = " + e.getValue() + " ";
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
    public List<GatTiquete> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GatTiquete> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM GatTiquetes c WHERE 1 = 1  and c.estado = 0  ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND c.gnSedesId.id = " + paramConsulta.getParametroConsulta1();
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += " AND c.fechaHoraCrea >= '" + formatoCorto.format(paramConsulta.getParametroConsulta2()) + " 00:00:00' ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "numero":
                            strQuery += " AND c.numero LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoServicio":
                            strQuery += " AND c.maeTipoServicioId = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "c." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "c.id DESC";
            }
            List<GatTiquetes> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GatTiquetes sedeTaquilla : list) {
                listaResultados.add(castEntidadNegocio(sedeTaquilla));
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
    public GatTiquete consultar(int id) throws Exception {
        GatTiquete objRes = null;
        try {
            objRes = castEntidadNegocio((GatTiquetes) getEntityManager().find(GatTiquetes.class, id));
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
    public int insertar(GatTiquete obj) throws Exception {
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
    public void actualizar(GatTiquete obj) throws java.lang.Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GatTiquetes a SET ";
            strQuery += "a.gnSedesId.id = :gnSedesId ,";
            if (obj.getGatUsuario() != null && obj.getGatUsuario().getId() != null) {
                strQuery += "a.gatUsuariosId.id = :gatUsuariosId ,";
            }
            strQuery += "a.maeTipoServicioId = :maeTipoServicioId ,";
            strQuery += "a.maeTipoServicioCodigo = :maeTipoServicioCodigo ,";
            strQuery += "a.maeTipoServicioValor = :maeTipoServicioValor, ";
            strQuery += "a.numero = :numero, ";
            strQuery += "a.estado = :estado, ";
            strQuery += "a.prioritario =:prioritario, ";
            strQuery += "a.fechaHoraLlamado = :fechaHoraLlamado, ";
            strQuery += "a.fechaHoraAtendido = :fechaHoraAtendido, ";
            strQuery += "a.fechaHoraFinaliza = :fechaHoraFinaliza, ";
            strQuery += "a.fechaHoraAbandona = :fechaHoraAbandona ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("gnSedesId", obj.getGnSede().getId());
            if (obj.getGatUsuario() != null && obj.getGatUsuario().getId() != null) {
                query.setParameter("gatUsuariosId", obj.getGatUsuario().getId());
            }
            query.setParameter("maeTipoServicioId", obj.getMaeTipoServicio());
            query.setParameter("maeTipoServicioCodigo", obj.getMaeTipoServicioCodigo());
            query.setParameter("maeTipoServicioValor", obj.getMaeTipoServicioValor());
            query.setParameter("numero", obj.getNumero());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("prioritario", obj.getPrioritario());
            query.setParameter("fechaHoraLlamado", obj.getFechaHoraLlamado());
            query.setParameter("fechaHoraAtendido", obj.getFechaHoraAtendido());
            query.setParameter("fechaHoraFinaliza", obj.getFechaHoraFinaliza());
            query.setParameter("fechaHoraAbandona", obj.getFechaHoraAbandona());
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
    public GatTiquete eliminar(int id) throws java.lang.Exception {
        GatTiquete obj = null;
        try {
            GatTiquetes ent = getEntityManager().find(GatTiquetes.class, id);
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

    private GatTiquete castEntidadNegocio(GatTiquetes entidad) {
        GatTiquete negocio = new GatTiquete();
        negocio.setId(entidad.getId());
        negocio.setGnSede(new GnSede(entidad.getGnSedesId().getId()));
        if (entidad.getGatUsuariosId() != null) {
            negocio.setGatUsuario(new GatUsuario(entidad.getGatUsuariosId().getId(), entidad.getGatUsuariosId().getNombres(), entidad.getGatUsuariosId().getApellidos()));
        }
        negocio.setMaeTipoServicio(entidad.getMaeTipoServicioId());
        negocio.setMaeTipoServicioCodigo(entidad.getMaeTipoServicioCodigo());
        negocio.setMaeTipoServicioValor(entidad.getMaeTipoServicioValor());
        negocio.setNumero(entidad.getNumero());
        negocio.setEstado(entidad.getEstado());
        negocio.setFechaHoraLlamado(entidad.getFechaHoraLlamado());
        negocio.setFechaHoraAtendido(entidad.getFechaHoraAtendido());
        negocio.setFechaHoraAbandona(entidad.getFechaHoraAbandona());
        negocio.setFechaHoraFinaliza(entidad.getFechaHoraFinaliza());
        negocio.setPrioritario(entidad.getPrioritario());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        return negocio;
    }

    private GatTiquete castEntidadNegocioHistorico(GatTiquetes entidad) {
        GatTiquete negocio = new GatTiquete();
        negocio.setId(entidad.getId());
        negocio.setEstado(entidad.getEstado());
        negocio.setFechaHoraLlamado(entidad.getFechaHoraLlamado());
        negocio.setFechaHoraAtendido(entidad.getFechaHoraAtendido());
        negocio.setFechaHoraAbandona(entidad.getFechaHoraAbandona());
        negocio.setFechaHoraFinaliza(entidad.getFechaHoraFinaliza());
        //Auditoria
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }

    private GatTiquetes castNegocioEntidad(GatTiquete negocio) {
        GatTiquetes entidad = new GatTiquetes();
        entidad.setId(negocio.getId());
        entidad.setGnSedesId(new GnSedes(negocio.getGnSede().getId()));
        if (negocio.getGatUsuario() != null && negocio.getGatUsuario().getId() != null) {
            entidad.setGatUsuariosId(new GatUsuarios(negocio.getGatUsuario().getId()));
        }
        entidad.setMaeTipoServicioId(negocio.getMaeTipoServicio());
        entidad.setMaeTipoServicioCodigo(negocio.getMaeTipoServicioCodigo());
        entidad.setMaeTipoServicioValor(negocio.getMaeTipoServicioValor());
        entidad.setNumero(negocio.getNumero());
        entidad.setEstado(negocio.getEstado());
        entidad.setFechaHoraLlamado(negocio.getFechaHoraLlamado());
        entidad.setFechaHoraAtendido(negocio.getFechaHoraAtendido());
        entidad.setFechaHoraAbandona(negocio.getFechaHoraAbandona());
        entidad.setFechaHoraFinaliza(negocio.getFechaHoraFinaliza());
        entidad.setPrioritario(negocio.getPrioritario());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        return entidad;
    }

    @Override
    public GatTiquete consultarSiguiente(String listaServicios, int idSede) throws Exception {
        GatTiquete objResult = null;
        try {
            String strQuery = "FROM GatAtenciones a WHERE a.fechaHoraCrea >= '" + formatoCorto.format(new Date()) + " 00:00:00' AND a.gnSedesId.id =" + idSede + " ORDER BY a.id DESC";
            List<GatAtenciones> listaAtenciones = getEntityManager().createQuery(strQuery).setMaxResults(2).getResultList();
            int contador = 0;
            if (listaAtenciones != null) {
                for (GatAtenciones atencion : listaAtenciones) {
                    if (atencion.getGatTiquetesId().getPrioritario() != null && atencion.getGatTiquetesId().getPrioritario() == true) {
                        contador++;
                    }
                }
            }
            if (contador < 2) {
                contador = 1;
            } else {
                contador = 0;
            }
            Date fechaActual = new Date();
            strQuery = "FROM GatTiquetes c "
                    + " WHERE c.estado = 0 AND c.maeTipoServicioId IN (" + listaServicios + ")"
                    + " AND c.fechaHoraCrea >= '" + formatoCorto.format(fechaActual) + " 00:00:00' AND c.fechaHoraCrea <= '" + formatoLargo.format(fechaActual) + "'"
                    + " AND c.prioritario = " + contador
                    + " AND c.gnSedesId.id = " + idSede
                    + " ORDER BY c.id ASC";
            List<GatTiquetes> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if (list.isEmpty()) {
                if (contador == 0) {
                    contador = 1;
                } else {
                    contador = 0;
                }
                strQuery = "FROM GatTiquetes c "
                        + " WHERE c.estado = 0 AND c.maeTipoServicioId IN (" + listaServicios + ")"
                        + " AND c.fechaHoraCrea >= '" + formatoCorto.format(fechaActual) + " 00:00:00' AND c.fechaHoraCrea <= '" + formatoLargo.format(fechaActual) + "'"
                        + " AND c.prioritario = " + contador
                        + " AND c.gnSedesId.id = " + idSede
                        + " ORDER BY c.id ASC";
                list = getEntityManager().createQuery(strQuery)
                        .getResultList();
                for (GatTiquetes tiquete : list) {
                    objResult = castEntidadNegocio(tiquete);
                    break;
                }
            } else {
                for (GatTiquetes tiquete : list) {
                    objResult = castEntidadNegocio(tiquete);
                    break;
                }
            }

        } catch (NoResultException e) {
            objResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objResult;
    }

    @Override
    public int consultarTotalHoy(int idSede) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GatTiquetes c "
                    + " WHERE c.fechaHoraCrea >= '" + formatoCorto.format(new Date()) + " 00:00:00' "
                    + " AND c.gnSedesId.id = " + idSede;
            cant = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
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
    public int consultarTotalHoy(int idSede, int idServicio) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GatTiquetes c "
                    + " WHERE c.fechaHoraCrea >= '" + formatoCorto.format(new Date()) + " 00:00:00' "
                    + " AND c.gnSedesId.id = " + idSede
                    + " AND c.maeTipoServicioId = " + idServicio;
            cant = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
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
    public int consultarTotalPorFecha(Date fecha) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GatTiquetes c "
                    + " WHERE c.fechaHoraCrea >= '" + formatoCorto.format(fecha) + " 00:00:00' ";
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            //Exception(CONSULTAR_TODOS, e);
            cant = 0;
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

    @Override
    public List<GatTiquete> consultarPorTipoYNumeroDocumento(int idTipoDocumento, String numeroDocumento) throws Exception {
        List<GatTiquete> tiquetes = new ArrayList<>();
        try {
            String strQuery = "FROM GatTiquetes c "
                    + " WHERE c.estado = 0"
                    + " AND c.gatUsuariosId.maeTipoDocumentoId = " + idTipoDocumento
                    + " AND c.gatUsuariosId.numeroDocumento = '" + numeroDocumento + "'"
                    + " ORDER BY c.id ASC";
            List<GatTiquetes> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GatTiquetes tiq : list) {
                tiquetes.add(castEntidadNegocio(tiq));
            }
        } catch (NoResultException e) {
            tiquetes = new ArrayList<>();
        } catch (Exception e) {
            tiquetes = new ArrayList<>();
        } finally {
            cerrarEntityManager();
        }
        return tiquetes;
    }

    @Override
    public int consultarTotalPendiente(Date fecha, Integer idSede) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GatTiquetes c "
                    + " WHERE c.estado = 0 and c.gnSedesId.id = :idSede and c.fechaHoraCrea >= '" + formatoCorto.format(fecha) + " 00:00:00' ";
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("idSede", idSede)
                    .getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            //Exception(CONSULTAR_TODOS, e);
            cant = 0;
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

    @Override
    public int consultarTotalHoyPorEstadoYSede(int idSede, int estado) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GatTiquetes c "
                    + " WHERE c.estado = :estado and c.gnSedesId.id = :idSede and c.fechaHoraCrea >= '" + formatoCorto.format(new Date()) + " 00:00:00' ";
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("estado", estado)
                    .setParameter("idSede", idSede)
                    .getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            //Exception(CONSULTAR_TODOS, e);
            cant = 0;
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

    @Override
    public int consultarTotalHoyPrioritarios(int idSede) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GatTiquetes c "
                    + " WHERE c.prioritario = 1 and c.gnSedesId.id = :idSede and c.fechaHoraCrea >= '" + formatoCorto.format(new Date()) + " 00:00:00' ";
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("idSede", idSede)
                    .getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            //Exception(CONSULTAR_TODOS, e);
            cant = 0;
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

    @Override
    public List<GatTiquete> consultarPorFecha(int idSede, Date fechaInicio, Date fechaFin) throws Exception {
        List<GatTiquete> tiquetes = new ArrayList<>();
        try {
            String strQuery = "FROM GatTiquetes c "
                    + " WHERE c.gnSedesId.id = " + idSede 
                    + " AND c.fechaHoraCrea >= '" + formatoLargo.format(fechaInicio) + "' "
                    + " AND c.fechaHoraCrea <= '" + formatoLargo.format(fechaFin) + "' ";
//                    + " WHERE c.gnSedesId.id = " + idSede + " and c.fechaHoraCrea >= '" + formatoCorto.format(fechaInicio) + " 00:00:00' "
//                    + " and c.fechaHoraCrea <= '" + formatoCorto.format(fechaFin) + " 23:59:59' ";
            List<GatTiquetes> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GatTiquetes tiq : list) {
                tiquetes.add(castEntidadNegocioHistorico(tiq));
            }
        } catch (NoResultException e) {
            tiquetes = new ArrayList<>();
        } catch (Exception e) {
            tiquetes = new ArrayList<>();
        } finally {
            cerrarEntityManager();
        }
        return tiquetes;
    }
}
