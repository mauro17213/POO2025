/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria;


import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmFacturaEstado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.ejb.entidades.CmFacturaEstados;
import com.saviasaludeps.savia.ejb.entidades.CmFacturas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmFacturaEstadoRemoto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jperezn
 */
@Stateless
@Remote(CmFacturaEstadoRemoto.class)
@Local(CmFacturaEstadoLocal.class)
public class CmFacturaEstadoServicio extends GenericoServicio implements CmFacturaEstadoLocal, CmFacturaEstadoRemoto {
    
    @Override
    public CmFacturaEstado consultar(int id) throws Exception {
        CmFacturaEstado obj = null;
        try {
            CmFacturaEstados per = (CmFacturaEstados) getEntityManager().find(CmFacturaEstados.class, id);
            if(per != null){
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
    public int insertar(CmFacturaEstado obj) throws Exception {
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
    public void actualizar(CmFacturaEstado obj) throws Exception {
        try {
 
            CmFacturaEstados cmFactura =  castNegocioEntidad(obj);       
            String hql = "UPDATE CmFacturaEstados SET  "
                    + " estadoFactura = :estadoFactura "
                    + " WHERE id = :id";
            
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estadoFactura", cmFactura.getEstadoFactura());
            query.setParameter("id", cmFactura.getId());
            query.executeUpdate();
            
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CmFacturaEstado eliminar(int id) throws Exception {
        CmFacturaEstado obj = null;
        try {
            CmFacturaEstados ent = getEntityManager().find(CmFacturaEstados.class, id);
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
    public void insertarEstadoMasivo(List<CmFacturaEstado> listFacturaEstados) throws Exception {
        try {
 
            StringBuilder stringBuilder = new StringBuilder(10000000);
               
            String columnas = "cm_facturas_id,";
            columnas += "estado_factura,";
            columnas += "usuario_crea,";
            columnas += "terminal_crea,";
            columnas += "fecha_hora_crea";
            
            if(listFacturaEstados !=null && !listFacturaEstados.isEmpty()){

                String strQuery = "INSERT INTO cm_factura_estados (" + columnas + ") values ";
                stringBuilder.append(strQuery);
                for (CmFacturaEstado facturaEstado : listFacturaEstados) {
                    stringBuilder.append(" (");
                    stringBuilder.append("").append(facturaEstado.getCmFactura().getId()).append(",");
                    stringBuilder.append("").append(facturaEstado.getEstadoFactura()).append(",");
                    stringBuilder.append("'").append(facturaEstado.getUsuarioCrea()).append("',");
                    stringBuilder.append("'").append(facturaEstado.getTerminalCrea()).append("',");
                    stringBuilder.append(" NOW() ");
                    stringBuilder.append("),");
                }
                String queryString = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
                Query query = getEntityManager().createNativeQuery(queryString);
                query.executeUpdate();
            }
            
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void borrarFacturasEstado(List<CmFacturaEstado> listFacturaEstados, int estado) throws Exception {
        try {
            if (listFacturaEstados != null && !listFacturaEstados.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder(10000000);
                for (CmFacturaEstado facturaEstado : listFacturaEstados) {
                    stringBuilder.append(facturaEstado.getCmFactura().getId());
                    stringBuilder.append(",");
                }
                String idFacturasParaBorrar = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
                Query q = getEntityManager().createNativeQuery("DELETE FROM cm_factura_estados WHERE cm_facturas_id IN ( " + idFacturasParaBorrar + ") and estado_factura = " + estado + " ");
                q.executeUpdate();
            }
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
  
    @Override
    public List<CmFacturaEstado> buscarFacturasNoPermitidasPorUsuario(int estado, String usuario, List<CmFacturaEstado> listFacturaEstados) throws Exception {
        List<CmFacturaEstado> listaResultados = new ArrayList();
        try {
            StringBuilder stringBuilder = new StringBuilder(10000000);

            if (listFacturaEstados != null && !listFacturaEstados.isEmpty()) {

                for (CmFacturaEstado facturaEstado : listFacturaEstados) {
                    stringBuilder.append(facturaEstado.getCmFactura().getId());
                    stringBuilder.append(",");
                }
                String idFacturas = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);

                String strQuery = "SELECT  cmfe.cm_facturas_id, cmfe.estado_factura, "
                        + " cmfe.usuario_crea, cmfe.terminal_crea, "
                        + " cmfe.fecha_hora_crea, cmf.numero_facturado, cmf.numero_radicado  "
                        + " FROM cm_factura_estados cmfe INNER JOIN cm_facturas cmf ON "
                        + " cmf.id = cmfe.cm_facturas_id WHERE cmfe.estado_factura = " + estado + " and "
                        + " cmfe.cm_facturas_id IN (" + idFacturas + ") and cmfe.usuario_crea != '" + usuario + "'";
                Query nativeQuery = em.createNativeQuery(strQuery);
                List<Object[]> results = nativeQuery.getResultList();
                
                
                for (Object[] result : results) { 
                    CmFactura factura =  new CmFactura((Integer) result[0]);
                    factura.setNumeroFacturado((String) result[5]);
                    factura.setNumeroRadicado((Integer)result[6]);  
                    CmFacturaEstado estados = new CmFacturaEstado(
                             factura,
                            (Integer) result[1],
                            (String) result[2],
                            (String) result[3],
                            (Date) result[4]
                    );  
                    listaResultados.add(estados);
                }
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }
    
    @Override
    public List<CmFacturaEstado> buscarFacturasUsadasPorUsuario(int estado, String usuario, List<CmFacturaEstado> listFacturaEstados) throws Exception {
        List<CmFacturaEstado> listaResultados = new ArrayList();
        try {
            StringBuilder stringBuilder = new StringBuilder(10000000);

            if (listFacturaEstados != null && !listFacturaEstados.isEmpty()) {

                for (CmFacturaEstado facturaEstado : listFacturaEstados) {
                    stringBuilder.append(facturaEstado.getCmFactura().getId());
                    stringBuilder.append(",");
                }
                String idFacturas = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);

                String strQuery = "SELECT  cmfe.cm_facturas_id, cmfe.estado_factura, "
                        + " cmfe.usuario_crea, cmfe.terminal_crea, "
                        + " cmfe.fecha_hora_crea, cmf.numero_facturado, cmf.numero_radicado  "
                        + " FROM cm_factura_estados cmfe INNER JOIN cm_facturas cmf ON "
                        + " cmf.id = cmfe.cm_facturas_id WHERE cmfe.estado_factura = " + estado + " and "
                        + " cmfe.cm_facturas_id IN (" + idFacturas + ") and cmfe.usuario_crea = '" + usuario + "'";
                Query nativeQuery = em.createNativeQuery(strQuery);
                List<Object[]> results = nativeQuery.getResultList();
                
                
                for (Object[] result : results) { 
                    CmFactura factura =  new CmFactura((Integer) result[0]);
                    factura.setNumeroFacturado((String) result[5]);
                    factura.setNumeroRadicado((Integer)result[6]);  
                    CmFacturaEstado estados = new CmFacturaEstado(
                             factura,
                            (Integer) result[1],
                            (String) result[2],
                            (String) result[3],
                            (Date) result[4]
                    );  
                    listaResultados.add(estados);
                }
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }
   
    public static CmFacturaEstado castEntidadNegocio(CmFacturaEstados ent) {
        CmFacturaEstado neg = new CmFacturaEstado();
        neg.setId(ent.getId());
        neg.setEstadoFactura(ent.getEstadoFactura());
        neg.setCmFactura(new CmFactura(ent.getCmFacturasId().getId()));
        neg.setOrigenFactura(ent.getCmFacturasId().getOrigenFactura());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        return neg;
    }

    public static CmFacturaEstados castNegocioEntidad(CmFacturaEstado neg) {
        CmFacturaEstados ent = new CmFacturaEstados();
        ent.setId(neg.getId());
        ent.setEstadoFactura(neg.getEstadoFactura());
        ent.setCmFacturasId(new CmFacturas(neg.getCmFactura().getId()));
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        return ent;
    }
    
 }
