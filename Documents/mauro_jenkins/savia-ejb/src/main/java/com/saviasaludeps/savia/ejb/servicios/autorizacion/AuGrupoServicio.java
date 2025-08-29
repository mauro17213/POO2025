/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuGrupo;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuGrupos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuGrupoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
@Remote(AuGrupoRemoto.class)
public class AuGrupoServicio extends GenericoServicio implements AuGrupoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuGrupos p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "nombre":
                            strQuery += "AND p.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += "AND p.descripcion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "orden":
                            strQuery += "AND g.orden = '" + e.getValue() + "' ";
                            break;
                        case "activo":
                            strQuery += "AND g.activo = " + e.getValue() + " ";
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
    public List<AuGrupo> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuGrupo> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuGrupos p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "nombre":
                            strQuery += "AND p.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += "AND p.descripcion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "orden":
                            strQuery += "AND g.orden = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += "AND g.activo = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.orden ASC";
            }
            List<AuGrupos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuGrupos per : list) {
                listaResultados.add(castEntidadNegocio(per));
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
    public List<AuGrupo> consultarActivos() throws Exception {
        List<AuGrupo> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuGrupos p "
                    + "WHERE p.activo = true ";
            strQuery += "ORDER BY p.nombre";
            List<AuGrupos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuGrupos per : list) {
                listaResultados.add(castEntidadNegocio(per));
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
    public AuGrupo consultar(int id) throws Exception {
        AuGrupo objRes = null;
        try {
            objRes = castEntidadNegocio((AuGrupos) getEntityManager().find(AuGrupos.class, id));
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
    public int insertar(AuGrupo obj) throws Exception {
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
    public void actualizar(AuGrupo obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuGrupos a SET ";
            strQuery += "a.nombre = :nombre ,";
            strQuery += "a.descripcion = :descripcion ,";
            strQuery += "a.orden = :orden ,";
            strQuery += "a.activo = :activo ,";
            strQuery += "a.soloGrupo = :soloGrupo,";
            strQuery += "a.maeAmbitoId = :maeAmbitoId ,";
            strQuery += "a.maeAmbitoCodigo = :maeAmbitoCodigo ,";
            strQuery += "a.maeAmbitoValor = :maeAmbitoValor ,";
            strQuery += "a.generico = :generico ,";
            strQuery += "a.tutela = :tutela ,";
            strQuery += "a.pbs = :pbs ,";
            strQuery += "a.esTecnologia = :esTecnologia ,";
            strQuery += "a.esInsumo = :esInsumo ,";
            strQuery += "a.esMedicamento = :esMedicamento ,";
            strQuery += "a.esPaquete = :esPaquete ,";
            strQuery += "a.aplicaSeguimiento = :aplicaSeguimiento ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("descripcion", obj.getDescripcion());
            query.setParameter("orden", obj.getOrden());
            query.setParameter("activo", obj.isActivo());
            query.setParameter("soloGrupo", obj.isSoloGrupo());
            query.setParameter("maeAmbitoId", obj.getMaeAmbitoId());
            query.setParameter("maeAmbitoCodigo", obj.getMaeAmbitoCodigo());
            query.setParameter("maeAmbitoValor", obj.getMaeAmbitoValor());
            query.setParameter("generico", obj.isGenerico());
            query.setParameter("aplicaSeguimiento", obj.getAplicaSeguimiento());
            query.setParameter("tutela", obj.isTutela());
            query.setParameter("pbs", obj.isPbs());
            query.setParameter("esTecnologia", obj.isEsTecnologia());
            query.setParameter("esInsumo", obj.isEsInsumo());
            query.setParameter("esMedicamento", obj.isEsMedicamento());
            query.setParameter("esPaquete", obj.isEsPaquete());
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
    public AuGrupo eliminar(int id) throws Exception {
        AuGrupo obj = null;
        try {
            AuGrupos ent = getEntityManager().find(AuGrupos.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
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

    private AuGrupo castEntidadNegocio(AuGrupos ent) {
        AuGrupo negocio = new AuGrupo();
        negocio.setId(ent.getId());
        negocio.setNombre(ent.getNombre());
        negocio.setDescripcion(ent.getDescripcion());
        negocio.setActivo(ent.getActivo());
        negocio.setSoloGrupo(ent.getSoloGrupo());
        negocio.setOrden(ent.getOrden());
        negocio.setMaeAmbitoId(ent.getMaeAmbitoId());
        negocio.setMaeAmbitoCodigo(ent.getMaeAmbitoCodigo());
        negocio.setMaeAmbitoValor(ent.getMaeAmbitoValor());
        negocio.setGenerico(ent.getGenerico());
        negocio.setTutela(ent.getTutela());
        negocio.setPbs(ent.getPbs());
        negocio.setEsTecnologia(ent.getEsTecnologia());
        negocio.setEsPaquete(ent.getEsPaquete());
        negocio.setEsInsumo(ent.getEsInsumo());
        negocio.setEsMedicamento(ent.getEsMedicamento());
        negocio.setAplicaSeguimiento(ent.getAplicaSeguimiento());
        negocio.setAuGrupoUsuarioList(new ArrayList());
        negocio.setAuGrupoSedeList(new ArrayList());
        negocio.setAuGrupoDiagnosticoList(new ArrayList());
        negocio.setAuGrupoTecnologiaList(new ArrayList());
        negocio.setUsuarioCrea(ent.getUsuarioCrea());
        negocio.setTerminalCrea(ent.getTerminalCrea());
        negocio.setFechaHoraCrea(ent.getFechaHoraCrea());
        negocio.setUsuarioModifica(ent.getUsuarioModifica());
        negocio.setTerminalModifica(ent.getTerminalModifica());
        negocio.setFechaHoraModifica(ent.getFechaHoraModifica());
        return negocio;
    }

    private AuGrupos castNegocioEntidad(AuGrupo negocio) {
        AuGrupos entidad = new AuGrupos();
        entidad.setId(negocio.getId());
        entidad.setNombre(negocio.getNombre());
        entidad.setDescripcion(negocio.getDescripcion());
        entidad.setActivo(negocio.isActivo());
        entidad.setSoloGrupo(negocio.isSoloGrupo());
        entidad.setOrden(negocio.getOrden());
        entidad.setMaeAmbitoId(negocio.getMaeAmbitoId());
        entidad.setMaeAmbitoCodigo(negocio.getMaeAmbitoCodigo());
        entidad.setMaeAmbitoValor(negocio.getMaeAmbitoValor());
        entidad.setGenerico(negocio.isGenerico());
        entidad.setTutela(negocio.isTutela());
        entidad.setPbs(negocio.isPbs());
        entidad.setEsTecnologia(negocio.isEsTecnologia());
        entidad.setEsPaquete(negocio.isEsPaquete());
        entidad.setEsInsumo(negocio.isEsInsumo());
        entidad.setEsMedicamento(negocio.isEsMedicamento());
        entidad.setAplicaSeguimiento(negocio.getAplicaSeguimiento());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        return entidad;
    }

    @Override
    public void actualizarUltimoUsuario(AuGrupo obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuGrupos a SET ";
            strQuery += "a.ultimoUsuarioMedicoId =: ultimoUsuarioMedicoId,";
            strQuery += "a.ultimoUsuarioEnfermeroId =: ultimoUsuarioEnfermeroId,";
            strQuery += "a.ultimoUsuarioAuxiliarId =: ultimoUsuarioAuxiliarId,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("ultimoUsuarioMedicoId", obj.getUltimoUsuarioMedicoId());
            query.setParameter("ultimoUsuarioEnfermeroId", obj.getUltimoUsuarioEnfermeroId());
            query.setParameter("ultimoUsuarioAuxiliarId", obj.getUltimoUsuarioAuxiliarId());
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
    public List<AuGrupo> consultarOtrosActivos(int idGrupo) throws Exception {
        List<AuGrupo> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuGrupos p "
                    + "WHERE p.activo = true ";
            if (idGrupo > 0) {
                strQuery += "AND p.id != " + idGrupo;
            }
            strQuery += "ORDER BY p.nombre";
            List<AuGrupos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuGrupos per : list) {
                listaResultados.add(castEntidadNegocio(per));
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
    public boolean validarGrupoGenerico() throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuGrupos p "
                    + "WHERE p.generico = 1 ";
            cantidad = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            cantidad = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cantidad > 0;
    }

    @Override
    public boolean validarGrupoTutela() throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuGrupos p "
                    + "WHERE p.tutela = 1 ";
            cantidad = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            cantidad = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cantidad > 0;
    }

    @Override
    public boolean validarGrupoPbs() throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuGrupos p "
                    + "WHERE p.pbs = 1 ";
            cantidad = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            cantidad = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cantidad > 0;
    }

    @Override
    public List<AuGrupo> consultarPorOrden(int orden) throws Exception {
        List<AuGrupo> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuGrupos p "
                    + "WHERE p.orden >= " + orden + " ";
            strQuery += "ORDER BY p.orden";
            List<AuGrupos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuGrupos per : list) {
                listaResultados.add(castEntidadNegocio(per));
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
    public List<AuGrupo> consultarPorTecnologia(int tecnologia_id) throws Exception {
        List<AuGrupo> listaResultados = new ArrayList();
        try {
            StringBuilder strQuery = new StringBuilder();

            strQuery.append(" select  ");
            strQuery.append("  ag.id, ");
            strQuery.append("  ag.nombre, ");
            strQuery.append("  ag.descripcion, ");
            strQuery.append("  ag.orden, ");
            strQuery.append("  ag.activo, ");
            strQuery.append("  ag.solo_grupo, ");
            strQuery.append("  ag.ultimo_usuario_medico_id, ");
            strQuery.append("  ag.ultimo_usuario_enfermero_id, ");
            strQuery.append("  ag.ultimo_usuario_auxiliar_id, ");
            strQuery.append("  ag.ultimo_usuario_odontologo_id, ");
            strQuery.append("  ag.ultimo_usuario_auxiliar_oral_id, ");
            strQuery.append("  ag.mae_ambito_id, ");
            strQuery.append("  ag.mae_ambito_codigo, ");
            strQuery.append("  ag.mae_ambito_valor, ");
            strQuery.append("  ag.tutela, ");
            strQuery.append("  ag.pbs, ");
            strQuery.append("  ag.generico, ");
            strQuery.append("  ag.es_tecnologia, ");
            strQuery.append("  ag.es_insumo, ");
            strQuery.append("  ag.es_medicamento, ");
            strQuery.append("  ag.es_paquete, ");
            strQuery.append("  ag.usuario_crea, ");
            strQuery.append("  ag.terminal_crea, ");
            strQuery.append("  ag.fecha_hora_crea, ");
            strQuery.append("  ag.usuario_modifica, ");
            strQuery.append("  ag.terminal_modifica, ");
            strQuery.append("  ag.fecha_hora_modifica ");
            strQuery.append(" FROM au_grupos ag ");
            strQuery.append(" inner join au_grupo_tecnologias agt on ag.id = agt.au_grupos_id and agt.ma_tecnologia_id = ").append(tecnologia_id);

            javax.persistence.Query nativeQuery = em.createNativeQuery(strQuery.toString());
            List<Object[]> results = nativeQuery.getResultList();
            listaResultados = results
                    .stream()
                    .map(result -> new AuGrupo(((int) result[0])))
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

}
