
package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.juridico.CntjProceso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjEstado;
import com.saviasaludeps.savia.ejb.entidades.CntjProcesos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CtnjProcesoRemoto;
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
@Remote(CtnjProcesoRemoto.class)
public class CntjProcesoServicio extends GenericoServicio implements CtnjProcesoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjProcesos c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery.append(" AND c.nombre = '" + (String) e.getValue() + "' ");
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
    public List<CntjProceso> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntjProceso> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjProcesos c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery.append(" AND c.nombre = '" + (String) e.getValue() + "' ");
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
            List<CntjProcesos> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjProcesos contratacion : list) {
                listResult.add(castEntidadNegocio(contratacion));
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
    public int insertar(CntjProceso objeto) throws java.lang.Exception {
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
    public CntjProceso consultar(int idproceso) throws java.lang.Exception {
        CntjProceso objRes = null;
        try {
            objRes = castEntidadNegocio((CntjProcesos) getEntityManager().find(CntjProcesos.class, idproceso));
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
    public void actualizar(CntjProceso objeto) throws java.lang.Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CntjProcesos SET nombre = :nombre, ");
            sql.append("descripcion = :descripcion,  ");
            sql.append("activo = :activo,  ");
            sql.append("tipoProceso = :tipoProceso,  ");
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("nombre", objeto.getNombre());
            query.setParameter("descripcion", objeto.getDescripcion());
            query.setParameter("activo", objeto.getActivo());
            query.setParameter("tipoProceso", objeto.getTipoProceso());
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
    public List<CntjProceso> getProcesos() throws java.lang.Exception {
        List<CntjProceso> listResult = new ArrayList<>();
        try {
            String strQuery = "SELECT p FROM CntjProcesos p WHERE p.id > 0 and p.activo = 1 ";

            List<CntjProcesos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntjProcesos pc : list) {
                listResult.add(castEntidadNegocio(pc));
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
    public CntjProceso getProcesoPorTipo(int tipoProceso) throws java.lang.Exception {
       CntjProceso resultado = null;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT p FROM CntjProcesos p WHERE p.id > 0 and p.activo = 1 and p.tipoProceso = :tipoProceso ");
            CntjProcesos proceso = getEntityManager().createQuery(strQuery.toString(), CntjProcesos.class)
                    .setParameter("tipoProceso", tipoProceso)
                    .getSingleResult();
            resultado = castEntidadNegocio(proceso);
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return resultado;
    }
    
    private CntjProceso castEntidadNegocio(CntjProcesos entidad) {
        CntjProceso objeto = new CntjProceso();
        objeto.setId(entidad.getId());
        objeto.setDescripcion(entidad.getDescripcion());
        objeto.setActivo(entidad.getActivo());
        objeto.setTipoProceso(entidad.getTipoProceso());
        objeto.setNombre(entidad.getNombre());
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        objeto.setUsuarioModifica(entidad.getUsuarioModifica());
        objeto.setFechaHoraModifica(entidad.getFechaHoraModifica());
        objeto.setTerminalModifica(entidad.getTerminalModifica());
        return objeto;
    }
    
    private CntjProcesos castNegocioEntidad(CntjProceso obj){
        CntjProcesos ent = new CntjProcesos();
        ent.setDescripcion(obj.getDescripcion());
        ent.setActivo(obj.getActivo());
        ent.setTipoProceso(obj.getTipoProceso());
        ent.setNombre(obj.getNombre());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }

    
}
