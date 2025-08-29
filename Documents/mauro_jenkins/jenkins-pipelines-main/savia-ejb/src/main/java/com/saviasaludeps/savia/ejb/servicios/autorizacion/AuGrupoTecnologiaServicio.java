/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuGrupo;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoTecnologia;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuGrupos;
import com.saviasaludeps.savia.ejb.entidades.AuGrupoTecnologias;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuGrupoTecnologiaRemoto;
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
 * @author Stiven Giraldo
 */
@Stateless
@Remote(AuGrupoTecnologiaRemoto.class)
public class AuGrupoTecnologiaServicio extends GenericoServicio implements AuGrupoTecnologiaRemoto {

    @Override
    public AuGrupoTecnologia consultar(int id) throws Exception {
        AuGrupoTecnologia objRes = null;
        try {
            objRes = castEntidadNegocio((AuGrupoTecnologias) getEntityManager().find(AuGrupoTecnologias.class, id));
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
    public int insertar(AuGrupoTecnologia obj) throws Exception {
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
    public void actualizar(AuGrupoTecnologia obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuGrupoTecnologias a SET ";
            strQuery += "a.tipo = :tipo,";
            strQuery += "a.maeTipoAuditorId = :maeTipoAuditorId,";
            strQuery += "a.maeTipoAuditorCodigo = :maeTipoAuditorCodigo,";
            strQuery += "a.maeTipoAuditorValor = :maeTipoAuditorValor,";
            strQuery += "a.aplicaSeguimiento = :aplicaSeguimiento,";
            strQuery += "a.maeSeguimientoServicioId = :maeSeguimientoServicioId,";
            strQuery += "a.maeSeguimientoServicioCodigo = :maeSeguimientoServicioCodigo,";
            strQuery += "a.maeSeguimientoServicioValor = :maeSeguimientoServicioValor,";
            strQuery += "a.usuarioModifica = :usuarioModifica,";
            strQuery += "a.terminalModifica = :terminalModifica,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("tipo", obj.getTipo());
            query.setParameter("maeTipoAuditorId", obj.getMaeTipoAuditorId());
            query.setParameter("maeTipoAuditorCodigo", obj.getMaeTipoAuditorCodigo());
            query.setParameter("maeTipoAuditorValor", obj.getMaeTipoAuditorValor());
            query.setParameter("aplicaSeguimiento", obj.getAplicaSeguimiento());
            query.setParameter("maeSeguimientoServicioId", obj.getMaeSeguimientoServicioId());
            query.setParameter("maeSeguimientoServicioCodigo", obj.getMaeSeguimientoServicioCodigo());
            query.setParameter("maeSeguimientoServicioValor", obj.getMaeSeguimientoServicioValor());
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
    public AuGrupoTecnologia eliminar(int id) throws Exception {
        AuGrupoTecnologia obj = null;
        try {
            AuGrupoTecnologias ent = getEntityManager().find(AuGrupoTecnologias.class, id);
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

    private AuGrupoTecnologia castEntidadNegocio(AuGrupoTecnologias entidad) {
        AuGrupoTecnologia negocio = new AuGrupoTecnologia();
        negocio.setId(entidad.getId());
        negocio.setAuGrupo(new AuGrupo(entidad.getAuGruposId().getId()));
        negocio.setTipoTecnologia(entidad.getTipoTecnologia());
        negocio.setMaTecnologiaId(entidad.getMaTecnologiaId());
        negocio.setMaTecnologiaCodigo(entidad.getMaTecnologiaCodigo());
        negocio.setMaTecnologiasValor(entidad.getMaTecnologiasValor());
        negocio.setMaeMedicamentoId(entidad.getMaeMedicamentoId());
        negocio.setMaeMedicamentoCodigo(entidad.getMaeMedicamentoCodigo());
        negocio.setMaeMedicamentoValor(entidad.getMaeMedicamentoValor());
        negocio.setTipo(entidad.getTipo());
        negocio.setMaeTipoAuditorId(entidad.getMaeTipoAuditorId());
        negocio.setMaeTipoAuditorCodigo(entidad.getMaeTipoAuditorCodigo());
        negocio.setMaeTipoAuditorValor(entidad.getMaeTipoAuditorValor());
        negocio.setMaeSeguimientoServicioId(entidad.getMaeSeguimientoServicioId());
        negocio.setMaeSeguimientoServicioCodigo(entidad.getMaeSeguimientoServicioCodigo());
        negocio.setMaeSeguimientoServicioValor(entidad.getMaeSeguimientoServicioValor());
        negocio.setAplicaSeguimiento(entidad.getAplicaSeguimiento());
        negocio.setNivelComplejidad(entidad.getNivelComplejidad());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        return negocio;
    }

    private AuGrupoTecnologias castNegocioEntidad(AuGrupoTecnologia negocio) {
        AuGrupoTecnologias entidad = new AuGrupoTecnologias();
        entidad.setId(negocio.getId());
        entidad.setAuGruposId(new AuGrupos(negocio.getAuGrupo().getId()));
        entidad.setMaTecnologiaId(negocio.getMaTecnologiaId());
        entidad.setMaTecnologiaCodigo(negocio.getMaTecnologiaCodigo());
        entidad.setMaTecnologiasValor(negocio.getMaTecnologiasValor());
        entidad.setMaeMedicamentoId(negocio.getMaeMedicamentoId());
        entidad.setMaeMedicamentoCodigo(negocio.getMaeMedicamentoCodigo());
        entidad.setMaeMedicamentoValor(negocio.getMaeMedicamentoValor());
        entidad.setTipo(negocio.getTipo());
        entidad.setTipoTecnologia(negocio.getTipoTecnologia());
        entidad.setMaeTipoAuditorId(negocio.getMaeTipoAuditorId());
        entidad.setMaeTipoAuditorCodigo(negocio.getMaeTipoAuditorCodigo());
        entidad.setMaeTipoAuditorValor(negocio.getMaeTipoAuditorValor());
        entidad.setMaeSeguimientoServicioId(negocio.getMaeSeguimientoServicioId());
        entidad.setMaeSeguimientoServicioCodigo(negocio.getMaeSeguimientoServicioCodigo());
        entidad.setMaeSeguimientoServicioValor(negocio.getMaeSeguimientoServicioValor());
        entidad.setAplicaSeguimiento(negocio.getAplicaSeguimiento());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        return entidad;
    }

    @Override
    public List<AuGrupoTecnologia> consultarListaPorIdGrupo(int idGrupo) throws Exception {
        List<AuGrupoTecnologia> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuGrupoTecnologias p "
                    + "WHERE p.id > 0";
            strQuery += " AND p.auGruposId = " + idGrupo + " ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<AuGrupoTecnologias> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuGrupoTecnologias tecnologias : list) {
                listaResultados.add(castEntidadNegocio(tecnologias));
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
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuGrupoTecnologias p ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "WHERE p.auGruposId.id = " + paramConsulta.getParametroConsulta1() + " ";
            } else {
                strQuery += "WHERE p.id > 0 ";
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND p.tipo <> " + paramConsulta.getParametroConsulta2() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "maTecnologiaCodigo":
                            strQuery += "AND p.maTecnologiaCodigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maTecnologiasValor":
                            strQuery += "AND p.maTecnologiasValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "tipoTecnologia":
                            strQuery += "AND p.tipoTecnologia =" + e.getValue() + " ";
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
    public List<AuGrupoTecnologia> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuGrupoTecnologia> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuGrupoTecnologias p ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "WHERE p.auGruposId.id = " + paramConsulta.getParametroConsulta1() + " ";
            } else {
                strQuery += "WHERE p.id > 0 ";
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND p.tipo <> " + paramConsulta.getParametroConsulta2() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "maTecnologiaCodigo":
                            strQuery += "AND p.maTecnologiaCodigo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maTecnologiasValor":
                            strQuery += "AND p.maTecnologiasValor LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "tipoTecnologia":
                            strQuery += "AND p.tipoTecnologia =" + e.getValue() + " ";
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

            List<AuGrupoTecnologias> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuGrupoTecnologias grupoTecnologia : list) {
                listaResultados.add(castEntidadNegocio(grupoTecnologia));
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
    public boolean validarTecnologia(int idTecnologia, int tipoTecnologia, int idGrupo) throws Exception {
        boolean existe = false;
        try {
            String strQuery = "FROM AuGrupoTecnologias p "
                    + "WHERE p.id > 0";
            strQuery += " AND p.auGruposId.id = " + idGrupo + " ";
            strQuery += " AND p.maTecnologiaId = " + idTecnologia + " ";
            strQuery += " AND p.tipoTecnologia = " + tipoTecnologia + " ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<AuGrupoTecnologias> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            existe = list != null && !list.isEmpty();
        } catch (NoResultException e) {
            existe = false;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return existe;
    }

    @Override
    public boolean validarTecnologiaGrupoAprueba(int idTecnologia, int tipoTecnologia, String grupos, String ambito) throws Exception {
        boolean existe = false;
        try {
            String strQuery = "SELECT COUNT(t.id) FROM AuGrupoTecnologias t "
                    + " INNER JOIN t.auGruposId g ";
            strQuery += " WHERE g.id IN( " + grupos + ") ";
            strQuery += " AND g.activo =  1";
            strQuery += " AND g.maeAmbitoCodigo =:ambito";
            strQuery += " AND t.maTecnologiaId=:idTecnologia";
            strQuery += " AND t.tipoTecnologia=:tipoTecnologia ";
            int cantidad = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("ambito", ambito)
                    .setParameter("idTecnologia", idTecnologia)
                    .setParameter("tipoTecnologia", tipoTecnologia)
                    .getSingleResult();
            existe = cantidad > 0;
        } catch (NoResultException e) {
            existe = false;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return existe;
    }

    @Override
    public boolean validarTecnologiaGrupoTipoUsuario(int idTecnologia, int tipoTecnologia, int idGrupo, int idUsuario) throws Exception {
        boolean existe = false;
        try {
            String strQuery = "SELECT COUNT(t.id) FROM AuGrupoTecnologias t  "
                    + " INNER JOIN t.auGruposId g INNER JOIN g.auGrupoUsuariosList gu INNER JOIN gu.gnUsuariosId u ";
            strQuery += " WHERE g.id =:idGrupo ";
            strQuery += " AND t.maTecnologiaId=:idTecnologia";
            strQuery += " AND t.tipoTecnologia=:tipoTecnologia ";
            strQuery += " AND u.id =:idUsuario ";
            strQuery += " AND gu.activo = 1 ";
            strQuery += " AND gu.maeTipoAuditorId = t.maeTipoAuditorId ";
            int cantidad = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("idGrupo", idGrupo)
                    .setParameter("idTecnologia", idTecnologia)
                    .setParameter("tipoTecnologia", tipoTecnologia)
                    .setParameter("idUsuario", idUsuario)
                    .getSingleResult();
            existe = cantidad > 0;
        } catch (NoResultException e) {
            existe = false;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return existe;
    }

    @Override
    public boolean validarAuditor(int idTecnologia, String tipoAuditor) throws Exception {
        boolean existe = false;
        try {
            String strQuery = "FROM AuGrupoTecnologias p "
                    + "WHERE p.id > 0";
            strQuery += " AND p.maeTipoAuditorCodigo >= " + tipoAuditor + " ";
            strQuery += " AND p.maTecnologiaId = " + idTecnologia + " ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<AuGrupoTecnologias> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            existe = list != null && !list.isEmpty();
        } catch (NoResultException e) {
            existe = false;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return existe;
    }

    @Override
    public boolean validarAplicaSeguimiento(int idTecnologia, int tipoTecnologia, int idGrupo) throws Exception {
        boolean existe = false;
        try {
            String strQuery = "SELECT COUNT(p.id) FROM AuGrupoTecnologias AS p INNER JOIN p.auGruposId g "
                    + " WHERE  p.maTecnologiaId=:idTecnologia "
                    + "AND p.tipoTecnologia=:tipoTecnologia "
                    + "AND p.aplicaSeguimiento=1 "
                    + "AND g.aplicaSeguimiento=1 "
                    + "AND g.aplicaSeguimiento=1 "
                    + "AND g.id <>:idGrupo  "
                    + "AND g.activo=1";
            int res = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("idTecnologia", idTecnologia)
                    .setParameter("tipoTecnologia", tipoTecnologia)
                    .setParameter("idGrupo", idGrupo)
                    .getSingleResult();
            existe = res > 0;
        } catch (NoResultException e) {
            existe = false;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return existe;
    }

    @Override
    public AuGrupoTecnologia tecnologiaAplicaSeguimiento(int idTecnologia, int tipoTecnologia) throws Exception {
        AuGrupoTecnologia tecnologia = null;
        try {
            String strQuery = "SELECT p FROM AuGrupoTecnologias AS p INNER JOIN p.auGruposId g "
                    + " WHERE  p.maTecnologiaId=:idTecnologia "
                    + "AND p.tipoTecnologia=:tipoTecnologia "
                    + "AND p.aplicaSeguimiento=1 "
                    + "AND g.aplicaSeguimiento=1";
            AuGrupoTecnologias tecnologias = (AuGrupoTecnologias) getEntityManager().createQuery(strQuery)
                    .setParameter("idTecnologia", idTecnologia)
                    .setParameter("tipoTecnologia", tipoTecnologia)
                    .setMaxResults(1)
                    .getSingleResult();
            tecnologia = castEntidadNegocio(tecnologias);
        } catch (NoResultException e) {
            tecnologia = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return tecnologia;
    }
}
