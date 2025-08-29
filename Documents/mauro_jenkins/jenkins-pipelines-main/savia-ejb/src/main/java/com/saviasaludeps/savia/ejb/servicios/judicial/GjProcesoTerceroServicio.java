/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.judicial;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.judicial.GjProceso;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoTercero;
import com.saviasaludeps.savia.dominio.judicial.GjTercero;
import com.saviasaludeps.savia.ejb.entidades.GjProcesoTerceros;
import com.saviasaludeps.savia.ejb.entidades.GjProcesos;
import com.saviasaludeps.savia.ejb.entidades.GjTerceros;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.judicial.GjProcesoTerceroRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jperezn
 */
@Stateless
@Remote(GjProcesoTerceroRemoto.class)
public class GjProcesoTerceroServicio extends GenericoServicio implements GjProcesoTerceroRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(t.id) FROM GjProcesoTerceros t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "xxx":
                            strQuery += "AND t.xxx LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "xx":
                            strQuery += "AND t.xx LIKE '" + (String) e.getValue() + "%' ";
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
    public List<GjProcesoTercero> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GjProcesoTercero> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t FROM GjProcesoTerceros t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "xxx":
                            strQuery += "AND t.xxx LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "xx":
                            strQuery += "AND t.xx LIKE '" + (String) e.getValue() + "%' ";
                            break;

                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "t." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "t.id ASC";
            }
            List<GjProcesoTerceros> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GjProcesoTerceros per : list) {
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

//    @Override
//    public List<GjProcesoTercero> consultarListaPorIdProceso(int idProceso) throws Exception {
//        List<GjProcesoTercero> listaResultados = new ArrayList();
//        try {
//            String strQuery = "FROM GjProcesoTerceros p "
//                    + "WHERE p.gjProcesosId.id = " + idProceso + " ";
//            strQuery += " ORDER BY ";
//            strQuery += " p.id DESC";
//            List<GjProcesoTerceros> list = getEntityManager().createQuery(strQuery)
//                    .getResultList();
//            for (GjProcesoTerceros usuarios : list) {
//                listaResultados.add(castEntidadNegocio(usuarios));
//            }
//        } catch (NoResultException e) {
//            listaResultados = new ArrayList();
//        } catch (Exception e) {
//            Exception(CONSULTAR_TODOS, e);
//        } finally {
//            cerrarEntityManager();
//        }
//        return listaResultados;
//    }
    
    @Override
public List<GjProcesoTercero> consultarListaPorIdProceso(int idProceso) throws Exception {
    List<GjProcesoTercero> listaResultados = new ArrayList<>();
    try {
        String strQuery = "FROM GjProcesoTerceros p "
                + "WHERE p.gjProcesosId.id = :idProceso "
                + "ORDER BY p.id DESC";
        List<GjProcesoTerceros> list = getEntityManager()
                .createQuery(strQuery, GjProcesoTerceros.class)
                .setParameter("idProceso", idProceso)
                .getResultList();
        for (GjProcesoTerceros usuarios : list) {
            listaResultados.add(castEntidadNegocio(usuarios));
        }
    } catch (NoResultException e) {
        listaResultados = new ArrayList<>();
    } catch (Exception e) {
        Exception(CONSULTAR_TODOS, e);
    }
    return listaResultados;
}


    @Override
    public GjProcesoTercero consultar(int id) throws Exception {
        GjProcesoTercero objRes = null;
        try {
            GjProcesoTerceros per = getEntityManager().find(GjProcesoTerceros.class, id);
            if (per != null) {
                objRes = castEntidadNegocio(per);
            }
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
    public int insertar(GjProcesoTercero obj) throws Exception {
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
    public void actualizar(GjProcesoTercero obj) throws Exception {
        try {
            String hql = "UPDATE GjProcesoTerceros SET "
                    
                   + " maeCalidadActuaId = :maeCalidadActuaId,"
                    + " maeCalidadActuaValor = :maeCalidadActuaValor,"
                    + " maeCalidadActuaCodigo = :maeCalidadActuaCodigo,"
                    + " maeTipoDocumentoId = :maeTipoDocumentoId,"
                    + " maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo,"
                    + " maeTipoDocumentoValor = :maeTipoDocumentoValor,"
                    + " documento = :documento,"
                    + " nombres = :nombres,"
                    + " apellidos = :apellidos,"
                    + " razonSocial = :razonSocial,"
                    + " telefono = :telefono,"
                    + " gjProcesosId.id = :gjProcesosId,"
                    + " gjTercerosId.id = :gjTercerosId,"
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica ";
            hql += " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            
            query.setParameter("maeCalidadActuaId", obj.getMaeCalidadActuaId());
            query.setParameter("maeCalidadActuaValor", obj.getMaeCalidadActuaValor());
            query.setParameter("maeCalidadActuaCodigo", obj.getMaeCalidadActuaCodigo());
            query.setParameter("maeTipoDocumentoId", obj.getMaeTipoDocumentorepreId());
            query.setParameter("maeTipoDocumentoCodigo", obj.getMaeTipoDocumentoCodigo());
            query.setParameter("maeTipoDocumentoValor", obj.getMaeTipoDocumentoValor());
            query.setParameter("documento", obj.getDocumento());
            query.setParameter("nombres", obj.getNombres());
            query.setParameter("apellidos", obj.getApellidos());
            query.setParameter("razonSocial", obj.getRazonSocial());
            query.setParameter("telefono", obj.getTelefono());
            query.setParameter("gjProcesosId", obj.getGjProcesosId().getId());
            query.setParameter("gjTercerosId", obj.getGjTercerosId().getId());
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
    public GjProcesoTercero eliminar(int id) throws Exception {
        GjProcesoTercero obj = null;
        try {
            GjProcesoTerceros ent = getEntityManager().find(GjProcesoTerceros.class, id);
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

    public static GjProcesoTercero castEntidadNegocio(GjProcesoTerceros per) {
        GjProcesoTercero obj = new GjProcesoTercero();
        obj.setId(per.getId());
        if (per.getGjProcesosId() != null) {
            obj.setGjProcesosId(new GjProceso(per.getGjProcesosId().getId()));
        }
        
        if (per.getGjTercerosId() != null) {
           
            GjTercero tercero = new GjTercero();
            tercero.setId(per.getGjTercerosId().getId());
            tercero.setTipo(per.getGjTercerosId().getTipo());
            tercero.setMaeTipoDocumentoId(per.getGjTercerosId().getMaeTipoDocumentoId());
            tercero.setMaeTipoDocumentoCodigo(per.getGjTercerosId().getMaeTipoDocumentoCodigo());
            tercero.setMaeTipoDocumentoValor(per.getGjTercerosId().getMaeTipoDocumentoValor());
            tercero.setDocumento(per.getGjTercerosId().getDocumento());
            tercero.setNombres(per.getGjTercerosId().getNombres());
            tercero.setApellidos(per.getGjTercerosId().getApellidos());
            tercero.setTelefono(per.getGjTercerosId().getTelefono());
            tercero.setCorreoElectronico(per.getGjTercerosId().getCorreoElectronico());
            obj.setGjTercerosId(tercero);
        }   
        obj.setMaeCalidadActuaId(per.getMaeCalidadActuaId());        
        obj.setMaeCalidadActuaValor(per.getMaeCalidadActuaValor());
        obj.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setDocumento(per.getDocumento());
        obj.setNombres(per.getNombres());
        obj.setApellidos(per.getApellidos());
        obj.setRazonSocial(per.getRazonSocial());
        obj.setTelefono(per.getTelefono());

        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        return obj;
    }

    public static GjProcesoTerceros castNegocioEntidad(GjProcesoTercero obj) {
        GjProcesoTerceros per = new GjProcesoTerceros();
        per.setId(obj.getId());

        if (obj.getGjProcesosId().getId() != null) {
            per.setGjProcesosId(new GjProcesos(obj.getGjProcesosId().getId()));
        }
        if (obj.getGjTercerosId() != null) {
            per.setGjTercerosId(new GjTerceros(obj.getGjTercerosId().getId()));
        }        
        per.setMaeCalidadActuaId(obj.getMaeCalidadActuaId());
        per.setMaeCalidadActuaCodigo(obj.getMaeCalidadActuaCodigo());
        per.setMaeCalidadActuaValor(obj.getMaeCalidadActuaValor());

        per.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
        per.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
        per.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
        per.setDocumento(obj.getDocumento());
        per.setNombres(obj.getNombres());
        per.setApellidos(obj.getApellidos());
        per.setRazonSocial(obj.getRazonSocial());
        per.setTelefono(obj.getTelefono());

        //Auditoría
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        return per;
    }
}
