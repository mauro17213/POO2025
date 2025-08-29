/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuGrupo;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoPrograma;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuGrupoProgramas;
import com.saviasaludeps.savia.ejb.entidades.AuGrupos;
import com.saviasaludeps.savia.ejb.entidades.PeProgramas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuGrupoProgramaRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author sgiraldo
 */
@Stateless
@Remote(AuGrupoProgramaRemoto.class)
public class AuGrupoProgramaServicio extends GenericoServicio implements AuGrupoProgramaRemoto {

    @Override
    public AuGrupoPrograma consultar(int id) throws Exception {
        AuGrupoPrograma objRes = null;
        try {
            objRes = castEntidadNegocio((AuGrupoProgramas) getEntityManager().find(AuGrupoProgramas.class, id));
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
    public int insertar(AuGrupoPrograma obj) throws Exception {
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
    public void actualizar(AuGrupoPrograma obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuGrupoProgramas a SET ";
            strQuery += "a.auGruposId.id = :auGruposId ,";
            strQuery += "a.peProgramasId.id = :peProgramasId ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("auGruposId", obj.getAuGrupoId().getId());
            query.setParameter("peProgramasId", obj.getPeProgramaId().getId());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
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
    public AuGrupoPrograma eliminar(int id) throws Exception {
        AuGrupoPrograma obj = null;
        try {
            AuGrupoProgramas ent = getEntityManager().find(AuGrupoProgramas.class, id);
            if (ent != null){
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
    public List<AuGrupoPrograma> consultarListaPorIdGrupo(int idGrupo) throws Exception {
        List<AuGrupoPrograma> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuGrupoProgramas p "
                    + "WHERE p.id > 0";
            strQuery += " AND p.auGruposId.id = " + idGrupo + " ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<AuGrupoProgramas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuGrupoProgramas programa : list) {
                listaResultados.add(castEntidadNegocio(programa));
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
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuGrupoProgramas p ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "WHERE p.auGruposId.id = " + paramConsulta.getParametroConsulta1()+ " ";
            } else {
                strQuery += "WHERE p.id > 0 ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "peProgramaId.descripcionPrograma" :
                            strQuery += "AND p.peProgramasId.descripcionPrograma LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "peProgramaId.codigoPrograma" :
                            strQuery += "AND p.peProgramasId.codigoPrograma LIKE '%" + e.getValue() + "%' ";
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
    public List<AuGrupoPrograma> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuGrupoPrograma> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuGrupoProgramas p ";
            if (paramConsulta.getParametroConsulta1()!= null) {
                strQuery += "WHERE p.auGruposId.id = " + paramConsulta.getParametroConsulta1() + " ";
            } else {
                strQuery += "WHERE p.id > 0 ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "peProgramaId.descripcionPrograma" :
                            strQuery += "AND p.peProgramasId.descripcionPrograma LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "peProgramaId.codigoPrograma" :
                            strQuery += "AND p.peProgramasId.codigoPrograma LIKE '%" + e.getValue() + "%' ";
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

            List<AuGrupoProgramas> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuGrupoProgramas programa : list) {
                listaResultados.add(castEntidadNegocio(programa));
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
    public boolean validarPrograma(int idPrograma, int idGrupo) throws Exception {
        boolean existe = false;
        try {
            String strQuery = "FROM AuGrupoProgramas p "
                    + "WHERE p.id > 0";
            strQuery += " AND p.auGruposId.id = " + idGrupo + " ";
            strQuery += " AND p.peProgramasId.id = " + idPrograma + " ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<AuGrupoProgramas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            existe = list != null && !list.isEmpty();
        } catch (NoResultException e) {
            existe = false;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return existe;
    }
    
    public AuGrupoPrograma castEntidadNegocio(AuGrupoProgramas entidad) {
        AuGrupoPrograma negocio = new AuGrupoPrograma();
        negocio.setId(entidad.getId());
        negocio.setAuGrupoId(new AuGrupo(entidad.getAuGruposId().getId()));
        negocio.setPeProgramaId(new PePrograma(entidad.getPeProgramasId().getId()));
        negocio.getPeProgramaId().setCodigoPrograma(entidad.getPeProgramasId().getCodigoPrograma());
        negocio.getPeProgramaId().setDescripcionPrograma(entidad.getPeProgramasId().getDescripcionPrograma());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        return negocio;
    }
    
    public AuGrupoProgramas castNegocioEntidad(AuGrupoPrograma negocio) {
        AuGrupoProgramas entidad = new AuGrupoProgramas();
        entidad.setId(negocio.getId());
        entidad.setAuGruposId(new AuGrupos(negocio.getAuGrupoId().getId()));
        entidad.setPeProgramasId(new PeProgramas(negocio.getPeProgramaId().getId()));
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        return entidad;
    }
    
}
