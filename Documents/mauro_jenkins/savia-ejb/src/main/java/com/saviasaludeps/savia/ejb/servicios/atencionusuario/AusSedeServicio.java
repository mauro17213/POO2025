/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.atencionusuario;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.atencionusuario.AusSede;
import com.saviasaludeps.savia.ejb.entidades.GnSedes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.atencionusuario.AusSedeRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(AusSedeRemoto.class)
public class AusSedeServicio extends GenericoServicio implements AusSedeRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(s) FROM GnSedes s "
                    + "WHERE s.gnEmpresasId.id = :empresa_id ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND s.nombre LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "ubicacion.id":
                            //Ubicacion ubi = (Ubicacion) e.getValue();
                            strQuery += "AND s.gnUbicacionesId.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public List<AusSede> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AusSede> listResult = new ArrayList();
        try {
            String strQuery = "FROM GnSedes s "
                    + "WHERE s.gnEmpresasId.id = :empresa_id ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND s.nombre LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "ubicacion.id":
                            //Ubicacion ubi = (Ubicacion) e.getValue();
                            //strQuery += "AND s.gnUbicacionesId.id = " + ubi.getId() + " ";
                            strQuery += "AND s.gnUbicacionesId.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "s." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "s.nombre ASC";
            }
            List<GnSedes> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (GnSedes per : list) {
                listResult.add(castEntidadNegocio(per));
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
    public List<AusSede> consultarListaPorUbicacion(Integer idUbicacion, Integer idempresa) throws Exception {
        List<AusSede> listResult = new ArrayList();
        try {
            String strQuery = "SELECT pt FROM GnSedes pt "
                    + "WHERE pt.gnEmpresasId.id = :empresa "
                    + "AND pt.gnUbicacionesId.id = :idUbicacion "
                    + "AND pt.activo = 1 "
                    + "ORDER BY pt.nombre ASC";
                  
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("empresa", idempresa);
            query.setParameter("idUbicacion", idUbicacion);
            List<GnSedes> list = query.getResultList();
            for (GnSedes per : list) {
                listResult.add(castEntidadNegocio(per));
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
    public AusSede consultar(int id) throws Exception {
        AusSede objRes = null;
        try {
            objRes = castEntidadNegocio((GnSedes) getEntityManager().find(GnSedes.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    public static AusSede castEntidadNegocio(GnSedes per){
        AusSede obj = new AusSede();
        obj.setEmpresasId(new Empresa(per.getGnEmpresasId().getId()));
        obj.setId(per.getId());
        obj.setNombre(per.getNombre());
        obj.setDescripcion(per.getDescripcion());
        obj.setDireccion(per.getDireccion());
        obj.setTelefono(per.getTelefono());
        obj.setUbicacionesId(new Ubicacion(per.getGnUbicacionesId().getId()));
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }    
    
    
}
