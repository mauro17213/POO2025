/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.administracion.UbicacionRemoto;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.math.BigDecimal;
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
import org.hibernate.Session;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(UbicacionRemoto.class)
public class UbicacionServicio extends GenericoServicio implements UbicacionRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(u) FROM GnUbicaciones u "
                    + "WHERE id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery += "AND u.tipo = " + (String) e.getValue() + " ";
                            break;
                        case "nombre":
                            strQuery += "AND u.nombre LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "ubicacionPadre.nombre":
                            strQuery += "AND u.gnUbicacionesId.nombre LIKE '" + (String) e.getValue() + "%' ";
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
    public List<Ubicacion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<Ubicacion> listResult = new ArrayList();
        try {
            String strQuery = "FROM GnUbicaciones u "
                    + "WHERE id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery += "AND u.tipo = " + (String) e.getValue() + " ";
                            break;
                        case "nombre":
                            strQuery += "AND u.nombre LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "ubicacionPadre.nombre":
                            strQuery += "AND u.gnUbicacionesId.nombre LIKE '" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "u." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "u.nombre ASC ";
            }
            List<GnUbicaciones> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GnUbicaciones per : list) {
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
    public Ubicacion consultar(int id) throws Exception {
        Ubicacion objRes = null;
        try {
            objRes = castEntidadNegocio((GnUbicaciones) getEntityManager().find(GnUbicaciones.class, id));
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
    public Ubicacion consultarPorNombreTipo(String nombre, int tipo) throws Exception {
        Ubicacion objRes = null;
        try {
            // Crear consulta JPQL
            String jpql = "SELECT g FROM GnUbicaciones g WHERE g.nombre = :nombre and g.tipo = :tipo";
            // Ejecutar la consulta
            GnUbicaciones entidad = getEntityManager()
                    .createQuery(jpql, GnUbicaciones.class)
                    .setParameter("nombre", nombre)
                    .setParameter("tipo", tipo)
                    .getSingleResult();
            // Convertir la entidad a negocio
            objRes = castEntidadNegocio(entidad);
        } catch (NoResultException e) {
            objRes = null; // No se encontró el registro
        } catch (Exception e) {
            Exception(CONSULTAR, e); // Manejo de excepción genérica
        } finally {
            cerrarEntityManager(); // Asegurar el cierre del EntityManager
        }
        return objRes;
    }

    @Override
    public int insertar(Ubicacion obj) throws Exception {
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
    public void actualizar(Ubicacion obj) throws Exception {
        try {
            GnUbicaciones per = castNegocioEntidad(obj);
            String hql = "UPDATE GnUbicaciones SET "
                    + "nombre = :nombre, "
                    + "maeRegionId = :maeRegionId, "
                    + "maeRegionCodigo = :maeRegionCodigo, "
                    + "maeRegionValor = :maeRegionValor, "
                    + "prefijo = :prefijo, "
                    + "indicativo = :indicativo, "
                    + "cobertura = :cobertura, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id ";
            Session session = getEntityManager().unwrap(Session.class);
            org.hibernate.Query query = session.createQuery(hql);
            query.setProperties(per);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public Ubicacion eliminar(int id) throws Exception {
        Ubicacion obj = null;
        try {
            GnUbicaciones ent = getEntityManager().find(GnUbicaciones.class, id);
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
    public List<Ubicacion> consultarActivas() throws Exception {
        List<Ubicacion> listResult = new ArrayList();
        try {
            String strQuery = "SELECT "
                    + "ubi.id, ubi.nombre, ubi.tipo, ubi.cobertura, ubi.maeRegionId, "
                    + "ubi.maeRegionCodigo, ubi.maeRegionValor, ubi.descripcion, ubi.prefijo, "
                    + "ubi.gpsGeorreferenciada, ubi.gpsLatitud, ubi.gpsLongitud, ubi.gpsProfundidad, "
                    + "ubiP.id, ubiP.nombre, ubiP.prefijo "
                    + "FROM GnUbicaciones AS ubi "
                    + "LEFT JOIN ubi.gnUbicacionesId AS ubiP "
                    + "GROUP BY ubi.id";
            Query query = getEntityManager().createQuery(strQuery);
            List<Object[]> list = query.getResultList();
            if (list != null) {
                for (Object[] ubica : list) {
                    listResult.add(castearNegocio(ubica));
                }
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
    public Ubicacion consultarMunicipiosPorPrefijo(String departamentoPrefijo, String municipioPrefijo) throws Exception {
        Ubicacion municipio = null;
        try {
            String strQuery = "SELECT ubi.id, ubi.nombre, ubi.tipo, ubi.cobertura, ubi.maeRegionId, "
                    + "ubi.maeRegionCodigo, ubi.maeRegionValor, ubi.descripcion, ubi.prefijo, "
                    + "ubi.gpsGeorreferenciada, ubi.gpsLatitud, ubi.gpsLongitud, ubi.gpsProfundidad, "
                    + "ubiP.id, ubiP.nombre, ubiP.prefijo "
                    + " FROM GnUbicaciones AS ubi LEFT JOIN ubi.gnUbicacionesId AS ubiP WHERE ubi.tipo = " + Ubicacion.TIPO_MUNICIPIO + " AND ubi.prefijo = '" + municipioPrefijo + "' AND ubiP.prefijo = '" + departamentoPrefijo + "' GROUP BY ubi.id";
            Query query = getEntityManager().createQuery(strQuery);
            Object[] obj = (Object[]) query.getSingleResult();
            if (obj != null) {
                municipio = castearNegocio(obj);

            }
        } catch (NoResultException e) {
            municipio = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return municipio;
    }

    @Override
    public Ubicacion consultarPaisPorPrefijo(String paisPrefijo) throws Exception {
        Ubicacion pais = null;
        try {
            String strQuery = "SELECT ubi.id, ubi.nombre, ubi.tipo, ubi.cobertura, ubi.maeRegionId, "
                    + "ubi.maeRegionCodigo, ubi.maeRegionValor, ubi.descripcion, ubi.prefijo, "
                    + "ubi.gpsGeorreferenciada, ubi.gpsLatitud, ubi.gpsLongitud, ubi.gpsProfundidad, "
                    + "ubiP.id, ubiP.nombre, ubiP.prefijo "
                    + " FROM GnUbicaciones AS ubi LEFT JOIN ubi.gnUbicacionesId AS ubiP WHERE ubi.tipo = " + Ubicacion.TIPO_PAIS + " AND ubi.prefijo = '" + paisPrefijo + "' GROUP BY ubi.id";
            Query query = getEntityManager().createQuery(strQuery);
            List<Object[]> list = query.getResultList();
            if (list != null) {
                for (Object[] ubi : list) {
                    pais = castearNegocio(ubi);
                    break;
                }
            }
        } catch (NoResultException e) {
            pais = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return pais;
    }

    public static Ubicacion castEntidadNegocio(GnUbicaciones per) {
        Ubicacion obj = new Ubicacion();
        obj.setId(per.getId());
        GnUbicaciones padre = (GnUbicaciones) per.getGnUbicacionesId();
        try {
            if (padre != null && padre.getId() != null && padre.getId() != 0) {
                obj.setUbicacionPadre(castEntidadNegocio(padre));
            }
        } catch (Exception e) {
            obj.setUbicacionPadre(null);
        }
        obj.setTipo(per.getTipo());
        obj.setNombre(per.getNombre());
        obj.setDescripcion(per.getDescripcion());
        obj.setPrefijo(per.getPrefijo());
        obj.setIndicativo(per.getIndicativo());
        obj.setCobertura(per.getCobertura());
        obj.setMaeRegionId(per.getMaeRegionId());
        obj.setMaeRegionCodigo(per.getMaeRegionCodigo());
        obj.setMaeRegionValor(per.getMaeRegionValor());
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static GnUbicaciones castNegocioEntidad(Ubicacion obj) {
        GnUbicaciones per = new GnUbicaciones();
        per.setId(obj.getId());
        per.setGnUbicacionesId(obj.getUbicacionPadre() != null ? castNegocioEntidad(obj.getUbicacionPadre()) : null);
        per.setTipo(obj.getTipo());
        per.setNombre(obj.getNombre());
        per.setDescripcion(obj.getDescripcion());
        per.setPrefijo(obj.getPrefijo());
        per.setIndicativo(obj.getIndicativo());
        per.setCobertura(obj.isCobertura());
        per.setMaeRegionId(obj.getMaeRegionId());
        per.setMaeRegionCodigo(obj.getMaeRegionCodigo());
        per.setMaeRegionValor(obj.getMaeRegionValor());
        //Auditoria
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }

    @Override
    public Ubicacion consultarMunicipiosPorPrefijoCobertura(String departamentoPrefijo, String municipioPrefijo) throws Exception {
        Ubicacion municipio = null;
        try {
            String strQuery = "SELECT ubi.id, ubi.nombre, ubi.tipo, ubi.cobertura, ubi.maeRegionId, "
                    + "ubi.maeRegionCodigo, ubi.maeRegionValor, ubi.descripcion, ubi.prefijo, "
                    + "ubi.gpsGeorreferenciada, ubi.gpsLatitud, ubi.gpsLongitud, ubi.gpsProfundidad, "
                    + "ubiP.id, ubiP.nombre, ubiP.prefijo "
                    + " FROM GnUbicaciones AS ubi LEFT JOIN ubi.gnUbicacionesId AS ubiP WHERE ubi.tipo = " + Ubicacion.TIPO_MUNICIPIO + " AND ubi.prefijo = '" + municipioPrefijo + "' AND ubiP.prefijo = '" + departamentoPrefijo + "' AND ubi.cobertura = 1 GROUP BY ubi.id";
            Query query = getEntityManager().createQuery(strQuery);
            List<Object[]> list = query.getResultList();
            if (list != null) {
                for (Object[] ubi : list) {
                    municipio = castearNegocio(ubi);
                    break;
                }
            }
        } catch (NoResultException e) {
            municipio = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return municipio;
    }

    @Override
    public HashMap<Integer, Ubicacion> consultarHashActivas() throws Exception {
        HashMap<Integer, Ubicacion> hashUbicaciones = new HashMap();
        try {
            String strQuery = "SELECT ubi.id, ubi.nombre, ubi.tipo, ubi.cobertura, ubi.maeRegionId, "
                    + "ubi.maeRegionCodigo, ubi.maeRegionValor, ubi.descripcion, ubi.prefijo, "
                    + "ubi.gpsGeorreferenciada, ubi.gpsLatitud, ubi.gpsLongitud, ubi.gpsProfundidad, "
                    + "ubiP.id, ubiP.nombre, ubiP.prefijo "
                    + " FROM GnUbicaciones AS ubi LEFT JOIN ubi.gnUbicacionesId AS ubiP GROUP BY ubi.id";
            Query query = getEntityManager().createQuery(strQuery);
            List<Object[]> list = query.getResultList();
            if (list != null) {
                for (Object[] ubi : list) {
                    Ubicacion ubicacion = castearNegocio(ubi);
                    hashUbicaciones.put(ubicacion.getId(), ubicacion);
                }
            }
        } catch (NoResultException e) {
            hashUbicaciones = new HashMap();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return hashUbicaciones;
    }

    private Ubicacion castearNegocio(Object[] ubica) {
        Ubicacion negocio = new Ubicacion();
        negocio.setId((Integer) ubica[0]);
        negocio.setNombre((String) ubica[1]);
        negocio.setTipo((Integer) ubica[2]);
        negocio.setCobertura((Boolean) ubica[3]);
        negocio.setMaeRegionId((Integer) ubica[4]);
        negocio.setMaeRegionCodigo((String) ubica[5]);
        negocio.setMaeRegionValor((String) ubica[6]);
        negocio.setDescripcion((String) ubica[7]);
        negocio.setPrefijo((String) ubica[8]);
        negocio.setGpsGeorreferenciada((Boolean) ubica[9]);
        if (ubica[10] != null) {
            negocio.setGpsLatitud(((BigDecimal) ubica[10]).doubleValue());
        }
        if (ubica[11] != null) {
            negocio.setGpsLongitud(((BigDecimal) ubica[11]).doubleValue());
        }
        if (ubica[12] != null) {
            negocio.setGpsProfundidad((Integer) ubica[12]);
        }
        if (ubica.length > 9) {
            negocio.setUbicacionPadre(new Ubicacion());
            negocio.getUbicacionPadre().setId((Integer) ubica[13]);
            negocio.getUbicacionPadre().setNombre((String) ubica[14]);
            negocio.getUbicacionPadre().setPrefijo((String) ubica[15]);
        }
        return negocio;
    }

    @Override
    public List<Ubicacion> consultarActivasNuevas(Long time) throws Exception {
        List<Ubicacion> listResult = new ArrayList();
        try {
            String strQuery = "SELECT "
                    + "ubi.id, ubi.nombre, ubi.tipo, ubi.cobertura, ubi.maeRegionId, "
                    + "ubi.maeRegionCodigo, ubi.maeRegionValor, ubi.descripcion, ubi.prefijo, "
                    + "ubi.gpsGeorreferenciada, ubi.gpsLatitud, ubi.gpsLongitud, ubi.gpsProfundidad, "
                    + "ubi.gnUbicacionesId.id, ubi.gnUbicacionesId.nombre, ubi.gnUbicacionesId.prefijo  "
                    + "FROM GnUbicaciones AS ubi "
                    + "WHERE id > 0 "
                    + "AND (fechaHoraCrea > :time OR fechaHoraModifica > :time) "
                    + "GROUP BY ubi.id";
            Query query = getEntityManager().createQuery(strQuery);
            List<Object[]> list = query.setParameter("time", new Date(time), TemporalType.TIMESTAMP).getResultList();
            if (list != null) {
                for (Object[] ubica : list) {
                    listResult.add(castearNegocio(ubica));
                }
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
