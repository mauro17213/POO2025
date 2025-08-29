/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuTopeAfiliado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AuTopeAfiliados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import com.saviasaludeps.savia.negocio.autorizacion.AuTopeAfiliadoRemoto;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(AuTopeAfiliadoRemoto.class)
public class AuTopeAfiliadoServicio extends GenericoServicio implements AuTopeAfiliadoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(u) FROM AuTopeAfiliados u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();      
                    
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND u.id = ").append(e.getValue()).append(" ");
                            break;
                        case "activo":
                            strQuery.append("AND u.activo = ").append(e.getValue()).append(" ");
                            break;
                        case "aseAfiliadosId.maeTipoDocumento":
                            strTitulo = agregarJoin("INNER JOIN AsegAfiliados aseg ON u.aseAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append("AND aseg.maeTipoDocumentoId = ").append(e.getValue()).append(" ");
                            break;
                        case "aseAfiliadosId.numeroDocumento":
                            strTitulo = agregarJoin("INNER JOIN AsegAfiliados aseg ON u.aseAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append("AND aseg.numeroDocumento = '").append(e.getValue()).append("' ");
                            break;
                        case "aseAfiliadosId.primerNombre":
                            strTitulo = agregarJoin("INNER JOIN AsegAfiliados aseg ON u.aseAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append("AND aseg.primerNombre LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "aseAfiliadosId.segundoNombre":
                            strTitulo = agregarJoin("INNER JOIN AsegAfiliados aseg ON u.aseAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append("AND aseg.segundoNombre LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "aseAfiliadosId.primerApellido":
                            strTitulo = agregarJoin("INNER JOIN AsegAfiliados aseg ON u.aseAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append("AND aseg.primerApellido LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "aseAfiliadosId.segundoApellido":
                            strTitulo = agregarJoin("INNER JOIN AsegAfiliados aseg ON u.aseAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append("AND aseg.segundoApellido LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "usuarioCrea":
                            strQuery.append("AND u.usuarioCrea LIKE '%").append(e.getValue()).append("%' ");
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
    public List<AuTopeAfiliado> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuTopeAfiliado> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuTopeAfiliados u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                         case "id":
                            strQuery.append("AND u.id = ").append(e.getValue()).append(" ");
                            break;
                        case "activo":
                            strQuery.append("AND u.activo = ").append(e.getValue()).append(" ");
                            break;
                        case "aseAfiliadosId.maeTipoDocumento":
                            strTitulo = agregarJoin("INNER JOIN AsegAfiliados aseg ON u.aseAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append("AND aseg.maeTipoDocumentoId = ").append(e.getValue()).append(" ");
                            break;
                        case "aseAfiliadosId.numeroDocumento":
                            strTitulo = agregarJoin("INNER JOIN AsegAfiliados aseg ON u.aseAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append("AND aseg.numeroDocumento = '").append(e.getValue()).append("' ");
                            break;
                        case "aseAfiliadosId.primerNombre":
                            strTitulo = agregarJoin("INNER JOIN AsegAfiliados aseg ON u.aseAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append("AND aseg.primerNombre LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "aseAfiliadosId.segundoNombre":
                            strTitulo = agregarJoin("INNER JOIN AsegAfiliados aseg ON u.aseAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append("AND aseg.segundoNombre LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "aseAfiliadosId.primerApellido":
                            strTitulo = agregarJoin("INNER JOIN AsegAfiliados aseg ON u.aseAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append("AND aseg.primerApellido LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "aseAfiliadosId.segundoApellido":
                            strTitulo = agregarJoin("INNER JOIN AsegAfiliados aseg ON u.aseAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append("AND aseg.segundoApellido LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "usuarioCrea":
                            strQuery.append("AND u.usuarioCrea LIKE '%").append(e.getValue()).append("%' ");
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
            List<AuTopeAfiliados> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuTopeAfiliados per : list) {
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
            return sql +=  join;
        }
    }
    
    @Override
    public AuTopeAfiliado consultar(int id) throws Exception {
        AuTopeAfiliado objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AuTopeAfiliados) getEntityManager().find(AuTopeAfiliados.class, id));
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
    public int insertar(AuTopeAfiliado obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
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
    public void actualizar(AuTopeAfiliado obj) throws Exception {
        try {
            String hql = "UPDATE AuTopeAfiliados SET "
                    + "activo = :activo, "
                    + "justificacionInactivo = :justificacionInactivo, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("activo", obj.isActivo());
            query.setParameter("justificacionInactivo", obj.getJustificacionInactivo());
            //auditoria
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public AuTopeAfiliado consultarAfiliadoActivo(int idAfiliado) throws Exception {
        AuTopeAfiliado auTopeAfiliado = null;

        try {
            String strQuery = "SELECT p "
                    + "FROM AuTopeAfiliados p "
                    + "INNER JOIN AsegAfiliados aseg ON p.aseAfiliadosId = aseg.id "
                    + "WHERE aseg.id = " + idAfiliado + " "
                    + "AND p.activo = 1 "
                    + "ORDER BY p.fechaHoraCrea DESC ";
            List<AuTopeAfiliados> lista = getEntityManager().createQuery(strQuery).setMaxResults(1).getResultList();
            for (AuTopeAfiliados obj : lista) {
                auTopeAfiliado = castEntidadNegocioLargo(obj);
            }
        } catch (NoResultException e) {
            auTopeAfiliado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return auTopeAfiliado;
    }
    
    public static AuTopeAfiliado castEntidadNegocioCorto(AuTopeAfiliados ent) {
        AuTopeAfiliado obj = new AuTopeAfiliado();
        obj.setId(ent.getId());
        obj.setActivo(ent.getActivo());
        if(ent.getAseAfiliadosId()!= null){
            AsegAfiliado afiliado = new AsegAfiliado();
            afiliado.setId(ent.getAseAfiliadosId().getId());
            afiliado.setMaeTipoDocumento(ent.getAseAfiliadosId().getMaeTipoDocumentoId());
            afiliado.setMaeTipoDocumentoCodigo(ent.getAseAfiliadosId().getMaeTipoDocumentoCodigo());
            afiliado.setMaeTipoDocumentoValor(ent.getAseAfiliadosId().getMaeTipoDocumentoValor());
            afiliado.setNumeroDocumento(ent.getAseAfiliadosId().getNumeroDocumento());
            afiliado.setPrimerNombre(ent.getAseAfiliadosId().getPrimerNombre());
            afiliado.setSegundoNombre(ent.getAseAfiliadosId().getSegundoNombre());
            afiliado.setPrimerApellido(ent.getAseAfiliadosId().getPrimerApellido());
            afiliado.setSegundoApellido(ent.getAseAfiliadosId().getSegundoApellido());
            obj.setAseAfiliadosId(afiliado);
        }
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }

    public static AuTopeAfiliado castEntidadNegocioLargo(AuTopeAfiliados ent) {
        AuTopeAfiliado obj = new AuTopeAfiliado();
        obj.setId(ent.getId());
        if(ent.getAseAfiliadosId()!= null){
            AsegAfiliado afiliado = new AsegAfiliado();
            afiliado.setId(ent.getAseAfiliadosId().getId());
            obj.setAseAfiliadosId(afiliado);
        }
        obj.setActivo(ent.getActivo());
        obj.setJustificacionActivo(ent.getJustificacionActivo());
        obj.setJustificacionInactivo(ent.getJustificacionInactivo());
       
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        return obj;
    }

    public static AuTopeAfiliados castNegocioEntidad(AuTopeAfiliado obj) {
        AuTopeAfiliados ent = new AuTopeAfiliados();
        ent.setId(obj.getId());
        if(obj.getAseAfiliadosId()!= null){
            AsegAfiliados afiliado = new AsegAfiliados();
            afiliado.setId(obj.getAseAfiliadosId().getId());
            ent.setAseAfiliadosId(afiliado);
        }
        ent.setActivo(obj.isActivo());
        ent.setJustificacionActivo(obj.getJustificacionActivo());
        ent.setJustificacionInactivo(obj.getJustificacionInactivo());
        //Auditoria
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }
}
