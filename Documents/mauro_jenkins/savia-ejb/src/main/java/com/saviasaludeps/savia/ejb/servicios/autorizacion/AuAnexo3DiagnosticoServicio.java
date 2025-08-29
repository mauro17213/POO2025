/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Diagnostico;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo3Diagnosticos;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos3;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3DiagnosticoRemoto;
import java.util.ArrayList;
import java.util.List;
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
@Remote(AuAnexo3DiagnosticoRemoto.class)
public class AuAnexo3DiagnosticoServicio extends GenericoServicio implements AuAnexo3DiagnosticoRemoto{

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AuAnexo3Diagnostico> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AuAnexo3Diagnostico consultar(int id) throws java.lang.Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertar(AuAnexo3Diagnostico obj) throws java.lang.Exception {
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
    public void actualizar(AuAnexo3Diagnostico obj) throws Exception {
        try {
            //Falta realizar
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AuAnexo3Diagnosticos a SET ";
            strQuery += "a.auAnexos3Id.id = :auAnexos3Id ,";
            strQuery += "a.principal = :principal ,";
            strQuery += "a.maDiagnosticosId = :maDiagnosticosId ,";
            strQuery += "a.maDiagnosticosCodigo = :maDiagnosticosCodigo ,";
            strQuery += "a.maDiagnosticosValor = :maDiagnosticosValor ,";
            strQuery += "a.maeTipoDiagnosticoId = :maeTipoDiagnosticoId ,";
            strQuery += "a.maeTipoDiagnosticoCodigo = :maeTipoDiagnosticoCodigo ,";
            strQuery += "a.maeTipoDiagnosticoValor = :maeTipoDiagnosticoValor ,";
            strQuery += "a.usuarioCrea = :usuarioCrea ,";
            strQuery += "a.terminalCrea = :terminalCrea ,";
            strQuery += "a.fechaHoraCrea = :fechaHoraCrea ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";    
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("auAnexos3Id", obj.getAuAnexos3Id().getId());
            query.setParameter("principal", obj.getPrincipal());
            query.setParameter("maDiagnosticosId", obj.getMaDiagnosticosId());
            query.setParameter("maDiagnosticosCodigo", obj.getMaDiagnosticosCodigo());
            query.setParameter("maDiagnosticosValor", obj.getMaDiagnosticosValor());
            query.setParameter("maeTipoDiagnosticoId", obj.getMaeTipoDiagnosticoId());
            query.setParameter("maeTipoDiagnosticoCodigo", obj.getMaeTipoDiagnosticoCodigo());
            query.setParameter("maeTipoDiagnosticoValor", obj.getMaeTipoDiagnosticoValor());
            query.setParameter("usuarioCrea", obj.getUsuarioCrea());
            query.setParameter("terminalCrea", obj.getTerminalCrea());
            query.setParameter("fechaHoraCrea", obj.getFechaHoraCrea());
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
    public AuAnexo3Diagnostico eliminar(int id) throws Exception {
        AuAnexo3Diagnostico obj = null;
        try {
            AuAnexo3Diagnosticos ent = getEntityManager().find(AuAnexo3Diagnosticos.class, id);
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

   public static AuAnexo3Diagnosticos castNegocioEntidad(AuAnexo3Diagnostico negocio){
       AuAnexo3Diagnosticos entidad = new AuAnexo3Diagnosticos();
       entidad.setId(negocio.getId());
       entidad.setAuAnexos3Id(new AuAnexos3(negocio.getAuAnexos3Id().getId()));
       entidad.setPrincipal(negocio.getPrincipal());
       entidad.setMaDiagnosticosId(negocio.getMaDiagnosticosId());
       entidad.setMaDiagnosticosCodigo(negocio.getMaDiagnosticosCodigo());
       entidad.setMaDiagnosticosValor(negocio.getMaDiagnosticosValor());
       entidad.setMaeTipoDiagnosticoId(negocio.getMaeTipoDiagnosticoId());
       entidad.setMaeTipoDiagnosticoCodigo(negocio.getMaeTipoDiagnosticoCodigo());
       entidad.setMaeTipoDiagnosticoValor(negocio.getMaeTipoDiagnosticoValor());
       //Auditoria
       entidad.setUsuarioCrea(negocio.getUsuarioCrea());
       entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
       entidad.setTerminalCrea(negocio.getTerminalCrea());
       entidad.setUsuarioModifica(negocio.getUsuarioModifica());
       entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
       entidad.setTerminalModifica(negocio.getTerminalModifica());
       return entidad;       
   }
   
   public static AuAnexo3Diagnostico castEntidadNegocio(AuAnexo3Diagnosticos entidad){
       AuAnexo3Diagnostico negocio = new AuAnexo3Diagnostico();
       negocio.setId(entidad.getId());
       negocio.setAuAnexos3Id(new AuAnexo3(entidad.getAuAnexos3Id().getId()));
       negocio.setPrincipal(entidad.getPrincipal());
       negocio.setMaDiagnosticosId(entidad.getMaDiagnosticosId());
       negocio.setMaDiagnosticosCodigo(entidad.getMaDiagnosticosCodigo());
       negocio.setMaDiagnosticosValor(entidad.getMaDiagnosticosValor());
       negocio.setMaeTipoDiagnosticoId(entidad.getMaeTipoDiagnosticoId());
       negocio.setMaeTipoDiagnosticoCodigo(entidad.getMaeTipoDiagnosticoCodigo());
       negocio.setMaeTipoDiagnosticoValor(entidad.getMaeTipoDiagnosticoValor());
       //Auditoria
       negocio.setUsuarioCrea(entidad.getUsuarioCrea());
       negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
       negocio.setTerminalCrea(entidad.getTerminalCrea());
       negocio.setUsuarioModifica(entidad.getUsuarioModifica());
       negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
       negocio.setTerminalModifica(entidad.getTerminalModifica());
       return negocio;       
   }
    
   public static List<AuAnexo3Diagnostico> casteoListaEntidadNegocio(List<AuAnexo3Diagnosticos> listaEntidad) {
        List<AuAnexo3Diagnostico> listaNegocio = new ArrayList();
        for(AuAnexo3Diagnosticos entidad: listaEntidad){
           listaNegocio.add(castEntidadNegocio(entidad));
        }
        return listaNegocio;
    }
}
