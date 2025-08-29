/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCargaCierre;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCargaCierreSuceso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AucCargaCierreSucesos;
import com.saviasaludeps.savia.ejb.entidades.AucCargaCierres;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucCargaCierreSucesoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author ibohorquez
 */
@Stateless
@Remote(AucCargaCierreSucesoRemoto.class)
public class AucCargaCierreSucesoServicio extends GenericoServicio implements AucCargaCierreSucesoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM AucCargaCierreSucesos c ";
            strQuery += "WHERE c.aucCargaCierresId.id = " + paramConsulta.getParametroConsulta3() + " ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery += "AND c.tipo = " + e.getValue() + " ";
                            break;
                        case "descripcion":
                            strQuery += "AND c.descripcion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "fila":
                            strQuery += "AND c.fila = " + e.getValue() + " ";
                            break;
                        case "columna":
                            strQuery += "AND c.columna = " + e.getValue() + " ";
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
    public List<AucCargaCierreSuceso> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AucCargaCierreSuceso> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AucCargaCierreSucesos c ";
            strQuery += "WHERE c.aucCargaCierresId.id = " + paramConsulta.getParametroConsulta3() + " ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery += "AND c.tipo = " + e.getValue() + " ";
                            break;
                        case "descripcion":
                            strQuery += "AND c.descripcion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "fila":
                            strQuery += "AND c.fila = " + e.getValue() + " ";
                            break;
                        case "columna":
                            strQuery += "AND c.columna = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "c." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "c.id ASC";
            }
            List<AucCargaCierreSucesos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AucCargaCierreSucesos entidad : list) {
                listaResultados.add(castEntidadNegocio(entidad));
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
    public int insertar(AucCargaCierreSuceso obj) throws Exception {
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
    public List<AucCargaCierreSuceso> consultarListaPorIdCarga(int id) throws Exception {
        List<AucCargaCierreSuceso> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AucCargaCierreSucesos p "
                    + "WHERE p.aucCargaCierresId.id = " + id;

            List<AucCargaCierreSucesos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AucCargaCierreSucesos fallo : list) {
                listaResultados.add(castEntidadNegocio(fallo));
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
    

    private AucCargaCierreSuceso castEntidadNegocio(AucCargaCierreSucesos entidad) {
        AucCargaCierreSuceso negocio = new AucCargaCierreSuceso();
        negocio.setId(entidad.getId());
        negocio.setTipo(entidad.getTipo());
        negocio.setDescripcion(entidad.getDescripcion());
        negocio.setFila(entidad.getFila());
        negocio.setColumna(entidad.getColumna());
        negocio.setAucCargaCierreId(new AucCargaCierre(entidad.getId()));
        return negocio;
    }

    private AucCargaCierreSucesos castNegocioEntidad(AucCargaCierreSuceso negocio) {
        AucCargaCierreSucesos entidad = new AucCargaCierreSucesos();
        entidad.setTipo(negocio.getTipo());
        entidad.setDescripcion(negocio.getDescripcion());
        entidad.setFila(negocio.getFila());
        entidad.setColumna(negocio.getColumna());
        entidad.setAucCargaCierresId(new AucCargaCierres(negocio.getAucCargaCierreId().getId()));
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

}
