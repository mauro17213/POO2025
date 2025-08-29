/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.ModuloManual;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.GnModuloManuales;
import com.saviasaludeps.savia.ejb.entidades.GnModulos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.administracion.ModuloManualRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(ModuloManualRemoto.class)
public class ModuloManualServicio extends GenericoServicio implements ModuloManualRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;

        String strQuery = "SELECT COUNT(mm) FROM GnModuloManuales mm WHERE 1 = 1";
        try {
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND mm.gnModulosId.id = " + (String) e.getValue() + " ";
                            break;
                        case "nombre":
                            strQuery += " AND mm.nombre = " + (String) e.getValue() + " ";
                            break;
                        case "descripcion":
                            strQuery += " AND mm.descripcion = " + (String) e.getValue() + " ";
                            break;
                        case "version":
                            strQuery += " AND mm.version = " + (String) e.getValue() + " ";
                            break;
                        case "fechaVersion":
                            strQuery += " AND mm.fechaVersion = " + (String) e.getValue() + " ";
                            break;
                        case "ruta":
                            strQuery += " AND mm.ruta = " + (String) e.getValue() + " ";
                            break;
                        case "archivo":
                            strQuery += " AND mm.archivo = " + (String) e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += " AND mm.usuarioCrea = " + (String) e.getValue() + " ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += " AND mm.fechaHoraCrea = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }

            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }

        return cant;
    }

    @Override
    public List<ModuloManual> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<ModuloManual> listResult = new ArrayList<>();

        String strQuery = "SELECT mm FROM GnModuloManuales mm WHERE 1 = 1 ";
        try {

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND mm.gnModulosId.id = " + (String) e.getValue() + " ";
                            break;
                        case "nombre":
                            strQuery += " AND mm.nombre = " + (String) e.getValue() + " ";
                            break;
                        case "descripcion":
                            strQuery += " AND mm.descripcion = " + (String) e.getValue() + " ";
                            break;
                        case "version":
                            strQuery += " AND mm.version = " + (String) e.getValue() + " ";
                            break;
                        case "fechaVersion":
                            strQuery += " AND mm.fechaVersion = " + (String) e.getValue() + " ";
                            break;
                        case "ruta":
                            strQuery += " AND mm.ruta = " + (String) e.getValue() + " ";
                            break;
                        case "archivo":
                            strQuery += " AND mm.archivo = " + (String) e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += " AND mm.usuarioCrea = " + (String) e.getValue() + " ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += " AND mm.fechaHoraCrea = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }

            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "mm." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "mm.fechaVersion DESC, mm.fechaHoraCrea DESC";
            }

            List<GnModuloManuales> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();

            for (GnModuloManuales item : list) {
                listResult.add(castEntidadNegocio(item));
            }

        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }

        return listResult;
    }
    
    @Override
    public ModuloManual consultar(int id) throws Exception {
        ModuloManual objResult = new ModuloManual();

        try {
            objResult = castEntidadNegocio(getEntityManager().find(GnModuloManuales.class, id));
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
    public ModuloManual consultarXModulo(int id, boolean actual, int tipo) throws Exception {
        ModuloManual ObjectResult = new ModuloManual();

        String sql = "FROM GnModuloManuales mm WHERE mm.gnModulosId.id = :id AND mm.actual = :actual and tipo = :tipo ";

        try {
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("id", id);
            query.setParameter("actual", actual);
            query.setParameter("tipo", tipo);
            List<GnModuloManuales> list = query.setMaxResults(1).getResultList();

            for (GnModuloManuales item : list) {
                ObjectResult  = castEntidadNegocio(item);
            }
        } catch (NoResultException e) {
            ObjectResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return ObjectResult;
    }
    
    @Override
    public int insertar(ModuloManual objeto) throws Exception {
        int id = 0;
        
        try {
            id = getEntityManager().merge(castNegocioEntidad(objeto)).getId();
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        
        return id;
    }
    
    @Override
    public void actualizarActual(int id, boolean actual) throws Exception {
        try {
            String sql = "UPDATE GnModuloManuales SET actual = :actual WHERE id = :id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("actual", actual);
            query.setParameter("id", id);            
            query.executeUpdate();            
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarXModulo(int modulos_id, int tipo) throws Exception {
        try {
            String sql = "UPDATE GnModuloManuales SET actual = :actual WHERE gnModulosId.id = :id and tipo = :tipo";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("actual", Boolean.FALSE);
            query.setParameter("id", modulos_id);            
            query.setParameter("tipo", tipo);            
            query.executeUpdate();            
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    public static ModuloManual castEntidadNegocio(GnModuloManuales per) {
        ModuloManual obj = new ModuloManual();
        obj.setActual(per.getActual());
        obj.setArchivo(per.getArchivo());
        obj.setDescripcion(per.getDescripcion());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setFechaVersion(per.getFechaVersion());
        obj.setId(per.getId());
        if (per.getGnModulosId() != null) {
            obj.setModulo(new Modulo(per.getGnModulosId().getId()));
            obj.getModulo().setNombre(per.getGnModulosId().getNombre());
        }
        obj.setNombre(per.getNombre());
        obj.setRuta(per.getRuta());
        obj.setTipo(per.getTipo());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setVersion(per.getVersion());

        return obj;
    }

    public static GnModuloManuales castNegocioEntidad(ModuloManual obj) {
        GnModuloManuales per = new GnModuloManuales();
        per.setActual(obj.isActual());
        per.setArchivo(obj.getArchivo());
        per.setDescripcion(obj.getDescripcion());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setFechaVersion(obj.getFechaVersion());
        per.setId(obj.getId());
        if (obj.getModulo() != null) {
            per.setGnModulosId(new GnModulos(obj.getModulo().getId()));
            per.getGnModulosId().setNombre(obj.getModulo().getNombre());
        }
        per.setNombre(obj.getNombre());
        per.setTipo(obj.getTipo());
        per.setRuta(obj.getRuta());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setVersion(obj.getVersion());
        return per;
    }

}
