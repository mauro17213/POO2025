/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.especial.PeCarga;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.entidades.PeCargas;
import com.saviasaludeps.savia.ejb.entidades.PeProgramas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.especial.PeCargaMasivaRemoto;
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
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(PeCargaMasivaRemoto.class)
@Local(PeCargaMasivaLocal.class)
public class PeCargaMasivaServicio extends GenericoServicio implements PeCargaMasivaRemoto, PeCargaMasivaLocal {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM PeCargas p WHERE p.id > 0 AND p.tipo = 0 ";
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += " AND p.gnEmpresasId.id = :empresa_id ";
            } 
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "archivo":
                            strQuery += "AND p.archivo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND p.fechaHoraInicio = '" + e.getValue().toString() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea like '%" + (String) e.getValue() + "%' ";
                            break;
                        case "detalle":
                            strQuery += "AND p.detalle = '" + e.getValue() + "' ";
                            break;
                        case "programa":
                            strQuery += "AND p.peProgramasId.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getEmpresaId() != null) {
                query.setParameter("empresa_id", paramConsulta.getEmpresaId());
            }
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
    public List<PeCarga> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<PeCarga> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p FROM PeCargas p WHERE p.id > 0 AND p.tipo = 0 ";
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += " AND p.gnEmpresasId.id = :empresa_id ";
            } 
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "archivo":
                            strQuery += "AND p.archivo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND p.fechaHoraInicio = '" + e.getValue().toString() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea like '%" + (String) e.getValue() + "%' ";
                            break;
                        case "detalle":
                            strQuery += "AND p.detalle = '" + e.getValue() + "' ";
                            break;
                        case "programa":
                            strQuery += "AND p.peProgramasId.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    case "programa":
                        strQuery += "p.peProgramasId.id "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    default:
                        strQuery += "p." + paramConsulta.getOrden() + " "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                }
            } else {
                strQuery += "p.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getEmpresaId() != null) {
                query.setParameter("empresa_id", paramConsulta.getEmpresaId());
            }
            query.setFirstResult(paramConsulta.getPrimerRegistro());
            query.setMaxResults(paramConsulta.getRegistrosPagina());
            List<PeCargas> list = query.getResultList();
            for (PeCargas per : list) {
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
    public int cantidadListaCargaDiagnosticoTecnologia(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM PeCargas p WHERE p.id > 0 and p.tipo in (1,2) ";
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += " AND p.gnEmpresasId.id = :empresa_id ";
            } 
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "archivo":
                            strQuery += "AND p.archivo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND p.fechaHoraInicio = '" + e.getValue().toString() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea like '%" + (String) e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += "AND p.tipo = " + e.getValue() + " ";
                            break;
                        case "detalle":
                            strQuery += "AND p.detalle = '" + e.getValue() + "' ";
                            break;
                        case "programa":
                            strQuery += "AND p.peProgramasId.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getEmpresaId() != null) {
                query.setParameter("empresa_id", paramConsulta.getEmpresaId());
            }
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
    public List<PeCarga> consultarListaCargaDiagnosticoTecnologia(ParamConsulta paramConsulta) throws Exception {
        List<PeCarga> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "SELECT p FROM PeCargas p WHERE p.id > 0 AND p.tipo in (1,2) ";
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += " AND p.gnEmpresasId.id = :empresa_id ";
            } 
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "archivo":
                            strQuery += "AND p.archivo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND p.fechaHoraInicio = '" + e.getValue().toString() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea like '%" + (String) e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += "AND p.tipo = " + e.getValue() + " ";
                            break;
                        case "detalle":
                            strQuery += "AND p.detalle = '" + e.getValue() + "' ";
                            break;
                        case "programa":
                            strQuery += "AND p.peProgramasId.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    case "programa":
                        strQuery += "p.peProgramasId.id "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    default:
                        strQuery += "p." + paramConsulta.getOrden() + " "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                }
            } else {
                strQuery += "p.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getEmpresaId() != null) {
                query.setParameter("empresa_id", paramConsulta.getEmpresaId());
            }
            query.setFirstResult(paramConsulta.getPrimerRegistro());
            query.setMaxResults(paramConsulta.getRegistrosPagina());
            List<PeCargas> list = query.getResultList();
            for (PeCargas per : list) {
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
    public int insertar(PeCarga obj) throws Exception {
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
    public void actualizar(PeCarga obj) throws Exception {
        try {
            String sql = "UPDATE PeCargas SET "
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
    public PeCarga consultar(int id) throws Exception {
        PeCarga objResult = new PeCarga();

        try {
            objResult = castEntidadNegocio((PeCargas) getEntityManager().find(PeCargas.class, id));
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
    public void actualizarArchivo(PeCarga obj) throws Exception {
        try {
            String sql = "UPDATE PeCargas "
                    + "SET archivo = :archivo "
                    + "WHERE id = :id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("archivo", obj.getArchivo());
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
    public List<PeCarga> consultarCargasSiguientes(int numRegistros)  {
        List<PeCarga> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p FROM PeCargas p WHERE p.id > 0 AND p.estado = 0 ";            
            Query query = getEntityManager().createQuery(strQuery).setMaxResults(numRegistros);  
            List<PeCargas> list = query.getResultList();
            for (PeCargas per : list) {
                listResult.add(castEntidadNegocio(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
    @Override
    public PeCarga consultarCargasSiguientes()  {
        PeCarga resultado = null;
        try {
            String strQuery = "SELECT p FROM PeCargas p WHERE p.id > 0 AND p.estado = 0 ";            
            Query query = getEntityManager().createQuery(strQuery).setMaxResults(1);  
            PeCargas res = (PeCargas) query.getSingleResult();
            resultado = castEntidadNegocio(res);
        } catch (NoResultException e) {
            resultado = null;
        } finally {
            cerrarEntityManager();
        }
        return resultado;
    }
    
    @Override
    public void actualizarDetalle(PeCarga obj) throws Exception {
        try {
            StringBuilder sql = new StringBuilder("UPDATE PeCargas SET ");
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

    public static PeCarga castEntidadNegocio(PeCargas ent) {
        PeCarga obj = new PeCarga();
        obj.setId(ent.getId());
        if (ent.getGnEmpresasId() != null) {
            obj.setEmpresa(new Empresa(ent.getGnEmpresasId().getId()));
        }
        obj.setTipo(ent.getTipo());
        obj.setNombre(ent.getNombre());
        obj.setArchivo(ent.getArchivo());
        obj.setRuta(ent.getRuta());
        obj.setRegistros(ent.getRegistros());
        obj.setDetalle(ent.getDetalle());
        obj.setEstado(ent.getEstado());
        obj.setExitosos(ent.getExitosos());
        obj.setFallidos(ent.getFallidos());
        obj.setFechaHoraInicio(ent.getFechaHoraInicio());
        obj.setFechaHoraFin(ent.getFechaHoraFin());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        if (ent.getPeProgramasId() != null) {
            obj.setIdPrograma(ent.getPeProgramasId().getId());
            obj.setPrograma(ent.getPeProgramasId().getDescripcionPrograma());
        } else {
            obj.setIdPrograma(0);
            obj.setPrograma("");
        }
        obj.setExiste(ent.getExiste());
        obj.setRespRuta(ent.getRespRuta());
        obj.setRespNombre(ent.getRespNombre());
        obj.setRespExiste(ent.getRespExiste());
        obj.setRespArchivo(ent.getRespArchivo());
        return obj;
    }

    public static PeCargas castNegocioEntidad(PeCarga obj) {
        PeCargas ent = new PeCargas();
        ent.setId(obj.getId());
        if (obj.getEmpresa() != null) {
            ent.setGnEmpresasId(new GnEmpresas(obj.getEmpresa().getId()));
        }
        ent.setPeProgramasId(new PeProgramas(obj.getIdPrograma()));
        ent.setNombre(obj.getNombre());
        ent.setTipo(obj.getTipo());
        ent.setArchivo(obj.getArchivo());
        ent.setRuta(obj.getRuta());
        ent.setRegistros(obj.getRegistros());
        ent.setDetalle(obj.getDetalle());
        ent.setEstado(obj.getEstado());
        ent.setExitosos(obj.getExitosos());
        ent.setFallidos(obj.getFallidos());
        ent.setFechaHoraInicio(obj.getFechaHoraInicio());
        ent.setFechaHoraFin(obj.getFechaHoraFin());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setPeProgramasId(new PeProgramas(obj.getIdPrograma()));
        ent.setExiste(obj.getExiste());
        ent.setRespRuta(obj.getRespRuta());
        ent.setRespNombre(obj.getRespNombre());
        ent.setRespExiste(obj.getRespExiste());
        ent.setRespArchivo(obj.getRespArchivo());
        return ent;
    }    

}
