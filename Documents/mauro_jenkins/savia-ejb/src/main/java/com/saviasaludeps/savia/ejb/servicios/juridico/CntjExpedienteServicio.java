package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjEstado;
import com.saviasaludeps.savia.dominio.juridico.CntjExpediente;
import com.saviasaludeps.savia.dominio.juridico.CntjProceso;
import com.saviasaludeps.savia.ejb.entidades.CntjEstados;
import com.saviasaludeps.savia.ejb.entidades.CntjExpedientes;
import com.saviasaludeps.savia.ejb.entidades.CntjProcesos;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CntjExpedienteRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author idbohorquez
 */
@Stateless
@Remote(CntjExpedienteRemoto.class)
public class CntjExpedienteServicio  extends GenericoServicio implements CntjExpedienteRemoto{

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjExpedientes c WHERE c.id > 0 ");
            if(paramConsulta.getParametroConsulta1() != null){
                strQuery.append(" AND ( c.gnUsuariosPropietarioId.id = ").append(paramConsulta.getParametroConsulta1());
                strQuery.append(" OR gnUsuariosResponsableId.id = ").append(paramConsulta.getParametroConsulta1()).append(") ");
            }
            if(paramConsulta.getParametroConsulta2() == null){
                strQuery.append(" AND c.cntjExpedientesId IS NULL ");
            }
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "numeroExpediente" :
                            strQuery.append(" AND c.numeroExpediente = '").append(e.getValue()).append("' ");
                            break;
                        case "procesoId.id" :
                            strQuery.append(" AND c.cntjProcesosId.id = ").append(e.getValue());
                            break;
                        case "estadoActual.nombre" :
                            strQuery.append(" AND c.cntjEstadosActualId.nombre = '").append(e.getValue()).append("' ");
                            break;
                        case "fechaEjecucionEstado" :
                            strQuery.append(" AND c.fechaEjecucionEstado = ").append(e.getValue());
                            break;
                        case "fechaHoraCrea" :
                            strQuery.append(" AND c.fechaHoraCrea = ").append(e.getValue());
                            break;
                        case "contrato" :
                            strQuery.append(" AND c.contrato = '").append(e.getValue()).append("' ");
                            break;
                        case "expediente_id" :
                            strQuery.append(" AND c.cntjExpedientesId.id = ").append(e.getValue());
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery.toString())
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
    public List<CntjExpediente> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntjExpediente> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjExpedientes c WHERE c.id > 0 ");
            if(paramConsulta.getParametroConsulta1() != null){
                strQuery.append(" AND ( c.gnUsuariosPropietarioId.id = ").append(paramConsulta.getParametroConsulta1());
                strQuery.append(" OR gnUsuariosResponsableId.id = ").append(paramConsulta.getParametroConsulta1()).append(") ");
            }
            if(paramConsulta.getParametroConsulta2() == null){
                strQuery.append(" AND c.cntjExpedientesId IS NULL ");
            }
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "numeroExpediente" :
                            strQuery.append(" AND c.numeroExpediente = '").append(e.getValue()).append("' ");
                            break;
                        case "procesoId.id" :
                            strQuery.append(" AND c.cntjProcesosId.id = ").append(e.getValue());
                            break;
                        case "estadoActual.nombre" :
                            strQuery.append(" AND c.cntjEstadosActualId.nombre = '").append(e.getValue()).append("' ");
                            break;
                        case "fechaEjecucionEstado" :
                            strQuery.append(" AND c.fechaEjecucionEstado = ").append(e.getValue());
                            break;
                        case "fechaHoraCrea" :
                            strQuery.append(" AND c.fechaHoraCrea = ").append(e.getValue());
                            break;
                        case "contrato" :
                            strQuery.append(" AND c.contrato = '").append(e.getValue()).append("' ");
                            break;
                        case "expediente_id" :
                            strQuery.append(" AND c.cntjExpedientesId.id = ").append(e.getValue());
                            break;
                    }
                }
            }
            strQuery.append(" ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                strQuery.append(" c." + paramConsulta.getOrden() + " " + (paramConsulta.isAscendente() ? "ASC" : "DESC"));
            } else {
                strQuery.append(" c.id DESC ");
            }
            List<CntjExpedientes> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjExpedientes campo : list) {
                listResult.add(castEntidadNegocio(campo));
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
    public int consultarCantidadTareas(ParamConsulta paramConsulta) throws java.lang.Exception {
        Integer cant = 0;
        try {
            StringBuilder query = new StringBuilder();
            StringBuilder strwhere = new StringBuilder();
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "numeroExpediente" :
                            strwhere.append(" AND  cex.numero_expediente like '%").append(e.getValue()).append("%' ");
                            break;
                        case "estadoActual.nombre" :
                            strwhere.append(" AND  eex.nombre like '%").append(e.getValue()).append("%' ");
                            break;
                    }
                }
            }
            
            query.append("select count(tbl.id) as cantidad FROM ( ");
            query.append("select cex.id  from cntj_estados ce ");
            query.append("inner join ( ");
            query.append("select ceg.cntj_estados_id from cntj_estado_grupos ceg ");
            query.append("inner join cntj_usuario_grupos cug on ceg.cntj_grupos_id = cug.cntj_grupos_id ");
            query.append("and cug.gn_usuarios_id = :idusuario and ceg.ejecucion = 1 ");
            query.append(") es on ce.id = es.cntj_estados_id ");
            query.append("inner join cntj_transiciones ct on ce.cntj_transiciones_id = ct.id and ce.activo = 1 ");
            query.append("inner join cntj_expedientes cex on cex.cntj_estados_actual_id = ct.cntj_estados_id ");
            query.append(" inner join cntj_estados eex on cex.cntj_estados_actual_id = eex.id ");
            query.append("WHERE ( ");
            query.append("ce.valida_grupo  = 0 ");
            query.append("OR ( ");
            query.append("ce.valida_grupo = 1 ");
            query.append("AND EXISTS ( ");
            query.append("SELECT 1 ");
            query.append("FROM cntj_usuario_grupos cug2 ");
            query.append("WHERE cug2.cntj_grupos_id in (select cug3.cntj_grupos_id  from cntj_usuario_grupos cug3 where cug3.gn_usuarios_id = :idusuario) ");
            query.append("AND cug2.gn_usuarios_id = cex.gn_usuarios_propietario_id ");
            query.append(") ");
            query.append(") ");
            query.append(")  ").append(strwhere.toString());
            query.append(" GROUP by cex.id ");
            query.append(") tbl ");
            
            Object result = getEntityManager().createNativeQuery(query.toString())
                    .setParameter("idusuario", paramConsulta.getParametroConsulta1())
                    .getSingleResult();
            cant = ((Number) result).intValue();
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
    public List<CntjExpediente> consultarListaTareas(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntjExpediente> listResult = new ArrayList<>();
        try {
            StringBuilder query = new StringBuilder();
            StringBuilder strwhere = new StringBuilder();
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "numeroExpediente" :
                            strwhere.append(" AND  cex.numero_expediente like '%").append(e.getValue()).append("%' ");
                            break;
                        case "estadoActual.nombre" :
                            strwhere.append(" AND eex.nombre like '%").append(e.getValue()).append("%' ");
                            break;
                    }
                }
            }
            
            query.append("select cex.* from cntj_estados ce ");
            query.append("inner join ( ");
            query.append("select ceg.cntj_estados_id from cntj_estado_grupos ceg ");
            query.append("inner join cntj_usuario_grupos cug on ceg.cntj_grupos_id = cug.cntj_grupos_id ");
            query.append("and cug.gn_usuarios_id = :idusuario and ceg.ejecucion = 1 ");
            query.append(") es on ce.id = es.cntj_estados_id ");
            query.append("inner join cntj_transiciones ct on ce.cntj_transiciones_id = ct.id and ce.activo = 1 ");
            query.append("inner join cntj_expedientes cex on cex.cntj_estados_actual_id = ct.cntj_estados_id ");
            query.append(" inner join cntj_estados eex on cex.cntj_estados_actual_id = eex.id ");
            query.append("WHERE ( ");
            query.append("ce.valida_grupo  = 0 ");
            query.append("OR ( ");
            query.append("ce.valida_grupo = 1 ");
            query.append("AND EXISTS ( ");
            query.append("SELECT 1 ");
            query.append("FROM cntj_usuario_grupos cug2 ");
            query.append("WHERE cug2.cntj_grupos_id in (select cug3.cntj_grupos_id  from cntj_usuario_grupos cug3 where cug3.gn_usuarios_id = :idusuario) ");
            query.append("AND cug2.gn_usuarios_id = cex.gn_usuarios_propietario_id ");
            query.append(") ");
            query.append(") ");
            query.append(")  ").append(strwhere.toString());
            query.append(" GROUP by cex.id ");
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        
                    }
                }
            }
            query.append(" ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                query.append(" cex." + paramConsulta.getOrden() + " " + (paramConsulta.isAscendente() ? "ASC" : "DESC"));
            } else {
                query.append(" cex.id DESC ");
            }
            List<CntjExpedientes> list = getEntityManager().createNativeQuery(query.toString(), CntjExpedientes.class)
                    .setParameter("idusuario", paramConsulta.getParametroConsulta1())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjExpedientes campo : list) {
                listResult.add(castEntidadNegocio(campo));
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
    public int insertar(CntjExpediente objeto) throws java.lang.Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(objeto)).getId();
            objeto.setId(id);
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
    public CntjExpediente consultar(int id) throws java.lang.Exception {
        CntjExpediente objRes = null;
        try {
            objRes = castEntidadNegocio((CntjExpedientes) getEntityManager().find(CntjExpedientes.class, id));
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
    public void actualizar(CntjExpediente objeto) throws java.lang.Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CntjExpedientes SET  ");
            sql.append("cntjEstadosActualId.id = :estadoActual,  ");
            sql.append("fechaEjecucionEstado = :fechaEjecucionEstado,  ");      
            sql.append(" jsonData = :jsondata, ");
            sql.append(" contrato = :contrato, ");
            sql.append(" gnUsuariosPropietarioId.id = :propietario, ");
            sql.append(" gnUsuariosResponsableId.id = :responsable, ");            
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estadoActual", objeto.getEstadoActual().getId());
            query.setParameter("fechaEjecucionEstado", objeto.getFechaEjecucionEstado());
            query.setParameter("jsondata", objeto.getJsonData());
            query.setParameter("contrato", objeto.getContrato());
            query.setParameter("propietario", objeto.getUsuarioPropietario().getId());
            query.setParameter("responsable", objeto.getUsuarioResponsable() != null ? objeto.getUsuarioResponsable().getId() : null);            
            query.setParameter("usuarioModifica", objeto.getUsuarioModifica());
            query.setParameter("terminalModifica", objeto.getTerminalModifica());
            query.setParameter("fechaHoraModifica", objeto.getFechaHoraModifica());            
            query.setParameter("id", objeto.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public CntjExpediente eliminar(int id) throws java.lang.Exception {
        CntjExpediente obj = null;
        try {
            CntjExpedientes ent = getEntityManager().find(CntjExpedientes.class, id);
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
    public List<CntjExpediente> listaExpedienteEstadoComite() throws java.lang.Exception {
        List<CntjExpediente> listResult = new ArrayList<>();
        try {
            StringBuilder query = new StringBuilder();
            query.append(" select ex.id, ex.numero_expediente from cntj_expedientes ex ");
            query.append(" inner join cntj_estado_ejecuciones cee on ex.id = cee.cntj_expedientes_id ");
            query.append(" inner join cntj_estados ce on cee.cntj_estados_id = ce.id and ce.tipo = 3 ");
            query.append(" GROUP BY ex.id, ex.numero_expediente ");
            Query nativeQuery = em.createNativeQuery(query.toString());
            List<Object[]> results = nativeQuery.getResultList();
            listResult = results
                    .stream()
                    .map(result -> new CntjExpediente(((Integer) result[0]),
                    (String) result[1]
            )).collect(Collectors.toList());

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
    public Integer ultimoNumeroExpediente(Integer anio) throws java.lang.Exception {
        Integer cant = 0;
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT IFNULL(MAX(CAST(SUBSTRING_INDEX(numero_expediente, '-', -1) AS UNSIGNED)),0) AS numero_expediente ");
            query.append("FROM cntj_expedientes WHERE numero_expediente LIKE '").append(anio).append("-%' ");

            Object result = getEntityManager().createNativeQuery(query.toString())
                    .getSingleResult();
            cant = ((Number) result).intValue();
        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }
    
    private CntjExpediente castEntidadNegocio(CntjExpedientes entidad) {
        CntjExpediente objeto = new CntjExpediente();
        objeto.setId(entidad.getId());
        CntjProceso proceso = new CntjProceso(entidad.getCntjProcesosId().getId());
        proceso.setNombre(entidad.getCntjProcesosId().getNombre());
        objeto.setProcesoId(proceso);         
        if(entidad.getCntjEstadosActualId() != null){
            CntjEstado estado = new CntjEstado(entidad.getCntjEstadosActualId().getId());
            estado.setNombre(entidad.getCntjEstadosActualId().getNombre());
            estado.setModificaDatos(entidad.getCntjEstadosActualId().getModificaDatos());
            estado.setModificaFecha(entidad.getCntjEstadosActualId().getModificaFecha());
            estado.setTipo(entidad.getCntjEstadosActualId().getTipo());
            objeto.setEstadoActual(estado);
        }
        objeto.setJsonData(entidad.getJsonData());
        objeto.setNumeroExpediente(entidad.getNumeroExpediente() == null ? "" : entidad.getNumeroExpediente());
        objeto.setFechaEjecucionEstado(entidad.getFechaEjecucionEstado());
        objeto.setContrato(entidad.getContrato());
        if(entidad.getGnUsuariosPropietarioId() != null){
            objeto.setUsuarioPropietario(new Usuario(entidad.getGnUsuariosPropietarioId().getId()));
        }
        if(entidad.getGnUsuariosResponsableId() != null){
            objeto.setUsuarioResponsable(new Usuario(entidad.getGnUsuariosResponsableId().getId()));
        }        
        if(entidad.getCntjExpedientesId() != null){
            objeto.setExpedienteId(new CntjExpediente(entidad.getCntjExpedientesId().getId()));
        }        
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        objeto.setUsuarioModifica(entidad.getUsuarioModifica());
        objeto.setFechaHoraModifica(entidad.getFechaHoraModifica());
        objeto.setTerminalModifica(entidad.getTerminalModifica());
        return objeto;
    }
    
    private CntjExpedientes castNegocioEntidad(CntjExpediente obj){
        CntjExpedientes ent = new CntjExpedientes();
        ent.setCntjProcesosId(new CntjProcesos(obj.getProcesoId().getId()));
        if(obj.getEstadoActual() != null){
            ent.setCntjEstadosActualId(new CntjEstados(obj.getEstadoActual().getId()));
        }        
        ent.setJsonData(obj.getJsonData());
        ent.setNumeroExpediente(obj.getNumeroExpediente());
        ent.setFechaEjecucionEstado(obj.getFechaEjecucionEstado());
        ent.setContrato(obj.getContrato());
        if(obj.getUsuarioPropietario() != null){
            ent.setGnUsuariosPropietarioId(new GnUsuarios(obj.getUsuarioPropietario().getId()));
        }        
        if(obj.getUsuarioResponsable() != null){
            ent.setGnUsuariosResponsableId(new GnUsuarios(obj.getUsuarioResponsable().getId()));
        } 
        if(obj.getExpedienteId() != null){
            ent.setCntjExpedientesId(new CntjExpedientes(obj.getExpedienteId().getId()));
        }
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }
   
    
}
