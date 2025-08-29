/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.atencionusuario;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCargaFijo;
import com.saviasaludeps.savia.ejb.entidades.AusCargaFijos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.atencionusuario.AusCargaFijoRemoto;
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
@Remote(AusCargaFijoRemoto.class)
@Local(AusCargaFijoLocal.class)
public class AusCargaFijoServicio extends GenericoServicio implements AusCargaFijoLocal, AusCargaFijoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(acf) FROM AusCargaFijos acf "
                    + "WHERE 1 = 1 ";
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
    public List<AusCargaFijo> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AusCargaFijo> listResult = new ArrayList();
        try {
            String strQuery = "FROM AusCargaFijos acf "
                    + "WHERE 1 = 1 ";
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
            List<AusCargaFijos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AusCargaFijos per : list) {
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
        String strQuery = "FROM AusCargaFijos ";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<AusCargaFijos> list = query.getResultList();
            for (AusCargaFijos per : list) {
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
    
     public static AusCargaFijo castEntidadNegocio(AusCargaFijos ent) {
        AusCargaFijo obj = new AusCargaFijo();
        obj.setId(ent.getId());
        obj.setNombre(ent.getNombre());
        obj.setValor(ent.getValor());
        return obj;
    }

    public static AusCargaFijos castNegocioEntidad(AusCargaFijo  obj) {
        AusCargaFijos ent = new AusCargaFijos ();
        ent.setId(obj.getId());
        ent.setNombre(obj.getNombre());
        ent.setValor(obj.getValor());
        return ent;
    }

    @Override
    public AusCargaFijo consultar(int id) throws java.lang.Exception {
        AusCargaFijo objRes = null;
        try {
            AusCargaFijos cargaFijo = (AusCargaFijos) getEntityManager().find(AusCargaFijos.class, id);
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
