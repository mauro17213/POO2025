/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.anticipo;

import com.saviasaludeps.savia.dominio.anticipo.AntAnticipo;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoGestion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AntAnticipoGestiones;
import com.saviasaludeps.savia.ejb.entidades.AntAnticipos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.anticipo.AnticipoGestionRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(AnticipoGestionRemoto.class)
public class AnticipoGestionServicio extends GenericoServicio implements AnticipoGestionRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(u) FROM AntAnticipoGestiones u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            /*if (paramConsulta.getEmpresaId() != null) {
                strTitulo = agregarJoin("INNER JOIN GnEmpresas gne ON u.gnEmpresasId = gne.id ", strTitulo);
                strQuery.append("AND gne.id = ").append(paramConsulta.getEmpresaId()).append(" ");
            }*/        
                    
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND u.id = ").append(e.getValue()).append(" ");
                            break;
                        case "tipo":
                            strQuery.append("AND u.tipo = ").append(e.getValue()).append(" ");
                            break;
                        case "estado":
                            strQuery.append("AND u.estado = ").append(e.getValue()).append(" ");
                            break;
                        case "antAnticiposId.id":
                            strTitulo = agregarJoin("INNER JOIN AntAnticipos ant ON u.antAnticiposId = ant.id ", strTitulo);
                            strQuery.append("AND ant.id = ").append(e.getValue()).append(" ");
                            break;
                       
                    }
                }
            }
            sql.append(strTitulo).append(strQuery);
            cant = (int) (long) getEntityManager().createQuery(sql.toString())
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
    public List<AntAnticipoGestion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AntAnticipoGestion> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AntAnticipoGestiones u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            /*if (paramConsulta.getEmpresaId() != null) {
                strTitulo = agregarJoin("INNER JOIN GnEmpresas gne ON u.gnEmpresasId = gne.id ", strTitulo);
                strQuery.append("AND gne.id = ").append(paramConsulta.getEmpresaId()).append(" ");
            }*/   
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND u.id = ").append(e.getValue()).append(" ");
                            break;
                        case "tipo":
                            strQuery.append("AND u.tipo = ").append(e.getValue()).append(" ");
                            break;
                        case "estado":
                            strQuery.append("AND u.estado = ").append(e.getValue()).append(" ");
                            break;
                        case "antAnticiposId.id":
                            strTitulo = agregarJoin("INNER JOIN AntAnticipos ant ON u.antAnticiposId = ant.id ", strTitulo);
                            strQuery.append("AND ant.id = ").append(e.getValue()).append(" ");
                            break;
                        
                    }
                }
            }
            sql.append(strTitulo).append(strQuery);
            sql.append("ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                sql.append("u.").append(paramConsulta.getOrden()).append((paramConsulta.isAscendente() ? " ASC" : " DESC"));
            } else {
                sql.append("u.id DESC");
            }
            List<AntAnticipoGestiones> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AntAnticipoGestiones per : list) {
                listResult.add(castEntidadNegocioCorto(per));
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
    
    private String agregarJoin(String join, String sql) {
        if (sql.contains(join)) {
            return sql;
        } else {
            return sql += join;
        }
    }
    
    @Override
    public List<AntAnticipoGestion> consultarGestionPorAnticipoId(int idAnticipo) throws Exception {
        List<AntAnticipoGestion> obj = new ArrayList();
        try {
            String strQuery = "SELECT p "
                    + "FROM AntAnticipoGestiones p "
                    + "WHERE p.id > 0 "
                    + "AND p.antAnticiposId.id = " + idAnticipo + " "
                    + "ORDER BY p.id DESC";
            List<AntAnticipoGestiones> list = getEntityManager().createQuery(strQuery).getResultList();
            for (AntAnticipoGestiones gestion : list) {
                obj.add(castEntidadNegocioLargo(gestion));
            }
        } catch (NoResultException e) {
            obj = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    
    @Override
    public AntAnticipoGestion consultar(int id) throws Exception {
        AntAnticipoGestion objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AntAnticipoGestiones) getEntityManager().find(AntAnticipoGestiones.class, id));
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
    public int insertar(AntAnticipoGestion obj) throws Exception {
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

    public static AntAnticipoGestion castEntidadNegocioCorto(AntAnticipoGestiones ent) {
        AntAnticipoGestion obj = new AntAnticipoGestion();
        obj.setId(ent.getId());
        if(ent.getAntAnticiposId() != null){
            obj.setAntAnticiposId(new AntAnticipo(ent.getAntAnticiposId().getId()));
        }
        obj.setTipo(ent.getTipo());
        obj.setEstado(ent.getEstado());
        obj.setDescripcion(ent.getDescripcion());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }

    public static AntAnticipoGestion castEntidadNegocioLargo(AntAnticipoGestiones ent) {
        AntAnticipoGestion obj = new AntAnticipoGestion();
        obj.setId(ent.getId());
        if(ent.getAntAnticiposId() != null){
            obj.setAntAnticiposId(new AntAnticipo(ent.getAntAnticiposId().getId()));
        }
        obj.setTipo(ent.getTipo());
        obj.setEstado(ent.getEstado());
        obj.setDescripcion(ent.getDescripcion());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        
        return obj;
    }

    public static AntAnticipoGestiones castNegocioEntidad(AntAnticipoGestion obj) {
        AntAnticipoGestiones ent = new AntAnticipoGestiones();
        ent.setId(obj.getId());
        if(obj.getAntAnticiposId() != null){
            ent.setAntAnticiposId(new AntAnticipos(obj.getAntAnticiposId().getId()));
        }
        ent.setTipo(obj.getTipo());
        ent.setEstado(obj.getEstado());
        ent.setDescripcion(obj.getDescripcion());
       
        //Auditoria
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }
}
