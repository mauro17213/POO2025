/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria;


import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDevolucion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmDevolucionMasivaN;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmAuditoriaDevoluciones;
import com.saviasaludeps.savia.ejb.entidades.CmDevolucionMasiva;
import com.saviasaludeps.savia.ejb.entidades.CmFacturas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaDevolucionRemoto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;

@Stateless
@Remote(CmAuditoriaDevolucionRemoto.class)
@Local(CmAuditoriaDevolucionLocal.class)
public class CmAuditoriaDevolucionServicio extends GenericoServicio implements CmAuditoriaDevolucionLocal, CmAuditoriaDevolucionRemoto {
   
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(d) FROM CmAuditoriaDevoluciones d "
                    + "WHERE d.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nit":
                            strQuery += "AND d.nit = '" + e.getValue() + "' ";
                            break;
                        case "ips":
                            strQuery += "AND d.ips LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "numeroFacturado":
                            strQuery += "AND d.numeroFacturado = '" + e.getValue() + "' ";
                            break;
                        case "numeroRadicado":
                            strQuery += "AND d.numeroRadicado = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            //Rango Fechas de Devolución
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND d.fechaDevolucion BETWEEN :fh_inicio AND :fh_fin ";
            } else if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND d.fechaDevolucion >= :fh_inicio ";
            } else if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND d.fechaDevolucion <= :fh_fin ";
            }
            //Solo devoluciones del codigo de habilitación del obj conexión
            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += "AND d.cmFacturasId.cntPrestadoresId.codigoMinSalud = :codigo_habilitacion ";
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fh_inicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
                query.setParameter("fh_fin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("fh_inicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fh_fin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            }
            if (paramConsulta.getParametroConsulta3() != null) {
                query.setParameter("codigo_habilitacion", (paramConsulta.getParametroConsulta3()));
            }
            cant = (int) (long) query
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
    public List<CmAuditoriaDevolucion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmAuditoriaDevolucion> listResult = new ArrayList();
        try {
            String strQuery = "SELECT d FROM CmAuditoriaDevoluciones d "
                    + "WHERE d.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nit":
                            strQuery += "AND d.nit = '" + e.getValue() + "' ";
                            break;
                        case "ips":
                            strQuery += "AND d.ips LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "numeroFacturado":
                            strQuery += "AND d.numeroFacturado = '" + e.getValue() + "' ";
                            break;
                        case "numeroRadicado":
                            strQuery += "AND d.numeroRadicado = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            //Rango Fechas de Devolución
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND d.fechaDevolucion BETWEEN :fh_inicio AND :fh_fin ";
            } else if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND d.fechaDevolucion >= :fh_inicio ";
            } else if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND d.fechaDevolucion <= :fh_fin ";
            }
            //Solo devoluciones del codigo de habilitación del obj conexión
            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += "AND d.cmFacturasId.cntPrestadoresId.codigoMinSalud = :codigo_habilitacion ";
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "d." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "d.fechaDevolucion DESC ";
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fh_inicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
                query.setParameter("fh_fin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("fh_inicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fh_fin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            }
            if (paramConsulta.getParametroConsulta3() != null) {
                query.setParameter("codigo_habilitacion", (paramConsulta.getParametroConsulta3()));
            }
            List<CmAuditoriaDevoluciones> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();

            for (CmAuditoriaDevoluciones per : list) {
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
    public CmAuditoriaDevolucion consultar(int id) throws Exception {
        CmAuditoriaDevolucion obj = null;
        try {
            CmAuditoriaDevoluciones per = (CmAuditoriaDevoluciones) getEntityManager().find(CmAuditoriaDevoluciones.class, id);
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
    public int insertar(CmAuditoriaDevolucion obj) throws Exception {
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
    public void actualizar(CmAuditoriaDevolucion obj) throws Exception {
        try {
            CmAuditoriaDevoluciones dignostico = castNegocioEntidad(obj);
            String hql = "UPDATE CmAuditoriaDevoluciones SET"
                    + " maDiagniosticosId = :maDiagniosticosId,"
                    + " maDiagnosticosCodigo = :maDiagnosticosCodigo,"
                    + " maDiagnosticosValor = :maDiagnosticosValor,"
                    + " usuarioModifica  = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica,"
                    + " cmDetallesId.id = :cmDetallesId"
                    + " WHERE id = :id";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("usuarioModifica", dignostico.getUsuarioModifica());
            query.setParameter("terminalModifica", dignostico.getTerminalModifica());
            query.setParameter("id", dignostico.getId());
            query.executeUpdate();

        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public List<CmAuditoriaDevolucion> consultarPorCmAuditoriaDevolucionMasivaId(int cmAuditoriaDevolucionMasivaId) throws Exception {
        List<CmAuditoriaDevolucion> listaResultado = new ArrayList<>();

        try {
            String strQuery = " FROM CmAuditoriaDevoluciones cmd WHERE cmd.id > 0 AND cmd.cmDevolucionMasivaId.id = :id ";
            List<CmAuditoriaDevoluciones> lista = getEntityManager().createQuery(strQuery).setParameter("id", cmAuditoriaDevolucionMasivaId).getResultList();

            for (CmAuditoriaDevoluciones devolucion : lista) {
               listaResultado.add(castEntidadNegocio(devolucion));
            }

        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return listaResultado;
    }
    
    @Override
    public   List<CmAuditoriaDevolucion>  consultarPorFacturaId( String idsFactura) throws Exception {
         List<CmAuditoriaDevolucion>  devoluciones = new ArrayList<>();
        try {
            String strQuery = " FROM CmAuditoriaDevoluciones cmd WHERE cmd.cmFacturasId.id IN ("+idsFactura+") ORDER BY cmd.id DESC ";
            List<CmAuditoriaDevoluciones> lista = getEntityManager().createQuery(strQuery).getResultList();
            for (CmAuditoriaDevoluciones devolucionEnt : lista) {
                devoluciones.add(castEntidadNegocio(devolucionEnt));
            }
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return devoluciones;
    }
    
    @Override
    public List<CmAuditoriaDevolucion> consultarPorCmFacturaIdTipoDevolucion(String idsFactura, int tipoDevolucion, int idBusqueda) throws Exception {
        List<CmAuditoriaDevolucion> devoluciones = new ArrayList<>();
        try {
            String strQuery = " FROM CmAuditoriaDevoluciones cmd WHERE cmd.cmFacturasId.id IN (" + idsFactura + ") ";

            if (CmAuditoriaDevolucion.TIPO_DEVOLUCION_INDIVIDUAL == tipoDevolucion) {
                strQuery += " AND cmd.id = " + idBusqueda;
            }

            if (CmAuditoriaDevolucion.TIPO_DEVOLUCION_MASIVA == tipoDevolucion) {
                strQuery += " AND cmd.cmDevolucionMasivaId.id = " + idBusqueda;
            }

            strQuery += " ORDER BY cmd.id DESC ";

            List<CmAuditoriaDevoluciones> lista = getEntityManager().createQuery(strQuery).getResultList();
            for (CmAuditoriaDevoluciones devolucionEnt : lista) {
                devoluciones.add(castEntidadNegocio(devolucionEnt));
            }
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return devoluciones;
    }

    @Override
    public CmAuditoriaDevolucion eliminar(int id) throws Exception {
        CmAuditoriaDevolucion obj = null;
        try {
            CmAuditoriaDevoluciones per = getEntityManager().find(CmAuditoriaDevoluciones.class, id);
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

    public static CmAuditoriaDevolucion castEntidadNegocio(CmAuditoriaDevoluciones neg) {
        CmAuditoriaDevolucion ent = new CmAuditoriaDevolucion();
        ent.setId(neg.getId());
        CmFactura factura = new CmFactura(neg.getCmFacturasId().getId(),
                neg.getCmFacturasId().getMaeTipoContratoValor(),
                neg.getCmFacturasId().getNumeroRadicado(),
                neg.getCmFacturasId().getNumeroFacturado(),
                neg.getCmFacturasId().getNit(),
                neg.getCmFacturasId().getIps(),
                neg.getCmFacturasId().getFechaRadicacion(),
                neg.getCmFacturasId().getFechaPrestacion(),
                neg.getCmFacturasId().getMaeRegimenValor(),
                neg.getCmFacturasId().getValorFactura(),
                neg.getCmFacturasId().getEstadoFactura(),
                neg.getCmFacturasId().getValorCopago()
        );
        factura.setNumeroContrato(neg.getCmFacturasId().getNumeroContrato());
        ent.setCmFactura(factura);
        if(neg.getCmDevolucionMasivaId() != null && neg.getCmDevolucionMasivaId().getId() != null){
            ent.setCmDevolucionMasiva(new CmDevolucionMasivaN(neg.getCmDevolucionMasivaId().getId()));
        }
        ent.setMaeRegimenId(neg.getMaeRegimenId());
        ent.setMaeRegimenCodigo(neg.getMaeRegimenCodigo());
        ent.setMaeRegimenValor(neg.getMaeRegimenValor());
        ent.setMaeContratoModalidadId(neg.getMaeContratoModalidadId());
        ent.setMaeContratoModalidadCodigo(neg.getMaeContratoModalidadCodigo());
        ent.setMaeContratoModalidadValor(neg.getMaeContratoModalidadValor());
        ent.setMaeMotivoDevolucionId(neg.getMaeMotivoDevolucionId());
        ent.setMaeMotivoDevolucionCodigo(neg.getMaeMotivoDevolucionCodigo());
        ent.setMaeMotivoDevolucionValor(neg.getMaeMotivoDevolucionValor());
        ent.setNit(neg.getNit());
        ent.setIps(neg.getIps());
        ent.setObservacion(neg.getObservacion());
        ent.setNumeroFacturado(neg.getNumeroFacturado());
        ent.setNumeroRadicado(neg.getNumeroRadicado());
        ent.setFechaDevolucion(neg.getFechaDevolucion());
        ent.setFechaRadicacion(neg.getFechaRadicacion());
        ent.setValorFactura(neg.getValorFactura());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setUsuarioModifica(neg.getUsuarioModifica());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setTerminalModifica(neg.getTerminalModifica());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setFechaHoraModifica(neg.getFechaHoraModifica());
        return ent;
    }

    public static CmAuditoriaDevoluciones castNegocioEntidad(CmAuditoriaDevolucion ent) {
        CmAuditoriaDevoluciones neg = new CmAuditoriaDevoluciones();
        neg.setId(ent.getId());
        neg.setCmFacturasId(new CmFacturas(ent.getCmFactura().getId()));
        if( ent.getCmDevolucionMasiva() != null && ent.getCmDevolucionMasiva().getId() !=  null){
          neg.setCmDevolucionMasivaId(new CmDevolucionMasiva(ent.getCmDevolucionMasiva().getId()));
        }
        neg.setMaeRegimenId(ent.getMaeRegimenId());
        neg.setMaeRegimenCodigo(ent.getMaeRegimenCodigo());
        neg.setMaeRegimenValor(ent.getMaeRegimenValor());
        neg.setMaeContratoModalidadId(ent.getMaeContratoModalidadId());
        neg.setMaeContratoModalidadCodigo(ent.getMaeContratoModalidadCodigo());
        neg.setMaeContratoModalidadValor(ent.getMaeContratoModalidadValor());
        neg.setMaeMotivoDevolucionId(ent.getMaeMotivoDevolucionId());
        neg.setMaeMotivoDevolucionCodigo(ent.getMaeMotivoDevolucionCodigo());
        neg.setMaeMotivoDevolucionValor(ent.getMaeMotivoDevolucionValor());
        neg.setMaeDevolucionMotivoGeneralId(ent.getMaeDevolucionMotivoGeneralId());
        neg.setMaeDevolucionMotivoGeneralCodigo(ent.getMaeDevolucionMotivoGeneralCodigo());
        neg.setMaeDevolucionMotivoGeneralValor(ent.getMaeDevolucionMotivoGeneralValor());
        neg.setMaeDevolucionMotivoGeneralDescripcion(ent.getMaeDevolucionMotivoGeneralDescripcion());
        neg.setNit(ent.getNit());
        neg.setIps(ent.getIps());
        neg.setObservacion(ent.getObservacion());
        neg.setNumeroFacturado(ent.getNumeroFacturado());
        neg.setNumeroRadicado(ent.getNumeroRadicado());
        neg.setFechaRadicacion(ent.getFechaRadicacion());
        neg.setFechaDevolucion(ent.getFechaDevolucion());
        neg.setValorFactura(ent.getValorFactura());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setUsuarioModifica(ent.getUsuarioModifica());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setTerminalModifica(ent.getTerminalModifica());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        neg.setFechaHoraModifica(ent.getFechaHoraModifica());
        return neg;
    }
    
     public static CmAuditoriaDevolucion castEntidadNegocioCorto(CmAuditoriaDevoluciones neg) {
        CmAuditoriaDevolucion ent = new CmAuditoriaDevolucion();
        ent.setId(neg.getId());
        CmFactura factura = new CmFactura();
        ent.setCmFactura(factura);
        ent.setMaeRegimenId(neg.getMaeRegimenId());
        ent.setMaeRegimenCodigo(neg.getMaeRegimenCodigo());
        ent.setMaeRegimenValor(neg.getMaeRegimenValor());
        ent.setMaeContratoModalidadId(neg.getMaeContratoModalidadId());
        ent.setMaeContratoModalidadCodigo(neg.getMaeContratoModalidadCodigo());
        ent.setMaeContratoModalidadValor(neg.getMaeContratoModalidadValor());
        ent.setMaeMotivoDevolucionId(neg.getMaeMotivoDevolucionId());
        ent.setMaeMotivoDevolucionCodigo(neg.getMaeMotivoDevolucionCodigo());
        ent.setMaeMotivoDevolucionValor(neg.getMaeMotivoDevolucionValor());
        ent.setMaeDevolucionMotivoGeneralId(neg.getMaeDevolucionMotivoGeneralId());
        ent.setMaeDevolucionMotivoGeneralCodigo(neg.getMaeDevolucionMotivoGeneralCodigo());
        ent.setMaeDevolucionMotivoGeneralValor(neg.getMaeDevolucionMotivoGeneralValor());
        ent.setMaeDevolucionMotivoGeneralDescripcion(neg.getMaeDevolucionMotivoGeneralDescripcion());
        ent.setNit(neg.getNit());
        ent.setIps(neg.getIps());
        ent.setObservacion(neg.getObservacion());
        ent.setNumeroFacturado(neg.getNumeroFacturado());
        ent.setNumeroRadicado(neg.getNumeroRadicado());
        ent.setFechaDevolucion(neg.getFechaDevolucion());
        ent.setFechaRadicacion(neg.getFechaRadicacion());
        ent.setValorFactura(neg.getValorFactura());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setUsuarioModifica(neg.getUsuarioModifica());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setTerminalModifica(neg.getTerminalModifica());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setFechaHoraModifica(neg.getFechaHoraModifica());
        return ent;
    }
}
