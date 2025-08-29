/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.saviasaludeps.savia.dominio.especial.PeCorrelacion;
import com.saviasaludeps.savia.dominio.especial.PeInsumoCalculo;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.especial.PeTipoValidacion;
import com.saviasaludeps.savia.dominio.especial.PeValidacion;
import com.saviasaludeps.savia.dominio.especial.PeVariable;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.PeProgramas;
import com.saviasaludeps.savia.ejb.entidades.PeVariables;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.especial.PeVariableRemoto;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jdlopez
 *
 */
@Stateless
@Local(PeVariableLocal.class)
@Remote(PeVariableRemoto.class)
public class PeVariableServicio extends GenericoServicio implements PeVariableLocal, PeVariableRemoto {

    private static final Gson INSTANCIA_GSON;

    static {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        builder.serializeNulls();
        INSTANCIA_GSON = builder.create();
    }

    @Override
    public int insertar(PeVariable variable) throws java.lang.Exception {
        int id = 0;
        try {
            PeVariables entidad = this.castNegocioEntidad(variable);
            id = (int) getEntityManager().merge(entidad).getId();
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(v) FROM PeVariables v ";
            StringBuilder strWhere = new StringBuilder(" WHERE v.id > 0 ");
            StringBuilder sql = new StringBuilder();
            if (paramConsulta.getFiltros() != null) {
                this.modificarConsulta(paramConsulta, strQuery, strWhere);
            }
            sql.append(strQuery).append(strWhere);
            cantidad = (int) (long) getEntityManager().createQuery(sql.toString())
                    .getSingleResult();
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
    public List<PeVariable> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<PeVariable> listResult = new ArrayList();
        try {
            String strQuery = "SELECT v FROM PeVariables v ";
            StringBuilder strWhere = new StringBuilder(" WHERE v.id > 0 ");
            StringBuilder sql = new StringBuilder();

            if (paramConsulta.getFiltros() != null) {
                this.modificarConsulta(paramConsulta, strQuery, strWhere);
            }
            sql.append(strQuery).append(strWhere);

            //ORDER BY
            sql.append(" ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                sql.append(" v.").append(paramConsulta.getOrden()).append(" ").append((paramConsulta.isAscendente() ? "ASC" : "DESC"));
            } else {
                sql.append(" v.id ");
            }

            List<PeVariables> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();

            for (PeVariables variable : list) {
                listResult.add(castEntidadNegocio(variable));
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
    public PeVariable consultarPorId(int id) throws java.lang.Exception {
        PeVariable objResult = new PeVariable();

        try {
            objResult = this.castEntidadNegocio((PeVariables) getEntityManager().find(PeVariables.class, id));
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
    public List<PeVariable> consultarPorIdPrograma(String idPrograma) throws java.lang.Exception {
        List<PeVariable> listResult = new ArrayList();
        try {
            String strQuery = "SELECT v FROM PeVariables v ";
            StringBuilder strWhere = new StringBuilder(" WHERE v.id > 0 ");
            StringBuilder sql = new StringBuilder();

            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setFiltros(Map.of("pePrograma.id", idPrograma
            ));

            if (paramConsulta.getFiltros() != null) {
                this.modificarConsulta(paramConsulta, strQuery, strWhere);
            }
            sql.append(strQuery).append(strWhere);

            List<PeVariables> list = getEntityManager().createQuery(sql.toString())
                    .getResultList();

            for (PeVariables variable : list) {
                listResult.add(castEntidadNegocio(variable));
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
    public Integer consultarIdPorIdProgramaNombre(Integer idPrograma, String nombre) throws java.lang.Exception {
        Integer resultado = null;
        try {
            String sql = "SELECT id FROM pe_variables v WHERE v.pe_programas_id = :idPrograma AND v.nombre = :nombre";

            resultado = (Integer) getEntityManager()
                    .createNativeQuery(sql)
                    .setParameter("idPrograma", idPrograma)
                    .setParameter("nombre", nombre)
                    .getSingleResult();

        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            throw new Exception("Error al consultar el ID por programa y nombre", e);
        } finally {
            cerrarEntityManager();
        }
        return resultado;
    }

    @Override
    public PeVariable consultarPorIdProgramaNombre(Integer idPrograma, String nombre) throws java.lang.Exception {
        PeVariable consulta = null;
        try {
            String sql = "SELECT v FROM PeVariables v WHERE v.peProgramasId.id = :idPrograma AND v.nombre = :nombre";

            PeVariables resultado = (PeVariables) getEntityManager().createQuery(sql)
                    .setParameter("idPrograma", idPrograma)
                    .setParameter("nombre", nombre)
                    .getSingleResult();

            if (resultado == null) {
                return consulta;
            }
            consulta = this.castEntidadNegocio(resultado);

        } catch (NoResultException e) {
            consulta = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return consulta;
    }

    @Override
    public List<PeVariable> consultarPorIdProgramaYNombres(Integer idPrograma, List<String> nombres) throws java.lang.Exception {
        List<PeVariable> resultadoConsulta = new ArrayList<>();
        try {
            String sql = "SELECT v.* FROM pe_variables v "
                    + "WHERE v.pe_programas_id = :idPrograma AND v.nombre IN (:nombres) "
                    + "ORDER BY FIELD(v.nombre, :nombres)";

            List<PeVariables> resultados = getEntityManager()
                    .createNativeQuery(sql, PeVariables.class)
                    .setParameter("idPrograma", idPrograma)
                    .setParameter("nombres", nombres)
                    .getResultList();

            if (resultados == null) {
                return resultadoConsulta;
            }
            for (PeVariables entidad : resultados) {
                PeVariable variable = this.castEntidadNegocio(entidad);
                this.castCorrelacionEntidadCorrelacionNegocio(variable);
                resultadoConsulta.add(variable);
            }
        } catch (NoResultException e) {
            resultadoConsulta = null;
        } catch (Exception e) {
            throw new Exception("Error al consultar listado de variables el ID por programa y nombre", e);
        } finally {
            cerrarEntityManager();
        }
        return resultadoConsulta;
    }

    @Override
    public List<PeVariable> consultarPorIdProgramaTipo(int idPrograma, short tipo) throws java.lang.Exception {
        List<PeVariable> resultado = new ArrayList();
        try {
            String strQuery = "SELECT v FROM PeVariables v "
                    + "WHERE v.peProgramasId.id = :idPrograma "
                    + "AND v.tipo = :tipo "
                    + "AND v.activa = 1 ";

            List<PeVariables> lista = getEntityManager().createQuery(strQuery)
                    .setParameter("idPrograma", idPrograma)
                    .setParameter("tipo", tipo)
                    .getResultList();

            for (PeVariables variable : lista) {
                resultado.add(castEntidadNegocio(variable));
            }
        } catch (NoResultException e) {
            resultado = new ArrayList();
        } catch (Exception e) {
            System.out.println("e -> " + e.getMessage());
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return resultado;
    }

    @Override
    public List<String> consultarListadoVariablesObligatoriasPorIdPrograma(int idPrograma) throws java.lang.Exception {
        List<String> resultados = null;
        try {
            String sql = "SELECT v.nombre FROM pe_variables v "
                    + "WHERE v.tipo <> 5 "
                    + "AND v.pe_programas_id = :idPrograma "
                    + "AND v.obligatoria = 1 ";

            resultados = getEntityManager()
                    .createNativeQuery(sql)
                    .setParameter("idPrograma", idPrograma)
                    .getResultList();

            return resultados;
        } catch (Exception e) {
            throw new Exception("Error al consultar listado de nombres de variables obligatorias po ID por programa", e);
        } finally {
            cerrarEntityManager();
        }
    }

    private void modificarConsulta(ParamConsulta paramConsulta, String strQuery,
            StringBuilder strWhere) {
        for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
            switch ((String) e.getKey()) {
                case "id":
                    strWhere.append(" AND v.id LIKE '%").append((String) e.getValue()).append("%' ");
                    break;
                case "pePrograma.id":
                    strWhere.append(" AND v.peProgramasId.id = '").append((String) e.getValue()).append("' ");
                    break;
                case "nombre":
                    strWhere.append(" AND v.nombre LIKE '%").append((String) e.getValue()).append("%' ");
                    break;
                case "activa":
                    strWhere.append(" AND v.activa = ").append(e.getValue());
                    break;
                case "tipo":
                    strWhere.append(" AND v.tipo = ").append(e.getValue());
                    break;
                case "recurrente":
                    strWhere.append(" AND v.recurrente = ").append(e.getValue());
                    break;
                case "obligatoria":
                    strWhere.append(" AND v.obligatoria = ").append(e.getValue());
                    break;
                case "editable":
                    strWhere.append(" AND v.editable = ").append(e.getValue());
                    break;

            }
        }
        if (paramConsulta.getParametroConsulta1() != null) {//parametro de consulta 1 selector de programa
            strWhere.append(" AND v.peProgramasId.id = '").append(String.valueOf(paramConsulta.getParametroConsulta1())).append("' ");
        }
    }

    @Override
    public void actualizar(PeVariable variable) throws java.lang.Exception {
        try {
            PeVariables entidad = this.castNegocioEntidad(variable);

            String sql = "UPDATE PeVariables SET nombre = :nombre, "
                    + "descripcion = :descripcion, "
                    + "activa = :activa, "
                    + "tipo = :tipo, "
                    + "recurrente = :recurrente, "
                    + "obligatoria = :obligatoria, "
                    + "editable = :editable, "
                    + "usuarioModifica = :usuarioModifica,"
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica, "
                    + "peProgramasId.id = :peProgramasId, "
                    + "validacion = :validacion, "
                    + "insumo_calculo = :insumoCalculo "
                    + "WHERE id = :id";

            Query query = getEntityManager().createQuery(sql);
            query.setParameter("nombre", entidad.getNombre());
            query.setParameter("descripcion", entidad.getDescripcion());
            query.setParameter("activa", entidad.getActiva());
            query.setParameter("tipo", entidad.getTipo());
            query.setParameter("recurrente", entidad.getRecurrente());
            query.setParameter("obligatoria", entidad.getObligatoria());
            query.setParameter("editable", entidad.getEditable());
            query.setParameter("usuarioModifica", entidad.getUsuarioModifica());
            query.setParameter("terminalModifica", entidad.getTerminalModifica());
            query.setParameter("fechaHoraModifica", entidad.getFechaHoraModifica());
            query.setParameter("peProgramasId", entidad.getPeProgramasId().getId());
            query.setParameter("validacion", entidad.getValidacion());
            query.setParameter("insumoCalculo", entidad.getInsumoCalculo());
            query.setParameter("id", entidad.getId());
            query.executeUpdate();

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void eliminar(int id) throws java.lang.Exception {
        try {
            PeVariables entidad = getEntityManager().find(PeVariables.class, id);
            if (entidad == null) {
                throw new NoSuchElementException("No se encontró el registro para eliminar");
            }

            getEntityManager().remove(entidad);
        } catch (NoSuchElementException e) {
            Exception(ELIMINAR, e);
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }

    }

    /*
    * Metodos de casteo
     */
    public PeVariables castNegocioEntidad(PeVariable obj) {
        PeVariables entidad = new PeVariables();
        entidad.setId((Objects.nonNull(obj.getId())) ? obj.getId() : null);
        entidad.setPeProgramasId(new PeProgramas(obj.getPePrograma().getId()));
        entidad.setNombre(obj.getNombre());
        entidad.setDescripcion(obj.getDescripcion());
        entidad.setActiva(obj.isActiva());
        entidad.setTipo(obj.getTipo().shortValue());
        entidad.setRecurrente(obj.isRecurrente());
        entidad.setObligatoria(obj.isObligatoria());
        entidad.setEditable(obj.isEditable());
        entidad.setValidacion(Objects.nonNull(obj.getValidaciones()) ? INSTANCIA_GSON.toJson(obj.getValidaciones()) : null);
        entidad.setInsumoCalculo(Objects.nonNull(obj.getInsumoCalculo()) ? INSTANCIA_GSON.toJson(obj.getInsumoCalculo()) : null);
        entidad.setUsuarioCrea(obj.getUsuarioCrea());
        entidad.setTerminalCrea(obj.getTerminalCrea());
        entidad.setFechaHoraCrea((Objects.nonNull(obj.getFechaHoraCrea())) ? obj.getFechaHoraCrea() : null);
        entidad.setFechaHoraModifica((Objects.nonNull(obj.getFechaHoraModifica())) ? obj.getFechaHoraModifica() : null);
        entidad.setUsuarioModifica((Objects.nonNull(obj.getUsuarioModifica())) ? obj.getUsuarioModifica() : null);
        entidad.setTerminalModifica((Objects.nonNull(obj.getTerminalModifica())) ? obj.getTerminalModifica() : null);
        return entidad;
    }

    public PeVariable castEntidadNegocio(PeVariables entidad) {
        PeVariable variable = new PeVariable();
        variable.setPePrograma(this.castPrograma(entidad.getPeProgramasId()));
        variable.setId(entidad.getId());
        variable.setNombre(entidad.getNombre());
        variable.setDescripcion(entidad.getDescripcion());
        variable.setActiva(entidad.getActiva());
        variable.setTipo((int) entidad.getTipo());
        variable.setRecurrente(entidad.getRecurrente());
        variable.setObligatoria(entidad.getObligatoria());
        variable.setEditable(entidad.getEditable());
        variable.setValidaciones((Objects.nonNull(entidad.getValidacion())) ? this.deserializarValidaciones(entidad.getValidacion()) : null);
        variable.setInsumoCalculo((Objects.nonNull(entidad.getInsumoCalculo())) ? this.deserializarInsumoCalculo(entidad.getInsumoCalculo()) : null);
        variable.setUsuarioCrea(entidad.getUsuarioCrea());
        variable.setTerminalCrea(entidad.getTerminalCrea());
        variable.setFechaHoraCrea(entidad.getFechaHoraCrea());
        variable.setFechaHoraModifica(entidad.getFechaHoraModifica());
        variable.setUsuarioModifica(entidad.getUsuarioModifica());
        variable.setTerminalModifica(entidad.getTerminalModifica());
        return variable;
    }

    public PePrograma castPrograma(PeProgramas ent) {
        PePrograma obj = new PePrograma();
        obj.setActivo(ent.getActivo());
        obj.setCodigoPrograma(ent.getCodigoPrograma());
        obj.setDescripcionPrograma(ent.getDescripcionPrograma());
        obj.setId(ent.getId());
        obj.setMaeTipoProgramaId(ent.getMaeTipoProgramaId());
        obj.setMaeTipoProgramaCodigo(ent.getMaeTipoProgramaCodigo());
        obj.setMaeTipoProgramaValor(ent.getMaeTipoProgramaValor());
        obj.setMaeCategoriaValor(ent.getMaeCategoriaValor());
        obj.setExoneradoCopago(ent.getExoneradoCopago());
        return obj;
    }

    /**
     * castea PeVariable validando si tiene una validacion de correlacion para
     * asignarlo al objeto
     */
    private void castCorrelacionEntidadCorrelacionNegocio(PeVariable variable) {
        if (variable.getValidaciones() != null) {
            List<PeCorrelacion> correlaciones = variable.getValidaciones().stream()
                    .filter(v -> v.getTipo().equals(PeTipoValidacion.CORRELACION.getId()))
                    .map(v -> this.deserializarCorrelacion(v.getValor().toString()))
                    .collect(Collectors.toList());
            if (!correlaciones.isEmpty()) {
                variable.setValidacionesCorrelacion(correlaciones);
            }
        }
    }

    private PeCorrelacion deserializarCorrelacion(String correlacion) {
        Type tipo = new TypeToken<PeCorrelacion>() {
        }.getType();
        return INSTANCIA_GSON.fromJson(correlacion, tipo);
    }

    private List<PeValidacion> deserializarValidaciones(String validacion) {
        Type tipo = new TypeToken<ArrayList<PeValidacion>>() {
        }.getType();
        return INSTANCIA_GSON.fromJson(validacion, tipo);
    }

    private PeInsumoCalculo deserializarInsumoCalculo(String insumoCalculo) {
        Type tipo = new TypeToken<PeInsumoCalculo>() {
        }.getType();
        return INSTANCIA_GSON.fromJson(insumoCalculo, tipo);
    }

//    private Gson instanciaGson() {
//        GsonBuilder builder = new GsonBuilder();
//        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
//        builder.serializeNulls();
//        return builder.create();
//    }
}
