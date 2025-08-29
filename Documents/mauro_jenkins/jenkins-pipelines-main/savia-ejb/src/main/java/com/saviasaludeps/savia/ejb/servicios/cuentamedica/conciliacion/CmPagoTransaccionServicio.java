/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.cuentamedica.conciliacion;


import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmPago;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmPagoTransaccion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmPagoTransacciones;
import com.saviasaludeps.savia.ejb.entidades.CmPagos;
import com.saviasaludeps.savia.ejb.utilidades.Encrypt;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmPagoTransaccionRemoto;

@Stateless
@Remote(CmPagoTransaccionRemoto.class)
public class CmPagoTransaccionServicio extends GenericoServicio implements CmPagoTransaccionRemoto {



    @Override
    public CmPagoTransaccion consultar(int id) throws Exception {
        CmPagoTransaccion objRes = null;
        try {
            objRes = castEntidadNegocio((CmPagoTransacciones) getEntityManager().find(CmPagoTransacciones.class, id));
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
    public int insertar(CmPagoTransaccion obj) throws Exception {
        int id = 0;
        try {
            CmPagoTransacciones per = castNegocioEntidad(obj);
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
    public void actualizar(CmPagoTransaccion obj) throws Exception {
       //
    }

    @Override
    public CmPagoTransaccion eliminar(int id) throws Exception {
        CmPagoTransaccion obj = null;
        try {
            CmPagoTransacciones ent = getEntityManager().find(CmPagoTransacciones.class, id);
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

    public static CmPagoTransaccion castEntidadNegocio(CmPagoTransacciones per) {
        CmPagoTransaccion obj = new CmPagoTransaccion();
        obj.setId(per.getId());
        obj.setNut(per.getNut());
        obj.setFacturas(per.getFacturas());
        obj.setPaquete(per.getPaquete());
        obj.setFinalizado(per.getFinalizado());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setCmPagosId(new CmPago(per.getCmPagosId().getId()));
        return obj;
    }

    public static CmPagoTransacciones castNegocioEntidad(CmPagoTransaccion obj) {
        CmPagoTransacciones per = new CmPagoTransacciones();
        per.setId(obj.getId());
        per.setNut(obj.getNut());
        per.setFacturas(obj.getFacturas());
        per.setPaquete(obj.getPaquete());
        per.setFinalizado(obj.isFinalizado());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setCmPagosId(new CmPagos(obj.getCmPagosId().getId()));
        return per;
    }

}
