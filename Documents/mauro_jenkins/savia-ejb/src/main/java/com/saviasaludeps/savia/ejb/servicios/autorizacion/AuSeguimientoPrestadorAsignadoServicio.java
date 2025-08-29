/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimiento;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoPrestadorAsignado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuSeguimientoPrestadorAsignados;
import com.saviasaludeps.savia.ejb.entidades.AuSeguimientos;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuSeguimientoPrestadorAsignadoRemoto;
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
@Remote(AuSeguimientoPrestadorAsignadoRemoto.class)
public class AuSeguimientoPrestadorAsignadoServicio extends GenericoServicio implements AuSeguimientoPrestadorAsignadoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuSeguimientoPrestadorAsignados p "
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
    public List<AuSeguimientoPrestadorAsignado> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuSeguimientoPrestadorAsignado> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuSeguimientoPrestadorAsignados p "
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
            List<AuSeguimientoPrestadorAsignados> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuSeguimientoPrestadorAsignados per : list) {
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
    public AuSeguimientoPrestadorAsignado consultar(int id) throws Exception {
        AuSeguimientoPrestadorAsignado objRes = null;
        try {
            objRes = castEntidadNegocio((AuSeguimientoPrestadorAsignados) getEntityManager().find(AuSeguimientoPrestadorAsignados.class, id));
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
    public int insertar(AuSeguimientoPrestadorAsignado obj) throws Exception {
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
    public void actualizar(AuSeguimientoPrestadorAsignado obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuSeguimientoPrestadorAsignados a SET ";
            strQuery += "a.estado = :estado ,";
            strQuery += "a.auSeguimientosId = :auSeguimientosId ,";
            strQuery += "a.cntPrestadorSedesId = :cntPrestadorSedesId ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("auSeguimientosId", new AuSeguimientos(obj.getAuSeguimiento().getId()));
            query.setParameter("cntPrestadorSedesId", new CntPrestadorSedes(obj.getCntPrestadorSede().getId()));

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
    
    @Override
    public AuSeguimientoPrestadorAsignado prestadorPorSeguimientoPorPrestador(int seguimiento, int prestador) throws Exception {
        AuSeguimientoPrestadorAsignado tecnologia = null;
        try {
            String strQuery = "SELECT p FROM AuSeguimientoPrestadorAsignados p "
                    + "WHERE p.auSeguimientosId.id =:seguimiento "
                    + "AND p.cntPrestadorSedesId.id=:prestador ORDER BY P.id DESC";
            AuSeguimientoPrestadorAsignados result = (AuSeguimientoPrestadorAsignados)
                    getEntityManager().createQuery(strQuery)
                    .setParameter("seguimiento", seguimiento)
                    .setParameter("prestador", prestador)
                    .setMaxResults(1)
                    .getSingleResult();
            tecnologia = castEntidadNegocio(result);
        } catch (NoResultException e) {
            tecnologia = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return tecnologia;
    }

    private static AuSeguimientoPrestadorAsignado castEntidadNegocio(AuSeguimientoPrestadorAsignados ent) {
        AuSeguimientoPrestadorAsignado negocio = new AuSeguimientoPrestadorAsignado();
        negocio.setId(ent.getId());
        negocio.setEstado(ent.getEstado());
        negocio.setBorrado(ent.getBorrado());

        negocio.setAuSeguimiento(new AuSeguimiento(ent.getAuSeguimientosId().getId()));
        negocio.setCntPrestadorSede(new CntPrestadorSede(ent.getCntPrestadorSedesId().getId()));

        negocio.setUsuarioCrea(ent.getUsuarioCrea());
        negocio.setTerminalCrea(ent.getTerminalCrea());
        negocio.setFechaHoraCrea(ent.getFechaHoraCrea());
        negocio.setUsuarioModifica(ent.getUsuarioModifica());
        negocio.setTerminalModifica(ent.getTerminalModifica());
        negocio.setFechaHoraModifica(ent.getFechaHoraModifica());
        return negocio;
    }

    private AuSeguimientoPrestadorAsignados castNegocioEntidad(AuSeguimientoPrestadorAsignado negocio) {
        AuSeguimientoPrestadorAsignados entidad = new AuSeguimientoPrestadorAsignados();
        entidad.setId(negocio.getId());

        entidad.setEstado(negocio.getEstado());
        entidad.setBorrado(negocio.getBorrado());

        entidad.setAuSeguimientosId(new AuSeguimientos(negocio.getAuSeguimiento().getId()));
        entidad.setCntPrestadorSedesId(new CntPrestadorSedes(negocio.getCntPrestadorSede().getId()));

        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        return entidad;
    }

    public static List<AuSeguimientoPrestadorAsignado> casteoListaEntidadNegocio(List<AuSeguimientoPrestadorAsignados> listaEntidad) {
        List<AuSeguimientoPrestadorAsignado> listaNegocio = new ArrayList();
        listaEntidad.forEach(entidad -> {
            listaNegocio.add(castEntidadNegocio(entidad));
        });
        return listaNegocio;
    }
}
