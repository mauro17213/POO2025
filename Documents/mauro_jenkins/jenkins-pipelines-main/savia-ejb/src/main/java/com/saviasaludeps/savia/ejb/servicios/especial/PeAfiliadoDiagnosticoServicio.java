/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.saviasaludeps.savia.dominio.especial.PeAfiliadoDiagnostico;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadoDiagnosticoRemoto;
import com.saviasaludeps.savia.ejb.entidades.PeAfiliadoDiagnosticos;
import com.saviasaludeps.savia.ejb.entidades.PeAfiliadosProgramas;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author idbohorquez
 */
@Stateless
@Remote(PeAfiliadoDiagnosticoRemoto.class)
@Local(PeAfiliadoDiagnosticoLocal.class)
public class PeAfiliadoDiagnosticoServicio extends GenericoServicio implements PeAfiliadoDiagnosticoLocal,PeAfiliadoDiagnosticoRemoto {

     /**
     * Funcion encargada de insertar un nuevo diagnostico
     * @author idbohorquez
     * @creacion 08/11/2022
     * @param afiliadoDiagnostico
     * @return Integer
     * @throws Exception
     */
    @Override
    public Integer insertar(PeAfiliadoDiagnostico afiliadoDiagnostico) throws java.lang.Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(afiliadoDiagnostico)).getId();
            afiliadoDiagnostico.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }
    
    /**
     * Funcion encargada de eliminar registros de diagnosticos
     * @author idbohorquez
     * @creacion 08/11/2022
     * @param afiliadoDiagnostico
     * @throws Exception
     */
    @Override
    public void eliminar(PeAfiliadoDiagnostico diagnostico) throws java.lang.Exception {
        try {
            PeAfiliadoDiagnosticos ent = getEntityManager().find(PeAfiliadoDiagnosticos.class, diagnostico.getId());
            if (ent != null) {
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizar(PeAfiliadoDiagnostico obj) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PeAfiliadoDiagnosticos SET maDiagnosticosId = :maDiagnosticosId, ");
            sql.append("maDiagnosticosCodigo = :maDiagnosticosCodigo,  ");
            sql.append("maDiagnosticosValor = :maDiagnosticosValor,  ");
            sql.append("principal = :principal,  ");
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("maDiagnosticosId", obj.getMaDiagnosticosId());
            query.setParameter("maDiagnosticosCodigo", obj.getMaDiagnosticosCodigo());
            query.setParameter("maDiagnosticosValor", obj.getMaDiagnosticosValor());
            query.setParameter("principal", obj.getPrincipal());
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
       
    
    /**
     * Funcion encargada de consultar el listado de diagnosticos existentes para un afiliado
     * en el programa
     * @author idbohorquez
     * @creacion 08/11/2022
     * @param afiliadoDiagnostico
     * @return List<PeAfiliadoDiagnostico>
     * @throws Exception
     */
    @Override
    public List<PeAfiliadoDiagnostico> getDialDiagnosticosAfiliadoPrograma(int afiliadoPrograma) throws java.lang.Exception {
        List<PeAfiliadoDiagnostico> diagnosticos = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("FROM PeAfiliadoDiagnosticos p " );
            strQuery.append(" WHERE p.peAfiliadosProgramasId.id = ").append(afiliadoPrograma);           

            List<PeAfiliadoDiagnosticos> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (PeAfiliadoDiagnosticos item : list) {
                diagnosticos.add(castNegocioEntidad(item));
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
     * Funcion encargada de consultar diagnostico por idAfiliado programa y por
     * el codigo el diagnostico
     * @author idbohorquez
     * @creacion 15/11/2022
     * @param idAfiliadoPrograma 
     * @param maeCodigoDiagnostico
     * @return PeAfiliadoDiagnostico
     * @throws Exception
     */
    @Override
    public PeAfiliadoDiagnostico consultarAfiliadoPrograma(Integer idAfiliadoPrograma, String maeCodigoDiagnostico) throws java.lang.Exception {
        PeAfiliadoDiagnostico diagnostico = null;
        try {
            StringBuilder strQuery = new StringBuilder("FROM PeAfiliadoDiagnosticos p " );
            strQuery.append(" WHERE p.peAfiliadosProgramasId.id = :peAfiliadosProgramasId ");           
            strQuery.append(" AND p.maDiagnosticosCodigo = :maDiagnosticosCodigo");           

            PeAfiliadoDiagnosticos diagnos = (PeAfiliadoDiagnosticos) getEntityManager().createQuery(strQuery.toString())
                    .setParameter("peAfiliadosProgramasId", idAfiliadoPrograma)
                    .setParameter("maDiagnosticosCodigo", maeCodigoDiagnostico)
                    .setMaxResults(1)
                    .getSingleResult();
            
            diagnostico = castNegocioEntidad(diagnos);
        } catch (NoResultException e) {
            diagnostico = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return diagnostico;
    }
    
    /**
     * Funcion encargada de consultar el diagnostico principal
     * @author idbohorquez
     * @creacion 15/06/2023
     * @param idAfiliadoPrograma
     * @return PeAfiliadoDiagnostico
     * @throws Exception
     */
    @Override
    public PeAfiliadoDiagnostico consultarDiagnosticoPrincipal(Integer idAfiliadoPrograma) throws java.lang.Exception {
        PeAfiliadoDiagnostico diagnostico = null;
        try {
            StringBuilder strQuery = new StringBuilder("FROM PeAfiliadoDiagnosticos p " );
            strQuery.append(" WHERE p.peAfiliadosProgramasId.id = :peAfiliadosProgramasId ");           
            strQuery.append(" AND p.principal = 1 ");           

            PeAfiliadoDiagnosticos diagnos = (PeAfiliadoDiagnosticos) getEntityManager().createQuery(strQuery.toString())
                    .setParameter("peAfiliadosProgramasId", idAfiliadoPrograma)
                    .setMaxResults(1)
                    .getSingleResult();
            
            diagnostico = castNegocioEntidad(diagnos);
        } catch (NoResultException e) {
            diagnostico = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return diagnostico;
    }
    
     /**
     * Funcion encargada de remover el diagnostico principal del afilaido programa
     * @author idbohorquez
     * @creacion 15/06/2023
     * @param obj
     * @throws Exception
     */
    @Override
    public void removerDiagnosticoPrincipal(PeAfiliadoDiagnostico obj) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PeAfiliadoDiagnosticos SET  ");
            sql.append("principal = 0, ");
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE peAfiliadosProgramasId.id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getPeAfiliadosProgramasId().getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

     /**
     * Funcion encargada de castear la informacion del dominio a la entidad
     * @author idbohorquez
     * @creacion 08/11/2022
     * @param afiliadoDiagnostico
     * @return Integer
     * @throws Exception
     */
    public static PeAfiliadoDiagnosticos castNegocioEntidad(PeAfiliadoDiagnostico obj) {
        PeAfiliadoDiagnosticos ent = new PeAfiliadoDiagnosticos();
        ent.setPeAfiliadosProgramasId(new PeAfiliadosProgramas(obj.getPeAfiliadosProgramasId().getId()));
        ent.setMaDiagnosticosId(obj.getMaDiagnosticosId());
        ent.setMaDiagnosticosCodigo(obj.getMaDiagnosticosCodigo());
        ent.setMaDiagnosticosValor(obj.getMaDiagnosticosValor());
        ent.setPrincipal(obj.getPrincipal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFehaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());        
        return ent;
    }   
    
    
    public static PeAfiliadoDiagnostico castNegocioEntidad(PeAfiliadoDiagnosticos ent) {
        PeAfiliadoDiagnostico obj = new PeAfiliadoDiagnostico();
        obj.setId(ent.getId());
        obj.setPeAfiliadosProgramasId(new PeAfiliadosPrograma(ent.getPeAfiliadosProgramasId().getId()));
        obj.setMaDiagnosticosId(ent.getMaDiagnosticosId());
        obj.setMaDiagnosticosCodigo(ent.getMaDiagnosticosCodigo());
        obj.setMaDiagnosticosValor(ent.getMaDiagnosticosValor());
        obj.getPeAfiliadosProgramasId().setMaDiagnosticoPrincipal(new MaDiagnostico(ent.getPeAfiliadosProgramasId().getMaDiagnosticoPrincipalId()));
        obj.setPrincipal(ent.getPrincipal());
               
        return obj;
    }

    
    
    
}
