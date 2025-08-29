
package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjEstado;
import com.saviasaludeps.savia.dominio.juridico.CntjProceso;
import com.saviasaludeps.savia.dominio.juridico.CntjTransicion;
import com.saviasaludeps.savia.ejb.entidades.CntjEstados;
import com.saviasaludeps.savia.ejb.entidades.CntjProcesos;
import com.saviasaludeps.savia.ejb.entidades.CntjTransiciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CtnjEstadoRemoto;
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
@Remote(CtnjEstadoRemoto.class)
public class CntjEstadoServicio extends GenericoServicio implements CtnjEstadoRemoto{

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjEstados c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id_proceso":
                            strQuery.append(" AND c.cntjProcesosId.id = " +  e.getValue() + " ");
                            break;
                        case "nombre":
                            strQuery.append(" AND c.nombre like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "cntjTransicionId.cntjEstadoId.nombre":
                            strQuery.append(" AND c.cntjTransicionesId.cntjEstadosId.nombre like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "cntjTransicionId.nombre":
                            strQuery.append(" AND c.cntjTransicionesId.nombre like '%" + (String) e.getValue() + "%' ");
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
    public List<CntjEstado> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntjEstado> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT p FROM CntjEstados p WHERE p.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id_proceso":
                            strQuery.append(" AND p.cntjProcesosId.id = " +  e.getValue() + " ");
                            break;
                        case "nombre":
                            strQuery.append(" AND p.nombre like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "cntjTransicionId.cntjEstadoId.nombre":
                            strQuery.append(" AND p.cntjTransicionesId.cntjEstadosId.nombre like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "cntjTransicionId.nombre":
                            strQuery.append(" AND p.cntjTransicionesId.nombre like '%" + (String) e.getValue() + "%' ");
                            break;
                    }
                }
            }
            strQuery.append(" ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                strQuery.append(" p." + paramConsulta.getOrden() + " " + (paramConsulta.isAscendente() ? "ASC" : "DESC"));
            } else {
                strQuery.append(" p.id DESC ");
            }
            List<CntjEstados> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjEstados plantilla : list) {
                listResult.add(castEntidadNegocio(plantilla));
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
    public int insertar(CntjEstado objeto) throws java.lang.Exception {
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
    public CntjEstado consultar(int idestado) throws java.lang.Exception {
        CntjEstado objRes = null;
        try {
            objRes = castEntidadNegocio((CntjEstados) getEntityManager().find(CntjEstados.class, idestado));
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
    public void actualizar(CntjEstado objeto) throws java.lang.Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CntjEstados SET tipo = :tipo, ");
            sql.append("nombre = :nombre,  ");
            sql.append("descripcion = :descripcion,  ");
            sql.append("activo = :activo,  ");
            if(objeto.getCntjTransicionId() != null){
                sql.append("cntjTransicionesId.id = :cntjTransicionesId,  ");
            }            
            sql.append("modificaFecha = :modificaFecha, ");
            sql.append("modificaDatos = :modificaDatos, ");
            sql.append("validaGrupo = :validaGrupo, ");   
            sql.append("resultadoComite = :resultadoComite, ");               
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("tipo", objeto.getTipo());
            query.setParameter("nombre", objeto.getNombre());
            query.setParameter("descripcion", objeto.getDescripcion());
            query.setParameter("activo", objeto.getActivo());
            if(objeto.getCntjTransicionId() != null){
                query.setParameter("cntjTransicionesId", objeto.getCntjTransicionId().getId());
            }            
            query.setParameter("modificaFecha", objeto.isModificaFecha());
            query.setParameter("modificaDatos", objeto.isModificaDatos());
            query.setParameter("validaGrupo", objeto.isValidaGrupo());            
            query.setParameter("resultadoComite", objeto.getResultadoComite());            
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
    public List<CntjEstado> listaEstadosProceso(int idproceso) throws java.lang.Exception {
        List<CntjEstado> listResult = new ArrayList<>();
        try {
            Query nativeQuery = em.createNativeQuery("SELECT p.id, p.nombre FROM cntj_estados p WHERE p.id > 0 and cntj_procesos_id = "+idproceso+" and p.activo = 1 ");
            List<Object[]> results = nativeQuery.getResultList();
            listResult = results
                    .stream()
                    .map(result -> new CntjEstado(((Integer) result[0]),
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
    public List<CntjEstado> consultarEstadoSiguientes(int idexpediente, int idusuario) throws java.lang.Exception {
        List<CntjEstado> listResult = new ArrayList<>();
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT ce2.id, ce2.cntj_procesos_id, ce2.cntj_transiciones_id , ");
            query.append("( select ct.nombre from cntj_transiciones ct ");
            query.append("where ct.id = ce2.cntj_transiciones_id ) as nombre_transicion, ");
            query.append("ce2.tipo , ");
            query.append("ce2.nombre , ");
            query.append("ce2.descripcion , ");
            query.append("ce2.activo, ");
            query.append("ce2.modifica_fecha, ");
            query.append("ce2.modifica_datos ");
            query.append("from cntj_estados ce2 ");
            query.append("inner join ( ");
            query.append("select ");
            query.append("cet.cntj_transiciones_id ");
            query.append("from ");
            query.append("cntj_estados cet ");
            query.append("where ");
            query.append("cet.cntj_transiciones_id in ( ");
            query.append("select ");
            query.append("ct.id ");
            query.append("from ");
            query.append("cntj_transiciones ct ");
            query.append("where ");
            query.append("ct.cntj_estados_id = ( ");
            query.append("select ");
            query.append("cee.cntj_estados_id as idestado ");
            query.append("from ");
            query.append("cntj_estado_ejecuciones cee ");
            query.append("where ");
            query.append("cee.cntj_expedientes_id = :idexpediente order by cee.id desc limit 1 ) and ct.activo = 1 ) ");
            query.append(") ");
            query.append("tbl on ");
            query.append("tbl.cntj_transiciones_id = ce2.cntj_transiciones_id and ce2.activo = 1 ");
            query.append("and ce2.id in ( select ceg.cntj_estados_id from cntj_estado_grupos ceg ");
            query.append("inner join cntj_usuario_grupos cug on ceg.cntj_grupos_id  = cug.cntj_grupos_id and cug.gn_usuarios_id = :idusuario and ceg.ejecucion = 1 ");
            query.append(" ) ");

            query.append("order by ");
            query.append("ce2.id asc ");

            List<Object[]> list = getEntityManager().createNativeQuery(query.toString())
                    .setParameter("idexpediente", idexpediente)
                    .setParameter("idusuario", idusuario)
                    .getResultList();
            if(!list.isEmpty()){
                listResult = list.stream().map(resultado -> new CntjEstado(
                        (int) resultado[0], 
                        (Integer) resultado[4], 
                        (String) resultado[5], 
                        (String) resultado[6], 
                        (boolean) resultado[7],
                        new CntjProceso((int) resultado[1]), 
                        new CntjTransicion((int) resultado[2], (String) resultado[3]),
                        (boolean) resultado[8],
                        (boolean) resultado[9]
                )).collect(Collectors.toList());
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
    public CntjEstado consultarEstadoInicio(int idproceso, int idusuario) throws java.lang.Exception {
        CntjEstado objRes = null;
        try {            
            StringBuilder query = new StringBuilder();
            query.append("select ce.id , ce.nombre  from cntj_estados ce ");
            query.append("inner join cntj_estado_grupos ceg on ce.id = ceg.cntj_estados_id and ce.cntj_procesos_id = :idproceso ");
            query.append("inner join cntj_usuario_grupos cug on ceg.cntj_grupos_id = cug.cntj_grupos_id and cug.gn_usuarios_id = :idusuario ");
            query.append("where ce.tipo = 0 and ce.activo = 1 ");

            Object[] estado =  (Object[]) getEntityManager().createNativeQuery(query.toString())
                    .setParameter("idproceso", idproceso)
                    .setParameter("idusuario", idusuario)
                    .getSingleResult(); 
            if(estado != null){
                objRes = new CntjEstado((int) estado[0],(String) estado[1]);
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
    public CntjEstado consultarEstadoResultadoLinea(int idproceso, int resultado, int estadoActual) throws Exception {
        CntjEstado respuesta = null;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjEstados c  "); 
            strQuery.append(" INNER JOIN CntjTransiciones ct ON c.cntjTransicionesId.id = ct.id AND ct.cntjEstadosId.id = :estadoActual ");
            strQuery.append(" WHERE c.id > 0 and c.cntjProcesosId.id = :procesoId and c.tipo = :resultado and c.activo = 1  ");            
            CntjEstados estado = getEntityManager().createQuery(strQuery.toString(), CntjEstados.class)
                    .setParameter("estadoActual", estadoActual)
                    .setParameter("procesoId", idproceso)
                    .setParameter("resultado", resultado)
                    .setMaxResults(1)
                    .getSingleResult();            
            respuesta = castEntidadNegocio(estado);
        } catch (NoResultException e) {
            respuesta = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return respuesta;
    }
    
    private CntjEstado castEntidadNegocio(CntjEstados entidad) {
        CntjEstado objeto = new CntjEstado();
        objeto.setId(entidad.getId());
        objeto.setTipo(entidad.getTipo());
        objeto.setNombre(entidad.getNombre());
        objeto.setDescripcion(entidad.getDescripcion());
        objeto.setActivo(entidad.getActivo());
        if(entidad.getCntjProcesosId() != null){
            objeto.setCntjProcesoId(new CntjProceso(entidad.getCntjProcesosId().getId()));
        }
        if(entidad.getCntjTransicionesId() != null){
            CntjTransicion transicion = new CntjTransicion(entidad.getCntjTransicionesId().getId());
            transicion.setNombre(entidad.getCntjTransicionesId().getNombre());
            CntjEstado estadoPadre = new CntjEstado();
            if(entidad.getCntjTransicionesId().getCntjEstadosId() != null){
                estadoPadre.setId(entidad.getCntjTransicionesId().getCntjEstadosId().getId());
                estadoPadre.setNombre(entidad.getCntjTransicionesId().getCntjEstadosId().getNombre());
            }
            transicion.setCntjEstadoId(estadoPadre);
            objeto.setCntjTransicionId(transicion);
        }       
        objeto.setModificaFecha(entidad.getModificaFecha());
        objeto.setModificaDatos(entidad.getModificaDatos());
        objeto.setValidaGrupo(entidad.getValidaGrupo());
        
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        objeto.setUsuarioModifica(entidad.getUsuarioModifica());
        objeto.setFechaHoraModifica(entidad.getFechaHoraModifica());
        objeto.setTerminalModifica(entidad.getTerminalModifica());
        return objeto;
    }

    private CntjEstados castNegocioEntidad(CntjEstado obj) {
        CntjEstados ent = new CntjEstados();
        ent.setTipo(obj.getTipo());
        ent.setNombre(obj.getNombre());
        ent.setDescripcion(obj.getDescripcion());
        ent.setActivo(obj.getActivo());
        ent.setCntjProcesosId(new CntjProcesos(obj.getCntjProcesoId().getId()));
        if(obj.getCntjTransicionId() != null){
            ent.setCntjTransicionesId(new CntjTransiciones(obj.getCntjTransicionId().getId()));
        }        
        ent.setModificaFecha(obj.isModificaFecha());
        ent.setModificaDatos(obj.isModificaDatos());
        ent.setValidaGrupo(obj.isValidaGrupo());
        
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }
    
}
