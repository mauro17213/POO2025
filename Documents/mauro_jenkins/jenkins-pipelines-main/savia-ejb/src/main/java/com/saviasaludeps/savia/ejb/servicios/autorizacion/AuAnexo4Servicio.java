package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.Anexo4Reporte;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Impresion;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Reporte;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo2Diagnosticos;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo3Diagnosticos;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4Entregas;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4Impresiones;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4Items;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos2;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos3;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos4;
import com.saviasaludeps.savia.ejb.entidades.CntContratoDetalles;
import com.saviasaludeps.savia.ejb.entidades.CntContratos;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.servicios.aseguramiento.AfiliadoServicio;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4Remoto;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
@Remote(AuAnexo4Remoto.class)
public class AuAnexo4Servicio extends GenericoServicio implements AuAnexo4Remoto {

    private static final SimpleDateFormat formatoCorto = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strTitulo = "SELECT COUNT(p) FROM AuAnexos4 p "
                    + "WHERE p.id > 0 ";
            String strQuery = "";
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "AND p.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "cntContratosId.contrato":
                            strQuery += "AND p.cntContratosId.contrato = '" + e.getValue() + "' ";
                            break;
                        case "maeRegimenId.id":
                            strQuery += "AND p.maeRegimenId = " + e.getValue() + " ";
                            break;
                        case "maeRegimenValor":
                            strQuery += "AND p.maeRegimenValor = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestadorSedeId.nombreSede":
                            strQuery += "AND p.cntPrestadorSedesId.nombre like '%" + e.getValue() + "%' ";
                            break;
                        case "cntPrestador.razonSocial":
                            strQuery += "AND p.cntPrestador.razonSocial = '" + e.getValue() + "' ";
                            break;
                        case "asegAfiliadoId.numeroDocumento":
                            strQuery += "AND p.asegAfiliadosId.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.numeroDocumento = '" + (String) e.getValue() + "' ) ";
                            break;
                        case "asegAfiliadoId.maeTipoDocumento":
                            strQuery += "AND p.asegAfiliadosId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "maEspecialidad.valor":
                            strQuery += "AND p.maEspecialidadValor = '" + e.getValue() + "' ";
                            break;
                        case "asegAfiliadoId.nombreCompleto":
                            strQuery += "AND (p.auAnexos3Id.asegAfiliadosId.primerNombre = '" + e.getValue() + "' ";
                            strQuery += "OR p.auAnexos3Id.asegAfiliadosId.segundoNombre = '" + e.getValue() + "' ";
                            strQuery += "OR p.auAnexos3Id.asegAfiliadosId.primerApellido = '" + e.getValue() + "' ";
                            strQuery += "OR p.auAnexos3Id.asegAfiliadosId.segundoApellido = '" + e.getValue() + "' ) ";
                            break;
                        case "auAnexo4ItemsList":
                            strTitulo = "SELECT COUNT(DISTINCT p) FROM AuAnexos4 p "
                                    + "LEFT JOIN AuAnexo4Items ai ON p.id = ai.auAnexos4Id.id "
                                    + "WHERE p.id > 0 ";
                            strQuery += "AND ai.maTecnologiaCodigo = '" + e.getValue() + "' ";
                            break;
                        case "medioAutorizacion":
                            strQuery += "AND p.medioAutorizacion = " + e.getValue() + " ";
                            break;
                        case "posfechada":
                            strQuery += "AND p.posfechada = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery = strTitulo + strQuery;
            if (paramConsulta.getParametroConsulta8() != null) {
                if (paramConsulta.getParametroConsulta9() != null) {
                    strQuery += "AND p.fechaAutorizacion BETWEEN '" + formatoCorto.format(paramConsulta.getParametroConsulta8()) + " 00:00:00' AND '" + formatoCorto.format(paramConsulta.getParametroConsulta9()) + " 23:59:59' ";
                } else {
                    strQuery += "AND p.fechaAutorizacion >= '" + formatoCorto.format(paramConsulta.getParametroConsulta8()) + " 00:00:00' ";
                }
            } else {
                if (paramConsulta.getParametroConsulta9() != null) {
                    strQuery += "AND p.fechaAutorizacion <= '" + formatoCorto.format(paramConsulta.getParametroConsulta9()) + " 23:59:59' ";
                }
            }
            //Añadir tipo opcional ANEXO3 O ANEXO2
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND p.auAnexos2Id IS NOT NULL ";
            } else if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += "AND p.auAnexos3Id IS NOT NULL ";
            }
            //Añadir solicitud
            if (paramConsulta.getParametroConsulta4() != null) {
                strQuery += "AND (p.auAnexos2Id = " + paramConsulta.getParametroConsulta4() + " ";
                strQuery += "OR p.auAnexos3Id = " + paramConsulta.getParametroConsulta4() + ") ";
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
    public List<AuAnexo4> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo4> listaResultados = new ArrayList();
        try {
            String strTitulo = "FROM AuAnexos4 p "
                    + "WHERE p.id > 0 ";
            String strQuery = "";
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "AND p.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "cntContratosId.contrato":
                            strQuery += "AND p.cntContratosId.contrato = '" + e.getValue() + "' ";
                            break;
                        case "maeRegimenId.id":
                            strQuery += "AND p.maeRegimenId = " + e.getValue() + " ";
                            break;
                        case "maeRegimenValor":
                            strQuery += "AND p.maeRegimenValor = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestadorSedeId.nombreSede":
                            strQuery += "AND p.cntPrestadorSedesId.nombre like '%" + e.getValue() + "%' ";
                            break;
                        case "cntPrestador.razonSocial":
                            strQuery += "AND p.cntPrestador.razonSocial = '" + e.getValue() + "' ";
                            break;
                        case "asegAfiliadoId.numeroDocumento":
                            strQuery += "AND p.asegAfiliadosId.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.numeroDocumento = '" + (String) e.getValue() + "' ) ";
                            break;
                        case "asegAfiliadoId.maeTipoDocumento":
                            strQuery += "AND p.asegAfiliadosId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "maEspecialidad.valor":
                            strQuery += "AND p.maEspecialidadValor = '" + e.getValue() + "' ";
                            break;
                        case "asegAfiliadoId.nombreCompleto":
                            strQuery += "AND (p.auAnexos3Id.asegAfiliadosId.primerNombre = '" + e.getValue() + "' ";
                            strQuery += "OR p.auAnexos3Id.asegAfiliadosId.segundoNombre = '" + e.getValue() + "' ";
                            strQuery += "OR p.auAnexos3Id.asegAfiliadosId.primerApellido = '" + e.getValue() + "' ";
                            strQuery += "OR p.auAnexos3Id.asegAfiliadosId.segundoApellido = '" + e.getValue() + "' ) ";
                            break;
                        case "auAnexo4ItemsList":
                            strTitulo = "SELECT DISTINCT p FROM AuAnexos4 p "
                                    + "LEFT JOIN AuAnexo4Items ai ON p.id = ai.auAnexos4Id.id "
                                    + "WHERE p.id > 0 ";
                            strQuery += "AND ai.maTecnologiaCodigo = '" + e.getValue() + "' ";
                            break;
                        case "medioAutorizacion":
                            strQuery += "AND p.medioAutorizacion = " + e.getValue() + " ";
                            break;
                        case "posfechada":
                            strQuery += "AND p.posfechada = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery = strTitulo + strQuery;
            if (paramConsulta.getParametroConsulta8() != null) {
                if (paramConsulta.getParametroConsulta9() != null) {
                    strQuery += "AND p.fechaAutorizacion BETWEEN '" + formatoCorto.format(paramConsulta.getParametroConsulta8()) + " 00:00:00' AND '" + formatoCorto.format(paramConsulta.getParametroConsulta9()) + " 23:59:59' ";
                } else {
                    strQuery += "AND p.fechaAutorizacion >= '" + formatoCorto.format(paramConsulta.getParametroConsulta8()) + " 00:00:00' ";
                }
            } else {
                if (paramConsulta.getParametroConsulta9() != null) {
                    strQuery += "AND p.fechaAutorizacion <= '" + formatoCorto.format(paramConsulta.getParametroConsulta9()) + " 23:59:59' ";
                }
            }
            //Añadir tipo opcional ANEXO3
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND p.auAnexos2Id IS NOT NULL ";
            } else if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += "AND p.auAnexos3Id IS NOT NULL ";
            }
            //Añadir solicitud
            if (paramConsulta.getParametroConsulta4() != null) {
                strQuery += "AND (p.auAnexos2Id = " + paramConsulta.getParametroConsulta4() + " ";
                strQuery += "OR p.auAnexos3Id = " + paramConsulta.getParametroConsulta4() + ") ";
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<AuAnexos4> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexos4 anexo4 : list) {
                listaResultados.add(castEntidadNegocioCorto(anexo4));
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
    public List<AuAnexo4> consultarAutorizacionFecha(
            String fechaInicio,
            String fechaFinal,
            String codHabPrestador) throws java.lang.Exception {
        List<AuAnexo4> lista = new ArrayList();
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("FROM AuAnexos4 p ")
                    .append("WHERE p.cntPrestadorSedesId.codigoHabilitacion ='")
                    .append(codHabPrestador).append("'")
                    //                    .append(" AND p.estado = '")
                    //                    .append(estado).append("'")
                    .append(" AND p.fechaAutorizacion BETWEEN '")
                    .append(fechaInicio).append("'")
                    .append(" AND '")
                    .append(fechaFinal).append("'")
                    .append(" ORDER BY p.id DESC");

            List<AuAnexos4> list = getEntityManager()
                    .createQuery(strQuery.toString())
                    .setMaxResults(500)
                    .getResultList();

            for (AuAnexos4 anexo4 : list) {
                lista.add(castEntidadNegocioLargo(anexo4));
            }

        } catch (NoResultException e) {
            lista = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return lista;
    }

    @Override
    public Integer consultarCantidadAutorizacionesFecha(
            String fechaInicio,
            String fechaFinal,
            String codHabPrestador) throws java.lang.Exception {
        Integer cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("select count(*) FROM AuAnexos4 p ")
                    .append("WHERE p.cntPrestadorSedesId.codigoHabilitacion ='")
                    .append(codHabPrestador).append("'")
                    //                    .append(" AND p.estado = '")
                    //                    .append(estado).append("'")
                    .append(" AND p.fechaAutorizacion BETWEEN '")
                    .append(fechaInicio).append("'")
                    .append(" AND '")
                    .append(fechaFinal).append("'")
                    .append(" ORDER BY p.id DESC");

            cant = (int) (long) getEntityManager()
                    .createQuery(strQuery.toString())
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
    public AuAnexo4 consultar(int id) throws Exception {
        AuAnexo4 objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AuAnexos4) getEntityManager().find(AuAnexos4.class, id));
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
    public AuAnexo4 consultarCorto(int id) throws Exception {
        AuAnexo4 objRes = null;
        try {
            objRes = castEntidadNegocioCorto((AuAnexos4) getEntityManager().find(AuAnexos4.class, id));
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
    public AuAnexo4 consultarByIdAnexo2(int idByAnexo2) throws Exception {
        AuAnexo4 anexo4 = null;
        try {
            String strQuery = "FROM AuAnexos4 p "
                    + "WHERE p.id > 0";
            strQuery += "AND p.auAnexos2Id.id = " + idByAnexo2 + " ";
            strQuery += "ORDER BY ";
            strQuery += "p.id DESC";
            List<AuAnexos4> list = getEntityManager().createQuery(strQuery).getResultList();
            for (AuAnexos4 anexos4 : list) {
                anexo4 = castEntidadNegocioLargo(anexos4);
            }
        } catch (NoResultException e) {
            anexo4 = new AuAnexo4();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return anexo4;
    }

    @Override
    public AuAnexo4 consultarByIdCotizacion(int idCotizacion) throws Exception {
        AuAnexo4 anexo4 = null;
        try {
            String strQuery = "SELECT p FROM AuAnexos4 p "
                    + "INNER JOIN AuAnexo4Items aai ON aai.auAnexos4Id = p.id "
                    + "INNER JOIN AuCotizaciones auc ON aai.auCotizacionesId = auc.id "
                    + "WHERE p.id > 0";
            strQuery += "AND auc.id = " + idCotizacion + " ";
            strQuery += "ORDER BY ";
            strQuery += "p.id DESC";
            List<AuAnexos4> list = getEntityManager().createQuery(strQuery).getResultList();
            for (AuAnexos4 anexos4 : list) {
                anexo4 = castEntidadNegocioLargo(anexos4);
            }
        } catch (NoResultException e) {
            anexo4 = new AuAnexo4();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return anexo4;
    }

    @Override
    public int insertar(AuAnexo4 obj) throws Exception {
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
    public void actualizarEstado(AuAnexo4 obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexos4 a SET ";
            strQuery += "a.estado = :estado, ";
            strQuery += "a.maeEstadoMotivoId = :maeEstadoMotivoId, ";
            strQuery += "a.maeEstadoMotivoCodigo = :maeEstadoMotivoCodigo, ";
            strQuery += "a.maeEstadoMotivoValor = :maeEstadoMotivoValor, ";
            strQuery += "a.estadoJustificacion = :estadoJustificacion, ";
            if(obj.getFuenteAnula() != null){
                strQuery += "a.fuenteAnula = :fuenteAnula ,";
            }
            if(obj.getAuAnexo4CargaAnuladasId() != null){
                strQuery += "a.auAnexo4CargaAnuladasId.id = :auAnexo4CargaAnuladasId ,"; 
            }
            strQuery += "a.usuarioModifica = :usuarioModifica, ";
            strQuery += "a.terminalModifica = :terminalModifica, ";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("maeEstadoMotivoId", obj.getMaeEstadoMotivoId());
            query.setParameter("maeEstadoMotivoCodigo", obj.getMaeEstadoMotivoCodigo());
            query.setParameter("maeEstadoMotivoValor", obj.getMaeEstadoMotivoValor());
            query.setParameter("estadoJustificacion", obj.getEstadoJustificacion());
            if (obj.getFuenteAnula() != null) {
                query.setParameter("fuenteAnula", obj.getFuenteAnula());
            }
            if(obj.getAuAnexo4CargaAnuladasId() != null){
                query.setParameter("auAnexo4CargaAnuladasId", obj.getAuAnexo4CargaAnuladasId().getId());
            }
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
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
    public void actualizarValorCopago(AuAnexo4 obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexos4 a SET ";
            strQuery += "a.valorCopago = :valorCopago, ";
            strQuery += "a.archivo =  :archivo, ";
            strQuery += "a.archivoNombre = :archivoNombre, ";
            strQuery += "a.ruta = :ruta, ";
            strQuery += "a.usuarioModifica = :usuarioModifica, ";
            strQuery += "a.terminalModifica = :terminalModifica, ";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("valorCopago", obj.getValorCopago());
            query.setParameter("archivo", obj.getArchivo());
            query.setParameter("archivoNombre", obj.getArchivoNombre());
            query.setParameter("ruta", obj.getRuta());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
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
    public int autorizarPreautorizacion(AuAnexo4 obj) throws Exception {
        int actualiza = 0;
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexos4 a SET ";
            strQuery += "a.estado = :estado, ";
            strQuery += "a.fechaAutorizacion = :fechaAutorizacion, ";
            strQuery += "a.impresionesAutorizadas = :impresionesAutorizadas, ";
            strQuery += "a.impresionesRealizadas = :impresionesRealizadas, ";
            strQuery += "a.archivo =  :archivo, ";
            strQuery += "a.archivoNombre = :archivoNombre, ";
            strQuery += "a.ruta = :ruta, ";
            strQuery += "a.usuarioModifica = :usuarioModifica, ";
            strQuery += "a.terminalModifica = :terminalModifica, ";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("fechaAutorizacion", obj.getFechaAutorizacion());
            query.setParameter("impresionesAutorizadas", obj.getImpresionesAutorizadas());
            query.setParameter("impresionesRealizadas", obj.getImpresionesRealizadas());
            query.setParameter("archivo", obj.getArchivo());
            query.setParameter("archivoNombre", obj.getArchivoNombre());
            query.setParameter("ruta", obj.getRuta());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            actualiza = query.executeUpdate();
        } catch (NoResultException e) {
            actualiza = 0;
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            actualiza = 0;
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
        return actualiza;
    }

    @Override
    public void actualizarArchivoReporte(AuAnexo4 obj) throws Exception {
        try {
            //AuAnexos4 per = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexos4 a SET ";
            strQuery += " a.archivo =  :archivo, ";
            strQuery += " a.archivoNombre = :archivoNombre, ";
            strQuery += " a.ruta = :ruta ";
            strQuery += " WHERE a.id = :id";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("archivo", obj.getArchivo());
            query.setParameter("archivoNombre", obj.getArchivoNombre());
            query.setParameter("ruta", obj.getRuta());
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
    public void actualizar(AuAnexo4 obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexos4 a SET ";
            strQuery += "a.archivoNombre = :archivo_nombre, ";
            strQuery += "a.archivo = :archivo, ";
            strQuery += "a.estado = :estado, ";
            strQuery += "a.maeEstadoMotivoId = :maeEstadoMotivoId, ";
            strQuery += "a.maeEstadoMotivoCodigo = :maeEstadoMotivoCodigo, ";
            strQuery += "a.maeEstadoMotivoValor = :maeEstadoMotivoValor, ";
            strQuery += "a.estadoJustificacion = :estadoJustificacion, ";
            strQuery += "a.impresionesRealizadas = :impresionesRealizadas ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("archivo_nombre", obj.getArchivoNombre());
            query.setParameter("archivo", obj.getArchivo());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("maeEstadoMotivoId", obj.getMaeEstadoMotivoId());
            query.setParameter("maeEstadoMotivoCodigo", obj.getMaeEstadoMotivoCodigo());
            query.setParameter("maeEstadoMotivoValor", obj.getMaeEstadoMotivoValor());
            query.setParameter("estadoJustificacion", obj.getEstadoJustificacion());
            query.setParameter("impresionesRealizadas", obj.getImpresionesRealizadas());
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
    public void anular(AuAnexo4 obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexos4 a SET ";
            strQuery += "a.id = :id ,";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            //query.setProperties(per);
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
    public AuAnexo4 eliminar(int id) throws Exception {
        AuAnexo4 obj = null;
        try {
            /*AuAnexos4 ent = getEntityManager().find(AuAnexos4.class, id);
            if (ent != null){
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }*/
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public AuAnexo4 consultarPorAutorizacion(String autorizacion) throws java.lang.Exception {
        AuAnexo4 obj = null;
        try {
            String strQuery = "FROM AuAnexos4 a "
                    + "WHERE numeroAutorizacion = :autorizacion";
            AuAnexos4 auAnexos4 = (AuAnexos4) getEntityManager().createQuery(strQuery)
                    .setParameter("autorizacion", autorizacion)
                    .getSingleResult();
            obj = castEntidadNegocioLargo(auAnexos4);
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    private AuAnexo4 castEntidadNegocioLargo(AuAnexos4 ent) {
        AuAnexo4 neg = new AuAnexo4();
        //Variables
        if (ent != null) {
            if (ent.getId() != null) {
                neg.setId(ent.getId());
            }
            if (ent.getGnEmpresasId() != null) {
                neg.setGnEmpresaId(new Empresa(ent.getGnEmpresasId().getId()));
            }
            if (ent.getAuAnexos2Id() != null) {
                neg.setAuAnexo2Id(new AuAnexo2(ent.getAuAnexos2Id().getId()));
            }
            if (ent.getAuAnexos3Id() != null) {
                neg.setAuAnexo3Id(new AuAnexo3(ent.getAuAnexos3Id().getId()));
                neg.getAuAnexo3Id().setFuenteOrigen(ent.getAuAnexos3Id().getFuenteOrigen());
                neg.getAuAnexo3Id().setJustificacionClinica(ent.getAuAnexos3Id().getJustificacionClinica());
            }
            neg.setNumeroAutorizacion(ent.getNumeroAutorizacion());
            neg.setFechaInicio(ent.getFechaInicio());
            neg.setFechaFin(ent.getFechaFin());
            neg.setDiasVigencia(ent.getDiasVigencia());
            neg.setPosfechada(ent.getPosfechada() == 1 ? true : false);
            neg.setFechaAutorizacion(ent.getFechaAutorizacion());
            neg.setFechaAutorizacionImpresion(ent.getFechaAutorizacionImpresion());
            neg.setEstado(ent.getEstado());
            neg.setMaeEstadoMotivoId(ent.getMaeEstadoMotivoId() != null ? ent.getMaeEstadoMotivoId() : 0);
            neg.setMaeEstadoMotivoCodigo(ent.getMaeEstadoMotivoCodigo() != null ? ent.getMaeEstadoMotivoCodigo() : "");
            neg.setMaeEstadoMotivoValor(ent.getMaeEstadoMotivoValor() != null ? ent.getMaeEstadoMotivoValor() : "");
            neg.setEstadoJustificacion(ent.getEstadoJustificacion());

            neg.setAsegAfiliadoId(AfiliadoServicio.castEntidadNegocioLargo(ent.getAsegAfiliadosId()));

            neg.setAfiliadoTipoDocumento(ent.getAfiliadoTipoDocumento());
            neg.setAfiliadoNumeroDocumento(ent.getAfiliadoNumeroDocumento());
            neg.setAfiliadoPrimerApellido(ent.getAfiliadoPrimerApellido());
            neg.setAfiliadoSegundoApellido(ent.getAfiliadoSegundoApellido());
            neg.setAfiliadoPrimerNombre(ent.getAfiliadoPrimerNombre());
            neg.setAfiliadoSegundoNombre(ent.getAfiliadoSegundoNombre());
            neg.setAfiliadoFechaNacimiento(ent.getAfiliadoFechaNacimiento());
            neg.setAfiliadoUbicacion(ent.getAfiliadoUbicacion());
            neg.setAfiliadoDepartamento(ent.getAfiliadoDepartamento());
            neg.setAfiliadoMunicipio(ent.getAfiliadoMunicipio());
            neg.setAfiliadoDireccion(ent.getAsegAfiliadosId().getDireccionResidencia());
            neg.setAfiliadoTelefono(ent.getAfiliadoTelefono());
            neg.setAfiliadoCelular(ent.getAfiliadoCelular());
            neg.setAfiliadoCorreo(ent.getAfiliadoCorreo());
            CntPrestadorSede sede = new CntPrestadorSede(ent.getCntPrestadorSedesId().getId());
            sede.setNombreSede(ent.getCntPrestadorSedesId().getNombre());
            CntPrestador prestador = new CntPrestador(ent.getCntPrestadorSedesId().getCntPrestadoresId().getId());
            prestador.setRazonSocial(ent.getCntPrestadorSedesId().getCntPrestadoresId().getRazonSocial());
            prestador.setMaeTipoDocumentoValor(ent.getCntPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoValor());
            prestador.setMaeTipoDocumentoCodigo(ent.getCntPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoCodigo());
            prestador.setMaeTipoDocumentoId(ent.getCntPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoId());
            prestador.setNumeroDocumento(ent.getCntPrestadorSedesId().getCntPrestadoresId().getNumeroDocumento());
            sede.setDireccion(ent.getCntPrestadorSedesId().getDireccion());
            sede.setTelefonoCitas(ent.getCntPrestadorSedesId().getTelefonoCitas());
            sede.setUbicacionId(ent.getCntPrestadorSedesId().getUbicacionId());
            sede.setCntPrestador(prestador);
            sede.setCodigoHabilitacionSede(ent.getCntPrestadorSedesId().getCodigoHabilitacion());
            neg.setCntPrestadorSedeId(sede);
            neg.setPrestadorTipoDocumento(ent.getPrestadorTipoDocumento());
            neg.setPrestadorNumeroDocumento(ent.getPrestadorNumeroDocumento());
            neg.setPrestadorNombre(ent.getPrestadorNombre());
            neg.setPrestadorCodigoHabilitacion(ent.getPrestadorCodigoHabilitacion());
            neg.setPrestadorTelefonoCita(ent.getPrestadorTelefonoCita());
            neg.setPrestadorDireccion(ent.getPrestadorDireccion());
            neg.setPrestadorDepartamento(ent.getPrestadorDepartamento());
            neg.setPrestadorMunicipio(ent.getPrestadorMunicipio());
            if (ent.getCntContratosId() != null) {
                neg.setCntContratoId(new CntContrato());
                neg.getCntContratoId().setContrato(ent.getCntContratosId().getContrato());
            }
            neg.setMaeRegimenId(ent.getMaeRegimenId());
            neg.setMaeRegimenCodigo(ent.getMaeRegimenCodigo());
            neg.setMaeRegimenValor(ent.getMaeRegimenValor());
            neg.setMaeAmbitoAtencionId(ent.getMaeAmbitoAtencionId());
            neg.setMaeAmbitoAtencionCodigo(ent.getMaeAmbitoAtencionCodigo());
            neg.setMaeAmbitoAtencionValor(ent.getMaeAmbitoAtencionValor());
            neg.setMaServicioHabilitadoId(ent.getMaServicioHabilitadoId());
            neg.setMaServicioHabilitadoCodigo(ent.getMaServicioHabilitadoCodigo());
            neg.setMaServicioHabilitadoValor(ent.getMaServicioHabilitadoValor());
            neg.setMaEspecialidadId(ent.getMaEspecialidadId());
            neg.setMaEspecialidadCodigo(ent.getMaEspecialidadCodigo());
            neg.setMaEspecialidadValor(ent.getMaEspecialidadValor());
            neg.setMaeGuiaManejoIntegralId(ent.getMaeGuiaManejoIntegralId());
            neg.setMaeGuiaManejoIntegralCodigo(ent.getMaeGuiaManejoIntegralCodigo());
            neg.setMaeGuiaManejoIntegralValor(ent.getMaeGuiaManejoIntegralValor());
            neg.setCantidadEntregas(ent.getCantidadEntregas());
            neg.setNumeroEntrega(ent.getNumeroEntrega());
            neg.setAnexo3Cama(ent.getAnexo3Cama());
            neg.setTipoServicioHabilitado(ent.getTipoServicioHabilitado());
            neg.setDiagnosticoPrincipal(ent.getDiagnosticoPrincipal());
            neg.setNombreAutoriza(ent.getNombreAutoriza());
            neg.setCargoActividadAutoriza(ent.getCargoActividadAutoriza());
            neg.setEpsTelefono(ent.getEpsTelefono());
            neg.setEntidadPago(ent.getEntidadPago());
            neg.setCodigoEntidadPago(ent.getCodigoEntidadPago());
            neg.setAplicaFactura(ent.getAplicaFactura());
            neg.setAplicaNobps(ent.getAplicaNobps());
            neg.setAplicaPac(ent.getAplicaPac());
            neg.setAplicaCuotaModeradora(ent.getAplicaCuotaModeradora());
            if (ent.getAplicaCopago() != null) {
                neg.setAplicaCopago(ent.getAplicaCopago());
            } else {
                neg.setAplicaCopago(false);
            }
            if (ent.getAplicaCuotaRecuperacion() != null) {
                neg.setAplicaCuotaRecuperacion(ent.getAplicaCuotaRecuperacion());
            } else {
                neg.setAplicaCuotaRecuperacion(false);
            }
            neg.setAplicaOtro(ent.getAplicaOtro());
            neg.setAplicaAltocosto(ent.getAplicaAltocosto());
            neg.setAplicaTutela(ent.getAplicaTutela());
            neg.setAplicaTopeMaximo(ent.getAplicaTopeMaximo());
            neg.setAplicaNoRed(ent.getAplicaNoRed());
            neg.setAplicaAutorizacionAutomatica(ent.getAplicaAutorizacionAutomatica());
            neg.setAplicaTiqueteBonoVale(ent.getAplicaTiqueteBonoVale());
            neg.setAplicaCapitaRecobro(ent.getAplicaCapitaRecobro());
            if (ent.getValorCuotaModeradora() != null) {
                neg.setValorCuotaModeradora(ent.getValorCuotaModeradora());
            } else {
                neg.setValorCuotaModeradora(new BigDecimal(0));
            }
            if (ent.getValorCopago() != null) {
                neg.setValorCopago(ent.getValorCopago());
            } else {
                neg.setValorCopago(new BigDecimal(0));
            }
            neg.setValorPac(ent.getValorPac());
            neg.setValorCuotaRecuperacion(ent.getValorCuotaRecuperacion());
            neg.setValorCuotaOtro(ent.getValorCuotaOtro());
            neg.setValorTopeMaximo(ent.getValorTopeMaximo());
            neg.setValorCotizacion(ent.getValorCotizacion());
            neg.setSemanasAfiliacion(ent.getSemanasAfiliacion());
            neg.setNumeroPrescripcion(ent.getNumeroPrescripcion());
            neg.setNumeroTutela(ent.getNumeroTutela());
            neg.setExcentoCopago(ent.getExcentoCopago());
            neg.setMotivoExentoCobro(ent.getMotivoExentoCobro());
            neg.setPorcentajeRecuperacion(ent.getPorcentajeRecuperacion());
            neg.setImpresionesAutorizadas(ent.getImpresionesAutorizadas());
            neg.setImpresionesRealizadas(ent.getImpresionesRealizadas());
            neg.setObservacion(ent.getObservacion());
            neg.setRuta(ent.getRuta());
            neg.setArchivo(ent.getArchivo());
            neg.setArchivoNombre(ent.getArchivoNombre());
            //Listas
            List<AuAnexo4Item> lista = new ArrayList();
            if (ent.getAuAnexo4ItemsList() != null) {
                for (AuAnexo4Items item : ent.getAuAnexo4ItemsList()) {
                    lista.add(AuAnexo4ItemServicio.castEntidadNegocio(item));
                }
            }
            if (ent.getAuAnexos2Id() != null && ent.getAuAnexos2Id().getAuAnexo2DiagnosticosList() != null) {
                for (AuAnexo2Diagnosticos diagno : ent.getAuAnexos2Id().getAuAnexo2DiagnosticosList()) {
                    if (diagno.getPrincipal()) {
                        neg.setDiagnosticoPrincipal(diagno.getMaDiagnosticosCodigo() + " - " + diagno.getMaDiagnosticosValor());
                    }
                }
            }
            if (ent.getAuAnexos3Id() != null && ent.getAuAnexos3Id().getAuAnexo3DiagnosticosList() != null) {
                for (AuAnexo3Diagnosticos diagno : ent.getAuAnexos3Id().getAuAnexo3DiagnosticosList()) {
                    if (diagno.getPrincipal()) {
                        neg.setDiagnosticoPrincipal(diagno.getMaDiagnosticosCodigo() + " - " + diagno.getMaDiagnosticosValor());
                    }
                }
            }
            if (ent.getAuAnexos2Id() != null && ent.getAuAnexos2Id().getAuSolicitudAdjuntosList() != null) {
                neg.setListaAdjuntos(AuSolicitudAdjuntoServicio.casteoListaEntidadNegocio(ent.getAuAnexos2Id().getAuSolicitudAdjuntosList()));
            }
            if (ent.getAuAnexos3Id() != null && ent.getAuAnexos3Id().getAuSolicitudAdjuntosList() != null) {
                neg.setListaAdjuntos(AuSolicitudAdjuntoServicio.casteoListaEntidadNegocio(ent.getAuAnexos3Id().getAuSolicitudAdjuntosList()));
            }
            if (ent.getAuAnexo4ImpresionesList() != null) {
                List<AuAnexo4Impresion> listaImpresiones = new ArrayList();
                for (AuAnexo4Impresiones impresiones : ent.getAuAnexo4ImpresionesList()) {
                    AuAnexo4Impresion impresion = new AuAnexo4Impresion(impresiones.getId());
                    impresion.setAuAnexo4Id(new AuAnexo4(impresiones.getAuAnexos4Id().getId()));
                    impresion.setTipoImpresion(impresiones.getTipoImpresion());
                    impresion.setOrigenImpresion(impresiones.getOrigenImpresion());
                    impresion.setFechaHoraCrea(impresiones.getFechaHoraCrea());
                    impresion.setUsuarioCrea(impresiones.getUsuarioCrea());
                    impresion.setTerminalCrea(impresiones.getTerminalCrea());
                    listaImpresiones.add(impresion);
                }
                neg.setAuAnexo4ImpresionesList(listaImpresiones);
            }
            neg.setAuAnexo4ItemsList(lista);
            if (ent.getAuAnexo4EntregasList() != null && !ent.getAuAnexo4EntregasList().isEmpty()) {
                boolean isEntrega = false;
                for (AuAnexo4Entregas entrega : ent.getAuAnexo4EntregasList()) {
                    if (!entrega.getAnulada()) {
                        if(entrega.getTipoEntrega() != 5){
                            isEntrega = true;
                            break;
                        }
                    }
                }
                neg.setTieneEntrega(isEntrega);
            } else {
                neg.setTieneEntrega(false);
            }
            /*if (ent.getAuAnexo4EntregasList() != null && !ent.getAuAnexo4EntregasList().isEmpty()) {
                boolean isEntrega = true;
                for (AuAnexo4Entregas entrega : ent.getAuAnexo4EntregasList()) {
                    if (entrega.getAnulada()) {
                        isEntrega = false;
                    }
                }
                neg.setTieneEntrega(isEntrega);
            } else {
                neg.setTieneEntrega(false);
            }*/
            neg.setMedioAutorizacion(ent.getMedioAutorizacion());
            neg.setContratoAnticipado(ent.getContratoAnticipado());
            neg.setContratoAnticipadoObservacion(ent.getContratoAnticipadoObservacion());
            //2023-09-28 jyperez nuevo campo
            if (ent.getPagoAnticipado() != null) {
                neg.setPagoAnticipado(ent.getPagoAnticipado());
            } else {
                neg.setPagoAnticipado(false);
            }
            neg.setTopeAplicado(ent.getTopeAplicado());
            //Valores auditoria
            neg.setUsuarioCrea(ent.getUsuarioCrea());
            neg.setTerminalCrea(ent.getTerminalCrea());
            neg.setFechaHoraCrea(ent.getFechaHoraCrea());
            neg.setUsuarioModifica(ent.getUsuarioModifica());
            neg.setTerminalModifica(ent.getTerminalModifica());
            neg.setFechaHoraModifica(ent.getFechaHoraModifica());

            neg.setVersion(ent.getVersion());
            neg.setConsecutivo(ent.getConsecutivo());
            neg.setDireccionAlternativa(ent.getAfiliadoDireccionAlternativa());
            neg.setMaeModalidadTecnologiaId(ent.getMaeModalidadTecnologiaId());
            neg.setMaeModalidadTecnologiaCodigo(ent.getMaeModalidadTecnologiaCodigo());
            neg.setMaeModalidadTecnologiaValor(ent.getMaeModalidadTecnologiaValor());
            neg.setMaeFinalidadTecnologiaId(ent.getMaeFinalidadTecnologiaId());
            neg.setMaeFinalidadTecnologiaCodigo(ent.getMaeFinalidadTecnologiaCodigo());
            neg.setMaeFinalidadTecnologiaValor(ent.getMaeFinalidadTecnologiaValor());
            neg.setMaeUbicacionId(ent.getMaeUbicacionId());
            neg.setMaeUbicacionCodigo(ent.getMaeUbicacionCodigo());
            neg.setMaeUbicacionValor(ent.getMaeUbicacionValor());
        } else {
            neg = null;
        }

        return neg;
    }

    private AuAnexos4 castNegocioEntidad(AuAnexo4 negocio) {
        AuAnexos4 entidad = new AuAnexos4();
        //Variables
        if (negocio.getGnEmpresaId() != null) {
            entidad.setGnEmpresasId(new GnEmpresas(negocio.getGnEmpresaId().getId()));
        }
        if (negocio.getAuAnexo2Id() != null) {
            entidad.setAuAnexos2Id(new AuAnexos2(negocio.getAuAnexo2Id().getId()));
        }
        if (negocio.getAuAnexo3Id() != null) {
            entidad.setAuAnexos3Id(new AuAnexos3(negocio.getAuAnexo3Id().getId()));
        }
        entidad.setNumeroAutorizacion(negocio.getNumeroAutorizacion());
        entidad.setFechaInicio(negocio.getFechaInicio());
        entidad.setFechaFin(negocio.getFechaFin());
        entidad.setDiasVigencia(negocio.getDiasVigencia());
        entidad.setPosfechada(negocio.isPosfechada() ? 1 : 0);
        entidad.setFechaAutorizacion(negocio.getFechaAutorizacion());
        entidad.setFechaAutorizacionImpresion(negocio.getFechaAutorizacion());
        entidad.setEstado(negocio.getEstado());
        entidad.setMaeEstadoMotivoId(negocio.getMaeEstadoMotivoId());
        entidad.setMaeEstadoMotivoCodigo(negocio.getMaeEstadoMotivoCodigo());
        entidad.setMaeEstadoMotivoValor(negocio.getMaeEstadoMotivoValor());
        entidad.setEstadoJustificacion(negocio.getEstadoJustificacion());
        if (negocio.getAsegAfiliadoId() != null) {
            entidad.setAsegAfiliadosId(new AsegAfiliados(negocio.getAsegAfiliadoId().getId()));
        }
        entidad.setAfiliadoTipoDocumento(negocio.getAfiliadoTipoDocumento());
        entidad.setAfiliadoNumeroDocumento(negocio.getAfiliadoNumeroDocumento());
        entidad.setAfiliadoPrimerApellido(negocio.getAfiliadoPrimerApellido());
        entidad.setAfiliadoSegundoApellido(negocio.getAfiliadoSegundoApellido());
        entidad.setAfiliadoPrimerNombre(negocio.getAfiliadoPrimerNombre());
        entidad.setAfiliadoSegundoNombre(negocio.getAfiliadoSegundoNombre());
        entidad.setAfiliadoFechaNacimiento(negocio.getAfiliadoFechaNacimiento());
        entidad.setAfiliadoUbicacion(negocio.getAfiliadoUbicacion());
        entidad.setAfiliadoDepartamento(negocio.getAfiliadoDepartamento());
        entidad.setAfiliadoMunicipio(negocio.getAfiliadoMunicipio());
        entidad.setAfiliadoDireccion(negocio.getAfiliadoDireccion());
        entidad.setAfiliadoTelefono(negocio.getAfiliadoTelefono());
        entidad.setAfiliadoCelular(negocio.getAfiliadoCelular());
        entidad.setAfiliadoCorreo(negocio.getAfiliadoCorreo());
        if (negocio.getCntPrestadorSedeId() != null) {
            entidad.setCntPrestadorSedesId(new CntPrestadorSedes(negocio.getCntPrestadorSedeId().getId()));
        }
        entidad.setPrestadorTipoDocumento(negocio.getPrestadorTipoDocumento());
        entidad.setPrestadorNumeroDocumento(negocio.getPrestadorNumeroDocumento());
        entidad.setPrestadorNombre(negocio.getPrestadorNombre());
        entidad.setPrestadorCodigoHabilitacion(negocio.getPrestadorCodigoHabilitacion());
        entidad.setPrestadorTelefonoCita(negocio.getPrestadorTelefonoCita());
        entidad.setPrestadorDireccion(negocio.getPrestadorDireccion());
        entidad.setPrestadorDepartamento(negocio.getPrestadorDepartamento());
        entidad.setPrestadorMunicipio(negocio.getPrestadorMunicipio());
        if (negocio.getCntContratoId() != null) {
            entidad.setCntContratosId(new CntContratos(negocio.getCntContratoId().getId()));
        }
        entidad.setMaeRegimenId(negocio.getMaeRegimenId());
        entidad.setMaeRegimenCodigo(negocio.getMaeRegimenCodigo());
        entidad.setMaeRegimenValor(negocio.getMaeRegimenValor());
        entidad.setMaeAmbitoAtencionId(negocio.getMaeAmbitoAtencionId());
        entidad.setMaeAmbitoAtencionCodigo(negocio.getMaeAmbitoAtencionCodigo());
        entidad.setMaeAmbitoAtencionValor(negocio.getMaeAmbitoAtencionValor());
        if (negocio.getMaServicioHabilitadoId() > 0) {
            entidad.setMaServicioHabilitadoId(negocio.getMaServicioHabilitadoId());
            entidad.setMaServicioHabilitadoCodigo(negocio.getMaServicioHabilitadoCodigo());
            entidad.setMaServicioHabilitadoValor(negocio.getMaServicioHabilitadoValor());
        }
        entidad.setMaEspecialidadId(negocio.getMaEspecialidadId());
        entidad.setMaEspecialidadCodigo(negocio.getMaEspecialidadCodigo());
        entidad.setMaEspecialidadValor(negocio.getMaEspecialidadValor());
        entidad.setMaeGuiaManejoIntegralId(negocio.getMaeGuiaManejoIntegralId());
        entidad.setMaeGuiaManejoIntegralCodigo(negocio.getMaeGuiaManejoIntegralCodigo());
        entidad.setMaeGuiaManejoIntegralValor(negocio.getMaeGuiaManejoIntegralValor());
        entidad.setCantidadEntregas(negocio.getCantidadEntregas());
        entidad.setNumeroEntrega(negocio.getNumeroEntrega());
        entidad.setAnexo3Cama(negocio.getAnexo3Cama());
        entidad.setTipoServicioHabilitado(negocio.getTipoServicioHabilitado());
        entidad.setDiagnosticoPrincipal(negocio.getDiagnosticoPrincipal());
        entidad.setNombreAutoriza(negocio.getNombreAutoriza());
        entidad.setCargoActividadAutoriza(negocio.getCargoActividadAutoriza());
        entidad.setEpsTelefono(negocio.getEpsTelefono());
        entidad.setEntidadPago(negocio.getEntidadPago());
        entidad.setCodigoEntidadPago(negocio.getCodigoEntidadPago());
        entidad.setAplicaFactura(negocio.isAplicaFactura());
        entidad.setAplicaNobps(negocio.isAplicaNobps());
        entidad.setAplicaPac(negocio.isAplicaPac());
        entidad.setAplicaCuotaModeradora(negocio.isAplicaCuotaModeradora());
        if (negocio.getAplicaCopago() != null) {
            entidad.setAplicaCopago(negocio.getAplicaCopago());
        } else {
            entidad.setAplicaCopago(false);
        }
        if (negocio.getAplicaCuotaRecuperacion() != null) {
            entidad.setAplicaCuotaRecuperacion(negocio.getAplicaCuotaRecuperacion());
        } else {
            entidad.setAplicaCuotaRecuperacion(false);
        }
        if (negocio.getAplicaOtro() != null) {
            entidad.setAplicaOtro(negocio.getAplicaOtro());
        } else {
            entidad.setAplicaOtro(false);
        }
        if (negocio.getAplicaAltocosto() != null) {
            entidad.setAplicaAltocosto(negocio.getAplicaAltocosto());
        } else {
            entidad.setAplicaAltocosto(false);
        }
        if (negocio.getAplicaTutela() != null) {
            entidad.setAplicaTutela(negocio.getAplicaTutela());
        } else {
            entidad.setAplicaTutela(false);
        }
        entidad.setAplicaTopeMaximo(negocio.isAplicaTopeMaximo());
        entidad.setAplicaNoRed(negocio.isAplicaNoRed());
        entidad.setAplicaAutorizacionAutomatica(negocio.isAplicaAutorizacionAutomatica());
        entidad.setAplicaTiqueteBonoVale(negocio.isAplicaTiqueteBonoVale());
        entidad.setAplicaCapitaRecobro(negocio.isAplicaCapitaRecobro());
        entidad.setValorCuotaModeradora(negocio.getValorCuotaModeradora());
        entidad.setValorCopago(negocio.getValorCopago());
        entidad.setValorPac(negocio.getValorPac());
        entidad.setValorCuotaRecuperacion(negocio.getValorCuotaRecuperacion());
        entidad.setValorCuotaOtro(negocio.getValorCuotaOtro());
        entidad.setValorTopeMaximo(negocio.getValorTopeMaximo());
        entidad.setValorCotizacion(negocio.getValorCotizacion());
        entidad.setSemanasAfiliacion(negocio.getSemanasAfiliacion());
        entidad.setNumeroPrescripcion(negocio.getNumeroPrescripcion());
        entidad.setNumeroTutela(negocio.getNumeroTutela());
        if (negocio.getExcentoCopago() != null) {
            entidad.setExcentoCopago(negocio.getExcentoCopago());
        } else {
            entidad.setExcentoCopago(false);
        }
        entidad.setMotivoExentoCobro(negocio.getMotivoExentoCobro());
        entidad.setPorcentajeRecuperacion(negocio.getPorcentajeRecuperacion());
        entidad.setImpresionesAutorizadas(AuAnexo4.IMPRESIONES_AUTORIZADAS);
        if (negocio.getImpresionesRealizadas() != null) {
            entidad.setImpresionesRealizadas(negocio.getImpresionesRealizadas());
        } else {
            entidad.setImpresionesRealizadas(0);
        }
        entidad.setObservacion(negocio.getObservacion());
        entidad.setRuta(negocio.getRuta());
        entidad.setArchivo(negocio.getArchivo());
        entidad.setMedioAutorizacion(negocio.getMedioAutorizacion());
        entidad.setContratoAnticipado(negocio.getContratoAnticipado());
        entidad.setContratoAnticipadoObservacion(negocio.getContratoAnticipadoObservacion());
        //2023-09-28 jyperez nuevo campo
        entidad.setPagoAnticipado(negocio.isPagoAnticipado());
        //2023-09-28 pvacca nuevo campo
        entidad.setTopeAplicado(negocio.isTopeAplicado());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());

        entidad.setVersion(negocio.isVersion());
        entidad.setConsecutivo(negocio.getConsecutivo());
        entidad.setAfiliadoDireccionAlternativa(negocio.getDireccionAlternativa());
        entidad.setMaeModalidadTecnologiaId(negocio.getMaeModalidadTecnologiaId());
        entidad.setMaeModalidadTecnologiaCodigo(negocio.getMaeModalidadTecnologiaCodigo());
        entidad.setMaeModalidadTecnologiaValor(negocio.getMaeModalidadTecnologiaValor());
        entidad.setMaeFinalidadTecnologiaId(negocio.getMaeFinalidadTecnologiaId());
        entidad.setMaeFinalidadTecnologiaCodigo(negocio.getMaeFinalidadTecnologiaCodigo());
        entidad.setMaeFinalidadTecnologiaValor(negocio.getMaeFinalidadTecnologiaValor());
        entidad.setMaeUbicacionId(negocio.getMaeUbicacionId());
        entidad.setMaeUbicacionCodigo(negocio.getMaeUbicacionCodigo());
        entidad.setMaeUbicacionValor(negocio.getMaeUbicacionValor());
        return entidad;
    }

    private AuAnexo4 castEntidadNegocioCorto(AuAnexos4 ent) {
        AuAnexo4 neg = new AuAnexo4();
        //Variables
        neg.setId(ent.getId());

        if (ent.getGnEmpresasId() != null) {
            neg.setGnEmpresaId(new Empresa(ent.getGnEmpresasId().getId()));
        }
        if (ent.getAuAnexos2Id() != null) {
            neg.setAuAnexo2Id(new AuAnexo2(ent.getAuAnexos2Id().getId()));
        }
        if (ent.getAuAnexos3Id() != null) {
            neg.setAuAnexo3Id(new AuAnexo3(ent.getAuAnexos3Id().getId()));
            neg.getAuAnexo3Id().setFuenteOrigen(ent.getAuAnexos3Id().getFuenteOrigen());
            neg.getAuAnexo3Id().setJustificacionClinica(ent.getAuAnexos3Id().getJustificacionClinica());
        }
        neg.setNumeroAutorizacion(ent.getNumeroAutorizacion());
        neg.setFechaInicio(ent.getFechaInicio());
        neg.setFechaFin(ent.getFechaFin());
        neg.setDiasVigencia(ent.getDiasVigencia());
        neg.setPosfechada(ent.getPosfechada() == 1 ? true : false);
        neg.setFechaAutorizacion(ent.getFechaAutorizacion());
        neg.setFechaAutorizacionImpresion(ent.getFechaAutorizacionImpresion());
        neg.setEstado(ent.getEstado());
        neg.setAsegAfiliadoId(
                new AsegAfiliado(
                        ent.getAsegAfiliadosId().getId(),
                        ent.getAsegAfiliadosId().getIdAfiliado(),
                        ent.getAsegAfiliadosId().getPrimerNombre(),
                        ent.getAsegAfiliadosId().getSegundoNombre(),
                        ent.getAsegAfiliadosId().getPrimerApellido(),
                        ent.getAsegAfiliadosId().getSegundoApellido(),
                        ent.getAsegAfiliadosId().getGenero(),
                        0,
                        ent.getAsegAfiliadosId().getNumeroDocumento(),
                        ent.getAsegAfiliadosId().getMaeEstadoAfiliacionId()
                )
        );
        neg.getAsegAfiliadoId().setMaeTipoDocumento(ent.getAsegAfiliadosId().getMaeTipoDocumentoId());
        neg.getAsegAfiliadoId().setMaeTipoDocumentoValor(ent.getAsegAfiliadosId().getMaeTipoDocumentoValor());
        neg.getAsegAfiliadoId().setMaeTipoDocumentoCodigo(ent.getAsegAfiliadosId().getMaeTipoDocumentoCodigo());
        neg.setAfiliadoTelefono(ent.getAfiliadoTelefono());
        neg.setAfiliadoCelular(ent.getAfiliadoCelular());

        CntPrestadorSede sede = new CntPrestadorSede(ent.getCntPrestadorSedesId().getId());
        sede.setNombreSede(ent.getCntPrestadorSedesId().getNombre());
        CntPrestador prestador = new CntPrestador(ent.getCntPrestadorSedesId().getCntPrestadoresId().getId());
        prestador.setRazonSocial(ent.getCntPrestadorSedesId().getCntPrestadoresId().getRazonSocial());
        prestador.setMaeTipoDocumentoValor(ent.getCntPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoValor());
        prestador.setMaeTipoDocumentoCodigo(ent.getCntPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoCodigo());
        prestador.setMaeTipoDocumentoId(ent.getCntPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoId());
        prestador.setNumeroDocumento(ent.getCntPrestadorSedesId().getCntPrestadoresId().getNumeroDocumento());
        sede.setUbicacionId(ent.getCntPrestadorSedesId().getUbicacionId());
        sede.setCntPrestador(prestador);
        sede.setCodigoHabilitacionSede(ent.getCntPrestadorSedesId().getCodigoHabilitacion());
        neg.setCntPrestadorSedeId(sede);

        neg.setCantidadEntregas(ent.getCantidadEntregas());
        neg.setNumeroEntrega(ent.getNumeroEntrega());

        neg.setNombreAutoriza(ent.getNombreAutoriza());
        neg.setImpresionesAutorizadas(ent.getImpresionesAutorizadas());
        neg.setImpresionesRealizadas(ent.getImpresionesRealizadas());
        neg.setObservacion(ent.getObservacion());
        neg.setRuta(ent.getRuta());
        neg.setArchivo(ent.getArchivo());
        neg.setArchivoNombre(ent.getArchivoNombre());
        neg.setMaeAmbitoAtencionCodigo(ent.getMaeAmbitoAtencionCodigo());
        neg.setMedioAutorizacion(ent.getMedioAutorizacion());
        neg.setAplicaCopago(ent.getAplicaCopago());
        //Listas
        List<AuAnexo4Item> lista = new ArrayList();
        if (ent.getAuAnexo4ItemsList() != null) {
            for (AuAnexo4Items item : ent.getAuAnexo4ItemsList()) {
                lista.add(castEntidaNegocioAuAnexo4Item(item));
            }
        }

        if (ent.getAuAnexo4ImpresionesList() != null) {
            List<AuAnexo4Impresion> listaImpresiones = new ArrayList();
            for (AuAnexo4Impresiones impresiones : ent.getAuAnexo4ImpresionesList()) {
                AuAnexo4Impresion impresion = new AuAnexo4Impresion(impresiones.getId());
                impresion.setAuAnexo4Id(new AuAnexo4(impresiones.getAuAnexos4Id().getId()));
                impresion.setTipoImpresion(impresiones.getTipoImpresion());
                impresion.setOrigenImpresion(impresiones.getOrigenImpresion());
                impresion.setFechaHoraCrea(impresiones.getFechaHoraCrea());
                impresion.setUsuarioCrea(impresiones.getUsuarioCrea());
                impresion.setTerminalCrea(impresiones.getTerminalCrea());
                listaImpresiones.add(impresion);
            }
            neg.setAuAnexo4ImpresionesList(listaImpresiones);
        }
        neg.setAuAnexo4ItemsList(lista);
        if (ent.getAuAnexo4EntregasList() != null && !ent.getAuAnexo4EntregasList().isEmpty()) {
            boolean isEntrega = false;
            for (AuAnexo4Entregas entrega : ent.getAuAnexo4EntregasList()) {
                if (!entrega.getAnulada()) {
                    if(entrega.getTipoEntrega() != 5){
                        isEntrega = true;
                        break;
                    }
                }
            }
            neg.setTieneEntrega(isEntrega);
        } else {
            neg.setTieneEntrega(false);
        }
        //2023-09-28 jyperez nuevo campo
        if (ent.getPagoAnticipado() != null) {
            neg.setPagoAnticipado(ent.getPagoAnticipado());
        } else {
            neg.setPagoAnticipado(false);
        }
        if (ent.getCntContratosId() != null) {
            CntContrato contrato = new CntContrato();
            contrato.setId(ent.getCntContratosId().getId());
            contrato.setFechaFin(ent.getCntContratosId().getFechaFin());
            neg.setCntContratoId(contrato);
        }
        //Valores auditoria
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        neg.setUsuarioModifica(ent.getUsuarioModifica());
        neg.setTerminalModifica(ent.getTerminalModifica());
        neg.setFechaHoraModifica(ent.getFechaHoraModifica());

        neg.setVersion(ent.getVersion());
        neg.setConsecutivo(ent.getConsecutivo());
        neg.setDireccionAlternativa(ent.getAfiliadoDireccionAlternativa());
        neg.setMaeModalidadTecnologiaId(ent.getMaeModalidadTecnologiaId());
        neg.setMaeModalidadTecnologiaCodigo(ent.getMaeModalidadTecnologiaCodigo());
        neg.setMaeModalidadTecnologiaValor(ent.getMaeModalidadTecnologiaValor());
        neg.setMaeFinalidadTecnologiaId(ent.getMaeFinalidadTecnologiaId());
        neg.setMaeFinalidadTecnologiaCodigo(ent.getMaeFinalidadTecnologiaCodigo());
        neg.setMaeFinalidadTecnologiaValor(ent.getMaeFinalidadTecnologiaValor());
        neg.setMaeUbicacionId(ent.getMaeUbicacionId());
        neg.setMaeUbicacionCodigo(ent.getMaeUbicacionCodigo());
        neg.setMaeUbicacionValor(ent.getMaeUbicacionValor());
        return neg;
    }

    @Override
    public AuAnexo4 consultarPorAnexo3(int idAnexo3) throws Exception {
        AuAnexo4 anexo4 = null;
        try {
            String strQuery = "FROM AuAnexos4 p "
                    + "WHERE  p.auAnexos3Id.id = " + idAnexo3 + " "
                    + "ORDER BY p.id ASC";
            List<AuAnexos4> list = getEntityManager().createQuery(strQuery)
                    .getResultList();

            for (AuAnexos4 anexos4 : list) {
                anexo4 = castEntidadNegocioLargo(anexos4);
            }
        } catch (NoResultException e) {
            anexo4 = new AuAnexo4();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return anexo4;
    }

    public AuAnexo4Item castEntidaNegocioAuAnexo4Item(AuAnexo4Items entidad) {
        AuAnexo4Item negocio = new AuAnexo4Item();
        negocio.setId(entidad.getId());
        if (entidad.getAuAnexo3ItemsId() != null) {
            negocio.setAuAnexo3ItemId(new AuAnexo3Item(entidad.getAuAnexo3ItemsId().getId()));
            negocio.getAuAnexo3ItemId().setEstado(entidad.getAuAnexo3ItemsId().getEstado());
        }
        if (entidad.getCntContratoDetallesId() != null) {
            CntContratoDetalle contratoDetalle = new CntContratoDetalle();
            contratoDetalle.setId(entidad.getCntContratoDetallesId().getId());
            negocio.setCntContratoDetalle(contratoDetalle);
            if(entidad.getCntContratoDetallesId().getCntContratosId() != null){
                CntContrato contrato = new CntContrato();
                contrato.setId(entidad.getCntContratoDetallesId().getCntContratosId().getId());
                contrato.setFechaFin(entidad.getCntContratoDetallesId().getCntContratosId().getFechaFin());
                negocio.getCntContratoDetalle().setCntContrato(contrato);
            }
        }
        negocio.setTipoTecnologia(entidad.getTipoTecnologia());
        negocio.setMaTecnologiaId(entidad.getMaTecnologiaId());
        negocio.setMaTecnologiaCodigo(entidad.getMaTecnologiaCodigo());
        negocio.setMaTecnologiaValor(entidad.getMaTecnologiaValor());
        negocio.setCantidadAutorizada(entidad.getCantidadAutorizada());
        return negocio;
    }

    @Override
    public void actualizarImpresion(AuAnexo4 obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexos4 a SET ";
            strQuery += "a.ruta = :ruta, ";
            strQuery += "a.archivo = :archivo, ";
            strQuery += "a.archivoNombre = :archivoNombre, ";
            strQuery += "a.impresionesAutorizadas = :impresionesAutorizadas, ";
            strQuery += "a.impresionesRealizadas = :impresionesRealizadas";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("ruta", obj.getRuta());
            query.setParameter("archivo", obj.getArchivo());
            query.setParameter("archivoNombre", obj.getArchivoNombre());
            query.setParameter("impresionesAutorizadas", obj.getImpresionesAutorizadas());
            query.setParameter("impresionesRealizadas", obj.getImpresionesRealizadas());
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
    public int consultarCantidadListaPorAfiliado(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(DISTINCT p) FROM AuAnexos4 p "
                    + "LEFT JOIN AuAnexo4Items ai ON p.id = ai.auAnexos4Id.id "
                    + "WHERE p.id > 0 ";//p.fechaAutorizacion <= '" + formatoCorto.format(new Date())+" 23:59:59' ";
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "AND p.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            }
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND p.asegAfiliadosId.id = " + paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "cntContratosId.contrato":
                            strQuery += "AND p.cntContratosId.contrato = '" + e.getValue() + "' ";
                            break;
                        case "maeRegimenId.id":
                            strQuery += "AND p.maeRegimenId = " + e.getValue() + " ";
                            break;
                        case "maeRegimenValor":
                            strQuery += "AND p.maeRegimenValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntPrestadorSedeId.nombreSede":
                            strQuery += "AND p.cntPrestadorSedesId.nombre = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.razonSocial":
                            strQuery += "AND p.cntPrestador.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "asegAfiliadoId.numeroDocumento":
                            strQuery += "AND p.asegAfiliadosId.numeroDocumento = '" + e.getValue() + "' ";
                            break;
                        case "asegAfiliadoId.maeTipoDocumento":
                            strQuery += "AND p.asegAfiliadosId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "maEspecialidad.valor":
                            strQuery += "AND p.maEspecialidadValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "asegAfiliadoId.nombreCompleto":
                            strQuery += "AND (p.auAnexos3Id.asegAfiliadosId.primerNombre LIKE '%" + e.getValue() + "%' ";
                            strQuery += "OR p.auAnexos3Id.asegAfiliadosId.segundoNombre LIKE '%" + e.getValue() + "%' ";
                            strQuery += "OR p.auAnexos3Id.asegAfiliadosId.primerApellido LIKE '%" + e.getValue() + "%' ";
                            strQuery += "OR p.auAnexos3Id.asegAfiliadosId.segundoApellido LIKE '%" + e.getValue() + "%' ";
                            strQuery += "OR CONCAT(p.auAnexos3Id.asegAfiliadosId.primerApellido, ' ', p.auAnexos3Id.asegAfiliadosId.segundoApellido, ";
                            strQuery += "p.auAnexos3Id.asegAfiliadosId.primerNombre, ' ', p.auAnexos3Id.asegAfiliadosId.segundoNombre) ";
                            strQuery += "LIKE '%" + e.getValue() + "%') ";
                            break;
                        case "auAnexo4ItemsList":
                            strQuery += "AND ai.maTecnologiaCodigo = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            if (paramConsulta.getParametroConsulta8() != null) {
                if (paramConsulta.getParametroConsulta9() != null) {
                    strQuery += "AND p.fechaAutorizacion BETWEEN '" + formatoCorto.format(paramConsulta.getParametroConsulta8()) + " 00:00:00' AND '" + formatoCorto.format(paramConsulta.getParametroConsulta9()) + " 23:59:59' ";
                } else {
                    strQuery += "AND p.fechaAutorizacion >= '" + formatoCorto.format(paramConsulta.getParametroConsulta8()) + " 00:00:00' ";
                }
            } else {
                if (paramConsulta.getParametroConsulta9() != null) {
                    strQuery += "AND p.fechaAutorizacion <= '" + formatoCorto.format(paramConsulta.getParametroConsulta9()) + " 23:59:59' ";
                }
            }
            //Añadir tipo opcional ANEXO3 O ANEXO2
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND p.auAnexos2Id IS NOT NULL ";
            } else if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += "AND p.auAnexos3Id IS NOT NULL ";
            }
            //Añadir solicitud
            if (paramConsulta.getParametroConsulta4() != null) {
                strQuery += "AND (p.auAnexos2Id = " + paramConsulta.getParametroConsulta4() + " ";
                strQuery += "OR p.auAnexos3Id = " + paramConsulta.getParametroConsulta4() + ") ";
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
    public List<AuAnexo4> consultarListaPorAfiliado(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo4> listaResultados = new ArrayList();
        try {
            String strQuery = "SELECT DISTINCT p FROM AuAnexos4 p "
                    + "LEFT JOIN AuAnexo4Items ai ON p.id = ai.auAnexos4Id.id "
                    + "WHERE p.id > 0 ";//p.fechaAutorizacion <= '" + formatoCorto.format(new Date())+" 23:59:59' ";
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "AND p.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            }
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND p.asegAfiliadosId.id = " + paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "cntContratosId.contrato":
                            strQuery += "AND p.cntContratosId.contrato = '" + e.getValue() + "' ";
                            break;
                        case "maeRegimenId.id":
                            strQuery += "AND p.maeRegimenId = " + e.getValue() + " ";
                            break;
                        case "maeRegimenValor":
                            strQuery += "AND p.maeRegimenValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntPrestadorSedeId.nombreSede":
                            strQuery += "AND p.cntPrestadorSedesId.nombre = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.razonSocial":
                            strQuery += "AND p.cntPrestador.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maEspecialidad.valor":
                            strQuery += "AND p.maEspecialidadValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "asegAfiliadoId.numeroDocumento":
                            strQuery += "AND p.asegAfiliadosId.numeroDocumento = '" + e.getValue() + "' ";
                            break;
                        case "asegAfiliadoId.maeTipoDocumento":
                            strQuery += "AND p.asegAfiliadosId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "asegAfiliadoId.nombreCompleto":
                            strQuery += "AND (p.auAnexos3Id.asegAfiliadosId.primerNombre LIKE '%" + e.getValue() + "%' ";
                            strQuery += "OR p.auAnexos3Id.asegAfiliadosId.segundoNombre LIKE '%" + e.getValue() + "%' ";
                            strQuery += "OR p.auAnexos3Id.asegAfiliadosId.primerApellido LIKE '%" + e.getValue() + "%' ";
                            strQuery += "OR p.auAnexos3Id.asegAfiliadosId.segundoApellido LIKE '%" + e.getValue() + "%' ";
                            strQuery += "OR CONCAT(p.auAnexos3Id.asegAfiliadosId.primerApellido, ' ', p.auAnexos3Id.asegAfiliadosId.segundoApellido, ";
                            strQuery += "p.auAnexos3Id.asegAfiliadosId.primerNombre, ' ', p.auAnexos3Id.asegAfiliadosId.segundoNombre) ";
                            strQuery += "LIKE '%" + e.getValue() + "%') ";
                            break;
                        case "auAnexo4ItemsList":
                            strQuery += "AND ai.maTecnologiaCodigo = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            if (paramConsulta.getParametroConsulta8() != null) {
                if (paramConsulta.getParametroConsulta9() != null) {
                    strQuery += "AND p.fechaAutorizacion BETWEEN '" + formatoCorto.format(paramConsulta.getParametroConsulta8()) + " 00:00:00' AND '" + formatoCorto.format(paramConsulta.getParametroConsulta9()) + " 23:59:59' ";
                } else {
                    strQuery += "AND p.fechaAutorizacion >= '" + formatoCorto.format(paramConsulta.getParametroConsulta8()) + " 00:00:00' ";
                }
            } else {
                if (paramConsulta.getParametroConsulta9() != null) {
                    strQuery += "AND p.fechaAutorizacion <= '" + formatoCorto.format(paramConsulta.getParametroConsulta9()) + " 23:59:59' ";
                }
            }
            //Añadir tipo opcional ANEXO3
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND p.auAnexos2Id IS NOT NULL ";
            } else if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += "AND p.auAnexos3Id IS NOT NULL ";
            }
            //Añadir solicitud
            if (paramConsulta.getParametroConsulta4() != null) {
                strQuery += "AND (p.auAnexos2Id = " + paramConsulta.getParametroConsulta4() + " ";
                strQuery += "OR p.auAnexos3Id = " + paramConsulta.getParametroConsulta4() + ") ";
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<AuAnexos4> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexos4 anexo4 : list) {
                listaResultados.add(castEntidadNegocioLargo(anexo4));
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
    public List<Anexo4Reporte> consultarListaParaReporte(AuAnexo4Reporte reporte) throws Exception {
        List<Anexo4Reporte> listaResultados = new ArrayList();
        try {
            String strQuery = "SELECT "
                    + "aa.id, "
                    + "aa.au_anexos2_id, "
                    + "aa.au_anexos3_id, "
                    + "aa2.mae_tipo_documento_id, "
                    + "aa2.numero_documento, "
                    + "aa2.primer_nombre, "
                    + "aa2.segundo_nombre, "
                    + "aa2.primer_apellido, "
                    + "aa2.segundo_apellido, "
                    + "aai.ma_tecnologia_codigo, "
                    + "aai.ma_tecnologia_valor, "
                    + "aai.cantidad_autorizada, "
                    + "cp.razon_social, "
                    + "aa.estado, "
                    + "aa.fecha_autorizacion, "
                    + "aa.usuario_crea, "
                    + "CONCAT_WS('-',aa2.telefono_fijo,aa2.telefono_movil), "
                    + "aa2.mae_regimen_valor, "
                    + "aa.mae_ambito_atencion_valor, "
                    + "cps.nombre "
                    + "FROM au_anexos4 aa "
                    + "INNER JOIN au_anexo4_items aai ON aa.id = aai.au_anexos4_id "
                    + "INNER JOIN cnt_prestador_sedes cps ON aa.cnt_prestador_sedes_id = cps.id "
                    + "INNER JOIN cnt_prestadores cp ON cp.id = cps.cnt_prestadores_id  "
                    + "INNER JOIN aseg_afiliados aa2 ON aa2.id = aa.aseg_afiliados_id "
                    + "WHERE aa.fecha_autorizacion BETWEEN '" + formatoCorto.format(reporte.getFechaInicio()) + " 00:00:00' AND '" + formatoCorto.format(reporte.getFechaFin()) + " 23:59:59' ";

            /*if (reporte.getEmpresaId() > 1) {
                strQuery += "AND aa.gn_empresas_id = " + reporte.getEmpresaId() + " ";
            }*/
            if (reporte.getPrestador() != null) {
                strQuery += "AND cp.id = '" + reporte.getPrestador() + "' ";
            }

            if (reporte.getSede() != null) {
                strQuery += "AND aa.cnt_prestador_sedes_id = '" + reporte.getSede().getId() + "' ";
            }

            strQuery += "ORDER BY aa.id ASC";
            javax.persistence.Query nativeQuery = em.createNativeQuery(strQuery);
            List<Object[]> results = nativeQuery.getResultList();
            listaResultados = results
                    .stream()
                    .map(result -> new Anexo4Reporte(((int) result[0]),
                    (Integer) result[1],
                    (Integer) result[2],
                    (int) result[3],
                    (String) result[4],
                    (String) result[5],
                    (String) result[6],
                    (String) result[7],
                    (String) result[8],
                    (String) result[9],
                    (String) result[10],
                    (int) result[11],
                    (String) result[12],
                    (Integer) result[13],
                    (Date) result[14],
                    (String) result[15],
                    (String) result[16],
                    (String) result[17],
                    (String) result[18],
                    (String) result[19]
            ))
                    .collect(Collectors.toList());

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
    public void actualizarConsecutivo(AuAnexo4 obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexos4 a SET ";
            strQuery += " a.consecutivo = :consecutivo ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("consecutivo", obj.getConsecutivo());
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

    private String getConsecutivoGen(int id) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        return formato.format(new Date()) + id;
    }
}
