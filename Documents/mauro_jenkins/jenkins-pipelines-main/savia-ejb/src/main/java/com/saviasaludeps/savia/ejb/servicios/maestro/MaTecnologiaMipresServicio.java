/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.maestro;


import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologiaMipres;
import com.saviasaludeps.savia.dominio.mipres.MpCodigoInsumo;
import com.saviasaludeps.savia.ejb.entidades.MaTecnologias;
import com.saviasaludeps.savia.ejb.entidades.MaTecnologiasMipres;
import com.saviasaludeps.savia.ejb.entidades.MpCodigoInsumos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.maestro.MaTecnologiaMipresRemoto;
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
@Remote(MaTecnologiaMipresRemoto.class)
public class MaTecnologiaMipresServicio extends GenericoServicio implements MaTecnologiaMipresRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaTecnologiasMipres p "
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
                        case "descripcion":
                            strQuery += " AND p.descripcion LIKE '%" + e.getValue() + "%' ";
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
    public List<MaTecnologiaMipres> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<MaTecnologiaMipres> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaTecnologiasMipres p "
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
                        case "descripcion":
                            strQuery += " AND p.descripcion  LIKE '%" + e.getValue() + "%' ";
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
            List<MaTecnologiasMipres> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (MaTecnologiasMipres per : list) {
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
    public MaTecnologiaMipres consultar(int id) throws Exception {
        MaTecnologiaMipres objRes = null;
        try {
            objRes = castEntidadNegocio((MaTecnologiasMipres) getEntityManager().find(MaTecnologiasMipres.class, id));
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
    public int insertar(MaTecnologiaMipres obj) throws Exception {
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
    public void actualizar(MaTecnologiaMipres obj) throws Exception {
        try {
            //getEntityManager().merge(castNegocioEntidad(obj));
            MaTecnologiasMipres tecnologiaMipres = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            //session.update(per);
            String strQuery = "UPDATE MaTecnologiasMipres a SET ";
            strQuery += " a.codigoMipres = :codigoMipres ,";
            strQuery += " a.descripcion = :descripcion ,";
            //campos auditoria
            strQuery += " a.usuarioModifica = :usuarioModifica ,";
            strQuery += " a.terminalModifica = :terminalModifica ,";
            //campo fechas
            //SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaModifica = formatoFechaHora.format(obj.getFechaHoraModifica());
            strQuery += " a.fechaHoraModifica = '" + fechaModifica + "' ";
            //campos objetos
            if (tecnologiaMipres.getInsumosMipresId() != null) {
                strQuery += ", a.insumosMipresId.id = " + tecnologiaMipres.getInsumosMipresId().getId() + " ";
            }
            
            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(tecnologiaMipres);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public MaTecnologiaMipres eliminar(int id) throws Exception {
        MaTecnologiaMipres obj = null;
        try {
            MaTecnologiasMipres ent = getEntityManager().find(MaTecnologiasMipres.class, id);
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
    public List<MaTecnologiaMipres> consultarTodos() throws Exception {
        List<MaTecnologiaMipres> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaTecnologiasMipres p "
                    + "ORDER BY p.id ";
            List<MaTecnologiasMipres> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaTecnologiasMipres per : list) {
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

    public static MaTecnologiaMipres castEntidadNegocio(MaTecnologiasMipres per) {
        MaTecnologiaMipres obj = new MaTecnologiaMipres();
        obj.setId(per.getId());
        obj.setCodigoMipres(per.getCodigoMipres());
        obj.setDescripcion(per.getDescripcion());
        //objeto
        if(per.getMaTecnologiasId()!= null) {
            obj.setMaTecnologia(new MaTecnologia(per.getMaTecnologiasId().getId()));
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

    public static MaTecnologiasMipres castNegocioEntidad(MaTecnologiaMipres obj) {
        MaTecnologiasMipres per = new MaTecnologiasMipres();
        per.setId(obj.getId());
        per.setCodigoMipres(obj.getCodigoMipres());
        per.setDescripcion(obj.getDescripcion());
        //objetos
        if (obj.getMaTecnologia()!= null) {
            per.setMaTecnologiasId(new MaTecnologias(obj.getMaTecnologia().getId()));
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
    public MaTecnologiaMipres consultarPorCodigo(String codigo) throws java.lang.Exception {
        MaTecnologiaMipres objetoRes = null;
        int i = 0;
        try {
            String strQuery = "FROM MaTecnologiasMipres p "
                    + " WHERE p.codigoMipres = '" + codigo +"' ";
            List<MaTecnologiasMipres> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaTecnologiasMipres per : list) {
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
    public MaTecnologiaMipres consultarPorTecnologiaYCodigoMipres(int idTecnologia,String codigo) throws java.lang.Exception {
        MaTecnologiaMipres objetoRes = null;
        int i = 0;
        try {
            String strQuery = "FROM MaTecnologiasMipres p "
                    + " WHERE p.codigoMipres = '" + codigo +"' "
                    + " AND p.maTecnologiasId.id = " + idTecnologia +" ";
            List<MaTecnologiasMipres> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaTecnologiasMipres per : list) {
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
    public MaTecnologiaMipres consultarPorCodigoTecnologiaYCodigoMipres(String codigoTecnologia,String codigoMipres) throws java.lang.Exception {
        MaTecnologiaMipres objetoRes = null;
        int i = 0;
        try {
            String strQuery = "FROM MaTecnologiasMipres p "
                    + " WHERE p.codigoMipres = '" + codigoMipres +"' "
                    + " AND p.maTecnologiasId.codigoPropio = '" + codigoTecnologia +"' ";
            List<MaTecnologiasMipres> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaTecnologiasMipres per : list) {
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
            String strQuery = "SELECT COUNT(p) FROM MaTecnologiasMipres p "
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
    public List<MaTecnologiaMipres> consultarListaBuscador(ParamConsulta paramConsulta) throws Exception {
        List<MaTecnologiaMipres> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaTecnologiasMipres p "
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
            List<MaTecnologiasMipres> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (MaTecnologiasMipres per : list) {
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
