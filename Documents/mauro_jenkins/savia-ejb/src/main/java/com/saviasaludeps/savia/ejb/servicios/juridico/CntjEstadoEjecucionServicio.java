package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjDocumento;
import com.saviasaludeps.savia.dominio.juridico.CntjEstado;
import com.saviasaludeps.savia.dominio.juridico.CntjEstadoEjecucion;
import com.saviasaludeps.savia.dominio.juridico.CntjExpediente;
import com.saviasaludeps.savia.dominio.juridico.CntjLinea;
import com.saviasaludeps.savia.dominio.juridico.CntjTransicion;
import com.saviasaludeps.savia.ejb.entidades.CntjDocumentos;
import com.saviasaludeps.savia.ejb.entidades.CntjEstadoEjecuciones;
import com.saviasaludeps.savia.ejb.entidades.CntjEstados;
import com.saviasaludeps.savia.ejb.entidades.CntjExpedientes;
import com.saviasaludeps.savia.ejb.entidades.CntjLineas;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CntjEstadoEjecucionRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author Chass
 */
@Stateless
@Remote(CntjEstadoEjecucionRemoto.class)
public class CntjEstadoEjecucionServicio extends GenericoServicio implements CntjEstadoEjecucionRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjEstadoEjecuciones c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append(" AND id = " +  e.getValue() + " ");
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
    public List<CntjEstadoEjecucion> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntjEstadoEjecucion> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT p FROM CntjEstadoEjecuciones p WHERE p.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append(" AND id = " +  e.getValue() + " ");
                            break;
                    }
                }
            }
            strQuery.append(" ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                strQuery.append(" p." + paramConsulta.getOrden() + " " + (paramConsulta.isAscendente() ? "ASC" : "DESC"));
            } else {
                strQuery.append(" p.id DESC ");
            }
            List<CntjEstadoEjecuciones> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjEstadoEjecuciones estadosEjecucion : list) {
                listResult.add(castEntidadNegocio(estadosEjecucion));
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
    public int insertar(CntjEstadoEjecucion objeto) throws java.lang.Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(objeto)).getId();
            objeto.setId(id);
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
    public CntjEstadoEjecucion consultar(int id) throws java.lang.Exception {
        CntjEstadoEjecucion objRes = null;
        try {
            objRes = castEntidadNegocio((CntjEstadoEjecuciones) getEntityManager().find(CntjEstadoEjecuciones.class, id));
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
    public CntjEstadoEjecucion eliminar(int id) throws Exception {
        CntjEstadoEjecucion obj = null;
        try {
            CntjEstadoEjecuciones ent = getEntityManager().find(CntjEstadoEjecuciones.class, id);
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
    

    @Override
    public List<CntjEstadoEjecucion> listaEjecucionExpediente(int idExpediente) throws java.lang.Exception {
        List<CntjEstadoEjecucion> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT p FROM CntjEstadoEjecuciones p WHERE p.id > 0 and cntjExpedientesId.id = ").append(idExpediente);            
            List<CntjEstadoEjecuciones> list = getEntityManager().createQuery(strQuery.toString()).getResultList();
            for (CntjEstadoEjecuciones estadosEjecucion : list) {
                listResult.add(castEntidadNegocio(estadosEjecucion));
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
    
    
    private CntjEstadoEjecucion castEntidadNegocio(CntjEstadoEjecuciones entidad) {
        CntjEstadoEjecucion objeto = new CntjEstadoEjecucion();
        objeto.setId(entidad.getId());
        objeto.setCntjExpedienteId(new CntjExpediente(entidad.getCntjExpedientesId().getId()));
        CntjEstado estado = new CntjEstado(entidad.getCntjEstadosId().getId());
        estado.setNombre(entidad.getCntjEstadosId().getNombre());
        if(entidad.getCntjEstadosId().getCntjTransicionesId() != null){
            CntjTransicion transicion = new CntjTransicion(entidad.getCntjEstadosId().getCntjTransicionesId().getId());
            transicion.setNombre(entidad.getCntjEstadosId().getCntjTransicionesId().getNombre());
            estado.setCntjTransicionId(transicion);
        }        
        objeto.setCntjEstadoId(estado);
        if(entidad.getGnUsuariosId() != null){
            Usuario usuario = new Usuario(entidad.getGnUsuariosId().getId());
            usuario.setNombre(entidad.getGnUsuariosId().getNombre());
            objeto.setGnUsuariosId(usuario);
        }
        if(entidad.getCntjLineasId() != null){
            objeto.setCntjLineaId(new CntjLinea(entidad.getCntjLineasId().getId()));
        }
        if(entidad.getCntjDocumentosId() != null){
            objeto.setCntjDocumentoId(new CntjDocumento(entidad.getCntjDocumentosId().getId()));
        }     
        objeto.setObservacion(entidad.getObservacion());
        objeto.setFechaEjecucion(entidad.getFechaEjecucion());
        
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        objeto.setUsuarioModifica(entidad.getUsuarioModifica());
        objeto.setFechaHoraModifica(entidad.getFechaHoraModifica());
        objeto.setTerminalModifica(entidad.getTerminalModifica());
        return objeto;
    }

    private CntjEstadoEjecuciones castNegocioEntidad(CntjEstadoEjecucion obj) {
        CntjEstadoEjecuciones ent = new CntjEstadoEjecuciones();
        ent.setCntjExpedientesId(new CntjExpedientes(obj.getCntjExpedienteId().getId()));
        ent.setCntjEstadosId(new CntjEstados(obj.getCntjEstadoId().getId()));
        if(obj.getGnUsuariosId() != null){
            ent.setGnUsuariosId(new GnUsuarios(obj.getGnUsuariosId().getId()));
        }
        if(obj.getCntjLineaId() != null){
            ent.setCntjLineasId(new CntjLineas(obj.getCntjLineaId().getId()));
        }
        if(obj.getCntjDocumentoId() != null){
            ent.setCntjDocumentosId(new CntjDocumentos(obj.getCntjDocumentoId().getId()));
        }  
        ent.setObservacion(obj.getObservacion());
        ent.setFechaEjecucion(obj.getFechaEjecucion());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }
    
    
    
}
