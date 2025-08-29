/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimiento;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoServicio;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuSeguimientoServicios;
import com.saviasaludeps.savia.ejb.entidades.AuSeguimientos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuSeguimientoServicioRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author iavenegas
 */
@Stateless
@Remote(AuSeguimientoServicioRemoto.class)
public class AuSeguimientoServicioServicio extends GenericoServicio implements AuSeguimientoServicioRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuSeguimientoServicios p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += "AND p.activo = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            cantidad = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            cantidad = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cantidad;
    }

    @Override
    public List<AuSeguimientoServicio> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuSeguimientoServicio> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuSeguimientoServicios p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += "AND p.activo = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id ASC";
            }
            List<AuSeguimientoServicios> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuSeguimientoServicios per : list) {
                listaResultados.add(castEntidadNegocio(per));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }

    @Override
    public AuSeguimientoServicio consultar(int id) throws Exception {
        AuSeguimientoServicio objRes = null;
        try {
            objRes = castEntidadNegocio((AuSeguimientoServicios) getEntityManager().find(AuSeguimientoServicios.class, id));
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
    public int insertar(AuSeguimientoServicio obj) throws Exception {
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
    public void actualizar(AuSeguimientoServicio obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuSeguimientoServicios a SET ";
            strQuery += "a.litros = :litros, ";
            strQuery += "a.tiempoUso = :tiempoUso, ";
            strQuery += "a.tiempoDuracionTratamiento = :tiempoDuracionTratamiento, ";
            strQuery += "a.gasesArteriales = :gasesArteriales, ";
            strQuery += "a.presion = :presion, ";
            strQuery += "a.rampa = :rampa, ";
            strQuery += "a.maeTipoMascaraId = :maeTipoMascaraId, ";
            strQuery += "a.maeTipoMascaraCodigo = :maeTipoMascaraCodigo, ";
            strQuery += "a.maeTipoMascaraValor = :maeTipoMascaraValor, ";
            strQuery += "a.maeTallaMascaraId = :maeTallaMascaraId, ";
            strQuery += "a.maeTallaMascaraCodigo = :maeTallaMascaraCodigo, ";
            strQuery += "a.maeTallaMascaraValor = :maeTallaMascaraValor, ";
            strQuery += "a.maeTipoSondaId = :maeTipoSondaId, ";
            strQuery += "a.maeTipoSondaCodigo = :maeTipoSondaCodigo, ";
            strQuery += "a.maeTipoSondaValor = :maeTipoSondaValor, ";
            strQuery += "a.maeTipoInsumoId = :maeTipoInsumoId, ";
            strQuery += "a.maeTipoInsumoCodigo = :maeTipoInsumoCodigo, ";
            strQuery += "a.maeTipoInsumoValor = :maeTipoInsumoValor, ";
            strQuery += "a.presiones = :presiones, ";
            strQuery += "a.tipoMascaraTos = :tipoMascaraTos, ";
            strQuery += "a.observaciones = :observaciones, ";
            strQuery += "a.usuarioModifica = :usuarioModifica, ";
            strQuery += "a.terminalModifica = :terminalModifica, ";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("litros", obj.getLitros());
            query.setParameter("tiempoUso", obj.getTiempoUso());
            query.setParameter("tiempoDuracionTratamiento", obj.getTiempoDuracionTratamiento());
            query.setParameter("gasesArteriales", obj.getGasesArteriales());
            query.setParameter("presion", obj.getPresion());
            query.setParameter("rampa", obj.getRampa());
            query.setParameter("maeTipoMascaraId", obj.getMaeTipoMascaraId());
            query.setParameter("maeTipoMascaraCodigo", obj.getMaeTipoMascaraCodigo());
            query.setParameter("maeTipoMascaraValor", obj.getMaeTipoMascaraValor());
            query.setParameter("maeTallaMascaraId", obj.getMaeTallaMascaraId());
            query.setParameter("maeTallaMascaraCodigo", obj.getMaeTallaMascaraCodigo());
            query.setParameter("maeTallaMascaraValor", obj.getMaeTallaMascaraValor());
            query.setParameter("maeTipoSondaId", obj.getMaeTipoSondaId());
            query.setParameter("maeTipoSondaCodigo", obj.getMaeTipoSondaCodigo());
            query.setParameter("maeTipoSondaValor", obj.getMaeTipoSondaValor());
            query.setParameter("maeTipoInsumoId", obj.getMaeTipoInsumoId());
            query.setParameter("maeTipoInsumoCodigo", obj.getMaeTipoInsumoCodigo());
            query.setParameter("maeTipoInsumoValor", obj.getMaeTipoInsumoValor());
            query.setParameter("presiones", obj.getPresiones());
            query.setParameter("tipoMascaraTos", obj.getTipoMascaraTos());
            query.setParameter("observaciones", obj.getObservaciones());

            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    private static AuSeguimientoServicio castEntidadNegocio(AuSeguimientoServicios ent) {
        AuSeguimientoServicio negocio = new AuSeguimientoServicio();
        negocio.setId(ent.getId());
        negocio.setMaeSeguimientoServicioId(ent.getMaeSeguimientoServicioId());
        negocio.setMaeSeguimientoServicioCodigo(ent.getMaeSeguimientoServicioCodigo());
        negocio.setMaeSeguimientoServicioValor(ent.getMaeSeguimientoServicioValor());
        negocio.setLitros(ent.getLitros());
        negocio.setTiempoUso(ent.getTiempoUso());
        negocio.setTiempoDuracionTratamiento(ent.getTiempoDuracionTratamiento());
        negocio.setGasesArteriales(ent.getGasesArteriales());
        negocio.setPresion(ent.getPresion());
        negocio.setRampa(ent.getRampa());
        negocio.setMaeTipoMascaraId(ent.getMaeTipoMascaraId());
        negocio.setMaeTipoMascaraCodigo(ent.getMaeTipoMascaraCodigo());
        negocio.setMaeTipoMascaraValor(ent.getMaeTipoMascaraValor());
        negocio.setMaeTallaMascaraId(ent.getMaeTallaMascaraId());
        negocio.setMaeTallaMascaraCodigo(ent.getMaeTallaMascaraCodigo());
        negocio.setMaeTallaMascaraValor(ent.getMaeTallaMascaraValor());
        negocio.setMaeTipoSondaId(ent.getMaeTipoSondaId());
        negocio.setMaeTipoSondaCodigo(ent.getMaeTipoSondaCodigo());
        negocio.setMaeTipoSondaValor(ent.getMaeTipoSondaValor());
        negocio.setMaeTipoInsumoId(ent.getMaeTipoInsumoId());
        negocio.setMaeTipoInsumoCodigo(ent.getMaeTipoInsumoCodigo());
        negocio.setMaeTipoInsumoValor(ent.getMaeTipoInsumoValor());
        negocio.setPresiones(ent.getPresiones());
        negocio.setTipoMascaraTos(ent.getTipoMascaraTos());
        negocio.setObservaciones(ent.getObservaciones());
        negocio.setBorrado(ent.getBorrado());
        negocio.setBorrado(ent.getBorrado());

        negocio.setAuSeguimientoId(new AuSeguimiento(ent.getAuSeguimientoId().getId()));

        negocio.setUsuarioCrea(ent.getUsuarioCrea());
        negocio.setTerminalCrea(ent.getTerminalCrea());
        negocio.setFechaHoraCrea(ent.getFechaHoraCrea());
        negocio.setUsuarioModifica(ent.getUsuarioModifica());
        negocio.setTerminalModifica(ent.getTerminalModifica());
        negocio.setFechaHoraModifica(ent.getFechaHoraModifica());
        return negocio;
    }

    private AuSeguimientoServicios castNegocioEntidad(AuSeguimientoServicio negocio) {
        AuSeguimientoServicios entidad = new AuSeguimientoServicios();
        entidad.setId(negocio.getId());
        entidad.setMaeSeguimientoServicioId(negocio.getMaeSeguimientoServicioId());
        entidad.setMaeSeguimientoServicioCodigo(negocio.getMaeSeguimientoServicioCodigo());
        entidad.setMaeSeguimientoServicioValor(negocio.getMaeSeguimientoServicioValor());
        entidad.setLitros(negocio.getLitros());
        entidad.setTiempoUso(negocio.getTiempoUso());
        entidad.setTiempoDuracionTratamiento(negocio.getTiempoDuracionTratamiento());
        entidad.setGasesArteriales(negocio.getGasesArteriales());
        entidad.setPresion(negocio.getPresion());
        entidad.setRampa(negocio.getRampa());
        entidad.setMaeTipoMascaraId(negocio.getMaeTipoMascaraId());
        entidad.setMaeTipoMascaraCodigo(negocio.getMaeTipoMascaraCodigo());
        entidad.setMaeTipoMascaraValor(negocio.getMaeTipoMascaraValor());
        entidad.setMaeTallaMascaraId(negocio.getMaeTallaMascaraId());
        entidad.setMaeTallaMascaraCodigo(negocio.getMaeTallaMascaraCodigo());
        entidad.setMaeTallaMascaraValor(negocio.getMaeTallaMascaraValor());
        entidad.setMaeTipoSondaId(negocio.getMaeTipoSondaId());
        entidad.setMaeTipoSondaCodigo(negocio.getMaeTipoSondaCodigo());
        entidad.setMaeTipoSondaValor(negocio.getMaeTipoSondaValor());
        entidad.setMaeTipoInsumoId(negocio.getMaeTipoInsumoId());
        entidad.setMaeTipoInsumoCodigo(negocio.getMaeTipoInsumoCodigo());
        entidad.setMaeTipoInsumoValor(negocio.getMaeTipoInsumoValor());
        entidad.setPresiones(negocio.getPresiones());
        entidad.setTipoMascaraTos(negocio.getTipoMascaraTos());
        entidad.setObservaciones(negocio.getObservaciones());
        entidad.setBorrado(negocio.getBorrado());
        entidad.setBorrado(negocio.getBorrado());

        entidad.setAuSeguimientoId(new AuSeguimientos(negocio.getAuSeguimientoId().getId()));

        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        return entidad;
    }

    public static List<AuSeguimientoServicio> casteoListaEntidadNegocio(List<AuSeguimientoServicios> listaEntidad) {
        List<AuSeguimientoServicio> listaNegocio = new ArrayList();
        listaEntidad.forEach(entidad -> {
            listaNegocio.add(castEntidadNegocio(entidad));
        });
        return listaNegocio;
    }
}
