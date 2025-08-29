/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CntContratoDetalles;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Query;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(CntPrestadorSedeRemoto.class)
public class CntPrestadorSedeServicio extends GenericoServicio implements CntPrestadorSedeRemoto {
    
    private static final SimpleDateFormat FORMATO_SIN_HORA = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM CntPrestadorSedes p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoMinSalud":
                            strQuery += " AND p.codigoMinSalud LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoDocumentoId":
                            strQuery += " AND p.maeTipoDocumentoId  = " + e.getValue() + " ";
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
    public List<CntPrestadorSede> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CntPrestadorSede> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM CntPrestadorSedes p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "codigoMinSalud":
                            strQuery += " AND p.codigoMinSalud LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoDocumentoId":
                            strQuery += " AND p.maeTipoDocumentoId  = " + e.getValue() + " ";
                            break;
                        case "codigoHabilitacion":
                            strQuery += " AND p.codigoHabilitacion  = '" + e.getValue() + "' ";
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
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (CntPrestadorSedes per : list) {
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
    public CntPrestadorSede consultar(int id) throws Exception {
        CntPrestadorSede objRes = null;
        try {
            objRes = castEntidadNegocio((CntPrestadorSedes) getEntityManager().find(CntPrestadorSedes.class, id));
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
    public int insertar(CntPrestadorSede obj) throws Exception {
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
    public void actualizar(CntPrestadorSede obj) throws Exception {
        try {
            String sql = "UPDATE CntPrestadorSedes SET "
                    + "codigoPrestador = :codigoPrestador, "
                    + "ubicacionId = :ubicacionId, "
                    + "maeRegionId = :maeRegionId, "
                    + "maeRegionCodigo = :maeRegionCodigo, "
                    + "maeRegionValor = :maeRegionValor, "
                    + "direccion = :direccion, "
                    + "nombre = :nombre, "
                    + "codigo = :codigo, "
                    + "codigoHabilitacion = :codigoHabilitacion, "
                    + "zonaPrecedencia = :zonaPrecedencia, "
                    + "estadoSede = :estadoSede, "
                    + "nivelAtencion = :nivelAtencion, "
                    + "maeClasePrestadorId = :maeClasePrestadorId, "
                    + "maeClasePrestadorCodigo = :maeClasePrestadorCodigo, "
                    + "maeClasePrestadorValor = :maeClasePrestadorValor, "
                    + "fax = :fax, "
                    + "telefonoCitas = :telefonoCitas, "
                    + "correoElectronico = :correoElectronico, "
                    + "telefonoAdministrativo = :telefonoAdministrativo, "
                    + "capitacion = :capitacion, "
                    + "fechaFacturaElectronica = :fechaFacturaElectronica, "
                    + "grupoRipsMinisterio = :grupoRipsMinisterio, "
                    + "usuarioCrea = :usuarioCrea, "
                    + "terminalCrea = :terminalCrea, "
                    + "fechaHoraCrea = :fechaHoraCrea, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("codigoPrestador", obj.getCodigoPrestador());
            query.setParameter("ubicacionId", obj.getUbicacionId());
            query.setParameter("maeRegionId", obj.getMaeRegionId());
            query.setParameter("maeRegionCodigo", obj.getMaeRegionCodigo());
            query.setParameter("maeRegionValor", obj.getMaeRegionValor());
            query.setParameter("direccion", obj.getDireccion());
            query.setParameter("nombre", obj.getNombreSede());
            query.setParameter("codigo", obj.getCodigoSede());
            query.setParameter("codigoHabilitacion", obj.getCodigoHabilitacionSede());
            query.setParameter("zonaPrecedencia", obj.getZonaPrecedencia());
            query.setParameter("estadoSede", obj.getEstadoSede());
            query.setParameter("nivelAtencion", obj.getNivelAtencion());
            query.setParameter("maeClasePrestadorId", obj.getClasePrestador());
            query.setParameter("maeClasePrestadorCodigo", obj.getMaeClasePrestadorCodigo());
            query.setParameter("maeClasePrestadorValor", obj.getMaeClasePrestadorValor());
            query.setParameter("fax", obj.getFax());
            query.setParameter("telefonoCitas", obj.getTelefonoCitas());
            query.setParameter("correoElectronico", obj.getCorreoElectronico());
            query.setParameter("telefonoAdministrativo", obj.getTelefonoAdministrativo());
            query.setParameter("capitacion", obj.getCapitacion());
            query.setParameter("fechaFacturaElectronica", obj.getFechaFacturaElectronica());
            query.setParameter("grupoRipsMinisterio", obj.getGrupoRipsMinisterio());
            query.setParameter("usuarioCrea", obj.getUsuarioCrea());
            query.setParameter("terminalCrea", obj.getTerminalCrea());
            query.setParameter("fechaHoraCrea", obj.getFechaHoraCrea());
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
    public CntPrestadorSede eliminar(int id) throws Exception {
        CntPrestadorSede obj = null;
        try {
            CntPrestadorSedes ent = getEntityManager().find(CntPrestadorSedes.class, id);
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
    public List<CntPrestadorSede> consultarTodos() throws Exception {
        List<CntPrestadorSede> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntPrestadorSedes p "
                    + "ORDER BY p.id ";
            List<CntPrestadorSedes> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntPrestadorSedes per : list) {
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
    public List<CntPrestadorSede> consultarPorNombre(String nombre) throws java.lang.Exception {
        List<CntPrestadorSede> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM CntPrestadorSedes p "
                    + "WHERE p.nombre LIKE :nombre ";
            strQuery += " ORDER BY p.nombre ASC";
            List<CntPrestadorSedes> list = getEntityManager().createQuery(strQuery)
                    .setParameter("nombre", "%" + nombre + "%")
                    .getResultList();
            for (CntPrestadorSedes per : list) {
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

    public static CntPrestadorSede castEntidadNegocio(CntPrestadorSedes per) {
        CntPrestadorSede obj = new CntPrestadorSede();
        obj.setId(per.getId());
        obj.setUbicacionId(per.getUbicacionId());
        obj.setCodigoPrestador(per.getCodigoPrestador());
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
        //2024-11-08 jyperez nuevos campos
        obj.setGrupoRipsMinisterio(per.getGrupoRipsMinisterio());
        obj.setFechaFacturaElectronica(per.getFechaFacturaElectronica());
        if (per.getCntPrestadoresId() != null) {
            CntPrestador  cntPrestador = new CntPrestador();
            cntPrestador.setId(per.getCntPrestadoresId().getId());
            cntPrestador.setMaeTipoDocumentoId(per.getCntPrestadoresId().getMaeTipoDocumentoId());
            cntPrestador.setMaeTipoDocumentoCodigo(per.getCntPrestadoresId().getMaeTipoDocumentoCodigo());
            cntPrestador.setMaeTipoDocumentoValor(per.getCntPrestadoresId().getMaeTipoDocumentoValor());
            cntPrestador.setNumeroDocumento(per.getCntPrestadoresId().getNumeroDocumento());
            cntPrestador.setRazonSocial(per.getCntPrestadoresId().getRazonSocial());
            obj.setCntPrestador(cntPrestador);
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

    public static CntPrestadorSedes castNegocioEntidad(CntPrestadorSede obj) {
        CntPrestadorSedes per = new CntPrestadorSedes();
        per.setId(obj.getId());
        per.setUbicacionId(obj.getUbicacionId());
        per.setCodigoPrestador(obj.getCodigoPrestador());
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
        //2024-11-08 jyperez nuevos campos
        per.setGrupoRipsMinisterio(obj.getGrupoRipsMinisterio());
        per.setFechaFacturaElectronica(obj.getFechaFacturaElectronica());
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
    public List<CntPrestadorSede> consultarPorPrestador(int cntPrestadorId) throws java.lang.Exception {
        List<CntPrestadorSede> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p FROM CntPrestadorSedes p "
                    + "WHERE p.cntPrestadoresId.id = :cntPrestadorId "
                    + "AND p.estadoSede = 1 ";
            List<CntPrestadorSedes> list = getEntityManager().createQuery(strQuery)
                    .setParameter("cntPrestadorId", cntPrestadorId)
                    .getResultList();
            for (CntPrestadorSedes per : list) {
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
    public CntPrestadorSede consultarPorCodigoHabilitacion(String codigoHabilitacion) {
        CntPrestadorSede objResult = null;
        int i = 0;
        try {
            String strQuery = "SELECT p FROM CntPrestadorSedes p WHERE p.codigoHabilitacion = :codigoHabilitacion ";
            CntPrestadorSedes cntPrestadorSedes = (CntPrestadorSedes) getEntityManager().createQuery(strQuery)
                    .setParameter("codigoHabilitacion", codigoHabilitacion)
                    .setMaxResults(1)
                    .getSingleResult();
            objResult = castEntidadNegocio(cntPrestadorSedes);
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
    
    @Override
    public CntPrestadorSede consultarSedePorCodigoHabilitacionYEstado(String codigo) throws java.lang.Exception {
        CntPrestadorSede result = null;
        int i = 0;
        try {
            String strQuery = "FROM CntPrestadorSedes p "
                    + "WHERE p.codigoHabilitacion = '" + codigo + "' "
                    + "AND p.estadoSede = 1 "
                    + "ORDER BY p.id ";
            List<CntPrestadorSedes> list = getEntityManager().createQuery(strQuery).getResultList();
            for (CntPrestadorSedes per : list) {
                if (i == 0) {
                    result = castEntidadNegocio(per);
                    i++;
                }
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
    public int consultarCantidadListaGestionar(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM CntPrestadorSedes p WHERE p.id > 0 ";
            //parámetro id del prestador
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND P.cntPrestadoresId.id = " + (Integer) paramConsulta.getParametroConsulta1() + " ";
            }
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    
                    switch ((String) e.getKey()) {
                        case "codigoHabilitacionSede":
                            strQuery += " AND p.codigoHabilitacion LIKE '" + e.getValue() + "' ";
                            break;
                        case "nombreSede":
                            strQuery += " AND p.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "direccion":
                            strQuery += " AND p.direccion  LIKE '%" + e.getValue() + "%' ";
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
    public List<CntPrestadorSede> consultarListaGestionar(ParamConsulta paramConsulta) throws Exception {
        List<CntPrestadorSede> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM CntPrestadorSedes p WHERE p.id > 0 ";
            //parámetro id del prestador
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND P.cntPrestadoresId.id = " + (Integer) paramConsulta.getParametroConsulta1() + " ";
            }
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    
                    switch ((String) e.getKey()) {
                        case "codigoHabilitacionSede":
                            strQuery += " AND p.codigoHabilitacion LIKE '" + e.getValue() + "' ";
                            break;
                        case "nombreSede":
                            strQuery += " AND p.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "direccion":
                            strQuery += " AND p.direccion  LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                
                switch(paramConsulta.getOrden()) {
                    case "codigoHabilitacionSede":
                    strQuery += "p.codigoHabilitacion "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                    break;
                    case "nombreSede":
                    strQuery += "p.nombre "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                    break;
                    default:
                    strQuery += "p." + paramConsulta.getOrden() + " "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                    break;
                }
            } else {
                strQuery += "p.id DESC";
            }
            List<CntPrestadorSedes> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (CntPrestadorSedes per : list) {
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
    public int consultarCantidadListaConContrato(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            Date fechaActual = new Date();
            String strQuery = "SELECT COUNT(p) FROM CntContratoDetalles p WHERE p.activo = 1 "
                    + " AND p.fechaHoraInicio >= '"+FORMATO_SIN_HORA.format(fechaActual)+"' AND p.fechaHoraFin <= '"+FORMATO_SIN_HORA.format(fechaActual)+"' ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND p.tipoTecnologia = "+paramConsulta.getParametroConsulta1().toString();
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += " AND p.maTecnologiaId = "+paramConsulta.getParametroConsulta2().toString();
            }
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
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
    public List<CntPrestadorSede> consultarListaConContrato(ParamConsulta paramConsulta) throws Exception {
        List<CntPrestadorSede> listResult = new ArrayList();
        try {
            Date fechaActual = new Date();
            String strQuery = "FROM CntContratoDetalles p WHERE p.activo = 1"
                    + " AND p.fechaHoraInicio >= '"+FORMATO_SIN_HORA.format(fechaActual)+"' AND p.fechaHoraFin <= '"+FORMATO_SIN_HORA.format(fechaActual)+"' ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND p.tipoTecnologia = "+paramConsulta.getParametroConsulta1().toString();
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += " AND p.maTecnologiaId = "+paramConsulta.getParametroConsulta2().toString();
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                    }
                }
            }
            List<CntContratoDetalles> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntContratoDetalles per : list) {
                listResult.add(castEntidadNegocio(per.getCntContratoSedesId().getCntPrestadorSedesId()));
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
