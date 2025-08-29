
package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjComite;
import com.saviasaludeps.savia.dominio.juridico.CntjComiteAsistente;
import com.saviasaludeps.savia.ejb.entidades.CntjComiteAsistentes;
import com.saviasaludeps.savia.ejb.entidades.CntjComites;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CtnjComiteAsistenteRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author idbohorquez
 */

@Stateless
@Remote(CtnjComiteAsistenteRemoto.class)
public class CntjComiteAsistenteServicio extends GenericoServicio implements CtnjComiteAsistenteRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjComiteAsistentes c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery.toString())
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
    public List<CntjComiteAsistente> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntjComiteAsistente> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjComiteAsistentes c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        
                    }
                }
            }
            strQuery.append(" ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                strQuery.append(" c." + paramConsulta.getOrden() + " " + (paramConsulta.isAscendente() ? "ASC" : "DESC"));
            } else {
                strQuery.append(" c.id DESC ");
            }
            List<CntjComiteAsistentes> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjComiteAsistentes asistente  : list) {
                listResult.add(castEntidadNegocio(asistente));
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
    public int insertar(CntjComiteAsistente objeto) throws java.lang.Exception {
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
    public CntjComiteAsistente consultar(int id) throws java.lang.Exception {
        CntjComiteAsistente objRes = null;
        try {
            objRes = castEntidadNegocio((CntjComiteAsistentes) getEntityManager().find(CntjComiteAsistentes.class, id));
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
    public void actualizar(CntjComiteAsistente objeto) throws java.lang.Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CntjComiteAsistentes SET cntjComitesId.id = :comitesId, ");
            sql.append("gnUsuariosId.id = :usuariosId,  ");
            sql.append("aprueba = :aprueba,  ");
            sql.append("observaciones = :observaciones,  ");
            
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("comitesId", objeto.getCntjComiteId().getId());
            query.setParameter("usuariosId", objeto.getUsuarioId().getId());
            query.setParameter("aprueba", objeto.isAprueba());
            query.setParameter("observaciones", objeto.getObservacion());                        
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
    public CntjComiteAsistente eliminar(int id) throws Exception {
        CntjComiteAsistente obj = null;
        try {
            CntjComiteAsistentes ent = getEntityManager().find(CntjComiteAsistentes.class, id);
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
    public List<CntjComiteAsistente> asistentesComite(int idcomite) throws java.lang.Exception {
        List<CntjComiteAsistente> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjComiteAsistentes c WHERE c.id > 0 and c.cntjComitesId.id = :idcomite "); 
            strQuery.append(" ORDER BY c.id DESC");
            
            List<CntjComiteAsistentes> list = getEntityManager().createQuery(strQuery.toString())
                    .setParameter("idcomite", idcomite)
                    .getResultList();
            for (CntjComiteAsistentes asistente  : list) {
                listResult.add(castEntidadNegocio(asistente));
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
    
    
    private CntjComiteAsistente castEntidadNegocio(CntjComiteAsistentes entidad) {
        CntjComiteAsistente objeto = new CntjComiteAsistente();
        objeto.setId(entidad.getId());
        objeto.setCntjComiteId(new CntjComite(entidad.getCntjComitesId().getId()));
        if(entidad.getGnUsuariosId() != null){
            Usuario usuario = new Usuario(entidad.getGnUsuariosId().getId());
            usuario.setMaeTipoDocumentoId(entidad.getGnUsuariosId().getMaeTipoDocumentoId());
            usuario.setMaeTipoDocumentoCodigo(entidad.getGnUsuariosId().getMaeTipoDocumentoCodigo());
            usuario.setMaeTipoDocumentoValor(entidad.getGnUsuariosId().getMaeTipoDocumentoValor());
            usuario.setDocumento(entidad.getGnUsuariosId().getDocumento());
            usuario.setNombre(entidad.getGnUsuariosId().getNombre());
            usuario.setMaeCargoValor(entidad.getGnUsuariosId().getMaeCargoValor());
            usuario.setMaeAreaValor(entidad.getGnUsuariosId().getMaeAreaValor());            
            objeto.setUsuarioId(usuario);
        }        
        objeto.setAprueba(entidad.getAprueba());
        objeto.setObservacion(entidad.getObservaciones());        
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        objeto.setUsuarioModifica(entidad.getUsuarioModifica());
        objeto.setFechaHoraModifica(entidad.getFechaHoraModifica());
        objeto.setTerminalModifica(entidad.getTerminalModifica());
        return objeto;
    }
    
    private CntjComiteAsistentes castNegocioEntidad(CntjComiteAsistente obj){
        CntjComiteAsistentes ent = new CntjComiteAsistentes();
        ent.setCntjComitesId(new CntjComites(obj.getCntjComiteId().getId()));
        ent.setGnUsuariosId(new GnUsuarios(obj.getUsuarioId().getId()));
        ent.setAprueba(obj.isAprueba());
        ent.setObservaciones(obj.getObservacion());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }

    
}
