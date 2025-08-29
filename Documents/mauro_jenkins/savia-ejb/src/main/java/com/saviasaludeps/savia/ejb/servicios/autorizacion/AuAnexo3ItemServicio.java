/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo3Items;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos3;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupo;
import com.saviasaludeps.savia.ejb.entidades.AuGrupos;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3ItemRemoto;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Stiven Giraldo
 */
@Stateless
@Remote(AuAnexo3ItemRemoto.class)
public class AuAnexo3ItemServicio extends GenericoServicio implements AuAnexo3ItemRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuAnexo3Items p "
                    + "WHERE p.id > 0";

            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND p.auGruposId.id = " + paramConsulta.getParametroConsulta1() + " ";
                strQuery += " AND (p.estado =" + AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA + " OR p.estado =" + AuAnexo3Item.ESTADO_CON_COTIZACION + ") ";
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "auAnexo3Id.id":
                            strQuery += "AND p.auAnexos3Id.id = " + e.getValue() + " ";
                            break;
                        case "maTecnologiaCodigo":
                            strQuery += "AND p.maTecnologiaCodigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "gnUsuarioId.nombre":
                            strQuery += "AND p.gnUsuariosId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maTecnologiaValor":
                            strQuery += "AND p.maTecnologiaValor LIKE '%" + e.getValue() + "%' ";
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
    public List<AuAnexo3Item> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo3Item> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo3Items p "
                    + "WHERE p.id > 0";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND p.auGruposId.id = " + paramConsulta.getParametroConsulta1() + " ";
                strQuery += " AND (p.estado =" + AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA + " OR p.estado =" + AuAnexo3Item.ESTADO_CON_COTIZACION + ") ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "auAnexo3Id.id":
                            strQuery += "AND p.auAnexos3Id.id = " + e.getValue() + " ";
                            break;
                        case "maTecnologiaCodigo":
                            strQuery += "AND p.maTecnologiaCodigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "gnUsuarioId.nombre":
                            strQuery += "AND p.gnUsuariosId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maTecnologiaValor":
                            strQuery += "AND p.maTecnologiaValor LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }

            List<AuAnexo3Items> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexo3Items anexo3Item : list) {
                listaResultados.add(castEntidadNegocio(anexo3Item));
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
    public int consultarCantidadListaCotizacion(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuAnexo3Items p "
                    //                    + "WHERE p.id > 0 ";
                    + "WHERE ((p.posfechado is NULL OR p.posfechado = 0) OR (p.posfechado = 1 AND p.posfechadoPrincipal = 1)) ";

            //Añadir estados opcionales
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND p.estado IN(" + paramConsulta.getParametroConsulta1() + ") ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "auAnexo3Id.id":
                            strQuery += "AND p.auAnexos3Id.id = " + e.getValue() + " ";
                            break;
                        case "auAnexo3Id.cntPrestadorSedeId.nombreSede":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "auAnexo3Id.cntPrestadorSedesId.codigoHabilitacion":
                            strQuery += "AND p.cntPrestdorSedesId.codigoHabilitacion = " + e.getValue() + " ";
                            break;
                        case "auAnexo3Id.cntPrestadorSedeId.codigoPrestador":
                            strQuery += "AND p.cntPrestadorSedesId.codigoPrestador = '" + e.getValue() + "' ";
                            break;
                        case "auAnexo3Id.cntPrestadorSedeId.id":
                            strQuery += "AND p.cntPrestadorSedesId.id = " + e.getValue() + " ";
                            break;
                        case "auAnexo3Id.cntPrestadorSedeId.cntPrestadorId.codigoMinSalud":
                            strQuery += "AND p.cntPrestadorSedesId.cntPrestadoresId.codigoMinSalud = " + e.getValue() + " ";
                            break;
                        case "auAnexo3Id.cntPrestadorSedeId.cntPrestadorId.id":
                            strQuery += "AND p.cntPrestadorSedesId.cntPrestadoresId.id = " + e.getValue() + " ";
                            break;
                        case "maTecnologiaCodigo":
                            strQuery += "AND p.maTecnologiaCodigo = '" + e.getValue() + "' ";
                            break;
                        case "auAnexo3Id.asegAfiliadoId.maeTipoDocumento":
                            strQuery += "AND p.auAnexos3Id.asegAfiliadosId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "auAnexo3Id.asegAfiliadoId.numeroDocumento":
                            strQuery += "AND p.auAnexos3Id.asegAfiliadosId.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.numeroDocumento = '" + (String) e.getValue() + "' ) ";
                            break;
                        case "auAnexo3Id.asegAfiliadoId.primerApellido":
                            strQuery += "AND (p.auAnexos3Id.asegAfiliadosId.primerApellido LIKE '%" + e.getValue() + "%' ";
                            strQuery += "OR p.auAnexos3Id.asegAfiliadosId.segundoApellido LIKE '%" + e.getValue() + "%' ";
                            strQuery += "OR CONCAT(p.auAnexos3Id.asegAfiliadosId.primerApellido, ' ', p.auAnexos3Id.asegAfiliadosId.segundoApellido) LIKE '%" + e.getValue() + "%') ";
                            break;
                        case "auAnexo3Id.asegAfiliadoId.primerNombre":
                            strQuery += "AND (p.auAnexos3Id.asegAfiliadosId.primerNombre LIKE '%" + e.getValue() + "%' ";
                            strQuery += "OR p.auAnexos3Id.asegAfiliadosId.segundoNombre LIKE '%" + e.getValue() + "%' ";
                            strQuery += "OR CONCAT(p.auAnexos3Id.asegAfiliadosId.primerNombre, ' ', p.auAnexos3Id.asegAfiliadosId.segundoNombre) LIKE '%" + e.getValue() + "%') ";
                            break;
                        case "auAnexo3Id.asegAfiliadoId.afiliacionUbicacion.nombre":
                            strQuery += "AND p.auAnexos3Id.asegAfiliadosId.afiliacionUbicacionesId.tipo = " + Ubicacion.TIPO_MUNICIPIO + " ";
                            strQuery += "AND p.auAnexos3Id.asegAfiliadosId.afiliacionUbicacionesId.nombre = '" + e.getValue() + "' ";
                            break;
                        case "auAnexo3Id.fechaSolicitud":
                            strQuery += "AND date_format(p.auAnexos3Id.fechaSolicitud , '%Y-%m-%d') = '" + e.getValue() + "' ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "tipoTecnologia":
                            strQuery += "AND p.tipoTecnologia = " + e.getValue() + " ";
                            break;
                        case "maTecnologiaValor":
                            strQuery += "AND p.maTecnologiaValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maDiagnosticoValor":
                            strQuery += "AND p.maDiagnosticoValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "auAnexo3Id.cntPrestadorSedeId.cntPrestador.razonSocial":
                            strQuery += "AND p.auAnexos3Id.cntPrestadorSedesId.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "auAnexo3Id.maeAmbitoAtencionValor":
                            strQuery += "AND p.auAnexos3Id.maeAmbitoAtencionId = " + e.getValue() + " ";
                            break;
                        case "auAnexo3Id.fechaHoraCrea":
                            strQuery += "AND date_format(p.auAnexos3Id.fechaHoraCrea , '%Y-%m-%d') = '" + e.getValue() + "' ";
                            break;
                        case "auAnexo3Id.asegAfiliadoId.tienePortabilidad":
                            strQuery += "AND p.auAnexos3Id.asegAfiliadosId.tienePortabilidad = " + e.getValue() + " ";
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
    public List<AuAnexo3Item> consultarListaCotizacion(ParamConsulta paramConsulta) throws Exception {
        long startTime = System.currentTimeMillis();
        long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        List<AuAnexo3Item> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo3Items p "
                    + "WHERE ((p.posfechado is NULL OR p.posfechado = 0) OR (p.posfechado = 1 AND p.posfechadoPrincipal = 1)) ";
            //Añadir estados opcionales
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND p.estado IN(" + paramConsulta.getParametroConsulta1() + ") ";
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "auAnexo3Id.id":
                            strQuery += "AND p.auAnexos3Id.id = " + e.getValue() + " ";
                            break;
                        case "auAnexo3Id.cntPrestadorSedeId.nombreSede":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "auAnexo3Id.cntPrestadorSedesId.codigoHabilitacion":
                            strQuery += "AND p.cntPrestdorSedesId.codigoHabilitacion = " + e.getValue() + " ";
                            break;
                        case "auAnexo3Id.cntPrestadorSedeId.codigoPrestador":
                            strQuery += "AND p.cntPrestadorSedesId.codigoPrestador = '" + e.getValue() + "' ";
                            break;
                        case "auAnexo3Id.cntPrestadorSedeId.id":
                            strQuery += "AND p.cntPrestadorSedesId.id = " + e.getValue() + " ";
                            break;
                        case "auAnexo3Id.cntPrestadorSedeId.cntPrestadorId.codigoMinSalud":
                            strQuery += "AND p.cntPrestadorSedesId.cntPrestadorId.codigoMinSalud = " + e.getValue() + " ";
                            break;
                        case "auAnexo3Id.cntPrestadorSedeId.cntPrestadorId.id":
                            strQuery += "AND p.cntPrestadorSedesId.cntPrestadoresId.id = " + e.getValue() + " ";
                            break;
                        case "maTecnologiaCodigo":
                            strQuery += "AND p.maTecnologiaCodigo = '" + e.getValue() + "' ";
                            break;
                        case "auAnexo3Id.asegAfiliadoId.maeTipoDocumento":
                            strQuery += "AND p.auAnexos3Id.asegAfiliadosId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "auAnexo3Id.asegAfiliadoId.numeroDocumento":
                            strQuery += "AND p.auAnexos3Id.asegAfiliadosId.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.numeroDocumento = '" + (String) e.getValue() + "' ) ";
                            break;
                        case "auAnexo3Id.asegAfiliadoId.primerApellido":
                            strQuery += "AND (p.auAnexos3Id.asegAfiliadosId.primerApellido LIKE '%" + e.getValue() + "%' ";
                            strQuery += "OR p.auAnexos3Id.asegAfiliadosId.segundoApellido LIKE '%" + e.getValue() + "%' ";
                            strQuery += "OR CONCAT(p.auAnexos3Id.asegAfiliadosId.primerApellido, ' ', p.auAnexos3Id.asegAfiliadosId.segundoApellido) LIKE '%" + e.getValue() + "%') ";
                            break;
                        case "auAnexo3Id.asegAfiliadoId.primerNombre":
                            strQuery += "AND (p.auAnexos3Id.asegAfiliadosId.primerNombre LIKE '%" + e.getValue() + "%' ";
                            strQuery += "OR p.auAnexos3Id.asegAfiliadosId.segundoNombre LIKE '%" + e.getValue() + "%' ";
                            strQuery += "OR CONCAT(p.auAnexos3Id.asegAfiliadosId.primerNombre, ' ', p.auAnexos3Id.asegAfiliadosId.segundoNombre) LIKE '%" + e.getValue() + "%') ";
                            break;
                        case "auAnexo3Id.asegAfiliadoId.afiliacionUbicacion.nombre":
                            strQuery += "AND p.auAnexos3Id.asegAfiliadosId.afiliacionUbicacionesId.tipo = " + Ubicacion.TIPO_MUNICIPIO + " ";
                            strQuery += "AND p.auAnexos3Id.asegAfiliadosId.afiliacionUbicacionesId.nombre = '" + e.getValue() + "' ";
                            break;
                        case "auAnexo3Id.fechaSolicitud":
                            strQuery += "AND date_format(p.auAnexos3Id.fechaSolicitud , '%Y-%m-%d') = '" + e.getValue() + "' ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "tipoTecnologia":
                            strQuery += "AND p.tipoTecnologia = " + e.getValue() + " ";
                            break;
                        case "maTecnologiaValor":
                            strQuery += "AND p.maTecnologiaValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maDiagnosticoValor":
                            strQuery += "AND p.maDiagnosticoValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "auAnexo3Id.cntPrestadorSedeId.cntPrestador.razonSocial":
                            strQuery += "AND p.auAnexos3Id.cntPrestadorSedesId.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "auAnexo3Id.maeAmbitoAtencionValor":
                            strQuery += "AND p.auAnexos3Id.maeAmbitoAtencionId = " + e.getValue() + " ";
                            break;
                        case "auAnexo3Id.fechaHoraCrea":
                            strQuery += "AND date_format(p.auAnexos3Id.fechaHoraCrea , '%Y-%m-%d') = '" + e.getValue() + "' ";
                            break;
                        case "auAnexo3Id.asegAfiliadoId.tienePortabilidad":
                            strQuery += "AND p.auAnexos3Id.asegAfiliadosId.tienePortabilidad = " + e.getValue() + " ";
                            break;
                    }
                }
            }

            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }

            List<AuAnexo3Items> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexo3Items anexo3Item : list) {
                listaResultados.add(castEntidadNegocioCotizacion(anexo3Item));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        long endTime = System.currentTimeMillis();
        long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Tiempo de ejecución metodo consultarListaCotizacion:  " + (endTime - startTime) + " ms");
        System.out.println("Memoria consumida metodo consultarListaCotizacion: " + (endMemory - startMemory) + " bytes");
        return listaResultados;

    }

    @Override
    public AuAnexo3Item consultar(int id) throws Exception {
        AuAnexo3Item objRes = null;
        try {
            objRes = castEntidadNegocio((AuAnexo3Items) getEntityManager().find(AuAnexo3Items.class, id));
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
    public AuAnexo3Item consultarParaCotizacion(int id) throws Exception {
        AuAnexo3Item objRes = null;
        try {
            objRes = castEntidadNegocioCotizacionGestion((AuAnexo3Items) getEntityManager().find(AuAnexo3Items.class, id));
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
    public int insertar(AuAnexo3Item obj) throws Exception {
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
    public void actualizar(AuAnexo3Item obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexo3Items a SET ";
            if (obj.getAuAnexo3Id() != null && obj.getAuAnexo3Id().getId() != null) {
                strQuery += "a.auAnexos3Id.id = :auAnexos3Id ,";
            }
            if (obj.getAuGrupoId() != null && obj.getAuGrupoId().getId() != null) {
                strQuery += "a.auGruposId.id = :auGruposId ,";
            }
            strQuery += "a.gnUsuariosId.id = :gnUsuariosId ,";
            strQuery += "a.estado = :estado ,";

            strQuery += "a.tipoTecnologia = :tipoTecnologia ,";
            strQuery += "a.maTecnologiaId = :maTecnologiaId ,";
            strQuery += "a.maTecnologiaCodigo = :maTecnologiaCodigo ,";
            strQuery += "a.maTecnologiaValor = :maTecnologiaValor ,";
            strQuery += "a.cantidadSolicitada = :cantidadSolicitada ,";
            strQuery += "a.maServicioSolicitadoId = :maServicioSolicitadoId ,";
            strQuery += "a.maServicioSolicitadoCodigo = :maServicioSolicitadoCodigo ,";
            strQuery += "a.maServicioSolicitadoValor = :maServicioSolicitadoValor ,";
            if (obj.getMaeEstadoMotivoItemId() != null) {
                strQuery += "a.maeEstadoMotivoItemId = :maeEstadoMotivoItemId ,";
                strQuery += "a.maeEstadoMotivoItemCodigo = :maeEstadoMotivoItemCodigo ,";
                strQuery += "a.maeEstadoMotivoItemValor = :maeEstadoMotivoItemValor ,";
                strQuery += "a.estadoJustificacion = :estadoJustificacion,";
            }
            if (obj.getMaeCausaExternaId() != null) {
                strQuery += "a.maeCausaExternaId = :maeCausaExternaId ,";
                strQuery += "a.maeCausaExternaCodigo = :maeCausaExternaCodigo ,";
                strQuery += "a.maeCausaExternaValor = :maeCausaExternaValor ,";
            }
            if (obj.getMaeFinalidadId() != null) {
                strQuery += "a.maeFinalidadId = :maeFinalidadId ,";
                strQuery += "a.maeFinalidadCodigo = :maeFinalidadCodigo ,";
                strQuery += "a.maeFinalidadValor = :maeFinalidadValor ,";
            }
            if (obj.getMaeTipoCatastroficoId() != null) {
                strQuery += "a.maeTipoCatastroficoId = :maeTipoCatastroficoId ,";
                strQuery += "a.maeTipoCatastroficoCodigo = :maeTipoCatastroficoCodigo ,";
                strQuery += "a.maeTipoCatastroficoValor = :maeTipoCatastroficoValor ,";
            }
            if (obj.getFrecuencia() != null) {
                strQuery += "a.frecuencia = :frecuencia ,";
            }
            if (obj.getMaeViaAdministracionId() != null) {
                strQuery += "a.maeViaAdministracionId = :maeViaAdministracionId ,";
                strQuery += "a.maeViaAdministracionCodigo = :maeViaAdministracionCodigo ,";
                strQuery += "a.maeViaAdministracionValor = :maeViaAdministracionValor ,";
            }
            strQuery += "a.maeAmbitoId = :maeAmbitoId ,";
            strQuery += "a.maeAmbitoCodigo = :maeAmbitoCodigo ,";
            strQuery += "a.maeAmbitoValor = :maeAmbitoValor ,";
            if (obj.getNivel() != null) {
                strQuery += "a.nivel = :nivel ,";
            }
            strQuery += "a.maDiagnosticoId = :maDiagnosticoId ,";
            strQuery += "a.maDiagnosticoCodigo = :maDiagnosticoCodigo ,";
            strQuery += "a.maDiagnosticoValor = :maDiagnosticoValor ,";
            strQuery += "a.prequirurgico = :prequirurgico ,";
            strQuery += "a.fechaFormula = :fechaFormula ,";
            strQuery += "a.dosis = :dosis ,";
            strQuery += "a.posologia = :posologia ,";
            strQuery += "a.posfechado = :posfechado ,";
            strQuery += "a.fechaPosfechado = :fechaPosfechado, ";
            strQuery += "a.posfechadoPrincipal = :posfechadoPrincipal, ";
            strQuery += "a.efectosAdversos = :efectosAdversos, ";
            strQuery += "a.usuarioCrea = :usuarioCrea ,";
            strQuery += "a.terminalCrea = :terminalCrea ,";
            strQuery += "a.fechaHoraCrea = :fechaHoraCrea ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            if (obj.getAuAnexo3Id() != null && obj.getAuAnexo3Id().getId() != null) {
                query.setParameter("auAnexos3Id", obj.getAuAnexo3Id().getId());
            }
            if (obj.getAuGrupoId() != null && obj.getAuGrupoId().getId() != null) {
                query.setParameter("auGruposId", obj.getAuGrupoId().getId());
            }
            if (obj.getGnUsuarioId() != null && obj.getGnUsuarioId().getId() != null) {
                query.setParameter("gnUsuariosId", obj.getGnUsuarioId().getId());
            } else {
                query.setParameter("gnUsuariosId", null);
            }
            query.setParameter("estado", obj.getEstado());

            query.setParameter("tipoTecnologia", obj.getTipoTecnologia());
            query.setParameter("maTecnologiaId", obj.getMaTecnologiaId());
            query.setParameter("maTecnologiaCodigo", obj.getMaTecnologiaCodigo());
            query.setParameter("maTecnologiaValor", obj.getMaTecnologiaValor());
            query.setParameter("cantidadSolicitada", obj.getCantidadSolicitada());
            query.setParameter("maServicioSolicitadoId", obj.getMaServicioSolicitadoId());
            query.setParameter("maServicioSolicitadoCodigo", obj.getMaServicioSolicitadoCodigo());
            query.setParameter("maServicioSolicitadoValor", obj.getMaServicioSolicitadoValor());
            if (obj.getMaeEstadoMotivoItemId() != null) {
                query.setParameter("maeEstadoMotivoItemId", obj.getMaeEstadoMotivoItemId());
                query.setParameter("maeEstadoMotivoItemCodigo", obj.getMaeEstadoMotivoItemCodigo());
                query.setParameter("maeEstadoMotivoItemValor", obj.getMaeEstadoMotivoItemValor());
                query.setParameter("estadoJustificacion", obj.getEstadoJustificacion());
            }
            if (obj.getMaeCausaExternaId() != null) {
                query.setParameter("maeCausaExternaId", obj.getMaeCausaExternaId());
                query.setParameter("maeCausaExternaCodigo", obj.getMaeCausaExternaCodigo());
                query.setParameter("maeCausaExternaValor", obj.getMaeCausaExternaValor());
            }
            if (obj.getMaeFinalidadId() != null) {
                query.setParameter("maeFinalidadId", obj.getMaeFinalidadId());
                query.setParameter("maeFinalidadCodigo", obj.getMaeFinalidadCodigo());
                query.setParameter("maeFinalidadValor", obj.getMaeFinalidadValor());
            }
            if (obj.getMaeTipoCatastroficoId() != null) {
                query.setParameter("maeTipoCatastroficoId", obj.getMaeTipoCatastroficoId());
                query.setParameter("maeTipoCatastroficoCodigo", obj.getMaeTipoCatastroficoCodigo());
                query.setParameter("maeTipoCatastroficoValor", obj.getMaeTipoCatastroficoValor());
            }
            if (obj.getFrecuencia() != null) {
                query.setParameter("frecuencia", obj.getFrecuencia());
            }
            if (obj.getMaeViaAdministracionId() != null) {
                query.setParameter("maeViaAdministracionId", obj.getMaeViaAdministracionId());
                query.setParameter("maeViaAdministracionCodigo", obj.getMaeViaAdministracionCodigo());
                query.setParameter("maeViaAdministracionValor", obj.getMaeViaAdministracionValor());
            }
            query.setParameter("maeAmbitoId", obj.getMaeAmbitoId());
            query.setParameter("maeAmbitoCodigo", obj.getMaeAmbitoCodigo());
            query.setParameter("maeAmbitoValor", obj.getMaeAmbitoValor());
            if (obj.getNivel() != null) {
                query.setParameter("nivel", obj.getNivel());
            }
            query.setParameter("maDiagnosticoId", obj.getMaDiagnosticoId());
            query.setParameter("maDiagnosticoCodigo", obj.getMaDiagnosticoCodigo());
            query.setParameter("maDiagnosticoValor", obj.getMaDiagnosticoValor());
            query.setParameter("prequirurgico", obj.getPrequirurgico());
            query.setParameter("fechaFormula", obj.getFechaFormula());
            query.setParameter("dosis", obj.getDosis());
            query.setParameter("posologia", obj.getPosologia());
            query.setParameter("posfechado", obj.isPosfechado());
            query.setParameter("fechaPosfechado", obj.getFechaPosfechado());
            query.setParameter("posfechadoPrincipal", obj.getPosfechadoPrincipal());
            query.setParameter("efectosAdversos", obj.getEfectosAdversos());
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
            System.out.println(e.toString() + e.getMessage());
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
            System.out.println(e.toString() + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarEstado(AuAnexo3Item obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexo3Items a SET ";
            strQuery += "a.estado = :estado, ";
            strQuery += "a.estadoJustificacion = :estadoJustificacion, ";
            strQuery += "a.maeEstadoMotivoItemId = :maeEstadoMotivoItemId, ";
            strQuery += "a.maeEstadoMotivoItemCodigo = :maeEstadoMotivoItemCodigo, ";
            strQuery += "a.maeEstadoMotivoItemValor = :maeEstadoMotivoItemValor, ";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += "WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("estadoJustificacion", obj.getEstadoJustificacion());
            query.setParameter("maeEstadoMotivoItemId", obj.getMaeEstadoMotivoItemId());
            query.setParameter("maeEstadoMotivoItemCodigo", obj.getMaeEstadoMotivoItemCodigo());
            query.setParameter("maeEstadoMotivoItemValor", obj.getMaeEstadoMotivoItemValor());
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
    public AuAnexo3Item eliminar(int id) throws Exception {
        AuAnexo3Item obj = null;
        try {
            AuAnexo3Items ent = getEntityManager().find(AuAnexo3Items.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    private static AuAnexo3Item castEntidadNegocio(AuAnexo3Items ent) {
        AuAnexo3Item negocio = new AuAnexo3Item();
        negocio.setId(ent.getId());
        negocio.setAuAnexo3Id(new AuAnexo3(ent.getAuAnexos3Id().getId()));
        negocio.getAuAnexo3Id().setPrioridad(ent.getAuAnexos3Id().getPrioridad());
        if (ent.getAuGruposId() != null) {
            negocio.setAuGrupoId(new AuGrupo(ent.getAuGruposId().getId()));
            negocio.getAuGrupoId().setNombre(ent.getAuGruposId().getNombre());
            negocio.getAuGrupoId().setDescripcion(ent.getAuGruposId().getDescripcion());
        }
        if (ent.getGnUsuariosId() != null) {
            negocio.setGnUsuarioId(new Usuario(ent.getGnUsuariosId().getId()));
            negocio.getGnUsuarioId().setNombre(ent.getGnUsuariosId().getNombre());
        }
        negocio.setEstado(ent.getEstado());
        negocio.setTipoTecnologia(ent.getTipoTecnologia());
        negocio.setMaTecnologiaId(ent.getMaTecnologiaId());
        negocio.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        negocio.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        negocio.setCantidadSolicitada(ent.getCantidadSolicitada());
        negocio.setMaMedicamentoId(ent.getMaMedicamentoId());
        negocio.setMaMedicamentoCodigo(ent.getMaMedicamentoCodigo());
        negocio.setMaMedicamentoValor(ent.getMaMedicamentoValor());
        
        if (ent.getMaServicioSolicitadoId() != null) {
            negocio.setMaServicioSolicitadoId(ent.getMaServicioSolicitadoId());
            negocio.setMaServicioSolicitadoCodigo(ent.getMaServicioSolicitadoCodigo());
            negocio.setMaServicioSolicitadoValor(ent.getMaServicioSolicitadoValor());
        }

        if (ent.getMaeEstadoMotivoItemId() != null) {
            negocio.setMaeEstadoMotivoItemId(ent.getMaeEstadoMotivoItemId());
            negocio.setMaeEstadoMotivoItemCodigo(ent.getMaeEstadoMotivoItemCodigo());
            negocio.setMaeEstadoMotivoItemValor(ent.getMaeEstadoMotivoItemValor());
        }
        if (ent.getEstadoJustificacion() != null) {
            negocio.setEstadoJustificacion(ent.getEstadoJustificacion());
        }
        if (ent.getMaeCausaExternaId() != null) {
            negocio.setMaeCausaExternaId(ent.getMaeCausaExternaId());
            negocio.setMaeCausaExternaCodigo(ent.getMaeCausaExternaCodigo());
            negocio.setMaeCausaExternaValor(ent.getMaeCausaExternaValor());
        }
        if (ent.getMaeFinalidadId() != null) {
            negocio.setMaeFinalidadId(ent.getMaeFinalidadId());
            negocio.setMaeFinalidadCodigo(ent.getMaeFinalidadCodigo());
            negocio.setMaeFinalidadValor(ent.getMaeFinalidadValor());
        }
        if (ent.getMaeTipoCatastroficoId() != null) {
            negocio.setMaeTipoCatastroficoId(ent.getMaeTipoCatastroficoId());
            negocio.setMaeTipoCatastroficoCodigo(ent.getMaeTipoCatastroficoCodigo());
            negocio.setMaeTipoCatastroficoValor(ent.getMaeTipoCatastroficoValor());
        }
        negocio.setFrecuencia(ent.getFrecuencia());
        if (ent.getMaeViaAdministracionId() != null) {
            negocio.setMaeViaAdministracionId(ent.getMaeViaAdministracionId());
            negocio.setMaeViaAdministracionCodigo(ent.getMaeViaAdministracionCodigo());
            negocio.setMaeViaAdministracionValor(ent.getMaeViaAdministracionValor());
        }
        if (ent.getMaeAmbitoId() != null) {
            negocio.setMaeAmbitoId(ent.getMaeAmbitoId());
            negocio.setMaeAmbitoCodigo(ent.getMaeAmbitoCodigo());
            negocio.setMaeAmbitoValor(ent.getMaeAmbitoValor());
        }
        negocio.setNivel(ent.getNivel());
        if (ent.getMaDiagnosticoId() != null) {
            negocio.setMaDiagnosticoId(ent.getMaDiagnosticoId());
            negocio.setMaDiagnosticoCodigo(ent.getMaDiagnosticoCodigo());
            negocio.setMaDiagnosticoValor(ent.getMaDiagnosticoValor());
        }
        if (ent.getPrequirurgico() != null) {
            negocio.setPrequirurgico(ent.getPrequirurgico());
        }
        if (ent.getAuItemBitacorasList() != null) {
            negocio.setAuItemBitacorasList(AuItemBitacoraServicio.castListaEntidadNegocio(ent.getAuItemBitacorasList()));
        }
        negocio.setFechaFormula(ent.getFechaFormula());
        negocio.setDosis(ent.getDosis());
        negocio.setPosologia(ent.getPosologia());
        negocio.setPosfechado(ent.getPosfechado() == null ? false : ent.getPosfechado());
        negocio.setFechaPosfechado(ent.getFechaPosfechado());
        negocio.setPosfechadoPrincipal(ent.getPosfechadoPrincipal() == null ? false : ent.getPosfechadoPrincipal());
        negocio.setEfectosAdversos(ent.getEfectosAdversos() == null ? false : ent.getEfectosAdversos());
        negocio.setUsuarioCrea(ent.getUsuarioCrea());
        negocio.setTerminalCrea(ent.getTerminalCrea());
        negocio.setFechaHoraCrea(ent.getFechaHoraCrea());
        negocio.setUsuarioModifica(ent.getUsuarioModifica());
        negocio.setTerminalModifica(ent.getTerminalModifica());
        negocio.setFechaHoraModifica(ent.getFechaHoraModifica());
        return negocio;
    }

    private static AuAnexo3Item castEntidadNegocioCotizacion(AuAnexo3Items ent) {
        AuAnexo3Item negocio = new AuAnexo3Item();
        negocio.setId(ent.getId());
        AuAnexo3 anexo3 = new AuAnexo3(ent.getAuAnexos3Id().getId());
        anexo3.setMaeAmbitoAtencionValor(ent.getAuAnexos3Id().getMaeAmbitoAtencionValor());
        anexo3.setFechaHoraCrea(ent.getAuAnexos3Id().getFechaHoraCrea());
        negocio.setAuAnexo3Id(anexo3);
        negocio.setEstado(ent.getEstado());
        negocio.setTipoTecnologia(ent.getTipoTecnologia());
        negocio.setMaTecnologiaId(ent.getMaTecnologiaId());
        negocio.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        negocio.setNivel(ent.getNivel());
        if (ent.getMaDiagnosticoId() != null) {
            negocio.setMaDiagnosticoId(ent.getMaDiagnosticoId());
            negocio.setMaDiagnosticoCodigo(ent.getMaDiagnosticoCodigo());
            negocio.setMaDiagnosticoValor(ent.getMaDiagnosticoValor());
        }
        if (ent.getAuAnexos3Id().getAsegAfiliadosId() != null) {
            AsegAfiliado afiliado = new AsegAfiliado();
            afiliado.setPrimerNombre(ent.getAuAnexos3Id().getAsegAfiliadosId().getPrimerNombre());
            if (ent.getAuAnexos3Id().getAsegAfiliadosId().getSegundoNombre() != null) {
                afiliado.setSegundoNombre(ent.getAuAnexos3Id().getAsegAfiliadosId().getSegundoNombre());
            }
            afiliado.setPrimerApellido(ent.getAuAnexos3Id().getAsegAfiliadosId().getPrimerApellido());
            if (ent.getAuAnexos3Id().getAsegAfiliadosId().getSegundoApellido() != null) {
                afiliado.setSegundoApellido(ent.getAuAnexos3Id().getAsegAfiliadosId().getSegundoApellido());
            }
            afiliado.setMaeTipoDocumento(ent.getAuAnexos3Id().getAsegAfiliadosId().getMaeTipoDocumentoId());
            afiliado.setMaeTipoDocumentoValor(ent.getAuAnexos3Id().getAsegAfiliadosId().getMaeTipoDocumentoValor());
            afiliado.setMaeTipoDocumentoCodigo(ent.getAuAnexos3Id().getAsegAfiliadosId().getMaeTipoDocumentoCodigo());
            afiliado.setNumeroDocumento(ent.getAuAnexos3Id().getAsegAfiliadosId().getNumeroDocumento());
            afiliado.setTienePortabilidad(ent.getAuAnexos3Id().getAsegAfiliadosId().getTienePortabilidad());
            if (ent.getAuAnexos3Id().getAsegAfiliadosId().getAfiliacionUbicacionesId() != null) {
                Ubicacion ubicacion = new Ubicacion();
                ubicacion.setNombre(ent.getAuAnexos3Id().getAsegAfiliadosId().getAfiliacionUbicacionesId().getNombre());
                afiliado.setAfiliacionUbicacion(ubicacion);
            }
            negocio.getAuAnexo3Id().setAsegAfiliadoId(afiliado);
        }
        if (ent.getAuAnexos3Id().getCntPrestadorSedesId() != null) {
            CntPrestadorSede sede = new CntPrestadorSede();
            CntPrestador prestador = new CntPrestador();
            prestador.setRazonSocial(ent.getAuAnexos3Id()
                    .getCntPrestadorSedesId()
                    .getCntPrestadoresId()
                    .getRazonSocial());
            sede.setCntPrestador(prestador);
            negocio.getAuAnexo3Id().setCntPrestadorSedeId(sede);
        }
        if (ent.getAuItemBitacorasList() != null) {
            negocio.setAuItemBitacorasList(AuItemBitacoraServicio.castListaEntidadNegocio(ent.getAuItemBitacorasList()));
        }
        negocio.getAuAnexo3Id().setFechaSolicitud(ent.getAuAnexos3Id().getFechaSolicitud());
        negocio.setPosfechado(ent.getPosfechado() == null ? false : ent.getPosfechado());
        negocio.setPosfechadoPrincipal(ent.getPosfechadoPrincipal() == null ? false : ent.getPosfechadoPrincipal());
        negocio.setUsuarioCrea(ent.getUsuarioCrea());
        negocio.setTerminalCrea(ent.getTerminalCrea());
        negocio.setFechaHoraCrea(ent.getFechaHoraCrea());
        negocio.setFechaHoraModifica(ent.getFechaHoraModifica());
        return negocio;
    }

    private static AuAnexo3Items castNegocioEntidad(AuAnexo3Item negocio) {
        AuAnexo3Items entidad = new AuAnexo3Items();
        entidad.setId(negocio.getId());
        entidad.setAuAnexos3Id(new AuAnexos3(negocio.getAuAnexo3Id().getId()));
        if (negocio.getAuGrupoId() != null) {
            entidad.setAuGruposId(new AuGrupos(negocio.getAuGrupoId().getId()));
        }
        if (negocio.getGnUsuarioId() != null) {
            entidad.setGnUsuariosId(new GnUsuarios(negocio.getGnUsuarioId().getId()));
        }
        entidad.setEstado(negocio.getEstado());

        entidad.setTipoTecnologia(negocio.getTipoTecnologia());
        if (negocio.getMaTecnologiaId() > 0) {
            entidad.setMaTecnologiaId(negocio.getMaTecnologiaId());
            entidad.setMaTecnologiaCodigo(negocio.getMaTecnologiaCodigo());
            entidad.setMaTecnologiaValor(negocio.getMaTecnologiaValor());
        }
        if (negocio.getMaMedicamentoId() != null) {
            entidad.setMaMedicamentoId(negocio.getMaMedicamentoId());
            entidad.setMaMedicamentoCodigo(negocio.getMaMedicamentoCodigo());
            entidad.setMaMedicamentoValor(negocio.getMaMedicamentoValor());
        }
        entidad.setCantidadSolicitada(negocio.getCantidadSolicitada());
        if (negocio.getMaServicioSolicitadoId() != null) {
            entidad.setMaServicioSolicitadoId(negocio.getMaServicioSolicitadoId());
            entidad.setMaServicioSolicitadoCodigo(negocio.getMaServicioSolicitadoCodigo());
            entidad.setMaServicioSolicitadoValor(negocio.getMaServicioSolicitadoValor());
        }
        if (negocio.getMaeEstadoMotivoItemId() != null) {
            entidad.setMaeEstadoMotivoItemId(negocio.getMaeEstadoMotivoItemId());
            entidad.setMaeEstadoMotivoItemCodigo(negocio.getMaeEstadoMotivoItemCodigo());
            entidad.setMaeEstadoMotivoItemValor(negocio.getMaeEstadoMotivoItemValor());
            if (negocio.getEstadoJustificacion() != null) {
                entidad.setEstadoJustificacion(negocio.getEstadoJustificacion());
            }
        }
        if (negocio.getMaeCausaExternaId() != null) {
            entidad.setMaeCausaExternaId(negocio.getMaeCausaExternaId());
            entidad.setMaeCausaExternaCodigo(negocio.getMaeCausaExternaCodigo());
            entidad.setMaeCausaExternaValor(negocio.getMaeCausaExternaValor());
        }
        if (negocio.getMaeFinalidadId() != null) {
            entidad.setMaeFinalidadId(negocio.getMaeFinalidadId());
            entidad.setMaeFinalidadCodigo(negocio.getMaeFinalidadCodigo());
            entidad.setMaeFinalidadValor(negocio.getMaeFinalidadValor());
        }
        if (negocio.getMaeTipoCatastroficoId() != null) {
            entidad.setMaeTipoCatastroficoId(negocio.getMaeTipoCatastroficoId());
            entidad.setMaeTipoCatastroficoCodigo(negocio.getMaeTipoCatastroficoCodigo());
            entidad.setMaeTipoCatastroficoValor(negocio.getMaeTipoCatastroficoValor());
        }
        if (negocio.getFrecuencia() != null) {
            entidad.setFrecuencia(negocio.getFrecuencia());
        }
        if (negocio.getMaeViaAdministracionId() != null) {
            entidad.setMaeViaAdministracionId(negocio.getMaeViaAdministracionId());
            entidad.setMaeViaAdministracionCodigo(negocio.getMaeViaAdministracionCodigo());
            entidad.setMaeViaAdministracionValor(negocio.getMaeViaAdministracionValor());
        }
        if (negocio.getMaeAmbitoId() != null) {
            entidad.setMaeAmbitoId(negocio.getMaeAmbitoId());
            entidad.setMaeAmbitoCodigo(negocio.getMaeAmbitoCodigo());
            entidad.setMaeAmbitoValor(negocio.getMaeAmbitoValor());
        }
        if (negocio.getNivel() != null) {
            entidad.setNivel(negocio.getNivel());
        }
        if (negocio.getMaDiagnosticoId() != null) {
            entidad.setMaDiagnosticoId(negocio.getMaDiagnosticoId());
            entidad.setMaDiagnosticoCodigo(negocio.getMaDiagnosticoCodigo());
            entidad.setMaDiagnosticoValor(negocio.getMaDiagnosticoValor());
        }
        entidad.setPrequirurgico(negocio.getPrequirurgico());
        entidad.setFechaFormula(negocio.getFechaFormula());
        entidad.setDosis(negocio.getDosis());
        entidad.setPosologia(negocio.getPosologia());
        entidad.setPosfechado(negocio.isPosfechado());
        entidad.setFechaPosfechado(negocio.getFechaPosfechado());
        entidad.setPosfechadoPrincipal(negocio.getPosfechadoPrincipal());
        entidad.setEfectosAdversos(negocio.getEfectosAdversos());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

    public static List<AuAnexo3Item> casteoListaEntidadNegocio(List<AuAnexo3Items> listaEntidad) {
        List<AuAnexo3Item> listaNegocio = new ArrayList();
        listaEntidad.forEach(entidad -> {
            boolean agregar = true;
            if (entidad.getPosfechado() != null && entidad.getPosfechado() == true) {
                if (entidad.getPosfechadoPrincipal() == null) {
                    agregar = false;
                } else {
                    if (entidad.getPosfechadoPrincipal() == false) {
                        agregar = false;
                    }
                }
            }
            if (agregar) {
                listaNegocio.add(castEntidadNegocio(entidad));
            }
        });
        for (AuAnexo3Item item : listaNegocio) {
            if (item.isPosfechado()) {
                int cantidad = 0;
                cantidad = listaEntidad.stream()
                        .filter(items -> (item.getMaTecnologiaId() == items.getMaTecnologiaId()))
                        .map(items -> items.getCantidadSolicitada())
                        .reduce(cantidad, Integer::sum);
                item.setCantidadSolicitada(cantidad);
            }
        }
        return listaNegocio;
    }

    private AuAnexo3Item castEntidadNegocioCotizacionGestion(AuAnexo3Items ent) {
        AuAnexo3Item negocio = new AuAnexo3Item();
        negocio.setId(ent.getId());
        negocio.setAuAnexo3Id(new AuAnexo3(ent.getAuAnexos3Id().getId()));
        negocio.setEstado(ent.getEstado());
        negocio.setTipoTecnologia(ent.getTipoTecnologia());
        negocio.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        negocio.setMaTecnologiaId(ent.getMaTecnologiaId());
        negocio.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        negocio.setNivel(ent.getNivel());
        if (ent.getMaDiagnosticoId() != null) {
            negocio.setMaDiagnosticoId(ent.getMaDiagnosticoId());
            negocio.setMaDiagnosticoCodigo(ent.getMaDiagnosticoCodigo());
            negocio.setMaDiagnosticoValor(ent.getMaDiagnosticoValor());
        }
        if (ent.getAuAnexos3Id().getAsegAfiliadosId() != null) {
            AsegAfiliado afiliado = new AsegAfiliado();
            afiliado.setId(ent.getAuAnexos3Id().getAsegAfiliadosId().getId());
            afiliado.setPrimerNombre(ent.getAuAnexos3Id().getAsegAfiliadosId().getPrimerNombre());
            if (ent.getAuAnexos3Id().getAsegAfiliadosId().getSegundoNombre() != null) {
                afiliado.setSegundoNombre(ent.getAuAnexos3Id().getAsegAfiliadosId().getSegundoNombre());
            }
            afiliado.setPrimerApellido(ent.getAuAnexos3Id().getAsegAfiliadosId().getPrimerApellido());
            if (ent.getAuAnexos3Id().getAsegAfiliadosId().getSegundoApellido() != null) {
                afiliado.setSegundoApellido(ent.getAuAnexos3Id().getAsegAfiliadosId().getSegundoApellido());
            }
            afiliado.setMaeTipoDocumento(ent.getAuAnexos3Id().getAsegAfiliadosId().getMaeTipoDocumentoId());
            afiliado.setMaeTipoDocumentoCodigo(ent.getAuAnexos3Id().getAsegAfiliadosId().getMaeTipoDocumentoCodigo());
            afiliado.setNumeroDocumento(ent.getAuAnexos3Id().getAsegAfiliadosId().getNumeroDocumento());
            if (ent.getAuAnexos3Id().getAsegAfiliadosId().getAfiliacionUbicacionesId() != null) {
                Ubicacion ubicacion = new Ubicacion();
                ubicacion.setNombre(ent.getAuAnexos3Id().getAsegAfiliadosId().getAfiliacionUbicacionesId().getNombre());
                afiliado.setAfiliacionUbicacion(ubicacion);
            }
            negocio.getAuAnexo3Id().setAsegAfiliadoId(afiliado);
        }
        if (ent.getAuAnexos3Id().getCntPrestadorSedesId() != null) {
            CntPrestadorSede sede = new CntPrestadorSede();
            sede.setNombreSede(ent.getAuAnexos3Id().getCntPrestadorSedesId().getNombre());
            CntPrestador prestador = new CntPrestador();
            prestador.setRazonSocial(ent.getAuAnexos3Id()
                    .getCntPrestadorSedesId()
                    .getCntPrestadoresId()
                    .getRazonSocial());
            sede.setCntPrestador(prestador);
            sede.setId(ent.getAuAnexos3Id().getCntPrestadorSedesId().getId());
            prestador.setCodigoMinSalud(ent.getAuAnexos3Id()
                    .getCntPrestadorSedesId()
                    .getCntPrestadoresId()
                    .getCodigoMinSalud());
            negocio.getAuAnexo3Id().setCntPrestadorSedeId(sede);
        }
        negocio.getAuAnexo3Id().setFechaSolicitud(ent.getAuAnexos3Id().getFechaSolicitud());
        negocio.setUsuarioCrea(ent.getUsuarioCrea());
        negocio.setTerminalCrea(ent.getTerminalCrea());
        negocio.setFechaHoraCrea(ent.getFechaHoraCrea());
        negocio.setPosfechado(ent.getPosfechado() == null ? false : ent.getPosfechado());
        negocio.setFechaPosfechado(ent.getFechaPosfechado());
        negocio.setPosfechadoPrincipal(ent.getPosfechadoPrincipal() == null ? false : ent.getPosfechadoPrincipal());
        return negocio;
    }

    private Date getDateFromLocalDate(Object paramConsulta) {
        LocalDate localFecha1 = (LocalDate) paramConsulta;
        return Date.from(localFecha1.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public List<AuAnexo3Item> listarItemsPosfechados(int idAnexo3, int idTecnologia) throws Exception {
        List<AuAnexo3Item> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo3Items p "
                    + "WHERE p.auAnexos3Id.id = " + idAnexo3 + " "
                    + "AND p.maTecnologiaId = " + idTecnologia + ""
                    + "AND p.posfechado = 1";

            List<AuAnexo3Items> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuAnexo3Items anexo3Item : list) {
                listaResultados.add(castEntidadNegocio(anexo3Item));
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
    public void actualizarPosfechado(AuAnexo3Item obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexo3Items a SET ";
//            strQuery += "a.posfechado = :posfechado, ";
//            strQuery += "a.fechaPosfechado = :fechaPosfechado, ";
            strQuery += "a.posfechadoPrincipal = :posfechadoPrincipal ";
            strQuery += "WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
//            query.setParameter("posfechado", obj.isPosfechado());
//            query.setParameter("fechaPosfechado", obj.getFechaPosfechado());
            query.setParameter("posfechadoPrincipal", obj.getPosfechadoPrincipal());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
            System.out.println(e.toString() + e.getMessage());
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
            System.out.println(e.toString() + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarPosfechados(AuAnexo3Item obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexo3Items a SET ";
            strQuery += "a.posfechado = :posfechado, ";
            strQuery += "a.fechaPosfechado = :fechaPosfechado, ";
            strQuery += "a.cantidadSolicitada = :cantidadSolicitada, ";
            strQuery += "a.posfechadoPrincipal = :posfechadoPrincipal ";
            strQuery += "WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("posfechado", obj.isPosfechado());
            query.setParameter("fechaPosfechado", obj.getFechaPosfechado());
            query.setParameter("cantidadSolicitada", obj.getCantidadSolicitada());
            query.setParameter("posfechadoPrincipal", obj.getPosfechadoPrincipal());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
            System.out.println(e.toString() + e.getMessage());
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
            System.out.println(e.toString() + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarFechaPosfechado(AuAnexo3Item obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexo3Items a SET ";
            strQuery += "a.fechaPosfechado = :fechaPosfechado ";
            strQuery += "WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("fechaPosfechado", obj.getFechaPosfechado());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
            System.out.println(e.toString() + e.getMessage());
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
            System.out.println(e.toString() + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public List<AuAnexo3Item> listaItemsByAnexo3Id(int idAnexo3) throws Exception {
        List<AuAnexo3Item> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo3Items p "
                    + "WHERE p.auAnexos3Id.id = " + idAnexo3 + " ";

            List<AuAnexo3Items> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuAnexo3Items anexo3Item : list) {
                boolean agregar = true;
                if (anexo3Item.getPosfechado() != null && anexo3Item.getPosfechado()) {
                    if (anexo3Item.getPosfechadoPrincipal() != null && anexo3Item.getPosfechadoPrincipal() == false) {
                        agregar = false;
                    }
                }
                if (agregar) {
                    listaResultados.add(castEntidadNegocio(anexo3Item));
                }
            }
            for (AuAnexo3Item item : listaResultados) {
                if (item.isPosfechado()) {
                    int cantidad = 0;
                    cantidad = list.stream()
                            .filter(items -> (item.getMaTecnologiaId() == items.getMaTecnologiaId()))
                            .map(items -> items.getCantidadSolicitada())
                            .reduce(cantidad, Integer::sum);
                    item.setCantidadSolicitada(cantidad);
                }

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
    public List<AuAnexo3Item> listaTodosItemsAnexo3Id(int idAnexo3) throws Exception {
        List<AuAnexo3Item> listaResultados = new ArrayList();
        try {
            String strQuery = "SELECT p FROM AuAnexo3Items p INNER JOIN p.auAnexos3Id a WHERE a.id=:idAnexo3";

            List<AuAnexo3Items> list = getEntityManager().createQuery(strQuery)
                    .setParameter("idAnexo3", idAnexo3)
                    .getResultList();
            for (AuAnexo3Items anexo3Item : list) {

                listaResultados.add(castEntidadNegocio(anexo3Item));

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
    public void actualizarGrupo(AuAnexo3Item obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexo3Items a SET ";
            if (obj.getAuGrupoId() != null) {
                strQuery += "a.auGruposId.id = :auGruposId, ";
            }
            strQuery += "a.gnUsuariosId.id = :gnUsuariosId ";
            strQuery += "WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            if (obj.getAuGrupoId() != null) {
                query.setParameter("auGruposId", obj.getAuGrupoId().getId());
            }
            if (obj.getGnUsuarioId() != null) {
                query.setParameter("gnUsuariosId", obj.getGnUsuarioId().getId());
            } else {
                query.setParameter("gnUsuariosId", null);
            }
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
    public List<AuAnexo3Item> listarPorIdGrupo(int idGrupo) throws Exception {
        List<AuAnexo3Item> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo3Items p "
                    + "WHERE p.auGruposId.id = " + idGrupo + " ";

            List<AuAnexo3Items> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuAnexo3Items anexo3Item : list) {
                boolean agregar = true;
                if (anexo3Item.getPosfechado() != null && anexo3Item.getPosfechado() == true) {
                    if (anexo3Item.getPosfechadoPrincipal() != null && anexo3Item.getPosfechadoPrincipal() == false) {
                        agregar = false;
                    }
                }
                if (agregar) {
                    listaResultados.add(castEntidadNegocio(anexo3Item));
                }
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
    public List<AuAnexo3Item> itemsByAfiliadoByFechaOrdenMedica(int afiliado, String tecnologias, String estados, Date fechaOrdenMedica, int idSolicitud) throws Exception {
        List<AuAnexo3Item> listaResultados = new ArrayList();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT p.id, ");
            strQuery.append(" p.estado, ");
            strQuery.append(" p.tipoTecnologia, ");
            strQuery.append(" p.maTecnologiaId, ");
            strQuery.append(" p.maTecnologiaCodigo, ");
            strQuery.append(" p.maTecnologiaValor, ");
            strQuery.append(" p.cantidadSolicitada, ");
            strQuery.append(" p.auAnexos3Id.id ");
            strQuery.append(" FROM AuAnexo3Items p ");
            strQuery.append(" INNER JOIN AuAnexos3 a ON p.auAnexos3Id.id = a.id AND a.asegAfiliadosId.id=:afiliado  ");
            strQuery.append(" AND a.fechaSolicitud =:fechaSolicitud AND a.id <> :idSolicitud  ");
            strQuery.append(" WHERE p.maTecnologiaId IN(").append(tecnologias).append(") ");
            strQuery.append(" AND p.estado IN(").append(estados).append(") ");

            List<Object[]> list = getEntityManager().createQuery(strQuery.toString())
                    .setParameter("afiliado", afiliado)
                    .setParameter("fechaSolicitud", fechaOrdenMedica)
                    .setParameter("idSolicitud", idSolicitud)
                    .getResultList();
            for (Object[] anexo3Item : list) {
                AuAnexo3Item item = new AuAnexo3Item();                
                item.setId((int) Optional.ofNullable(anexo3Item[0]).orElse(0));
                item.setEstado((int) Optional.ofNullable(anexo3Item[1]).orElse(0));
                item.setTipoTecnologia((int) Optional.ofNullable(anexo3Item[2]).orElse(0));
                item.setMaTecnologiaId((int) Optional.ofNullable(anexo3Item[3]).orElse(0));
                item.setMaTecnologiaCodigo((String) Optional.ofNullable(anexo3Item[4]).orElse(""));
                item.setMaTecnologiaValor((String) Optional.ofNullable(anexo3Item[5]).orElse(""));
                item.setCantidadSolicitada((int) Optional.ofNullable(anexo3Item[6]).orElse(0));
                item.setAuAnexo3Id(new AuAnexo3((int) Optional.ofNullable(anexo3Item[7]).orElse(0)));
                listaResultados.add(item);
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
    
    private static AuAnexo3Item castEntidadNegocioCorto(AuAnexo3Items ent) {
        AuAnexo3Item negocio = new AuAnexo3Item();
        negocio.setId(ent.getId());        
        negocio.setEstado(ent.getEstado());
        negocio.setTipoTecnologia(ent.getTipoTecnologia());
        negocio.setMaTecnologiaId(ent.getMaTecnologiaId());
        negocio.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        negocio.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        negocio.setCantidadSolicitada(ent.getCantidadSolicitada());        
        return negocio;
    }

}
