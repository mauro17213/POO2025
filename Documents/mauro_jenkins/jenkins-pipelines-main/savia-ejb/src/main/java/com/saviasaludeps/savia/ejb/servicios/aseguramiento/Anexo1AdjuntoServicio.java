/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.aseguramiento;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAnexo1;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAnexo1Adjunto;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAnexo1Adjuntos;
import com.saviasaludeps.savia.ejb.entidades.AsegAnexos1;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.aseguramiento.Anexo1AdjuntoRemoto;

/**
 *
 * @author jose perez hernandez
 */
@Stateless
@Remote(Anexo1AdjuntoRemoto.class)
public class Anexo1AdjuntoServicio extends GenericoServicio implements Anexo1AdjuntoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AsegAnexo1Adjuntos p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public List<AsegAnexo1Adjunto> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AsegAnexo1Adjunto> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM AsegAnexo1Adjuntos p "
                    + "WHERE p.id > 0 ";
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
            List<AsegAnexo1Adjuntos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (AsegAnexo1Adjuntos per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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
    public AsegAnexo1Adjunto consultar(int id) throws Exception {
        AsegAnexo1Adjunto objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AsegAnexo1Adjuntos) getEntityManager().find(AsegAnexo1Adjuntos.class, id));
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
    public int insertar(AsegAnexo1Adjunto obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidadLargo(obj)).getId();
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
    public void actualizar(AsegAnexo1Adjunto obj) throws Exception {
        try {
            getEntityManager().merge(castNegocioEntidadLargo(obj));
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AsegAnexo1Adjunto eliminar(int id) throws Exception {
        AsegAnexo1Adjunto obj = null;
        try {
            AsegAnexo1Adjuntos ent = getEntityManager().find(AsegAnexo1Adjuntos.class, id);
            if (ent != null) {
                obj = castEntidadNegocioLargo(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public List<AsegAnexo1Adjunto> consultarTodos() throws Exception {
        List<AsegAnexo1Adjunto> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegAnexo1Adjuntos p "
                    + "ORDER BY p.fechaHoraCrea DESC";
            List<AsegAnexo1Adjuntos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegAnexo1Adjuntos per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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

    public static AsegAnexo1Adjunto castEntidadNegocioLargo(AsegAnexo1Adjuntos per) {
        AsegAnexo1Adjunto obj = new AsegAnexo1Adjunto();
        obj.setId(per.getId());
        obj.setArchivo(per.getArchivo());
        obj.setRuta(per.getRuta());
        //objetos
        if (per.getAsegAnexos1Id() != null) {
            obj.setAsegAnexo1Id(new AsegAnexo1(per.getAsegAnexos1Id().getId()));
        }
        // auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        //objetos

        return obj;
    }

    public static AsegAnexo1Adjuntos castNegocioEntidadLargo(AsegAnexo1Adjunto obj) {
        AsegAnexo1Adjuntos per = new AsegAnexo1Adjuntos();
        per.setId(obj.getId());
        per.setArchivo(obj.getArchivo());
        per.setRuta(obj.getRuta());
        //objetos
        if (obj.getAsegAnexo1Id() != null) {
            per.setAsegAnexos1Id(new AsegAnexos1(obj.getAsegAnexo1Id().getId()));
        }
        // auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        return per;
    }

}
