package com.saviasaludeps.savia.ejb.servicios.mipres;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacion;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoSede;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.maestro.MaInsumoMipres;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaPaquete;
import com.saviasaludeps.savia.dominio.maestro.MaPaqueteMipres;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologiaMipres;
import com.saviasaludeps.savia.dominio.mipres.MpAnuladaPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpCodigoInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpCodigoUnirss;
import com.saviasaludeps.savia.dominio.mipres.MpCotizacion;
import com.saviasaludeps.savia.dominio.mipres.MpCotizacionDetalle;
import com.saviasaludeps.savia.dominio.mipres.MpDetalleItem;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamiento;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamientoEntregado;
import com.saviasaludeps.savia.dominio.mipres.MpEntregaFactura;
import com.saviasaludeps.savia.dominio.mipres.MpEntregaSuministro;
import com.saviasaludeps.savia.dominio.mipres.MpHomologacion;
import com.saviasaludeps.savia.dominio.mipres.MpMedicamentoIndicacionUnirs;
import com.saviasaludeps.savia.dominio.mipres.MpMedicamentoPrincipioActivo;
import com.saviasaludeps.savia.dominio.mipres.MpNoDireccionado;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionAuditoria;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionDetalle;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionHistorico;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionProgramada;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionRecobrante;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.dominio.mipres.MpProgramadaEntrega;
import com.saviasaludeps.savia.dominio.mipres.ReporteDireccionamiento;
import com.saviasaludeps.savia.ejb.entidades.AuCotizaciones;
import com.saviasaludeps.savia.ejb.entidades.CntContratoDetalles;
import com.saviasaludeps.savia.ejb.entidades.CntContratos;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorUnionTemporal;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.entidades.MpMedicamentoIndicacionesUnirs;
import com.saviasaludeps.savia.ejb.entidades.MaInsumos;
import com.saviasaludeps.savia.ejb.entidades.MaInsumosMipres;
import com.saviasaludeps.savia.ejb.entidades.MaMedicamentos;
import com.saviasaludeps.savia.ejb.entidades.MaPaquetesMipres;
import com.saviasaludeps.savia.ejb.entidades.MaTecnologias;
import com.saviasaludeps.savia.ejb.entidades.MaTecnologiasMipres;
import com.saviasaludeps.savia.ejb.entidades.MpCodigoInsumos;
import com.saviasaludeps.savia.ejb.entidades.MpCotizaciones;
import com.saviasaludeps.savia.ejb.entidades.MpDireccionamientoEntregados;
import com.saviasaludeps.savia.ejb.entidades.MpDireccionamientos;
import com.saviasaludeps.savia.ejb.entidades.MpEntregaFacturas;
import com.saviasaludeps.savia.ejb.entidades.MpEntregaSuministros;
import com.saviasaludeps.savia.ejb.entidades.MpHomologaciones;
import com.saviasaludeps.savia.ejb.entidades.MpMedicamentoPrincipiosActivos;
import com.saviasaludeps.savia.ejb.entidades.MpNoDireccionados;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionAnulada;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionHistoricos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionInsumos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionItemAuditoria;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionMedicamentos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionProgramadas;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionRecobrantes;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionTecnologias;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripciones;
import com.saviasaludeps.savia.ejb.entidades.MpProgramadaEntregas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.mipres.MpPrescripcionDetalleRemoto;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author bsgomez
 */
@Stateless
@Remote(MpPrescripcionDetalleRemoto.class)
public class MpPrescripcionDetalleServicio extends GenericoServicio implements MpPrescripcionDetalleRemoto {

    private static final SimpleDateFormat formatoLargo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        try {
            String strQuery = "SELECT COUNT(p) FROM MpPrescripciones p WHERE p.id > 0";
            String prescripcion = "p";
            if (paramConsulta.getParametroConsulta1() != null) {
                switch (paramConsulta.getParametroConsulta1().toString()) {
                    case "1":
                        prescripcion = "m.mpPrescripcionesId";
                        strQuery = "SELECT COUNT(" + prescripcion + ") FROM MpPrescripcionMedicamentos m WHERE m.id > 0";
                        if (paramConsulta.getParametroConsulta2() != null) {
                            strQuery += "AND m.estado = " + paramConsulta.getParametroConsulta2() + " ";
                        }
                        if (paramConsulta.getParametroConsulta3() != null) {
                            strQuery += " AND m.estadoJuntaProfesionales = " + paramConsulta.getParametroConsulta3() + " ";
                        }
                        if (paramConsulta.getParametroConsulta4() != null) {
                            strQuery += " AND m.descripcionMedicamentoPrincipioActivo LIKE '%" + paramConsulta.getParametroConsulta4() + "%'";
                        }
                        break;
                    case "4":
                        prescripcion = "m.mpPrescripcionesId";
                        strQuery = "SELECT COUNT(" + prescripcion + ") FROM MpPrescripcionMedicamentos m WHERE m.id > 0";
                        if (paramConsulta.getParametroConsulta2() != null) {
                            strQuery += "AND m.estado = " + paramConsulta.getParametroConsulta2() + " ";
                        }
                        if (paramConsulta.getParametroConsulta3() != null) {
                            strQuery += " AND m.estadoJuntaProfesionales = " + paramConsulta.getParametroConsulta3() + " ";
                        }
                        if (paramConsulta.getParametroConsulta4() != null) {
                            strQuery += " AND m.maeProductosNutricionalesValor LIKE '%" + paramConsulta.getParametroConsulta4() + "%'";
                        }
                        break;
                    case "2":
                        prescripcion = "t.mpPrescripcionId";
                        strQuery = "SELECT COUNT(" + prescripcion + ") FROM MpPrescripcionTecnologias t WHERE t.id > 0";
                        if (paramConsulta.getParametroConsulta2() != null) {
                            strQuery += "AND t.estado = " + paramConsulta.getParametroConsulta2() + " ";
                        }
                        if (paramConsulta.getParametroConsulta3() != null) {
                            strQuery += " AND t.estadoJuntaProfesionales = " + paramConsulta.getParametroConsulta3() + " ";
                        }
                        if (paramConsulta.getParametroConsulta4() != null) {
                            strQuery += " AND t.maeTecnologiaValor LIKE '%" + paramConsulta.getParametroConsulta4() + "%'";
                        }
                        break;
                    case "3":
                        prescripcion = "i.mpPrescripcionId";
                        strQuery = "SELECT COUNT(" + prescripcion + ") FROM MpPrescripcionInsumos i WHERE i.id > 0";
                        if (paramConsulta.getParametroConsulta2() != null) {
                            strQuery += "AND i.estado = " + paramConsulta.getParametroConsulta2() + " ";
                        }
                        if (paramConsulta.getParametroConsulta3() != null) {
                            strQuery += " AND i.estadoJuntaProfesionales = " + paramConsulta.getParametroConsulta3() + " ";
                        }
                        if (paramConsulta.getParametroConsulta4() != null) {
                            strQuery += " AND i.maeDispositivosValor LIKE '%" + paramConsulta.getParametroConsulta4() + "%'";
                        }
                        break;
                    case "5":
                        prescripcion = "i.mpPrescripcionId";
                        strQuery = "SELECT COUNT(" + prescripcion + ") FROM MpPrescripcionInsumos i WHERE i.id > 0";
                        if (paramConsulta.getParametroConsulta2() != null) {
                            strQuery += "AND i.estado = " + paramConsulta.getParametroConsulta2() + " ";
                        }
                        if (paramConsulta.getParametroConsulta3() != null) {
                            strQuery += " AND i.estadoJuntaProfesionales = " + paramConsulta.getParametroConsulta3() + " ";
                        }
                        if (paramConsulta.getParametroConsulta4() != null) {
                            strQuery += " AND i.maeServiciosComplementariosValor LIKE '%" + paramConsulta.getParametroConsulta4() + "%'";
                        }
                        break;
                    default:
                        break;
                }
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry<String, Object> entry : paramConsulta.getFiltros().entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (value != null) {
                        switch (key) {
                            case "numeroPrescripcion":
                                strQuery += " AND " + prescripcion + ".numeroPrescripcion LIKE '%" + value + "%'";
                                break;
                            case "estadoPrescripcion":
                                strQuery += (" AND " + prescripcion + ".estado = " + value + "");
                                break;
                            case "codigoAmbitoAtencion":
                                if ("1".equals(value)) {
                                    strQuery += " AND (" + prescripcion + ".codAmbAte IS NULL OR " + prescripcion + ".codAmbAte = '')";
                                } else {
                                    strQuery += " AND " + prescripcion + ".codAmbAte = '" + value + "'";
                                }
                                break;
                            case "primerNombreAfiliado":
                                strQuery += " AND (" + prescripcion + ".asegAfiliadosId.primerNombre LIKE '%" + value + "%' OR " + prescripcion + ".asegAfiliadosId.segundoNombre LIKE '%" + value + "%')";
                                break;
                            case "primerApellidoAfiliado":
                                strQuery += " AND (" + prescripcion + ".asegAfiliadosId.primerApellido LIKE '%" + value + "%' OR " + prescripcion + ".asegAfiliadosId.segundoApellido LIKE '%" + value + "%')";
                                break;
                            case "numeroDocumentoAfiliado":
                                strQuery += " AND " + prescripcion + ".asegAfiliadosId.numeroDocumento LIKE '%" + value + "%'";
                                break;
                            case "fechaPrescripcion":
                                strQuery += " AND " + prescripcion + ".fechaPrescripcion = '" + value + " 00:00:00' ";
                                break;
                            case "tipoTecnologiaItem":
                                strQuery += " AND (";
                                String tipoTecnologia = (String) value;
                                switch (tipoTecnologia) {
                                    case "1":
                                        strQuery += " m.tipoTecnologia = 1";
                                        break;
                                    case "4":
                                        strQuery += " m.tipoTecnologia = 4";
                                        break;
                                    case "2":
                                        strQuery += " t.tipoTecnologia = 2";
                                        break;
                                    case "3":
                                        strQuery += " i.tipoTecnologia = 3";
                                        break;
                                    case "5":
                                        strQuery += " i.tipoTecnologia = 5";
                                        break;
                                    default:
                                        break;
                                }
                                strQuery += ")";
                                break;
                            default:
                                break;
                        }
                    }
                }
            }

            Query query = getEntityManager().createQuery(strQuery);
            int cant = ((Number) query.getSingleResult()).intValue();
            cerrarEntityManager();
            return cant;

        } catch (NoResultException e) {
            return 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
            return 0;
        }
    }

    @Override
    public List<MpPrescripcionDetalle> consultarLista(ParamConsulta paramConsulta) throws Exception {

        List<MpPrescripcionDetalle> listResult = new ArrayList<>();
        try {

            String strQuery = " FROM MpPrescripciones  p "
                    + " WHERE p.id > 0 ";
            String p = "p";
            if (paramConsulta.getParametroConsulta1() != null) {

                switch (paramConsulta.getParametroConsulta1().toString()) {
                    case "1":
                        p = "m.mpPrescripcionesId";
                        strQuery = " SELECT DISTINCT " + p + " FROM MpPrescripcionMedicamentos m WHERE m.id > 0";
                        if (paramConsulta.getParametroConsulta2() != null) {
                            strQuery += " AND m.estado = " + paramConsulta.getParametroConsulta2() + " ";
                        }
                        if (paramConsulta.getParametroConsulta3() != null) {
                            strQuery += " AND m.estadoJuntaProfesionales = " + paramConsulta.getParametroConsulta3() + " ";
                        }
                        if (paramConsulta.getParametroConsulta4() != null) {
                            strQuery += " AND m.descripcionMedicamentoPrincipioActivo LIKE '%" + paramConsulta.getParametroConsulta4() + "%'";
                        }
                        break;
                    case "4":
                        p = "m.mpPrescripcionesId";
                        strQuery = " SELECT DISTINCT " + p + " FROM MpPrescripcionMedicamentos m WHERE m.id > 0";
                        if (paramConsulta.getParametroConsulta2() != null) {
                            strQuery += " AND m.estado = " + paramConsulta.getParametroConsulta2() + " ";
                        }
                        if (paramConsulta.getParametroConsulta3() != null) {
                            strQuery += " AND m.estadoJuntaProfesionales = " + paramConsulta.getParametroConsulta3() + " ";
                        }
                        if (paramConsulta.getParametroConsulta4() != null) {
                            strQuery += " AND m.maeProductosNutricionalesValor LIKE '%" + paramConsulta.getParametroConsulta4() + "%'";
                        }
                        break;
                    case "2":
                        p = "t.mpPrescripcionId";
                        strQuery = "SELECT DISTINCT " + p + " FROM MpPrescripcionTecnologias t WHERE t.id > 0";
                        if (paramConsulta.getParametroConsulta2() != null) {
                            strQuery += " AND t.estado = " + paramConsulta.getParametroConsulta2() + " ";
                        }
                        if (paramConsulta.getParametroConsulta3() != null) {
                            strQuery += " AND t.estadoJuntaProfesionales = " + paramConsulta.getParametroConsulta3() + " ";
                        }
                        if (paramConsulta.getParametroConsulta4() != null) {
                            strQuery += " AND t.maeTecnologiaValor LIKE '%" + paramConsulta.getParametroConsulta4() + "%'";
                        }
                        break;
                    case "3":
                        p = "i.mpPrescripcionId";
                        strQuery = "SELECT DISTINCT " + p + " FROM MpPrescripcionInsumos i WHERE i.id > 0";
                        if (paramConsulta.getParametroConsulta2() != null) {
                            strQuery += " AND i.estado = " + paramConsulta.getParametroConsulta2() + " ";
                        }
                        if (paramConsulta.getParametroConsulta3() != null) {
                            strQuery += " AND i.estadoJuntaProfesionales = " + paramConsulta.getParametroConsulta3() + " ";
                        }
                        if (paramConsulta.getParametroConsulta4() != null) {
                            strQuery += " AND i.maeDispositivosValor LIKE '%" + paramConsulta.getParametroConsulta4() + "%'";
                        }
                        break;
                    case "5":
                        p = "i.mpPrescripcionId";
                        strQuery = "SELECT DISTINCT " + p + " FROM MpPrescripcionInsumos i WHERE i.id > 0";
                        if (paramConsulta.getParametroConsulta2() != null) {
                            strQuery += " AND i.estado = " + paramConsulta.getParametroConsulta2() + " ";
                        }
                        if (paramConsulta.getParametroConsulta3() != null) {
                            strQuery += " AND i.estadoJuntaProfesionales = " + paramConsulta.getParametroConsulta3() + " ";
                        }
                        if (paramConsulta.getParametroConsulta4() != null) {
                            strQuery += " AND i.maeServiciosComplementariosValor LIKE '%" + paramConsulta.getParametroConsulta4() + "%'";
                        }
                        break;
                    default:
                        break;
                }
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry<String, Object> e : paramConsulta.getFiltros().entrySet()) {
                    switch (e.getKey()) {
                        case "numeroPrescripcion":
                            strQuery += (" AND " + p + ".numeroPrescripcion LIKE '%") + (String) e.getValue() + ("%' ");
                            break;
                        case "estadoPrescripcion":
                            strQuery += (" AND " + p + ".estado = ") + (String) e.getValue() + (" ");
                            break;
                        case "codigoAmbitoAtencion":
                            if ("1".equals((String) e.getValue())) {
                                strQuery += " AND (" + p + ".codAmbAte IS NULL OR " + p + ".codAmbAte = '') ";
                            } else {
                                strQuery += " AND " + p + ".codAmbAte = '" + (String) e.getValue() + "' ";
                            }
                            break;
                        case "primerNombreAfiliado":
                            strQuery += "AND (" + p + ".asegAfiliadosId.primerNombre LIKE '%" + (String) e.getValue() + "%' "
                                    + "OR " + p + ".asegAfiliadosId.segundoNombre LIKE '%" + (String) e.getValue() + "%') ";
                            break;
                        case "primerApellidoAfiliado":
                            strQuery += "AND (" + p + ".asegAfiliadosId.primerApellido LIKE '%" + (String) e.getValue() + "%' "
                                    + "OR " + p + ".asegAfiliadosId.segundoApellido LIKE '%" + (String) e.getValue() + "%') ";
                            break;

                        case "numeroDocumentoAfiliado":
                            strQuery += ("AND " + p + ".asegAfiliadosId.numeroDocumento LIKE '%") + (String) e.getValue() + ("%' ");
                            break;
                        case "fechaPrescripcion":
                            strQuery += ("AND " + p + ".fechaPrescripcion = '" + e.getValue() + " 00:00:00'");
                            break;
                        case "tipoTecnologiaItem":
                            strQuery += " AND (";
                            String tipoTecnologia = (String) e.getValue();
                            switch (tipoTecnologia) {
                                case "1":
                                    strQuery += "m.tipoTecnologia = 1";
                                    break;
                                case "4":
                                    strQuery += "m.tipoTecnologia = 4";
                                    break;
                                case "2":
                                    strQuery += "t.tipoTecnologia = 2";
                                    break;
                                case "3":
                                    strQuery += "i.tipoTecnologia = 3";
                                    break;
                                case "5":
                                    strQuery += "i.tipoTecnologia = 5";
                                    break;
                                default:
                                    break;
                            }
                            strQuery += ")";
                            break;

                        default:
                            break;
                    }
                }
            }
            strQuery += " ORDER BY " + p + ".fechaPrescripcion DESC, " + p + ".horaPrescripcion ASC";

            List<MpPrescripciones> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
//            if (paramConsulta.getFiltros().get("numeroPrescripcion") == null || paramConsulta.getFiltros().get("tipoTecnologiaItem") == null) {
            for (MpPrescripciones per : list) {
                if (per != null) {

                    try {
                        List<MpPrescripcionDetalle> detalles = castEntidadNegocioPres(per);
                        listResult.addAll(detalles);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
//            } else {
//                boolean bucleEjecutado = false;
//                for (MpPrescripciones per : list) {
//                    if (per != null) {
//                        if (bucleEjecutado == false) {
//                            try {
//                                List<MpPrescripcionDetalle> detalles = castEntidadNegocioPres(per);
//                                listResult.addAll(detalles);
//                                bucleEjecutado = true;
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }
//            }

        } catch (NoResultException e) {
            listResult = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return listResult;

    }

    @Override
    public MpPrescripcionDetalle consultarPrescripcionDetalle(Integer tipoTec, Integer id) throws Exception {
        MpPrescripcionDetalle result = null;
        try {
            String strQuery = " ";
            String p = " ";
            switch (tipoTec) {
                case 1:
                case 4:
                    p = "m.mpPrescripcionesId";
                    strQuery = " SELECT DISTINCT " + p + " FROM MpPrescripcionMedicamentos m WHERE m.id = :id";
                    break;
                case 2:
                    p = "t.mpPrescripcionId";
                    strQuery = " SELECT DISTINCT " + p + " FROM MpPrescripcionTecnologias t WHERE t.id = :id";
                    break;
                case 3:
                case 5:
                    p = "i.mpPrescripcionId";
                    strQuery = "SELECT DISTINCT " + p + " FROM MpPrescripcionInsumos i WHERE i.id = :id";
                    break;
                default:
                    break;
            }
            TypedQuery<MpPrescripciones> query = getEntityManager().createQuery(strQuery, MpPrescripciones.class);
            query.setParameter("id", id);
            MpPrescripciones obj = query.getSingleResult();
            result = castEntidadNegocioPrescripcionDetalle(obj, id);

        } catch (NoResultException e) {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return result;

    }

    public MpPrescripcionDetalle castEntidadNegocioPrescripcionDetalle(MpPrescripciones result, int id) {
        MpPrescripcionDetalle entidadCombinada = new MpPrescripcionDetalle();

        entidadCombinada.setIdPrescripcion(result.getId());
        entidadCombinada.setNumeroPrescripcion(result.getNumeroPrescripcion());
        entidadCombinada.setEstadoPrescripcion(result.getEstado());
        entidadCombinada.setCodigoAmbitoAtencion(result.getCodAmbAte());
        entidadCombinada.setReferenciaContra(result.getReferenciaAmbitoAtencion());
        entidadCombinada.setFechaPrescripcion(result.getFechaPrescripcion());
        entidadCombinada.setHoraPrescripcion(result.getHoraPrescripcion());
        entidadCombinada.setDocumentoPrestador(result.getPrestadorNumeroDocumento());

        if (result.getMpPrescripcionMedicamentosList() != null && !result.getMpPrescripcionMedicamentosList().isEmpty()) {
            for (MpPrescripcionMedicamentos med : result.getMpPrescripcionMedicamentosList()) {
                if (med.getId() == id) {
                    MpPrescripcionMedicamentos medicamento = med;
                    entidadCombinada.setIdItem(medicamento.getId());
                    entidadCombinada.setJuntaP(medicamento.getEstadoJuntaProfesionales());
                    entidadCombinada.setTipoTecnologiaItem(medicamento.getTipoTecnologia());
                    entidadCombinada.setConsecutivoItem(medicamento.getConsecutivoOrden());
                    switch (medicamento.getTipoTecnologia()) {
                        case 1:
                            entidadCombinada.setNombreItem(medicamento.getDescripcionMedicamentoPrincipioActivo());
                            break;
                        case 4:
                            entidadCombinada.setNombreItem(medicamento.getMaeProductosNutricionalesValor());
                            break;
                        default:
                            break;
                    }
                    entidadCombinada.setEstadoItem(medicamento.getEstado());
                    entidadCombinada.setUsuarioAtiende(medicamento.getUsuarioAtiende());
                    entidadCombinada.setTerminalAtiende(medicamento.getTerminalAtiende());
                    entidadCombinada.setFechaHoraAtiende(medicamento.getFechaHoraAtiende());
                    entidadCombinada.setAtendido(medicamento.getAtendido());
                    entidadCombinada.setBanderaAtencion(medicamento.getBanderaAtencion());
                    if (result.getAsegAfiliadosId() != null) {
                        entidadCombinada.setIdAfiliado(result.getAsegAfiliadosId() != null ? result.getAsegAfiliadosId().getId() : null);
                        entidadCombinada.setPrimerNombreAfiliado(result.getAsegAfiliadosId().getPrimerNombre() != null ? result.getAsegAfiliadosId().getPrimerNombre() : null);
                        entidadCombinada.setPrimerApellidoAfiliado(result.getAsegAfiliadosId().getPrimerApellido() != null ? result.getAsegAfiliadosId().getPrimerApellido() : null);
                        entidadCombinada.setSegundoNombreAfiliado(result.getAsegAfiliadosId().getSegundoNombre() != null ? result.getAsegAfiliadosId().getSegundoNombre() : null);
                        entidadCombinada.setSegundoApellidoAfiliado(result.getAsegAfiliadosId().getSegundoApellido() != null ? result.getAsegAfiliadosId().getSegundoApellido() : null);
                        entidadCombinada.setNumeroDocumentoAfiliado(result.getAsegAfiliadosId().getNumeroDocumento() != null ? result.getAsegAfiliadosId().getNumeroDocumento() : null);
                        entidadCombinada.setRegimenAfiliado(result.getAsegAfiliadosId().getRegimen() != null ? result.getAsegAfiliadosId().getRegimen() : null);
                        entidadCombinada.setTipoDocumentoAfiliado(result.getAsegAfiliadosId().getMaeTipoDocumentoValor() != null ? result.getAsegAfiliadosId().getMaeTipoDocumentoValor() : null);
                        entidadCombinada.setMaeTipoDocumentoAfiliadoCodigo(result.getAsegAfiliadosId().getMaeTipoDocumentoCodigo() != null ? result.getAsegAfiliadosId().getMaeTipoDocumentoCodigo() : null);
                        entidadCombinada.setMaeTipoDocumentoAfiliadoId(result.getAsegAfiliadosId().getMaeTipoDocumentoId());
                        entidadCombinada.setAfiliacionUbicacion(castUbicacionEntidadNegocio(result.getAsegAfiliadosId().getAfiliacionUbicacionesId()));
                    }
                    if (result.getMpAfiliadosId() != null) {
                        entidadCombinada.setNumeroDocumentoAfiliadoMp(result.getMpAfiliadosId().getNumeroDocumento() != null ? result.getMpAfiliadosId().getNumeroDocumento() : null);
                        entidadCombinada.setTipoDocumentoAfiliadoMp(result.getMpAfiliadosId().getMaeTipoDocumentoValor() != null ? result.getMpAfiliadosId().getMaeTipoDocumentoValor() : null);
                        entidadCombinada.setMaeTipoDocumentoAfiliadoCodigoMp(result.getMpAfiliadosId().getMaeTipoDocumentoCodigo() != null ? result.getMpAfiliadosId().getMaeTipoDocumentoCodigo() : null);
                        entidadCombinada.setMaeTipoDocumentoAfiliadoIdMp(result.getMpAfiliadosId().getMaeTipoDocumentoId());
                    }
                }
            }
        }

        if (result.getMpPrescripcionTecnologiasList() != null && !result.getMpPrescripcionTecnologiasList().isEmpty()) {
            for (MpPrescripcionTecnologias tec : result.getMpPrescripcionTecnologiasList()) {
                if (tec.getId() == id) {
                    MpPrescripcionTecnologias tecnologia = tec;
                    entidadCombinada.setIdItem(tecnologia.getId());
                    entidadCombinada.setJuntaP(tecnologia.getEstadoJuntaProfesionales());
                    entidadCombinada.setTipoTecnologiaItem(tecnologia.getTipoTecnologia());
                    entidadCombinada.setConsecutivoItem(tecnologia.getConsecutivoOrden());
                    entidadCombinada.setNombreItem(tecnologia.getMaTecnologiaValor());
                    entidadCombinada.setEstadoItem(tecnologia.getEstado());
                    entidadCombinada.setUsuarioAtiende(tecnologia.getUsuarioAtiende());
                    entidadCombinada.setTerminalAtiende(tecnologia.getTerminalAtiende());
                    entidadCombinada.setFechaHoraAtiende(tecnologia.getFechaHoraAtiende());
                    entidadCombinada.setAtendido(tecnologia.getAtendido());
                    entidadCombinada.setBanderaAtencion(tecnologia.getBanderaAtencion());
                    if (result.getAsegAfiliadosId() != null) {
                        entidadCombinada.setIdAfiliado(result.getAsegAfiliadosId() != null ? result.getAsegAfiliadosId().getId() : null);
                        entidadCombinada.setPrimerNombreAfiliado(result.getAsegAfiliadosId().getPrimerNombre() != null ? result.getAsegAfiliadosId().getPrimerNombre() : null);
                        entidadCombinada.setPrimerApellidoAfiliado(result.getAsegAfiliadosId().getPrimerApellido() != null ? result.getAsegAfiliadosId().getPrimerApellido() : null);
                        entidadCombinada.setSegundoNombreAfiliado(result.getAsegAfiliadosId().getSegundoNombre() != null ? result.getAsegAfiliadosId().getSegundoNombre() : null);
                        entidadCombinada.setSegundoApellidoAfiliado(result.getAsegAfiliadosId().getSegundoApellido() != null ? result.getAsegAfiliadosId().getSegundoApellido() : null);
                        entidadCombinada.setNumeroDocumentoAfiliado(result.getAsegAfiliadosId().getNumeroDocumento() != null ? result.getAsegAfiliadosId().getNumeroDocumento() : null);
                        entidadCombinada.setRegimenAfiliado(result.getAsegAfiliadosId().getRegimen() != null ? result.getAsegAfiliadosId().getRegimen() : null);
                        entidadCombinada.setTipoDocumentoAfiliado(result.getAsegAfiliadosId().getMaeTipoDocumentoValor() != null ? result.getAsegAfiliadosId().getMaeTipoDocumentoValor() : null);
                        entidadCombinada.setMaeTipoDocumentoAfiliadoCodigo(result.getAsegAfiliadosId().getMaeTipoDocumentoCodigo() != null ? result.getAsegAfiliadosId().getMaeTipoDocumentoCodigo() : null);
                        entidadCombinada.setMaeTipoDocumentoAfiliadoId(result.getAsegAfiliadosId().getMaeTipoDocumentoId());
                        entidadCombinada.setAfiliacionUbicacion(castUbicacionEntidadNegocio(result.getAsegAfiliadosId().getAfiliacionUbicacionesId()));
                    }
                    if (result.getMpAfiliadosId() != null) {
                        entidadCombinada.setNumeroDocumentoAfiliadoMp(result.getMpAfiliadosId().getNumeroDocumento() != null ? result.getMpAfiliadosId().getNumeroDocumento() : null);
                        entidadCombinada.setTipoDocumentoAfiliadoMp(result.getMpAfiliadosId().getMaeTipoDocumentoValor() != null ? result.getMpAfiliadosId().getMaeTipoDocumentoValor() : null);
                        entidadCombinada.setMaeTipoDocumentoAfiliadoCodigoMp(result.getMpAfiliadosId().getMaeTipoDocumentoCodigo() != null ? result.getMpAfiliadosId().getMaeTipoDocumentoCodigo() : null);
                        entidadCombinada.setMaeTipoDocumentoAfiliadoIdMp(result.getMpAfiliadosId().getMaeTipoDocumentoId());
                    }
                }
            }
        }

        if (result.getMpPrescripcionInsumosList() != null && !result.getMpPrescripcionInsumosList().isEmpty()) {
            for (MpPrescripcionInsumos ins : result.getMpPrescripcionInsumosList()) {
                if (ins.getId() == id) {
                    MpPrescripcionInsumos insumo = ins;
                    entidadCombinada.setIdItem(insumo.getId());
                    entidadCombinada.setJuntaP(insumo.getEstadoJuntaProfesionales());
                    entidadCombinada.setTipoTecnologiaItem(insumo.getTipoTecnologia());
                    entidadCombinada.setConsecutivoItem(insumo.getConsecutivoOrden());
                    switch (insumo.getTipoTecnologia()) {
                        case 3:
                            entidadCombinada.setNombreItem(insumo.getMaedispositivosValor());
                            break;
                        case 5:
                            entidadCombinada.setNombreItem(insumo.getMaeServiciosComplementariosValor());
                            break;
                        default:
                            break;
                    }
                    entidadCombinada.setEstadoItem(insumo.getEstado());
                    entidadCombinada.setUsuarioAtiende(insumo.getUsuarioAtiende());
                    entidadCombinada.setTerminalAtiende(insumo.getTerminalAtiende());
                    entidadCombinada.setFechaHoraAtiende(insumo.getFechaHoraAtiende());
                    entidadCombinada.setAtendido(insumo.getAtendido());
                    entidadCombinada.setBanderaAtencion(insumo.getBanderaAtencion());
                    if (result.getAsegAfiliadosId() != null) {
                        entidadCombinada.setIdAfiliado(result.getAsegAfiliadosId() != null ? result.getAsegAfiliadosId().getId() : null);
                        entidadCombinada.setPrimerNombreAfiliado(result.getAsegAfiliadosId().getPrimerNombre() != null ? result.getAsegAfiliadosId().getPrimerNombre() : null);
                        entidadCombinada.setPrimerApellidoAfiliado(result.getAsegAfiliadosId().getPrimerApellido() != null ? result.getAsegAfiliadosId().getPrimerApellido() : null);
                        entidadCombinada.setSegundoNombreAfiliado(result.getAsegAfiliadosId().getSegundoNombre() != null ? result.getAsegAfiliadosId().getSegundoNombre() : null);
                        entidadCombinada.setSegundoApellidoAfiliado(result.getAsegAfiliadosId().getSegundoApellido() != null ? result.getAsegAfiliadosId().getSegundoApellido() : null);
                        entidadCombinada.setNumeroDocumentoAfiliado(result.getAsegAfiliadosId().getNumeroDocumento() != null ? result.getAsegAfiliadosId().getNumeroDocumento() : null);
                        entidadCombinada.setRegimenAfiliado(result.getAsegAfiliadosId().getRegimen() != null ? result.getAsegAfiliadosId().getRegimen() : null);
                        entidadCombinada.setTipoDocumentoAfiliado(result.getAsegAfiliadosId().getMaeTipoDocumentoValor() != null ? result.getAsegAfiliadosId().getMaeTipoDocumentoValor() : null);
                        entidadCombinada.setMaeTipoDocumentoAfiliadoCodigo(result.getAsegAfiliadosId().getMaeTipoDocumentoCodigo() != null ? result.getAsegAfiliadosId().getMaeTipoDocumentoCodigo() : null);
                        entidadCombinada.setMaeTipoDocumentoAfiliadoId(result.getAsegAfiliadosId().getMaeTipoDocumentoId());
                        entidadCombinada.setAfiliacionUbicacion(castUbicacionEntidadNegocio(result.getAsegAfiliadosId().getAfiliacionUbicacionesId()));
                    }
                    if (result.getMpAfiliadosId() != null) {
                        entidadCombinada.setNumeroDocumentoAfiliadoMp(result.getMpAfiliadosId().getNumeroDocumento() != null ? result.getMpAfiliadosId().getNumeroDocumento() : null);
                        entidadCombinada.setTipoDocumentoAfiliadoMp(result.getMpAfiliadosId().getMaeTipoDocumentoValor() != null ? result.getMpAfiliadosId().getMaeTipoDocumentoValor() : null);
                        entidadCombinada.setMaeTipoDocumentoAfiliadoCodigoMp(result.getMpAfiliadosId().getMaeTipoDocumentoCodigo() != null ? result.getMpAfiliadosId().getMaeTipoDocumentoCodigo() : null);
                        entidadCombinada.setMaeTipoDocumentoAfiliadoIdMp(result.getMpAfiliadosId().getMaeTipoDocumentoId());
                    }
                }
            }
        }

        return entidadCombinada;
    }

    public List<MpPrescripcionDetalle> castEntidadNegocioPres(MpPrescripciones result) {
        List<MpPrescripcionDetalle> listResult = new ArrayList<>();
        if (result.getMpPrescripcionMedicamentosList() != null) {
            for (MpPrescripcionMedicamentos listamed : result.getMpPrescripcionMedicamentosList()) {
                MpPrescripcionDetalle entidadCombinada = new MpPrescripcionDetalle();

                entidadCombinada.setIdPrescripcion(result.getId());
                entidadCombinada.setNumeroPrescripcion(result.getNumeroPrescripcion());
                entidadCombinada.setEstadoPrescripcion(result.getEstado());
                entidadCombinada.setCodigoAmbitoAtencion(result.getCodAmbAte());
                entidadCombinada.setReferenciaContra(result.getReferenciaAmbitoAtencion());
                entidadCombinada.setFechaPrescripcion(result.getFechaPrescripcion());
                entidadCombinada.setHoraPrescripcion(result.getHoraPrescripcion());
                entidadCombinada.setDocumentoPrestador(result.getPrestadorNumeroDocumento());

                entidadCombinada.setIdItem(listamed.getId());
                entidadCombinada.setJuntaP(listamed.getEstadoJuntaProfesionales());
                entidadCombinada.setTipoTecnologiaItem(listamed.getTipoTecnologia());
                entidadCombinada.setConsecutivoItem(listamed.getConsecutivoOrden());
                switch (listamed.getTipoTecnologia()) {
                    case 1:
                        entidadCombinada.setNombreItem(listamed.getDescripcionMedicamentoPrincipioActivo());
                        break;
                    case 4:
                        entidadCombinada.setNombreItem(listamed.getMaeProductosNutricionalesValor());
                        break;

                    default:
                        break;
                }
                entidadCombinada.setEstadoItem(listamed.getEstado());
                entidadCombinada.setUsuarioAtiende(listamed.getUsuarioAtiende());
                entidadCombinada.setTerminalAtiende(listamed.getTerminalAtiende());
                entidadCombinada.setFechaHoraAtiende(listamed.getFechaHoraAtiende());
                entidadCombinada.setAtendido(listamed.getAtendido());

                if (result.getAsegAfiliadosId() != null) {
                    entidadCombinada.setIdAfiliado(result.getAsegAfiliadosId() != null ? result.getAsegAfiliadosId().getId() : null);
                    entidadCombinada.setPrimerNombreAfiliado(result.getAsegAfiliadosId().getPrimerNombre() != null ? result.getAsegAfiliadosId().getPrimerNombre() : null);
                    entidadCombinada.setPrimerApellidoAfiliado(result.getAsegAfiliadosId().getPrimerApellido() != null ? result.getAsegAfiliadosId().getPrimerApellido() : null);
                    entidadCombinada.setSegundoNombreAfiliado(result.getAsegAfiliadosId().getSegundoNombre() != null ? result.getAsegAfiliadosId().getSegundoNombre() : null);
                    entidadCombinada.setSegundoApellidoAfiliado(result.getAsegAfiliadosId().getSegundoApellido() != null ? result.getAsegAfiliadosId().getSegundoApellido() : null);
                    entidadCombinada.setNumeroDocumentoAfiliado(result.getAsegAfiliadosId().getNumeroDocumento() != null ? result.getAsegAfiliadosId().getNumeroDocumento() : null);
                    entidadCombinada.setRegimenAfiliado(result.getAsegAfiliadosId().getRegimen() != null ? result.getAsegAfiliadosId().getRegimen() : null);
                    entidadCombinada.setTipoDocumentoAfiliado(result.getAsegAfiliadosId().getMaeTipoDocumentoValor() != null ? result.getAsegAfiliadosId().getMaeTipoDocumentoValor() : null);
                    entidadCombinada.setMaeTipoDocumentoAfiliadoCodigo(result.getAsegAfiliadosId().getMaeTipoDocumentoCodigo() != null ? result.getAsegAfiliadosId().getMaeTipoDocumentoCodigo() : null);
                    entidadCombinada.setMaeTipoDocumentoAfiliadoId(result.getAsegAfiliadosId().getMaeTipoDocumentoId());
                    entidadCombinada.setAfiliacionUbicacion(castUbicacionEntidadNegocio(result.getAsegAfiliadosId().getAfiliacionUbicacionesId()));
                }

                if (result.getMpAfiliadosId() != null) {
                    entidadCombinada.setNumeroDocumentoAfiliadoMp(result.getMpAfiliadosId().getNumeroDocumento() != null ? result.getMpAfiliadosId().getNumeroDocumento() : null);
                    entidadCombinada.setTipoDocumentoAfiliadoMp(result.getMpAfiliadosId().getMaeTipoDocumentoValor() != null ? result.getMpAfiliadosId().getMaeTipoDocumentoValor() : null);
                    entidadCombinada.setMaeTipoDocumentoAfiliadoCodigoMp(result.getMpAfiliadosId().getMaeTipoDocumentoCodigo() != null ? result.getMpAfiliadosId().getMaeTipoDocumentoCodigo() : null);
                    entidadCombinada.setMaeTipoDocumentoAfiliadoIdMp(result.getMpAfiliadosId().getMaeTipoDocumentoId());
                }

                if (result.getCntProfesionalesId() != null) {
                    entidadCombinada.setPrimerNombrePrestador(result.getCntProfesionalesId().getPrimerNombre());
                    entidadCombinada.setPrimerApellidoPrestador(result.getCntProfesionalesId().getPrimerApellido());
                    entidadCombinada.setSegundoNombrePrestador(result.getCntProfesionalesId().getSegundoNombre());
                    entidadCombinada.setSegundoApellidoPrestador(result.getCntProfesionalesId().getSegundoApellido());
                    entidadCombinada.setNumeroDocumentoPrestador(result.getCntProfesionalesId().getDocumento());
                }
                listResult.add(entidadCombinada);

            }

        }
        if (result.getMpPrescripcionTecnologiasList() != null) {
            for (MpPrescripcionTecnologias listamed : result.getMpPrescripcionTecnologiasList()) {
                MpPrescripcionDetalle entidadCombinada = new MpPrescripcionDetalle();

                entidadCombinada.setIdPrescripcion(result.getId());
                entidadCombinada.setNumeroPrescripcion(result.getNumeroPrescripcion());
                entidadCombinada.setEstadoPrescripcion(result.getEstado());
                entidadCombinada.setCodigoAmbitoAtencion(result.getCodAmbAte());
                entidadCombinada.setReferenciaContra(result.getReferenciaAmbitoAtencion());
                entidadCombinada.setFechaPrescripcion(result.getFechaPrescripcion());
                entidadCombinada.setHoraPrescripcion(result.getHoraPrescripcion());
                entidadCombinada.setDocumentoPrestador(result.getPrestadorNumeroDocumento());

                entidadCombinada.setIdItem(listamed.getId());
                entidadCombinada.setJuntaP(listamed.getEstadoJuntaProfesionales());
                entidadCombinada.setTipoTecnologiaItem(listamed.getTipoTecnologia());
                entidadCombinada.setConsecutivoItem(listamed.getConsecutivoOrden());
                entidadCombinada.setNombreItem(listamed.getMaTecnologiaValor());
                entidadCombinada.setEstadoItem(listamed.getEstado());
                entidadCombinada.setEstadoItem(listamed.getEstado());
                entidadCombinada.setUsuarioAtiende(listamed.getUsuarioAtiende());
                entidadCombinada.setTerminalAtiende(listamed.getTerminalAtiende());
                entidadCombinada.setFechaHoraAtiende(listamed.getFechaHoraAtiende());
                entidadCombinada.setAtendido(listamed.getAtendido());

                if (result.getAsegAfiliadosId() != null) {
                    entidadCombinada.setIdAfiliado(result.getAsegAfiliadosId() != null ? result.getAsegAfiliadosId().getId() : null);
                    entidadCombinada.setPrimerNombreAfiliado(result.getAsegAfiliadosId().getPrimerNombre() != null ? result.getAsegAfiliadosId().getPrimerNombre() : null);
                    entidadCombinada.setPrimerApellidoAfiliado(result.getAsegAfiliadosId().getPrimerApellido() != null ? result.getAsegAfiliadosId().getPrimerApellido() : null);
                    entidadCombinada.setSegundoNombreAfiliado(result.getAsegAfiliadosId().getSegundoNombre() != null ? result.getAsegAfiliadosId().getSegundoNombre() : null);
                    entidadCombinada.setSegundoApellidoAfiliado(result.getAsegAfiliadosId().getSegundoApellido() != null ? result.getAsegAfiliadosId().getSegundoApellido() : null);
                    entidadCombinada.setNumeroDocumentoAfiliado(result.getAsegAfiliadosId().getNumeroDocumento() != null ? result.getAsegAfiliadosId().getNumeroDocumento() : null);
                    entidadCombinada.setRegimenAfiliado(result.getAsegAfiliadosId().getRegimen() != null ? result.getAsegAfiliadosId().getRegimen() : null);
                    entidadCombinada.setTipoDocumentoAfiliado(result.getAsegAfiliadosId().getMaeTipoDocumentoValor() != null ? result.getAsegAfiliadosId().getMaeTipoDocumentoValor() : null);
                    entidadCombinada.setMaeTipoDocumentoAfiliadoCodigo(result.getAsegAfiliadosId().getMaeTipoDocumentoCodigo() != null ? result.getAsegAfiliadosId().getMaeTipoDocumentoCodigo() : null);
                    entidadCombinada.setMaeTipoDocumentoAfiliadoId(result.getAsegAfiliadosId().getMaeTipoDocumentoId());
                    entidadCombinada.setAfiliacionUbicacion(castUbicacionEntidadNegocio(result.getAsegAfiliadosId().getAfiliacionUbicacionesId()));
                }
                if (result.getCntProfesionalesId() != null) {
                    entidadCombinada.setPrimerNombrePrestador(result.getCntProfesionalesId().getPrimerNombre());
                    entidadCombinada.setPrimerApellidoPrestador(result.getCntProfesionalesId().getPrimerApellido());
                    entidadCombinada.setSegundoNombrePrestador(result.getCntProfesionalesId().getSegundoNombre());
                    entidadCombinada.setSegundoApellidoPrestador(result.getCntProfesionalesId().getSegundoApellido());
                    entidadCombinada.setNumeroDocumentoPrestador(result.getCntProfesionalesId().getDocumento());
                }
                listResult.add(entidadCombinada);

            }
        }
        if (result.getMpPrescripcionInsumosList() != null) {
            for (MpPrescripcionInsumos listamed : result.getMpPrescripcionInsumosList()) {
                MpPrescripcionDetalle entidadCombinada = new MpPrescripcionDetalle();

                entidadCombinada.setIdPrescripcion(result.getId());
                entidadCombinada.setNumeroPrescripcion(result.getNumeroPrescripcion());
                entidadCombinada.setEstadoPrescripcion(result.getEstado());
                entidadCombinada.setCodigoAmbitoAtencion(result.getCodAmbAte());
                entidadCombinada.setReferenciaContra(result.getReferenciaAmbitoAtencion());
                entidadCombinada.setFechaPrescripcion(result.getFechaPrescripcion());
                entidadCombinada.setHoraPrescripcion(result.getHoraPrescripcion());
                entidadCombinada.setDocumentoPrestador(result.getPrestadorNumeroDocumento());

                entidadCombinada.setIdItem(listamed.getId());
                entidadCombinada.setJuntaP(listamed.getEstadoJuntaProfesionales());
                entidadCombinada.setTipoTecnologiaItem(listamed.getTipoTecnologia());
                entidadCombinada.setConsecutivoItem(listamed.getConsecutivoOrden());
                switch (listamed.getTipoTecnologia()) {
                    case 3:
                        entidadCombinada.setNombreItem(listamed.getMaedispositivosValor());
                        break;
                    case 5:
                        entidadCombinada.setNombreItem(listamed.getMaeServiciosComplementariosValor());
                        break;

                    default:
                        break;
                }
                entidadCombinada.setEstadoItem(listamed.getEstado());
                entidadCombinada.setEstadoItem(listamed.getEstado());
                entidadCombinada.setUsuarioAtiende(listamed.getUsuarioAtiende());
                entidadCombinada.setTerminalAtiende(listamed.getTerminalAtiende());
                entidadCombinada.setFechaHoraAtiende(listamed.getFechaHoraAtiende());
                entidadCombinada.setAtendido(listamed.getAtendido());

                if (result.getAsegAfiliadosId() != null) {
                    entidadCombinada.setIdAfiliado(result.getAsegAfiliadosId() != null ? result.getAsegAfiliadosId().getId() : null);
                    entidadCombinada.setPrimerNombreAfiliado(result.getAsegAfiliadosId().getPrimerNombre() != null ? result.getAsegAfiliadosId().getPrimerNombre() : null);
                    entidadCombinada.setPrimerApellidoAfiliado(result.getAsegAfiliadosId().getPrimerApellido() != null ? result.getAsegAfiliadosId().getPrimerApellido() : null);
                    entidadCombinada.setSegundoNombreAfiliado(result.getAsegAfiliadosId().getSegundoNombre() != null ? result.getAsegAfiliadosId().getSegundoNombre() : null);
                    entidadCombinada.setSegundoApellidoAfiliado(result.getAsegAfiliadosId().getSegundoApellido() != null ? result.getAsegAfiliadosId().getSegundoApellido() : null);
                    entidadCombinada.setNumeroDocumentoAfiliado(result.getAsegAfiliadosId().getNumeroDocumento() != null ? result.getAsegAfiliadosId().getNumeroDocumento() : null);
                    entidadCombinada.setRegimenAfiliado(result.getAsegAfiliadosId().getRegimen() != null ? result.getAsegAfiliadosId().getRegimen() : null);
                    entidadCombinada.setTipoDocumentoAfiliado(result.getAsegAfiliadosId().getMaeTipoDocumentoValor() != null ? result.getAsegAfiliadosId().getMaeTipoDocumentoValor() : null);
                    entidadCombinada.setMaeTipoDocumentoAfiliadoCodigo(result.getAsegAfiliadosId().getMaeTipoDocumentoCodigo() != null ? result.getAsegAfiliadosId().getMaeTipoDocumentoCodigo() : null);
                    entidadCombinada.setMaeTipoDocumentoAfiliadoId(result.getAsegAfiliadosId().getMaeTipoDocumentoId());
                    entidadCombinada.setAfiliacionUbicacion(castUbicacionEntidadNegocio(result.getAsegAfiliadosId().getAfiliacionUbicacionesId()));
                }

                if (result.getCntProfesionalesId() != null) {
                    entidadCombinada.setPrimerNombrePrestador(result.getCntProfesionalesId().getPrimerNombre());
                    entidadCombinada.setPrimerApellidoPrestador(result.getCntProfesionalesId().getPrimerApellido());
                    entidadCombinada.setSegundoNombrePrestador(result.getCntProfesionalesId().getSegundoNombre());
                    entidadCombinada.setSegundoApellidoPrestador(result.getCntProfesionalesId().getSegundoApellido());
                    entidadCombinada.setNumeroDocumentoPrestador(result.getCntProfesionalesId().getDocumento());
                }
                listResult.add(entidadCombinada);

            }
        }
        return listResult;
    }

    public static Ubicacion castUbicacionEntidadNegocio(GnUbicaciones per) {
        Ubicacion obj = new Ubicacion();
        if (per != null) {
            obj.setId(per.getId());
            obj.setNombre(per.getNombre());
            obj.setTipo(per.getTipo());
            obj.setPrefijo(per.getPrefijo());
            // 2020-10-21  ajuste para soportar datos de la ubicación padre - Req Validacion Derechos de Afiliado
            if (per.getGnUbicacionesId() != null) {
                obj.setUbicacionPadre(new Ubicacion(null, per.getGnUbicacionesId().getId(), per.getGnUbicacionesId().getTipo(), per.getGnUbicacionesId().getCodigoPostal(), per.getGnUbicacionesId().getNombre()));
                obj.getUbicacionPadre().setPrefijo(per.getGnUbicacionesId().getPrefijo());
            }
        }
        return obj;
    }

    @Override
    public int insertarDireccionamiento(MpDireccionamiento direc) throws Exception {
        int id = 0;

        try {

            MpDireccionamientos entidad = castNegocioEntidadDireccionamiento(direc);

            if (entidad.getId() == null) {

                getEntityManager().persist(entidad);
                getEntityManager().flush();
                id = entidad.getId();
            } else {
                id = entidad.getId();
            }
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }

        return id;
    }

    public static MpDireccionamientos castNegocioEntidadDireccionamiento(MpDireccionamiento neg) {
        MpDireccionamientos ent = new MpDireccionamientos();

        ent.setMpPrescripcionesId(new MpPrescripciones(neg.getMpPrescripcionId().getId()));

        if (neg.getTipoTecnologia() != null) {
            switch (neg.getTipoTecnologia()) {
                case 1:
                case 4:
                    ent.setMpPrescripcionMedicamentosId(new MpPrescripcionMedicamentos(neg.getMpPrescripcionMedicamentoId().getId()));
                    break;
                case 2:
                    ent.setMpPrescripcionTecnologiasId(new MpPrescripcionTecnologias(neg.getMpPrescripcionTecnologiaId().getId()));
                    break;
                case 3:
                case 5:
                    ent.setMpPrescripcionInsumosId(new MpPrescripcionInsumos(neg.getMpPrescripcionInsumoId().getId()));
                    break;
                default:
                    break;
            }
        }
        ent.setValorTecContratada((neg.getValorTecContratada()));
        ent.setUbicacionSedeId((neg.getUbicacionSedeIdStr()));
        ent.setTipoTecnologia(neg.getTipoTecnologia());
        ent.setFechaMaxEntrega(neg.getFechaMaxEntrega());
        ent.setEntregaTotal(neg.getEntregaTotal());
        ent.setEntregadoNumero(neg.getEntregadoNumero());
        ent.setEntregadoTotal(neg.getEntregadoTotal());
        ent.setEntregadoPendiente(neg.getEntregadoPendiente());
        ent.setJustificacionDireccionamiento(neg.getJustificacionDireccionamiento());
        ent.setEnvioCorreoAuto(neg.getEnvioCorreoAuto());
        ent.setFechaEnvioAuto(neg.getFechaEnvioAuto());
        ent.setCodigoMpEntrega(neg.getCodigoMpEntrega());
        ent.setCodigoMpPropio(neg.getCodigoMpPropio());
        ent.setSubEntrega(neg.getSubEntrega());
        ent.setCodigoPrestadorSede(neg.getCodigoPrestadorSede());
        ent.setDireccionSede(neg.getDireccionSede());
        ent.setNombreSede(neg.getNombreSede());
        ent.setCodigoSede(neg.getCodigoSede());
        ent.setCodigoHabilitacionSede(neg.getCodigoHabilitacionSede());
        ent.setMaeTipoDocumentoId(neg.getMaeTipoDocumentoPacienteId());
        ent.setMaeTipoDocumentoCodigo(neg.getMaeTipoDocumentoPacienteCodigo());
        ent.setMaeTipoDocumentoValor(neg.getMaeTipoDocumentoPacienteValor());
        ent.setNumeroDocumentoPaciente(neg.getNumeroDocumentoPaciente());
        ent.setConsecutivoEntrega(neg.getConsecutivoEntrega());
        ent.setEsEntregaParcial(neg.getEsEntregaParcial());
        ent.setEsEntregaDiferida(neg.getEsEntregaDiferida());
        ent.setCodigoEntregaParcial(neg.getCodigoEntregaParcial());
        ent.setCodigoEntregaDiferida(neg.getCodigoEntregaDiferida());
        ent.setNumeroPrescripcionAsoc(neg.getNumeroPrescripcionAso());
        ent.setConsecutivoTecAsociada(neg.getConsecutivoTecAsociada());
        ent.setEstado(neg.getEstado());
        ent.setUltimoDireccionamiento(neg.getUltimoDireccionamiento());
        ent.setMaeTipoDocumentoPrestadorId((neg.getMaeTipoDocumentoPrestadorId()));
        ent.setMaeTipoDocumentoPrestadorCodigo((neg.getMaeTipoDocumentoPrestadorCodigo()));
        ent.setMaeTipoDocumentoPrestadorValor((neg.getMaeTipoDocumentoPrestadorValor()));
        ent.setPrestadorRazonSocial(neg.getPrestadorRazonSocial());
        BigInteger bigIntValue = neg.getPrestadorNumeroDocumento();
        Integer intValue = bigIntValue.intValue();
        ent.setPrestadorNumeroDocumento(intValue);
        ent.setUltimoDireccionamiento(neg.getUltimoDireccionamiento());
        ent.setFechaDireccionamiento(neg.getFechaHoraCrea());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());

        return ent;
    }

    @Override
    public List<MpHomologacion> consultarHomologacion() throws Exception {
        List<MpHomologacion> listaResultado = new ArrayList<>();

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT mph FROM MpHomologaciones mph WHERE mph.id > 0");
            List<MpHomologaciones> lista = getEntityManager().createQuery(strQuery.toString()).getResultList();
            lista.forEach(mpHomologacion -> listaResultado.add(castEntidadNegocioH(mpHomologacion)));
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    @Override
    public List<Integer> consultarIdDireccionamiento(Integer idPrescripcion, Integer idItem, Integer tipo) throws Exception {
        List<Integer> listaResultado = new ArrayList<>();

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT mpd FROM MpDireccionamientos mpd WHERE mpd.mpPrescripcionesId.id = :idPrescripcion ");
            switch (tipo) {
                case 1:
                case 4:
                    strQuery.append(" AND mpd.mpPrescripcionMedicamentosId.id = :idItem ");
                    break;
                case 2:
                    strQuery.append(" AND mpd.mpPrescripcionTecnologiasId.id = :idItem ");
                    break;
                case 3:
                case 5:
                    strQuery.append(" AND mpd.mpPrescripcionInsumosId.id = :idItem ");
                    break;
                default:
            }
            List<MpDireccionamientos> lista = getEntityManager().createQuery(strQuery.toString(), MpDireccionamientos.class)
                    .setParameter("idPrescripcion", idPrescripcion)
                    .setParameter("idItem", idItem)
                    .getResultList();

            lista.forEach(mpd -> listaResultado.add(mpd.getId()));
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    @Override
    public List<MpDireccionamientoEntregado> consultarEntregaSinDireccionamiento(Integer idPrescripcion, Integer idItem, Integer tipo) throws Exception {
        List<MpDireccionamientoEntregado> listaResultado = new ArrayList<>();

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT mpd FROM MpDireccionamientoEntregados mpd WHERE mpd.mpPrescripcionId.id = :idPrescripcion ");
            switch (tipo) {
                case 1:
                case 4:
                    strQuery.append(" AND mpd.mpPrescripicionMedicamentosId.id = :idItem ");
                    break;
                case 2:
                    strQuery.append(" AND mpd.mpPrescripicionTecnologiasId.id = :idItem ");
                    break;
                case 3:
                case 5:
                    strQuery.append(" AND mpd.mpPrescripicionInsumosId.id = :idItem ");
                    break;
                default:
            }
            strQuery.append(" AND mpd.estRepEntrega <> 0 ");
            List<MpDireccionamientoEntregados> lista = getEntityManager().createQuery(strQuery.toString())
                    .setParameter("idItem", idItem)
                    .setParameter("idPrescripcion", idPrescripcion)
                    .getResultList();
            lista.forEach(mpEntrega -> listaResultado.add(castEntidadNegocioEntrega(mpEntrega)));

        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    public static MpHomologacion castEntidadNegocioH(MpHomologaciones ent) {
        MpHomologacion obj = new MpHomologacion();
        obj.setTipo(ent.getTipo());
        obj.setCodigo(ent.getCodigo());
        obj.setNombre(ent.getNombre());
        return obj;
    }

    @Override
    public List<MpPrescripcion> consultarPorMpPrescripcionPorDocumento(String doc) throws Exception {
        List<MpPrescripcion> listaResultado = new ArrayList<>();

        try {
            String strQuery = "SELECT mp FROM MpPrescripciones mp WHERE mp.asegAfiliadosId.numeroDocumento = :doc ";
            strQuery += "ORDER BY mp.numeroPrescripcion DESC";

            List<MpPrescripciones> lista = getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("doc", doc)
                    .getResultList();

            lista.forEach(mp -> listaResultado.add(castEntidadNegocioMp(mp)));
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    public static MpPrescripcion castEntidadNegocioMp(MpPrescripciones ent) {
        MpPrescripcion obj = new MpPrescripcion();
        obj.setId(ent.getId());
        obj.setCodAmbAte(ent.getCodAmbAte());
        obj.setNumeroPrescripcion(ent.getNumeroPrescripcion());
        obj.setFechaPrescripcion(ent.getFechaPrescripcion());
        obj.setHoraPrescripcion(ent.getHoraPrescripcion());
        obj.setPrestadorRazonSocial(ent.getPrestadorRazonSocial());
        obj.setPrestadorNumeroDocumento(ent.getPrestadorNumeroDocumento());
        if (ent.getCntProfesionalesId() != null) {
            obj.setCntProfesional(new CntProfesional(ent.getCntProfesionalesId().getId()));
            obj.getCntProfesional().setPrimerNombre(ent.getCntProfesionalesId().getPrimerNombre());
            obj.getCntProfesional().setSegundoNombre(ent.getCntProfesionalesId().getSegundoNombre());
            obj.getCntProfesional().setPrimerApellido(ent.getCntProfesionalesId().getPrimerApellido());
            obj.getCntProfesional().setSegundoApellido(ent.getCntProfesionalesId().getSegundoApellido());
            obj.getCntProfesional().setDocumento(ent.getCntProfesionalesId().getDocumento());
        }
        obj.setMaDiagnosticoPrincipalCodigo(ent.getMaDiagnosticoPrincipalCodigo());
        obj.setMaDiagnosticoPrincipalValor(ent.getMaDiagnosticoPrincipalValor());
        return obj;
    }

    public String reemplazarCaracteresEspeciales(String dato) {
        String cadenaCaracteresEspeciales = "áéíóúüÁÉÍÓÚÜñÑ.,;:!?¿¡(){}[]<>%^*&/|#~`'\\\"";
        String cadenaCaracteresSinAcento = "aeiouuAEIOUUnN        ";

        for (int i = 0; i < cadenaCaracteresEspeciales.length(); i++) {
            dato = dato.replace(cadenaCaracteresEspeciales.charAt(i), cadenaCaracteresSinAcento.charAt(i));
        }

        return dato;
    }

    @Override
    public MpPrescripcion consultarPrescripcion(int id) throws Exception {
        MpPrescripcion objResult = new MpPrescripcion();
        try {
            objResult = castEntidadNegocio((MpPrescripciones) getEntityManager().find(MpPrescripciones.class, id));
        } catch (NoResultException e) {
            objResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objResult;
    }

    @Override
    public MpPrescripcion consultarPrescripcionCot(int id) throws Exception {
        MpPrescripcion objResult = new MpPrescripcion();
        try {
            objResult = castEntidadNegocioCot((MpPrescripciones) getEntityManager().find(MpPrescripciones.class, id));
        } catch (NoResultException e) {
            objResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objResult;
    }

    public static MpPrescripcion castEntidadNegocioCot(MpPrescripciones ent) {
        MpPrescripcion obj = new MpPrescripcion();
        obj.setId(ent.getId());
        obj.setNumeroPrescripcion(ent.getNumeroPrescripcion());
        return obj;
    }

    @Override
    public MpPrescripcionMedicamento consultarPrescripcionMCot(int id) throws Exception {
        MpPrescripcionMedicamento objResult = new MpPrescripcionMedicamento();
        try {
            objResult = castEntidadNegocioMCot((MpPrescripcionMedicamentos) getEntityManager().find(MpPrescripcionMedicamentos.class, id));
        } catch (NoResultException e) {
            objResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objResult;
    }

    public static MpPrescripcionMedicamento castEntidadNegocioMCot(MpPrescripcionMedicamentos ent) {
        MpPrescripcionMedicamento obj = new MpPrescripcionMedicamento();
        obj.setId(ent.getId());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setConsecutivoOrden(ent.getConsecutivoOrden());
        obj.setDescripcionMedicamentoPrincipioActivo(ent.getDescripcionMedicamentoPrincipioActivo());//nombre tipo 1
        obj.setMaeProductosNutricionalesValor(ent.getMaeProductosNutricionalesValor());//nombre de tipo 4
        return obj;
    }

    @Override
    public MpPrescripcionTecnologia consultarPrescripcionTCot(int id) throws Exception {
        MpPrescripcionTecnologia objResult = new MpPrescripcionTecnologia();
        try {
            objResult = castEntidadNegocioTCot((MpPrescripcionTecnologias) getEntityManager().find(MpPrescripcionTecnologias.class, id));
        } catch (NoResultException e) {
            objResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objResult;
    }

    public static MpPrescripcionTecnologia castEntidadNegocioTCot(MpPrescripcionTecnologias ent) {
        MpPrescripcionTecnologia obj = new MpPrescripcionTecnologia();
        obj.setId(ent.getId());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setConsecutivoOrden(ent.getConsecutivoOrden());
        obj.setMaTecnologiaValor(ent.getMaTecnologiaValor());//nombre tipo 2        
        return obj;
    }

    @Override
    public MpPrescripcionInsumo consultarPrescripcionICot(int id) throws Exception {
        MpPrescripcionInsumo objResult = new MpPrescripcionInsumo();
        try {
            objResult = castEntidadNegocioICot((MpPrescripcionInsumos) getEntityManager().find(MpPrescripcionInsumos.class, id));
        } catch (NoResultException e) {
            objResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objResult;
    }

    public static MpPrescripcionInsumo castEntidadNegocioICot(MpPrescripcionInsumos ent) {
        MpPrescripcionInsumo obj = new MpPrescripcionInsumo();
        obj.setId(ent.getId());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setConsecutivoOrden(ent.getConsecutivoOrden());
        obj.setMaeServiciosComplementariosNombre(ent.getMaeServiciosComplementariosValor());//nombre tipo 3
        obj.setMaeDispositivosNombre(ent.getMaedispositivosValor());//nombre de tipo 5
        return obj;
    }

    @Override
    public MpAnuladaPrescripcion consultarMpPrescripcionAnulada(int idPrescripcion) throws Exception {
        MpAnuladaPrescripcion result = null;
        try {
            String strQuery = "FROM MpPrescripcionAnulada p "
                    + "WHERE p.mpPrescripcionesId = '" + idPrescripcion + "' ";
            MpPrescripcionAnulada obj = (MpPrescripcionAnulada) getEntityManager().createQuery(strQuery).getSingleResult();
            result = castEntidadNegocioAnulada(obj);
        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            result = null;
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    @Override
    public MpPrescripcionAuditoria consultarAuditoriaa(Integer id, Integer idItem, int tipo) throws Exception {
        MpPrescripcionAuditoria result = null;
        try {
            String strQuery = "FROM MpPrescripcionItemAuditoria p "
                    + "WHERE p.mpPrescripcionId = '" + id + "' ";
            switch (tipo) {
                case 1:
                    strQuery += "AND p.mpPrescripcionMedicamentoId= '" + idItem + "' ";
                    break;
                case 2:
                    strQuery += "AND p.mpPrescripcionTecnologiaId = '" + idItem + "' ";
                    break;
                case 3:
                    strQuery += "AND p.mpPrescripcionInsumoId = '" + idItem + "' ";
                    break;
                default:
                    break;
            }

            MpPrescripcionItemAuditoria obj = (MpPrescripcionItemAuditoria) getEntityManager().createQuery(strQuery).getSingleResult();
            result = castEntidadNegocioAuditoria(obj);
        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            result = null;
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    @Override
    public List<MpPrescripcionAuditoria> consultarAuditoriaaLista(Integer id, Integer idItem, int tipo) throws Exception {
        List<MpPrescripcionAuditoria> result = new ArrayList<>();
        try {
            String strQuery = "FROM MpPrescripcionItemAuditoria p WHERE p.mpPrescripcionId.id = :id ";

            switch (tipo) {
                case 1:
                    strQuery += "AND p.mpPrescripcionMedicamentoId.id = :idItem ";
                    break;
                case 2:
                    strQuery += "AND p.mpPrescripcionTecnologiaId.id = :idItem ";
                    break;
                case 3:
                    strQuery += "AND p.mpPrescripcionInsumoId.id = :idItem ";
                    break;
                default:
                    throw new IllegalArgumentException("Tipo no válido: " + tipo);
            }

            List<MpPrescripcionItemAuditoria> lista = getEntityManager()
                    .createQuery(strQuery, MpPrescripcionItemAuditoria.class)
                    .setParameter("id", id)
                    .setParameter("idItem", idItem)
                    .getResultList();

            for (MpPrescripcionItemAuditoria obj : lista) {
                result.add(castEntidadNegocioAuditoria(obj));
            }
        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            throw new Exception("Error al consultar auditorías", e);
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    public static MpPrescripcionAuditoria castEntidadNegocioAuditoria(MpPrescripcionItemAuditoria ent) {
        MpPrescripcionAuditoria obj = new MpPrescripcionAuditoria();
        obj.setNotaAuditoria(ent.getNotaAuditoria());
        obj.setEstado(ent.getEstado());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        return obj;
    }

    @Override
    public MpDetalleItem consultarPrescripcionM(int id) throws Exception {
        MpDetalleItem result = null;
        try {
            String strQuery = "SELECT p FROM MpPrescripcionMedicamentos p WHERE p.id = :id";
            TypedQuery<MpPrescripcionMedicamentos> query = getEntityManager().createQuery(strQuery, MpPrescripcionMedicamentos.class);
            query.setParameter("id", id);
            MpPrescripcionMedicamentos obj = query.getSingleResult();
            result = castEntidadNegocioPrescripcionM(obj);
        } catch (Exception e) {
            throw e;
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    @Override
    public MpPrescripcion consultarPrescripcionPorId(int id) throws Exception {
        MpPrescripcion result = null;
        try {
            String strQuery = "SELECT p FROM MpPrescripciones p WHERE p.id = :id";
            TypedQuery<MpPrescripciones> query = getEntityManager().createQuery(strQuery, MpPrescripciones.class);
            query.setParameter("id", id);
            MpPrescripciones obj = query.getSingleResult();
            result = castEntidadNegocioDetalle(obj);
        } catch (Exception e) {
            throw e;
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    public static MpDetalleItem castEntidadNegocioPrescripcionM(MpPrescripcionMedicamentos ent) {
        MpDetalleItem obj = new MpDetalleItem();
        obj.setId(ent.getId());
        obj.setConsecutivo(ent.getConsecutivoOrden());
        obj.setTipoTecnologia(String.valueOf(ent.getTipoTecnologia()) == null ? String.valueOf(ent.getTipoMedicamento()) : String.valueOf(ent.getTipoTecnologia()));
        obj.setTipoPrestacion(String.valueOf(ent.getTipoPrestacion()));
        obj.setEstado(String.valueOf(ent.getEstado()));
        obj.setCodigoFormaFarmaceutica(ent.getCodigoFormulaFarmaceutica());
        obj.setNombreTecnologiaPrescripta(ent.getMaeProductosNutricionalesValor() == null ? ent.getDescripcionMedicamentoPrincipioActivo() : ent.getMaeProductosNutricionalesValor());
        obj.setDuracionTratamientoOrdenado(ent.getDuracionTratamiento());
        obj.setCantidadTotalPrescrita(String.valueOf(ent.getCantidadTotalFormulada()));
        obj.setJustificacionNoPbs(ent.getJustificacionNoPbs());
        obj.setEstadoJuntaProfesionales(ent.getEstadoJuntaProfesionales());
        obj.setTieneCups(Boolean.FALSE);
        obj.setCombinacionCups(Boolean.FALSE);
        obj.setFaseExperimental(ent.getEnFaseExperimental());
        obj.setFinanciadoPbsUpc(ent.getFinancPbsCargoUpc());
        obj.setUtilizoProcedExistentePbsUpc(ent.getUtilizoNutriMedExistentePbsUpc());
        obj.setPbsUtilizado(ent.getPbsUtilizado());
        obj.setDescartoNoExistePbs(ent.getDescartoNoExistePbs());
        obj.setServicioPrestaraColombia(Boolean.TRUE);
        obj.setCantidadTotal(ent.getCantidadTratamiento());

        return obj;
    }

    @Override
    public MpDetalleItem consultarPrescripcionT(int id) throws Exception {
        MpDetalleItem result = null;
        try {
            String strQuery = "SELECT p FROM MpPrescripcionTecnologias p WHERE p.id = :id";
            TypedQuery<MpPrescripcionTecnologias> query = getEntityManager().createQuery(strQuery, MpPrescripcionTecnologias.class);
            query.setParameter("id", id);
            MpPrescripcionTecnologias obj = query.getSingleResult();
            result = castEntidadNegocioPrescripcionT(obj);
        } catch (Exception e) {
            throw e;
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    public static MpDetalleItem castEntidadNegocioPrescripcionT(MpPrescripcionTecnologias ent) {
        MpDetalleItem obj = new MpDetalleItem();
        obj.setId(ent.getId());
        obj.setConsecutivo(ent.getConsecutivoOrden());
        obj.setTipoTecnologia(String.valueOf(ent.getTipoTecnologia()));
        obj.setTipoPrestacion(String.valueOf(ent.getTipoPrestacion()));
        obj.setEstado(String.valueOf(ent.getEstado()));
        obj.setCodigoFormaFarmaceutica("-- -- --");
        obj.setNombreTecnologiaPrescripta(ent.getMaTecnologiaValor());
        obj.setDuracionTratamientoOrdenado(ent.getCantidadDuracionTratamiento());
        obj.setCantidadTotalPrescrita(String.valueOf(ent.getCantidadPrescrita()));
        obj.setJustificacionNoPbs(ent.getJustificacionNoPbs());
        obj.setEstadoJuntaProfesionales(ent.getEstadoJuntaProfesionales());
        obj.setTieneCups(ent.getTieneCups());
        obj.setCombinacionCups(ent.getCombinacionCups());
        obj.setFaseExperimental(ent.getFaseExperimental());
        obj.setFinanciadoPbsUpc(Boolean.FALSE);
        obj.setUtilizoProcedExistentePbsUpc(ent.getUtilizoProcedExistentePbsUpc());
        obj.setPbsUtilizado(ent.getPbsUtilizado());
        obj.setDescartoNoExistePbs(ent.getDescartoNoExistePbs());
        obj.setServicioPrestaraColombia(Boolean.TRUE);
        obj.setCantidadTotal(ent.getCantidadTotal());

        return obj;
    }

    @Override
    public MpDetalleItem consultarPrescripcionI(int id) throws Exception {
        MpDetalleItem result = null;
        try {
            String strQuery = "SELECT p FROM MpPrescripcionInsumos p WHERE p.id = :id";
            TypedQuery<MpPrescripcionInsumos> query = getEntityManager().createQuery(strQuery, MpPrescripcionInsumos.class);
            query.setParameter("id", id);
            MpPrescripcionInsumos obj = query.getSingleResult();
            result = castEntidadNegocioPrescripcionI(obj);
        } catch (Exception e) {
            throw e;
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    public static MpDetalleItem castEntidadNegocioPrescripcionI(MpPrescripcionInsumos ent) {
        MpDetalleItem obj = new MpDetalleItem();
        obj.setId(ent.getId());
        obj.setConsecutivo(ent.getConsecutivoOrden());
        obj.setTipoTecnologia(String.valueOf(ent.getTipoTecnologia()));
        obj.setTipoPrestacion(String.valueOf(ent.getTipoPrestacion()));
        obj.setEstado(String.valueOf(ent.getEstado()));
        obj.setCodigoFormaFarmaceutica(ent.getCodigoFormulada());
        obj.setNombreTecnologiaPrescripta(ent.getMaeServiciosComplementariosValor() == null ? ent.getMaedispositivosValor() : ent.getMaeServiciosComplementariosValor());
        obj.setDuracionTratamientoOrdenado(ent.getDuracionTratamiento());
        obj.setCantidadTotalPrescrita(ent.getCantidadFormulada());
        obj.setJustificacionNoPbs(ent.getJustificacionNoPbs());
        obj.setEstadoJuntaProfesionales(ent.getEstadoJuntaProfesionales());
        obj.setTieneCups(Boolean.FALSE);
        obj.setCombinacionCups(Boolean.FALSE);
        obj.setFaseExperimental(Boolean.FALSE);
        obj.setFinanciadoPbsUpc(Boolean.FALSE);
        obj.setUtilizoProcedExistentePbsUpc(Boolean.FALSE);
        obj.setPbsUtilizado(Boolean.FALSE);
        obj.setDescartoNoExistePbs(ent.getDescartoNoExistePbs());
        obj.setServicioPrestaraColombia(Boolean.TRUE);
        obj.setCantidadTotal(Integer.valueOf(ent.getCantidadFormulada()));

        return obj;
    }

    public static MpPrescripcion castEntidadNegocio(MpPrescripciones ent) {
        MpPrescripcion obj = new MpPrescripcion();
        if (ent.getRecobrante()) {
            obj.setRecobrante(true);
        } else {
            obj.setRecobrante(false);
        }
        obj.setAsegAfiliadoMadreDocumento(ent.getAsegAfiliadoMadreDocumento());
        obj.setAsegAfiliadoMadreTipoDocumento(ent.getAsegAfiliadoMadreTipoDocumento());
        obj.setCodAmbAte(ent.getCodAmbAte());
        obj.setCodigoEnfermedadHuerfana(ent.getCodigoEnfermedadHuerfana());
        obj.setCodigoEps(ent.getCodigoEps());
        obj.setConsecutivoMipres(ent.getConsecutivoMipres());
        obj.setDiagnosticoEnfermedadHuerfana(ent.getDiagnosticoEnfermedadHuerfana());
        obj.setEnfermedadHuerfana(ent.getEnfermedadHuerfana());
        obj.setEstado(ent.getEstado());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setFechaPrescripcion(ent.getFechaPrescripcion());
        obj.setHoraPrescripcion(ent.getHoraPrescripcion());
        obj.setId(ent.getId());
        obj.setMaDiagnosticoPrincipalCodigo(ent.getMaDiagnosticoPrincipalCodigo());
        obj.setMaDiagnosticoPrincipalId(ent.getMaDiagnosticoPrincipalId());
        obj.setMaDiagnosticoPrincipalValor(ent.getMaDiagnosticoPrincipalValor());
        obj.setMaDiagnosticoRelacionado1Codigo(ent.getMaDiagnosticoRelacionado1Codigo());
        obj.setMaDiagnosticoRelacionado1Id(ent.getMaDiagnosticoRelacionado1Id());
        obj.setMaDiagnosticoRelacionado1Valor(ent.getMaDiagnosticoRelacionado1Valor());
        obj.setMaDiagnosticoRelacionado2Codigo(ent.getMaDiagnosticoRelacionado2Codigo());
        obj.setMaDiagnosticoRelacionado2Id(ent.getMaDiagnosticoRelacionado2Id());
        obj.setMaDiagnosticoRelacionado2Valor(ent.getMaDiagnosticoRelacionado2Valor());
        obj.setMaTipoDocumentoPrestadorCodigo(ent.getMaTipoDocumentoPrestadorCodigo());
        obj.setMaTipoDocumentoPrestadorId(ent.getMaTipoDocumentoPrestadorId());
        obj.setMaTipoDocumentoPrestadorValor(ent.getMaTipoDocumentoPrestadorValor());
        obj.setNumeroDocumentoDonanteVivo(ent.getNumeroDocumentoDonanteVivo());
        obj.setNumeroPrescripcion(ent.getNumeroPrescripcion());
        if (!ent.getRecobrante()) {
            obj.setPacienteCovid19(ent.getPacienteCovid19());
            obj.setReferenciaAmbitoAtencion(ent.getReferenciaAmbitoAtencion());
        }
        obj.setPrestadorNumeroDocumento(ent.getPrestadorNumeroDocumento());
        obj.setPrestadorRazonSocial(ent.getPrestadorRazonSocial());
        obj.setSedeCodigoHabilitacion(ent.getSedeCodigoHabilitacion());
        obj.setSopNutricional(ent.getSopNutricional());
        obj.setSopNutricional(ent.getSopNutricional());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setTipoDocumentoDonanteVivo(ent.getTipoDocumentoDonanteVivo());
        obj.setTipoPrescripcion(ent.getTipoPrescripcion());
        obj.setTipoTransaccion(ent.getTipoTransaccion());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());

        if (ent.getAsegAfiliadosId() != null) {
            obj.setAsegAfiliado(new AsegAfiliado(ent.getAsegAfiliadosId().getId()));
            obj.getAsegAfiliado().setMaeTipoDocumento(ent.getAsegAfiliadosId().getMaeTipoDocumentoId());
            obj.getAsegAfiliado().setMaeTipoDocumentoValor(ent.getAsegAfiliadosId().getMaeTipoDocumentoValor());
            obj.getAsegAfiliado().setNumeroDocumento(ent.getAsegAfiliadosId().getNumeroDocumento());
            obj.getAsegAfiliado().setPrimerNombre(ent.getAsegAfiliadosId().getPrimerNombre());
            obj.getAsegAfiliado().setSegundoNombre(ent.getAsegAfiliadosId().getSegundoNombre());
            obj.getAsegAfiliado().setPrimerApellido(ent.getAsegAfiliadosId().getPrimerApellido());
            obj.getAsegAfiliado().setSegundoApellido(ent.getAsegAfiliadosId().getSegundoApellido());
            obj.getAsegAfiliado().setRegimen(ent.getAsegAfiliadosId().getRegimen());
            obj.getAsegAfiliado().setGenero(ent.getAsegAfiliadosId().getGenero());
            obj.getAsegAfiliado().setMaeRegimenCodigo(ent.getAsegAfiliadosId().getMaeRegimenCodigo());
        }

        if (ent.getCntProfesionalesId() != null) {
            obj.setCntProfesional(new CntProfesional(ent.getCntProfesionalesId().getId()));
            obj.getCntProfesional().setPrimerNombre(ent.getCntProfesionalesId().getPrimerNombre());
            obj.getCntProfesional().setSegundoNombre(ent.getCntProfesionalesId().getSegundoNombre());
            obj.getCntProfesional().setPrimerApellido(ent.getCntProfesionalesId().getPrimerApellido());
            obj.getCntProfesional().setSegundoApellido(ent.getCntProfesionalesId().getSegundoApellido());
            obj.getCntProfesional().setDocumento(ent.getCntProfesionalesId().getDocumento());
            obj.getCntProfesional().setMaeTipoDocumentoValor(ent.getCntProfesionalesId().getMaeTipoDocumentoValor());
            obj.getCntProfesional().setRegistroMedico(ent.getCntProfesionalesId().getRegistroMedico());
        }

        return obj;
    }

    public static MpPrescripcion castEntidadNegocioDetalle(MpPrescripciones ent) {
        MpPrescripcion obj = new MpPrescripcion();
        obj.setCodAmbAte(ent.getCodAmbAte());
        obj.setCodigoEnfermedadHuerfana(ent.getCodigoEnfermedadHuerfana());
        obj.setConsecutivoMipres(ent.getConsecutivoMipres());
        obj.setDiagnosticoEnfermedadHuerfana(ent.getDiagnosticoEnfermedadHuerfana());
        obj.setEnfermedadHuerfana(ent.getEnfermedadHuerfana());
        obj.setEstado(ent.getEstado());
        obj.setFechaPrescripcion(ent.getFechaPrescripcion());
        obj.setMaDiagnosticoPrincipalValor(ent.getMaDiagnosticoPrincipalValor());
        obj.setMaDiagnosticoRelacionado1Valor(ent.getMaDiagnosticoRelacionado1Valor());
        obj.setMaDiagnosticoRelacionado2Valor(ent.getMaDiagnosticoRelacionado2Valor());
        obj.setNumeroPrescripcion(ent.getNumeroPrescripcion());

        return obj;
    }

    public static MpAnuladaPrescripcion castEntidadNegocioAnulada(MpPrescripcionAnulada ent) {
        MpAnuladaPrescripcion obj = new MpAnuladaPrescripcion();
        obj.setEstado(ent.getEstado());
        obj.setTipo(ent.getTipo());
        obj.setNumeroPrescripcion(ent.getNumeroPrescripcion());
        obj.setJustificacion(ent.getJustificacion());
        obj.setObservacion(ent.getObservacion());
        obj.setUsuarioSolicita(ent.getUsuarioSolicita());
        obj.setFechaHoraSolicitud(ent.getFechaHoraSolicitud());
        obj.setUsuarioAnula(ent.getUsuarioAnula());
        obj.setFechaHoraAnulacion(ent.getFechaHoraAnulacion());
        return obj;
    }

    @Override
    public MpPrescripcionRecobrante consultarRecobrante(int idPrescripcion) throws Exception {
        MpPrescripcionRecobrante result = null;
        try {
            String strQuery = "FROM MpPrescripcionRecobrantes p "
                    + "WHERE p.mpPrescripcionesId = '" + idPrescripcion + "' ";
            MpPrescripcionRecobrantes obj = (MpPrescripcionRecobrantes) getEntityManager().createQuery(strQuery).getSingleResult();
            result = castEntidadNegocioRecobrante(obj);
        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            result = null;
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    @Override
    public MpPrescripcionTecnologia consultarPorTecnologia(int idItem) throws Exception {
        MpPrescripcionTecnologia result = null;
        try {
            String strQuery = "FROM MpPrescripcionTecnologias p "
                    + "WHERE p.id = '" + idItem + "' ";
            MpPrescripcionTecnologias obj = (MpPrescripcionTecnologias) getEntityManager().createQuery(strQuery).getSingleResult();
            result = castEntidadNegocioLargo(obj);
        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            result = null;
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    @Override
    public MpPrescripcionInsumo consultarPorInsumo(int idItem) throws Exception {
        MpPrescripcionInsumo result = null;
        try {
            String strQuery = "FROM MpPrescripcionInsumos p "
                    + "WHERE p.id = '" + idItem + "' ";
            MpPrescripcionInsumos obj = (MpPrescripcionInsumos) getEntityManager().createQuery(strQuery).getSingleResult();
            if (obj.getTipoTecnologia() == 5) {
                result = castEntidadNegocioInsumo(obj);
            } else {
                result = castEntidadNegocioInsumo(obj);
            }
        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            result = null;
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    @Override
    public MpPrescripcionMedicamento consultarPorMedicamento(int idItem) throws Exception {
        MpPrescripcionMedicamento result = null;
        try {
            String strQuery = "FROM MpPrescripcionMedicamentos p "
                    + "WHERE p.id = '" + idItem + "' ";
            MpPrescripcionMedicamentos obj = (MpPrescripcionMedicamentos) getEntityManager().createQuery(strQuery).getSingleResult();
            if (obj.getTipoTecnologia() == 1) {
                result = castEntidadNegocioM(obj);
            } else {
                result = castEntidadNegocioCorto(obj);
            }
        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            result = null;
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    @Override
    public MpDireccionamiento direccionarMedicamento(int idItem) throws Exception {
        MpDireccionamiento result = null;
        try {
            String strQuery = "FROM MpPrescripcionMedicamentos p "
                    + "WHERE p.id = '" + idItem + "' ";
            MpPrescripcionMedicamentos obj = (MpPrescripcionMedicamentos) getEntityManager().createQuery(strQuery).getSingleResult();

            result = castEntidadNegocioDireccionar(obj);

        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            result = null;
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    public static MpDireccionamiento castEntidadNegocioDireccionar(MpPrescripcionMedicamentos ent) {
        MpDireccionamiento obj = new MpDireccionamiento();

        obj.setMpPrescripcionMedicamentoId(new MpPrescripcionMedicamento());

        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.getMpPrescripcionMedicamentoId().setId(ent.getId());
        obj.setEntregaTotal(ent.getCantidadTotalFormulada().intValue());
        obj.setSubEntrega(0);
        obj.setCodigoMpEntrega(ent.getCodigoMipresEntregar());
        obj.setNumeroDeEntregas(ent.getCantidadTratamiento());
        obj.setCronoEntregas(ent.getDuracionTratamiento());

        if (ent.getMpPrescripcionesId() != null) {
            if (ent.getMpPrescripcionesId() != null) {
                obj.getMpPrescripcionMedicamentoId().setMpPrescripcion(new MpPrescripcion(ent.getMpPrescripcionesId().getId()));
                obj.getMpPrescripcionMedicamentoId().getMpPrescripcion().setMunicipioIpsPrescriptora(ent.getMpPrescripcionesId().getMunicipioIpsPrescriptora());

            }
        }

        return obj;
    }

    @Override
    public MpDireccionamiento direccionarTecnologia(int idItem) throws Exception {
        MpDireccionamiento result = null;
        try {
            String strQuery = "FROM MpPrescripcionTecnologias t "
                    + "WHERE t.id = '" + idItem + "' ";
            MpPrescripcionTecnologias obj = (MpPrescripcionTecnologias) getEntityManager().createQuery(strQuery).getSingleResult();

            result = castEntidadNegocioDireccionarT(obj);

        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            result = null;
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    public static MpDireccionamiento castEntidadNegocioDireccionarT(MpPrescripcionTecnologias ent) {
        MpDireccionamiento obj = new MpDireccionamiento();

        obj.setMpPrescripcionTecnologiaId(new MpPrescripcionTecnologia());

        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.getMpPrescripcionMedicamentoId().setId(ent.getId());
        obj.setEntregaTotal(ent.getCantidadFormulada());
        obj.setSubEntrega(0);
        obj.setCodigoMpEntrega(ent.getCodigoMipresEntregar());
        obj.setNumeroDeEntregas(ent.getCantidadDuracionTratamiento());
        obj.setCronoEntregas(ent.getCodigoPeriodoDuracionTratamiento());

        if (ent.getMpPrescripcionId() != null) {
            if (ent.getMpPrescripcionId() != null) {
                obj.getMpPrescripcionMedicamentoId().setMpPrescripcion(new MpPrescripcion(ent.getMpPrescripcionId().getId()));
                obj.getMpPrescripcionMedicamentoId().getMpPrescripcion().setMunicipioIpsPrescriptora(ent.getMpPrescripcionId().getMunicipioIpsPrescriptora());

            }
        }

        return obj;
    }

    @Override
    public MpDireccionamiento direccionarInsumo(int idItem) throws Exception {
        MpDireccionamiento result = null;
        try {
            String strQuery = "FROM MpPrescripcionInsumos t "
                    + "WHERE t.id = '" + idItem + "' ";
            MpPrescripcionInsumos obj = (MpPrescripcionInsumos) getEntityManager().createQuery(strQuery).getSingleResult();

            result = castEntidadNegocioDireccionarI(obj);

        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            result = null;
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    public static MpDireccionamiento castEntidadNegocioDireccionarI(MpPrescripcionInsumos ent) {
        MpDireccionamiento obj = new MpDireccionamiento();

        obj.setMpPrescripcionTecnologiaId(new MpPrescripcionTecnologia());

        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.getMpPrescripcionMedicamentoId().setId(ent.getId());
        obj.setEntregaTotal(ent.getCantidadTotalEntrega().intValue());
        obj.setSubEntrega(0);
        obj.setCodigoMpEntrega(ent.getCodigoMipresEntregar());
        obj.setNumeroDeEntregas(ent.getCantidad() != null ? Integer.parseInt(ent.getCantidad()) : 1);
        obj.setCronoEntregas(ent.getCodPerDurTrat());

        if (ent.getMpPrescripcionId() != null) {
            if (ent.getMpPrescripcionId() != null) {
                obj.getMpPrescripcionMedicamentoId().setMpPrescripcion(new MpPrescripcion(ent.getMpPrescripcionId().getId()));
                obj.getMpPrescripcionMedicamentoId().getMpPrescripcion().setMunicipioIpsPrescriptora(ent.getMpPrescripcionId().getMunicipioIpsPrescriptora());

            }
        }

        return obj;
    }

    public static MpPrescripcionRecobrante castEntidadNegocioRecobrante(MpPrescripcionRecobrantes ent) {
        MpPrescripcionRecobrante obj = new MpPrescripcionRecobrante();

        obj.setId(ent.getId());
        obj.setFallo(ent.getFallo());
        obj.setTipoTutela(ent.getTipoTutela());
        obj.setFechaFallo(ent.getFechaFallo());
        obj.setFechaPrimeraInstancia(ent.getFechaPrimeraInstancia());
        obj.setFechaSegundaInstancia(ent.getFechaSegundaInstancia());
        obj.setFechaCorte(ent.getFechaCorte());
        obj.setFechaDesacato(ent.getFechaDesacato());
        obj.setMaDiagnosticoMotivaPrincipalId(ent.getMaDiagnosticoMotivaPrincipalId());
        obj.setMaDiagnosticoMotivaPrincipalCodigo(ent.getMaDiagnosticoMotivaPrincipalCodigo());
        obj.setMaDiagnosticoMotivaPrincipalValor(ent.getMaDiagnosticoMotivaPrincipalValor());
        obj.setMaDiagnosticoMotiva2Id(ent.getMaDiagnosticoMotiva2Id());
        obj.setMaDiagnosticoMotiva2Codigo(ent.getMaDiagnosticoMotiva2Codigo());
        obj.setMaDiagnosticoMotiva2Valor(ent.getMaDiagnosticoMotiva2Valor());
        obj.setMaDiagnosticoMotiva3Id(ent.getMaDiagnosticoMotiva3Id());
        obj.setMaDiagnosticoMotiva3Codigo(ent.getMaDiagnosticoMotiva3Codigo());
        obj.setMaDiagnosticoMotiva3Valor(ent.getMaDiagnosticoMotiva3Valor());
        obj.setCriterio1Corte(ent.getCriterio1Corte());
        obj.setCriterio2Corte(ent.getCriterio2Corte());
        obj.setCriterio3Corte(ent.getCriterio3Corte());
        obj.setCriterio4Corte(ent.getCriterio4Corte());
        obj.setAclaracionFallo(ent.getAclaracionFallo());
        obj.setJustificacionMedica(ent.getJustificacionMedica());

        if (ent.getMpPrescripcionesId() != null) {
            obj.setMpPrescripcionId(new MpPrescripcion(ent.getMpPrescripcionesId().getId()));
        }
        return obj;
    }

    @Override
    public List<MpPrescripcionMedicamento> consultarPorMpPrescripcionMedicamento(int mpPrescripcionId) throws Exception {
        List<MpPrescripcionMedicamento> listaResultados = new ArrayList<>();
        try {
            String strQuery = "SELECT p FROM MpPrescripcionMedicamentos p WHERE p.mpPrescripcionesId.id = " + mpPrescripcionId;
            List<MpPrescripcionMedicamentos> MP = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MpPrescripcionMedicamentos medicamentos : MP) {
                listaResultados.add(castEntidadNegocioCOR(medicamentos));
            }

        } catch (NoResultException nre) {
            System.out.println("No se encontraron resultados para el ID proporcionado.");
        } catch (javax.persistence.PersistenceException ex) {
            Throwable rootCause = ex.getCause();
            if (rootCause instanceof org.hibernate.exception.GenericJDBCException) {
                System.out.println("Error de JDBC: " + rootCause.getMessage());
                System.out.println("Tipo de error: " + rootCause.getClass().getSimpleName());
                System.out.println("Causa raíz: " + rootCause);

            } else if (rootCause instanceof org.hibernate.exception.SQLGrammarException) {
                System.out.println("Error de sintaxis SQL: " + rootCause.getMessage());
                System.out.println("Tipo de error: " + rootCause.getClass().getSimpleName());
                System.out.println("Causa raíz: " + rootCause);

            } else {
                System.out.println("Error de persistencia: " + ex.getMessage());
                System.out.println("Tipo de error: " + ex.getClass().getSimpleName());
                System.out.println("Causa raíz: " + ex);

            }
        } catch (Exception ex) {
            System.out.println("Otro error: " + ex.getMessage());
            System.out.println("Tipo de error: " + ex.getClass().getSimpleName());
            System.out.println("Causa raíz: " + ex);

        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }

    @Override
    public List<MpPrescripcionMedicamento> consultarPorMpPrescripcionMedicamentoH(int mpPrescripcionId) throws Exception {
        List<MpPrescripcionMedicamento> listaResultados = new ArrayList<>();
        try {
            String strQuery = "SELECT p FROM MpPrescripcionMedicamentos p WHERE p.mpPrescripcionesId.id = " + mpPrescripcionId;
            List<MpPrescripcionMedicamentos> MP = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MpPrescripcionMedicamentos medicamentos : MP) {
                MpPrescripcionMedicamento obj = new MpPrescripcionMedicamento();
                obj.setId(medicamentos.getId());
                obj.setTipoTecnologia(medicamentos.getTipoTecnologia());
                listaResultados.add(obj);
            }

        } catch (NoResultException nre) {
            System.out.println("No se encontraron resultados para el ID proporcionado.");
        } catch (javax.persistence.PersistenceException ex) {
            Throwable rootCause = ex.getCause();
            if (rootCause instanceof org.hibernate.exception.GenericJDBCException) {
                System.out.println("Error de JDBC: " + rootCause.getMessage());
                System.out.println("Tipo de error: " + rootCause.getClass().getSimpleName());
                System.out.println("Causa raíz: " + rootCause);

            } else if (rootCause instanceof org.hibernate.exception.SQLGrammarException) {
                System.out.println("Error de sintaxis SQL: " + rootCause.getMessage());
                System.out.println("Tipo de error: " + rootCause.getClass().getSimpleName());
                System.out.println("Causa raíz: " + rootCause);

            } else {
                System.out.println("Error de persistencia: " + ex.getMessage());
                System.out.println("Tipo de error: " + ex.getClass().getSimpleName());
                System.out.println("Causa raíz: " + ex);

            }
        } catch (Exception ex) {
            System.out.println("Otro error: " + ex.getMessage());
            System.out.println("Tipo de error: " + ex.getClass().getSimpleName());
            System.out.println("Causa raíz: " + ex);

        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }

    @Override
    public List<MpPrescripcionMedicamento> consultarPorMpPrescripcionMedicamentoPlanManejo(Integer mpPrescripcionId) throws Exception {
        List<MpPrescripcionMedicamento> listaResultados = new ArrayList<>();
        try {
            String strQuery = "SELECT p FROM MpPrescripcionMedicamentos p WHERE p.mpPrescripcionesId.id = " + mpPrescripcionId;
            List<MpPrescripcionMedicamentos> MP = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MpPrescripcionMedicamentos medicamentos : MP) {
                listaResultados.add(castEntidadNegocioPlanManejo(medicamentos));
            }

        } catch (NoResultException nre) {
            System.out.println("No se encontraron resultados para el ID proporcionado.");
        } catch (javax.persistence.PersistenceException ex) {
            Throwable rootCause = ex.getCause();
            if (rootCause instanceof org.hibernate.exception.GenericJDBCException) {
                System.out.println("Error de JDBC: " + rootCause.getMessage());
                System.out.println("Tipo de error: " + rootCause.getClass().getSimpleName());
                System.out.println("Causa raíz: " + rootCause);

            } else if (rootCause instanceof org.hibernate.exception.SQLGrammarException) {
                System.out.println("Error de sintaxis SQL: " + rootCause.getMessage());
                System.out.println("Tipo de error: " + rootCause.getClass().getSimpleName());
                System.out.println("Causa raíz: " + rootCause);

            } else {
                System.out.println("Error de persistencia: " + ex.getMessage());
                System.out.println("Tipo de error: " + ex.getClass().getSimpleName());
                System.out.println("Causa raíz: " + ex);

            }
        } catch (Exception ex) {
            System.out.println("Otro error: " + ex.getMessage());
            System.out.println("Tipo de error: " + ex.getClass().getSimpleName());
            System.out.println("Causa raíz: " + ex);

        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }

    public static MpPrescripcionMedicamento castEntidadNegocio(MpPrescripcionMedicamentos ent) {
        MpPrescripcionMedicamento obj = new MpPrescripcionMedicamento();

        obj.setCantidadTotalEntrega(ent.getCantidadTotalEntrega());
        obj.setCantidadTotalFormulada(ent.getCantidadTotalFormulada());
        obj.setCantidadTratamiento(ent.getCantidadTratamiento());
        obj.setCausaSolicitud1(ent.getCausaSolicitud1());
        obj.setCausaSolicitud2(ent.getCausaSolicitud2());
        obj.setCausaSolicitud3(ent.getCausaSolicitud3());
        obj.setCausaSolicitud4(ent.getCausaSolicitud4());
        obj.setCausaSolicitud5(ent.getCausaSolicitud5());
        obj.setCausaSolicitud6(ent.getCausaSolicitud6());
        obj.setCodigoFormulaFarmaceutica(ent.getCodigoFormulaFarmaceutica());
        obj.setCodigoFrecuenciaAdministracion(ent.getCodigoFrecuenciaAdministracion());
        obj.setCodigoViaAdministracion(ent.getCodigoViaAdministracion());
        obj.setConsecutivoOrden(ent.getConsecutivoOrden());
        obj.setDescripcionMedicamentoPrincipioActivo(ent.getDescripcionMedicamentoPrincipioActivo());
        obj.setDescripcionProductoNutricional(ent.getDescripcionProductoNutricional());
        obj.setDescripcionRazon31(ent.getDescripcionRazon31());
        obj.setDescripcionRazon32(ent.getDescripcionRazon32());
        obj.setDescripcionRazon41(ent.getDescripcionRazon41());
        obj.setDescripcionRazon42(ent.getDescripcionRazon42());
        obj.setDescripcionRazon43(ent.getDescripcionRazon43());
        obj.setDescripcionRazon44(ent.getDescripcionRazon44());
        obj.setDescripcionRazon5(ent.getDescripcionRazon5());
        obj.setDescripcionRazon51(ent.getDescripcionRazon51());
        obj.setDescripcionRazon52(ent.getDescripcionRazon52());
        obj.setDescripcionRazon53(ent.getDescripcionRazon53());
        obj.setDescripcionRazon54(ent.getDescripcionRazon54());
        obj.setDosis(ent.getDosis());
        obj.setDosisUnidadMedida(ent.getDosisUnidadMedida());
        obj.setDuracionTratamiento(ent.getDuracionTratamiento());
        obj.setEntregados(ent.getEntregados());
        obj.setEstado(ent.getEstado());
        obj.setEstadoJuntaProfesionales(ent.getEstadoJuntaProfesionales());
        obj.setFechaDireccionamiento(ent.getFechaDireccionamiento());
        obj.setId(ent.getId());
        obj.setIdDireccionamiento(ent.getIdDireccionamiento());
        obj.setIdTransaccion(ent.getIdTransaccion());
        obj.setIndicacionRecibida(ent.getIndicacionRecibida());
        obj.setIndicacionesEspeciales(ent.getIndicacionesEspeciales());
        obj.setJustificacionNoPbs(ent.getJustificacionNoPbs());
        obj.setEsDiagnosticoCancer(ent.getEsDiagnosticoCancer());
        obj.setEsDiagnosticoDesnutricion(ent.getEsDiagnosticoDesnutricion());
        obj.setEsDiagnosticoEnfermedadRenal(ent.getEsDiagnosticoEnfermedadRenal());
        obj.setEsDiagnosticoVih(ent.getEsDiagnosticoVih());
        obj.setMedPbsUtilizado(ent.getMedPbsUtilizado());
        if (ent.getMpPrescripcionesId() != null) {
            obj.setMpPrescripcion(new MpPrescripcion(ent.getMpPrescripcionesId().getId()));
        }
        obj.setNumeroFrecuenciaAdministracion(ent.getNumeroFrecuenciaAdministracion());
        obj.setPendientes(ent.getPendientes());
        obj.setRazonCausaSolicitud31(ent.getRazonCausaSolicitud31());
        obj.setRazonCausaSolicitud32(ent.getRazonCausaSolicitud32());
        obj.setRazonCausaSolicitud41(ent.getRazonCausaSolicitud41());
        obj.setRazonCausaSolicitud42(ent.getRazonCausaSolicitud42());
        obj.setRazonCausaSolicitud43(ent.getRazonCausaSolicitud43());
        obj.setRazonCausaSolicitud44(ent.getRazonCausaSolicitud44());
        obj.setRazonCausaSolicitud51(ent.getRazonCausaSolicitud51());
        obj.setRazonCausaSolicitud52(ent.getRazonCausaSolicitud52());
        obj.setRazonCausaSolicitud53(ent.getRazonCausaSolicitud53());
        obj.setRazonCausaSolicitud54(ent.getRazonCausaSolicitud54());
        obj.setTipoMedicamento(ent.getTipoMedicamento());
        obj.setTipoPrestacion(ent.getTipoPrestacion());
        obj.setTipoProductoNutricional(ent.getTipoProductoNutricional());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setUnidadFarmaceuticaCantidadTotal(ent.getUnidadFarmaceuticaCantidadTotal());
        obj.setFechaMaximaEntrega(ent.getFechaMaximaEntrega());

        obj.setMaeProductosNutricionalesCodigo(CONSULTAR_TODOS);
        if (ent.getMaeProductosNutricionalesCodigo() != null) {
            obj.setMaeProductosNutricionalesCodigo(ent.getMaeProductosNutricionalesCodigo());
            obj.setMaeProductosNutricionalesValor(ent.getMaeProductosNutricionalesValor());
        }
        return obj;
    }

    public static MpPrescripcionMedicamento castEntidadNegocioPlanManejo(MpPrescripcionMedicamentos ent) {
        MpPrescripcionMedicamento obj = new MpPrescripcionMedicamento();
        if (ent.getMpPrescripcionesId() != null) {
            MpPrescripcion prescripcion = new MpPrescripcion();
            if (ent.getMpPrescripcionesId().getAsegAfiliadosId() != null) {
                AsegAfiliado afiliado = new AsegAfiliado();
                afiliado.setId(ent.getMpPrescripcionesId().getAsegAfiliadosId().getId());
                afiliado.setNumeroDocumento(ent.getMpPrescripcionesId().getAsegAfiliadosId().getNumeroDocumento());
                afiliado.setPrimerNombre(ent.getMpPrescripcionesId().getAsegAfiliadosId().getPrimerNombre());
                afiliado.setSegundoNombre(ent.getMpPrescripcionesId().getAsegAfiliadosId().getSegundoNombre());
                afiliado.setPrimerApellido(ent.getMpPrescripcionesId().getAsegAfiliadosId().getPrimerApellido());
                afiliado.setSegundoApellido(ent.getMpPrescripcionesId().getAsegAfiliadosId().getSegundoApellido());
                afiliado.setRegimen(ent.getMpPrescripcionesId().getAsegAfiliadosId().getRegimen());
                prescripcion.setAsegAfiliado(afiliado);
            }
            if (ent.getMpPrescripcionesId().getCntProfesionalesId() != null) {
                CntProfesional profecional = new CntProfesional();
                profecional.setId(ent.getMpPrescripcionesId().getCntProfesionalesId().getId());
                profecional.setDocumento(ent.getMpPrescripcionesId().getCntProfesionalesId().getDocumento());
                profecional.setPrimerNombre(ent.getMpPrescripcionesId().getCntProfesionalesId().getPrimerNombre());
                profecional.setSegundoNombre(ent.getMpPrescripcionesId().getCntProfesionalesId().getSegundoNombre());
                profecional.setPrimerApellido(ent.getMpPrescripcionesId().getCntProfesionalesId().getPrimerApellido());
                profecional.setSegundoApellido(ent.getMpPrescripcionesId().getCntProfesionalesId().getSegundoApellido());
                profecional.setRegistroMedico(ent.getMpPrescripcionesId().getCntProfesionalesId().getRegistroMedico());
                prescripcion.setCntProfesional(profecional);
            }
            prescripcion.setId(ent.getMpPrescripcionesId().getId());
            prescripcion.setNumeroPrescripcion(ent.getMpPrescripcionesId().getNumeroPrescripcion());
            prescripcion.setMaDiagnosticoPrincipalValor(ent.getMpPrescripcionesId().getMaDiagnosticoPrincipalValor());
            prescripcion.setCodAmbAte(ent.getMpPrescripcionesId().getCodAmbAte());
            prescripcion.setSedeCodigoHabilitacion(ent.getMpPrescripcionesId().getSedeCodigoHabilitacion());
            prescripcion.setPrestadorNumeroDocumento(ent.getMpPrescripcionesId().getPrestadorNumeroDocumento());
            prescripcion.setPrestadorRazonSocial(ent.getMpPrescripcionesId().getPrestadorRazonSocial());
            obj.setMpPrescripcion(prescripcion);
        }
        obj.setDosis(ent.getDosis());
        obj.setTipoPrestacion(ent.getTipoPrestacion());
        obj.setTipoTecnologia(ent.getTipoPrestacion());
        obj.setNumeroFrecuenciaAdministracion(ent.getNumeroFrecuenciaAdministracion());
        obj.setCodigoFrecuenciaAdministracion(ent.getCodigoFrecuenciaAdministracion());
        obj.setCantidadTratamiento(ent.getCantidadTratamiento());
        obj.setDuracionTratamiento(ent.getDuracionTratamiento());
        obj.setCantidadTotalFormulada(ent.getCantidadTotalFormulada());
        obj.setIndicacionRecibida(ent.getIndicacionRecibida());

        return obj;
    }

    public static MpPrescripcionMedicamento castEntidadNegocioCOR(MpPrescripcionMedicamentos ent) {
        MpPrescripcionMedicamento obj = new MpPrescripcionMedicamento();
        obj.setCantidadTotalEntrega(ent.getCantidadTotalEntrega());
        obj.setCantidadTotalFormulada(ent.getCantidadTotalFormulada());
        obj.setCantidadTratamiento(ent.getCantidadTratamiento());
        obj.setCodigoFormulaFarmaceutica(ent.getCodigoFormulaFarmaceutica());
        obj.setConsecutivoOrden(ent.getConsecutivoOrden());
        obj.setDescripcionMedicamentoPrincipioActivo(ent.getDescripcionMedicamentoPrincipioActivo());
        obj.setDescripcionProductoNutricional(ent.getDescripcionProductoNutricional());
        obj.setDuracionTratamiento(ent.getDuracionTratamiento());
        obj.setEntregados(ent.getEntregados());
        obj.setEstado(ent.getEstado());
        obj.setEstadoJuntaProfesionales(ent.getEstadoJuntaProfesionales());
        obj.setFechaDireccionamiento(ent.getFechaDireccionamiento());
        obj.setId(ent.getId());
        obj.setIdTransaccion(ent.getIdTransaccion());
        obj.setMedPbsUtilizado(ent.getMedPbsUtilizado());
        if (ent.getMpPrescripcionesId() != null) {
            obj.setMpPrescripcion(new MpPrescripcion(ent.getMpPrescripcionesId().getId()));
        }
        obj.setPendientes(ent.getPendientes());
        obj.setTipoMedicamento(ent.getTipoMedicamento());
        obj.setTipoPrestacion(ent.getTipoPrestacion());
        obj.setTipoProductoNutricional(ent.getTipoProductoNutricional());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setMaeProductosNutricionalesCodigo(CONSULTAR_TODOS);
        if (ent.getMaeProductosNutricionalesCodigo() != null) {
            obj.setMaeProductosNutricionalesCodigo(ent.getMaeProductosNutricionalesCodigo());
            obj.setMaeProductosNutricionalesValor(ent.getMaeProductosNutricionalesValor());
        }
        return obj;
    }

    @Override
    public List<MpPrescripcionTecnologia> consultarPorMpPrescripcionTecnologia(int mpPrescripcionId) throws Exception {
        List<MpPrescripcionTecnologia> listaResultado = new ArrayList<>();

        try {
            String strQuery = "FROM MpPrescripcionTecnologias p WHERE p.mpPrescripcionId.id = " + mpPrescripcionId;
            List<MpPrescripcionTecnologias> MP = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MpPrescripcionTecnologias tec : MP) {
                listaResultado.add(castEntidadNegocioC(tec));
            }

        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    @Override
    public List<MpPrescripcionTecnologia> consultarPorMpPrescripcionTecnologiaH(int mpPrescripcionId) throws Exception {
        List<MpPrescripcionTecnologia> listaResultado = new ArrayList<>();

        try {
            String strQuery = "FROM MpPrescripcionTecnologias p WHERE p.mpPrescripcionId.id = " + mpPrescripcionId;
            List<MpPrescripcionTecnologias> MP = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MpPrescripcionTecnologias tec : MP) {
                MpPrescripcionTecnologia obj = new MpPrescripcionTecnologia();
                obj.setId(tec.getId());
                obj.setTipoTecnologia(tec.getTipoTecnologia());
                listaResultado.add(obj);
            }

        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    @Override
    public List<MpPrescripcionTecnologia> consultarPorMpPrescripcionTecnologiaPlanManejo(int mpPrescripcionId) throws Exception {
        List<MpPrescripcionTecnologia> listaResultado = new ArrayList<>();

        try {
            String strQuery = "FROM MpPrescripcionTecnologias p WHERE p.mpPrescripcionId.id = " + mpPrescripcionId;
            List<MpPrescripcionTecnologias> MP = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MpPrescripcionTecnologias tec : MP) {
                listaResultado.add(castEntidadNegocioPlanManejo(tec));
            }

        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    public static MpPrescripcionTecnologia castEntidadNegocioPlanManejo(MpPrescripcionTecnologias ent) {
        MpPrescripcionTecnologia obj = new MpPrescripcionTecnologia();
        obj.setCantidadDuracionTratamiento(ent.getCantidadDuracionTratamiento());
        obj.setCantidadFormulada(ent.getCantidadFormulada());
        obj.setCantidadTotal(ent.getCantidadTotal());
        obj.setCantidadTotalEntrega(ent.getCantidadTotalEntrega());
        obj.setFrecuenciaDeUso(ent.getFrecuenciaDeUso());
        obj.setId(ent.getId());
        obj.setIndicacionesPaciente(ent.getIndicacionesPaciente());
        obj.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        obj.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        obj.setTipoPrestacion(ent.getTipoPrestacion());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        if (ent.getMpPrescripcionId() != null) {
            MpPrescripcion prescripcion = new MpPrescripcion();
            if (ent.getMpPrescripcionId().getAsegAfiliadosId() != null) {
                AsegAfiliado afiliado = new AsegAfiliado();
                afiliado.setId(ent.getMpPrescripcionId().getAsegAfiliadosId().getId());
                afiliado.setNumeroDocumento(ent.getMpPrescripcionId().getAsegAfiliadosId().getNumeroDocumento());
                afiliado.setPrimerNombre(ent.getMpPrescripcionId().getAsegAfiliadosId().getPrimerNombre());
                afiliado.setSegundoNombre(ent.getMpPrescripcionId().getAsegAfiliadosId().getSegundoNombre());
                afiliado.setPrimerApellido(ent.getMpPrescripcionId().getAsegAfiliadosId().getPrimerApellido());
                afiliado.setSegundoApellido(ent.getMpPrescripcionId().getAsegAfiliadosId().getSegundoApellido());
                afiliado.setRegimen(ent.getMpPrescripcionId().getAsegAfiliadosId().getRegimen());
                prescripcion.setAsegAfiliado(afiliado);
            }
            if (ent.getMpPrescripcionId().getCntProfesionalesId() != null) {
                CntProfesional profecional = new CntProfesional();
                profecional.setId(ent.getMpPrescripcionId().getCntProfesionalesId().getId());
                profecional.setDocumento(ent.getMpPrescripcionId().getCntProfesionalesId().getDocumento());
                profecional.setPrimerNombre(ent.getMpPrescripcionId().getCntProfesionalesId().getPrimerNombre());
                profecional.setSegundoNombre(ent.getMpPrescripcionId().getCntProfesionalesId().getSegundoNombre());
                profecional.setPrimerApellido(ent.getMpPrescripcionId().getCntProfesionalesId().getPrimerApellido());
                profecional.setSegundoApellido(ent.getMpPrescripcionId().getCntProfesionalesId().getSegundoApellido());
                profecional.setRegistroMedico(ent.getMpPrescripcionId().getCntProfesionalesId().getRegistroMedico());
                prescripcion.setCntProfesional(profecional);
            }
            prescripcion.setId(ent.getMpPrescripcionId().getId());
            prescripcion.setNumeroPrescripcion(ent.getMpPrescripcionId().getNumeroPrescripcion());
            prescripcion.setMaDiagnosticoPrincipalValor(ent.getMpPrescripcionId().getMaDiagnosticoPrincipalValor());
            prescripcion.setCodAmbAte(ent.getMpPrescripcionId().getCodAmbAte());
            prescripcion.setSedeCodigoHabilitacion(ent.getMpPrescripcionId().getSedeCodigoHabilitacion());
            prescripcion.setPrestadorNumeroDocumento(ent.getMpPrescripcionId().getPrestadorNumeroDocumento());
            prescripcion.setPrestadorRazonSocial(ent.getMpPrescripcionId().getPrestadorRazonSocial());
            obj.setMpPrescripcion(prescripcion);
        }

        return obj;
    }

    public static MpPrescripcionTecnologia castEntidadNegocio(MpPrescripcionTecnologias ent) {
        MpPrescripcionTecnologia obj = new MpPrescripcionTecnologia();
        obj.setCantidadDuracionTratamiento(ent.getCantidadDuracionTratamiento());
        obj.setCantidadFormulada(ent.getCantidadFormulada());
        obj.setCantidadTotal(ent.getCantidadTotal());
        obj.setCantidadTotalEntrega(ent.getCantidadTotalEntrega());
        obj.setCausaSolicitud11(ent.getCausaSolicitud11());
        obj.setCausaSolicitud12(ent.getCausaSolicitud12());
        obj.setCausaSolicitud2(ent.getCausaSolicitud2());
        obj.setCausaSolicitud3(ent.getCausaSolicitud3());
        obj.setCausaSolicitud4(ent.getCausaSolicitud4());
        obj.setCausaSolicitud5(ent.getCausaSolicitud5());
        obj.setCausaSolicitud6(ent.getCausaSolicitud6());
        obj.setCausaSolicitud7(ent.getCausaSolicitud7());
        obj.setCodigoPeriodoDuracionTratamiento(ent.getCodigoPeriodoDuracionTratamiento());
        obj.setCodigoRazonCausa52(ent.getCodigoRazonCausa52());
        obj.setCodigoUnidadTiempoFrecuenciaUso(ent.getCodigoUnidadTiempoFrecuenciaUso());
        obj.setConsecutivoOrden(ent.getConsecutivoOrden());
        obj.setDescripcionRazon51(ent.getDescripcionRazon51());
        obj.setDescripcionRazon52(ent.getDescripcionRazon52());
        obj.setEntregados(ent.getEntregados());
        obj.setEstado(ent.getEstado());
        obj.setEstadoJuntaProfesionales(ent.getEstadoJuntaProfesionales());
        obj.setFechaDireccionamiento(ent.getFechaDireccionamiento());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setFrecuenciaDeUso(ent.getFrecuenciaDeUso());
        obj.setId(ent.getId());
        obj.setIdDireccionamiento(ent.getIdDireccionamiento());
        obj.setIdTransaccion(ent.getIdTransaccion());
        obj.setIndicacionesPaciente(ent.getIndicacionesPaciente());
        obj.setJustificacionNoPbs(ent.getJustificacionNoPbs());
        obj.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        obj.setMaTecnologiaId(ent.getMaTecnologiaId());
        obj.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        if (ent.getMpPrescripcionId() != null) {
            obj.setMpPrescripcion(new MpPrescripcion(ent.getMpPrescripcionId().getId()));
        }
        obj.setPendientes(ent.getPendientes());
        obj.setRazonCausaSolicitud51(ent.getRazonCausaSolicitud51());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setTipoPrestacion(ent.getTipoPrestacion());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setFechaMaximaEntrega(ent.getFechaMaximaEntrega());
        return obj;
    }

    public static MpPrescripcionTecnologia castEntidadNegocioC(MpPrescripcionTecnologias ent) {
        MpPrescripcionTecnologia obj = new MpPrescripcionTecnologia();
        obj.setCantidadDuracionTratamiento(ent.getCantidadDuracionTratamiento());
        obj.setCantidadFormulada(ent.getCantidadFormulada());
        obj.setCantidadTotal(ent.getCantidadTotal());
        obj.setCantidadTotalEntrega(ent.getCantidadTotalEntrega());
        obj.setCausaSolicitud11(ent.getCausaSolicitud11());
        obj.setCausaSolicitud12(ent.getCausaSolicitud12());
        obj.setCausaSolicitud2(ent.getCausaSolicitud2());
        obj.setCausaSolicitud3(ent.getCausaSolicitud3());
        obj.setCausaSolicitud4(ent.getCausaSolicitud4());
        obj.setCausaSolicitud5(ent.getCausaSolicitud5());
        obj.setCausaSolicitud6(ent.getCausaSolicitud6());
        obj.setCausaSolicitud7(ent.getCausaSolicitud7());
        obj.setCodigoPeriodoDuracionTratamiento(ent.getCodigoPeriodoDuracionTratamiento());
        obj.setCodigoRazonCausa52(ent.getCodigoRazonCausa52());
        obj.setCodigoUnidadTiempoFrecuenciaUso(ent.getCodigoUnidadTiempoFrecuenciaUso());
        obj.setConsecutivoOrden(ent.getConsecutivoOrden());
        obj.setDescripcionRazon51(ent.getDescripcionRazon51());
        obj.setDescripcionRazon52(ent.getDescripcionRazon52());
        obj.setEntregados(ent.getEntregados());
        obj.setEstado(ent.getEstado());
        obj.setEstadoJuntaProfesionales(ent.getEstadoJuntaProfesionales());
        obj.setFechaDireccionamiento(ent.getFechaDireccionamiento());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setFrecuenciaDeUso(ent.getFrecuenciaDeUso());
        obj.setId(ent.getId());
        obj.setIdDireccionamiento(ent.getIdDireccionamiento());
        obj.setIdTransaccion(ent.getIdTransaccion());
        obj.setIndicacionesPaciente(ent.getIndicacionesPaciente());
        obj.setJustificacionNoPbs(ent.getJustificacionNoPbs());
        obj.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        obj.setMaTecnologiaId(ent.getMaTecnologiaId());
        obj.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        if (ent.getMpPrescripcionId() != null) {
            obj.setMpPrescripcion(new MpPrescripcion(ent.getMpPrescripcionId().getId()));
        }
        obj.setPendientes(ent.getPendientes());
        obj.setRazonCausaSolicitud51(ent.getRazonCausaSolicitud51());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setTipoPrestacion(ent.getTipoPrestacion());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setFechaMaximaEntrega(ent.getFechaMaximaEntrega());
        obj.setProcePbsDescartado(ent.getProcePbsDescartado());
        obj.setProcRealizaraCol(ent.getProcRealizaraCol());
        return obj;
    }

    @Override
    public List<MpPrescripcionInsumo> consultarPorMpPrescripcionInsumo(int mpPrescripcionId) throws Exception {
        List<MpPrescripcionInsumo> listaResultado = new ArrayList<>();

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT mi FROM MpPrescripcionInsumos mi WHERE mi.mpPrescripcionId.id = :id");
            List<MpPrescripcionInsumos> lista = getEntityManager().createQuery(strQuery.toString()).setParameter("id", mpPrescripcionId).getResultList();
            lista.forEach(mpInsumo -> listaResultado.add(castEntidadNegocio(mpInsumo)));
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    @Override
    public List<MpPrescripcionInsumo> consultarPorMpPrescripcionInsumoH(int mpPrescripcionId) throws Exception {
        List<MpPrescripcionInsumo> listaResultado = new ArrayList<>();

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT mi FROM MpPrescripcionInsumos mi WHERE mi.mpPrescripcionId.id = :id");
            List<MpPrescripcionInsumos> MP = getEntityManager().createQuery(strQuery.toString()).setParameter("id", mpPrescripcionId).getResultList();
            for (MpPrescripcionInsumos ins : MP) {
                MpPrescripcionInsumo obj = new MpPrescripcionInsumo();
                obj.setId(ins.getId());
                obj.setTipoTecnologia(ins.getTipoTecnologia());
                listaResultado.add(obj);
            }
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    @Override
    public List<MpPrescripcionInsumo> consultarPorMpPrescripcionInsumoPlanManejo(int mpPrescripcionId) throws Exception {
        List<MpPrescripcionInsumo> listaResultado = new ArrayList<>();

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT mi FROM MpPrescripcionInsumos mi WHERE mi.mpPrescripcionId.id = :id");
            List<MpPrescripcionInsumos> lista = getEntityManager().createQuery(strQuery.toString()).setParameter("id", mpPrescripcionId).getResultList();
            lista.forEach(mpInsumo -> listaResultado.add(castEntidadNegocioPlanManejo(mpInsumo)));
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    @Override
    public List<MpPrescripcionProgramada> consultarListaProgramada(int prescripcion, int item, int tipoTecnologia) throws Exception {
        List<MpPrescripcionProgramada> listaResultado = new ArrayList<>();

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT pP FROM MpPrescripcionProgramadas pP WHERE pP.mpPrescripcionesId.id = :id");

            int tipoTecnologiaInt = (tipoTecnologia);

            switch (tipoTecnologiaInt) {
                case 1:
                case 4:
                    strQuery.append(" AND pP.mpPrescripcionMedicamentosId.id = :itemId");
                    break;
                case 2:
                    strQuery.append(" AND pP.mpPrescripcionTecnologiasId.id = :itemId");
                    break;
                case 3:
                case 5:
                    strQuery.append(" AND pP.mpPrescripcionInsumosId.id = :itemId");
                    break;
                default:
                    break;
            }

            List<MpPrescripcionProgramadas> lista = getEntityManager()
                    .createQuery(strQuery.toString())
                    .setParameter("id", prescripcion)
                    .setParameter("itemId", item)
                    .getResultList();

            lista.forEach(mpInsumo -> listaResultado.add(castEntidadNegocioProgramada(mpInsumo)));
        } catch (Exception e) {

            throw new Exception("Error al consultar la lista programada", e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    public static MpPrescripcionProgramada castEntidadNegocioProgramada(MpPrescripcionProgramadas ent) {
        MpPrescripcionProgramada obj = new MpPrescripcionProgramada();
        obj.setEstado(ent.getEstado());
        obj.setMaeTipoDocumentoPrestadorValor(ent.getMaeTipoDocumentoPrestadorValor());
        obj.setPrestadorNumeroDocumento(ent.getPrestadorNumeroDocumento());
        obj.setPrestadorRazonSocial(ent.getPrestadorRazonSocial());
        obj.setFechaDireccionamiento(ent.getFechaDireccionamiento());
        obj.setFechaMaximaEntrega(ent.getFechaMaxEntrega());
        obj.setEntregaNumero(ent.getEntregaNumero());
        obj.setEntregaCantidad(ent.getEntregaCantidad());
        obj.setEntregadoTotal(ent.getEntregadoTotal());
        obj.setEntregadoPendiente(ent.getEntregadoPendiente());
        return obj;
    }

    @Override
    public List<MpProgramadaEntrega> consultarListaEntrega(int prescripcion, int item, int tipoTecnologia) throws Exception {
        List<MpProgramadaEntrega> listaResultado = new ArrayList<>();

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT pE FROM MpProgramadaEntregas pE WHERE pE.mpPrescripcionesId.id = :id");

            int tipoTecnologiaInt = (tipoTecnologia);

            switch (tipoTecnologiaInt) {
                case 1:
                case 4:
                    strQuery.append(" AND pE.mpPrescripcionMedicamentosId.id = :itemId");
                    break;
                case 2:
                    strQuery.append(" AND pE.mpPrescripcionTecnologiasId.id = :itemId");
                    break;
                case 3:
                case 5:
                    strQuery.append(" AND pE.mpPrescripcionInsumosId.id = :itemId");
                    break;
                default:
                    break;
            }

            List<MpProgramadaEntregas> lista = getEntityManager()
                    .createQuery(strQuery.toString())
                    .setParameter("id", prescripcion)
                    .setParameter("itemId", item)
                    .getResultList();

            lista.forEach(mpInsumo -> listaResultado.add(castEntidadNegocioEntregas(mpInsumo)));
        } catch (Exception e) {

            throw new Exception("Error al consultar la lista programada", e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    public static MpProgramadaEntrega castEntidadNegocioEntregas(MpProgramadaEntregas ent) {
        MpProgramadaEntrega obj = new MpProgramadaEntrega();
        obj.setEstado(ent.getEstado());
        obj.setIdReporteEntrega(ent.getIdReporteEntrega());
        obj.setCantidad(ent.getCantidad());
        obj.setNumeroEntrega(ent.getNumeroEntrega());
        obj.setEntregaTotal(ent.getEntregaTotal());
        obj.setCausaNoEntrega(ent.getCausaNoEntrega());
        return obj;
    }

    public static MpPrescripcionInsumo castEntidadNegocio(MpPrescripcionInsumos ent) {
        MpPrescripcionInsumo obj = new MpPrescripcionInsumo();
        obj.setCantidad(ent.getCantidad());
        obj.setCantidadFormulada(ent.getCantidadFormulada());
        obj.setCantidadTotalEntrega(ent.getCantidadTotalEntrega());
        obj.setCausaSolicitud1(ent.getCausaSolicitud1());
        obj.setCausaSolicitud2(ent.getCausaSolicitud2());
        obj.setCausaSolicitud3(ent.getCausaSolicitud3());
        obj.setCausaSolicitud4(ent.getCausaSolicitud4());
        obj.setCausaSolicitud5(ent.getCausaSolicitud5());
        obj.setCodigoDispositivo(ent.getCodigoDispositivo());
        obj.setCodigoForma(ent.getCodigoForma());
        obj.setCodigoMunicipioDestinoAlb(ent.getCodigoMunicipioDestinoAlb());
        obj.setCodigoMunicipioOrigenAlb(ent.getCodigoMunicipioOrigenAlb());
        obj.setCodigoServicioComplementario(ent.getCodigoServicioComplementario());
        obj.setConsecutivoOrden(ent.getConsecutivoOrden());
        obj.setDescripcionCausa4(ent.getDescripcionCausa4());
        obj.setDescripcionCausaS4(ent.getDescripcionCausaS4());
        obj.setDuracionTratamiento(ent.getDuracionTratamiento());
        obj.setEntregados(ent.getEntregados());
        obj.setEstado(ent.getEstado());
        obj.setEstadoJuntaProfesionales(ent.getEstadoJuntaProfesionales());
        obj.setFechaDireccionamiento(ent.getFechaDireccionamiento());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setFrecuenciaUso(ent.getFrecuenciaUso());
        obj.setId(ent.getId());
        obj.setIdDireccionamiento(ent.getIdDireccionamiento());
        obj.setIdTransaccion(ent.getIdTransaccion());
        obj.setIndicacionesRecomendaciones(ent.getIndicacionesRecomendaciones());
        obj.setJustificacionNoPbs(ent.getJustificacionNoPbs());
        if (ent.getMpPrescripcionId() != null) {
            obj.setMpPrescripcion(new MpPrescripcion(ent.getMpPrescripcionId().getId()));
        }
        obj.setNombreAlb(ent.getNombreAlb());
        obj.setNombreTecnologiaAvalada(ent.getNombreTecnologiaAvalada());
        obj.setNumeroDocumentoAcompanante(ent.getNumeroDocumentoAcompanante());
        obj.setParentezcoAcompanante(ent.getParentezcoAcompanante());
        obj.setPendiente(ent.getPendiente());
        obj.setReqAcom(ent.getReqAcom());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setTipoDocumentoAcompanante(ent.getTipoDocumentoAcompanante());
        obj.setTipoDocumentoAcompananteAlbergue(ent.getTipoDocumentoAcompananteAlbergue());
        obj.setTipoPrestacion(ent.getTipoPrestacion());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setTipoTransporte(ent.getTipoTransporte());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setFechaMaximaEntrega(ent.getFechaMaximaEntrega());
        if (ent.getMaeDispositivosCodigo() != null) {
            obj.setMaeDispositivosCodigo(ent.getMaeDispositivosCodigo());
            obj.setMaeDispositivosNombre(ent.getMaedispositivosValor());
        }
        if (ent.getMaeServiciosComplementariosCodigo() != null) {
            obj.setMaeServiciosComplementariosCodigo(ent.getMaeServiciosComplementariosCodigo());
            obj.setMaeServiciosComplementariosNombre(ent.getMaeServiciosComplementariosValor());
        }
        return obj;
    }

    public static MpPrescripcionInsumo castEntidadNegocioPlanManejo(MpPrescripcionInsumos ent) {
        MpPrescripcionInsumo obj = new MpPrescripcionInsumo();
        obj.setCantidad(ent.getCantidad());
        obj.setCantidadFormulada(ent.getCantidadFormulada());
        obj.setCantidadTotalEntrega(ent.getCantidadTotalEntrega());
        obj.setCodPerDurTrat(ent.getCodPerDurTrat());
        obj.setCodFrecuenciaUso(ent.getCodFrecuenciaUso());
        obj.setFrecuenciaUso(ent.getFrecuenciaUso());
        obj.setIndicacionesRecomendaciones(ent.getIndicacionesRecomendaciones());
        obj.setId(ent.getId());
        if (ent.getMpPrescripcionId() != null) {
            MpPrescripcion prescripcion = new MpPrescripcion();
            if (ent.getMpPrescripcionId().getAsegAfiliadosId() != null) {
                AsegAfiliado afiliado = new AsegAfiliado();
                afiliado.setId(ent.getMpPrescripcionId().getAsegAfiliadosId().getId());
                afiliado.setNumeroDocumento(ent.getMpPrescripcionId().getAsegAfiliadosId().getNumeroDocumento());
                afiliado.setPrimerNombre(ent.getMpPrescripcionId().getAsegAfiliadosId().getPrimerNombre());
                afiliado.setSegundoNombre(ent.getMpPrescripcionId().getAsegAfiliadosId().getSegundoNombre());
                afiliado.setPrimerApellido(ent.getMpPrescripcionId().getAsegAfiliadosId().getPrimerApellido());
                afiliado.setSegundoApellido(ent.getMpPrescripcionId().getAsegAfiliadosId().getSegundoApellido());
                afiliado.setRegimen(ent.getMpPrescripcionId().getAsegAfiliadosId().getRegimen());
                prescripcion.setAsegAfiliado(afiliado);
            }
            if (ent.getMpPrescripcionId().getCntProfesionalesId() != null) {
                CntProfesional profecional = new CntProfesional();
                profecional.setId(ent.getMpPrescripcionId().getCntProfesionalesId().getId());
                profecional.setDocumento(ent.getMpPrescripcionId().getCntProfesionalesId().getDocumento());
                profecional.setPrimerNombre(ent.getMpPrescripcionId().getCntProfesionalesId().getPrimerNombre());
                profecional.setSegundoNombre(ent.getMpPrescripcionId().getCntProfesionalesId().getSegundoNombre());
                profecional.setPrimerApellido(ent.getMpPrescripcionId().getCntProfesionalesId().getPrimerApellido());
                profecional.setSegundoApellido(ent.getMpPrescripcionId().getCntProfesionalesId().getSegundoApellido());
                profecional.setRegistroMedico(ent.getMpPrescripcionId().getCntProfesionalesId().getRegistroMedico());
                prescripcion.setCntProfesional(profecional);
            }
            prescripcion.setId(ent.getMpPrescripcionId().getId());
            prescripcion.setNumeroPrescripcion(ent.getMpPrescripcionId().getNumeroPrescripcion());
            prescripcion.setMaDiagnosticoPrincipalValor(ent.getMpPrescripcionId().getMaDiagnosticoPrincipalValor());
            prescripcion.setCodAmbAte(ent.getMpPrescripcionId().getCodAmbAte());
            prescripcion.setSedeCodigoHabilitacion(ent.getMpPrescripcionId().getSedeCodigoHabilitacion());
            prescripcion.setPrestadorNumeroDocumento(ent.getMpPrescripcionId().getPrestadorNumeroDocumento());
            prescripcion.setPrestadorRazonSocial(ent.getMpPrescripcionId().getPrestadorRazonSocial());
            obj.setMpPrescripcion(prescripcion);
        }

        return obj;
    }

    public static MpPrescripcionMedicamento castEntidadNegocioCorto(MpPrescripcionMedicamentos ent) {
        MpPrescripcionMedicamento obj = new MpPrescripcionMedicamento();
        obj.setId(ent.getId());
        if (ent.getMpPrescripcionesId() != null) {
            MpPrescripcion mpPrescripcion = new MpPrescripcion(ent.getMpPrescripcionesId().getId());
            mpPrescripcion.setMaDiagnosticoPrincipalCodigo(ent.getMpPrescripcionesId().getMaDiagnosticoPrincipalCodigo());
            mpPrescripcion.setMaDiagnosticoPrincipalValor(ent.getMpPrescripcionesId().getMaDiagnosticoPrincipalValor());
            mpPrescripcion.setEnfermedadHuerfana(ent.getMpPrescripcionesId().getEnfermedadHuerfana());
            mpPrescripcion.setDiagnosticoEnfermedadHuerfana(ent.getMpPrescripcionesId().getDiagnosticoEnfermedadHuerfana());
            mpPrescripcion.setCodigoEnfermedadHuerfana(ent.getMpPrescripcionesId().getCodigoEnfermedadHuerfana());
            mpPrescripcion.setMaDiagnosticoRelacionado1Codigo(ent.getMpPrescripcionesId().getMaDiagnosticoRelacionado1Codigo());
            mpPrescripcion.setMaDiagnosticoRelacionado1Valor(ent.getMpPrescripcionesId().getMaDiagnosticoRelacionado1Valor());
            mpPrescripcion.setMaDiagnosticoRelacionado2Codigo(ent.getMpPrescripcionesId().getMaDiagnosticoRelacionado2Codigo());
            mpPrescripcion.setMaDiagnosticoRelacionado2Valor(ent.getMpPrescripcionesId().getMaDiagnosticoRelacionado2Valor());

            obj.setMpPrescripcion(mpPrescripcion);
        }
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setMaeProductosNutricionalesValor(ent.getMaeProductosNutricionalesValor());
        obj.setDescripcionMedicamentoPrincipioActivo(ent.getDescripcionMedicamentoPrincipioActivo());
        obj.setUnidadFarmaceuticaCantidadTotal(ent.getUnidadFarmaceuticaCantidadTotal());
        obj.setCodigoViaAdministracion(ent.getCodigoViaAdministracion());
        obj.setNumeroFrecuenciaAdministracion(ent.getNumeroFrecuenciaAdministracion());
        obj.setCodigoFrecuenciaAdministracion(ent.getCodigoFrecuenciaAdministracion());
        obj.setDosis(ent.getDosis());
        obj.setDosisUnidadMedida(ent.getDosisUnidadMedida());
        obj.setCantidadTratamiento(ent.getCantidadTratamiento());
        obj.setDuracionTratamiento(ent.getDuracionTratamiento());
        obj.setCantidadTotalFormulada(ent.getCantidadTotalFormulada());
        obj.setIndicacionesEspeciales(ent.getIndicacionesEspeciales());
        obj.setIndicacionRecibida(ent.getIndicacionRecibida());
        obj.setJustificacionNoPbs(ent.getJustificacionNoPbs());
        obj.setConsecutivoOrden(ent.getConsecutivoOrden());
        obj.setTipoPrestacion(ent.getTipoPrestacion());
        obj.setEnFaseExperimental(ent.getEnFaseExperimental());
        obj.setUtilizoNutriMedExistentePbsUpc(ent.getUtilizoNutriMedExistentePbsUpc());
        obj.setCubiertoPbsCargoUpc(ent.getCubiertoPbsCargoUpc());
        obj.setDescartoReaccionesAdversas(ent.getDescartoReaccionesAdversas());
        obj.setResultSatisPrev(ent.getResultSatisPrev());
        obj.setRegistroInvima(ent.getRegistroInvima());

        obj.setEstadoJuntaProfesionales(ent.getEstadoJuntaProfesionales());
        obj.setNumActaJunta(ent.getNumActaJunta());
        obj.setConsecutivoJuntaTecnologia(ent.getConsecutivoJuntaTecnologia());
        obj.setModJunta(ent.getModJunta());
        obj.setFechaActaJunta(ent.getFechaActaJunta());
        obj.setJustificacionTecJunta(ent.getJustificacionTecJunta());

        obj.setUsuarioAtiende(ent.getUsuarioAtiende());
        obj.setTerminalAtiende(ent.getTerminalAtiende());
        obj.setFechaHoraAtiende(ent.getFechaHoraAtiende());
        obj.setAtendido(ent.getAtendido());

        obj.setDescMedPbsUpc(ent.getDescMedPbsUpc());
        obj.setDescartoReaccionesAdversas(ent.getDescartoReaccionesAdversas());
        obj.setDescartoEvidenciaEfiEfecClinica(ent.getDescartoEvidenciaEfiEfecClinica());
        obj.setTipoProductoNutricional(ent.getTipoProductoNutricional());
        obj.setEsDiagnosticoVih(ent.getEsDiagnosticoVih());
        obj.setEsDiagnosticoCancer(ent.getEsDiagnosticoCancer());
        obj.setEsDiagnosticoDesnutricion(ent.getEsDiagnosticoDesnutricion());

        return obj;
    }

    public static MpPrescripcionMedicamento castEntidadNegocioM(MpPrescripcionMedicamentos ent) {
        MpPrescripcionMedicamento obj = new MpPrescripcionMedicamento();
        obj.setId(ent.getId());
        if (ent.getMpPrescripcionesId() != null) {
            MpPrescripcion mpPrescripcion = new MpPrescripcion(ent.getMpPrescripcionesId().getId());
            mpPrescripcion.setMaDiagnosticoPrincipalCodigo(ent.getMpPrescripcionesId().getMaDiagnosticoPrincipalCodigo());
            mpPrescripcion.setMaDiagnosticoPrincipalValor(ent.getMpPrescripcionesId().getMaDiagnosticoPrincipalValor());
            mpPrescripcion.setEnfermedadHuerfana(ent.getMpPrescripcionesId().getEnfermedadHuerfana());
            mpPrescripcion.setDiagnosticoEnfermedadHuerfana(ent.getMpPrescripcionesId().getDiagnosticoEnfermedadHuerfana());
            mpPrescripcion.setCodigoEnfermedadHuerfana(ent.getMpPrescripcionesId().getCodigoEnfermedadHuerfana());
            mpPrescripcion.setMaDiagnosticoRelacionado1Codigo(ent.getMpPrescripcionesId().getMaDiagnosticoRelacionado1Codigo());
            mpPrescripcion.setMaDiagnosticoRelacionado1Valor(ent.getMpPrescripcionesId().getMaDiagnosticoRelacionado1Valor());
            mpPrescripcion.setMaDiagnosticoRelacionado2Codigo(ent.getMpPrescripcionesId().getMaDiagnosticoRelacionado2Codigo());
            mpPrescripcion.setMaDiagnosticoRelacionado2Valor(ent.getMpPrescripcionesId().getMaDiagnosticoRelacionado2Valor());

            obj.setMpPrescripcion(mpPrescripcion);
        }
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setDescripcionMedicamentoPrincipioActivo(ent.getDescripcionMedicamentoPrincipioActivo());
        obj.setUnidadFarmaceuticaCantidadTotal(ent.getUnidadFarmaceuticaCantidadTotal());
        obj.setCodigoViaAdministracion(ent.getCodigoViaAdministracion());
        obj.setNumeroFrecuenciaAdministracion(ent.getNumeroFrecuenciaAdministracion());
        obj.setCodigoFrecuenciaAdministracion(ent.getCodigoFrecuenciaAdministracion());
        obj.setDosis(ent.getDosis());
        obj.setDosisUnidadMedida(ent.getDosisUnidadMedida());
        obj.setCantidadTratamiento(ent.getCantidadTratamiento());
        obj.setDuracionTratamiento(ent.getDuracionTratamiento());
        obj.setCantidadTotalFormulada(ent.getCantidadTotalFormulada());
        obj.setIndicacionesEspeciales(ent.getIndicacionesEspeciales());
        obj.setIndicacionRecibida(ent.getIndicacionRecibida());
        obj.setJustificacionNoPbs(ent.getJustificacionNoPbs());
        obj.setConsecutivoOrden(ent.getConsecutivoOrden());
        obj.setTipoPrestacion(ent.getTipoPrestacion());
        obj.setEnFaseExperimental(ent.getEnFaseExperimental());
        obj.setRegistroInvima(ent.getRegistroInvima());
        obj.setUtilizoNutriMedExistentePbsUpc(ent.getUtilizoNutriMedExistentePbsUpc());
        obj.setCubiertoPbsCargoUpc(ent.getCubiertoPbsCargoUpc());
        obj.setDescartoReaccionesAdversas(ent.getDescartoReaccionesAdversas());
        obj.setResultSatisPrev(ent.getResultSatisPrev());
        obj.setTipoMedicamento(ent.getTipoMedicamento());
        obj.setListaNoUsoSanitarioUnirs(ent.getListaNoUsoSanitarioUnirs());
        obj.setDescartoAlternativa(ent.getDescartoAlternativa());
        obj.setRegAprobAutClin(ent.getRegAprobAutClin());
        obj.setCodigoFormulaFarmaceutica(ent.getCodigoFormulaFarmaceutica());

        obj.setEstadoJuntaProfesionales(ent.getEstadoJuntaProfesionales());
        obj.setNumActaJunta(ent.getNumActaJunta());
        obj.setConsecutivoJuntaTecnologia(ent.getConsecutivoJuntaTecnologia());
        obj.setModJunta(ent.getModJunta());
        obj.setFechaActaJunta(ent.getFechaActaJunta());
        obj.setJustificacionTecJunta(ent.getJustificacionTecJunta());

        obj.setUsuarioAtiende(ent.getUsuarioAtiende());
        obj.setTerminalAtiende(ent.getTerminalAtiende());
        obj.setFechaHoraAtiende(ent.getFechaHoraAtiende());
        obj.setAtendido(ent.getAtendido());
        return obj;
    }

    public static MpPrescripcionTecnologia castEntidadNegocioLargo(MpPrescripcionTecnologias ent) {
        MpPrescripcionTecnologia obj = new MpPrescripcionTecnologia();
        obj.setId(ent.getId());
        if (ent.getMpPrescripcionId() != null) {
            MpPrescripcion mpPrescripcion = new MpPrescripcion(ent.getMpPrescripcionId().getId());
            mpPrescripcion.setMaDiagnosticoPrincipalCodigo(ent.getMpPrescripcionId().getMaDiagnosticoPrincipalCodigo());
            mpPrescripcion.setMaDiagnosticoPrincipalValor(ent.getMpPrescripcionId().getMaDiagnosticoPrincipalValor());
            mpPrescripcion.setEnfermedadHuerfana(ent.getMpPrescripcionId().getEnfermedadHuerfana());
            mpPrescripcion.setDiagnosticoEnfermedadHuerfana(ent.getMpPrescripcionId().getDiagnosticoEnfermedadHuerfana());
            mpPrescripcion.setCodigoEnfermedadHuerfana(ent.getMpPrescripcionId().getCodigoEnfermedadHuerfana());
            mpPrescripcion.setMaDiagnosticoRelacionado1Codigo(ent.getMpPrescripcionId().getMaDiagnosticoRelacionado1Codigo());
            mpPrescripcion.setMaDiagnosticoRelacionado1Valor(ent.getMpPrescripcionId().getMaDiagnosticoRelacionado1Valor());
            mpPrescripcion.setMaDiagnosticoRelacionado2Codigo(ent.getMpPrescripcionId().getMaDiagnosticoRelacionado2Codigo());
            mpPrescripcion.setMaDiagnosticoRelacionado2Valor(ent.getMpPrescripcionId().getMaDiagnosticoRelacionado2Valor());

            obj.setMpPrescripcion(mpPrescripcion);
        }
        obj.setIdTransaccion(ent.getIdTransaccion());
        obj.setIdDireccionamiento(ent.getIdDireccionamiento());
        obj.setEstado(ent.getEstado());
        obj.setConsecutivoOrden(ent.getConsecutivoOrden());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setTipoPrestacion(ent.getTipoPrestacion());
        obj.setCausaSolicitud2(ent.getCausaSolicitud2());
        obj.setCausaSolicitud3(ent.getCausaSolicitud3());
        obj.setCausaSolicitud4(ent.getCausaSolicitud4());
        obj.setCausaSolicitud5(ent.getCausaSolicitud5());
        obj.setCausaSolicitud6(ent.getCausaSolicitud6());
        obj.setCausaSolicitud7(ent.getCausaSolicitud7());
        obj.setCausaSolicitud11(ent.getCausaSolicitud11());
        obj.setCausaSolicitud12(ent.getCausaSolicitud12());
        obj.setCodigoRazonCausa52(ent.getCodigoRazonCausa52());
        obj.setDescripcionRazon52(ent.getDescripcionRazon52());
        obj.setRazonCausaSolicitud51(ent.getRazonCausaSolicitud51());
        obj.setDescripcionRazon51(ent.getDescripcionRazon51());
        obj.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        obj.setMaTecnologiaId(ent.getMaTecnologiaId());
        obj.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        obj.setCantidadFormulada(ent.getCantidadFormulada());
        obj.setFrecuenciaDeUso(ent.getFrecuenciaDeUso());
        obj.setCodigoUnidadTiempoFrecuenciaUso(ent.getCodigoUnidadTiempoFrecuenciaUso());
        obj.setCantidadDuracionTratamiento(ent.getCantidadDuracionTratamiento());
        obj.setCantidadTotal(ent.getCantidadTotal());
        obj.setCodigoPeriodoDuracionTratamiento(ent.getCodigoPeriodoDuracionTratamiento());
        obj.setJustificacionNoPbs(ent.getJustificacionNoPbs());
        obj.setIndicacionesPaciente(ent.getIndicacionesPaciente());
        obj.setCantidadTotalEntrega(ent.getCantidadTotalEntrega());
        obj.setEntregados(ent.getEntregados());
        obj.setPendientes(ent.getPendientes());
        obj.setFechaMaximaEntrega(ent.getFechaMaximaEntrega());
        obj.setFechaDireccionamiento(ent.getFechaDireccionamiento());

        obj.setEstadoJuntaProfesionales(ent.getEstadoJuntaProfesionales());
        obj.setNumActaJunta(ent.getNumActaJunta());
        obj.setConsecutivoJuntaTecnologia(ent.getConsecutivoJuntaTecnologia());
        obj.setModJunta(ent.getModJunta());
        obj.setFechaActaJunta(ent.getFechaActaJunta());
        obj.setJustificacionTecJunta(ent.getJustificacionTecJunta());

        obj.setUsuarioAtiende(ent.getUsuarioAtiende());
        obj.setTerminalAtiende(ent.getTerminalAtiende());
        obj.setFechaHoraAtiende(ent.getFechaHoraAtiende());
        obj.setAtendido(ent.getAtendido());
        return obj;
    }

    public static MpPrescripcionInsumo castEntidadNegocioInsumo(MpPrescripcionInsumos ent) {
        MpPrescripcionInsumo obj = new MpPrescripcionInsumo();
        obj.setId(ent.getId());
        if (ent.getMpPrescripcionId() != null) {
            MpPrescripcion mpPrescripcion = new MpPrescripcion(ent.getMpPrescripcionId().getId());
            mpPrescripcion.setMaDiagnosticoPrincipalCodigo(ent.getMpPrescripcionId().getMaDiagnosticoPrincipalCodigo());
            mpPrescripcion.setMaDiagnosticoPrincipalValor(ent.getMpPrescripcionId().getMaDiagnosticoPrincipalValor());
            mpPrescripcion.setEnfermedadHuerfana(ent.getMpPrescripcionId().getEnfermedadHuerfana());
            mpPrescripcion.setDiagnosticoEnfermedadHuerfana(ent.getMpPrescripcionId().getDiagnosticoEnfermedadHuerfana());
            mpPrescripcion.setCodigoEnfermedadHuerfana(ent.getMpPrescripcionId().getCodigoEnfermedadHuerfana());
            mpPrescripcion.setMaDiagnosticoRelacionado1Codigo(ent.getMpPrescripcionId().getMaDiagnosticoRelacionado1Codigo());
            mpPrescripcion.setMaDiagnosticoRelacionado1Valor(ent.getMpPrescripcionId().getMaDiagnosticoRelacionado1Valor());
            mpPrescripcion.setMaDiagnosticoRelacionado2Codigo(ent.getMpPrescripcionId().getMaDiagnosticoRelacionado2Codigo());
            mpPrescripcion.setMaDiagnosticoRelacionado2Valor(ent.getMpPrescripcionId().getMaDiagnosticoRelacionado2Valor());

            obj.setMpPrescripcion(mpPrescripcion);
        }
        obj.setMaeServiciosComplementariosNombre(ent.getMaeServiciosComplementariosValor());
        obj.setCantidadFormulada(ent.getCantidadFormulada());
        obj.setCantidadTotalEntrega(ent.getCantidadTotalEntrega());
        obj.setDescripcionCausa4(ent.getDescripcionCausa4());
        obj.setTipoPrestacion(ent.getTipoPrestacion());
        obj.setJustificacionNoPbs(ent.getJustificacionNoPbs());
        obj.setConsecutivoOrden(ent.getConsecutivoOrden());
        obj.setEstadoJuntaProfesionales(ent.getEstadoJuntaProfesionales());
        obj.setTipoTransporte(ent.getTipoTransporte());
        obj.setFrecuenciaUso(ent.getFrecuenciaUso());
        obj.setCodFrecuenciaUso(ent.getCodFrecuenciaUso());
        obj.setCantidad(ent.getCantidad());
        obj.setCodPerDurTrat(ent.getCodPerDurTrat());
        obj.setIndicacionesRecomendaciones(ent.getIndicacionesRecomendaciones());
        obj.setJustificacionNoPbs(ent.getJustificacionNoPbs());
        obj.setConsecutivoOrden(ent.getConsecutivoOrden());
        obj.setTipoPrestacion(ent.getTipoPrestacion());
        obj.setEstadoJuntaProfesionales(ent.getEstadoJuntaProfesionales());
        obj.setUsoServCosmeticoSuntuario(ent.getUsoServCosmeticoSuntuario());
        obj.setServicioPrestaraColombia(ent.getServicioPrestaraColombia());
        obj.setServRegistradoAutCompetente(ent.getServRegistradoAutCompetente());
        obj.setServCondicionClinDiagPaciente(ent.getServCondicionClinDiagPaciente());
        obj.setEvidenciaEfiEfecClinica(ent.getEvidenciaEfiEfecClinica());
        obj.setMaeDispositivosNombre(ent.getMaedispositivosValor());
        obj.setMaeDispositivosCodigo(ent.getMaeDispositivosCodigo());
        obj.setTipoTecnologia(ent.getTipoTecnologia());

        obj.setEstadoJuntaProfesionales(ent.getEstadoJuntaProfesionales());
        obj.setNumActaJunta(ent.getNumActaJunta());
        obj.setConsecutivoJuntaTecnologia(ent.getConsecutivoJuntaTecnologia());
        obj.setModJunta(ent.getModalidadJunta());
        obj.setFechaActaJunta(ent.getFechaActaJunta());
        obj.setJustificacionTecJunta(ent.getJustificacionTecJunta());
        obj.setDescripcionServicioComplementario(ent.getDescripcionServicioComplementario());

        obj.setUsuarioAtiende(ent.getUsuarioAtiende());
        obj.setTerminalAtiende(ent.getTerminalAtiende());
        obj.setFechaHoraAtiende(ent.getFechaHoraAtiende());
        obj.setAtendido(ent.getAtendido());
        return obj;
    }

    @Override
    public List<MpMedicamentoPrincipioActivo> consultarPrincipioActivo(int item) throws Exception {
        List<MpMedicamentoPrincipioActivo> listaResultado = new ArrayList<>();

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT mt FROM MpMedicamentoPrincipiosActivos mt WHERE mt.mpPrescripcionMedicamentosId.id = :id");
            List<MpMedicamentoPrincipiosActivos> lista = getEntityManager().createQuery(strQuery.toString()).setParameter("id", item).getResultList();
            lista.forEach(mpPActivo -> listaResultado.add(castEntidadNegocioPA(mpPActivo)));
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    @Override
    public List<MpMedicamentoIndicacionUnirs> consultarUnirs(int item) throws Exception {
        List<MpMedicamentoIndicacionUnirs> listaResultado = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT mt FROM MpMedicamentoIndicacionesUnirs mt WHERE mt.mpPrescripcionMedicamentosId.id = :id");
            List<MpMedicamentoIndicacionesUnirs> lista = getEntityManager().createQuery(strQuery.toString()).setParameter("id", item).getResultList();
            lista.forEach(mpUnirs -> listaResultado.add(castEntidadNegocioUnirs(mpUnirs)));
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    public static MpMedicamentoIndicacionUnirs castEntidadNegocioUnirs(MpMedicamentoIndicacionesUnirs per) {
        MpMedicamentoIndicacionUnirs obj = new MpMedicamentoIndicacionUnirs();
        obj.setId(per.getId());
        obj.setCodigoIndicacion(per.getCodigoIndicacion());
        if (per.getMpCodigoUnirsId() != null) {
            MpCodigoUnirss unirs = new MpCodigoUnirss();
            unirs.setId(per.getMpCodigoUnirsId().getId());
            unirs.setIndicacion(per.getMpCodigoUnirsId().getIndicacion());
            obj.setMpCodigoUnirsId(unirs);
        }
        return obj;
    }

    public static MpMedicamentoPrincipioActivo castEntidadNegocioPA(MpMedicamentoPrincipiosActivos ent) {
        MpMedicamentoPrincipioActivo obj = new MpMedicamentoPrincipioActivo();
        obj.setConsecutivoOrden(ent.getConsecutivoOrden());
        obj.setCodigoPrincipioActivo(ent.getCodigoPrincipioActivo());
        obj.setConcecutivoCantidad(ent.getConcecutivoCantidad());
        obj.setUnidadMedidaConcentracion(ent.getUnidadMedidaConcentracion());
        obj.setCantidadContenido(ent.getCantidadContenido());
        obj.setUnidadCantidadContenido(ent.getUnidadCantidadContenido());

        return obj;
    }

    @Override
    public int consultarCantidadListaPrestador(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p.id) FROM CntPrestadores p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeTipoDocumentoId":
                            strQuery += "AND p.maeTipoDocumentoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "documento":
                            strQuery += "AND p.documento LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "nombres":
                            strQuery += "AND p.nombres LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "apellidos":
                            strQuery += "AND p.apellidos LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "telefono":
                            strQuery += "AND p.telefono LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "razonSocial":
                            strQuery += "AND p.razonSocial LIKE '%" + (String) e.getValue() + "%'";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
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
    public int consultarCantidadListaPrestadorSede(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM CntPrestadorSedes p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoHabilitacionSede":
                            strQuery += " AND p.codigoHabilitacion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "codigoPrestador":
                            strQuery += " AND p.codigoPrestador LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreSede":
                            strQuery += " AND p.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "direccion":
                            strQuery += " AND p.direccion LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
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
    public List<CntPrestadorSede> consultarListaPrestadorSede(ParamConsulta paramConsulta) throws Exception {
        List<CntPrestadorSede> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM CntPrestadorSedes p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoHabilitacionSede":
                            strQuery += " AND p.codigoHabilitacion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "codigoPrestador":
                            strQuery += " AND p.codigoPrestador LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreSede":
                            strQuery += " AND p.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "direccion":
                            strQuery += " AND p.direccion LIKE '%" + e.getValue() + "%' ";
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
            List<CntPrestadorSedes> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntPrestadorSedes per : list) {
                listResult.add(castEntidadNegocioPrestadorSede(per));
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

    public static CntPrestadorSede castEntidadNegocioPrestadorSede(CntPrestadorSedes per) {
        CntPrestadorSede obj = new CntPrestadorSede();
        obj.setId(per.getId());
        obj.setUbicacionId(per.getUbicacionId());
        obj.setCodigoPrestador(per.getCodigoPrestador());
        obj.setDireccion(per.getDireccion());
        obj.setNombreSede(per.getNombre());
        obj.setCodigoSede(per.getCodigo());
        obj.setCodigoHabilitacionSede(per.getCodigoHabilitacion());
        obj.setEstadoSede(per.getEstadoSede());
        obj.setFax(per.getFax());
        obj.setTelefonoCitas(per.getTelefonoCitas());
        obj.setCorreoElectronico(per.getCorreoElectronico());
        obj.setTelefonoAdministrativo(per.getTelefonoAdministrativo());
        if (per.getCntPrestadoresId() != null) {
            CntPrestador cntPrestador = new CntPrestador();
            cntPrestador.setId(per.getCntPrestadoresId().getId());
            cntPrestador.setMaeTipoDocumentoId(per.getCntPrestadoresId().getMaeTipoDocumentoId());
            cntPrestador.setMaeTipoDocumentoCodigo(per.getCntPrestadoresId().getMaeTipoDocumentoCodigo());
            cntPrestador.setMaeTipoDocumentoValor(per.getCntPrestadoresId().getMaeTipoDocumentoValor());
            cntPrestador.setNumeroDocumento(per.getCntPrestadoresId().getNumeroDocumento());
            cntPrestador.setRazonSocial(per.getCntPrestadoresId().getRazonSocial());
            obj.setCntPrestador(cntPrestador);
        }
        return obj;
    }

    @Override
    public boolean consultarAuditoria(int idPres, int idItem, int tipoTec) throws Exception {
        try {
            // Construcción de la consulta base
            String strQuery = "SELECT p FROM MpPrescripcionItemAuditoria p "
                    + "WHERE p.mpPrescripcionId.id = :idPres ";

            // Añadiendo condiciones según el tipo de tecnología
            switch (tipoTec) {
                case 1:
                case 4:
                    strQuery += "AND p.mpPrescripcionMedicamentoId.id = :idItem";
                    break;
                case 2:
                    strQuery += "AND p.mpPrescripcionTecnologiaId.id = :idItem";
                    break;
                case 3:
                case 5:
                    strQuery += "AND p.mpPrescripcionInsumoId.id = :idItem";
                    break;
                default:
                    throw new IllegalArgumentException("Tipo de tecnología no válido: " + tipoTec);
            }

            // Creación y configuración de la consulta
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("idPres", idPres);
            query.setParameter("idItem", idItem);

            List<?> results = query.getResultList();
            return !results.isEmpty();

        } catch (IllegalArgumentException e) {
            // Manejo específico para tipoTec no válido
            throw e;
        } catch (Exception e) {
            // Manejo genérico de excepciones
            Exception(CONSULTAR_TODOS, e);
            return false;
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void insertarAuditoria(MpPrescripcionAuditoria obj) throws Exception {
        Integer id = 0;
        try {
            getEntityManager().merge(castNegocioEntidadAu(obj)).getId();

        } catch (Exception e) {
            Exception(INSERTAR, e, "");
        } finally {
            cerrarEntityManager();
        }

    }

    @Override
    public void insetarSolicitudCot(MpCotizacion obj) throws Exception {
        Integer id = 0;
        try {
            getEntityManager().merge(castNegocioEntidadCot(obj)).getId();

        } catch (Exception e) {
            Exception(INSERTAR, e, "");
        } finally {
            cerrarEntityManager();
        }

    }

    public static MpCotizaciones castNegocioEntidadCot(MpCotizacion obj) {
        MpCotizaciones per = new MpCotizaciones();
        per.setMpPrescripcionesId(new MpPrescripciones(obj.getMpPrescripcioneId().getId()));
        per.setNumeroPrescripcion(obj.getNumeroPrescripcion());
        switch (obj.getTipoTecnologia()) {
            case 1:
            case 4:
                per.setMpPrescripcionMedicamentosId(new MpPrescripcionMedicamentos(obj.getMpPrescripcionMedicamentoId().getId()));
                break;
            case 2:
                per.setMpPrescripcionTecnologiasId(new MpPrescripcionTecnologias(obj.getMpPrescripcionTecnologiaId().getId()));
                break;
            case 3:
            case 5:
                per.setMpPrescripcionInsumosId(new MpPrescripcionInsumos(obj.getMpPrescripcionInsumoId().getId()));
                break;
            default:
                break;
        }
        per.setTipoTecnologia(obj.getTipoTecnologia());
        per.setEstado(obj.getEstado());
        per.setConsecutivoOrden(obj.getConsecutivoOrden());
        per.setNombreTecnologia(obj.getNombreTecnologia());

        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        return per;
    }

    @Override
    public void insertarNoDireccionamiento(MpNoDireccionado obj) throws Exception {
        int id = 0;

        try {

            MpNoDireccionados entidad = castNegocioEntidadNoDireccionado(obj);

            if (entidad.getId() == null) {

                getEntityManager().persist(entidad);
                getEntityManager().flush();
                id = entidad.getId();
            } else {
                id = entidad.getId();
            }
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }

    }

    public static MpNoDireccionados castNegocioEntidadNoDireccionado(MpNoDireccionado neg) {
        MpNoDireccionados ent = new MpNoDireccionados();
        ent.setTipoTecnologia(neg.getTipoTecnologia());
        ent.setMpPrescripcionesId(new MpPrescripciones(neg.getMpPrescripcionesId().getId()));

        if (neg.getTipoTecnologia() != 0) {
            switch (neg.getTipoTecnologia()) {
                case 1:
                case 4:
                    ent.setMpPrescripcionMedicamentosId(new MpPrescripcionMedicamentos(neg.getMpPrescripcionMedicamentosId().getId()));
                    break;
                case 2:
                    ent.setMpPrescripcionTecnologiasId(new MpPrescripcionTecnologias(neg.getMpPrescripcionTecnologiasId().getId()));
                    break;
                case 3:
                case 5:
                    ent.setMpPrescripcionInsumosId(new MpPrescripcionInsumos(neg.getMpPrescripcionInsumosId().getId()));
                    break;
                default:
                    break;
            }
        }
        ent.setJustificacionNoDireccionamiento(neg.getJustificacionNoDireccionamiento());
        ent.setFechaAnulacion(neg.getFechaAnulacion());
        ent.setConsecutivoTecnologia(neg.getConsecutivoTecnologia());
        ent.setConTecAsociada(neg.getConTecAsociada());

        ent.setNumeroPrescripcionAsociada(neg.getNumeroPrescripcionAsociada());
        ent.setFecNoDireccionamiento(neg.getFecNoDireccionamiento());
        ent.setEstadoNoDireccionamiento(3);
        ent.setIdNoDireccionamiento(neg.getIdNoDireccionamiento());
        ent.setCodigoNoDireccionamiento(neg.getCodigoNoDireccionamiento());

        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());

        return ent;
    }

    public static MpPrescripcionItemAuditoria castNegocioEntidadAu(MpPrescripcionAuditoria obj) {
        MpPrescripcionItemAuditoria per = new MpPrescripcionItemAuditoria();
        per.setId(obj.getId());

        if (obj.getMpPrescripcionId() != null) {
            per.setMpPrescripcionId(new MpPrescripciones(obj.getMpPrescripcionId().getId()));
        }

        if (obj.getMpMedicamentoId() != null) {
            per.setMpPrescripcionMedicamentoId(new MpPrescripcionMedicamentos(obj.getMpMedicamentoId().getId()));
        } else if (obj.getMpTecnologiaId() != null) {
            per.setMpPrescripcionTecnologiaId(new MpPrescripcionTecnologias(obj.getMpTecnologiaId().getId()));
        } else if (true) {
            per.setMpPrescripcionInsumoId(new MpPrescripcionInsumos(obj.getMpInsumoId().getId()));
        }

        per.setEstado(obj.getEstado());
        per.setNotaAuditoria(obj.getNotaAuditoria());

        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        return per;
    }

    @Override
    public void cambiarEstadoPrescripcion(Integer idM, Integer idT, Integer idI, int estado) throws Exception {
        try {
            if (idM != null) {
                String hql = "UPDATE MpPrescripcionMedicamentos SET "
                        + " estado = :estado "
                        + " WHERE id = :id";
                Query query = getEntityManager().createQuery(hql);
                query.setParameter("estado", estado);
                query.setParameter("id", idM);
                query.executeUpdate();
                getEntityManager().flush();
            } else if (idT != null) {
                String hql = "UPDATE MpPrescripcionTecnologias SET "
                        + " estado = :estado "
                        + " WHERE id = :id";
                Query query = getEntityManager().createQuery(hql);
                query.setParameter("estado", estado);
                query.setParameter("id", idT);
                query.executeUpdate();
                getEntityManager().flush();
            } else if (idI != null) {
                String hql = "UPDATE MpPrescripcionInsumos SET "
                        + " estado = :estado "
                        + " WHERE id = :id";
                Query query = getEntityManager().createQuery(hql);
                query.setParameter("estado", estado);
                query.setParameter("id", idI);
                query.executeUpdate();
                getEntityManager().flush();
            }

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }

    }

    @Override
    public void cambiarEstadoPrescripcionInicial(Integer id) throws Exception {
        try {
            String hql = "UPDATE MpPrescripciones SET "
                    + " estado = 5 "
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();
            getEntityManager().flush();

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }

    }

    @Override
    public void actualizarAtencionItem(Integer id, Integer tipo) throws Exception {
        String hql = "";
        switch (tipo) {
            case 1:
            case 4:
                hql = "UPDATE MpPrescripcionMedicamentos SET banderaAtencion = :banderaAtencion WHERE id = :id";
                break;
            case 2:
                hql = "UPDATE MpPrescripcionTecnologias SET banderaAtencion = :banderaAtencion WHERE id = :id";
                break;
            case 3:
            case 5:
                hql = "UPDATE MpPrescripcionInsumos SET banderaAtencion = :banderaAtencion WHERE id = :id";
                break;
            default:
                break;
        }

        try {

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("id", id);
            query.setParameter("banderaAtencion", true);
            query.executeUpdate();
            getEntityManager().flush();

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }

    }

    @Override
    public void actualizarFinAtencion(Integer id, Integer tipo) throws Exception {
        String hql = "";
        switch (tipo) {
            case 1:
            case 4:
                hql = "UPDATE MpPrescripcionMedicamentos SET "
                        + " banderaAtencion = FALSE "
                        + " WHERE id = :id";
                break;
            case 2:
                hql = "UPDATE MpPrescripcionTecnologias SET "
                        + " banderaAtencion = FALSE "
                        + " WHERE id = :id";
                break;
            case 3:
            case 5:
                hql = "UPDATE MpPrescripcionInsumos SET "
                        + " banderaAtencion = FALSE "
                        + " WHERE id = :id";
                break;
            default:
        }

        try {

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();
            getEntityManager().flush();

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }

    }

    @Override
    public void liberarTecnologia(Integer id, Integer tipo) throws Exception {
        String hql = "";
        switch (tipo) {
            case 1:
            case 4:
                hql = "UPDATE MpPrescripcionMedicamentos SET "
                        + " banderaAtencion = FALSE "
                        + " WHERE id = :id";
                break;
            case 2:
                hql = "UPDATE MpPrescripcionTecnologias SET "
                        + " banderaAtencion = FALSE "
                        + " WHERE id = :id";
                break;
            case 3:
            case 5:
                hql = "UPDATE MpPrescripcionInsumos SET "
                        + " banderaAtencion = FALSE "
                        + " WHERE id = :id";
                break;
            default:
        }

        try {

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();
            getEntityManager().flush();

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }

    }

    @Override
    public void actualizarItemCot(Integer id, Integer tipo) throws Exception {
        String hql = "";
        switch (tipo) {
            case 1:
            case 4:
                hql = "UPDATE MpPrescripcionMedicamentos SET estado = :valor WHERE id = :id";
                break;
            case 2:
                hql = "UPDATE MpPrescripcionTecnologias SET estado = :valor WHERE id = :id";
                break;
            case 3:
            case 5:
                hql = "UPDATE MpPrescripcionInsumos SET estado = :valor WHERE id = :id";
                break;
            default:
                break;
        }
        try {

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("id", id);
            query.setParameter("valor", 11);
            query.executeUpdate();
            getEntityManager().flush();

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void insertarAtencion(String usuario, String terminal, boolean atendido, Date fecha, int tabla, int id) throws Exception {
        try {
            switch (tabla) {
                case 1: {
                    String hql = "UPDATE MpPrescripcionMedicamentos SET "
                            + " usuarioAtiende = :usuarioAtiende, "
                            + " terminalAtiende = :terminalAtiende, "
                            + " fechaHoraAtiende = :fechaHoraAtiende, "
                            + " atendido = :atendido "
                            + " WHERE id = :id";
                    Query query = getEntityManager().createQuery(hql);
                    query.setParameter("usuarioAtiende", usuario);
                    query.setParameter("terminalAtiende", terminal);
                    query.setParameter("fechaHoraAtiende", fecha);
                    query.setParameter("atendido", atendido);
                    query.setParameter("id", id);
                    query.executeUpdate();
                    break;
                }
                case 2: {
                    String hql = "UPDATE MpPrescripcionTecnologias SET "
                            + " usuarioAtiende = :usuarioAtiende, "
                            + " terminalAtiende = :terminalAtiende, "
                            + " fechaHoraAtiende = :fechaHoraAtiende, "
                            + " atendido = :atendido "
                            + " WHERE id = :id";
                    Query query = getEntityManager().createQuery(hql);
                    query.setParameter("usuarioAtiende", usuario);
                    query.setParameter("terminalAtiende", terminal);
                    query.setParameter("fechaHoraAtiende", fecha);
                    query.setParameter("atendido", atendido);
                    query.setParameter("id", id);
                    query.executeUpdate();
                    break;
                }
                case 3: {
                    String hql = "UPDATE MpPrescripcionInsumos SET "
                            + " usuarioAtiende = :usuarioAtiende, "
                            + " terminalAtiende = :terminalAtiende, "
                            + " fechaHoraAtiende = :fechaHoraAtiende, "
                            + " atendido = :atendido "
                            + " WHERE id = :id";
                    Query query = getEntityManager().createQuery(hql);
                    query.setParameter("usuarioAtiende", usuario);
                    query.setParameter("terminalAtiende", terminal);
                    query.setParameter("fechaHoraAtiende", fecha);
                    query.setParameter("atendido", atendido);
                    query.setParameter("id", id);
                    query.executeUpdate();
                    break;
                }
                default:
                    break;
            }

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }

    }

    @Override
    public MpPrescripcionHistorico inicioHistorico(MpPrescripcionHistorico historico) throws Exception {
        Integer id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(historico)).getId();
            historico.setId(id);
        } catch (Exception e) {
            Exception(INSERTAR, e, "");
        } finally {
            cerrarEntityManager();
        }
        return historico;
    }

    @Override
    public MpDireccionamientoEntregado verEntrega(Integer direccionamientoId) {
        MpDireccionamientoEntregado entrega = null;
        try {
            String strQuery = "SELECT e FROM MpDireccionamientoEntregados e WHERE e.mpDireccionamientosId.id = :direccionamientoId";
            MpDireccionamientoEntregados obj = (MpDireccionamientoEntregados) getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("direccionamientoId", direccionamientoId)
                    .getSingleResult();

            entrega = castEntidadNegocioEntrega(obj);
        } catch (NoResultException e) {
            // Entidad no encontrada, devuelve null
            entrega = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return entrega;
    }

    @Override
    public MpDireccionamientoEntregado verEntregaSuministroD(Integer id) {
        MpDireccionamientoEntregado entrega = null;
        try {
            String strQuery = "SELECT e FROM MpDireccionamientoEntregados e WHERE e.id = :id";
            MpDireccionamientoEntregados obj = (MpDireccionamientoEntregados) getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("id", id)
                    .getSingleResult();

            entrega = castEntidadNegocioEntregaSuministro(obj);
        } catch (NoResultException e) {
            // Entidad no encontrada, devuelve null
            entrega = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return entrega;
    }

    public static MpDireccionamientoEntregado castEntidadNegocioEntregaSuministro(MpDireccionamientoEntregados ent) {
        MpDireccionamientoEntregado obj = new MpDireccionamientoEntregado();
        obj.setId(ent.getId());
        if (ent.getMpDireccionamientoId() != null) {
            MpDireccionamiento direc = new MpDireccionamiento();
            direc.setId(ent.getMpDireccionamientoId().getId());
            direc.setPrestadorRazonSocial(ent.getMpDireccionamientoId().getPrestadorRazonSocial());
            direc.setEntregaTotal(ent.getMpDireccionamientoId().getEntregaTotal());
            obj.setIpsDirecciona(ent.getMpDireccionamientoId().getPrestadorRazonSocial());
            obj.setMpDireccionamientoId(direc);
        } else {
            if (ent.getMpPrescripcionId() != null) {
                MpPrescripcion pres = new MpPrescripcion();
                pres.setId(ent.getMpPrescripcionId().getId());
                pres.setPrestadorRazonSocial(ent.getMpPrescripcionId().getPrestadorRazonSocial());
                obj.setIpsDirecciona(ent.getMpPrescripcionId().getPrestadorRazonSocial());
                obj.setMpPrescripcion(pres);
            }
        }
        if (ent.getMpPrescripcionId() != null) {
            MpPrescripcion pres = new MpPrescripcion();
            pres.setId(ent.getMpPrescripcionId().getId());
            pres.setNumeroPrescripcion(ent.getMpPrescripcionId().getNumeroPrescripcion());
            pres.setCodAmbAte(ent.getMpPrescripcionId().getCodAmbAte());
            pres.setMaDiagnosticoPrincipalCodigo(ent.getMpPrescripcionId().getMaDiagnosticoPrincipalCodigo());
            pres.setMaDiagnosticoPrincipalValor(ent.getMpPrescripcionId().getMaDiagnosticoPrincipalValor());
            if (ent.getMpPrescripcionId().getAsegAfiliadosId() != null) {
                AsegAfiliado afiliado = new AsegAfiliado();
                afiliado.setId(ent.getMpPrescripcionId().getAsegAfiliadosId().getId());
                afiliado.setPrimerNombre(ent.getMpPrescripcionId().getAsegAfiliadosId().getPrimerNombre());
                afiliado.setPrimerApellido(ent.getMpPrescripcionId().getAsegAfiliadosId().getPrimerApellido());
                afiliado.setSegundoNombre(ent.getMpPrescripcionId().getAsegAfiliadosId().getSegundoNombre());
                afiliado.setSegundoApellido(ent.getMpPrescripcionId().getAsegAfiliadosId().getSegundoApellido());
                afiliado.setRegimen(ent.getMpPrescripcionId().getAsegAfiliadosId().getRegimen());
                afiliado.setNumeroDocumento(ent.getMpPrescripcionId().getAsegAfiliadosId().getNumeroDocumento());
                pres.setAsegAfiliado(afiliado);
            }
            obj.setMpPrescripcion(pres);
        }

        if (ent.getMpPrescripicionMedicamentosId() != null) {
            MpPrescripcionMedicamento med = new MpPrescripcionMedicamento();
            med.setId(ent.getMpPrescripicionMedicamentosId().getId());
            med.setTipoTecnologia(ent.getMpPrescripicionMedicamentosId().getTipoTecnologia());
            med.setCantidadTotalEntrega(ent.getMpPrescripicionMedicamentosId().getCantidadTotalEntrega());
            med.setConsecutivoOrden(ent.getMpPrescripicionMedicamentosId().getConsecutivoOrden());
            switch (ent.getMpPrescripicionMedicamentosId().getTipoTecnologia()) {
                case 1:
                    med.setDescripcionMedicamentoPrincipioActivo(ent.getMpPrescripicionMedicamentosId().getDescripcionMedicamentoPrincipioActivo());
                    med.setCodigoFormulaFarmaceutica(ent.getMpPrescripicionMedicamentosId().getCodigoFormulaFarmaceutica());
                    break;
                case 4:
                    med.setMaeProductosNutricionalesValor(ent.getMpPrescripicionMedicamentosId().getMaeProductosNutricionalesValor());
                    med.setMaeProductosNutricionalesCodigo(ent.getMpPrescripicionMedicamentosId().getMaeProductosNutricionalesCodigo());
                    break;
                default:
                    break;
            }
            obj.setMpPrescripcionMedicamentoId(med);
        }

        if (ent.getMpPrescripicionInsumosId() != null) {
            MpPrescripcionInsumo ins = new MpPrescripcionInsumo();
            ins.setId(ent.getMpPrescripicionInsumosId().getId());
            ins.setTipoTecnologia(ent.getMpPrescripicionInsumosId().getTipoTecnologia());
            ins.setCantidadTotalEntrega(ent.getMpPrescripicionInsumosId().getCantidadTotalEntrega());
            ins.setConsecutivoOrden(ent.getMpPrescripicionInsumosId().getConsecutivoOrden());
            switch (ent.getMpPrescripicionInsumosId().getTipoTecnologia()) {
                case 3:
                    ins.setMaeDispositivosNombre(ent.getMpPrescripicionInsumosId().getMaedispositivosValor());
                    ins.setMaeDispositivosCodigo(ent.getMpPrescripicionInsumosId().getMaeDispositivosCodigo());
                    break;
                case 5:
                    ins.setMaeServiciosComplementariosNombre(ent.getMpPrescripicionInsumosId().getMaeServiciosComplementariosValor());
                    ins.setMaeServiciosComplementariosCodigo(ent.getMpPrescripicionInsumosId().getMaeServiciosComplementariosCodigo());
                    break;
                default:
                    break;
            }
            obj.setMpPrescripcionInsumoId(ins);
        }

        if (ent.getMpPrescripicionTecnologiasId() != null) {
            MpPrescripcionTecnologia tec = new MpPrescripcionTecnologia();
            tec.setId(ent.getMpPrescripicionTecnologiasId().getId());
            tec.setTipoTecnologia(ent.getMpPrescripicionTecnologiasId().getTipoTecnologia());
            tec.setCantidadTotalEntrega(ent.getMpPrescripicionTecnologiasId().getCantidadTotalEntrega());
            tec.setMaTecnologiaValor(ent.getMpPrescripicionTecnologiasId().getMaTecnologiaValor());
            tec.setMaTecnologiaCodigo(ent.getMpPrescripicionTecnologiasId().getMaTecnologiaCodigo());
            tec.setConsecutivoOrden(ent.getMpPrescripicionTecnologiasId().getConsecutivoOrden());
            obj.setMpPrescripcionTecnologiaId(tec);
        }

        obj.setIdTransaccion(ent.getIdTransaccion());
        obj.setNumeroLote(ent.getNumeroLote());
        obj.setValorTotal(ent.getValorTotal());
        obj.setCausaNoEntrega(ent.getCausaNoEntrega());
        obj.setIdReporteEntrega(ent.getIdReporteEntrega());
        obj.setEstRepEntrega(ent.getEstRepEntrega());
        obj.setCantidadEntrega(ent.getCantidadEntrega());
        obj.setNumeroEntrega(ent.getNumeroEntrega());
        obj.setEntregaTotal(ent.getEntregaTotal());
        obj.setFechaEntrega(ent.getFechaEntrega());
        obj.setFechaAnulacion(ent.getFechaAnulacion());
        obj.setCodTecEntregado(ent.getCodTecEntregado());
        obj.setDescTecEntregado(ent.getDescTecEntregado());
        if (ent.getFechaAnulacion() != null) {
            obj.setAnulado(true);
        } else {
            obj.setAnulado(false);
        }
        Integer ult = ent.getEntregaCompleta();
        if (ult != null && ult == 1) {
            obj.setEntregaCompleta(true);
        } else {
            obj.setEntregaCompleta(false);
        }
        obj.setTipoTecnologia(ent.getTipoTecnologia());

        return obj;
    }

    @Override
    public MpDireccionamientoEntregado verEntregaD(Integer direccionamientoId) throws Exception {
        MpDireccionamientoEntregado entrega = new MpDireccionamientoEntregado();
        try {

            String strQuery = "SELECT e FROM MpDireccionamientoEntregados e WHERE e.id = :direccionamientoId";

            MpDireccionamientoEntregados obj = (MpDireccionamientoEntregados) getEntityManager()
                    .createQuery(strQuery.toString())
                    .setParameter("direccionamientoId", direccionamientoId)
                    .getSingleResult();

            entrega = castEntidadNegocioEntrega(obj);

        } catch (NoResultException e) {
            throw new Exception("No se encontró la entidad con mpDireccionamientos id: " + direccionamientoId, e);
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al ver la entrega con mpDireccionamientos id: " + direccionamientoId);
        } finally {
            cerrarEntityManager();
        }
        return entrega;
    }

    public static MpDireccionamientoEntregado castEntidadNegocioEntrega(MpDireccionamientoEntregados ent) {
        MpDireccionamientoEntregado obj = new MpDireccionamientoEntregado();
        obj.setId(ent.getId());
        if (ent.getMpDireccionamientoId() != null) {
            MpDireccionamiento direc = new MpDireccionamiento();
            direc.setId(ent.getMpDireccionamientoId().getId());
            direc.setPrestadorRazonSocial(ent.getMpDireccionamientoId().getPrestadorRazonSocial());
            direc.setFechaMaxEntrega(ent.getMpDireccionamientoId().getFechaMaxEntrega());
            direc.setEntregaTotal(ent.getMpDireccionamientoId().getEntregaTotal());
            obj.setIpsDirecciona(ent.getMpDireccionamientoId().getPrestadorRazonSocial());
            obj.setMpDireccionamientoId(direc);
        } else {
            if (ent.getMpPrescripcionId() != null) {
                MpPrescripcion pres = new MpPrescripcion();
                pres.setId(ent.getMpPrescripcionId().getId());
                pres.setPrestadorRazonSocial(ent.getMpPrescripcionId().getPrestadorRazonSocial());
                obj.setIpsDirecciona(ent.getMpPrescripcionId().getPrestadorRazonSocial());
                obj.setMpPrescripcion(pres);
            }
        }
        obj.setIdTransaccion(ent.getIdTransaccion());
        obj.setIdReporteEntrega(ent.getIdReporteEntrega());
        obj.setEstRepEntrega(ent.getEstRepEntrega());
        obj.setCantidadEntrega(ent.getCantidadEntrega());
        obj.setNumeroEntrega(ent.getNumeroEntrega());
        obj.setJustificacionDireccionamiento(ent.getJustificacionDireccionamiento());
        obj.setCausaNoEntrega(ent.getCausaNoEntrega());
        obj.setEstadoEntrega(ent.getEstadoEntrega());
        obj.setEntregaTotal(ent.getEntregaTotal());
        obj.setValorTotal(ent.getValorTotal());
        obj.setFechaEntrega(ent.getFechaEntrega());
        obj.setFechaAnulacion(ent.getFechaAnulacion());
        obj.setCodTecEntregado(ent.getCodTecEntregado());
        obj.setDescTecEntregado(ent.getDescTecEntregado());
        if (ent.getFechaAnulacion() != null) {
            obj.setAnulado(true);
        } else {
            obj.setAnulado(false);
        }
        Integer ult = ent.getEntregaCompleta();
        if (ult != null && ult == 1) {
            obj.setEntregaCompleta(true);
        } else {
            obj.setEntregaCompleta(false);
        }
        obj.setTipoTecnologia(ent.getTipoTecnologia());

        return obj;
    }

    public static MpPrescripcionHistoricos castNegocioEntidad(MpPrescripcionHistorico obj) {
        MpPrescripcionHistoricos per = new MpPrescripcionHistoricos();
        if (obj.getMpPrescripcion() != null) {
            per.setMpPrescripcionesId(new MpPrescripciones(obj.getMpPrescripcion().getId()));
        }
        per.setTipoTecnologia(obj.getTipoTecnologia());
        per.setIdPrescripcionTecnologia(obj.getIdPrescripcionTecnologia());
        per.setEstado(obj.getEstado());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        return per;
    }

    @Override
    public List<MpPrescripcionHistorico> consultarListaDeHistorico(int prescripcion, int item) throws Exception {
        List<MpPrescripcionHistorico> listaResultado = new ArrayList<>();

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(" SELECT pP FROM MpPrescripcionHistoricos pP WHERE pP.mpPrescripcionesId.id = :id ");
            strQuery.append(" AND pP.idPrescripcionTecnologia = :itemId ");
            strQuery.append(" ORDER BY pP.id DESC ");

            List<MpPrescripcionHistoricos> lista = getEntityManager()
                    .createQuery(strQuery.toString())
                    .setParameter("id", prescripcion)
                    .setParameter("itemId", item)
                    .getResultList();

            lista.forEach(mpH -> listaResultado.add(castNegocioEntidadH(mpH)));
        } catch (Exception e) {

            throw new Exception("Error al consultar la lista Historico", e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    public static MpPrescripcionHistorico castNegocioEntidadH(MpPrescripcionHistoricos obj) {
        MpPrescripcionHistorico per = new MpPrescripcionHistorico();
        if (obj.getMpPrescripcionesId() != null) {
            per.setMpPrescripcion(new MpPrescripcion(obj.getMpPrescripcionesId().getId()));
        }
        per.setTipoTecnologia(obj.getTipoTecnologia());
        per.setIdPrescripcionTecnologia(obj.getIdPrescripcionTecnologia());
        per.setEstado(obj.getEstado());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        return per;
    }

    @Override
    public String consultarCorreoPrestador(String documento) throws Exception {
        String correo = null;
        try {
            String strQuery = " SELECT c.contacto"
                    + " FROM CntPrestadorContactos c"
                    + " WHERE c.cntPrestadoresId.id = ("
                    + " SELECT p.id"
                    + " FROM CntPrestadores p"
                    + " WHERE p.numeroDocumento = :documento AND p.activo = 1"
                    + " )"
                    + " AND c.maeTipoContactoCodigo = 3"
                    + " AND c.maeAreaContactoCodigo = 6"
                    + " ORDER BY c.fechaHoraCrea DESC";

            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("documento", documento);

            correo = (String) query.setMaxResults(1).getSingleResult();

        } catch (NoResultException e) {

            correo = null;
        } catch (Exception e) {

            throw new Exception("Error al consultar el correo del prestador.", e);
        } finally {
            cerrarEntityManager();
        }
        return correo;
    }

    @Override
    public String consultarCorreoPrestadorDir(String documento) throws Exception {
        String correo = null;
        try {
            String strQuery = " SELECT c.contacto"
                    + " FROM CntPrestadorContactos c"
                    + " WHERE c.cntPrestadoresId.id = ("
                    + " SELECT p.id"
                    + " FROM CntPrestadores p"
                    + " WHERE p.codigoMinSalud = :documento AND p.activo = 1"
                    + " )"
                    + " AND c.maeTipoContactoCodigo = 3"
                    + " ORDER BY c.fechaHoraCrea DESC";

            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("documento", documento);

            correo = (String) query.setMaxResults(1).getSingleResult();

        } catch (NoResultException e) {

            correo = null;
        } catch (Exception e) {

            throw new Exception("Error al consultar el correo del prestador.", e);
        } finally {
            cerrarEntityManager();
        }
        return correo;
    }

    @Override
    public String consultarCorreoAfiliado(int id) throws Exception {
        String correo = null;
        try {
            String strQuery = " SELECT a.email"
                    + " FROM AsegAfiliados a"
                    + " WHERE a.id = :id";

            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("id", id);

            correo = (String) query.setMaxResults(1).getSingleResult();

        } catch (NoResultException e) {

            correo = null;
        } catch (Exception e) {

            throw new Exception("Error al consultar el correo del prestador.", e);
        } finally {
            cerrarEntityManager();
        }
        return correo;
    }

    @Override
    public String consultarContactoAfiliado(int id) throws Exception {
        String celular = null;
        try {
            String strQuery = "SELECT a.numeroContacto"
                    + " FROM AsegAfiliadoContactos a"
                    + " WHERE a.asegAfiliadosId.id = :id"
                    + " AND a.maeTipoContactoCodigo = 2";

            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("id", id);

            celular = (String) query.setMaxResults(1).getSingleResult();

        } catch (NoResultException e) {

            celular = null;
        } catch (Exception e) {

            throw new Exception("Error al consultar el celular del afiliado.", e);
        } finally {
            cerrarEntityManager();
        }
        return celular;
    }

    @Override
    public List<MaMedicamento> consultarListaMaMedicamento(ParamConsulta paramConsulta) throws Exception {
        List<MaMedicamento> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaMedicamentos p WHERE p.id > 0 ";
            strQuery += " AND p.activo = TRUE ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cum":
                            strQuery += " AND p.cum LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcionInvima":
                            strQuery += " AND p.descripcionInvima LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcionEstandarizada":
                            strQuery += " AND p.descripcionEstandarizada LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeConcentracionValor":
                            strQuery += " AND p.maeConcentracionValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maePrincipioActivoValor":
                            strQuery += " AND p.maePrincipioActivoValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeFormaFarmaceuticaValor":
                            strQuery += " AND p.maeFormaFarmaceuticaValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeCoberturaValor":
                            strQuery += " AND p.maeCoberturaValor  = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY p.id DESC";

            List<MaMedicamentos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (MaMedicamentos per : list) {
                listResult.add(castEntidadNegocioMaMedicamento(per));
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

    public static MaMedicamento castEntidadNegocioMaMedicamento(MaMedicamentos per) {
        MaMedicamento obj = new MaMedicamento();
        obj.setId(per.getId());
        obj.setActivo(per.getActivo());
        obj.setCum(per.getCum());
        obj.setDescripcionInvima(per.getDescripcionInvima());
        obj.setDescripcionEstandarizada(per.getDescripcionEstandarizada());
        obj.setMaeCoberturaValor(per.getMaeCoberturaValor());
        obj.setMaeConcentracionValor(per.getMaeConcentracionValor());
        obj.setMaePrincipioActivoValor(per.getMaePrincipioActivoValor());
        obj.setMaeFormaFarmaceuticaValor(per.getMaeFormaFarmaceuticaValor());
        return obj;
    }

    @Override
    public int consultarCantidadListaMaMedicamento(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT (p) FROM MaMedicamentos p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cum":
                            strQuery += " AND p.cum LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcionInvima":
                            strQuery += " AND p.descripcionInvima LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcionEstandarizada":
                            strQuery += " AND p.descripcionEstandarizada LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeConcentracionValor":
                            strQuery += " AND p.maeConcentracionValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maePrincipioActivoValor":
                            strQuery += " AND p.maePrincipioActivoValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeFormaFarmaceuticaValor":
                            strQuery += " AND p.maeFormaFarmaceuticaValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeCoberturaValor":
                            strQuery += " AND p.maeCoberturaValor  = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
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
    public int consultarCantidadListaMaInsumo(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaInsumos p "
                    + "WHERE p.activo = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeTipoValor":
                            strQuery += " AND p.maeTipoValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "codigo":
                            strQuery += " AND p.codigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += " AND p.descripcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cobertura":
                            strQuery += " AND p.cobertura = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
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
    public int consultarCantidadListaMaInsumoMipres(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaInsumosMipres p "
                    + "WHERE p.maInsumosId.activo = true "
                    + "AND p.insumosMipresId.activo = true";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoMipres":
                            strQuery += " AND p.codigoMipres  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcionMipres":
                            strQuery += " AND p.descripcionMipres  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maInsumosMipresId.descripcion":
                            strQuery += " AND p.maInsumosMipresId.descripcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
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
    public int consultarCantidadListaMaPaqueteMipres(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaPaquetesMipres p "
                    + " INNER JOIN MaPaquetes paquete ON p.maPaquetesId = paquete.id "
                    + " INNER JOIN MpCodigoInsumos insumo ON p.mpCodigoInsumosId = insumo.id "
                    + " WHERE paquete.activo = true "
                    + " AND insumo.activo = true ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoMipres":
                            strQuery += " AND p.codigoMipres  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcionMipres":
                            strQuery += " AND p.descripcionMipres  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maPaquetesId.nombre":
                            strQuery += " AND p.maPaquetesId.nombre  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "mpCodigoIndumosId.descripcion":
                            strQuery += " AND p.mpCodigoIndumosId.descripcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
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
    public int consultarCantidadListaMaTecnologiaMipres(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaTecnologiasMipres p "
                    + "WHERE p.maTecnologiasId.activo = true "
                    + "AND p.insumosMipresId.activo = true";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoMipres":
                            strQuery += " AND p.codigoMipres  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += " AND p.descripcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
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
    public List<MaInsumo> consultarListaMaInsumo(ParamConsulta paramConsulta) throws Exception {
        List<MaInsumo> listResult = new ArrayList();
        try {
            String strQuery = " FROM MaInsumos p "
                    + "WHERE p.activo = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeTipoValor":
                            strQuery += " AND p.maeTipoValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "codigo":
                            strQuery += " AND p.codigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += " AND p.descripcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cobertura":
                            strQuery += " AND p.cobertura = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY p.id DESC";
            List<MaInsumos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (MaInsumos per : list) {
                listResult.add(castEntidadNegocioMaInsumo(per));
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

    public static MaInsumo castEntidadNegocioMaInsumo(MaInsumos per) {
        MaInsumo obj = new MaInsumo();
        obj.setId(per.getId());
        obj.setMaeTipoValor(per.getMaeTipoValor());
        obj.setCodigo(per.getCodigo());
        obj.setDescripcion(per.getDescripcion());
        obj.setActivo(per.getActivo());
        obj.setCobertura(per.getCobertura());
        return obj;
    }

    @Override
    public List<MaPaqueteMipres> consultarListaMaPaqueteMipres(ParamConsulta paramConsulta) throws Exception {
        List<MaPaqueteMipres> listResult = new ArrayList();
        try {
            String strQuery = " SELECT p FROM MaPaquetesMipres p "
                    + " INNER JOIN MaPaquetes paquete ON p.maPaquetesId = paquete.id "
                    + " INNER JOIN MpCodigoInsumos insumo ON p.mpCodigoInsumosId = insumo.id "
                    + " WHERE paquete.activo = true "
                    + " AND insumo.activo = true ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoMipres":
                            strQuery += " AND p.codigoMipres  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcionMipres":
                            strQuery += " AND p.descripcionMipres  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maPaquetesId.nombre":
                            strQuery += " AND paquete.nombre  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "mpCodigoIndumosId.descripcion":
                            strQuery += " AND insumo.descripcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY p.id DESC";
            List<MaPaquetesMipres> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (MaPaquetesMipres per : list) {
                listResult.add(castEntidadNegocioMaPaquete(per));
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

    public static MaPaqueteMipres castEntidadNegocioMaPaquete(MaPaquetesMipres per) {
        MaPaqueteMipres obj = new MaPaqueteMipres();
        obj.setId(per.getId());
        obj.setDescripcionMipres(per.getDescripcionMipres());
        obj.setCodigoMipres(per.getCodigoMipres());
        if (per.getMpCodigoInsumosId() != null) {
            MpCodigoInsumo insumo = new MpCodigoInsumo();
            insumo.setId(per.getMpCodigoInsumosId().getId());
            insumo.setDescripcion(per.getMpCodigoInsumosId().getDescripcion());
            obj.setMpCodigoInsumo(insumo);
        }
        if (per.getMaPaquetesId() != null) {
            MaPaquete paquete = new MaPaquete();
            paquete.setId(per.getMaPaquetesId().getId());
            paquete.setNombre(per.getMaPaquetesId().getNombre());
            obj.setMaPaquete(paquete);
        }
        return obj;
    }

    @Override
    public List<MaInsumoMipres> consultarListaMaInsumoMipres(ParamConsulta paramConsulta) throws Exception {
        List<MaInsumoMipres> listResult = new ArrayList();
        try {
            String strQuery = " FROM MaInsumosMipres p "
                    + "WHERE p.maInsumosId.activo = true "
                    + "AND p.insumosMipresId.activo = true";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoMipres":
                            strQuery += " AND p.codigoMipres  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcionMipres":
                            strQuery += " AND p.descripcionMipres  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maInsumo.descripcion":
                            strQuery += " AND p.maInsumosId.descripcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY p.id DESC";
            List<MaInsumosMipres> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (MaInsumosMipres per : list) {
                listResult.add(castEntidadNegocioMaInsumoMipres(per));
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

    public static MaInsumoMipres castEntidadNegocioMaInsumoMipres(MaInsumosMipres per) {
        MaInsumoMipres obj = new MaInsumoMipres();
        obj.setId(per.getId());
        obj.setCodigoMipres(per.getCodigoMipres());
        obj.setDescripcionMipres(per.getDescripcionMipres());
        if (per.getMaInsumosId() != null) {
            MaInsumo insumo = new MaInsumo();
            insumo.setId(per.getMaInsumosId().getId());
            insumo.setDescripcion(per.getMaInsumosId().getDescripcion());
            obj.setMaInsumo(insumo);
        }
        return obj;
    }

    @Override
    public List<MaTecnologiaMipres> consultarListaMaTecnologiaMipres(ParamConsulta paramConsulta) throws Exception {
        List<MaTecnologiaMipres> listResult = new ArrayList();
        try {
            String strQuery = " FROM MaTecnologiasMipres p "
                    + " WHERE p.maTecnologiasId.activo = true "
                    + " AND p.insumosMipresId.activo = true";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoMipres":
                            strQuery += " AND p.codigoMipres  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += " AND p.descripcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maTecnologia.propioDescripcion":
                            strQuery += " AND p.maTecnologiasId.propioDescripcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY p.id DESC";
            List<MaTecnologiasMipres> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (MaTecnologiasMipres per : list) {
                listResult.add(castEntidadNegocioMaTecnologiaMipres(per));
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

    public static MaTecnologiaMipres castEntidadNegocioMaTecnologiaMipres(MaTecnologiasMipres per) {
        MaTecnologiaMipres obj = new MaTecnologiaMipres();
        obj.setId(per.getId());
        obj.setCodigoMipres(per.getCodigoMipres());
        obj.setDescripcion(per.getDescripcion());
        if (per.getMaTecnologiasId() != null) {
            MaTecnologia tecnologia = new MaTecnologia();
            tecnologia.setId(per.getMaTecnologiasId().getId());
            tecnologia.setPropioDescripcion(per.getMaTecnologiasId().getPropioDescripcion());
            obj.setMaTecnologia(tecnologia);
        }
        return obj;
    }

    @Override
    public int consultarCantidadListaTecnologia(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaTecnologias p "
                    + "LEFT JOIN MaTecnologiasMipres m ON m.maTecnologiasId = p.id "
                    + "WHERE m.maTecnologiasId IS NULL";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cups":
                            strQuery += " AND p.cups LIKE '%" + e.getValue() + "%'";
                            break;
                        case "cupsDescipcion":
                            strQuery += " AND p.cupsDescipcion LIKE '%" + e.getValue() + "%'";
                            break;
                        case "maeCoberturaValor":
                            strQuery += " AND p.maeCoberturaValor = " + e.getValue().toString();
                            break;
                    }
                }
            }
            cant = ((Number) getEntityManager().createQuery(strQuery).getSingleResult()).intValue();
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
    public int consultarCantidadListaPrescripcionPrestador(ParamConsulta paramConsulta, String numeroPrestador) throws Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(DISTINCT p) FROM MpPrescripciones p "
                    + "LEFT JOIN p.mpDireccionamientosList d "
                    + "LEFT JOIN p.mpPrescripcionItemAuditoriaList a "
                    + "WHERE ((d.estado IN (1, 2) AND d.prestadorNumeroDocumento = :numeroPrestadorInt) "
                    + "OR (d IS NULL AND p.codAmbAte IN (22, 30) AND a.estado IN (1, 5) AND p.prestadorNumeroDocumento = :numeroPrestador))");

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry<String, Object> e : paramConsulta.getFiltros().entrySet()) {
                    String key = e.getKey();
                    Object value = e.getValue();
                    if (value == null || value.toString().trim().isEmpty()) {
                        continue;
                    }

                    switch (key) {
                        case "numeroPrescripcion":
                            strQuery.append(" AND LOWER(p.numeroPrescripcion) LIKE LOWER('%").append(value).append("%')");
                            break;
                        case "codAmbAte":
                            strQuery.append(" AND p.codAmbAte = '").append(value).append("'");
                            break;
                        case "asegAfiliado.primerNombre":
                            strQuery.append(" AND (LOWER(p.asegAfiliadosId.primerNombre) LIKE LOWER('%").append(value)
                                    .append("%') OR LOWER(p.asegAfiliadosId.segundoNombre) LIKE LOWER('%").append(value).append("%'))");
                            break;
                        case "asegAfiliado.primerApellido":
                            strQuery.append(" AND (LOWER(p.asegAfiliadosId.primerApellido) LIKE LOWER('%").append(value)
                                    .append("%') OR LOWER(p.asegAfiliadosId.segundoApellido) LIKE LOWER('%").append(value).append("%'))");
                            break;
                        case "asegAfiliado.numeroDocumento":
                            strQuery.append(" AND LOWER(p.asegAfiliadosId.numeroDocumento) LIKE LOWER('%").append(value).append("%')");
                            break;
                    }
                }
            }

            Query query = getEntityManager().createQuery(strQuery.toString());
            query.setParameter("numeroPrestadorInt", Integer.parseInt(numeroPrestador));
            query.setParameter("numeroPrestador", numeroPrestador);

            cant = ((Number) query.getSingleResult()).intValue();
        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            throw new Exception("Error al consultar la cantidad: " + e.getMessage(), e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

    @Override
    public List<MpPrescripcion> consultarListaPrescripcionPrestador(ParamConsulta paramConsulta, String numeroPrestador) throws Exception {
        List<MpPrescripcion> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT DISTINCT p FROM MpPrescripciones p ")
                    .append("LEFT JOIN p.mpDireccionamientosList d ")
                    .append("LEFT JOIN p.mpPrescripcionItemAuditoriaList a ")
                    .append("WHERE (")
                    .append("(d.estado IN (1, 2) AND d.prestadorNumeroDocumento = :numeroPrestadorInt) ")
                    .append("OR (d IS NULL AND p.codAmbAte IN (22, 30) AND a.estado IN (1, 5) AND p.prestadorNumeroDocumento = :numeroPrestador)")
                    .append(")");

            if (paramConsulta.getFiltros() != null && !paramConsulta.getFiltros().isEmpty()) {
                for (Map.Entry<String, Object> e : paramConsulta.getFiltros().entrySet()) {
                    String key = e.getKey();
                    Object value = e.getValue();
                    if (value == null || value.toString().trim().isEmpty()) {
                        continue;
                    }

                    switch (key) {
                        case "numeroPrescripcion":
                            strQuery.append(" AND LOWER(p.numeroPrescripcion) LIKE :filtroNumeroPrescripcion");
                            break;
                        case "codAmbAte":
                            strQuery.append(" AND p.codAmbAte = :filtroCodAmbAte");
                            break;
                        case "asegAfiliado.primerNombre":
                            strQuery.append(" AND (LOWER(p.asegAfiliadosId.primerNombre) LIKE :filtroNombre OR LOWER(p.asegAfiliadosId.segundoNombre) LIKE :filtroNombre)");
                            break;
                        case "asegAfiliado.primerApellido":
                            strQuery.append(" AND (LOWER(p.asegAfiliadosId.primerApellido) LIKE :filtroApellido OR LOWER(p.asegAfiliadosId.segundoApellido) LIKE :filtroApellido)");
                            break;
                        case "asegAfiliado.numeroDocumento":
                            strQuery.append(" AND p.asegAfiliadosId.numeroDocumento LIKE :filtroDocumento");
                            break;
                    }
                }
            }

            strQuery.append(" ORDER BY p.id DESC");

            TypedQuery<MpPrescripciones> query = getEntityManager().createQuery(strQuery.toString(), MpPrescripciones.class)
                    .setParameter("numeroPrestadorInt", Integer.parseInt(numeroPrestador))
                    .setParameter("numeroPrestador", numeroPrestador)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina());

            // Agrega parámetros de filtros
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry<String, Object> e : paramConsulta.getFiltros().entrySet()) {
                    String key = e.getKey();
                    Object value = e.getValue();
                    if (value == null || value.toString().trim().isEmpty()) {
                        continue;
                    }

                    switch (key) {
                        case "numeroPrescripcion":
                            query.setParameter("filtroNumeroPrescripcion", "%" + value.toString().toLowerCase() + "%");
                            break;
                        case "codAmbAte":
                            query.setParameter("filtroCodAmbAte", value.toString());
                            break;
                        case "asegAfiliado.primerNombre":
                            query.setParameter("filtroNombre", "%" + value.toString().toLowerCase() + "%");
                            break;
                        case "asegAfiliado.primerApellido":
                            query.setParameter("filtroApellido", "%" + value.toString().toLowerCase() + "%");
                            break;
                        case "asegAfiliado.numeroDocumento":
                            query.setParameter("filtroDocumento", "%" + value.toString() + "%");
                            break;
                    }
                }
            }

            List<MpPrescripciones> lista = query.getResultList();

            for (MpPrescripciones pres : lista) {
                listResult.add(castEntidadNegocioPrescripcionPrestador(pres));
            }

        } catch (NoResultException e) {
            listResult = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listResult;
    }

    public static MpPrescripcion castEntidadNegocioPrescripcionPrestador(MpPrescripciones ent) {
        MpPrescripcion obj = new MpPrescripcion();

        obj.setCodAmbAte(ent.getCodAmbAte());//

        obj.setEstado(ent.getEstado());//
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaPrescripcion(ent.getFechaPrescripcion());//
        obj.setHoraPrescripcion(ent.getHoraPrescripcion());//
        obj.setId(ent.getId());//      
        obj.setMaTipoDocumentoPrestadorCodigo(ent.getMaTipoDocumentoPrestadorCodigo());
        obj.setMaTipoDocumentoPrestadorId(ent.getMaTipoDocumentoPrestadorId());
        obj.setMaTipoDocumentoPrestadorValor(ent.getMaTipoDocumentoPrestadorValor());
        obj.setNumeroPrescripcion(ent.getNumeroPrescripcion());
        obj.setPrestadorNumeroDocumento(ent.getPrestadorNumeroDocumento());
        obj.setPrestadorRazonSocial(ent.getPrestadorRazonSocial());

        if (ent.getAsegAfiliadosId() != null) {
            obj.setAsegAfiliado(new AsegAfiliado(ent.getAsegAfiliadosId().getId()));
            obj.getAsegAfiliado().setNumeroDocumento(ent.getAsegAfiliadosId().getNumeroDocumento());
            obj.getAsegAfiliado().setPrimerNombre(ent.getAsegAfiliadosId().getPrimerNombre());
            obj.getAsegAfiliado().setSegundoNombre(ent.getAsegAfiliadosId().getSegundoNombre());
            obj.getAsegAfiliado().setPrimerApellido(ent.getAsegAfiliadosId().getPrimerApellido());
            obj.getAsegAfiliado().setSegundoApellido(ent.getAsegAfiliadosId().getSegundoApellido());
            obj.getAsegAfiliado().setRegimen(ent.getAsegAfiliadosId().getRegimen());
        }
        return obj;
    }

    @Override
    public List<MaTecnologia> consultarListaMaTecnologia(ParamConsulta paramConsulta) throws Exception {
        List<MaTecnologia> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p FROM MaTecnologias p "
                    + "LEFT JOIN MaTecnologiasMipres m ON m.maTecnologiasId = p.id "
                    + "WHERE m.maTecnologiasId IS NULL";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cups":
                            strQuery += " AND p.cups LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cupsDescipcion":
                            strQuery += " AND p.cupsDescipcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeCoberturaValor":
                            strQuery += " AND p.maeCoberturaValor = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY p.id DESC";

            List<MaTecnologias> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (MaTecnologias per : list) {
                listResult.add(castEntidadNegocioMaTecnologia(per));
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

    public static MaTecnologia castEntidadNegocioMaTecnologia(MaTecnologias per) {
        MaTecnologia obj = new MaTecnologia();
        obj.setId(per.getId());
        obj.setActivo(per.getActivo());
        obj.setCups(per.getCups());
        obj.setCupsDescipcion(per.getCupsDescipcion());
        obj.setMaeCoberturaValor(per.getMaeCoberturaValor());
        return obj;
    }

    @Override
    public List<SelectItem> consultarPrescripciones(String doc) throws Exception {
        List<SelectItem> listResult = new ArrayList<>();
        try {
            String strQuery = "SELECT p.id, p.numeroPrescripcion FROM MpPrescripciones p "
                    + "WHERE p.asegAfiliadosId.numeroDocumento = :doc ";
            strQuery += " ORDER BY p.id DESC";

            TypedQuery<Object[]> query = getEntityManager().createQuery(strQuery, Object[].class);
            query.setParameter("doc", doc);

            List<Object[]> resultList = query.getResultList();

            for (Object[] result : resultList) {
                int id = (int) result[0];
                String numeroPrescripcion = (String) result[1];
                listResult.add(new SelectItem(id, numeroPrescripcion));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public List<MpDireccionamiento> consultarListaDeDireccionamiento(int prescripcion, int item, int tipoTecnologia) throws Exception {
        List<MpDireccionamiento> listaResultado = new ArrayList<>();

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(" SELECT p FROM MpDireccionamientos p WHERE p.mpPrescripcionesId.id = :id ");
            switch (tipoTecnologia) {
                case 1:
                case 4:
                    strQuery.append(" AND p.mpPrescripcionMedicamentosId.id = :itemId ");
                    break;
                case 2:
                    strQuery.append(" AND p.mpPrescripcionTecnologiasId.id = :itemId ");
                    break;
                case 3:
                case 5:
                    strQuery.append(" AND p.mpPrescripcionInsumosId.id = :itemId ");
                    break;
                default:
                    break;
            }
            strQuery.append(" ORDER BY p.id DESC ");

            List<MpDireccionamientos> lista = getEntityManager()
                    .createQuery(strQuery.toString())
                    .setParameter("id", prescripcion)
                    .setParameter("itemId", item)
                    .getResultList();

            lista.forEach(mpD -> listaResultado.add(castNegocioEntidadD(mpD)));
        } catch (Exception e) {

            throw new Exception("Error al consultar la lista de direccionamientos", e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    public static MpDireccionamiento castNegocioEntidadD(MpDireccionamientos obj) {
        MpDireccionamiento per = new MpDireccionamiento();

        per.setId(obj.getId());
        per.setRespuestaDireccionamiento(obj.getRespuestaDireccionamiento());
        per.setEstado(obj.getEstado());
        per.setNombreSede(obj.getNombreSede());
        per.setFechaDireccionamiento(obj.getFechaDireccionamiento());
        per.setFechaMaxEntrega(obj.getFechaMaxEntrega());
        per.setEntregaCantidad(obj.getConsecutivoEntrega());
        per.setConsecutivoEntrega(obj.getConsecutivoEntrega());
        per.setEntregaTotal(obj.getEntregaTotal());
        per.setEntregadoNumero(obj.getEntregadoNumero());
        per.setJustificacionDireccionamiento(obj.getJustificacionDireccionamiento());
        per.setUsuarioAnula(obj.getUsuarioAnula());
        per.setTerminalAnula(obj.getTerminalAnula());
        per.setFechaHoraAnula(obj.getFechaHoraAnula());
        per.setPreeliminado(obj.getPreeliminado());
        per.setEliminado(obj.getEliminado());

        return per;
    }

    @Override
    public List<MpDireccionamientoEntregado> consultarListaDeDireccionamientoEntregado(int prescripcion) throws Exception {
        List<MpDireccionamientoEntregado> listaResultado = new ArrayList<>();

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(" SELECT p FROM MpDireccionamientoEntregados p WHERE p.mpDireccionamientoId.id = :id ");
            strQuery.append(" ORDER BY p.id DESC ");

            List<MpDireccionamientoEntregados> lista = getEntityManager()
                    .createQuery(strQuery.toString())
                    .setParameter("id", prescripcion)
                    .getResultList();

            lista.forEach(mp -> listaResultado.add(castNegocioEntidadD(mp)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al consultar la lista de direccionamientos entregados", e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    @Override
    public List<MpDireccionamientoEntregado> consultarListaDeDireccionamientoEntregadoPresItem(int prescripcion, int item, int tec) throws Exception {
        List<MpDireccionamientoEntregado> listaResultado = new ArrayList<>();

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(" SELECT p FROM MpDireccionamientoEntregados p WHERE p.mpPrescripcionId.id = :id ");
            switch (tec) {
                case 1:
                    strQuery.append(" AND p.mpPrescripicionMedicamentosId.id = :item ");
                    break;
                case 2:
                    strQuery.append(" AND p.mpPrescripicionTecnologiasId.id = :item ");
                    break;
                case 3:
                    strQuery.append(" AND p.mpPrescripicionInsumosId.id = :item ");
                    break;
                case 4:
                    strQuery.append(" AND p.mpPrescripicionMedicamentosId.id = :item ");
                    break;
                case 5:
                    strQuery.append(" AND p.mpPrescripicionInsumosId.id = :item ");
                    break;
                default:
                    break;
            }
            strQuery.append(" ORDER BY p.id DESC ");

            List<MpDireccionamientoEntregados> lista = getEntityManager()
                    .createQuery(strQuery.toString())
                    .setParameter("id", prescripcion)
                    .setParameter("item", item)
                    .getResultList();

            lista.forEach(mp -> listaResultado.add(castNegocioEntidadD(mp)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al consultar la lista de direccionamientos entregados", e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    public static MpDireccionamientoEntregado castNegocioEntidadD(MpDireccionamientoEntregados obj) {
        MpDireccionamientoEntregado per = new MpDireccionamientoEntregado();

        per.setId(obj.getId());
        per.setEstadoEntrega(obj.getEstadoEntrega());
        per.setCantidadEntrega(obj.getCantidadEntrega());
        per.setNumeroEntrega(obj.getNumeroEntrega());
        per.setFechaEntrega(obj.getFechaEntrega());
        per.setJustificacionDireccionamiento(obj.getJustificacionDireccionamiento());
        per.setValorReportado(obj.getValorReportado());
        per.setValorTotal(obj.getValorTotal());
//        if (obj.getUltimaEntrega() == 1 && obj.getUltimaEntrega() != null) {
//            per.setUltimaEntrega(true);
//        } else {
//            per.setUltimaEntrega(false);
//        }
//        per.setCopago(obj.getCopago());

        return per;
    }

    @Override
    public List<MpNoDireccionado> consultarListaDeNoDireccionamiento(int prescripcion, int item, int tipoTecnologia) throws Exception {
        List<MpNoDireccionado> listaResultado = new ArrayList<>();

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(" SELECT p FROM MpNoDireccionados p WHERE p.mpPrescripcionesId.id = :id ");
            switch (tipoTecnologia) {
                case 1:
                case 4:
                    strQuery.append(" AND p.mpPrescripcionMedicamentosId.id = :itemId ");
                    break;
                case 2:
                    strQuery.append(" AND p.mpPrescripcionTecnologiasId.id = :itemId ");
                    break;
                case 3:
                case 5:
                    strQuery.append(" AND p.mpPrescripcionInsumosId.id = :itemId ");
                    break;
                default:
                    break;
            }
            strQuery.append(" ORDER BY p.id DESC ");

            List<MpNoDireccionados> lista = getEntityManager()
                    .createQuery(strQuery.toString())
                    .setParameter("id", prescripcion)
                    .setParameter("itemId", item)
                    .getResultList();

            for (MpNoDireccionados mpNd : lista) {
                listaResultado.add(castNegocioEntidadNd(mpNd));
            }
        } catch (Exception e) {

            throw new Exception("Error al consultar la lista de no direccionamientos", e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    public static MpNoDireccionado castNegocioEntidadNd(MpNoDireccionados per) {
        MpNoDireccionado obj = new MpNoDireccionado();

        obj.setId(per.getId());
        obj.setJustificacionNoDireccionamiento(per.getJustificacionNoDireccionamiento());
        if (per.getMpPrescripcionesId() != null) {
            obj.setMpPrescripcionesId(new MpPrescripcion(per.getMpPrescripcionesId().getId()));
            obj.getMpPrescripcionesId().setNumeroPrescripcion(per.getMpPrescripcionesId().getNumeroPrescripcion());
        }
        obj.setConsecutivoTecnologia(per.getConsecutivoTecnologia());
        obj.setNumeroPrescripcionAsociada(per.getNumeroPrescripcionAsociada());
        obj.setConTecAsociada(per.getConTecAsociada());
        obj.setEstadoNoDireccionamiento(per.getEstadoNoDireccionamiento());
        obj.setCodigoNoDireccionamiento(per.getCodigoNoDireccionamiento());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        return obj;
    }

    @Override
    public int consultarCantidadListaSedePrestador(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM CntPrestadorSedes p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoHabilitacionSede":
                            strQuery += " AND p.codigoHabilitacion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreSede":
                            strQuery += " AND p.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "ubicacion.id":
                            strQuery += " AND p.ubicacionId = " + e.getValue() + " ";
                            break;
                        case "cntPrestador.maeTipoDocumentoId":
                            strQuery += " AND p.cntPrestadoresId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "cntPrestador.numeroDocumento":
                            strQuery += " AND p.cntPrestadoresId.numeroDocumento LIKE '%" + e.getValue() + "%' ";
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
    public List<CntPrestadorSede> consultarListaSedePrestador(ParamConsulta paramConsulta) throws Exception {
        List<CntPrestadorSede> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM CntPrestadorSedes p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoHabilitacionSede":
                            strQuery += " AND p.codigoHabilitacion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreSede":
                            strQuery += " AND p.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "ubicacion.id":
                            strQuery += " AND p.ubicacionId = " + e.getValue() + " ";
                            break;
                        case "cntPrestador.maeTipoDocumentoId":
                            strQuery += " AND p.cntPrestadoresId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "cntPrestador.numeroDocumento":
                            strQuery += " AND p.cntPrestadoresId.numeroDocumento LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntPrestador.razonSocial":
                            strQuery += " AND p.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
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
            List<CntPrestadorSedes> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (CntPrestadorSedes per : list) {
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

    public static CntPrestadorSede castEntidadNegocio(CntPrestadorSedes per) {
        CntPrestadorSede obj = new CntPrestadorSede();
        obj.setId(per.getId());
        obj.setUbicacionId(per.getUbicacionId());
        obj.setCodigoPrestador(per.getCodigoPrestador());
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
            CntPrestador cntPrestador = new CntPrestador();
            cntPrestador.setId(per.getCntPrestadoresId().getId());
            cntPrestador.setMaeTipoDocumentoId(per.getCntPrestadoresId().getMaeTipoDocumentoId());
            cntPrestador.setMaeTipoDocumentoCodigo(per.getCntPrestadoresId().getMaeTipoDocumentoCodigo());
            cntPrestador.setMaeTipoDocumentoValor(per.getCntPrestadoresId().getMaeTipoDocumentoValor());
            cntPrestador.setNumeroDocumento(per.getCntPrestadoresId().getNumeroDocumento());
            cntPrestador.setRazonSocial(per.getCntPrestadoresId().getRazonSocial());
            obj.setCntPrestador(cntPrestador);
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
    public void anularDireccionamiento(int id, Integer estado, String usuario, String terminal, Date fecha) throws Exception {
        try {
            if (estado == 3) {
                estado = 5;
                String hql = "UPDATE MpDireccionamientos SET "
                        + "estado = :estado, "
                        + "preEliminado = 1, "
                        + "usuarioAnula = :usuario, "
                        + "terminalAnula = :terminal, "
                        + "fechaHoraAnula = :fecha "
                        + "WHERE id = :id";
                Query query = getEntityManager().createQuery(hql);
                query.setParameter("estado", estado);
                query.setParameter("usuario", usuario);
                query.setParameter("terminal", terminal);
                query.setParameter("fecha", fecha);
                query.setParameter("id", id);
                query.executeUpdate();
                getEntityManager().flush();
            } else if (estado == 1) {
                String hql = "UPDATE MpDireccionamientos SET "
                        + "preEliminado = 1, "
                        + "usuarioAnula = :usuario, "
                        + "terminalAnula = :terminal, "
                        + "fechaHoraAnula = :fecha "
                        + " WHERE id = :id";
                Query query = getEntityManager().createQuery(hql);
                query.setParameter("usuario", usuario);
                query.setParameter("terminal", terminal);
                query.setParameter("fecha", fecha);
                query.setParameter("id", id);
                query.executeUpdate();
                getEntityManager().flush();
            }

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }

    }

    @Override
    public void EliminarDireccionamiento(int id) throws Exception {
        try {
            String hql = "UPDATE MpDireccionamientos SET "
                    + "eliminado = 1 "
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();
            getEntityManager().flush();

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }

    }

    @Override
    public Boolean consultarDireccionamientoCot(int prescripcion, int item, int tipoTecnologia) throws Exception {
        boolean resultado = false;

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(" SELECT p FROM MpDireccionamientos p WHERE p.mpPrescripcionesId.id = :id ");

            switch (tipoTecnologia) {
                case 1:
                case 4:
                    strQuery.append(" AND p.mpPrescripcionMedicamentosId.id = :itemId ");
                    break;
                case 2:
                    strQuery.append(" AND p.mpPrescripcionTecnologiasId.id = :itemId ");
                    break;
                case 3:
                case 5:
                    strQuery.append(" AND p.mpPrescripcionInsumosId.id = :itemId ");
                    break;
                default:
                    return false;
            }

            strQuery.append(" AND (p.eliminado IS NULL OR p.eliminado = false) ");
            strQuery.append(" ORDER BY p.consecutivoEntrega DESC ");

            List<?> lista = getEntityManager()
                    .createQuery(strQuery.toString())
                    .setParameter("id", prescripcion)
                    .setParameter("itemId", item)
                    .getResultList();

            if (lista != null && !lista.isEmpty()) {
                resultado = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al consultar la lista de direccionamientos", e);
        } finally {
            cerrarEntityManager();
        }

        return resultado;
    }

    @Override
    public List<MpDireccionamiento> consultarListaDeDireccionamientoDireccionado(int prescripcion, int item, int tipoTecnologia) throws Exception {
        List<MpDireccionamiento> listaResultado = new ArrayList<>();

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(" SELECT p FROM MpDireccionamientos p WHERE p.mpPrescripcionesId.id = :id ");
            switch (tipoTecnologia) {
                case 1:
                case 4:
                    strQuery.append(" AND p.mpPrescripcionMedicamentosId.id = :itemId ");
                    break;
                case 2:
                    strQuery.append(" AND p.mpPrescripcionTecnologiasId.id = :itemId ");
                    break;
                case 3:
                case 5:
                    strQuery.append(" AND p.mpPrescripcionInsumosId.id = :itemId ");
                    break;
                default:
                    break;
            }
            strQuery.append("AND (p.eliminado IS NULL OR p.eliminado = false) ");

            strQuery.append(" ORDER BY p.consecutivoEntrega DESC ");

            List<MpDireccionamientos> lista = getEntityManager()
                    .createQuery(strQuery.toString())
                    .setParameter("id", prescripcion)
                    .setParameter("itemId", item)
                    .getResultList();

            lista.forEach(mpD -> listaResultado.add(castNegocioEntidadDd(mpD)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al consultar la lista de direccionamientos", e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    @Override
    public List<Integer> consultarIdParaBorrar(int prescripcion, int item, int tipoTecnologia) throws Exception {
        List<Integer> listaResultado = new ArrayList<>();

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(" SELECT p.id FROM MpDireccionamientos p WHERE p.mpPrescripcionesId.id = :id ");
            strQuery.append(" AND p.estado = 3");
            switch (tipoTecnologia) {
                case 1:
                case 4:
                    strQuery.append(" AND p.mpPrescripcionMedicamentosId.id = :itemId ");
                    break;
                case 2:
                    strQuery.append(" AND p.mpPrescripcionTecnologiasId.id = :itemId ");
                    break;
                case 3:
                case 5:
                    strQuery.append(" AND p.mpPrescripcionInsumosId.id = :itemId ");
                    break;
                default:
                    break;
            }
            strQuery.append(" AND (p.respuestaDireccionamiento LIKE :codigo) ");
            strQuery.append(" AND (p.eliminado IS NULL OR p.eliminado = false) ");
            strQuery.append(" AND (p.preeliminado IS NULL OR p.preeliminado = false) ");
            strQuery.append(" ORDER BY p.consecutivoEntrega DESC ");

            listaResultado = getEntityManager()
                    .createQuery(strQuery.toString(), Integer.class)
                    .setParameter("id", prescripcion)
                    .setParameter("itemId", item)
                    .setParameter("codigo", "%CodSerTecAEntregar%")
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al consultar la lista de direccionamientos", e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }

    @Override
    public void borradoLogico(List<Integer> id) throws Exception {
        try {
            String hql = "UPDATE MpDireccionamientos SET "
                    + "eliminado = 1 "
                    + ", preeliminado = 1 "
                    + " WHERE id IN :ids";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("ids", id);
            query.executeUpdate();
            getEntityManager().flush();

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }

    }

    public static MpDireccionamiento castNegocioEntidadDd(MpDireccionamientos obj) {
        MpDireccionamiento per = new MpDireccionamiento();

        per.setId(obj.getId());
        per.setTipoTecnologia(obj.getTipoTecnologia());
        if (obj.getMpPrescripcionesId() != null) {
            MpPrescripcion prescripcion = new MpPrescripcion();
            prescripcion.setId(obj.getMpPrescripcionesId().getId());
            prescripcion.setNumeroPrescripcion(obj.getMpPrescripcionesId().getNumeroPrescripcion());
            per.setMpPrescripcionId(prescripcion);
        }
        if (obj.getMpPrescripcionTecnologiasId() != null) {
            MpPrescripcionTecnologia prescripcionT = new MpPrescripcionTecnologia();
            prescripcionT.setId(obj.getMpPrescripcionTecnologiasId().getId());
            per.setMpPrescripcionTecnologiaId(prescripcionT);
        }
        if (obj.getMpPrescripcionMedicamentosId() != null) {
            MpPrescripcionMedicamento prescripcionM = new MpPrescripcionMedicamento();
            prescripcionM.setId(obj.getMpPrescripcionMedicamentosId().getId());
            per.setMpPrescripcionMedicamentoId(prescripcionM);
        }
        if (obj.getMpPrescripcionInsumosId() != null) {
            MpPrescripcionInsumo prescripcionI = new MpPrescripcionInsumo();
            prescripcionI.setId(obj.getMpPrescripcionInsumosId().getId());
            per.setMpPrescripcionInsumoId(prescripcionI);
        }

        per.setMaeTipoDocumentoPrestadorId(obj.getMaeTipoDocumentoPrestadorId());
        per.setMaeTipoDocumentoPrestadorCodigo(obj.getMaeTipoDocumentoPrestadorCodigo());
        per.setMaeTipoDocumentoPrestadorValor(obj.getMaeTipoDocumentoPrestadorValor());
        per.setPrestadorNumeroDocumentoStr(obj.getPrestadorNumeroDocumento().toString());
        per.setCodigoPrestadorSede(obj.getCodigoPrestadorSede());
        per.setValorTecContratada(obj.getValorTecContratada());

        per.setRespuestaDireccionamiento(obj.getRespuestaDireccionamiento());

        per.setPrestadorNumeroDocumento(new BigInteger(obj.getPrestadorNumeroDocumento().toString()));

        per.setUltimoDireccionamiento(obj.getUltimoDireccionamiento());
        per.setPrestadorRazonSocial(obj.getPrestadorRazonSocial());
        per.setSedeCodigoHabilitacion(obj.getSedeCodigoHabilitacion());
        per.setSedeDireccionPrestador(obj.getSedeDireccionPrestador());
        per.setSedeTelefonoPrestador(obj.getSedeTelefonoPrestador());
        per.setEstado(obj.getEstado());
        per.setFechaDireccionamiento(obj.getFechaDireccionamiento());
        per.setFechaMaxEntrega(obj.getFechaMaxEntrega());
        per.setEntregaTotal(obj.getEntregaTotal());
        per.setEntregadoNumero(obj.getEntregadoNumero());
        per.setEntregadoTotal(obj.getEntregadoTotal());
        per.setEntregadoPendiente(obj.getEntregadoPendiente());
        per.setJustificacionDireccionamiento(obj.getJustificacionDireccionamiento());
        per.setEnvioCorreoAuto(obj.getEnvioCorreoAuto());
        per.setCausaNoEntregaCod(obj.getCausaNoEntregaCod());
        per.setCodigoMpEntrega(obj.getCodigoMpEntrega());
        per.setCodigoMpPropio(obj.getCodigoMpPropio());
        per.setSubEntrega(obj.getSubEntrega());
        per.setCodigoPrestadorSede(obj.getCodigoPrestadorSede());
        per.setConsecutivoEntrega(obj.getConsecutivoEntrega());
//        per.setUbicacionSedeId((obj.getUbicacionSedeId()));
        per.setMaeRegionSedeId(obj.getMaeRegionSedeId());
        per.setMaeRegionSedeCodigo(obj.getMaeRegionSedeCodigo());
        per.setMaeRegionSedeValor(obj.getMaeRegionSedeCodigo());
        per.setDireccionSede(obj.getDireccionSede());
        per.setNombreSede(obj.getNombreSede());
        per.setCodigoSede(obj.getCodigoSede());
        per.setCodigoHabilitacionSede(obj.getCodigoHabilitacionSede());
        per.setFaxSede(obj.getFaxSede());
        per.setTelefonoCitasSede(obj.getTelefonoCitasSede());
        per.setCorreoElectronicoSede(obj.getCorreoElectronicoSede());
        per.setTelefonoAdministrativoSede(obj.getTelefonoAdministrativoSede());
        per.setDireccionamientoEsEstadoAnulado(obj.getDireccionamientoEsEstadoAnulado());
        per.setFechaAnulacionDireccionamiento(obj.getFechaAnulacionDireccionamiento());
//        per.setMaeTipoDocumentoPrestadorId(obj.getMaeTipoDocumentoId());
//        per.setMaeTipoDocumentoPrestadorCodigo(obj.getMaeTipoDocumentoCodigo());
//        per.setMaeTipoDocumentoPrestadorValor(obj.getMaeTipoDocumentoValor());
        per.setNumeroDocumentoPaciente(obj.getNumeroDocumentoPaciente());
        per.setMaeTipoDocumentoPacienteId(obj.getMaeTipoDocumentoId());
        per.setMaeTipoDocumentoPacienteCodigo(obj.getMaeTipoDocumentoCodigo());
        per.setMaeTipoDocumentoPacienteValor(obj.getMaeTipoDocumentoValor());
        per.setCodigoTipoDireccionamiento(obj.getCodigoTipoDireccionamiento());
        per.setCodigoEntregaParcial(obj.getCodigoEntregaParcial());
        per.setCodigoEntregaDiferida(obj.getCodigoEntregaDiferida());
        per.setEsEntregaParcial(obj.getEsEntregaParcial());
        per.setEsEntregaDiferida(obj.getEsEntregaDiferida());
        per.setNumeroPrescripcionAso(obj.getNumeroPrescripcionAsoc());
        per.setConsecutivoTecAsociada(obj.getConsecutivoTecAsociada());

        per.setPreeliminado(obj.getPreeliminado());
        per.setEliminado(obj.getEliminado());

        return per;
    }

    @Override
    public Integer consultarTutelas(Integer id) throws Exception {
        try {
            String strQuery = "SELECT COUNT(p) FROM TuTutelas p WHERE p.tuPersonasId.asegAfiliadoId = :id";

            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("id", id);
            int cant = ((Number) query.getSingleResult()).intValue();
            cerrarEntityManager();
            return cant;

        } catch (NoResultException e) {
            return 0;
        } catch (Exception e) {
            throw new Exception("Error consultando tutelas", e);
        }
    }

    @Override
    public String consultarCorreoPrestador(BigInteger prestador, String habilitacion) throws Exception {
        try {
            String strQuery = "SELECT p.contacto "
                    + "FROM CntPrestadorContactos p "
                    + "JOIN p.cntPrestadoresId ps "
                    + "JOIN p.cntPrestadorSedesId sp "
                    + "WHERE ps.numeroDocumento = :prestador "
                    + "AND p.maeTipoContactoCodigo = '3' "
                    + "AND p.maeAreaContactoCodigo = '6' "
                    + "AND p.autorizaEnvio = true "
                    + "AND p.activo = true "
                    + "AND sp.codigoHabilitacion = :habilitacion "
                    + "ORDER BY p.id DESC";

            TypedQuery<String> query = getEntityManager().createQuery(strQuery, String.class);
            query.setParameter("prestador", prestador.toString());
            query.setParameter("habilitacion", habilitacion);

            query.setMaxResults(1);
            String correo = query.getSingleResult();

            cerrarEntityManager();
            return correo;

        } catch (NoResultException e) {
            return null; // No hay ningún contacto que cumpla con las condiciones
        } catch (Exception e) {
            throw new Exception("Error consultando correo del prestador", e);
        }
    }

    @Override
    public int consultarCantidadListaContratoDetalle(ParamConsulta paramConsulta, Integer id, Integer tipo) throws Exception {
        int cant = 0;
        Date fechaActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String strQuery = "SELECT COUNT(c.id) FROM CntContratoDetalles c "
                    + "WHERE c.id > 0 "
                    + " AND c.maTecnologiaId= " + id + " "
                    + " AND c.tipoTecnologia = " + tipo + " "
                    + " AND c.activo = 1 "
                    + " AND c.cntContratosId.maeEstadoContratoCodigo = '01' " //contrato vigente
                    + " AND c.cntContratosId.fechaFin >= '" + sdf.format(fechaActual) + "'"; // con fecha fin mayor o igual a la actual
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeTipoDocumentoId":
                            strQuery += "AND p.maeTipoDocumentoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "documento":
                            strQuery += "AND p.documento LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "nombres":
                            strQuery += "AND p.nombres LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "apellidos":
                            strQuery += "AND p.apellidos LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "telefono":
                            strQuery += "AND p.telefono LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "razonSocial":
                            strQuery += "AND p.razonSocial LIKE '%" + (String) e.getValue() + "%'";
                            break;
                    }
                }
            }
            Query query = getEntityManager().createQuery(strQuery);
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
    public List<CntContratoDetalle> consultarListaContratoDetalle(ParamConsulta paramConsulta, Integer id, Integer tipo) throws Exception {

        List<CntContratoDetalle> listResult = new ArrayList();
        Date fechaActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            String strQuery = "SELECT c FROM CntContratoDetalles c "
                    + " JOIN CntContratos contrato ON c.cntContratosId = contrato.id "
                    + " JOIN MpPrestadores prestador ON contrato.cntPrestadoresId = prestador.cntPrestadoresId "
                    + " WHERE c.id > 0 "
                    + " AND c.maTecnologiaId= " + id + " "
                    + " AND c.tipoTecnologia = " + tipo + " "
                    + " AND c.activo = 1 "
                    + " AND contrato.maeEstadoContratoCodigo = '01' "
                    + " AND c.cntContratosId.fechaFin >= '" + sdf.format(fechaActual) + "'"
                    + " AND prestador.activo = true ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntContratoSede.cntPrestadorSede.nombreSede":
                            strQuery += " AND c.cntContratoSedesId.cntPrestadorSedesId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntContratoSede.cntPrestadorSede.codigoHabilitacionSede":
                            strQuery += " AND c.cntContratoSedesId.cntPrestadorSedesId.codigoHabilitacion LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY c.id DESC";

            List<CntContratoDetalles> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();

            for (CntContratoDetalles per : list) {
                listResult.add(castDetalleContrato(per));
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

    public static CntContratoDetalle castDetalleContrato(final CntContratoDetalles per) {
        final CntContratoDetalle obj = new CntContratoDetalle();
        obj.setId(per.getId());
        obj.setMaServicioHabilitacionValor(per.getMaServicioHabilitacionValor());
        obj.setValorContratado(per.getValorContratado());

        if (per.getCntContratoSedesId() != null) {
            final CntContratoSede contratoSede = new CntContratoSede();
            contratoSede.setCntPrestadorSede(castSedes(per.getCntContratoSedesId().getCntPrestadorSedesId()));
            obj.setCntContratoSede(contratoSede);
        }
        if (per.getCntContratosId() != null) {
            final CntContrato contrato = new CntContrato();
            contrato.setId(per.getCntContratosId().getId());
            contrato.setCntPrestador(castContrato(per.getCntContratosId().getCntPrestadoresId()));
            obj.setCntContrato(contrato);
        }
        return obj;
    }

    public static CntPrestador castContrato(final CntPrestadores per) {
        final CntPrestador obj = new CntPrestador();
        obj.setId(per.getId());
        obj.setRazonSocial(per.getRazonSocial());
        return obj;
    }

    public static CntPrestadorSede castSedes(final CntPrestadorSedes per) {
        final CntPrestadorSede obj = new CntPrestadorSede();
        obj.setId(per.getId());
        obj.setCodigoPrestador(per.getCodigoPrestador());
        obj.setNombreSede(per.getNombre());
        obj.setCodigoSede(per.getCodigo());
        obj.setCodigoHabilitacionSede(per.getCodigoHabilitacion());
        obj.setFax(per.getFax());
        obj.setTelefonoCitas(per.getTelefonoCitas());
        obj.setTelefonoAdministrativo(per.getTelefonoAdministrativo());
        obj.setCorreoElectronico(per.getCorreoElectronico());
        obj.setDireccion(per.getDireccion());
        obj.setUbicacionId(per.getUbicacionId());
        if (per.getCntPrestadoresId() != null) {
            obj.setCntPrestador(castPrestador(per.getCntPrestadoresId()));
        }
        return obj;
    }

    public static CntPrestador castPrestador(final CntPrestadores per) {
        final CntPrestador obj = new CntPrestador();
        obj.setId(per.getId());
        obj.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setNumeroDocumento(per.getNumeroDocumento());
        obj.setRazonSocial(per.getRazonSocial());
        obj.setUnionTemporal(per.getUnionTemporal());
        return obj;
    }

    @Override
    public MpPrescripcion consultarPrescripcionS(Integer id) throws Exception {
        MpPrescripcion entrega = new MpPrescripcion();
        try {

            String strQuery = "SELECT p FROM MpPrescripciones p WHERE p.id = :id";
            TypedQuery<MpPrescripciones> query = getEntityManager().createQuery(strQuery, MpPrescripciones.class);
            query.setParameter("id", id);
            MpPrescripciones obj = query.getSingleResult();
            entrega = castEntidadNegocioDetallePres(obj);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return entrega;
    }

    @Override
    public MpPrescripcion traerDatosPrescripcion(Integer id) throws Exception {
        MpPrescripcion entrega = new MpPrescripcion();
        try {

            String strQuery = "SELECT p FROM MpPrescripciones p WHERE p.id = :id";
            TypedQuery<MpPrescripciones> query = getEntityManager().createQuery(strQuery, MpPrescripciones.class);
            query.setParameter("id", id);
            MpPrescripciones obj = query.getSingleResult();
            entrega = castEntidadNegocioDatos(obj);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return entrega;
    }

    public static MpPrescripcion castEntidadNegocioDatos(MpPrescripciones ent) {
        MpPrescripcion obj = new MpPrescripcion();
        obj.setId(ent.getId());
        obj.setNumeroPrescripcion(ent.getNumeroPrescripcion());
        obj.setFechaPrescripcion(ent.getFechaPrescripcion());
        obj.setHoraPrescripcion(ent.getHoraPrescripcion());
        return obj;
    }

    public static MpPrescripcion castEntidadNegocioDetallePres(MpPrescripciones ent) {
        MpPrescripcion obj = new MpPrescripcion();
        obj.setId(ent.getId());
        obj.setNumeroPrescripcion(ent.getNumeroPrescripcion());
        obj.setCodAmbAte(ent.getCodAmbAte());

        if (ent.getAsegAfiliadosId() != null) {
            obj.setAsegAfiliado(new AsegAfiliado(ent.getAsegAfiliadosId().getId()));
            obj.getAsegAfiliado().setNumeroDocumento(ent.getAsegAfiliadosId().getNumeroDocumento());
            obj.getAsegAfiliado().setPrimerNombre(ent.getAsegAfiliadosId().getPrimerNombre());
            obj.getAsegAfiliado().setSegundoNombre(ent.getAsegAfiliadosId().getSegundoNombre());
            obj.getAsegAfiliado().setPrimerApellido(ent.getAsegAfiliadosId().getPrimerApellido());
            obj.getAsegAfiliado().setSegundoApellido(ent.getAsegAfiliadosId().getSegundoApellido());
            obj.getAsegAfiliado().setRegimen(ent.getAsegAfiliadosId().getRegimen());
            obj.getAsegAfiliado().setGenero(ent.getAsegAfiliadosId().getGenero());
        }
        obj.setMaDiagnosticoPrincipalCodigo(ent.getMaDiagnosticoPrincipalCodigo());
        obj.setMaDiagnosticoPrincipalValor(ent.getMaDiagnosticoPrincipalValor());

        return obj;
    }

    @Override
    public MpPrescripcionMedicamento consultarMedicamentoSuministro(Integer id) throws Exception {
        MpPrescripcionMedicamento entrega = new MpPrescripcionMedicamento();
        try {

            String strQuery = "SELECT p FROM MpPrescripcionMedicamentos p WHERE p.id = :id";
            TypedQuery<MpPrescripcionMedicamentos> query = getEntityManager().createQuery(strQuery, MpPrescripcionMedicamentos.class);
            query.setParameter("id", id);
            MpPrescripcionMedicamentos obj = query.getSingleResult();
            entrega = castEntidadNegocioDetalleMed(obj);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return entrega;
    }

    public static MpPrescripcionMedicamento castEntidadNegocioDetalleMed(MpPrescripcionMedicamentos ent) {
        MpPrescripcionMedicamento obj = new MpPrescripcionMedicamento();
        obj.setId(ent.getId());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        switch (ent.getTipoTecnologia()) {
            case 1:
                obj.setDescripcionMedicamentoPrincipioActivo(ent.getDescripcionMedicamentoPrincipioActivo());
                break;
            case 4:
                obj.setMaeProductosNutricionalesValor(ent.getMaeProductosNutricionalesValor());
                break;

            default:
                break;
        }
        obj.setCantidadTotalFormulada(ent.getCantidadTotalFormulada());

        return obj;
    }

    @Override
    public MpPrescripcionTecnologia consultarTecnologiaSuministro(Integer id) throws Exception {
        MpPrescripcionTecnologia entrega = new MpPrescripcionTecnologia();
        try {

            String strQuery = "SELECT p FROM MpPrescripcionTecnologias p WHERE p.id = :id";
            TypedQuery<MpPrescripcionTecnologias> query = getEntityManager().createQuery(strQuery, MpPrescripcionTecnologias.class);
            query.setParameter("id", id);
            MpPrescripcionTecnologias obj = query.getSingleResult();
            entrega = castEntidadNegocioDetalleTec(obj);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return entrega;
    }

    public static MpPrescripcionTecnologia castEntidadNegocioDetalleTec(MpPrescripcionTecnologias ent) {
        MpPrescripcionTecnologia obj = new MpPrescripcionTecnologia();
        obj.setId(ent.getId());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        obj.setCantidadFormulada(ent.getCantidadFormulada());

        return obj;
    }

    @Override
    public MpPrescripcionInsumo consultarInsumoSuministro(Integer id) throws Exception {
        MpPrescripcionInsumo entrega = new MpPrescripcionInsumo();
        try {

            String strQuery = "SELECT p FROM MpPrescripcionInsumos p WHERE p.id = :id";
            TypedQuery<MpPrescripcionInsumos> query = getEntityManager().createQuery(strQuery, MpPrescripcionInsumos.class);
            query.setParameter("id", id);
            MpPrescripcionInsumos obj = query.getSingleResult();
            entrega = castEntidadNegocioDetalleIns(obj);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return entrega;
    }

    public static MpPrescripcionInsumo castEntidadNegocioDetalleIns(MpPrescripcionInsumos ent) {
        MpPrescripcionInsumo obj = new MpPrescripcionInsumo();
        obj.setId(ent.getId());
        obj.setTipoTecnologia(ent.getTipoTecnologia());

        switch (ent.getTipoTecnologia()) {
            case 3:
                obj.setMaeDispositivosNombre(ent.getMaedispositivosValor());
                break;
            case 5:
                obj.setMaeServiciosComplementariosNombre(ent.getMaeServiciosComplementariosValor());
                break;

            default:
                break;
        }
        obj.setCantidadTotalEntrega(ent.getCantidadTotalEntrega());

        return obj;
    }

    @Override
    public MpDireccionamiento consultarDireccionamientoS(Integer id) throws Exception {
        MpDireccionamiento entrega = new MpDireccionamiento();
        try {

            String strQuery = "SELECT p FROM MpDireccionamientos p WHERE p.id = :id";
            TypedQuery<MpDireccionamientos> query = getEntityManager().createQuery(strQuery, MpDireccionamientos.class);
            query.setParameter("id", id);
            MpDireccionamientos obj = query.getSingleResult();
            entrega = castEntidadNegocioD(obj);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return entrega;
    }

    public static MpDireccionamiento castEntidadNegocioD(MpDireccionamientos ent) {
        MpDireccionamiento obj = new MpDireccionamiento();
        obj.setId(ent.getId());
        obj.setEntregaTotal(ent.getEntregaTotal());
        return obj;
    }

    @Override
    public MpPrescripcionAuditoria consultarAudirotiaSis(Integer id, Integer tipo, Integer idTipo) throws Exception {
        MpPrescripcionAuditoria aud = null;

        try {
            if (id == null || idTipo == null) {
                return null;
            }

            String strQuery = "SELECT p FROM MpPrescripcionItemAuditoria p WHERE p.mpPrescripcionId.id = :id";
            switch (tipo) {
                case 1:
                    strQuery += " AND p.mpPrescripcionMedicamentoId.id = :idTipo";
                    break;
                case 2:
                    strQuery += " AND p.mpPrescripcionTecnologiaId.id = :idTipo";
                    break;
                case 3:
                    strQuery += " AND p.mpPrescripcionInsumoId.id = :idTipo";
                    break;
                default:
                    return null;
            }
            strQuery += " ORDER BY p.id DESC";

            TypedQuery<MpPrescripcionItemAuditoria> query = getEntityManager().createQuery(strQuery, MpPrescripcionItemAuditoria.class);
            query.setParameter("id", id);
            query.setParameter("idTipo", idTipo);
            query.setMaxResults(1);

            List<MpPrescripcionItemAuditoria> resultList = query.getResultList();
            if (!resultList.isEmpty()) {
                aud = castEntidadNegocioAud(resultList.get(0));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return aud;
    }

    @Override
    public MpPrescripcionAuditoria consultarAudirotiaS(Integer id) throws Exception {
        MpPrescripcionAuditoria aud = new MpPrescripcionAuditoria();
        try {

            String strQuery = "SELECT p FROM MpPrescripcionItemAuditoria p WHERE p.mpPrescripcionId.id = :id";

            TypedQuery<MpPrescripcionItemAuditoria> query = getEntityManager().createQuery(strQuery, MpPrescripcionItemAuditoria.class);
            query.setParameter("id", id);

            MpPrescripcionItemAuditoria obj = query.getSingleResult();
            aud = castEntidadNegocioAud(obj);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return aud;
    }

    public static MpPrescripcionAuditoria castEntidadNegocioAud(MpPrescripcionItemAuditoria ent) {
        MpPrescripcionAuditoria obj = new MpPrescripcionAuditoria();
        obj.setId(ent.getId());
        obj.setNotaAuditoria(ent.getNotaAuditoria());
        return obj;
    }

    @Override
    public MpCodigoInsumo consultarPorCodigoInsumo(String cod) throws Exception {
        MpCodigoInsumo codigoInsumo = new MpCodigoInsumo();
        try {

            String strQuery = "SELECT cod FROM MpCodigoInsumos cod WHERE cod.codigoMipres = :codigoMipres ";
            strQuery += " AND cod.activo = TRUE ";
            TypedQuery<MpCodigoInsumos> query = getEntityManager().createQuery(strQuery, MpCodigoInsumos.class);
            query.setParameter("codigoMipres", cod);
            MpCodigoInsumos obj = query.getSingleResult();
            codigoInsumo = castEntidadNegocioCodigoInsumo(obj);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return codigoInsumo;
    }

    public static MpCodigoInsumo castEntidadNegocioCodigoInsumo(MpCodigoInsumos ent) {
        MpCodigoInsumo obj = new MpCodigoInsumo();
        obj.setId(ent.getId());
        obj.setCodigoMipres(ent.getCodigoMipres());
        obj.setDescripcion(ent.getDescripcion());
        obj.setActivo(ent.getActivo());
        return obj;
    }

    @Override
    public int consultarCantidadListaPorCodigoInsumo(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MpCodigoInsumos p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoMipres":
                            strQuery += " AND p.codigoMipres LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += " AND p.descripcion LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "AND p.activo = TRUE ";
            cant = (int) (long) getEntityManager().createQuery(strQuery)
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
    public List<MpCodigoInsumo> consultarListaPorCodigoInsumo(ParamConsulta paramConsulta) throws Exception {
        List<MpCodigoInsumo> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "SELECT p FROM MpCodigoInsumos p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoMipres":
                            strQuery += " AND p.codigoMipres LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += " AND p.descripcion LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += " AND p.activo = TRUE ";
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " p.id DESC ";
            }
            List<MpCodigoInsumos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (MpCodigoInsumos per : list) {
                listResult.add(castEntidadNegocioCodigoM(per));
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

    public static MpCodigoInsumo castEntidadNegocioCodigoM(MpCodigoInsumos per) {
        MpCodigoInsumo obj = new MpCodigoInsumo();
        obj.setId(per.getId());
        obj.setCodigoMipres(per.getCodigoMipres());
        obj.setDescripcion(per.getDescripcion());
        obj.setActivo(per.getActivo());
        return obj;
    }

    @Override
    public MpDireccionamientoEntregado actualizarCiclo(Boolean cierre, Integer id) throws Exception {
        MpDireccionamientoEntregado obj = null;
        try {
            if (cierre != false) {
                String hql = "UPDATE MpDireccionamientoEntregados SET "
                        + " estadoSuministro = :estadoSuministro, "
                        + " cierreSuministro = :cierreSuministro, "
                        + " anularSuministro = :anularSuministro "
                        + " WHERE id = :id";
                Query query = getEntityManager().createQuery(hql);
                query.setParameter("estadoSuministro", 2);
                query.setParameter("cierreSuministro", true);
                query.setParameter("anularSuministro", false);
                query.setParameter("id", id);
                query.executeUpdate();
                getEntityManager().flush();
            } else {
                String hql = "UPDATE MpDireccionamientoEntregados SET "
                        + " estadoSuministro = :estadoSuministro, "
                        + " cierreSuministro = :cierreSuministro "
                        + " WHERE id = :id";
                Query query = getEntityManager().createQuery(hql);
                query.setParameter("estadoSuministro", 0);
                query.setParameter("cierreSuministro", false);
                query.setParameter("id", id);
                query.executeUpdate();
                getEntityManager().flush();
            }

            obj = castEntidadNegocioEntrega((MpDireccionamientoEntregados) getEntityManager().find(MpDireccionamientoEntregados.class, id));

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public MpDireccionamientoEntregado actualizarAnula(Boolean anula, Integer id) throws Exception {
        MpDireccionamientoEntregado obj = null;
        try {
            if (anula != false) {
                String hql = "UPDATE MpDireccionamientoEntregados SET "
                        + " estadoSuministro = :estadoSuministro, "
                        + " cierreSuministro = :cierreSuministro, "
                        + " anularSuministro = :anularSuministro "
                        + " WHERE id = :id";
                Query query = getEntityManager().createQuery(hql);
                query.setParameter("estadoSuministro", 0);
                query.setParameter("cierreSuministro", false);
                query.setParameter("anularSuministro", true);
                query.setParameter("id", id);
                query.executeUpdate();
                getEntityManager().flush();
            }

            obj = castEntidadNegocioEntrega((MpDireccionamientoEntregados) getEntityManager().find(MpDireccionamientoEntregados.class, id));

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public MpDireccionamientoEntregado ActualizarCompletaUltima(Boolean val, Integer valor, Integer id) throws Exception {
        MpDireccionamientoEntregado obj = null;
        try {
            boolean va  = false;
            Integer va2 = 0;
            if (null != valor) {

                switch (valor) {
                    case 1: {
                        if (val == true) {
                            va  = true;
                        } else {
                            va  = false;
                        }
                        String hql = "UPDATE MpDireccionamientoEntregados SET "
                                + " ultimaEntrega = :ultimaEntrega "
                                + " WHERE id = :id";
                        Query query = getEntityManager().createQuery(hql);
                        query.setParameter("ultimaEntrega", va);
                        query.setParameter("id", id);
                        query.executeUpdate();
                        getEntityManager().flush();
                        break;
                    }
                    case 2: {
                        if (val == true) {
                            va2 = 1;
                        } else {
                            va2 = 0;
                        }
                        String hql = "UPDATE MpDireccionamientoEntregados SET "
                                + " entregaCompleta = :entregaCompleta "
                                + " WHERE id = :id";
                        Query query = getEntityManager().createQuery(hql);
                        query.setParameter("entregaCompleta", va2);
                        query.setParameter("id", id);
                        query.executeUpdate();
                        getEntityManager().flush();
                        break;
                    }
                    case 3: {
                        String hql = "UPDATE MpDireccionamientoEntregados SET "
                                + " afectaPresupuesto = :afectaPresupuesto "
                                + " WHERE id = :id";
                        Query query = getEntityManager().createQuery(hql);
                        query.setParameter("afectaPresupuesto", val);
                        query.setParameter("id", id);
                        query.executeUpdate();
                        getEntityManager().flush();
                        break;
                    }
                    case 4: {
                        String hql = "UPDATE MpDireccionamientoEntregados SET "
                                + " cierreCiclo = :cierreCiclo "
                                + " WHERE id = :id";
                        Query query = getEntityManager().createQuery(hql);
                        query.setParameter("cierreCiclo", val);
                        query.setParameter("id", id);
                        query.executeUpdate();
                        getEntityManager().flush();
                        break;
                    }
                    default:
                        break;
                }
            }

            obj = castEntidadNegocioEntregaSuministro((MpDireccionamientoEntregados) getEntityManager().find(MpDireccionamientoEntregados.class, id));

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public MpDireccionamientoEntregado consultarEntregaSinDireccionamiento(Integer id) throws Exception {
        MpDireccionamientoEntregado obj = null;
        try {

            obj = castEntidadNegocioEntrega((MpDireccionamientoEntregados) getEntityManager().find(MpDireccionamientoEntregados.class, id));

        } catch (NoResultException e) {

        } catch (Exception e) {
            throw new Exception("Error consultando MpDireccionamientoEntregado", e);
        }
        return obj;
    }

    @Override
    public void guardarSuministro(MpEntregaSuministro obj) throws Exception {
        try {
//            String strQuery = "SELECT MAX(e.id) FROM MpEntregaSuministros  e";
//            TypedQuery<Integer> query = getEntityManager().createQuery(strQuery, Integer.class);
//            Integer id = query.getSingleResult();
//            if (id == null) {
//                obj.setIdSuministro("");
//            } else {
//                obj.setIdSuministro("");
//            }
            MpEntregaSuministros entidad = castNegocioEntidadEntregaSuministro(obj);
            getEntityManager().persist(entidad);
            getEntityManager().flush();
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    public MpEntregaSuministros castNegocioEntidadEntregaSuministro(MpEntregaSuministro obj) {
        MpEntregaSuministros per = new MpEntregaSuministros();

        per.setMpDireccionamientoEntregadosId(new MpDireccionamientoEntregados(obj.getMpDireccionamientoEntregadoId().getId()));
        per.setUltimaEntrega(obj.getUltimaEntrega());

        per.setNumeroPrescripcionAsociada(obj.getNumeroPrescripcionAsociada());
        per.setEstadoMipres(1);
        per.setAnulado(false);
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setFechaHoraSuminisro(obj.getFechaSuministro());
        per.setIdSuministro(obj.getIdSuministro());
        return per;
    }

    @Override
    public MpEntregaSuministro consultarSuministroDeEntrega(Integer id) throws Exception {
        MpEntregaSuministro suministro = null;
        try {

            String strQuery = "SELECT e FROM MpEntregaSuministros e WHERE e.mpDireccionamientoEntregadosId.id = :id ";
            strQuery += " AND e.estadoMipres IN (1,2)";

            TypedQuery<MpEntregaSuministros> query = getEntityManager().createQuery(strQuery, MpEntregaSuministros.class);
            query.setParameter("id", id);
            MpEntregaSuministros obj = query.getSingleResult();
            suministro = castEntidadNegocioSuministro(obj);

        } catch (Exception e) {
            suministro = null;
        } finally {
            cerrarEntityManager();
        }
        return suministro;
    }

    public MpEntregaSuministro castEntidadNegocioSuministro(MpEntregaSuministros per) {
        MpEntregaSuministro obj = new MpEntregaSuministro();
        if (per.getMpDireccionamientoEntregadosId() != null) {
            obj.setMpDireccionamientoEntregadoId(new MpDireccionamientoEntregado(per.getMpDireccionamientoEntregadosId().getId()));
            obj.setNumeroEntrega(per.getMpDireccionamientoEntregadosId().getNumeroEntrega());
            obj.setNumeroLote(per.getMpDireccionamientoEntregadosId().getNumeroLote());
            obj.setValorEntregado(per.getMpDireccionamientoEntregadosId().getValorTotal());
            obj.setCantidadEntrega(per.getMpDireccionamientoEntregadosId().getCantidadEntrega());
            if (per.getMpDireccionamientoEntregadosId().getMpPrescripicionMedicamentosId() != null) {
                obj.setConsecutivo(per.getMpDireccionamientoEntregadosId().getMpPrescripicionMedicamentosId().getConsecutivoOrden());
            } else if (per.getMpDireccionamientoEntregadosId().getMpPrescripicionTecnologiasId() != null) {
                obj.setConsecutivo(per.getMpDireccionamientoEntregadosId().getMpPrescripicionTecnologiasId().getConsecutivoOrden());
            } else if (per.getMpDireccionamientoEntregadosId().getMpPrescripicionInsumosId() != null) {
                obj.setConsecutivo(per.getMpDireccionamientoEntregadosId().getMpPrescripicionInsumosId().getConsecutivoOrden());
            }
        }
        obj.setId(per.getId());
        obj.setUltimaEntrega(per.getUltimaEntrega());
        obj.setNumeroPrescripcionAsociada(per.getNumeroPrescripcionAsociada());
        obj.setEstadoMipres(per.getEstadoMipres());
        obj.setAnulado(per.getAnulado());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setFechaSuministro(per.getFechaHoraSuminisro());
        obj.setIdSuministro(per.getIdSuministro());
        return obj;
    }

    @Override
    public Boolean anularSuministro(Integer id) throws Exception {
        Boolean res = false;
        Integer estadoMipres;
        Integer valor = null;

        // Consultar el estadoMipres actual del suministro
        String strQuery = "SELECT e.estadoMipres FROM MpEntregaSuministros e WHERE e.id = :id";
        TypedQuery<Integer> query = getEntityManager().createQuery(strQuery, Integer.class);
        try {
            estadoMipres = query.setParameter("id", id).getSingleResult();

            if (estadoMipres != null) {
                if (estadoMipres == 1) {
                    valor = 4;
                } else if (estadoMipres == 2) {
                    valor = 3;
                } else if (estadoMipres == 9) {
                    valor = null;
                }
            }

            if (valor != null) {
                res = true;
                String hql = "UPDATE MpEntregaSuministros SET estadoMipres = :valor, fechaHoraAnula = :fecha WHERE id = :id";

                Query updateQuery = getEntityManager().createQuery(hql);
                updateQuery.setParameter("valor", valor);
                updateQuery.setParameter("id", id);
                updateQuery.setParameter("fecha", new Date());

                updateQuery.executeUpdate();
                getEntityManager().flush();
            } else {
                res = false;
            }

        } catch (NoResultException e) {
            throw new Exception("No se encontró el suministro con ID " + id);
        } catch (Exception e) {
            throw new Exception("Error al intentar anular el suministro: " + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
        return res;
    }

    @Override
    public MpEntregaFactura consultarFactura(Integer id) throws Exception {
        MpEntregaFactura factura = null;
        try {

            String strQuery = "SELECT e FROM MpEntregaFacturas e WHERE e.mpDireccionamientoEntregadosId.id = :id ";
            strQuery += " AND e.estado IN (1,2) ";

            TypedQuery<MpEntregaFacturas> query = getEntityManager().createQuery(strQuery, MpEntregaFacturas.class);
            query.setParameter("id", id);
            MpEntregaFacturas obj = query.getSingleResult();
            factura = castEntidadNegocioFactura(obj);

        } catch (Exception e) {
            factura = null;
        } finally {
            cerrarEntityManager();
        }
        return factura;
    }

    public MpEntregaFactura castEntidadNegocioFactura(MpEntregaFacturas per) {
        MpEntregaFactura obj = new MpEntregaFactura();

        if (per.getMpDireccionamientoEntregadosId() != null) {
            MpDireccionamientoEntregado entrega = new MpDireccionamientoEntregado();
            entrega.setId(per.getMpDireccionamientoEntregadosId().getId());
            entrega.setEstRepEntrega(per.getMpDireccionamientoEntregadosId().getEstRepEntrega());
            obj.setMpDireccionamientoEntregadoId(entrega);
        }
        obj.setId(per.getId());
        obj.setCodigoFacturado(per.getCodigoFacturado());
        obj.setEstado(per.getEstado());
        obj.setNoFactura(per.getNoFactura());
        obj.setCufe(per.getCufe());
        obj.setFechaFacturacion(per.getFechaFacturacion());
        obj.setMpEntregaFacturascol(per.getMpEntregaFacturascol());
        obj.setFechaConsumo(per.getFechaConsumo());
        obj.setIdFacturacion(per.getIdFacturacion());
        obj.setIdTransaccion(per.getIdTransaccion());
        obj.setIdCicloFacturacion(per.getIdCicloFacturacion());
        obj.setNoidEPS(per.getNoidEPS());
        obj.setCodEPS(per.getCodEPS());
        obj.setCantUnitaria(per.getCantUnitaria());
        obj.setValorUnitario(per.getValorUnitario());
        obj.setCopago(per.getCopago());
        obj.setCuotaModeradora(per.getCuotaModeradora());
        obj.setValorTotal(per.getValorTotal());
        obj.setNoSubEntrega(per.getNoSubEntrega());
        obj.setFechaAnulacion(per.getFechaAnulacion());

        obj.setConfirmaTipoComparador(per.getConfirmaTipoComparador());
        obj.setConfirmaCierreCiclo(per.getConfirmaCierreCiclo());
        obj.setConfirmaFhCierreCiclo(per.getConfirmaFhCierreCiclo());
        obj.setConfirmaFecha(per.getConfirmaFecha());
        obj.setConfirmaAfectaPresupuesto(per.getConfirmaAfectaPresupuesto());
        return obj;
    }

    @Override
    public void cierreCiloFactura(Integer id, String usuario, String ip) throws Exception {

        try {

            String hql = "UPDATE MpEntregaFacturas SET estadoMipres = :valor, confirmaCierreCiclo = :confirmaCierre, confirmaFhCierreCiclo = :confirmaFh,"
                    + "confirmarUsuarioCrea = :usuario , confirmarTerminalCrea = :ip , confirmarFechaHoraCrea = :horaConfirma, "
                    + "confirmaTipoComparador = :confirmaComparador, estado = :estado WHERE id = :id";

            Query updateQuery = getEntityManager().createQuery(hql);
            updateQuery.setParameter("valor", 1);
            updateQuery.setParameter("confirmaCierre", true);

            updateQuery.setParameter("usuario", usuario);
            updateQuery.setParameter("ip", ip);
            updateQuery.setParameter("horaConfirma", new Date());

            Short confirmaComparador = 3;
            updateQuery.setParameter("confirmaComparador", confirmaComparador);
            updateQuery.setParameter("confirmaFh", new Date());
            updateQuery.setParameter("id", id);
            Short estado = 2;
            updateQuery.setParameter("estado", estado);

            updateQuery.executeUpdate();
            getEntityManager().flush();

        } catch (NoResultException e) {
            throw new Exception("No se encontró el suministro con ID " + id);
        } catch (Exception e) {
            throw new Exception("Error al intentar anular el suministro: " + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void afectaPresupuestoMax(boolean afecta, Integer id) throws Exception {

        try {

            String hql = "UPDATE MpEntregaFacturas SET confirmaAfectaPresupuesto = :afecta WHERE id = :id";

            Query updateQuery = getEntityManager().createQuery(hql);
            updateQuery.setParameter("afecta", afecta);
            updateQuery.setParameter("id", id);
            updateQuery.executeUpdate();
            getEntityManager().flush();

        } catch (NoResultException e) {
            throw new Exception("No se encontró el suministro con ID " + id);
        } catch (Exception e) {
            throw new Exception("Error al intentar anular el suministro: " + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public ReporteDireccionamiento consultarDireccionamientoReporte(Integer id) throws Exception {
        ReporteDireccionamiento entrega = null;
        try {

            String strQuery = "SELECT p FROM MpDireccionamientos p WHERE p.id = :id";
            TypedQuery<MpDireccionamientos> query = getEntityManager().createQuery(strQuery, MpDireccionamientos.class);
            query.setParameter("id", id);
            MpDireccionamientos obj = query.getSingleResult();
            entrega = castEntidadNegocioReporte(obj);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return entrega;
    }

    public static ReporteDireccionamiento castEntidadNegocioReporte(MpDireccionamientos ent) {
        ReporteDireccionamiento obj = new ReporteDireccionamiento();

        obj.setStrIdCiclo(ent.getIdTransaccion());
        LocalDate fechaActual = LocalDate.now();
        String fechaFormateada = fechaActual.format(DateTimeFormatter.ISO_DATE);
        LocalTime horaActual = LocalTime.now();
        String horaFormateada = horaActual.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        obj.setStrFecha(fechaFormateada);
        obj.setStrHora(horaFormateada);

        if (ent.getMpPrescripcionesId() != null) {
            obj.setStrNumeroPrescripcion(ent.getMpPrescripcionesId().getNumeroPrescripcion());
            if (ent.getMpPrescripcionesId().getCodAmbAte() != null) {
                switch (ent.getMpPrescripcionesId().getCodAmbAte()) {
                    case MpPrescripcionDetalle.ID_AMBITO_AMBULATORIO_PRIORIZADO:
                        obj.setStrAmbito("Ambulatorio - Priorizado");
                        break;
                    case MpPrescripcionDetalle.ID_AMBITO_AMBULATORIO_NO_PRIORIZADO:
                        obj.setStrAmbito("Ambulatorio - No Priorizado");
                        break;
                    case MpPrescripcionDetalle.ID_AMBITO_HOSPITALARIO_DOMICILIARIO:
                        obj.setStrAmbito("Hospitalario - Domiciliario");
                        break;
                    case MpPrescripcionDetalle.ID_AMBITO_HOSPITALARIO_INTERNACION:
                        obj.setStrAmbito("Hospitalario - Internacion");
                        break;
                    case MpPrescripcionDetalle.ID_AMBITO_URGENCIAS:
                        obj.setStrAmbito("Urgencias");
                        break;
                    default:
                        obj.setStrAmbito("Recobrante");
                        break;
                }
            } else {
                obj.setStrAmbito("Recobrante");
            }
        }

        if (ent.getMpPrescripcionesId().getAsegAfiliadosId() != null) {//afiliado
//            if (ent.getMpPrescripcionesId().getAsegAfiliadosId().getMaeRegimenCodigo().trim() == "01") {
            obj.setStrRegimen(ent.getMpPrescripcionesId().getAsegAfiliadosId().getMaeRegimenValor());
//            } else if (ent.getMpPrescripcionesId().getAsegAfiliadosId().getMaeRegimenCodigo().trim() == "02") {
//                obj.setStrRegimen("Contributivo ");
//            }

            obj.setStrNombrePaciente(ent.getMpPrescripcionesId().getAsegAfiliadosId().getPrimerNombre() + " "
                    + ent.getMpPrescripcionesId().getAsegAfiliadosId().getSegundoNombre() + " "
                    + ent.getMpPrescripcionesId().getAsegAfiliadosId().getPrimerApellido() + " - "
                    + ent.getMpPrescripcionesId().getAsegAfiliadosId().getMaeTipoDocumentoCodigo() + " - "
                    + ent.getMpPrescripcionesId().getAsegAfiliadosId().getNumeroDocumento());

            obj.setStrMunicipio(ent.getMpPrescripcionesId().getAsegAfiliadosId().getAfiliacionUbicacionesId().getId().toString());
        }//fin afiliado

        if (ent.getMpPrescripcionesId().getCntProfesionalesId() != null) {//profecional
            obj.setStrNombreProfesional(ent.getMpPrescripcionesId().getCntProfesionalesId().getPrimerNombre() + " "
                    + ent.getMpPrescripcionesId().getCntProfesionalesId().getSegundoNombre() + " "
                    + ent.getMpPrescripcionesId().getCntProfesionalesId().getPrimerApellido() + " ");
        }//fin profecional
        switch (ent.getTipoTecnologia()) {
            case 1:
                obj.setStrTipoTecnologia("Medicamentos");
                break;
            case 2:
                obj.setStrTipoTecnologia("Procedimiento");
                break;
            case 3:
                obj.setStrTipoTecnologia("Dispositivo");
                break;
            case 4:
                obj.setStrTipoTecnologia("Producto Nutricional");
                break;
            case 5:
                obj.setStrTipoTecnologia("Servicio Complementario");
                break;
            default:
                obj.setStrTipoTecnologia("Sin Tecnologia");
                break;
        }

        obj.setStrFechaDireccionamiento(ent.getFechaDireccionamiento() != null ? ent.getFechaDireccionamiento().toString() : "");
        obj.setStrIdDireccionamiento(ent.getIdDireccionamiento() != null ? ent.getIdDireccionamiento().toString() : "");

        obj.setStrEstado("Direccionado");
        obj.setStrFechaMaximaEntrega(ent.getFechaMaxEntrega() != null ? ent.getFechaMaxEntrega().toString() : "");
        obj.setStrCantidad(ent.getEntregaTotal() != null ? ent.getEntregaTotal().toString() : "");

        obj.setStrPrestador(ent.getNombreSede());
        obj.setStrCodigoEntrega(ent.getCodigoMpEntrega() + " - " + ent.getCodigoMpPropio());
        obj.setStrNombreDirecciona(ent.getUsuarioCrea());
        obj.setStrNota(ent.getJustificacionDireccionamiento());

        return obj;
    }

    @Override
    public String consultarUbicacionReporte(int id) {
        String entrega = null;
        try {

            String strQuery = "SELECT p FROM GnUbicaciones p WHERE p.id = :id";
            TypedQuery<GnUbicaciones> query = getEntityManager().createQuery(strQuery, GnUbicaciones.class);
            query.setParameter("id", id);
            GnUbicaciones obj = query.getSingleResult();
            entrega = castEntidadNegocioReporteUbi(obj);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return entrega;
    }

    private static String castEntidadNegocioReporteUbi(GnUbicaciones ent) {
        String obj = "";
        String padre = "";
        String hijo = "";

        if (ent.getGnUbicacionesId() != null) {
            padre = ent.getGnUbicacionesId().getNombre() + " - ";
        }
        hijo = ent.getNombre();
        obj = padre + hijo;
        return obj;
    }

    @Override
    public boolean tieneAuditorias(int id) throws Exception {
        boolean tieneAuditorias = false;
        try {
            String strQuery = "SELECT COUNT(t) FROM MpPrescripcionItemAuditoria t WHERE t.mpPrescripcionId.id = :id";
            Long count = (Long) getEntityManager().createQuery(strQuery)
                    .setParameter("id", id)
                    .getSingleResult();

            tieneAuditorias = count > 1;

        } catch (Exception e) {
            e.printStackTrace();
            tieneAuditorias = false;
        } finally {
            cerrarEntityManager();
        }
        return tieneAuditorias;
    }

    @Override
    public boolean tieneDireccionamiento(Integer prescripcion, Integer medicamento, Integer tecnologia, Integer insumo) throws Exception {
        boolean tieneDireccionamiento = false;
        Long count = null;
        String strQuery = "";
        try {
            if (medicamento != null) {
                strQuery = "SELECT COUNT(t) FROM MpDireccionamientos t WHERE t.mpPrescripcionesId.id = :id";
                strQuery += " AND t.mpPrescripcionMedicamentosId.id = :medicamento";
                count = (Long) getEntityManager().createQuery(strQuery)
                        .setParameter("id", prescripcion)
                        .setParameter("medicamento", medicamento)
                        .getSingleResult();
            } else if (tecnologia != null) {
                strQuery = "SELECT COUNT(t) FROM MpDireccionamientos t WHERE t.mpPrescripcionesId.id = :id";
                strQuery += " AND t.mpPrescripcionTecnologiasId.id = :tecnologia";
                count = (Long) getEntityManager().createQuery(strQuery)
                        .setParameter("id", prescripcion)
                        .setParameter("tecnologia", tecnologia)
                        .getSingleResult();
            } else if (insumo != null) {
                strQuery = "SELECT COUNT(t) FROM MpDireccionamientos t WHERE t.mpPrescripcionesId.id = :id";
                strQuery += " AND t.mpPrescripcionInsumosId.id = :insumo";
                count = (Long) getEntityManager().createQuery(strQuery)
                        .setParameter("id", prescripcion)
                        .setParameter("insumo", insumo)
                        .getSingleResult();
            }
            tieneDireccionamiento = count >= 1;

        } catch (Exception e) {
            e.printStackTrace();
            tieneDireccionamiento = false;
        } finally {
            cerrarEntityManager();
        }
        return tieneDireccionamiento;
    }

    @Override
    public boolean tieneNoDireccionamiento(Integer prescripcion, Integer medicamento, Integer tecnologia, Integer insumo) throws Exception {
        boolean tieneNoDireccionamiento = false;
        Long count = null;
        String strQuery = "";
        try {
            if (medicamento != null) {
                strQuery = "SELECT COUNT(t) FROM MpNoDireccionados t WHERE t.mpPrescripcionesId.id = :id";
                strQuery += " AND t.mpPrescripcionMedicamentosId.id = :medicamento";
                count = (Long) getEntityManager().createQuery(strQuery)
                        .setParameter("id", prescripcion)
                        .setParameter("medicamento", medicamento)
                        .getSingleResult();
            } else if (tecnologia != null) {
                strQuery = "SELECT COUNT(t) FROM MpNoDireccionados t WHERE t.mpPrescripcionesId.id = :id";
                strQuery += " AND t.mpPrescripcionTecnologiasId.id = :tecnologia";
                count = (Long) getEntityManager().createQuery(strQuery)
                        .setParameter("id", prescripcion)
                        .setParameter("tecnologia", tecnologia)
                        .getSingleResult();
            } else if (insumo != null) {
                strQuery = "SELECT COUNT(t) FROM MpNoDireccionados t WHERE t.mpPrescripcionesId.id = :id";
                strQuery += " AND t.mpPrescripcionInsumosId.id = :insumo";
                count = (Long) getEntityManager().createQuery(strQuery)
                        .setParameter("id", prescripcion)
                        .setParameter("insumo", insumo)
                        .getSingleResult();
            }
            tieneNoDireccionamiento = count >= 1;

        } catch (Exception e) {
            e.printStackTrace();
            tieneNoDireccionamiento = false;
        } finally {
            cerrarEntityManager();
        }
        return tieneNoDireccionamiento;
    }

    @Override
    public List<MpDireccionamiento> consultarDireccionamientosPorAfiliado(
            int idAfiliado, ParamConsulta paramConsulta) throws Exception {
        List<MpDireccionamiento> listaDireccionamientos = new ArrayList<>();

        try {
            StringBuilder strQuery = new StringBuilder("FROM MpDireccionamientos p WHERE p.mpPrescripcionesId.asegAfiliadosId = ").append(idAfiliado).append(" AND p.estado IN (1,2)");

            if (paramConsulta.getFiltros() != null && !paramConsulta.getFiltros().isEmpty()) {
                for (Map.Entry<String, Object> e : paramConsulta.getFiltros().entrySet()) {
                    String key = e.getKey();
                    Object value = e.getValue();

                    if (null != key) {
                        switch (key) {
                            case "mpPrescripcionId.numeroPrescripcion":
                                strQuery.append(" AND p.mpPrescripcionesId.numeroPrescripcion LIKE '%").append(value).append("%'");
                                break;
                            case "mpPrescripcionId.fechaPrescripcion":
                                strQuery.append(" AND p.mpPrescripcionesId.fechaPrescripcion = '").append(value).append("'");
                                break;
                            case "mpPrescripcionId.estado":
                                strQuery.append(" AND p.mpPrescripcionesId.estado = ").append(value);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }

            strQuery.append(" ORDER BY ")
                    .append(paramConsulta.getOrden() != null && !paramConsulta.getOrden().isEmpty()
                            ? "p.fechaDireccionamiento " + (paramConsulta.isAscendente() ? "ASC" : "DESC")
                            : "p.mpPrescripcionesId.fechaPrescripcion DESC");

            List<MpDireccionamientos> resultados = getEntityManager()
                    .createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();

            for (MpDireccionamientos direccionamiento : resultados) {
                MpDireccionamiento obj = new MpDireccionamiento();
                obj.setId(direccionamiento.getId());

                MpPrescripcion pre = new MpPrescripcion();
                pre.setId(direccionamiento.getMpPrescripcionesId().getId());
                pre.setNumeroPrescripcion(direccionamiento.getMpPrescripcionesId().getNumeroPrescripcion());
                pre.setFechaPrescripcion(direccionamiento.getMpPrescripcionesId().getFechaPrescripcion());
                pre.setEstado(direccionamiento.getMpPrescripcionesId().getEstado());
                obj.setMpPrescripcionId(pre);

                obj.setConsecutivoEntrega(direccionamiento.getConsecutivoEntrega());
                obj.setTipoTecnologia(direccionamiento.getTipoTecnologia());
                obj.setFechaDireccionamiento(direccionamiento.getFechaDireccionamiento());
                obj.setFechaMaxEntrega(direccionamiento.getFechaMaxEntrega());
                obj.setPrestadorRazonSocial(direccionamiento.getPrestadorRazonSocial());
                obj.setEstado(direccionamiento.getEstado());
                asignarEstadoTecnologia(obj.getEstado(), obj);

                switch (obj.getTipoTecnologia()) {
                    case 1:
                    case 4:
                        MpPrescripcionMedicamento prescripcionMedicamento = new MpPrescripcionMedicamento();
                        prescripcionMedicamento.setId(direccionamiento.getMpPrescripcionMedicamentosId().getId());

                        if (direccionamiento.getMpPrescripcionMedicamentosId().getDescripcionMedicamentoPrincipioActivo() != null) {
                            prescripcionMedicamento.setDescripcionMedicamentoPrincipioActivo(
                                    direccionamiento.getMpPrescripcionMedicamentosId().getDescripcionMedicamentoPrincipioActivo());
                        } else {
                            prescripcionMedicamento.setMaeProductosNutricionalesValor(
                                    direccionamiento.getMpPrescripcionMedicamentosId().getMaeProductosNutricionalesValor());
                        }

                        obj.setValorTecnologia(
                                direccionamiento.getMpPrescripcionMedicamentosId().getDescripcionMedicamentoPrincipioActivo() != null
                                ? prescripcionMedicamento.getDescripcionMedicamentoPrincipioActivo()
                                : prescripcionMedicamento.getMaeProductosNutricionalesValor());

                        obj.setMpPrescripcionMedicamentoId(prescripcionMedicamento);
                        break;
                    case 2:
                        MpPrescripcionTecnologia prescripcionTecnologia = new MpPrescripcionTecnologia();
                        prescripcionTecnologia.setId(direccionamiento.getMpPrescripcionTecnologiasId().getId());
                        prescripcionTecnologia.setMaTecnologiaValor(direccionamiento.getMpPrescripcionTecnologiasId().getMaTecnologiaValor());
                        obj.setValorTecnologia(prescripcionTecnologia.getMaTecnologiaValor());
                        obj.setMpPrescripcionTecnologiaId(prescripcionTecnologia);
                        break;
                    case 3:
                    case 5:
                        MpPrescripcionInsumo prescripcionInsumo = new MpPrescripcionInsumo();
                        prescripcionInsumo.setId(direccionamiento.getMpPrescripcionInsumosId().getId());

                        if (direccionamiento.getMpPrescripcionInsumosId().getDescripcionServicioComplementario() != null) {
                            prescripcionInsumo.setDescripcionServicioComplementario(direccionamiento.getMpPrescripcionInsumosId().getDescripcionServicioComplementario());
                        } else {
                            prescripcionInsumo.setMaeDispositivosNombre(
                                    direccionamiento.getMpPrescripcionInsumosId().getMaedispositivosValor());
                        }

                        obj.setValorTecnologia(
                                direccionamiento.getMpPrescripcionInsumosId().getDescripcionServicioComplementario() != null
                                ? prescripcionInsumo.getDescripcionServicioComplementario()
                                : prescripcionInsumo.getMaeDispositivosNombre());

                        obj.setMpPrescripcionInsumoId(prescripcionInsumo);
                        break;
                    default:
                        break;
                }
                listaDireccionamientos.add(obj);
            }
        } catch (NoResultException e) {
            return Collections.emptyList();
        } catch (Exception e) {
            throw new Exception("Error en consultarDireccionamientosPorPrescripciones - " + e.getMessage(), e);
        } finally {
            cerrarEntityManager();
        }
        return listaDireccionamientos;
    }

    private void asignarEstadoTecnologia(int estado, MpDireccionamiento obj) {
        switch (estado) {
            case 1:
                obj.setEstadoTecnologia("Direccionado");
                break;
            case 2:
                obj.setEstadoTecnologia("Programado");
                break;
            default:
                break;
        }
    }

    @Override
    public int contarConsultarDireccionamientosPorAfiliado(int idAfiliado, ParamConsulta paramConsulta) throws java.lang.Exception {
        Long contador = Long.valueOf(0);
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(p) FROM MpDireccionamientos p WHERE p.mpPrescripcionesId.asegAfiliadosId = ").append(idAfiliado);

            if (paramConsulta.getFiltros() != null && !paramConsulta.getFiltros().isEmpty()) {
                for (Map.Entry<String, Object> e : paramConsulta.getFiltros().entrySet()) {
                    String key = e.getKey();
                    Object value = e.getValue();

                    if (null != key) {
                        switch (key) {
                            case "mpPrescripcionId.numeroPrescripcion":
                                strQuery.append(" AND p.mpPrescripcionesId.numeroPrescripcion LIKE '%").append(value).append("%'");
                                break;
                            case "mpPrescripcionId.fechaPrescripcion":
                                strQuery.append(" AND p.mpPrescripcionesId.fechaPrescripcion = '").append(value).append("'");
                                break;
                            case "mpPrescripcionId.estado":
                                strQuery.append(" AND p.mpPrescripcionesId.estado = ").append(value);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }

            contador = (Long) getEntityManager()
                    .createQuery(strQuery.toString())
                    .getSingleResult();
        } catch (NoResultException e) {
            contador = Long.valueOf(0);
        } catch (Exception e) {
            throw new Exception("Error contando consultarListaMpDireccionamientos  - " + e.getMessage());
        } finally {
            cerrarEntityManager();
        }

        return contador.intValue();
    }

    @Override
    public int consultarCantidadListaCotizacion(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MpCotizaciones p "
                    + " WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "numeroPrescripcion":
                            strQuery += " AND p.numeroPrescripcion LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "nombreTecnologia":
                            strQuery += " AND p.nombreTecnologia LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "tipoTecnologia":
                            strQuery += " AND p.tipoTecnologia = " + (String) e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += " AND p.estado = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            Query query = getEntityManager().createQuery(strQuery);
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
    public List<MpCotizacionDetalle> consultarListaCotizaciones(ParamConsulta paramConsulta) throws Exception {
        List<MpCotizacionDetalle> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM MpCotizaciones p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "numeroPrescripcion":
                            strQuery += " AND p.numeroPrescripcion LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "nombreTecnologia":
                            strQuery += " AND p.nombreTecnologia LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "tipoTecnologia":
                            strQuery += " AND p.tipoTecnologia = " + (String) e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += " AND p.estado = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY p.id DESC ";

            List<MpCotizaciones> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (MpCotizaciones per : list) {
                listResult.add(castEntidadNegocioCotizacion(per));
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
    public MpCotizacionDetalle consultarParaCotizacion(Integer id) throws Exception {
        MpCotizacionDetalle result = null;
        try {
            String strQuery = "FROM MpCotizaciones p WHERE p.id = :id ";

            MpCotizaciones cotizacion = getEntityManager()
                    .createQuery(strQuery, MpCotizaciones.class)
                    .setParameter("id", id)
                    .setMaxResults(1) // Solo un resultado
                    .getSingleResult();

            result = castEntidadNegocioCotizacion(cotizacion);

        } catch (NoResultException e) {
            result = null; // Si no hay resultado, devuelve null
        } catch (Exception e) {
            throw new Exception("Error al consultar la cotización", e);
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    public static MpCotizacionDetalle castEntidadNegocioCotizacion(MpCotizaciones ent) {
        Integer idPres = ent.getMpPrescripcionesId().getId();
        Integer idMed = (ent.getMpPrescripcionMedicamentosId() != null) ? ent.getMpPrescripcionMedicamentosId().getId() : null;
        Integer idTec = (ent.getMpPrescripcionTecnologiasId() != null) ? ent.getMpPrescripcionTecnologiasId().getId() : null;
        Integer idIns = (ent.getMpPrescripcionInsumosId() != null) ? ent.getMpPrescripcionInsumosId().getId() : null;
        Integer idCot = (ent.getAuCotizacionesId() != null) ? ent.getAuCotizacionesId().getId() : null;

        MpCotizacionDetalle obj = new MpCotizacionDetalle();
        obj.setId(ent.getId());
        obj.setNumeroPrescripcion(ent.getNumeroPrescripcion());
        if (idPres != null) {
            obj.setIdPrescripcion(idPres);
        }
        if (idMed != null) {
            obj.setIdItem(idMed);
        } else if (idTec != null) {
            obj.setIdItem(idTec);
        } else if (idIns != null) {
            obj.setIdItem(idIns);
        }
        obj.setIdCotizacion(idCot);
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        switch (ent.getTipoTecnologia()) {
            case 1:
                obj.setNombreTipoTecnologia("Medicamentos");
                break;
            case 2:
                obj.setNombreTipoTecnologia("Procedimientos");
                break;
            case 3:
                obj.setNombreTipoTecnologia("Dispositivos");
                break;
            case 4:
                obj.setNombreTipoTecnologia("Productos nutricionales");
                break;
            case 5:
                obj.setNombreTipoTecnologia("Servicios Complementarios");
                break;
            default:
                obj.setNombreTipoTecnologia("Sin Tecnologí­a Clara");
                break;
        }

        obj.setNombreTecnologia(ent.getNombreTecnologia());
        obj.setEstado(ent.getEstado());
        switch (ent.getEstado()) {
            case 11:
                obj.setNombreTipoEstado("Pendiente De Cotizaciòn");
                break;
            case 12:
                obj.setNombreTipoEstado("Con Cotizacion");
                break;
            case 13:
                obj.setNombreTipoEstado("Rechazo De Cotizaciòn");
                break;
            case 14:
                obj.setNombreTipoEstado("Direccionado Con Cotizacion");
                break;
            case 15:
                obj.setNombreTipoEstado("Direccionado Sin Cotizacion");
                break;
            case 17:
                obj.setNombreTipoEstado("Direccionado Sin Asignar Cotización Activa");
                break;
            case 18:
                obj.setNombreTipoEstado("NoDireccionado Sin Cotizacion");
                break;
            case 19:
                obj.setNombreTipoEstado("NoDireccionado Con Cotizacion");
                break;
            default:
                obj.setNombreTipoEstado("Sin Estado");
                break;
        }
        return obj;
    }

    @Override
    public Integer consultarCotizacion(Integer prescripcion, Integer item, Integer tipo) throws Exception {
        Integer estado = null;
        try {
            String strQuery = "SELECT p.estado FROM MpCotizaciones p WHERE p.mpPrescripcionesId.id = :prescripcion";

            switch (tipo) {
                case 1:
                case 4:
                    strQuery += " AND p.mpPrescripcionMedicamentosId.id = :item";
                    break;
                case 2:
                    strQuery += " AND p.mpPrescripcionTecnologiasId.id = :item";
                    break;
                case 3:
                case 5:
                    strQuery += " AND p.mpPrescripcionInsumosId.id = :item";
                    break;
                default:
                    return null;
            }
            strQuery += " ORDER BY p.id DESC";
            TypedQuery<Integer> query = getEntityManager().createQuery(strQuery, Integer.class);
            query.setParameter("prescripcion", prescripcion);
            query.setParameter("item", item);
            query.setMaxResults(1);
            List<Integer> resultList = query.getResultList();
            if (!resultList.isEmpty()) {
                estado = resultList.get(0);
            } else {
                estado = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return estado;
    }

    @Override
    public Integer consultarCotizacion2(Integer prescripcion, Integer item, Integer tipo) throws Exception {
        Integer estado = null;
        try {
            String strQuery = "SELECT p.id FROM MpCotizaciones p WHERE p.mpPrescripcionesId.id = :prescripcion";

            switch (tipo) {
                case 1:
                case 4:
                    strQuery += " AND p.mpPrescripcionMedicamentosId.id = :item";
                    break;
                case 2:
                    strQuery += " AND p.mpPrescripcionTecnologiasId.id = :item";
                    break;
                case 3:
                case 5:
                    strQuery += " AND p.mpPrescripcionInsumosId.id = :item";
                    break;
                default:
                    return null;
            }
            strQuery += " ORDER BY p.id DESC";
            TypedQuery<Integer> query = getEntityManager().createQuery(strQuery, Integer.class);
            query.setParameter("prescripcion", prescripcion);
            query.setParameter("item", item);
            query.setMaxResults(1);
            List<Integer> resultList = query.getResultList();
            if (!resultList.isEmpty()) {
                estado = resultList.get(0);
            } else {
                estado = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return estado;
    }

    @Override
    public void cambiarEstadoCotizacionMp(Integer id, Integer estado) throws Exception {

        try {

            String hql = "UPDATE MpCotizaciones SET estado = :estado WHERE id = :id";

            Query updateQuery = getEntityManager().createQuery(hql);
            updateQuery.setParameter("estado", estado);
            updateQuery.setParameter("id", id);

            updateQuery.executeUpdate();
            getEntityManager().flush();

        } catch (NoResultException e) {
            throw new Exception("No se encontró la cotizaciòn con ID " + id);
        } catch (Exception e) {
            throw new Exception("Error al intentar cambiar de estado la cotizaciòn: " + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void cambiarEstadoCotizacionMpAu(Integer id, Integer estado) throws Exception {

        try {

            String hql = "UPDATE MpCotizaciones SET estado = :estado WHERE auCotizacionesId.id = :id";

            Query updateQuery = getEntityManager().createQuery(hql);
            updateQuery.setParameter("estado", estado);
            updateQuery.setParameter("id", id);

            updateQuery.executeUpdate();
            getEntityManager().flush();

        } catch (NoResultException e) {
            throw new Exception("No se encontró la cotizaciòn con ID " + id);
        } catch (Exception e) {
            throw new Exception("Error al intentar cambiar de estado la cotizaciòn: " + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void asignarCotizacionASolicitud(Integer id, Integer cotizacion) throws Exception {

        try {

            String hql = "UPDATE MpCotizaciones SET auCotizacionesId.id = :cotizacion WHERE id = :id";

            Query updateQuery = getEntityManager().createQuery(hql);
            updateQuery.setParameter("cotizacion", cotizacion);
            updateQuery.setParameter("id", id);

            updateQuery.executeUpdate();
            getEntityManager().flush();

        } catch (NoResultException e) {
            throw new Exception("No se encontró la cotizaciòn con ID " + id);
        } catch (Exception e) {
            throw new Exception("Error al intentar cambiar de estado la cotizaciòn: " + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void asignarUsuarioGestion(Integer id, String usuario, String ip) throws Exception {

        try {
            String hql = "UPDATE MpCotizaciones SET usuarioModifica = :usuario,"
                    + " terminalModifica = :ip, fechaHoraModifica = :fecha WHERE id = :id";

            Query updateQuery = getEntityManager().createQuery(hql);
            updateQuery.setParameter("usuario", usuario);
            updateQuery.setParameter("ip", ip);
            updateQuery.setParameter("fecha", new Date());
            updateQuery.setParameter("id", id);

            updateQuery.executeUpdate();
            getEntityManager().flush();

        } catch (NoResultException e) {
            throw new Exception("No se encontró la cotizaciòn con ID " + id);
        } catch (Exception e) {
            throw new Exception("Error al intentar cambiar de estado la cotizaciòn: " + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void asignarUsuarioRechaza(Integer id, String usuario, String ip) throws Exception {

        try {
            String hql = "UPDATE MpCotizaciones SET usuarioRechaza = :usuario,"
                    + " terminalRechaza = :ip, fechaHoraRechaza = :fecha WHERE id = :id";

            Query updateQuery = getEntityManager().createQuery(hql);
            updateQuery.setParameter("usuario", usuario);
            updateQuery.setParameter("ip", ip);
            updateQuery.setParameter("fecha", new Date());
            updateQuery.setParameter("id", id);

            updateQuery.executeUpdate();
            getEntityManager().flush();

        } catch (NoResultException e) {
            throw new Exception("No se encontró la cotizaciòn con ID " + id);
        } catch (Exception e) {
            throw new Exception("Error al intentar cambiar de estado la cotizaciòn: " + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void cambiarEstadoItem(Integer id, Integer tipo, int estado) throws Exception {

        try {

            String hql = "";

            switch (tipo) {
                case 1:
                case 4:
                    hql = " UPDATE MpPrescripcionMedicamentos SET estado = :estado WHERE id = :id";
                    break;
                case 2:
                    hql = " UPDATE MpPrescripcionTecnologias SET estado = :estado WHERE id = :id";
                    break;
                case 3:
                case 5:
                    hql = " UPDATE MpPrescripcionInsumos SET estado = :estado WHERE id = :id";
                    break;
                default:
                    break;
            }

            Query updateQuery = getEntityManager().createQuery(hql);
            updateQuery.setParameter("estado", estado);
            updateQuery.setParameter("id", id);

            updateQuery.executeUpdate();
            getEntityManager().flush();

        } catch (NoResultException e) {
            throw new Exception("No se encontró la cotizaciòn con ID " + id);
        } catch (Exception e) {
            throw new Exception("Error al intentar cambiar de estado la cotizaciòn: " + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AuCotizacion consultarAuCotizacion(Integer id) throws Exception {
        AuCotizacion result = null;
        try {
            String strQuery = "FROM AuCotizaciones p WHERE p.id = :id ";

            AuCotizaciones cotizacion = getEntityManager()
                    .createQuery(strQuery, AuCotizaciones.class)
                    .setParameter("id", id)
                    .setMaxResults(1) // Solo un resultado
                    .getSingleResult();

            result = castEntidadNegocioAuCotizacion(cotizacion);

        } catch (NoResultException e) {
            result = null; // Si no hay resultado, devuelve null
        } catch (Exception e) {
            throw new Exception("Error al consultar la cotización", e);
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    @Override
    public AuCotizacion consultarAuCotizacionVigencia(Integer id) throws Exception {
        AuCotizacion result = null;

        try {
            LocalDate fechaActual = LocalDate.now();
            Date fechaDate = java.sql.Date.valueOf(fechaActual);

            String strQuery = "FROM AuCotizaciones p "
                    + "WHERE p.id = :id "
                    + "AND p.fechaInicioVigencia <= :fecha "
                    + "AND p.fechaFinVigencia >= :fecha";

            List<AuCotizaciones> resultados = getEntityManager()
                    .createQuery(strQuery, AuCotizaciones.class)
                    .setParameter("id", id)
                    .setParameter("fecha", fechaDate)
                    .setMaxResults(1)
                    .getResultList();

            if (!resultados.isEmpty()) {
                result = castEntidadNegocioAuCotizacion(resultados.get(0));
            }

        } catch (Exception e) {
            throw new Exception("Error al consultar la cotización", e);
        } finally {
            cerrarEntityManager();
        }

        return result;
    }

    public static AuCotizacion castEntidadNegocioAuCotizacion(AuCotizaciones ent) {

        AuCotizacion obj = new AuCotizacion();
        obj.setId(ent.getId());
        if (ent.getCntPrestadorSedesId() != null) {
            CntPrestadorSede sede = new CntPrestadorSede();
            sede.setId(ent.getCntPrestadorSedesId().getId());
            sede.setCodigoPrestador(ent.getCntPrestadorSedesId().getCodigoPrestador());
            sede.setDireccion(ent.getCntPrestadorSedesId().getDireccion());
            sede.setNombreSede(ent.getCntPrestadorSedesId().getNombre());
            sede.setCodigoHabilitacionSede(ent.getCntPrestadorSedesId().getCodigoHabilitacion());
            if (ent.getCntPrestadorSedesId().getCntPrestadoresId() != null) {
                CntPrestador prestador = new CntPrestador();
                prestador.setId(ent.getCntPrestadorSedesId().getCntPrestadoresId().getId());
                prestador.setNumeroDocumento(ent.getCntPrestadorSedesId().getCntPrestadoresId().getNumeroDocumento());
                prestador.setNombreRepresentanteLegal(ent.getCntPrestadorSedesId().getCntPrestadoresId().getNombreRepresentanteLegal());
                prestador.setMaeTipoDocumentoId(ent.getCntPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoId());
                prestador.setMaeTipoDocumentoCodigo(ent.getCntPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoCodigo());
                prestador.setMaeTipoDocumentoValor(ent.getCntPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoValor());
                prestador.setRazonSocial(ent.getCntPrestadorSedesId().getCntPrestadoresId().getRazonSocial());
                sede.setCntPrestador(prestador);
            }
            obj.setCntPrestadorSede(sede);
        }
        obj.setActivo(ent.getActivo());
        obj.setMaTecnologiaId(ent.getMaTecnologiaId());
        obj.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        obj.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        obj.setTipoTarifa(ent.getTipoTarifa());
        obj.setPorcentajeNegociacion(ent.getPorcentajeNegociacion());
        obj.setValorTecnologia(ent.getValorTecnologia());
        obj.setFechaInicioVigencia(ent.getFechaInicioVigencia());
        obj.setFechaFinVigencia(ent.getFechaFinVigencia());
        obj.setObservacion(ent.getObservacion());
        obj.setTipoTecnologiaMipres(ent.getTipoTecnologiaMipres());
        obj.setUsuarioCrea(ent.getUsuarioCrea());

        return obj;
    }

    @Override
    public MpCotizacionDetalle consultarCotizacionParaDireccionar(Integer prescripcion, Integer item, Integer tipo) throws Exception {
        MpCotizacionDetalle result = null;
        try {
            String strQuery = "FROM MpCotizaciones p WHERE p.mpPrescripcionesId.id = :prescripcion  ";
            switch (tipo) {
                case 1:
                case 4:
                    strQuery += " AND p.mpPrescripcionMedicamentosId.id = :item ";
                    break;
                case 2:
                    strQuery += " AND p.mpPrescripcionTecnologiasId.id = :item ";
                    break;
                case 3:
                case 5:
                    strQuery += " AND p.mpPrescripcionInsumosId.id = :item ";
                    break;
                default:
                    break;
            }
            strQuery += " ORDER BY p.id DESC";

            MpCotizaciones cotizacion = getEntityManager()
                    .createQuery(strQuery, MpCotizaciones.class)
                    .setParameter("prescripcion", prescripcion)
                    .setParameter("item", item)
                    .setMaxResults(1)
                    .getSingleResult();

            result = castEntidadNegocioCotizacion(cotizacion);

        } catch (NoResultException e) {
            result = null; // Si no hay resultado, devuelve null
        } catch (Exception e) {
            throw new Exception("Error al consultar la cotización", e);
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    @Override
    public boolean anularNoDireccionamiento(Integer idPrescripcion, int tipoTecnologiaITem, int idItem, String usuarioAnula, String terminarAnula, int estado) throws Exception {
        boolean realizado = true;
        try {
            String hql = "UPDATE MpNoDireccionados SET "
                    + "estadoNoDireccionamiento = " + estado + ", "
                    + "usuarioAnula = '" + usuarioAnula + "', "
                    + "terminalAnula = '" + terminarAnula + "', "
                    + "fechaHoraAnula = '" + formatoLargo.format(new Date()) + "' "
                    + "WHERE tipoTecnologia = " + tipoTecnologiaITem + " AND mpPrescripcionesId.id = " + idPrescripcion + " ";
            String hqlItem = "";
            switch (tipoTecnologiaITem) {
                case 1:
                case 4:
                    hql += "AND mpPrescripcionMedicamentosId.id = " + idItem;
                    hqlItem = "UPDATE MpPrescripcionMedicamentos SET estado = " + MpPrescripcionMedicamento.ESTADO_ANULANDO_NO_DIRECCIONAMIENTO + " "
                            + "WHERE id = " + idItem;
                    break;
                case 2:
                    hql += "AND mpPrescripcionTecnologiasId.id = " + idItem;
                    hqlItem = "UPDATE MpPrescripcionTecnologias SET estado = " + MpPrescripcionTecnologia.ESTADO_ANULANDO_NO_DIRECCIONAMIENTO + " "
                            + "WHERE id = " + idItem;
                    break;
                case 3:
                case 5:
                    hql += "AND mpPrescripcionInsumosId.id = " + idItem;
                    hqlItem = "UPDATE MpPrescripcionInsumos SET estado = " + MpPrescripcionInsumo.ESTADO_ANULANDO_NO_DIRECCIONAMIENTO + " "
                            + "WHERE id = " + idItem;
                    break;
                default:
                    realizado = false;
                    break;
            }

            if (realizado) {
                Query query = getEntityManager().createQuery(hql);
                query.executeUpdate();
                getEntityManager().flush();
                Query query2 = getEntityManager().createQuery(hqlItem);
                query2.executeUpdate();
                getEntityManager().flush();
            }

        } catch (Exception e) {
            realizado = false;
        }
        return realizado;
    }

    @Override
    public String consultarCntUnionTemp(Integer id) {
        String valor = null;
        try {
            String strQuery = "SELECT p FROM CntPrestadorUnionTemporal p WHERE p.cntPrestadoresId.id = :id";
            TypedQuery<CntPrestadorUnionTemporal> query = getEntityManager().createQuery(strQuery, CntPrestadorUnionTemporal.class);
            query.setParameter("id", id);

            List<CntPrestadorUnionTemporal> resultados = query.getResultList();
            if (!resultados.isEmpty()) {
                valor = castEntidadNegocioTemp(resultados.get(0));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return valor;
    }

    private static String castEntidadNegocioTemp(CntPrestadorUnionTemporal ent) {
        String obj = "";
        if (ent.getCntPrestadorUnionTemporalId() != null) {
            obj = ent.getCntPrestadorUnionTemporalId().getRazonSocial();
        }
        return obj;
    }

}
