package com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupo;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupoPrestador;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmGrupoPrestadores;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.ejb.entidades.CmGrupos;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmGrupoPrestadorRemoto;
import javax.persistence.Query;

@Stateless
@Remote(CmGrupoPrestadorRemoto.class)
public class CmGrupoPrestadorServicio extends GenericoServicio implements CmGrupoPrestadorRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p.id) FROM CmGrupoPrestadores p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += " AND p.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                        case "idGrupo":
                            strQuery += " AND  p.cmGruposId.id = " + e.getValue() + " ";
                            break;
                        case "cntPrestador.numeroDocumento":
                            strQuery += "AND  p.cntPrestadoresId.numeroDocumento = '" + e.getValue() + "' ";
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
    public List<CmGrupoPrestador> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmGrupoPrestador> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p FROM CmGrupoPrestadores p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND p.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "idGrupo":
                            strQuery += "AND  p.cmGruposId.id = " + e.getValue() + " ";
                            break;
                        case "cntPrestador.numeroDocumento":
                            strQuery += "AND  p.cntPrestadoresId.numeroDocumento = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                String parametro = paramConsulta.getOrden().replace("cntPrestador.numeroDocumento", "cntPrestadoresId.numeroDocumento");
                strQuery += "p." + parametro + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<CmGrupoPrestadores> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CmGrupoPrestadores per : list) {
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
    public List<CmGrupoPrestador> consultarLista(int id) throws Exception {
        List<CmGrupoPrestador> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "SELECT p FROM CmGrupoPrestadores p "
                    + "WHERE p.cmGruposId.id = :id_grupo ";
            strQuery += "ORDER BY p.id DESC ";
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("id_grupo", id);
            List<CmGrupoPrestadores> list = query.getResultList();
            for (CmGrupoPrestadores per : list) {
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
    public CmGrupoPrestador consultar(int idGrupo , int idPrestador) throws Exception {
        CmGrupoPrestador prestador = new CmGrupoPrestador();
        int i = 0;
        try {
            String strQuery = "SELECT p FROM CmGrupoPrestadores p "
                    + " WHERE p.cmGruposId.id = :id_grupo AND "
                    + " p.cntPrestadoresId.id = :id_prestador  ";
            strQuery += " ORDER BY p.id DESC ";
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("id_grupo", idGrupo);
            query.setParameter("id_prestador", idPrestador);
            List<CmGrupoPrestadores> list = query.getResultList();
            for (CmGrupoPrestadores per : list) {
                prestador = castEntidadNegocio(per);
            }
        } catch (NoResultException e) {
            prestador = new CmGrupoPrestador();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return prestador;
    }

    @Override
    public CmGrupoPrestador consultar(int id) throws Exception {
        CmGrupoPrestador objRes = null;
        try {
            objRes = castEntidadNegocio((CmGrupoPrestadores) getEntityManager().find(CmGrupoPrestadores.class, id));
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
    public int insertar(CmGrupoPrestador obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(CmGrupoPrestador obj) throws Exception {
        try {
            String hql = "UPDATE CmGrupoPrestadores SET "
                    + "cmGruposId = :cm_grupos_id, "
                    + "cntPrestadoresId = :cnt_prestadores, "
                    + "activo = :activo, "
                    + "usuarioModifica = :usuario_modifica, "
                    + "terminalModifica = :terminal_modifica, "
                    + "fechaHoraModifica = :fecha_hora_modifica "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("cm_grupos_id", obj.getCmGrupo());
            query.setParameter("cnt_prestadores", obj.getCntPrestador());
            query.setParameter("usuario_modifica", obj.getUsuarioModifica());
            query.setParameter("terminal_modifica", obj.getTerminalModifica());
            query.setParameter("fecha_hora_modifica", obj.getFechaHoraModifica());
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
    public CmGrupoPrestador eliminar(int id) throws Exception {
        CmGrupoPrestador obj = null;
        try {
            CmGrupoPrestadores ent = getEntityManager().find(CmGrupoPrestadores.class, id);
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

    public static CmGrupoPrestador castEntidadNegocio(CmGrupoPrestadores per) {
        CmGrupoPrestador neg = new CmGrupoPrestador();
        neg.setId(per.getId());
        neg.setCmGrupo(new CmGrupo(per.getCmGruposId().getId()));
        neg.setCntPrestador(new CntPrestador(per.getCntPrestadoresId().getId()));
        neg.setActivo(per.getActivo());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        neg.setRazonSocial(per.getCntPrestadoresId().getRazonSocial());
        CntPrestador cntPrestador = new CntPrestador(per.getCntPrestadoresId().getId(),
                per.getCntPrestadoresId().getRazonSocial(),
                per.getCntPrestadoresId().getNombreRepresentanteLegal()
        );
        cntPrestador.setActivo(per.getCntPrestadoresId().getActivo());
        cntPrestador.setCodigoMinSalud(per.getCntPrestadoresId().getCodigoMinSalud());
        cntPrestador.setNumeroDocumento(per.getCntPrestadoresId().getNumeroDocumento());
        neg.setCntPrestador(cntPrestador);

        if (per.getUsuarioModifica() != null) {
            neg.setUsuarioModifica(per.getUsuarioModifica());
        }
        if (per.getTerminalModifica() != null) {
            neg.setTerminalModifica(per.getTerminalModifica());
        }
        if (per.getFechaHoraModifica() != null) {
            neg.setFechaHoraModifica(per.getFechaHoraModifica());
        }
        return neg;
    }

    public static CmGrupoPrestadores castNegocioEntidad(CmGrupoPrestador obj) {
        CmGrupoPrestadores ent = new CmGrupoPrestadores();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setCmGruposId(new CmGrupos(obj.getCmGrupo().getId()));
        ent.setCntPrestadoresId(new CntPrestadores(obj.getCntPrestador().getId()));
        ent.setActivo(obj.getActivo());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        if (obj.getUsuarioModifica() != null) {
            ent.setUsuarioModifica(obj.getUsuarioModifica());
        }
        if (obj.getTerminalModifica() != null) {
            ent.setTerminalModifica(obj.getTerminalModifica());
        }
        if (obj.getFechaHoraModifica() != null) {
            ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        }
        return ent;
    }

}
