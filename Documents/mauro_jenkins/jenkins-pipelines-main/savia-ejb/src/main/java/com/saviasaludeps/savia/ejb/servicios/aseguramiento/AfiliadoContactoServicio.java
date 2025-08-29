package com.saviasaludeps.savia.ejb.servicios.aseguramiento;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContacto;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliadoContactos;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoContactoRemoto;
import javax.persistence.TypedQuery;

/**
 *
 * @author jose perez hernandez
 */
@Stateless
@Remote(AfiliadoContactoRemoto.class)
public class AfiliadoContactoServicio extends GenericoServicio implements AfiliadoContactoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AsegAfiliadoContactos p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        /*case "asegAfiliado.idAfiliado":
                            strQuery += "AND p.asegAfiliadosId.idAfiliado = '" + e.getValue() + "' ";
                            break;
                        case "maeTipoDocumentoId":
                            strQuery += "AND p.maeTipoDocumentoId = " +  e.getValue() + " ";
                            break;
                        case "numeroDocumento":
                            strQuery += "AND p.numeroDocumento = '" + e.getValue() + "' ";
                            break;
                        case "primerApellido":
                            strQuery += "AND p.primerApellido LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "segundoApellido":
                            strQuery += "AND p.segundoApellido LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "primerNombre":
                            strQuery += "AND p.primerNombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "segundoNombre":
                            strQuery += "AND p.segundoNombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += "AND p.tipo = " + e.getValue() + " ";
                            break;*/
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getSingleResult();
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
    public List<AsegAfiliadoContacto> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AsegAfiliadoContacto> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM AsegAfiliadoContactos p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        /*case "asegAfiliado.idAfiliado":
                            strQuery += "AND p.asegAfiliadosId.idAfiliado = '" + e.getValue() + "' ";
                            break;
                        case "maeTipoDocumentoId":
                            strQuery += "AND p.maeTipoDocumentoId = " +  e.getValue() + " ";
                            break;
                        case "numeroDocumento":
                            strQuery += "AND p.numeroDocumento = '" + e.getValue() + "' ";
                            break;
                        case "primerApellido":
                            strQuery += "AND p.primerApellido LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "segundoApellido":
                            strQuery += "AND p.segundoApellido LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "primerNombre":
                            strQuery += "AND p.primerNombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "segundoNombre":
                            strQuery += "AND p.segundoNombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += "AND p.tipo = " + e.getValue() + " ";
                            break;*/
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
            List<AsegAfiliadoContactos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (AsegAfiliadoContactos per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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
    public AsegAfiliadoContacto consultar(int id) throws Exception {
        AsegAfiliadoContacto objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AsegAfiliadoContactos) getEntityManager().find(AsegAfiliadoContactos.class, id));
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
    public int insertar(AsegAfiliadoContacto obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidadLargo(obj)).getId();
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
    public void actualizar(AsegAfiliadoContacto obj) throws Exception {
        try {
            getEntityManager().merge(castNegocioEntidadLargo(obj));
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AsegAfiliadoContacto eliminar(int id) throws Exception {
        AsegAfiliadoContacto obj = null;
        try {
            AsegAfiliadoContactos ent = getEntityManager().find(AsegAfiliadoContactos.class, id);
            if (ent != null) {
                obj = castEntidadNegocioLargo(ent);
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
    public List<AsegAfiliadoContacto> consultarTodos() throws Exception {
        List<AsegAfiliadoContacto> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegAfiliadoContactos p "
                    + "ORDER BY p.fechaHoraCrea DESC";
            List<AsegAfiliadoContactos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegAfiliadoContactos per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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
    public AsegAfiliadoContacto consultarUltimoPorAfiliadoYTipo(int idAfiliado, String tipoContacto) throws Exception {
        AsegAfiliadoContacto objRes = null;
        try {
            TypedQuery<AsegAfiliadoContactos> query = getEntityManager().createQuery(
                    "SELECT a FROM AsegAfiliadoContactos a WHERE a.asegAfiliadosId.id = :idAfiliado"
                    + " and a.maeTipoContactoCodigo = :tipoContacto ORDER BY a.id DESC",
                    AsegAfiliadoContactos.class
            );
            query.setParameter("idAfiliado", idAfiliado);
            query.setParameter("tipoContacto", tipoContacto);

            query.setMaxResults(1);

            AsegAfiliadoContactos resultado = query.getSingleResult();
            objRes = castEntidadNegocioLargo(resultado);
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception("CONSULTAR_ULTIMO_AFILIADO_CONTACTO", e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    public static AsegAfiliadoContacto castEntidadNegocioLargo(AsegAfiliadoContactos per) {
        AsegAfiliadoContacto obj = new AsegAfiliadoContacto();
        obj.setId(per.getId());
        obj.setNumeroContacto(per.getNumeroContacto());
        obj.setMaeTipoContactoId(per.getMaeTipoContactoId());
        obj.setMaeTipoContactoCodigo(per.getMaeTipoContactoCodigo());
        obj.setMaeTipoContactoValor(per.getMaeTipoContactoValor());
        obj.setObservacion(per.getObservacion());
        obj.setActivo(per.getActivo());
        //objetos
        if (per.getAsegAfiliadosId() != null) {
            obj.setAsegAfiliado(new AsegAfiliado(per.getAsegAfiliadosId().getId(), per.getAsegAfiliadosId().getIdAfiliado(),
                    per.getAsegAfiliadosId().getPrimerNombre(), per.getAsegAfiliadosId().getSegundoNombre(), per.getAsegAfiliadosId().getPrimerApellido(), per.getAsegAfiliadosId().getSegundoApellido()));
        }
        // auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        return obj;
    }

    public static AsegAfiliadoContactos castNegocioEntidadLargo(AsegAfiliadoContacto obj) {
        AsegAfiliadoContactos per = new AsegAfiliadoContactos();
        per.setId(obj.getId());
        per.setNumeroContacto(obj.getNumeroContacto());
        per.setMaeTipoContactoId(obj.getMaeTipoContactoId());
        per.setMaeTipoContactoCodigo(obj.getMaeTipoContactoCodigo());
        per.setMaeTipoContactoValor(obj.getMaeTipoContactoValor());
        per.setObservacion(obj.getObservacion());
        per.setActivo(obj.isActivo());
        //objetos
        if (obj.getAsegAfiliado() != null) {
            per.setAsegAfiliadosId(new AsegAfiliados(obj.getAsegAfiliado().getId()));
        }
        // auditoria
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        return per;
    }
}
