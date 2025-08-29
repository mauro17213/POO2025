/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.tutela;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContactoConsulta;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuPersona;
import com.saviasaludeps.savia.dominio.tutela.TuPersonaContacto;
import com.saviasaludeps.savia.ejb.entidades.TuPersonas;
import com.saviasaludeps.savia.ejb.entidades.TuPersonasContactos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.tutela.TuPersonaContactoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jramirer
 */
@Stateless
@Remote(TuPersonaContactoRemoto.class)
public class TuPersonaContactoServicio extends GenericoServicio implements TuPersonaContactoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c.id) FROM TuPersonasContactos c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "personaId":
                            strQuery += "AND c.tuPersonasId.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "numeroContacto":
                            strQuery += "AND c.numeroContacto = '" + (String) e.getValue() + "' ";
                            break;
                    }
                }
            }

            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND c.numeroDocumento LIKE '%" + (String) paramConsulta.getParametroConsulta1() + "%' ";
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
    public List<TuPersonaContacto> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<TuPersonaContacto> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t FROM TuPersonasContactos t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "personaId":
                            strQuery += "AND t.tuPersonasId.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "numeroContacto":
                            strQuery += "AND t.numeroContacto = '" + (String) e.getValue() + "' ";
                            break;
                    }
                }
            }

            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND t.numeroDocumento LIKE '%" + (String) paramConsulta.getParametroConsulta1() + "%' ";
            }

            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND t.tuPersonasId.id = " + paramConsulta.getParametroConsulta2() + " ";
            }

            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "t." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "t.id ASC";
            }

            List<TuPersonasContactos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();

            int posicion = 0;
            for (TuPersonasContactos per : list) {
                TuPersonaContacto contacto = castEntidadNegocio(per);
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
    public TuPersonaContacto consultar(int id) throws Exception {
        TuPersonaContacto objRes = null;
        try {
            objRes = castEntidadNegocio((TuPersonasContactos) getEntityManager().find(TuPersonasContactos.class, id));
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
    public int insertar(TuPersonaContacto obj) throws Exception {
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
    public void actualizar(TuPersonaContacto obj) throws Exception {
        try {
            String hql = "UPDATE TuPersonasContactos SET"
                    + " nombre = :nombre,"
                    + " codigoDespacho = :codigoDespacho,"
                    + " ubicacionId = :ubicacionId,"
                    + " activo = :activo,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
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
    public TuPersonaContacto eliminar(int id) throws Exception {
        TuPersonaContacto obj = null;
        try {
            TuPersonasContactos ent = getEntityManager().find(TuPersonasContactos.class, id);
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

    public static TuPersonaContacto castEntidadNegocio(TuPersonasContactos ent) {
        TuPersonaContacto neg = new TuPersonaContacto();
        neg.setId(ent.getId());
        neg.setNumeroContacto(ent.getNumeroContacto());
        neg.setTuPersona(new TuPersona(ent.getTuPersonasId().getId()));
        neg.setObservacion(ent.getObservacion());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        neg.setUsuarioModifica(ent.getUsuarioModifica());
        neg.setTerminalModifica(ent.getTerminalModifica());
        neg.setFechaHoraModifica(ent.getFechaHoraModifica());
        return neg;
    }

    public static TuPersonasContactos castNegocioEntidad(TuPersonaContacto neg) {
        TuPersonasContactos ent = new TuPersonasContactos();
        ent.setId(neg.getId());
        ent.setNumeroContacto(neg.getNumeroContacto());
        ent.setObservacion(neg.getObservacion());
        ent.setTuPersonasId(new TuPersonas(neg.getTuPersona().getId()));
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setUsuarioModifica(neg.getUsuarioModifica());
        ent.setTerminalModifica(neg.getTerminalModifica());
        ent.setFechaHoraModifica(neg.getFechaHoraModifica());
        return ent;
    }

    @Override
    public List<TuPersonaContacto> consultarListaContactos(int idPersona) throws java.lang.Exception {
        List<TuPersonaContacto> listResult = new ArrayList();
        try {
            String strQuery = "FROM TuPersonasContactos p "
                    + "WHERE ";
            strQuery += " p.tuPersonasId.id = " + idPersona + " ";

            List<TuPersonasContactos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (TuPersonasContactos cont : list) {
                listResult.add(castEntidadNegocio(cont));
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
    public List<AsegAfiliadoContactoConsulta> consultarListaContactosPorAsegAfiliado(int idAsegAfiliado, String numeroExcluidos) throws Exception {
        List<AsegAfiliadoContactoConsulta> listResult = new ArrayList();
        try {
//            String strQuery = "SELECT t FROM TuPersonasContactos t "
//                    + "WHERE t.tuPersonasId.id IN ( SELECT p.id FROM TuPersonas p WHERE p.asegAfiliadoId = " +  idAsegAfiliado + ") "
//                    + "AND t.numeroContacto NOT IN (" + numeroExcluidos +") "
//                    + "ORDER BY t.id ASC";
            String strQuery = "SELECT t FROM TuPersonasContactos t "
                    + "WHERE t.tuPersonasId.id IN ( SELECT p.id FROM TuPersonas p WHERE p.asegAfiliadoId = " + idAsegAfiliado + ") ";
            if (numeroExcluidos != null && !numeroExcluidos.isEmpty()) {
                strQuery += "AND t.numeroContacto NOT IN (" + numeroExcluidos + ") ";
            }
            strQuery += "ORDER BY t.id ASC";
            List<TuPersonasContactos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();

            for (TuPersonasContactos per : list) {
                listResult.add(castEntidadNegocioAfilaido(per));
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

    private AsegAfiliadoContactoConsulta castEntidadNegocioAfilaido(TuPersonasContactos ent) {
        AsegAfiliadoContactoConsulta neg = new AsegAfiliadoContactoConsulta();
        neg.setId(ent.getId());
        neg.setNumeroContacto(ent.getNumeroContacto());
        neg.setObservacion(ent.getObservacion());
        neg.setMaeTipoContactoValor(AsegAfiliadoContactoConsulta.TIPO_CONTACTO_NULL);
        neg.setOrigen(AsegAfiliadoContactoConsulta.ORIGEN_TUTELA);
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        neg.setUsuarioModifica(ent.getUsuarioModifica());
        neg.setTerminalModifica(ent.getTerminalModifica());
        neg.setFechaHoraModifica(ent.getFechaHoraModifica());
        return neg;
    }
}
