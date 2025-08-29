/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.maestro;


import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaPaquete;
import com.saviasaludeps.savia.dominio.maestro.MaPaqueteMipres;
import com.saviasaludeps.savia.dominio.mipres.MpCodigoInsumo;
import com.saviasaludeps.savia.ejb.entidades.MaPaquetes;
import com.saviasaludeps.savia.ejb.entidades.MaPaquetesMipres;
import com.saviasaludeps.savia.ejb.entidades.MpCodigoInsumos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.maestro.MaPaqueteMipresRemoto;
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
@Remote(MaPaqueteMipresRemoto.class)
public class MaPaqueteMipresServicio extends GenericoServicio implements MaPaqueteMipresRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaPaquetesMipres p "
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
                            strQuery += " AND p.descripcionMipres LIKE '%" + e.getValue() + "%' ";
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
    public List<MaPaqueteMipres> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<MaPaqueteMipres> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaPaquetesMipres p "
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
            List<MaPaquetesMipres> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (MaPaquetesMipres per : list) {
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
    public MaPaqueteMipres consultar(int id) throws Exception {
        MaPaqueteMipres objRes = null;
        try {
            objRes = castEntidadNegocio((MaPaquetesMipres) getEntityManager().find(MaPaquetesMipres.class, id));
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
    public int insertar(MaPaqueteMipres obj) throws Exception {
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
    public void actualizar(MaPaqueteMipres obj) throws Exception {
        try {
            //getEntityManager().merge(castNegocioEntidad(obj));
            MaPaquetesMipres paqueteMipres = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            //session.update(per);
            String strQuery = "UPDATE MaPaquetesMipres a SET ";
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
            if (paqueteMipres.getMpCodigoInsumosId() != null) {
                strQuery += ", a.getMpCodigoInsumosId.id = " + paqueteMipres.getMpCodigoInsumosId().getId() + " ";
            }
            
            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(paqueteMipres);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public MaPaqueteMipres eliminar(int id) throws Exception {
        MaPaqueteMipres obj = null;
        try {
            MaPaquetesMipres ent = getEntityManager().find(MaPaquetesMipres.class, id);
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
    public List<MaPaqueteMipres> consultarTodos() throws Exception {
        List<MaPaqueteMipres> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaPaquetesMipres p "
                    + "ORDER BY p.id ";
            List<MaPaquetesMipres> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaPaquetesMipres per : list) {
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

    public static MaPaqueteMipres castEntidadNegocio(MaPaquetesMipres per) {
        MaPaqueteMipres obj = new MaPaqueteMipres();
        obj.setId(per.getId());
        obj.setCodigoMipres(per.getCodigoMipres());
        obj.setDescripcionMipres(per.getDescripcionMipres());
        //objeto
        if(per.getMaPaquetesId()!= null) {
            obj.setMaPaquete(new MaPaquete(per.getMaPaquetesId().getId()));
        }
        if(per.getMpCodigoInsumosId()!= null) {
            MpCodigoInsumo cod = new MpCodigoInsumo(per.getMpCodigoInsumosId().getId());
            //cod.setMaeInsumoId(per.getInsumosMipresId().getMaeInsumoId());
            cod.setCodigoMipres(per.getMpCodigoInsumosId().getCodigoMipres());
            cod.setDescripcion(per.getMpCodigoInsumosId().getDescripcion());
            cod.setActivo(per.getMpCodigoInsumosId().getActivo());
            cod.setVersionMipres(per.getMpCodigoInsumosId().getVersionMipres());
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

    public static MaPaquetesMipres castNegocioEntidad(MaPaqueteMipres obj) {
        MaPaquetesMipres per = new MaPaquetesMipres();
        per.setId(obj.getId());
        per.setCodigoMipres(obj.getCodigoMipres());
        per.setDescripcionMipres(obj.getDescripcionMipres());
        //objetos
        if (obj.getMaPaquete()!= null) {
            per.setMaPaquetesId(new MaPaquetes(obj.getMaPaquete().getId()));
        }
        if(obj.getMpCodigoInsumo()!= null) {
            per.setMpCodigoInsumosId(new MpCodigoInsumos(obj.getMpCodigoInsumo().getId()));
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
    public MaPaqueteMipres consultarPorCodigo(String codigo) throws java.lang.Exception {
        MaPaqueteMipres objetoRes = null;
        int i = 0;
        try {
            String strQuery = "FROM MaPaquetesMipres p "
                    + " WHERE p.codigoMipres = '" + codigo +"' ";
            List<MaPaquetesMipres> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaPaquetesMipres per : list) {
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
    public MaPaqueteMipres consultarPorTecnologiaYCodigoMipres(int idTecnologia,String codigo) throws java.lang.Exception {
        MaPaqueteMipres objetoRes = null;
        int i = 0;
        try {
            String strQuery = "FROM MaPaquetesMipres p "
                    + " WHERE p.codigoMipres = '" + codigo +"' "
                    + " AND p.maPaquetesId.id = " + idTecnologia +" ";
            List<MaPaquetesMipres> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaPaquetesMipres per : list) {
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
    public MaPaqueteMipres consultarPorCodigoTecnologiaYCodigoMipres(String codigoTecnologia,String codigoMipres) throws java.lang.Exception {
        MaPaqueteMipres objetoRes = null;
        int i = 0;
        try {
            String strQuery = "FROM MaPaquetesMipres p "
                    + " WHERE p.codigoMipres = '" + codigoMipres +"' "
                    + " AND p.maPaquetesId.codigo = '" + codigoTecnologia +"' ";
            List<MaPaquetesMipres> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaPaquetesMipres per : list) {
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
            String strQuery = "SELECT COUNT(p) FROM MaPaquetesMipres p "
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
    public List<MaPaqueteMipres> consultarListaBuscador(ParamConsulta paramConsulta) throws Exception {
        List<MaPaqueteMipres> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaPaquetesMipres p "
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
            List<MaPaquetesMipres> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (MaPaquetesMipres per : list) {
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
