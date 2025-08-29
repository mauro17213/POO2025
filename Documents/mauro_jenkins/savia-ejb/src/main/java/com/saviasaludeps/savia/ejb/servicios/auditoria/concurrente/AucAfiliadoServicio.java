/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucAfiliado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.AucAfiliados;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucAfiliadoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author sgiraldov
 */
@Stateless
@Remote(AucAfiliadoRemoto.class)
public class AucAfiliadoServicio extends GenericoServicio implements AucAfiliadoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AucAfiliados p "
                    + "WHERE p.id > 0";

            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "AND p.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            } 
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
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
    public List<AucAfiliado> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AucAfiliado> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AucAfiliados p "
                    + "WHERE p.id > 0";;

            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "AND p.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            }
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<AucAfiliados> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AucAfiliados afiliado : list) {
                listaResultados.add(castEntidadNegocio(afiliado));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }

    @Override
    public AucAfiliado consultar(int id) throws Exception {
        AucAfiliado objRes = null;
        try {
            objRes = castEntidadNegocio((AucAfiliados) getEntityManager().find(AucAfiliados.class, id));
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
    public int insertar(AucAfiliado obj) throws Exception {
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
    public void actualizar(AucAfiliado obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucAfiliados a SET ";
            strQuery += "a.asegAfiliadosId.id = :asegAfiliadosId ,";
            strQuery += "a.maeEstadoAfiliadoId = :maeEstadoAfiliadoId ,";
            strQuery += "a.maeEstadoAfiliadoCodigo = :maeEstadoAfiliadoCodigo ,";
            strQuery += "a.maeEstadoAfiliadoValor = :maeEstadoAfiliadoValor ,";
            strQuery += "a.maeTipoDocumentoId = :maeTipoDocumentoId ,";
            strQuery += "a.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo ,";
            strQuery += "a.maeTipoDocumentoValor = :maeTipoDocumentoValor ,";
            strQuery += "a.numeroDocumento = :numeroDocumento ,";
            strQuery += "a.primerApellido = :primerApellido ,";
            strQuery += "a.segundoApellido = :segundoApellido ,";
            strQuery += "a.primerNombre = :primerNombre ,";
            strQuery += "a.segundoNombre = :segundoNombre ,";
            strQuery += "a.fechaNacimiento = :fechaNacimiento ,";
            strQuery += "a.maeGeneroId = :maeGeneroId ,";
            strQuery += "a.maeGeneroCodigo = :maeGeneroCodigo ,";
            strQuery += "a.maeGeneroValor = :maeGeneroValor ,";
            strQuery += "a.correoElectronico = :correoElectronico ,";
            strQuery += "a.direccionResidencia = :direccionResidencia ,";
            strQuery += "a.ubicacionAfiliacionId = :ubicacionAfiliacionId ,";
            strQuery += "a.contratoAfiliacion = :contratoAfiliacion ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("asegAfiliadosId", obj.getAsegAfiliadoId().getId());
            query.setParameter("maeEstadoAfiliadoId", obj.getMaeEstadoAfiliadoId());
            query.setParameter("maeEstadoAfiliadoCodigo", obj.getMaeEstadoAfiliadoCodigo());
            query.setParameter("maeEstadoAfiliadoValor", obj.getMaeEstadoAfiliadoValor());
            query.setParameter("maeTipoDocumentoId", obj.getMaeTipoDocumentoId());
            query.setParameter("maeTipoDocumentoCodigo", obj.getMaeTipoDocumentoCodigo());
            query.setParameter("maeTipoDocumentoValor", obj.getMaeTipoDocumentoValor());
            query.setParameter("numeroDocumento", obj.getNumeroDocumento());
            query.setParameter("primerApellido", obj.getPrimerApellido());
            query.setParameter("segundoApellido", obj.getSegundoApellido());
            query.setParameter("primerNombre", obj.getPrimerNombre());
            query.setParameter("segundoNombre", obj.getSegundoNombre());
            query.setParameter("fechaNacimiento", obj.getFechaNacimiento());
            query.setParameter("maeGeneroId", obj.getMaeGeneroId());
            query.setParameter("maeGeneroCodigo", obj.getMaeGeneroCodigo());
            query.setParameter("maeGeneroValor", obj.getMaeGeneroValor());
            query.setParameter("correoElectronico", obj.getCorreoElectronico());
            query.setParameter("direccionResidencia", obj.getDireccionResidencia());
            query.setParameter("ubicacionAfiliacionId", obj.getUbicacionAfiliacionId());
            query.setParameter("contratoAfiliacion", obj.getContratoAfiliacion());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AucAfiliado eliminar(int id) throws Exception {
        AucAfiliado obj = null;
        try {
            AucAfiliados ent = getEntityManager().find(AucAfiliados.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            Exception(ELIMINAR, e);
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }
    
    private AucAfiliado castEntidadNegocio(AucAfiliados entidad) {
        AucAfiliado negocio = new AucAfiliado();
        negocio.setId(entidad.getId());
        if(entidad.getAsegAfiliadosId() != null){
            negocio.setAsegAfiliadoId(new AsegAfiliado(entidad.getAsegAfiliadosId().getId()));
        }
        negocio.setMaeEstadoAfiliadoId(entidad.getMaeEstadoAfiliadoId());
        negocio.setMaeEstadoAfiliadoCodigo(entidad.getMaeEstadoAfiliadoCodigo());
        negocio.setMaeEstadoAfiliadoValor(entidad.getMaeEstadoAfiliadoValor());
        negocio.setMaeTipoDocumentoId(entidad.getMaeTipoDocumentoId());
        negocio.setMaeTipoDocumentoCodigo(entidad.getMaeTipoDocumentoCodigo());
        negocio.setMaeTipoDocumentoValor(entidad.getMaeTipoDocumentoValor());
        negocio.setNumeroDocumento(entidad.getNumeroDocumento());
        negocio.setPrimerApellido(entidad.getPrimerApellido());
        negocio.setSegundoApellido(entidad.getSegundoApellido());
        negocio.setPrimerNombre(entidad.getPrimerNombre());
        negocio.setSegundoNombre(entidad.getSegundoNombre());
        negocio.setFechaNacimiento(entidad.getFechaNacimiento());
        negocio.setMaeGeneroId(entidad.getMaeGeneroId());
        negocio.setMaeGeneroCodigo(entidad.getMaeGeneroCodigo());
        negocio.setMaeGeneroValor(entidad.getMaeGeneroValor());
        negocio.setCorreoElectronico(entidad.getCorreoElectronico());
        negocio.setDireccionResidencia(entidad.getDireccionResidencia());
        negocio.setUbicacionAfiliacionId(entidad.getUbicacionAfiliacionId());
        negocio.setContratoAfiliacion(entidad.getContratoAfiliacion());
        negocio.setMaeRegimenId(entidad.getMaeRegimenId());
        negocio.setMaeRegimenCodigo(entidad.getMaeRegimenCodigo());
        negocio.setMaeRegimenValor(entidad.getMaeRegimenValor());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }
    
    private AucAfiliados castNegocioEntidad(AucAfiliado negocio) {
        AucAfiliados entidad = new AucAfiliados();
        if(negocio.getAsegAfiliadoId() != null){
           entidad.setAsegAfiliadosId(new AsegAfiliados(negocio.getAsegAfiliadoId().getId()));
        }
        entidad.setMaeEstadoAfiliadoId(negocio.getMaeEstadoAfiliadoId());
        entidad.setMaeEstadoAfiliadoCodigo(negocio.getMaeEstadoAfiliadoCodigo());
        entidad.setMaeEstadoAfiliadoValor(negocio.getMaeEstadoAfiliadoValor());
        entidad.setMaeTipoDocumentoId(negocio.getMaeTipoDocumentoId());
        entidad.setMaeTipoDocumentoCodigo(negocio.getMaeTipoDocumentoCodigo());
        entidad.setMaeTipoDocumentoValor(negocio.getMaeTipoDocumentoValor());
        entidad.setNumeroDocumento(negocio.getNumeroDocumento());
        entidad.setPrimerApellido(negocio.getPrimerApellido());
        entidad.setSegundoApellido(negocio.getSegundoApellido());
        entidad.setPrimerNombre(negocio.getPrimerNombre());
        entidad.setSegundoNombre(negocio.getSegundoNombre());
        entidad.setFechaNacimiento(negocio.getFechaNacimiento());
        entidad.setMaeGeneroId(negocio.getMaeGeneroId());
        entidad.setMaeGeneroCodigo(negocio.getMaeGeneroCodigo());
        entidad.setMaeGeneroValor(negocio.getMaeGeneroValor());
        entidad.setCorreoElectronico(negocio.getCorreoElectronico());
        entidad.setDireccionResidencia(negocio.getDireccionResidencia());
        entidad.setUbicacionAfiliacionId(negocio.getUbicacionAfiliacionId());
        entidad.setContratoAfiliacion(negocio.getContratoAfiliacion());
        entidad.setMaeRegimenId(negocio.getMaeRegimenId());
        entidad.setMaeRegimenCodigo(negocio.getMaeRegimenCodigo());
        entidad.setMaeRegimenValor(negocio.getMaeRegimenValor());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }
    
}
