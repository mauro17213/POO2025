/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.mipres;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionRecobrante;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionRecobrantes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.mipres.MpPrescripcionRemoto;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripciones;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(MpPrescripcionRemoto.class)
//@Local(MpPrescripcionLocal.class)
public class MpPrescripcionServicio extends GenericoServicio implements /*MpPrescripcionLocal, */MpPrescripcionRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT COUNT(m) FROM MpPrescripciones m WHERE 1 = 1 ");

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry<String, Object> e : paramConsulta.getFiltros().entrySet()) {
                    switch (e.getKey()) {
                        case "numeroPrescripcion":
                            strQuery.append("AND m.numeroPrescripcion = '").append((String) e.getValue()).append("' ");
                            break;
                        case "consecutivoMipres":
                            strQuery.append("AND m.consecutivoMipres = '").append((String) e.getValue()).append("' ");
                            break;
                        case "tipoPrescripcion":
                            strQuery.append("AND m.tipoPrescripcion = '").append((String) e.getValue()).append("' ");
                            break;
                        case "asegAfiliado.maeTipoDocumento":
                            strQuery.append("AND m.asegAfiliadosId.maeTipoDocumentoId = '").append((String) e.getValue()).append("' ");
                            break;
                        case "asegAfiliado.numeroDocumento":
                            strQuery.append("AND m.asegAfiliadosId.numeroDocumento = '").append((String) e.getValue()).append("' ");
                            break;
                        case "asegAfiliado.nombres":
                            strQuery.append("AND (m.asegAfiliadosId.primerNombre LIKE '%").append((String) e.getValue()).append("%' ")
                                    .append("OR m.asegAfiliadosId.segundoNombre LIKE '%").append((String) e.getValue() + "%' ")
                                    .append("OR m.asegAfiliadosId.primerApellido LIKE '%").append(e.getValue() + "%' ")
                                    .append("OR m.asegAfiliadosId.segundoApellido LIKE '%").append(e.getValue() + "%') ")
                                    .append("OR CONCAT(m.asegAfiliadosId.primerNombre, ' ', COALESCE(m.asegAfiliadosId.segundoNombre,''), ' ', m.asegAfiliadosId.primerApellido, ' ', COALESCE(m.asegAfiliadosId.segundoApellido,'')) LIKE '%").append((String) e.getValue()).append("%' ");
                            break;
                        case "prestadorRazonSocial":
                            strQuery.append("AND m.prestadorRazonSocial LIKE '%").append((String) e.getValue()).append("%' ");
                            break;
                        case "prestadorNumeroDocumento":
                            strQuery.append("AND m.prestadorNumeroDocumento LIKE '%").append((String) e.getValue()).append("%' ");
                            break;
                        case "cntProfesional.nombreCompleto":
                           strQuery.append("AND (m.cntProfesionalesId.primerNombre LIKE '%").append((String) e.getValue()).append("%' ")
                                    .append("OR m.cntProfesionalesId.segundoNombre LIKE '%").append((String) e.getValue() + "%' ")
                                    .append("OR m.cntProfesionalesId.primerApellido LIKE '%").append(e.getValue() + "%' ")
                                    .append("OR m.cntProfesionalesId.segundoApellido LIKE '%").append(e.getValue() + "%') ");
                            break;
                        case "asegAfiliado.regimen":
                            strQuery.append("AND m.asegAfiliadosId.regimen = '").append((String) e.getValue()).append("' ");
                            break;
                        default:
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery.toString())
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
    public List<MpPrescripcion> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<MpPrescripcion> listaResultado = new ArrayList<>();

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT m FROM MpPrescripciones m WHERE 1 = 1 ");

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry<String, Object> e : paramConsulta.getFiltros().entrySet()) {
                    switch (e.getKey()) {
                        case "numeroPrescripcion":
                            strQuery.append("AND m.numeroPrescripcion = '").append((String) e.getValue()).append("' ");
                            break;
                        case "consecutivoMipres":
                            strQuery.append("AND m.consecutivoMipres = '").append((String) e.getValue()).append("' ");
                            break;
                        case "tipoPrescripcion":
                            strQuery.append("AND m.tipoPrescripcion = '").append((String) e.getValue()).append("' ");
                            break;
                        case "asegAfiliado.maeTipoDocumento":
                            strQuery.append("AND m.asegAfiliadosId.maeTipoDocumentoId = '").append((String) e.getValue()).append("' ");
                            break;
                        case "asegAfiliado.numeroDocumento":
                            strQuery.append("AND m.asegAfiliadosId.numeroDocumento = '").append((String) e.getValue()).append("' ");
                            break;
                        case "asegAfiliado.nombres":
                            strQuery.append("AND (m.asegAfiliadosId.primerNombre LIKE '%").append((String) e.getValue()).append("%' ")
                                    .append("OR m.asegAfiliadosId.segundoNombre LIKE '%").append((String) e.getValue() + "%' ")
                                    .append("OR m.asegAfiliadosId.primerApellido LIKE '%").append((String) e.getValue() + "%' ")
                                    .append("OR m.asegAfiliadosId.segundoApellido LIKE '%").append((String) e.getValue() + "%') ");
                                    //.append("OR CONCAT(m.asegAfiliadosId.primerNombre, ' ', COALESCE(m.asegAfiliadosId.segundoNombre,''), ' ', m.asegAfiliadosId.primerApellido, ' ', COALESCE(m.asegAfiliadosId.segundoApellido,'')) LIKE '%").append((String) e.getValue()).append("%')");
                            break;
                        case "prestadorRazonSocial":
                            strQuery.append("AND m.prestadorRazonSocial LIKE '%").append((String) e.getValue()).append("%' ");
                            break;
                        case "prestadorNumeroDocumento":
                            strQuery.append("AND m.prestadorNumeroDocumento LIKE '%").append((String) e.getValue()).append("%' ");
                            break;
                        case "cntProfesional.nombreCompleto":
                           strQuery.append("AND (m.cntProfesionalesId.primerNombre LIKE '%").append((String) e.getValue()).append("%' ")
                                    .append("OR m.cntProfesionalesId.segundoNombre LIKE '%").append((String) e.getValue() + "%' ")
                                    .append("OR m.cntProfesionalesId.primerApellido LIKE '%").append((String) e.getValue() + "%' ")
                                    .append("OR m.cntProfesionalesId.segundoApellido LIKE '%").append((String) e.getValue() + "%')");
                                    //.append("OR CONCAT(m.cntProfesionalesId.primerNombre, ' ', COALESCE(m.cntProfesionalesId.segundoNombre,''), ' ', m.cntProfesionalesId.primerApellido, ' ', COALESCE(m.cntProfesionalesId.segundoApellido,'')) LIKE '%").append((String) e.getValue()).append("%') ");
                            break;
                        case "asegAfiliado.regimen":
                            strQuery.append("AND m.asegAfiliadosId.regimen = '").append((String) e.getValue()).append("' ");
                            break;
                        case "recobrante":
                            strQuery.append("AND m.recobrante = '").append((String) e.getValue()).append("' ");
                            break;
                        default:
                            break;
                    }
                }
            }

            if (paramConsulta.getOrden() != null) {
                if (paramConsulta.getOrden().equals("asegAfiliado.maeTipoDocumento")) {
                    strQuery.append("ORDER BY m.asegAfiliadosId.maeTipoDocumentoId ")
                            .append(paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else if (paramConsulta.getOrden().equals("asegAfiliado.numeroDocumento")) {
                    strQuery.append("ORDER BY m.asegAfiliadosId.numeroDocumento ")
                            .append(paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else if (paramConsulta.getOrden().equals("asegAfiliado.nombres")) {
                    strQuery.append("ORDER BY m.asegAfiliadosId.primerNombre ")
                            .append(paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else if (paramConsulta.getOrden().equals("cntPrestadorSede.cntPrestador.razonSocial")) {
                    strQuery.append("ORDER BY m.cntPrestadorSedesId.cntPrestadoresId.razonSocial ")
                            .append(paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else if (paramConsulta.getOrden().equals("cntPrestadorSede.cntPrestador.numeroDocumento")) {
                    strQuery.append("ORDER BY m.cntPrestadorSedesId.cntPrestadoresId.numeroDocumento ")
                            .append(paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else if (paramConsulta.getOrden().equals("cntProfesional.nombreCompleto")) {
                    strQuery.append("ORDER BY m.cntProfesionalesId.primerNombre ")
                            .append(paramConsulta.isAscendente() ? "ASC" : "DESC");
                }  else if (paramConsulta.getOrden().equals("asegAfiliado.regimen")) {
                    strQuery.append("ORDER BY m.asegAfiliadosId.regimen ")
                            .append(paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else {
                    strQuery.append("ORDER BY m." + paramConsulta.getOrden() + " ")
                            .append(paramConsulta.isAscendente() ? "ASC" : "DESC");
                }
            } else {
                strQuery.append(" ORDER BY m.id DESC ");
                //strQuery.append("ORDER BY m.id");
            }

            List<MpPrescripciones> lista = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();

            for (MpPrescripciones pre : lista) {
                listaResultado.add(castEntidadNegocio(pre));
            }
        } catch (NoResultException e) {
            listaResultado = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }
    @Override
    public int consultarCantidadListaPorAfiliado(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT COUNT(m) FROM MpPrescripciones m WHERE 1 = 1 ");
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery.append("AND m.asegAfiliadosId.id = ").append((Integer) paramConsulta.getParametroConsulta1()).append(" ");
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry<String, Object> e : paramConsulta.getFiltros().entrySet()) {
                    switch (e.getKey()) {
                        case "numeroPrescripcion":
                            strQuery.append("AND m.numeroPrescripcion = '").append((String) e.getValue()).append("' ");
                            break;
                        case "consecutivoMipres":
                            strQuery.append("AND m.consecutivoMipres = '").append((String) e.getValue()).append("' ");
                            break;
                        case "tipoPrescripcion":
                            strQuery.append("AND m.tipoPrescripcion = '").append((String) e.getValue()).append("' ");
                            break;
                        case "asegAfiliado.maeTipoDocumento":
                            strQuery.append("AND m.asegAfiliadosId.maeTipoDocumentoId = '").append((String) e.getValue()).append("' ");
                            break;
                        case "asegAfiliado.numeroDocumento":
                            strQuery.append("AND m.asegAfiliadosId.numeroDocumento = '").append((String) e.getValue()).append("' ");
                            break;
                        case "asegAfiliado.nombres":
                            strQuery.append("AND (m.asegAfiliadosId.primerNombre LIKE '%").append((String) e.getValue()).append("%' ")
                                    .append("OR m.asegAfiliadosId.segundoNombre LIKE '%").append((String) e.getValue() + "%' ")
                                    .append("OR m.asegAfiliadosId.primerApellido LIKE '%").append(e.getValue() + "%' ")
                                    .append("OR m.asegAfiliadosId.segundoApellido LIKE '%").append(e.getValue() + "%') ")
                                    .append("OR CONCAT(m.asegAfiliadosId.primerNombre, ' ', COALESCE(m.asegAfiliadosId.segundoNombre,''), ' ', m.asegAfiliadosId.primerApellido, ' ', COALESCE(m.asegAfiliadosId.segundoApellido,'')) LIKE '%").append((String) e.getValue()).append("%' ");
                            break;
                        case "prestadorRazonSocial":
                            strQuery.append("AND m.prestadorRazonSocial LIKE '%").append((String) e.getValue()).append("%' ");
                            break;
                        case "prestadorNumeroDocumento":
                            strQuery.append("AND m.prestadorNumeroDocumento LIKE '%").append((String) e.getValue()).append("%' ");
                            break;
                        case "cntProfesional.nombreCompleto":
                           strQuery.append("AND (m.cntProfesionalesId.primerNombre LIKE '%").append((String) e.getValue()).append("%' ")
                                    .append("OR m.cntProfesionalesId.segundoNombre LIKE '%").append((String) e.getValue() + "%' ")
                                    .append("OR m.cntProfesionalesId.primerApellido LIKE '%").append(e.getValue() + "%' ")
                                    .append("OR m.cntProfesionalesId.segundoApellido LIKE '%").append(e.getValue() + "%') ");
                            break;
                        case "asegAfiliado.regimen":
                            strQuery.append("AND m.asegAfiliadosId.regimen = '").append((String) e.getValue()).append("' ");
                            break;
                        default:
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery.toString())
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
    public List<MpPrescripcion> consultarListaPorAfiliado(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<MpPrescripcion> listaResultado = new ArrayList<>();

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT m FROM MpPrescripciones m WHERE 1 = 1 ");
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery.append("AND m.asegAfiliadosId.id = ").append((Integer) paramConsulta.getParametroConsulta1()).append(" ");
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry<String, Object> e : paramConsulta.getFiltros().entrySet()) {
                    switch (e.getKey()) {
                        case "numeroPrescripcion":
                            strQuery.append("AND m.numeroPrescripcion = '").append((String) e.getValue()).append("' ");
                            break;
                        case "consecutivoMipres":
                            strQuery.append("AND m.consecutivoMipres = '").append((String) e.getValue()).append("' ");
                            break;
                        case "tipoPrescripcion":
                            strQuery.append("AND m.tipoPrescripcion = '").append((String) e.getValue()).append("' ");
                            break;
                        case "asegAfiliado.maeTipoDocumento":
                            strQuery.append("AND m.asegAfiliadosId.maeTipoDocumentoId = '").append((String) e.getValue()).append("' ");
                            break;
                        case "asegAfiliado.numeroDocumento":
                            strQuery.append("AND m.asegAfiliadosId.numeroDocumento = '").append((String) e.getValue()).append("' ");
                            break;
                        case "asegAfiliado.nombres":
                            strQuery.append("AND (m.asegAfiliadosId.primerNombre LIKE '%").append((String) e.getValue()).append("%' ")
                                    .append("OR m.asegAfiliadosId.segundoNombre LIKE '%").append((String) e.getValue() + "%' ")
                                    .append("OR m.asegAfiliadosId.primerApellido LIKE '%").append((String) e.getValue() + "%' ")
                                    .append("OR m.asegAfiliadosId.segundoApellido LIKE '%").append((String) e.getValue() + "%') ");
                                    //.append("OR CONCAT(m.asegAfiliadosId.primerNombre, ' ', COALESCE(m.asegAfiliadosId.segundoNombre,''), ' ', m.asegAfiliadosId.primerApellido, ' ', COALESCE(m.asegAfiliadosId.segundoApellido,'')) LIKE '%").append((String) e.getValue()).append("%')");
                            break;
                        case "prestadorRazonSocial":
                            strQuery.append("AND m.prestadorRazonSocial LIKE '%").append((String) e.getValue()).append("%' ");
                            break;
                        case "prestadorNumeroDocumento":
                            strQuery.append("AND m.prestadorNumeroDocumento LIKE '%").append((String) e.getValue()).append("%' ");
                            break;
                        case "cntProfesional.nombreCompleto":
                           strQuery.append("AND (m.cntProfesionalesId.primerNombre LIKE '%").append((String) e.getValue()).append("%' ")
                                    .append("OR m.cntProfesionalesId.segundoNombre LIKE '%").append((String) e.getValue() + "%' ")
                                    .append("OR m.cntProfesionalesId.primerApellido LIKE '%").append((String) e.getValue() + "%' ")
                                    .append("OR m.cntProfesionalesId.segundoApellido LIKE '%").append((String) e.getValue() + "%')");
                                    //.append("OR CONCAT(m.cntProfesionalesId.primerNombre, ' ', COALESCE(m.cntProfesionalesId.segundoNombre,''), ' ', m.cntProfesionalesId.primerApellido, ' ', COALESCE(m.cntProfesionalesId.segundoApellido,'')) LIKE '%").append((String) e.getValue()).append("%') ");
                            break;
                        case "asegAfiliado.regimen":
                            strQuery.append("AND m.asegAfiliadosId.regimen = '").append((String) e.getValue()).append("' ");
                            break;
                        case "recobrante":
                            strQuery.append("AND m.recobrante = '").append((String) e.getValue()).append("' ");
                            break;
                        default:
                            break;
                    }
                }
            }

            if (paramConsulta.getOrden() != null) {
                if (paramConsulta.getOrden().equals("asegAfiliado.maeTipoDocumento")) {
                    strQuery.append("ORDER BY m.asegAfiliadosId.maeTipoDocumentoId ")
                            .append(paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else if (paramConsulta.getOrden().equals("asegAfiliado.numeroDocumento")) {
                    strQuery.append("ORDER BY m.asegAfiliadosId.numeroDocumento ")
                            .append(paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else if (paramConsulta.getOrden().equals("asegAfiliado.nombres")) {
                    strQuery.append("ORDER BY m.asegAfiliadosId.primerNombre ")
                            .append(paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else if (paramConsulta.getOrden().equals("cntPrestadorSede.cntPrestador.razonSocial")) {
                    strQuery.append("ORDER BY m.cntPrestadorSedesId.cntPrestadoresId.razonSocial ")
                            .append(paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else if (paramConsulta.getOrden().equals("cntPrestadorSede.cntPrestador.numeroDocumento")) {
                    strQuery.append("ORDER BY m.cntPrestadorSedesId.cntPrestadoresId.numeroDocumento ")
                            .append(paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else if (paramConsulta.getOrden().equals("cntProfesional.nombreCompleto")) {
                    strQuery.append("ORDER BY m.cntProfesionalesId.primerNombre ")
                            .append(paramConsulta.isAscendente() ? "ASC" : "DESC");
                }  else if (paramConsulta.getOrden().equals("asegAfiliado.regimen")) {
                    strQuery.append("ORDER BY m.asegAfiliadosId.regimen ")
                            .append(paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else {
                    strQuery.append("ORDER BY m." + paramConsulta.getOrden() + " ")
                            .append(paramConsulta.isAscendente() ? "ASC" : "DESC");
                }
            } else {
                strQuery.append(" ORDER BY m.id DESC ");
            }

            List<MpPrescripciones> lista = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();

            for (MpPrescripciones pre : lista) {
                listaResultado.add(castEntidadNegocio(pre));
            }
        } catch (NoResultException e) {
            listaResultado = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }
    
    @Override
    public MpPrescripcion consultar(int id) throws Exception {
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
            obj.getAsegAfiliado().setNumeroDocumento(ent.getAsegAfiliadosId().getNumeroDocumento());
            obj.getAsegAfiliado().setPrimerNombre(ent.getAsegAfiliadosId().getPrimerNombre());
            obj.getAsegAfiliado().setSegundoNombre(ent.getAsegAfiliadosId().getSegundoNombre());
            obj.getAsegAfiliado().setPrimerApellido(ent.getAsegAfiliadosId().getPrimerApellido());
            obj.getAsegAfiliado().setSegundoApellido(ent.getAsegAfiliadosId().getSegundoApellido());
            obj.getAsegAfiliado().setRegimen(ent.getAsegAfiliadosId().getRegimen());
            obj.getAsegAfiliado().setGenero(ent.getAsegAfiliadosId().getGenero());
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

    public static MpPrescripcionRecobrante castEntidadNegocioRecobrante(MpPrescripcionRecobrantes ent) {
        MpPrescripcionRecobrante obj = new MpPrescripcionRecobrante();
        
        obj.setId(ent.getId());
        obj.setFallo(ent.getFallo());
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
    
}
