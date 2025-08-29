
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.especial.PeCarga;
import com.saviasaludeps.savia.dominio.especial.PeCargaGestion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.entidades.PeCargas;
import com.saviasaludeps.savia.ejb.entidades.PeCargasGestiones;
import static com.saviasaludeps.savia.ejb.servicios.especial.PeCargaMasivaServicio.castEntidadNegocio;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.especial.PeCargaGestionRemoto;
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
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(PeCargaGestionRemoto.class)
@Local(PeCargaGestionLocal.class)
public class PeCargaGestionServicio extends GenericoServicio implements PeCargaGestionRemoto, PeCargaGestionLocal {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM PeCargasGestiones p ";
            strQuery += "WHERE p.id > 0 ";
           
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "archivo":
                            strQuery += "AND p.archivo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND p.fechaHoraInicio = '" + e.getValue().toString() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea like '%" + (String) e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += "AND p.tipo in (" + e.getValue() + ") ";
                            break;
                        case "detalle":
                            strQuery += "AND p.detalle = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            Query query = getEntityManager().createQuery(strQuery);
            cant = (int) (long) query.getSingleResult();
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
    public List<PeCargaGestion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<PeCargaGestion> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM PeCargasGestiones p ";
            strQuery += " WHERE p.id > 0 ";
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "archivo":
                            strQuery += "AND p.archivo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND p.fechaHoraInicio = '" + e.getValue().toString() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea like '%" + (String) e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += "AND p.tipo in (" + e.getValue() + ") ";
                            break;
                        case "detalle":
                            strQuery += "AND p.detalle = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    case "programa":
                        strQuery += "p.peProgramasId.id "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    default:
                        strQuery += "p." + paramConsulta.getOrden() + " "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                }
            } else {
                strQuery += "p.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
            query.setFirstResult(paramConsulta.getPrimerRegistro());
            query.setMaxResults(paramConsulta.getRegistrosPagina());
            List<PeCargasGestiones> list = query.getResultList();
            for (PeCargasGestiones per : list) {
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
    public int insertar(PeCargaGestion obj) throws Exception {
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
    public void actualizar(PeCargaGestion obj) throws Exception {
        try {
            String sql = "UPDATE PeCargasGestiones SET "
                    + "estado = :estado, "
                    + "registros = :registros, "
                    + "exitosos = :exitosos, "
                    + "fallidos = :fallidos, "
                    + "detalle = :detalle, "
                    + "respExiste = :respExiste, "
                    + "fechaHoraFin = :fechaHoraFin "
                    + "WHERE id = :id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("registros", obj.getRegistros());
            query.setParameter("exitosos", obj.getExitosos());
            query.setParameter("fallidos", obj.getFallidos());
            query.setParameter("detalle", obj.getDetalle());
            query.setParameter("respExiste", obj.getRespExiste());
            query.setParameter("fechaHoraFin", obj.getFechaHoraFin());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public PeCargaGestion consultar(int id) throws Exception {
        PeCargaGestion objResult = new PeCargaGestion();
        try {
            objResult = castEntidadNegocio((PeCargasGestiones) getEntityManager().find(PeCargasGestiones.class, id));
        } catch (NoResultException e) {
            objResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return objResult;
    }

    @Override
    public void actualizarArchivo(PeCargaGestion obj) throws Exception {
        try {
            String sql = "UPDATE PeCargasGestiones "
                    + "SET archivo = :archivo "
                    + "WHERE id = :id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("archivo", obj.getArchivo());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public PeCargaGestion consultarCargasSiguientes()  {
        PeCargaGestion resultado = null;
        try {
            String strQuery = "SELECT p FROM PeCargasGestiones p WHERE p.id > 0 AND p.estado = 0 ";            
            Query query = getEntityManager().createQuery(strQuery).setMaxResults(1);  
            PeCargasGestiones res = (PeCargasGestiones) query.getSingleResult();
            resultado = castEntidadNegocio(res);
        } catch (NoResultException e) {
            resultado = null;
        } finally {
            cerrarEntityManager();
        }
        return resultado;
    }

    public static PeCargaGestion castEntidadNegocio(PeCargasGestiones ent) {
        PeCargaGestion obj = new PeCargaGestion();
        obj.setId(ent.getId());
        obj.setNombre(ent.getNombre());
        obj.setArchivo(ent.getArchivo());
        obj.setRuta(ent.getRuta());
        obj.setRegistros(ent.getRegistros());
        obj.setDetalle(ent.getDetalle());
        obj.setEstado(ent.getEstado());
        obj.setExitosos(ent.getExitosos());
        obj.setFallidos(ent.getFallidos());
        obj.setFechaHoraInicio(ent.getFechaHoraInicio());
        obj.setFechaHoraFin(ent.getFechaHoraFin());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());   
        if(ent.getGnUsuariosId() != null){
            obj.setUsuario(new Usuario(ent.getGnUsuariosId().getId()));
        }        
        obj.setExiste(ent.getExiste());
        obj.setRespRuta(ent.getRespRuta());
        obj.setRespNombre(ent.getRespNombre());
        obj.setRespExiste(ent.getRespExiste());
        obj.setRespArchivo(ent.getRespArchivo());
        return obj;
    }

    public static PeCargasGestiones castNegocioEntidad(PeCargaGestion obj) {
        PeCargasGestiones ent = new PeCargasGestiones();
        ent.setId(obj.getId());
        ent.setNombre(obj.getNombre());
        ent.setArchivo(obj.getArchivo());
        ent.setRuta(obj.getRuta());
        ent.setRegistros(obj.getRegistros());
        ent.setDetalle(obj.getDetalle());
        ent.setEstado(obj.getEstado());
        ent.setExitosos(obj.getExitosos());
        ent.setFallidos(obj.getFallidos());
        ent.setGnUsuariosId(new GnUsuarios(obj.getUsuario().getId()));
        ent.setFechaHoraInicio(obj.getFechaHoraInicio());
        ent.setFechaHoraFin(obj.getFechaHoraFin());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setExiste(obj.getExiste());
        ent.setRespRuta(obj.getRespRuta());
        ent.setRespNombre(obj.getRespNombre());
        ent.setRespExiste(obj.getRespExiste());
        ent.setRespArchivo(obj.getRespArchivo());
        return ent;
    }

}
