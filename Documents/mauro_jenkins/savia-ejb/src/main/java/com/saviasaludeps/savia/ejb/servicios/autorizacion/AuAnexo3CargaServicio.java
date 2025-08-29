package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Carga;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo3Cargas;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3CargaRemoto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

@Stateless
@Remote(AuAnexo3CargaRemoto.class)
public class AuAnexo3CargaServicio extends GenericoServicio implements AuAnexo3CargaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuAnexo3Cargas p ";
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "WHERE p.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            } else {
                strQuery += "WHERE p.id > 0 ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombreArchivo":
                            strQuery += "AND p.nombreArchivo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND p.fechaHoraInicio >= '" + new java.sql.Timestamp(((Date) e.getValue()).getTime()) + "' ";
                            break;
                        case "fechaHoraFin":
                            strQuery += "AND p.fechaHoraFin <= '" + new java.sql.Timestamp(((Date) e.getValue()).getTime()) + "' ";
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
    public List<AuAnexo3Carga> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo3Carga> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo3Cargas p ";
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "WHERE p.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            } else {
                strQuery += "WHERE p.id > 0 ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "nombreArchivo":
                            strQuery += "AND p.nombreArchivo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND p.fechaHoraInicio >= '" + new java.sql.Timestamp(((Date) e.getValue()).getTime()) + "' ";
                            break;
                        case "fechaHoraFin":
                            strQuery += "AND p.fechaHoraFin <= '" + new java.sql.Timestamp(((Date) e.getValue()).getTime()) + "' ";
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

            List<AuAnexo3Cargas> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexo3Cargas anexo3Carga : list) {
                listaResultados.add(castEntidadNegocio(anexo3Carga));
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
    public AuAnexo3Carga consultar(int id) throws Exception {
        AuAnexo3Carga objRes = null;
        try {
            objRes = castEntidadNegocio((AuAnexo3Cargas) getEntityManager().find(AuAnexo3Cargas.class, id));
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
    public int insertar(AuAnexo3Carga obj) throws Exception {
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
    public void actualizar(AuAnexo3Carga obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexo3Cargas a SET ";
            if (obj.getGnEmpresasId() != null) {
                strQuery += "a.gnEmpresasId.id = :gnEmpresasId ,";
            }
            strQuery += "a.estado = :estado ,";
            strQuery += "a.fechaHoraInicio = :fechaHoraInicio ,";
            if (obj.getFechaHoraFin() != null) {
                strQuery += "a.fechaHoraFin = :fechaHoraFin ,";
            }
            strQuery += "a.registrosTotal = :registrosTotal ,";
            if (obj.getRegistrosExitosos() != null) {
                strQuery += "a.registrosExitosos = :registrosExitosos ,";
            }
            if (obj.getRegistrosExitosos() != null) {
                strQuery += "a.registrosRechazados = :registrosRechazados ,";
            }
            strQuery += "a.nombreArchivo = :nombreArchivo ,";
            strQuery += "a.ruta = :ruta ,";
            strQuery += "a.archivo = :archivo ,";
            strQuery += "a.existe = :existe ,";
            strQuery += "a.usuarioCrea = :usuarioCrea ,";
            strQuery += "a.terminalCrea = :terminalCrea ,";
            strQuery += "a.fechaHoraCrea = :fechaHoraCrea ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            if (obj.getGnEmpresasId() != null) {
                query.setParameter("gnEmpresasId", obj.getGnEmpresasId().getId());
            }
            query.setParameter("estado", obj.getEstado());
            query.setParameter("fechaHoraInicio", obj.getFechaHoraInicio());
            if (obj.getFechaHoraFin() != null) {
                query.setParameter("fechaHoraFin", obj.getFechaHoraFin());
            }
            query.setParameter("registrosTotal", obj.getRegistrosTotal());
            if (obj.getRegistrosExitosos() != null) {
                query.setParameter("registrosExitosos", obj.getRegistrosExitosos());
            }
            if (obj.getRegistrosRechazados() != null) {
                query.setParameter("registrosRechazados", obj.getRegistrosRechazados());
            }
            query.setParameter("nombreArchivo", obj.getNombreArchivo());
            query.setParameter("ruta", obj.getRuta());
            query.setParameter("archivo", obj.getArchivo());
            query.setParameter("existe", obj.isExiste());
            query.setParameter("usuarioCrea", obj.getUsuarioCrea());
            query.setParameter("terminalCrea", obj.getTerminalCrea());
            query.setParameter("fechaHoraCrea", obj.getFechaHoraCrea());
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
    public AuAnexo3Carga eliminar(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//    
//    @Override
//    public boolean consultarDuplicado(String nombreArchivo) throws Exception {
//        boolean respusta = false;
//        
//        return respuesta;
//    }

    public AuAnexo3Cargas castNegocioEntidad(AuAnexo3Carga negocio) {
        AuAnexo3Cargas entidad = new AuAnexo3Cargas();
        entidad.setEstado(negocio.getEstado());
        entidad.setGnEmpresasId(new GnEmpresas(negocio.getGnEmpresasId().getId()));
        entidad.setFechaHoraInicio(negocio.getFechaHoraInicio());
        entidad.setRegistrosTotal(negocio.getRegistrosTotal());
        entidad.setRegistrosExitosos(negocio.getRegistrosExitosos());
        entidad.setRegistrosRechazados(negocio.getRegistrosRechazados());
        entidad.setNombreArchivo(negocio.getNombreArchivo());
        entidad.setRuta(negocio.getRuta());
        entidad.setArchivo(negocio.getArchivo());
        entidad.setExiste(negocio.isExiste());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }

    public AuAnexo3Carga castEntidadNegocio(AuAnexo3Cargas entidad) {
        AuAnexo3Carga negocio = new AuAnexo3Carga();
        if (entidad.getGnEmpresasId() != null) {
            negocio.setGnEmpresasId(new Empresa(entidad.getGnEmpresasId().getId()));
        }
        negocio.setId(entidad.getId());
        negocio.setEstado(entidad.getEstado());
        negocio.setFechaHoraInicio(entidad.getFechaHoraInicio());
        if (entidad.getFechaHoraFin() != null) {
            negocio.setFechaHoraFin(entidad.getFechaHoraFin());
        }
        negocio.setRegistrosTotal(entidad.getRegistrosTotal());
        negocio.setRegistrosExitosos(entidad.getRegistrosExitosos());
        negocio.setRegistrosRechazados(entidad.getRegistrosRechazados());
        negocio.setNombreArchivo(entidad.getNombreArchivo());
        negocio.setRuta(entidad.getRuta());
        negocio.setArchivo(entidad.getArchivo());
        negocio.setExiste(entidad.getExiste());
        negocio.setEstadoObservacion(entidad.getEstadoObservacion());
        negocio.setUsuarioGestionEstado(entidad.getUsuarioGestionEstado());
        negocio.setFechaHoraGestionEstado(entidad.getFechaHoraGestionEstado());
        negocio.setTerminalGestionEstado(entidad.getTerminalGestionEstado());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        return negocio;
    }

    @Override
    public List<AuAnexo3Carga> consultarArchivoNombre(String nombre) throws java.lang.Exception {
        List<AuAnexo3Carga> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo3Cargas c WHERE c.nombreArchivo = '" + nombre + "' AND c.estado <> 8";
            List<AuAnexo3Cargas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuAnexo3Cargas anexo3Carga : list) {
                listaResultados.add(castEntidadNegocio(anexo3Carga));
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
    public AuAnexo3Carga consultarSiguienteCarga(int estado) throws Exception {
        AuAnexo3Carga result = null;
        try {
            String strQuery = "FROM AuAnexo3Cargas c WHERE c.estado = " + estado + " ORDER BY c.id DESC";
            List<AuAnexo3Cargas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuAnexo3Cargas anexo3Carga : list) {
                result = castEntidadNegocio(anexo3Carga);
            }
        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    @Override
    public List<AuAnexo3Carga> consultarEstadoProceso(int idEmpresa) throws java.lang.Exception {
        List<AuAnexo3Carga> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo3Cargas c WHERE c.estado IN (0, 1, 3, 4, 6, 7) AND c.gnEmpresasId.id = '" + idEmpresa + "'"
                    + " ORDER BY c.id DESC";
            List<AuAnexo3Cargas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuAnexo3Cargas anexo3Carga : list) {
                listaResultados.add(castEntidadNegocio(anexo3Carga));
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
    public void actualizarEstado(AuAnexo3Carga obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexo3Cargas a SET ";
            strQuery += "a.estadoObservacion = :estadoObservacion, ";
            strQuery += "a.usuarioGestionEstado = :usuarioGestionEstado, ";
            strQuery += "a.terminalGestionEstado = :terminalGestionEstado, ";
            strQuery += "a.fechaHoraGestionEstado = :fechaHoraGestionEstado, ";
            strQuery += "a.estado = :estado ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("estadoObservacion", obj.getEstadoObservacion());
            query.setParameter("usuarioGestionEstado", obj.getUsuarioGestionEstado());
            query.setParameter("terminalGestionEstado", obj.getTerminalGestionEstado());
            query.setParameter("fechaHoraGestionEstado", obj.getFechaHoraGestionEstado());
            query.setParameter("estado", obj.getEstado());
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

}
