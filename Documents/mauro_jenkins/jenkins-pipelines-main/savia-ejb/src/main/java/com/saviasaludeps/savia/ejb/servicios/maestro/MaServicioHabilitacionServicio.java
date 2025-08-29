/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.maestro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaServicioHabilitacion;
import com.saviasaludeps.savia.ejb.entidades.MaServiciosHabilitacion;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.maestro.MaServicioHabilitacionRemoto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author rpalacios
 */
@Stateless
@Remote(MaServicioHabilitacionRemoto.class)
public class MaServicioHabilitacionServicio extends GenericoServicio implements MaServicioHabilitacionRemoto {

    @Override
    public List<MaServicioHabilitacion> consultarTodos() throws Exception {
        List<MaServicioHabilitacion> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaServiciosHabilitacion m "
                    + "ORDER BY m.id ";
            List<MaServiciosHabilitacion> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaServiciosHabilitacion per : list) {
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
    
    
    public static MaServicioHabilitacion castEntidadNegocio(MaServiciosHabilitacion ent) {        
        MaServicioHabilitacion obj = new MaServicioHabilitacion();
        obj.setId(ent.getId());
        obj.setCodigo(ent.getCodigo());
        obj.setNombre(ent.getNombre());
        obj.setActivo(ent.getActivo());
        obj.setMaeGrupoId(ent.getMaeGrupoId());
        obj.setMaeGrupoCodigo(ent.getMaeGrupoCodigo());
        obj.setMaeGrupoValor(ent.getMaeGrupoValor());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        return obj;
    }
    
        @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaServiciosHabilitacion p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigo":
                            strQuery += " AND p.codigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "codigoExacto":
                            strQuery += " AND p.codigo = '" + e.getValue() + "' ";
                            break;
                        case "nombre":
                            strQuery += " AND p.nombre  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                        case "maeGrupoId":
                            strQuery += " AND p.maeGrupoId = " + e.getValue() + " ";
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
    public List<MaServicioHabilitacion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<MaServicioHabilitacion> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaServiciosHabilitacion p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigo":
                            strQuery += " AND p.codigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += " AND p.nombre  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                        case "codigoExacto":
                            strQuery += " AND p.codigo = " + e.getValue() + " ";
                            break;
                        case "maeGrupoId":
                            strQuery += " AND p.maeGrupoId = " + e.getValue() + " ";
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
            List<MaServiciosHabilitacion> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (MaServiciosHabilitacion per : list) {
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
    public List<MaServicioHabilitacion> consultarListaTodo(ParamConsulta paramConsulta) throws Exception {
        List<MaServicioHabilitacion> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaServiciosHabilitacion p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigo":
                            strQuery += " AND p.codigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += " AND p.nombre  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
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
            List<MaServiciosHabilitacion> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaServiciosHabilitacion per : list) {
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
    public MaServicioHabilitacion consultar(int id) throws Exception {
        MaServicioHabilitacion objRes = null;
        try {
            objRes = castEntidadNegocio((MaServiciosHabilitacion) getEntityManager().find(MaServiciosHabilitacion.class, id));
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
    public int insertar(MaServicioHabilitacion obj) throws Exception {
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
    public void actualizar(MaServicioHabilitacion obj) throws Exception {
        try {
            MaServiciosHabilitacion servicio = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            //getEntityManager().merge(castNegocioEntidad(obj));
            String strQuery = "UPDATE MaServiciosHabilitacion a SET ";
            strQuery += " a.codigo = :codigo ,";
            strQuery += " a.nombre = :nombre ,";
            strQuery += " a.activo = :activo ,";
            strQuery += " a.maeGrupoId = :maeGrupoId ,";
            strQuery += " a.maeGrupoCodigo = :maeGrupoCodigo ,";
            strQuery += " a.maeGrupoValor = :maeGrupoValor ,";
            //campos auditoria
            strQuery += " a.usuarioModifica = :usuarioModifica ,";
            strQuery += " a.terminalModifica = :terminalModifica ,";
            //campo fechas
            SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaModifica = formatoFechaHora.format(obj.getFechaHoraModifica());
            strQuery += " a.fechaHoraModifica = '" + fechaModifica + "' ";
            //campos objetos
            
            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(servicio);
            query.executeUpdate();
            
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public MaServicioHabilitacion eliminar(int id) throws Exception {
        MaServicioHabilitacion obj = null;
        try {
            MaServiciosHabilitacion ent = getEntityManager().find(MaServiciosHabilitacion.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                Query q = getEntityManager().createQuery("DELETE MaServiciosHabilitacion a WHERE a.id = " + id);
                q.executeUpdate();
//                getEntityManager().remove(ent);
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

    public static MaServiciosHabilitacion castNegocioEntidad(MaServicioHabilitacion obj) {
        MaServiciosHabilitacion per = new MaServiciosHabilitacion();
        per.setId(obj.getId());
        per.setCodigo(obj.getCodigo());
        per.setNombre(obj.getNombre());
        per.setActivo(obj.isActivo());
        per.setMaeGrupoId(obj.getMaeGrupoId());
        per.setMaeGrupoCodigo(obj.getMaeGrupoCodigo());
        per.setMaeGrupoValor(obj.getMaeGrupoValor());
        //auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        return per;
    }
    
    @Override
    public MaServicioHabilitacion consultarPorCodigo(int codigo) throws Exception {
        MaServicioHabilitacion objResult = null;
        try {
            String strQuery = "FROM MaServiciosHabilitacion s "
                    + "WHERE s.codigo = " + codigo;
            List<MaServiciosHabilitacion> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
           if(list != null && !list.isEmpty()) {
                objResult = castEntidadNegocio(list.get(0));
           }
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
    public HashMap<String, MaServicioHabilitacion> consultarHash() throws Exception {
        HashMap<String,MaServicioHabilitacion> hashResult = new HashMap();
        try {
            String strQuery = "FROM MaServiciosHabilitacion m "
                    + "ORDER BY m.id ";
            List<MaServiciosHabilitacion> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaServiciosHabilitacion per : list) {
                MaServicioHabilitacion obj = castEntidadNegocio(per);
                hashResult.put(String.valueOf(obj.getCodigo()), obj);
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
    public List<MaServicioHabilitacion> consultarTodoSingleton() throws Exception {
        List<MaServicioHabilitacion> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t.id, "
                    + "t.activo, "
                    + "t.codigo, "
                    + "t.nombre "
                    + "FROM MaServiciosHabilitacion t ORDER by t.id ";
            Query q = getEntityManager().createQuery(strQuery);
            List<Object[]> lista = q.getResultList();
            for (Object[] per : lista) {
                MaServicioHabilitacion diagnostico = new MaServicioHabilitacion();
                diagnostico.setId((Integer) per[0]);
                diagnostico.setActivo((Boolean) per[1]);
                diagnostico.setCodigo((Integer) per[2]);
                diagnostico.setNombre(per[3].toString());
                listResult.add(diagnostico);
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
