/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.GnUbicacionBarrio;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.GnUbicacionBarrios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.administracion.GnUbicacionBarrioRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author sgiraldov
 */
@Stateless
@Remote(GnUbicacionBarrioRemoto.class)
public class GnUbicacionBarrioServicio extends GenericoServicio implements GnUbicacionBarrioRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(b) FROM GnUbicacionBarrios b "
                    + " WHERE 1 = 1 ";            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND b.id = " + e.getValue() + " ";
                            break;
                        case "gnUbicacionesId":
                            strQuery += "AND b.gnUbicacionesId = " + e.getValue() + " ";
                            break;
                        case "codigoBarrio":
                            strQuery += "AND b.codigoBarrio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += "AND b.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "codigoComuna":
                            strQuery += "AND b.codigoComuna LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "comuna":
                            strQuery += "AND b.comuna LIKE '%" + e.getValue() + "%' ";
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
    public List<GnUbicacionBarrio> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GnUbicacionBarrio> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM GnUbicacionBarrios b WHERE 1 = 1 ";
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND b.id = " + e.getValue() + " ";
                            break;
                        case "gnUbicacionesId":
                            strQuery += "AND b.gnUbicacionesId = " + e.getValue() + " ";
                            break;
                        case "codigoBarrio":
                            strQuery += "AND b.codigoBarrio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += "AND b.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "codigoComuna":
                            strQuery += "AND b.codigoComuna LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "comuna":
                            strQuery += "AND b.comuna LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "b." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "b.id DESC";
            }
            List<GnUbicacionBarrios> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GnUbicacionBarrios alerta : list) {
                listaResultados.add(castEntidadNegocio(alerta));
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
    public GnUbicacionBarrio consultar(int id) throws Exception {
        GnUbicacionBarrio objRes = null;
        try {
            objRes = castEntidadNegocio((GnUbicacionBarrios) getEntityManager().find(GnUbicacionBarrios.class, id));
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
    public int insertar(GnUbicacionBarrio obj) throws Exception {
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
    public void actualizar(GnUbicacionBarrio obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GnUbicacionBarrios b SET ";
            strQuery += "b.gnUbicacionesId = :gnUbicacionesId ,";
            strQuery += "b.codigoBarrio = :codigoBarrio ,";
            strQuery += "b.nombre = :nombre ,";
            strQuery += "b.codigoComuna = :codigoComuna ,";
            strQuery += "b.comuna = :comuna ,";
            strQuery += "b.usuarioModifica = :usuarioModifica, ";
            strQuery += "b.terminalModifica = :terminalModifica, ";
            strQuery += "b.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE b.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("gnUbicacionesId", obj.getGnUbicacionesId());
            query.setParameter("codigoBarrio", obj.getCodigoBarrio());
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("codigoComuna", obj.getCodigoComuna());
            query.setParameter("comuna", obj.getComuna());
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

    @Override
    public GnUbicacionBarrio eliminar(int id) throws Exception {
        GnUbicacionBarrio obj = null;
        try {
            GnUbicacionBarrios ent = getEntityManager().find(GnUbicacionBarrios.class, id);
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
    
    private GnUbicacionBarrio castEntidadNegocio(GnUbicacionBarrios entidad) {
        //List<AsegAfiliado> listaAfiliados = new ArrayList();
        GnUbicacionBarrio negocio = new GnUbicacionBarrio();
        negocio.setId(entidad.getId());
        negocio.setGnUbicacionesId(entidad.getGnUbicacionesId());
        negocio.setCodigoBarrio("" + entidad.getCodigoBarrio());
        negocio.setNombre(entidad.getNombre());
        negocio.setCodigoComuna("" + entidad.getCodigoComuna());
        negocio.setComuna(entidad.getComuna());
        //objetos
        /*if (entidad.getAsegAfiliadosList() != null) {
            for (AsegAfiliados af: entidad.getAsegAfiliadosList()) {
                AsegAfiliado afiliado = new AsegAfiliado(af.getId());
                listaAfiliados.add(afiliado);
            }
            negocio.setListaAfiliados(listaAfiliados);
        }*/
        //Auditoria
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        return negocio;
    }
    
    private GnUbicacionBarrios castNegocioEntidad(GnUbicacionBarrio negocio) {
        //List<AsegAfiliados> listaAfiliados = new ArrayList();
        GnUbicacionBarrios entidad = new GnUbicacionBarrios();
        entidad.setGnUbicacionesId(negocio.getGnUbicacionesId());
        entidad.setCodigoBarrio(negocio.getCodigoBarrio());
        entidad.setNombre(negocio.getNombre());
        entidad.setCodigoComuna(negocio.getCodigoComuna());
        entidad.setComuna(negocio.getComuna());
        //objetos
        /*if (negocio.getListaAfiliados() != null) {
            for (AsegAfiliado af: negocio.getListaAfiliados()) {
                AsegAfiliados afiliado = new AsegAfiliados(af.getId());
                listaAfiliados.add(afiliado);
            }
            entidad.setAsegAfiliadosList(listaAfiliados);
        }*/
        //Auditoria
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        return entidad;
    }

    @Override
    public List<GnUbicacionBarrio> consultarTodos() throws Exception {
        List<GnUbicacionBarrio> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM GnUbicacionBarrios b "+
                    " ORDER BY b.id DESC";
            List<GnUbicacionBarrios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GnUbicacionBarrios alerta : list) {
                listaResultados.add(castEntidadNegocio(alerta));
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
    public List<GnUbicacionBarrio> consultarPorUbicacion(int idUbicacion) throws Exception {
        List<GnUbicacionBarrio> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM GnUbicacionBarrios b WHERE b.gnUbicacionesId = " + idUbicacion +
                    " ORDER BY b.id DESC";   
            List<GnUbicacionBarrios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GnUbicacionBarrios alerta : list) {
                listaResultados.add(castEntidadNegocio(alerta));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            listaResultados = new ArrayList();
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }
    
}
