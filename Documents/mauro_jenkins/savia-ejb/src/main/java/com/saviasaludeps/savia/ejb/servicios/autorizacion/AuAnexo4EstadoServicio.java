/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Estado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4Estados;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos4;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4EstadoRemoto;
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
 * @author Stiven Giraldo
 */
@Stateless
@Remote(AuAnexo4EstadoRemoto.class)
public class AuAnexo4EstadoServicio extends GenericoServicio implements AuAnexo4EstadoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuAnexo4Estados p "
                    + "WHERE p.id > 0";
            if(paramConsulta.getFiltros() != null){
                for(Map.Entry e : paramConsulta.getFiltros().entrySet()){
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }            
            cantidad = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e){
            cantidad = 0;
        }catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally{
            cerrarEntityManager();
        }
        return cantidad;
    }

    @Override
    public List<AuAnexo4Estado> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo4Estado> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo4Estados p "
                    + "WHERE p.id > 0";
            if(paramConsulta.getFiltros() != null){
                for(Map.Entry e : paramConsulta.getFiltros().entrySet()){
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null){
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<AuAnexo4Estados> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexo4Estados anexo4Estado : list){
                listaResultados.add(castEntidadNegocio(anexo4Estado));
            }
        } catch (NoResultException e){
            listaResultados = new ArrayList();
        }catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally{
            cerrarEntityManager();
        }
        return listaResultados;
    }

    @Override
    public AuAnexo4Estado consultar(int id) throws Exception {
        AuAnexo4Estado objRes = null;
        try {
            objRes = castEntidadNegocio((AuAnexo4Estados) getEntityManager().find(AuAnexo4Estados.class, id));
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
    public int insertar(AuAnexo4Estado obj) throws Exception {
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
    public void actualizar(AuAnexo4Estado obj) throws Exception {
        try {
            AuAnexo4Estados per = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexo4Estados a SET ";
            strQuery += "a.id = :id ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setProperties(per);
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
    public AuAnexo4Estado eliminar(int id) throws Exception {
        AuAnexo4Estado obj = null;
        try {
            AuAnexo4Estados ent = getEntityManager().find(AuAnexo4Estados.class, id);
            if (ent != null){
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
    
    private AuAnexo4Estado castEntidadNegocio(AuAnexo4Estados entidad){
        AuAnexo4Estado negocio = new AuAnexo4Estado();
        negocio.setId(entidad.getId());
        negocio.setAuAnexo4Id(new AuAnexo4(entidad.getAuAnexos4Id().getId()));
        negocio.setMaeEstadoId(entidad.getMaeEstadoId());
        negocio.setMaeEstadoCodigo(entidad.getMaeEstadoCodigo());
        negocio.setMaeEstadoValor(entidad.getMaeEstadoValor());
        if(entidad.getMaeMotivoEstadoId() != null){
            negocio.setMaeMotivoEstadoId(entidad.getMaeMotivoEstadoId());
            negocio.setMaeMotivoEstadoCodigo(entidad.getMaeMotivoEstadoCodigo());
            negocio.setMaeMotivoEstadoValor(entidad.getMaeMotivoEstadoValor());            
        }
        negocio.setObservacion(entidad.getObservacion());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        return negocio;
    }
    
    private AuAnexo4Estados castNegocioEntidad(AuAnexo4Estado negocio){
        AuAnexo4Estados entidad = new AuAnexo4Estados();
        entidad.setId(negocio.getId());
        entidad.setAuAnexos4Id(new AuAnexos4(negocio.getAuAnexo4Id().getId()));
        entidad.setMaeEstadoId(negocio.getMaeEstadoId());
        entidad.setMaeEstadoCodigo(negocio.getMaeEstadoCodigo());
        entidad.setMaeEstadoValor(negocio.getMaeEstadoValor());
        if(negocio.getMaeMotivoEstadoId() != null){
            entidad.setMaeMotivoEstadoId(negocio.getMaeMotivoEstadoId());
            entidad.setMaeMotivoEstadoCodigo(negocio.getMaeMotivoEstadoCodigo());
            entidad.setMaeMotivoEstadoValor(negocio.getMaeMotivoEstadoValor());            
        }
        entidad.setObservacion(negocio.getObservacion());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        return entidad;
    }
}
