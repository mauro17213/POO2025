package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjCampo;
import com.saviasaludeps.savia.dominio.juridico.CntjProceso;
import com.saviasaludeps.savia.ejb.entidades.CntjCampos;
import com.saviasaludeps.savia.ejb.entidades.CntjProcesos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CntjCampoRemoto;
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
@Remote(CntjCampoRemoto.class)
public class CntjCampoServicio  extends GenericoServicio implements CntjCampoRemoto{

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjCampos c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id_proceso":
                            strQuery.append(" AND c.cntjProcesosId.id = " + e.getValue() + " ");
                            break;
                        case "nombre":
                            strQuery.append(" AND c.nombre like '%" + (String) e.getValue() + "%' ");
                            break;                        
                        case "tabla":
                            strQuery.append(" AND c.tablaDestino like '%" + (String) e.getValue() + "%' ");
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
    public List<CntjCampo> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntjCampo> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjCampos c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id_proceso":
                            strQuery.append(" AND c.cntjProcesosId.id = " + e.getValue() + " ");
                            break;
                        case "nombre":
                            strQuery.append(" AND c.nombre like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "tabla":
                            strQuery.append(" AND c.tablaDestino like '%" + (String) e.getValue() + "%' ");
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
            List<CntjCampos> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjCampos campo : list) {
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
    public int insertar(CntjCampo objeto) throws java.lang.Exception {
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
    public CntjCampo consultar(int idcampo) throws java.lang.Exception {
        CntjCampo objRes = null;
        try {
            objRes = castEntidadNegocio((CntjCampos) getEntityManager().find(CntjCampos.class, idcampo));
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
    public void actualizar(CntjCampo objeto) throws java.lang.Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CntjCampos SET nombre = :nombre, ");
            sql.append("etiqueta = :etiqueta,  ");
            sql.append("tipoDato = :tipoDato,  ");
            sql.append("descripcion = :descripcion,  ");
            sql.append("aplicaMaestro = :aplicaMaestro,  ");
            sql.append("maestro = :maestro,  ");      
            sql.append("tablaDestino = :tablaDestino,  ");      
            sql.append("campoDestino = :campoDestino,  "); 
            sql.append("valoresLista = :valoresLista,  "); 
            sql.append("campoReferencia = :campoReferencia,  "); 
            sql.append("valorReferencia = :valorReferencia,  ");             
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("nombre", objeto.getNombre());
            query.setParameter("etiqueta", objeto.getEtiqueta());
            query.setParameter("tipoDato", objeto.getTipoDato());
            query.setParameter("descripcion", objeto.getDescripcion());
            query.setParameter("aplicaMaestro", objeto.isAplicaMaestro());
            query.setParameter("maestro", objeto.getMaestroTipo() != null ? Integer.parseInt(objeto.getMaestroTipo()) : null);            
            query.setParameter("tablaDestino", objeto.getTabla());            
            query.setParameter("campoDestino", objeto.getCampo());  
            query.setParameter("valoresLista", objeto.getValoresLista());  
            query.setParameter("campoReferencia", objeto.getCampoReferencia());  
            query.setParameter("valorReferencia", objeto.getValorReferencia());              
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
    public List<CntjCampo> consultarCamposProceso(int idproceso) throws java.lang.Exception {
        List<CntjCampo> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjCampos c WHERE c.id > 0 and c.cntjProcesosId.id = :idproceso order by c.nombre asc ");
            List<CntjCampos> list = getEntityManager().createQuery(strQuery.toString())
                    .setParameter("idproceso", idproceso)
                    .getResultList();
            for (CntjCampos campo : list) {
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
    public List<CntjCampo> listaCamposDocumentoEstadoGenerados(int idestado) throws java.lang.Exception {
        List<CntjCampo> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT cp FROM CntjEstadoProcesoDocumentos epd  ");     
            strQuery.append(" inner join CntjPlantillas p on p.cntjProcesoDocumentosId.id = epd.cntjProcesoDocumentosId.id and p.activo = 1 and epd.cntjProcesoDocumentosId.tipoDocumento in (0,2)  and epd.cntjEstadosId.id = ").append(idestado);
            strQuery.append(" inner join CntjPlantillaCampos pc on pc.cntjPlantillasId.id = p.id ");            
            strQuery.append(" inner join CntjCampos cp on pc.cntjCampoId.id = cp.id  ");  //and cp.tipoDato <> 7
            strQuery.append(" where epd.cntjProcesoDocumentosId.activo = 1  ");
            strQuery.append(" GROUP BY cp.id  ");
            List<CntjCampos> list = getEntityManager().createQuery(strQuery.toString()).getResultList();
            for (CntjCampos item : list) {
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
    public List<String> listaTablasDb() throws java.lang.Exception {
        List<String> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT table_name FROM information_schema.tables WHERE table_name like 'cntj_%' and table_schema = DATABASE();");
            List<String> list = getEntityManager().createNativeQuery(strQuery.toString()).getResultList();
            listResult = new ArrayList<>(list);
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
    public List<String> listaCamposTabla(String tbl) throws java.lang.Exception {
        List<String> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder(" SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = :tabla ");
            List<String> list = getEntityManager().createNativeQuery(strQuery.toString())
                    .setParameter("tabla", tbl)
                    .getResultList();
            listResult = new ArrayList<>(list);
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
    public List<CntjCampo> listaCamposReferenciados(int idproceso) throws java.lang.Exception {
        List<CntjCampo> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjCampos c WHERE c.id > 0 and c.cntjProcesosId.id = :idproceso and (c.tipoDato = 5 or c.tipoDato = 8 ) order by c.nombre asc ");
            List<CntjCampos> list = getEntityManager().createQuery(strQuery.toString())
                    .setParameter("idproceso", idproceso)
                    .getResultList();
            for (CntjCampos campo : list) {
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
    public boolean existeNombreCampo(int idproceso, String nombre) throws java.lang.Exception {
        boolean existe = false;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT count(c) FROM CntjCampos c WHERE c.id > 0 and c.cntjProcesosId.id = :idproceso and c.nombre = :nombre ");
            Integer resultado = (int)(long) getEntityManager().createQuery(strQuery.toString())
                    .setParameter("idproceso", idproceso)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
            if(resultado > 0){
                existe = true;
            }
        } catch (NoResultException e) {
            existe = false;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return existe;
    }
    
    @Override
    public boolean existeEtiquetaCampo(int idproceso, String etiqueta) throws java.lang.Exception {
        boolean existe = false;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT count(c) FROM CntjCampos c WHERE c.id > 0 and c.cntjProcesosId.id = :idproceso and c.etiqueta = :etiqueta ");
            Integer resultado = (int)(long) getEntityManager().createQuery(strQuery.toString())
                    .setParameter("idproceso", idproceso)
                    .setParameter("etiqueta", etiqueta)
                    .getSingleResult();
            if(resultado > 0){
                existe = true;
            }
        } catch (NoResultException e) {
            existe = false;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return existe;
    }
    
    @Override
    public List<CntjCampo> camposPorReferencia(int idestado, String nombreCampo) throws java.lang.Exception {
        List<CntjCampo> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT cp FROM CntjEstadoProcesoDocumentos epd  ");     
            strQuery.append(" inner join CntjPlantillas p on p.cntjProcesoDocumentosId.id = epd.cntjProcesoDocumentosId.id and p.activo = 1 and epd.cntjProcesoDocumentosId.tipoDocumento in (0,2)  and epd.cntjEstadosId.id = ").append(idestado);
            strQuery.append(" inner join CntjPlantillaCampos pc on pc.cntjPlantillasId.id = p.id ");            
            strQuery.append(" inner join CntjCampos cp on pc.cntjCampoId.id = cp.id and cp.tipoDato = 7 and cp.campoReferencia = :campoReferencia ");            
            strQuery.append(" where epd.cntjProcesoDocumentosId.activo = 1  ");
            List<CntjCampos> list = getEntityManager().createQuery(strQuery.toString())
                    .setParameter("campoReferencia", nombreCampo)
                    .getResultList();
            for (CntjCampos item : list) {
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
    public List<CntjCampo> camposPorReferenciaCampo(String campo) throws java.lang.Exception {
        List<CntjCampo> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT cp FROM CntjCampos cp  ");               
            strQuery.append(" where cp.id > 0 and cp.tipoDato = 7 and cp.campoReferencia = :campo   ");
            List<CntjCampos> list = getEntityManager().createQuery(strQuery.toString())
                    .setParameter("campo", campo)
                    .getResultList();
            for (CntjCampos item : list) {
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
    
    private CntjCampo castEntidadNegocio(CntjCampos entidad) {
        CntjCampo objeto = new CntjCampo();
        objeto.setId(entidad.getId());
        objeto.setCntjProcesoId(new CntjProceso(entidad.getCntjProcesosId().getId()));
        objeto.setNombre(entidad.getNombre());
        objeto.setDescripcion(entidad.getDescripcion());
        objeto.setEtiqueta(entidad.getEtiqueta());
        objeto.setTipoDato(Integer.valueOf(entidad.getTipoDato()));
        objeto.setAplicaMaestro(entidad.getAplicaMaestro());
        if(entidad.getMaestro() != null){
            objeto.setMaestroTipo( String.format("%02d", entidad.getMaestro()));
        }        
        objeto.setTabla(entidad.getTablaDestino());
        objeto.setCampo(entidad.getCampoDestino());
        objeto.setValoresLista(entidad.getValoresLista());        
        objeto.setCampoReferencia(entidad.getCampoReferencia());        
        objeto.setValorReferencia(entidad.getValorReferencia()); 
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        objeto.setUsuarioModifica(entidad.getUsuarioModifica());
        objeto.setFechaHoraModifica(entidad.getFechaHoraModifica());
        objeto.setTerminalModifica(entidad.getTerminalModifica());
        return objeto;
    }
    
    private CntjCampos castNegocioEntidad(CntjCampo obj){
        CntjCampos ent = new CntjCampos();
        ent.setCntjProcesosId(new CntjProcesos(obj.getCntjProcesoId().getId()));
        ent.setNombre(obj.getNombre());
        ent.setDescripcion(obj.getDescripcion());
        ent.setEtiqueta(obj.getEtiqueta());
        ent.setTipoDato(obj.getTipoDato().shortValue());
        ent.setAplicaMaestro(obj.isAplicaMaestro());
        if(obj.getMaestroTipo() != null){
            ent.setMaestro(Integer.valueOf(obj.getMaestroTipo()));
        }      
        ent.setTablaDestino(obj.getTabla());
        ent.setCampoDestino(obj.getCampo());        
        ent.setValoresLista(obj.getValoresLista());        
        ent.setCampoReferencia(obj.getCampoReferencia());        
        ent.setValorReferencia(obj.getValorReferencia()); 
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }
   
    
}
