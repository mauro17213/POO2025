/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.tutela;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuRepresentante;
import com.saviasaludeps.savia.ejb.entidades.TuRepresentantes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.tutela.TuRepresentanteRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author pavacca
 */
@Stateless
@Remote(TuRepresentanteRemoto.class)
@Local(TuRepresentanteLocal.class)
public class TuRepresentanteServicio extends GenericoServicio implements TuRepresentanteLocal, TuRepresentanteRemoto{
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(t.id) FROM TuRepresentantes t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND t.id = " + (String) e.getValue() + " ";
                            break;
                        case "nombre":
                            strQuery += "AND t.nombre LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            String convertir = (e.getValue().equals("true")) ? "1": "0";
                            strQuery += "AND t.activo = " + convertir + " ";
                            break;
                    }
                }
            }
            //strQuery += "AND t.activo = 1 ";
            
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //                    .setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public List<TuRepresentante> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<TuRepresentante> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t FROM TuRepresentantes t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND t.id = " + (String) e.getValue() + " ";
                            break;
                        case "nombre":
                            strQuery += "AND t.nombre LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            String convertir = (e.getValue().equals("true")) ? "1": "0";
                            strQuery += "AND t.activo = " + convertir + " ";
                            break;
                    }
                }
            }
            //strQuery += "AND t.activo = 1 ";
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "t." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "t.id ASC";
            }
            List<TuRepresentantes> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //                    .setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (TuRepresentantes per : list) {
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
    public List<TuRepresentante> consultarRepresentantesActivos() throws Exception {
        List<TuRepresentante> listaResultados = new ArrayList();
        try {
              String strQuery = "SELECT t FROM TuRepresentantes t "
                    + "WHERE t.id > 0 "
                    + "AND t.activo = 1 "
                    + "ORDER BY t.id DESC";

            List<TuRepresentantes> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (TuRepresentantes seguimiento : list) {
                listaResultados.add(castEntidadNegocio(seguimiento));
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
    public TuRepresentante consultar(int id) throws Exception {
        TuRepresentante objRes = null;
        try {
            objRes = castEntidadNegocio((TuRepresentantes) getEntityManager().find(TuRepresentantes.class, id));
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
    public int insertar(TuRepresentante obj) throws Exception {
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
    public void actualizar(TuRepresentante obj) throws Exception {
        try {
            String hql = "UPDATE TuRepresentantes SET"
                    + ((obj.getNombre() != null) ? " nombre = :nombre," : "")
                    + " activo = :activo,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            if (obj.getNombre() != null) {
                query.setParameter("nombre", obj.getNombre());
            }
            query.setParameter("activo", obj.isActivo());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public TuRepresentante eliminar(int id) throws Exception {
        TuRepresentante obj = null;
        try {
            TuRepresentantes ent = getEntityManager().find(TuRepresentantes.class, id);
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
    
    public static TuRepresentantes castNegocioEntidad(TuRepresentante obj) {
        TuRepresentantes per = new TuRepresentantes();
        per.setId(obj.getId());
        per.setNombre(obj.getNombre());
        per.setActivo(obj.isActivo());
       
        //Auditoría
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }
    
    public static TuRepresentante castEntidadNegocio(TuRepresentantes per) {
        TuRepresentante obj = new TuRepresentante();
        obj.setId(per.getId());
        obj.setNombre(per.getNombre());
        obj.setActivo(per.getActivo());
       
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
