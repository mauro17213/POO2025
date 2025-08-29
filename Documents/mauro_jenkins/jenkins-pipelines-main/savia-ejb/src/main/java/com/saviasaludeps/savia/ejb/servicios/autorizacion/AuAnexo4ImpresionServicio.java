/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Impresion;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4Impresiones;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos4;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4ImpresionRemoto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author Stiven Giraldo
 */
@Stateless
@Remote(AuAnexo4ImpresionRemoto.class)
public class AuAnexo4ImpresionServicio extends GenericoServicio implements AuAnexo4ImpresionRemoto {

    private static final SimpleDateFormat formatoCorto = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat formatoLargo = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    
    @Override
    public List<AuAnexo4Impresion> consultarPorIdAnexo4(int idAnexo4) throws Exception {
        List<AuAnexo4Impresion> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo4Entregas p "
                    + "WHERE p.auAnexos4Id = "+idAnexo4+" ORDER BY p.id DESC";
            List<AuAnexo4Impresiones> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuAnexo4Impresiones anexo4Impresion : list){
                listaResultados.add(castEntidadNegocio(anexo4Impresion));
            }
        } catch (NoResultException e){
            listaResultados = new ArrayList();
        }catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally{
            cerrarEntityManager();
        }
        return listaResultados;
    }

    @Override
    public int insertar(AuAnexo4Impresion obj) throws Exception {
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

    private AuAnexo4Impresion castEntidadNegocio(AuAnexo4Impresiones entidad) {
        AuAnexo4Impresion negocio = new AuAnexo4Impresion(entidad.getId());
        negocio.setAuAnexo4Id(new AuAnexo4(entidad.getAuAnexos4Id().getId()));
        negocio.getAuAnexo4Id().setAfiliadoTipoDocumento(entidad.getAuAnexos4Id().getAfiliadoTipoDocumento());
        negocio.getAuAnexo4Id().setAfiliadoNumeroDocumento(entidad.getAuAnexos4Id().getAfiliadoNumeroDocumento());
        negocio.getAuAnexo4Id().setAsegAfiliadoId(new AsegAfiliado(entidad.getAuAnexos4Id().getAsegAfiliadosId().getId()));
        negocio.getAuAnexo4Id().getAsegAfiliadoId().setMaeTipoDocumento(entidad.getAuAnexos4Id().getAsegAfiliadosId().getMaeTipoDocumentoId());
        if (entidad.getAuAnexos4Id().getAuAnexos2Id() != null) {
            negocio.getAuAnexo4Id().setAuAnexo2Id(new AuAnexo2(entidad.getAuAnexos4Id().getAuAnexos2Id().getId()));
        }
        if (entidad.getAuAnexos4Id().getAuAnexos3Id() != null) {
            negocio.getAuAnexo4Id().setAuAnexo3Id(new AuAnexo3(entidad.getAuAnexos4Id().getAuAnexos3Id().getId()));
        }
        negocio.getAuAnexo4Id().setImpresionesRealizadas(entidad.getAuAnexos4Id().getImpresionesRealizadas());
        negocio.getAuAnexo4Id().setImpresionesAutorizadas(entidad.getAuAnexos4Id().getImpresionesAutorizadas());
        negocio.setOrigenImpresion(entidad.getOrigenImpresion());
        negocio.setTipoImpresion(entidad.getTipoImpresion());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        return negocio;
    }
    
    private AuAnexo4Impresiones castNegocioEntidad(AuAnexo4Impresion negocio) {
        AuAnexo4Impresiones entidad = new AuAnexo4Impresiones();
        entidad.setAuAnexos4Id(new AuAnexos4(negocio.getAuAnexo4Id().getId()));
        entidad.setOrigenImpresion(negocio.getOrigenImpresion());
        entidad.setTipoImpresion(negocio.getTipoImpresion());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        return entidad;
    }

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(p) FROM AuAnexo4Impresiones p ";
            String strQuery = "WHERE p.id > 0 ";
                     
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "auAnexo4Id.afiliadoTipoDocumento":
                            strQuery += "AND p.auAnexos4Id.asegAfiliadosId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "auAnexo4Id.afiliadoNumeroDocumento":
                            strQuery += "AND p.auAnexos4Id.asegAfiliadosId.numeroDocumento = " + e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "auAnexo4Id.auAnexo3Id.id":
                            strQuery += "AND (p.auAnexos4Id.auAnexos3Id.id = '" + e.getValue() + "' OR p.auAnexos4Id.auAnexos2Id = '" + e.getValue() + "' )";
                            break;
                        case "terminalCrea":
                            strQuery += "AND p.terminalCrea = '" + e.getValue() + "' ";
                            break;
                        case "auAnexo4Id.id":
                            strQuery += "AND p.auAnexos4Id.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery = strTitulo + strQuery;
            if (paramConsulta.getParametroConsulta1() != null) {
                if (paramConsulta.getParametroConsulta2() != null) {
                    strQuery += "AND p.fechaHoraCrea BETWEEN '" + formatoCorto.format(paramConsulta.getParametroConsulta1()) + " 00:00:00' AND '" + formatoCorto.format(paramConsulta.getParametroConsulta2()) + " 23:59:59' ";
                } else {
                    strQuery += "AND p.fechaHoraCrea >= '" + formatoCorto.format(paramConsulta.getParametroConsulta1()) + " 00:00:00' ";
                }
            } else {
                if (paramConsulta.getParametroConsulta2() != null) {
                    strQuery += "AND p.fechaHoraCrea <= '" + formatoCorto.format(paramConsulta.getParametroConsulta2()) + " 23:59:59' ";
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
    public List<AuAnexo4Impresion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo4Impresion> listaResultados = new ArrayList();
        try {
            String strTitulo = "FROM AuAnexo4Impresiones p ";
            String strQuery = "WHERE p.id > 0 ";
                     
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "auAnexo4Id.afiliadoTipoDocumento":
                            strQuery += "AND p.auAnexos4Id.asegAfiliadosId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "auAnexo4Id.afiliadoNumeroDocumento":
                            strQuery += "AND p.auAnexos4Id.asegAfiliadosId.numeroDocumento = " + e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "auAnexo4Id.auAnexo3Id.id":
                            strQuery += "AND (p.auAnexos4Id.auAnexos3Id.id = '" + e.getValue() + "' OR p.auAnexos4Id.auAnexos2Id = '" + e.getValue() + "' )";
                            break;
                        case "terminalCrea":
                            strQuery += "AND p.terminalCrea = '" + e.getValue() + "' ";
                            break;
                        case "auAnexo4Id.id":
                            strQuery += "AND p.auAnexos4Id.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery = strTitulo + strQuery;
            if (paramConsulta.getParametroConsulta1() != null) {
                if (paramConsulta.getParametroConsulta2() != null) {
                    strQuery += "AND p.fechaHoraCrea BETWEEN '" + formatoCorto.format(paramConsulta.getParametroConsulta1()) + " 00:00:00' AND '" + formatoCorto.format(paramConsulta.getParametroConsulta2()) + " 23:59:59' ";
                } else {
                    strQuery += "AND p.fechaHoraCrea >= '" + formatoCorto.format(paramConsulta.getParametroConsulta1()) + " 00:00:00' ";
                }
            } else {
                if (paramConsulta.getParametroConsulta2() != null) {
                    strQuery += "AND p.fechaHoraCrea <= '" + formatoCorto.format(paramConsulta.getParametroConsulta2()) + " 23:59:59' ";
                }
            }
            
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<AuAnexo4Impresiones> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexo4Impresiones impresiones : list) {
                listaResultados.add(castEntidadNegocio(impresiones));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }

    @Override
    public AuAnexo4Impresion consultar(int id) throws Exception {
        AuAnexo4Impresion objRes = null;
        try {
            objRes = castEntidadNegocio((AuAnexo4Impresiones) getEntityManager().find(AuAnexo4Impresiones.class, id));
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
    public void actualizar(AuAnexo4Impresion obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
