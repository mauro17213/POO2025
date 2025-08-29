/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Entrega;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Item;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4Entregas;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4Items;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos4;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4EntregaRemoto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Stiven Giraldo
 */
@Stateless
@Remote(AuAnexo4EntregaRemoto.class)
public class AuAnexo4EntregaServicio extends GenericoServicio implements AuAnexo4EntregaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuAnexo4Entregas p "
                    + "WHERE p.id > 0";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
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
    public List<AuAnexo4Entrega> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo4Entrega> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo4Entregas p "
                    + "WHERE p.id > 0";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<AuAnexo4Entregas> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexo4Entregas anexo4Entrega : list) {
                listaResultados.add(castEntidadNegocio(anexo4Entrega));
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
    public AuAnexo4Entrega consultar(int id) throws Exception {
        AuAnexo4Entrega objRes = null;
        try {
            objRes = castEntidadNegocio((AuAnexo4Entregas) getEntityManager().find(AuAnexo4Entregas.class, id));
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
    public int insertar(AuAnexo4Entrega obj) throws Exception {
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
    public void actualizar(AuAnexo4Entrega obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexo4Entregas a SET ";
            strQuery += "a.tipoEntrega = :tipoEntrega, ";
            strQuery += "a.anulada = :anulada, ";
            strQuery += "a.anulaObservacion = :anulaObservacion, ";
            strQuery += "a.cantidadEntregada = :cantidadEntregada, ";
            strQuery += "a.cantidadPendiente = :cantidadPendiente, ";
            strQuery += "a.usuarioModifica = :usuarioModifica, ";
            strQuery += "a.terminalModifica = :terminalModifica, ";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("tipoEntrega", obj.getTipoEntrega());
            query.setParameter("anulada", obj.isAnulada());
            query.setParameter("anulaObservacion", obj.getAnulaObservacion());
            query.setParameter("cantidadEntregada", obj.getCantidadEntregada());
            query.setParameter("cantidadPendiente", obj.getCantidadPendiente());
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
    public AuAnexo4Entrega eliminar(int id) throws Exception {
        AuAnexo4Entrega obj = null;
        try {
            AuAnexo4Entregas ent = getEntityManager().find(AuAnexo4Entregas.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            Exception(ELIMINAR, e);
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    private AuAnexo4Entrega castEntidadNegocio(AuAnexo4Entregas entidad) {
        AuAnexo4Entrega negocio = new AuAnexo4Entrega();
        negocio.setId(entidad.getId());
        negocio.setAuAnexo4Id(new AuAnexo4(entidad.getAuAnexos4Id().getId()));
        if (entidad.getAuAnexo4ItemsId() != null) {
            negocio.setAuAnexo4ItemId(new AuAnexo4Item(entidad.getAuAnexo4ItemsId().getId()));
            negocio.getAuAnexo4ItemId().setMaTecnologiaCodigo(entidad.getAuAnexo4ItemsId().getMaTecnologiaCodigo());
            negocio.getAuAnexo4ItemId().setTipoTecnologia(entidad.getAuAnexo4ItemsId().getTipoTecnologia());
        }
        negocio.setReclamaAfiliado(entidad.getReclamaAfiliado());
        negocio.setFechaHoraEntrega(entidad.getFechaHoraEntrega());
        negocio.setTipoEntrega(entidad.getTipoEntrega());
        negocio.setCantidadAutorizada(entidad.getCantidadAutorizada());
        negocio.setCantidadEntregada(entidad.getCantidadEntregada());
        if (entidad.getCantidadPendiente() != null) {
            negocio.setCantidadPendiente(entidad.getCantidadPendiente());
        }
        negocio.setNombreReclama(entidad.getNombreReclama());
        if (entidad.getTelefonoReclama() != null) {
            negocio.setTelefonoReclama(entidad.getTelefonoReclama());
        }
        if (entidad.getCelularReclama() != null) {
            negocio.setCelularReclama(entidad.getCelularReclama());
        }
        if (entidad.getMaeCausaNoEntregaId() != null) {
            negocio.setMaeCausaNoEntregaId(entidad.getMaeCausaNoEntregaId());
            negocio.setMaeCausaNoEntregaCodigo(entidad.getMaeCausaNoEntregaCodigo());
            negocio.setMaeCausaNoEntregaValor(entidad.getMaeCausaNoEntregaValor());
        }
        negocio.setAnulada(entidad.getAnulada());
        negocio.setAnulaObservacion(entidad.getAnulaObservacion());
        negocio.setNoPrestadoObservacion(entidad.getNoPrestadoObservacion());
        negocio.setFuenteOrigen(entidad.getFuenteOrigen());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        if (entidad.getUsuarioModifica() != null) {
            negocio.setUsuarioModifica(entidad.getUsuarioModifica());
            negocio.setTerminalModifica(entidad.getTerminalModifica());
            negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        }
        return negocio;
    }

    private AuAnexo4Entregas castNegocioEntidad(AuAnexo4Entrega negocio) {
        AuAnexo4Entregas entidad = new AuAnexo4Entregas();
        entidad.setAuAnexos4Id(new AuAnexos4(negocio.getAuAnexo4Id().getId()));
        if (negocio.getAuAnexo4ItemId() != null) {
            entidad.setAuAnexo4ItemsId(new AuAnexo4Items(negocio.getAuAnexo4ItemId().getId()));
        }
        entidad.setReclamaAfiliado(negocio.isReclamaAfiliado());
        entidad.setFechaHoraEntrega(negocio.getFechaHoraEntrega());
        entidad.setTipoEntrega(negocio.getTipoEntrega());
        entidad.setCantidadAutorizada(negocio.getCantidadAutorizada());
        entidad.setCantidadEntregada(negocio.getCantidadEntregada());
        if (negocio.getCantidadPendiente() != null) {
            entidad.setCantidadPendiente(negocio.getCantidadPendiente());
        }
        if (negocio.getNombreReclama() != null) {
            entidad.setNombreReclama(negocio.getNombreReclama());
        }
        if (negocio.getTelefonoReclama() != null) {
            entidad.setTelefonoReclama(negocio.getTelefonoReclama().trim().length() > 16 ? negocio.getTelefonoReclama().trim().substring(0, 16) : negocio.getTelefonoReclama().trim());
        }
        if (negocio.getCelularReclama() != null) {
            entidad.setCelularReclama(negocio.getCelularReclama());
        }
        if (negocio.getMaeCausaNoEntregaId() != null) {
            entidad.setMaeCausaNoEntregaId(negocio.getMaeCausaNoEntregaId());
            entidad.setMaeCausaNoEntregaCodigo(negocio.getMaeCausaNoEntregaCodigo());
            entidad.setMaeCausaNoEntregaValor(negocio.getMaeCausaNoEntregaValor());
        }
        entidad.setAnulada(negocio.isAnulada());
        entidad.setAnulaObservacion(negocio.getAnulaObservacion());
        entidad.setNoPrestadoObservacion(negocio.getNoPrestadoObservacion());
        entidad.setFuenteOrigen(negocio.getFuenteOrigen());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

    @Override
    public List<AuAnexo4Entrega> consultarPorIdItemAnexo4(int id) throws Exception {
        List<AuAnexo4Entrega> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo4Entregas p "
                    + "WHERE p.auAnexo4ItemsId = " + id + " ORDER BY p.id DESC";
            List<AuAnexo4Entregas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuAnexo4Entregas anexo4Entrega : list) {
                listaResultados.add(castEntidadNegocio(anexo4Entrega));
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
    public AuAnexo4Entrega consultarPorIdItemAnexo4NoAnulado(int id) throws Exception {
        AuAnexo4Entrega ObjectResult = new AuAnexo4Entrega();
        String sql = "FROM AuAnexo4Entregas p "
                + "WHERE p.auAnexo4ItemsId.id = :id "
                + "AND p.anulada = 0 "
                + "ORDER BY p.id DESC";
        try {
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("id", id);
            List<AuAnexo4Entregas> list = query.setMaxResults(1).getResultList();
            for (AuAnexo4Entregas item : list) {
                ObjectResult = castEntidadNegocio(item);
            }
        } catch (NoResultException e) {
            ObjectResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return ObjectResult;
    }
   
    @Override
    public List<AuAnexo4Entrega> consultarListaPorIdAnexo4(int id) throws Exception {
        List<AuAnexo4Entrega> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo4Entregas p "
                    + "WHERE p.auAnexos4Id = " + id + " ORDER BY p.id DESC";
            List<AuAnexo4Entregas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuAnexo4Entregas anexo4Entrega : list) {
                listaResultados.add(castEntidadNegocio(anexo4Entrega));
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
    public void entregarTodo(List<AuAnexo4> listaAnexos) throws Exception {
        try {
            for (AuAnexo4 anexo : listaAnexos) {
                for (AuAnexo4Item item : anexo.getAuAnexo4ItemsList()) {
                    AuAnexo4Entrega entrega = new AuAnexo4Entrega();
                    entrega.setAuAnexo4Id(anexo);
                    entrega.setAuAnexo4ItemId(item);
                    entrega.setCantidadAutorizada(item.getCantidadAutorizada());
                    entrega.setCantidadEntregada(item.getCantidadAutorizada());
                    entrega.setCantidadPendiente(0);
                    entrega.setReclamaAfiliado(true);
                    entrega.setFuenteOrigen(anexo.getOrigenEntrega());
                    entrega.setNombreReclama(anexo.getAsegAfiliadoId().getNombreCompleto() != null ? anexo.getAsegAfiliadoId().getNombreCompleto() : "");
                    entrega.setCelularReclama(anexo.getAsegAfiliadoId().getTelefonoMovil() != null ? anexo.getAsegAfiliadoId().getTelefonoMovil() : "");
                    entrega.setFechaHoraEntrega(item.getFechaPrestacion());
                    entrega.setTipoEntrega(AuAnexo4Entrega.TIPO_TOTAL);
                    entrega.setUsuarioCrea("Sistema");
                    entrega.setTerminalCrea("127.0.0.1");
                    entrega.setFechaHoraCrea(new Date());
                    insertar(entrega);
                }

            }
        } catch (Exception e) {
        }
    }

    @Override
    public int consultarCantidadEstadoAnuladaloporAnexo4Item(int idAnexo4Item) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(m) FROM AuAnexo4Entregas m "
                    + "WHERE 1 = 1 "
                    + "AND m.anulada = 1"
                    + "AND m.auAnexo4ItemsId = '" + idAnexo4Item +"' ";

            cant = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (NumberFormatException e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

}
