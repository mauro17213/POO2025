/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.gestionAtencion;

import com.saviasaludeps.savia.dominio.administracion.GnSede;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeFuncionario;
import com.saviasaludeps.savia.ejb.entidades.GatSedeFuncionarios;
import com.saviasaludeps.savia.ejb.entidades.GnSedes;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatSedeFuncionarioRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author acuartas
 */
@Stateless
@Remote(GatSedeFuncionarioRemoto.class)
public class GatSedeFuncionarioServicio extends GenericoServicio implements GatSedeFuncionarioRemoto {

    @Override
    public GatSedeFuncionario consultar(int id) throws Exception {
        GatSedeFuncionario objRes = null;
        try {
            objRes = castEntidadNegocio((GatSedeFuncionarios) getEntityManager().find(GatSedeFuncionarios.class, id));
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
    public int insertar(GatSedeFuncionario obj) throws Exception {
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
    public void actualizar(GatSedeFuncionario obj) throws Exception {
         try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GatSedeFuncionarios a SET ";
            strQuery += "a.gnUsuariosId.id = :gnUsuariosId ,";
            strQuery += "a.activo = :activo ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            //strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("gnUsuariosId", obj.getUsuarioId().getId());
            query.setParameter("activo", obj.isActivo());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            //query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());            
            query.setParameter("id", obj.getId());
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
    public GatSedeFuncionario eliminar(int id) throws Exception {
        GatSedeFuncionario obj = null;
        try {
            GatSedeFuncionarios ent = getEntityManager().find(GatSedeFuncionarios.class, id);
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

    private GatSedeFuncionario castEntidadNegocio(GatSedeFuncionarios entidad) {
        GatSedeFuncionario negocio = new GatSedeFuncionario();
        negocio.setId(entidad.getId());
        negocio.setGnSedeId(new GnSede(entidad.getGnSedesId().getId()));
        negocio.setUsuarioId(new Usuario(entidad.getGnUsuariosId().getId()));
        negocio.getUsuarioId().setNombre(entidad.getGnUsuariosId().getNombre());
        negocio.setActivo(entidad.getActivo());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        return negocio;
    }
    
    private GatSedeFuncionarios castNegocioEntidad(GatSedeFuncionario negocio) {
        GatSedeFuncionarios entidad = new GatSedeFuncionarios();
        entidad.setGnSedesId(new GnSedes(negocio.getGnSedeId().getId()));
        entidad.setGnUsuariosId(new GnUsuarios(negocio.getUsuarioId().getId()));
        entidad.setActivo(negocio.isActivo());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        return entidad;
    }

    @Override
    public List<GatSedeFuncionario> listarPorIdSede(int idSede) throws Exception {
        List<GatSedeFuncionario> listaResultado = new ArrayList<>();
        try {
            String strQuery = "FROM GatSedeFuncionarios c "
                    + " WHERE c.gnSedesId.id = "+idSede
                    + " ORDER BY c.id DESC";
            List<GatSedeFuncionarios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GatSedeFuncionarios funcionario : list) {
                listaResultado.add(castEntidadNegocio(funcionario));
            }
        } catch (NoResultException e) {
            listaResultado = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultado;
    }

    @Override
    public GatSedeFuncionario consultarPorSedeYUsuario(int idSede, int idUsuario) throws Exception {
        GatSedeFuncionario obj = new GatSedeFuncionario();
        try {
            String strQuery = "FROM GatSedeFuncionarios c "
                    + " WHERE c.gnSedesId.id = "+idSede
                    + " AND c.gnUsuariosId.id = "+idUsuario
                    + " ORDER BY c.id DESC";
            List<GatSedeFuncionarios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if (list != null) {
                for (GatSedeFuncionarios funcionario : list) {
                    obj = castEntidadNegocio(funcionario);
                    break;
                }
            }            
        } catch (NoResultException e) {
            obj = new GatSedeFuncionario();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public List<Usuario> consultarIdUsuarios(int idSede) throws Exception {
        List<Usuario> listResul = new ArrayList<>();
        try {
            String strQuery = "FROM GatSedeFuncionarios c "
                    + " WHERE c.gnSedesId.id = "+idSede
                    + " ORDER BY c.id DESC";
            List<GatSedeFuncionarios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if (list != null) {
                for (GatSedeFuncionarios funcionario : list) {
                    listResul.add(new Usuario(funcionario.getGnUsuariosId().getId()));
                }
            }            
        } catch (NoResultException e) {
            listResul = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listResul;
    }
}
