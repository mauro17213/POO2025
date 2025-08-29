/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Historico;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo3Historicos;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos3;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3HistoricoRemoto;
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
 * @author idbohorquez
 */

@Stateless
@Remote(AuAnexo3HistoricoRemoto.class)
public class AuAnexo3HistoricoServicio extends GenericoServicio implements AuAnexo3HistoricoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuAnexo3Historicos p "
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
    public List<AuAnexo3Historico> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<AuAnexo3Historico> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo3Historicos p "
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
            List<AuAnexo3Historicos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexo3Historicos anexo3Historico : list){
                listaResultados.add(castEntidadNegocio(anexo3Historico));
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
    public AuAnexo3Historico consultar(int id) throws java.lang.Exception {
        AuAnexo3Historico objRes = null;
        try {
            objRes = castEntidadNegocio((AuAnexo3Historicos) getEntityManager().find(AuAnexo3Historicos.class, id));
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
    public int insertar(AuAnexo3Historico obj) throws java.lang.Exception {
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
    public void actualizar(AuAnexo3Historico obj) throws java.lang.Exception {
        try {
            AuAnexo3Historicos per = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexo3Historicos a SET ";
            strQuery += "a.id = :id ,";
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
    public AuAnexo3Historico eliminar(int id) throws java.lang.Exception {
        AuAnexo3Historico obj = null;
        try {
            AuAnexo3Historicos ent = getEntityManager().find(AuAnexo3Historicos.class, id);
            if (ent != null){
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }    
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }
    
    private AuAnexo3Historico castEntidadNegocio(AuAnexo3Historicos entidad){
        AuAnexo3Historico negocio = new AuAnexo3Historico();
        negocio.setId(entidad.getId());
        negocio.setAuAnexo3Id(new AuAnexo3(entidad.getAuAnexos3Id().getId()));
        negocio.setTipo(entidad.getTipo());
        negocio.setEstado(entidad.getEstado());
        negocio.setObservacion(entidad.getObservacion());        
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }
    
    private AuAnexo3Historicos castNegocioEntidad(AuAnexo3Historico negocio){
        AuAnexo3Historicos entidad = new AuAnexo3Historicos();
        entidad.setId(negocio.getId());
        entidad.setAuAnexos3Id(new AuAnexos3(negocio.getAuAnexo3Id().getId()));
        entidad.setTipo(negocio.getTipo());
        entidad.setEstado(negocio.getEstado());
        entidad.setObservacion(negocio.getObservacion());        
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;        
    }
    
    @Override
    public List<AuAnexo3Historico> listarPorIdItem(int idItem) throws Exception {
        List<AuAnexo3Historico> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo3Historicos p "
                    + "WHERE p.auAnexos3Id.id =" + idItem
                    + " ORDER BY p.id DESC";

            List<AuAnexo3Historicos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuAnexo3Historicos anexo3Historico : list){
                listaResultados.add(castEntidadNegocio(anexo3Historico));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }
    
}
