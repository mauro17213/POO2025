/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.maestro;


import com.saviasaludeps.savia.dominio.maestro.MaTecnologiaServicioHabilitacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaServicioHabilitacion;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.ejb.entidades.MaServiciosHabilitacion;
import com.saviasaludeps.savia.ejb.entidades.MaTecnologiaServiciosHabilitacion;
import com.saviasaludeps.savia.ejb.entidades.MaTecnologias;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.maestro.MaTecnologiaServicioHabilitacionRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author jyperez
 */
@Stateless
@Remote(MaTecnologiaServicioHabilitacionRemoto.class)
public class MaTecnologiaServicioHabilitacionServicio extends GenericoServicio implements MaTecnologiaServicioHabilitacionRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaTecnologiaServiciosHabilitacion p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public List<MaTecnologiaServicioHabilitacion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<MaTecnologiaServicioHabilitacion> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaTecnologiaServiciosHabilitacion p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                        case "maTecnologiaId":
                            strQuery += " AND p.maTecnologiasId.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<MaTecnologiaServiciosHabilitacion> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (MaTecnologiaServiciosHabilitacion per : list) {
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
    public MaTecnologiaServicioHabilitacion consultar(int id) throws Exception {
        MaTecnologiaServicioHabilitacion objRes = null;
        try {
            objRes = castEntidadNegocio((MaTecnologiaServiciosHabilitacion) getEntityManager().find(MaTecnologiaServiciosHabilitacion.class, id));
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
    public int insertar(MaTecnologiaServicioHabilitacion obj) throws Exception {
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
    public void actualizar(MaTecnologiaServicioHabilitacion obj) throws Exception {
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
    public MaTecnologiaServicioHabilitacion eliminar(int id) throws Exception {
        MaTecnologiaServicioHabilitacion obj = null;
        try {
            MaTecnologiaServiciosHabilitacion ent = getEntityManager().find(MaTecnologiaServiciosHabilitacion.class, id);
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
    public List<MaTecnologiaServicioHabilitacion> consultarTodos() throws Exception {
        List<MaTecnologiaServicioHabilitacion> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaTecnologiaServiciosHabilitacion p "
                    + "ORDER BY p.id ";
            List<MaTecnologiaServiciosHabilitacion> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaTecnologiaServiciosHabilitacion per : list) {
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
    public List<MaTecnologiaServicioHabilitacion> consultarPorTecnologia(int id) throws Exception {
        List<MaTecnologiaServicioHabilitacion> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaTecnologiaServiciosHabilitacion p WHERE p.maTecnologiasId.id = " + id
                    + " ORDER BY p.id ";
            List<MaTecnologiaServiciosHabilitacion> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaTecnologiaServiciosHabilitacion per : list) {
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
    public MaTecnologiaServicioHabilitacion consultarPorTecnologiaYServicio(int idTecnologia, int idServicio) throws Exception {
        MaTecnologiaServicioHabilitacion objRes = null;
        try {
            String strQuery = "FROM MaTecnologiaServiciosHabilitacion p WHERE p.maTecnologiasId.id = " + idTecnologia +
                    " AND p.maServiciosHabilitacionId.id = " + idServicio  + " ";
            
            objRes = castEntidadNegocio((MaTecnologiaServiciosHabilitacion) getEntityManager().createQuery(strQuery).getSingleResult());
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    public static MaTecnologiaServicioHabilitacion castEntidadNegocio(MaTecnologiaServiciosHabilitacion per) {
        MaTecnologiaServicioHabilitacion obj = new MaTecnologiaServicioHabilitacion();
        obj.setId(per.getId());
        obj.setActivo(per.getActivo());
        //objetos
        if (per.getMaServiciosHabilitacionId() != null) {
            obj.setMaServicioHabilitacion(castServicioHabilitacionEntidadNegocio(per.getMaServiciosHabilitacionId()));
        }
        if(per.getMaTecnologiasId() != null) {
            obj.setMaTecnologia(new MaTecnologia(per.getMaTecnologiasId().getId()));
        }
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }

    public static MaTecnologiaServiciosHabilitacion castNegocioEntidad(MaTecnologiaServicioHabilitacion obj) {
        MaTecnologiaServiciosHabilitacion per = new MaTecnologiaServiciosHabilitacion();
        per.setId(obj.getId());
        per.setActivo(obj.isActivo());
                //objetos
        if (obj.getMaServicioHabilitacion() != null) {
            per.setMaServiciosHabilitacionId(new MaServiciosHabilitacion(obj.getMaServicioHabilitacion().getId()));
        }
        if(obj.getMaTecnologia() != null) {
            per.setMaTecnologiasId(new MaTecnologias(obj.getMaTecnologia().getId()));
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
    
    public static MaServicioHabilitacion castServicioHabilitacionEntidadNegocio(MaServiciosHabilitacion ent) {        
        MaServicioHabilitacion obj = new MaServicioHabilitacion();
        obj.setId(ent.getId());
        obj.setCodigo(ent.getCodigo());
        obj.setNombre(ent.getNombre());
        obj.setActivo(ent.getActivo());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        return obj;
    }

    @Override
    public MaTecnologiaServicioHabilitacion consultarPorTecnologiaYCodigoServicio(int idTecnologia, String codigoServicio) throws java.lang.Exception {
        MaTecnologiaServicioHabilitacion objRes = null;
        int i = 0;
        try {
            String strQuery = "FROM MaTecnologiaServiciosHabilitacion p WHERE p.maTecnologiasId.id = " + idTecnologia +
                    " AND p.maServiciosHabilitacionId.codigo = " + codigoServicio  + " ";
            
            List<MaTecnologiaServiciosHabilitacion> list = getEntityManager().createQuery(strQuery).getResultList();
            for (MaTecnologiaServiciosHabilitacion obj : list) {
                if (i == 0) {
                    objRes = castEntidadNegocio(obj);
                    i++;
                }
            }
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
    public int consultarCantidadPorServicioHabilitacion(int idServicio) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaTecnologiaServiciosHabilitacion p "
                    + "WHERE p.id > 0 AND p.maServiciosHabilitacionId.id = " + idServicio;
            
            cant = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }
    
}
