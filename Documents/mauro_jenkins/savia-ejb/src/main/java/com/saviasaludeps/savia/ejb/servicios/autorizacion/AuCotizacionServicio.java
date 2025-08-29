/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.anticipo.AntAnticipo;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoItem;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacion;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos3;
import com.saviasaludeps.savia.ejb.entidades.AuCotizacionItems;
import com.saviasaludeps.savia.ejb.entidades.AuCotizaciones;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.MaSoatTarifarioValores;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuCotizacionRemoto;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

@Stateless
@Remote(AuCotizacionRemoto.class)
public class AuCotizacionServicio extends GenericoServicio implements AuCotizacionRemoto {

    @Override
    public AuCotizacion consultar(int id) throws Exception {
        AuCotizacion objRes = null;
        try {
            objRes = castEntidadNegocio((AuCotizaciones) getEntityManager().find(AuCotizaciones.class, id));
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
    public AuCotizacion consultarUltimoPorTipoYCodigo(int tipo, String codigoTecnologia) throws Exception {
        AuCotizacion objRes = null;
        try {
            String strQuery = "SELECT c FROM AuCotizaciones c "
                    + "WHERE c.tipoTecnologia = :tipo_tecnologia "
                    + "AND c.maTecnologiaCodigo = :codigo_tecnologia "
                    + "ORDER by c.id DESC";
            Query query = getEntityManager().createQuery(strQuery);
            query.setMaxResults(1);
            query.setParameter("tipo_tecnologia", tipo);
            query.setParameter("codigo_tecnologia", codigoTecnologia);
            objRes = castEntidadNegocio((AuCotizaciones) query.getSingleResult());
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
    public int insertar(AuCotizacion obj) throws Exception {
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
    public void actualizar(AuCotizacion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuCotizaciones a SET ";
            strQuery += "a.activo = :activo,";
            strQuery += "a.usuarioModifica = :usuario_modifica,";
            strQuery += "a.terminalModifica = :terminal_modifica,";
            strQuery += "a.fechaHoraModifica = :fecha_hora_modifica";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("activo", obj.getActivo());
            query.setParameter("usuarioCrea", obj.getUsuarioCrea());
            query.setParameter("terminalCrea", obj.getTerminalCrea());
            query.setParameter("fechaHoraCrea", obj.getFechaHoraCrea());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    private static AuCotizacion castEntidadNegocio(AuCotizaciones ent) {
        AuCotizacion negocio = new AuCotizacion();
        negocio.setId(ent.getId());
        CntPrestadorSede sede = new CntPrestadorSede();
        sede.setId(ent.getCntPrestadorSedesId().getId());
        negocio.setCntPrestadorSede(sede);
        negocio.setTipoTecnologia(ent.getTipoTecnologia());
        negocio.setMaTecnologiaId(ent.getMaTecnologiaId());
        negocio.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        negocio.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        negocio.setMaMedicamentoId(ent.getMaMedicamentoId());
        negocio.setMaMedicamentoCodigo(ent.getMaMedicamentoCodigo());
        negocio.setMaMedicamentoValor(ent.getMaMedicamentoValor());
        negocio.setPorcentajeNegociacion(ent.getPorcentajeNegociacion());
        negocio.setValorTecnologia(ent.getValorTecnologia());
        negocio.setFechaInicioVigencia(ent.getFechaInicioVigencia());
        negocio.setFechaFinVigencia(ent.getFechaFinVigencia());
        negocio.setObservacion(ent.getObservacion());
        negocio.setMaTarifarioId(ent.getMaTarifarioId());
        negocio.setMaTarifarioCodigo(ent.getMaTarifarioCodigo());
        negocio.setMaTarifarioValor(ent.getMaTarifarioValor());
        negocio.setActivo(ent.getActivo());
        negocio.setFuenteOrigen(ent.getFuenteOrigen());
        //2023-09-28 jyperez nuevo campo
        if (ent.getPagoAnticipado() != null) {
            negocio.setPagoAnticipado(ent.getPagoAnticipado());
        } else {
            negocio.setPagoAnticipado(false);
        }
        if (ent.getAuSolicitudAdjuntosList() != null) {
            negocio.setAuSolicitudAdjuntosList(AuSolicitudAdjuntoServicio.casteoListaEntidadNegocio(ent.getAuSolicitudAdjuntosList()));
        }
        //-062024-17 pvacca nuevos campos 
        if(ent.getMpPrescripcionId() != null){
            negocio.setMpPrescripcionId(new MpPrescripcion(ent.getMpPrescripcionId().getId()));
        }
        if(ent.getAsegAfiliadosId() != null){
            negocio.setAsegAfiliadosId(new AsegAfiliado (ent.getAsegAfiliadosId().getId()));
        }
        if(ent.getAuAnexos3Id() != null){
            negocio.setAuAnexo3Id(new AuAnexo3(ent.getAuAnexos3Id().getId()));
        }
        negocio.setFuenteOrigen(ent.getFuenteOrigen());
        negocio.setMpNumeroPrescripcion(ent.getMpNumeroPrescripcion());
        if(ent.getAntAnticiposId() != null){
            negocio.setAntAnticiposId(new AntAnticipo(ent.getAntAnticiposId()));
        }
        if(ent.getAntAnticipoItemsId()!= null){
            negocio.setAntAnticipoItemsId(new AntAnticipoItem(ent.getAntAnticipoItemsId()));
        }
        //Auditoria
        negocio.setUsuarioCrea(ent.getUsuarioCrea());
        negocio.setFechaHoraCrea(ent.getFechaHoraCrea());
        negocio.setTerminalCrea(ent.getTerminalCrea());
        return negocio;
    }

    private static AuCotizaciones castNegocioEntidad(AuCotizacion negocio) {
        AuCotizaciones entidad = new AuCotizaciones();
        entidad.setCntPrestadorSedesId(new CntPrestadorSedes(negocio.getCntPrestadorSede().getId()));
        entidad.setActivo(negocio.getActivo());
        entidad.setTipoTecnologia(negocio.getTipoTecnologia());
         entidad.setTipoTecnologiaMipres(negocio.getTipoTecnologiaMipres());
        entidad.setMaTecnologiaCodigo(negocio.getMaTecnologiaCodigo());
        entidad.setMaTecnologiaValor(negocio.getMaTecnologiaValor());
        entidad.setMaTecnologiaId(negocio.getMaTecnologiaId());
        entidad.setMaMedicamentoId(negocio.getMaMedicamentoId());
        entidad.setMaMedicamentoCodigo(negocio.getMaMedicamentoCodigo());
        entidad.setMaMedicamentoValor(negocio.getMaMedicamentoValor());
        entidad.setPorcentajeNegociacion((negocio.getPorcentajeNegociacion() != null)? negocio.getPorcentajeNegociacion(): new BigDecimal(0));
        entidad.setValorTecnologia(negocio.getValorTecnologia());
        entidad.setFechaInicioVigencia(negocio.getFechaInicioVigencia());
        entidad.setFechaFinVigencia(negocio.getFechaFinVigencia());
        entidad.setObservacion(negocio.getObservacion());
        entidad.setTipoTarifa(negocio.getTipoTarifa());
        entidad.setFuenteOrigen(negocio.getFuenteOrigen());
        //2023-09-28 jyperez nuevo campo
        entidad.setPagoAnticipado(negocio.isPagoAnticipado());
        if (negocio.getMaTarifarioId() != null) {
            entidad.setMaTarifarioId(negocio.getMaTarifarioId());
            entidad.setMaTarifarioCodigo(negocio.getMaTarifarioCodigo());
            entidad.setMaTarifarioValor(negocio.getMaTarifarioValor());
        }
        //-062024-17 pvacca nuevos campos 
        if(negocio.getMpPrescripcionId() != null){
            entidad.setMpPrescripcionId(new MpPrescripciones(negocio.getMpPrescripcionId().getId()));
        }
        if(negocio.getAsegAfiliadosId() != null){
            entidad.setAsegAfiliadosId(new AsegAfiliados (negocio.getAsegAfiliadosId().getId()));
        }
        if(negocio.getAuAnexo3Id() != null){
            entidad.setAuAnexos3Id(new AuAnexos3(negocio.getAuAnexo3Id().getId()));
        }
        if(negocio.getAntAnticiposId() != null){
            entidad.setAntAnticiposId(negocio.getAntAnticiposId().getId());
        }
        if(negocio.getAntAnticipoItemsId() != null){
            entidad.setAntAnticipoItemsId(negocio.getAntAnticipoItemsId().getId());
        }
        entidad.setFuenteOrigen(negocio.getFuenteOrigen());
        entidad.setMpNumeroPrescripcion(negocio.getMpNumeroPrescripcion());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(a) FROM AuCotizaciones a "
                    + "WHERE a.activo = 1 AND current_date() <= a.fechaFinVigencia ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND a.tipoTecnologia = " + paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND a.maTecnologiaId = " + paramConsulta.getParametroConsulta2();
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntPrestadorSede.codigoHabilitacionSede":
                            strQuery += "AND a.cntPrestadorSedesId.codigoHabilitacion = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestadorSede.cntPrestador.razonSocial":
                            strQuery += "AND a.cntPrestadorSedeId.cntPrestadorId.razonSocial = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntPrestadorSede.nombreSede":
                            strQuery += "AND a.cntPrestadorSedeId.nombre = '" + (String) e.getValue() + "' ";
                            break;
                    }
                }
            }
            javax.persistence.Query query = getEntityManager().createQuery(strQuery);
            cant = (int) (long) query.getSingleResult();
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
    public List<AuCotizacion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuCotizacion> listResult = new ArrayList();
        try {
            String strQuery = "SELECT a FROM AuCotizaciones a "
                    + "WHERE a.activo = 1 AND current_date() <= a.fechaFinVigencia ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND a.tipoTecnologia = " + paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND a.maTecnologiaId = " + paramConsulta.getParametroConsulta2();
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntPrestadorSede.codigoHabilitacionSede":
                            strQuery += "AND a.cntPrestadorSedesId.codigoHabilitacion = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestadorSede.cntPrestador.razonSocial":
                            strQuery += "AND a.cntPrestadorSedeId.cntPrestadorId.razonSocial = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntPrestadorSede.nombreSede":
                            strQuery += "AND a.cntPrestadorSedeId.nombre = '" + (String) e.getValue() + "' ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    default:
                        strQuery += "a." + paramConsulta.getOrden() + " "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                }
            } else {
                strQuery += "a.id DESC";
            }
            List<AuCotizaciones> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuCotizaciones per : list) {
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
    public AuCotizacion consultarPorIdItemAnexo3(int idItemAnexo3) throws Exception {
        AuCotizacion objRes = null;
        try {
            String strQuery = "SELECT c FROM AuCotizacionItems c "
                    + "WHERE c.auAnexo3ItemsId.id = :itemId "
                    + "ORDER by c.id DESC";
            Query query = getEntityManager().createQuery(strQuery);
            query.setMaxResults(1);
            query.setParameter("itemId", idItemAnexo3);
            AuCotizacionItems item = (AuCotizacionItems) query.getSingleResult();
            if (item != null) {
                objRes = castEntidadNegocio(item.getAuCotizacionesId());
            }
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
    public AuCotizacion consultarPorIdAnexo3(int idAnexo3) throws Exception {
        AuCotizacion objRes = null;
        try {
            String strQuery = "SELECT c FROM AuCotizaciones c "
                    + "WHERE c.auAnexos3Id.id = :idAnexo3 "
                    + "ORDER by c.id DESC";
            Query query = getEntityManager().createQuery(strQuery);
            query.setMaxResults(1);
            query.setParameter("idAnexo3", idAnexo3);
            AuCotizaciones item = (AuCotizaciones) query.getSingleResult();
            if (item != null) {
                objRes = castEntidadNegocio(item);
            }
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
    public BigDecimal consultarValorSoat(int idTarifario) throws Exception {
        BigDecimal valor = new BigDecimal(0);
        try {
            Date fecha = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy");
            String strQuery = "SELECT c FROM MaSoatTarifarioValores c "
                    + "WHERE c.agno = " + formato.format(fecha) + " "
                    + " AND c.maSoatTarifariosId.id = " + idTarifario + " "
                    + "ORDER by c.id DESC";
            List<MaSoatTarifarioValores> lista = getEntityManager().createQuery(strQuery).getResultList();
            if (!lista.isEmpty()) {
                valor = lista.get(0).getValor();
            }
        } catch (NoResultException e) {
            valor = new BigDecimal(0);
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return valor;
    }

}
