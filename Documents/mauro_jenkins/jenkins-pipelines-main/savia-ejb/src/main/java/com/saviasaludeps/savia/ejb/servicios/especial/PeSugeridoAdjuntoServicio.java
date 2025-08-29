
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.saviasaludeps.savia.dominio.especial.PeAfiliadoSugerido;
import com.saviasaludeps.savia.dominio.especial.PeSugeridoAdjunto;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.PeAfiliadosSugeridos;
import com.saviasaludeps.savia.ejb.entidades.PeSugeridoAdjuntos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.especial.PeSugeridoAdjuntoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author idbohorquez
 */
@Stateless
@Remote(PeSugeridoAdjuntoRemoto.class)
public class PeSugeridoAdjuntoServicio extends GenericoServicio implements PeSugeridoAdjuntoRemoto {

    
    /**
     * Consulta de cantidad de registros en una lista
     * @author idbohorquez
     * @creacion 15/02/2023
     * @param paramConsulta
     * @return int
     * @throws Exception 
     */
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(p) FROM PeSugeridoAdjuntos p ");
            strQuery.append(" WHERE p.id > 0   ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery.append(" AND p.nombre = " + (String) e.getValue() + " ");
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
     * @author idbohorquez
     * @creacion 15/02/2023
     * @param paramConsulta
     * @return List<PeSugeridoAdjunto>
     * @throws Exception 
     */
    @Override
    public List<PeSugeridoAdjunto> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<PeSugeridoAdjunto> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT p FROM PeSugeridoAdjuntos p WHERE p.id > 0  ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery.append(" AND p.nombre = " + (String) e.getValue() + " ");
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
            List<PeSugeridoAdjuntos> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (PeSugeridoAdjuntos adjuntos : list) {
                listResult.add(castEntidadNegocio(adjuntos));
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
     * Insertar nuevo registro adjunto sugerido
     * @author idbohorquez
     * @creacion 15/02/2023
     * @param obj
     * @return int
     * @throws Exception 
     */
    @Override
    public int insertar(PeSugeridoAdjunto obj) throws java.lang.Exception {
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
     * Consultar lista de registros de adjuntos en sugeridos 
     * @author idbohorquez
     * @creacion 15/02/2023
     * @return List<PeSugeridoAdjunto>
     * @throws Exception 
     */
    @Override
    public List<PeSugeridoAdjunto> listar() throws java.lang.Exception {
        List<PeSugeridoAdjunto> listResult = new ArrayList<>();
         try {
            String strQuery = "SELECT p FROM PeSugeridoAdjuntos p WHERE p.id > 0 ";

            List<PeSugeridoAdjuntos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (PeSugeridoAdjuntos adjuntos : list) {
                listResult.add(castEntidadNegocio(adjuntos));
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
     * Consultar lista de registros de adjuntos por id sugerido
     * @author idbohorquez
     * @param idSugerido
     * @creacion 1/02/2023
     * @return List<PeSugeridoAdjunto>
     * @throws Exception 
     */
    @Override
    public List<PeSugeridoAdjunto> listar(Integer idSugerido) throws java.lang.Exception {
         List<PeSugeridoAdjunto> listResult = new ArrayList<>();
         try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(" SELECT p FROM PeSugeridoAdjuntos p WHERE p.id > 0 ");
            strQuery.append(" AND p.peAfiliadosSugeridosId.id = ").append(idSugerido);

            List<PeSugeridoAdjuntos> list = getEntityManager().createQuery(strQuery.toString())
                    .getResultList();
            for (PeSugeridoAdjuntos adjuntos : list) {
                listResult.add(castEntidadNegocio(adjuntos));
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
     * funcion encargada de pasar la información del objeto entidad a dominino
     *
     * @author idbohorquez
     * @creacion 15/02/2023
     * @param paramConsulta
     * @return PeSugeridoAdjunto
     */
    private PeSugeridoAdjunto castEntidadNegocio(PeSugeridoAdjuntos ent){
        PeSugeridoAdjunto obj = new PeSugeridoAdjunto();
        obj.setId(ent.getId());
        obj.setPeAfiliadoSugerido(new PeAfiliadoSugerido(ent.getPeAfiliadosSugeridosId().getId()));
        obj.setMaeTipoArchivoId(ent.getMaeTipoArchivoId());
        obj.setMaeTipoArchivoCodigo(ent.getMaeTipoArchivoCodigo());
        obj.setMaeTipoArchivoValor(ent.getMaeTipoArchivoValor());
        obj.setNombre(ent.getNombre());
        obj.setRuta(ent.getRuta());
        obj.setArchivo(ent.getArchivo());
        obj.setObservacion(ent.getObservacion());
        obj.setExiste(ent.getExiste());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        return obj;        
    }
    
    
    /**
     * funcion encargada de pasar la información del objeto entidad a dominino
     *
     * @author idbohorquez
     * @creacion 15/02/2023
     * @param paramConsulta
     * @return PeSugeridoAdjunto
     */
    private PeSugeridoAdjuntos castNegocioEntidad(PeSugeridoAdjunto obj){
        PeSugeridoAdjuntos ent = new PeSugeridoAdjuntos();
        ent.setPeAfiliadosSugeridosId(new PeAfiliadosSugeridos(obj.getPeAfiliadoSugerido().getId()));
        ent.setMaeTipoArchivoId(obj.getMaeTipoArchivoId());
        ent.setMaeTipoArchivoCodigo(obj.getMaeTipoArchivoCodigo());
        ent.setMaeTipoArchivoValor(obj.getMaeTipoArchivoValor());
        ent.setNombre(obj.getNombre());
        ent.setRuta(obj.getRuta());
        ent.setArchivo(obj.getArchivo());
        ent.setObservacion(ent.getObservacion());
        ent.setExiste(obj.getExiste());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        ent.setTerminalModifica(obj.getTerminalModifica());
        return ent;        
    }

    
    
}
