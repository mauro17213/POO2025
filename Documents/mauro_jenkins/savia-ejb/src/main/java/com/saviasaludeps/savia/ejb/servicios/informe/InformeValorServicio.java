package com.saviasaludeps.savia.ejb.servicios.informe;

import com.saviasaludeps.savia.dominio.informe.InfGenerado;
import com.saviasaludeps.savia.dominio.informe.InfInformeValor;
import com.saviasaludeps.savia.dominio.informe.InfInformeVariable;
import com.saviasaludeps.savia.ejb.entidades.InfInformeGenerados;
import com.saviasaludeps.savia.ejb.entidades.InfInformeValores;
import com.saviasaludeps.savia.ejb.entidades.InfInformeVariables;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.informe.InformeValorRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
@Remote(InformeValorRemoto.class)
public class InformeValorServicio extends GenericoServicio implements InformeValorRemoto {

    @Override
    public int insertar(InfInformeValor obj) throws Exception {
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
    public List<InfInformeValor> consultarListaValores(int id) throws Exception {
        List<InfInformeValor> listResult = new ArrayList();
        try {
            String strQuery = "FROM InfInformeValores i "
                    + "WHERE 1 = 1 AND i.id = " + id + " ";
            List<InfInformeValores> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (InfInformeValores ent : list) {
                listResult.add(castEntidadNegocio(ent));
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

    private InfInformeValores castNegocioEntidad(InfInformeValor negocio) {
        InfInformeValores ent = new InfInformeValores();
        ent.setInfGeneradosId(new InfInformeGenerados(negocio.getInfGenerado().getId()));
        ent.setInfInformeVariablesId(new InfInformeVariables(negocio.getInfInformeVariable().getId()));
        ent.setVariable(negocio.getVariable());
        ent.setUsuarioCrea(negocio.getUsuarioCrea());
        ent.setFechaHoraCrea(negocio.getFechaHoraCrea());
        ent.setTerminalCrea(negocio.getTerminalCrea());
        return ent;
    }

    private InfInformeValor castEntidadNegocio(InfInformeValores ent) {
        InfInformeValor neg = new InfInformeValor();
        neg.setId(ent.getId());
        neg.setInfGenerado(new InfGenerado(ent.getInfGeneradosId().getId()));
        neg.setInfInformeVariable(new InfInformeVariable(ent.getInfInformeVariablesId().getId()));
        neg.setVariable(ent.getVariable());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        return neg;
    }

    @Override
    public List<InfInformeValor> consultarPorIdVariable(int idVariable) throws Exception {
        List<InfInformeValor> valores = new ArrayList<>();
        try {
            String strQuery = "FROM InfInformeValores i "
                    + "WHERE i.infInformeVariablesId.id = " + idVariable + " ";
            List<InfInformeValores> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (InfInformeValores ent : list) {
                valores.add(castEntidadNegocio(ent));
            }
        } catch (NoResultException e) {
            valores = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return valores;
    }

    @Override
    public InfInformeValor borrar(int id) throws Exception {
        InfInformeValor obj = null;
        try {
            InfInformeValores ent = getEntityManager().find(InfInformeValores.class, id);
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

}
