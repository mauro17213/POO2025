package com.saviasaludeps.savia.ejb.servicios.recobro;

import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.recobro.RcoConciliacion;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.RcoConciliaciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.recobro.RcoConciliacionRemoto;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Remote(RcoConciliacionRemoto.class)
public class RcoConciliacionServicio extends GenericoServicio implements RcoConciliacionRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(u) FROM RcoConciliaciones u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder(); 
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND u.id = ").append(e.getValue()).append(" ");
                            break;
                        case "nombre":
                            strQuery.append("AND u.nombre LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "estado":
                            strQuery.append("AND u.estado = ").append(e.getValue()).append(" ");
                            break;
                        case "cntPresadoresSedesId.nombreSede":
                            strTitulo = agregarJoin("INNER JOIN CntPrestadorSedes cntp ON u.cntPresadoresSedesId = cntp.id ", strTitulo);
                            strQuery.append("AND cntp.nombre LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "usuarioCrea":
                            strQuery.append("AND u.usuarioCrea LIKE '%").append(e.getValue()).append("%' ");
                            break;
                    }
                }
            }
            sql.append(strTitulo).append(strQuery);
            cant = (int) (long) getEntityManager().createQuery(sql.toString())
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
    public List<RcoConciliacion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<RcoConciliacion> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM RcoConciliaciones u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND u.id = ").append(e.getValue()).append(" ");
                            break;
                        case "nombre":
                            strQuery.append("AND u.nombre LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "estado":
                            strQuery.append("AND u.estado = ").append(e.getValue()).append(" ");
                            break;
                        case "cntPresadoresSedesId.nombreSede":
                            strTitulo = agregarJoin("INNER JOIN CntPrestadorSedes cntp ON u.cntPresadoresSedesId = cntp.id ", strTitulo);
                            strQuery.append("AND cntp.nombre LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "usuarioCrea":
                            strQuery.append("AND u.usuarioCrea LIKE '%").append(e.getValue()).append("%' ");
                            break;
                    }
                }
            }
            sql.append(strTitulo).append(strQuery);
            sql.append("ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                sql.append("u.").append(paramConsulta.getOrden()).append((paramConsulta.isAscendente() ? " ASC" : " DESC"));
            } else {
                sql.append("u.id DESC");
            }
            List<RcoConciliaciones> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (RcoConciliaciones per : list) {
                listResult.add(castEntidadNegocioCorto(per));
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
    
    @SuppressWarnings("UnusedAssignment")
    private String agregarJoin(String join, String sql) {
        if (sql.contains(join)) {
            return sql;
        } else {
            return sql += join;
        }
    }
   
    @Override
    public RcoConciliacion consultar(int id) throws Exception {
        RcoConciliacion objRes = null;
        try {
            objRes = castEntidadNegocioLargo((RcoConciliaciones) getEntityManager().find(RcoConciliaciones.class, id));
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
    public int insertar(RcoConciliacion obj) throws Exception {
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
    public void actualizar(RcoConciliacion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE RcoConciliaciones a SET ";
            if (obj.getCntPresadoresSedesId().getId() != null) {
                strQuery += "a.cntPresadoresSedesId.id = :cntPresadoresSedesId ,";
            }
            strQuery += "a.nombre = :nombre ";
            strQuery += "a.descripcion = :descripcion ";
            strQuery += "a.estado = :estado ,";
            strQuery += "a.numeroContrato = :numeroContrato , ";
            strQuery += "a.cntContratoId = :cntContratoId  ,";
            strQuery += "a.fechaInicio = :fechaInicio , ";
            strQuery += "a.fechaFin = :fechaFin ,";
            strQuery += "a.correoEnvio = :correoEnvio ,";
            strQuery += "a.cantidadItems = :cantidadItems ,";
            strQuery += "a.cantidadItemsRecobrados = :cantidadItemsRecobrados ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";

            Query query = session.createQuery(strQuery);
            if (obj.getCntPresadoresSedesId().getId() != null) {
                query.setParameter("cntPresadoresSedesId", obj.getCntPresadoresSedesId());
            }
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("descripcion", obj.getDescripcion());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("numeroContrato", obj.getNumeroContrato());
            query.setParameter("cntContratoId", obj.getCntContratoId());
            query.setParameter("fechaInicio", obj.getFechaInicio());
            query.setParameter("fechaFin", obj.getFechaFin());
            query.setParameter("correoEnvio", obj.getCorreoEnvio());
            query.setParameter("cantidadItems", obj.getCantidadItems());
            query.setParameter("cantidadItemsRecobrados", obj.getCantidadItemsRecobrados());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
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
    public void actualizarEstadoEnvio(RcoConciliacion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE RcoConciliaciones a SET ";
            strQuery += "a.estado = :estado ,";
            strQuery += "a.correoEnvio = :correoEnvio ,";
            //strQuery += "a.cantidadItems = :cantidadItems ,";
            //strQuery += "a.cantidadItemsRecobrados = :cantidadItemsRecobrados ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";

            Query query = session.createQuery(strQuery);
            
            query.setParameter("estado", obj.getEstado());
            query.setParameter("correoEnvio", obj.getCorreoEnvio());
            //query.setParameter("cantidadItems", obj.getCantidadItems());
            //query.setParameter("cantidadItemsRecobrados", obj.getCantidadItemsRecobrados());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
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
    public void actualizarEstado(RcoConciliacion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE RcoConciliaciones a SET ";
            strQuery += "a.estado = :estado ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";

            Query query = session.createQuery(strQuery);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
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
    public void actualizarValorTotalRecobro(RcoConciliacion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE RcoConciliaciones a SET ";
            strQuery += "a.valorTotalConciliado = :valorTotalConciliado ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";

            Query query = session.createQuery(strQuery);
            query.setParameter("valorTotalConciliado", obj.getValorTotalConciliado().longValue());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
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
    
    private RcoConciliacion castEntidadNegocioCorto(RcoConciliaciones entidad) {
        RcoConciliacion negocio = new RcoConciliacion();
        negocio.setId(entidad.getId());
        if(entidad.getCntPresadoresSedesId() != null){
            CntPrestadorSede cntPrestadorSede = new CntPrestadorSede();
            cntPrestadorSede.setId(entidad.getCntPresadoresSedesId().getId());
            cntPrestadorSede.setNombreSede(entidad.getCntPresadoresSedesId().getNombre());
            negocio.setCntPresadoresSedesId(cntPrestadorSede);
        }
        negocio.setNombre(entidad.getNombre());
        negocio.setEstado(entidad.getEstado());
        negocio.setFechaInicio(entidad.getFechaInicio());
        negocio.setFechaFin(entidad.getFechaFin());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }
    
    private RcoConciliacion castEntidadNegocioLargo(RcoConciliaciones entidad) {
        RcoConciliacion negocio = new RcoConciliacion();
        negocio.setId(entidad.getId());
        if(entidad.getCntPresadoresSedesId() != null){
            negocio.setCntPresadoresSedesId(new CntPrestadorSede(entidad.getCntPresadoresSedesId().getId()));
        }
        negocio.setNombre(entidad.getNombre());
        negocio.setDescripcion(entidad.getDescripcion());
        negocio.setEstado(entidad.getEstado());
        negocio.setNumeroContrato(entidad.getNumeroContrato());
        if(entidad.getCntContratoId() != null){
            negocio.setCntContratoId(new CntContrato(entidad.getCntContratoId()));
        }
        negocio.setFechaInicio(entidad.getFechaInicio());
        negocio.setFechaFin(entidad.getFechaFin());
        negocio.setCorreoEnvio(entidad.getCorreoEnvio());
        negocio.setCantidadItems(entidad.getCantidadItems());
        negocio.setCantidadItemsRecobrados(entidad.getCantidadItemsRecobrados());
        negocio.setValorTotalConciliado(entidad.getValorTotalConciliado());
        negocio.setValorRestanteNoConciliado(entidad.getValorRestanteNoConciliado());
        negocio.setValorRestanteNoConciliado(entidad.getValorConciliacion());
      
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }

    private RcoConciliaciones castNegocioEntidad(RcoConciliacion negocio) {
        RcoConciliaciones entidad = new RcoConciliaciones();
        entidad.setId(negocio.getId());
        if(negocio.getCntPresadoresSedesId() != null){
            entidad.setCntPresadoresSedesId(new CntPrestadorSedes(negocio.getCntPresadoresSedesId().getId()));
        }
        entidad.setNombre(negocio.getNombre());
        entidad.setDescripcion(negocio.getDescripcion());
        entidad.setEstado(negocio.getEstado());
        entidad.setNumeroContrato(negocio.getNumeroContrato());
        if(negocio.getCntContratoId() != null){
            entidad.setCntContratoId(negocio.getCntContratoId().getId());
        }
        entidad.setFechaInicio(negocio.getFechaInicio());
        entidad.setFechaFin(negocio.getFechaFin());
        entidad.setCorreoEnvio(negocio.getCorreoEnvio());
        entidad.setCantidadItems(negocio.getCantidadItems());
        entidad.setCantidadItemsRecobrados(negocio.getCantidadItemsRecobrados());
        entidad.setValorTotalConciliado(negocio.getValorTotalConciliado());
        entidad.setValorRestanteNoConciliado(negocio.getValorRestanteNoConciliado());
        entidad.setValorConciliacion(negocio.getValorConciliacion());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        return entidad;
    }

}
