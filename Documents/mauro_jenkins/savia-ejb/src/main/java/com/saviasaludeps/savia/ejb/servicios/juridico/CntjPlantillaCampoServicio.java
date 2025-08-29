package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.juridico.CntjCampo;
import com.saviasaludeps.savia.dominio.juridico.CntjPlantilla;
import com.saviasaludeps.savia.dominio.juridico.CntjPlantillaCampo;
import com.saviasaludeps.savia.dominio.juridico.CntjProcesoDocumento;
import com.saviasaludeps.savia.ejb.entidades.CntjCampos;
import com.saviasaludeps.savia.ejb.entidades.CntjPlantillaCampos;
import com.saviasaludeps.savia.ejb.entidades.CntjPlantillas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.juridico.CntjPlantillaCampoRemoto;

/**
 *
 * @author idbohorquez
 */
@Stateless
@Remote(CntjPlantillaCampoRemoto.class)
public class CntjPlantillaCampoServicio extends GenericoServicio implements CntjPlantillaCampoRemoto {

    
    @Override
    public int insertar(CntjPlantillaCampo objeto) throws java.lang.Exception {
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
    public List<CntjPlantillaCampo> plantillasCampos(int idcampo) throws java.lang.Exception {
        List<CntjPlantillaCampo> listResult = new ArrayList<>();
        try {
            String strQuery = "SELECT p FROM CntjPlantillaCampos p WHERE p.id > 0 and p.cntjCampoId.id = :idcampo ";

            List<CntjPlantillaCampos> list = getEntityManager().createQuery(strQuery)
                    .setParameter("idcampo", idcampo)
                    .getResultList();
            for (CntjPlantillaCampos pc : list) {
                listResult.add(castEntidadNegocio(pc));
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
    public CntjPlantillaCampo eliminar(int id) throws Exception {
        CntjPlantillaCampo obj = null;
        try {
            CntjPlantillaCampos ent = getEntityManager().find(CntjPlantillaCampos.class, id);
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
    public List<CntjCampo> getCamposPlantilla(int idplantilla) throws java.lang.Exception {
        List<CntjCampo> listResult = new ArrayList<>();
        try {
            String strQuery = "SELECT p FROM CntjPlantillaCampos p WHERE p.id > 0 and p.cntjPlantillasId.id = :idplantilla ";

            List<CntjPlantillaCampos> list = getEntityManager().createQuery(strQuery)
                    .setParameter("idplantilla", idplantilla)
                    .getResultList();
            for (CntjPlantillaCampos pc : list) {
                listResult.add(castEntidadNegocioCampo(pc));
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
    public List<CntjPlantillaCampo> listaCamposDocumentoEstadoGenerados(int idestado) throws java.lang.Exception {
        List<CntjPlantillaCampo> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT pc FROM CntjPlantillas p  ");     
            strQuery.append(" inner join CntjEstadoProcesoDocumentos ed on p.cntjProcesoDocumentosId.id = ed.cntjProcesoDocumentosId.id ");
            strQuery.append(" inner join CntjPlantillaCampos pc on pc.cntjPlantillasId.id = p.id ");            
            strQuery.append(" where p.id > 0 and ed.cntjProcesoDocumentosId.activo = 1 and ed.cntjEstadosId.id = ").append(idestado);
            strQuery.append(" and ed.cntjProcesoDocumentosId.tipoDocumento = 0 ");
            List<CntjPlantillaCampos> list = getEntityManager().createQuery(strQuery.toString()).getResultList();
            for (CntjPlantillaCampos item : list) {
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

    private CntjPlantillaCampo castEntidadNegocio(CntjPlantillaCampos entidad) {
        CntjPlantillaCampo objeto = new CntjPlantillaCampo();
        objeto.setId(entidad.getId());
        if(entidad.getCntjCampoId() != null){
            objeto.setCntjCampoId(new CntjCampo(entidad.getCntjCampoId().getId()));
        }
        if(entidad.getCntjPlantillasId() != null){
            CntjPlantilla plantilla = new CntjPlantilla(entidad.getCntjPlantillasId().getId());
            plantilla.setNombre(entidad.getCntjPlantillasId().getNombre());
            plantilla.setVersion(entidad.getCntjPlantillasId().getVersion());
            if (entidad.getCntjPlantillasId().getCntjProcesoDocumentosId() != null) {
                CntjProcesoDocumento procesoDocumento = new CntjProcesoDocumento(entidad.getCntjPlantillasId().getCntjProcesoDocumentosId().getCntjProcesosId().getId());
                procesoDocumento.setNombre(entidad.getCntjPlantillasId().getCntjProcesoDocumentosId().getCntjProcesosId().getNombre());
                plantilla.setProcesodocumentoId(procesoDocumento);
            }
            objeto.setCntjPlantillaId(plantilla);
        }        
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        return objeto;
    }

    private CntjPlantillaCampos castNegocioEntidad(CntjPlantillaCampo obj) {
        CntjPlantillaCampos ent = new CntjPlantillaCampos();
        ent.setId(obj.getId());
        ent.setCntjCampoId(new CntjCampos(obj.getCntjCampoId().getId()));
        ent.setCntjPlantillasId(new CntjPlantillas(obj.getCntjPlantillaId().getId()));
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }
    
    private CntjCampo castEntidadNegocioCampo(CntjPlantillaCampos entidad) {
        CntjCampo objeto = new CntjCampo();
        if (entidad.getCntjCampoId() != null) {
            objeto.setId(entidad.getCntjCampoId().getId());
            objeto.setNombre(entidad.getCntjCampoId().getNombre());
            objeto.setEtiqueta(entidad.getCntjCampoId().getEtiqueta());
            objeto.setDescripcion(entidad.getCntjCampoId().getDescripcion());
            objeto.setUsuarioCrea(entidad.getUsuarioCrea());
            objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
            objeto.setTerminalCrea(entidad.getTerminalCrea());
        }  
        return objeto;
    }

}
