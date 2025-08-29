package com.saviasaludeps.savia.ejb.servicios.contratacion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoHistorico;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CntContratoDetalles;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.ejb.entidades.CntContratoHistoricos;
import com.saviasaludeps.savia.ejb.entidades.CntContratoSedes;
import com.saviasaludeps.savia.ejb.entidades.CntContratos;
import javax.persistence.Query;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoHistoricoRemoto;
import org.hibernate.Session;

@Stateless
@Remote(CntContratoHistoricoRemoto.class)
public class CntContratoHistoricoServicio extends GenericoServicio implements CntContratoHistoricoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntContratoHistoricos c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null &&
                    paramConsulta.getParametroConsulta2() != null &&
                    paramConsulta.getParametroConsulta3() != null ) {
                strQuery += "AND c.cntContratosId.id = " +(Integer) paramConsulta.getParametroConsulta1() + " ";
                strQuery += "AND c.cntContratoSedesId.id = " +(Integer) paramConsulta.getParametroConsulta2() + " ";
                strQuery += "AND c.cntContratoDetallesId.id = " +(Integer) paramConsulta.getParametroConsulta3() + " ";
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
    public List<CntContratoHistorico> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CntContratoHistorico> listResult = new ArrayList();
        try {
            String strQuery = "SELECT c FROM CntContratoHistoricos c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null &&
                    paramConsulta.getParametroConsulta2() != null &&
                    paramConsulta.getParametroConsulta3() != null ) {
                strQuery += "AND c.cntContratosId.id = " +(Integer) paramConsulta.getParametroConsulta1() + " ";
                strQuery += "AND c.cntContratoSedesId.id = " +(Integer) paramConsulta.getParametroConsulta2() + " ";
                strQuery += "AND c.cntContratoDetallesId.id = " +(Integer) paramConsulta.getParametroConsulta3() + " ";
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
            List<CntContratoHistoricos> list = query.setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntContratoHistoricos per : list) {
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

    private CntContratoHistorico castEntidadNegocio(CntContratoHistoricos per) {
        CntContratoHistorico neg = new CntContratoHistorico();
        neg.setId(per.getId());
        neg.setToString(per.getToString());
        neg.setOrigen(per.getOrigen());
        neg.setTipo(per.getTipo());
        //objetos
        if(per.getCntContratosId()!= null) {
            neg.setCntContrato(new CntContrato(per.getCntContratosId().getId()));
        }
        if(per.getCntContratoSedesId()!= null) {
            neg.setCntContratoSede(new CntContratoSede(per.getCntContratoSedesId().getId()));
        }
        if(per.getCntContratoDetallesId()!= null) {
            GsonBuilder gsonBuilderRespuesta = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss") //Formato fecha 
            .serializeNulls();
            Gson gson = gsonBuilderRespuesta.create();
            CntContratoDetalle objetoContratoDetalle = gson.fromJson(per.getToString(), CntContratoDetalle.class);
            objetoContratoDetalle.setId(per.getCntContratoDetallesId().getId());
            neg.setCntContratoDetalle(objetoContratoDetalle);
        }
        //auditoria
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    @Override
    public CntContratoHistorico consultar(int id) throws Exception {
        CntContratoHistorico objRes = null;
        try {
            objRes = castEntidadNegocio((CntContratoHistoricos) getEntityManager().find(CntContratoHistoricos.class, id));
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
    public int insertar(CntContratoHistorico obj) throws Exception {
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
    public void actualizar(CntContratoHistorico obj) throws Exception {
        try {
            CntContratoHistoricos contrato = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            //session.update(per);
            String strQuery = "UPDATE CntContratoHistoricos a SET ";
            strQuery += " a.toString = :toString ,";
            strQuery += " a.origen = :origen ,";
            strQuery += " a.tipo = :tipo ";
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
    public CntContratoHistorico eliminar(int id) throws Exception {
        CntContratoHistorico obj = null;
        try {
            CntContratoHistoricos ent = getEntityManager().find(CntContratoHistoricos.class, id);
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
    public List<CntContratoHistorico> consultarTodos() throws Exception {
        List<CntContratoHistorico> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntContratoHistoricos p "
                    + "ORDER BY p.id ";
            List<CntContratoHistoricos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntContratoHistoricos per : list) {
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

    public static CntContratoHistoricos castNegocioEntidad(CntContratoHistorico obj) {
        CntContratoHistoricos per = new CntContratoHistoricos();
        per.setId(obj.getId());
        per.setToString(obj.getToString());
        per.setOrigen(obj.getOrigen());
        per.setTipo(obj.getTipo());
        //objetos
        if(obj.getCntContrato() != null) {
            per.setCntContratosId(new CntContratos(obj.getCntContrato().getId()));
        }
        if(obj.getCntContratoSede() != null) {
            per.setCntContratoSedesId(new CntContratoSedes(obj.getCntContratoSede().getId()));
        }
        if(obj.getCntContratoDetalle() != null) {
            per.setCntContratoDetallesId(new CntContratoDetalles(obj.getCntContratoDetalle().getId()));
        }
        //auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        return per;
    }
    
    @Override
    public boolean consultarHistoricoExistente(int tipo, int contratoId, int contratoSedeId, int contratoDetalleId) throws Exception{
        boolean resultado = false;
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntContratoHistoricos c "
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
        if (cant > 0 ) {
            resultado = true;
        }
        return resultado;
    }
    
    @Override
    public CntContratoHistorico consultarUltimoHistoricoExistente(int tipo, int contratoId, int contratoSedeId, int contratoDetalleId) throws Exception{
        CntContratoHistorico resultado = null;
        try {
            String strQuery = "SELECT c FROM CntContratoHistoricos c "
                    + "WHERE c.id > 0 ";
            strQuery += "AND c.tipo = " + tipo + " ";
            strQuery += "AND c.cntContratosId.id = " + contratoId + " ";
            if (tipo == CntContratoHistorico.TIPO_DETALLE) {//contrato detalle
                strQuery += "AND c.cntContratoSedesId.id = " + contratoSedeId + " ";
                strQuery += "AND c.cntContratoDetallesId.id = " + contratoDetalleId + " ";
            }
            strQuery += " order by c.id desc ";
            
            Query query = getEntityManager().createQuery(strQuery);
            CntContratoHistoricos his =  (CntContratoHistoricos) query.getResultStream().findFirst().orElse(null);
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
