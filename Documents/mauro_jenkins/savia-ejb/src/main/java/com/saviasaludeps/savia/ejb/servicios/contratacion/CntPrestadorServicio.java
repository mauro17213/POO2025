/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorContacto;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorContactos;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.GnMaestros;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.webservices.rest.objeto.contratacion.IpsDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.contratacion.IpsSedesDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(CntPrestadorRemoto.class)
public class CntPrestadorServicio extends GenericoServicio implements CntPrestadorRemoto {
    
    @Override
    public CntPrestador consultarPorPrestador(int id) throws java.lang.Exception {
        CntPrestador obj = null;
        try {
            String strQuery = "FROM CntPrestadorSedes AS s ";
            strQuery += "INNER JOIN s.cntPrestadoresId p ";
            strQuery += "WHERE p.id = :id_sede ";
            strQuery += " ORDER BY s.nombre ASC";
            CntPrestadores per = (CntPrestadores) getEntityManager().createQuery(strQuery)
                    .setParameter("id_sede", id)
                    .getSingleResult();
            obj = castEntidadNegocio(per);
        } catch (NoResultException e) {
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }
    
    @Override
    public List<CntPrestador> consultarPorNombre(String nombre) throws java.lang.Exception {
        List<CntPrestador> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM CntPrestadores s "
                    + "WHERE s.razonSocial LIKE :razon_social ";
            strQuery += " ORDER BY s.razonSocial ASC";
            List<CntPrestadores> list = getEntityManager().createQuery(strQuery)
                    .setParameter("razon_social", "%" + nombre + "%")
                    .getResultList();
            for (CntPrestadores per : list) {
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
    public CntPrestador consultarPorCodigoHabilitacion(String codigoHabilitacion) {
        CntPrestador objResult = null;
        int i = 0;
        try {
            String strQuery = "FROM CntPrestadores s "
                    + "WHERE s.codigoMinSalud = :codigo_min_salud ";
            CntPrestadores cntPrestadores = (CntPrestadores) getEntityManager().createQuery(strQuery)
                    .setParameter("codigo_min_salud", codigoHabilitacion)
                    .getSingleResult();
            objResult = castEntidadNegocio(cntPrestadores);
        } catch (NoResultException e) {
        } catch (Exception e) {
            try {
                Exception(CONSULTAR_TODOS, e);
            } catch (java.lang.Exception ex) {
            }
        } finally {
            cerrarEntityManager();
        }
        return objResult;
    }
    
    public static CntPrestador castEntidadNegocio(CntPrestadores per) {
        CntPrestador obj = new CntPrestador();
        List<CntPrestadorSede> listaSedes = new ArrayList<>();
        obj.setId(per.getId());
        obj.setCodigoMinSalud(per.getCodigoMinSalud());
        obj.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setNumeroDocumento(per.getNumeroDocumento());
        obj.setDigitoVerificacion(per.getDigitoVerificacion());
        obj.setRazonSocial(per.getRazonSocial());
        obj.setNaturalezaJuridica(per.getNaturalezaJuridica());
        obj.setPrefijo(per.getPrefijo());
        obj.setMaeClasePrestador(per.getMaeClasePrestadorId());
        obj.setMaeClasePrestadorCodigo(per.getMaeClasePrestadorCodigo());
        obj.setMaeClasePrestadorValor(per.getMaeClasePrestadorValor());
        obj.setCategoriaPrestador(per.getCategoriaPrestador());
        obj.setNivelAtencion(per.getNivelAtencion());
        obj.setMaeTipoDocumentoRepId(per.getMaeTipoDocumentoRepId());
        obj.setMaeTipoDocumentoRepCodigo(per.getMaeTipoDocumentoRepCodigo());
        obj.setMaeTipoDocumentoRepValor(per.getMaeTipoDocumentoRepValor());
        obj.setNumeroDocumentoRep(per.getNumeroDocumentoRep());
        obj.setNombreRepresentanteLegal(per.getNombreRepresentanteLegal());
        obj.setActivo(per.getActivo());
        //2024-10-03 jyperez nuevos campos
        obj.setGrupoRipsMinisterio(per.getGrupoRipsMinisterio());
        if (per.getFacturacionElectronica() != null) {
            obj.setFacturacionElectronica(per.getFacturacionElectronica());
        } else {
            obj.setFacturacionElectronica(false);
        }
        if (per.getUnionTemporal()!= null) {
            obj.setUnionTemporal(per.getUnionTemporal());
        } else {
            obj.setUnionTemporal(false);
        }
        //objetos
        if (per.getCntPrestadorSedesList() != null) {
            per.getCntPrestadorSedesList().forEach(sede -> {
                listaSedes.add(castPrestadorSedesEntidadNegocio(sede));
            });
            obj.setListaPrestadorSedes(listaSedes);
        }
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }
    
    public static CntPrestadores castNegocioEntidad(CntPrestador obj) {
        CntPrestadores per = new CntPrestadores();
        per.setId(obj.getId());
        per.setCodigoMinSalud(obj.getCodigoMinSalud());
        per.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
        per.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
        per.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
        per.setNumeroDocumento(obj.getNumeroDocumento());
        per.setDigitoVerificacion(obj.getDigitoVerificacion());
        per.setRazonSocial(obj.getRazonSocial());
        per.setNaturalezaJuridica(obj.getNaturalezaJuridica());
        per.setPrefijo(obj.getPrefijo());
        per.setMaeClasePrestadorId(obj.getMaeClasePrestador());
        per.setMaeClasePrestadorCodigo(obj.getMaeClasePrestadorCodigo());
        per.setMaeClasePrestadorValor(obj.getMaeClasePrestadorValor());
        per.setCategoriaPrestador(obj.getCategoriaPrestador());
        per.setNivelAtencion(obj.getNivelAtencion());
        per.setMaeTipoDocumentoRepId(obj.getMaeTipoDocumentoRepId());
        per.setMaeTipoDocumentoRepCodigo(obj.getMaeTipoDocumentoRepCodigo());
        per.setMaeTipoDocumentoRepValor(obj.getMaeTipoDocumentoRepValor());
        per.setNumeroDocumentoRep(obj.getNumeroDocumentoRep());
        per.setNombreRepresentanteLegal(obj.getNombreRepresentanteLegal());
        per.setActivo(obj.isActivo());
        per.setFacturacionElectronica(obj.getFacturacionElectronica());
        per.setUnionTemporal(obj.getUnionTemporal());
        //2024-10-03 jyperez nuevos campos
        per.setGrupoRipsMinisterio(obj.getGrupoRipsMinisterio());
        //auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        return per;
    }
    
    public static CntPrestadorSede castPrestadorSedesEntidadNegocio(CntPrestadorSedes per) {
        CntPrestadorSede obj = new CntPrestadorSede();
        obj.setId(per.getId());
        obj.setUbicacionId(per.getUbicacionId());
        obj.setCodigoPrestador(per.getCodigoPrestador());
        obj.setUbicacionId(per.getUbicacionId());
        obj.setMaeRegionId(per.getMaeRegionId());
        obj.setMaeRegionCodigo(per.getMaeRegionCodigo());
        obj.setMaeRegionValor(per.getMaeRegionValor());
        obj.setDireccion(per.getDireccion());
        obj.setNombreSede(per.getNombre());
        obj.setCodigoSede(per.getCodigo());
        obj.setCodigoHabilitacionSede(per.getCodigoHabilitacion());
        obj.setZonaPrecedencia(per.getZonaPrecedencia());
        obj.setEstadoSede(per.getEstadoSede());
        obj.setNivelAtencion(per.getNivelAtencion());
        obj.setClasePrestador(per.getMaeClasePrestadorId());
        obj.setMaeClasePrestadorCodigo(per.getMaeClasePrestadorCodigo());
        obj.setMaeClasePrestadorValor(per.getMaeClasePrestadorValor());
        obj.setFax(per.getFax());
        obj.setTelefonoCitas(per.getTelefonoCitas());
        obj.setCorreoElectronico(per.getCorreoElectronico());
        obj.setTelefonoAdministrativo(per.getTelefonoAdministrativo());
        obj.setCapitacion(per.getCapitacion());
        obj.setGrupoRipsMinisterio(per.getGrupoRipsMinisterio());
        obj.setFechaFacturaElectronica(per.getFechaFacturaElectronica());
        if (per.getCntPrestadoresId() != null) {
            obj.setCntPrestador(new CntPrestador(per.getCntPrestadoresId().getId(), per.getCntPrestadoresId().getRazonSocial()));
            obj.getCntPrestador().setMaeTipoDocumentoCodigo(per.getCntPrestadoresId().getMaeTipoDocumentoCodigo());
            obj.getCntPrestador().setMaeTipoDocumentoValor(per.getCntPrestadoresId().getMaeTipoDocumentoValor());
            obj.getCntPrestador().setNumeroDocumento(per.getCntPrestadoresId().getNumeroDocumento());
        }
        //2022-05-31 jyperez adicionamos lista contactos
        if (per.getCntPrestadorContactosList() != null) {
            obj.setListaCntPrestadorContactos(new ArrayList());
            for (CntPrestadorContactos contacto : per.getCntPrestadorContactosList()) {
                obj.getListaCntPrestadorContactos().add(castPrestadorContactoEntidadNegocio(contacto));
            }
        }
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }
    
    public static CntPrestadorContacto castPrestadorContactoEntidadNegocio(CntPrestadorContactos per) {
        CntPrestadorContacto obj = new CntPrestadorContacto();
        obj.setId(per.getId());
        obj.setMaeTipoContactoId(per.getMaeTipoContactoId());
        obj.setMaeTipoContactoCodigo(per.getMaeTipoContactoCodigo());
        obj.setMaeTipoContactoValor(per.getMaeTipoContactoValor());
        obj.setMaeAreaContactoId(per.getMaeAreaContactoId());
        obj.setMaeAreaContactoCodigo(per.getMaeAreaContactoCodigo());
        obj.setMaeAreaContactoValor(per.getMaeAreaContactoValor());
        obj.setContacto(per.getContacto());
        obj.setAutorizaEnvio(per.getAutorizaEnvio());
        obj.setActivo(per.getActivo());
        obj.setObservacion(per.getObservacion());
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        return obj;
    }
    
    public static CntPrestadorSedes castPrestadorSedesNegocioEntidad(CntPrestadorSede obj) {
        CntPrestadorSedes per = new CntPrestadorSedes();
        per.setId(obj.getId());
        per.setCodigoPrestador(obj.getCodigoPrestador());
        per.setUbicacionId(obj.getUbicacionId());
        per.setMaeRegionId(obj.getMaeRegionId());
        per.setMaeRegionCodigo(obj.getMaeRegionCodigo());
        per.setMaeRegionValor(obj.getMaeRegionValor());
        per.setDireccion(obj.getDireccion());
        per.setNombre(obj.getNombreSede());
        per.setCodigo(obj.getCodigoSede());
        per.setCodigoHabilitacion(obj.getCodigoHabilitacionSede());
        per.setZonaPrecedencia(obj.getZonaPrecedencia());
        per.setEstadoSede(obj.getEstadoSede());
        per.setNivelAtencion(obj.getNivelAtencion());
        per.setMaeClasePrestadorId(obj.getClasePrestador());
        per.setMaeClasePrestadorCodigo(obj.getMaeClasePrestadorCodigo());
        per.setMaeClasePrestadorValor(obj.getMaeClasePrestadorValor());
        per.setFax(obj.getFax());
        per.setTelefonoCitas(obj.getTelefonoCitas());
        per.setCorreoElectronico(obj.getCorreoElectronico());
        per.setTelefonoAdministrativo(obj.getTelefonoAdministrativo());
        per.setCapitacion(obj.getCapitacion());
        if (obj.getCntPrestador() != null) {
            per.setCntPrestadoresId(new CntPrestadores(obj.getCntPrestador().getId()));
        }
        //auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        return per;
    }
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(DISTINCT p) FROM CntPrestadores p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                        case "codigoMinSalud":
                            strQuery += " AND p.codigoMinSalud = '" + e.getValue() + "' ";
                            break;
                        case "maeTipoDocumentoId":
                            strQuery += " AND p.maeTipoDocumentoId  = " + e.getValue() + " ";
                            break;
                        case "numeroDocumento":
                            strQuery += " AND p.numeroDocumento = '" + e.getValue() + "' ";
                            break;
                        case "razonSocial":
                            strQuery += " AND p.razonSocial  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeClasePrestador":
                            strQuery += " AND p.maeClasePrestadorId  = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public List<CntPrestador> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CntPrestador> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntPrestadores p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                        case "codigoMinSalud":
                            strQuery += " AND p.codigoMinSalud = '" + e.getValue() + "' ";
                            break;
                        case "maeTipoDocumentoId":
                            strQuery += " AND p.maeTipoDocumentoId  = " + e.getValue() + " ";
                            break;
                        case "numeroDocumento":
                            strQuery += " AND p.numeroDocumento = '" + e.getValue() + "' ";
                            break;
                        case "razonSocial":
                            strQuery += " AND p.razonSocial  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeClasePrestador":
                            strQuery += " AND p.maeClasePrestadorId  = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<CntPrestadores> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (CntPrestadores per : list) {
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
    public List<CntPrestador> consultarListaPrestador(String tipodocumento, String numeroDocumento) throws Exception {
        
        List<CntPrestador> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntPrestadores p "
                    + " WHERE p.maeTipoDocumentoCodigo = '" + tipodocumento + "' "
                    + " AND p.numeroDocumento = '" + numeroDocumento + "' ";
            List<CntPrestadores> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntPrestadores per : list) {
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
    public CntPrestador consultar(int id) throws Exception {
        CntPrestador objRes = null;
        try {
            CntPrestadores per = getEntityManager().find(CntPrestadores.class, id);
            objRes = castEntidadNegocio(per);
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
    public int insertar(CntPrestador obj) throws Exception {
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
    public void actualizar(CntPrestador obj) throws Exception {
        try {
            CntPrestadores prestador = castNegocioEntidad(obj);
            //getEntityManager().merge(castNegocioEntidad(obj));
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE CntPrestadores p SET ";
            strQuery += " p.codigoMinSalud = :codigoMinSalud ,";
            strQuery += " p.activo = :activo ,";
            strQuery += " p.maeTipoDocumentoId = :maeTipoDocumentoId ,";
            strQuery += " p.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo ,";
            strQuery += " p.maeTipoDocumentoValor = :maeTipoDocumentoValor ,";
            strQuery += " p.numeroDocumento = :numeroDocumento ,";
            strQuery += " p.digitoVerificacion = :digitoVerificacion ,";
            strQuery += " p.razonSocial = :razonSocial ,";
            strQuery += " p.naturalezaJuridica = :naturalezaJuridica ,";
            strQuery += " p.facturacionElectronica = :facturacionElectronica ,";
            strQuery += " p.maeClasePrestadorId = :maeClasePrestadorId ,";
            strQuery += " p.maeClasePrestadorCodigo = :maeClasePrestadorCodigo ,";
            strQuery += " p.maeClasePrestadorValor = :maeClasePrestadorValor ,";
            strQuery += " p.categoriaPrestador = :categoriaPrestador ,";
            strQuery += " p.nivelAtencion = :nivelAtencion ,";
            strQuery += " p.maeTipoDocumentoRepId = :maeTipoDocumentoRepId ,";
            strQuery += " p.maeTipoDocumentoRepCodigo = :maeTipoDocumentoRepCodigo ,";
            strQuery += " p.maeTipoDocumentoRepValor = :maeTipoDocumentoRepValor ,";
            strQuery += " p.numeroDocumentoRep = :numeroDocumentoRep ,";
            strQuery += " p.nombreRepresentanteLegal = :nombreRepresentanteLegal ,";
            strQuery += " p.unionTemporal = :unionTemporal ,";
            //2024-10-03 jyperez nuevos campos
            strQuery += " p.grupoRipsMinisterio = :grupoRipsMinisterio ,";
            strQuery += " p.usuarioModifica = :usuarioModifica ,";
            strQuery += " p.fechaHoraModifica = :fechaHoraModifica ,";
            strQuery += " p.terminalModifica = :terminalModifica ";
            strQuery += " WHERE p.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(prestador);
            query.executeUpdate();
        } catch (NoResultException e) {
            
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public CntPrestador eliminar(int id) throws Exception {
        CntPrestador obj = null;
        try {
            CntPrestadores ent = getEntityManager().find(CntPrestadores.class, id);
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
    public List<CntPrestador> consultarTodos() throws Exception {
        List<CntPrestador> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntPrestadores p "
                    + "ORDER BY p.id ";
            List<CntPrestadores> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntPrestadores per : list) {
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

    /*comentamos la función que ya no se usa.
    @Override
    public HashMap<Integer, CntPrestadorSede> consultarHashSedesPorDiviPoli(String divipola) throws Exception {
        HashMap<Integer, CntPrestadorSede> hashResult = new HashMap();
        String strQuery = "FROM CntPrestadorSedes p "
                + "WHERE p.municipioDivipola = '" + divipola + "' "
                + "AND p.estadoSede = '1' "
                + "AND p.nivelAtencion = 1 "
                + "ORDER BY p.id ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<CntPrestadorSedes> list = query.getResultList();
            for (CntPrestadorSedes per : list) {
                CntPrestadorSede obj = castPrestadorSedesEntidadNegocio(per);
                hashResult.put(obj.getId(), obj);
            }
        } catch (NoResultException e) {
            hashResult = new HashMap();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return hashResult;
    }*/
    @Override
    public CntPrestadorSede consultarSede(int id) throws Exception {
        CntPrestadorSede objRes = null;
        try {
            objRes = castPrestadorSedesEntidadNegocio((CntPrestadorSedes) getEntityManager().find(CntPrestadorSedes.class, id));
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
    public CntPrestadorSede consultarSedePorCodigoHabilitacionTodos(String codigo) throws java.lang.Exception {
        CntPrestadorSede result = null;
        int i = 0;
        try {
            String strQuery = "FROM CntPrestadorSedes p "
                    + "WHERE p.codigoHabilitacion = '" + codigo + "' "
                    + "ORDER BY p.id ";
            List<CntPrestadorSedes> list = getEntityManager().createQuery(strQuery).getResultList();
            for (CntPrestadorSedes per : list) {
                result = castPrestadorSedesEntidadNegocio(per);
            }
        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return result;
    }
    
    @Override
    public List<CntPrestadorSede> consultarSedesTodos() throws java.lang.Exception {
        List<CntPrestadorSede> listResult = new ArrayList();
        try {
            Query nativeQuery = em.createNativeQuery("SELECT id,cnt_prestadores_id,codigo_prestador,ubicacion_id,nombre,codigo_habilitacion,estado_sede FROM cnt_prestador_sedes ");
            List<Object[]> results = nativeQuery.getResultList();
            listResult = results
                    .stream()
                    .map(result -> new CntPrestadorSede(((Integer) result[0]),
                    (Integer) result[1],
                    (String) result[2],
                    (Integer) result[3],
                    (String) result[4],
                    (String) result[5],
                    (Boolean) result[6]
            ))
                    .collect(Collectors.toList());
            
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
    public IpsDTO consultarIps(String codigoHabilitacionPrestador, String documentoPrestador, String codigoSedePrestador) throws java.lang.Exception {
        IpsDTO ips = null;
        
        try {
            String strQuery = "FROM CntPrestadores p "
                    + "WHERE p.id > 0 ";
            
            if ((documentoPrestador == null || documentoPrestador.trim().equals("")) && (codigoHabilitacionPrestador == null || codigoHabilitacionPrestador.trim().equals(""))) {
                throw new Exception("Parámetros incompletos");
            } else {
                if (codigoHabilitacionPrestador != null && !codigoHabilitacionPrestador.trim().equals("")) {
                    strQuery += "AND p.codigoMinSalud = '" + codigoHabilitacionPrestador + "' ";
                }
                if (documentoPrestador != null && !documentoPrestador.trim().equals("")) {
                    strQuery += "AND p.numeroDocumento = '" + documentoPrestador + "' ";
                }
            }
            
            List<CntPrestadores> list = getEntityManager().createQuery(strQuery).getResultList();
            if (!list.isEmpty()) {
                
                CntPrestadores per = list.get(0);
                //Carga Ips
                ips = new IpsDTO();
                ips.setCodigoHabilitacion(per.getCodigoMinSalud());
                if (per.getMaeTipoDocumentoId() != 0) {
                    GnMaestros tipoDocumento = (GnMaestros) getEntityManager().find(GnMaestros.class, per.getMaeTipoDocumentoId());
                    ips.setTipoDocumento(tipoDocumento.getValor());
                }
                
                ips.setNumeroDocumento(per.getNumeroDocumento());
                ips.setDigitoVerificacion(per.getDigitoVerificacion());
                ips.setRazonSocial(per.getRazonSocial());
                ips.setNaturalezaJuridica(per.getNaturalezaJuridica());
                ips.setPrefijo(per.getPrefijo());
                GnMaestros clasePrestador = (GnMaestros) getEntityManager().find(GnMaestros.class, per.getMaeClasePrestadorId());
                if (clasePrestador != null) {
                    ips.setClase(clasePrestador.getDescripcion());
                }
                
                ips.setCategoria(String.valueOf(per.getCategoriaPrestador()));
                ips.setNivelAtencion(String.valueOf(per.getNivelAtencion()));
                GnMaestros tipoDocumentoRep = (GnMaestros) getEntityManager().find(GnMaestros.class, per.getMaeTipoDocumentoRepId());
                if (tipoDocumentoRep != null) {
                    ips.setTipoDocRepresentanteLegal(tipoDocumentoRep.getValor());
                }
                
                ips.setDocRepresentanteLegal(per.getNumeroDocumentoRep());
                ips.setNomRepresentanteLegal(per.getNombreRepresentanteLegal());
                if (ips.getCodigoHabilitacion() != null) {
//                    Consulta y Carga de IpsSedes 
                    String strQuerySedes = "FROM CntPrestadorSedes ps "
                            + "WHERE ps.codigoPrestador = '" + ips.getCodigoHabilitacion() + "' ";
                    
                    if (codigoSedePrestador != null && !codigoSedePrestador.trim().equals("")) {
                        strQuerySedes += "AND ps.codigoSede = '" + codigoSedePrestador + "'";
                    } else if (codigoHabilitacionPrestador != null && codigoHabilitacionPrestador != "") {
                        throw new Exception("Parámetros incompletos ");
                    }
                    
                    List<CntPrestadorSedes> listSedes = getEntityManager().createQuery(strQuerySedes).getResultList();
                    List<IpsSedesDTO> listaIpsSedes = new ArrayList();
                    if (!listSedes.isEmpty()) {
                        for (CntPrestadorSedes sede : listSedes) {
                            
                            IpsSedesDTO ipsSedes = new IpsSedesDTO();
                            GnUbicaciones ubicacion = (GnUbicaciones) getEntityManager().find(GnUbicaciones.class, sede.getUbicacionId());
                            if (ubicacion != null) {
                                ipsSedes.setMunicipio(ubicacion.getNombre());
                            }
                            ipsSedes.setCodigoPrestador(sede.getCodigoPrestador());
                            ipsSedes.setDireccion(sede.getDireccion());
                            ipsSedes.setNombre(sede.getNombre());
                            ipsSedes.setCodigo(sede.getCodigo());
                            ipsSedes.setCodigoHabilitacion(sede.getCodigoHabilitacion());
                            ipsSedes.setZonaPrecedencia(sede.getZonaPrecedencia());
                            if (sede.getEstadoSede() != null) {
                                ipsSedes.setEstado(sede.getEstadoSede().toString());
                            }
                            if (sede.getNivelAtencion() != null) {
                                ipsSedes.setNivelAtencion(sede.getNivelAtencion().toString());
                            }
                            if (sede.getMaeClasePrestadorId() != null) {
                                ipsSedes.setClasePrestador(sede.getMaeClasePrestadorValor());
                            }
                            
                            ipsSedes.setFax(sede.getFax());
                            ipsSedes.setTelefonoCitas(sede.getTelefonoCitas());
                            ipsSedes.setCorreoElectronico(sede.getCorreoElectronico());
                            ipsSedes.setTelefonoAdministrativo(sede.getTelefonoAdministrativo());
                            listaIpsSedes.add(ipsSedes);
                        }
                    }
                    ips.setSedes(listaIpsSedes);
                }

//            
            }
            
        } catch (NoResultException e) {
            ips = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return ips;
    }

    //funciones utliidad
    public String retornarConector(int i) {
        if (i == 0) {
            return "WHERE ";
        } else {
            return "AND ";
        }
    }
    
    @Override
    public List<CntPrestadorSede> consultarListaSedes(String divipola) throws java.lang.Exception {
        List<CntPrestadorSede> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM CntPrestadorSedes p "
                    + "WHERE p.ubicacionId = '" + divipola + "' "
                    + "AND p.estadoSede = 1 "
                    + "AND p.nivelAtencion = 1"
                    + "ORDER BY p.id ";
            List<CntPrestadorSedes> list = getEntityManager().createQuery(strQuery).getResultList();
            for (CntPrestadorSedes per : list) {
                listResult.add(castPrestadorSedesEntidadNegocio(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
    @Override
    public HashMap<String, CntPrestadorSede> consultarHashSedesPorId(String divipola) throws Exception {
        HashMap<String, CntPrestadorSede> hashResult = new HashMap();
        String strQuery = "FROM CntPrestadorSedes p "
                + " WHERE p.ubicacionId = '" + divipola + "' "
                + " AND p.estadoSede = 1 "
                + " ORDER BY p.id ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<CntPrestadorSedes> list = query.getResultList();
            for (CntPrestadorSedes per : list) {
                CntPrestadorSede obj = castPrestadorSedesEntidadNegocio(per);
                hashResult.put(obj.getId().toString(), obj);
            }
        } catch (NoResultException e) {
            hashResult = new HashMap();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return hashResult;
    }
    
    @Override
    public List<CntPrestadorSede> consultarListaSedesCapitadas(String divipola) throws java.lang.Exception {
        List<CntPrestadorSede> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM CntPrestadorSedes p "
                    + "WHERE p.ubicacionId = '" + divipola + "' "
                    + "AND p.estadoSede = 1 "
                    + "AND p.capitacion = 1 "
                    // 2021-04-19 INC 767 comentamos el filtro por nivel de atención.
                    //+ "AND p.nivelAtencion = 1 "
                    + "ORDER BY p.id ";
            List<CntPrestadorSedes> list = getEntityManager().createQuery(strQuery).getResultList();
            for (CntPrestadorSedes per : list) {
                listResult.add(castPrestadorSedesEntidadNegocio(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
    @Override
    public HashMap<String, CntPrestadorSede> consultarHashSedesCapitadasPorId(String divipola) throws Exception {
        HashMap<String, CntPrestadorSede> hashResult = new HashMap();
        String strQuery = "FROM CntPrestadorSedes p "
                + "WHERE p.ubicacionId = '" + divipola + "' "
                + "AND p.estadoSede = 1 "
                + "AND p.capitacion = 1 "
                + "ORDER BY p.id ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<CntPrestadorSedes> list = query.getResultList();
            for (CntPrestadorSedes per : list) {
                CntPrestadorSede obj = castPrestadorSedesEntidadNegocio(per);
                hashResult.put(obj.getId().toString(), obj);
            }
        } catch (NoResultException e) {
            hashResult = new HashMap();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return hashResult;
    }
    
    @Override
    public CntPrestadorSede consultarSedePorCodigoHabilitacion(String codigo) throws java.lang.Exception {
        CntPrestadorSede result = null;
        int i = 0;
        try {
            String strQuery = "FROM CntPrestadorSedes p "
                    + "WHERE p.codigoHabilitacion = '" + codigo + "' "
                    + "AND p.estadoSede = 1 "
                    //2021-06-15 jyperez INC 812 se comenta la validación de nivel 1
                    //+ "AND p.nivelAtencion = 1 "
                    //2021-09-06 jyperez INC 979 incluir la validación de capitación = 1
                    + "AND p.capitacion = 1 "
                    + "ORDER BY p.id ";
            List<CntPrestadorSedes> list = getEntityManager().createQuery(strQuery).getResultList();
            for (CntPrestadorSedes per : list) {
                result = castPrestadorSedesEntidadNegocio(per);
            }
        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return result;
    }
    
    @Override
    public int consultarCantidadListaSede(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM CntPrestadorSedes p WHERE p.id > 0 ";
            strQuery += "AND  p.estadoSede = 1 ";
            //JaimeOlarte Para consultar por codigo habilitacion en referencia y contrareferencia 
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND  p.cntPrestadoresId.codigoMinSalud = '" + (String) paramConsulta.getParametroConsulta1() + "' ";
            }
            if (paramConsulta.getParametroConsulta5() != null) {
                strQuery += "AND  p.cntPrestadoresId.id = " + paramConsulta.getParametroConsulta5() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntPrestadoresId.tipoDocumento":
                            strQuery += "AND p.maeTipoDocumentoId  = " + e.getValue() + " ";
                            break;
                        case "cntPrestadoresId.numeroDocumento":
                            strQuery += "AND p.numeroDocumento  = " + e.getValue() + " ";
                            break;
                        case "cntPrestador.razonSocial":
                            strQuery += "AND p.cntPrestadoresId.razonSocial  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "codigoHabilitacionSede":
                            strQuery += "AND p.codigoHabilitacion  = '" + e.getValue() + "' ";
                            break;
                        case "nombreSede":
                            strQuery += "AND p.nombre  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "ubicacionId":
                            strQuery += "AND p.ubicacionId  = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.maeTipoDocumentoId":
                            strQuery += "AND p.cntPrestadoresId.maeTipoDocumentoId  = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntPrestador.numeroDocumento":
                            strQuery += "AND p.cntPrestadoresId.numeroDocumento  = '" + (String) e.getValue() + "' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public List<CntPrestadorSede> consultarListaSede(ParamConsulta paramConsulta) throws Exception {
        List<CntPrestadorSede> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntPrestadorSedes p WHERE p.id > 0 ";
            strQuery += "AND  p.estadoSede = 1 ";
            //JaimeOlarte Para consultar por codigo habilitacion en referencia y contrareferencia 
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND  p.cntPrestadoresId.codigoMinSalud = '" + (String) paramConsulta.getParametroConsulta1() + "' ";
            }
            if (paramConsulta.getParametroConsulta5() != null) {
                strQuery += "AND  p.cntPrestadoresId.id = " + paramConsulta.getParametroConsulta5() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntPrestadoresId.tipoDocumento":
                            strQuery += "AND p.maeTipoDocumentoId  = " + e.getValue() + " ";
                            break;
                        case "cntPrestadoresId.numeroDocumento":
                            strQuery += "AND p.numeroDocumento  = " + e.getValue() + " ";
                            break;
                        case "cntPrestador.razonSocial":
                            strQuery += "AND p.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "razonSocial":
                            strQuery += "AND p.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "codigoHabilitacionSede":
                            strQuery += "AND p.codigoHabilitacion  = '" + e.getValue() + "' ";
                            break;
                        case "nombreSede":
                            strQuery += "AND p.nombre  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "ubicacion.ubicacionPadre.id":
                            strQuery += "AND p.ubicacionId  = '" + e.getValue() + "' ";
                            break;
                        case "ubicacion.id":
                            strQuery += "AND p.ubicacionId  = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.maeTipoDocumentoId":
                            strQuery += "AND p.cntPrestadoresId.maeTipoDocumentoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntPrestador.numeroDocumento":
                            strQuery += "AND p.cntPrestadoresId.numeroDocumento  = '" + (String) e.getValue() + "' ";
                            break;
                        case "ubicacionId":
                            strQuery += "AND p.ubicacionId  = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<CntPrestadorSedes> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (CntPrestadorSedes per : list) {
                listResult.add(castPrestadorSedesEntidadNegocio(per));
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
    public int consultarCantidadBuscadorSede(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p.cntPrestadorSedesId) FROM CntContratoSedes p "
                    + "WHERE p.cntPrestadorSedesId.estadoSede = '1' ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoHabilitacionSede":
                            strQuery += "AND p.cntPrestadorSedesId.codigoHabilitacion  = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.maeTipoDocumentoId":
                            strQuery += "AND p.cntPrestadorSedesId.cntPrestadoresId.maeTipoDocumentoId  = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.numeroDocumento":
                            strQuery += "AND p.cntPrestadorSedesId.cntPrestadoresId.numeroDocumento  = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.razonSocial":
                            strQuery += "AND p.cntPrestadorSedesId.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreSede":
                            strQuery += "AND p.cntPrestadorSedesId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntContratosId.activo":
                            strQuery += "AND p.cntContratosId.activo  = " + e.getValue() + " ";
                            break;
                        case "fecha":
                            strQuery += "AND ('" + e.getValue() + "' BETWEEN p.cntContratosId.fechaInicio AND p.cntContratosId.fechaFin) ";
                            break;
                        case "listar":
                            String valor = (String) e.getValue();
                            if (!valor.equals("todos")) {
                                strQuery += "AND p.cntContratosId.gnEmpresasId.id = '" + valor + "' ";
                            }
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public List<CntPrestadorSede> consultarListaBuscadorSede(ParamConsulta paramConsulta) throws Exception {
        List<CntPrestadorSede> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p.cntPrestadorSedesId FROM CntContratoSedes p "
                    + "WHERE p.cntPrestadorSedesId.estadoSede = '1' ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoHabilitacionSede":
                            strQuery += "AND p.cntPrestadorSedesId.codigoHabilitacion  = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.maeTipoDocumentoId":
                            strQuery += "AND p.cntPrestadorSedesId.cntPrestadoresId.maeTipoDocumentoId  = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.numeroDocumento":
                            strQuery += "AND p.cntPrestadorSedesId.cntPrestadoresId.numeroDocumento   = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.razonSocial":
                            strQuery += "AND p.cntPrestadorSedesId.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreSede":
                            strQuery += "AND p.cntPrestadorSedesId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntContratosId.activo":
                            strQuery += "AND p.cntContratosId.activo  = " + e.getValue() + " ";
                            break;
                        case "fecha":
                            strQuery += "AND '" + e.getValue() + "' BETWEEN p.cntContratosId.fechaInicio AND p.cntContratosId.fechaFin ";
                            break;
                        case "listar":
                            String valor = (String) e.getValue();
                            if (!valor.equals("todos")) {
                                strQuery += "AND p.cntContratosId.gnEmpresasId.id = '" + valor + "' ";
                            }
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<CntPrestadorSedes> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntPrestadorSedes per : list) {
                listResult.add(castPrestadorSedesEntidadNegocio(per));
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
    public CntPrestador consultarPorNit(String nit) throws java.lang.Exception {
        CntPrestador objResult = null;
        int i = 0;
        try {
            String strQuery = "FROM CntPrestadores s "
                    + "WHERE s.numeroDocumento = :nit "
                    + "AND s.activo = '1'";
            strQuery += " ORDER BY s.razonSocial ASC";
            CntPrestadores cntPrestadores = (CntPrestadores) getEntityManager().createQuery(strQuery).setMaxResults(1)
                    .setParameter("nit", nit)
                    .getSingleResult();
            objResult = castEntidadNegocio(cntPrestadores);
        } catch (NoResultException e) {
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objResult;
    }
    
    @Override
    public CntPrestador consultarPorCodigoMinSalud(String codMinSalud) throws java.lang.Exception {
        CntPrestador objResult = null;
        int i = 0;
        try {
            String strQuery = "FROM CntPrestadores s "
                    + "WHERE s.codigoMinSalud = '" + codMinSalud + "' ";
            List<CntPrestadores> listaPrestadores = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntPrestadores pre : listaPrestadores) {
                if (i == 0) {
                    objResult = castEntidadNegocio(pre);
                    i++;
                }
            }
        } catch (NoResultException e) {
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objResult;
    }
    
    @Override
    public CntPrestador consultarPorIdYCodigoMinSalud(int id, String codMinSalud) throws java.lang.Exception {
        CntPrestador objResult = null;
        int i = 0;
        try {
            String strQuery = "FROM CntPrestadores s "
                    + "WHERE s.codigoMinSalud = '" + codMinSalud + "' AND s.id <> " + id;
            List<CntPrestadores> listaPrestadores = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntPrestadores pre : listaPrestadores) {
                if (i == 0) {
                    objResult = castEntidadNegocio(pre);
                    i++;
                }
            }
        } catch (NoResultException e) {
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objResult;
    }

    @Override
    public CntPrestador consultarPorTipoDocumentoYNumero(int id, String numeroDocumento) throws java.lang.Exception {
        CntPrestador objResult = null;
        int i = 0;
        try {
            String strQuery = "FROM CntPrestadores s "
                    + "WHERE s.maeTipoDocumentoId = " + id + " AND s.numeroDocumento = '" + numeroDocumento +"'";
            List<CntPrestadores> listaPrestadores = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntPrestadores pre : listaPrestadores) {
                if (i == 0) {
                    objResult = castEntidadNegocio(pre);
                    i++;
                }
            }
        } catch (NoResultException e) {
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objResult;
    }
    
    @Override
    public List<CntPrestadorSede> consultarListaSedesSinNivelAtencion(String divipola) throws java.lang.Exception {
        List<CntPrestadorSede> listResult = new ArrayList();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT ps FROM CntPrestadores cp  ");
            strQuery.append(" inner join CntPrestadorSedes ps on cp.id = ps.cntPrestadoresId AND ps.ubicacionId ='").append(divipola).append("' AND (ps.capitacion is null OR ps.capitacion = 0) and cp.nivelAtencion > 0  ");
            strQuery.append(" inner join GnUbicaciones gu on ps.ubicacionId = gu.id  ");
            strQuery.append(" ORDER BY ps.id ");
            List<CntPrestadorSedes> list = getEntityManager().createQuery(strQuery.toString()).getResultList();
            for (CntPrestadorSedes per : list) {
                listResult.add(castPrestadorSedesEntidadNegocio(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public CntPrestadorSede consultarSedePorCodigoHabilitacionActivo(String codigo) throws java.lang.Exception {
        CntPrestadorSede result = null;
        try {
            String strQuery = "FROM CntPrestadorSedes p "
                    + "WHERE p.codigoHabilitacion = '" + codigo + "' "
                    + "AND p.estadoSede = '1'"
                    + "ORDER BY p.id ";
            List<CntPrestadorSedes> list = getEntityManager().createQuery(strQuery).getResultList();
            for (CntPrestadorSedes per : list) {
                result = castPrestadorSedesEntidadNegocio(per);
            }
        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    @Override
    public int consultarCantidadListaSedeUnionTemporal(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(unionTemp) FROM CntPrestadores unionTemp ";
            strQuery += " JOIN CntPrestadorUnionTemporal cput ON unionTemp.id = cput.cntPrestadorUnionTemporalId.id ";
            strQuery += " JOIN CntPrestadores cpRelacionados ON cput.cntPrestadoresId.id = cpRelacionados.id ";
            //join cnt_prestador_sedes cps ON cp_relacionados.id = cps.cnt_prestadores_id
            strQuery += " JOIN CntPrestadorSedes p ON cpRelacionados.id = p.cntPrestadoresId.id ";
            strQuery += " WHERE p.id > 0 AND p.estadoSede = 1 ";
            //JaimeOlarte Para consultar por codigo habilitacion en referencia y contrareferencia 
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND  unionTemp.codigoMinSalud = '" + (String) paramConsulta.getParametroConsulta1() + "' ";
            }
            if (paramConsulta.getParametroConsulta5() != null) {
                strQuery += "AND  unionTemp.id = " + paramConsulta.getParametroConsulta5() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntPrestadoresId.tipoDocumento":
                            strQuery += "AND p.maeTipoDocumentoId  = " + e.getValue() + " ";
                            break;
                        case "cntPrestadoresId.numeroDocumento":
                            strQuery += "AND p.numeroDocumento  = " + e.getValue() + " ";
                            break;
                        case "cntPrestador.razonSocial":
                            strQuery += "AND p.cntPrestadoresId.razonSocial  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "codigoHabilitacionSede":
                            strQuery += "AND p.codigoHabilitacion  = '" + e.getValue() + "' ";
                            break;
                        case "nombreSede":
                            strQuery += "AND p.nombre  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "ubicacionId":
                            strQuery += "AND p.ubicacionId  = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.maeTipoDocumentoId":
                            strQuery += "AND p.cntPrestadoresId.maeTipoDocumentoId  = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntPrestador.numeroDocumento":
                            strQuery += "AND p.cntPrestadoresId.numeroDocumento  = '" + (String) e.getValue() + "' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public List<CntPrestadorSede> consultarListaSedeUnionTemporal(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntPrestadorSede> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p FROM CntPrestadores unionTemp ";
            strQuery += " JOIN CntPrestadorUnionTemporal cput ON unionTemp.id = cput.cntPrestadorUnionTemporalId.id ";
            strQuery += " JOIN CntPrestadores cpRelacionados ON cput.cntPrestadoresId.id = cpRelacionados.id ";
            //join cnt_prestador_sedes cps ON cp_relacionados.id = cps.cnt_prestadores_id
            strQuery += " JOIN CntPrestadorSedes p ON cpRelacionados.id = p.cntPrestadoresId.id ";
            strQuery += " WHERE p.id > 0 AND p.estadoSede = 1 ";
            //JaimeOlarte Para consultar por codigo habilitacion en referencia y contrareferencia 
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND  unionTemp.codigoMinSalud = '" + (String) paramConsulta.getParametroConsulta1() + "' ";
            }
            if (paramConsulta.getParametroConsulta5() != null) {
                strQuery += "AND  unionTemp.id = " + paramConsulta.getParametroConsulta5() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntPrestadoresId.tipoDocumento":
                            strQuery += "AND p.maeTipoDocumentoId  = " + e.getValue() + " ";
                            break;
                        case "cntPrestadoresId.numeroDocumento":
                            strQuery += "AND p.numeroDocumento  = " + e.getValue() + " ";
                            break;
                        case "cntPrestador.razonSocial":
                            strQuery += "AND p.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "razonSocial":
                            strQuery += "AND p.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "codigoHabilitacionSede":
                            strQuery += "AND p.codigoHabilitacion  = '" + e.getValue() + "' ";
                            break;
                        case "nombreSede":
                            strQuery += "AND p.nombre  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "ubicacion.ubicacionPadre.id":
                            strQuery += "AND p.ubicacionId  = '" + e.getValue() + "' ";
                            break;
                        case "ubicacion.id":
                            strQuery += "AND p.ubicacionId  = '" + e.getValue() + "' ";
                            break;
                        case "cntPrestador.maeTipoDocumentoId":
                            strQuery += "AND p.cntPrestadoresId.maeTipoDocumentoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntPrestador.numeroDocumento":
                            strQuery += "AND p.cntPrestadoresId.numeroDocumento  = '" + (String) e.getValue() + "' ";
                            break;
                        case "ubicacionId":
                            strQuery += "AND p.ubicacionId  = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<CntPrestadorSedes> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (CntPrestadorSedes per : list) {
                listResult.add(castPrestadorSedesEntidadNegocio(per));
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
}
