/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Adjunto;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo3Adjuntos;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos3;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3AdjuntoRemoto;
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
@Remote(AuAnexo3AdjuntoRemoto.class)
public class AuAnexo3AdjuntoServicio extends GenericoServicio implements AuAnexo3AdjuntoRemoto{

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuAnexo3Adjuntos p "
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
    public List<AuAnexo3Adjunto> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo3Adjunto> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo3Adjuntos p "
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
            List<AuAnexo3Adjuntos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexo3Adjuntos anexo3Adjunto : list){
                listaResultados.add(castEntidadNegocio(anexo3Adjunto));
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
    public AuAnexo3Adjunto consultar(int id) throws Exception {
        AuAnexo3Adjunto objRes = null;
        try {
            objRes = castEntidadNegocio((AuAnexo3Adjuntos) getEntityManager().find(AuAnexo3Adjuntos.class, id));
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
    public int insertar(AuAnexo3Adjunto obj) throws Exception {
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
    public void actualizar(AuAnexo3Adjunto obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexo3Adjuntos a SET ";
            strQuery += "a.auAnexos3Id = :auAnexos3Id ,";
            strQuery += "a.maeTipoArchivoId = :maeTipoArchivoId ,";
            strQuery += "a.maeTipoArchivoCodigo = :maeTipoArchivoCodigo ,";
            strQuery += "a.maeTipoArchivoValor = :maeTipoArchivoValor ,";
            strQuery += "a.nombreArchivo = :nombreArchivo ,";
            strQuery += "a.ruta = :ruta ,";
            strQuery += "a.archivo = :archivo ,";
            strQuery += "a.existe = :existe ,";
            strQuery += "a.usuarioCrea = :usuarioCrea ,";
            strQuery += "a.terminalCrea = :terminalCrea ,";
            strQuery += "a.fechaHoraCrea = :fechaHoraCrea ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("maeTipoArchivoId", obj.getMaeTipoArchivoId());
            query.setParameter("maeTipoArchivoCodigo", obj.getMaeTipoArchivoCodigo());
            query.setParameter("maeTipoArchivoValor", obj.getMaeTipoArchivoValor());
            query.setParameter("nombreArchivo", obj.getNombreArchivo());
            query.setParameter("ruta", obj.getRuta());
            query.setParameter("archivo", obj.getArchivo());
            query.setParameter("existe", obj.isExiste());
            query.setParameter("usuarioCrea", obj.getUsuarioCrea());
            query.setParameter("terminalCrea", obj.getTerminalCrea());
            query.setParameter("fechaHoraCrea", obj.getFechaHoraCrea());
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
    public AuAnexo3Adjunto eliminar(int id) throws Exception {
        AuAnexo3Adjunto obj = null;
        try {
            AuAnexo3Adjuntos ent = getEntityManager().find(AuAnexo3Adjuntos.class, id);
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
    
    private static AuAnexo3Adjunto castEntidadNegocio(AuAnexo3Adjuntos entidad){
        AuAnexo3Adjunto negocio = new AuAnexo3Adjunto();
        negocio.setId(entidad.getId());
        negocio.setAuAnexos3Id(new AuAnexo3(entidad.getAuAnexos3Id().getId()));
        negocio.setMaeTipoArchivoId(entidad.getMaeTipoArchivoId());
        negocio.setMaeTipoArchivoCodigo(entidad.getMaeTipoArchivoCodigo());
        negocio.setMaeTipoArchivoValor(entidad.getMaeTipoArchivoValor());
        negocio.setNombreArchivo(entidad.getNombreArchivo());
        negocio.setRuta(entidad.getRuta());
        negocio.setArchivo(entidad.getArchivo());
        negocio.setExiste(entidad.getExiste());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        return negocio;
    }
    
    private static AuAnexo3Adjuntos castNegocioEntidad(AuAnexo3Adjunto negocio){
        AuAnexo3Adjuntos entidad = new AuAnexo3Adjuntos();
        entidad.setId(negocio.getId());
        entidad.setAuAnexos3Id(new AuAnexos3(negocio.getAuAnexos3Id().getId()));
        entidad.setMaeTipoArchivoId(negocio.getMaeTipoArchivoId());
        entidad.setMaeTipoArchivoCodigo(negocio.getMaeTipoArchivoCodigo());
        entidad.setMaeTipoArchivoValor(negocio.getMaeTipoArchivoValor());
        entidad.setNombreArchivo(negocio.getNombreArchivo());
        entidad.setRuta(negocio.getRuta());
        entidad.setArchivo(negocio.getArchivo());
        entidad.setExiste(negocio.isExiste());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        return entidad;        
    }
    
    public static List<AuAnexo3Adjunto> casteoListaEntidadNegocio(List<AuAnexo3Adjuntos> listaEntidad){
        List<AuAnexo3Adjunto> listaNegocio = new ArrayList();
        listaEntidad.forEach(entidad -> {
            listaNegocio.add(castEntidadNegocio(entidad));
        });
        return listaNegocio;
    }
}
