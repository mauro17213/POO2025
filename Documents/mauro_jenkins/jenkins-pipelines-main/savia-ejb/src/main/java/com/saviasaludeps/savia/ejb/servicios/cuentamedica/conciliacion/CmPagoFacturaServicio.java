package com.saviasaludeps.savia.ejb.servicios.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmPago;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmPagoFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmPagoTransaccion;
import com.saviasaludeps.savia.ejb.entidades.CmPagoFacturas;
import com.saviasaludeps.savia.ejb.entidades.CmPagoTransacciones;
import com.saviasaludeps.savia.ejb.entidades.CmPagos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmPagoFacturaRemoto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
@Remote(CmPagoFacturaRemoto.class)
public class CmPagoFacturaServicio extends GenericoServicio implements CmPagoFacturaRemoto {
    
    @Override
    public CmPagoFactura consultar(int id) throws Exception {
        CmPagoFactura objRes = null;
        try {
            objRes = castEntidadNegocio((CmPagoFacturas) getEntityManager().find(CmPagoFacturas.class, id));
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
    public int insertar(CmPagoFactura obj) throws Exception {
        int id = 0;
        try {
            CmPagoFacturas per = castNegocioEntidad(obj);
            id = (int) getEntityManager().merge(per).getId();
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "pago ya existe.");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }
    
    @Override
    public void actualizar(CmPagoFactura obj) throws Exception {
        //
    }
    
    @Override
    public CmPagoFactura eliminar(int id) throws Exception {
        CmPagoFactura obj = null;
        try {
            CmPagoFacturas ent = getEntityManager().find(CmPagoFacturas.class, id);
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
    
    public static CmPagoFactura castEntidadNegocio(CmPagoFacturas per) {
        CmPagoFactura obj = new CmPagoFactura();
        obj.setId(per.getId());
        obj.setConsecutivo(per.getConsecutivo());
        obj.setNumeroFactura(per.getNumeroFactura());
        obj.setClaseDocumento(per.getClaseDocumento());
        obj.setCmFacturaValor(per.getCmFacturaValor());
        obj.setCmFacturaFecha(per.getCmFacturaFecha());
        obj.setCmFacturaEstado(per.getCmFacturaEstado());
        obj.setDocumentoContable(per.getDocumentoContable());
        obj.setValorBruto(per.getValorBruto());
        obj.setValorNeto(per.getValorNeto());
        obj.setValorDeducciones(per.getValorDeducciones());
        obj.setDescripcion(per.getDescripcion());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        if (per.getCmFacturasId() != null) {
            obj.setCmFacturasId(new CmFactura(per.getCmFacturasId().getId()));
        }
        obj.setCmPagoTransaccionesId(new CmPagoTransaccion(per.getCmPagoTransaccionesId().getId()));
        obj.setCmPagosId(new CmPago(per.getCmPagosId().getId()));
        return obj;
    }
    
    public static CmPagoFacturas castNegocioEntidad(CmPagoFactura obj) {
        CmPagoFacturas per = new CmPagoFacturas();
        per.setId(obj.getId());
        per.setConsecutivo(obj.getConsecutivo());
        per.setNumeroFactura(obj.getNumeroFactura());
        per.setDocumentoContable(obj.getDocumentoContable());
        per.setClaseDocumento(obj.getClaseDocumento());
        per.setCmFacturaValor(obj.getCmFacturaValor());
        per.setCmFacturaFecha(obj.getCmFacturaFecha());
        per.setCmFacturaEstado(obj.getCmFacturaEstado());
        per.setValorBruto(obj.getValorBruto());
        per.setValorNeto(obj.getValorNeto());
        per.setValorDeducciones(obj.getValorDeducciones());
        per.setDescripcion(obj.getDescripcion());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        //per.setCmFacturasId(new CmFacturas(obj.getCmFacturasId().getId()));
        per.setCmPagoTransaccionesId(new CmPagoTransacciones(obj.getCmPagoTransaccionesId().getId()));
        per.setCmPagosId(new CmPagos(obj.getCmPagosId().getId()));
        return per;
    }
    
    @Override
    public Map<String, CmPagoFactura> consultarFacturaXNumeroNit(String numeroFactura, String nit) throws java.lang.Exception {
        //List<CmPagoFactura> listResult = new ArrayList<>();
        Map<String, CmPagoFactura> listResult = new HashMap<>();
        try {
            String strQuery = "FROM CmPagoFacturas cp "
                    + "WHERE cp.numeroFactura = '" + numeroFactura
                    + "' AND cp.cmPagosId.documento =  '" + nit
                    + "' ORDER BY cp.id";
            List<CmPagoFacturas> list = getEntityManager()
                    .createQuery(strQuery).setMaxResults(5000)
                    .getResultList();
            for (CmPagoFacturas per : list) {
                listResult.put(
                        nit + "||"
                        + per.getClaseDocumento() + "||"
                        + numeroFactura + "||"
                        + per.getCmPagosId().getFechaHora().toString().substring(0, 19) + "||"
                        + per.getDocumentoContable(),
                         castEntidadNegocio(per));
            }
        } catch (NoResultException e) {
            listResult = new HashMap<>();
        } catch (Exception e) {
            listResult = new HashMap<>();
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
}
