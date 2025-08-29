/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.GnValidacionCampo;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Perfil;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.GnMaestroAccionRelaciones;
import com.saviasaludeps.savia.ejb.entidades.GnMaestroAcciones;
import com.saviasaludeps.savia.ejb.entidades.GnMaestroTipos;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.ejb.entidades.GnMaestros;
import com.saviasaludeps.savia.ejb.entidades.GnValidacionCampos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(MaestroRemoto.class)
public class MaestroServicio extends GenericoServicio implements MaestroRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(m) FROM GnMaestros m ";
            Object objeto = paramConsulta.getParametroConsulta1();
            if (objeto == null) {//Tiene permisos de verlos a todos
                strQuery += "WHERE 1 = 1 ";
            } else {//Solo puede ver los que tienen permisos asignados
                strQuery += "INNER JOIN GnMaestroTipos t ON t.tipo = m.tipo ";
                List<Perfil> listaPerfiles = (List<Perfil>) objeto;
                String sqlPerfiles = null;
                for (Perfil per : listaPerfiles) {
                    if (sqlPerfiles == null) {
                        sqlPerfiles = "t.gnPerfilesId.id = " + per.getId() + " ";
                    } else {
                        sqlPerfiles += "OR t.gnPerfilesId.id = " + per.getId() + " ";
                    }
                }
                if (sqlPerfiles != null) {
                    strQuery += "WHERE " + "(" + sqlPerfiles + ") ";
                } else {
                    strQuery += "WHERE 1 = 1 ";
                }
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maestroTipo.tipo":
                            strQuery += "AND m.tipo = '" + (String) e.getValue() + "' ";
                            break;
                        case "nombre":
                            strQuery += "AND m.nombre LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "valor":
                            strQuery += "AND m.valor LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND m.activo = " + (String) e.getValue() + " ";
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
    public List<Maestro> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<Maestro> listResult = new ArrayList();
        try {
            String strQuery = "SELECT m FROM GnMaestros m ";
            Object objeto = paramConsulta.getParametroConsulta1();
            if (objeto == null) {//Tiene permisos de verlos a todos
                strQuery += "WHERE 1 = 1 ";
            } else {//Solo puede ver los que tienen permisos asignados
                strQuery += "INNER JOIN GnMaestroTipos t ON t.tipo = m.tipo ";
                List<Perfil> listaPerfiles = (List<Perfil>) objeto;
                String sqlPerfiles = null;
                for (Perfil per : listaPerfiles) {
                    if (sqlPerfiles == null) {
                        sqlPerfiles = "t.gnPerfilesId.id = " + per.getId() + " ";
                    } else {
                        sqlPerfiles += "OR t.gnPerfilesId.id = " + per.getId() + " ";
                    }
                }
                if (sqlPerfiles != null) {
                    strQuery += "WHERE " + "(" + sqlPerfiles + ") ";
                } else {
                    strQuery += "WHERE 1 = 1 ";
                }
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maestroTipo.tipo":
                            strQuery += "AND m.tipo = '" + (String) e.getValue() + "' ";
                            break;
                        case "nombre":
                            strQuery += "AND m.nombre LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "valor":
                            strQuery += "AND m.valor LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND m.activo = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "m." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "m.nombre ASC ";
            }
            List<GnMaestros> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GnMaestros per : list) {
                listResult.add(castEntidadNegocioCorto(per));
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
    public Maestro consultar(int id) throws Exception {
        Maestro objRes = null;
        try {
            objRes = castEntidadNegocio((GnMaestros) getEntityManager().find(GnMaestros.class, id));
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
    public Maestro consultarPorValorTipo(String valor, String tipo) throws Exception {
        Maestro objRes = null;
        try {
            String strQuery = "FROM GnMaestros m "
                    + "WHERE m.valor ='" + valor
                    + "' AND m.tipo='" + tipo + "'";
            objRes = castEntidadNegocio((GnMaestros) getEntityManager().createQuery(strQuery).getSingleResult());
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
    public Maestro consultarPorNombreTipo(String nombre, String tipo) throws Exception {
        Maestro objRes = null;
        try {
            String strQuery = "FROM GnMaestros m "
                    + "WHERE m.nombre ='" + nombre
                    + "' AND m.tipo='" + tipo + "'";
            objRes = castEntidadNegocio((GnMaestros) getEntityManager().createQuery(strQuery).getSingleResult());
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
    public int insertar(Maestro obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "La combinación nombre, tipo, valor ya existe.");
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    @Override
    public void actualizar(Maestro obj) throws Exception {
        try {
            getEntityManager().merge(castNegocioEntidad(obj));
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public Maestro eliminar(int id) throws Exception {
        Maestro obj = null;
        try {
            GnMaestros ent = getEntityManager().find(GnMaestros.class, id);
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
    public List<Maestro> consultarPorTipo(String tipo) throws Exception {
        List<Maestro> listResult = new ArrayList();
        String strQuery = "FROM GnMaestros e "
                + "WHERE e.tipo = '" + tipo + "' "
                + "AND e.activo = 1 "
                + "ORDER BY e.nombre ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnMaestros> list = query.getResultList();
            for (GnMaestros obj : list) {
                listResult.add(castEntidadNegocio(obj));
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
    public List<Maestro> consultarPorTipoHijo(String tipo) throws Exception {
        List<Maestro> listResult = new ArrayList();
        String strQuery = "SELECT m FROM GnMaestros m "
                //Se cambio el query porque el campo mth.gnMaestroTiposTipo no existe en la tabla GnMaestroTipos
                //+ "INNER JOIN GnMaestroTipos mth ON m.tipo.tipo = mth.gnMaestroTiposTipo.tipo "
                + "INNER JOIN GnMaestroTipos mth ON m.tipo.tipo = mth.tipo "
                + "WHERE mth.tipo = '" + tipo + "' "
                + "ORDER BY m.nombre ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnMaestros> list = query.getResultList();
            for (GnMaestros obj : list) {
                listResult.add(castEntidadNegocio(obj));
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
    public HashMap<Integer, Maestro> consultarHashPorTipo(String tipo) throws Exception {
        HashMap<Integer, Maestro> hashResult = new HashMap();
        String strQuery = "FROM GnMaestros e "
                + "WHERE e.tipo = '" + tipo + "' "
                + "AND e.activo = 1";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnMaestros> list = query.getResultList();
            for (GnMaestros per : list) {
                Maestro obj = castEntidadNegocio(per);
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
    public HashMap<String, Maestro> consultarHashPorTipoValor(String tipo) throws Exception {
        HashMap<String, Maestro> hashResult = new HashMap();
        String strQuery = "FROM GnMaestros e "
                + "WHERE e.tipo = '" + tipo + "' "
                + "AND e.activo = 1";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnMaestros> list = query.getResultList();
            for (GnMaestros per : list) {
                Maestro obj = castEntidadNegocio(per);
                hashResult.put(obj.getValor(), obj);
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
    public HashMap<Integer, Maestro> consultarHash() throws Exception {
        HashMap<Integer, Maestro> hashResult = new HashMap();
        String strQuery = "FROM GnMaestros e ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnMaestros> list = query.getResultList();
            for (GnMaestros per : list) {
                Maestro obj = castEntidadNegocio(per);
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
    public List<Maestro> consultarLista() throws Exception {
        List<Maestro> listResult = new ArrayList();
        String strQuery = "FROM GnMaestros e";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnMaestros> list = query.getResultList();
            for (GnMaestros obj : list) {
                listResult.add(castEntidadNegocio(obj));
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
    public List<Maestro> consultarPorTipoYOrdenPorId(String tipo) throws Exception {
        List<Maestro> listResult = new ArrayList();
        String strQuery = "FROM GnMaestros m "
                + "WHERE m.tipo = '" + tipo + "' "
                + "AND m.activo = 1 "
                + "ORDER BY m.id ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnMaestros> list = query.getResultList();
            for (GnMaestros obj : list) {
                listResult.add(castEntidadNegocio(obj));
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
    public List<MaestroAccion> consultarAccionesPorTipo(String tipo) throws Exception {
        List<MaestroAccion> listResult = new ArrayList();
        String strQuery = "FROM GnMaestroAcciones a "
                + "WHERE a.maestrosTipo.tipo = :tipo "
                + "AND a.maestrosTipo.activo = 1 ";
        try {
            Query query = getEntityManager().createQuery(strQuery)
                    .setParameter("tipo", tipo);
            List<GnMaestroAcciones> list = query.getResultList();
            for (GnMaestroAcciones per : list) {
                MaestroAccion obj = new MaestroAccion();
                obj.setId(per.getId());
                obj.setMaestrosTipo(
                        new MaestroTipo(
                                per.getMaestrosTipo().getTipo()
                        )
                );
                obj.setNombre(per.getNombre());
                obj.setDescripcion(per.getDescripcion());
                listResult.add(obj);
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
    public MaestroAccion consultarAcciones(int id) throws Exception {
        MaestroAccion obj = null;
        String strQuery = "FROM GnMaestroAcciones a "
                + "WHERE a.id = " + id
                + "AND a.maestrosTipo.activo = 1 ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            GnMaestroAcciones per = (GnMaestroAcciones) query.getSingleResult();
            if (per != null) {
                obj = new MaestroAccion();
                obj.setId(per.getId());
                obj.setMaestrosTipo(new MaestroTipo(
                        per.getMaestrosTipo().getTipo(),
                        per.getMaestrosTipo().getNombre(),
                        per.getMaestrosTipo().getActivo()
                ));
                obj.setIdGrupo(per.getGrupoId());
                obj.setNombre(per.getNombre());
                obj.setDescripcion(per.getDescripcion());
            }
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            obj = null;
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public List<Maestro> consultarMaestrosPorAccion(int idAccion) throws Exception {
        List<Maestro> lista = new ArrayList();
        String strQuery = "SELECT maestro "
                + "FROM GnMaestroAccionRelaciones AS relacion "
                + "INNER JOIN relacion.gnMaestrosId AS maestro "
                + "INNER JOIN relacion.gnMaestroAccionesId AS accion "
                + "WHERE accion.id = " + idAccion;
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnMaestros> list = query.getResultList();
            for (GnMaestros per : list) {
                lista.add(castEntidadNegocio(per));
            }
        } catch (NoResultException e) {
            lista = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return lista;
    }

    public static Maestro castEntidadNegocioCorto(GnMaestros per) {
        Maestro obj = new Maestro();
        obj.setId(per.getId());
        obj.setTipo(per.getTipo().getTipo());
        obj.setMaestroTipo(new MaestroTipo(
                per.getTipo().getTipo(),
                per.getTipo().getNombre(),
                per.getTipo().getActivo())
        );
        obj.setNombre(per.getNombre());
        obj.setValor(per.getValor());
        obj.setDescripcion(per.getDescripcion());
        obj.setActivo(per.getActivo());
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static Maestro castEntidadNegocio(GnMaestros per) {
        Maestro obj = new Maestro();
        obj.setId(per.getId());
        obj.setTipo(per.getTipo().getTipo());
        obj.setMaestroTipo(new MaestroTipo(
                per.getTipo().getTipo(),
                per.getTipo().getNombre(),
                per.getTipo().getActivo())
        );
        obj.setNombre(per.getNombre());
        obj.setValor(per.getValor());
        obj.setDescripcion(per.getDescripcion());
        obj.setActivo(per.getActivo());
        if (per.getGnValidacionCamposId() != null) {
            GnValidacionCampo validacion = new GnValidacionCampo(
                    per.getGnValidacionCamposId().getId(),
                    per.getGnValidacionCamposId().getValidator(),
                    per.getGnValidacionCamposId().getNombre(),
                    per.getGnValidacionCamposId().getExpresionRegular()
            );
            obj.setValidacionCampoId(validacion);
        } else {
            obj.setValidacionCampoId(new GnValidacionCampo());
        }
        // rpalacic (2024-04-16) Se comento por el costo de consulta.
        //Se recorre la lista de padres
        if (per.getGnMaestroAccionRelacionesList() != null) {
//            obj.setMaestroAcciones(castListEntidadNegocioAcciones(per.getGnMaestroAccionRelacionesList()));
            obj.setMaestroAcciones(castListEntidadNegocioAcciones(per.getTipo().getTipo(), per.getGnMaestroAccionRelacionesList()));
        }
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static GnMaestros castNegocioEntidad(Maestro obj) {
        GnMaestros per = new GnMaestros();
        per.setId(obj.getId());
        per.setTipo(new GnMaestroTipos(obj.getTipo()));
        per.setNombre(obj.getNombre());
        per.setValor(obj.getValor());
        per.setDescripcion(obj.getDescripcion());
        per.setActivo(obj.isActivo());
        if (obj.getMaestroTipo() != null && obj.getMaestroTipo().getTipo() != null) {
            per.setTipo(new GnMaestroTipos(obj.getMaestroTipo().getTipo()));
        }
        if (obj.getValidacionCampoId() != null && obj.getValidacionCampoId().getId() != null) {
            per.setGnValidacionCamposId(new GnValidacionCampos(obj.getValidacionCampoId().getId()));
        }
        //Auditoría
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }

    @Override
    public HashMap<Integer, Maestro> consultarHashPorPadre(int idPadre) throws Exception {
        HashMap<Integer, Maestro> hashResult = new HashMap();
        String strQuery = "FROM GnMaestros m "
                + "WHERE m.gnMaestrosId.id = " + idPadre;
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnMaestros> list = query.getResultList();
            for (GnMaestros per : list) {
                Maestro obj = castEntidadNegocio(per);
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
    public List<Maestro> consultarListaPorPadre(String tipo, int idPadre) throws Exception {
        List<Maestro> listResult = new ArrayList();
        String strQuery = "SELECT hijo "
                + "FROM GnMaestroRelaciones AS relacion "
                + "INNER JOIN relacion.gnMaestrosIdPadre AS padre "
                + "INNER JOIN relacion.gnMaestrosIdHijo AS hijo "
                + "WHERE hijo.tipo = '" + tipo + "' "
                + "AND padre.id = " + idPadre + " "
                + "AND hijo.activo = true";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnMaestros> list = query.getResultList();
            for (GnMaestros obj : list) {
                listResult.add(castEntidadNegocio(obj));
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
    public List<Maestro> consultarListaPorPadre(String tipo, int idPadre1, int idPadre2) throws Exception {
        List<Maestro> listResult = new ArrayList();
        String strQuery = "SELECT hijo "
                + "FROM GnMaestroRelaciones AS relacion "
                + "INNER JOIN relacion.gnMaestrosIdPadre AS padre "
                + "INNER JOIN relacion.gnMaestrosIdHijo AS hijo "
                + "WHERE hijo.tipo = '" + tipo + "' "
                + "AND hijo.activo = true "
                + "AND padre.id = " + idPadre1 + " "
                + "AND hijo.id IN ( SELECT gnMaestrosIdHijo.id FROM GnMaestroRelaciones AS relacion2 "
                + "WHERE relacion2.gnMaestrosIdPadre.id = " + idPadre2 + " ) ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnMaestros> list = query.getResultList();
            for (GnMaestros obj : list) {
                listResult.add(castEntidadNegocio(obj));
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
    public List<Maestro> consultarListaPorPadre(String tipo, int idPadre1, int idPadre2, int idPadre3) throws Exception {
        List<Maestro> listResult = new ArrayList();
        String strQuery = "SELECT hijo "
                + "FROM GnMaestroRelaciones AS relacion "
                + "INNER JOIN relacion.gnMaestrosIdPadre AS padre "
                + "INNER JOIN relacion.gnMaestrosIdHijo AS hijo "
                + "WHERE hijo.tipo = '" + tipo + "' "
                + "AND hijo.activo = true "
                + "AND padre.id = " + idPadre1 + " "
                + "AND hijo.id IN ( SELECT gnMaestrosIdHijo.id FROM GnMaestroRelaciones AS relacion2 "
                + "WHERE relacion2.gnMaestrosIdPadre.id = " + idPadre2 + " ) "
                + "AND hijo.id IN ( SELECT gnMaestrosIdHijo.id FROM GnMaestroRelaciones AS relacion3 "
                + "WHERE relacion3.gnMaestrosIdPadre.id = " + idPadre3 + " ) ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnMaestros> list = query.getResultList();
            for (GnMaestros obj : list) {
                listResult.add(castEntidadNegocio(obj));
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
    public List<Maestro> consultarListaPorPadre(String tipo, String valorPadre) throws Exception {
        List<Maestro> listResult = new ArrayList();
        String strQuery = "SELECT hijo "
                + "FROM GnMaestroRelaciones AS relacion "
                + "INNER JOIN relacion.gnMaestrosIdPadre AS padre "
                + "INNER JOIN relacion.gnMaestrosIdHijo AS hijo "
                + "WHERE hijo.tipo.tipo = '" + tipo + "' "
                + "AND padre.valor = '" + valorPadre + "' "
                + "AND hijo.activo = true";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnMaestros> list = query.getResultList();
            for (GnMaestros obj : list) {
                listResult.add(castEntidadNegocio(obj));
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
    public List<Maestro> consultarListaPorHijo(String tipo, int idHijo) throws Exception {
        List<Maestro> listResult = new ArrayList();
        String strQuery = "SELECT padre "
                + "FROM GnMaestroRelaciones AS relacion "
                + "INNER JOIN relacion.gnMaestrosIdPadre AS padre "
                + "INNER JOIN relacion.gnMaestrosIdHijo AS hijo "
                + "WHERE padre.tipo = '" + tipo + "' "
                + "AND hijo.id = " + idHijo + " "
                + "AND padre.activo = true";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnMaestros> list = query.getResultList();
            for (GnMaestros obj : list) {
                listResult.add(castEntidadNegocio(obj));
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
    public List<Maestro> consultarListaPorHijo(String tipo, String valorHijo) throws Exception {
        List<Maestro> listResult = new ArrayList();
        String strQuery = "SELECT padre "
                + "FROM GnMaestroRelaciones AS relacion "
                + "INNER JOIN relacion.gnMaestrosIdPadre AS padre "
                + "INNER JOIN relacion.gnMaestrosIdHijo AS hijo "
                + "WHERE padre.tipo = '" + tipo + "' "
                + "AND hijo.valor = '" + valorHijo + "' "
                + "AND padre.activo = true";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnMaestros> list = query.getResultList();
            for (GnMaestros obj : list) {
                listResult.add(castEntidadNegocio(obj));
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
    public boolean isRelacionPadreHijo(int idHijo, int idPadre) throws Exception {
        boolean isRelacion;
        String strQuery = "SELECT hijo "
                + "FROM GnMaestroRelaciones AS relacion "
                + "INNER JOIN relacion.gnMaestrosIdPadre AS padre "
                + "INNER JOIN relacion.gnMaestrosIdHijo AS hijo "
                + "WHERE hijo.id = " + idHijo + " "
                + "AND padre.id = " + idPadre;
        try {
            Query query = getEntityManager().createQuery(strQuery);
            isRelacion = !query.getResultList().isEmpty();
        } catch (Exception e) {
            isRelacion = false;
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return isRelacion;
    }

    @Override
    public boolean isRelacionPadreHijo(int idHijo, int idPadre1, int idPadre2) throws Exception {
        boolean isRelacion;
        String strQuery = "SELECT hijo "
                + "FROM GnMaestroRelaciones AS relacion "
                + "INNER JOIN relacion.gnMaestrosIdPadre AS padre "
                + "INNER JOIN relacion.gnMaestrosIdHijo AS hijo "
                + "WHERE hijo.id = " + idHijo + " "
                + "AND padre.id = " + idPadre1 + " "
                + "AND hijo.id IN ( SELECT gnMaestrosIdHijo.id FROM GnMaestroRelaciones AS relacion2 "
                + "WHERE relacion2.gnMaestrosIdPadre.id = " + idPadre2 + " ) ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnMaestros> list = query.getResultList();
            isRelacion = !list.isEmpty();
        } catch (Exception e) {
            isRelacion = false;
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return isRelacion;
    }

    @Override
    public boolean isRelacionPadreHijo(int idHijo, int idPadre1, int idPadre2, int idPadre3) throws Exception {
        boolean isRelacion;
        String strQuery = "SELECT hijo "
                + "FROM GnMaestroRelaciones AS relacion "
                + "INNER JOIN relacion.gnMaestrosIdPadre AS padre "
                + "INNER JOIN relacion.gnMaestrosIdHijo AS hijo "
                + "WHERE hijo.id = " + idHijo + " "
                + "AND padre.id = " + idPadre1 + " "
                + "AND hijo.id IN ( SELECT gnMaestrosIdHijo.id FROM GnMaestroRelaciones AS relacion2 "
                + "WHERE relacion2.gnMaestrosIdPadre.id = " + idPadre2 + " ) "
                + "AND hijo.id IN ( SELECT gnMaestrosIdHijo.id FROM GnMaestroRelaciones AS relacion3 "
                + "WHERE relacion3.gnMaestrosIdPadre.id = " + idPadre3 + " ) ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnMaestros> list = query.getResultList();
            isRelacion = !list.isEmpty();
        } catch (Exception e) {
            isRelacion = false;
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return isRelacion;
    }

    public static List<MaestroAccion> castListEntidadNegocioAcciones(String tipo, List<GnMaestroAccionRelaciones> relaciones) {
        List<MaestroAccion> negocioAcciones = new ArrayList<>();
        for (GnMaestroAccionRelaciones relacion : relaciones) {
            MaestroAccion accion = new MaestroAccion();
            accion.setId(relacion.getGnMaestroAccionesId().getId());
            accion.setIdGrupo(relacion.getGnMaestroAccionesId().getGrupoId());
            accion.setNombre(relacion.getGnMaestroAccionesId().getNombre());
            accion.setDescripcion(relacion.getGnMaestroAccionesId().getDescripcion());
//            accion.setMaestrosTipo(new MaestroTipo(relacion.getGnMaestroAccionesId().getMaestrosTipo().getTipo()));
            accion.setMaestrosTipo(new MaestroTipo(tipo));
            negocioAcciones.add(accion);
        }
        return negocioAcciones;
    }

    @Override
    public List<Maestro> listarPorIdValidacion(int idValidacion) throws Exception {
        List<Maestro> listResult = new ArrayList();
        String strQuery = "FROM GnMaestros m "
                + "WHERE m.gnValidacionCamposId.id = " + idValidacion;
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnMaestros> list = query.getResultList();
            for (GnMaestros obj : list) {
                listResult.add(castEntidadNegocio(obj));
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
    public List<Maestro> listarPorTipos(List<String> tipos) throws Exception {
        List<Maestro> listResult = new ArrayList();
        try {
            String textoTipos = "";
            for (String tipo : tipos) {
                if (!textoTipos.isEmpty()) {
                    textoTipos += ",";
                }
                textoTipos += "'" + tipo + "'";
            }
            String strQuery = "FROM GnMaestros m "
                    + "WHERE m.tipo IN (" + textoTipos + ") AND m.activo = 1";
            Query query = getEntityManager().createQuery(strQuery);
            List<GnMaestros> list = query.getResultList();
            for (GnMaestros obj : list) {
                listResult.add(castEntidadNegocio(obj));
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
    public List<Maestro> listarPorTiposNuevos(List<String> tipos, Long time) throws Exception {
        List<Maestro> listResult = new ArrayList();
        try {
            String textoTipos = "";
            for (String tipo : tipos) {
                if (!textoTipos.isEmpty()) {
                    textoTipos += ",";
                }
                textoTipos += "'" + tipo + "'";
            }
            String strQuery = "FROM GnMaestros m "
                    + "WHERE m.tipo IN (" + textoTipos + ") AND m.activo = 1"
                    + " AND (m.fechaHoraCrea > :time OR m.fechaHoraModifica > :time) ";
            Query query = getEntityManager().createQuery(strQuery);
            List<GnMaestros> list = query.setParameter("time", new Date(time), TemporalType.TIMESTAMP).getResultList();
            for (GnMaestros obj : list) {
                listResult.add(castEntidadNegocio(obj));
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
    public List<Maestro> listarPorTiposSinAcciones(String tipo) throws Exception {
        List<Maestro> listResult = new ArrayList();
        String strQuery = "FROM GnMaestros e "
                + "WHERE e.tipo = '" + tipo + "' "
                + "AND e.activo = 1 "
                + "ORDER BY e.nombre ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnMaestros> list = query.getResultList();
            for (GnMaestros obj : list) {
                listResult.add(castEntidadNegocioCorto(obj));
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

}
