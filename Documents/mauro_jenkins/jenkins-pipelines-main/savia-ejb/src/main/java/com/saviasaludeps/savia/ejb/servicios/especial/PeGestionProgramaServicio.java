
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.especial.PeGestion;
import com.saviasaludeps.savia.ejb.entidades.PeAfiliadosProgramas;
import com.saviasaludeps.savia.ejb.entidades.PeGestiones;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.especial.PeGestionProgramaRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(PeGestionProgramaRemoto.class)
@Local(PeGestionProgramaLocal.class)
public class PeGestionProgramaServicio extends GenericoServicio implements PeGestionProgramaRemoto, PeGestionProgramaLocal {

    /**
     * Inserta un registro de gestion al afiliado 
     *
     * @param obj
     * @return
     * @throws Exception
     */
    @Override
    public int insertar(PeGestion obj) throws Exception {
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
    
    /**
     * Consultar una gestion mediante su id 
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public PeGestion consultar(int id) throws Exception {
        PeGestion objResult = new PeGestion();

        try {
            objResult = castEntidadNegocio((PeGestiones) getEntityManager().find(PeGestiones.class, id));
        } catch (NoResultException e) {
            objResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return objResult;
    }

    
    /**
     * consulta las gestiones de un afiliado especifico
     *
     * @param idAfiliadosProg
     * @return
     * @throws Exception
     */
    @Override
    public List<PeGestion> consultarPorIdAfiliadoPrograma(int idAfiliadosProg) throws Exception {
        List<PeGestion> listResult = new ArrayList<>();
        try {
            String strQuery = "SELECT p FROM PeGestiones p "
                    + "WHERE p.borrado = 0 AND p.peAfiliadosProgramasId.id = :id "
                    + "ORDER BY p.fechaHoraCrea desc, p.id ";
            
            List<PeGestiones> list = getEntityManager().createQuery(strQuery)
                    .setParameter("id", idAfiliadosProg)
                    .getResultList();
            for (PeGestiones per : list) {
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
     * Permite modificar la opcion de una gestion
     *
     * @param objeto
     * @throws Exception
     */
    @Override
    public void modificar(PeGestion objeto) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            StringBuilder sql = new StringBuilder("UPDATE PeGestiones a SET  ");          
            sql.append(" a.descripcion = :descripcion ,");
            sql.append(" a.usuarioModifica = :usuarioModifica, ");
            sql.append(" a.terminalModifica = :terminalModifica, ");
            sql.append(" a.fechaHoraModifica = :fechaHoraModifica ");           
            sql.append(" WHERE a.id = :id ");
            Query query = session.createQuery(sql.toString());           
            query.setParameter("descripcion", objeto.getDescripcion());
            query.setParameter("usuarioModifica", objeto.getUsuarioModifica());
            query.setParameter("terminalModifica", objeto.getTerminalModifica());
            query.setParameter("fechaHoraModifica", objeto.getFechaHoraModifica());            
            query.setParameter("id", objeto.getId());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    /**
     * Permite hacer borrado logico de la gestiones mediante el id de la gestion
     *
     * @param objeto
     * @throws Exception
     */
    @Override
    public void eliminar(PeGestion objeto) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE PeGestiones a SET a.borrado = 1,  ";          
            strQuery += "a.borradoObservacion = :borradoObservacion ,";
            strQuery += "a.usuarioBorra = :usuarioBorra ,";
            strQuery += "a.terminalBorra = :terminalBorra ,";
            strQuery += "a.fechaHoraBorra = :fechaHoraBorra ";            
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);           
            query.setParameter("borradoObservacion", objeto.getBorradoObservacion());
            query.setParameter("usuarioBorra", objeto.getUsuarioModifica());
            query.setParameter("terminalBorra", objeto.getTerminalModifica());
            query.setParameter("fechaHoraBorra", objeto.getFechaHoraModifica());            
            query.setParameter("id", objeto.getId());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    public static PeGestiones castNegocioEntidad(PeGestion obj) {
        PeGestiones ent = new PeGestiones();
        
        ent.setDescripcion(obj.getDescripcion());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setId(obj.getId());
        if (obj.getPeAfiliadosProgramasId() != null) {
            ent.setPeAfiliadosProgramasId(new PeAfiliadosProgramas(obj.getPeAfiliadosProgramasId().getId()));
        }
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setTipo(obj.getTipo());
        ent.setMaeTipoId(obj.getMaeTipoId());
        ent.setMaeTipoCodigo(obj.getMaeTipoCodigo());
        ent.setMaeTipoValor(obj.getMaeTipoValor());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        if (obj.getUsuariosId() != null) {
            ent.setGnUsuariosId(new GnUsuarios(obj.getUsuariosId().getId()));
        }
        ent.setFuenteOrigen(obj.getFuenteOriegen());
        ent.setBorrado(obj.getBorrado());
        
        return ent;
    }
    
    public static PeGestion castEntidadNegocio(PeGestiones ent) {
        PeGestion obj = new PeGestion();
        
        obj.setDescripcion(ent.getDescripcion());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setId(ent.getId());
        if (ent.getPeAfiliadosProgramasId() != null) {
            obj.setPeAfiliadosProgramasId(new PeAfiliadosPrograma(ent.getPeAfiliadosProgramasId().getId()));
        }        
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTipo(ent.getTipo());
        obj.setMaeTipoId(ent.getMaeTipoId());
        obj.setMaeTipoCodigo(ent.getMaeTipoCodigo());
        obj.setMaeTipoValor(ent.getMaeTipoValor());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        if (ent.getGnUsuariosId() != null) {
            Usuario user = new Usuario(ent.getGnUsuariosId().getId());
            user.setNombre(ent.getGnUsuariosId().getNombre());
            obj.setUsuariosId(user);
        }
        obj.setFuenteOriegen(ent.getFuenteOrigen());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        return obj;
    }
    
}
