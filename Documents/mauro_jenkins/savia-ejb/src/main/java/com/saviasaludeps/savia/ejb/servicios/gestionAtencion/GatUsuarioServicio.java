/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.gestionAtencion;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatUsuario;
import com.saviasaludeps.savia.ejb.entidades.GatUsuarios;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatUsuarioRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author acuartas
 */
@Stateless
@Remote(GatUsuarioRemoto.class)
public class GatUsuarioServicio extends GenericoServicio implements GatUsuarioRemoto{

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GatUsuarios c "
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
    public List<GatUsuario> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GatUsuario> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM GatUsuarios c WHERE 1 = 1 ";
            
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
            List<GatUsuarios> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GatUsuarios sedeTaquilla : list) {
                listaResultados.add(castEntidadNegocio(sedeTaquilla));
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
    public GatUsuario consultar(int id) throws Exception {
        GatUsuario objRes = null;
        try {
            objRes = castEntidadNegocio((GatUsuarios) getEntityManager().find(GatUsuarios.class, id));
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
    public int insertar(GatUsuario obj) throws Exception {
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
    public void actualizar(GatUsuario obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GatUsuarios a SET ";
            strQuery += "a.asegAfiliadosId.id = :asegAfiliadosId ,";
            strQuery += "a.maeTipoDocumentoId = :maeTipoDocumentoId ,";
            strQuery += "a.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo ,";
            strQuery += "a.maeTipoDocumentoValor = :maeTipoDocumentoValor ,";
            strQuery += "a.numeroDocumento = :numeroDocumento ,";
            strQuery += "a.nombres = :nombres ,";
            strQuery += "a.apellidos = :apellidos, ";
            strQuery += "a.fechaNacimiento = :fechaNacimiento ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("asegAfiliadosId", obj.getAsegAfiliado().getId());
            query.setParameter("maeTipoDocumentoId", obj.getMaeTipoDocumentoId());
            query.setParameter("maeTipoDocumentoCodigo", obj.getMaeTipoDocumentoCodigo());
            query.setParameter("maeTipoDocumentoValor", obj.getMaeTipoDocumentoValor());
            query.setParameter("numeroDocumento", obj.getNumeroDocumento());
            query.setParameter("nombres", obj.getNombres());
            query.setParameter("apellidos", obj.getApellidos());
            query.setParameter("fechaNacimiento", obj.getFechaNacimiento());
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
    public GatUsuario eliminar(int id) throws java.lang.Exception {
        GatUsuario obj = null;
        try {
            GatUsuarios ent = getEntityManager().find(GatUsuarios.class, id);
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
    
    private GatUsuario castEntidadNegocio(GatUsuarios entidad) {
        GatUsuario negocio = new GatUsuario();
        if (entidad.getAsegAfiliadosId() != null) {
            negocio.setAsegAfiliado(new AsegAfiliado(entidad.getAsegAfiliadosId().getId()));
        }        
        negocio.setMaeTipoDocumentoId(entidad.getMaeTipoDocumentoId());
        negocio.setMaeTipoDocumentoCodigo(entidad.getMaeTipoDocumentoCodigo());
        negocio.setMaeTipoDocumentoValor(entidad.getMaeTipoDocumentoValor());
        negocio.setNumeroDocumento(entidad.getNumeroDocumento());
        negocio.setNombres(entidad.getNombres());
        negocio.setApellidos(entidad.getApellidos());
        negocio.setFechaNacimiento(entidad.getFechaNacimiento());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }
    
    private GatUsuarios castNegocioEntidad(GatUsuario negocio) {
        GatUsuarios entidad = new GatUsuarios();
        if (negocio.getAsegAfiliado() != null) {
            entidad.setAsegAfiliadosId(new AsegAfiliados(negocio.getAsegAfiliado().getId()));
        }        
        entidad.setMaeTipoDocumentoId(negocio.getMaeTipoDocumentoId());
        entidad.setMaeTipoDocumentoCodigo(negocio.getMaeTipoDocumentoCodigo());
        entidad.setMaeTipoDocumentoValor(negocio.getMaeTipoDocumentoValor());
        entidad.setNumeroDocumento(negocio.getNumeroDocumento());
        entidad.setNombres(negocio.getNombres());
        entidad.setApellidos(negocio.getApellidos());
        entidad.setFechaNacimiento(negocio.getFechaNacimiento());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }
}
