/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupo;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimiento;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo3Items;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos3;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos4;
import com.saviasaludeps.savia.ejb.entidades.AuGrupos;
import com.saviasaludeps.savia.ejb.entidades.AuSeguimientoAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AuSeguimientos;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuSeguimientoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author iavenegas
 */
@Stateless
@Remote(AuSeguimientoRemoto.class)
public class AuSeguimientoServicio extends GenericoServicio implements AuSeguimientoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuSeguimientos p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "AND p.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "maeEstadoSeguimientoId":
                            strQuery += "AND p.maeEstadoSeguimientoId = " + e.getValue() + " ";
                            break;
                        case "auAnexos3Id.id":
                            strQuery += "AND p.auAnexos3Id.id =" + e.getValue() + " ";
                            break;
                        case "tipoTecnologia":
                            strQuery += "AND p.tipoTecnologia =" + e.getValue() + " ";
                            break;
                        case "maTecnologiaCodigo":
                            strQuery += "AND p.maTecnologiaCodigo = '" + e.getValue() + "' ";
                            break;
                        case "maTecnologiaValor":
                            strQuery += "AND p.maTecnologiaValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "auAnexo3ItemsId.estado":
                            strQuery += "AND p.auAnexo3ItemsId.estado = " + e.getValue() + " ";
                            break;
                        case "auGruposId.id":
                            strQuery += "AND p.auGruposId.id = " + e.getValue() + " ";
                            break;
                        case "auSeguimientoAfiliadosId.asegAfiliado.maeTipoDocumento":
                            strQuery += "AND p.auSeguimientoAfiliadosId.asegAfiliadosId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "auSeguimientoAfiliadosId.asegAfiliado.numeroDocumento":
                            strQuery += "AND p.auSeguimientoAfiliadosId.asegAfiliadosId.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.numeroDocumento = '" + (String) e.getValue() + "' ) ";
                            break;
                        case "auSeguimientoAfiliadosId.asegAfiliado.nombres":
                            strQuery += "AND (p.auSeguimientoAfiliadosId.asegAfiliadosId.primerNombre = '" + e.getValue() + "' ";
                            strQuery += "OR p.auSeguimientoAfiliadosId.asegAfiliadosId.segundoNombre = '" + e.getValue() + "' ";
                            strQuery += "OR p.auSeguimientoAfiliadosId.asegAfiliadosId.primerApellido = '" + e.getValue() + "' ";
                            strQuery += "OR p.auSeguimientoAfiliadosId.asegAfiliadosId.segundoApellido = '" + e.getValue() + "' ) ";
                            break;

                        case "cntPrestadorSedeAsignadoId.nombreSede":
                            strQuery += "AND p.cntPrestadorSedeAsignadoId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeAmbitoAtencionId":
                            strQuery += "AND p.maeAmbitoAtencionId = " + e.getValue() + " ";
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
    public List<AuSeguimiento> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuSeguimiento> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuSeguimientos p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "AND p.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "maeEstadoSeguimientoId":
                            strQuery += "AND p.maeEstadoSeguimientoId = " + e.getValue() + " ";
                            break;
                        case "auAnexos3Id.id":
                            strQuery += "AND p.auAnexos3Id.id =" + e.getValue() + " ";
                            break;
                        case "tipoTecnologia":
                            strQuery += "AND p.tipoTecnologia =" + e.getValue() + " ";
                            break;
                        case "maTecnologiaCodigo":
                            strQuery += "AND p.maTecnologiaCodigo = '" + e.getValue() + "' ";
                            break;
                        case "maTecnologiaValor":
                            strQuery += "AND p.maTecnologiaValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "auAnexo3ItemsId.estado":
                            strQuery += "AND p.auAnexo3ItemsId.estado = " + e.getValue() + " ";
                            break;
                        case "auGruposId.id":
                            strQuery += "AND p.auGruposId.id = " + e.getValue() + " ";
                            break;
                        case "auSeguimientoAfiliadosId.asegAfiliado.maeTipoDocumento":
                            strQuery += "AND p.auSeguimientoAfiliadosId.asegAfiliadosId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "auSeguimientoAfiliadosId.asegAfiliado.numeroDocumento":
                            strQuery += "AND p.auSeguimientoAfiliadosId.asegAfiliadosId.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.numeroDocumento = '" + (String) e.getValue() + "' ) ";
                            break;
                        case "auSeguimientoAfiliadosId.asegAfiliado.nombres":
                            strQuery += "AND (p.auSeguimientoAfiliadosId.asegAfiliadosId.primerNombre = '" + e.getValue() + "' ";
                            strQuery += "OR p.auSeguimientoAfiliadosId.asegAfiliadosId.segundoNombre = '" + e.getValue() + "' ";
                            strQuery += "OR p.auSeguimientoAfiliadosId.asegAfiliadosId.primerApellido = '" + e.getValue() + "' ";
                            strQuery += "OR p.auSeguimientoAfiliadosId.asegAfiliadosId.segundoApellido = '" + e.getValue() + "' ) ";
                            break;

                        case "cntPrestadorSedeAsignadoId.nombreSede":
                            strQuery += "AND p.cntPrestadorSedeAsignadoId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;

                        case "maeAmbitoAtencionId":
                            strQuery += "AND p.maeAmbitoAtencionId = " + e.getValue() + " ";
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
            List<AuSeguimientos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuSeguimientos per : list) {
                listaResultados.add(castEntidadNegocioCorto(per));
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
    public AuSeguimiento consultar(int id) throws Exception {
        AuSeguimiento objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AuSeguimientos) getEntityManager().find(AuSeguimientos.class, id));
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
    public AuSeguimiento consultarCorto(int id) throws Exception {
        AuSeguimiento objRes = null;
        try {
            objRes = castEntidadNegocioCorto((AuSeguimientos) getEntityManager().find(AuSeguimientos.class, id));
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
    public int insertar(AuSeguimiento obj) throws Exception {
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
    public void actualizarEstado(AuSeguimiento obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuSeguimientos a SET ";
            strQuery += "a.maeEstadoSeguimientoId = :maeEstadoSeguimientoId ,";
            strQuery += "a.maeEstadoSeguimientoCodigo = :maeEstadoSeguimientoCodigo ,";
            strQuery += "a.maeEstadoSeguimientoValor = :maeEstadoSeguimientoValor ,";
            strQuery += "a.estadoTecnologia = :estadoTecnologia ,";
            strQuery += "a.gnEmpresasId = :empresa ,";
            strQuery += "a.cntPrestadorSedeAsignadoId = :prestador ,";

            if (obj.getAuAnexos4Id() != null) {
                strQuery += "a.auAnexos4Id = :auAnexos4Id ,";
            }
            
            //2023-12-07 jyperez nuevos campos
            strQuery += "a.fechaHoraAsignaPrestador = :fechaHoraAsignaPrestador ,";
            
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("maeEstadoSeguimientoId", obj.getMaeEstadoSeguimientoId());
            query.setParameter("maeEstadoSeguimientoCodigo", obj.getMaeEstadoSeguimientoCodigo());
            query.setParameter("maeEstadoSeguimientoValor", obj.getMaeEstadoSeguimientoValor());
            query.setParameter("estadoTecnologia", obj.getEstadoTecnologia());
            if (obj.getGnEmpresasId() != null) {
                query.setParameter("empresa", new GnEmpresas(obj.getGnEmpresasId().getId()));
            } else {
                query.setParameter("empresa", null);
            }
            if (obj.getCntPrestadorSedeAsignadoId() != null) {
                query.setParameter("prestador", new CntPrestadorSedes(obj.getCntPrestadorSedeAsignadoId().getId()));
            } else {
                query.setParameter("prestador", null);
            }
            if (obj.getAuAnexos4Id() != null) {
                query.setParameter("auAnexos4Id", new AuAnexos4(obj.getAuAnexos4Id().getId()));
            }
            //2023-12-07 jyperez nuevos campos
            query.setParameter("fechaHoraAsignaPrestador", obj.getFechaHoraAsignaPrestador());
            
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
    public void actualizarFechasSeguimiento(AuSeguimiento obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuSeguimientos a SET ";
            
            strQuery += "a.fechaHoraPrimeraGestion = :fechaHoraPrimeraGestion ,";
            strQuery += "a.fechaHoraUltimaGestion = :fechaHoraUltimaGestion ,";
            strQuery += "a.fechaHoraAtiende = :fechaHoraAtiende ,";
            strQuery += "a.fechaHoraAsignaPrestador = :fechaHoraAsignaPrestador ,";
            
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            //2023-12-07 jyperez nuevos campos
            query.setParameter("fechaHoraPrimeraGestion", obj.getFechaHoraPrimeraGestion());
            query.setParameter("fechaHoraUltimaGestion", obj.getFechaHoraUltimaGestion());
            query.setParameter("fechaHoraAtiende", obj.getFechaHoraAtiende());
            query.setParameter("fechaHoraAsignaPrestador", obj.getFechaHoraAsignaPrestador());
            
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
    public void actualizar(AuSeguimiento obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuSeguimientos a SET ";
            strQuery += "a.maeAcompananteTipoDocumentoId = :maeAcompananteTipoDocumentoId ,";
            strQuery += "a.maeAcompananteTipoDocumentoCodigo = :maeAcompananteTipoDocumentoCodigo ,";
            strQuery += "a.maeAcompananteTipoDocumentoValor = :maeAcompananteTipoDocumentoValor ,";
            strQuery += "a.acompananteDocumento = :acompananteDocumento ,";
            strQuery += "a.acompanantePrimerNombre = :acompanantePrimerNombre ,";
            strQuery += "a.acompananteSegundoNombre = :acompananteSegundoNombre ,";
            strQuery += "a.acompanantePrimerApellido = :acompanantePrimerApellido ,";
            strQuery += "a.acompananteSegundoApellido = :acompananteSegundoApellido ,";
            strQuery += "a.acompananteTelefono = :acompananteTelefono ,";
            strQuery += "a.acompananteDireccionResidencia = :acompananteDireccionResidencia ,";
            strQuery += "a.acompananteBarrioResidencia = :acompananteBarrioResidencia ,";
            if (obj.getGnAcompananteResidenciaUbicacionId() != null) {
                strQuery += "a.gnAcompananteResidenciaUbicacionId = :gnAcompananteResidenciaUbicacionId ,";
            }
            
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("maeAcompananteTipoDocumentoId", obj.getMaeAcompananteTipoDocumentoId());
            query.setParameter("maeAcompananteTipoDocumentoCodigo", obj.getMaeAcompananteTipoDocumentoCodigo());
            query.setParameter("maeAcompananteTipoDocumentoValor", obj.getMaeAcompananteTipoDocumentoValor());
            query.setParameter("acompananteDocumento", obj.getAcompananteDocumento());
            query.setParameter("acompanantePrimerNombre", obj.getAcompanantePrimerNombre());
            query.setParameter("acompananteSegundoNombre", obj.getAcompananteSegundoNombre());
            query.setParameter("acompanantePrimerApellido", obj.getAcompanantePrimerApellido());
            query.setParameter("acompananteSegundoApellido", obj.getAcompananteSegundoApellido());
            query.setParameter("acompananteTelefono", obj.getAcompananteTelefono());
            query.setParameter("acompananteDireccionResidencia", obj.getAcompananteDireccionResidencia());
            query.setParameter("acompananteBarrioResidencia", obj.getAcompananteBarrioResidencia());
            if (obj.getGnAcompananteResidenciaUbicacionId() != null) {
                query.setParameter("gnAcompananteResidenciaUbicacionId", new GnUbicaciones(obj.getGnAcompananteResidenciaUbicacionId().getId()));
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
    public AuSeguimiento seguimientoPorAnexo3Item(int idItem) throws Exception {
        AuSeguimiento existe = null;
        try {
            String strQuery = "SELECT p FROM AuSeguimientos p "
                    + "WHERE p.auAnexo3ItemsId.id=:idItem "
                    + "AND p.maeEstadoSeguimientoCodigo <>:estadoCancelado";
            AuSeguimientos seguimiento = (AuSeguimientos) getEntityManager().createQuery(strQuery)
                    .setParameter("idItem", idItem)
                    .setParameter("estadoCancelado", AuSeguimiento.ESTADO_CANCELADO)
                    .getSingleResult();
            existe = castEntidadNegocioCorto(seguimiento);
        } catch (NoResultException e) {
            existe = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return existe;
    }

    @Override
    public boolean validarExisteSeguimiento(int idItem, int idAnexo3) throws Exception {
        boolean existe = false;
        try {
            String strQuery = "SELECT COUNT(p.id) FROM AuSeguimientos p "
                    + "WHERE p.auAnexo3ItemsId.id=:idItem "
                    + "AND p.auAnexos3Id.id=:idAnexo3 "
                    + "AND p.maeEstadoSeguimientoCodigo <>:estadoCancelado";
            int seguimiento = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("idItem", idItem)
                    .setParameter("idAnexo3", idAnexo3)
                    .setParameter("estadoCancelado", AuSeguimiento.ESTADO_CANCELADO)
                    .getSingleResult();
            existe = seguimiento > 0;
        } catch (NoResultException e) {
            existe = false;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return existe;
    }

    private AuSeguimiento castEntidadNegocioCorto(AuSeguimientos ent) {
        AuSeguimiento negocio = new AuSeguimiento();
        negocio.setId(ent.getId());
        negocio.setEstadoTecnologia(ent.getEstadoTecnologia());
        negocio.setMaeEstadoSeguimientoId(ent.getMaeEstadoSeguimientoId());
        negocio.setMaeEstadoSeguimientoCodigo(ent.getMaeEstadoSeguimientoCodigo());
        negocio.setMaeEstadoSeguimientoValor(ent.getMaeEstadoSeguimientoValor());
        negocio.setTipoTecnologia(ent.getTipoTecnologia());
        negocio.setMaTecnologiaId(ent.getMaTecnologiaId());
        negocio.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        negocio.setMaTecnologiaValor(ent.getMaTecnologiaValor());

        negocio.setMaeAmbitoAtencionId(ent.getMaeAmbitoAtencionId());
        negocio.setMaeAmbitoAtencionCodigo(ent.getMaeAmbitoAtencionCodigo());
        negocio.setMaeAmbitoAtencionValor(ent.getMaeAmbitoAtencionValor());
        negocio.setAuAnexos3Id(new AuAnexo3(ent.getAuAnexos3Id().getId()));
        negocio.setAuAnexo3ItemsId(new AuAnexo3Item(
                ent.getAuAnexo3ItemsId().getId(),
                ent.getAuAnexo3ItemsId().getEstado(),
                ent.getAuAnexo3ItemsId().getTipoTecnologia(),
                ent.getAuAnexo3ItemsId().getMaTecnologiaId(),
                ent.getAuAnexo3ItemsId().getMaTecnologiaCodigo(),
                ent.getAuAnexo3ItemsId().getMaTecnologiaValor()
        ));
        if (ent.getAuAnexos4Id() != null) {
            negocio.setAuAnexos4Id(new AuAnexo4(ent.getAuAnexos4Id().getId()));
        }

        if (ent.getAuGruposId() != null) {
            negocio.setAuGruposId(new AuGrupo(ent.getAuGruposId().getId(), ent.getAuGruposId().getNombre()));
        }

        if (ent.getGnEmpresasId() != null) {
            negocio.setGnEmpresasId(new Empresa(ent.getGnEmpresasId().getId()));
        }

        if (ent.getCntPrestadorSedeAsignadoId() != null) {
            negocio.setCntPrestadorSedeAsignadoId(
                    new CntPrestadorSede(
                            ent.getCntPrestadorSedeAsignadoId().getId(),
                            ent.getCntPrestadorSedeAsignadoId().getNombre(),
                            new CntPrestador(ent.getCntPrestadorSedeAsignadoId().getCntPrestadoresId().getId())
                    )
            );
        }

        if (ent.getAuSeguimientoAfiliadosId() != null) {
            AuSeguimientoAfiliado seguimientoAfiliado = new AuSeguimientoAfiliado(
                    ent.getAuSeguimientoAfiliadosId().getId()
            //                    ent.getAuSeguimientoAfiliadosId().getMaeTipoDocumentoValor(),
            //                    ent.getAuSeguimientoAfiliadosId().getNumeroDocumento() + "",
            //                    ent.getAuSeguimientoAfiliadosId().getPrimerApellido(),
            //                    ent.getAuSeguimientoAfiliadosId().getSegundoApellido(),
            //                    ent.getAuSeguimientoAfiliadosId().getPrimerNombre(),
            //                    ent.getAuSeguimientoAfiliadosId().getSegundoNombre()
            );
            AsegAfiliado afiliado = new AsegAfiliado(
                    ent.getAuSeguimientoAfiliadosId().getAsegAfiliadosId().getId(),
                    ent.getAuSeguimientoAfiliadosId().getAsegAfiliadosId().getIdAfiliado(),
                    ent.getAuSeguimientoAfiliadosId().getAsegAfiliadosId().getPrimerNombre(),
                    ent.getAuSeguimientoAfiliadosId().getAsegAfiliadosId().getSegundoNombre(),
                    ent.getAuSeguimientoAfiliadosId().getAsegAfiliadosId().getPrimerApellido(),
                    ent.getAuSeguimientoAfiliadosId().getAsegAfiliadosId().getSegundoApellido(),
                    ent.getAuSeguimientoAfiliadosId().getAsegAfiliadosId().getGenero(),
                    0,
                    ent.getAuSeguimientoAfiliadosId().getAsegAfiliadosId().getNumeroDocumento(),
                    ent.getAuSeguimientoAfiliadosId().getAsegAfiliadosId().getMaeEstadoAfiliacionId()
            );
            afiliado.setMaeTipoDocumentoValor(ent.getAuSeguimientoAfiliadosId().getAsegAfiliadosId().getMaeTipoDocumentoValor());
            seguimientoAfiliado.setAsegAfiliado(afiliado);
            negocio.setAuSeguimientoAfiliadosId(seguimientoAfiliado);
        }

        //2023-12-07 jyperez nuevos campos REQ Filtros y semaforización seguimiento y prestadores.
        negocio.setFechaHoraPrimeraGestion(ent.getFechaHoraPrimeraGestion());
        negocio.setFechaHoraUltimaGestion(ent.getFechaHoraUltimaGestion());
        negocio.setFechaHoraAtiende(ent.getFechaHoraAtiende());
        negocio.setFechaHoraAsignaPrestador(ent.getFechaHoraAsignaPrestador());
        negocio.setUsuarioCrea(ent.getUsuarioCrea());
        negocio.setTerminalCrea(ent.getTerminalCrea());
        negocio.setFechaHoraCrea(ent.getFechaHoraCrea());
        negocio.setUsuarioModifica(ent.getUsuarioModifica());
        negocio.setTerminalModifica(ent.getTerminalModifica());
        negocio.setFechaHoraModifica(ent.getFechaHoraModifica());
        return negocio;
    }

    private AuSeguimiento castEntidadNegocioLargo(AuSeguimientos ent) {
        AuSeguimiento negocio = new AuSeguimiento();
        negocio.setId(ent.getId());
        negocio.setEstadoTecnologia(ent.getEstadoTecnologia());
        negocio.setMaeEstadoSeguimientoId(ent.getMaeEstadoSeguimientoId());
        negocio.setMaeEstadoSeguimientoCodigo(ent.getMaeEstadoSeguimientoCodigo());
        negocio.setMaeEstadoSeguimientoValor(ent.getMaeEstadoSeguimientoValor());
        negocio.setTipoTecnologia(ent.getTipoTecnologia());
        negocio.setMaTecnologiaId(ent.getMaTecnologiaId());
        negocio.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        negocio.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        negocio.setMaeTipoServicioId(ent.getMaeTipoServicioId());
        negocio.setMaeTipoServicioCodigo(ent.getMaeTipoServicioCodigo());
        negocio.setMaeTipoServicioValor(ent.getMaeTipoServicioValor());
        negocio.setMaeAcompananteTipoDocumentoId(ent.getMaeAcompananteTipoDocumentoId());
        negocio.setMaeAcompananteTipoDocumentoCodigo(ent.getMaeAcompananteTipoDocumentoCodigo());
        negocio.setMaeAcompananteTipoDocumentoValor(ent.getMaeAcompananteTipoDocumentoValor());
        negocio.setAcompananteDocumento(ent.getAcompananteDocumento());
        negocio.setAcompanantePrimerNombre(ent.getAcompanantePrimerNombre());
        negocio.setAcompananteSegundoNombre(ent.getAcompananteSegundoNombre());
        negocio.setAcompanantePrimerApellido(ent.getAcompanantePrimerApellido());
        negocio.setAcompananteSegundoApellido(ent.getAcompananteSegundoApellido());
        negocio.setAcompananteTelefono(ent.getAcompananteTelefono());
        negocio.setAcompananteDireccionResidencia(ent.getAcompananteDireccionResidencia());
        negocio.setAcompananteBarrioResidencia(ent.getAcompananteBarrioResidencia());
        negocio.setMaeAmbitoAtencionId(ent.getMaeAmbitoAtencionId());
        negocio.setMaeAmbitoAtencionCodigo(ent.getMaeAmbitoAtencionCodigo());
        negocio.setMaeAmbitoAtencionValor(ent.getMaeAmbitoAtencionValor());
        negocio.setAplicaPrestador(ent.getAplicaPrestador());
        negocio.setAuAnexos3Id(new AuAnexo3(ent.getAuAnexos3Id().getId()));
        negocio.setAuAnexo3ItemsId(new AuAnexo3Item(
                ent.getAuAnexo3ItemsId().getId(),
                ent.getAuAnexo3ItemsId().getEstado(),
                ent.getAuAnexo3ItemsId().getTipoTecnologia(),
                ent.getAuAnexo3ItemsId().getMaTecnologiaId(),
                ent.getAuAnexo3ItemsId().getMaTecnologiaCodigo(),
                ent.getAuAnexo3ItemsId().getMaTecnologiaValor()
        ));
        if (ent.getAuAnexos4Id() != null) {
            negocio.setAuAnexos4Id(new AuAnexo4(ent.getAuAnexos4Id().getId()));
        }
        if (ent.getAuGruposId() != null) {
            negocio.setAuGruposId(new AuGrupo(ent.getAuGruposId().getId(), ent.getAuGruposId().getNombre()));
        }
        if (ent.getCntPrestadorSedeAsignadoId() != null) {
            CntPrestadorSede sede = new CntPrestadorSede(ent.getCntPrestadorSedeAsignadoId().getId());
            sede.setNombreSede(ent.getCntPrestadorSedeAsignadoId().getNombre());
            CntPrestador prestador = new CntPrestador(ent.getCntPrestadorSedeAsignadoId().getCntPrestadoresId().getId());
            prestador.setRazonSocial(ent.getCntPrestadorSedeAsignadoId().getCntPrestadoresId().getRazonSocial());
            prestador.setMaeTipoDocumentoValor(ent.getCntPrestadorSedeAsignadoId().getCntPrestadoresId().getMaeTipoDocumentoValor());
            prestador.setMaeTipoDocumentoCodigo(ent.getCntPrestadorSedeAsignadoId().getCntPrestadoresId().getMaeTipoDocumentoCodigo());
            prestador.setMaeTipoDocumentoId(ent.getCntPrestadorSedeAsignadoId().getCntPrestadoresId().getMaeTipoDocumentoId());
            prestador.setNumeroDocumento(ent.getCntPrestadorSedeAsignadoId().getCntPrestadoresId().getNumeroDocumento());
            sede.setDireccion(ent.getCntPrestadorSedeAsignadoId().getDireccion());
            sede.setTelefonoCitas(ent.getCntPrestadorSedeAsignadoId().getTelefonoCitas());
            sede.setUbicacionId(ent.getCntPrestadorSedeAsignadoId().getUbicacionId());
            sede.setCntPrestador(prestador);
            sede.setCodigoHabilitacionSede(ent.getCntPrestadorSedeAsignadoId().getCodigoHabilitacion());
            negocio.setCntPrestadorSedeAsignadoId(sede);
        }
        if (ent.getGnAcompananteResidenciaUbicacionId() != null) {
            negocio.setGnAcompananteResidenciaUbicacionId(new Ubicacion(ent.getGnAcompananteResidenciaUbicacionId().getId()));
        }
        if (ent.getGnEmpresasId() != null) {
            negocio.setGnEmpresasId(new Empresa(ent.getGnEmpresasId().getId()));
        }
        if (ent.getAuSeguimientoAfiliadosId() != null) {
            negocio.setAuSeguimientoAfiliadosId(
                    AuSeguimientoAfiliadoServicio.castEntidadNegocioLargo(ent.getAuSeguimientoAfiliadosId())
            );
        }

        if (ent.getAuSolicitudAdjuntosList() != null) {
            negocio.setAuSolicitudAdjuntosList(AuSolicitudAdjuntoServicio.casteoListaEntidadNegocio(ent.getAuSolicitudAdjuntosList()));
        }

        if (ent.getAuSeguimientoServiciosList() != null) {
            negocio.setAuSeguimientoServicioList(AuSeguimientoServicioServicio.casteoListaEntidadNegocio(ent.getAuSeguimientoServiciosList()));
        }

        if (ent.getAuSeguimientoServiciosList() != null) {
            negocio.setAuSeguimientoServicioList(AuSeguimientoServicioServicio.casteoListaEntidadNegocio(ent.getAuSeguimientoServiciosList()));
        }

//        if (ent.getAuSeguimientoGestionesList() != null) {
//            negocio.setAuSeguimientoGestionList(AuSeguimientoGestionServicio.casteoListaEntidadNegocio(ent.getAuSeguimientoGestionesList()));
//        }
        if (ent.getAuSeguimientoPrestadorAsignadosList() != null) {
            negocio.setAuSeguimientoPrestadorAsignadoList(AuSeguimientoPrestadorAsignadoServicio.casteoListaEntidadNegocio(ent.getAuSeguimientoPrestadorAsignadosList()));
        }

        //2023-12-07 jyperez nuevos campos REQ Filtros y semaforización seguimiento y prestadores.
        negocio.setFechaHoraPrimeraGestion(ent.getFechaHoraPrimeraGestion());
        negocio.setFechaHoraUltimaGestion(ent.getFechaHoraUltimaGestion());
        negocio.setFechaHoraAtiende(ent.getFechaHoraAtiende());
        negocio.setFechaHoraAsignaPrestador(ent.getFechaHoraAsignaPrestador());
        negocio.setUsuarioCrea(ent.getUsuarioCrea());
        negocio.setTerminalCrea(ent.getTerminalCrea());
        negocio.setFechaHoraCrea(ent.getFechaHoraCrea());
        negocio.setUsuarioModifica(ent.getUsuarioModifica());
        negocio.setTerminalModifica(ent.getTerminalModifica());
        negocio.setFechaHoraModifica(ent.getFechaHoraModifica());
        return negocio;
    }

    private AuSeguimientos castNegocioEntidad(AuSeguimiento negocio) {
        AuSeguimientos entidad = new AuSeguimientos();
        entidad.setId(negocio.getId());
        entidad.setEstadoTecnologia(negocio.getEstadoTecnologia());
        entidad.setMaeEstadoSeguimientoId(negocio.getMaeEstadoSeguimientoId());
        entidad.setMaeEstadoSeguimientoCodigo(negocio.getMaeEstadoSeguimientoCodigo());
        entidad.setMaeEstadoSeguimientoValor(negocio.getMaeEstadoSeguimientoValor());
        entidad.setTipoTecnologia(negocio.getTipoTecnologia());
        entidad.setMaTecnologiaId(negocio.getMaTecnologiaId());
        entidad.setMaTecnologiaCodigo(negocio.getMaTecnologiaCodigo());
        entidad.setMaTecnologiaValor(negocio.getMaTecnologiaValor());
        entidad.setMaeTipoServicioId(negocio.getMaeTipoServicioId());
        entidad.setMaeTipoServicioCodigo(negocio.getMaeTipoServicioCodigo());
        entidad.setMaeTipoServicioValor(negocio.getMaeTipoServicioValor());
        entidad.setMaeAcompananteTipoDocumentoId(negocio.getMaeAcompananteTipoDocumentoId());
        entidad.setMaeAcompananteTipoDocumentoCodigo(negocio.getMaeAcompananteTipoDocumentoCodigo());
        entidad.setMaeAcompananteTipoDocumentoValor(negocio.getMaeAcompananteTipoDocumentoValor());
        entidad.setAcompananteDocumento(negocio.getAcompananteDocumento());
        entidad.setAcompanantePrimerNombre(negocio.getAcompanantePrimerNombre());
        entidad.setAcompananteSegundoNombre(negocio.getAcompananteSegundoNombre());
        entidad.setAcompanantePrimerApellido(negocio.getAcompanantePrimerApellido());
        entidad.setAcompananteTelefono(negocio.getAcompananteTelefono());
        entidad.setAcompananteDireccionResidencia(negocio.getAcompananteDireccionResidencia());
        entidad.setAcompananteBarrioResidencia(negocio.getAcompananteBarrioResidencia());
        entidad.setMaeAmbitoAtencionId(negocio.getMaeAmbitoAtencionId());
        entidad.setMaeAmbitoAtencionCodigo(negocio.getMaeAmbitoAtencionCodigo());
        entidad.setMaeAmbitoAtencionValor(negocio.getMaeAmbitoAtencionValor());
        entidad.setAplicaPrestador(negocio.getAplicaPrestador());
        entidad.setAuAnexos3Id(new AuAnexos3(negocio.getAuAnexos3Id().getId()));
        entidad.setAuAnexo3ItemsId(new AuAnexo3Items(negocio.getAuAnexo3ItemsId().getId()));
        if (negocio.getAuAnexos4Id() != null) {
            entidad.setAuAnexos4Id(new AuAnexos4(negocio.getAuAnexos4Id().getId()));
        }
        if (negocio.getAuGruposId() != null) {
            entidad.setAuGruposId(new AuGrupos(negocio.getAuGruposId().getId()));
        }
        if (negocio.getCntPrestadorSedeAsignadoId() != null) {
            entidad.setCntPrestadorSedeAsignadoId(new CntPrestadorSedes(negocio.getCntPrestadorSedeAsignadoId().getId()));
        }
        if (negocio.getGnAcompananteResidenciaUbicacionId() != null) {
            entidad.setGnAcompananteResidenciaUbicacionId(new GnUbicaciones(negocio.getGnAcompananteResidenciaUbicacionId().getId()));
        }
        if (negocio.getGnEmpresasId() != null) {
            entidad.setGnEmpresasId(new GnEmpresas(negocio.getGnEmpresasId().getId()));
        }
        if (negocio.getAuSeguimientoAfiliadosId() != null) {
            entidad.setAuSeguimientoAfiliadosId(new AuSeguimientoAfiliados(negocio.getAuSeguimientoAfiliadosId().getId()));
        }

        //2023-12-07 jyperez nuevos campos REQ Filtros y semaforización seguimiento y prestadores.
        entidad.setFechaHoraPrimeraGestion(negocio.getFechaHoraPrimeraGestion());
        entidad.setFechaHoraUltimaGestion(negocio.getFechaHoraUltimaGestion());
        entidad.setFechaHoraAtiende(negocio.getFechaHoraAtiende());
        entidad.setFechaHoraAsignaPrestador(negocio.getFechaHoraAsignaPrestador());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        return entidad;
    }

}
