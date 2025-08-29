/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.tutela;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuJuzgado;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.entidades.TuJuzgados;
import com.saviasaludeps.savia.ejb.servicios.administracion.UbicacionServicio;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.tutela.TuJuzgadoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jramirer
 */
@Stateless
@Remote(TuJuzgadoRemoto.class)
public class TuJuzgadoServicio extends GenericoServicio implements TuJuzgadoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(t.id) FROM TuJuzgados t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND t.nombre LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "codigo_despacho":
                            strQuery += "AND t.codigoDespacho LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "gnUbicacionId.id":
                            strQuery += "AND t.gnUbicacionesId.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "activo":
                            String strActivo = (e.getValue().equals("true"))? "1": "0"; 
                            strQuery += "AND t.activo = '" + strActivo + "' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //                    .setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public List<TuJuzgado> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<TuJuzgado> listResult = new ArrayList();
        try {
            String strQuery = "SELECT j FROM TuJuzgados j "
                    + "WHERE j.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND j.nombre LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "codigo_despacho":
                            strQuery += "AND j.codigoDespacho LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "gnUbicacionId.id":
                            strQuery += "AND j.gnUbicacionesId.id = " +  e.getValue() + " ";
                            break;
                        case "activo":
                            String strActivo = (e.getValue().equals("true"))? "1": "0"; 
                            strQuery += "AND j.activo = '" + strActivo + "' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                String order = paramConsulta.getOrden().
                        replace("codigo_despacho", "codigoDespacho").
                        replace("gnUbicacionId.id", "gnUbicacionesId.id");
                strQuery += "j." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "j.id ASC";
            }
            List<TuJuzgados> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //                    .setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (TuJuzgados per : list) {
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
    public TuJuzgado consultar(int id) throws Exception {
        TuJuzgado objRes = null;
        try {
            objRes = castEntidadNegocio((TuJuzgados) getEntityManager().find(TuJuzgados.class, id));
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
    public int insertar(TuJuzgado obj) throws Exception {
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
    public void actualizar(TuJuzgado obj) throws Exception {
        try {
            String hql = "UPDATE TuJuzgados SET"
                    + " nombre = :nombre,"
                    + " codigoDespacho = :codigoDespacho,"
                    + " activo = :activo,";
            if (obj.getGnUbicacionId() != null
                    && obj.getGnUbicacionId().getId() > 0) {
                hql += "gnUbicacionesId.id = :gnUbicacionesId, ";
            }
            hql += " gnUbicacionesId = :gnUbicacionesId,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica";
            hql += " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("codigoDespacho", obj.getCodigo_despacho());
            query.setParameter("activo", obj.getActivo());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());

            if (obj.getGnUbicacionId() != null
                    && obj.getGnUbicacionId().getId() > 0) {
                query.setParameter("gnUbicacionesId", obj.getGnUbicacionId().getId());
            }

            query.setParameter("id", obj.getId());
            query.executeUpdate();
//            int id = obj.getId(); 
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public TuJuzgado eliminar(int id) throws Exception {
        TuJuzgado obj = null;
        try {
            TuJuzgados ent = getEntityManager().find(TuJuzgados.class, id);
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
    public List<TuJuzgado> consultarJuzgadoPorUbicacion(int id) throws Exception {
        List<TuJuzgado> listResult = new ArrayList();
        try {
            String strQuery = "SELECT j FROM TuJuzgados j "
                    + "WHERE j.gnUbicacionesId.id = " + id + " "
                    + "AND j.activo = '1' "
                    + "ORDER BY j.id ASC";
            
            List<TuJuzgados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (TuJuzgados per : list) {
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
    public List<TuJuzgado> consultarJuzgadoPorUbicacion(int idUbicacion, int idJuzgado) throws Exception {
        List<TuJuzgado> listResult = new ArrayList();
        try {
            String strQuery = "(SELECT j.* "
                              + "FROM tu_juzgados j "
                             + "WHERE j.gn_ubicaciones_id = " + idUbicacion + " "
                    + "AND j.activo = '1' "
                    + "ORDER BY j.id ASC ) "
                    + ((idJuzgado > 0) ? "UNION "
                    + "(SELECT tj.* "
                       + "FROM tu_juzgados tj "
                      + "WHERE tj.id = " + idJuzgado + ""
                       + " AND tj.activo = '0' " +
                     "ORDER BY tj.id ASC "
                    + "LIMIT 1)": "");
            
            List<Object[]> lstObj =  getEntityManager().createNativeQuery(strQuery).getResultList();

            listResult = lstObj
                    .stream()
                    .map(result -> new TuJuzgado((Integer) result[0],
                        (String) result[1],
                    (String) result[2],
                    (Boolean) result[4],
                    new Ubicacion((Integer) result[3])
            )).collect(Collectors.toList());
            
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
    public static Ubicacion castEntidadNegocio(GnUbicaciones ubicacionNegiocio) {
        return (ubicacionNegiocio != null) ? UbicacionServicio.castEntidadNegocio(ubicacionNegiocio) : new Ubicacion();
    }

    public static GnUbicaciones castNegocioEntidad(Ubicacion ubicacionEntidad) {
        return (ubicacionEntidad != null) ? UbicacionServicio.castNegocioEntidad(ubicacionEntidad) : new GnUbicaciones();
    }

    public static TuJuzgado castEntidadNegocio(TuJuzgados per) {
        TuJuzgado obj = new TuJuzgado();
        obj.setId(per.getId());
        obj.setNombre(per.getNombre());
        obj.setCodigo_despacho(per.getCodigoDespacho());
        obj.setActivo(per.getActivo());
        if (per.getGnUbicacionesId() != null) {
            obj.setGnUbicacionId(new Ubicacion(per.getGnUbicacionesId().getId()));
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

    public static TuJuzgados castNegocioEntidad(TuJuzgado obj) {
        TuJuzgados per = new TuJuzgados();
        per.setId(obj.getId());
        per.setNombre(obj.getNombre());
        per.setCodigoDespacho(obj.getCodigo_despacho());
        per.setActivo(obj.getActivo());
        if (obj.getGnUbicacionId() != null) {
            per.setGnUbicacionesId(new GnUbicaciones(obj.getGnUbicacionId().getId()));
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
}
