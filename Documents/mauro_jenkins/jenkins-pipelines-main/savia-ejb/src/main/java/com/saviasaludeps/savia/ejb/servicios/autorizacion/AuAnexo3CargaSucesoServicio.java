package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3CargaSuceso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo3CargaSucesos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3CargaSucesoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
@Remote(AuAnexo3CargaSucesoRemoto.class)
public class AuAnexo3CargaSucesoServicio extends GenericoServicio implements AuAnexo3CargaSucesoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM AuAnexo3CargaSucesos c ";
            strQuery += "WHERE c.auAnexo3CargasId = " + paramConsulta.getParametroConsulta3() + " ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery += "AND c.tipo = " + e.getValue() + " ";
                            break;
                        case "descripcion":
                            strQuery += "AND c.descripcion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "fila":
                            strQuery += "AND c.fila = " + e.getValue() + " ";
                            break;
                        case "columna":
                            strQuery += "AND c.columna = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            cantidad = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e) {
            cantidad = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cantidad;
    }

    @Override
    public List<AuAnexo3CargaSuceso> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo3CargaSuceso> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo3CargaSucesos c ";
            strQuery += "WHERE c.auAnexo3CargasId = " + paramConsulta.getParametroConsulta3() + " ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery += "AND c.tipo = " + e.getValue() + " ";
                            break;
                        case "descripcion":
                            strQuery += "AND c.descripcion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "fila":
                            strQuery += "AND c.fila = " + e.getValue() + " ";
                            break;
                        case "columna":
                            strQuery += "AND c.columna = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "c." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "c.id ASC";
            }
            List<AuAnexo3CargaSucesos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexo3CargaSucesos entidad : list) {
                listaResultados.add(castEntidadNegocio(entidad));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }

    public AuAnexo3CargaSuceso castEntidadNegocio(AuAnexo3CargaSucesos entidad) {
        AuAnexo3CargaSuceso negocio = new AuAnexo3CargaSuceso();
        negocio.setId(entidad.getId());
        negocio.setFila(entidad.getFila());
        negocio.setTipo(entidad.getTipo());
        negocio.setColumna(entidad.getColumna());
        negocio.setDescripcion(entidad.getDescripcion());
        return negocio;
    }
}
