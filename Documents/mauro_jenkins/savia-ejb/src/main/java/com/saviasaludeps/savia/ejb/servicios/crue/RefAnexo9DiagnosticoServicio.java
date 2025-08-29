/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.crue;

import com.saviasaludeps.savia.dominio.crue.RefAnexo9;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9Diagnostico;
import com.saviasaludeps.savia.ejb.entidades.RefAnexo9Diagnosticos;
import com.saviasaludeps.savia.ejb.entidades.RefAnexos9;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.crue.RefAnexo9DiagnosticoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(RefAnexo9DiagnosticoRemoto.class)
@Local(RefAnexo9DiagnosticoLocal.class)
public class RefAnexo9DiagnosticoServicio extends GenericoServicio implements RefAnexo9DiagnosticoRemoto, RefAnexo9DiagnosticoLocal {

    @Override
    public int insertar(RefAnexo9Diagnostico obj) throws Exception {
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
    public void actualizar(RefAnexo9Diagnostico obj) throws Exception {
        try {
            String sql = "UPDATE RefAnexo9Diagnosticos SET maDiagnosticosId = :maDiagnosticosId, "
                    + "maDiagnosticosCodigo = :maDiagnosticosCodigo, "
                    + "maDiagnosticosValor = :maDiagnosticosValor, "
                    + "principal = :principal, "
                    + "refAnexos9Id.id = :refAnexos9Id, "
                    + "usuarioCrea = :usuarioCrea, "
                    + "terminalCrea = :terminalCrea, "
                    + "fechaHoraCrea = :fechaHoraCrea, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("maDiagnosticosId", obj.getMaDiagnosticosId());
            query.setParameter("maDiagnosticosCodigo", obj.getMaDiagnosticosCodigo());
            query.setParameter("maDiagnosticosValor", obj.getMaDiagnosticosValor());
            query.setParameter("principal", obj.isPrincipal());
            query.setParameter("refAnexos9Id", obj.getRefAnexo9().getId());
            query.setParameter("usuarioCrea", obj.getUsuarioCrea());
            query.setParameter("terminalCrea", obj.getTerminalCrea());
            query.setParameter("fechaHoraCrea", obj.getFechaHoraCrea());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
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
    public List<RefAnexo9Diagnostico> consultarPorRefAnexo9(int idRefAnexo9) throws Exception {
        List<RefAnexo9Diagnostico> listResultado = new ArrayList<>();
        try {
            String sql = "SELECT r FROM RefAnexo9Diagnosticos r "
                    + "WHERE r.refAnexos9Id.id = :id "
                    + "ORDER BY r.maDiagnosticosCodigo";

            List<RefAnexo9Diagnosticos> listRefAnexo9Diagnosticos = getEntityManager().createQuery(sql)
                    .setParameter("id", idRefAnexo9)
                    .getResultList();

            for (RefAnexo9Diagnosticos diagnosticos : listRefAnexo9Diagnosticos) {
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
    
    @Override
    public RefAnexo9Diagnostico eliminar(int id) throws Exception {
        RefAnexo9Diagnostico obj = null;
        try {
            RefAnexo9Diagnosticos ent = getEntityManager().find(RefAnexo9Diagnosticos.class, id);
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

    public static RefAnexo9Diagnosticos castNegocioEntidad(RefAnexo9Diagnostico obj) {
        RefAnexo9Diagnosticos ent = new RefAnexo9Diagnosticos();
        ent.setId(obj.getId());
        ent.setMaDiagnosticosCodigo(obj.getMaDiagnosticosCodigo());
        ent.setMaDiagnosticosId(obj.getMaDiagnosticosId());
        ent.setMaDiagnosticosValor(obj.getMaDiagnosticosValor());
        ent.setPrincipal(obj.isPrincipal());
        if (obj.getRefAnexo9() != null && obj.getRefAnexo9().getId() != null) {
            ent.setRefAnexos9Id(new RefAnexos9(obj.getRefAnexo9().getId()));
        }
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        return ent;
    }

    public static RefAnexo9Diagnostico castEntidadNegocio(RefAnexo9Diagnosticos ent) {
        RefAnexo9Diagnostico obj = new RefAnexo9Diagnostico();
        obj.setId(ent.getId());
        obj.setMaDiagnosticosCodigo(ent.getMaDiagnosticosCodigo());
        obj.setMaDiagnosticosId(ent.getMaDiagnosticosId());
        obj.setMaDiagnosticosValor(ent.getMaDiagnosticosValor());
        obj.setPrincipal(ent.getPrincipal());
        if (ent.getRefAnexos9Id() != null) {
            obj.setRefAnexo9(new RefAnexo9(ent.getRefAnexos9Id().getId()));
        }
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        return obj;
    }

}
