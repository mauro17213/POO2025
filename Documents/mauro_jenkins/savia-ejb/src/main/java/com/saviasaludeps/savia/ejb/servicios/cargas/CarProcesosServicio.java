/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.servicios.cargas;

import com.saviasaludeps.savia.dominio.cargas.CarProceso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CarProcesos;
import com.saviasaludeps.savia.negocio.cargas.CarProcesosRemoto;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author aguevara
 */
@Stateless
@Remote(CarProcesosRemoto.class)
public class CarProcesosServicio extends GenericoServicio implements CarProcesosRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CarProcesos c "
                    + " WHERE 1 = 1 ";
            boolean agregarFiltro = true;
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {

                        case "nombre":
                            strQuery += "AND c.nombre  LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND c.activo  = " + (String) e.getValue() + " ";
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
    public List<CarProceso> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CarProceso> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM CarProcesos c WHERE 1 = 1";
            boolean agregarFiltro = true;
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {

                        case "nombre":
                            strQuery += "AND c.nombre  LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND c.activo  = " + (String) e.getValue() + " ";
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
            List<CarProcesos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CarProcesos procesos : list) {
                listaResultados.add(castEntidadNegocio(procesos));
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
    public CarProceso consultar(int id) throws java.lang.Exception {
        CarProceso objRes = null;
        try {

            objRes = castEntidadNegocio((CarProcesos) getEntityManager().find(CarProcesos.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            objRes = null;
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public boolean validarExiste(String nombre) throws java.lang.Exception {
        boolean existe = false;
        try {
            String strQuery = "FROM CarProcesos a "
                    + "WHERE a.nombre = '" + nombre + "' ";
            List<CarProcesos> list = getEntityManager().createQuery(strQuery).getResultList();
            if (list != null && list.size() > 0) {
                existe = true;
            }
        } catch (NoResultException e) {
            existe = false;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return existe;
    }

    @Override
    public int insertar(CarProceso obj) throws Exception {

        int _id = 0;
        try {
            CarProcesos per = castNegocioEntidad(obj);
            _id = (int) getEntityManager().merge(per).getId();
            per.setId(_id);
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
    public CarProceso eliminar(int id) throws java.lang.Exception {
        CarProceso obj = null;
        try {
            CarProcesos ent = getEntityManager().find(CarProcesos.class, id);
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
    public void actualizar(CarProceso obj) throws java.lang.Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE CarProcesos c SET ";
            strQuery += "c.nombre = :nombre ,";
            strQuery += "c.descripcion = :descripcion ,";
            strQuery += "c.activo = :activo ,";
            strQuery += "c.editable = :editable ,";
            strQuery += "c.estructuraJson = :estructuraJson ,";
            strQuery += "c.usuarioModifica = :usuarioModifica ,";
            strQuery += "c.terminalModifica = :terminalModifica ,";
            strQuery += "c.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE c.id = :id ";

            Query query = session.createQuery(strQuery);
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("descripcion", obj.getDescripcion());
            query.setParameter("activo", obj.isActivo());
            query.setParameter("editable", obj.isEditable());
            query.setParameter("estructuraJson", obj.getEstructuraJson());
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

    private CarProcesos castNegocioEntidad(CarProceso negocio) {
        CarProcesos entidad = new CarProcesos();

        entidad.setId(negocio.getId());
        entidad.setNombre(negocio.getNombre());
        entidad.setDescripcion(negocio.getDescripcion());
        entidad.setActivo(negocio.isActivo());
        entidad.setEditable(negocio.isEditable());
        entidad.setEstructuraJson(negocio.getEstructuraJson());

        //Auditoria
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        return entidad;
    }

    private CarProceso castEntidadNegocio(CarProcesos entidad) {
        CarProceso negocio = new CarProceso();
        negocio.setId(entidad.getId());
        negocio.setNombre(entidad.getNombre());
        negocio.setDescripcion(entidad.getDescripcion());
        negocio.setActivo(entidad.getActivo());
        negocio.setEditable(entidad.getEditable());
        negocio.setEstructuraJson(entidad.getEstructuraJson());

        //Auditoria
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        return negocio;
    }

}
