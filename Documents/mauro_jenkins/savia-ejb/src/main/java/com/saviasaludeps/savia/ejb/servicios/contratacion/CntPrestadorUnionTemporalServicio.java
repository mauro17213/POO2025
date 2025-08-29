/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadoresUnionTemporal;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorUnionTemporal;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorUnionTemporalRemoto;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(CntPrestadorUnionTemporalRemoto.class)
public class CntPrestadorUnionTemporalServicio extends GenericoServicio implements CntPrestadorUnionTemporalRemoto {
    
    public static CntPrestadoresUnionTemporal castEntidadNegocio(CntPrestadorUnionTemporal per) {
        CntPrestadoresUnionTemporal obj = new CntPrestadoresUnionTemporal();
        obj.setId(per.getId());
        obj.setBorrado(per.getBorrado());
        //objetos
        if(per.getCntPrestadorUnionTemporalId()!= null) {
            obj.setCntPrestadorUnionTemporal(castCntPrestadorEntidadNegocio(per.getCntPrestadorUnionTemporalId()));
        }
        if(per.getCntPrestadoresId() != null) {
            obj.setCntPrestador(castCntPrestadorEntidadNegocio(per.getCntPrestadoresId()));
        }
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setFechaHoraBorra(per.getFechaHoraBorra());
        obj.setTerminalBorra(per.getTerminalBorra());
        obj.setUsuarioBorra(per.getUsuarioBorra());
        return obj;
    }
    
    public static CntPrestador castCntPrestadorEntidadNegocio(CntPrestadores per) {
        CntPrestador obj = new CntPrestador();
        obj.setId(per.getId());
        obj.setCodigoMinSalud(per.getCodigoMinSalud());
        obj.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setNumeroDocumento(per.getNumeroDocumento());
        obj.setDigitoVerificacion(per.getDigitoVerificacion());
        obj.setRazonSocial(per.getRazonSocial());
        obj.setNaturalezaJuridica(per.getNaturalezaJuridica());
        obj.setPrefijo(per.getPrefijo());
        obj.setMaeClasePrestador(per.getMaeClasePrestadorId());
        obj.setMaeClasePrestadorCodigo(per.getMaeClasePrestadorCodigo());
        obj.setMaeClasePrestadorValor(per.getMaeClasePrestadorValor());
        obj.setCategoriaPrestador(per.getCategoriaPrestador());
        obj.setNivelAtencion(per.getNivelAtencion());
        obj.setMaeTipoDocumentoRepId(per.getMaeTipoDocumentoRepId());
        obj.setMaeTipoDocumentoRepCodigo(per.getMaeTipoDocumentoRepCodigo());
        obj.setMaeTipoDocumentoRepValor(per.getMaeTipoDocumentoRepValor());
        obj.setNumeroDocumentoRep(per.getNumeroDocumentoRep());
        obj.setNombreRepresentanteLegal(per.getNombreRepresentanteLegal());
        obj.setActivo(per.getActivo());
        if (per.getFacturacionElectronica() != null) {
            obj.setFacturacionElectronica(per.getFacturacionElectronica());
        } else {
            obj.setFacturacionElectronica(false);
        }
        //objetos
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }
    
    public static CntPrestadorUnionTemporal castNegocioEntidad(CntPrestadoresUnionTemporal obj) {
        CntPrestadorUnionTemporal per = new CntPrestadorUnionTemporal();
        per.setId(obj.getId());
        per.setBorrado(obj.getBorrado());
        //objetos
        if (obj.getCntPrestadorUnionTemporal() != null) {
            per.setCntPrestadorUnionTemporalId(new CntPrestadores(obj.getCntPrestadorUnionTemporal().getId()));
        }
        if (obj.getCntPrestador() != null) {
            per.setCntPrestadoresId(new CntPrestadores(obj.getCntPrestador().getId()));
        }
                
        //auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setFechaHoraBorra(obj.getFechaHoraBorra());
        per.setTerminalBorra(obj.getTerminalBorra());
        per.setUsuarioBorra(obj.getUsuarioBorra());
        return per;
    }
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(DISTINCT p) FROM CntPrestadorUnionTemporal p "
                    + "WHERE p.id > 0 AND (p.borrado IS NULL OR p.borrado = 0) ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND p.cntPrestadorUnionTemporalId.id = " + paramConsulta.getParametroConsulta1() + " " ;
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntPrestador.codigoMinSalud":
                            strQuery += " AND p.cntPrestadoresId.codigoMinSalud = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.maeTipoDocumentoId":
                            strQuery += " AND p.cntPrestadoresId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "cntPrestador.numeroDocumento":
                            strQuery += " AND p.cntPrestadoresId.numeroDocumento  = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.razonSocial":
                            strQuery += " AND p.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public List<CntPrestadoresUnionTemporal> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CntPrestadoresUnionTemporal> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntPrestadorUnionTemporal p "
                    + "WHERE p.id > 0 AND (p.borrado IS NULL OR p.borrado = 0) ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND p.cntPrestadorUnionTemporalId.id = " + paramConsulta.getParametroConsulta1() + " " ;
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntPrestador.codigoMinSalud":
                            strQuery += " AND p.cntPrestadoresId.codigoMinSalud = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.maeTipoDocumentoId":
                            strQuery += " AND p.cntPrestadoresId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "cntPrestador.numeroDocumento":
                            strQuery += " AND p.cntPrestadoresId.numeroDocumento  = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.razonSocial":
                            strQuery += " AND p.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch(paramConsulta.getOrden()) {
                    case "cntPrestador.codigoMinSalud":
                    strQuery += "p.cntPrestadoresId.codigoMinSalud "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                    break;
                    case "cntPrestador.numeroDocumento":
                    strQuery += "p.cntPrestadoresId.numeroDocumento "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                    break;
                    default:
                        strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                    break;
                }
            } else {
                strQuery += "p.id DESC";
            }
            List<CntPrestadorUnionTemporal> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (CntPrestadorUnionTemporal per : list) {
                listResult.add(castEntidadNegocio(per));
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
    public CntPrestadoresUnionTemporal consultar(int id) throws Exception {
        CntPrestadoresUnionTemporal objRes = null;
        try {
            CntPrestadorUnionTemporal per = getEntityManager().find(CntPrestadorUnionTemporal.class, id);
            objRes = castEntidadNegocio(per);
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
    public int insertar(CntPrestadoresUnionTemporal obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(_id);
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
    public void actualizar(CntPrestadoresUnionTemporal obj) throws Exception {
        try {
            CntPrestadorUnionTemporal prestador = castNegocioEntidad(obj);
            //getEntityManager().merge(castNegocioEntidad(obj));
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE CntPrestadorUnionTemporal p SET ";
            strQuery += " p.borrado = :borrado ,";
            //auditoria
            strQuery += " p.usuarioModifica = :usuarioModifica ,";
            strQuery += " p.fechaHoraModifica = :fechaHoraModifica ,";
            strQuery += " p.terminalModifica = :terminalModifica, ";
            strQuery += " p.usuarioBorra = :usuarioBorra ,";
            strQuery += " p.terminalBorra = :terminalBorra ,";
            strQuery += " p.fechaHoraBorra = :fechaHoraBorra ";
            strQuery += " WHERE p.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(prestador);
            query.executeUpdate();
        } catch (NoResultException e) {
            
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public CntPrestadoresUnionTemporal eliminar(int id) throws Exception {
        CntPrestadoresUnionTemporal obj = null;
        try {
            CntPrestadorUnionTemporal ent = getEntityManager().find(CntPrestadorUnionTemporal.class, id);
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
    public List<CntPrestadoresUnionTemporal> consultarTodos() throws Exception {
        List<CntPrestadoresUnionTemporal> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntPrestadorUnionTemporal p "
                    + "ORDER BY p.id ";
            List<CntPrestadorUnionTemporal> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntPrestadorUnionTemporal per : list) {
                listResult.add(castEntidadNegocio(per));
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
    public int consultarPorPrestadorUnionTemporalIdyPrestadorId(int idPrestadorUnionTemporal, int idPrestador) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM CntPrestadorUnionTemporal p "
                    + "WHERE p.cntPrestadorUnionTemporalId.id = " + idPrestadorUnionTemporal + " "
                    + "AND p.cntPrestadoresId.id = " + idPrestador + " "
                    + "AND (p.borrado IS NULL OR p.borrado = 0) ";
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
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
        
}
