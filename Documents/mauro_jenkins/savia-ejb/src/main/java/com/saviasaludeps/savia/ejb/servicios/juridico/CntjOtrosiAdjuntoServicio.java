
package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjOtrosi;
import com.saviasaludeps.savia.dominio.juridico.CntjOtrosiAdjunto;
import com.saviasaludeps.savia.ejb.entidades.CntjOtrosiAdjuntos;
import com.saviasaludeps.savia.ejb.entidades.CntjOtrosies;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CtnjOtrosiAdjuntoRemoto;
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
@Remote(CtnjOtrosiAdjuntoRemoto.class)
public class CntjOtrosiAdjuntoServicio extends GenericoServicio implements CtnjOtrosiAdjuntoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjOtrosiAdjuntos c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append(" AND c.id = ").append(e.getValue());
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

    @Override
    public List<CntjOtrosiAdjunto> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntjOtrosiAdjunto> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjOtrosiAdjuntos c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append(" AND c.id = ").append(e.getValue());
                            break;
                        
                    }
                }
            }
            strQuery.append(" ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                strQuery.append(" c." + paramConsulta.getOrden() + " " + (paramConsulta.isAscendente() ? "ASC" : "DESC"));
            } else {
                strQuery.append(" c.id DESC ");
            }
            List<CntjOtrosiAdjuntos> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjOtrosiAdjuntos adjunto  : list) {
                listResult.add(castEntidadNegocio(adjunto));
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
    public int insertar(CntjOtrosiAdjunto objeto) throws java.lang.Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(objeto)).getId();
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }
    
    @Override
    public CntjOtrosiAdjunto consultar(int id) throws java.lang.Exception {
        CntjOtrosiAdjunto objRes = null;
        try {
            objRes = castEntidadNegocio((CntjOtrosiAdjuntos) getEntityManager().find(CntjOtrosiAdjuntos.class, id));
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
    public List<CntjOtrosiAdjunto> adjuntosOtrosi(int id) throws java.lang.Exception {
        List<CntjOtrosiAdjunto> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjOtrosiAdjuntos c WHERE c.id > 0 and c.cntjOtrosiesId.id = :idotrosi  ");            
            strQuery.append(" ORDER BY c.id DESC ");
            List<CntjOtrosiAdjuntos> list = getEntityManager().createQuery(strQuery.toString())
                    .setParameter("idotrosi", id)
                    .getResultList();
            for (CntjOtrosiAdjuntos adjunto  : list) {
                listResult.add(castEntidadNegocio(adjunto));
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
    public CntjOtrosiAdjunto eliminar(int id) throws Exception {
        CntjOtrosiAdjunto obj = null;
        try {
            CntjOtrosiAdjuntos ent = getEntityManager().find(CntjOtrosiAdjuntos.class, id);
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
    
    
    private CntjOtrosiAdjunto castEntidadNegocio(CntjOtrosiAdjuntos entidad) {
        CntjOtrosiAdjunto objeto = new CntjOtrosiAdjunto();
        objeto.setId(entidad.getId());
        objeto.setOtrosiId(new CntjOtrosi(entidad.getCntjOtrosiesId().getId()));
        objeto.setMaetipoArchivoId(entidad.getMaeTipoArchivoId());
        objeto.setMaetipoArchivoCodigo(entidad.getMaeTipoArchivoCodigo());
        objeto.setMaetipoArchivoValor(entidad.getMaeTipoArchivoValor());
        objeto.setNombre(entidad.getNombre());
        objeto.setRuta(entidad.getRuta());
        objeto.setArchivo(entidad.getArchivo());
        objeto.setExiste(entidad.getExiste());        
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        return objeto;
    }
    
    private CntjOtrosiAdjuntos castNegocioEntidad(CntjOtrosiAdjunto obj){
        CntjOtrosiAdjuntos ent = new CntjOtrosiAdjuntos();
        ent.setCntjOtrosiesId(new CntjOtrosies(obj.getOtrosiId().getId()));
        ent.setMaeTipoArchivoId(obj.getMaetipoArchivoId());
        ent.setMaeTipoArchivoCodigo(obj.getMaetipoArchivoCodigo());
        ent.setMaeTipoArchivoValor(obj.getMaetipoArchivoValor());
        ent.setNombre(obj.getNombre());
        ent.setRuta(obj.getRuta());
        ent.setArchivo(obj.getArchivo());
        ent.setExiste(obj.isExiste());        
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }

    
}
