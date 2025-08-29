/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.financiera;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.financiera.FinCarga;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.FinCargas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.financiera.FinCargaRemoto;
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
@Remote(FinCargaRemoto.class)
public class FinCargaServicio extends GenericoServicio implements FinCargaRemoto {
    

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(fg) FROM FinCargas fg "
                    + " WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND fg.id = " + e.getValue() + " ";
                            break;
                        case "archivo":
                            strQuery += "AND fg.archivo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND fg.estado = " + e.getValue() + " ";
                            break;
                            case "usuarioCrea":
                            strQuery += "AND fg.usuarioCrea like '%" + (String) e.getValue() + "%' ";
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
    public List<FinCarga> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<FinCarga> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM FinCargas fg WHERE 1 = 1 ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND fg.id = " + e.getValue() + " ";
                            break;
                        case "archivo":
                            strQuery += "AND fg.archivo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND fg.estado = " + e.getValue() + " ";
                            break;
                            case "usuarioCrea":
                            strQuery += "AND fg.usuarioCrea like '%" + (String) e.getValue() + "%' ";
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
            List<FinCargas> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (FinCargas carga : list) {
                listaResultados.add(castEntidadNegocio(carga));
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
    public FinCarga consultar(int id) throws Exception {
        FinCarga objRes = null;
        try {
            FinCargas per = getEntityManager().find(FinCargas.class, id);
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
    public int insertar(FinCarga obj) throws Exception {
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
    public void actualizar(FinCarga obj) throws Exception {
        try {
            String hql = "UPDATE FinCargas SET "
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
    public void actualizarEstado(FinCarga obj) throws Exception {
        try {
            String hql = "UPDATE FinCargas SET "
                    + " estado = :estado "
                    + " WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            
            query.setParameter("estado", obj.getEstado());
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
     public void actualizarNumeroEjecucion(FinCarga obj) throws Exception {
        try {
            String hql = "UPDATE FinCargas SET "
                    + " exitosos = :exitosos , "
                    + " fallidos = :fallidos , "
                    + " fechaHoraFin = :fechaHoraFin "
                    + " WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            
            query.setParameter("exitosos", obj.getExitosos());
            query.setParameter("fallidos", obj.getFallidos());
            query.setParameter("fechaHoraFin", obj.getFechaHoraFin());
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
     public void actualizarFechaFin(FinCarga obj) throws Exception {
        try {
            String hql = "UPDATE FinCargas SET "
                    + " fechaHoraFin = :fechaHoraFin "
                    + " WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
           
            query.setParameter("fechaHoraFin", obj.getFechaHoraFin());
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
     public void actualizarRespuestaExiste(FinCarga obj) throws Exception {
        try {
            String hql = "UPDATE FinCargas SET "
                    + " respExiste = :respExiste  "
                    + " WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            
            query.setParameter("respExiste", obj.getRespExiste());
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
    public FinCarga eliminar(int id) throws Exception {
        FinCarga obj = null;
        try {
            FinCargas ent = getEntityManager().find(FinCargas.class, id);
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
    
    @Override
    public CntPrestador consultarPrestadorCarga(String documento) throws java.lang.Exception {
        CntPrestador obj = null;
        try {
            String strQuery = "FROM CntPrestadores p ";
            strQuery += "WHERE p.id > 0 AND p.numeroDocumento = :prestador AND p.activo = 1 ";
            strQuery += " ORDER BY p.id ASC";
            CntPrestadores per = (CntPrestadores) getEntityManager().createQuery(strQuery)
                    .setParameter("prestador", documento)
                    .getSingleResult();
            if(per != null){
                 obj = castEntidadNegocio(per);
            } 
        } catch (NoResultException e) {
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }    

    private FinCarga castEntidadNegocio(FinCargas ent) {
        FinCarga neg = new FinCarga();
        neg.setId(ent.getId());
        neg.setNombre(ent.getNombre());
        neg.setRuta(ent.getRuta());
        neg.setArchivo(ent.getArchivo());
        neg.setExiste(ent.getExiste());
        neg.setRespNombre(ent.getRespNombre());
        neg.setRespRuta(ent.getRespRuta());
        neg.setRespArchivo(ent.getRespArchivo());
        neg.setRespExiste(ent.getRespExiste());
        neg.setEstado(ent.getEstado());
        neg.setRegistros(ent.getRegistros());
        neg.setExitosos(ent.getExitosos());
        neg.setFallidos(ent.getFallidos());
        neg.setFechaHoraInicio(ent.getFechaHoraInicio());
        neg.setFechaHoraFin(ent.getFechaHoraFin());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());   
        return neg;
    }

    private FinCargas castNegocioEntidad(FinCarga neg) {
        FinCargas ent = new FinCargas();
        ent.setId(neg.getId());
        ent.setNombre(neg.getNombre());
        ent.setRuta(neg.getRuta());
        ent.setArchivo(neg.getArchivo());
        ent.setExiste(neg.getExiste());
        ent.setRespNombre(neg.getRespNombre());
        ent.setRespRuta(neg.getRespRuta());
        ent.setRespArchivo(neg.getRespArchivo());
        ent.setRespExiste(neg.getRespExiste());
        ent.setEstado(neg.getEstado());
        ent.setRegistros(neg.getRegistros());
        ent.setExitosos(neg.getExitosos());
        ent.setFallidos(neg.getFallidos());
        ent.setFechaHoraInicio(neg.getFechaHoraInicio());
        ent.setFechaHoraFin(neg.getFechaHoraFin());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea()); 
        return ent;
    }
    
     
    public static CntPrestador castEntidadNegocio(CntPrestadores per) {
        CntPrestador obj = new CntPrestador();
        obj.setId(per.getId());
        obj.setNumeroDocumento(per.getNumeroDocumento());
        obj.setRazonSocial(per.getRazonSocial());
        obj.setNaturalezaJuridica(per.getNaturalezaJuridica());
        obj.setPrefijo(per.getPrefijo());
        return obj;
    }
    



}
