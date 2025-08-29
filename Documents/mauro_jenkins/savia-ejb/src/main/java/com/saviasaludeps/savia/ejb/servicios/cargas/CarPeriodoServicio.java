/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.servicios.cargas;

import com.saviasaludeps.savia.dominio.cargas.CarPeriodo;
import com.saviasaludeps.savia.dominio.cargas.CarProceso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CarPeriodos;
import com.saviasaludeps.savia.ejb.entidades.CarProcesos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cargas.CarPeriodoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Session;

/**
 *
 * @author aguevara
 */
@Stateless
@Remote(CarPeriodoRemoto.class)
public class CarPeriodoServicio extends GenericoServicio implements CarPeriodoRemoto{

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CarPeriodos c "
                    + " WHERE c.carProcesosId.id = " + paramConsulta.getParametroConsulta1();
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
    public List<CarPeriodo> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CarPeriodo> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM CarPeriodos c "
                    + " WHERE c.carProcesosId.id = " + paramConsulta.getParametroConsulta1();
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
            List<CarPeriodos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CarPeriodos periodo : list) {
                listaResultados.add(castEntidadNegocio(periodo));
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
    public CarPeriodo consultar(int id) throws java.lang.Exception {
        
        CarPeriodo objRes = null;
        try {

            objRes = castEntidadNegocio((CarPeriodos) getEntityManager().find(CarPeriodos.class, id));
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
    public int insertar(CarPeriodo obj) throws java.lang.Exception {
        int _id = 0;
        try {
            CarPeriodos per = castNegocioEntidad(obj);
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
    public CarPeriodo eliminar(int id) throws java.lang.Exception {
        CarPeriodo obj = null;
        try {
            CarPeriodos ent = getEntityManager().find(CarPeriodos.class, id);
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
    public void actualizar(CarPeriodo obj) throws java.lang.Exception {
        try {
            CarPeriodos carProcesoPrestadores = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE CarPeriodos a SET ";
            strQuery += " a.nombre = :nombre ,";
            strQuery += " a.fechaInicio = :fechaInicio ,";
            strQuery += " a.fechaFin = :fechaFin ,";
            strQuery += " a.usuarioModifica = :usuarioModifica ,";
            strQuery += " a.fechaHoraModifica = :fechaHoraModifica ,";
            strQuery += " a.terminalModifica = :terminalModifica ";
            
            //campos objetos
            if (carProcesoPrestadores.getCarProcesosId() != null) {
                strQuery += ", a.carProcesosId.id = " + carProcesoPrestadores.getCarProcesosId().getId() + " ";
            }
            
            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(carProcesoPrestadores);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }

    }

    @Override
    public List<CarPeriodo> listarPorIdProceso(int id) throws java.lang.Exception {
        List<CarPeriodo> listaResultado = new ArrayList<>();
        try {
            String strQuery = "FROM CarPeriodos c "
                    + " WHERE c.carProcesosId.id = "+id
                    + " ORDER BY c.id DESC";
            List<CarPeriodos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CarPeriodos proceso : list) {
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
    public int consultarCantidadListaTotal(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CarPeriodos c "
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
    public List<CarPeriodo> consultarListaTotal(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CarPeriodo> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM CarPeriodos c WHERE 1 = 1";                  
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
            List<CarPeriodos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CarPeriodos periodos : list) {
                listaResultados.add(castEntidadNegocio(periodos));
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

    private CarPeriodo castEntidadNegocio(CarPeriodos entidad) {
        CarPeriodo negocio = new CarPeriodo();
        negocio.setId(entidad.getId());
        //Objetos
        if(entidad.getCarProcesosId()!=null){
            negocio.setCarProceso(castProcesoEntidadNegocio(entidad.getCarProcesosId()));
        }
        negocio.setNombre(entidad.getNombre());
        negocio.setFechaInicio(entidad.getFechaInicio());
        negocio.setFechaFin(entidad.getFechaFin());

        //Auditoria
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        return negocio;

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


    private CarPeriodos castNegocioEntidad(CarPeriodo obj) {
         CarPeriodos per = new CarPeriodos();
        per.setId(obj.getId());

        //Objetos
        if(obj.getCarProceso()!=null){
            per.setCarProcesosId(new CarProcesos(obj.getCarProceso().getId()));
        }
        per.setNombre(obj.getNombre());
        per.setFechaInicio(obj.getFechaInicio());
        per.setFechaFin(obj.getFechaFin());

        //Auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        return per;

    }

    
    
}
