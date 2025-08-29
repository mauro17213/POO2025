/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.servicios.cargas;

import com.saviasaludeps.savia.dominio.cargas.CarProceso;
import com.saviasaludeps.savia.dominio.cargas.CarProcesoUsuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CarProcesoUsuarios;
import com.saviasaludeps.savia.ejb.entidades.CarProcesos;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import static com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria.CmGrupoUsuarioServicio.castUsuarioEntidadNegocio;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cargas.CarProcesoUsuarioRemoto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import javax.persistence.Query;

/**
 *
 * @author aguevara
 */
@Stateless
@Remote(CarProcesoUsuarioRemoto.class)
public class CarProcesoUsuarioServicio extends GenericoServicio implements CarProcesoUsuarioRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CarProcesoUsuarios c "
                    + " WHERE 1 = 1 ";
            boolean agregarFiltro = true;
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        //Campos prestadores procesos

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
    public List<CarProcesoUsuario> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CarProcesoUsuario> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM CarProcesoUsuarios c WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
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
            List<CarProcesoUsuarios> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CarProcesoUsuarios usuarios : list) {
                listaResultados.add(castEntidadNegocio(usuarios));
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
    public CarProcesoUsuario consultar(int id) throws java.lang.Exception {

        CarProcesoUsuario objRes = null;
        try {

            objRes = castEntidadNegocio((CarProcesoUsuarios) getEntityManager().find(CarProcesoUsuarios.class, id));
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insertar(CarProcesoUsuario obj) throws java.lang.Exception {
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
    public CarProcesoUsuario eliminar(int id) throws java.lang.Exception {
        CarProcesoUsuario obj = null;
        try {
            CarProcesoUsuarios ent = getEntityManager().find(CarProcesoUsuarios.class, id);
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
    public void actualizar(CarProcesoUsuario obj) throws java.lang.Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE CarProcesoUsuarios a SET ";
            strQuery += "a.gnUsuariosId.id = :gnUsuariosId ,";
            strQuery += "a.carProcesosId.id = :carProcesosId ,";
            strQuery += "a.activo = :activo ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            if (obj.getUsuario() != null) {
                query.setParameter("gnUsuariosId", obj.getUsuario().getId());
            } else {
                query.setParameter("gnUsuariosId", null);
            }
            if (obj.getProceso() != null) {
                query.setParameter("carProcesosId", obj.getProceso().getId());
            } else {
                query.setParameter("carProcesosId", null);
            }
            query.setParameter("activo", obj.isActivo());
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

    private CarProcesoUsuario castEntidadNegocio(CarProcesoUsuarios entidad) {
        CarProcesoUsuario neg = new CarProcesoUsuario();
        neg.setId(entidad.getId());
        neg.setActivo(entidad.getActivo());
        neg.setAdmin(entidad.getAdmin());

        //Objetos
        if (entidad.getCarProcesosId() != null) {
            neg.setProceso(castProcesoEntidadNegocio(entidad.getCarProcesosId()));
        }

        if (entidad.getGnUsuariosId().getId() != null) {
            neg.setUsuario(castUsuarioEntidadNegocio(entidad.getGnUsuariosId()));
        }

        //auditoria
        neg.setUsuarioCrea(entidad.getUsuarioCrea());
        neg.setTerminalCrea(entidad.getTerminalCrea());
        neg.setFechaHoraCrea(entidad.getFechaHoraCrea());
        neg.setUsuarioModifica(entidad.getUsuarioModifica());
        neg.setTerminalModifica(entidad.getTerminalModifica());
        neg.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return neg;

    }

    private static CarProcesoUsuarios castNegocioEntidad(CarProcesoUsuario obj) {
        CarProcesoUsuarios per = new CarProcesoUsuarios();
        per.setId(obj.getId());
        per.setActivo(obj.isActivo());
        per.setAdmin(obj.isAdmin());

        //Objetos
        if (obj.getProceso() != null) {
            per.setCarProcesosId(new CarProcesos(obj.getProceso().getId()));
        }

        if (obj.getUsuario().getId() != null) {
            per.setGnUsuariosId(new GnUsuarios(obj.getUsuario().getId()));
        }
        //auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        return per;

    }

    private CarProceso castProcesoEntidadNegocio(CarProcesos entidad) {
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

    @Override
    public List<CarProcesoUsuario> listarPorIdProceso(int idProceso) throws Exception {
        List<CarProcesoUsuario> listaResultado = new ArrayList<>();
        try {
            String strQuery = "FROM CarProcesoUsuarios c "
                    + " WHERE c.carProcesosId.id = " + idProceso
                    + " ORDER BY c.id DESC";
            List<CarProcesoUsuarios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CarProcesoUsuarios proceso : list) {
                listaResultado.add(castEntidadNegocio(proceso));
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
    public List<CarProcesoUsuario> listarPorIdUsuario(int id) throws Exception {
        Set<CarProcesoUsuario> setResultado = new HashSet<>();  // Usamos un Set para evitar duplicados
        try {
            String strQuery = "FROM CarProcesoUsuarios c "
                    + "WHERE c.gnUsuariosId.id = :usuarioId "
                    + "ORDER BY c.id DESC";

            // Ejecutamos la consulta con parámetros seguros
            List<CarProcesoUsuarios> list = getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("usuarioId", id)
                    .getResultList();

            // Añadimos los resultados al Set para evitar duplicados
            for (CarProcesoUsuarios proceso : list) {
                setResultado.add(castEntidadNegocio(proceso));
            }
        } catch (NoResultException e) {
            setResultado = new HashSet<>();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }

        // Convertimos el Set a una lista antes de devolver el resultado
        return new ArrayList<>(setResultado);
    }

    @Override
    public List<CarProceso> listarProcesosUnicosPorUsuario(int idUsuario, ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CarProceso> listaProcesos = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder(
                    "SELECT pu.carProcesosId "
                    + "FROM CarProcesoUsuarios pu "
                    + "WHERE pu.gnUsuariosId.id = :idUsuario "
            );

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry<String, Object> filtro : paramConsulta.getFiltros().entrySet()) {
                    if ("procesoNombre".equals(filtro.getKey())) {
                        strQuery.append("AND pu.carProcesosId.nombre = :procesoNombre ");
                    }
                }
            }

            Query query = getEntityManager().createQuery(strQuery.toString());

            query.setParameter("idUsuario", idUsuario);

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry<String, Object> filtro : paramConsulta.getFiltros().entrySet()) {
                    if ("procesoNombre".equals(filtro.getKey())) {
                        query.setParameter("procesoNombre", filtro.getValue());
                    }
                }
            }

            List<CarProcesos> list = query.getResultList();
            for (CarProcesos proceso : list) {
                CarProceso car = castProcesoEntidadNegocio(proceso);
                listaProcesos.add(car);
            }
        } catch (Exception e) {
            System.err.println("Hubo un fallo al listar procesos de usuario:"+e.getMessage());
        }
        return listaProcesos;
    }

}
