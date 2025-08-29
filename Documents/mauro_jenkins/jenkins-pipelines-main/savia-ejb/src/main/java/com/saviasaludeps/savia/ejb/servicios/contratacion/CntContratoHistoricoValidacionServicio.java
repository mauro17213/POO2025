package com.saviasaludeps.savia.ejb.servicios.contratacion;

import com.saviasaludeps.savia.negocio.contratacion.CntContratoHistoricoValidacionRemoto;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoHistoricoValidacion;
import com.saviasaludeps.savia.ejb.entidades.CntContratoHistoricoValidaciones;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoHistorico;
import com.saviasaludeps.savia.ejb.entidades.CntContratoDetalles;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CntContratoSedes;
import com.saviasaludeps.savia.ejb.entidades.CntContratos;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.ejb.Remote;
import java.util.List;
import java.util.Map;

@Stateless
@Remote(CntContratoHistoricoValidacionRemoto.class)
public class CntContratoHistoricoValidacionServicio extends GenericoServicio implements CntContratoHistoricoValidacionRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntContratoHistoricoValidaciones c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null
                    && paramConsulta.getParametroConsulta3() != null) {
                strQuery += "AND c.cntContratosId.id = " + (Integer) paramConsulta.getParametroConsulta1() + " ";
                strQuery += "AND c.cntContratoSedesId.id = " + (Integer) paramConsulta.getParametroConsulta2() + " ";
                strQuery += "AND c.cntContratoDetallesId.id = " + (Integer) paramConsulta.getParametroConsulta3() + " ";
            } else {
                throw new Exception("No se configuraron parámetros del contrato detalle.");
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id = " + (String) e.getValue() + " ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += "AND c.fechaHoraCrea = '" + (String) e.getValue() + "' ";
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
    public List<CntContratoHistoricoValidacion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CntContratoHistoricoValidacion> listResult = new ArrayList();
        try {
            String strQuery = "SELECT c FROM CntContratoHistoricoValidaciones c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null
                    && paramConsulta.getParametroConsulta3() != null) {
                strQuery += "AND c.cntContratosId.id = " + (Integer) paramConsulta.getParametroConsulta1() + " ";
                strQuery += "AND c.cntContratoSedesId.id = " + (Integer) paramConsulta.getParametroConsulta2() + " ";
                strQuery += "AND c.cntContratoDetallesId.id = " + (Integer) paramConsulta.getParametroConsulta3() + " ";
            } else {
                throw new Exception("No se configuraron parámetros del contrato detalle.");
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id = " + (String) e.getValue() + " ";
                            break;
                        case "origen":
                            strQuery += "AND c.origen = " + (String) e.getValue() + " ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += "AND c.fechaHoraCrea = '" + (String) e.getValue() + "' ";
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
            List<CntContratoHistoricoValidaciones> list = query.setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntContratoHistoricoValidaciones per : list) {
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

    private CntContratoHistoricoValidacion castEntidadNegocio(CntContratoHistoricoValidaciones per) {
        CntContratoHistoricoValidacion neg = new CntContratoHistoricoValidacion();
        neg.setId(per.getId());
        neg.setTipoTecnologia(per.getTipoTecnologia());
        neg.setMaTecnologiaId(per.getMaTecnologiaId());
        neg.setMaTecnologiaCodigo(per.getMaTecnologiaCodigo());
        neg.setMaTecnologiaValor(per.getMaTecnologiaValor());
        neg.setValor(per.getValor());
        neg.setFechaInicio(per.getFechaInicio());
        neg.setFechaFin(per.getFechaFin());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        //2024-02-22 jyperez nuevos campos
        neg.setTipoManualTarifario(per.getTipoManualTarifario());
        neg.setMaManualTarifarioCodigo(per.getMaManualTarifarioCodigo());
        neg.setMaManualTarifarioAgno(per.getMaManualTarifarioAgno());
        neg.setValorManual(per.getValorManual());
        neg.setPorcentajeVariacion(per.getPorcentajeVariacion());
        //objetos
        if (per.getCntPrestadorSedesId() != null) {
            neg.setCntPrestadorSedeId(per.getCntPrestadorSedesId().getId());
            neg.setCntPrestadorSedeCodigoHabilitacion(per.getCntPrestadorSedesCodigoHabilitacion());
        }
        if (per.getCntPrestadoresId() != null) {
            neg.setCntPrestadorId(per.getCntPrestadoresId().getId());
            neg.setCntPrestadorNumeroDocumento(per.getCntPrestadoresNumeroDocumento());
            neg.setCntPrestadorCodigoMinSalud(per.getCntPrestadoresCodigoMinsalud());
        }
        if (per.getCntContratosId() != null) {
            neg.setCntContratoId(per.getCntContratosId().getId());
        }
        if (per.getCntContratoSedesId() != null) {
            neg.setCntContratoSedeId(per.getCntContratoSedesId().getId());
        }
        if (per.getCntContratoDetallesId() != null) {
            neg.setCntContratoDetalleId(per.getCntContratoDetallesId().getId());
        }
        //auditoria
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    @Override
    public CntContratoHistoricoValidacion consultar(int id) throws Exception {
        CntContratoHistoricoValidacion objRes = null;
        try {
            objRes = castEntidadNegocio((CntContratoHistoricoValidaciones) getEntityManager().find(CntContratoHistoricoValidaciones.class, id));
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
    public int insertar(CntContratoHistoricoValidacion obj) throws Exception {
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
    public void actualizar(CntContratoHistoricoValidacion obj) throws Exception {
        try {
            CntContratoHistoricoValidaciones contrato = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            //session.update(per);
            String strQuery = "UPDATE CntContratoHistoricoValidaciones a SET ";
            strQuery += " a.tipoTecnologia = :tipoTecnologia,";
            strQuery += " a.fechaInicio = :fechaInicio,";
            strQuery += " a.fechaFin = :fechaFin";
            //campos objetos
            if(contrato.getCntContratosId() != null) {
                strQuery += ", a.cntContratosId.id = " + contrato.getCntContratosId().getId() + " ";
            }
            if(contrato.getCntContratoSedesId()!= null) {
                strQuery += ", a.cntContratoSedesId.id = " + contrato.getCntContratoSedesId().getId() + " ";
            }
            if(contrato.getCntContratoDetallesId()!= null) {
                strQuery += ", a.cntContratoDetallesId.id = " + contrato.getCntContratoDetallesId().getId() + " ";
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
    public CntContratoHistoricoValidacion eliminar(int id) throws Exception {
        CntContratoHistoricoValidacion obj = null;
        try {
            CntContratoHistoricoValidaciones ent = getEntityManager().find(CntContratoHistoricoValidaciones.class, id);
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
    public List<CntContratoHistoricoValidacion> consultarTodos() throws Exception {
        List<CntContratoHistoricoValidacion> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntContratoHistoricoValidaciones p "
                    + "ORDER BY p.id ";
            List<CntContratoHistoricoValidaciones> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntContratoHistoricoValidaciones per : list) {
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

    public static CntContratoHistoricoValidaciones castNegocioEntidad(CntContratoHistoricoValidacion obj) {
        CntContratoHistoricoValidaciones contratoHistoricoValidaciones = new CntContratoHistoricoValidaciones();
        
        contratoHistoricoValidaciones.setId(obj.getId());
        contratoHistoricoValidaciones.setTipoTecnologia(obj.getTipoTecnologia());
        contratoHistoricoValidaciones.setMaTecnologiaId(obj.getMaTecnologiaId());
        contratoHistoricoValidaciones.setMaTecnologiaCodigo(obj.getMaTecnologiaCodigo());
        contratoHistoricoValidaciones.setMaTecnologiaValor(obj.getMaTecnologiaValor());
        contratoHistoricoValidaciones.setValor(obj.getValor());
        contratoHistoricoValidaciones.setFechaInicio(obj.getFechaInicio());
        contratoHistoricoValidaciones.setFechaFin(obj.getFechaFin());
        contratoHistoricoValidaciones.setFechaHoraCrea(obj.getFechaHoraCrea());
        //2024-02-22 jyperez nuevos campos
        contratoHistoricoValidaciones.setTipoManualTarifario(obj.getTipoManualTarifario());
        contratoHistoricoValidaciones.setMaManualTarifarioCodigo(obj.getMaManualTarifarioCodigo());
        contratoHistoricoValidaciones.setMaManualTarifarioAgno(obj.getMaManualTarifarioAgno());
        contratoHistoricoValidaciones.setValorManual(obj.getValorManual());
        contratoHistoricoValidaciones.setPorcentajeVariacion(obj.getPorcentajeVariacion());
        //Objetos
        if (obj.getCntPrestadorId() != 0) {
            contratoHistoricoValidaciones.setCntPrestadoresId(new CntPrestadores(obj.getCntPrestadorId()));
            contratoHistoricoValidaciones.setCntPrestadoresNumeroDocumento(obj.getCntPrestadorNumeroDocumento());
            contratoHistoricoValidaciones.setCntPrestadoresCodigoMinsalud(obj.getCntPrestadorCodigoMinSalud());
        }
        if (obj.getCntPrestadorSedeId() != 0){
            contratoHistoricoValidaciones.setCntPrestadorSedesId(new CntPrestadorSedes(obj.getCntPrestadorSedeId()));
            contratoHistoricoValidaciones.setCntPrestadorSedesCodigoHabilitacion(obj.getCntPrestadorSedeCodigoHabilitacion());
        }
        if (obj.getCntContratoId() != 0){
            contratoHistoricoValidaciones.setCntContratosId(new CntContratos(obj.getCntContratoId()));
        }
        if (obj.getCntContratoSedeId() != 0){
            contratoHistoricoValidaciones.setCntContratoSedesId(new CntContratoSedes(obj.getCntContratoSedeId()));
        }
        if (obj.getCntContratoDetalleId() != 0){
            contratoHistoricoValidaciones.setCntContratoDetallesId(new CntContratoDetalles(obj.getCntContratoDetalleId()));
        }
        
        return contratoHistoricoValidaciones;
    }

    @Override
    public boolean consultarHistoricoValidacionExistente(int tipo, int contratoId, int contratoSedeId, int contratoDetalleId) throws Exception {
        boolean resultado = false;
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntContratoHistoricoValidaciones c "
                    + "WHERE c.id > 0 ";
            strQuery += "AND c.tipo = " + tipo + " ";
            strQuery += "AND c.cntContratosId.id = " + contratoId + " ";
            if (tipo == CntContratoHistorico.TIPO_DETALLE) {//contrato detalle
                strQuery += "AND c.cntContratoSedesId.id = " + contratoSedeId + " ";
                strQuery += "AND c.cntContratoDetallesId.id = " + contratoDetalleId + " ";
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
        if (cant > 0) {
            resultado = true;
        }
        return resultado;
    }
    
    @Override
    public CntContratoHistoricoValidacion consultarUltimoHistoricoValidacion(int tipo, int contratoId, int contratoSedeId, int contratoDetalleId) throws Exception {
        CntContratoHistoricoValidacion resultado = null;
        try {
            String strQuery = "SELECT c FROM CntContratoHistoricoValidaciones c "
                    + "WHERE c.id > 0 ";
            strQuery += "AND c.cntContratosId.id = " + contratoId + " ";
            if (tipo == CntContratoHistorico.TIPO_DETALLE) {//contrato detalle
                strQuery += "AND c.cntContratoSedesId.id = " + contratoSedeId + " ";
                strQuery += "AND c.cntContratoDetallesId.id = " + contratoDetalleId + " ";
            }
            strQuery += " ORDER BY c.id DESC ";
            Query query = getEntityManager().createQuery(strQuery);
            CntContratoHistoricoValidaciones his =  (CntContratoHistoricoValidaciones) query.getResultStream().findFirst().orElse(null);
            resultado = castEntidadNegocio(his);
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return resultado;
    }

}
