
package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjTercero;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.CntjTerceros;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CtnjTerceroRemoto;
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
@Remote(CtnjTerceroRemoto.class)
public class CntjTerceroServicio extends GenericoServicio implements CtnjTerceroRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjTerceros c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery.append(" AND c.nombre like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "tipoTercero":
                            strQuery.append(" AND c.tipoTercero = ").append(e.getValue());
                            break;
                        case "maeTipoDocumentoId":
                            strQuery.append(" AND c.maeTipoDocumentoId = ").append(e.getValue());
                            break;
                        case "numeroDocumento":
                            strQuery.append(" AND c.numeroDocumento = '").append(e.getValue()).append("' ");
                            break;
                        case "razonSocial":
                            strQuery.append(" AND c.razonSocial like '%").append(e.getValue()).append("%' ");
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
    public List<CntjTercero> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CntjTercero> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjTerceros  c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery.append(" AND c.nombre like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "tipoTercero":
                            strQuery.append(" AND c.tipoTercero = ").append(e.getValue());
                            break;
                        case "maeTipoDocumentoId":
                            strQuery.append(" AND c.maeTipoDocumentoId = ").append(e.getValue());
                            break;
                        case "numeroDocumento":
                            strQuery.append(" AND c.numeroDocumento = '").append(e.getValue()).append("' ");
                            break;
                        case "razonSocial":
                            strQuery.append(" AND c.razonSocial like '%").append(e.getValue()).append("%' ");
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
            List<CntjTerceros> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjTerceros grupo : list) {
                listResult.add(castEntidadNegocio(grupo));
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
    public int insertar(CntjTercero objeto) throws java.lang.Exception {
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
    public CntjTercero consultar(int idtercero) throws java.lang.Exception {
        CntjTercero objRes = null;
        try {
            objRes = castEntidadNegocio((CntjTerceros) getEntityManager().find(CntjTerceros.class, idtercero));
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
    public void actualizar(CntjTercero objeto) throws java.lang.Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CntjTerceros SET ");
            sql.append("tipoTercero = :tipoTercero,  ");
            sql.append("maeTipoDocumentoId = :maeTipoDocumentoId,  ");
            sql.append("maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo,  ");
            sql.append("maeTipoDocumentoValor = :maeTipoDocumentoValor,  ");
            sql.append("numeroDocumento = :numeroDocumento,  ");
            sql.append("razonSocial = :razonSocial,  ");
            sql.append("naturalezaJuridica = :naturalezaJuridica,  ");
            sql.append("gnUbicacionesId.id = :municipio,  ");
            sql.append("direccion = :direccion,  ");
            sql.append("correoElectronico = :correoElectronico,  ");
            sql.append("maeRepresentanteTipoDocumentoId = :maeRepresentanteTipoDocumentoId,  ");
            sql.append("maeRepresentanteTipoDocumentoCodigo = :maeRepresentanteTipoDocumentoCodigo,  ");
            sql.append("maeRepresentanteTipoDocumentoValor = :maeRepresentanteTipoDocumentoValor,  ");
            sql.append("representanteNumeroDocumento = :representanteNumeroDocumento,  ");
            sql.append("nombreRepresentanteLegal = :nombreRepresentanteLegal,  ");            
            sql.append("telefonoTercero = :telefonoTercero,  ");       
            sql.append("maeCargoId = :maeCargoId,  ");            
            sql.append("maeCargoCodigo = :maeCargoCodigo,  ");            
            sql.append("maeCargoValor = :maeCargoValor,  ");            
            sql.append("maeAreaId = :maeAreaId,  ");            
            sql.append("maeAreaCodigo = :maeAreaCodigo,  ");            
            sql.append("maeAreaValor = :maeAreaValor,  ");            
            sql.append("unionTemporal = :unionTemporal,  ");
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("tipoTercero", objeto.getTipoTercero().shortValue());
            query.setParameter("maeTipoDocumentoId", objeto.getMaeTipoDocumentoId());
            query.setParameter("maeTipoDocumentoCodigo", objeto.getMaeTipoDocumentoCodigo());
            query.setParameter("maeTipoDocumentoValor", objeto.getMaeTipoDocumentoValor());
            query.setParameter("numeroDocumento", objeto.getNumeroDocumento());
            query.setParameter("razonSocial", objeto.getRazonSocial());
            query.setParameter("naturalezaJuridica", objeto.getNaturalezaJuridica().shortValue());
            query.setParameter("municipio", objeto.getGnUbicacionId().getId());
            query.setParameter("direccion", objeto.getDireccion());
            query.setParameter("correoElectronico", objeto.getCorreoElectronico());
            query.setParameter("maeRepresentanteTipoDocumentoId", objeto.getMaeRepresentanteTipoDocumentoId());
            query.setParameter("maeRepresentanteTipoDocumentoCodigo", objeto.getMaeRepresentanteTipoDocumentoCodigo());
            query.setParameter("maeRepresentanteTipoDocumentoValor", objeto.getMaeRepresentanteTipoDocumentoValor());
            query.setParameter("representanteNumeroDocumento", objeto.getRepresentanteNumeroDocumento());
            query.setParameter("nombreRepresentanteLegal", objeto.getNombreRepresentanteLegal());
            query.setParameter("telefonoTercero", objeto.getTelefonoTercero());
            query.setParameter("maeCargoId", objeto.getMaeCargoId());
            query.setParameter("maeCargoCodigo", objeto.getMaeCargoCodigo());
            query.setParameter("maeCargoValor", objeto.getMaeCargoValor());
            query.setParameter("maeAreaId", objeto.getMaeAreaId());
            query.setParameter("maeAreaCodigo", objeto.getMaeAreaCodigo());
            query.setParameter("maeAreaValor", objeto.getMaeAreaValor());
            query.setParameter("unionTemporal", objeto.isUnionTemporal());            
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
    public List<CntjTercero> listaTercerosUt() throws java.lang.Exception {
        List<CntjTercero> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjTerceros  c WHERE c.id > 0 and c.unionTemporal = 1 "); 
            List<CntjTerceros> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (CntjTerceros grupo : list) {
                listResult.add(castEntidadNegocio(grupo));
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
    
   
    
    private CntjTercero castEntidadNegocio(CntjTerceros entidad) {
        CntjTercero objeto = new CntjTercero();
        objeto.setId(entidad.getId());
        if(entidad.getCntPrestadoresId() != null){
            objeto.setCntPrestadorId(new CntPrestador(entidad.getCntPrestadoresId().getId()));
        }
        if(entidad.getGnUsuariosId() != null){
            objeto.setGnUsuarioId(new Usuario(entidad.getGnUsuariosId().getId()));
        }        
        objeto.setTipoTercero((int)entidad.getTipoTercero());
        objeto.setNaturalezaJuridica((int)entidad.getNaturalezaJuridica());
        objeto.setMaeTipoDocumentoId(entidad.getMaeTipoDocumentoId());
        objeto.setMaeTipoDocumentoCodigo(entidad.getMaeTipoDocumentoCodigo());
        objeto.setMaeTipoDocumentoValor(entidad.getMaeTipoDocumentoValor());
        objeto.setNumeroDocumento(entidad.getNumeroDocumento());
        objeto.setRazonSocial(entidad.getRazonSocial());
        objeto.setMaeRepresentanteTipoDocumentoId(entidad.getMaeRepresentanteTipoDocumentoId());
        objeto.setMaeRepresentanteTipoDocumentoCodigo(entidad.getMaeRepresentanteTipoDocumentoCodigo());
        objeto.setMaeRepresentanteTipoDocumentoValor(entidad.getMaeRepresentanteTipoDocumentoValor());
        objeto.setRepresentanteNumeroDocumento(entidad.getRepresentanteNumeroDocumento());
        objeto.setNombreRepresentanteLegal(entidad.getNombreRepresentanteLegal());
        objeto.setCodigoHabilitacion(entidad.getCodigoHabilitacion());
        if(entidad.getGnUbicacionesId() != null ){
            Ubicacion ubicacion = new Ubicacion(entidad.getGnUbicacionesId().getId());
            ubicacion.setNombre(entidad.getGnUbicacionesId().getNombre());
            objeto.setGnUbicacionId(ubicacion);
        }        
        objeto.setDireccion(entidad.getDireccion());
        objeto.setCorreoElectronico(entidad.getCorreoElectronico());
        objeto.setTelefonoTercero(entidad.getTelefonoTercero());
        objeto.setMaeCargoId(entidad.getMaeCargoId());
        objeto.setMaeCargoCodigo(entidad.getMaeCargoCodigo());
        objeto.setMaeCargoValor(entidad.getMaeCargoValor());
        objeto.setMaeAreaId(entidad.getMaeAreaId());
        objeto.setMaeAreaCodigo(entidad.getMaeAreaCodigo());
        objeto.setMaeAreaValor(entidad.getMaeAreaValor());
        objeto.setUnionTemporal(entidad.getUnionTemporal());        
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        objeto.setUsuarioModifica(entidad.getUsuarioModifica());
        objeto.setFechaHoraModifica(entidad.getFechaHoraModifica());
        objeto.setTerminalModifica(entidad.getTerminalModifica());
        return objeto;
    }
    
    private CntjTerceros castNegocioEntidad(CntjTercero obj){
        CntjTerceros ent = new CntjTerceros();
        if(obj.getCntPrestadorId() != null){
            ent.setCntPrestadoresId(new CntPrestadores(obj.getCntPrestadorId().getId()));
        }
        if(obj.getGnUsuarioId() != null){
            ent.setGnUsuariosId(new GnUsuarios(obj.getGnUsuarioId().getId()));
        }        
        ent.setTipoTercero(obj.getTipoTercero().shortValue());
        ent.setNaturalezaJuridica(obj.getNaturalezaJuridica().shortValue());
        ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
        ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
        ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
        ent.setNumeroDocumento(obj.getNumeroDocumento());
        ent.setRazonSocial(obj.getRazonSocial());
        ent.setMaeRepresentanteTipoDocumentoId(obj.getMaeRepresentanteTipoDocumentoId());
        ent.setMaeRepresentanteTipoDocumentoCodigo(obj.getMaeRepresentanteTipoDocumentoCodigo());
        ent.setMaeRepresentanteTipoDocumentoValor(obj.getMaeRepresentanteTipoDocumentoValor());
        ent.setRepresentanteNumeroDocumento(obj.getRepresentanteNumeroDocumento());
        ent.setNombreRepresentanteLegal(obj.getNombreRepresentanteLegal());
        ent.setCodigoHabilitacion(obj.getCodigoHabilitacion());
        ent.setGnUbicacionesId(new GnUbicaciones(obj.getGnUbicacionId().getId()));
        ent.setDireccion(obj.getDireccion());
        ent.setCorreoElectronico(obj.getCorreoElectronico());
        ent.setTelefonoTercero(obj.getTelefonoTercero());
        ent.setMaeCargoId(obj.getMaeCargoId());
        ent.setMaeCargoCodigo(obj.getMaeCargoCodigo());
        ent.setMaeCargoValor(obj.getMaeCargoValor());
        ent.setMaeAreaId(obj.getMaeAreaId());
        ent.setMaeAreaCodigo(obj.getMaeAreaCodigo());
        ent.setMaeAreaValor(obj.getMaeAreaValor());
        ent.setUnionTemporal(obj.isUnionTemporal());        
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }

    
}
