/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimiento;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoAfiliado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AuSeguimientoAfiliados;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.servicios.aseguramiento.AfiliadoServicio;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuSeguimientoAfiliadoRemoto;
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
@Remote(AuSeguimientoAfiliadoRemoto.class)
public class AuSeguimientoAfiliadoServicio extends GenericoServicio implements AuSeguimientoAfiliadoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuSeguimientoAfiliados p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += "AND p.activo = " + e.getValue() + " ";
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
    public List<AuSeguimientoAfiliado> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuSeguimientoAfiliado> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuSeguimientoAfiliados p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += "AND p.activo = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.orden DESC";
            }
            List<AuSeguimientoAfiliados> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuSeguimientoAfiliados per : list) {
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
    public AuSeguimientoAfiliado consultar(int id) throws Exception {
        AuSeguimientoAfiliado objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AuSeguimientoAfiliados) getEntityManager().find(AuSeguimientoAfiliados.class, id));
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
    public int insertar(AuSeguimientoAfiliado obj) throws Exception {
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
    public void actualizar(AuSeguimientoAfiliado obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuSeguimientoAfiliados a SET ";
            strQuery += "a.direccionResidencia = :direccionResidencia, ";
            strQuery += "a.barrioResidencia = :barrioResidencia, ";
            strQuery += "a.energiaPrepagada = :energiaPrepagada, ";
            if (obj.getGnResidenciaUbicacionId() != null) {
                strQuery += "a.gnResidenciaUbicacionId = :gnResidenciaUbicacion, ";
            }
            strQuery += "a.usuarioModifica = :usuarioModifica, ";
            strQuery += "a.terminalModifica = :terminalModifica, ";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("direccionResidencia", obj.getDireccionResidencia());
            query.setParameter("barrioResidencia", obj.getBarrioResidencia());
            query.setParameter("energiaPrepagada", obj.getEnergiaPrepagada());
            if (obj.getGnResidenciaUbicacionId() != null) {
                query.setParameter("gnResidenciaUbicacion", new GnUbicaciones(obj.getGnResidenciaUbicacionId().getId()));
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
    public AuSeguimientoAfiliado seguimientoPorTeconologiaPorAfiliado(int idTecnologia, int tipoTecnologia, int idAfiliado) throws Exception {
        AuSeguimientoAfiliado resultado = null;
        try {
            String strQuery = "SELECT p FROM AuSeguimientoAfiliados p INNER JOIN p.auSeguimientosList s  "
                    + "WHERE p.maTecnologiaId=:idTecnologia "
                    + "AND p.tipoTecnologia=:tipoTecnologia "
                    + "AND p.asegAfiliadosId.id=:idAfiliado "
                    + "AND s.maeEstadoSeguimientoCodigo =:estado  "
                    + "ORDER BY p.id DESC";
            AuSeguimientoAfiliados seguimiento = (AuSeguimientoAfiliados) getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("idTecnologia", idTecnologia)
                    .setParameter("tipoTecnologia", tipoTecnologia)
                    .setParameter("idAfiliado", idAfiliado)
                    .setParameter("estado", AuSeguimiento.ESTADO_ENTREGADO)
                    .setMaxResults(1)
                    .getSingleResult();
            resultado = castEntidadNegocioEspecifico(seguimiento);
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return resultado;
    }

    public static AuSeguimientoAfiliado castEntidadNegocioCorto(AuSeguimientoAfiliados ent) {
        AuSeguimientoAfiliado negocio = new AuSeguimientoAfiliado();
        negocio.setId(ent.getId());
        negocio.setMaeEstadoAfiliadoId(ent.getMaeEstadoAfiliadoId());
        negocio.setMaeEstadoAfiliadoCodigo(ent.getMaeEstadoAfiliadoCodigo());
        negocio.setMaeEstadoAfiliadoValor(ent.getMaeEstadoAfiliadoValor());
        negocio.setNumeroDocumento(ent.getNumeroDocumento());
        negocio.setMaeTipoDocumentoId(ent.getMaeTipoDocumentoId());
        negocio.setMaeTipoDocumentoCodigo(ent.getMaeTipoDocumentoCodigo());
        negocio.setMaeTipoDocumentoValor(ent.getMaeTipoDocumentoValor());
        negocio.setMaeRegimenId(ent.getMaeRegimenId());
        negocio.setMaeRegimenCodigo(ent.getMaeRegimenCodigo());
        negocio.setMaeRegimenValor(ent.getMaeRegimenValor());

        negocio.setTipoTecnologia(ent.getTipoTecnologia());
        negocio.setMaTecnologiaId(ent.getMaTecnologiaId());
        negocio.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        negocio.setMaTecnologiaValor(ent.getMaTecnologiaValor());

        negocio.setBorrado(ent.getBorrado());

        negocio.setAsegAfiliado(
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

        negocio.setUsuarioCrea(ent.getUsuarioCrea());
        negocio.setTerminalCrea(ent.getTerminalCrea());
        negocio.setFechaHoraCrea(ent.getFechaHoraCrea());
        negocio.setUsuarioModifica(ent.getUsuarioModifica());
        negocio.setTerminalModifica(ent.getTerminalModifica());
        negocio.setFechaHoraModifica(ent.getFechaHoraModifica());
        return negocio;
    }

    public static AuSeguimientoAfiliado castEntidadNegocioLargo(AuSeguimientoAfiliados ent) {
        AuSeguimientoAfiliado negocio = new AuSeguimientoAfiliado();
        negocio.setId(ent.getId());
        negocio.setMaeEstadoAfiliadoId(ent.getMaeEstadoAfiliadoId());
        negocio.setMaeEstadoAfiliadoCodigo(ent.getMaeEstadoAfiliadoCodigo());
        negocio.setMaeEstadoAfiliadoValor(ent.getMaeEstadoAfiliadoValor());
        negocio.setMaeTipoDocumentoId(ent.getMaeTipoDocumentoId());
        negocio.setMaeTipoDocumentoCodigo(ent.getMaeTipoDocumentoCodigo());
        negocio.setMaeTipoDocumentoValor(ent.getMaeTipoDocumentoValor());
        negocio.setMaeRegimenId(ent.getMaeRegimenId());
        negocio.setMaeRegimenCodigo(ent.getMaeRegimenCodigo());
        negocio.setMaeRegimenValor(ent.getMaeRegimenValor());
        negocio.setNumeroDocumento(ent.getNumeroDocumento());
        negocio.setPrimerApellido(ent.getPrimerApellido());
        negocio.setSegundoApellido(ent.getSegundoApellido());
        negocio.setPrimerNombre(ent.getPrimerNombre());
        negocio.setSegundoNombre(ent.getSegundoNombre());
        negocio.setFechaNacimiento(ent.getFechaNacimiento());
        negocio.setMaeGeneroId(ent.getMaeGeneroId());
        negocio.setMaeGeneroCodigo(ent.getMaeGeneroCodigo());
        negocio.setMaeGeneroValor(ent.getMaeGeneroValor());
        negocio.setCorreoElectronico(ent.getCorreoElectronico());
        negocio.setContratoAfiliacion(ent.getContratoAfiliacion());
        negocio.setTipoTecnologia(ent.getTipoTecnologia());
        negocio.setMaTecnologiaId(ent.getMaTecnologiaId());
        negocio.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        negocio.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        negocio.setDireccionResidencia(ent.getDireccionResidencia());
        negocio.setBarrioResidencia(ent.getBarrioResidencia());
        negocio.setEnergiaPrepagada(ent.getEnergiaPrepagada());
        negocio.setBorrado(ent.getBorrado());

        if (ent.getAuSolicitudAdjuntosList() != null) {
            negocio.setAuSolicitudAdjuntosList(AuSolicitudAdjuntoServicio.casteoListaEntidadNegocio(ent.getAuSolicitudAdjuntosList()));
        }

        negocio.setAsegAfiliado(AfiliadoServicio.castEntidadNegocioLargo(ent.getAsegAfiliadosId()));

        if (ent.getGnResidenciaUbicacionId() != null) {
            negocio.setGnResidenciaUbicacionId(new Ubicacion(ent.getGnResidenciaUbicacionId().getId()));
        }

        if (ent.getAuSeguimientoAfiliadoContactosList() != null) {
            negocio.setAuSeguimientoAfiliadoContactosList(AuSeguimientoAfiliadoContactoServicio.casteoListaEntidadNegocio(ent.getAuSeguimientoAfiliadoContactosList()));
        }

        negocio.setUsuarioCrea(ent.getUsuarioCrea());
        negocio.setTerminalCrea(ent.getTerminalCrea());
        negocio.setFechaHoraCrea(ent.getFechaHoraCrea());
        negocio.setUsuarioModifica(ent.getUsuarioModifica());
        negocio.setTerminalModifica(ent.getTerminalModifica());
        negocio.setFechaHoraModifica(ent.getFechaHoraModifica());
        return negocio;
    }

    public static AuSeguimientoAfiliado castEntidadNegocioEspecifico(AuSeguimientoAfiliados ent) {
        AuSeguimientoAfiliado negocio = new AuSeguimientoAfiliado();
        negocio.setId(ent.getId());

        negocio.setDireccionResidencia(ent.getDireccionResidencia());
        negocio.setBarrioResidencia(ent.getBarrioResidencia());
        negocio.setEnergiaPrepagada(ent.getEnergiaPrepagada());
        negocio.setBorrado(ent.getBorrado());

        if (ent.getGnResidenciaUbicacionId() != null) {
            negocio.setGnResidenciaUbicacionId(new Ubicacion(ent.getGnResidenciaUbicacionId().getId()));
        }

        if (ent.getAuSeguimientoAfiliadoContactosList() != null) {
            negocio.setAuSeguimientoAfiliadoContactosList(AuSeguimientoAfiliadoContactoServicio.casteoListaEntidadNegocio(ent.getAuSeguimientoAfiliadoContactosList()));
        }

        if (ent.getAuSolicitudAdjuntosList() != null) {
            negocio.setAuSolicitudAdjuntosList(AuSolicitudAdjuntoServicio.casteoListaEntidadNegocio(ent.getAuSolicitudAdjuntosList()));
        }

        return negocio;
    }

    private AuSeguimientoAfiliados castNegocioEntidad(AuSeguimientoAfiliado negocio) {
        AuSeguimientoAfiliados entidad = new AuSeguimientoAfiliados();
        entidad.setId(negocio.getId());
        entidad.setAsegAfiliadosId(new AsegAfiliados(negocio.getAsegAfiliado().getId()));
        entidad.setMaeEstadoAfiliadoId(negocio.getMaeEstadoAfiliadoId());
        entidad.setMaeEstadoAfiliadoCodigo(negocio.getMaeEstadoAfiliadoCodigo());
        entidad.setMaeEstadoAfiliadoValor(negocio.getMaeEstadoAfiliadoValor());
        entidad.setMaeTipoDocumentoId(negocio.getMaeTipoDocumentoId());
        entidad.setMaeTipoDocumentoCodigo(negocio.getMaeTipoDocumentoCodigo());
        entidad.setMaeTipoDocumentoValor(negocio.getMaeTipoDocumentoValor());
        entidad.setMaeRegimenId(negocio.getMaeRegimenId());
        entidad.setMaeRegimenCodigo(negocio.getMaeRegimenCodigo());
        entidad.setMaeRegimenValor(negocio.getMaeRegimenValor());
        entidad.setNumeroDocumento(negocio.getNumeroDocumento());
        entidad.setPrimerApellido(negocio.getPrimerApellido());
        entidad.setSegundoApellido(negocio.getSegundoApellido());
        entidad.setPrimerNombre(negocio.getPrimerNombre());
        entidad.setSegundoNombre(negocio.getSegundoNombre());
        entidad.setFechaNacimiento(negocio.getFechaNacimiento());
        entidad.setMaeGeneroId(negocio.getMaeGeneroId());
        entidad.setMaeGeneroCodigo(negocio.getMaeGeneroCodigo());
        entidad.setMaeGeneroValor(negocio.getMaeGeneroValor());
        entidad.setCorreoElectronico(negocio.getCorreoElectronico());
        entidad.setContratoAfiliacion(negocio.getContratoAfiliacion());
        entidad.setTipoTecnologia(negocio.getTipoTecnologia());
        entidad.setMaTecnologiaId(negocio.getMaTecnologiaId());
        entidad.setMaTecnologiaCodigo(negocio.getMaTecnologiaCodigo());
        entidad.setMaTecnologiaValor(negocio.getMaTecnologiaValor());
        entidad.setDireccionResidencia(negocio.getDireccionResidencia());
        entidad.setBarrioResidencia(negocio.getBarrioResidencia());
        entidad.setEnergiaPrepagada(negocio.getEnergiaPrepagada());
        entidad.setBorrado(negocio.getBorrado());

        if (negocio.getGnResidenciaUbicacionId() != null) {
            entidad.setGnResidenciaUbicacionId(new GnUbicaciones(negocio.getGnResidenciaUbicacionId().getId()));
        }

        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        return entidad;
    }

}
