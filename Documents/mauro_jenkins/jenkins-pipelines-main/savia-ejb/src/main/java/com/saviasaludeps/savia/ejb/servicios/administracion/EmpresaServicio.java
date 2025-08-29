/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.negocio.administracion.EmpresaRemoto;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(EmpresaRemoto.class)
public class EmpresaServicio extends GenericoServicio implements EmpresaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(e) FROM GnEmpresas e "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nit":
                            strQuery += "AND e.nit LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "razonSocial":
                            strQuery += "AND e.razonSocial LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "nombreComercial":
                            strQuery += "AND e.nombreComercial LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "ciudad.id":
                            Ubicacion ubi = (Ubicacion) e.getValue();
                            strQuery += "AND e.ubicacionesId.id = " + ubi.getId() + " ";
                            break;
                        case "activa":
                            strQuery += "AND e.activa = " + (String) e.getValue() + " ";
                            break;
                        case "administradora":
                            strQuery += "AND e.administradora = " + (String) e.getValue() + " ";
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
    public List<Empresa> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<Empresa> listResult = new ArrayList();
        try {
            String strQuery = "FROM GnEmpresas e "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nit":
                            strQuery += "AND e.nit LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "razonSocial":
                            strQuery += "AND e.razonSocial LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "nombreComercial":
                            strQuery += "AND e.nombreComercial LIKE '" + (String) e.getValue() + "%' ";
                            break;
                        case "ciudad.id":
                            Ubicacion ubi = (Ubicacion) e.getValue();
                            strQuery += "AND e.ubicacionesId.id = " + ubi.getId() + " ";
                            break;
                        case "activa":
                            strQuery += "AND e.activa = " + (String) e.getValue() + " ";
                            break;
                        case "administradora":
                            strQuery += "AND e.administradora = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "e." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "e.nit ASC ";
            }
            List<GnEmpresas> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GnEmpresas per : list) {
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
    public Empresa consultar(int id) throws Exception {
        Empresa objRes = null;
        try {
            objRes = castEntidadNegocio((GnEmpresas) getEntityManager().find(GnEmpresas.class, id));
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
    public int insertar(Empresa obj) throws Exception {
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
    public void actualizar(Empresa obj) throws Exception {
        try {
//            getEntityManager().merge(castNegocioEntidad(obj));  
            GnEmpresas per = castNegocioEntidad(obj);
            String hql = "UPDATE GnEmpresas SET "
                    + "nit = :nit, "
                    + "razonSocial = :razonSocial, "
                    + "nombreComercial = :nombreComercial, "
                    + "descripcion = :descripcion, "
                    + "administradora = :administradora, "
                    + "activa = :activa, "
                    + "sesionesUsuario = :sesionesUsuario, "
                    + "codigoHabilitacion = :codigoHabilitacion, ";
            if (per.getGnUbicacionesId() != null) {
                hql += "gnUbicacionesId.id = " + per.getGnUbicacionesId().getId() + ", ";
            }
            if (per.getCntPrestadoresId() != null) {
                hql += "cntPrestadoresId.id = " + per.getCntPrestadoresId().getId() + ", ";
            }
            hql += "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id ";
            Session session = getEntityManager().unwrap(Session.class);
            org.hibernate.Query query = session.createQuery(hql);
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
    public Empresa eliminar(int id) throws Exception {
        Empresa obj = null;
        try {
            GnEmpresas ent = getEntityManager().find(GnEmpresas.class, id);
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
    public List<Empresa> consultarActivas() throws Exception {
        List<Empresa> listResult = new ArrayList();
        String strQuery = "FROM GnEmpresas "
                + "WHERE activa = 1 "
                + "ORDER BY nombreComercial";
        try {
            Query query = getEntityManager().createQuery(strQuery);
            List<GnEmpresas> list = query.getResultList();
            for (GnEmpresas obj : list) {
                listResult.add(castEntidadNegocioCorto(obj));
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

    public static Empresa castEntidadNegocio(GnEmpresas per) {
        Empresa obj = new Empresa();
        obj.setId(per.getId());
        obj.setNit(per.getNit());
        obj.setRazonSocial(per.getRazonSocial());
        obj.setNombreComercial(per.getNombreComercial());
        obj.setActiva(per.getActiva());
        obj.setDescripcion(per.getDescripcion());
        obj.setAdministradora(per.getAdministradora());
        obj.setCiudad(new Ubicacion(per.getGnUbicacionesId().getId()));
        obj.setReceptorUsuario(per.getReceptorUsuario());
        obj.setReceptorContrasena(per.getReceptorContrasena());
        obj.setCodigoHabilitacion(per.getCodigoHabilitacion());
        obj.setSesionesUsuario(per.getSesionesUsuario());
        //Prestador
        if (per.getCntPrestadoresId() != null) {
            obj.setCntPrestador(castEntidadNegocioPrestador(per.getCntPrestadoresId()));
            obj.setCodigoHabilitacion(per.getCntPrestadoresId().getCodigoMinSalud());
        }
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static GnEmpresas castNegocioEntidad(Empresa obj) {
        GnEmpresas per = new GnEmpresas();
        per.setId(obj.getId());
        per.setNit(obj.getNit());
        per.setRazonSocial(obj.getRazonSocial());
        per.setNombreComercial(obj.getNombreComercial());
        per.setDescripcion(obj.getDescripcion());
        per.setActiva(obj.isActiva());
        per.setAdministradora(obj.isAdministradora());
        per.setReceptorUsuario(obj.getReceptorUsuario());
        per.setReceptorContrasena(obj.getReceptorContrasena());
        per.setSesionesUsuario(obj.getSesionesUsuario());
        //Prestador
        if (obj.getCntPrestador() != null) {
            per.setCntPrestadoresId(castNegocioEntidadPrestador(obj.getCntPrestador()));
            per.setCodigoHabilitacion(obj.getCntPrestador().getCodigoMinSalud());
        }
        //Ubicación
        per.setGnUbicacionesId(new GnUbicaciones(obj.getCiudad().getId()));
        //Auditoría
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }

    private static CntPrestadores castNegocioEntidadPrestador(CntPrestador cntPrestador) {
        CntPrestadores ent = new CntPrestadores();
        ent.setId(cntPrestador.getId());
        return ent;
    }

    private static CntPrestador castEntidadNegocioPrestador(CntPrestadores cntPrestadoresId) {
        CntPrestador neg = new CntPrestador();
        neg.setId(cntPrestadoresId.getId());
        neg.setCodigoMinSalud(cntPrestadoresId.getCodigoMinSalud());
        neg.setRazonSocial(cntPrestadoresId.getRazonSocial());
        neg.setNumeroDocumento(cntPrestadoresId.getNumeroDocumento());
        neg.setMaeTipoDocumentoValor(cntPrestadoresId.getMaeTipoDocumentoValor());
        neg.setMaeTipoDocumentoId(cntPrestadoresId.getMaeTipoDocumentoId());
        neg.setMaeTipoDocumentoCodigo(cntPrestadoresId.getMaeTipoDocumentoCodigo());
        neg.setMaeClasePrestador(cntPrestadoresId.getMaeClasePrestadorId());
        neg.setMaeClasePrestadorValor(cntPrestadoresId.getMaeClasePrestadorValor());
        neg.setMaeClasePrestadorCodigo(cntPrestadoresId.getMaeClasePrestadorCodigo());
        neg.setNaturalezaJuridica(cntPrestadoresId.getNaturalezaJuridica());
        neg.setDigitoVerificacion(cntPrestadoresId.getDigitoVerificacion());
        return neg;
    }

    @Override
    public Empresa consultarPorPrestador(int idPrestador) throws Exception {
        Empresa objRes = null;
        GnEmpresas result = null;
        try {
            String strQuery = "FROM GnEmpresas g "
                    + "WHERE g.activa = 1 AND g.cntPrestadoresId.id = " + idPrestador;

            Query query = getEntityManager().createQuery(strQuery);
            result = (GnEmpresas) query.getSingleResult();
            objRes = castEntidadNegocio(result);

        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private Empresa castEntidadNegocioCorto(GnEmpresas per) {
        Empresa obj = new Empresa();
        obj.setId(per.getId());
        obj.setNit(per.getNit());
        obj.setRazonSocial(per.getRazonSocial());
        obj.setNombreComercial(per.getNombreComercial());
        obj.setActiva(per.getActiva());
        obj.setDescripcion(per.getDescripcion());
        obj.setAdministradora(per.getAdministradora());
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

}
