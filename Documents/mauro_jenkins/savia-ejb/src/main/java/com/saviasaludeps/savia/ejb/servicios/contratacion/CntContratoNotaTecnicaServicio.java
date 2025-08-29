package com.saviasaludeps.savia.ejb.servicios.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoNotaTecnica;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.ejb.entidades.CntContratoNotasTecnicas;
import com.saviasaludeps.savia.ejb.entidades.CntContratos;
import javax.persistence.Query;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoNotaTecnicaRemoto;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Session;

@Stateless
@Remote(CntContratoNotaTecnicaRemoto.class)
public class CntContratoNotaTecnicaServicio extends GenericoServicio implements CntContratoNotaTecnicaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntContratoNotasTecnicas c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null ) {
                strQuery += "AND c.cntContratosId.id = " +(Integer) paramConsulta.getParametroConsulta1() + " ";
            } else {
                throw new Exception("No se configuraron parámetros del contrato.");
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id = " + (String) e.getValue() + " ";
                            break;
                        case "tipoTecnologia":
                            strQuery += "AND c.tipoTecnologia = " + (String) e.getValue() + " ";
                            break;
                        case "maTecnologiaCodigo":
                            strQuery += "AND c.maTecnologiaCodigo = '" + (String) e.getValue() + "' ";
                            break;
                        case "maTecnologiaValor":
                            strQuery += "AND c.maTecnologiaValor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaInicio":
                            strQuery += "AND c.fechaInicio = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaFin":
                            strQuery += "AND c.fechaFin = '" + (String) e.getValue() + "' ";
                            break;
                        case "observacion":
                            strQuery += "AND c.observacion LIKE '%" + (String) e.getValue() + "%' ";
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
    public List<CntContratoNotaTecnica> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CntContratoNotaTecnica> listResult = new ArrayList();
        try {
            String strQuery = "SELECT c FROM CntContratoNotasTecnicas c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND c.cntContratosId.id = " +(Integer) paramConsulta.getParametroConsulta1() + " ";
            } else {
                throw new Exception("No se configuraron parámetros del contrato detalle.");
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id = " + (String) e.getValue() + " ";
                            break;
                        case "tipoTecnologia":
                            strQuery += "AND c.tipoTecnologia = " + (String) e.getValue() + " ";
                            break;
                        case "maTecnologiaCodigo":
                            strQuery += "AND c.maTecnologiaCodigo = '" + (String) e.getValue() + "' ";
                            break;
                        case "maTecnologiaValor":
                            strQuery += "AND c.maTecnologiaValor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaInicio":
                            strQuery += "AND c.fechaInicio = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaFin":
                            strQuery += "AND c.fechaFin = '" + (String) e.getValue() + "' ";
                            break;
                        case "observacion":
                            strQuery += "AND c.observacion LIKE '%" + (String) e.getValue() + "%' ";
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
            List<CntContratoNotasTecnicas> list = query.setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntContratoNotasTecnicas per : list) {
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

    private CntContratoNotaTecnica castEntidadNegocio(CntContratoNotasTecnicas per) {
        CntContratoNotaTecnica neg = new CntContratoNotaTecnica();
        neg.setId(per.getId());
        neg.setTipoTecnologia(per.getTipoTecnologia());
        neg.setMaTecnologiaId(per.getMaTecnologiaId());
        neg.setMaTecnologiaCodigo(per.getMaTecnologiaCodigo());
        neg.setMaTecnologiaValor(per.getMaTecnologiaValor());
        neg.setCostoPromedio(per.getCostoPromedio());
        neg.setTipoFrecuencia(per.getTipoFrecuencia());
        neg.setFrecuenciaUso(per.getFrecuenciaUso());
        neg.setCantidadAfiliados(per.getCantidadAfiliados());
        neg.setMaeAmbitoId(per.getMaeAmbitoId());
        neg.setMaeAmbitoCodigo(per.getMaeAmbitoCodigo());
        neg.setMaeAmbitoValor(per.getMaeAmbitoValor());
        neg.setObservacion(per.getObservacion());
        neg.setFechaInicio(per.getFechaInicio());
        neg.setFechaFin(per.getFechaFin());
        //objetos
        if(per.getCntContratosId()!= null) {
            CntContrato contrato = new CntContrato(per.getCntContratosId().getId());
            contrato.setContrato(per.getCntContratosId().getContrato());
            neg.setCntContrato(contrato);
        }
        //auditoria
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        neg.setUsuarioModifica(per.getUsuarioModifica());
        neg.setTerminalModifica(per.getTerminalModifica());
        neg.setFechaHoraModifica(per.getFechaHoraModifica());
        
        return neg;
    }

    @Override
    public CntContratoNotaTecnica consultar(int id) throws Exception {
        CntContratoNotaTecnica objRes = null;
        try {
            objRes = castEntidadNegocio((CntContratoNotasTecnicas) getEntityManager().find(CntContratoNotasTecnicas.class, id));
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
    public int insertar(CntContratoNotaTecnica obj) throws Exception {
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
    public void actualizar(CntContratoNotaTecnica obj) throws Exception {
        try {
            CntContratoNotasTecnicas contrato = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            //session.update(per);
            String strQuery = "UPDATE CntContratoNotasTecnicas a SET ";
            strQuery += " a.tipoTecnologia = :tipoTecnologia ,";
            strQuery += " a.maTecnologiaId = :maTecnologiaId ,";
            strQuery += " a.maTecnologiaCodigo = :maTecnologiaCodigo ,";
            strQuery += " a.maTecnologiaValor = :maTecnologiaValor ,";
            strQuery += " a.costoPromedio = :costoPromedio ,";
            strQuery += " a.tipoFrecuencia = :tipoFrecuencia ,";
            strQuery += " a.frecuenciaUso = :frecuenciaUso ,";
            strQuery += " a.cantidadAfiliados = :cantidadAfiliados ,";
            strQuery += " a.maeAmbitoId = :maeAmbitoId ,";
            strQuery += " a.maeAmbitoCodigo = :maeAmbitoCodigo ,";
            strQuery += " a.maeAmbitoValor = :maeAmbitoValor ,";
            strQuery += " a.observacion = :observacion ,";
            strQuery += " a.fechaInicio = :fechaInicio ,";
            strQuery += " a.fechaFin = :fechaFin ,";
            //campos auditoria
            strQuery += " a.usuarioModifica = :usuarioModifica ,";
            strQuery += " a.fechaHoraModifica = :fechaHoraModifica ,";
            strQuery += " a.terminalModifica = :terminalModifica ";
            //campos objetos
            if(contrato.getCntContratosId() != null) {
                strQuery += ", a.cntContratosId.id = " + contrato.getCntContratosId().getId() + " ";
            }
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
    public CntContratoNotaTecnica eliminar(int id) throws Exception {
        CntContratoNotaTecnica obj = null;
        try {
            CntContratoNotasTecnicas ent = getEntityManager().find(CntContratoNotasTecnicas.class, id);
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
    public List<CntContratoNotaTecnica> consultarTodos() throws Exception {
        List<CntContratoNotaTecnica> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntContratoNotasTecnicas p "
                    + "ORDER BY p.id ";
            List<CntContratoNotasTecnicas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntContratoNotasTecnicas per : list) {
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

    public static CntContratoNotasTecnicas castNegocioEntidad(CntContratoNotaTecnica obj) {
        CntContratoNotasTecnicas per = new CntContratoNotasTecnicas();
        per.setId(obj.getId());
        per.setTipoTecnologia(obj.getTipoTecnologia());
        per.setMaTecnologiaId(obj.getMaTecnologiaId());
        per.setMaTecnologiaCodigo(obj.getMaTecnologiaCodigo());
        per.setMaTecnologiaValor(obj.getMaTecnologiaValor());
        per.setCostoPromedio(obj.getCostoPromedio());
        per.setTipoFrecuencia(obj.getTipoFrecuencia());
        per.setFrecuenciaUso(obj.getFrecuenciaUso());
        per.setCantidadAfiliados(obj.getCantidadAfiliados());
        per.setMaeAmbitoId(obj.getMaeAmbitoId());
        per.setMaeAmbitoCodigo(obj.getMaeAmbitoCodigo());
        per.setMaeAmbitoValor(obj.getMaeAmbitoValor());
        per.setObservacion(obj.getObservacion());
        per.setFechaInicio(obj.getFechaInicio());
        per.setFechaFin(obj.getFechaFin());
        //objetos
        if(obj.getCntContrato() != null) {
            per.setCntContratosId(new CntContratos(obj.getCntContrato().getId()));
        }
        //auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }

    @Override
    public CntContratoNotaTecnica consultar(int contratoId, int tecnologiaId, BigDecimal frecuenciaUso, int tipoFrecuencia, int cantidadAfiliados, BigDecimal costoPromedio, Date fechaInicio, Date fechaFin, int ambitoId) throws java.lang.Exception {
        CntContratoNotaTecnica result = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String strQuery = "FROM CntContratoNotasTecnicas nt "
                    + " WHERE nt.cntContratosId.id = " + contratoId
                    + " AND nt.maTecnologiaId = " + tecnologiaId
                    + " AND nt.frecuenciaUso = " + frecuenciaUso
                    + " AND nt.tipoFrecuencia = " + tipoFrecuencia
                    + " AND nt.cantidadAfiliados = " + cantidadAfiliados
                    + " AND nt.costoPromedio = " + costoPromedio
                    + " AND nt.fechaInicio = '" + sdf.format(fechaInicio) + "' "
                    + " AND nt.fechaFin = '" + sdf.format(fechaFin) + "' "
                    + " AND nt.maeAmbitoId = " + ambitoId;
            CntContratoNotasTecnicas data = (CntContratoNotasTecnicas) getEntityManager().createQuery(strQuery)
                    .getSingleResult();
                result =castEntidadNegocio(data);
        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    @Override
    public int contarCantidadTecnologiasEnContrato(int contratoId, int tecnologiaId) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntContratoDetalles c "
                    + " WHERE c.cntContratosId.id = " + contratoId
                    + " AND c.maTecnologiaId = " + tecnologiaId;
            
            Query query = getEntityManager().createQuery(strQuery);
            cant = (int) (long) query.getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

    @Override
    public List<CntContratoNotaTecnica> consultarPorContrato(int contratoId) throws java.lang.Exception {
        List<CntContratoNotaTecnica> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntContratoNotasTecnicas nt "
                    + " WHERE nt.cntContratosId.id = " + contratoId
                    + " ORDER BY nt.id ";
            List<CntContratoNotasTecnicas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntContratoNotasTecnicas per : list) {
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
