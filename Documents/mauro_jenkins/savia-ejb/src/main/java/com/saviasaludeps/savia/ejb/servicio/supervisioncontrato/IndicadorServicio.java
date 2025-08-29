/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.servicio.supervisioncontrato;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.supervisioncontrato.ScIndicador;
import com.saviasaludeps.savia.ejb.entidades.ScIndicadores;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.supervisionContrato.IndicadoresRemoto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Session;

/**
 *
 * @author aguevara
 */
@Stateless
@Remote(IndicadoresRemoto.class)
public class IndicadorServicio extends GenericoServicio implements IndicadoresRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(p) FROM ScIndicadores p WHERE p.borrado = 0 AND p.id > 0 ");
            Map<String, Object> parametros = new HashMap<>();

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry<String, Object> e : paramConsulta.getFiltros().entrySet()) {
                    String key = e.getKey();
                    Object value = e.getValue();
                    switch (key) {
                    case "id":
                        strQuery.append(" AND p.id = :id ");
                        parametros.put("id", Integer.valueOf(value.toString()));
                        break;
                    case "codigo":
                        strQuery.append(" AND p.codigo = :codigo ");
                        parametros.put("codigo", value);
                        break;
                    case "nombre":
                        strQuery.append(" AND p.nombre = :nombre ");
                        parametros.put("nombre", value);
                        break;
                    case "maeAreaValor":
                        strQuery.append(" AND p.maeAreaValor = :maeAreaValor ");
                        parametros.put("maeAreaValor", value);
                        break;
                    case "maeClaseValor":
                        strQuery.append(" AND p.maeClaseValor = :maeClaseValor ");
                        parametros.put("maeClaseValor", value);
                        break;
                    case "maeMacroprocesoValor":
                        strQuery.append(" AND p.maeMacroprocesoValor = :maeMacroprocesoValor ");
                        parametros.put("maeMacroprocesoValor", value);
                        break;
                    case "maeProcesoValor":
                        strQuery.append(" AND p.maeProcesoValor = :maeProcesoValor ");
                        parametros.put("maeProcesoValor", value);
                        break;
                }
                }
            }

            TypedQuery<Long> query = getEntityManager().createQuery(strQuery.toString(), Long.class);
            for (Map.Entry<String, Object> param : parametros.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }

            cant = query.getSingleResult().intValue();

        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

    @Override
public List<ScIndicador> consultarLista(ParamConsulta paramConsulta) throws Exception {
    List<ScIndicador> listResult = new ArrayList<>();
    try {
        StringBuilder strQuery = new StringBuilder("SELECT p FROM ScIndicadores p WHERE p.borrado = 0 AND p.id > 0 ");
        Map<String, Object> parametros = new HashMap<>();

        if (paramConsulta.getFiltros() != null) {
            for (Map.Entry<String, Object> e : paramConsulta.getFiltros().entrySet()) {
                String key = e.getKey();
                Object value = e.getValue();

                switch (key) {
                    case "id":
                        strQuery.append(" AND p.id = :id ");
                        parametros.put("id", Integer.valueOf(value.toString()));
                        break;
                    case "codigo":
                        strQuery.append(" AND p.codigo = :codigo ");
                        parametros.put("codigo", value);
                        break;
                    case "nombre":
                        strQuery.append(" AND p.nombre = :nombre ");
                        parametros.put("nombre", value);
                        break;
                    case "maeAreaValor":
                        strQuery.append(" AND p.maeAreaValor = :maeAreaValor ");
                        parametros.put("maeAreaValor", value);
                        break;
                    case "maeClaseValor":
                        strQuery.append(" AND p.maeClaseValor = :maeClaseValor ");
                        parametros.put("maeClaseValor", value);
                        break;
                    case "maeMacroprocesoValor":
                        strQuery.append(" AND p.maeMacroprocesoValor = :maeMacroprocesoValor ");
                        parametros.put("maeMacroprocesoValor", value);
                        break;
                    case "maeProcesoValor":
                        strQuery.append(" AND p.maeProcesoValor = :maeProcesoValor ");
                        parametros.put("maeProcesoValor", value);
                        break;
                }
            }
        }

        // Ordenamiento
        strQuery.append(" ORDER BY ");
        if (paramConsulta.getOrden() != null) {
            strQuery.append("p.").append(paramConsulta.getOrden()).append(" ");
            strQuery.append(paramConsulta.isAscendente() ? "ASC" : "DESC");
        } else {
            strQuery.append("p.id DESC");
        }

        TypedQuery<ScIndicadores> query = getEntityManager()
            .createQuery(strQuery.toString(), ScIndicadores.class)
            .setFirstResult(paramConsulta.getPrimerRegistro())
            .setMaxResults(paramConsulta.getRegistrosPagina());

        for (Map.Entry<String, Object> param : parametros.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }

        for (ScIndicadores indicador : query.getResultList()) {
            listResult.add(castEntidadNegocio(indicador));
        }

    } catch (Exception e) {
        Exception(CONSULTAR_TODOS, e);
    } finally {
        cerrarEntityManager();
    }
    return listResult;
}


    @Override
    public ScIndicador consultar(int id) throws java.lang.Exception {
        ScIndicador objResult = new ScIndicador();

        try {
            objResult = castEntidadNegocio((ScIndicadores) getEntityManager().find(ScIndicadores.class, id));
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
    public int insertar(ScIndicador obj) throws java.lang.Exception {
        int _id = 0;
        try {
            ScIndicadores per = castNegocioEntidad(obj);
            _id = (int) getEntityManager().merge(per).getId();
            per.setId(_id);
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
    public void actualizar(ScIndicador obj) throws java.lang.Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE ScIndicadores c SET ";
            strQuery += "c.codigo = :codigo ,";
            strQuery += "c.nombre = :nombre ,";
            strQuery += "c.descripcion = :descripcion ,";
            strQuery += "c.objetivo = :objetivo ,";

            strQuery += "c.maeClaseId = :maeClaseId ,";
            strQuery += "c.maeClaseCodigo = :maeClaseCodigo ,";
            strQuery += "c.maeClaseValor = :maeClaseValor ,";
            strQuery += "c.maeClaseTipo = :maeClaseTipo ,";

            strQuery += "c.maeMacroprocesoId = :maeMacroprocesoId ,";
            strQuery += "c.maeMacroprocesoCodigo = :maeMacroprocesoCodigo ,";
            strQuery += "c.maeMacroprocesoValor = :maeMacroprocesoValor ,";
            strQuery += "c.maeMacroprocesoTipo = :maeMacroprocesoTipo ,";

            strQuery += "c.maeProcesoId = :maeProcesoId ,";
            strQuery += "c.maeProcesoCodigo = :maeProcesoCodigo ,";
            strQuery += "c.maeProcesoValor = :maeProcesoValor ,";
            strQuery += "c.maeProcesoTipo = :maeProcesoTipo ,";

            strQuery += "c.maeAreaId = :maeAreaId ,";
            strQuery += "c.maeAreaCodigo = :maeAreaCodigo ,";
            strQuery += "c.maeAreaValor = :maeAreaValor ,";
            strQuery += "c.maeAreaTipo = :maeAreaTipo ,";

            strQuery += "c.tipo = :tipo ,";
            strQuery += "c.normativa = :normativa ,";
            strQuery += "c.activo = :activo ,";

            strQuery += "c.borrado = :borrado ,";
            strQuery += "c.borradoObservacion = :borradoObservacion ,";

            strQuery += "c.usuarioModifica = :usuarioModifica ,";
            strQuery += "c.terminalModifica = :terminalModifica ,";
            strQuery += "c.fechaHoraModifica = :fechaHoraModifica ,";

            strQuery += "c.usuarioBorra = :usuarioBorra ,";
            strQuery += "c.terminalBorra = :terminalBorra ,";
            strQuery += "c.fechaHoraBorra = :fechaHoraBorra ";
            strQuery += " WHERE c.id = :id ";

            Query query = session.createQuery(strQuery);
            query.setParameter("codigo", obj.getCodigo());
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("descripcion", obj.getDescripcion());
            query.setParameter("objetivo", obj.getObjetivo());

            query.setParameter("maeClaseId", obj.getMaeClaseId());
            query.setParameter("maeClaseCodigo", obj.getMaeClaseCodigo());
            query.setParameter("maeClaseValor", obj.getMaeClaseValor());
            query.setParameter("maeClaseTipo", obj.getMaeClaseTipo());

            query.setParameter("maeMacroprocesoId", obj.getMaeMacroprocesoId());
            query.setParameter("maeMacroprocesoCodigo", obj.getMaeMacroprocesoCodigo());
            query.setParameter("maeMacroprocesoValor", obj.getMaeMacroprocesoValor());
            query.setParameter("maeMacroprocesoTipo", obj.getMaeMacroprocesoTipo());

            query.setParameter("maeProcesoId", obj.getMaeProcesoId());
            query.setParameter("maeProcesoCodigo", obj.getMaeProcesoCodigo());
            query.setParameter("maeProcesoValor", obj.getMaeProcesoValor());
            query.setParameter("maeProcesoTipo", obj.getMaeProcesoTipo());

            query.setParameter("maeAreaId", obj.getMaeAreaId());
            query.setParameter("maeAreaCodigo", obj.getMaeAreaCodigo());
            query.setParameter("maeAreaValor", obj.getMaeAreaValor());
            query.setParameter("maeAreaTipo", obj.getMaeAreaTipo());

            query.setParameter("tipo", obj.getTipo());
            query.setParameter("normativa", obj.getNormativa());
            query.setParameter("activo", obj.isActivo());

            query.setParameter("borrado", obj.isBorrado());
            query.setParameter("borradoObservacion", obj.getBorradoObservacion());

            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());

            query.setParameter("usuarioBorra", obj.getUsuarioBorra());
            query.setParameter("terminalBorra", obj.getTerminalBorra());
            query.setParameter("fechaHoraBorra", obj.getFechaHoraBorra());
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
    public ScIndicador eliminar(int id) throws java.lang.Exception {
        ScIndicador obj = null;
        try {
            ScIndicadores ent = getEntityManager().find(ScIndicadores.class, id);
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

    private ScIndicador castEntidadNegocio(ScIndicadores ent) {
        ScIndicador obj = new ScIndicador();

        obj.setId(ent.getId());
        obj.setCodigo(ent.getCodigo());
        obj.setNombre(ent.getNombre());
        obj.setDescripcion(ent.getDescripcion());
        obj.setObjetivo(ent.getObjetivo());

        obj.setMaeClaseId(ent.getMaeClaseId());
        obj.setMaeClaseCodigo(ent.getMaeClaseCodigo());
        obj.setMaeClaseValor(ent.getMaeClaseValor());
        obj.setMaeClaseTipo(ent.getMaeClaseTipo());

        obj.setMaeMacroprocesoId(ent.getMaeMacroprocesoId());
        obj.setMaeMacroprocesoCodigo(ent.getMaeMacroprocesoCodigo());
        obj.setMaeMacroprocesoValor(ent.getMaeMacroprocesoValor());
        obj.setMaeMacroprocesoTipo(ent.getMaeMacroprocesoTipo());

        obj.setMaeProcesoId(ent.getMaeProcesoId());
        obj.setMaeProcesoCodigo(ent.getMaeProcesoCodigo());
        obj.setMaeProcesoValor(ent.getMaeProcesoValor());
        obj.setMaeProcesoTipo(ent.getMaeProcesoTipo());

        obj.setMaeAreaId(ent.getMaeAreaId());
        obj.setMaeAreaCodigo(ent.getMaeAreaCodigo());
        obj.setMaeAreaValor(ent.getMaeAreaValor());
        obj.setMaeAreaTipo(ent.getMaeAreaTipo());

        obj.setActivo(ent.getActivo());
        obj.setTipo(ent.getTipo());
        obj.setNormativa(ent.getNormativa());
        obj.setBorrado(ent.getBorrado());
        obj.setBorradoObservacion(ent.getBorradoObservacion());

        //auditoria
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getUsuarioModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setUsuarioBorra(ent.getUsuarioBorra());
        obj.setTerminalBorra(ent.getUsuarioBorra());
        obj.setFechaHoraBorra(ent.getFechaHoraBorra());

        return obj;

    }

    private ScIndicadores castNegocioEntidad(ScIndicador obj) {
        ScIndicadores per = new ScIndicadores();
        per.setId(obj.getId());
        per.setCodigo(obj.getCodigo());
        per.setNombre(obj.getNombre());
        per.setDescripcion(obj.getDescripcion());
        per.setObjetivo(obj.getObjetivo());

        per.setMaeClaseId(obj.getMaeClaseId());
        per.setMaeClaseCodigo(obj.getMaeClaseCodigo());
        per.setMaeClaseValor(obj.getMaeClaseValor());
        per.setMaeClaseTipo(obj.getMaeClaseTipo());

        per.setMaeMacroprocesoId(obj.getMaeMacroprocesoId());
        per.setMaeMacroprocesoCodigo(obj.getMaeMacroprocesoCodigo());
        per.setMaeMacroprocesoValor(obj.getMaeMacroprocesoValor());
        per.setMaeMacroprocesoTipo(obj.getMaeMacroprocesoTipo());

        per.setMaeProcesoId(obj.getMaeProcesoId());
        per.setMaeProcesoCodigo(obj.getMaeProcesoCodigo());
        per.setMaeProcesoValor(obj.getMaeProcesoValor());
        per.setMaeProcesoTipo(obj.getMaeProcesoTipo());

        per.setMaeAreaId(obj.getMaeAreaId());
        per.setMaeAreaCodigo(obj.getMaeAreaCodigo());
        per.setMaeAreaValor(obj.getMaeAreaValor());
        per.setMaeAreaTipo(obj.getMaeAreaTipo());

        per.setActivo(obj.isActivo());
        per.setTipo(obj.getTipo());
        per.setNormativa(obj.getNormativa());
        per.setBorrado(obj.isBorrado());
        per.setBorradoObservacion(obj.getBorradoObservacion());

        //auditoria
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getUsuarioModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setUsuarioBorra(obj.getUsuarioBorra());
        per.setTerminalBorra(obj.getUsuarioBorra());
        per.setFechaHoraBorra(obj.getFechaHoraBorra());

        return per;
    }

}
