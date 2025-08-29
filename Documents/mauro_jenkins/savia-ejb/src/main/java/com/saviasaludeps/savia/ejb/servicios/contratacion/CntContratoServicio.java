package com.saviasaludeps.savia.ejb.servicios.contratacion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoCobertura;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoSede;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CntContratoCoberturas;
import com.saviasaludeps.savia.ejb.entidades.CntContratoDetalles;
import com.saviasaludeps.savia.ejb.entidades.CntContratoSedes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.ejb.entidades.CntContratos;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import javax.persistence.Query;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoRemoto;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Session;

@Stateless
@Remote(CntContratoRemoto.class)
public class CntContratoServicio extends GenericoServicio implements CntContratoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntContratos c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntPrestadoresId":
                            strQuery += "AND c.cntPrestadoresId.id = " + (Integer) e.getValue() + " ";
                            break;
                        case "contrato":
                            strQuery += "AND c.contrato = '" + (String) e.getValue() + "' ";
                            break;
                        case "activo":
                            strQuery += "AND c.activo = " + (boolean) e.getValue() + " ";
                            break;
                        case "cntPrestador.nombreRepresentanteLegal":
                            strQuery += "AND c.cntPrestadoresId.nombreRepresentanteLegal LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "negociacion":
                            strQuery += "AND c.negociacion LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "cntPrestador.maeTipoDocumentoCodigo":
                            strQuery += "AND c.cntPrestadoresId.maeTipoDocumentoCodigo = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntPrestador.maeTipoDocumentoValor":
                            strQuery += "AND c.cntPrestadoresId.maeTipoDocumentoValor = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntPrestador.digitoVerificacion":
                            strQuery += "AND c.cntPrestadoresId.digitoVerificacion = " + e.getValue() + " ";
                            break;
                        case "cntPrestador.razonSocial":
                            strQuery += "AND c.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntPrestador.codigoMinSalud":
                            strQuery += "AND c.cntPrestadoresId.codigoMinSalud = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.nivelAtencion":
                            strQuery += "AND c.cntPrestadoresId.nivelAtencion = " + e.getValue() + " ";
                            break;
                        case "maeEstadoContratoId":
                            strQuery += "AND c.maeEstadoContratoId = " + e.getValue() + " ";
                            break;
                        case "maeEstadoContratoCodigo":
                            strQuery += "AND c.maeEstadoContratoCodigo = '" + e.getValue() + "' ";
                            break;
                        case "maeEstadoContratoValor":
                            strQuery += "AND c.maeEstadoContratoValor = '" + e.getValue() + "' ";
                            break;
                        case "fechaInicio":
                            strQuery += "AND c.fechaInicio = '" + e.getValue() + "' ";
                            break;
                        case "fechaFin":
                            strQuery += "AND c.fechaFin = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.numeroDocumento":
                            strQuery += "AND c.cntPrestadoresId.numeroDocumento LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            //Fecha
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND c.fechaInicio >= :fh_inicio AND c.fechaFin <= :fh_fin ";
            } else if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND c.fechaInicio >= :fh_inicio ";
            } else if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND c.fechaFin <= :fh_fin ";
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("fh_inicio", (Date) paramConsulta.getParametroConsulta1());
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fh_fin", (Date) paramConsulta.getParametroConsulta2());
            }
            if (paramConsulta.getParametroConsulta1() != null) {// Miiembro de un area (Persona)
                query.setParameter("fh_inicio", (Date) paramConsulta.getParametroConsulta2());
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
    public List<CntContrato> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CntContrato> listResult = new ArrayList();
        try {
            String strQuery = "SELECT c FROM CntContratos c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntPrestadoresId":
                            strQuery += "AND c.cntPrestadoresId.id = " + (Integer) e.getValue() + " ";
                            break;
                        case "contrato":
                            strQuery += "AND c.contrato = '" + (String) e.getValue() + "' ";
                            break;
                        case "activo":
                            strQuery += "AND c.activo = " + (boolean) e.getValue() + " ";
                            break;
                        case "cntPrestador.nombreRepresentanteLegal":
                            strQuery += "AND c.cntPrestadoresId.nombreRepresentanteLegal LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "negociacion":
                            strQuery += "AND c.negociacion LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "cntPrestador.maeTipoDocumentoCodigo":
                            strQuery += "AND c.cntPrestadoresId.maeTipoDocumentoCodigo = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntPrestador.maeTipoDocumentoValor":
                            strQuery += "AND c.cntPrestadoresId.maeTipoDocumentoValor = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntPrestador.digitoVerificacion":
                            strQuery += "AND c.cntPrestadoresId.digitoVerificacion = " + e.getValue() + " ";
                            break;
                        case "cntPrestador.razonSocial":
                            strQuery += "AND c.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntPrestador.codigoMinSalud":
                            strQuery += "AND c.cntPrestadoresId.codigoMinSalud = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.nivelAtencion":
                            strQuery += "AND c.cntPrestadoresId.nivelAtencion = " + e.getValue() + " ";
                            break;
                        case "maeEstadoContratoId":
                            strQuery += "AND c.maeEstadoContratoId = " + e.getValue() + " ";
                            break;
                        case "maeEstadoContratoCodigo":
                            strQuery += "AND c.maeEstadoContratoCodigo = '" + e.getValue() + "' ";
                            break;
                        case "maeEstadoContratoValor":
                            strQuery += "AND c.maeEstadoContratoValor = '" + e.getValue() + "' ";
                            break;
                        case "fechaInicio":
                            strQuery += "AND c.fechaInicio = '" + e.getValue() + "' ";
                            break;
                        case "fechaFin":
                            strQuery += "AND c.fechaFin = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.numeroDocumento":
                            strQuery += "AND c.cntPrestadoresId.numeroDocumento LIKE '%" + e.getValue() + "%' ";
                            break;

                    }
                }
            }
            //Fecha
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND c.fechaInicio >= :fh_inicio AND c.fechaFin <= :fh_fin ";
            } else if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND c.fechaInicio >= :fh_inicio ";
            } else if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND c.fechaFin <= :fh_fin ";
            }
            //ordenamiento
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {

                switch (paramConsulta.getOrden()) {
                    case "cntContrato.contrato":
                        strQuery += "c.cntContratosId.contrato "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntPrestador.nombreRepresentanteLegal":
                        strQuery += "c.cntPrestadoresId.nombreRepresentanteLegal "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntPrestador.numeroDocumento":
                        strQuery += "c.cntPrestadoresId.numeroDocumento "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntPrestador.razonSocial":
                        strQuery += "c.cntPrestadoresId.razonSocial "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntContrato.negociacion":
                        strQuery += "c.negociacion "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    default:
                        strQuery += "c." + paramConsulta.getOrden() + " "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                }

            } else {
                strQuery += "c.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("fh_inicio", (Date) paramConsulta.getParametroConsulta1());
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fh_fin", (Date) paramConsulta.getParametroConsulta2());
            }
            if (paramConsulta.getParametroConsulta1() != null) {// Miiembro de un area (Persona)
                query.setParameter("fh_inicio", (Date) paramConsulta.getParametroConsulta2());
            }
            //getEntityManager().createQuery(strQuery)
            List<CntContratos> list = query.setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntContratos per : list) {
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
    public int consultarCantidadListaConciliacion(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntContratos c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntPrestadoresId":
                            strQuery += "AND c.cntPrestadoresId.id = " + (Integer) e.getValue() + " ";
                            break;
                        case "contrato":
                            strQuery += "AND c.contrato = '" + (String) e.getValue() + "' ";
                            break;
                        case "activo":
                            strQuery += "AND c.activo = " + (boolean) e.getValue() + " ";
                            break;
                        case "cntPrestador.nombreRepresentanteLegal":
                            strQuery += "AND c.cntPrestadoresId.nombreRepresentanteLegal LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "negociacion":
                            strQuery += "AND c.negociacion LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "cntPrestador.maeTipoDocumentoCodigo":
                            strQuery += "AND c.cntPrestadoresId.maeTipoDocumentoCodigo = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntPrestador.maeTipoDocumentoValor":
                            strQuery += "AND c.cntPrestadoresId.maeTipoDocumentoValor = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntPrestador.digitoVerificacion":
                            strQuery += "AND c.cntPrestadoresId.digitoVerificacion = " + e.getValue() + " ";
                            break;
                        case "cntPrestador.razonSocial":
                            strQuery += "AND c.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntPrestador.codigoMinSalud":
                            strQuery += "AND c.cntPrestadoresId.codigoMinSalud = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.nivelAtencion":
                            strQuery += "AND c.cntPrestadoresId.nivelAtencion = " + e.getValue() + " ";
                            break;
                        case "maeEstadoContratoId":
                            strQuery += "AND c.maeEstadoContratoId = " + e.getValue() + " ";
                            break;
                        case "maeEstadoContratoCodigo":
                            strQuery += "AND c.maeEstadoContratoCodigo = '" + e.getValue() + "' ";
                            break;
                        case "maeEstadoContratoValor":
                            strQuery += "AND c.maeEstadoContratoValor = '" + e.getValue() + "' ";
                            break;
                        case "fechaInicio":
                            strQuery += "AND c.fechaInicio = '" + e.getValue() + "' ";
                            break;
                        case "fechaFin":
                            strQuery += "AND c.fechaFin = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.numeroDocumento":
                            strQuery += "AND c.cntPrestadoresId.numeroDocumento LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            //Fecha
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND c.fechaInicio >= :fh_inicio AND c.fechaFin <= :fh_fin ";
            } else if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND c.fechaInicio >= :fh_inicio ";
            } else if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND c.fechaFin <= :fh_fin ";
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("fh_inicio", (Date) paramConsulta.getParametroConsulta1());
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fh_fin", (Date) paramConsulta.getParametroConsulta2());
            }
            if (paramConsulta.getParametroConsulta1() != null) {// Miiembro de un area (Persona)
                query.setParameter("fh_inicio", (Date) paramConsulta.getParametroConsulta2());
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
    public List<CntContrato> consultarListaConciliacion(ParamConsulta paramConsulta) throws Exception {
        List<CntContrato> listResult = new ArrayList();
        try {
            String strQuery = "SELECT c FROM CntContratos c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntPrestadoresId":
                            strQuery += "AND c.cntPrestadoresId.id = " + (Integer) e.getValue() + " ";
                            break;
                        case "contrato":
                            strQuery += "AND c.contrato = '" + (String) e.getValue() + "' ";
                            break;
                        case "activo":
                            strQuery += "AND c.activo = " + (boolean) e.getValue() + " ";
                            break;
                        case "cntPrestador.nombreRepresentanteLegal":
                            strQuery += "AND c.cntPrestadoresId.nombreRepresentanteLegal LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "negociacion":
                            strQuery += "AND c.negociacion LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "cntPrestador.maeTipoDocumentoCodigo":
                            strQuery += "AND c.cntPrestadoresId.maeTipoDocumentoCodigo = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntPrestador.maeTipoDocumentoValor":
                            strQuery += "AND c.cntPrestadoresId.maeTipoDocumentoValor = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntPrestador.digitoVerificacion":
                            strQuery += "AND c.cntPrestadoresId.digitoVerificacion = " + e.getValue() + " ";
                            break;
                        case "cntPrestador.razonSocial":
                            strQuery += "AND c.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntPrestador.codigoMinSalud":
                            strQuery += "AND c.cntPrestadoresId.codigoMinSalud = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.nivelAtencion":
                            strQuery += "AND c.cntPrestadoresId.nivelAtencion = " + e.getValue() + " ";
                            break;
                        case "maeEstadoContratoId":
                            strQuery += "AND c.maeEstadoContratoId = " + e.getValue() + " ";
                            break;
                        case "maeEstadoContratoCodigo":
                            strQuery += "AND c.maeEstadoContratoCodigo = '" + e.getValue() + "' ";
                            break;
                        case "maeEstadoContratoValor":
                            strQuery += "AND c.maeEstadoContratoValor = '" + e.getValue() + "' ";
                            break;
                        case "fechaInicio":
                            strQuery += "AND c.fechaInicio = '" + e.getValue() + "' ";
                            break;
                        case "fechaFin":
                            strQuery += "AND c.fechaFin = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.numeroDocumento":
                            strQuery += "AND c.cntPrestadoresId.numeroDocumento LIKE '%" + e.getValue() + "%' ";
                            break;

                    }
                }
            }
            //Fecha
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND c.fechaInicio >= :fh_inicio AND c.fechaFin <= :fh_fin ";
            } else if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND c.fechaInicio >= :fh_inicio ";
            } else if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND c.fechaFin <= :fh_fin ";
            }
            //ordenamiento
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {

                switch (paramConsulta.getOrden()) {
                    case "cntContrato.contrato":
                        strQuery += "c.cntContratosId.contrato "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntPrestador.nombreRepresentanteLegal":
                        strQuery += "c.cntPrestadoresId.nombreRepresentanteLegal "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntPrestador.numeroDocumento":
                        strQuery += "c.cntPrestadoresId.numeroDocumento "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntPrestador.razonSocial":
                        strQuery += "c.cntPrestadoresId.razonSocial "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntContrato.negociacion":
                        strQuery += "c.negociacion "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    default:
                        strQuery += "c." + paramConsulta.getOrden() + " "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                }

            } else {
                strQuery += "c.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("fh_inicio", (Date) paramConsulta.getParametroConsulta1());
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fh_fin", (Date) paramConsulta.getParametroConsulta2());
            }
            if (paramConsulta.getParametroConsulta1() != null) {// Miiembro de un area (Persona)
                query.setParameter("fh_inicio", (Date) paramConsulta.getParametroConsulta2());
            }
            //getEntityManager().createQuery(strQuery)
            List<CntContratos> list = query.setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntContratos per : list) {
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
    
    private CntContrato castEntidadNegocio(CntContratos per) {
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
        //2021-06-11 Sprint 1 nuevos campos
        neg.setNumAfiliados(per.getNumAfiliados());
        neg.setMaeRegimenId(per.getMaeRegimenId());
        neg.setMaeRegimenCodigo(per.getMaeRegimenCodigo());
        neg.setMaeRegimenValor(per.getMaeRegimenValor());
        //2023-06-21 jyperez nuevo requerimiento
        //2025-05-13 jyperez cambio BD a campo booleano
        neg.setAutorizaGestion(per.getAutorizaGestion());
        //objetos
        if (per.getCntPrestadoresId() != null) {
            neg.setCntPrestador(castCntPrestadorEntidadNegocio(per.getCntPrestadoresId()));
        }
        if (per.getGnEmpresasId() != null) {
            neg.setGnEmpresa(new Empresa(per.getGnEmpresasId().getId()));
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
        obj.setUnionTemporal(per.getUnionTemporal());
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

    @Override
    public CntContrato consultar(int id) throws Exception {
        CntContrato objRes = null;
        try {
            objRes = castEntidadNegocioLargo((CntContratos) getEntityManager().find(CntContratos.class, id));
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
    public int insertar(CntContrato obj) throws Exception {
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
    public void actualizar(CntContrato obj) throws Exception {
        try {
            CntContratos contrato = castNegocioEntidad(obj);
            //getEntityManager().merge(castNegocioEntidad(obj));
            Session session = getEntityManager().unwrap(Session.class);
            //session.update(per);
            String strQuery = "UPDATE CntContratos a SET ";
            strQuery += " a.negociacion = :negociacion ,";
            strQuery += " a.contrato = :contrato ,";
            strQuery += " a.descripcion = :descripcion ,";
            strQuery += " a.activo = :activo ,";
            strQuery += " a.maeEstadoContratoId = :maeEstadoContratoId ,";
            strQuery += " a.maeEstadoContratoCodigo = :maeEstadoContratoCodigo ,";
            strQuery += " a.maeEstadoContratoValor = :maeEstadoContratoValor ,";
            //campo fechas
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            String fechaInicio = formatoFecha.format(obj.getFechaInicio());
            strQuery += " a.fechaInicio = '" + fechaInicio + "', ";
            String fechaFin = formatoFecha.format(obj.getFechaFin());
            strQuery += " a.fechaFin = '" + fechaFin + "', ";

            strQuery += " a.valor = :valor ,";
            strQuery += " a.valorMes = :valorMes ,";
            strQuery += " a.valorPresupuestoTotal = :valorPresupuestoTotal ,";
            strQuery += " a.diasLimitePago = :diasLimitePago ,";
            //2021-06-15 jyperez Sprint 1 nuevos campos
            strQuery += " a.numAfiliados = :numAfiliados ,";
            strQuery += " a.maeRegimenId = :maeRegimenId ,";
            strQuery += " a.maeRegimenCodigo = :maeRegimenCodigo ,";
            strQuery += " a.maeRegimenValor = :maeRegimenValor ,";
            strQuery += " a.usuarioModifica = :usuarioModifica ,";
            strQuery += " a.fechaHoraModifica = :fechaHoraModifica ,";
            strQuery += " a.terminalModifica = :terminalModifica ,";
            //2023-06-21 jyperez nuevo requerimiento
            strQuery += " a.autorizaGestion = :autorizaGestion ";
            //campos objetos 
            if (contrato.getCntPrestadoresId() != null) {
                strQuery += ", a.cntPrestadoresId.id = " + contrato.getCntPrestadoresId().getId() + " ";
            }
            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(contrato);
            query.executeUpdate();

        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarEjecucionContratoAutorizado(CntContrato obj) throws Exception {
        try {
            String hql = "UPDATE CntContratos SET "    
                    + "ejecucionContratoAutorizado = :ejecucionContratoAutorizado "
                    //2025-07-10 jyperez se comenta actualización de auditoria por solicitud de la PO
                    /*+ "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "*/
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("ejecucionContratoAutorizado", obj.getEjecucionContratoAutorizado());
            //auditoria
            //query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            //query.setParameter("terminalModifica", obj.getTerminalModifica());
            //query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public CntContrato eliminar(int id) throws Exception {
        CntContrato obj = null;
        try {
            CntContratos ent = getEntityManager().find(CntContratos.class, id);
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
    public List<CntContrato> consultarTodos() throws Exception {
        List<CntContrato> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntContratos p "
                    + "ORDER BY p.id ";
            List<CntContratos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntContratos per : list) {
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

    public static CntContratos castNegocioEntidad(CntContrato obj) {
        CntContratos per = new CntContratos();
        per.setId(obj.getId());
        per.setNegociacion(obj.getNegociacion());
        per.setContrato(obj.getContrato());
        per.setDescripcion(obj.getDescripcion());
        per.setActivo(obj.isActivo());
        per.setMaeEstadoContratoId(obj.getMaeEstadoContratoId());
        per.setMaeEstadoContratoCodigo(obj.getMaeEstadoContratoCodigo());
        per.setMaeEstadoContratoValor(obj.getMaeEstadoContratoValor());
        per.setFechaInicio(obj.getFechaInicio());
        per.setFechaFin(obj.getFechaFin());
        per.setValor(obj.getValor());
        per.setValorMes(obj.getValorMes());
        per.setValorPresupuestoTotal(obj.getValorPresupuestoTotal());
        per.setDiasLimitePago(obj.getDiasLimitePago());
        //2021-06-11 Sprint 1 nuevos campos
        per.setNumAfiliados(obj.getNumAfiliados());
        per.setMaeRegimenId(obj.getMaeRegimenId());
        per.setMaeRegimenCodigo(obj.getMaeRegimenCodigo());
        per.setMaeRegimenValor(obj.getMaeRegimenValor());
        //2023-06-21 jyperez nuevo requerimiento
        per.setAutorizaGestion(obj.getAutorizaGestion());
        per.setEjecucionContratoAutorizado(obj.getEjecucionContratoAutorizado());
        per.setEjecucionContratoPrestado(obj.getEjecucionContratoPrestado());
        per.setEjecucionTotalContrato(obj.getEjecucionTotalContrato());
        //objetos
        if (obj.getCntPrestador() != null) {
            per.setCntPrestadoresId(new CntPrestadores(obj.getCntPrestador().getId()));
        }
        if (obj.getGnEmpresa() != null) {
            per.setGnEmpresasId(new GnEmpresas(obj.getGnEmpresa().getId()));
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
    public CntContrato consultarConSedes(int id) throws java.lang.Exception {
        CntContrato objRes = null;
        try {
            objRes = castEntidadNegocioLargo((CntContratos) getEntityManager().find(CntContratos.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private CntContrato castEntidadNegocioLargo(CntContratos per) {
        CntContrato neg = new CntContrato();
        ArrayList<CntContratoSede> listaSedes = new ArrayList<>();

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
        //2021-06-11 Sprint 1 nuevos campos
        neg.setNumAfiliados(per.getNumAfiliados());
        neg.setMaeRegimenId(per.getMaeRegimenId());
        neg.setMaeRegimenCodigo(per.getMaeRegimenCodigo());
        neg.setMaeRegimenValor(per.getMaeRegimenValor());
        //2023-06-21 jyperez nuevo requerimiento
        //2025-05-13 jyperez cambio BD a campo booleano
        neg.setAutorizaGestion(per.getAutorizaGestion());
        neg.setEjecucionContratoAutorizado(per.getEjecucionContratoAutorizado());
        neg.setEjecucionContratoPrestado(per.getEjecucionContratoPrestado());
        neg.setEjecucionTotalContrato(per.getEjecucionTotalContrato());
        //objetos
        if (per.getCntPrestadoresId() != null) {
            neg.setCntPrestador(castCntPrestadorEntidadNegocio(per.getCntPrestadoresId()));
        }
        if (per.getGnEmpresasId() != null) {
            neg.setGnEmpresa(new Empresa(per.getGnEmpresasId().getId()));
        }
        //lista objetos
        if (per.getCntContratoSedesList() != null) {
            for (CntContratoSedes sede : per.getCntContratoSedesList()) {
                CntContratoSede aux = castCntContratoSedeEntidadNegocio(sede);
                listaSedes.add(aux);
            }
            neg.setListaContratoSedes(listaSedes);
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

    public CntContratoSede castCntContratoSedeEntidadNegocio(CntContratoSedes per) {
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
            CntContrato contrato = new CntContrato(per.getCntContratosId().getId());
            contrato.setActivo(per.getCntContratosId().getActivo());
            contrato.setContrato(per.getCntContratosId().getContrato());

            CntPrestador prestador = new CntPrestador();
            if (per.getCntContratosId().getCntPrestadoresId() != null) {
                prestador.setId(per.getCntContratosId().getCntPrestadoresId().getId());
                prestador.setRazonSocial(per.getCntContratosId().getCntPrestadoresId().getRazonSocial());
            }
            contrato.setCntPrestador(prestador);
            neg.setCntContrato(contrato);
        }
        if (per.getCntPrestadorSedesId() != null) {
            neg.setCntPrestadorSede(castPrestadorSedesEntidadNegocio(per.getCntPrestadorSedesId()));
        }
        //2022-07-14 jyperez se obtiene la lista coberturas
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
    
    public CntContratoCobertura castContratoCoberturaEntidadNegocio(CntContratoCoberturas per) {
        CntContratoCobertura neg = new CntContratoCobertura();
        neg.setId(per.getId());
        neg.setActivo(per.getActivo());
        //objetos
        if(per.getCntContratosId() != null) {
            neg.setCntContrato(new CntContrato(per.getCntContratosId().getId()));
        }
        if(per.getCntContratoSedesId()!= null) {
            neg.setCntContratoSede(new CntContratoSede(per.getCntContratoSedesId().getId()));
        }
        if (per.getCntPrestadorSedesId() != null) {
            neg.setCntPrestadorSede(new CntPrestadorSede(per.getCntPrestadorSedesId().getId()));
        }
        if (per.getGnUbicacionesId() != null) {
            neg.setUbicacion(new Ubicacion(null,per.getGnUbicacionesId().getId(),per.getGnUbicacionesId().getTipo(),"",per.getGnUbicacionesId().getNombre()));
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

    @Override
    public int consultarCantidadPorContrato(String contrato) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntContratos c "
                    + "WHERE c.contrato = '" + contrato + "' ";
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
    public CntContrato consultarPorContrato(String contrato) throws java.lang.Exception {
        CntContrato obj = null;
        int i = 0;
        try {
            String strQuery = "FROM CntContratos p "
                    + "WHERE p.contrato = '" + contrato + "'";
            List<CntContratos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntContratos per : list) {
                if (i == 0) {
                    obj = castEntidadNegocio(per);
                    i++;
                }
            }
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public CntContrato consultarContratoCompleto(int id) throws java.lang.Exception {
        CntContrato objRes = null;
        try {
            objRes = castEntidadNegocioCompleto((CntContratos) getEntityManager().find(CntContratos.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private CntContrato castEntidadNegocioCompleto(CntContratos per) {
        CntContrato neg = new CntContrato();
        ArrayList<CntContratoSede> listaSedes = new ArrayList<>();

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
        //2021-06-11 Sprint 1 nuevos campos
        neg.setNumAfiliados(per.getNumAfiliados());
        neg.setMaeRegimenId(per.getMaeRegimenId());
        neg.setMaeRegimenCodigo(per.getMaeRegimenCodigo());
        neg.setMaeRegimenValor(per.getMaeRegimenValor());
        //2023-06-21 jyperez nuevo requerimiento
        //2025-05-13 jyperez cambio BD a campo booleano
        neg.setAutorizaGestion(per.getAutorizaGestion());
        //objetos
        if (per.getCntPrestadoresId() != null) {
            neg.setCntPrestador(castCntPrestadorEntidadNegocio(per.getCntPrestadoresId()));
        }
        if (per.getGnEmpresasId() != null) {
            neg.setGnEmpresa(new Empresa(per.getGnEmpresasId().getId()));
        }
        //lista objetos
        if (per.getCntContratoSedesList() != null) {
            for (CntContratoSedes sede : per.getCntContratoSedesList()) {
                CntContratoSede aux = castCntContratoSedeEntidadNegocioCompleto(sede);
                listaSedes.add(aux);
            }
            neg.setListaContratoSedes(listaSedes);
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

    public CntContratoSede castCntContratoSedeEntidadNegocioCompleto(CntContratoSedes per) {
        CntContratoSede neg = new CntContratoSede();
        List<CntContratoDetalle> listaContratoDetalle;

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
            CntContrato contrato = new CntContrato(per.getCntContratosId().getId());
            contrato.setActivo(per.getCntContratosId().getActivo());
            contrato.setContrato(per.getCntContratosId().getContrato());

            CntPrestador prestador = new CntPrestador();
            if (per.getCntContratosId().getCntPrestadoresId() != null) {
                prestador.setId(per.getCntContratosId().getCntPrestadoresId().getId());
                prestador.setRazonSocial(per.getCntContratosId().getCntPrestadoresId().getRazonSocial());
            }
            contrato.setCntPrestador(prestador);
            neg.setCntContrato(contrato);
        }
        if (per.getCntPrestadorSedesId() != null) {
            neg.setCntPrestadorSede(castPrestadorSedesEntidadNegocio(per.getCntPrestadorSedesId()));
        }
        // lista de ContratoDetalles
        if (per.getCntContratoDetallesList() != null) {
            listaContratoDetalle = new ArrayList<>();
            for (CntContratoDetalles det : per.getCntContratoDetallesList()) {
                CntContratoDetalle detalle = castCntContratoDetalleEntidadNegocio(det);
                listaContratoDetalle.add(detalle);
            }
            neg.setCntContratoDetalle(listaContratoDetalle);

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

    private CntContratoDetalle castCntContratoDetalleEntidadNegocio(CntContratoDetalles per) {
        CntContratoDetalle neg = new CntContratoDetalle();
        neg.setId(per.getId());
        neg.setActivo(per.getActivo());
        neg.setTipoTecnologia(per.getTipoTecnologia());
        neg.setMaTecnologiaId(per.getMaTecnologiaId());
        neg.setMaTecnologiaCodigo(per.getMaTecnologiaCodigo());
        neg.setMaTecnologiaValor(per.getMaTecnologiaValor());
        neg.setMaServicioHabilitacionId(per.getMaServicioHabilitacionId());
        neg.setMaServicioHabilitacionCodigo(per.getMaServicioHabilitacionCodigo());
        neg.setMaServicioHabilitacionValor(per.getMaServicioHabilitacionValor());
        neg.setTipoManualTarifario(per.getTipoManualTarifario());
        neg.setMaManualTarifarioId(per.getMaManualTarifarioId());
        neg.setMaManualTarifarioCodigo(per.getMaManualTarifarioCodigo());
        neg.setMaManualTarifarioValor(per.getMaManualTarifarioValor());
        neg.setMaManualTarifarioAgno(per.getMaManualTarifarioAgno());
        neg.setValorManual(per.getValorManual());
        neg.setValorContratado(per.getValorContratado());
        neg.setPorcentajeVariacion(per.getPorcentajeVariacion());
        neg.setComplejidad(per.getComplejidad());
        neg.setObservacionIncluye(per.getObservacionIncluye());
        neg.setObservacionExcluye(per.getObservacionExcluye());
        neg.setInterdependencia(per.getInterdependencia());
        neg.setMaeAmbitoId(per.getMaeAmbitoId());
        neg.setMaeAmbitoCodigo(per.getMaeAmbitoCodigo());
        neg.setMaeAmbitoValor(per.getMaeAmbitoValor());
        neg.setFechaHoraInicio(per.getFechaHoraInicio());
        //2022-10-05 jyperez nuevos campos
        neg.setAutomaticoManual(per.getAutomaticoManual());
        neg.setAutomaticoMasivo(per.getAutomaticoMasivo());
        neg.setAutomaticoInteroperabilidad(per.getAutomaticoInteroperabilidad());
        neg.setPreautorizacion(per.getPreautorizacion());
        neg.setFechaHoraFin(per.getFechaHoraFin());
        neg.setValorMaximoRegulado(per.getValorMaximoRegulado());
        //objetos
        if (per.getCntContratoSedesId() != null) {
            CntContratoSede sedeAux = new CntContratoSede(per.getCntContratoSedesId().getId());
            sedeAux.setMaeModalidadContratoCodigo(per.getCntContratoSedesId().getMaeModalidadContratoCodigo());
            if (per.getCntContratoSedesId().getCntPrestadorSedesId() != null) {
                sedeAux.setCntPrestadorSede(new CntPrestadorSede(per.getCntContratoSedesId().getCntPrestadorSedesId().getId(), per.getCntContratoSedesId().getCntPrestadorSedesId().getCntPrestadoresId().getId(), per.getCntContratoSedesId().getCntPrestadorSedesId().getCodigoPrestador(), per.getCntContratoSedesId().getCntPrestadorSedesId().getUbicacionId(), per.getCntContratoSedesId().getCntPrestadorSedesId().getNombre(), per.getCntContratoSedesId().getCntPrestadorSedesId().getCodigoHabilitacion(), per.getCntContratoSedesId().getCntPrestadorSedesId().getEstadoSede()));
            }
            neg.setCntContratoSede(sedeAux);
        }
        if (per.getCntPrestadorSedesInterdependenciaId() != null) {
            neg.setCntPrestadorSedesInterdependencia(new CntPrestadorSede(per.getCntPrestadorSedesInterdependenciaId().getId()));
        }
        if (per.getCntContratosId() != null) {
            CntContrato contrato = new CntContrato(per.getCntContratosId().getId(), per.getCntContratosId().getContrato());
            if (per.getCntContratosId().getCntPrestadoresId() != null) {
                contrato.setCntPrestador(new CntPrestador(per.getCntContratosId().getCntPrestadoresId().getId(), per.getCntContratosId().getCntPrestadoresId().getRazonSocial()));
            }
            neg.setCntContrato(contrato);
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
    public CntContrato consultarDatosBasicos(int id) throws Exception {
        CntContrato objRes = null;
        try {
            objRes = castEntidadNegocioCorto((CntContratos) getEntityManager().find(CntContratos.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }
    
    private CntContrato castEntidadNegocioCorto(CntContratos per) {
        CntContrato neg = new CntContrato();
        neg.setId(per.getId());
        //neg.setNegociacion(per.getNegociacion());
        neg.setContrato(per.getContrato());
        neg.setDescripcion(per.getDescripcion());
        neg.setActivo(per.getActivo());
        //2023-06-21 jyperez nuevo requerimiento
        //2025-05-13 jyperez cambio BD a campo booleano
        neg.setAutorizaGestion(per.getAutorizaGestion());
//        neg.setMaeEstadoContratoId(per.getMaeEstadoContratoId());
//        neg.setMaeEstadoContratoCodigo(per.getMaeEstadoContratoCodigo());
//        neg.setMaeEstadoContratoValor(per.getMaeEstadoContratoValor());
//        neg.setFechaInicio(per.getFechaInicio());
//        neg.setFechaFin(per.getFechaFin());
//        neg.setValor(per.getValor());
//        neg.setValorMes(per.getValorMes());
//        neg.setValorPresupuestoTotal(per.getValorPresupuestoTotal());
//        neg.setDiasLimitePago(per.getDiasLimitePago());
//        //2021-06-11 Sprint 1 nuevos campos
//        neg.setNumAfiliados(per.getNumAfiliados());
//        neg.setMaeRegimenId(per.getMaeRegimenId());
//        neg.setMaeRegimenCodigo(per.getMaeRegimenCodigo());
//        neg.setMaeRegimenValor(per.getMaeRegimenValor());
        //objetos
        if (per.getCntPrestadoresId() != null) {
            CntPrestador prestador = new CntPrestador();
            prestador.setId(per.getCntPrestadoresId().getId());
            prestador.setRazonSocial(per.getCntPrestadoresId().getRazonSocial());
            prestador.setCodigoMinSalud(per.getCntPrestadoresId().getCodigoMinSalud());
            neg.setCntPrestador(prestador);
        }
        if (per.getGnEmpresasId() != null) {
            neg.setGnEmpresa(new Empresa(per.getGnEmpresasId().getId()));
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

    @Override
    public List<CntContrato> consultarPorPrestadorSede(int cntPrestadorId, int tipoTecnologia, int idTecnologia, String maeModalidadContratoCodigo, Date fecha, int ubicacionId) throws java.lang.Exception {
        List<CntContrato> listResult = null;
        SimpleDateFormat std = new SimpleDateFormat("yyyy-MM-dd");
        int i = 0;
        try {
            String strQuery = "SELECT DISTINCT(p.cntContratosId) FROM CntContratoDetalles p "
                    + " WHERE p.cntContratosId.cntPrestadoresId.id = " + cntPrestadorId + " "
                    + " AND p.cntContratoSedesId.cntPrestadorSedesId.ubicacionId = " + ubicacionId + " "
                    + " AND p.cntContratoSedesId.maeModalidadContratoCodigo = '" + maeModalidadContratoCodigo + "' "
                    + " AND p.tipoTecnologia = " + tipoTecnologia + " "
                    + " AND p.maTecnologiaId = " + idTecnologia + " "
                    + " AND p.activo = 1 "
                    + " AND p.fechaHoraFin >= '" + std.format(fecha) + "' "
                    + " AND p.cntContratosId.maeEstadoContratoCodigo = '01' "
                    + " AND p.cntContratosId.activo = 1 "
                    + " AND p.cntContratosId.fechaFin >= '" + std.format(fecha) + "' ";
            List<CntContratos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if (!list.isEmpty()) {
                listResult = new ArrayList();
                for (CntContratos per : list) {
                        CntContrato obj = castEntidadNegocio(per);
                        listResult.add(obj);
                }
            }
        } catch (NoResultException e) {
            listResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
}
