/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoHistorico;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliadoHistoricos;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.aseguramiento.ConsultarAfiliadoRemoto;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import javax.persistence.Query;
import javax.persistence.TemporalType;


/**
 *
 * @author jperez
 */
@Stateless
@Remote(ConsultarAfiliadoRemoto.class)
public class ConsultarAfiliadoServicio extends GenericoServicio implements ConsultarAfiliadoRemoto {

    @Override
    public int consultarCantidadListaHistorico(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        SimpleDateFormat std = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String strQuery = "SELECT COUNT(p) FROM AsegAfiliados p "
                    + " JOIN p.asegAfiliadoHistoricosList hist WHERE p.id = hist.asegAfiliadosId.id "
                    + " AND hist.fechaHoraCrea  <= '" + std.format( (Date) paramConsulta.getParametroConsulta9() ) + "' ";
             if (paramConsulta.getParametroConsulta1() != null) {
               strQuery += " AND p.maeTipoDocumentoId = " + paramConsulta.getParametroConsulta1() + " ";
            }
            
            if (paramConsulta.getParametroConsulta2() != null) {
               strQuery += " AND p.numeroDocumento = '" + (String) paramConsulta.getParametroConsulta2() + "' ";
            }
            
            if (paramConsulta.getParametroConsulta3() != null) {
               strQuery += " AND ( p.primerNombre = '" + (String) paramConsulta.getParametroConsulta3() + "') ";
            }
             
            if (paramConsulta.getParametroConsulta4() != null) {
                strQuery += " AND ( p.segundoNombre = '" + (String) paramConsulta.getParametroConsulta4() + "' ) ";
            }
            
            if (paramConsulta.getParametroConsulta5() != null) {
               strQuery += " AND ( p.primerApellido = '" + (String) paramConsulta.getParametroConsulta5() + "' ) ";
            }
            
            if (paramConsulta.getParametroConsulta6() != null) {
               strQuery += " AND ( p.segundoApellido = '" + (String) paramConsulta.getParametroConsulta6() + "' ) ";
            }
            
            if (paramConsulta.getParametroConsulta7() != null) {
                strQuery += "AND p.fechaNacimiento = :fhNacimiento  ";            
            }
            
            if (paramConsulta.getParametroConsulta8() != null) {
                strQuery += " AND p.idAfiliado = '" + (String) paramConsulta.getParametroConsulta8() + "' ";
            }
                
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeEstadoAfiliacion":
                            strQuery += "AND p.maeEstadoAfiliacionId = " + e.getValue() + " ";
                            break;
                        case "regimen":
                            strQuery += "AND p.regimen LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "idAfiliado":
                            strQuery += "AND p.idAfiliado LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "serialBdua":
                            strQuery += "AND p.serialBdua = " + (String) e.getValue() + " ";
                            break;
                        case "maeTipoDocumento":
                            strQuery += "AND p.maeTipoDocumentoId = " + (String) e.getValue() + " ";
                            break;
                        case "documento":
                        case "numeroDocumento":
                            strQuery += "AND p.numeroDocumento = '" + (String) e.getValue() + "' ";
                            break;
                        case "primerNombre":
                            strQuery += "AND p.primerNombre = '" + (String) e.getValue() + "' ";
                            break;
                        case "segundoNombre":
                        case "seguindoNombre":
                            strQuery += "AND p.segundoNombre = '" + (String) e.getValue() + "' ";
                            break;
                        case "primerApellido":
                            strQuery += "AND p.primerApellido = '" + (String) e.getValue() + "' ";
                            break;
                        case "segundoApellido":
                        case "seguindoApellido":
                            strQuery += "AND p.segundoApellido = '" + (String) e.getValue() + "' ";
                            break;
                        case "genero":
                            strQuery += "AND p.genero LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaNacimiento":
                            strQuery += "AND p.fechaNacimiento = '" + (String) e.getValue() + "' ";
                            break;
                        case "afiliacionUbicacion.nombre":
                            strQuery += "AND UPPER(p.afiliacionUbicacionesId.nombre) LIKE UPPER('%" + (String) e.getValue() + "%') ";
                            break;
                        case "nivelSisben":
                            strQuery += "AND p.nivelSisben LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "categoriaIbc"://validar
                            strQuery += "AND p.categoriaIbc LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaAfiliacionEps":
                            strQuery += "AND p.fechaAfiliacionEps = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaEgresoEps":
                            strQuery += "AND p.fechaEgresoEps = '" + (String) e.getValue() + "' ";
                            break;
                        case "modeloLiquidacion":
                            strQuery += "AND p.modeloLiquidacion LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "primariaPrestadorSede.nombreSede":
                            strQuery += "AND UPPER(p.primariaCntPrestadorSedesId.nombreSede) LIKE  UPPER('%" + (String) e.getValue() + "%') ";
                            break;
                    }
                }
            }
            
            //strQuery += " GROUP BY p.id ";
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getParametroConsulta7() != null) {
                query.setParameter("fhNacimiento", ((Date) paramConsulta.getParametroConsulta7()), TemporalType.TIMESTAMP);
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
    public List<AsegAfiliado> consultarListaHistorico(ParamConsulta paramConsulta) throws Exception {
        List<AsegAfiliado> listResult = new ArrayList();
        int i = 0;
        SimpleDateFormat std = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaConsulta;
        try {
            fechaConsulta = (Date) paramConsulta.getParametroConsulta9();
            String strQuery = "SELECT p FROM AsegAfiliados p "
                    + " JOIN p.asegAfiliadoHistoricosList hist WHERE p.id = hist.asegAfiliadosId.id "
                    + " AND hist.fechaHoraCrea  <= '" + std.format( (Date) paramConsulta.getParametroConsulta9() ) + "' ";
            if (paramConsulta.getParametroConsulta1() != null) {
               strQuery += " AND p.maeTipoDocumentoId = " + paramConsulta.getParametroConsulta1() + " ";
            }
            
            if (paramConsulta.getParametroConsulta2() != null) {
               strQuery += " AND p.numeroDocumento = '" + (String) paramConsulta.getParametroConsulta2() + "' ";
            }
            
              if (paramConsulta.getParametroConsulta3() != null) {
               strQuery += " AND ( p.primerNombre = '" + (String) paramConsulta.getParametroConsulta3() + "') ";
            }
             
            if (paramConsulta.getParametroConsulta4() != null) {
                strQuery += " AND ( p.segundoNombre = '" + (String) paramConsulta.getParametroConsulta4() + "' ) ";
            }
            
            if (paramConsulta.getParametroConsulta5() != null) {
               strQuery += " AND ( p.primerApellido = '" + (String) paramConsulta.getParametroConsulta5() + "' ) ";
            }
            
            if (paramConsulta.getParametroConsulta6() != null) {
               strQuery += " AND ( p.segundoApellido = '" + (String) paramConsulta.getParametroConsulta6() + "' ) ";
            }
            
            if (paramConsulta.getParametroConsulta7() != null) {
                strQuery += "AND p.fechaNacimiento = :fhNacimiento  ";            
            }
            
            if (paramConsulta.getParametroConsulta8() != null) {
                strQuery += " AND p.idAfiliado = '" + (String) paramConsulta.getParametroConsulta8() + "' ";
            }
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeEstadoAfiliacion":
                            strQuery += "AND p.maeEstadoAfiliacionId = " + e.getValue() + " ";
                            break;
                        case "regimen":
                            strQuery += "AND p.regimen LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "idAfiliado":
                            strQuery += "AND p.idAfiliado LIKE '%" + e.getValue() + "%' ";
                            break; 
                        case "serialBdua":
                            strQuery += "AND p.serialBdua = " + (String) e.getValue() + " ";
                            break;            
                        case "maeTipoDocumento":
                            strQuery += "AND p.maeTipoDocumentoId = " + (String) e.getValue() + " ";
                            break;
                        case "documento":
                        case "numeroDocumento":
                            strQuery += "AND p.numeroDocumento = '" + (String) e.getValue() + "' ";
                            break;
                        case "primerNombre":
                            strQuery += "AND p.primerNombre = '" + (String) e.getValue() + "' ";
                            break;
                        case "segundoNombre":
                        case "seguindoNombre":
                            strQuery += "AND p.segundoNombre = '" + (String) e.getValue() + "' ";
                            break;
                        case "primerApellido":
                            strQuery += "AND p.primerApellido = '" + (String) e.getValue() + "' ";
                            break;
                        case "segundoApellido":
                        case "seguindoApellido":
                            strQuery += "AND p.segundoApellido = '" + (String) e.getValue() + "' ";
                            break;
                        case "genero":
                            strQuery += "AND p.genero LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaNacimiento":
                            strQuery += "AND p.fechaNacimiento = '" + (String) e.getValue() + "' ";
                            break;
                        case "afiliacionUbicacion.nombre":
                            strQuery += "AND UPPER(p.afiliacionUbicacionesId.nombre) LIKE UPPER('%" + (String) e.getValue() + "%') ";
                            break;
                        case "nivelSisben":
                            strQuery += "AND p.nivelSisben LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "categoriaIbc"://validar
                            strQuery += "AND p.categoriaIbc LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaAfiliacionEps":
                            strQuery += "AND p.fechaAfiliacionEps = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaEgresoEps":
                            strQuery += "AND p.fechaEgresoEps = '" + (String) e.getValue() + "' ";
                            break;
                        case "modeloLiquidacion":
                            strQuery += "AND p.modeloLiquidacion LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "primariaPrestadorSede.nombreSede":
                            strQuery += "AND UPPER(p.primariaCntPrestadorSedesId.nombreSede) LIKE  UPPER('%" + (String) e.getValue() + "%') ";
                            break;
                    }
                }
            }
            strQuery += " GROUP BY p.id ";
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) { 
                
                 switch (paramConsulta.getOrden()) {
                    case "documento":
                            strQuery += "p.numeroDocumento ";
                            break;
                    case "seguindoNombre":
                          strQuery += "p.segundoNombre ";
                        break;
                    case "seguindoApellido":
                          strQuery += "p.segundoApellido ";
                        break;
                    default:
                        strQuery += "p." + paramConsulta.getOrden() + " ";
                        break;
                }
            
                strQuery += (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            
            
            Query query = getEntityManager().createQuery(strQuery);
            
            if (paramConsulta.getParametroConsulta7() != null) {
                query.setParameter("fhNacimiento", ((Date) paramConsulta.getParametroConsulta7()), TemporalType.TIMESTAMP);
            }
            
            List<AsegAfiliados> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AsegAfiliados per : list) {
                listResult.add(castEntidadNegocioCortoHistorico(per,fechaConsulta));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            //Exception(CONSULTAR_TODOS, e);
            
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
    public static AsegAfiliado castEntidadNegocioCortoHistorico(AsegAfiliados per, Date fechaConsulta) {
        AsegAfiliado afiliado = new AsegAfiliado();
        afiliado.setId(per.getId());
        afiliado.setIdAfiliado(per.getIdAfiliado());//Contrato
        afiliado.setPrimerNombre(per.getPrimerNombre());
        afiliado.setSegundoNombre(per.getSegundoNombre());
        afiliado.setPrimerApellido(per.getPrimerApellido());
        afiliado.setSegundoApellido(per.getSegundoApellido());
        afiliado.setNumeroDocumento(per.getNumeroDocumento());
        afiliado.setSerialBdua(per.getSerialBdua());
        afiliado.setFechaNacimiento(per.getFechaNacimiento());
        // se debe obtener la lista de historial de afiliado, esto para el registro
        if (per.getAsegAfiliadoHistoricosList() != null) {
            afiliado.setListaAsegAfiliadoHistorico(castListaAfiliadoHistoricoEntidadNegocio(per.getAsegAfiliadoHistoricosList(),fechaConsulta));
            
        }
        return afiliado;
    }
    
    private static List<AsegAfiliadoHistorico> castListaAfiliadoHistoricoEntidadNegocio(List<AsegAfiliadoHistoricos> asegAfiliadoHistoricosList, Date fechaConsulta) {
        List<AsegAfiliadoHistorico> listaAfiliadosHistorico = new ArrayList<>();
        AsegAfiliadoHistorico obj = null;
        Date fechaAnterior = null;
        // ordenamos la lista inicialmente
        for (AsegAfiliadoHistoricos per: asegAfiliadoHistoricosList) {
            //validamos la primera vez que es null
            if (fechaAnterior == null) {
                if (per.getFechaHoraCrea().compareTo(fechaConsulta) <= 0) {
                    fechaAnterior = per.getFechaHoraCrea();
                    //hacemos cast del objeto. Recordemos que solo uno quedará en la lista, que debe ser el más próximo a la fecha
                    obj = castAfiliadoHistoricoEntidadNegocioLargo(per);
                }
            } else {
                // se ha realizado una asignación previa
                //validamos si la fecha del objeto, esta entre la fecha anterior y la fecha de consulta. Si es así, se reemplaza el objeto
                if (per.getFechaHoraCrea().compareTo(fechaAnterior) >= 0 && per.getFechaHoraCrea().compareTo(fechaConsulta) <= 0) {                    
                        fechaAnterior = per.getFechaHoraCrea();
                        obj = castAfiliadoHistoricoEntidadNegocioLargo(per);
                }
            }
        }
        //validamos si se obtuvo un registro que cumpliera con la proximidad de fecha, para retornarlo
        if (obj != null) {
            listaAfiliadosHistorico.add(obj);
        }
        
        return listaAfiliadosHistorico;
    }
    
    public static AsegAfiliadoHistorico castAfiliadoHistoricoEntidadNegocioLargo(AsegAfiliadoHistoricos per) {
        AsegAfiliadoHistorico obj = new AsegAfiliadoHistorico();
        obj.setId(per.getId());
        obj.setTostringAfiliado(per.getTostringAfiliado());
        // auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        //objetos
        if (per.getAsegAfiliadosId() != null) {
            obj.setAsegAfiliado(new AsegAfiliado(per.getAsegAfiliadosId().getId()));
        }
        return obj;
    }

    public static AsegAfiliadoHistoricos castAfiliadoHistoricoNegocioEntidadLargo(AsegAfiliadoHistorico obj) {
        AsegAfiliadoHistoricos per = new AsegAfiliadoHistoricos();
        per.setId(obj.getId());
        per.setTostringAfiliado(obj.getTostringAfiliado());
        // auditoria
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        //objetos
        if (obj.getAsegAfiliado() != null) {
            per.setAsegAfiliadosId(new AsegAfiliados(obj.getAsegAfiliado().getId()));
        }
        return per;
    }
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        //SimpleDateFormat std = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String strQuery = "SELECT COUNT(p) FROM AsegAfiliados p "
                    + "WHERE p.id > 0 ";
             if (paramConsulta.getParametroConsulta1() != null) {
               strQuery += " AND p.maeTipoDocumentoId = " + paramConsulta.getParametroConsulta1() + " ";
            }
            
            if (paramConsulta.getParametroConsulta2() != null) {
               strQuery += " AND p.numeroDocumento = '" + (String) paramConsulta.getParametroConsulta2() + "' ";
            }
            
            if (paramConsulta.getParametroConsulta3() != null) {
               strQuery += " AND ( p.primerNombre = '" + (String) paramConsulta.getParametroConsulta3() + "') ";
            }
             
            if (paramConsulta.getParametroConsulta4() != null) {
                strQuery += " AND ( p.segundoNombre = '" + (String) paramConsulta.getParametroConsulta4() + "' ) ";
            }
            
            if (paramConsulta.getParametroConsulta5() != null) {
               strQuery += " AND ( p.primerApellido = '" + (String) paramConsulta.getParametroConsulta5() + "' ) ";
            }
            
            if (paramConsulta.getParametroConsulta6() != null) {
               strQuery += " AND ( p.segundoApellido = '" + (String) paramConsulta.getParametroConsulta6() + "' ) ";
            }
            
            if (paramConsulta.getParametroConsulta7() != null) {
                strQuery += "AND p.fechaNacimiento = :fhNacimiento  ";            
            }
            
            if (paramConsulta.getParametroConsulta8() != null) {
                strQuery += " AND p.idAfiliado = '" + (String) paramConsulta.getParametroConsulta8() + "' ";
            }
                
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeEstadoAfiliacion":
                            strQuery += "AND p.maeEstadoAfiliacionId = " + e.getValue() + " ";
                            break;
                        case "regimen":
                            strQuery += "AND p.regimen LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "idAfiliado":
                            strQuery += "AND p.idAfiliado LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "serialBdua":
                            strQuery += "AND p.serialBdua = " + (String) e.getValue() + " ";
                            break;
                        case "maeTipoDocumento":
                            strQuery += "AND p.maeTipoDocumentoId = " + (String) e.getValue() + " ";
                            break;
                        case "documento":
                        case "numeroDocumento":
                            strQuery += "AND p.numeroDocumento = '" + (String) e.getValue() + "' ";
                            break;
                        case "primerNombre":
                            strQuery += "AND p.primerNombre = '" + (String) e.getValue() + "' ";
                            break;
                        case "segundoNombre":
                        case "seguindoNombre":
                            strQuery += "AND p.segundoNombre = '" + (String) e.getValue() + "' ";
                            break;
                        case "primerApellido":
                            strQuery += "AND p.primerApellido = '" + (String) e.getValue() + "' ";
                            break;
                        case "segundoApellido":
                        case "seguindoApellido":
                            strQuery += "AND p.segundoApellido = '" + (String) e.getValue() + "' ";
                            break;
                        case "genero":
                            strQuery += "AND p.genero LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaNacimiento":
                            strQuery += "AND p.fechaNacimiento = '" + (String) e.getValue() + "' ";
                            break;
                        case "afiliacionUbicacion.nombre":
                            strQuery += "AND UPPER(p.afiliacionUbicacionesId.nombre) LIKE UPPER('%" + (String) e.getValue() + "%') ";
                            break;
                        case "nivelSisben":
                            strQuery += "AND p.nivelSisben LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "categoriaIbc"://validar
                            strQuery += "AND p.categoriaIbc LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaAfiliacionEps":
                            strQuery += "AND p.fechaAfiliacionEps = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaEgresoEps":
                            strQuery += "AND p.fechaEgresoEps = '" + (String) e.getValue() + "' ";
                            break;
                        case "modeloLiquidacion":
                            strQuery += "AND p.modeloLiquidacion LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "primariaPrestadorSede.nombreSede":
                            strQuery += "AND UPPER(p.primariaCntPrestadorSedesId.nombreSede) LIKE  UPPER('%" + (String) e.getValue() + "%') ";
                            break;
                    }
                }
            }
            
            strQuery += " GROUP BY p.id ";
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getParametroConsulta7() != null) {
                query.setParameter("fhNacimiento", ((Date) paramConsulta.getParametroConsulta7()), TemporalType.TIMESTAMP);
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
    public List<AsegAfiliado> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AsegAfiliado> listResult = new ArrayList();
        int i = 0;
        //SimpleDateFormat std = new SimpleDateFormat("yyyy-MM-dd");
        //Date fechaConsulta;
        try {
            String strQuery = "FROM AsegAfiliados p "
                    + "WHERE p.id > 0 ";
          
            if (paramConsulta.getParametroConsulta1() != null) {  
                if (paramConsulta.getParametroConsulta2() != null) {
                    strQuery += "AND p.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.maeTipoDocumentoId = " + paramConsulta.getParametroConsulta1() + 
                            " AND ad.numeroDocumento = '" + (String) paramConsulta.getParametroConsulta2() + "' ) ";
                }
            } else {
                if (paramConsulta.getParametroConsulta2() != null) {
                    strQuery += "AND p.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.numeroDocumento = '" + (String) paramConsulta.getParametroConsulta2() + "' ) ";
                }
            }
            
              if (paramConsulta.getParametroConsulta3() != null) {
               strQuery += " AND ( p.primerNombre = '" + (String) paramConsulta.getParametroConsulta3() + "') ";
            }
             
            if (paramConsulta.getParametroConsulta4() != null) {
                strQuery += " AND ( p.segundoNombre = '" + (String) paramConsulta.getParametroConsulta4() + "' ) ";
            }
            
            if (paramConsulta.getParametroConsulta5() != null) {
               strQuery += " AND ( p.primerApellido = '" + (String) paramConsulta.getParametroConsulta5() + "' ) ";
            }
            
            if (paramConsulta.getParametroConsulta6() != null) {
               strQuery += " AND ( p.segundoApellido = '" + (String) paramConsulta.getParametroConsulta6() + "' ) ";
            }
            
            if (paramConsulta.getParametroConsulta7() != null) {
                strQuery += "AND p.fechaNacimiento = :fhNacimiento  ";            
            }
            
            if (paramConsulta.getParametroConsulta8() != null) {
                strQuery += " AND p.idAfiliado = '" + (String) paramConsulta.getParametroConsulta8() + "' ";
            }
            //2024-01-10 jyperez IS 58 retiro por duplicado - se configura parámetro para que sólo traiga los que no estan marcados como duplicados
            //2024-02-29 jyperez SE hace el cambio de validación a el valor cero
            if (paramConsulta.getParametroConsulta9() != null) {
                strQuery += " AND p.duplicado  = " + (int) paramConsulta.getParametroConsulta9() + " ";
            }
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) { 
                    switch ((String) e.getKey()) {
                        case "maeEstadoAfiliacion":
                            strQuery += "AND p.maeEstadoAfiliacionId = " + e.getValue() + " ";
                            break;
                        case "regimen":
                            strQuery += "AND p.regimen LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "idAfiliado":
                            strQuery += "AND p.idAfiliado LIKE '%" + e.getValue() + "%' ";
                            break; 
                        case "serialBdua":
                            strQuery += "AND p.serialBdua = " + (String) e.getValue() + " ";
                            break;            
                        case "maeTipoDocumento":
                            strQuery += "AND p.maeTipoDocumentoId = " + (String) e.getValue() + " ";
                            break;
                        case "documento":
                        case "numeroDocumento":
                            strQuery += "AND p.numeroDocumento = '" + (String) e.getValue() + "' ";
                            break;
                        case "primerNombre":
                            strQuery += "AND p.primerNombre = '" + (String) e.getValue() + "' ";
                            break;
                        case "segundoNombre":
                        case "seguindoNombre":
                            strQuery += "AND p.segundoNombre = '" + (String) e.getValue() + "' ";
                            break;
                        case "primerApellido":
                            strQuery += "AND p.primerApellido = '" + (String) e.getValue() + "' ";
                            break;
                        case "segundoApellido":
                        case "seguindoApellido":
                            strQuery += "AND p.segundoApellido = '" + (String) e.getValue() + "' ";
                            break;
                        case "genero":
                            strQuery += "AND p.genero LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaNacimiento":
                            strQuery += "AND p.fechaNacimiento = '" + (String) e.getValue() + "' ";
                            break;
                        case "afiliacionUbicacion.nombre":
                            strQuery += "AND UPPER(p.afiliacionUbicacionesId.nombre) LIKE UPPER('%" + (String) e.getValue() + "%') ";
                            break;
                        case "nivelSisben":
                            strQuery += "AND p.nivelSisben LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "categoriaIbc"://validar
                            strQuery += "AND p.categoriaIbc LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaAfiliacionEps":
                            strQuery += "AND p.fechaAfiliacionEps = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaEgresoEps":
                            strQuery += "AND p.fechaEgresoEps = '" + (String) e.getValue() + "' ";
                            break;
                        case "modeloLiquidacion":
                            strQuery += "AND p.modeloLiquidacion LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "primariaPrestadorSede.nombreSede":
                            strQuery += "AND UPPER(p.primariaCntPrestadorSedesId.nombreSede) LIKE  UPPER('%" + (String) e.getValue() + "%') ";
                            break;
                    }
                }
            }
            //strQuery += " GROUP BY p.id ";
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) { 
                
                 switch (paramConsulta.getOrden()) {
                    case "documento":
                            strQuery += "p.numeroDocumento ";
                            break;
                    case "seguindoNombre":
                          strQuery += "p.segundoNombre ";
                        break;
                    case "seguindoApellido":
                          strQuery += "p.segundoApellido ";
                        break;
                    default:
                        strQuery += "p." + paramConsulta.getOrden() + " ";
                        break;
                }
            
                strQuery += (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            
            
            Query query = getEntityManager().createQuery(strQuery);
            
            if (paramConsulta.getParametroConsulta7() != null) {
                query.setParameter("fhNacimiento", ((Date) paramConsulta.getParametroConsulta7()), TemporalType.TIMESTAMP);
            }
            
            List<AsegAfiliados> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AsegAfiliados per : list) {
                listResult.add(castEntidadNegocioCorto(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            //Exception(CONSULTAR_TODOS, e);
            
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
    public static AsegAfiliado castEntidadNegocioCorto(AsegAfiliados per) {
        AsegAfiliado afiliado = new AsegAfiliado();
        afiliado.setId(per.getId());
        afiliado.setIdAfiliado(per.getIdAfiliado());//Contrato
        afiliado.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        afiliado.setPrimerNombre(per.getPrimerNombre());
        afiliado.setSegundoNombre(per.getSegundoNombre());
        afiliado.setPrimerApellido(per.getPrimerApellido());
        afiliado.setSegundoApellido(per.getSegundoApellido());
        afiliado.setNumeroDocumento(per.getNumeroDocumento());
        afiliado.setSerialBdua(per.getSerialBdua());
        afiliado.setFechaNacimiento(per.getFechaNacimiento());
        //2024-02-29 jyperez se realiza ajuste para actualizar el valor duplicado de nulo a cero
        if (per.getDuplicado() != null ) {
            afiliado.setDuplicado(per.getDuplicado());
        } else {
            afiliado.setDuplicado(false);
        }
        return afiliado;
    }

}

