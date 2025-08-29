/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Carga;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupo;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoSede;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesionalPrestador;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Rescate;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo3Cargas;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo3Items;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos3;
import com.saviasaludeps.savia.ejb.entidades.CntContratoDetalles;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.CntProfesionalPrestadores;
import com.saviasaludeps.savia.ejb.entidades.CntProfesionales;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.entidades.PeAfiliadosProgramas;
import com.saviasaludeps.savia.ejb.entidades.PeProgramas;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo2Rescates;
import com.saviasaludeps.savia.ejb.servicios.aseguramiento.AfiliadoServicio;
import com.saviasaludeps.savia.ejb.servicios.contratacion.CntProfesionalServicio;
import com.saviasaludeps.savia.ejb.servicios.especial.PeAfiliadoSugeridoServicio;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.utilidades.Util;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3Remoto;
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

/**
 *
 * @author Stiven Giraldo
 */
@Stateless
@Remote(AuAnexo3Remoto.class)
public class AuAnexo3Servicio extends GenericoServicio implements AuAnexo3Remoto {

    private static final SimpleDateFormat formatoCorto = new SimpleDateFormat("yyyy-MM-dd");

    private static List<AuAnexo3Item> castearListaItemEntidadNegocio(List<AuAnexo3Items> auAnexo3ItemsList) {
        List<AuAnexo3Item> lista = new ArrayList();
        for (AuAnexo3Items item : auAnexo3ItemsList) {
            boolean agregar = true;
            if (item.getPosfechado() != null && item.getPosfechado() == true) {
                if (item.getPosfechadoPrincipal() == null) {
                    agregar = false;
                } else {
                    if (item.getPosfechadoPrincipal() == false) {
                        agregar = false;
                    }
                }
            }
            if (agregar) {
                AuAnexo3Item newItem = new AuAnexo3Item();
                newItem.setId(item.getId());
                if (item.getAuGruposId() != null) {
                    newItem.setAuGrupoId(new AuGrupo(item.getAuGruposId().getId()));
                    newItem.getAuGrupoId().setNombre(item.getAuGruposId().getNombre());
                    newItem.getAuGrupoId().setDescripcion(item.getAuGruposId().getDescripcion());
                }
                if (item.getGnUsuariosId() != null) {
                    newItem.setGnUsuarioId(new Usuario(item.getGnUsuariosId().getId()));
                    newItem.getGnUsuarioId().setNombre(item.getGnUsuariosId().getNombre());
                    newItem.getGnUsuarioId().setUsuario(item.getGnUsuariosId().getUsuario());
                }
                newItem.setEstado(item.getEstado());
                newItem.setMaTecnologiaId(item.getMaTecnologiaId());
                newItem.setMaTecnologiaCodigo(item.getMaTecnologiaCodigo());
                newItem.setMaTecnologiaValor(item.getMaTecnologiaValor());
                lista.add(newItem);
            }
        }
        return lista;
    }
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(u) FROM AuAnexos3 u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            if (paramConsulta.getEmpresaId() != null) {
                strTitulo = agregarJoin("INNER JOIN GnEmpresas gne ON u.gnEmpresasId = gne.id ", strTitulo);
                strQuery.append("AND gne.id = ").append(paramConsulta.getEmpresaId()).append(" ");
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND u.id = ").append(e.getValue()).append(" ");
                            break;
                        case "fuenteOrigen":
                            strQuery.append("AND u.fuenteOrigen = ").append(e.getValue()).append(" ");
                            break;
                        case "asegAfiliadoId.maeTipoDocumento":
                            strTitulo = agregarJoin("INNER JOIN AsegAfiliados aseg ON u.asegAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append("AND aseg.maeTipoDocumentoId = ").append(e.getValue()).append(" ");
                            break;
                        case "asegAfiliadoId.numeroDocumento":
                            strTitulo = agregarJoin("INNER JOIN AsegAfiliados aseg ON u.asegAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append("AND aseg.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.numeroDocumento = '").append(e.getValue()).append("') ");
                            break;
                        case "asegAfiliadoId.apellidos":
                            strTitulo = agregarJoin("INNER JOIN AsegAfiliados aseg ON u.asegAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append("AND aseg.primerApellido LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "asegAfiliadoId.nombres":
                            strTitulo = agregarJoin("INNER JOIN AsegAfiliados aseg ON u.asegAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append("AND aseg.primerNombre LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "numero":
                            strQuery.append("AND u.numero = ").append(e.getValue()).append(" ");
                            break;
                        case "fechaSolicitud":
                            strQuery.append("AND u.fechaSolicitud = ").append(e.getValue()).append(" ");
                            break;
                        case "maeAmbitoAtencionId":
                            strQuery.append("AND u.maeAmbitoAtencionId = ").append(e.getValue()).append(" ");
                            break;  
                        case "cntPrestadorSedeId":
                            strTitulo = agregarJoin("INNER JOIN CntPrestadorSedes cntps ON u.cntPrestadorSedesId = cntps.id ", strTitulo);
                            strQuery.append("AND cntps.id = ").append(e.getValue()).append(" ");
                            break;
                        case "maServicioSolicitadoId":
                            strQuery.append("AND u.maServicioSolicitadoId = ").append(e.getValue()).append(" ");
                            break;
                        case "usuarioCrea":
                            strQuery.append("AND u.usuarioCrea LIKE '%").append(e.getValue()).append("% ");
                            break;
                        case "cntProfesionaleId":
                            strTitulo = agregarJoin("INNER JOIN CntProfesionales cntpf ON u.cntProfesionalesId = cntpf.id ", strTitulo);
                            strQuery.append("AND cntpf.id = ").append(e.getValue()).append(" ");
                            break;
                        case "estado":
                            strQuery.append("AND u.estado = ").append(e.getValue()).append(" ");
                            break;
                        case "cntPrestadorSedeNombre":
                            strTitulo = agregarJoin("INNER JOIN CntPrestadorSedes cps ON u.cntPrestadorSedesId = cps.id ", strTitulo);
                            strTitulo = agregarJoin("INNER JOIN CntPrestadores cnt ON cps.cntPrestadoresId = cnt.id ", strTitulo);
                            strQuery.append("AND cnt.razonSocial LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "prioridad":
                            strQuery.append("AND u.prioridad = ").append(e.getValue()).append(" ");
                            break;
                        case "auAnexo3ItemsList":
                            strTitulo = "SELECT COUNT(DISTINCT u) FROM AuAnexos3 u ";
                            strTitulo = agregarJoin("LEFT JOIN AuAnexo3Items ai ON u.id = ai.auAnexos3Id ", strTitulo);
                            strTitulo = agregarJoin("INNER JOIN CntPrestadorSedes cps ON u.cntPrestadorSedesId = cps.id ", strTitulo);
                            strTitulo = agregarJoin("INNER JOIN CntPrestadores cnt ON cps.cntPrestadoresId = cnt.id ", strTitulo);
                            strQuery.append("AND ai.maTecnologiaCodigo = ").append(e.getValue()).append(" ");
                            break;
                        case "objetoTecnologia.auditor":
                            strTitulo = "SELECT COUNT(DISTINCT u) FROM AuAnexos3 u ";
                            strTitulo = agregarJoin("LEFT JOIN AuAnexo3Items ai ON u.id = ai.auAnexos3Id ", strTitulo);
                            strTitulo = agregarJoin("INNER JOIN GnUsuarios gnu ON ai.gnUsuariosId = gnu.id ", strTitulo);
                            strTitulo = agregarJoin("INNER JOIN CntPrestadorSedes cps ON u.cntPrestadorSedesId = cps.id ", strTitulo);
                            strTitulo = agregarJoin("INNER JOIN CntPrestadores cnt ON cps.cntPrestadoresId = cnt.id ", strTitulo);
                            strQuery.append("AND gnu.usuario = '").append(e.getValue()).append("' ");
                            break;
                        case "objetoTecnologia.auGrupoId":
                            strTitulo = "SELECT COUNT(DISTINCT u) FROM AuAnexos3 u ";
                            strTitulo = agregarJoin("LEFT JOIN AuAnexo3Items ai ON u.id = ai.auAnexos3Id ", strTitulo);
                            strTitulo = agregarJoin("INNER JOIN AuGrupos aug ON ai.auGruposId = aug.id ", strTitulo);
                            strTitulo = agregarJoin("INNER JOIN CntPrestadorSedes cps ON u.cntPrestadorSedesId = cps.id ", strTitulo);
                            strTitulo = agregarJoin("INNER JOIN CntPrestadores cnt ON cps.cntPrestadoresId = cnt.id ", strTitulo);
                            strQuery.append("AND aug.id = ").append(e.getValue()).append(" ");
                            break;
                        case "cntPrestadorSedeId.ubicacionId":
                            strTitulo = agregarJoin("INNER JOIN CntPrestadorSedes cntps ON u.cntPrestadorSedesId = cntps.id ", strTitulo);
                            strQuery.append("AND cntps.ubicacionId = ").append(e.getValue()).append(" ");
                            break;
                        case "peProgramaEspecialId":
                            String cadenaPrograma = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery.append("AND u.peProgramaEspecialId IN (").append(cadenaPrograma).append(") ");
                            break;
                    }
                }
            }
            
            sql.append(strTitulo).append(strQuery);
            if (paramConsulta.getParametroConsulta1() != null) {
                if (paramConsulta.getParametroConsulta2() != null) {
                    sql.append("AND u.fechaHoraCrea BETWEEN '")
                       .append(formatoCorto.format(paramConsulta.getParametroConsulta1()))
                       .append(" 00:00:00' AND '")
                       .append(formatoCorto.format(paramConsulta.getParametroConsulta2()))
                       .append(" 23:59:59' ");
                 
                } else {
                    sql.append("AND u.fechaHoraCrea >= '")
                       .append(formatoCorto.format(paramConsulta.getParametroConsulta1()))
                       .append(" 00:00:00' ");
                }
            } else {
                if (paramConsulta.getParametroConsulta2() != null) {
                    sql.append("AND u.fechaHoraCrea <= '")
                       .append(formatoCorto.format(paramConsulta.getParametroConsulta2()))
                       .append(" 23:59:59' ");
                }
            }
            cant = (int) (long) getEntityManager().createQuery(sql.toString())
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
    
    /*@Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(p) FROM AuAnexos3 p ";
            String strQuery = "";

            if (paramConsulta.getEmpresaId() != null) {
                strTitulo += "INNER JOIN p.gnEmpresasId e ON e.id = p.gnEmpresasId.id ";
                strQuery += "WHERE e.id = " + paramConsulta.getEmpresaId() + " ";
            } else {
                strQuery += "WHERE p.id > 0 ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "fuenteOrigen":
                            strQuery += "AND p.fuenteOrigen = " + e.getValue() + " ";
                            break;
                        case "asegAfiliadoId.maeTipoDocumento":
                            strQuery += "AND p.asegAfiliadosId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "asegAfiliadoId.numeroDocumento":
                            strQuery += "AND p.asegAfiliadosId.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.numeroDocumento = '" + (String) e.getValue() + "' ) ";
                            break;
                        case "asegAfiliadoId.apellidos":
                            strQuery += "AND p.asegAfiliadosId.primerApellido LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "asegAfiliadoId.nombres":
                            strQuery += "AND p.asegAfiliadosId.primerNombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "numero":
                            strQuery += "AND p.numero = " + e.getValue() + " ";
                            break;
                        case "fechaSolicitud":
                            strQuery += "AND p.fechaSolicitud = " + e.getValue() + " ";
                            break;
                        case "maeAmbitoAtencionId":
                            strQuery += "AND p.maeAmbitoAtencionId = " + e.getValue() + " ";
                            break;
                        case "cntPrestadorSedeId":
                            strQuery += "AND p.cntPrestadorSedesId = " + e.getValue() + " ";
                            break;
                        case "maServicioSolicitadoId":
                            strQuery += "AND p.maServicioSolicitadoId = " + e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntProfesionaleId":
                            strQuery += "AND p.cntProfesionalesId = " + e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "cntPrestadorSedeNombre":
                            strQuery += "AND p.cntPrestadorSedesId.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "prioridad":
                            strQuery += "AND p.prioridad = " + e.getValue() + " ";
                            break;
                        case "auAnexo3ItemsList":
                            strTitulo = "SELECT COUNT(DISTINCT p) FROM AuAnexos3 p "
                                    + "LEFT JOIN AuAnexo3Items ai ON p.id = ai.auAnexos3Id.id ";
                            strQuery += "AND ai.maTecnologiaCodigo = '" + e.getValue() + "' ";
                            break;
                        case "objetoTecnologia.auditor":
                            strTitulo = "SELECT COUNT(DISTINCT p) FROM AuAnexos3 p "
                                    + "LEFT JOIN AuAnexo3Items ai ON p.id = ai.auAnexos3Id.id ";
                            strQuery += "AND ai.gnUsuariosId.usuario = '" + e.getValue() + "' ";
                            break;
                        case "objetoTecnologia.auGrupoId":
                            strTitulo = "SELECT COUNT(DISTINCT p) FROM AuAnexos3 p "
                                    + "LEFT JOIN AuAnexo3Items ai ON p.id = ai.auAnexos3Id.id ";
                            strQuery += "AND ai.auGruposId.id = " + e.getValue() + " ";
                            break;
                        case "cntPrestadorSedeId.ubicacionId":
                            strQuery += "AND p.cntPrestadorSedesId.ubicacionId = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            strQuery = strTitulo + strQuery;
            if (paramConsulta.getParametroConsulta1() != null) {
                if (paramConsulta.getParametroConsulta2() != null) {
                    strQuery += "AND p.fechaHoraCrea BETWEEN '" + formatoCorto.format(paramConsulta.getParametroConsulta1()) + " 00:00:00' AND '" + formatoCorto.format(paramConsulta.getParametroConsulta2()) + " 23:59:59' ";
                } else {
                    strQuery += "AND p.fechaHoraCrea >= '" + formatoCorto.format(paramConsulta.getParametroConsulta1()) + " 00:00:00' ";
                }
            } else {
                if (paramConsulta.getParametroConsulta2() != null) {
                    strQuery += "AND p.fechaHoraCrea <= '" + formatoCorto.format(paramConsulta.getParametroConsulta2()) + " 23:59:59' ";
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
    }*/
    
    @Override
    public List<AuAnexo3> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo3> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuAnexos3 u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            if (paramConsulta.getEmpresaId() != null) {
                strTitulo = agregarJoin("INNER JOIN GnEmpresas gne ON u.gnEmpresasId = gne.id ", strTitulo);
                strQuery.append("AND gne.id = ").append(paramConsulta.getEmpresaId()).append(" ");
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND u.id = ").append(e.getValue()).append(" ");
                            break;
                        case "fuenteOrigen":
                            strQuery.append("AND u.fuenteOrigen = ").append(e.getValue()).append(" ");
                            break;
                        case "asegAfiliadoId.maeTipoDocumento":
                            strTitulo = agregarJoin("INNER JOIN AsegAfiliados aseg ON u.asegAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append("AND aseg.maeTipoDocumentoId = ").append(e.getValue()).append(" ");
                            break;
                        case "asegAfiliadoId.numeroDocumento":
                            strTitulo = agregarJoin("INNER JOIN AsegAfiliados aseg ON u.asegAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append("AND aseg.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.numeroDocumento = '").append(e.getValue()).append("') ");
                            break;
                        case "asegAfiliadoId.apellidos":
                            strTitulo = agregarJoin("INNER JOIN AsegAfiliados aseg ON u.asegAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append("AND aseg.primerApellido LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "asegAfiliadoId.nombres":
                            strTitulo = agregarJoin("INNER JOIN AsegAfiliados aseg ON u.asegAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append("AND aseg.primerNombre LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "numero":
                            strQuery.append("AND u.numero = ").append(e.getValue()).append(" ");
                            break;
                        case "fechaSolicitud":
                            strQuery.append("AND u.fechaSolicitud = ").append(e.getValue()).append(" ");
                            break;
                        case "maeAmbitoAtencionId":
                            strQuery.append("AND u.maeAmbitoAtencionId = ").append(e.getValue()).append(" ");
                            break;  
                        case "cntPrestadorSedeId":
                            strTitulo = agregarJoin("INNER JOIN CntPrestadorSedes cntps ON u.cntPrestadorSedesId = cntps.id ", strTitulo);
                            strQuery.append("AND cntps.id = ").append(e.getValue()).append(" ");
                            break;
                        case "maServicioSolicitadoId":
                            strQuery.append("AND u.maServicioSolicitadoId = ").append(e.getValue()).append(" ");
                            break;
                        case "usuarioCrea":
                            strQuery.append("AND u.usuarioCrea LIKE '%").append(e.getValue()).append("% ");
                            break;
                        case "cntProfesionaleId":
                            strTitulo = agregarJoin("INNER JOIN CntProfesionales cntpf ON u.cntProfesionalesId = cntpf.id ", strTitulo);
                            strQuery.append("AND cntpf.id = ").append(e.getValue()).append(" ");
                            break;
                        case "estado":
                            strQuery.append("AND u.estado = ").append(e.getValue()).append(" ");
                            break;
                        case "cntPrestadorSedeNombre":
                            strTitulo = agregarJoin("INNER JOIN CntPrestadorSedes cps ON u.cntPrestadorSedesId = cps.id ", strTitulo);
                            strTitulo = agregarJoin("INNER JOIN CntPrestadores cnt ON cps.cntPrestadoresId = cnt.id ", strTitulo);
                            strQuery.append("AND cnt.razonSocial LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "prioridad":
                            strQuery.append("AND u.prioridad = ").append(e.getValue()).append(" ");
                            break;
                        case "auAnexo3ItemsList":
                            strTitulo = "SELECT DISTINCT u FROM AuAnexos3 u ";
                            strTitulo = agregarJoin("LEFT JOIN AuAnexo3Items ai ON u.id = ai.auAnexos3Id ", strTitulo);
                            strTitulo = agregarJoin("INNER JOIN CntPrestadorSedes cps ON u.cntPrestadorSedesId = cps.id ", strTitulo);
                            strTitulo = agregarJoin("INNER JOIN CntPrestadores cnt ON cps.cntPrestadoresId = cnt.id ", strTitulo);
                            strQuery.append("AND ai.maTecnologiaCodigo = ").append(e.getValue()).append(" ");
                            break;
                        case "objetoTecnologia.auditor":
                            strTitulo = "SELECT DISTINCT u FROM AuAnexos3 u ";
                            strTitulo = agregarJoin("LEFT JOIN AuAnexo3Items ai ON u.id = ai.auAnexos3Id ", strTitulo);
                            strTitulo = agregarJoin("INNER JOIN GnUsuarios gnu ON ai.gnUsuariosId = gnu.id ", strTitulo);
                            strTitulo = agregarJoin("INNER JOIN CntPrestadorSedes cps ON u.cntPrestadorSedesId = cps.id ", strTitulo);
                            strTitulo = agregarJoin("INNER JOIN CntPrestadores cnt ON cps.cntPrestadoresId = cnt.id ", strTitulo);
                            strQuery.append("AND gnu.usuario = '").append(e.getValue()).append("' ");
                            break;
                        case "objetoTecnologia.auGrupoId":
                            strTitulo = "SELECT DISTINCT u FROM AuAnexos3 u ";
                            strTitulo = agregarJoin("LEFT JOIN AuAnexo3Items ai ON u.id = ai.auAnexos3Id ", strTitulo);
                            strTitulo = agregarJoin("INNER JOIN AuGrupos aug ON ai.auGruposId = aug.id ", strTitulo);
                            strTitulo = agregarJoin("INNER JOIN CntPrestadorSedes cps ON u.cntPrestadorSedesId = cps.id ", strTitulo);
                            strTitulo = agregarJoin("INNER JOIN CntPrestadores cnt ON cps.cntPrestadoresId = cnt.id ", strTitulo);
                            strQuery.append("AND aug.id = ").append(e.getValue()).append(" ");
                            break;
                        case "cntPrestadorSedeId.ubicacionId":
                            strTitulo = agregarJoin("INNER JOIN CntPrestadorSedes cntps ON u.cntPrestadorSedesId = cntps.id ", strTitulo);
                            strQuery.append("AND cntps.ubicacionId = ").append(e.getValue()).append(" ");
                            break;
                        case "peProgramaEspecialId":
                            String cadenaPrograma = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery.append("AND u.peProgramaEspecialId IN (").append(cadenaPrograma).append(") ");
                            break;
                    }
                }
            }
            sql.append(strTitulo).append(strQuery);
            if (paramConsulta.getParametroConsulta1() != null) {
                if (paramConsulta.getParametroConsulta2() != null) {
                    sql.append("AND u.fechaHoraCrea BETWEEN '")
                       .append(formatoCorto.format(paramConsulta.getParametroConsulta1()))
                       .append(" 00:00:00' AND '")
                       .append(formatoCorto.format(paramConsulta.getParametroConsulta2()))
                       .append(" 23:59:59' ");
                 
                } else {
                    sql.append("AND u.fechaHoraCrea >= '")
                       .append(formatoCorto.format(paramConsulta.getParametroConsulta1()))
                       .append(" 00:00:00' ");
                }
            } else {
                if (paramConsulta.getParametroConsulta2() != null) {
                    sql.append("AND u.fechaHoraCrea <= '")
                       .append(formatoCorto.format(paramConsulta.getParametroConsulta2()))
                       .append(" 23:59:59' ");
                }
            }
            sql.append("ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                sql.append("u.").append(paramConsulta.getOrden()).append((paramConsulta.isAscendente() ? " ASC" : " DESC"));
            } else {
                sql.append("u.id DESC");
            }
            List<AuAnexos3> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexos3 per : list) {
                listResult.add(castEntidadNegocioLista(per));
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
    
    /*@Override
    public List<AuAnexo3> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo3> listaResultados = new ArrayList();
        try {
            String strTitulo = "SELECT p FROM AuAnexos3 p ";
            String strQuery = "";
            if (paramConsulta.getEmpresaId() != null) {
                strTitulo += "INNER JOIN p.gnEmpresasId e ON e.id = p.gnEmpresasId.id ";
                strQuery += "WHERE e.id = " + paramConsulta.getEmpresaId() + " ";
            } else {
                strQuery += "WHERE p.id > 0 ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "fuenteOrigen":
                            strQuery += "AND p.fuenteOrigen = " + e.getValue() + " ";
                            break;
                        case "asegAfiliadoId.maeTipoDocumento":
                            strQuery += "AND p.asegAfiliadosId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "asegAfiliadoId.numeroDocumento":
                            strQuery += "AND p.asegAfiliadosId.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.numeroDocumento = '" + (String) e.getValue() + "' ) ";
                            break;
                        case "asegAfiliadoId.apellidos":
                            strQuery += "AND p.asegAfiliadosId.primerApellido LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "asegAfiliadoId.nombres":
                            strQuery += "AND p.asegAfiliadosId.primerNombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "numero":
                            strQuery += "AND p.numero = " + e.getValue() + " ";
                            break;
                        case "fechaSolicitud":
                            strQuery += "AND p.fechaSolicitud = " + e.getValue() + " ";
                            break;
                        case "maeAmbitoAtencionId":
                            strQuery += "AND p.maeAmbitoAtencionId = " + e.getValue() + " ";
                            break;
                        case "cntPrestadorSedeId":
                            strQuery += "AND p.cntPrestadorSedesId = " + e.getValue() + " ";
                            break;
                        case "maServicioSolicitadoId":
                            strQuery += "AND p.maServicioSolicitadoId = " + e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntProfesionaleId":
                            strQuery += "AND p.cntProfesionalesId = " + e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "cntPrestadorSedeNombre":
                            strQuery += "AND p.cntPrestadorSedesId.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "prioridad":
                            strQuery += "AND p.prioridad = " + e.getValue() + " ";
                            break;
                        case "auAnexo3ItemsList":
                            strTitulo = "SELECT DISTINCT p FROM AuAnexos3 p "
                                    + "LEFT JOIN AuAnexo3Items ai ON p.id = ai.auAnexos3Id.id ";
                            strQuery += "AND ai.maTecnologiaCodigo = '" + e.getValue() + "' ";
                            break;
                        case "objetoTecnologia.auditor":
                            strTitulo = "SELECT DISTINCT p FROM AuAnexos3 p "
                                    + "LEFT JOIN AuAnexo3Items ai ON p.id = ai.auAnexos3Id.id ";
                            strQuery += "AND ai.gnUsuariosId.usuario = '" + e.getValue() + "' ";
                            break;
                        case "objetoTecnologia.auGrupoId":
                            strTitulo = "SELECT DISTINCT p FROM AuAnexos3 p "
                                    + "LEFT JOIN AuAnexo3Items ai ON p.id = ai.auAnexos3Id.id ";
                            strQuery += "AND ai.auGruposId.id = " + e.getValue() + " ";
                            break;
                        case "cntPrestadorSedeId.ubicacionId":
                            strQuery += "AND p.cntPrestadorSedesId.ubicacionId = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            strQuery = strTitulo + strQuery;
            if (paramConsulta.getParametroConsulta1() != null) {
                if (paramConsulta.getParametroConsulta2() != null) {
                    strQuery += "AND p.fechaHoraCrea BETWEEN '" + formatoCorto.format(paramConsulta.getParametroConsulta1()) + " 00:00:00' AND '" + formatoCorto.format(paramConsulta.getParametroConsulta2()) + " 23:59:59' ";
                } else {
                    strQuery += "AND p.fechaHoraCrea >= '" + formatoCorto.format(paramConsulta.getParametroConsulta1()) + " 00:00:00' ";
                }
            } else {
                if (paramConsulta.getParametroConsulta2() != null) {
                    strQuery += "AND p.fechaHoraCrea <= '" + formatoCorto.format(paramConsulta.getParametroConsulta2()) + " 23:59:59' ";
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<AuAnexos3> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexos3 anexo3 : list) {
                listaResultados.add(castEntidadNegocioLista(anexo3));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }*/
    
    private String agregarJoin(String join, String sql) {
        if (sql.contains(join)) {
            return sql;
        } else {
            return sql += join;
        }
    }
    
    @Override
    public AuAnexo3 consultar(int id) throws Exception {
        AuAnexo3 objRes = null;
        try {
            AuAnexos3 anex3 = (AuAnexos3) getEntityManager().find(AuAnexos3.class, id);
            if(anex3 != null){
                objRes = castEntidadNegocioLargo(anex3);
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
    public int insertar(AuAnexo3 obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidadCorto(obj)).getId();
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
    public void actualizar(AuAnexo3 obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexos3 a SET ";
            strQuery += "a.id = :id ,";
            if (obj.getGnEmpresaId() != null) {
                strQuery += "a.gnEmpresasId.id = :gnEmpresasId ,";
            }
            if (obj.getAuAnexo3CargaId() != null && obj.getAuAnexo3CargaId().getId() != null) {
                strQuery += "a.auAnexo3CargasId.id = :auAnexo3CargasId ,";
            }
            strQuery += "a.numero = :numero ,";
            strQuery += "a.fechaSolicitud = :fechaSolicitud ,";
            strQuery += "a.cntPrestadorSedesId.id = :cntPrestadorSedesId ,";
            strQuery += "a.asegAfiliadosId.id = :asegAfiliadosId ,";
            strQuery += "a.nombreAcompanante = :nombreAcompanante ,";
            strQuery += "a.telefonoAcompanante = :telefonoAcompanante ,";
            strQuery += "a.celularAcompanente = :celularAcompanente ,";
            strQuery += "a.estado = :estado ,";
            if (obj.getMaeEstadoMotivoId() != null) {
                strQuery += "a.maeEstadoMotivoId = :maeEstadoMotivoId ,";
                strQuery += "a.maeEstadoMotivoCodigo = :maeEstadoMotivoCodigo ,";
                strQuery += "a.maeEstadoMotivoValor = :maeEstadoMotivoValor ,";
                strQuery += "a.estadoJustificacion = :estadoJustificacion,";
            }
            if (obj.getMaeCausaExternaId() != null) {
                strQuery += "a.maeCausaExternaId = :maeCausaExternaId ,";
                strQuery += "a.maeCausaExternaCodigo = :maeCausaExternaCodigo ,";
                strQuery += "a.maeCausaExternaValor = :maeCausaExternaValor ,";
            }
            strQuery += "a.maeAmbitoAtencionId = :maeAmbitoAtencionId ,";
            strQuery += "a.maeAmbitoAtencionCodigo = :maeAmbitoAtencionCodigo ,";
            strQuery += "a.maeAmbitoAtencionValor = :maeAmbitoAtencionValor ,";
            strQuery += "a.maServicioSolicitadoId = :maServicioSolicitadoId ,";
            strQuery += "a.maServicioSolicitadoCodigo = :maServicioSolicitadoCodigo ,";
            strQuery += "a.maServicioSolicitadoValor = :maServicioSolicitadoValor ,";
            strQuery += "a.maServicioHabilitadoId = :maServicioHabilitadoId ,";
            strQuery += "a.maServicioHabilitadoCodigo = :maServicioHabilitadoCodigo ,";
            strQuery += "a.maServicioHabilitadoValor = :maServicioHabilitadoValor ,";
            if (obj.getPeProgramaId() != null) {
                strQuery += "a.peProgramasId.id = :peProgramasId ,";
            }
            strQuery += "a.maeOrigenAtencionId = :maeOrigenAtencionId ,";
            strQuery += "a.maeOrigenAtencionCodigo = :maeOrigenAtencionCodigo ,";
            strQuery += "a.maeOrigenAtencionValor = :maeOrigenAtencionValor ,";
            strQuery += "a.prioridad = :prioridad ,";
            strQuery += "a.maeTipoServicioId = :maeTipoServicioId ,";
            strQuery += "a.maeTipoServicioCodigo = :maeTipoServicioCodigo ,";
            strQuery += "a.maeTipoServicioValor = :maeTipoServicioValor ,";
            strQuery += "a.maeUbicacionId = :maeUbicacionId ,";
            strQuery += "a.maeUbicacionCodigo = :maeUbicacionCodigo ,";
            strQuery += "a.maeUbicacionValor = :maeUbicacionValor ,";
            if (obj.getCama() != null) {
                strQuery += "a.cama = :cama ,";
            }
            strQuery += "a.justificacionClinica = :justificacionClinica ,";
            strQuery += "a.cntProfesionalesId.id = :cntProfesionalesId ,";
            strQuery += "a.nombreProfesional = :nombreProfesional ,";
            strQuery += "a.cargoProfesional = :cargoProfesional ,";
            //strQuery += "a.telefonoProfesional = :telefonoProfesional ,";
            //strQuery += "a.celularProfesional = :celularProfesional ,";
            strQuery += "a.fuenteOrigen = :fuenteOrigen ,";
            if (obj.getPeProgramaEspecialId() != null) {
                strQuery += "a.peProgramaEspecialId = :peProgramaEspecialId ,";
                strQuery += "a.peProgramaEspecialValor = :peProgramaEspecialValor ,";
                strQuery += "a.peProgramaEspecialCodigo = :peProgramaEspecialCodigo ,";
            }
            if (obj.getMaeGuiaManejoIntegralId() != null) {
                strQuery += "a.maeGuiaManejoIntegralId = :maeGuiaManejoIntegralId ,";
                strQuery += "a.maeGuiaManejoIntegralCodigo = :maeGuiaManejoIntegralCodigo ,";
                strQuery += "a.maeGuiaManejoIntegralValor = :maeGuiaManejoIntegralValor ,";
            }
            strQuery += "a.cuotaModeradora = :cuotaModeradora ,";
            strQuery += "a.cuotaRecuperacion = :cuotaRecuperacion ,";
            strQuery += "a.copago = :copago ,";
            strQuery += "a.afiliadoDireccionAlternativa = :afiliadoDireccionAlternativa ,";
            strQuery += "a.maeModalidadTecnologiaId = :maeModalidadTecnologiaId ,";
            strQuery += "a.maeModalidadTecnologiaCodigo = :maeModalidadTecnologiaCodigo ,";
            strQuery += "a.maeModalidadTecnologiaValor = :maeModalidadTecnologiaValor ,";
            strQuery += "a.maeFinalidadTecnologiaId = :maeFinalidadTecnologiaId ,";
            strQuery += "a.maeFinalidadTecnologiaCodigo = :maeFinalidadTecnologiaCodigo ,";
            strQuery += "a.maeFinalidadTecnologiaValor = :maeFinalidadTecnologiaValor ,"; 
            if(obj.getFuenteAnula() != null){
                strQuery += "a.fuenteAnula = :fuenteAnula ,";
            }
            if(obj.getAuAnexo3CargaAnuladasId() !=  null){
                strQuery += "a.auAnexo3CargaAnuladasId.id = :auAnexo3CargaAnuladasId ,"; 
            }
            strQuery += "a.usuarioCrea = :usuarioCrea ,";
            strQuery += "a.terminalCrea = :terminalCrea ,";
            strQuery += "a.fechaHoraCrea = :fechaHoraCrea ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            if (obj.getGnEmpresaId() != null) {
                query.setParameter("gnEmpresasId", obj.getGnEmpresaId().getId());
            }
            if (obj.getAuAnexo3CargaId() != null && obj.getAuAnexo3CargaId().getId() != null) {
                query.setParameter("auAnexo3CargasId", obj.getAuAnexo3CargaId().getId());
            }
            query.setParameter("numero", obj.getNumero());
            query.setParameter("fechaSolicitud", obj.getFechaSolicitud());
            query.setParameter("cntPrestadorSedesId", obj.getCntPrestadorSedeId().getId());
            query.setParameter("asegAfiliadosId", obj.getAsegAfiliadoId().getId());
            query.setParameter("nombreAcompanante", obj.getNombreAcompanante());
            query.setParameter("telefonoAcompanante", obj.getTelefonoAcompanante());
            query.setParameter("celularAcompanente", obj.getCelularAcompanente());
            query.setParameter("estado", obj.getEstado());
            if (obj.getMaeEstadoMotivoId() != null) {
                query.setParameter("maeEstadoMotivoId", obj.getMaeEstadoMotivoId());
                query.setParameter("maeEstadoMotivoCodigo", obj.getMaeEstadoMotivoCodigo());
                query.setParameter("maeEstadoMotivoValor", obj.getMaeEstadoMotivoValor());
                query.setParameter("estadoJustificacion", obj.getEstadoJustificacion());
            }
            if (obj.getMaeCausaExternaId() != null) {
                query.setParameter("maeCausaExternaId", obj.getMaeCausaExternaId());
                query.setParameter("maeCausaExternaCodigo", obj.getMaeCausaExternaCodigo());
                query.setParameter("maeCausaExternaValor", obj.getMaeCausaExternaValor());
            }
            query.setParameter("maeAmbitoAtencionId", obj.getMaeAmbitoAtencionId());
            query.setParameter("maeAmbitoAtencionCodigo", obj.getMaeAmbitoAtencionCodigo());
            query.setParameter("maeAmbitoAtencionValor", obj.getMaeAmbitoAtencionValor());
            query.setParameter("maServicioSolicitadoId", obj.getMaServicioSolicitadoId());
            query.setParameter("maServicioSolicitadoCodigo", obj.getMaServicioSolicitadoCodigo());
            query.setParameter("maServicioSolicitadoValor", obj.getMaServicioSolicitadoValor());
            query.setParameter("maServicioHabilitadoId", obj.getMaServicioHabilitadoId());
            query.setParameter("maServicioHabilitadoCodigo", obj.getMaServicioHabilitadoCodigo());
            query.setParameter("maServicioHabilitadoValor", obj.getMaServicioHabilitadoValor());
            if (obj.getPeProgramaId() != null) {
                query.setParameter("peProgramasId", obj.getPeProgramaId().getId());
            }
            query.setParameter("maeOrigenAtencionId", obj.getMaeOrigenAtencionId());
            query.setParameter("maeOrigenAtencionCodigo", obj.getMaeOrigenAtencionCodigo());
            query.setParameter("maeOrigenAtencionValor", obj.getMaeOrigenAtencionValor());
            query.setParameter("prioridad", obj.getPrioridad());
            query.setParameter("maeTipoServicioId", obj.getMaeTipoServicioId());
            query.setParameter("maeTipoServicioCodigo", obj.getMaeTipoServicioCodigo());
            query.setParameter("maeTipoServicioValor", obj.getMaeTipoServicioValor());
            query.setParameter("maeUbicacionId", obj.getMaeUbicacionId());
            query.setParameter("maeUbicacionCodigo", obj.getMaeUbicacionCodigo());
            query.setParameter("maeUbicacionValor", obj.getMaeUbicacionValor());
            if (obj.getCama() != null) {
                query.setParameter("cama", obj);
            }
            query.setParameter("justificacionClinica", obj.getJustificacionClinica());
            query.setParameter("cntProfesionalesId", obj.getCntProfesionaleId().getId());
            query.setParameter("nombreProfesional", obj.getNombreProfesional());
            query.setParameter("cargoProfesional", obj.getCargoProfesional());
            query.setParameter("fuenteOrigen", obj.getFuenteOrigen());
            if (obj.getPeProgramaEspecialId() != null) {
                query.setParameter("peProgramaEspecialId", obj.getPeProgramaEspecialId());
                query.setParameter("peProgramaEspecialValor", obj.getPeProgramaEspecialValor());
                query.setParameter("peProgramaEspecialCodigo", obj.getPeProgramaEspecialCodigo());
            }
            if (obj.getMaeGuiaManejoIntegralId() != null) {
                query.setParameter("maeGuiaManejoIntegralId", obj.getMaeGuiaManejoIntegralId());
                query.setParameter("maeGuiaManejoIntegralCodigo", obj.getMaeGuiaManejoIntegralCodigo());
                query.setParameter("maeGuiaManejoIntegralValor", obj.getMaeGuiaManejoIntegralValor());
            }
            query.setParameter("cuotaModeradora", obj.getCuotaModeradora());
            query.setParameter("cuotaRecuperacion", obj.getCuotaRecuperacion());
            query.setParameter("copago", obj.getCopago());
            query.setParameter("afiliadoDireccionAlternativa", obj.getDireccionAlternativa());
            query.setParameter("maeModalidadTecnologiaId", obj.getMaeModalidadTecnologiaId());
            query.setParameter("maeModalidadTecnologiaCodigo", obj.getMaeModalidadTecnologiaCodigo());
            query.setParameter("maeModalidadTecnologiaValor", obj.getMaeModalidadTecnologiaValor());
            query.setParameter("maeFinalidadTecnologiaId", obj.getMaeFinalidadTecnologiaId());
            query.setParameter("maeFinalidadTecnologiaCodigo", obj.getMaeFinalidadTecnologiaCodigo());
            query.setParameter("maeFinalidadTecnologiaValor", obj.getMaeFinalidadTecnologiaValor());
            if (obj.getFuenteAnula() != null) {
                query.setParameter("fuenteAnula", obj.getFuenteAnula());
            }
            if(obj.getAuAnexo3CargaAnuladasId() != null){
                query.setParameter("auAnexo3CargaAnuladasId", obj.getAuAnexo3CargaAnuladasId().getId());
            }
            query.setParameter("usuarioCrea", obj.getUsuarioCrea());
            query.setParameter("terminalCrea", obj.getTerminalCrea());
            query.setParameter("fechaHoraCrea", obj.getFechaHoraCrea());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AuAnexo3 eliminar(int id) throws Exception {
        AuAnexo3 obj = null;
        try {
            AuAnexos3 ent = getEntityManager().find(AuAnexos3.class, id);
            if (ent != null) {
                obj = castEntidadNegocioCorto(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            Exception(ELIMINAR, e);
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public List<AuAnexo3> consultarAutorizacion(String tipoDocumento, String numeroDocumento, Date fechaNacimiento) throws java.lang.Exception {
        List<AuAnexo3> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexos3 p "
                    + "WHERE p.asegAfiliadosId.maeTipoDocumentoId =" + tipoDocumento
                    + " p.asegAfiliadosId.numeroIdentificacion ='" + numeroDocumento + "'"
                    + " p.asegAfiliadosId.fechaNacimiento ='" + formatoCorto.format(fechaNacimiento) + "'"
                    + " ORDER BY p.id DESC";

            List<AuAnexos3> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuAnexos3 anexo3 : list) {
                listaResultados.add(castEntidadNegocioCorto(anexo3));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }

    public static AuAnexo3 castEntidadNegocioCorto(AuAnexos3 entidad) {
        AuAnexo3 negocio = new AuAnexo3();
        negocio.setId(entidad.getId());
        if (entidad.getGnEmpresasId() != null) {
            negocio.setGnEmpresaId(new Empresa(entidad.getGnEmpresasId().getId()));
        }
        if (entidad.getAuAnexo3CargasId() != null) {
            negocio.setAuAnexo3CargaId(new AuAnexo3Carga(entidad.getAuAnexo3CargasId().getId()));
        }
        negocio.setNumero(entidad.getNumero());
        negocio.setFechaSolicitud(entidad.getFechaSolicitud());
        negocio.setCntPrestadorSedeId(castPrestadorSede(entidad.getCntPrestadorSedesId()));
        negocio.setAsegAfiliadoId(AfiliadoServicio.castEntidadNegocioLargo(entidad.getAsegAfiliadosId()));
        negocio.setNombreAcompanante(entidad.getNombreAcompanante());
        negocio.setTelefonoAcompanante(entidad.getTelefonoAcompanante());
        negocio.setCelularAcompanente(entidad.getCelularAcompanente());
        negocio.setEstado(entidad.getEstado());
        if (entidad.getEstadoJustificacion() != null) {
            negocio.setEstadoJustificacion(entidad.getEstadoJustificacion());
        }
        negocio.setMaeCausaExternaId(entidad.getMaeCausaExternaId());
        negocio.setMaeCausaExternaCodigo(entidad.getMaeCausaExternaCodigo());
        negocio.setMaeCausaExternaValor(entidad.getMaeCausaExternaValor());
        negocio.setMaeAmbitoAtencionId(entidad.getMaeAmbitoAtencionId());
        negocio.setMaeAmbitoAtencionCodigo(entidad.getMaeAmbitoAtencionCodigo());
        negocio.setMaeAmbitoAtencionValor(entidad.getMaeAmbitoAtencionValor());
        negocio.setPrioridad(entidad.getPrioridad());
        negocio.setCama(entidad.getCama());
        negocio.setJustificacionClinica(entidad.getJustificacionClinica());
        negocio.setCntProfesionaleId(CntProfesionalServicio.castEntidadNegocio(entidad.getCntProfesionalesId()));
        negocio.setNombreProfesional(entidad.getNombreProfesional());
        negocio.setCargoProfesional(entidad.getCargoProfesional());
        negocio.setTelefonoProfesional(entidad.getTelefonoProfesional());
        negocio.setCelularProfesional(entidad.getCelularProfesional());
        negocio.setMaeOrigenAtencionId(entidad.getMaeOrigenAtencionId());
        negocio.setMaeOrigenAtencionCodigo(entidad.getMaeOrigenAtencionCodigo());
        negocio.setMaeOrigenAtencionValor(entidad.getMaeOrigenAtencionValor());
        negocio.setFuenteOrigen(entidad.getFuenteOrigen());
        negocio.setMaServicioSolicitadoId(entidad.getMaServicioSolicitadoId());
        negocio.setMaServicioSolicitadoCodigo(entidad.getMaServicioSolicitadoCodigo());
        negocio.setMaServicioSolicitadoValor(entidad.getMaServicioSolicitadoValor());
        negocio.setPeProgramaEspecialId(entidad.getPeProgramaEspecialId());
        negocio.setPeProgramaEspecialCodigo(entidad.getPeProgramaEspecialCodigo());
        negocio.setPeProgramaEspecialValor(entidad.getPeProgramaEspecialValor());
        negocio.setMaServicioHabilitadoId(entidad.getMaServicioHabilitadoId());
        negocio.setMaServicioHabilitadoCodigo(entidad.getMaServicioHabilitadoCodigo());
        negocio.setMaServicioHabilitadoValor(entidad.getMaServicioHabilitadoValor());
        if (entidad.getMaeTipoServicioId() != null) {
            negocio.setMaeTipoServicioId(entidad.getMaeTipoServicioId());
            negocio.setMaeTipoServicioCodigo(entidad.getMaeTipoServicioCodigo());
            negocio.setMaeTipoServicioValor(entidad.getMaeTipoServicioValor());
        }
        negocio.setMaeGuiaManejoIntegralId(entidad.getMaeGuiaManejoIntegralId());
        negocio.setMaeGuiaManejoIntegralCodigo(entidad.getMaeGuiaManejoIntegralCodigo());
        negocio.setMaeGuiaManejoIntegralValor(entidad.getMaeGuiaManejoIntegralValor());
        negocio.setMaeUbicacionId(entidad.getMaeUbicacionId());
        negocio.setMaeUbicacionCodigo(entidad.getMaeUbicacionCodigo());
        negocio.setMaeUbicacionValor(entidad.getMaeUbicacionValor());
        negocio.setCuotaModeradora(entidad.getCuotaModeradora());
        negocio.setCuotaRecuperacion(entidad.getCuotaRecuperacion());
        negocio.setCopago(entidad.getCopago());
        int cantidadAutorizaciones = 0;
        if (entidad.getAuAnexos4List() != null && !entidad.getAuAnexos4List().isEmpty()) {
            /*for (AuAnexos4 anexo : entidad.getAuAnexos4List()) {
                if (anexo.getAuAnexo4ItemsList() != null && !entidad.getAuAnexo3ItemsList().isEmpty()) {
                    cantidadAutorizaciones += anexo.getAuAnexo4ItemsList().size();
                }
            }*/
            cantidadAutorizaciones = entidad.getAuAnexos4List().size();
        }
        negocio.setCantidadAutorizaciones(cantidadAutorizaciones);
        negocio.setCantidadTecnologias(entidad.getAuAnexo3ItemsList().size());
        //Datos auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        return negocio;
    }

    public static AuAnexo3 castEntidadNegocioLista(AuAnexos3 entidad) {
        AuAnexo3 negocio = new AuAnexo3();
        negocio.setId(entidad.getId());
        negocio.setFuenteOrigen(entidad.getFuenteOrigen());
        AsegAfiliado afiliado = new AsegAfiliado();
        afiliado.setMaeTipoDocumento(entidad.getAsegAfiliadosId().getMaeTipoDocumentoId());
        afiliado.setMaeTipoDocumentoValor(entidad.getAsegAfiliadosId().getMaeTipoDocumentoValor());
        afiliado.setMaeTipoDocumentoCodigo(entidad.getAsegAfiliadosId().getMaeTipoDocumentoCodigo());
        afiliado.setNumeroDocumento(entidad.getAsegAfiliadosId().getNumeroDocumento());
        afiliado.setPrimerNombre(entidad.getAsegAfiliadosId().getPrimerNombre());
        afiliado.setSegundoNombre(entidad.getAsegAfiliadosId().getSegundoNombre());
        afiliado.setPrimerApellido(entidad.getAsegAfiliadosId().getPrimerApellido());
        afiliado.setSegundoApellido(entidad.getAsegAfiliadosId().getSegundoApellido());
        negocio.setAsegAfiliadoId(afiliado);
        if (entidad.getAuAnexo3CargasId() != null) {
            negocio.setAuAnexo3CargaId(new AuAnexo3Carga(entidad.getAuAnexo3CargasId().getId()));
        }
        int cantidadAutorizaciones = 0;
        if (entidad.getAuAnexos4List() != null) {
            cantidadAutorizaciones = entidad.getAuAnexos4List().size();
        }
        negocio.setCantidadAutorizaciones(cantidadAutorizaciones);
        negocio.setCantidadTecnologias(entidad.getAuAnexo3ItemsList().size());
        negocio.setPrioridad(entidad.getPrioridad());
        negocio.setMaeAmbitoAtencionId(entidad.getMaeAmbitoAtencionId());
        negocio.setMaeAmbitoAtencionValor(entidad.getMaeAmbitoAtencionValor());
        negocio.setMaServicioSolicitadoValor(entidad.getMaServicioSolicitadoValor());
        negocio.setMaServicioHabilitadoValor(entidad.getMaServicioHabilitadoValor());
        CntPrestadorSede prestadorSede = new CntPrestadorSede();
        CntPrestador prestador = new CntPrestador();
        prestador.setRazonSocial(entidad.getCntPrestadorSedesId().getCntPrestadoresId().getRazonSocial());
        prestadorSede.setCntPrestador(prestador);
        prestadorSede.setUbicacionId(entidad.getCntPrestadorSedesId().getUbicacionId());
        negocio.setCntPrestadorSedeId(prestadorSede);
        negocio.setEstado(entidad.getEstado());
        if (entidad.getEstadoJustificacion() != null) {
            negocio.setEstadoJustificacion(entidad.getEstadoJustificacion());
        }
        negocio.setAuAnexo3ItemsList(castearListaItemEntidadNegocio(entidad.getAuAnexo3ItemsList()));
        negocio.setTieneRescates(false);
//        if (entidad.getAuAnexo2RescatesList() != null && !entidad.getAuAnexo2RescatesList().isEmpty()) {
        if (entidad.getAuAnexo2RescatesList() != null) {
            for (AuAnexo2Rescates auAnexo2Rescates : entidad.getAuAnexo2RescatesList()) {
                negocio.setTieneRescates(true);
                break;
            }
//            negocio.setAuAnexo2RescateList(castRescates(entidad.getAuAnexo2RescatesList()));
        }
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        
        negocio.setConsecutivo(entidad.getConsecutivo());
        negocio.setVersion(entidad.getVersion());
        negocio.setMaeModalidadTecnologiaId(entidad.getMaeModalidadTecnologiaId());
        negocio.setMaeModalidadTecnologiaCodigo(entidad.getMaeModalidadTecnologiaCodigo());
        negocio.setMaeModalidadTecnologiaValor(entidad.getMaeModalidadTecnologiaValor());
        negocio.setMaeFinalidadTecnologiaId(entidad.getMaeFinalidadTecnologiaId());
        negocio.setMaeFinalidadTecnologiaCodigo(entidad.getMaeFinalidadTecnologiaCodigo());
        negocio.setMaeFinalidadTecnologiaValor(entidad.getMaeFinalidadTecnologiaValor());
        negocio.setPeProgramaEspecialId(entidad.getPeProgramaEspecialId());
        negocio.setPeProgramaEspecialCodigo(entidad.getPeProgramaEspecialCodigo());
        negocio.setPeProgramaEspecialValor(entidad.getPeProgramaEspecialValor());
        return negocio;
    }

    public static AuAnexos3 castNegocioEntidadCorto(AuAnexo3 negocio) {
        AuAnexos3 entidad = new AuAnexos3();
        entidad.setId(negocio.getId());
        if (negocio.getGnEmpresaId() != null) {
            entidad.setGnEmpresasId(new GnEmpresas(negocio.getGnEmpresaId().getId()));
        }
        if (negocio.getAuAnexo3CargaId() != null) {
            entidad.setAuAnexo3CargasId(new AuAnexo3Cargas(negocio.getAuAnexo3CargaId().getId()));
        }
        entidad.setNumero(negocio.getNumero());
        entidad.setFechaSolicitud(negocio.getFechaSolicitud());
        entidad.setCntPrestadorSedesId(new CntPrestadorSedes(negocio.getCntPrestadorSedeId().getId()));
        entidad.setAsegAfiliadosId(new AsegAfiliados(negocio.getAsegAfiliadoId().getId()));
        entidad.setNombreAcompanante(negocio.getNombreAcompanante());
        entidad.setTelefonoAcompanante(negocio.getTelefonoAcompanante());
        entidad.setCelularAcompanente(negocio.getCelularAcompanente());
        entidad.setEstado(negocio.getEstado());
        if (negocio.getMaeEstadoMotivoId() != null) {
            entidad.setMaeEstadoMotivoId(negocio.getMaeEstadoMotivoId());
            entidad.setMaeEstadoMotivoCodigo(negocio.getMaeEstadoMotivoCodigo());
            entidad.setMaeEstadoMotivoValor(negocio.getMaeEstadoMotivoValor());
        }
        if (negocio.getEstadoJustificacion() != null) {
            entidad.setEstadoJustificacion(negocio.getEstadoJustificacion());
        }
        entidad.setMaeCausaExternaId(negocio.getMaeCausaExternaId());
        entidad.setMaeCausaExternaCodigo(negocio.getMaeCausaExternaCodigo());
        entidad.setMaeCausaExternaValor(negocio.getMaeCausaExternaValor());
        entidad.setMaeAmbitoAtencionId(negocio.getMaeAmbitoAtencionId());
        entidad.setMaeAmbitoAtencionCodigo(negocio.getMaeAmbitoAtencionCodigo());
        entidad.setMaeAmbitoAtencionValor(negocio.getMaeAmbitoAtencionValor());
        entidad.setPrioridad(negocio.getPrioridad());
        entidad.setCama(negocio.getCama());
        entidad.setJustificacionClinica(negocio.getJustificacionClinica());
        if (negocio.getCntProfesionaleId().getId() != null) {
            entidad.setCntProfesionalesId(new CntProfesionales(negocio.getCntProfesionaleId().getId()));
            entidad.setNombreProfesional(negocio.getNombreProfesional());
            entidad.setCargoProfesional(negocio.getCargoProfesional());
            entidad.setTelefonoProfesional(negocio.getTelefonoProfesional());
            entidad.setCelularProfesional(negocio.getCelularProfesional());
        }        
        entidad.setMaeOrigenAtencionId(negocio.getMaeOrigenAtencionId());
        entidad.setMaeOrigenAtencionCodigo(negocio.getMaeOrigenAtencionCodigo());
        entidad.setMaeOrigenAtencionValor(negocio.getMaeOrigenAtencionValor());
        entidad.setFuenteOrigen(negocio.getFuenteOrigen());
        entidad.setMaServicioSolicitadoId(negocio.getMaServicioSolicitadoId());
        entidad.setMaServicioSolicitadoCodigo(negocio.getMaServicioSolicitadoCodigo());
        entidad.setMaServicioSolicitadoValor(negocio.getMaServicioSolicitadoValor());
        if (negocio.getPeProgramaId() != null) {
            entidad.setPeProgramasId(new PeProgramas(negocio.getPeProgramaId().getId()));
        }
        entidad.setPeProgramaEspecialId(negocio.getPeProgramaEspecialId());
        entidad.setPeProgramaEspecialCodigo(negocio.getPeProgramaEspecialCodigo());
        entidad.setPeProgramaEspecialValor(negocio.getPeProgramaEspecialValor());
        entidad.setMaServicioHabilitadoId(negocio.getMaServicioHabilitadoId());
        entidad.setMaServicioHabilitadoCodigo(negocio.getMaServicioHabilitadoCodigo());
        entidad.setMaServicioHabilitadoValor(negocio.getMaServicioHabilitadoValor());
        if (negocio.getMaeTipoServicioId() != null) {
            entidad.setMaeTipoServicioId(negocio.getMaeTipoServicioId());
            entidad.setMaeTipoServicioCodigo(negocio.getMaeTipoServicioCodigo());
            entidad.setMaeTipoServicioValor(negocio.getMaeTipoServicioValor());
        }
        entidad.setMaeGuiaManejoIntegralId(negocio.getMaeGuiaManejoIntegralId());
        entidad.setMaeGuiaManejoIntegralCodigo(negocio.getMaeGuiaManejoIntegralCodigo());
        entidad.setMaeGuiaManejoIntegralValor(negocio.getMaeGuiaManejoIntegralValor());
        entidad.setMaeUbicacionId(negocio.getMaeUbicacionId());
        entidad.setMaeUbicacionCodigo(negocio.getMaeUbicacionCodigo());
        entidad.setMaeUbicacionValor(negocio.getMaeUbicacionValor());
        entidad.setCuotaModeradora(negocio.getCuotaModeradora());
        entidad.setCuotaRecuperacion(negocio.getCuotaRecuperacion());
        entidad.setCopago(negocio.getCopago());
        //Datos auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        //nuevos valores resolucion 2335
        entidad.setVersion(negocio.getVersion());
        entidad.setConsecutivo(negocio.getConsecutivo());
        entidad.setAfiliadoDireccionAlternativa(negocio.getDireccionAlternativa());
        entidad.setMaeModalidadTecnologiaId(negocio.getMaeModalidadTecnologiaId());
        entidad.setMaeModalidadTecnologiaCodigo(negocio.getMaeModalidadTecnologiaCodigo());
        entidad.setMaeModalidadTecnologiaValor(negocio.getMaeModalidadTecnologiaValor());
        entidad.setMaeFinalidadTecnologiaId(negocio.getMaeFinalidadTecnologiaId());
        entidad.setMaeFinalidadTecnologiaCodigo(negocio.getMaeFinalidadTecnologiaCodigo());
        entidad.setMaeFinalidadTecnologiaValor(negocio.getMaeFinalidadTecnologiaValor());
        return entidad;
    }

    @Override
    public List<PePrograma> consultarProgramasAfiliado(int idAfiliado) throws Exception {
        List<PePrograma> programas = new ArrayList();
        try {
            String strQuery = "FROM PeAfiliadosProgramas p "
                    + "WHERE p.asegAfiliadosId.id =" + idAfiliado
                    + " AND p.activo = 1 AND p.peProgramasId.activo = 1"
                    + " ORDER BY p.id DESC";

            List<PeAfiliadosProgramas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (PeAfiliadosProgramas afiliadoPrograma : list) {
                programas.add(castProgramaEspecial(afiliadoPrograma.getPeProgramasId()));
            }
        } catch (NoResultException e) {
            programas = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return programas;
    }

    private static List<AuAnexo2Rescate> castRescates(List<AuAnexo2Rescates> rescates) {
        List<AuAnexo2Rescate> rescatesObj = new ArrayList();
        rescates.stream().map(r -> {
            AuAnexo2Rescate rescate = new AuAnexo2Rescate();
            rescate.setId(r.getId());
            rescate.setEstado(r.getEstado());
            return rescate;
        }).forEachOrdered(rescate -> {
            rescatesObj.add(rescate);
        });

        return rescatesObj;
    }

    private PePrograma castProgramaEspecial(PeProgramas programas) {
        PePrograma programa = new PePrograma();
        programa.setId(programas.getId());
        programa.setCodigoPrograma(programas.getCodigoPrograma());
        programa.setDescripcionPrograma(programas.getDescripcionPrograma());
        programa.setMaeTipoProgramaId(programas.getMaeTipoProgramaId());
        programa.setExoneradoCopago(programas.getExoneradoCopago());
        programa.setExoneracionObligatoria(programas.getExoneracionObligatoria());
        programa.setActivo(programas.getActivo());
        return programa;
    }

    private static CntPrestadorSede castPrestadorSede(CntPrestadorSedes prestadorSede) {
        CntPrestadorSede prestador = new CntPrestadorSede();
        prestador.setId(prestadorSede.getId());
        prestador.setCodigoHabilitacionSede(prestadorSede.getCodigoHabilitacion());
        prestador.setClasePrestador(prestadorSede.getMaeClasePrestadorId());
        prestador.setUbicacionId(prestadorSede.getUbicacionId());
        prestador.setNombreSede(prestadorSede.getNombre());
        prestador.setDireccion(prestadorSede.getDireccion());
        prestador.setTelefonoCitas(prestadorSede.getTelefonoCitas());
        prestador.setCntPrestador(castearPrestador(prestadorSede.getCntPrestadoresId()));
        return prestador;
    }

    private static CntPrestador castearPrestador(CntPrestadores prestadorBD) {
        CntPrestador prestador = new CntPrestador();
        prestador.setId(prestadorBD.getId());
        prestador.setMaeTipoDocumentoId(prestadorBD.getMaeTipoDocumentoId());
        prestador.setMaeTipoDocumentoCodigo(prestadorBD.getMaeTipoDocumentoCodigo());
        prestador.setMaeTipoDocumentoValor(prestadorBD.getMaeTipoDocumentoValor());
        prestador.setNumeroDocumento(prestadorBD.getNumeroDocumento());
        prestador.setRazonSocial(prestadorBD.getRazonSocial());
        prestador.setCodigoMinSalud(prestadorBD.getCodigoMinSalud());
        return prestador;
    }

    @Override
    public int consultarCantidadPrestadorSedes(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM CntPrestadorSedes p "
                    + "WHERE p.id > 0";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            cantidad = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            cantidad = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cantidad;
    }

    @Override
    public CntProfesionalPrestador buscarEspeciliadad(int prestadorId, int profesionalId) throws Exception {
        CntProfesionalPrestador resultado = null;
        try {
            String strQuery = "FROM CntProfesionalPrestadores p "
                    + " WHERE p.cntPrestadoresId.id = :prestador_id "
                    + " AND p.cntProfesionalesId.id = :profesional_id";
            List<CntProfesionalPrestadores> lista = getEntityManager().createQuery(strQuery)
                    .setParameter("prestador_id", prestadorId)
                    .setParameter("profesional_id", profesionalId)
                    .getResultList();
            if (lista != null && !lista.isEmpty()) {
                resultado = castEntidadNegocioProfesionalPrestador(lista.get(0));
            }
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return resultado;
    }

    private CntProfesionalPrestador castEntidadNegocioProfesionalPrestador(CntProfesionalPrestadores entidad) {
        CntProfesionalPrestador negocio = new CntProfesionalPrestador();
        negocio.setId(entidad.getId());
        negocio.setCntPrestador(new CntPrestador(entidad.getCntPrestadoresId().getId()));
        negocio.setCntProfesionalesId(new CntProfesional(entidad.getCntProfesionalesId().getId()));
        negocio.setActivo(entidad.getActivo());
        negocio.setMaEspecialidadId(entidad.getMaEspecialidadId());
        negocio.setMaEspecialidadCodigo(entidad.getMaEspecialidadCodigo());
        negocio.setMaEspecialidadValor(entidad.getMaEspecialidadValor());
        return negocio;
    }

    private CntProfesionalPrestadores castNegocioEntidadProfesionalPrestador(CntProfesionalPrestador negocio) {
        CntProfesionalPrestadores entidad = new CntProfesionalPrestadores();
        entidad.setId(negocio.getId());
        entidad.setCntPrestadoresId(new CntPrestadores(negocio.getCntPrestador().getId()));
        entidad.setCntProfesionalesId(new CntProfesionales(negocio.getCntProfesionalesId().getId()));
        entidad.setActivo(negocio.isActivo());
        entidad.setMaEspecialidadId(negocio.getMaEspecialidadId());
        entidad.setMaEspecialidadCodigo(negocio.getMaEspecialidadCodigo());
        entidad.setMaEspecialidadValor(negocio.getMaEspecialidadValor());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        return entidad;
    }

    public static AuAnexo3 castEntidadNegocioLargo(AuAnexos3 entidad) {
        AuAnexo3 negocio  = new AuAnexo3();
        negocio.setId(entidad.getId());
        if (entidad.getGnEmpresasId() != null) {
            negocio.setGnEmpresaId(new Empresa(entidad.getGnEmpresasId().getId()));
        }
        //Castear el carga id falta desarrollar
        if (entidad.getAuAnexo3CargasId() != null) {
            negocio.setAuAnexo3CargaId(new AuAnexo3Carga(entidad.getAuAnexo3CargasId().getId()));
        }
        if (entidad.getMaeEstadoMotivoId() != null) {
            negocio.setMaeEstadoMotivoId(entidad.getMaeEstadoMotivoId());
            negocio.setMaeEstadoMotivoCodigo(entidad.getMaeEstadoMotivoCodigo());
            negocio.setMaeEstadoMotivoValor(entidad.getMaeEstadoMotivoValor());
            if (entidad.getEstadoJustificacion() != null) {
                negocio.setEstadoJustificacion(entidad.getEstadoJustificacion());
            }
        }
        negocio.setNumero(entidad.getNumero());
        negocio.setFechaSolicitud(entidad.getFechaSolicitud());
        negocio.setCntPrestadorSedeId(castPrestadorSede(entidad.getCntPrestadorSedesId()));
        negocio.setAsegAfiliadoId(AfiliadoServicio.castEntidadNegocioLargo(entidad.getAsegAfiliadosId()));
        negocio.setNombreAcompanante(entidad.getNombreAcompanante());
        negocio.setTelefonoAcompanante(entidad.getTelefonoAcompanante());
        negocio.setCelularAcompanente(entidad.getCelularAcompanente());
        negocio.setEstado(entidad.getEstado());
        negocio.setMaeCausaExternaId(entidad.getMaeCausaExternaId());
        negocio.setMaeCausaExternaCodigo(entidad.getMaeCausaExternaCodigo());
        negocio.setMaeCausaExternaValor(entidad.getMaeCausaExternaValor());
        negocio.setMaeAmbitoAtencionId(entidad.getMaeAmbitoAtencionId());
        negocio.setMaeAmbitoAtencionCodigo(entidad.getMaeAmbitoAtencionCodigo());
        negocio.setMaeAmbitoAtencionValor(entidad.getMaeAmbitoAtencionValor());
        negocio.setPrioridad(entidad.getPrioridad());
        negocio.setCama(entidad.getCama());
        negocio.setJustificacionClinica(entidad.getJustificacionClinica());
        if (entidad.getCntProfesionalesId() != null) {
            negocio.setCntProfesionaleId(CntProfesionalServicio.castEntidadNegocio(entidad.getCntProfesionalesId()));
            negocio.setNombreProfesional(entidad.getNombreProfesional());
            negocio.setCargoProfesional(entidad.getCargoProfesional());
            negocio.setTelefonoProfesional(entidad.getTelefonoProfesional());
            negocio.setCelularProfesional(entidad.getCelularProfesional());
        }      
        negocio.setMaeOrigenAtencionId(entidad.getMaeOrigenAtencionId());
        negocio.setMaeOrigenAtencionCodigo(entidad.getMaeOrigenAtencionCodigo());
        negocio.setMaeOrigenAtencionValor(entidad.getMaeOrigenAtencionValor());
        negocio.setFuenteOrigen(entidad.getFuenteOrigen());
        negocio.setMaServicioSolicitadoId(entidad.getMaServicioSolicitadoId());
        negocio.setMaServicioSolicitadoCodigo(entidad.getMaServicioSolicitadoCodigo());
        negocio.setMaServicioSolicitadoValor(entidad.getMaServicioSolicitadoValor());
        negocio.setPeProgramaEspecialId(entidad.getPeProgramaEspecialId());
        negocio.setPeProgramaEspecialCodigo(entidad.getPeProgramaEspecialCodigo());
        negocio.setPeProgramaEspecialValor(entidad.getPeProgramaEspecialValor());
        negocio.setMaServicioHabilitadoId(entidad.getMaServicioHabilitadoId());
        negocio.setMaServicioHabilitadoCodigo(entidad.getMaServicioHabilitadoCodigo());
        negocio.setMaServicioHabilitadoValor(entidad.getMaServicioHabilitadoValor());
        negocio.setMaeGuiaManejoIntegralId(entidad.getMaeGuiaManejoIntegralId());
        negocio.setMaeGuiaManejoIntegralCodigo(entidad.getMaeGuiaManejoIntegralCodigo());
        negocio.setMaeGuiaManejoIntegralValor(entidad.getMaeGuiaManejoIntegralValor());
        negocio.setCuotaModeradora(entidad.getCuotaModeradora());
        negocio.setCuotaRecuperacion(entidad.getCuotaRecuperacion());
        negocio.setCopago(entidad.getCopago());
        if (entidad.getAuAnexo3DiagnosticosList() != null) {
            negocio.setAuAnexo3DiagnosticosList(AuAnexo3DiagnosticoServicio.casteoListaEntidadNegocio(entidad.getAuAnexo3DiagnosticosList()));
        }
        if (entidad.getAuAnexo3ItemsList() != null) {
            negocio.setAuAnexo3ItemsList(AuAnexo3ItemServicio.casteoListaEntidadNegocio(entidad.getAuAnexo3ItemsList()));
        }
        if (entidad.getAuAnexo3AfiliadosList() != null) {
            negocio.setAuAnexo3AfiliadosList(AuAnexo3AfiliadoServicio.castListaEntidadNegocio(entidad.getAuAnexo3AfiliadosList()));
        }
        negocio.setMaeUbicacionId(entidad.getMaeUbicacionId());
        negocio.setMaeUbicacionCodigo(entidad.getMaeUbicacionCodigo());
        negocio.setMaeUbicacionValor(entidad.getMaeUbicacionValor());
        //Falta casteo Lista adjutos
        if (entidad.getAuSolicitudAdjuntosList() != null) {
            negocio.setAuSolicitudAdjuntosList(AuSolicitudAdjuntoServicio.casteoListaEntidadNegocio(entidad.getAuSolicitudAdjuntosList()));
        }
        if (entidad.getPeProgramasId() != null) {
            negocio.setPeProgramaId(new PePrograma(entidad.getPeProgramasId().getId()));
        }
        if (entidad.getMaeTipoServicioId() != null) {
            negocio.setMaeTipoServicioId(entidad.getMaeTipoServicioId());
            negocio.setMaeTipoServicioCodigo(entidad.getMaeTipoServicioCodigo());
            negocio.setMaeTipoServicioValor(entidad.getMaeTipoServicioValor());
        }
        //Falta casteo lista tutela
        if (entidad.getAuAnexo3TutelasList() != null) {
            negocio.setAuAnexo3TutelasList(AuAnexo3TutelaServicio.casteoListaEntidadNegocio(entidad.getAuAnexo3TutelasList()));
        }
        negocio.setEstadoProcesoActual(entidad.getEstadoProcesoActual());
        //Datos auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());

        //nuevos campos resolucion 2335
        negocio.setVersion(entidad.getVersion());
        negocio.setConsecutivo(entidad.getConsecutivo());
        negocio.setDireccionAlternativa(entidad.getAfiliadoDireccionAlternativa());
        negocio.setMaeModalidadTecnologiaId(entidad.getMaeModalidadTecnologiaId());
        negocio.setMaeModalidadTecnologiaCodigo(entidad.getMaeModalidadTecnologiaCodigo());
        negocio.setMaeModalidadTecnologiaValor(entidad.getMaeModalidadTecnologiaValor());
        negocio.setMaeFinalidadTecnologiaId(entidad.getMaeFinalidadTecnologiaId());
        negocio.setMaeFinalidadTecnologiaCodigo(entidad.getMaeFinalidadTecnologiaCodigo());
        negocio.setMaeFinalidadTecnologiaValor(entidad.getMaeFinalidadTecnologiaValor());
        
        return negocio;
    }

    @Override
    public int consultarCantidadListaContratos(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            Date fechaActual = new Date();
            String strQuery = "SELECT COUNT(DISTINCT c ) FROM CntContratoDetalles c "
                    + "WHERE (c.cntContratoSedesId.maeModalidadContratoId = 9118 OR c.cntContratoSedesId.maeModalidadContratoId = 9597) AND c.fechaHoraFin >= '" + formatoCorto.format(fechaActual) + "' AND c.activo = 1 AND c.valorContratado > 0 "
                    + " AND c.cntContratosId.maeEstadoContratoId = 9598 AND c.cntContratosId.fechaFin >= '" + formatoCorto.format(fechaActual) + "' ";
            int tipoTecnologia = (int) (paramConsulta.getParametroConsulta1() == null ? 0 : paramConsulta.getParametroConsulta1());
            if (tipoTecnologia != 0) {
                strQuery += "AND c.tipoTecnologia = " + (tipoTecnologia == AuAnexo3Item.TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO ? AuAnexo3Item.TIPO_TECNOLOGIA_CUM : tipoTecnologia) + " ";
            }
            if (paramConsulta.getParametroConsulta2() != null && tipoTecnologia != AuAnexo3Item.TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO) {
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
                strQuery += ") ";
            } else if (paramConsulta.getParametroConsulta2() != null && tipoTecnologia == AuAnexo3Item.TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO) {
                String[] tecnologias = ((String) paramConsulta.getParametroConsulta2()).split(",");
                int contador = 1;
                strQuery += "AND EXISTS(SELECT m FROM MaMedicamentos m WHERE m.id=c.maTecnologiaId AND m.maAgrupadoresMedicamentoId.id IN(";
                for (String tec : tecnologias) {
                    strQuery += "" + tec;
                    if (contador < tecnologias.length) {
                        strQuery += " , ";
                    }
                    contador++;
                }
                strQuery += ")) ";
            }
            /*if (paramConsulta.getParametroConsulta3() != null ) {
                strQuery += "AND c.maeAmbitoId = " + paramConsulta.getParametroConsulta3() + " ";
            }*/
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
                            strQuery += "AND c.maTecnologiaCodigo LIKE '%" + (String) e.getValue() + "%' ";
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
                        case "cntContratoSede.cntPrestadorSede.nombreSede":
                            strQuery += "AND c.cntContratoSedesId.cntPrestadorSedesId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeAmbitoValor":
                            strQuery += "AND c.maeAmbitoValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maServicioHabilitacionCodigo":
                            strQuery += "AND c.maServicioHabilitacionCodigo = '" + e.getValue() + "' ";
                            break;
                        case "maServicioHabilitacionValor":
                            strQuery += "AND c.maServicioHabilitacionValor LIKE '%" + e.getValue() + "%' ";
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
    public List<CntContratoDetalle> consultarListaContratos(ParamConsulta paramConsulta) throws Exception {
        List<CntContratoDetalle> listResult = new ArrayList();
        try {
            Date fechaActual = new Date();
            String strQuery = "SELECT DISTINCT c FROM CntContratoDetalles c "
                    + "WHERE (c.cntContratoSedesId.maeModalidadContratoId = 9118 OR c.cntContratoSedesId.maeModalidadContratoId = 9597) AND c.fechaHoraFin >= '" + formatoCorto.format(fechaActual) + "' AND c.activo = 1 AND c.valorContratado > 0 "
                    + " AND c.cntContratosId.maeEstadoContratoId = 9598 AND c.cntContratosId.fechaFin >= '" + formatoCorto.format(fechaActual) + "' ";
            int tipoTecnologia = (int) (paramConsulta.getParametroConsulta1() == null ? 0 : paramConsulta.getParametroConsulta1());
            if (tipoTecnologia != 0) {
                strQuery += "AND c.tipoTecnologia = " + (tipoTecnologia == AuAnexo3Item.TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO ? AuAnexo3Item.TIPO_TECNOLOGIA_CUM : tipoTecnologia) + " ";
            }
            if (paramConsulta.getParametroConsulta2() != null && tipoTecnologia != AuAnexo3Item.TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO) {
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
                strQuery += ") ";
            } else if (paramConsulta.getParametroConsulta2() != null && tipoTecnologia == AuAnexo3Item.TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO) {
                String[] tecnologias = ((String) paramConsulta.getParametroConsulta2()).split(",");
                int contador = 1;
                strQuery += "AND EXISTS(SELECT m FROM MaMedicamentos m WHERE m.id=c.maTecnologiaId AND m.maAgrupadoresMedicamentoId.id IN(";
                for (String tec : tecnologias) {
                    strQuery += "" + tec;
                    if (contador < tecnologias.length) {
                        strQuery += " , ";
                    }
                    contador++;
                }
                strQuery += ")) ";
            }
            /*if (paramConsulta.getParametroConsulta3() != null ) {
                strQuery += "AND c.maeAmbitoId = " + paramConsulta.getParametroConsulta3() + " ";
            }*/
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntContrato.contrato":
                            strQuery += "AND c.cntContratosId.contrato = '" + (Integer) e.getValue() + "' ";
                            break;
                        case "tipoTecnologia":
                            strQuery += "AND c.AND c.tipoTecnologia = " + (String) e.getValue() + " ";
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
                        case "cntContratoSede.cntPrestadorSede.nombreSede":
                            strQuery += "AND c.cntContratoSedesId.cntPrestadorSedesId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeAmbitoValor":
                            strQuery += "AND c.maeAmbitoValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maServicioHabilitacionCodigo":
                            strQuery += "AND c.maServicioHabilitacionCodigo = '" + e.getValue() + "' ";
                            break;
                        case "maServicioHabilitacionValor":
                            strQuery += "AND c.maServicioHabilitacionValor LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            //strQuery += "GROUP BY c.cntContratoSedesId.cntPrestadorSedesId HAVING COUNT(c.cntContratoSedesId.cntPrestadorSedesId) = 1";
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
        neg.setFechaHoraFin(per.getFechaHoraFin());
        neg.setPreautorizacion(per.getPreautorizacion());
        //objetos
        if (per.getCntContratoSedesId() != null) {
            neg.setCntContratoSede(new CntContratoSede(per.getCntContratoSedesId().getId()));
        }
        if (per.getCntPrestadorSedesInterdependenciaId() != null) {
            neg.setCntPrestadorSedesInterdependencia(new CntPrestadorSede(per.getCntPrestadorSedesInterdependenciaId().getId()));
        }
        if (per.getCntContratosId() != null) {
            CntContrato contrato = new CntContrato();
            contrato.setId(per.getCntContratosId().getId());
            contrato.setContrato(per.getCntContratosId().getContrato());
            contrato.setEjecucionContratoAutorizado(per.getCntContratosId().getEjecucionContratoAutorizado());
            contrato.setEjecucionContratoPrestado(per.getCntContratosId().getEjecucionContratoPrestado());
            contrato.setEjecucionTotalContrato(per.getCntContratosId().getEjecucionTotalContrato());
            neg.setCntContrato(contrato);
        }
        //auditoria
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    @Override
    public CntContratoDetalle consultarContratoDetalle(int idIps, int idTecnologia, int tipoTecnologia) throws Exception {
        CntContratoDetalle contrato = null;
        try {
            Date fechaActual = new Date();
            String strQuery = "SELECT DISTINCT c FROM CntContratoDetalles c "
                    + "WHERE (c.cntContratoSedesId.maeModalidadContratoId = 9118 OR c.cntContratoSedesId.maeModalidadContratoId = 9597) "
                    + "AND c.cntContratosId.maeEstadoContratoId = 9598 AND c.cntContratosId.fechaFin >= '" + formatoCorto.format(fechaActual) + "' "
                    + "AND c.activo = 1 "
                    + "AND c.fechaHoraFin >= '" + formatoCorto.format(fechaActual) + "' "
                    + "AND c.cntContratoSedesId.cntPrestadorSedesId.id = " + idIps + " "
                    + "AND c.tipoTecnologia = " + (tipoTecnologia == AuAnexo3Item.TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO ? AuAnexo3Item.TIPO_TECNOLOGIA_CUM : tipoTecnologia) + " "
                    + "AND c.valorContratado > 0 ";

            if (tipoTecnologia == AuAnexo3Item.TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO) {
                strQuery += "AND EXISTS(SELECT m FROM MaMedicamentos m WHERE m.id=c.maTecnologiaId AND m.maAgrupadoresMedicamentoId.id = " + idTecnologia + ") ";
            } else {
                strQuery += "AND c.maTecnologiaId = " + idTecnologia + " ";
            }
            strQuery += "ORDER BY c.valorContratado ASC";
            List<CntContratoDetalles> lista = getEntityManager().createQuery(strQuery).getResultList();
            if (lista != null && !lista.isEmpty()) {
                contrato = castEntidadNegocio(lista.get(0));
            }
        } catch (NoResultException e) {
            contrato = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return contrato;
    }

    @Override
    public void actualizarEstado(AuAnexo3 obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexos3 a SET ";
            strQuery += "a.estado = :estado ,";
            if (obj.getMaeEstadoMotivoId() != null) {
                strQuery += "a.maeEstadoMotivoId = :maeEstadoMotivoId ,";
                strQuery += "a.maeEstadoMotivoCodigo = :maeEstadoMotivoCodigo ,";
                strQuery += "a.maeEstadoMotivoValor = :maeEstadoMotivoValor ,";
                strQuery += "a.estadoJustificacion = :estadoJustificacion,";
            }
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("estado", obj.getEstado());
            if (obj.getMaeEstadoMotivoId() != null) {
                query.setParameter("maeEstadoMotivoId", obj.getMaeEstadoMotivoId());
                query.setParameter("maeEstadoMotivoCodigo", obj.getMaeEstadoMotivoCodigo());
                query.setParameter("maeEstadoMotivoValor", obj.getMaeEstadoMotivoValor());
                query.setParameter("estadoJustificacion", obj.getEstadoJustificacion());
            }
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarEstadoProcesoActual(AuAnexo3 obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexos3 a SET ";
            strQuery += "a.estadoProcesoActual = :estadoProcesoActual ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("estadoProcesoActual", obj.getEstadoProcesoActual());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public int consultarProcesoActual(int idSolicitud) throws Exception {
        int estadoProcesoActual = 0;
        try {
            javax.persistence.Query query = getEntityManager().createQuery("SELECT a.estadoProcesoActual FROM AuAnexos3 a WHERE a.id=:id");
            query.setParameter("id", idSolicitud);
            estadoProcesoActual = (int) query.getSingleResult();
        } catch (NoResultException e) {
            Exception(CONSULTAR, e);
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return estadoProcesoActual;
    }
    
    @Override
    public int consultarCantidadListaAfiliado(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(p) FROM AuAnexos3 p ";
            String strQuery = "";

            if (paramConsulta.getEmpresaId() != null) {
                strTitulo += "INNER JOIN p.gnEmpresasId e ON e.id = p.gnEmpresasId.id ";
                strQuery += "WHERE e.id = " + paramConsulta.getEmpresaId() + " ";
            } else {
                strQuery += "WHERE p.id > 0 ";
            }
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND p.asegAfiliadosId.id = " + paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "fuenteOrigen":
                            strQuery += "AND p.fuenteOrigen = " + e.getValue() + " ";
                            break;
                        case "cntPrestadorSedeNombre":
                            strQuery += "AND p.cntPrestadorSedesId.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "auAnexo3ItemsList":
                            strTitulo = "SELECT COUNT(DISTINCT p) FROM AuAnexos3 p "
                                    + "LEFT JOIN AuAnexo3Items ai ON p.id = ai.auAnexos3Id.id ";
                            strQuery += "AND ai.maTecnologiaCodigo = '" + e.getValue() + "' ";
                            break;
                        case "estado":
                            strQuery += " AND p.estado = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery = strTitulo + strQuery;
            
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
    public List<AuAnexo3> consultarListaAfiliado(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo3> listaResultados = new ArrayList();
        try {
            String strTitulo = "SELECT p FROM AuAnexos3 p ";
            String strQuery = "";
            if (paramConsulta.getEmpresaId() != null) {
                strTitulo += "INNER JOIN p.gnEmpresasId e ON e.id = p.gnEmpresasId.id ";
                strQuery += "WHERE e.id = " + paramConsulta.getEmpresaId() + " ";
            } else {
                strQuery += "WHERE p.id > 0 ";
            }
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND p.asegAfiliadosId.id = " + paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "fuenteOrigen":
                            strQuery += "AND p.fuenteOrigen = " + e.getValue() + " ";
                            break;
                        case "cntPrestadorSedeNombre":
                            strQuery += "AND p.cntPrestadorSedesId.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "auAnexo3ItemsList":
                            strTitulo = "SELECT DISTINCT p FROM AuAnexos3 p "
                                    + "LEFT JOIN AuAnexo3Items ai ON p.id = ai.auAnexos3Id.id ";
                            strQuery += "AND ai.maTecnologiaCodigo = '" + e.getValue() + "' ";
                            break;
                        case "estado":
                            strQuery += " AND p.estado = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery = strTitulo + strQuery;
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<AuAnexos3> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexos3 anexo3 : list) {
                listaResultados.add(castEntidadNegocioLista(anexo3));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }
    
    @Override
    public void actualizarConsecutivo(AuAnexo3 obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexos3 a SET a.consecutivo = :consecutivo, ";           
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("consecutivo", obj.getConsecutivo());            
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

}
