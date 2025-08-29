/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.atencionusuario;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.atencionusuario.AusGrupo;
import com.saviasaludeps.savia.dominio.atencionusuario.AusGrupoUsuario;
import com.saviasaludeps.savia.ejb.entidades.AusGrupoUsuarios;
import com.saviasaludeps.savia.ejb.entidades.AusGrupos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.atencionusuario.AusGrupoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jramirer
 */
@Stateless
@Remote(AusGrupoRemoto.class)
public class AusGrupoServicio extends GenericoServicio implements AusGrupoRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
                String strQuery = "SELECT COUNT(ag.id) FROM AusGrupos ag "
                    + "WHERE ag.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND ag.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += "AND ag.descripcion LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += "AND ag.tipo = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            
            strQuery += " ORDER BY ag.id ";
             
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
    public List<AusGrupo> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AusGrupo> listResult = new ArrayList();
        try {
            String strQuery = "SELECT ag FROM AusGrupos ag "
                    + " WHERE ag.id > 0   ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND ag.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += "AND ag.descripcion LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += "AND ag.tipo = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }        
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                String order = "ag." + paramConsulta.getOrden();
                strQuery += order
                        + (paramConsulta.isAscendente() ? " ASC " : " DESC ");
            } else {
                strQuery += "ag.id DESC";
            }
            List<AusGrupos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AusGrupos per : list) {
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
    public AusGrupo consultar(int id) throws Exception {
        AusGrupo objRes = null;
        try {
            AusGrupos per = getEntityManager().find(AusGrupos.class, id);
            if (per != null) {
                objRes = castEntidadNegocioLargo(per);
            }
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
    public int insertar(AusGrupo obj) throws Exception {
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
    public void actualizar(AusGrupo obj) throws Exception {
        try {
            String hql = "UPDATE AusGrupos SET "
                    + " nombre = :nombre,"
                    + " descripcion = :descripcion,"
                    + " tipo = :tipo,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("descripcion", obj.getDescripcion());
            query.setParameter("tipo", obj.getTipo());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
        
    @Override
    public AusGrupo eliminar(int id) throws Exception {
        AusGrupo obj = null;
        try {
            AusGrupos ent = getEntityManager().find(AusGrupos.class, id);
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
    
    
    public static AusGrupo castEntidadNegocio(AusGrupos per) {
        String descripcionEstadosAsociados = "";
        AusGrupo obj = new AusGrupo();
        obj.setId(per.getId());
        obj.setNombre(per.getNombre());
        obj.setDescripcion(per.getDescripcion());
        obj.setTipo(per.getTipo());
        //objetos
        /*if (per.getAusGrupoUsuariosList() != null) {
            for (AusGrupoUsuarios agu : per.getAusGrupoUsuariosList()) {
                
            }
        }*/
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }
    
    public static AusGrupo castEntidadNegocioLargo(AusGrupos per) {
        String descripcionEstadosAsociados = "";
        AusGrupo obj = new AusGrupo();
        obj.setId(per.getId());
        obj.setNombre(per.getNombre());
        obj.setDescripcion(per.getDescripcion());
        obj.setTipo(per.getTipo());
        //objetos
        if (per.getAusGrupoUsuariosList() != null) {
            List<AusGrupoUsuario> listaUsuario = new ArrayList();
            for (AusGrupoUsuarios agu : per.getAusGrupoUsuariosList()) {
                AusGrupoUsuario aus = AusGrupoUsuarioServicio.castEntidadNegocio(agu);
                listaUsuario.add(aus);
            }
        }
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }
    
    public static AusGrupos castNegocioEntidad(AusGrupo obj) {
        AusGrupos per = new AusGrupos();
        per.setId(obj.getId());
        per.setNombre(obj.getNombre()); 
        per.setDescripcion(obj.getDescripcion());
        per.setTipo(obj.getTipo());
        //Auditoría
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }

    @Override
    public List<AusGrupo> consultarTodos() throws java.lang.Exception {
        List<AusGrupo> listResult = new ArrayList();
        try {
            String strQuery = "SELECT ag FROM AusGrupos ag "
                    + " ORDER BY ag.id DESC";
            List<AusGrupos> list = getEntityManager().createQuery(strQuery).getResultList();
            for (AusGrupos per : list) {
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
}
