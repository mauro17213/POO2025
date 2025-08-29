/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContactoConsulta;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucAfiliado;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucAfiliadoContacto;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.AucAfiliadoContactos;
import com.saviasaludeps.savia.ejb.entidades.AucAfiliados;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucAfiliadoContactoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author sgiraldov
 */
@Stateless
@Remote(AucAfiliadoContactoRemoto.class)
public class AucAfiliadoContactoServicio extends GenericoServicio implements AucAfiliadoContactoRemoto {

    @Override
    public AucAfiliadoContacto consultar(int id) throws Exception {
        AucAfiliadoContacto objRes = null;
        try {
            objRes = castEntidadNegocio((AucAfiliadoContactos) getEntityManager().find(AucAfiliadoContactos.class, id));
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
    public int insertar(AucAfiliadoContacto obj) throws Exception {
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
    public void actualizar(AucAfiliadoContacto obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucAfiliadoContactos a SET ";
            strQuery += "a.aucAfiliadosId.id = :aucAfiliadosId ,";
            strQuery += "a.numeroContacto = :numeroContacto ,";
            strQuery += "a.observacion = :observacion ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("aucAfiliadosId", obj.getAucAfiliadoId().getId());
            query.setParameter("numeroContacto", obj.getNumeroContacto());
            query.setParameter("observacion", obj.getObservacion());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
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
    public AucAfiliadoContacto eliminar(int id) throws Exception {
        AucAfiliadoContacto obj = null;
        try {
            AucAfiliadoContactos ent = getEntityManager().find(AucAfiliadoContactos.class, id);
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

    @Override
    public List<AucAfiliadoContacto> consultarLista(int idAucAfiliado) throws Exception {
        List<AucAfiliadoContacto> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t FROM AucAfiliadoContactos t "
                    + "WHERE t.aucAfiliadosId.id = " + idAucAfiliado + " "
                    + "ORDER BY t.id ASC";

            List<AucAfiliadoContactos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();

            int posicion = 0;
            for (AucAfiliadoContactos per : list) {
                AucAfiliadoContacto contacto = castEntidadNegocio(per);
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

    @Override
    public List<AucAfiliadoContacto> consultarListaContactosPorAsegAfiliado(int idAucAfiliado) throws Exception {
        List<AucAfiliadoContacto> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t FROM AucAfiliadoContactos t "
                    + "WHERE t.aucAfiliadosId.id IN ( SELECT p.id FROM AucAfiliados p WHERE p.asegAfiliadosId.id = " + idAucAfiliado + ") "
                    + "ORDER BY t.id ASC";

            List<AucAfiliadoContactos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();

            int posicion = 0;
            for (AucAfiliadoContactos per : list) {
                AucAfiliadoContacto contacto = castEntidadNegocio(per);
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

    @Override
    public List<AsegAfiliadoContactoConsulta> consultarListaContactosPorAsegAfiliado(int idAucAfiliado, String numeroExcluidos) throws Exception {
        List<AsegAfiliadoContactoConsulta> listResult = new ArrayList();
        try {
//            String strQuery = "SELECT t FROM AucAfiliadoContactos t "
//                    + "WHERE t.aucAfiliadosId.id IN ( SELECT p.id FROM AucAfiliados p WHERE p.asegAfiliadosId.id = " + idAucAfiliado + ") "
//                    + "AND t.numeroContacto NOT IN (" + numeroExcluidos + ") "
//                    + "ORDER BY t.id ASC";
            String strQuery = "SELECT t FROM AucAfiliadoContactos t "
                    + "WHERE t.aucAfiliadosId.id IN ( SELECT p.id FROM AucAfiliados p WHERE p.asegAfiliadosId.id = " + idAucAfiliado + ") ";
            if (numeroExcluidos != null && !numeroExcluidos.isEmpty()) {
                strQuery += "AND t.numeroContacto NOT IN (" + numeroExcluidos + ") ";
            }
            strQuery += "ORDER BY t.id ASC";
            List<AucAfiliadoContactos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();

            for (AucAfiliadoContactos per : list) {
                listResult.add(castEntidadNegocioAfiliado(per));
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

    private AucAfiliadoContacto castEntidadNegocio(AucAfiliadoContactos entidad) {
        AucAfiliadoContacto negocio = new AucAfiliadoContacto();
        negocio.setId(entidad.getId());
        negocio.setAucAfiliadoId(new AucAfiliado(entidad.getAucAfiliadosId().getId()));
        negocio.setNumeroContacto(entidad.getNumeroContacto());
        negocio.setObservacion(entidad.getObservacion());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }

    private AucAfiliadoContactos castNegocioEntidad(AucAfiliadoContacto negocio) {
        AucAfiliadoContactos entidad = new AucAfiliadoContactos();
        entidad.setAucAfiliadosId(new AucAfiliados(negocio.getAucAfiliadoId().getId()));
        entidad.setNumeroContacto(negocio.getNumeroContacto());
        entidad.setObservacion(negocio.getObservacion());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

    private AsegAfiliadoContactoConsulta castEntidadNegocioAfiliado(AucAfiliadoContactos ent) {
        AsegAfiliadoContactoConsulta neg = new AsegAfiliadoContactoConsulta();
        neg.setId(ent.getId());
        neg.setNumeroContacto(ent.getNumeroContacto());
        neg.setObservacion(ent.getObservacion());
        neg.setMaeTipoContactoValor(AsegAfiliadoContactoConsulta.TIPO_CONTACTO_NULL);
        neg.setOrigen(AsegAfiliadoContactoConsulta.ORIGEN_AUDIATORIA_CONCURRENTE);
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        return neg;
    }
}
