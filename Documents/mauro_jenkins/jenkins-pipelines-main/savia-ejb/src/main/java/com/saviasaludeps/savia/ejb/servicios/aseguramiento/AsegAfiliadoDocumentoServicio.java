/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.aseguramiento;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoDocumento;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliadoDocumentos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.aseguramiento.AsegAfiliadoDocumentoRemoto;
import java.text.SimpleDateFormat;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(AsegAfiliadoDocumentoRemoto.class)
public class AsegAfiliadoDocumentoServicio extends GenericoServicio implements AsegAfiliadoDocumentoRemoto {

    @Override
    public AsegAfiliadoDocumento consultar(int id) throws Exception {
        AsegAfiliadoDocumento objRes = null;
        try {
            objRes = castEntidadNegocio((AsegAfiliadoDocumentos) getEntityManager().find(AsegAfiliadoDocumentos.class, id));
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
    public int insertar(AsegAfiliadoDocumento obj) throws Exception {
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
    public void actualizar(AsegAfiliadoDocumento obj) throws Exception {
        try {
            getEntityManager().merge(castNegocioEntidad(obj));
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AsegAfiliadoDocumento eliminar(int id) throws Exception {
        AsegAfiliadoDocumento obj = null;
        try {
            AsegAfiliadoDocumentos ent = getEntityManager().find(AsegAfiliadoDocumentos.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
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
    public List<AsegAfiliadoDocumento> consultarTodos() throws Exception {
        List<AsegAfiliadoDocumento> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegAfiliadoDocumentos p "
                    + "ORDER BY p.fechaHoraCrea DESC";
            List<AsegAfiliadoDocumentos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegAfiliadoDocumentos per : list) {
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

    public static AsegAfiliadoDocumento castEntidadNegocio(AsegAfiliadoDocumentos per) {
        AsegAfiliadoDocumento obj = new AsegAfiliadoDocumento();
        obj.setId(per.getId());
        obj.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setNumeroDocumento(per.getNumeroDocumento());
        obj.setFechaExpedicion(per.getFechaExpedicion());
        if (per.getAsegAfiliadosId() != null) {
            obj.setAsegAfiliado(new AsegAfiliado(per.getAsegAfiliadosId().getId()));
        }
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        return obj;
    }

    public static AsegAfiliadoDocumentos castNegocioEntidad(AsegAfiliadoDocumento obj) {
        AsegAfiliadoDocumentos per = new AsegAfiliadoDocumentos();
        per.setId(obj.getId());
        per.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
        per.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
        per.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
        per.setNumeroDocumento(obj.getNumeroDocumento());
        per.setFechaExpedicion(obj.getFechaExpedicion());
        if (obj.getAsegAfiliado() != null) {
            per.setAsegAfiliadosId(new AsegAfiliados(obj.getAsegAfiliado().getId()));
        }
        //auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        return per;
    }

    @Override
    public List<AsegAfiliadoDocumento> consultarPorAfiliado(int id) throws Exception {
        List<AsegAfiliadoDocumento> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegAfiliadoDocumentos p "
                    + " WHERE p.asegAfiliadosId.id = " + id
                    + "ORDER BY p.fechaHoraCrea DESC";
            List<AsegAfiliadoDocumentos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegAfiliadoDocumentos per : list) {
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
    public String obtenerHistoricoPorAfiliado(int id) throws Exception {
        String mensaje = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //List<AsegAfiliadoDocumento> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegAfiliadoDocumentos p "
                    + " WHERE p.asegAfiliadosId.id = " + id
                    + "ORDER BY p.fechaHoraCrea DESC";
            List<AsegAfiliadoDocumentos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegAfiliadoDocumentos per : list) {
                mensaje = mensaje + per.getMaeTipoDocumentoCodigo() + " " + per.getNumeroDocumento() + " " + sdf.format(per.getFechaExpedicion()) +" \n";
            }
        } catch (NoResultException e) {
            //listResult = new ArrayList();
            mensaje = "";
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return mensaje;
    }

}
