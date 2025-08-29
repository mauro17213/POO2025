
package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.juridico.CntjGrupo;
import com.saviasaludeps.savia.dominio.juridico.CntjUsuarioGrupo;
import com.saviasaludeps.savia.ejb.entidades.CntjGrupos;
import com.saviasaludeps.savia.ejb.entidades.CntjUsuarioGrupos;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CtnjUsuarioGrupoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author idbohorquez
 */

@Stateless
@Remote(CtnjUsuarioGrupoRemoto.class)
public class CntjUsuarioGrupoServicio extends GenericoServicio implements CtnjUsuarioGrupoRemoto {

   
    @Override
    public int insertar(CntjUsuarioGrupo objeto) throws java.lang.Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(objeto)).getId();
            objeto.setId(id);
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
    public CntjUsuarioGrupo consultar(int idusuariogrupo) throws java.lang.Exception {
        CntjUsuarioGrupo objRes = null;
        try {
            objRes = castEntidadNegocio((CntjUsuarioGrupos) getEntityManager().find(CntjUsuarioGrupos.class, idusuariogrupo));
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
    public void actualizar(CntjUsuarioGrupo objeto) throws java.lang.Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CntjGrupos SET cntjGruposId.id = :grupoId, ");
            sql.append("gnUsuariosId.id = :usuarioId,  ");
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("grupoId", objeto.getCntjGruposId().getId());
            query.setParameter("usuarioId", objeto.getGnUsuarioId().getId());
            query.setParameter("usuarioModifica", objeto.getUsuarioModifica());
            query.setParameter("terminalModifica", objeto.getTerminalModifica());
            query.setParameter("fechaHoraModifica", objeto.getFechaHoraModifica());            
            query.setParameter("id", objeto.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public CntjUsuarioGrupo eliminar(int id) throws Exception {
        CntjUsuarioGrupo obj = null;
        try {
            CntjUsuarioGrupos ent = getEntityManager().find(CntjUsuarioGrupos.class, id);
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
    public List<CntjUsuarioGrupo> listaUsuariosGrupo(int idgrupo) throws java.lang.Exception {
        List<CntjUsuarioGrupo> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjUsuarioGrupos c WHERE c.id > 0 and c.cntjGruposId.id = ").append(idgrupo);
            List<CntjUsuarioGrupos> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (CntjUsuarioGrupos usuarioGrupo : list) {
                listResult.add(castEntidadNegocio(usuarioGrupo));
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
    public List<CntjUsuarioGrupo> listarUsuariosGrupoPermisos(int idestado) throws java.lang.Exception {
        List<CntjUsuarioGrupo> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjUsuarioGrupos c  ");
            strQuery.append(" INNER JOIN CntjEstadoGrupos eg ON c.cntjGruposId.id = eg.cntjGruposId.id AND eg.cntjEstadosId = ").append(idestado);
            strQuery.append(" WHERE c.id > 0");
            List<CntjUsuarioGrupos> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (CntjUsuarioGrupos usuarioGrupo : list) {
                listResult.add(castEntidadNegocio(usuarioGrupo));
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
    public CntjUsuarioGrupo consultarGrupoUsuario(int idusuario) throws java.lang.Exception {
        CntjUsuarioGrupo resultado = null;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjUsuarioGrupos c  ");
            strQuery.append(" WHERE c.id > 0 AND c.gnUsuariosId.id = :idusuario ");
            CntjUsuarioGrupos usuarioGrupo = (CntjUsuarioGrupos) getEntityManager().createQuery(strQuery.toString())
                    .setParameter("idusuario", idusuario)
                    .getSingleResult();
            resultado = castEntidadNegocio(usuarioGrupo);
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return resultado;
    }
    
    private CntjUsuarioGrupo castEntidadNegocio(CntjUsuarioGrupos entidad) {
        CntjUsuarioGrupo objeto = new CntjUsuarioGrupo();
        objeto.setId(entidad.getId());
        objeto.setCntjGruposId(new CntjGrupo(entidad.getCntjGruposId().getId()));
        Usuario user = new Usuario(entidad.getGnUsuariosId().getId());
        user.setUsuario(entidad.getGnUsuariosId().getUsuario());
        user.setNombre(entidad.getGnUsuariosId().getNombre());
        user.setCorreoElectronico(entidad.getGnUsuariosId().getCorreoElectronico());
        user.setActivo(entidad.getGnUsuariosId().getActivo());
        objeto.setGnUsuarioId(user);
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        objeto.setUsuarioModifica(entidad.getUsuarioModifica());
        objeto.setFechaHoraModifica(entidad.getFechaHoraModifica());
        objeto.setTerminalModifica(entidad.getTerminalModifica());
        return objeto;
    }
    
    private CntjUsuarioGrupos castNegocioEntidad(CntjUsuarioGrupo obj){
        CntjUsuarioGrupos ent = new CntjUsuarioGrupos();
        ent.setCntjGruposId(new CntjGrupos(obj.getCntjGruposId().getId()));
        ent.setGnUsuariosId(new GnUsuarios(obj.getGnUsuarioId().getId()));
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }

    
}
