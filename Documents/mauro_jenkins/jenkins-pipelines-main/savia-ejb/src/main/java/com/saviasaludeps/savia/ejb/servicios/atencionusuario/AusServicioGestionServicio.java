/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.atencionusuario;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersona;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCaso;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCasoServicio;
import com.saviasaludeps.savia.dominio.atencionusuario.AusServicioGestion;
import com.saviasaludeps.savia.dominio.atencionusuario.AusServicioGestionHistorico;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.ejb.entidades.AusCasoServicios;
import com.saviasaludeps.savia.ejb.entidades.AusServicioGestiones;
import com.saviasaludeps.savia.ejb.servicios.administracion.UsuarioServicio;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.utilidades.Util;
import com.saviasaludeps.savia.negocio.atencionusuario.AusServicioGestionRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(AusServicioGestionRemoto.class)
@Local(AusServicioGestionLocal.class)
public class AusServicioGestionServicio extends GenericoServicio implements AusServicioGestionLocal, AusServicioGestionRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta, int idEstadoServicio) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(s) FROM AusCasoServicios s "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "casosId.id":
                            strQuery += "AND s.ausCasosId.id LIKE '%" +  e.getValue() + "%' ";
                            break;

                        case "casosId.radicado":
                            strQuery += "AND s.ausCasosId.radicado LIKE '%" +  e.getValue() + "%' ";
                            break;

                        case "casosId.asuPersonasId.mae_tipo_documento_id":
                            strQuery += "AND s.ausCasosId.ausPersonasId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;

                        case "casosId.asuPersonasId.documento":
                            strQuery += "AND s.ausCasosId.ausPersonasId.documento = '" +  e.getValue() + "' ";
                            break;
                            
                        case "casosId.asuPersonasId.nombres":
                            strQuery += "AND s.ausCasosId.ausPersonasId.nombres LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                            
                        case "casosId.asuPersonasId.apellidos":
                            strQuery += "AND s.ausCasosId.ausPersonasId.apellidos LIKE '%" + (String) e.getValue() + "%' ";
                            break;  
                       
                        case "maServicioValor":
                            strQuery += "AND s.maServicioValor = '" +  e.getValue() + "' ";
                            break;
                            
                        case "maeServicioMotivoId":
                            strQuery += "AND s.maeServicioMotivoId = " +  e.getValue() + " ";
                            break;
                            
                        case "maeServicioId":
                            strQuery += "AND s.maServicioId = " +  e.getValue() + " ";
                            break;
                            
                        case "maeEstadoId":
                            strQuery += "AND s.maeEstadoId = " +  e.getValue() + " ";
                            break;   
                            
                        case "estado":
                            strQuery += "AND s.maeEstadoId = " +  e.getValue() + " ";
                            break;
                            
                        case "estadoDiferente":
                            strQuery += "AND s.maeEstadoId != " +  e.getValue() + " ";
                            break;
                            
                        case "estadoDiferente2":
                            strQuery += "AND s.maeEstadoId != " +  e.getValue() + " ";
                            break;
                        
                        case "estadoMenor":
                            strQuery += "AND s.maeEstadoId < " +  e.getValue() + " ";
                            break;
                        
                        case "fechaVencimiento":
                            strQuery += "AND s.fechaVencimiento >= '" +  e.getValue() + "' ";
                            break;
                    }
                }
            }    
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND s.gnUsuariosAsignadoId.id = " + (int) paramConsulta.getParametroConsulta1() + " ";
            }
            strQuery += "AND s.maeEstadoId != " + idEstadoServicio + " ";
            
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
    public List<AusCasoServicio> consultarLista(ParamConsulta paramConsulta, int idEstadoServicio) throws java.lang.Exception {
        List<AusCasoServicio> listResult = new ArrayList();
        try {
            String strQuery = "FROM AusCasoServicios s "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        
                        case "casosId.id":
                            strQuery += "AND s.ausCasosId.id LIKE '%" +  e.getValue() + "%' ";
                            break;

                        case "casosId.radicado":
                            strQuery += "AND s.ausCasosId.radicado LIKE '%" +  e.getValue() + "%' ";
                            break;

                        case "casosId.asuPersonasId.mae_tipo_documento_id":
                            strQuery += "AND s.ausCasosId.ausPersonasId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;

                        case "casosId.asuPersonasId.documento":
                            strQuery += "AND s.ausCasosId.ausPersonasId.documento = '" +  e.getValue() + "' ";
                            break;
                            
                        case "casosId.asuPersonasId.nombres":
                            strQuery += "AND s.ausCasosId.ausPersonasId.nombres LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                            
                        case "casosId.asuPersonasId.apellidos":
                            strQuery += "AND s.ausCasosId.ausPersonasId.apellidos LIKE '%" + (String) e.getValue() + "%' ";
                            break;  

                        case "maServicioValor":
                            strQuery += "AND s.maServicioValor = '" +  e.getValue() + "' ";
                            break;
                        
                        case "maeServicioMotivoId":
                            strQuery += "AND s.maeServicioMotivoId = " +  e.getValue() + " ";
                            break;
                            
                        case "maServicioId":
                            strQuery += "AND s.maServicioId = '" +  e.getValue() + "' ";
                            break;    
                        
                        case "maeEstadoId":
                            strQuery += "AND s.maeEstadoId = " +  e.getValue() + " ";
                            break;  
                        
                        case "estado":
                            strQuery += "AND s.maeEstadoId = " +  e.getValue() + " ";
                            break;
                            
                        case "estadoDiferente":
                            strQuery += "AND s.maeEstadoId != " + e.getValue() + " ";
                            break;
                            
                        case "estadoDiferente2":
                            strQuery += "AND s.maeEstadoId != " + e.getValue() + " ";
                            break;
                        
                        case "estadoMenor":
                            strQuery += "AND s.maeEstadoId < " +  e.getValue() + " ";
                            break;
                        
                        case "fechaVencimiento":
                            strQuery += "AND s.fechaVencimiento >= '" +  e.getValue() + "' ";
                            break;                         
                    }
                }
            }
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND s.gnUsuariosAsignadoId.id = " + (int) paramConsulta.getParametroConsulta1() + " ";
            }
            
            strQuery += "AND s.maeEstadoId != " + idEstadoServicio + " ";
            
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                String order = paramConsulta.getOrden().
                    replace("casosId.id", "ausCasosId.id").
                    replace("casosId.radicado", "ausCasosId.radicado").
                    replace("casosId.asuPersonasId.documento", "ausCasosId.ausPersonasId.documento").
                    replace("casosId.asuPersonasId.nombres", "ausCasosId.ausPersonasId.nombres").
                    replace("casosId.asuPersonasId.apellidos", "ausCasosId.ausPersonasId.apellidos").
                    replace("casosId.asuPersonasId.mae_tipo_documento_id", "ausCasosId.ausPersonasId.maeTipoDocumentoId");
                strQuery += "s." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "s.fechaHoraCrea DESC";
            }
            List<AusCasoServicios> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AusCasoServicios per : list) {
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
    public int consultarCantidadListaEstadoAsignado(ParamConsulta paramConsulta, int idEstadoServicioParaBuscar, int idEstadoServicioNoIncluido, int idEstadoEstudio) throws java.lang.Exception {
        int cant = 0;
        boolean esConsultaRegistrosVencidos = false;
        boolean  noHayFiltroEstado = true;
        try {
            String strQuery = "SELECT COUNT(s) FROM AusCasoServicios s "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "casosId.id":
                            strQuery += "AND s.ausCasosId.id LIKE '%" +  e.getValue() + "%' ";
                            break;

                        case "casosId.radicado":
                            strQuery += "AND s.ausCasosId.radicado LIKE '%" +  e.getValue() + "%' ";
                            break;

                        case "casosId.asuPersonasId.mae_tipo_documento_id":
                            strQuery += "AND s.ausCasosId.ausPersonasId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;

                        case "casosId.asuPersonasId.documento":
                            strQuery += "AND s.ausCasosId.ausPersonasId.documento = '" +  e.getValue() + "' ";
                            break;
                            
                        case "casosId.asuPersonasId.nombres":
                            strQuery += "AND s.ausCasosId.ausPersonasId.nombres LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                            
                        case "casosId.asuPersonasId.apellidos":
                            strQuery += "AND s.ausCasosId.ausPersonasId.apellidos LIKE '%" + (String) e.getValue() + "%' ";
                            break;  
                       
                        case "maServicioValor":
                            strQuery += "AND s.maServicioValor = '" +  e.getValue() + "' ";
                            break;
                            
                        case "maeServicioMotivoId":
                            strQuery += "AND s.maeServicioMotivoId = " +  e.getValue() + " ";
                            break;
                            
                        case "maeServicioId":
                            strQuery += "AND s.maServicioId = " +  e.getValue() + " ";
                            break;
                            
                        case "maeEstadoId":
                            String cadenaEstado = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND s.maeEstadoId IN ( " + cadenaEstado + ") ";
                            break;   
                            
                        case "estado":
                            strQuery += "AND s.maeEstadoId = " +  e.getValue() + " ";
                            noHayFiltroEstado = false;
                            break;
                            
                        case "estadoDiferente":
                            strQuery += "AND s.maeEstadoId != " +  e.getValue() + " ";
                            break;
                            
                        case "estadoDiferente2":
                            strQuery += "AND s.maeEstadoId != " +  e.getValue() + " ";
                            break;
                        
                        case "estadoMenor":
                            strQuery += "AND s.maeEstadoId < " +  e.getValue() + " ";
                            esConsultaRegistrosVencidos = true;
                            break;
                        
                        case "fechaVencimiento":
                            strQuery += "AND s.fechaVencimiento >= '" + e.getValue() + "' ";
                            break;

                        case "maDiagnostico.nombre":
                            strQuery += "AND s.maDiagnosticosValor like '%" + e.getValue() + "%' ";
                            break;
                            
                        case "casosId.maeSolicitudOrigenId":
                            String cadenaOrigen = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND s.ausCasosId.maeSolicitudOrigenId IN ( " + cadenaOrigen + ") ";
                            break;
                                    
                        case "casosId.maeSolicitudRiesgoVidalId":
                            String cadenaRiesgo = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND s.ausCasosId.maeSolicitudRiesgoVidalId IN ( " + cadenaRiesgo + ") ";
                            break;
                    }
                }
            }    
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND s.gnUsuariosAsignadoId.id = " + (int) paramConsulta.getParametroConsulta1() + " ";
            }
            
            if(esConsultaRegistrosVencidos){
               strQuery += "AND s.maeEstadoId != " + idEstadoServicioNoIncluido + " ";
            }else{
                if(noHayFiltroEstado){
                    strQuery += " AND ( s.maeEstadoId = " + idEstadoServicioParaBuscar + " OR s.maeEstadoId = " + idEstadoEstudio + " ) ";
                }
            }
            
            strQuery += "AND s.borrado = 0 ";
            
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
    public List<AusCasoServicio> consultarListaEstadoAsignado(ParamConsulta paramConsulta, int idEstadoServicioParaBuscar , int idEstadoServicioNoIncluido, int idEstadoEstudio) throws java.lang.Exception {
        List<AusCasoServicio> listResult = new ArrayList();
        boolean esConsultaRegistrosVencidos = false;
        boolean  noHayFiltroEstado = true;
        try {
            String strQuery = "FROM AusCasoServicios s "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        
                        case "casosId.id":
                            strQuery += "AND s.ausCasosId.id LIKE '%" +  e.getValue() + "%' ";
                            break;

                        case "casosId.radicado":
                            strQuery += "AND s.ausCasosId.radicado LIKE '%" +  e.getValue() + "%' ";
                            break;

                        case "casosId.asuPersonasId.mae_tipo_documento_id":
                            strQuery += "AND s.ausCasosId.ausPersonasId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;

                        case "casosId.asuPersonasId.documento":
                            strQuery += "AND s.ausCasosId.ausPersonasId.documento = '" +  e.getValue() + "' ";
                            break;
                            
                        case "casosId.asuPersonasId.nombres":
                            strQuery += "AND s.ausCasosId.ausPersonasId.nombres LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                            
                        case "casosId.asuPersonasId.apellidos":
                            strQuery += "AND s.ausCasosId.ausPersonasId.apellidos LIKE '%" + (String) e.getValue() + "%' ";
                            break;  

                        case "maServicioValor":
                            strQuery += "AND s.maServicioValor = '" +  e.getValue() + "' ";
                            break;
                        
                        case "maeServicioMotivoId":
                            strQuery += "AND s.maeServicioMotivoId = " +  e.getValue() + " ";
                            break;
                            
                        case "maServicioId":
                            strQuery += "AND s.maServicioId = '" +  e.getValue() + "' ";
                            break;    
                        
                        case "maeEstadoId":
                            String cadenaEstado = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND s.maeEstadoId IN ( " + cadenaEstado + ") ";
                            noHayFiltroEstado = false;
                            break;  
                        
                        case "estado":
                            strQuery += "AND s.maeEstadoId = " +  e.getValue() + " ";
                            break;
                            
                        case "estadoDiferente":
                            strQuery += "AND s.maeEstadoId != " + e.getValue() + " ";
                            break;
                            
                        case "estadoDiferente2":
                            strQuery += "AND s.maeEstadoId != " + e.getValue() + " ";
                            break;
                        
                        case "estadoMenor":
                            strQuery += "AND s.maeEstadoId < " +  e.getValue() + " ";
                            esConsultaRegistrosVencidos = true;
                            break;
                        
                        case "fechaVencimiento":
                            strQuery += "AND s.fechaVencimiento >= '" +  e.getValue() + "' ";
                            break;     
                            
                        case "maDiagnostico.nombre":
                            strQuery += "AND s.maDiagnosticosValor like '%" + e.getValue() +"%' ";
                            break;
                            
                        case "casosId.maeSolicitudOrigenId":
                            String cadenaOrigen = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND s.ausCasosId.maeSolicitudOrigenId IN ( " + cadenaOrigen + ") ";
                            break;
                                    
                        case "casosId.maeSolicitudRiesgoVidalId":
                            String cadenaRiesgo = Util.convertirArrayToString((String[]) e.getValue(), ",");
                            strQuery += "AND s.ausCasosId.maeSolicitudRiesgoVidalId IN ( " + cadenaRiesgo + ") ";
                            break;
                    }
                }
            }
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND s.gnUsuariosAsignadoId.id = " + (int) paramConsulta.getParametroConsulta1() + " ";
            }
            
            if(esConsultaRegistrosVencidos){
               strQuery += "AND s.maeEstadoId != " + idEstadoServicioNoIncluido + " ";
            } else {
                if (noHayFiltroEstado) {
                    strQuery += "AND (s.maeEstadoId = " + idEstadoServicioParaBuscar + " OR s.maeEstadoId = " + idEstadoEstudio + ") ";
                }
            }
            strQuery += "AND s.borrado = 0 ";
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                String order = paramConsulta.getOrden().
                    replace("casosId.id", "ausCasosId.id").
                    replace("casosId.radicado", "ausCasosId.radicado").
                    replace("casosId.asuPersonasId.documento", "ausCasosId.ausPersonasId.documento").
                    replace("casosId.asuPersonasId.nombres", "ausCasosId.ausPersonasId.nombres").
                    replace("casosId.asuPersonasId.apellidos", "ausCasosId.ausPersonasId.apellidos").
                    replace("casosId.asuPersonasId.mae_tipo_documento_id", "ausCasosId.ausPersonasId.maeTipoDocumentoId").
                    replace("maDiagnostico.id", "maDiagnosticosId.id");
                strQuery += "s." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "s.fechaHoraCrea DESC";
            }
            List<AusCasoServicios> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AusCasoServicios per : list) {
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
    public int insertar(AusServicioGestion obj) throws Exception {
        int id = 0;
        try {
            //id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al insertar el Servicio Gestion");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(AusServicioGestion obj) throws java.lang.Exception {
        try {
            int id = 0;
            //id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e, "Error al actualizar el servicio gestion");
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AusServicioGestion consultar(int id) throws Exception {
        AusServicioGestion objRes = null;
        try {
            AusServicioGestiones servicioGestionNegocio = (AusServicioGestiones) getEntityManager().find(AusServicioGestiones.class, id);
            objRes = castEntidadNegocio(servicioGestionNegocio);
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e, "Error al consultar un el Servicio Gestion");
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public AusServicioGestion eliminar(int id) throws java.lang.Exception {
        AusServicioGestion obj = null;
        try {
            AusServicioGestiones ent = getEntityManager().find(AusServicioGestiones.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e, "Error al eliminar un Servicio Gestion");
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }
    
    public static AusServicioGestion castEntidadNegocio(AusServicioGestiones servicioGestionNegocio) {
        AusServicioGestion servicioGestion = new AusServicioGestion();
        servicioGestion.setId(servicioGestionNegocio.getId());
        servicioGestion.setAusServicios(new AusCasoServicio(servicioGestionNegocio.getAusServiciosId().getId()));
        servicioGestion.setEstado(servicioGestionNegocio.getMaeEstadoId());
        servicioGestion.setObservacion(servicioGestionNegocio.getObservacion());
        servicioGestion.setUsuarioCrea(servicioGestionNegocio.getUsuarioCrea());
        servicioGestion.setFechaHoraCrea(servicioGestionNegocio.getFechaHoraCrea());
        servicioGestion.setTerminalCrea(servicioGestionNegocio.getTerminalCrea());
        return servicioGestion;
    }
    
    public static List<AusServicioGestionHistorico> castEntidadNegocio(List<AusServicioGestiones> servicioGestionNegocio) {
        
        List<AusServicioGestionHistorico> servicioGestion = new ArrayList<> ();
        
        for(AusServicioGestiones ausServicioGestiones:servicioGestionNegocio){
            AusServicioGestionHistorico ausServicioGestion = new AusServicioGestionHistorico();
            ausServicioGestion.setId(ausServicioGestiones.getId());
            if(ausServicioGestiones.getAusServiciosId() != null){
                ausServicioGestion.setCasoServicios(new AusCasoServicio(ausServicioGestiones.getAusServiciosId().getId()));
            }
            ausServicioGestion.setMaeEstadoId(ausServicioGestiones.getMaeEstadoId());
            ausServicioGestion.setMaeEstadoCodigo(ausServicioGestiones.getMaeEstadoCodigo());
            ausServicioGestion.setMaeEstadoValor(ausServicioGestiones.getMaeEstadoValor());
            ausServicioGestion.setObservacion(ausServicioGestiones.getObservacion());
            ausServicioGestion.setUsuarioCrea(ausServicioGestiones.getUsuarioCrea());
            ausServicioGestion.setFechaHoraCrea(ausServicioGestiones.getFechaHoraCrea());
            ausServicioGestion.setTerminalCrea(ausServicioGestiones.getTerminalCrea());
            servicioGestion.add(ausServicioGestion);
        }
        return servicioGestion;
    }
    
    public static AusCasoServicio castEntidadNegocio(AusCasoServicios servicioNegocio) {
        AusCasoServicio servicioEntidad = new AusCasoServicio();
        servicioEntidad.setId(servicioNegocio.getId());
        AusCaso caso = new AusCaso();
        if(servicioNegocio.getAusCasosId() !=  null){
            caso.setId(servicioNegocio.getAusCasosId().getId());
            caso.setMaeSolicitudOrigenId(servicioNegocio.getAusCasosId().getMaeSolicitudOrigenId());
            caso.setMaeSolicitudOrigenCodigo(servicioNegocio.getAusCasosId().getMaeSolicitudOrigenCodigo());
            caso.setMaeSolicitudOrigenValor(servicioNegocio.getAusCasosId().getMaeSolicitudOrigenValor());
            caso.setMaeSolicitudRiesgoVidalId(servicioNegocio.getAusCasosId().getMaeSolicitudRiesgoVidalId());
            caso.setMaeSolicitudRiesgoVidalCodigo(servicioNegocio.getAusCasosId().getMaeSolicitudRiesgoVidalCodigo());
            caso.setMaeSolicitudRiesgoVidalValor(servicioNegocio.getAusCasosId().getMaeSolicitudRiesgoVidalValor());
        }
       
        AusPersona persona = new AusPersona(servicioNegocio.getAusCasosId().getAusPersonasId().getId());
        persona.setMae_tipo_documento_id(servicioNegocio.getAusCasosId().getAusPersonasId().getMaeTipoDocumentoId());
        persona.setMae_tipo_documento_codigo(servicioNegocio.getAusCasosId().getAusPersonasId().getMaeTipoDocumentoCodigo());
        persona.setMae_tipo_documento_valor(servicioNegocio.getAusCasosId().getAusPersonasId().getMaeTipoDocumentoValor());
        persona.setDocumento(servicioNegocio.getAusCasosId().getAusPersonasId().getDocumento());
        persona.setNombres(servicioNegocio.getAusCasosId().getAusPersonasId().getNombres());
        persona.setApellidos(servicioNegocio.getAusCasosId().getAusPersonasId().getApellidos());
        servicioEntidad.setCasosId(caso);
        caso.setAusPersonasId(persona);
        
        
        caso.setRadicado(servicioNegocio.getAusCasosId().getRadicado());
        servicioEntidad.setMaeServicioAmbitoId(servicioNegocio.getMaeServicioAmbitoId());
        servicioEntidad.setMaeServicioAmbitoCodigo(servicioNegocio.getMaeServicioAmbitoCodigo());
        servicioEntidad.setMaeServicioAmbitoValor(servicioNegocio.getMaeServicioAmbitoValor());
        servicioEntidad.setMaeServicioMotivoId(servicioNegocio.getMaeServicioMotivoId());
        servicioEntidad.setMaeServicioMotivoCodigo(servicioNegocio.getMaeServicioMotivoCodigo());
        servicioEntidad.setMaeServicioMotivoValor(servicioNegocio.getMaeServicioMotivoValor());
        servicioEntidad.setMaeEstadoId(servicioNegocio.getMaeEstadoId());
        servicioEntidad.setMaeEstadoCodigo(servicioNegocio.getMaeEstadoCodigo());
        servicioEntidad.setMaeEstadoValor(servicioNegocio.getMaeEstadoValor());
        servicioEntidad.setFechaVencimiento(servicioNegocio.getFechaVencimiento());
        servicioEntidad.setFechaCumplimiento(servicioNegocio.getFechaCumplimiento());
        servicioEntidad.setMaServicioId(servicioNegocio.getMaServicioId());
        servicioEntidad.setMaServicioCodigo(servicioNegocio.getMaServicioCodigo());
        servicioEntidad.setMaServicioValor(servicioNegocio.getMaServicioValor());
        servicioEntidad.setCantidad(servicioNegocio.getCantidad());
        servicioEntidad.setDescripcion(servicioNegocio.getDescripcion());
        servicioEntidad.setPertinencia(servicioNegocio.getPertinencia());
        servicioEntidad.setBorrado(servicioNegocio.getBorrado());
        
        if(servicioNegocio.getMaDiagnosticosId() != null){
            MaDiagnostico diagnostico = new  MaDiagnostico(servicioNegocio.getMaDiagnosticosId().getId());
            diagnostico.setCodigo(servicioNegocio.getMaServicioCodigo());
            diagnostico.setNombre(servicioNegocio.getMaDiagnosticosValor());
            servicioEntidad.setMaDiagnostico(diagnostico);
        }
        
        if(servicioNegocio.getCntPrestadorSedePrescriptoraId() != null)
            servicioEntidad.setObjetoPrestadorSede(new CntPrestadorSede(servicioNegocio.getCntPrestadorSedePrescriptoraId().getId()));
        
        if(servicioNegocio.getCntPrestadorSedeDestinoId() != null)
            servicioEntidad.setObjetoPrestadorIps(new CntPrestadorSede(servicioNegocio.getCntPrestadorSedeDestinoId().getId()));
        
        if (servicioNegocio.getGnUsuariosAsignadoId() != null) {
            servicioEntidad.setAsignadoUsuariosId(UsuarioServicio.castEntidadNegocio(servicioNegocio.getGnUsuariosAsignadoId()));
        }
        
        if(servicioNegocio.getGnUsuariosAsignadoId() != null){
            Usuario usuarioResponsable = new Usuario();
            usuarioResponsable.setId(servicioNegocio.getGnUsuariosAsignadoId().getId());
            usuarioResponsable.setNombre(servicioNegocio.getGnUsuariosAsignadoId().getNombre());
            servicioEntidad.setIdUsuarioResponsable(usuarioResponsable);
        }
        
        if(servicioNegocio.getAusServicioGestionesList() != null &&
                !servicioNegocio.getAusServicioGestionesList().isEmpty()){
            servicioEntidad.setServicioGestionesList(castEntidadNegocio(servicioNegocio.getAusServicioGestionesList()));
        }
        servicioEntidad.setUsuarioCrea(servicioNegocio.getUsuarioCrea());
        servicioEntidad.setTerminalCrea(servicioNegocio.getTerminalCrea());
        servicioEntidad.setFechaHoraCrea(servicioNegocio.getFechaHoraCrea());
        servicioEntidad.setUsuarioModifica(servicioNegocio.getUsuarioModifica());
        servicioEntidad.setFechaHoraModifica(servicioNegocio.getFechaHoraModifica());
        servicioEntidad.setTerminalModifica(servicioNegocio.getTerminalModifica());
        servicioEntidad.setUsuarioBorra(servicioNegocio.getUsuarioBorra());
        servicioEntidad.setFechaHoraBorra(servicioNegocio.getFechaHoraBorra());
        servicioEntidad.setTerminalBorra(servicioNegocio.getTerminalBorra());
        servicioEntidad.setAdjuntosList(AusAdjuntoServicio.castEntidadNegocio(servicioNegocio.getAusAdjuntosList())  );
        return servicioEntidad;
    }

 
}
