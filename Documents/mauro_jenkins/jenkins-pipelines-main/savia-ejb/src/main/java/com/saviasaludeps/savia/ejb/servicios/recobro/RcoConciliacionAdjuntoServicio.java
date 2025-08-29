package com.saviasaludeps.savia.ejb.servicios.recobro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.recobro.RcoConciliacion;
import com.saviasaludeps.savia.dominio.recobro.RcoConciliacionAdjunto;
import com.saviasaludeps.savia.ejb.entidades.RcoConciliacionAdjuntos;
import com.saviasaludeps.savia.ejb.entidades.RcoConciliaciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.recobro.RcoConciliacionAdjuntoRemoto;
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
 * @author Stiven Giraldo
 */
@Stateless
@Remote(RcoConciliacionAdjuntoRemoto.class)
public class RcoConciliacionAdjuntoServicio extends GenericoServicio implements RcoConciliacionAdjuntoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
         int cant = 0;
        try {
            String strQuery = "SELECT COUNT(m) FROM RcoConciliacionAdjuntos m ";           
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND m.id = " + (String) e.getValue() + " ";
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
    public List<RcoConciliacionAdjunto> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<RcoConciliacionAdjunto> listResult = new ArrayList();
        try {
            String strQuery = "SELECT m FROM RcoConciliacionAdjuntos m ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND m.id = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "m." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "m.id ASC ";
            }
            List<RcoConciliacionAdjuntos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (RcoConciliacionAdjuntos per : list) {
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
    public List<RcoConciliacionAdjunto> consultarListaPorConciliacion(int conciliacionId) throws Exception {
        List<RcoConciliacionAdjunto> listResult = new ArrayList();
        try {
            String strQuery = "SELECT a FROM RcoConciliacionAdjuntos a "
                    + "INNER JOIN RcoConciliaciones rcoc ON a.rcoConciliacionesId = rcoc.id "
                    + "WHERE rcoc.id = :conciliacionId "
                    + "AND a.existe = 1"
                    + "ORDER BY a.fechaHoraCrea DESC";
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("conciliacionId", conciliacionId);
            List<RcoConciliacionAdjuntos> list = query.getResultList();
            for (RcoConciliacionAdjuntos per : list) {
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
    public RcoConciliacionAdjunto consultar(int id) throws Exception {
       RcoConciliacionAdjunto objRes = null;
        try {
            objRes = castEntidadNegocio((RcoConciliacionAdjuntos) getEntityManager().find(RcoConciliacionAdjuntos.class, id));
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
    public int insertar(RcoConciliacionAdjunto obj) throws Exception {
          int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "La combinación nombre, tipo, valor ya existe.");
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    @Override
    public void actualizar(RcoConciliacionAdjunto obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE RcoConciliacionAdjuntos a SET ";
            strQuery += "a.nombreArchivo = :nombreArchivo ,";
            strQuery += "a.ruta = :ruta ,";
            strQuery += "a.archivo = :archivo ,";
            strQuery += "a.existe = :existe ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("nombreArchivo", obj.getNombreArchivo());
            query.setParameter("ruta", obj.getRuta());
            query.setParameter("archivo", obj.getArchivo());
            query.setParameter("existe", obj.isExiste());            
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
    public RcoConciliacionAdjunto eliminar(int id) throws Exception {
         RcoConciliacionAdjunto obj = null;
        try {
            RcoConciliacionAdjuntos ent = getEntityManager().find(RcoConciliacionAdjuntos.class, id);
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

    private RcoConciliacionAdjunto castEntidadNegocio(RcoConciliacionAdjuntos entidad) {
        RcoConciliacionAdjunto negocio = new RcoConciliacionAdjunto();
        negocio.setId(entidad.getId());
        if(entidad.getRcoConciliacionesId() != null){
            negocio.setRcoConciliacionId(new RcoConciliacion(entidad.getRcoConciliacionesId().getId()));
        }
        negocio.setMaeTipoArchivoId(entidad.getMaeTipoArchivoId());
        negocio.setMaeTipoArchivoCodigo(entidad.getMaeTipoArchivoCodigo());
        negocio.setMaeTipoArchivoValor(entidad.getMaeTipoArchivoValor());
        negocio.setTipo(entidad.getTipo());
        negocio.setNombreArchivo(entidad.getNombreArchivo());
        negocio.setRuta(entidad.getRuta());
        negocio.setArchivo(entidad.getArchivo());
        negocio.setExiste(entidad.getExiste());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;        
    }
    
    private RcoConciliacionAdjuntos castNegocioEntidad(RcoConciliacionAdjunto negocio) {
        RcoConciliacionAdjuntos entidad = new RcoConciliacionAdjuntos();
        entidad.setId(negocio.getId());
        if(negocio.getRcoConciliacionId() != null){
            entidad.setRcoConciliacionesId(new RcoConciliaciones(negocio.getRcoConciliacionId().getId()));
        }
        entidad.setMaeTipoArchivoId(negocio.getMaeTipoArchivoId());
        entidad.setMaeTipoArchivoCodigo(negocio.getMaeTipoArchivoCodigo());
        entidad.setMaeTipoArchivoValor(negocio.getMaeTipoArchivoValor());
        entidad.setTipo(negocio.getTipo());
        entidad.setNombreArchivo(negocio.getNombreArchivo());
        entidad.setRuta(negocio.getRuta());
        entidad.setArchivo(negocio.getArchivo());
        entidad.setExiste(negocio.isExiste());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;        
    }
    
}
