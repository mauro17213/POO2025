/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.maestro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.ejb.entidades.MaDiagnosticos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.maestro.MaDiagnosticoRemoto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author rpalacios
 */
@Stateless
@Remote(MaDiagnosticoRemoto.class)
public class MaDiagnosticoServicio extends GenericoServicio implements MaDiagnosticoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(d) FROM MaDiagnosticos d "
                    + "WHERE d.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeDiagnosticoCapituloId":
                            strQuery += "AND d.maeDiagnosticoCapituloId = " + (String) e.getValue() + " ";
                            break;
                        case "maeDiagnosticoCapituloValor":
                            strQuery += "AND d.maeDiagnosticoCapituloValor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeDiagnosticoCategoriaId":
                            strQuery += "AND d.maeDiagnosticoCategoriaId = " + (String) e.getValue() + " ";
                            break;
                        case "maeDiagnosticoCategoriaValor":
                            strQuery += "AND d.maeDiagnosticoCategoriaValor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "codigo":
                            strQuery += "AND d.codigo = '" + (String) e.getValue() + "' ";
                            break;
                        case "nombre":
                            strQuery += "AND d.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND d.activo = " + (String) e.getValue() + " ";
                            break;
                        case "sexoAplica":
                            strQuery += "AND d.sexoAplica = " + (String) e.getValue() + " ";
                            break;
                        case "edadInferior":
                            strQuery += "AND d.edadInferior = '" + (String) e.getValue() + "' ";
                            break;
                        case "edadSuperior":
                            strQuery += "AND d.edadSuperior = '" + (String) e.getValue() + "' ";
                            break;
                        case "grupoMortalidad":
                            strQuery += "AND d.grupoMortalidad = '" + (String) e.getValue() + "' ";
                            break;
                        case "grupoMortalidadLista":
                            strQuery += "AND d.grupoMortalidadLista = '" + (String) e.getValue() + "' ";
                            break;
                        case "valorLimiteInferior":
                            strQuery += "AND d.valorLimiteInferior = " + (String) e.getValue() + " ";
                            break;
                        case "valorLimiteSuperior":
                            strQuery += "AND d.valorLimiteSuperior = " + (String) e.getValue() + " ";
                            break;
                        case "excentoCobro":
                            strQuery += "AND d.excentoCobro = " + (String) e.getValue() + " ";
                            break;
                        case "altoCosto":
                            strQuery += "AND d.altoCosto = " + (String) e.getValue() + " ";
                            break;
                        case "priorizarCrue":
                            strQuery += " AND d.priorizarCrue = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
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
    public List<MaDiagnostico> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<MaDiagnostico> listResult = new ArrayList<>();
        try {
            String strQuery = "FROM MaDiagnosticos d "
                    + "WHERE d.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeDiagnosticoCapituloId":
                            strQuery += "AND d.maeDiagnosticoCapituloId = " + (String) e.getValue() + " ";
                            break;
                        case "maeDiagnosticoCapituloValor":
                            strQuery += "AND d.maeDiagnosticoCapituloValor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeDiagnosticoCategoriaId":
                            strQuery += "AND d.maeDiagnosticoCategoriaId = " + (String) e.getValue() + " ";
                            break;
                        case "maeDiagnosticoCategoriaValor":
                            strQuery += "AND d.maeDiagnosticoCategoriaValor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "codigo":
                            strQuery += "AND d.codigo = '" + (String) e.getValue() + "' ";
                            break;
                        case "nombre":
                            strQuery += "AND d.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND d.activo = " + (String) e.getValue() + " ";
                            break;
                        case "sexoAplica":
                            strQuery += "AND d.sexoAplica = " + (String) e.getValue() + " ";
                            break;
                        case "edadInferior":
                            strQuery += "AND d.edadInferior = '" + (String) e.getValue() + "' ";
                            break;
                        case "edadSuperior":
                            strQuery += "AND d.edadSuperior = '" + (String) e.getValue() + "' ";
                            break;
                        case "grupoMortalidad":
                            strQuery += "AND d.grupoMortalidad = '" + (String) e.getValue() + "' ";
                            break;
                        case "grupoMortalidadLista":
                            strQuery += "AND d.grupoMortalidadLista = '" + (String) e.getValue() + "' ";
                            break;
                        case "valorLimiteInferior":
                            strQuery += "AND d.valorLimiteInferior = " + (String) e.getValue() + " ";
                            break;
                        case "valorLimiteSuperior":
                            strQuery += "AND d.valorLimiteSuperior = " + (String) e.getValue() + " ";
                            break;
                        case "excentoCobro":
                            strQuery += "AND d.excentoCobro = " + (String) e.getValue() + " ";
                            break;
                        case "altoCosto":
                            strQuery += "AND d.altoCosto = " + (String) e.getValue() + " ";
                            break;
                        case "priorizarCrue":
                            strQuery += " AND d.priorizarCrue = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "d." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "d.id DESC ";
            }
            List<MaDiagnosticos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (MaDiagnosticos per : list) {
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

    public static MaDiagnostico castEntidadNegocio(MaDiagnosticos ent) {
        MaDiagnostico obj = new MaDiagnostico();

        obj.setActivo(ent.getActivo());
        obj.setAltoCosto(ent.getAltoCosto());
        //obj.setCapituloCodigo(ent.getCapituloCodigo());
        //obj.setCapituloNombre(ent.getCapituloNombre());
        //obj.setCategoriaCodigo(ent.getCategoriaCodigo());
        //obj.setCategoriaNombre(ent.getCategoriaNombre());
        obj.setCodigo(ent.getCodigo());
        obj.setEdadInferior(ent.getEdadInferior());
        obj.setEdadSuperior(ent.getEdadSuperior());
        obj.setExcentoCobro(ent.getExcentoCobro());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setGrupoMortalidad(ent.getGrupoMortalidad());
        obj.setGrupoMortalidadLista(ent.getGrupoMortalidadLista());
        obj.setId(ent.getId());
        obj.setMaeDiagnosticoCapituloCodigo(ent.getMaeDiagnosticoCapituloCodigo());
        obj.setMaeDiagnosticoCapituloId(ent.getMaeDiagnosticoCapituloId());
        obj.setMaeDiagnosticoCapituloValor(ent.getMaeDiagnosticoCapituloValor());
        obj.setMaeDiagnosticoCategoriaCodigo(ent.getMaeDiagnosticoCategoriaCodigo());
        obj.setMaeDiagnosticoCategoriaId(ent.getMaeDiagnosticoCategoriaId());
        obj.setMaeDiagnosticoCategoriaValor(ent.getMaeDiagnosticoCategoriaValor());
        obj.setNombre(ent.getNombre());
        obj.setSexoAplica(ent.getSexoAplica());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setValorLimiteInferior(ent.getValorLimiteInferior());
        obj.setValorLimiteSuperior(ent.getValorLimiteSuperior());
        obj.setPriorizarCrue(ent.getPriorizarCrue());
        //2025-05-06 jyperez nuevo campo
        obj.setMaCacId(ent.getMaCacId());
        obj.setMaCacCodigo(ent.getMaCacCodigo());
        obj.setMaCacValor(ent.getMaCacValor());

        return obj;
    }

    @Override
    public MaDiagnostico consultar(int id) throws Exception {
        MaDiagnostico objRes = null;
        try {
            objRes = castEntidadNegocio((MaDiagnosticos) getEntityManager().find(MaDiagnosticos.class, id));
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
    public int insertar(MaDiagnostico obj) throws Exception {
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
    public void actualizar(MaDiagnostico obj) throws Exception {
        try {
            //getEntityManager().merge(castNegocioEntidad(obj));
            MaDiagnosticos diagnostico = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE MaDiagnosticos a SET ";
            strQuery += " a.maeDiagnosticoCapituloId = :maeDiagnosticoCapituloId ,";
            strQuery += " a.maeDiagnosticoCapituloCodigo = :maeDiagnosticoCapituloCodigo ,";
            strQuery += " a.maeDiagnosticoCapituloValor = :maeDiagnosticoCapituloValor ,";
            strQuery += " a.maeDiagnosticoCategoriaId = :maeDiagnosticoCategoriaId ,";
            strQuery += " a.maeDiagnosticoCategoriaCodigo = :maeDiagnosticoCategoriaCodigo ,";
            strQuery += " a.maeDiagnosticoCategoriaValor = :maeDiagnosticoCategoriaValor ,";
            strQuery += " a.codigo = :codigo ,";
            strQuery += " a.nombre = :nombre ,";
            strQuery += " a.activo = :activo ,";
            strQuery += " a.sexoAplica = :sexoAplica ,";
            strQuery += " a.edadInferior = :edadInferior ,";
            strQuery += " a.edadSuperior = :edadSuperior ,";
            strQuery += " a.grupoMortalidad = :grupoMortalidad ,";
            strQuery += " a.grupoMortalidadLista = :grupoMortalidadLista ,";
            strQuery += " a.valorLimiteInferior = :valorLimiteInferior ,";
            strQuery += " a.valorLimiteSuperior = :valorLimiteSuperior ,";
            strQuery += " a.excentoCobro = :excentoCobro ,";
            strQuery += " a.altoCosto = :altoCosto ,";
            strQuery += " a.priorizarCrue = :priorizarCrue ,";
            //2025-05-06 jyperez nuevo campo
            strQuery += " a.maCacId = :maCacId ,";
            strQuery += " a.maCacCodigo = :maCacCodigo ,";
            strQuery += " a.maCacValor = :maCacValor ,";
            //auditoria
            strQuery += " a.usuarioModifica = :usuarioModifica ,";
            strQuery += " a.terminalModifica = :terminalModifica ,";
            SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaModifica = formatoFechaHora.format(obj.getFechaHoraModifica());
            strQuery += " a.fechaHoraModifica = '" + fechaModifica + "' ";
            //objetos
            
            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(diagnostico);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public MaDiagnostico eliminar(int id) throws Exception {
        MaDiagnostico obj = null;
        try {
            MaDiagnosticos ent = getEntityManager().find(MaDiagnosticos.class, id);
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
    public List<MaDiagnostico> consultarTodos() throws Exception {
        List<MaDiagnostico> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaDiagnosticos m "
                    + "ORDER BY m.id ";
            List<MaDiagnosticos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaDiagnosticos per : list) {
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
    public HashMap<String, MaDiagnostico> consultarTodosRips() throws Exception {
        HashMap<String, MaDiagnostico> hashResult = new HashMap<>();
        try {
            String strQuery = "SELECT d FROM MaDiagnosticos d "
                    + "WHERE d.activo = :activo ";
            Set<MaDiagnosticos> set = new HashSet<>(getEntityManager().createQuery(strQuery)
                    .setParameter("activo", true)
                    .getResultList());
            for (MaDiagnosticos per : set) {
                hashResult.put(per.getCodigo(),castEntidadNegocioCorto(per));
            }
        } catch (NoResultException e) {
            hashResult = new HashMap();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return hashResult;
    }

    public static MaDiagnostico castEntidadNegocioCorto(MaDiagnosticos ent) {
        MaDiagnostico obj = new MaDiagnostico();
        obj.setCodigo(ent.getCodigo());
        obj.setId(ent.getId());
        obj.setNombre(ent.getNombre());
        return obj;
    }

    public static MaDiagnosticos castNegocioEntidad(MaDiagnostico obj) {
        MaDiagnosticos per = new MaDiagnosticos();
        per.setId(obj.getId());
        per.setCodigo(obj.getCodigo());
        per.setNombre(obj.getNombre());
        per.setActivo(obj.isActivo());
        per.setAltoCosto(obj.isAltoCosto());
        //per.setCapituloCodigo(obj.getCapituloCodigo());
        //per.setCapituloNombre(obj.getCapituloNombre());
        //per.setCategoriaCodigo(obj.getCategoriaCodigo());
        //per.setCategoriaNombre(obj.getCategoriaNombre());
        per.setEdadInferior(obj.getEdadInferior());
        per.setEdadSuperior(obj.getEdadSuperior());
        per.setExcentoCobro(obj.getExcentoCobro());
        per.setGrupoMortalidad(obj.getGrupoMortalidad());
        per.setGrupoMortalidadLista(obj.getGrupoMortalidadLista());
        per.setId(obj.getId());
        per.setMaeDiagnosticoCapituloCodigo(obj.getMaeDiagnosticoCapituloCodigo());
        per.setMaeDiagnosticoCapituloId(obj.getMaeDiagnosticoCapituloId());
        per.setMaeDiagnosticoCapituloValor(obj.getMaeDiagnosticoCapituloValor());
        per.setMaeDiagnosticoCategoriaCodigo(obj.getMaeDiagnosticoCategoriaCodigo());
        per.setMaeDiagnosticoCategoriaId(obj.getMaeDiagnosticoCategoriaId());
        per.setMaeDiagnosticoCategoriaValor(obj.getMaeDiagnosticoCategoriaValor());
        per.setSexoAplica(obj.getSexoAplica());
        per.setValorLimiteInferior(obj.getValorLimiteInferior());
        per.setValorLimiteSuperior(obj.getValorLimiteSuperior());
        per.setPriorizarCrue(obj.getPriorizarCrue());
        //2025-05-06 jyperez nuevo campo
        per.setMaCacId(obj.getMaCacId());
        per.setMaCacCodigo(obj.getMaCacCodigo());
        per.setMaCacValor(obj.getMaCacValor());
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
    public int consultarCantidadListaBuscador(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(d) FROM MaDiagnosticos d "
                    + "WHERE d.activo = :activo ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeDiagnosticoCapituloValor":
                            strQuery += "AND d.maeDiagnosticoCapituloValor like '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeDiagnosticoCategoriaValor":
                            strQuery += "AND d.maeDiagnosticoCategoriaValor like '%" + (String) e.getValue() + "%' ";
                            break;
                        case "codigo":
                            strQuery += "AND d.codigo = '" + (String) e.getValue() + "' ";
                            break;
                        case "nombre":
                            strQuery += "AND d.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;                        
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("activo", true)
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
    public List<MaDiagnostico> consultarListaBuscador(ParamConsulta paramConsulta) throws Exception {
        List<MaDiagnostico> listResult = new ArrayList<>();
        try {
           String strQuery = "SELECT d FROM MaDiagnosticos d "
                    + "WHERE d.activo = :activo ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                         case "maeDiagnosticoCapituloValor":
                            strQuery += "AND d.maeDiagnosticoCapituloValor like '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeDiagnosticoCategoriaValor":
                            strQuery += "AND d.maeDiagnosticoCategoriaValor like '%" + (String) e.getValue() + "%' ";
                            break;
                        case "codigo":
                            strQuery += "AND d.codigo = '" + (String) e.getValue() + "' ";
                            break;
                        case "nombre":
                            strQuery += "AND d.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;                        
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "d." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "d.id";
            }
            List<MaDiagnosticos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .setParameter("activo", true)
                    .getResultList();
            for (MaDiagnosticos per : list) {
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
    public MaDiagnostico consultarPorCodigo(String codigo) throws Exception {
        MaDiagnostico objectoResultado = new MaDiagnostico();
        try {
            String strQuery = "SELECT m FROM MaDiagnosticos m WHERE m.codigo = :codigo "
                    + "ORDER BY m.id ";
            List<MaDiagnosticos> list = getEntityManager().createQuery(strQuery)
                    .setParameter("codigo", codigo)
                    .setMaxResults(1)
                    .getResultList();
            for (MaDiagnosticos per : list) {
                objectoResultado = castEntidadNegocio(per);
            }
        } catch (NoResultException e) {
            objectoResultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objectoResultado;
    }

    @Override
    public HashMap<String, MaDiagnostico> consultarHash() throws Exception {
        HashMap<String,MaDiagnostico> hashResult = new HashMap();
        try {
            String strQuery = "FROM MaDiagnosticos m "
                    + "ORDER BY m.id ";
            List<MaDiagnosticos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaDiagnosticos per : list) {
                MaDiagnostico obj = castEntidadNegocio(per);
                hashResult.put(obj.getCodigo(), obj);
            }
        } catch (NoResultException e) {
            hashResult = new HashMap();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return hashResult;
    }

    @Override
    public List<MaDiagnostico> consultarTodoSingleton() throws Exception {
        List<MaDiagnostico> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t.id, "
                    + "t.activo, "
                    + "t.codigo, "
                    + "t.nombre "
                    + "FROM MaDiagnosticos t ORDER by t.id ";
            Query q = getEntityManager().createQuery(strQuery);
            List<Object[]> lista = q.getResultList();
            for (Object[] per : lista) {
                MaDiagnostico diagnostico = new MaDiagnostico();
                diagnostico.setId((Integer) per[0]);
                diagnostico.setActivo((Boolean) per[1]);
                diagnostico.setCodigo(per[2].toString());
                diagnostico.setNombre(per[3].toString());
                listResult.add(diagnostico);
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
}
