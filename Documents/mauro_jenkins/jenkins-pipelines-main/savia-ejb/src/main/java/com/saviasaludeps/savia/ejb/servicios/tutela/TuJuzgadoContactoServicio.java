/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.tutela;

import com.saviasaludeps.savia.dominio.tutela.TuJuzgado;
import com.saviasaludeps.savia.dominio.tutela.TuJuzgadoContacto;
import com.saviasaludeps.savia.ejb.entidades.TuJuzgadoContactos;
import com.saviasaludeps.savia.ejb.entidades.TuJuzgados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.tutela.TuJuzgadoContactoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jramirer
 */
@Stateless
@Remote(TuJuzgadoContactoRemoto.class)
public class TuJuzgadoContactoServicio extends GenericoServicio implements TuJuzgadoContactoRemoto {

    @Override
    public TuJuzgadoContacto consultar(int id) throws Exception {
        TuJuzgadoContacto objRes = null;
        try {
            objRes = castEntidadNegocio((TuJuzgadoContactos) getEntityManager().find(TuJuzgadoContactos.class, id));
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
    public int insertar(TuJuzgadoContacto obj) throws Exception {
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
    public void actualizar(TuJuzgadoContacto obj) throws Exception {
        try {
            String hql = "UPDATE TuJuzgadoContactos SET"
                    + " maeTipoContactoId = :maeTipoContactoId,"
                    + " maeTipoContactoCodigo = :maeTipoContactoCodigo,"
                    + " maeTipoContactoValor = :maeTipoContactoValor,"
                    + " contacto = :contacto,"
                    + " activo = :activo,"
                    + " observacion = :observacion,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("maeTipoContactoId", obj.getMaeTipoContactoId());
            query.setParameter("maeTipoContactoCodigo", obj.getMaeTipoContactoCodigo());
            query.setParameter("maeTipoContactoValor", obj.getMaeTipoContactoValor());
            query.setParameter("contacto", obj.getContacto());
            query.setParameter("activo", obj.isActivo());
            query.setParameter("observacion", obj.getObservacion());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public TuJuzgadoContacto eliminar(int id) throws Exception {
        TuJuzgadoContacto obj = null;
        try {
            TuJuzgadoContactos ent = getEntityManager().find(TuJuzgadoContactos.class, id);
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
    public List<TuJuzgadoContacto> consultarListaContactos(int idTuJuzgadosId) throws java.lang.Exception {
        List<TuJuzgadoContacto> listResult = new ArrayList();
        try {
            String strQuery = "FROM TuJuzgadoContactos p "
                    + "WHERE p.tuJuzgadosId.id = " + idTuJuzgadosId + " "
                    + "ORDER BY p.id ASC";

            List<TuJuzgadoContactos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            int posicion = 0;
            for (TuJuzgadoContactos cont : list) {
                TuJuzgadoContacto contacto = castEntidadNegocio(cont);
                contacto.setPosicion(posicion++);
                listResult.add(contacto);
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

    public static TuJuzgadoContacto castEntidadNegocio(TuJuzgadoContactos per) {
        TuJuzgadoContacto obj = new TuJuzgadoContacto();
        obj.setId(per.getId());
        obj.setMaeTipoContactoId(per.getMaeTipoContactoId());
        obj.setMaeTipoContactoCodigo(per.getMaeTipoContactoCodigo());
        obj.setMaeTipoContactoValor(per.getMaeTipoContactoValor());
        obj.setContacto(per.getContacto());
        obj.setObservacion(per.getObservacion());
        obj.setActivo(per.getActivo());
        if (per.getTuJuzgadosId() != null) {
            obj.setTuJuzgadosId(new TuJuzgado(per.getTuJuzgadosId().getId()));
        }
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static TuJuzgadoContactos castNegocioEntidad(TuJuzgadoContacto obj) {
        TuJuzgadoContactos per = new TuJuzgadoContactos();
        per.setId(obj.getId());
        per.setMaeTipoContactoId(obj.getMaeTipoContactoId());
        per.setMaeTipoContactoCodigo(obj.getMaeTipoContactoCodigo());
        per.setMaeTipoContactoValor(obj.getMaeTipoContactoValor());
        per.setContacto(obj.getContacto());
        per.setObservacion(obj.getObservacion());
        per.setActivo(obj.isActivo());
        if (obj.getTuJuzgadosId() != null) {
            per.setTuJuzgadosId(new TuJuzgados(obj.getTuJuzgadosId().getId()));
        }
        //Auditoría
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }
}
