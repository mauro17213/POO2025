
package com.saviasaludeps.savia.ejb.servicios.aseguramiento;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegMaNovedad;
import com.saviasaludeps.savia.ejb.entidades.AsegMaNovedades;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.aseguramiento.MaNovedadRemoto;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Stateless
@Remote(MaNovedadRemoto.class)
public class MaNovedadServicio extends GenericoServicio implements MaNovedadRemoto {

    
    
    @Override
    public AsegMaNovedad consultarNovedad(String codigo) throws Exception {
        AsegMaNovedad novedad = null;
        try {
            TypedQuery<AsegMaNovedades> query = 
                    em.createNamedQuery("AsegMaNovedades.findByCodigoNovedad", AsegMaNovedades.class);
            query.setParameter("codigoNovedad",codigo);
            AsegMaNovedades dias = query.getSingleResult();
            if (dias!=null){
                novedad= castEntidadNegocio(dias);
            }
        } catch (NoResultException e) {
            novedad = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return novedad;
    }


    public static AsegMaNovedad castEntidadNegocio(AsegMaNovedades per) {
        AsegMaNovedad obj = new AsegMaNovedad();
        obj.setId(per.getId());
        obj.setCodigoNovedad(per.getCodigoNovedad());
        obj.setDescripcionNovedad(per.getDescripcionNovedad());
        obj.setRegimen(per.getRegimen());
        obj.setActivo(per.getActivo());
        obj.setCodigoNovedadPadre(per.getCodigoNovedadPadre());
        obj.setReporteNormativo(per.getReporteNormativo());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalMofica());// nombre malo
        return obj;
    }

    public static AsegMaNovedades castNegocioEntidad(AsegMaNovedad obj) {
        AsegMaNovedades per = new AsegMaNovedades();
        per.setId(obj.getId());
        per.setCodigoNovedad(obj.getCodigoNovedad());
        obj.setDescripcionNovedad(per.getDescripcionNovedad());
        per.setRegimen(obj.getRegimen());
        per.setActivo(obj.isActivo());
        //Auditoria
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        return per;
    }
}
