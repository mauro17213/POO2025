package com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupo;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupoUsuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.ejb.entidades.CmGrupoUsuarios;
import com.saviasaludeps.savia.ejb.entidades.CmGrupos;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmGrupoUsuarioRemoto;
import java.util.Map;
import java.util.Random;
import javax.persistence.Query;

@Stateless
@Remote(CmGrupoUsuarioRemoto.class)
public class CmGrupoUsuarioServicio extends GenericoServicio implements CmGrupoUsuarioRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(u.id) FROM CmGrupoUsuarios u "
                    + "WHERE u.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "gnUsuario.nombre":
                            strQuery += " AND u.gnUsuariosId.nombre = '" + (String) e.getValue() + "' ";
                            break;
                        case "tipo":
                            strQuery += " AND u.tipo = " + e.getValue() + " ";
                            break;
                        case "idGrupo":
                            strQuery += " AND  u.cmGruposId.id = " + e.getValue() + " ";
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
    public List<CmGrupoUsuario> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmGrupoUsuario> listResult = new ArrayList();
        try {
            String strQuery = "SELECT u FROM CmGrupoUsuarios u "
                    + "WHERE u.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                         case "gnUsuario.nombre":
                            strQuery += " AND u.gnUsuariosId.nombre = '" + (String) e.getValue() + "' ";
                            break;
                        case "tipo":
                            strQuery += " AND u.tipo = " + e.getValue() + " ";
                            break;
                        case "idGrupo":
                            strQuery += " AND  u.cmGruposId.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                String parametro = paramConsulta.getOrden().replace("gnUsuario.nombre","gnUsuariosId.nombre");
                strQuery += "u." + parametro + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "u.id DESC";
            }
            List<CmGrupoUsuarios> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CmGrupoUsuarios per : list) {
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
    public List<CmGrupoUsuario> consultarListaParametrizada(ParamConsulta paramConsulta) throws Exception {
        List<CmGrupoUsuario> listResult = new ArrayList();
        try {
            String strQuery = "SELECT u FROM CmGrupoUsuarios u "
                    + "WHERE u.id > 0 ";
            
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND u.activo = :activo ";
            }
            
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += " AND  u.cmGruposId.id = :idGrupo ";
            }
            
            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += " AND  u.tipo = :tipo ";
            }
            
            strQuery += " ORDER BY u.id DESC";

            Query query = getEntityManager().createQuery(strQuery);

            if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("activo", paramConsulta.getParametroConsulta1());
            }

            if (paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("idGrupo", paramConsulta.getParametroConsulta2());
            }
            
            if (paramConsulta.getParametroConsulta3() != null) {
                query.setParameter("tipo", paramConsulta.getParametroConsulta3());
            }
         
         
            List<CmGrupoUsuarios> list = query. getResultList();
            for (CmGrupoUsuarios per : list) {
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
    public List<CmGrupoUsuario> consultarLista(int id) throws Exception {
        List<CmGrupoUsuario> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "SELECT u FROM CmGrupoUsuarios u "
                    + "WHERE u.cmGruposId.id = :id_grupo ";
            strQuery += "ORDER BY u.id DESC ";
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("id_grupo", id);
            List<CmGrupoUsuarios> list = query.getResultList();
            for (CmGrupoUsuarios per : list) {
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
    public CmGrupoUsuario consultar(int idGrupo, int idUsuario, int idTipo ) throws Exception {
        CmGrupoUsuario usuario = new CmGrupoUsuario();
        try {
            String strQuery = "SELECT u FROM CmGrupoUsuarios u "
                    + " WHERE u.cmGruposId.id = :id_grupo AND "
                    + " u.tipo = :id_tipo AND u.gnUsuariosId.id = :id_usuario";
            strQuery += " ORDER BY u.id DESC ";
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("id_grupo", idGrupo);
            query.setParameter("id_tipo", idTipo);
            query.setParameter("id_usuario", idUsuario);
            List<CmGrupoUsuarios> list = query.getResultList();
            for (CmGrupoUsuarios per : list) {
                usuario = (castEntidadNegocio(per));
            }
        } catch (NoResultException e) {
            usuario = new CmGrupoUsuario();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return usuario;
    }

    @Override
    public CmGrupoUsuario consultar(int id) throws Exception {
        CmGrupoUsuario objRes = null;
        try {
            objRes = castEntidadNegocio((CmGrupoUsuarios) getEntityManager().find(CmGrupoUsuarios.class, id));
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
    public int insertar(CmGrupoUsuario obj) throws Exception {
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
    public void actualizar(CmGrupoUsuario obj) throws Exception {
        try {
            String hql = "UPDATE CmGrupoUsuarios SET "
                    + "tipo = :tipo, "
                    + "activo = :activo, "
                    + "cmGruposId.id = :cm_grupos_id, "
                    + "gnUsuariosId.id = :gn_usuarios_id, "
                    + "usuarioCrea = :usuario_crea, "
                    + "terminalCrea = :terminal_crea, "
                    + "fechaHoraCrea = :fecha_hora_crea, "
                    + "usuarioModifica = :usuario_modifica, "
                    + "terminalModifica = :terminal_modifica, "
                    + "fechaHoraModifica = :fecha_hora_modifica "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("tipo", obj.getTipo());
            query.setParameter("activo", obj.isActivo());
            query.setParameter("cm_grupos_id", obj.getCmGrupo().getId());
            query.setParameter("gn_usuarios_id", obj.getGnUsuario().getId());
            query.setParameter("usuario_crea", obj.getUsuarioCrea());
            query.setParameter("terminal_crea", obj.getTerminalCrea());
            query.setParameter("fecha_hora_crea", obj.getFechaHoraCrea());
            query.setParameter("usuario_modifica", obj.getUsuarioModifica());
            query.setParameter("terminal_modifica", obj.getTerminalModifica());
            query.setParameter("fecha_hora_modifica", obj.getFechaHoraModifica());
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
    public CmGrupoUsuario eliminar(int id) throws Exception {
        CmGrupoUsuario obj = null;
        try {
            CmGrupoUsuarios ent = getEntityManager().find(CmGrupoUsuarios.class, id);
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
    public CmGrupoUsuario consultarPorTipoYNit(int idTipo, String nit) throws java.lang.Exception {
        CmGrupoUsuario obj = new CmGrupoUsuario();
        int i = 0;
        try {
            String hql = "SELECT count(u.id) FROM CmGrupoUsuarios u ";
            hql += "INNER JOIN u.cmGruposId g ";
            hql += "INNER JOIN CmGrupoPrestadores p ON p.cmGruposId = g ";
            hql += "WHERE u.tipo = :id_tipo ";
            hql += "AND p.cntPrestadoresId.numeroDocumento = :nit";
            Query countUsuarios = getEntityManager().createQuery(hql)
                    .setParameter("id_tipo", idTipo)
                    .setParameter("nit", nit);
            int count = (int) (long) countUsuarios.getSingleResult();
            if (count > 0) {
                Random random = new Random();
                int filaAleatoria = random.nextInt(count - 1 + 1) + 1;
                hql = "SELECT u FROM CmGrupoUsuarios u ";
                hql += "INNER JOIN u.cmGruposId g ";
                hql += "INNER JOIN CmGrupoPrestadores p ON p.cmGruposId = g ";
                hql += "WHERE u.tipo = :id_tipo ";
                hql += "AND p.cntPrestadoresId.numeroDocumento = :nit";
                Query query = getEntityManager().createQuery(hql)
                        .setParameter("id_tipo", idTipo)
                        .setParameter("nit", nit)
                        .setMaxResults(1)
                        .setFirstResult(filaAleatoria - 1);
                Object cmGrupoUsuarios = query.getSingleResult();
                obj = castEntidadNegocio((CmGrupoUsuarios) cmGrupoUsuarios);
            }
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public CmGrupoUsuario consultarUsuarioLider(int idTipo, String nit, boolean camaFija, boolean pbs) throws Exception {
        CmGrupoUsuario obj = null;
        int i = 0;
        try {
            String hql = "SELECT count(u.id) FROM CmGrupoUsuarios u ";
            hql += "INNER JOIN u.cmGruposId g ";
            hql += "INNER JOIN CmGrupoPrestadores p ON p.cmGruposId = g ";
            hql += "WHERE u.tipo = :id_tipo ";
            hql += "AND p.cntPrestadoresId.numeroDocumento = :nit ";
            hql += "AND g.pbs = :pbs ";
            hql += "AND g.camaFija = :cama_fija ";
            Query countUsuarios = getEntityManager().createQuery(hql)
                    .setParameter("id_tipo", idTipo)
                    .setParameter("nit", nit)
                    .setParameter("cama_fija", camaFija)
                    .setParameter("pbs", pbs);
            int count = (int) (long) countUsuarios.getSingleResult();
            if (count > 0) {
                Random random = new Random();
                int filaAleatoria = random.nextInt(count - 1 + 1) + 1;
                hql = "SELECT u FROM CmGrupoUsuarios u ";
                hql += "INNER JOIN u.cmGruposId g ";
                hql += "INNER JOIN CmGrupoPrestadores p ON p.cmGruposId = g ";
                hql += "WHERE u.tipo = :id_tipo ";
                hql += "AND p.cntPrestadoresId.numeroDocumento = :nit ";
                hql += "AND g.pbs = :pbs ";
                hql += "AND g.camaFija = :cama_fija ";
                Query query = getEntityManager().createQuery(hql)
                        .setParameter("id_tipo", idTipo)
                        .setParameter("nit", nit)
                        .setParameter("cama_fija", camaFija)
                        .setParameter("pbs", pbs)
                        .setMaxResults(1)
                        .setFirstResult(filaAleatoria - 1);
                Object cmGrupoUsuarios = query.getSingleResult();
                obj = castEntidadNegocio((CmGrupoUsuarios) cmGrupoUsuarios);
            }
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public List<CmGrupoUsuario> consultarPorUsuarioLista(int idUsuario) throws Exception {
        List<CmGrupoUsuario> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "SELECT u FROM CmGrupoUsuarios u "
                    + "WHERE u.gnUsuariosId.id = :gnUsuariosId AND u.activo = 1  ";
            strQuery += "ORDER BY u.id DESC ";
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("gnUsuariosId", idUsuario);
            List<CmGrupoUsuarios> list = query.getResultList();
            for (CmGrupoUsuarios per : list) {
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
    public List<CmGrupoUsuario> consultarPorAtributosLista(ParamConsulta paramConsulta) throws Exception {
        List<CmGrupoUsuario> listResult = new ArrayList();
        try {
            String strQuery = "SELECT ug FROM CmGrupoUsuarios ug "
                    + "WHERE ug.activo = 1  ";
            
            if (paramConsulta.getParametroConsulta1() != null) {
               strQuery += " AND ug.gnUsuariosId.gnEmpresasId.id = :empresaid ";
            }
            if (paramConsulta.getParametroConsulta2() != null) {
               strQuery += " AND ug.tipo = :idTipo ";
            }
            if (paramConsulta.getParametroConsulta3() != null) {
               strQuery += " AND ug.cmGruposId.id = :idGrupo ";
            }
            
            strQuery += "ORDER BY ug.id DESC ";
            Query query = getEntityManager().createQuery(strQuery);
            
            if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("empresaid", paramConsulta.getParametroConsulta1());
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("idTipo", paramConsulta.getParametroConsulta2());
            }
            if (paramConsulta.getParametroConsulta3() != null) {
                query.setParameter("idGrupo", paramConsulta.getParametroConsulta3());
            }

            List<CmGrupoUsuarios> list = query.getResultList();
            for (CmGrupoUsuarios per : list) {
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


    public static CmGrupoUsuario castEntidadNegocio(CmGrupoUsuarios per) {
        CmGrupoUsuario neg = new CmGrupoUsuario();
        neg.setId(per.getId());
        neg.setTipo(per.getTipo());
        neg.setActivo(per.getActivo());
        if (per.getCmGruposId().getId() != null) {
            neg.setCmGrupo(new CmGrupo( per.getCmGruposId().getId()) );
            neg.getCmGrupo().setTipoGrupo( per.getCmGruposId().getTipoGrupo() );
            neg.getCmGrupo().setCategoria( per.getCmGruposId().getCategoria() );
        }
       
        neg.setGnUsuario(new Usuario(
                per.getGnUsuariosId().getId(),
                per.getGnUsuariosId().getUsuario(),
                per.getGnUsuariosId().getNombre()
        ));
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        if (per.getUsuarioModifica() != null) {
            neg.setUsuarioModifica(per.getUsuarioModifica());
        }
        if (per.getTerminalModifica() != null) {
            neg.setTerminalModifica(per.getTerminalModifica());
        }
        if (per.getFechaHoraModifica() != null) {
            neg.setFechaHoraModifica(per.getFechaHoraModifica());
        }
        return neg;
    }

    public static CmGrupoUsuarios castNegocioEntidad(CmGrupoUsuario obj) {
        CmGrupoUsuarios ent = new CmGrupoUsuarios();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setTipo(obj.getTipo());
        ent.setActivo(obj.isActivo());
        ent.setCmGruposId(new CmGrupos(
                obj.getCmGrupo().getId()
        ));
        ent.setGnUsuariosId(new GnUsuarios(
                obj.getGnUsuario().getId()
        ));
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        if (obj.getUsuarioModifica() != null) {
            ent.setUsuarioModifica(obj.getUsuarioModifica());
        }
        if (obj.getTerminalModifica() != null) {
            ent.setTerminalModifica(obj.getTerminalModifica());
        }
        if (obj.getFechaHoraModifica() != null) {
            ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        }
        return ent;
    }

    @Override
    public List<Usuario> consultarListaByNitTipoUsuario(int empresaId, int idTipo, String usuario, String idGrupos) throws Exception {
        List<Usuario> listResult = new ArrayList();
        int i = 0;
        try {

            String strQuery = "SELECT u FROM GnUsuarios u ";
            strQuery += "INNER JOIN CmGrupoUsuarios gu ON gu.gnUsuariosId = u.id ";
            strQuery += "WHERE u.id > 0 ";

            if (!"".equals(usuario)) {
                strQuery += " AND u.nombre LIKE '%" + usuario + "%' ";
            }

            if (idTipo > 0) {
                strQuery += " AND gu.tipo = '" + idTipo + "' ";
            }

            if (!"".equals(idGrupos)) {
                strQuery += " AND gu.cmGruposId.id IN(" + idGrupos + ") ";
            }

            strQuery += " AND u.gnEmpresasId.id = '" + empresaId + "' ";
            strQuery += " AND gu.activo = 1 ";

            Query query = getEntityManager().createQuery(strQuery);

            List<GnUsuarios> list = query.getResultList();
            for (GnUsuarios per : list) {
                listResult.add(castUsuarioEntidadNegocio(per));
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

    public static Usuario castUsuarioEntidadNegocio(GnUsuarios per) {
        Usuario obj = new Usuario(
                per.getId(),
                per.getUsuario(),
                per.getNombre());

        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        if (per.getUsuarioModifica() != null) {
            obj.setUsuarioModifica(per.getUsuarioModifica());
        }
        if (per.getTerminalModifica() != null) {
            obj.setTerminalModifica(per.getTerminalModifica());
        }
        if (per.getFechaHoraModifica() != null) {
            obj.setFechaHoraModifica(per.getFechaHoraModifica());
        }
        return obj;
    }

}
