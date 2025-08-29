/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuGrupo;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoSede;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuGrupoSedes;
import com.saviasaludeps.savia.ejb.entidades.AuGrupos;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuGrupoSedeRemoto;
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
 * @author Stiven Giraldo
 */
@Stateless
@Remote(AuGrupoSedeRemoto.class)
public class AuGrupoSedeServicio extends GenericoServicio implements AuGrupoSedeRemoto {

    @Override
    public AuGrupoSede consultar(int id) throws Exception {
        AuGrupoSede objRes = null;
        try {
            objRes = castEntidadNegocio((AuGrupoSedes) getEntityManager().find(AuGrupoSedes.class, id));
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
    public int insertar(AuGrupoSede obj) throws Exception {
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
    public void actualizar(AuGrupoSede obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuGrupoSedes SET ";
            strQuery += "a.auGruposId.id = :auGruposId ,";
            strQuery += "a.cntPrestadoresId.id = :cntPrestadoresId ,";
            strQuery += "a.cntPrestadorSedesId.id = :cntPrestadorSedesId ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("auGruposId", obj.getAuGrupo().getId());
            query.setParameter("cntPrestadoresId", obj.getCntPrestadoresId().getId());
            query.setParameter("cntPrestadorSedesId", obj.getCntPrestadorSede().getId());
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
    public AuGrupoSede eliminar(int id) throws Exception {
        AuGrupoSede obj = null;
        try {
            AuGrupoSedes ent = getEntityManager().find(AuGrupoSedes.class, id);
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
    
    private AuGrupoSede castEntidadNegocio(AuGrupoSedes entidad) {
        AuGrupoSede negocio = new AuGrupoSede();
        negocio.setId(entidad.getId());
        negocio.setAuGrupo(new AuGrupo(entidad.getAuGruposId().getId()));
        negocio.setCntPrestadorSede(new CntPrestadorSede(entidad.getCntPrestadorSedesId().getId()));
        negocio.getCntPrestadorSede().setNombreSede(entidad.getCntPrestadorSedesId().getNombre());
        negocio.setCntPrestadoresId(new CntPrestador(entidad.getCntPrestadoresId().getId()));
        negocio.getCntPrestadoresId().setRazonSocial(entidad.getCntPrestadoresId().getRazonSocial());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }
    
    private AuGrupoSedes castNegocioEntidad(AuGrupoSede negocio) {
        AuGrupoSedes entidad = new AuGrupoSedes();
        entidad.setId(negocio.getId());
        entidad.setAuGruposId(new AuGrupos(negocio.getAuGrupo().getId()));
        entidad.setCntPrestadorSedesId(new CntPrestadorSedes(negocio.getCntPrestadorSede().getId()));
        entidad.setCntPrestadoresId(new CntPrestadores(negocio.getCntPrestadoresId().getId()));
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

    @Override
    public List<AuGrupoSede> consultarListaPorIdGrupo(int idGrupo) throws Exception {
        List<AuGrupoSede> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuGrupoSedes p "
                    + "WHERE p.id > 0";
            strQuery += " AND p.auGruposId.id = " + idGrupo + " ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<AuGrupoSedes> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuGrupoSedes sedes : list) {
                listaResultados.add(castEntidadNegocio(sedes));
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
            String strQuery = "SELECT COUNT(p) FROM AuGrupoSedes p ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "WHERE p.auGruposId.id = " + (int) paramConsulta.getParametroConsulta1()+ " ";
            } else {
                strQuery += "WHERE p.id > 0 ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "cntPrestadoresId.razonSocial" :
                            strQuery += "AND p.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntPrestadorSede.nombreSede":
                            strQuery += "AND p.cntPrestadorSedesId.nombre LIKE '%" + e.getValue() + "%' ";
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
    public List<AuGrupoSede> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuGrupoSede> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuGrupoSedes p ";
            strQuery += "WHERE p.auGruposId.id = " + paramConsulta.getParametroConsulta1() + " ";
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "cntPrestadoresId.razonSocial" :
                            strQuery += "AND p.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cntPrestadorSede.nombreSede":
                            strQuery += "AND p.cntPrestadorSedesId.nombre LIKE '%" + e.getValue() + "%' ";
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

            List<AuGrupoSedes> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuGrupoSedes grupoSede : list) {
                listaResultados.add(castEntidadNegocio(grupoSede));
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
    public boolean validarSede(int idSede, int idGrupo) throws Exception {
        boolean existe = false;
        try {
            String strQuery = "FROM AuGrupoSedes p "
                    + "WHERE p.id > 0";
            strQuery += " AND p.auGruposId.id = " + idGrupo + " ";
            strQuery += " AND p.cntPrestadorSedesId.id = " + idSede + " ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<AuGrupoSedes> list = getEntityManager().createQuery(strQuery)
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
    
}
