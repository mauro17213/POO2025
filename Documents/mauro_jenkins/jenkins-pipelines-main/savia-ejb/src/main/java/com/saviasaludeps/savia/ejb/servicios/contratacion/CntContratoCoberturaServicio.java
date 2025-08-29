package com.saviasaludeps.savia.ejb.servicios.contratacion;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoCobertura;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoSede;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.ejb.entidades.CntContratoCoberturas;
import com.saviasaludeps.savia.ejb.entidades.CntContratoSedes;
import com.saviasaludeps.savia.ejb.entidades.CntContratos;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import javax.persistence.Query;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoCoberturaRemoto;

@Stateless
@Remote(CntContratoCoberturaRemoto.class)
public class CntContratoCoberturaServicio extends GenericoServicio implements CntContratoCoberturaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntContratoCoberturas c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id = " + (Integer) e.getValue() + " ";
                            break;
                    }
                }
            }
            Query query = getEntityManager().createQuery(strQuery);
            cant = (int) (long) query.getSingleResult();
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
    public List<CntContratoCobertura> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CntContratoCobertura> listResult = new ArrayList();
        try {
            String strQuery = "SELECT c FROM CntContratoCoberturas c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND c.id = " + (Integer) e.getValue() + " ";
                            break;
                    }
                }
            }
            //Query query = getEntityManager().createQuery(strQuery);
            List<CntContratoCoberturas> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntContratoCoberturas per : list) {
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

    private CntContratoCobertura castEntidadNegocio(CntContratoCoberturas per) {
        CntContratoCobertura neg = new CntContratoCobertura();
        neg.setId(per.getId());
        neg.setActivo(per.getActivo());
        //objetos
        if(per.getCntContratosId() != null) {
            neg.setCntContrato(new CntContrato(per.getCntContratosId().getId()));
        }
        if(per.getCntContratoSedesId()!= null) {
            neg.setCntContratoSede(new CntContratoSede(per.getCntContratoSedesId().getId()));
        }
        if (per.getCntPrestadorSedesId() != null) {
            neg.setCntPrestadorSede(new CntPrestadorSede(per.getCntPrestadorSedesId().getId()));
        }
        if (per.getGnUbicacionesId() != null) {
            neg.setUbicacion(new Ubicacion(null,per.getGnUbicacionesId().getId(),per.getGnUbicacionesId().getTipo(),"",per.getGnUbicacionesId().getNombre()));
        }
        //auditoria
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        neg.setUsuarioModifica(per.getUsuarioModifica());
        neg.setTerminalModifica(per.getTerminalModifica());
        neg.setUsuarioModifica(per.getUsuarioModifica());
        return neg;
    }

    @Override
    public CntContratoCobertura consultar(int id) throws Exception {
        CntContratoCobertura objRes = null;
        try {
            objRes = castEntidadNegocio((CntContratoCoberturas) getEntityManager().find(CntContratoCoberturas.class, id));
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
    public int insertar(CntContratoCobertura obj) throws Exception {
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
    public void actualizar(CntContratoCobertura obj) throws Exception {
        try {
            getEntityManager().merge(castNegocioEntidad(obj));
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CntContratoCobertura eliminar(int id) throws Exception {
        CntContratoCobertura obj = null;
        try {
            CntContratoCoberturas ent = getEntityManager().find(CntContratoCoberturas.class, id);
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
    public List<CntContratoCobertura> consultarTodos() throws Exception {
        List<CntContratoCobertura> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntContratoCoberturas c "
                    + "ORDER BY c.id ";
            List<CntContratoCoberturas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntContratoCoberturas per : list) {
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

    public static CntContratoCoberturas castNegocioEntidad(CntContratoCobertura obj) {
        CntContratoCoberturas per = new CntContratoCoberturas();
        per.setId(obj.getId());
        per.setActivo(obj.isActivo());
        //objetos
        if(obj.getCntContrato() != null) {
            per.setCntContratosId(new CntContratos(obj.getCntContrato().getId()));
        }
        if(obj.getCntContratoSede()!= null) {
            per.setCntContratoSedesId(new CntContratoSedes(obj.getCntContratoSede().getId()));
        }
        if(obj.getCntPrestadorSede()!= null) {
            per.setCntPrestadorSedesId(new CntPrestadorSedes(obj.getCntPrestadorSede().getId()));
        }
        if(obj.getUbicacion()!= null) {
            per.setGnUbicacionesId(new GnUbicaciones(obj.getUbicacion().getId()));
        }
        //auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        return per;
    }

    @Override
    public boolean eliminarRegistrosAsociados(int CntContratoSedeId) throws Exception {
        boolean estado = false;
        try {
            String strQuery = "DELETE FROM CntContratoCoberturas c "
                    + "WHERE c.cntContratoSedesId.id = " + CntContratoSedeId;
            int var = getEntityManager().createQuery(strQuery)
                    .executeUpdate();
            estado = true;
        } catch (NoResultException e) {
            estado = false;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return estado;
    }
}
