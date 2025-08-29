/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuEntregaCarga;
import com.saviasaludeps.savia.dominio.autorizacion.AuEntregaCargaDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.AuEntregaCargas;
import com.saviasaludeps.savia.negocio.autorizacion.AuEntregaCargaRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author iavenegas
 */
@Stateless
@Remote(AuEntregaCargaRemoto.class)
public class AuEntregaCargaServicio extends GenericoServicio implements AuEntregaCargaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(p) FROM AuEntregaCargas p ";
            String strQuery = "";

            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "WHERE p.gnEmpresasId = " + paramConsulta.getEmpresaId() + " ";
            } else {
                strQuery += "WHERE p.id > 0 ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "nombre":
                            strQuery += "AND p.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "tipoTecnologia":
                            strQuery += "AND p.tipoTecnologia = " + e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery = strTitulo + strQuery;

            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

    @Override
    public List<AuEntregaCarga> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuEntregaCarga> listaResultados = new ArrayList();
        try {
            String strTitulo = "FROM AuEntregaCargas p ";
            String strQuery = "";
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "WHERE p.gnEmpresasId = " + paramConsulta.getEmpresaId() + " ";
            } else {
                strQuery += "WHERE p.id > 0 ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "nombre":
                            strQuery += "AND p.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "tipoTecnologia":
                            strQuery += "AND p.tipoTecnologia = " + e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea LIKE '%" + e.getValue() + "%' ";
                            break;

                    }
                }
            }
            strQuery = strTitulo + strQuery;

            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<AuEntregaCargas> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuEntregaCargas carga : list) {
                listaResultados.add(castEntidadNegocioLista(carga));
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
    public List<AuEntregaCarga> consultarArchivoNombre(String nombre) throws Exception {
        List<AuEntregaCarga> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuEntregaCargas p WHERE p.nombre=:nombre AND p.estado=" + AuEntregaCarga.ESTADO_EN_PROCESO;

            List<AuEntregaCargas> list = getEntityManager().createQuery(strQuery)
                    .setParameter("nombre", nombre)
                    .getResultList();
            for (AuEntregaCargas carga : list) {
                listaResultados.add(castEntidadNegocioLista(carga));
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
    public AuEntregaCarga consultar(int id) throws Exception {
        AuEntregaCarga objRes = null;
        try {
            objRes = castEntidadNegocio((AuEntregaCargas) getEntityManager().find(AuEntregaCargas.class, id));
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
    public int insertar(AuEntregaCarga obj) throws Exception {
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
    public void actualizar(AuEntregaCarga entrega) throws Exception {
        try {
            String strQuery = "UPDATE AuEntregaCargas a SET"
                    + " a.estado=:estado, "
                    + " a.exitosos=:exitosos, "
                    + " a.fallidos=:fallidos, "
                    + " a.fechaHoraFin=:fechaHoraFin "
                    + " WHERE a.id =:id";
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("estado", entrega.getEstado());
            query.setParameter("exitosos", entrega.getExitosos());
            query.setParameter("fallidos", entrega.getFallidos());
            query.setParameter("fechaHoraFin", entrega.getFechaHoraFin());
            query.setParameter("id", entrega.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    public AuEntregaCarga castEntidadNegocioLista(AuEntregaCargas ent) {
        AuEntregaCarga obj = new AuEntregaCarga();
        obj.setId(ent.getId());
        obj.setNombre(ent.getNombre());
        obj.setRuta(ent.getRuta());
        obj.setArchivo(ent.getArchivo());
        obj.setEstado(ent.getEstado());
        obj.setRegistros(ent.getRegistros());
        obj.setExitosos(ent.getExitosos());
        obj.setFallidos(ent.getFallidos());
        obj.setDetalle(ent.getDetalle());
        //2023-12-19 jyperez nuevo campo
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        if (ent.getGnEmpresasId() != null) {
            obj.setEmpresa(ent.getGnEmpresasId());
        }
        obj.setFechaHoraInicio(ent.getFechaHoraInicio());
        obj.setFechaHoraFin(ent.getFechaHoraFin());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());

        return obj;
    }

    public AuEntregaCarga castEntidadNegocio(AuEntregaCargas ent) {
        AuEntregaCarga obj = new AuEntregaCarga();
        obj.setId(ent.getId());
        obj.setNombre(ent.getNombre());
        obj.setRuta(ent.getRuta());
        obj.setArchivo(ent.getArchivo());
        obj.setEstado(ent.getEstado());
        obj.setRegistros(ent.getRegistros());
        obj.setExitosos(ent.getExitosos());
        obj.setFallidos(ent.getFallidos());
        obj.setDetalle(ent.getDetalle());
        obj.setFechaHoraInicio(ent.getFechaHoraInicio());
        obj.setFechaHoraFin(ent.getFechaHoraFin());
        //2023-12-19 jyperez nuevo campo
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        if (ent.getGnEmpresasId() != null) {
            obj.setEmpresa(ent.getGnEmpresasId());
        }
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        //sucessos
        if (ent.getAuEntregaCargaDetallesList() != null) {
            List<AuEntregaCargaDetalle> detalles = new ArrayList();
            ent.getAuEntregaCargaDetallesList().forEach(detalle -> {
                detalles.add(AuEntregaCargaDetalleServicio.castEntidadNegocio(detalle));
            });
            obj.setAuEntregaCargaDetalleList(detalles);
        }
        return obj;
    }

    public AuEntregaCargas castNegocioEntidad(AuEntregaCarga obj) {
        AuEntregaCargas ent = new AuEntregaCargas();
        ent.setId(obj.getId());
        ent.setNombre(obj.getNombre());
        ent.setRuta(obj.getRuta());
        ent.setArchivo(obj.getArchivo());
        ent.setEstado(obj.getEstado());
        ent.setRegistros(obj.getRegistros());
        ent.setExitosos(obj.getExitosos());
        ent.setFallidos(obj.getFallidos());
        ent.setDetalle(obj.getDetalle());
        ent.setFechaHoraInicio(obj.getFechaHoraInicio());
        ent.setFechaHoraFin(obj.getFechaHoraFin());
        //2023-12-19 jyperez nuevos campos
        ent.setTipoTecnologia(obj.getTipoTecnologia());
        if (obj.getEmpresa() != null) {
            ent.setGnEmpresasId(obj.getEmpresa());
        }
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());

        return ent;
    }

    @Override
    public AuEntregaCarga consultarSiguienteCarga(int estado) throws Exception {
        AuEntregaCarga result = null;
        try {
            String strQuery = "FROM AuEntregaCargas c WHERE c.estado = "+estado+" ORDER BY c.id DESC";
            List<AuEntregaCargas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuEntregaCargas entregaCarga : list) {
                 result = castEntidadNegocio(entregaCarga);
            }
        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    @Override
    public void actualizarEstado(AuEntregaCarga obj) throws Exception {
        try {
            String strQuery = "UPDATE AuEntregaCargas a SET"
                    + " a.estado=:estado "
                    + " WHERE a.id =:id";
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
}
