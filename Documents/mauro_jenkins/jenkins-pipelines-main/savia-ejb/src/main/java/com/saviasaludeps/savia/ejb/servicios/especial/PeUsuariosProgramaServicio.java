/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.especial.PeUsuariosPrograma;
import com.saviasaludeps.savia.ejb.entidades.PeProgramas;
import com.saviasaludeps.savia.ejb.entidades.PeUsuariosProgramas;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.especial.PeUsuariosProgramaRemoto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@Remote(PeUsuariosProgramaRemoto.class)
@Local(PeUsuariosProgramaLocal.class)
public class PeUsuariosProgramaServicio extends GenericoServicio implements PeUsuariosProgramaLocal, PeUsuariosProgramaRemoto {

    @Override
    public List<PeUsuariosPrograma> consultarPorPrograma(int idPrograma) throws Exception {
        List<PeUsuariosPrograma> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p FROM PeUsuariosProgramas p "
                    + "WHERE p.peProgramasId.id = :idPrograma "
                    + "ORDER BY p.id ";

            List<PeUsuariosProgramas> list = getEntityManager().createQuery(strQuery)
                    .setParameter("idPrograma", idPrograma)
                    .getResultList();
            for (PeUsuariosProgramas per : list) {
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
    public List<PeUsuariosPrograma> consultarPorProgramaActivo(int idPrograma) throws Exception {
        List<PeUsuariosPrograma> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p FROM PeUsuariosProgramas p "
                    + "WHERE p.peProgramasId.id = :idPrograma "
                    + "AND p.activo = :activo "
                    + "AND p.fechaInicio <= :fecha "
                    + "AND P.fechaFin >= :fecha "
                    + "ORDER BY p.id ";

            List<PeUsuariosProgramas> list = getEntityManager().createQuery(strQuery)
                    .setParameter("idPrograma", idPrograma)
                    .setParameter("activo", true)
                    .setParameter("fecha", new Date())
                    .getResultList();
            for (PeUsuariosProgramas per : list) {
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
    public int insertar(PeUsuariosPrograma obj) throws Exception {
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
    public void actualizar(PeUsuariosPrograma obj) throws Exception {
        try {
            String sql = "UPDATE PeUsuariosProgramas SET tipo = :tipo, "
                    + "activo = :activo, "
                    + "fechaInicio = :fechaInicio, "
                    + "fechaFin = :fechaFin, "
                    + "usuarioCrea = :usuarioCrea, "
                    + "terminalCrea = :terminalCrea, "
                    + "fechaHoraCrea = :fechaHoraCrea, "
                    + "usuarioModifica = :usuarioModifica,"
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica, "
                    + "peProgramasId.id = :peProgramasId, "
                    + "gnUsuariosId.id = :gnUsuariosId "
                    + "WHERE id = :id";

            Query query = getEntityManager().createQuery(sql);
            query.setParameter("tipo", obj.getTipo());
            query.setParameter("activo", obj.isActivo());
            query.setParameter("fechaInicio", obj.getFechaInicio());
            query.setParameter("fechaFin", obj.getFechaFin());
            query.setParameter("usuarioCrea", obj.getUsuarioCrea());
            query.setParameter("terminalCrea", obj.getTerminalCrea());
            query.setParameter("fechaHoraCrea", obj.getFechaHoraCrea());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("peProgramasId", obj.getPeProgramasId().getId());
            query.setParameter("gnUsuariosId", obj.getUsuariosId().getId());
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
    public PeUsuariosPrograma eliminar(int id) throws Exception {
        PeUsuariosPrograma obj = null;
        try {
            PeUsuariosProgramas ent = getEntityManager().find(PeUsuariosProgramas.class, id);
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

    @Override
    public List<PeUsuariosPrograma> consultarTodos() throws Exception {
        List<PeUsuariosPrograma> listResult = new ArrayList();
        try {
            String strQuery = "FROM PeUsuariosProgramas p "
                    + "WHERE p.activo = :activo "
                    + "ORDER BY p.gnUsuariosId.nombre DESC";
            List<PeUsuariosProgramas> list = getEntityManager().createQuery(strQuery)
                    .setParameter("activo", true)
                    .getResultList();
            for (PeUsuariosProgramas per : list) {
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

    /**
     * Se valida si el usaurio esta asignado como usuario responsable del
     * programa
     *
     * @param idUsuario
     * @param idPrograma
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public boolean isUsuarioResponsable(Integer idUsuario, Integer idPrograma) throws Exception {
        boolean responsable = false;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT p FROM PeUsuariosProgramas p ");
            strQuery.append(" INNER JOIN GnUsuarios gu on p.gnUsuariosId = gu.id AND gu.id = :idUsuario ");
            strQuery.append(" WHERE p.activo = :activo AND p.peProgramasId.id = :idPrograma ");

            PeUsuariosProgramas users = (PeUsuariosProgramas) getEntityManager().createQuery(strQuery.toString())
                    .setParameter("idUsuario", idUsuario)
                    .setParameter("activo", true)
                    .setParameter("idPrograma", idPrograma)
                    .getSingleResult();
            if (users != null) {
                responsable = true;
            }
        } catch (NoResultException e) {
            responsable = false;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return responsable;
    }

    /**
     * Metodo para cambiar el estado de un usuario en todos los programas en los
     * que está como responsable
     *
     * @param obj
     * @throws Exception
     */
    @Override
    public void cambiarEstadoUsuarioProgramas(PeUsuariosPrograma obj) throws Exception {
        try {
            String sql = "UPDATE PeUsuariosProgramas SET "
                    + "activo = :activo, "
                    + "usuarioModifica = :usuarioModifica,"
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica, "
                    + "WHERE gnUsuariosId.id = :gnUsuariosId ";

            Query query = getEntityManager().createQuery(sql);
            query.setParameter("activo", obj.isActivo());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("gnUsuariosId", obj.getUsuariosId().getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    /**
     * Metodo para cambiar el estado de un usuario en todos los programas en los
     * que está como responsable
     *
     * @param usuario
     * @throws Exception
     */
    @Override
    public void cambiarEstadoUsuario(Usuario usuario) throws Exception {
        try {
            String sql = "UPDATE PeUsuariosProgramas SET "
                    + "activo = 0 , "
                    + "usuarioModifica = :usuarioModifica,"
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica, "
                    + "WHERE gnUsuariosId.id = :gnUsuariosId ";

            Query query = getEntityManager().createQuery(sql);
            query.setParameter("usuarioModifica", usuario.getUsuarioModifica());
            query.setParameter("terminalModifica", usuario.getTerminalModifica());
            query.setParameter("fechaHoraModifica", usuario.getFechaHoraModifica());
            query.setParameter("gnUsuariosId", usuario.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }


    public static PeUsuariosPrograma castEntidadNegocio(PeUsuariosProgramas ent) {
        PeUsuariosPrograma obj = new PeUsuariosPrograma();

        obj.setActivo(ent.getActivo());
        obj.setFechaFin(ent.getFechaFin());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setFechaInicio(ent.getFechaInicio());
        obj.setId(ent.getId());
        if (ent.getPeProgramasId() != null) {
            obj.setPeProgramasId(new PePrograma(ent.getPeProgramasId().getId()));
        }
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setTipo(ent.getTipo());
        obj.setMaeTipoId(ent.getMaeTipoId());
        obj.setMaeTipoCodigo(ent.getMaeTipoCodigo());
        obj.setMaeTipoValor(ent.getMaeTipoValor());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        if (ent.getGnUsuariosId() != null) {
            Usuario user = new Usuario(ent.getGnUsuariosId().getId());
            user.setNombre(ent.getGnUsuariosId().getNombre());
            user.setUsuario(ent.getGnUsuariosId().getUsuario());
            user.setCorreoElectronico(ent.getGnUsuariosId().getCorreoElectronico());
            obj.setUsuariosId(user);
        }

        return obj;
    }

    public static PeUsuariosProgramas castNegocioEntidad(PeUsuariosPrograma obj) {
        PeUsuariosProgramas ent = new PeUsuariosProgramas();

        ent.setActivo(obj.isActivo());
        ent.setFechaFin(obj.getFechaFin());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        ent.setFechaInicio(obj.getFechaInicio());
        ent.setId(obj.getId());
        if (obj.getPeProgramasId() != null) {
            ent.setPeProgramasId(new PeProgramas(obj.getPeProgramasId().getId()));
        }
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setTipo(obj.getTipo());
        ent.setMaeTipoId(obj.getMaeTipoId());
        ent.setMaeTipoCodigo(obj.getMaeTipoCodigo());
        ent.setMaeTipoValor(obj.getMaeTipoValor());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        if (obj.getUsuariosId() != null) {
            ent.setGnUsuariosId(new GnUsuarios(obj.getUsuariosId().getId()));
        }

        return ent;
    }
}
