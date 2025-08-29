/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.aseguramiento;

import com.saviasaludeps.savia.dominio.administracion.GnUbicacionBarrio;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContacto;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContactoConsulta;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegRadicadoNovedad;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.aseguramiento.ReporteAfiliacion;
import com.saviasaludeps.savia.dominio.aseguramiento.ReporteEncuesta016;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliadoContactos;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliadoDocumentos;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AsegRadicadoNovedades;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.entidades.GnMaestros;
import com.saviasaludeps.savia.ejb.entidades.GnUbicacionBarrios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.utilidades.MetaPhoneEsp;
import com.saviasaludeps.savia.ejb.utilidades.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author jose perez hernandez
 */
@Stateless
@Remote(AfiliadoRemoto.class)
public class AfiliadoServicio extends GenericoServicio implements AfiliadoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p.maeTipoDocumentoId) FROM AsegAfiliados p "
                    + "WHERE p.maeTipoDocumentoId > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeEstadoAfiliacion":
                            strQuery += "AND p.maeEstadoAfiliacionId = " + e.getValue() + " ";
                            break;
                        case "maeRegimenId":
                            strQuery += "AND p.maeRegimenId = " + e.getValue() + " ";
                            break;
                        case "idAfiliado":
                            strQuery += "AND p.idAfiliado = '" + e.getValue() + "' ";
                            break;
                        case "serialBdua":
                            strQuery += "AND p.serialBdua LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoDocumento":
                            strQuery += "AND p.maeTipoDocumentoId = " + (String) e.getValue() + " ";
                            break;
                        case "numeroDocumento":
                            strQuery += "AND p.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.numeroDocumento = '" + (String) e.getValue() + "' ) ";
                            break;
                        case "primerNombre":
                            strQuery += "AND p.primerNombre = '" + (String) e.getValue() + "' ";
                            break;
                        case "segundoNombre":
                            strQuery += "AND p.segundoNombre = '" + (String) e.getValue() + "' ";
                            break;
                        case "primerApellido":
                            strQuery += "AND p.primerApellido = '" + (String) e.getValue() + "' ";
                            break;
                        case "segundoApellido":
                            strQuery += "AND p.segundoApellido = '" + (String) e.getValue() + "' ";
                            break;
                        case "genero":
                            strQuery += "AND p.genero LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeGeneroId":
                            strQuery += "AND p.maeGeneroId = " + (String) e.getValue() + " ";
                            break;
                        case "fechaNacimiento":
                            strQuery += "AND p.fechaNacimiento = '" + (String) e.getValue() + "' ";
                            break;
                        case "afiliacionUbicacion.nombre":
                            strQuery += "AND p.afiliacionUbicacionesId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeNivelSisbenId":
                            strQuery += "AND p.maeNivelSisbenId = " + (String) e.getValue() + " ";
                            break;
                        case "categoriaIbc":
                            strQuery += "AND p.categoriaIbc LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeCategoriaIbcId":
                            strQuery += "AND p.maeCategoriaIbcId = " + (String) e.getValue() + " ";
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
                        case "maeModeloLiquidacionId":
                            strQuery += "AND p.maeModeloLiquidacionId = " + (String) e.getValue() + " ";
                            break;
                        case "primariaPrestadorSede.nombreSede":
                            strQuery += "AND p.primariaCntPrestadorSedesId.nombre = '" + (String) e.getValue() + "' ";
                            break;
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
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
    public List<AsegAfiliado> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AsegAfiliado> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p.id, p.idAfiliado, p.serialBdua, p.primerNombre "
                    + ", p.segundoNombre, p.primerApellido, p.segundoApellido, p.fechaNacimiento "
                    + ", p.maeGeneroId, p.maeGeneroValor, p.maeTipoDocumentoId, p.maeTipoDocumentoValor "
                    + ", p.numeroDocumento, p.fechaAfiliacionEps, p.fechaEgresoEps, p.maeEstadoAfiliacionId "
                    + ", p.maeEstadoAfiliacionValor, p.maeRegimenId, p.maeRegimenValor , p.maeNivelSisbenId "
                    + ", p.maeNivelSisbenValor, p.maeCategoriaIbcId, p.maeCategoriaIbcValor, p.maeModeloLiquidacionId "
                    + ", p.maeModeloLiquidacionValor, p.primariaCntPrestadorSedesId.nombre, p.afiliacionUbicacionesId.nombre "
                    + "FROM AsegAfiliados p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeEstadoAfiliacion":
                            strQuery += "AND p.maeEstadoAfiliacionId = " + e.getValue() + " ";
                            break;
                        case "maeRegimenId":
                            strQuery += "AND p.maeRegimenId = " + e.getValue() + " ";
                            break;
                        case "idAfiliado":
                            strQuery += "AND p.idAfiliado = '" + e.getValue() + "' ";
                            break;
                        case "serialBdua":
                            strQuery += "AND p.serialBdua LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoDocumento":
                            strQuery += "AND p.maeTipoDocumentoId = " + (String) e.getValue() + " ";
                            break;
                        case "numeroDocumento":
                            strQuery += "AND p.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.numeroDocumento = '" + (String) e.getValue() + "' ) ";
                            break;
                        case "primerNombre":
                            strQuery += "AND p.primerNombre = '" + (String) e.getValue() + "' ";
                            break;
                        case "segundoNombre":
                            strQuery += "AND p.segundoNombre = '" + (String) e.getValue() + "' ";
                            break;
                        case "primerApellido":
                            strQuery += "AND p.primerApellido = '" + (String) e.getValue() + "' ";
                            break;
                        case "segundoApellido":
                            strQuery += "AND p.segundoApellido = '" + (String) e.getValue() + "' ";
                            break;
                        case "genero":
                            strQuery += "AND p.genero LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeGeneroId":
                            strQuery += "AND p.maeGeneroId = " + (String) e.getValue() + " ";
                            break;
                        case "fechaNacimiento":
                            strQuery += "AND p.fechaNacimiento = '" + (String) e.getValue() + "' ";
                            break;
                        case "afiliacionUbicacion.nombre":
                            strQuery += "AND p.afiliacionUbicacionesId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeNivelSisbenId":
                            strQuery += "AND p.maeNivelSisbenId = " + (String) e.getValue() + " ";
                            break;
                        case "categoriaIbc":
                            strQuery += "AND p.categoriaIbc LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeCategoriaIbcId":
                            strQuery += "AND p.maeCategoriaIbcId = " + (String) e.getValue() + " ";
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
                        case "maeModeloLiquidacionId":
                            strQuery += "AND p.maeModeloLiquidacionId = " + (String) e.getValue() + " ";
                            break;
                        case "primariaPrestadorSede.nombreSede":
                            strQuery += "AND p.primariaCntPrestadorSedesId.nombre = '" + (String) e.getValue() + "' ";
                            break;
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "maeRegimenCodigo":
                            strQuery += "AND p.maeRegimenCodigo = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                if (paramConsulta.getOrden().equals("afiliacionUbicacion.nombre")) {
                    strQuery += "p.afiliacionUbicacionesId.nombre "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else {
                    strQuery += "p." + paramConsulta.getOrden() + " "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                }
            } else {
                strQuery += "p.id DESC";
            }
            List<Object[]> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (Object[] per : list) {
                int j = 0;
                AsegAfiliado af = new AsegAfiliado();
                af.setId((Integer) per[j]);
                j++;
                if (per[j] != null) {
                    af.setIdAfiliado(per[j].toString());
                }
                j++;
                af.setSerialBdua((BigInteger) per[j]);
                j++;
                af.setPrimerNombre(per[j].toString());
                j++;
                if (per[j] != null) {
                    af.setSegundoNombre(per[j].toString());
                }
                j++;
                af.setPrimerApellido(per[j].toString());
                j++;
                if (per[j] != null) {
                    af.setSegundoApellido(per[j].toString());
                }
                j++;
                af.setFechaNacimiento((Date) per[j]);
                j++;
                af.setMaeGeneroId((Integer) per[j]);
                j++;
                if (per[j] != null) {
                    af.setMaeGeneroValor(per[j].toString());
                }
                j++;
                af.setMaeTipoDocumento((Integer) per[j]);
                j++;
                if (per[j] != null) {
                    af.setMaeTipoDocumentoValor(per[j].toString());
                }
                j++;
                af.setNumeroDocumento(per[j].toString());
                j++;
                af.setFechaAfiliacionEps((Date) per[j]);
                j++;
                af.setFechaEgresoEps((Date) per[j]);
                j++;
                af.setMaeEstadoAfiliacion((Integer) per[j]);
                j++;
                if (per[j] != null) {
                    af.setMaeEstadoAfiliacionValor(per[j].toString());
                }
                j++;
                af.setMaeRegimenId((Integer) per[j]);
                j++;
                if (per[j] != null) {
                    af.setMaeRegimenValor(per[j].toString());
                }
                j++;
                af.setMaeNivelSisbenId((Integer) per[j]);
                j++;
                if (per[j] != null) {
                    af.setMaeNivelSisbenValor(per[j].toString());
                }
                j++;
                af.setMaeCategoriaIbcId((Integer) per[j]);
                j++;
                if (per[j] != null) {
                    af.setMaeCategoriaIbcValor(per[j].toString());
                }
                j++;
                af.setMaeModeloLiquidacionId((Integer) per[j]);
                j++;
                if (per[j] != null) {
                    af.setMaeModeloLiquidacionValor(per[j].toString());
                }
                j++;
                //primaria prestador
                if (per[j] != null) {
                    af.setPrimariaPrestadorSede(new CntPrestadorSede());
                    af.getPrimariaPrestadorSede().setNombreSede(per[j].toString());
                    j++;
                } else {
                    j++;
                }
                //afiliacion ubicacion
                if (per[j] != null) {
                    af.setAfiliacionUbicacion(new Ubicacion());
                    af.getAfiliacionUbicacion().setNombre(per[j].toString());
                }
                //2022-09-07 jyperez se realiza la adición del histórico de documentos en el nuevo campo
                af.setHistoricoDocumentoAfiliado(obtenerHistoricoPorAfiliado(af.getId()));

                listResult.add(af);
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
    public AsegAfiliado consultar(int id) throws Exception {
        AsegAfiliado objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AsegAfiliados) getEntityManager().find(AsegAfiliados.class, id));
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
    public int insertar(AsegAfiliado obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidadLargo(obj)).getId();
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
    public void actualizar(AsegAfiliado obj) throws Exception {
        try {
            AsegAfiliados per = castNegocioEntidadLargo(obj);
            //getEntityManager().merge(castNegocioEntidadLargo(obj));
            Session session = getEntityManager().unwrap(Session.class);
            //session.update(per);
            String strQuery = "UPDATE AsegAfiliados a SET ";
            //strQuery += " a.id = :id";
            strQuery += " a.idAfiliado = :idAfiliado ,";
            strQuery += " a.serialBdua = :serialBdua ,";
            strQuery += " a.registroBdua = :registroBdua ,";
            strQuery += " a.primerNombre = :primerNombre ,";
            strQuery += " a.segundoNombre = :segundoNombre ,";
            strQuery += " a.primerApellido = :primerApellido ,";
            strQuery += " a.segundoApellido = :segundoApellido ,";
            //Cambio fecha nacimiento
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            String fechaNacimiento = formatoFecha.format(obj.getFechaNacimiento());
            strQuery += " a.fechaNacimiento = '" + fechaNacimiento + "', ";
//            strQuery += " a.fechaNacimiento = :fechaNacimiento ,";
            strQuery += " a.genero = :genero ,";
            strQuery += " a.maeGeneroId = :maeGeneroId ,";
            strQuery += " a.maeGeneroCodigo = :maeGeneroCodigo ,";
            strQuery += " a.maeGeneroValor = :maeGeneroValor ,";
            //2023-06-15 jyperez N45 genero de identificación
            strQuery += " a.maeGeneroIdentificacionId = :maeGeneroIdentificacionId ,";
            strQuery += " a.maeGeneroIdentificacionCodigo = :maeGeneroIdentificacionCodigo ,";
            strQuery += " a.maeGeneroIdentificacionValor = :maeGeneroIdentificacionValor ,";
            //2020-12-21 jyperez Cambio fecha afiliación (cambia un día)
            String fechaExpedicionCedula;
            //Se añade validación null debido a que se presentan nulos en fecha expedicion
            if (obj.getFechaExpedicionCedula() != null) {
                fechaExpedicionCedula = formatoFecha.format(obj.getFechaExpedicionCedula());
                strQuery += " a.fechaExpedicionCedula = '" + fechaExpedicionCedula + "', ";
            }
            strQuery += " a.maeTipoDocumentoId = :maeTipoDocumentoId ,";
            strQuery += " a.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo ,";
            strQuery += " a.maeTipoDocumentoValor = :maeTipoDocumentoValor ,";
            strQuery += " a.numeroDocumento = :numeroDocumento ,";
            strQuery += " a.maeTipoDocumentoCfId = :maeTipoDocumentoCfId ,";
            strQuery += " a.maeTipoDocumentoCfCodigo = :maeTipoDocumentoCfCodigo ,";
            strQuery += " a.maeTipoDocumentoCfValor = :maeTipoDocumentoCfValor ,";
            strQuery += " a.numeroDocumentoCf = :numeroDocumentoCf ,";
            //2020-12-21 jyperez Cambio fecha afiliación (cambia un día)
            String fechaAfiliacionSgsss = formatoFecha.format(obj.getFechaAfiliacionSgsss());
            strQuery += " a.fechaAfiliacionSgsss = '" + fechaAfiliacionSgsss + "', ";
            strQuery += " a.fechaAfiliacionEps = :fechaAfiliacionEps ,";
            strQuery += " a.afiliacionLegalizada = :afiliacionLegalizada ,";
            strQuery += " a.fechaEgresoEps = :fechaEgresoEps ,";
            strQuery += " a.fechaCambioEstadoEps = :fechaCambioEstadoEps ,";
            //2022-10-31 jyperez nuevo campo actualización
            strQuery += " a.fechaSuspensionEps = :fechaSuspensionEps ,";
            strQuery += " a.tipoBeneficiario = :tipoBeneficiario ,";
            strQuery += " a.maeTipoAfiliadoId = :maeTipoAfiliadoId ,";
            strQuery += " a.maeTipoAfiliadoCodigo = :maeTipoAfiliadoCodigo ,";
            strQuery += " a.maeTipoAfiliadoValor = :maeTipoAfiliadoValor ,";
            strQuery += " a.maeEstadoAfiliacionId = :maeEstadoAfiliacionId ,";
            strQuery += " a.maeEstadoAfiliacionCodigo = :maeEstadoAfiliacionCodigo ,";
            strQuery += " a.maeEstadoAfiliacionValor = :maeEstadoAfiliacionValor ,";
            strQuery += " a.maeOrigenAfiliadoId = :maeOrigenAfiliadoId ,";
            strQuery += " a.maeOrigenAfiliadoCodigo = :maeOrigenAfiliadoCodigo ,";
            strQuery += " a.maeOrigenAfiliadoValor = :maeOrigenAfiliadoValor ,";
            strQuery += " a.parentescoCotizante = :parentescoCotizante ,";
            strQuery += " a.maeParentescoCotizanteId = :maeParentescoCotizanteId ,";
            strQuery += " a.maeParentescoCotizanteCodigo = :maeParentescoCotizanteCodigo ,";
            strQuery += " a.maeParentescoCotizanteValor = :maeParentescoCotizanteValor ,";
            strQuery += " a.autorizaEnvioSms = :autorizaEnvioSms ,";
            strQuery += " a.autorizaEnvioEmail = :autorizaEnvioEmail ,";
            strQuery += " a.telefonoFijo = :telefonoFijo ,";
            strQuery += " a.telefonoMovil = :telefonoMovil ,";
            strQuery += " a.email = :email ,";
            strQuery += " a.zona = :zona ,";
            strQuery += " a.maeZonaId = :maeZonaId ,";
            strQuery += " a.maeZonaCodigo = :maeZonaCodigo ,";
            strQuery += " a.maeZonaValor = :maeZonaValor ,";
            strQuery += " a.direccionResidencia = :direccionResidencia ,";
            strQuery += " a.barrio = :barrio ,";
            strQuery += " a.regimen = :regimen ,";
            strQuery += " a.nivelSisben = :nivelSisben ,";
            strQuery += " a.puntajeSisben = :puntajeSisben ,";
            strQuery += " a.fichaSisben = :fichaSisben ,";
            strQuery += " a.fechaSisben = :fechaSisben ,";
            strQuery += " a.categoriaIbc = :categoriaIbc ,";
            strQuery += " a.maeCategoriaIbcId = :maeCategoriaIbcId ,";
            strQuery += " a.maeCategoriaIbcCodigo = :maeCategoriaIbcCodigo ,";
            strQuery += " a.maeCategoriaIbcValor = :maeCategoriaIbcValor ,";
            strQuery += " a.tienePortabilidad = :tienePortabilidad ,";
            strQuery += " a.fechaPortabilidad = :fechaPortabilidad ,";
            strQuery += " a.tipoCotizante = :tipoCotizante ,";
            strQuery += " a.maeTipoCotizanteId = :maeTipoCotizanteId ,";
            strQuery += " a.maeTipoCotizanteCodigo = :maeTipoCotizanteCodigo ,";
            strQuery += " a.maeTipoCotizanteValor = :maeTipoCotizanteValor ,";
            strQuery += " a.discapacidad = :discapacidad ,";
            strQuery += " a.tipoDiscapacidad = :tipoDiscapacidad ,";
            strQuery += " a.maeTipoDiscapacidadId = :maeTipoDiscapacidadId ,";
            strQuery += " a.maeTipoDiscapacidadCodigo = :maeTipoDiscapacidadCodigo ,";
            strQuery += " a.maeTipoDiscapacidadValor = :maeTipoDiscapacidadValor ,";
            strQuery += " a.condicionDiscapacidad = :condicionDiscapacidad ,";
            strQuery += " a.maeDiscapacidadCondicionId = :maeDiscapacidadCondicionId ,";
            strQuery += " a.maeDiscapacidadCondicionCodigo = :maeDiscapacidadCondicionCodigo ,";
            strQuery += " a.maeDiscapacidadCondicionValor = :maeDiscapacidadCondicionValor ,";
            strQuery += " a.fechaInicioDiscapacidad = :fechaInicioDiscapacidad ,";
            strQuery += " a.fechaFinDiscapacidad = :fechaFinDiscapacidad ,";
            strQuery += " a.maeGrupoPoblacionalId = :maeGrupoPoblacionalId ,";
            strQuery += " a.maeGrupoPoblacionalCodigo = :maeGrupoPoblacionalCodigo ,";
            strQuery += " a.maeGrupoPoblacionalValor = :maeGrupoPoblacionalValor ,";
            strQuery += " a.victima = :victima ,";
            strQuery += " a.etnia = :etnia ,";
            strQuery += " a.maeEtniaId = :maeEtniaId ,";
            strQuery += " a.maeEtniaCodigo = :maeEtniaCodigo ,";
            strQuery += " a.maeEtniaValor = :maeEtniaValor ,";
            strQuery += " a.maeComunidadEtniaId = :maeComunidadEtniaId ,";
            strQuery += " a.maeComunidadEtniaCodigo = :maeComunidadEtniaCodigo ,";
            strQuery += " a.maeComunidadEtniaValor = :maeComunidadEtniaValor ,";
            strQuery += " a.maeCausaNovedadId = :maeCausaNovedadId ,";
            strQuery += " a.maeCausaNovedadCodigo = :maeCausaNovedadCodigo ,";
            strQuery += " a.maeCausaNovedadValor = :maeCausaNovedadValor ,";
            strQuery += " a.fechaReactivacion = :fechaReactivacion ,";
            strQuery += " a.estadoCivil = :estadoCivil ,";
            strQuery += " a.maeEstadoCivilId = :maeEstadoCivilId ,";
            strQuery += " a.maeEstadoCivilCodigo = :maeEstadoCivilCodigo ,";
            strQuery += " a.maeEstadoCivilValor = :maeEstadoCivilValor ,";
            strQuery += " a.fechaMovilidad = :fechaMovilidad ,";
            strQuery += " a.modeloLiquidacion = :modeloLiquidacion ,";
            strQuery += " a.maeModeloLiquidacionId = :maeModeloLiquidacionId ,";
            strQuery += " a.maeModeloLiquidacionCodigo = :maeModeloLiquidacionCodigo ,";
            strQuery += " a.maeModeloLiquidacionValor = :maeModeloLiquidacionValor ,";
            strQuery += " a.maeTipoDocumentoAportanteId = :maeTipoDocumentoAportanteId ,";
            strQuery += " a.maeTipoDocumentoAportanteCodigo = :maeTipoDocumentoAportanteCodigo ,";
            strQuery += " a.maeTipoDocumentoAportanteValor = :maeTipoDocumentoAportanteValor ,";
            strQuery += " a.numeroDocumentoAportante = :numeroDocumentoAportante ,";
            strQuery += " a.maeActividadEconomicaId = :maeActividadEconomicaId ,";
            strQuery += " a.maeActividadEconomicaCodigo = :maeActividadEconomicaCodigo ,";
            strQuery += " a.maeActividadEconomicaValor = :maeActividadEconomicaValor ,";
            strQuery += " a.maeArlId = :maeArlId ,";
            strQuery += " a.maeArlCodigo = :maeArlCodigo ,";
            strQuery += " a.maeArlValor = :maeArlValor ,";
            strQuery += " a.maeAfpId = :maeAfpId ,";
            strQuery += " a.maeAfpCodigo = :maeAfpCodigo ,";
            strQuery += " a.maeAfpValor = :maeAfpValor ,";
            strQuery += " a.maeCajaCompensacionId = :maeCajaCompensacionId ,";
            strQuery += " a.maeCajaCompensacionCodigo = :maeCajaCompensacionCodigo ,";
            strQuery += " a.maeCajaCompensacionValor = :maeCajaCompensacionValor ,";
            strQuery += " a.sincronizado = :sincronizado ,";
            strQuery += " a.observacion = :observacion ,";
            strQuery += " a.sincronizado = :sincronizado,";
            strQuery += " a.historicoAfiliado = :historicoAfiliado ,";
            strQuery += " a.usuarioModifica = :usuarioModifica ,";
            strQuery += " a.fechaHoraModifica = :fechaHoraModifica ,";
            strQuery += " a.terminalModifica = :terminalModifica, ";
            //2021-05-03 jyperez nuevo campo
            strQuery += " a.maeGrupoSisbenId = :maeGrupoSisbenId, ";
            strQuery += " a.maeGrupoSisbenCodigo = :maeGrupoSisbenCodigo, ";
            strQuery += " a.maeGrupoSisbenValor = :maeGrupoSisbenValor, ";
            //2022-05-17 INC XXX  (estabilización Sprint 07) jyperez nuevo campo
            strQuery += " a.maeSubGrupoSisbenId = :maeSubGrupoSisbenId, ";
            strQuery += " a.maeSubGrupoSisbenCodigo = :maeSubGrupoSisbenCodigo, ";
            strQuery += " a.maeSubGrupoSisbenValor = :maeSubGrupoSisbenValor, ";
            //2021-05-03 jyperez nuevo campo
            strQuery += " a.codigoFonetico = :codigoFonetico, ";
            //2021-05-20 jyperez nuevos campos
            strQuery += " a.maeRegimenId = :maeRegimenId, ";
            strQuery += " a.maeRegimenCodigo = :maeRegimenCodigo, ";
            strQuery += " a.maeRegimenValor = :maeRegimenValor, ";
            strQuery += " a.maeNivelSisbenId = :maeNivelSisbenId, ";
            strQuery += " a.maeNivelSisbenCodigo = :maeNivelSisbenCodigo, ";
            strQuery += " a.maeNivelSisbenValor = :maeNivelSisbenValor, ";
            strQuery += " a.origenUltimoRegistro = :origenUltimoRegistro, ";
            //2022-02-21 jyperez nuevo campo resolución 2153 de 2021 de la Adres
            strQuery += " a.maeMetodologiaGrupoPoblacionalId = :maeMetodologiaGrupoPoblacionalId, ";
            strQuery += " a.maeMetodologiaGrupoPoblacionalCodigo = :maeMetodologiaGrupoPoblacionalCodigo, ";
            strQuery += " a.maeMetodologiaGrupoPoblacionalValor = :maeMetodologiaGrupoPoblacionalValor, ";
            //2022-06-09 jyperez nuevos campos Decreto 616 y Resolución 925 de 2022
            strQuery += " a.maeSolidariaPorcentajeId = :maeSolidariaPorcentajeId, ";
            strQuery += " a.maeSolidariaPorcentajeCodigo = :maeSolidariaPorcentajeCodigo, ";
            strQuery += " a.maeSolidariaPorcentajeValor = :maeSolidariaPorcentajeValor, ";
            //2022-07-21 jyperez nuevo campo
            strQuery += " a.aceptaContribucionSolidaria = :aceptaContribucionSolidaria, ";
            //2023-06-20 jyperez N48 acuerdo pago suspensión X mora
            strQuery += " a.acuerdoPago = :acuerdoPago, ";
            strQuery += " a.fechaAcuerdoPago = :fechaAcuerdoPago, ";
            //2023-08-16 jyperez N47 madre gestante
            strQuery += " a.usuarioGestante = :usuarioGestante, ";
            strQuery += " a.fechaFinPeriodoGestacion = :fechaFinPeriodoGestacion, ";
            //2023-07-25 jyperez N00_99 incapacidad prolongada
            strQuery += " a.incapacidadProlongada = :incapacidadProlongada, ";
            //2023-07-25 jyperez IS_58 retiro por duplicidad
            strQuery += " a.duplicado = :duplicado, ";
            //2024-03-13 jyperez N00-42 traslado preaprobado
            strQuery += " a.trasladoPreaprobado = :trasladoPreaprobado, ";
            //2024-03-22 jyperez RES 2335
            strQuery += " a.direccionAlternaContacto = :direccionAlternaContacto, ";
            strQuery += " a.nombreContactoEmergencia = :nombreContactoEmergencia, ";
            strQuery += " a.telefonoContactoEmergencia = :telefonoContactoEmergencia, ";
            //2025-08-04 jyperez nuevos campos
            strQuery += " a.direccionGeorefLatitud = :direccionGeorefLatitud, ";
            strQuery += " a.direccionGeorefLongitud = :direccionGeorefLongitud ";
            
            //2022-11-25 jyperez adición nuevos campos habeas data
            if (per.getTratamientoDatosAutoriza() != null) {
                strQuery += ", a.tratamientoDatosAutoriza = :tratamientoDatosAutoriza ";
            }
            if (per.getTratamientDatosFechaHora() != null) {
                strQuery += ", a.tratamientDatosFechaHora = : tratamientDatosFechaHora ";
            }
            // campos objetos 
            if (per.getPrimariaCntPrestadorSedesId() != null) {
                strQuery += ", a.primariaCntPrestadorSedesId.id = " + per.getPrimariaCntPrestadorSedesId().getId() + " ";
            }
            if (per.getPortabilidadCntPrestadorSedesId() != null) {
                strQuery += ", a.portabilidadCntPrestadorSedesId.id = " + per.getPortabilidadCntPrestadorSedesId().getId() + " ";
            }
            if (per.getNacimientoUbicacionesId() != null) {
                strQuery += ", a.nacimientoUbicacionesId.id = " + per.getNacimientoUbicacionesId().getId() + " ";
            }
            if (per.getNacionalidadUbicacionesId() != null) {
                strQuery += ", a.nacionalidadUbicacionesId.id = " + per.getNacionalidadUbicacionesId().getId() + " ";
            }
            if (per.getAfiliacionUbicacionesId() != null) {
                strQuery += ", a.afiliacionUbicacionesId.id = " + per.getAfiliacionUbicacionesId().getId() + " ";
            }
            //2023-06-15 jyperez N43 nacimiento ubicación
            if (per.getGnUbicacionesLugarNacimientoId() != null) {
                strQuery += ", a.gnUbicacionesLugarNacimientoId.id = " + per.getGnUbicacionesLugarNacimientoId().getId() + " ";
            } else {
                strQuery += ", a.gnUbicacionesLugarNacimientoId.id = null ";
            }
            if (per.getResidenciaUbicacionId() != null) {
                strQuery += ", a.residenciaUbicacionId.id = " + per.getResidenciaUbicacionId().getId() + " ";
            }
            //2025-03-13 jyperez nuvo objeto ubicacionBarrio
            if (per.getGnUbicacionBarriosId() != null ) {
                strQuery += ", a.gnUbicacionBarriosId.id = " + per.getGnUbicacionBarriosId().getId() + " ";
            }
            //2025-07-24 jyperez nuevo campo ubicacion actual afiliado
            if (per.getGnUbicacionActualAfiliado()!= null) {
                strQuery += ", a.gnUbicacionActualAfiliado.id = " + per.getGnUbicacionActualAfiliado().getId() + " ";
            } else {
                strQuery += ", a.gnUbicacionActualAfiliado.id = null ";
            }
            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(per);
            query.executeUpdate();
            //session.beginTransaction().commit();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AsegAfiliado eliminar(int id) throws Exception {
        AsegAfiliado obj = null;
        try {
            AsegAfiliados ent = getEntityManager().find(AsegAfiliados.class, id);
            if (ent != null) {
                obj = castEntidadNegocioLargo(ent);
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
    public List<AsegAfiliado> consultarTodos() throws Exception {
        List<AsegAfiliado> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegAfiliados p "
                    + "ORDER BY p.fechaHoraCrea DESC";
            List<AsegAfiliados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegAfiliados per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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

    public static AsegAfiliado castEntidadNegocioLargo(AsegAfiliados per) {
        AsegAfiliado obj = new AsegAfiliado();
        obj.setId(per.getId());
        obj.setIdAfiliado(per.getIdAfiliado());
        obj.setSerialBdua(per.getSerialBdua());
        obj.setRegistroBdua(per.getRegistroBdua());
        obj.setPrimerNombre(per.getPrimerNombre());
        obj.setSegundoNombre(per.getSegundoNombre());
        obj.setPrimerApellido(per.getPrimerApellido());
        obj.setSegundoApellido(per.getSegundoApellido());
        obj.setFechaNacimiento(per.getFechaNacimiento());
        obj.setGenero(per.getGenero());
        obj.setMaeGeneroId(per.getMaeGeneroId());
        obj.setMaeGeneroCodigo(per.getMaeGeneroCodigo());
        obj.setMaeGeneroValor(per.getMaeGeneroValor());
        //2023-06-15 jyperez N45 genero identificacion
        obj.setMaeGeneroIdentificacionId(per.getMaeGeneroIdentificacionId());
        obj.setMaeGeneroIdentificacionCodigo(per.getMaeGeneroIdentificacionCodigo());
        obj.setMaeGeneroIdentificacionValor(per.getMaeGeneroIdentificacionValor());
        obj.setFechaExpedicionCedula(per.getFechaExpedicionCedula());
        obj.setMaeTipoDocumento(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setNumeroDocumento(per.getNumeroDocumento());
        obj.setMaeTipoDocumentoCf(per.getMaeTipoDocumentoCfId());
        obj.setMaeTipoDocumentoCfCodigo(per.getMaeTipoDocumentoCfCodigo());
        obj.setMaeTipoDocumentoCfValor(per.getMaeTipoDocumentoCfValor());
        obj.setNumeroDocumentoCf(per.getNumeroDocumentoCf());
        obj.setFechaAfiliacionSgsss(per.getFechaAfiliacionSgsss());
        obj.setFechaAfiliacionEps(per.getFechaAfiliacionEps());
        obj.setFechaEgresoEps(per.getFechaEgresoEps());
        obj.setFechaCambioEstadoEps(per.getFechaCambioEstadoEps());
        obj.setTipoBeneficiario(per.getTipoBeneficiario());
        obj.setMaeTipoAfiliadoId(per.getMaeTipoAfiliadoId());
        obj.setMaeTipoAfiliadoCodigo(per.getMaeTipoAfiliadoCodigo());
        obj.setMaeTipoAfiliadoValor(per.getMaeTipoAfiliadoValor());
        obj.setMaeEstadoAfiliacion(per.getMaeEstadoAfiliacionId());
        obj.setMaeEstadoAfiliacionCodigo(per.getMaeEstadoAfiliacionCodigo());
        obj.setMaeEstadoAfiliacionValor(per.getMaeEstadoAfiliacionValor());
        obj.setMaeOrigenAfiliado(per.getMaeOrigenAfiliadoId());
        obj.setMaeOrigenAfiliadoCodigo(per.getMaeOrigenAfiliadoCodigo());
        obj.setMaeOrigenAfiliadoValor(per.getMaeOrigenAfiliadoValor());
        obj.setParentescoCotizante(per.getParentescoCotizante());
        obj.setMaeParentescoCotizanteId(per.getMaeParentescoCotizanteId());
        obj.setMaeParentescoCotizanteCodigo(per.getMaeParentescoCotizanteCodigo());
        obj.setMaeParentescoCotizanteValor(per.getMaeParentescoCotizanteValor());
        obj.setAutorizaEnvioSms(per.getAutorizaEnvioSms());
        obj.setAutorizaEnvioEmail(per.getAutorizaEnvioEmail());
        obj.setTelefonoFijo(per.getTelefonoFijo());
        obj.setTelefonoMovil(per.getTelefonoMovil());
        obj.setEmail(per.getEmail());
        obj.setZona(per.getZona());
        obj.setMaeZonaId(per.getMaeZonaId());
        obj.setMaeZonaCodigo(per.getMaeZonaCodigo());
        obj.setMaeZonaValor(per.getMaeZonaValor());
        obj.setDireccionResidencia(per.getDireccionResidencia());
        obj.setBarrio(per.getBarrio());
        obj.setRegimen(per.getRegimen());
        obj.setNivelSisben(per.getNivelSisben());
        //2021-05-20 jyperez nuevos campos
        obj.setMaeRegimenId(per.getMaeRegimenId());
        obj.setMaeRegimenCodigo(per.getMaeRegimenCodigo());
        obj.setMaeRegimenValor(per.getMaeRegimenValor());
        obj.setMaeNivelSisbenId(per.getMaeNivelSisbenId());
        obj.setMaeNivelSisbenCodigo(per.getMaeNivelSisbenCodigo());
        obj.setMaeNivelSisbenValor(per.getMaeNivelSisbenValor());
        if (per.getPuntajeSisben() != null) {
            obj.setPuntajeSisben(per.getPuntajeSisben().doubleValue());
        }
        obj.setFichaSisben(per.getFichaSisben());
        obj.setFechaSisben(per.getFechaSisben());
        obj.setCategoriaIbc(per.getCategoriaIbc());
        obj.setMaeCategoriaIbcId(per.getMaeCategoriaIbcId());
        obj.setMaeCategoriaIbcCodigo(per.getMaeCategoriaIbcCodigo());
        obj.setMaeCategoriaIbcValor(per.getMaeCategoriaIbcValor());
        obj.setTienePortabilidad(per.getTienePortabilidad());
        obj.setFechaPortabilidad(per.getFechaPortabilidad());
        obj.setTipoCotizante(per.getTipoCotizante());
        obj.setMaeTipoCotizanteId(per.getMaeTipoCotizanteId());
        obj.setMaeTipoCotizanteCodigo(per.getMaeTipoCotizanteCodigo());
        obj.setMaeTipoCotizanteValor(per.getMaeTipoCotizanteValor());
        obj.setDiscapacidad(per.getDiscapacidad());
        obj.setTipoDiscapacidad(per.getTipoDiscapacidad());
        obj.setMaeTipoDiscapacidadId(per.getMaeTipoDiscapacidadId());
        obj.setMaeTipoDiscapacidadCodigo(per.getMaeTipoDiscapacidadCodigo());
        obj.setMaeTipoDiscapacidadValor(per.getMaeTipoDiscapacidadValor());
        obj.setCondicionDiscapacidad(per.getCondicionDiscapacidad());
        obj.setMaeCondicionDiscapacidadId(per.getMaeDiscapacidadCondicionId());
        obj.setMaeCondicionDiscapacidadCodigo(per.getMaeDiscapacidadCondicionCodigo());
        obj.setMaeCondicionDiscapacidadValor(per.getMaeDiscapacidadCondicionValor());
        obj.setFechaIniciodiscapacidad(per.getFechaInicioDiscapacidad());
        obj.setFechaFinDiscapacidad(per.getFechaFinDiscapacidad());
        obj.setMaeGrupoPoblacional(per.getMaeGrupoPoblacionalId());
        obj.setMaeGrupoPoblacionalCodigo(per.getMaeGrupoPoblacionalCodigo());
        obj.setMaeGrupoPoblacionalValor(per.getMaeGrupoPoblacionalValor());
        obj.setVictima(per.getVictima());
        obj.setEtnia(per.getEtnia());
        obj.setMaeEtniaId(per.getMaeEtniaId());
        obj.setMaeEtniaCodigo(per.getMaeEtniaCodigo());
        obj.setMaeEtniaValor(per.getMaeEtniaValor());
        obj.setMaeComunidadEtniaId(per.getMaeComunidadEtniaId());
        obj.setMaeComunidadEtniaCodigo(per.getMaeComunidadEtniaCodigo());
        obj.setMaeComunidadEtniaValor(per.getMaeComunidadEtniaValor());
        obj.setMaeCausaNovedad(per.getMaeCausaNovedadId());
        obj.setMaeCausaNovedadCodigo(per.getMaeCausaNovedadCodigo());
        obj.setMaeCausaNovedadValor(per.getMaeCausaNovedadValor());
        obj.setFechaReactivacion(per.getFechaReactivacion());
        obj.setEstadoCivil(per.getEstadoCivil());
        obj.setMaeEstadoCivilId(per.getMaeEstadoCivilId());
        obj.setMaeEstadoCivilCodigo(per.getMaeEstadoCivilCodigo());
        obj.setMaeEstadoCivilValor(per.getMaeEstadoCivilValor());
        obj.setFechaMovilidad(per.getFechaMovilidad());
        obj.setModeloLiquidacion(per.getModeloLiquidacion());
        obj.setMaeModeloLiquidacionId(per.getMaeModeloLiquidacionId());
        obj.setMaeModeloLiquidacionCodigo(per.getMaeModeloLiquidacionCodigo());
        obj.setMaeModeloLiquidacionValor(per.getMaeModeloLiquidacionValor());
        obj.setMaeTipoDocumentoAportante(per.getMaeTipoDocumentoAportanteId());
        obj.setMaeTipoDocumentoAportanteCodigo(per.getMaeTipoDocumentoAportanteCodigo());
        obj.setMaeTipoDocumentoAportanteValor(per.getMaeTipoDocumentoAportanteValor());
        obj.setNumeroDocumentoAportante(per.getNumeroDocumentoAportante());
        obj.setMaeActividadEconomica(per.getMaeActividadEconomicaId());
        obj.setMaeActividadEconomicaCodigo(per.getMaeActividadEconomicaCodigo());
        obj.setMaeActividadEconomicaValor(per.getMaeActividadEconomicaValor());
        obj.setMaeArl(per.getMaeArlId());
        obj.setMaeArlCodigo(per.getMaeArlCodigo());
        obj.setMaeArlValor(per.getMaeArlValor());
        obj.setMaeAfp(per.getMaeAfpId());
        obj.setMaeAfpCodigo(per.getMaeAfpCodigo());
        obj.setMaeAfpValor(per.getMaeAfpValor());
        obj.setMaeCajaCompensacion(per.getMaeCajaCompensacionId());
        obj.setMaeCajaCompensacionCodigo(per.getMaeCajaCompensacionCodigo());
        obj.setMaeCajaCompensacionValor(per.getMaeCajaCompensacionValor());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setObservacionNovedad(per.getObservacion());
        obj.setSincronizado(per.getSincronizado());
        //2021-05-03 jyperez nuevo campo
        obj.setMaeGrupoSisbenId(per.getMaeGrupoSisbenId());
        obj.setMaeGrupoSisbenCodigo(per.getMaeGrupoSisbenCodigo());
        obj.setMaeGrupoSisbenValor(per.getMaeGrupoSisbenValor());
        //2022-04-25 jyperez nuevos campos
        obj.setMaeSubGrupoSisbenId(per.getMaeSubGrupoSisbenId());
        obj.setMaeSubGrupoSisbenCodigo(per.getMaeSubGrupoSisbenCodigo());
        obj.setMaeSubGrupoSisbenValor(per.getMaeSubGrupoSisbenValor());
        //2021-05-11 jyperez nuevo campo para el soundex
        obj.setCodigoFonetico(per.getCodigoFonetico());
        obj.setOrigenUltimoRegistro(per.getOrigenUltimoRegistro());
        //2022-02-21 jyperez nuevo campo resolución 2153 de 2021 de la Adres
        obj.setMaeMetodologiaGrupoPoblacionalId(per.getMaeMetodologiaGrupoPoblacionalId());
        obj.setMaeMetodologiaGrupoPoblacionalCodigo(per.getMaeMetodologiaGrupoPoblacionalCodigo());
        obj.setMaeMetodologiaGrupoPoblacionalValor(per.getMaeMetodologiaGrupoPoblacionalValor());
        //2022-06-09 jyperez nuevos campos Decreto 616 y Resolución 925 de 2022
        obj.setMaeSolidariaPorcentajeId(per.getMaeSolidariaPorcentajeId());
        obj.setMaeSolidariaPorcentajeCodigo(per.getMaeSolidariaPorcentajeCodigo());
        obj.setMaeSolidariaPorcentajeValor(per.getMaeSolidariaPorcentajeValor());
        //2022-07-21 jyperez nuevo campo
        obj.setAceptaContribucionSolidaria(per.getAceptaContribucionSolidaria());
        //2022-10-31 jyperez nuevo campo
        obj.setFechaSuspension(per.getFechaSuspensionEps());
        //2022-11-25 jyperez nuevos campos
        obj.setTratamientoDatosAutoriza(per.getTratamientoDatosAutoriza());
        obj.setTratamientoDatosFechaHora(per.getTratamientDatosFechaHora());
        //2023-06-15 jyperez N48 acuerdo de pago por suspensión X mora
        obj.setAcuerdoPago(per.getAcuerdoPago());
        obj.setFechaAcuerdoPago(per.getFechaAcuerdoPago());
        //2023-07-25 jyperez nuevo campo
        obj.setIncapacidadProlongada(per.getIncapacidadProlongada());
        //2023-08-16 jyperez N47 madre gestante
        obj.setUsuarioGestante(per.getUsuarioGestante());
        obj.setFechaFinPeriodoGestacion(per.getFechaFinPeriodoGestacion());
        //2024-01-10 jyperez Is 58 retiro por duplicado
        //2024-02-29 jyperez se realiza ajuste para actualizar el valor duplicado de nulo a cero
        //2024-03-05 rpalacic Corrección de forma para evitar dato nulo
        obj.setDuplicado((per.getDuplicado() == null) ? false : per.getDuplicado());
        //2024-03-13 jyperez N00-42 traslado preaprobado
        obj.setTrasladoPreaprobado((per.getTrasladoPreaprobado() == null) ? false : per.getTrasladoPreaprobado());
        //2024-03-22 jyperez RES 2335
        obj.setDireccionAlternaContacto(per.getDireccionAlternaContacto());
        obj.setNombreContactoEmergencia(per.getNombreContactoEmergencia());
        obj.setTelefonoContactoEmergencia(per.getTelefonoContactoEmergencia());
        //2024-09-23 jyperez nuevos campos portabilidad
        obj.setMaeTipoPortabilidadId(per.getMaeTipoPortabilidadId());
        obj.setMaeTipoPortabilidadCodigo(per.getMaeTipoPortabilidadCodigo());
        obj.setMaeTipoPortabilidadValor(per.getMaeTipoPortabilidadValor());
        obj.setMaeMotivoPortabilidadId(per.getMaeMotivoPortabilidadId());
        obj.setMaeMotivoPortabilidadCodigo(per.getMaeMotivoPortabilidadCodigo());
        obj.setMaeMotivoPortabilidadValor(per.getMaeMotivoPortabilidadValor());
        obj.setMaeOrigenSolicitudPortabilidadId(per.getMaeOrigenSolicitudPortabilidadId());
        obj.setMaeOrigenSolicitudPortabilidadCodigo(per.getMaeOrigenSolicitudPortabilidadCodigo());
        obj.setMaeOrigenSolicitudPortabilidadValor(per.getMaeOrigenSolicitudPortabilidadValor());
        obj.setPeriodoInicialPortabilidad(per.getPeriodoInicialPortabilidad());
        obj.setPeriodoFinalPortabilidad(per.getPeriodoFinalPortabilidad());
        obj.setTelefonoContactoPortabilidad(per.getTelefonoContactoPortabilidad());
        obj.setDireccionPortabilidad(per.getDireccionPortabilidad());
        obj.setCorreoElectronicoPortabilidad(per.getCorreoElectronicoPortabilidad());
        obj.setObservacionPortabilidad(per.getObservacionPortabilidad());
        //2025-08-04 jyperez nuevos campos georeferenciación
        obj.setDireccionGeorefLongitud(per.getDireccionGeorefLongitud());
        obj.setDireccionGeorefLatitud(per.getDireccionGeorefLatitud());
        if (per.getPrimariaCntPrestadorSedesId() != null) {
            obj.setPrimariaPrestadorSede(castPrestadorSedesEntidadNegocio(per.getPrimariaCntPrestadorSedesId()));
        }
        if (per.getPortabilidadCntPrestadorSedesId() != null) {
            obj.setPortabilidadPrestadorSede(castPrestadorSedesEntidadNegocio(per.getPortabilidadCntPrestadorSedesId()));
        }
        if (per.getNacionalidadUbicacionesId() != null) {
            obj.setNacionalidadUbicacion(castUbicacionEntidadNegocio(per.getNacionalidadUbicacionesId()));
        }
        if (per.getNacimientoUbicacionesId() != null) {
            obj.setNacimientoUbicacion(castUbicacionEntidadNegocio(per.getNacimientoUbicacionesId()));
        }
        if (per.getAfiliacionUbicacionesId() != null) {
            obj.setAfiliacionUbicacion(castUbicacionEntidadNegocio(per.getAfiliacionUbicacionesId()));
        }
        if (per.getResidenciaUbicacionId() != null) {
            obj.setResidenciaUbicacion(castUbicacionEntidadNegocio(per.getResidenciaUbicacionId()));
        }
        //2023-06-15 jyperez N46 nacimiento ubicacion
        if (per.getGnUbicacionesLugarNacimientoId() != null) {
            obj.setGnUbicacionesLugarNacimientoId(castUbicacionEntidadNegocio(per.getGnUbicacionesLugarNacimientoId()));
        }
        //2025-03-13 jyperez nuevo objeto ubicacionBarrio
        if (per.getGnUbicacionBarriosId() != null) {
            obj.setUbicacionBarrio(castUbicacionBarrioEntidadNegocio(per.getGnUbicacionBarriosId()));
        }
        //2025-07-24 jyperez nuevo objeto ubicacion actual afiliado
        if (per.getGnUbicacionActualAfiliado()!= null) {
            obj.setGnUbicacionActualAfiliado(castUbicacionEntidadNegocio(per.getGnUbicacionActualAfiliado()));
        }
        //2021-05-27 jyperez adicionamos cast lista de Contactos
        if (per.getAsegAfiliadoContactosList() != null) {
            List<AsegAfiliadoContacto> listaContactos = new ArrayList<>();
            List<AsegAfiliadoContactoConsulta> listaContactosconsulta = new ArrayList<>();
            for (AsegAfiliadoContactos contacto : per.getAsegAfiliadoContactosList()) {
                listaContactos.add(castAfiliadoContactoEntidadNegocio(contacto));
                listaContactosconsulta.add(castEntidadNegocioAfiliado(contacto));
            }
            obj.setListaAsegAfiliadoContacto(listaContactos);
            obj.setListaContactoAfiliado(listaContactosconsulta);
//            per.getAsegAfiliadoContactosList().forEach(contacto -> {
//                listaContactos.add(castAfiliadoContactoEntidadNegocio(contacto));
//            });
//            obj.setListaAsegAfiliadoContacto(listaContactos);
        }
        //2022-09-08 jyperez se adiciona funcionalidad para leer el histórico de documentos del afiliado
        if (per.getAsegAfiliadoDocumentosList() != null) {
            String dato = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (AsegAfiliadoDocumentos ad : per.getAsegAfiliadoDocumentosList()) {
                dato = dato + ad.getMaeTipoDocumentoCodigo() + " " + ad.getNumeroDocumento() + " ";
                if (ad.getFechaExpedicion() != null) {
                    dato = dato + sdf.format(ad.getFechaExpedicion()) + " \n";
                } else {
                    dato = dato + "\n";
                }
            }
            obj.setHistoricoDocumentoAfiliado(dato);
        }
        //2024-12-11 jyperez obtenemos la lista de radicado novedades
        if (per.getAsegRadicadoNovedadesList() != null ) {
            AsegRadicadoNovedad arn;
            List<AsegRadicadoNovedad> lista = new ArrayList();
            for (AsegRadicadoNovedades aux: per.getAsegRadicadoNovedadesList()) {
                arn = new AsegRadicadoNovedad();
                arn.setId(aux.getId());
                arn.setAsegAfiliado(new AsegAfiliado(per.getId()));
                arn.setSoporteNovedad(aux.getSoporteNovedad());
                arn.setFechaHoraCrea(aux.getFechaHoraCrea());
                arn.setUsuarioCrea(aux.getUsuarioCrea());
                arn.setTerminalCrea(aux.getTerminalCrea());
                lista.add(arn);
            }
            obj.setListaAsegRadicadoNovedades(lista);
        }
        //2023-02-02 jyperez FALLO PASO A PDN 02-02-2023 ajuste isaac
        //obj.setResidenciaUbicacion(new Ubicacion(per.getResidenciaUbicacionId().getId()));
        return obj;
    }

    public static AsegAfiliadoContacto castAfiliadoContactoEntidadNegocio(AsegAfiliadoContactos per) {
        AsegAfiliadoContacto obj = new AsegAfiliadoContacto();
        obj.setId(per.getId());
        obj.setNumeroContacto(per.getNumeroContacto());
        obj.setMaeTipoContactoId(per.getMaeTipoContactoId());
        obj.setMaeTipoContactoCodigo(per.getMaeTipoContactoCodigo());
        obj.setMaeTipoContactoValor(per.getMaeTipoContactoValor());
        obj.setObservacion(per.getObservacion());
        obj.setActivo(per.getActivo());
        //objetos
        if (per.getAsegAfiliadosId() != null) {
            obj.setAsegAfiliado(new AsegAfiliado(per.getAsegAfiliadosId().getId(), per.getAsegAfiliadosId().getIdAfiliado(),
                    per.getAsegAfiliadosId().getPrimerNombre(), per.getAsegAfiliadosId().getSegundoNombre(), per.getAsegAfiliadosId().getPrimerApellido(), per.getAsegAfiliadosId().getSegundoApellido()));
        }
        // auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        return obj;
    }

    public static Ubicacion castUbicacionEntidadNegocio(GnUbicaciones per) {
        Ubicacion obj = new Ubicacion();
        if (per != null) {
            obj.setId(per.getId());
            obj.setNombre(per.getNombre());
            obj.setTipo(per.getTipo());
            obj.setPrefijo(per.getPrefijo());
            // 2020-10-21 jyperez ajuste para soportar datos de la ubicación padre - Req Validacion Derechos de Afiliado
            if (per.getGnUbicacionesId() != null) {
                obj.setUbicacionPadre(new Ubicacion(null, per.getGnUbicacionesId().getId(), per.getGnUbicacionesId().getTipo(), per.getGnUbicacionesId().getCodigoPostal(), per.getGnUbicacionesId().getNombre()));
                obj.getUbicacionPadre().setPrefijo(per.getGnUbicacionesId().getPrefijo());
            }
        }
        return obj;
    }
    
    public static GnUbicacionBarrio castUbicacionBarrioEntidadNegocio(GnUbicacionBarrios entidad) {
        GnUbicacionBarrio negocio = new GnUbicacionBarrio();
        negocio.setId(entidad.getId());
        negocio.setGnUbicacionesId(entidad.getGnUbicacionesId());
        negocio.setCodigoBarrio("" + entidad.getCodigoBarrio());
        negocio.setNombre(entidad.getNombre());
        negocio.setCodigoComuna("" + entidad.getCodigoComuna());
        negocio.setComuna(entidad.getComuna());
        //Auditoria
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        return negocio;
    }

    public static CntPrestadorSede castPrestadorSedesEntidadNegocio(CntPrestadorSedes per) {
        CntPrestadorSede obj = new CntPrestadorSede();
        if (per != null) {
            obj.setId(per.getId());
            obj.setUbicacionId(per.getUbicacionId());
            obj.setCodigoPrestador(per.getCodigoPrestador());
            obj.setDireccion(per.getDireccion());
            obj.setNombreSede(per.getNombre());
            obj.setCodigoSede(per.getCodigo());
            obj.setCodigoHabilitacionSede(per.getCodigoHabilitacion());
            obj.setZonaPrecedencia(per.getZonaPrecedencia());
            obj.setEstadoSede(per.getEstadoSede());
            obj.setNivelAtencion(per.getNivelAtencion());
            obj.setClasePrestador(per.getMaeClasePrestadorId());
            obj.setFax(per.getFax());
            obj.setTelefonoCitas(per.getTelefonoCitas());
            obj.setCorreoElectronico(per.getCorreoElectronico());
            obj.setTelefonoAdministrativo(per.getTelefonoAdministrativo());
            obj.setCapitacion(per.getCapitacion());
            if (per.getCntPrestadoresId() != null) {
                obj.setCntPrestador(new CntPrestador(per.getCntPrestadoresId().getId(), per.getCntPrestadoresId().getRazonSocial()));
            }
        }
        return obj;
    }

    public static AsegAfiliados castNegocioEntidadLargo(AsegAfiliado obj) {
        AsegAfiliados per = new AsegAfiliados();
        per.setId(obj.getId());
        per.setIdAfiliado(obj.getIdAfiliado());
        per.setSerialBdua(obj.getSerialBdua());
        per.setRegistroBdua(obj.isRegistroBdua());
        per.setPrimerNombre(obj.getPrimerNombre());
        per.setSegundoNombre(obj.getSegundoNombre());
        per.setPrimerApellido(obj.getPrimerApellido());
        per.setSegundoApellido(obj.getSegundoApellido());
        per.setFechaNacimiento(obj.getFechaNacimiento());
        per.setGenero(obj.getGenero());
        per.setMaeGeneroId(obj.getMaeGeneroId());
        per.setMaeGeneroCodigo(obj.getMaeGeneroCodigo());
        per.setMaeGeneroValor(obj.getMaeGeneroValor());
        //2023-06-15 jyperez N45 genero identificación
        per.setMaeGeneroIdentificacionId(obj.getMaeGeneroIdentificacionId());
        per.setMaeGeneroIdentificacionCodigo(obj.getMaeGeneroIdentificacionCodigo());
        per.setMaeGeneroIdentificacionValor(obj.getMaeGeneroIdentificacionValor());
        per.setFechaExpedicionCedula(obj.getFechaExpedicionCedula());
        per.setMaeTipoDocumentoId(obj.getMaeTipoDocumento());
        per.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
        per.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
        per.setNumeroDocumento(obj.getNumeroDocumento());
        per.setMaeTipoDocumentoCfId(obj.getMaeTipoDocumentoCf());
        per.setMaeTipoDocumentoCfCodigo(obj.getMaeTipoDocumentoCfCodigo());
        per.setMaeTipoDocumentoCfValor(obj.getMaeTipoDocumentoCfValor());
        per.setNumeroDocumentoCf(obj.getNumeroDocumentoCf());
        per.setFechaAfiliacionSgsss(obj.getFechaAfiliacionSgsss());
        per.setFechaAfiliacionEps(obj.getFechaAfiliacionEps());
        per.setFechaEgresoEps(obj.getFechaEgresoEps());
        per.setFechaCambioEstadoEps(obj.getFechaCambioEstadoEps());
        per.setTipoBeneficiario(obj.getTipoBeneficiario());
        per.setMaeTipoAfiliadoId(obj.getMaeTipoAfiliadoId());
        per.setMaeTipoAfiliadoCodigo(obj.getMaeTipoAfiliadoCodigo());
        per.setMaeTipoAfiliadoValor(obj.getMaeTipoAfiliadoValor());
        per.setMaeEstadoAfiliacionId(obj.getMaeEstadoAfiliacion());
        per.setMaeEstadoAfiliacionCodigo(obj.getMaeEstadoAfiliacionCodigo());
        per.setMaeEstadoAfiliacionValor(obj.getMaeEstadoAfiliacionValor());
        per.setMaeOrigenAfiliadoId(obj.getMaeOrigenAfiliado());
        per.setMaeOrigenAfiliadoCodigo(obj.getMaeOrigenAfiliadoCodigo());
        per.setMaeOrigenAfiliadoValor(obj.getMaeOrigenAfiliadoValor());
        per.setParentescoCotizante(obj.getParentescoCotizante());
        per.setMaeParentescoCotizanteId(obj.getMaeParentescoCotizanteId());
        per.setMaeParentescoCotizanteCodigo(obj.getMaeParentescoCotizanteCodigo());
        per.setMaeParentescoCotizanteValor(obj.getMaeParentescoCotizanteValor());
        per.setAutorizaEnvioSms(obj.getAutorizaEnvioSms());
        per.setAutorizaEnvioEmail(obj.getAutorizaEnvioEmail());
        per.setTelefonoFijo(obj.getTelefonoFijo());
        per.setTelefonoMovil(obj.getTelefonoMovil());
        per.setEmail(obj.getEmail());
        per.setZona(obj.getZona());
        per.setMaeZonaId(obj.getMaeZonaId());
        per.setMaeZonaCodigo(obj.getMaeZonaCodigo());
        per.setMaeZonaValor(obj.getMaeZonaValor());
        per.setDireccionResidencia(obj.getDireccionResidencia());
        per.setBarrio(obj.getBarrio());
        per.setRegimen(obj.getRegimen());
        per.setNivelSisben(obj.getNivelSisben());
        //2021-05-20 jyperez nuevos campos
        per.setMaeRegimenId(obj.getMaeRegimenId());
        per.setMaeRegimenCodigo(obj.getMaeRegimenCodigo());
        per.setMaeRegimenValor(obj.getMaeRegimenValor());
        per.setMaeNivelSisbenId(obj.getMaeNivelSisbenId());
        per.setMaeNivelSisbenCodigo(obj.getMaeNivelSisbenCodigo());
        per.setMaeNivelSisbenValor(obj.getMaeNivelSisbenValor());
        if (obj.getPuntajeSisben() != null) {
            //per.setPuntajeSisben(obj.getPuntajeSisben().floatValue());
            per.setPuntajeSisben(new BigDecimal(obj.getPuntajeSisben()));
        }
        per.setFichaSisben(obj.getFichaSisben());
        per.setFechaSisben(obj.getFechaSisben());
        per.setCategoriaIbc(obj.getCategoriaIbc());
        per.setMaeCategoriaIbcId(obj.getMaeCategoriaIbcId());
        per.setMaeCategoriaIbcCodigo(obj.getMaeCategoriaIbcCodigo());
        per.setMaeCategoriaIbcValor(obj.getMaeCategoriaIbcValor());
        per.setTienePortabilidad(obj.getTienePortabilidad());
        per.setFechaPortabilidad(obj.getFechaPortabilidad());
        per.setTipoCotizante(obj.getTipoCotizante());
        per.setMaeTipoCotizanteId(obj.getMaeTipoCotizanteId());
        per.setMaeTipoCotizanteCodigo(obj.getMaeTipoCotizanteCodigo());
        per.setMaeTipoCotizanteValor(obj.getMaeTipoCotizanteValor());
        per.setDiscapacidad(obj.isDiscapacidad());
        per.setTipoDiscapacidad(obj.getTipoDiscapacidad());
        per.setMaeTipoDiscapacidadId(obj.getMaeTipoDiscapacidadId());
        per.setMaeTipoDiscapacidadCodigo(obj.getMaeTipoDiscapacidadCodigo());
        per.setMaeTipoDiscapacidadValor(obj.getMaeTipoDiscapacidadValor());
        per.setCondicionDiscapacidad(obj.getCondicionDiscapacidad());
        per.setMaeDiscapacidadCondicionId(obj.getMaeCondicionDiscapacidadId());
        per.setMaeDiscapacidadCondicionCodigo(obj.getMaeCondicionDiscapacidadCodigo());
        per.setMaeDiscapacidadCondicionValor(obj.getMaeCondicionDiscapacidadValor());
        per.setFechaInicioDiscapacidad(obj.getFechaIniciodiscapacidad());
        per.setFechaFinDiscapacidad(obj.getFechaFinDiscapacidad());
        per.setDuplicado(obj.getDuplicado());
        per.setMaeGrupoPoblacionalId(obj.getMaeGrupoPoblacional());
        per.setMaeGrupoPoblacionalCodigo(obj.getMaeGrupoPoblacionalCodigo());
        per.setMaeGrupoPoblacionalValor(obj.getMaeGrupoPoblacionalValor());
        per.setVictima(obj.getVictima());
        per.setEtnia(obj.getEtnia());
        per.setMaeEtniaId(obj.getMaeEtniaId());
        per.setMaeEtniaCodigo(obj.getMaeEtniaCodigo());
        per.setMaeEtniaValor(obj.getMaeEtniaValor());
        per.setMaeComunidadEtniaId(obj.getMaeComunidadEtniaId());
        per.setMaeComunidadEtniaCodigo(obj.getMaeComunidadEtniaCodigo());
        per.setMaeComunidadEtniaValor(obj.getMaeComunidadEtniaValor());
        per.setMaeCausaNovedadId(obj.getMaeCausaNovedad());
        per.setMaeCausaNovedadCodigo(obj.getMaeCausaNovedadCodigo());
        per.setMaeCausaNovedadValor(obj.getMaeCausaNovedadValor());
        per.setFechaReactivacion(obj.getFechaReactivacion());
        per.setEstadoCivil(obj.getEstadoCivil());
        per.setMaeEstadoCivilId(obj.getMaeEstadoCivilId());
        per.setMaeEstadoCivilCodigo(obj.getMaeEstadoCivilCodigo());
        per.setMaeEstadoCivilValor(obj.getMaeEstadoCivilValor());
        per.setFechaMovilidad(obj.getFechaMovilidad());
        per.setModeloLiquidacion(obj.getModeloLiquidacion());
        per.setMaeModeloLiquidacionId(obj.getMaeModeloLiquidacionId());
        per.setMaeModeloLiquidacionCodigo(obj.getMaeModeloLiquidacionCodigo());
        per.setMaeModeloLiquidacionValor(obj.getMaeModeloLiquidacionValor());
        per.setMaeTipoDocumentoAportanteId(obj.getMaeTipoDocumentoAportante());
        per.setMaeTipoDocumentoAportanteCodigo(obj.getMaeTipoDocumentoAportanteCodigo());
        per.setMaeTipoDocumentoAportanteValor(obj.getMaeTipoDocumentoAportanteValor());
        per.setNumeroDocumentoAportante(obj.getNumeroDocumentoAportante());
        per.setMaeActividadEconomicaId(obj.getMaeActividadEconomica());
        per.setMaeActividadEconomicaCodigo(obj.getMaeActividadEconomicaCodigo());
        per.setMaeActividadEconomicaValor(obj.getMaeActividadEconomicaValor());
        per.setMaeArlId(obj.getMaeArl());
        per.setMaeArlCodigo(obj.getMaeArlCodigo());
        per.setMaeArlValor(obj.getMaeArlValor());
        per.setMaeAfpId(obj.getMaeAfp());
        per.setMaeAfpCodigo(obj.getMaeAfpCodigo());
        per.setMaeAfpValor(obj.getMaeAfpValor());
        per.setMaeCajaCompensacionId(obj.getMaeCajaCompensacion());
        per.setMaeCajaCompensacionCodigo(obj.getMaeCajaCompensacionCodigo());
        per.setMaeCajaCompensacionValor(obj.getMaeCajaCompensacionValor());
        per.setObservacion(obj.getObservacionNovedad());
        per.setSincronizado(obj.getSincronizado());
        //2021-05-03 jyperez nuevo campo
        per.setMaeGrupoSisbenId(obj.getMaeGrupoSisbenId());
        per.setMaeGrupoSisbenCodigo(obj.getMaeGrupoSisbenCodigo());
        per.setMaeGrupoSisbenValor(obj.getMaeGrupoSisbenValor());
        //2022-04-25 jyperez nuevos campos
        per.setMaeSubGrupoSisbenId(obj.getMaeSubGrupoSisbenId());
        per.setMaeSubGrupoSisbenCodigo(obj.getMaeSubGrupoSisbenCodigo());
        per.setMaeSubGrupoSisbenValor(obj.getMaeSubGrupoSisbenValor());
        //2022-02-23 jyperez nuevos campos
        per.setMaeMetodologiaGrupoPoblacionalId(obj.getMaeMetodologiaGrupoPoblacionalId());
        per.setMaeMetodologiaGrupoPoblacionalCodigo(obj.getMaeMetodologiaGrupoPoblacionalCodigo());
        per.setMaeMetodologiaGrupoPoblacionalValor(obj.getMaeMetodologiaGrupoPoblacionalValor());
        //2022-06-09 jyperez nuevos campos Decreto 616 y Resolución 925 de 2022
        per.setMaeSolidariaPorcentajeId(obj.getMaeSolidariaPorcentajeId());
        per.setMaeSolidariaPorcentajeCodigo(obj.getMaeSolidariaPorcentajeCodigo());
        per.setMaeSolidariaPorcentajeValor(obj.getMaeSolidariaPorcentajeValor());
        //2021-05-11 jyperez nuevo campo para el soundex
        per.setCodigoFonetico(calcularCodigoFonetico(obj.getPrimerNombre(), obj.getSegundoNombre(), obj.getPrimerApellido(), obj.getSegundoApellido()));
        per.setOrigenUltimoRegistro(obj.getOrigenUltimoRegistro());
        //2022-07-21 jyperez nuevo campo
        per.setAceptaContribucionSolidaria(obj.getAceptaContribucionSolidaria());
        //2022-10-31 jyperez nuevo campo
        per.setFechaSuspensionEps(obj.getFechaSuspension());
        //2022-11-25 jyperez nuevos campos
        per.setTratamientoDatosAutoriza(obj.getTratamientoDatosAutoriza());
        per.setTratamientDatosFechaHora(obj.getTratamientoDatosFechaHora());
        //2023-06-15 jyperez N48 acuerdo de pago suspensión X mora
        per.setAcuerdoPago(obj.getAcuerdoPago());
        per.setFechaAcuerdoPago(obj.getFechaAcuerdoPago());
        //2023-08-16 jyperez N47 madre gestante
        per.setUsuarioGestante(obj.getUsuarioGestante());
        per.setFechaFinPeriodoGestacion(obj.getFechaFinPeriodoGestacion());
        //2023-07-25 jyperez nuevo campo
        per.setIncapacidadProlongada(obj.getIncapacidadProlongada());
        //2024-01-10 jyperez Is 58 retiro por duplicado
        //2024-02-29 jyperez se realiza ajuste para actualizar el valor duplicado de nulo a cero
        //2024-03-05 rpalacic Corrección de forma para evitar dato nulo
        //2024-03-13 jyperez se corrige debido a que el campo a almacenar corresponde al per no al obj
        per.setDuplicado((obj.getDuplicado() == null) ? false : obj.getDuplicado());
        //2024-03-13 jyperez N00-42 traslado preaprobado
        per.setTrasladoPreaprobado(obj.isTrasladoPreaprobado());
        //2024-03-22 jyperez RES 2335
        per.setDireccionAlternaContacto(obj.getDireccionAlternaContacto());
        per.setNombreContactoEmergencia(obj.getNombreContactoEmergencia());
        per.setTelefonoContactoEmergencia(obj.getTelefonoContactoEmergencia());
        //2024-09-23 jyperez nuevos campos portabilidad
        per.setMaeTipoPortabilidadId(obj.getMaeTipoPortabilidadId());
        per.setMaeTipoPortabilidadCodigo(obj.getMaeTipoPortabilidadCodigo());
        per.setMaeTipoPortabilidadValor(obj.getMaeTipoPortabilidadValor());
        per.setMaeMotivoPortabilidadId(obj.getMaeMotivoPortabilidadId());
        per.setMaeMotivoPortabilidadCodigo(obj.getMaeMotivoPortabilidadCodigo());
        per.setMaeMotivoPortabilidadValor(obj.getMaeMotivoPortabilidadValor());
        per.setMaeOrigenSolicitudPortabilidadId(obj.getMaeOrigenSolicitudPortabilidadId());
        per.setMaeOrigenSolicitudPortabilidadCodigo(obj.getMaeOrigenSolicitudPortabilidadCodigo());
        per.setMaeOrigenSolicitudPortabilidadValor(obj.getMaeOrigenSolicitudPortabilidadValor());
        per.setPeriodoInicialPortabilidad(obj.getPeriodoInicialPortabilidad());
        per.setPeriodoFinalPortabilidad(obj.getPeriodoFinalPortabilidad());
        per.setTelefonoContactoPortabilidad(obj.getTelefonoContactoPortabilidad());
        per.setDireccionPortabilidad(obj.getDireccionPortabilidad());
        per.setCorreoElectronicoPortabilidad(obj.getCorreoElectronicoPortabilidad());
        per.setObservacionPortabilidad(obj.getObservacionPortabilidad());
        //2025-08-04 jyperez nuevos campos georeferenciación
        per.setDireccionGeorefLongitud(obj.getDireccionGeorefLongitud());
        per.setDireccionGeorefLatitud(obj.getDireccionGeorefLatitud());
        if (obj.getPrimariaPrestadorSede() != null) {
            per.setPrimariaCntPrestadorSedesId(new CntPrestadorSedes(obj.getPrimariaPrestadorSede().getId()));
        }
        if (obj.getPortabilidadPrestadorSede() != null) {
            per.setPortabilidadCntPrestadorSedesId(new CntPrestadorSedes(obj.getPortabilidadPrestadorSede().getId()));
        }
        if (obj.getNacionalidadUbicacion() != null) {
            per.setNacionalidadUbicacionesId(new GnUbicaciones(obj.getNacionalidadUbicacion().getId()));
        }
        if (obj.getNacimientoUbicacion() != null) {
            per.setNacimientoUbicacionesId(new GnUbicaciones(obj.getNacimientoUbicacion().getId()));
        }
        if (obj.getAfiliacionUbicacion() != null) {
            per.setAfiliacionUbicacionesId(new GnUbicaciones(obj.getAfiliacionUbicacion().getId()));
        }
        if (obj.getResidenciaUbicacion() != null) {
            per.setResidenciaUbicacionId(new GnUbicaciones(obj.getResidenciaUbicacion().getId()));
        }
        //2023-06-15 jyperez N43 lugar de nacimiento
        if (obj.getGnUbicacionesLugarNacimientoId() != null) {
            per.setGnUbicacionesLugarNacimientoId(new GnUbicaciones(obj.getGnUbicacionesLugarNacimientoId().getId()));
        }
        //2025-03-13 jyperez nuevo objeto ubicacionBarrio
        if (obj.getUbicacionBarrio()!= null && obj.getUbicacionBarrio().getId() != null && obj.getUbicacionBarrio().getId() != 0) {
            per.setGnUbicacionBarriosId(new GnUbicacionBarrios(obj.getUbicacionBarrio().getId()));
        }
        //2025-07-24 jyperez campo lugar ubicación actual
        if (obj.getGnUbicacionActualAfiliado()!= null) {
            per.setGnUbicacionActualAfiliado(new GnUbicaciones(obj.getGnUbicacionActualAfiliado().getId()));
        }
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setObservacion(obj.getObservacionNovedad());
        return per;
    }

    @Override
    public List<AsegAfiliado> consultarGrupoFamiliar(Integer tipoDocumentoCF, String numeroDocumentoCF) throws Exception {
        List<AsegAfiliado> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegAfiliados p "
                    + " WHERE p.maeTipoDocumentoCfId = " + tipoDocumentoCF
                    + " AND p.numeroDocumentoCf = '" + numeroDocumentoCF + "' "
                    + " AND p.maeTipoDocumentoId <> p.maeTipoDocumentoCfId "
                    + " AND p.numeroDocumento <> p.numeroDocumentoCf "
                    + " ORDER BY p.id ASC";
            List<AsegAfiliados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegAfiliados per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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
    public AsegAfiliado consultar(Integer tipodocumento, String numeroDocumento) throws Exception {
        AsegAfiliado afiliadoResult = null;
        try {
            String strQuery = "FROM AsegAfiliados p "
                    + " WHERE p.maeTipoDocumentoId = " + tipodocumento
                    + " AND p.numeroDocumento = '" + numeroDocumento + "' ";
            AsegAfiliados obj = (AsegAfiliados) getEntityManager().createQuery(strQuery).getSingleResult();
            afiliadoResult = castEntidadNegocioCorto(obj);
        } catch (NoResultException e) {
            afiliadoResult = null;
        } catch (Exception e) {
            afiliadoResult = null;
        } finally {
            cerrarEntityManager();
        }
        return afiliadoResult;
    }

    @Override
    public AsegAfiliado consultarPorAfiliadoDocumentoHistorico(Integer tipodocumento, String numeroDocumento) throws Exception {
        AsegAfiliado afiliadoResult = null;
        try {
            String strQuery = "SELECT p.asegAfiliadosId FROM AsegAfiliadoDocumentos p "
                    + " WHERE p.maeTipoDocumentoId = " + tipodocumento
                    + " AND p.numeroDocumento = '" + numeroDocumento + "' "
                    + " AND p.asegAfiliadosId.maeEstadoAfiliacionId IN "
                    + " ( SELECT m.gnMaestrosId.id FROM GnMaestroAccionRelaciones m "
                    + " WHERE m.gnMaestroAccionesId.id = " + MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO + " ) ";
            AsegAfiliados obj = (AsegAfiliados) getEntityManager().createQuery(strQuery).getSingleResult();
            afiliadoResult = castEntidadNegocioCorto(obj);
        } catch (NoResultException e) {
            afiliadoResult = null;
        } catch (Exception e) {
            afiliadoResult = null;
        } finally {
            cerrarEntityManager();
        }
        return afiliadoResult;
    }

    @Override
    public List<AsegAfiliado> consultarListaAfiliados(String tipodocumento, String numeroDocumento) throws Exception {

        List<AsegAfiliado> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegAfiliados p "
                    + " WHERE p.maeTipoDocumentoCodigo = '" + tipodocumento + "' "
                    + " AND p.numeroDocumento = '" + numeroDocumento + "' ";
            List<AsegAfiliados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegAfiliados per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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
    public AsegAfiliado consultar(String primerApellido, String primerNombre, Date fechaNacimiento) throws Exception {
        AsegAfiliado afiliadoResult = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String strQuery = "FROM AsegAfiliados p "
                    + " WHERE p.fechaNacimiento = '" + sdf.format(fechaNacimiento) + "' "
                    + " AND p.primerApellido = '" + primerApellido + "' "
                    + " AND p.primerNombre = '" + primerNombre + "' ";
            AsegAfiliados obj = (AsegAfiliados) getEntityManager().createQuery(strQuery).getSingleResult();
            afiliadoResult = castEntidadNegocioCorto(obj);
        } catch (NoResultException e) {
            afiliadoResult = null;
        } catch (Exception e) {
            afiliadoResult = null;
        } finally {
            cerrarEntityManager();
        }
        return afiliadoResult;
    }

    @Override
    public ReporteEncuesta016 reporte016(String id) throws Exception {
        ReporteEncuesta016 reporte = new ReporteEncuesta016();
        try {
            String strQuery = "SELECT "
                    + "en.primerNombre , "
                    + "en.segundoNombre , "
                    + "en.primerApellido , "
                    + "en.segundoApellido , "
                    + "af.numeroDocumento , "
                    + "DATE_FORMAT(en.fechaHoraCrea, '%d-%m-%Y') , "
                    + "ub.nombre , "
                    + "en.observacion , "
                    + "(SELECT respuesta FROM AsegTabulacionEncuestas p1 "
                    + "WHERE p1.asegEncuestaPreguntasId = 8 AND p1.asegAfiliadosId = en.asegAfiliadosId), "
                    + "(SELECT respuesta FROM AsegTabulacionEncuestas p2 "
                    + "WHERE p2.asegEncuestaPreguntasId = 9 AND p2.asegAfiliadosId = en.asegAfiliadosId), "
                    + "(SELECT respuesta FROM AsegTabulacionEncuestas p3 "
                    + "WHERE p3.asegEncuestaPreguntasId = 10 AND p3.asegAfiliadosId = en.asegAfiliadosId), "
                    + "(SELECT respuesta FROM AsegTabulacionEncuestas p4 "
                    + "WHERE p4.asegEncuestaPreguntasId = 11 AND p4.asegAfiliadosId = en.asegAfiliadosId), "
                    + "(SELECT respuesta FROM AsegTabulacionEncuestas p5 "
                    + "WHERE p5.asegEncuestaPreguntasId = 12 AND p5.asegAfiliadosId = en.asegAfiliadosId) "
                    + "FROM AsegTabulacionEncuestas en "
                    + "INNER JOIN en.asegAfiliadosId af "
                    + "INNER JOIN af.afiliacionUbicacionesId ub "
                    + "WHERE en.asegAfiliadosId = " + id + " ";
            Query q = getEntityManager().createQuery(strQuery);
            List<Object[]> lista = q.getResultList();
            if (lista.size() > 0) {
                Object[] objeto = lista.get(0);
                String nombreCompleto = objeto[0].toString() + " ";
                if (objeto[1] != null) {
                    nombreCompleto = nombreCompleto + objeto[1].toString() + " ";
                }
                nombreCompleto = nombreCompleto + objeto[2].toString() + " " + objeto[3].toString();
                reporte.setStrNombre(nombreCompleto.toUpperCase());
                reporte.setId(id);
                reporte.setStrDocumento(objeto[4].toString());
                reporte.setStrFecha(objeto[5].toString());
                reporte.setStrMunicipio(objeto[6].toString());
                String observacion = "";
                if (objeto[7] != null) {
                    observacion = objeto[7].toString();
                }
                reporte.setStrObservaciones(observacion);
                reporte.setBlnRespuesta1((Boolean) objeto[8]);
                reporte.setBlnRespuesta2((Boolean) objeto[9]);
                reporte.setBlnRespuesta3((Boolean) objeto[10]);
                reporte.setBlnRespuesta4((Boolean) objeto[11]);
                reporte.setBlnRespuesta5((Boolean) objeto[12]);
            }
        } catch (NoResultException e) {
            reporte = new ReporteEncuesta016();
        } catch (Exception e) {
            reporte = new ReporteEncuesta016();
        } finally {
            cerrarEntityManager();
        }
        return reporte;
    }

    @Override
    public void actualizarAfiliadoPortabilidad(AsegAfiliado obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AsegAfiliados a SET ";
            strQuery += " a.tienePortabilidad = " + (obj.getTienePortabilidad() ? "1" : "0") + ",";
            strQuery += " a.fechaPortabilidad = " + (obj.getFechaPortabilidad() != null ? "'" + Util.fechaDateAString(obj.getFechaPortabilidad()) + "'" : "NULL") + ", ";
            strQuery += " a.modeloLiquidacion = " + (obj.getModeloLiquidacion()) + ", ";
            strQuery += " a.maeModeloLiquidacionId = " + (obj.getMaeModeloLiquidacionId()) + ", ";
            strQuery += " a.maeModeloLiquidacionCodigo = '" + (obj.getMaeModeloLiquidacionCodigo()) + "', ";
            strQuery += " a.maeModeloLiquidacionValor = '" + (obj.getMaeModeloLiquidacionValor()) + "', ";
            // campos objetos 
            if (obj.getPortabilidadPrestadorSede() != null) {
                strQuery += " a.portabilidadCntPrestadorSedesId.id = " + obj.getPortabilidadPrestadorSede().getId() + ", ";
            } else {
                strQuery += " a.portabilidadCntPrestadorSedesId.id = NULL, ";
            }
            //2024-09-19 jyperez adicionamos los valores de los nuevos campos en afiliado
            strQuery += " a.maeTipoPortabilidadId = " + obj.getMaeTipoPortabilidadId() + " ,";
            strQuery += " a.maeTipoPortabilidadCodigo = '" + obj.getMaeTipoPortabilidadCodigo() + "' ,";
            strQuery += " a.maeTipoPortabilidadValor = '" + obj.getMaeTipoPortabilidadValor() + "' ,";
            strQuery += " a.maeMotivoPortabilidadId = " + obj.getMaeMotivoPortabilidadId() + " ,";
            strQuery += " a.maeMotivoPortabilidadCodigo = '" + obj.getMaeMotivoPortabilidadCodigo() + "' ,";
            strQuery += " a.maeMotivoPortabilidadValor = '" + obj.getMaeMotivoPortabilidadValor() + "' ,";
            strQuery += " a.maeOrigenSolicitudPortabilidadId = " + obj.getMaeOrigenSolicitudPortabilidadId() + " ,";
            strQuery += " a.maeOrigenSolicitudPortabilidadCodigo = '" + obj.getMaeOrigenSolicitudPortabilidadCodigo() + "' ,";
            strQuery += " a.maeOrigenSolicitudPortabilidadValor = '" + obj.getMaeOrigenSolicitudPortabilidadValor() + "' ,";
            strQuery += " a.periodoInicialPortabilidad = " + (obj.getPeriodoInicialPortabilidad() != null ? "'" + Util.fechaDateAString(obj.getPeriodoInicialPortabilidad()) + "'" : "NULL") + ", ";
            strQuery += " a.periodoFinalPortabilidad = " + (obj.getPeriodoFinalPortabilidad() != null ? "'" + Util.fechaDateAString(obj.getPeriodoFinalPortabilidad()) + "'" : "NULL") + ", ";
            strQuery += " a.telefonoContactoPortabilidad = '" + obj.getTelefonoContactoPortabilidad() + "' ,";
            strQuery += " a.direccionPortabilidad = '" + obj.getDireccionPortabilidad() + "' ,";
            strQuery += " a.correoElectronicoPortabilidad = '" + obj.getCorreoElectronicoPortabilidad() + "' ,";
            strQuery += " a.observacionPortabilidad = '" + obj.getObservacionPortabilidad() + "' ,";
            strQuery += " a.usuarioModifica = '" + obj.getUsuarioModifica() + "' ,";
            strQuery += " a.fechaHoraModifica = '" + Util.fechaDateAString(obj.getFechaHoraModifica()) + "' ,";
            strQuery += " a.terminalModifica = '" + obj.getTerminalModifica() + "' ";
            strQuery += " WHERE a.id = " + obj.getId();
            org.hibernate.Query query = session.createQuery(strQuery);
            query.executeUpdate();
            //session.beginTransaction().commit();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    public static AsegAfiliado castEntidadNegocioCorto(AsegAfiliados per) {
        AsegAfiliado obj = new AsegAfiliado();
        obj.setId(per.getId());
        obj.setIdAfiliado(per.getIdAfiliado());//Contrato
        obj.setSerialBdua(per.getSerialBdua());
        obj.setRegistroBdua(per.getRegistroBdua());
        obj.setPrimerNombre(per.getPrimerNombre());
        obj.setSegundoNombre(per.getSegundoNombre());
        obj.setPrimerApellido(per.getPrimerApellido());
        obj.setSegundoApellido(per.getSegundoApellido());
        obj.setFechaNacimiento(per.getFechaNacimiento());
        obj.setFechaExpedicionCedula(per.getFechaExpedicionCedula());
        obj.setGenero(per.getGenero());
        obj.setMaeGeneroId(per.getMaeGeneroId());
        obj.setMaeGeneroCodigo(per.getMaeGeneroCodigo());
        obj.setMaeGeneroValor(per.getMaeGeneroValor());
        obj.setGenero(per.getGenero());
        //2023-06-15 jyperez N45 genero identificacion
        obj.setMaeGeneroIdentificacionId(per.getMaeGeneroIdentificacionId());
        obj.setMaeGeneroIdentificacionCodigo(per.getMaeGeneroIdentificacionCodigo());
        obj.setMaeGeneroIdentificacionValor(per.getMaeGeneroIdentificacionValor());
        obj.setMaeTipoDocumento(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setNumeroDocumento(per.getNumeroDocumento());
        obj.setFechaAfiliacionEps(per.getFechaAfiliacionEps());
        obj.setFechaEgresoEps(per.getFechaEgresoEps());
        obj.setTipoBeneficiario(per.getTipoBeneficiario());
        obj.setMaeTipoAfiliadoId(per.getMaeTipoAfiliadoId());
        obj.setMaeTipoAfiliadoCodigo(per.getMaeTipoAfiliadoCodigo());
        obj.setMaeTipoAfiliadoValor(per.getMaeTipoAfiliadoValor());
        obj.setMaeEstadoAfiliacion(per.getMaeEstadoAfiliacionId());
        obj.setMaeEstadoAfiliacionCodigo(per.getMaeEstadoAfiliacionCodigo());
        obj.setMaeEstadoAfiliacionValor(per.getMaeEstadoAfiliacionValor());
        obj.setRegimen(per.getRegimen());
        obj.setNivelSisben(per.getNivelSisben());
        //2021-05-20 jyperez nuevos campos
        obj.setMaeRegimenId(per.getMaeRegimenId());
        obj.setMaeRegimenCodigo(per.getMaeRegimenCodigo());
        obj.setMaeRegimenValor(per.getMaeRegimenValor());
        obj.setMaeNivelSisbenId(per.getMaeNivelSisbenId());
        obj.setMaeNivelSisbenCodigo(per.getMaeNivelSisbenCodigo());
        obj.setMaeNivelSisbenValor(per.getMaeNivelSisbenValor());
        obj.setCategoriaIbc(per.getCategoriaIbc());
        obj.setMaeCategoriaIbcId(per.getMaeCategoriaIbcId());
        obj.setMaeCategoriaIbcCodigo(per.getMaeCategoriaIbcCodigo());
        obj.setMaeCategoriaIbcValor(per.getMaeCategoriaIbcValor());
        obj.setModeloLiquidacion(per.getModeloLiquidacion());
        obj.setMaeModeloLiquidacionId(per.getMaeModeloLiquidacionId());
        obj.setMaeModeloLiquidacionCodigo(per.getMaeModeloLiquidacionCodigo());
        obj.setMaeModeloLiquidacionValor(per.getMaeModeloLiquidacionValor());
        obj.setMaeTipoDocumentoCf(per.getMaeTipoDocumentoCfId());
        obj.setMaeTipoDocumentoCfCodigo(per.getMaeTipoDocumentoCfCodigo());
        obj.setMaeTipoDocumentoCfValor(per.getMaeTipoDocumentoCfValor());
        obj.setNumeroDocumentoCf(per.getNumeroDocumentoCf());
        //2020-08-18 jyperez nuevos campos solicitados
        obj.setDireccionResidencia(per.getDireccionResidencia());
        obj.setZona(per.getZona());
        obj.setMaeZonaId(per.getMaeZonaId());
        obj.setMaeZonaCodigo(per.getMaeZonaCodigo());
        obj.setMaeZonaValor(per.getMaeZonaValor());
        obj.setTelefonoFijo(per.getTelefonoFijo());
        obj.setTelefonoMovil(per.getTelefonoMovil());
        obj.setEmail(per.getEmail());
        obj.setAutorizaEnvioEmail(per.getAutorizaEnvioEmail());
        obj.setAutorizaEnvioSms(per.getAutorizaEnvioSms());
        obj.setMaeGrupoPoblacional(per.getMaeGrupoPoblacionalId());
        obj.setMaeGrupoPoblacionalCodigo(per.getMaeGrupoPoblacionalCodigo());
        obj.setMaeGrupoPoblacionalValor(per.getMaeGrupoPoblacionalValor());
        obj.setEtnia(per.getEtnia());
        obj.setMaeEtniaId(per.getMaeEtniaId());
        obj.setMaeEtniaCodigo(per.getMaeEtniaCodigo());
        obj.setMaeEtniaValor(per.getMaeEtniaValor());
        obj.setTienePortabilidad(per.getTienePortabilidad());
        if (per.getPuntajeSisben() != null) {
            obj.setPuntajeSisben(per.getPuntajeSisben().doubleValue());
        }
        obj.setFichaSisben(per.getFichaSisben());
        obj.setFechaSisben(per.getFechaSisben());
        obj.setDiscapacidad(per.getDiscapacidad());
        obj.setTipoDiscapacidad(per.getTipoDiscapacidad());
        obj.setMaeTipoDiscapacidadId(per.getMaeTipoDiscapacidadId());
        obj.setMaeTipoDiscapacidadCodigo(per.getMaeTipoDiscapacidadCodigo());
        obj.setMaeTipoDiscapacidadValor(per.getMaeTipoDiscapacidadValor());
        obj.setVictima(per.getVictima());
        obj.setObservacionNovedad(per.getObservacion());
        obj.setOrigenUltimoRegistro(per.getOrigenUltimoRegistro());
        //2022-10-31 jyperez nuevo campo
        obj.setFechaSuspension(per.getFechaSuspensionEps());
        //2022-11-25 jyperez nuevos campos
        obj.setTratamientoDatosAutoriza(per.getTratamientoDatosAutoriza());
        obj.setTratamientoDatosFechaHora(per.getTratamientDatosFechaHora());
        //2023-06-15 jyperez N48 acuerdo de pago suspensión x mora
        obj.setAcuerdoPago(per.getAcuerdoPago());
        obj.setFechaAcuerdoPago(per.getFechaAcuerdoPago());
        //2023-07-25 jyperez nuevo campo
        obj.setIncapacidadProlongada(per.getIncapacidadProlongada());
        //2024-01-10 jyperez Is 58 retiro por duplicado
        //2024-02-29 jyperez se realiza ajuste para actualizar el valor duplicado de nulo a cero
        //2024-03-05 rpalacic Corrección de forma para evitar dato nulo
        obj.setDuplicado((per.getDuplicado() == null) ? false : per.getDuplicado());
        //2024-03-13 jyperez N00-42 traslado preaprobado
        obj.setTrasladoPreaprobado((per.getTrasladoPreaprobado() == null) ? false : per.getTrasladoPreaprobado());
        //2024-03-22 jyperez RES 2335
        obj.setDireccionAlternaContacto(per.getDireccionAlternaContacto());
        obj.setNombreContactoEmergencia(per.getNombreContactoEmergencia());
        obj.setTelefonoContactoEmergencia(per.getTelefonoContactoEmergencia());
        //2025-08-04 jyperez nuevos campos georeferenciación
        obj.setDireccionGeorefLongitud(per.getDireccionGeorefLongitud());
        obj.setDireccionGeorefLatitud(per.getDireccionGeorefLatitud());
        if (per.getPrimariaCntPrestadorSedesId() != null) {
            obj.setPrimariaPrestadorSede(new CntPrestadorSede(per.getPrimariaCntPrestadorSedesId().getId(), per.getPrimariaCntPrestadorSedesId().getNombre(), null));
            //obj.setPrimariaPrestadorSede(castPrestadorSedesEntidadNegocio(per.getPrimariaCntPrestadorSedesId()));
        }
        if (per.getAfiliacionUbicacionesId() != null) {
            obj.setAfiliacionUbicacion(castUbicacionEntidadNegocio(per.getAfiliacionUbicacionesId()));
        }
        if (per.getResidenciaUbicacionId() != null) {
            obj.setResidenciaUbicacion(castUbicacionEntidadNegocio(per.getResidenciaUbicacionId()));
        }
        //2023-06-15 jyperez N43 lugar nacimiento
        if (per.getGnUbicacionesLugarNacimientoId() != null) {
            obj.setGnUbicacionesLugarNacimientoId(castUbicacionEntidadNegocio(per.getGnUbicacionesLugarNacimientoId()));
        }
        if (per.getPortabilidadCntPrestadorSedesId() != null) {
            obj.setPortabilidadPrestadorSede(castPrestadorSedesEntidadNegocio(per.getPortabilidadCntPrestadorSedesId()));
        }
        return obj;
    }

    @Override
    public List<ReporteAfiliacion> reporteAfiliacion(String id) throws Exception {
        List<ReporteAfiliacion> listaReportes = new ArrayList();
        AsegAfiliados afiliado;
        Set<String> listaMae = new HashSet();
        try {
            String strQuery = "SELECT rad "
                    + "FROM AsegRadicadoNovedades rad "
                    + "WHERE rad.asegAfiliadosId = " + id;

            List<AsegRadicadoNovedades> lista = getEntityManager().createQuery(strQuery).getResultList();
            if (lista.size() > 0) {
                afiliado = lista.get(lista.size() - 1).getAsegAfiliadosId();

                listaMae.add("" + afiliado.getMaeTipoDocumentoId());
                listaMae.add(afiliado.getMaeGeneroId().toString());
                listaMae.add("" + afiliado.getMaeGrupoPoblacionalId());

                strQuery = "SELECT rad "
                        + "FROM AsegAfiliados rad "
                        + "WHERE rad.numeroDocumentoCf = '" + afiliado.getNumeroDocumento() + "'";
                List<AsegAfiliados> listaGrupoFamiliar = getEntityManager().createQuery(strQuery).getResultList();

                if (listaGrupoFamiliar.size() > 0) {
                    for (AsegAfiliados familiar : listaGrupoFamiliar) {
                        listaMae.add("" + familiar.getMaeTipoDocumentoId());
                        listaMae.add(familiar.getMaeGeneroId().toString());
                    }
                }

                strQuery = "SELECT m "
                        + "FROM GnMaestros m "
                        + "WHERE m.id IN (" + listaMae.toString().replace("[", "").replace("]", "") + ")";;

                List<GnMaestros> listaMaestros = getEntityManager().createQuery(strQuery).getResultList();
                HashMap<String, String> listadoMaestros = new HashMap<String, String>();
                for (GnMaestros maestro : listaMaestros) {
                    listadoMaestros.put(maestro.getId().toString(), maestro.getValor());
                }

                //Novedades
                String strNumeroRadicacion = lista.get(lista.size() - 1).getId().toString();
                Date dtmFechaRadicacion = lista.get(lista.size() - 1).getFechaHoraCrea();
                String strTipoTramite = "";
                if (afiliado.getFechaAfiliacionEps() != null) {
                    Date fechaInicial = afiliado.getFechaAfiliacionEps();
                    Date fechaFinal = new Date();
                    int dias = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);
                    if (dias <= 15) {
                        strTipoTramite = "A";
                    } else {
                        strTipoTramite = "R";
                    }
                }
                String strTipoAfiliacion = afiliado.getTipoBeneficiario() == null ? "" : afiliado.getTipoBeneficiario();
                String strTipoAfiliado = afiliado.getTipoBeneficiario() == null ? "" : afiliado.getTipoBeneficiario();

                //Afiliado
                String strRegimen = afiliado.getRegimen() == null ? "" : afiliado.getRegimen();
                String strPrimerApellido = afiliado.getPrimerApellido() == null ? "" : afiliado.getPrimerApellido();
                String strSegundoApellido = afiliado.getSegundoApellido() == null ? "" : afiliado.getSegundoApellido();
                String strPrimerNombre = afiliado.getPrimerNombre() == null ? "" : afiliado.getPrimerNombre();
                String strSegundoNombre = afiliado.getSegundoNombre() == null ? "" : afiliado.getSegundoNombre();
                String strTipoDocumento = listadoMaestros.get("" + afiliado.getMaeTipoDocumentoId());
                String strNumeroDocumento = afiliado.getNumeroDocumento() == null ? "" : afiliado.getNumeroDocumento();
                String strSexo = listadoMaestros.get(afiliado.getMaeGeneroId().toString());
                Date dtmFechaNacimiento = afiliado.getFechaNacimiento();
                String strEtnia = afiliado.getEtnia() == null ? "" : afiliado.getEtnia();
                String strTipoDiscapacidad = afiliado.getTipoDiscapacidad() == null ? "" : afiliado.getTipoDiscapacidad();
                String strCondicionDiscapacidad = afiliado.getCondicionDiscapacidad() == null ? "" : afiliado.getCondicionDiscapacidad();
                String strPuntajeSisben = afiliado.getPuntajeSisben() == null ? "0" : afiliado.getPuntajeSisben().toString();
                String strGrupoPoblacionEspecial = "" + afiliado.getMaeGrupoPoblacionalId() == null ? "" : listadoMaestros.get("" + afiliado.getMaeGrupoPoblacionalId());
                String strNombreARL = afiliado.getMaeArlId() == null ? "" : afiliado.getMaeArlId().toString();
                String strNombrePensiones = "";
                String strDireccionResidencia = afiliado.getDireccionResidencia() == null ? "" : afiliado.getDireccionResidencia();
                String strTelefono = afiliado.getTelefonoFijo() == null ? "" : afiliado.getTelefonoFijo();
                String strCelular = afiliado.getTelefonoMovil() == null ? "" : afiliado.getTelefonoMovil();
                String strCorreoElectronico = afiliado.getEmail() == null ? "" : afiliado.getEmail();
                String strMunicipio = afiliado.getAfiliacionUbicacionesId() == null ? "" : afiliado.getAfiliacionUbicacionesId().getNombre();
                String strTipoZona = afiliado.getZona() == null ? "" : afiliado.getZona();
                String strDepartamento = afiliado.getAfiliacionUbicacionesId() == null ? "" : afiliado.getAfiliacionUbicacionesId().getGnUbicacionesId().getNombre();
                String strIpsPrimaria = afiliado.getPrimariaCntPrestadorSedesId() == null ? "" : afiliado.getPrimariaCntPrestadorSedesId().getNombre();
                String strCodigoIpsPrimaria = afiliado.getPrimariaCntPrestadorSedesId() == null ? "" : afiliado.getPrimariaCntPrestadorSedesId().getCodigoHabilitacion();

                //Compañero
                String strPrimerApellidoCompanero = null;
                String strSegundoApellidoCompanero = null;
                String strPrimerNombreCompanero = null;
                String strSegundoNombreCompanero = null;
                String strTipoDocumentoCompanero = null;
                String strNumeroDocumentoCompanero = null;
                String strSexoCompanero = null;
                Date dtmFechaNacimientoCompanero = null;
                if (listaGrupoFamiliar.size() > 0) {
                    for (AsegAfiliados familiar : listaGrupoFamiliar) {
                        if (familiar.getParentescoCotizante() != null && familiar.getParentescoCotizante().equals("1")) {
                            strPrimerApellidoCompanero = familiar.getPrimerApellido();
                            strSegundoApellidoCompanero = familiar.getSegundoApellido();
                            strPrimerNombreCompanero = familiar.getPrimerNombre();
                            strSegundoNombreCompanero = familiar.getSegundoNombre();
                            strTipoDocumentoCompanero = listadoMaestros.get("" + familiar.getMaeTipoDocumentoId());
                            strNumeroDocumentoCompanero = familiar.getNumeroDocumento();
                            strSexoCompanero = listadoMaestros.get(familiar.getMaeGeneroId().toString());
                            dtmFechaNacimientoCompanero = familiar.getFechaNacimiento();
                        }
                    }
                }

                int contador = 0;
                if (listaGrupoFamiliar.size() > 1) {
                    for (AsegAfiliados familiar : listaGrupoFamiliar) {
                        ReporteAfiliacion reporte = new ReporteAfiliacion();

                        //Novedades
                        reporte.setStrNumeroRadicacion(strNumeroRadicacion);
                        reporte.setDtmFechaRadicacion(dtmFechaRadicacion);
                        reporte.setStrTipoTramite(strTipoTramite);
                        reporte.setStrTipoAfiliacion(strTipoAfiliacion);
                        reporte.setStrTipoAfiliado(strTipoAfiliado);

                        //Afiliado
                        reporte.setStrRegimen(strRegimen);
                        reporte.setStrPrimerApellido(strPrimerApellido);
                        reporte.setStrSegundoApellido(strSegundoApellido);
                        reporte.setStrPrimerNombre(strPrimerNombre);
                        reporte.setStrSegundoNombre(strSegundoNombre);
                        reporte.setStrTipoDocumento(strTipoDocumento);
                        reporte.setStrNumeroDocumento(strNumeroDocumento);
                        reporte.setStrSexo(strSexo);
                        reporte.setDtmFechaNacimiento(dtmFechaNacimiento);
                        reporte.setStrEtnia(strEtnia);
                        reporte.setStrTipoDiscapacidad(strTipoDiscapacidad);
                        reporte.setStrCondicionDiscapacidad(strCondicionDiscapacidad);
                        reporte.setStrPuntajeSisben(strPuntajeSisben);
                        reporte.setStrGrupoPoblacionEspecial(strGrupoPoblacionEspecial);
                        reporte.setStrNombreARL(strNombreARL);
                        reporte.setStrNombrePensiones(strNombrePensiones);
                        reporte.setStrDireccionResidencia(strDireccionResidencia);
                        reporte.setStrTelefono(strTelefono);
                        reporte.setStrCelular(strCelular);
                        reporte.setStrCorreoElectronico(strCorreoElectronico);
                        reporte.setStrMunicipio(strMunicipio);
                        reporte.setStrTipoZona(strTipoZona);
                        reporte.setStrDepartamento(strDepartamento);
                        reporte.setStrIpsPrimaria(strIpsPrimaria);
                        reporte.setStrCodigoIpsPrimaria(strCodigoIpsPrimaria);

                        //Compañero
                        reporte.setStrPrimerApellidoCompanero(strPrimerApellidoCompanero);
                        reporte.setStrSegundoApellidoCompanero(strSegundoApellidoCompanero);
                        reporte.setStrPrimerNombreCompanero(strPrimerNombreCompanero);
                        reporte.setStrSegundoNombreCompanero(strSegundoNombreCompanero);
                        reporte.setStrTipoDocumentoCompanero(strTipoDocumentoCompanero);
                        reporte.setStrNumeroDocumentoCompanero(strNumeroDocumentoCompanero);
                        reporte.setStrSexoCompanero(strSexoCompanero);
                        reporte.setDtmFechaNacimientoCompanero(dtmFechaNacimientoCompanero);

                        if (familiar.getParentescoCotizante() != null && !familiar.getParentescoCotizante().equals("1") && contador < 1) {
                            String primerApellido = familiar.getPrimerApellido();
                            String segundoApellido = familiar.getSegundoApellido();
                            String primerNombre = familiar.getPrimerNombre();
                            String segundoNombre = familiar.getSegundoNombre();
                            String tipoDocumento = listadoMaestros.get("" + familiar.getMaeTipoDocumentoId());
                            String numeroDocumento = familiar.getNumeroDocumento();
                            String genero = listadoMaestros.get(familiar.getMaeGeneroId().toString());
                            Date fechaNacimiento = familiar.getFechaNacimiento();
                            String parentezco = familiar.getParentescoCotizante();
                            String etnia = familiar.getEtnia();
                            String discapacidad = familiar.getTipoDiscapacidad() == null ? "" : familiar.getTipoDiscapacidad();
                            String condicion = familiar.getCondicionDiscapacidad() == null ? "" : familiar.getCondicionDiscapacidad();
                            String municipio = familiar.getAfiliacionUbicacionesId() == null ? null : familiar.getAfiliacionUbicacionesId().getNombre();
                            String zona = familiar.getZona();
                            String departamento = familiar.getAfiliacionUbicacionesId() == null ? null : familiar.getAfiliacionUbicacionesId().getGnUbicacionesId().getNombre();
                            String telefono = familiar.getTelefonoMovil();

                            //Beneficiario
                            reporte.setStrPrimerApellidoBeneficiario(primerApellido);
                            reporte.setStrSegundoApellidoBeneficiario(segundoApellido);
                            reporte.setStrPrimerNombreBeneficiario(primerNombre);
                            reporte.setStrSegundoNombreBeneficiario(segundoNombre);
                            reporte.setStrTipoDocumentoBeneficiario(tipoDocumento);
                            reporte.setStrNumeroDocumentoBeneficiario(numeroDocumento);
                            reporte.setStrSexoBeneficiario(genero);
                            reporte.setDtmFechaNacimientoBeneficiario(fechaNacimiento);
                            reporte.setStrParentescoBeneficiario(parentezco);
                            reporte.setStrEtniaBeneficiario(etnia);
                            reporte.setStrTipoDiscapacidadBeneficiario(discapacidad);
                            reporte.setStrCondicionDiscapacidadBeneficiario(condicion);
                            reporte.setStrMunicipioBeneficiario(municipio);
                            reporte.setStrZonaBeneficiario(zona);
                            reporte.setStrDepartamentoBeneficiario(departamento);
                            reporte.setStrTelefonoBeneficiario(telefono);
                            if (contador < 1) {
                                listaReportes.add(reporte);
                                contador++;
                            }

                        }

                    }
                } else {
                    ReporteAfiliacion reporte = new ReporteAfiliacion();

                    //Novedades
                    reporte.setStrNumeroRadicacion(strNumeroRadicacion);
                    reporte.setDtmFechaRadicacion(dtmFechaRadicacion);
                    reporte.setStrTipoTramite(strTipoTramite);
                    reporte.setStrTipoAfiliacion(strTipoAfiliacion);
                    reporte.setStrTipoAfiliado(strTipoAfiliado);

                    //Afiliado
                    reporte.setStrRegimen(strRegimen);
                    reporte.setStrPrimerApellido(strPrimerApellido);
                    reporte.setStrSegundoApellido(strSegundoApellido);
                    reporte.setStrPrimerNombre(strPrimerNombre);
                    reporte.setStrSegundoNombre(strSegundoNombre);
                    reporte.setStrTipoDocumento(strTipoDocumento);
                    reporte.setStrNumeroDocumento(strNumeroDocumento);
                    reporte.setStrSexo(strSexo);
                    reporte.setDtmFechaNacimiento(dtmFechaNacimiento);
                    reporte.setStrEtnia(strEtnia);
                    reporte.setStrTipoDiscapacidad(strTipoDiscapacidad);
                    reporte.setStrCondicionDiscapacidad(strCondicionDiscapacidad);
                    reporte.setStrPuntajeSisben(strPuntajeSisben);
                    reporte.setStrGrupoPoblacionEspecial(strGrupoPoblacionEspecial);
                    reporte.setStrNombreARL(strNombreARL);
                    reporte.setStrNombrePensiones(strNombrePensiones);
                    reporte.setStrDireccionResidencia(strDireccionResidencia);
                    reporte.setStrTelefono(strTelefono);
                    reporte.setStrCelular(strCelular);
                    reporte.setStrCorreoElectronico(strCorreoElectronico);
                    reporte.setStrMunicipio(strMunicipio);
                    reporte.setStrTipoZona(strTipoZona);
                    reporte.setStrDepartamento(strDepartamento);
                    reporte.setStrIpsPrimaria(strIpsPrimaria);
                    reporte.setStrCodigoIpsPrimaria(strCodigoIpsPrimaria);

                    //Compañero
                    reporte.setStrPrimerApellidoCompanero(strPrimerApellidoCompanero);
                    reporte.setStrSegundoApellidoCompanero(strSegundoApellidoCompanero);
                    reporte.setStrPrimerNombreCompanero(strPrimerNombreCompanero);
                    reporte.setStrSegundoNombreCompanero(strSegundoNombreCompanero);
                    reporte.setStrTipoDocumentoCompanero(strTipoDocumentoCompanero);
                    reporte.setStrNumeroDocumentoCompanero(strNumeroDocumentoCompanero);
                    reporte.setStrSexoCompanero(strSexoCompanero);
                    reporte.setDtmFechaNacimientoCompanero(dtmFechaNacimientoCompanero);

                    //Beneficiario
                    reporte.setStrPrimerApellidoBeneficiario("");
                    reporte.setStrSegundoApellidoBeneficiario("");
                    reporte.setStrPrimerNombreBeneficiario("");
                    reporte.setStrSegundoNombreBeneficiario("");
                    reporte.setStrTipoDocumentoBeneficiario("");
                    reporte.setStrNumeroDocumentoBeneficiario("");
                    reporte.setStrSexoBeneficiario("");
                    reporte.setDtmFechaNacimientoBeneficiario(null);
                    reporte.setStrParentescoBeneficiario("");
                    reporte.setStrEtniaBeneficiario("");
                    reporte.setStrTipoDiscapacidadBeneficiario("");
                    reporte.setStrCondicionDiscapacidadBeneficiario("");
                    reporte.setStrMunicipioBeneficiario("");
                    reporte.setStrZonaBeneficiario("");
                    reporte.setStrDepartamentoBeneficiario("");
                    reporte.setStrTelefonoBeneficiario("");

                    if (listaGrupoFamiliar.size() > 0) {
                        if (listaGrupoFamiliar.get(0).getParentescoCotizante() != null && !listaGrupoFamiliar.get(0).getParentescoCotizante().equals("1")) {
                            String primerApellido = listaGrupoFamiliar.get(0).getPrimerApellido();
                            String segundoApellido = listaGrupoFamiliar.get(0).getSegundoApellido();
                            String primerNombre = listaGrupoFamiliar.get(0).getPrimerNombre();
                            String segundoNombre = listaGrupoFamiliar.get(0).getSegundoNombre();
                            String tipoDocumento = listadoMaestros.get("" + listaGrupoFamiliar.get(0).getMaeTipoDocumentoId());
                            String numeroDocumento = listaGrupoFamiliar.get(0).getNumeroDocumento();
                            String genero = listadoMaestros.get(listaGrupoFamiliar.get(0).getMaeGeneroId().toString());
                            Date fechaNacimiento = listaGrupoFamiliar.get(0).getFechaNacimiento();
                            String parentezco = listaGrupoFamiliar.get(0).getParentescoCotizante();
                            String etnia = listaGrupoFamiliar.get(0).getEtnia();
                            String discapacidad = listaGrupoFamiliar.get(0).getTipoDiscapacidad() == null ? "" : listaGrupoFamiliar.get(0).getTipoDiscapacidad();
                            String condicion = listaGrupoFamiliar.get(0).getCondicionDiscapacidad() == null ? "" : listaGrupoFamiliar.get(0).getCondicionDiscapacidad();
                            String municipio = listaGrupoFamiliar.get(0).getAfiliacionUbicacionesId() == null ? null : listaGrupoFamiliar.get(0).getAfiliacionUbicacionesId().getNombre();
                            String zona = listaGrupoFamiliar.get(0).getZona();
                            String departamento = listaGrupoFamiliar.get(0).getAfiliacionUbicacionesId() == null ? null : listaGrupoFamiliar.get(0).getAfiliacionUbicacionesId().getGnUbicacionesId().getNombre();
                            String telefono = listaGrupoFamiliar.get(0).getTelefonoMovil();

                            //Beneficiario
                            reporte.setStrPrimerApellidoBeneficiario(primerApellido);
                            reporte.setStrSegundoApellidoBeneficiario(segundoApellido);
                            reporte.setStrPrimerNombreBeneficiario(primerNombre);
                            reporte.setStrSegundoNombreBeneficiario(segundoNombre);
                            reporte.setStrTipoDocumentoBeneficiario(tipoDocumento);
                            reporte.setStrNumeroDocumentoBeneficiario(numeroDocumento);
                            reporte.setStrSexoBeneficiario(genero);
                            reporte.setDtmFechaNacimientoBeneficiario(fechaNacimiento);
                            reporte.setStrParentescoBeneficiario(parentezco);
                            reporte.setStrEtniaBeneficiario(etnia);
                            reporte.setStrTipoDiscapacidadBeneficiario(discapacidad);
                            reporte.setStrCondicionDiscapacidadBeneficiario(condicion);
                            reporte.setStrMunicipioBeneficiario(municipio);
                            reporte.setStrZonaBeneficiario(zona);
                            reporte.setStrDepartamentoBeneficiario(departamento);
                            reporte.setStrTelefonoBeneficiario(telefono);
                        }
                    }
                    listaReportes.add(reporte);
                }
            }

        } catch (NoResultException e) {
            listaReportes = new ArrayList();
        } catch (Exception e) {
            listaReportes = new ArrayList();
        } finally {
            cerrarEntityManager();
        }
        return listaReportes;
    }

    @Override
    public AsegAfiliado consultar(String idAfiliado) throws java.lang.Exception {
        AsegAfiliado afiliadoResult = null;
        try {
            String strQuery = "FROM AsegAfiliados p "
                    + "LEFT JOIN FETCH p.nacionalidadUbicacionesId "
                    + "LEFT JOIN FETCH p.nacimientoUbicacionesId "
                    + "LEFT JOIN FETCH p.gnUbicacionesLugarNacimientoId "
                    + "WHERE p.idAfiliado = '" + idAfiliado + "' ";
            AsegAfiliados obj = (AsegAfiliados) getEntityManager().createQuery(strQuery).getSingleResult();
            afiliadoResult = castEntidadNegocioLargo(obj);
        } catch (NoResultException e) {
            afiliadoResult = null;
        } catch (Exception e) {
            afiliadoResult = null;
        } finally {
            cerrarEntityManager();
        }
        return afiliadoResult;
    }

    @Override
    public AsegAfiliado consultar(String primerApellido, String segundoApellido, String primerNombre, String segundoNombre, Date fechaNacimiento) throws java.lang.Exception {
        AsegAfiliado afiliadoResult = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int i = 0;
        try {
            String strQuery = "FROM AsegAfiliados p "
                    + " WHERE p.fechaNacimiento = '" + sdf.format(fechaNacimiento) + "' "
                    + " AND p.primerApellido = '" + primerApellido + "' ";
            //2020-10-26 jyperez INC 319 incluimos la validación por valor NULO
            if (segundoApellido == null) {
                strQuery = strQuery + " AND p.segundoApellido IS NULL ";
            } else if (segundoApellido.equals("")) {
                strQuery = strQuery + " AND (p.segundoApellido IS NULL OR p.segundoApellido = '" + segundoApellido + "') ";
            } else {
                strQuery = strQuery + " AND p.segundoApellido = '" + segundoApellido + "' ";
            }
            strQuery = strQuery + " AND p.primerNombre = '" + primerNombre + "' ";
            if (segundoNombre == null) {
                strQuery = strQuery + " AND p.segundoNombre IS NULL ";
            } else if (segundoNombre.equals("")) {
                strQuery = strQuery + " AND (p.segundoNombre IS NULL OR p.segundoNombre = '" + segundoNombre + "') ";
            } else {
                strQuery = strQuery + " AND p.segundoNombre = '" + segundoNombre + "' ";
            }
            //2021-04-07 jyperez INC 757 se realiza cambio a recibir una lista debido a que ya se le permitirá a los usuarios crear duplicados.
            // entonces obtendremos el primer registro si existe mas de un duplicado registrado.
            List<AsegAfiliados> listaAfiliados = getEntityManager().createQuery(strQuery).getResultList();
            for (AsegAfiliados af : listaAfiliados) {
                if (i == 0) {
                    afiliadoResult = castEntidadNegocioCorto(af);
                    i++;
                }
            }
        } catch (NoResultException e) {
            afiliadoResult = null;
        } catch (Exception e) {
            afiliadoResult = null;
        } finally {
            cerrarEntityManager();
        }
        return afiliadoResult;
    }
    
    @Override
    public AsegAfiliado consultar(String numeroDocumento, String primerApellido, String segundoApellido, String primerNombre, String segundoNombre, Date fechaNacimiento) throws java.lang.Exception {
        AsegAfiliado afiliadoResult = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int i = 0;
        try {
            String strQuery = "FROM AsegAfiliados p "
                    + " WHERE p.fechaNacimiento = '" + sdf.format(fechaNacimiento) + "' "
                    + " AND p.numeroDocumento = '" + numeroDocumento + "' "
                    + " AND p.primerApellido = '" + primerApellido + "' ";
            //2020-10-26 jyperez INC 319 incluimos la validación por valor NULO
            if (segundoApellido == null) {
                strQuery = strQuery + " AND p.segundoApellido IS NULL ";
            } else if (segundoApellido.equals("")) {
                strQuery = strQuery + " AND (p.segundoApellido IS NULL OR p.segundoApellido = '" + segundoApellido + "') ";
            } else {
                strQuery = strQuery + " AND p.segundoApellido = '" + segundoApellido + "' ";
            }
            strQuery = strQuery + " AND p.primerNombre = '" + primerNombre + "' ";
            if (segundoNombre == null) {
                strQuery = strQuery + " AND p.segundoNombre IS NULL ";
            } else if (segundoNombre.equals("")) {
                strQuery = strQuery + " AND (p.segundoNombre IS NULL OR p.segundoNombre = '" + segundoNombre + "') ";
            } else {
                strQuery = strQuery + " AND p.segundoNombre = '" + segundoNombre + "' ";
            }
            //2021-04-07 jyperez INC 757 se realiza cambio a recibir una lista debido a que ya se le permitirá a los usuarios crear duplicados.
            // entonces obtendremos el primer registro si existe mas de un duplicado registrado.
            List<AsegAfiliados> listaAfiliados = getEntityManager().createQuery(strQuery).getResultList();
            for (AsegAfiliados af : listaAfiliados) {
                if (i == 0) {
                    afiliadoResult = castEntidadNegocioCorto(af);
                    i++;
                }
            }
        } catch (NoResultException e) {
            afiliadoResult = null;
        } catch (Exception e) {
            afiliadoResult = null;
        } finally {
            cerrarEntityManager();
        }
        return afiliadoResult;
    }

    @Override
    public AsegAfiliado consultar(int codigoDocumento, String numeroDocumento, String primerApellido, String segundoApellido, String primerNombre, String segundoNombre, Date fechaNacimiento) throws java.lang.Exception {
        AsegAfiliado afiliadoResult = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String strQuery = "FROM AsegAfiliados p "
                    + " WHERE p.fechaNacimiento = '" + sdf.format(fechaNacimiento) + "' "
                    + " AND p.primerApellido = '" + primerApellido + "' "
                    + " AND p.segundoApellido = '" + segundoApellido + "' "
                    + " AND p.primerNombre = '" + primerNombre + "' "
                    + " AND p.segundoNombre = '" + segundoNombre + "' "
                    + " AND p.maeTipoDocumentoId = " + codigoDocumento + " "
                    + " AND p.numeroDocumento = '" + numeroDocumento + "' ";
            AsegAfiliados obj = (AsegAfiliados) getEntityManager().createQuery(strQuery).getSingleResult();
            afiliadoResult = castEntidadNegocioCorto(obj);
        } catch (NoResultException e) {
            afiliadoResult = null;
        } catch (Exception e) {
            afiliadoResult = null;
        } finally {
            cerrarEntityManager();
        }
        return afiliadoResult;
    }

    @Override
    public List<AsegAfiliado> consultarPorNumeroDocumento(String numeroDocumento) throws Exception {
        List<AsegAfiliado> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegAfiliados p "
                    + " WHERE p.numeroDocumento = '" + numeroDocumento + "' "
                    + " ORDER BY p.id ASC";
            List<AsegAfiliados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegAfiliados per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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
    public List<AsegAfiliado> consultarPorFoneticos(String primerApellido, String segundoApellido, String primerNombre, String segundoNombre) throws java.lang.Exception {
        List<AsegAfiliado> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegAfiliados p "
                    + " WHERE p.codigoFonetico = '" + calcularCodigoFonetico(primerNombre, segundoNombre, primerApellido, segundoApellido) + "' ";
            List<AsegAfiliados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegAfiliados per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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
    public List<AsegAfiliado> consultar(String tipodocumento, String numeroDocumento, String fechaNacimiento, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido) throws Exception {
        List<AsegAfiliado> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM AsegAfiliados p "
                    + "WHERE p.id > 0 ";
            if (!tipodocumento.equals("")) {
                strQuery += "AND p.maeTipoDocumentoId = " + tipodocumento + " ";
            }
            if (!numeroDocumento.equals("")) {
                strQuery += "AND p.numeroDocumento = '" + numeroDocumento + "' ";
            }
            if (!primerNombre.equals("")) {
                strQuery += "AND p.primerNombre = '" + primerNombre + "' ";
            }
            if (!segundoNombre.equals("")) {
                strQuery += "AND p.segundoNombre = '" + segundoNombre + "' ";
            }
            if (!primerApellido.equals("")) {
                strQuery += "AND p.primerApellido = '" + primerApellido + "' ";
            }
            if (!segundoApellido.equals("")) {
                strQuery += "AND p.segundoApellido = '" + segundoApellido + "' ";
            }
            if (!fechaNacimiento.equals("")) {
                strQuery += "AND p.fechaNacimiento = '" + fechaNacimiento + "' ";
            }
            // // ajuste realizado de acuerdo a ticket 1056 - se cambia orden de lista ordenando por estado de afiliado y se agrega estado de afiliado
            strQuery += "ORDER BY p.maeEstadoAfiliacionId ASC";
            List<AsegAfiliados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegAfiliados per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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

    public static String calcularCodigoFonetico(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido) {
        String mensaje = "";
        MetaPhoneEsp metaphone = new MetaPhoneEsp();
        mensaje = metaphone.codificar(primerNombre + " " + segundoNombre + " " + primerApellido + " " + segundoApellido);
        return mensaje;
    }

    @Override
    public int consultarCantidadListaBuscador(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p.maeTipoDocumentoId) FROM AsegAfiliados p "
                    + "WHERE p.maeTipoDocumentoId > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeEstadoAfiliacion":
                            strQuery += "AND p.maeEstadoAfiliacionId = " + e.getValue() + " ";
                            break;
                        case "maeRegimenId":
                            strQuery += "AND p.maeRegimenId = " + e.getValue() + " ";
                            break;
                        case "idAfiliado":
                            strQuery += "AND p.idAfiliado = '" + e.getValue() + "' ";
                            break;
                        case "serialBdua":
                            strQuery += "AND p.serialBdua LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoDocumento":
                            strQuery += "AND p.maeTipoDocumentoId = " + (String) e.getValue() + " ";
                            break;
                        case "numeroDocumento":
                            strQuery += "AND p.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.numeroDocumento = '" + (String) e.getValue() + "' ) ";
                            //strQuery += "AND p.numeroDocumento = '" + (String) e.getValue() + "' ";
                            break;
                        case "primerNombre":
                            strQuery += "AND p.primerNombre = '" + (String) e.getValue() + "' ";
                            break;
                        case "segundoNombre":
                            strQuery += "AND p.segundoNombre = '" + (String) e.getValue() + "' ";
                            break;
                        case "primerApellido":
                            strQuery += "AND p.primerApellido = '" + (String) e.getValue() + "' ";
                            break;
                        case "segundoApellido":
                            strQuery += "AND p.segundoApellido = '" + (String) e.getValue() + "' ";
                            break;
                        case "maeGeneroId":
                            strQuery += "AND p.maeGeneroId = " + (String) e.getValue() + " ";
                            break;
                        case "fechaNacimiento":
                            strQuery += "AND p.fechaNacimiento = '" + (String) e.getValue() + "' ";
                            break;
                        case "afiliacionUbicacion.nombre":
                            strQuery += "AND p.afiliacionUbicacionesId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeNivelSisbenId":
                            strQuery += "AND p.maeNivelSisbenId = " + (String) e.getValue() + " ";
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
                            strQuery += "AND p.primariaCntPrestadorSedesId.nombre =  '" + (String) e.getValue() + "' ";
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
    public List<AsegAfiliado> consultarListaBuscador(ParamConsulta paramConsulta) throws Exception {
        List<AsegAfiliado> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM AsegAfiliados p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeEstadoAfiliacion":
                            strQuery += "AND p.maeEstadoAfiliacionId = " + e.getValue() + " ";
                            break;
                        case "maeRegimenId":
                            strQuery += "AND p.maeRegimenId = " + e.getValue() + " ";
                            break;
                        case "idAfiliado":
                            strQuery += "AND p.idAfiliado = '" + e.getValue() + "' ";
                            break;
                        case "serialBdua":
                            strQuery += "AND p.serialBdua LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoDocumento":
                            strQuery += "AND p.maeTipoDocumentoId = " + (String) e.getValue() + " ";
                            break;
                        case "numeroDocumento":
                            strQuery += "AND p.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.numeroDocumento = '" + (String) e.getValue() + "' ) ";
//                            strQuery += "AND p.numeroDocumento = '" + (String) e.getValue() + "' ";
                            break;
                        case "primerNombre":
                            strQuery += "AND p.primerNombre = '" + (String) e.getValue() + "' ";
                            break;
                        case "segundoNombre":
                            strQuery += "AND p.segundoNombre = '" + (String) e.getValue() + "' ";
                            break;
                        case "primerApellido":
                            strQuery += "AND p.primerApellido = '" + (String) e.getValue() + "' ";
                            break;
                        case "segundoApellido":
                            strQuery += "AND p.segundoApellido = '" + (String) e.getValue() + "' ";
                            break;
                        case "maeGeneroId":
                            strQuery += "AND p.maeGeneroId = " + (String) e.getValue() + " ";
                            break;
                        case "fechaNacimiento":
                            strQuery += "AND p.fechaNacimiento = '" + (String) e.getValue() + "' ";
                            break;
                        case "afiliacionUbicacion.nombre":
                            strQuery += "AND p.afiliacionUbicacionesId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeNivelSisbenId":
                            strQuery += "AND p.maeNivelSisbenId = " + (String) e.getValue() + " ";
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
                            strQuery += "AND p.primariaCntPrestadorSedesId.nombre = '" + (String) e.getValue() + "' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                if (paramConsulta.getOrden().equals("afiliacionUbicacion.nombre")) {
                    strQuery += "p.afiliacionUbicacionesId.nombre "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else if (paramConsulta.getOrden().equals("maeTipoDocumento")) {
                    strQuery += "p.maeTipoDocumentoId "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else if (paramConsulta.getOrden().equals("maeEstadoAfiliacion")) {
                    strQuery += "p.maeEstadoAfiliacionId "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else {
                    strQuery += "p." + paramConsulta.getOrden() + " "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                }
            } else {
                strQuery += "p.id DESC";
            }
            List<AsegAfiliados> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (AsegAfiliados per : list) {
                listResult.add(castEntidadNegocioBuscador(per));
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

    public static AsegAfiliado castEntidadNegocioBuscador(AsegAfiliados per) {
        AsegAfiliado obj = new AsegAfiliado();
        obj.setId(per.getId());
        obj.setIdAfiliado(per.getIdAfiliado());//Contrato
        //obj.setSerialBdua(per.getSerialBdua());
        //obj.setRegistroBdua(per.getRegistroBdua());
        obj.setPrimerNombre(per.getPrimerNombre());
        obj.setSegundoNombre(per.getSegundoNombre());
        obj.setPrimerApellido(per.getPrimerApellido());
        obj.setSegundoApellido(per.getSegundoApellido());
        obj.setFechaNacimiento(per.getFechaNacimiento());
        obj.setFechaExpedicionCedula(per.getFechaExpedicionCedula());
        obj.setGenero(per.getGenero());
        obj.setMaeGeneroId(per.getMaeGeneroId());
        obj.setMaeGeneroCodigo(per.getMaeGeneroCodigo());
        obj.setMaeGeneroValor(per.getMaeGeneroValor());
        obj.setMaeTipoDocumento(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setNumeroDocumento(per.getNumeroDocumento());
        obj.setMaeEstadoAfiliacion(per.getMaeEstadoAfiliacionId());
        obj.setMaeEstadoAfiliacionCodigo(per.getMaeEstadoAfiliacionCodigo());
        obj.setMaeEstadoAfiliacionValor(per.getMaeEstadoAfiliacionValor());
        //obj.setFechaAfiliacionEps(per.getFechaAfiliacionEps());
        //obj.setFechaEgresoEps(per.getFechaEgresoEps());
        obj.setTipoBeneficiario(per.getTipoBeneficiario());
        obj.setMaeTipoAfiliadoId(per.getMaeTipoAfiliadoId());
        obj.setMaeTipoAfiliadoCodigo(per.getMaeTipoAfiliadoCodigo());
        obj.setMaeTipoAfiliadoValor(per.getMaeTipoAfiliadoValor());
        obj.setMaeEstadoAfiliacion(per.getMaeEstadoAfiliacionId());
        obj.setRegimen(per.getRegimen());
        //obj.setNivelSisben(per.getNivelSisben());
        //2021-05-20 jyperez nuevos campos
        obj.setMaeRegimenId(per.getMaeRegimenId());
        obj.setMaeRegimenCodigo(per.getMaeRegimenCodigo());
        obj.setMaeRegimenValor(per.getMaeRegimenValor());
        //obj.setMaeNivelSisbenId(per.getMaeNivelSisbenId());
        //obj.setMaeNivelSisbenCodigo(per.getMaeNivelSisbenCodigo());
        //obj.setMaeNivelSisbenValor(per.getMaeNivelSisbenValor());
        //obj.setCategoriaIbc(per.getCategoriaIbc());
        //2025-07-28 jyperez FALLO campos necesarios para actualización de afiliado en Portabilidad
        obj.setModeloLiquidacion(per.getModeloLiquidacion());
        obj.setMaeModeloLiquidacionId(per.getMaeModeloLiquidacionId());
        obj.setMaeModeloLiquidacionCodigo(per.getMaeModeloLiquidacionCodigo());
        obj.setMaeModeloLiquidacionValor(per.getMaeModeloLiquidacionValor());
        //obj.setMaeTipoDocumentoCf(per.getMaeTipoDocumentoCfId());
        //obj.setNumeroDocumentoCf(per.getNumeroDocumentoCf());
        obj.setDireccionResidencia(per.getDireccionResidencia());
        obj.setZona(per.getZona());
        obj.setMaeZonaId(per.getMaeZonaId());
        obj.setMaeZonaCodigo(per.getMaeZonaCodigo());
        obj.setMaeZonaValor(per.getMaeZonaValor());
        obj.setTelefonoFijo(per.getTelefonoFijo());
        obj.setTelefonoMovil(per.getTelefonoMovil());
        //obj.setEmail(per.getEmail());
        //obj.setAutorizaEnvioEmail(per.getAutorizaEnvioEmail());
        //obj.setAutorizaEnvioSms(per.getAutorizaEnvioSms());
        //obj.setMaeGrupoPoblacional(per.getMaeGrupoPoblacionalId());
        //obj.setEtnia(per.getEtnia());
        //obj.setTienePortabilidad(per.getTienePortabilidad());
        /*if (per.getPuntajeSisben() != null) {
            obj.setPuntajeSisben(per.getPuntajeSisben().doubleValue());
        }
        obj.setFichaSisben(per.getFichaSisben());
        obj.setFechaSisben(per.getFechaSisben());
        obj.setDiscapacidad(per.getDiscapacidad());
        obj.setTipoDiscapacidad(per.getTipoDiscapacidad());
        obj.setVictima(per.getVictima());*/
        obj.setObservacionNovedad(per.getObservacion());
        //2022-10-31 jyperez nuevo campo
        obj.setFechaSuspension(per.getFechaSuspensionEps());
        //2023-10-10 jyperez nuevo campo
        obj.setIncapacidadProlongada(per.getIncapacidadProlongada());
        //2024-01-10 jyperez Is 58 retiro por duplicado
        //2024-03-05 rpalacic Corrección de forma para evitar dato nulo
        obj.setDuplicado((per.getDuplicado() == null) ? false : per.getDuplicado());
        //2024-03-13 jyperez N00-42 traslado preaprobado
        obj.setTrasladoPreaprobado((per.getTrasladoPreaprobado() == null) ? false : per.getTrasladoPreaprobado());
        //2024-03-22 jyperez RES 2335
        obj.setDireccionAlternaContacto(per.getDireccionAlternaContacto());
        obj.setNombreContactoEmergencia(per.getNombreContactoEmergencia());
        obj.setTelefonoContactoEmergencia(per.getTelefonoContactoEmergencia());
        if (per.getPrimariaCntPrestadorSedesId() != null) {
            obj.setPrimariaPrestadorSede(new CntPrestadorSede(per.getPrimariaCntPrestadorSedesId().getId(), per.getPrimariaCntPrestadorSedesId().getNombre(), null));
            //obj.setPrimariaPrestadorSede(castPrestadorSedesEntidadNegocio(per.getPrimariaCntPrestadorSedesId()));
        }
        if (per.getAfiliacionUbicacionesId() != null) {
            obj.setAfiliacionUbicacion(castUbicacionEntidadNegocio(per.getAfiliacionUbicacionesId()));
        }
        if (per.getResidenciaUbicacionId() != null) {
            obj.setResidenciaUbicacion(castUbicacionEntidadNegocio(per.getResidenciaUbicacionId()));
        }
        /*
        if (per.getPortabilidadCntPrestadorSedesId() != null) {
            obj.setPortabilidadPrestadorSede(castPrestadorSedesEntidadNegocio(per.getPortabilidadCntPrestadorSedesId()));
        }*/

        return obj;
    }

    @Override
    public AsegAfiliado consultarActivo(Integer tipodocumento, String numeroDocumento) throws Exception {
        AsegAfiliado afiliadoResult = null;
        try {
            String strQuery = "SELECT DISTINCT p FROM AsegAfiliados p "
                    + " INNER JOIN GnMaestros g ON p.maeEstadoAfiliacionId = g.id "
                    + " WHERE p.maeTipoDocumentoId = " + tipodocumento
                    + " AND p.numeroDocumento = '" + numeroDocumento + "' "
                    + " AND g.gnMaestroAccionesId.id = 3 ";
            AsegAfiliados obj = (AsegAfiliados) getEntityManager().createQuery(strQuery).getSingleResult();
            afiliadoResult = castEntidadNegocioCorto(obj);
        } catch (NoResultException e) {
            afiliadoResult = null;
        } catch (Exception e) {
            afiliadoResult = null;
        } finally {
            cerrarEntityManager();
        }
        return afiliadoResult;
    }

    @Override
    public List<AsegAfiliado> consultarPorTipoDocumentoYNumeroDocumento(Integer tipoDocumento, String numeroDocumento) throws Exception {

        List<AsegAfiliado> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegAfiliados p "
                    + " WHERE p.maeTipoDocumentoId = " + tipoDocumento + " "
                    + " AND p.numeroDocumento = '" + numeroDocumento + "' "
                    + " ORDER BY p.id ASC";
            List<AsegAfiliados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegAfiliados per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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
    public List<AsegAfiliado> consultarPorTipoDocumentoYNumeroDocumento(String codigoTipoDocumento, String numeroDocumento) throws Exception {

        List<AsegAfiliado> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegAfiliados p "
                    + " WHERE p.maeTipoDocumentoCodigo = '" + codigoTipoDocumento + "' "
                    + " AND p.numeroDocumento = '" + numeroDocumento + "' "
                    + " ORDER BY p.id ASC";
            List<AsegAfiliados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegAfiliados per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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

    public String obtenerHistoricoPorAfiliado(int id) throws Exception {
        String mensaje = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //List<AsegAfiliadoDocumento> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegAfiliadoDocumentos p "
                    + " WHERE p.asegAfiliadosId.id = " + id
                    + " ORDER BY p.fechaHoraCrea DESC";
            List<AsegAfiliadoDocumentos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegAfiliadoDocumentos per : list) {
                mensaje = mensaje + per.getMaeTipoDocumentoCodigo() + " " + per.getNumeroDocumento() + " ";
                //2022-09-27 jyperez validamos que la fecha no este vacia
                if (per.getFechaExpedicion() != null) {
                    mensaje = mensaje + sdf.format(per.getFechaExpedicion()) + " \n";
                } else {
                    mensaje = mensaje + "\n";
                }
            }
        } catch (NoResultException e) {
            //listResult = new ArrayList();
            mensaje = "";
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return mensaje;
    }

    private static AsegAfiliadoContactoConsulta castEntidadNegocioAfiliado(AsegAfiliadoContactos ent) {
        AsegAfiliadoContactoConsulta neg = new AsegAfiliadoContactoConsulta();
        neg.setId(ent.getId());
        neg.setNumeroContacto(ent.getNumeroContacto());
        neg.setObservacion(ent.getObservacion());
        neg.setMaeTipoContactoValor(ent.getMaeTipoContactoValor());
        neg.setOrigen(AsegAfiliadoContactoConsulta.ORIGEN_ASEGURAMIENTO);
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        return neg;
    }
}
