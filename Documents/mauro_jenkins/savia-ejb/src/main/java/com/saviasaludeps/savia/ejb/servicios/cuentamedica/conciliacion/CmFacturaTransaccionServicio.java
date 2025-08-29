/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFacturaTransaccion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmFacturaTransacciones;
import com.saviasaludeps.savia.ejb.entidades.CmFacturas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaTransaccionRemoto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jeperez
 */
@Stateless
@Remote(CmFacturaTransaccionRemoto.class)
@Local(CmFacturaTransaccionLocal.class)
public class CmFacturaTransaccionServicio extends GenericoServicio implements CmFacturaTransaccionLocal, CmFacturaTransaccionRemoto {

    
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(cmft) FROM CmFacturaTransacciones cmft ";
            strQuery += " WHERE cmft.id > 0 ";
            
            
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND cmft.cmFacturasId.id = :cmFacturasId ";
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery += " AND cmft.tipo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += " AND cmft.estado LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }

            Query query = getEntityManager().createQuery(strQuery).setParameter("cmFacturasId", paramConsulta.getParametroConsulta1());

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
    public List<CmFacturaTransaccion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmFacturaTransaccion> listResult = new ArrayList();
        try {
            String strQuery = "FROM CmFacturaTransacciones cmft ";
            strQuery += " WHERE 1= 1  ";
            
             if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND cmft.cmFacturasId.id = :cmFacturasId ";
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery += " AND cmft.tipo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += " AND cmft.estado LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }

            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                String order = paramConsulta.getOrden().replace("gsAfiliado", "gsAfiliadosId").
                        replace("gsZona", "gsZonasId");
                strQuery += " cmft." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " cmft.fechaHoraInicio DESC,  cmft.id DESC ";
            }
            
            Query query = getEntityManager().createQuery(strQuery).setParameter("cmFacturasId", paramConsulta.getParametroConsulta1());

            List<CmFacturaTransacciones> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CmFacturaTransacciones neg : list) {
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
    public CmFacturaTransaccion consultar(int id) throws Exception {
        CmFacturaTransaccion obj = null;
        try {
            CmFacturaTransacciones per = (CmFacturaTransacciones) getEntityManager().find(CmFacturaTransacciones.class, id);
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
    public int insertar(CmFacturaTransaccion obj) throws Exception {
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
     public CmFacturaTransaccion insertarTransaccionEnvioFactura(int idFactura, int tipo, int estado) {
        CmFacturaTransaccion transaccion = new CmFacturaTransaccion();
        try {
            transaccion.setCmFactura(new CmFactura(idFactura));
            transaccion.setFechaHoraInicio(new Date());
            transaccion.setEstado(estado);
            transaccion.setTipo(tipo);
            transaccion.setId(insertar(transaccion));
        } catch (Exception e) {
        }
        return transaccion;
    }

    @Override
    public void actualizar(CmFacturaTransaccion obj) throws Exception {
        try {
            String hql = "UPDATE CmFacturaTransacciones SET"
                    + " estado = :estado,"
                    + " fechaHoraFin = :fechaHoraFin,"
                    + " respuestaCodigo = :respuestaCodigo,"
                    + " respuestaDescripcion= :respuestaDescripcion"
                    + " WHERE cmFacturasId.id = :idFactura AND estado = :estadoAnterior AND tipo = :tipo";

            Query query = getEntityManager().createQuery(hql);
           
            query.setParameter("estado", obj.getEstado());
            query.setParameter("fechaHoraFin", obj.getFechaHoraFin());
            query.setParameter("respuestaCodigo", obj.getRespuestaCodigo());
            query.setParameter("respuestaDescripcion", obj.getRespuestaDescripcion());
            query.setParameter("idFactura", obj.getCmFactura().getId());
            query.setParameter("estadoAnterior", obj.getEstadoAnterior());
            query.setParameter("tipo", obj.getTipo()); 
             
            query.executeUpdate();

        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CmFacturaTransaccion eliminar(int id) throws Exception {
        CmFacturaTransaccion obj = null;
        try {
            CmFacturaTransacciones per = getEntityManager().find(CmFacturaTransacciones.class, id);
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

     
    
    public static CmFacturaTransacciones castNegocioEntidad(CmFacturaTransaccion neg) {
        CmFacturaTransacciones ent = new CmFacturaTransacciones();
        ent.setId(neg.getId());
        ent.setTipo(neg.getTipo());
        ent.setEstado(neg.getEstado());
        ent.setFechaHoraInicio(neg.getFechaHoraInicio());
        ent.setFechaHoraFin(neg.getFechaHoraFin());
        ent.setCmFacturasId(new CmFacturas(neg.getCmFactura().getId()));
        ent.setRespuestaCodigo(neg.getRespuestaCodigo());
        ent.setRespuestaDescripcion(neg.getRespuestaDescripcion());
        return ent;
    }

    public static CmFacturaTransaccion castEntidadNegocio(CmFacturaTransacciones ent) {
        CmFacturas cmfacturas = ent.getCmFacturasId();
        CmFacturaTransaccion neg = new CmFacturaTransaccion();
        CmFactura factura = new CmFactura(cmfacturas.getId());
        factura.setNumeroFacturado(cmfacturas.getNumeroFacturado());
        neg.setCmFactura(factura);
        neg.setId(ent.getId());
        neg.setTipo(ent.getTipo());
        neg.setEstado(ent.getEstado());
        neg.setFechaHoraInicio(ent.getFechaHoraInicio());
        neg.setFechaHoraFin(ent.getFechaHoraFin());    
        neg.setRespuestaCodigo(ent.getRespuestaCodigo());
        neg.setRespuestaDescripcion(ent.getRespuestaDescripcion());
        return neg;
    }

}
