package com.saviasaludeps.savia.ejb.servicios.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupo;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupoPrestador;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupoUsuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CmGrupoPrestadores;
import com.saviasaludeps.savia.ejb.entidades.CmGrupoUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.ejb.entidades.CmGrupos;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmGrupoRemoto;
import javax.persistence.Query;

@Stateless
@Remote(CmGrupoRemoto.class)
public class CmGrupoServicio extends GenericoServicio implements CmGrupoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(g.id) FROM CmGrupos g "
                    + "WHERE g.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND g.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND g.activo = " + e.getValue() + " ";
                            break;
                        case "categoria":
                            strQuery += "AND g.categoria = " + e.getValue() + " ";
                            break;
                        case "tipoGrupo":
                            strQuery += "AND g.tipoGrupo = " + e.getValue() + " ";
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
    public List<CmGrupo> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmGrupo> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "SELECT g FROM CmGrupos g "
                    + "WHERE g.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "nombre":
                            strQuery += "AND g.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND g.activo = " + e.getValue() + " ";
                            break;
                        case "categoria":
                            strQuery += "AND g.categoria = " + e.getValue() + " ";
                            break;
                        case "tipoGrupo":
                            strQuery += "AND g.tipoGrupo = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "g." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "g.id DESC";
            }
            List<CmGrupos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (CmGrupos per : list) {
                listResult.add(castEntidadNegocioCorto(per));
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
    public CmGrupo consultar(int id) throws Exception {
        CmGrupo objRes = null;
        try {
            objRes = castEntidadNegocio((CmGrupos) getEntityManager().find(CmGrupos.class, id));
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
    public CmGrupo consultarConParametrizacion(ParamConsulta paramConsulta) throws Exception {
        CmGrupo grupo = new CmGrupo();
        try {
            String strQuery = "SELECT g FROM CmGrupos g "
                    + "WHERE g.id > 0 ";

            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND g.activo = :activo ";
            }
            
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += " AND g.tipoGrupo = :tipoGrupo ";
            }
            
             if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += " AND g.categoria = :categoria ";
            }

            Query query = getEntityManager().createQuery(strQuery);
            
            if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("activo", paramConsulta.getParametroConsulta1());
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("tipoGrupo", paramConsulta.getParametroConsulta2());
            }
            if (paramConsulta.getParametroConsulta3() != null) {
                query.setParameter("categoria", paramConsulta.getParametroConsulta3());
            }

            CmGrupos cmGrupos = (CmGrupos) query.setMaxResults(1).getSingleResult();
            if (cmGrupos != null) {
                grupo = (castEntidadNegocio(cmGrupos));
            }
        } catch (NoResultException e) {
            grupo = new CmGrupo();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return grupo;
    }


    @Override
    public int insertar(CmGrupo obj) throws Exception {
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
    public void actualizar(CmGrupo obj) throws Exception {
        try {
            String hql = "UPDATE CmGrupos SET "
                    + "nombre = :nombre, "
                    + "descripcion = :descripcion, "
                    + "activo = :activo, "
                    + "pbs = :pbs, "
                    + "cama_fija = :cama_fija, "
                    + "tipoGrupo = :tipoGrupo, "
                    + "categoria = :categoria, "
                    + "usuarioModifica = :usuario_modifica, "
                    + "terminalModifica = :terminal_modifica, "
                    + "fechaHoraModifica = :fecha_hora_modifica "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("descripcion", obj.getDescripcion());
            query.setParameter("activo", obj.isActivo());
            query.setParameter("pbs", obj.getPbs());
            query.setParameter("cama_fija", obj.getCamaFija()); 
            query.setParameter("tipoGrupo", obj.getTipoGrupo());
            query.setParameter("categoria", obj.getCategoria());  
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
    public void actualizarIdAsignacion(CmGrupo obj) throws Exception {
        try {
            String hql = "UPDATE CmGrupos SET "
                    + "usuariosIdAsignacion = :usuariosIdAsignacion "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("usuariosIdAsignacion", obj.getUsuariosIdAsignacion());
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
    public CmGrupo eliminar(int id) throws Exception {
        CmGrupo obj = null;
        try {
            CmGrupos ent = getEntityManager().find(CmGrupos.class, id);
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

    public static CmGrupo castEntidadNegocio(CmGrupos per) {
        CmGrupo neg = new CmGrupo();
        neg.setId(per.getId());
        neg.setNombre(per.getNombre());
        neg.setDescripcion(per.getDescripcion());
        neg.setActivo(per.getActivo());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        neg.setUsuariosIdAsignacion(per.getUsuariosIdAsignacion());
        neg.setTipoGrupo(per.getTipoGrupo());
        neg.setCategoria(per.getCategoria());
        if (per.getUsuarioModifica() != null) {
            neg.setUsuarioModifica(per.getUsuarioModifica());
        }
        if (per.getTerminalModifica() != null) {
            neg.setTerminalModifica(per.getTerminalModifica());
        }
        if (per.getFechaHoraModifica() != null) {
            neg.setFechaHoraModifica(per.getFechaHoraModifica());
        }
        if (per.getCmGrupoPrestadoresList() != null) {
            //neg.setListaCmGrupoPrestadores(castNegocioEntidadPrestadores(per.getCmGrupoPrestadoresList()));
        }
        if (per.getCmGrupoUsuariosList() != null) {
            //neg.setListaCmGrupoUsuarios(castNegocioEntidadUsuarios(per.getCmGrupoUsuariosList()));
        }
        neg.setCamaFija(per.getCamaFija());
        neg.setPbs(per.getPbs());
        return neg;
    }
    
     public static CmGrupo castEntidadNegocioCorto(CmGrupos per) {
        CmGrupo neg = new CmGrupo();
        neg.setId(per.getId());
        neg.setNombre(per.getNombre());
        neg.setDescripcion(per.getDescripcion());
        neg.setActivo(per.getActivo());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        neg.setUsuariosIdAsignacion(per.getUsuariosIdAsignacion());
        neg.setTipoGrupo(per.getTipoGrupo());
        neg.setCategoria(per.getCategoria());
        if (per.getUsuarioModifica() != null) {
            neg.setUsuarioModifica(per.getUsuarioModifica());
        }
        if (per.getTerminalModifica() != null) {
            neg.setTerminalModifica(per.getTerminalModifica());
        }
        if (per.getFechaHoraModifica() != null) {
            neg.setFechaHoraModifica(per.getFechaHoraModifica());
        }
        neg.setCamaFija(per.getCamaFija());
        neg.setPbs(per.getPbs());
        return neg;
    }

    public static CmGrupos castNegocioEntidad(CmGrupo obj) {
        CmGrupos ent = new CmGrupos();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setNombre(obj.getNombre());
        ent.setDescripcion(obj.getDescripcion());
        ent.setActivo(obj.isActivo());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setUsuariosIdAsignacion(obj.getUsuariosIdAsignacion());
        ent.setTipoGrupo(obj.getTipoGrupo());
        ent.setCategoria(obj.getCategoria());
        if (obj.getUsuarioModifica() != null) {
            ent.setUsuarioModifica(obj.getUsuarioModifica());
        }
        if (obj.getTerminalModifica() != null) {
            ent.setTerminalModifica(obj.getTerminalModifica());
        }
        if (obj.getFechaHoraModifica() != null) {
            ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        }
        ent.setCamaFija(obj.getCamaFija());
        ent.setPbs(obj.getPbs());
        return ent;
    }

    private static List<CmGrupoPrestador> castNegocioEntidadPrestadores(List<CmGrupoPrestadores> cmGrupoPrestadoresList) {
        List<CmGrupoPrestador> listaNegocio = new ArrayList<>();
        CmGrupoPrestador neg;
        for (CmGrupoPrestadores ent : cmGrupoPrestadoresList) {
            neg = new CmGrupoPrestador();
            neg.setId(ent.getId());
            neg.setIdInsertar(0);
            CntPrestador cntPrestador = new CntPrestador(ent.getCntPrestadoresId().getId(), ent.getCntPrestadoresId().getRazonSocial(),
                    ent.getCntPrestadoresId().getNombreRepresentanteLegal()
            );
            cntPrestador.setActivo(ent.getCntPrestadoresId().getActivo());
            cntPrestador.setCodigoMinSalud(ent.getCntPrestadoresId().getCodigoMinSalud());
            cntPrestador.setNumeroDocumento(ent.getCntPrestadoresId().getNumeroDocumento());
            neg.setCntPrestador(cntPrestador);
            
            listaNegocio.add(neg);
        }
        return listaNegocio;
    }

    private static List<CmGrupoUsuario> castNegocioEntidadUsuarios(List<CmGrupoUsuarios> cmGrupoUsuariosList) {
        List<CmGrupoUsuario> listaNegocio = new ArrayList<>();
        CmGrupoUsuario neg;
        Usuario usuario;
        for (CmGrupoUsuarios ent : cmGrupoUsuariosList) {
            usuario = new Usuario();
            usuario.setId(ent.getGnUsuariosId().getId());
            usuario.setNombre(ent.getGnUsuariosId().getNombre());
            usuario.setUsuario(ent.getGnUsuariosId().getUsuario());
            neg = new CmGrupoUsuario();
            neg.setId(ent.getId());
            neg.setTipo(ent.getTipo());
            neg.setGnUsuario(usuario);
            listaNegocio.add(neg);
        }
        return listaNegocio;
    }

}
