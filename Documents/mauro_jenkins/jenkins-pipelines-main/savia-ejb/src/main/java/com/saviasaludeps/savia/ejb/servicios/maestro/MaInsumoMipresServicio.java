/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.maestro;


import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.maestro.MaInsumoMipres;
import com.saviasaludeps.savia.dominio.mipres.MpCodigoInsumo;
import com.saviasaludeps.savia.ejb.entidades.MaInsumos;
import com.saviasaludeps.savia.ejb.entidades.MaInsumosMipres;
import com.saviasaludeps.savia.ejb.entidades.MpCodigoInsumos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.maestro.MaInsumoMipresRemoto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Session;

/**
 *
 * @author jyperez
 */
@Stateless
@Remote(MaInsumoMipresRemoto.class)
public class MaInsumoMipresServicio extends GenericoServicio implements MaInsumoMipresRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaInsumosMipres p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND p.id = " + e.getValue() + " ";
                            break;
                        case "codigoMipres":
                            strQuery += " AND p.codigoMipres LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcionMipres":
                            strQuery += " AND p.descripcionMipres  LIKE '%" + e.getValue() + "%' ";
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
    public List<MaInsumoMipres> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<MaInsumoMipres> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaInsumosMipres p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND p.id = " + e.getValue() + " ";
                            break;
                        case "codigoMipres":
                            strQuery += " AND p.codigoMipres LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcionMipres":
                            strQuery += " AND p.descripcionMipres  LIKE '%" + e.getValue() + "%' ";
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
            List<MaInsumosMipres> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (MaInsumosMipres per : list) {
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
    public MaInsumoMipres consultar(int id) throws Exception {
        MaInsumoMipres objRes = null;
        try {
            objRes = castEntidadNegocio((MaInsumosMipres) getEntityManager().find(MaInsumosMipres.class, id));
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
    public int insertar(MaInsumoMipres obj) throws Exception {
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
    public void actualizar(MaInsumoMipres obj) throws Exception {
        try {
            //getEntityManager().merge(castNegocioEntidad(obj));
            MaInsumosMipres insumoMipres = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            //session.update(per);
            String strQuery = "UPDATE MaInsumosMipres a SET ";
            strQuery += " a.codigoMipres = :codigoMipres ,";
            strQuery += " a.descripcionMipres = :descripcionMipres ,";
            //campos auditoria
            strQuery += " a.usuarioModifica = :usuarioModifica ,";
            strQuery += " a.terminalModifica = :terminalModifica ,";
            //campo fechas
            //SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaModifica = formatoFechaHora.format(obj.getFechaHoraModifica());
            strQuery += " a.fechaHoraModifica = '" + fechaModifica + "' ";
            //campos objetos
            if (insumoMipres.getInsumosMipresId() != null) {
                strQuery += ", a.insumosMipresId.id = " + insumoMipres.getInsumosMipresId().getId() + " ";
            }
            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(insumoMipres);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public MaInsumoMipres eliminar(int id) throws Exception {
        MaInsumoMipres obj = null;
        try {
            MaInsumosMipres ent = getEntityManager().find(MaInsumosMipres.class, id);
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
    public List<MaInsumoMipres> consultarTodos() throws Exception {
        List<MaInsumoMipres> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaInsumosMipres p "
                    + "ORDER BY p.id ";
            List<MaInsumosMipres> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaInsumosMipres per : list) {
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

    public static MaInsumoMipres castEntidadNegocio(MaInsumosMipres per) {
        MaInsumoMipres obj = new MaInsumoMipres();
        obj.setId(per.getId());
        obj.setCodigoMipres(per.getCodigoMipres());
        obj.setDescripcionMipres(per.getDescripcionMipres());
        //objeto
        if(per.getMaInsumosId() != null) {
            obj.setMaInsumo(new MaInsumo(per.getMaInsumosId().getId()));
        }
        if(per.getInsumosMipresId() != null) {
            obj.setMpCodigoInsumo(new MpCodigoInsumo(per.getInsumosMipresId().getId()));
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

    public static MaInsumosMipres castNegocioEntidad(MaInsumoMipres obj) {
        MaInsumosMipres per = new MaInsumosMipres();
        per.setId(obj.getId());
        per.setCodigoMipres(obj.getCodigoMipres());
        per.setDescripcionMipres(obj.getDescripcionMipres());
        //objetos
        if (obj.getMaInsumo() != null) {
            per.setMaInsumosId(new MaInsumos(obj.getMaInsumo().getId()));
        }
        if(obj.getMpCodigoInsumo()!= null) {
            per.setInsumosMipresId(new MpCodigoInsumos(obj.getMpCodigoInsumo().getId()));
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
    public MaInsumoMipres consultarPorCodigo(String codigo) throws java.lang.Exception {
        MaInsumoMipres objetoRes = null;
        int i = 0;
        try {
            String strQuery = "FROM MaInsumosMipres p "
                    + " WHERE p.codigoMipres = '" + codigo +"' ";
            List<MaInsumosMipres> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaInsumosMipres per : list) {
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
    public MaInsumoMipres consultarPorTecnologiaYCodigoMipres(int idTecnologia, String codigo) throws java.lang.Exception {
        MaInsumoMipres objetoRes = null;
        int i = 0;
        try {
            String strQuery = "FROM MaInsumosMipres p "
                    + " WHERE p.codigoMipres = '" + codigo +"' "
                    + " AND p.maInsumosId.id = " + idTecnologia +" ";
            List<MaInsumosMipres> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaInsumosMipres per : list) {
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
    public MaInsumoMipres consultarPorCodigoTecnologiaYCodigoMipres(String codigoTecnologia,String codigoMipres) throws java.lang.Exception {
        MaInsumoMipres objetoRes = null;
        int i = 0;
        try {
            String strQuery = "FROM MaInsumosMipres p "
                    + " WHERE p.codigoMipres = '" + codigoMipres +"' "
                    + " AND p.maInsumosId.codigo = '" + codigoTecnologia +"' ";
            List<MaInsumosMipres> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaInsumosMipres per : list) {
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
            String strQuery = "SELECT COUNT(p) FROM MaInsumosMipres p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND p.id = " + e.getValue() + " ";
                            break;
                        case "codigoMipres":
                            strQuery += " AND p.codigoMipres LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcionMipres":
                            strQuery += " AND p.descripcionMipres  LIKE '%" + e.getValue() + "%' ";
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
    public List<MaInsumoMipres> consultarListaBuscador(ParamConsulta paramConsulta) throws Exception {
        List<MaInsumoMipres> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaInsumosMipres p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND p.id = " + e.getValue() + " ";
                            break;
                        case "codigoMipres":
                            strQuery += " AND p.codigoMipres LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcionMipres":
                            strQuery += " AND p.descripcionMipres  LIKE '%" + e.getValue() + "%' ";
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
            List<MaInsumosMipres> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (MaInsumosMipres per : list) {
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
    
}
