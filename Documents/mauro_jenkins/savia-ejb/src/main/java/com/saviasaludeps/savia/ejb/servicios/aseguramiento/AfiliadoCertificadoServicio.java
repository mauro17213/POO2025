/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.aseguramiento;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoCertificado;
import com.saviasaludeps.savia.dominio.aseguramiento.CertificadoAfiliacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliadoCertificados;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoCertificadoRemoto;

/**
 *
 * @author jose perez hernandez
 */
@Stateless
@Remote(AfiliadoCertificadoRemoto.class)
public class AfiliadoCertificadoServicio extends GenericoServicio implements AfiliadoCertificadoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AsegAfiliadoCertificados p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "asegAfiliado.idAfiliado":
                            strQuery += "AND p.asegAfiliadosId.idAfiliado = '" + e.getValue() + "' ";
                            break;
                        case "maeTipoDocumentoId":
                            strQuery += "AND p.maeTipoDocumentoId = " +  e.getValue() + " ";
                            break;
                        case "numeroDocumento":
                            strQuery += "AND p.numeroDocumento = '" + e.getValue() + "' ";
                            break;
                        case "primerApellido":
                            strQuery += "AND p.primerApellido LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "segundoApellido":
                            strQuery += "AND p.segundoApellido LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "primerNombre":
                            strQuery += "AND p.primerNombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "segundoNombre":
                            strQuery += "AND p.segundoNombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += "AND p.tipo = " + e.getValue() + " ";
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
    public List<AsegAfiliadoCertificado> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AsegAfiliadoCertificado> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM AsegAfiliadoCertificados p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "asegAfiliado.idAfiliado":
                            strQuery += "AND p.asegAfiliadosId.idAfiliado = '" + e.getValue() + "' ";
                            break;
                        case "maeTipoDocumentoId":
                            strQuery += "AND p.maeTipoDocumentoId = " +  e.getValue() + " ";
                            break;
                        case "numeroDocumento":
                            strQuery += "AND p.numeroDocumento = '" + e.getValue() + "' ";
                            break;
                        case "primerApellido":
                            strQuery += "AND p.primerApellido LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "segundoApellido":
                            strQuery += "AND p.segundoApellido LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "primerNombre":
                            strQuery += "AND p.primerNombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "segundoNombre":
                            strQuery += "AND p.segundoNombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += "AND p.tipo = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<AsegAfiliadoCertificados> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (AsegAfiliadoCertificados per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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
    public AsegAfiliadoCertificado consultar(int id) throws Exception {
        AsegAfiliadoCertificado objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AsegAfiliadoCertificados) getEntityManager().find(AsegAfiliadoCertificados.class, id));
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
    public int insertar(AsegAfiliadoCertificado obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidadLargo(obj)).getId();
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
    public void actualizar(AsegAfiliadoCertificado obj) throws Exception {
        try {
            getEntityManager().merge(castNegocioEntidadLargo(obj));
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AsegAfiliadoCertificado eliminar(int id) throws Exception {
        AsegAfiliadoCertificado obj = null;
        try {
            AsegAfiliadoCertificados ent = getEntityManager().find(AsegAfiliadoCertificados.class, id);
            if (ent != null) {
                obj = castEntidadNegocioLargo(ent);
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
    public List<AsegAfiliadoCertificado> consultarTodos() throws Exception {
        List<AsegAfiliadoCertificado> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegAfiliadoCertificados p "
                    + "ORDER BY p.fechaHoraCrea DESC";
            List<AsegAfiliadoCertificados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegAfiliadoCertificados per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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

    public static AsegAfiliadoCertificado castEntidadNegocioLargo(AsegAfiliadoCertificados per) {
        AsegAfiliadoCertificado obj = new AsegAfiliadoCertificado();
        obj.setId(per.getId());
        obj.setIdAfiliado(per.getIdAfiliado());
        obj.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setNumeroDocumento(per.getNumeroDocumento());
        obj.setPrimerNombre(per.getPrimerNombre());
        obj.setSegundoNombre(per.getSegundoNombre());
        obj.setPrimerApellido(per.getPrimerApellido());
        obj.setSegundoApellido(per.getSegundoApellido());
        obj.setFechaNacimiento(per.getFechaNacimiento());
        obj.setMaeSubgrupoSisbenId(per.getMaeSubgrupoSisbenId());
        obj.setMaeSubgrupoSisbenCodigo(per.getMaeSubgrupoSisbenCodigo());
        obj.setMaeSubgrupoSisbenValor(per.getMaeSubgrupoSisbenValor());
        obj.setMaeNivelId(per.getMaeNivelId());
        obj.setMaeNivelCodigo(per.getMaeNivelCodigo());
        obj.setMaeNivelValor(per.getMaeNivelValor());
        obj.setMaeGrupoPoblacionalId(per.getMaeGrupoPoblacionalId());
        obj.setMaeGrupoPoblacionalCodigo(per.getMaeGrupoPoblacionalCodigo());
        obj.setMaeGrupoPoblacionalValor(per.getMaeGrupoPoblacionalValor());
        obj.setFechaAfiliacion(per.getFechaAfiliacion());
        obj.setMaeRegimenId(per.getMaeRegimenId());
        obj.setMaeRegimenDescripcion(per.getMaeRegimenDescripcion());
        obj.setMaeRegimenValor(per.getMaeRegimenValor());
        obj.setMaeEstadoAfiliacionId(per.getMaeEstadoAfiliacionId());
        obj.setMaeEstadoAfiliacionCodigo(per.getMaeEstadoAfiliacionCodigo());
        obj.setMaeEstadoAfiliacionValor(per.getMaeEstadoAfiliacionValor());
        obj.setModeloLiquidacion(per.getModeloLiquidacion());
        obj.setFechaRetiro(per.getFechaRetiro());
        obj.setSemanaAfiliacion(per.getSemanaAfiliacion());
        obj.setDireccionAfiliado(per.getDireccionAfiliado());
        obj.setTelefonoAfiliado(per.getTelefonoAfiliado());
        obj.setCelularAfiliado(per.getCelularAfiliado());
        obj.setAfiliacionUbicacionId(per.getAfiliacionUbicacionId());
        obj.setAfiliacionUbicacionValor(per.getAfiliacionUbicacionValor());
        obj.setResidenciaUbicacionId(per.getResidenciaUbicacionId());
        obj.setResidenciaUbicacionValor(per.getResidenciaUbicacionValor());
        obj.setCntPrestadorSedesId(per.getCntPrestadorSedesId());
        obj.setCntPrestadorSedesValor(per.getCntPrestadorSedesValor());
        obj.setCorreoElectronico(per.getCorreoElectronico());
        obj.setTipo(per.getTipo());
        obj.setFechaInicioVigencia(per.getFechaInicioVigencia());
        obj.setFechaFinVigencia(per.getFechaFinVigencia());
        obj.setDiasVigencia(per.getDiasVigencia());
        obj.setNombreArchivo(per.getNombreArchivo());
        obj.setRuta(per.getRuta());
        //objetos
        if (per.getAsegAfiliadosId() != null) {
            obj.setAsegAfiliado(new AsegAfiliado(per.getAsegAfiliadosId().getId(), per.getAsegAfiliadosId().getIdAfiliado(),
                    per.getAsegAfiliadosId().getPrimerNombre(), per.getAsegAfiliadosId().getSegundoNombre(), per.getAsegAfiliadosId().getPrimerApellido(), per.getAsegAfiliadosId().getSegundoApellido()));
        }
        // auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        return obj;
    }

    public static AsegAfiliadoCertificados castNegocioEntidadLargo(AsegAfiliadoCertificado obj) {
        AsegAfiliadoCertificados per = new AsegAfiliadoCertificados();
        per.setId(obj.getId());
        per.setIdAfiliado(obj.getIdAfiliado());
        per.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
        per.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
        per.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
        per.setNumeroDocumento(obj.getNumeroDocumento());
        per.setPrimerNombre(obj.getPrimerNombre());
        per.setSegundoNombre(obj.getSegundoNombre());
        per.setPrimerApellido(obj.getPrimerApellido());
        per.setSegundoApellido(obj.getSegundoApellido());
        per.setFechaNacimiento(obj.getFechaNacimiento());
        per.setMaeSubgrupoSisbenId(obj.getMaeSubgrupoSisbenId());
        per.setMaeSubgrupoSisbenCodigo(obj.getMaeSubgrupoSisbenCodigo());
        per.setMaeSubgrupoSisbenValor(obj.getMaeSubgrupoSisbenValor());
        per.setMaeNivelId(obj.getMaeNivelId());
        per.setMaeNivelCodigo(obj.getMaeNivelCodigo());
        per.setMaeNivelValor(obj.getMaeNivelValor());
        per.setMaeGrupoPoblacionalId(obj.getMaeGrupoPoblacionalId());
        per.setMaeGrupoPoblacionalCodigo(obj.getMaeGrupoPoblacionalCodigo());
        per.setMaeGrupoPoblacionalValor(obj.getMaeGrupoPoblacionalValor());
        per.setFechaAfiliacion(obj.getFechaAfiliacion());
        per.setMaeRegimenId(obj.getMaeRegimenId());
        per.setMaeRegimenDescripcion(obj.getMaeRegimenDescripcion());
        per.setMaeRegimenValor(obj.getMaeRegimenValor());
        per.setMaeEstadoAfiliacionId(obj.getMaeEstadoAfiliacionId());
        per.setMaeEstadoAfiliacionCodigo(obj.getMaeEstadoAfiliacionCodigo());
        per.setMaeEstadoAfiliacionValor(obj.getMaeEstadoAfiliacionValor());
        per.setModeloLiquidacion(obj.getModeloLiquidacion());
        per.setFechaRetiro(obj.getFechaRetiro());
        per.setSemanaAfiliacion(obj.getSemanaAfiliacion());
        per.setDireccionAfiliado(obj.getDireccionAfiliado());
        per.setTelefonoAfiliado(obj.getTelefonoAfiliado());
        per.setCelularAfiliado(obj.getCelularAfiliado());
        per.setAfiliacionUbicacionId(obj.getAfiliacionUbicacionId());
        per.setAfiliacionUbicacionValor(obj.getAfiliacionUbicacionValor());
        per.setResidenciaUbicacionId(obj.getResidenciaUbicacionId());
        per.setResidenciaUbicacionValor(obj.getResidenciaUbicacionValor());
        per.setCntPrestadorSedesId(obj.getCntPrestadorSedesId());
        per.setCntPrestadorSedesValor(obj.getCntPrestadorSedesValor());
        per.setCorreoElectronico(obj.getCorreoElectronico());
        per.setTipo(obj.getTipo());
        per.setFechaInicioVigencia(obj.getFechaInicioVigencia());
        per.setFechaFinVigencia(obj.getFechaFinVigencia());
        per.setDiasVigencia(obj.getDiasVigencia());
        per.setNombreArchivo(obj.getNombreArchivo());
        per.setRuta(obj.getRuta());
        //objetos
        if (obj.getAsegAfiliado() != null) {
            per.setAsegAfiliadosId(new AsegAfiliados(obj.getAsegAfiliado().getId()));
        }
        // auditoria
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        return per;
    }

    @Override
    public List<CertificadoAfiliacion> consultarCertificadoAfiliacion(String idAfiliado, String ruta, String nombreArchivo) throws java.lang.Exception {
        List<CertificadoAfiliacion> listaCertificado = new ArrayList();
        
        
        return listaCertificado;
        
    }

}
