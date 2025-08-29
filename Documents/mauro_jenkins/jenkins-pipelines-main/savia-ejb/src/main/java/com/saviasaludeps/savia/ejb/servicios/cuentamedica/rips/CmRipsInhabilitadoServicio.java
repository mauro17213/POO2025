package com.saviasaludeps.savia.ejb.servicios.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsInhabilitado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmRipsInhabilitados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmRipsInhabilitadoRemoto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
@Remote(CmRipsInhabilitadoRemoto.class)
public class CmRipsInhabilitadoServicio extends GenericoServicio implements CmRipsInhabilitadoRemoto {

    @Override
    public List<CmRipsInhabilitado> consultarModalidadInhabilitada(ParamConsulta paramConsulta) throws Exception {
        List<CmRipsInhabilitado> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "SELECT i FROM CmRipsInhabilitados i "
                    + "WHERE i.id > 0 ";
            strQuery += "AND i.fechaHoraDesde < '" + new java.sql.Timestamp(((Date) paramConsulta.getParametroConsulta1()).getTime()) + "' ";
            strQuery += "AND i.fechaHoraHasta > '" + new java.sql.Timestamp(((Date) paramConsulta.getParametroConsulta1()).getTime()) + "' ";
            strQuery += "AND i.maeContratoModalidadId = " + (int) paramConsulta.getParametroConsulta2();
            strQuery += "AND i.coberturaCierre = " + (int) paramConsulta.getParametroConsulta3();
            List<CmRipsInhabilitados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CmRipsInhabilitados ent : list) {
                listResult.add(castEntidadNegocio(ent));
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

    private CmRipsInhabilitado castEntidadNegocio(CmRipsInhabilitados ent) {
        CmRipsInhabilitado negocio = new CmRipsInhabilitado();
        negocio.setId(ent.getId());
        negocio.setMotivo(ent.getMotivo());
        negocio.setFechaHoraDesde(ent.getFechaHoraDesde());
        negocio.setFechaHoraHasta(ent.getFechaHoraHasta());
        negocio.setMaeContratoModalidadId(ent.getMaeContratoModalidadId());
        negocio.setMaeContratoModalidadCodigo(ent.getMaeContratoModalidadCodigo());
        negocio.setMaeContratoModalidadValor(ent.getMaeContratoModalidadValor());
        negocio.setFechaHoraCrea(ent.getFechaHoraCrea());
        negocio.setTerminalCrea(ent.getTerminalCrea());
        negocio.setUsuarioCrea(ent.getUsuarioCrea());
        negocio.setUsuarioModifica(ent.getUsuarioModifica());
        negocio.setTerminalModifica(ent.getTerminalModifica());
        negocio.setFechaHoraModifica(ent.getFechaHoraModifica());
        negocio.setCoberturaCierre(ent.getCoberturaCierre());
        return negocio;
    }

}
