package com.saviasaludeps.savia.ejb.servicios.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntContratoCarga;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.ejb.entidades.CntContratoCargas;
import javax.persistence.Query;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoCargaRemoto;
import java.text.SimpleDateFormat;
import org.hibernate.Session;

@Stateless
@Remote(CntContratoCargaRemoto.class)
public class CntContratoCargaServicio extends GenericoServicio implements CntContratoCargaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntContratoCargas c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id = " + (String) e.getValue() + " ";
                            break;
                        case "tipo":
                            strQuery += "AND c.tipo = " + (String) e.getValue() + " ";
                            break;
                        case "nombreArchivo":
                            strQuery += "AND c.nombreArchivo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND c.estado = " + (String) e.getValue() + " ";
                            break;
                        case "registrosTotal":
                            strQuery += "AND c.registrosTotal = " + (String) e.getValue() + " ";
                            break;
                        case "registrosExitosos":
                            strQuery += "AND c.registrosExitosos = " + (String) e.getValue() + " ";
                            break;
                        case "registrosRechazados":
                            strQuery += "AND c.registrosRechazados = " + (String) e.getValue() + " ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND c.fechaHoraInicio = '" +  (String) e.getValue() + "' ";
                            break;
                        case "fechaHoraFin":
                            strQuery += "AND c.fechaHoraFin = '" +  (String) e.getValue() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND c.usuarioCrea LIKE '%" + (String) e.getValue() + "%' ";
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
    public List<CntContratoCarga> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CntContratoCarga> listResult = new ArrayList();
        try {
            String strQuery = "SELECT c FROM CntContratoCargas c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id = " + (String) e.getValue() + " ";
                            break;
                        case "tipo":
                            strQuery += "AND c.tipo = " + (String) e.getValue() + " ";
                            break;
                        case "nombreArchivo":
                            strQuery += "AND c.nombreArchivo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND c.estado = " + (String) e.getValue() + " ";
                            break;
                        case "registrosTotal":
                            strQuery += "AND c.registrosTotal = " + (String) e.getValue() + " ";
                            break;
                        case "registrosExitosos":
                            strQuery += "AND c.registrosExitosos = " + (String) e.getValue() + " ";
                            break;
                        case "registrosRechazados":
                            strQuery += "AND c.registrosRechazados = " + (String) e.getValue() + " ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND c.fechaHoraInicio = '" +  (String) e.getValue() + "' ";
                            break;
                        case "fechaHoraFin":
                            strQuery += "AND c.fechaHoraFin = '" +  (String) e.getValue() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND c.usuarioCrea LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            //ordenamiento
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                        strQuery += "c." + paramConsulta.getOrden() + " "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "c.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
             //getEntityManager().createQuery(strQuery)
            List<CntContratoCargas> list = query.setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntContratoCargas per : list) {
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

    private CntContratoCarga castEntidadNegocio(CntContratoCargas per) {
        CntContratoCarga neg = new CntContratoCarga();
        neg.setId(per.getId());
        neg.setEstado(per.getEstado());
        neg.setTipo(per.getTipo());
        neg.setFechaHoraInicio(per.getFechaHoraInicio());
        neg.setFechaHoraFin(per.getFechaHoraFin());
        neg.setRegistrosTotal(per.getRegistrosTotal());
        neg.setRegistrosExitosos(per.getRegistrosExitosos());
        neg.setRegistrosRechazados(per.getRegistrosRechazados());
        neg.setNombreArchivo(per.getNombreArchivo());
        neg.setRuta(per.getRuta());
        neg.setArchivo(per.getArchivo());
        neg.setExiste(per.getExiste());
        //2025-02-12 jyperez nuevos campos
        neg.setRespNombre(per.getRespNombre());
        neg.setRespRuta(per.getRespRuta());
        neg.setRespArchivo(per.getRespArchivo());
        neg.setRespExiste((per.getRespExiste() != null)? per.getRespExiste(): false);
        //objetos
        //auditoria
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    @Override
    public CntContratoCarga consultar(int id) throws Exception {
        CntContratoCarga objRes = null;
        try {
            objRes = castEntidadNegocio((CntContratoCargas) getEntityManager().find(CntContratoCargas.class, id));
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
    public int insertar(CntContratoCarga obj) throws Exception {
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
    public void actualizar(CntContratoCarga obj) throws Exception {
        try {
            CntContratoCargas contrato = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            //session.update(per);
            String strQuery = "UPDATE CntContratoCargas a SET ";
            strQuery += " a.registrosTotal = :registrosTotal ,";
            strQuery += " a.registrosExitosos = :registrosExitosos ,";
            strQuery += " a.registrosRechazados = :registrosRechazados ,";
            strQuery += " a.ruta = :ruta ,";
            strQuery += " a.archivo = :archivo ,";
            strQuery += " a.existe = :existe ,";
            strQuery += " a.estado = :estado ,";
            //campo fechas
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaInicio = formatoFecha.format(obj.getFechaHoraInicio());
            strQuery += " a.fechaHoraInicio = '" + fechaInicio + "' ";
            if (obj.getFechaHoraFin() != null) {
                String fechaFin = formatoFecha.format(obj.getFechaHoraFin());
                strQuery += ", a.fechaHoraFin = '" + fechaFin + "' ";
            }
            //campos objetos

            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(contrato);
            query.executeUpdate();
            
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CntContratoCarga eliminar(int id) throws Exception {
        CntContratoCarga obj = null;
        try {
            CntContratoCargas ent = getEntityManager().find(CntContratoCargas.class, id);
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
    public List<CntContratoCarga> consultarTodos() throws Exception {
        List<CntContratoCarga> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntContratoCargas p "
                    + "ORDER BY p.id ";
            List<CntContratoCargas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntContratoCargas per : list) {
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

    public static CntContratoCargas castNegocioEntidad(CntContratoCarga obj) {
        CntContratoCargas per = new CntContratoCargas();
        per.setId(obj.getId());
        per.setEstado(obj.getEstado());
        per.setTipo(obj.getTipo());
        per.setFechaHoraInicio(obj.getFechaHoraInicio());
        per.setFechaHoraFin(obj.getFechaHoraFin());
        per.setRegistrosTotal(obj.getRegistrosTotal());
        per.setRegistrosExitosos(obj.getRegistrosExitosos());
        per.setRegistrosRechazados(obj.getRegistrosRechazados());
        per.setNombreArchivo(obj.getNombreArchivo());
        per.setRuta(obj.getRuta());
        per.setArchivo(obj.getArchivo());
        per.setExiste(obj.getExiste());
        //2025-02-12 jyperez nuevos campos
        per.setRespNombre(obj.getRespNombre());
        per.setRespRuta(obj.getRespRuta());
        per.setRespArchivo(obj.getRespArchivo());
        per.setRespExiste(obj.isRespExiste());
        //objetos
        //auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        return per;
    }

    @Override
    public CntContratoCarga consultarSiguienteCarga(int estado) throws java.lang.Exception {
        CntContratoCarga objResult = null;
        try {
            String strQuery = "FROM CntContratoCargas p "
                    + " WHERE p.estado = " + estado
                    + " ORDER BY p.id ASC ";
            List<CntContratoCargas> result = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if(!result.isEmpty()) {
                objResult = castEntidadNegocio(result.get(0));
            }
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
    public int consultarCantidadCargasArchivoExistente(String nombreArchivo) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntContratoCargas c "
                    + " WHERE c.nombreArchivo LIKE '" + nombreArchivo + "' ";
            
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
    
}
