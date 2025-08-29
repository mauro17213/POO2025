
package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.juridico.CntjEstado;
import com.saviasaludeps.savia.dominio.juridico.CntjEstadoGrupo;
import com.saviasaludeps.savia.dominio.juridico.CntjGrupo;
import com.saviasaludeps.savia.ejb.entidades.CntjEstadoGrupos;
import com.saviasaludeps.savia.ejb.entidades.CntjEstados;
import com.saviasaludeps.savia.ejb.entidades.CntjGrupos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CntjEstadoGrupoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author idbohorquez
 */
@Stateless
@Remote(CntjEstadoGrupoRemoto.class)
public class CntjEstadoGrupoServicio extends GenericoServicio implements CntjEstadoGrupoRemoto {

    @Override
    public int insertar(CntjEstadoGrupo objeto) throws java.lang.Exception {
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
    public List<CntjEstadoGrupo> gruposEstado(int idEstado) throws java.lang.Exception {
        List<CntjEstadoGrupo> listResult = new ArrayList<>();
        try {
            String strQuery = "SELECT p FROM CntjEstadoGrupos p WHERE p.id > 0 and p.cntjEstadosId.id = :idestado ";

            List<CntjEstadoGrupos> list = getEntityManager().createQuery(strQuery)
                    .setParameter("idestado", idEstado)
                    .getResultList();
            for (CntjEstadoGrupos pc : list) {
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
    public CntjEstadoGrupo eliminar(int id) throws Exception {
        CntjEstadoGrupo obj = null;
        try {
            CntjEstadoGrupos ent = getEntityManager().find(CntjEstadoGrupos.class, id);
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
    public CntjEstadoGrupo consultar(int idestado) throws java.lang.Exception {
        CntjEstadoGrupo objRes = null;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjEstadoGrupos c WHERE c.id > 0 and cntjEstadosId.id = ").append(idestado);
            CntjEstadoGrupos estadogrupo = (CntjEstadoGrupos) getEntityManager().createQuery(strQuery.toString()).getSingleResult();                    
            objRes = castEntidadNegocio(estadogrupo);
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }
    
    private CntjEstadoGrupo castEntidadNegocio(CntjEstadoGrupos entidad) {
        CntjEstadoGrupo objeto = new CntjEstadoGrupo();
        objeto.setId(entidad.getId());
        if(entidad.getCntjEstadosId() != null){
            objeto.setEstadoId(new CntjEstado(entidad.getCntjEstadosId().getId()));
        }
        if(entidad.getCntjGruposId() != null){
            CntjGrupo grupo = new CntjGrupo(entidad.getCntjGruposId().getId());
            grupo.setNombre(entidad.getCntjGruposId().getNombre());
            grupo.setActivo(entidad.getCntjGruposId().getActivo());
            objeto.setGrupoId(grupo);
        }   
        objeto.setEjecucion(entidad.getEjecucion());
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        return objeto;
    }

    private CntjEstadoGrupos castNegocioEntidad(CntjEstadoGrupo obj) {
        CntjEstadoGrupos ent = new CntjEstadoGrupos();
        ent.setId(obj.getId());
        ent.setCntjEstadosId(new CntjEstados(obj.getEstadoId().getId()));
        ent.setCntjGruposId(new CntjGrupos(obj.getGrupoId().getId()));
        ent.setEjecucion(obj.getEjecucion());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }
    
}
