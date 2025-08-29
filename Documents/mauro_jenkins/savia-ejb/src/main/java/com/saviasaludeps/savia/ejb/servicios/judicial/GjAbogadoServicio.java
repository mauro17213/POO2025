/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.judicial;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.judicial.GjAbogado;
import com.saviasaludeps.savia.ejb.entidades.GjAbogados;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.servicios.administracion.UsuarioServicio;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.judicial.GjAbogadoRemoto;

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
@Remote(GjAbogadoRemoto.class)
public class GjAbogadoServicio extends GenericoServicio implements GjAbogadoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(u.id) FROM GjAbogados u  "
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
                        case "tarjetaProfecional":
                            strQuery += "AND u.tarjetaProfecional LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "nombre":
                            strQuery += "AND u.nombre LIKE '%" + (String) e.getValue() + "%'";
                            break;
                    }
                }
            }

            cant = (int) (long) getEntityManager().createQuery(strQuery)
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
    public List<GjAbogado> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GjAbogado> listResult = new ArrayList();
        try {
            String strQuery = "SELECT u FROM GjAbogados u "
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
                        case "tarjetaProfecional":
                            strQuery += "AND u.tarjetaProfecional LIKE '%" + (String) e.getValue() + "%'";
                            break;
                            case "nombre":
                            strQuery += "AND u.nombre LIKE '%" + (String) e.getValue() + "%'";
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
            List<GjAbogados> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GjAbogados per : list) {
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
    public GjAbogado consultar(int id) throws Exception {
        GjAbogado objRes = null;
        try {
            objRes = castEntidadNegocio((GjAbogados) getEntityManager().find(GjAbogados.class, id));
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
    public Integer insertar(GjAbogado obj) throws Exception {
        Integer id = null;
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

    @Override
    public void actualizar(GjAbogado obj) throws Exception {
        try {
            String hql = "UPDATE GjAbogados SET "
                    + "gnUsuariosId.id = :gnUsuariosId, "
                    + "maeTipoDocumentoId = :maeTipoDocumentoId, "
                    + "maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo, "
                    + "maeTipoDocumentoValor = :maeTipoDocumentoValor, "
                    + "documento = :documento, "
                    + "tarjetaProfecional = :tarjetaProfecional, "
                    + "nombre = :nombre, "
                    + "tipo = :tipo, ";
//            
            hql
                    += "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica ";
            hql += "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("gnUsuariosId", obj.getGnUsuario().getId());
            query.setParameter("maeTipoDocumentoId", obj.getMaeTipoDocumentoId());
            query.setParameter("maeTipoDocumentoCodigo", obj.getMaeTipoDocumentoCodigo());
            query.setParameter("maeTipoDocumentoValor", obj.getMaeTipoDocumentoValor());
            query.setParameter("documento", obj.getDocumento());
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("tarjetaProfecional", obj.getTarjetaProfecional());
            query.setParameter("tipo", obj.getTipo());

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
    public GjAbogado eliminar(int id) throws Exception {
        GjAbogado obj = null;
        try {
            GjAbogados ent = getEntityManager().find(GjAbogados.class,
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

    @Override
    public GjAbogado consultarPersona(GjAbogado obj) throws Exception {
        GjAbogado ausPersona = new GjAbogado();
        try {
            String strQuery = "FROM GjAbogados p "
                    + "WHERE";
            strQuery += " p.maeTipoDocumentoCodigo = '" + obj.getMaeTipoDocumentoCodigo() + "' ";
            strQuery += "AND p.documento = " + obj.getDocumento() + " ";

            List<GjAbogados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GjAbogados per : list) {
                ausPersona = castEntidadNegocio(per);
            }

        } catch (NoResultException e) {
            ausPersona = new GjAbogado();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return ausPersona;
    }

    @Override
    public List<Usuario> consultarTodosUsuarios() throws Exception {
        List<Usuario> listResult = new ArrayList();
        String strQuery = "FROM GnUsuarios"
                + " ORDER BY nombre, usuario";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnUsuarios> list = query.getResultList();
            for (GnUsuarios obj : list) {
                listResult.add(CastEntidadNegocionSimple(obj));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception("Consulta de registros", e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
    @Override
    public GjAbogado consultarAbogado(GjAbogado obj) throws Exception {
        GjAbogado ausPersona = new GjAbogado();
        try {
            String strQuery = "FROM GjAbogados p "
                    + "WHERE";
            strQuery += " p.documento = " + obj.getDocumento() + " ";

            List<GjAbogados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GjAbogados per : list) {
                ausPersona = castEntidadNegocio(per);
            }

        } catch (NoResultException e) {
            ausPersona = new GjAbogado();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return ausPersona;
    }
    
    @Override
    public int consultarCantidadListaBuscador(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p.id) FROM GjAbogados p "
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
                        case "tarjetaProfecional":
                            strQuery += "AND p.tarjetaProfecional LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "nombre":
                            strQuery += "AND p.nombre LIKE '%" + (String) e.getValue() + "%'";
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
    public List<GjAbogado> consultarListaBuscador(ParamConsulta paramConsulta) throws Exception {
        List<GjAbogado> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM GjAbogados p "
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
                        case "tarjetaProfecional":
                            strQuery += "AND p.tarjetaProfecional LIKE '%" + (String) e.getValue() + "%'";
                            break;
                        case "nombre":
                            strQuery += "AND p.nombre LIKE '%" + (String) e.getValue() + "%'";
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
            List<GjAbogados> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (GjAbogados per : list) {
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
    
    public static GjAbogado castEntidadNegocioBuscador(GjAbogados per) {
        GjAbogado obj = new GjAbogado();
        obj.setId(per.getId());
        obj.setTipo(per.getTipo());
        obj.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setDocumento(per.getDocumento());
        obj.setNombre(per.getNombre());
        obj.setTarjetaProfecional(per.getTarjetaProfecional());            

        return obj;
    }

    public static Usuario CastEntidadNegocionSimple(GnUsuarios per) {
        Usuario obj = new Usuario();
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

    public static GjAbogado castEntidadNegocio(GjAbogados per) {
        GjAbogado obj = new GjAbogado();

        obj.setId(per.getId());
        obj.setTipo(per.getTipo());
        if (per.getGnUsuariosId() != null) {
            obj.setGnUsuario(new Usuario(per.getGnUsuariosId().getId()));
        }
        obj.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setDocumento(per.getDocumento());
        obj.setTarjetaProfecional(per.getTarjetaProfecional());
        obj.setNombre(per.getNombre());
        obj.setTipo(per.getTipo());

        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static GjAbogados castNegocioEntidad(GjAbogado obj) {
        GjAbogados per = new GjAbogados();

        per.setId(obj.getId());
        per.setTipo(obj.getTipo());
        if (obj.getGnUsuario() != null) {
            per.setGnUsuariosId(new GnUsuarios(obj.getGnUsuario().getId()));
        }
        per.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());

        per.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
        per.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
        per.setDocumento(obj.getDocumento());
        per.setTarjetaProfecional(obj.getTarjetaProfecional());
        per.setNombre(obj.getNombre());

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
