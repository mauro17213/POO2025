/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.ReporteImpresion;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.ReporteImpresionRemoto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Stiven Giraldo
 */
@Stateless
@Remote(ReporteImpresionRemoto.class)
public class ReporteImpresionServicio extends GenericoServicio implements ReporteImpresionRemoto{

    @Override
    public List<ReporteImpresion> consultarImpresionesAnexo4PorFecha(Date fechaInicio, Date fechaFin) throws Exception {
        List<ReporteImpresion> listResult = new ArrayList();
        try {
            String strQuery = "SELECT COUNT(*) AS Cantidad,DATE(fecha_hora_crea) "
                    + "AS Fecha FROM au_anexo4_impresiones "
                    + "WHERE id > 0 ";

            if (fechaInicio != null && fechaFin != null) {
                strQuery += "AND fecha_hora_crea BETWEEN :fh_inicio AND :fh_fin ";
            } else if (fechaInicio != null) {
                strQuery += "AND fecha_hora_crea >= :fh_inicio ";
            } else if (fechaFin != null) {
                strQuery += "AND fecha_hora_crea <= :fh_fin ";
            }

            strQuery += "GROUP BY DATE(fecha_hora_crea) ";
            strQuery += "ORDER BY DATE(fecha_hora_crea) desc ";

            Query query = em.createNativeQuery(strQuery);
            if (fechaInicio != null && fechaFin != null) {
                query.setParameter("fh_inicio", fechaInicio);
                query.setParameter("fh_fin", fechaFin);
            } else if (fechaInicio != null) {
                query.setParameter("fh_inicio", fechaInicio);
            } else if (fechaFin != null) {
                query.setParameter("fh_fin", fechaFin);
            }
            List<Object[]> lstObj = query.getResultList();
            for (Object[] per : lstObj) {
                listResult.add(castReporteImpresion(per));
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

    private ReporteImpresion castReporteImpresion(Object[] per) {
        ReporteImpresion impresion = new ReporteImpresion();
        try {
            impresion.setCantidad(Integer.parseInt(per[0].toString()));
            impresion.setFecha(new SimpleDateFormat("yyyy-MM-dd").parse(per[1].toString()));
        } catch (NumberFormatException | ParseException e) {
        }
        return impresion;
    }
    
}
