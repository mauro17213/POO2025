/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupo;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCarga;
import com.saviasaludeps.savia.dominio.facturacionelectronica.CmFeNota;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmDetalles;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.CmFacturas;
import com.saviasaludeps.savia.ejb.entidades.CmFeNotas;
import com.saviasaludeps.savia.ejb.entidades.CmGlosaRespuestas;
import com.saviasaludeps.savia.ejb.entidades.CmGrupos;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargas;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaGlosaRemoto;
import java.util.ArrayList;
import java.util.Date;
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
@Remote(CmFacturaGlosaRemoto.class)
@Local(CmFacturaGlosaLocal.class)
public class CmFacturaGlosaServicio extends GenericoServicio implements CmFacturaGlosaLocal, CmFacturaGlosaRemoto {
  
    public final static String ESTADO_BUSQUEDA_DEFECTO = CmFactura.ESTADO_FACTURA_GLOSADA+ "," +
                CmFactura.ESTADO_FACTURA_ESPERA_SAP_PARA_RESPUESTA_CONCILIACION + "," + 
                CmFactura.ESTADO_FACTURA_CONCILIADA ;

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cmf.id) FROM CmFacturas cmf ";
            strQuery += " WHERE cmf.id > 0 AND  cmf.gnEmpresasId.id = :empresaid AND cmf.estadoFactura IN ("+ ESTADO_BUSQUEDA_DEFECTO +") ";
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                strQuery += " AND cmf.fechaRadicacion BETWEEN :fhInicio AND :fhFin ";
            }
            
            if (paramConsulta.getParametroConsulta5() != null) {
                strQuery += " AND cmf.cmFeRipsCargasId.id IS NULL ";
            }
             
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
                            strQuery += " AND cmf.ips LIKE '%" + e.getValue() + "%' ";
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
                        case "tipoRespuesta":
                            if (e.getValue().equals("0")) {
                                strQuery += " AND cmf.cmGlosaRespuestasList  IS EMPTY ";
                            } else {
                                strQuery += " AND 1 <=  "
                                        + "( SELECT cmg.id  FROM CmGlosaRespuestas cmg WHERE cmg.id = (SELECT MAX( cmg2.id ) FROM CmGlosaRespuestas cmg2 WHERE cmg2.cmFacturasId.id = cmf.id) "
                                        + " AND cmg.tipoRespuesta = '" + e.getValue() + "'"
                                        + "  ) ";
                            }
                            break;
                        case "cmDetalleFactura.nombreCompletoAfiliado":
                            strQuery += "";
                            break;
                        case "cmDetalleFactura.documento":
                            strQuery += "";
                            break;
                        case "tipoAuditoria":
                            strQuery += " AND cmf.tipoAuditoria = '" + e.getValue() + "' ";
                            break;
                        case "multiUsuario":
                            strQuery += " AND cmf.multiusuario = " + e.getValue() + "";
                            break;
                         case "origenFactura":
                            strQuery += " AND cmf.origenFactura = " + e.getValue() + "";
                            break;
                        case "estadoFactura":
                            strQuery += " AND cmf.estadoFactura = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            //validar al menos una glosa en la consulta 
            //strQuery += " AND cmf.cmDetallesList IS NOT EMPTY ";
            Query query = getEntityManager().createQuery(strQuery).setParameter("empresaid", paramConsulta.getEmpresaId());

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
            String strQuery = "SELECT cmf  FROM CmFacturas cmf ";
            strQuery += " WHERE cmf.id > 0 AND cmf.gnEmpresasId.id = :empresaid  AND cmf.estadoFactura IN ("+ESTADO_BUSQUEDA_DEFECTO+") ";
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                strQuery += " AND cmf.fechaRadicacion BETWEEN :fhInicio AND :fhFin ";
            }
            
            if (paramConsulta.getParametroConsulta5() != null) {
                strQuery += " AND cmf.cmFeRipsCargasId.id IS NULL ";
            }
            
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
                            strQuery += " AND cmf.ips LIKE '%" + e.getValue() + "%' ";
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
                        case "tipoRespuesta":
                            if (e.getValue().equals("0")) {
                                strQuery += " AND cmf.cmGlosaRespuestasList  IS EMPTY ";
                            } else {
                                strQuery += " AND 1 <=  "
                                        + "( SELECT cmg.id  FROM CmGlosaRespuestas cmg WHERE cmg.id = (SELECT MAX( cmg2.id ) FROM CmGlosaRespuestas cmg2 WHERE cmg2.cmFacturasId.id = cmf.id) "
                                        + " AND cmg.tipoRespuesta = '" + e.getValue() + "'"
                                        + "  ) ";
                            }
                            break;
                        case "cmDetalleFactura.nombreCompletoAfiliado":
                            strQuery += "";
                            break;
                        case "cmDetalleFactura.documento":
                            strQuery += "";
                            break;
                        case "tipoAuditoria":
                            strQuery += " AND cmf.tipoAuditoria = '" + e.getValue() + "' ";
                            break;
                        case "multiUsuario":
                            strQuery += " AND cmf.multiusuario = " + e.getValue() + "";
                            break;
                        case "origenFactura":
                            strQuery += " AND cmf.origenFactura = " + e.getValue() + "";
                            break;
                        case "estadoFactura":
                            strQuery += " AND cmf.estadoFactura = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                String order = paramConsulta.getOrden().replace("gsAfiliado", "gsAfiliadosId").
                        replace("gsZona", "gsZonasId");
                strQuery += " cmf." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " cmf.id  DESC";
            }
            Query query = getEntityManager().createQuery(strQuery).setParameter("empresaid", paramConsulta.getEmpresaId());
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
                CmFactura ent = castEntidadNegocio(neg);
                if (neg.getCmGlosaRespuestasList() != null
                        && !neg.getCmGlosaRespuestasList().isEmpty()) {
                    CmGlosaRespuestas ultimaRespuesta = neg.getCmGlosaRespuestasList().
                            get(neg.getCmGlosaRespuestasList().size() - 1);
                    ent.setTipoRespuesta(ultimaRespuesta.getTipoRespuesta());
                }
                listResult.add(ent);
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
            obj = castEntidadNegocio(per);
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
            Exception(INSERTAR, e, "Error al insertar la solicitud");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(CmFactura obj) throws Exception {
        try {
            obj.setId((int) getEntityManager().merge(castNegocioEntidad(obj)).getId());
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

    public static CmFactura castEntidadNegocio(CmFacturas neg) {
        CmFactura ent = new CmFactura();
        ent.setId(neg.getId());
        
        CmRipsCarga ripCarga = new CmRipsCarga();
        if(neg.getCmRipsCargasId() != null && neg.getCmRipsCargasId().getId() != null){
           ripCarga = new CmRipsCarga(neg.getCmRipsCargasId().getId());
        }
        ent.setCmRipCarga(ripCarga);
               
        if(neg.getCmGruposId() != null){
          ent.setCmGrupo(new CmGrupo(neg.getCmGruposId().getId(),
                           neg.getCmGruposId().getNombre(),
                           neg.getCmGruposId().getDescripcion()
          ));
        }     
        if (neg.getGnEmpresasId() != null) {
            ent.setEmpresa(new Empresa(neg.getGnEmpresasId().getId()));
        }
        if (neg.getCntPrestadoresId() != null) {
            ent.setCntPrestador(new CntPrestador(neg.getCntPrestadoresId().getId(),
                    neg.getCntPrestadoresId().getRazonSocial(),
                    neg.getCntPrestadoresId().getNombreRepresentanteLegal()
            ));
        }
        if(neg.getCmFeRipsCargasId() != null){
            ent.setCmFeRipsCarga(new CmFeRipsCarga(neg.getCmFeRipsCargasId().getId()));
        }
        ent.setNumeroRadicado(neg.getNumeroRadicado());
        ent.setNit(neg.getNit());
        ent.setIps(neg.getIps());
        ent.setNumeroFacturado(neg.getNumeroFacturado());
        ent.setFechaRadicacion(neg.getFechaRadicacion());
        ent.setFechaPrestacion(neg.getFechaPrestacion());
        ent.setMaeTipoContratoId(neg.getMaeTipoContratoId());
        ent.setMaeTipoContratoCodigo(neg.getMaeTipoContratoCodigo() );
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
        ent.setValorBruto(neg.getValorBruto());
        ent.setValorCopago(neg.getValorCopago());
        
        Usuario usuarioLider = new Usuario();
        if(neg.getGnUsuariosLiderId() != null){
           usuarioLider = new Usuario(neg.getGnUsuariosLiderId().getId(), 
                                      neg.getGnUsuariosLiderId().getUsuario(),
                                      neg.getGnUsuariosLiderId().getNombre());
           usuarioLider.setCorreoElectronico(neg.getGnUsuariosLiderId().getCorreoElectronico());
        }
        ent.setUsuarioLider(usuarioLider);
        
        Usuario usuarioMedico = new Usuario();
        if(neg.getGnUsuariosMedicoId()!= null){
           usuarioMedico = new Usuario(neg.getGnUsuariosMedicoId().getId(), 
                                       neg.getGnUsuariosMedicoId().getUsuario(),
                                       neg.getGnUsuariosMedicoId().getNombre());
           usuarioMedico.setCorreoElectronico(neg.getGnUsuariosMedicoId().getCorreoElectronico());
        }
        ent.setUsuarioMedico(usuarioMedico);
        
        Usuario usuarioTecnico = new Usuario();
        if(neg.getGnUsuariosTecnicoId()!= null){
            usuarioTecnico = new Usuario(neg.getGnUsuariosTecnicoId().getId(), 
                                         neg.getGnUsuariosTecnicoId().getUsuario(),
                                         neg.getGnUsuariosTecnicoId().getNombre());
           usuarioTecnico.setCorreoElectronico(neg.getGnUsuariosTecnicoId().getCorreoElectronico());
        }
        ent.setUsuarioTecnico(usuarioTecnico);
         
        Usuario usuarioGestiona= new Usuario();
        if(neg.getGnUsuariosGestionaId() != null){
            usuarioGestiona = new Usuario(neg.getGnUsuariosGestionaId().getId(), 
                                       neg.getGnUsuariosGestionaId().getUsuario(),
                                       neg.getGnUsuariosGestionaId().getNombre());
            usuarioGestiona.setCorreoElectronico(neg.getGnUsuariosGestionaId().getCorreoElectronico());
        }
            
        if (!ent.isMultiUsuario()) {
            for (CmDetalles cmDetalles : neg.getCmDetallesList()) {
                ent.setDocumentoMonoUsuario(cmDetalles.getDocumento());
                ent.setNombreMonoUsuario(cmDetalles.getNombreCompletoAfiliado());
                break;
            }
        }
        
        if (!neg.getCmFeNotasList().isEmpty()) {
            CmFeNotas nota = neg.getCmFeNotasList().get(0);
            ent.setCmFeNota(new CmFeNota( nota.getId(),nota.getTipo(),nota.getNumeroNota(),
                    nota.getValorNota(),nota.getCude(),nota.getFechaHoraEmision(),
                    nota.getUsuarioCrea(), nota.getTerminalCrea(), nota.getFechaHoraCrea()));
        }
        
        ent.setPbs(Optional.ofNullable(neg.getPbs()).orElse(false));
        ent.setUsuarioGestiona(usuarioGestiona);
        ent.setMarcacion(neg.getMarcacion());
        ent.setFechaMarcacion(neg.getFechaMarcacion());
        ent.setFechaVencimiento(neg.getFechaVencimiento());
        ent.setTipoAuditoria(neg.getTipoAuditoria());
        ent.setOrigenFactura(neg.getOrigenFactura());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setUsuarioModifica(neg.getUsuarioModifica());
        ent.setTerminalModifica(neg.getTerminalModifica());
        ent.setFechaHoraModifica(neg.getFechaHoraModifica());
        return ent;
    }

    public static CmFacturas castNegocioEntidad(CmFactura ent) {
        CmFacturas neg = new CmFacturas();
        neg.setId(ent.getId());
        CmRipsCargas ripCarga = new CmRipsCargas();
        if(ent.getCmRipCarga() != null && ent.getCmRipCarga().getId() != null){
           ripCarga = new CmRipsCargas(ent.getCmRipCarga().getId());
        }
        neg.setCmRipsCargasId(ripCarga);
        if(ent.getCmGrupo() != null && ent.getCmGrupo().getId() != null){
          neg.setCmGruposId(new CmGrupos(ent.getCmGrupo().getId()));
        }
        if (ent.getEmpresa() != null) {
            neg.setGnEmpresasId(new GnEmpresas(ent.getEmpresa().getId()));
        }
        if (ent.getCntPrestador() != null) {
            neg.setCntPrestadoresId(new CntPrestadores(ent.getCntPrestador().getId()));
        }
        neg.setNumeroRadicado(ent.getNumeroRadicado());
        neg.setNit(ent.getNit());
        neg.setIps(ent.getIps());
        neg.setNumeroFacturado(ent.getNumeroFacturado());
        neg.setFechaRadicacion(ent.getFechaRadicacion());
        neg.setFechaPrestacion(ent.getFechaPrestacion());
        neg.setMaeTipoContratoId(ent.getMaeTipoContratoId());
        neg.setMaeTipoContratoCodigo(ent.getMaeTipoContratoCodigo());
        neg.setMaeTipoContratoValor(ent.getMaeTipoContratoValor());
        neg.setMaeRegimenId(ent.getMaeRegimenId());
        neg.setMaeRegimenCodigo(ent.getMaeRegimenCodigo());
        neg.setMaeRegimenValor(ent.getMaeRegimenValor());
        neg.setValorFactura(ent.getValorFactura());
        neg.setValorPagadoFactura(ent.getValorPagadoFactura());
        neg.setEstadoFactura(ent.getEstadoFactura());
        neg.setMultiusuario(ent.isMultiUsuario());
        neg.setValorInicialGlosa(ent.getValorInicialGlosa());
        neg.setValorPendienteActual(ent.getValorPendienteActual());
        
        if(ent.getUsuarioLider() != null && ent.getUsuarioLider().getId() != null){
           neg.setGnUsuariosLiderId(new GnUsuarios(ent.getUsuarioLider().getId()));
        }
        
        if(ent.getUsuarioTecnico()!= null && ent.getUsuarioTecnico().getId() != null){
           neg.setGnUsuariosTecnicoId(new GnUsuarios(ent.getUsuarioTecnico().getId()));
        }
        
        if(ent.getUsuarioMedico() != null && ent.getUsuarioMedico().getId() != null){
           neg.setGnUsuariosMedicoId(new GnUsuarios(ent.getUsuarioMedico().getId()));
        }
        
        if(ent.getUsuarioGestiona() != null && ent.getUsuarioGestiona().getId() != null){
           neg.setGnUsuariosGestionaId(new GnUsuarios(ent.getUsuarioGestiona().getId()));
        }
        
        neg.setMarcacion(ent.getMarcacion());
        neg.setFechaMarcacion(ent.getFechaMarcacion());
        neg.setFechaVencimiento(ent.getFechaVencimiento());
        neg.setTipoAuditoria(ent.getTipoAuditoria());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        neg.setUsuarioModifica(ent.getUsuarioModifica());
        neg.setTerminalModifica(ent.getTerminalModifica());
        neg.setFechaHoraModifica(ent.getFechaHoraModifica());
        return neg;
    }
  
    @Override
    public void actualizarBloqueoFactura(ParamConsulta paramConsulta) throws java.lang.Exception {
        try {
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta4() != null) {
                String hql = "UPDATE CmFacturas f SET "
                        + "f.fechaMarcacion =:fechaMarcacion , "
                        + "f.fechaVencimiento =:fechaVencimiento , "
                        + "f.marcacion =:marcacion "
                        + "WHERE f.id IN (" + paramConsulta.getParametroConsulta1() + ") ";
                Query query = getEntityManager().createQuery(hql);
                query.setParameter("fechaMarcacion", paramConsulta.getParametroConsulta2());
                query.setParameter("fechaVencimiento", paramConsulta.getParametroConsulta3());
                query.setParameter("marcacion", paramConsulta.getParametroConsulta4());
                query.executeUpdate();
            }
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarTipoAuditoria(ParamConsulta paramConsulta) throws java.lang.Exception {
        try {
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                String hql = "UPDATE CmFacturas f SET "
                        + "f.tipoAuditoria =:tipoAuditoria "
                        + "WHERE f.id IN (" + paramConsulta.getParametroConsulta1() + ") ";
                Query query = getEntityManager().createQuery(hql);
                query.setParameter("tipoAuditoria", paramConsulta.getParametroConsulta2());
                query.executeUpdate();
            }
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarValoresAuditoria(ParamConsulta paramConsulta) throws java.lang.Exception {
        boolean editarEps = false;
        boolean editarIps = false;
        boolean editarObservacion = false;
        if (paramConsulta.getParametroConsulta1() != null && paramConsulta.getParametroConsulta2() != null) {
            editarEps = true;
        }
        if (paramConsulta.getParametroConsulta3() != null && paramConsulta.getParametroConsulta4() != null) {
            editarIps = true;
        }
        if (paramConsulta.getParametroConsulta5() != null) {
            editarObservacion = true;
        }
        if ((editarEps || editarIps || editarObservacion) && paramConsulta.getParametroConsulta6() != null) {
            String hql = "UPDATE CmDetalles cmd SET ";
            if (editarEps) {
                hql += "cmd.valorPagadoEps =:valorPagadoEps, ";
                hql += "cmd.porcentajePagadoEps =:porcentajePagadoEps ";
            }
            if (editarIps) {
                hql += editarEps ? ", " : " ";
                hql += "cmd.valorAceptadoIps =:valorAceptadoIps, ";
                hql += "cmd.porcentajeAceptadoIps =:porcentajeAceptadoIps ";
            }
            if (editarObservacion) {
                hql += editarEps || editarIps ? " , " : "";
                hql += "cmd.observacionRespuestaDetalles =:observacionRespuestaDetalles ";
            }
            hql += "WHERE cmd.id = :id ";
            Query query = getEntityManager().createQuery(hql);
            if (editarEps) {
                query.setParameter("valorPagadoEps", paramConsulta.getParametroConsulta1());
                query.setParameter("porcentajePagadoEps", paramConsulta.getParametroConsulta2());
            }
            if (editarIps) {
                query.setParameter("valorAceptadoIps", paramConsulta.getParametroConsulta3());
                query.setParameter("porcentajeAceptadoIps", paramConsulta.getParametroConsulta4());
            }
            if (editarObservacion) {
                query.setParameter("observacionRespuestaDetalles", paramConsulta.getParametroConsulta5());
            }
            query.setParameter("id", paramConsulta.getParametroConsulta6());
            query.executeUpdate();
        }
    }

    @Override
    public void borrarValoresAuditoria(ParamConsulta paramConsulta) throws java.lang.Exception {
        if (paramConsulta.getParametroConsulta1() != null) {
            String hql = "UPDATE CmDetalles cmd SET ";
            hql += "cmd.valorPagadoEps = NULL, ";
            hql += "cmd.porcentajePagadoEps = NULL, ";
            hql += "cmd.valorAceptadoIps = 0, ";
            hql += "cmd.porcentajeAceptadoIps = NULL, ";
            hql += "cmd.observacionRespuestaDetalles = NULL ";
            hql += "WHERE cmd.id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("id", paramConsulta.getParametroConsulta1());
            query.executeUpdate();
        }
    }

}
