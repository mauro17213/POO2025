/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.webservice;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.webservice.WsConexion;
import com.saviasaludeps.savia.dominio.webservice.WsServicio;
import com.saviasaludeps.savia.ejb.entidades.WsConexiones;
import com.saviasaludeps.savia.ejb.entidades.WsConexionesServicios;
import com.saviasaludeps.savia.ejb.utilidades.Encrypt;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.webservice.WsConexionRemoto;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(WsConexionRemoto.class)
@Local(WsConexionLocal.class)
public class WsConexionServicio extends GenericoServicio implements WsConexionLocal, WsConexionRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(s) FROM WsConexiones s "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND s.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "usuario":
                            strQuery += "AND s.usuario LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND s.activo = '" + (String) e.getValue() + "' ";
                            break;
                        case "tipoAutenticacion":
                            strQuery += "AND s.tipoAutenticacion = '" + (String) e.getValue() + "' ";
                            break;
                        case "llave":
                            strQuery += "AND s.llave LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "ip":
                            strQuery += "AND s.ip LIKE '%" + (String) e.getValue() + "%' ";
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
    public List<WsConexion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<WsConexion> listResult = new ArrayList();
        try {
            String strQuery = "FROM WsConexiones s "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND s.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "usuario":
                            strQuery += "AND s.usuario LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND s.activo = '" + (String) e.getValue() + "' ";
                            break;
                        case "tipoAutenticacion":
                            strQuery += "AND s.tipoAutenticacion = '" + (String) e.getValue() + "' ";
                            break;
                        case "llave":
                            strQuery += "AND s.llave LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "ip":
                            strQuery += "AND s.ip LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "s." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "s.nombre ASC";
            }
            List<WsConexiones> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (WsConexiones per : list) {
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
    public WsConexion consultar(int id) throws Exception {
        WsConexion objRes = null;
        try {
            objRes = castEntidadNegocio((WsConexiones) getEntityManager().find(WsConexiones.class, id));
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
    public int insertar(WsConexion obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
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
    public void actualizar(WsConexion obj) throws Exception {
        try {
            getEntityManager().merge(castNegocioEntidadActualizar(obj));
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public WsConexion eliminar(int id) throws Exception {
        WsConexion obj = null;
        try {
            WsConexiones ent = getEntityManager().find(WsConexiones.class, id);
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
    public List<WsConexion> consultarTodas() throws Exception {
        List<WsConexion> listResult = new ArrayList();
        try {
            String strQuery = "FROM WsConexiones s "
                    + "ORDER BY s.nombre ASC";
            List<WsConexiones> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (WsConexiones per : list) {
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

    public static WsConexion castEntidadNegocio(WsConexiones per) {
        WsConexion obj = new WsConexion();
        obj.setId(per.getId());
        obj.setNombre(per.getNombre());
        obj.setTipoAutenticacion(per.getTipoAutenticacion());
        obj.setUsuario(per.getUsuario());
        obj.setContrasena(per.getContrasena());
        obj.setLlave(per.getLlave());
        obj.setIp(per.getIp());
        obj.setActivo(per.getActivo());
        List<WsConexionesServicios> listaPer2 = per.getWsConexionesServiciosList();
        List<WsServicio> listaServicios = new ArrayList();
        for(WsConexionesServicios per2 : listaPer2){
            listaServicios.add(new WsServicio(per2.getId()));
        }
        obj.setListaWsServicios(listaServicios);
        obj.setTipoAutenticacion(per.getTipoAutenticacion());
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static WsConexiones castNegocioEntidad(WsConexion obj) {
        WsConexiones per = new WsConexiones();
        per.setId(obj.getId());
        per.setNombre(obj.getNombre());
        per.setTipoAutenticacion(obj.getTipoAutenticacion());
        per.setUsuario(obj.getUsuario());
        per.setContrasena(Encrypt.sha512(obj.getContrasena()));
        per.setLlave(obj.getLlave());
        per.setIp(obj.getIp());
        per.setActivo(obj.isActivo());
        
        //Auditoria
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }
    
    public static WsConexiones castNegocioEntidadActualizar(WsConexion obj) {
        WsConexiones per = new WsConexiones();
        per.setId(obj.getId());
        per.setNombre(obj.getNombre());
        per.setTipoAutenticacion(obj.getTipoAutenticacion());
        per.setUsuario(obj.getUsuario());
        if (obj.getContrasenaNueva() != null && !obj.getContrasenaNueva().trim().isEmpty()) {
            obj.setContrasena(Encrypt.sha512(obj.getContrasenaNueva()));
        } else {
            per.setContrasena(obj.getContrasena());
        }
        per.setLlave(obj.getLlave());
        per.setIp(obj.getIp());
        per.setActivo(obj.isActivo());
        
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
