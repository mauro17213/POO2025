/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.crue;

import com.saviasaludeps.savia.dominio.crue.AuAnexo2;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Item;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo2Items;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo3Items;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo2Items;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos2;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2ItemRemoto;
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
 * @author AlexanderDiaz
 */
@Stateless
@Remote(AuAnexo2ItemRemoto.class)
public class AuAnexo2ItemServicio extends GenericoServicio implements AuAnexo2ItemRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuAnexo2Items p "
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
    public List<AuAnexo2Item> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo2Item> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo2Items p "
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
            /**
            List<AuAnexo2Items> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexo2Items anexo2Item : list){
                listaResultados.add(castEntidadNegocio(anexo2Item));
            }**/
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
    public List<AuAnexo2Item> consultarListaByIdAnexo2(int idAnexo2) throws Exception {
        List<AuAnexo2Item> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo2Items p "
                    + "WHERE p.id > 0";
            
            strQuery += "AND p.auAnexos2Id.id = " + idAnexo2 + " ";
            strQuery += "ORDER BY ";
            strQuery += "p.id DESC";
            List<AuAnexo2Items> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuAnexo2Items anexo2Item : list){
                listaResultados.add(castEntidadNegocio(anexo2Item));
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
    public AuAnexo2Item consultar(int id) throws Exception {
        AuAnexo2Item objRes = null;
        try {
            //objRes = castEntidadNegocio((AuAnexo2Items) getEntityManager().find(AuAnexo2Items.class, id));
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
    public int insertar(AuAnexo2Item obj) throws Exception {
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
    public void actualizar(AuAnexo2Item obj) throws Exception {
        try {
            //AuAnexo2Items per = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexo2Items a SET ";
            strQuery += "a.id = :id ,";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            //query.setProperties(per);
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
    public AuAnexo2Item eliminar(int id) throws Exception {
        AuAnexo2Item obj = null;
        try {
            /*AuAnexo2Items ent = getEntityManager().find(AuAnexo2Items.class, id);
            if (ent != null){
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }*/        
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }
    
    private AuAnexo2Items castNegocioEntidad(AuAnexo2Item negocio){
        AuAnexo2Items entidad = new AuAnexo2Items();
        if(negocio.getId() != null){
            entidad.setId(negocio.getId());
        }
        entidad.setAuAnexos2Id(new AuAnexos2(negocio.getAuAnexos2Id().getId()));
        entidad.setMaTecnologiaId(negocio.getMaTecnologiaId());
        entidad.setMaTecnologiaCodigo(negocio.getMaTecnologiaCodigo());
        entidad.setMaTecnologiaValor(negocio.getMaTecnologiaValor());
        entidad.setCantidadSolicitada(negocio.getCantidadSolicitada());
        entidad.setMaServicioSolicitadoId(negocio.getMaServicioSolicitadoId());
        entidad.setMaServicioSolicitadoCodigo(negocio.getMaServicioSolicitadoCodigo());
        entidad.setMaServicioSolicitadoValor(negocio.getMaServicioSolicitadoValor());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        return entidad;
    }
    
    public static AuAnexo2Item castEntidadNegocio(AuAnexo2Items ent){
        AuAnexo2Item neg = new AuAnexo2Item();
        if(ent.getId() != null){
            neg.setId(ent.getId());
        }
        neg.setAuAnexos2Id(new AuAnexo2(ent.getAuAnexos2Id().getId()));
        neg.setMaTecnologiaId(ent.getMaTecnologiaId());
        neg.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        neg.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        neg.setCantidadSolicitada(ent.getCantidadSolicitada());
        neg.setMaServicioSolicitadoId(ent.getMaServicioSolicitadoId());
        neg.setMaServicioSolicitadoCodigo(ent.getMaServicioSolicitadoCodigo());
        neg.setMaServicioSolicitadoValor(ent.getMaServicioSolicitadoValor());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        return neg;
    }
}
