/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.PeProgramas;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.especial.PeProgramaRemoto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.sql.DataSource;

/**
 *
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(PeProgramaRemoto.class)
@Local(PeProgramaLocal.class)
public class PeProgramaServicio extends GenericoServicio implements PeProgramaLocal, PeProgramaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM PeProgramas p ";
            StringBuilder strWhere = new StringBuilder(" WHERE p.id > 0 ");
            StringBuilder sql = new StringBuilder();
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strWhere.append(" AND p.id = '").append((String) e.getValue()).append("' ");
                            break;
                        case "codigoPrograma":
                            strWhere.append(" AND p.codigoPrograma LIKE '%").append((String) e.getValue()).append("%' ");
                            break;
                        case "descripcionPrograma":
                            strWhere.append(" AND p.descripcionPrograma LIKE '%").append((String) e.getValue()).append("%' ");
                            break;
                        case "activo":
                            strWhere.append(" AND p.activo = '").append((String) e.getValue()).append("' ");
                            break;
                        case "tipo":
                            strWhere.append(" AND p.maeTipoProgramaCodigo = '").append((String) e.getValue()).append("' ");
                            break;
                        case "usuarioCrea":
                            strWhere.append(" AND p.usuarioCrea LIKE '%").append((String) e.getValue()).append("%' ");
                            break;
                        case "usuariosId.nombre":
                            strQuery = agregarJoin(" INNER JOIN GnUsuarios gu ON p.gnUsuariosId = gu.id ", strQuery);
                            strWhere.append(" AND gu.nombre LIKE '%").append((String) e.getValue()).append("%' ");
                            break;
                        case "maeCategoriaValor":
                            strWhere.append("AND p.maeCategoriaCodigo = ").append((String) e.getValue());
                            break;
                        case "registroAfiliadoSolicitud":
                            strWhere.append(" AND p.registroAfiliadoSolicitud = ").append(e.getValue());
                            break;
                        case "exoneradoCopago":
                            strWhere.append(" AND p.exoneradoCopago = ").append(e.getValue());
                            break;
                        case "aplicaRescate":
                            strWhere.append(" AND p.aplicaRescate = ").append(e.getValue());
                            break;
                        case "aplicaRecobro":
                            strWhere.append(" AND p.aplicaRecobro = ").append(e.getValue());
                            break;
                        case "direcciona":
                            strWhere.append(" AND p.direcciona = ").append(e.getValue());
                            break;
                        case "maeAgrupadorId":
                            strWhere.append(" AND p.maeAgrupadorId = ").append(e.getValue());
                            break;
                        case "aplicaRescateAnexo3Ambulatorio":
                            strWhere.append(" AND p.aplicaRescateAnexo3Ambulatorio = ").append(e.getValue());
                            break;
                        case "aplicaRescateAnexo3Hospitalario":
                            strWhere.append(" AND p.aplicaRescateAnexo3Hospitalario = ").append(e.getValue());
                            break;
                        case "aplicaRescateAnexo2Urgencia":
                            strWhere.append(" AND p.aplicaRescateAnexo2Urgencias = ").append(e.getValue());
                            break;
                        case "sexoAplica":
                            strWhere.append(" AND p.sexoAplica = ").append(e.getValue() );
                            break;
                        case "cantidadRegistro":
                            strWhere.append(" AND p.cantidadRegistros = ").append(e.getValue());
                            break;
                    }
                }
            }
            sql.append(strQuery).append(strWhere);
            cant = (int) (long) getEntityManager().createQuery(sql.toString())
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
    public List<PePrograma> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<PePrograma> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p FROM PeProgramas p ";
            StringBuilder strWhere = new StringBuilder(" WHERE p.id > 0 ");
            StringBuilder sql = new StringBuilder();
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strWhere.append(" AND p.id = '").append((String) e.getValue()).append("' ");
                            break;
                        case "codigoPrograma":
                            strWhere.append(" AND p.codigoPrograma LIKE '%").append((String) e.getValue()).append("%' ");
                            break;
                        case "descripcionPrograma":
                            strWhere.append(" AND p.descripcionPrograma LIKE '%").append((String) e.getValue()).append("%' ");
                            break;
                        case "activo":
                            strWhere.append(" AND p.activo = '").append((String) e.getValue()).append("' ");
                            break;
                        case "tipo":
                            strWhere.append(" AND p.maeTipoProgramaCodigo = '").append((String) e.getValue()).append("' ");
                            break;
                        case "usuarioCrea":
                            strWhere.append(" AND p.usuarioCrea LIKE '%").append((String) e.getValue()).append("%' ");
                            break;
                        case "usuariosId.nombre":
                            strQuery = agregarJoin(" INNER JOIN GnUsuarios gu ON p.gnUsuariosId = gu.id ", strQuery);
                            strWhere.append(" AND gu.nombre LIKE '%").append((String) e.getValue()).append("%' ");
                            break;
                        case "maeCategoriaValor":
                            strWhere.append("AND p.maeCategoriaCodigo = ").append((String) e.getValue());
                            break;
                        case "registroAfiliadoSolicitud":
                            strWhere.append(" AND p.registroAfiliadoSolicitud = ").append(e.getValue());
                            break;
                        case "exoneradoCopago":
                            strWhere.append(" AND p.exoneradoCopago = ").append(e.getValue());
                            break;
                        case "aplicaRescate":
                            strWhere.append(" AND p.aplicaRescate = ").append(e.getValue());
                            break;
                        case "aplicaRecobro":
                            strWhere.append(" AND p.aplicaRecobro = ").append(e.getValue());
                            break;
                        case "direcciona":
                            strWhere.append(" AND p.direcciona = ").append(e.getValue());
                            break;
                        case "maeAgrupadorId":
                            strWhere.append(" AND p.maeAgrupadorId = ").append(e.getValue());
                            break;
                        case "aplicaRescateAnexo3Ambulatorio":
                            strWhere.append(" AND p.aplicaRescateAnexo3Ambulatorio = ").append(e.getValue());
                            break;
                        case "aplicaRescateAnexo3Hospitalario":
                            strWhere.append(" AND p.aplicaRescateAnexo3Hospitalario = ").append(e.getValue());
                            break;
                        case "aplicaRescateAnexo2Urgencia":
                            strWhere.append(" AND p.aplicaRescateAnexo2Urgencias = ").append(e.getValue());
                            break;
                        case "sexoAplica":
                            strWhere.append(" AND p.sexoAplica = ").append(e.getValue() );
                            break;
                        case "cantidadRegistro":
                            strWhere.append(" AND p.cantidadRegistros = ").append(e.getValue());
                            break;
                    }
                }
            }
            sql.append(strQuery).append(strWhere);
            sql.append(" ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                sql.append(" p." + paramConsulta.getOrden()).append(" ").append((paramConsulta.isAscendente() ? "ASC" : "DESC"));
            } else {
                sql.append(" p.id ");
            }
            List<PeProgramas> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (PeProgramas per : list) {
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
    public List<PePrograma> consultarTodos() throws Exception {
        List<PePrograma> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p FROM PeProgramas p ";

            List<PeProgramas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (PeProgramas per : list) {
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

    /**
     * Metodo que consulta el listado de todos los programas que esten activos
     *
     * @author Isaac Bohorquez
     * @fechaCreacion 13/07/2022
     * @return List<PePrograma>
     * @throws Exception
     */
    @Override
    public List<PePrograma> consultarTodosEstado(int estado) throws Exception {
        List<PePrograma> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p FROM PeProgramas p WHERE p.activo = " + estado;
            List<PeProgramas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (PeProgramas per : list) {
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
    public PePrograma consultar(int id) throws Exception {
        PePrograma objResult = new PePrograma();

        try {
            objResult = castEntidadNegocio((PeProgramas) getEntityManager().find(PeProgramas.class, id));
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
    public PePrograma consultarPorCodigo(String codigoPrograma) throws Exception {
        PePrograma objResult = new PePrograma();
        String strQuery = "SELECT p FROM PeProgramas p WHERE p.codigoPrograma = :codigoPrograma";

        try {
            PeProgramas resultado = (PeProgramas) getEntityManager().createQuery(strQuery)
                    .setParameter("codigoPrograma", codigoPrograma)
                    .setMaxResults(1)
                    .getSingleResult();

            objResult = castEntidadNegocio(resultado);

        } catch (NoResultException e) {
            objResult = null;
        }
        return objResult;
    }

    @Override
    public int insertar(PePrograma obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
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
    public void actualizar(PePrograma obj) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PeProgramas SET codigoPrograma = :codigoPrograma, ");
            sql.append("descripcionPrograma = :descripcionPrograma,  ");
            sql.append("maeCategoriaId = :maeCategoriaId,  ");
            sql.append("maeCategoriaCodigo = :maeCategoriaCodigo,  ");
            sql.append("maeCategoriaValor = :maeCategoriaValor,  ");
            sql.append("maeTipoProgramaId = :maeTipoProgramaId, ");
            sql.append("maeTipoProgramaCodigo = :maeTipoProgramaCodigo, ");
            sql.append("maeTipoProgramaValor = :maeTipoProgramaValor, ");            
            sql.append("activo = :activo, ");
            sql.append("maeAgrupadorId = :maeAgrupadorId, ");
            sql.append("maeAgrupadorCodigo = :maeAgrupadorCodigo, ");
            sql.append("maeAgrupadorValor = :maeAgrupadorValor, ");
            sql.append("direcciona = :direcciona, ");
            sql.append("registroAfiliadoAfiliacion = ").append((obj.getRegistroAfiliadoAfiliacion()) ? 1 : 0).append(", ");
            sql.append("registroAfiliadoSolicitud = ").append(obj.getRegistroAfiliadoSolicitud()).append(", ");
            sql.append("registroAfiliadoHospitalizacion = ").append(obj.getRegistroAfiliadoHospitalizacion()).append(", ");
            sql.append("cantidadRegistros = :cantidadRegistros, ");
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica, ");
            sql.append("gnUsuariosId.id = :gnUsuariosId, ");
            sql.append("exoneradoCopago = :exoneradoCopago, ");
            sql.append("aplicaRescate = :aplicaRescate, ");
            sql.append("aplicaRescateAnexo3Ambulatorio = :aplicaRescateAnexo3Ambulatorio, ");
            sql.append("aplicaRescateAnexo3Hospitalario = :aplicaRescateAnexo3Hospitalario, ");
            sql.append("aplicaRescateAnexo2Urgencias = :aplicaRescateAnexo2Urgencias, ");
            sql.append("sexoAplica = :sexoAplica, ");
            sql.append("aplicaRecobro = :aplicaRecobro, ");
            sql.append("exoneracionObligatoria = :exoneracionObligatoria ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("codigoPrograma", obj.getCodigoPrograma());
            query.setParameter("descripcionPrograma", obj.getDescripcionPrograma());
            query.setParameter("maeCategoriaId", obj.getMaeCategoriaId());
            query.setParameter("maeCategoriaCodigo", obj.getMaeCategoriaCodigo());
            query.setParameter("maeCategoriaValor", obj.getMaeCategoriaValor());
            query.setParameter("maeTipoProgramaId", obj.getMaeTipoProgramaId());
            query.setParameter("maeTipoProgramaCodigo", obj.getMaeTipoProgramaCodigo());
            query.setParameter("maeTipoProgramaValor", obj.getMaeTipoProgramaValor());
            query.setParameter("activo", obj.isActivo());
            query.setParameter("maeAgrupadorId", obj.getMaeAgrupadorId());
            query.setParameter("maeAgrupadorCodigo", obj.getMaeAgrupadorCodigo());
            query.setParameter("maeAgrupadorValor", obj.getMaeAgrupadorValor());
            query.setParameter("direcciona", obj.getDirecciona());
            query.setParameter("cantidadRegistros", obj.getCantidadRegistro()==0?false:true);
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("gnUsuariosId", obj.getUsuariosId().getId());
            query.setParameter("exoneradoCopago", obj.isExoneradoCopago());
            query.setParameter("aplicaRescate", obj.getAplicaRescate());
            query.setParameter("aplicaRescateAnexo3Ambulatorio", obj.getAplicaRescateAnexo3Ambulatorio());
            query.setParameter("aplicaRescateAnexo3Hospitalario", obj.getAplicaRescateAnexo3Hospitalario());
            query.setParameter("aplicaRescateAnexo2Urgencias", obj.getAplicaRescateAnexo2Urgencia());
            query.setParameter("sexoAplica", Short.valueOf(obj.getSexoAplica().toString()));
            query.setParameter("aplicaRecobro", obj.getAplicaRecobro());
            query.setParameter("exoneracionObligatoria", obj.isExoneracionObligatoria());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public PePrograma eliminar(int id) throws Exception {
        PePrograma obj = null;
        try {
            PeProgramas ent = getEntityManager().find(PeProgramas.class, id);
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

    /**
     * Funcion encargada de consultar listado de programas en los que se
     * encuentra matriculado un afiliado y programas en los que está sugerido
     *
     * @author idbohorquez
     * @fechaCreacion 18/11/2022
     * @param idAfiliado
     * @return List<PePrograma>
     * @throws Exception
     */
    @Override
    public List<PePrograma> programasMatriculadosSugeridos(Integer idAfiliado) throws Exception {
        List<PePrograma> listResult = new ArrayList();
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            dbConnection = this.getConnection();
            StringBuilder strQuery = new StringBuilder("SELECT  tbl.id, tbl.codigo_programa, tbl.descripcion_programa, tbl.tipo , tbl.tipo_programa ");
            strQuery.append("FROM ( ");
            strQuery.append(" select pap.id, pp.codigo_programa, pp.descripcion_programa , 'Matriculado' as tipo , 1 as tipo_programa from pe_programas pp  ");
            strQuery.append(" inner join  pe_afiliados_programas pap on pp.id = pap.pe_programas_id and pap.aseg_afiliados_id = ").append(idAfiliado).append(" and pap.activo = 1  ");
            strQuery.append(" UNION  ");
            strQuery.append(" select pas.id, pp.codigo_programa, pp.descripcion_programa, 'Sugerido' as tipo , 0 as tipo_programa from pe_programas pp   ");
            strQuery.append(" inner join pe_afiliados_sugeridos pas on pp.id = pas.pe_programas_id and pas.aseg_afiliados_id = ").append(idAfiliado).append(" and pas.estado = 1  ");
            strQuery.append(" ) tbl order by tbl.tipo asc, tbl.descripcion_programa asc ");

            preparedStatement = dbConnection.prepareStatement(strQuery.toString());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PePrograma programa = new PePrograma();
                programa.setId(resultSet.getInt("id"));
                programa.setCodigoPrograma(resultSet.getString("codigo_programa"));
                programa.setDescripcionPrograma(resultSet.getString("descripcion_programa").toUpperCase());
                programa.setMaeTipoProgramaValor(resultSet.getString("tipo"));
                programa.setMaeTipoProgramaId(resultSet.getInt("tipo_programa"));
                listResult.add(programa);
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return listResult;
    }

    @Override
    public List<PePrograma> programasNoMatriculadosSugeridosHospitalizacion(Integer idAfiliado) throws Exception {
        List<PePrograma> listResult = new ArrayList();
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            dbConnection = this.getConnection();
            StringBuilder strQuery = new StringBuilder("SELECT  pp.id , pp.codigo_programa , pp.descripcion_programa ");
            strQuery.append(" FROM   ");
            strQuery.append(" pe_programas pp ");
            strQuery.append(" where pp.activo = 1 ");
            strQuery.append(" AND pp.registro_afiliado_hospitalizacion IN (")
                    .append(PePrograma.REGISTRO_AFILIADO_AUTOMATICO)
                    .append(",")
                    .append(PePrograma.REGISTRO_AFILIADO_SUGERIDO);
            strQuery.append(")");
            strQuery.append(" AND pp.id not in (  ");
            strQuery.append(" select pap.pe_programas_id  from pe_afiliados_programas pap where pap.aseg_afiliados_id = ").append(idAfiliado).append(" and pap.activo = 1  ");
            strQuery.append(" UNION  ");
            strQuery.append(" select pas.pe_programas_id  from pe_afiliados_sugeridos pas where pas.aseg_afiliados_id = ").append(idAfiliado).append(" and pas.estado in (1)  ");
            strQuery.append(" UNION  ");
            strQuery.append(" select pas.pe_programas_id  from pe_afiliados_sugeridos pas ")
                    .append( " inner join pe_afiliados_programas pap2 on pas.aseg_afiliados_id = pap2.aseg_afiliados_id and pas.pe_programas_id = pap2.pe_programas_id and pap2.activo = 1 ")
                    .append( " where pas.aseg_afiliados_id = ").append(idAfiliado).append(" and pas.estado in (")
                    .append(PePrograma.ESTADO_SUGERIDO_PENDIENTE)
                    .append(",")
                    .append(PePrograma.ESTADO_SUGERIDO_MARCADO)
                    .append(") ");
            strQuery.append(" )  ");
            strQuery.append(" order BY pp.descripcion_programa asc ");

            preparedStatement = dbConnection.prepareStatement(strQuery.toString());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PePrograma programa = new PePrograma();
                programa.setId(resultSet.getInt("id"));
                programa.setCodigoPrograma(resultSet.getString("codigo_programa"));
                programa.setDescripcionPrograma(resultSet.getString("descripcion_programa").toUpperCase());
                listResult.add(programa);
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return listResult;
    }
    
    @Override
    public List<PePrograma> programasNoMatriculadosSugeridosSolicitud(Integer idAfiliado) throws Exception {
        List<PePrograma> listResult = new ArrayList();
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            dbConnection = this.getConnection();
            StringBuilder strQuery = new StringBuilder("SELECT  pp.id , pp.codigo_programa , pp.descripcion_programa ");
            strQuery.append(" FROM   ");
            strQuery.append(" pe_programas pp ");
            strQuery.append(" where pp.activo = 1 ");
            strQuery.append(" AND pp.registro_afiliado_solicitud IN (")
                    .append(PePrograma.REGISTRO_AFILIADO_AUTOMATICO)
                    .append(",")
                    .append(PePrograma.REGISTRO_AFILIADO_SUGERIDO);
            strQuery.append(")");
            strQuery.append(" AND pp.id not in (  ");
            strQuery.append(" select pap.pe_programas_id  from pe_afiliados_programas pap where pap.aseg_afiliados_id = ").append(idAfiliado).append(" and pap.activo = 1  ");
            strQuery.append(" UNION  ");
            strQuery.append(" select pas.pe_programas_id  from pe_afiliados_sugeridos pas where pas.aseg_afiliados_id = ").append(idAfiliado).append(" and pas.estado in (1)  ");
            strQuery.append(" UNION  ");
            strQuery.append(" select pas.pe_programas_id  from pe_afiliados_sugeridos pas ")
                    .append( " inner join pe_afiliados_programas pap2 on pas.aseg_afiliados_id = pap2.aseg_afiliados_id and pas.pe_programas_id = pap2.pe_programas_id and pap2.activo = 1 ")
                    .append( " where pas.aseg_afiliados_id = ").append(idAfiliado).append(" and pas.estado in (")
                    .append(PePrograma.ESTADO_SUGERIDO_PENDIENTE)
                    .append(",")
                    .append(PePrograma.ESTADO_SUGERIDO_MARCADO)
                    .append(") ");
            strQuery.append(" )  ");
            strQuery.append(" order BY pp.descripcion_programa asc ");

            preparedStatement = dbConnection.prepareStatement(strQuery.toString());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PePrograma programa = new PePrograma();
                programa.setId(resultSet.getInt("id"));
                programa.setCodigoPrograma(resultSet.getString("codigo_programa"));
                programa.setDescripcionPrograma(resultSet.getString("descripcion_programa").toUpperCase());
                listResult.add(programa);
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return listResult;
    }
    
    /**
     * Función encargada de consultar listado de afilaidos matriculados en programas
     * filtrado por el id afilaido y id tecnologia.
     * 
     * @author idbohorquez
     * @creado 24/29/2023
     * @param idAfiliado
     * @param maeTecnologiaId
     * @return List<PeAfiliadosPrograma>
     * @throws Exception 
     */
    @Override
    public List<PePrograma> programasAfiliadosTecnologia(int idAfiliado, int maeTecnologiaId, int tipoTecnologia) throws Exception {
        List<PePrograma> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT DISTINCT p.peProgramasId FROM PeAfiliadosProgramas p ");
            strQuery.append(" INNER JOIN PeProgramaTecnologias ppt ON p.peProgramasId = ppt.peProgramasId and ppt.borrado = 0 and  ppt.maTecnologiaId = :idTecnologia and ppt.tipoTecnologia = ").append(tipoTecnologia);
            strQuery.append("  WHERE p.asegAfiliadosId.id = :idAfiliado AND p.activo = 1 ");

            List<PeProgramas> list = getEntityManager().createQuery(strQuery.toString())
                    .setParameter("idTecnologia", maeTecnologiaId)
                    .setParameter("idAfiliado", idAfiliado)
                    .getResultList();
            for (PeProgramas per : list) {
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
    
    /**
     * Función encargada de consultar listado programas en los que aplica rescate
     * para hospitalización segun filtro de parametros
     * 
     * @author idbohorquez
     * @creado 17/04/2023
     * @param idAfilaido
     * @param idSede
     * @return List<PePrograma>
     * @throws Exception 
     */
    @Override
    public List<PePrograma> programasAplicaRescateHospitalizacion(int idAfilaido, int idSede) throws java.lang.Exception {
        List<PePrograma> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT DISTINCT p.peProgramasId FROM PeAfiliadosProgramas p ");
            strQuery.append(" WHERE p.asegAfiliadosId.id = :idAfiliado ");
            strQuery.append(" AND p.cntPrestadorSedesId.id NOT IN (1,").append(idSede).append(")");
            strQuery.append(" AND p.peProgramasId.aplicaRescate = 1 ");
            strQuery.append(" AND p.peProgramasId.activo = 1 ");            
            strQuery.append(" AND p.activo = 1 ");
            
            List<PeProgramas> list = getEntityManager().createQuery(strQuery.toString())
                    .setParameter("idAfiliado", idAfilaido)
                    .getResultList();
            for (PeProgramas per : list) {
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

    public Integer cantidadAfiliadoPrograma(int idPrograma) throws Exception {
        Integer cantidad = 0;
        String strQuery = "SELECT count(p) FROM PeAfiliadosProgramas p WHERE p.peProgramasId.id = :idPrograma and p.activo = 1 ";

        try {
            cantidad = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("idPrograma", idPrograma)
                    .setMaxResults(1)
                    .getSingleResult();

        } catch (NoResultException e) {
            cantidad = 0;
        }
        return cantidad;
    }
    
    public Integer cantidadAfiliadoProgramaAgrupador(Integer idAgrupador) throws Exception {
        Integer cantidad = 0;
        StringBuilder strQuery = new StringBuilder("SELECT count(p) FROM PeAfiliadosProgramas p WHERE   ");
        strQuery.append(" p.peProgramasId.maeAgrupadorId = :maeAgrupadorId ");
        strQuery.append(" and p.activo = 1");
        try {
            cantidad = (int) (long) getEntityManager().createQuery(strQuery.toString())
                    .setParameter("maeAgrupadorId", idAgrupador)
                    .setMaxResults(1)
                    .getSingleResult();

        } catch (NoResultException e) {
            cantidad = 0;
        }
        return cantidad;
    }

    public static PePrograma castEntidadNegocio(PeProgramas ent) {
        PePrograma obj = new PePrograma();

        obj.setActivo(ent.getActivo());
        obj.setCodigoPrograma(ent.getCodigoPrograma());
        obj.setDescripcionPrograma(ent.getDescripcionPrograma());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setId(ent.getId());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setMaeTipoProgramaId(ent.getMaeTipoProgramaId());
        obj.setMaeTipoProgramaCodigo(ent.getMaeTipoProgramaCodigo());
        obj.setMaeTipoProgramaValor(ent.getMaeTipoProgramaValor());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setExoneradoCopago(ent.getExoneradoCopago());
        if (ent.getGnUsuariosId() != null) {
            Usuario user = new Usuario(ent.getGnUsuariosId().getId());
            user.setNombre(ent.getGnUsuariosId().getNombre());
            obj.setUsuariosId(user);

        }
        obj.setMaeCategoriaId(ent.getMaeCategoriaId());
        obj.setMaeCategoriaCodigo(ent.getMaeCategoriaCodigo());
        obj.setMaeCategoriaValor(ent.getMaeCategoriaValor());
        obj.setAplicaRescate(ent.getAplicaRescate());
        obj.setAplicaRescateAnexo3Ambulatorio(ent.getAplicaRescateAnexo3Ambulatorio());
        obj.setAplicaRescateAnexo3Hospitalario(ent.getAplicaRescateAnexo3Hospitalario());
        obj.setAplicaRescateAnexo2Urgencia(ent.getAplicaRescateAnexo2Urgencias());
        obj.setDirecciona(ent.getDirecciona());
        obj.setRegistroAfiliadoAfiliacion(ent.getRegistroAfiliadoAfiliacion() == 0 ? false : true);
        obj.setRegistroAfiliadoSolicitud(Integer.valueOf(ent.getRegistroAfiliadoSolicitud()));
        obj.setRegistroAfiliadoHospitalizacion(Integer.valueOf(ent.getRegistroAfiliadoHospitalizacion()));
        obj.setMaeAgrupadorId(ent.getMaeAgrupadorId());
        obj.setMaeAgrupadorCodigo(ent.getMaeAgrupadorCodigo());
        obj.setMaeAgrupadorValor(ent.getMaeAgrupadorValor());
        obj.setSexoAplica(Integer.valueOf(ent.getSexoAplica()));
        obj.setCantidadRegistro((ent.getCantidadRegistros()) ? 1 : 0);
        obj.setAplicaRecobro(ent.getAplicaRecobro());
        obj.setExoneracionObligatoria(ent.getExoneracionObligatoria());
        return obj;
    }

    public static PeProgramas castNegocioEntidad(PePrograma obj) {
        PeProgramas ent = new PeProgramas();
        ent.setActivo(obj.isActivo());
        ent.setCodigoPrograma(obj.getCodigoPrograma());
        ent.setDescripcionPrograma(obj.getDescripcionPrograma());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        ent.setId(obj.getId());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setMaeTipoProgramaId(obj.getMaeTipoProgramaId());
        ent.setMaeTipoProgramaCodigo(obj.getMaeTipoProgramaCodigo());
        ent.setMaeTipoProgramaValor(obj.getMaeTipoProgramaValor());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        ent.setExoneradoCopago(obj.isExoneradoCopago());
        if (obj.getUsuariosId() != null) {
            ent.setGnUsuariosId(new GnUsuarios(obj.getUsuariosId().getId()));
        }
        ent.setMaeCategoriaId(obj.getMaeCategoriaId());
        ent.setMaeCategoriaCodigo(obj.getMaeCategoriaCodigo());
        ent.setMaeCategoriaValor(obj.getMaeCategoriaValor());
        ent.setAplicaRescate(obj.getAplicaRescate());
        ent.setAplicaRescateAnexo3Ambulatorio(obj.getAplicaRescateAnexo3Ambulatorio());
        ent.setAplicaRescateAnexo3Hospitalario(obj.getAplicaRescateAnexo3Hospitalario());
        ent.setAplicaRescateAnexo2Urgencias(obj.getAplicaRescateAnexo2Urgencia());
        ent.setDirecciona(obj.getDirecciona());
        ent.setRegistroAfiliadoAfiliacion((obj.getRegistroAfiliadoAfiliacion()) ? Short.valueOf("1") : Short.valueOf("0"));
        ent.setRegistroAfiliadoSolicitud(obj.getRegistroAfiliadoSolicitud().shortValue());
        ent.setRegistroAfiliadoHospitalizacion(obj.getRegistroAfiliadoHospitalizacion().shortValue());
        ent.setMaeAgrupadorId(obj.getMaeAgrupadorId());
        ent.setMaeAgrupadorCodigo(obj.getMaeAgrupadorCodigo());
        ent.setMaeAgrupadorValor(obj.getMaeAgrupadorValor());
        ent.setSexoAplica(Short.valueOf(obj.getSexoAplica().toString()));
        ent.setCantidadRegistros(obj.getCantidadRegistro() == 0 ? false : true);
        ent.setAplicaRecobro(obj.getAplicaRecobro());
        ent.setExoneracionObligatoria(obj.isExoneracionObligatoria());
        return ent;
    }

    public PePrograma castEntidadNegocioLargo(PeProgramas ent) throws java.lang.Exception {
        PePrograma obj = new PePrograma();

        obj.setActivo(ent.getActivo());
        obj.setCodigoPrograma(ent.getCodigoPrograma());
        obj.setDescripcionPrograma(ent.getDescripcionPrograma());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setId(ent.getId());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setMaeTipoProgramaId(ent.getMaeTipoProgramaId());
        obj.setMaeTipoProgramaCodigo(ent.getMaeTipoProgramaCodigo());
        obj.setMaeTipoProgramaValor(ent.getMaeTipoProgramaValor());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setExoneradoCopago(ent.getExoneradoCopago());
        if (ent.getGnUsuariosId() != null) {
            obj.setUsuariosId(castGnUsuarioEntidadNegocio(ent.getGnUsuariosId()));
        }
        obj.setCantidadAfiliados(cantidadAfiliadoPrograma(ent.getId()));
        obj.setCantidadAfiliadosAgrupador(cantidadAfiliadoProgramaAgrupador( ent.getMaeAgrupadorId()));
        obj.setMaeCategoriaId(ent.getMaeCategoriaId());
        obj.setMaeCategoriaCodigo(ent.getMaeCategoriaCodigo());
        obj.setMaeCategoriaValor(ent.getMaeCategoriaValor());
        obj.setAplicaRescate(ent.getAplicaRescate());
        obj.setAplicaRescateAnexo3Ambulatorio(ent.getAplicaRescateAnexo3Ambulatorio());
        obj.setAplicaRescateAnexo3Hospitalario(ent.getAplicaRescateAnexo3Hospitalario());
        obj.setAplicaRescateAnexo2Urgencia(ent.getAplicaRescateAnexo2Urgencias());
        obj.setDirecciona(ent.getDirecciona());
        obj.setRegistroAfiliadoAfiliacion(ent.getRegistroAfiliadoAfiliacion() == 0 ? false : true);
        obj.setRegistroAfiliadoSolicitud(Integer.valueOf(ent.getRegistroAfiliadoSolicitud()));
        obj.setRegistroAfiliadoHospitalizacion(Integer.valueOf(ent.getRegistroAfiliadoHospitalizacion()));
        obj.setMaeAgrupadorId(ent.getMaeAgrupadorId());
        obj.setMaeAgrupadorCodigo(ent.getMaeAgrupadorCodigo());
        obj.setMaeAgrupadorValor(ent.getMaeAgrupadorValor());
        obj.setSexoAplica(Integer.valueOf(ent.getSexoAplica())); 
        obj.setCantidadRegistro((ent.getCantidadRegistros()) ? 1 : 0);
        obj.setAplicaRecobro(ent.getAplicaRecobro());
        obj.setExoneracionObligatoria(ent.getExoneracionObligatoria());
        return obj;
    }

    public static Usuario castGnUsuarioEntidadNegocio(GnUsuarios per) {
        Usuario obj = new Usuario();
        obj.setEmpresa(new Empresa(per.getGnEmpresasId().getId()));
        obj.setId(per.getId());
        obj.setUsuario(per.getUsuario());
        obj.setNombre(per.getNombre());
        obj.setCorreoElectronico(per.getCorreoElectronico());
        obj.setTelefono(per.getTelefono());
        obj.setCelular(per.getCelular());
        obj.setActivo(per.getActivo());
        obj.setFechaInicio(per.getFechaInicio());
        obj.setFechaFin(per.getFechaFin());
        obj.setFechaUltimoIngreso(per.getFechaUltimoIngreso());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public Connection getConnection() throws java.lang.Exception {
        Connection dbConnection = null;
        try {
            DataSource data = (DataSource) getEntityManager().getEntityManagerFactory().getProperties().get("hibernate.connection.datasource");
            if (data == null) {
                throw new Exception("No se pudo obtener el datasource ");
            }
            dbConnection = (Connection) data.getConnection();
            if (dbConnection == null) {
                throw new Exception("No se pudo obtener la conexion con DB");
            }
        } catch (SQLException e) {
            Exception("Error obtener conexion sql de GenericoServicio : ", e);
        } catch (Exception e) {
            Exception("Error general al obtener la conexion : ", e);
        }

        return dbConnection;
    }
    
    private String agregarJoin(String join, String sql){
        if(sql.contains(join)){
            return sql;
        }else{
            return sql += join;
        }
    }

    @Override
    public List<PePrograma> consultarPorCodigoAgrupadorGeneroAfiliado(String maeAgrupadorCodigo, short generoAfiliado) throws java.lang.Exception {
         List<PePrograma> listResult = new ArrayList<>();
        try {
            String strQuery = "SELECT p FROM PeProgramas p"
                    + " WHERE p.maeAgrupadorCodigo = :maeAgrupadorCodigo"
                    + " AND p.sexoAplica in (:generoAfiliado, 2)"
                    + " AND p.activo = 1";

            List<PeProgramas> list = getEntityManager().createQuery(strQuery)
                    .setParameter("maeAgrupadorCodigo", maeAgrupadorCodigo)
                    .setParameter("generoAfiliado", generoAfiliado)
                    .getResultList();
            for (PeProgramas per : list) {
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
