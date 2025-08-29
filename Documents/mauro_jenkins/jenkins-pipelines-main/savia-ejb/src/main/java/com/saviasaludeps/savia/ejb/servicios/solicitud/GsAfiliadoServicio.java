/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.solicitud;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.solicitud.GsAfiliado;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.entidades.GsAfiliados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.solicitud.GsAfiliadoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jramirez
 */
@Stateless
@Remote(GsAfiliadoRemoto.class)
public class GsAfiliadoServicio extends GenericoServicio implements GsAfiliadoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GsAfiliados c "
                    + " WHERE c.empresasId.id = :empresaid  ";
            boolean consultaConFechas = false;
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                strQuery += " AND c.fechaHoraCrea BETWEEN :fh_inicio AND :fh_fin ";
                consultaConFechas = true;
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            if (paramConsulta.getParametroConsulta3() != null && paramConsulta.getFiltros().isEmpty()) {
                strQuery += "AND c.responsableUsuariosId.id = " + (int) paramConsulta.getParametroConsulta3() + " ";
            }
            if (consultaConFechas) {
//                Calendar calIni = GenericoServicio.addHourToParam1(paramConsulta.getParametroConsulta1());
//                Calendar calFin = GenericoServicio.addHourToParam2(paramConsulta.getParametroConsulta2());
                cant = (int) (long) getEntityManager().createQuery(strQuery)
                        //                        .setParameter("fh_inicio", (calIni), TemporalType.TIMESTAMP)
                        //                        .setParameter("fh_fin", (calFin), TemporalType.TIMESTAMP)
                        .getSingleResult();
            } else {
                cant = (int) (long) getEntityManager().createQuery(strQuery)
                        .setParameter("empresaid", paramConsulta.getEmpresaId())
                        .getSingleResult();
            }

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
    public List<GsAfiliado> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GsAfiliado> listResult = new ArrayList();
        try {
            String strQuery = "FROM GsAfiliados c "
                    + " WHERE c.empresasId.id = :empresaid ";
            boolean consultaConFechas = false;
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                strQuery += " AND c.fechaHoraCrea BETWEEN :fh_inicio AND :fh_fin ";
                consultaConFechas = true;
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            if (paramConsulta.getParametroConsulta3() != null && paramConsulta.getFiltros().isEmpty()) {
                strQuery += "AND c.responsableUsuariosId.id = " + (int) paramConsulta.getParametroConsulta3() + " ";
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "c." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "c.fechaHoraCrea DESC";
            }
            List<GsAfiliados> list;
            if (consultaConFechas) {
//                Calendar calIni = GenericoServicio.addHourToParam1(paramConsulta.getParametroConsulta1());
//                Calendar calFin = GenericoServicio.addHourToParam2(paramConsulta.getParametroConsulta2());
                list = getEntityManager().createQuery(strQuery)
                        .setFirstResult(paramConsulta.getPrimerRegistro())
                        .setMaxResults(paramConsulta.getRegistrosPagina())
                        //                        .setParameter("fh_inicio", (calIni), TemporalType.TIMESTAMP)
                        //                        .setParameter("fh_fin", (calFin), TemporalType.TIMESTAMP)
                        .getResultList();
            } else {
                list = getEntityManager().createQuery(strQuery)
                        .setFirstResult(paramConsulta.getPrimerRegistro())
                        .setMaxResults(paramConsulta.getRegistrosPagina())
                        .setParameter("empresaid", paramConsulta.getEmpresaId())
                        .getResultList();
            }
            for (GsAfiliados casoNegocion : list) {
                listResult.add(castEntidadNegocio(casoNegocion));
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
    public List<GsAfiliado> consultarPorFiltros(ParamConsulta paramConsulta) throws Exception {
        List<GsAfiliado> listResult = new ArrayList();
        try {
            String strQuery = "FROM GsAfiliados c "
                    + " WHERE 1 = 1 ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipoDocumento":
                            strQuery += " AND c.personasId.maeTipoDocumento= " + e.getValue() + " ";
                            break;
                        case "documento":
                            strQuery += " AND c.personasId.documento = " + e.getValue() + " ";
                            break;
                        case "idGsSolicitud":
                            strQuery += " AND c.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }

            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "c." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "c.fechaHoraCrea DESC";
            }
            List<GsAfiliados> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();

            for (GsAfiliados casoNegocion : list) {
                listResult.add(castEntidadNegocio(casoNegocion));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public int insertar(GsAfiliado obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "Error al insertar un afiliado en solicitudes");
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }
    
    @Override
    public void actualizar(GsAfiliado obj) throws Exception {
        try {
            String hql = "UPDATE GsAfiliados SET"
                    + " documentoTipo = :documentoTipo,"
                    + " documentoNumero = :documentoNumero,"
                    + " documentoFechaExpedicion = :documentoFechaExpedicion,"
                    + " primerNombre = :primerNombre,"
                    + " segundoNombre = :segundoNombre,"
                    + " primerApellido = :primerApellido,"
                    + " segundoApellido = :segundoApellido,"
                    + " fechaNacimiento = :fechaNacimiento,"
                    + " sexo = :sexo,"
                    + " residenciaUbicacionNombre = :residenciaUbicacionNombre,"
                    + " residenciaDireccion = :residenciaDireccion,"
                    + " residenciaZonaTipo = :residenciaZonaTipo,"
                    + " departamentoResidencia = :departamentoResidencia,"
                    + " descripcionDireccion = :descripcionDireccion,"
                    + " atencionUbicacionNombre = :atencionUbicacionNombre"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            if (obj.getDocumentoNumero() == null) {
                query.setParameter("documentoNumero", null);
            } else {
                query.setParameter("documentoNumero", obj.getDocumentoNumero());
            }
            if (obj.getDocumentoFechaExpedicion() == null) {
                query.setParameter("documentoFechaExpedicion", null);
            } else {
                query.setParameter("documentoFechaExpedicion", obj.getDocumentoFechaExpedicion());
            }
            if (obj.getPrimerNombre() == null) {
                query.setParameter("primerNombre", null);
            } else {
                query.setParameter("primerNombre", obj.getPrimerNombre());
            }
            if (obj.getSegundoNombre() == null) {
                query.setParameter("segundoNombre", null);
            } else {
                query.setParameter("segundoNombre", obj.getSegundoNombre());
            }
            if (obj.getPrimerApellido() == null) {
                query.setParameter("primerApellido", null);
            } else {
                query.setParameter("primerApellido", obj.getPrimerApellido());
            }
            if (obj.getSegundoApellido() == null) {
                query.setParameter("segundoApellido", null);
            } else {
                query.setParameter("segundoApellido", obj.getSegundoApellido());
            }
            if (obj.getFechaNacimiento() == null) {
                query.setParameter("fechaNacimiento", null);
            } else {
                query.setParameter("fechaNacimiento", obj.getFechaNacimiento());
            }
            if (obj.getSexo() == null) {
                query.setParameter("sexo", null);
            } else {
                query.setParameter("sexo", obj.getSexo());
            }
            if (obj.getResidenciaUbicacion() == null) {
                query.setParameter("residenciaUbicacionesId", null);
            } else {
                query.setParameter("residenciaUbicacionesId", obj.getResidenciaUbicacion().getId());
            }
            if (obj.getResidenciaUbicacionNombre() == null) {
                query.setParameter("residenciaUbicacionNombre ", null);
            } else {
                query.setParameter("residenciaUbicacionNombre ", obj.getResidenciaUbicacionNombre());
            }
            if (obj.getResidenciaDireccion() == null) {
                query.setParameter("residenciaDireccion", null);
            } else {
                query.setParameter("residenciaDireccion", obj.getResidenciaDireccion());
            }
            if (obj.getResidenciaZonaTipo() == null) {
                query.setParameter("residenciaZonaTipo", null);
            } else {
                query.setParameter("residenciaZonaTipo", obj.getResidenciaZonaTipo());
            }
            if (obj.getAtencionUbicacion() == null) {
                query.setParameter("atencionUbicacionesId", null);
            } else {
                query.setParameter("atencionUbicacionesId", obj.getAtencionUbicacion().getId());
            }
            if (obj.getAtencionUbicacionNombre() == null) {
                query.setParameter("atencionUbicacionNombre", null);
            } else {
                query.setParameter("atencionUbicacionNombre", obj.getAtencionUbicacionNombre());
            }
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public GsAfiliado eliminar(int id) throws Exception {
        GsAfiliado obj = null;
        try {
            GsAfiliados ent = getEntityManager().find(GsAfiliados.class, id);
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
    public GsAfiliado consultar(int id) throws Exception {
        GsAfiliado objRes = null;
        try {
            GsAfiliados casoNegocio = (GsAfiliados) getEntityManager().find(GsAfiliados.class, id);
            objRes = castEntidadNegocio(casoNegocio);
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    public static GsAfiliado castEntidadNegocio(GsAfiliados per) {
        GsAfiliado obj = new GsAfiliado();
        obj.setId(per.getId());
        obj.setDocumentoTipo(per.getDocumentoTipo());
        obj.setDocumentoNumero(per.getDocumentoNumero());
        obj.setDocumentoFechaExpedicion(per.getDocumentoFechaExpedicion());
        obj.setPrimerNombre(per.getPrimerNombre());
        obj.setSegundoNombre(per.getSegundoNombre());
        obj.setPrimerApellido(per.getPrimerApellido());
        obj.setSegundoApellido(per.getSegundoApellido());
        obj.setFechaNacimiento(per.getFechaNacimiento());
        obj.setSexo(per.getSexo());
        if (per.getResidenciaUbicacionesId() != null) {
            obj.setResidenciaUbicacion(new Ubicacion(per.getResidenciaUbicacionesId().getId()));
            obj.setResidenciaUbicacionNombre(per.getResidenciaUbicacionesId().getNombre());
        }
        if (per.getAtencionUbicacionesId() != null) {
            obj.setAtencionUbicacion(new Ubicacion(per.getAtencionUbicacionesId().getId()));
            obj.setAtencionUbicacionNombre(per.getAtencionUbicacionesId().getNombre());
        }
        obj.setResidenciaDireccion(per.getResidenciaDireccion());
        obj.setResidenciaZonaTipo(per.getResidenciaZonaTipo());
        return obj;
    }

    public static GsAfiliados castNegocioEntidad(GsAfiliado obj) {
        GsAfiliados per = new GsAfiliados();
        per.setId(obj.getId());
        per.setDocumentoTipo(obj.getDocumentoTipo());
        per.setDocumentoNumero(obj.getDocumentoNumero());
        per.setDocumentoFechaExpedicion(obj.getDocumentoFechaExpedicion());
        per.setPrimerNombre(obj.getPrimerNombre());
        per.setSegundoNombre(obj.getSegundoNombre());
        per.setPrimerApellido(obj.getPrimerApellido());
        per.setSegundoApellido(obj.getSegundoApellido());
        per.setFechaNacimiento(obj.getFechaNacimiento());
        per.setSexo(obj.getSexo());
        if (obj.getResidenciaUbicacion() != null) {
            per.setResidenciaUbicacionNombre(obj.getResidenciaUbicacion().getNombre());
            per.setResidenciaUbicacionesId(new GnUbicaciones(obj.getResidenciaUbicacion().getId()));
        }
        if (obj.getAtencionUbicacion() != null) {
            per.setAtencionUbicacionNombre(obj.getAtencionUbicacion().getNombre());
            per.setAtencionUbicacionesId(new GnUbicaciones(obj.getAtencionUbicacion().getId()));
        }
        per.setResidenciaDireccion(obj.getResidenciaDireccion());
        per.setResidenciaZonaTipo(obj.getResidenciaZonaTipo());
        return per;
    }

    @Override
    public GsAfiliado consultarAfiliado(String numeroDocumento, String tipoDocumento) throws java.lang.Exception {
         GsAfiliado afiliadoItem = null;
        try {
            String strQuery = "FROM GsAfiliados p "
                    + "WHERE p.documentoTipo = '" + tipoDocumento + "' ";
            strQuery += " AND p.documentoNumero = " + numeroDocumento+ " ";
            strQuery += " ORDER BY ";
            strQuery += " p.documentoNumero DESC";
            List<GsAfiliados> list = getEntityManager().createQuery(strQuery).getResultList();
            if (!list.isEmpty()) {
               afiliadoItem = castEntidadNegocio(list.get(0));
            }
        } catch (NoResultException e) {
            afiliadoItem = null;
        } catch (Exception e) {
            afiliadoItem = null;
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return afiliadoItem;
         
    }
}
