/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmMarcacionMasivaIps;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmFacturas;
import com.saviasaludeps.savia.ejb.entidades.CmMarcacionIpsMasiva;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmMarcacionMasivaIpsRemoto;
import java.util.ArrayList;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Stateless
@Remote(CmMarcacionMasivaIpsRemoto.class)
@Local(CmMarcacionMasivaIpsLocal.class)
public class CmMarcacionMasivaIpsServicio extends GenericoServicio implements CmMarcacionMasivaIpsLocal, CmMarcacionMasivaIpsRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM CmMarcacionIpsMasiva p ";
            
            if (paramConsulta.getEmpresaId() == null) {
                strQuery += "WHERE p.id > 0 ";
            } else {
                strQuery += "WHERE p.gnEmpresasId.id = :empresa_id ";
            }
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "archivo":
                            strQuery += "AND p.archivo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND p.fechaHoraInicio = '" + e.getValue().toString() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea like '%" + (String) e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += "AND p.tipo in (" + e.getValue() + ") ";
                            break;
                        case "detalle":
                            strQuery += "AND p.detalle = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }           
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getEmpresaId() != null) {
                query.setParameter("empresa_id", paramConsulta.getEmpresaId());
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
    public List<CmMarcacionMasivaIps> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
         List<CmMarcacionMasivaIps> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmMarcacionIpsMasiva p ";
            if (paramConsulta.getEmpresaId() == null) {
                strQuery += "WHERE p.id > 0 ";
            } else {
                strQuery += "WHERE p.gnEmpresasId.id = :empresa_id ";
            }
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "archivo":
                            strQuery += "AND p.archivo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND p.fechaHoraInicio = '" + e.getValue().toString() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea like '%" + (String) e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += "AND p.tipo in (" + e.getValue() + ") ";
                            break;
                        case "detalle":
                            strQuery += "AND p.detalle = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    case "programa":
                        strQuery += "p.peProgramasId.id "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    default:
                        strQuery += "p." + paramConsulta.getOrden() + " "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                }
            } else {
                strQuery += "p.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getEmpresaId() != null) {
                query.setParameter("empresa_id", paramConsulta.getEmpresaId());
            }
            query.setFirstResult(paramConsulta.getPrimerRegistro());
            query.setMaxResults(paramConsulta.getRegistrosPagina());
            List<CmMarcacionIpsMasiva> list = query.getResultList();
            for (CmMarcacionIpsMasiva per : list) {
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
    public List<CmFactura> consultarParaMarcacionIpsMasiva(String numerosFacturados, String numerosRadicados) throws java.lang.Exception {
        List<CmFactura> listaFacturas = new ArrayList();
        try {

            String strQuery = "FROM CmFacturas cmf WHERE cmf.id > 0 AND "
                    + " cmf.numeroFacturado IN (" + numerosFacturados + ") AND "
                    + " cmf.numeroRadicado IN (" + numerosRadicados + ")  ";
            Query query = getEntityManager().createQuery(strQuery);
            List<CmFacturas> list = query.getResultList();
            for (CmFacturas neg : list) {
                listaFacturas.add(castEntidadNegocioCorto(neg));
            }
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaFacturas;
    }
    
    public static CmFactura castEntidadNegocioCorto(CmFacturas neg) {
        CmFactura ent = new CmFactura();
        ent.setId(neg.getId());
        ent.setNit(neg.getNit());
        ent.setNumeroRadicado(neg.getNumeroRadicado());
        ent.setNumeroFacturado(neg.getNumeroFacturado());
        ent.setEstadoFactura(neg.getEstadoFactura());
        return ent;
    }

    @Override
    public CmMarcacionMasivaIps consultar(int id) throws java.lang.Exception {
        CmMarcacionMasivaIps objResult = new CmMarcacionMasivaIps();
        try {
            objResult = castEntidadNegocio((CmMarcacionIpsMasiva) getEntityManager().find(CmMarcacionIpsMasiva.class, id));
        } catch (NoResultException e) {
            objResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return objResult;
    }

    @Override
    public int insertar(CmMarcacionMasivaIps obj) throws java.lang.Exception {
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
    public void actualizar(CmMarcacionMasivaIps obj) throws java.lang.Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void actualizarFinProceso(CmMarcacionMasivaIps obj) throws java.lang.Exception {
         String sql = "UPDATE CmMarcacionIpsMasiva "
                    + "SET estado = :estado ,"
                    + " exitosos = :exitosos ,"
                    + " fallidos = :fallidos ,"
                    + " fechaHoraFin = :fechaHoraFin "
                    + "WHERE id = :id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("exitosos", obj.getExitosos());
            query.setParameter("fallidos", obj.getFallidos());
            query.setParameter("fechaHoraFin", obj.getFechaHoraFin());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
    }

    @Override
    public CmMarcacionMasivaIps eliminar(int id) throws java.lang.Exception {
        CmMarcacionMasivaIps obj = null;
        try {
            CmMarcacionIpsMasiva per = getEntityManager().find(CmMarcacionIpsMasiva.class, id);
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
    
    
    public static CmMarcacionMasivaIps castEntidadNegocio(CmMarcacionIpsMasiva ent) {
        CmMarcacionMasivaIps neg = new CmMarcacionMasivaIps();
        neg.setId(ent.getId());
        neg.setNombre(ent.getNombre());
        neg.setArchivo(ent.getArchivo());
        neg.setRuta(ent.getRuta());
        neg.setRegistros(ent.getRegistros());
        neg.setEstado(ent.getEstado());
        neg.setExitosos(ent.getExitosos());
        neg.setFallidos(ent.getFallidos());
        neg.setFechaHoraInicio(ent.getFechaHoraInicio());
        neg.setFechaHoraFin(ent.getFechaHoraFin());
        neg.setEmpresa(new Empresa(ent.getGnEmpresasId().getId()));
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        return neg;
    }

    public static CmMarcacionIpsMasiva castNegocioEntidad(CmMarcacionMasivaIps neg) {
        CmMarcacionIpsMasiva ent = new CmMarcacionIpsMasiva();
        ent.setId(neg.getId());
        ent.setNombre(neg.getNombre());
        ent.setArchivo(neg.getArchivo());
        ent.setRuta(neg.getRuta());
        ent.setRegistros(neg.getRegistros());
        ent.setEstado(neg.getEstado());
        ent.setExitosos(neg.getExitosos());
        ent.setFallidos(neg.getFallidos());
        ent.setFechaHoraInicio(neg.getFechaHoraInicio());
        ent.setFechaHoraFin(neg.getFechaHoraFin());
        ent.setGnEmpresasId(new GnEmpresas(neg.getEmpresa().getId()));
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        return ent;
    }

}
