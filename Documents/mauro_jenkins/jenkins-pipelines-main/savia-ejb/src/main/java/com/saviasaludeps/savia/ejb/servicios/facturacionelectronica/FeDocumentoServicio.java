package com.saviasaludeps.savia.ejb.servicios.facturacionelectronica;

import com.saviasaludeps.savia.dominio.facturacionelectronica.FeDocumento;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.FeDocumentos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.facturacionelectronica.FeDocumentoRemoto;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Stateless
@Remote(FeDocumentoRemoto.class)
public class FeDocumentoServicio extends GenericoServicio implements FeDocumentoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(d.id) FROM FeDocumentos d WHERE 1 = 1";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry<String, Object> e : paramConsulta.getFiltros().entrySet()) {
                    switch (e.getKey()) {
                        case "prestadorNit":
                            strQuery += " AND d.prestadorNit = :prestadorNit";
                            break;
                        case "documentoNumero":
                            strQuery += " AND d.documentoNumero = :documentoNumero";
                            break;
                        case "documentoTipo":
                            strQuery += " AND d.documentoTipo = :documentoTipo";
                            break;
                    }
                }
            }

            Query query = getEntityManager().createQuery(strQuery);

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry<String, Object> e : paramConsulta.getFiltros().entrySet()) {
                    if ("documentoTipo".equals(e.getKey())) {
                        query.setParameter(e.getKey(), Short.valueOf(String.valueOf(e.getValue())));
                    } else {
                        query.setParameter(e.getKey(), e.getValue());
                    }
                }
            }

            cantidad = ((Long) query.getSingleResult()).intValue();
        } catch (NoResultException e) {
            cantidad = 0;
        } catch (Exception e) {
            throw new Exception("Error al consultar la cantidad de registros: " + e.getMessage(), e);
        } finally {
            cerrarEntityManager();
        }

        return cantidad;
    }

    @Override
    public List<FeDocumento> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<FeDocumento> listResult = new ArrayList<>();
        try {
            String strQuery = "SELECT d FROM FeDocumentos d WHERE 1 = 1";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry<String, Object> e : paramConsulta.getFiltros().entrySet()) {
                    switch (e.getKey()) {
                        case "prestadorNit":
                            strQuery += " AND d.prestadorNit = :prestadorNit";
                            break;
                        case "documentoNumero":
                            strQuery += " AND d.documentoNumero = :documentoNumero";
                            break;
                        case "documentoTipo":
                            strQuery += " AND d.documentoTipo = :documentoTipo";
                            break;
                    }
                }
            }

            if (paramConsulta.getOrden() != null && !paramConsulta.getOrden().isEmpty()) {
                strQuery += " ORDER BY d." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " ORDER BY d.id ASC";
            }

            Query query = getEntityManager().createQuery(strQuery);

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry<String, Object> e : paramConsulta.getFiltros().entrySet()) {
                    if ("documentoTipo".equals(e.getKey())) {
                        query.setParameter(e.getKey(), Short.valueOf(String.valueOf(e.getValue())));
                    } else {
                        query.setParameter(e.getKey(), e.getValue());
                    }
                }
            }

            query.setFirstResult(paramConsulta.getPrimerRegistro());
            query.setMaxResults(paramConsulta.getRegistrosPagina());

            List<FeDocumentos> list = query.getResultList();
            for (FeDocumentos per : list) {
                listResult.add(castEntidadNegocio(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList<>();
        } catch (Exception e) {
            throw new Exception("Error al consultar la lista de documentos: " + e.getMessage(), e);
        } finally {
            cerrarEntityManager();
        }

        return listResult;
    }

    @Override
    public FeDocumento consultar(int id) throws Exception {
        FeDocumento objRes = null;
        try {
            FeDocumentos documentoEntidad = getEntityManager().find(FeDocumentos.class, id);
            if (documentoEntidad != null) {
                objRes = castEntidadNegocio(documentoEntidad);
            }
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            throw new Exception("Error al consultar el documento: " + e.getMessage(), e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public FeDocumento consultarFacturaPorPrestador(ParamConsulta paramConsulta) throws java.lang.Exception {
        FeDocumento feDocumento = new FeDocumento();
        try {
            String strQuery = "FROM FeDocumentos fed WHERE 1 = 1 ";
            
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND fed.prestadorNit = '" + paramConsulta.getParametroConsulta1() + "' ";
            }

            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND fed.documentoNumero = '" + paramConsulta.getParametroConsulta2() + "' ";
            }
            
            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += "AND fed.documentoTipo = " + paramConsulta.getParametroConsulta3() + " ";
            }

            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "fed." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "fed.id DESC";
            }
            List<FeDocumentos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (FeDocumentos feDocumentoObtenido : list) {
                  feDocumento = castEntidadNegocio(feDocumentoObtenido);
            }
        } catch (NoResultException e) {
            feDocumento = new FeDocumento();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return feDocumento;
    }

    public static FeDocumento castEntidadNegocio(FeDocumentos per) {
        FeDocumento obj = new FeDocumento();
        obj.setId(per.getId());
        obj.setPrestadorNit(per.getPrestadorNit());
        obj.setDocumentoTipo(per.getDocumentoTipo());
        obj.setDocumentoNumero(per.getDocumentoNumero());
        obj.setDocumentoValor(per.getDocumentoValor());
        obj.setEstado(per.getEstado());
        obj.setCodigoUnicoDian(per.getCodigoUnicoDian());
        obj.setEstadoDescripcion(per.getEstadoDescripcion());
        obj.setFechaDocumento(per.getFechaDocumento());
        obj.setFechaRadicacion(per.getFechaRadicacion());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        return obj;
    }

    public static FeDocumentos castNegocioEntidad(FeDocumento obj) {
        FeDocumentos per = new FeDocumentos();
        per.setId(obj.getId());
        per.setPrestadorNit(obj.getPrestadorNit());
        per.setDocumentoTipo(obj.getDocumentoTipo());
        per.setDocumentoNumero(obj.getDocumentoNumero());
        per.setDocumentoValor(obj.getDocumentoValor());
        per.setEstado(obj.getEstado());
        per.setCodigoUnicoDian(obj.getCodigoUnicoDian());
        per.setEstadoDescripcion(obj.getEstadoDescripcion());
        per.setFechaDocumento(obj.getFechaDocumento());
        per.setFechaRadicacion(obj.getFechaRadicacion());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        return per;
    }

}
