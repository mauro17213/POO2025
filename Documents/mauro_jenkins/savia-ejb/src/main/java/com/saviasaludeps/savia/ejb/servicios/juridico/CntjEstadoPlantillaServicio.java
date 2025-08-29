package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjEstado;
import com.saviasaludeps.savia.dominio.juridico.CntjEstadoPlantilla;
import com.saviasaludeps.savia.dominio.juridico.CntjPlantilla;
import com.saviasaludeps.savia.ejb.entidades.CntjEstadoPlantillas;
import com.saviasaludeps.savia.ejb.entidades.CntjEstados;
import com.saviasaludeps.savia.ejb.entidades.CntjPlantillas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CntjEstadoPlantillaRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author Chass
 */
@Stateless
@Remote(CntjEstadoPlantillaRemoto.class)
public class CntjEstadoPlantillaServicio extends GenericoServicio implements CntjEstadoPlantillaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjEstadoPlantillas c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append(" AND id = " +  e.getValue() + " ");
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
    public List<CntjEstadoPlantilla> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntjEstadoPlantilla> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT p FROM CntjEstadoPlantillas p WHERE p.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append(" AND id = " +  e.getValue() + " ");
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
            List<CntjEstadoPlantillas> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjEstadoPlantillas estadoPlantilla : list) {
                listResult.add(castEntidadNegocio(estadoPlantilla));
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
    public int insertar(CntjEstadoPlantilla objeto) throws java.lang.Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(objeto)).getId();
            objeto.setId(id);
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
    public CntjEstadoPlantilla consultar(int id) throws java.lang.Exception {
        CntjEstadoPlantilla objRes = null;
        try {
            objRes = castEntidadNegocio((CntjEstadoPlantillas) getEntityManager().find(CntjEstadoPlantillas.class, id));
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
    public CntjEstadoPlantilla eliminar(int id) throws Exception {
        CntjEstadoPlantilla obj = null;
        try {
            CntjEstadoPlantillas ent = getEntityManager().find(CntjEstadoPlantillas.class, id);
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
    public List<CntjEstadoPlantilla> listaEstadoPlantillas(int idestado) throws java.lang.Exception {
        List<CntjEstadoPlantilla> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT p FROM CntjEstadoPlantillas p WHERE p.id > 0 and cntjEstadosId.id = ").append(idestado);            
            List<CntjEstadoPlantillas> list = getEntityManager().createQuery(strQuery.toString()).getResultList();
            for (CntjEstadoPlantillas estadoPlantilla : list) {
                listResult.add(castEntidadNegocio(estadoPlantilla));
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
    
    
    private CntjEstadoPlantilla castEntidadNegocio(CntjEstadoPlantillas entidad) {
        CntjEstadoPlantilla objeto = new CntjEstadoPlantilla();
        objeto.setId(entidad.getId());
        objeto.setEstadoId(new CntjEstado(entidad.getCntjEstadosId().getId()));
        CntjPlantilla plantilla = new CntjPlantilla(entidad.getCntjPlantillasId().getId());
        plantilla.setNombre(entidad.getCntjPlantillasId().getNombre());
        plantilla.setEstructura(entidad.getCntjPlantillasId().getEstructura());
        objeto.setPlantillaId(plantilla);
        objeto.setEditable(entidad.getEditable());
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        return objeto;
    }

    private CntjEstadoPlantillas castNegocioEntidad(CntjEstadoPlantilla obj) {
        CntjEstadoPlantillas ent = new CntjEstadoPlantillas();
        ent.setCntjEstadosId(new CntjEstados(obj.getEstadoId().getId()));
        ent.setCntjPlantillasId(new CntjPlantillas(obj.getPlantillaId().getId()));
        ent.setEditable(obj.isEditable());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }
    
    
    
}
