package com.saviasaludeps.savia.ejb.servicios.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsRegla;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsReglaEntrada;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.ejb.entidades.CmRipsReglaEntradas;
import com.saviasaludeps.savia.ejb.entidades.CmRipsReglas;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmRipsReglaEntradaRemoto;

@Stateless
@Remote(CmRipsReglaEntradaRemoto.class)
public class CmRipsReglaEntradaServicio extends GenericoServicio implements CmRipsReglaEntradaRemoto {

    @Override
    public List<CmRipsReglaEntrada> consultarLista(int idRegla) throws java.lang.Exception {
        List<CmRipsReglaEntrada> listResult = new ArrayList();
        try {
            String strQuery = "SELECT re FROM CmRipsReglaEntradas re "
                    + "WHERE cmRipsReglasId.id = :id ";
            strQuery += "ORDER BY archivo ASC ";
            List<CmRipsReglaEntradas> list = getEntityManager().createQuery(strQuery)
                    .setParameter("id", idRegla)
                    .getResultList();
            for (CmRipsReglaEntradas per : list) {
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
    public int insertar(CmRipsReglaEntrada obj) throws Exception {
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
    public void eliminar(int id) throws Exception {
        CmRipsReglaEntrada obj = null;
        try {
            CmRipsReglaEntradas ent = getEntityManager().find(CmRipsReglaEntradas.class, id);
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

    public static CmRipsReglaEntrada castEntidadNegocio(CmRipsReglaEntradas per) {
        CmRipsReglaEntrada neg = new CmRipsReglaEntrada();
        neg.setId(per.getId());
        neg.setCmRipsRegla(new CmRipsRegla(per.getCmRipsReglasId().getId()));
        neg.setTipo(per.getTipo());
        neg.setPosicion(per.getPosicion());
        neg.setArchivo(per.getArchivo());
        neg.setCampo(per.getCampo());
        return neg;
    }

    public static CmRipsReglaEntradas castNegocioEntidad(CmRipsReglaEntrada obj) {
        CmRipsReglaEntradas ent = new CmRipsReglaEntradas();
        CmRipsReglas cmRipsReglas = new CmRipsReglas();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        cmRipsReglas.setId(obj.getCmRipsRegla().getId());
        ent.setCmRipsReglasId(cmRipsReglas);
        ent.setTipo(obj.getTipo());
        ent.setPosicion(obj.getPosicion());
        ent.setOrden(obj.getOrden());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setArchivo(obj.getArchivo());
        ent.setCampo(obj.getCampo());
        return ent;
    }

}
