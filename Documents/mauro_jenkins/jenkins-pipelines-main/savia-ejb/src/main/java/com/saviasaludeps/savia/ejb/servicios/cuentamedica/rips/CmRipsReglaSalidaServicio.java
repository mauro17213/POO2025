package com.saviasaludeps.savia.ejb.servicios.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsRegla;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsReglaSalida;
import com.saviasaludeps.savia.ejb.entidades.CmRipsReglaSalidas;
import com.saviasaludeps.savia.ejb.entidades.CmRipsReglas;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmRipsReglaSalidaRemoto;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Remote(CmRipsReglaSalidaRemoto.class)
public class CmRipsReglaSalidaServicio extends GenericoServicio implements CmRipsReglaSalidaRemoto {

    @Override
    public int insertar(CmRipsReglaSalida obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    @Override
    public List<CmRipsReglaSalida> consultarLista(int idRegla) throws java.lang.Exception {
        List<CmRipsReglaSalida> listResult = new ArrayList();
        try {
            String strQuery = "SELECT re FROM CmRipsReglaSalidas re "
                    + "WHERE cmRipsReglasId.id = :id ";
            strQuery += "ORDER BY id ASC ";
            List<CmRipsReglaSalidas> list = getEntityManager().createQuery(strQuery)
                    .setParameter("id", idRegla)
                    .getResultList();
            for (CmRipsReglaSalidas per : list) {
                listResult.add(castEntidadNegocio(per));
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
    public void eliminar(int id) throws Exception {
        CmRipsReglaSalida obj = null;
        try {
            CmRipsReglaSalidas ent = getEntityManager().find(CmRipsReglaSalidas.class, id);
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
    }

    public static CmRipsReglaSalida castEntidadNegocio(CmRipsReglaSalidas per) {
        CmRipsReglaSalida neg = new CmRipsReglaSalida();
        neg.setId(per.getId());
        neg.setCodigo(per.getCodigo());
        neg.setDescripcion(per.getDescripcion());
        neg.setCmRipsRegla(new CmRipsRegla(per.getCmRipsReglasId().getId()));
        return neg;
    }

    public static CmRipsReglaSalidas castNegocioEntidad(CmRipsReglaSalida obj) {
        CmRipsReglaSalidas ent = new CmRipsReglaSalidas();
        CmRipsReglas cmRipsReglas = new CmRipsReglas(obj.getId());
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        cmRipsReglas.setId(obj.getCmRipsRegla().getId());
        ent.setCmRipsReglasId(cmRipsReglas);
        ent.setCodigo(obj.getCodigo());
        ent.setDescripcion(obj.getDescripcion());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        if (obj.getUsuarioModifica() != null) {
            ent.setUsuarioModifica(obj.getUsuarioModifica());
            ent.setTerminalModifica(obj.getTerminalModifica());
            ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        }
        return ent;
    }

}
