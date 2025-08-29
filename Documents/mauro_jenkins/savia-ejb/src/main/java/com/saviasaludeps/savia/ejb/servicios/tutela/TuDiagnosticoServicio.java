/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.tutela;

import com.saviasaludeps.savia.dominio.tutela.TuDiagnostico;
import com.saviasaludeps.savia.ejb.entidades.TuTutelaDiagnosticos;
import com.saviasaludeps.savia.ejb.entidades.TuTutelaItems;
import com.saviasaludeps.savia.ejb.entidades.TuTutelas;
import static com.saviasaludeps.savia.ejb.servicios.tutela.TuTutelaItemServicio.castEntidadNegocio;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.tutela.TuDiagnosticoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author pavacca
 */
@Stateless
@Remote(TuDiagnosticoRemoto.class)
@Local(TuDiagnosticoLocal.class)
public class TuDiagnosticoServicio extends GenericoServicio implements TuDiagnosticoLocal, TuDiagnosticoRemoto {

    @Override
    public TuDiagnostico consultar(int id) throws Exception {
        TuDiagnostico objRes = null;
        try {
            objRes = castEntidadNegocio((TuTutelaDiagnosticos) getEntityManager().find(TuTutelaDiagnosticos.class, id));
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
    public int insertar(TuDiagnostico tuTutelaEstadoRepresentante) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(tuTutelaEstadoRepresentante)).getId();
            tuTutelaEstadoRepresentante.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al insertar un diagnostico al item");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(TuDiagnostico obj) throws Exception {
        try {
            String hql = "UPDATE TuTutelaDiagnosticos SET"
                    + " maDiagnosticosId = :maDiagnosticosId,"
                    + " maDiagnosticosCodigo = :maDiagnosticosCodigo,"
                    + " maDiagnosticosValor = :maDiagnosticosValor,"
                    + ((obj.getMaeTipoDiagnosticoId() > 0) ? " maeTipoDiagnosticoId = :maeTipoDiagnosticoId," : "")
                    + ((obj.getMaeTipoDiagnosticoCodigo() != null ) ? " maeTipoDiagnosticoCodigo = :maeTipoDiagnosticoCodigo," : "")
                    + ((obj.getMaeTipoDiagnosticoValor() != null) ? " maeTipoDiagnosticoValor = :maeTipoDiagnosticoValor," : "")
                    + ((obj.getTuTutelaItemsId() != null ) ? " tuTutelaItemsId.id = :tuTutelaItemsId," : "")
                    + ((obj.getTuTutelasId() != null ) ? " tuTutelasId.id = :tuTutelasId," : "")
                    + " esPrincipal = :esPrincipal,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("maDiagnosticosId", obj.getMaDiagnosticosId());
            query.setParameter("maDiagnosticosCodigo", obj.getMaDiagnosticosCodigo());
            query.setParameter("maDiagnosticosValor", obj.getMaDiagnosticosValor());
            if(obj.getMaeTipoDiagnosticoId() > 0)
                query.setParameter("maeTipoDiagnosticoId", obj.getMaeTipoDiagnosticoId());
            if(obj.getMaeTipoDiagnosticoCodigo() != null)
                query.setParameter("maeTipoDiagnosticoCodigo", obj.getMaeTipoDiagnosticoCodigo());
            if(obj.getMaeTipoDiagnosticoValor() != null)
                query.setParameter("maeTipoDiagnosticoValor", obj.getMaeTipoDiagnosticoValor());
            if(obj.getTuTutelaItemsId() != null)
                query.setParameter("tuTutelaItemsId", obj.getTuTutelaItemsId().getId());
            if(obj.getTuTutelasId() != null)
                query.setParameter("tuTutelasId", obj.getTuTutelasId().getId());
            query.setParameter("esPrincipal", obj.isEsPrincipal());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public TuDiagnostico eliminar(int id) throws Exception {
        TuDiagnostico obj = null;
        try {
            TuTutelaDiagnosticos ent = getEntityManager().find(TuTutelaDiagnosticos.class, id);
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

    public static TuTutelaDiagnosticos castNegocioEntidad(TuDiagnostico per) {
        TuTutelaDiagnosticos obj = new TuTutelaDiagnosticos();
        obj.setId(per.getId());
        if(per.getTuTutelasId() != null)
            obj.setTuTutelasId(new TuTutelas(per.getTuTutelasId().getId()));
        if(per.getTuTutelaItemsId() != null)
            obj.setTuTutelaItemsId(new TuTutelaItems(per.getTuTutelaItemsId().getId()));
        obj.setMaDiagnosticosId(per.getMaDiagnosticosId());
        obj.setMaDiagnosticosCodigo(per.getMaDiagnosticosCodigo());
        obj.setMaDiagnosticosValor(per.getMaDiagnosticosValor());
        obj.setMaeTipoDiagnosticoId(per.getMaeTipoDiagnosticoId());
        obj.setMaeTipoDiagnosticoCodigo(per.getMaeTipoDiagnosticoCodigo());
        obj.setMaeTipoDiagnosticoValor(per.getMaeTipoDiagnosticoValor());
        obj.setEsPrincipal(per.isEsPrincipal());
        //Auditoria
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static List<TuDiagnostico> castEntidadNegocio(List<TuTutelaDiagnosticos> serviciosNegocio) {
        List<TuDiagnostico> listaServicios = new ArrayList<>();
        serviciosNegocio.forEach(servicioNegocio -> {
            listaServicios.add(castEntidadNegocio(servicioNegocio));
        });
        return listaServicios;
    }

    public static TuDiagnostico castEntidadNegocio(TuTutelaDiagnosticos per) {
        TuDiagnostico obj = new TuDiagnostico();
        obj.setId(per.getId());
        obj.setMaDiagnosticosId(per.getMaDiagnosticosId());
        obj.setMaDiagnosticosCodigo(per.getMaDiagnosticosCodigo());
        obj.setMaDiagnosticosValor(per.getMaDiagnosticosValor());
        obj.setMaeTipoDiagnosticoId(per.getMaeTipoDiagnosticoId());
        obj.setMaeTipoDiagnosticoCodigo(per.getMaeTipoDiagnosticoCodigo());
        obj.setMaeTipoDiagnosticoValor(per.getMaeTipoDiagnosticoValor());
        obj.setEsPrincipal(per.getEsPrincipal());
        //Auditoria
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }
}
