package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjProceso;
import com.saviasaludeps.savia.dominio.juridico.CntjProcesoDocumento;
import com.saviasaludeps.savia.ejb.entidades.CntjProcesoDocumentos;
import com.saviasaludeps.savia.ejb.entidades.CntjProcesos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CntjProcesoDocumentoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author idbohorquez
 */
@Stateless
@Remote(CntjProcesoDocumentoRemoto.class)
public class CntjProcesoDocumentoServicio  extends GenericoServicio implements CntjProcesoDocumentoRemoto{

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjProcesoDocumentos c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id_proceso":
                            strQuery.append(" AND c.cntjProcesosId.id = " + e.getValue() + " ");
                            break;                       
                        case "nombre":
                            strQuery.append(" AND c.nombre like '%").append((String) e.getValue()).append("%' ");
                            break;                       
                        case "etapaContratacion":
                            strQuery.append(" AND c.etapaContratacion = ").append(e.getValue());
                            break;                       
                        case "tipoDocumento":
                            strQuery.append(" AND c.tipoDocumento = ").append(e.getValue());
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
    public List<CntjProcesoDocumento> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntjProcesoDocumento> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjProcesoDocumentos c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id_proceso":
                            strQuery.append(" AND c.cntjProcesosId.id = " + e.getValue() + " ");
                            break;
                        case "nombre":
                            strQuery.append(" AND c.nombre like '%").append((String) e.getValue()).append("%' ");
                            break;                       
                        case "etapaContratacion":
                            strQuery.append(" AND c.etapaContratacion = ").append(e.getValue());
                            break;                       
                        case "tipoDocumento":
                            strQuery.append(" AND c.tipoDocumento = ").append(e.getValue());
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
            List<CntjProcesoDocumentos> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjProcesoDocumentos item : list) {
                listResult.add(castEntidadNegocio(item));
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
    public int insertar(CntjProcesoDocumento objeto) throws java.lang.Exception {
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
    public CntjProcesoDocumento consultar(int id) throws java.lang.Exception {
        CntjProcesoDocumento objRes = null;
        try {
            objRes = castEntidadNegocio((CntjProcesoDocumentos) getEntityManager().find(CntjProcesoDocumentos.class, id));
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
    public void actualizar(CntjProcesoDocumento objeto) throws java.lang.Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CntjProcesoDocumentos SET nombre = :nombre, ");
            sql.append("descripcion = :descripcion,  ");
            sql.append("activo = :activo,  ");
            sql.append("etapaContratacion = :etapaContratacion,  ");
            sql.append("tipoDocumento = :tipoDocumento,  ");
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("nombre", objeto.getNombre());
            query.setParameter("descripcion", objeto.getDescripcion());
            query.setParameter("activo", objeto.isActivo()?1:0);
            query.setParameter("etapaContratacion", objeto.getEtapaContratacion());
            query.setParameter("tipoDocumento", objeto.getTipoDocumento());
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
    public List<CntjProcesoDocumento> consultarDocumentos() throws java.lang.Exception {
        List<CntjProcesoDocumento> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjProcesoDocumentos c WHERE c.id > 0 order by c.nombre asc ");
            List<CntjProcesoDocumentos> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (CntjProcesoDocumentos item : list) {
                listResult.add(castEntidadNegocio(item));
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
    public List<CntjProcesoDocumento> consultarDocumentosGenerados() throws java.lang.Exception {
        List<CntjProcesoDocumento> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjProcesoDocumentos c WHERE c.id > 0 and c.tipoDocumento = 0 order by c.nombre asc ");
            List<CntjProcesoDocumentos> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (CntjProcesoDocumentos item : list) {
                listResult.add(castEntidadNegocio(item));
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
    public List<CntjProcesoDocumento> consultarDocumentosGeneradosMixtos() throws java.lang.Exception {
        List<CntjProcesoDocumento> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjProcesoDocumentos c WHERE c.id > 0 and c.tipoDocumento in (0,2) order by c.nombre asc ");
            List<CntjProcesoDocumentos> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (CntjProcesoDocumentos item : list) {
                listResult.add(castEntidadNegocio(item));
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
    public List<CntjProcesoDocumento> consultarDocumentosProceso(int idproceso) throws java.lang.Exception {
        List<CntjProcesoDocumento> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjProcesoDocumentos c WHERE c.id > 0 and c.cntjProcesosId.id = :idproceso order by c.nombre asc ");
            List<CntjProcesoDocumentos> list = getEntityManager().createQuery(strQuery.toString())
                    .setParameter("idproceso", idproceso)
                    .getResultList();
            for (CntjProcesoDocumentos item : list) {
                listResult.add(castEntidadNegocio(item));
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
    
    
    private CntjProcesoDocumento castEntidadNegocio(CntjProcesoDocumentos entidad) {
        CntjProcesoDocumento objeto = new CntjProcesoDocumento();
        objeto.setId(entidad.getId());
        if(entidad.getCntjProcesosId() != null){
            CntjProceso proceso = new CntjProceso(entidad.getCntjProcesosId().getId());
            proceso.setNombre(entidad.getCntjProcesosId().getNombre());
            objeto.setProcesoId(proceso);
        }        
        objeto.setNombre(entidad.getNombre());
        objeto.setDescripcion(entidad.getDescripcion());
        objeto.setActivo(entidad.getActivo() == 1);
        objeto.setEtapaContratacion(entidad.getEtapaContratacion());
        objeto.setTipoDocumento(entidad.getTipoDocumento());
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        objeto.setUsuarioModifica(entidad.getUsuarioModifica());
        objeto.setFechaHoraModifica(entidad.getFechaHoraModifica());
        objeto.setTerminalModifica(entidad.getTerminalModifica());
        return objeto;
    }
    
    private CntjProcesoDocumentos castNegocioEntidad(CntjProcesoDocumento obj){
        CntjProcesoDocumentos ent = new CntjProcesoDocumentos();
        ent.setCntjProcesosId(new CntjProcesos(obj.getProcesoId().getId()));
        ent.setNombre(obj.getNombre());
        ent.setDescripcion(obj.getDescripcion());
        ent.setActivo(obj.isActivo() ? 1 : 0);
        ent.setEtapaContratacion(obj.getEtapaContratacion());
        ent.setTipoDocumento(obj.getTipoDocumento());                
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }
   
    
}
