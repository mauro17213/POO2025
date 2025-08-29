/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContactoConsulta;
import com.saviasaludeps.savia.dominio.especial.PeTelefono;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.PeTelefonos;
import com.saviasaludeps.savia.ejb.utilidades.ConnectionManager1;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.especial.PeTelefonosRemoto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author Isaac Bohorquez
 */
@Stateless
@Remote(PeTelefonosRemoto.class)
@Local(PeTelefonosLocal.class)
public class PeTelefonosServicio extends GenericoServicio implements PeTelefonosLocal, PeTelefonosRemoto {

    private static final int BATCH_SIZE = 200; // Tamaño del lote
    private static final String INSERT_SQL = "INSERT INTO pe_telefonos (aseg_afiliados_id, tipo, numero, usuario_crea, terminal_crea, fecha_hora_crea) VALUES (?, ?, ?, ?, ?, ?)";

    /**
     * @author Isaac Bohorquez
     * @FechaCreacion 08-06-2022
     * @param id_afiliado
     * @param tipo_telefono
     * @param numero
     * @return PeTelefono
     * @throws java.lang.Exception
     */
    @Override
    public PeTelefono consultarTelefonoAfiliado(int id_afiliado, int tipo_telefono, String numero) throws Exception {
        PeTelefono peTelefono = new PeTelefono();
        try {
            StringBuilder strQuery = new StringBuilder("Select p FROM PeTelefonos p ");
            strQuery.append(" WHERE p.asegAfiliadosId.id = ").append(id_afiliado);
            strQuery.append(" AND p.tipo = ").append(tipo_telefono);
            strQuery.append(" AND p.numero = '").append(numero).append("' ");

            PeTelefonos programaTecnologias = (PeTelefonos) getEntityManager().createQuery(strQuery.toString()).setMaxResults(1).getSingleResult();
            peTelefono = castEntidadNegocio(programaTecnologias);
        } catch (NoResultException e) {
            peTelefono = new PeTelefono();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return peTelefono;
    }

    /**
     * @author Isaac Bohorquez
     * @FechaCreacion 08-06-2022
     * @param ent
     * @return PeTelefono
     */
    public static PeTelefono castEntidadNegocio(PeTelefonos ent) {
        PeTelefono peTelefono = new PeTelefono();
        peTelefono.setId(ent.getId());
        peTelefono.setAsegAfiliadosId(new AsegAfiliado(ent.getAsegAfiliadosId().getId()));
        peTelefono.setNumero(ent.getNumero());
        peTelefono.setObservacion(ent.getObservacion());
        peTelefono.setTipo(ent.getTipo());
        peTelefono.setMaeTipoContactoId(ent.getTipo());
        peTelefono.setMaeTipoContactoCodigo(String.valueOf(ent.getTipo()));
        peTelefono.setUsuarioCrea(ent.getUsuarioCrea());
        peTelefono.setFechaHoraCrea(ent.getFechaHoraCrea());
        peTelefono.setTerminalCrea(ent.getTerminalCrea());
        peTelefono.setUsuarioModifica(ent.getUsuarioModifica());
        peTelefono.setFechaHoraModifica(ent.getFechaHoraModifica());
        peTelefono.setTerminalModifica(ent.getTerminalModifica());
        switch (ent.getTipo()) {
            case 1:
                peTelefono.setMaeTipoContactoValor(PeTelefono.PE_TELEFONO_TIPO_FIJO);
                break;
            case 2:
                peTelefono.setMaeTipoContactoValor(PeTelefono.PE_TELEFONO_TIPO_MOVIL);
                break;
            case 3:
                peTelefono.setMaeTipoContactoValor(PeTelefono.PE_TELEFONO_TIPO_OFICINA);
                break;
            default:
                peTelefono.setMaeTipoContactoValor(PeTelefono.PE_TELEFONO_TIPO_NA);
                break;
        }
        return peTelefono;
    }

    /**
     * @author Isaac Bohorquez
     * @FechaCreacion 08-06-2022
     * @param peTelefono
     * @return {id} int
     * @throws java.lang.Exception
     */
    @Override
    public int insertarTelefonosAfiliadosProgramas(PeTelefono peTelefono) throws java.lang.Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(peTelefono)).getId();
            peTelefono.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    /**
     * @author Isaac Bohorquez
     * @FechaCreacion 08-06-2022
     * @param {objs} PeTelefono
     * @return PeTelefonos
     */
    public static PeTelefonos castNegocioEntidad(PeTelefono objs) {
        PeTelefonos ent = new PeTelefonos();
        ent.setAsegAfiliadosId(new AsegAfiliados(objs.getAsegAfiliadosId().getId()));
        ent.setNumero(objs.getNumero());
        ent.setObservacion(objs.getObservacion());
        ent.setTipo(objs.getTipo());
        ent.setUsuarioCrea(objs.getUsuarioCrea());
        ent.setFechaHoraCrea(objs.getFechaHoraCrea());
        ent.setTerminalCrea(objs.getTerminalCrea());
        return ent;
    }

    @Override
    public List<PeTelefono> getListaContatoAfiliado(int id_afiliado, String telefonos_existentes) throws java.lang.Exception {
        List<PeTelefono> contactos = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT p FROM PeTelefonos p ");
            strQuery.append(" WHERE p.asegAfiliadosId.id = ").append(id_afiliado);
            if (telefonos_existentes != null && !telefonos_existentes.isEmpty()) {
                strQuery.append(" and p.numero not in ( ").append(telefonos_existentes).append(" ) ");
            }

            List<PeTelefonos> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (PeTelefonos item : list) {
                contactos.add(castEntidadNegocio(item));
            }
        } catch (NoResultException e) {
            contactos = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return contactos;
    }

    public List<AsegAfiliadoContactoConsulta> getListaContactoAfiliado(int id_afiliado, String telefonosExistentes) throws java.lang.Exception {
        List<AsegAfiliadoContactoConsulta> contactos = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("FROM PeTelefonos p ");
            strQuery.append(" WHERE p.asegAfiliadosId.id = ").append(id_afiliado);
            if (telefonosExistentes != null && !telefonosExistentes.isEmpty()) {
                strQuery.append(" and p.numero not in ( ").append(telefonosExistentes).append(" ) ");
            }

            List<PeTelefonos> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (PeTelefonos item : list) {
                contactos.add(castEntidadNegocioAfiliado(item));
            }
        } catch (NoResultException e) {
            contactos = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return contactos;
    }

    /**
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public PeTelefono eliminar(int id) throws Exception {
        PeTelefono obj = null;
        try {
            PeTelefonos ent = getEntityManager().find(PeTelefonos.class, id);
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

    private AsegAfiliadoContactoConsulta castEntidadNegocioAfiliado(PeTelefonos ent) {
        AsegAfiliadoContactoConsulta neg = new AsegAfiliadoContactoConsulta();
        neg.setId(ent.getId());
        neg.setNumeroContacto(ent.getNumero());
        neg.setObservacion(ent.getObservacion());
        neg.setMaeTipoContactoValor(String.valueOf(ent.getTipo()));
        neg.setOrigen(AsegAfiliadoContactoConsulta.ORIGEN_GESTION_RIESGO);
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        return neg;
    }

    @Override
    public void insertarTelefonosPorLotes(List<PeTelefono> telefonos) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionManager1.getInstance().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT_SQL);
            int contador = 0;
            for (PeTelefono telefono : telefonos) {
               // aseg_afiliados_id, tipo, numero, usuario_crea, terminal_crea, fecha_hora_crea
                preparedStatement.setInt(1, telefono.getAsegAfiliadosId().getId());
                preparedStatement.setInt(2, telefono.getTipo());
                preparedStatement.setString(3, telefono.getNumero());
                preparedStatement.setString(4, telefono.getUsuarioCrea());
                preparedStatement.setString(5, telefono.getTerminalCrea());
                preparedStatement.setTimestamp(6, new java.sql.Timestamp(telefono.getFechaHoraCrea().getTime()));

                preparedStatement.addBatch();

                contador++;
                if (contador % BATCH_SIZE == 0) {
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                    contador = 0;
                }
            }

            // Ejecuta el último lote si quedan inserciones pendientes
            if (contador > 0) {
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
            }
            connection.commit(); // Confirma la transacción
            connection.setAutoCommit(true);

        } catch (SQLException ex) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    Logger.getLogger(PeTelefonosServicio.class.getName()).log(Level.SEVERE, "Error al revertir la transacción insertar telefonos por lotes ", e);
                }
            }
            Logger.getLogger(PeTelefonosServicio.class.getName()).log(Level.SEVERE, "Error al insertar telefonos por lotes", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PeTelefonosServicio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.cerrarRecursos(preparedStatement, connection);
        }
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

}
