/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.especial.PeProgramaTecnologia;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.especial.PeProgramaTecnologiasRemoto;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import com.saviasaludeps.savia.ejb.entidades.PeProgramaTecnologias;
import com.saviasaludeps.savia.ejb.entidades.PeProgramas;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author idbohorquez
 */
@Stateless
@Remote(PeProgramaTecnologiasRemoto.class)
@Local(PeProgramaTecnologiasLocal.class)
public class PeProgramaTecnologiasServicio extends GenericoServicio implements PeProgramaTecnologiasLocal, PeProgramaTecnologiasRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM PeProgramaTecnologias p "
                    + "WHERE p.id > 0 AND p.borrado = 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "idPrograma":
                            strQuery += "AND p.peProgramasId.id = " + (String) e.getValue() + " ";
                            break;
                        case "maTecnologiaCodigo":
                            strQuery += "AND maTecnologiaCodigo = '" + (String) e.getValue() + "' ";
                            break; 
                        case "tipoTecnologia":
                            strQuery += "AND tipoTecnologia = " + (String) e.getValue() + " ";
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
    public List<PeProgramaTecnologia> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<PeProgramaTecnologia> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p FROM PeProgramaTecnologias p "
                    + "WHERE p.borrado = 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "idPrograma":
                            strQuery += "AND p.peProgramasId.id = " + (String) e.getValue() + " ";
                            break;
                        case "maTecnologiaCodigo":
                            strQuery += "AND maTecnologiaCodigo = '" + (String) e.getValue() + "' ";
                            break;  
                        case "tipoTecnologia":
                            strQuery += "AND tipoTecnologia = " + (String) e.getValue() + " ";
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
            List<PeProgramaTecnologias> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (PeProgramaTecnologias tecno : list) {
            listResult.add(castTecnologias(tecno));
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
    public int insertar(PeProgramaTecnologia obj) throws java.lang.Exception {
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
    
     public static PeProgramaTecnologias castNegocioEntidad(PeProgramaTecnologia obj) {
        PeProgramaTecnologias ent = new PeProgramaTecnologias();
        
        ent.setPeProgramasId(new PeProgramas(obj.getPeProgramasId().getId()));
        ent.setTipoTecnologia(obj.getTipoTecnologia());
        ent.setMaTecnologiaId(obj.getMaTecnologiaId());
        ent.setMaTecnologiaCodigo(obj.getMaTecnologiaCodigo());
        ent.setMaTecnologiaValor(obj.getMaTecnologiaValor());
        ent.setBorrado(false);
        ent.setMarcaAutomatico(obj.getMarcaAutomatico());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setDirecciona(obj.getDirecciona());
        return ent;
    }

    @Override
    public List<PeProgramaTecnologia> consultarTecnologiasPrograma(int idPrograma) throws java.lang.Exception {
        List<PeProgramaTecnologia> tecnologias = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("FROM PeProgramaTecnologias p " );
            strQuery.append(" WHERE p.peProgramasId.id = ").append(idPrograma);
            strQuery.append(" AND p.borrado = 0 ");

            List<PeProgramaTecnologias> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (PeProgramaTecnologias tecnologia : list) {
                tecnologias.add(castTecnologias(tecnologia));
            }
        } catch (NoResultException e) {
            tecnologias = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return tecnologias;
    }
    
    private PeProgramaTecnologia castTecnologias(PeProgramaTecnologias tecnologia) {
        PeProgramaTecnologia tecno = new PeProgramaTecnologia();
        tecno.setId(tecnologia.getId());
        tecno.setPeProgramasId(new PePrograma(tecnologia.getPeProgramasId().getId()));
        tecno.setTipoTecnologia(tecnologia.getTipoTecnologia());
        tecno.setMaTecnologiaId(tecnologia.getMaTecnologiaId());
        tecno.setMaTecnologiaCodigo(tecnologia.getMaTecnologiaCodigo());
        tecno.setMaTecnologiaValor(tecnologia.getMaTecnologiaValor());
        tecno.setBorrado(tecnologia.getBorrado());
        tecno.setMarcaAutomatico(tecnologia.getMarcaAutomatico());
        tecno.setDirecciona(tecnologia.getDirecciona());
        tecno.setUsuarioCrea(tecnologia.getUsuarioCrea());
        tecno.setFechaHoraCrea(tecnologia.getFechaHoraCrea());
        tecno.setTerminalCrea(tecnologia.getTerminalCrea());
        tecno.setUsuarioModifica(tecnologia.getUsuarioModifica());
        tecno.setFechaHoraModifica(tecnologia.getFechaHoraModifica());
        tecno.setTerminalModifica(tecnologia.getTerminalModifica());
        return tecno;
    }

    @Override
    public void eliminar(PeProgramaTecnologia obj) throws java.lang.Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE PeProgramaTecnologias a SET a.borrado = 1,  ";            
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica, ";            
            strQuery += "a.usuarioBorra = :usuarioBorra ,";
            strQuery += "a.terminalBorra = :terminalBorra ,";
            strQuery += "a.fechaHoraBorra = :fechaHoraBorra ";            
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());            
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

    @Override
    public PeProgramaTecnologia consultarTecnologiaPrograma(PeProgramaTecnologia objeto) throws java.lang.Exception {
        PeProgramaTecnologia programaTecnologia = new PeProgramaTecnologia();
        try {
            StringBuilder strQuery = new StringBuilder("Select p FROM PeProgramaTecnologias p ");
            strQuery.append(" WHERE p.borrado = 0 ");
            strQuery.append(" AND p.peProgramasId.id = ").append(objeto.getPeProgramasId().getId());
            strQuery.append(" AND p.tipoTecnologia = ").append(objeto.getTipoTecnologia());
            strQuery.append(" AND p.maTecnologiaId = ").append(objeto.getMaTecnologiaId());
            strQuery.append(" AND p.maTecnologiaCodigo = '").append(objeto.getMaTecnologiaCodigo()).append("' ");
            strQuery.append(" AND p.maTecnologiaValor = '").append(objeto.getMaTecnologiaValor()).append("' ");

           PeProgramaTecnologias programaTecnologias = (PeProgramaTecnologias) getEntityManager().createQuery(strQuery.toString()).setMaxResults(1).getSingleResult();
           programaTecnologia = castEntidadNegocio(programaTecnologias);
        } catch (NoResultException e) {
            programaTecnologia = new PeProgramaTecnologia();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return programaTecnologia;
    }
    
     public static PeProgramaTecnologia castEntidadNegocio(PeProgramaTecnologias ent) {
        PeProgramaTecnologia obj = new PeProgramaTecnologia();
        obj.setId(ent.getId());
        obj.setPeProgramasId(new PePrograma(ent.getPeProgramasId().getId()));
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setMaTecnologiaId(ent.getMaTecnologiaId());
        obj.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        obj.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        obj.setBorrado(ent.getBorrado());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setDirecciona(ent.getDirecciona());
        return obj;
    }
     
    @Override
    public void actualizarMarcaAutomatica(PeProgramaTecnologia obj) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE PeProgramaTecnologias p SET p.marcaAutomatico = :marca, ");
            sql.append(" p.direcciona = :direcciona, ");
            sql.append(" p.usuarioModifica = :usuarioModifica, ");
            sql.append(" p.terminalModifica = :terminalModifica, ");
            sql.append(" p.fechaHoraModifica = :fechaHoraModifica ");
            sql.append(" WHERE p.id = :ide ");
            
            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("marca", obj.getMarcaAutomatico());  
            query.setParameter("direcciona", obj.getDirecciona());  
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("ide", obj.getId());
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
     * @param idTecnologias
     * @param idProgramas
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public List<PeProgramaTecnologia> consultarTecnologiasProgramaMarcacionAutomatica(String idTecnologias, String idProgramas) throws java.lang.Exception {
        List<PeProgramaTecnologia> tecnologias = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("FROM PeProgramaTecnologias p " );
            strQuery.append(" WHERE p.maTecnologiaId in (").append(idTecnologias).append(") ");
            strQuery.append(" AND p.peProgramasId.id in (").append(idProgramas).append(") ");
            strQuery.append(" AND p.borrado = 0 AND p.marcaAutomatico = 1 ");

            List<PeProgramaTecnologias> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (PeProgramaTecnologias tecnologia : list) {
                tecnologias.add(castTecnologias(tecnologia));
            }
        } catch (NoResultException e) {
            tecnologias = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return tecnologias;
    }
    
    /**
     * @authot idbohorquez
     * @fechaCreacion 18/08/2022
     * @param idTecnologias
     * @param idProgramas
     * @return List<PeProgramaTecnologia>
     * @throws java.lang.Exception
     */
    @Override
    public List<PeProgramaTecnologia> consultarTecnologiasProgramaDirecciona(String idTecnologias, String idProgramas) throws java.lang.Exception {
        List<PeProgramaTecnologia> tecnologias = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("FROM PeProgramaTecnologias p " );
            strQuery.append(" WHERE p.maTecnologiaId in (").append(idTecnologias).append(") ");
            strQuery.append(" AND p.peProgramasId.id in (").append(idProgramas).append(") ");
            strQuery.append(" AND p.borrado = 0 AND p.direcciona = 1 ");

            List<PeProgramaTecnologias> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (PeProgramaTecnologias tecnologia : list) {
                tecnologias.add(castTecnologias(tecnologia));
            }
        } catch (NoResultException e) {
            tecnologias = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return tecnologias;
    }
    
    @Override
    public PeProgramaTecnologia consultarTecnologiasProgramaDirecciona(Integer idTecnologias, int tipoTecnologia, Integer idProgramas) throws java.lang.Exception {
        PeProgramaTecnologia tecnologia = null;
        try {
            StringBuilder strQuery = new StringBuilder("FROM PeProgramaTecnologias p " );
            strQuery.append(" WHERE p.maTecnologiaId = :maTecnologiaId ");
            strQuery.append(" AND p.tipoTecnologia = ").append(tipoTecnologia);
            strQuery.append(" AND p.peProgramasId.id = :idPrograma ");
            strQuery.append(" AND p.borrado = 0 AND p.direcciona = 1 ");

            PeProgramaTecnologias resultado = (PeProgramaTecnologias) getEntityManager().createQuery(strQuery.toString())
                    .setParameter("maTecnologiaId", idTecnologias)
                    .setParameter("idPrograma", idProgramas)
                    .getSingleResult();
            tecnologia = castTecnologias(resultado);
            
        } catch (NoResultException e) {
            tecnologia = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return tecnologia;
    }
    
}
