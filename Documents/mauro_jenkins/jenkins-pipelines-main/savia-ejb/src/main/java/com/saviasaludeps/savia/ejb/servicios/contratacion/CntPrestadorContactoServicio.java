package com.saviasaludeps.savia.ejb.servicios.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorContacto;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorContactos;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import javax.persistence.Query;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorContactoRemoto;
import org.hibernate.Session;

@Stateless
@Remote(CntPrestadorContactoRemoto.class)
public class CntPrestadorContactoServicio extends GenericoServicio implements CntPrestadorContactoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntPrestadorContactos c "
                    + "WHERE c.id > 0 ";
//            if (paramConsulta.getParametroConsulta1() != null &&
//                    paramConsulta.getParametroConsulta2() != null &&
//                    paramConsulta.getParametroConsulta3() != null ) {
//                strQuery += "AND c.cntContratosId.id = " +(Integer) paramConsulta.getParametroConsulta1() + " ";
//                strQuery += "AND c.cntContratoSedesId.id = " +(Integer) paramConsulta.getParametroConsulta2() + " ";
//                strQuery += "AND c.cntContratoDetallesId.id = " +(Integer) paramConsulta.getParametroConsulta3() + " ";
//            } else {
//                throw new Exception("No se configuraron parámetros del contrato detalle.");
//            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id = " + (String) e.getValue() + " ";
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
    public List<CntPrestadorContacto> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CntPrestadorContacto> listResult = new ArrayList();
        try {
            String strQuery = "SELECT c FROM CntPrestadorContactos c "
                    + "WHERE c.id > 0 ";
//            if (paramConsulta.getParametroConsulta1() != null &&
//                    paramConsulta.getParametroConsulta2() != null &&
//                    paramConsulta.getParametroConsulta3() != null ) {
//                strQuery += "AND c.cntContratosId.id = " +(Integer) paramConsulta.getParametroConsulta1() + " ";
//                strQuery += "AND c.cntContratoSedesId.id = " +(Integer) paramConsulta.getParametroConsulta2() + " ";
//                strQuery += "AND c.cntContratoDetallesId.id = " +(Integer) paramConsulta.getParametroConsulta3() + " ";
//            } else {
//                throw new Exception("No se configuraron parámetros del contrato detalle.");
//            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id = " + (String) e.getValue() + " ";
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
            List<CntPrestadorContactos> list = query.setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntPrestadorContactos per : list) {
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

    private CntPrestadorContacto castEntidadNegocio(CntPrestadorContactos per) {
        CntPrestadorContacto neg = new CntPrestadorContacto();
        neg.setId(per.getId());
        neg.setMaeTipoContactoId(per.getMaeTipoContactoId());
        neg.setMaeTipoContactoCodigo(per.getMaeTipoContactoCodigo());
        neg.setMaeTipoContactoValor(per.getMaeTipoContactoValor());
        neg.setMaeAreaContactoId(per.getMaeAreaContactoId());
        neg.setMaeAreaContactoCodigo(per.getMaeAreaContactoCodigo());
        neg.setMaeAreaContactoValor(per.getMaeAreaContactoValor());
        neg.setContacto(per.getContacto());
        neg.setAutorizaEnvio(per.getAutorizaEnvio());
        neg.setActivo(per.getActivo());
        neg.setObservacion(per.getObservacion());
        //objetos
        if (per.getCntPrestadoresId() != null) {
            neg.setCntPrestador(new CntPrestador(per.getCntPrestadoresId().getId()));
        }
        if (per.getCntPrestadorSedesId() != null) {
            neg.setCntPrestadorSede(new CntPrestadorSede(per.getCntPrestadorSedesId().getId()));
        }
        //auditoria
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    @Override
    public CntPrestadorContacto consultar(int id) throws Exception {
        CntPrestadorContacto objRes = null;
        try {
            objRes = castEntidadNegocio((CntPrestadorContactos) getEntityManager().find(CntPrestadorContactos.class, id));
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
    public int insertar(CntPrestadorContacto obj) throws Exception {
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
    public void actualizar(CntPrestadorContacto obj) throws Exception {
        try {
            CntPrestadorContactos contacto = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            //session.update(per);
            String strQuery = "UPDATE CntPrestadorContactos p SET ";
            strQuery += " p.maeTipoContactoId = :maeTipoContactoId ,";
            strQuery += " p.maeTipoContactoCodigo = :maeTipoContactoCodigo ,";
            strQuery += " p.maeTipoContactoValor = :maeTipoContactoValor ,";
            strQuery += " p.maeAreaContactoId = :maeAreaContactoId ,";
            strQuery += " p.maeAreaContactoCodigo = :maeAreaContactoCodigo ,";
            strQuery += " p.maeAreaContactoValor = :maeAreaContactoValor ,";
            strQuery += " p.contacto = :contacto ,";
            strQuery += " p.autorizaEnvio = :autorizaEnvio ,";
            strQuery += " p.activo = :activo ,";
            strQuery += " p.observacion = :observacion ";
            //campos objetos
            if(contacto.getCntPrestadoresId() != null) {
                strQuery += ", p.cntPrestadoresId.id = " + contacto.getCntPrestadoresId().getId() + " ";
            }
            if(contacto.getCntPrestadorSedesId()!= null) {
                strQuery += ", p.cntPrestadorSedesId.id = " + contacto.getCntPrestadorSedesId().getId() + " ";
            }
            strQuery += " WHERE p.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(contacto);
            query.executeUpdate();
            
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CntPrestadorContacto eliminar(int id) throws Exception {
        CntPrestadorContacto obj = null;
        try {
            CntPrestadorContactos ent = getEntityManager().find(CntPrestadorContactos.class, id);
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
    public List<CntPrestadorContacto> consultarTodos() throws Exception {
        List<CntPrestadorContacto> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntPrestadorContactos p "
                    + "ORDER BY p.id ";
            List<CntPrestadorContactos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntPrestadorContactos per : list) {
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

    public static CntPrestadorContactos castNegocioEntidad(CntPrestadorContacto obj) {
        CntPrestadorContactos per = new CntPrestadorContactos();
        per.setId(obj.getId());
        per.setMaeTipoContactoId(obj.getMaeTipoContactoId());
        per.setMaeTipoContactoCodigo(obj.getMaeTipoContactoCodigo());
        per.setMaeTipoContactoValor(obj.getMaeTipoContactoValor());
        per.setMaeAreaContactoId(obj.getMaeAreaContactoId());
        per.setMaeAreaContactoCodigo(obj.getMaeAreaContactoCodigo());
        per.setMaeAreaContactoValor(obj.getMaeAreaContactoValor());
        per.setContacto(obj.getContacto());
        per.setAutorizaEnvio(obj.isAutorizaEnvio());
        per.setActivo(obj.isActivo());
        per.setObservacion(obj.getObservacion());
        //objetos
        if (obj.getCntPrestador() != null) {
            per.setCntPrestadoresId(new CntPrestadores(obj.getCntPrestador().getId()));
        }
        if (obj.getCntPrestadorSede() != null) {
            per.setCntPrestadorSedesId(new CntPrestadorSedes(obj.getCntPrestadorSede().getId()));
        }
        //auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        return per;
    }

    @Override
    public List<CntPrestadorContacto> consultarPorCntContratoSede(int prestador, int prestadorSede) throws java.lang.Exception {
        List<CntPrestadorContacto> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntPrestadorContactos p "
                    + " WHERE p.cntPrestadoresId.id = " + prestador
                    + " AND p.cntPrestadorSedesId.id = " + prestadorSede
                    + " ORDER BY p.id ";
            List<CntPrestadorContactos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntPrestadorContactos per : list) {
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
    public List<CntPrestadorContacto> consultarPorCntContratoSedeYTipo(int prestador, int prestadorSede, String tipo) throws java.lang.Exception {
        List<CntPrestadorContacto> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntPrestadorContactos p "
                    + " WHERE p.cntPrestadoresId.id = " + prestador
                    + " AND p.cntPrestadorSedesId.id = " + prestadorSede
                    + " AND p.maeTipoContactoCodigo = '" + tipo + "' "
                    + " ORDER BY p.id ";
            List<CntPrestadorContactos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntPrestadorContactos per : list) {
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
    public List<CntPrestadorContacto> consultarPorCntContratoSedeYArea(int prestador, int prestadorSede, String area) throws java.lang.Exception {
        List<CntPrestadorContacto> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntPrestadorContactos p "
                    + " WHERE p.cntPrestadoresId.id = " + prestador
                    + " AND p.cntPrestadorSedesId.id = " + prestadorSede
                    + " AND p.maeAreaContactoCodigo = '" + area + "' "
                    + " ORDER BY p.id ";
            List<CntPrestadorContactos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntPrestadorContactos per : list) {
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
    public List<CntPrestadorContacto> consultarPorCntContratoSedeTipoYArea(int prestador, int prestadorSede, String tipo, String area) throws java.lang.Exception {
        List<CntPrestadorContacto> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntPrestadorContactos p "
                    + " WHERE p.cntPrestadoresId.id = " + prestador
                    + " AND p.cntPrestadorSedesId.id = " + prestadorSede
                    + " AND p.maeTipoContactoCodigo = '" + tipo + "' "
                    + " AND p.maeAreaContactoCodigo = '" + area + "' "
                    + " ORDER BY p.id ";
            List<CntPrestadorContactos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntPrestadorContactos per : list) {
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
