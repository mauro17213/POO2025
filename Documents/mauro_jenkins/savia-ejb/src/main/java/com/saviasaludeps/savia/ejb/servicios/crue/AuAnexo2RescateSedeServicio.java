package com.saviasaludeps.savia.ejb.servicios.crue;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2RescateSede;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo2RescateSedes;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2RescateSedeRemoto;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author jdlopez
 */
@Stateless
@Local(AuAnexo2RescateSedeLocal.class)
@Remote(AuAnexo2RescateSedeRemoto.class)
public class AuAnexo2RescateSedeServicio extends GenericoServicio implements AuAnexo2RescateSedeLocal, AuAnexo2RescateSedeRemoto {

    @Override
    public int insertar(AuAnexo2RescateSede obj) throws java.lang.Exception {
        int id = 0;
        try {
            AuAnexo2RescateSedes entidad = this.castNegocioEntidad(obj);
            id = (int) getEntityManager().merge(entidad).getId();
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public AuAnexo2RescateSede consultar(int idAnexo2RescateSede) throws java.lang.Exception {
        AuAnexo2RescateSede resultado = new AuAnexo2RescateSede();

        try {
            resultado = castEntidadNegocio((AuAnexo2RescateSedes) getEntityManager().find(AuAnexo2RescateSedes.class, idAnexo2RescateSede));
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return resultado;
    }

    @Override
    public AuAnexo2RescateSede consultarIdSedeCapitaIdSedeOrigen(int idPrestadorSedeCapita, int idPrestadorSedeOrigen) throws java.lang.Exception {
        try {

            String query = "SELECT aars FROM AuAnexo2RescateSedes aars "
                    + "WHERE aars.cntPrestadorSedeCapitaId.id = :idPrestadorSedeCapita "
                    + "AND aars.cntPrestadorSedeOrigenId.id = :idPrestadorSedeOrigen";

            AuAnexo2RescateSedes result = (AuAnexo2RescateSedes) getEntityManager().createQuery(query)
                    .setParameter("idPrestadorSedeCapita", idPrestadorSedeCapita)
                    .setParameter("idPrestadorSedeOrigen", idPrestadorSedeOrigen)
                    .getSingleResult();

            return this.castEntidadNegocio(result);

        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return null;
    }

    private AuAnexo2RescateSedes castNegocioEntidad(AuAnexo2RescateSede obj) {
        AuAnexo2RescateSedes ent = new AuAnexo2RescateSedes();
        ent.setId(obj.getId());
        if (obj.getPrestadorSedeCapita() != null) {
            ent.setCntPrestadorSedeCapitaId(new CntPrestadorSedes(obj.getPrestadorSedeCapita().getId()));
        }
        if (obj.getPrestadorSedeOrigen() != null) {
            ent.setCntPrestadorSedeOrigenId(new CntPrestadorSedes(obj.getPrestadorSedeOrigen().getId()));
        }
        ent.setAplicaRescateAnexo2(obj.isAplicaRescateAnexo2());
        ent.setAplicaRescateAnexo3(obj.isAplicaRescateAnexo3());
        ent.setAplicaRescateAnexo3Hosp(obj.isAplicaRescateAnexo3Hosp());
        ent.setAplicaRescateHosp(obj.isAplicaRescateHosp());
        ent.setActivo(obj.isActivo());
        ent.setObservacion(obj.getObservacion());
        ent.setInactivoObservacion(obj.getInactivoObservacion());
        //auditoria
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        return ent;
    }

    private AuAnexo2RescateSede castEntidadNegocio(AuAnexo2RescateSedes ent) {
        AuAnexo2RescateSede obj = new AuAnexo2RescateSede();
        obj.setId(ent.getId());
        if (ent.getCntPrestadorSedeCapitaId() != null) {
            obj.setPrestadorSedeCapita(new CntPrestadorSede(ent.getCntPrestadorSedeCapitaId().getId()));
        }
        if (ent.getCntPrestadorSedeOrigenId() != null) {
            obj.setPrestadorSedeOrigen(new CntPrestadorSede(ent.getCntPrestadorSedeOrigenId().getId()));
        }
        obj.setAplicaRescateAnexo2(ent.getAplicaRescateAnexo2());
        obj.setAplicaRescateAnexo3(ent.getAplicaRescateAnexo3());
        obj.setAplicaRescateAnexo3Hosp(ent.getAplicaRescateAnexo3Hosp());
        obj.setAplicaRescateHosp(ent.getAplicaRescateHosp());
        obj.setActivo(ent.getActivo());
        obj.setObservacion(ent.getObservacion());
        obj.setInactivoObservacion(ent.getInactivoObservacion());
        //auditoria
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        return obj;
    }
}
