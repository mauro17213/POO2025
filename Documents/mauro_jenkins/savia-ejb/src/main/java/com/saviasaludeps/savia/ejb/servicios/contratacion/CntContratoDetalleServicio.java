package com.saviasaludeps.savia.ejb.servicios.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
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
import com.saviasaludeps.savia.ejb.entidades.CntContratoDetalles;
import com.saviasaludeps.savia.ejb.entidades.CntContratoSedes;
import com.saviasaludeps.savia.ejb.entidades.CntContratos;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import javax.persistence.Query;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoDetalleRemoto;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.hibernate.Session;

@Stateless
@Remote(CntContratoDetalleRemoto.class)
public class CntContratoDetalleServicio extends GenericoServicio implements CntContratoDetalleRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntContratoDetalles c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND c.cntContratoSedesId.id = " + (Integer) paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getParametroConsulta2() != null) {//Tecnlogias
                String[] tecnologias = ((String) paramConsulta.getParametroConsulta2()).split(",");
                int contador = 1;
                strQuery += "AND c.maTecnologiaId IN (";
                for (String tec : tecnologias) {
                    strQuery += "" + tec;
                    if (contador < tecnologias.length) {
                        strQuery += " , ";
                    }
                    contador++;
                }
                strQuery += ")";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntContrato.contrato":
                            strQuery += "AND c.cntContratosId.contrato = '" + (Integer) e.getValue() + "' ";
                            break;
                        case "tipoTecnologia":
                            strQuery += "AND c.tipoTecnologia = " + (String) e.getValue() + " ";
                            break;
                        case "maTecnologiaCodigo":
                            strQuery += "AND c.maTecnologiaCodigo = '" + (String) e.getValue() + "' ";
                            break;
                        case "maTecnologiaValor":
                            strQuery += "AND c.maTecnologiaValor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "tipoManualTarifario":
                            strQuery += "AND c.tipoManualTarifario = " + (String) e.getValue() + " ";
                            break;
                        case "porcentajeVariacion":
                            strQuery += "AND c.porcentajeVariacion = " + (String) e.getValue() + " ";
                            break;
                        case "valorContratado":
                            strQuery += "AND c.valorContratado = " + e.getValue() + " ";
                            break;
                        case "valorManual":
                            strQuery += "AND c.valorManual = " + e.getValue() + " ";
                            break;
                        case "cntContratoSede.cntPrestadorSede.codigoHabilitacionSede":
                            strQuery += "AND c.cntContratoSedesId.cntPrestadorSedesId.codigoHabilitacion = '" + e.getValue() + "' ";
                            break;
                        case "cntContratoSede.cntPrestadorSede.cntPrestador.razonSocial":
                            strQuery += "AND c.cntContratoSedesId.cntPrestadorSedesId.cntPrestadoresId.razonSocial LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maTecnologiaId":
                            strQuery += "AND c.maTecnologiaId = " + (Integer) e.getValue() + " ";
                            break;
                        case "maManualTarifarioAgno":
                            strQuery += "AND c.maManualTarifarioAgno = " + e.getValue() + " ";
                            break;
                        case "maServicioHabilitacionCodigo":
                            strQuery += "AND c.maServicioHabilitacionCodigo = '" + e.getValue() + "' ";
                            break;
                        case "maServicioHabilitacionValor":
                            strQuery += "AND c.maServicioHabilitacionValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maManualTarifarioCodigo":
                            strQuery += "AND c.maManualTarifarioCodigo = '" + e.getValue() + "' ";
                            break;
                        case "automaticoManual":
                            strQuery += "AND c.automaticoManual = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += "AND c.activo = " + e.getValue() + " ";
                            break;
                        case "complejidad":
                            strQuery += "AND c.complejidad = " + e.getValue() + " ";
                            break;
                        case "maeAmbitoId":
                            strQuery += "AND c.maeAmbitoId = " + e.getValue() + " ";
                            break;
                    }
                }
            }
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
    public List<CntContratoDetalle> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CntContratoDetalle> listResult = new ArrayList();
        try {
            String strQuery = "SELECT c FROM CntContratoDetalles c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND c.cntContratoSedesId.id = " + (Integer) paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getParametroConsulta2() != null) {//Tecnlogias
                String[] tecnologias = ((String) paramConsulta.getParametroConsulta2()).split(",");
                int contador = 1;
                strQuery += "AND c.maTecnologiaId IN (";
                for (String tec : tecnologias) {
                    strQuery += "" + tec;
                    if (contador < tecnologias.length) {
                        strQuery += " , ";
                    }
                    contador++;
                }
                strQuery += ")";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntContrato.contrato":
                            strQuery += "AND c.cntContratosId.contrato = '" + (Integer) e.getValue() + "' ";
                            break;
                        case "tipoTecnologia":
                            strQuery += "AND c.tipoTecnologia = " + (String) e.getValue() + " ";
                            break;
                        case "maTecnologiaCodigo":
                            strQuery += "AND c.maTecnologiaCodigo = '" + (String) e.getValue() + "' ";
                            break;
                        case "maTecnologiaValor":
                            strQuery += "AND c.maTecnologiaValor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "tipoManualTarifario":
                            strQuery += "AND c.tipoManualTarifario = " + (String) e.getValue() + " ";
                            break;
                        case "porcentajeVariacion":
                            strQuery += "AND c.porcentajeVariacion = " + (String) e.getValue() + " ";
                            break;
                        case "valorContratado":
                            strQuery += "AND c.valorContratado = " + e.getValue() + " ";
                            break;
                        case "valorManual":
                            strQuery += "AND c.valorManual = " + e.getValue() + " ";
                            break;
                        case "maTecnologiaId":
                            strQuery += "AND c.maTecnologiaId = " + (Integer) e.getValue() + " ";
                            break;
                        case "cntPrestadorSedesId":
                            strQuery += "AND c.cntContratoSedesId.cntPrestadorSedesId.id = " + (Integer) e.getValue() + " ";
                            break;
                        case "tecnologias":
                            strQuery += "AND c.maTecnologiaId IN (" + e.getValue() + ") ";
                            break;
                        case "cntContratoSede.cntPrestadorSede.cntPrestador.razonSocial":
                            strQuery += "AND c.cntContratoSedesId.cntPrestadorSedesId.cntPrestadoresId.razonSocial LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "cntContratoSede.cntPrestadorSede.codigoHabilitacionSede":
                            strQuery += "AND c.cntContratoSedesId.cntPrestadorSedesId.codigoHabilitacion = '" + e.getValue() + "' ";
                            break;
                        case "maManualTarifarioAgno":
                            strQuery += "AND c.maManualTarifarioAgno = " + e.getValue() + " ";
                            break;
                        case "maServicioHabilitacionCodigo":
                            strQuery += "AND c.maServicioHabilitacionCodigo = '" + e.getValue() + "' ";
                            break;
                        case "maServicioHabilitacionValor":
                            strQuery += "AND c.maServicioHabilitacionValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maManualTarifarioCodigo":
                            strQuery += "AND c.maManualTarifarioCodigo = '" + e.getValue() + "' ";
                            break;
                        case "automaticoManual":
                            strQuery += "AND c.automaticoManual = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += "AND c.activo = " + e.getValue() + " ";
                            break;
                        case "complejidad":
                            strQuery += "AND c.complejidad = " + e.getValue() + " ";
                            break;
                        case "maeAmbitoId":
                            strQuery += "AND c.maeAmbitoId = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    case "cntContrato.contrato":
                        strQuery += "c.cntContratosId.contrato "
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
            //Query query = getEntityManager().createQuery(strQuery);
            List<CntContratoDetalles> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntContratoDetalles per : list) {
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
    public int consultarCantidadListaMarcaciones(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntContratoDetalles c "
                    + "WHERE c.id > 0 ";
            strQuery += " AND c.cntContratoSedesId.maeModalidadContratoCodigo NOT IN (01,03) ";
            strQuery += " AND c.cntContratosId.maeEstadoContratoCodigo = '01' ";
            strQuery += " AND c.activo = 1 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND c.cntContratoSedesId.id = " + (Integer) paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntContrato.contrato":
                            strQuery += "AND c.cntContratosId.contrato = '" + e.getValue() + "' ";
                            break;
                        case "cntContratoSede.cntPrestadorSede.codigoHabilitacionSede":
                            strQuery += "AND c.cntContratoSedesId.cntPrestadorSedesId.codigoHabilitacion = '" + e.getValue() + "' ";
                            break;
                        case "cntContratoSede.maeModalidadContratoId":
                            strQuery += "AND c.cntContratoSedesId.maeModalidadContratoId = '" + e.getValue() + "' ";
                            break;
                        case "tipoTecnologia":
                            strQuery += "AND c.tipoTecnologia = " + e.getValue() + " ";
                            break;
                        case "maTecnologiaCodigo":
                            strQuery += "AND c.maTecnologiaCodigo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maTecnologiaValor":
                            strQuery += "AND c.maTecnologiaValor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "automaticoManual":
                            strQuery += "AND c.automaticoManual = " + e.getValue() + " ";
                            break;
                        case "automaticoMasivo":
                            strQuery += "AND c.automaticoMasivo = " + e.getValue() + " ";
                            break;
                        case "automaticoInteroperabilidad":
                            strQuery += "AND c.automaticoInteroperabilidad = " + e.getValue() + " ";
                            break;
                        case "preautorizacion":
                            strQuery += "AND c.preautorizacion = " + e.getValue() + " ";
                            break;
                        case "maeAmbitoId":
                            strQuery += "AND c.maeAmbitoId = " + e.getValue() + " ";
                            break;
                    }
                }
            }
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
    public List<CntContratoDetalle> consultarListaMarcaciones(ParamConsulta paramConsulta) throws Exception {
        List<CntContratoDetalle> listResult = new ArrayList();
        try {
            String strQuery = "SELECT c FROM CntContratoDetalles c "
                    + "WHERE c.id > 0 ";
            strQuery += " AND c.cntContratoSedesId.maeModalidadContratoCodigo NOT IN ('01','03') ";
            strQuery += " AND c.cntContratosId.maeEstadoContratoCodigo = '01' ";
            strQuery += " AND c.activo = 1 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND c.cntContratoSedesId.id = " + (Integer) paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntContrato.contrato":
                            strQuery += "AND c.cntContratosId.contrato = '" + e.getValue() + "' ";
                            break;
                        case "cntContratoSede.cntPrestadorSede.codigoHabilitacionSede":
                            strQuery += "AND c.cntContratoSedesId.cntPrestadorSedesId.codigoHabilitacion = '" + e.getValue() + "' ";
                            break;
                        case "cntContratoSede.maeModalidadContratoId":
                            strQuery += "AND c.cntContratoSedesId.maeModalidadContratoId = '" + e.getValue() + "' ";
                            break;
                        case "tipoTecnologia":
                            strQuery += "AND c.tipoTecnologia = " + e.getValue() + " ";
                            break;
                        case "maTecnologiaCodigo":
                            strQuery += "AND c.maTecnologiaCodigo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maTecnologiaValor":
                            strQuery += "AND c.maTecnologiaValor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "automaticoManual":
                            strQuery += "AND c.automaticoManual = " + e.getValue() + " ";
                            break;
                        case "automaticoMasivo":
                            strQuery += "AND c.automaticoMasivo = " + e.getValue() + " ";
                            break;
                        case "automaticoInteroperabilidad":
                            strQuery += "AND c.automaticoInteroperabilidad = " + e.getValue() + " ";
                            break;
                        case "preautorizacion":
                            strQuery += "AND c.preautorizacion = " + e.getValue() + " ";
                            break;
                        case "maeAmbitoId":
                            strQuery += "AND c.maeAmbitoId = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    case "cntContrato.contrato":
                        strQuery += "c.cntContratosId.contrato "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntContratoSede.cntPrestadorSede.codigoHabilitacionSede":
                        strQuery += "c.cntContratoSedesId.cntPrestadorSedesId.codigoHabilitacion "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntContrato.cntContratoSede.maeModalidadContratoId":
                        strQuery += "c.cntContratoSedesId.maeModalidadContratoId "
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
            //Query query = getEntityManager().createQuery(strQuery);
            List<CntContratoDetalles> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntContratoDetalles per : list) {
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
    public int consultarCantidadListaSinAutorizacion(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntContratoDetalles c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND c.cntContratoSedesId.id = " + (Integer) paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getParametroConsulta2() != null) {//Tecnlogias
                String[] tecnologias = ((String) paramConsulta.getParametroConsulta2()).split(",");
                int contador = 1;
                strQuery += "AND c.maTecnologiaId IN (";
                for (String tec : tecnologias) {
                    strQuery += "" + tec;
                    if (contador < tecnologias.length) {
                        strQuery += " , ";
                    }
                    contador++;
                }
                strQuery += ")";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntContrato.contrato":
                            strQuery += "AND c.cntContratosId.contrato = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.maeEstadoContratoCodigo":
                            strQuery += "AND c.cntContratosId.maeEstadoContratoCodigo = '" + e.getValue() + "' ";
                            break;
                        case "tipoTecnologia":
                            strQuery += "AND c.tipoTecnologia = " + e.getValue() + " ";
                            break;
                        case "maTecnologiaCodigo":
                            strQuery += "AND c.maTecnologiaCodigo = '" + (String) e.getValue() + "' ";
                            break;
                        case "maTecnologiaValor":
                            strQuery += "AND c.maTecnologiaValor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "tipoManualTarifario":
                            strQuery += "AND c.tipoManualTarifario = " + (String) e.getValue() + " ";
                            break;
                        case "porcentajeVariacion":
                            strQuery += "AND c.porcentajeVariacion = " + (String) e.getValue() + " ";
                            break;
                        case "valorContratado":
                            strQuery += "AND c.valorContratado = " + e.getValue() + " ";
                            break;
                        case "valorManual":
                            strQuery += "AND c.valorManual = " + e.getValue() + " ";
                            break;
                        case "cntPrestadorSedesId":
                            strQuery += "AND c.cntContratoSedesId.cntPrestadorSedesId.id = " + e.getValue() + " ";
                            break;
                        case "cntContratoSede.cntPrestadorSede.codigoHabilitacionSede":
                            strQuery += "AND c.cntContratoSedesId.cntPrestadorSedesId.codigoHabilitacion = '" + e.getValue() + "' ";
                            break;
                        case "cntContratoSede.cntPrestadorSede.cntPrestador.razonSocial":
                            strQuery += "AND c.cntContratoSedesId.cntPrestadorSedesId.cntPrestadoresId.razonSocial LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "cntContratoSedesId.maeModalidadContratoCodigo":
                            strQuery += "AND c.cntContratoSedesId.maeModalidadContratoCodigo = '" + e.getValue() + "' ";
                            break;
                        case "maTecnologiaId":
                            strQuery += "AND c.maTecnologiaId = " + e.getValue() + " ";
                            break;
                        case "maManualTarifarioAgno":
                            strQuery += "AND c.maManualTarifarioAgno = " + e.getValue() + " ";
                            break;
                        case "maServicioHabilitacionCodigo":
                            strQuery += "AND c.maServicioHabilitacionCodigo = '" + e.getValue() + "' ";
                            break;
                        case "maServicioHabilitacionValor":
                            strQuery += "AND c.maServicioHabilitacionValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maManualTarifarioCodigo":
                            strQuery += "AND c.maManualTarifarioCodigo = '" + e.getValue() + "' ";
                            break;
                        case "automaticoManual":
                            strQuery += "AND c.automaticoManual = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += "AND c.activo = " + e.getValue() + " ";
                            break;
                        case "complejidad":
                            strQuery += "AND c.complejidad = " + e.getValue() + " ";
                            break;
                        case "maeAmbitoId":
                            strQuery += "AND c.maeAmbitoId = " + e.getValue() + " ";
                            break;
                    }
                }
            }
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
    public List<CntContratoDetalle> consultarListaSinAutorizacion(ParamConsulta paramConsulta) throws Exception {
        List<CntContratoDetalle> listResult = new ArrayList();
        try {
            String strQuery = "SELECT c FROM CntContratoDetalles c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND c.cntContratoSedesId.id = " + (Integer) paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getParametroConsulta2() != null) {//Tecnlogias
                String[] tecnologias = ((String) paramConsulta.getParametroConsulta2()).split(",");
                int contador = 1;
                strQuery += "AND c.maTecnologiaId IN (";
                for (String tec : tecnologias) {
                    strQuery += "" + tec;
                    if (contador < tecnologias.length) {
                        strQuery += " , ";
                    }
                    contador++;
                }
                strQuery += ")";
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntContrato.contrato":
                            strQuery += "AND c.cntContratosId.contrato = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.maeEstadoContratoCodigo":
                            strQuery += "AND c.cntContratosId.maeEstadoContratoCodigo = '" + e.getValue() + "' ";
                            break;
                        case "tipoTecnologia":
                            strQuery += "AND c.tipoTecnologia = " + e.getValue() + " ";
                            break;
                        case "maTecnologiaCodigo":
                            strQuery += "AND c.maTecnologiaCodigo = '" + (String) e.getValue() + "' ";
                            break;
                        case "maTecnologiaValor":
                            strQuery += "AND c.maTecnologiaValor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "tipoManualTarifario":
                            strQuery += "AND c.tipoManualTarifario = " + (String) e.getValue() + " ";
                            break;
                        case "porcentajeVariacion":
                            strQuery += "AND c.porcentajeVariacion = " + (String) e.getValue() + " ";
                            break;
                        case "valorContratado":
                            strQuery += "AND c.valorContratado = " + e.getValue() + " ";
                            break;
                        case "valorManual":
                            strQuery += "AND c.valorManual = " + e.getValue() + " ";
                            break;
                        case "maTecnologiaId":
                            strQuery += "AND c.maTecnologiaId = " + e.getValue() + " ";
                            break;
                        case "cntPrestadorSedesId":
                            strQuery += "AND c.cntContratoSedesId.cntPrestadorSedesId.id = " + e.getValue() + " ";
                            break;
                        case "tecnologias":
                            strQuery += "AND c.maTecnologiaId IN (" + e.getValue() + ") ";
                            break;
                        case "cntContratoSede.cntPrestadorSede.cntPrestador.razonSocial":
                            strQuery += "AND c.cntContratoSedesId.cntPrestadorSedesId.cntPrestadoresId.razonSocial LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "cntContratoSede.cntPrestadorSede.codigoHabilitacionSede":
                            strQuery += "AND c.cntContratoSedesId.cntPrestadorSedesId.codigoHabilitacion = '" + e.getValue() + "' ";
                            break;
                        case "cntContratoSedesId.maeModalidadContratoCodigo":
                            strQuery += "AND c.cntContratoSedesId.maeModalidadContratoCodigo = '" + e.getValue() + "' ";
                            break;
                        case "maManualTarifarioAgno":
                            strQuery += "AND c.maManualTarifarioAgno = " + e.getValue() + " ";
                            break;
                        case "maServicioHabilitacionCodigo":
                            strQuery += "AND c.maServicioHabilitacionCodigo = '" + e.getValue() + "' ";
                            break;
                        case "maServicioHabilitacionValor":
                            strQuery += "AND c.maServicioHabilitacionValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maManualTarifarioCodigo":
                            strQuery += "AND c.maManualTarifarioCodigo = '" + e.getValue() + "' ";
                            break;
                        case "automaticoManual":
                            strQuery += "AND c.automaticoManual = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += "AND c.activo = " + e.getValue() + " ";
                            break;
                        case "complejidad":
                            strQuery += "AND c.complejidad = " + e.getValue() + " ";
                            break;
                        case "maeAmbitoId":
                            strQuery += "AND c.maeAmbitoId = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    case "cntContrato.contrato":
                        strQuery += "c.cntContratosId.contrato "
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
            //Query query = getEntityManager().createQuery(strQuery);
            List<CntContratoDetalles> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntContratoDetalles per : list) {
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
    public int cantidadPorTecnologia(int idTecnologia) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntContratoDetalles c "
                    + "WHERE maTecnologiaId = :id_tecnologia ";
            List<CntContratoDetalles> list = getEntityManager().createQuery(strQuery)
                    .setParameter("id_tecnologia", idTecnologia)
                    .getResultList();
            Query query = getEntityManager().createQuery(strQuery);
            cant = (int) (long) query
                    .setParameter("id_tecnologia", idTecnologia)
                    .getSingleResult();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

    @Override
    public List<CntContratoDetalle> consultarListaSinAutorizacionCarga(int tipoTecnologia, int idTecnologia, int idPresetadorSede) throws Exception {
        List<CntContratoDetalle> ObjectResult = new ArrayList<>();
        String sql = "SELECT ccd FROM CntContratoDetalles ccd "
                + "INNER JOIN CntContratoSedes ccs ON ccd.cntContratoSedesId = ccs.id "
                + "INNER JOIN CntPrestadorSedes cps ON ccs.cntPrestadorSedesId = cps.id "
                + "INNER JOIN CntContratos cc ON ccs.cntContratosId = cc.id "
                + "WHERE ccd.id > 0 "
                + "AND ccd.tipoTecnologia = :tipoTecnologia "
                + "AND ccd.maTecnologiaId = :idTecnologia "
                + "AND cps.id = :cntPrestadorSedesId "
                + "AND ccd.activo = :activo "
                + "AND ccs.maeModalidadContratoCodigo = :maeModalidadContratoCodigo "
                + "AND cc.maeEstadoContratoCodigo =:maeEstadoContratoCodigo "
                + "ORDER BY ccd.id DESC";
        try {

            Query query = getEntityManager().createQuery(sql);
            query.setParameter("tipoTecnologia", tipoTecnologia);
            query.setParameter("idTecnologia", idTecnologia);
            query.setParameter("cntPrestadorSedesId", idPresetadorSede);
            query.setParameter("activo", Boolean.TRUE);
            query.setParameter("maeModalidadContratoCodigo", "02");
            query.setParameter("maeEstadoContratoCodigo", "01");
            List<CntContratoDetalles> list = query.getResultList();
            for (CntContratoDetalles item : list) {
                ObjectResult.add(castEntidadNegocioCorto(item));
            }
        } catch (NoResultException e) {
            ObjectResult = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return ObjectResult;
    }

    private CntContratoDetalle castEntidadNegocio(CntContratoDetalles per) {
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
            CntContratoSede sede = new CntContratoSede(per.getCntContratoSedesId().getId());
            sede.setMaeModalidadContratoId(per.getCntContratoSedesId().getMaeModalidadContratoId());
            sede.setMaeModalidadContratoCodigo(per.getCntContratoSedesId().getMaeModalidadContratoCodigo());
            sede.setMaeModalidadContratoValor(per.getCntContratoSedesId().getMaeModalidadContratoValor());
            sede.setCntPrestadorSede(new CntPrestadorSede(per.getCntContratoSedesId().getCntPrestadorSedesId().getId(), per.getCntContratoSedesId().getCntPrestadorSedesId().getCodigoHabilitacion()));
            neg.setCntContratoSede(sede);
        }
        if (per.getCntPrestadorSedesInterdependenciaId() != null) {
            neg.setCntPrestadorSedesInterdependencia(new CntPrestadorSede(per.getCntPrestadorSedesInterdependenciaId().getId()));
        }
        if (per.getCntContratosId() != null) {
            CntContrato contrato = new CntContrato(per.getCntContratosId().getId(), per.getCntContratosId().getContrato());
            if (per.getCntContratosId().getCntPrestadoresId() != null) {
                contrato.setCntPrestador(new CntPrestador(per.getCntContratosId().getCntPrestadoresId().getId(), per.getCntContratosId().getCntPrestadoresId().getRazonSocial()));
            }
            contrato.setEjecucionContratoAutorizado(per.getCntContratosId().getEjecucionContratoAutorizado());
            neg.setCntContrato(contrato);
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
    public CntContratoDetalle consultar(int id) throws Exception {
        CntContratoDetalle objRes = null;
        try {
            objRes = castEntidadNegocio((CntContratoDetalles) getEntityManager().find(CntContratoDetalles.class, id));
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
    public int insertar(CntContratoDetalle obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            //obj.setId(_id);
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
    public void actualizar(CntContratoDetalle obj) throws Exception {
        try {
            CntContratoDetalles detalle = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            //session.update(per);
            String strQuery = "UPDATE CntContratoDetalles a SET ";
            strQuery += " a.automaticoManual = :automaticoManual ,";
            strQuery += " a.automaticoMasivo = :automaticoMasivo ,";
            strQuery += " a.automaticoInteroperabilidad = :automaticoInteroperabilidad ,";
            strQuery += " a.preautorizacion = :preautorizacion ,";
            strQuery += " a.activo = :activo ,";
            strQuery += " a.tipoTecnologia = :tipoTecnologia ,";
            strQuery += " a.maTecnologiaId = :maTecnologiaId ,";
            strQuery += " a.maTecnologiaCodigo = :maTecnologiaCodigo ,";
            strQuery += " a.maTecnologiaValor = :maTecnologiaValor ,";
            strQuery += " a.maServicioHabilitacionId = :maServicioHabilitacionId ,";
            strQuery += " a.maServicioHabilitacionCodigo = :maServicioHabilitacionCodigo ,";
            strQuery += " a.maServicioHabilitacionValor = :maServicioHabilitacionValor ,";
            strQuery += " a.tipoManualTarifario = :tipoManualTarifario ,";
            strQuery += " a.maManualTarifarioId = :maManualTarifarioId ,";
            strQuery += " a.maManualTarifarioCodigo = :maManualTarifarioCodigo ,";
            strQuery += " a.maManualTarifarioValor = :maManualTarifarioValor ,";
            strQuery += " a.maManualTarifarioAgno = :maManualTarifarioAgno ,";
            strQuery += " a.valorManual = :valorManual ,";
            strQuery += " a.valorContratado = :valorContratado ,";
            strQuery += " a.porcentajeVariacion = :porcentajeVariacion ,";
            strQuery += " a.complejidad = :complejidad ,";
            strQuery += " a.observacionIncluye = :observacionIncluye ,";
            strQuery += " a.observacionExcluye = :observacionExcluye ,";
            strQuery += " a.interdependencia = :interdependencia ,";
            strQuery += " a.maeAmbitoId = :maeAmbitoId ,";
            strQuery += " a.maeAmbitoCodigo = :maeAmbitoCodigo ,";
            strQuery += " a.maeAmbitoValor = :maeAmbitoValor ,";
            strQuery += " a.valorMaximoRegulado = :valorMaximoRegulado ,";
            //campos auditoria
            strQuery += " a.usuarioModifica = :usuarioModifica ,";
            strQuery += " a.terminalModifica = :terminalModifica ,";
            //campo fechas
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaModifica = formatoFechaHora.format(obj.getFechaHoraModifica());
            strQuery += " a.fechaHoraModifica = '" + fechaModifica + "',";
            String fechaInicio = formatoFecha.format(obj.getFechaHoraInicio());
            strQuery += " a.fechaHoraInicio = '" + fechaInicio + "' ";
            if (obj.getFechaHoraFin() != null) {
                String fechaFin = formatoFecha.format(obj.getFechaHoraFin());
                strQuery += ", a.fechaHoraFin = '" + fechaFin + "' ";
            }
            //campos objetos
            if (obj.getCntPrestadorSedesInterdependencia() != null) {
                strQuery += ", a.cntPrestadorSedesInterdependenciaId.id = " + obj.getCntPrestadorSedesInterdependencia().getId() + " ";
            }

            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(detalle);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarMarcacion(CntContratoDetalle obj) throws Exception {
        try {
            CntContratoDetalles detalle = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            //session.update(per);
            String strQuery = "UPDATE CntContratoDetalles a SET ";
            strQuery += " a.automaticoManual = :automaticoManual ,";
            strQuery += " a.automaticoMasivo = :automaticoMasivo ,";
            strQuery += " a.automaticoInteroperabilidad = :automaticoInteroperabilidad ,";
            strQuery += " a.preautorizacion = :preautorizacion ,";
            //campos auditoria
            strQuery += " a.usuarioModifica = :usuarioModifica ,";
            strQuery += " a.terminalModifica = :terminalModifica ,";
            //campo fechas
            SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaModifica = formatoFechaHora.format(obj.getFechaHoraModifica());
            strQuery += " a.fechaHoraModifica = '" + fechaModifica + "' ";
            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(detalle);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CntContratoDetalle eliminar(int id) throws Exception {
        CntContratoDetalle obj = null;
        try {
            CntContratoDetalles ent = getEntityManager().find(CntContratoDetalles.class, id);
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
    public List<CntContratoDetalle> consultarTodos() throws Exception {
        List<CntContratoDetalle> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntContratoDetalles p "
                    + "ORDER BY p.id ";
            List<CntContratoDetalles> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntContratoDetalles per : list) {
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

    public static CntContratoDetalles castNegocioEntidad(CntContratoDetalle obj) {
        CntContratoDetalles per = new CntContratoDetalles();
        per.setId(obj.getId());
        per.setActivo(obj.getActivo());
        per.setTipoTecnologia(obj.getTipoTecnologia());
        per.setMaTecnologiaId(obj.getMaTecnologiaId());
        per.setMaTecnologiaCodigo(obj.getMaTecnologiaCodigo());
        per.setMaTecnologiaValor(obj.getMaTecnologiaValor());
        per.setMaServicioHabilitacionId(obj.getMaServicioHabilitacionId());
        per.setMaServicioHabilitacionCodigo(obj.getMaServicioHabilitacionCodigo());
        per.setMaServicioHabilitacionValor(obj.getMaServicioHabilitacionValor());
        per.setTipoManualTarifario(obj.getTipoManualTarifario());
        per.setMaManualTarifarioId(obj.getMaManualTarifarioId());
        per.setMaManualTarifarioCodigo(obj.getMaManualTarifarioCodigo());
        per.setMaManualTarifarioValor(obj.getMaManualTarifarioValor());
        per.setMaManualTarifarioAgno(obj.getMaManualTarifarioAgno());
        per.setValorManual(obj.getValorManual());
        per.setValorContratado(obj.getValorContratado());
        per.setPorcentajeVariacion(obj.getPorcentajeVariacion());
        per.setComplejidad(obj.getComplejidad());
        per.setObservacionIncluye(obj.getObservacionIncluye());
        per.setObservacionExcluye(obj.getObservacionExcluye());
        per.setInterdependencia(obj.getInterdependencia());
        per.setMaeAmbitoId(obj.getMaeAmbitoId());
        per.setMaeAmbitoCodigo(obj.getMaeAmbitoCodigo());
        per.setMaeAmbitoValor(obj.getMaeAmbitoValor());
        per.setFechaHoraInicio(obj.getFechaHoraInicio());
        per.setFechaHoraFin(obj.getFechaHoraFin());
        //2022-10-05 jyperez nuevos campos
        per.setAutomaticoManual(obj.isAutomaticoManual());
        per.setAutomaticoMasivo(obj.isAutomaticoMasivo());
        per.setAutomaticoInteroperabilidad(obj.isAutomaticoInteroperabilidad());
        per.setPreautorizacion(obj.isPreautorizacion());
        per.setValorMaximoRegulado(obj.getValorMaximoRegulado());
        //objetos
        if (obj.getCntContratoSede() != null) {
            per.setCntContratoSedesId(new CntContratoSedes(obj.getCntContratoSede().getId()));
        }
        if (obj.getCntPrestadorSedesInterdependencia() != null && obj.getCntPrestadorSedesInterdependencia().getId() != null) {
            per.setCntPrestadorSedesInterdependenciaId(new CntPrestadorSedes(obj.getCntPrestadorSedesInterdependencia().getId()));
        }
        if (obj.getCntContrato() != null) {
            per.setCntContratosId(new CntContratos(obj.getCntContrato().getId()));
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

    private CntContratoDetalle castEntidadNegocioMinimo(CntContratoDetalles ent) {
        CntContratoDetalle neg = new CntContratoDetalle();
        neg.setMaTecnologiaId(ent.getMaTecnologiaId());
        neg.setId(ent.getId());
        return neg;
    }

    @Override
    public CntContratoDetalle consultarPorContratoSedeTecnologiaTipoYServicioHabilitacion(int idContrato, int idContratoSede, int idTecnologia, int idServicioHabilitacion, int tipoTecnologia) throws java.lang.Exception {
        CntContratoDetalle objRes = null;
        int i = 0;
        try {
            String strQuery = "FROM CntContratoDetalles p "
                    + "WHERE p.cntContratosId.id  = " + idContrato
                    + " AND p.cntContratoSedesId = " + idContratoSede
                    + " AND p.maTecnologiaId = " + idTecnologia
                    + " AND p.tipoTecnologia = " + tipoTecnologia
                    + " AND p.maServicioHabilitacionId = " + idServicioHabilitacion;
            List<CntContratoDetalles> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntContratoDetalles per : list) {
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
    public CntContratoDetalle consultarPorSedeTecnologiaYTipo(int tecnologiaId, int tipoTecnologia, int sedeEntregaId) throws Exception {
        CntContratoDetalle objectResult = new CntContratoDetalle();
        String sql = "SELECT ccd FROM CntContratoDetalles ccd "
                + "INNER JOIN CntContratoSedes ccs ON ccd.cntContratoSedesId = ccs.id "
                + "INNER JOIN CntPrestadorSedes cps ON ccs.cntPrestadorSedesId = cps.id "
                + "INNER JOIN CntContratos cc ON ccs.cntContratosId = cc.id "
                + "WHERE ccd.tipoTecnologia = :tipoTecnologia "
                + "AND ccd.maTecnologiaId = :tecnologiaId "
                + "AND cps.id = :sedeEntregaId "
                + "AND cc.maeEstadoContratoCodigo = '01' ";
        try {
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("tipoTecnologia", tipoTecnologia);
            query.setParameter("tecnologiaId", tecnologiaId);
            query.setParameter("sedeEntregaId", sedeEntregaId);
            objectResult = castEntidadNegocioCorto((CntContratoDetalles) query.getSingleResult());
        } catch (NoResultException e) {
            objectResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objectResult;
    }

    @Override
    public CntContratoDetalle consultarPorContratoSedeTecnologiaYTipoTecnologia(int idContrato, int idContratoSede, int idTecnologia, int tipoTecnologia) throws java.lang.Exception {
        CntContratoDetalle objRes = null;
        int i = 0;
        try {
            String strQuery = "FROM CntContratoDetalles p "
                    + "WHERE p.cntContratosId.id  = " + idContrato
                    + " AND p.cntContratoSedesId = " + idContratoSede
                    + " AND p.maTecnologiaId = " + idTecnologia
                    + " AND p.tipoTecnologia = " + tipoTecnologia;
            List<CntContratoDetalles> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntContratoDetalles per : list) {
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
    public int consultarCantidadListaTecnologias(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntContratoDetalles c "
                    + "WHERE c.id > 0 ";
            strQuery += "AND c.cntContratosId.maeEstadoContratoCodigo = '01' ";
            if (paramConsulta.getParametroConsulta1() != null) {//TipoTecnologia
                strQuery += "AND c.tipoTecnologia = " + paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getParametroConsulta2() != null && !paramConsulta.getParametroConsulta2().equals("")) {//CodigoTecnologia
                strQuery += "AND c.maTecnologiaCodigo LIKE '%" + (String) paramConsulta.getParametroConsulta2() + "%' ";
            }
            if (paramConsulta.getParametroConsulta3() != null && !paramConsulta.getParametroConsulta3().equals("")) {//ValorTecnologia
                strQuery += "AND c.maTecnologiaValor LIKE '%" + (String) paramConsulta.getParametroConsulta3() + "%' ";
            }
            //2023-02-10 jyperez se incluyen los  nuevos parámetros de NIT, fecha inicial y fecha Final de contrato
            if (paramConsulta.getParametroConsulta4() != null && !paramConsulta.getParametroConsulta4().equals("")) {//NIT
                strQuery += "AND c.cntContratosId.cntPrestadoresId.numeroDocumento = '" + (String) paramConsulta.getParametroConsulta4() + "' ";
            }
            if (paramConsulta.getParametroConsulta5() != null && !paramConsulta.getParametroConsulta5().equals("")) {//Fecha Inicial
                strQuery += "AND c.fechaHoraInicio > '" + (String) paramConsulta.getParametroConsulta5() + "' ";
            }
            if (paramConsulta.getParametroConsulta6() != null && !paramConsulta.getParametroConsulta6().equals("")) {//Fecha Final
                strQuery += "AND c.fechaHoraFin < '" + (String) paramConsulta.getParametroConsulta6() + "' ";
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntContrato.contrato":
                            strQuery += "AND c.cntContratosId.contrato = '" + e.getValue() + "' ";
                            break;
                        case "tipoTecnologia":
                            strQuery += "AND c.tipoTecnologia = " + (String) e.getValue() + " ";
                            break;
                        case "maTecnologiaCodigo":
                            strQuery += "AND c.maTecnologiaCodigo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maTecnologiaValor":
                            strQuery += "AND c.maTecnologiaValor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "tipoManualTarifario":
                            strQuery += "AND c.tipoManualTarifario = " + (String) e.getValue() + " ";
                            break;
                        case "maManualTarifarioValor":
                            strQuery += "AND c.maManualTarifarioValor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "porcentajeVariacion":
                            strQuery += "AND c.porcentajeVariacion = " + (String) e.getValue() + " ";
                            break;
                        case "valorContratado":
                            strQuery += "AND c.valorContratado = " + e.getValue() + " ";
                            break;
                        case "valorManual":
                            strQuery += "AND c.valorManual = " + e.getValue() + " ";
                            break;
                        case "cntContratoSede.cntPrestadorSede.codigoHabilitacionSede":
                            strQuery += "AND c.cntContratoSedesId.cntPrestadorSedesId.codigoHabilitacion = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.cntPrestador.razonSocial":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.razonSocial LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maTecnologiaId":
                            strQuery += "AND c.maTecnologiaId = " + (Integer) e.getValue() + " ";
                            break;
                        case "maManualTarifarioAgno":
                            strQuery += "AND c.maManualTarifarioAgno = " + e.getValue() + " ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND c.fechaHoraInicio = '" + e.getValue() + "' ";
                            break;
                        case "fechaHoraFin":
                            strQuery += "AND c.fechaHoraFin = '" + e.getValue() + "' ";
                            break;
                        case "automaticoManual":
                            strQuery += "AND c.automaticoManual = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += "AND c.activo = " + e.getValue() + " ";
                            break;
                    }
                }
            }
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
    public List<CntContratoDetalle> consultarListaTecnologias(ParamConsulta paramConsulta) throws Exception {
        List<CntContratoDetalle> listResult = new ArrayList();
        try {
            String strQuery = "SELECT c FROM CntContratoDetalles c "
                    + "WHERE c.id > 0 ";
            strQuery += "AND c.cntContratosId.maeEstadoContratoCodigo = '01' ";
            if (paramConsulta.getParametroConsulta1() != null) {//TipoTecnologia
                strQuery += "AND c.tipoTecnologia = " + paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getParametroConsulta2() != null && !paramConsulta.getParametroConsulta2().equals("")) {//CodigoTecnologia
                strQuery += "AND c.maTecnologiaCodigo LIKE '%" + (String) paramConsulta.getParametroConsulta2() + "%' ";
            }
            if (paramConsulta.getParametroConsulta3() != null && !paramConsulta.getParametroConsulta3().equals("")) {//ValorTecnologia
                strQuery += "AND c.maTecnologiaValor LIKE '%" + (String) paramConsulta.getParametroConsulta3() + "%' ";
            }
            //2023-02-10 jyperez se incluyen los  nuevos parámetros de NIT, fecha inicial y fecha Final de contrato
            if (paramConsulta.getParametroConsulta4() != null && !paramConsulta.getParametroConsulta4().equals("")) {//NIT
                strQuery += "AND c.cntContratosId.cntPrestadoresId.numeroDocumento = '" + (String) paramConsulta.getParametroConsulta4() + "' ";
            }
            if (paramConsulta.getParametroConsulta5() != null && !paramConsulta.getParametroConsulta5().equals("")) {//Fecha Inicial
                strQuery += "AND c.fechaHoraInicio > '" + (String) paramConsulta.getParametroConsulta5() + "' ";
            }
            if (paramConsulta.getParametroConsulta6() != null && !paramConsulta.getParametroConsulta6().equals("")) {//Fecha Final
                strQuery += "AND c.fechaHoraFin < '" + (String) paramConsulta.getParametroConsulta6() + "' ";
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntContrato.contrato":
                            strQuery += "AND c.cntContratosId.contrato = '" + e.getValue() + "' ";
                            break;
                        case "tipoTecnologia":
                            strQuery += "AND c.tipoTecnologia = " + (String) e.getValue() + " ";
                            break;
                        case "maTecnologiaCodigo":
                            strQuery += "AND c.maTecnologiaCodigo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maTecnologiaValor":
                            strQuery += "AND c.maTecnologiaValor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "tipoManualTarifario":
                            strQuery += "AND c.tipoManualTarifario = " + (String) e.getValue() + " ";
                            break;
                        case "maManualTarifarioValor":
                            strQuery += "AND c.maManualTarifarioValor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "porcentajeVariacion":
                            strQuery += "AND c.porcentajeVariacion = " + (String) e.getValue() + " ";
                            break;
                        case "valorContratado":
                            strQuery += "AND c.valorContratado = " + e.getValue() + " ";
                            break;
                        case "valorManual":
                            strQuery += "AND c.valorManual = " + e.getValue() + " ";
                            break;
                        case "cntContratoSede.cntPrestadorSede.codigoHabilitacionSede":
                            strQuery += "AND c.cntContratoSedesId.cntPrestadorSedesId.codigoHabilitacion = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.cntPrestador.razonSocial":
                            strQuery += "AND c.cntContratosId.cntPrestadoresId.razonSocial LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maTecnologiaId":
                            strQuery += "AND c.maTecnologiaId = " + (Integer) e.getValue() + " ";
                            break;
                        case "maManualTarifarioAgno":
                            strQuery += "AND c.maManualTarifarioAgno = " + e.getValue() + " ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND c.fechaHoraInicio = '" + e.getValue() + "' ";
                            break;
                        case "fechaHoraFin":
                            strQuery += "AND c.fechaHoraFin = '" + e.getValue() + "' ";
                            break;
                        case "automaticoManual":
                            strQuery += "AND c.automaticoManual = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += "AND c.activo = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    case "cntContrato.contrato":
                        strQuery += "c.cntContratosId.contrato "
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
            //Query query = getEntityManager().createQuery(strQuery);
            List<CntContratoDetalles> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntContratoDetalles per : list) {
                listResult.add(castEntidadNegocioCorto(per));
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
    public List<CntContrato> consultarPorTecnologia(int tipoTecnologia, int idTecnologia) throws java.lang.Exception {
        List<CntContrato> listResult = new ArrayList();
        HashMap<String, CntContrato> hashContrato = new HashMap();
        Date fechaActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String strQuery = "FROM CntContratoDetalles c "
                    + "WHERE c.tipoTecnologia = " + tipoTecnologia + " "
                    + "AND c.maTecnologiaId = " + idTecnologia + " "
                    //2021-05-28 jyperez INC 809 se incluye validación de contrato detalle activo.
                    + "AND c.activo = 1 "
                    //2021-09-02 jyperez Req Ajuste validación Inactivar Maestras
                    + "AND c.cntContratosId.maeEstadoContratoCodigo = '01' " //contrato vigente
                    + "AND c.cntContratosId.fechaFin >= '" + sdf.format(fechaActual) + "'"; // con fecha fin mayor o igual a la actual
            List<CntContratoDetalles> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntContratoDetalles per : list) {
                CntContratoDetalle obj = castEntidadNegocio(per);
                if (hashContrato.get(obj.getCntContrato().getContrato()) == null) {
                    listResult.add(obj.getCntContrato());
                    hashContrato.put(obj.getCntContrato().getContrato(), obj.getCntContrato());
                }
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
    public CntContratoDetalle consultarPorSedeTecnologiaTipoYServicioHabilitacion(int idPrestadorSede, String codigoTecnologia, String codigoServicioHabilitacion, int tipoTecnologia, String modalidad) throws java.lang.Exception {
        CntContratoDetalle objRes = null;
        try {
            Date fechaActual = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaActualStr = sdf.format(fechaActual);
            String strQuery = "FROM CntContratoDetalles p "
                    + "WHERE P.cntContratoSedesId.cntPrestadorSedesId.id  =:cntPrestadorSedesId"
                    + " AND p.cntContratoSedesId.maeModalidadContratoCodigo in( " + modalidad + ")"
                    + " AND P.maTecnologiaCodigo in(" + codigoTecnologia + ")"
                    + " AND p.tipoTecnologia =:tipoTecnologia"
                    + " AND p.maServicioHabilitacionCodigo in( " + codigoServicioHabilitacion + ")"
                    + " AND p.activo = 1"
                    + " AND p.fechaHoraInicio <= '" + fechaActualStr + "'"//tecnologia con fecha vigente
                    + " AND (p.fechaHoraFin IS NULL or p.fechaHoraFin >= '" + fechaActualStr + "')"
                    + " AND p.cntContratoSedesId.cntContratosId.maeEstadoContratoCodigo = '01' " //contrato vigente
                    + " AND p.cntContratoSedesId.cntContratosId.fechaFin >= '" + fechaActualStr + "'"// fecha contrato vigente
                    + " AND p.cntContratoSedesId.cntContratosId.fechaInicio <= '" + fechaActualStr + "'";
            List<CntContratoDetalles> list = getEntityManager().createQuery(strQuery)
                    .setParameter("cntPrestadorSedesId", idPrestadorSede)
                    .setParameter("tipoTecnologia", tipoTecnologia)
                    .getResultList();
            for (CntContratoDetalles per : list) {
                objRes = castEntidadNegocio(per);
                break;
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

    private CntContratoDetalle castEntidadNegocioCorto(CntContratoDetalles per) {
        CntContratoDetalle neg = new CntContratoDetalle();
        neg.setId(per.getId());
        //neg.setActivo(per.getActivo());
        neg.setTipoTecnologia(per.getTipoTecnologia());
        neg.setMaTecnologiaId(per.getMaTecnologiaId());
        neg.setMaTecnologiaCodigo(per.getMaTecnologiaCodigo());
        neg.setMaTecnologiaValor(per.getMaTecnologiaValor());
        neg.setMaServicioHabilitacionId(per.getMaServicioHabilitacionId());
        neg.setMaServicioHabilitacionCodigo(per.getMaServicioHabilitacionCodigo());
        neg.setMaServicioHabilitacionValor(per.getMaServicioHabilitacionValor());
        neg.setTipoManualTarifario(per.getTipoManualTarifario());
        //neg.setMaManualTarifarioId(per.getMaManualTarifarioId());
        //neg.setMaManualTarifarioCodigo(per.getMaManualTarifarioCodigo());
        //neg.setMaManualTarifarioValor(per.getMaManualTarifarioValor());
        neg.setMaManualTarifarioAgno(per.getMaManualTarifarioAgno());
        neg.setValorManual(per.getValorManual());
        neg.setValorContratado(per.getValorContratado());
        neg.setPorcentajeVariacion(per.getPorcentajeVariacion());
        //neg.setComplejidad(per.getComplejidad());
        //neg.setObservacionIncluye(per.getObservacionIncluye());
        //neg.setObservacionExcluye(per.getObservacionExcluye());
        //neg.setInterdependencia(per.getInterdependencia());
        //neg.setMaeAmbitoId(per.getMaeAmbitoId());
        //neg.setMaeAmbitoCodigo(per.getMaeAmbitoCodigo());
        //neg.setMaeAmbitoValor(per.getMaeAmbitoValor());
        neg.setFechaHoraInicio(per.getFechaHoraInicio());
        //2022-10-05 jyperez nuevos campos
        neg.setAutomaticoManual(per.getAutomaticoManual());
        neg.setAutomaticoMasivo(per.getAutomaticoMasivo());
        neg.setAutomaticoInteroperabilidad(per.getAutomaticoInteroperabilidad());
        neg.setPreautorizacion(per.getPreautorizacion());
        neg.setFechaHoraFin(per.getFechaHoraFin());
        neg.setActivo(per.getActivo());
        //objetos
        if (per.getCntContratoSedesId() != null) {
            neg.setCntContratoSede(new CntContratoSede(per.getCntContratoSedesId().getId()));
        }
        /*
        if (per.getCntPrestadorSedesInterdependenciaId() != null) {
            neg.setCntPrestadorSedesInterdependencia(new CntPrestadorSede(per.getCntPrestadorSedesInterdependenciaId().getId()));
        }*/
        if (per.getCntContratosId() != null) {
            CntContrato contrato = new CntContrato(per.getCntContratosId().getId(), per.getCntContratosId().getContrato(), per.getCntContratosId().getMaeEstadoContratoValor());
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
        neg.setFechaHoraModifica(per.getFechaHoraModifica());
        return neg;
    }

    @Override
    public List<CntContratoDetalle> consultarPorContratoSede(int idContratoSede) throws java.lang.Exception {
        List<CntContratoDetalle> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntContratoDetalles c "
                    + "WHERE c.cntContratoSedesId.id = " + idContratoSede + " ";
            List<CntContratoDetalles> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntContratoDetalles per : list) {
                CntContratoDetalle obj = castEntidadNegocioLargo(per);
                listResult.add(obj);
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

    private CntContratoDetalle castEntidadNegocioLargo(CntContratoDetalles per) {
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
            neg.setCntPrestadorSedesInterdependencia(new CntPrestadorSede(per.getCntPrestadorSedesInterdependenciaId().getId(), per.getCntPrestadorSedesInterdependenciaId().getCodigoHabilitacion()));
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
        neg.setFechaHoraModifica(per.getFechaHoraModifica());
        return neg;
    }

    @Override
    public CntContratoDetalle consultar(int idContrato, int idContratoSede, int idTecnologia, int idServicioHabilitacion, int tipoTecnologia, int tipoManualTarifario, Integer idManualTarifario, Integer anioSoat, Integer idAmbito) throws java.lang.Exception {
        CntContratoDetalle objRes = null;
        int i = 0;
        try {
            String strQuery = "FROM CntContratoDetalles p "
                    + "WHERE p.cntContratosId.id  = " + idContrato
                    + " AND p.cntContratoSedesId = " + idContratoSede
                    + " AND p.maTecnologiaId = " + idTecnologia
                    + " AND p.tipoTecnologia = " + tipoTecnologia
                    + " AND p.maServicioHabilitacionId = " + idServicioHabilitacion
                    + " AND p.tipoManualTarifario = " + tipoManualTarifario;
            //validamos los campos que son Integer, teniendo en cuenta que pueden venir nulos.
            // si llegan nulos no se adicionaran a la consulta
            if (idManualTarifario != null) {
                strQuery += " AND p.maManualTarifarioId = " + idManualTarifario;
            }
            if (anioSoat != null) {
                strQuery += " AND p.maManualTarifarioAgno = " + anioSoat;
            }
            if (idAmbito != null) {
                strQuery += " AND p.maeAmbitoId = " + idAmbito;
            }

            List<CntContratoDetalles> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntContratoDetalles per : list) {
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
    public CntContratoDetalle consultar(int idContrato, int idContratoSede, int idTecnologia, int tipoTecnologia, int tipoManualTarifario, Integer idManualTarifario, Integer anioSoat, Integer idAmbito) throws java.lang.Exception {
        CntContratoDetalle objRes = null;
        int i = 0;
        try {
            String strQuery = "FROM CntContratoDetalles p "
                    + "WHERE p.cntContratosId.id  = " + idContrato
                    + " AND p.cntContratoSedesId = " + idContratoSede
                    + " AND p.maTecnologiaId = " + idTecnologia
                    + " AND p.tipoTecnologia = " + tipoTecnologia
                    + " AND p.tipoManualTarifario = " + tipoManualTarifario;
            //validamos los campos que son Integer, teniendo en cuenta que pueden venir nulos.
            // si llegan nulos no se adicionaran a la consulta
            if (idManualTarifario != null) {
                strQuery += " AND p.maManualTarifarioId = " + idManualTarifario;
            }
            if (anioSoat != null) {
                strQuery += " AND p.maManualTarifarioAgno = " + anioSoat;
            }
            if (idAmbito != null) {
                strQuery += " AND p.maeAmbitoId = " + idAmbito;
            }

            List<CntContratoDetalles> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntContratoDetalles per : list) {
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
    public void actualizarProrrogaCntContratoDetalles(int idContrato, Date FechaFinContrato) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            //session.update(per);
            String strQuery = "UPDATE CntContratoDetalles a SET ";
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            String fechaFin = formatoFecha.format(FechaFinContrato);
            strQuery += " a.fechaHoraFin = '" + fechaFin + "' ";
            //campos objetos

            strQuery += " WHERE a.cntContratosId.id = " + idContrato + " AND a.activo = 1 ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public int consultarCantidadPorTipoTecnologiaYPrestadorSede(int tipoTecnologia, String codigoTecnologia,
            int cntPrestadorSedeId, String maeModalidadContratoCodigo) throws Exception {
        int cantidad = 0;
        try {

            //Session session = getEntityManager().unwrap(Session.class);
            //session.update(per);
            String strQuery = "SELECT COUNT(1) FROM CntContratoDetalles cdt ";
            strQuery += " WHERE cdt.tipoTecnologia = " + tipoTecnologia + " "
                    + " AND cdt.maTecnologiaCodigo = '" + codigoTecnologia + "' "
                    + " AND cdt.activo = 1 "
                    + " AND cdt.cntContratoSedesId.cntPrestadorSedesId.id = " + cntPrestadorSedeId + " "
                    + " AND cdt.cntContratoSedesId.maeModalidadContratoCodigo = '" + maeModalidadContratoCodigo + "' "
                    + " AND cdt.cntContratosId.activo = 1 "
                    + " AND cdt.cntContratosId.maeEstadoContratoCodigo = '01' ";

            //org.hibernate.Query query = session.createQuery(strQuery);
            cantidad = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            cantidad = 0;
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
        return cantidad;
    }
}
