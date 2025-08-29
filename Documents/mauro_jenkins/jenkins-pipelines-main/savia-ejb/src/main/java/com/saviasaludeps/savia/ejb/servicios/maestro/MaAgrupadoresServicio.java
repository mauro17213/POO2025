/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.maestro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaAgrupadoresMedicamentos;
import com.saviasaludeps.savia.ejb.entidades.MaAgrupadoresMedicamento;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.maestro.MaAgrupadoresMedicamentoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author jarodriguez
 */
@Stateless
@Remote(MaAgrupadoresMedicamentoRemoto.class)
public class MaAgrupadoresServicio extends GenericoServicio implements MaAgrupadoresMedicamentoRemoto {
    @Override
    public List<MaAgrupadoresMedicamentos> consultarTodos() throws Exception {
        List<MaAgrupadoresMedicamentos> listResult = new ArrayList();
        try{
            String strQuery = "FROM MaAgrupadoresMedicamento m "
                    + "ORDER BY m.id ";
            List<MaAgrupadoresMedicamento> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaAgrupadoresMedicamento per: list) {
                 listResult.add(castEntidadNegocio(per));
            } 
        }catch (NoResultException e){
            listResult = new ArrayList();
        }catch (Exception e){
            Exception(CONSULTAR_TODOS, e);
        }finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
    /**
     *
     * @param ent
     * @return
     */
    public static MaAgrupadoresMedicamentos castEntidadNegocio (MaAgrupadoresMedicamento ent){
        MaAgrupadoresMedicamentos obj = new MaAgrupadoresMedicamentos();
        obj.setId(ent.getId());
        obj.setCodigo(ent.getCodigo());
        obj.setNombre(ent.getNombre());
        //auditoria
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        return obj;
    }
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception{
        int cant = 0;
        try{
            String strQuery = "SELECT COUNT(p) FROM MaAgrupadoresMedicamento p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigo":
                            strQuery += " AND p.codigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += " AND p.nombre  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "usuarioCrea":
                            strQuery += " AND p.usuarioCrea  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += " AND p.fechaHoraCrea  LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getSingleResult();
        }catch (NoResultException e){
            cant = 0;
        }catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }
    
    
    @Override
    public List<MaAgrupadoresMedicamentos> consultarLista(ParamConsulta paramConsulta) throws Exception{
        List<MaAgrupadoresMedicamentos> listResult = new ArrayList();
        try{
             String strQuery = "FROM MaAgrupadoresMedicamento p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigo":
                            strQuery += " AND p.codigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += " AND p.nombre  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "usuarioCrea":
                            strQuery += " AND p.usuarioCrea  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += " AND p.fechaHoraCrea  LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
                strQuery += " ORDER BY ";
                if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
                 List<MaAgrupadoresMedicamento> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
                 for (MaAgrupadoresMedicamento per : list) {
                listResult.add(castEntidadNegocio(per));
            }
            }
        }catch (NoResultException e){
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
        
    }
    
    @Override
    public MaAgrupadoresMedicamentos consultar(int id) throws Exception {
        MaAgrupadoresMedicamentos objRes = null;
        try {
            objRes = castEntidadNegocio((MaAgrupadoresMedicamento) getEntityManager().find(MaAgrupadoresMedicamento.class, id));
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
    public int insertar(MaAgrupadoresMedicamentos obj) throws Exception {
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
    public void actualizar(MaAgrupadoresMedicamentos obj) throws Exception {
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
    public MaAgrupadoresMedicamentos eliminar(int id) throws Exception {
        MaAgrupadoresMedicamentos obj = null;
        try {
            MaAgrupadoresMedicamento ent = getEntityManager().find(MaAgrupadoresMedicamento.class, id);
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
    
    public static  MaAgrupadoresMedicamento castNegocioEntidad( MaAgrupadoresMedicamentos obj){
        MaAgrupadoresMedicamento per = new MaAgrupadoresMedicamento();
        per.setId(obj.getId());
        per.setCodigo(obj.getCodigo());
        per.setNombre(obj.getNombre());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
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
    public int consultarCodigo(MaAgrupadoresMedicamentos obj) throws Exception{
        int cant = 0;
        try{
            String strQuery = "SELECT COUNT(p) FROM MaAgrupadoresMedicamento p WHERE codigo = '" + obj.getCodigo() + "'";
            if (obj.getId() != null) {
                 strQuery += " AND id NOT IN ("+ obj.getId() +")";
            }
            
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getSingleResult();
        }catch (NoResultException e){
            cant = 0;
        }catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

}
