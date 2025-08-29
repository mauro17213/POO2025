
package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjComite;
import com.saviasaludeps.savia.dominio.juridico.CntjExpediente;
import com.saviasaludeps.savia.dominio.juridico.CntjLinea;
import com.saviasaludeps.savia.ejb.entidades.CntjComites;
import com.saviasaludeps.savia.ejb.entidades.CntjExpedientes;
import com.saviasaludeps.savia.ejb.entidades.CntjLineas;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CtnjLineaRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author idbohorquez
 */

@Stateless
@Remote(CtnjLineaRemoto.class)
public class CntjLineaServicio extends GenericoServicio implements CtnjLineaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjLineas c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id_comite":
                            strQuery.append(" AND c.cntjComitesId.id = ").append(e.getValue());
                            break;
                        case "area":
                            strQuery.append(" AND c.area like '%").append(e.getValue()).append("%' ");
                            break;
                        case "tipo":
                            strQuery.append(" AND c.tipo = ").append(e.getValue());
                            break;                        
                        case "estado":
                            strQuery.append(" AND c.estado = ").append(e.getValue());
                            break;
                        case "usuariosId.nombre":
                            strQuery.append(" AND c.gnUsuariosId.nombre like '%").append(e.getValue()).append("%' ");
                            break;
                        
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery.toString())
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
    public List<CntjLinea> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntjLinea> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjLineas c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id_comite":
                            strQuery.append(" AND c.cntjComitesId.id = ").append(e.getValue());
                            break;
                        case "area":
                            strQuery.append(" AND c.area like '%").append(e.getValue()).append("%' ");
                            break;
                        case "tipo":
                            strQuery.append(" AND c.tipo = ").append(e.getValue());
                            break;                        
                        case "estado":
                            strQuery.append(" AND c.estado = ").append(e.getValue());
                            break;
                        case "usuariosId.nombre":
                            strQuery.append(" AND c.gnUsuariosId.nombre like '%").append(e.getValue()).append("%' ");
                            break;
                        
                    }
                }
            }
            strQuery.append(" ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                strQuery.append(" c." + paramConsulta.getOrden() + " " + (paramConsulta.isAscendente() ? "ASC" : "DESC"));
            } else {
                strQuery.append(" c.id DESC ");
            }
            List<CntjLineas> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjLineas comite  : list) {
                listResult.add(castEntidadNegocio(comite));
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
    public int insertar(CntjLinea objeto) throws java.lang.Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(objeto)).getId();
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }
    
    @Override
    public CntjLinea consultar(int id) throws java.lang.Exception {
        CntjLinea objRes = null;
        try {
            objRes = castEntidadNegocio((CntjLineas) getEntityManager().find(CntjLineas.class, id));
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
    public void actualizar(CntjLinea objeto) throws java.lang.Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CntjLineas SET gnUsuariosId.id = :idusuario, ");
            sql.append("tipo = :tipo,  ");
            sql.append("estado = :estado,  ");
            sql.append("descripcion = :descripcion,  ");
            sql.append("area = :area,  ");
            sql.append("cntjExpedientesId.id = :idexpediente,  ");
            sql.append("observaciones = :observaciones,  ");
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("idusuario", objeto.getUsuariosId().getId());
            query.setParameter("tipo", objeto.getTipo().shortValue());
            query.setParameter("estado", objeto.getEstado().shortValue());
            query.setParameter("descripcion", objeto.getDescripcion());
            query.setParameter("area", objeto.getArea());
            query.setParameter("idexpediente", objeto.getExpedienteId().getId());
            query.setParameter("observaciones", objeto.getObservacion());            
            query.setParameter("usuarioModifica", objeto.getUsuarioModifica());
            query.setParameter("terminalModifica", objeto.getTerminalModifica());
            query.setParameter("fechaHoraModifica", objeto.getFechaHoraModifica());            
            query.setParameter("id", objeto.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public int cantidadLineaComite(int idcomite) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjLineas c WHERE c.id > 0 ");
            strQuery.append(" AND c.cntjComitesId.id = ").append(idcomite);
            cant = (int) (long) getEntityManager().createQuery(strQuery.toString())
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
    
    
    private CntjLinea castEntidadNegocio(CntjLineas entidad) {
        CntjLinea objeto = new CntjLinea();
        objeto.setId(entidad.getId());
        objeto.setCntjComiteId(new CntjComite(entidad.getCntjComitesId().getId()));
        if(entidad.getGnUsuariosId() != null){
            Usuario usuario = new Usuario(entidad.getGnUsuariosId().getId());
            usuario.setNombre(entidad.getGnUsuariosId().getNombre());
            objeto.setUsuariosId(usuario);
        }
        if(entidad.getCntjExpedientesId() != null){
            CntjExpediente expediente = new CntjExpediente(entidad.getCntjExpedientesId().getId());
            expediente.setNumeroExpediente(entidad.getCntjExpedientesId().getNumeroExpediente());
            objeto.setExpedienteId(expediente);
        }
        objeto.setTipo((int) entidad.getTipo());
        objeto.setEstado((int) entidad.getEstado());
        objeto.setDescripcion(entidad.getDescripcion());
        objeto.setArea(entidad.getArea());
        objeto.setObservacion(entidad.getObservaciones()); 
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        objeto.setUsuarioModifica(entidad.getUsuarioModifica());
        objeto.setFechaHoraModifica(entidad.getFechaHoraModifica());
        objeto.setTerminalModifica(entidad.getTerminalModifica());
        return objeto;
    }
    
    private CntjLineas castNegocioEntidad(CntjLinea obj){
        CntjLineas ent = new CntjLineas();
        ent.setCntjComitesId(new CntjComites(obj.getCntjComiteId().getId()));
        ent.setGnUsuariosId(new GnUsuarios(obj.getUsuariosId().getId()));
        if(obj.getExpedienteId().getId() != null){
            ent.setCntjExpedientesId(new CntjExpedientes(obj.getExpedienteId().getId()));
        }        
        ent.setTipo(obj.getTipo().shortValue());
        ent.setEstado(obj.getEstado().shortValue());
        ent.setDescripcion(obj.getDescripcion());
        ent.setArea(obj.getArea());
        ent.setObservaciones(obj.getObservacion());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }

    
}
