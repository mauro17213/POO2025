/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.maestro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaAgrupadorMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.maestro.MaIss2000Tarifario;
import com.saviasaludeps.savia.dominio.maestro.MaIss2001Tarifario;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaPaquete;
import com.saviasaludeps.savia.dominio.maestro.MaPaqueteMipres;
import com.saviasaludeps.savia.dominio.maestro.MaSoatTarifario;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.dominio.mipres.MpCodigoInsumo;
import com.saviasaludeps.savia.ejb.entidades.MaInsumos;
import com.saviasaludeps.savia.ejb.entidades.MaMedicamentos;
import com.saviasaludeps.savia.ejb.entidades.MaPaquetes;
import com.saviasaludeps.savia.ejb.entidades.MaPaquetesMipres;
import com.saviasaludeps.savia.ejb.entidades.MaTecnologias;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.maestro.MaPaqueteRemoto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author jyperez
 */
@Stateless
@Remote(MaPaqueteRemoto.class)
public class MaPaqueteServicio extends GenericoServicio implements MaPaqueteRemoto {
    
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaPaquetes p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigo":
                            strQuery += " AND p.codigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += " AND p.nombre  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "incluye":
                            strQuery += " AND p.incluye LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "excluye":
                            strQuery += " AND p.excluye LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "observacion":
                            strQuery += " AND p.observacion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                        case "maeAmbitoId":
                            strQuery += " AND p.maeAmbitoId = " + e.getValue() + " ";
                            break;
                        case "maeAmbitoCodigo":
                            strQuery += " AND p.maeAmbitoCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeAmbitoValor":
                            strQuery += " AND p.maeAmbitoValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "requisitosTecnicos":
                            strQuery += " AND p.requisitosTecnicos  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "esAltoCosto":
                            strQuery += " AND p.esAtoCosto = " + e.getValue() + " ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += " AND p.fechaHoraCrea  LIKE '" + e.getValue() + "' ";
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
    public List<MaPaquete> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<MaPaquete> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaPaquetes p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigo":
                            strQuery += " AND p.codigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += " AND p.nombre  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "incluye":
                            strQuery += " AND p.incluye LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "excluye":
                            strQuery += " AND p.excluye LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "observacion":
                            strQuery += " AND p.observacion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                        case "maeAmbitoId":
                            strQuery += " AND p.maeAmbitoId = " + e.getValue() + " ";
                            break;
                        case "maeAmbitoCodigo":
                            strQuery += " AND p.maeAmbitoCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeAmbitoValor":
                            strQuery += " AND p.maeAmbitoValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "requisitosTecnicos":
                            strQuery += " AND p.requisitosTecnicos  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "esAltoCosto":
                            strQuery += " AND p.esAtoCosto = " + e.getValue() + " ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += " AND p.fechaHoraCrea  LIKE '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<MaPaquetes> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (MaPaquetes per : list) {
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
    public MaPaquete consultar(int id) throws Exception {
        MaPaquete objRes = null;
        try {
            objRes = castEntidadNegocio((MaPaquetes) getEntityManager().find(MaPaquetes.class, id));
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
    public int insertar(MaPaquete obj) throws Exception {
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
    public void actualizar(MaPaquete obj) throws Exception {
        try {
            //getEntityManager().merge(castNegocioEntidad(obj));
            MaPaquetes paquete = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            //session.update(per);
            String strQuery = "UPDATE MaPaquetes a SET ";
            strQuery += " a.codigo = :codigo ,";
            strQuery += " a.nombre = :nombre ,";
            strQuery += " a.activo = :activo ,";
            strQuery += " a.incluye = :incluye ,";
            strQuery += " a.excluye = :excluye ,";
            strQuery += " a.observacion = :observacion ,";
            strQuery += " a.maeAmbitoId = :maeAmbitoId ,";
            strQuery += " a.maeAmbitoCodigo = :maeAmbitoCodigo ,";
            strQuery += " a.maeAmbitoValor = :maeAmbitoValor ,";
            strQuery += " a.requisitosTecnicos = :requisitosTecnicos ,";
            strQuery += " a.esAtoCosto = :esAtoCosto ,";
            strQuery += " a.tipoTecnologia = :tipoTecnologia ,";
            //campos auditoria
            strQuery += " a.usuarioModifica = :usuarioModifica ,";
            strQuery += " a.terminalModifica = :terminalModifica ,";
            //campo fechas
            //SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaModifica = formatoFechaHora.format(obj.getFechaHoraModifica());
            strQuery += " a.fechaHoraModifica = '" + fechaModifica + "' ";
            //campos objetos
            if (paquete.getMaInsumosId() != null) {
                strQuery += ", a.maInsumosId.id = " + paquete.getMaInsumosId().getId() + " ";
            }
            if (paquete.getMaMedicamentosId()!= null) {
                strQuery += ", a.maMedicamentosId.id = " + paquete.getMaMedicamentosId().getId() + " ";
            }
            if (paquete.getMaTecnologiasId()!= null) {
                strQuery += ", a.maTecnologiasId.id = " + paquete.getMaTecnologiasId().getId() + " ";
            }
            
            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(paquete);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public MaPaquete eliminar(int id) throws Exception {
        MaPaquete obj = null;
        try {
            MaPaquetes ent = getEntityManager().find(MaPaquetes.class, id);
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
    public List<MaPaquete> consultarTodos() throws Exception {
        List<MaPaquete> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaPaquetes p "
                    + "ORDER BY p.id ";
            List<MaPaquetes> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaPaquetes per : list) {
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

    public static MaPaquete castEntidadNegocio(MaPaquetes per) {
        MaPaquete obj = new MaPaquete();
        obj.setId(per.getId());
        obj.setCodigo(per.getCodigo());
        obj.setNombre(per.getNombre());
        obj.setActivo(per.getActivo());
        obj.setIncluye(per.getIncluye());
        obj.setExcluye(per.getExcluye());
        obj.setObservacion(per.getObservacion());
        obj.setMaeAmbitoId(per.getMaeAmbitoId());
        obj.setMaeAmbitoCodigo(per.getMaeAmbitoCodigo());
        obj.setMaeAmbitoValor(per.getMaeAmbitoValor());
        obj.setRequisitosTecnicos(per.getRequisitosTecnicos());
        obj.setEsAltoCosto(per.getEsAtoCosto());
        //2020-12-21 jyperez nuevos campos
        obj.setTipoTecnologia(per.getTipoTecnologia());
        //objetos
        if (per.getMaInsumosId()!= null){
            obj.setMaInsumo(castInsumoEntidadNegocio(per.getMaInsumosId()));
        }
        if (per.getMaMedicamentosId()!= null){
            obj.setMaMedicamento(castMedicamentoEntidadNegocio(per.getMaMedicamentosId()));
        }
        if (per.getMaTecnologiasId()!= null){
            obj.setMaTecnologia(castTecnologiaEntidadNegocio(per.getMaTecnologiasId()));
        }
        if (per.getMaPaquetesMipresList() != null) {
            List<MaPaqueteMipres> list= new ArrayList();
            for (MaPaquetesMipres aux: per.getMaPaquetesMipresList()) {
                MaPaqueteMipres tecmp = castPaqueteMipresEntidadNegocio(aux);
                list.add(tecmp);
            }
            obj.setListaPaqueteMipres(list);
        }
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }
    
    public static MaPaqueteMipres castPaqueteMipresEntidadNegocio(MaPaquetesMipres per) {
        MaPaqueteMipres obj = new MaPaqueteMipres();
        obj.setId(per.getId());
        obj.setCodigoMipres(per.getCodigoMipres());
        obj.setDescripcionMipres(per.getDescripcionMipres());
        //objeto
        if(per.getMaPaquetesId()!= null) {
            obj.setMaPaquete(new MaPaquete(per.getMaPaquetesId().getId()));
        }
        if(per.getMpCodigoInsumosId()!= null) {
            MpCodigoInsumo cod = new MpCodigoInsumo(per.getMpCodigoInsumosId().getId());
            //cod.setMaeInsumoId(per.getInsumosMipresId().getMaeInsumoId());
            cod.setCodigoMipres(per.getMpCodigoInsumosId().getCodigoMipres());
            cod.setDescripcion(per.getMpCodigoInsumosId().getDescripcion());
            cod.setActivo(per.getMpCodigoInsumosId().getActivo());
            cod.setVersionMipres(per.getMpCodigoInsumosId().getVersionMipres());
            //pendiente si necesitamos auditoria pero no se considera
            obj.setMpCodigoInsumo(cod);
        }
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }

    public static MaPaquetes castNegocioEntidad(MaPaquete obj) {
        MaPaquetes per = new MaPaquetes();
        per.setId(obj.getId());
        per.setCodigo(obj.getCodigo());
        per.setNombre(obj.getNombre());
        per.setActivo(obj.isActivo());
        per.setIncluye(obj.getIncluye());
        per.setExcluye(obj.getExcluye());
        per.setObservacion(obj.getObservacion());
        per.setMaeAmbitoId(obj.getMaeAmbitoId());
        per.setMaeAmbitoCodigo(obj.getMaeAmbitoCodigo());
        per.setMaeAmbitoValor(obj.getMaeAmbitoValor());
        per.setRequisitosTecnicos(obj.getRequisitosTecnicos());
        per.setEsAtoCosto(obj.getEsAltoCosto());
        //2020-12-21 jyperez nuevos campos
        per.setTipoTecnologia(obj.getTipoTecnologia());
        //objetos
        if (obj.getMaInsumo() != null){
            per.setMaInsumosId(new MaInsumos(obj.getMaInsumo().getId()));
        }
        if (obj.getMaMedicamento() != null){
            per.setMaMedicamentosId(new MaMedicamentos(obj.getMaMedicamento().getId()));
        }
        if (obj.getMaTecnologia() != null){
            per.setMaTecnologiasId(new MaTecnologias(obj.getMaTecnologia().getId()));
        }
        //auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        return per;
    }
    
    public static MaTecnologia castTecnologiaEntidadNegocio(MaTecnologias per) {
        MaTecnologia obj = new MaTecnologia();
        obj.setId(per.getId());
        obj.setMaeGrupoTecnologiaId(per.getMaeGrupoTecnologiaId());
        obj.setMaeGrupoTecnologiaCodigo(per.getMaeGrupoTecnologiaCodigo());
        obj.setMaeGrupoTecnologiaValor(per.getMaeGrupoTecnologiaValor());
        obj.setGrupoDescripcion(per.getGrupoDescripcion());
        obj.setCups(per.getCups());
        obj.setCupsDescipcion(per.getCupsDescipcion());
        obj.setCodigoPropio(per.getCodigoPropio());
        obj.setPropioDescripcion(per.getPropioDescripcion());
        obj.setAplicaSubsidiado(per.getAplicaSubsidiado());
        obj.setAplicaContributivo(per.getAplicaContributivo());
        obj.setSexoAplica(per.getSexoAplica());
        //obj.setEsPbs(per.getEsPbs());
        obj.setNivelComplejidad(per.getNivelComplejidad());
        obj.setEdadDesde(per.getEdadDesde());
        obj.setEdadHasta(per.getEdadHasta());
        obj.setUnidadDesde(per.getUnidadDesde());
        obj.setUnidadHasta(per.getUnidadHasta());
        obj.setCodigoFinanciador(per.getCodigoFinanciador());
        obj.setFrecuencia(per.getFrecuencia());
        obj.setCobertura(per.getCobertura());
        //2022-03-22 jyperez nuevos campos
        obj.setEventoUnico(per.getEventoUnico());
        obj.setAclaracion(per.getAclaracion());
        obj.setCondicion(per.getCondicion());
        obj.setTipoPago(per.getTipoPago());
        //objetos
        if (per.getMaIss2000TarifariosId() != null) {
            obj.setMaIss2000Tarifario(new MaIss2000Tarifario(per.getMaIss2000TarifariosId().getId()));
        }
        if (per.getMaIss2001TarifariosId() != null) {
            obj.setMaIss2001Tarifario(new MaIss2001Tarifario(per.getMaIss2001TarifariosId().getId()));
        }
        if (per.getMaSoatTarifariosId() != null) {
            obj.setMaSoatTarifario(new MaSoatTarifario(per.getMaSoatTarifariosId().getId()));
        }
        //auditoria
        /*obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());*/
        return obj;
    }
    
    public static MaMedicamento castMedicamentoEntidadNegocio(MaMedicamentos per) {
        MaMedicamento obj = new MaMedicamento();
        obj.setId(per.getId());
        obj.setCum(per.getCum());
        obj.setDescripcionInvima(per.getDescripcionInvima());
        obj.setDescripcionEstandarizada(per.getDescripcionEstandarizada());
        //2021-03-02 jyperez corrección campo Cobertura. Tener en cuenta que cambió
        obj.setMaeCoberturaId(per.getMaeCoberturaId());
        obj.setMaeCoberturaCodigo(per.getMaeCoberturaCodigo());
        obj.setMaeCoberturaValor(per.getMaeCoberturaValor());
        obj.setMaeConcentracionId(per.getMaeConcentracionId());
        obj.setMaeConcentracionCodigo(per.getMaeConcentracionCodigo());
        obj.setMaeConcentracionValor(per.getMaeConcentracionValor());
        obj.setMaePrincipioActivoId(per.getMaePrincipioActivoId());
        obj.setMaePrincipioActivoCodigo(per.getMaePrincipioActivoCodigo());
        obj.setMaePrincipioActivoValor(per.getMaePrincipioActivoValor());
        obj.setMaeFormaFarmaceuticaId(per.getMaeFormaFarmaceuticaId());
        obj.setMaeFormaFarmaceuticaCodigo(per.getMaeFormaFarmaceuticaCodigo());
        obj.setMaeFormaFarmaceuticaValor(per.getMaeFormaFarmaceuticaValor());
        obj.setMaeTipoPpmId(per.getMaeTipoPpmId());
        obj.setMaeTipoPpmCodigo(per.getMaeTipoPpmCodigo());
        obj.setMaeTipoPpmValor(per.getMaeTipoPpmValor());
        obj.setEsAltoCosto(per.getEsAltoCosto());
        obj.setEsCapitado(per.getEsCapitado());
        obj.setAplicaSubsidiado(per.getAplicaSubsidiado());
        obj.setAplicaContributivo(per.getAplicaContributivo());
        obj.setSexoAplica(per.getSexoAplica());
        obj.setCodigoIum(per.getCodigoIum());
        obj.setEdadDesde(per.getEdadDesde());
        obj.setEdadHasta(per.getEdadHasta());
        obj.setEsRegulado(per.getEsRegulado());
        obj.setValorMaximoRegulado(per.getValorMaximoRegulado());
        obj.setValorReferenteMinimo(per.getValorReferenteMinimo());
        obj.setValorReferete(per.getValorReferete());
        //obj.setCodigoFinanciador(per.getCodigoFinanciador());
        //obj.setIdViejo(per.getIdViejo());
        //obj.setCobertura(per.getCobertura());
        if (per.getMaAgrupadoresMedicamentoId() != null) {
            obj.setMaAgrupadorMedicamento(new MaAgrupadorMedicamento(per.getMaAgrupadoresMedicamentoId().getId(),per.getMaAgrupadoresMedicamentoId().getCodigo(),per.getMaAgrupadoresMedicamentoId().getNombre()));
        }
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }
    
    public static MaInsumo castInsumoEntidadNegocio(MaInsumos per) {
        MaInsumo obj = new MaInsumo();
        obj.setId(per.getId());
        obj.setMaeTipoId(per.getMaeTipoId());
        obj.setMaeTipoCodigo(per.getMaeTipoCodigo());
        obj.setMaeTipoValor(per.getMaeTipoValor());
        obj.setCodigo(per.getCodigo());
        obj.setDescripcion(per.getDescripcion());
        obj.setAbreviatura(per.getAbreviatura());
        obj.setActivo(per.getActivo());
        obj.setCobertura(per.getCobertura());
        obj.setAutomatico(per.getAutomatico());
        obj.setCodigoHabilitacion(per.getCodigoHabilitacion());
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }

    @Override
    public MaPaquete consultarPorCodigo(String codigo) throws java.lang.Exception {
        MaPaquete objetoRes = null;
        int i = 0;
        try {
            String strQuery = "FROM MaPaquetes p "
                    + "WHERE p.codigo = '" + codigo + "'";
            List<MaPaquetes> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaPaquetes per : list) {
                if (i == 0) {
                    objetoRes = castEntidadNegocio(per);
                    i++;
                }
            }
                
        } catch (NoResultException e) {
            objetoRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objetoRes;
    }
    
    @Override
    public int consultarCantidadListaBuscador(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaPaquetes p WHERE p.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND p.activo = " + (String) paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigo":
                            strQuery += " AND p.codigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += " AND p.nombre  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "incluye":
                            strQuery += " AND p.incluye LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "excluye":
                            strQuery += " AND p.excluye LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "observacion":
                            strQuery += " AND p.observacion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                        case "maeAmbitoId":
                            strQuery += " AND p.maeAmbitoId = " + e.getValue() + " ";
                            break;
                        case "maeAmbitoCodigo":
                            strQuery += " AND p.maeAmbitoCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeAmbitoValor":
                            strQuery += " AND p.maeAmbitoValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "requisitosTecnicos":
                            strQuery += " AND p.requisitosTecnicos  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "esAltoCosto":
                            strQuery += " AND p.esAtoCosto = " + e.getValue() + " ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += " AND p.fechaHoraCrea  LIKE '" + e.getValue() + "' ";
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
    public List<MaPaquete> consultarListaBuscador(ParamConsulta paramConsulta) throws Exception {
        List<MaPaquete> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaPaquetes p WHERE p.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND p.activo = " + (String) paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigo":
                            strQuery += " AND p.codigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += " AND p.nombre  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "incluye":
                            strQuery += " AND p.incluye LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "excluye":
                            strQuery += " AND p.excluye LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "observacion":
                            strQuery += " AND p.observacion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                        case "maeAmbitoId":
                            strQuery += " AND p.maeAmbitoId = " + e.getValue() + " ";
                            break;
                        case "maeAmbitoCodigo":
                            strQuery += " AND p.maeAmbitoCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeAmbitoValor":
                            strQuery += " AND p.maeAmbitoValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "requisitosTecnicos":
                            strQuery += " AND p.requisitosTecnicos  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "esAltoCosto":
                            strQuery += " AND p.esAtoCosto = " + e.getValue() + " ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += " AND p.fechaHoraCrea  LIKE '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<MaPaquetes> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (MaPaquetes per : list) {
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
    public HashMap<String, MaPaquete> consultarHash() throws Exception {
        HashMap<String,MaPaquete> hashResult = new HashMap();
        try {
            String strQuery = "FROM MaServiciosHabilitacion m "
                    + "ORDER BY m.id ";
            List<MaPaquetes> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaPaquetes per : list) {
                MaPaquete obj = castEntidadNegocio(per);
                hashResult.put(obj.getCodigo(), obj);
            }
        } catch (NoResultException e) {
            hashResult = new HashMap();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return hashResult;
    }
    
    @Override
    public List<MaPaquete> consultarTodoSingleton() throws Exception {
        List<MaPaquete> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t.id, "
                    + "t.activo, "
                    + "t.codigo, "
                    + "t.nombre "
                    + "FROM MaPaquetes t ORDER BY t.id ";
            Query q = getEntityManager().createQuery(strQuery);
            List<Object[]> lista = q.getResultList();
            for (Object[] per : lista) {
                MaPaquete paquete = new MaPaquete();
                paquete.setId((Integer) per[0]);
                paquete.setActivo((Boolean) per[1]);
                paquete.setCodigo(per[2].toString());
                paquete.setNombre(per[3].toString());
                listResult.add(paquete);
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
}
