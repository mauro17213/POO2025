/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.judicial;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContactoConsulta;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.judicial.GjTercero;
import com.saviasaludeps.savia.dominio.judicial.GjTerceroContacto;
import com.saviasaludeps.savia.ejb.entidades.GjTerceroContactos;
import com.saviasaludeps.savia.ejb.entidades.GjTerceros;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.judicial.GjTerceroContactoRemoto;
import java.util.ArrayList;
import java.util.Arrays;
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
@Remote(GjTerceroContactoRemoto.class)
public class GjTerceroContactoServicio extends GenericoServicio implements GjTerceroContactoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c.id) FROM GjTerceroContactos c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "gjTercerosId":
                            strQuery += "AND c.gjTercerosId.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "contacto":
                            strQuery += "AND c.contacto = '" + (String) e.getValue() + "' ";
                            break;
                    }
                }
            }
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND c.contacto LIKE '%" + (String) paramConsulta.getParametroConsulta1() + "%' ";
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND c.gjTercerosId.id = " + paramConsulta.getParametroConsulta2() + " ";
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
    public List<GjTerceroContacto> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GjTerceroContacto> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t FROM GjTerceroContactos t "
                    + "WHERE t.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "gjTercerosId":
                            strQuery += "AND t.gjTercerosId.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "contacto":
                            strQuery += "AND t.contacto = '" + (String) e.getValue() + "' ";
                            break;
                    }
                }
            }

            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND t.contacto LIKE '%" + (String) paramConsulta.getParametroConsulta1() + "%' ";
            }

            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND t.gjTercerosId.id = " + paramConsulta.getParametroConsulta2() + " ";
            }

            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "t." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "t.gjTercerosId.id DESC";
            }

            List<GjTerceroContactos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();

            int posicion = 0;
            for (GjTerceroContactos per : list) {
                GjTerceroContacto contacto = castEntidadNegocio(per);
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
    public GjTerceroContacto consultar(int id) throws Exception {
        GjTerceroContacto objRes = null;
        try {
            objRes = castEntidadNegocio((GjTerceroContactos) getEntityManager().find(GjTerceroContactos.class, id));
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
    public int insertar(GjTerceroContacto obj) throws Exception {
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
    public void actualizar(GjTerceroContacto obj) throws Exception {
        try {
            String hql = "UPDATE GjTerceroContactos SET"
                    + " gjTercerosId.id = :gjTercerosId,"
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
            query.setParameter("gjTercerosId", obj.getGjTercerosId().getId());
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
    public GjTerceroContacto eliminar(int id) throws Exception {
        GjTerceroContacto obj = null;
        try {
            GjTerceroContactos ent = getEntityManager().find(GjTerceroContactos.class, id);
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

    public static GjTerceroContacto castEntidadNegocio(GjTerceroContactos ent) {
        GjTerceroContacto neg = new GjTerceroContacto();
        neg.setId(ent.getId());
        if (ent.getGjTercerosId() != null) {
            GjTercero ter = new GjTercero();
            ter.setId(ent.getGjTercerosId().getId());
            neg.setGjTercerosId(ter);
        }
        neg.setMaeTipoContactoId(ent.getMaeTipoContactoId());
        neg.setMaeTipoContactoCodigo(ent.getMaeTipoContactoCodigo());
        neg.setMaeTipoContactoValor(ent.getMaeTipoContactoValor());
        neg.setContacto(ent.getContacto());
        neg.setActivo(ent.getActivo());
        neg.setObservacion(ent.getObservacion());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        neg.setUsuarioModifica(ent.getUsuarioModifica());
        neg.setTerminalModifica(ent.getTerminalModifica());
        neg.setFechaHoraModifica(ent.getFechaHoraModifica());
        return neg;
    }

    public static GjTerceroContactos castNegocioEntidad(GjTerceroContacto neg) {
        GjTerceroContactos ent = new GjTerceroContactos();
        ent.setId(neg.getId());
        ent.setGjTercerosId(new GjTerceros(neg.getGjTercerosId().getId()));
        ent.setMaeTipoContactoId(neg.getMaeTipoContactoId());
        ent.setMaeTipoContactoCodigo(neg.getMaeTipoContactoCodigo());
        ent.setMaeTipoContactoValor(neg.getMaeTipoContactoValor());
        ent.setContacto(neg.getContacto());
        ent.setActivo(neg.isActivo());
        ent.setObservacion(neg.getObservacion());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setUsuarioModifica(neg.getUsuarioModifica());
        ent.setTerminalModifica(neg.getTerminalModifica());
        ent.setFechaHoraModifica(neg.getFechaHoraModifica());
        return ent;
    }

    @Override
    public List<GjTerceroContacto> consultarListaContactos(int idTercero) throws java.lang.Exception {
        List<GjTerceroContacto> listResult = new ArrayList();
        try {
            String strQuery = "FROM GjTerceroContactos p "
                    + "WHERE ";
            strQuery += " p.gjTercerosId.id = " + idTercero + " ";

            List<GjTerceroContactos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GjTerceroContactos cont : list) {
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
    public List<AsegAfiliadoContactoConsulta> consultarListaContactosAfiliado(int idAfiliado, String numerosExcluidos) throws java.lang.Exception {
        List<AsegAfiliadoContactoConsulta> listResult = new ArrayList();
        try {
            String strQuery = "FROM GjTerceroContactos p "
                    + "WHERE ";         
            strQuery += "p.gjTercerosId.id = (SELECT gt.id FROM GjTerceros gt WHERE gt.asegAfiliadosId.id = " + idAfiliado + ")";

            if (numerosExcluidos != null && !numerosExcluidos.isEmpty()) {
                String[] numeros = numerosExcluidos.split(",");
                strQuery += " AND p.contacto NOT IN (:numeros)";
                List<String> numerosList = Arrays.asList(numeros);
                listResult = getEntityManager().createQuery(strQuery)
                        .setParameter("numeros", numerosList)
                        .getResultList();
            } else {
                List<GjTerceroContactos> list = getEntityManager().createQuery(strQuery)
                        .getResultList();
                for (GjTerceroContactos cont : list) {
                    listResult.add(castEntidadNegocioAfiliado(cont));
                }
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
    
    private AsegAfiliadoContactoConsulta castEntidadNegocioAfiliado(GjTerceroContactos ent) {
        AsegAfiliadoContactoConsulta neg = new AsegAfiliadoContactoConsulta();
        neg.setId(ent.getId());
        neg.setNumeroContacto(ent.getContacto());
        neg.setObservacion(ent.getObservacion());
        neg.setMaeTipoContactoValor(ent.getMaeTipoContactoValor());
        neg.setOrigen(AsegAfiliadoContactoConsulta.ORIGEN_GESTION_JUDICIAL);
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        return neg;
    }

}
