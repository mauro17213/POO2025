/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.gestionAtencion;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeTaquilla;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTaquillaFuncionario;
import com.saviasaludeps.savia.ejb.entidades.GatTaquillaFuncionarios;
import com.saviasaludeps.savia.ejb.entidades.GatSedeTaquillas;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatTaquillaFuncionarioRemoto;
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
 * @author acuartas
 */
@Stateless
@Remote(GatTaquillaFuncionarioRemoto.class)
public class GatTaquillaFuncionarioServicio extends GenericoServicio implements GatTaquillaFuncionarioRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GatTaquillaFuncionarios c "
                    + " WHERE 1 = 1 ";            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id LIKE '%" + e.getValue() + "%' ";
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
    public List<GatTaquillaFuncionario> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GatTaquillaFuncionario> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM GatTaquillaFuncionarios c WHERE 1 = 1 ";
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "c." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "c.id DESC";
            }
            List<GatTaquillaFuncionarios> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GatTaquillaFuncionarios funcionario : list) {
                listaResultados.add(castEntidadNegocio(funcionario));
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
    public GatTaquillaFuncionario consultar(int id) throws Exception {
        GatTaquillaFuncionario objRes = null;
        try {
            objRes = castEntidadNegocio((GatTaquillaFuncionarios) getEntityManager().find(GatTaquillaFuncionarios.class, id));
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
    public int insertar(GatTaquillaFuncionario obj) throws Exception {
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
    public void actualizar(GatTaquillaFuncionario obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GatTaquillaFuncionarios a SET ";
            strQuery += "a.gatSedeTaquillasId.id = :gatSedeTaquillasId ,";
            strQuery += "a.gnUsuariosId.id = :gnUsuariosId ,";
            strQuery += "a.fechaHoraInicio = :fechaHoraInicio ,";
            strQuery += "a.fechaHoraFin = :fechaHoraFin ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("gatSedeTaquillasId", obj.getGatSedeTaquillaId().getId());
            query.setParameter("gnUsuariosId", obj.getGnUsuarioId().getId());
            query.setParameter("fechaHoraInicio", obj.getFechaHoraInicio());
            query.setParameter("fechaHoraFin", obj.getFechaHoraFin());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
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
    public GatTaquillaFuncionario eliminar(int id) throws Exception {
        GatTaquillaFuncionario obj = null;
        try {
            GatTaquillaFuncionarios ent = getEntityManager().find(GatTaquillaFuncionarios.class, id);
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
    
    private GatTaquillaFuncionario castEntidadNegocio(GatTaquillaFuncionarios entidad) {
        GatTaquillaFuncionario negocio = new GatTaquillaFuncionario();
        negocio.setId(entidad.getId());
        negocio.setGatSedeTaquillaId(new GatSedeTaquilla(entidad.getGatSedeTaquillasId().getId()));
        negocio.setGnUsuarioId(new Usuario(entidad.getGnUsuariosId().getId()));
        negocio.setFechaHoraInicio(entidad.getFechaHoraInicio());
        negocio.setFechaHoraFin(entidad.getFechaHoraFin());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }
    
    private GatTaquillaFuncionarios castNegocioEntidad(GatTaquillaFuncionario negocio) {
        GatTaquillaFuncionarios entidad = new GatTaquillaFuncionarios();
        entidad.setId(negocio.getId());
        entidad.setGatSedeTaquillasId(new GatSedeTaquillas(negocio.getGatSedeTaquillaId().getId()));
        entidad.setGnUsuariosId(new GnUsuarios(negocio.getGnUsuarioId().getId()));
        entidad.setFechaHoraInicio(negocio.getFechaHoraInicio());
        entidad.setFechaHoraFin(negocio.getFechaHoraFin());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        return entidad;
    }
}
