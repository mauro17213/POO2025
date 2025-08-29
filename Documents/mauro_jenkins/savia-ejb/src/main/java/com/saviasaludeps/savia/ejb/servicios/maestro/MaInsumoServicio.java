/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.maestro;


import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaInsumoMipres;
import com.saviasaludeps.savia.dominio.mipres.MpCodigoInsumo;
import com.saviasaludeps.savia.ejb.entidades.MaInsumos;
import com.saviasaludeps.savia.ejb.entidades.MaInsumosMipres;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.maestro.MaInsumoRemoto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author jyperez
 */
@Stateless
@Remote(MaInsumoRemoto.class)
public class MaInsumoServicio extends GenericoServicio implements MaInsumoRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaInsumos p "
                    + "WHERE p.automatico = FALSE ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeTipoId":
                            strQuery += " AND p.maeTipoId = " + e.getValue() + " ";
                            break;
                        case "maeTipoCodigo":
                            strQuery += " AND p.maeTipoCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoValor":
                            strQuery += " AND p.maeTipoValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "codigo":
                            strQuery += " AND p.codigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += " AND p.descripcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "abreviatura":
                            strQuery += " AND p.abreviatura  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                        case "cobertura":
                            strQuery += " AND p.cobertura = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public List<MaInsumo> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<MaInsumo> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaInsumos p "
                    + "WHERE p.automatico = FALSE ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeTipoId":
                            strQuery += " AND p.maeTipoId = " + e.getValue() + " ";
                            break;
                        case "maeTipoCodigo":
                            strQuery += " AND p.maeTipoCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoValor":
                            strQuery += " AND p.maeTipoValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "codigo":
                            strQuery += " AND p.codigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += " AND p.descripcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "abreviatura":
                            strQuery += " AND p.abreviatura  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                        case "cobertura":
                            strQuery += " AND p.cobertura = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<MaInsumos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (MaInsumos per : list) {
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
    public MaInsumo consultar(int id) throws Exception {
        MaInsumo objRes = null;
        try {
            objRes = castEntidadNegocio((MaInsumos) getEntityManager().find(MaInsumos.class, id));
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
    public int insertar(MaInsumo obj) throws Exception {
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
    public void actualizar(MaInsumo obj) throws Exception {
        try {
            //getEntityManager().merge(castNegocioEntidad(obj));
            MaInsumos insumo = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            //session.update(per);
            String strQuery = "UPDATE MaInsumos a SET ";
            strQuery += " a.maeTipoId = :maeTipoId ,";
            strQuery += " a.maeTipoCodigo = :maeTipoCodigo ,";
            strQuery += " a.maeTipoValor = :maeTipoValor ,";
            strQuery += " a.automatico = :automatico ,";
            strQuery += " a.codigoHabilitacion = :codigoHabilitacion ,";
            strQuery += " a.codigo = :codigo ,";
            strQuery += " a.descripcion = :descripcion ,";
            strQuery += " a.abreviatura = :abreviatura ,";
            strQuery += " a.activo = :activo ,";
            strQuery += " a.cobertura = :cobertura ,";
            //campos auditoria
            strQuery += " a.usuarioModifica = :usuarioModifica ,";
            strQuery += " a.terminalModifica = :terminalModifica ,";
            //campo fechas
            //SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaModifica = formatoFechaHora.format(obj.getFechaHoraModifica());
            strQuery += " a.fechaHoraModifica = '" + fechaModifica + "' ";
            //campos objetos
            
            
            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(insumo);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public MaInsumo eliminar(int id) throws Exception {
        MaInsumo obj = null;
        try {
            MaInsumos ent = getEntityManager().find(MaInsumos.class, id);
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
    public List<MaInsumo> consultarTodos() throws Exception {
        List<MaInsumo> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaInsumos p "
                    + "ORDER BY p.id ";
            List<MaInsumos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaInsumos per : list) {
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

    public static MaInsumo castEntidadNegocio(MaInsumos per) {
        MaInsumo obj = new MaInsumo();
        obj.setId(per.getId());
        obj.setMaeTipoId(per.getMaeTipoId());
        obj.setMaeTipoCodigo(per.getMaeTipoCodigo());
        obj.setMaeTipoValor(per.getMaeTipoValor());
        obj.setCodigo(per.getCodigo());
        obj.setDescripcion(per.getDescripcion());
        obj.setAbreviatura(per.getAbreviatura());
        obj.setActivo(per.getActivo());
        obj.setCobertura(per.getCobertura());
        obj.setAutomatico(per.getAutomatico());
        obj.setCodigoHabilitacion(per.getCodigoHabilitacion());
        //objetos
        if (per.getMaInsumosMipresList() != null) {
            
            List<MaInsumoMipres> list = new ArrayList();
            for (MaInsumosMipres aux: per.getMaInsumosMipresList()) {
                MaInsumoMipres tecmp = castInsumoMipresEntidadNegocio(aux);
                list.add(tecmp);
            }
            obj.setListaInsumoMipres(list);
        }
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }

    public static MaInsumos castNegocioEntidad(MaInsumo obj) {
        MaInsumos per = new MaInsumos();
        per.setId(obj.getId());
        per.setMaeTipoId(obj.getMaeTipoId());
        per.setMaeTipoCodigo(obj.getMaeTipoCodigo());
        per.setMaeTipoValor(obj.getMaeTipoValor());
        per.setCodigo(obj.getCodigo());
        per.setDescripcion(obj.getDescripcion().trim());
        per.setAbreviatura(obj.getAbreviatura());
        per.setActivo(obj.isActivo());
        per.setCobertura(obj.getCobertura());
        per.setAutomatico(obj.isAutomatico());
        per.setCodigoHabilitacion(obj.getCodigoHabilitacion());
        //auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        return per;
    }
    
        public static MaInsumoMipres castInsumoMipresEntidadNegocio(MaInsumosMipres per) {
        MaInsumoMipres obj = new MaInsumoMipres();
        obj.setId(per.getId());
        obj.setCodigoMipres(per.getCodigoMipres());
        obj.setDescripcionMipres(per.getDescripcionMipres());
        //objeto
        if(per.getMaInsumosId() != null) {
            obj.setMaInsumo(new MaInsumo(per.getMaInsumosId().getId()));
        }
        if(per.getInsumosMipresId() != null) {
            MpCodigoInsumo cod = new MpCodigoInsumo(per.getInsumosMipresId().getId());
            //cod.setMaeInsumoId(per.getInsumosMipresId().getMaeInsumoId());
            cod.setCodigoMipres(per.getInsumosMipresId().getCodigoMipres());
            cod.setDescripcion(per.getInsumosMipresId().getDescripcion());
            cod.setActivo(per.getInsumosMipresId().getActivo());
            cod.setVersionMipres(per.getInsumosMipresId().getVersionMipres());
            //pendiente si necesitamos auditoria pero no se considera
            obj.setMpCodigoInsumo(cod);
        }
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }


    @Override
    public MaInsumo consultarPorCodigo(String codigo) throws java.lang.Exception {
        MaInsumo objetoRes = null;
        int i = 0;
        try {
            String strQuery = "FROM MaInsumos p "
                    + " WHERE p.codigo = '" + codigo +"' "
                    + " AND p.automatico = FALSE ";
            List<MaInsumos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaInsumos per : list) {
                if (i==0) {
                    objetoRes =castEntidadNegocio(per);
                    i++;
                }
            }
        } catch (NoResultException e) {
            objetoRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objetoRes;
    }
    
    @Override
    public int consultarCantidadListaBuscador(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaInsumos p "
                    + "WHERE p.automatico = FALSE ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND p.activo = " + (String) paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeTipoId":
                            strQuery += " AND p.maeTipoId = " + e.getValue() + " ";
                            break;
                        case "maeTipoCodigo":
                            strQuery += " AND p.maeTipoCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoValor":
                            strQuery += " AND p.maeTipoValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "codigo":
                            strQuery += " AND p.codigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += " AND p.descripcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "abreviatura":
                            strQuery += " AND p.abreviatura  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                        case "cobertura":
                            strQuery += " AND p.cobertura = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public List<MaInsumo> consultarListaBuscador(ParamConsulta paramConsulta) throws Exception {
        List<MaInsumo> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaInsumos p "
                    + "WHERE p.automatico = FALSE ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND p.activo = " + (String) paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeTipoId":
                            strQuery += " AND p.maeTipoId = " + e.getValue() + " ";
                            break;
                        case "maeTipoCodigo":
                            strQuery += " AND p.maeTipoCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoValor":
                            strQuery += " AND p.maeTipoValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "codigo":
                            strQuery += " AND p.codigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcion":
                            strQuery += " AND p.descripcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "abreviatura":
                            strQuery += " AND p.abreviatura  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                        case "cobertura":
                            strQuery += " AND p.cobertura = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<MaInsumos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (MaInsumos per : list) {
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
    public HashMap<String, MaInsumo> consultarHash() throws Exception {
        HashMap<String,MaInsumo> hashResult = new HashMap();
        try {
            String strQuery = "FROM MaInsumos m "
                    + "ORDER BY m.id ";
            List<MaInsumos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaInsumos per : list) {
                MaInsumo obj = castEntidadNegocio(per);
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
    public List<MaInsumo> consultarTodoSingleton() throws Exception {
        List<MaInsumo> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t.id, "
                    + "t.activo, "
                    + "t.codigo, "
                    + "t.descripcion "
                    + "FROM MaInsumos t "
                    + "WHERE t.automatico = 0 "
                    + "ORDER BY t.id ";
            Query q = getEntityManager().createQuery(strQuery);
            List<Object[]> lista = q.getResultList();
            for (Object[] per : lista) {
                MaInsumo insumo = new MaInsumo();
                insumo.setId((Integer) per[0]);
                insumo.setActivo((Boolean) per[1]);
                insumo.setCodigo(per[2].toString());
                insumo.setDescripcion(per[3].toString());
                listResult.add(insumo);
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
