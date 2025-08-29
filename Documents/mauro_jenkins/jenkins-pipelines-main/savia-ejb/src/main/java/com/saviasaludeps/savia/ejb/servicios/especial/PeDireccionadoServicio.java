/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.especial.PeDireccionado;
import com.saviasaludeps.savia.dominio.especial.PeDireccionadoItem;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.especial.PeDireccionadoRemoto;
import com.saviasaludeps.savia.ejb.entidades.PeDireccionados;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos3;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.entidades.PeDireccionadoItems;
import com.saviasaludeps.savia.ejb.entidades.PeProgramas;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author idbohorquez
 */
@Stateless
@Remote(PeDireccionadoRemoto.class)
@Local(PeDireccionadoLocal.class)
public class PeDireccionadoServicio extends GenericoServicio implements PeDireccionadoLocal, PeDireccionadoRemoto {

    /**
     * Consulta de cantidad de registros en una lista de direccionados
     *
     * @author idbohorquez
     * @creado 29/08/2022
     * @param paramConsulta
     * @return int
     * @throws Exception
     */
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder();
            StringBuilder strTitulo = new StringBuilder();
            strTitulo.append("SELECT COUNT(p.id) FROM PeDireccionados p ");
            if (paramConsulta.getEmpresaId() == null) {
                strQuery.append(" WHERE p.id > 0 ");
            } else {
                strQuery.append(" WHERE p.gnEmpresasId.id = :idEmpresa ");
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append(" AND p.id = '" + (String) e.getValue() + "' ");
                            break;
                        case "auAnexos3Id":
                            strQuery.append(" AND p.auAnexos3Id.id = '" + (String) e.getValue() + "' ");
                            break;
                        case "maeAfiliadoTipoDocumentoId":
                            strQuery.append(" AND p.maeAfiliadoTipoDocumentoId = '" + (String) e.getValue() + "' ");
                            break;
                        case "afiliadoNumeroDocumento":
                            strQuery.append(" AND p.afiliadoNumeroDocumento = '" + (String) e.getValue() + "' ");
                            break;
                        case "afiliadoPrimerApellido":
                            strQuery.append(" AND CONCAT_WS(' ',afiliadoPrimerApellido , afiliadoSegundoApellido) like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "afiliadoPrimerNombre":
                            strQuery.append(" AND CONCAT_WS(' ',afiliadoPrimerNombre , afiliadoSegundoNombre) like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "tieneTutela":
                            strQuery.append(" AND p.tieneTutela = " + (String) e.getValue() + " ");
                            break;
                        case "cntPrestadorSedesId.cntPrestador.razonSocial":
                            strQuery.append(" AND p.cntPrestadorSedesId.cntPrestadoresId.razonSocial like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "estado":
                            strQuery.append(" AND p.estado = " + (String) e.getValue() + " ");
                            break;
                        case "usuarioCrea":
                            strQuery.append(" AND p.usuarioCrea like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "listDireccionadoItems":
                            strTitulo.append(" INNER JOIN PeDireccionadoItems di ON p.id = di.peDireccionadosId.id ");
                            strQuery.append(" AND di.auAnexo3ItemsId.maTecnologiaCodigo = " + (String) e.getValue() + " ");
                            break;
                        case "peProgramasId.descripcionPrograma":
                            strQuery.append(" AND p.peProgramasId.descripcionPrograma like '%" + (String) e.getValue() + "%' ");
                            break;
                    }
                }
            }
            strTitulo.append(strQuery.toString());
            Query query = getEntityManager().createQuery(strTitulo.toString());
            if (paramConsulta.getEmpresaId() != null) {
                query.setParameter("idEmpresa", paramConsulta.getEmpresaId());
            }
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

    /**
     * Consultar lista de registros de direccionados
     *
     * @author idbohorquez
     * @creado 29/08/2022
     * @param paramConsulta
     * @throws Exception
     */
    @Override
    public List<PeDireccionado> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<PeDireccionado> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder();
            StringBuilder strTitulo = new StringBuilder();
            strTitulo.append("SELECT p FROM PeDireccionados p ");
            if (paramConsulta.getEmpresaId() == null) {
                strQuery.append(" WHERE p.id > 0 ");
            } else {
                strQuery.append(" WHERE p.gnEmpresasId.id = :idEmpresa ");
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append(" AND p.id = '" + (String) e.getValue() + "' ");
                            break;
                        case "auAnexos3Id":
                            strQuery.append(" AND p.auAnexos3Id.id = '" + (String) e.getValue() + "' ");
                            break;
                        case "maeAfiliadoTipoDocumentoId":
                            strQuery.append(" AND p.maeAfiliadoTipoDocumentoId = '" + (String) e.getValue() + "' ");
                            break;
                        case "afiliadoNumeroDocumento":
                            strQuery.append(" AND p.afiliadoNumeroDocumento = '" + (String) e.getValue() + "' ");
                            break;
                        case "afiliadoPrimerApellido":
                            strQuery.append(" AND CONCAT_WS(' ',afiliadoPrimerApellido , afiliadoSegundoApellido) like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "afiliadoPrimerNombre":
                            strQuery.append(" AND CONCAT_WS(' ',afiliadoPrimerNombre , afiliadoSegundoNombre) like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "tieneTutela":
                            strQuery.append(" AND p.tieneTutela = " + (String) e.getValue() + " ");
                            break;
                        case "cntPrestadorSedesId.cntPrestador.razonSocial":
                            strQuery.append(" AND p.cntPrestadorSedesId.cntPrestadoresId.razonSocial like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "estado":
                            strQuery.append(" AND p.estado = " + (String) e.getValue() + " ");
                            break;
                        case "usuarioCrea":
                            strQuery.append(" AND p.usuarioCrea like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "listDireccionadoItems":
                            strTitulo.append(" INNER JOIN PeDireccionadoItems di ON p.id = di.peDireccionadosId.id ");
                            strQuery.append(" AND di.auAnexo3ItemsId.maTecnologiaCodigo = " + (String) e.getValue() + " ");
                            break;
                        case "peProgramasId.descripcionPrograma":
                            strQuery.append(" AND p.peProgramasId.descripcionPrograma like '%" + (String) e.getValue() + "%' ");
                            break;
                    }
                }
            }
            strQuery.append(" ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                strQuery.append("p." + paramConsulta.getOrden() + " ").append((paramConsulta.isAscendente() ? "ASC" : "DESC"));
            } else {
                strQuery.append(" p.id DESC ");
            }
            strTitulo.append(strQuery.toString());
            Query query = getEntityManager().createQuery(strTitulo.toString());
            if (paramConsulta.getEmpresaId() != null) {
                query.setParameter("idEmpresa", paramConsulta.getEmpresaId());
            }
            query.setFirstResult(paramConsulta.getPrimerRegistro());
            query.setMaxResults(paramConsulta.getRegistrosPagina());
            List<PeDireccionados> list = query.getResultList();
            for (PeDireccionados dir : list) {
                listResult.add(castEntidadNegocio(dir));
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
     * Funcion encargada de guardar un nuevo registro de direccionado en DB
     *
     * @author idbohorquez
     * @param obj
     * @creado 30/08/2022
     * @return int
     * @throws Exception
     */
    @Override
    public int insertar(PeDireccionado obj) throws Exception {
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
     * Funcion encargada de consultar registro de direccionado por su id
     *
     * @author idbohorquez
     * @param id
     * @creado 30/08/2022
     * @return PeDireccionado
     * @throws Exception
     */
    @Override
    public PeDireccionado consultar(int id) throws Exception {
        PeDireccionado objRes = null;
        try {
            objRes = castEntidadNegocio((PeDireccionados) getEntityManager().find(PeDireccionados.class, id));
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
     * Metodo encargada de anular un registro de direccionado
     *
     * @author idbohorquez
     * @param obj
     * @creado 01/09/2022
     * @throws Exception
     */
    @Override
    public void anular(PeDireccionado obj) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PeDireccionados SET estado = :estado, ");
            sql.append("observacion = :observacion, ");
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("observacion", obj.getObservacion());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    /**
     * Metodo encargada de cambiar el estado de un registro
     *
     * @author idbohorquez
     * @param obj
     * @creado 14/09/2022
     * @throws Exception
     */
    @Override
    public void actualizarEstado(PeDireccionado obj) throws Exception {
        try {
            StringBuilder sql = new StringBuilder("UPDATE PeDireccionados SET ");
            sql.append("estado = :estado, ");
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");
            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    /**
     * Consulta de cantidad de registros en una lista de direccionados
     * incluyendo id afiliado
     *
     * @author idbohorquez
     * @creado 21/12/2022
     * @param paramConsulta
     * @return int
     * @throws Exception
     */
    @Override
    public int consultarCantidadListaAfiliado(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder();
            StringBuilder strTitulo = new StringBuilder();
            strTitulo.append("SELECT COUNT(p) FROM PeDireccionados p ");
            if (paramConsulta.getEmpresaId() == null) {
                strQuery.append(" WHERE p.id > 0 ");
            } else {
                strQuery.append(" WHERE p.gnEmpresasId.id = :idEmpresa ");
            }
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery.append(" AND p.asegAfiliadosId.id = " + paramConsulta.getParametroConsulta1() + " ");
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append(" AND p.id = '" + (String) e.getValue() + "' ");
                            break;
                        case "auAnexos3Id":
                            strQuery.append(" AND p.auAnexos3Id.id = '" + (String) e.getValue() + "' ");
                            break;
                        case "maeAfiliadoTipoDocumentoValor":
                            strQuery.append(" AND p.maeAfiliadoTipoDocumentoValor = '" + (String) e.getValue() + "' ");
                            break;
                        case "afiliadoNumeroDocumento":
                            strQuery.append(" AND p.afiliadoNumeroDocumento = '" + (String) e.getValue() + "' ");
                            break;
                        case "afiliadoPrimerApellido":
                            strQuery.append(" AND CONCAT_WS(' ',afiliadoPrimerApellido , afiliadoSegundoApellido) like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "afiliadoPrimerNombre":
                            strQuery.append(" AND CONCAT_WS(' ',afiliadoPrimerNombre , afiliadoSegundoNombre) like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "tieneTutela":
                            strQuery.append(" AND p.tieneTutela = " + (String) e.getValue() + " ");
                            break;
                        case "cntPrestadorSedesId.cntPrestador.razonSocial":
                            strQuery.append(" AND p.cntPrestadorSedesId.cntPrestadoresId.razonSocial like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "estado":
                            strQuery.append(" AND p.estado = " + (String) e.getValue() + " ");
                            break;
                        case "usuarioCrea":
                            strQuery.append(" AND p.usuarioCrea like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "listDireccionadoItems":
                            strTitulo.append(" INNER JOIN PeDireccionadoItems di ON p.id = di.peDireccionadosId.id ");
                            strQuery.append(" AND di.auAnexo3ItemsId.maTecnologiaCodigo = " + (String) e.getValue() + " ");
                            break;
                        case "peProgramasId.descripcionPrograma":
                            strQuery.append(" AND p.peProgramasId.descripcionPrograma like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "idAfiliado":
                            strQuery.append(" AND p.asegAfiliadosId.id = " + (String) e.getValue() + " ");
                            break;
                    }
                }
            }
            strTitulo.append(strQuery.toString());
            Query query = getEntityManager().createQuery(strTitulo.toString());
            if (paramConsulta.getEmpresaId() != null) {
                query.setParameter("idEmpresa", paramConsulta.getEmpresaId());
            }
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

    /**
     * Consultar lista de registros de direccionados incluyendo id afiliado
     *
     * @author idbohorquez
     * @creado 21/12/2022
     * @param paramConsulta
     * @throws Exception
     */
    @Override
    public List<PeDireccionado> consultarListaAfiliado(ParamConsulta paramConsulta) throws Exception {
        List<PeDireccionado> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder();
            StringBuilder strTitulo = new StringBuilder();
            strTitulo.append("SELECT p FROM PeDireccionados p ");
            if (paramConsulta.getEmpresaId() == null) {
                strQuery.append(" WHERE p.id > 0 ");
            } else {
                strQuery.append(" WHERE p.gnEmpresasId.id = :idEmpresa ");
            }
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery.append(" AND p.asegAfiliadosId.id = " + paramConsulta.getParametroConsulta1() + " ");
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append(" AND p.id = '" + (String) e.getValue() + "' ");
                            break;
                        case "auAnexos3Id":
                            strQuery.append(" AND p.auAnexos3Id.id = '" + (String) e.getValue() + "' ");
                            break;
                        case "maeAfiliadoTipoDocumentoValor":
                            strQuery.append(" AND p.maeAfiliadoTipoDocumentoValor = '" + (String) e.getValue() + "' ");
                            break;
                        case "afiliadoNumeroDocumento":
                            strQuery.append(" AND p.afiliadoNumeroDocumento = '" + (String) e.getValue() + "' ");
                            break;
                        case "afiliadoPrimerApellido":
                            strQuery.append(" AND CONCAT_WS(' ',afiliadoPrimerApellido , afiliadoSegundoApellido) like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "afiliadoPrimerNombre":
                            strQuery.append(" AND CONCAT_WS(' ',afiliadoPrimerNombre , afiliadoSegundoNombre) like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "tieneTutela":
                            strQuery.append(" AND p.tieneTutela = " + (String) e.getValue() + " ");
                            break;
                        case "cntPrestadorSedesId.cntPrestador.razonSocial":
                            strQuery.append(" AND p.cntPrestadorSedesId.cntPrestadoresId.razonSocial like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "estado":
                            strQuery.append(" AND p.estado = " + (String) e.getValue() + " ");
                            break;
                        case "usuarioCrea":
                            strQuery.append(" AND p.usuarioCrea like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "listDireccionadoItems":
                            strTitulo.append(" INNER JOIN PeDireccionadoItems di ON p.id = di.peDireccionadosId.id ");
                            strQuery.append(" AND di.auAnexo3ItemsId.maTecnologiaCodigo = " + (String) e.getValue() + " ");
                            break;
                        case "peProgramasId.descripcionPrograma":
                            strQuery.append(" AND p.peProgramasId.descripcionPrograma like '%" + (String) e.getValue() + "%' ");
                            break;
                        case "idAfiliado":
                            strQuery.append(" AND p.asegAfiliadosId.id = " + (String) e.getValue() + " ");
                            break;
                    }
                }
            }
            strQuery.append(" ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                strQuery.append("p." + paramConsulta.getOrden() + " ").append((paramConsulta.isAscendente() ? "ASC" : "DESC"));
            } else {
                strQuery.append(" p.id DESC ");
            }
            strTitulo.append(strQuery.toString());
            Query query = getEntityManager().createQuery(strTitulo.toString());
            if (paramConsulta.getEmpresaId() != null) {
                query.setParameter("idEmpresa", paramConsulta.getEmpresaId());
            }
            query.setFirstResult(paramConsulta.getPrimerRegistro());
            query.setMaxResults(paramConsulta.getRegistrosPagina());
            List<PeDireccionados> list = query.getResultList();
            for (PeDireccionados dir : list) {
                listResult.add(castEntidadNegocio(dir));
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
     * Metodo para anular los registros direccionados de un afiliado retirado
     *
     * @author idbohorquez
     * @creado 21/04/2023
     * @param idAfiliado
     * @param observacion
     * @throws Exception
     */
    @Override
    public void anularAfiliadoRetirado(PeDireccionado obj) throws java.lang.Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PeDireccionados SET estado = :estado, ");
            sql.append("observacion = :observacion, ");
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE asegAfiliadosId.id = :idAfilaido AND estado = 1 ");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("observacion", obj.getObservacion());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("idAfilaido", obj.getAsegAfiliadosId().getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    /**
     * Funcion que obtiene los elementos de entidad y los pasa a objeto de
     * negocio
     *
     * @author idbohorquez
     * @param ent
     * @return
     * @creado 29/08/2022
     * @throws Exception
     */
    public PeDireccionado castEntidadNegocio(PeDireccionados ent) throws Exception {
        PeDireccionado obj = new PeDireccionado();
        obj.setId(ent.getId());
        obj.setMaeAfiliadoTipoDocumentoId(ent.getMaeAfiliadoTipoDocumentoId());
        obj.setMaeAfiliadoTipoDocumentoCodigo(ent.getMaeAfiliadoTipoDocumentoCodigo());
        obj.setMaeAfiliadoTipoDocumentoValor(ent.getMaeAfiliadoTipoDocumentoValor());
        obj.setAfiliadoNumeroDocumento(ent.getAfiliadoNumeroDocumento());
        obj.setAfiliadoPrimerNombre(ent.getAfiliadoPrimerNombre());
        obj.setAfiliadoSegundoNombre(ent.getAfiliadoSegundoNombre());
        obj.setAfiliadoPrimerApellido(ent.getAfiliadoPrimerApellido());
        obj.setAfiliadoSegundoApellido(ent.getAfiliadoSegundoApellido());
        obj.setEstado(ent.getEstado());
        obj.setObservacion(ent.getObservacion());
        obj.setTieneTutela(ent.getTieneTutela());

        AsegAfiliado asegAfiliado = new AsegAfiliado();
        asegAfiliado.setId(ent.getAsegAfiliadosId().getId());
        asegAfiliado.setFechaNacimiento(ent.getAsegAfiliadosId().getFechaNacimiento());
        obj.setAsegAfiliadosId(asegAfiliado);

        obj.setAuAnexos3Id(new AuAnexo3(ent.getAuAnexos3Id().getId()));

        CntPrestadorSede prestadorSede = new CntPrestadorSede();
        CntPrestador prestador = new CntPrestador();
        prestador.setId(ent.getCntPrestadoresId().getId());
        prestador.setRazonSocial(ent.getCntPrestadorSedesId().getCntPrestadoresId().getRazonSocial());
        prestadorSede.setId(ent.getCntPrestadorSedesId().getId());
        prestadorSede.setCntPrestador(prestador);
        prestadorSede.setUbicacionId(ent.getCntPrestadorSedesId().getUbicacionId());
        prestadorSede.setNombreSede(ent.getCntPrestadorSedesId().getNombre());

        obj.setFechaHoraEnGestion(ent.getFechaHoraEnGestion());
        obj.setFechaHoraGestiona(ent.getFechaHoraGestiona());
        obj.setCntPrestadorSedesId(prestadorSede);
        obj.setCntPrestadoresId(new CntPrestador(ent.getCntPrestadoresId().getId()));
        obj.setPeProgramasId(new PePrograma(ent.getPeProgramasId().getId()));
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());

        List<PeDireccionadoItem> listDireccionadoItem = new ArrayList<>();
        for (PeDireccionadoItems items : ent.getPeDireccionadoItemsList()) {
            PeDireccionadoItem direccionadoItem = new PeDireccionadoItem();

            AuAnexo3Item auAnexo3Item = new AuAnexo3Item();
            auAnexo3Item.setId(items.getAuAnexo3ItemsId().getId());
            auAnexo3Item.setEstado(items.getAuAnexo3ItemsId().getEstado());
            auAnexo3Item.setMaTecnologiaId(items.getAuAnexo3ItemsId().getMaTecnologiaId());
            auAnexo3Item.setMaTecnologiaCodigo(items.getAuAnexo3ItemsId().getMaTecnologiaCodigo());
            auAnexo3Item.setMaTecnologiaValor(items.getAuAnexo3ItemsId().getMaTecnologiaValor());
            auAnexo3Item.setCantidadSolicitada(items.getAuAnexo3ItemsId().getCantidadSolicitada());
            auAnexo3Item.setTipoTecnologia(items.getAuAnexo3ItemsId().getTipoTecnologia());
            auAnexo3Item.setMaDiagnosticoValor(items.getAuAnexo3ItemsId().getMaDiagnosticoValor());
            direccionadoItem.setAuAnexo3ItemsId(auAnexo3Item);

            direccionadoItem.setId(items.getId());
            direccionadoItem.setPeDireccionadosId(new PeDireccionado(items.getAuAnexo3ItemsId().getId()));
            direccionadoItem.setEstado(items.getEstado());
            direccionadoItem.setFechaPrestacion(items.getFechaPrestacion());
            direccionadoItem.setObservacion(items.getObservacion());
            listDireccionadoItem.add(direccionadoItem);
        }
        obj.setListDireccionadoItems(listDireccionadoItem);

        PePrograma pePrograma = new PePrograma();
        pePrograma.setId(ent.getPeProgramasId().getId());
        pePrograma.setDescripcionPrograma(ent.getPeProgramasId().getDescripcionPrograma());
        obj.setPeProgramasId(pePrograma);
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        return obj;
    }

    /**
     * Funcion que obtiene los elementos de negocio y los pasa a objeto de
     * entidad
     *
     * @author idbohorquez
     * @param obj
     * @creado 30/08/2022
     * @return PeDireccionados
     */
    public static PeDireccionados castNegocioEntidad(PeDireccionado obj) {
        PeDireccionados ent = new PeDireccionados();
        ent.setId(obj.getId());
        ent.setMaeAfiliadoTipoDocumentoId(obj.getMaeAfiliadoTipoDocumentoId());
        ent.setMaeAfiliadoTipoDocumentoCodigo(obj.getMaeAfiliadoTipoDocumentoCodigo());
        ent.setMaeAfiliadoTipoDocumentoValor(obj.getMaeAfiliadoTipoDocumentoValor());
        ent.setAfiliadoNumeroDocumento(obj.getAfiliadoNumeroDocumento());
        ent.setAfiliadoPrimerNombre(obj.getAfiliadoPrimerNombre());
        ent.setAfiliadoSegundoNombre(obj.getAfiliadoSegundoNombre());
        ent.setAfiliadoPrimerApellido(obj.getAfiliadoPrimerApellido());
        ent.setAfiliadoSegundoApellido(obj.getAfiliadoSegundoApellido());
        ent.setEstado(obj.getEstado());
        //ent.setObservacion(obj.getObservacion());
        ent.setTieneTutela(obj.getTieneTutela());
        ent.setAsegAfiliadosId(new AsegAfiliados(obj.getAsegAfiliadosId().getId()));
        ent.setAuAnexos3Id(new AuAnexos3(obj.getAuAnexos3Id().getId()));
        ent.setCntPrestadorSedesId(new CntPrestadorSedes(obj.getCntPrestadorSedesId().getId()));
        ent.setCntPrestadoresId(new CntPrestadores(obj.getCntPrestadoresId().getId()));
        ent.setPeProgramasId(new PeProgramas(obj.getPeProgramasId().getId()));
        ent.setFechaHoraEnGestion(obj.getFechaHoraEnGestion());
        ent.setFechaHoraGestiona(obj.getFechaHoraGestiona());
        ent.setGnEmpresasId(new GnEmpresas(obj.getGnEmpresasId().getId()));
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        ent.setTerminalModifica(obj.getTerminalModifica());
        return ent;
    }

}
