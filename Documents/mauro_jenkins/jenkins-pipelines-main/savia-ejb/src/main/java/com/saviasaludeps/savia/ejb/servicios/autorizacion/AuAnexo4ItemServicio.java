/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Entrega;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacion;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Item;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo2Items;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo3Items;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4Entregas;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4Items;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos4;
import com.saviasaludeps.savia.ejb.entidades.AuCotizaciones;
import com.saviasaludeps.savia.ejb.entidades.CntContratoDetalles;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4ItemRemoto;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Stiven Giraldo
 */
@Stateless
@Remote(AuAnexo4ItemRemoto.class)
public class AuAnexo4ItemServicio extends GenericoServicio implements AuAnexo4ItemRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuAnexo4Items p "
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
    public List<AuAnexo4Item> consultarListaByIdAnexo4(int idAnexo4) throws Exception {
        List<AuAnexo4Item> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo4Items p "
                    + "WHERE p.id > 0";
            strQuery += " AND p.auAnexos4Id.id = " + idAnexo4 + " ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<AuAnexo4Items> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuAnexo4Items anexo4Item : list) {
                listaResultados.add(castEntidadNegocio(anexo4Item));
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
    public List<AuAnexo4Item> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo4Item> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo4Items p "
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
            /**
             * List<AuAnexo4Items> list =
             * getEntityManager().createQuery(strQuery)
             * .setFirstResult(paramConsulta.getPrimerRegistro())
             * .setMaxResults(paramConsulta.getRegistrosPagina())
             * .getResultList(); for (AuAnexo4Items anexo4Item : list){
             * listaResultados.add(castEntidadNegocio(anexo4Item)); }*
             */
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
    public AuAnexo4Item consultar(int id) throws Exception {
        AuAnexo4Item objRes = null;
        try {
            //objRes = castEntidadNegocio((AuAnexo4Items) getEntityManager().find(AuAnexo4Items.class, id));
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
    public int insertar(AuAnexo4Item obj) throws Exception {
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
    public void actualizar(AuAnexo4Item obj) throws Exception {
        try {
            //AuAnexo4Items per = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexo4Items a SET ";
            strQuery += "a.entrega = :entrega, ";
            strQuery += "a.entregaObservacion = :entregaObservacion ";
            strQuery += "WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("entrega", obj.getEntrega());
            query.setParameter("entregaObservacion", obj.getEntregaObservacion());
            query.setParameter("id", obj.getId());
            //query.setProperties(per);
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
    public AuAnexo4Item eliminar(int id) throws Exception {
        AuAnexo4Item obj = null;
        try {
            AuAnexo4Items ent = getEntityManager().find(AuAnexo4Items.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public AuAnexo4Item consultarPorIdAnexo4PorCodigo(int idAnexo, String codigo) throws Exception {
        AuAnexo4Item objRes = null;
        try {
            String strQuery = "FROM AuAnexo4Items p "
                    + "WHERE p.auAnexos4Id.id =:idAnexo "
                    + " AND p.maTecnologiaCodigo =:codigo";

            AuAnexo4Items item = (AuAnexo4Items) getEntityManager().createQuery(strQuery)
                    .setParameter("idAnexo", idAnexo)
                    .setParameter("codigo", codigo)
                    .getSingleResult();
            objRes = castEntidadNegocio(item);
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
    public AuAnexo4Item eliminarPorIdAnexo4(int idAnexo4) throws Exception {
        AuAnexo4Item obj = null;
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "DELETE AuAnexo4Items ";
            strQuery += " WHERE auAnexos4Id.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", idAnexo4);
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    /**
     * private AuAnexo4Item castEntidadNegocio(AuAnexo4Items anexo4Item){
     *
     * }*
     */
    private AuAnexo4Items castNegocioEntidad(AuAnexo4Item negocio) {
        AuAnexo4Items entidad = new AuAnexo4Items();
        entidad.setAuAnexos4Id(new AuAnexos4(negocio.getAuAnexo4Id().getId()));
        if (negocio.getAuAnexo3ItemId() != null) {
            entidad.setAuAnexo3ItemsId(new AuAnexo3Items(negocio.getAuAnexo3ItemId().getId()));
        }
        if (negocio.getAuAnexo2ItemId() != null) {
            entidad.setAuAnexo2ItemsId(new AuAnexo2Items(negocio.getAuAnexo2ItemId().getId()));
        }
        if (negocio.getAuCotizacion() != null) {
            entidad.setAuCotizacionesId(new AuCotizaciones(negocio.getAuCotizacion().getId()));
        }
        if (negocio.getCntContratoDetalle() != null) {
            entidad.setCntContratoDetallesId(new CntContratoDetalles(negocio.getCntContratoDetalle().getId()));
        }
        entidad.setTipoTecnologia(negocio.getTipoTecnologia());
        entidad.setMaTecnologiaId(negocio.getMaTecnologiaId());
        entidad.setMaTecnologiaCodigo(negocio.getMaTecnologiaCodigo());
        entidad.setMaTecnologiaValor(negocio.getMaTecnologiaValor());
        if (negocio.getMaMedicamentoId() > 0) {
            entidad.setMaMedicamentoId(negocio.getMaMedicamentoId());
            entidad.setMaMedicamentoCodigo(negocio.getMaMedicamentoCodigo());
            entidad.setMaMedicamentoValor(negocio.getMaMedicamentoValor());
        }
        entidad.setCantidadAutorizada(negocio.getCantidadAutorizada());
        entidad.setCostoServicio(negocio.getCostoServicio());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        return entidad;
    }

    public static AuAnexo4Item castEntidadNegocio(AuAnexo4Items ent) {
        AuAnexo4Item neg = new AuAnexo4Item();
        if (ent.getAuAnexos4Id() != null) {
            AuAnexo4 au4 = new AuAnexo4();
            au4.setId(ent.getAuAnexos4Id().getId());
            au4.setFechaAutorizacion(ent.getAuAnexos4Id().getFechaAutorizacion());
            if(ent.getAuAnexos4Id().getCntPrestadorSedesId() != null){
                CntPrestadorSede sede = new CntPrestadorSede();
                sede.setId(ent.getAuAnexos4Id().getCntPrestadorSedesId().getId());
                sede.setNombreSede(ent.getAuAnexos4Id().getCntPrestadorSedesId().getNombre());
                au4.setCntPrestadorSedeId(sede);
            }
            neg.setAuAnexo4Id(au4);
        }
        neg.setEntrega(ent.getEntrega());
        neg.setId(ent.getId());
        if (ent.getAuAnexo3ItemsId() != null) {
            AuAnexo3Item item3 = new AuAnexo3Item();
            item3.setId(ent.getAuAnexo3ItemsId().getId());
            item3.setEstado(ent.getAuAnexo3ItemsId().getEstado());
            neg.setAuAnexo3ItemId(item3);
        }
        if (ent.getAuAnexo2ItemsId() != null) {
            AuAnexo2Item item2 = new AuAnexo2Item();
            item2.setId(ent.getAuAnexo2ItemsId().getId());
            neg.setAuAnexo2ItemId(item2);
        }

        if (ent.getAuCotizacionesId() != null) {
            neg.setAuCotizacion(new AuCotizacion(ent.getAuCotizacionesId().getId()));
        }
        if (ent.getCntContratoDetallesId() != null) {
            CntContratoDetalle cntContratoDetalle = new CntContratoDetalle();
            cntContratoDetalle.setId(ent.getCntContratoDetallesId().getId());
            if(ent.getCntContratoDetallesId().getCntContratosId() != null){
                CntContrato contrato = new CntContrato();
                contrato.setId(ent.getCntContratoDetallesId().getCntContratosId().getId());
                contrato.setEjecucionContratoAutorizado(ent.getCntContratoDetallesId().getCntContratosId().getEjecucionContratoAutorizado());
                contrato.setEjecucionContratoPrestado(ent.getCntContratoDetallesId().getCntContratosId().getEjecucionContratoPrestado());
                contrato.setEjecucionTotalContrato(ent.getCntContratoDetallesId().getCntContratosId().getEjecucionTotalContrato());
                cntContratoDetalle.setCntContrato(contrato);
            }
            neg.setCntContratoDetalle(cntContratoDetalle);
        }
        neg.setTipoTecnologia(ent.getTipoTecnologia());
        neg.setMaTecnologiaId(ent.getMaTecnologiaId());
        neg.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        neg.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        //2023-07-11| lguerreh | agrego valores ma medicamentos
        if (ent.getMaMedicamentoId() != null) {
            neg.setMaMedicamentoId(ent.getMaMedicamentoId());
            neg.setMaMedicamentoCodigo(ent.getMaMedicamentoCodigo());
            neg.setMaMedicamentoValor(ent.getMaMedicamentoValor());
        }

        neg.setCantidadAutorizada(ent.getCantidadAutorizada());
        neg.setCostoServicio(ent.getCostoServicio());
        int cantidad = 0;
        if (ent.getAuAnexo4EntregasList() != null) {
            neg.setAuAnexo4EntregasList(new ArrayList());
            for (AuAnexo4Entregas entrega : ent.getAuAnexo4EntregasList()) {
                cantidad += entrega.getCantidadEntregada();
                //2024-03-04|lguerreroh| adicion llenado de items entregas
                AuAnexo4Entrega itemEnt = new AuAnexo4Entrega();
                itemEnt.setId(entrega.getId());
                itemEnt.setCantidadAutorizada(entrega.getCantidadAutorizada());
                itemEnt.setCantidadEntregada(entrega.getCantidadEntregada());
                itemEnt.setCantidadPendiente(entrega.getCantidadPendiente());
                neg.getAuAnexo4EntregasList().add(itemEnt);
            }
        }
        if (cantidad == ent.getCantidadAutorizada()) {
            neg.setCantidadPendiente(0);
            neg.setEntregada(true);
        } else {
            neg.setCantidadPendiente(ent.getCantidadAutorizada() - cantidad);
            neg.setEntregada(false);
        }
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        return neg;
    }

    @Override
    public AuAnexo4Item consultarUltimoPorItem(int idItem, int idTipoItem, int idAfiliado) throws Exception {
        AuAnexo4Item anexo4Item = null;
        try {
            String strQuery = "FROM AuAnexo4Items p "
                    + "WHERE p.auAnexos4Id.estado <> 2 ";
            strQuery += " AND p.maTecnologiaId = " + idItem + " ";
            strQuery += " AND p.tipoTecnologia = " + idTipoItem + " ";
            strQuery += " AND p.auAnexos4Id.asegAfiliadosId.id = " + idAfiliado + " ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<AuAnexo4Items> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if (!list.isEmpty()) {
                AuAnexo4Items items = list.get(0);
                anexo4Item = new AuAnexo4Item(items.getId());
                anexo4Item.setMaTecnologiaId(items.getMaTecnologiaId());
                anexo4Item.setMaTecnologiaCodigo(items.getMaTecnologiaCodigo());
                anexo4Item.setMaTecnologiaValor(items.getMaTecnologiaValor());
                AuAnexo4 anexo4 = new AuAnexo4(items.getAuAnexos4Id().getId());
                anexo4.setFechaAutorizacion(items.getAuAnexos4Id().getFechaAutorizacion());
                CntPrestadorSede sede = new CntPrestadorSede(items.getAuAnexos4Id().getCntPrestadorSedesId().getId());
                sede.setNombreSede(items.getAuAnexos4Id().getCntPrestadorSedesId().getNombre());
                anexo4.setCntPrestadorSedeId(sede);
                anexo4Item.setAuAnexo4Id(anexo4);
            }
        } catch (NoResultException e) {
            anexo4Item = null;
        } catch (Exception e) {
            anexo4Item = null;
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return anexo4Item;
    }
    
    @Override
    public List<AuAnexo4Item> consultarPorItems(int idItem, int idTipoItem, int idAfiliado) throws Exception {
        AuAnexo4Item anexo4Item = null;
        List<AuAnexo4Item> listItems = new ArrayList<>();
        LocalDate fechaLimite = LocalDate.now().minusYears(2);
        try {
            String strQuery = "FROM AuAnexo4Items p "
                    + "WHERE p.auAnexos4Id.estado <> 2 ";
            strQuery += " AND p.maTecnologiaId = " + idItem + " ";
            strQuery += " AND p.tipoTecnologia = " + idTipoItem + " ";
            strQuery += " AND p.auAnexos4Id.asegAfiliadosId.id = " + idAfiliado + " ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<AuAnexo4Items> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if (!list.isEmpty()) {
            for (AuAnexo4Items items : list) {
                Date fechaAutorizacion = items.getAuAnexos4Id().getFechaAutorizacion();
                Instant instant = fechaAutorizacion.toInstant();
                LocalDate fechaAutorizacionLocalDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

                if (fechaAutorizacionLocalDate.isAfter(fechaLimite)) {
                    anexo4Item = new AuAnexo4Item(items.getId());
                    anexo4Item.setMaTecnologiaId(items.getMaTecnologiaId());
                    anexo4Item.setMaTecnologiaCodigo(items.getMaTecnologiaCodigo());
                    anexo4Item.setMaTecnologiaValor(items.getMaTecnologiaValor());
                    AuAnexo4 anexo4 = new AuAnexo4(items.getAuAnexos4Id().getId());
                    anexo4.setFechaAutorizacion(items.getAuAnexos4Id().getFechaAutorizacion());
                    CntPrestadorSede sede = new CntPrestadorSede(items.getAuAnexos4Id().getCntPrestadorSedesId().getId());
                    sede.setNombreSede(items.getAuAnexos4Id().getCntPrestadorSedesId().getNombre());
                    anexo4.setCntPrestadorSedeId(sede);
                    anexo4Item.setAuAnexo4Id(anexo4);
                    listItems.add(anexo4Item);
                }
            }
        }
        } catch (NoResultException e) {
            listItems = null;
        } catch (Exception e) {
            listItems = null;
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listItems;
    }
    
    @Override
    public AuAnexo4Item consultarPorIdAnexo4PorAgrupadorMedicamento(int idAnexo, String codigoAgrupador) throws Exception {
        AuAnexo4Item objRes = null;
        try {
            String strQuery = "FROM AuAnexo4Items p "
                    + "WHERE p.auAnexos4Id.id =:idAnexo "
                    + " AND p.tipoTecnologia = " + AuAnexo3Item.TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO
                    + " AND p.maTecnologiaCodigo =:codigo";
            AuAnexo4Items item = (AuAnexo4Items) getEntityManager().createQuery(strQuery)
                    .setParameter("idAnexo", idAnexo)
                    .setParameter("codigo", codigoAgrupador)
                    .getSingleResult();
            objRes = castEntidadNegocio(item);
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
    public List<AuAnexo4Item> consultarAutorizacionByTecnologiaWithContratoDetalle(int idContratoDetalle, int idTecnologia) throws Exception {
        List<AuAnexo4Item> obj = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuAnexo4Items u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            strTitulo = agregarJoin("INNER JOIN AuAnexos4 aa ON u.auAnexos4Id = aa.id ", strTitulo);
            strQuery.append("AND u.cntContratoDetallesId = ").append(idContratoDetalle).append(" ");
            strQuery.append("AND u.maTecnologiaId = ").append(idTecnologia).append(" ");
            strQuery.append("AND aa.estado IN (0, 1, 4, 6) ");
            sql.append(strTitulo).append(strQuery);
            sql.append("ORDER BY u.id DESC");

            List<AuAnexo4Items> list = getEntityManager().createQuery(sql.toString()).getResultList();
            for (AuAnexo4Items item : list) {
                obj.add(castEntidadNegocio(item));
            }
        } catch (NoResultException e) {
            obj = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }
    
    @Override
    public List<AuAnexo4Item> consultarAutorizacionByTecnologiaWithContratoDetalleAsegAfiliado(int idContratoDetalle, int idTecnologia, int idAsegAfiliadosId) throws Exception {
        List<AuAnexo4Item> obj = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuAnexo4Items u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            strTitulo = agregarJoin("INNER JOIN AuAnexos4 aa ON u.auAnexos4Id = aa.id ", strTitulo);
            strQuery.append("AND u.cntContratoDetallesId = ").append(idContratoDetalle).append(" ");
            strQuery.append("AND u.maTecnologiaId = ").append(idTecnologia).append(" ");
            strQuery.append("AND aa.asegAfiliadosId = ").append(idAsegAfiliadosId).append(" ");
            strQuery.append("AND aa.estado IN (0, 1, 4, 6) ");
            sql.append(strTitulo).append(strQuery);
            sql.append("ORDER BY u.id DESC");

            List<AuAnexo4Items> list = getEntityManager().createQuery(sql.toString()).getResultList();
            for (AuAnexo4Items item : list) {
                obj.add(castEntidadNegocio(item));
            }
        } catch (NoResultException e) {
            obj = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }
    
    @Override
    public List<AuAnexo4Item> consultarAutorizacionByFechasWithContratoDetalle(int idContratoDetalle, int idTecnologia) throws Exception {
        List<AuAnexo4Item> obj = new ArrayList();
        try {
            String strTitulo = "SELECT DATE_FORMAT(a4i.fecha_hora_crea, '%Y-%m') AS f, COUNT(1) AS au4 FROM au_anexo4_items AS a4i ";
            StringBuilder strQuery = new StringBuilder("WHERE a4i.id > 0 ");
            StringBuilder sql = new StringBuilder();
            strTitulo = agregarJoin("INNER JOIN au_anexos4 AS aa ON aa.id = a4i.au_anexos4_id ", strTitulo);
            strTitulo = agregarJoin("INNER JOIN cnt_contrato_detalles AS cd ON cd.id = a4i.cnt_contrato_detalles_id ", strTitulo);
            strTitulo = agregarJoin("INNER JOIN cnt_contratos AS c ON c.id = cd.cnt_contratos_id ", strTitulo);
            strQuery.append("AND a4i.fecha_hora_crea BETWEEN c.fecha_inicio AND c.fecha_fin ");
            strQuery.append("AND a4i.cnt_contrato_detalles_id = ").append(idContratoDetalle).append(" ");
            strQuery.append("AND a4i.ma_tecnologia_id = ").append(idTecnologia).append(" ");
            strQuery.append("AND a4i.fecha_hora_crea >= c.fecha_inicio ");
            strQuery.append("AND aa.estado IN (0, 1, 4, 6) ");
            sql.append(strTitulo).append(strQuery);
            sql.append("GROUP BY f ");
            List<Object[]> list = getEntityManager().createNativeQuery(sql.toString()).getResultList();
            obj = list.stream()
                    .map(result -> new AuAnexo4Item((String.valueOf(result[0])),
                    ((BigInteger) result[1]).intValue()
            )).collect(Collectors.toList());

        } catch (NoResultException e) {
            obj = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }
    
    @SuppressWarnings("UnusedAssignment")
    private String agregarJoin(String join, String sql) {
        if (sql.contains(join)) {
            return sql;
        } else {
            return sql += join;
        }
    }
}
