/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.judicial;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.judicial.GjTercero;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.GjTerceros;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.judicial.GjTerceroRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author bsteven_gomez
 */
@Stateless
@Remote(GjTerceroRemoto.class)
public class GjTerceroServicio extends GenericoServicio implements GjTerceroRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(u.id) FROM GjTerceros u  "
                    + "WHERE u.id > 0";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeTipoDocumentoId":
                            strQuery += "AND u.maeTipoDocumentoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "documento":
                            strQuery += "AND u.documento LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "nombres":
                            strQuery += "AND u.nombres LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "apellidos":
                            strQuery += "AND u.apellidos LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "telefono":
                            strQuery += "AND u.telefono LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "razonSocial":
                            strQuery += "AND u.razonSocial LIKE '%" + (String) e.getValue() + "%'";
                            break;
                    }
                }
            }
//strQuery += "AND t.borrado != 1 ";
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //                    .setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public int consultarCantidadListaBuscador(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p.id) FROM GjTerceros p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {                                                
                        case "maeTipoDocumentoId":
                            strQuery += "AND p.maeTipoDocumentoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "documento":
                            strQuery += "AND p.documento LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "nombres":
                            strQuery += "AND p.nombres LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "apellidos":
                            strQuery += "AND p.apellidos LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "telefono":
                            strQuery += "AND p.telefono LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "razonSocial":
                            strQuery += "AND p.razonSocial LIKE '%" + (String) e.getValue() + "%'";
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
    
     @Override
    public List<GjTercero> consultarListaBuscador(ParamConsulta paramConsulta) throws Exception {
        List<GjTercero> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM GjTerceros p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                       case "maeTipoDocumentoId":
                            strQuery += "AND p.maeTipoDocumentoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "documento":
                            strQuery += "AND p.documento LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "nombres":
                            strQuery += "AND p.nombres LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "apellidos":
                            strQuery += "AND p.apellidos LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "telefono":
                            strQuery += "AND p.telefono LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "razonSocial":
                            strQuery += "AND p.razonSocial LIKE '%" + (String) e.getValue() + "%'";
                            break;                  
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
            List<GjTerceros> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (GjTerceros per : list) {
                listResult.add(castEntidadNegocioBuscador(per));
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
    
    public static GjTercero castEntidadNegocioBuscador(GjTerceros per) {
        GjTercero obj = new GjTercero();
        obj.setId(per.getId());
        obj.setTipo(per.getTipo());
        obj.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setDocumento(per.getDocumento());
        obj.setNombres(per.getNombres());
        obj.setApellidos(per.getApellidos());
        obj.setRazonSocial(per.getRazonSocial());
        obj.setTelefono(per.getTelefono());
        obj.setDireccion(per.getDireccion());
        obj.setCorreoElectronico(per.getCorreoElectronico());        

        return obj;
    }

    @Override
    public List<GjTercero> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GjTercero> listResult = new ArrayList();
        try {
            String strQuery = "SELECT u FROM GjTerceros u "
                    + "WHERE u.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeTipoDocumentoId":
                            strQuery += "AND u.maeTipoDocumentoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "documento":
                            strQuery += "AND u.documento LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "nombres":
                            strQuery += "AND u.nombres LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "apellidos":
                            strQuery += "AND u.apellidos LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "telefono":
                            strQuery += "AND u.telefono LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "razonSocial":
                            strQuery += "AND u.razonSocial LIKE '%" + (String) e.getValue() + "%'";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "u." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "u.id DESC";
            }
            List<GjTerceros> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GjTerceros per : list) {
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
    public GjTercero consultar(int id) throws Exception {
        GjTercero objRes = null;
        try {
            objRes = castEntidadNegocio((GjTerceros) getEntityManager().find(GjTerceros.class, id));
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
    public Integer insertar(GjTercero obj) throws Exception {
        Integer id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (Exception e) {
            Exception(INSERTAR, e, "");
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

//    @Override
//    public int insertar(GjTercero obj) throws Exception {
//        int _id = 0;
//        try {
//            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
//            obj.setId(_id);
//        } catch (NoResultException e) {
//            _id = 0;
//        } catch (Exception e) {
//            Exception(INSERTAR, e);
//        } finally {
//            cerrarEntityManager();
//        }
//        return _id;
//    }
    @Override
    public void actualizar(GjTercero obj) throws Exception {
        try {
            String hql = "UPDATE GjTerceros SET "
                    + "tipo = :tipo, "
                    + "maeTipoDocumentoId = :maeTipoDocumentoId, "
                    + "maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo, "
                    + "maeTipoDocumentoValor = :maeTipoDocumentoValor, "
                    + "documento = :documento, "
                    + "nombres = :nombres, "
                    + "apellidos = :apellidos, "
                    + "razonSocial = :razonSocial, "
                    + "telefono = :telefono, "
                    + "gnUbicacionesId = :gnUbicacionesId, "
                    + "direccion = :direccion, "
                    + "correoElectronico = :correoElectronico, ";
//            if (obj.getGnUbicacionesId() != null
//                    && obj.getGnUbicacionesId().getId() > 0) {
//                hql += "gnUbicacionesId = :gnUbicacionesId, ";
//            }

//            if (obj.getAsegAfiliado() != null
//                    && obj.getAsegAfiliadoId() != null) {
//                hql += "asegAfiliadosId.id = :asegAfiliadosId, ";
//            }
//            if (obj.getCntPrestador()!= null
//                    && obj.getAsegAfiliadoId() != null) {
//                hql += "asegAfiliadosId.id = :asegAfiliadosId, ";
//            }
            hql
                    += "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica ";
            hql += "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("tipo", obj.getTipo());
            query.setParameter("maeTipoDocumentoId", obj.getMaeTipoDocumentoId());
            query.setParameter("maeTipoDocumentoCodigo", obj.getMaeTipoDocumentoCodigo());
            query.setParameter("maeTipoDocumentoValor", obj.getMaeTipoDocumentoValor());
            query.setParameter("documento", obj.getDocumento());
            query.setParameter("nombres", obj.getNombres());
            query.setParameter("apellidos", obj.getApellidos());
            query.setParameter("razonSocial", obj.getRazonSocial());
            query.setParameter("telefono", obj.getTelefono());
            query.setParameter("gnUbicacionesId", obj.getGnUbicacionesId().getId());
            query.setParameter("direccion", obj.getDireccion());
//            if (obj.getGnUbicacionesId() != null && obj.getGnUbicacionesId().getId() > 0) {
//                query.setParameter("gnUbicacionesId", obj.getGnUbicacionesId().getId());
//            }           
            query.setParameter("correoElectronico", obj.getCorreoElectronico());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public GjTercero eliminar(int id) throws Exception {
        GjTercero obj = null;
        try {
            GjTerceros ent = getEntityManager().find(GjTerceros.class,
                    id);
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

//    public static GjTercero CastEntidadNegocionSimple(GjTerceros per) {
//        GjTercero obj = new GjTercero();
//
//        obj.setId(per.getId());
//        obj.setTipo(per.getTipo());
//        if (per.getAsegAfiliadosId() != null) {
//            obj.setAsegAfiliado(new AsegAfiliado(
//                    per.getAsegAfiliadosId().getId(),
//                    per.getAsegAfiliadosId().getIdAfiliado(),
//                    per.getAsegAfiliadosId().getPrimerNombre(),
//                    per.getAsegAfiliadosId().getSegundoNombre(),
//                    per.getAsegAfiliadosId().getPrimerApellido(),
//                    per.getAsegAfiliadosId().getSegundoApellido()
//            ));
//        }
//        if (per.getCntPrestadoresId() != null) {
//            obj.setCntPrestador(new CntPrestador(
//                    per.getCntPrestadoresId().getId(),
//                    per.getCntPrestadoresId().getRazonSocial()
//            ));
//        }
//        obj.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
//        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
//        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
//        obj.setDocumento(per.getDocumento());
//        obj.setNombres(per.getNombres());
//        obj.setApellidos(per.getApellidos());
//        obj.setRazonSocial(per.getRazonSocial());
//        obj.setTelefono(per.getTelefono());
//        obj.setGnUbicacionesId(per.getGnUbicacionesId());
//        obj.setDireccion(per.getDireccion());
//        obj.setCorreoElectronico(per.getCorreoElectronico());
//        return obj;
//    }
    @Override
    public GjTercero consultarPersona(GjTercero obj) throws Exception {
        GjTercero ausPersona = new GjTercero();
        try {
            String strQuery = "FROM GjTerceros p "
                    + "WHERE";
            strQuery += " p.maeTipoDocumentoCodigo = '" + obj.getMaeTipoDocumentoCodigo() + "' ";
            strQuery += "AND p.numeroDocumento = " + obj.getDocumento() + " ";

            List<GjTerceros> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GjTerceros per : list) {
                ausPersona = castEntidadNegocio(per);
            }

        } catch (NoResultException e) {
            ausPersona = new GjTercero();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return ausPersona;
    }

    @Override
    public GjTercero consultarTerCero(GjTercero obj) throws Exception {
        GjTercero ausPersona = new GjTercero();
        try {
            String strQuery = "FROM GjTerceros p "
                    + "WHERE";
            strQuery += " p.documento = " + obj.getDocumento() + " ";

            List<GjTerceros> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GjTerceros per : list) {
                ausPersona = castEntidadNegocio(per);
            }

        } catch (NoResultException e) {
            ausPersona = new GjTercero();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return ausPersona;
    }

    public static GjTercero castEntidadNegocio(GjTerceros per) {
        GjTercero obj = new GjTercero();

        obj.setId(per.getId());
        obj.setTipo(per.getTipo());
        if (per.getAsegAfiliadosId() != null) {
            obj.setAsegAfiliado(new AsegAfiliado(
                    per.getAsegAfiliadosId().getId(),
                    per.getAsegAfiliadosId().getIdAfiliado(),
                    per.getAsegAfiliadosId().getPrimerNombre(),
                    per.getAsegAfiliadosId().getSegundoNombre(),
                    per.getAsegAfiliadosId().getPrimerApellido(),
                    per.getAsegAfiliadosId().getSegundoApellido()
            ));
        }
        if (per.getCntPrestadoresId() != null) {
            obj.setCntPrestador(new CntPrestador(
                    per.getCntPrestadoresId().getId(),
                    per.getCntPrestadoresId().getRazonSocial()
            ));
        }
        obj.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setDocumento(per.getDocumento());
        obj.setNombres(per.getNombres());
        obj.setApellidos(per.getApellidos());
        obj.setRazonSocial(per.getRazonSocial());
        obj.setTelefono(per.getTelefono());

        if (per.getGnUbicacionesId() > 0) {
            obj.setGnUbicacionesId(new Ubicacion(per.getGnUbicacionesId()));

        }
        obj.setDireccion(per.getDireccion());
        obj.setCorreoElectronico(per.getCorreoElectronico());

        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static GjTerceros castNegocioEntidad(GjTercero obj) {
        GjTerceros per = new GjTerceros();
        per.setId(obj.getId());
        per.setTipo(obj.getTipo());
        if (obj.getAsegAfiliado() != null) {
            if (obj.getAsegAfiliadoId() != null) {
                per.setAsegAfiliadosId(new AsegAfiliados(obj.getAsegAfiliadoId()));
            }
        }
        if (obj.getCntPrestador() != null) {
            if (obj.getCntPrestadorId() != null) {
                per.setCntPrestadoresId(new CntPrestadores(obj.getCntPrestadorId()));
            }
        }
        per.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
        per.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());//cc
        per.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());//ok
        per.setDocumento(obj.getDocumento());//ok
        per.setNombres(obj.getNombres());
        per.setApellidos(obj.getApellidos());
        per.setRazonSocial(obj.getRazonSocial());
        per.setTelefono(obj.getTelefono());
        per.setGnUbicacionesId(obj.getGnUbicacionesId().getId());
        per.setDireccion(obj.getDireccion());
        per.setCorreoElectronico(obj.getCorreoElectronico());
        //Auditoría
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }

}
