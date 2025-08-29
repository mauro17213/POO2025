package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjContrato;
import com.saviasaludeps.savia.dominio.juridico.CntjDocumento;
import com.saviasaludeps.savia.dominio.juridico.CntjExpediente;
import com.saviasaludeps.savia.dominio.juridico.CntjPlantilla;
import com.saviasaludeps.savia.dominio.juridico.CntjProceso;
import com.saviasaludeps.savia.ejb.entidades.CntjContratos;
import com.saviasaludeps.savia.ejb.entidades.CntjDocumentos;
import com.saviasaludeps.savia.ejb.entidades.CntjExpedientes;
import com.saviasaludeps.savia.ejb.entidades.CntjPlantillas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CntjDocumentoRemoto;
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
@Remote(CntjDocumentoRemoto.class)
public class CntjDocumentoServicio  extends GenericoServicio implements CntjDocumentoRemoto{

     @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjDocumentos c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id_expediente":
                            strQuery.append(" AND c.cntjExpedientesId.id IN ( SELECT e.id FROM CntjExpedientes e WHERE e.id = ").append(e.getValue()).append(" OR e.cntjExpedientesId.id = ").append(e.getValue()).append(") ");
                            break;                        
                        case "nombre":
                            strQuery.append(" AND c.nombre like '%").append(e.getValue()).append("%' ");
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
    public List<CntjDocumento> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntjDocumento> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjDocumentos c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id_expediente":
                            strQuery.append(" AND c.cntjExpedientesId.id IN ( SELECT e.id FROM CntjExpedientes e WHERE e.id = ").append(e.getValue()).append(" OR e.cntjExpedientesId.id = ").append(e.getValue()).append(") ");
                            break;  
                        case "nombre":
                            strQuery.append(" AND c.nombre like '%").append(e.getValue()).append("%' ");
                            break; 
                    }
                }
            }
            strQuery.append(" ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                strQuery.append(" c." + paramConsulta.getOrden() + " " + (paramConsulta.isAscendente() ? "ASC" : "DESC"));
            } else {
                strQuery.append(" c.id ASC ");
            }
            List<CntjDocumentos> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjDocumentos documento : list) {
                listResult.add(castEntidadNegocio(documento));
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
    public int insertar(CntjDocumento objeto) throws java.lang.Exception {
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
    public CntjDocumento consultar(int iddocumento) throws java.lang.Exception {
        CntjDocumento objRes = null;
        try {
            objRes = castEntidadNegocio((CntjDocumentos) getEntityManager().find(CntjDocumentos.class, iddocumento));
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
    public List<CntjDocumento> documentosExpediente(int id) throws java.lang.Exception {
        List<CntjDocumento> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjDocumentos c WHERE c.id > 0 and c.cntjExpedientesId.id =  ").append(id);      
            List<CntjDocumentos> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (CntjDocumentos documento : list) {
                listResult.add(castEntidadNegocio(documento));
            }
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
     @Override
    public void actualizar(CntjDocumento objeto) throws java.lang.Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CntjDocumentos SET  ");
            sql.append("nombre = :nombre,  ");
            sql.append("descripcion = :descripcion,  ");      
            sql.append("tipo = :tipo, ");
            sql.append("documentoNombre = :documentoNombre, ");
            sql.append("documentoRuta = :documentoRuta, ");
            sql.append("documentoArchivo = :documentoArchivo, ");   
            sql.append("documentoExiste = :documentoExiste, ");   
            sql.append("cntjExpedientesId.id = :expediente, ");   
            sql.append("cntjPlantillasId.id = :plantilla, ");   
            sql.append("etapaContratacion = :etapaContratacion, ");   
            
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("nombre", objeto.getNombre());
            query.setParameter("descripcion", objeto.getDescripcion());
            query.setParameter("tipo", objeto.getTipo());
            query.setParameter("documentoNombre", objeto.getDocumentoNombre());
            query.setParameter("documentoRuta", objeto.getDocumentoRuta());
            query.setParameter("documentoArchivo", objeto.getDocumentoArchivo());    
            query.setParameter("documentoExiste", objeto.getDocumentoExiste());    
            query.setParameter("expediente", objeto.getCntjExpedienteId().getId());    
            query.setParameter("plantilla", objeto.getCntjPlantillaId() != null ? objeto.getCntjPlantillaId().getId() : null );    
            query.setParameter("etapaContratacion", objeto.getEtapaContratacion());    
            
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
    public void restablecerActualizacion(CntjDocumento objeto) throws java.lang.Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CntjDocumentos SET  ");
            sql.append("nombre = :nombre,  ");
            sql.append("descripcion = :descripcion,  ");      
            sql.append("tipo = :tipo, ");
            sql.append("documentoNombre = :documentoNombre, ");
            sql.append("documentoRuta = :documentoRuta, ");
            sql.append("documentoArchivo = :documentoArchivo, ");   
            sql.append("documentoExiste = :documentoExiste, ");   
            sql.append("cntjExpedientesId.id = :expediente, ");   
            sql.append("cntjPlantillasId.id = :plantilla, ");               
            sql.append("etapaContratacion = :etapaContratacion, ");
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");  
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("nombre", objeto.getNombre());
            query.setParameter("descripcion", objeto.getDescripcion());
            query.setParameter("tipo", objeto.getTipo());
            query.setParameter("documentoNombre", objeto.getDocumentoNombre());
            query.setParameter("documentoRuta", objeto.getDocumentoRuta());
            query.setParameter("documentoArchivo", objeto.getDocumentoArchivo());    
            query.setParameter("documentoExiste", objeto.getDocumentoExiste());    
            query.setParameter("expediente", objeto.getCntjExpedienteId().getId());    
            query.setParameter("plantilla", objeto.getCntjPlantillaId().getId());    
            query.setParameter("etapaContratacion", objeto.getEtapaContratacion());    
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
    public CntjDocumento eliminar(int id) throws java.lang.Exception {
        CntjDocumento obj = null;
        try {
            CntjDocumentos ent = getEntityManager().find(CntjDocumentos.class, id);
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
    public void eliminarPorExpediente(int idExpediente) throws java.lang.Exception {
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("DELETE FROM CntjDocumentos d WHERE d.cntjExpedientesId.id = :expediente ");
            Query query = getEntityManager().createQuery(strQuery.toString())
                    .setParameter("expediente", idExpediente);
            query.executeUpdate();
        }catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
     @Override
    public Integer existeDocumentoExpediente(int idexpediente, String documentoNombre) throws java.lang.Exception {
        Integer existe = null;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c.id FROM CntjDocumentos c WHERE c.id > 0 and c.cntjExpedientesId.id = :idexpediente and c.documentoNombre = :nombre ");
            Object result = getEntityManager().createQuery(strQuery.toString())
                    .setParameter("idexpediente", idexpediente)
                    .setParameter("nombre", documentoNombre)
                    .getSingleResult();
            existe = ((Number) result).intValue();
        } catch (NoResultException e) {
            existe = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return existe;
    }
    
    @Override
    public List<CntjDocumento> documentosExportar(int id) throws java.lang.Exception {
        List<CntjDocumento> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjDocumentos c WHERE c.id > 0  ");      
            strQuery.append(" AND c.cntjExpedientesId.id IN ( SELECT e.id FROM CntjExpedientes e WHERE e.id = ").append(id).append(" OR e.cntjExpedientesId.id = ").append(id).append(") ");
            strQuery.append(" AND c.etapaContratacion is not null ");
            List<CntjDocumentos> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (CntjDocumentos documento : list) {
                listResult.add(castEntidadNegocio(documento));
            }
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
    @Override
    public CntjDocumento getDocumentoExpedienteNombre(int idexpediente, String documentoNombre) throws java.lang.Exception {
        CntjDocumento existe = null;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjDocumentos c WHERE c.id > 0 and c.cntjExpedientesId.id = :idexpediente and c.nombre = :nombre ");
            CntjDocumentos result = getEntityManager().createQuery(strQuery.toString(), CntjDocumentos.class)
                    .setParameter("idexpediente", idexpediente)
                    .setParameter("nombre", documentoNombre)
                    .setMaxResults(1)
                    .getSingleResult();
            existe = castEntidadNegocio(result);
        } catch (NoResultException e) {
            existe = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return existe;
    }
    
    private CntjDocumento castEntidadNegocio(CntjDocumentos entidad) {
        if(entidad == null){
            return null;
        }
        CntjDocumento objeto = new CntjDocumento();
        objeto.setId(entidad.getId());
        if(entidad.getCntjPlantillasId() != null){
            objeto.setCntjPlantillaId(new CntjPlantilla(entidad.getCntjPlantillasId().getId()));
        }
        CntjExpediente expediente = new CntjExpediente(entidad.getCntjExpedientesId().getId());
        CntjProceso proceso = new CntjProceso(entidad.getCntjExpedientesId().getCntjProcesosId().getId());
        proceso.setNombre(entidad.getCntjExpedientesId().getCntjProcesosId().getNombre());
        expediente.setProcesoId(proceso);
        objeto.setCntjExpedienteId(expediente);
        if(entidad.getCntjContratosId() != null){
            objeto.setCntjContratoId(new CntjContrato(entidad.getCntjContratosId().getId()));
        }
        objeto.setEtapaContratacion(entidad.getEtapaContratacion());
        objeto.setNombre(entidad.getNombre());
        objeto.setDescripcion(entidad.getDescripcion());
        objeto.setTipo(entidad.getTipo());
        objeto.setDocumentoNombre(entidad.getDocumentoNombre());
        objeto.setDocumentoRuta(entidad.getDocumentoRuta());
        objeto.setDocumentoArchivo(entidad.getDocumentoArchivo());
        objeto.setDocumentoExiste(entidad.getDocumentoExiste());
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        objeto.setUsuarioModifica(entidad.getUsuarioModifica());
        objeto.setFechaHoraModifica(entidad.getFechaHoraModifica());
        objeto.setTerminalModifica(entidad.getTerminalModifica());
        return objeto;
    }
    
    private CntjDocumentos castNegocioEntidad(CntjDocumento obj){
        CntjDocumentos ent = new CntjDocumentos();
        if(obj.getCntjPlantillaId() != null){
            ent.setCntjPlantillasId(new CntjPlantillas(obj.getCntjPlantillaId().getId()));
        }
        if(obj.getCntjContratoId() != null){
            ent.setCntjContratosId(new CntjContratos(obj.getCntjContratoId().getId()));
        }        
        ent.setCntjExpedientesId(new CntjExpedientes(obj.getCntjExpedienteId().getId()));
        ent.setNombre(obj.getNombre());
        ent.setDescripcion(obj.getDescripcion());
        ent.setTipo(obj.getTipo());
        ent.setDocumentoNombre(obj.getDocumentoNombre());
        ent.setDocumentoRuta(obj.getDocumentoRuta());
        ent.setDocumentoArchivo(obj.getDocumentoArchivo());
        ent.setDocumentoExiste(obj.getDocumentoExiste());    
        ent.setEtapaContratacion(obj.getEtapaContratacion());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }
    
}
