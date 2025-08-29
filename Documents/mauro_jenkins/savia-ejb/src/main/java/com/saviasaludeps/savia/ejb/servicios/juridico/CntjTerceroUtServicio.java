
package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjTercero;
import com.saviasaludeps.savia.dominio.juridico.CntjTerceroUt;
import com.saviasaludeps.savia.ejb.entidades.CntjTerceroUnionTemporal;
import com.saviasaludeps.savia.ejb.entidades.CntjTerceros;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CtnjTerceroUtRemoto;
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
@Remote(CtnjTerceroUtRemoto.class)
public class CntjTerceroUtServicio extends GenericoServicio implements CtnjTerceroUtRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjTerceroUnionTemporal c WHERE c.id > 0 AND c.borrado = 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "razonSocial":
                            strQuery.append(" AND c.razonSocial like '%").append((String) e.getValue()).append("' ");
                            break;
                        case "id_tercero":
                            strQuery.append(" AND c.cntjTercerosId.id = ").append(e.getValue());
                            break;
                        case "maeTipoDocumentoId":
                            strQuery.append(" AND c.maeTipoDocumentoId = ").append(e.getValue());
                            break;
                        case "numeroDocumento":
                            strQuery.append(" AND c.numeroDocumento = '").append((String) e.getValue()).append("' ");
                            break;
                        case "naturalezaJuridica":
                            strQuery.append(" AND c.naturalezaJuridica = ").append(e.getValue());
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
    public List<CntjTerceroUt> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntjTerceroUt> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjTerceroUnionTemporal c WHERE c.id > 0 AND c.borrado = 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "razonSocial":
                            strQuery.append(" AND c.razonSocial like '%").append((String) e.getValue()).append("' ");
                            break;
                        case "id_tercero":
                            strQuery.append(" AND c.cntjTercerosId.id = ").append(e.getValue());
                            break;
                        case "maeTipoDocumentoId":
                            strQuery.append(" AND c.maeTipoDocumentoId = ").append(e.getValue());
                            break;
                        case "numeroDocumento":
                            strQuery.append(" AND c.numeroDocumento = '").append((String) e.getValue()).append("' ");
                            break;
                        case "naturalezaJuridica":
                            strQuery.append(" AND c.naturalezaJuridica = ").append(e.getValue());
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
            List<CntjTerceroUnionTemporal> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjTerceroUnionTemporal ut : list) {
                listResult.add(castEntidadNegocio(ut));
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
    public int insertar(CntjTerceroUt objeto) throws java.lang.Exception {
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
    public CntjTerceroUt consultar(int id) throws java.lang.Exception {
        CntjTerceroUt objRes = null;
        try {
            objRes = castEntidadNegocio((CntjTerceroUnionTemporal) getEntityManager().find(CntjTerceroUnionTemporal.class, id));
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
    public void actualizar(CntjTerceroUt objeto) throws java.lang.Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CntjTerceroUnionTemporal SET cntjTercerosId.id = :idtercero, ");
            sql.append("maeTipoDocumentoId = :maeTipoDocumentoId,  ");
            sql.append("maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo,  ");
            sql.append("maeTipoDocumentoValor = :maeTipoDocumentoValor,  ");
            sql.append("numeroDocumento = :numeroDocumento,  ");
            sql.append("razonSocial = :razonSocial,  ");  
            sql.append("naturalezaJuridica = :naturalezaJuridica, ");
            sql.append("borrado = :borrado,  "); 
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("idtercero", objeto.getCntjTercero().getId());
            query.setParameter("maeTipoDocumentoId", objeto.getMaeTipoDocumentoId());
            query.setParameter("maeTipoDocumentoCodigo", objeto.getMaeTipoDocumentoCodigo());
            query.setParameter("maeTipoDocumentoValor", objeto.getMaeTipoDocumentoValor());
            query.setParameter("numeroDocumento", objeto.getNumeroDocumento());
            query.setParameter("razonSocial", objeto.getRazonSocial());
            query.setParameter("naturalezaJuridica", objeto.getNaturalezaJuridica().shortValue());
            query.setParameter("borrado", objeto.isBorrado());  
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
    public void borrar(CntjTerceroUt objeto) throws java.lang.Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CntjTerceroUnionTemporal SET  ");
            sql.append("borrado = :borrado,  "); 
            sql.append("usuarioBorra = :usuarioBorra, ");
            sql.append("usuarioBorra = :terminalBorra, ");
            sql.append("fechaHoraBorra = :fechaHoraBorra ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());            
            query.setParameter("borrado", objeto.isBorrado());
            query.setParameter("usuarioBorra", objeto.getUsuarioModifica());
            query.setParameter("terminalBorra", objeto.getTerminalModifica());
            query.setParameter("fechaHoraBorra", objeto.getFechaHoraModifica());            
            query.setParameter("id", objeto.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public List<CntjTerceroUt> consultarPorTercero(Integer idTercero) throws java.lang.Exception {
        List<CntjTerceroUt> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjTerceroUnionTemporal c WHERE c.id > 0 AND c.borrado = 0 ");
            strQuery.append(" AND c.cntjTercerosId.id = :idtercero ");            
            strQuery.append(" ORDER BY  c.id DESC ");
            
            List<CntjTerceroUnionTemporal> list = getEntityManager().createQuery(strQuery.toString())
                    .setParameter("idtercero", idTercero)
                    .getResultList();
            for (CntjTerceroUnionTemporal ut : list) {
                listResult.add(castEntidadNegocio(ut));
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
    
    private CntjTerceroUt castEntidadNegocio(CntjTerceroUnionTemporal entidad) {
        CntjTerceroUt objeto = new CntjTerceroUt();
        objeto.setId(entidad.getId());
        objeto.setCntjTercero(new CntjTercero(entidad.getCntjTercerosId().getId()));
        objeto.setMaeTipoDocumentoId(entidad.getMaeTipoDocumentoId());
        objeto.setMaeTipoDocumentoCodigo(entidad.getMaeTipoDocumentoCodigo());
        objeto.setMaeTipoDocumentoValor(entidad.getMaeTipoDocumentoValor());
        objeto.setNumeroDocumento(entidad.getNumeroDocumento());
        objeto.setRazonSocial(entidad.getRazonSocial());
        objeto.setBorrado(entidad.getBorrado());
        objeto.setNaturalezaJuridica(entidad.getNaturalezaJuridica());
        objeto.setPorcentajeParticipacion(entidad.getPorcentajeParticipacion());
        
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        objeto.setUsuarioModifica(entidad.getUsuarioModifica());
        objeto.setFechaHoraModifica(entidad.getFechaHoraModifica());
        objeto.setTerminalModifica(entidad.getTerminalModifica());
        return objeto;
    }
    
    private CntjTerceroUnionTemporal castNegocioEntidad(CntjTerceroUt obj){
        CntjTerceroUnionTemporal ent = new CntjTerceroUnionTemporal();
        ent.setCntjTercerosId(new CntjTerceros(obj.getCntjTercero().getId()));
        ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
        ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
        ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
        ent.setNumeroDocumento(obj.getNumeroDocumento());
        ent.setRazonSocial(obj.getRazonSocial());
        ent.setBorrado(obj.isBorrado());
        ent.setNaturalezaJuridica(obj.getNaturalezaJuridica());  
        ent.setPorcentajeParticipacion(obj.getPorcentajeParticipacion());
        
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }

    
}
