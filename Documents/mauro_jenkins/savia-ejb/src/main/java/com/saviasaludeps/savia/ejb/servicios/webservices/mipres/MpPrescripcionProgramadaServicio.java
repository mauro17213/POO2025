/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.webservices.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionProgramada;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionProgramadas;
import static com.saviasaludeps.savia.ejb.servicios.mipres.MpProgramadaServicio.castNegocioEntidad;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.webservices.mipres.MpPrescripcionProgramadaRemoto;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.direccionamiento.Direccionamiento;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author jramirez
 */
@Stateless
@Remote(MpPrescripcionProgramadaRemoto.class)
public class MpPrescripcionProgramadaServicio extends GenericoServicio implements MpPrescripcionProgramadaRemoto {

    @Override
    public int insertar(MpPrescripcionProgramada programada) {
        int _id = 0;
        try {
                Thread.sleep(60 * 10);
            _id = (int) getEntityManager().merge(castNegocioEntidad(programada)).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            try {
                Thread.sleep(1000);
                System.out.println(e);
                Exception(INSERTAR, e);
            } catch (java.lang.Exception ex) {
                Logger.getLogger(MpPrescripcionProgramadaServicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    private CntPrestadorSedes consultarPrestadorSedePorCodigoHabilitacion(String codigoHabilitacion) throws Exception {
        CntPrestadorSedes objRes = null;
        try {
            String strQuery = "FROM CntPrestadorSedes c "
                    + "WHERE c.codigoHabilitacion ='" + codigoHabilitacion + "'";

            List<CntPrestadorSedes> list = getEntityManager().createQuery(strQuery).getResultList();
            if (list != null && !list.isEmpty()) {
                objRes = list.get(0);
            }
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            System.out.println(e);
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public int consultarPorPrescripcionTecnologiaEntrega(String tipoTecnologia, int prescripcion, int codTecnologia, int numeroEntrega) {
        int idProgramada = 0;
        try {
            String strQuery = "";
            strQuery += ("SELECT id FROM mp_prescripcion_programadas mp WHERE tipo_tecnologia = :tipoTecnologia ");
            switch (tipoTecnologia) {
                case "1":
                case "4":
                    strQuery += (" AND mp_prescripcion_medicamentos_id = :id ");

                    break;
                case "2":
                    strQuery += (" AND mp_prescripcion_tecnologias_id = :id ");

                    break;
                case "3":
                case "5":
                    strQuery += (" AND mp_prescripcion_insumos_id = :id ");
                    break;
            }
            strQuery += (" AND mp_prescripciones_id = :prescripcion");
            strQuery += (" AND entrega_numero = :entrega_numero ");

//            idProgramada = getEntityManager().createNativeQuery(strQuery.toString())
//                    .setParameter("tipoTecnologia", tipoTecnologia)
//                    .setParameter("id", codTecnologia)
//                    .setParameter("prescripcion", prescripcion)
//                    .setParameter("entrega_numero", numeroEntrega)
//                    .getFirstResult();
            List<Object[]> list = getEntityManager().createNativeQuery(strQuery)
                    .setParameter("tipoTecnologia", tipoTecnologia)
                    .setParameter("id", codTecnologia)
                    .setParameter("prescripcion", prescripcion)
                    .setParameter("entrega_numero", numeroEntrega)
                    .getResultList();
            List<MpPrescripcionProgramada> listResult = new ArrayList();
            if (list != null) {
                for (Object idAfiliado : list) {
                    MpPrescripcionProgramada afiliadoIn = new MpPrescripcionProgramada((Integer) idAfiliado);
                    listResult.add(afiliadoIn);
                }
            }
            idProgramada = listResult.get(0).getId();
            //*resultado = castEntidadNegocio(idProgramada);

        } catch (Exception e) {
            try {
                Exception(CONSULTAR, e);
            } catch (java.lang.Exception ex) {
                Logger.getLogger(MpPrescripcionProgramadaServicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            cerrarEntityManager();
        }
        return idProgramada;
    }

    @Override
    public Map<String, MpPrescripcionProgramada> consultarListaDireccionamiento(
            List<Direccionamiento > listaDireccionamientos) throws java.lang.Exception {
        String strQuery = "select id_transaccion, id_direccionamiento "
                + "from mp_prescripcion_programadas "
                + "WHERE id_transaccion IN ";
        String whereIn = "(";
        for (Direccionamiento direccionamiento : listaDireccionamientos) {
            if (direccionamiento.getID() != null
                    && !direccionamiento.getID().equals("")) {
                whereIn += "'" + direccionamiento.getID() + "',";
            }
        }
        //Remover ultima ,
        if (!whereIn.equals("(")) {
            whereIn = whereIn.substring(0, whereIn.length() - 1);
        }
        whereIn += ")";
        strQuery += whereIn;
        List<Object[]> listObjPrescripciones = null;
        try {
            listObjPrescripciones = getEntityManager().createNativeQuery(strQuery).getResultList();
        } catch (Exception ex) {

        } finally {
            cerrarEntityManager();
        }
        //List<MpPrescripcionProgramada> listaDireccionamientoLocales = new ArrayList<>();
        Map<String, MpPrescripcionProgramada> listaDireccionamientoLocales = new HashMap<>();
        for (Object[] obj : listObjPrescripciones) {
            MpPrescripcionProgramada direccionamiento = new MpPrescripcionProgramada();
            direccionamiento.setIdTransaccion((Integer) (obj[0]));
            direccionamiento.setIdDireccionamiento((Integer) (obj[1]));
            listaDireccionamientoLocales.put((Integer) (obj[0])+""+(Integer) (obj[1]), direccionamiento);
        }
        return listaDireccionamientoLocales;
    }

}
