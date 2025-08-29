/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.atencionusuario;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCargaMasiva;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCaso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AusCargaMasivas;
import com.saviasaludeps.savia.ejb.entidades.AusCasos;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.atencionusuario.AusCargaMasivaRemoto;
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
 * @author jperezN
 */
@Stateless
@Remote(AusCargaMasivaRemoto.class)
@Local(AusCargaMasivaLocal.class)
public class AusCargaMasivaServicio extends GenericoServicio implements AusCargaMasivaLocal, AusCargaMasivaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AusCargaMasivas p ";
            strQuery += "WHERE p.id > 0 ";

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
                            strQuery += "AND p.usuarioCrea LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += "AND p.tipo IN (" + e.getValue() + ") ";
                            break;
                        case "detalle":
                            strQuery += "AND p.detalle = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            Query query = getEntityManager().createQuery(strQuery);
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
    public List<AusCargaMasiva> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AusCargaMasiva> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM AusCargaMasivas p ";
            strQuery += " WHERE p.id > 0 ";

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
                            strQuery += "AND p.usuarioCrea LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += "AND p.tipo IN (" + e.getValue() + ") ";
                            break;
                        case "detalle":
                            strQuery += "AND p.detalle = '" + e.getValue() + "' ";
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
            query.setFirstResult(paramConsulta.getPrimerRegistro());
            query.setMaxResults(paramConsulta.getRegistrosPagina());
            List<AusCargaMasivas> list = query.getResultList();
            for (AusCargaMasivas per : list) {
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
    public int insertar(AusCargaMasiva obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al insertar un registro carga masiva");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(AusCargaMasiva obj) throws java.lang.Exception {
        try {
            String hql = "UPDATE AusCargaMasivas SET"
                    + " cantidadRegistros = :cantidadRegistros,"
                    + " estado = :estado,"
                    + " fechaHoraFin = :fechaHoraFin,"
                    + " observacion = :observacion,"
                    + " exitosos = :exitosos, "
                    + " fallidos = :fallidos "
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("cantidadRegistros", obj.getCantidadRegistros());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("fechaHoraFin", obj.getFechaHoraFin());
            query.setParameter("observacion", obj.getObservacion());
            query.setParameter("exitosos", obj.getExitosos());
            query.setParameter("fallidos", obj.getFallidos());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e, "Error al actualizar un carga masiva atencion usuario");
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarFechaMasEstado(AusCargaMasiva obj) throws java.lang.Exception {
        try {
            String hql = "UPDATE AusCargaMasivas SET"
                    + " fechaHoraFin = :fechaHoraFin, "
                    + " estado = :estado "
                    + ((obj.getObservacion() == null || "".equals(obj.getObservacion())) ? "" : " ,observacion = :observacion ")
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("fechaHoraFin", obj.getFechaHoraFin());
            query.setParameter("estado", obj.getEstado());
            if (obj.getObservacion() != null && !"".equals(obj.getObservacion())) {
                query.setParameter("observacion", obj.getObservacion());
            }
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e, "Error al actualizar estado y fecha fin carga masiva");
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarObservacion(AusCargaMasiva obj) throws java.lang.Exception {
        try {
            String hql = "UPDATE AusCargaMasivas SET"
                    + " estado = :estado,"
                    + " observacion = :observacion"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("observacion", obj.getObservacion());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e, "Error al actualizar un carga masiva atencion usuario");
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarEstadoProcesado(AusCargaMasiva obj) throws java.lang.Exception {
        try {
            String hql = "UPDATE AusCargaMasivas SET"
                    + " estado = :estado"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e, "Error al actualizar un carga masiva atencion usuario");
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarArchivo(AusCargaMasiva obj) throws Exception {
        try {
            String sql = "UPDATE AusCargaMasivas "
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
    public void actualizarArchivoResultado(AusCargaMasiva obj) throws Exception {
        try {
            String sql = "UPDATE AusCargaMasivas SET "
                    + "respNombre = :respNombre, "
                    + "respRuta = :respRuta, "
                    + "respArchivo = :respArchivo, "
                    + "respExiste = :respExiste "
                    + "WHERE id = :id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("respNombre", obj.getRespNombre());
            query.setParameter("respRuta", obj.getRespRuta());
            query.setParameter("respArchivo", obj.getRespArchivo());
            query.setParameter("respExiste", obj.getRespExiste());
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
    public AusCargaMasiva consultar(int id) throws Exception {
        AusCargaMasiva objRes = new AusCargaMasiva();
        try {
            AusCargaMasivas cargaMasiva = (AusCargaMasivas) getEntityManager().find(AusCargaMasivas.class, id);
            if (cargaMasiva != null) {
                objRes = castEntidadNegocio(cargaMasiva);
            }

        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e, "Error al consultar un carga masiva atencion usuario");
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public List<AsegAfiliado> consultarDatosAsegAfiliadoLista(ParamConsulta paramConsulta) throws Exception {
        List<AsegAfiliado> listResult = new ArrayList();
        try {
            if (paramConsulta.getParametroConsulta1() != null || paramConsulta.getParametroConsulta2() != null) {

                String strQuery = "FROM AsegAfiliados p "
                        + "WHERE p.id > 0 ";

                if (paramConsulta.getParametroConsulta1() != null) {
                    strQuery += "AND p.numeroDocumento IN (" + paramConsulta.getParametroConsulta1() + ") ";
                }

                if (paramConsulta.getParametroConsulta2() != null) {
                    strQuery += "AND p.id IN (" + paramConsulta.getParametroConsulta2() + ") ";
                }

                List<AsegAfiliados> list = getEntityManager().createQuery(strQuery)
                        .getResultList();
                for (AsegAfiliados per : list) {
                    listResult.add(castEntidadNegocio(per));
                }
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
    public List<AusCaso> consultarCasosPorRadicadoRegistrado(ParamConsulta paramConsulta) throws Exception {
        List<AusCaso> listResult = new ArrayList();
        try {
            if (paramConsulta.getParametroConsulta1() != null) {

                String strQuery = "FROM AusCasos auCaso "
                        + "WHERE auCaso.id > 0 ";

                if (paramConsulta.getParametroConsulta1() != null) {
                    strQuery += "AND auCaso.radicado IN (" + paramConsulta.getParametroConsulta1() + ") ";
                }

                List<AusCasos> list = getEntityManager().createQuery(strQuery)
                        .getResultList();
                for (AusCasos per : list) {
                    listResult.add(castEntidadNegocio(per));
                }
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
    public List<AusCargaMasiva> consulltarPorEstadoCola(Integer numeroMaximoRegistro) throws Exception {
        List<AusCargaMasiva> listResult = new ArrayList();
        try {

            String strQuery = "FROM AusCargaMasivas ausCargaMasivas "
                    + "WHERE ausCargaMasivas.id > 0 "
                    + "AND ausCargaMasivas.estado = 0 "
                    + "ORDER BY ausCargaMasivas.id ASC ";

            List<AusCargaMasivas> list = getEntityManager().createQuery(strQuery).setMaxResults(numeroMaximoRegistro)
                    .getResultList();
            for (AusCargaMasivas per : list) {
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
    public List<AsegAfiliado> consultarUltimosRegistroAsegAfiliadoLista(ParamConsulta paramConsulta) throws Exception {
        List<AsegAfiliado> listResult = new ArrayList();
        try {
            if (paramConsulta.getParametroConsulta1() != null) {

                String strQuery = "select MAX( ID ) as idAfililado FROM aseg_afiliados "
                        + "WHERE id > 0 ";

                if (paramConsulta.getParametroConsulta1() != null) {
                    strQuery += " and numero_documento IN (" + paramConsulta.getParametroConsulta1() + ")  GROUP BY numero_documento ";
                }

                List<Object[]> listAfiliados = getEntityManager().createNativeQuery(strQuery).getResultList();

                if (listAfiliados != null) {
                    for (Object idAfiliado : listAfiliados) {
                        AsegAfiliado afiliadoIn = new AsegAfiliado((Integer) idAfiliado);
                        listResult.add(afiliadoIn);
                    }
                }

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

    public static AusCargaMasivas castNegocioEntidad(AusCargaMasiva neg) {
        AusCargaMasivas ent = new AusCargaMasivas();
        ent.setId(neg.getId());
        if(neg.getEmpresa() != null && neg.getEmpresa().getId() != null){
            ent.setGnEmpresaId(new GnEmpresas(neg.getEmpresa().getId()));
        }
        ent.setCantidadRegistros(neg.getCantidadRegistros());
        ent.setEstado(neg.getEstado());
        ent.setExitosos(neg.getExitosos());
        ent.setFallidos(neg.getFallidos());
        ent.setTipo(neg.getTipo());
        ent.setNombre(neg.getNombre());
        ent.setRuta(neg.getRuta());
        ent.setArchivo(neg.getArchivo());
        ent.setFechaHoraInicio(neg.getFechaHoraInicio());
        ent.setFechaHoraFin(neg.getFechaHoraFin());
        ent.setExiste(neg.getExiste());
        ent.setObservacion(neg.getObservacion());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        return ent;
    }

    public static AusCargaMasiva castEntidadNegocio(AusCargaMasivas entidad) {
        AusCargaMasiva negocio = new AusCargaMasiva();
        negocio.setId(entidad.getId());
        if(entidad.getGnEmpresaId() != null && entidad.getGnEmpresaId().getId() != null){
            negocio.setEmpresa(new Empresa(entidad.getGnEmpresaId().getId()));
        }
        negocio.setCantidadRegistros(entidad.getCantidadRegistros());
        negocio.setEstado(entidad.getEstado());
        negocio.setExitosos(entidad.getExitosos());
        negocio.setFallidos(entidad.getFallidos());
        negocio.setTipo(entidad.getTipo());
        negocio.setNombre(entidad.getNombre());
        negocio.setRuta(entidad.getRuta());
        negocio.setArchivo(entidad.getArchivo());
        negocio.setFechaHoraInicio(entidad.getFechaHoraInicio());
        negocio.setFechaHoraFin(entidad.getFechaHoraFin());
        negocio.setObservacion(entidad.getObservacion());
        negocio.setRespArchivo(entidad.getRespArchivo());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        return negocio;
    }

    public static AsegAfiliado castEntidadNegocio(AsegAfiliados ent) {
        AsegAfiliado neg = new AsegAfiliado();
        neg.setId(ent.getId());
        neg.setNumeroDocumento(ent.getNumeroDocumento());
        neg.setMaeTipoDocumentoValor(ent.getMaeTipoDocumentoValor());
        neg.setMaeTipoDocumentoCodigo(ent.getMaeTipoDocumentoCodigo());
        neg.setMaeEstadoAfiliacion(ent.getMaeEstadoAfiliacionId());
        neg.setMaeEstadoAfiliacionCodigo(ent.getMaeEstadoAfiliacionCodigo());
        neg.setMaeEstadoAfiliacionValor(ent.getMaeEstadoAfiliacionValor());
        neg.setTelefonoFijo(ent.getTelefonoFijo());
        neg.setTelefonoMovil(ent.getTelefonoMovil());
        neg.setFechaNacimiento(ent.getFechaNacimiento());
        return neg;
    }

    public static AusCaso castEntidadNegocio(AusCasos ent) {
        AusCaso neg = new AusCaso();
        neg.setId(ent.getId());
        neg.setRadicado(ent.getRadicado());
        return neg;
    }

}
