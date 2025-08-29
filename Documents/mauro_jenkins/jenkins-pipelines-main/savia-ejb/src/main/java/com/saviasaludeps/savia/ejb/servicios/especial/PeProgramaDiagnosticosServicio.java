/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.especial.PeProgramaDiagnostico;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.especial.PeProgramaDiagnosticosRemoto;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.ejb.entidades.PeProgramaDiagnosticos;
import com.saviasaludeps.savia.ejb.entidades.PeProgramas;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author idbohorquez
 */
@Stateless
@Remote(PeProgramaDiagnosticosRemoto.class)
@Local(PeProgramaDiagnosticosLocal.class)
public class PeProgramaDiagnosticosServicio extends GenericoServicio implements PeProgramaDiagnosticosLocal, PeProgramaDiagnosticosRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM PeProgramaDiagnosticos p "
                    + "WHERE p.id > 0 AND p.borrado = 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "idPrograma":
                            strQuery += "AND p.peProgramasId.id = " + (String) e.getValue() + " ";
                            break; 
                        case "maDiagnosticoCodigo":
                            strQuery += "AND p.maDiagnosticoCodigo = '" + (String) e.getValue() + "' ";
                            break;
                    }
                }
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
    public List<PeProgramaDiagnostico> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<PeProgramaDiagnostico> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p FROM PeProgramaDiagnosticos p "
                    + "WHERE p.borrado = 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "idPrograma":
                            strQuery += "AND p.peProgramasId.id = " + (String) e.getValue() + " ";
                            break;   
                        case "maDiagnosticoCodigo":
                            strQuery += "AND p.maDiagnosticoCodigo = '" + (String) e.getValue() + "'  ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id ASC";
            }
            List<PeProgramaDiagnosticos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (PeProgramaDiagnosticos user : list) {
                listResult.add(castentidadNegocio(user));
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
    public int insertar(PeProgramaDiagnostico obj) throws java.lang.Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }
    
    @Override
    public List<PeProgramaDiagnostico> consultarDiagnosticosPrograma(int idPrograma) throws java.lang.Exception {
        List<PeProgramaDiagnostico> diagnosticos = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("FROM PeProgramaDiagnosticos p " );
            strQuery.append(" WHERE p.peProgramasId.id = ").append(idPrograma);
            strQuery.append(" AND p.borrado = 0 ");

            List<PeProgramaDiagnosticos> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (PeProgramaDiagnosticos diagnostico : list) {
                diagnosticos.add(castentidadNegocio(diagnostico));
            }
        } catch (NoResultException e) {
            diagnosticos = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return diagnosticos;
    }
    
    @Override
    public void actualizar(PeProgramaDiagnostico obj) throws java.lang.Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE PeProgramaDiagnosticos a SET a.marcaAutomatico = :marcaAutomatico,  ";            
            strQuery += " a.direcciona = :direcciona , ";
            strQuery += " a.aplicaRescate = :aplicaRescate , ";
            strQuery += " a.usuarioModifica = :usuarioModifica , ";
            strQuery += " a.terminalModifica = :terminalModifica , ";
            strQuery += " a.fechaHoraModifica = :fechaHoraModifica  ";    
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("marcaAutomatico", obj.getMarcaAutomatico());
            query.setParameter("direcciona", obj.getDirecciona());
            query.setParameter("aplicaRescate", obj.getAplicaRescate());
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
    public void eliminar(PeProgramaDiagnostico obj) throws java.lang.Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE PeProgramaDiagnosticos a SET a.borrado = 1,  ";             
            strQuery += "a.usuarioBorra = :usuarioBorra ,";
            strQuery += "a.terminalBorra = :terminalBorra ,";
            strQuery += "a.fechaHoraBorra = :fechaHoraBorra ";            
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);          
            query.setParameter("usuarioBorra", obj.getUsuarioModifica());
            query.setParameter("terminalBorra", obj.getTerminalModifica());
            query.setParameter("fechaHoraBorra", obj.getFechaHoraModifica());            
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
    
    /**
     *
     * @param obj
     * @throws java.lang.Exception
     */
    @Override
    public void actualizarMarcaAutomatica(PeProgramaDiagnostico obj) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE PeProgramaDiagnosticos SET marcaAutomatico = :marca, ");
            sql.append(" direcciona = :direcciona, ");
            sql.append(" aplicaRescate = :aplicaRescate, ");
            sql.append(" usuarioModifica = :usuarioModifica, ");
            sql.append(" terminalModifica = :terminalModifica, ");
            sql.append(" fechaHoraModifica = :fechaHoraModifica ");
            sql.append(" WHERE id = :id ");
            
            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("marca", obj.getMarcaAutomatico());  
            query.setParameter("direcciona", obj.getDirecciona());  
            query.setParameter("aplicaRescate", obj.getAplicaRescate());  
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {
            
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    /**
     * @authot Isaac Bohorquez
     * @fechaCreacion 23/06/2022
     * @param idDiagnosticos
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public List<PeProgramaDiagnostico> consultarDiagnosticosProgramaMarcacionAutomatica(String idDiagnosticos) throws java.lang.Exception {
        List<PeProgramaDiagnostico> diagnosticos = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("FROM PeProgramaDiagnosticos p " );
            strQuery.append(" WHERE p.maDiagnosticoId in (").append(idDiagnosticos).append(") ");
            strQuery.append(" AND p.borrado = 0 AND p.marcaAutomatico = 1 ");

            List<PeProgramaDiagnosticos> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (PeProgramaDiagnosticos diagnostico : list) {
                diagnosticos.add(castentidadNegocio(diagnostico));
            }
        } catch (NoResultException e) {
            diagnosticos = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return diagnosticos;
    }
        
    /**
     * @authot Isaac Bohorquez
     * @fechaCreacion 18/08/2022
     * @param idDiagnosticos
     * @return List<PeProgramaDiagnostico>
     * @throws java.lang.Exception
     */
    @Override
    public List<PeProgramaDiagnostico> consultarDiagnosticosProgramaDireccionados(String idDiagnosticos) throws java.lang.Exception {
        List<PeProgramaDiagnostico> diagnosticos = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("FROM PeProgramaDiagnosticos p " );
            strQuery.append(" WHERE p.maDiagnosticoId in (").append(idDiagnosticos).append(") ");   
            strQuery.append(" AND p.peProgramasId.direcciona = ").append(true);
            strQuery.append(" AND p.borrado = 0 AND p.direcciona = ").append(true);            

            List<PeProgramaDiagnosticos> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (PeProgramaDiagnosticos diagnostico : list) {
                diagnosticos.add(castentidadNegocio(diagnostico));
            }
        } catch (NoResultException e) {
            diagnosticos = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return diagnosticos;
    }
    
    /**
     * Metodo encargado de consultar si existe un registro con el id de programa
     * y el id de maetro diagnostico.
     * 
     * @authot idbohorquez
     * @fechaCreacion 02/12/2022
     * @param objeto
     * @return PeProgramaDiagnostico
     * @throws Exception
     */
    @Override
    public PeProgramaDiagnostico consultarDiagnosticoPrograma(PeProgramaDiagnostico objeto) throws java.lang.Exception {
        PeProgramaDiagnostico peProgramaDiagnostico = null;
        try {
            StringBuilder strQuery = new StringBuilder("Select p FROM PeProgramaDiagnosticos p ");
            strQuery.append(" WHERE p.borrado = 0 ");
            strQuery.append(" AND p.peProgramasId.id = ").append(objeto.getPeProgramasId().getId());
            strQuery.append(" AND p.maDiagnosticoId = ").append(objeto.getMaDiagnosticoId());

           PeProgramaDiagnosticos peProgramaDiagnosticos = (PeProgramaDiagnosticos) getEntityManager().createQuery(strQuery.toString()).setMaxResults(1).getSingleResult();
           peProgramaDiagnostico = castentidadNegocio(peProgramaDiagnosticos);
        } catch (NoResultException e) {
            peProgramaDiagnostico = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return peProgramaDiagnostico;
    }
    
    public static PeProgramaDiagnosticos castNegocioEntidad(PeProgramaDiagnostico obj) {
        PeProgramaDiagnosticos ent = new PeProgramaDiagnosticos();
        ent.setPeProgramasId(new PeProgramas(obj.getPeProgramasId().getId()));
        ent.setMaDiagnosticoId(obj.getMaDiagnosticoId());
        ent.setMaDiagnosticoCodigo(obj.getMaDiagnosticoCodigo());
        ent.setMaDiagnosticoValor(obj.getMaDiagnosticoValor());
        ent.setBorrado(false);
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setMarcaAutomatico(obj.getMarcaAutomatico());
        ent.setDirecciona(obj.getDirecciona());
        ent.setAplicaRescate(obj.getAplicaRescate());
        return ent;
    }

    private PeProgramaDiagnostico castentidadNegocio(PeProgramaDiagnosticos diagnostico) {
        PeProgramaDiagnostico diag = new PeProgramaDiagnostico();
        diag.setId(diagnostico.getId());
        diag.setPeProgramasId(new PePrograma(diagnostico.getPeProgramasId().getId()));
        diag.setMaDiagnosticoId(diagnostico.getMaDiagnosticoId());
        diag.setMaDiagnosticoCodigo(diagnostico.getMaDiagnosticoCodigo());
        diag.setMaDiagnosticoValor(diagnostico.getMaDiagnosticoValor());
        diag.setBorrado(diagnostico.getBorrado());
        diag.setMarcaAutomatico(diagnostico.getMarcaAutomatico());
        diag.setDirecciona(diagnostico.getDirecciona());
        diag.setAplicaRescate(diagnostico.getAplicaRescate());
        diag.setUsuarioCrea(diagnostico.getUsuarioCrea());
        diag.setFechaHoraCrea(diagnostico.getFechaHoraCrea());
        diag.setTerminalCrea(diagnostico.getTerminalCrea());
        diag.setUsuarioModifica(diagnostico.getUsuarioModifica());
        diag.setFechaHoraModifica(diagnostico.getFechaHoraModifica());
        diag.setTerminalModifica(diagnostico.getTerminalModifica());
        return diag;
    }
    
    
}
