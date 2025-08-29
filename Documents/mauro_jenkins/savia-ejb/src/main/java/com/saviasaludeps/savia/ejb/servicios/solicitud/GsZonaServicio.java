/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.solicitud;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.solicitud.GsSolicitud;
import com.saviasaludeps.savia.dominio.solicitud.GsZona;
import com.saviasaludeps.savia.dominio.solicitud.GsZonaUsuario;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.entidades.GsZonaUsuarios;
import com.saviasaludeps.savia.ejb.entidades.GsZonas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.solicitud.GsZonaRemoto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jramirez
 */
@Stateless
@Remote(GsZonaRemoto.class)
public class GsZonaServicio extends GenericoServicio implements GsZonaLocal, GsZonaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(z) FROM GsZonas z "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND z.nombre LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "ubicacion.id":
                            Ubicacion ubi = (Ubicacion) e.getValue();
                            strQuery += "AND z.gnUbicacionesId.id = " + ubi.getId() + " ";
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
    public List<GsZona> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GsZona> listResult = new ArrayList();
        try {
            String strQuery = "FROM GsZonas z "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND z.nombre LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "ubicacion.id":
                            Ubicacion ubi = (Ubicacion) e.getValue();
                            strQuery += "AND z.gnUbicacionesId.id = " + ubi.getId() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "z." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "z.nombre ASC";
            }
            List<GsZonas> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GsZonas per : list) {
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
    public GsZona consultar(int id) throws Exception {
        GsZona objRes = null;
        try {
            objRes = castEntidadNegocio((GsZonas) getEntityManager().find(GsZonas.class, id));
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
    public int insertar(GsZona obj) throws Exception {
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
    public void actualizar(GsZona obj) throws Exception {
        try {
//            getEntityManager().merge(castNegocioEntidad(obj));
            String hql = "UPDATE GsZonas SET "
                    + "nombre = :nombre, "
                    + "descripcion = :descripcion, "
                    + "porDefecto = :porDefecto, ";
            if (obj.getUbicacion() != null
                    && obj.getUbicacion().getId() > 0) {
                hql += "gnUbicacionesId.id = :gnUbicacionesId, ";
            }

            hql += "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica ";
            hql += "WHERE id = :id";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("descripcion", obj.getDescripcion());
            query.setParameter("porDefecto", obj.getPorDefecto());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());

            if (obj.getUbicacion() != null
                    && obj.getUbicacion().getId() > 0) {
                query.setParameter("gnUbicacionesId", obj.getUbicacion().getId());
            }
            query.setParameter("id", obj.getId());
            query.executeUpdate();

        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public GsZona eliminar(int id) throws Exception {
        GsZona obj = null;
        try {
            GsZonas ent = getEntityManager().find(GsZonas.class, id);
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
    public GsZonas consultarPorUbicacion(int ubicacionId, EntityManager em) {
        GsZonas zona;
        try {
            String strQuery = "FROM GsZonas z "
                    + " WHERE z.gnUbicacionesId.id = :ubicacionesId";
            Query query = em.createQuery(strQuery);
            query.setParameter("ubicacionesId", ubicacionId);
            zona = (GsZonas) query.getSingleResult();
        } catch (NoResultException e) {
            zona = null;
        } catch (Exception e) {
            zona = null;
        }
        return zona;
    }

    @Override
    public GsZonas consultarPorDefecto(EntityManager em) {
        GsZonas zona = null;
        try {
            String strQuery = "FROM GsZonas z "
                    + "WHERE porDefecto = true "
                    + "ORDER BY z.id ASC ";
            List<GsZonas> list = em.createQuery(strQuery)
                    .getResultList();
            for (GsZonas per : list) {
                zona = per;
//                zona = castEntidadNegocio(per);
                break;
            }
        } catch (NoResultException e) {
            zona = null;
        } catch (Exception e) {
            zona = null;
        }
        return zona;
    }

    @Override
    public List<GsZonaUsuarios> consultarUsuariosActivos(int zonaId, int tipoSolicitud, EntityManager em) {
        List<GsZonaUsuarios> listaUsuarios;
        try {
            String strQuery = "FROM GsZonaUsuarios AS zu "
                    + "WHERE zu.activo = TRUE "
                    + "AND zu.gnUsuariosId.activo = TRUE "
                    + "AND zu.gsZonasId.id = :zonasId ";
            if (tipoSolicitud == GsSolicitud.TIPO_SOLICITUD_AFILIACION) {
                strQuery += "AND zu.gestion01 = TRUE ";
            } else if (tipoSolicitud == GsSolicitud.TIPO_SOLICITUD_AUTORIZACION) {
                strQuery += "AND zu.gestion10 = TRUE ";
            }
            strQuery += "ORDER BY zu.gnUsuariosId.id ";
            listaUsuarios = em.createQuery(strQuery)
                    .setParameter("zonasId", zonaId)
                    .getResultList();
        } catch (NoResultException e) {
            listaUsuarios = new ArrayList();
        } catch (Exception e) {
            listaUsuarios = new ArrayList();
        }
        return listaUsuarios;
    }

    @Override
    public List<GsZona> consultarTodas() throws Exception {
        List<GsZona> listResult = new ArrayList();
        try {
            String strQuery = "FROM GsZonas z ";
            strQuery += "ORDER BY z.nombre ASC ";
            List<GsZonas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GsZonas per : list) {
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
    public HashMap<Integer, GsZona> consultarTodasHash() throws Exception {
        HashMap<Integer, GsZona> hashResult = new HashMap();
        try {
            String strQuery = "FROM GsZonas z ";
            strQuery += "ORDER BY z.nombre ASC ";
            List<GsZonas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GsZonas per : list) {
                GsZona obj = castEntidadNegocio(per);
                hashResult.put(obj.getId(), obj);
            }
        } catch (NoResultException e) {
            hashResult = new HashMap();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return hashResult;
    }

    @Override
    public List<GsZonaUsuario> consultarUsuarios(Integer zonaId) throws Exception {
        List<GsZonaUsuario> listResult = new ArrayList();
        try {
            String strQuery = "FROM GsZonaUsuarios z "
                    + "WHERE z.gsZonasId.id = :zonaId ";
            strQuery += "ORDER BY z.fechaHoraCrea ASC ";
            List<GsZonaUsuarios> list = getEntityManager().createQuery(strQuery)
                    .setParameter("zonaId", zonaId)
                    .getResultList();
            for (GsZonaUsuarios per : list) {
                listResult.add(castEntidadNegocioUsuario(per));
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
    public GsZonaUsuario consultarUsuario(int id) throws Exception {
        GsZonaUsuario objRes = null;
        try {
            objRes = castEntidadNegocioUsuario((GsZonaUsuarios) getEntityManager().find(GsZonaUsuarios.class, id));
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
    public int insertarUsuario(GsZonaUsuario obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidadUsuario(obj)).getId();
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
    public void actualizarUsuario(GsZonaUsuario obj) throws Exception {
        try {
//            getEntityManager().merge(castNegocioEntidadUsuario(obj));
            String hql = "UPDATE GsZonaUsuarios SET "
                    + "maeSala = :maeSala, "
                    + "activo = :activo, "
                    + "gestiones = :gestiones, "                    
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica, ";
            if (obj.getGsZona() != null
                    && obj.getGsZona().getId() > 0) {
                hql += "gsZonasId.id = :gsZonasId, ";
            }

            if (obj.getUsuario() != null
                    && obj.getUsuario().getId() > 0) {
                hql += "gnUsuariosId.id = :gnUsuariosId, ";
            }
            hql += "gestion01 = :gestion01, "
                    + "gestion02 = :gestion02, "
                    + "gestion03 = :gestion03, "
                    + "gestion04 = :gestion04, "
                    + "gestion05 = :gestion05, "
                    + "gestion06 = :gestion06, "
                    + "gestion07 = :gestion07, "
                    + "gestion08 = :gestion08, "
                    + "gestion09 = :gestion09, "
                    + "gestion10 = :gestion10 ";
            hql += "WHERE id = :id";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("maeSala", obj.getMaeSala());
            query.setParameter("activo", obj.isActivo());
            query.setParameter("gestiones", obj.getGestiones());            
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("gestion01", obj.isGestion01());
            query.setParameter("gestion02", obj.isGestion02());
            query.setParameter("gestion03", obj.isGestion03());
            query.setParameter("gestion04", obj.isGestion04());
            query.setParameter("gestion05", obj.isGestion05());
            query.setParameter("gestion06", obj.isGestion06());
            query.setParameter("gestion07", obj.isGestion07());
            query.setParameter("gestion08", obj.isGestion08());
            query.setParameter("gestion09", obj.isGestion09());
            query.setParameter("gestion10", obj.isGestion10());

            if (obj.getGsZona() != null
                    && obj.getGsZona().getId() > 0) {
                query.setParameter("gsZonasId", obj.getGsZona().getId());
            }

            if (obj.getUsuario() != null
                    && obj.getUsuario().getId() > 0) {
                query.setParameter("gnUsuariosId", obj.getUsuario().getId());
            }

            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public GsZonaUsuario eliminarUsuario(int id) throws Exception {
        GsZonaUsuario obj = null;
        try {
            GsZonaUsuarios ent = (GsZonaUsuarios) getEntityManager().find(GsZonaUsuarios.class, id);
            if (ent != null) {
                obj = castEntidadNegocioUsuario(ent);
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

    public static GsZona castEntidadNegocio(GsZonas per) {
        GsZona obj = new GsZona();
        obj.setId(per.getId());
        obj.setNombre(per.getNombre());
        obj.setDescripcion(per.getDescripcion());
        obj.setPorDefecto(per.getPorDefecto());
        obj.setUbicacion(new Ubicacion(per.getGnUbicacionesId().getId()));
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static GsZonas castNegocioEntidad(GsZona obj) {
        GsZonas per = new GsZonas();
        per.setId(obj.getId());
        per.setNombre(obj.getNombre());
        per.setDescripcion(obj.getDescripcion());
        per.setPorDefecto(obj.getPorDefecto());
        per.setGnUbicacionesId(new GnUbicaciones(obj.getUbicacion().getId()));
        //Auditoria
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }

    public static GsZonaUsuario castEntidadNegocioUsuario(GsZonaUsuarios per) {
        GsZonaUsuario obj = new GsZonaUsuario();
        obj.setId(per.getId());
        obj.setGsZona(new GsZona(per.getGsZonasId().getId()));
        obj.setUsuario(
                new Usuario(
                        per.getGnUsuariosId().getId(),
                        per.getGnUsuariosId().getUsuario(),
                        per.getGnUsuariosId().getNombre()
                )
        );
        obj.setActivo(per.getActivo());
        obj.setGestiones(per.getGestiones());
        obj.setGestion01(per.getGestion01());
        obj.setGestion02(per.getGestion02());
        obj.setGestion03(per.getGestion03());
        obj.setGestion04(per.getGestion04());
        obj.setGestion05(per.getGestion05());
        obj.setGestion06(per.getGestion06());
        obj.setGestion07(per.getGestion07());
        obj.setGestion08(per.getGestion08());
        obj.setGestion09(per.getGestion09());
        obj.setGestion10(per.getGestion10());
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static GsZonaUsuarios castNegocioEntidadUsuario(GsZonaUsuario obj) {
        GsZonaUsuarios per = new GsZonaUsuarios();
        per.setId(obj.getId());
        per.setGsZonasId(new GsZonas(obj.getGsZona().getId()));
        per.setGnUsuariosId(new GnUsuarios(obj.getUsuario().getId()));
        per.setActivo(obj.isActivo());
        per.setGestiones(obj.getGestiones());
        per.setGestion01(obj.isGestion01());
        per.setGestion02(obj.isGestion02());
        per.setGestion03(obj.isGestion03());
        per.setGestion04(obj.isGestion04());
        per.setGestion05(obj.isGestion05());
        per.setGestion06(obj.isGestion06());
        per.setGestion07(obj.isGestion07());
        per.setGestion08(obj.isGestion08());
        per.setGestion09(obj.isGestion09());
        per.setGestion10(obj.isGestion10());
        //Auditoria
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }
}
