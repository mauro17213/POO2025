package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjEstado;
import com.saviasaludeps.savia.dominio.juridico.CntjEstadoProcesoDocumento;
import com.saviasaludeps.savia.dominio.juridico.CntjProcesoDocumento;
import com.saviasaludeps.savia.ejb.entidades.CntjEstadoProcesoDocumentos;
import com.saviasaludeps.savia.ejb.entidades.CntjEstados;
import com.saviasaludeps.savia.ejb.entidades.CntjProcesoDocumentos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CntjEstadoProcesoDocumentoRemoto;
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
@Remote(CntjEstadoProcesoDocumentoRemoto.class)
public class CntjEstadoProcesoDocumentoServicio extends GenericoServicio implements CntjEstadoProcesoDocumentoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjEstadoProcesoDocumentos c WHERE c.id > 0 ");
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
    public List<CntjEstadoProcesoDocumento> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntjEstadoProcesoDocumento> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT p FROM CntjEstadoProcesoDocumentos p WHERE p.id > 0 ");
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
            List<CntjEstadoProcesoDocumentos> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjEstadoProcesoDocumentos item : list) {
                listResult.add(castEntidadNegocio(item));
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
    public int insertar(CntjEstadoProcesoDocumento objeto) throws java.lang.Exception {
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
    public CntjEstadoProcesoDocumento consultar(int id) throws java.lang.Exception {
        CntjEstadoProcesoDocumento objRes = null;
        try {
            objRes = castEntidadNegocio((CntjEstadoProcesoDocumentos) getEntityManager().find(CntjEstadoProcesoDocumentos.class, id));
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
    public CntjEstadoProcesoDocumento eliminar(int id) throws Exception {
        CntjEstadoProcesoDocumento obj = null;
        try {
            CntjEstadoProcesoDocumentos ent = getEntityManager().find(CntjEstadoProcesoDocumentos.class, id);
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
    public List<CntjEstadoProcesoDocumento> listaDocumentoEstado(int idestado) throws java.lang.Exception {
        List<CntjEstadoProcesoDocumento> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT p FROM CntjEstadoProcesoDocumentos p WHERE p.id > 0 and p.cntjProcesoDocumentosId.activo = 1 and p.cntjEstadosId.id = ").append(idestado);            
            List<CntjEstadoProcesoDocumentos> list = getEntityManager().createQuery(strQuery.toString()).getResultList();
            for (CntjEstadoProcesoDocumentos item : list) {
                listResult.add(castEntidadNegocio(item));
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
    public List<CntjEstadoProcesoDocumento> listaDocumentoEstadoGenerados(int idestado) throws java.lang.Exception {
        List<CntjEstadoProcesoDocumento> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT p FROM CntjEstadoProcesoDocumentos p WHERE p.id > 0 ");     
            strQuery.append(" and p.cntjProcesoDocumentosId.activo = 1 ");
            strQuery.append(" and p.cntjEstadosId.id = ").append(idestado);
            strQuery.append(" and p.cntjProcesoDocumentosId.tipoDocumento = 0 ");
            List<CntjEstadoProcesoDocumentos> list = getEntityManager().createQuery(strQuery.toString()).getResultList();
            for (CntjEstadoProcesoDocumentos item : list) {
                listResult.add(castEntidadNegocio(item));
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
    public List<CntjEstadoProcesoDocumento> listaDocumentoEstadoAdjuntos(int idestado) throws java.lang.Exception {
        List<CntjEstadoProcesoDocumento> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT p FROM CntjEstadoProcesoDocumentos p WHERE p.id > 0 ");     
            strQuery.append(" and p.cntjProcesoDocumentosId.activo = 1 ");
            strQuery.append(" and p.cntjEstadosId.id = ").append(idestado);
            strQuery.append(" and p.cntjProcesoDocumentosId.tipoDocumento > 0 ");
            List<CntjEstadoProcesoDocumentos> list = getEntityManager().createQuery(strQuery.toString()).getResultList();
            for (CntjEstadoProcesoDocumentos item : list) {
                listResult.add(castEntidadNegocio(item));
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
    public List<CntjEstadoProcesoDocumento> listaDocumentoEstadoDigitalizado(int idestado) throws java.lang.Exception {
        List<CntjEstadoProcesoDocumento> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT p FROM CntjEstadoProcesoDocumentos p WHERE p.id > 0 ");     
            strQuery.append(" and p.cntjProcesoDocumentosId.activo = 1 ");
            strQuery.append(" and p.cntjEstadosId.id = ").append(idestado);
            strQuery.append(" and p.cntjProcesoDocumentosId.tipoDocumento = 1 ");
            List<CntjEstadoProcesoDocumentos> list = getEntityManager().createQuery(strQuery.toString()).getResultList();
            for (CntjEstadoProcesoDocumentos item : list) {
                listResult.add(castEntidadNegocio(item));
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
    
    
    private CntjEstadoProcesoDocumento castEntidadNegocio(CntjEstadoProcesoDocumentos entidad) {
        CntjEstadoProcesoDocumento objeto = new CntjEstadoProcesoDocumento();
        objeto.setId(entidad.getId());
        CntjEstado estado = new CntjEstado(entidad.getCntjEstadosId().getId());
        estado.setNombre(entidad.getCntjProcesoDocumentosId().getNombre());
        objeto.setEstadoId(estado);
        CntjProcesoDocumento documento = new CntjProcesoDocumento(entidad.getCntjProcesoDocumentosId().getId());
        documento.setNombre(entidad.getCntjProcesoDocumentosId().getNombre());
        documento.setTipoDocumento(entidad.getCntjProcesoDocumentosId().getTipoDocumento());
        documento.setEtapaContratacion(entidad.getCntjProcesoDocumentosId().getEtapaContratacion());
        objeto.setProcesodocumentoId(documento);
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        return objeto;
    }

    private CntjEstadoProcesoDocumentos castNegocioEntidad(CntjEstadoProcesoDocumento obj) {
        CntjEstadoProcesoDocumentos ent = new CntjEstadoProcesoDocumentos();
        ent.setCntjEstadosId(new CntjEstados(obj.getEstadoId().getId()));
        ent.setCntjEstadosId(new CntjEstados(ent.getCntjEstadosId().getId()));
        ent.setCntjProcesoDocumentosId(new CntjProcesoDocumentos(obj.getProcesodocumentoId().getId()));
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }

    
    
    
}
