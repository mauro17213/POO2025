package com.saviasaludeps.savia.ejb.servicios.informe;

import com.saviasaludeps.savia.dominio.informe.InfInformeVariable;
import com.saviasaludeps.savia.ejb.entidades.InfInformeVariables;
import com.saviasaludeps.savia.ejb.entidades.InfInformes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.informe.InformeVariableRemoto;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Stateless
@Remote(InformeVariableRemoto.class)
public class InformeVariableServicio extends GenericoServicio implements InformeVariableRemoto {

    @Override
    public int insertar(InfInformeVariable obj) throws Exception {
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
    public void actualizar(InfInformeVariable obj) throws java.lang.Exception {
        try {
            String hql = "UPDATE InfInformeVariables SET "
                    + "nombre = :nombre, "
                    + "valor = :valor, "
                    + "orden = :orden, "
                    + "dinamico = :dinamico, "
                    + "tipo = :tipo "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("valor", obj.getValor());
            query.setParameter("orden", obj.getOrden());
            query.setParameter("dinamico", obj.isDinamico());
            query.setParameter("tipo", obj.getTipo());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public InfInformeVariable eliminar(int id) throws Exception {
        InfInformeVariable obj = null;
        try {
            InfInformeVariables ent = getEntityManager().find(InfInformeVariables.class, id);
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
    
    private InfInformeVariables castNegocioEntidad(InfInformeVariable negocio) {
        InfInformeVariables ent = new InfInformeVariables();
        ent.setInfInformesId(new InfInformes(negocio.getInfInforme().getId()));
        ent.setNombre(negocio.getNombre());
        ent.setValor(negocio.getValor());
        ent.setOrden(negocio.getOrden());
        ent.setDinamico(negocio.isDinamico());
        ent.setTipo(negocio.getTipo());
        ent.setUsuarioCrea(negocio.getUsuarioCrea());
        ent.setFechaHoraCrea(negocio.getFechaHoraCrea());
        ent.setTerminalCrea(negocio.getTerminalCrea());
        return ent;
    }
    
    private InfInformeVariable castEntidadNegocio(InfInformeVariables entidad) {
        InfInformeVariable negocio = new InfInformeVariable();
        negocio.setId(entidad.getId());
        return negocio;
    }

}
