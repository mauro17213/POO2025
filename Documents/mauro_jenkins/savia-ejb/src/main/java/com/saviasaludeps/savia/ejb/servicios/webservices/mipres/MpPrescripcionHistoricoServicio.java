
package com.saviasaludeps.savia.ejb.servicios.webservices.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionHistorico;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionHistoricos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.webservices.mipres.MpPrescripcionHistoricoRemoto;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
@Remote(MpPrescripcionHistoricoRemoto.class)
public class MpPrescripcionHistoricoServicio extends GenericoServicio implements MpPrescripcionHistoricoRemoto {

    @Override
    public int insertar(MpPrescripcionHistorico historico) {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(historico)).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            try {
                System.out.println(e);
                Exception(INSERTAR, e);
            } catch (java.lang.Exception ex) {
                Logger.getLogger(MpPrescripcionHistoricoServicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static MpPrescripcionHistoricos castNegocioEntidad(MpPrescripcionHistorico obj) {
        MpPrescripcionHistoricos per = new MpPrescripcionHistoricos();
        per.setMpPrescripcionesId(new MpPrescripciones(obj.getMpPrescripcion().getId()));
        per.setIdPrescripcionTecnologia(obj.getIdPrescripcionTecnologia());
        per.setTipoTecnologia(obj.getTipoTecnologia());
        per.setEstado(obj.getEstado());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        return per;
    }

}
