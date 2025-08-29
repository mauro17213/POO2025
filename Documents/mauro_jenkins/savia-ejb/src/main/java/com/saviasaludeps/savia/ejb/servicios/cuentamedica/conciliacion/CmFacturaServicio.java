/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDevolucion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupo;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCarga;
import com.saviasaludeps.savia.dominio.facturacionelectronica.CmFeNota;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaDevoluciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.CmFacturas;
import com.saviasaludeps.savia.ejb.entidades.CmFeNotas;
import com.saviasaludeps.savia.ejb.entidades.CmGlosaRespuestas;
import com.saviasaludeps.savia.ejb.entidades.CmGrupos;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargas;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria.CmAuditoriaDevolucionServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaRemoto;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(CmFacturaRemoto.class)
@Local(CmFacturaLocal.class)
public class CmFacturaServicio extends GenericoServicio implements CmFacturaLocal, CmFacturaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cmf) FROM CmFacturas cmf ";
            strQuery += " WHERE cmf.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "numeroRadicado":
                            strQuery += " AND cmf.numeroRadicado = '" + e.getValue() + "' ";
                            break;
                        case "nit":
                            strQuery += " AND cmf.nit = '" + e.getValue() + "' ";
                            break;
                        case "ips":
                            strQuery += " AND cmf.ips = '" + e.getValue() + "' ";
                            break;
                        case "numeroFacturado":
                            strQuery += " AND cmf.numeroFacturado = '" + e.getValue() + "' ";
                            break;
                        case "fechaPrestacion":
                            strQuery += " AND cmf.fechaPrestacion = " + e.getValue() + " ";
                            break;
                        case "tipoContrato":
                            strQuery += " AND cmf.tipoContrato = '" + e.getValue() + "' ";
                            break;
                        case "regimen":
                            strQuery += " AND cmf.regimen = '" + e.getValue() + "' ";
                            break;
                        case "valorFactura":
                            strQuery += " AND cmf.valorFactura  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "valorInicialGlosa":
                            strQuery += " AND cmf.valorInicialGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "valorPendienteActual":
                            strQuery += " AND cmf.valorPendienteActual LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "valorPendienteGlosa":
                            strQuery += " AND cmf.cmGlosasList.valorPendiente LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estadoFactura":
                            strQuery += " AND cmf.estadoFactura = '" + e.getValue() + "' ";
                            break;
                        case "cmDetalleFactura.nombreCompletoAfiliado":
                            strQuery += "";
                            break;
                        case "origenFactura":
                            strQuery += " AND cmf.origenFactura = " + e.getValue() + "";
                            break;
                        case "cmDetalleFactura.documento":
                            strQuery += "";
                            break;
                        case "version":
                             strQuery += " AND cmf.version = " + e.getValue() + "";
                            break; 
                    }
                }
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fhInicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
                query.setParameter("fhFin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            }

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
    public List<CmFactura> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmFactura> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmFacturas cmf ";
            strQuery += " WHERE cmf.id > 0  ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "numeroRadicado":
                            strQuery += " AND cmf.numeroRadicado = '" + e.getValue() + "' ";
                            break;
                        case "nit":
                            strQuery += " AND cmf.nit = '" + e.getValue() + "' ";
                            break;
                        case "ips":
                            strQuery += " AND cmf.ips = '" + e.getValue() + "' ";
                            break;
                        case "numeroFacturado":
                            strQuery += " AND cmf.numeroFacturado = '" + e.getValue() + "' ";
                            break;
                        case "fechaPrestacion":
                            strQuery += " AND cmf.fechaPrestacion = " + e.getValue() + " ";
                            break;
                        case "tipoContrato":
                            strQuery += " AND cmf.tipoContrato = '" + e.getValue() + "' ";
                            break;
                        case "regimen":
                            strQuery += " AND cmf.regimen = '" + e.getValue() + "' ";
                            break;
                        case "valorFactura":
                            strQuery += " AND cmf.valorFactura LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "valorInicialGlosa":
                            strQuery += " AND cmf.valorInicialGlosa LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "valorPendienteGlosa":
                            strQuery += " AND cmf.cmGlosasList.valorPendiente LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "valorPendienteActual":
                            strQuery += " AND cmf.valorPendienteActual LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estadoFactura":
                            strQuery += " AND cmf.estadoFactura = '" + e.getValue() + "' ";
                            break;
                        case "cmDetalleFactura.nombreCompletoAfiliado":
                            strQuery += "";
                            break;
                        case "cmDetalleFactura.documento":
                            strQuery += "";
                            break;
                        case "origenFactura":
                            strQuery += " AND cmf.origenFactura = " + e.getValue() + "";
                            break;
                        case "version":
                             strQuery += " AND cmf.version = " + e.getValue() + "";
                            break; 
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                String order = paramConsulta.getOrden().
                        replace("gsAfiliado", "gsAfiliadosId").
                        replace("gsZona", "gsZonasId");

                strQuery += " cmf." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " cmf.id DESC ";
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fhInicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
                query.setParameter("fhFin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            }
            List<CmFacturas> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CmFacturas neg : list) {
                listResult.add(castEntidadNegocio(neg));
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
    public List<CmFactura> consultarPorCargaYEstado(int idCarga, int estado) throws java.lang.Exception {
        List<CmFactura> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmFacturas cmf ";
            strQuery += "WHERE cmf.id > 0 ";
            strQuery += "AND cmRipsCargasId.id = :idCarga ";
            strQuery += "AND estadoFactura = :estado ";

            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("idCarga", idCarga);
            query.setParameter("estado", estado);
            List<CmFacturas> list = query.getResultList();
            for (CmFacturas neg : list) {
                listResult.add(castEntidadNegocio(neg));
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
    public List<CmFactura> consultarPorIdCargaRip(int idCarga) throws java.lang.Exception {
        List<CmFactura> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmFacturas cmf ";
            strQuery += "WHERE cmf.id > 0 ";
            strQuery += "AND cmf.cmRipsCargasId.id = :idCarga ";

            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("idCarga", idCarga);
            List<CmFacturas> list = query.getResultList();
            for (CmFacturas neg : list) {
                listResult.add(castEntidadNegocio(neg));
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
    public List<CmFactura> consultarPorIdFeCargaRip(int idCarga) throws java.lang.Exception {
        List<CmFactura> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmFacturas cmf ";
            strQuery += "WHERE cmf.id > 0 ";
            strQuery += "AND cmf.cmFeRipsCargasId.id = :idCarga ";

            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("idCarga", idCarga);
            List<CmFacturas> list = query.getResultList();
            for (CmFacturas neg : list) {
                listResult.add(castEntidadNegocio(neg));
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
    public CmFactura consultar(int id) throws Exception {
        CmFactura obj = null;
        try {
            CmFacturas per = (CmFacturas) getEntityManager().find(CmFacturas.class, id);
            if (per != null) {
                obj = castEntidadNegocio(per);
            }
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public int insertar(CmFactura obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al insertar la factura");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(CmFactura obj) throws Exception {
        try {
            CmFacturas facturas = castNegocioEntidad(obj);
            String hql = "UPDATE CmFacturas SET ";
            
            if (facturas.getGnEmpresasId() != null && facturas.getGnEmpresasId().getId() != null ) {
                hql += " gnEmpresasId.id = :empresasId,";
            }
            
            if (facturas.getCmRipsCargasId() != null && facturas.getCmRipsCargasId().getId() != null ) {
                hql += " cmRipsCargasId.id = :cmRipsCargasId,";
            }
            if (facturas.getCmGruposId() != null && facturas.getCmGruposId().getId() != null) {
                hql += " cmGruposId.id = :cmGruposId,";
            }
            
            if (facturas.getCntPrestadoresId() != null && facturas.getCntPrestadoresId().getId() != null) {
                hql += " cntPrestadoresId.id  = :cntPrestadoresId,";
            }
            hql +=    " nit = :nit,"
                    + " ips  = :ips,"
                    + " numeroRadicado = :numeroRadicado,"
                    + " fechaRadicacion = :fechaRadicacion,"
                    + " numeroFacturado = :numeroFacturado,"
                    + " fechaPrestacion = :fechaPrestacion,"
                    + " maeTipoContratoId = :maeTipoContratoId,"
                    + " maeTipoContratoCodigo = :maeTipoContratoCodigo,"
                    + " maeTipoContratoValor = :maeTipoContratoValor,"
                    + " maeRegimenId = :maeRegimenId,"
                    + " maeRegimenCodigo = :maeRegimenCodigo,"
                    + " maeRegimenValor = :maeRegimenValor,"
                    + " valorFactura = :valorFactura,"
                    + " valorPagadoFactura = :valorPagadoFactura,"
                    + " estadoFactura = :estadoFactura,"
                    + " multiusuario = :multiusuario,"
                    + " valorInicialGlosa = :valorInicialGlosa,"
                    + " valorPendienteActual = :valorPendienteActual,";
            if (facturas.getGnUsuariosLiderId() != null) {
                hql += " gnUsuariosLiderId.id = :gnUsuariosLiderId,";
            }

            if (facturas.getGnUsuariosTecnicoId() != null) {
                hql += " gnUsuariosTecnicoId.id = :gnUsuariosTecnicoId,";
            }

            if (facturas.getGnUsuariosMedicoId() != null) {
                hql += " gnUsuariosMedicoId.id = :gnUsuariosMedicoId,";
            }

            if (facturas.getGnUsuariosGestionaId() != null) {
                hql += " gnUsuariosGestionaId.id = :gnUsuariosGestionaId,";
            }

            hql += " marcacion = :marcacion,"
                    + " fechaMarcacion = :fechaMarcacion,"
                    + " fechaVencimiento = :fechaVencimiento,"
                    + " tipoAuditoria = :tipoAuditoria,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);

            if (facturas.getGnEmpresasId() != null && facturas.getGnEmpresasId().getId() != null) {
                query.setParameter("empresasId", facturas.getGnEmpresasId().getId());
            }

            if (facturas.getCmRipsCargasId() != null && facturas.getCmRipsCargasId().getId() != null) {
                query.setParameter("cmRipsCargasId", facturas.getCmRipsCargasId().getId());
            }
     
            if (facturas.getCmGruposId() != null && facturas.getCmGruposId().getId() != null ) {
                query.setParameter("cmGruposId", facturas.getCmGruposId().getId());
            }
            
            if (facturas.getCntPrestadoresId() != null && facturas.getCntPrestadoresId().getId() != null ) {
                query.setParameter("cntPrestadoresId", facturas.getCntPrestadoresId().getId());
            }
            
            query.setParameter("nit", facturas.getNit());
            query.setParameter("ips", facturas.getIps());
            query.setParameter("numeroRadicado", facturas.getNumeroRadicado());
            query.setParameter("fechaRadicacion", facturas.getFechaRadicacion());
            query.setParameter("numeroFacturado", facturas.getNumeroFacturado());
            query.setParameter("fechaPrestacion", facturas.getFechaPrestacion());
            query.setParameter("maeTipoContratoId", facturas.getMaeTipoContratoId());
            query.setParameter("maeTipoContratoCodigo", facturas.getMaeTipoContratoCodigo());
            query.setParameter("maeTipoContratoValor", facturas.getMaeTipoContratoValor());
            query.setParameter("maeRegimenId", facturas.getMaeRegimenId());
            query.setParameter("maeRegimenCodigo", facturas.getMaeRegimenCodigo());
            query.setParameter("maeRegimenValor", facturas.getMaeRegimenValor());
            query.setParameter("valorFactura", facturas.getValorFactura());
            query.setParameter("valorPagadoFactura", facturas.getValorPagadoFactura());
            query.setParameter("estadoFactura", facturas.getEstadoFactura());
            query.setParameter("multiusuario", facturas.getMultiusuario());
            query.setParameter("valorInicialGlosa", facturas.getValorInicialGlosa());
            query.setParameter("valorPendienteActual", facturas.getValorPendienteActual());

            if (facturas.getGnUsuariosLiderId() != null && facturas.getGnUsuariosLiderId().getId() != null  ) {
                query.setParameter("gnUsuariosLiderId", facturas.getGnUsuariosLiderId().getId());
            }
            if (facturas.getGnUsuariosTecnicoId() != null && facturas.getGnUsuariosTecnicoId().getId() != null) {
                query.setParameter("gnUsuariosTecnicoId", facturas.getGnUsuariosTecnicoId().getId());
            }
            if (facturas.getGnUsuariosMedicoId() != null && facturas.getGnUsuariosMedicoId().getId() != null) {
                query.setParameter("gnUsuariosMedicoId", facturas.getGnUsuariosMedicoId().getId());
            }
            if (facturas.getGnUsuariosGestionaId() != null && facturas.getGnUsuariosGestionaId().getId() != null) {
                //si el identificador es cero es por que se desea setear la asignacion.
                Integer idUsuario = facturas.getGnUsuariosGestionaId().getId() != 0
                        ? facturas.getGnUsuariosGestionaId().getId() : null;
                query.setParameter("gnUsuariosGestionaId", idUsuario);
            }

            query.setParameter("marcacion", facturas.getMarcacion());
            query.setParameter("fechaMarcacion", facturas.getFechaMarcacion());
            query.setParameter("fechaVencimiento", facturas.getFechaVencimiento());
            query.setParameter("tipoAuditoria", facturas.getTipoAuditoria());
            query.setParameter("usuarioModifica", facturas.getUsuarioModifica());
            query.setParameter("terminalModifica", facturas.getTerminalModifica());
            query.setParameter("fechaHoraModifica", facturas.getFechaHoraModifica());
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
    public CmFactura eliminar(int id) throws Exception {
        CmFactura obj = null;
        try {
            CmFacturas per = getEntityManager().find(CmFacturas.class, id);
            if (per != null) {
                obj = castEntidadNegocio(per);
                getEntityManager().remove(per);
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
    public void cambiarEstadoFacturas(List<CmFactura> listFacturas, int estado) throws Exception {
        try {
            if ( ! listFacturas.isEmpty() ) {
                
                StringBuilder stringBuilder = new StringBuilder(10000000);
                for (CmFactura cmFactura : listFacturas) {
                    stringBuilder.append(cmFactura.getId());
                    stringBuilder.append(",");
                }
                String idFacturasParaActualizar = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);

                String hql = "UPDATE CmFacturas SET "
                        + " estadoFactura  = :estadoFactura  WHERE id IN ( " + idFacturasParaActualizar + " )";

                Query query = getEntityManager().createQuery(hql);
                query.setParameter("estadoFactura", estado);
                query.executeUpdate();
            }
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {

        }
    }
    
    public static CmFactura castEntidadNegocio(CmFacturas neg) {
        CmFactura ent = new CmFactura();
        ent.setId(neg.getId());

        CmRipsCarga ripCarga = new CmRipsCarga();
        if (neg.getCmRipsCargasId() != null && neg.getCmRipsCargasId().getId() != null) {
            ripCarga = new CmRipsCarga(neg.getCmRipsCargasId().getId());
            Integer idCntContrato = (neg.getCmRipsCargasId().getCntContratosId()) != null
                    ? neg.getCmRipsCargasId().getCntContratosId().getId() : null;
            ripCarga.setCntContrato(new CntContrato(idCntContrato));
        }
        ent.setCmRipCarga(ripCarga);

        if (neg.getCmGruposId() != null) {
            ent.setCmGrupo(new CmGrupo(neg.getCmGruposId().getId(),
                    neg.getCmGruposId().getNombre(),
                    neg.getCmGruposId().getDescripcion()
            ));
        }
        if (neg.getGnEmpresasId() != null) {
            ent.setEmpresa(new Empresa(neg.getGnEmpresasId().getId()));
        }
        if (neg.getCntPrestadoresId() != null) {
            CntPrestador cntPrestador = new CntPrestador(neg.getCntPrestadoresId().getId());
            cntPrestador.setCodigoMinSalud(neg.getCntPrestadoresId().getCodigoMinSalud());
            ent.setCntPrestador(cntPrestador);
        }
        
        if (neg.getCmFeRipsCargasId() != null && neg.getCmFeRipsCargasId().getId() != null) {
            ent.setCmFeRipsCarga(new CmFeRipsCarga(neg.getCmFeRipsCargasId().getId()));
        }
        
        ent.setNit(neg.getNit());
        ent.setIps(neg.getIps());
        ent.setNumeroRadicado(neg.getNumeroRadicado());
        ent.setFechaRadicacion(neg.getFechaRadicacion());
        ent.setNumeroFacturado(neg.getNumeroFacturado());
        ent.setFechaPrestacion(neg.getFechaPrestacion());
        ent.setMaeTipoContratoId(neg.getMaeTipoContratoId());
        ent.setMaeTipoContratoCodigo(neg.getMaeTipoContratoCodigo());
        ent.setMaeTipoContratoValor(neg.getMaeTipoContratoValor());

        ent.setMaeRegimenId(neg.getMaeRegimenId());
        ent.setMaeRegimenCodigo(neg.getMaeRegimenCodigo());
        ent.setMaeRegimenValor(neg.getMaeRegimenValor());
        ent.setValorFactura(neg.getValorFactura());
        ent.setValorPagadoFactura(neg.getValorPagadoFactura());
        ent.setEstadoFactura(neg.getEstadoFactura());
        ent.setMultiUsuario(neg.getMultiusuario());
        ent.setValorInicialGlosa(neg.getValorInicialGlosa());
        ent.setValorPendienteActual(neg.getValorPendienteActual());
        ent.setPbs(Optional.ofNullable(neg.getPbs()).orElse(false));
        ent.setVersion(Optional.ofNullable(neg.getVersion()).orElse(false));

        Usuario usuarioLider = new Usuario();
        if (neg.getGnUsuariosLiderId() != null) {
            usuarioLider = new Usuario(neg.getGnUsuariosLiderId().getId(),
                    neg.getGnUsuariosLiderId().getUsuario(),
                    neg.getGnUsuariosLiderId().getNombre());
            usuarioLider.setCorreoElectronico(neg.getGnUsuariosLiderId().getCorreoElectronico());
        }
        ent.setUsuarioLider(usuarioLider);

        Usuario usuarioMedico = new Usuario();
        if (neg.getGnUsuariosMedicoId() != null) {
            usuarioMedico = new Usuario(neg.getGnUsuariosMedicoId().getId(),
                    neg.getGnUsuariosMedicoId().getUsuario(),
                    neg.getGnUsuariosMedicoId().getNombre());
            usuarioMedico.setCorreoElectronico(neg.getGnUsuariosMedicoId().getCorreoElectronico());
        }
        ent.setUsuarioMedico(usuarioMedico);

        Usuario usuarioTecnico = new Usuario();
        if (neg.getGnUsuariosTecnicoId() != null) {
            usuarioTecnico = new Usuario(neg.getGnUsuariosTecnicoId().getId(),
                    neg.getGnUsuariosTecnicoId().getUsuario(),
                    neg.getGnUsuariosTecnicoId().getNombre());
            usuarioTecnico.setCorreoElectronico(neg.getGnUsuariosTecnicoId().getCorreoElectronico());
        }
        ent.setUsuarioTecnico(usuarioTecnico);

        Usuario usuarioGestiona = new Usuario();
        if (neg.getGnUsuariosGestionaId() != null) {
            usuarioGestiona = new Usuario(neg.getGnUsuariosGestionaId().getId(),
                    neg.getGnUsuariosGestionaId().getUsuario(),
                    neg.getGnUsuariosGestionaId().getNombre());
            usuarioGestiona.setCorreoElectronico(neg.getGnUsuariosGestionaId().getCorreoElectronico());
        }
        
        
        if (!neg.getCmFeNotasList().isEmpty()) {
            CmFeNotas nota = neg.getCmFeNotasList().get(0);
            ent.setCmFeNota(new CmFeNota( nota.getId(),nota.getTipo(),nota.getNumeroNota(),
                    nota.getValorNota(),nota.getCude(),nota.getFechaHoraEmision(),
                    nota.getUsuarioCrea(), nota.getTerminalCrea(), nota.getFechaHoraCrea()));
        }
        
        ent.setUsuarioGestiona(usuarioGestiona);
        ent.setMarcacion(neg.getMarcacion());
        ent.setFechaMarcacion(neg.getFechaMarcacion());
        ent.setFechaVencimiento(neg.getFechaVencimiento());
        ent.setHistorialProceso(neg.getHistorialProceso());
        ent.setTipoAuditoria(neg.getTipoAuditoria());
        ent.setValorCopago(neg.getValorCopago());
        ent.setValorCuotaModeradora(neg.getValorCuotaModeradora());
        ent.setNumeroContrato(neg.getNumeroContrato());
        ent.setValorBruto(neg.getValorBruto());
        ent.setOrigenFactura(neg.getOrigenFactura());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setUsuarioModifica(neg.getUsuarioModifica());
        ent.setTerminalModifica(neg.getTerminalModifica());
        ent.setFechaHoraModifica(neg.getFechaHoraModifica());
        int cantidaProcesosEjecutdos
                = neg.getCmGlosaRespuestasList() != null ? neg.getCmGlosaRespuestasList().size() : 0;
        ent.setCantidadProcesosEjecutados(cantidaProcesosEjecutdos);
        CmAuditoriaDevolucion devolucionEnt = new CmAuditoriaDevolucion();
        if (ent.isDevuelta()) {
            CmAuditoriaDevoluciones devolucion = neg.getCmAuditoriaDevolucionesList() != null
                    && !neg.getCmAuditoriaDevolucionesList().isEmpty()
                    ? neg.getCmAuditoriaDevolucionesList().get(0)
                    : new CmAuditoriaDevoluciones();
            if (devolucion.getId() != null) {
                devolucionEnt = CmAuditoriaDevolucionServicio.castEntidadNegocioCorto(devolucion);
            }
        }
        ent.setCmAuditoriaDevolucion(devolucionEnt);
       
        ent.setUsuarioAudita(neg.getUsuarioAudita());
        ent.setTerminalAudita(neg.getTerminalAudita());
        ent.setFechaHoraAudita(neg.getFechaHoraAudita());
        ent.setUsuarioRespuesta(neg.getUsuarioRespuesta());
        ent.setTerminalRespuesta(neg.getTerminalRespuesta());
        ent.setFechaHoraRespuesta(neg.getFechaHoraRespuesta());
        ent.setUsuarioConcilia(neg.getUsuarioConcilia());
        ent.setTerminalConcilia(neg.getTerminalConcilia());
        ent.setFechaHoraConcilia(neg.getFechaHoraConcilia());
        ent.setUsuarioDevuelve(neg.getUsuarioDevuelve());
        ent.setTerminalDevuelve(neg.getTerminalDevuelve());
        ent.setFechaHoraDevuelve(neg.getFechaHoraDevuelve());
        ent.setFechaAsignacionMedico(neg.getFechaAsignacionMedico());
        ent.setFechaAsignacionTecnico(neg.getFechaAsignacionTecnico());
        
        return ent;
    }
    
    public static CmFactura castSinObjetosEntidadNegocio(CmFacturas neg) {
        CmFactura ent = new CmFactura();
        ent.setId(neg.getId());

        ent.setNit(neg.getNit());
        ent.setIps(neg.getIps());
        ent.setNumeroRadicado(neg.getNumeroRadicado());
        ent.setFechaRadicacion(neg.getFechaRadicacion());
        ent.setNumeroFacturado(neg.getNumeroFacturado());
        ent.setFechaPrestacion(neg.getFechaPrestacion());
        ent.setMaeTipoContratoId(neg.getMaeTipoContratoId());
        ent.setMaeTipoContratoCodigo(neg.getMaeTipoContratoCodigo());
        ent.setMaeTipoContratoValor(neg.getMaeTipoContratoValor());
        CntPrestadores prestador = Optional.ofNullable(neg.getCntPrestadoresId()).
                orElse(new CntPrestadores());
        ent.setIdCntPrestador(Optional.ofNullable(prestador.getId()).orElse(0));

        ent.setMaeRegimenId(neg.getMaeRegimenId());
        ent.setMaeRegimenCodigo(neg.getMaeRegimenCodigo());
        ent.setMaeRegimenValor(neg.getMaeRegimenValor());
        ent.setValorFactura(neg.getValorFactura());
        ent.setValorPagadoFactura(neg.getValorPagadoFactura());
        ent.setEstadoFactura(neg.getEstadoFactura());
        ent.setMultiUsuario(neg.getMultiusuario());
        ent.setValorInicialGlosa(neg.getValorInicialGlosa());
        ent.setValorPendienteActual(neg.getValorPendienteActual());
        ent.setPbs(Optional.ofNullable(neg.getPbs()).orElse(false));
        ent.setVersion(Optional.ofNullable(neg.getVersion()).orElse(false));

        ent.setMarcacion(neg.getMarcacion());
        ent.setFechaMarcacion(neg.getFechaMarcacion());
        ent.setFechaVencimiento(neg.getFechaVencimiento());
        ent.setHistorialProceso(neg.getHistorialProceso());
        ent.setTipoAuditoria(neg.getTipoAuditoria());
        ent.setValorCopago(neg.getValorCopago());
        ent.setValorCuotaModeradora(neg.getValorCuotaModeradora());
        ent.setNumeroContrato(neg.getNumeroContrato());
        ent.setValorBruto(neg.getValorBruto());
        ent.setOrigenFactura(neg.getOrigenFactura());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setUsuarioModifica(neg.getUsuarioModifica());
        ent.setTerminalModifica(neg.getTerminalModifica());
        ent.setFechaHoraModifica(neg.getFechaHoraModifica());
        int cantidaProcesosEjecutdos
                = neg.getCmGlosaRespuestasList() != null ? neg.getCmGlosaRespuestasList().size() : 0;
        ent.setCantidadProcesosEjecutados(cantidaProcesosEjecutdos);
       
        if (neg.getCmFeRipsCargasId() != null) {
            ent.setCmFeRipsCarga(new CmFeRipsCarga(neg.getCmFeRipsCargasId().getId()));
        }
        ent.setUsuarioAudita(neg.getUsuarioAudita());
        ent.setTerminalAudita(neg.getTerminalAudita());
        ent.setFechaHoraAudita(neg.getFechaHoraAudita());
        ent.setUsuarioRespuesta(neg.getUsuarioRespuesta());
        ent.setTerminalRespuesta(neg.getTerminalRespuesta());
        ent.setFechaHoraRespuesta(neg.getFechaHoraRespuesta());
        ent.setUsuarioConcilia(neg.getUsuarioConcilia());
        ent.setTerminalConcilia(neg.getTerminalConcilia());
        ent.setFechaHoraConcilia(neg.getFechaHoraConcilia());
        ent.setUsuarioDevuelve(neg.getUsuarioDevuelve());
        ent.setTerminalDevuelve(neg.getTerminalDevuelve());
        ent.setFechaHoraDevuelve(neg.getFechaHoraDevuelve());
        ent.setFechaAsignacionMedico(neg.getFechaAsignacionMedico());
        ent.setFechaAsignacionTecnico(neg.getFechaAsignacionTecnico());
        
        return ent;
    }

    public static CmFacturas castNegocioEntidad(CmFactura obj) {
        CmFacturas ent = new CmFacturas();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }

        CmRipsCargas ripCarga = new CmRipsCargas();
        if (obj.getCmRipCarga() != null && obj.getCmRipCarga().getId() != null) {
            ripCarga = new CmRipsCargas(obj.getCmRipCarga().getId());
        }
        ent.setCmRipsCargasId(ripCarga);

        if (obj.getCmGrupo() != null && obj.getCmGrupo().getId() != null) {
            ent.setCmGruposId(new CmGrupos(obj.getCmGrupo().getId()));
        }

        if (obj.getEmpresa() != null  && obj.getEmpresa().getId() != null) {
            ent.setGnEmpresasId(new GnEmpresas(obj.getEmpresa().getId()));
        }
        if (obj.getCntPrestador() != null && obj.getCntPrestador().getId() != null) {
            ent.setCntPrestadoresId(new CntPrestadores(obj.getCntPrestador().getId()));
        }
        String nit = obj.getNit().equals("") ? " " : obj.getNit();
        ent.setNit(nit);
        String ips = obj.getIps().equals("") ? " " : obj.getIps();
        ent.setIps(ips);
        ent.setNumeroRadicado(obj.getNumeroRadicado());
        ent.setFechaRadicacion(obj.getFechaRadicacion());
        String numeroFacturado = obj.getNumeroFacturado().equals("") ? " " : obj.getNumeroFacturado();
        ent.setNumeroFacturado(numeroFacturado);
        ent.setFechaPrestacion(obj.getFechaPrestacion());
        ent.setMaeTipoContratoId(obj.getMaeTipoContratoId());
        if (obj.getMaeTipoContratoCodigo() != null) {
            ent.setMaeTipoContratoCodigo(obj.getMaeTipoContratoCodigo());
        }
        if (obj.getMaeTipoContratoValor() != null) {
            ent.setMaeTipoContratoValor(obj.getMaeTipoContratoValor());
        }
        ent.setMaeRegimenId(obj.getMaeRegimenId());
        if (obj.getMaeRegimenCodigo() != null) {
            ent.setMaeRegimenCodigo(obj.getMaeRegimenCodigo());
        }
        if (obj.getMaeRegimenValor() != null) {
            ent.setMaeRegimenValor(obj.getMaeRegimenValor());
        }
        ent.setValorFactura(obj.getValorFactura());
        ent.setValorPagadoFactura(obj.getValorPagadoFactura());
        ent.setEstadoFactura(obj.getEstadoFactura());
        ent.setMultiusuario(obj.isMultiUsuario());
        ent.setValorInicialGlosa(obj.getValorInicialGlosa());
        ent.setValorPendienteActual(obj.getValorPendienteActual());
        ent.setPbs(obj.isPbs());
        if (obj.getUsuarioLider() != null && obj.getUsuarioLider().getId() != null) {
            ent.setGnUsuariosLiderId(new GnUsuarios(obj.getUsuarioLider().getId()));
        }

        if (obj.getUsuarioTecnico() != null && obj.getUsuarioTecnico().getId() != null) {
            ent.setGnUsuariosTecnicoId(new GnUsuarios(obj.getUsuarioTecnico().getId()));
        }

        if (obj.getUsuarioMedico() != null && obj.getUsuarioMedico().getId() != null) {
            ent.setGnUsuariosMedicoId(new GnUsuarios(obj.getUsuarioMedico().getId()));
        }

        if (obj.getUsuarioGestiona() != null && obj.getUsuarioGestiona().getId() != null) {
            ent.setGnUsuariosGestionaId(new GnUsuarios(obj.getUsuarioGestiona().getId()));
        }

        if (obj.getHistorialProceso() != null) {
            ent.setHistorialProceso(obj.getHistorialProceso());
        }
        ent.setMarcacion(obj.getMarcacion());
        if (obj.getFechaMarcacion() != null) {
            ent.setFechaMarcacion(obj.getFechaMarcacion());
        }
        if (obj.getFechaVencimiento() != null) {
            ent.setFechaVencimiento(obj.getFechaVencimiento());
        }
        ent.setNumeroContrato(obj.getNumeroContrato());
        ent.setValorCopago(obj.getValorCopago());
        ent.setValorCuotaModeradora(obj.getValorCuotaModeradora());
        ent.setFechaRadicacion(obj.getFechaRadicacion());
        ent.setTipoAuditoria(obj.getTipoAuditoria());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        return ent;
    }

    @Override
    public void actualizarEstadoGestion(ParamConsulta paramConsulta) throws java.lang.Exception {
        try {
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null
                    && paramConsulta.getParametroConsulta3() != null) {
                String hql = "UPDATE CmFacturas f SET"
                        + " f.gnUsuariosGestionaId.id =:valor"
                        + " WHERE f.id IN (" + paramConsulta.getParametroConsulta3() + ") and (f.gnUsuariosGestionaId.id = :usuarioGestionaIn or "
                        + " f.gnUsuariosGestionaId.id IS NULL ) ";
                Query query = getEntityManager().createQuery(hql);
                Integer idUsuarioAsignar = (Integer) paramConsulta.getParametroConsulta1() > 0
                        ? (Integer) paramConsulta.getParametroConsulta1() : null;
                query.setParameter("valor", idUsuarioAsignar);
                query.setParameter("usuarioGestionaIn", paramConsulta.getParametroConsulta2());
                query.executeUpdate();
            }
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarMarcadoGlosaIPS(ParamConsulta paramConsulta) throws java.lang.Exception {
        try {
            if (paramConsulta.getParametroConsulta1() != null &&
                paramConsulta.getParametroConsulta2() != null) {
                String hql = "UPDATE CmFacturas f SET"
                        + " f.respuestaIps =: respuestaIps , "
                        + " f.fechaMarcacionRespuestaIps =: fechaMarcacion"
                        + " WHERE f.id IN (" + paramConsulta.getParametroConsulta1() + ")  ";
                Query query = getEntityManager().createQuery(hql);
                query.setParameter("respuestaIps", paramConsulta.getParametroConsulta2());
                query.setParameter("fechaMarcacion", paramConsulta.getParametroConsulta3());
                query.executeUpdate();
            }
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarUsuarioGestionFacturasMasiva(ParamConsulta paramConsulta) throws java.lang.Exception {
        try {
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                String hql = "UPDATE CmFacturas f SET"
                        + " f.gnUsuariosGestionaId.id =:usuarioGestiona"
                        + " WHERE f.id IN (" + paramConsulta.getParametroConsulta1() + ") ";
                Query query = getEntityManager().createQuery(hql);
                Integer idUsuarioAsignar = (Integer) paramConsulta.getParametroConsulta2() > 0
                        ? (Integer) paramConsulta.getParametroConsulta2() : null;
                query.setParameter("usuarioGestiona", idUsuarioAsignar);
                query.executeUpdate();
            }
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public String consultarFacturasBloquedas(ParamConsulta paramConsulta) throws java.lang.Exception {
        String facturasBloquedas = "";
        try {
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                String Idfacturas = (String) paramConsulta.getParametroConsulta1();
                String strQuery = "FROM CmFacturas cmf ";
                strQuery += " WHERE cmf.id IN (" + Idfacturas + ") AND "
                        + "( cmf.gnUsuariosGestionaId.id != :usuarioGestionaIn  AND "
                        + "  cmf.gnUsuariosGestionaId.id IS NOT NULL )";
                Query query = getEntityManager().createQuery(strQuery);
                query.setParameter("usuarioGestionaIn", paramConsulta.getParametroConsulta2());
                List<CmFacturas> list = query.getResultList();
                StringBuilder sb = new StringBuilder();
                for (CmFacturas neg : list) {
                    sb.append(" Número Radicado : ");
                    sb.append(neg.getNumeroRadicado());
                    sb.append(" Usuario Bloquea : ");
                    sb.append(neg.getGnUsuariosGestionaId().getNombre());
                    sb.append("(");
                    sb.append(neg.getGnUsuariosGestionaId().getCorreoElectronico());
                    sb.append(")");
                    sb.append("-");
                }
                if (sb.length() > 0) {
                    facturasBloquedas = sb.deleteCharAt(sb.length() - 1).toString();
                }
            }
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return facturasBloquedas;
    }

    @Override
    public Map<String, String> consultarIps(ParamConsulta paramConsulta) throws java.lang.Exception {
        Map<String, String> ips = new HashMap<>();
        try {
            if ((paramConsulta.getParametroConsulta1() != null && !paramConsulta.getParametroConsulta1().equals(""))
                    || (paramConsulta.getParametroConsulta2() != null && !paramConsulta.getParametroConsulta2().equals(""))) {
                String strQuery = "SELECT DISTINCT(cmf.nit) , cmf.ips FROM CmFacturas cmf WHERE cmf.id > 0 ";
                if ( paramConsulta.getParametroConsulta1() != null && ! paramConsulta.getParametroConsulta1().equals("") ) {
                    strQuery += " AND cmf.nit LIKE '%" + paramConsulta.getParametroConsulta1() + "%' ";
                }
                if ( paramConsulta.getParametroConsulta2() != null && ! paramConsulta.getParametroConsulta2().equals("") ) {
                    strQuery += " AND cmf.ips LIKE '%" + paramConsulta.getParametroConsulta2() + "%' ";
                }
                
                Query query = getEntityManager().createQuery(strQuery).setMaxResults(50);
                List<Object[]> list = query.getResultList();
                for (Object[] item : list) {
                    String nitInp = (String) item[0];
                    String ipsOut = (String) item[1];
                    if (ips.get(nitInp) == null) {
                        ips.put(nitInp, ipsOut);
                    }
                }
            }
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return ips;
    }

    @Override
    public List<CmFactura> consultarPorRadicado(ParamConsulta paramConsulta) throws Exception {
        List<CmFactura> listaFacturas = new ArrayList();
        try {
            if (paramConsulta.getParametroConsulta1() != null) {
                String idRadicacion = (String) paramConsulta.getParametroConsulta1();
                String strQuery = "FROM CmFacturas cmf WHERE cmf.id > 0 AND cmf.numeroRadicado IN (" + idRadicacion + ") ";
                Query query = getEntityManager().createQuery(strQuery);
                List<CmFacturas> list = query.getResultList();
                for (CmFacturas neg : list) {
                    CmFactura ent = castEntidadNegocio(neg);
                    if (neg.getCmGlosaRespuestasList() != null
                            && !neg.getCmGlosaRespuestasList().isEmpty()) {
                        CmGlosaRespuestas ultimaRespuesta = neg.getCmGlosaRespuestasList().
                                get(neg.getCmGlosaRespuestasList().size() - 1);
                        ent.setTipoRespuesta(ultimaRespuesta.getTipoRespuesta());
                    }
                    listaFacturas.add(ent);
                }
            }
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaFacturas;
    }
    
    @Override
    public List<CmFactura> consultarPorNumerosFacturados(ParamConsulta paramConsulta) throws Exception {
        List<CmFactura> listaFacturas = new ArrayList();
        try {
            if (paramConsulta.getParametroConsulta1() != null) {
                String numerosFacturados = (String) paramConsulta.getParametroConsulta1();
                String strQuery = "FROM CmFacturas cmf WHERE cmf.id > 0 AND cmf.numeroFacturado IN (" +numerosFacturados + ") "
                                + " and cmf.estadoFactura != " + CmFactura.ESTADO_FACTURA_ANULADA;
                Query query = getEntityManager().createQuery(strQuery);
                List<CmFacturas> list = query.getResultList();
                for (CmFacturas neg : list) {
                    CmFactura ent = castSinObjetosEntidadNegocio(neg);
                    if (neg.getCmGlosaRespuestasList() != null
                            && !neg.getCmGlosaRespuestasList().isEmpty()) {
                        CmGlosaRespuestas ultimaRespuesta = neg.getCmGlosaRespuestasList().
                                get(neg.getCmGlosaRespuestasList().size() - 1);
                        ent.setTipoRespuesta(ultimaRespuesta.getTipoRespuesta());
                    }
                    listaFacturas.add(ent);
                }
            }
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaFacturas;
    }

    @Override
    public List<CmFactura> consultarPorAtributos(ParamConsulta paramConsulta) throws Exception {
        List<CmFactura> listaFacturas = new ArrayList();
        try {
            if (paramConsulta.getParametroConsulta1() != null) {

                String strQuery = "FROM CmFacturas cmf WHERE cmf.id > 0  ";

                if (paramConsulta.getParametroConsulta1() != null) {
                    String numeroFacturado = (String) paramConsulta.getParametroConsulta1();
                    strQuery += " AND  cmf.numeroFacturado = '" + numeroFacturado + "' ";
                }
                if (paramConsulta.getParametroConsulta2() != null) {
                    String nit = (String) paramConsulta.getParametroConsulta2();
                    strQuery += " AND cmf.nit = '" + nit + "' ";
                }      
                if (paramConsulta.getParametroConsulta3() != null) {
                    strQuery += " AND cmf.estadoFactura NOT IN(" + CmFactura.ESTADO_FACTURA_ANULADA + ") ";
                }
                
                strQuery += " ORDER BY cmf.id DESC ";

                Query query = getEntityManager().createQuery(strQuery);
                List<CmFacturas> list = query.getResultList();
                for (CmFacturas neg : list) {
                    CmFactura ent = castEntidadNegocio(neg);
                    if (neg.getCmGlosaRespuestasList() != null
                            && !neg.getCmGlosaRespuestasList().isEmpty()) {
                        CmGlosaRespuestas ultimaRespuesta = neg.getCmGlosaRespuestasList().
                                get(neg.getCmGlosaRespuestasList().size() - 1);
                        ent.setTipoRespuesta(ultimaRespuesta.getTipoRespuesta());
                    }
                    listaFacturas.add(ent);
                }
            }
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaFacturas;
    }

    @Override
    public List<CmFactura> consultarPorPrestadorYFactura(int idPrestador, String numeroFacturado) throws java.lang.Exception {
        List<CmFactura> listaFacturas = new ArrayList();
        try {
            String strQuery = "FROM CmFacturas cmf WHERE cmf.numeroFacturado = '" + numeroFacturado + "' ";
            strQuery += "AND cmf.cntPrestadoresId.id = " + idPrestador + " ";
            strQuery += "AND cmf.estadoFactura <> 5 ";
            strQuery += "ORDER BY cmf.id DESC ";
            Query query = getEntityManager().createQuery(strQuery);
            List<CmFacturas> list = query.getResultList();
            for (CmFacturas neg : list) {
                CmFactura ent = castEntidadNegocio(neg);
                if (neg.getCmGlosaRespuestasList() != null
                        && !neg.getCmGlosaRespuestasList().isEmpty()) {
                    CmGlosaRespuestas ultimaRespuesta = neg.getCmGlosaRespuestasList().
                            get(neg.getCmGlosaRespuestasList().size() - 1);
                    ent.setTipoRespuesta(ultimaRespuesta.getTipoRespuesta());
                }
                listaFacturas.add(ent);
            }
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaFacturas;
    }

    @Override
    public Map<String, CmFactura> hashCosultarPorRadicado(ParamConsulta paramConsulta) throws java.lang.Exception {
        Map<String, CmFactura> mapFacturasEncontradas = new HashMap<>();
        try {
            List<CmFactura> listaFacturas = consultarPorRadicado(paramConsulta);
            for (CmFactura factura : listaFacturas) {
                mapFacturasEncontradas.put(String.valueOf(factura.getNumeroRadicado()), factura);
            }
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return mapFacturasEncontradas;
    }
    
    @Override
    public Map<String, CmFactura> hashCosultarPorNumeroFacturado(ParamConsulta paramConsulta) throws java.lang.Exception {
        Map<String, CmFactura> mapFacturasEncontradas = new HashMap<>();
        try {
            List<CmFactura> listaFacturas = consultarPorNumerosFacturados(paramConsulta);
            for (CmFactura factura : listaFacturas) {
                mapFacturasEncontradas.put(factura.getNumeroRadicado() + "-" + factura.getNumeroFacturado(), factura);
            }
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return mapFacturasEncontradas;
    }

    @Override
    public void actualizarHistorialProcesoFactura(ParamConsulta paramConsulta) throws java.lang.Exception {
        try {
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                String hql = "UPDATE CmFacturas f SET"
                        + " f.historialProceso =:historialProceso"
                        + " WHERE f.id = :id ";
                Query query = getEntityManager().createQuery(hql);
                query.setParameter("historialProceso", paramConsulta.getParametroConsulta1());
                query.setParameter("id", paramConsulta.getParametroConsulta2());
                query.executeUpdate();
            }
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarEstadoAuditoria(ParamConsulta paramConsulta) throws java.lang.Exception {
        try {
            if (paramConsulta.getParametroConsulta1() != null) {
                String hql = "UPDATE CmFacturas f SET";
                if (paramConsulta.getParametroConsulta2() != null) {
                    hql += " f.tipoAuditoria = :tipoAuditoria ";
                }

                if (paramConsulta.getParametroConsulta2() != null
                        && paramConsulta.getParametroConsulta3() != null) {
                    hql += ",";
                }

                if (paramConsulta.getParametroConsulta3() != null) {
                    hql += " f.estadoFactura = :estadoFactura ";
                }
                hql += " WHERE f.id = :id ";

                Query query = getEntityManager().createQuery(hql);
                if (paramConsulta.getParametroConsulta2() != null) {
                    query.setParameter("tipoAuditoria", paramConsulta.getParametroConsulta2());
                }
                if (paramConsulta.getParametroConsulta3() != null) {
                    query.setParameter("estadoFactura", paramConsulta.getParametroConsulta3());
                }
                query.setParameter("id", paramConsulta.getParametroConsulta1());
                query.executeUpdate();
            }
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarUsuarioSegunMomento(ParamConsulta paramConsulta) throws java.lang.Exception {
        try {
            boolean existeEstado = true;
            boolean hayModificacionFechaAuditoria = true;
            if (paramConsulta.getParametroConsulta1() != null) {
                String hql = "UPDATE CmFacturas cmf SET";

                switch ((int) paramConsulta.getParametroConsulta1()) {

                    case CmFactura.ESTADO_FACTURA_AUDITADA_EXITOSA:
                        hql += " cmf.usuarioAudita = :usuarioMomento ,";
                        hql += " cmf.terminalAudita = :terminalMomento ";
                        //hql += " cmf.fechaHoraAudita = :fechaHoraMomento ";
                        hayModificacionFechaAuditoria = false;
                        break;
                    case CmFactura.ESTADO_FACTURA_GLOSADA:
                        hql += " cmf.usuarioRespuesta = :usuarioMomento ,";
                        hql += " cmf.terminalRespuesta = :terminalMomento ,";
                        hql += " cmf.fechaHoraRespuesta = :fechaHoraMomento ";
                        break;
                    case CmFactura.ESTADO_FACTURA_CONCILIADA:
                        hql += " cmf.usuarioConcilia = :usuarioMomento ,";
                        hql += " cmf.terminalConcilia = :terminalMomento ,";
                        hql += " cmf.fechaHoraConcilia = :fechaHoraMomento ";
                        break;
                    case CmFactura.ESTADO_FACTURA_DEVUELTA:
                        hql += " cmf.usuarioDevuelve = :usuarioMomento ,";
                        hql += " cmf.terminalDevuelve = :terminalMomento ";
                        //hql += " cmf.fechaHoraDevuelve = :fechaHoraMomento ";
                        hayModificacionFechaAuditoria = false;
                        break;

                    default:
                        existeEstado = false;
                        break;
                }

                hql += " WHERE cmf.id = :id ";

                if (existeEstado) {

                    Query query = getEntityManager().createQuery(hql);
                    if (paramConsulta.getParametroConsulta3() != null) {
                        query.setParameter("usuarioMomento", paramConsulta.getParametroConsulta3());
                    }
                    if (paramConsulta.getParametroConsulta4() != null) {
                        query.setParameter("terminalMomento", paramConsulta.getParametroConsulta4());
                    }

                    if (paramConsulta.getParametroConsulta5() != null && hayModificacionFechaAuditoria) {
                        query.setParameter("fechaHoraMomento", paramConsulta.getParametroConsulta5());
                    }

                    query.setParameter("id", paramConsulta.getParametroConsulta2());
                    query.executeUpdate();
                }
            }
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarUsuarioGestiona(ParamConsulta paramConsulta) throws java.lang.Exception {
        try {
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                String hql = "UPDATE CmFacturas f SET";
                hql += " f.gnUsuariosGestionaId.id = :idUsuarioGestiona ";
                hql += " WHERE f.id = :id ";

                Integer idUsuarioGestiona = (Integer) paramConsulta.getParametroConsulta2() == 0 ? null
                        : (Integer) paramConsulta.getParametroConsulta2();

                Query query = getEntityManager().createQuery(hql);
                query.setParameter("id", paramConsulta.getParametroConsulta1());
                query.setParameter("idUsuarioGestiona", idUsuarioGestiona);
                query.executeUpdate();
            }
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarUsuarioGestionaAsignado(ParamConsulta paramConsulta) throws java.lang.Exception {
        try {
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null
                    && paramConsulta.getParametroConsulta3() != null) {

                String hql = "UPDATE CmFacturas f SET";
                switch ((Integer) paramConsulta.getParametroConsulta1()) {
                    case CmFactura.TIPO_USUARIO_AUDITORIA_LIDER://Lider
                        hql += " f.gnUsuariosLiderId.id = :idUsuarioGestiona, ";
                        break;
                    case CmFactura.TIPO_USUARIO_AUDITORIA_TECNICO://Tecnico
                        hql += " f.gnUsuariosTecnicoId.id = :idUsuarioGestiona, ";
                        hql += " f.tipoAuditoria = '"+CmFactura.TIPO_AUDITORIA_PERTINENCIA_TECNICA+"', ";
                        hql += " f.fechaAsignacionTecnico = :fechaHoraModifica , ";
                        break;
                    case CmFactura.TIPO_USUARIO_AUDITORIA_MEDICO://Medico
                        hql += " f.gnUsuariosMedicoId.id = :idUsuarioGestiona, ";
                        hql += " f.tipoAuditoria = '"+CmFactura.TIPO_AUDITORIA_PERTINENCIA_MEDICA+"', ";
                        hql += " f.fechaAsignacionMedico = :fechaHoraModifica, ";
                        break;
                }

                hql += " f.gnUsuariosGestionaId.id = :idUsuarioGestiona, ";
                hql += " usuarioModifica = :usuarioModifica, "
                        + " terminalModifica = :terminalModifica, "
                        + " fechaHoraModifica = :fechaHoraModifica ";
                hql += " WHERE f.id = :id ";

                Query query = getEntityManager().createQuery(hql);
                query.setParameter("idUsuarioGestiona", paramConsulta.getParametroConsulta2());
                query.setParameter("id", paramConsulta.getParametroConsulta3());

                query.setParameter("usuarioModifica", paramConsulta.getParametroConsulta4());
                query.setParameter("terminalModifica", paramConsulta.getParametroConsulta5());
                query.setParameter("fechaHoraModifica", paramConsulta.getParametroConsulta6());
                query.executeUpdate();
            }
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarFechaAuditoriaCmFactura(String idsCmFactura, Date fechaAuditoria) throws java.lang.Exception {
        try {
            String hql = "UPDATE CmFacturas f SET";
            hql += " f.fechaHoraAudita = :fechaHoraAudita ";
            hql += " WHERE f.id IN ("+idsCmFactura+") ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("fechaHoraAudita", fechaAuditoria);
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarFechaDevolucionCmFactura(String idsCmFactura, Date fechaDevolucion) throws java.lang.Exception {
        try {
            String hql = "UPDATE CmFacturas f SET";
            hql += " f.fechaHoraDevuelve = :fechaHoraDevuelve ";
            hql += " WHERE f.id IN ("+idsCmFactura+") ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("fechaHoraDevuelve", fechaDevolucion);
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public boolean verificarFacturaMonousurio(ParamConsulta paramConsulta) throws Exception {
        boolean esFacturaMonoUsuario = false;
        try {

            if (paramConsulta.getParametroConsulta1() != null) {

                String strQuery = "SELECT cmd.cmFacturasId.id, "
                        + "COUNT(distinct cmd.documento) "
                        + "FROM CmDetalles cmd WHERE cmd.cmFacturasId.id = :idfactura "
                        + "GROUP BY cmd.cmFacturasId.id "
                        + "HAVING COUNT( distinct cmd.documento ) = 1 ";
                Query query = getEntityManager().createQuery(strQuery);

                if (paramConsulta.getParametroConsulta1() != null) {
                    query.setParameter("idfactura", paramConsulta.getParametroConsulta1());
                }

                List<Object> list = query.getResultList();

                esFacturaMonoUsuario = list != null && !list.isEmpty();
            }

        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return esFacturaMonoUsuario;

    }

    @Override
    public int consultarPorNumeroFacturadoYCarga(String numeroFacturado, int idCarga) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(id) FROM CmFacturas "
                    + "WHERE numeroFacturado = :numero_facturado "
                    + "AND cmRipsCargasId.id = :id_carga ";
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("id_carga", idCarga);
            query.setParameter("numero_facturado", numeroFacturado);
            cant = (int) (long) query.getSingleResult();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

}
