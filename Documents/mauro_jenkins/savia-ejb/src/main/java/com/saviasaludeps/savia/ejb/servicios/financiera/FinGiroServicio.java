/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.financiera;

import com.saviasaludeps.savia.dominio.financiera.FinGiro;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.FinGiros;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.financiera.FinGiroRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jeperez
 */
@Stateless
@Remote(FinGiroRemoto.class)
public class FinGiroServicio extends GenericoServicio implements FinGiroRemoto {
    

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(fg) FROM FinGiros fg "
                    + " WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND fg.id = " + e.getValue() + " ";
                            break;
                        case "tipo":
                            strQuery += " AND fg.tipo = " + e.getValue() + " ";
                            break;
                        case "nombre":
                            strQuery += " AND fg.nombre = '" + e.getValue() + "' ";
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
    public List<FinGiro> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<FinGiro> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM FinGiros fg WHERE 1 = 1 ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND fg.id = " + e.getValue() + " ";
                            break;
                        case "tipo":
                            strQuery += " AND fg.tipo = " + e.getValue() + " ";
                            break;
                        case "nombre":
                            strQuery += " AND fg.nombre = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            
            
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "fg." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "fg.id DESC";
            }
            List<FinGiros> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (FinGiros giro : list) {
                listaResultados.add(castEntidadNegocio(giro));
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
    public FinGiro consultar(int id) throws Exception {
        FinGiro objRes = null;
        try {
            FinGiros per = getEntityManager().find(FinGiros.class, id);
            if (per != null) {
                objRes = castEntidadNegocio(per);
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
    public int insertar(FinGiro obj) throws Exception {
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
    public void actualizar(FinGiro obj) throws Exception {
        try {
            String hql = "UPDATE FinGiros SET "
                    + "tipo = :tipo, "
                    + "nombre = :nombre, "
                    + "usuario_modifica = :usuario_modifica, "
                    + "terminal_modifica = :terminal_modifica, "
                    + "fecha_hora_modifica = :fecha_hora_modifica "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            
            query.setParameter("tipo", obj.getTipo());
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("usuario_modifica", obj.getUsuarioModifica());
            query.setParameter("terminal_modifica", obj.getTerminalModifica());
            query.setParameter("fecha_hora_modifica", obj.getFechaHoraModifica());
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
    public FinGiro eliminar(int id) throws Exception {
        FinGiro obj = null;
        try {
            FinGiros ent = getEntityManager().find(FinGiros.class, id);
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

    private FinGiro castEntidadNegocio(FinGiros ent) {
        FinGiro neg = new FinGiro();
        neg.setId(ent.getId());
        neg.setTipo(ent.getTipo());
        neg.setNombre(ent.getNombre());
        neg.setBorrado(ent.getBorrado());
       
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());   
        neg.setFechaHoraModifica(ent.getFechaHoraModifica());
        neg.setUsuarioModifica(ent.getUsuarioModifica());
        neg.setTerminalModifica(ent.getTerminalModifica());
        neg.setFechaHoraBorra(ent.getFechaHoraBorra());
        neg.setUsuarioBorra(ent.getUsuarioBorra());
        neg.setTerminalBorra(ent.getTerminalBorra());
        return neg;
    }

    private FinGiros castNegocioEntidad(FinGiro neg) {
        FinGiros ent = new FinGiros();
        ent.setId(neg.getId());
        ent.setTipo(neg.getTipo());
        ent.setNombre(neg.getNombre());
        ent.setBorrado(neg.getBorrado());
       
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());   
        ent.setFechaHoraModifica(neg.getFechaHoraModifica());
        ent.setUsuarioModifica(neg.getUsuarioModifica());
        ent.setTerminalModifica(neg.getTerminalModifica());
        ent.setFechaHoraBorra(neg.getFechaHoraBorra());
        ent.setUsuarioBorra(neg.getUsuarioBorra());
        ent.setTerminalBorra(neg.getTerminalBorra());
        return ent;
    }



}
