/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuGrupo;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoDiagnostico;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuGrupoDiagnosticos;
import com.saviasaludeps.savia.ejb.entidades.AuGrupos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuGrupoDiagnosticoRemoto;
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
@Remote(AuGrupoDiagnosticoRemoto.class)
public class AuGrupoDiagnosticoServicio extends GenericoServicio implements AuGrupoDiagnosticoRemoto {

    @Override
    public AuGrupoDiagnostico consultar(int id) throws Exception {
        AuGrupoDiagnostico objRes = null;
        try {
            objRes = castEntidadNegocio((AuGrupoDiagnosticos) getEntityManager().find(AuGrupoDiagnosticos.class, id));
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
    public int insertar(AuGrupoDiagnostico obj) throws Exception {
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
    public void actualizar(AuGrupoDiagnostico obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuGrupoDiagnosticos SET ";
            strQuery += "a.auGruposId.id = :auGruposId ,";
            strQuery += "a.maeDiagnosticoId = :maeDiagnosticoId ,";
            strQuery += "a.maeDiagnosticoCodigo = :maeDiagnosticoCodigo ,";
            strQuery += "a.maeDiagnosticoValor = :maeDiagnosticoValor ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", obj.getId());
            query.setParameter("auGruposId", obj.getAuGrupo().getId());
            query.setParameter("maeDiagnosticoId", obj.getMaDiagnosticoId());
            query.setParameter("maeDiagnosticoCodigo", obj.getMaDiagnosticoCodigo());
            query.setParameter("maeDiagnosticoValor", obj.getMaDiagnosticoValor());
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
    public AuGrupoDiagnostico eliminar(int id) throws Exception {
        AuGrupoDiagnostico obj = null;
        try {
            AuGrupoDiagnosticos ent = getEntityManager().find(AuGrupoDiagnosticos.class, id);
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
    
    private AuGrupoDiagnostico castEntidadNegocio(AuGrupoDiagnosticos entidad) {
        AuGrupoDiagnostico negocio = new AuGrupoDiagnostico();
        negocio.setId(entidad.getId());
        negocio.setAuGrupo(new AuGrupo(entidad.getAuGruposId().getId()));
        negocio.setMaDiagnosticoId(entidad.getMaDiagnosticoId());
        negocio.setMaDiagnosticoCodigo(entidad.getMaDiagnosticoCodigo());
        negocio.setMaDiagnosticoValor(entidad.getMaDiagnosticoValor());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        return negocio;
    }
    
    private AuGrupoDiagnosticos castNegocioEntidad(AuGrupoDiagnostico negocio) {
        AuGrupoDiagnosticos entidad = new AuGrupoDiagnosticos();
        entidad.setId(negocio.getId());
        entidad.setAuGruposId(new AuGrupos(negocio.getAuGrupo().getId()));
        entidad.setMaDiagnosticoId(negocio.getMaDiagnosticoId());
        entidad.setMaDiagnosticoCodigo(negocio.getMaDiagnosticoCodigo());
        entidad.setMaDiagnosticoValor(negocio.getMaDiagnosticoValor());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        return entidad;
    }

    @Override
    public List<AuGrupoDiagnostico> consultarListaPorIdGrupo(int idGrupo) throws Exception {
        List<AuGrupoDiagnostico> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuGrupoDiagnosticos p "
                    + "WHERE p.id > 0";
            strQuery += " AND p.auGruposId.id = " + idGrupo + " ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<AuGrupoDiagnosticos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuGrupoDiagnosticos historicos : list) {
                listaResultados.add(castEntidadNegocio(historicos));
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
            String strQuery = "SELECT COUNT(p) FROM AuGrupoDiagnosticos p ";
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
    public List<AuGrupoDiagnostico> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuGrupoDiagnostico> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuGrupoDiagnosticos p ";
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

            List<AuGrupoDiagnosticos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuGrupoDiagnosticos grupoDiagnostico : list) {
                listaResultados.add(castEntidadNegocio(grupoDiagnostico));
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
    public boolean validarDiagnostico(int idDiagnostico, int idGrupo) throws Exception {
        boolean existe = false;
        try {
            String strQuery = "FROM AuGrupoDiagnosticos p "
                    + "WHERE p.id > 0";
            strQuery += " AND p.auGruposId.id = " + idGrupo + " ";
            strQuery += " AND p.maDiagnosticoId = "+ idDiagnostico + " ";
            strQuery += " ORDER BY ";
            strQuery += " p.id DESC";
            List<AuGrupoDiagnosticos> list = getEntityManager().createQuery(strQuery)
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
