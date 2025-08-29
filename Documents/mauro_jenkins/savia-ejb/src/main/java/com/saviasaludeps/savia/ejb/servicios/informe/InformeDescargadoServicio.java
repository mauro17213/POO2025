package com.saviasaludeps.savia.ejb.servicios.informe;

import com.saviasaludeps.savia.dominio.informe.InfDescargado;
import com.saviasaludeps.savia.dominio.informe.InfGenerado;
import com.saviasaludeps.savia.dominio.informe.InfGrupoUsuario;
import com.saviasaludeps.savia.ejb.entidades.InfInformeDescargados;
import com.saviasaludeps.savia.ejb.entidades.InfInformeGenerados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.informe.InformeDescargadoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
@Remote(InformeDescargadoRemoto.class)
public class InformeDescargadoServicio extends GenericoServicio implements InformeDescargadoRemoto {

    @Override
    public int insertar(InfDescargado obj) throws Exception {
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
    public List<InfDescargado> listarPorGenerado(int id) throws Exception {
        List<InfDescargado> listResult = new ArrayList();
        try {
            String strQuery = "FROM InfInformeDescargados d "
                    + "WHERE d.infInformeGeneradosId.id = " + id + " ";
            strQuery += "ORDER BY d.id desc ";
            List<InfInformeDescargados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (InfInformeDescargados ent : list) {
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

    private InfDescargado castEntidadNegocio(InfInformeDescargados ent) {
        InfDescargado neg = new InfDescargado();
        neg.setFechaDescarga(ent.getFechaDescarga());
        neg.setInfGrupoUsuario(new InfGrupoUsuario(ent.getInfUsuarioId()));
        neg.setInfInformeGenerado(new InfGenerado(ent.getInfInformeGeneradosId().getId()));
        neg.setEmpresaNombre(ent.getEmpresaNombre());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        return neg;
    }

    private InfInformeDescargados castNegocioEntidad(InfDescargado neg) {
        InfInformeDescargados ent = new InfInformeDescargados();
        ent.setFechaDescarga(neg.getFechaDescarga());
        ent.setInfInformeGeneradosId(new InfInformeGenerados(neg.getInfInformeGenerado().getId()));
        ent.setInfUsuarioId(neg.getInfGrupoUsuario().getId());
        ent.setEmpresaNombre(neg.getEmpresaNombre());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        return ent;
    }

}
