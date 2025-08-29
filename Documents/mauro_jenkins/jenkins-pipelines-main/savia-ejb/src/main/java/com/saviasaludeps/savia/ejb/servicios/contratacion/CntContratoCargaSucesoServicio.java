package com.saviasaludeps.savia.ejb.servicios.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntContratoCarga;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoCargaSuceso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.ejb.entidades.CntContratoCargaSucesos;
import com.saviasaludeps.savia.ejb.entidades.CntContratoCargas;
import javax.persistence.Query;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoCargaSucesoRemoto;
import java.text.SimpleDateFormat;
import org.hibernate.Session;

@Stateless
@Remote(CntContratoCargaSucesoRemoto.class)
public class CntContratoCargaSucesoServicio extends GenericoServicio implements CntContratoCargaSucesoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntContratoCargaSucesos c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id = " + (Integer) e.getValue() + " ";
                            break;
                        case "nombreArchivo":
                            strQuery += "AND c.nombreArchivo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND c.estado = " + (int) e.getValue() + " ";
                            break;
                        case "registrosTotal":
                            strQuery += "AND c.negociacion LIKE '%" + (boolean) e.getValue() + "%' ";
                            break;
                        case "registrosExitosos":
                            strQuery += "AND c.cntPrestadoresId.maeTipoDocumentoCodigo = '" + (String) e.getValue() + "' ";
                            break;
                        case "registrosRechazados":
                            strQuery += "AND c.cntPrestadoresId.maeTipoDocumentoValor = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND c.fechaHoraInicio = '" + e.getValue() + "' ";
                            break;
                        case "fechaHoraFin":
                            strQuery += "AND c.fechaHoraFin = '" + e.getValue() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND c.usuarioCrea LIKE '%" + e.getValue() + "%' ";
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
    public List<CntContratoCargaSuceso> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CntContratoCargaSuceso> listResult = new ArrayList();
        try {
            String strQuery = "SELECT c FROM CntContratoCargaSucesos c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id = " + (Integer) e.getValue() + " ";
                            break;
                        case "nombreArchivo":
                            strQuery += "AND c.nombreArchivo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND c.activo = " + (boolean) e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND c.estado = " + (int) e.getValue() + " ";
                            break;
                        case "registrosTotal":
                            strQuery += "AND c.negociacion LIKE '%" + (boolean) e.getValue() + "%' ";
                            break;
                        case "registrosExitosos":
                            strQuery += "AND c.cntPrestadoresId.maeTipoDocumentoCodigo = '" + (String) e.getValue() + "' ";
                            break;
                        case "registrosRechazados":
                            strQuery += "AND c.cntPrestadoresId.maeTipoDocumentoValor = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND c.fechaHoraInicio = '" + e.getValue() + "' ";
                            break;
                        case "fechaHoraFin":
                            strQuery += "AND c.fechaHoraFin = '" + e.getValue() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND c.usuarioCrea LIKE '%" + e.getValue() + "%' ";
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
            List<CntContratoCargaSucesos> list = query.setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntContratoCargaSucesos per : list) {
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

    private CntContratoCargaSuceso castEntidadNegocio(CntContratoCargaSucesos per) {
        CntContratoCargaSuceso neg = new CntContratoCargaSuceso();
        neg.setId(per.getId());
        neg.setEstado(per.getEstado());
        neg.setData(per.getData());
        neg.setFechaHoraProceso(per.getFechaHoraProceso());
        neg.setDetalleFallo(per.getDetalleFallo());
        //objetos
        if (per.getCntContratoCargasId()!= null) {
            neg.setCntContratoCarga(new CntContratoCarga(per.getCntContratoCargasId().getId()));
        }
        //auditoria
        return neg;
    }

    @Override
    public CntContratoCargaSuceso consultar(int id) throws Exception {
        CntContratoCargaSuceso objRes = null;
        try {
            objRes = castEntidadNegocio((CntContratoCargaSucesos) getEntityManager().find(CntContratoCargaSucesos.class, id));
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
    public int insertar(CntContratoCargaSuceso obj) throws Exception {
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
    public void actualizar(CntContratoCargaSuceso obj) throws Exception {
        try {
            CntContratoCargaSucesos contrato = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            //session.update(per);
            String strQuery = "UPDATE CntContratoCargaSucesos a SET ";
            strQuery += " a.estado = :estado ,";
            strQuery += " a.data = :data ,";
            strQuery += " a.detalleFallo = :detalleFallo ,";
            //campo fechas
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaInicio = formatoFecha.format(obj.getFechaHoraProceso());
            strQuery += " a.fechaHoraProceso = '" + fechaInicio + "' ";
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
    public CntContratoCargaSuceso eliminar(int id) throws Exception {
        CntContratoCargaSuceso obj = null;
        try {
            CntContratoCargaSucesos ent = getEntityManager().find(CntContratoCargaSucesos.class, id);
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
    public List<CntContratoCargaSuceso> consultarTodos() throws Exception {
        List<CntContratoCargaSuceso> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntContratoCargaSucesos p "
                    + "ORDER BY p.id ";
            List<CntContratoCargaSucesos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntContratoCargaSucesos per : list) {
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

    public static CntContratoCargaSucesos castNegocioEntidad(CntContratoCargaSuceso obj) {
        CntContratoCargaSucesos per = new CntContratoCargaSucesos();
        per.setId(obj.getId());
        per.setEstado(obj.getEstado());
        per.setData(obj.getData());
        per.setFechaHoraProceso(obj.getFechaHoraProceso());
        per.setDetalleFallo(obj.getDetalleFallo());
        //objetos
        if (obj.getCntContratoCarga() != null) {
            per.setCntContratoCargasId(new CntContratoCargas(obj.getCntContratoCarga().getId()));
        }
        //auditoria
        return per;
    }

    @Override
    public List<CntContratoCargaSuceso> consultarPorContratoCarga(int idContratoCarga) throws java.lang.Exception {
        List<CntContratoCargaSuceso> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntContratoCargaSucesos p "
                    + " WHERE p.cntContratoCargasId.id = " + idContratoCarga
                    + " ORDER BY p.id ASC ";
            List<CntContratoCargaSucesos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntContratoCargaSucesos per : list) {
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
    
}
