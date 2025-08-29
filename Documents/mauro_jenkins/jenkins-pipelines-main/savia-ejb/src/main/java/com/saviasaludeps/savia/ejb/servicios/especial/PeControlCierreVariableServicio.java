/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.saviasaludeps.savia.dominio.especial.PeCierreCarga;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.PeCierreCargas;
import com.saviasaludeps.savia.ejb.entidades.PeProgramas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.especial.PeControlCierreVariablesRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jdlopez
 */
@Stateless
@Remote(PeControlCierreVariablesRemoto.class)
@Local(PeControlCierreVariablesLocal.class)
public class PeControlCierreVariableServicio extends GenericoServicio implements PeControlCierreVariablesRemoto, PeControlCierreVariablesLocal {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
       int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM PeCierreCargas c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "programa":
                            strQuery += "AND c.peProgramasId.id = " +  e.getValue() + " ";
                            break;
                         case "periodo":
                            strQuery += "AND c.periodo = " +  e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (NumberFormatException e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

    @Override
    public List<PeCierreCarga> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<PeCierreCarga> listResult = new ArrayList();
        try {
            String strQuery = "FROM PeCierreCargas c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "programa":
                            strQuery += "AND c.peProgramasId.id = " +  e.getValue() + " ";
                            break;
                         case "periodo":
                            strQuery += "AND c.periodo = " +  e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "c." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "c.id DESC ";
            }
            List<PeCierreCargas> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (PeCierreCargas ent : list) {
                listResult.add(castEntidadNegocio(ent));
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
    public PeCierreCarga consultar(int id) throws java.lang.Exception {
        PeCierreCarga objResult = new PeCierreCarga();

        try {
            objResult = castEntidadNegocio((PeCierreCargas) getEntityManager().find(PeCierreCargas.class, id));
        } catch (NoResultException e) {
            objResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }

        return objResult;
    }
    
     @Override
    public PeCierreCarga consultarIdProgramaPeriodo(int idPrograma, int periodo) throws java.lang.Exception {
        PeCierreCarga consulta = null;
        try {
            String sql = "SELECT v FROM PeCierreCargas v WHERE v.peProgramasId.id = :idPrograma "
                    + "AND v.periodo = :periodo "
                    + "AND YEAR(v.fechaHoraCrea) = YEAR(CURRENT_DATE)";

            PeCierreCargas resultado = (PeCierreCargas) getEntityManager().createQuery(sql)
                    .setParameter("idPrograma", idPrograma) 
                    .setParameter("periodo", periodo)
                    .getSingleResult();

            if (resultado == null) {
                return consulta;
            }
            consulta = this.castEntidadNegocio(resultado);

        } catch (NoResultException e) {
            consulta = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return consulta;
    }

    @Override
    public int insertar(PeCierreCarga obj) throws java.lang.Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(this.castNegocioEntidad(obj)).getId();
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
    public void actualizar(PeCierreCarga obj) throws java.lang.Exception {
        try {
           
            String sql = "UPDATE PeCierreCargas SET motivo = :motivo, "
                    + "fechaHoraDesde = :fechaHoraDesde, "
                    + "fechaHoraHasta = :fechaHoraHasta, "
                    + "usuarioModifica = :usuarioModifica,"
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id";

            Query query = getEntityManager().createQuery(sql);
            query.setParameter("motivo", obj.getMotivo());
            query.setParameter("fechaHoraDesde", obj.getFechaHoraDesde());
            query.setParameter("fechaHoraHasta", obj.getFechaHoraHasta());
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


    private PeCierreCarga castEntidadNegocio(PeCierreCargas ent) {
        PeCierreCarga obj = new PeCierreCarga();
        obj.setId(ent.getId());

        if (ent.getPeProgramasId() != null) {
            PePrograma pePrograma = new PePrograma(ent.getPeProgramasId().getId());
            pePrograma.setDescripcionPrograma(ent.getPeProgramasId().getDescripcionPrograma());
            obj.setPrograma(pePrograma);
        }
        obj.setMotivo(ent.getMotivo());
        obj.setPeriodo(ent.getPeriodo());
        obj.setFechaHoraDesde(ent.getFechaHoraDesde());
        obj.setFechaHoraHasta(ent.getFechaHoraHasta());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        return obj;
    }

    private PeCierreCargas castNegocioEntidad(PeCierreCarga obj) {
        PeCierreCargas ent = new PeCierreCargas();
        ent.setId(obj.getId());
        ent.setPeProgramasId(new PeProgramas(obj.getPrograma().getId()));
        ent.setMotivo(obj.getMotivo());
        ent.setPeriodo(obj.getPeriodo());
        ent.setFechaHoraDesde(obj.getFechaHoraDesde());
        ent.setFechaHoraHasta(obj.getFechaHoraHasta());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        ent.setTerminalModifica(obj.getTerminalModifica());
        return ent;
    }   
}
