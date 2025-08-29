/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Entrega;
import com.saviasaludeps.savia.dominio.autorizacion.AuEntregaCarga;
import com.saviasaludeps.savia.dominio.autorizacion.AuEntregaCargaDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo4Entregas;
import com.saviasaludeps.savia.ejb.entidades.AuEntregaCargaDetalles;
import com.saviasaludeps.savia.ejb.entidades.AuEntregaCargas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuEntregaCargaDetalleRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author iavenegas
 */
@Stateless
@Remote(AuEntregaCargaDetalleRemoto.class)
public class AuEntregaCargaDetalleServicio extends GenericoServicio implements AuEntregaCargaDetalleRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuEntregaCargaDetalles p ";
            strQuery += "WHERE p.auEntregaCargasId.id = " + paramConsulta.getParametroConsulta3() + " ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "fila":
                            strQuery += "AND p.fila = " + e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;

                    }
                }
            }

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
    public List<AuEntregaCargaDetalle> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuEntregaCargaDetalle> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuEntregaCargaDetalles p ";
            strQuery += "WHERE p.auEntregaCargasId.id = " + paramConsulta.getParametroConsulta3() + " ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "fila":
                            strQuery += "AND p.fila = " + e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
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
            List<AuEntregaCargaDetalles> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuEntregaCargaDetalles carga : list) {
                listaResultados.add(castEntidadNegocio(carga));
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
    public AuEntregaCargaDetalle consultar(int id) throws Exception {
        AuEntregaCargaDetalle objRes = null;
        try {
            objRes = castEntidadNegocio((AuEntregaCargaDetalles) getEntityManager().find(AuEntregaCargaDetalles.class, id));
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
    public int insertar(AuEntregaCargaDetalle obj) throws Exception {
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

    public static AuEntregaCargaDetalle castEntidadNegocio(AuEntregaCargaDetalles ent) {
        AuEntregaCargaDetalle obj = new AuEntregaCargaDetalle();
        obj.setId(ent.getId());
        obj.setAuEntregaCarga(new AuEntregaCarga(ent.getAuEntregaCargasId().getId()));
        if (ent.getAuEntregaCargasId() != null) {
            obj.setAuAnexo4Entrega(new AuAnexo4Entrega(ent.getAuEntregaCargasId().getId()));
        }
        obj.setFila(ent.getFila());
        obj.setEstado(ent.getEstado());
        obj.setDetalleFallo(ent.getDetalleFallo());
        obj.setFechaHoraProceso(ent.getFechaHoraProceso());
        return obj;
    }

    public AuEntregaCargaDetalles castNegocioEntidad(AuEntregaCargaDetalle obj) {
        AuEntregaCargaDetalles ent = new AuEntregaCargaDetalles();
        ent.setId(obj.getId());
        ent.setAuEntregaCargasId(new AuEntregaCargas(obj.getAuEntregaCarga().getId()));
        if (obj.getAuAnexo4Entrega() != null) {
            ent.setAuAnexo4EntregasId(new AuAnexo4Entregas(obj.getAuAnexo4Entrega().getId()));
        }
        ent.setFila(obj.getFila());
        ent.setEstado(obj.getEstado());
        ent.setData(obj.getData());
        ent.setDetalleFallo(obj.getDetalleFallo());
        ent.setFechaHoraProceso(obj.getFechaHoraProceso());

        return ent;
    }
}
