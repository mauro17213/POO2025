package com.saviasaludeps.savia.ejb.servicios.especial;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadoDiagnostico;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizaciones;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.PeAfiliadosProgramas;
import com.saviasaludeps.savia.ejb.entidades.PeProgramas;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadosProgramaRemoto;
import com.saviasaludeps.savia.ejb.entidades.PeAfiliadoDiagnosticos;
import com.saviasaludeps.savia.ejb.entidades.PeAfiliadosSugeridos;
import static com.saviasaludeps.savia.ejb.servicios.aseguramiento.AfiliadoServicio.castPrestadorSedesEntidadNegocio;
import static com.saviasaludeps.savia.ejb.servicios.aseguramiento.AfiliadoServicio.castUbicacionEntidadNegocio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(PeAfiliadosProgramaRemoto.class)
@Local(PeAfiliadosProgramaLocal.class)
public class PeAfiliadosProgramaServicio extends GenericoServicio implements PeAfiliadosProgramaLocal, PeAfiliadosProgramaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            StringBuilder strTitulo = new StringBuilder("SELECT COUNT(p) FROM PeAfiliadosProgramas p  ");
            StringBuilder strQuery = new StringBuilder("  WHERE p.id > 0  ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append(" AND p.id = '").append((String) e.getValue()).append("'");
                            break;
                        case "activo":
                            strQuery.append(" AND p.activo = '").append((String) e.getValue()).append("'");
                            break;
                        case "asegAfiliado.maeTipoDocumento":
                            strQuery.append(" AND p.asegAfiliadosId.maeTipoDocumentoId = '").append((String) e.getValue()).append("'");
                            break;
                        case "asegAfiliado.numeroDocumento":
                            strQuery.append(" AND p.asegAfiliadosId.numeroDocumento = '").append((String) e.getValue()).append("'  ");
                            break;
                        case "asegAfiliado.nombres":
                            strQuery.append(" AND CONCAT(p.asegAfiliadosId.primerNombre, ' ', p.asegAfiliadosId.segundoNombre) LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "asegAfiliado.apellidos":
                            strQuery.append(" AND CONCAT(p.asegAfiliadosId.primerApellido, ' ', p.asegAfiliadosId.segundoApellido)  LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "asegAfiliado.maeGeneroId":
                            strQuery.append(" AND p.asegAfiliadosId.maeGeneroId = ").append(e.getValue());
                            break;
                        case "asegAfiliado.afiliacionUbicacion.nombre":
                            strQuery.append(" AND p.asegAfiliadosId.afiliacionUbicacionesId.nombre = '").append(e.getValue()).append("' ");
                            break;
                        case "asegAfiliado.maeEstadoAfiliacion":
                            strQuery.append(" AND p.asegAfiliadosId.maeEstadoAfiliacionId = '").append(e.getValue()).append("' ");
                            break;
                        case "asegAfiliado.maeRegimenId":
                            strQuery.append(" AND p.asegAfiliadosId.maeRegimenId = '").append(e.getValue()).append("' ");
                            break;
                        case "pePrograma.descripcionPrograma":
                            strQuery.append(" AND p.peProgramasId.id = '").append(e.getValue()).append("' ");
                            break;
                        case "maeDxPrincipalId":
                            strQuery.append(" AND p.maeDxPrincipalId = '").append(e.getValue()).append("' ");
                            break;
                        case "maeDx2Id":
                            strQuery.append(" AND p.maeDx2Id = '").append(e.getValue()).append("' ");
                            break;
                        case "maeDx3Id":
                            strQuery.append(" AND p.maeDx3Id = '").append(e.getValue()).append("' ");
                            break;
                        case "maeRegionCorporalId":
                            strQuery.append(" AND p.maeRegionCorporalId = '").append(e.getValue()).append("' ");
                            break;
                        case "gnUsuario.nombre":
                            strQuery.append(" AND p.gnUsuariosId.nombre LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "cntPrestadorSede.nombreSede":
                            strQuery.append(" AND p.cntPrestadorSedesId.nombre LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "peAfiliadoDiagnosticoList":
                            strTitulo = agregarJoin(" INNER JOIN PeAfiliadoDiagnosticos diag ON p.id = diag.peAfiliadosProgramasId ", strTitulo);
                            strQuery.append(" AND diag.maDiagnosticosCodigo = '").append(e.getValue()).append("' ");
                            break;
                        case "fuenteOrigen":
                            strQuery.append(" AND p.fuenteOrigen = '").append(e.getValue()).append("' ");
                            break;
                        case "aucHospitalizacion.id":
                            if (e.getValue().equals("0")) {
                                strQuery.append(" AND p.aucHospitalizacionesId is null ");
                            } else {
                                strQuery.append(" AND p.aucHospitalizacionesId is not null ");
                            }
                            break;
                        case "tipoPaciente":
                            strQuery.append(" AND p.tipoPaciente = ").append(e.getValue()).append(" ");
                            break;
                        case "adherente":
                            strQuery.append(" AND p.adherente = ").append(e.getValue()).append(" ");
                            break;
                        case "disentimiento":
                            strQuery.append(" AND p.disentimiento = ").append(e.getValue()).append(" ");
                            break;
                    }
                }
            }
            strTitulo.append(strQuery.toString());
            cant = (int) (long) getEntityManager().createQuery(strTitulo.toString())
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
    public List<PeAfiliadosPrograma> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<PeAfiliadosPrograma> listResult = new ArrayList();
        try {
            StringBuilder strTitulo = new StringBuilder("SELECT p FROM PeAfiliadosProgramas p  ");
            StringBuilder strQuery = new StringBuilder("  WHERE p.id > 0  ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append(" AND p.id = '").append((String) e.getValue()).append("'");
                            break;
                        case "activo":
                            strQuery.append(" AND p.activo = '").append((String) e.getValue()).append("'");
                            break;
                        case "asegAfiliado.maeTipoDocumento":
                            strQuery.append(" AND p.asegAfiliadosId.maeTipoDocumentoId = '").append((String) e.getValue()).append("'");
                            break;
                        case "asegAfiliado.numeroDocumento":
                            strQuery.append(" AND p.asegAfiliadosId.numeroDocumento = '").append((String) e.getValue()).append("'  ");
                            break;
                        case "asegAfiliado.nombres":
                            strQuery.append(" AND CONCAT(p.asegAfiliadosId.primerNombre, ' ', p.asegAfiliadosId.segundoNombre) LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "asegAfiliado.apellidos":
                            strQuery.append(" AND CONCAT(p.asegAfiliadosId.primerApellido, ' ', p.asegAfiliadosId.segundoApellido)  LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "asegAfiliado.maeGeneroId":
                            strQuery.append(" AND p.asegAfiliadosId.maeGeneroId = ").append(e.getValue());
                            break;
                        case "asegAfiliado.afiliacionUbicacion.nombre":
                            strQuery.append(" AND p.asegAfiliadosId.afiliacionUbicacionesId.nombre = '").append(e.getValue()).append("' ");
                            break;
                        case "asegAfiliado.maeEstadoAfiliacion":
                            strQuery.append(" AND p.asegAfiliadosId.maeEstadoAfiliacionId = '").append(e.getValue()).append("' ");
                            break;
                        case "asegAfiliado.maeRegimenId":
                            strQuery.append(" AND p.asegAfiliadosId.maeRegimenId = '").append(e.getValue()).append("' ");
                            break;
                        case "pePrograma.descripcionPrograma":
                            strQuery.append(" AND p.peProgramasId.id = '").append(e.getValue()).append("' ");
                            break;
                        case "maeDxPrincipalId":
                            strQuery.append(" AND p.maeDxPrincipalId = '").append(e.getValue()).append("' ");
                            break;
                        case "maeDx2Id":
                            strQuery.append(" AND p.maeDx2Id = '").append(e.getValue()).append("' ");
                            break;
                        case "maeDx3Id":
                            strQuery.append(" AND p.maeDx3Id = '").append(e.getValue()).append("' ");
                            break;
                        case "maeRegionCorporalId":
                            strQuery.append(" AND p.maeRegionCorporalId = '").append(e.getValue()).append("' ");
                            break;
                        case "gnUsuario.nombre":
                            strQuery.append(" AND p.gnUsuariosId.nombre LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "cntPrestadorSede.nombreSede":
                            strQuery.append(" AND p.cntPrestadorSedesId.nombre LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "peAfiliadoDiagnosticoList":
                            strTitulo = agregarJoin(" INNER JOIN PeAfiliadoDiagnosticos diag ON p.id = diag.peAfiliadosProgramasId ", strTitulo);
                            strQuery.append(" AND diag.maDiagnosticosCodigo = '").append(e.getValue()).append("' ");
                            break;
                        case "fuenteOrigen":
                            strQuery.append(" AND p.fuenteOrigen = '").append(e.getValue()).append("' ");
                            break;
                        case "aucHospitalizacion.id":
                            if (e.getValue().equals("0")) {
                                strQuery.append(" AND p.aucHospitalizacionesId is null ");
                            } else {
                                strQuery.append(" AND p.aucHospitalizacionesId is not null ");
                            }
                            break;
                        case "tipoPaciente":
                            strQuery.append(" AND p.tipoPaciente = ").append(e.getValue()).append(" ");
                            break;
                        case "adherente":
                            strQuery.append(" AND p.adherente = ").append(e.getValue()).append(" ");
                            break;
                        case "disentimiento":
                            strQuery.append(" AND p.disentimiento = ").append(e.getValue()).append(" ");
                            break;
                    }
                }
            }
            strTitulo.append(strQuery.toString()).append(" ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                if (paramConsulta.getOrden().equals("asegAfiliado.nombres")) {
                    strTitulo.append("p.asegAfiliadosId.primerNombre ").append((paramConsulta.isAscendente() ? "ASC" : "DESC"));
                } else if (paramConsulta.getOrden().equals("asegAfiliado.apellidos")) {
                    strTitulo.append("p.asegAfiliadosId.primerApellido ").append((paramConsulta.isAscendente() ? "ASC" : "DESC"));
                } else if (paramConsulta.getOrden().equals("asegAfiliado.afiliacionUbicacion.nombre")) {
                    strTitulo.append("p.asegAfiliadosId.afiliacionUbicacionesId.nombre ").append((paramConsulta.isAscendente() ? "ASC" : "DESC"));
                } else if (paramConsulta.getOrden().equals("asegAfiliado.maeEstadoAfiliacion")) {
                    strTitulo.append("p.asegAfiliadosId.maeEstadoAfiliacionId ").append((paramConsulta.isAscendente() ? "ASC" : "DESC"));
                } else if (paramConsulta.getOrden().equals("asegAfiliado.regimen")) {
                    strTitulo.append("p.asegAfiliadosId.regimen ").append((paramConsulta.isAscendente() ? "ASC" : "DESC"));
                } else if (paramConsulta.getOrden().equals("pePrograma.descripcionPrograma")) {
                    strTitulo.append("peProgramasId.descripcionPrograma ").append((paramConsulta.isAscendente() ? "ASC" : "DESC"));
                } else if (paramConsulta.getOrden().equals("gnUsuario.nombre")) {
                    strTitulo.append("gnUsuariosId.nombre ").append((paramConsulta.isAscendente() ? "ASC" : "DESC"));
                } else if (paramConsulta.getOrden().equals("asegAfiliado.numeroDocumento")) {
                    strTitulo.append("p.asegAfiliadosId.numeroDocumento ").append((paramConsulta.isAscendente() ? "ASC" : "DESC"));
                } else if (paramConsulta.getOrden().equals("asegAfiliado.fechaNacimiento")) {
                    strTitulo.append("p.asegAfiliadosId.fechaNacimiento ").append((paramConsulta.isAscendente() ? "ASC" : "DESC"));
                } else if (paramConsulta.getOrden().equals("asegAfiliado.genero")) {
                    strTitulo.append("p.asegAfiliadosId.genero ").append((paramConsulta.isAscendente() ? "ASC" : "DESC"));
                } else if (paramConsulta.getOrden().equals("cntPrestadorSede.id")) {
                    strTitulo.append("p.cntPrestadorSedesId.id ").append((paramConsulta.isAscendente() ? "ASC" : "DESC"));
                } else {
                    strTitulo.append("p." + paramConsulta.getOrden()).append((paramConsulta.isAscendente() ? " ASC" : " DESC"));
                }
            } else {
                strTitulo.append(" p.id DESC");
            }
            List<PeAfiliadosProgramas> list = getEntityManager().createQuery(strTitulo.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (PeAfiliadosProgramas per : list) {
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
    public PeAfiliadosPrograma consultar(int id) throws Exception {
        PeAfiliadosPrograma objResult = new PeAfiliadosPrograma();

        try {
            objResult = castEntidadNegocio((PeAfiliadosProgramas) getEntityManager().find(PeAfiliadosProgramas.class, id));
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
    public List<PeAfiliadosPrograma> consultarAfiliados(int idAfiliado) throws Exception {
        List<PeAfiliadosPrograma> listResult = new ArrayList<>();
        try {
            String strQuery = "SELECT p FROM PeAfiliadosProgramas p WHERE p.asegAfiliadosId.id = :id ";

            List<PeAfiliadosProgramas> list = getEntityManager().createQuery(strQuery)
                    .setParameter("id", idAfiliado)
                    .getResultList();
            for (PeAfiliadosProgramas per : list) {
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
    public List<PeAfiliadosPrograma> consultarAfiliadoActivo(int idAfiliado) throws Exception {
        List<PeAfiliadosPrograma> listResult = new ArrayList<>();
        try {
            String strQuery = "SELECT p FROM PeAfiliadosProgramas p WHERE p.asegAfiliadosId.id = :id AND p.activo = 1 "
                    + " AND p.peProgramasId.activo = 1 AND p.asegAfiliadosId.maeEstadoAfiliacionCodigo = 101 ";

            List<PeAfiliadosProgramas> list = getEntityManager().createQuery(strQuery)
                    .setParameter("id", idAfiliado)
                    .getResultList();
            for (PeAfiliadosProgramas per : list) {
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
    public int insertar(PeAfiliadosPrograma obj) throws Exception {
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
    public void actualizar(PeAfiliadosPrograma obj) throws Exception {
        try {
            String sql = "UPDATE PeAfiliadosProgramas SET "
                    + "activo = :activo, "
                    + "maeCausaActivoId = :maeCausaActivoId, "
                    + "maeCausaActivoCodigo = :maeCausaActivoCodigo, "
                    + "maeCausaActivoValor = :maeCausaActivoValor, "
                    + "maeCausaInactivoId = :maeCausaInactivoId, "
                    + "maeCausaInactivoCodigo = :maeCausaInactivoCodigo, "
                    + "maeCausaInactivoValor = :maeCausaInactivoValor, "
                    + "fechaInicioPrograma = :fechaInicioPrograma, "
                    + "fechaFinPrograma = :fechaFinPrograma, "
                    + "fechaDiagnostico = :fechaDiagnostico, "
                    + "estadoDiagnostico = :estadoDiagnostico, "
                    + "maeRegionCorporalId = :maeRegionCorporalId, "
                    + "maeRegionCorporalCodigo = :maeRegionCorporalCodigo, "
                    + "maeRegionCorporalValor = :maeRegionCorporalValor, "
                    + "maeMedioDxId = :maeMedioDxId, "
                    + "maeMedioDxCodigo = :maeMedioDxCodigo, "
                    + "maeMedioDxValor = :maeMedioDxValor, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica, "
                    + "peProgramasId.id = :peProgramasId, "
                    + "asegAfiliadosId.id = :asegAfiliadosId, "
                    + "gnUsuariosId.id = :gnUsuariosId, "
                    + "cntPrestadorSedesId.id = :cntPrestadorSedesId, "
                    + "tipoPaciente = :tipoPaciente, "
                    + "adherente = :adherente, "
                    + "disentimiento = :disentimiento, "
                    + "estadoSivigila = :estadoSivigila, "
                    + "maeCausaDescarteId = :maeCausaDescarteId, "
                    + "maeCausaDescarteCodigo = :maeCausaDescarteCodigo, "
                    + "maeCausaDescarteValor = :maeCausaDescarteValor, "
                    + "notificadoSivigila = :notificadoSivigila, "
                    + "planificacionFamiliar = :planificacionFamiliar, "
                    + "maeNueveSentenciasId = :maeNueveSentenciasId, "
                    + "maeNueveSentenciasCodigo = :maeNueveSentenciasCodigo, "
                    + "maeNueveSentenciasValor = :maeNueveSentenciasValor "
                    + "WHERE id = :id";

            Query query = getEntityManager().createQuery(sql);
            query.setParameter("activo", obj.getActivo());
            query.setParameter("maeCausaActivoId", obj.getMaeCausaActivoId());
            query.setParameter("maeCausaActivoCodigo", obj.getMaeCausaActivoCodigo());
            query.setParameter("maeCausaActivoValor", obj.getMaeCausaActivoValor());
            query.setParameter("maeCausaInactivoId", obj.getMaeCausaInactivoId());
            query.setParameter("maeCausaInactivoCodigo", obj.getMaeCausaInactivoCodigo());
            query.setParameter("maeCausaInactivoValor", obj.getMaeCausaInactivoValor());
            query.setParameter("fechaInicioPrograma", obj.getFechaInicioPrograma());
            query.setParameter("fechaFinPrograma", obj.getFechaFinPrograma());

            query.setParameter("fechaDiagnostico", obj.getFechaDiagnostico());
            query.setParameter("estadoDiagnostico", obj.getEstadoDiagnostico());

            query.setParameter("maeRegionCorporalId", obj.getMaeRegionCorporalId());
            query.setParameter("maeRegionCorporalCodigo", obj.getMaeRegionCorporalCodigo());
            query.setParameter("maeRegionCorporalValor", obj.getMaeRegionCorporalValor());

            query.setParameter("maeMedioDxId", obj.getMaeMedioDxId());
            query.setParameter("maeMedioDxCodigo", obj.getMaeMedioDxCodigo());
            query.setParameter("maeMedioDxValor", obj.getMaeMedioDxValor());

            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("peProgramasId", obj.getPePrograma().getId());
            query.setParameter("asegAfiliadosId", obj.getAsegAfiliado().getId());
            query.setParameter("gnUsuariosId", obj.getGnUsuario() != null ? obj.getGnUsuario().getId() : null);
            query.setParameter("cntPrestadorSedesId", obj.getCntPrestadorSede().getId());
            query.setParameter("tipoPaciente", Short.valueOf(obj.getTipoPaciente().toString()));
            query.setParameter("adherente", obj.getAdherente());
            query.setParameter("disentimiento", obj.getDisentimiento());
            query.setParameter("estadoSivigila", obj.getEstadoSivigila());
            query.setParameter("maeCausaDescarteId", obj.getMaeCausaDescarteId());
            query.setParameter("maeCausaDescarteCodigo", obj.getMaeCausaDescarteCodigo());
            query.setParameter("maeCausaDescarteValor", obj.getMaeCausaDescarteValor());
            query.setParameter("notificadoSivigila", obj.getNotificadoSivigila());
            query.setParameter("planificacionFamiliar", obj.isPlanificacionFamiliar());
            query.setParameter("maeNueveSentenciasId", obj.getIdSentencia());
            query.setParameter("maeNueveSentenciasCodigo", obj.getCodigoSentencia());
            query.setParameter("maeNueveSentenciasValor", obj.getValorSentencia());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public PeAfiliadosPrograma eliminar(int id) throws Exception {
        PeAfiliadosPrograma obj = null;
        try {
            PeAfiliadosProgramas ent = getEntityManager().find(PeAfiliadosProgramas.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
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

    /**
     * Consulta la cantidad de registros de un afiliado en un programa segun el
     * estado del programa
     *
     * @param idAfiliado
     * @param idPrograma
     * @return
     * @throws Exception
     */
    @Override
    public int consultarCantidadProgramaEstado(int idAfiliado, int idPrograma, Integer estadoPrograma) throws Exception {
        int cantidad = 0;
        try {
            StringBuilder query = new StringBuilder();
            query.append(" SELECT COUNT(p) FROM PeAfiliadosProgramas p ");
            query.append(" WHERE p.id > 0 ");
            if (estadoPrograma != null) {
                query.append(" AND p.activo = ").append(estadoPrograma);
            }
            query.append(" AND p.asegAfiliadosId.id = ").append(idAfiliado);
            query.append(" AND p.peProgramasId.id = ").append(idPrograma);
            cantidad = (int) (long) getEntityManager().createQuery(query.toString())
                    .getSingleResult();
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
    public List<PeAfiliadosPrograma> consultarAfiliadoPorProgramaEstado(int idAfiliado, int idPrograma, boolean estado) throws Exception {
        List<PeAfiliadosPrograma> listResult = new ArrayList<>();
        try {
            String strQuery = "SELECT p FROM PeAfiliadosProgramas p WHERE p.asegAfiliadosId.id = :idAfiliado "
                    + " AND p.peProgramasId.id = :idPrograma AND p.activo = :activo ";

            List<PeAfiliadosProgramas> list = getEntityManager().createQuery(strQuery)
                    .setParameter("idAfiliado", idAfiliado).setParameter("idPrograma", idPrograma).setParameter("activo", estado)
                    .getResultList();
            for (PeAfiliadosProgramas per : list) {
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
    public PeAfiliadosPrograma consultarAfiliadoPorProgramaFechaFin(int idAfiliado, int idPrograma, Date fechaFin) throws Exception {
        PeAfiliadosPrograma result = new PeAfiliadosPrograma();
        int i = 0;
        try {
            String strQuery = "SELECT p FROM PeAfiliadosProgramas p WHERE p.asegAfiliadosId.id = :idAfiliado "
                    + " AND p.peProgramasId.id = :idPrograma AND p.fechaFinPrograma = :fechaFin ";

            List<PeAfiliadosProgramas> list = getEntityManager().createQuery(strQuery)
                    .setParameter("idAfiliado", idAfiliado).setParameter("idPrograma", idPrograma).setParameter("fechaFin", fechaFin)
                    .getResultList();
            for (PeAfiliadosProgramas per : list) {
                if (i == 0) {
                    result = castEntidadNegocio(per);
                    i++;
                }
            }

        } catch (NoResultException e) {
            result = new PeAfiliadosPrograma();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
            result = new PeAfiliadosPrograma();
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    @Override
    public int consultarCantidadListaPorAfiliado(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM PeAfiliadosProgramas p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "AND p.asegAfiliadosId.id = '" + paramConsulta.getEmpresaId() + "' ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "pePrograma.descripcionPrograma":
                            strQuery += "AND p.peProgramasId.descripcionPrograma LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "fuenteOrigen":
                            strQuery += " AND p.fuenteOrigen = '" + e.getValue() + "' ";
                            break;
                        case "pePrograma.codigoPrograma":
                            strQuery += "AND p.peProgramasId.codigoPrograma = '" + e.getValue() + "' ";
                            break;
                        case "pePrograma.maeTipoProgramaId":
                            strQuery += "AND p.peProgramasId.maeTipoProgramaId = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += "AND p.activo = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntPrestadorSede.nombreSede":
                            strQuery += "AND p.cntPrestadorSedesId.nombre LIKE '%" + (String) e.getValue() + "%' ";
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
    public List<PeAfiliadosPrograma> consultarListaPorAfiliado(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<PeAfiliadosPrograma> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p FROM PeAfiliadosProgramas p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "AND p.asegAfiliadosId.id = '" + paramConsulta.getEmpresaId() + "' ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "pePrograma.descripcionPrograma":
                            strQuery += "AND p.peProgramasId.descripcionPrograma LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "fuenteOrigen":
                            strQuery += " AND p.fuenteOrigen = '" + e.getValue() + "' ";
                            break;
                        case "pePrograma.codigoPrograma":
                            strQuery += "AND p.peProgramasId.codigoPrograma = '" + e.getValue() + "' ";
                            break;
                        case "pePrograma.maeTipoProgramaId":
                            strQuery += "AND p.peProgramasId.maeTipoProgramaId = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += "AND p.activo = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntPrestadorSede.nombreSede":
                            strQuery += "AND p.cntPrestadorSedesId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;

                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {

                if (paramConsulta.getOrden().equals("pePrograma.descripcionPrograma")) {
                    strQuery += "p.peProgramasId.descripcionPrograma "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else if (paramConsulta.getOrden().equals("pePrograma.codigoPrograma")) {
                    strQuery += "p.peProgramasId.codigoPrograma "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else if (paramConsulta.getOrden().equals("pePrograma.maeTipoProgramaId")) {
                    strQuery += "p.peProgramasId.maeTipoProgramaId "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else if (paramConsulta.getOrden().equals("cntPrestadorSede.nombreSede")) {
                    strQuery += "p.cntPrestadorSedesId.nombre "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else {
                    strQuery += "p." + paramConsulta.getOrden() + " "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                }
            } else {
                strQuery += "p.id DESC";
            }
            List<PeAfiliadosProgramas> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (PeAfiliadosProgramas per : list) {
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
    public PeAfiliadosPrograma consultarAfiliadoPorPrograma(int idAfiliado, int idPrograma) throws Exception {
        PeAfiliadosPrograma result = new PeAfiliadosPrograma();
        int i = 0;
        try {
            String strQuery = "SELECT p FROM PeAfiliadosProgramas p WHERE p.asegAfiliadosId.id = :idAfiliado "
                    + " AND p.peProgramasId.id = :idPrograma ";

            List<PeAfiliadosProgramas> list = getEntityManager().createQuery(strQuery)
                    .setParameter("idAfiliado", idAfiliado).setParameter("idPrograma", idPrograma)
                    .getResultList();
            for (PeAfiliadosProgramas per : list) {
                if (i == 0) {
                    result = castEntidadNegocio(per);
                    i++;
                }
            }

        } catch (NoResultException e) {
            result = new PeAfiliadosPrograma();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
            result = new PeAfiliadosPrograma();
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    @Override
    public void actualizarEstadoActivo(PeAfiliadosPrograma obj) throws Exception {
        try {
            String sql = "UPDATE PeAfiliadosProgramas SET activo = :activo, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE asegAfiliadosId.id = :id "
                    + "AND peProgramasId.id = :peProgramasId ";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("id", obj.getAsegAfiliado().getId());
            query.setParameter("peProgramasId", obj.getPePrograma().getId());
            query.setParameter("activo", obj.getActivo());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());

            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public int consultarDuplicadoProgramaAfiliadoActivo(int idAfiliado, int idPrograma, int idAfiliadoPrograma) throws Exception {
        int cantidad = 0;
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(" SELECT COUNT(p) FROM PeAfiliadosProgramas p ");
            strQuery.append(" WHERE p.activo = 1 AND p.asegAfiliadosId.id =  ").append(idAfiliado);
            strQuery.append(" AND p.peProgramasId.id =  ").append(idPrograma);
            strQuery.append(" AND p.id not in (").append(idAfiliadoPrograma).append(") ");

            cantidad = (int) (long) getEntityManager().createQuery(strQuery.toString())
                    .getSingleResult();
        } catch (NoResultException e) {
            cantidad = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cantidad;

    }

    /**
     * Metodo encargado de cambiar el estado de todos los afiliado asociados a
     * un programa especifico
     *
     * @author idbohorquez
     * @param programa
     * @param idPrograma
     * @param causaEstado
     * @throws Exception
     */
    @Override
    public void cambiarEstadoAfiliadosPrograma(PePrograma programa, Maestro causaEstado) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE PeAfiliadosProgramas SET activo = :activo, ");
            sql.append(" fechaFinPrograma = :fechaFin,");
            sql.append(" maeCausaInactivoId = :maeCausaId, ");
            sql.append(" maeCausaInactivoCodigo = :maeCausaCodigo, ");
            sql.append(" maeCausaInactivoValor = :maeCausaValor, ");
            sql.append(" usuarioModifica = :usuarioModifica, ");
            sql.append(" terminalModifica = :terminalModifica, ");
            sql.append(" fechaHoraModifica = :fechaHoraModifica ");
            sql.append(" WHERE peProgramasId.id = :idPrograma AND activo = :oldEstado ");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("activo", false);
            query.setParameter("fechaFin", new Date());
            query.setParameter("maeCausaId", causaEstado.getId());
            query.setParameter("maeCausaCodigo", causaEstado.getTipo());
            query.setParameter("maeCausaValor", causaEstado.getNombre());
            query.setParameter("usuarioModifica", programa.getUsuarioModifica());
            query.setParameter("terminalModifica", programa.getTerminalModifica());
            query.setParameter("fechaHoraModifica", programa.getFechaHoraModifica());
            query.setParameter("idPrograma", programa.getId());
            query.setParameter("oldEstado", true);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    /**
     * Función encargada de validar si un afiliado esta inscrito en el listado
     * de programas recibidos.
     *
     * @author idbohorquez
     * @creado 15/09/2022
     * @param idAfiliado
     * @param idProgramas
     * @return List<PeAfiliadosPrograma>
     * @throws Exception
     */
    @Override
    public List<PeAfiliadosPrograma> programasMatriculadosAfiliado(Integer idAfiliado, String idProgramas) throws java.lang.Exception {
        List<PeAfiliadosPrograma> listAfiliadoPrograma = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder(" FROM PeAfiliadosProgramas p ");
            strQuery.append(" WHERE p.asegAfiliadosId.id = :idAfiliado ");
            strQuery.append(" AND p.peProgramasId.id in (").append(idProgramas).append(") ");
            strQuery.append(" AND p.activo = 1 AND p.cntPrestadorSedesId > 1 ");

            Query query = getEntityManager().createQuery(strQuery.toString());
            query.setParameter("idAfiliado", idAfiliado);

            List<PeAfiliadosProgramas> list = query.getResultList();
            for (PeAfiliadosProgramas peAfiliadosPrograma : list) {
                listAfiliadoPrograma.add(castEntidadNegocio(peAfiliadosPrograma));
            }

        } catch (NoResultException e) {
            listAfiliadoPrograma = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listAfiliadoPrograma;
    }

    /**
     * Función encargada de validar si un afiliado esta inscrito en el listado
     * de programas recibidos sin importar su su sede es sin especificar.
     *
     * @author idbohorquez
     * @param diagnosticos
     * @creado 15/09/2022
     * @param idAfiliado
     * @param idProgramas
     * @return List<PeAfiliadosPrograma>
     * @throws Exception
     */
    @Override
    public List<PeAfiliadosPrograma> programasMatriculadosAfiliadoAllSede(Integer idAfiliado, String idProgramas, String diagnosticos) throws java.lang.Exception {
        List<PeAfiliadosPrograma> listAfiliadoPrograma = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder(" FROM PeAfiliadosProgramas p ");
            strQuery.append(" WHERE p.asegAfiliadosId.id = :idAfiliado ");
            strQuery.append(" AND p.peProgramasId.id in (").append(idProgramas).append(") ");
            strQuery.append(" AND ( p.maDiagnosticoPrincipalId in (").append(diagnosticos).append(") or p.maDiagnostico2Id in (").append(diagnosticos).append(") or p.maDiagnostico3Id in (").append(diagnosticos).append(") ) ");
            strQuery.append(" AND p.activo = 1 ");

            Query query = getEntityManager().createQuery(strQuery.toString());
            query.setParameter("idAfiliado", idAfiliado);

            List<PeAfiliadosProgramas> list = query.getResultList();
            for (PeAfiliadosProgramas peAfiliadosPrograma : list) {
                listAfiliadoPrograma.add(castEntidadNegocio(peAfiliadosPrograma));
            }

        } catch (NoResultException e) {
            listAfiliadoPrograma = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listAfiliadoPrograma;
    }

    /**
     * Función encargada de consultar afilaidos programas segun filtros
     * recibidos por parametros
     *
     * @author idbohorquez
     * @creado 14/04/2023
     * @param idAfilaido
     * @param ambito
     * @param diagnosticos
     * @return List<PeAfiliadosPrograma>
     * @throws Exception
     */
    @Override
    public List<PeAfiliadosPrograma> aplicarRescateAnexo3(Integer idAfilaido, Integer ambito, String diagnosticos) throws java.lang.Exception {
        List<PeAfiliadosPrograma> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT DISTINCT p FROM PeAfiliadosProgramas p ");
            strQuery.append(" INNER JOIN PeProgramaDiagnosticos ppt ON p.peProgramasId = ppt.peProgramasId  ");
            strQuery.append(" and ppt.aplicaRescate = 1 and ppt.borrado = 0 ");
            strQuery.append(" and ppt.maDiagnosticoId in (").append(diagnosticos).append(") ");
            strQuery.append(" WHERE p.asegAfiliadosId.id = :idAfiliado AND p.activo = 1 AND p.peProgramasId.activo = 1 AND p.cntPrestadorSedesId.id NOT IN (1) ");
            if (ambito == PeAfiliadosPrograma.AMBITO_AMBULATORIO) {
                strQuery.append(" and p.peProgramasId.aplicaRescateAnexo3Ambulatorio = 1 ");
            } else if (ambito == PeAfiliadosPrograma.AMBITO_HOSPITALARIO) {
                strQuery.append(" and p.peProgramasId.aplicaRescateAnexo3Hospitalario = 1 ");
            }
            List<PeAfiliadosProgramas> list = getEntityManager().createQuery(strQuery.toString())
                    .setParameter("idAfiliado", idAfilaido)
                    .getResultList();
            for (PeAfiliadosProgramas per : list) {
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
    public List<PeAfiliadosPrograma> listarPorAfiliadoYCodigoPrograma(Integer idAfiliado, String codigoPrograma) throws Exception {
        List<PeAfiliadosPrograma> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p FROM PeAfiliadosProgramas p "
                    + "WHERE p.asegAfiliadosId.id = " + idAfiliado + " AND p.peProgramasId.codigoPrograma = '" + codigoPrograma + "' ";
            strQuery += "ORDER BY p.id";

            List<PeAfiliadosProgramas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (PeAfiliadosProgramas per : list) {
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

    /**
     * Consulta la cantidad de afiliados que continene un programa especifico
     *
     * @param idPrograma
     * @return int
     * @throws Exception
     */
    @Override
    public int consultarCantidadAfiliadoPrograma(int idPrograma) throws Exception {
        int cantidad = 0;
        try {
            StringBuilder query = new StringBuilder();
            query.append(" SELECT COUNT(p) FROM PeAfiliadosProgramas p ");
            query.append(" WHERE p.id > 0 AND p.peProgramasId.id = ").append(idPrograma);
            cantidad = (int) (long) getEntityManager().createQuery(query.toString())
                    .getSingleResult();
        } catch (NoResultException e) {
            cantidad = 0;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return cantidad;

    }

    public static PeAfiliadosPrograma castEntidadNegocio(PeAfiliadosProgramas ent) {
        PeAfiliadosPrograma obj = new PeAfiliadosPrograma();
        if (ent.getAsegAfiliadosId() != null) {
            AsegAfiliado afiliado = new AsegAfiliado(ent.getAsegAfiliadosId().getId());
            afiliado.setEmail(ent.getAsegAfiliadosId().getEmail());
            afiliado.setMaeGrupoPoblacionalValor(ent.getAsegAfiliadosId().getMaeGrupoPoblacionalValor());
            afiliado.setMaeEtniaValor(ent.getAsegAfiliadosId().getMaeEtniaValor());
            afiliado.setMaeModeloLiquidacionValor(ent.getAsegAfiliadosId().getMaeModeloLiquidacionValor());
            obj.setAsegAfiliado(afiliado);
        }
        obj.setActivo(ent.getActivo());
        obj.setFechaDiagnostico(ent.getFechaDiagnostico());
        obj.setEstadoDiagnostico(ent.getEstadoDiagnostico());
        obj.setFechaFinPrograma(ent.getFechaFinPrograma());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setFechaInicioPrograma(ent.getFechaInicioPrograma());
        obj.setId(ent.getId());
        obj.setTipoPaciente((int) ent.getTipoPaciente());
        List<PeAfiliadoDiagnostico> diagnosticos = new ArrayList<>();
        for (PeAfiliadoDiagnosticos item : ent.getPeAfiliadoDiagnosticosList()) {
            PeAfiliadoDiagnostico diagnostico = new PeAfiliadoDiagnostico();
            diagnostico.setId(item.getId());
            diagnostico.setMaDiagnosticosId(item.getMaDiagnosticosId());
            diagnostico.setMaDiagnosticosCodigo(item.getMaDiagnosticosCodigo());
            diagnostico.setMaDiagnosticosValor(item.getMaDiagnosticosValor());
            diagnostico.setPrincipal(item.getPrincipal());
            diagnosticos.add(diagnostico);
        }
        obj.setPeAfiliadoDiagnosticoList(diagnosticos);

        obj.setMaeCausaActivoCodigo(ent.getMaeCausaActivoCodigo());
        obj.setMaeCausaActivoId(ent.getMaeCausaActivoId());
        obj.setMaeCausaActivoValor(ent.getMaeCausaActivoValor());

        obj.setMaeCausaInactivoCodigo(ent.getMaeCausaInactivoCodigo());
        obj.setMaeCausaInactivoId(ent.getMaeCausaInactivoId());
        obj.setMaeCausaInactivoValor(ent.getMaeCausaInactivoValor());

        obj.setMaeMedioDxCodigo(ent.getMaeMedioDxCodigo());
        obj.setMaeMedioDxId(ent.getMaeMedioDxId());
        obj.setMaeMedioDxValor(ent.getMaeMedioDxValor());

        obj.setMaeRegionCorporalCodigo(ent.getMaeRegionCorporalCodigo());
        obj.setMaeRegionCorporalId(ent.getMaeRegionCorporalId());
        obj.setMaeRegionCorporalValor(ent.getMaeRegionCorporalValor());

        if (ent.getPeProgramasId() != null) {
            obj.setPePrograma(castPrograma(ent.getPeProgramasId()));
        }
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        if (ent.getGnUsuariosId() != null) {
            obj.setGnUsuario(new Usuario(ent.getGnUsuariosId().getId()));
        }

        if (ent.getCntPrestadorSedesId() != null) {
            CntPrestadorSede prestador = new CntPrestadorSede();
            prestador.setId(ent.getCntPrestadorSedesId().getId());
            prestador.setNombreSede(ent.getCntPrestadorSedesId().getNombre());
            prestador.setCntPrestador(new CntPrestador(ent.getCntPrestadorSedesId().getCntPrestadoresId().getId()));
            obj.setCntPrestadorSede(prestador);
        }
        obj.setFuenteOrigen(ent.getFuenteOrigen());
        if (ent.getAucHospitalizacionesId() != null) {
            obj.setAucHospitalizacion(new AucHospitalizacion(ent.getAucHospitalizacionesId().getId()));
        }
        if (ent.getAsegAfiliadosId() != null) {
            obj.setAsegAfiliado(castAfiliado(ent.getAsegAfiliadosId()));
        }

        if (ent.getAdherente() != null) {
            obj.setAdherente(ent.getAdherente());
        }
        if (ent.getDisentimiento() != null) {
            obj.setDisentimiento(ent.getDisentimiento());
        }
        obj.setEstadoSivigila(ent.getEstadoSivigila());
        if (ent.getMaeCausaDescarteId() != null) {
            obj.setMaeCausaDescarteId(ent.getMaeCausaDescarteId());
            obj.setMaeCausaDescarteCodigo(ent.getMaeCausaDescarteCodigo());
            obj.setMaeCausaDescarteValor(ent.getMaeCausaDescarteValor());
        }
        obj.setNotificadoSivigila(ent.getNotificadoSivigila());
        obj.setPlanificacionFamiliar(ent.getPlanificacionFamiliar());
        obj.setIdSentencia(ent.getMaeNueveSentenciasId());
        obj.setCodigoSentencia(ent.getMaeNueveSentenciasCodigo());
        obj.setValorSentencia(ent.getMaeNueveSentenciasValor());

        return obj;
    }

    public static PeAfiliadosProgramas castNegocioEntidad(PeAfiliadosPrograma obj) {
        PeAfiliadosProgramas ent = new PeAfiliadosProgramas();

        if (obj.getAsegAfiliado() != null) {
            ent.setAsegAfiliadosId(new AsegAfiliados(obj.getAsegAfiliado().getId()));
        }
        ent.setActivo(obj.getActivo());
        ent.setFechaDiagnostico(obj.getFechaDiagnostico());
        ent.setEstadoDiagnostico(obj.getEstadoDiagnostico());
        ent.setFechaFinPrograma(obj.getFechaFinPrograma());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        ent.setFechaInicioPrograma(obj.getFechaInicioPrograma());
        ent.setId(obj.getId());

        ent.setMaeCausaActivoCodigo(obj.getMaeCausaActivoCodigo());
        if (obj.getMaeCausaActivoId() != null) {
            ent.setMaeCausaActivoId(obj.getMaeCausaActivoId());
        }
        ent.setMaeCausaActivoValor(obj.getMaeCausaActivoValor());

        ent.setMaeCausaInactivoCodigo(obj.getMaeCausaInactivoCodigo());
        ent.setMaeCausaInactivoId(obj.getMaeCausaInactivoId());
        ent.setMaeCausaInactivoValor(obj.getMaeCausaInactivoValor());

        ent.setMaeMedioDxCodigo(obj.getMaeMedioDxCodigo());
        ent.setMaeMedioDxId(obj.getMaeMedioDxId());
        ent.setMaeMedioDxValor(obj.getMaeMedioDxValor());

        ent.setMaeRegionCorporalCodigo(obj.getMaeRegionCorporalCodigo());
        ent.setMaeRegionCorporalId(obj.getMaeRegionCorporalId());
        ent.setMaeRegionCorporalValor(obj.getMaeRegionCorporalValor());

        if (obj.getPePrograma() != null) {
            ent.setPeProgramasId(new PeProgramas(obj.getPePrograma().getId()));
        }
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        if (obj.getGnUsuario().getId() != null) {
            ent.setGnUsuariosId(new GnUsuarios(obj.getGnUsuario().getId()));
        }

        if (obj.getCntPrestadorSede() != null) {
            ent.setCntPrestadorSedesId(new CntPrestadorSedes(obj.getCntPrestadorSede().getId()));
        }
        ent.setFuenteOrigen(obj.getFuenteOrigen());
        if (obj.getIdSolicitudOrigen() != null) {
            ent.setIdSolicitudOrigen(obj.getIdSolicitudOrigen());
        }
        if (obj.getPeAfiliadoSugerido() != null) {
            ent.setPeAfiliadosSugeridosId(new PeAfiliadosSugeridos(obj.getPeAfiliadoSugerido().getId()));
        }
        if (obj.getAucHospitalizacion() != null) {
            ent.setAucHospitalizacionesId(new AucHospitalizaciones(obj.getAucHospitalizacion().getId()));
        }
        ent.setTipoPaciente(Short.valueOf(obj.getTipoPaciente().toString()));
        ent.setAdherente(obj.getAdherente());
        ent.setDisentimiento(obj.getDisentimiento());
        ent.setEstadoSivigila(obj.getEstadoSivigila());
        ent.setMaeCausaDescarteId(obj.getMaeCausaDescarteId());
        ent.setMaeCausaDescarteCodigo(obj.getMaeCausaDescarteCodigo());
        ent.setMaeCausaDescarteValor(obj.getMaeCausaDescarteValor());
        ent.setNotificadoSivigila(obj.getNotificadoSivigila());
        ent.setPlanificacionFamiliar(obj.isPlanificacionFamiliar());
        ent.setMaeNueveSentenciasId(obj.getIdSentencia());
        ent.setMaeNueveSentenciasCodigo(obj.getCodigoSentencia());
        ent.setMaeNueveSentenciasValor(obj.getValorSentencia());

        return ent;
    }

    public static PeAfiliadosPrograma castEntidadNegocioLargo(PeAfiliadosProgramas ent) {
        PeAfiliadosPrograma obj = new PeAfiliadosPrograma();

        if (ent.getAsegAfiliadosId() != null) {
            obj.setAsegAfiliado(new AsegAfiliado(ent.getAsegAfiliadosId().getId()));
        }
        obj.setActivo(ent.getActivo());
        obj.setEstadoDiagnostico(ent.getEstadoDiagnostico());
        obj.setFechaDiagnostico(ent.getFechaDiagnostico());
        obj.setFechaFinPrograma(ent.getFechaFinPrograma());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setFechaInicioPrograma(ent.getFechaInicioPrograma());
        obj.setId(ent.getId());
        obj.setTipoPaciente((int) ent.getTipoPaciente());
        obj.setMaeCausaActivoCodigo(ent.getMaeCausaActivoCodigo());
        obj.setMaeCausaActivoId(ent.getMaeCausaActivoId());
        obj.setMaeCausaActivoValor(ent.getMaeCausaActivoValor());

        obj.setMaeCausaInactivoCodigo(ent.getMaeCausaInactivoCodigo());
        obj.setMaeCausaInactivoId(ent.getMaeCausaInactivoId());
        obj.setMaeCausaInactivoValor(ent.getMaeCausaInactivoValor());

        obj.setMaeMedioDxCodigo(ent.getMaeMedioDxCodigo());
        obj.setMaeMedioDxId(ent.getMaeMedioDxId());
        obj.setMaeMedioDxValor(ent.getMaeMedioDxValor());

        obj.setMaeRegionCorporalCodigo(ent.getMaeRegionCorporalCodigo());
        obj.setMaeRegionCorporalId(ent.getMaeRegionCorporalId());
        obj.setMaeRegionCorporalValor(ent.getMaeRegionCorporalValor());

        if (ent.getPeProgramasId() != null) {
            obj.setPePrograma(castProgramaEntidadNegocio(ent.getPeProgramasId()));
        }
        obj.setFuenteOrigen(ent.getFuenteOrigen());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        if (ent.getGnUsuariosId() != null) {
            obj.setGnUsuario(new Usuario(ent.getGnUsuariosId().getId()));
        }

        if (ent.getCntPrestadorSedesId() != null) {
            obj.setCntPrestadorSede(new CntPrestadorSede(ent.getCntPrestadorSedesId().getId()));
            obj.getCntPrestadorSede().setNombreSede(ent.getCntPrestadorSedesId().getNombre());
        }
        if (ent.getAdherente() != null) {
            obj.setAdherente(ent.getAdherente());
        } else {
            obj.setAdherente(false);
        }
        if (ent.getDisentimiento() != null) {
            obj.setDisentimiento(ent.getDisentimiento());
        } else {
            obj.setDisentimiento(false);
        }
        obj.setEstadoSivigila(ent.getEstadoSivigila());
        obj.setNotificadoSivigila(ent.getNotificadoSivigila());
        obj.setPlanificacionFamiliar(ent.getPlanificacionFamiliar());
        obj.setIdSentencia(ent.getMaeNueveSentenciasId());
        obj.setCodigoSentencia(ent.getMaeNueveSentenciasCodigo());
        obj.setValorSentencia(ent.getMaeNueveSentenciasValor());

        return obj;
    }

    public static PePrograma castProgramaEntidadNegocio(PeProgramas ent) {
        PePrograma obj = new PePrograma();

        obj.setActivo(ent.getActivo());
        obj.setCodigoPrograma(ent.getCodigoPrograma());
        obj.setDescripcionPrograma(ent.getDescripcionPrograma());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setId(ent.getId());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setMaeTipoProgramaId(ent.getMaeTipoProgramaId());
        obj.setMaeTipoProgramaCodigo(ent.getMaeTipoProgramaCodigo());
        obj.setMaeTipoProgramaValor(ent.getMaeTipoProgramaValor());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setExoneradoCopago(ent.getExoneradoCopago());
        //2022-08-09 jyperez nuevos campos mapeo
        obj.setMaeCategoriaId(ent.getMaeCategoriaId());
        obj.setMaeCategoriaCodigo(ent.getMaeCategoriaCodigo());
        obj.setMaeCategoriaValor(ent.getMaeCategoriaValor());
        //2022-09-30 jyperez adición nuevos campos mapeo
        obj.setAplicaRescate(ent.getAplicaRescate());
        obj.setDirecciona(ent.getDirecciona());
        if (ent.getGnUsuariosId() != null) {
            obj.setUsuariosId(new Usuario(ent.getGnUsuariosId().getId(), ent.getGnUsuariosId().getUsuario(), ent.getGnUsuariosId().getNombre()));
        }
        obj.setRegistroAfiliadoAfiliacion(ent.getRegistroAfiliadoAfiliacion() == 0 ? false : true);
        obj.setRegistroAfiliadoSolicitud(Integer.valueOf(ent.getRegistroAfiliadoSolicitud()));
        obj.setRegistroAfiliadoHospitalizacion(Integer.valueOf(ent.getRegistroAfiliadoHospitalizacion()));

        return obj;
    }

    public static AsegAfiliado castAfiliado(AsegAfiliados per) {
        AsegAfiliado obj = new AsegAfiliado();
        obj.setId(per.getId());
        obj.setIdAfiliado(per.getIdAfiliado());
        obj.setPrimerNombre(per.getPrimerNombre());
        obj.setSegundoNombre(per.getSegundoNombre());
        obj.setPrimerApellido(per.getPrimerApellido());
        obj.setSegundoApellido(per.getSegundoApellido());
        obj.setFechaNacimiento(per.getFechaNacimiento());
        obj.setGenero(per.getGenero());
        obj.setMaeGeneroId(per.getMaeGeneroId());
        obj.setMaeGeneroCodigo(per.getMaeGeneroCodigo());
        obj.setMaeGeneroValor(per.getMaeGeneroValor());
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
        obj.setMaeRegimenId(per.getMaeRegimenId());
        obj.setMaeRegimenCodigo(per.getMaeRegimenCodigo());
        obj.setMaeRegimenValor(per.getMaeRegimenValor());
        return obj;
    }

    public static PePrograma castPrograma(PeProgramas ent) {
        PePrograma obj = new PePrograma();
        obj.setActivo(ent.getActivo());
        obj.setCodigoPrograma(ent.getCodigoPrograma());
        obj.setDescripcionPrograma(ent.getDescripcionPrograma());
        obj.setId(ent.getId());
        obj.setMaeTipoProgramaId(ent.getMaeTipoProgramaId());
        obj.setMaeTipoProgramaCodigo(ent.getMaeTipoProgramaCodigo());
        obj.setMaeTipoProgramaValor(ent.getMaeTipoProgramaValor());
        obj.setMaeCategoriaValor(ent.getMaeCategoriaValor());
        obj.setMaeAgrupadorId(ent.getMaeAgrupadorId());
        obj.setMaeAgrupadorCodigo(ent.getMaeAgrupadorCodigo());
        obj.setMaeAgrupadorValor(ent.getMaeAgrupadorValor());
        obj.setExoneradoCopago(ent.getExoneradoCopago());
        if (ent.getGnUsuariosId() != null) {
            Usuario user = new Usuario(ent.getGnUsuariosId().getId());
            user.setNombre(ent.getGnUsuariosId().getNombre());
            obj.setUsuariosId(user);
        }

        return obj;
    }

    private StringBuilder agregarJoin(String join, StringBuilder sql) {
        if (sql.toString().contains(join)) {
            return sql;
        } else {
            return sql.append(join);
        }
    }

}
