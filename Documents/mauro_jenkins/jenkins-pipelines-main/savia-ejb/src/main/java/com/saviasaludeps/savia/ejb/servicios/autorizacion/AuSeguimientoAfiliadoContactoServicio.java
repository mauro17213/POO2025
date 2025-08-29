/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoAfiliadoContacto;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuSeguimientoAfiliadoContactos;
import com.saviasaludeps.savia.ejb.entidades.AuSeguimientoAfiliados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuSeguimientoAfiliadoContactoRemoto;
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
@Remote(AuSeguimientoAfiliadoContactoRemoto.class)
public class AuSeguimientoAfiliadoContactoServicio extends GenericoServicio implements AuSeguimientoAfiliadoContactoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuSeguimientoAfiliadoContactos p "
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
    public List<AuSeguimientoAfiliadoContacto> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuSeguimientoAfiliadoContacto> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuSeguimientoAfiliadoContactos p "
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
            List<AuSeguimientoAfiliadoContactos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuSeguimientoAfiliadoContactos per : list) {
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
    public AuSeguimientoAfiliadoContacto consultar(int id) throws Exception {
        AuSeguimientoAfiliadoContacto objRes = null;
        try {
            objRes = castEntidadNegocio((AuSeguimientoAfiliadoContactos) getEntityManager().find(AuSeguimientoAfiliadoContactos.class, id));
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
    public int insertar(AuSeguimientoAfiliadoContacto obj) throws Exception {
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
    public void actualizar(AuSeguimientoAfiliadoContacto obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuSeguimientoAfiliadoContactos a SET ";
            strQuery += "a.borrado = :borrado ,";
            strQuery += "a.usuarioBorra = :usuarioBorra ,";
            strQuery += "a.terminalBorra = :terminalBorra ,";
            strQuery += "a.fechaHoraBorra = :fechaHoraBorra, ";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("borrado", obj.getBorrado());

            query.setParameter("usuarioBorra", obj.getUsuarioBorra());
            query.setParameter("terminalBorra", obj.getTerminalBorra());
            query.setParameter("fechaHoraBorra", obj.getFechaHoraBorra());
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

    private static AuSeguimientoAfiliadoContacto castEntidadNegocio(AuSeguimientoAfiliadoContactos ent) {
        AuSeguimientoAfiliadoContacto negocio = new AuSeguimientoAfiliadoContacto();
        negocio.setId(ent.getId());
        negocio.setNumeroContacto(ent.getNumeroContacto());
        negocio.setMaeTipoContactoId(ent.getMaeTipoContactoId());
        negocio.setMaeTipoContactoCodigo(ent.getMaeTipoContactoCodigo());
        negocio.setMaeTipoContactoValor(ent.getMaeTipoContactoValor());
        negocio.setObservacion(ent.getObservacion());
        negocio.setActivo(ent.getActivo());
        negocio.setBorrado(ent.getBorrado());
        negocio.setAuSeguimientoAfiliado(new AuSeguimientoAfiliado(ent.getAuSeguimientoAfiliadoId().getId()));

        negocio.setUsuarioCrea(ent.getUsuarioCrea());
        negocio.setTerminalCrea(ent.getTerminalCrea());
        negocio.setFechaHoraCrea(ent.getFechaHoraCrea());
        negocio.setUsuarioModifica(ent.getUsuarioModifica());
        negocio.setTerminalModifica(ent.getTerminalModifica());
        negocio.setFechaHoraModifica(ent.getFechaHoraModifica());
        negocio.setUsuarioBorra(ent.getUsuarioBorra());
        negocio.setTerminalBorra(ent.getTerminalBorra());
        negocio.setFechaHoraBorra(ent.getFechaHoraBorra());
        return negocio;
    }

    private AuSeguimientoAfiliadoContactos castNegocioEntidad(AuSeguimientoAfiliadoContacto negocio) {
        AuSeguimientoAfiliadoContactos entidad = new AuSeguimientoAfiliadoContactos();
        entidad.setId(negocio.getId());
        entidad.setNumeroContacto(negocio.getNumeroContacto());
        entidad.setMaeTipoContactoId(negocio.getMaeTipoContactoId());
        entidad.setMaeTipoContactoCodigo(negocio.getMaeTipoContactoCodigo());
        entidad.setMaeTipoContactoValor(negocio.getMaeTipoContactoValor());
        entidad.setObservacion(negocio.getObservacion());
        entidad.setActivo(negocio.getActivo());
        entidad.setBorrado(negocio.getBorrado());
        entidad.setAuSeguimientoAfiliadoId(new AuSeguimientoAfiliados(negocio.getAuSeguimientoAfiliado().getId()));

        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        entidad.setUsuarioBorra(negocio.getUsuarioBorra());
        entidad.setTerminalBorra(negocio.getTerminalBorra());
        entidad.setFechaHoraBorra(negocio.getFechaHoraBorra());
        return entidad;
    }

    public static List<AuSeguimientoAfiliadoContacto> casteoListaEntidadNegocio(List<AuSeguimientoAfiliadoContactos> listaEntidad) {
        List<AuSeguimientoAfiliadoContacto> listaNegocio = new ArrayList();
        listaEntidad.forEach(entidad -> {
            if (entidad.getBorrado() == null || !entidad.getBorrado()) {
                listaNegocio.add(castEntidadNegocio(entidad));
            }
        });
        return listaNegocio;
    }
}
