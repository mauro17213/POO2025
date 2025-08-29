package com.saviasaludeps.savia.ejb.servicios.contratacion;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoCobertura;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoSede;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.ejb.entidades.CntContratoSedes;
import com.saviasaludeps.savia.ejb.entidades.CntContratos;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.CntContratoCoberturas;
import javax.persistence.Query;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoSedeRemoto;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.hibernate.Session;

@Stateless
@Remote(CntContratoSedeRemoto.class)
public class CntContratoSedeServicio extends GenericoServicio implements CntContratoSedeRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntContratoSedes c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "complejidad":
                            strQuery += "AND c.complejidad = " + (Integer) e.getValue() + " ";
                            break;
                        case "numAfiliados":
                            strQuery += "AND c.numAfiliados = " + (String) e.getValue() + " ";
                            break;
                        case "maeModalidadContratoId":
                            strQuery += "AND c.maeModalidadContratoId = " + e.getValue() + " ";
                            break;
                        case "maeModalidadContratoCodigo":
                            strQuery += "AND c.maeModalidadContratoCodigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeModalidadContratoValor":
                            strQuery += "AND c.maeModalidadContratoValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nivelAtencion":
                            strQuery += "AND c.nivelAtencion = " + e.getValue() + " ";
                            break;
                        case "fechaInicio":
                            strQuery += "AND c.fechaInicio = '" + e.getValue() + "' ";
                            break;
                        case "fechaFin":
                            strQuery += "AND c.fechaFin = '" + e.getValue() + "' ";
                            break;
                        case "valorUpcAfiliado":
                            strQuery += "AND c.valorUpcAfiliado = " + e.getValue() + " ";
                            break;
                        case "valorContrato":
                            strQuery += "AND c.valorContrato = " + e.getValue() + " ";
                            break;
                        case "observacion":
                            strQuery += "AND c.nivelAtencion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "aplicaSubsidiado":
                            strQuery += "AND c.aplicaSubsidiado = " + e.getValue() + " ";
                            break;
                        case "aplicaContribuitivo":
                            strQuery += "AND c.aplicaContribuitivo = " + e.getValue() + " ";
                            break;
                        //campos contratos
                        case "cntContrato.cntPrestador.maeTipoDocumentoCodigo":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.maeTipoDocumentoCodigo = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntContrato.cntPrestador.maeTipoDocumentoValor":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.maeTipoDocumentoValor = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntContrato.cntPrestador.numeroDocumento":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.numeroDocumento = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntContrato.cntPrestador.digitoVerificacion":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.digitoVerificacion = " + e.getValue() + " ";
                            break;
                        case "cntContrato.cntPrestador.razonSocial":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntContrato.cntPrestador.codigoMinSalud":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.codigoMinSalud = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.cntPrestador.nivelAtencion":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.nivelAtencion = " + e.getValue() + " ";
                            break;
                        case "cntContrato.maeEstadoContratoId":
                            strQuery += "AND c.cntContratosId.maeEstadoContratoId = " + e.getValue() + " ";
                            break;
                        case "cntContrato.maeEstadoContratoCodigo":
                            strQuery += "AND c.cntContratosId.maeEstadoContratoCodigo = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.maeEstadoContratoValor":
                            strQuery += "AND c.cntContratosId.maeEstadoContratoValor = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.fechaInicio":
                            strQuery += "AND c.cntContratosId.fechaInicio = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.fechaFin":
                            strQuery += "AND c.cntContratosId.fechaFin = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.contrato":
                            strQuery += "AND c.cntContratosId.contrato = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestadorSede.cntPrestador.id":
                            strQuery += "AND c.cntPrestadorSedesId.cntPrestadoresId.id = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += "AND c.cntContratosId.activo = " + (Boolean) e.getValue() + " ";
                            break;
                        case "cntPrestadorSede.codigoHabilitacionSede":
                            strQuery += "AND c.cntPrestadorSedesId.codigoHabilitacion = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestadorSede.nombreSede":
                            strQuery += "AND c.cntPrestadorSedesId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            //RANGO DE FECHA DE CONTRATO
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND fechaInicio >= :fh_inicio ";
            } else if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND fechaFin <= :fh_fin ";
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                LocalDate localFecha1 = (LocalDate) paramConsulta.getParametroConsulta1();
                Date dateFecha1 = Date.from(localFecha1.atStartOfDay(ZoneId.systemDefault()).toInstant());
                query.setParameter("fh_inicio", (dateFecha1));
                LocalDate localFecha2 = (LocalDate) paramConsulta.getParametroConsulta2();
                Date dateFecha2 = Date.from(localFecha2.atStartOfDay(ZoneId.systemDefault()).toInstant());
                query.setParameter("fh_fin", (dateFecha2));
            } else if (paramConsulta.getParametroConsulta1() != null) {
                LocalDate localFecha1 = (LocalDate) paramConsulta.getParametroConsulta1();
                Date dateFecha1 = Date.from(localFecha1.atStartOfDay(ZoneId.systemDefault()).toInstant());
                query.setParameter("fh_inicio", (dateFecha1));
            } else if (paramConsulta.getParametroConsulta2() != null) {
                LocalDate localFecha2 = (LocalDate) paramConsulta.getParametroConsulta2();
                Date dateFecha2 = Date.from(localFecha2.atStartOfDay(ZoneId.systemDefault()).toInstant());
                query.setParameter("fh_fin", (dateFecha2));
            }
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
    public List<CntContratoSede> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CntContratoSede> listResult = new ArrayList();
        try {
            String strQuery = "SELECT c FROM CntContratoSedes c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "complejidad":
                            strQuery += "AND c.complejidad = " + (Integer) e.getValue() + " ";
                            break;
                        case "numAfiliados":
                            strQuery += "AND c.numAfiliados = " + (String) e.getValue() + " ";
                            break;
                        case "maeModalidadContratoId":
                            strQuery += "AND c.maeModalidadContratoId = " + e.getValue() + " ";
                            break;
                        case "maeModalidadContratoCodigo":
                            strQuery += "AND c.maeModalidadContratoCodigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeModalidadContratoValor":
                            strQuery += "AND c.maeModalidadContratoValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nivelAtencion":
                            strQuery += "AND c.nivelAtencion = " + e.getValue() + " ";
                            break;
                        case "fechaInicio":
                            strQuery += "AND c.fechaInicio = '" + e.getValue() + "' ";
                            break;
                        case "fechaFin":
                            strQuery += "AND c.fechaFin = '" + e.getValue() + "' ";
                            break;
                        case "valorUpcAfiliado":
                            strQuery += "AND c.valorUpcAfiliado = " + e.getValue() + " ";
                            break;
                        case "valorContrato":
                            strQuery += "AND c.valorContrato = " + e.getValue() + " ";
                            break;
                        case "observacion":
                            strQuery += "AND c.nivelAtencion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "aplicaSubsidiado":
                            strQuery += "AND c.aplicaSubsidiado = " + e.getValue() + " ";
                            break;
                        case "aplicaContribuitivo":
                            strQuery += "AND c.aplicaContribuitivo = " + e.getValue() + " ";
                            break;
                        //campos contratos
                        case "cntContrato.cntPrestador.maeTipoDocumentoCodigo":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.maeTipoDocumentoCodigo = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntContrato.cntPrestador.maeTipoDocumentoValor":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.maeTipoDocumentoValor = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntContrato.cntPrestador.numeroDocumento":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.numeroDocumento = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntContrato.cntPrestador.digitoVerificacion":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.digitoVerificacion = " + e.getValue() + " ";
                            break;
                        case "cntContrato.cntPrestador.razonSocial":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntContrato.cntPrestador.codigoMinSalud":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.codigoMinSalud = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.cntPrestador.nivelAtencion":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.nivelAtencion = " + e.getValue() + " ";
                            break;
                        case "cntContrato.maeEstadoContratoId":
                            strQuery += "AND c.cntContratosId.maeEstadoContratoId = " + e.getValue() + " ";
                            break;
                        case "cntContrato.maeEstadoContratoCodigo":
                            strQuery += "AND c.cntContratosId.maeEstadoContratoCodigo = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.maeEstadoContratoValor":
                            strQuery += "AND c.cntContratosId.maeEstadoContratoValor = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.fechaInicio":
                            strQuery += "AND c.cntContratosId.fechaInicio = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.fechaFin":
                            strQuery += "AND c.cntContratosId.fechaFin = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.contrato":
                            strQuery += "AND c.cntContratosId.contrato = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestadorSede.cntPrestador.id":
                            strQuery += "AND c.cntPrestadorSedesId.cntPrestadoresId.id = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += "AND c.cntContratosId.activo = " + (Boolean) e.getValue() + " ";
                            break;
                        case "cntPrestadorSede.codigoHabilitacionSede":
                            strQuery += "AND c.cntPrestadorSedesId.codigoHabilitacion = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestadorSede.nombreSede":
                            strQuery += "AND c.cntPrestadorSedesId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            //RANGO DE FECHA DE CONTRATO
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND fechaInicio >= :fh_inicio ";
            } else if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND fechaFin <= :fh_fin ";
            }
            //ordenamiento
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    case "cntContrato.contrato":
                        strQuery += "c.cntContratosId.contrato "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntContrato.cntPrestador.numeroDocumento":
                        strQuery += "c.cntContratosId.cntPrestadoresId.numeroDocumento "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntContrato.cntPrestador.digitoVerificacion":
                        strQuery += "c.cntContratosId.cntPrestadoresId.digitoVerificacion "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntContrato.cntPrestador.razonSocial":
                        strQuery += "c.cntContratosId.cntPrestadoresId.razonSocial "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntContrato.cntPrestador.codigoMinSalud":
                        strQuery += "c.cntContratosId.cntPrestadoresId.codigoMinSalud "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntContrato.fechaInicio":
                        strQuery += "c.cntContratosId.fechaInicio "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntContrato.fechaFin":
                        strQuery += "c.cntContratosId.fechaFin "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntContrato.cntPrestador.nivelAtencion":
                        strQuery += "c.cntContratosId.cntPrestadoresId.nivelAtencion "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntContrato.maeEstadoContratoId":
                        strQuery += "c.cntContratosId.maeEstadoContratoId "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntPrestadorSede.codigoHabilitacionSede":
                        strQuery += "c.cntPrestadorSedesId.codigoHabilitacion "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntPrestadorSede.nombreSede":
                        strQuery += "c.cntPrestadorSedesId.nombre "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    default:
                        strQuery += "c." + paramConsulta.getOrden() + " "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                }

            } else {
                strQuery += "c.cntContratosId.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                LocalDate localFecha1 = (LocalDate) paramConsulta.getParametroConsulta1();
                Date dateFecha1 = Date.from(localFecha1.atStartOfDay(ZoneId.systemDefault()).toInstant());
                query.setParameter("fh_inicio", (dateFecha1));
                LocalDate localFecha2 = (LocalDate) paramConsulta.getParametroConsulta2();
                Date dateFecha2 = Date.from(localFecha2.atStartOfDay(ZoneId.systemDefault()).toInstant());
                query.setParameter("fh_fin", (dateFecha2));
            } else if (paramConsulta.getParametroConsulta1() != null) {
                LocalDate localFecha1 = (LocalDate) paramConsulta.getParametroConsulta1();
                Date dateFecha1 = Date.from(localFecha1.atStartOfDay(ZoneId.systemDefault()).toInstant());
                query.setParameter("fh_inicio", (dateFecha1));
            } else if (paramConsulta.getParametroConsulta2() != null) {
                LocalDate localFecha2 = (LocalDate) paramConsulta.getParametroConsulta2();
                Date dateFecha2 = Date.from(localFecha2.atStartOfDay(ZoneId.systemDefault()).toInstant());
                query.setParameter("fh_fin", (dateFecha2));
            }
            List<CntContratoSedes> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntContratoSedes per : list) {
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

    public CntContratoSede castEntidadNegocio(CntContratoSedes per) {
        CntContratoSede neg = new CntContratoSede();
        neg.setId(per.getId());
        neg.setComplejidad(per.getComplejidad());
        neg.setNumAfiliados(per.getNumAfiliados());
        neg.setMaeModalidadContratoId(per.getMaeModalidadContratoId());
        neg.setMaeModalidadContratoCodigo(per.getMaeModalidadContratoCodigo());
        neg.setMaeModalidadContratoValor(per.getMaeModalidadContratoValor());
        neg.setNivelAtencion(per.getNivelAtencion());
        neg.setFechaInicio(per.getFechaInicio());
        neg.setFechaFin(per.getFechaFin());
        neg.setValorUpcAfiliado(per.getValorUpcAfiliado());
        neg.setValorContrato(per.getValorContrato());
        neg.setObservacion(per.getObservacion());
        neg.setAplicaSubsidiado(per.getAplicaSubsidiado());
        neg.setAplicaContribuitivo(per.getAplicaContribuitivo());
        neg.setAplicaPac(per.getAplicaPac());
        neg.setAplicaGlosaExtemporanea(per.getAplicaGlosaExtemporanea());
        neg.setAplicaAuditoria(per.getAplicaAuditoria());
        neg.setAplicaPortabilidad(per.getAplicaPortabilidad());
        neg.setAplicaAgendamiento(per.getAplicaAgendamiento());
        neg.setAplicaAutorizacion(per.getAplicaAutorizacion());
        neg.setAplicaRecaudoCopagosIps(per.getAplicaRecaudoCopagosIps());
        //objetos
        if (per.getCntContratosId() != null) {
            neg.setCntContrato(castContratoEntidadNegocio(per.getCntContratosId()));
        }
        if (per.getCntPrestadorSedesId() != null) {
            neg.setCntPrestadorSede(castPrestadorSedesEntidadNegocio(per.getCntPrestadorSedesId()));
        }
        if (per.getCntContratoCoberturasList() != null) {
            neg.setListaCntContratoCobertura(new ArrayList());
            for (CntContratoCoberturas cobertura : per.getCntContratoCoberturasList()) {
                neg.getListaCntContratoCobertura().add(castContratoCoberturaEntidadNegocio(cobertura));
            }
        }
        //auditoria
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        neg.setUsuarioModifica(per.getUsuarioModifica());
        neg.setTerminalModifica(per.getTerminalModifica());
        neg.setFechaHoraModifica(per.getFechaHoraModifica());
        return neg;
    }

    public CntPrestadorSede castPrestadorSedesEntidadNegocio(CntPrestadorSedes per) {
        CntPrestadorSede obj = new CntPrestadorSede();
        obj.setId(per.getId());
        obj.setCodigoPrestador(per.getCodigoPrestador());
        obj.setUbicacionId(per.getUbicacionId());
        obj.setMaeRegionId(per.getMaeRegionId());
        obj.setMaeRegionCodigo(per.getMaeRegionCodigo());
        obj.setMaeRegionValor(per.getMaeRegionValor());
        obj.setDireccion(per.getDireccion());
        obj.setNombreSede(per.getNombre());
        obj.setCodigoSede(per.getCodigo());
        obj.setCodigoHabilitacionSede(per.getCodigoHabilitacion());
        obj.setZonaPrecedencia(per.getZonaPrecedencia());
        obj.setEstadoSede(per.getEstadoSede());
        obj.setNivelAtencion(per.getNivelAtencion());
        obj.setClasePrestador(per.getMaeClasePrestadorId());
        obj.setMaeClasePrestadorCodigo(per.getMaeClasePrestadorCodigo());
        obj.setMaeClasePrestadorValor(per.getMaeClasePrestadorValor());
        obj.setFax(per.getFax());
        obj.setTelefonoCitas(per.getTelefonoCitas());
        obj.setCorreoElectronico(per.getCorreoElectronico());
        obj.setTelefonoAdministrativo(per.getTelefonoAdministrativo());
        obj.setCapitacion(per.getCapitacion());
        if (per.getCntPrestadoresId() != null) {
            obj.setCntPrestador(new CntPrestador(per.getCntPrestadoresId().getId(), per.getCntPrestadoresId().getRazonSocial()));
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

    public CntContrato castContratoEntidadNegocio(CntContratos per) {
        CntContrato neg = new CntContrato();
        neg.setId(per.getId());
        neg.setNegociacion(per.getNegociacion());
        neg.setContrato(per.getContrato());
        neg.setDescripcion(per.getDescripcion());
        neg.setActivo(per.getActivo());
        neg.setMaeEstadoContratoId(per.getMaeEstadoContratoId());
        neg.setMaeEstadoContratoCodigo(per.getMaeEstadoContratoCodigo());
        neg.setMaeEstadoContratoValor(per.getMaeEstadoContratoValor());
        neg.setFechaInicio(per.getFechaInicio());
        neg.setFechaFin(per.getFechaFin());
        neg.setValor(per.getValor());
        neg.setValorMes(per.getValorMes());
        neg.setValorPresupuestoTotal(per.getValorPresupuestoTotal());
        neg.setDiasLimitePago(per.getDiasLimitePago());
        //2023-06-21 jyperez nuevo requerimiento
        //2025-05-13 jyperez cambio BD a campo booleano
        neg.setAutorizaGestion(per.getAutorizaGestion());
        //objetos
        if (per.getCntPrestadoresId() != null) {
            neg.setCntPrestador(castCntPrestadorEntidadNegocio(per.getCntPrestadoresId()));
        }
        //auditoria
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        neg.setUsuarioModifica(per.getUsuarioModifica());
        neg.setTerminalModifica(per.getTerminalModifica());
        neg.setFechaHoraModifica(per.getFechaHoraModifica());
        return neg;
    }

    public CntPrestador castCntPrestadorEntidadNegocio(CntPrestadores per) {
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

    public CntContratoCobertura castContratoCoberturaEntidadNegocio(CntContratoCoberturas per) {
        CntContratoCobertura neg = new CntContratoCobertura();
        neg.setId(per.getId());
        neg.setActivo(per.getActivo());
        //objetos
        if (per.getCntContratosId() != null) {
            neg.setCntContrato(new CntContrato(per.getCntContratosId().getId()));
        }
        if (per.getCntContratoSedesId() != null) {
            neg.setCntContratoSede(new CntContratoSede(per.getCntContratoSedesId().getId()));
        }
        if (per.getCntPrestadorSedesId() != null) {
            neg.setCntPrestadorSede(new CntPrestadorSede(per.getCntPrestadorSedesId().getId()));
        }
        if (per.getGnUbicacionesId() != null) {
            neg.setUbicacion(new Ubicacion(null, per.getGnUbicacionesId().getId(), per.getGnUbicacionesId().getTipo(), "", per.getGnUbicacionesId().getNombre()));
        }
        //auditoria
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        neg.setUsuarioModifica(per.getUsuarioModifica());
        neg.setTerminalModifica(per.getTerminalModifica());
        neg.setUsuarioModifica(per.getUsuarioModifica());
        return neg;
    }

    @Override
    public CntContratoSede consultar(int id) throws Exception {
        CntContratoSede objRes = null;
        try {
            objRes = castEntidadNegocio((CntContratoSedes) getEntityManager().find(CntContratoSedes.class, id));
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
    public int insertar(CntContratoSede obj) throws Exception {
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
    public void actualizar(CntContratoSede obj) throws Exception {
        try {
            CntContratoSedes contratoSede = castNegocioEntidad(obj);
            //getEntityManager().merge(castNegocioEntidad(obj));
            Session session = getEntityManager().unwrap(Session.class);
            //session.update(per);
            String strQuery = "UPDATE CntContratoSedes a SET ";
            strQuery += " a.complejidad = :complejidad ,";
            strQuery += " a.numAfiliados = :numAfiliados ,";
            strQuery += " a.maeModalidadContratoId = :maeModalidadContratoId ,";
            strQuery += " a.maeModalidadContratoCodigo = :maeModalidadContratoCodigo ,";
            strQuery += " a.maeModalidadContratoValor = :maeModalidadContratoValor ,";
            strQuery += " a.nivelAtencion = :nivelAtencion ,";
            strQuery += " a.valorUpcAfiliado = :valorUpcAfiliado ,";
            strQuery += " a.valorContrato = :valorContrato ,";
            strQuery += " a.observacion = :observacion ,";
            strQuery += " a.aplicaSubsidiado = :aplicaSubsidiado ,";
            strQuery += " a.aplicaContribuitivo = :aplicaContribuitivo ,";
            strQuery += " a.aplicaPac = :aplicaPac ,";
            strQuery += " a.aplicaGlosaExtemporanea = :aplicaGlosaExtemporanea ,";
            strQuery += " a.aplicaAuditoria = :aplicaAuditoria ,";
            strQuery += " a.aplicaPortabilidad = :aplicaPortabilidad ,";
            strQuery += " a.aplicaAgendamiento = :aplicaAgendamiento ,";
            strQuery += " a.aplicaAutorizacion = :aplicaAutorizacion ,";
            strQuery += " a.aplicaRecaudoCopagosIps = :aplicaRecaudoCopagosIps ,";

            //campo fechas
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            String fechaInicio = formatoFecha.format(obj.getFechaInicio());
            strQuery += " a.fechaInicio = '" + fechaInicio + "', ";
            String fechaFin = formatoFecha.format(obj.getFechaFin());
            strQuery += " a.fechaFin = '" + fechaFin + "', ";

            strQuery += " a.usuarioModifica = :usuarioModifica ,";
            strQuery += " a.fechaHoraModifica = :fechaHoraModifica ,";
            strQuery += " a.terminalModifica = :terminalModifica ";
            //campos objetos
            // campos objetos 
            if (contratoSede.getCntPrestadorSedesId() != null) {
                strQuery += ", a.cntPrestadorSedesId.id = " + contratoSede.getCntPrestadorSedesId().getId() + " ";
            }
            if (contratoSede.getCntContratosId() != null) {
                strQuery += ", a.cntContratosId.id = " + contratoSede.getCntContratosId().getId() + " ";
            }
            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(contratoSede);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CntContratoSede eliminar(int id) throws Exception {
        CntContratoSede obj = null;
        try {
            CntContratoSedes ent = getEntityManager().find(CntContratoSedes.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            throw new Exception("No se encontro alguna sede que se intenta borrar");
        } catch (Exception e) {
            if (obj != null && obj.getCntPrestadorSede() != null) {
                throw new Exception("La sede '" + obj.getCntPrestadorSede().getNombreSede() + "' que se intenta borrar tiene dependencias");
            } else {
                throw new Exception("Una sede que se intenta borrar tiene dependencias");
            }
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public List<CntContratoSede> consultarTodos() throws Exception {
        List<CntContratoSede> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntContratoSedes p "
                    + "ORDER BY p.id ";
            List<CntContratoSedes> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntContratoSedes per : list) {
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

    public static CntContratoSedes castNegocioEntidad(CntContratoSede obj) {
        CntContratoSedes per = new CntContratoSedes();
        per.setId(obj.getId());
        per.setComplejidad(obj.getComplejidad());
        per.setNumAfiliados(obj.getNumAfiliados());
        per.setMaeModalidadContratoId(obj.getMaeModalidadContratoId());
        per.setMaeModalidadContratoCodigo(obj.getMaeModalidadContratoCodigo());
        per.setMaeModalidadContratoValor(obj.getMaeModalidadContratoValor());
        per.setNivelAtencion(obj.getNivelAtencion());
        per.setFechaInicio(obj.getFechaInicio());
        per.setFechaFin(obj.getFechaFin());
        per.setValorUpcAfiliado(obj.getValorUpcAfiliado());
        per.setValorContrato(obj.getValorContrato());
        per.setObservacion(obj.getObservacion());
        per.setAplicaSubsidiado(obj.getAplicaSubsidiado());
        per.setAplicaContribuitivo(obj.getAplicaContribuitivo());
        per.setAplicaPac(obj.getAplicaPac());
        per.setAplicaGlosaExtemporanea(obj.getAplicaGlosaExtemporanea());
        per.setAplicaAuditoria(obj.getAplicaAuditoria());
        per.setAplicaPortabilidad(obj.getAplicaPortabilidad());
        per.setAplicaAgendamiento(obj.getAplicaAgendamiento());
        per.setAplicaAutorizacion(obj.getAplicaAutorizacion());
        per.setAplicaRecaudoCopagosIps(obj.getAplicaRecaudoCopagosIps());
        //objetos
        if (obj.getCntContrato() != null) {
            per.setCntContratosId(new CntContratos(obj.getCntContrato().getId()));
        }
        if (obj.getCntPrestadorSede() != null) {
            per.setCntPrestadorSedesId(new CntPrestadorSedes(obj.getCntPrestadorSede().getId()));
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

    @Override
    public int consultarCantidadPorContratoYCodigoHabilitacionPrestador(String contrato, String codigoHabilitacion) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntContratoSedes c "
                    + "WHERE c.cntContratosId.contrato = '" + contrato + "' "
                    + "AND c.cntPrestadorSedesId.codigoHabilitacion = '" + codigoHabilitacion + "' ";
            Query query = getEntityManager().createQuery(strQuery);
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
    public CntContratoSede consultarPorContratoCodigoHabilitacionPrestadorYModalidad(String contrato, String codigoHabilitacion, String modalidad) throws java.lang.Exception {
        CntContratoSede objRes = null;
        int i = 0;
        try {
            String strQuery = "FROM CntContratoSedes c "
                    + "WHERE c.cntContratosId.contrato = '" + contrato + "' "
                    + "AND c.cntPrestadorSedesId.codigoHabilitacion = '" + codigoHabilitacion + "' "
                    + "AND c.maeModalidadContratoCodigo = '" + modalidad + "' ";
            List<CntContratoSedes> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntContratoSedes per : list) {
                if (i == 0) {
                    objRes = castEntidadNegocio(per);
                    i++;
                }
            }
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public CntContratoSede consultarPorContratoYCodigoHabilitacionPrestador(String contrato, String codigoHabilitacion) throws Exception {
        CntContratoSede objResult = null;
        try {
            String strQuery = "FROM CntContratoSedes c "
                    + "WHERE c.cntContratosId.contrato = '" + contrato + "' "
                    + "AND c.cntPrestadorSedesId.codigoHabilitacion = '" + codigoHabilitacion + "' ";
            Query query = getEntityManager().createQuery(strQuery);
            objResult = castEntidadNegocio((CntContratoSedes) query.getSingleResult());
        } catch (NoResultException e) {
            objResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objResult;
    }
    
    @Override
    public CntContratoSede consultarPorContratoModalidadYCodigoHabilitacionPrestador(String contrato, String modalidad, String codigoHabilitacion) throws Exception {
        CntContratoSede objResult = null;
        try {
            String strQuery = "FROM CntContratoSedes c "
                    + "WHERE c.cntContratosId.contrato = '" + contrato + "' "
                    + "AND c.cntPrestadorSedesId.codigoHabilitacion = '" + codigoHabilitacion + "' "
                    + "AND c.maeModalidadContratoCodigo = '" + modalidad + "' ";
            Query query = getEntityManager().createQuery(strQuery);
            objResult = castEntidadNegocio((CntContratoSedes) query.getSingleResult());
        } catch (NoResultException e) {
            objResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objResult;
    }
    
    @Override
    public int consultarCantidadListaBuscador(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntContratoSedes c "
                    + "WHERE c.id > 0 ";
            // 2023-12-26 jyperez ajuste para Cuentas Medicas - RIPs Se envia el id de la Sede
            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += " AND c.cntPrestadorSedesId.id = " + paramConsulta.getParametroConsulta3() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "complejidad":
                            strQuery += "AND c.complejidad = " + (Integer) e.getValue() + " ";
                            break;
                        case "numAfiliados":
                            strQuery += "AND c.numAfiliados = " + (String) e.getValue() + " ";
                            break;
                        case "maeModalidadContratoId":
                            strQuery += "AND c.maeModalidadContratoId = " + e.getValue() + " ";
                            break;
                        case "maeModalidadContratoCodigo":
                            strQuery += "AND c.maeModalidadContratoCodigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeModalidadContratoValor":
                            strQuery += "AND c.maeModalidadContratoValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nivelAtencion":
                            strQuery += "AND c.nivelAtencion = " + e.getValue() + " ";
                            break;
                        case "fechaInicio":
                            strQuery += "AND c.fechaInicio = '" + e.getValue() + "' ";
                            break;
                        case "fechaFin":
                            strQuery += "AND c.fechaFin = '" + e.getValue() + "' ";
                            break;
                        case "valorUpcAfiliado":
                            strQuery += "AND c.valorUpcAfiliado = " + e.getValue() + " ";
                            break;
                        case "valorContrato":
                            strQuery += "AND c.valorContrato = " + e.getValue() + " ";
                            break;
                        case "observacion":
                            strQuery += "AND c.nivelAtencion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "aplicaSubsidiado":
                            strQuery += "AND c.aplicaSubsidiado = " + e.getValue() + " ";
                            break;
                        case "aplicaContribuitivo":
                            strQuery += "AND c.aplicaContribuitivo = " + e.getValue() + " ";
                            break;
                        //campos contratos
                        case "cntContrato.cntPrestador.maeTipoDocumentoCodigo":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.maeTipoDocumentoCodigo = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntContrato.cntPrestador.maeTipoDocumentoValor":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.maeTipoDocumentoValor = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntContrato.cntPrestador.numeroDocumento":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.numeroDocumento = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntContrato.cntPrestador.digitoVerificacion":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.digitoVerificacion = " + e.getValue() + " ";
                            break;
                        case "cntContrato.cntPrestador.razonSocial":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntContrato.cntPrestador.codigoMinSalud":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.codigoMinSalud = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.cntPrestador.nivelAtencion":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.nivelAtencion = " + e.getValue() + " ";
                            break;
                        case "cntContrato.maeEstadoContratoId":
                            strQuery += "AND c.cntContratosId.maeEstadoContratoId = " + e.getValue() + " ";
                            break;
                        case "cntContrato.maeEstadoContratoCodigo":
                            strQuery += "AND c.cntContratosId.maeEstadoContratoCodigo = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.maeEstadoContratoValor":
                            strQuery += "AND c.cntContratosId.maeEstadoContratoValor = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.fechaInicio":
                            strQuery += "AND c.cntContratosId.fechaInicio = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.fechaFin":
                            strQuery += "AND c.cntContratosId.fechaFin = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.contrato":
                            strQuery += "AND c.cntContratosId.contrato = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestadorSede.cntPrestador.id":
                            strQuery += "AND c.cntPrestadorSedesId.cntPrestadoresId.id = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += "AND c.cntContratosId.activo = " + (Boolean) e.getValue() + " ";
                            break;
                        case "cntPrestadorSede.codigoHabilitacionSede":
                            strQuery += "AND c.cntPrestadorSedesId.codigoHabilitacion = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestadorSede.nombreSede":
                            strQuery += "AND c.cntPrestadorSedesId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            //RANGO DE FECHA DE CONTRATO
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND fechaInicio >= :fh_inicio ";
            } else if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND fechaFin <= :fh_fin ";
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                LocalDate localFecha1 = (LocalDate) paramConsulta.getParametroConsulta1();
                Date dateFecha1 = Date.from(localFecha1.atStartOfDay(ZoneId.systemDefault()).toInstant());
                query.setParameter("fh_inicio", (dateFecha1));
                LocalDate localFecha2 = (LocalDate) paramConsulta.getParametroConsulta2();
                Date dateFecha2 = Date.from(localFecha2.atStartOfDay(ZoneId.systemDefault()).toInstant());
                query.setParameter("fh_fin", (dateFecha2));
            } else if (paramConsulta.getParametroConsulta1() != null) {
                LocalDate localFecha1 = (LocalDate) paramConsulta.getParametroConsulta1();
                Date dateFecha1 = Date.from(localFecha1.atStartOfDay(ZoneId.systemDefault()).toInstant());
                query.setParameter("fh_inicio", (dateFecha1));
            } else if (paramConsulta.getParametroConsulta2() != null) {
                LocalDate localFecha2 = (LocalDate) paramConsulta.getParametroConsulta2();
                Date dateFecha2 = Date.from(localFecha2.atStartOfDay(ZoneId.systemDefault()).toInstant());
                query.setParameter("fh_fin", (dateFecha2));
            }
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
    public List<CntContratoSede> consultarListaBuscador(ParamConsulta paramConsulta) throws Exception {
        List<CntContratoSede> listResult = new ArrayList();
        try {
            String strQuery = "SELECT c FROM CntContratoSedes c "
                    + "WHERE c.id > 0 ";
            // 2023-12-26 jyperez ajuste para Cuentas Medicas - RIPs Se envia el id de la Sede
            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += " AND c.cntPrestadorSedesId.id = " + paramConsulta.getParametroConsulta3() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "complejidad":
                            strQuery += "AND c.complejidad = " + (Integer) e.getValue() + " ";
                            break;
                        case "numAfiliados":
                            strQuery += "AND c.numAfiliados = " + (String) e.getValue() + " ";
                            break;
                        case "maeModalidadContratoId":
                            strQuery += "AND c.maeModalidadContratoId = " + e.getValue() + " ";
                            break;
                        case "maeModalidadContratoCodigo":
                            strQuery += "AND c.maeModalidadContratoCodigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeModalidadContratoValor":
                            strQuery += "AND c.maeModalidadContratoValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nivelAtencion":
                            strQuery += "AND c.nivelAtencion = " + e.getValue() + " ";
                            break;
                        case "fechaInicio":
                            strQuery += "AND c.fechaInicio = '" + e.getValue() + "' ";
                            break;
                        case "fechaFin":
                            strQuery += "AND c.fechaFin = '" + e.getValue() + "' ";
                            break;
                        case "valorUpcAfiliado":
                            strQuery += "AND c.valorUpcAfiliado = " + e.getValue() + " ";
                            break;
                        case "valorContrato":
                            strQuery += "AND c.valorContrato = " + e.getValue() + " ";
                            break;
                        case "observacion":
                            strQuery += "AND c.nivelAtencion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "aplicaSubsidiado":
                            strQuery += "AND c.aplicaSubsidiado = " + e.getValue() + " ";
                            break;
                        case "aplicaContribuitivo":
                            strQuery += "AND c.aplicaContribuitivo = " + e.getValue() + " ";
                            break;
                        //campos contratos
                        case "cntContrato.cntPrestador.maeTipoDocumentoCodigo":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.maeTipoDocumentoCodigo = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntContrato.cntPrestador.maeTipoDocumentoValor":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.maeTipoDocumentoValor = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntContrato.cntPrestador.numeroDocumento":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.numeroDocumento = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntContrato.cntPrestador.digitoVerificacion":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.digitoVerificacion = " + e.getValue() + " ";
                            break;
                        case "cntContrato.cntPrestador.razonSocial":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntContrato.cntPrestador.codigoMinSalud":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.codigoMinSalud = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.cntPrestador.nivelAtencion":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.nivelAtencion = " + e.getValue() + " ";
                            break;
                        case "cntContrato.maeEstadoContratoId":
                            strQuery += "AND c.cntContratosId.maeEstadoContratoId = " + e.getValue() + " ";
                            break;
                        case "cntContrato.maeEstadoContratoCodigo":
                            strQuery += "AND c.cntContratosId.maeEstadoContratoCodigo = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.maeEstadoContratoValor":
                            strQuery += "AND c.cntContratosId.maeEstadoContratoValor = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.fechaInicio":
                            strQuery += "AND c.cntContratosId.fechaInicio = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.fechaFin":
                            strQuery += "AND c.cntContratosId.fechaFin = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.contrato":
                            strQuery += "AND c.cntContratosId.contrato = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestadorSede.cntPrestador.id":
                            strQuery += "AND c.cntPrestadorSedesId.cntPrestadoresId.id = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += "AND c.cntContratosId.activo = " + (Boolean) e.getValue() + " ";
                            break;
                        case "cntPrestadorSede.codigoHabilitacionSede":
                            strQuery += "AND c.cntPrestadorSedesId.codigoHabilitacion = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestadorSede.nombreSede":
                            strQuery += "AND c.cntPrestadorSedesId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            //RANGO DE FECHA DE CONTRATO
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND fechaInicio >= :fh_inicio ";
            } else if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND fechaFin <= :fh_fin ";
            }
            //ordenamiento
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    case "cntContrato.contrato":
                        strQuery += "c.cntContratosId.contrato "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntContrato.cntPrestador.numeroDocumento":
                        strQuery += "c.cntContratosId.cntPrestadoresId.numeroDocumento "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntContrato.cntPrestador.digitoVerificacion":
                        strQuery += "c.cntContratosId.cntPrestadoresId.digitoVerificacion "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntContrato.cntPrestador.razonSocial":
                        strQuery += "c.cntContratosId.cntPrestadoresId.razonSocial "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntContrato.cntPrestador.codigoMinSalud":
                        strQuery += "c.cntContratosId.cntPrestadoresId.codigoMinSalud "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntContrato.fechaInicio":
                        strQuery += "c.cntContratosId.fechaInicio "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntContrato.fechaFin":
                        strQuery += "c.cntContratosId.fechaFin "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntContrato.cntPrestador.nivelAtencion":
                        strQuery += "c.cntContratosId.cntPrestadoresId.nivelAtencion "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntContrato.maeEstadoContratoId":
                        strQuery += "c.cntContratosId.maeEstadoContratoId "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntPrestadorSede.codigoHabilitacionSede":
                        strQuery += "c.cntPrestadorSedesId.codigoHabilitacion "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntPrestadorSede.nombreSede":
                        strQuery += "c.cntPrestadorSedesId.nombre "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    default:
                        strQuery += "c." + paramConsulta.getOrden() + " "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                }

            } else {
                strQuery += "c.cntContratosId.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                LocalDate localFecha1 = (LocalDate) paramConsulta.getParametroConsulta1();
                Date dateFecha1 = Date.from(localFecha1.atStartOfDay(ZoneId.systemDefault()).toInstant());
                query.setParameter("fh_inicio", (dateFecha1));
                LocalDate localFecha2 = (LocalDate) paramConsulta.getParametroConsulta2();
                Date dateFecha2 = Date.from(localFecha2.atStartOfDay(ZoneId.systemDefault()).toInstant());
                query.setParameter("fh_fin", (dateFecha2));
            } else if (paramConsulta.getParametroConsulta1() != null) {
                LocalDate localFecha1 = (LocalDate) paramConsulta.getParametroConsulta1();
                Date dateFecha1 = Date.from(localFecha1.atStartOfDay(ZoneId.systemDefault()).toInstant());
                query.setParameter("fh_inicio", (dateFecha1));
            } else if (paramConsulta.getParametroConsulta2() != null) {
                LocalDate localFecha2 = (LocalDate) paramConsulta.getParametroConsulta2();
                Date dateFecha2 = Date.from(localFecha2.atStartOfDay(ZoneId.systemDefault()).toInstant());
                query.setParameter("fh_fin", (dateFecha2));
            }
            List<CntContratoSedes> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntContratoSedes per : list) {
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
}
