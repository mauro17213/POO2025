/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpEntregaSuministro;
import com.saviasaludeps.savia.ejb.entidades.MpEntregaSuministros;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.mipres.MpSuministroRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(MpSuministroRemoto.class)
public class MpEntregaSuministroServicio extends GenericoServicio implements MpSuministroRemoto {

    @Override
    public List<MpEntregaSuministro> consultarListaPorEntrega(int id) throws Exception {

        List<MpEntregaSuministro> listaResultado = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT mp FROM MpEntregaSuministros mp WHERE mpProgramadaEntregasId.id = :id ");
            List<MpEntregaSuministros> lista = getEntityManager().createQuery(strQuery.toString())
                    .setParameter("id", id)
                    .getResultList();
            lista.forEach(mpSuministro -> listaResultado.add(castEntidadNegocio(mpSuministro)));
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultado;
    }

    public static MpEntregaSuministro castEntidadNegocio(MpEntregaSuministros ent) {
        MpEntregaSuministro obj = new MpEntregaSuministro();
        obj.setId(ent.getId());
        obj.setIdSuministro(ent.getIdSuministro());
//        obj.setNumeroEntrega(ent.getNumeroEntrega());
        obj.setUltimaEntrega(ent.getUltimaEntrega());
//        obj.setEntregaCompleta(ent.getEntregaCompleta());
        obj.setCausaNoEntrega(obj.getCausaNoEntrega());
        obj.setEstado(obj.getEstado());
        obj.setFechaSuministro(ent.getFechaHoraSuminisro());
        obj.setFechaAnulacion(obj.getFechaAnulacion());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }

}
