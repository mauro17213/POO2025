/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.conciliacion;


import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmPagoFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmPagoFacturaRetencion;
import com.saviasaludeps.savia.ejb.entidades.CmPagoFacturaRetenciones;
import com.saviasaludeps.savia.ejb.entidades.CmPagoFacturas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmPagoFacturaRetencionRemoto;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
@Remote(CmPagoFacturaRetencionRemoto.class)
public class CmPagoFacturaRetencionServicio extends GenericoServicio implements CmPagoFacturaRetencionRemoto {



    @Override
    public CmPagoFacturaRetencion consultar(int id) throws Exception {
        CmPagoFacturaRetencion objRes = null;
        try {
            objRes = castEntidadNegocio((CmPagoFacturaRetenciones) getEntityManager().find(CmPagoFacturaRetenciones.class, id));
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
    public int insertar(CmPagoFacturaRetencion obj) throws Exception {
        int id = 0;
        try {
            CmPagoFacturaRetenciones per = castNegocioEntidad(obj);
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
    public void actualizar(CmPagoFacturaRetencion obj) throws Exception {
       //
    }

    @Override
    public CmPagoFacturaRetencion eliminar(int id) throws Exception {
        CmPagoFacturaRetencion obj = null;
        try {
            CmPagoFacturaRetenciones ent = getEntityManager().find(CmPagoFacturaRetenciones.class, id);
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

    public static CmPagoFacturaRetencion castEntidadNegocio(CmPagoFacturaRetenciones per) {
        CmPagoFacturaRetencion obj = new CmPagoFacturaRetencion();
        obj.setId(per.getId());
        obj.setCodigo(per.getCodigo());
        obj.setDescripcion(per.getDescripcion());
        obj.setValor(per.getValor());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setCmPagoFacturasId(new CmPagoFactura(per.getCmPagoFacturasId().getId()));
        return obj;
    }

    public static CmPagoFacturaRetenciones castNegocioEntidad(CmPagoFacturaRetencion obj) {
        CmPagoFacturaRetenciones per = new CmPagoFacturaRetenciones();
        per.setId(obj.getId());
        per.setCodigo(obj.getCodigo());
        per.setDescripcion(obj.getDescripcion());
        per.setValor(obj.getValor());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setCmPagoFacturasId(new CmPagoFacturas(obj.getCmPagoFacturasId().getId()));
        return per;
    }

}
