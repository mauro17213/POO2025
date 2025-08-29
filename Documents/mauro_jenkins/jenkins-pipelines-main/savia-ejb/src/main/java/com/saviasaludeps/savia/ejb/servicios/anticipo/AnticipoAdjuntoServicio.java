/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.anticipo;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipo;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoAdjunto;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AntAnticipoAdjuntos;
import com.saviasaludeps.savia.ejb.entidades.AntAnticipos;
import com.saviasaludeps.savia.ejb.entidades.GnMaestros;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.anticipo.AnticipoAdjuntoRemoto;
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
@Remote(AnticipoAdjuntoRemoto.class)
public class AnticipoAdjuntoServicio extends GenericoServicio implements AnticipoAdjuntoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(u) FROM AntAnticipoAdjuntos u ";
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
                        case "origen":
                            strQuery.append("AND u.origen = ").append(e.getValue()).append(" ");
                            break;
                        case "antAnticiposId.id":
                            strTitulo = agregarJoin("INNER JOIN AntAnticipos ant ON u.antAnticiposId = ant.id ", strTitulo);
                            strQuery.append("AND ant.id = ").append(e.getValue()).append(" ");
                            break;
                        case "maeTipoArchivoId.id":
                            strTitulo = agregarJoin("INNER JOIN GnMaestros maes ON u.maeTipoArchivoId = maes.id ", strTitulo);
                            strQuery.append("AND maes.id = ").append(e.getValue()).append(" ");
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
    public List<AntAnticipoAdjunto> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AntAnticipoAdjunto> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AntAnticipoAdjuntos u ";
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
                         case "origen":
                            strQuery.append("AND u.origen = ").append(e.getValue()).append(" ");
                            break;
                        case "antAnticiposId.id":
                            strTitulo = agregarJoin("INNER JOIN AntAnticipos ant ON u.antAnticiposId = ant.id ", strTitulo);
                            strQuery.append("AND ant.id = ").append(e.getValue()).append(" ");
                            break;
                        case "maeTipoArchivoId.id":
                            strTitulo = agregarJoin("INNER JOIN GnMaestros maes ON u.maeTipoArchivoId = maes.id ", strTitulo);
                            strQuery.append("AND maes.id = ").append(e.getValue()).append(" ");
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
            List<AntAnticipoAdjuntos> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AntAnticipoAdjuntos per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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
    public List<AntAnticipoAdjunto> consultarAdjuntoPorAnticipoId(int idAnticipo) throws Exception {
        List<AntAnticipoAdjunto> obj = new ArrayList<>();
        try {
            String strQuery = "SELECT p "
                    + "FROM AntAnticipoAdjuntos p "
                    + "WHERE p.id > 0 "
                    + "AND p.antAnticiposId.id = " + idAnticipo + " "
                    + "ORDER BY p.id DESC";
            List<AntAnticipoAdjuntos> list = getEntityManager().createQuery(strQuery).getResultList();
            for (AntAnticipoAdjuntos adjunto : list) {
                obj.add(castEntidadNegocioLargo(adjunto));
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
    public AntAnticipoAdjunto consultar(int id) throws Exception {
        AntAnticipoAdjunto objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AntAnticipoAdjuntos) getEntityManager().find(AntAnticipoAdjuntos.class, id));
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
    public int insertar(AntAnticipoAdjunto obj) throws Exception {
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

 
    public static AntAnticipoAdjunto castEntidadNegocioLargo(AntAnticipoAdjuntos ent) {
        AntAnticipoAdjunto obj = new AntAnticipoAdjunto();
        obj.setId(ent.getId());
        if(ent.getAntAnticiposId() != null){
            obj.setAntAnticiposId(new AntAnticipo(ent.getAntAnticiposId().getId()));
        }
        obj.setOrigen(ent.getOrigen());
        if(ent.getMaeTipoArchivoId() > 0 ){
            obj.setMaeTipoArchivoId(ent.getMaeTipoArchivoId());
        }
        obj.setMaeTipoArchivoCodigo(ent.getMaeTipoArchivoCodigo());
        obj.setMaeTipoArchivoValor(ent.getMaeTipoArchivoValor());
        obj.setMaeTipoArchivoTipo(ent.getMaeTipoArchivoTipo());
        obj.setNombre(ent.getNombre());
        obj.setRuta(ent.getRuta());
        obj.setArchivo(ent.getArchivo());
        obj.setExiste(ent.getExiste());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        
        return obj;
    }

    public static AntAnticipoAdjuntos castNegocioEntidad(AntAnticipoAdjunto obj) {
        AntAnticipoAdjuntos ent = new AntAnticipoAdjuntos();
        ent.setId(obj.getId());
        if(obj.getAntAnticiposId() != null){
            ent.setAntAnticiposId(new AntAnticipos(obj.getAntAnticiposId().getId()));
        }
        ent.setOrigen(obj.getOrigen());
        if(obj.getMaeTipoArchivoId() > 0){
            ent.setMaeTipoArchivoId(obj.getMaeTipoArchivoId());
        }
        ent.setMaeTipoArchivoCodigo(obj.getMaeTipoArchivoCodigo());
        ent.setMaeTipoArchivoValor(obj.getMaeTipoArchivoValor());
        ent.setMaeTipoArchivoTipo(obj.getMaeTipoArchivoTipo());
        ent.setNombre(obj.getNombre());
        ent.setRuta(obj.getRuta());
        ent.setArchivo(obj.getArchivo());
        ent.setExiste(obj.isExiste());
        //Auditoria
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }
}
