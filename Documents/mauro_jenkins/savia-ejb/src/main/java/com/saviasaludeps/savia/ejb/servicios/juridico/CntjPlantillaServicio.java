package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.juridico.CntjPlantilla;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjProceso;
import com.saviasaludeps.savia.dominio.juridico.CntjProcesoDocumento;
import com.saviasaludeps.savia.ejb.entidades.CntjPlantillas;
import com.saviasaludeps.savia.ejb.entidades.CntjProcesoDocumentos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CntjPlantillaRemoto;
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
@Remote(CntjPlantillaRemoto.class)
public class CntjPlantillaServicio extends GenericoServicio implements CntjPlantillaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(p) FROM CntjPlantillas p WHERE p.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery.append(" AND p.nombre like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "procesodocumentoId.nombre":
                            strQuery.append(" AND p.cntjProcesoDocumentosId.nombre like '%" + (String) e.getValue() + "%' ");
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
    public List<CntjPlantilla> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntjPlantilla> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT p FROM CntjPlantillas p WHERE p.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery.append(" AND p.nombre like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "procesodocumentoId.nombre":
                            strQuery.append(" AND p.cntjProcesoDocumentosId.nombre like '%" + (String) e.getValue() + "%' ");
                            break;
                    }
                }
            }
            strQuery.append(" ORDER BY ");
            if (paramConsulta.getOrden() != null) {                
                if (paramConsulta.getOrden().equals("procesodocumentoId.nombre")) {
                    strQuery.append("p.cntjProcesoDocumentosId.nombre ").append((paramConsulta.isAscendente() ? "ASC" : "DESC"));
                }else{
                    strQuery.append(" p." + paramConsulta.getOrden()).append(" ").append((paramConsulta.isAscendente() ? "ASC" : "DESC"));
                }
            } else {
                strQuery.append(" p.id DESC ");
            }
            List<CntjPlantillas> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjPlantillas plantilla : list) {
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
    public CntjPlantilla consultar(int idplantilla) throws java.lang.Exception {
        CntjPlantilla objRes = null;
        try {
            objRes = castEntidadNegocio((CntjPlantillas) getEntityManager().find(CntjPlantillas.class, idplantilla));
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
    public int insertar(CntjPlantilla objeto) throws java.lang.Exception {
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
    public void actualizar(CntjPlantilla objeto) throws java.lang.Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CntjPlantillas SET nombre = :nombre, ");
            sql.append("descripcion = :descripcion,  ");
            sql.append("activo = :activo,  ");
            sql.append("estructura = :estructura,  ");
            sql.append("version = :version,  ");
            sql.append("cntjProcesoDocumentosId.id = :cntjProcesoDocumentosId,  ");
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("nombre", objeto.getNombre());
            query.setParameter("descripcion", objeto.getDescripcion());
            query.setParameter("activo", objeto.getActivo());
            query.setParameter("estructura", objeto.getEstructura());
            query.setParameter("version", objeto.getVersion());
            query.setParameter("cntjProcesoDocumentosId", objeto.getProcesodocumentoId().getId());
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
    public CntjPlantilla eliminar(int id) throws Exception {
        CntjPlantilla obj = null;
        try {
            CntjPlantillas ent = getEntityManager().find(CntjPlantillas.class, id);
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
    public void inactivarPlantillasDocumento(CntjPlantilla objeto) throws java.lang.Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CntjPlantillas SET ");
            sql.append("activo = 0,  ");
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE cntjProcesoDocumentosId.id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("usuarioModifica", objeto.getUsuarioModifica());
            query.setParameter("terminalModifica", objeto.getTerminalModifica());
            query.setParameter("fechaHoraModifica", objeto.getFechaHoraModifica());
            query.setParameter("id", objeto.getProcesodocumentoId().getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public List<CntjPlantilla> lista() throws java.lang.Exception {
        List<CntjPlantilla> listResult = new ArrayList<>();
        try {
            Query nativeQuery = em.createNativeQuery("SELECT p.id, p.nombre FROM cntj_plantillas p WHERE p.id > 0  ");
            List<Object[]> results = nativeQuery.getResultList();
            listResult = results
                    .stream()
                    .map(result -> new CntjPlantilla(((Integer) result[0]),
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
    public List<CntjPlantilla> listaDocumentoEstadoGenerados(int idestado) throws java.lang.Exception {
        List<CntjPlantilla> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT p FROM CntjPlantillas p  ");     
            strQuery.append(" inner join CntjEstadoProcesoDocumentos ed on p.cntjProcesoDocumentosId.id = ed.cntjProcesoDocumentosId.id and p.activo = 1 ");
            strQuery.append(" where p.id > 0 and ed.cntjProcesoDocumentosId.activo = 1 and ed.cntjEstadosId.id = ").append(idestado);
            strQuery.append(" and ed.cntjProcesoDocumentosId.tipoDocumento = 0 ");
            List<CntjPlantillas> list = getEntityManager().createQuery(strQuery.toString()).getResultList();
            for (CntjPlantillas item : list) {
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
    public List<CntjPlantilla> listaDocumentoEstadoGeneradosMixtos(int idestado) throws java.lang.Exception {
        List<CntjPlantilla> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT p FROM CntjPlantillas p  ");     
            strQuery.append(" inner join CntjEstadoProcesoDocumentos ed on p.cntjProcesoDocumentosId.id = ed.cntjProcesoDocumentosId.id and p.activo = 1 ");
            strQuery.append(" where p.id > 0 and ed.cntjProcesoDocumentosId.activo = 1 and ed.cntjEstadosId.id = ").append(idestado);
            strQuery.append(" and ed.cntjProcesoDocumentosId.tipoDocumento in (0,2) ");
            List<CntjPlantillas> list = getEntityManager().createQuery(strQuery.toString()).getResultList();
            for (CntjPlantillas item : list) {
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
    public List<CntjPlantilla> listaPlantillasProceso(int idproceso) throws java.lang.Exception {
        List<CntjPlantilla> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT p FROM CntjPlantillas p  ");     
            strQuery.append(" inner join CntjProcesoDocumentos pd on p.cntjProcesoDocumentosId.id = pd.id and pd.tipoDocumento in (0,2) and  pd.activo = 1 and pd.cntjProcesosId.id = ").append(idproceso);
            strQuery.append(" where p.id > 0 and p.activo = 1 ");
            List<CntjPlantillas> list = getEntityManager().createQuery(strQuery.toString()).getResultList();
            for (CntjPlantillas item : list) {
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
    public Integer plantillasDocumento(int iddocumento) throws java.lang.Exception {
        Integer cantidad = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(p) FROM CntjPlantillas p WHERE p.id > 0 and p.cntjProcesoDocumentosId.id = :documentoid ");
            cantidad = (int) (long) getEntityManager().createQuery(strQuery.toString())
                    .setParameter("documentoid", iddocumento)
                    .getSingleResult();
        } catch (NoResultException e) {
            cantidad = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cantidad;
    }
    
    @Override
    public Integer documentosGenerados(int idplantilla) throws java.lang.Exception {
        Integer cantidad = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(p) FROM CntjDocumentos p WHERE p.id > 0 and p.cntjPlantillasId.id = :idplantilla ");
            cantidad = (int) (long) getEntityManager().createQuery(strQuery.toString())
                    .setParameter("idplantilla", idplantilla)
                    .getSingleResult();
        } catch (NoResultException e) {
            cantidad = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cantidad;
    }
    
    @Override
    public Integer plantillaIdVersionAnterior(int iddocumento) throws java.lang.Exception {
        Integer plantillaId = null;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT MAX(p.id) FROM CntjPlantillas p WHERE p.id > 0 and p.cntjProcesoDocumentosId.id = :documentoid ");
            plantillaId = (int) getEntityManager().createQuery(strQuery.toString())
                    .setParameter("documentoid", iddocumento)
                    .getSingleResult();
        } catch (NoResultException e) {
            plantillaId = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return plantillaId;
    }
    
    private CntjPlantilla castEntidadNegocio(CntjPlantillas entidad) {
        CntjPlantilla objeto = new CntjPlantilla();
        objeto.setId(entidad.getId());
        if (entidad.getCntjProcesoDocumentosId() != null) {
            CntjProcesoDocumento documento = new CntjProcesoDocumento(entidad.getCntjProcesoDocumentosId().getId());
            documento.setNombre(entidad.getCntjProcesoDocumentosId().getNombre());
            documento.setTipoDocumento(entidad.getCntjProcesoDocumentosId().getTipoDocumento());
            documento.setEtapaContratacion(entidad.getCntjProcesoDocumentosId().getEtapaContratacion());
            CntjProceso proceso = new CntjProceso(entidad.getCntjProcesoDocumentosId().getCntjProcesosId().getId());
            proceso.setNombre(entidad.getCntjProcesoDocumentosId().getCntjProcesosId().getNombre());
            documento.setProcesoId(proceso);
            objeto.setProcesodocumentoId(documento);
        }
        objeto.setDescripcion(entidad.getDescripcion());
        objeto.setEstructura(entidad.getEstructura());
        objeto.setEstructuraVisor(entidad.getEstructura());
        objeto.setNombre(entidad.getNombre());
        objeto.setActivo(entidad.getActivo());
        objeto.setVersion(entidad.getVersion());
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        objeto.setUsuarioModifica(entidad.getUsuarioModifica());
        objeto.setFechaHoraModifica(entidad.getFechaHoraModifica());
        objeto.setTerminalModifica(entidad.getTerminalModifica());
        return objeto;
    }

    private CntjPlantillas castNegocioEntidad(CntjPlantilla obj) {
        CntjPlantillas ent = new CntjPlantillas();
        ent.setId(obj.getId());
        if(obj.getProcesodocumentoId() != null){
            ent.setCntjProcesoDocumentosId(new CntjProcesoDocumentos(obj.getProcesodocumentoId().getId()));
        }
        ent.setDescripcion(obj.getDescripcion());
        ent.setEstructura(obj.getEstructura());
        ent.setNombre(obj.getNombre());
        ent.setActivo(obj.getActivo());
        ent.setVersion(obj.getVersion());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }

}
