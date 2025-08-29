package com.saviasaludeps.savia.ejb.servicios.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoAdjunto;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.ejb.entidades.CntContratoAdjuntos;
import com.saviasaludeps.savia.ejb.entidades.CntContratos;
import javax.persistence.Query;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoAdjuntoRemoto;

@Stateless
@Remote(CntContratoAdjuntoRemoto.class)
public class CntContratoAdjuntoServicio extends GenericoServicio implements CntContratoAdjuntoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntContratoAdjuntos c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeTipoArchivoId":
                            strQuery += "AND c.maeTipoArchivoId = " + (Integer) e.getValue() + " ";
                            break;
                        case "maeTipoArchivoCodigo":
                            strQuery += "AND c.maeTipoArchivoCodigo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeTipoArchivoValor":
                            strQuery += "AND c.maeTipoArchivoValor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += "AND c.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "ruta":
                            strQuery += "AND c.ruta LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "archivo":
                            strQuery += "AND c.archivo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "existe":
                            strQuery += "AND c.existe = " + (boolean) e.getValue() + " ";
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
    public List<CntContratoAdjunto> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CntContratoAdjunto> listResult = new ArrayList();
        try {
            String strQuery = "SELECT c FROM CntContratoAdjuntos c "
                    + "WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeTipoArchivoId":
                            strQuery += "AND c.maeTipoArchivoId = " + (Integer) e.getValue() + " ";
                            break;
                        case "maeTipoArchivoCodigo":
                            strQuery += "AND c.maeTipoArchivoCodigo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeTipoArchivoValor":
                            strQuery += "AND c.maeTipoArchivoValor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += "AND c.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "ruta":
                            strQuery += "AND c.ruta LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "archivo":
                            strQuery += "AND c.archivo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "existe":
                            strQuery += "AND c.existe = " + (boolean) e.getValue() + " ";
                            break;
                    }
                }
            }
            //Query query = getEntityManager().createQuery(strQuery);
            List<CntContratoAdjuntos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntContratoAdjuntos per : list) {
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

    private CntContratoAdjunto castEntidadNegocio(CntContratoAdjuntos per) {
        CntContratoAdjunto neg = new CntContratoAdjunto();
        neg.setId(per.getId());
        neg.setMaeTipoArchivoId(per.getMaeTipoArchivoId());
        neg.setMaeTipoArchivoCodigo(per.getMaeTipoArchivoCodigo());
        neg.setMaeTipoArchivoValor(per.getMaeTipoArchivoValor());
        neg.setNombre(per.getNombre());
        neg.setRuta(per.getRuta());
        neg.setArchivo(per.getArchivo());
        neg.setExiste(per.getExiste());
        //objetos
        if(per.getCntContratosId() != null) {
            neg.setCntContrato(new CntContrato(per.getCntContratosId().getId()));
        }
        //auditoria
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        //neg.setUsuarioModifica(per.getUsuarioModifica());
        //neg.setTerminalModifica(per.getTerminalModifica());
        //neg.setUsuarioModifica(per.getUsuarioModifica());
        return neg;
    }

    @Override
    public CntContratoAdjunto consultar(int id) throws Exception {
        CntContratoAdjunto objRes = null;
        try {
            objRes = castEntidadNegocio((CntContratoAdjuntos) getEntityManager().find(CntContratoAdjuntos.class, id));
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
    public int insertar(CntContratoAdjunto obj) throws Exception {
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
    public void actualizar(CntContratoAdjunto obj) throws Exception {
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
    public CntContratoAdjunto eliminar(int id) throws Exception {
        CntContratoAdjunto obj = null;
        try {
            CntContratoAdjuntos ent = getEntityManager().find(CntContratoAdjuntos.class, id);
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
    public List<CntContratoAdjunto> consultarTodos() throws Exception {
        List<CntContratoAdjunto> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntContratoAdjuntos p "
                    + "ORDER BY p.id ";
            List<CntContratoAdjuntos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntContratoAdjuntos per : list) {
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

    public static CntContratoAdjuntos castNegocioEntidad(CntContratoAdjunto obj) {
        CntContratoAdjuntos per = new CntContratoAdjuntos();
        per.setId(obj.getId());
        per.setMaeTipoArchivoId(obj.getMaeTipoArchivoId());
        per.setMaeTipoArchivoCodigo(obj.getMaeTipoArchivoCodigo());
        per.setMaeTipoArchivoValor(obj.getMaeTipoArchivoValor());
        per.setNombre(obj.getNombre());
        per.setRuta(obj.getRuta());
        per.setArchivo(obj.getArchivo());
        per.setExiste(obj.isExiste());
        //objetos
        if(obj.getCntContrato() != null) {
            per.setCntContratosId(new CntContratos(obj.getCntContrato().getId()));
        }
        //auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        //per.setFechaHoraModifica(obj.getFechaHoraModifica());
        //per.setTerminalModifica(obj.getTerminalModifica());
        //per.setUsuarioModifica(obj.getUsuarioModifica());
        return per;
    }
}
