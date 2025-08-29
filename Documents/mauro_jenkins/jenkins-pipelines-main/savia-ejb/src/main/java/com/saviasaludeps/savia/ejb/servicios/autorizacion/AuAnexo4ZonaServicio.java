/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Destino;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Zona;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4Destinos;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4Zonas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4ZonaRemoto;
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
@Remote(AuAnexo4ZonaRemoto.class)
public class AuAnexo4ZonaServicio extends GenericoServicio implements AuAnexo4ZonaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuAnexo4Zonas p "
                    + "WHERE p.id > 0";
            if(paramConsulta.getFiltros() != null){
                for(Map.Entry e : paramConsulta.getFiltros().entrySet()){
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "nombre":
                            strQuery += "AND p.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "ubicacionId":
                            strQuery += "AND p.ubicacionId = " +e.getValue() + " ";
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
    public List<AuAnexo4Zona> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo4Zona> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo4Zonas p "
                    + "WHERE p.id > 0";
            if(paramConsulta.getFiltros() != null){
                for(Map.Entry e : paramConsulta.getFiltros().entrySet()){
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "nombre":
                            strQuery += "AND p.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "ubicacionId":
                            strQuery += "AND p.ubicacionId = " +e.getValue() + " ";
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
            List<AuAnexo4Zonas> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexo4Zonas anexo4Zonas : list){
                listaResultados.add(castEntidadNegocio(anexo4Zonas));
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
    public int insertar(AuAnexo4Zona obj) throws Exception {
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
    public AuAnexo4Zona eliminar(int id) throws Exception {
        AuAnexo4Zona obj = null;
        try {
            AuAnexo4Zonas ent = getEntityManager().find(AuAnexo4Zonas.class, id);
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

    @Override
    public AuAnexo4Zona consultar(int id) throws Exception {
        AuAnexo4Zona objRes = null;
        try {
            objRes = castEntidadNegocio((AuAnexo4Zonas) getEntityManager().find(AuAnexo4Zonas.class, id));
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
    public void actualizar(AuAnexo4Zona obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexo4Zonas a SET ";
            strQuery += "a.ubicacionId = :ubicacionId ,";
            strQuery += "a.ubicacionValor = :ubicacionValor ,";
            strQuery += "a.nombre = :nombre ,";
            strQuery += "a.activo = :activo ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("ubicacionId", obj.getUbicacionId());
            query.setParameter("ubicacionValor", obj.getUbicacionValor());
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("activo", obj.isActivo());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    private AuAnexo4Zona castEntidadNegocio(AuAnexo4Zonas entidad) {
        AuAnexo4Zona negocio = new AuAnexo4Zona();
        negocio.setId(entidad.getId());
        negocio.setUbicacionId(entidad.getUbicacionId());
        negocio.setUbicacionValor(entidad.getUbicacionValor());
        negocio.setNombre(entidad.getNombre());
        negocio.setActivo(entidad.getActivo());        
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }
    
    public AuAnexo4Zona castEntidadNegocioLargo(AuAnexo4Zonas entidad) {
        AuAnexo4Zona negocio = new AuAnexo4Zona();
        negocio.setId(entidad.getId());
        negocio.setUbicacionId(entidad.getUbicacionId());
        negocio.setUbicacionValor(entidad.getUbicacionValor());
        negocio.setNombre(entidad.getNombre());
        negocio.setActivo(entidad.getActivo());
        negocio.setAuAnexo4DestinoList(new ArrayList());
        if (!entidad.getAuAnexo4DestinosList().isEmpty()) {
            entidad.getAuAnexo4DestinosList().forEach(destino -> {
                negocio.getAuAnexo4DestinoList().add(castEntidadNegocioDestino(destino));
            });
        }
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }
    
    private AuAnexo4Zonas castNegocioEntidad(AuAnexo4Zona negocio) {
        AuAnexo4Zonas entidad = new AuAnexo4Zonas();
        entidad.setId(negocio.getId());
        entidad.setUbicacionId(negocio.getUbicacionId());
        entidad.setUbicacionValor(negocio.getUbicacionValor());
        entidad.setNombre(negocio.getNombre());
        entidad.setActivo(negocio.isActivo());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        return entidad;
    }
    
    public AuAnexo4Destino castEntidadNegocioDestino(AuAnexo4Destinos entidad){
        AuAnexo4Destino negocio = new AuAnexo4Destino();
        negocio.setId(entidad.getId());
        negocio.setUbicacionId(entidad.getUbicacionId());
        negocio.setUbicacionValor(entidad.getUbicacionValor());
        negocio.setOrden(entidad.getOrden());
        negocio.setActivo(entidad.getActivo());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }

    @Override
    public boolean validarZona(int idUbicacion) throws Exception {
        boolean validar = false;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuAnexo4Zonas p "
                    + "WHERE p.ubicacionId = " + idUbicacion + " "
                    + "ORDER BY p.id DESC";
            int cantidad = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
            if (cantidad == 0) {
                validar = true;
            }
        } catch (NoResultException e) {
            validar = false;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
            validar = false;
        } finally {
            cerrarEntityManager();
        }
        return validar;
    }
    
}
