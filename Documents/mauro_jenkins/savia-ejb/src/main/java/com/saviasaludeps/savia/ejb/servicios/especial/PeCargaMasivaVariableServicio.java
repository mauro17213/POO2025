/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.negocio.especial.PeCargaMasivaVariableRemoto;
import com.saviasaludeps.savia.dominio.especial.PeCargaVariable;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.entidades.PeCargasVariables;
import com.saviasaludeps.savia.ejb.entidades.PeProgramas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
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
@Remote(PeCargaMasivaVariableRemoto.class)
@Local(PeCargaMasivaVariableLocal.class)
public class PeCargaMasivaVariableServicio extends GenericoServicio implements PeCargaMasivaVariableRemoto, PeCargaMasivaVariableLocal {

    @Override
    public PeCargaVariable consultar(int id) throws java.lang.Exception {
        PeCargaVariable objResult = new PeCargaVariable();

        try {
            objResult = castEntidadNegocio((PeCargasVariables) getEntityManager().find(PeCargasVariables.class, id));
        } catch (NoResultException e) {
            objResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return objResult;
    }

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM PeCargasVariables c WHERE c.id > 0 ";
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += " AND c.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id = " + e.getValue() + " ";
                            break;
                        case "archivo":
                            strQuery += "AND c.archivo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND c.estado = " + e.getValue() + " ";
                            break;
                        case "periodoCargue":
                            strQuery += "AND c.periodoCargue = " + e.getValue() + " ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND c.fechaHoraInicio = '" + e.getValue().toString() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND c.usuarioCrea like '%" + (String) e.getValue() + "%' ";
                            break;
                        case "detalle":
                            strQuery += "AND c.detalle = '" + e.getValue() + "' ";
                            break;
                        case "programa":
                            strQuery += "AND c.peProgramasId.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            Query query = getEntityManager().createQuery(strQuery);
//            if (paramConsulta.getEmpresaId() != null) {
//                query.setParameter("empresa_id", paramConsulta.getEmpresaId());
//            }
            cant = (int) (long) query.getSingleResult();
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
    public List<PeCargaVariable> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<PeCargaVariable> listResult = new ArrayList();
        try {
            String strQuery = "SELECT c FROM PeCargasVariables c WHERE c.id > 0 ";
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += " AND c.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id = " + e.getValue() + " ";
                            break;
                        case "archivo":
                            strQuery += "AND c.archivo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND c.estado = " + e.getValue() + " ";
                            break;
                        case "periodoCargue":
                            strQuery += "AND c.periodoCargue = " + e.getValue() + " ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND c.fechaHoraInicio = '" + e.getValue().toString() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND c.usuarioCrea like '%" + (String) e.getValue() + "%' ";
                            break;
                        case "detalle":
                            strQuery += "AND c.detalle = '" + e.getValue() + "' ";
                            break;
                        case "programa":
                            strQuery += "AND c.peProgramasId.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    case "programa":
                        strQuery += "c.peProgramasId.id "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        if (!paramConsulta.isAscendente()) {
                            strQuery += ", c.id DESC";
                        }
                        break;
                    default:
                        strQuery += "c." + paramConsulta.getOrden() + " "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        if (!paramConsulta.isAscendente()) {
                            strQuery += ", c.id DESC";
                        }
                        break;
                }
            } else {
                strQuery += "c.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
//            if (paramConsulta.getEmpresaId() != null) {
//                query.setParameter("empresa_id", paramConsulta.getEmpresaId());
//            }
            query.setFirstResult(paramConsulta.getPrimerRegistro());
            query.setMaxResults(paramConsulta.getRegistrosPagina());
            List<PeCargasVariables> list = query.getResultList();
            for (PeCargasVariables per : list) {
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
    public int insertar(PeCargaVariable obj) throws Exception {
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
    public PeCargaVariable consultarCargasSiguientes() throws java.lang.Exception {
        PeCargaVariable resultado = null;
        try {

            String strQuery = "SELECT c FROM PeCargasVariables c WHERE c.id > 0 AND c.estado = 0 ";
            Query query = getEntityManager().createQuery(strQuery).setMaxResults(1);
            PeCargasVariables res = (PeCargasVariables) query.getSingleResult();
            resultado = castEntidadNegocio(res);
        } catch (NoResultException e) {
            resultado = null;
        } finally {
            cerrarEntityManager();
        }
        return resultado;
    }

    @Override
    public void actualizar(PeCargaVariable obj) throws java.lang.Exception {
        try {
            String sql = "UPDATE PeCargasVariables SET "
                    + "estado = :estado, "
                    + "registros = :registros, "
                    + "exitosos = :exitosos, "
                    + "fallidos = :fallidos, "
                    + "detalle = :detalle, "
                    + "respExiste = :respExiste, "
                    + "fechaHoraFin = :fechaHoraFin "
                    + "WHERE id = :id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("registros", obj.getRegistros());
            query.setParameter("exitosos", obj.getExitosos());
            query.setParameter("fallidos", obj.getFallidos());
            query.setParameter("detalle", obj.getDetalle());
            query.setParameter("respExiste", obj.getRespExiste());
            query.setParameter("fechaHoraFin", obj.getFechaHoraFin());
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
    public void actualizarDetalle(PeCargaVariable obj) throws java.lang.Exception {
        try {
            StringBuilder sql = new StringBuilder("UPDATE PeCargasVariables SET ");
            sql.append("estado = :estado, ");
            sql.append("detalle = :detalle, ");
            sql.append("respExiste = :respExiste ");
            sql.append("WHERE id = :id");
            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("detalle", obj.getDetalle());
            query.setParameter("respExiste", obj.getRespExiste());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public boolean existeCargaEnColaOProcesoPorIdPrestador(int idPrestador) throws java.lang.Exception {
        try {
            String jpql = "SELECT COUNT(c) FROM PeCargasVariables c "
                    + "WHERE c.cntPrestadorSedesId.id = :idPrestador AND c.estado IN (0, 1)";
            Query query = getEntityManager().createQuery(jpql);
            query.setParameter("idPrestador", idPrestador);

            Long count = (Long) query.getSingleResult();
            return count > 0;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return false;
    }

    @Override
    public List<PeCargaVariable> consultarCargasPorPeriodo(int idPeriodo, int idEmpresa) throws java.lang.Exception {
        List<PeCargaVariable> listaCargas = new ArrayList<>();
        try {
            String jpql = "SELECT c FROM PeCargasVariables c "
                    + "WHERE c.periodoCargue = :periodo "
                    + "AND c.gnEmpresasId.id = :idEmpresa";  

            Query query = getEntityManager().createQuery(jpql);
            query.setParameter("periodo", idPeriodo);
            query.setParameter("idEmpresa", idEmpresa);

            List<PeCargasVariables> entidades = query.getResultList();
            for (PeCargasVariables entidad : entidades) {
                listaCargas.add(castEntidadNegocio(entidad));
            }
        } catch (Exception e) {
            Exception("consultarCargasPorPeriodo", e);
        } finally {
            cerrarEntityManager();
        }
        return listaCargas;
    }

    private PeCargasVariables castNegocioEntidad(PeCargaVariable obj) {
        PeCargasVariables ent = new PeCargasVariables();
        ent.setId(obj.getId());
        if (obj.getEmpresa() != null) {
            ent.setGnEmpresasId(new GnEmpresas(obj.getEmpresa().getId()));
        }
        ent.setCntPrestadorSedesId(new CntPrestadorSedes(obj.getPrestadorSede().getId()));
        ent.setPeProgramasId(new PeProgramas(obj.getPrograma().getId()));
        ent.setNombre(obj.getNombre());
        ent.setArchivo(obj.getArchivo());
        ent.setRuta(obj.getRuta());
        ent.setRegistros(obj.getRegistros());
        ent.setDetalle(obj.getDetalle());
        ent.setEstado(obj.getEstado());
        ent.setExitosos(obj.getExitosos());
        ent.setFallidos(obj.getFallidos());
        ent.setPeriodoCargue(obj.getPeriodoCargue());
        ent.setFechaHoraInicio(obj.getFechaHoraInicio());
        ent.setFechaHoraFin(obj.getFechaHoraFin());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setExiste(obj.getExiste());
        ent.setRespRuta(obj.getRespRuta());
        ent.setRespNombre(obj.getRespNombre());
        ent.setRespExiste(obj.getRespExiste());
        ent.setRespArchivo(obj.getRespArchivo());
        return ent;
    }

    public PeCargaVariable castEntidadNegocio(PeCargasVariables ent) {
        PeCargaVariable obj = new PeCargaVariable();
        obj.setId(ent.getId());
        if (ent.getGnEmpresasId() != null) {
            obj.setEmpresa(new Empresa(ent.getGnEmpresasId().getId()));
        }
        if (ent.getPeProgramasId() != null) {
            PePrograma pePrograma = new PePrograma(ent.getPeProgramasId().getId());
            pePrograma.setDescripcionPrograma(ent.getPeProgramasId().getDescripcionPrograma());
            obj.setPrograma(pePrograma);
        }
        if (ent.getCntPrestadorSedesId() != null) {
            CntPrestadorSede prestadorSede = new CntPrestadorSede(ent.getCntPrestadorSedesId().getId());
            prestadorSede.setCodigoHabilitacionSede(ent.getCntPrestadorSedesId().getCodigoHabilitacion());
            prestadorSede.setUbicacionId(ent.getCntPrestadorSedesId().getUbicacionId());
            prestadorSede.setDireccion(ent.getCntPrestadorSedesId().getDireccion());
            prestadorSede.setNombreSede(ent.getCntPrestadorSedesId().getNombre());
            prestadorSede.setCodigoSede(ent.getCntPrestadorSedesId().getCodigo());

            CntPrestador prestador = new CntPrestador(ent.getCntPrestadorSedesId().getCntPrestadoresId().getId());
            prestador.setNumeroDocumento(ent.getCntPrestadorSedesId().getCntPrestadoresId().getNumeroDocumento());
            prestadorSede.setCntPrestador(prestador);
            obj.setPrestadorSede(prestadorSede);
        }
        obj.setNombre(ent.getNombre());
        obj.setArchivo(ent.getArchivo());
        obj.setRuta(ent.getRuta());
        obj.setRegistros(ent.getRegistros());
        obj.setDetalle(ent.getDetalle());
        obj.setEstado(ent.getEstado());
        obj.setExitosos(ent.getExitosos());
        obj.setFallidos(ent.getFallidos());
        obj.setPeriodoCargue(ent.getPeriodoCargue());
        obj.setFechaHoraInicio(ent.getFechaHoraInicio());
        obj.setFechaHoraFin(ent.getFechaHoraFin());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setExiste(ent.getExiste());
        obj.setRespRuta(ent.getRespRuta());
        obj.setRespNombre(ent.getRespNombre());
        obj.setRespExiste(ent.getRespExiste());
        obj.setRespArchivo(ent.getRespArchivo());
        return obj;
    }

    @Override
    public List<PeCargaVariable> consultarCargasPorPeriodoSede(int idPeriodo, int idPrestador) throws java.lang.Exception {
        List<PeCargaVariable> listaCargas = new ArrayList<>();
        try {
            String jpql = "SELECT c FROM PeCargasVariables c "
                    + "WHERE c.periodoCargue = :periodo "
                    + "AND c.cntPrestadorSedesId.id = :idPrestador";  

            Query query = getEntityManager().createQuery(jpql);
            query.setParameter("periodo", idPeriodo);
            query.setParameter("idPrestador", idPrestador);

            List<PeCargasVariables> entidades = query.getResultList();
            for (PeCargasVariables entidad : entidades) {
                listaCargas.add(castEntidadNegocio(entidad));
            }
        } catch (Exception e) {
            Exception("consultarCargasPorPeriodo", e);
        } finally {
            cerrarEntityManager();
        }
        return listaCargas;
    }
}
 