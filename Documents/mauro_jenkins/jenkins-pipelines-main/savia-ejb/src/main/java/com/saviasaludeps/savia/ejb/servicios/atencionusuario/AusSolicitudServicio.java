/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.atencionusuario;

import com.saviasaludeps.savia.dominio.atencionusuario.AusCaso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.atencionusuario.AusSolicitud;
import com.saviasaludeps.savia.ejb.entidades.AusCasos;
import com.saviasaludeps.savia.ejb.entidades.AusSolicitudes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.atencionusuario.AusSolicitudRemoto;
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
 * @author jose-perez
 */
@Stateless
@Remote(AusSolicitudRemoto.class)
public class AusSolicitudServicio extends GenericoServicio implements AusSolicitudRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(s) FROM AusSolicitudes s "
                    + " WHERE s.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND s.id = " + e.getValue() + " ";
                            break;
                        case "maeTipoSolicitudId":
                            strQuery += "AND s.maeTipoSolicitudId = " + e.getValue() + " ";
                            break;
                        case "maeTipoDocumentoId":
                            strQuery += "AND s.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "numeroDocumento":
                            strQuery += "AND s.numeroDocumento LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += "AND s.descripcion  LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "nombres":
                            strQuery += "AND s.nombres LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "apellidos":
                            strQuery += "AND s.apellidos LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeEstadoSolicitudId":
                            strQuery += "AND s.maeEstadoSolicitudId = " + (String) e.getValue() + " ";
                            break;
                        case "ausCaso.id":
                            strQuery += "AND s.ausCasosId.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                        //.setParameter("gnEmpresasId", paramConsulta.getEmpresaId())
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
    public List<AusSolicitud> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AusSolicitud> listResult = new ArrayList();
        try {
            String strQuery = "FROM AusSolicitudes s "
                    + " WHERE s.id > 0 ";
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND s.id = " + e.getValue() + " ";
                            break;
                        case "maeTipoSolicitudId":
                            strQuery += "AND s.maeTipoSolicitudId = " + e.getValue() + " ";
                            break;
                        case "maeTipoDocumentoId":
                            strQuery += "AND s.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "numeroDocumento":
                            strQuery += "AND s.numeroDocumento LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += "AND s.descripcion  LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "nombres":
                            strQuery += "AND s.nombres LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "apellidos":
                            strQuery += "AND s.apellidos LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeEstadoSolicitudId":
                            strQuery += "AND s.maeEstadoSolicitudId = " + (String) e.getValue() + " ";
                            break;
                        case "ausCaso.id":
                            strQuery += "AND s.ausCasosId.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                if (paramConsulta.getOrden().equals("ausCaso.id")) {
                    strQuery += "s.ausCasosId.id "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else {
                strQuery += "s." + paramConsulta.getOrden() + " "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                }
            } else {
                strQuery += "s.id DESC";
            }
            List<AusSolicitudes> list = null;
                list = getEntityManager().createQuery(strQuery)
                        .setFirstResult(paramConsulta.getPrimerRegistro())
                        .setMaxResults(paramConsulta.getRegistrosPagina())
                        //.setParameter("gnEmpresasId", paramConsulta.getEmpresaId())
                        .getResultList();
            for (AusSolicitudes soli : list) {
                listResult.add(castEntidadNegocio(soli));
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
    public List<AusSolicitud> consultarTodos() throws Exception {
        List<AusSolicitud> listResult = new ArrayList();
        String strQuery = "FROM AusSolicitudes"
                + " ORDER BY id DESC ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<AusSolicitudes> list = query.getResultList();
            for (AusSolicitudes obj : list) {
                listResult.add(castEntidadNegocio(obj));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception("Consulta de registros", e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public int insertar(AusSolicitud caso) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(caso)).getId();
            caso.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al insertar una registro");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(AusSolicitud obj) throws Exception {
        try {
            AusSolicitudes per = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            String hql = "UPDATE AusSolicitudes s SET"
                    + " s.maeTipoSolicitudId = :maeTipoSolicitudId,"
                    + " s.maeTipoSolicitudCodigo = :maeTipoSolicitudCodigo,"
                    + " s.maeTipoSolicitudValor = :maeTipoSolicitudValor,"
                    + " s.nombres = :nombres,"
                    + " s.apellidos = :apellidos,"
                    + " s.maeTipoDocumentoId = :maeTipoDocumentoId,"
                    + " s.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo,"
                    + " s.maeTipoDocumentoValor = :maeTipoDocumentoValor,"
                    + " s.numeroDocumento = :numeroDocumento,"
                    + " s.email = :email,"
                    + " s.detalleEmail = :detalleEmail,"
                    + " s.telefono = :telefono,"
                    + " s.descripcion = :descripcion,"
                    + " s.maeEstadoSolicitudId = :maeEstadoSolicitudId,"
                    + " s.maeEstadoSolicitudCodigo = :maeEstadoSolicitudCodigo,"
                    + " s.maeEstadoSolicitudValor = :maeEstadoSolicitudValor,"
                    + " s.aplicaCaso = :aplicaCaso,"
                    + " s.direccionNotificacion = :direccionNotificacion,";
                    if (per.getAusCasosId() != null) {
                        hql+= " s.ausCasosId.id = " + per.getAusCasosId().getId() + ",";
                    }
                    hql+= " s.usuarioModifica = :usuarioModifica,"
                    + " s.terminalModifica = :terminalModifica,"
                    + " s.fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE s.id = :id";
            //Query query = getEntityManager().createQuery(hql);
            org.hibernate.Query query = session.createQuery(hql);
            query.setProperties(per);
            query.executeUpdate();
//            int id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
//            obj.setId(id);
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public AusSolicitud eliminar(int id) throws Exception {
        AusSolicitud obj = null;
        try {
            AusSolicitudes ent = getEntityManager().find(AusSolicitudes.class, id);
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
    public AusSolicitud consultar(int id) throws Exception {
        AusSolicitud objRes = null;
        try {
            AusSolicitudes casoNegocio = (AusSolicitudes) getEntityManager().find(AusSolicitudes.class, id);
            objRes = castEntidadNegocio(casoNegocio);
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
    public int consultarCantidadListaExterna(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(s) FROM AusSolicitudes s "
                    + " WHERE s.maeTipoDocumentoCodigo = '" + paramConsulta.getParametroConsulta1() + "' "
                    + " AND s.numeroDocumento = :documentoNumero ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND s.id LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("documentoNumero", paramConsulta.getParametroConsulta2());
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
    public List<AusSolicitud> consultarListaExterna(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<AusSolicitud> listResult = new ArrayList();
        try {
            String strQuery = "FROM AusSolicitudes s "
                    + " WHERE s.maeTipoDocumentoCodigo = '" + paramConsulta.getParametroConsulta1() + "' "
                    + " AND s.numeroDocumento = :documentoNumero ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND s.id LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "s." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "s.fechaHoraCrea DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("documentoNumero", paramConsulta.getParametroConsulta2());
            //query.setParameter("fhNacimiento", ((Date) paramConsulta.getParametroConsulta3()), TemporalType.TIMESTAMP);
            List<AusSolicitudes> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AusSolicitudes per : list) {
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

    public static AusSolicitud castEntidadNegocio(AusSolicitudes per) {
        AusSolicitud obj = new AusSolicitud();
        obj.setId(per.getId());
        obj.setMaeTipoSolicitudId(per.getMaeTipoSolicitudId());
        obj.setMaeTipoSolicitudCodigo(per.getMaeTipoSolicitudCodigo());
        obj.setMaeTipoSolicitudValor(per.getMaeTipoSolicitudValor());
        obj.setNombres(per.getNombres());
        obj.setApellidos(per.getApellidos());
        obj.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setEmail(per.getEmail());
        obj.setDetalleEmail(per.getDetalleEmail());
        obj.setNumeroDocumento(per.getNumeroDocumento());
        obj.setTelefono(per.getTelefono());
        obj.setDescripcion(per.getDescripcion());
        obj.setMaeEstadoSolicitudId(per.getMaeEstadoSolicitudId());
        obj.setMaeEstadoSolicitudCodigo(per.getMaeEstadoSolicitudCodigo());
        obj.setMaeEstadoSolicitudValor(per.getMaeEstadoSolicitudValor());
        obj.setAplicaCaso(per.getAplicaCaso());
        obj.setDireccionNotificacion(per.getDireccionNotificacion());
        //objetos
        if (per.getAusCasosId()!= null) {
            obj.setAusCaso(new AusCaso(per.getAusCasosId().getId()));
        }
        if (per.getAusAdjuntosList() != null && !per.getAusAdjuntosList().isEmpty()) {
            obj.setAusAdjuntosList(AusAdjuntoServicio.castEntidadNegocio(per.getAusAdjuntosList()));
        }
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }
    
    public static AusSolicitudes castNegocioEntidad(AusSolicitud obj) {
        AusSolicitudes per = new AusSolicitudes();
        per.setId(obj.getId());
        per.setMaeTipoSolicitudId(obj.getMaeTipoSolicitudId());
        per.setMaeTipoSolicitudCodigo(obj.getMaeTipoSolicitudCodigo());
        per.setMaeTipoSolicitudValor(obj.getMaeTipoSolicitudValor());
        per.setNombres(obj.getNombres());
        per.setApellidos(obj.getApellidos());
        per.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
        per.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
        per.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
        per.setEmail(obj.getEmail());
        per.setDetalleEmail(obj.getDetalleEmail());
        per.setNumeroDocumento(obj.getNumeroDocumento());
        per.setTelefono(obj.getTelefono());
        per.setDescripcion(obj.getDescripcion());
        per.setMaeEstadoSolicitudId(obj.getMaeEstadoSolicitudId());
        per.setMaeEstadoSolicitudCodigo(obj.getMaeEstadoSolicitudCodigo());
        per.setMaeEstadoSolicitudValor(obj.getMaeEstadoSolicitudValor());
        per.setAplicaCaso(obj.isAplicaCaso());
        per.setDireccionNotificacion(obj.getDireccionNotificacion());
        //objetos
        if (obj.getAusCaso()!= null) {
            per.setAusCasosId(new AusCasos(obj.getAusCaso().getId()));
        }
        /*if (obj.getAusAdjuntosList() != null && !obj.getAusAdjuntosList().isEmpty()) {
            //per.setAusAdjuntosList(AusAdjuntoServicio.castNegocioEntidad(obj.getAusAdjuntosList()));
        }*/
        //auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        //per.setAfiliadoEdad(obj.getAusPersonasId().getEdad());
        return per;
    }

}
