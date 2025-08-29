/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.especial.PeIpsPrograma;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.PeIpsProgramas;
import com.saviasaludeps.savia.ejb.entidades.PeProgramas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.especial.PeIpsProgramaRemoto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(PeIpsProgramaRemoto.class)
@Local(PeIpsProgramaLocal.class)
public class PeIpsProgramaServicio extends GenericoServicio implements PeIpsProgramaLocal, PeIpsProgramaRemoto {
    
    @Override
    public List<PeIpsPrograma> consultarPorPrograma(int idPrograma) throws Exception {
        List<PeIpsPrograma> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p FROM PeIpsProgramas p "
                    + "WHERE p.peProgramasId.id = :idPrograma "
                    + "ORDER BY p.id ";
            
            List<PeIpsProgramas> list = getEntityManager().createQuery(strQuery)
                    .setParameter("idPrograma", idPrograma)
                    .getResultList();
            for (PeIpsProgramas per : list) {
                listResult.add(castEntidadNegocio(per));
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
    public List<PeIpsPrograma> consultarPorProgramaEIPS(int idPrograma, int idSede) throws Exception {
        List<PeIpsPrograma> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p FROM PeIpsProgramas p "
                    + "WHERE p.peProgramasId.id = :idPrograma "
                    + "AND p.cntPrestadorSedesId.id = :idSede "
                    + "AND p.activo = :activo "
                    + "ORDER BY p.id ";
            
            List<PeIpsProgramas> list = getEntityManager().createQuery(strQuery)
                    .setParameter("idPrograma", idPrograma)
                    .setParameter("idSede", idSede)
                    .setParameter("activo", true)
                    .getResultList();
            for (PeIpsProgramas per : list) {
                listResult.add(castEntidadNegocio(per));
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
    public int insertar(PeIpsPrograma obj) throws Exception {
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
    public void actualizar(PeIpsPrograma obj) throws Exception {
        try {
            String sql = "UPDATE PeIpsProgramas SET fechaInicio = :fechaInicio, "
                    + "fechaFin = :fechaFin, "
                    + "activo = :activo, "
                    + "usuariosCrea = :usuariosCrea, "
                    + "terminalCrea = :terminalCrea, "
                    + "fechaHoraCrea = :fechaHoraCrea, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica, "
                    + "cntPrestadorSedesId.id = :cntPrestadorSedesId, "
                    + "peProgramasId.id = :peProgramasId "
                    + "WHERE id = :id";
            
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("fechaInicio", obj.getFechaInicio());
            query.setParameter("fechaFin", obj.getFechaFin());
            query.setParameter("activo", obj.isActivo());
            query.setParameter("usuariosCrea", obj.getUsuarioCrea());
            query.setParameter("terminalCrea", obj.getTerminalCrea());
            query.setParameter("fechaHoraCrea", obj.getFechaHoraCrea());            
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("cntPrestadorSedesId", obj.getCntPrestadorSedesId().getId());
            query.setParameter("peProgramasId", obj.getPeProgramasId().getId());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {
            
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    
    @Override
    public PeIpsPrograma eliminar(int id) throws Exception {
        PeIpsPrograma obj = null;
        try {
            PeIpsProgramas ent = getEntityManager().find(PeIpsProgramas.class, id);
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
    public List<PeIpsPrograma> consultarPorProgramaActivo(int idPrograma) throws Exception {
        List<PeIpsPrograma> listResult = new ArrayList<>();
        try {
            String strQuery = "SELECT p FROM PeIpsProgramas p "
                    + "WHERE p.peProgramasId.id = :idPrograma "
                    + "AND p.activo = :activo "
                    + "AND p.fechaInicio <= :fecha "
                    + "AND P.fechaFin >= :fecha "
                    + "ORDER BY p.id ";
            
            List<PeIpsProgramas> list = getEntityManager().createQuery(strQuery)
                    .setParameter("idPrograma", idPrograma)
                    .setParameter("activo", true)
                    .setParameter("fecha", new Date())
                    .getResultList();
            for (PeIpsProgramas per : list) {
                listResult.add(castEntidadNegocio(per));
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
    
    
    public static PeIpsPrograma castEntidadNegocio(PeIpsProgramas ent) {
        PeIpsPrograma obj = new PeIpsPrograma();
        
        obj.setActivo(ent.getActivo());
        if (ent.getCntPrestadorSedesId() != null) {
            CntPrestadorSede prestador = new CntPrestadorSede(ent.getCntPrestadorSedesId().getId());
            prestador.setCodigoPrestador(ent.getCntPrestadorSedesId().getCodigoPrestador());
            prestador.setNombreSede(ent.getCntPrestadorSedesId().getNombre());
            prestador.setCodigoHabilitacionSede(ent.getCntPrestadorSedesId().getCodigoHabilitacion());
            obj.setCntPrestadorSedesId(prestador);
        }
        obj.setFechaFin(ent.getFechaFin());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setFechaInicio(ent.getFechaInicio());
        obj.setId(ent.getId());
        if (ent.getPeProgramasId() != null) {
            obj.setPeProgramasId(new PePrograma(ent.getPeProgramasId().getId()));
        }
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setUsuarioCrea(ent.getUsuariosCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        
        return obj;
    }
    
    public static PeIpsProgramas castNegocioEntidad(PeIpsPrograma obj) {
        PeIpsProgramas ent = new PeIpsProgramas();
        
        ent.setActivo(obj.isActivo());
        if (obj.getCntPrestadorSedesId() != null) {
            ent.setCntPrestadorSedesId(new CntPrestadorSedes(obj.getCntPrestadorSedesId().getId()));
        }
        ent.setFechaFin(obj.getFechaFin());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        ent.setFechaInicio(obj.getFechaInicio());
        ent.setId(obj.getId());
        if (obj.getPeProgramasId() != null) {
            ent.setPeProgramasId(new PeProgramas(obj.getPeProgramasId().getId()));
        }
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        ent.setUsuariosCrea(obj.getUsuarioCrea());
        
        return ent;
    }

    @Override
    public List<PePrograma> consultarProgramaPorPrestador(int idPrestador) throws Exception {
        List<PePrograma> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p FROM PeIpsProgramas p "
                    + "WHERE p.cntPrestadorSedesId.cntPrestadoresId.id = :idPrestador "
                    + " AND p.activo = 1 "
                    + "ORDER BY p.id ";
            
            List<PeIpsProgramas> list = getEntityManager().createQuery(strQuery)
                    .setParameter("idPrestador", idPrestador)
                    .getResultList();
            for (PeIpsProgramas per : list) {
                listResult.add(castEntidadNegocioPrograma(per.getPeProgramasId()));
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

    private PePrograma castEntidadNegocioPrograma(PeProgramas entidad) {
        PePrograma negocio = new PePrograma();
        negocio.setId(entidad.getId());
        negocio.setCodigoPrograma(entidad.getCodigoPrograma());
        return negocio;
    }
}
