/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.tutela;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuMemorialPersona;
import com.saviasaludeps.savia.ejb.entidades.TuMemorialPersonas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.tutela.TuMemorialPersonaRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author pavacca
 */
@Stateless
@Remote(TuMemorialPersonaRemoto.class)
public class TuMemorialPersonaServicio extends GenericoServicio implements TuMemorialPersonaRemoto{
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(t) FROM TuMemorialPersonas t ";
            StringBuilder strQuery = new StringBuilder(" WHERE t.id > 0 ");
            StringBuilder sql = new StringBuilder();
           
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append(" AND t.id = ").append(e.getValue()).append(" ");
                            break;
                        case "tipoPersonal":
                            strQuery.append(" AND t.tipoPersonal = ").append(e.getValue()).append(" ");
                            break;
                        case "nombreCompleto":
                            strQuery.append(" AND CONCAT(t.primerNombre,' ',t.segundoNombre,' ',t.primerApellido, ' ' ,t.segundoApellido ) LIKE '%")
                                    .append((String) e.getValue()).append("%' ");
                            break;
                        case "maeTipoDocumentoId":
                            strQuery.append(" AND t.maeTipoDocumentoId = ").append(e.getValue()).append(" ");
                            break;
                        case "numeroDocumento":
                            strQuery.append(" AND t.numeroDocumento = ").append(e.getValue()).append(" ");
                            break;
                        case "maeGnCargoId":
                            strQuery.append(" AND t.maeGnCargoId = ").append(e.getValue()).append(" ");
                            break;
                        case "numeroTarjetaProfesional":
                            strQuery.append(" AND t.numeroTarjetaProfesional = ").append(e.getValue()).append(" ");
                            break;
                          
                    }
                }
            }
            sql.append(strTitulo).append(strQuery);
            cant = (int) (long) getEntityManager().createQuery(sql.toString())
                    //                    .setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public List<TuMemorialPersona> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<TuMemorialPersona> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT t FROM TuMemorialPersonas t ";
            StringBuilder strQuery = new StringBuilder(" WHERE t.id > 0 ");
            StringBuilder sql = new StringBuilder();
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append(" AND t.id = ").append(e.getValue()).append(" ");
                            break;
                        case "tipoPersonal":
                            strQuery.append(" AND t.tipoPersonal = ").append(e.getValue()).append(" ");
                            break;
                        case "nombreCompleto":
                            strQuery.append(" AND CONCAT(t.primerNombre,' ',t.segundoNombre,' ',t.primerApellido, ' ' ,t.segundoApellido ) LIKE '%")
                                    .append((String) e.getValue()).append("%' ");
                            break;
                        case "maeTipoDocumentoId":
                            strQuery.append(" AND t.maeTipoDocumentoId = ").append(e.getValue()).append(" ");
                            break;
                        case "numeroDocumento":
                            strQuery.append(" AND t.numeroDocumento = ").append(e.getValue()).append(" ");
                            break;
                        case "maeGnCargoId":
                            strQuery.append(" AND t.maeGnCargoId = ").append(e.getValue()).append(" ");
                            break;
                        case "numeroTarjetaProfesional":
                            strQuery.append(" AND t.numeroTarjetaProfesional = ").append(e.getValue()).append(" ");
                            break;
                    }
                }
            }
            sql.append(strTitulo).append(strQuery);
            sql.append(" ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                if (paramConsulta.getOrden().equals("tipoPersonal")) {
                    sql.append("t.tipoPersonal ").append((paramConsulta.isAscendente() ? "ASC" : "DESC"));
                } else {
                    sql.append("t.").append(paramConsulta.getOrden()).append((paramConsulta.isAscendente() ? " ASC" : " DESC"));
                }
            } else {
                sql.append("t.id DESC");
            }
            List<TuMemorialPersonas> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //                    .setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (TuMemorialPersonas per : list) {
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
    public TuMemorialPersona consultar(int id) throws Exception {
        TuMemorialPersona objRes = null;
        try {
            objRes = castEntidadNegocioLargo((TuMemorialPersonas) getEntityManager().find(TuMemorialPersonas.class, id));
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
    public int insertar(TuMemorialPersona obj) throws Exception {
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
    public void actualizar(TuMemorialPersona obj) throws Exception {
        try {
            String hql = "UPDATE TuMemorialPersonas SET"
                    + " tipoPersonal = :tipoPersonal,"
                    + " maeTipoDocumentoId = :maeTipoDocumentoId,"
                    + " maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo,"
                    + " maeTipoDocumentoValor = :maeTipoDocumentoValor,"
                    + " numeroDocumento = :numeroDocumento,"
                    + " maeGnCargoId = :maeGnCargoId,"
                    + " maeGnCargoCodigo = :maeGnCargoCodigo,"
                    + " maeGnCargoValor = :maeGnCargoValor,"
                    + " numeroTarjetaProfesional = :numeroTarjetaProfesional,"
                    + " primerNombre = :primerNombre,"
                    + " segundoNombre = :segundoNombre,"
                    + " primerApellido = :primerApellido,"
                    + " segundoApellido = :segundoApellido,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica";
            hql += " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("tipoPersonal", obj.getTipoPersonal());
            query.setParameter("maeTipoDocumentoId", obj.getMaeTipoDocumentoId());
            query.setParameter("maeTipoDocumentoCodigo", obj.getMaeTipoDocumentoCodigo());
            query.setParameter("maeTipoDocumentoValor", obj.getMaeTipoDocumentoValor());
            query.setParameter("numeroDocumento", obj.getNumeroDocumento());
            query.setParameter("maeGnCargoId", obj.getMaeGnCargoId());
            query.setParameter("maeGnCargoCodigo", obj.getMaeGnCargoCodigo());
            query.setParameter("maeGnCargoValor", obj.getMaeGnCargoValor());
            query.setParameter("numeroTarjetaProfesional", obj.getNumeroTarjetaProfesional());
            query.setParameter("primerNombre", obj.getPrimerNombre());
            query.setParameter("segundoNombre", obj.getSegundoNombre());
            query.setParameter("primerApellido", obj.getPrimerApellido());
            query.setParameter("segundoApellido", obj.getSegundoApellido());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public TuMemorialPersona eliminar(int id) throws Exception {
        TuMemorialPersona obj = null;
        try {
            TuMemorialPersonas ent = getEntityManager().find(TuMemorialPersonas.class, id);
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
    public List<TuMemorialPersona> consultarListaApoderados() throws Exception {
        List<TuMemorialPersona> tutelas = new ArrayList();
        try {
            String strQuery = "SELECT t FROM TuMemorialPersonas t "
                    + "WHERE t.id > 0 "
                    + "AND t.tipoPersonal = 1 "
                    + "ORDER BY t.id DESC ";
            List<TuMemorialPersonas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (TuMemorialPersonas per : list) {
                tutelas.add(castEntidadNegocio(per));
            }
        } catch (NoResultException e) {
            tutelas = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return tutelas;
    }
    
    @Override
    public List<TuMemorialPersona> consultarListaAsistenten() throws Exception {
        List<TuMemorialPersona> tutelas = new ArrayList();
        try {
            String strQuery = "SELECT t FROM TuMemorialPersonas t "
                    + "WHERE t.id > 0 "
                    + "AND t.tipoPersonal = 2 "
                    + "ORDER BY t.id DESC ";
            List<TuMemorialPersonas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (TuMemorialPersonas per : list) {
                tutelas.add(castEntidadNegocio(per));
            }
        } catch (NoResultException e) {
            tutelas = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return tutelas;
    }
    
    public static TuMemorialPersona castEntidadNegocio(TuMemorialPersonas per) {
        TuMemorialPersona obj = new TuMemorialPersona();
        obj.setId(per.getId());
        obj.setTipoPersonal(per.getTipoPersonal());
        obj.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setNumeroDocumento(per.getNumeroDocumento());
        obj.setMaeGnCargoId(per.getMaeGnCargoId());
        obj.setMaeGnCargoCodigo(per.getMaeGnCargoCodigo());
        obj.setMaeGnCargoValor(per.getMaeGnCargoValor());
        obj.setNumeroTarjetaProfesional(per.getNumeroTarjetaProfesional());
        obj.setPrimerNombre(per.getPrimerNombre());
        obj.setSegundoNombre(per.getSegundoNombre());
        obj.setPrimerApellido(per.getPrimerApellido());
        obj.setSegundoApellido(per.getSegundoApellido());
        
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }
    
    public static TuMemorialPersona castEntidadNegocioLargo(TuMemorialPersonas per) {
        TuMemorialPersona obj = new TuMemorialPersona();
        obj.setId(per.getId());
        obj.setTipoPersonal(per.getTipoPersonal());
        obj.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setNumeroDocumento(per.getNumeroDocumento());
        obj.setMaeGnCargoId(per.getMaeGnCargoId());
        obj.setMaeGnCargoCodigo(per.getMaeGnCargoCodigo());
        obj.setMaeGnCargoValor(per.getMaeGnCargoValor());
        obj.setNumeroTarjetaProfesional(per.getNumeroTarjetaProfesional());
        obj.setPrimerNombre(per.getPrimerNombre());
        obj.setSegundoNombre(per.getSegundoNombre());
        obj.setPrimerApellido(per.getPrimerApellido());
        obj.setSegundoApellido(per.getSegundoApellido());
        if(per.getTuMemorialFirmasList() != null){
            obj.setTuMemorialFirmasList(TuMemorialFirmaServicio.castEntidadNegocio(per.getTuMemorialFirmasList()));
        }
        
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }
    
    public static TuMemorialPersonas castNegocioEntidad(TuMemorialPersona obj) {
        TuMemorialPersonas per = new TuMemorialPersonas();
        per.setTipoPersonal(obj.getTipoPersonal());
        per.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
        per.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
        per.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
        per.setNumeroDocumento(obj.getNumeroDocumento());
        per.setMaeGnCargoId(obj.getMaeGnCargoId());
        per.setMaeGnCargoCodigo(obj.getMaeGnCargoCodigo());
        per.setMaeGnCargoValor(obj.getMaeGnCargoValor());
        per.setNumeroTarjetaProfesional(obj.getNumeroTarjetaProfesional());
        per.setPrimerNombre(obj.getPrimerNombre());
        per.setSegundoNombre(obj.getSegundoNombre());
        per.setPrimerApellido(obj.getPrimerApellido());
        per.setSegundoApellido(obj.getSegundoApellido()); 
        //Auditoría
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        return per;
    }
}
