package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjExpediente;
import com.saviasaludeps.savia.dominio.juridico.CntjExpedienteResponsable;
import com.saviasaludeps.savia.ejb.entidades.CntjExpedienteResponsables;
import com.saviasaludeps.savia.ejb.entidades.CntjExpedientes;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CntjExpedienteResponsableRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author idbohorquez
 */
@Stateless
@Remote(CntjExpedienteResponsableRemoto.class)
public class CntjExpedienteResponsableServicio extends GenericoServicio implements CntjExpedienteResponsableRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
       int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjExpedienteResponsables c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "expediente":
                            strQuery.append(" AND c.cntjExpedientesId.id = ").append(e.getValue());
                            break;
                        case "usuarioId.documento":
                            strQuery.append(" AND c.gnUsuariosId.documento = '").append((String)e.getValue()).append("' ");
                            break;
                        case "usuarioId.nombre":
                            strQuery.append(" AND c.gnUsuariosId.nombre like '%").append((String)e.getValue()).append("%' ");
                            break;
                        case "rol":
                            strQuery.append(" AND c.rol = ").append(e.getValue());
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery.toString())
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
    public List<CntjExpedienteResponsable> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CntjExpedienteResponsable> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjExpedienteResponsables c WHERE c.id > 0 ");
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "expediente":
                            strQuery.append(" AND c.cntjExpedientesId.id = ").append(e.getValue());
                            break;
                        case "usuarioId.documento":
                            strQuery.append(" AND c.gnUsuariosId.documento = '").append((String)e.getValue()).append("' ");
                            break;
                        case "usuarioId.nombre":
                            strQuery.append(" AND c.gnUsuariosId.nombre like '%").append((String)e.getValue()).append("%' ");
                            break;
                        case "rol":
                            strQuery.append(" AND c.rol = ").append(e.getValue());
                            break;
                    }
                }
            }
            strQuery.append(" ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                strQuery.append(" c." + paramConsulta.getOrden() + " " + (paramConsulta.isAscendente() ? "ASC" : "DESC"));
            } else {
                strQuery.append(" c.id DESC ");
            }
            List<CntjExpedienteResponsables> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjExpedienteResponsables item : list) {
                listResult.add(castEntidadNegocio(item));
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
    public int insertar(CntjExpedienteResponsable objeto) throws java.lang.Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(objeto)).getId();
            objeto.setId(id);
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
    public CntjExpedienteResponsable consultar(int id) throws java.lang.Exception {
        CntjExpedienteResponsable objRes = null;
        try {
            objRes = castEntidadNegocio((CntjExpedienteResponsables) getEntityManager().find(CntjExpedienteResponsables.class, id));
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
    public void actualizar(CntjExpedienteResponsable objeto) throws java.lang.Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CntjExpedienteResponsables SET  ");
            sql.append("gnUsuariosId.id = :usuarioId,  ");
            sql.append("cntjExpedientesId.id = :expedienteId,  ");      
            sql.append("fechaInicial = :fechaInicial, ");
            sql.append("fechaFinal = :fechaFinal, ");
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("usuarioId", objeto.getUsuarioId().getId());
            query.setParameter("expedienteId", objeto.getExpedienteId().getId());
            query.setParameter("fechaInicial", objeto.getFechaInicial());
            query.setParameter("fechaFinal", objeto.getFechaFinal());
            query.setParameter("usuarioModifica", objeto.getUsuarioModifica());
            query.setParameter("terminalModifica", objeto.getTerminalModifica());
            query.setParameter("fechaHoraModifica", objeto.getFechaHoraModifica());            
            query.setParameter("id", objeto.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CntjExpedienteResponsable ultimoResponsable(int idExpediente, int rol) throws java.lang.Exception {
        CntjExpedienteResponsable resultado = null;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjExpedienteResponsables c WHERE c.id > 0 AND c.cntjExpedientesId.id = :idexpediente and c.rol = :rol ");
            strQuery.append(" and c.id = ( select max(c2.id) from CntjExpedienteResponsables c2 where c2.id > 0 and c2.cntjExpedientesId.id = :idexpediente  and c2.rol = :rol ) ");
            resultado = castEntidadNegocio((CntjExpedienteResponsables) getEntityManager().createQuery(strQuery.toString())
                    .setParameter("idexpediente", idExpediente)
                    .setParameter("rol", rol)
                    .getSingleResult());
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return resultado;
    }
    
    @Override
    public CntjExpedienteResponsable eliminar(int id) throws java.lang.Exception {
        CntjExpedienteResponsable obj = null;
        try {
            CntjExpedienteResponsables ent = getEntityManager().find(CntjExpedienteResponsables.class, id);
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
    
    private CntjExpedienteResponsable castEntidadNegocio(CntjExpedienteResponsables ent) {
        CntjExpedienteResponsable obj = new CntjExpedienteResponsable();
        obj.setId(ent.getId());
        Usuario usuario = new Usuario(ent.getGnUsuariosId().getId());
        usuario.setNombre(ent.getGnUsuariosId().getNombre());
        usuario.setDocumento(ent.getGnUsuariosId().getDocumento());
        usuario.setMaeAreaValor(ent.getGnUsuariosId().getMaeAreaValor());
        usuario.setMaeCargoValor(ent.getGnUsuariosId().getMaeCargoValor());
        obj.setUsuarioId(usuario);
        obj.setExpedienteId(new CntjExpediente(ent.getCntjExpedientesId().getId()));
        obj.setFechaInicial(ent.getFechaInicial());
        obj.setFechaFinal(ent.getFechaFinal());
        obj.setRol(ent.getRol());
        
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        return obj;
    }
    
    private CntjExpedienteResponsables castNegocioEntidad(CntjExpedienteResponsable obj){
        CntjExpedienteResponsables ent = new CntjExpedienteResponsables();
        ent.setGnUsuariosId(new GnUsuarios(obj.getUsuarioId().getId()));
        ent.setCntjExpedientesId(new CntjExpedientes(obj.getExpedienteId().getId()));
        ent.setFechaInicial(obj.getFechaInicial());
        ent.setFechaFinal(obj.getFechaFinal());
        ent.setRol(obj.getRol());
        
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }
    
}
