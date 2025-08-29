/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.crue;

import com.saviasaludeps.savia.dominio.crue.AuAnexo2;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Diagnostico;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo2Diagnosticos;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos2;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2DiagnosticoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author AlexanderDiaz
 */
@Stateless
@Remote(AuAnexo2DiagnosticoRemoto.class)
@Local(AuAnexo2DiagnosticoLocal.class)
public class AuAnexo2DiagnosticoServicio extends GenericoServicio implements AuAnexo2DiagnosticoRemoto, AuAnexo2DiagnosticoLocal {

    @Override
    public int insertar(AuAnexo2Diagnostico obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }
    
    @Override
    public void actualizar(AuAnexo2Diagnostico obj) throws Exception {
        try {
            String sql = "UPDATE AuAnexo2Diagnosticos SET "
                    + "maDiagnosticosId = :maDiagnosticosId, "
                    + "maDiagnosticosCodigo = :maDiagnosticosCodigo, "
                    + "maDiagnosticosValor = :maDiagnosticosValor, "
                    + "principal = :principal, "
                    + "auAnexos2Id.id = :auAnexos2Id "
                    + "WHERE id = :id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("maDiagnosticosId", obj.getMaDiagnosticosId());
            query.setParameter("maDiagnosticosCodigo", obj.getMaDiagnosticosCodigo());
            query.setParameter("maDiagnosticosValor", obj.getMaDiagnosticosValor());
            query.setParameter("principal", obj.isPrincipal());
            query.setParameter("auAnexos2Id", obj.getAuAnexo2().getId());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public List<AuAnexo2Diagnostico> consultarPorAuAnexo2(int idAuAnexo2) throws Exception {
        List<AuAnexo2Diagnostico> listResultado = new ArrayList<>();
        try {
            String sql = "SELECT r FROM AuAnexo2Diagnosticos r "
                    + "WHERE r.auAnexos2Id.id = :id "
                    + "ORDER BY r.maDiagnosticosCodigo";

            List<AuAnexo2Diagnosticos> listAuAnexo2Diagnosticos = getEntityManager().createQuery(sql)
                    .setParameter("id", idAuAnexo2)
                    .getResultList();

            for (AuAnexo2Diagnosticos diagnosticos : listAuAnexo2Diagnosticos) {
                listResultado.add(castEntidadNegocio(diagnosticos));
            }

        } catch (NoResultException e) {
            listResultado = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }

        return listResultado;
    }

    public static AuAnexo2Diagnosticos castNegocioEntidad(AuAnexo2Diagnostico obj) {
        AuAnexo2Diagnosticos ent = new AuAnexo2Diagnosticos();
        ent.setId(obj.getId());
        ent.setMaDiagnosticosCodigo(obj.getMaDiagnosticosCodigo());
        ent.setMaDiagnosticosId(obj.getMaDiagnosticosId());
        ent.setMaDiagnosticosValor(obj.getMaDiagnosticosValor());
        ent.setPrincipal(obj.isPrincipal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        //Datos de Tipo Diagnostico
        ent.setMaeTipoDiagnosticoId(obj.getMaeTipoDiagnosticoId());
        ent.setMaeTipoDiagnosticoCodigo(obj.getMaeTipoDiagnosticoCodigo());
        ent.setMaeTipoDiagnosticoValor(obj.getMaeTipoDiagnosticoValor());
        
        if (obj.getAuAnexo2() != null && obj.getAuAnexo2().getId() != null) {
            ent.setAuAnexos2Id(new AuAnexos2(obj.getAuAnexo2().getId()));
        }
        return ent;
    }

    public static AuAnexo2Diagnostico castEntidadNegocio(AuAnexo2Diagnosticos ent) {
        AuAnexo2Diagnostico obj = new AuAnexo2Diagnostico();
        obj.setId(ent.getId());
        obj.setMaDiagnosticosCodigo(ent.getMaDiagnosticosCodigo());
        obj.setMaDiagnosticosId(ent.getMaDiagnosticosId());
        obj.setMaDiagnosticosValor(ent.getMaDiagnosticosValor());
        obj.setPrincipal(ent.getPrincipal());
        
        obj.setMaeTipoDiagnosticoId(ent.getMaeTipoDiagnosticoId());
        obj.setMaeTipoDiagnosticoCodigo(ent.getMaeTipoDiagnosticoCodigo());
        obj.setMaeTipoDiagnosticoValor(ent.getMaeTipoDiagnosticoValor());
        
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        
        if (ent.getAuAnexos2Id() != null) {
            obj.setAuAnexo2(new AuAnexo2(ent.getAuAnexos2Id().getId()));
        }
        return obj;
    }

}
