/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadoSugerido;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos3;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizaciones;
import com.saviasaludeps.savia.ejb.entidades.PeAfiliadosSugeridos;
import com.saviasaludeps.savia.ejb.entidades.PeProgramas;
import com.saviasaludeps.savia.ejb.entidades.RefAnexos9;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadoSugeridoRemoto;
import javax.persistence.Query;

/**
 *
 * @author idbohorquez
 */
@Stateless
@Remote(PeAfiliadoSugeridoRemoto.class)
public class PeAfiliadoSugeridoServicio extends GenericoServicio implements PeAfiliadoSugeridoRemoto {

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @author idbohorquez
     * @creacion 04/11/2022
     * @param paramConsulta
     * @return int
     * @throws Exception
     */
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(p) FROM PeAfiliadosSugeridos p ");
            strQuery.append(" WHERE p.id > 0 AND p.estado in (1,3,4)  ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "estado":
                            strQuery.append(" AND p.estado = " + (String) e.getValue() + " ");
                            break;
                        case "asegAfiliado.maeTipoDocumento":
                            strQuery.append(" AND p.asegAfiliadosId.maeTipoDocumentoId = ").append((String) e.getValue());
                            break;
                        case "asegAfiliado.numeroDocumento":
                            strQuery.append(" AND p.asegAfiliadosId.numeroDocumento = '").append((String) e.getValue() + "' ");
                            break;
                        case "asegAfiliado.nombres":
                            strQuery.append(" AND CONCAT(p.asegAfiliadosId.primerNombre, ' ', p.asegAfiliadosId.segundoNombre) LIKE '%").append(e.getValue()).append( "%' ");
                            break;
                        case "asegAfiliado.apellidos":
                            strQuery.append(" AND CONCAT(p.asegAfiliadosId.primerApellido, ' ', p.asegAfiliadosId.segundoApellido)  LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "asegAfiliado.maeGeneroId":
                            strQuery.append(" AND p.asegAfiliadosId.maeGeneroId = ").append(e.getValue());
                            break;
                        case "asegAfiliado.afiliacionUbicacion.nombre":
                            strQuery.append(" AND p.asegAfiliadosId.afiliacionUbicacionesId.nombre like '%").append(e.getValue()).append("%' ");
                            break;
                        case "pePrograma.id":
                            strQuery.append(" AND p.peProgramasId.id = ").append(e.getValue());
                            break;
                        case "pePrograma.usuariosId.nombre":
                            strQuery.append(" AND p.peProgramasId.gnUsuariosId.nombre like '%").append(e.getValue()).append("%' ");
                            break;
                        case "origen":
                            strQuery.append(" AND p.origen = " + (String) e.getValue() + " ");
                            break;
                        case "origenRechazo":
                            strQuery.append(" AND p.rechazoOrigen = " + (String) e.getValue() + " ");
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery.toString())
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

    /**
     * Consultar lista de registros en sugeridos
     *
     * @author idbohorquez
     * @creacion 04/11/2022
     * @param paramConsulta
     * @return List<PeSugerido>
     * @throws Exception
     */
    @Override
    public List<PeAfiliadoSugerido> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<PeAfiliadoSugerido> listResult = new ArrayList();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT p FROM PeAfiliadosSugeridos p WHERE p.id > 0 AND p.estado in (1,3,4) ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "estado":
                            strQuery.append(" AND p.estado = " + (String) e.getValue() + " ");
                            break;
                        case "asegAfiliado.maeTipoDocumento":
                            strQuery.append(" AND p.asegAfiliadosId.maeTipoDocumentoId = ").append((String) e.getValue());
                            break;
                        case "asegAfiliado.numeroDocumento":
                            strQuery.append(" AND p.asegAfiliadosId.numeroDocumento = '").append((String) e.getValue() + "' ");
                            break;
                        case "asegAfiliado.nombres":
                            strQuery.append(" AND CONCAT(p.asegAfiliadosId.primerNombre, ' ', p.asegAfiliadosId.segundoNombre) LIKE '%").append(e.getValue()).append( "%' ");
                            break;
                        case "asegAfiliado.apellidos":
                            strQuery.append(" AND CONCAT(p.asegAfiliadosId.primerApellido, ' ', p.asegAfiliadosId.segundoApellido)  LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "asegAfiliado.maeGeneroId":
                            strQuery.append(" AND p.asegAfiliadosId.maeGeneroId = ").append(e.getValue());
                            break;
                        case "asegAfiliado.afiliacionUbicacion.nombre":
                            strQuery.append(" AND p.asegAfiliadosId.afiliacionUbicacionesId.nombre like '%").append(e.getValue()).append("%' ");
                            break;
                        case "pePrograma.id":
                            strQuery.append(" AND p.peProgramasId.id = ").append(e.getValue());
                            break;
                        case "pePrograma.usuariosId.nombre":
                            strQuery.append(" AND p.peProgramasId.gnUsuariosId.nombre like '%").append(e.getValue()).append("%' ");
                            break;
                        case "origen":
                            strQuery.append(" AND p.origen = " + (String) e.getValue() + " ");
                            break;
                        case "origenRechazo":
                            strQuery.append(" AND p.rechazoOrigen = " + (String) e.getValue() + " ");
                            break;
                    }
                }
            }
            strQuery.append(" ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                strQuery.append(" p." + paramConsulta.getOrden() + " " + (paramConsulta.isAscendente() ? "ASC" : "DESC"));
            } else {
                strQuery.append(" p.id DESC ");
            }
            List<PeAfiliadosSugeridos> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (PeAfiliadosSugeridos sugerido : list) {
                listResult.add(castEntidadNegocio(sugerido));
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
     * Insertar nuevo registro sugerido
     *
     * @author idbohorquez
     * @creacion 18/11/2022
     * @param obj
     * @return int
     * @throws Exception
     */
    @Override
    public int insertar(PeAfiliadoSugerido obj) throws Exception {
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

    /**
     * Metodo para consultar un registro de sugerido por su id
     *
     * @author idbohorquez
     * @creacion 15/11/2022
     * @param int
     * @return PeAfiliadoSugerido
     * @throws Exception
     */
    @Override
    public PeAfiliadoSugerido consultar(int id) throws Exception {
        PeAfiliadoSugerido objRes = null;
        try {
            objRes = castEntidadNegocio((PeAfiliadosSugeridos) getEntityManager().find(PeAfiliadosSugeridos.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    /**
     * Metodo para cambiar estado de sugeridos
     *
     * @author idbohorquez
     * @creacion 17/11/2022
     * @throws Exception
     */
    @Override
    public void cambiarEstado(PeAfiliadoSugerido obj) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PeAfiliadosSugeridos SET estado = :estado, ");
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estado", (short) obj.getEstado());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    /**
     * 
     * @param idAfiliado
     * @param idPrograma
     * @return
     * @throws Exception 
     */    
    @Override
    public PeAfiliadoSugerido consultarAfiliadoPorProgramaActivos(int idAfiliado, int idPrograma) throws Exception {
        PeAfiliadoSugerido result = new PeAfiliadoSugerido();
        try {
            String strQuery = "SELECT p FROM PeAfiliadosSugeridos p WHERE p.asegAfiliadosId.id = :idAfiliado "
                    + "AND p.peProgramasId.id = :idPrograma AND p.estado IN("
                    + PeAfiliadoSugerido.ESTADO_MARCADO + "," + PeAfiliadoSugerido.ESTADO_PENDIENTE + ")";

            PeAfiliadosSugeridos per = (PeAfiliadosSugeridos) getEntityManager().createQuery(strQuery)
                    .setParameter("idAfiliado", idAfiliado).setParameter("idPrograma", idPrograma)
                    .setMaxResults(1)
                    .getSingleResult();

            result = castEntidadNegocio(per);

        } catch (NoResultException e) {
            result = new PeAfiliadoSugerido();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
            result = new PeAfiliadoSugerido();
        } finally {
            cerrarEntityManager();
        }
        return result;
    }
    
    /**
     * Metodo para rechazar un registro de sugeridos
     * @author idbohorquez
     * @param obj
     * @creacion 14/02/2023
     * @throws Exception 
     */
    @Override
    public void rechazar(PeAfiliadoSugerido obj) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PeAfiliadosSugeridos SET estado = :estado, ");
            sql.append("rechazo_origen = :rechazo_origen, ");
            sql.append("rechazo_observacion = :rechazo_observacion, ");
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estado", (short) obj.getEstado());
            query.setParameter("rechazo_origen", obj.getOrigenRechazo());
            query.setParameter("rechazo_observacion", obj.getObservacionRechazo());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void rechazarAfiliadoretirado(PeAfiliadoSugerido obj) throws java.lang.Exception {
       try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PeAfiliadosSugeridos SET estado = :estado, ");
            sql.append("rechazo_origen = :rechazo_origen, ");
            sql.append("rechazo_observacion = :rechazo_observacion, ");
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE asegAfiliadosId.id = :id AND estado = 1");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estado", (short) obj.getEstado());
            query.setParameter("rechazo_origen", obj.getOrigenRechazo());
            query.setParameter("rechazo_observacion", obj.getObservacionRechazo());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getAsegAfiliado().getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    /**
     * Metodo para consultar listado de sugeridos por id afiliado.
     * @author idbohorquez
     * @param idAfiliado
     * @return 
     * @creacion 11/06/2024
     * @throws Exception 
     */
    @Override
    public List<PeAfiliadoSugerido> consultarSugeridosAfiliado(int idAfiliado) throws Exception {
        List<PeAfiliadoSugerido> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p FROM PeAfiliadosSugeridos p WHERE p.asegAfiliadosId.id = :idAfiliado";

            List<PeAfiliadosSugeridos> lista = getEntityManager().createQuery(strQuery)
                    .setParameter("idAfiliado", idAfiliado)
                    .getResultList();                    
            
            for (PeAfiliadosSugeridos sugerido : lista) {
                listResult.add(castEntidadNegocio(sugerido));
            }

        } catch (NoResultException e) {
            listResult = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
            listResult = new ArrayList<>();
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    

    /**
     * funcion encargada de pasar la información del objeto entidad a dominino
     *
     * @author idbohorquez
     * @creacion 04/11/2022
     * @param paramConsulta
     * @return List<PeSugerido>
     * @throws Exception
     */
    private static PeAfiliadoSugerido castEntidadNegocio(PeAfiliadosSugeridos ent) {
        PeAfiliadoSugerido obj = new PeAfiliadoSugerido();
        obj.setId(ent.getId());
        obj.setOrigen(ent.getOrigen());
        if(ent.getRechazoOrigen() != null){
            obj.setOrigenRechazo(ent.getRechazoOrigen().intValue());
        }        
        obj.setEstado(ent.getEstado());
        obj.setObservacion(ent.getObservacion());
        obj.setObservacionRechazo(ent.getRechazoObservacion());
        if (ent.getAsegAfiliadosId() != null) {
            AsegAfiliado afiliado = new AsegAfiliado(ent.getAsegAfiliadosId().getId());
            afiliado.setMaeTipoDocumentoValor(ent.getAsegAfiliadosId().getMaeTipoDocumentoValor());
            afiliado.setMaeTipoDocumentoCodigo(ent.getAsegAfiliadosId().getMaeTipoDocumentoCodigo());
            afiliado.setNumeroDocumento(ent.getAsegAfiliadosId().getNumeroDocumento());
            afiliado.setNombresCF(ent.getAsegAfiliadosId().getPrimerNombre() + " " + (ent.getAsegAfiliadosId().getSegundoNombre() == null ? "" : ent.getAsegAfiliadosId().getSegundoNombre()));
            afiliado.setApellidosCF(ent.getAsegAfiliadosId().getPrimerApellido() + " " + (ent.getAsegAfiliadosId().getSegundoApellido() == null ? "" : ent.getAsegAfiliadosId().getSegundoApellido()));
            afiliado.setFechaNacimiento(ent.getAsegAfiliadosId().getFechaNacimiento());
            afiliado.setGenero(ent.getAsegAfiliadosId().getMaeGeneroValor());
            afiliado.setPrimerNombre(ent.getAsegAfiliadosId().getPrimerNombre());
            afiliado.setSegundoNombre(ent.getAsegAfiliadosId().getSegundoNombre());
            afiliado.setPrimerApellido(ent.getAsegAfiliadosId().getPrimerApellido());
            afiliado.setSegundoApellido(ent.getAsegAfiliadosId().getSegundoApellido());
            Ubicacion ubicacionAfiliacion = new Ubicacion(ent.getAsegAfiliadosId().getAfiliacionUbicacionesId().getId());
            ubicacionAfiliacion.setNombre(ent.getAsegAfiliadosId().getAfiliacionUbicacionesId().getNombre());
            afiliado.setAfiliacionUbicacion(ubicacionAfiliacion);
            obj.setAsegAfiliado(afiliado);
        }
        if (ent.getPeProgramasId() != null) {
            PePrograma programa = new PePrograma(ent.getPeProgramasId().getId());
            programa.setCodigoPrograma(ent.getPeProgramasId().getCodigoPrograma());
            programa.setDescripcionPrograma(ent.getPeProgramasId().getDescripcionPrograma());
            Usuario user = new Usuario(ent.getPeProgramasId().getGnUsuariosId().getId());
            user.setNombre(ent.getPeProgramasId().getGnUsuariosId().getNombre());
            programa.setUsuariosId(user);
            obj.setPePrograma(programa);
        }
        if (ent.getAuAnexos3Id() != null) {
            obj.setAuAnexos3(new AuAnexo3(ent.getAuAnexos3Id().getId()));
        }
        if (ent.getRefAnexos9Id() != null) {
            obj.setRefAnexos9(new RefAnexo9(ent.getRefAnexos9Id().getId()));
        }
        if (ent.getAucHospitalizacionesId() != null) {
            obj.setAucHospitalizaciones(new AucHospitalizacion(ent.getAucHospitalizacionesId().getId()));
        }
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        return obj;
    }

    public static PeAfiliadosSugeridos castNegocioEntidad(PeAfiliadoSugerido obj) {
        PeAfiliadosSugeridos ent = new PeAfiliadosSugeridos();
        ent.setAsegAfiliadosId(new AsegAfiliados(obj.getAsegAfiliado().getId()));
        ent.setPeProgramasId(new PeProgramas(obj.getPePrograma().getId()));
        ent.setOrigen((short) obj.getOrigen());
        ent.setEstado(Short.valueOf("1"));
        if (obj.getAuAnexos3() != null) {
            ent.setAuAnexos3Id(new AuAnexos3(obj.getAuAnexos3().getId()));
        }
        if (obj.getRefAnexos9() != null) {
            ent.setRefAnexos9Id(new RefAnexos9(obj.getRefAnexos9().getId()));
        }
        if (obj.getAucHospitalizaciones() != null) {
            ent.setAucHospitalizacionesId(new AucHospitalizaciones(obj.getAucHospitalizaciones().getId()));
        }
        ent.setObservacion(obj.getObservacion());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }    
    
    public static List<PeAfiliadoSugerido> castEntidadNegocio(List<PeAfiliadosSugeridos> listEnt) {
        List<PeAfiliadoSugerido> listado = new ArrayList<PeAfiliadoSugerido>();
        for (PeAfiliadosSugeridos sugerido : listEnt) {
            listado.add(castEntidadNegocio(sugerido));
        }
        return listado;
    }

}
