/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.tutela;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuPersona;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.entidades.TuPersonas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.tutela.TuPersonaRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jramirer
 */
@Stateless
@Remote(TuPersonaRemoto.class)
public class TuPersonaServicio extends GenericoServicio implements TuPersonaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(t.id) FROM TuPersonas t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombres":
                            strQuery += "AND t.nombres LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "apellidos":
                            strQuery += "AND t.apellidos LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "numeroDocumento":
                            strQuery += "AND t.numeroDocumento LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeEstadoAfiliadoId":
                            strQuery += "AND t.maeEstadoAfiliadoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "maeGeneroId":
                            strQuery += "AND t.maeGeneroId = '" + (String) e.getValue() + "' ";
                            break;
                    }
                }
            }
            
            if(paramConsulta.getParametroConsulta1() != null){
                strQuery += "AND t.numeroDocumento LIKE '%" + (String) paramConsulta.getParametroConsulta1() + "%' ";
            }
            
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
    public List<TuPersona> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<TuPersona> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t FROM TuPersonas t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombres":
                            strQuery += "AND t.nombres LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "apellidos":
                            strQuery += "AND t.apellidos LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "codigoDespacho":
                            strQuery += "AND t.codigoDespacho LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "ubicacion.id":
                            Ubicacion ubi = (Ubicacion) e.getValue();
                            strQuery += "AND t.gnUbicacionesId.id = " + ubi.getId() + " ";
                            break;
                        case "numeroDocumento":
                            strQuery += "AND t.numeroDocumento LIKE '%" + (String) e.getValue() + "%' ";
                            break;  
                        case "maeEstadoAfiliadoId":
                            strQuery += "AND t.maeEstadoAfiliadoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "maeGeneroId":
                            strQuery += "AND t.maeGeneroId = '" + (String) e.getValue() + "' ";
                            break;
                    }
                    }
                }
                     
            if( paramConsulta.getParametroConsulta1() != null ){
                strQuery += "AND t.numeroDocumento LIKE '%" + (String) paramConsulta.getParametroConsulta1() + "%' ";
            }
            
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "t." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "t.id ASC";
            }
            List<TuPersonas> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //                    .setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (TuPersonas per : list) {
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
    public TuPersona consultar(int id) throws Exception {
        TuPersona objRes = null;
        try {
            objRes = castEntidadNegocio((TuPersonas) getEntityManager().find(TuPersonas.class, id));
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
    public int insertar(TuPersona obj) throws Exception {
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
    public void actualizar(TuPersona obj) throws Exception {
        try {
            String hql = "UPDATE TuPersonas SET"
                    + " asegAfiliadoId = :asegAfiliadoId,"
                    + " maeEstadoAfiliadoId = :maeEstadoAfiliadoId,"
                    + " maeEstadoAfiliadoCodigo = :maeEstadoAfiliadoCodigo,"
                    + " maeEstadoAfiliadoValor = :maeEstadoAfiliadoValor,"
                    + " maeTipoDocumentoId = :maeTipoDocumentoId,"
                    + " maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo,"
                    + " maeTipoDocumentoValor = :maeTipoDocumentoValor,"
                    + " numeroDocumento = :numeroDocumento,"
                    + " nombres = :nombres,"
                    + " apellidos = :apellidos,"
                    + " fechaNacimiento = :fechaNacimiento,"
                    + " maeGeneroId = :maeGeneroId,"
                    + " maeGeneroCodigo = :maeGeneroCodigo,"
                    + " maeGeneroValor = :maeGeneroValor,"
                    + " agenteOficioso = :agenteOficioso,"
                    + " correoElectronico = :correoElectronico,"
                    + " direccionResidencia = :direccionResidencia,"
                    + " ubicacionAfiliacionId.id = :ubicacionAfiliacionId,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("asegAfiliadoId", obj.getAsegAfiliadoId());
            query.setParameter("maeEstadoAfiliadoId", obj.getMaeEstadoAfiliadoId());
            query.setParameter("maeEstadoAfiliadoCodigo", obj.getMaeEstadoAfiliadoCodigo());
            query.setParameter("maeEstadoAfiliadoValor", obj.getMaeEstadoAfiliadoValor());
            query.setParameter("maeTipoDocumentoId", obj.getMaeTipoDocumentoId());
            query.setParameter("maeTipoDocumentoCodigo", obj.getMaeTipoDocumentoCodigo());
            query.setParameter("maeTipoDocumentoValor", obj.getMaeTipoDocumentoValor());
            query.setParameter("numeroDocumento", obj.getNumeroDocumento());
            query.setParameter("nombres", obj.getNombres());
            query.setParameter("apellidos", obj.getApellidos());
            query.setParameter("fechaNacimiento", obj.getFechaNacimento());
            query.setParameter("maeGeneroId", obj.getMaeGeneroId());
            query.setParameter("maeGeneroCodigo", obj.getMaeGeneroCodigo());
            query.setParameter("maeGeneroValor", obj.getMaeGeneroValor());
            query.setParameter("agenteOficioso", obj.getAgentreOficioso());
            query.setParameter("correoElectronico", obj.getCorreoElectronico());
            query.setParameter("direccionResidencia", obj.getDireccion());
            query.setParameter("ubicacionAfiliacionId", obj.getUbicacionAfiliadoId().getId());
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
    public TuPersona eliminar(int id) throws Exception {
        TuPersona obj = null;
        try {
            TuPersonas ent = getEntityManager().find(TuPersonas.class, id);
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
    public TuPersona consultarPersona(TuPersona obj) throws Exception {
        TuPersona ausPersona = new TuPersona();
        try {
            String strQuery = "FROM TuPersonas p "
                    + "WHERE";
            strQuery += " p.maeTipoDocumentoCodigo = '" + obj.getMaeTipoDocumentoCodigo() + "' ";
            strQuery += "AND p.numeroDocumento = '" + obj.getNumeroDocumento() + "' ";

            List<TuPersonas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (TuPersonas per : list) {
                ausPersona = castEntidadNegocio(per);
            }

        } catch (NoResultException e) {
            ausPersona = new TuPersona();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return ausPersona;
    }
    
    public static TuPersona castEntidadNegocio(TuPersonas per) {
        TuPersona obj = new TuPersona();
        obj.setId(per.getId());
        obj.setAsegAfiliadoId(per.getAsegAfiliadoId());
        obj.setMaeEstadoAfiliadoId(per.getMaeEstadoAfiliadoId());
        obj.setMaeEstadoAfiliadoCodigo(per.getMaeEstadoAfiliadoCodigo());
        obj.setMaeEstadoAfiliadoValor(per.getMaeEstadoAfiliadoValor());
        obj.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setNumeroDocumento(per.getNumeroDocumento());
        obj.setNombres(per.getNombres());
        obj.setApellidos(per.getApellidos());
        obj.setFechaNacimento(per.getFechaNacimiento());
        obj.setMaeGeneroId(per.getMaeGeneroId());
        obj.setMaeGeneroCodigo(per.getMaeGeneroCodigo());
        obj.setMaeGeneroValor(per.getMaeGeneroValor());
        if(per.getAgenteOficioso() != null){
            Boolean agentreOficioso;
            agentreOficioso = !(per.getAgenteOficioso().equals("0"));
            obj.setAgentreOficioso(agentreOficioso);
        }
        String correo = per.getCorreoElectronico() == null || per.getCorreoElectronico().equalsIgnoreCase("NULL") ? "" : per.getCorreoElectronico();
        correo = correo.trim();
        obj.setCorreoElectronico(correo);
        obj.setDireccion(per.getDireccionResidencia());
        if(per.getUbicacionAfiliacionId() != null){
            obj.setUbicacionAfiliadoId(new Ubicacion(per.getUbicacionAfiliacionId().getId()));
        }
        
//        obj.setDireccion(per.getDireccion());
//        if (obj.getUbicacionAfiliadoId()!= null) {
//            per.setUbicacionAfiliacionId(new Ubicacion(obj.getUbicacionAfiliadoId().getId()));
//        }

        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static TuPersonas castNegocioEntidad(TuPersona obj) {
        TuPersonas per = new TuPersonas();
        per.setId(obj.getId());
        per.setNombres(obj.getNombres());
        per.setApellidos(obj.getApellidos());
        per.setCorreoElectronico(obj.getCorreoElectronico());
        per.setMaeEstadoAfiliadoId(obj.getMaeEstadoAfiliadoId());
        per.setMaeEstadoAfiliadoCodigo(obj.getMaeEstadoAfiliadoCodigo());
        per.setMaeEstadoAfiliadoValor(obj.getMaeEstadoAfiliadoValor());
        per.setMaeGeneroId(obj.getMaeGeneroId());
        per.setMaeGeneroCodigo(obj.getMaeGeneroCodigo());
        per.setMaeGeneroValor(obj.getMaeGeneroValor());
        per.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
        per.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
        per.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
        per.setNumeroDocumento(obj.getNumeroDocumento());
        if(obj.getUbicacionAfiliadoId() != null){
            per.setUbicacionAfiliacionId(new GnUbicaciones(obj.getUbicacionAfiliadoId().getId()));
        }
        per.setDireccionResidencia(obj.getDireccion());
        per.setFechaNacimiento(obj.getFechaNacimento());
        per.setAsegAfiliadoId(obj.getAsegAfiliadoId());
  //      per.setAgenteOficioso(obj.getAgentreOficioso());
//        per.setCodigoDespacho(obj.getCodigo_despacho());
//        per.setActivo(obj.getActivo());
        //Auditoría
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }
}