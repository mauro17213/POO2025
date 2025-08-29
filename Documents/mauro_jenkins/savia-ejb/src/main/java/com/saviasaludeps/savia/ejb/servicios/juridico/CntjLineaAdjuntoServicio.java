
package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjLinea;
import com.saviasaludeps.savia.dominio.juridico.CntjLineaAdjunto;
import com.saviasaludeps.savia.ejb.entidades.CntjLineaAdjuntos;
import com.saviasaludeps.savia.ejb.entidades.CntjLineas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CtnjLineaAdjuntoRemoto;
import java.nio.file.Files;
import java.nio.file.Paths;
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
@Remote(CtnjLineaAdjuntoRemoto.class)
public class CntjLineaAdjuntoServicio extends GenericoServicio implements CtnjLineaAdjuntoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjLineaAdjuntos c WHERE c.id > 0 ");
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
    public List<CntjLineaAdjunto> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntjLineaAdjunto> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjLineaAdjuntos c WHERE c.id > 0 ");
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
            List<CntjLineaAdjuntos> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjLineaAdjuntos adjunto  : list) {
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
    public int insertar(CntjLineaAdjunto objeto) throws java.lang.Exception {
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
    public CntjLineaAdjunto consultar(int id) throws java.lang.Exception {
        CntjLineaAdjunto objRes = null;
        try {
            objRes = castEntidadNegocio((CntjLineaAdjuntos) getEntityManager().find(CntjLineaAdjuntos.class, id));
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
    public List<CntjLineaAdjunto> adjuntosLineas(int idlinea) throws java.lang.Exception {
        List<CntjLineaAdjunto> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjLineaAdjuntos c WHERE c.id > 0 and cntjLineasId.id = :idlinea  ");            
            strQuery.append(" ORDER BY c.id DESC ");
            List<CntjLineaAdjuntos> list = getEntityManager().createQuery(strQuery.toString())
                    .setParameter("idlinea", idlinea)
                    .getResultList();
            for (CntjLineaAdjuntos adjunto  : list) {
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
    public CntjLineaAdjunto eliminar(int id) throws Exception {
        CntjLineaAdjunto obj = null;
        try {
            CntjLineaAdjuntos ent = getEntityManager().find(CntjLineaAdjuntos.class, id);
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
    
    
    private CntjLineaAdjunto castEntidadNegocio(CntjLineaAdjuntos entidad) {
        CntjLineaAdjunto objeto = new CntjLineaAdjunto();
        objeto.setId(entidad.getId());
        objeto.setCntjLineasId(new CntjLinea(entidad.getCntjLineasId().getId()));
        objeto.setMaeTipoArchivoId(entidad.getMaeTipoArchivoId());
        objeto.setMaeTipoArchivoCodigo(entidad.getMaeTipoArchivoCodigo());
        objeto.setMaeTipoArchivoValor(entidad.getMaeTipoArchivoValor());
        objeto.setNombre(entidad.getNombre());
        objeto.setRuta(entidad.getRuta());
        objeto.setArchivo(entidad.getArchivo());
        objeto.setExiste(entidad.getExiste()); 
        if (!Files.exists(Paths.get(entidad.getRuta() + entidad.getArchivo()))) {
            objeto.setExiste(false);
        }
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        return objeto;
    }
    
    private CntjLineaAdjuntos castNegocioEntidad(CntjLineaAdjunto obj){
        CntjLineaAdjuntos ent = new CntjLineaAdjuntos();
        ent.setCntjLineasId(new CntjLineas(obj.getCntjLineasId().getId()));
        ent.setMaeTipoArchivoId(obj.getMaeTipoArchivoId());
        ent.setMaeTipoArchivoCodigo(obj.getMaeTipoArchivoCodigo());
        ent.setMaeTipoArchivoValor(obj.getMaeTipoArchivoValor());
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
