/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.tutela;

import com.saviasaludeps.savia.ejb.servicios.atencionusuario.*;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCargaFijo;
import com.saviasaludeps.savia.dominio.tutela.TuCargaFijo;
import com.saviasaludeps.savia.ejb.entidades.AusCargaFijos;
import com.saviasaludeps.savia.ejb.entidades.TuCargaFijos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.atencionusuario.AusCargaFijoRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuCargaFijoRemoto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;


/**
 *
 * @author jperezn
 */
@Stateless
@Remote(TuCargaFijoRemoto.class)
@Local(TuCargaFijoLocal.class)
public class TuCargaFijoServicio extends GenericoServicio implements TuCargaFijoLocal, TuCargaFijoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(acf) FROM TuCargaFijos acf "
                    + "WHERE acf.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND acf.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "nombre":
                            strQuery += "AND acf.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "valor":
                            strQuery += "AND acf.valor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
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

    @Override
    public List<TuCargaFijo> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<TuCargaFijo> listResult = new ArrayList();
        try {
            String strQuery = "FROM TuCargaFijos acf "
                    + "WHERE acf.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND acf.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "nombre":
                            strQuery += "AND acf.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "valor":
                            strQuery += "AND acf.valor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "acf." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "acf.id ASC ";
            }
            List<TuCargaFijos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (TuCargaFijos per : list) {
                listResult.add(castEntidadNegocio(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
    @Override
    public HashMap<Integer, String> consultarHash() {
        HashMap<Integer, String> hashResult = new HashMap();
        String strQuery = "FROM TuCargaFijos ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<TuCargaFijos> list = query.getResultList();
            for (TuCargaFijos per : list) {
                hashResult.put(per.getId(), per.getValor());
            }
        } catch (NoResultException e) {
            hashResult = new HashMap();
        } catch (Exception e) {
            hashResult = new HashMap();
        } finally {
            cerrarEntityManager();
        }
        return hashResult;
    }
    
     public static TuCargaFijo castEntidadNegocio(TuCargaFijos ent) {
        TuCargaFijo obj = new TuCargaFijo();
        obj.setId(ent.getId());
        obj.setNombre(ent.getNombre());
        obj.setValor(ent.getValor());
        return obj;
    }

    public static TuCargaFijos castNegocioEntidad(TuCargaFijo  obj) {
        TuCargaFijos ent = new TuCargaFijos ();
        ent.setId(obj.getId());
        ent.setNombre(obj.getNombre());
        ent.setValor(obj.getValor());
        return ent;
    }

    @Override
    public TuCargaFijo consultar(int id) throws java.lang.Exception {
        TuCargaFijo objRes = null;
        try {
            TuCargaFijos cargaFijo = (TuCargaFijos) getEntityManager().find(TuCargaFijos.class, id);
            if (cargaFijo != null) {
                objRes = castEntidadNegocio(cargaFijo);
            }
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;  
    }
}
