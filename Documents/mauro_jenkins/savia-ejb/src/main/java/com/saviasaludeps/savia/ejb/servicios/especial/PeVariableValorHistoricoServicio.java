/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.saviasaludeps.savia.dominio.especial.PeVariableValorHistorico;
import com.saviasaludeps.savia.ejb.entidades.PeAfiliadosProgramas;
import com.saviasaludeps.savia.ejb.entidades.PeCargasVariables;
import com.saviasaludeps.savia.ejb.entidades.PeProgramas;
import com.saviasaludeps.savia.ejb.entidades.PeVariables;
import com.saviasaludeps.savia.ejb.entidades.PeVariablesValores;
import com.saviasaludeps.savia.ejb.entidades.PeVariablesValoresHistoricos;
import com.saviasaludeps.savia.ejb.utilidades.ConnectionManager1;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.especial.PeVariableValorHistoricoRemoto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jdlopez
 */
@Stateless
@Local(PeVariableValorHistoricoLocal.class)
@Remote(PeVariableValorHistoricoRemoto.class)
public class PeVariableValorHistoricoServicio extends GenericoServicio implements PeVariableValorHistoricoLocal, PeVariableValorHistoricoRemoto {

    private static final int BATCH_SIZE = 500; // Tamaño del lote
    private static final String INSERT_SQL = "INSERT INTO pe_variables_valores_historicos "
            + "(pe_afiliados_programas_id, pe_programas_id, pe_variables_id, pe_variables_valores_id,"
            + " pe_cargas_variables_id, valor, usuario_crea, terminal_crea, fecha_hora_crea) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public int insertar(PeVariableValorHistorico historico) throws java.lang.Exception {
        int id = 0;
        try {
            PeVariablesValoresHistoricos entidad = this.castNegocioEntidad(historico);
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
    public void insertarPorLotes(List<PeVariableValorHistorico> valoresHistoricos) throws java.lang.Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionManager1.getInstance().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT_SQL);

            int contador = 0;
            for (PeVariableValorHistorico valorHistorico : valoresHistoricos) {
                preparedStatement.setInt(1, valorHistorico.getPeAfiliadosProgramasId());
                preparedStatement.setInt(2, valorHistorico.getPeProgramasId());
                preparedStatement.setInt(3, valorHistorico.getPeVariablesId());
                preparedStatement.setInt(4, valorHistorico.getPeVariablesValoresId());
                preparedStatement.setInt(5, valorHistorico.getPeCargaVariablesId());

                // Limpieza de cadenas para evitar caracteres no soportados
                preparedStatement.setString(6, limpiarCaracteresNoASCII(valorHistorico.getValor()));
                preparedStatement.setString(7, limpiarCaracteresNoASCII(valorHistorico.getUsuarioCrea()));
                preparedStatement.setString(8, limpiarCaracteresNoASCII(valorHistorico.getTerminalCrea()));

                preparedStatement.setTimestamp(9, new java.sql.Timestamp(valorHistorico.getFechaHoraCrea().getTime()));

                preparedStatement.addBatch();
                contador++;

                if (contador % BATCH_SIZE == 0) {
                    preparedStatement.executeBatch();
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
                    Logger.getLogger(PeTelefonosServicio.class.getName()).log(Level.SEVERE, "Error al revertir la transacción insertar valores historicos por lotes ", e);
                }
            }
            Logger.getLogger(PeTelefonosServicio.class.getName()).log(Level.SEVERE, "Error al insertar valores historicos por lotes", ex);
        } finally {
            this.cerrarRecursos(preparedStatement, connection);
        }
    }

    /**
     * Limpia caracteres no ASCII (como emojis o símbolos no soportados por
     * utf8)
     */
    private String limpiarCaracteresNoASCII(String texto) {
        if (texto == null) {
            return null;
        }
        return texto.replaceAll("[^\\x20-\\x7E]", ""); // Elimina caracteres no imprimibles ASCII
    }

    private void cerrarRecursos(PreparedStatement preparedStatement, Connection connection) {
        try {

            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeTelefonosServicio.class.getName()).log(Level.SEVERE, "Error al cerrar recursos", ex);
        }
    }

    @Override
    public List<PeVariableValorHistorico> consultarListadoIdAfiliadoIdProgramaIdValor(int idPrograma, int idAfiliado, int idVariable, int idValor) throws java.lang.Exception {
        List<PeVariableValorHistorico> listResult = new ArrayList<>();
        try {
            StringBuilder strTitulo = new StringBuilder();
            strTitulo.append("SELECT h FROM PeVariablesValoresHistoricos h WHERE h.peProgramasId.id = :idPrograma AND h.peAfiliadosProgramasId.id = :idAfiliado AND "
                    + "h.peVariablesId.id = :idVariable AND h.peVariablesValoresId.id = :idValor ORDER BY h.id DESC");

            Query query = getEntityManager().createQuery(strTitulo.toString());
            query.setParameter("idPrograma", idPrograma);
            query.setParameter("idAfiliado", idAfiliado);
            query.setParameter("idVariable", idVariable);
            query.setParameter("idValor", idValor);
            List<PeVariablesValoresHistoricos> list = query.getResultList();

            for (PeVariablesValoresHistoricos entidad : list) {
                listResult.add(castEntidadNegocio(entidad));
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

    private PeVariablesValoresHistoricos castNegocioEntidad(PeVariableValorHistorico obj) {
        PeVariablesValoresHistoricos entidad = new PeVariablesValoresHistoricos();
        entidad.setId(obj.getId());
        entidad.setPeAfiliadosProgramasId(new PeAfiliadosProgramas(obj.getPeAfiliadosProgramasId()));
        entidad.setPeProgramasId(new PeProgramas(obj.getPeProgramasId()));
        entidad.setPeVariablesId(new PeVariables(obj.getPeVariablesId()));
        entidad.setPeVariablesValoresId(new PeVariablesValores(obj.getPeVariablesValoresId()));
        entidad.setPeCargasVariablesId(Objects.isNull(obj.getPeCargaVariablesId()) ? null : new PeCargasVariables(obj.getPeCargaVariablesId()));
        entidad.setUsuarioCrea(obj.getUsuarioCrea());
        entidad.setTerminalCrea(obj.getTerminalCrea());
        entidad.setFechaHoraCrea(obj.getFechaHoraCrea());
        entidad.setValor(obj.getValor());
        return entidad;
    }

    private PeVariableValorHistorico castEntidadNegocio(PeVariablesValoresHistoricos entidad) {
        PeVariableValorHistorico obj = new PeVariableValorHistorico();
        obj.setId(entidad.getId());
        obj.setPeAfiliadosProgramasId(entidad.getPeAfiliadosProgramasId().getId());
        obj.setPeProgramasId(entidad.getPeProgramasId().getId());
        obj.setPeVariablesId(entidad.getPeVariablesId().getId());
        obj.setPeVariablesValoresId(entidad.getPeVariablesValoresId().getId());
        obj.setPeCargaVariablesId(Objects.isNull(entidad.getPeCargasVariablesId()) ? null : entidad.getPeCargasVariablesId().getId());
        obj.setUsuarioCrea(entidad.getUsuarioCrea());
        obj.setTerminalCrea(entidad.getTerminalCrea());
        obj.setFechaHoraCrea(entidad.getFechaHoraCrea());
        obj.setValor(entidad.getValor());
        return obj;
    }

}
