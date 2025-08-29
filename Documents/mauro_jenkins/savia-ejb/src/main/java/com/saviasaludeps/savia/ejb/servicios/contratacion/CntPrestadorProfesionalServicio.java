/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesionalPrestador;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.CntProfesionalPrestadores;
import com.saviasaludeps.savia.ejb.entidades.CntProfesionales;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorProfesionalRemoto;
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
 * @author Jaime Andres Olarte
 */
@Stateless
@Local(CntPrestadorProfesionalLocal.class)
@Remote(CntPrestadorProfesionalRemoto.class)
public class CntPrestadorProfesionalServicio extends GenericoServicio implements CntPrestadorProfesionalLocal, CntPrestadorProfesionalRemoto {

    @Override
    public List<CntProfesionalPrestador> consultarPorProfesional(int id) throws Exception {
        List<CntProfesionalPrestador> listResult = new ArrayList<>();
        try {
            String strQuery = "FROM CntProfesionalPrestadores p WHERE p.cntProfesionalesId.id = :id "
                    + "ORDER BY p.id ";
            List<CntProfesionalPrestadores> list = getEntityManager().createQuery(strQuery)
                    .setParameter("id", id)
                    .getResultList();
            for (CntProfesionalPrestadores per : list) {
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
    public CntProfesionalPrestador consultarPorProfesionalYPrestador(int idProfesional, int idPrestador) throws Exception {
        CntProfesionalPrestador resultado = null;
        try {
            String strQuery = "FROM CntProfesionalPrestadores p WHERE p.cntProfesionalesId.id = :idProfesional AND p.cntPrestadoresId.id = :idPrestador "
                    + "ORDER BY p.id ";
            CntProfesionalPrestadores obj = (CntProfesionalPrestadores) getEntityManager().createQuery(strQuery)
                    .setParameter("idProfesional", idProfesional)
                    .setParameter("idPrestador", idPrestador)
                    .setMaxResults(1)
                    .getSingleResult();
            resultado = castEntidadNegocio(obj);
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return resultado;
    }

    @Override
    public int insertar(CntProfesionalPrestador obj) throws Exception {
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
    public void actualizar(CntProfesionalPrestador obj) throws Exception {
        try {
            String sql = "UPDATE CntProfesionalPrestadores SET activo = :activo, "
                    + "maEspecialidadId = :maEspecialidadId, "
                    + "maEspecialidadCodigo = :maEspecialidadCodigo, "
                    + "maEspecialidadValor = :maEspecialidadValor, "
                    + "cntPrestadoresId.id = :cntPrestadoresId, "
                    + "cntProfesionalesId.id = :cntProfesionalesId, "
                    + "usuarioCrea = :usuarioCrea, "
                    + "terminalCrea = :terminalCrea, "
                    + "fechaHoraCrea = :fechaHoraCrea, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("activo", obj.isActivo());
            query.setParameter("maEspecialidadId", obj.getMaEspecialidadId());
            query.setParameter("maEspecialidadCodigo", obj.getMaEspecialidadCodigo());
            query.setParameter("maEspecialidadValor", obj.getMaEspecialidadValor());
            query.setParameter("cntPrestadoresId", obj.getCntPrestador().getId());
            query.setParameter("cntProfesionalesId", obj.getCntProfesionalesId().getId());
            query.setParameter("usuarioCrea", obj.getUsuarioCrea());
            query.setParameter("terminalCrea", obj.getTerminalCrea());
            query.setParameter("fechaHoraCrea", obj.getFechaHoraCrea());
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

    @Override
    public CntProfesionalPrestador eliminar(int id) throws Exception {
        CntProfesionalPrestador obj = null;
        try {
            CntProfesionalPrestadores ent = getEntityManager().find(CntProfesionalPrestadores.class, id);
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

    public static CntProfesionalPrestadores castNegocioEntidad(CntProfesionalPrestador obj) {
        CntProfesionalPrestadores ent = new CntProfesionalPrestadores();
        ent.setId(obj.getId());
        if (obj.getCntPrestador() != null && obj.getCntPrestador().getId() != null) {
            ent.setCntPrestadoresId(new CntPrestadores(obj.getCntPrestador().getId()));
        }
        if (obj.getCntProfesionalesId() != null && obj.getCntProfesionalesId().getId() != null) {
            ent.setCntProfesionalesId(new CntProfesionales(obj.getCntProfesionalesId().getId()));
        }
        ent.setActivo(obj.isActivo());
        ent.setMaEspecialidadCodigo(obj.getMaEspecialidadCodigo());
        ent.setMaEspecialidadId(obj.getMaEspecialidadId());
        ent.setMaEspecialidadValor(obj.getMaEspecialidadValor());        
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        return ent;
    }

    public static CntProfesionalPrestador castEntidadNegocio(CntProfesionalPrestadores ent) {
        CntProfesionalPrestador obj = new CntProfesionalPrestador();
        obj.setActivo(ent.getActivo());
        if (ent.getCntPrestadoresId() != null) {
            CntPrestador cntPrestador = new CntPrestador();
            cntPrestador.setId(ent.getCntPrestadoresId().getId());
            cntPrestador.setNumeroDocumento(ent.getCntPrestadoresId().getNumeroDocumento());
            cntPrestador.setRazonSocial(ent.getCntPrestadoresId().getRazonSocial());
            cntPrestador.setMaeTipoDocumentoValor(ent.getCntPrestadoresId().getMaeTipoDocumentoValor());
            obj.setCntPrestador(cntPrestador);
        }
        if (ent.getCntProfesionalesId() != null) {
            CntProfesional cntProfesional = new CntProfesional(ent.getCntProfesionalesId().getId());
            cntProfesional.setDocumento(ent.getCntProfesionalesId().getDocumento());
            cntProfesional.setFechaHoraCrea(ent.getCntProfesionalesId().getFechaHoraCrea());
            cntProfesional.setFechaHoraModifica(ent.getCntProfesionalesId().getFechaHoraModifica());
            cntProfesional.setMaeTipoCodumentoId(ent.getCntProfesionalesId().getMaeTipoCodumentoId());
            cntProfesional.setMaeTipoDocumentoCodigo(ent.getCntProfesionalesId().getMaeTipoDocumentoCodigo());
            cntProfesional.setMaeTipoDocumentoValor(ent.getCntProfesionalesId().getMaeTipoDocumentoValor());
            cntProfesional.setPrimerApellido(ent.getCntProfesionalesId().getPrimerApellido());
            cntProfesional.setPrimerNombre(ent.getCntProfesionalesId().getPrimerNombre());
            cntProfesional.setSegundoApellido(ent.getCntProfesionalesId().getSegundoApellido());
            cntProfesional.setSegundoNombre(ent.getCntProfesionalesId().getSegundoNombre());
            if (ent.getCntProfesionalesId().getRegistroMedico().length()>17) {
                System.out.println("Prestador mayor a 17 digitos");
            }
            cntProfesional.setRegistroMedico(ent.getCntProfesionalesId().getRegistroMedico());
            cntProfesional.setTerminalCrea(ent.getCntProfesionalesId().getTerminalCrea());
            cntProfesional.setTerminalModifica(ent.getCntProfesionalesId().getTerminalModifica());
            cntProfesional.setUsuarioCrea(ent.getCntProfesionalesId().getUsuarioCrea());
            cntProfesional.setUsuarioModifica(ent.getCntProfesionalesId().getUsuarioModifica());
            obj.setCntProfesionalesId(cntProfesional);
        }
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setId(ent.getId());
        obj.setMaEspecialidadCodigo(ent.getMaEspecialidadCodigo());
        obj.setMaEspecialidadId(ent.getMaEspecialidadId());
        obj.setMaEspecialidadValor(ent.getMaEspecialidadValor());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        return obj;
    }

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM CntProfesionalPrestadores p ";
            if (paramConsulta.getEmpresaId() == null) {
                strQuery += "WHERE p.id > 0 ";
            } else {
                strQuery += "INNER JOIN p.cntPrestadoresId cp "
                        + "INNER JOIN GnEmpresas ge on ge.cntPrestadoresId.id = cp.id "
                        + "WHERE ge.cntPrestadoresId.id = '" + paramConsulta.getEmpresaId() + "' ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntProfesionalesId.registroMedico":
                            strQuery += "AND p.cntProfesionalesId.registroMedico = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntProfesionalesId.maeTipoCodumentoId":
                            strQuery += "AND p.cntProfesionalesId.maeTipoCodumentoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntProfesionalesId.documento":
                            strQuery += "AND p.cntProfesionalesId.documento = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntProfesionalesId.nombreCompleto":
                            strQuery += "AND (p.cntProfesionalesId.primerNombre LIKE '" + (String) e.getValue() + "%' "
                                    + "OR p.cntProfesionalesId.segundoNombre LIKE '" + (String) e.getValue() + "%' "
                                    + "OR p.cntProfesionalesId.primerApellido LIKE '" + e.getValue() + "%' "
                                    + "OR p.cntProfesionalesId.segundoApellido LIKE '" + e.getValue() + "%') ";
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
    public List<CntProfesionalPrestador> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CntProfesionalPrestador> listResult = new ArrayList<>();
        try {
            String strQuery = "SELECT p FROM CntProfesionalPrestadores p ";
            if (paramConsulta.getEmpresaId() == null) {
                strQuery += "WHERE p.id > 0 ";
            } else {
                strQuery += "INNER JOIN p.cntPrestadoresId cp "
                        + "INNER JOIN GnEmpresas ge on ge.cntPrestadoresId.id = cp.id "
                        + "WHERE ge.cntPrestadoresId.id = '" + paramConsulta.getEmpresaId() + "' ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "cntProfesionalesId.registroMedico":
                            strQuery += "AND p.cntProfesionalesId.registroMedico = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntProfesionalesId.maeTipoCodumentoId":
                            strQuery += "AND p.cntProfesionalesId.maeTipoCodumentoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntProfesionalesId.documento":
                            strQuery += "AND p.cntProfesionalesId.documento = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntProfesionalesId.nombreCompleto":
                            strQuery += "AND (p.cntProfesionalesId.primerNombre LIKE '" + (String) e.getValue() + "%' "
                                    + "OR p.cntProfesionalesId.segundoNombre LIKE '" + (String) e.getValue() + "%' "
                                    + "OR p.cntProfesionalesId.primerApellido LIKE '" + e.getValue() + "%' "
                                    + "OR p.cntProfesionalesId.segundoApellido LIKE '" + e.getValue() + "%') ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                if (paramConsulta.getOrden().equals("cntProfesionalesId.nombreCompleto")) {
                    strQuery += "p.cntProfesionalesId.primerNombre "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else if (paramConsulta.getOrden().equals("cntPrestador.razonSocial")) {
                    strQuery += "p.cntPrestadoresId.razonSocial "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else {
                    strQuery += "p." + paramConsulta.getOrden() + " "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                }
            } else {
                strQuery += "p.cntProfesionalesId.id DESC, p.id DESC ";
            }
            List<CntProfesionalPrestadores> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntProfesionalPrestadores per : list) {
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
    public List<CntProfesionalPrestador> consultarListaPorProfesionalYPrestador(int idProfesional, int idPrestador) throws Exception {
        List<CntProfesionalPrestador> listaResultado = new ArrayList<>();
        try {
            String strQuery = "FROM CntProfesionalPrestadores p "
                    + "WHERE p.cntProfesionalesId.id = :idProfesional "
                    + "AND p.cntPrestadoresId.id = :idPrestador "
                    + "ORDER BY p.id ";
            List<CntProfesionalPrestadores> lista = getEntityManager().createQuery(strQuery)
                    .setParameter("idProfesional", idProfesional)
                    .setParameter("idPrestador", idPrestador)
                    .getResultList();

            for (CntProfesionalPrestadores item : lista) {
                listaResultado.add(castEntidadNegocio(item));
            }
        } catch (NoResultException e) {
            listaResultado = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultado;
    }

    @Override
    public CntProfesionalPrestador consultar(int id) throws Exception {
        CntProfesionalPrestador objResultado = new CntProfesionalPrestador();

        try {
            objResultado = castEntidadNegocio((CntProfesionalPrestadores) getEntityManager().find(CntProfesionalPrestadores.class, id));
        } catch (NoResultException e) {
            objResultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }

        return objResultado;
    }
}
