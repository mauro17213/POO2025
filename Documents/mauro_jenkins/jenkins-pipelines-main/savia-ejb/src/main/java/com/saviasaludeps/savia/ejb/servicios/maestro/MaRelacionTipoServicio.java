/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.maestro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaRelacionTipo;
import com.saviasaludeps.savia.ejb.entidades.MaRelacionTipos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.maestro.MaRelacionTipoRemoto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Session;

/**
 *
 * @author rpalacios
 */
@Stateless
@Remote(MaRelacionTipoRemoto.class)
public class MaRelacionTipoServicio extends GenericoServicio implements MaRelacionTipoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaRelacionTipos p WHERE p.id > 0 ";
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
    public List<MaRelacionTipo> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<MaRelacionTipo> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaRelacionTipos p WHERE p.id > 0 ";
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
            List<MaRelacionTipos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (MaRelacionTipos per : list) {
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
    public MaRelacionTipo consultar(int id) throws Exception {
        MaRelacionTipo objRes = null;
        try {
            objRes = castEntidadNegocio((MaRelacionTipos) getEntityManager().find(MaRelacionTipos.class, id));
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
    public int insertar(MaRelacionTipo obj) throws Exception {
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
    
    public static MaRelacionTipo castEntidadNegocio(MaRelacionTipos ent) {        
        MaRelacionTipo obj = new MaRelacionTipo();
        obj.setId(ent.getId());
        obj.setNombre(ent.getNombre());
        obj.setDescripcion(ent.getDescripcion());
        //Auditoria
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        return obj;
    }
    
    public static MaRelacionTipos castNegocioEntidad(MaRelacionTipo obj) {
        MaRelacionTipos ent = new MaRelacionTipos();
        ent.setId(obj.getId());
        ent.setNombre(obj.getNombre());
        ent.setDescripcion(obj.getDescripcion());
        //auditoria
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        return ent;
    }
}
