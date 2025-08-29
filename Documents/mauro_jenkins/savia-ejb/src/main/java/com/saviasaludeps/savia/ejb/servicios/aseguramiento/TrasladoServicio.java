/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.aseguramiento;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegTraslado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AsegTraslados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.aseguramiento.TrasladoRemoto;
import org.hibernate.Session;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(TrasladoRemoto.class)
public class TrasladoServicio extends GenericoServicio implements TrasladoRemoto {

    /**
     * Consulta de cantidad de registros en una lista - PENDIENTE IMPLEMENTACION
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i=0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AsegTraslados p ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeEstadoAfiliacionId":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.maeEstadoAfiliacionId = " + e.getValue() + " ";
                            break;
                        case "regimen":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.regimen LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "idAfiliado":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.idAfiliado LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoDocumentoId":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.maeTipoDocumentoId = " + (String) e.getValue() + " ";
                            break;
                        case "numeroDocumento":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.numeroDocumento LIKE '%" + (String) e.getValue() + "&' ";
                            break;
                        case "primerNombre":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.primerNombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "segundoNombre":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.segundoNombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "primerApellido":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.primerApellido LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "segundoApellido":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.segundoApellido LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "genero":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.genero LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaNacimiento":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.fechaNacimiento = '" + (String) e.getValue() + "' ";
                            break;
                        case "afiliacionUbicacionesId.nombre":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "UPPER(p.afiliacionUbicacionesId.nombre) LIKE UPPER('%" + (String) e.getValue() + "%') ";
                            break;
                        case "nivelSisben":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.nivelSisben LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "categoriaIbc"://validar
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.categoriaIbc LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaAfiliacionEps":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.fechaAfiliacionEps = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaEgresoEps":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.fechaEgresoEps = '" + (String) e.getValue() + "' ";
                            break;
                        case "modeloLiquidacion":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.modeloLiquidacion LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "primariaPrestadorSedesId.nombreSede":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += " UPPER(p.primariaCntPrestadorSedesId.nombreSede) LIKE  UPPER('%" + (String) e.getValue() + "%') ";
                            break;
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
    
    /**
     * Consultar lista de registros -PENDIENTE IMPLEMENTACION
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    @Override
    public List<AsegTraslado> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AsegTraslado> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM AsegTraslados p ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeEstadoAfiliacionId":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.maeEstadoAfiliacionId = " + e.getValue() + " ";
                            break;
                        case "regimen":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.regimen LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "idAfiliado":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.idAfiliado LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoDocumentoId":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.maeTipoDocumentoId = " + (String) e.getValue() + " ";
                            break;
                        case "numeroDocumento":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.numeroDocumento LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "primerNombre":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.primerNombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "segundoNombre":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.segundoNombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "primerApellido":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.primerApellido LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "segundoApellido":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.segundoApellido LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "genero":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.genero LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaNacimiento":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.fechaNacimiento = '" + (String) e.getValue() + "' ";
                            break;
                        case "afiliacionUbicacionesId.nombre":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "UPPER(p.afiliacionUbicacionesId.nombre) LIKE UPPER('%" + (String) e.getValue() + "%') ";
                            break;
                        case "nivelSisben":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.nivelSisben LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "categoriaIbc"://validar
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.categoriaIbc LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaAfiliacionEps":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.fechaAfiliacionEps = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaEgresoEps":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.fechaEgresoEps = '" + (String) e.getValue() + "' ";
                            break;
                        case "modeloLiquidacion":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "p.modeloLiquidacion LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "primariaPrestadorSedesId.nombreSede":
                            strQuery += retornarConector(i);
                            i++;
                            strQuery += "UPPER(p.primariaCntPrestadorSedesId.nombreSede) LIKE  UPPER('%" + (String) e.getValue() + "%') ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.fechaHoraCrea DESC";
            }
            List<AsegTraslados> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (AsegTraslados per : list) {
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
    public AsegTraslado consultar(int id) throws Exception {
        AsegTraslado objRes = null;
        try {
            objRes = castEntidadNegocio((AsegTraslados) getEntityManager().find(AsegTraslados.class, id));
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
    public int insertar(AsegTraslado obj) throws Exception {
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
    public void actualizar(AsegTraslado obj) throws Exception {
        try {
            AsegTraslados per = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AsegTraslados a SET ";
            strQuery += "a.codigoEps = :codigoEps ,";
            strQuery += "a.tipoDocumentoBdua = :tipoDocumentoBdua ,";
            strQuery += "a.numeroDocumentoBdua = :numeroDocumentoBdua ,";
            strQuery += "a.primerApellidoBdua = :primerApellidoBdua ,";
            strQuery += "a.segundoApellidoBdua = :segundoApellidoBdua ,";
            strQuery += "a.primerNombreBdua = :primerNombreBdua ,";
            strQuery += "a.segundoNombreBdua = :segundoNombreBdua ,";
            strQuery += "a.fechaNacimientoBdua = :fechaNacimientoBdua, ";
            strQuery += "a.generoBdua = :generoBdua, ";
            strQuery += "a.fechaReporte = :fechaReporte, ";
            strQuery += "a.ubicacionesId = :ubicacionesId, ";
            strQuery += "a.usuarioCrea = :usuarioCrea, ";
            strQuery += "a.terminalCrea = :terminalCrea, ";
            strQuery += "a.fechaHoraCrea = :fechaHoraCrea, ";
            strQuery += "a.usuarioModifica = :usuarioModifica, ";
            strQuery += "a.terminalModifica = :terminalModifica, ";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica, ";
            strQuery += "a.maeEpsOrigenId = :maeEpsOrigenId ";
            if (per.getAsegAfiliadosId() != null) {
                strQuery += ", a.asegAfiliadosId.id = " + per.getAsegAfiliadosId().getId() + " ";
            }
            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(per);
            query.executeUpdate();
            
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AsegTraslado eliminar(int id) throws Exception {
        AsegTraslado obj = null;
        try {
            AsegTraslados ent = getEntityManager().find(AsegTraslados.class, id);
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
    public List<AsegTraslado> consultarTodos() throws Exception {
        List<AsegTraslado> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegTraslados p "
                    + "ORDER BY p.fechaHoraCrea DESC ";
            List<AsegTraslados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegTraslados per : list) {
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

    public static AsegTraslado castEntidadNegocio(AsegTraslados per) {
        AsegTraslado obj = new AsegTraslado();
        obj.setId(per.getId());
        obj.setCodigoEps(per.getCodigoEps());
        obj.setTipoDocumentoBdua(per.getTipoDocumentoBdua());
        obj.setNumeroDocumentoBdua(per.getNumeroDocumentoBdua());
        obj.setPrimerApellidoBdua(per.getPrimerApellidoBdua());
        obj.setSegundoApellidoBdua(per.getSegundoApellidoBdua());
        obj.setPrimerNombreBdua(per.getPrimerNombreBdua());
        obj.setSegundoNombreBdua(per.getSegundoNombreBdua());
        obj.setFechaNacimientoBdua(per.getFechaNacimientoBdua());
        obj.setGeneroBdua(per.getGeneroBdua());
        obj.setFechaReporte(per.getFechaReporte());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUbicacion(per.getUbicacionesId());
        obj.setMaeEpsOrigenId(per.getMaeEpsOrigenId());
        if (per.getAsegAfiliadosId()!= null) {
            obj.setAsegAfiliado(new AsegAfiliado(per.getAsegAfiliadosId().getId()));
        }
        return obj;
    }

    public static AsegTraslados castNegocioEntidad(AsegTraslado obj) {
        AsegTraslados per = new AsegTraslados();
        per.setId(obj.getId());
        per.setCodigoEps(obj.getCodigoEps());
        per.setTipoDocumentoBdua(obj.getTipoDocumentoBdua());
        per.setNumeroDocumentoBdua(obj.getNumeroDocumentoBdua());
        per.setPrimerApellidoBdua(obj.getPrimerApellidoBdua());
        per.setSegundoApellidoBdua(obj.getSegundoApellidoBdua());
        per.setPrimerNombreBdua(obj.getPrimerNombreBdua());
        per.setSegundoNombreBdua(obj.getSegundoNombreBdua());
        per.setFechaNacimientoBdua(obj.getFechaNacimientoBdua());
        per.setGeneroBdua(obj.getGeneroBdua());
        per.setFechaReporte(obj.getFechaReporte());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setUbicacionesId(obj.getUbicacion());
        per.setMaeEpsOrigenId(obj.getMaeEpsOrigenId());
        if (obj.getAsegAfiliado()!= null) {
            per.setAsegAfiliadosId(new AsegAfiliados(obj.getAsegAfiliado().getId()));
        }
        return per;
    }

    //funciones utliidad
    
    public String retornarConector(int i) {
        if (i == 0) {
            return "WHERE ";
        } else {
            return "AND ";
        }
    }

    @Override
    public AsegTraslado consultar(Integer tipodocumento, String numeroDocumento) throws java.lang.Exception {
        AsegTraslado afiliadoResult = new AsegTraslado();
        try {
            String strQuery = "FROM AsegTraslados p "
                    + "WHERE p.tipoDocumentoBdua = " + tipodocumento + " "
                    + "AND p.numeroDocumentoBdua = '" + numeroDocumento + "' ";
            AsegTraslados obj = (AsegTraslados) getEntityManager().createQuery(strQuery).getSingleResult();
            afiliadoResult = castEntidadNegocio(obj);
        } catch (NoResultException e) {
            afiliadoResult = new AsegTraslado();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return afiliadoResult;
    }

    @Override
    public AsegTraslado consultarPorAfiliado(int idAfiliado) throws java.lang.Exception {
        AsegTraslado afiliadoResult = new AsegTraslado();
        afiliadoResult.setId(0);
        try {
            String strQuery = "FROM AsegTraslados p "
                    + "WHERE p.asegAfiliadosId.id = " + idAfiliado + " "
                    + "ORDER BY p.id DESC ";
            
            List<AsegTraslados> list = getEntityManager().createQuery(strQuery).getResultList();
            if (list != null && !list.isEmpty()) {
                afiliadoResult = castEntidadNegocio(list.get(0));
            }
        } catch (NoResultException e) {
            afiliadoResult = new AsegTraslado();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return afiliadoResult;
    }

}
