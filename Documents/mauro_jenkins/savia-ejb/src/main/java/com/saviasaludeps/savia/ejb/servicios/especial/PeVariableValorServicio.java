/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.saviasaludeps.savia.dominio.especial.PeValidacion;
import com.saviasaludeps.savia.dominio.especial.PeVariableValor;
import com.saviasaludeps.savia.dominio.especial.PeVariableValorAlmacenado;
import com.saviasaludeps.savia.ejb.entidades.PeAfiliadosProgramas;
import com.saviasaludeps.savia.ejb.entidades.PeCargasVariables;
import com.saviasaludeps.savia.ejb.entidades.PeVariables;
import com.saviasaludeps.savia.ejb.entidades.PeVariablesValores;
import com.saviasaludeps.savia.ejb.utilidades.ConnectionManager1;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.especial.PeVariableValorRemoto;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author jdlopez
 */
@Stateless
@Local(PeVariableValorLocal.class)
@Remote(PeVariableValorRemoto.class)
public class PeVariableValorServicio extends GenericoServicio implements PeVariableValorLocal, PeVariableValorRemoto {

    private static final String INSERTAR_LOTES_SQL = "INSERT INTO pe_variables_valores (pe_variables_id, pe_afiliados_programas_id, pe_cargas_variables_id, nombre, "
            + "descripcion, tipo, recurrente, recurrencia, valor_fecha, valor_entero, valor_decimal, valor_texto, valor_texto_largo, usuario_crea, terminal_crea, fecha_hora_crea) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String ACTUALIZAR_LOTES_SQL = "UPDATE pe_variables_valores SET "
            + "pe_cargas_variables_id = ?, valor_fecha = ?, valor_entero = ?, valor_decimal = ?, valor_texto = ?, valor_texto_largo = ?, "
            + "usuario_modifica = ?, terminal_modifica = ?, fecha_hora_modifica = ? "
            + "WHERE id = ?";
    private static final int BATCH_SIZE = 500; // Tamaño del lote

    @Override
    public int insertar(PeVariableValor valor) throws java.lang.Exception {
        int id = 0;
        try {
            PeVariablesValores entidad = this.castNegocioEntidad(valor);
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
    public List<PeVariableValor> insertarVariablesValoresPorLotes(List<PeVariableValor> variablesValores) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        List<PeVariableValor> variablesValoresInsertados = new ArrayList<>();
        try {
            connection = ConnectionManager1.getInstance().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERTAR_LOTES_SQL, PreparedStatement.RETURN_GENERATED_KEYS);

            int contador = 0;
            int contadorAux = contador;
            for (PeVariableValor variableValor : variablesValores) {
                preparedStatement.setInt(1, variableValor.getIdVariable());
                preparedStatement.setInt(2, variableValor.getIdAfiliado());
                preparedStatement.setInt(3, variableValor.getIdCarga());
                preparedStatement.setString(4, limpiarCaracteresPeligrosos(variableValor.getNombre()));
                preparedStatement.setString(5, limpiarCaracteresPeligrosos(variableValor.getDescripcion()));
                preparedStatement.setInt(6, variableValor.getTipo());
                preparedStatement.setBoolean(7, variableValor.getRecurrente());

                if (variableValor.getRecurrencia() != null) {
                    preparedStatement.setInt(8, (int) variableValor.getRecurrencia());
                } else {
                    preparedStatement.setNull(8, Types.INTEGER);
                }

                if (variableValor.getFecha() != null) {
                    preparedStatement.setTimestamp(9, new Timestamp(variableValor.getFecha().getTime()));
                } else {
                    preparedStatement.setNull(9, Types.DATE);
                }

                if (variableValor.getEntero() != null) {
                    preparedStatement.setInt(10, variableValor.getEntero());
                } else {
                    preparedStatement.setNull(10, Types.INTEGER);
                }

                if (variableValor.getDecimal() != null) {
                    preparedStatement.setBigDecimal(11, variableValor.getDecimal());
                } else {
                    preparedStatement.setNull(11, Types.DECIMAL);
                }

                // Limpieza aplicada aquí
                preparedStatement.setString(12, limpiarCaracteresPeligrosos(variableValor.getTexto()));
                preparedStatement.setString(13, limpiarCaracteresPeligrosos(variableValor.getTextoLargo()));
                preparedStatement.setString(14, limpiarCaracteresPeligrosos(variableValor.getUsuarioCrea()));
                preparedStatement.setString(15, limpiarCaracteresPeligrosos(variableValor.getTerminalCrea()));
                preparedStatement.setTimestamp(16, new Timestamp(variableValor.getFechaHoraCrea().getTime()));

                preparedStatement.addBatch();
                contador++;

                if (contador % BATCH_SIZE == 0) {
                    contadorAux += contador;
                    this.ejecutarInsercionLotes(preparedStatement, variablesValores, variablesValoresInsertados, contadorAux - BATCH_SIZE);
                    contador = 0;
                }
            }

            if (contador > 0) {
                this.ejecutarInsercionLotes(preparedStatement, variablesValores, variablesValoresInsertados, variablesValores.size() - contador);
            }

            connection.commit();
            connection.setAutoCommit(true);
            return variablesValoresInsertados;

        } catch (SQLException ex) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    Logger.getLogger(PeVariableValorServicio.class.getName()).log(Level.SEVERE, "Error al revertir la transacción", e);
                }
            }
            Logger.getLogger(PeVariableValorServicio.class.getName()).log(Level.SEVERE, "Error al insertar variables valor por lotes", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PeVariableValorServicio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.cerrarRecursos(generatedKeys, preparedStatement, connection);
        }
        return null;
    }

    /**
     * Elimina caracteres fuera del plano multilingüe básico (como emojis o
     * símbolos de 4 bytes)
     */
    private String limpiarCaracteresPeligrosos(String texto) {
        if (texto == null) {
            return null;
        }
        return texto.codePoints()
                .filter(c
                        -> c == 10 || c == 13
                || // saltos de línea
                (c >= 32 && c <= 126)
                || // caracteres ASCII imprimibles
                (c >= 160 && c <= 255)) // letras latinas extendidas (á, é, ñ, etc.)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Override
    public List<PeVariableValor> insertarListado(List<PeVariableValor> valores, int idAfiliado) throws java.lang.Exception {
        try {
            for (PeVariableValor v : valores) {
                v.setIdAfiliado(idAfiliado);
                v.setId(this.insertar(v));
            }
            return valores;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        }
        return null;
    }

    @Override
    public PeVariableValor consultar(int idVariableValor) throws java.lang.Exception {
        PeVariableValor objResult = new PeVariableValor();

        try {
            objResult = castEntidadNegocio((PeVariablesValores) getEntityManager().find(PeVariablesValores.class,
                    idVariableValor));
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
    public PeVariableValor consultarIdVariableIdAfiliado(int idvariable, int idAfiliado) throws java.lang.Exception {
        try {
            String query = "SELECT * FROM pe_variables_valores pvv "
                    + "WHERE pvv.pe_variables_id = :idvariable "
                    + "AND pvv.pe_afiliados_programas_id = :idAfiliado";

            PeVariablesValores result = (PeVariablesValores) getEntityManager()
                    .createNativeQuery(query, PeVariablesValores.class)
                    .setParameter("idvariable", idvariable)
                    .setParameter("idAfiliado", idAfiliado)
                    .getSingleResult();

            return this.castEntidadNegocio(result);

        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return null;
    }

    @Override
    public PeVariableValorAlmacenado consultarValorPorIdVariableIdAfiliado(int tipo, int idVariable, int idAfiliado) throws java.lang.Exception {
        PeVariableValorAlmacenado peVariableValorAlmacenado = null;
        try {
            String columna;
            switch (tipo) {
                case 0:
                    columna = "valor_fecha";
                    break;
                case 1:
                    columna = "valor_entero";
                    break;
                case 2:
                    columna = "valor_decimal";
                    break;
                case 3:
                    columna = "valor_texto";
                    break;
                case 4:
                    columna = "valor_texto_largo";
                    break;
                default:
                    throw new IllegalArgumentException("Tipo no válido. Debe ser un valor entre 0 y 4.");
            }

            String query = "SELECT pvv.id, pvv." + columna + " FROM pe_variables_valores pvv "
                    + "WHERE pvv.pe_variables_id = :idVariable "
                    + "AND pvv.pe_afiliados_programas_id = :idAfiliado";

            Object[] result = (Object[]) getEntityManager()
                    .createNativeQuery(query)
                    .setParameter("idVariable", idVariable)
                    .setParameter("idAfiliado", idAfiliado)
                    .getSingleResult();

            peVariableValorAlmacenado = new PeVariableValorAlmacenado((Integer) result[0], result[1]);
            return peVariableValorAlmacenado;

        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new Exception("Error al consultar el valor: " + e.getMessage(), e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public List<PeVariableValor> consultarListadoIdAfiliado(int idAfiliado) throws java.lang.Exception {
        try {
            TypedQuery<PeVariablesValores> query = getEntityManager().createQuery(
                    "SELECT pvv FROM PeVariablesValores pvv WHERE pvv.peAfiliadosProgramasId.id = :programId",
                    PeVariablesValores.class
            );

            query.setParameter("programId", idAfiliado);

            List<PeVariablesValores> results = query.getResultList();
            return results.stream()
                    .map(e -> this.castEntidadNegocio(e))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return null;
    }

    @Override
    public void actualizar(PeVariableValor valor) throws java.lang.Exception {
        try {

            PeVariablesValores entidad = this.castNegocioEntidad(valor);

            String sql = "UPDATE PeVariablesValores SET nombre = :nombre, "
                    + "descripcion = :descripcion, "
                    + "tipo = :tipo, "
                    + "valorFecha = :valorFecha, "
                    + "valorEntero = :valorEntero, "
                    + "valorDecimal = :valorDecimal, "
                    + "valorTexto = :valorTexto, "
                    + "valorTextoLargo = :valorTextoLargo, "
                    + "recurrente = :recurrente, "
                    + "recurrencia = :recurrencia, "
                    + "peAfiliadosProgramasId.id = :peAfiliadosProgramasId, "
                    + "peVariablesId.id = :peVariablesId, "
                    + "peCargasVariablesId.id = :peCargasVariablesId, "
                    + "usuarioModifica = :usuarioModifica,"
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id";

            Query query = getEntityManager().createQuery(sql);
            query.setParameter("nombre", entidad.getNombre());
            query.setParameter("descripcion", entidad.getDescripcion());
            query.setParameter("tipo", entidad.getTipo());
            query.setParameter("recurrente", entidad.getRecurrente());
            query.setParameter("recurrencia", entidad.getRecurrencia());
            query.setParameter("valorFecha", entidad.getValorFecha());
            query.setParameter("valorEntero", entidad.getValorEntero());
            query.setParameter("valorDecimal", entidad.getValorDecimal());
            query.setParameter("valorTexto", entidad.getValorTexto());
            query.setParameter("valorTextoLargo", entidad.getValorTextoLargo());
            query.setParameter("peAfiliadosProgramasId", entidad.getPeAfiliadosProgramasId().getId());
            query.setParameter("peCargasVariablesId", Objects.nonNull(entidad.getPeCargasVariablesId()) ? entidad.getPeCargasVariablesId().getId() : null);
            query.setParameter("peVariablesId", entidad.getPeVariablesId().getId());
            query.setParameter("usuarioModifica", entidad.getUsuarioModifica());
            query.setParameter("terminalModifica", entidad.getTerminalModifica());
            query.setParameter("fechaHoraModifica", entidad.getFechaHoraModifica());
            query.setParameter("id", entidad.getId());
            query.executeUpdate();

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarListado(List<PeVariableValor> valores) throws java.lang.Exception {
        try {
            for (PeVariableValor v : valores) {
                this.actualizar(v);
            }
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        }
    }

    @Override
    public void actualizarPorLotes(List<PeVariableValor> variablesValores) throws java.lang.Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager1.getInstance().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(ACTUALIZAR_LOTES_SQL);

            int contador = 0;
            for (PeVariableValor variableValor : variablesValores) {
                if (variableValor.getIdCarga() != null) {
                    preparedStatement.setInt(1, variableValor.getIdCarga());
                } else {
                    preparedStatement.setNull(1, Types.INTEGER);
                }

                if (variableValor.getFecha() != null) {
                    preparedStatement.setTimestamp(2, new Timestamp(variableValor.getFecha().getTime()));
                } else {
                    preparedStatement.setNull(2, Types.DATE);
                }

                if (variableValor.getEntero() != null) {
                    preparedStatement.setInt(3, variableValor.getEntero());
                } else {
                    preparedStatement.setNull(3, Types.INTEGER);
                }

                if (variableValor.getDecimal() != null) {
                    preparedStatement.setBigDecimal(4, variableValor.getDecimal());
                } else {
                    preparedStatement.setNull(4, Types.DECIMAL);
                }

                // Limpieza de caracteres no ASCII antes de insertar
                preparedStatement.setString(5, limpiarCaracteresNoASCII(variableValor.getTexto()));
                preparedStatement.setString(6, limpiarCaracteresNoASCII(variableValor.getTextoLargo()));
                preparedStatement.setString(7, limpiarCaracteresNoASCII(variableValor.getUsuarioModifica()));
                preparedStatement.setString(8, limpiarCaracteresNoASCII(variableValor.getTerminalModifica()));
                preparedStatement.setTimestamp(9, new Timestamp(variableValor.getFechaHoraModifica().getTime()));
                preparedStatement.setInt(10, variableValor.getId());

                preparedStatement.addBatch();
                contador++;

                if (contador % BATCH_SIZE == 0) {
                    preparedStatement.executeBatch(); // línea 401
                    preparedStatement.clearBatch();
                    contador = 0;
                }
            }

            if (contador > 0) {
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    Logger.getLogger(PeVariableValorServicio.class.getName()).log(Level.SEVERE, "Error al revertir la transacción", e);
                }
            }
            Logger.getLogger(PeVariableValorServicio.class.getName()).log(Level.SEVERE, "Error al actualizar variables valor por lotes", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PeVariableValorServicio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.cerrarRecursos(null, preparedStatement, connection);
        }
    }

    /**
     * Elimina caracteres no ASCII (como emojis o símbolos no soportados)
     */
    private String limpiarCaracteresNoASCII(String texto) {
        if (texto == null) {
            return null;
        }
        return texto.replaceAll("[^\\x20-\\x7E]", ""); // elimina todo excepto los caracteres imprimibles ASCII
    }

    public PeVariablesValores castNegocioEntidad(PeVariableValor obj) {
        PeVariablesValores entidad = new PeVariablesValores();
        entidad.setId((Objects.nonNull(obj.getId())) ? obj.getId() : null);
        entidad.setPeVariablesId(new PeVariables(obj.getIdVariable()));
        entidad.setPeAfiliadosProgramasId(new PeAfiliadosProgramas(obj.getIdAfiliado()));
        entidad.setPeCargasVariablesId((Objects.nonNull(obj.getIdCarga())) ? new PeCargasVariables(obj.getIdCarga()) : null);
        entidad.setNombre(obj.getNombre());
        entidad.setDescripcion(obj.getDescripcion());
        entidad.setTipo(obj.getTipo().shortValue());
        entidad.setRecurrente(obj.getRecurrente());
        entidad.setRecurrencia(obj.getRecurrencia());
        //valores
        entidad.setValorFecha((Objects.nonNull(obj.getFecha())) ? obj.getFecha() : null);
        entidad.setValorEntero((Objects.nonNull(obj.getEntero())) ? obj.getEntero() : null);
        entidad.setValorDecimal((Objects.nonNull(obj.getDecimal())) ? obj.getDecimal() : null);
        entidad.setValorTexto((Objects.nonNull(obj.getTexto())) ? obj.getTexto() : null);
        entidad.setValorTextoLargo((Objects.nonNull(obj.getTextoLargo())) ? obj.getTextoLargo() : null);
        //auditoria
        entidad.setUsuarioCrea(obj.getUsuarioCrea());
        entidad.setTerminalCrea(obj.getTerminalCrea());
        entidad.setFechaHoraCrea(obj.getFechaHoraCrea());
        entidad.setFechaHoraModifica((Objects.nonNull(obj.getFechaHoraModifica())) ? obj.getFechaHoraModifica() : null);
        entidad.setUsuarioModifica((Objects.nonNull(obj.getUsuarioModifica())) ? obj.getUsuarioModifica() : null);
        entidad.setTerminalModifica((Objects.nonNull(obj.getTerminalModifica())) ? obj.getTerminalModifica() : null);
        return entidad;
    }

    public PeVariableValor castEntidadNegocio(PeVariablesValores entidad) {
        PeVariableValor obj = new PeVariableValor();
        obj.setId(entidad.getId());
        obj.setIdVariable(entidad.getPeVariablesId().getId());
        obj.setIdAfiliado(entidad.getPeAfiliadosProgramasId().getId());
        obj.setIdCarga(Objects.nonNull(entidad.getPeCargasVariablesId()) ? entidad.getPeCargasVariablesId().getId() : null);
        obj.setNombre(entidad.getNombre());
        obj.setDescripcion(entidad.getPeVariablesId().getDescripcion());//obtener la descripcion siempre actualizada por la variable
        obj.setTipo((int) entidad.getPeVariablesId().getTipo());
        obj.setRecurrente((Objects.nonNull(entidad.getRecurrente())) ? entidad.getRecurrente() : null);
        obj.setRecurrencia((Objects.nonNull(entidad.getRecurrencia())) ? entidad.getRecurrencia() : null);
        obj.setEditable(entidad.getPeVariablesId().getEditable());
        obj.setValidaciones((Objects.nonNull(entidad.getPeVariablesId().getValidacion())) ? this.castValidacionEntidadValidacionNegocio(entidad.getPeVariablesId().getValidacion()) : null);
        //valores
        obj.setFecha((Objects.nonNull(entidad.getValorFecha())) ? entidad.getValorFecha() : null);
        obj.setEntero((Objects.nonNull(entidad.getValorEntero())) ? entidad.getValorEntero() : null);
        obj.setDecimal((Objects.nonNull(entidad.getValorDecimal())) ? entidad.getValorDecimal() : null);
        obj.setTexto((Objects.nonNull(entidad.getValorTexto())) ? entidad.getValorTexto() : null);
        obj.setTextoLargo((Objects.nonNull(entidad.getValorTextoLargo())) ? entidad.getValorTextoLargo() : null);
        //auditoria
        obj.setUsuarioCrea(entidad.getUsuarioCrea());
        obj.setTerminalCrea(entidad.getTerminalCrea());
        obj.setFechaHoraCrea(entidad.getFechaHoraCrea());
        obj.setFechaHoraModifica((Objects.nonNull(entidad.getFechaHoraModifica())) ? entidad.getFechaHoraModifica() : null);
        obj.setUsuarioModifica((Objects.nonNull(entidad.getUsuarioModifica())) ? entidad.getUsuarioModifica() : null);
        obj.setTerminalModifica((Objects.nonNull(entidad.getTerminalModifica())) ? entidad.getTerminalModifica() : null);
        return obj;
    }

    private List<PeValidacion> castValidacionEntidadValidacionNegocio(String validacion) {
        Type tipo = new TypeToken<ArrayList<PeValidacion>>() {
        }.getType();
        return this.instanciaGson().fromJson(validacion, tipo);
    }

    private Gson instanciaGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        builder.serializeNulls();
        return builder.create();
    }

    private void ejecutarInsercionLotes(PreparedStatement preparedStatement, List<PeVariableValor> variablesValores, List<PeVariableValor> variablesValoresAux, int indexInicial) throws SQLException {
        preparedStatement.executeBatch(); // Ejecuta el lote
        preparedStatement.clearBatch(); //libera memoria
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            int index = indexInicial;
            while (generatedKeys.next() && index < variablesValores.size()) {
                PeVariableValor vv = variablesValores.get(index);
                vv.setId(generatedKeys.getInt(1));
                variablesValoresAux.add(vv);
                index++;

            }
        } catch (SQLException ex) {
            Logger.getLogger(PeVariableValorServicio.class
                    .getName()).log(Level.SEVERE, "Error al obtener llaves generadas ", ex);
        }
    }

    private void cerrarRecursos(ResultSet generatedKeys, PreparedStatement preparedStatement, Connection connection) {
        try {
            if (generatedKeys != null) {
                generatedKeys.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();

            }
        } catch (SQLException ex) {
            Logger.getLogger(PeVariableValorServicio.class
                    .getName()).log(Level.SEVERE, "Error al cerrar recursos", ex);
        }
    }

}
