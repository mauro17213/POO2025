/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Afiliado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo3Afiliados;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos3;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3AfiliadoRemoto;
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
 * @author Stiven Giraldo
 */
@Stateless
@Remote(AuAnexo3AfiliadoRemoto.class)
public class AuAnexo3AfiliadoServicio extends GenericoServicio implements AuAnexo3AfiliadoRemoto{

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cantidad = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AuAnexo3Afiliados p "
                    + "WHERE p.id > 0";
            if(paramConsulta.getFiltros() != null){
                for(Map.Entry e : paramConsulta.getFiltros().entrySet()){
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }            
            cantidad = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
        } catch (NoResultException e){
            cantidad = 0;
        }catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally{
            cerrarEntityManager();
        }
        return cantidad;
    }

    @Override
    public List<AuAnexo3Afiliado> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo3Afiliado> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM AuAnexo3Afiliados p "
                    + "WHERE p.id > 0";
            if(paramConsulta.getFiltros() != null){
                for(Map.Entry e : paramConsulta.getFiltros().entrySet()){
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null){
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            
            List<AuAnexo3Afiliados> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexo3Afiliados anexo3Afiliado : list){
                listaResultados.add(castEntidadNegocio(anexo3Afiliado));
            }
        } catch (NoResultException e){
            listaResultados = new ArrayList();
        }catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally{
            cerrarEntityManager();
        }
        return listaResultados;
    }

    @Override
    public AuAnexo3Afiliado consultar(int id) throws Exception {
        AuAnexo3Afiliado objRes = null;
        try {
            objRes = castEntidadNegocio((AuAnexo3Afiliados) getEntityManager().find(AuAnexo3Afiliados.class, id));
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
    public int insertar(AuAnexo3Afiliado obj) throws Exception {
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
    public void actualizar(AuAnexo3Afiliado obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexo3Afiliados a SET ";
            strQuery += "a.auAnexo3Id.id = :auAnexo3Id ,";
            strQuery += "a.maeTipoDocumentoId = :maeTipoDocumentoId ,";
            strQuery += "a.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo ,";
            strQuery += "a.maeTipoDocumentoValor = :maeTipoDocumentoValor ,";
            strQuery += "a.numeroIdentificacion = :numeroIdentificacion ,";
            strQuery += "a.primerNombre = :primerNombre ,";
            strQuery += "a.segundoNombre = :segundoNombre ,";
            strQuery += "a.primerApellido = :primerApellido ,";
            strQuery += "a.segundoApellido = :segundoApellido ,";
            strQuery += "a.fechaNacimiento = :fechaNacimiento ,";
            strQuery += "a.direccionAfiliado = :direccionAfiliado ,";
            strQuery += "a.telefonoAfiliado = :telefonoAfiliado ,";
            strQuery += "a.celularAfiliado = :celularAfiliado ,";
            strQuery += "a.correoElectronico = :correoElectronico ,";
            strQuery += "a.maeRegimenId = :maeRegimenId ,";
            strQuery += "a.maeRegimenCodigo = :maeRegimenCodigo ,";
            strQuery += "a.maeRegimenValor = :maeRegimenValor ,";
            //2023-11-01 jyperez nuevo campo
            strQuery += "a.incapacidadProlongada = :incapacidadProlongada ,";
            strQuery += "a.usuarioCrea = :usuarioCrea ,";
            strQuery += "a.fechaHoraCrea = :fechaHoraCrea ,";
            strQuery += "a.terminalCrea = :terminalCrea ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ";
            strQuery += " WHERE a.id = :id ";            
            Query query = session.createQuery(strQuery);
            query.setParameter("auAnexo3Id", obj.getAuAnexo3Id().getId());
            query.setParameter("maeTipoDocumentoId", obj.getMaeTipoDocumentoId());
            query.setParameter("maeTipoDocumentoCodigo", obj.getMaeTipoDocumentoCodigo());
            query.setParameter("maeTipoDocumentoValor", obj.getMaeTipoDocumentoValor());
            query.setParameter("numeroIdentificacion", obj.getNumeroIdentificacion());
            query.setParameter("primerNombre", obj.getPrimerNombre());
            query.setParameter("segundoNombre", obj.getSegundoNombre());
            query.setParameter("primerApellido", obj.getPrimerApellido());
            query.setParameter("segundoApellido", obj.getSegundoApellido());
            query.setParameter("fechaNacimiento", obj.getFechaNacimiento());
            query.setParameter("direccionAfiliado", obj.getDireccionAfiliado());
            query.setParameter("telefonoAfiliado", obj.getTelefonoAfiliado());
            query.setParameter("celularAfiliado", obj.getCelularAfiliado());
            query.setParameter("correoElectronico", obj.getCorreoElectronico());
            query.setParameter("maeRegimenId", obj.getMaeRegimenId());
            query.setParameter("maeRegimenCodigo", obj.getMaeRegimenCodigo());
            query.setParameter("maeRegimenValor", obj.getMaeRegimenValor());
            query.setParameter("incapacidadProlongada", obj.getIncapacidadProlongada());
            query.setParameter("usuarioCrea", obj.getUsuarioCrea());
            query.setParameter("fechaHoraCrea", obj.getFechaHoraCrea());
            query.setParameter("terminalCrea", obj.getTerminalCrea());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
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
    public AuAnexo3Afiliado eliminar(int id) throws Exception {
        AuAnexo3Afiliado obj = null;
        try {
            AuAnexo3Afiliados ent = getEntityManager().find(AuAnexo3Afiliados.class, id);
            if (ent != null){
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }        
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }
    
    public static AuAnexo3Afiliado castEntidadNegocio(AuAnexo3Afiliados entidad){
        AuAnexo3Afiliado negocio = new AuAnexo3Afiliado();
        negocio.setId(entidad.getId());
        negocio.setAuAnexo3Id(new AuAnexo3(entidad.getAuAnexo3Id().getId()));
        negocio.setMaeTipoDocumentoId(entidad.getMaeTipoDocumentoId());
        negocio.setMaeTipoDocumentoCodigo(entidad.getMaeTipoDocumentoCodigo());
        negocio.setMaeTipoDocumentoValor(entidad.getMaeTipoDocumentoValor());
        negocio.setNumeroIdentificacion(entidad.getNumeroIdentificacion());
        negocio.setPrimerNombre(entidad.getPrimerNombre());
        negocio.setSegundoNombre(entidad.getSegundoNombre());
        negocio.setPrimerApellido(entidad.getPrimerApellido());
        negocio.setSegundoApellido(entidad.getSegundoApellido());
        negocio.setFechaNacimiento(entidad.getFechaNacimiento());
        negocio.setDireccionAfiliado(entidad.getDireccionAfiliado());
        negocio.setTelefonoAfiliado(entidad.getTelefonoAfiliado());
        negocio.setCelularAfiliado(entidad.getCelularAfiliado());
        negocio.setCorreoElectronico(entidad.getCorreoElectronico());
        negocio.setMaeRegimenId(entidad.getMaeRegimenId());
        negocio.setMaeRegimenCodigo(entidad.getMaeRegimenCodigo());
        negocio.setMaeRegimenValor(entidad.getMaeRegimenValor());
        //2023-11-01 jyperez nuevo campo
        negocio.setIncapacidadProlongada(entidad.getIncapacidadProlongada());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        return negocio;
    }
    
    public static AuAnexo3Afiliados castNegocioEntidad(AuAnexo3Afiliado negocio){
        AuAnexo3Afiliados entidad = new AuAnexo3Afiliados();
        entidad.setId(negocio.getId());
        entidad.setAuAnexo3Id(new AuAnexos3(negocio.getAuAnexo3Id().getId()));
        entidad.setMaeTipoDocumentoId(negocio.getMaeTipoDocumentoId());
        entidad.setMaeTipoDocumentoCodigo(negocio.getMaeTipoDocumentoCodigo());
        entidad.setMaeTipoDocumentoValor(negocio.getMaeTipoDocumentoValor());
        entidad.setNumeroIdentificacion(negocio.getNumeroIdentificacion());
        entidad.setPrimerNombre(negocio.getPrimerNombre());
        entidad.setSegundoNombre(negocio.getSegundoNombre());
        entidad.setPrimerApellido(negocio.getPrimerApellido());
        entidad.setSegundoApellido(negocio.getSegundoApellido());
        entidad.setFechaNacimiento(negocio.getFechaNacimiento());
        entidad.setDireccionAfiliado(negocio.getDireccionAfiliado());
        entidad.setTelefonoAfiliado(negocio.getTelefonoAfiliado());
        entidad.setCelularAfiliado(negocio.getCelularAfiliado());
        entidad.setCorreoElectronico(negocio.getCorreoElectronico());
        entidad.setMaeRegimenId(negocio.getMaeRegimenId());
        entidad.setMaeRegimenCodigo(negocio.getMaeRegimenCodigo());
        entidad.setMaeRegimenValor(negocio.getMaeRegimenValor());
        //2023-11-01 jyperez nuevo campo
        entidad.setIncapacidadProlongada(negocio.getIncapacidadProlongada());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        if(negocio.getUsuarioModifica() != null){
            entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        }
        if(negocio.getFechaHoraModifica() != null){
            entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        }
        if(negocio.getTerminalModifica() != null){
            entidad.setTerminalModifica(negocio.getTerminalModifica());
        }        
        return entidad;        
    }
    
    public static List<AuAnexo3Afiliado> castListaEntidadNegocio(List<AuAnexo3Afiliados> entidades){
        List<AuAnexo3Afiliado> negocios = new ArrayList();
        for(AuAnexo3Afiliados entidad : entidades){
            AuAnexo3Afiliado negocio = new AuAnexo3Afiliado();
            negocio.setId(entidad.getId());
            negocio.setAuAnexo3Id(new AuAnexo3(entidad.getAuAnexo3Id().getId()));
            negocio.setMaeTipoDocumentoId(entidad.getMaeTipoDocumentoId());
            negocio.setMaeTipoDocumentoCodigo(entidad.getMaeTipoDocumentoCodigo());
            negocio.setMaeTipoDocumentoValor(entidad.getMaeTipoDocumentoValor());
            negocio.setNumeroIdentificacion(entidad.getNumeroIdentificacion());
            negocio.setPrimerNombre(entidad.getPrimerNombre());
            negocio.setSegundoNombre(entidad.getSegundoNombre());
            negocio.setPrimerApellido(entidad.getPrimerApellido());
            negocio.setSegundoApellido(entidad.getSegundoApellido());
            negocio.setFechaNacimiento(entidad.getFechaNacimiento());
            negocio.setDireccionAfiliado(entidad.getDireccionAfiliado());
            negocio.setTelefonoAfiliado(entidad.getTelefonoAfiliado());
            negocio.setCelularAfiliado(entidad.getCelularAfiliado());
            negocio.setCorreoElectronico(entidad.getCorreoElectronico());
            negocio.setMaeRegimenId(entidad.getMaeRegimenId());
            negocio.setMaeRegimenCodigo(entidad.getMaeRegimenCodigo());
            negocio.setMaeRegimenValor(entidad.getMaeRegimenValor());
            //2023-11-01 jyperez nuevo campo
            negocio.setIncapacidadProlongada(entidad.getIncapacidadProlongada());
            //Auditoria
            negocio.setUsuarioCrea(entidad.getUsuarioCrea());
            negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
            negocio.setTerminalCrea(entidad.getTerminalCrea());
            negocio.setUsuarioModifica(entidad.getUsuarioModifica());
            negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
            negocio.setTerminalModifica(entidad.getTerminalModifica());
            negocios.add(negocio);
        }
        
        return negocios;
    }
        
}
