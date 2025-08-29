/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.maestro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaRelacion;
import com.saviasaludeps.savia.dominio.maestro.MaRelacionTipo;
import com.saviasaludeps.savia.ejb.entidades.MaRelacionTipos;
import com.saviasaludeps.savia.ejb.entidades.MaRelaciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.maestro.MaRelacionRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author rpalacios
 */
@Stateless
@Remote(MaRelacionRemoto.class)
public class MaRelacionServicio extends GenericoServicio implements MaRelacionRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaRelaciones p WHERE p.id > 0 ";
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
    public List<MaRelacion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<MaRelacion> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaRelaciones p WHERE p.id > 0 ";
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
            List<MaRelaciones> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (MaRelaciones per : list) {
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
    public MaRelacion consultar(int id) throws Exception {
        MaRelacion objRes = null;
        try {
            objRes = castEntidadNegocio((MaRelaciones) getEntityManager().find(MaRelaciones.class, id));
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
    public int insertar(MaRelacion obj) throws Exception {
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
    public List<MaRelacion> consultarPorMaServicioHabilitacionId(int maId) throws Exception {
        List<MaRelacion>  objResult = new ArrayList<>();
        try {
            String strQuery = "FROM MaRelaciones s "
                    + "WHERE s.maId = " + maId + " " 
                    + "AND s.activo = 1";
            List<MaRelaciones> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for(MaRelaciones relacion: list){
                objResult.add(castEntidadNegocio(relacion));
            }
          
        } catch (NoResultException e) {
            objResult = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objResult;
    }
    
    public static MaRelacion castEntidadNegocio(MaRelaciones ent) {        
        MaRelacion obj = new MaRelacion();
        obj.setId(ent.getId());
        if(ent.getMaRelacionTiposId() != null){
            MaRelacionTipo maRelacionTipo = new MaRelacionTipo();
            maRelacionTipo.setId(ent.getMaRelacionTiposId().getId());
            obj.setMaRelacionTiposId(maRelacionTipo);

        }
        obj.setGnMaestroTipo(ent.getGnMaestroTipo());
        obj.setGnId(ent.getGnId());
        obj.setGnCodigo(ent.getGnCodigo());
        obj.setGnValor(ent.getGnValor());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setMaId(ent.getMaId());
        obj.setMaCodigo(ent.getMaCodigo());
        obj.setMaValor(ent.getMaValor());
        obj.setActivo(ent.getActivo());
        //Auditoria  
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        return obj;
    }
    
    public static MaRelaciones castNegocioEntidad(MaRelacion obj) {
        MaRelaciones ent = new MaRelaciones();
        ent.setId(ent.getId());
        if(obj.getMaRelacionTiposId() != null){
            MaRelacionTipos maRelacionTipo = new MaRelacionTipos();
            maRelacionTipo.setId(obj.getMaRelacionTiposId().getId());
            ent.setMaRelacionTiposId(maRelacionTipo);

        }
        ent.setGnMaestroTipo(ent.getGnMaestroTipo());
        ent.setGnId(ent.getGnId());
        ent.setGnCodigo(ent.getGnCodigo());
        ent.setGnValor(ent.getGnValor());
        ent.setTipoTecnologia(ent.getTipoTecnologia());
        ent.setMaId(ent.getMaId());
        ent.setMaCodigo(ent.getMaCodigo());
        ent.setMaValor(ent.getMaValor());
        ent.setActivo(ent.getActivo());
        //auditoria
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        return ent;
    }

    
}
