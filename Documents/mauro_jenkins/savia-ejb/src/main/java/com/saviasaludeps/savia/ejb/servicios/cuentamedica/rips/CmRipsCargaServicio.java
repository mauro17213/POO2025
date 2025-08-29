package com.saviasaludeps.savia.ejb.servicios.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAcConsulta;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAdServiciosAgrupado;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAhHospitalizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAmMedicamento;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAnRecienNacido;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAtOtrosServicio;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAuUrgencia;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCargaAnexo;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmRipsCargaRemoto;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAfFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsApProcedimiento;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCargaDetalleDTO;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCargaEstado;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCtControlObj;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsEstructuraError;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsSuceso;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsUsUsuario;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargas;
import com.saviasaludeps.savia.ejb.entidades.CmRipsEstructuraErrores;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaUsUsuarios;
import com.saviasaludeps.savia.ejb.entidades.CmRipsAfFacturas;
import com.saviasaludeps.savia.ejb.entidades.CmRipsAcConsultas;
import com.saviasaludeps.savia.ejb.entidades.CmRipsAmMedicamentos;
import com.saviasaludeps.savia.ejb.entidades.CmRipsAtOtrosServicios;
import com.saviasaludeps.savia.ejb.entidades.CmRipsApProcedimientos;
import com.saviasaludeps.savia.ejb.entidades.CmRipsAnRecienNacidos;
import com.saviasaludeps.savia.ejb.entidades.CmRipsAhHospitalizaciones;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCtControl;
import com.saviasaludeps.savia.ejb.entidades.CmRipsUsUsuarios;
import com.saviasaludeps.savia.ejb.entidades.CmRipsAdServiciosAgrupados;
import com.saviasaludeps.savia.ejb.entidades.CmRipsAuUrgencias;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaAnexos;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaAfFacturas;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaAcConsultas;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaAmMedicamentos;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaAtOtrosServicios;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaApProcedimientos;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaAnRecienNacidos;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaAhHospitalizaciones;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaCtControl;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaEstados;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaAdServiciosAgrupados;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaAuUrgencias;
import com.saviasaludeps.savia.ejb.entidades.CmRipsSucesos;
import com.saviasaludeps.savia.ejb.entidades.CntContratos;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
@Remote(CmRipsCargaRemoto.class)
public class CmRipsCargaServicio extends GenericoServicio implements CmRipsCargaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c.id) FROM CmRipsCargas c LEFT JOIN CntContratos cntc ON c.cntContratosId = cntc.id "
                    + " WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "numeroCuenta":
                            strQuery += "AND c.numeroCuenta = " + (String) e.getValue() + " ";
                            break;
                        case "id":
                            strQuery += "AND c.id = " + e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND c.estado = " + e.getValue() + " ";
                            break;
                        case "gnPrestadorSede.nombreSede":
                            strQuery += "AND c.gnPrestadorSedesId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "gnPrestadorSede.cntPrestador.numeroDocumento":
                            strQuery += "AND c.gnPrestadorSedesId.cntPrestadoresId.numeroDocumento = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.id":
                            strQuery += "AND c.cntContratosId = " + (String) e.getValue() + " ";
                            break;
                        case "cntContrato.contrato":
                            strQuery += "AND cntc.contrato = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaPrestacion":
                            strQuery += "AND c.fechaPrestacion = " + e.getValue() + " ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += "AND c.fechaHoraCrea = " + e.getValue() + " ";
                            break;
                        case "maeContratoModalidadValor":
                            strQuery += "AND c.maeContratoModalidadValor = '" + e.getValue() + "' ";
                            break;
                        case "maeRegimenValor":
                            strQuery += "AND c.maeRegimenValor = '" + e.getValue() + "' ";
                            break;
                        case "pbs":
                            strQuery += " AND c.pbs = " + e.getValue() + " ";
                            break;
                        case "camaFija":
                            strQuery += " AND c.camaFija = " + e.getValue() + " ";
                            break;
                        case "usuarioAudita":
                            strQuery += " AND c.usuarioAudita like '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            //SI ES UNA IPS SOLO LISTAR LOS CARGUES DEL PRESTADOR
            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += "AND c.gnPrestadorSedesId.cntPrestadoresId.id = :id_prestador ";
            }
            //RANGO DE FECHA CARGA
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND c.fechaHoraInicio >= :fh_inicio AND c.fechaHoraFin <= :fh_fin ";
            } else if (paramConsulta.getParametroConsulta1() != null && paramConsulta.getParametroConsulta2() == null) {
                strQuery += "AND c.fechaHoraInicio >= :fh_inicio ";
            } else if (paramConsulta.getParametroConsulta2() != null && paramConsulta.getParametroConsulta1() == null) {
                strQuery += "AND c.fechaHoraFin <= :fh_fin ";
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getParametroConsulta3() != null) {
                query.setParameter("id_prestador", paramConsulta.getParametroConsulta3());
            }
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fh_inicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
                query.setParameter("fh_fin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta1() != null && paramConsulta.getParametroConsulta2() == null) {
                query.setParameter("fh_inicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta2() != null && paramConsulta.getParametroConsulta1() == null) {
                query.setParameter("fh_fin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            }
            cant = (int) (long) query
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
    public List<CmRipsCarga> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmRipsCarga> listResult = new ArrayList();
        try {
            String strQuery = "SELECT c, cntc FROM CmRipsCargas c LEFT JOIN CntContratos cntc ON c.cntContratosId = cntc.id "
                    + " WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "numeroCuenta":
                            strQuery += "AND c.numeroCuenta = " + (String) e.getValue() + " ";
                            break;
                        case "id":
                            strQuery += "AND c.id = " + e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND c.estado = " + e.getValue() + " ";
                            break;
                        case "gnPrestadorSede.nombreSede":
                            strQuery += "AND c.gnPrestadorSedesId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "gnPrestadorSede.cntPrestador.numeroDocumento":
                            strQuery += "AND c.gnPrestadorSedesId.cntPrestadoresId.numeroDocumento = '" + e.getValue() + "' ";
                            break;
                        case "cntContrato.id":
                            strQuery += "AND c.cntContratosId = " + (String) e.getValue() + " ";
                            break;
                       case "cntContrato.contrato":
                            strQuery += "AND cntc.contrato = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaPrestacion":
                            strQuery += "AND c.fechaPrestacion > " + e.getValue() + " ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += "AND c.fechaHoraCrea > " + e.getValue() + " ";
                            break;
                        case "maeContratoModalidadValor":
                            strQuery += "AND c.maeContratoModalidadValor = '" + e.getValue() + "' ";
                            break;
                        case "maeRegimenValor":
                            strQuery += "AND c.maeRegimenValor = '" + e.getValue() + "' ";
                            break;
                        case "pbs":
                            strQuery += " AND c.pbs = " + e.getValue() + " ";
                            break;
                        case "camaFija":
                            strQuery += " AND c.camaFija = " + e.getValue() + " ";
                            break;
                        case "usuarioAudita":
                            strQuery += " AND c.usuarioAudita like '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            //SI ES UNA IPS SOLO LISTAR LOS CARGUES DEL PRESTADOR
            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += "AND c.gnPrestadorSedesId.cntPrestadoresId.id = :id_prestador ";
            }
            //RANGO DE FECHA CARGA
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND c.fechaHoraInicio >= :fh_inicio AND c.fechaHoraFin <= :fh_fin ";
            } else if (paramConsulta.getParametroConsulta1() != null && paramConsulta.getParametroConsulta2() == null) {
                strQuery += "AND c.fechaHoraInicio >= :fh_inicio ";
            } else if (paramConsulta.getParametroConsulta2() != null && paramConsulta.getParametroConsulta1() == null) {
                strQuery += "AND c.fechaHoraFin <= :fh_fin ";
            }
            strQuery += "ORDER BY c.id desc";
            Query query = getEntityManager().createQuery(strQuery);
            //SI ES UNA IPS SOLO LISTAR LOS CARGUES DEL PRESTADOR
            if (paramConsulta.getParametroConsulta3() != null) {
                query.setParameter("id_prestador", paramConsulta.getParametroConsulta3());
            }
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fh_inicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
                query.setParameter("fh_fin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta1() != null && paramConsulta.getParametroConsulta2() == null) {
                query.setParameter("fh_inicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta2() != null && paramConsulta.getParametroConsulta1() == null) {
                query.setParameter("fh_fin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            }
            
              List<Object[]> listCargas = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();

            if (listCargas != null) {
                for (Object[] cargaObj : listCargas) {
                 CmRipsCargas cargaEntidad = (CmRipsCargas) Optional.ofNullable(cargaObj[0]).orElse(new CmRipsCargas());
                 CmRipsCarga cargaNegocio = castEntidadNegocioCorto(cargaEntidad);
                 CntContratos contratoObj = (CntContratos) Optional.ofNullable(cargaObj[1]).orElse(new CntContratos());
                 String contratoNumero = Optional.ofNullable(contratoObj.getContrato()).orElse("");
                 cargaNegocio.getCntContrato().setContrato(contratoNumero);
                 listResult.add(cargaNegocio);        
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
    public CmRipsCarga consultar(int id) throws Exception {
        CmRipsCarga objRes = null;
        try {
            String hql = "SELECT c FROM CmRipsCargas c "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("id", id);
            CmRipsCargas cmRipsCargas = (CmRipsCargas) query.getSingleResult();
            objRes = castEntidadNegocio(cmRipsCargas);
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    public List<CmRipsCargaAnexo> consultarAnexos(int id) throws Exception {
        List<CmRipsCargaAnexo> listResult = new ArrayList();
        try {
            String hql = "SELECT a FROM CmRipsCargaAnexos a "
                    + "WHERE a.cmRipsCargasId.id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("id", id);
            List<CmRipsCargaAnexos> list = query.getResultList();
            for (CmRipsCargaAnexos ent : list) {
                listResult.add(castEntidadNegocioCorto(ent));
            }
        } catch (NoResultException e) {
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public CmRipsCarga consultarConDetalles(int id) throws Exception {
        CmRipsCarga objRes = null;
        try {
            String hql = "SELECT c FROM CmRipsCargas c "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("id", id);
            CmRipsCargas cmRipsCargas = (CmRipsCargas) query.getSingleResult();
            objRes = castEntidadNegocioDetalles(cmRipsCargas);
            //Adicionar contrato
            if (objRes.getCntContrato() != null) {
                objRes.setCntContrato(getContrato(objRes.getCntContrato().getId()));
            }
            if (objRes.getCntContrato() == null) {
                objRes.setCntContrato(new CntContrato());
                objRes.getCntContrato().setContrato("N/A");
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
    public int consultarCantidadFacturaDetalles(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            
            String sqlDocumentoAfiliado   = "";
            String sqlTipoDocumento = "";
            if (paramConsulta.getFiltros() != null && paramConsulta.getFiltros().size() > 0) {
                String documentoAfiliado   = (String) Optional.ofNullable( paramConsulta.getFiltros().get("documento")).orElse("");
                String tipoDocumento = (String) Optional.ofNullable( paramConsulta.getFiltros().get("codigoDocumento")).orElse("");
                sqlDocumentoAfiliado   = documentoAfiliado.length()>1  ?  " AND documento_afiliado = '"+documentoAfiliado+"' " : "";
                sqlTipoDocumento = tipoDocumento.length()>1 ? " AND mae_tipo_documento_codigo = '"+tipoDocumento.toUpperCase()+"' " : "";    
            }
          
            String strQuery = "SELECT COUNT(1) FROM ( "
                    + "SELECT "
                    + "num_factura AS numero_factura, "
                    + "mae_tipo_documento_codigo AS tipo_documento, "
                    + "documento_afiliado AS documento, "
                    + "fecha_consulta AS fecha, "
                    + "autorizacion AS autorizacion, "
                    + "ma_tecnologia_codigo AS codigo_servicio, "
                    + "valor_consulta AS valor, "
                    + "1 AS cantidad, "
                    + "0 AS valor_copago, "
                    + "valor_cuota_moderadora AS cuota_moderadoras, "
                    + "valor_a_pagar AS total "
                    + "FROM cm_rips_ac_consultas "
                    + "WHERE cm_rips_cargas_id = " + (Integer) paramConsulta.getParametroConsulta1() + " "
                    + "AND num_factura = '" + (String) paramConsulta.getParametroConsulta2() + "' "
                    + sqlDocumentoAfiliado
                    + sqlTipoDocumento
                    + "UNION ALL "
                    + "SELECT "
                    + "numero_factura AS numero_factura, "
                    + "mae_tipo_documento_codigo AS tipo_documento, "
                    + "documento_afiliado AS documento, "
                    + "null AS fecha, "
                    + "autorizacion AS autorizacion, "
                    + "ma_medicamento_codigo AS codigo_servicio, "
                    + "valor_unitario AS valor, "
                    + "numero_unidades AS cantidad, "
                    + "0 AS valor_copago, "
                    + "0 AS cuota_moderadoras, "
                    + "valor_a_pagar AS total "
                    + "FROM cm_rips_am_medicamentos "
                    + "WHERE cm_rips_cargas_id = " + (Integer) paramConsulta.getParametroConsulta1() + " "
                    + "AND numero_factura = '" + (String) paramConsulta.getParametroConsulta2() + "' "
                    + sqlDocumentoAfiliado
                    + sqlTipoDocumento
                    + "UNION ALL "
                    + "SELECT  "
                    + "numero_factura AS numero_factura, "
                    + "mae_tipo_documento_codigo AS tipo_documento, "
                    + "documento_afiliado AS documento, "
                    + "fecha_procedimiento AS fecha, "
                    + "autorizacion AS autorizacion, "
                    + "ma_tecnologia_codigo AS codigo_servicio, "
                    + "valor_a_pagar AS valor, "
                    + "1 AS cantidad, "
                    + "0 AS valor_copago, "
                    + "0 AS cuota_moderadoras, "
                    + "valor_a_pagar AS total "
                    + "FROM cm_rips_ap_procedimientos "
                    + "WHERE cm_rips_cargas_id = " + (Integer) paramConsulta.getParametroConsulta1() + " "
                    + "AND numero_factura = '" + (String) paramConsulta.getParametroConsulta2() + "' "
                    + sqlDocumentoAfiliado
                    + sqlTipoDocumento
                    + "UNION ALL "
                    + "SELECT "
                    + "numero_factura AS numero_factura, "
                    + "mae_tipo_documento_codigo AS tipo_documento, "
                    + "documento_afiliado AS documento, "
                    + "null AS fecha, "
                    + "autorizacion AS autorizacion, "
                    + "ma_tecnologia_codigo AS codigo_servicio, "
                    + "valor_unidades AS valor, "
                    + "unidades AS cantidad, "
                    + "0 AS valor_copago, "
                    + "0 AS cuota_moderadoras, "
                    + "total AS total "
                    + "FROM cm_rips_at_otros_servicios "
                    + "WHERE cm_rips_cargas_id = " + (Integer) paramConsulta.getParametroConsulta1() + " "
                    + "AND numero_factura = '" + (String) paramConsulta.getParametroConsulta2() + "' "
                    + sqlDocumentoAfiliado
                    + sqlTipoDocumento
                    + ") AS detalles  ";
            Query query = em.createNativeQuery(strQuery);
            cant = ((Number) query.getSingleResult()).intValue();
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
    public List<CmRipsCargaDetalleDTO> consultarFacturaDetalles(ParamConsulta paramConsulta) throws Exception {
        List<CmRipsCargaDetalleDTO> listResult = new ArrayList<>();
        try {
            

            String sqlDocumentoAfiliado   = "";
            String sqlTipoDocumento = "";
            if (paramConsulta.getFiltros() != null && paramConsulta.getFiltros().size() > 0) {
                String documentoAfiliado   = (String) Optional.ofNullable( paramConsulta.getFiltros().get("documento")).orElse("");
                String tipoDocumento = (String) Optional.ofNullable( paramConsulta.getFiltros().get("codigoDocumento")).orElse("");
                sqlDocumentoAfiliado   = documentoAfiliado.length()>1  ?  " AND documento_afiliado = '"+documentoAfiliado+"' " : "";
                sqlTipoDocumento = tipoDocumento.length()>1 ? " AND mae_tipo_documento_codigo = '"+tipoDocumento.toUpperCase()+"' " : "";    
            }
            
            String strQuery = "SELECT "
                    + "num_factura AS numero_factura, "
                    + "mae_tipo_documento_codigo AS tipo_documento, "
                    + "documento_afiliado AS documento, "
                    + "fecha_consulta AS fecha, "
                    + "autorizacion AS autorizacion, "
                    + "ma_tecnologia_codigo AS codigo_servicio, "
                    + "valor_consulta AS valor, "
                    + "1 AS cantidad, "
                    + "0 AS valor_copago, "
                    + "valor_cuota_moderadora AS cuota_moderadoras, "
                    + "valor_a_pagar AS total, "
                    + "3 as tipo_servicio, "
                    + "ma_tecnologia_valor as nombre_servicio "
                    + "FROM cm_rips_ac_consultas "
                    + "WHERE cm_rips_cargas_id = " + (Integer) paramConsulta.getParametroConsulta1() + " "
                    + "AND num_factura = '" + (String) paramConsulta.getParametroConsulta2() + "' "
                    + sqlDocumentoAfiliado
                    + sqlTipoDocumento
                    + "UNION ALL "
                    + "SELECT "
                    + "numero_factura AS numero_factura, "
                    + "mae_tipo_documento_codigo AS tipo_documento, "
                    + "documento_afiliado AS documento, "
                    + "null AS fecha, "
                    + "autorizacion AS autorizacion, "
                    + "ma_medicamento_codigo AS codigo_servicio, "
                    + "valor_unitario AS valor, "
                    + "numero_unidades AS cantidad, "
                    + "0 AS valor_copago, "
                    + "0 AS cuota_moderadoras, "
                    + "valor_a_pagar AS total, "
                    + "0 as tipo_servicio, "
                    + "ma_medicamento_valor as nombre_servicio "
                    + "FROM cm_rips_am_medicamentos "
                    + "WHERE cm_rips_cargas_id = " + (Integer) paramConsulta.getParametroConsulta1() + " "
                    + "AND numero_factura = '" + (String) paramConsulta.getParametroConsulta2() + "' "
                    + sqlDocumentoAfiliado
                    + sqlTipoDocumento
                    + "UNION ALL "
                    + "SELECT  "
                    + "numero_factura AS numero_factura, "
                    + "mae_tipo_documento_codigo AS tipo_documento, "
                    + "documento_afiliado AS documento, "
                    + "fecha_procedimiento AS fecha, "
                    + "autorizacion AS autorizacion, "
                    + "ma_tecnologia_codigo AS codigo_servicio, "
                    + "valor_a_pagar AS valor, "
                    + "1 AS cantidad, "
                    + "0 AS valor_copago, "
                    + "0 AS cuota_moderadoras, "
                    + "valor_a_pagar AS total, "
                    + "1 as tipo_servicio, "
                    + "ma_tecnologia_valor as nombre_servicio "
                    + "FROM cm_rips_ap_procedimientos "
                    + "WHERE cm_rips_cargas_id = " + (Integer) paramConsulta.getParametroConsulta1() + " "
                    + "AND numero_factura = '" + (String) paramConsulta.getParametroConsulta2() + "' "
                    + sqlDocumentoAfiliado
                    + sqlTipoDocumento
                    + "UNION ALL "
                    + "SELECT "
                    + "numero_factura AS numero_factura, "
                    + "mae_tipo_documento_codigo AS tipo_documento, "
                    + "documento_afiliado AS documento, "
                    + "null AS fecha, "
                    + "autorizacion AS autorizacion, "
                    + "ma_tecnologia_codigo AS codigo_servicio, "
                    + "valor_unidades AS valor, "
                    + "unidades AS cantidad, "
                    + "0 AS valor_copago, "
                    + "0 AS cuota_moderadoras, "
                    + "total AS total, "
                    + "2 as tipo_servicio, "
                    + "ma_tecnologia_valor as nombre_servicio "
                    + "FROM cm_rips_at_otros_servicios "
                    + "WHERE cm_rips_cargas_id = " + (Integer) paramConsulta.getParametroConsulta1() + " "
                    + "AND numero_factura = '" + (String) paramConsulta.getParametroConsulta2() + "' "
                    + sqlDocumentoAfiliado
                    + sqlTipoDocumento;
            Query query = em.createNativeQuery(strQuery);
            List<Object[]> lstObj = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            int id = 0;
            for (Object[] per : lstObj) {
                id++;
                listResult.add(castDetallesToDetalle(per, id));
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
    public CmRipsCarga consultarErroresEstructura(int id) throws Exception {
        CmRipsCarga objRes = null;
        try {
            String hql = "SELECT DISTINCT c FROM CmRipsCargas c "
                    + "LEFT JOIN CmRipsEstructuraErrores e WITH e.cmRipsCargasId = c "
                    + "WHERE c.id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("id", id);
            CmRipsCargas cmRipsCargas = (CmRipsCargas) query.getSingleResult();
            objRes = castEntidadNegocioErrores(cmRipsCargas);
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
    public int consultarCantidadListaSucesos(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            List<Predicate> predicates = new ArrayList<>();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<CmRipsSucesos> cmRipsSucesos = cq.from(CmRipsSucesos.class);
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "alerta":
                            predicates.add(cb.equal(cmRipsSucesos.get("alerta"), e.getValue()));
                            break;
                        case "nombreRegla":
                            predicates.add(cb.equal(cmRipsSucesos.get("nombreRegla"), e.getValue()));
                            break;
                        case "tipo":
                            predicates.add(cb.equal(cmRipsSucesos.get("tipoRegla"), e.getValue()));
                            break;
                        case "archivoNombre":
                            predicates.add(cb.equal(cmRipsSucesos.get("archivoNombre"), e.getValue()));
                            break;
                        case "archivoFila":
                            predicates.add(cb.equal(cmRipsSucesos.get("archivoFila"), e.getValue()));
                            break;
                    }
                }
            }
            predicates.add(cb.equal(cmRipsSucesos.get("cmRipsCargasId"), (Integer) paramConsulta.getParametroConsulta1()));
            cq.where(predicates.<Predicate>toArray(new Predicate[predicates.size()]));
            cq.select(cb.count(cmRipsSucesos));
            cant = em.createQuery(cq).getSingleResult().intValue();
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
    public List<CmRipsSuceso> consultarListaSucesos(ParamConsulta paramConsulta) throws Exception {
        List<CmRipsSuceso> listResult = new ArrayList();
        try {
            List<Order> orderList = new ArrayList();
            List<Predicate> predicates = new ArrayList<>();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<CmRipsSucesos> cq = cb.createQuery(CmRipsSucesos.class);
            Root<CmRipsSucesos> cmRipsSucesos = cq.from(CmRipsSucesos.class);
            orderList.add(cb.desc(cmRipsSucesos.get("id")));
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "alerta":
                            predicates.add(cb.equal(cmRipsSucesos.get("alerta"), e.getValue()));
                            break;
                        case "nombreRegla":
                            predicates.add(cb.equal(cmRipsSucesos.get("nombreRegla"), e.getValue()));
                            break;
                        case "tipo":
                            predicates.add(cb.equal(cmRipsSucesos.get("tipoRegla"), e.getValue()));
                            break;
                        case "archivoNombre":
                            predicates.add(cb.equal(cmRipsSucesos.get("archivoNombre"), e.getValue()));
                            break;
                        case "archivoFila":
                            predicates.add(cb.equal(cmRipsSucesos.get("archivoFila"), e.getValue()));
                            break;
                    }
                }
            }
            predicates.add(cb.equal(cmRipsSucesos.get("cmRipsCargasId"), (Integer) paramConsulta.getParametroConsulta1()));
            cq.orderBy(orderList);
            cq.multiselect(
                    cmRipsSucesos.get("id"),
                    cmRipsSucesos.get("archivoNombre"),
                    cmRipsSucesos.get("archivoFila"),
                    cmRipsSucesos.get("cmRipsReglasId"),
                    cmRipsSucesos.get("cmRipsReglasMensajesId"),
                    cmRipsSucesos.get("nombreRegla"),
                    cmRipsSucesos.get("tipoRegla"),
                    cmRipsSucesos.get("alerta"),
                    cmRipsSucesos.get("descripcionMensaje"),
                    cmRipsSucesos.get("usuarioCrea"),
                    cmRipsSucesos.get("terminalCrea"),
                    cmRipsSucesos.get("fechaHoraCrea")
            );
            cq.where(predicates.toArray(new Predicate[]{}));
            TypedQuery<CmRipsSucesos> query = em.createQuery(cq);
            List<CmRipsSucesos> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CmRipsSucesos per : list) {
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
    public CmRipsCarga consultarPorParametros(ParamConsulta paramConsulta) throws Exception {
        CmRipsCarga objRes = null;
        try {
            String hql = "SELECT c FROM CmRipsCargas c "
                         + " WHERE c.id > 0 ";
      
            if (paramConsulta.getParametroConsulta1() != null) {
                    hql += " AND c.numeroCuenta = " + paramConsulta.getParametroConsulta1() + " ";
            }
            
            if (paramConsulta.getParametroConsulta2() != null) {
                    hql += " AND c.gnPrestadorSedesId.cntPrestadoresId.numeroDocumento = '" + paramConsulta.getParametroConsulta2() + "' ";
            }         
            
            hql += " ORDER BY c.id desc";
            
            Query query = getEntityManager().createQuery(hql);
            List<CmRipsCargas> list = query.getResultList();
            
            if(list != null && ! list.isEmpty() ){
              CmRipsCargas cmRipsCargas =  list.get(0);
              objRes = castEntidadNegocio(cmRipsCargas);
            }else{
              objRes = new CmRipsCarga();
            }       
        } catch (NoResultException e) {
            objRes =  new CmRipsCarga();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }


    @Override
    public int insertar(CmRipsCarga obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
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
    public void actualizar(CmRipsCarga obj) throws Exception {
        try {
            Query query;
            String hql = "";
            if (obj.getUsuarioAudita() != null) {
                hql = "UPDATE CmRipsCargas SET "
                        + "estado = :estado, "
                        + "usuario_audita = :usuarioAudita "
                        + "WHERE id = :id ";
                query = getEntityManager().createQuery(hql);
                query.setParameter("estado", obj.getEstado());
                query.setParameter("usuarioAudita", obj.getUsuarioAudita());
                query.setParameter("id", obj.getId());
            } else {
                hql = "UPDATE CmRipsCargas SET "
                        + "estado = :estado "
                        + "WHERE id = :id ";
                query = getEntityManager().createQuery(hql);
                query.setParameter("estado", obj.getEstado());
                query.setParameter("id", obj.getId());
            }
            query.executeUpdate();
            //Actualizar roles
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
 
    @Override
    public void actualizarEstadoRechazo(CmRipsCarga obj) throws Exception {
        try {
            Query query;
            String hql = "UPDATE CmRipsCargas SET "
                    + "estado = :estado , "
                    + "observacionRechazo = :observacionRechazo , "
                    + "maeRechazoId = :maeRechazoId , "
                    + "maeRechazoCodigo = :maeRechazoCodigo , "
                    + "maeRechazoValor = :maeRechazoValor "
                    + "WHERE id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("observacionRechazo", obj.getObservacionRechazo());
            query.setParameter("maeRechazoId", obj.getMaeRechazoId());
            query.setParameter("maeRechazoCodigo", obj.getMaeRechazoCodigo());
            query.setParameter("maeRechazoValor", obj.getMaeRechazoValor());
            query.setParameter("id", obj.getId());

            query.executeUpdate();
            //Actualizar roles
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarPbsCamaFija(CmRipsCarga obj) throws Exception {
        try {
            Query query;
            String hql = "";
            hql = "UPDATE CmRipsCargas SET "
                    + "camaFija = :cama_fija, "
                    + "pbs = :pbs "
                    + "WHERE id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("cama_fija", obj.getCamaFija());
            query.setParameter("pbs", obj.getPbs());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
            //Actualizar roles
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        CmRipsCarga obj = null;
        try {
            CmRipsCarga ent = getEntityManager().find(CmRipsCarga.class, id);
            if (ent != null) {
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void eliminarCmRipsCargaCtControl(int idRipCarga) throws Exception {
        CmRipsCarga obj = null;
        try {
            Query query;
            String hql = "DELETE  CmRipsCargaCtControl crc "
                    + " WHERE crc.cmRipsCargasId.id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("id", idRipCarga);
            query.executeUpdate();
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void eliminarCmRipsCargaUsUsuarios(int idRipCarga) throws Exception {
        CmRipsCarga obj = null;
        try {
            Query query;
            String hql = "DELETE  CmRipsCargaUsUsuarios crc "
                    + " WHERE crc.cmRipsCargasId.id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("id", idRipCarga);
            query.executeUpdate();
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void eliminarCmRipsCargaAuUrgencias(int idRipCarga) throws Exception {
        CmRipsCarga obj = null;
        try {
            Query query;
            String hql = "DELETE  CmRipsCargaAuUrgencias crc "
                    + " WHERE crc.cmRipsCargasId.id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("id", idRipCarga);
            query.executeUpdate();
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void eliminarCmRipsCargaAcConsultas(int idRipCarga) throws Exception {
        CmRipsCarga obj = null;
        try {
            Query query;
            String hql = "DELETE  CmRipsCargaAcConsultas crc "
                    + " WHERE crc.cmRipsCargasId.id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("id", idRipCarga);
            query.executeUpdate();
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void eliminarCmRipsCargaAdServiciosAgrupados(int idRipCarga) throws Exception {
        CmRipsCarga obj = null;
        try {
            Query query;
            String hql = "DELETE  CmRipsCargaAdServiciosAgrupados crc "
                    + " WHERE crc.cmRipsCargasId.id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("id", idRipCarga);
            query.executeUpdate();
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void eliminarCmRipsCargaAfFacturas(int idRipCarga) throws Exception {
        CmRipsCarga obj = null;
        try {
            Query query;
            String hql = "DELETE  CmRipsCargaAfFacturas crc "
                    + " WHERE crc.cmRipsCargasId.id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("id", idRipCarga);
            query.executeUpdate();
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void eliminarCmRipsCargaAhHospitalizaciones(int idRipCarga) throws Exception {
        CmRipsCarga obj = null;
        try {
            Query query;
            String hql = "DELETE  CmRipsCargaAhHospitalizaciones crc "
                    + " WHERE crc.cmRipsCargasId.id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("id", idRipCarga);
            query.executeUpdate();
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void eliminarCmRipsCargaAmMedicamentos(int idRipCarga) throws Exception {
        CmRipsCarga obj = null;
        try {
            Query query;
            String hql = "DELETE  CmRipsCargaAmMedicamentos crc "
                    + " WHERE crc.cmRipsCargasId.id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("id", idRipCarga);
            query.executeUpdate();
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void eliminarCmRipsCargaAnRecienNacidos(int idRipCarga) throws Exception {
        CmRipsCarga obj = null;
        try {
            Query query;
            String hql = "DELETE CmRipsCargaAnRecienNacidos crc "
                    + " WHERE crc.cmRipsCargasId.id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("id", idRipCarga);
            query.executeUpdate();
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void eliminarCmRipsCargaApProcedimientos(int idRipCarga) throws Exception {
        CmRipsCarga obj = null;
        try {
            Query query;
            String hql = "DELETE CmRipsCargaApProcedimientos crc "
                    + " WHERE crc.cmRipsCargasId.id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("id", idRipCarga);
            query.executeUpdate();
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void eliminarCmRipsCargaAtOtrosServicios(int idRipCarga) throws Exception {
        CmRipsCarga obj = null;
        try {
            Query query;
            String hql = "DELETE CmRipsCargaAtOtrosServicios crc "
                    + " WHERE crc.cmRipsCargasId.id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("id", idRipCarga);
            query.executeUpdate();
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void eliminarCmRipsCtControl(int idRipCarga) throws Exception {
        CmRipsCarga obj = null;
        try {
            Query query;
            String hql = "DELETE CmRipsCtControl crc "
                    + " WHERE crc.cmRipsCargasId.id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("id", idRipCarga);
            query.executeUpdate();
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void eliminarCmRipsUsUsuarios(int idRipCarga) throws Exception {
        CmRipsCarga obj = null;
        try {
            Query query;
            String hql = "DELETE CmRipsUsUsuarios crc "
                    + " WHERE crc.cmRipsCargasId.id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("id", idRipCarga);
            query.executeUpdate();
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void eliminarCmRipsAuUrgencias(int idRipCarga) throws Exception {
        CmRipsCarga obj = null;
        try {
            Query query;
            String hql = "DELETE CmRipsAuUrgencias crc "
                    + " WHERE crc.cmRipsCargasId.id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("id", idRipCarga);
            query.executeUpdate();
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void eliminarCmRipsAcConsultas(int idRipCarga) throws Exception {
        CmRipsCarga obj = null;
        try {
            Query query;
            String hql = "DELETE CmRipsAcConsultas crc "
                    + " WHERE crc.cmRipsCargasId.id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("id", idRipCarga);
            query.executeUpdate();
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void eliminarCmRipsAdServiciosAgrupados(int idRipCarga) throws Exception {
        CmRipsCarga obj = null;
        try {
            Query query;
            String hql = "DELETE CmRipsAdServiciosAgrupados crc "
                    + " WHERE crc.cmRipsCargasId.id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("id", idRipCarga);
            query.executeUpdate();
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void eliminarCmRipsAfFacturas(int idRipCarga) throws Exception {
        CmRipsCarga obj = null;
        try {
            Query query;
            String hql = "DELETE CmRipsAfFacturas crc "
                    + " WHERE crc.cmRipsCargasId.id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("id", idRipCarga);
            query.executeUpdate();
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void eliminarCmRipsAhHospitalizaciones(int idRipCarga) throws Exception {
        CmRipsCarga obj = null;
        try {
            Query query;
            String hql = "DELETE CmRipsAhHospitalizaciones crc "
                    + " WHERE crc.cmRipsCargasId.id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("id", idRipCarga);
            query.executeUpdate();
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void eliminarCmRipsAmMedicamentos(int idRipCarga) throws Exception {
        CmRipsCarga obj = null;
        try {
            Query query;
            String hql = "DELETE CmRipsAmMedicamentos crc "
                    + " WHERE crc.cmRipsCargasId.id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("id", idRipCarga);
            query.executeUpdate();
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void eliminarCmRipsAnRecienNacidos(int idRipCarga) throws Exception {
        CmRipsCarga obj = null;
        try {
            Query query;
            String hql = "DELETE CmRipsAnRecienNacidos crc "
                    + " WHERE crc.cmRipsCargasId.id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("id", idRipCarga);
            query.executeUpdate();
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void eliminarCmRipsApProcedimientos(int idRipCarga) throws Exception {
        CmRipsCarga obj = null;
        try {
            Query query;
            String hql = "DELETE CmRipsApProcedimientos crc "
                    + " WHERE crc.cmRipsCargasId.id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("id", idRipCarga);
            query.executeUpdate();
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void eliminarCmRipsAtOtrosServicios(int idRipCarga) throws Exception {
        CmRipsCarga obj = null;
        try {
            Query query;
            String hql = "DELETE CmRipsAtOtrosServicios crc "
                    + " WHERE crc.cmRipsCargasId.id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("id", idRipCarga);
            query.executeUpdate();
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public int insertarAdjunto(CmRipsCargaAnexo obj) throws Exception {
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
    public CmRipsCargaAnexo eliminarAdjunto(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int validarPendientes(int idEstado) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public int consultarCantidadContratos(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String strQuery = "SELECT COUNT(c) FROM CntContratoSedes c "
                    + "WHERE c.id > 0 ";

            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND c.cntPrestadorSedesId.id = " + paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND UPPER(c.cntContratosId.maeEstadoContratoValor) LIKE '" + paramConsulta.getParametroConsulta2() + "%' ";
            }
            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += " AND c.fechaInicio <='" + sdf.format((Date) paramConsulta.getParametroConsulta3()) + "' AND c.fechaFin >= '" + sdf.format((Date) paramConsulta.getParametroConsulta3()) + "'";
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
    public int insertarEstructuraErrores(CmRipsEstructuraError obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
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
    public int insertarCargaAc(CmRipsAcConsulta obj, int tipo) throws Exception {
        int id = 0;
        try {
            if (tipo == CmRipsCarga.TABLA_CARGA) {
                id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();

            } else if (tipo == CmRipsCarga.TABLA_FINAL) {
                id = (int) getEntityManager().merge(castNegocioEntidadFinal(obj)).getId();
            }
            obj.setId(id);
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
    public int insertarCargaAd(CmRipsAdServiciosAgrupado obj, int tipo) throws Exception {
        int id = 0;
        try {
            if (tipo == CmRipsCarga.TABLA_CARGA) {
                id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            } else if (tipo == CmRipsCarga.TABLA_FINAL) {
                id = (int) getEntityManager().merge(castNegocioEntidadFinal(obj)).getId();
            }
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
    public int insertarCargaAf(CmRipsAfFactura obj, int tipo) throws Exception {
        int id = 0;
        try {
            if (tipo == CmRipsCarga.TABLA_CARGA) {
                id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            } else if (tipo == CmRipsCarga.TABLA_FINAL) {
                id = (int) getEntityManager().merge(castNegocioEntidadFinal(obj)).getId();
            }
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
    public int insertarCargaAh(CmRipsAhHospitalizacion obj, int tipo) throws Exception {
        int id = 0;
        try {
            if (tipo == CmRipsCarga.TABLA_CARGA) {
                id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            } else if (tipo == CmRipsCarga.TABLA_FINAL) {
                id = (int) getEntityManager().merge(castNegocioEntidadFinal(obj)).getId();
            }
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
    public int insertarCargaAm(CmRipsAmMedicamento obj, int tipo) throws Exception {
        int id = 0;
        try {
            if (tipo == CmRipsCarga.TABLA_CARGA) {
                id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            } else if (tipo == CmRipsCarga.TABLA_FINAL) {
                id = (int) getEntityManager().merge(castNegocioEntidadFinal(obj)).getId();
            }
            obj.setId(id);
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
    public int insertarCargaAn(CmRipsAnRecienNacido obj, int tipo) throws Exception {
        int id = 0;
        try {
            if (tipo == CmRipsCarga.TABLA_CARGA) {
                id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            } else if (tipo == CmRipsCarga.TABLA_FINAL) {
                id = (int) getEntityManager().merge(castNegocioEntidadFinal(obj)).getId();
            }
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
    public int insertarCargaAt(CmRipsAtOtrosServicio obj, int tipo) throws Exception {
        int id = 0;
        try {
            if (tipo == CmRipsCarga.TABLA_CARGA) {
                id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            } else if (tipo == CmRipsCarga.TABLA_FINAL) {
                id = (int) getEntityManager().merge(castNegocioEntidadFinal(obj)).getId();
            }
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
    public int insertarCargaCt(CmRipsCtControlObj obj, int tipo) throws Exception {
        int id = 0;
        try {
            if (tipo == CmRipsCarga.TABLA_CARGA) {
                id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            } else if (tipo == CmRipsCarga.TABLA_FINAL) {
                id = (int) getEntityManager().merge(castNegocioEntidadFinal(obj)).getId();
            }
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
    public int insertarCargaUs(CmRipsUsUsuario obj, int tipo) throws Exception {
        int id = 0;
        try {
            if (tipo == CmRipsCarga.TABLA_CARGA) {
                id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            } else if (tipo == CmRipsCarga.TABLA_FINAL) {
                id = (int) getEntityManager().merge(castNegocioEntidadFinal(obj)).getId();
            }
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
    public int insertarCargaAp(CmRipsApProcedimiento obj, int tipo) throws Exception {
        int id = 0;
        try {
            if (tipo == CmRipsCarga.TABLA_CARGA) {
                id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            } else if (tipo == CmRipsCarga.TABLA_FINAL) {
                id = (int) getEntityManager().merge(castNegocioEntidadFinal(obj)).getId();
            }
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
    public int insertarCargaAu(CmRipsAuUrgencia obj, int tipo) throws Exception {
        int id = 0;
        try {
            if (tipo == CmRipsCarga.TABLA_CARGA) {
                id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            } else if (tipo == CmRipsCarga.TABLA_FINAL) {
                id = (int) getEntityManager().merge(castNegocioEntidadFinal(obj)).getId();
            }
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
    public int insertarCargaEstados(CmRipsCargaEstado obj, int tipo) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    //Consultar un contrato por ID 
    private CntContrato getContrato(Integer id) throws Exception {
        CntContrato objRes = null;
        try {
            String hql = "SELECT c FROM CntContratos c "
                    + "WHERE c.id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("id", id);
            CntContratos cntContratos = (CntContratos) query.getSingleResult();
            objRes = castEntidadNegocio(cntContratos);
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
    public CmRipsSuceso consultarSucesoRipsAM(ParamConsulta paramConsulta) throws java.lang.Exception {
        CmRipsSuceso suceso = new CmRipsSuceso();
        try {   
         
            String strQuery = " SELECT cms.cm_rips_cargas_id, cms.archivo_fila , cms.nombre_regla, "
                    + " cms.descripcion_mensaje FROM cm_rips_sucesos cms JOIN  "
                    + " cm_rips_am_medicamentos cam ON  cam.cm_rips_cargas_id = cms.cm_rips_cargas_id " 
                    + " WHERE cms.archivo_fila = cam.fila  AND "
                    + " cam.cm_rips_cargas_id = " + paramConsulta.getParametroConsulta1() +" AND "
                    + " cam.numero_factura = '"+paramConsulta.getParametroConsulta2()+"' AND "
                    + " cam.documento_afiliado = '"+ paramConsulta.getParametroConsulta3()+"' AND "
                    + " cam.autorizacion = "+paramConsulta.getParametroConsulta6()+" AND "
                    + " cam.ma_medicamento_codigo = '"+paramConsulta.getParametroConsulta4()+"' AND " 
                    + " cms.nombre_regla = '"+paramConsulta.getParametroConsulta7()+"' AND "
                    + " cms.archivo_nombre = 'AM'   ";

            List<Object[]> listAfiliados = getEntityManager().createNativeQuery(strQuery).getResultList();
            for (Object[] itemSuceso : listAfiliados) {
                suceso.setCmRipsCarga(new CmRipsCarga((Integer) itemSuceso[0]));
                suceso.setArchivoFila((int) itemSuceso[1]);
                suceso.setNombreRegla((String) itemSuceso[2]);
                suceso.setDescripcionMensaje((String) itemSuceso[3]);
            }

        } catch (NoResultException e) {
            suceso = new CmRipsSuceso();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return suceso;
    }
    
    @Override
    public CmRipsSuceso consultarSucesoRipsAC(ParamConsulta paramConsulta) throws java.lang.Exception {
        CmRipsSuceso suceso = new CmRipsSuceso();
        try {
             
            String strQuery = " SELECT cms.cm_rips_cargas_id, cms.archivo_fila , cms.nombre_regla, " +
                                " cms.descripcion_mensaje FROM cm_rips_sucesos cms JOIN  " +
                                " cm_rips_ac_consultas cam ON  cam.cm_rips_cargas_id = cms.cm_rips_cargas_id " +
                                " WHERE cms.archivo_fila = cam.fila AND " +
                                " cam.cm_rips_cargas_id = "+paramConsulta.getParametroConsulta1()+"  AND " +
                                " cam.num_factura='"+paramConsulta.getParametroConsulta2()+"' AND" +
                                " cam.documento_afiliado = '"+paramConsulta.getParametroConsulta3()+"' AND " +
                                " cam.autorizacion = "+paramConsulta.getParametroConsulta6()+" AND " +
                                " cam.ma_tecnologia_codigo = '"+paramConsulta.getParametroConsulta4()+"' AND " +
                                " cms.nombre_regla = '"+paramConsulta.getParametroConsulta7()+"' AND " +
                                " cms.archivo_nombre = 'AC'  ";

            List<Object[]> listAfiliados = getEntityManager().createNativeQuery(strQuery).getResultList();
            for (Object[] itemSuceso : listAfiliados) {
                suceso.setCmRipsCarga(new CmRipsCarga((Integer) itemSuceso[0]));
                suceso.setArchivoFila((int) itemSuceso[1]);
                suceso.setNombreRegla((String) itemSuceso[2]);
                suceso.setDescripcionMensaje((String) itemSuceso[3]);
            }

        } catch (NoResultException e) {
            suceso = new CmRipsSuceso();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return suceso;
    }
    
    @Override
    public CmRipsSuceso consultarSucesoRipsAP(ParamConsulta paramConsulta) throws java.lang.Exception {
        CmRipsSuceso suceso = new CmRipsSuceso();
        try {
            
            
            String strQuery = " SELECT cms.cm_rips_cargas_id, cms.archivo_fila , cms.nombre_regla, " +
                                " cms.descripcion_mensaje FROM cm_rips_sucesos cms JOIN  " +
                                " cm_rips_ap_procedimientos cam ON  cam.cm_rips_cargas_id = cms.cm_rips_cargas_id " +
                                " WHERE cms.archivo_fila = cam.fila AND " +
                                " cam.cm_rips_cargas_id = "+paramConsulta.getParametroConsulta1()+"  AND " +
                                " cam.numero_factura='"+paramConsulta.getParametroConsulta2()+"' AND" +
                                " cam.documento_afiliado = '"+paramConsulta.getParametroConsulta3()+"' AND " +
                                " cam.autorizacion = "+paramConsulta.getParametroConsulta6()+" AND " +
                                " cam.ma_tecnologia_codigo = '"+paramConsulta.getParametroConsulta4()+"' AND " +
                                " cms.nombre_regla = '"+paramConsulta.getParametroConsulta7()+"' AND " +
                                " cms.archivo_nombre = 'AP' ";

            List<Object[]> listAfiliados = getEntityManager().createNativeQuery(strQuery).getResultList();
            for (Object[] itemSuceso : listAfiliados) {
                suceso.setCmRipsCarga(new CmRipsCarga((Integer) itemSuceso[0]));
                suceso.setArchivoFila((int) itemSuceso[1]);
                suceso.setNombreRegla((String) itemSuceso[2]);
                suceso.setDescripcionMensaje((String) itemSuceso[3]);
            }

        } catch (NoResultException e) {
            suceso = new CmRipsSuceso();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return suceso;
    }
    
    @Override
    public CmRipsSuceso consultarSucesoRipsAT(ParamConsulta paramConsulta) throws java.lang.Exception {
        CmRipsSuceso suceso = new CmRipsSuceso();
        try {
             
            String strQuery = "  SELECT cms.cm_rips_cargas_id, cms.archivo_fila , cms.nombre_regla, "
                    + " cms.descripcion_mensaje FROM cm_rips_sucesos cms JOIN  "
                    + " cm_rips_at_otros_servicios cam ON  cam.cm_rips_cargas_id = cms.cm_rips_cargas_id "
                    + " WHERE cms.archivo_fila = cam.fila AND "
                    + " cam.cm_rips_cargas_id = "+paramConsulta.getParametroConsulta1()+"  AND "
                    + " cam.numero_factura='"+paramConsulta.getParametroConsulta2()+"' AND "
                    + " cam.documento_afiliado = '"+paramConsulta.getParametroConsulta3()+"' AND "
                    + " cam.autorizacion = "+paramConsulta.getParametroConsulta6()+" AND "
                    + " cam.ma_tecnologia_codigo = '"+paramConsulta.getParametroConsulta4()+"' AND "
                    + " cms.nombre_regla = '"+paramConsulta.getParametroConsulta7()+"' AND "
                    + " cms.archivo_nombre = 'AT' ";

            List<Object[]> listAfiliados = getEntityManager().createNativeQuery(strQuery).getResultList();
            for (Object[] itemSuceso : listAfiliados) {
                suceso.setCmRipsCarga(new CmRipsCarga((Integer) itemSuceso[0]));
                suceso.setArchivoFila((int) itemSuceso[1]);
                suceso.setNombreRegla((String) itemSuceso[2]);
                suceso.setDescripcionMensaje((String) itemSuceso[3]);
            }

        } catch (NoResultException e) {
            suceso = new CmRipsSuceso();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return suceso;
    }
    
    /*
    NEGOCIO ENTIDAD CAST CARGA
     */
    public static CmRipsCargas castNegocioEntidad(CmRipsCarga obj) {
        CmRipsCargas ent = new CmRipsCargas();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        if (obj.getGnPrestadorSede() != null) {
            ent.setGnPrestadorSedesId(new CntPrestadorSedes(obj.getGnPrestadorSede().getId()));
        }
        if (obj.getCntContratoSede() != null && obj.getCntContratoSede().getCntContrato() != null) {
            ent.setCntContratosId( new CntContratos( obj.getCntContratoSede().getCntContrato().getId()));
            ent.setCntTipoContratoId(obj.getCntTipoContratoId());
        } else {
            ent.setCntContratosId(null);
        }
        if (obj.getEmpresa() != null) {
            ent.setGnEmpresasId(new GnEmpresas(obj.getEmpresa().getId()));
        }
        ent.setValorCarga(obj.getValorCarga());
        ent.setContrato(obj.getContrato());
        //MAE REGIMEN
        if (obj.getMaeRegimenCodigo() != null) {
            ent.setMaeRegimenCodigo(obj.getMaeRegimenCodigo());
            if (obj.getMaeRegimenId() != null) {
                ent.setMaeRegimenId(obj.getMaeRegimenId());
            }
            if (obj.getMaeRegimenValor() != null) {
                ent.setMaeRegimenValor(obj.getMaeRegimenValor());
            }
        }
        //MAE GN_UBICACION REGIONAL
        if (obj.getMaeRegionalCodigo() != null) {
            ent.setMaeReginalCodigo(obj.getMaeRegionalCodigo());
            if (obj.getMaeRegionalId() != null) {
                ent.setMaeRegionalId(obj.getMaeRegionalId());
            }
            if (obj.getMaeRegionalValor() != null) {
                ent.setMaeRegionalValor(obj.getMaeRegionalValor());
            }
        }
        //MAE MODALIDAD
        if (obj.getMaeContratoModalidadCodigo() != null) {
            ent.setMaeContratoModalidadCodigo(obj.getMaeContratoModalidadCodigo());
            if (obj.getMaeContratoModalidadId() != null) {
                ent.setMaeContratoModalidadId(obj.getMaeContratoModalidadId());
            }
            if (obj.getMaeContratoModalidadValor() != null) {
                ent.setMaeContratoModalidadValor(obj.getMaeContratoModalidadValor());
            }
        }
        if (obj.getNumeroCuenta() != null) {
            ent.setNumeroCuenta(obj.getNumeroCuenta());
        }
        if (obj.getFechaHoraInicio() != null) {
            ent.setFechaHoraInicio(obj.getFechaHoraInicio());
        }
        ent.setTiempo(obj.getTiempo());
        if (obj.getFechaHoraFin() != null) {
            ent.setFechaHoraFin(obj.getFechaHoraFin());
        }
        ent.setPbs(obj.getPbs());
        ent.setCamaFija(obj.getCamaFija());
        ent.setCantidadFactura(Integer.toString(obj.getCantidadFactura()));
        ent.setFechaPrestacion(obj.getFechaPrestacion());
        ent.setEstado(obj.getEstado());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaCtControl castNegocioEntidad(CmRipsCtControlObj obj) {
        CmRipsCargaCtControl ent = new CmRipsCargaCtControl();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setCodigoReps(obj.getCodigoReps());
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setArchivoControl(obj.getArchivoControl());
        ent.setArchivoAf(obj.getArchivoAf());
        ent.setArchivoUs(obj.getArchivoUs());
        ent.setArchivoAd(obj.getArchivoAd());
        ent.setArchivoAc(obj.getArchivoAc());
        ent.setArchivoAp(obj.getArchivoAp());
        ent.setArchivoAh(obj.getArchivoAh());
        ent.setArchivoAu(obj.getArchivoAu());
        ent.setArchivoAn(obj.getArchivoAn());
        ent.setArchivoAm(obj.getArchivoAm());
        ent.setArchivoAt(obj.getArchivoAt());
        ent.setRegistrosAf(obj.getRegistrosAf());
        ent.setRegistroAc(obj.getRegistroAc());
        ent.setRegistrosUs(obj.getRegistrosUs());
        ent.setRegistrosAp(obj.getRegistrosAp());
        ent.setRegistrosAh(obj.getRegistrosAh());
        ent.setRegistrosAu(obj.getRegistrosAu());
        ent.setRegistrosAm(obj.getRegistrosAm());
        ent.setRegistrosAt(obj.getRegistrosAt());
        ent.setRegistrosAn(obj.getRegistrosAn());
        ent.setRegistrosAd(obj.getRegistrosAd());
        ent.setFechaAf(obj.getFechaAf());
        ent.setFechaUs(obj.getFechaUs());
        ent.setFechaAc(obj.getFechaAc());
        ent.setFechaAp(obj.getFechaAp());
        ent.setFechaAh(obj.getFechaAh());
        ent.setFechaAu(obj.getFechaAu());
        ent.setFechaAm(obj.getFechaAm());
        ent.setFechaAt(obj.getFechaAt());
        ent.setFechaAn(obj.getFechaAn());
        ent.setFechaAd(obj.getFechaAd());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaAnexos castNegocioEntidad(CmRipsCargaAnexo obj) {
        CmRipsCargaAnexos ent = new CmRipsCargaAnexos();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setTipoArchivo(obj.getTipoStr());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setExiste(obj.isExiste());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsEstructuraErrores castNegocioEntidad(CmRipsEstructuraError obj) {
        CmRipsEstructuraErrores ent = new CmRipsEstructuraErrores();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoFila(obj.getArchivoFila());
        ent.setDescripcionError(obj.getDescripcionError());
        return ent;
    }

    public static CmRipsSucesos castNegocioEntidad(CmRipsSuceso obj) {
        CmRipsSucesos ent = new CmRipsSucesos();
//        if (obj.getId() != null) {
//            ent.setId(obj.getId());
//        }
//        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
//        ent.setArchivoNombre(obj.getArchivoNombre());
//        ent.setArchivoFila(obj.getArchivoFila());
//        ent.setDescripcionError(obj.getDescripcionError());
        return ent;
    }

    public static CmRipsCargaEstados castNegocioEntidad(CmRipsCargaEstado obj) {
        CmRipsCargaEstados ent = new CmRipsCargaEstados();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setEstado(obj.getEstado());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaAcConsultas castNegocioEntidad(CmRipsAcConsulta obj) {
        CmRipsCargaAcConsultas ent = new CmRipsCargaAcConsultas();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setFila(obj.getFila());
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumFactura(obj.getNumFactura());
        ent.setCodigoReps(obj.getCodigoReps());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setArchivoNombre(obj.getArchivoNombre());
        //MAE TIPO DOCUMENTO
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setDocumentoAfiliado(obj.getDocumentoAfiliado());
        if (obj.getFechaConsulta() != null) {
            ent.setFechaConsulta(obj.getFechaConsulta());
        }
        ent.setAutorizacion(obj.getAutorizacion());
        if (obj.getMaTecnologiaId() != null) {
            ent.setMaTecnologiaId(obj.getMaTecnologiaId());
        }
        if (obj.getMaTecnologiaCodigo() != null) {
            ent.setMaTecnologiaCodigo(obj.getMaTecnologiaCodigo());
        }
        if (obj.getMaTecnologiaValor() != null) {
            ent.setMaTecnologiaValor(obj.getMaTecnologiaValor());
        }
        //MAE FINALIDAD CONSULTA
        if (obj.getMaeFinalidadConsultaCodigo() != null) {
            ent.setMaeFinalidadConsultaCodigo(obj.getMaeFinalidadConsultaCodigo());
            if (obj.getMaeFinalidadConsultaId() != null) {
                ent.setMaeFinalidadConsultaId(obj.getMaeFinalidadConsultaId());
            }
            if (obj.getMaeFinalidadConsultaValor() != null) {
                ent.setMaeFinalidadConsultaValor(obj.getMaeFinalidadConsultaValor());
            }
        }
        //MAE CAUSA EXTERNA
        if (obj.getMaeCausaExternaCodigo() != null) {
            ent.setMaeCausaExternaCodigo(obj.getMaeCausaExternaCodigo());
            if (obj.getMaeCausaExternaId() != null) {
                ent.setMaeCausaExternaId(obj.getMaeCausaExternaId());
            }
            if (obj.getMaeCausaExternaValor() != null) {
                ent.setMaeCausaExternaValor(obj.getMaeCausaExternaValor());
            }
        }
        //MA DIAGNOSTICO
        if (obj.getMaDiagnosticoPrincipalCodigo() != null) {
            ent.setMaDiagnosticoPrincipalCodigo(obj.getMaDiagnosticoPrincipalCodigo());
            if (obj.getMaDiagnosticoPrincipalId() != null) {
                ent.setMaDiagnosticoPrincipalId(obj.getMaDiagnosticoPrincipalId());
            }
            if (obj.getMaDiagnosticoPrincipalValor() != null) {
                ent.setMaDiagnosticoPrincipalValor(obj.getMaDiagnosticoPrincipalValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoRelacionado1Codigo());
            if (obj.getMaDiagnosticoRelacionado1Id() != null) {
                ent.setMaDiagnosticoRelacionado1Id(obj.getMaDiagnosticoRelacionado1Id());
            }
            if (obj.getMaDiagnosticoRelacionado1Valor() != null) {
                ent.setMaDiagnosticoRelacionado1Valor(obj.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (obj.getMaDiagnosticoRelacionado2Codigo() != null) {
            ent.setMaDiagnosticoRelacionado2Codigo(obj.getMaDiagnosticoRelacionado2Codigo());
            if (obj.getMaDiagnosticoRelacionado2Id() != null) {
                ent.setMaDiagnosticoRelacionado2Id(obj.getMaDiagnosticoRelacionado2Id());
            }
            if (obj.getMaDiagnosticoRelacionado2Valor() != null) {
                ent.setMaDiagnosticoRelacionado2Valor(obj.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (obj.getMaDiagnosticoRelacionado3Codigo() != null) {
            ent.setMaDiagnosticoRelacionado3Codigo(obj.getMaDiagnosticoRelacionado3Codigo());
            if (obj.getMaDiagnosticoRelacionado3Id() != null) {
                ent.setMaDiagnosticoRelacionado3Id(obj.getMaDiagnosticoRelacionado3Id());
            }
            if (obj.getMaDiagnosticoRelacionado3Valor() != null) {
                ent.setMaDiagnosticoRelacionado3Valor(obj.getMaDiagnosticoRelacionado3Valor());
            }
        }
        //MA TIPO DIAGNOSTICO 
        if (obj.getMaeTipoDiagnosticoCodigo() != null) {
            ent.setMaeTipoDiagnosticoCodigo(obj.getMaeTipoDiagnosticoCodigo());
            if (obj.getMaeTipoDiagnosticoId() != null) {
                ent.setMaeTipoDiagnosticoId(obj.getMaeTipoDiagnosticoId());
            }
            if (obj.getMaeTipoDiagnosticoValor() != null) {
                ent.setMaeTipoDiagnosticoValor(obj.getMaeTipoDiagnosticoValor());
            }
        }
        ent.setValorConsulta(obj.getValorConsulta());
        ent.setValorCuotaModeradora(obj.getValorCuotaModeradora());
        ent.setValorAPagar(obj.getValorAPagar());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaAdServiciosAgrupados castNegocioEntidad(CmRipsAdServiciosAgrupado obj) {
        CmRipsCargaAdServiciosAgrupados ent = new CmRipsCargaAdServiciosAgrupados();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setFila(obj.getFila());
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumeroFactura(obj.getNumeroFactura());
        ent.setCodigoReps(obj.getCodigoReps());
        ent.setCantidadServicios(obj.getCantidadServicio());
        ent.setValorUnitario(obj.getValorUnitario());
        ent.setValorConcepto(obj.getValorConcepto());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaAfFacturas castNegocioEntidad(CmRipsAfFactura obj) {
        CmRipsCargaAfFacturas ent = new CmRipsCargaAfFacturas();
        if (obj.getId() != null) {
            //ent.setId(obj.getId());
        }
        ent.setFila(obj.getFila());
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setCodigoReps(obj.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setRazonSocial(obj.getRazonSocial());
        ent.setNit(obj.getNit().trim());
        ent.setNumeroFactura(obj.getNumeroFactura());
        ent.setFechaFactura(obj.getFechaFactura());
        if (obj.getFechaInicio() != null) {
            ent.setFechaInicio(obj.getFechaInicio());
        }
        if (obj.getFechaFinal() != null) {
            ent.setFechaFinal(obj.getFechaFinal());
        }
        ent.setCodigoEps(obj.getCodigoEps());
        if (obj.getNombreAdministradora() != null) {
            ent.setNombreAdministradora(obj.getNombreAdministradora());
        }
        if (obj.getContrato() != null) {
            ent.setContrato(obj.getContrato());
        }
        if (obj.getPlanBeneficio() != null) {
            ent.setPlanBeneficios(obj.getPlanBeneficio());
        }
        if (obj.getNumeroPoliza() != null) {
            ent.setNumeroPoliza(obj.getNumeroPoliza());
        }
        ent.setValorCopago(obj.getValorCopago());
        ent.setValorAPagar(obj.getValorAPagar());
        if (obj.getValorComision() != null) {
            ent.setValorComision(obj.getValorComision());
        }
        if (obj.getValorDescuento() != null) {
            ent.setValorDescuento(obj.getValorDescuento());
        }
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaAhHospitalizaciones castNegocioEntidad(CmRipsAhHospitalizacion obj) {
        CmRipsCargaAhHospitalizaciones ent = new CmRipsCargaAhHospitalizaciones();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setFila(obj.getFila());
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumeroFactura(obj.getNumeroFactura());
        ent.setCodigoReps(obj.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setDocumentoAfiliado(obj.getDocumentoAfiliado());
        //MAE VIA INGRESO
        if (obj.getMaeViaIngresoCodigo() != null) {
            ent.setMaeViaIngresoCodigo(obj.getMaeViaIngresoCodigo());
            if (obj.getMaeViaIngresoId() != null) {
                ent.setMaeViaIngresoId(obj.getMaeViaIngresoId());
            }
            if (obj.getMaeViaIngresoValor() != null) {
                ent.setMaeViaIngresoValor(obj.getMaeViaIngresoValor());
            }
        }
        //MAE CAUSA EXTERNA
        if (obj.getMaeCausaExternaCodigo() != null) {
            ent.setMaeCausaExternaCodigo(obj.getMaeViaIngresoCodigo());
            if (obj.getMaeCausaExternaId() != null) {
                ent.setMaeCausaExternaId(obj.getMaeCausaExternaId());
            }
            if (obj.getMaeCausaExternaValor() != null) {
                ent.setMaeCausaExternaValor(obj.getMaeCausaExternaValor());
            }
        }
        //MA DIAGNOSTICO INGRESO
        if (obj.getMaDiagnosticoIngresoCodigo() != null) {
            ent.setMaDiagnosticoIngresoCodigo(obj.getMaDiagnosticoIngresoCodigo());
            if (obj.getMaDiagnosticoIngresoId() != null) {
                ent.setMaDiagnosticoIngresoId(obj.getMaDiagnosticoIngresoId());
            }
            if (obj.getMaDiagnosticoIngresoValor() != null) {
                ent.setMaDiagnosticoIngresoValor(obj.getMaDiagnosticoIngresoValor());
            }
        }
        //MA DIAGNOSTICO EGRESO
        if (obj.getMaDiagnosticoEgresoCodigo() != null) {
            ent.setMaDiagnosticoEgresoCodigo(obj.getMaDiagnosticoEgresoCodigo());
            if (obj.getMaDiagnosticoEgresoId() != null) {
                ent.setMaDiagnosticoEgresoId(obj.getMaDiagnosticoEgresoId());
            }
            if (obj.getMaDiagnosticoEgresoValor() != null) {
                ent.setMaDiagnosticoEgresoValor(obj.getMaDiagnosticoEgresoValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoRelacionado1Codigo());
            if (obj.getMaDiagnosticoRelacionado1Id() != null) {
                ent.setMaDiagnosticoRelacionado1Id(obj.getMaDiagnosticoRelacionado1Id());
            }
            if (obj.getMaDiagnosticoRelacionado1Valor() != null) {
                ent.setMaDiagnosticoRelacionado1Valor(obj.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (obj.getMaDiagnosticoRelacionado2Codigo() != null) {
            ent.setMaDiagnosticoRelacionado2Codigo(obj.getMaDiagnosticoRelacionado2Codigo());
            if (obj.getMaDiagnosticoRelacionado2Id() != null) {
                ent.setMaDiagnosticoRelacionado2Id(obj.getMaDiagnosticoRelacionado2Id());
            }
            if (obj.getMaDiagnosticoRelacionado2Valor() != null) {
                ent.setMaDiagnosticoRelacionado2Valor(obj.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoMuerteCodigo());
            if (obj.getMaDiagnosticoMuerteId() != null) {
                ent.setMaDiagnosticoMuerteId(obj.getMaDiagnosticoMuerteId());
            }
            if (obj.getMaDiagnosticoMuerteValor() != null) {
                ent.setMaDiagnosticoMuerteValor(obj.getMaDiagnosticoMuerteValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoMuerteCodigo());
            if (obj.getMaDiagnosticoMuerteId() != null) {
                ent.setMaDiagnosticoMuerteId(obj.getMaDiagnosticoMuerteId());
            }
            if (obj.getMaDiagnosticoMuerteValor() != null) {
                ent.setMaDiagnosticoMuerteValor(obj.getMaDiagnosticoMuerteValor());
            }
        }
        //MA DIAGNOSTICO COMPLIACION
        if (obj.getMaDiagnosticoComplicacionCodigo() != null) {
            ent.setMaDiagnosticoComplicacionCodigo(obj.getMaDiagnosticoComplicacionCodigo());
            if (obj.getMaDiagnosticoComplicacionId() != null) {
                ent.setMaDiagnosticoComplicacionId(obj.getMaDiagnosticoComplicacionId());
            }
            if (obj.getMaDiagnosticoComplicacionValor() != null) {
                ent.setMaDiagnosticoComplicacionValor(obj.getMaDiagnosticoComplicacionValor());
            }
        }
        //MA DIAGNOSTICO COMPLIACION
        if (obj.getMaDiagnosticoComplicacionCodigo() != null) {
            ent.setMaDiagnosticoComplicacionCodigo(obj.getMaDiagnosticoComplicacionCodigo());
            if (obj.getMaDiagnosticoComplicacionId() != null) {
                ent.setMaDiagnosticoComplicacionId(obj.getMaDiagnosticoComplicacionId());
            }
            if (obj.getMaDiagnosticoComplicacionValor() != null) {
                ent.setMaDiagnosticoComplicacionValor(obj.getMaDiagnosticoComplicacionValor());
            }
        }
        //MAE ESTADO SALIDA
        if (obj.getMaeEstadoSalidaCodigo() != null) {
            ent.setMaeEstadoSalidaCodigo(obj.getMaeEstadoSalidaCodigo());
            if (obj.getMaeEstadoSalidaId() != null) {
                ent.setMaeEstadoSalidaId(obj.getMaeEstadoSalidaId());
            }
            if (obj.getMaeEstadoSalidaValor() != null) {
                ent.setMaeEstadoSalidaValor(obj.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE
        if (obj.getMaeEstadoSalidaCodigo() != null) {
            ent.setMaeEstadoSalidaCodigo(obj.getMaeEstadoSalidaCodigo());
            if (obj.getMaeEstadoSalidaId() != null) {
                ent.setMaeEstadoSalidaId(obj.getMaeEstadoSalidaId());
            }
            if (obj.getMaeEstadoSalidaValor() != null) {
                ent.setMaeEstadoSalidaValor(obj.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE ID
        if (obj.getMaeEstadoSalidaCodigo() != null) {
            ent.setMaeEstadoSalidaCodigo(obj.getMaeEstadoSalidaCodigo());
            if (obj.getMaeEstadoSalidaId() != null) {
                ent.setMaeEstadoSalidaId(obj.getMaeEstadoSalidaId());
            }
            if (obj.getMaeEstadoSalidaValor() != null) {
                ent.setMaeEstadoSalidaValor(obj.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE VALOR
        if (obj.getMaeEstadoSalidaCodigo() != null) {
            ent.setMaeEstadoSalidaCodigo(obj.getMaeEstadoSalidaCodigo());
            if (obj.getMaeEstadoSalidaId() != null) {
                ent.setMaeEstadoSalidaId(obj.getMaeEstadoSalidaId());
            }
            if (obj.getMaeEstadoSalidaValor() != null) {
                ent.setMaeEstadoSalidaValor(obj.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE ID
        if (obj.getMaDiagnosticoMuerteCodigo() != null) {
            ent.setMaDiagnosticoMuerteCodigo(obj.getMaDiagnosticoMuerteCodigo());
            if (obj.getMaDiagnosticoMuerteId() != null) {
                ent.setMaDiagnosticoMuerteId(obj.getMaDiagnosticoMuerteId());
            }
            if (obj.getMaeEstadoSalidaValor() != null) {
                ent.setMaeEstadoSalidaValor(obj.getMaeEstadoSalidaValor());
            }
        }
        //MAE  VIA INGRESO
        if (obj.getMaeViaIngresoCodigo() != null) {
            ent.setMaeViaIngresoCodigo(obj.getMaeViaIngresoCodigo());
            if (obj.getMaeViaIngresoId() != null) {
                ent.setMaeViaIngresoId(obj.getMaeViaIngresoId());
            }
            if (obj.getMaeViaIngresoValor() != null) {
                ent.setMaeViaIngresoValor(obj.getMaeViaIngresoValor());
            }
        }
        //MAE  CAUSA EXTERNA
        if (obj.getMaeCausaExternaCodigo() != null) {
            ent.setMaeCausaExternaCodigo(obj.getMaeViaIngresoCodigo());
            if (obj.getMaeViaIngresoId() != null) {
                ent.setMaeViaIngresoId(obj.getMaeViaIngresoId());
            }
            if (obj.getMaeViaIngresoValor() != null) {
                ent.setMaeViaIngresoValor(obj.getMaeViaIngresoValor());
            }
        }
        //MA DIAGNOSTICO CODIGO
        ent.setFechaIngreso(obj.getFechaIngreso());
        ent.setHoraIngreso(obj.getHoraIngreso());
        ent.setAutorizacion(obj.getAutorizacion());
        if (obj.getMaDiagnosticoIngresoCodigo() != null) {
            ent.setMaDiagnosticoIngresoCodigo(obj.getMaDiagnosticoIngresoCodigo());
            if (obj.getMaDiagnosticoIngresoId() != null) {
                ent.setMaDiagnosticoIngresoId(obj.getMaDiagnosticoIngresoId());
            }
        }
        if (obj.getMaDiagnosticoIngresoValor() != null) {
            ent.setMaDiagnosticoIngresoValor(obj.getMaDiagnosticoIngresoValor());
        }
        //MA DIAGNOSTICO EGRESO CODIGO
        if (obj.getMaDiagnosticoEgresoCodigo() != null) {
            ent.setMaDiagnosticoEgresoCodigo(obj.getMaDiagnosticoEgresoCodigo());
            if (obj.getMaDiagnosticoEgresoId() != null) {
                ent.setMaDiagnosticoEgresoId(obj.getMaDiagnosticoEgresoId());
            }
            if (obj.getMaDiagnosticoEgresoValor() != null) {
                ent.setMaDiagnosticoEgresoValor(obj.getMaDiagnosticoEgresoValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1 CODIGO
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoEgresoCodigo());
            if (obj.getMaDiagnosticoRelacionado1Id() != null) {
                ent.setMaDiagnosticoRelacionado1Id(obj.getMaDiagnosticoRelacionado1Id());
            }
            if (obj.getMaDiagnosticoRelacionado1Valor() != null) {
                ent.setMaDiagnosticoRelacionado1Valor(obj.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2 CODIGO
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoEgresoCodigo());
            if (obj.getMaDiagnosticoRelacionado1Id() != null) {
                ent.setMaDiagnosticoRelacionado1Id(obj.getMaDiagnosticoRelacionado1Id());
            }
            if (obj.getMaDiagnosticoRelacionado1Valor() != null) {
                ent.setMaDiagnosticoRelacionado1Valor(obj.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3 CODIGO
        if (obj.getMaDiagnosticoMuerteCodigo() != null) {
            ent.setMaDiagnosticoMuerteCodigo(obj.getMaDiagnosticoEgresoCodigo());
            if (obj.getMaDiagnosticoMuerteId() != null) {
                ent.setMaDiagnosticoMuerteId(obj.getMaDiagnosticoMuerteId());
            }
            if (obj.getMaDiagnosticoMuerteValor() != null) {
                ent.setMaDiagnosticoMuerteValor(obj.getMaDiagnosticoMuerteValor());
            }
        }
        //MA DIAGNOSTICO COMPLICACION
        if (obj.getMaDiagnosticoComplicacionCodigo() != null) {
            ent.setMaDiagnosticoComplicacionCodigo(obj.getMaDiagnosticoComplicacionCodigo());
            if (obj.getMaDiagnosticoComplicacionId() != null) {
                ent.setMaDiagnosticoComplicacionId(obj.getMaDiagnosticoComplicacionId());
            }
            if (obj.getMaDiagnosticoComplicacionValor() != null) {
                ent.setMaDiagnosticoComplicacionValor(obj.getMaDiagnosticoComplicacionValor());
            }
        }
        //MAE ESTADO SALIDA CODIGO
        if (obj.getMaeEstadoSalidaCodigo() != null) {
            ent.setMaeEstadoSalidaCodigo(obj.getMaeEstadoSalidaCodigo());
            if (obj.getMaeEstadoSalidaId() != null) {
                ent.setMaeEstadoSalidaId(obj.getMaeEstadoSalidaId());
            }
            if (obj.getMaDiagnosticoComplicacionValor() != null) {
                ent.setMaDiagnosticoComplicacionValor(obj.getMaDiagnosticoComplicacionValor());
            }
        }
        //MA DIAGNOSTICO MUERTE CODIGO
        if (obj.getMaDiagnosticoMuerteCodigo() != null) {
            ent.setMaDiagnosticoMuerteCodigo(obj.getMaDiagnosticoMuerteCodigo());
            if (obj.getMaDiagnosticoMuerteId() != null) {
                ent.setMaDiagnosticoMuerteId(obj.getMaDiagnosticoMuerteId());
            }
            if (obj.getMaDiagnosticoComplicacionValor() != null) {
                ent.setMaDiagnosticoComplicacionValor(obj.getMaDiagnosticoComplicacionValor());
            }
        }
        ent.setFechaIngreso(obj.getFechaIngreso());
        ent.setHoraIngreso(obj.getHoraIngreso());
        ent.setAutorizacion(obj.getAutorizacion());
        ent.setFechaSalida(obj.getFechaSalida());
        ent.setHoraSalida(obj.getHoraSalida());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaAmMedicamentos castNegocioEntidad(CmRipsAmMedicamento obj) {
        CmRipsCargaAmMedicamentos ent = new CmRipsCargaAmMedicamentos();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setFila(obj.getFila());
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumeroFactura(obj.getNumeroFactura());
        //MAE TIPO DOCUMENTO
        ent.setCodigoReps(obj.getCodigoReps());
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setDocumentoAfiliado(obj.getDocumentoAfiliado());
        ent.setAutorizacion(obj.getAutorizacion());
        //MA MEDICAMENTO
        if (obj.getMaMedicamentoCodigo() != null) {
            ent.setMaMedicamentoCodigo(obj.getMaMedicamentoCodigo());
            if (obj.getMaMedicamentoId() != null) {
                ent.setMaMedicamentoId(obj.getMaMedicamentoId());
            }
            if (obj.getMaMedicamentoCodigo() != null) {
                ent.setMaMedicamentoValor(obj.getMaMedicamentoValor());
            }
        }
        //MAE TIPO MEDICAMENTO
        if (obj.getMaeTipoMedicamentoCodigo() != null) {
            ent.setMaeTipoMedicamentoCodigo(obj.getMaeTipoMedicamentoCodigo());
            if (obj.getMaeTipoMedicamentoId() != null) {
                ent.setMaeTipoMedicamentoId(obj.getMaeTipoMedicamentoId());
            }
            if (obj.getMaeTipoMedicamentoValor() != null) {
                ent.setMaeTipoMedicamentoValor(obj.getMaeTipoMedicamentoValor());
            }
        }
        ent.setNombreGenerico(obj.getNombreGenerico());
        if (obj.getFormaFarmaceutica() != null) {
            ent.setFormaFarmaceutica(obj.getFormaFarmaceutica());
        }
        if (obj.getConcentracion() != null) {
            ent.setConcentracion(obj.getConcentracion());
        }
        if (obj.getUnidadMedida() != null) {
            ent.setUnidadMedida(obj.getUnidadMedida());
        }
        ent.setNumeroUnidades(obj.getNumeroUnidad());
        ent.setValorUnitario(obj.getValorUnitario());
        ent.setValorAPagar(obj.getValorAPagar());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaAnRecienNacidos castNegocioEntidad(CmRipsAnRecienNacido obj) {
        CmRipsCargaAnRecienNacidos ent = new CmRipsCargaAnRecienNacidos();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setFila(obj.getFila());
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumeroFactura(obj.getNumeroFactura());
        ent.setCodigoReps(obj.getCodigoReps());
        //MAE TIPO DOCUMENTO
        ent.setCodigoReps(obj.getCodigoReps());
        if (obj.getMaeTipoDocumentoMadreCodigo() != null) {
            ent.setMaeTipoDocumentoMadreCodigo(obj.getMaeTipoDocumentoMadreCodigo());
            if (obj.getMaeTipoDocumentoMadreId() != null) {
                ent.setMaeTipoDocumentoMadreId(obj.getMaeTipoDocumentoMadreId());
            }
            if (obj.getMaeTipoDocumentoMadreValor() != null) {
                ent.setMaeTipoDocumentoMadreValor(obj.getMaeTipoDocumentoMadreValor());
            }
        }
        ent.setCodigoReps(obj.getCodigoReps());
        //MAE CONTROL PENATAL
        if (obj.getMaeControlPenatalCodigo() != null) {
            ent.setMaeControlPenatalCodigo(obj.getMaeControlPenatalCodigo());
            if (obj.getMaeControlPenatalId() != null) {
                ent.setMaeControlPenatalId(obj.getMaeControlPenatalId());
            }
            if (obj.getMaeControlPenatalValor() != null) {
                ent.setMaeControlPenatalValor(obj.getMaeControlPenatalValor());
            }
        }
        //MAE SEXO CODIGO
        if (obj.getMaeSexoCodigo() != null) {
            ent.setMaeSexoCodigo(obj.getMaeSexoCodigo());
            if (obj.getMaeSexoId() != null) {
                ent.setMaeSexoId(obj.getMaeSexoId());
            }
            if (obj.getMaeSexoValor() != null) {
                ent.setMaeSexoValor(obj.getMaeSexoValor());
            }
        }
        //MA DIAGNOSTICO PRINCIPAL
        if (obj.getMaDiagnosticoPrincipalCodigo() != null) {
            ent.setMaDiagnosticoPrincipalCodigo(obj.getMaDiagnosticoPrincipalCodigo());
            if (obj.getMaDiagnosticoPrincipalId() != null) {
                ent.setMaDiagnosticoPrincipalId(obj.getMaDiagnosticoPrincipalId());
            }
            if (obj.getMaDiagnosticoPrincipalValor() != null) {
                ent.setMaDiagnosticoPrincipalValor(obj.getMaDiagnosticoPrincipalValor());
            }
        }
        ent.setDocumentoAfiliadoMadre(obj.getDocumentoAfiliadoMadre());
        ent.setFechaNacimiento(obj.getFechaNacimiento());
        ent.setHoraNacimiento(obj.getHoraNacimiento());
        if (obj.getEdadGestacion() != null) {
            ent.setEdadGestacion(obj.getEdadGestacion());
        }
        //MA CAUSA MUERTE
        if (obj.getMaCausaMuerteDiagnosticoCodigo() != null) {
            ent.setMaCausaMuerteDiagnosticoCodigo(obj.getMaCausaMuerteDiagnosticoCodigo());
            if (obj.getMaCausaMuerteDiagnosticoId() != null) {
                ent.setMaCausaMuerteDiagnosticoId(obj.getMaCausaMuerteDiagnosticoId());
            }
            if (obj.getMaCausaMuerteDiagnosticoValor() != null) {
                ent.setMaCausaMuerteDiagnosticoValor(obj.getMaDiagnosticoPrincipalValor());
            }
        }
        if (obj.getPeso() != null) {
            ent.setPeso(obj.getPeso());
        }
        if (obj.getFechaMuerte() != null) {
            ent.setFechaMuerte(obj.getFechaMuerte());
        }
        if (obj.getHoraMuerte() != null) {
            ent.setHoraMuerte(obj.getHoraMuerte());
        }
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaApProcedimientos castNegocioEntidad(CmRipsApProcedimiento obj) {
        CmRipsCargaApProcedimientos ent = new CmRipsCargaApProcedimientos();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setFila(obj.getFila());
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumeroFactura(obj.getNumeroFactura());
        ent.setCodigoReps(obj.getCodigoReps());
        //MAE TIPO DOCUMENTO
        ent.setCodigoReps(obj.getCodigoReps());
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setDocumentoAfiliado(obj.getDocumentoAfiliado());
        ent.setFechaProcedimiento(obj.getFechaProcedimiento());
        ent.setAutorizacion(obj.getAutorizacion());
        //ent.setMaeTecnologiaCodigo(obj.getCodigoServicio());
        //MAE CODIGO AMBITO ATENCION
        ent.setCodigoReps(obj.getCodigoReps());
        if (obj.getMaeAmbitoAtencionCodigo() != null) {
            ent.setMaeAmbitoAtencionCodigo(obj.getMaeAmbitoAtencionCodigo());
            if (obj.getMaeAmbitoAtencionId() != null) {
                ent.setMaeAmbitoAtencionId(obj.getMaeAmbitoAtencionId());
            }
            if (obj.getMaeAmbitoAtencionValor() != null) {
                ent.setMaeAmbitoAtencionCodigoValor(obj.getMaeAmbitoAtencionValor());
            }
        }
        //MAE FINALIDAD PROCEDIMIENTOS
        ent.setCodigoReps(obj.getCodigoReps());
        if (obj.getMaeFinalidadProcedimientoCodigo() != null) {
            ent.setMaeFinalidadProcedimientoCodigo(obj.getMaeFinalidadProcedimientoCodigo());
            if (obj.getMaeFinalidadProcedimientoId() != null) {
                ent.setMaeFinalidadProcedimientoId(obj.getMaeFinalidadProcedimientoId());
            }
            if (obj.getMaeFinalidadProcedimientoValor() != null) {
                ent.setMaeFinalidadProcedimientoValor(obj.getMaeFinalidadProcedimientoValor());
            }
        }
        //MAE PERSONAL ATIENDE
        ent.setCodigoReps(obj.getCodigoReps());
        if (obj.getMaePersonalAtiendeCodigo() != null) {
            ent.setMaePersonalAtiendeCodigo(obj.getMaePersonalAtiendeCodigo());
            if (obj.getMaePersonalAtiendeId() != null) {
                ent.setMaePersonalAtiendeId(obj.getMaePersonalAtiendeId());
            }
            if (obj.getMaePersonalAtiendeValor() != null) {
                ent.setMaePersonalAtiendeValor(obj.getMaePersonalAtiendeValor());
            }
        }
        //MAE DIAGNOSTICO PRINCIPAL
        if (obj.getMaDiagnosticoPrincipalCodigo() != null) {
            ent.setMaDiagnosticoPrincipalCodigo(obj.getMaDiagnosticoPrincipalCodigo());
            if (obj.getMaDiagnosticoPrincipalId() != null) {
                ent.setMaDiagnosticoPrincipalId(obj.getMaDiagnosticoPrincipalId());
            }
            if (obj.getMaDiagnosticoPrincipalValor() != null) {
                ent.setMaDiagnosticoPrincipalValor(obj.getMaDiagnosticoPrincipalValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoRelacionado1Codigo());
            if (obj.getMaDiagnosticoRelacionado1Id() != null) {
                ent.setMaDiagnosticoRelacionado1Id(obj.getMaDiagnosticoRelacionado1Id());
            }
            if (obj.getMaDiagnosticoRelacionado1Valor() != null) {
                ent.setMaDiagnosticoRelacionado1Valor(obj.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (obj.getMaDiagnosticoRelacionado2Codigo() != null) {
            ent.setMaDiagnosticoRelacionado2Codigo(obj.getMaDiagnosticoRelacionado2Codigo());
            if (obj.getMaDiagnosticoRelacionado2Id() != null) {
                ent.setMaDiagnosticoRelacionado2Id(obj.getMaDiagnosticoRelacionado2Id());
            }
            if (obj.getMaDiagnosticoRelacionado2Valor() != null) {
                ent.setMaDiagnosticoRelacionado2Valor(obj.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MAE FORMA ACTO
        if (obj.getMaeFormaActoCodigo() != null) {
            ent.setMaeFormaActoCodigo(obj.getMaeFormaActoCodigo());
            if (obj.getMaeFormaActoId() != null) {
                ent.setMaeFormaActoId(obj.getMaeFormaActoId());
            }
            if (obj.getMaeFormaActoValor() != null) {
                ent.setMaeFormaActoValor(obj.getMaeFormaActoValor());
            }
        }
        ent.setValorAPagar(obj.getValorAPagar());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaAtOtrosServicios castNegocioEntidad(CmRipsAtOtrosServicio obj) {
        CmRipsCargaAtOtrosServicios ent = new CmRipsCargaAtOtrosServicios();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setFila(obj.getFila());
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumeroFactura(obj.getNumeroFactura());
        ent.setCodigoReps(obj.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setDocumentoAfiliado(obj.getDocumentoAfiliado());
        ent.setAutorizacion(obj.getAutorizacion());
        //MAE TIPO SERVICIO
        if (obj.getMaeTipoServicioCodigo() != null) {
            ent.setMaeTipoServicioCodigo(obj.getMaeTipoServicioCodigo());
            if (obj.getMaeTipoServicioId() != null) {
                ent.setMaeTipoServicioId(obj.getMaeTipoServicioId());
            }
            if (obj.getMaeTipoServicioValor() != null) {
                ent.setMaeTipoServicioValor(obj.getMaeTipoServicioValor());
            }
        }
        //MA TECNOLOGIAS
        if (obj.getMaTecnologiaCodigo() != null) {
            ent.setMaTecnologiaCodigo(obj.getMaTecnologiaCodigo());
            if (obj.getMaTecnologiaId() != null) {
                ent.setMaTecnologiaId(obj.getMaTecnologiaId());
            }
            if (obj.getMaTecnologiaValor() != null) {
                ent.setMaTecnologiaValor(obj.getMaTecnologiaValor());
            }
        }
        ent.setCodigoServicio(obj.getMaTecnologiaCodigo());
        ent.setNombreServicio(obj.getNombreServicio());
        ent.setUnidades(obj.getUnidades());
        ent.setValorUnidades(obj.getValorUnidades());
        ent.setTotal(obj.getTotal());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaAuUrgencias castNegocioEntidad(CmRipsAuUrgencia obj) {
        CmRipsCargaAuUrgencias ent = new CmRipsCargaAuUrgencias();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setFila(obj.getFila());
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumeroFactura(obj.getNumeroFactura());
        ent.setCodigoReps(obj.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setDocumentoAfiliado(obj.getDocumentoAfiliado());
        ent.setFechaIngreso(obj.getFechaIngreso());
        ent.setHoraIngreso(obj.getHoraIngreso());
        ent.setAutorizacion(obj.getAutorizacion());
        //MAE CAUSA EXTERNA
        if (obj.getMaeCausaExternaCodigo() != null) {
            ent.setMaeCausaExternaCodigo(obj.getMaeCausaExternaCodigo());
            if (obj.getMaeCausaExternaId() != null) {
                ent.setMaeCausaExternaId(obj.getMaeCausaExternaId());
            }
            if (obj.getMaeCausaExternaValor() != null) {
                ent.setMaeCausaExternaValor(obj.getMaeCausaExternaValor());
            }
        }
        if (obj.getMaDiagnosticoSalidaCodigo() != null) {
            ent.setMaDiagnosticoSalidaCodigo(obj.getMaDiagnosticoSalidaCodigo());
            if (obj.getMaDiagnosticoSalidaId() != null) {
                ent.setMaDiagnosticoSalidaId(obj.getMaDiagnosticoSalidaId());
            }
            if (obj.getMaDiagnosticoSalidaValor() != null) {
                ent.setMaDiagnosticoSalidaValor(obj.getMaDiagnosticoSalidaValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoRelacionado1Codigo());
            if (obj.getMaDiagnosticoRelacionado1Id() != null) {
                ent.setMaDiagnosticoRelacionado1Id(obj.getMaDiagnosticoRelacionado1Id());
            }
            if (obj.getMaDiagnosticoRelacionado1Valor() != null) {
                ent.setMaDiagnosticoRelacionado1Valor(obj.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (obj.getMaDiagnosticoRelacionado2Codigo() != null) {
            ent.setMaDiagnosticoRelacionado2Codigo(obj.getMaDiagnosticoRelacionado2Codigo());
            if (obj.getMaDiagnosticoRelacionado2Id() != null) {
                ent.setMaDiagnosticoRelacionado2Id(obj.getMaDiagnosticoRelacionado2Id());
            }
            if (obj.getMaDiagnosticoRelacionado2Valor() != null) {
                ent.setMaDiagnosticoRelacionado2Valor(obj.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO4
        if (obj.getMaDiagnosticoRelacionado3Codigo() != null) {
            ent.setMaDiagnosticoRelacionado3Codigo(obj.getMaDiagnosticoRelacionado3Codigo());
            if (obj.getMaDiagnosticoRelacionado3Id() != null) {
                ent.setMaDiagnosticoRelacionado3Id(obj.getMaDiagnosticoRelacionado3Id());
            }
            if (obj.getMaDiagnosticoRelacionado3Valor() != null) {
                ent.setMaDiagnosticoRelacionado3Valor(obj.getMaDiagnosticoRelacionado3Valor());
            }
        }
        //MA DIAGNOSTICO MUERTE
        if (obj.getMaDiagnosticoMuerteCodigo() != null) {
            ent.setMaDiagnosticoMuerteCodigo(obj.getMaDiagnosticoMuerteCodigo());
            if (obj.getMaDiagnosticoMuerteId() != null) {
                ent.setMaDiagnosticoMuerteId(obj.getMaDiagnosticoMuerteId());
            }
            if (obj.getMaDiagnosticoMuerteValor() != null) {
                ent.setMaDiagnosticoMuerteValor(obj.getMaDiagnosticoMuerteValor());
            }
        }
        //MAE DESTINO SALIDA
        if (obj.getMaeDestinoSalidaCodigo() != null) {
            ent.setMaeDestinoSalidaCodigo(obj.getMaeDestinoSalidaCodigo());
            if (obj.getMaeDestinoSalidaId() != null) {
                ent.setMaeDestinoSalidaId(obj.getMaeDestinoSalidaId());
            }
            if (obj.getMaeDestinoSalidaValor() != null) {
                ent.setMaeDestinoSalidaValor(obj.getMaeDestinoSalidaValor());
            }
        }
        //MAE ESTADO SALIDA
        if (obj.getMaeEstadoSalidaCodigo() != null) {
            ent.setMaeEstadoSalidaCodigo(obj.getMaeEstadoSalidaCodigo());
            if (obj.getMaeEstadoSalidaId() != null) {
                ent.setMaeEstadoSalidaId(obj.getMaeEstadoSalidaId());
            }
            if (obj.getMaeEstadoSalidaValor() != null) {
                ent.setMaeEstadoSalidaValor(obj.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (obj.getMaDiagnosticoMuerteCodigo() != null) {
            ent.setMaDiagnosticoMuerteCodigo(obj.getMaDiagnosticoMuerteCodigo());
            if (obj.getMaDiagnosticoMuerteId() != null) {
                ent.setMaDiagnosticoMuerteId(obj.getMaDiagnosticoMuerteId());
            }
            if (obj.getMaDiagnosticoMuerteValor() != null) {
                ent.setMaDiagnosticoMuerteValor(obj.getMaDiagnosticoMuerteValor());
            }
        }
        ent.setFechaSalida(obj.getFechaSalida());
        ent.setHoraSalida(obj.getHoraSalida());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaUsUsuarios castNegocioEntidad(CmRipsUsUsuario obj) {
        CmRipsCargaUsUsuarios ent = new CmRipsCargaUsUsuarios();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setFila(obj.getFila());
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        //MAE TIPO DOCUMENTO
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setDocumentoAfiliado(obj.getDocumentoAfiliado());
        if (obj.getAdministradora() != null) {
            ent.setAdministradora(obj.getAdministradora());
        }
        //MAE TIPO USUARIO
        if (obj.getMaeTipoUsuarioCodigo() != null) {
            ent.setMaeTipoUsuarioCodigo(obj.getMaeTipoUsuarioCodigo());
            if (obj.getMaeTipoUsuarioId() != null) {
                ent.setMaeTipoUsuarioId(obj.getMaeTipoUsuarioId());
            }
            if (obj.getMaeTipoUsuarioValor() != null) {
                ent.setMaeTipoUsuarioValor(obj.getMaeTipoUsuarioValor());
            }
        }
        ent.setPrimerApellido(obj.getPrimerApellido());
        ent.setSegundoApellido(obj.getSegundoApellido());
        ent.setPrimerNombre(obj.getPrimerNombre());
        ent.setSegundoNombre(obj.getSegundoNombre());
        if (obj.getEdad() != null) {
            ent.setEdad(obj.getEdad());
        }
        if (obj.getCodigoUnidadMedidaEdad() != null) {
            ent.setCodigoUnidadMedidaEdad(obj.getCodigoUnidadMedidaEdad());
        }
        //MAE SEXO
        if (obj.getMaeSexoCodigo() != null) {
            ent.setMaeSexoCodigo(obj.getMaeSexoCodigo());
            if (obj.getMaeSexoId() != null) {
                ent.setMaeSexoId(obj.getMaeTipoUsuarioId());
            }
            if (obj.getMaeSexoValor() != null) {
                ent.setMaeSexoValor(obj.getMaeSexoValor());
            }
        }
        //MAE DEPARTAMENTO
        if (obj.getDepartamentoCodigo() != null) {
            ent.setDepartamentoCodigo(obj.getDepartamentoCodigo());
            if (obj.getDepartamentoId() != null) {
                ent.setDepartamentoId(obj.getDepartamentoId());
            }
            if (obj.getDepartamentoNombre() != null) {
                ent.setDepartamentoNombre(obj.getDepartamentoNombre());
            }
        }
        //MAE CIUDAD
        if (obj.getCiudadCodigo() != null) {
            ent.setCiudadCodigo(obj.getCiudadCodigo());
            if (obj.getCiudadId() != null) {
                ent.setCiudadId(obj.getCiudadId());
            }
            if (obj.getCiudadNombre() != null) {
                ent.setCiudadNombre(obj.getCiudadNombre());
            }
        }
        //MAE ZONA RESIDENCIA
        if (obj.getMaeZonaResidenciaCodigo() != null) {
            ent.setMaeZonaResidenciaCodigo(obj.getMaeZonaResidenciaCodigo());
            if (obj.getMaeZonaResidenciaId() != null) {
                ent.setMaeZonaResidenciaId(obj.getMaeZonaResidenciaId());
            }
            if (obj.getMaeZonaResidenciaValor() != null) {
                ent.setMaeZonaResidenciaValor(obj.getMaeZonaResidenciaValor());
            }
        }
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    /*
    NEGOCIO ENTIDAD CAST TABLAS FINALES
    MANTENER DUPLICADO CON CAST CARGA
     */
    public static CmRipsCtControl castNegocioEntidadFinal(CmRipsCtControlObj obj) {
        CmRipsCtControl ent = new CmRipsCtControl();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setCodigoReps(obj.getCodigoReps());
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setArchivoControl(obj.getArchivoControl());
        ent.setArchivoAf(obj.getArchivoAf());
        ent.setArchivoUs(obj.getArchivoUs());
        ent.setArchivoAd(obj.getArchivoAd());
        ent.setArchivoAc(obj.getArchivoAc());
        ent.setArchivoAp(obj.getArchivoAp());
        ent.setArchivoAh(obj.getArchivoAh());
        ent.setArchivoAu(obj.getArchivoAu());
        ent.setArchivoAn(obj.getArchivoAn());
        ent.setArchivoAm(obj.getArchivoAm());
        ent.setArchivoAt(obj.getArchivoAt());
        ent.setRegistrosAf(obj.getRegistrosAf());
        ent.setRegistroAc(obj.getRegistroAc());
        ent.setRegistrosUs(obj.getRegistrosUs());
        ent.setRegistrosAp(obj.getRegistrosAp());
        ent.setRegistrosAh(obj.getRegistrosAh());
        ent.setRegistrosAu(obj.getRegistrosAu());
        ent.setRegistrosAm(obj.getRegistrosAm());
        ent.setRegistrosAt(obj.getRegistrosAt());
        ent.setRegistrosAn(obj.getRegistrosAn());
        ent.setRegistrosAd(obj.getRegistrosAd());
        ent.setFechaAf(obj.getFechaAf());
        ent.setFechaUs(obj.getFechaUs());
        ent.setFechaAc(obj.getFechaAc());
        ent.setFechaAp(obj.getFechaAp());
        ent.setFechaAh(obj.getFechaAh());
        ent.setFechaAu(obj.getFechaAu());
        ent.setFechaAm(obj.getFechaAm());
        ent.setFechaAt(obj.getFechaAt());
        ent.setFechaAn(obj.getFechaAn());
        ent.setFechaAd(obj.getFechaAd());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsAcConsultas castNegocioEntidadFinal(CmRipsAcConsulta obj) {
        CmRipsAcConsultas ent = new CmRipsAcConsultas();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumFactura(obj.getNumFactura());
        ent.setCodigoReps(obj.getCodigoReps());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setArchivoNombre(obj.getArchivoNombre());
        //MAE TIPO DOCUMENTO
        ent.setCodigoReps(obj.getCodigoReps());
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setDocumentoAfiliado(obj.getDocumentoAfiliado());
        if (obj.getFechaConsulta() != null) {
            ent.setFechaConsulta(obj.getFechaConsulta());
        }
        ent.setAutorizacion(obj.getAutorizacion());
        if (obj.getMaTecnologiaCodigo() != null) {
            ent.setMaTecnologiaCodigo(obj.getMaTecnologiaCodigo());
        }
        if (obj.getMaTecnologiaId() != null) {
            ent.setMaTecnologiaId(obj.getMaTecnologiaId());
        }
        if (obj.getMaTecnologiaValor() != null) {
            ent.setMaTecnologiaValor(obj.getMaTecnologiaValor());
        }
        //MAE FINALIDAD CONSULTA
        if (obj.getMaeFinalidadConsultaCodigo() != null) {
            ent.setMaeFinalidadConsultaCodigo(obj.getMaeFinalidadConsultaCodigo());
            if (obj.getMaeFinalidadConsultaId() != null) {
                ent.setMaeFinalidadConsultaId(obj.getMaeFinalidadConsultaId());
            }
            if (obj.getMaeFinalidadConsultaValor() != null) {
                ent.setMaeFinalidadConsultaValor(obj.getMaeFinalidadConsultaValor());
            }
        }
        //MAE CAUSA EXTERNA
        if (obj.getMaeCausaExternaCodigo() != null) {
            ent.setMaeCausaExternaCodigo(obj.getMaeCausaExternaCodigo());
            if (obj.getMaeCausaExternaId() != null) {
                ent.setMaeCausaExternaId(obj.getMaeCausaExternaId());
            }
            if (obj.getMaeCausaExternaValor() != null) {
                ent.setMaeCausaExternaValor(obj.getMaeCausaExternaValor());
            }
        }
        //MA DIAGNOSTICO
        if (obj.getMaDiagnosticoPrincipalCodigo() != null) {
            ent.setMaDiagnosticoPrincipalCodigo(obj.getMaDiagnosticoPrincipalCodigo());
            if (obj.getMaDiagnosticoPrincipalId() != null) {
                ent.setMaDiagnosticoPrincipalId(obj.getMaDiagnosticoPrincipalId());
            }
            if (obj.getMaDiagnosticoPrincipalValor() != null) {
                ent.setMaDiagnosticoPrincipalValor(obj.getMaDiagnosticoPrincipalValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoRelacionado1Codigo());
            if (obj.getMaDiagnosticoRelacionado1Id() != null) {
                ent.setMaDiagnosticoRelacionado1Id(obj.getMaDiagnosticoRelacionado1Id());
            }
            if (obj.getMaDiagnosticoRelacionado1Valor() != null) {
                ent.setMaDiagnosticoRelacionado1Valor(obj.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (obj.getMaDiagnosticoRelacionado2Codigo() != null) {
            ent.setMaDiagnosticoRelacionado2Codigo(obj.getMaDiagnosticoRelacionado2Codigo());
            if (obj.getMaDiagnosticoRelacionado2Id() != null) {
                ent.setMaDiagnosticoRelacionado2Id(obj.getMaDiagnosticoRelacionado2Id());
            }
            if (obj.getMaDiagnosticoRelacionado2Valor() != null) {
                ent.setMaDiagnosticoRelacionado2Valor(obj.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (obj.getMaDiagnosticoRelacionado3Codigo() != null) {
            ent.setMaDiagnosticoRelacionado3Codigo(obj.getMaDiagnosticoRelacionado3Codigo());
            if (obj.getMaDiagnosticoRelacionado3Id() != null) {
                ent.setMaDiagnosticoRelacionado3Id(obj.getMaDiagnosticoRelacionado3Id());
            }
            if (obj.getMaDiagnosticoRelacionado3Valor() != null) {
                ent.setMaDiagnosticoRelacionado3Valor(obj.getMaDiagnosticoRelacionado3Valor());
            }
        }
        //MA TIPO DIAGNOSTICO 
        if (obj.getMaeTipoDiagnosticoCodigo() != null) {
            ent.setMaeTipoDiagnosticoCodigo(obj.getMaeTipoDiagnosticoCodigo());
            if (obj.getMaeTipoDiagnosticoId() != null) {
                ent.setMaeTipoDiagnosticoId(obj.getMaeTipoDiagnosticoId());
            }
            if (obj.getMaeTipoDiagnosticoValor() != null) {
                ent.setMaeTipoDiagnosticoValor(obj.getMaeTipoDiagnosticoValor());
            }
        }
        ent.setValorConsulta(obj.getValorConsulta());
        //TODO REMOVER TO STRING CUANDO SE MODIFIQUE A BIGDECIMAL
        ent.setValorCuotaModeradora(obj.getValorCuotaModeradora());
        ent.setValorAPagar(obj.getValorAPagar());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsAdServiciosAgrupados castNegocioEntidadFinal(CmRipsAdServiciosAgrupado obj) {
        CmRipsAdServiciosAgrupados ent = new CmRipsAdServiciosAgrupados();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumeroFactura(obj.getNumeroFactura());
        ent.setCodigoReps(obj.getCodigoReps());
        ent.setCantidadServicios(obj.getCantidadServicio());
        ent.setValorUnitario(obj.getValorUnitario());
        ent.setValorConcepto(obj.getValorConcepto());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsAfFacturas castNegocioEntidadFinal(CmRipsAfFactura obj) {
        CmRipsAfFacturas ent = new CmRipsAfFacturas();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setCodigoReps(obj.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setRazonSocial(obj.getRazonSocial());
        ent.setNit(obj.getNit().trim());
        ent.setNumeroFactura(obj.getNumeroFactura());
        ent.setFechaFactura(obj.getFechaFactura());
        if (obj.getFechaInicio() != null) {
            ent.setFechaInicio(obj.getFechaInicio());
        }
        if (obj.getFechaFinal() != null) {
            ent.setFechaFinal(obj.getFechaFinal());
        }
        ent.setCodigoEps(obj.getCodigoEps());
        if (obj.getNombreAdministradora() != null) {
            ent.setNombreAdministradora(obj.getNombreAdministradora());
        }
        if (obj.getContrato() != null) {
            ent.setContrato(obj.getContrato());
        }
        if (obj.getPlanBeneficio() != null) {
            ent.setPlanBeneficios(obj.getPlanBeneficio());
        }
        if (obj.getNumeroPoliza() != null) {
            ent.setNumeroPoliza(obj.getNumeroPoliza());
        }
        ent.setValorCopago(obj.getValorCopago());
        ent.setValorAPagar(obj.getValorAPagar());
        if (obj.getValorComision() != null) {
            ent.setValorComision(obj.getValorComision());
        }
        if (obj.getValorDescuento() != null) {
            ent.setValorDescuento(obj.getValorDescuento());
        }
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsAhHospitalizaciones castNegocioEntidadFinal(CmRipsAhHospitalizacion obj) {
        CmRipsAhHospitalizaciones ent = new CmRipsAhHospitalizaciones();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumeroFactura(obj.getNumeroFactura());
        ent.setCodigoReps(obj.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setDocumentoAfiliado(obj.getDocumentoAfiliado());
        //MAE VIA INGRESO
        if (obj.getMaeViaIngresoCodigo() != null) {
            ent.setMaeViaIngresoCodigo(obj.getMaeViaIngresoCodigo());
            if (obj.getMaeViaIngresoId() != null) {
                ent.setMaeViaIngresoId(obj.getMaeViaIngresoId());
            }
            if (obj.getMaeViaIngresoValor() != null) {
                ent.setMaeViaIngresoValor(obj.getMaeViaIngresoValor());
            }
        }
        //MAE CAUSA EXTERNA
        if (obj.getMaeCausaExternaCodigo() != null) {
            ent.setMaeCausaExternaCodigo(obj.getMaeViaIngresoCodigo());
            if (obj.getMaeCausaExternaId() != null) {
                ent.setMaeCausaExternaId(obj.getMaeCausaExternaId());
            }
            if (obj.getMaeCausaExternaValor() != null) {
                ent.setMaeCausaExternaValor(obj.getMaeCausaExternaValor());
            }
        }
        //MA DIAGNOSTICO INGRESO
        if (obj.getMaDiagnosticoIngresoCodigo() != null) {
            ent.setMaDiagnosticoIngresoCodigo(obj.getMaDiagnosticoIngresoCodigo());
            if (obj.getMaDiagnosticoIngresoId() != null) {
                ent.setMaDiagnosticoIngresoId(obj.getMaDiagnosticoIngresoId());
            }
            if (obj.getMaDiagnosticoIngresoValor() != null) {
                ent.setMaDiagnosticoIngresoValor(obj.getMaDiagnosticoIngresoValor());
            }
        }
        //MA DIAGNOSTICO EGRESO
        if (obj.getMaDiagnosticoEgresoCodigo() != null) {
            ent.setMaDiagnosticoEgresoCodigo(obj.getMaDiagnosticoEgresoCodigo());
            if (obj.getMaDiagnosticoEgresoId() != null) {
                ent.setMaDiagnosticoEgresoId(obj.getMaDiagnosticoEgresoId());
            }
            if (obj.getMaDiagnosticoEgresoValor() != null) {
                ent.setMaDiagnosticoEgresoValor(obj.getMaDiagnosticoEgresoValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoRelacionado1Codigo());
            if (obj.getMaDiagnosticoRelacionado1Id() != null) {
                ent.setMaDiagnosticoRelacionado1Id(obj.getMaDiagnosticoRelacionado1Id());
            }
            if (obj.getMaDiagnosticoRelacionado1Valor() != null) {
                ent.setMaDiagnosticoRelacionado1Valor(obj.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (obj.getMaDiagnosticoRelacionado2Codigo() != null) {
            ent.setMaDiagnosticoRelacionado2Codigo(obj.getMaDiagnosticoRelacionado2Codigo());
            if (obj.getMaDiagnosticoRelacionado2Id() != null) {
                ent.setMaDiagnosticoRelacionado2Id(obj.getMaDiagnosticoRelacionado2Id());
            }
            if (obj.getMaDiagnosticoRelacionado2Valor() != null) {
                ent.setMaDiagnosticoRelacionado2Valor(obj.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoMuerteCodigo());
            if (obj.getMaDiagnosticoMuerteId() != null) {
                ent.setMaDiagnosticoMuerteId(obj.getMaDiagnosticoMuerteId());
            }
            if (obj.getMaDiagnosticoMuerteValor() != null) {
                ent.setMaDiagnosticoMuerteValor(obj.getMaDiagnosticoMuerteValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoMuerteCodigo());
            if (obj.getMaDiagnosticoMuerteId() != null) {
                ent.setMaDiagnosticoMuerteId(obj.getMaDiagnosticoMuerteId());
            }
            if (obj.getMaDiagnosticoMuerteValor() != null) {
                ent.setMaDiagnosticoMuerteValor(obj.getMaDiagnosticoMuerteValor());
            }
        }
        //MA DIAGNOSTICO COMPLIACION
        if (obj.getMaDiagnosticoComplicacionCodigo() != null) {
            ent.setMaDiagnosticoComplicacionCodigo(obj.getMaDiagnosticoComplicacionCodigo());
            if (obj.getMaDiagnosticoComplicacionId() != null) {
                ent.setMaDiagnosticoComplicacionId(obj.getMaDiagnosticoComplicacionId());
            }
            if (obj.getMaDiagnosticoComplicacionValor() != null) {
                ent.setMaDiagnosticoComplicacionValor(obj.getMaDiagnosticoComplicacionValor());
            }
        }
        //MA DIAGNOSTICO COMPLIACION
        if (obj.getMaDiagnosticoComplicacionCodigo() != null) {
            ent.setMaDiagnosticoComplicacionCodigo(obj.getMaDiagnosticoComplicacionCodigo());
            if (obj.getMaDiagnosticoComplicacionId() != null) {
                ent.setMaDiagnosticoComplicacionId(obj.getMaDiagnosticoComplicacionId());
            }
            if (obj.getMaDiagnosticoComplicacionValor() != null) {
                ent.setMaDiagnosticoComplicacionValor(obj.getMaDiagnosticoComplicacionValor());
            }
        }
        //MAE ESTADO SALIDA
        if (obj.getMaeEstadoSalidaCodigo() != null) {
            ent.setMaeEstadoSalidaCodigo(obj.getMaeEstadoSalidaCodigo());
            if (obj.getMaeEstadoSalidaId() != null) {
                ent.setMaeEstadoSalidaId(obj.getMaeEstadoSalidaId());
            }
            if (obj.getMaeEstadoSalidaValor() != null) {
                ent.setMaeEstadoSalidaValor(obj.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE
        if (obj.getMaeEstadoSalidaCodigo() != null) {
            ent.setMaeEstadoSalidaCodigo(obj.getMaeEstadoSalidaCodigo());
            if (obj.getMaeEstadoSalidaId() != null) {
                ent.setMaeEstadoSalidaId(obj.getMaeEstadoSalidaId());
            }
            if (obj.getMaeEstadoSalidaValor() != null) {
                ent.setMaeEstadoSalidaValor(obj.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE ID
        if (obj.getMaeEstadoSalidaCodigo() != null) {
            ent.setMaeEstadoSalidaCodigo(obj.getMaeEstadoSalidaCodigo());
            if (obj.getMaeEstadoSalidaId() != null) {
                ent.setMaeEstadoSalidaId(obj.getMaeEstadoSalidaId());
            }
            if (obj.getMaeEstadoSalidaValor() != null) {
                ent.setMaeEstadoSalidaValor(obj.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE VALOR
        if (obj.getMaeEstadoSalidaCodigo() != null) {
            ent.setMaeEstadoSalidaCodigo(obj.getMaeEstadoSalidaCodigo());
            if (obj.getMaeEstadoSalidaId() != null) {
                ent.setMaeEstadoSalidaId(obj.getMaeEstadoSalidaId());
            }
            if (obj.getMaeEstadoSalidaValor() != null) {
                ent.setMaeEstadoSalidaValor(obj.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE ID
        if (obj.getMaDiagnosticoMuerteCodigo() != null) {
            ent.setMaDiagnosticoMuerteCodigo(obj.getMaDiagnosticoMuerteCodigo());
            if (obj.getMaDiagnosticoMuerteId() != null) {
                ent.setMaDiagnosticoMuerteId(obj.getMaDiagnosticoMuerteId());
            }
            if (obj.getMaeEstadoSalidaValor() != null) {
                ent.setMaeEstadoSalidaValor(obj.getMaeEstadoSalidaValor());
            }
        }
        //MAE  VIA INGRESO
        if (obj.getMaeViaIngresoCodigo() != null) {
            ent.setMaeViaIngresoCodigo(obj.getMaeViaIngresoCodigo());
            if (obj.getMaeViaIngresoId() != null) {
                ent.setMaeViaIngresoId(obj.getMaeViaIngresoId());
            }
            if (obj.getMaeViaIngresoValor() != null) {
                ent.setMaeViaIngresoValor(obj.getMaeViaIngresoValor());
            }
        }
        //MAE  CAUSA EXTERNA
        if (obj.getMaeCausaExternaCodigo() != null) {
            ent.setMaeCausaExternaCodigo(obj.getMaeViaIngresoCodigo());
            if (obj.getMaeViaIngresoId() != null) {
                ent.setMaeViaIngresoId(obj.getMaeViaIngresoId());
            }
            if (obj.getMaeViaIngresoValor() != null) {
                ent.setMaeViaIngresoValor(obj.getMaeViaIngresoValor());
            }
        }
        //MA DIAGNOSTICO CODIGO
        ent.setFechaIngreso(obj.getFechaIngreso());
        ent.setHoraIngreso(obj.getHoraIngreso());
        ent.setAutorizacion(obj.getAutorizacion());
        if (obj.getMaDiagnosticoIngresoCodigo() != null) {
            ent.setMaDiagnosticoIngresoCodigo(obj.getMaDiagnosticoIngresoCodigo());
            if (obj.getMaDiagnosticoIngresoId() != null) {
                ent.setMaDiagnosticoIngresoId(obj.getMaDiagnosticoIngresoId());
            }
        }
        if (obj.getMaDiagnosticoIngresoValor() != null) {
            ent.setMaDiagnosticoIngresoValor(obj.getMaDiagnosticoIngresoValor());
        }
        //MA DIAGNOSTICO EGRESO CODIGO
        if (obj.getMaDiagnosticoEgresoCodigo() != null) {
            ent.setMaDiagnosticoEgresoCodigo(obj.getMaDiagnosticoEgresoCodigo());
            if (obj.getMaDiagnosticoEgresoId() != null) {
                ent.setMaDiagnosticoEgresoId(obj.getMaDiagnosticoEgresoId());
            }
            if (obj.getMaDiagnosticoEgresoValor() != null) {
                ent.setMaDiagnosticoEgresoValor(obj.getMaDiagnosticoEgresoValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1 CODIGO
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoEgresoCodigo());
            if (obj.getMaDiagnosticoRelacionado1Id() != null) {
                ent.setMaDiagnosticoRelacionado1Id(obj.getMaDiagnosticoRelacionado1Id());
            }
            if (obj.getMaDiagnosticoRelacionado1Valor() != null) {
                ent.setMaDiagnosticoRelacionado1Valor(obj.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2 CODIGO
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoEgresoCodigo());
            if (obj.getMaDiagnosticoRelacionado1Id() != null) {
                ent.setMaDiagnosticoRelacionado1Id(obj.getMaDiagnosticoRelacionado1Id());
            }
            if (obj.getMaDiagnosticoRelacionado1Valor() != null) {
                ent.setMaDiagnosticoRelacionado1Valor(obj.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3 CODIGO
        if (obj.getMaDiagnosticoMuerteCodigo() != null) {
            ent.setMaDiagnosticoMuerteCodigo(obj.getMaDiagnosticoEgresoCodigo());
            if (obj.getMaDiagnosticoMuerteId() != null) {
                ent.setMaDiagnosticoMuerteId(obj.getMaDiagnosticoMuerteId());
            }
            if (obj.getMaDiagnosticoMuerteValor() != null) {
                ent.setMaDiagnosticoMuerteValor(obj.getMaDiagnosticoMuerteValor());
            }
        }
        //MA DIAGNOSTICO COMPLICACION
        if (obj.getMaDiagnosticoComplicacionCodigo() != null) {
            ent.setMaDiagnosticoComplicacionCodigo(obj.getMaDiagnosticoComplicacionCodigo());
            if (obj.getMaDiagnosticoComplicacionId() != null) {
                ent.setMaDiagnosticoComplicacionId(obj.getMaDiagnosticoComplicacionId());
            }
            if (obj.getMaDiagnosticoComplicacionValor() != null) {
                ent.setMaDiagnosticoComplicacionValor(obj.getMaDiagnosticoComplicacionValor());
            }
        }
        //MAE ESTADO SALIDA CODIGO
        if (obj.getMaeEstadoSalidaCodigo() != null) {
            ent.setMaeEstadoSalidaCodigo(obj.getMaeEstadoSalidaCodigo());
            if (obj.getMaeEstadoSalidaId() != null) {
                ent.setMaeEstadoSalidaId(obj.getMaeEstadoSalidaId());
            }
            if (obj.getMaDiagnosticoComplicacionValor() != null) {
                ent.setMaDiagnosticoComplicacionValor(obj.getMaDiagnosticoComplicacionValor());
            }
        }
        //MA DIAGNOSTICO MUERTE CODIGO
        if (obj.getMaDiagnosticoMuerteCodigo() != null) {
            ent.setMaDiagnosticoMuerteCodigo(obj.getMaDiagnosticoMuerteCodigo());
            if (obj.getMaDiagnosticoMuerteId() != null) {
                ent.setMaDiagnosticoMuerteId(obj.getMaDiagnosticoMuerteId());
            }
            if (obj.getMaDiagnosticoComplicacionValor() != null) {
                ent.setMaDiagnosticoComplicacionValor(obj.getMaDiagnosticoComplicacionValor());
            }
        }
        ent.setFechaIngreso(obj.getFechaIngreso());
        ent.setHoraIngreso(obj.getHoraIngreso());
        ent.setAutorizacion(obj.getAutorizacion());
        ent.setFechaSalida(obj.getFechaSalida());
        ent.setHoraSalida(obj.getHoraSalida());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsAmMedicamentos castNegocioEntidadFinal(CmRipsAmMedicamento obj) {
        CmRipsAmMedicamentos ent = new CmRipsAmMedicamentos();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumeroFactura(obj.getNumeroFactura());
        //MAE TIPO DOCUMENTO
        ent.setCodigoReps(obj.getCodigoReps());
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setDocumentoAfiliado(obj.getDocumentoAfiliado());
        ent.setAutorizacion(obj.getAutorizacion());
        //MA MEDICAMENTO
        if (obj.getMaMedicamentoCodigo() != null) {
            ent.setMaMedicamentoCodigo(obj.getMaMedicamentoCodigo());
            if (obj.getMaMedicamentoId() != null) {
                ent.setMaMedicamentoId(obj.getMaMedicamentoId());
            }
            if (obj.getMaMedicamentoValor() != null) {
                ent.setMaMedicamentoValor(obj.getMaMedicamentoValor());
            }
        }
        //MAE TIPO MEDICAMENTO
        if (obj.getMaeTipoMedicamentoCodigo() != null) {
            ent.setMaeTipoMedicamentoCodigo(obj.getMaeTipoMedicamentoCodigo());
            if (obj.getMaeTipoMedicamentoId() != null) {
                ent.setMaeTipoMedicamentoId(obj.getMaeTipoMedicamentoId());
            }
            if (obj.getMaeTipoMedicamentoValor() != null) {
                ent.setMaeTipoMedicamentoValor(obj.getMaeTipoMedicamentoValor());
            }
        }
        ent.setNombreGenerico(obj.getNombreGenerico());
        if (obj.getFormaFarmaceutica() != null) {
            ent.setFormaFarmaceutica(obj.getFormaFarmaceutica());
        }
        if (obj.getConcentracion() != null) {
            ent.setConcentracion(obj.getConcentracion());
        }
        if (obj.getUnidadMedida() != null) {
            ent.setUnidadMedida(obj.getUnidadMedida());
        }
        ent.setNumeroUnidades(obj.getNumeroUnidad());
        ent.setValorUnitario(obj.getValorUnitario());
        ent.setValorAPagar(obj.getValorAPagar());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsAnRecienNacidos castNegocioEntidadFinal(CmRipsAnRecienNacido obj) {
        CmRipsAnRecienNacidos ent = new CmRipsAnRecienNacidos();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumeroFactura(obj.getNumeroFactura());
        ent.setCodigoReps(obj.getCodigoReps());
        //MAE TIPO DOCUMENTO
        ent.setCodigoReps(obj.getCodigoReps());
        if (obj.getMaeTipoDocumentoMadreCodigo() != null) {
            ent.setMaeTipoDocumentoMadreCodigo(obj.getMaeTipoDocumentoMadreCodigo());
            if (obj.getMaeTipoDocumentoMadreId() != null) {
                ent.setMaeTipoDocumentoMadreId(obj.getMaeTipoDocumentoMadreId());
            }
            if (obj.getMaeTipoDocumentoMadreValor() != null) {
                ent.setMaeTipoDocumentoMadreValor(obj.getMaeTipoDocumentoMadreValor());
            }
        }
        //MAE CONTROL PENATAL
        ent.setCodigoReps(obj.getCodigoReps());
        if (obj.getMaeControlPenatalCodigo() != null) {
            ent.setMaeControlPenatalCodigo(obj.getMaeTipoDocumentoMadreCodigo());
            if (obj.getMaeControlPenatalId() != null) {
                ent.setMaeControlPenatalId(obj.getMaeControlPenatalId());
            }
            if (obj.getMaeControlPenatalValor() != null) {
                ent.setMaeControlPenatalValor(obj.getMaeControlPenatalValor());
            }
        }
        //MAE CODIGO SEXO
        if (obj.getMaeSexoCodigo() != null) {
            ent.setMaeSexoCodigo(obj.getMaeSexoCodigo());
            if (obj.getMaeSexoId() != null) {
                ent.setMaeSexoId(obj.getMaeSexoId());
            }
            if (obj.getMaeSexoValor() != null) {
                ent.setMaeSexoValor(obj.getMaeSexoValor());
            }
        }
        //MA DIAGNOSTICO PRINCIPAL
        if (obj.getMaDiagnosticoPrincipalCodigo() != null) {
            ent.setMaDiagnosticoPrincipalCodigo(obj.getMaDiagnosticoPrincipalCodigo());
            if (obj.getMaDiagnosticoPrincipalId() != null) {
                ent.setMaDiagnosticoPrincipalId(obj.getMaDiagnosticoPrincipalId());
            }
            if (obj.getMaDiagnosticoPrincipalValor() != null) {
                ent.setMaDiagnosticoPrincipalValor(obj.getMaDiagnosticoPrincipalValor());
            }
        }
        ent.setDocumentoAfiliadoMadre(obj.getDocumentoAfiliadoMadre());
        ent.setFechaNacimiento(obj.getFechaNacimiento());
        ent.setHoraNacimiento(obj.getHoraNacimiento());
        if (obj.getEdadGestacion() != null) {
            ent.setEdadGestacion(obj.getEdadGestacion());
        }
        //MA CAUSA MUERTE
        if (obj.getMaCausaMuerteDiagnosticoCodigo() != null) {
            ent.setMaCausaMuerteDiagnosticoCodigo(obj.getMaCausaMuerteDiagnosticoCodigo());
            if (obj.getMaCausaMuerteDiagnosticoId() != null) {
                ent.setMaCausaMuerteDiagnosticoId(obj.getMaCausaMuerteDiagnosticoId());
            }
            if (obj.getMaCausaMuerteDiagnosticoValor() != null) {
                ent.setMaCausaMuerteDiagnosticoValor(obj.getMaDiagnosticoPrincipalValor());
            }
        }
        if (obj.getPeso() != null) {
            ent.setPeso(obj.getPeso());
        }
        if (obj.getFechaMuerte() != null) {
            ent.setFechaMuerte(obj.getFechaMuerte());
        }
        if (obj.getHoraMuerte() != null) {
            ent.setHoraMuerte(obj.getHoraMuerte());
        }
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsApProcedimientos castNegocioEntidadFinal(CmRipsApProcedimiento obj) {
        CmRipsApProcedimientos ent = new CmRipsApProcedimientos();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumeroFactura(obj.getNumeroFactura());
        ent.setCodigoReps(obj.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setDocumentoAfiliado(obj.getDocumentoAfiliado());
        ent.setFechaProcedimiento(obj.getFechaProcedimiento());
        ent.setAutorizacion(obj.getAutorizacion());
        //ent.setCodigoServicio(obj.getCodigoServicio());
        //MAE CODIGO AMBITO ATENCION
        ent.setCodigoReps(obj.getCodigoReps());
        if (obj.getMaeAmbitoAtencionCodigo() != null) {
            ent.setMaeAmbitoAtencionCodigo(obj.getMaeAmbitoAtencionCodigo());
            if (obj.getMaeAmbitoAtencionId() != null) {
                ent.setMaeAmbitoAtencionId(obj.getMaeAmbitoAtencionId());
            }
            if (obj.getMaeAmbitoAtencionValor() != null) {
                ent.setMaeAmbitoAtencionCodigoValor(obj.getMaeAmbitoAtencionValor());
            }
        }
        //MAE FINALIDAD PROCEDIMIENTOS
        ent.setCodigoReps(obj.getCodigoReps());
        if (obj.getMaeFinalidadProcedimientoCodigo() != null) {
            ent.setMaeFinalidadProcedimientoCodigo(obj.getMaeFinalidadProcedimientoCodigo());
            if (obj.getMaeFinalidadProcedimientoId() != null) {
                ent.setMaeFinalidadProcedimientoId(obj.getMaeFinalidadProcedimientoId());
            }
            if (obj.getMaeFinalidadProcedimientoValor() != null) {
                ent.setMaeFinalidadProcedimientoValor(obj.getMaeFinalidadProcedimientoValor());
            }
        }
        //MAE PERSONAL ATIENDE
        ent.setCodigoReps(obj.getCodigoReps());
        if (obj.getMaePersonalAtiendeCodigo() != null) {
            ent.setMaePersonalAtiendeCodigo(obj.getMaePersonalAtiendeCodigo());
            if (obj.getMaePersonalAtiendeId() != null) {
                ent.setMaePersonalAtiendeId(obj.getMaePersonalAtiendeId());
            }
            if (obj.getMaePersonalAtiendeValor() != null) {
                ent.setMaePersonalAtiendeValor(obj.getMaePersonalAtiendeValor());
            }
        }
        //MAE DIAGNOSTICO PRINCIPAL
        if (obj.getMaDiagnosticoPrincipalCodigo() != null) {
            ent.setMaDiagnosticoPrincipalCodigo(obj.getMaDiagnosticoPrincipalCodigo());
            if (obj.getMaDiagnosticoPrincipalId() != null) {
                ent.setMaDiagnosticoPrincipalId(obj.getMaDiagnosticoPrincipalId());
            }
            if (obj.getMaDiagnosticoPrincipalValor() != null) {
                ent.setMaDiagnosticoPrincipalValor(obj.getMaDiagnosticoPrincipalValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoRelacionado1Codigo());
            if (obj.getMaDiagnosticoRelacionado1Id() != null) {
                ent.setMaDiagnosticoRelacionado1Id(obj.getMaDiagnosticoRelacionado1Id());
            }
            if (obj.getMaDiagnosticoRelacionado1Valor() != null) {
                ent.setMaDiagnosticoRelacionado1Valor(obj.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (obj.getMaDiagnosticoRelacionado2Codigo() != null) {
            ent.setMaDiagnosticoRelacionado2Codigo(obj.getMaDiagnosticoRelacionado2Codigo());
            if (obj.getMaDiagnosticoRelacionado2Id() != null) {
                ent.setMaDiagnosticoRelacionado2Id(obj.getMaDiagnosticoRelacionado2Id());
            }
            if (obj.getMaDiagnosticoRelacionado2Valor() != null) {
                ent.setMaDiagnosticoRelacionado2Valor(obj.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MAE FORMA ACTO
        if (obj.getMaeFormaActoCodigo() != null) {
            ent.setMaeFormaActoCodigo(obj.getMaeFormaActoCodigo());
            if (obj.getMaeFormaActoId() != null) {
                ent.setMaeFormaActoId(obj.getMaeFormaActoId());
            }
            if (obj.getMaeFormaActoValor() != null) {
                ent.setMaeFormaActoValor(obj.getMaeFormaActoValor());
            }
        }
        ent.setValorAPagar(obj.getValorAPagar());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsAtOtrosServicios castNegocioEntidadFinal(CmRipsAtOtrosServicio obj) {
        CmRipsAtOtrosServicios ent = new CmRipsAtOtrosServicios();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumeroFactura(obj.getNumeroFactura());
        ent.setCodigoReps(obj.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setDocumentoAfiliado(obj.getDocumentoAfiliado());
        ent.setAutorizacion(obj.getAutorizacion());
        //MAE TIPO SERVICIO
        if (obj.getMaeTipoServicioCodigo() != null) {
            ent.setMaeTipoServicioCodigo(obj.getMaeTipoServicioCodigo());
            if (obj.getMaeTipoServicioId() != null) {
                ent.setMaeTipoServicioId(obj.getMaeTipoServicioId());
            }
            if (obj.getMaeTipoServicioValor() != null) {
                ent.setMaeTipoServicioValor(obj.getMaeTipoServicioValor());
            }
        }
        //MA TECNOLOGIA
        if (obj.getMaTecnologiaCodigo() != null) {
            ent.setMaTecnologiaCodigo(obj.getMaTecnologiaCodigo());
            if (obj.getMaTecnologiaId() != null) {
                ent.setMaTecnologiaId(obj.getMaTecnologiaId());
            }
            if (obj.getMaTecnologiaValor() != null) {
                ent.setMaTecnologiaValor(obj.getMaTecnologiaValor());
            }
        }
        ent.setCodigoServicio(obj.getMaTecnologiaCodigo());
        ent.setNombreServicio(obj.getNombreServicio());
        ent.setUnidades(obj.getUnidades());
        ent.setValorUnidades(obj.getValorUnidades());
        ent.setTotal(obj.getTotal());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsAuUrgencias castNegocioEntidadFinal(CmRipsAuUrgencia obj) {
        CmRipsAuUrgencias ent = new CmRipsAuUrgencias();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumeroFactura(obj.getNumeroFactura());
        ent.setCodigoReps(obj.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setDocumentoAfiliado(obj.getDocumentoAfiliado());
        ent.setFechaIngreso(obj.getFechaIngreso());
        ent.setHoraIngreso(obj.getHoraIngreso());
        ent.setAutorizacion(obj.getAutorizacion());
        //MAE CAUSA EXTERNA
        if (obj.getMaeCausaExternaCodigo() != null) {
            ent.setMaeCausaExternaCodigo(obj.getMaeCausaExternaCodigo());
            if (obj.getMaeCausaExternaId() != null) {
                ent.setMaeCausaExternaId(obj.getMaeCausaExternaId());
            }
            if (obj.getMaeCausaExternaValor() != null) {
                ent.setMaeCausaExternaValor(obj.getMaeCausaExternaValor());
            }
        }
        if (obj.getMaDiagnosticoSalidaCodigo() != null) {
            ent.setMaDiagnosticoSalidaCodigo(obj.getMaDiagnosticoSalidaCodigo());
            if (obj.getMaDiagnosticoSalidaId() != null) {
                ent.setMaDiagnosticoSalidaId(obj.getMaDiagnosticoSalidaId());
            }
            if (obj.getMaDiagnosticoSalidaValor() != null) {
                ent.setMaDiagnosticoSalidaValor(obj.getMaDiagnosticoSalidaValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoRelacionado1Codigo());
            if (obj.getMaDiagnosticoRelacionado1Id() != null) {
                ent.setMaDiagnosticoRelacionado1Id(obj.getMaDiagnosticoRelacionado1Id());
            }
            if (obj.getMaDiagnosticoRelacionado1Valor() != null) {
                ent.setMaDiagnosticoRelacionado1Valor(obj.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (obj.getMaDiagnosticoRelacionado2Codigo() != null) {
            ent.setMaDiagnosticoRelacionado2Codigo(obj.getMaDiagnosticoRelacionado2Codigo());
            if (obj.getMaDiagnosticoRelacionado2Id() != null) {
                ent.setMaDiagnosticoRelacionado2Id(obj.getMaDiagnosticoRelacionado2Id());
            }
            if (obj.getMaDiagnosticoRelacionado2Valor() != null) {
                ent.setMaDiagnosticoRelacionado2Valor(obj.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (obj.getMaDiagnosticoRelacionado3Codigo() != null) {
            ent.setMaDiagnosticoRelacionado3Codigo(obj.getMaDiagnosticoRelacionado3Codigo());
            if (obj.getMaDiagnosticoRelacionado3Id() != null) {
                ent.setMaDiagnosticoRelacionado3Id(obj.getMaDiagnosticoRelacionado3Id());
            }
            if (obj.getMaDiagnosticoRelacionado3Valor() != null) {
                ent.setMaDiagnosticoRelacionado3Valor(obj.getMaDiagnosticoRelacionado3Valor());
            }
        }
        //MA DIAGNOSTICO MUERTE
        if (obj.getMaDiagnosticoMuerteCodigo() != null) {
            ent.setMaDiagnosticoMuerteCodigo(obj.getMaDiagnosticoMuerteCodigo());
            if (obj.getMaDiagnosticoMuerteId() != null) {
                ent.setMaDiagnosticoMuerteId(obj.getMaDiagnosticoMuerteId());
            }
            if (obj.getMaDiagnosticoMuerteValor() != null) {
                ent.setMaDiagnosticoMuerteValor(obj.getMaDiagnosticoMuerteValor());
            }
        }
        //MAE DESTINO SALIDA
        if (obj.getMaeDestinoSalidaCodigo() != null) {
            ent.setMaeDestinoSalidaCodigo(obj.getMaeDestinoSalidaCodigo());
            if (obj.getMaeDestinoSalidaId() != null) {
                ent.setMaeDestinoSalidaId(obj.getMaeDestinoSalidaId());
            }
            if (obj.getMaeDestinoSalidaValor() != null) {
                ent.setMaeDestinoSalidaValor(obj.getMaeDestinoSalidaValor());
            }
        }
        //MAE ESTADO SALIDA
        if (obj.getMaeEstadoSalidaCodigo() != null) {
            ent.setMaeEstadoSalidaCodigo(obj.getMaeEstadoSalidaCodigo());
            if (obj.getMaeEstadoSalidaId() != null) {
                ent.setMaeEstadoSalidaId(obj.getMaeEstadoSalidaId());
            }
            if (obj.getMaeEstadoSalidaValor() != null) {
                ent.setMaeEstadoSalidaValor(obj.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (obj.getMaDiagnosticoMuerteCodigo() != null) {
            ent.setMaDiagnosticoMuerteCodigo(obj.getMaDiagnosticoMuerteCodigo());
            if (obj.getMaDiagnosticoMuerteId() != null) {
                ent.setMaDiagnosticoMuerteId(obj.getMaDiagnosticoMuerteId());
            }
            if (obj.getMaDiagnosticoMuerteValor() != null) {
                ent.setMaDiagnosticoMuerteValor(obj.getMaDiagnosticoMuerteValor());
            }
        }
        ent.setFechaSalida(obj.getFechaSalida());
        ent.setHoraSalida(obj.getHoraSalida());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsUsUsuarios castNegocioEntidadFinal(CmRipsUsUsuario obj) {
        CmRipsUsUsuarios ent = new CmRipsUsUsuarios();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        //MAE TIPO DOCUMENTO
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setDocumentoAfiliado(obj.getDocumentoAfiliado());
        if (obj.getAdministradora() != null) {
            ent.setAdministradora(obj.getAdministradora());
        }
        //MAE TIPO USUARIO
        if (obj.getMaeTipoUsuarioCodigo() != null) {
            ent.setMaeTipoUsuarioCodigo(obj.getMaeTipoUsuarioCodigo());
            if (obj.getMaeTipoUsuarioId() != null) {
                ent.setMaeTipoUsuarioId(obj.getMaeTipoUsuarioId());
            }
            if (obj.getMaeTipoUsuarioValor() != null) {
                ent.setMaeTipoUsuarioValor(obj.getMaeTipoUsuarioValor());
            }
        }
        ent.setPrimerApellido(obj.getPrimerApellido());
        ent.setSegundoApellido(obj.getSegundoApellido());
        ent.setPrimerNombre(obj.getPrimerNombre());
        ent.setSegundoNombre(obj.getSegundoNombre());
        if (obj.getEdad() != null) {
            ent.setEdad(obj.getEdad());
        }
        if (obj.getCodigoUnidadMedidaEdad() != null) {
            ent.setCodigoUnidadMedidaEdad(obj.getCodigoUnidadMedidaEdad());
        }
        //MAE SEXO
        if (obj.getMaeSexoCodigo() != null) {
            ent.setMaeSexoCodigo(obj.getMaeSexoCodigo());
            if (obj.getMaeSexoId() != null) {
                ent.setMaeSexoId(obj.getMaeTipoUsuarioId());
            }
            if (obj.getMaeSexoValor() != null) {
                ent.setMaeSexoValor(obj.getMaeSexoValor());
            }
        }
        //MAE DEPARTAMENTO
        if (obj.getDepartamentoCodigo() != null) {
            ent.setDepartamentoCodigo(obj.getDepartamentoCodigo());
            if (obj.getDepartamentoId() != null) {
                ent.setDepartamentoId(obj.getDepartamentoId());
            }
            if (obj.getDepartamentoNombre() != null) {
                ent.setDepartamentoNombre(obj.getDepartamentoNombre());
            }
        }
        //MAE CIUDAD
        if (obj.getCiudadCodigo() != null) {
            ent.setCiudadCodigo(obj.getCiudadCodigo());
            if (obj.getCiudadId() != null) {
                ent.setCiudadId(obj.getCiudadId());
            }
            if (obj.getCiudadNombre() != null) {
                ent.setCiudadNombre(obj.getCiudadNombre());
            }
        }
        //MAE ZONA RESIDENCIA
        if (obj.getMaeZonaResidenciaCodigo() != null) {
            ent.setMaeZonaResidenciaCodigo(obj.getMaeZonaResidenciaCodigo());
            if (obj.getMaeZonaResidenciaId() != null) {
                ent.setMaeZonaResidenciaId(obj.getMaeZonaResidenciaId());
            }
            if (obj.getMaeZonaResidenciaValor() != null) {
                ent.setMaeZonaResidenciaValor(obj.getMaeZonaResidenciaValor());
            }
        }
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    /*
    ENTIDAD NEGOCIO FINALES
     */
    public static CmRipsCarga castEntidadNegocioDetalles(CmRipsCargas per) {
        CmRipsCarga neg = new CmRipsCarga();
        List<CmRipsEstructuraError> listaCmRipsEstructuraErrores = new ArrayList<>();
        List<CmRipsAcConsulta> listaCmRipsAcConsultas = new ArrayList<>();
        List<CmRipsAdServiciosAgrupado> listaCmRipsAdServiciosAgrupados = new ArrayList<>();
        List<CmRipsAfFactura> listaCmRipsAfFacturas = new ArrayList<>();
        List<CmRipsAhHospitalizacion> listaCmRipsAhHospitalizaciones = new ArrayList<>();
        List<CmRipsAmMedicamento> listaCmRipsAmMedicamentos = new ArrayList<>();
        List<CmRipsAnRecienNacido> listaCmRipsAnRecienNacidos = new ArrayList<>();
        List<CmRipsApProcedimiento> listaCmRipsApProcedimientos = new ArrayList<>();
        List<CmRipsAtOtrosServicio> listaCmRipsAtOtrosServicios = new ArrayList<>();
        List<CmRipsAuUrgencia> listaCmRipsAuUrgencias = new ArrayList<>();
        List<CmRipsCtControlObj> listaCmRipsCtControl = new ArrayList<>();
        List<CmRipsUsUsuario> listaCmRipsUsUsuarios = new ArrayList<>();
        neg.setId(per.getId());
        if (per.getGnPrestadorSedesId() != null) {
            neg.setGnPrestadorSede(new CntPrestadorSede(per.getGnPrestadorSedesId().getId()));
            if (per.getGnPrestadorSedesId().getNombre() != null) {
                neg.getGnPrestadorSede().setNombreSede(per.getGnPrestadorSedesId().getNombre());
                if (per.getGnPrestadorSedesId().getCntPrestadoresId() != null) {
                    CntPrestador cntPrestador = new CntPrestador();
                    cntPrestador.setId(per.getGnPrestadorSedesId().getCntPrestadoresId().getId());
                    cntPrestador.setRazonSocial(per.getGnPrestadorSedesId().getCntPrestadoresId().getRazonSocial());
                    cntPrestador.setNumeroDocumento(per.getGnPrestadorSedesId().getCntPrestadoresId().getNumeroDocumento());
                    cntPrestador.setMaeTipoDocumentoCodigo(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoCodigo());
                    cntPrestador.setMaeTipoDocumentoId(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoId());
                    cntPrestador.setMaeTipoDocumentoValor(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoValor());
                    cntPrestador.setMaeTipoDocumentoRepCodigo(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoRepCodigo());
                    cntPrestador.setMaeTipoDocumentoRepId(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoRepId());
                    cntPrestador.setMaeTipoDocumentoRepValor(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoRepValor());
                    neg.getGnPrestadorSede().setCntPrestador(cntPrestador);
                }
            }
        }
        if (per.getCntContratosId() != null) {
            neg.setCntContrato(new CntContrato(per.getCntContratosId().getId()));
        }
        if (per.getCmRipsAcConsultasList() != null) {
            for (CmRipsAcConsultas ent : per.getCmRipsAcConsultasList()) {
                CmRipsAcConsulta cmRipsAcConsulta = castEntidadNegocio(ent);
                listaCmRipsAcConsultas.add(cmRipsAcConsulta);
            }
        }

        if (per.getCmRipsAfFacturasList() != null) {
            for (CmRipsAfFacturas ent : per.getCmRipsAfFacturasList()) {
                CmRipsAfFactura cmRipsAfFactura = castEntidadNegocio(ent);
                listaCmRipsAfFacturas.add(cmRipsAfFactura);
            }
        }
        if (per.getCmRipsAmMedicamentosList() != null) {
            for (CmRipsAmMedicamentos ent : per.getCmRipsAmMedicamentosList()) {
                CmRipsAmMedicamento cmRipsAmMedicamento = castEntidadNegocio(ent);
                listaCmRipsAmMedicamentos.add(cmRipsAmMedicamento);
            }
        }
        if (per.getCmRipsApProcedimientosList() != null) {
            for (CmRipsApProcedimientos ent : per.getCmRipsApProcedimientosList()) {
                CmRipsApProcedimiento cmRipsApProcedimiento = castEntidadNegocio(ent);
                listaCmRipsApProcedimientos.add(cmRipsApProcedimiento);
            }
        }
        if (per.getCmRipsAtOtrosServiciosList() != null) {
            for (CmRipsAtOtrosServicios ent : per.getCmRipsAtOtrosServiciosList()) {
                CmRipsAtOtrosServicio cmRipsAtOtrosServicio = castEntidadNegocio(ent);
                listaCmRipsAtOtrosServicios.add(cmRipsAtOtrosServicio);
            }
        }
        if (per.getCmRipsUsUsuariosList() != null) {
            for (CmRipsUsUsuarios ent : per.getCmRipsUsUsuariosList()) {
                CmRipsUsUsuario cmRipsUsUsuario = castEntidadNegocio(ent);
                listaCmRipsUsUsuarios.add(cmRipsUsUsuario);
            }
        }
        if (per.getCmRipsCtControlList() != null) {
            for (CmRipsCtControl ent : per.getCmRipsCtControlList()) {
                CmRipsCtControlObj cmRipsCtControl = castEntidadNegocio(ent);
                listaCmRipsCtControl.add(cmRipsCtControl);
            }
        }
        neg.setListaCmRipsAcConsultas(listaCmRipsAcConsultas);
        neg.setListaCmRipsAdServiciosAgrupados(listaCmRipsAdServiciosAgrupados);
        neg.setListaCmRipsAfFacturas(listaCmRipsAfFacturas);
        neg.setListaCmRipsAhHospitalizaciones(listaCmRipsAhHospitalizaciones);
        neg.setListaCmRipsAmMedicamentos(listaCmRipsAmMedicamentos);
        neg.setListaCmRipsAnRecienNacidos(listaCmRipsAnRecienNacidos);
        neg.setListaCmRipsAtOtrosServicios(listaCmRipsAtOtrosServicios);
        neg.setListaCmRipsUsUsuarios(listaCmRipsUsUsuarios);
        neg.setListaCmRipsAuUrgencias(listaCmRipsAuUrgencias);
        neg.setListaCmRipsApProcedimientos(listaCmRipsApProcedimientos);
        neg.setListaCmRipsUsUsuarios(listaCmRipsUsUsuarios);
        neg.setListaCmRipsCtControl(listaCmRipsCtControl);
        neg.setMaeContratoModalidadCodigo(per.getMaeContratoModalidadCodigo());
        neg.setMaeContratoModalidadId(per.getMaeContratoModalidadId());
        neg.setMaeContratoModalidadValor(per.getMaeContratoModalidadValor());
        neg.setMaeRegimenCodigo(per.getMaeRegimenCodigo());
        neg.setMaeRegimenId(per.getMaeRegimenId());
        neg.setMaeRegimenValor(per.getMaeRegimenValor());
        neg.setMaeContratoModalidadValor(per.getMaeContratoModalidadValor());
        neg.setMaeContratoModalidadValor(per.getMaeContratoModalidadValor());
        if (per.getNumeroCuenta() != null) {
            neg.setNumeroCuenta(per.getNumeroCuenta());
        }
        if (per.getFechaPrestacion() != null) {
            neg.setFechaPrestacion(per.getFechaPrestacion());
        }
        if (per.getPbs() != null) {
            neg.setPbs(per.getPbs());
        } else {
            neg.setPbs(false);
        }
        neg.setCamaFija(per.getCamaFija());
        neg.setCantidadFactura(Integer.parseInt(per.getCantidadFactura()));
        neg.setValorCarga(per.getValorCarga());
        neg.setEstado(per.getEstado());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsCarga castEntidadNegocio(CmRipsCargas per) {
        CmRipsCarga neg = new CmRipsCarga();
        List<CmRipsEstructuraError> listaCmRipsEstructuraErrores = new ArrayList<>();
        List<CmRipsAcConsulta> listaCmRipsAcConsultas = new ArrayList<>();
        List<CmRipsAdServiciosAgrupado> listaCmRipsAdServiciosAgrupados = new ArrayList<>();
        List<CmRipsAfFactura> listaCmRipsAfFacturas = new ArrayList<>();
        List<CmRipsAhHospitalizacion> listaCmRipsAhHospitalizaciones = new ArrayList<>();
        List<CmRipsAmMedicamento> listaCmRipsAmMedicamentos = new ArrayList<>();
        List<CmRipsAnRecienNacido> listaCmRipsAnRecienNacidos = new ArrayList<>();
        List<CmRipsApProcedimiento> listaCmRipsApProcedimientos = new ArrayList<>();
        List<CmRipsAtOtrosServicio> listaCmRipsAtOtrosServicios = new ArrayList<>();
        List<CmRipsAuUrgencia> listaCmRipsAuUrgencias = new ArrayList<>();
        List<CmRipsCtControlObj> listaCmRipsCtControl = new ArrayList<>();
        List<CmRipsUsUsuario> listaCmRipsUsUsuarios = new ArrayList<>();
        neg.setId(per.getId());
        if (per.getCantidadFactura() != null) {
            neg.setCantidadFactura(Integer.parseInt(per.getCantidadFactura()));
        }
        if (per.getGnPrestadorSedesId() != null) {
            neg.setGnPrestadorSede(new CntPrestadorSede(per.getGnPrestadorSedesId().getId()));
            if (per.getGnPrestadorSedesId().getNombre() != null) {
                neg.getGnPrestadorSede().setNombreSede(per.getGnPrestadorSedesId().getNombre());
                if (per.getGnPrestadorSedesId().getCntPrestadoresId() != null) {
                    CntPrestador cntPrestador = new CntPrestador();
                    cntPrestador.setId(per.getGnPrestadorSedesId().getCntPrestadoresId().getId());
                    cntPrestador.setRazonSocial(per.getGnPrestadorSedesId().getCntPrestadoresId().getRazonSocial());
                    cntPrestador.setNumeroDocumento(per.getGnPrestadorSedesId().getCntPrestadoresId().getNumeroDocumento());
                    cntPrestador.setMaeTipoDocumentoCodigo(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoCodigo());
                    cntPrestador.setMaeTipoDocumentoId(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoId());
                    cntPrestador.setMaeTipoDocumentoValor(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoValor());
                    cntPrestador.setMaeTipoDocumentoRepCodigo(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoRepCodigo());
                    cntPrestador.setMaeTipoDocumentoRepId(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoRepId());
                    cntPrestador.setMaeTipoDocumentoRepValor(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoRepValor());
                    neg.getGnPrestadorSede().setCntPrestador(cntPrestador);
                }
            }
        }
        if (per.getCntContratosId() != null) {
            neg.setCntContrato(new CntContrato(per.getCntContratosId().getId()));
            neg.setCntTipoContratoId(per.getCntTipoContratoId());
        }
        if (per.getCmRipsAfFacturasList() != null) {

            for (CmRipsAfFacturas ent : per.getCmRipsAfFacturasList()) {
                CmRipsAfFactura cmRipsAfFactura = castEntidadNegocio(ent);
                listaCmRipsAfFacturas.add(cmRipsAfFactura);
            }
        }
        neg.setListaCmRipsAcConsultas(listaCmRipsAcConsultas);
        neg.setListaCmRipsAdServiciosAgrupados(listaCmRipsAdServiciosAgrupados);
        neg.setListaCmRipsAfFacturas(listaCmRipsAfFacturas);
        neg.setListaCmRipsAhHospitalizaciones(listaCmRipsAhHospitalizaciones);
        neg.setListaCmRipsAmMedicamentos(listaCmRipsAmMedicamentos);
        neg.setListaCmRipsAnRecienNacidos(listaCmRipsAnRecienNacidos);
        neg.setListaCmRipsAtOtrosServicios(listaCmRipsAtOtrosServicios);
        neg.setListaCmRipsAuUrgencias(listaCmRipsAuUrgencias);
        neg.setListaCmRipsApProcedimientos(listaCmRipsApProcedimientos);
        neg.setListaCmRipsUsUsuarios(listaCmRipsUsUsuarios);
        neg.setListaCmRipsCtControl(listaCmRipsCtControl);
        neg.setListaCmRipsCargaAnRecienNacidos(listaCmRipsAnRecienNacidos);
        neg.setListaCmRipsCargaAtOtrosServicios(listaCmRipsAtOtrosServicios);
        neg.setListaCmRipsApCargaProcedimientos(listaCmRipsApProcedimientos);
        neg.setListaCmRipsCargaAuUrgencias(listaCmRipsAuUrgencias);
        neg.setListaCmRipsCargaCtControles(listaCmRipsCtControl);
        neg.setListacmRipsCargaUsUsuarios(listaCmRipsUsUsuarios);
        neg.setListaCmRipsEstructuraErrores(listaCmRipsEstructuraErrores);
        if (per.getNumeroCuenta() != null) {
            neg.setNumeroCuenta(per.getNumeroCuenta());
        }
        if (per.getFechaPrestacion() != null) {
            neg.setFechaPrestacion(per.getFechaPrestacion());
        }
        neg.setContrato(per.getContrato());
        neg.setMaeRegionalValor(per.getMaeRegionalValor());
        neg.setMaeContratoModalidadValor(per.getMaeContratoModalidadValor());
        neg.setMaeRegimenValor(per.getMaeRegimenValor());
        neg.setValorCarga(per.getValorCarga());
        neg.setCamaFija(per.getCamaFija());
        neg.setPbs(per.getPbs());
        neg.setEstado(per.getEstado());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsCarga castEntidadNegocioErrores(CmRipsCargas per) {
        CmRipsCarga neg = new CmRipsCarga();
        List<CmRipsEstructuraError> listaCmRipsEstructuraErrores = new ArrayList<>();
        List<CmRipsAcConsulta> listaCmRipsAcConsultas = new ArrayList<>();
        List<CmRipsAdServiciosAgrupado> listaCmRipsAdServiciosAgrupados = new ArrayList<>();
        List<CmRipsAfFactura> listaCmRipsAfFacturas = new ArrayList<>();
        List<CmRipsAhHospitalizacion> listaCmRipsAhHospitalizaciones = new ArrayList<>();
        List<CmRipsAmMedicamento> listaCmRipsAmMedicamentos = new ArrayList<>();
        List<CmRipsAnRecienNacido> listaCmRipsAnRecienNacidos = new ArrayList<>();
        List<CmRipsApProcedimiento> listaCmRipsApProcedimientos = new ArrayList<>();
        List<CmRipsAtOtrosServicio> listaCmRipsAtOtrosServicios = new ArrayList<>();
        List<CmRipsAuUrgencia> listaCmRipsAuUrgencias = new ArrayList<>();
        List<CmRipsCtControlObj> listaCmRipsCtControl = new ArrayList<>();
        List<CmRipsUsUsuario> listaCmRipsUsUsuarios = new ArrayList<>();
        neg.setId(per.getId());
        if (per.getCmRipsEstructuraErroresList() != null) {
            for (CmRipsEstructuraErrores cmRipsEstructuraErrores : per.getCmRipsEstructuraErroresList()) {
                listaCmRipsEstructuraErrores.add(new CmRipsEstructuraError(
                        cmRipsEstructuraErrores.getArchivoNombre(),
                        cmRipsEstructuraErrores.getArchivoFila(),
                        cmRipsEstructuraErrores.getDescripcionError()
                ));
            }
        }
        neg.setListaCmRipsAcConsultas(listaCmRipsAcConsultas);
        neg.setListaCmRipsAdServiciosAgrupados(listaCmRipsAdServiciosAgrupados);
        neg.setListaCmRipsAfFacturas(listaCmRipsAfFacturas);
        neg.setListaCmRipsAhHospitalizaciones(listaCmRipsAhHospitalizaciones);
        neg.setListaCmRipsAmMedicamentos(listaCmRipsAmMedicamentos);
        neg.setListaCmRipsAnRecienNacidos(listaCmRipsAnRecienNacidos);
        neg.setListaCmRipsAtOtrosServicios(listaCmRipsAtOtrosServicios);
        neg.setListaCmRipsAuUrgencias(listaCmRipsAuUrgencias);
        neg.setListaCmRipsApProcedimientos(listaCmRipsApProcedimientos);
        neg.setListaCmRipsUsUsuarios(listaCmRipsUsUsuarios);
        neg.setListaCmRipsCtControl(listaCmRipsCtControl);
        neg.setListaCmRipsCargaAnRecienNacidos(listaCmRipsAnRecienNacidos);
        neg.setListaCmRipsCargaAtOtrosServicios(listaCmRipsAtOtrosServicios);
        neg.setListaCmRipsApCargaProcedimientos(listaCmRipsApProcedimientos);
        neg.setListaCmRipsCargaAuUrgencias(listaCmRipsAuUrgencias);
        neg.setListaCmRipsCargaCtControles(listaCmRipsCtControl);
        neg.setListacmRipsCargaUsUsuarios(listaCmRipsUsUsuarios);
        neg.setListaCmRipsEstructuraErrores(listaCmRipsEstructuraErrores);
        if (per.getNumeroCuenta() != null) {
            neg.setNumeroCuenta(per.getNumeroCuenta());
        }
        if (per.getFechaPrestacion() != null) {
            neg.setFechaPrestacion(per.getFechaPrestacion());
        }
        neg.setCamaFija(per.getCamaFija());
        neg.setPbs(per.getPbs());
        neg.setValorCarga(per.getValorCarga());
        neg.setEstado(per.getEstado());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsCarga castEntidadNegocioCorto(CmRipsCargas per) {
        CmRipsCarga neg = new CmRipsCarga();
        neg.setId(per.getId());
        if (per.getGnPrestadorSedesId() != null) {
            neg.setGnPrestadorSede(new CntPrestadorSede(per.getGnPrestadorSedesId().getId()));
            if (per.getGnPrestadorSedesId().getNombre() != null) {
                neg.getGnPrestadorSede().setNombreSede(per.getGnPrestadorSedesId().getNombre());
                if (per.getGnPrestadorSedesId().getCntPrestadoresId() != null) {
                    CntPrestador cntPrestador = new CntPrestador();
                    cntPrestador.setId(per.getGnPrestadorSedesId().getCntPrestadoresId().getId());
                    cntPrestador.setRazonSocial(per.getGnPrestadorSedesId().getCntPrestadoresId().getRazonSocial());
                    cntPrestador.setNumeroDocumento(per.getGnPrestadorSedesId().getCntPrestadoresId().getNumeroDocumento());
                    cntPrestador.setMaeTipoDocumentoCodigo(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoCodigo());
                    cntPrestador.setMaeTipoDocumentoId(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoId());
                    cntPrestador.setMaeTipoDocumentoValor(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoValor());
                    cntPrestador.setMaeTipoDocumentoRepCodigo(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoRepCodigo());
                    cntPrestador.setMaeTipoDocumentoRepId(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoRepId());
                    cntPrestador.setMaeTipoDocumentoRepValor(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoRepValor());
                    neg.getGnPrestadorSede().setCntPrestador(cntPrestador);
                }
            }
        }
        if (per.getCntContratosId() != null ) {
            neg.setCntContrato(new CntContrato(per.getCntContratosId().getId()));
            neg.setCntTipoContratoId(per.getCntTipoContratoId());
        }
        if (per.getNumeroCuenta() != null) {
            neg.setNumeroCuenta(per.getNumeroCuenta());
        }
        if (per.getFechaPrestacion() != null) {
            neg.setFechaPrestacion(per.getFechaPrestacion());
        }
        if (per.getFechaHoraInicio() != null) {
            neg.setFechaHoraInicio(per.getFechaHoraInicio());
        }
        if (per.getFechaHoraFin() != null) {
            neg.setFechaHoraFin(per.getFechaHoraFin());
        }
        if (per.getTiempo() != null) {
            neg.setTiempo(per.getTiempo());
        }
        neg.setUsuarioAudita(per.getUsuarioAudita());
        neg.setValorCarga(per.getValorCarga());
        neg.setEstado(per.getEstado());
        if (per.getCantidadFactura() != null) {
            neg.setCantidadFactura(Integer.parseInt(per.getCantidadFactura()));
        }
        neg.setContrato(per.getContrato());
        neg.setPbs(per.getPbs());
        neg.setCamaFija(per.getCamaFija());
        neg.setMaeContratoModalidadValor(per.getMaeContratoModalidadValor());
        neg.setMaeRegimenValor(per.getMaeRegimenValor());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsCtControlObj castEntidadNegocio(CmRipsCtControl per) {
        CmRipsCtControlObj neg = new CmRipsCtControlObj();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCodigoReps(per.getCodigoReps());
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setArchivoControl(per.getArchivoControl());
        neg.setArchivoAf(per.getArchivoAf());
        neg.setArchivoUs(per.getArchivoUs());
        neg.setArchivoAd(per.getArchivoAd());
        neg.setArchivoAc(per.getArchivoAc());
        neg.setArchivoAp(per.getArchivoAp());
        neg.setArchivoAh(per.getArchivoAh());
        neg.setArchivoAu(per.getArchivoAu());
        neg.setArchivoAn(per.getArchivoAn());
        neg.setArchivoAm(per.getArchivoAm());
        neg.setArchivoAt(per.getArchivoAt());
        neg.setRegistrosAf(per.getRegistrosAf());
        neg.setRegistroAc(per.getRegistroAc());
        neg.setRegistrosUs(per.getRegistrosUs());
        neg.setRegistrosAp(per.getRegistrosAp());
        neg.setRegistrosAh(per.getRegistrosAh());
        neg.setRegistrosAu(per.getRegistrosAu());
        neg.setRegistrosAm(per.getRegistrosAm());
        neg.setRegistrosAt(per.getRegistrosAt());
        neg.setRegistrosAn(per.getRegistrosAn());
        neg.setRegistrosAd(per.getRegistrosAd());
        neg.setFechaAf(per.getFechaAf());
        neg.setFechaUs(per.getFechaUs());
        neg.setFechaAc(per.getFechaAc());
        neg.setFechaAp(per.getFechaAp());
        neg.setFechaAh(per.getFechaAh());
        neg.setFechaAu(per.getFechaAu());
        neg.setFechaAm(per.getFechaAm());
        neg.setFechaAt(per.getFechaAt());
        neg.setFechaAn(per.getFechaAn());
        neg.setFechaAd(per.getFechaAd());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsCargaAnexo castEntidadNegocio(CmRipsCargaAnexos per) {
        CmRipsCargaAnexo neg = new CmRipsCargaAnexo();
        neg.setId(per.getId());
        if (per.getCmRipsCargasId() != null) {
            neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        }
        //neg.setMaeTipoArchivoId(per.getMaeTipoArchivoId());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setExiste(per.getExiste());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsEstructuraError castEntidadNegocio(CmRipsEstructuraErrores per) {
        CmRipsEstructuraError neg = new CmRipsEstructuraError();
        neg.setId(per.getId());
        if (per.getCmRipsCargasId() != null) {
            neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        }
        neg.setArchivoFila(per.getArchivoFila());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setDescripcionError(per.getDescripcionError());
        return neg;
    }

    public static CmRipsSuceso castEntidadNegocio(CmRipsSucesos per) {
        CmRipsSuceso neg = new CmRipsSuceso();
        neg.setId(per.getId());
        neg.setCmRipsReglasId(per.getCmRipsReglasId());
        neg.setArchivoFila(per.getArchivoFila());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setAlerta(per.getAlerta());
        neg.setTipoRegla(per.getTipoRegla());
        neg.setNombreRegla(per.getNombreRegla());
        neg.setDescripcionMensaje(per.getDescripcionMensaje());
        return neg;
    }

    public static CmRipsAcConsulta castEntidadNegocio(CmRipsAcConsultas per) {
        CmRipsAcConsulta neg = new CmRipsAcConsulta();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumFactura(per.getNumFactura());
        neg.setCodigoReps(per.getCodigoReps());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setFila(per.getFila());
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        if (per.getFechaConsulta() != null) {
            neg.setFechaConsulta(per.getFechaConsulta());
        }
        neg.setAutorizacion(neg.getAutorizacion());
        if (per.getMaTecnologiaCodigo() != null) {
            neg.setMaTecnologiaCodigo(per.getMaTecnologiaCodigo());
        }
        if (per.getMaTecnologiaId() != null) {
            neg.setMaTecnologiaId(per.getMaTecnologiaId());
        }
        if (per.getMaTecnologiaValor() != null) {
            neg.setMaTecnologiaValor(per.getMaTecnologiaValor());
        }
        //MAE FINALIDAD CONSULTA
        if (per.getMaeFinalidadConsultaCodigo() != null) {
            neg.setMaeFinalidadConsultaCodigo(per.getMaeFinalidadConsultaCodigo());
            if (per.getMaeFinalidadConsultaId() != null) {
                neg.setMaeFinalidadConsultaId(per.getMaeFinalidadConsultaId());
            }
            if (per.getMaeFinalidadConsultaValor() != null) {
                neg.setMaeFinalidadConsultaValor(per.getMaeFinalidadConsultaValor());
            }
        }
        //MAE CAUSA EXTERNA
        if (per.getMaeCausaExternaCodigo() != null) {
            neg.setMaeCausaExternaCodigo(per.getMaeCausaExternaCodigo());
            if (per.getMaeCausaExternaId() != null) {
                neg.setMaeCausaExternaId(per.getMaeCausaExternaId());
            }
            if (per.getMaeCausaExternaValor() != null) {
                neg.setMaeCausaExternaValor(per.getMaeCausaExternaValor());
            }
        }
        //MA DIAGNOSTICO
        if (per.getMaDiagnosticoPrincipalCodigo() != null) {
            neg.setMaDiagnosticoPrincipalCodigo(per.getMaDiagnosticoPrincipalCodigo());
            if (per.getMaDiagnosticoPrincipalId() != null) {
                neg.setMaDiagnosticoPrincipalId(per.getMaDiagnosticoPrincipalId());
            }
            if (per.getMaDiagnosticoPrincipalValor() != null) {
                neg.setMaDiagnosticoPrincipalValor(per.getMaDiagnosticoPrincipalValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoRelacionado1Codigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (per.getMaDiagnosticoRelacionado2Codigo() != null) {
            neg.setMaDiagnosticoRelacionado2Codigo(per.getMaDiagnosticoRelacionado2Codigo());
            if (per.getMaDiagnosticoRelacionado2Id() != null) {
                neg.setMaDiagnosticoRelacionado2Id(neg.getMaDiagnosticoRelacionado2Id());
            }
            if (per.getMaDiagnosticoRelacionado2Valor() != null) {
                neg.setMaDiagnosticoRelacionado2Valor(neg.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (per.getMaDiagnosticoRelacionado3Codigo() != null) {
            neg.setMaDiagnosticoRelacionado3Codigo(per.getMaDiagnosticoRelacionado3Codigo());
            if (per.getMaDiagnosticoRelacionado3Id() != null) {
                neg.setMaDiagnosticoRelacionado3Id(neg.getMaDiagnosticoRelacionado3Id());
            }
            if (per.getMaDiagnosticoRelacionado3Valor() != null) {
                neg.setMaDiagnosticoRelacionado3Valor(per.getMaDiagnosticoRelacionado3Valor());
            }
        }
        //MA TIPO DIAGNOSTICO 
        if (per.getMaeTipoDiagnosticoCodigo() != null) {
            neg.setMaeTipoDiagnosticoCodigo(per.getMaeTipoDiagnosticoCodigo());
            if (neg.getMaeTipoDiagnosticoId() != null) {
                neg.setMaeTipoDiagnosticoId(per.getMaeTipoDiagnosticoId());
            }
            if (per.getMaeTipoDiagnosticoValor() != null) {
                neg.setMaeTipoDiagnosticoValor(per.getMaeTipoDiagnosticoValor());
            }
        }
        neg.setValorConsulta(per.getValorConsulta());
        neg.setValorCuotaModeradora(per.getValorCuotaModeradora());
        neg.setValorAPagar(per.getValorAPagar());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAdServiciosAgrupado castEntidadNegocio(CmRipsAdServiciosAgrupados per) {
        CmRipsAdServiciosAgrupado neg = new CmRipsAdServiciosAgrupado();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }

        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setFila(per.getFila());
        neg.setCodigoReps(per.getCodigoReps());
        neg.setCantidadServicio(per.getCantidadServicios());
        neg.setValorUnitario(per.getValorUnitario());
        neg.setValorConcepto(per.getValorConcepto());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(neg.getArchivoNombreOriginal());
        neg.setUsuarioCrea(neg.getUsuarioCrea());
        neg.setTerminalCrea(neg.getTerminalCrea());
        neg.setFechaHoraCrea(neg.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAfFactura castEntidadNegocio(CmRipsAfFacturas per) {
        CmRipsAfFactura neg = new CmRipsAfFactura();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setCodigoReps(per.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setFila(per.getFila());
        neg.setRazonSocial(per.getRazonSocial());
        neg.setNit(per.getNit().trim());
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setFechaFactura(per.getFechaFactura());
        if (per.getFechaInicio() != null) {
            neg.setFechaInicio(per.getFechaInicio());
        }
        if (per.getFechaFinal() != null) {
            neg.setFechaFinal(per.getFechaFinal());
        }
        neg.setCodigoEps(per.getCodigoEps());
        if (per.getNombreAdministradora() != null) {
            neg.setNombreAdministradora(per.getNombreAdministradora());
        }
        if (per.getContrato() != null) {
            neg.setContrato(per.getContrato());
        }
        if (per.getPlanBeneficios() != null) {
            neg.setPlanBeneficio(per.getPlanBeneficios());
        }
        if (per.getNumeroPoliza() != null) {
            neg.setNumeroPoliza(per.getNumeroPoliza());
        }
        neg.setValorCopago(per.getValorCopago());
        neg.setValorAPagar(per.getValorAPagar());
        if (per.getValorComision() != null) {
            neg.setValorComision(per.getValorComision());
        }
        if (per.getValorDescuento() != null) {
            neg.setValorDescuento(per.getValorDescuento());
        }
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAhHospitalizacion castEntidadNegocio(CmRipsAhHospitalizaciones per) {
        CmRipsAhHospitalizacion neg = new CmRipsAhHospitalizacion();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setCodigoReps(per.getCodigoReps());
        neg.setId(per.getFila());
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        //MAE VIA INGRESO
        if (per.getMaeViaIngresoCodigo() != null) {
            neg.setMaeViaIngresoCodigo(per.getMaeViaIngresoCodigo());
            if (per.getMaeViaIngresoId() != null) {
                neg.setMaeViaIngresoId(per.getMaeViaIngresoId());
            }
            if (per.getMaeViaIngresoValor() != null) {
                neg.setMaeViaIngresoValor(per.getMaeViaIngresoValor());
            }
        }
        //MAE CAUSA EXTERNA
        if (per.getMaeCausaExternaCodigo() != null) {
            neg.setMaeCausaExternaCodigo(per.getMaeViaIngresoCodigo());
            if (per.getMaeCausaExternaId() != null) {
                neg.setMaeCausaExternaId(per.getMaeCausaExternaId());
            }
            if (per.getMaeCausaExternaValor() != null) {
                neg.setMaeCausaExternaValor(per.getMaeCausaExternaValor());
            }
        }
        //MA DIAGNOSTICO INGRESO
        if (per.getMaDiagnosticoIngresoCodigo() != null) {
            neg.setMaDiagnosticoIngresoCodigo(per.getMaDiagnosticoIngresoCodigo());
            neg.setMaDiagnosticoIngresoId(per.getMaDiagnosticoIngresoId());
            if (per.getMaDiagnosticoIngresoValor() != null) {
                neg.setMaDiagnosticoIngresoValor(per.getMaDiagnosticoIngresoValor());
            }
        }
        //MA DIAGNOSTICO EGRESO
        if (per.getMaDiagnosticoEgresoCodigo() != null) {
            neg.setMaDiagnosticoEgresoCodigo(per.getMaDiagnosticoEgresoCodigo());
            if (per.getMaDiagnosticoEgresoId() != null) {
                neg.setMaDiagnosticoEgresoId(per.getMaDiagnosticoEgresoId());
            }
            if (per.getMaDiagnosticoEgresoValor() != null) {
                neg.setMaDiagnosticoEgresoValor(per.getMaDiagnosticoEgresoValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoRelacionado1Codigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (per.getMaDiagnosticoRelacionado2Codigo() != null) {
            neg.setMaDiagnosticoRelacionado2Codigo(per.getMaDiagnosticoRelacionado2Codigo());
            if (per.getMaDiagnosticoRelacionado2Id() != null) {
                neg.setMaDiagnosticoRelacionado2Id(per.getMaDiagnosticoRelacionado2Id());
            }
            if (per.getMaDiagnosticoRelacionado2Valor() != null) {
                neg.setMaDiagnosticoRelacionado2Valor(per.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoMuerteValor() != null) {
                neg.setMaDiagnosticoMuerteValor(per.getMaDiagnosticoMuerteValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoMuerteValor() != null) {
                neg.setMaDiagnosticoMuerteValor(per.getMaDiagnosticoMuerteValor());
            }
        }
        //MA DIAGNOSTICO COMPLIACION
        if (per.getMaDiagnosticoComplicacionCodigo() != null) {
            neg.setMaDiagnosticoComplicacionCodigo(per.getMaDiagnosticoComplicacionCodigo());
            if (per.getMaDiagnosticoComplicacionId() != null) {
                neg.setMaDiagnosticoComplicacionId(per.getMaDiagnosticoComplicacionId());
            }
            if (per.getMaDiagnosticoComplicacionValor() != null) {
                neg.setMaDiagnosticoComplicacionValor(per.getMaDiagnosticoComplicacionValor());
            }
        }
        //MA DIAGNOSTICO COMPLIACION
        if (per.getMaDiagnosticoComplicacionCodigo() != null) {
            neg.setMaDiagnosticoComplicacionCodigo(per.getMaDiagnosticoComplicacionCodigo());
            if (per.getMaDiagnosticoComplicacionId() != null) {
                neg.setMaDiagnosticoComplicacionId(per.getMaDiagnosticoComplicacionId());
            }
            if (per.getMaDiagnosticoComplicacionValor() != null) {
                neg.setMaDiagnosticoComplicacionValor(per.getMaDiagnosticoComplicacionValor());
            }
        }
        //MAE ESTADO SALIDA
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE ID
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE VALOR
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE ID
        if (per.getMaDiagnosticoMuerteCodigo() != null) {
            neg.setMaDiagnosticoMuerteCodigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MAE  VIA INGRESO
        if (per.getMaeViaIngresoCodigo() != null) {
            neg.setMaeViaIngresoCodigo(per.getMaeViaIngresoCodigo());
            if (per.getMaeViaIngresoId() != null) {
                neg.setMaeViaIngresoId(per.getMaeViaIngresoId());
            }
            if (per.getMaeViaIngresoValor() != null) {
                neg.setMaeViaIngresoValor(per.getMaeViaIngresoValor());
            }
        }
        //MAE  CAUSA EXTERNA
        if (per.getMaeCausaExternaCodigo() != null) {
            neg.setMaeCausaExternaCodigo(per.getMaeViaIngresoCodigo());
            if (per.getMaeViaIngresoId() != null) {
                neg.setMaeViaIngresoId(per.getMaeViaIngresoId());
            }
            if (per.getMaeViaIngresoValor() != null) {
                neg.setMaeViaIngresoValor(per.getMaeViaIngresoValor());
            }
        }
        //MA DIAGNOSTICO CODIGO
        neg.setFechaIngreso(per.getFechaIngreso());
        neg.setHoraIngreso(per.getHoraIngreso());
        neg.setAutorizacion(per.getAutorizacion());
        if (per.getMaDiagnosticoIngresoCodigo() != null) {
            neg.setMaDiagnosticoIngresoCodigo(per.getMaDiagnosticoIngresoCodigo());
            neg.setMaDiagnosticoIngresoId(per.getMaDiagnosticoIngresoId());
        }
        if (per.getMaDiagnosticoIngresoValor() != null) {
            neg.setMaDiagnosticoIngresoValor(per.getMaDiagnosticoIngresoValor());
        }
        //MA DIAGNOSTICO EGRESO CODIGO
        if (per.getMaDiagnosticoEgresoCodigo() != null) {
            neg.setMaDiagnosticoEgresoCodigo(per.getMaDiagnosticoEgresoCodigo());
            if (per.getMaDiagnosticoEgresoId() != null) {
                neg.setMaDiagnosticoEgresoId(per.getMaDiagnosticoEgresoId());
            }
            if (per.getMaDiagnosticoEgresoValor() != null) {
                neg.setMaDiagnosticoEgresoValor(per.getMaDiagnosticoEgresoValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1 CODIGO
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoEgresoCodigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2 CODIGO
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoEgresoCodigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3 CODIGO
        if (per.getMaDiagnosticoMuerteCodigo() != null) {
            neg.setMaDiagnosticoMuerteCodigo(per.getMaDiagnosticoEgresoCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoMuerteValor() != null) {
                neg.setMaDiagnosticoMuerteValor(per.getMaDiagnosticoMuerteValor());
            }
        }
        //MA DIAGNOSTICO COMPLICACION
        if (per.getMaDiagnosticoComplicacionCodigo() != null) {
            neg.setMaDiagnosticoComplicacionCodigo(per.getMaDiagnosticoComplicacionCodigo());
            if (per.getMaDiagnosticoComplicacionId() != null) {
                neg.setMaDiagnosticoComplicacionId(per.getMaDiagnosticoComplicacionId());
            }
            if (per.getMaDiagnosticoComplicacionValor() != null) {
                neg.setMaDiagnosticoComplicacionValor(per.getMaDiagnosticoComplicacionValor());
            }
        }
        //MAE ESTADO SALIDA CODIGO
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaDiagnosticoComplicacionValor() != null) {
                neg.setMaDiagnosticoComplicacionValor(per.getMaDiagnosticoComplicacionValor());
            }
        }
        //MA DIAGNOSTICO MUERTE CODIGO
        if (per.getMaDiagnosticoMuerteCodigo() != null) {
            neg.setMaDiagnosticoMuerteCodigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoComplicacionValor() != null) {
                neg.setMaDiagnosticoComplicacionValor(per.getMaDiagnosticoComplicacionValor());
            }
        }
        neg.setFechaIngreso(per.getFechaIngreso());
        neg.setHoraIngreso(per.getHoraIngreso());
        neg.setAutorizacion(per.getAutorizacion());
        neg.setFechaSalida(per.getFechaSalida());
        neg.setHoraSalida(per.getHoraSalida());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAmMedicamento castEntidadNegocio(CmRipsAmMedicamentos per) {
        CmRipsAmMedicamento neg = new CmRipsAmMedicamento();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setFila(per.getFila());
        //MAE TIPO DOCUMENTO
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        neg.setAutorizacion(per.getAutorizacion());
        //MA MEDICAMENTO
        if (per.getMaMedicamentoCodigo() != null) {
            neg.setMaMedicamentoCodigo(per.getMaMedicamentoCodigo());
            if (per.getMaMedicamentoId() != null) {
                neg.setMaMedicamentoId(per.getMaMedicamentoId());
            }
            if (per.getMaMedicamentoValor() != null) {
                neg.setMaMedicamentoValor(per.getMaMedicamentoValor());
            }
        }
        //MAE TIPO MEDICAMENTO
        if (per.getMaeTipoMedicamentoCodigo() != null) {
            neg.setMaeTipoMedicamentoCodigo(per.getMaeTipoMedicamentoCodigo());
            if (per.getMaeTipoMedicamentoId() != null) {
                neg.setMaeTipoMedicamentoId(per.getMaeTipoMedicamentoId());
            }
            if (per.getMaeTipoMedicamentoValor() != null) {
                neg.setMaeTipoMedicamentoValor(per.getMaeTipoMedicamentoValor());
            }
        }
        neg.setNombreGenerico(per.getNombreGenerico());
        if (per.getFormaFarmaceutica() != null) {
            neg.setFormaFarmaceutica(per.getFormaFarmaceutica());
        }
        if (per.getConcentracion() != null) {
            neg.setConcentracion(per.getConcentracion());
        }
        if (neg.getUnidadMedida() != null) {
            per.setUnidadMedida(neg.getUnidadMedida());
        }
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setNumeroUnidad(per.getNumeroUnidades());
        neg.setValorUnitario(per.getValorUnitario());
        neg.setValorAPagar(per.getValorAPagar());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAnRecienNacido castEntidadNegocio(CmRipsAnRecienNacidos per) {
        CmRipsAnRecienNacido neg = new CmRipsAnRecienNacido();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setFila(per.getFila());
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setCodigoReps(per.getCodigoReps());
        //MAE TIPO DOCUMENTO
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaeTipoDocumentoMadreCodigo() != null) {
            neg.setMaeTipoDocumentoMadreCodigo(per.getMaeTipoDocumentoMadreCodigo());
            neg.setMaeTipoDocumentoMadreId(per.getMaeTipoDocumentoMadreId());
            if (per.getMaeTipoDocumentoMadreValor() != null) {
                neg.setMaeTipoDocumentoMadreValor(per.getMaeTipoDocumentoMadreValor());
            }
        }
        //MAE CONTROL PENATAL
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaeControlPenatalCodigo() != null) {
            neg.setMaeControlPenatalCodigo(per.getMaeControlPenatalCodigo());
            if (per.getMaeControlPenatalId() != null) {
                neg.setMaeControlPenatalId(per.getMaeControlPenatalId());
            }
            if (per.getMaeControlPenatalValor() != null) {
                neg.setMaeControlPenatalValor(per.getMaeControlPenatalValor());
            }
        }
        //MAE CODIGO SEXO
        if (per.getMaeSexoCodigo() != null) {
            neg.setMaeSexoCodigo(per.getMaeSexoCodigo());
            if (per.getMaeSexoId() != null) {
                neg.setMaeSexoId(per.getMaeSexoId());
            }
            if (per.getMaeSexoValor() != null) {
                neg.setMaeSexoValor(per.getMaeSexoValor());
            }
        }
        //MA DIAGNOSTICO PRINCIPAL
        if (per.getMaDiagnosticoPrincipalCodigo() != null) {
            neg.setMaDiagnosticoPrincipalCodigo(per.getMaDiagnosticoPrincipalCodigo());
            if (per.getMaDiagnosticoPrincipalId() != null) {
                neg.setMaDiagnosticoPrincipalId(per.getMaDiagnosticoPrincipalId());
            }
            if (per.getMaDiagnosticoPrincipalValor() != null) {
                neg.setMaDiagnosticoPrincipalValor(per.getMaDiagnosticoPrincipalValor());
            }
        }
        neg.setDocumentoAfiliadoMadre(per.getDocumentoAfiliadoMadre());
        neg.setFechaNacimiento(per.getFechaNacimiento());
        neg.setHoraNacimiento(per.getHoraNacimiento());
        if (per.getEdadGestacion() != null) {
            neg.setEdadGestacion(per.getEdadGestacion());
        }
        //MA CAUSA MUERTE
        if (per.getMaCausaMuerteDiagnosticoCodigo() != null) {
            neg.setMaCausaMuerteDiagnosticoCodigo(per.getMaCausaMuerteDiagnosticoCodigo());
            if (per.getMaCausaMuerteDiagnosticoId() != null) {
                neg.setMaCausaMuerteDiagnosticoId(per.getMaCausaMuerteDiagnosticoId());
            }
            if (per.getMaCausaMuerteDiagnosticoValor() != null) {
                neg.setMaCausaMuerteDiagnosticoValor(per.getMaDiagnosticoPrincipalValor());
            }
        }
        if (per.getPeso() != null) {
            neg.setPeso(per.getPeso());
        }
        if (per.getFechaMuerte() != null) {
            neg.setFechaMuerte(per.getFechaMuerte());
        }
        if (per.getHoraMuerte() != null) {
            neg.setHoraMuerte(per.getHoraMuerte());
        }
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsApProcedimiento castEntidadNegocio(CmRipsApProcedimientos per) {
        CmRipsApProcedimiento neg = new CmRipsApProcedimiento();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setFila(per.getFila());
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setCodigoReps(per.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        neg.setFechaProcedimiento(per.getFechaProcedimiento());
        neg.setAutorizacion(per.getAutorizacion());
        //MA TECNOLOGIA
        if (per.getMaTecnologiaCodigo() != null) {
            neg.setMaTecnologiaCodigo(per.getMaTecnologiaCodigo());
            if (per.getMaTecnologiaId() != null) {
                neg.setMaTecnologiaId(per.getMaTecnologiaId());
            }
            if (per.getMaTecnologiaValor() != null) {
                neg.setMaTecnologiaValor(per.getMaTecnologiaValor());
            }
        }
        //MAE CODIGO AMBITO ATENCION
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaeAmbitoAtencionCodigo() != null) {
            neg.setMaeAmbitoAtencionCodigo(per.getMaeAmbitoAtencionCodigo());
            if (per.getMaeAmbitoAtencionId() != null) {
                neg.setMaeAmbitoAtencionId(per.getMaeAmbitoAtencionId());
            }
            if (per.getMaeAmbitoAtencionCodigoValor() != null) {
                neg.setMaeAmbitoAtencionValor(per.getMaeAmbitoAtencionCodigoValor());
            }
        }
        //MAE FINALIDAD PROCEDIMIENTOS
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaeFinalidadProcedimientoCodigo() != null) {
            neg.setMaeFinalidadProcedimientoCodigo(per.getMaeFinalidadProcedimientoCodigo());
            if (per.getMaeFinalidadProcedimientoId() != null) {
                neg.setMaeFinalidadProcedimientoId(per.getMaeFinalidadProcedimientoId());
            }
            if (per.getMaeFinalidadProcedimientoValor() != null) {
                neg.setMaeFinalidadProcedimientoValor(per.getMaeFinalidadProcedimientoValor());
            }
        }
        //MAE PERSONAL ATIENDE
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaePersonalAtiendeCodigo() != null) {
            neg.setMaePersonalAtiendeCodigo(per.getMaePersonalAtiendeCodigo());
            if (per.getMaePersonalAtiendeId() != null) {
                neg.setMaePersonalAtiendeId(per.getMaePersonalAtiendeId());
            }
            if (per.getMaePersonalAtiendeValor() != null) {
                neg.setMaePersonalAtiendeValor(per.getMaePersonalAtiendeValor());
            }
        }
        //MAE DIAGNOSTICO PRINCIPAL
        if (per.getMaDiagnosticoPrincipalCodigo() != null) {
            neg.setMaDiagnosticoPrincipalCodigo(per.getMaDiagnosticoPrincipalCodigo());
            if (per.getMaDiagnosticoPrincipalId() != null) {
                neg.setMaDiagnosticoPrincipalId(per.getMaDiagnosticoPrincipalId());
            }
            if (per.getMaDiagnosticoPrincipalValor() != null) {
                neg.setMaDiagnosticoPrincipalValor(per.getMaDiagnosticoPrincipalValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoRelacionado1Codigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (per.getMaDiagnosticoRelacionado2Codigo() != null) {
            neg.setMaDiagnosticoRelacionado2Codigo(per.getMaDiagnosticoRelacionado2Codigo());
            if (per.getMaDiagnosticoRelacionado2Id() != null) {
                neg.setMaDiagnosticoRelacionado2Id(per.getMaDiagnosticoRelacionado2Id());
            }
            if (per.getMaDiagnosticoRelacionado2Valor() != null) {
                neg.setMaDiagnosticoRelacionado2Valor(per.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MAE FORMA ACTO
        if (per.getMaeFormaActoCodigo() != null) {
            neg.setMaeFormaActoCodigo(per.getMaeFormaActoCodigo());
            if (per.getMaeFormaActoId() != null) {
                neg.setMaeFormaActoId(per.getMaeFormaActoId());
            }
            if (per.getMaeFormaActoValor() != null) {
                neg.setMaeFormaActoValor(per.getMaeFormaActoValor());
            }
        }
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setValorAPagar(per.getValorAPagar());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAtOtrosServicio castEntidadNegocio(CmRipsAtOtrosServicios per) {
        CmRipsAtOtrosServicio neg = new CmRipsAtOtrosServicio();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setFila(per.getFila());
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setCodigoReps(per.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        neg.setAutorizacion(per.getAutorizacion());
        //MAE TIPO SERVICIO
        if (per.getMaeTipoServicioCodigo() != null) {
            neg.setMaeTipoServicioCodigo(per.getMaeTipoServicioCodigo());
            if (per.getMaeTipoServicioId() != null) {
                neg.setMaeTipoServicioId(per.getMaeTipoServicioId());
            }
            if (per.getMaeTipoServicioValor() != null) {
                neg.setMaeTipoServicioValor(per.getMaeTipoServicioValor());
            }
        }
        //MAE INSUMO
        if (per.getMaTecnologiaCodigo() != null) {
            neg.setMaTecnologiaCodigo(per.getMaTecnologiaCodigo());
            if (per.getMaTecnologiaId() != null) {
                neg.setMaTecnologiaId(per.getMaTecnologiaId());
            }
            if (per.getMaTecnologiaValor() != null) {
                neg.setMaTecnologiaValor(per.getMaTecnologiaValor());
            }
        }
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setNombreServicio(per.getNombreServicio());
        neg.setUnidades(per.getUnidades());
        neg.setValorUnidades(per.getValorUnidades());
        neg.setTotal(per.getTotal());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAuUrgencia castEntidadNegocio(CmRipsAuUrgencias per) {
        CmRipsAuUrgencia neg = new CmRipsAuUrgencia();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setFila(per.getFila());
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setCodigoReps(per.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        neg.setFechaIngreso(per.getFechaIngreso());
        neg.setHoraIngreso(per.getHoraIngreso());
        neg.setAutorizacion(per.getAutorizacion());
        //MAE CAUSA EXTERNA
        if (per.getMaeCausaExternaCodigo() != null) {
            neg.setMaeCausaExternaCodigo(per.getMaeCausaExternaCodigo());
            if (per.getMaeCausaExternaId() != null) {
                neg.setMaeCausaExternaId(per.getMaeCausaExternaId());
            }
            if (per.getMaeCausaExternaValor() != null) {
                neg.setMaeCausaExternaValor(per.getMaeCausaExternaValor());
            }
        }
        if (per.getMaDiagnosticoSalidaCodigo() != null) {
            neg.setMaDiagnosticoSalidaCodigo(per.getMaDiagnosticoSalidaCodigo());
            if (per.getMaDiagnosticoSalidaId() != null) {
                neg.setMaDiagnosticoSalidaId(per.getMaDiagnosticoSalidaId());
            }
            if (per.getMaDiagnosticoSalidaValor() != null) {
                neg.setMaDiagnosticoSalidaValor(per.getMaDiagnosticoSalidaValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoRelacionado1Codigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (per.getMaDiagnosticoRelacionado2Codigo() != null) {
            neg.setMaDiagnosticoRelacionado2Codigo(per.getMaDiagnosticoRelacionado2Codigo());
            if (per.getMaDiagnosticoRelacionado2Id() != null) {
                neg.setMaDiagnosticoRelacionado2Id(per.getMaDiagnosticoRelacionado2Id());
            }
            if (per.getMaDiagnosticoRelacionado2Valor() != null) {
                neg.setMaDiagnosticoRelacionado2Valor(per.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (per.getMaDiagnosticoRelacionado3Codigo() != null) {
            neg.setMaDiagnosticoRelacionado3Codigo(per.getMaDiagnosticoRelacionado3Codigo());
            if (per.getMaDiagnosticoRelacionado3Id() != null) {
                neg.setMaDiagnosticoRelacionado3Id(per.getMaDiagnosticoRelacionado3Id());
            }
            if (per.getMaDiagnosticoRelacionado3Valor() != null) {
                neg.setMaDiagnosticoRelacionado3Valor(per.getMaDiagnosticoRelacionado3Valor());
            }
        }
        //MA DIAGNOSTICO MUERTE
        if (per.getMaDiagnosticoMuerteCodigo() != null) {
            neg.setMaDiagnosticoMuerteCodigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoMuerteValor() != null) {
                neg.setMaDiagnosticoMuerteValor(per.getMaDiagnosticoMuerteValor());
            }
        }
        //MAE DESTINO SALIDA
        if (per.getMaeDestinoSalidaCodigo() != null) {
            neg.setMaeDestinoSalidaCodigo(per.getMaeDestinoSalidaCodigo());
            if (per.getMaeDestinoSalidaId() != null) {
                neg.setMaeDestinoSalidaId(per.getMaeDestinoSalidaId());
            }
            if (per.getMaeDestinoSalidaValor() != null) {
                neg.setMaeDestinoSalidaValor(per.getMaeDestinoSalidaValor());
            }
        }
        //MAE ESTADO SALIDA
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (per.getMaDiagnosticoMuerteCodigo() != null) {
            neg.setMaDiagnosticoMuerteCodigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoMuerteValor() != null) {
                neg.setMaDiagnosticoMuerteValor(per.getMaDiagnosticoMuerteValor());
            }
        }
        neg.setFechaSalida(per.getFechaSalida());
        neg.setHoraSalida(per.getHoraSalida());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsUsUsuario castEntidadNegocio(CmRipsUsUsuarios per) {
        CmRipsUsUsuario neg = new CmRipsUsUsuario();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setFila(per.getFila());
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        if (per.getAdministradora() != null) {
            neg.setAdministradora(per.getAdministradora());
        }
        //MAE TIPO USUARIO
        if (per.getMaeTipoUsuarioCodigo() != null) {
            neg.setMaeTipoUsuarioCodigo(per.getMaeTipoUsuarioCodigo());
            if (per.getMaeTipoUsuarioId() != null) {
                neg.setMaeTipoUsuarioId(per.getMaeTipoUsuarioId());
            }
            if (per.getMaeTipoUsuarioValor() != null) {
                neg.setMaeTipoUsuarioValor(per.getMaeTipoUsuarioValor());
            }
        }
        neg.setPrimerApellido(per.getPrimerApellido());
        neg.setSegundoApellido(per.getSegundoApellido());
        neg.setPrimerNombre(per.getPrimerNombre());
        neg.setSegundoNombre(per.getSegundoNombre());
        if (per.getEdad() != null) {
            neg.setEdad(per.getEdad());
        }
        if (per.getCodigoUnidadMedidaEdad() != null) {
            neg.setCodigoUnidadMedidaEdad(per.getCodigoUnidadMedidaEdad());
        }
        //MAE SEXO
        if (per.getMaeSexoCodigo() != null) {
            neg.setMaeSexoCodigo(per.getMaeSexoCodigo());
            if (per.getMaeSexoId() != null) {
                neg.setMaeSexoId(per.getMaeTipoUsuarioId());
            }
            if (per.getMaeSexoValor() != null) {
                neg.setMaeSexoValor(per.getMaeSexoValor());
            }
        }
        //MAE DEPARTAMENTO
        if (per.getDepartamentoCodigo() != null) {
            neg.setDepartamentoCodigo(per.getDepartamentoCodigo());
            if (per.getDepartamentoId() != null) {
                neg.setDepartamentoId(per.getDepartamentoId());
            }
            if (per.getDepartamentoNombre() != null) {
                neg.setDepartamentoNombre(per.getDepartamentoNombre());
            }
        }
        //MAE CIUDAD
        if (per.getCiudadCodigo() != null) {
            neg.setCiudadCodigo(per.getCiudadCodigo());
            if (per.getCiudadId() != null) {
                neg.setCiudadId(per.getCiudadId());
            }
            if (per.getCiudadNombre() != null) {
                neg.setCiudadNombre(per.getCiudadNombre());
            }
        }
        //MAE ZONA RESIDENCIA
        if (per.getMaeZonaResidenciaCodigo() != null) {
            neg.setMaeZonaResidenciaCodigo(per.getMaeZonaResidenciaCodigo());
            if (per.getMaeZonaResidenciaId() != null) {
                neg.setMaeZonaResidenciaId(per.getMaeZonaResidenciaId());
            }
            if (per.getMaeZonaResidenciaValor() != null) {
                neg.setMaeZonaResidenciaValor(per.getMaeZonaResidenciaValor());
            }
        }
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    /*
    ENTIDAD NEGOCIO CARGAS
     */
    public static CmRipsCtControlObj castEntidadNegocio(CmRipsCargaCtControl per) {
        CmRipsCtControlObj neg = new CmRipsCtControlObj();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCodigoReps(per.getCodigoReps());
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setArchivoControl(per.getArchivoControl());
        neg.setArchivoAf(per.getArchivoAf());
        neg.setArchivoUs(per.getArchivoUs());
        neg.setArchivoAd(per.getArchivoAd());
        neg.setArchivoAc(per.getArchivoAc());
        neg.setArchivoAp(per.getArchivoAp());
        neg.setArchivoAh(per.getArchivoAh());
        neg.setArchivoAu(per.getArchivoAu());
        neg.setArchivoAn(per.getArchivoAn());
        neg.setArchivoAm(per.getArchivoAm());
        neg.setArchivoAt(per.getArchivoAt());
        neg.setRegistrosAf(per.getRegistrosAf());
        neg.setRegistroAc(per.getRegistroAc());
        neg.setRegistrosUs(per.getRegistrosUs());
        neg.setRegistrosAp(per.getRegistrosAp());
        neg.setRegistrosAh(per.getRegistrosAh());
        neg.setRegistrosAu(per.getRegistrosAu());
        neg.setRegistrosAm(per.getRegistrosAm());
        neg.setRegistrosAt(per.getRegistrosAt());
        neg.setRegistrosAn(per.getRegistrosAn());
        neg.setRegistrosAd(per.getRegistrosAd());
        neg.setFechaAf(per.getFechaAf());
        neg.setFechaUs(per.getFechaUs());
        neg.setFechaAc(per.getFechaAc());
        neg.setFechaAp(per.getFechaAp());
        neg.setFechaAh(per.getFechaAh());
        neg.setFechaAu(per.getFechaAu());
        neg.setFechaAm(per.getFechaAm());
        neg.setFechaAt(per.getFechaAt());
        neg.setFechaAn(per.getFechaAn());
        neg.setFechaAd(per.getFechaAd());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAcConsulta castEntidadNegocio(CmRipsCargaAcConsultas per) {
        CmRipsAcConsulta neg = new CmRipsAcConsulta();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumFactura(per.getNumFactura());
        neg.setCodigoReps(per.getCodigoReps());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setArchivoNombre(per.getArchivoNombre());
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        if (per.getFechaConsulta() != null) {
            neg.setFechaConsulta(per.getFechaConsulta());
        }
        neg.setAutorizacion(per.getAutorizacion());
        if (per.getMaTecnologiaCodigo() != null) {
            neg.setMaTecnologiaCodigo(per.getMaTecnologiaCodigo());
        }
        if (per.getMaTecnologiaId() != null) {
            neg.setMaTecnologiaId(per.getMaTecnologiaId());
        }
        if (per.getMaTecnologiaValor() != null) {
            neg.setMaTecnologiaValor(per.getMaTecnologiaValor());
        }
        //MAE FINALIDAD CONSULTA
        if (per.getMaeFinalidadConsultaCodigo() != null) {
            neg.setMaeFinalidadConsultaCodigo(per.getMaeFinalidadConsultaCodigo());
            if (per.getMaeFinalidadConsultaId() != null) {
                neg.setMaeFinalidadConsultaId(per.getMaeFinalidadConsultaId());
            }
            if (per.getMaeFinalidadConsultaValor() != null) {
                neg.setMaeFinalidadConsultaValor(per.getMaeFinalidadConsultaValor());
            }
        }
        //MAE CAUSA EXTERNA
        if (per.getMaeCausaExternaCodigo() != null) {
            neg.setMaeCausaExternaCodigo(per.getMaeCausaExternaCodigo());
            if (per.getMaeCausaExternaId() != null) {
                neg.setMaeCausaExternaId(per.getMaeCausaExternaId());
            }
            if (per.getMaeCausaExternaValor() != null) {
                neg.setMaeCausaExternaValor(per.getMaeCausaExternaValor());
            }
        }
        //MA DIAGNOSTICO
        if (per.getMaDiagnosticoPrincipalCodigo() != null) {
            neg.setMaDiagnosticoPrincipalCodigo(per.getMaDiagnosticoPrincipalCodigo());
            if (per.getMaDiagnosticoPrincipalId() != null) {
                neg.setMaDiagnosticoPrincipalId(per.getMaDiagnosticoPrincipalId());
            }
            if (per.getMaDiagnosticoPrincipalValor() != null) {
                neg.setMaDiagnosticoPrincipalValor(per.getMaDiagnosticoPrincipalValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoRelacionado1Codigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (per.getMaDiagnosticoRelacionado2Codigo() != null) {
            neg.setMaDiagnosticoRelacionado2Codigo(per.getMaDiagnosticoRelacionado2Codigo());
            if (per.getMaDiagnosticoRelacionado2Id() != null) {
                neg.setMaDiagnosticoRelacionado2Id(per.getMaDiagnosticoRelacionado2Id());
            }
            if (per.getMaDiagnosticoRelacionado2Valor() != null) {
                neg.setMaDiagnosticoRelacionado2Valor(per.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (per.getMaDiagnosticoRelacionado3Codigo() != null) {
            neg.setMaDiagnosticoRelacionado3Codigo(per.getMaDiagnosticoRelacionado3Codigo());
            if (per.getMaDiagnosticoRelacionado3Id() != null) {
                neg.setMaDiagnosticoRelacionado3Id(per.getMaDiagnosticoRelacionado3Id());
            }
            if (per.getMaDiagnosticoRelacionado3Valor() != null) {
                neg.setMaDiagnosticoRelacionado3Valor(per.getMaDiagnosticoRelacionado3Valor());
            }
        }
        //MA TIPO DIAGNOSTICO 
        if (per.getMaeTipoDiagnosticoCodigo() != null) {
            neg.setMaeTipoDiagnosticoCodigo(per.getMaeTipoDiagnosticoCodigo());
            if (neg.getMaeTipoDiagnosticoId() != null) {
                neg.setMaeTipoDiagnosticoId(per.getMaeTipoDiagnosticoId());
            }
            if (per.getMaeTipoDiagnosticoValor() != null) {
                neg.setMaeTipoDiagnosticoValor(per.getMaeTipoDiagnosticoValor());
            }
        }
        neg.setValorConsulta(per.getValorConsulta());
        neg.setValorCuotaModeradora(per.getValorCuotaModeradora());
        neg.setValorAPagar(per.getValorAPagar());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAdServiciosAgrupado castEntidadNegocio(CmRipsCargaAdServiciosAgrupados per) {
        CmRipsAdServiciosAgrupado neg = new CmRipsAdServiciosAgrupado();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        per.setCmRipsCargasId(new CmRipsCargas(neg.getCmRipsCarga().getId()));
        per.setNumeroFactura(neg.getNumeroFactura());
        per.setCodigoReps(neg.getCodigoReps());
        per.setCantidadServicios(neg.getCantidadServicio());
        per.setValorUnitario(neg.getValorUnitario());
        per.setValorConcepto(neg.getValorConcepto());
        per.setArchivoNombre(neg.getArchivoNombre());
        per.setArchivoRuta(neg.getArchivoRuta());
        per.setArchivoNombreOriginal(neg.getArchivoNombreOriginal());
        per.setUsuarioCrea(neg.getUsuarioCrea());
        per.setTerminalCrea(neg.getTerminalCrea());
        per.setFechaHoraCrea(neg.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAfFactura castEntidadNegocio(CmRipsCargaAfFacturas per) {
        CmRipsAfFactura neg = new CmRipsAfFactura();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setCodigoReps(per.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setRazonSocial(per.getRazonSocial());
        neg.setNit(per.getNit().trim());
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setFechaFactura(per.getFechaFactura());
        if (per.getFechaInicio() != null) {
            neg.setFechaInicio(per.getFechaInicio());
        }
        if (per.getFechaFinal() != null) {
            neg.setFechaFinal(per.getFechaFinal());
        }
        neg.setCodigoEps(per.getCodigoEps());
        if (per.getNombreAdministradora() != null) {
            neg.setNombreAdministradora(per.getNombreAdministradora());
        }
        if (per.getContrato() != null) {
            neg.setContrato(per.getContrato());
        }
        if (per.getPlanBeneficios() != null) {
            neg.setPlanBeneficio(per.getPlanBeneficios());
        }
        if (per.getNumeroPoliza() != null) {
            neg.setNumeroPoliza(per.getNumeroPoliza());
        }
        neg.setValorCopago(per.getValorCopago());
        neg.setValorAPagar(per.getValorAPagar());
        if (per.getValorComision() != null) {
            neg.setValorComision(per.getValorComision());
        }
        if (per.getValorDescuento() != null) {
            neg.setValorDescuento(per.getValorDescuento());
        }
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAhHospitalizacion castEntidadNegocio(CmRipsCargaAhHospitalizaciones per) {
        CmRipsAhHospitalizacion neg = new CmRipsAhHospitalizacion();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setCodigoReps(per.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        //MAE VIA INGRESO
        if (per.getMaeViaIngresoCodigo() != null) {
            neg.setMaeViaIngresoCodigo(per.getMaeViaIngresoCodigo());
            if (per.getMaeViaIngresoId() != null) {
                neg.setMaeViaIngresoId(per.getMaeViaIngresoId());
            }
            if (per.getMaeViaIngresoValor() != null) {
                neg.setMaeViaIngresoValor(per.getMaeViaIngresoValor());
            }
        }
        //MAE CAUSA EXTERNA
        if (per.getMaeCausaExternaCodigo() != null) {
            neg.setMaeCausaExternaCodigo(per.getMaeViaIngresoCodigo());
            if (per.getMaeCausaExternaId() != null) {
                neg.setMaeCausaExternaId(per.getMaeCausaExternaId());
            }
            if (per.getMaeCausaExternaValor() != null) {
                neg.setMaeCausaExternaValor(per.getMaeCausaExternaValor());
            }
        }
        //MA DIAGNOSTICO INGRESO
        if (per.getMaDiagnosticoIngresoCodigo() != null) {
            neg.setMaDiagnosticoIngresoCodigo(per.getMaDiagnosticoIngresoCodigo());
            neg.setMaDiagnosticoIngresoId(per.getMaDiagnosticoIngresoId());
            if (per.getMaDiagnosticoIngresoValor() != null) {
                neg.setMaDiagnosticoIngresoValor(per.getMaDiagnosticoIngresoValor());
            }
        }
        //MA DIAGNOSTICO EGRESO
        if (per.getMaDiagnosticoEgresoCodigo() != null) {
            neg.setMaDiagnosticoEgresoCodigo(per.getMaDiagnosticoEgresoCodigo());
            if (per.getMaDiagnosticoEgresoId() != null) {
                neg.setMaDiagnosticoEgresoId(per.getMaDiagnosticoEgresoId());
            }
            if (per.getMaDiagnosticoEgresoValor() != null) {
                neg.setMaDiagnosticoEgresoValor(per.getMaDiagnosticoEgresoValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoRelacionado1Codigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (per.getMaDiagnosticoRelacionado2Codigo() != null) {
            neg.setMaDiagnosticoRelacionado2Codigo(per.getMaDiagnosticoRelacionado2Codigo());
            if (per.getMaDiagnosticoRelacionado2Id() != null) {
                neg.setMaDiagnosticoRelacionado2Id(per.getMaDiagnosticoRelacionado2Id());
            }
            if (per.getMaDiagnosticoRelacionado2Valor() != null) {
                neg.setMaDiagnosticoRelacionado2Valor(per.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoMuerteValor() != null) {
                neg.setMaDiagnosticoMuerteValor(per.getMaDiagnosticoMuerteValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoMuerteValor() != null) {
                neg.setMaDiagnosticoMuerteValor(per.getMaDiagnosticoMuerteValor());
            }
        }
        //MA DIAGNOSTICO COMPLIACION
        if (per.getMaDiagnosticoComplicacionCodigo() != null) {
            neg.setMaDiagnosticoComplicacionCodigo(per.getMaDiagnosticoComplicacionCodigo());
            if (per.getMaDiagnosticoComplicacionId() != null) {
                neg.setMaDiagnosticoComplicacionId(per.getMaDiagnosticoComplicacionId());
            }
            if (per.getMaDiagnosticoComplicacionValor() != null) {
                neg.setMaDiagnosticoComplicacionValor(per.getMaDiagnosticoComplicacionValor());
            }
        }
        //MA DIAGNOSTICO COMPLIACION
        if (per.getMaDiagnosticoComplicacionCodigo() != null) {
            neg.setMaDiagnosticoComplicacionCodigo(per.getMaDiagnosticoComplicacionCodigo());
            if (per.getMaDiagnosticoComplicacionId() != null) {
                neg.setMaDiagnosticoComplicacionId(per.getMaDiagnosticoComplicacionId());
            }
            if (per.getMaDiagnosticoComplicacionValor() != null) {
                neg.setMaDiagnosticoComplicacionValor(per.getMaDiagnosticoComplicacionValor());
            }
        }
        //MAE ESTADO SALIDA
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE ID
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE VALOR
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE ID
        if (per.getMaDiagnosticoMuerteCodigo() != null) {
            neg.setMaDiagnosticoMuerteCodigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MAE  VIA INGRESO
        if (per.getMaeViaIngresoCodigo() != null) {
            neg.setMaeViaIngresoCodigo(per.getMaeViaIngresoCodigo());
            if (per.getMaeViaIngresoId() != null) {
                neg.setMaeViaIngresoId(per.getMaeViaIngresoId());
            }
            if (per.getMaeViaIngresoValor() != null) {
                neg.setMaeViaIngresoValor(per.getMaeViaIngresoValor());
            }
        }
        //MAE  CAUSA EXTERNA
        if (per.getMaeCausaExternaCodigo() != null) {
            neg.setMaeCausaExternaCodigo(per.getMaeViaIngresoCodigo());
            if (per.getMaeViaIngresoId() != null) {
                neg.setMaeViaIngresoId(per.getMaeViaIngresoId());
            }
            if (per.getMaeViaIngresoValor() != null) {
                neg.setMaeViaIngresoValor(per.getMaeViaIngresoValor());
            }
        }
        //MA DIAGNOSTICO CODIGO
        neg.setFechaIngreso(per.getFechaIngreso());
        neg.setHoraIngreso(per.getHoraIngreso());
        neg.setAutorizacion(per.getAutorizacion());
        if (per.getMaDiagnosticoIngresoCodigo() != null) {
            neg.setMaDiagnosticoIngresoCodigo(per.getMaDiagnosticoIngresoCodigo());
            neg.setMaDiagnosticoIngresoId(per.getMaDiagnosticoIngresoId());
        }
        if (per.getMaDiagnosticoIngresoValor() != null) {
            neg.setMaDiagnosticoIngresoValor(per.getMaDiagnosticoIngresoValor());
        }
        //MA DIAGNOSTICO EGRESO CODIGO
        if (per.getMaDiagnosticoEgresoCodigo() != null) {
            neg.setMaDiagnosticoEgresoCodigo(per.getMaDiagnosticoEgresoCodigo());
            if (per.getMaDiagnosticoEgresoId() != null) {
                neg.setMaDiagnosticoEgresoId(per.getMaDiagnosticoEgresoId());
            }
            if (per.getMaDiagnosticoEgresoValor() != null) {
                neg.setMaDiagnosticoEgresoValor(per.getMaDiagnosticoEgresoValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1 CODIGO
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoEgresoCodigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2 CODIGO
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoEgresoCodigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3 CODIGO
        if (per.getMaDiagnosticoMuerteCodigo() != null) {
            neg.setMaDiagnosticoMuerteCodigo(per.getMaDiagnosticoEgresoCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoMuerteValor() != null) {
                neg.setMaDiagnosticoMuerteValor(per.getMaDiagnosticoMuerteValor());
            }
        }
        //MA DIAGNOSTICO COMPLICACION
        if (per.getMaDiagnosticoComplicacionCodigo() != null) {
            neg.setMaDiagnosticoComplicacionCodigo(per.getMaDiagnosticoComplicacionCodigo());
            if (per.getMaDiagnosticoComplicacionId() != null) {
                neg.setMaDiagnosticoComplicacionId(per.getMaDiagnosticoComplicacionId());
            }
            if (per.getMaDiagnosticoComplicacionValor() != null) {
                neg.setMaDiagnosticoComplicacionValor(per.getMaDiagnosticoComplicacionValor());
            }
        }
        //MAE ESTADO SALIDA CODIGO
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaDiagnosticoComplicacionValor() != null) {
                neg.setMaDiagnosticoComplicacionValor(per.getMaDiagnosticoComplicacionValor());
            }
        }
        //MA DIAGNOSTICO MUERTE CODIGO
        if (per.getMaDiagnosticoMuerteCodigo() != null) {
            neg.setMaDiagnosticoMuerteCodigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoComplicacionValor() != null) {
                neg.setMaDiagnosticoComplicacionValor(per.getMaDiagnosticoComplicacionValor());
            }
        }
        neg.setFechaIngreso(per.getFechaIngreso());
        neg.setHoraIngreso(per.getHoraIngreso());
        neg.setAutorizacion(per.getAutorizacion());
        neg.setFechaSalida(per.getFechaSalida());
        neg.setHoraSalida(per.getHoraSalida());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAmMedicamento castEntidadNegocio(CmRipsCargaAmMedicamentos per) {
        CmRipsAmMedicamento neg = new CmRipsAmMedicamento();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        //MAE TIPO DOCUMENTO
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        neg.setAutorizacion(per.getAutorizacion());
        //MA MEDICAMENTO
        if (per.getMaMedicamentoCodigo() != null) {
            neg.setMaMedicamentoCodigo(per.getMaMedicamentoCodigo());
            if (per.getMaMedicamentoId() != null) {
                neg.setMaMedicamentoId(per.getMaMedicamentoId());
            }
            if (per.getMaMedicamentoValor() != null) {
                neg.setMaMedicamentoValor(per.getMaMedicamentoValor());
            }
        }
        //MAE TIPO MEDICAMENTO
        if (per.getMaeTipoMedicamentoCodigo() != null) {
            neg.setMaeTipoMedicamentoCodigo(per.getMaeTipoMedicamentoCodigo());
            if (per.getMaeTipoMedicamentoId() != null) {
                neg.setMaeTipoMedicamentoId(per.getMaeTipoMedicamentoId());
            }
            if (per.getMaeTipoMedicamentoValor() != null) {
                neg.setMaeTipoMedicamentoValor(per.getMaeTipoMedicamentoValor());
            }
        }
        neg.setNombreGenerico(per.getNombreGenerico());
        if (per.getFormaFarmaceutica() != null) {
            neg.setFormaFarmaceutica(per.getFormaFarmaceutica());
        }
        if (per.getConcentracion() != null) {
            neg.setConcentracion(per.getConcentracion());
        }
        if (neg.getUnidadMedida() != null) {
            per.setUnidadMedida(neg.getUnidadMedida());
        }
        neg.setNumeroUnidad(per.getNumeroUnidades());
        neg.setValorUnitario(per.getValorUnitario());
        neg.setValorAPagar(per.getValorAPagar());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAnRecienNacido castEntidadNegocio(CmRipsCargaAnRecienNacidos per) {
        CmRipsAnRecienNacido neg = new CmRipsAnRecienNacido();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setCodigoReps(per.getCodigoReps());
        //MAE TIPO DOCUMENTO
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaeTipoDocumentoMadreCodigo() != null) {
            neg.setMaeTipoDocumentoMadreCodigo(per.getMaeTipoDocumentoMadreCodigo());
            neg.setMaeTipoDocumentoMadreId(per.getMaeTipoDocumentoMadreId());
            if (per.getMaeTipoDocumentoMadreValor() != null) {
                neg.setMaeTipoDocumentoMadreValor(per.getMaeTipoDocumentoMadreValor());
            }
        }
        //MAE CONTROL PENATAL
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaeControlPenatalCodigo() != null) {
            neg.setMaeControlPenatalCodigo(per.getMaeControlPenatalCodigo());
            if (per.getMaeControlPenatalId() != null) {
                neg.setMaeControlPenatalId(per.getMaeControlPenatalId());
            }
            if (per.getMaeControlPenatalValor() != null) {
                neg.setMaeControlPenatalValor(per.getMaeControlPenatalValor());
            }
        }
        //MAE CODIGO SEXO
        if (per.getMaeSexoCodigo() != null) {
            neg.setMaeSexoCodigo(per.getMaeSexoCodigo());
            if (per.getMaeSexoId() != null) {
                neg.setMaeSexoId(per.getMaeSexoId());
            }
            if (per.getMaeSexoValor() != null) {
                neg.setMaeSexoValor(per.getMaeSexoValor());
            }
        }
        //MA DIAGNOSTICO PRINCIPAL
        if (per.getMaDiagnosticoPrincipalCodigo() != null) {
            neg.setMaDiagnosticoPrincipalCodigo(per.getMaDiagnosticoPrincipalCodigo());
            if (per.getMaDiagnosticoPrincipalId() != null) {
                neg.setMaDiagnosticoPrincipalId(per.getMaDiagnosticoPrincipalId());
            }
            if (per.getMaDiagnosticoPrincipalValor() != null) {
                neg.setMaDiagnosticoPrincipalValor(per.getMaDiagnosticoPrincipalValor());
            }
        }
        neg.setDocumentoAfiliadoMadre(per.getDocumentoAfiliadoMadre());
        neg.setFechaNacimiento(per.getFechaNacimiento());
        neg.setHoraNacimiento(per.getHoraNacimiento());
        if (per.getEdadGestacion() != null) {
            neg.setEdadGestacion(per.getEdadGestacion());
        }
        //MA CAUSA MUERTE
        if (per.getMaCausaMuerteDiagnosticoCodigo() != null) {
            neg.setMaCausaMuerteDiagnosticoCodigo(per.getMaCausaMuerteDiagnosticoCodigo());
            if (per.getMaCausaMuerteDiagnosticoId() != null) {
                neg.setMaCausaMuerteDiagnosticoId(per.getMaCausaMuerteDiagnosticoId());
            }
            if (per.getMaCausaMuerteDiagnosticoValor() != null) {
                neg.setMaCausaMuerteDiagnosticoValor(per.getMaDiagnosticoPrincipalValor());
            }
        }
        if (per.getPeso() != null) {
            neg.setPeso(per.getPeso());
        }
        if (per.getFechaMuerte() != null) {
            neg.setFechaMuerte(per.getFechaMuerte());
        }
        if (per.getHoraMuerte() != null) {
            neg.setHoraMuerte(per.getHoraMuerte());
        }
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsApProcedimiento castEntidadNegocio(CmRipsCargaApProcedimientos per) {
        CmRipsApProcedimiento neg = new CmRipsApProcedimiento();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setCodigoReps(per.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        neg.setFechaProcedimiento(per.getFechaProcedimiento());
        neg.setAutorizacion(per.getAutorizacion());
        //neg.setCodigoServicio(per.getCodigoServicio());
        //MAE CODIGO AMBITO ATENCION
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaeAmbitoAtencionCodigo() != null) {
            neg.setMaeAmbitoAtencionCodigo(per.getMaeAmbitoAtencionCodigo());
            if (per.getMaeAmbitoAtencionId() != null) {
                neg.setMaeAmbitoAtencionId(per.getMaeAmbitoAtencionId());
            }
            if (per.getMaeAmbitoAtencionCodigoValor() != null) {
                neg.setMaeAmbitoAtencionValor(per.getMaeAmbitoAtencionCodigoValor());
            }
        }
        //MAE FINALIDAD PROCEDIMIENTOS
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaeFinalidadProcedimientoCodigo() != null) {
            neg.setMaeFinalidadProcedimientoCodigo(per.getMaeFinalidadProcedimientoCodigo());
            if (per.getMaeFinalidadProcedimientoId() != null) {
                neg.setMaeFinalidadProcedimientoId(per.getMaeFinalidadProcedimientoId());
            }
            if (per.getMaeFinalidadProcedimientoValor() != null) {
                neg.setMaeFinalidadProcedimientoValor(per.getMaeFinalidadProcedimientoValor());
            }
        }
        //MAE PERSONAL ATIENDE
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaePersonalAtiendeCodigo() != null) {
            neg.setMaePersonalAtiendeCodigo(per.getMaePersonalAtiendeCodigo());
            if (per.getMaePersonalAtiendeId() != null) {
                neg.setMaePersonalAtiendeId(per.getMaePersonalAtiendeId());
            }
            if (per.getMaePersonalAtiendeValor() != null) {
                neg.setMaePersonalAtiendeValor(per.getMaePersonalAtiendeValor());
            }
        }
        //MAE DIAGNOSTICO PRINCIPAL
        if (per.getMaDiagnosticoPrincipalCodigo() != null) {
            neg.setMaDiagnosticoPrincipalCodigo(per.getMaDiagnosticoPrincipalCodigo());
            if (per.getMaDiagnosticoPrincipalId() != null) {
                neg.setMaDiagnosticoPrincipalId(per.getMaDiagnosticoPrincipalId());
            }
            if (per.getMaDiagnosticoPrincipalValor() != null) {
                neg.setMaDiagnosticoPrincipalValor(per.getMaDiagnosticoPrincipalValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoRelacionado1Codigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (per.getMaDiagnosticoRelacionado2Codigo() != null) {
            neg.setMaDiagnosticoRelacionado2Codigo(per.getMaDiagnosticoRelacionado2Codigo());
            if (per.getMaDiagnosticoRelacionado2Id() != null) {
                neg.setMaDiagnosticoRelacionado2Id(per.getMaDiagnosticoRelacionado2Id());
            }
            if (per.getMaDiagnosticoRelacionado2Valor() != null) {
                neg.setMaDiagnosticoRelacionado2Valor(per.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MAE FORMA ACTO
        if (per.getMaeFormaActoCodigo() != null) {
            neg.setMaeFormaActoCodigo(per.getMaeFormaActoCodigo());
            if (per.getMaeFormaActoId() != null) {
                neg.setMaeFormaActoId(per.getMaeFormaActoId());
            }
            if (per.getMaeFormaActoValor() != null) {
                neg.setMaeFormaActoValor(per.getMaeFormaActoValor());
            }
        }
        neg.setValorAPagar(per.getValorAPagar());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAtOtrosServicio castEntidadNegocio(CmRipsCargaAtOtrosServicios per) {
        CmRipsAtOtrosServicio neg = new CmRipsAtOtrosServicio();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setCodigoReps(per.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        neg.setAutorizacion(per.getAutorizacion());
        //MAE TIPO SERVICIO
        if (per.getMaeTipoServicioCodigo() != null) {
            neg.setMaeTipoServicioCodigo(per.getMaeTipoServicioCodigo());
            if (per.getMaeTipoServicioId() != null) {
                neg.setMaeTipoServicioId(per.getMaeTipoServicioId());
            }
            if (per.getMaeTipoServicioValor() != null) {
                neg.setMaeTipoServicioValor(per.getMaeTipoServicioValor());
            }
        }
        //MA TECNOLOGIA 
        if (per.getMaTecnologiaCodigo() != null) {
            neg.setMaTecnologiaCodigo(per.getMaTecnologiaCodigo());
            if (per.getMaTecnologiaId() != null) {
                neg.setMaTecnologiaId(per.getMaTecnologiaId());
            }
            if (per.getMaTecnologiaValor() != null) {
                neg.setMaTecnologiaValor(per.getMaTecnologiaValor());
            }
        }
        neg.setNombreServicio(per.getNombreServicio());
        neg.setUnidades(per.getUnidades());
        neg.setValorUnidades(per.getValorUnidades());
        neg.setTotal(per.getTotal());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAuUrgencia castEntidadNegocio(CmRipsCargaAuUrgencias per) {
        CmRipsAuUrgencia neg = new CmRipsAuUrgencia();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setCodigoReps(per.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        neg.setFechaIngreso(per.getFechaIngreso());
        neg.setHoraIngreso(per.getHoraIngreso());
        neg.setAutorizacion(per.getAutorizacion());
        //MAE CAUSA EXTERNA
        if (per.getMaeCausaExternaCodigo() != null) {
            neg.setMaeCausaExternaCodigo(per.getMaeCausaExternaCodigo());
            if (per.getMaeCausaExternaId() != null) {
                neg.setMaeCausaExternaId(per.getMaeCausaExternaId());
            }
            if (per.getMaeCausaExternaValor() != null) {
                neg.setMaeCausaExternaValor(per.getMaeCausaExternaValor());
            }
        }
        if (per.getMaDiagnosticoSalidaCodigo() != null) {
            neg.setMaDiagnosticoSalidaCodigo(per.getMaDiagnosticoSalidaCodigo());
            if (per.getMaDiagnosticoSalidaId() != null) {
                neg.setMaDiagnosticoSalidaId(per.getMaDiagnosticoSalidaId());
            }
            if (per.getMaDiagnosticoSalidaValor() != null) {
                neg.setMaDiagnosticoSalidaValor(per.getMaDiagnosticoSalidaValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoRelacionado1Codigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (per.getMaDiagnosticoRelacionado2Codigo() != null) {
            neg.setMaDiagnosticoRelacionado2Codigo(per.getMaDiagnosticoRelacionado2Codigo());
            if (per.getMaDiagnosticoRelacionado2Id() != null) {
                neg.setMaDiagnosticoRelacionado2Id(per.getMaDiagnosticoRelacionado2Id());
            }
            if (per.getMaDiagnosticoRelacionado2Valor() != null) {
                neg.setMaDiagnosticoRelacionado2Valor(per.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (per.getMaDiagnosticoMuerteCodigo() != null) {
            neg.setMaDiagnosticoMuerteCodigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoMuerteValor() != null) {
                neg.setMaDiagnosticoMuerteValor(per.getMaDiagnosticoMuerteValor());
            }
        }
        //MAE DESTINO SALIDA
        if (per.getMaeDestinoSalidaCodigo() != null) {
            neg.setMaeDestinoSalidaCodigo(per.getMaeDestinoSalidaCodigo());
            if (per.getMaeDestinoSalidaId() != null) {
                neg.setMaeDestinoSalidaId(per.getMaeDestinoSalidaId());
            }
            if (per.getMaeDestinoSalidaValor() != null) {
                neg.setMaeDestinoSalidaValor(per.getMaeDestinoSalidaValor());
            }
        }
        //MAE ESTADO SALIDA
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (per.getMaDiagnosticoMuerteCodigo() != null) {
            neg.setMaDiagnosticoMuerteCodigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoMuerteValor() != null) {
                neg.setMaDiagnosticoMuerteValor(per.getMaDiagnosticoMuerteValor());
            }
        }
        neg.setFechaSalida(per.getFechaSalida());
        neg.setHoraSalida(per.getHoraSalida());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsUsUsuario castEntidadNegocio(CmRipsCargaUsUsuarios per) {
        CmRipsUsUsuario neg = new CmRipsUsUsuario();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        if (per.getAdministradora() != null) {
            neg.setAdministradora(per.getAdministradora());
        }
        //MAE TIPO USUARIO
        if (per.getMaeTipoUsuarioCodigo() != null) {
            neg.setMaeTipoUsuarioCodigo(per.getMaeTipoUsuarioCodigo());
            if (per.getMaeTipoUsuarioId() != null) {
                neg.setMaeTipoUsuarioId(per.getMaeTipoUsuarioId());
            }
            if (per.getMaeTipoUsuarioValor() != null) {
                neg.setMaeTipoUsuarioValor(per.getMaeTipoUsuarioValor());
            }
        }
        neg.setPrimerApellido(per.getPrimerApellido());
        neg.setSegundoApellido(per.getSegundoApellido());
        neg.setPrimerNombre(per.getPrimerNombre());
        neg.setSegundoNombre(per.getSegundoNombre());
        if (per.getEdad() != null) {
            neg.setEdad(per.getEdad());
        }
        if (per.getCodigoUnidadMedidaEdad() != null) {
            neg.setCodigoUnidadMedidaEdad(per.getCodigoUnidadMedidaEdad());
        }
        //MAE SEXO
        if (per.getMaeSexoCodigo() != null) {
            neg.setMaeSexoCodigo(per.getMaeSexoCodigo());
            if (per.getMaeSexoId() != null) {
                neg.setMaeSexoId(per.getMaeTipoUsuarioId());
            }
            if (per.getMaeSexoValor() != null) {
                neg.setMaeSexoValor(per.getMaeSexoValor());
            }
        }
        //MAE DEPARTAMENTO
        if (per.getDepartamentoCodigo() != null) {
            neg.setDepartamentoCodigo(per.getDepartamentoCodigo());
            if (per.getDepartamentoId() != null) {
                neg.setDepartamentoId(per.getDepartamentoId());
            }
            if (per.getDepartamentoNombre() != null) {
                neg.setDepartamentoNombre(per.getDepartamentoNombre());
            }
        }
        //MAE CIUDAD
        if (per.getCiudadCodigo() != null) {
            neg.setCiudadCodigo(per.getCiudadCodigo());
            if (per.getCiudadId() != null) {
                neg.setCiudadId(per.getCiudadId());
            }
            if (per.getCiudadNombre() != null) {
                neg.setCiudadNombre(per.getCiudadNombre());
            }
        }
        //MAE ZONA RESIDENCIA
        if (per.getMaeZonaResidenciaCodigo() != null) {
            neg.setMaeZonaResidenciaCodigo(per.getMaeZonaResidenciaCodigo());
            if (per.getMaeZonaResidenciaId() != null) {
                neg.setMaeZonaResidenciaId(per.getMaeZonaResidenciaId());
            }
            if (per.getMaeZonaResidenciaValor() != null) {
                neg.setMaeZonaResidenciaValor(per.getMaeZonaResidenciaValor());
            }
        }
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    private CmRipsCargaDetalleDTO castDetallesToDetalle(Object[] per, int id) {
        CmRipsCargaDetalleDTO detalleDTO = new CmRipsCargaDetalleDTO();
        detalleDTO.setId(id);
        detalleDTO.setNumeroFactura(per[0].toString());
        detalleDTO.setCodigoDocumento(per[1].toString());
        detalleDTO.setDocumento(per[2].toString());
        if (per[3] != null) {
            detalleDTO.setFecha((Date) per[3]);
        }
        detalleDTO.setAutorizacion(per[4].toString());
        detalleDTO.setCodigoServicio(per[5].toString());
        detalleDTO.setValor(new BigDecimal((per[6].toString())));
        detalleDTO.setCantidad(Integer.parseInt(per[7].toString()));
        detalleDTO.setCopago(new BigDecimal((per[8].toString())));
        detalleDTO.setValorCuotaModeradora(new BigDecimal((per[9].toString())));
        detalleDTO.setTotal(new BigDecimal((per[10].toString())));
        detalleDTO.setTipoServicio(Integer.parseInt((per[11].toString())));
        detalleDTO.setNombreServicio(per[12].toString());
        return detalleDTO;
    }

    private CntContrato castEntidadNegocio(CntContratos ent) {
        CntContrato neg = new CntContrato();
        neg.setContrato(ent.getContrato());
        return neg;
    }

    private CmRipsCargaAnexo castEntidadNegocioCorto(CmRipsCargaAnexos ent) {
        CmRipsCargaAnexo carga = new CmRipsCargaAnexo();
        carga.setArchivoNombreOriginal(ent.getArchivoNombreOriginal());
        carga.setArchivoNombre(ent.getArchivoNombre());
        carga.setArchivoRuta(ent.getArchivoRuta());
        carga.setTipoArchivo(ent.getTipoArchivo());
        return carga;
    }

    Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    @Override
    public List<CmRipsCarga> consultarSedeEnProceso(int[] estados, Integer id) throws Exception {
        List<CmRipsCarga> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmRipsCargas c "
                    + " WHERE c.id > 0 ";
            if (estados != null) {
                boolean primero = true;
                for (int i = 0; i <= (estados.length - 1); i++) {
                    if (primero) {
                        strQuery += "AND (c.estado = " + estados[i] + " ";
                        primero = false;
                    } else {
                        strQuery += "OR c.estado = " + estados[i] + " ";
                    }
                }
                strQuery += ") ";
                strQuery += "AND c.gnPrestadorSedesId.id = " + id + " ";
            }
            Query query = getEntityManager().createQuery(strQuery);
            List<CmRipsCargas> list = query.getResultList();
            for (CmRipsCargas per : list) {
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
}
